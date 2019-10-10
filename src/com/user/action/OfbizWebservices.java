/*******************************************************************************
 * /*******************************************************************************
 * * Copyright: 2019 digiBlitz Foundation
 * * 
 * * License: digiBlitz Public License 1.0 (DPL) 
 * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 * * 
 * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 * * 
 * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 * * 
 * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
package com.user.action;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMAttribute;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class OfbizWebservices {

	private static OMFactory fac;
	   private static OMNamespace omNs;
	 
	 
	   static {
	      fac = OMAbstractFactory.getOMFactory();
	      omNs = fac.createOMNamespace("http://ofbiz.apache.org/service/", "ns1");
	   }
	   
	   public String createEmployee( String salutation, String firstName, String lastName, String suffix, String contactNumber, String address1, String address2, 
			   String city, String postalCode, String emailAddress ) {
		   String partyId = null;
		   try{
			   		  
		      ServiceClient sc = new ServiceClient();
		      Options opts = new Options();
		      opts.setTo(new EndpointReference("http://192.168.1.118:8080/webtools/control/SOAPService"));
		      opts.setAction("createEmployee");
		      sc.setOptions(opts);
		    
		      OMElement res = sc.sendReceive(createEmployeePayLoad(salutation, firstName, lastName, suffix, contactNumber, address1, address2, 
					   city, postalCode, emailAddress));
		      String parentvalue=res.getQName().getLocalPart();
		      OMElement response= res.getParent().getBuilder().getDocumentElement();
		      
		      System.out.println("parentvalue---->"+parentvalue);
		     System.out.println("responsevalue--->"+response);
		     System.out.println("");
		     // System.out.println(res.getParent().getBuilder().getDocumentElement());
		     
		     String input = res.toString();
		     
		     partyId = getPartyId(input);
		     System.out.println("partyId = "+partyId);
		     
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		   return partyId;
	   }
		      
	  public static String getPartyId(String input){
		  
		  String mapkey = null;
	        String mapval = null;
	        String partyId = null;
	        
		  try {
		        DocumentBuilderFactory dbf =
		            DocumentBuilderFactory.newInstance();
		        DocumentBuilder db = dbf.newDocumentBuilder();
		        InputSource is = new InputSource();
		        is.setCharacterStream(new StringReader(input));

		        Document doc = db.parse(is);
		        //NodeList nodes = doc.getElementsByTagName("*");

		        XPathFactory xPathfactory = XPathFactory.newInstance();
		        XPath xpath = xPathfactory.newXPath();
		        XPathExpression keyexpr = xpath.compile("//map-Key/std-String[@value]");
		        XPathExpression valexpr = xpath.compile("//map-Value/std-String[@value]");
		        NodeList keynl = (NodeList) keyexpr.evaluate(doc, XPathConstants.NODESET);
		        NodeList valnl = (NodeList) valexpr.evaluate(doc, XPathConstants.NODESET);

		        Map<String, String> map = new HashMap<String, String>();
		        for (int i = 0; i < keynl.getLength(); i++)
		        {
		        	
		            Node keycurrentItem = keynl.item(i);
		            Node valcurrentItem = valnl.item(i);
		            String key = keycurrentItem.getAttributes().getNamedItem("value").getNodeValue();
		            String value = valcurrentItem.getAttributes().getNamedItem("value").getNodeValue();
		            map.put(key, value);
		            System.out.println(key+" --- "+value);
		        }
		    
		        
		        Iterator itr = map.entrySet().iterator();
		        while (itr.hasNext()) {
		        	Map.Entry<String,String> entry = (Map.Entry<String,String>) itr.next();
		        	mapkey = entry.getKey();
		        	mapval = entry.getValue();
		        	if(mapkey.equals("partyId")){
		        		partyId = mapval;
		        	}
				}
		        System.out.println("partyId "+partyId);
		        System.out.println("works fine");
		    }
		    catch (Exception e) {
		        e.printStackTrace();
		    }
		  
		  return partyId;
	  }
	
	   private static OMElement createEmployeePayLoad(String salutation, String firstName, String lastName, String suffix, String contactNumber, String address1, String address2, 
			   String city, String postalCode, String emailAddress) {
		   
		      OMElement createEmployee = fac.createOMElement("createEmployee", omNs);
		      OMElement mapMap = fac.createOMElement("map-Map", omNs);
		 
		      createEmployee.addChild(mapMap);
		 
		     // mapMap.addChild(createMapEntry("invoiceTypeId", "Payrol"));
		      //mapMap.addChild(createMapEntry("partyId", "digiBlitz Technologies"));
		      //mapMap.addChild(createMapEntry("partyIdFrom", "10160"));
		      String email="kamatchipriya@digiblitz.in";
		    
		      mapMap.addChild(createMapEntry("postalAddContactMechPurpTypeId", "PRIMARY_LOCATION"));
		      mapMap.addChild(createMapEntry("firstName", firstName));
		      mapMap.addChild(createMapEntry("lastName", lastName));
		      mapMap.addChild(createMapEntry("address1", address1));
		      mapMap.addChild(createMapEntry("address2", address2));
		      mapMap.addChild(createMapEntry("city", city));
		      mapMap.addChild(createMapEntry("postalCode", postalCode));
		      mapMap.addChild(createMapEntry("contactNumber", contactNumber));
		      mapMap.addChild(createMapEntry("emailAddress", emailAddress));
		      //mapMap.addChild(createMapEntry("countryCode", "IND"));
		      mapMap.addChild(createMapEntry("salutation", salutation));
		      mapMap.addChild(createMapEntry("suffix", suffix));
		      //mapMap.addChild(createMapEntry("birthDate", "1992-02-16"));

		      
		      
		      mapMap.addChild(createMapEntry("login.username", "admin"));
		      mapMap.addChild(createMapEntry("login.password", "ofbiz"));
		 
		      return createEmployee;
		   }
		 
	
	public String setUserDetailsToOfbiz(String currentPassword, String currentPasswordVerify, String userLoginId, 
			String requirePasswordChange, String enabled, String partyId, String userLogin, String loginUsername, 
			String loginPassword){
				
		String userCreationStatus = null;
		try{
		
			System.out.println("currentPassword "+ currentPassword+" currentPasswordVerify "+ currentPasswordVerify+" userLoginId "+ userLoginId+" requirePasswordChange "+ 
	  			requirePasswordChange+" enabled "+ enabled+" partyId "+  partyId+" userLogin "+ userLogin+" loginUsername "+ loginUsername+" loginPassword "+	loginPassword);
		
	      ServiceClient sc = new ServiceClient();
	      Options opts = sc.getOptions();
	      opts.setProperty(HTTPConstants.CHUNKED, Constants.VALUE_FALSE);
	     // serviceClient.setOptions(options);
	      
	      //Options opts = new Options();
	      opts.setTo(new EndpointReference("http://192.168.1.118:8080/webtools/control/SOAPService"));
	      opts.setAction("createUserLogin");
	      sc.setOptions(opts);
	    
	      OMElement res = sc.sendReceive(createPayLoadUser(currentPassword, currentPasswordVerify, userLoginId, 
	  			requirePasswordChange, enabled,  partyId, userLogin, loginUsername,	loginPassword));
	      System.out.println("After createPayLoad "+res);
	      String parentvalue=res.getQName().getLocalPart();
	      OMElement response= res.getParent().getBuilder().getDocumentElement();
	      
	      if(response != null){
	    	  userCreationStatus = "Not null";  
	      }
	      
	      
	      System.out.println("parentvalue----> "+parentvalue);
	      System.out.println("==========================================");
	     System.out.println("responsevalue---> "+response);
	     
		}catch(Exception e){
			e.printStackTrace();
		}
		return userCreationStatus;
	}
	
	private static OMElement createPayLoadUser(String currentPassword, String currentPasswordVerify, String userLoginId, 
			String requirePasswordChange, String enabled, String partyId, String userLogin, String loginUsername, 
			String loginPassword) {
	
		System.out.println("create Payload ---> currentPassword "+ currentPassword+" currentPasswordVerify "+ currentPasswordVerify+" userLoginId "+ userLoginId+" requirePasswordChange "+ 
	  			requirePasswordChange+" enabled "+ enabled+" partyId "+  partyId+" userLogin "+ userLogin+" loginUsername "+ loginUsername+" loginPassword "+	loginPassword);
	
	      OMElement createUserLogin = fac.createOMElement("createUserLogin", omNs);
	      OMElement mapMap = fac.createOMElement("map-Map", omNs);
	 
	      createUserLogin.addChild(mapMap);
	 
	    
	      mapMap.addChild(createMapEntry("currentPassword", currentPassword));
	      mapMap.addChild(createMapEntry("currentPasswordVerify", currentPasswordVerify));
	      mapMap.addChild(createMapEntry("userLoginId", userLoginId));
	      mapMap.addChild(createMapEntry("requirePasswordChange", requirePasswordChange));
	      mapMap.addChild(createMapEntry("enabled", enabled));
	      mapMap.addChild(createMapEntry("partyId", partyId));
	      mapMap.addChild(createMapEntry("userLogin", userLogin));
	      mapMap.addChild(createMapEntry("login.username", loginUsername));
	      mapMap.addChild(createMapEntry("login.password", loginPassword));
	 
	      return createUserLogin;
	   }
	
	//User creation ends
	
	//mapping role with security group starts
	
	   public String mapSecurityGroup(String loginName, String securityGroupId, String fromDate, String lastDate, String loginUserName, String loginPassword) {
		   
		   String mapSecurityGroupStatus = null;
		   try{
					  
		      ServiceClient sc = new ServiceClient();
		      Options opts = new Options();
		      opts.setTo(new EndpointReference("http://192.168.1.118:8080/webtools/control/SOAPService"));
		      opts.setAction("addUserLoginToSecurityGroup");
		      sc.setOptions(opts);
		    
		      OMElement res = sc.sendReceive(mapPayloadSecurityGroup(loginName, securityGroupId, fromDate, lastDate, loginUserName, loginPassword));
		      String parentvalue=res.getQName().getLocalPart();
		      OMElement response= res.getParent().getBuilder().getDocumentElement();
		      
		      if(response != null){
		    	  mapSecurityGroupStatus = "Not null";  
		      }
		      
		      System.out.println("parentvalue----> "+parentvalue);
		      System.out.println("----------------------------------------------");
		     System.out.println("responsevalue---> "+response);
		    
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		   return mapSecurityGroupStatus;
		  }
		 
	   private static OMElement mapPayloadSecurityGroup(String loginName, String securityGroupId, String fromDate, String lastDate, String loginUserName, String loginPassword) {
		   
		   OMElement addUserLoginToSecurityGroup = fac.createOMElement("addUserLoginToSecurityGroup", omNs);
		      OMElement mapMap = fac.createOMElement("map-Map", omNs);
		 
		      addUserLoginToSecurityGroup.addChild(mapMap);
		 
		    
		      mapMap.addChild(createMapEntry("groupId", securityGroupId));
		      mapMap.addChild(createMapEntry("userLoginId", loginName));
		      mapMap.addChild(createMapEntry("fromDate", fromDate));
		      mapMap.addChild(createMapEntry("thruDate", lastDate));
		     
		      mapMap.addChild(createMapEntry("login.username", loginUserName));
		      mapMap.addChild(createMapEntry("login.password", loginPassword));
		 
		      System.out.println("---------mapPayloadSecurityGroup ends -------");
		      return addUserLoginToSecurityGroup;
	
		   }
	   
	   public String createInvoice(String toPartyId, String fromPartyId, String invoiceTypeId, String invoiceStatusId, String description, String invoiceMessage) {
		   
		   String createInvoiceStatus = null;
		   
		   try{
			  
		      ServiceClient sc = new ServiceClient();
		      Options opts = new Options();
		      opts.setTo(new EndpointReference("http://192.168.1.118:8080/webtools/control/SOAPService"));
		      opts.setAction("createInvoice");
		      sc.setOptions(opts);
		    
		      OMElement res = sc.sendReceive(createInvoivePayLoad(toPartyId, fromPartyId, invoiceTypeId, invoiceStatusId, description, invoiceMessage));
		      String parentvalue=res.getQName().getLocalPart();
		      OMElement response= res.getParent().getBuilder().getDocumentElement();
		    
		      if(response != null){
		    	  createInvoiceStatus = "Invoice created Successfully";
		      }
		      
		      System.out.println("parentvalue---->"+parentvalue);
		     System.out.println("responsevalue--->"+response);
		    
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		   
		     return createInvoiceStatus;
		   }

		      
		      
		      
		 
	   public static OMElement createInvoivePayLoad(String toPartyId, String fromPartyId, String invoiceTypeId, String invoiceStatusId, String description, String invoiceMessage) {
		   
		      OMElement createInvoice = fac.createOMElement("createInvoice", omNs);
		      OMElement mapMap = fac.createOMElement("map-Map", omNs);
		 
		      createInvoice.addChild(mapMap);
		 
		    
		      mapMap.addChild(createMapEntry("invoiceTypeId", invoiceTypeId));
		      mapMap.addChild(createMapEntry("partyId", toPartyId));
		      mapMap.addChild(createMapEntry("partyIdFrom", fromPartyId));
		      mapMap.addChild(createMapEntry("statusId", invoiceStatusId));
		      mapMap.addChild(createMapEntry("invoiceMessage", invoiceMessage));
		      mapMap.addChild(createMapEntry("description", description));
		     // mapMap.addChild(createMapEntry("currencyUomId", "USD"));
		      mapMap.addChild(createMapEntry("login.username", "admin"));
		      mapMap.addChild(createMapEntry("login.password", "ofbiz"));
		 
		      return createInvoice;
		   }
	
	 
	   private static OMElement createMapEntry(String key, String val) {
	 
	      OMElement mapEntry = fac.createOMElement("map-Entry", omNs);
	 
	      // create the key
	      OMElement mapKey = fac.createOMElement("map-Key", omNs);
	      OMElement keyElement = fac.createOMElement("std-String", omNs);
	      OMAttribute keyAttribute = fac.createOMAttribute("value", null, key);
	 
	      mapKey.addChild(keyElement);
	      keyElement.addAttribute(keyAttribute);
	 
	      // create the value
	      OMElement mapValue = fac.createOMElement("map-Value", omNs);
	      OMElement valElement = fac.createOMElement("std-String", omNs);
	      OMAttribute valAttribute = fac.createOMAttribute("value", null, val);
	 
	      mapValue.addChild(valElement);
	      valElement.addAttribute(valAttribute);
	 
	      // attach to map-Entry
	      mapEntry.addChild(mapKey);
	      mapEntry.addChild(mapValue);
	 
	      return mapEntry;
	   }

	   
}

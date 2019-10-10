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
package com.db.partner.service;



import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class PartnerClient {
	
	/* ==================================== Department of State  ==============================================  */
	 

	 /*Department Of State
	  * Date: 12-04-2017
	  * Author:Priya
	  * digiBlitz Inc.
	 */
	static PartnerClient myInstance = new PartnerClient();
	public static PartnerClient getInstance(){
		return myInstance;
	}
	
	public String BecomeAPartner(String firstname, String lastname,String email, String company, 
			String Designation, String AddressLine1,String City, String Zipcode, String Country, String State,
			String PhoneNumber, String Website, String business_type, String publicorprivate,  String no_of_customer,
			String no_of_employee, String interest,String territory, String reselling){
		
		String value=null;
		try {
	           //String endpoint = "https://www.digiblitzonline.com:8943/ode/processes/DOSStage1PN?wsdl";
			   String endpoint = "http://localhost:8480/ode/processes/Partner?wsdl";
	           Service service = new Service();
	           Call call = (Call) service.createCall();
	           call.setTargetEndpointAddress( new java.net.URL(endpoint) );
	           call.setOperationName(new QName("http://service.partner.db.com/", "partnerSignUp") );
	           
	           call.addParameter("firstname", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("lastname", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("email", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("company", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("Designation", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("AddressLine1", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("City", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("Zipcode", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("Country", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("State", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("PhoneNumber", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("Website", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("business_type", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("publicorprivate", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("no_of_customer", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("no_of_employee", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("interest", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("territory", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("reselling", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           
	           call.setReturnType(org.apache.axis.Constants.XSD_STRING);
	           
	           
	          value=(String)call.invoke(new Object[]{firstname,lastname,email,company,Designation,AddressLine1,City,State,
						 Zipcode,Country,PhoneNumber,Website,business_type,publicorprivate,no_of_customer,no_of_employee,interest,territory,reselling});
	         
	          System.out.println(value);
	            System.out.println("works fine");
	       } catch (Exception e) { 
	           System.err.println(e.toString());
	       }
		return value;
	    
		
	}

}


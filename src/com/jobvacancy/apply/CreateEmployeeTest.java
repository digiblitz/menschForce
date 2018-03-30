/*******************************************************************************
 * Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/
 * 
 * License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 * 
 * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 * 
 * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 * 
 * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
package com.jobvacancy.apply;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMAttribute;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dberp.employee.session.DBERPEmployeeSessionBean;
import com.dberp.util.DBHelper;
import com.dberp.util.Employee;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

@Controller
@SuppressWarnings("unused")
public class CreateEmployeeTest {

	/**
	 * @param args
	 */
	
//	private static OMFactory fac;
//	   private static OMNamespace omNs;
//	 
//	   static {
//	      fac = OMAbstractFactory.getOMFactory();
//	      omNs = fac.createOMNamespace("http://ofbiz.apache.org/service/", "ns1");
//	   }
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping("/createEmployee.html")
	public ModelAndView ApplyJobVacancy(HttpServletRequest request,
			HttpServletResponse response) throws AxisFault {

//		ArrayList invoiceId=null;
//		ServiceClient sc = new ServiceClient();
//	      Options opts = new Options();
//	      opts.setTo(new EndpointReference(
//	         "http://192.168.1.118:8080/webtools/control/SOAPService"));
//	      opts.setAction("createEmployee");
//	      sc.setOptions(opts);
//	      String driveno = null;
//		OMElement res = sc.sendReceive(createemployees(driveno));
//	      String symbol = res.toString();
//	      System.out.println(res);
//	      
//	      invoiceId=getpartyId(symbol);
//	      
//	      String partyid = null;
//			String respoMessage = null;
//			String sucMessage = null;
//			
//			if(invoiceId!=null && invoiceId.size()!=0){
//				Iterator invoiceIdlist = invoiceId.iterator();
//				while(invoiceIdlist.hasNext()){
//					String[] invoice = (String [])invoiceIdlist.next();
//					
//					partyid = invoice[0];
//					 respoMessage = invoice[1];
//					 sucMessage = invoice[2];
//					
//				}
//			}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
		Calendar cal = Calendar.getInstance();  
		System.out.println(dateFormat.format(cal.getTime()));
		java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
		
		Employee emp = new Employee();
		emp.setEmpFname("Prabhudb");
		emp.setEmpLname("dbone");
		emp.setEmpJoiningDate(timestamp.toString());
		emp.setEmpAddress1("1st street");
		emp.setEmpAddress1c("1st street");
		emp.setEmpCityc("chennai");
		emp.setEmpPostalCodec("625105");
		emp.setEmpCity("chennai");
		emp.setEmpPostalCode("625105");
		emp.setEmpContactNo("9600215215");
		emp.setEmpMobileNo("9600215215");
		emp.setEmpEmailId("prabhu.pandi@digiblitz.in");
		emp.setEmpSSNNo("1234567890");
		emp.setEmpSalutation("Mr");
		emp.setEmpSuffix("BE");
	    
		DBERPEmployeeSessionBean objEmpSession = null;
		String partyId = null;
		try {
			partyId = new DBERPEmployeeSessionBean().addEmployeeDetailsToDBERP(emp,"http://192.168.1.118:8080/webtools/control/SOAPService");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
			
	}
	
	

}

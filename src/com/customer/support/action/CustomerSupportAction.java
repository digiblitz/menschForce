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
package com.customer.support.action;

import java.io.IOException;
import java.io.InputStream;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.xml.sax.SAXException;

import com.db.GeneralDBAction;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class CustomerSupportAction  implements Controller{
	
	static Logger log = Logger.getLogger(CustomerSupportAction.class.getName());
	
	public static final String LOG_FILE = "Log4j.properties";
	InputStream is;
	

	GeneralDBAction db = new GeneralDBAction();
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,ProtocolException,
			IOException, ParserConfigurationException, SAXException {
		
		Properties logProp = new Properties();  
	      is = (InputStream) getClass().getClassLoader().getResourceAsStream(LOG_FILE);
	      logProp.load(is); 
	      PropertyConfigurator.configure(logProp);      
	      log.info("Logging enabled"); 
		
		HttpSession session=request.getSession(true);
		String custProcess = request.getParameter("custProcess");
		
		if(custProcess.equalsIgnoreCase("getCustStarted"))
        { 
			System.out.println("Inside the getCustStarted::::::::::::::::::::::::");
			return new ModelAndView("customerSearchPage"); 
        }
		else if(custProcess.equalsIgnoreCase("getCustomerDetails"))
        { 
			System.out.println("Inside the getCustomerDetails::::::::::::::::::::::::");
			String getCustomerId = request.getParameter("customerId");
			System.out.println("getCustomerId:::::::::::::::::::::::::"+getCustomerId);
			ArrayList customerDetails = db.getCustomerDetailsById(getCustomerId);
			System.out.println("customer details :::::::::::::::::::::::"+customerDetails);
			request.setAttribute("customerDetails", customerDetails);
			return new ModelAndView("customerDetailPage"); 
        } 
		else if(custProcess.equalsIgnoreCase("changeCustomerPass"))
        { 
			System.out.println("Inside the changeCustomerPass::::::::::::::::::::::::");
			String getCustomerId = request.getParameter("customerId");
			System.out.println("getCustomerId:::::::::::::::::::::::::"+getCustomerId);
			boolean resetPassStatus = false;
			resetPassStatus = db.changeCustomerPass(getCustomerId);
			String customerPassStatus = null;
			if(resetPassStatus==true){
			customerPassStatus = "success";
			ArrayList customerDetails = db.getCustomerDetailsById(getCustomerId);
			System.out.println("customer details :::::::::::::::::::::::"+customerDetails);
			request.setAttribute("customerDetails", customerDetails);
			request.setAttribute("customerPassStatus", customerPassStatus);
			return new ModelAndView("customerDetailPage"); 
			} else{
				customerPassStatus = "fail";
				ArrayList customerDetails = db.getCustomerDetailsById(getCustomerId);
				System.out.println("customer details :::::::::::::::::::::::"+customerDetails);
				request.setAttribute("customerDetails", customerDetails);
				request.setAttribute("customerPassStatus", customerPassStatus);
				return new ModelAndView("customerDetailPage"); 
			}
        } 
		
		return null;
	}
}

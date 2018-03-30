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
package com.db.buyer;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class BuyerClient {
	
	/* ==================================== Buyer Client  ==============================================  */
	 

	 /*Buyer Client
	  * Date: 15-05-2017
	  * Author:Amalesh R
	  * digiBlitz Inc.
	 */
	static BuyerClient myInstance = new BuyerClient();
	public static BuyerClient getInstance(){
		return myInstance;
	}
	
	public String buyerToSignUpClient(String input){
		
		String value=null;
		try {
	           //String endpoint = "https://www.digiblitzonline.com:8943/ode/processes/DOSStage1PN?wsdl";
			   String endpoint = "http://www.digiblitzonline.com:8480/ode/processes/BuyerToSignUpProcess?wsdl";
	           Service service = new Service();
	           Call call = (Call) service.createCall();
	           call.setTargetEndpointAddress( new java.net.URL(endpoint) );
	           call.setOperationName(new QName("http://buyer.db.com/", "buyerToSignUp") );
	           
	           call.addParameter("input", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	          
	           
	          value=(String)call.invoke(new Object[]{input});
	         
	          System.out.println(value);
	            System.out.println("works fine");
	       } catch (Exception e) { 
	           System.err.println(e.toString());
	       }
		return value;
	    
		
	}

}

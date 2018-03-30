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
package com.db.immigration.action.USCIS.service.client;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class USCISClientStage5 {
	/* ==================================== USCIS ==============================================  */
	 

	 /*USCIS
	  * Date: 19-04-2017
	  * Author:Priya
	  * digiBlitz Inc.
	 */
	static USCISClientStage5 myInstance = new USCISClientStage5();
	public static USCISClientStage5 getInstance(){
		return myInstance;
	}
	
	public String stage5USCIS(String filePath,  String createDate, String ackDate, 
			String systemDate, String status,String documentName,String MultipleFilePath){
		
		String value=null;
		try {
	           String endpoint = 
	           			  "http://www.digiblitzonline.com:8480/ode/processes/USCISStage5PN?wsdl";
	     
	           Service service = new Service();
	           Call call = (Call) service.createCall();
	           call.setTargetEndpointAddress( new java.net.URL(endpoint) );
	           call.setOperationName(new QName("http://service.USCIS.action.immigration.db.com/", "USCISStage5") );
	           
	           call.addParameter("filePath", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("createDate", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("ackDate", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("systemDate", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("status", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("documentName", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("MultipleFilePath", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	          
	           call.setReturnType(org.apache.axis.Constants.XSD_STRING);
	           
	           
	          value=(String)call.invoke(new Object[]{filePath,createDate,ackDate,systemDate,status,documentName,MultipleFilePath});
	         
	          System.out.println(value);
	            System.out.println("works fine");
	       } catch (Exception e) { 
	           System.err.println(e.toString());
	       }
		return value;
	    
		
	}

}

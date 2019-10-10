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
package com.db.immigration.action.DOS.service.client;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class DOSClientStage1 {
	
	/* ==================================== Department of State  ==============================================  */
	 

	static DOSClientStage1 myInstance = new DOSClientStage1();
	public static DOSClientStage1 getInstance(){
		return myInstance;
	}
	
	public String stage1dos(String empUniqueId,String fileLocation, String UPLOAD_DIRECTORY, String createDate,
			String acknowledgementDate, String systemDate, String approveStatus){
		
		String value=null;
		try {
	           //String endpoint = "https://www.digiblitzonline.com:8943/ode/processes/DOSStage1PN?wsdl";
			   String endpoint = "http://localhost:8480/ode/processes/DOSStage1PN?wsdl";
	           Service service = new Service();
	           Call call = (Call) service.createCall();
	           call.setTargetEndpointAddress( new java.net.URL(endpoint) );
	           call.setOperationName(new QName("http://service.DOS.action.immigration.db.com/", "DOSStage1") );
	           
	           call.addParameter("user_id", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("file_path", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("multiple_file_path", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("created_date", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("ack_date", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("system_date", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("status", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.setReturnType(org.apache.axis.Constants.XSD_STRING);
	           
	           
	          value=(String)call.invoke(new Object[]{empUniqueId,fileLocation,UPLOAD_DIRECTORY,createDate,acknowledgementDate,systemDate,approveStatus});
	         
	          System.out.println(value);
	            System.out.println("works fine");
	       } catch (Exception e) { 
	           System.err.println(e.toString());
	       }
		return value;
	    
		
	}

}

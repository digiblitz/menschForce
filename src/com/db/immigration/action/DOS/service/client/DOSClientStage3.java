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
package com.db.immigration.action.DOS.service.client;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class DOSClientStage3 {
	
	/* ==================================== Department of State  ==============================================  */
	 

	 /*Department Of State
	  * Date: 12-04-2017
	  * Author:Priya
	  * digiBlitz Inc.
	 */
	
	static DOSClientStage3 myInstance = new DOSClientStage3();
	public static DOSClientStage3 getInstance(){
		return myInstance;
	}
	
public String stage3dos(String startDate,String endDate, String createDate,String acknowledgementDate,String systemDate,String approveStatus){
		
		String value=null;
		try {
	           //String endpoint = "https://www.digiblitzonline.com:8943/ode/processes/DOSStage3PN?wsdl";
	           String endpoint = "http://www.digiblitzonline.com:8480/ode/processes/DOSStage3PN?wsdl";
	     
	           Service service = new Service();
	           Call call = (Call) service.createCall();
	           call.setTargetEndpointAddress( new java.net.URL(endpoint) );
	           call.setOperationName(new QName("http://service.DOS.action.immigration.db.com/", "DOSStage3") );
	           
	           call.addParameter("start_date", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("end_date", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("created_date", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("ack_date", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("system_date", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.addParameter("status", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
	           call.setReturnType(org.apache.axis.Constants.XSD_STRING);
	           
	         
	           
	          value=(String)call.invoke(new Object[]{startDate,endDate,createDate,acknowledgementDate,systemDate,approveStatus});
	         
	          System.out.println(value);
	            System.out.println("works fine");
	       } catch (Exception e) { 
	           System.err.println(e.toString());
	       }
		return value;
	    
		
	}

}

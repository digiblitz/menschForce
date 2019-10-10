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
package com.db.email.service;



	import javax.xml.namespace.QName;
	import javax.xml.rpc.ParameterMode;

	import org.apache.axis.client.Call;
import org.apache.axis.client.Service;




	public class EmailServiceClient {
		
		static EmailServiceClient  myInstance = new EmailServiceClient();
		public static EmailServiceClient getInstance(){
			return myInstance;
		}
		
		
		
		public  String EmailRemainder(){
			System.out.println("in EmailRemainder method in EmailServiceClient");
			String value=null;
			try {
		           //String endpoint = "https://www.digiblitzonline.com:8943/ode/processes/DOSStage1PN?wsdl";
				   String endpoint = "http://localhost:8480/ode/processes/reminderemailservice?wsdl";
		           Service service = new Service();
		           Call call = (Call) service.createCall();
		           call.setTargetEndpointAddress( new java.net.URL(endpoint) );
		           call.setOperationName(new QName( "http://service.email.db.com/","process") );
		           
		           call.addParameter("payload", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
		          
		           call.setReturnType(org.apache.axis.Constants.XSD_STRING);
		           
		           String input="dummyinput";
		          value=(String)call.invoke(new Object[]{input});
		         
		          System.out.println(value);
		            System.out.println("works fine");
		       } catch (Exception e) { 
		           System.err.println(e.toString());
		       }
			return value;
		    
			
		}



		
		
	 
	 
	}



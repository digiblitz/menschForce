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
package com.db.immigration.action.DOS.service;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.db.immigration.action.DOS.service.client.DOSClientStage1;
import com.db.immigration.action.DOS.service.client.DOSClientStage4;
public class DOSClientTest {
	
	/* ==================================== Department of State  ==============================================  */
	 

	

	public static void main(String[] args) throws Exception {
		/*URL url = new URL("http://localhost:8060/menschforce/DOS/DOSService?wsdl");
		QName qname = new QName("http://service.DOS.action.immigration.db.com/","DOSServiceService");
		Service service = Service.create(url, qname);
		DOSStagesService State = service.getPort(DOSStagesService.class);
		System.out.println(State.Stage1("123","d://first.docx","d://first.docx","09-08-2017","09-08-2017","09-08-2017","approved"));
		System.out.println(State.Stage2("d://first.docx","d://first.docx","09-08-2017","09-08-2017","09-08-2017","approved"));
		System.out.println(State.Stage3("09-08-2017","09-08-2017","09-08-2017","09-08-2017","09-08-2017","approved"));
		System.out.println(State.Stage4("d://first.docx","09-08-2017","09-08-2017","09-08-2017","approved"));
		System.out.println(State.Stage5("d://first.docx","d://first.docx","09-08-2017","09-08-2017","09-08-2017","approved"));
		System.out.println(State.Stage6("d://first.docx","09-08-2017","09-08-2017","09-08-2017","09-08-2017","09-08-2017","approved"));*/
		
		//Test BPEL Proccess Stage1
		String dosBPELStatus = null;
    	DOSClientStage1 clientStage1 = new DOSClientStage1();
    	dosBPELStatus = clientStage1.stage1dos("12345", "d://first.docx", "d://first.docx", "09-08-2017","09-08-2017","09-08-2017","approved");
            
    	if(dosBPELStatus != null && dosBPELStatus.equalsIgnoreCase("Success1")){
    		System.out.println("Stage1 success");
    	}else {
    		System.out.println("Stage1 failed");
    	}
    	
    	//Test BPEL Proccess Stage4
    			String dosBPELStatus4 = null;
    	    	DOSClientStage4 clientStage4 = new DOSClientStage4();
    	    	dosBPELStatus4 = clientStage4.stage4dos("d://first.docx", "09-08-2017","09-08-2017","09-08-2017","approved");
    	            
    	    	if(dosBPELStatus4 != null && dosBPELStatus4.equalsIgnoreCase("Success4")){
    	    		System.out.println("Stage4 success");
    	    	}else {
    	    		System.out.println("Stage4 failed");
    	    	}
	}

}

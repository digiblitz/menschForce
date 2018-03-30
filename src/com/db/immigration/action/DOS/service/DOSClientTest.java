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
package com.db.immigration.action.DOS.service;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
public class DOSClientTest {
	
	/* ==================================== Department of State  ==============================================  */
	 

	 /*Department Of State
	  * Date: 10-04-2017
	  * Author:Priya
	  * digiBlitz Inc.
	 */
	

	public static void main(String[] args) throws Exception {
		URL url = new URL("http://localhost:8080/menschforce/DOS/DOSService?wsdl");
		QName qname = new QName("http://service.immigration.com/","DOSServiceService");
		Service service = Service.create(url, qname);
		DOSStagesService State = service.getPort(DOSStagesService.class);
		System.out.println(State.Stage1("123","d://first.docx","d://first.docx","09-08-2017","09-08-2017","09-08-2017","approved"));
		System.out.println(State.Stage2("d://first.docx","d://first.docx","09-08-2017","09-08-2017","09-08-2017","approved"));
		System.out.println(State.Stage3("09-08-2017","09-08-2017","09-08-2017","09-08-2017","09-08-2017","approved"));
		System.out.println(State.Stage4("d://first.docx","09-08-2017","09-08-2017","09-08-2017","approved"));
		System.out.println(State.Stage5("d://first.docx","d://first.docx","09-08-2017","09-08-2017","09-08-2017","approved"));
		System.out.println(State.Stage6("d://first.docx","09-08-2017","09-08-2017","09-08-2017","09-08-2017","09-08-2017","approved"));
	}

}

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
package com.emailservice.QASession;


import javax.xml.ws.Endpoint;

import org.springframework.stereotype.Controller;

import com.db.buyer.candidate.BuyerCandidateService;
import com.db.partner.service.PartnerService;
import com.temp.timesheet.TimesheetClient;
import com.temp.timesheet.timesheetservice;

@Controller
public class QAPublisher{
	
	
	
	static{
		
		QAClient emailClient=new QAClient();
		System.out.println("------ Published EmailServiceQA ------");
		
	//	Endpoint.publish("http://localhost:8780/EmailService", new PartnerService());
		//Endpoint.publish("http://localhost:8670/EmailServiceQA/EmailServiceQA", new QAService());
		//Endpoint.publish("http://localhost:8670/QASession/Emailservice", new QASessionService());
			
		//emailClient.TimesheetRemainder();
		
	}
}


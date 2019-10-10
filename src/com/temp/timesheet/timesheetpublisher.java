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
package com.temp.timesheet;


import javax.xml.ws.Endpoint;

import org.springframework.stereotype.Controller;

import com.ContactUs.ContactService;
import com.db.buyer.candidate.BuyerCandidateService;
import com.db.partner.service.PartnerService;
import com.temp.timesheet.timesheetservice;

@Controller
public class timesheetpublisher{
	
	
	
	static{
		TimesheetClient timehseetclient=new TimesheetClient();
		System.out.println("------ Published timesheet ------");
	//	Endpoint.publish("http://localhost:8670/Timesheet/TimesheetService", new timesheetservice());
		//Endpoint.publish("http://localhost:18080/BecomeAPartnership", new PartnerService());
		//Endpoint.publish("http://localhost:8670/mFContact/ContactService", new ContactService());
		
		System.out.println("------ Published success ------");
		//timehseetclient.timesheetremainder();
		
	}
}


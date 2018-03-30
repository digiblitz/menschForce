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


import javax.xml.ws.Endpoint;

import org.springframework.stereotype.Controller;

import com.db.buyer.candidate.BuyerCandidateService;

@Controller
public class BuyerPublisher{
	
	static{
		//System.out.println("------ Published ------");
		//Endpoint.publish("http://localhost:8670/Buyer/BuyerService", new BuyerService());
		//Endpoint.publish("http://localhost:8670/Buyer/Candidate/BuyerCandidateService", new BuyerCandidateService());
	}
	
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		Endpoint.publish("http://localhost:8980/Buyer/BuyerService", new BuyerService());
	}*/

	/*
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		Endpoint.publish("http://localhost:8980/Buyer/BuyerService", new BuyerService());
		System.out.println("-----------------------------------------");
		System.out.println("-----------------------------------------");
		System.out.println("-----------------------------------------");
		System.out.println("-----------------------------------------");
		System.out.println("-----------------------------------------");
		System.out.println("-----------------------------------------");
		System.out.println("-----------------------------------------");
		System.out.println("-----------------------------------------");
		System.out.println("-----------------------------------------");
	}*/

}

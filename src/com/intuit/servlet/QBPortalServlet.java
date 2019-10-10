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
package com.intuit.servlet;

import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QBPortalServlet {
	
	
	@RequestMapping("/QBDashboard.html")
	public ModelAndView syncEmployee(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{

			return new ModelAndView("QB_jsp/QBDashboard");
			
	}
	
	@RequestMapping("/EmployeePortal.html")
	public ModelAndView EmployeePortal(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
			request.setAttribute("empSyncStatus", "init");
			return new ModelAndView("QB_jsp/QBEmployeePortal");
		
	}
	
	@RequestMapping("/CustomerPortal.html")
	public ModelAndView CustomerPortal(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
			request.setAttribute("customerSyncStatus", "init");
			return new ModelAndView("QB_jsp/QBCustomerPortal");
		
	}

}

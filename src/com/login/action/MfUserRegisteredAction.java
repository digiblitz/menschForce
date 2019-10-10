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
package com.login.action;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.db.GeneralDBAction;
import com.hlcform.util.HLCUserMaster;
import com.hlchorse.form.display.HLCKaverySessionStatefulBean;
import com.jobvacancy.requirements.RequirementsAction;

@Controller
public class MfUserRegisteredAction {
	
	Logger logger = LoggerFactory.getLogger(MfUserRegisteredAction.class.getName());
	GeneralDBAction db = new GeneralDBAction();
	HLCUserMaster objUserMaster = new HLCUserMaster();
	HLCKaverySessionStatefulBean hrsStBean= new HLCKaverySessionStatefulBean();
	int totalNofPages = 1;
	
	@RequestMapping("/ListRegisteredUsers.html")
	public ModelAndView ListRegisteredUsers(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException, SQLException{
		
		HttpSession session=request.getSession(true); 
		
		String company = (String)session.getAttribute("jobPostCompanyName");
		ArrayList ListRegisteredUser = db.getAllUserMasterDetails(company);
		ArrayList RegisteredUserList = null;
		RequirementsAction req = new RequirementsAction();
		
		String currentPageNo = (String)request.getParameter("currentPageNo");
        
        RegisteredUserList = req.doPagination(ListRegisteredUser,currentPageNo);

        request.setAttribute("page", "PostRequirementElse");
        System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
        request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
        System.out.println("currentPageNo------------------>"+currentPageNo);
	 	request.setAttribute("currentPageNo", currentPageNo);
        request.setAttribute("RegisteredUserList",RegisteredUserList);
       //request.setAttribute("pageStatus","postReqList");
		request.setAttribute("RegisteredUserList", null);
		request.setAttribute("RegisteredUserList", RegisteredUserList);
		     
		return new ModelAndView("frmListUserRegistration");
	}
	
	@RequestMapping("/UpdateUserActiveStatus.html")
	public ModelAndView UpdateUserActiveStatus(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException, SQLException{
		
		HttpSession session=request.getSession(true); 
		
		 String userId = request.getParameter("user_Id");
		 String company = (String)session.getAttribute("jobPostCompanyName");
		 
         System.out.println("USER ID : "+userId);
         ArrayList ListRegisteredUser =null;
         String activeStat = request.getParameter("activeStatus");
         boolean bol1 = hrsStBean.editActiveStatus(activeStat, userId);
         
         try{
        	 
        	  ListRegisteredUser = db.getAllUserMasterDetails(company);
        	 
         }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //request.getParameter("selMemTypeId");
          
 		 ArrayList RegisteredUserList = null;
 		 RequirementsAction req = new RequirementsAction();
 		
 		String currentPageNo = (String)request.getParameter("currentPageNo");
         
         RegisteredUserList = req.doPagination(ListRegisteredUser,currentPageNo);

         request.setAttribute("page", "PostRequirementElse");
         System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
         request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
         System.out.println("currentPageNo------------------>"+currentPageNo);
 	 	request.setAttribute("currentPageNo", currentPageNo);
         request.setAttribute("RegisteredUserList",RegisteredUserList);
        //request.setAttribute("pageStatus","postReqList");
 		request.setAttribute("RegisteredUserList", null);
 		request.setAttribute("RegisteredUserList", RegisteredUserList);
         
         return new ModelAndView("frmListUserRegistration");
         
	}
}

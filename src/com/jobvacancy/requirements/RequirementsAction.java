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
package com.jobvacancy.requirements;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.eclipse.core.internal.jobs.JobStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.db.GeneralDBAction;
import com.hlccommon.util.Debug;
import com.hlcform.util.HLCUserMaster;
import com.infusionejb.session.InfusionSessionBean;

@Controller
public class RequirementsAction {
	
	int totalNofPages = 1;

	Logger logger = LoggerFactory.getLogger("RequirementAction.class");
	
	 private InfusionSessionBean obj1;
		{
			try{
				obj1 = new InfusionSessionBean();
			}catch(Exception e){
				 throw new RuntimeException(e);
			}
		}
	
	@RequestMapping("/CreatePostRequirements.html")
	public ModelAndView ViewPostRequirement(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		return new ModelAndView("requirements/createPostReq");
	}
	@RequestMapping("/PostRequirements.html")
	public ModelAndView InertPostRequirement(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		
		PostRequirementsBean objPostReq = new PostRequirementsBean();
		GeneralDBAction db=new GeneralDBAction();
		
		
		String JobTitle = null;
		String ClientReferenceID = null;
		String ReferenceID = null;
		String ClientIndustry = null;
		String PositionID = null;
		String Position = null;
		String InterviewProcess = null;
		String VisaAccepted = null;
		String Skills = null;
		String JobType = null;
		String LocationField = null;
		String NumberOfOpenings = null;
		String RecruiterEmail = null;
		String StartDate = null;
		String EndDate = null;
		String RequirementStatus = null; 
		String Rate = null;
		String Duration = null;
		String LocalRequired = null;
		String Salary = null;
		String FlexiblityOnSalary = null;
		String FlexiblityOnHrlyRate = null;
		String DateOnHold = null;
		String ExtraDocumentsRequired = null;
		//String RequiredExperience = null;
		String minExperience = null;
		String maxExperience = null;
		String Notes = null;
		String jobResponsibilites = null;
		String jobPostCompanyName = null;
		String jobPostCompanyCategory = null;
		String jobPostCompanyId = null;
		String jobPostUserId = null;
		String jobPostUserName = null;
		String jobPostUserRoleId = null;
		String jobPostUserRoleName = null;
		String uniqueJobPostCompanyId = null;
		//String requirementId = null;
		
//		ClientReferenceID = "test";
//		ReferenceID = "test";
//		ClientIndustry = "test";
//		PositionID = "test";
//		Position = "test";
//		InterviewProcess = "test";
//		VisaAccepted = "test";
//		Skills = "test";
//		JobType = "test";
//		LocationField = "test";
//		NumberOfOpenings = "test";
//		RecruiterEmail = "test";
//		StartDate = "test";
//		EndDate = "test";
//		RequirementStatus = "test"; 
//		Rate = "1";
//		Duration = "test";
//		LocalRequired = "test";
//		Salary = "test";
//		FlexiblityOnSalary = "test";
//		FlexiblityOnHrlyRate = "test";
//		DateOnHold = "test";
//		ExtraDocumentsRequired = "test";
//		RequiredExperience = "test";
//		Notes = "test";
//		jobResponsibilites = "test";
//		jobPostCompanyName = "test";
//		jobPostCompanyId = "test";
//		jobPostUserId = "test";
//		jobPostUserName = "test";
//		jobPostUserRoleId = "test";
//		jobPostUserRoleName = "test";
//		uniqueJobPostCompanyId = "test";
//		requirementId = "test";
		
		uniqueJobPostCompanyId = (String)session.getAttribute("uniqueJobPostCompanyId");
		jobPostCompanyName = (String)session.getAttribute("jobPostCompanyName");
		jobPostCompanyCategory = (String)session.getAttribute("jobPostCompanyCategory");
		jobPostCompanyId = (String)session.getAttribute("jobPostCompanyId");
		jobPostUserId = (String)session.getAttribute("jobPostUserId");
		jobPostUserName = (String)session.getAttribute("jobPostUserName");
		jobPostUserRoleId = (String)session.getAttribute("jobPostUserRoleId");
		jobPostUserRoleName = (String)session.getAttribute("jobPostUserRoleName");
		
		JobTitle = request.getParameter("JobTitle");
		//requirementId = request.getParameter("requirementId");
		ClientReferenceID = request.getParameter("ClientReferenceID");
		ReferenceID = request.getParameter("ReferenceID");
		ClientIndustry = request.getParameter("ClientIndustry");
		PositionID = request.getParameter("PositionID");
		Position = request.getParameter("Position");
		InterviewProcess = request.getParameter("InterviewProcess");
		VisaAccepted = request.getParameter("VisaAccepted");
		Skills = request.getParameter("Skills");
		JobType = request.getParameter("JobType");
		LocationField = request.getParameter("LocationField");
		NumberOfOpenings = request.getParameter("NumberOfOpenings");
		RecruiterEmail = request.getParameter("RecruiterEmail");
		StartDate = request.getParameter("StartDate");
		EndDate = request.getParameter("EndDate");
		RequirementStatus = request.getParameter("RequirementStatus");
		Rate = request.getParameter("Rate");
		Duration = request.getParameter("Duration");
		LocalRequired = request.getParameter("LocalRequired");
		Notes = request.getParameter("Notes");
		Salary = request.getParameter("Salary");
		FlexiblityOnSalary = request.getParameter("FlexiblityOnSalary");
		FlexiblityOnHrlyRate = request.getParameter("FlexiblityOnHrlyRate");
		DateOnHold = request.getParameter("DateOnHold");
		ExtraDocumentsRequired = request.getParameter("ExtraDocumentsRequired");
		jobResponsibilites = request.getParameter("jobResponsibilities");
		//RequiredExperience = request.getParameter("RequiredExperience");
		minExperience = request.getParameter("minExperience");
		maxExperience = request.getParameter("maxExperience");
		
		System.out.println("uniqueJobPostCompanyId--------------->"+uniqueJobPostCompanyId);
		System.out.println("jobPostCompanyName--------------->"+jobPostCompanyName);
		System.out.println("jobPostCompanyCategory--------------->"+jobPostCompanyCategory);
		System.out.println("jobPostCompanyId--------------->"+jobPostCompanyId);
		System.out.println("jobPostUserId--------------->"+jobPostUserId);
		System.out.println("jobPostUserName--------------->"+jobPostUserName);
		System.out.println("jobPostUserRoleId--------------->"+jobPostUserRoleId);
		System.out.println("jobPostUserRoleName--------------->"+jobPostUserRoleName);
		System.out.println("JobTitle--------------->"+JobTitle);
		//System.out.println("requirementId--------------->"+requirementId);
		System.out.println("ClientReferenceID--------------->"+ClientReferenceID);
		System.out.println("ReferenceID--------------->"+ReferenceID);
		System.out.println("ClientIndustry--------------->"+ClientIndustry);
		System.out.println("PositionID--------------->"+PositionID);
		System.out.println("Position--------------->"+Position);
		System.out.println("InterviewProcess--------------->"+InterviewProcess);
		System.out.println("VisaAccepted--------------->"+VisaAccepted);
		System.out.println("Skills--------------->"+Skills);
		System.out.println("JobType--------------->"+JobType);
		System.out.println("LocationField--------------->"+LocationField);
		System.out.println("NumberOfOpenings--------------->"+NumberOfOpenings);
		System.out.println("RecruiterEmail--------------->"+RecruiterEmail);
		System.out.println("StartDate--------------->"+StartDate);
		System.out.println("EndDate--------------->"+EndDate);
		System.out.println("RequirementStatus--------------->"+RequirementStatus);
		System.out.println("Rate--------------->"+Rate);
		System.out.println("Duration--------------->"+Duration);
		System.out.println("LocalRequired--------------->"+LocalRequired);
		System.out.println("Notes--------------->"+Notes);
		System.out.println("Salary--------------->"+Salary);
		System.out.println("FlexiblityOnSalary--------------->"+FlexiblityOnSalary);
		System.out.println("FlexiblityOnHrlyRate--------------->"+FlexiblityOnHrlyRate);
		System.out.println("DateOnHold--------------->"+DateOnHold);
		System.out.println("ExtraDocumentsRequired---------------->"+ExtraDocumentsRequired);
		System.out.println("jobResponsibilites--------------->"+jobResponsibilites);
		//System.out.println("RequiredExperience--------------->"+RequiredExperience);
		System.out.println("minExperience------------------->"+minExperience);
		System.out.println("maxExperience------------------->"+maxExperience);
		
		
		
		objPostReq.setJobTitle(JobTitle);
		//objPostReq.setrequirementId(requirementId);
		objPostReq.setClientIndustry(ClientIndustry);
		objPostReq.setClientReferenceID(ClientReferenceID);
		objPostReq.setDateOnHold(DateOnHold);
		objPostReq.setRate(Rate);
		objPostReq.setRecruiterEmail(RecruiterEmail);
		objPostReq.setDuration(Duration);
		objPostReq.setEndDate(EndDate);
		objPostReq.setExtraDocumentsRequired(ExtraDocumentsRequired);
		objPostReq.setFlexiblityOnHrlyRate(FlexiblityOnHrlyRate);
		objPostReq.setFlexiblityOnSalary(FlexiblityOnSalary);
		objPostReq.setInterviewProcess(InterviewProcess);
		objPostReq.setjobResponsibilites(jobResponsibilites);
		objPostReq.setJobType(JobType);
		objPostReq.setLocalRequired(LocalRequired);
		objPostReq.setLocationField(LocationField);
		objPostReq.setNotes(Notes);
		objPostReq.setNumberOfOpenings(NumberOfOpenings);
		objPostReq.setPosition(Position);
		objPostReq.setPositionID(PositionID);
		objPostReq.setReferenceID(ReferenceID);
		//objPostReq.setRequiredExperience(RequiredExperience);
		objPostReq.setminExperience(minExperience);
		objPostReq.setmaxExperience(maxExperience);
		objPostReq.setRequirementStatus(RequirementStatus);
		objPostReq.setSalary(Salary);
		objPostReq.setSkills(Skills);
		objPostReq.setStartDate(StartDate);
		objPostReq.setVisaAccepted(VisaAccepted);
		objPostReq.setjobPostCompanyId(jobPostCompanyId);
		objPostReq.setjobPostCompanyName(jobPostCompanyName);
		objPostReq.setjobPostCompanyCategory(jobPostCompanyCategory);
		objPostReq.setjobPostUserId(jobPostUserId);
		objPostReq.setjobPostUserName(jobPostUserName);
		objPostReq.setjobPostUserRoleId(jobPostUserRoleId);
		objPostReq.setjobPostUserRoleName(jobPostUserRoleName);
		objPostReq.setuniqueJobPostCompanyId(uniqueJobPostCompanyId);
		



boolean insertPostStatus = false;
		String RID = null;
		ArrayList ContactIDS = new ArrayList();
		String htmlBody= null;
		
		try {
			RID = db.selectRequirementId();
			insertPostStatus = db.insertPostRequirementDetails(objPostReq);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(insertPostStatus == true){
			System.out.println("Insert Job Post was successfully comes from Servlet------------>"+insertPostStatus);
		
			
			new RequirementActionThread(JobTitle,LocationField,Notes,RID,Position,minExperience,maxExperience,RequirementStatus,jobPostCompanyName,RecruiterEmail).start();
			
			
			Debug.print("RequirementsAction.postReqList()");


	        ArrayList postReqList = new ArrayList();
	        postReqList = (ArrayList)db.getAllPostReq(uniqueJobPostCompanyId,jobPostCompanyCategory);
	        
	        String currentPageNo = (String)request.getParameter("currentPageNo");
	        
	        postReqList = doPagination(postReqList,currentPageNo);

	        request.setAttribute("page", "PostRequirement");
	        System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
	        request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
	        System.out.println("currentPageNo------------------>"+currentPageNo);
		 	request.setAttribute("currentPageNo", currentPageNo);
	        request.setAttribute("allPostReqList",postReqList);
	        request.setAttribute("pageStatus","postReqList");
			return new ModelAndView("requirements/listPostReq");
		}else{
			System.out.println("Insert Job Post was not successfully comes from Servlet------------>"+insertPostStatus);
			Debug.print("RequirementsAction.postReqList()");
	        ArrayList postReqList = new ArrayList();
	        postReqList = (ArrayList)db.getAllPostReq(uniqueJobPostCompanyId,jobPostCompanyCategory);

	        String currentPageNo = (String)request.getParameter("currentPageNo");
	        
	        postReqList = doPagination(postReqList,currentPageNo);

	        request.setAttribute("page", "PostRequirementElse");
	        System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
	        request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
	        System.out.println("currentPageNo------------------>"+currentPageNo);
		 	request.setAttribute("currentPageNo", currentPageNo);
	        request.setAttribute("allPostReqList",postReqList);
	        request.setAttribute("pageStatus","postReqList");
			return new ModelAndView("requirements/listPostReq");
		}
	}
	
	@RequestMapping("/ListPostRequirements.html")
	public ModelAndView ListPostRequirement(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		String reqMails = request.getParameter("ReqMails");
		System.out.println("reqMails --> "+reqMails);
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("RequirementsAction.postReqList()");
		String jobPostCompanyUniqueId = null;
		String companyJobPostCategory = null;

		jobPostCompanyUniqueId = (String)session.getAttribute("uniqueJobPostCompanyId");
		companyJobPostCategory = (String)session.getAttribute("jobPostCompanyCategory");
        ArrayList postReqList = new ArrayList();
        postReqList = (ArrayList)db.getAllPostReq(jobPostCompanyUniqueId,companyJobPostCategory);
        
        String currentPageNo = (String)request.getParameter("currentPageNo");
        
        postReqList = doPagination(postReqList,currentPageNo);

        request.setAttribute("page", "ListPostRequirement");
        System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
        request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
        System.out.println("currentPageNo------------------>"+currentPageNo);
	 	request.setAttribute("currentPageNo", currentPageNo);
        request.setAttribute("allPostReqList",postReqList);
        request.setAttribute("pageStatus","postReqList");
        
        request.setAttribute("reqMails", reqMails);
        
		return new ModelAndView("requirements/listPostReq");
	}
	
	@RequestMapping("/ViewPostRequirementByUniquePostId.html")
	public ModelAndView ViewPostRequirementByUniquePostId(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("RequirementsAction.ViewPostRequirementByUniquePostId()");
		String postReqByUniqueId = null;
		String jobStatus=null;
		postReqByUniqueId = request.getParameter("uniqueId");
		jobStatus=request.getParameter("jobStatus");
        ArrayList postReqList = new ArrayList();
        postReqList = (ArrayList)db.getPostReqByUniqueId(postReqByUniqueId);
        request.setAttribute("jobStatus", jobStatus);
        request.setAttribute("PostReqList",null);
        request.setAttribute("PostReqList",postReqList);
		return new ModelAndView("requirements/viewPostReq");
	}
	
	@RequestMapping("/viewjobDetailsbyreqID.html")
	public ModelAndView viewjobDetailsbyreqID(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		String reqID =null;
		ArrayList PostReqList = new ArrayList();
		reqID = request.getParameter("RID");
        String req_uniqueID = null;
        req_uniqueID = (String)db.getuniqueID(reqID);
        PostReqList = (ArrayList)db.getPostReqByUniqueId(req_uniqueID);
        request.setAttribute("PostReqList",null);
        request.setAttribute("PostReqList",PostReqList);
        request.setAttribute("reqpageStatus","view");
        
		return new ModelAndView("requirements/viewPostReq");
	}
	
	@RequestMapping("/ListPostRequirementsByOpenStatus.html")
	public ModelAndView ListPostRequirementsByOpenStatus(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		String jobPostCompanyUniqueId = null;
		String companyJobPostCategory = null;

		jobPostCompanyUniqueId = (String)session.getAttribute("uniqueJobPostCompanyId");
		companyJobPostCategory = (String)session.getAttribute("jobPostCompanyCategory");
		Debug.print("RequirementsAction.postReqList()");
        ArrayList postReqList = new ArrayList();
        postReqList = (ArrayList)db.getAllPostReqByStatus("open",jobPostCompanyUniqueId,companyJobPostCategory);

        String currentPageNo = (String)request.getParameter("currentPageNo");
        
        postReqList = doPagination(postReqList,currentPageNo);

        request.setAttribute("page", "OpenStatus");
        System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
        request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
        System.out.println("currentPageNo------------------>"+currentPageNo);
	 	request.setAttribute("currentPageNo", currentPageNo);
        request.setAttribute("allPostReqList",postReqList);
        request.setAttribute("pageStatus","open");
		return new ModelAndView("requirements/listPostReq");
	}
	
	@RequestMapping("/ListPostRequirementsByClosedStatus.html")
	public ModelAndView ListPostRequirementsByClosedStatus(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		String jobPostCompanyUniqueId = null;
		String companyJobPostCategory = null;

		jobPostCompanyUniqueId = (String)session.getAttribute("uniqueJobPostCompanyId");
		companyJobPostCategory = (String)session.getAttribute("jobPostCompanyCategory");
		
		Debug.print("RequirementsAction.postReqList()");
        ArrayList postReqList = new ArrayList();
        postReqList = (ArrayList)db.getAllPostReqByStatus("closed",jobPostCompanyUniqueId,companyJobPostCategory);

        String currentPageNo = (String)request.getParameter("currentPageNo");
        
        postReqList = doPagination(postReqList,currentPageNo);

        request.setAttribute("page", "ClosedStatus");
        System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
        request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
        System.out.println("currentPageNo------------------>"+currentPageNo);
	 	request.setAttribute("currentPageNo", currentPageNo);
        request.setAttribute("allPostReqList",postReqList);
        request.setAttribute("pageStatus","closed");
		return new ModelAndView("requirements/listPostReq");
	}
	
	@RequestMapping("/ListPostRequirementsByOnHoldStatus.html")
	public ModelAndView ListPostRequirementsByOnHoldStatus(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		String jobPostCompanyUniqueId = null;
		String companyJobPostCategory = null;

		jobPostCompanyUniqueId = (String)session.getAttribute("uniqueJobPostCompanyId");
		companyJobPostCategory = (String)session.getAttribute("jobPostCompanyCategory");
		Debug.print("RequirementsAction.postReqList()");
        ArrayList postReqList = new ArrayList();
        postReqList = (ArrayList)db.getAllPostReqByStatus("onhold",jobPostCompanyUniqueId,companyJobPostCategory);

        String currentPageNo = (String)request.getParameter("currentPageNo");
        
        postReqList = doPagination(postReqList,currentPageNo);

        request.setAttribute("page", "OnHoldStatus");
        System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
        request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
        System.out.println("currentPageNo------------------>"+currentPageNo);
	 	request.setAttribute("currentPageNo", currentPageNo);
        request.setAttribute("allPostReqList",postReqList);
        request.setAttribute("pageStatus","onhold");
		return new ModelAndView("requirements/listPostReq");
	}
	
	@RequestMapping("/ListPostRequirementsByDeclinedStatus.html")
	public ModelAndView ListPostRequirementsByDeniedStatus(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		String jobPostCompanyUniqueId = null;
		String companyJobPostCategory = null;

		jobPostCompanyUniqueId = (String)session.getAttribute("uniqueJobPostCompanyId");
		companyJobPostCategory = (String)session.getAttribute("jobPostCompanyCategory");
		Debug.print("RequirementsAction.postReqList()");
        ArrayList postReqList = new ArrayList();
        postReqList = (ArrayList)db.getAllPostReqByStatus("declined",jobPostCompanyUniqueId,companyJobPostCategory);

        String currentPageNo = (String)request.getParameter("currentPageNo");
        
        postReqList = doPagination(postReqList,currentPageNo);

        request.setAttribute("page", "DeclinedStatus");
        System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
        request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
        System.out.println("currentPageNo------------------>"+currentPageNo);
	 	request.setAttribute("currentPageNo", currentPageNo);
        request.setAttribute("allPostReqList",postReqList);
        request.setAttribute("pageStatus","declined");
		return new ModelAndView("requirements/listPostReq");
	}
	
	@RequestMapping("/VMSDashboard.html")
	public ModelAndView VMSDashboard(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		String roleId = null;
		roleId = (String) session.getAttribute("roleId");
		
			if(roleId != null && roleId != "" && !roleId.isEmpty()){
			String roleDetails[] = db.getRole(roleId );
	        String vmsDashboard = roleDetails[5];
		        if(vmsDashboard != null){
		        	return new ModelAndView("requirements/"+vmsDashboard);
		        }else{
		        	return new ModelAndView("requirements/norole");
		        }
			}else{
				return new ModelAndView("requirements/norole");
			}
	}
	
	@RequestMapping("/initCreateRateNegotiation.html")
	public ModelAndView initCreateRateNegotiation(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("RequirementsAction.initCreateRateNegotiation()");
		String postReqByUniqueId = null;
		postReqByUniqueId = request.getParameter("uniqueId");
        ArrayList postReqList = new ArrayList();
        postReqList = (ArrayList)db.getPostReqByUniqueId(postReqByUniqueId);

        request.setAttribute("PostReqList",null);
        request.setAttribute("PostReqList",postReqList);
		return new ModelAndView("requirements/frmMFRateNegotiation");
	}
	
	@RequestMapping("/createRateNegotiation.html")
	public ModelAndView createRateNegotiation(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException, SQLException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("RequirementsAction.createRateNegotiation()");
		String postReqByUniqueId = null;
		String postReqId = null;
		String negotiateRate = null;
		String negotiateReason = null;
		String rateNegotiateCompanyName = null;
		String rateNegotiateCompanyCategory = null;
		String rateNegotiateCompanyId = null;
		String rateNegotiateUserId = null;
		String rateNegotiateUserName = null;
		String rateNegotiateUserRoleId = null;
		String rateNegotiateUserRoleName = null;
		String uniqueRateNegotiateCompanyId = null;
		
		postReqByUniqueId = request.getParameter("postReqUniqueId");
		postReqId = request.getParameter("postReqId");
		negotiateRate = request.getParameter("negotiateRate");
		negotiateReason = request.getParameter("negotiateReason");
		uniqueRateNegotiateCompanyId = (String)session.getAttribute("uniqueJobPostCompanyId");
		rateNegotiateCompanyName = (String)session.getAttribute("jobPostCompanyName");
		rateNegotiateCompanyCategory = (String)session.getAttribute("jobPostCompanyCategory");
		rateNegotiateCompanyId = (String)session.getAttribute("jobPostCompanyId");
		rateNegotiateUserId = (String)session.getAttribute("jobPostUserId");
		rateNegotiateUserName = (String)session.getAttribute("jobPostUserName");
		rateNegotiateUserRoleId = (String)session.getAttribute("jobPostUserRoleId");
		rateNegotiateUserRoleName = (String)session.getAttribute("jobPostUserRoleName");
        boolean status = false;
        boolean existStatus = true;
        existStatus = db.findRateNegotiateExistByPostUserId(postReqByUniqueId,rateNegotiateUserId);
        if(existStatus == false){
        status = db.insertRateNegotiation(postReqByUniqueId,postReqId,negotiateRate,negotiateReason,uniqueRateNegotiateCompanyId
        		,rateNegotiateCompanyName,rateNegotiateCompanyCategory,rateNegotiateCompanyId,rateNegotiateUserId,rateNegotiateUserName,rateNegotiateUserRoleId
        		,rateNegotiateUserRoleName);
        if(status = true){
			System.out.println("Rate negotiation successfully comes from Servlet------------>"+status);
			ArrayList postReqList = new ArrayList();
	        postReqList = (ArrayList)db.getPostReqByUniqueId(postReqByUniqueId);
	        if(postReqList!=null && postReqList.size()!=0){
			Iterator itr = postReqList.iterator();
			while (itr.hasNext()) {    
		        String TempList[] = (String[])itr.next();
		        String jobPostUserId =TempList[0];
		        
		        String postReqUsrEmail = db.getEmailIdByPassword(jobPostUserId);
		        String firstName = null;
                String lastName = null;
	                ArrayList userNameDetails = db.getFirstLastUserNameByUserId(jobPostUserId);
	                if(userNameDetails!=null && userNameDetails.size()!=0){
						Iterator ituserNameDetails = userNameDetails.iterator();
						int i=1;
						while(ituserNameDetails.hasNext()){
						
							String strUserList[]= (String[])ituserNameDetails.next();
							firstName = strUserList[0];
							lastName = strUserList[1];
							System.out.println("firstName :::::::::::::::::::::"+firstName);
							System.out.println("lastName :::::::::::::::::::::"+lastName);
						}
					}
				 
			/*-------------------Send Requirement Mails To Vendors start here-----------------------*/
			try {
           	obj1.createAndCheckDuplicateContact(firstName, lastName, postReqUsrEmail);
           	obj1.optin_outEmail(postReqUsrEmail);
			obj1.sendEmail("info@menschforce.com", postReqUsrEmail, "prabhu.pandi@digiblitz.in", "", "Text/Html",
						"Requirement Rate Negotiation Notification",
						"Rate : "+negotiateRate+ "Reason : "+negotiateReason+" Requested By : "+rateNegotiateUserName+"(Role - )"+rateNegotiateUserRoleName+" Company : "+rateNegotiateCompanyName+" - "+rateNegotiateCompanyCategory,
						"Rate : "+negotiateRate+ "Reason : "+negotiateReason+" Requested By : "+rateNegotiateUserName+"(Role - )"+rateNegotiateUserRoleName+" Company : "+rateNegotiateCompanyName+" - "+rateNegotiateCompanyCategory);
				System.out.println("email send successfully");
			} catch (XmlRpcException e) {
				e.printStackTrace();
			}
			
			/*-------------------Send Requirement Mails To Vendors end here-----------------------*/
			}}
	        request.setAttribute("status", "success");
	        ArrayList listReqRateNegotiation = new ArrayList();
	        listReqRateNegotiation = db.getListReqRateNegotiationByRequesterId(rateNegotiateUserId);
	        request.setAttribute("listReqRateNegotiation",null);
	        request.setAttribute("listReqRateNegotiation",listReqRateNegotiation);
	        return new ModelAndView("requirements/frmListReqRateNegotiation");
        }else{
        	request.setAttribute("status", "fail");
        	ArrayList listReqRateNegotiation = new ArrayList();
	        listReqRateNegotiation = db.getListReqRateNegotiationByRequesterId(rateNegotiateUserId);
	        request.setAttribute("listReqRateNegotiation",null);
	        request.setAttribute("listReqRateNegotiation",listReqRateNegotiation);
	        return new ModelAndView("requirements/frmListReqRateNegotiation");
        }
        }else{
        	request.setAttribute("existStatus", "exist");
        	return new ModelAndView("requirements/frmMFRateNegotiation");
        }
	}
	
	@RequestMapping("/rateNegotiationRequestList.html")
	public ModelAndView rateNegotiationRequestList(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		String rateNegotiateUserId = null;
		rateNegotiateUserId = (String)session.getAttribute("jobPostUserId");
		
		request.setAttribute("status", "init");
		ArrayList listReqRateNegotiation = new ArrayList();
        listReqRateNegotiation = db.getListReqRateNegotiationByRequesterId(rateNegotiateUserId);
        request.setAttribute("listReqRateNegotiation",null);
        request.setAttribute("listReqRateNegotiation",listReqRateNegotiation);
        return new ModelAndView("requirements/frmListReqRateNegotiation");
	}
	
	@RequestMapping("/rateNegotiationList.html")
	public ModelAndView rateNegotiationList(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		String jobPostCompanyUniqueId = null;
		String companyJobPostCategory = null;
		String rateNegotiateUserId = null;
		rateNegotiateUserId = (String)session.getAttribute("jobPostUserId");
		jobPostCompanyUniqueId = (String)session.getAttribute("uniqueJobPostCompanyId");
		companyJobPostCategory = (String)session.getAttribute("jobPostCompanyCategory");
		request.setAttribute("status", "init");
    	ArrayList listReqRateNegotiation = new ArrayList();
        listReqRateNegotiation = db.getListAllReqRateNegotiation(jobPostCompanyUniqueId,companyJobPostCategory,rateNegotiateUserId);
        request.setAttribute("listReqRateNegotiation",null);
        request.setAttribute("listReqRateNegotiation",listReqRateNegotiation);
		return new ModelAndView("requirements/frmListReqRateNegotiation");
	}
	
	@RequestMapping("/ViewRateNegotiation.html")
	public ModelAndView ViewRateNegotiation(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		String rateNegUniqueId = null;
		rateNegUniqueId = request.getParameter("uniqueId");
		request.setAttribute("status", "init");
    	ArrayList listReqRateNegotiation = new ArrayList();
        listReqRateNegotiation = db.getReqRateNegotiationByUniqueId(rateNegUniqueId);
        request.setAttribute("listReqRateNegotiation",null);
        request.setAttribute("listReqRateNegotiation",listReqRateNegotiation);
		return new ModelAndView("requirements/frmViewReqRateNegotiation");
	}
	
	@RequestMapping("/ListPostRequirementsByFreshness.html")
	public ModelAndView ListPostRequirementsByFreshness(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		 String Number_of_days = null;
		 String minimum_Exp = null;
		 String maximum_Exp = null;
		 String salary_range_from = null;
		 String salary_range_to = null;
		 String rate_range_from = null;
		 String rate_range_to = null;
		 String skills_value = null;
		 String position_value = null;
		 String location_value = null;
		 String Industry_value = null;
		 String jobtype_value = null;
		 
		  Number_of_days = (String)request.getParameter("days");
		  minimum_Exp = (String)request.getParameter("minExperience");
		  maximum_Exp = (String)request.getParameter("maxExperience");
		  salary_range_from = (String)request.getParameter("salary_range_from");
		  salary_range_to = (String)request.getParameter("salary_range_to");
		  rate_range_from = (String)request.getParameter("rate_range_from");
		  rate_range_to = (String)request.getParameter("rate_range_to");
		  skills_value = (String)request.getParameter("skills_value");
		  position_value = (String)request.getParameter("position_value");
		  location_value = (String)request.getParameter("location_value");
		  Industry_value = (String)request.getParameter("Industry_value");
		  jobtype_value = (String)request.getParameter("jobtype_value");
		 
		 int days=0;
			
				if(Number_of_days!=null){
					 days = Integer.parseInt(Number_of_days);
				  	}
				
				
				
					
	      System.out.println("number of days------"+Number_of_days); 
		  System.out.println("minimum_Exp----"+minimum_Exp);
		  System.out.println("maximum_Exp--------"+maximum_Exp);
		  System.out.println("position value----"+position_value);
		  System.out.println("skills-------"+skills_value);
		  System.out.println("location----"+location_value);
		  
		  System.out.println("Industry_value-------"+Industry_value);
		  System.out.println("jobtype_value----"+jobtype_value);
	 	Debug.print("RequirementsAction.postReqList()");
        ArrayList postReqList = new ArrayList();
        postReqList = (ArrayList)db.selectAllPostReqByDays(days,minimum_Exp,maximum_Exp,salary_range_from,salary_range_to,
        		rate_range_from, rate_range_to, skills_value,  position_value,
    			 Industry_value, jobtype_value, location_value);
       // postReqList = (ArrayList)db.selectAllPostReqByExp(minimum_Exp,maximum_Exp);
       // postReqList = (ArrayList)db.selectAllPostReqBySalary(salary_range_from,salary_range_to);
        
        String currentPageNo = (String)request.getParameter("currentPageNo");
        
        postReqList = doPagination(postReqList,currentPageNo);

        request.setAttribute("page", "ListPostReqByFreshness");
        System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
        request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
        System.out.println("currentPageNo------------------>"+currentPageNo);
	 	request.setAttribute("currentPageNo", currentPageNo);
        request.setAttribute("allPostReqList",postReqList);
        request.setAttribute("pageStatus","ListPostReqByFreshness");
		return new ModelAndView("requirements/listPostReq");
	}
	
	
	public ArrayList doPagination(ArrayList postReqList,String page){
		Map<Integer, ArrayList> map = new HashMap<Integer, ArrayList>();
     System.out.println("checksize-----"+postReqList.size());
	//  ArrayList req_mail = new ArrayList();
    
  	 
  	  
	  ArrayList al = new ArrayList();
	  int i = 1;
	  int j = 15;
	  //int count = 1;
	  totalNofPages = 1;
	  
	  Iterator itr1 = postReqList.iterator();
	  while(itr1.hasNext()){
	   String[] tempI =  (String[]) itr1.next();
	 
	   al.add(tempI);
	   
	   if(i == j){    
	    map.put(totalNofPages, al);
	    j=j+15;
	    totalNofPages = totalNofPages+1;
	    al = new ArrayList();
	    
	   }
	   i++; 
	  }
	  System.out.println("al size --- "+al.size());
	  System.out.println("map size --- "+map.size());
	 
	  
	  int key = 0;
	int pagenumber=1;
	 
	
	if(page!=null){
 pagenumber = Integer.parseInt(page);
	}
	 
	 
	  
	  Iterator<Map.Entry<Integer, ArrayList>> entries = map.entrySet().iterator();
	  while (entries.hasNext()) {
	      Map.Entry<Integer, ArrayList> entry = entries.next();
	      
	      key = entry.getKey();
	    
	  
	      if(pagenumber == key){
	       al = entry.getValue();
	      }
	     
	  }
	   System.out.println("al "+al.size());
	   
	   System.out.println("totalNofPages in doPagination Method------------------>"+totalNofPages);
	   
	  // Iterator itrr = al.iterator();
	//   while (itrr.hasNext()) {
	 //  System.out.println("itr "+itrr.next());
	//  }
	  //String [] array = (String[]) res.toArray();
	  
	  //System.out.println(array);
	  
  return al;
	}
	
	@RequestMapping("/EditPostRequirementByUniquePostId.html")
	public ModelAndView EditPostRequirementByUniquePostId(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("RequirementsAction.EditostRequirementByUniquePostId()");
		String postReqByUniqueId = null;
		String jobStatus = null;
		postReqByUniqueId = request.getParameter("uniqueId");
		jobStatus=request.getParameter("jobStatus");
        ArrayList postReqList = new ArrayList();
        postReqList = (ArrayList)db.getPostReqByUniqueId(postReqByUniqueId);
        
        request.setAttribute("PostReqList",null);
        request.setAttribute("PostReqList",postReqList);
        request.setAttribute("pageStatus","Edit");
        request.setAttribute("jobStatus", jobStatus);
        System.out.println("jobStatus in java=="+jobStatus);
		return new ModelAndView("requirements/editPostReq");
	}
	
	@RequestMapping("/RepostPostRequirementByUniquePostId.html")
	public ModelAndView RepostPostRequirementByUniquePostId(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("RequirementsAction.EditostRequirementByUniquePostId()");
		String postReqByUniqueId = null;
		String jobStatus = null;
		postReqByUniqueId = request.getParameter("uniqueId");
		jobStatus=request.getParameter("jobStatus");
        ArrayList postReqList = new ArrayList();
        postReqList = (ArrayList)db.getPostReqByUniqueId(postReqByUniqueId);

        request.setAttribute("PostReqList",null);
        request.setAttribute("PostReqList",postReqList);
        request.setAttribute("pageStatus","repost");
        request.setAttribute("jobStatus", jobStatus);
		return new ModelAndView("requirements/editPostReq");
	}
	
	
	
	@RequestMapping("/updatePostRequirementByUniquePostId.html")
	public ModelAndView updatePostRequirementByUniquePostId(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
	HttpSession session=request.getSession(true); 
	
	PostRequirementsBean objPostReq = new PostRequirementsBean();
	GeneralDBAction db=new GeneralDBAction();
	
	String pagestatus=null;
	String JobTitle = null;
	String ClientReferenceID = null;
	String ReferenceID = null;
	String ClientIndustry = null;
	String PositionID = null;
	String Position = null;
	String InterviewProcess = null;
	String VisaAccepted = null;
	String Skills = null;
	String JobType = null;
	String LocationField = null;
	String NumberOfOpenings = null;
	String RecruiterEmail = null;
	String StartDate = null;
	String EndDate = null;
	String RequirementStatus = null; 
	String Rate = null;
	String Duration = null;
	String LocalRequired = null;
	String Salary = null;
	String FlexiblityOnSalary = null;
	String FlexiblityOnHrlyRate = null;
	String DateOnHold = null;
	String ExtraDocumentsRequired = null;
	//String RequiredExperience = null;
	String minExperience = null;
	String maxExperience = null;
	String Notes = null;
	String jobResponsibilites = null;
	String jobPostCompanyName = null;
	String jobPostCompanyCategory = null;
	String jobPostCompanyId = null;
	String jobPostUserId = null;
	String jobPostUserName = null;
	String jobPostUserRoleId = null;
	String jobPostUserRoleName = null;
	String uniqueJobPostCompanyId = null;
	String postReqUniqueId=null;
	String requirementId = null;
	String jobStatus = null;
	
//	ClientReferenceID = "test";
//	ReferenceID = "test";
//	ClientIndustry = "test";
//	PositionID = "test";
//	Position = "test";
//	InterviewProcess = "test";
//	VisaAccepted = "test";
//	Skills = "test";
//	JobType = "test";
//	LocationField = "test";
//	NumberOfOpenings = "test";
//	RecruiterEmail = "test";
//	StartDate = "test";
//	EndDate = "test";
//	RequirementStatus = "test"; 
//	Rate = "1";
//	Duration = "test";
//	LocalRequired = "test";
//	Salary = "test";
//	FlexiblityOnSalary = "test";
//	FlexiblityOnHrlyRate = "test";
//	DateOnHold = "test";
//	ExtraDocumentsRequired = "test";
//	RequiredExperience = "test";
//	Notes = "test";
//	jobResponsibilites = "test";
//	jobPostCompanyName = "test";
//	jobPostCompanyId = "test";
//	jobPostUserId = "test";
//	jobPostUserName = "test";
//	jobPostUserRoleId = "test";
//	jobPostUserRoleName = "test";
//	uniqueJobPostCompanyId = "test";
//	requirementId = "test";
	
	
	uniqueJobPostCompanyId = (String)session.getAttribute("uniqueJobPostCompanyId");
	jobPostCompanyName = (String)session.getAttribute("jobPostCompanyName");
	jobPostCompanyCategory = (String)session.getAttribute("jobPostCompanyCategory");
	jobPostCompanyId = (String)session.getAttribute("jobPostCompanyId");
	jobPostUserId = (String)session.getAttribute("jobPostUserId");
	jobPostUserName = (String)session.getAttribute("jobPostUserName");
	jobPostUserRoleId = (String)session.getAttribute("jobPostUserRoleId");
	jobPostUserRoleName = (String)session.getAttribute("jobPostUserRoleName");
	
	JobTitle = request.getParameter("JobTitle");
	requirementId = request.getParameter("requirementId");
	ClientReferenceID = request.getParameter("ClientReferenceID");
	ReferenceID = request.getParameter("ReferenceID");
	ClientIndustry = request.getParameter("ClientIndustry");
	PositionID = request.getParameter("PositionID");
	Position = request.getParameter("Position");
	InterviewProcess = request.getParameter("InterviewProcess");
	VisaAccepted = request.getParameter("VisaAccepted");
	Skills = request.getParameter("Skills");
	JobType = request.getParameter("JobType");
	LocationField = request.getParameter("LocationField");
	NumberOfOpenings = request.getParameter("NumberOfOpenings");
	RecruiterEmail = request.getParameter("RecruiterEmail");
	StartDate = request.getParameter("StartDate");
	EndDate = request.getParameter("EndDate");
	RequirementStatus = request.getParameter("RequirementStatus");
	Rate = request.getParameter("Rate");
	Duration = request.getParameter("Duration");
	LocalRequired = request.getParameter("LocalRequired");
	Notes = request.getParameter("Notes");
	Salary = request.getParameter("Salary");
	FlexiblityOnSalary = request.getParameter("FlexiblityOnSalary");
	FlexiblityOnHrlyRate = request.getParameter("FlexiblityOnHrlyRate");
	DateOnHold = request.getParameter("DateOnHold");
	ExtraDocumentsRequired = request.getParameter("ExtraDocumentsRequired");
	jobResponsibilites = request.getParameter("jobResponsibilities");
	//RequiredExperience = request.getParameter("RequiredExperience");
	minExperience = request.getParameter("minExperience");
	maxExperience = request.getParameter("maxExperience");
	postReqUniqueId = request.getParameter("postReqUniqueId");
	pagestatus = request.getParameter("pageStatus");
	jobStatus=request.getParameter("jobStatus");
	
	System.out.println("uniqueJobPostCompanyId--------------->"+uniqueJobPostCompanyId);
	System.out.println("jobPostCompanyName--------------->"+jobPostCompanyName);
	System.out.println("jobPostCompanyCategory--------------->"+jobPostCompanyCategory);
	System.out.println("jobPostCompanyId--------------->"+jobPostCompanyId);
	System.out.println("jobPostUserId--------------->"+jobPostUserId);
	System.out.println("jobPostUserName--------------->"+jobPostUserName);
	System.out.println("jobPostUserRoleId--------------->"+jobPostUserRoleId);
	System.out.println("jobPostUserRoleName--------------->"+jobPostUserRoleName);
	System.out.println("JobTitle--------------->"+JobTitle);
	System.out.println("requirementId--------------->"+requirementId);
	System.out.println("ClientReferenceID--------------->"+ClientReferenceID);
	System.out.println("ReferenceID--------------->"+ReferenceID);
	System.out.println("ClientIndustry--------------->"+ClientIndustry);
	System.out.println("PositionID--------------->"+PositionID);
	System.out.println("Position--------------->"+Position);
	System.out.println("InterviewProcess--------------->"+InterviewProcess);
	System.out.println("VisaAccepted--------------->"+VisaAccepted);
	System.out.println("Skills--------------->"+Skills);
	System.out.println("JobType--------------->"+JobType);
	System.out.println("LocationField--------------->"+LocationField);
	System.out.println("NumberOfOpenings--------------->"+NumberOfOpenings);
	System.out.println("RecruiterEmail--------------->"+RecruiterEmail);
	System.out.println("StartDate--------------->"+StartDate);
	System.out.println("EndDate--------------->"+EndDate);
	System.out.println("RequirementStatus--------------->"+RequirementStatus);
	System.out.println("Rate--------------->"+Rate);
	System.out.println("Duration--------------->"+Duration);
	System.out.println("LocalRequired--------------->"+LocalRequired);
	System.out.println("Notes--------------->"+Notes);
	System.out.println("Salary--------------->"+Salary);
	System.out.println("FlexiblityOnSalary--------------->"+FlexiblityOnSalary);
	System.out.println("FlexiblityOnHrlyRate--------------->"+FlexiblityOnHrlyRate);
	System.out.println("DateOnHold--------------->"+DateOnHold);
	System.out.println("ExtraDocumentsRequired---------------->"+ExtraDocumentsRequired);
	System.out.println("jobResponsibilites--------------->"+jobResponsibilites);
	//System.out.println("RequiredExperience--------------->"+RequiredExperience);
	System.out.println("minExperience------------------->"+minExperience);
	System.out.println("maxExperience------------------->"+maxExperience);
	
	
	
	objPostReq.setJobTitle(JobTitle);
	objPostReq.setrequirementId(requirementId);
	objPostReq.setClientIndustry(ClientIndustry);
	objPostReq.setClientReferenceID(ClientReferenceID);
	objPostReq.setDateOnHold(DateOnHold);
	objPostReq.setRate(Rate);
	objPostReq.setRecruiterEmail(RecruiterEmail);
	objPostReq.setDuration(Duration);
	objPostReq.setEndDate(EndDate);
	objPostReq.setExtraDocumentsRequired(ExtraDocumentsRequired);
	objPostReq.setFlexiblityOnHrlyRate(FlexiblityOnHrlyRate);
	objPostReq.setFlexiblityOnSalary(FlexiblityOnSalary);
	objPostReq.setInterviewProcess(InterviewProcess);
	objPostReq.setjobResponsibilites(jobResponsibilites);
	objPostReq.setJobType(JobType);
	objPostReq.setLocalRequired(LocalRequired);
	objPostReq.setLocationField(LocationField);
	objPostReq.setNotes(Notes);
	objPostReq.setNumberOfOpenings(NumberOfOpenings);
	objPostReq.setPosition(Position);
	objPostReq.setPositionID(PositionID);
	objPostReq.setReferenceID(ReferenceID);
	//objPostReq.setRequiredExperience(RequiredExperience);
	objPostReq.setminExperience(minExperience);
	objPostReq.setmaxExperience(maxExperience);
	objPostReq.setRequirementStatus(RequirementStatus);
	objPostReq.setSalary(Salary);
	objPostReq.setSkills(Skills);
	objPostReq.setStartDate(StartDate);
	objPostReq.setVisaAccepted(VisaAccepted);
	objPostReq.setjobPostCompanyId(jobPostCompanyId);
	objPostReq.setjobPostCompanyName(jobPostCompanyName);
	objPostReq.setjobPostCompanyCategory(jobPostCompanyCategory);
	objPostReq.setjobPostUserId(jobPostUserId);
	objPostReq.setjobPostUserName(jobPostUserName);
	objPostReq.setjobPostUserRoleId(jobPostUserRoleId);
	objPostReq.setjobPostUserRoleName(jobPostUserRoleName);
	objPostReq.setuniqueJobPostCompanyId(uniqueJobPostCompanyId);
	
	boolean insertPostStatus = false;
	try {
		insertPostStatus = db.updatePostRequirementDetails(objPostReq,postReqUniqueId);
	} catch (SQLException e1) {
		e1.printStackTrace();
	}	
	
	
System.out.println("Update Job Post ------------>"+insertPostStatus);
	

	
	if(insertPostStatus == true){
		System.out.println("Update Job Post was successfully comes from Servlet------------>"+insertPostStatus);
		
		if(pagestatus!=null && pagestatus.equalsIgnoreCase("repost")){
		//-------------------Send Requirement Mails To Vendors start here-----------------------
			 new RequirementActionThread(JobTitle,LocationField,Notes,requirementId,Position,minExperience,maxExperience,RequirementStatus,jobPostCompanyName,RecruiterEmail).start();
		//-------------------Send Requirement Mails To Vendors end here-----------------------
		}
		
		Debug.print("RequirementsAction.postReqList()");
        ArrayList postReqList = new ArrayList();
        
        if(jobStatus!= null && !jobStatus.equalsIgnoreCase("postReqList")){
        postReqList = (ArrayList)db.getAllPostReqByStatus(jobStatus,uniqueJobPostCompanyId,jobPostCompanyCategory);
        }else{
        	postReqList = (ArrayList)db.getAllPostReq(uniqueJobPostCompanyId,jobPostCompanyCategory);
        }
        String currentPageNo = (String)request.getParameter("currentPageNo");
        
        postReqList = doPagination(postReqList,currentPageNo);

        request.setAttribute("page", "PostRequirement");
        System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
        request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
        System.out.println("currentPageNo------------------>"+currentPageNo);
	 	request.setAttribute("currentPageNo", currentPageNo);
        request.setAttribute("allPostReqList",postReqList);
        request.setAttribute("pageStatus",jobStatus);
		return new ModelAndView("requirements/listPostReq");
	}else{
		System.out.println("Update Job Post was not successfully comes from Servlet------------>"+insertPostStatus);
		Debug.print("RequirementsAction.postReqList()");
        ArrayList postReqList = new ArrayList();
        if(jobStatus!= null && !jobStatus.equalsIgnoreCase("postReqList")){
            postReqList = (ArrayList)db.getAllPostReqByStatus(jobStatus,uniqueJobPostCompanyId,jobPostCompanyCategory);
            }else{
            	postReqList = (ArrayList)db.getAllPostReq(uniqueJobPostCompanyId,jobPostCompanyCategory);
            }

        String currentPageNo = (String)request.getParameter("currentPageNo");
        
        postReqList = doPagination(postReqList,currentPageNo);

        request.setAttribute("page", "PostRequirementElse");
        System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
        request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
        System.out.println("currentPageNo------------------>"+currentPageNo);
	 	request.setAttribute("currentPageNo", currentPageNo);
        request.setAttribute("allPostReqList",postReqList);
        request.setAttribute("pageStatus",jobStatus);
		return new ModelAndView("requirements/listPostReq");
	
	}
	
	/*ArrayList postReqList = new ArrayList();
    postReqList = (ArrayList)db.getAllPostReq(uniqueJobPostCompanyId,jobPostCompanyCategory);

    String currentPageNo = (String)request.getParameter("currentPageNo");
    
    postReqList = doPagination(postReqList,currentPageNo);

    request.setAttribute("page", "PostRequirementElse");
    System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
    request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
    System.out.println("currentPageNo------------------>"+currentPageNo);
 	request.setAttribute("currentPageNo", currentPageNo);
    request.setAttribute("allPostReqList",postReqList);
    request.setAttribute("pageStatus","postReqList");
	return new ModelAndView("requirements/listPostReq");
	}*/
	}
	@RequestMapping("/ViewRequirementByRIDfromMail.html")
	public ModelAndView ViewRequirementByRIDfromMail(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
	HttpSession session=request.getSession(true); 
	
	
	GeneralDBAction db=new GeneralDBAction();
		
		String req_uniqueID=null;
		ArrayList PostReqList = new ArrayList();
		String reqID = request.getParameter("RID");
		
		req_uniqueID = (String)db.getuniqueID(reqID);
		PostReqList = (ArrayList)db.getPostReqByUniqueId(req_uniqueID);
		
		request.setAttribute("PostReqList",PostReqList);
		return new ModelAndView ("requirements/frmMailViewReq");
	}
	
	@RequestMapping("frmHomeForTrial.html")
	 public ModelAndView frmHomeForTrial(HttpServletRequest request,
	   HttpServletResponse response) throws RemoteException{
	 HttpSession session=request.getSession(true); 
	 
	 
	  
	  return new ModelAndView ("requirements/frmHomeforTrial");
	 }
	
	@RequestMapping("DeletePostRequirementByUniqueID.html")
	 public ModelAndView DeletePostRequirementByUniqueID(HttpServletRequest request,
	   HttpServletResponse response) throws Exception{
	 HttpSession session=request.getSession(true); 
	 Debug.print("PayrollAction.empDeletePayroll()");
	 GeneralDBAction db=new GeneralDBAction();

		String Req_Id = null;
		
		Req_Id = request.getParameter("requirementId");
		
		System.out.println("requirementId from delete method------------->"+Req_Id);
		
		
		boolean deleteStatus = false;
		if(Req_Id == null || Req_Id == ""){
			throw new Exception("Requirement Not Found!");
		}else{
			boolean deletePostById = db.deletePostRequirementByID(Req_Id);
			
		}
		
	  return new ModelAndView ("requirements/listPostReq");
	 }
}

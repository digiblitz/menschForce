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
package com.fresh.candidate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Properties;


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
import com.infusionejb.session.InfusionSessionBean;
import com.jobvacancy.apply.MFAppliedCandidateBean;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

@Controller
public class FreshCandidateAction {
	

	int totalNofPages = 1;

	Logger logger = LoggerFactory.getLogger("FreshCandidateAction.class");
	
	public static final String config_prop = "config.properties";
	public static final String LOG_FILE = "Log4j.properties";
	InputStream is;
	InputStream config_inputStream;
	
	Properties config_property = new Properties();
	
	 private InfusionSessionBean obj1;
		{
			try{
				obj1 = new InfusionSessionBean();
			}catch(Exception e){
				 throw new RuntimeException(e);
			}
		}
		
		GeneralDBAction db=new GeneralDBAction();
	
	@RequestMapping("/initCreateFreshCandidate.html")
	public ModelAndView initCreateFreshCandidate(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		request.setAttribute("createStatus", "init");
		return new ModelAndView("requirements/createFreshCandidate");
	}
	
	@RequestMapping("/createFreshCandidate.html")
	public ModelAndView createFreshCandidate(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		
		
		config_inputStream = getClass().getClassLoader().getResourceAsStream(config_prop);
		config_property.load(config_inputStream);
		
		String FreshCandiadateName = null;
		String documentStatus = null;
		String MobileNumber = null;
		String FreshCandidateEmail = null;
		String visaType = null;
		String Comments = null;
		String status = null;
		String RecruitedBy = null;
		String candidateCompanyUniqueId = null;	
		String candidateCompany = null;
		String candidateCompanyCategory = null;
		String userId = null;
		String education_certificates =null;
		String passport = null;
		String experience_letters = null;
		String other_achievements =null;
		
		HttpSession session = request.getSession(true) ;
		
		candidateCompanyUniqueId = (String) session .getAttribute("uniqueJobPostCompanyId");
		candidateCompany = (String) session.getAttribute("jobPostCompanyName");
		candidateCompanyCategory = (String) session.getAttribute("jobPostCompanyCategory");
		userId = (String) session.getAttribute("jobPostUserId");
		
		String fileLocation = null;
		String saveDirectory = config_property.getProperty("config.resumePath");/*save uploaded files to a 'Upload' directory in the web app*/
		
		System.out.println("saveDirectory----->"+saveDirectory);
		String resumeName = null;
		
		    if (!(new File(saveDirectory)).exists()) {
		    	(new File(saveDirectory)).mkdir();    // creates the directory if it does not exist        
		    }
			String parmName = "";
			String resp = "";
		    int i = 1;
		    resp += "<br>Here is information about uploaded files.<br>";
		      MultipartParser parser = new MultipartParser(request, 1024 * 1024 * 1024);  /* file limit size of 1GB*/
	            Part _part;
	            int k=0;
	            while ((_part = parser.readNextPart()) != null) {
	            	parmName = _part.getName();
		                if(_part.isParam()){
		                	System.out.println("Inside paramPart");
		                	ParamPart paramPart = (ParamPart)_part;
		                	
		                	if(parmName.equals("freshcandidate_name")){
		                		FreshCandiadateName = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("Passport")){
		                		passport = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("Education Certificates")){
		                		education_certificates = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("Experience Letters")){
		                		experience_letters = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("Other Achievements")){
		                		other_achievements = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("mobile_num")){
		                		MobileNumber = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("freshcandidate_email")){
		                		FreshCandidateEmail = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("visa_type")){
		                		visaType = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("comments")){
		                		Comments = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("status")){
		                		status = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("recruiter")){
		                		RecruitedBy = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	
		                }
		                if(_part.isFile()){
		                	
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();			                    
			                    resumeName = name;
			                    System.out.println("resumeName --> "+resumeName);
					          if (name != null) {
					        	  long fileSize = fPart.writeTo(new File(saveDirectory));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = saveDirectory+name;
					          }else {
			                      resp = "<br>The user did not upload a file for this part.";
			                  }
		                }
	            }
	            
	           
		
		 documentStatus = passport+","+education_certificates+","+experience_letters+","+other_achievements;
		
		boolean insertStatus =db.insertFreshCandidate(FreshCandiadateName,documentStatus,visaType,FreshCandidateEmail,
					 Comments,status,MobileNumber,RecruitedBy,userId, candidateCompanyUniqueId,candidateCompanyCategory,candidateCompany,fileLocation) ;
		
		if(insertStatus){
    		
    		//code starts to store the resume in database server
    		
    		int BUFFER_SIZE = 4096;
    		 
            String ftpUrl = config_property.getProperty("config.ftpURL");
            String host = config_property.getProperty("config.dbhost");
            String user = config_property.getProperty("config.dbuserName");
            String pass = config_property.getProperty("config.dbpassword");
            
            System.out.println("ftpUrl --> "+ftpUrl+" host--> "+host);
            System.out.println("user --> "+user+" pass--> "+pass);
            
            String filePath = fileLocation;
            String uploadPath = config_property.getProperty("config.resumePathSolr")+resumeName;
     
            System.out.println("uploadPath --> "+uploadPath);
            
            File f = new File(uploadPath);
            if(f.exists() && !f.isDirectory()) { 
                // do something
            System.out.println("in if");
            }else{
            new File(uploadPath);
            System.out.println("in else");
            }
            
            ftpUrl = String.format(ftpUrl, URLEncoder.encode(user), URLEncoder.encode(pass), host, uploadPath);
            System.out.println("Upload URL: " + ftpUrl);
     
            try {
                URL url = new URL(ftpUrl);
                URLConnection conn = url.openConnection();
                OutputStream outputStream = conn.getOutputStream();
                FileInputStream inputStream = new FileInputStream(filePath);
     
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
     
                
          /*      ProcessBuilder builder = new ProcessBuilder(
                        "cmd.exe", "/c", "cd \"D:\\Amalesh-Folder\" && dir");
                    builder.redirectErrorStream(true);
                    Process p = builder.start();
                    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line;
                    while (true) {
                        line = r.readLine();
                        if (line == null) { break; }
                        System.out.println(line);
                    }*/
                    
                inputStream.close();
                outputStream.close();
     
                System.out.println("File uploaded");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
		}       
		System.out.println("Insert Fresh Candidate Status into the database----->"+insertStatus);
		ArrayList FreshCandidateList = new ArrayList();
		FreshCandidateList = (ArrayList)db.getAllFreshCandidateListByCompany(candidateCompanyUniqueId);

        request.setAttribute("FreshCandidateList",null);
        request.setAttribute("FreshCandidateList",FreshCandidateList);
		return new ModelAndView("requirements/ListFreshCandidate");
	}
	
	@RequestMapping("/ListFreshCandidates.html")
	public ModelAndView ListFreshCandidate(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		ArrayList FreshCandidateList = new ArrayList();
		String userId = null;
		userId = (String) session.getAttribute("jobPostUserId");
		String jobPostCompanyName = (String)session.getAttribute("jobPostCompanyName");
		String candidateCompanyUniqueId = (String) session .getAttribute("uniqueJobPostCompanyId");
		
        FreshCandidateList = (ArrayList)db.getAllFreshCandidateListByCompany(candidateCompanyUniqueId);

        request.setAttribute("FreshCandidateList",null);
        request.setAttribute("FreshCandidateList",FreshCandidateList);
        
		return new ModelAndView("requirements/ListFreshCandidate");
	}
	
	@RequestMapping("/ViewFreshCandidateByUniqueId.html")
	public ModelAndView ViewFreshCandidateByUniqueId(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		ArrayList FreshCandidateDetails = new ArrayList();
		String userId = null;
		userId = (String) session.getAttribute("jobPostUserId");
		String can_uniqueId = request.getParameter("uniqueId");
        System.out.println("Unique Id------->"+can_uniqueId);
		FreshCandidateDetails = (ArrayList)db.getAllFreshCandidateListByUniqueId(can_uniqueId);

        request.setAttribute("FreshCandidateDetails",null);
        request.setAttribute("FreshCandidateDetails",FreshCandidateDetails);
        request.setAttribute("PageStatus","View");
		return new ModelAndView("requirements/ViewFreshCandidateDetails");
	}
	
	@RequestMapping("/DeleteFreshCandidateByUniqueId.html")
	public ModelAndView EditAndDeleteFreshCandidateByUniqurId(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		ArrayList FreshCandidateDetails = new ArrayList();
		String userId = null;
		ArrayList FreshCandidateList = new ArrayList();
		userId = (String) session.getAttribute("jobPostUserId");
		String jobPostCompanyName = (String)session.getAttribute("jobPostCompanyName");
		String candidateCompanyUniqueId = (String) session .getAttribute("uniqueJobPostCompanyId");
       
		FreshCandidateList = (ArrayList)db.getAllFreshCandidateListByCompany(candidateCompanyUniqueId);

        request.setAttribute("FreshCandidateList",null);
        request.setAttribute("FreshCandidateList",FreshCandidateList);
        
		return new ModelAndView("requirements/ListFreshCandidate");
	}
	
	@RequestMapping("/EditFreshCandidateByUniqueId.html")
	public ModelAndView EditFreshCandidateByUniqueId(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		ArrayList FreshCandidateDetails = new ArrayList();
		String userId = null;
		ArrayList FreshCandidateList = new ArrayList();
		userId = (String) session.getAttribute("jobPostUserId");
		String jobPostCompanyName = (String)session.getAttribute("jobPostCompanyName");
		
		String can_uniqueId = request.getParameter("uniqueId");
        
		FreshCandidateDetails = (ArrayList)db.getAllFreshCandidateListByUniqueId(can_uniqueId);

        request.setAttribute("FreshCandidateDetails",null);
        request.setAttribute("FreshCandidateDetails",FreshCandidateDetails);
        request.setAttribute("PageStatus","Edit");
        
		return new ModelAndView("requirements/EditFreshCandidateDetails");
	}
	
	@RequestMapping("/UpdateFreshCandidate.html")
	public ModelAndView UpdateFreshCandidate(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		
		
		config_inputStream = getClass().getClassLoader().getResourceAsStream(config_prop);
		config_property.load(config_inputStream);
		
		String FreshCandiadateName = null;
		String documentStatus = null;
		String MobileNumber = null;
		String FreshCandidateEmail = null;
		String visaType = null;
		String Comments = null;
		String status = null;
		String RecruitedBy = null;
		String candidateCompanyUniqueId = null;	
		String candidateCompany = null;
		String candidateCompanyCategory = null;
		String userId = null;
		String education_certificates =null;
		String passport = null;
		String experience_letters = null;
		String other_achievements =null;
		String can_uniqueId = null;
		String UploadResumeCheck = null;
		String OldCanResumeFile = null;
		
		HttpSession session = request.getSession(true) ;
		
		candidateCompanyUniqueId = (String) session .getAttribute("uniqueJobPostCompanyId");
		candidateCompany = (String) session.getAttribute("jobPostCompanyName");
		candidateCompanyCategory = (String) session.getAttribute("jobPostCompanyCategory");
		userId = (String) session.getAttribute("jobPostUserId");
		
		String fileLocation = null;
		String saveDirectory = config_property.getProperty("config.resumePath");/*save uploaded files to a 'Upload' directory in the web app*/
		
		String resumeName = null;
		
		    if (!(new File(saveDirectory)).exists()) {
		    	(new File(saveDirectory)).mkdir();    // creates the directory if it does not exist        
		    }
			String parmName = "";
			String resp = "";
		    int i = 1;
		    resp += "<br>Here is information about uploaded files.<br>";
		      MultipartParser parser = new MultipartParser(request, 1024 * 1024 * 1024);  /* file limit size of 1GB*/
	            Part _part;
	            int k=0;
	            while ((_part = parser.readNextPart()) != null) {
	            	parmName = _part.getName();
		                if(_part.isParam()){
		                	System.out.println("Inside paramPart");
		                	ParamPart paramPart = (ParamPart)_part;
		                	
		                	if(parmName.equals("freshcandidate_name")){
		                		FreshCandiadateName = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("Passport")){
		                		passport = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("Education Certificates")){
		                		education_certificates = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("Experience Letters")){
		                		experience_letters = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("Other Achievements")){
		                		other_achievements = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("mobile_num")){
		                		MobileNumber = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("freshcandidate_email")){
		                		FreshCandidateEmail = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("visa_type")){
		                		visaType = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("comments")){
		                		Comments = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("status")){
		                		status = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("recruiter")){
		                		RecruitedBy = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("uniqueId")){
		                		can_uniqueId = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                	}
		                	if(parmName.equals("UploadResumeCheck")){
		                		UploadResumeCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                	}
		                	if(parmName.equals("OldCanResume")){
		                		OldCanResumeFile = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                	}
		                	
		                }
		                if(_part.isFile()){
		                	
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();			                    
			                    resumeName = name;
			                    System.out.println("resumeName --> "+resumeName);
					          if (name != null && fPart.getName().equalsIgnoreCase("freshCanResume")) {
					        	  long fileSize = fPart.writeTo(new File(saveDirectory));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = saveDirectory+name;
					          }
					          if((UploadResumeCheck == null || UploadResumeCheck == "" || UploadResumeCheck.equalsIgnoreCase("")) &&
					        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
		                    		(fPart.getName().equalsIgnoreCase("freshCanResume"))){
			                      //resp = "<br>The user did not upload a file for this part.";
					        	  System.out.println("oldAccess_file-------------in-----if Access_fileCheck--Stage1 action--->"+OldCanResumeFile);
					        	  fileLocation = OldCanResumeFile;
					          }
					          else {
			                      resp = "<br>The user did not upload a file for this part.";
			                  }
		                }
	            }
	            
	           
		 
		 documentStatus = passport+","+education_certificates+","+experience_letters+","+other_achievements;
		
		boolean updateStatus =db.updateFreshCandidate(FreshCandiadateName,documentStatus,visaType,FreshCandidateEmail,
					 Comments,status,MobileNumber,RecruitedBy,userId,can_uniqueId,fileLocation) ;
		
		if(updateStatus && resumeName!=null){
    		
    		//code starts to store the resume in database server
    		
    		int BUFFER_SIZE = 4096;
    		 
            String ftpUrl = config_property.getProperty("config.ftpURL");
            String host = config_property.getProperty("config.dbhost");
            String user = config_property.getProperty("config.dbuserName");
            String pass = config_property.getProperty("config.dbpassword");
            
            System.out.println("ftpUrl --> "+ftpUrl+" host--> "+host);
            System.out.println("user --> "+user+" pass--> "+pass);
            
            String filePath = fileLocation;
            String uploadPath = config_property.getProperty("config.resumePathSolr")+resumeName;
     
            System.out.println("uploadPath --> "+uploadPath);
            
            File f = new File(uploadPath);
            if(f.exists() && !f.isDirectory()) { 
                // do something
            System.out.println("in if");
            }else{
            new File(uploadPath);
            System.out.println("in else");
            }
            
            ftpUrl = String.format(ftpUrl, URLEncoder.encode(user), URLEncoder.encode(pass), host, uploadPath);
            System.out.println("Upload URL: " + ftpUrl);
     
            try {
                URL url = new URL(ftpUrl);
                URLConnection conn = url.openConnection();
                OutputStream outputStream = conn.getOutputStream();
                FileInputStream inputStream = new FileInputStream(filePath);
     
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
     
                
          /*      ProcessBuilder builder = new ProcessBuilder(
                        "cmd.exe", "/c", "cd \"D:\\Amalesh-Folder\" && dir");
                    builder.redirectErrorStream(true);
                    Process p = builder.start();
                    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line;
                    while (true) {
                        line = r.readLine();
                        if (line == null) { break; }
                        System.out.println(line);
                    }*/
                    
                inputStream.close();
                outputStream.close();
     
                System.out.println("File uploaded");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
		}   
		System.out.println("Insert Fresh Candidate Status into the database----->"+updateStatus);
		ArrayList FreshCandidateList = new ArrayList();
		
		
		FreshCandidateList = (ArrayList)db.getAllFreshCandidateListByCompany(candidateCompanyUniqueId);

        request.setAttribute("FreshCandidateList",null);
        request.setAttribute("FreshCandidateList",FreshCandidateList);
		return new ModelAndView("requirements/ListFreshCandidate");
	}
	
	@RequestMapping("/DeleteFreshCandidate.html")
	public ModelAndView DeleteFreshCandidate(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		
		
		Debug.print("FreshCandidateAction.DeleteFreshCandidate()");
		String uniqueId = request.getParameter("uniqueId");
        String chkRoleIdArr[] = request.getParameterValues("chk");
        String candidateCompanyUniqueId = (String) session .getAttribute("uniqueJobPostCompanyId");
        
        for(int i=0;i<chkRoleIdArr.length;i++)
        Debug.print("FreshCandidateAction.DeleteFreshCandidate() checked records: "+chkRoleIdArr[i]);

        boolean result = false;
        Debug.print("FreshCandidateAction.DeleteFreshCandidate() values:" + uniqueId);

        if(chkRoleIdArr!=null){
            //result = roleRemote.deleteRole(roleId);
            result = db.deleteFreshCandidate(chkRoleIdArr);
            Debug.print("FreshCandidateAction.DeleteFreshCandidate() result:" + result);
            if(result==true){
                Debug.print("FreshCandidateAction.DeleteFreshCandidate() result:" + result);
                if(chkRoleIdArr!=null){
                String userId = null;
        		userId = (String) session.getAttribute("jobPostUserId");
                ArrayList FreshCandidateList = new ArrayList();
                FreshCandidateList = (ArrayList)db.getAllFreshCandidateListByCompany(candidateCompanyUniqueId);

                request.setAttribute("FreshCandidateList",null);
                request.setAttribute("FreshCandidateList",FreshCandidateList);
              
        		return new ModelAndView("requirements/ListFreshCandidate");
                }
            }
            else{
                Debug.print("HotListCandidateAction.DeleteHotListCandidate() result:" + result);
              
                if(chkRoleIdArr!=null){
                	String userId = null;
            		userId = (String) session.getAttribute("jobPostUserId");
                    ArrayList FreshCandidateList = new ArrayList();
                    FreshCandidateList = (ArrayList)db.getAllFreshCandidateListByCompany(candidateCompanyUniqueId);

                    request.setAttribute("FreshCandidateList",null);
                    request.setAttribute("FreshCandidateList",FreshCandidateList);
                   
                }

                return new ModelAndView("requirements/ListFreshCandidate");
            }
        }
        	return new ModelAndView("requirements/ListFreshCandidate");
	}
}

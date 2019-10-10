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
package com.hotlist.candidate;

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
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.xmlrpc.XmlRpcException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletContext;
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
public class HotListCandidateAction {
	
	int totalNofPages = 1;

	Logger logger = LoggerFactory.getLogger("HotListCandidateAction.class");
	
	public static final String config_prop = "config.properties";
	public static final String LOG_FILE = "Log4j.properties";
	InputStream is;
	InputStream config_inputStream;
	
	 private InfusionSessionBean obj1;
		{
			try{
				obj1 = new InfusionSessionBean();
			}catch(Exception e){
				 throw new RuntimeException(e);
			}
		}
		
		GeneralDBAction db=new GeneralDBAction();
	
	@RequestMapping("/initCreateHotListCandidate.html")
	public ModelAndView ViewPostRequirement(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		request.setAttribute("createStatus", "init");
		return new ModelAndView("requirements/createHotListCandidate");
	}
	@RequestMapping("/doCreateHotListCandidate.html")
	public ModelAndView InertPostRequirement(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		
		Properties config_property = new Properties();
		config_inputStream = getClass().getClassLoader().getResourceAsStream(config_prop);

		if (config_inputStream != null) {
			config_property.load(config_inputStream);
		} else {
			throw new FileNotFoundException("property file '" + config_prop + "' not found in the classpath");
		}
		
	
		HttpSession session=request.getSession(true);
			MFAppliedCandidateBean objApplyCand = new MFAppliedCandidateBean();
			
			String appliedUserId = null;
			String txtfirstname = null;
			String txtlastname = null;
			String txtemailaddress = null;
			String txtcontactnumber = null;
			String txtcurrentAddress1 = null;
			String drpvisaapproval = null;
			String drpvisatype = null;
			String drpi797available = null;
			String drpI97available = null;
			String txtrate = null;
			String txtmiddlename = null;
			String txtdateofbirth = null;
			String txttotalexperience = null;
			String txtexperienceinUSA = null;
			String drprelocation = null;
			String txtavailability = null;
			String txtpreferredlocation = null;
			String hotlistAvl = null;
			String txtskills = null;
			String txtbesttimefortelephonicinterview = null;
			String drltime = null;
			String drpwillinginperson = null;
			String txtempname = null;
			String txtempmailID = null;
			String txtempcontactnumber = null;
			String txtcontactperson = null;
			String txtempcompany = null;
			String candidateCompanyUniqueId = null;
			String candidateCompany = null;
			String candidateCompanyCategory = null;
			String canMaritalStatus = null;
			
			String uniqueJobPostCompanyId = null;
			uniqueJobPostCompanyId = (String) session.getAttribute("uniqueJobPostCompanyId");
	        ArrayList HotlistCandidateList = new ArrayList();
	        
	        
	        candidateCompanyUniqueId = (String) session.getAttribute("uniqueJobPostCompanyId");
			candidateCompany = (String) session.getAttribute("jobPostCompanyName");
			candidateCompanyCategory = (String) session.getAttribute("jobPostCompanyCategory");
			
			appliedUserId = (String)session.getAttribute("jobPostUserId");			
			
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
			                	
			                	if(parmName.equals("txtfirstname")){
			                		txtfirstname = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("txtlastname")){
			                		txtlastname = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("txtemailaddress")){
			                		txtemailaddress = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("txtcontactnumber")){
			                		txtcontactnumber = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("txtcurrentAddress1")){
			                		txtcurrentAddress1 = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("drpvisaapproval")){
			                		drpvisaapproval = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("drpvisatype")){
			                		drpvisatype = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("drpi797available")){
			                		drpi797available = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("drpI97available")){
			                		drpI97available = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("txtrate")){
			                		txtrate = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("txtmiddlename")){
			                		txtmiddlename = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("txtdateofbirth")){
			                		txtdateofbirth = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("txttotalexperience")){
			                		txttotalexperience = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("txtexperienceinUSA")){
			                		txtexperienceinUSA = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("drprelocation")){
			                		drprelocation = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("txtavailability")){
			                		txtavailability = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("txtpreferredlocation")){
			                		txtpreferredlocation = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("hotlistAvl")){
			                		hotlistAvl = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("txtskills")){
			                		txtskills = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("txtbesttimefortelephonicinterview")){
			                		txtbesttimefortelephonicinterview = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("drltime")){
			                		drltime = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("drpwillinginperson")){
			                		drpwillinginperson = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("txtempname")){
			                		txtempname = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("txtempmailID")){
			                		txtempmailID = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("txtempcontactnumber")){
			                		txtempcontactnumber = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("txtcontactperson")){
			                		txtcontactperson = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("txtempCompany")){
			                		txtempcompany = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("candidateCompanyUniqueId")){
			                		candidateCompanyUniqueId = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("candidateCompany")){
			                		candidateCompany = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("candidateCompanyCategory")){
			                		candidateCompanyCategory = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	/*
			                	if(parmName.equals("canMaritalStatus")){
			                		canMaritalStatus = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	*/
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
		            
		           
		            
		            
		            if(fileLocation != null && txtemailaddress != null){
		            	//objApplyCand.setRID(RID);
		    			objApplyCand.settxtfirstname(txtfirstname);
		    			objApplyCand.settxtlastname(txtlastname);
		    			objApplyCand.settxtemailaddress(txtemailaddress);
		    			objApplyCand.settxtcontactnumber(txtcontactnumber);
		    			objApplyCand.settxtcurrentAddress1(txtcurrentAddress1);
		    			objApplyCand.setdrpvisaapproval(drpvisaapproval);
		    			objApplyCand.setdrpvisatype(drpvisatype);
		    			objApplyCand.setdrpi797available(drpi797available);
		    			objApplyCand.setdrpI97available(drpI97available);
		    			objApplyCand.settxtrate(txtrate);
		    			objApplyCand.settxtpreferredlocation(txtpreferredlocation);
		    			objApplyCand.settxtmiddlename(txtmiddlename);
		    			objApplyCand.settxtdateofbirth(txtdateofbirth);
		    			objApplyCand.settxttotalexperience(txttotalexperience);
		    			objApplyCand.settxtexperienceinUSA(txtexperienceinUSA);
		    			objApplyCand.setdrprelocation(drprelocation);
		    			objApplyCand.settxtavailability(txtavailability); 
		    			//objApplyCand.setdrpbywhom(drpbywhom);
		    			objApplyCand.settxtskills(txtskills);
		    			objApplyCand.settxtbesttimefortelephonicinterview(txtbesttimefortelephonicinterview);
		    			objApplyCand.setdrltime(drltime);
		    			objApplyCand.setdrpwillinginperson(drpwillinginperson);
		    			objApplyCand.settxtempname(txtempname);
		    			objApplyCand.settxtempmailID(txtempmailID);
		    			objApplyCand.settxtempcontactnumber(txtempcontactnumber);
		    			objApplyCand.settxtcontactperson(txtcontactperson);
		    			objApplyCand.setCandidateCompanyUniqueId(candidateCompanyUniqueId);
		    			objApplyCand.setCandidateCompany(candidateCompanyCategory);
		    			objApplyCand.setCandidateCompanyCategory(candidateCompanyCategory);
		    			//objApplyCand.setCandidateMaritalStatus(canMaritalStatus);
		    			objApplyCand.setCandidateResumeLoc(fileLocation);
		    			
		    			boolean insertStatus = false;
		    			insertStatus = db.insertHotListCandidate(appliedUserId,txtfirstname,txtlastname,txtemailaddress,
		    					txtcontactnumber,txtcurrentAddress1,drpvisaapproval,drpvisatype,drpi797available,
		    					drpI97available,txtrate,txtmiddlename,txtdateofbirth,txttotalexperience,txtexperienceinUSA,
		    					drprelocation,txtavailability,txtpreferredlocation,hotlistAvl,txtskills,txtbesttimefortelephonicinterview,
		    					drltime,drpwillinginperson,txtempname,txtempmailID,txtempcontactnumber,txtcontactperson,"",
		    					candidateCompanyUniqueId,candidateCompany,candidateCompanyCategory,fileLocation,txtempcompany);
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
		                    
		                    //code for storing resume to database server ends here
		            			request.setAttribute("appliedUserId",appliedUserId);
		            			request.setAttribute("txtfirstname",txtfirstname);
		            			request.setAttribute("txtlastname",txtlastname);
		            			request.setAttribute("txtemailaddress",txtemailaddress);
		            			request.setAttribute("txtcontactnumber",txtcontactnumber);
		            			request.setAttribute("txtcurrentAddress1",txtcurrentAddress1);
		            			request.setAttribute("drpvisaapproval",drpvisaapproval);
		            			request.setAttribute("drpvisatype",drpvisatype);
		            			request.setAttribute("drpi797available",drpi797available);
		            			request.setAttribute("drpI97available",drpI97available);
		            			request.setAttribute("txtrate",txtrate);
		            			//request.setAttribute("RID", RID);
		            			
		            			request.setAttribute("txtmiddlename",txtmiddlename);
		            			request.setAttribute("txtdateofbirth",txtdateofbirth);
		            			request.setAttribute("txttotalexperience",txttotalexperience);
		            			request.setAttribute("txtexperienceinUSA",txtexperienceinUSA);
		            			request.setAttribute("drprelocation",drprelocation);
		            			request.setAttribute("txtavailability",txtavailability);
		            			request.setAttribute("txtpreferredlocation",txtpreferredlocation);
		            			request.setAttribute("hotlistAvl",hotlistAvl);
		            			request.setAttribute("txtskills",txtskills);
		            			request.setAttribute("txtbesttimefortelephonicinterview",txtbesttimefortelephonicinterview);
		            			request.setAttribute("drltime",drltime);
		            			request.setAttribute("drpwillinginperson",drpwillinginperson);
		            			request.setAttribute("txtempname",txtempname);
		            			request.setAttribute("txtempmailID",txtempmailID);
		            			request.setAttribute("txtempcontactnumber",txtempcontactnumber);
		            			request.setAttribute("txtcontactperson",txtcontactperson);
		            			request.setAttribute("txtempcompany",txtempcompany);
		            			request.setAttribute("createStatus", "success");
		            			
		            			HotlistCandidateList = (ArrayList)db.getAllHotlistCandidateListByuniqueJobPostCompanyId(uniqueJobPostCompanyId);
		            	        request.setAttribute("HotlistCandidateList",null);
		            	        request.setAttribute("HotlistCandidateList",HotlistCandidateList);
		            	        request.setAttribute("pageStatus","HotlistCandidateList");
		            			return new ModelAndView("requirements/listHotlistCandidateList"); 
		            			}else{
		            				//request.setAttribute("RID", RID);
		            				request.setAttribute("createStatus", "fail");
		            				
		            				HotlistCandidateList = (ArrayList)db.getAllHotlistCandidateListByuniqueJobPostCompanyId(uniqueJobPostCompanyId);
		            				request.setAttribute("HotlistCandidateList",null);
			            	        request.setAttribute("HotlistCandidateList",HotlistCandidateList);
			            	        request.setAttribute("pageStatus","HotlistCandidateList");
		            				return new ModelAndView("requirements/listHotlistCandidateList"); 
		            			}
			
		            }else{
		            	request.setAttribute("createStatus", "fail");
		            	request.setAttribute("HotlistCandidateList",null);
		            	
		            	HotlistCandidateList = (ArrayList)db.getAllHotlistCandidateListByuniqueJobPostCompanyId(uniqueJobPostCompanyId);
            	        request.setAttribute("HotlistCandidateList",HotlistCandidateList);
            	        request.setAttribute("pageStatus","HotlistCandidateList");
        				return new ModelAndView("requirements/listHotlistCandidateList"); 
		            }
			
			}
	@RequestMapping("/ListHotlistCandidate.html")
	public ModelAndView ListHotlistCandidate(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("HotListCandidateAction.ListHotlistCandidate()");
		String uniqueJobPostCompanyId = null;
		uniqueJobPostCompanyId = (String) session.getAttribute("uniqueJobPostCompanyId");
        ArrayList HotlistCandidateList = new ArrayList();
        HotlistCandidateList = (ArrayList)db.getAllHotlistCandidateListByuniqueJobPostCompanyId(uniqueJobPostCompanyId);

        request.setAttribute("HotlistCandidateList",null);
        request.setAttribute("HotlistCandidateList",HotlistCandidateList);
        request.setAttribute("pageStatus","HotlistCandidateList");
        request.setAttribute("createStatus", "init");
		return new ModelAndView("requirements/listHotlistCandidateList");
	}
	
	@RequestMapping("/ViewListHotlistCandidateByUniqueId.html")
	public ModelAndView ViewListHotlistCandidateByUniqueId(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("HotListCandidateAction.ViewListHotlistCandidateByUniqueId()");
		String HotlistCandidateByUniqueId = null;
		HotlistCandidateByUniqueId = request.getParameter("uniqueId");
        ArrayList HotlistCanDetailsList = new ArrayList();
        HotlistCanDetailsList = (ArrayList)db.getHotlistCanDetailsByUniqueId(HotlistCandidateByUniqueId);

        request.setAttribute("HotlistCanDetailsList",null);
        request.setAttribute("HotlistCanDetailsList",HotlistCanDetailsList);
        request.setAttribute("pageStatus","read");
		return new ModelAndView("requirements/ViewHotlistCandidateList");
	}
	
	@RequestMapping("/SearchHotCandidateStatus.html")
	public ModelAndView SearchHotCandidateStatus(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("HotListCandidateAction.SearchHotCandidateStatus()");
		String minExp = null;
		String maxExp = null;
		String visaType = null;
		String fromRate = null;
		String toRate = null;
		
		minExp = request.getParameter("minExp");
		maxExp = request.getParameter("maxExp");
		visaType = request.getParameter("visaType");
		fromRate = request.getParameter("fromRate");
		toRate = request.getParameter("toRate");
		
        ArrayList HotlistCanDetailsList = new ArrayList();
        HotlistCanDetailsList = (ArrayList)db.SearchHotlistCanDetailsByParameters(minExp,maxExp,visaType,fromRate,toRate);

        request.setAttribute("HotlistCandidateList",null);
        request.setAttribute("HotlistCandidateList",HotlistCanDetailsList);
        request.setAttribute("pageStatus","read");
		return new ModelAndView("requirements/listHotlistCandidateList");
	}
	
	@RequestMapping("/HotlistCanResumeDownload.html")
	public ModelAndView HotlistCanResumeDownloadByUniqueId(HttpServletRequest request,
			HttpServletResponse response) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, IOException{
		
		Properties config_property = new Properties();
		config_inputStream = getClass().getClassLoader().getResourceAsStream(config_prop);

		if (config_inputStream != null) {
			config_property.load(config_inputStream);
		} else {
			throw new FileNotFoundException("property file '" + config_prop + "' not found in the classpath");
		}
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("HotListCandidateAction.HotlistCanResumeDownloadByUniqueId()");
		String CanDetailsByUniqueId = null;
		CanDetailsByUniqueId = request.getParameter("uniqueId");
		String resumePath = null;
        ArrayList AppliedCanDetailsList = new ArrayList();
        AppliedCanDetailsList = (ArrayList)db.getHotlistCanDetailsByUniqueId(CanDetailsByUniqueId);
        if(AppliedCanDetailsList!=null && AppliedCanDetailsList.size()!=0){
            Iterator itr = AppliedCanDetailsList.iterator();
            while (itr.hasNext()) {    
            	String TempList[] = (String[])itr.next();            	
            	resumePath = TempList[29];
            }
        }
        
		System.out.println("resumePath "+resumePath);
		
		final int BUFFER_SIZE = 4096;
		   String filePath =resumePath;
		    
			  // get absolute path of the application
			     ServletContext context = request.getSession().getServletContext();
			       // String appPath = context.getRealPath("");
			     			 
			        // construct the complete absolute path of the file
			        String fullPath =filePath;      
			        File downloadFile = new File(fullPath);
			        FileInputStream inputStream = new FileInputStream(downloadFile);
			         
			        // get MIME type of the file
			        String mimeType = context.getMimeType(fullPath);
			        if (mimeType == null) {
			            // set to binary type if MIME mapping not found
			            mimeType = "application/octet-stream";
			        }
			        			 
			        // set content attributes for the response
			        response.setContentType(mimeType);
			        response.setContentLength((int) downloadFile.length());
			 
			        // set headers for the response
			        String headerKey = "Content-Disposition";
			        String headerValue = String.format("attachment; filename=\"%s\"",
			                downloadFile.getName());
			        response.setHeader(headerKey, headerValue);
			 
			        // get output stream of the response
					
			        OutputStream outStream = response.getOutputStream();
			 
			        byte[] buffer = new byte[BUFFER_SIZE];
			        int bytesRead = -1;
			 
			        // write bytes read from the input stream into the output stream
			        while ((bytesRead = inputStream.read(buffer)) != -1) {
			            outStream.write(buffer, 0, bytesRead);
			        }
			 
			        outStream.flush();
			        inputStream.close();
			        outStream.close();
			    
			 
			 return null;//new ModelAndView("approvenroll");	 
	}
	
	@RequestMapping("/DeleteHotListCandidate.html")
	public ModelAndView DeleteHotListCandidate(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("HotListCandidateAction.DeleteHotListCandidate()");
		String uniqueId = request.getParameter("uniqueId");
        String chkRoleIdArr[] = request.getParameterValues("chk");

        for(int i=0;i<chkRoleIdArr.length;i++)
        Debug.print("HotListCandidateAction.DeleteHotListCandidate() checked records: "+chkRoleIdArr[i]);

        boolean result = false;
        Debug.print("HotListCandidateAction.DeleteHotListCandidate() values:" + uniqueId);

        if(chkRoleIdArr!=null){
            //result = roleRemote.deleteRole(roleId);
            result = db.deleteHotListCandidate(chkRoleIdArr);
            Debug.print("HotListCandidateAction.DeleteHotListCandidate() result:" + result);
            if(result==true){
                Debug.print("HotListCandidateAction.DeleteHotListCandidate() result:" + result);
                if(chkRoleIdArr!=null){
                String uniqueJobPostCompanyId = null;
                uniqueJobPostCompanyId = (String) session.getAttribute("uniqueJobPostCompanyId");
                ArrayList HotlistCandidateList = new ArrayList();
                HotlistCandidateList = (ArrayList)db.getAllHotlistCandidateListByuniqueJobPostCompanyId(uniqueJobPostCompanyId);

                request.setAttribute("HotlistCandidateList",null);
                request.setAttribute("HotlistCandidateList",HotlistCandidateList);
                request.setAttribute("pageStatus","HotlistCandidateList");
        		return new ModelAndView("requirements/listHotlistCandidateList");
                }
            }
            else{
                Debug.print("HotListCandidateAction.DeleteHotListCandidate() result:" + result);
              
                if(chkRoleIdArr!=null){
                	String uniqueJobPostCompanyId = null;
                	uniqueJobPostCompanyId = (String) session.getAttribute("uniqueJobPostCompanyId");
                    ArrayList HotlistCandidateList = new ArrayList();
                    HotlistCandidateList = (ArrayList)db.getAllHotlistCandidateListByuniqueJobPostCompanyId(uniqueJobPostCompanyId);

                    request.setAttribute("HotlistCandidateList",null);
                    request.setAttribute("HotlistCandidateList",HotlistCandidateList);
                    request.setAttribute("pageStatus","HotlistCandidateList");
                }

                return new ModelAndView("requirements/listHotlistCandidateList");
            }
        }
        	return new ModelAndView("requirements/listHotlistCandidateList");
	}
	
	@RequestMapping("/editHotListCandidate.html")
	public ModelAndView editHotListCandidate(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("HotListCandidateAction.editHotListCandidate()");
		String HotlistCandidateByUniqueId = null;
		HotlistCandidateByUniqueId = request.getParameter("uniqueId");
        if(HotlistCandidateByUniqueId!=null){
    		
            ArrayList HotlistCanDetailsList = new ArrayList();
            HotlistCanDetailsList = (ArrayList)db.getHotlistCanDetailsByUniqueId(HotlistCandidateByUniqueId);

            request.setAttribute("HotlistCanDetailsList",null);
            request.setAttribute("HotlistCanDetailsList",HotlistCanDetailsList);
            request.setAttribute("pageStatus","read");
            request.setAttribute("editStatus", "init");
    		return new ModelAndView("requirements/frmEditHotlistCandidateList");
        }
        
        Debug.print("HotListCandidateAction.editHotListCandidate() sucessfully comes from servlet.");
		return new ModelAndView("requirements/frmEditHotlistCandidateList");
        
	}
	
	@RequestMapping("/doEditHotListCandidate.html")
	public ModelAndView doEditHotListCandidate(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		
		Properties config_property = new Properties();
		config_inputStream = getClass().getClassLoader().getResourceAsStream(config_prop);

		if (config_inputStream != null) {
			config_property.load(config_inputStream);
		} else {
			throw new FileNotFoundException("property file '" + config_prop + "' not found in the classpath");
		}
		
	
		HttpSession session=request.getSession(true);
			MFAppliedCandidateBean objApplyCand = new MFAppliedCandidateBean();
			
			String appliedUserId = null;
			String txtfirstname = null;
			String txtlastname = null;
			String txtemailaddress = null;
			String txtcontactnumber = null;
			String txtcurrentAddress1 = null;
			String drpvisaapproval = null;
			String drpvisatype = null;
			String drpi797available = null;
			String drpI97available = null;
			String txtrate = null;
			String txtmiddlename = null;
			String txtdateofbirth = null;
			String txttotalexperience = null;
			String txtexperienceinUSA = null;
			String drprelocation = null;
			String txtavailability = null;
			String txtpreferredlocation = null;
			String hotlistAvl = null;
			String txtskills = null;
			String txtbesttimefortelephonicinterview = null;
			String drltime = null;
			String drpwillinginperson = null;
			String txtempname = null;
			String txtempmailID = null;
			String txtempcontactnumber = null;
			String txtcontactperson = null;
			String candidateCompanyUniqueId = null;
			String candidateCompany = null;
			String candidateCompanyCategory = null;
			String employerCompany = null;
			String uniqueId = null;
			
			
	        candidateCompanyUniqueId = (String) session.getAttribute("uniqueJobPostCompanyId");
			candidateCompany = (String) session.getAttribute("jobPostCompanyName");
			candidateCompanyCategory = (String) session.getAttribute("jobPostCompanyCategory");
			
			appliedUserId = (String)session.getAttribute("jobPostUserId");	
			
			String uploadNewResumeCheck = null;
			String oldResumeUpload = null;
			
			String uniqueJobPostCompanyId = null;
			uniqueJobPostCompanyId = (String) session.getAttribute("uniqueJobPostCompanyId");
	        ArrayList HotlistCandidateList = new ArrayList();
			
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
			                	
			                	if(parmName.equals("uniqueId")){
			                		uniqueId = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("firstName")){
			                		txtfirstname = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("lastName")){
			                		txtlastname = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("CandidateEMail")){
			                		txtemailaddress = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("CandidateContactNumber")){
			                		txtcontactnumber = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("CurrentLocation")){
			                		txtcurrentAddress1 = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("VisaApprovalValue")){
			                		drpvisaapproval = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("VisaTypeValue")){
			                		drpvisatype = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("FormI797AvailableValue")){
			                		drpi797available = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("FormI94AvailableValue")){
			                		drpI97available = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("RateInDollar")){
			                		txtrate = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("MiddleName")){
			                		txtmiddlename = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("DateOfBirth")){
			                		txtdateofbirth = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("TotalExperience")){
			                		txttotalexperience = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("ExperienceInUSA")){
			                		txtexperienceinUSA = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("ReLocationValue")){
			                		drprelocation = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("Availability")){
			                		txtavailability = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("PreferredLocation")){
			                		txtpreferredlocation = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("hotlistAvl")){
			                		hotlistAvl = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("Skills")){
			                		txtskills = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("BestDateForTelephonicInterview")){
			                		txtbesttimefortelephonicinterview = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("BestTimeForTelephonicInterview")){
			                		drltime = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("WillingnessForAnInPersonInterviewAtOwnExpenseValue")){
			                		drpwillinginperson = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("EmployerName")){
			                		txtempname = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("EmployerMailID")){
			                		txtempmailID = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("EmployerContactNumber")){
			                		txtempcontactnumber = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("EmployerCompany")){
			                		employerCompany = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("ContactPerson")){
			                		txtcontactperson = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("candidateCompanyUniqueId")){
			                		candidateCompanyUniqueId = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("candidateCompany")){
			                		candidateCompany = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	if(parmName.equals("candidateCompanyCategory")){
			                		candidateCompanyCategory = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	/*
			                	if(parmName.equals("canMaritalStatus")){
			                		canMaritalStatus = paramPart.getStringValue();
			                		System.out.println("Inside param :::: "+paramPart.getStringValue());
			                		
			                	}
			                	*/
			                	if(parmName.equals("uploadNewResumeCheck")){
			                		uploadNewResumeCheck = paramPart.getStringValue();
			                		System.out.println("uploadfederalnewCheck----- :::: "+paramPart.getStringValue());
			                	}
			                	if(parmName.equals("oldResumeUpload")){
			                		oldResumeUpload = paramPart.getStringValue();
			                		System.out.println("oldfileupload----- :::: "+paramPart.getStringValue());
			                	}
			                }
			                if(_part.isFile()){
			                	
			                	 FilePart fPart = (FilePart) _part;  // get some info about the file
				                    String name = fPart.getFileName();			                    
				                    resumeName = name;
				                    System.out.println("resumeName --> "+resumeName);
						          if (name != null && fPart.getName().equalsIgnoreCase("candidateResume")) {
						        	  long fileSize = fPart.writeTo(new File(saveDirectory));
						        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
						        	  fileLocation = saveDirectory+name;
						          }else {
				                      resp = "<br>The user did not upload a file for this part.";
				                  }
						          
						          if((uploadNewResumeCheck == null || uploadNewResumeCheck == "" || uploadNewResumeCheck.equalsIgnoreCase("")) &&
				                    		(name == null || name.equalsIgnoreCase("") || name.equalsIgnoreCase("null")) &&
				                    		(fPart.getName().equalsIgnoreCase("candidateResume"))){
					                      //resp = "<br>The user did not upload a file for this part.";
							        	  System.out.println("oldResumeUpload-------------in-----if uploadNewResumeCheck--action--->"+oldResumeUpload);
							        	  //System.out.println("oldAdditionalfile-------------in-----else--Stage1 action--->"+oldAdditionalfile);
							        	  //fedStateFileList.add(oldfileupload);
							        	  fileLocation = oldResumeUpload;
					             }
			                }
		            }
		            
		           
		            
		            
		            if(fileLocation != null && txtemailaddress != null){
		            	//objApplyCand.setRID(RID);
		    			objApplyCand.settxtfirstname(txtfirstname);
		    			objApplyCand.settxtlastname(txtlastname);
		    			objApplyCand.settxtemailaddress(txtemailaddress);
		    			objApplyCand.settxtcontactnumber(txtcontactnumber);
		    			objApplyCand.settxtcurrentAddress1(txtcurrentAddress1);
		    			objApplyCand.setdrpvisaapproval(drpvisaapproval);
		    			objApplyCand.setdrpvisatype(drpvisatype);
		    			objApplyCand.setdrpi797available(drpi797available);
		    			objApplyCand.setdrpI97available(drpI97available);
		    			objApplyCand.settxtrate(txtrate);
		    			objApplyCand.settxtpreferredlocation(txtpreferredlocation);
		    			objApplyCand.settxtmiddlename(txtmiddlename);
		    			objApplyCand.settxtdateofbirth(txtdateofbirth);
		    			objApplyCand.settxttotalexperience(txttotalexperience);
		    			objApplyCand.settxtexperienceinUSA(txtexperienceinUSA);
		    			objApplyCand.setdrprelocation(drprelocation);
		    			objApplyCand.settxtavailability(txtavailability); 
		    			//objApplyCand.setdrpbywhom(drpbywhom);
		    			objApplyCand.settxtskills(txtskills);
		    			objApplyCand.settxtbesttimefortelephonicinterview(txtbesttimefortelephonicinterview);
		    			objApplyCand.setdrltime(drltime);
		    			objApplyCand.setdrpwillinginperson(drpwillinginperson);
		    			objApplyCand.settxtempname(txtempname);
		    			objApplyCand.settxtempmailID(txtempmailID);
		    			objApplyCand.settxtempcontactnumber(txtempcontactnumber);
		    			objApplyCand.settxtcontactperson(txtcontactperson);
		    			objApplyCand.setCandidateCompanyUniqueId(candidateCompanyUniqueId);
		    			objApplyCand.setCandidateCompany(candidateCompanyCategory);
		    			objApplyCand.setCandidateCompanyCategory(candidateCompanyCategory);
		    			//objApplyCand.setCandidateMaritalStatus(canMaritalStatus);
		    			objApplyCand.setCandidateResumeLoc(fileLocation);
		    			
		    			boolean insertStatus = false;
		    			insertStatus = db.updateHotListCandidate(appliedUserId,txtfirstname,txtlastname,txtemailaddress,
		    					txtcontactnumber,txtcurrentAddress1,drpvisaapproval,drpvisatype,drpi797available,
		    					drpI97available,txtrate,txtmiddlename,txtdateofbirth,txttotalexperience,txtexperienceinUSA,
		    					drprelocation,txtavailability,txtpreferredlocation,hotlistAvl,txtskills,txtbesttimefortelephonicinterview,
		    					drltime,drpwillinginperson,txtempname,txtempmailID,txtempcontactnumber,txtcontactperson,"",
		    					candidateCompanyUniqueId,candidateCompany,candidateCompanyCategory,fileLocation,uniqueId,employerCompany);
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
		                    
		            			request.setAttribute("editStatus", "success");
		            			
		            	        HotlistCandidateList = (ArrayList)db.getAllHotlistCandidateListByuniqueJobPostCompanyId(uniqueJobPostCompanyId);
		            			request.setAttribute("HotlistCandidateList",null);
		            	        request.setAttribute("HotlistCandidateList",HotlistCandidateList);
		            	        request.setAttribute("pageStatus","HotlistCandidateList");
		            			return new ModelAndView("requirements/listHotlistCandidateList"); 
		            			}else{
		            				
		            				request.setAttribute("editStatus", "fail");
		            				
		            		        HotlistCandidateList = (ArrayList)db.getAllHotlistCandidateListByuniqueJobPostCompanyId(uniqueJobPostCompanyId);
		            				request.setAttribute("HotlistCandidateList",null);
		            		        request.setAttribute("HotlistCandidateList",HotlistCandidateList);
		            		        request.setAttribute("pageStatus","HotlistCandidateList");
		            				return new ModelAndView("requirements/listHotlistCandidateList"); 
		            			}
			
		            }else{
		            	
		            	request.setAttribute("editStatus", "fail");
		            	
		    	        HotlistCandidateList = (ArrayList)db.getAllHotlistCandidateListByuniqueJobPostCompanyId(uniqueJobPostCompanyId);
		            	request.setAttribute("HotlistCandidateList",null);
		    	        request.setAttribute("HotlistCandidateList",HotlistCandidateList);
		    	        request.setAttribute("pageStatus","HotlistCandidateList");
        				return new ModelAndView("requirements/listHotlistCandidateList"); 
		            }
			
			}
	
	
}


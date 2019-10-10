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
package com.jobvacancy.apply;
import java.util.Base64;  

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.axis2.AxisFault;
import org.apache.xmlrpc.XmlRpcException;
import org.jasypt.util.text.BasicTextEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.db.GeneralDBAction;
import com.db.exceptionhandling.CustomGenericException;
import com.dberp.employee.session.DBERPEmployeeSessionBean;
import com.dberp.util.Employee;
import com.hlccommon.util.Debug;
import com.hlcform.util.HLCContactDetails;
import com.hlcform.util.HLCMemberUpdateDAO;
import com.hlcform.util.HLCUserMaster;
import com.infusionejb.session.InfusionSessionBean;
import com.jobvacancy.requirements.RequirementsAction;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;
import com.user.action.Crypt;
import com.user.action.SharePointEncrypt;
import com.util.email.EmailContent;
import com.util.email.MailMail;
import com.user.action.OfbizWebservices;
import com.jobvacancy.requirements.*;

@SuppressWarnings("unused")
@Controller
public class SPApplyJobRequirement {
	
	int totalNofPages = 1;
	
	RequirementsAction ReqAction = new RequirementsAction();
	
Logger logger = LoggerFactory.getLogger("SPApplyJobRequirement.class");
GeneralDBAction db = new GeneralDBAction();
	
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
		
			RequirementsAction obj = new RequirementsAction();
	@RequestMapping("/ApplyJobVacancy.html")
	public ModelAndView ApplyJobVacancy(HttpServletRequest request,
			HttpServletResponse response) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException{
		
		HttpSession session=request.getSession(true); 
		Crypt ccc = new Crypt();
		
		String spReqId = null;
		String RID = null;
		spReqId = request.getParameter("spReqId");
		RID = request.getParameter("RID");
		System.out.println("spReqId================="+spReqId);
		System.out.println("RID=========in ApplyJobVacancy.html========"+RID);
		
		/*-------------------------------------Role Name Encrypted start here-----------------------------------------*/
		SharePointEncrypt encp = new SharePointEncrypt();
		BasicTextEncryptor textEncryptor1 = new BasicTextEncryptor();
        textEncryptor1.setPassword("sa");
//        String encryptedroleName = textEncryptor1.encrypt(roleName);
        String sproleName = (String) session.getAttribute("sprolename");
        String sploginname = (String) session.getAttribute("sploginname");
        String sppass = (String) session.getAttribute("sppass");
        String defaultRoleName = "loginAuth";
        String sppassRole = sppass+"-sep-"+defaultRoleName;
        String sploginRole = sploginname+"-sep-"+sproleName;
        System.out.println("userNameWithRole::::::::::::::::::::::::::"+sploginRole);
//        String encryptedUserNameWithRole = encp.encrypt(userNameWithRole);
        String loginRoleAuth = ccc.encrypt(sploginRole,"digiblitz");
        System.out.println("loginRoleAuth::::::::::::::::::::::::"+loginRoleAuth);
        session.setAttribute("loginRoleAuth",loginRoleAuth);
        String passRoleAuth = ccc.encrypt(sppassRole,"digiblitz");
        System.out.println("passRoleAuth::::::::::::::::::::::::"+passRoleAuth);
        session.setAttribute("passRoleAuth",passRoleAuth);
        String result = "UN="+loginRoleAuth+"&PWD="+passRoleAuth+"&RID="+RID;
        response.sendRedirect("https://www.digiblitzonline.com/sp-portal/dbconsult/_layouts/15/dBVMSLogin/VMSLoginGeneral.aspx?"+result);
//        Cookie cookieRoleName = new Cookie("cookieRoleName",defaultRoleName);
//        cookieRoleName.setMaxAge(60*60*24);
//        response.addCookie(cookieRoleName);
/*-------------------------------------Role Name Encrypted end here-----------------------------------------*/
        
		return null;
	}
	
	@RequestMapping("/ViewApplyJobVacancy.html")
	public ModelAndView ViewApplyJobVacancy(HttpServletRequest request,
			HttpServletResponse response) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, IOException{
		
		HttpSession session=request.getSession(true);
		HLCContactDetails objContact = new HLCContactDetails();
	    HLCUserMaster objUserMaster = new HLCUserMaster();
	    HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
		
		String RID = null;
		String userId = null;
		String email =null;
		userId = (String) session.getAttribute("jobPostUserId");
		try {
			objUserMaster = db.getUserMasterDetailsByUserId(userId);
			objContact = objDAO.displayContactDetails(userId);
            Debug.print(" Contact Details  : \n"+objContact);
            Debug.print(" User Details  : \n"+objUserMaster);
        }catch (SQLException e){
            e.printStackTrace();
            throw new CustomGenericException(e.getErrorCode(),e.getMessage().toString());
        }
		RID = request.getParameter("RID");
		
		email = request.getParameter("User");
		
		
		System.out.println("email  >>> "+email);
		
		request.setAttribute("RID", RID);
		request.setAttribute("email", email);
		request.setAttribute("applyStatus", "init");
		request.setAttribute("objUserMaster", objUserMaster);
		request.setAttribute("objContact", objContact);
		System.out.println("RID===========in ViewApplyJobVacancy.html======"+RID);
		//System.out.println("RID===========in ViewApplyJobVacancy.html======"+RID);
		return new ModelAndView("requirements/ApplyJobVacancy"); 
	}
	
	@RequestMapping("/GetApplyJobVacancy.html")
	public ModelAndView GetApplyJobVacancy(HttpServletRequest request,
			HttpServletResponse response) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, IOException{
		
		HttpSession session=request.getSession(true);
		MFAppliedCandidateBean objApplyCand = new MFAppliedCandidateBean();
		String RID = null;
		String email = null;
		
String recEmail = request.getParameter("email");
		
		
		System.out.println("email  1>>> "+recEmail);
		RID = request.getParameter("RID");
		System.out.println("RID===========in GetApplyJobVacancy.html======"+RID);
		
		String appliedUserId = null;
		appliedUserId = request.getParameter("appliedUserId");
		String txtfirstname = request.getParameter("txtfirstname");
		String txtlastname = request.getParameter("txtlastname");
		String txtemailaddress = request.getParameter("txtemailaddress");
		String txtcontactnumber = request.getParameter("txtcontactnumber");
		String txtcurrentAddress1 = request.getParameter("txtcurrentAddress1");
		String drpvisaapproval = request.getParameter("drpvisaapproval");
		String drpvisatype = request.getParameter("drpvisatype");
		String drpi797available = request.getParameter("drpi797available");
		String drpI97available = request.getParameter("drpI97available");
		String txtrate = request.getParameter("txtrate");

		String txtmiddlename = null;
		String txtdateofbirth = null;
		String txttotalexperience = null;
		String txtexperienceinUSA = null;
		String drprelocation = null;
		String txtavailability = null;
		String txtpreferredlocation = null;
		String drpbywhom = null;
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
		String txtempcompany = null;
		
		
        candidateCompanyUniqueId = (String) session.getAttribute("uniqueJobPostCompanyId");
		candidateCompany = (String) session.getAttribute("jobPostCompanyName");
		candidateCompanyCategory = (String) session.getAttribute("jobPostCompanyCategory");
		
		txtmiddlename = request.getParameter("txtmiddlename");
		txtdateofbirth = request.getParameter("txtdateofbirth");
		txttotalexperience = request.getParameter("txttotalexperience");
		txtexperienceinUSA = request.getParameter("txtexperienceinUSA");
		drprelocation = request.getParameter("drprelocation");
		txtavailability = request.getParameter("txtavailability");
		txtpreferredlocation = request.getParameter("txtpreferredlocation");
		drpbywhom = request.getParameter("drpbywhom");
		txtskills = request.getParameter("txtskills");
		txtbesttimefortelephonicinterview = request.getParameter("txtbesttimefortelephonicinterview");
		drltime = request.getParameter("drltime");
		drpwillinginperson = request.getParameter("drpwillinginperson");
		txtempname = request.getParameter("txtempname");
		txtempmailID = request.getParameter("txtempmailID");
		txtempcontactnumber = request.getParameter("txtempcontactnumber");
		txtcontactperson = request.getParameter("txtcontactperson");
		txtempcompany = request.getParameter("txtempcompany");
		
		objApplyCand.setRID(RID);
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
		objApplyCand.setdrpbywhom(drpbywhom);
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
		
		boolean insertStatus = false;
		insertStatus = db.insertReceivedResumeDet(appliedUserId,txtfirstname,txtlastname,txtemailaddress,
				txtcontactnumber,txtcurrentAddress1,drpvisaapproval,drpvisatype,drpi797available,
				drpI97available,txtrate,txtmiddlename,txtdateofbirth,txttotalexperience,txtexperienceinUSA,
				drprelocation,txtavailability,txtpreferredlocation,drpbywhom,txtskills,txtbesttimefortelephonicinterview,
				drltime,drpwillinginperson,txtempname,txtempmailID,txtempcontactnumber,txtcontactperson,RID,
				candidateCompanyUniqueId,candidateCompany,candidateCompanyCategory,txtempcompany);
		
		if(insertStatus == true){
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
		request.setAttribute("RID", RID);
		
		request.setAttribute("txtmiddlename",txtmiddlename);
		request.setAttribute("txtdateofbirth",txtdateofbirth);
		request.setAttribute("txttotalexperience",txttotalexperience);
		request.setAttribute("txtexperienceinUSA",txtexperienceinUSA);
		request.setAttribute("drprelocation",drprelocation);
		request.setAttribute("txtavailability",txtavailability);
		request.setAttribute("txtpreferredlocation",txtpreferredlocation);
		request.setAttribute("drpbywhom",drpbywhom);
		request.setAttribute("txtskills",txtskills);
		request.setAttribute("txtbesttimefortelephonicinterview",txtbesttimefortelephonicinterview);
		request.setAttribute("drltime",drltime);
		request.setAttribute("drpwillinginperson",drpwillinginperson);
		request.setAttribute("txtempname",txtempname);
		request.setAttribute("txtempmailID",txtempmailID);
		request.setAttribute("txtempcontactnumber",txtempcontactnumber);
		request.setAttribute("txtcontactperson",txtcontactperson);
		request.setAttribute("txtempcompany",txtempcompany);
		request.setAttribute("recEmail",recEmail);
		return new ModelAndView("requirements/PostApplyJobToSP"); 
		}else{
			request.setAttribute("RID", RID);
			request.setAttribute("applyStatus", "fail");
			return new ModelAndView("requirements/ApplyJobVacancy"); 
		}
	}
	
	
	@RequestMapping("/getCandidateResume.html")
	public ModelAndView getCandidateResume(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		
		Properties config_property = new Properties();
		config_inputStream = getClass().getClassLoader().getResourceAsStream(config_prop);

		if (config_inputStream != null) {
			config_property.load(config_inputStream);
		} else {
			throw new FileNotFoundException("property file '" + config_prop + "' not found in the classpath");
		}
		
		
		GeneralDBAction db=new GeneralDBAction();
		
		String txtemailaddress = null;
		String txtcontactnumber=null;
		String txtrate=null;
		String txttotalexperience=null;
		String txtskills=null;
		String RID = null;
		String fileLocation = null;
		String txtfirstname=null;
		String req_uniqueID=null;
		String txtempmailID=null;
		String jobPostUserEmailID=null;
		String recuterEmail = null;
		String email = null;
		ArrayList PostReqList=new ArrayList();
		
		HttpSession session=request.getSession(true);
		jobPostUserEmailID=(String)session.getAttribute("jobPostUserEmailID");
		
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
		    /*-----------------------------------------------------------------------------------------------------*/  
		    /*-----------------------------------------------------------------------------------------------------*/
		    /*-----------------------------------------------------------------------------------------------------*/
		                	if(parmName.equals("email")){
		                		email = paramPart.getStringValue();
		                		System.out.println("Inside email :::: "+paramPart.getStringValue());
		                		
		                	}
		                	
		                	if(parmName.equals("txtemailaddress")){
		                		txtemailaddress = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	
		                	if(parmName.equals("txtfirstname")){
		                		txtfirstname = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("txtcontactnumber")){
		                		txtcontactnumber = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("txtrate")){
		                		txtrate = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("txttotalexperience")){
		                		txttotalexperience = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("txtskills")){
		                		txtskills = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("RID")){
		                		RID = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("appliedUserId")){
		                		txtemailaddress = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("txtempmailID")){
		                		txtempmailID = paramPart.getStringValue();
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
	            
	           
	            System.out.println("debug keshav "+ email);
	            
	            
	            Base64.Decoder decoder = Base64.getUrlDecoder();  
		        // Decoding URl  
		        String email_recuiter = new String(decoder.decode(email));  
		        System.out.println("Decoded URL: "+email_recuiter);  
	            
	            
	            
	            if(fileLocation != null && txtemailaddress != null && RID != null && email != null ){
	            	boolean resUpdatedStatus = false;
	            	resUpdatedStatus = db.updateResumeToCandidate(txtemailaddress,RID,fileLocation);
	            	if(resUpdatedStatus){
	            		
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
	             
	                        
	                        
	                        System.out.println("keshava debug1");
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
	                    
	                    System.out.println("keshava debug2");
	            		
	            		
	            		request.setAttribute("resumeUpdateStatus", "success");
	            		request.setAttribute("reqid", RID);
	            		
	            		 req_uniqueID = (String)db.getuniqueID(RID);
	            		 /*------------------------------------------------------*/
	  /*-------------Status mail sent start here---------------------------------------------------------------------------*/   
	            		 /*-------------------------------------------------------*/
	            		
	            		 /*keshav email config*/
	            			String emailid1 = txtemailaddress;  
	        				String toMailIds1[] = { emailid1 };// instance toMailds1
	        				EmailContent email1 = new EmailContent();// instance email1
	        				email1.setTo(toMailIds1);
	        				email1.setFrom("requirement@menschforce.com");
	        				email1.setSubject("You have successfully submitted your candidate with us");
	        				 
	        				            		 
	     	            
	     	        		
	            		/*String htmlbodytocandidate = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
	                             "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
	                             "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
	                             " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
	                             "<tr><td  valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 25px; margin: 16px 0 8px;padding: 0;text-align: left;\">Dear Candidate,</h4></td> </tr><tr></tr> <tr><td style=\"padding:0px 10px\"><p style=\"margin: 20px 0 10px;text-align:left;\">"+
	                             "Congratulations! You have successfully submitted your candidate with us. You can track your candidate status in your menschforce vendor management system. Click here to track the candidate status <a href=\"http://www.menschforce.com\">www.menschforce.com</a></td></tr><tr><td style=\"padding:0px 10px\"><p style=\"line-height: 20px;text-align:left;\">Requirement ID : <span style=\"font-weight:bold\"> "+RID+"</span></p></td></tr><tr><td style=\"padding:0px 10px\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>MenschForce Team</td></tr></tbody> </table> </td> </tr> </tbody> </table> </div></div></div></div></body></html>";*/
	            		
	            		String htmlbodytocandidate="<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;text-align: center;vertical-align: top; width: 100%\">" +
	        					"<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #333;border-radius: 4px 4px 0 0;padding-bottom: 25px; text-align: center\"><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px\"><a href=\"#\"><img style=\"height: auto; max-width: 156px\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a>" +
	        					"</td></tr>	</tbody> </table></td> </tr> </tbody></table><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>	<tr><td align=\"right\" valign=\"top\" style=\"background-color: #fff; width: 937px\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding: 16px;text-align: center; vertical-align: top\"><h4 style=\"font-size: 20px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: left\">Dear Staffing partner, </h4>" +
	        					"<p style=\"font-size: 15px;line-height: 27px; margin-bottom: 16px;margin-top: 16px; text-align: left\">Congratulations! You have successfully submitted your candidate with us. You can track your candidate status in your menschforce vendor management system. Click here to track the candidate status <a href=\"http://www.menschforce.com\">www.menschforce.com</a></td></tr></p><table style=\"width:85%;margin:0 auto\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody style=\"width:85%\"><tr  align=\"center\" valign=\"top\" style=\"padding: 16px;text-align: center; vertical-align: top\"><tr style=\"line-height:30px\">" +
	        					"<td><strong>Candidate Name</strong></td> <td>"+txtfirstname+"</td> </tr><tr style=\"line-height:25px\">" +
	        					"<td><strong>Email Id</strong></td> <td>"+txtemailaddress+"</td> </tr><tr style=\"line-height:25px\">" +
	        					"<td><strong>Candidate Contact Number</strong></td> <td>"+txtcontactnumber+"</td> </tr><tr style=\"line-height:25px\">" +
	        					"<td><strong>Skills</strong></td> <td>"+txtskills+"</td> </tr><tr style=\"line-height:25px\">" +
	        					"<td><strong>Candidate Experience</strong></td> <td>"+txttotalexperience+"</td> </tr><tr style=\"line-height:25px\">" +
	        					"<td><strong>RID</strong></td> <td>"+RID+"</td> </tr><tr style=\"line-height:25px\">" +
	        					"<td><strong>Rate</strong></td> <td>"+txtrate+"</td> </tr></tbody></table><table width=\"95%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr style=\"line-height:20px;\"><td style=\"padding-top:30px;\">Regards<br>Menschforce Team</td></tr></table>"+	
	        					"</tbody></table></td></tr></tbody></table></div></div></body></html>";
	            	
	            		
	            		email1.setBody(htmlbodytocandidate);// content=htmlBoady
	    				
	    				MailMail mail = new MailMail();
	    				boolean emailFlag1 = mail.sendMimeEmail(email1);
	    				Debug.print("Email sent sucessfully :" + emailFlag1);  
/*-------------------------------candidate-status upda--email sent to recruiter start here------------------------------------------------------------------------------------------------*/
	            	
	    				String emailid2 = email_recuiter ;
        				String toMailIds2[] = { emailid2 };// instance toMailds1
        				EmailContent email2 = new EmailContent();// instance email1
        				email2.setTo(toMailIds2);
        				email2.setFrom("hr@menschforce.com");
        				email2.setSubject("Your Username");
	            		
	        			 String htmlBodytoAccountManager = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;text-align: center;vertical-align: top; width: 100%\">" +
	        					"<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #333;border-radius: 4px 4px 0 0;padding-bottom: 25px; text-align: center\"><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px\"><a href=\"#\"><img style=\"height: auto; max-width: 156px\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a>" +
	        					"</td></tr>	</tbody> </table></td> </tr> </tbody></table><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>	<tr><td align=\"right\" valign=\"top\" style=\"background-color: #fff; width: 937px\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding: 16px;text-align: center; vertical-align: top\"><h4 style=\"font-size: 20px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: left\">Dear Staffing partner, </h4>" +
	        					"<p style=\"font-size: 15px;line-height: 27px; margin-bottom: 16px;margin-top: 16px; text-align: left\">A new application has been submitted for your requirement. You can update and track your applicant’s status by using the details given below.Login to your account to view the candidate details:<a href=\"http://www.menschforce.com\">www.menschforce.com</a></td></tr></p><table style=\"width:85%;margin:0 auto\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody style=\"width:85%\"><tr  align=\"center\" valign=\"top\" style=\"padding: 16px;text-align: center; vertical-align: top\"><tr style=\"line-height:30px\">" +
	        					"<td><strong>Candidate Name</strong></td> <td>"+txtfirstname+"</td> </tr><tr style=\"line-height:25px\">" +
	        					"<td><strong>Email Id</strong></td> <td>"+txtemailaddress+"</td> </tr><tr style=\"line-height:25px\">" +
	        					"<td><strong>Candidate Contact Number</strong></td> <td>"+txtcontactnumber+"</td> </tr><tr style=\"line-height:25px\">" +
	        					"<td><strong>Skills</strong></td> <td>"+txtskills+"</td> </tr><tr style=\"line-height:25px\">" +
	        					"<td><strong>Candidate Experience</strong></td> <td>"+txttotalexperience+"</td> </tr><tr style=\"line-height:25px\">" +
	        					"<td><strong>RID</strong></td> <td>"+RID+"</td> </tr><tr style=\"line-height:25px\">" +
	        					"<td><strong>Rate</strong></td> <td>"+txtrate+"</td> </tr></tbody></table><table width=\"95%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr style=\"line-height:20px;><td style=\"padding-top:30px;\">Regards<br>Menschforce Team</td></tr></table>"+	
	        					"</tbody></table></td></tr></tbody></table></div></div></body></html>";
	        			 
	        			 
	        			  email2.setBody(htmlBodytoAccountManager);// content=htmlBoady
		    				
		    				MailMail mail1 = new MailMail();
		    				boolean emailFlag2 = mail1.sendMimeEmail(email2);
		    				Debug.print("Email sent sucessfully1 :" + emailFlag2);

	        			 
	        			 
	        			 
	/*-----------------------------------candidate-status upda--email sent to recruiter End here--------------------------------------------------------------------------------------------*/        			 
	        			 
	            		
		     	       
	            		
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
	                    
	                    request.setAttribute("createStatus", "success");
	                    
	            		return new ModelAndView("requirements/listPostReq");
	            		
	            		//return new ModelAndView("requirements/ApplyJobVacancySuccess"); 
	            	}else{
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
	                    
	                    request.setAttribute("createStatus", "fail");
	                    
	            		return new ModelAndView("requirements/listPostReq");
	            		
	            		//request.setAttribute("resumeUpdateStatus", "fail");
	            		//return new ModelAndView("requirements/ApplyJobVacancySuccess"); 
	            	}
	            }else{
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
                    
                    request.setAttribute("createStatus", "fail");
                    
            		return new ModelAndView("requirements/listPostReq");
	            	//request.setAttribute("resumeUpdateStatus", "fail");
	            } 
	}
	
	
	@RequestMapping("/ApplyJobVacancySuccess.html")
	public ModelAndView ApplyJobVacancySuccess(HttpServletRequest request,
			HttpServletResponse response) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException,
			ParserConfigurationException, SAXException{
		
		HttpSession session=request.getSession(true);
		
		String uniqueReqId = null;
		//ArrayList uniqueReqIdList = new ArrayList();
		
		String candidateEmail = request.getParameter("email");
		String RequirementId = request.getParameter("RID1");
		
		System.out.println("candidateEmail------"+candidateEmail);
		System.out.println("RequirementId------"+RequirementId);
		
		
		try {

			URL url = new URL("https://www.digiblitzonline.com/sp-portal/dBConsult/digiblitz/_vti_bin/ListData.svc/JobVacancy?$filter=DBRequirementID%20eq%20%27"+RequirementId+"%27");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/atom+xml");
			conn.setRequestProperty("Authorization", "Basic c3VyZXNoOkdvbGQxMjM=");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			System.out.println("check....");

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			
		
			/*String output;
		System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);	}
			*/
			
			DocumentBuilderFactory dbFactory 
	        = DocumentBuilderFactory.newInstance();
	     DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	     Document doc = dBuilder.parse(conn.getInputStream());

	     doc.getDocumentElement().normalize();
	     System.out.println("Root element :" 
	        + doc.getDocumentElement().getNodeName());
	     NodeList nList = doc.getElementsByTagName("entry");
	     System.out.println("----------------------------");
	   
	     
	     
	   
	     for (int temp = 0; temp < nList.getLength(); temp++) 
	     {
	    	
	     
	    	 System.out.println("list..."+nList.getLength());
	        Node nNode = nList.item(temp);
	        
	        
	        System.out.println("\nCurrent Element :" 
	           + nNode.getNodeName());
	        
	        if (nNode.getNodeType() == Node.ELEMENT_NODE)
	        {
	        	
	           Element eElement = (Element) nNode;
	           System.out.println("inside loop........");
	          
	      
	           uniqueReqId = eElement.getElementsByTagName("d:Id") .item(0)  .getTextContent();
	 	                  
	 	     
	           
	        }
	        
	        //String tempstr[] = {id};
	        
	        //uniqueReqIdList.add(tempstr);
	        
	       // System.out.println("list.size "+list.size());
	       
	       System.out.println("----"+uniqueReqId);

	     }
	     
	     
	     //request.setAttribute("uniqueReqIdList",uniqueReqIdList);

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		


		try {

			URL url = new URL("https://www.digiblitzonline.com/sp-portal/dBConsult/digiblitz/_vti_bin/ListData.svc/ReceivedResumes?$filter=%20CandidateEMail%20eq%20%27"+candidateEmail+"%27%20and%20RequirementIDId%20eq%20"+uniqueReqId);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/atom+xml");
			conn.setRequestProperty("Authorization", "Basic c3VyZXNoOkdvbGQxMjM=");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			System.out.println("check....");

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			
		
			/*String output;
		System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);	}
			*/
			
			DocumentBuilderFactory dbFactory 
            = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(conn.getInputStream());
//         Document doc1 = dBuilder.parse(fis);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" 
            + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("entry");
         System.out.println("----------------------------");
         String download=null;
         String name=null;
         String reqid=null;
         String Fname=null;
         String Lname=null;
         String Mname=null;
         String Con_num=null;
         String Can_mail=null;
         String Sub_Id=null;
         String Dob=null;
         String Tot_Exp=null;
         String empname=null;
         String Emp_CN=null;
         String Emp_maail=null;
         String Con_per=null;
         String Current_loc=null;
         String bywhom_id=null;
         String pref_loc=null;
         String Rate=null;
         String avail=null;
         String visa_type=null;
         String Visa_app=null;
         String Form=null;
         String Form_94=null;
         String Time=null;
         String Exp=null;
         String skill=null;
         String Relo=null;
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" 
               + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println("inside loop........");
             
               download=eElement .getElementsByTagName("content")
		                  .item(0)
		                   .getAttributes().getNamedItem("src").getNodeValue();
	               System.out.println("Document : " 
	                  + download);
            
               name=eElement
	                  .getElementsByTagName("d:Name")
	                  .item(0)
	                  .getTextContent();
               System.out.println("Name : " 
                  + name);
                reqid =eElement
 	                  .getElementsByTagName("d:RequirementIDId")
	                  .item(0)
	                  .getTextContent();
               System.out.println(" RequirementId: " 
               + reqid);
                Fname=eElement
	 	                  .getElementsByTagName("d:FirstName")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("FirstName : " 
 	                  + Fname);
                Lname=eElement
	 	                  .getElementsByTagName("d:LastName")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("LastName : " 
 	                  + Lname);
                Mname= eElement
	 	                  .getElementsByTagName("d:MiddleName")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("MiddleName : " 
 	                  +Mname);
               Con_num=eElement
	 	                  .getElementsByTagName("d:CandidateContactNumber")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("CandidateContactNumber : " 
 	                  + Con_num);
               
                Can_mail=eElement
	 	                  .getElementsByTagName("d:CandidateEMail")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("CandidateEMail : " 
 	                  + Can_mail);
                Sub_Id= eElement
	 	                  .getElementsByTagName("d:SubmittedById")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("SubmittedById : " 
            		   +Sub_Id);
                Dob= eElement
	 	                  .getElementsByTagName("d:DateOfBirth")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("DateOfBirth : " 
 	                  +Dob);
                Tot_Exp=eElement
	 	                  .getElementsByTagName("d:TotalExperience")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("TotalExperience : " 
 	                  +Tot_Exp); 
                empname=eElement
	 	                  .getElementsByTagName("d:EmployerName")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("EmployerName : " 
 	                  + empname);
               Emp_CN= eElement
 	                  .getElementsByTagName("d:EmployerContactNumber")
 	                  .item(0)
 	                  .getTextContent();
               System.out.println("EmployerContactNumber : " 
 	                  +Emp_CN);
                Emp_maail= eElement
	 	                  .getElementsByTagName("d:EmployerMailID")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("EmployerMailID : " 
 	                  +Emp_maail);
                Con_per= eElement
	 	                  .getElementsByTagName("d:ContactPerson")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("ContactPerson : " 
 	                  +Con_per);
                Current_loc=eElement
	 	                  .getElementsByTagName("d:CurrentLocation")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("CurrentLocation : " 
 	                  + Current_loc);
                bywhom_id= eElement
	 	                  .getElementsByTagName("d:ByWhomValue")
	 	                  .item(0)
	 	                  .getTextContent();
               
               System.out.println("ByWhomValue : " 
 	                  +bywhom_id);
                pref_loc= eElement
	 	                  .getElementsByTagName("d:PreferredLocation")
	 	                  .item(0)
	 	                  .getTextContent();
               
               System.out.println("PreferredLocation : " 
	 	                  + pref_loc);
                Rate= eElement
	 	                  .getElementsByTagName("d:RateInDollar")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("RateInDollar : " 
	 	                  + Rate);
                avail=eElement
	 	                  .getElementsByTagName("d:Availability")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("Availability : " 
	 	                  + avail);
                visa_type= eElement
	 	                  .getElementsByTagName("d:VisaTypeValue")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("VisaTypeValue : " 
	 	                  +visa_type);
                Visa_app=eElement
	 	                  .getElementsByTagName("d:VisaApprovalValue")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("VisaApprovalValue : " 
	 	                  + Visa_app);
                Form= eElement
	 	                  .getElementsByTagName("d:FormI797AvailableValue")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("FormI797AvailableValue : " 
	 	                  +Form);
                Form_94= eElement
	 	                  .getElementsByTagName("d:FormI94AvailableValue")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("FormI94AvailableValue : " 
	 	                  +Form_94);
                Time=eElement
	 	                  .getElementsByTagName("d:BestTimeForTelephonicInterview")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("BestTimeForTelephonicInterview : " 
	 	                  + Time);
                Exp=eElement
	 	                  .getElementsByTagName("d:ExperienceInUSA")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("ExperienceInUSA : " 
	 	                  + Exp);
                skill= eElement
	 	                  .getElementsByTagName("d:Skills")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("Skills : " 
	 	                  +skill);
                Relo=eElement
	 	                  .getElementsByTagName("d:ReLocationValue")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("ReLocationValue : " 
	 	                  + Relo);
               
            }
            request.setAttribute("name",name);
            request.setAttribute("reqid",reqid);
            request.setAttribute("Fname",Fname);
            request.setAttribute("Lname",Lname);
            request.setAttribute("Mname",Mname);
            request.setAttribute("Con_num",Con_num);
            request.setAttribute("Can_mail",Can_mail);
            request.setAttribute("Sub_Id",Sub_Id);
            request.setAttribute("Dob",Dob);
            request.setAttribute("Tot_Exp",Tot_Exp);
            request.setAttribute("empname",empname);
            request.setAttribute("Emp_CN",Emp_CN);
            request.setAttribute("Emp_maail",Emp_maail);
            request.setAttribute("Con_per",Con_per);
            request.setAttribute("Current_loc",Current_loc);
            request.setAttribute("bywhom_id",bywhom_id);
            request.setAttribute("pref_loc",pref_loc);
            request.setAttribute("Rate",Rate);
            request.setAttribute("avail",avail);
            request.setAttribute("visa_type",visa_type);
            request.setAttribute("Visa_app",Visa_app);

            request.setAttribute("Form",Form);
            request.setAttribute("Form_94",Form_94);
            request.setAttribute("Time",Time);
            request.setAttribute("Exp",Exp);
            request.setAttribute("skill",skill);
            request.setAttribute("Relo",Relo);
            request.setAttribute("download",download);
            return new ModelAndView("requirements/ApplyJobVacancySuccess");
         }
		
			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		
		
		return new ModelAndView("requirements/ApplyJobVacancySuccess"); 
	}
	
	@RequestMapping("/ListAppliedCanDetails.html")
	public ModelAndView ListAppliedCanDetails(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("RequirementsAction.ListAppliedCanDetails()");
		String userId = null;
		userId = (String) session.getAttribute("jobPostUserId");
        ArrayList AppliedCandidateList = new ArrayList();
        AppliedCandidateList = (ArrayList)db.getAllAppliedCandidateListByUserId(userId);
        
        String currentPageNo = (String)request.getParameter("currentPageNo");
        AppliedCandidateList = doPagination(AppliedCandidateList,currentPageNo);

       System.out.println("do pgination");
        System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
        request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
        System.out.println("currentPageNo------------------>"+currentPageNo);
	 	request.setAttribute("currentPageNo", currentPageNo);
	 	
        request.setAttribute("allAppliedCandidateList",null);
        request.setAttribute("allAppliedCandidateList",AppliedCandidateList);
        request.setAttribute("pageStatus","AppliedCandidateList");
		return new ModelAndView("requirements/listAppliedCandidateList");
	}
	
	@RequestMapping("/ViewListAppliedCanDetailsByUniqueId.html")
	public ModelAndView ViewListAppliedCanDetailsByUniqueId(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("RequirementsAction.ViewListAppliedCanDetailsByUniqueId()");
		String CanDetailsByUniqueId = null;
		CanDetailsByUniqueId = request.getParameter("uniqueId");
        ArrayList AppliedCanDetailsList = new ArrayList();
        AppliedCanDetailsList = (ArrayList)db.getCanDetailsByUniqueId(CanDetailsByUniqueId);

        request.setAttribute("AppliedCanDetailsList",null);
        request.setAttribute("AppliedCanDetailsList",AppliedCanDetailsList);
        request.setAttribute("pageStatus","read");
		return new ModelAndView("requirements/ViewAppliedCandidateList");
	}
	
	
	
	@RequestMapping("/ListUpdateCandidateStatus.html")
	public ModelAndView ListCandidateStatus(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("RequirementsAction.ListAppliedCanDetails()");
		String jobPostCompanyUniqueId = null;
		String companyJobPostCategory = null;
		
		jobPostCompanyUniqueId = (String)session.getAttribute("uniqueJobPostCompanyId");
		companyJobPostCategory = (String)session.getAttribute("jobPostCompanyCategory");
		ArrayList listCandidateStatus = new ArrayList();
		listCandidateStatus = (ArrayList)db.getAllAppliedCandidateList(companyJobPostCategory,jobPostCompanyUniqueId);
		
		String currentPageNo = (String)request.getParameter("currentPageNo");
        
		listCandidateStatus = doPagination(listCandidateStatus,currentPageNo);

       
        System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
        request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
        System.out.println("currentPageNo------------------>"+currentPageNo);
	 	request.setAttribute("currentPageNo", currentPageNo);
        request.setAttribute("updateCanStatus","init");
        request.setAttribute("listCandidateStatus",null);
        request.setAttribute("listCandidateStatus",listCandidateStatus);
        request.setAttribute("pageStatus","update");
		return new ModelAndView("requirements/listCandidateStatus");
	}
	
	@RequestMapping("/ReceivedResumeList.html")
	public ModelAndView ReceivedResumeList(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("RequirementsAction.ReceivedResumeList()");
		String jobPostCompanyUniqueId = null;
		String companyJobPostCategory = null;
		
		jobPostCompanyUniqueId = (String)session.getAttribute("uniqueJobPostCompanyId");
		companyJobPostCategory = (String)session.getAttribute("jobPostCompanyCategory");
		ArrayList AppliedCandidateList = new ArrayList();
		AppliedCandidateList = (ArrayList)db.getAllAppliedCandidateList(companyJobPostCategory,jobPostCompanyUniqueId);
		
		String currentPageNo = (String)request.getParameter("currentPageNo");
        
		AppliedCandidateList = doPagination(AppliedCandidateList,currentPageNo);
		System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
        request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
        System.out.println("currentPageNo------------------>"+currentPageNo);
	 	request.setAttribute("currentPageNo", currentPageNo);
		request.setAttribute("allAppliedCandidateList",null);
        request.setAttribute("allAppliedCandidateList",AppliedCandidateList);
        request.setAttribute("pageStatus","CandidateResumeList");
		return new ModelAndView("requirements/listAppliedCandidateList");
	}
	
	@RequestMapping("/initUpdateCandidateStatus.html")
	public ModelAndView initUpdateCandidateStatusByUniqueId(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("RequirementsAction.initUpdateCandidateStatusByUniqueId()");
		String CanDetailsByUniqueId = null;
		CanDetailsByUniqueId = request.getParameter("uniqueId");
        ArrayList AppliedCanDetailsList = new ArrayList();
        AppliedCanDetailsList = (ArrayList)db.getCanDetailsByUniqueId(CanDetailsByUniqueId);

        request.setAttribute("AppliedCanDetailsList",null);
        request.setAttribute("AppliedCanDetailsList",AppliedCanDetailsList);
        request.setAttribute("pageStatus","Update");
		return new ModelAndView("requirements/ViewCandidateStatus");
	}
	
	/*------------------------------------------------------------------------------------------*/
/*-----------------------candidate status..status update details starting here----------------------------------------------------------------------------------------------*/
	/*------------------------------------------------------------------------------------------*/
	
	 @RequestMapping("/UpdateCandidateStatus.html")
	public ModelAndView UpdateCandidateStatusByUniqueId(HttpServletRequest request,
			HttpServletResponse response) throws  Exception{
		
		Properties config_property = new Properties();
		config_inputStream = getClass().getClassLoader().getResourceAsStream(config_prop);

		if (config_inputStream != null) {
			config_property.load(config_inputStream);
		} else {
			throw new FileNotFoundException("property file '" + config_prop + "' not found in the classpath");
		}
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		HLCUserMaster objUsrDet = new HLCUserMaster();
		HLCContactDetails objUsrCont = new HLCContactDetails();
		HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
		MFAppliedCandidateBean objCandidatebean = new MFAppliedCandidateBean();
		DBERPEmployeeSessionBean objEmpSession = new DBERPEmployeeSessionBean();
		Employee emp = null;
		
		String jobPostCompanyUniqueId = null;
		String companyJobPostCategory = null;
		
		jobPostCompanyUniqueId = (String)session.getAttribute("uniqueJobPostCompanyId");
		companyJobPostCategory = (String)session.getAttribute("jobPostCompanyCategory");
		
		Debug.print("RequirementsAction.UpdateCandidateStatusByUniqueId()");
		String CanDetailsByUniqueId = null;
		String candidateStatus = null;
		String appliedUserId = null;
		String appliedUsedRoleId = null;
		
//		String empMaritalStatus = null;
		String empSSNNo = null;
		String empPassportNo = null;
		String empContactNo = null;
		String empJobTitle = null;
		String empDepartment = null;
		String empPayGroup = null;
		String empPayratePerhour = null;
		String empPayPeriod = null;
		String empFederalTaxWHFormLocation = null;
		String empStateTaxWHFormLocation = null;
		String empJoiningDate = null;
		String empJobLocation = null;
		String candidateName=null;
		String candidateEmail=null;
		String RequirementID=null;
		String req_uniqueID=null;
		String CANID = null;
		String employerMailID=null;
		String txtempname=null;
		
		candidateName = request.getParameter("firstName");
		candidateEmail = request.getParameter("CandidateEMail");
		RequirementID = request.getParameter("RequirementID");
		CanDetailsByUniqueId = request.getParameter("uniqueId");
		candidateStatus = request.getParameter("candidateStatus");
		appliedUserId = request.getParameter("appliedUserId");
		CANID = request.getParameter("CANID");
		employerMailID=request.getParameter("employerMailID");
		txtempname=request.getParameter("txtempname");
		System.out.println("txtempname====="+txtempname);
		
		ArrayList PostReqList = new ArrayList();
		
		if(candidateStatus.equalsIgnoreCase("Joined")){
			
		//empMaritalStatus = request.getParameter("empMaritalStatus");
		empSSNNo = request.getParameter("empSSNNo");
		empPassportNo = request.getParameter("empPassportNo");
		empContactNo = request.getParameter("empContactNo");
		empJobTitle = request.getParameter("empJobTitle");
		empDepartment = request.getParameter("empDepartment");
		empPayGroup = request.getParameter("empPayGroup");
		empPayratePerhour = request.getParameter("empPayratePerhour");
		empPayPeriod = request.getParameter("empPayPeriod");
		empFederalTaxWHFormLocation = "";
		empStateTaxWHFormLocation = "";
		empJoiningDate = request.getParameter("empJoiningDate");
		empJobLocation = request.getParameter("empJobLocation");
		
		
		
		String newstr = empJoiningDate;
		SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format1.parse(newstr));
		} catch (ParseException e) {
			throw new Exception("Joining Date");
		}
		System.out.println(format.format(c.getTime()));
		empJoiningDate = format.format(c.getTime());
		}
		String htmlbody=null;
		req_uniqueID = (String)db.getuniqueID(RequirementID);
		 PostReqList = db.getPostReqByUniqueId(req_uniqueID);
		 String can_jobtitle=null;
		 Iterator itr = PostReqList.iterator();
		 while (itr.hasNext()) {  
		 String TempList[] = (String[])itr.next();   
		  can_jobtitle=TempList[30]; 
  /*--------------------------------connecting mail----------------------------------------------------------------*/
		 		
		  /*-----------------------------------------------connecting mail to recruiter--------------------------------------------------------------------------------------*/
		  /*-----------------------------connecting mail ----------------------------------------------------------------*/
	 		
          System.out.println("debug jithina mail start to recruiter");
          
            String emailid4 = employerMailID;
			  String toMailIds4[] = { emailid4 };// instance toMailds1
			  EmailContent email4 = new EmailContent();// instance email1
			  email4.setTo(toMailIds4);
			  email4.setFrom("requirement@menschforce.com");
			  email4.setSubject("Status of your Candidate");
	                 
	      			
	
	if(candidateStatus.equalsIgnoreCase("Accepted By Client") || candidateStatus == "Accepted By Client"){
					
		
		
		htmlbody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
              "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
              "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
              " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
              "<tr><td  valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 25px; margin: 16px 0 8px;padding: 0;text-align: left;\">Dear Staffing Partner,</h4></td> </tr><tr></tr> <tr><td style=\"padding:0px 10px\"><p style=\"margin: 20px 0 10px;text-align:left;\">"+
              "Your recent application for Job title <strong>' "+can_jobtitle+ "'</strong>.Your Candidate status has been updated by the menschForce Hiring Team.<br> Here are the Details:</td></tr><table style=\"width:85%;margin:0 auto\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody style=\"width:85%\"><tr  align=\"center\" valign=\"top\" style=\"padding: 16px;text-align: center; vertical-align: top\"><tr style=\"line-height:30px\">" +
				"<td><strong>Candidate Name</strong></td> <td>"+candidateName+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>Candidate Status</strong></td> <td>"+candidateStatus+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>Candidate ID</strong></td> <td>"+CANID+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>JobTitle</strong></td> <td>"+can_jobtitle+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>Reqirement ID</strong></td> <td>"+RequirementID+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>Candidate Email</strong></td> <td>"+candidateEmail+"</td> </tr>" +
				"</tbody></table><table style=\"width:95%;margin:0 auto\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td style=\"padding:30px 0px;background-color: #fff;\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>Menchforce Sourcing Team,<br>hr@menschforce.com"+
				"</td></tr></table></tbody></table></td></tr></tbody></table></div></div></div></body></html>";
		 			
	}
	
	
	else if(candidateStatus.equalsIgnoreCase("Rejected By Rate") || candidateStatus == "Rejected By Rate"){
		
								
		htmlbody ="<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
              "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
              "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
              " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
              "<tr><td  valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 25px; margin: 16px 0 8px;padding: 0;text-align: left;\">Dear Staffing Partner,</h4></td> </tr><tr></tr> <tr><td style=\"padding:0px 10px\"><p style=\"margin: 20px 0 10px;text-align:left;\">"+
              "Your recent application for Job title <strong>' "+can_jobtitle+ "'</strong>.Thank You for submitting your candidate's resume.Your Candidate status has been updated by the menschForce Hiring Team. Please re-submit the candidate profile with a acceptable rate . Click the link <a href=\"http://www.menschforce.com\">www.menschforce.com</a><br><br> Here are the Details:</td></tr><table style=\"width:85%;margin:0 auto\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody style=\"width:85%\"><tr  align=\"center\" valign=\"top\" style=\"padding: 16px;text-align: center; vertical-align: top\"><tr style=\"line-height:30px\">" +
				"<td><strong>Candidate Name</strong></td> <td>"+candidateName+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>Candidate Status</strong></td> <td>"+candidateStatus+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>Candidate ID</strong></td> <td>"+CANID+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>JobTitle</strong></td> <td>"+can_jobtitle+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>Reqirement ID</strong></td> <td>"+RequirementID+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>Candidate Email</strong></td> <td>"+candidateEmail+"</td> </tr>" +
				"</tbody></table><table style=\"width:95%;margin:0 auto\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td style=\"padding:30px 0px;background-color: #fff;\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>Menchforce Sourcing Team,<br>hr@menschforce.com"+
				"</td></tr></table></tbody></table></td></tr></tbody></table></div></div></div></body></html>";
		
		
	}
	
	else if(candidateStatus.equalsIgnoreCase("Rejected") || candidateStatus == "Rejected"){
		
		
		
		htmlbody="<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
              "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
              "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
              " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
              "<tr><td  valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 25px; margin: 16px 0 8px;padding: 0;text-align: left;\">Dear Staffing Partner,</h4></td> </tr><tr></tr> <tr><td style=\"padding:0px 10px\"><p style=\"margin: 20px 0 10px;text-align:left;\">"+
              "Thank You for submitting your candidate's resume.Your Candidate status has been updated by the menschForce Hiring Team. The candidate does not currently meet the necessary requirements. You can submit more candidates here. <a href=\"http://www.menschforce.com\">www.menschforce.com</a><br><br>Here are the Details:</td></tr><table style=\"width:85%;margin:0 auto\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody style=\"width:85%\"><tr  align=\"center\" valign=\"top\" style=\"padding: 16px;text-align: center; vertical-align: top\"><tr style=\"line-height:30px\">" +
				"<td><strong>Candidate Name</strong></td> <td>"+candidateName+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>Candidate Status</strong></td> <td>"+candidateStatus+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>Candidate ID</strong></td> <td>"+CANID+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>JobTitle</strong></td> <td>"+can_jobtitle+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>Reqirement ID</strong></td> <td>"+RequirementID+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>Candidate Email</strong></td> <td>"+candidateEmail+"</td> </tr>" +
				"</tbody></table><table style=\"width:95%;margin:0 auto\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td style=\"padding:30px 0px;background-color: #fff;\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>Menchforce Sourcing Team,<br>hr@menschforce.com"+
				"</td></tr></table></tbody></table></td></tr></tbody></table></div></div></div></body></html>";
	
		
	}
     

else if(candidateStatus.equalsIgnoreCase("Shortlisted") || candidateStatus == "Shortlisted"){
		
		htmlbody="<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
              "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
              "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
              " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
              "<tr><td  valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 25px; margin: 16px 0 8px;padding: 0;text-align: left;\">Dear Staffing Partner,</h4></td> </tr><tr></tr> <tr><td style=\"padding:0px 10px\"><p style=\"margin: 20px 0 10px;text-align:left;\">"+
              "Your recent application for Job title <strong>' "+can_jobtitle+ "'</strong>. Your Candidate status has been updated by the menschForce Hiring Team.<br>Here are the Details:</td></tr><table style=\"width:85%;margin:0 auto\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody style=\"width:85%\"><tr  align=\"center\" valign=\"top\" style=\"padding: 16px;text-align: center; vertical-align: top\"><tr style=\"line-height:30px\">" +
				"<td><strong>Candidate Name</strong></td> <td>"+candidateName+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>Candidate Status</strong></td> <td>"+candidateStatus+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>Candidate ID</strong></td> <td>"+CANID+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>JobTitle</strong></td> <td>"+can_jobtitle+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>Reqirement ID</strong></td> <td>"+RequirementID+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>Candidate Email</strong></td> <td>"+candidateEmail+"</td> </tr>" +
				"</tbody></table><table style=\"width:95%;margin:0 auto\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td style=\"padding:30px 0px;background-color: #fff;\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>Menchforce Sourcing Team,<br>hr@menschforce.com"+
				"</td></tr></table></tbody></table></td></tr></tbody></table></div></div></div></body></html>";
	
      }
	boolean updateCanStatus = false;
	
	updateCanStatus = db.updateCandidateStatusByUniqueId(CanDetailsByUniqueId,candidateStatus);
	
	if(updateCanStatus){
		System.out.println("debug jithina updateCanStatus-recruiter");
		
		try{
			email4.setBody(htmlbody);// content=htmlBoady
			MailMail mail4 = new MailMail();
			boolean emailFlag4 = mail4.sendMimeEmail(email4);
			Debug.print("Email sent sucessfully to recruiter:" + emailFlag4);
	 }
		catch (Exception ex) {
			System.err.println("Caught an exception.");
			request.setAttribute("response_status", "fail");
			ex.printStackTrace();

		} 
	
	}
		 


			
			/*----------------------------------------------------------- mail starts to candidate-------------------------------------------------------------------------------------*/
					
		  		  
		  
	            System.out.println("debug jithina mail start to candidate ");
	            
	              String emailid3 = candidateEmail;
    			  String toMailIds3[] = { emailid3 };// instance toMailds1
    			  EmailContent email3 = new EmailContent();// instance email1
    			  email3.setTo(toMailIds3);
    			  email3.setFrom("hr@menschforce.com");
    			  email3.setSubject("candidate::Status");
		                 
		      			
		
		if(candidateStatus.equalsIgnoreCase("Accepted By Client") || candidateStatus == "Accepted By Client"){
						
			
			
			htmlbody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
                    "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
                    "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
                    " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
                    "<tr><td  valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 25px; margin: 16px 0 8px;padding: 0;text-align: left;\">Dear " +candidateName+",</h4></td> </tr><tr></tr> <tr><td style=\"padding:0px 10px\"><p style=\"margin: 20px 0 10px;text-align:left;\">"+
                    "Your recent application for Job title <strong>' "+can_jobtitle+ ".'</strong>Your status has been updated by the menschForce Hiring Team.<br><br>Here are the details:</td></tr><table style=\"width:85%;margin:0 auto\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody style=\"width:85%\"><tr  align=\"center\" valign=\"top\" style=\"padding: 16px;text-align: center; vertical-align: top\"><tr style=\"line-height:30px\">" +
					"<td><strong>Candidate Name</strong></td> <td>"+candidateName+"</td> </tr><tr style=\"line-height:25px\">" +
					"<td><strong>Candidate Status</strong></td> <td>"+candidateStatus+"</td> </tr><tr style=\"line-height:25px\">" +
					"<td><strong>Candidate ID</strong></td> <td>"+CANID+"</td> </tr><tr style=\"line-height:25px\">" +
					"<td><strong>JobTitle</strong></td> <td>"+can_jobtitle+"</td> </tr><tr style=\"line-height:25px\">" +
					"<td><strong>Reqirement ID</strong></td> <td>"+RequirementID+"</td> </tr><tr style=\"line-height:25px\">" +
					"<td><strong>Candidate Email</strong></td> <td>"+candidateEmail+"</td> </tr>" +
					"</tbody></table><table style=\"width:95%;margin:0 auto\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td style=\"padding:30px 0px;background-color: #fff;\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>Menchforce Sourcing Team,<br>hr@menschforce.com"+
					"</td></tr></table></tbody></table></td></tr></tbody></table></div></div></div></body></html>";
			 			
		}
		 
		
		else if(candidateStatus.equalsIgnoreCase("Rejected By Rate") || candidateStatus == "Rejected By Rate"){
			
									
			htmlbody ="<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
                    "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
                    "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
                    " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
                    "<tr><td  valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 25px; margin: 16px 0 8px;padding: 0;text-align:left;\">Dear " +candidateName+",</h4></td> </tr><tr></tr> <tr><td style=\"padding:0px 10px\"><p style=\"margin: 20px 0 10px;text-align:left;\">"+
                    "Your recent application for Job title <strong>' "+can_jobtitle+ ".'</strong>Your status has been updated by the menschForce Hiring Team . Click the link <a href=\"http://www.menschforce.com\">www.menschforce.com</a> <br> Here are the details:</td></tr><table style=\"width:85%;margin:0 auto\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody style=\"width:85%\"><tr  align=\"center\" valign=\"top\" style=\"padding: 16px;text-align: center; vertical-align: top\"><tr style=\"line-height:30px\">" +
					"<td><strong>Candidate Name</strong></td> <td>"+candidateName+"</td> </tr><tr style=\"line-height:25px\">" +
					"<td><strong>Candidate Status</strong></td> <td>"+candidateStatus+"</td> </tr><tr style=\"line-height:25px\">" +
					"<td><strong>Candidate ID</strong></td> <td>"+CANID+"</td> </tr><tr style=\"line-height:25px\">" +
					"<td><strong>JobTitle</strong></td> <td>"+can_jobtitle+"</td> </tr><tr style=\"line-height:25px\">" +
					"<td><strong>Reqirement ID</strong></td> <td>"+RequirementID+"</td> </tr><tr style=\"line-height:25px\">" +
					"<td><strong>Candidate Email</strong></td> <td>"+candidateEmail+"</td> </tr>" +
					"</tbody></table><table style=\"width:95%;margin:0 auto\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td style=\"padding:30px 0px;background-color: #fff;\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>Menchforce Sourcing Team,<br>hr@menschforce.com"+
					"</td></tr></table></tbody></table></td></tr></tbody></table></div></div></div></body></html>";
			
			
		}
		
		else if(candidateStatus.equalsIgnoreCase("Rejected") || candidateStatus == "Rejected"){
			
			System.out.println("debug jithina rejected-candidate ");
			htmlbody="<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
                    "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
                    "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
                    " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
                    "<tr><td  valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 25px; margin: 16px 0 8px;padding: 0;text-align: left;\">Dear   " +candidateName+" ,</h4></td> </tr><tr></tr> <tr><td style=\"padding:0px 10px\"><p style=\"margin: 20px 0 10px;text-align:left;\">"+
                    "Your status has been updated by the menschForce Hiring Team.<br>Here are the Details:  </td></tr><table style=\"width:85%;margin:0 auto\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody style=\"width:85%\"><tr  align=\"center\" valign=\"top\" style=\"padding: 16px;text-align: center; vertical-align: top\"><tr style=\"line-height:30px\">" +
					"<td><strong>Candidate Name</strong></td> <td>"+candidateName+"</td> </tr><tr style=\"line-height:25px\">" +
					"<td><strong>Candidate Status</strong></td> <td>"+candidateStatus+"</td> </tr><tr style=\"line-height:25px\">" +
					"<td><strong>Candidate ID</strong></td> <td>"+CANID+"</td> </tr><tr style=\"line-height:25px\">" +
					"<td><strong>JobTitle</strong></td> <td>"+can_jobtitle+"</td> </tr><tr style=\"line-height:25px\">" +
					"<td><strong>Reqirement ID</strong></td> <td>"+RequirementID+"</td> </tr><tr style=\"line-height:25px\">" +
					"<td><strong>Candidate Email</strong></td> <td>"+candidateEmail+"</td> </tr>" +
					"</tbody></table><table style=\"width:95%;margin:0 auto\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td style=\"padding:30px 0px;background-color: #fff;\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>Menchforce Sourcing Team,<br>hr@menschforce.com"+
					"</td></tr></table></tbody></table></td></tr></tbody></table></div></div></div></body></html>";
		
			
		}
            else if(candidateStatus.equalsIgnoreCase("Shortlisted") || candidateStatus == "Shortlisted"){
			System.out.println("debug jithina enterd");
			htmlbody="<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
                    "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
                    "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
                    " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
                    "<tr><td  valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 25px; margin: 16px 0 8px;padding: 0;text-align: left;\">Dear " +candidateName+" ,</h4></td> </tr><tr></tr> <tr><td style=\"padding:0px 10px\"><p style=\"margin: 20px 0 10px;text-align:left;\">"+
                    "Your recent application for Job title <strong>' "+can_jobtitle+ ".'</strong>Your status has been updated by the menschForce Hiring Team.<br> Here are the details:</td></tr><table style=\"width:85%;margin:0 auto\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody style=\"width:85%\"><tr  align=\"center\" valign=\"top\" style=\"padding: 16px;text-align: center; vertical-align: top\"><tr style=\"line-height:30px\">" +
					"<td><strong>Candidate Name</strong></td> <td>"+candidateName+"</td> </tr><tr style=\"line-height:25px\">" +
					"<td><strong>Candidate Status</strong></td> <td>"+candidateStatus+"</td> </tr><tr style=\"line-height:25px\">" +
					"<td><strong>Candidate ID</strong></td> <td>"+CANID+"</td> </tr><tr style=\"line-height:25px\">" +
					"<td><strong>JobTitle</strong></td> <td>"+can_jobtitle+"</td> </tr><tr style=\"line-height:25px\">" +
					"<td><strong>Reqirement ID</strong></td> <td>"+RequirementID+"</td> </tr><tr style=\"line-height:25px\">" +
					"<td><strong>Candidate Email</strong></td> <td>"+candidateEmail+"</td> </tr>" +
					"</tbody></table><table style=\"width:95%;margin:0 auto\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td style=\"padding:30px 0px;background-color: #fff;\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>Menchforce Sourcing Team,<br>hr@menschforce.com"+
					"</td></tr></table></tbody></table></td></tr></tbody></table></div></div></div></body></html>";
		
            }

		
		updateCanStatus = db.updateCandidateStatusByUniqueId(CanDetailsByUniqueId,candidateStatus);
		
		if(updateCanStatus){
			System.out.println("debug jithina updateCanStatus-candidate");
			
			try{
				email3.setBody(htmlbody);// content=htmlBoady
    			MailMail mail3 = new MailMail();
    			boolean emailFlag3 = mail3.sendMimeEmail(email3);
    			Debug.print("Email sent sucessfully to candidate :" + emailFlag3);
		 }
			catch (Exception ex) {
				System.err.println("Caught an exception.");
				request.setAttribute("response_status", "fail");
				ex.printStackTrace();

			} 
			
		}
		
		}
		boolean updateCanStatus = false;
		
		updateCanStatus = db.updateCandidateStatusByUniqueId(CanDetailsByUniqueId,candidateStatus);
		
		//String fromAddress=null;
			if(updateCanStatus){
				System.out.println("debug jithina updateCanStatus");
				
				try{
						      			  
					//obj1.createAndCheckDuplicateContact(txtempname, txtempname, employerMailID);
					//obj1.optin_outEmail(employerMailID);
					//obj1.sendEmail("jprakaz593@gmail.com", employerMailID, "jprakazjp@gmail.com", "", "Html","Candidate Status",htmlbody,"" );
	      		
				
				}catch (Exception ex) {
					//System.err.println("Caught an exception.");
					//request.setAttribute("response_status", "fail");
					//ex.printStackTrace();

				} 
				
				
				if(candidateStatus.equalsIgnoreCase("Joined") || candidateStatus == "Joined"){
				
				
				String dbERPServiceURL = config_property.getProperty("config.dbERPServiceURL");/*db ERP Service URL*/
				String dBERPAdminLoginName = config_property.getProperty("config.dBERPAdminUserName");/*db ERP dBERP Admin LoginName*/
				String dBERPAdminLoginPass = config_property.getProperty("config.dBERPAdminUserPass");/*db ERP dBERP Admin LoginPass*/
				objUsrDet = db.getUserMasterDetailsByUserId(appliedUserId);
				objUsrCont = objDAO.displayContactDetails(appliedUserId);
				appliedUsedRoleId = db.getRoleIdByUserId(appliedUserId);
				objCandidatebean = db.getCanDetailBeanByUniqueId(CanDetailsByUniqueId);
				String empMaritalStatus = objCandidatebean.getCandidateMaritalStatus();
//				String empPassportNo = "LM123";
//				String empContactNo = "9876543210";
				String empCompany = objCandidatebean.getCandidateCompany();
				String empCompanyCategory = objCandidatebean.getCandidateCompanyCategory();
//				String empJobTitle = "Java Developer";
//				String empDepartment = "Software Application Development";
//				String empPayGroup = "TB12";
//				double empPayratePerhour = 30.0;
//				String empPayPeriod = "Semimonthly";
//				String empFederalTaxWHFormLocation = "No";
//				String empStateTaxWHFormLocation = "No";
//				String empJoiningDate = null;
//				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
//				Calendar cal = Calendar.getInstance();  
//				System.out.println(dateFormat.format(cal.getTime()));
//				java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
//				empJoiningDate = timestamp.toString();
//				String empJobLocation = "Verginia";
				boolean empActiveStatus = false;
				String empdberpPartyid = null;
				BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
	            textEncryptor.setPassword("sa");
	            String  decryptPwd = textEncryptor.decrypt(objUsrDet.getPassword());
	            System.out.println("decrypted Password in SPApplyJobRequirements::::: "+decryptPwd);
				
				emp = new Employee("", objUsrDet.getUserId(), appliedUsedRoleId,objEmpSession.getNextEmployeeId(), "", empSSNNo,
						objUsrDet.getPrefix(), objUsrDet.getSufix(), objUsrDet.getFirstName(), objUsrDet.getMiddleName(),
						objUsrDet.getLastName(), objUsrDet.getGender(), empMaritalStatus, objUsrDet.getDob(), objUsrDet.getEmailId(),
						objUsrCont.getMobileNo(), empContactNo, empPassportNo, objUsrCont.getAddress1(), objUsrCont.getAddress1(), objUsrCont.getAddress2(),
						objUsrCont.getAddress2(), objUsrCont.getCity(), objUsrCont.getCity(), objUsrCont.getState(), objUsrCont.getState(), objUsrCont.getCountry(), objUsrCont.getCountry(),
						objUsrCont.getZip(), objUsrCont.getZip(), empCompany, empCompanyCategory, empJobTitle, empDepartment, empJobLocation,
						empPayGroup, Double.parseDouble(empPayratePerhour), empPayPeriod, empFederalTaxWHFormLocation, empStateTaxWHFormLocation, empActiveStatus,
						empJoiningDate);
				
				Debug.print("Employee values------"+objUsrDet.getUserId()+ appliedUsedRoleId+new DBERPEmployeeSessionBean().getNextEmployeeId()+ ""+ ""+
						objUsrDet.getPrefix()+ objUsrDet.getSufix()+ objUsrDet.getFirstName()+ objUsrDet.getMiddleName()+
						objUsrDet.getLastName()+ objUsrDet.getGender()+ empMaritalStatus+ objUsrDet.getDob()+ objUsrDet.getEmailId()+
						objUsrCont.getMobileNo()+ empContactNo+ empPassportNo+ objUsrCont.getAddress1()+ objUsrCont.getAddress1()+ objUsrCont.getAddress2()+
						objUsrCont.getAddress2()+ objUsrCont.getCity()+ objUsrCont.getCity()+ objUsrCont.getState()+ objUsrCont.getState()+ objUsrCont.getCountry()+ objUsrCont.getCountry()+
						objUsrCont.getZip()+ objUsrCont.getZip()+ empCompany+ empCompanyCategory+ empJobTitle+ empDepartment+ empJobLocation+
						empPayGroup+ empPayratePerhour+ empPayPeriod+ empFederalTaxWHFormLocation+ empStateTaxWHFormLocation+ empActiveStatus+
						empJoiningDate);
				
				try {
					empdberpPartyid = objEmpSession.addEmployeeDetailsToDBERP(emp, dbERPServiceURL);
					String securityGroupId = db.getSecurityGroupIdbyRoleId(appliedUsedRoleId);
	                System.out.println(" securityGroupId "+securityGroupId);
	                if(empdberpPartyid != null && securityGroupId != null && objUsrDet.getLoginName() != null && decryptPwd != null){
					boolean createUserLoginToOfbizStatus = false;
					boolean addUserLoginToToOfbizStatus = false;
					createUserLoginToOfbizStatus = objEmpSession.createUserLoginToOfbiz(objUsrDet.getLoginName(), decryptPwd, decryptPwd, "N", "Y", empdberpPartyid, dBERPAdminLoginName, dBERPAdminLoginPass, dbERPServiceURL);
					addUserLoginToToOfbizStatus = objEmpSession.addUserLoginToSecurityGroup(objUsrDet.getLoginName(), securityGroupId, dBERPAdminLoginName, dBERPAdminLoginPass, dbERPServiceURL);
	                Debug.print("createUserLoginToOfbiz status in SPApplyJobRequirements.class"+createUserLoginToOfbizStatus);
	                Debug.print("addUserLoginToSecurityGroup status in SPApplyJobRequirements.class"+addUserLoginToToOfbizStatus);
	                }else{
	                	throw new Exception("Some of the following parameter might be a NULL vlaue (Employee Partyid,Employee securityGroupId ,Employee LoginName,Employee Password)");
	                }
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				}catch (NullPointerException npe) {
					npe.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				emp.setEmpdberpPartyid(empdberpPartyid);
				try {
					objEmpSession.addEmployeeDetails(emp);
					ArrayList listCandidateStatus = new ArrayList();
					
					listCandidateStatus = (ArrayList)db.getAllAppliedCandidateList(companyJobPostCategory,jobPostCompanyUniqueId);
			        String currentPageNo = (String)request.getParameter("currentPageNo");
			        
			        listCandidateStatus = doPagination(listCandidateStatus,currentPageNo);
					System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
			        request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
			        System.out.println("currentPageNo------------------>"+currentPageNo);
				 	request.setAttribute("currentPageNo", currentPageNo);

			        request.setAttribute("updateCanStatus","success");
			        request.setAttribute("listCandidateStatus",null);
			        request.setAttribute("listCandidateStatus",listCandidateStatus);
			        request.setAttribute("pageStatus","Update");
					
				} catch (CustomGenericException e) {
					new CustomGenericException(12345,"SQLException");
				}
				
				return new ModelAndView("requirements/listCandidateStatus");
				}else{
					ArrayList listCandidateStatus = new ArrayList();
					listCandidateStatus = (ArrayList)db.getAllAppliedCandidateList(companyJobPostCategory,jobPostCompanyUniqueId);
					 String currentPageNo = (String)request.getParameter("currentPageNo");
				        
				        listCandidateStatus = doPagination(listCandidateStatus,currentPageNo);
						System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
				        request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
				        System.out.println("currentPageNo------------------>"+currentPageNo);
					 	request.setAttribute("currentPageNo", currentPageNo);
			        request.setAttribute("updateCanStatus","success");
			        request.setAttribute("listCandidateStatus",null);
			        request.setAttribute("listCandidateStatus",listCandidateStatus);
			        request.setAttribute("pageStatus","Update");
			        return new ModelAndView("requirements/listCandidateStatus");
				}
			}else{
				ArrayList listCandidateStatus = new ArrayList();
				listCandidateStatus = (ArrayList)db.getAllAppliedCandidateList(companyJobPostCategory,jobPostCompanyUniqueId);
				 String currentPageNo = (String)request.getParameter("currentPageNo");
			        
			        listCandidateStatus = doPagination(listCandidateStatus,currentPageNo);
					System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
			        request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
			        System.out.println("currentPageNo------------------>"+currentPageNo);
				 	request.setAttribute("currentPageNo", currentPageNo);
		        request.setAttribute("updateCanStatus","fail");
		        request.setAttribute("listCandidateStatus",null);
		        request.setAttribute("listCandidateStatus",listCandidateStatus);
		        request.setAttribute("pageStatus","Update");
				return new ModelAndView("requirements/listCandidateStatus");
			}
		 }
			
	
/*-----------------------------------------------------status end here------------------------------------------------------------------*/	
	
	@RequestMapping("/initJoinedCandidateList.html")
	public ModelAndView initJoinedCandidateList(HttpServletRequest request,
			HttpServletResponse response) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException, SAXException,
			ParserConfigurationException{
		
				 
		OfbizWebservices ofbizServices = new OfbizWebservices();
			  try {

			   URL url = new URL("https://www.digiblitzonline.com/sp-portal/dBConsult/digiblitz/_vti_bin/ListData.svc/CandidateStatus?$filter=CandidateStatusValue%20eq%20%27Joined%27");
			   HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			   conn.setRequestMethod("GET");
			   conn.setRequestProperty("Accept", "application/atom+xml");
			   conn.setRequestProperty("Authorization", "Basic c3VyZXNoOkdvbGQxMjM=");

			   if (conn.getResponseCode() != 200) {
			    throw new RuntimeException("Failed : HTTP error code : "
			      + conn.getResponseCode());
			   }
			   System.out.println("check....");

			   BufferedReader br = new BufferedReader(new InputStreamReader(
			    (conn.getInputStream())));
			   
			  
			   /*String output;
			  System.out.println("Output from Server .... \n");
			   while ((output = br.readLine()) != null) {
			    System.out.println(output); }
			   */
			   
			   DocumentBuilderFactory dbFactory 
			            = DocumentBuilderFactory.newInstance();
			         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			         Document doc = dBuilder.parse(conn.getInputStream());
//			         Document doc1 = dBuilder.parse(fis);
			         doc.getDocumentElement().normalize();
			         System.out.println("Root element :" 
			            + doc.getDocumentElement().getNodeName());
			         NodeList nList = doc.getElementsByTagName("entry");
			         System.out.println("----------------------------");
			       
			         String id=null;
			         String title=null;
			         String ContentTypeID=null;
			         String CandidateFullName=null;
			         String CandidateStatusValue=null;
			         String UniqueReference=null;
			         String CANID=null;
			         String CandidateContactNumber=null;
			         String RequirementID=null;
			         String CandidateEMail=null;
			         String Modified=null;
			         String Created=null;
			         String AccountManagerAssignedToValue=null;
			         String RecruiterAssignedToValue=null;
			         String Id_ref=null;
			         String ContentType=null;
			         String SubmittedById=null;
			        
			        
			         ArrayList CandidateList=new ArrayList();
			         for (int temp = 0; temp < nList.getLength(); temp++) {
			            Node nNode = nList.item(temp);
			            System.out.println("\nCurrent Element :" 
			               + nNode.getNodeName());
			            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			               Element eElement = (Element) nNode;
			               System.out.println("inside loop........");
			             
			  
			               id=eElement .getElementsByTagName("id")  .item(0) .getTextContent(); 
			               title=eElement .getElementsByTagName("title")  .item(0) .getTextContent();          
			               ContentTypeID =eElement .getElementsByTagName("d:ContentTypeID") .item(0) .getTextContent();          
			               CandidateFullName=eElement .getElementsByTagName("d:CandidateFullName") .item(0)  .getTextContent(); 
			               CandidateStatusValue=eElement .getElementsByTagName("d:CandidateStatusValue") .item(0) .getTextContent();
			               UniqueReference= eElement.getElementsByTagName("d:UniqueReference").item(0) .getTextContent();
			               CANID=eElement  .getElementsByTagName("d:CANID").item(0) .getTextContent();
			               CandidateContactNumber=eElement .getElementsByTagName("d:CandidateContactNumber").item(0) .getTextContent();      
			               RequirementID= eElement .getElementsByTagName("d:RequirementID") .item(0)  .getTextContent();
			               CandidateEMail= eElement .getElementsByTagName("d:CandidateEMail") .item(0)  .getTextContent();
			               Modified= eElement .getElementsByTagName("d:Modified") .item(0)  .getTextContent();
			               Created=eElement .getElementsByTagName("d:Created")  .item(0) .getTextContent();
			               AccountManagerAssignedToValue=eElement .getElementsByTagName("d:AccountManagerAssignedToValue") .item(0) .getTextContent();          
			               RecruiterAssignedToValue= eElement .getElementsByTagName("d:RecruiterAssignedToValue") .item(0) .getTextContent();      
			               Id_ref= eElement .getElementsByTagName("d:Id").item(0) .getTextContent();
			               ContentType= eElement .getElementsByTagName("d:ContentType") .item(0)  .getTextContent();
			               SubmittedById= eElement .getElementsByTagName("d:SubmittedById") .item(0)  .getTextContent();
			              
			                    
			                     
			                     
			               
			            }
			            String TempList[] = {CANID,CandidateFullName,CandidateContactNumber,RequirementID,CandidateEMail,CandidateStatusValue,SubmittedById,UniqueReference};
			            request.setAttribute("id",id);
			            request.setAttribute("title",title);
			            request.setAttribute("ContentTypeID",ContentTypeID);
			            request.setAttribute("CandidateFullName",CandidateFullName);
			            request.setAttribute("CandidateStatusValue",CandidateStatusValue);
			            request.setAttribute("UniqueReference",UniqueReference);
//			            request.setAttribute("CANID",CANID);
			            request.setAttribute("CandidateContactNumber",CandidateContactNumber);
			            request.setAttribute("RequirementID",RequirementID);
			            request.setAttribute("CandidateEMail",CandidateEMail);
			            request.setAttribute("Modified",Modified);
			            request.setAttribute("Created",Created);
			            request.setAttribute("AccountManagerAssignedToValue",AccountManagerAssignedToValue);
			            request.setAttribute("RecruiterAssignedToValue",RecruiterAssignedToValue);
			            request.setAttribute("Id_ref",Id_ref);
			            request.setAttribute("ContentType",ContentType);
			            request.setAttribute("SubmittedById",SubmittedById);
			            CandidateList.add(TempList);
			            
			            String fName = null;
			            String lName = null;
			            String paddCity = null;
			            
			            
			            boolean updateCanStatus = false;
			            updateCanStatus = db.updateReceivedResumeDet(CandidateStatusValue,RequirementID,CandidateEMail);
			            
			            if(updateCanStatus == true){
			            ArrayList canFullDet = null;
			            canFullDet = db.getReceivedResumeDetByEmailId(CandidateEMail);
			            

						if(canFullDet!=null && canFullDet.size()!=0){
							Iterator itCanList = canFullDet.iterator();
							int i=1;
							while(itCanList.hasNext()){
							
								String strCanlist[]= (String[])itCanList.next();
								fName = strCanlist[0];
								lName = strCanlist[1];
								paddCity = strCanlist[2];
							}
							
							//send user details to ofbiz starts
			                 String loginUsername = "admin";
			                 String loginPassword = "ofbiz";
			                 String requirePasswordChange = "N";
			                 String enabled = "Y";
			                 //String partyId = "admin";
			                 
			                 System.out.println("fName----------in spApplyAction---"+fName);
			                 System.out.println("lName----------in spApplyAction---"+fName);
			                 System.out.println("paddCity----------in spApplyAction---"+paddCity);
			                
			                 //usrSalutation fName lName sName paddNoMob paddLane paddArea paddCity paddZip usrEmail 
			                 String partyId = ofbizServices.createEmployee("Mr/Mrs", fName, lName, "", CandidateContactNumber, paddCity, "", paddCity, "null", CandidateEMail );
			                 System.out.println(" partyId ---> "+partyId);
			            	 String status = null;
			                 if(partyId != null){
			        		        
			                  	 /*------------------------------------Generating Password-------------------------------------------------*/ 	      
			                  	                	final String AB = "123456789ABCDEFGHIjklmnopqr";
			                  	          		Random rnd = new Random();
			                  	          		int len=10;							
			                  	          		StringBuilder sb = new StringBuilder( len );
			                  	          		   for( int ij = 0; ij < len; ij++ ) {
			                  	          		     sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );			
			                  	          		   }							  
			                  	          	String password=sb.toString();

			                  	          
			                  /*------------------------------------Generating Password-------------------------------------------------*/ 	
			                	 status = ofbizServices.setUserDetailsToOfbiz(password, password, CANID, requirePasswordChange, enabled, partyId, fName+" "+lName, loginUsername, loginPassword); 	 
			                	 System.out.println("currentPassword "+ password+" currentPasswordVerify "+ password+" usrName "+ CANID+" requirePasswordChange "+ 
					        	  			requirePasswordChange+" enabled "+ enabled+" partyId "+  CANID+" userLogin "+ fName+" "+lName+" loginUsername "+ loginUsername+" loginPassword "+	loginPassword);
			                 }
			               
			               
			        	 
			                System.out.println("ofbiz service status "+status);
			                 //send user details to ofbiz ends
						}
			            
			          
			            }
			         }
			  
			  
			       
			         request.setAttribute("CandidateList",CandidateList);

			   conn.disconnect();

			    } catch (MalformedURLException e) {

			   e.printStackTrace();

			    } catch (IOException e) {

			   e.printStackTrace();

			    }
			  return new ModelAndView("requirements/JoinedCandidateList");
			  
			 
			 
	}
	/*----------------------------------------------------------------------------------------------------------------------------------*/
	
	@RequestMapping("/PostCandidateStatus.html")
	public ModelAndView PostCandidateStatus(HttpServletRequest request,
			HttpServletResponse response) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, IOException{
		
		HttpSession session=request.getSession(true);
		
		
		URL url = new URL("https://www.digiblitzonline.com/sp-portal/dBConsult/digiblitz/_vti_bin/ListData.svc/CandidateStatus");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		try {
		    //conn.setReadTimeout(10000);
		    //conn.setConnectTimeout(15000);
		    conn.setRequestMethod("POST");
		    conn.setRequestProperty("Accept", "application/atom+xml");
		    conn.setRequestProperty("content-type", "application/atom+xml");
			//conn.setRequestProperty("Authorization", "Basic R2FuZXNoZGI6R2FuZXNoQDEyMw==");
		    conn.setDoInput(true);
		    conn.setDoOutput(true);
		    String body = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>\n<entry xml:base=\"https://www.digiblitzonline.com/sp-portal/dBConsult/digiblitz/_vti_bin/ListData.svc/\" xmlns:d=\"http://schemas.microsoft.com/ado/2007/08/dataservices\" xmlns:m=\"http://schemas.microsoft.com/ado/2007/08/dataservices/metadata\" m:etag=\"W/&quot;2&quot;\" xmlns=\"http://www.w3.org/2005/Atom\">\n    <id>https://www.digiblitzonline.com/sp-portal/dBConsult/digiblitz/_vti_bin/ListData.svc/CandidateStatus(21)</id>\n    <title type=\"text\">Prabhudb</title>\n    <updated>2016-10-14T20:03:28-06:00</updated>\n    <author>\n        <name />\n    </author>\n    \n    <category term=\"Microsoft.SharePoint.DataService.CandidateStatusItem\" scheme=\"http://schemas.microsoft.com/ado/2007/08/dataservices/scheme\" />\n    <content type=\"application/xml\">\n        <m:properties>\n            \n            <d:CandidateFullName>Amalesh digiblitz</d:CandidateFullName>\n            <d:CandidateStatusValue>Shortlisted</d:CandidateStatusValue>\n           \n        \n            <d:CandidateID m:type=\"Edm.Int32\" m:null=\"true\" />\n            <d:StatusWF m:type=\"Edm.Int32\" m:null=\"true\" />\n            <d:CandidateContactNumber>784-578-1234</d:CandidateContactNumber>\n            <d:RequirementID>dBREQ00683</d:RequirementID>\n            <d:CandidateEMail>amalesh.paul@digiblitz.in</d:CandidateEMail>\n            <d:CandidateNameWF m:type=\"Edm.Int32\" m:null=\"true\" />\n            <d:CandidateContactNumberWF m:type=\"Edm.Int32\" m:null=\"true\" />\n            <d:CandidateEMailWF m:type=\"Edm.Int32\" m:null=\"true\" />\n            <d:RequirementIDWF m:type=\"Edm.Int32\" m:null=\"true\" />\n            <d:CREATEDBYMEWF m:type=\"Edm.Int32\" m:null=\"true\" />\n            <d:CandidateStatusEMailWF m:type=\"Edm.Int32\" m:null=\"true\" />\n            <d:StatusEmailWF m:type=\"Edm.Int32\" m:null=\"true\" />\n           \n            <d:AccountManagerAssignedToValue>Sathya</d:AccountManagerAssignedToValue>\n            <d:RecruiterAssignedToValue>Ganesh</d:RecruiterAssignedToValue>\n            <d:SubmittedById m:type=\"Edm.Int32\">2286</d:SubmittedById>\n          \n            <d:ContentType>Item</d:ContentType>\n            <d:Owshiddenversion m:type=\"Edm.Int32\">2</d:Owshiddenversion>\n           \n            <d:Path>/sp-portal/dBConsult/digiblitz/Lists/CandidateStatus</d:Path>\n        </m:properties>\n    </content>\n</entry>";
		    OutputStream output = new BufferedOutputStream(conn.getOutputStream());
		    output.write(body.getBytes());
		    output.flush();
		    if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
				
				
			}else{
				System.out.println("out------------"+conn.getResponseMessage());
				System.out.println("Candidate Status updateded successfully-------------");
				conn.disconnect();
			    request.setAttribute("canPostStatus", "Success");
			    
			}
		    System.out.println("out------------"+conn.getResponseMessage());
		    return new ModelAndView("requirements/PostCandidateStatusSuccess"); 
		    
		}catch (Exception e) {
			e.printStackTrace();
			conn.disconnect();
			request.setAttribute("canPostStatus", "failed");
			return new ModelAndView("requirements/PostCandidateStatusSuccess"); 
		}		
	}
	
	@RequestMapping("/GetHotListCandidate.html")
	public ModelAndView GetHotListCandidate(HttpServletRequest request,
			HttpServletResponse response) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, IOException, SAXException, ParserConfigurationException{
		try {

			URL url = new URL("https://www.digiblitzonline.com/sp-portal/dBConsult/digiblitz/_vti_bin/ListData.svc/HotListCandidate");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/atom+xml");
			conn.setRequestProperty("Authorization", "Basic c3VyZXNoOkdvbGQxMjM=");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			System.out.println("check....");

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			
		
			/*String output;
		System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);	}
			*/
			
			DocumentBuilderFactory dbFactory 
            = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(conn.getInputStream());
//         Document doc1 = dBuilder.parse(fis);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" 
            + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("entry");
         System.out.println("----------------------------");
       
         String id=null;
         String title=null;
         String ContentTypeID=null;
         String FIRSTNAME=null;
         String PHONE=null;
         String EMAIL=null;
         String TOTALEXPERIENCEValue=null;
         String SKILLS=null;
         String WILLINGNESSFORANINPERSONINTERVIEWATOWNEXPENSEValue=null;
         String EXPERIENCEINUSAValue=null;
         String CURRENTLOCATION=null;
         String RELOCATIONValue=null;
         String PREFERDLOCATION=null;
         String AVAILABILITY=null;
         String RATE=null;
         String VISAAPPROVALValue=null;
         String VISATYPEValue=null;
         String FORMI797AVAILABLEValue=null;
         String FORMI94AVAILABLEValue=null;
         String DOB=null;
         String BESTTIMEFORTELEPHONICINTERVIEW=null;
         String MIDDLENAME=null;
         String LASTNAME=null;
         String EMPLOYERNAME=null;
         String CONTACTPERSON=null;
         String EMPLOYERCONTACTNUMBER=null;
         String EMPLOYERMAILID=null;
         String CANDIDATEUSERNAMEId=null;
         String Id=null;
         
        
         ArrayList HotList=new ArrayList();
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" 
               + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println("inside loop........");
             
  
               id=eElement .getElementsByTagName("id")  .item(0) .getTextContent(); 
               title=eElement .getElementsByTagName("title")  .item(0) .getTextContent();          
               ContentTypeID =eElement .getElementsByTagName("d:ContentTypeID") .item(0) .getTextContent();          
               FIRSTNAME=eElement .getElementsByTagName("d:FIRSTNAME") .item(0)  .getTextContent(); 
               PHONE=eElement .getElementsByTagName("d:PHONE") .item(0) .getTextContent();
               EMAIL= eElement.getElementsByTagName("d:EMAIL").item(0) .getTextContent();
               TOTALEXPERIENCEValue=eElement  .getElementsByTagName("d:TOTALEXPERIENCEValue").item(0) .getTextContent();
               SKILLS=eElement .getElementsByTagName("d:SKILLS").item(0) .getTextContent();      
               WILLINGNESSFORANINPERSONINTERVIEWATOWNEXPENSEValue= eElement .getElementsByTagName("d:WILLINGNESSFORANINPERSONINTERVIEWATOWNEXPENSEValue") .item(0)  .getTextContent();
               EXPERIENCEINUSAValue= eElement .getElementsByTagName("d:EXPERIENCEINUSAValue") .item(0)  .getTextContent();
               CURRENTLOCATION= eElement .getElementsByTagName("d:CURRENTLOCATION") .item(0)  .getTextContent();
               RELOCATIONValue=eElement .getElementsByTagName("d:RELOCATIONValue")  .item(0) .getTextContent();
               PREFERDLOCATION=eElement .getElementsByTagName("d:PREFERDLOCATION") .item(0) .getTextContent();          
               AVAILABILITY= eElement .getElementsByTagName("d:AVAILABILITY") .item(0) .getTextContent();      
               RATE= eElement .getElementsByTagName("d:RATE").item(0) .getTextContent();
               VISAAPPROVALValue= eElement .getElementsByTagName("d:VISAAPPROVALValue") .item(0)  .getTextContent();
               VISATYPEValue= eElement .getElementsByTagName("d:VISATYPEValue") .item(0)  .getTextContent();
               FORMI797AVAILABLEValue= eElement .getElementsByTagName("d:FORMI797AVAILABLEValue").item(0) .getTextContent();
               FORMI797AVAILABLEValue= eElement .getElementsByTagName("d:FORMI797AVAILABLEValue").item(0) .getTextContent();          
               DOB= eElement .getElementsByTagName("d:DOB").item(0) .getTextContent();           
               BESTTIMEFORTELEPHONICINTERVIEW= eElement .getElementsByTagName("d:BESTTIMEFORTELEPHONICINTERVIEW").item(0) .getTextContent();           
               MIDDLENAME= eElement .getElementsByTagName("d:MIDDLENAME").item(0) .getTextContent();
               LASTNAME= eElement .getElementsByTagName("d:LASTNAME").item(0) .getTextContent();
               EMPLOYERNAME= eElement .getElementsByTagName("d:EMPLOYERNAME").item(0) .getTextContent();
               CONTACTPERSON= eElement .getElementsByTagName("d:CONTACTPERSON").item(0) .getTextContent();
               EMPLOYERCONTACTNUMBER= eElement .getElementsByTagName("d:EMPLOYERCONTACTNUMBER").item(0) .getTextContent();
               EMPLOYERMAILID= eElement .getElementsByTagName("d:EMPLOYERMAILID").item(0) .getTextContent(); 
               CANDIDATEUSERNAMEId= eElement .getElementsByTagName("d:CANDIDATEUSERNAMEId").item(0) .getTextContent(); 
               Id= eElement .getElementsByTagName("d:Id").item(0) .getTextContent(); 
             
            }
            String TempList[] = {FIRSTNAME,LASTNAME,PHONE,EMAIL,CURRENTLOCATION,Id };
            request.setAttribute("id",id);
            request.setAttribute("title",title);
            request.setAttribute("FIRSTNAME",FIRSTNAME);
            request.setAttribute("PHONE",PHONE);
            request.setAttribute("EMAIL",EMAIL);
            request.setAttribute("TOTALEXPERIENCEValue",TOTALEXPERIENCEValue);
            request.setAttribute("SKILLS",SKILLS);
            request.setAttribute("WILLINGNESSFORANINPERSONINTERVIEWATOWNEXPENSEValue",WILLINGNESSFORANINPERSONINTERVIEWATOWNEXPENSEValue);
            request.setAttribute("EXPERIENCEINUSAValue",EXPERIENCEINUSAValue);
            request.setAttribute("CURRENTLOCATION",CURRENTLOCATION);
            request.setAttribute("RELOCATIONValue",RELOCATIONValue);
            request.setAttribute("PREFERDLOCATION",PREFERDLOCATION);
            request.setAttribute("AVAILABILITY",AVAILABILITY);
            request.setAttribute("RATE",RATE);
            request.setAttribute("VISAAPPROVALValue",VISAAPPROVALValue);
            request.setAttribute("VISATYPEValue",VISATYPEValue);
            request.setAttribute("FORMI797AVAILABLEValue",FORMI797AVAILABLEValue);
            request.setAttribute("FORMI94AVAILABLEValue",FORMI94AVAILABLEValue);
            request.setAttribute("DOB",DOB);
            request.setAttribute("BESTTIMEFORTELEPHONICINTERVIEW",BESTTIMEFORTELEPHONICINTERVIEW);
            request.setAttribute("MIDDLENAME",MIDDLENAME);
            request.setAttribute("LASTNAME",LASTNAME);
            request.setAttribute("EMPLOYERNAME",EMPLOYERNAME);
            request.setAttribute("CONTACTPERSON",CONTACTPERSON);
            request.setAttribute("EMPLOYERCONTACTNUMBER",EMPLOYERCONTACTNUMBER);
            request.setAttribute("EMPLOYERMAILID",EMPLOYERMAILID);
            request.setAttribute("CANDIDATEUSERNAMEId",CANDIDATEUSERNAMEId);
            request.setAttribute("Id",Id);

            HotList.add(TempList);
         }
		
		
       
         request.setAttribute("HotList",HotList);

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		  return new ModelAndView("requirements/HotListCandidate");
		
	}
	

	@RequestMapping("/GetHotListCandidateByid.html")
	public ModelAndView GetHotListCandidateByid(HttpServletRequest request,
			HttpServletResponse response) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, IOException, SAXException, ParserConfigurationException{
		String value = request.getParameter("id");
		System.out.println("Valuelist"+value);
	try {

		URL url = new URL("https://www.digiblitzonline.com/sp-portal/dBConsult/digiblitz/_vti_bin/ListData.svc/HotListCandidate%28"+value+"%29");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/atom+xml");
		conn.setRequestProperty("Authorization", "Basic c3VyZXNoOkdvbGQxMjM=");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
		System.out.println("check....");

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
		
	
		/*String output;
	System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);	}
		*/
		
		DocumentBuilderFactory dbFactory 
        = DocumentBuilderFactory.newInstance();
     DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
     Document doc = dBuilder.parse(conn.getInputStream());
//     Document doc1 = dBuilder.parse(fis);
     doc.getDocumentElement().normalize();
     System.out.println("Root element :" 
        + doc.getDocumentElement().getNodeName());
     NodeList nList = doc.getElementsByTagName("entry");
     System.out.println("----------------------------");
   
     String id=null;
     String title=null;
     String ContentTypeID=null;
     String FIRSTNAME=null;
     String PHONE=null;
     String EMAIL=null;
     String TOTALEXPERIENCEValue=null;
     String SKILLS=null;
     String WILLINGNESSFORANINPERSONINTERVIEWATOWNEXPENSEValue=null;
     String EXPERIENCEINUSAValue=null;
     String CURRENTLOCATION=null;
     String RELOCATIONValue=null;
     String PREFERDLOCATION=null;
     String AVAILABILITY=null;
     String RATE=null;
     String VISAAPPROVALValue=null;
     String VISATYPEValue=null;
     String FORMI797AVAILABLEValue=null;
     String FORMI94AVAILABLEValue=null;
     String DOB=null;
     String BESTTIMEFORTELEPHONICINTERVIEW=null;
     String MIDDLENAME=null;
     String LASTNAME=null;
     String EMPLOYERNAME=null;
     String CONTACTPERSON=null;
     String EMPLOYERCONTACTNUMBER=null;
     String EMPLOYERMAILID=null;
     String CANDIDATEUSERNAMEId=null;
     String Id=null;
    
    
     ArrayList HotList=new ArrayList();
     for (int temp = 0; temp < nList.getLength(); temp++) {
        Node nNode = nList.item(temp);
        System.out.println("\nCurrent Element :" 
           + nNode.getNodeName());
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
           Element eElement = (Element) nNode;
           System.out.println("inside loop........");
         

           id=eElement .getElementsByTagName("id")  .item(0) .getTextContent(); 
           title=eElement .getElementsByTagName("title")  .item(0) .getTextContent();          
           ContentTypeID =eElement .getElementsByTagName("d:ContentTypeID") .item(0) .getTextContent();          
           FIRSTNAME=eElement .getElementsByTagName("d:FIRSTNAME") .item(0)  .getTextContent(); 
           PHONE=eElement .getElementsByTagName("d:PHONE") .item(0) .getTextContent();
           EMAIL= eElement.getElementsByTagName("d:EMAIL").item(0) .getTextContent();
           TOTALEXPERIENCEValue=eElement  .getElementsByTagName("d:TOTALEXPERIENCEValue").item(0) .getTextContent();
           SKILLS=eElement .getElementsByTagName("d:SKILLS").item(0) .getTextContent();      
           WILLINGNESSFORANINPERSONINTERVIEWATOWNEXPENSEValue= eElement .getElementsByTagName("d:WILLINGNESSFORANINPERSONINTERVIEWATOWNEXPENSEValue") .item(0)  .getTextContent();
           EXPERIENCEINUSAValue= eElement .getElementsByTagName("d:EXPERIENCEINUSAValue") .item(0)  .getTextContent();
           CURRENTLOCATION= eElement .getElementsByTagName("d:CURRENTLOCATION") .item(0)  .getTextContent();
           RELOCATIONValue=eElement .getElementsByTagName("d:RELOCATIONValue")  .item(0) .getTextContent();
           PREFERDLOCATION=eElement .getElementsByTagName("d:PREFERDLOCATION") .item(0) .getTextContent();          
           AVAILABILITY= eElement .getElementsByTagName("d:AVAILABILITY") .item(0) .getTextContent();      
           RATE= eElement .getElementsByTagName("d:RATE").item(0) .getTextContent();
           VISAAPPROVALValue= eElement .getElementsByTagName("d:VISAAPPROVALValue") .item(0)  .getTextContent();
           VISATYPEValue= eElement .getElementsByTagName("d:VISATYPEValue") .item(0)  .getTextContent();
           FORMI797AVAILABLEValue= eElement .getElementsByTagName("d:FORMI797AVAILABLEValue").item(0) .getTextContent();
           FORMI797AVAILABLEValue= eElement .getElementsByTagName("d:FORMI797AVAILABLEValue").item(0) .getTextContent();          
           DOB= eElement .getElementsByTagName("d:DOB").item(0) .getTextContent();           
           BESTTIMEFORTELEPHONICINTERVIEW= eElement .getElementsByTagName("d:BESTTIMEFORTELEPHONICINTERVIEW").item(0) .getTextContent();           
           MIDDLENAME= eElement .getElementsByTagName("d:MIDDLENAME").item(0) .getTextContent();
           LASTNAME= eElement .getElementsByTagName("d:LASTNAME").item(0) .getTextContent();
           EMPLOYERNAME= eElement .getElementsByTagName("d:EMPLOYERNAME").item(0) .getTextContent();
           CONTACTPERSON= eElement .getElementsByTagName("d:CONTACTPERSON").item(0) .getTextContent();
           EMPLOYERCONTACTNUMBER= eElement .getElementsByTagName("d:EMPLOYERCONTACTNUMBER").item(0) .getTextContent();
           EMPLOYERMAILID= eElement .getElementsByTagName("d:EMPLOYERMAILID").item(0) .getTextContent(); 
           CANDIDATEUSERNAMEId= eElement .getElementsByTagName("d:CANDIDATEUSERNAMEId").item(0) .getTextContent(); 
           Id= eElement .getElementsByTagName("d:Id").item(0) .getTextContent();
          
 	                 
 	                  
 	                  
           
        }
        String TempList[] = {FIRSTNAME,PHONE,EMAIL,TOTALEXPERIENCEValue,SKILLS,WILLINGNESSFORANINPERSONINTERVIEWATOWNEXPENSEValue,EXPERIENCEINUSAValue,CURRENTLOCATION,RELOCATIONValue,
                PREFERDLOCATION,AVAILABILITY,RATE,VISAAPPROVALValue,VISATYPEValue,FORMI797AVAILABLEValue,FORMI94AVAILABLEValue,DOB,BESTTIMEFORTELEPHONICINTERVIEW,MIDDLENAME,
                LASTNAME,EMPLOYERNAME,CONTACTPERSON,EMPLOYERCONTACTNUMBER,EMPLOYERMAILID,CANDIDATEUSERNAMEId,Id };
        request.setAttribute("id",id);
        request.setAttribute("title",title);
        request.setAttribute("FIRSTNAME",FIRSTNAME);
        request.setAttribute("PHONE",PHONE);
        request.setAttribute("EMAIL",EMAIL);
        request.setAttribute("TOTALEXPERIENCEValue",TOTALEXPERIENCEValue);
        request.setAttribute("SKILLS",SKILLS);
        request.setAttribute("WILLINGNESSFORANINPERSONINTERVIEWATOWNEXPENSEValue",WILLINGNESSFORANINPERSONINTERVIEWATOWNEXPENSEValue);
        request.setAttribute("EXPERIENCEINUSAValue",EXPERIENCEINUSAValue);
        request.setAttribute("CURRENTLOCATION",CURRENTLOCATION);
        request.setAttribute("RELOCATIONValue",RELOCATIONValue);
        request.setAttribute("PREFERDLOCATION",PREFERDLOCATION);
        request.setAttribute("AVAILABILITY",AVAILABILITY);
        request.setAttribute("RATE",RATE);
        request.setAttribute("VISAAPPROVALValue",VISAAPPROVALValue);
        request.setAttribute("VISATYPEValue",VISATYPEValue);
        request.setAttribute("FORMI797AVAILABLEValue",FORMI797AVAILABLEValue);
        request.setAttribute("FORMI94AVAILABLEValue",FORMI94AVAILABLEValue);
        request.setAttribute("DOB",DOB);
        request.setAttribute("BESTTIMEFORTELEPHONICINTERVIEW",BESTTIMEFORTELEPHONICINTERVIEW);
        request.setAttribute("MIDDLENAME",MIDDLENAME);
        request.setAttribute("LASTNAME",LASTNAME);
        request.setAttribute("EMPLOYERNAME",EMPLOYERNAME);
        request.setAttribute("CONTACTPERSON",CONTACTPERSON);
        request.setAttribute("EMPLOYERCONTACTNUMBER",EMPLOYERCONTACTNUMBER);
        request.setAttribute("EMPLOYERMAILID",EMPLOYERMAILID);
        request.setAttribute("CANDIDATEUSERNAMEId",CANDIDATEUSERNAMEId);
        request.setAttribute("Id",Id);

        HotList.add(TempList);
     }
	
	
   
     request.setAttribute("Hotlist",HotList);

		conn.disconnect();

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }
	return new ModelAndView("requirements/HotListCandidateDetails");
	
}
	
	@RequestMapping("/GetFreshCandidateList.html")
	public ModelAndView GetFreshCandidateList(HttpServletRequest request,
			HttpServletResponse response) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, IOException, SAXException, ParserConfigurationException{
		try {

			URL url = new URL("https://www.digiblitzonline.com/sp-portal/dBConsult/digiblitz/_vti_bin/ListData.svc/FreshCandidate");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/atom+xml");
			conn.setRequestProperty("Authorization", "Basic c3VyZXNoOkdvbGQxMjM=");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			System.out.println("check....");

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			
		
			/*String output;
		System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);	}
			*/
			
			DocumentBuilderFactory dbFactory 
            = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(conn.getInputStream());
//         Document doc1 = dBuilder.parse(fis);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" 
            + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("entry");
         System.out.println("----------------------------");
       
         String id=null;
         String title=null;
         String ContentTypeID=null;
         String Name=null;
         String VisaType=null;
         String MobileNumber=null;
         String Email=null;
         String Comments=null;
         String Status=null;
         String RecruitedBy=null;
         String Id=null;
         String ContentType=null;
         String Modified=null;
         String Created=null;
         
        
        
         ArrayList FreshCandidate=new ArrayList();
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" 
               + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println("inside loop........");
             
  
               id=eElement .getElementsByTagName("id")  .item(0) .getTextContent(); 
               title=eElement .getElementsByTagName("title")  .item(0) .getTextContent();          
               ContentTypeID =eElement .getElementsByTagName("d:ContentTypeID") .item(0) .getTextContent();          
               Name=eElement .getElementsByTagName("d:Name") .item(0)  .getTextContent(); 
               VisaType=eElement .getElementsByTagName("d:VisaType") .item(0) .getTextContent();
               MobileNumber= eElement.getElementsByTagName("d:MobileNumber").item(0) .getTextContent();
               Email=eElement  .getElementsByTagName("d:Email").item(0) .getTextContent();
               Comments=eElement .getElementsByTagName("d:Comments").item(0) .getTextContent();      
               Status= eElement .getElementsByTagName("d:Status") .item(0)  .getTextContent();
               RecruitedBy= eElement .getElementsByTagName("d:RecruitedBy") .item(0)  .getTextContent();
               Modified= eElement .getElementsByTagName("d:Modified") .item(0)  .getTextContent();
               Created=eElement .getElementsByTagName("d:Created")  .item(0) .getTextContent();
               Id=eElement .getElementsByTagName("d:Id") .item(0) .getTextContent();          
               
               ContentType= eElement .getElementsByTagName("d:ContentType") .item(0)  .getTextContent();
               
              
	 	                 
	 	                  
	 	                  
               
            }
            String TempList[] = {Name,MobileNumber,Email,Status,Id};
            request.setAttribute("id",id);
            request.setAttribute("title",title);
            request.setAttribute("ContentTypeID",ContentTypeID);
            request.setAttribute("Name",Name);
            request.setAttribute("VisaType",VisaType);
            request.setAttribute("MobileNumber",MobileNumber);
            request.setAttribute("Email",Email);
            request.setAttribute("Comments",Comments);
            request.setAttribute("Status",Status);
            request.setAttribute("RecruitedBy",RecruitedBy);
            request.setAttribute("Modified",Modified);
            request.setAttribute("Created",Created);
            
            request.setAttribute("Id",Id);
            request.setAttribute("ContentType",ContentType);

            FreshCandidate.add(TempList);
         }
		
		
       
         request.setAttribute("FreshCandidate",FreshCandidate);
         System.out.println("checksize-----"+FreshCandidate.size());

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		return new ModelAndView("requirements/FreshCandidateList");
		
	}
	
	@RequestMapping("/getFreshCandidateByid.html")
	public ModelAndView getFreshCandidateByid(HttpServletRequest request,
			HttpServletResponse response) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, IOException, SAXException, ParserConfigurationException{
try {
			
			String value = request.getParameter("id");
			System.out.println("Valuelist"+value);

			URL url = new URL("https://www.digiblitzonline.com/sp-portal/dBConsult/digiblitz/_vti_bin/ListData.svc/FreshCandidate%28"+value+"%29");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/atom+xml");
			conn.setRequestProperty("Authorization", "Basic c3VyZXNoOkdvbGQxMjM=");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			System.out.println("check....");

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			
		
			/*String output;
		System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);	}
			*/
			
			DocumentBuilderFactory dbFactory 
            = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(conn.getInputStream());
//         Document doc1 = dBuilder.parse(fis);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" 
            + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("entry");
         System.out.println("----------------------------");
       
         String id=null;
         String title=null;
         String ContentTypeID=null;
         String Name=null;
         String VisaType=null;
         String MobileNumber=null;
         String Email=null;
         String Comments=null;
         String Status=null;
         String RecruitedBy=null;
         String Id=null;
         String ContentType=null;
         String Modified=null;
         String Created=null;
         
        
        
         ArrayList CandidateList=new ArrayList();
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" 
               + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println("inside loop........");
             
  
               id=eElement .getElementsByTagName("id")  .item(0) .getTextContent(); 
               title=eElement .getElementsByTagName("title")  .item(0) .getTextContent();          
               ContentTypeID =eElement .getElementsByTagName("d:ContentTypeID") .item(0) .getTextContent();          
               Name=eElement .getElementsByTagName("d:Name") .item(0)  .getTextContent(); 
               VisaType=eElement .getElementsByTagName("d:VisaType") .item(0) .getTextContent();
               MobileNumber= eElement.getElementsByTagName("d:MobileNumber").item(0) .getTextContent();
               Email=eElement  .getElementsByTagName("d:Email").item(0) .getTextContent();
               Comments=eElement .getElementsByTagName("d:Comments").item(0) .getTextContent();      
               Status= eElement .getElementsByTagName("d:Status") .item(0)  .getTextContent();
               RecruitedBy= eElement .getElementsByTagName("d:RecruitedBy") .item(0)  .getTextContent();
               Modified= eElement .getElementsByTagName("d:Modified") .item(0)  .getTextContent();
               Created=eElement .getElementsByTagName("d:Created")  .item(0) .getTextContent();
               Id=eElement .getElementsByTagName("d:Id") .item(0) .getTextContent();          
               
               ContentType= eElement .getElementsByTagName("d:ContentType") .item(0)  .getTextContent();
               
              
	 	                 
	 	                  
	 	                  
               
            }
            String TempList[] = {Name,Email,MobileNumber,VisaType,Comments,Status};
            request.setAttribute("id",id);
            request.setAttribute("title",title);
            request.setAttribute("ContentTypeID",ContentTypeID);
            request.setAttribute("Name",Name);
            request.setAttribute("VisaType",VisaType);
            request.setAttribute("MobileNumber",MobileNumber);
            request.setAttribute("Email",Email);
            request.setAttribute("Comments",Comments);
            request.setAttribute("Status",Status);
            request.setAttribute("RecruitedBy",RecruitedBy);
            request.setAttribute("Modified",Modified);
            request.setAttribute("Created",Created);
            
            request.setAttribute("Id",Id);
            request.setAttribute("ContentType",ContentType);

            CandidateList.add(TempList);
         }
		
		
       
         request.setAttribute("CandidateList",CandidateList);

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		return new ModelAndView("requirements/FreshCandidate");
		
	}
	
	@RequestMapping("/ResumeDownload.html")
	public ModelAndView ResumeDownloadByUniqueId(HttpServletRequest request,
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
		
		Debug.print("SPApplyJobRequirement.ResumeDownloadByUniqueId()");
		String CanDetailsByUniqueId = null;
		CanDetailsByUniqueId = request.getParameter("uniqueId");
		String resumePath = null;
        ArrayList AppliedCanDetailsList = new ArrayList();
        AppliedCanDetailsList = (ArrayList)db.getCanDetailsByUniqueId(CanDetailsByUniqueId);
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
	
	@RequestMapping("/RequirementMails.html")
	public ModelAndView RequirementMails(HttpServletRequest request,
			HttpServletResponse response) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, IOException, SAXException, ParserConfigurationException{
		try {
			//Date date = new Date();
		    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    String currentdate=dateFormat.format(new Date());
		    System.out.println(currentdate);
		   

			URL url = new URL("https://www.digiblitzonline.com/sp-portal/dBConsult/digiblitz/_vti_bin/ListData.svc/RequirementMails?$filter=Created%20eq%20DateTime%27"+currentdate+"%27");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/atom+xml");
			conn.setRequestProperty("Authorization", "Basic c3VyZXNoOkdvbGQxMjM=");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			System.out.println("check....");

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			
		
			/*String output;
		System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);	}
			*/
			
			DocumentBuilderFactory dbFactory 
            = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(conn.getInputStream());
//         Document doc1 = dBuilder.parse(fis);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" 
            + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("entry");
         System.out.println("----------------------------");
       
         String id=null;
         String title=null;
         String ContentTypeID=null;
       
         String Name=null;
         String Title=null;
         String EMailSender=null;
         String EMailTo=null;
         String EMailCc=null;
         String EMailFrom=null;
         String EMailSubject=null;
         String Id=null;
         String ContentType=null;
         String Modified=null;
         String Created=null;
         String CopySource=null;
         String VirusStatus=null;
         String download=null;
        
        
         ArrayList req_mail=new ArrayList();
         
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" 
               + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println("inside loop........");
              
             
               download=eElement .getElementsByTagName("content")
		                  .item(0)
		                   .getAttributes().getNamedItem("src").getNodeValue();
	               System.out.println("Document : " 
	                  + download);
               id=eElement .getElementsByTagName("id")  .item(0) .getTextContent(); 
               title=eElement .getElementsByTagName("title")  .item(0) .getTextContent();          
               ContentTypeID =eElement .getElementsByTagName("d:ContentTypeID") .item(0) .getTextContent();          
               Name=eElement .getElementsByTagName("d:Name") .item(0)  .getTextContent(); 
               Title=eElement .getElementsByTagName("d:Title") .item(0) .getTextContent();
               EMailSender= eElement.getElementsByTagName("d:EMailSender").item(0) .getTextContent();
               EMailTo=eElement  .getElementsByTagName("d:EMailTo").item(0) .getTextContent();
               EMailCc=eElement .getElementsByTagName("d:EMailCc").item(0) .getTextContent();      
               EMailFrom= eElement .getElementsByTagName("d:EMailFrom") .item(0)  .getTextContent();
               EMailSubject= eElement .getElementsByTagName("d:EMailSubject") .item(0)  .getTextContent();
               Modified= eElement .getElementsByTagName("d:Modified") .item(0)  .getTextContent();
               Created=eElement .getElementsByTagName("d:Created")  .item(0) .getTextContent();
               Id=eElement .getElementsByTagName("d:Id") .item(0) .getTextContent();          
               
               ContentType= eElement .getElementsByTagName("d:ContentType") .item(0)  .getTextContent();
               CopySource=eElement .getElementsByTagName("d:CopySource") .item(0) .getTextContent(); 
               VirusStatus=eElement .getElementsByTagName("d:VirusStatus") .item(0) .getTextContent(); 
                         
	 	                  
	 	                  
               
            }
            String TempList[] = {Id,ContentType,Name,Modified,download };
            request.setAttribute("id",id);
            request.setAttribute("title",title);
            request.setAttribute("ContentTypeID",ContentTypeID);
            request.setAttribute("Name",Name);
            request.setAttribute("Title",Title);
            request.setAttribute("EMailSender",EMailSender);
            request.setAttribute("EMailTo",EMailTo);
            request.setAttribute("EMailCc",EMailCc);
            request.setAttribute("EMailFrom",EMailFrom);
            request.setAttribute("EMailSubject",EMailSubject);
            request.setAttribute("Modified",Modified);
            request.setAttribute("Created",Created);
            request.setAttribute("download",download);
            request.setAttribute("Id",Id);
            request.setAttribute("ContentType",ContentType);
            request.setAttribute("CopySource",CopySource);
            request.setAttribute("VirusStatus",VirusStatus);
           
            req_mail.add(TempList);
            
         }
        
         Map<Integer, ArrayList> map = new HashMap<Integer, ArrayList>();
         System.out.println("checksize-----"+req_mail.size());
   	//  ArrayList req_mail = new ArrayList();
        
      	 
      	  
   	  ArrayList al = new ArrayList();
   	  int i = 1;
   	  int j = 50;
   	  int count = 1;
   	  
   	  Iterator itr = req_mail.iterator();
   	  while(itr.hasNext()){
   	   String[] tempI =  (String[]) itr.next();
   	 
   	   al.add(tempI);
   	   
   	   if(i == j){    
   	    map.put(count, al);
   	    j=j+50;
   	    count = count+1;
   	    al = new ArrayList();
   	    
   	   }
   	   i++; 
   	  }
   	  System.out.println("al size --- "+al.size());
   	  System.out.println("map size --- "+map.size());
   	 
   	  
   	  int key = 0;
   	int pagenumber=1;
   	 
   	String page = (String) request.getParameter("pn");
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
   	   
   	  // Iterator itrr = al.iterator();
   	//   while (itrr.hasNext()) {
   	 //  System.out.println("itr "+itrr.next());
   	//  }
   	  //String [] array = (String[]) res.toArray();
   	  
   	  //System.out.println(array);
   	  
   	 
        
		
      
        // request.setAttribute("arraysize", String.valueOf(arraysize));
   	   request.setAttribute("pNo", String.valueOf(count));
 	   request.setAttribute("pagenumber", String.valueOf(pagenumber));
        // request.setAttribute("req_mail",req_mail);
 	   request.setAttribute("req_mail",al);
 	   request.setAttribute("page", "RequirementMails");

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		return new ModelAndView("requirements/RequirementMail");
		
	}
	
	@RequestMapping("/RequirementMailsByDate.html")
	public ModelAndView RequirementMailsByDate(HttpServletRequest request,
			HttpServletResponse response) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, IOException, SAXException, ParserConfigurationException{
		try {
			
		    String fromdate =(String)request.getParameter("fromdate"); 
		    String todate =(String)request.getParameter("todate"); 
		   
		    URL url = new URL("https://www.digiblitzonline.com/sp-portal/dBConsult/digiblitz/_vti_bin/ListData.svc/RequirementMails?$filter=%28Created%20ge%20datetime%27"+fromdate+"%27%29%20and%20%28Created%20le%20datetime%27"+todate+"%27%29");		
		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/atom+xml");
			conn.setRequestProperty("Authorization", "Basic c3VyZXNoOkdvbGQxMjM=");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			System.out.println("check....");

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			
		
			/*String output;
		System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);	}
			*/
			
			DocumentBuilderFactory dbFactory 
            = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(conn.getInputStream());
//         Document doc1 = dBuilder.parse(fis);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" 
            + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("entry");
         System.out.println("----------------------------");
       
         String id=null;
         String title=null;
         String ContentTypeID=null;
       
         String Name=null;
         String Title=null;
         String EMailSender=null;
         String EMailTo=null;
         String EMailCc=null;
         String EMailFrom=null;
         String EMailSubject=null;
         String Id=null;
         String ContentType=null;
         String Modified=null;
         String Created=null;
         String CopySource=null;
         String VirusStatus=null;
         String download=null;
        
        
         ArrayList req_mail=new ArrayList();
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" 
               + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println("inside loop........");
             
               download=eElement .getElementsByTagName("content")
		                  .item(0)
		                   .getAttributes().getNamedItem("src").getNodeValue();
	               System.out.println("Document : " 
	                  + download);
               id=eElement .getElementsByTagName("id")  .item(0) .getTextContent(); 
               title=eElement .getElementsByTagName("title")  .item(0) .getTextContent();          
               ContentTypeID =eElement .getElementsByTagName("d:ContentTypeID") .item(0) .getTextContent();          
               Name=eElement .getElementsByTagName("d:Name") .item(0)  .getTextContent(); 
               Title=eElement .getElementsByTagName("d:Title") .item(0) .getTextContent();
               EMailSender= eElement.getElementsByTagName("d:EMailSender").item(0) .getTextContent();
               EMailTo=eElement  .getElementsByTagName("d:EMailTo").item(0) .getTextContent();
               EMailCc=eElement .getElementsByTagName("d:EMailCc").item(0) .getTextContent();      
               EMailFrom= eElement .getElementsByTagName("d:EMailFrom") .item(0)  .getTextContent();
               EMailSubject= eElement .getElementsByTagName("d:EMailSubject") .item(0)  .getTextContent();
               Modified= eElement .getElementsByTagName("d:Modified") .item(0)  .getTextContent();
               Created=eElement .getElementsByTagName("d:Created")  .item(0) .getTextContent();
               Id=eElement .getElementsByTagName("d:Id") .item(0) .getTextContent();          
               
               ContentType= eElement .getElementsByTagName("d:ContentType") .item(0)  .getTextContent();
               CopySource=eElement .getElementsByTagName("d:CopySource") .item(0) .getTextContent(); 
               VirusStatus=eElement .getElementsByTagName("d:VirusStatus") .item(0) .getTextContent(); 
                         
              
	 	                  
               
            }
            String TempList[] = {Id,ContentType,Name,Modified,download };
            request.setAttribute("id",id);
            request.setAttribute("title",title);
            request.setAttribute("ContentTypeID",ContentTypeID);
            request.setAttribute("Name",Name);
            request.setAttribute("Title",Title);
            request.setAttribute("EMailSender",EMailSender);
            request.setAttribute("EMailTo",EMailTo);
            request.setAttribute("EMailCc",EMailCc);
            request.setAttribute("EMailFrom",EMailFrom);
            request.setAttribute("EMailSubject",EMailSubject);
            request.setAttribute("Modified",Modified);
            request.setAttribute("Created",Created);
            request.setAttribute("download",download);
            request.setAttribute("Id",Id);
            request.setAttribute("ContentType",ContentType);
            request.setAttribute("CopySource",CopySource);
            request.setAttribute("VirusStatus",VirusStatus);

            req_mail.add(TempList);
         }
        
		
         Map<Integer, ArrayList> map = new HashMap<Integer, ArrayList>();
         System.out.println("checksize-----"+req_mail.size());
   	//  ArrayList req_mail = new ArrayList();
        
      	 
      	  
   	  ArrayList al = new ArrayList();
   	  int i = 1;
   	  int j = 50;
   	  int count = 1;
   	  
   	  Iterator itr = req_mail.iterator();
   	  while(itr.hasNext()){
   	   String[] tempI =  (String[]) itr.next();
   	 
   	   al.add(tempI);
   	   
   	   if(i == j){    
   	    map.put(count, al);
   	    j=j+50;
   	    count = count+1;
   	    al = new ArrayList();
   	    
   	   }
   	   i++; 
   	  }
   	  System.out.println("al size --- "+al.size());
   	  System.out.println("map size --- "+map.size());
   	 
   	  
   	  int key = 0;
   	int pagenumber=1;
   	 
   	String page = (String) request.getParameter("pn");
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
   	   
   	  // Iterator itrr = al.iterator();
   	//   while (itrr.hasNext()) {
   	 //  System.out.println("itr "+itrr.next());
   	//  }
   	  //String [] array = (String[]) res.toArray();
   	  
   	  //System.out.println(array);
   	  
   	 
        
		
      request.setAttribute("status", "withdate");
        // request.setAttribute("arraysize", String.valueOf(arraysize));
   	   request.setAttribute("pNo", String.valueOf(count));
 	   request.setAttribute("pagenumber", String.valueOf(pagenumber));
         request.setAttribute("req_mail",al);
         System.out.println("checksize-----"+req_mail.size());
         
         request.setAttribute("page", "RequirementMailsByDate");
         request.setAttribute("fromdate", fromdate);
         request.setAttribute("todate", todate);
        

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		return new ModelAndView("requirements/RequirementMail");
		
	}
	
	@RequestMapping("/SearchCandidateStatus.html")
	public ModelAndView SearchCandidateStatus(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		 String status =null;
		 String reqid =null;
		 String availability=null;
		 String experience=null;
		 String location=null;
		 String formstatus=null;
		 
		Debug.print("RequirementsAction.SearchCandidateStatus()");
		 status =(String)request.getParameter("Search_req_status"); 
	     reqid =(String)request.getParameter("Search_req_id"); 
	     availability=(String)request.getParameter("Search_Availability");
	     experience=(String)request.getParameter("Search_Exp");
	     location=(String)request.getParameter("Search_Location");
	     formstatus = (String)request.getParameter("viewDetails");
	    System.out.println("RequirementId for searching====="+reqid);
	    System.out.println("Status for searching====="+status);

		String canDetailsByReqId = null;
		canDetailsByReqId = request.getParameter("reqid");
		System.out.println("tresting"+canDetailsByReqId);
        ArrayList listCandidateStatus = new ArrayList();
        listCandidateStatus = (ArrayList)db.getCanDetailsByRequirementID(reqid,status,availability,experience,location);
        
        String currentPageNo = (String)request.getParameter("currentPageNo");
        
        listCandidateStatus = doPagination(listCandidateStatus,currentPageNo);

       
        System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
        request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
        System.out.println("currentPageNo------------------>"+currentPageNo);
	 	request.setAttribute("currentPageNo", currentPageNo);
       
        request.setAttribute("pageStatus","Search");
        
        request.setAttribute("Search_req_status",status);
        request.setAttribute("Search_req_id",reqid);
        if(formstatus != null && formstatus.equalsIgnoreCase("viewdetails")){
        	 request.setAttribute("allAppliedCandidateList",null);
             request.setAttribute("allAppliedCandidateList",listCandidateStatus);
        	return new ModelAndView("requirements/listAppliedCandidateList");
        }else if(formstatus != null && formstatus.equalsIgnoreCase("updatestatus")){
        	 request.setAttribute("listCandidateStatus",null);
             request.setAttribute("listCandidateStatus",listCandidateStatus);
        	return new ModelAndView("requirements/listCandidateStatus");
        }
		return null;
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
}

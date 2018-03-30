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
package com.user.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ProtocolException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.xmlrpc.XmlRpcException;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.AD.action.NewUser;
import com.db.GeneralDBAction;
import com.hlccommon.util.Debug;
import com.hlcform.stateless.HLCkaverystatelessBean;
import com.hlcform.util.HLCContactDetails;
import com.hlcform.util.HLCMemberDetails;
import com.hlcform.util.HLCPaymentDetails;
import com.hlcform.util.HLCUserMaster;
import com.infusionejb.session.InfusionSessionBean;
import com.mee.action.UsrMeetListAction;
import com.mysql.dao.MySQLDAO;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import com.util.email.MailMail;

//@Controller
//@RequestMapping("/HelloPrabhu")
public class MfUserRoleAction implements Controller {
	
	 Properties properties;
	 
	 static Logger log = Logger.getLogger(MfUserRoleAction.class.getName());
		
		public static final String LOG_FILE = "Log4j.properties";
		InputStream is;
		
		 private InfusionSessionBean obj1;
			{
				try{
					obj1 = new InfusionSessionBean();
				}catch(Exception e){
					 throw new RuntimeException(e);
				}
			}
	 
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,ProtocolException,
			IOException, ParserConfigurationException, SAXException {
		
		// ======================log file properties configuration started====================
	       Properties logProp = new Properties();  
		      is = (InputStream) getClass().getClassLoader().getResourceAsStream(LOG_FILE);
		      logProp.load(is); 
		      PropertyConfigurator.configure(logProp);      
		      log.info("Logging enabled");  
		   // ======================log file properties configuration ended==================== 
		
		
		/*
		 * *===================Property file loaded here======================================
		 */
		/** Creates a new instance of EmailEngine */
        properties = new Properties();        
       // String fileName = System.getProperty("user.home")+File.separator+"companyDetails.properties";
       // Debug.print("FileName path::::::::::::::::::::::::::::::"+fileName);
        //InputStream in = this.getClass().getClassLoader().getResourceAsStream("/companyDetails.properties");
        try {               
               // properties.load(new FileInputStream(fileName));
        	//properties.load(KlgLoginAction.class.getResourceAsStream("/companyDetails.properties"));
        	properties.load(KlgUserAction.class.getResourceAsStream("/config.properties"));
        	
        	properties.load(getClass().getClassLoader().getResourceAsStream("/infusionMail.properties"));
        	
        	//properties.load(KlgLoginAction.class.getResourceAsStream("/paypal.properties"));
        	System.out.println("Property file loaded successfully");
            } catch (Exception e) {
                try {
                  //properties.load(new FileInputStream("src/companyDetails.properties"));
                  properties.load(new FileInputStream("/config.properties"));
                }catch(Exception ee) {
                   Debug.print("Could not load the config.properties");
                }
            }
		
		
		GeneralDBAction db=new GeneralDBAction();
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        Crypt ccc = new Crypt();
		HLCkaverystatelessBean humanMemb=new HLCkaverystatelessBean();
		
		String usrRoleProcess=request.getParameter("cmd");
		String source = request.getParameter("source");
		if(usrRoleProcess.equals("usrRoleReg")){
			 String encryptUserPass= null;
	          String encryptfNameLname= null;
			 try {
        		 
	        		// /*------------------------------------PRABHU START HERE-------------------------------------------------/
	               /*  String username = request.getParameter("usrname");
	                 String firstname = request.getParameter("fname");
	                 String lastname = request.getParameter("lname");
	                 String usrPassword = request.getParameter("cpwd");*/
				 String usrSalutation = null;
	        	 String fName = null;
	        	 String mName = null;
	        	 String lName = null;
	        	 String sName = null;
	        	 String usrDOB = null;
	        	 String gender = null;
	        	 String usrName = null;
	        	 String usrEmail = null;
	        	 String usrPwd = null;
	        	 String usrCnfPwd = null;
	        	 String usrCompanyDetails = null;
	        	 String usrCategory = null;
	        	 String secretQuestion = null;
	        	 String secretAns = null;
	        	 String paddPlotNo = null;
	        	 String paddBuildNo = null;
	        	 String paddFloorNo = null;
	        	 String paddStreetNo = null;
	        	 String paddLane = null;
	        	 String paddArea = null;
	        	 String paddCity = null;
	        	 String paddCountry = null;
	        	 String paddState = null;
	        	 String paddZip = null;
	        	 String paddCntryPhn = null;
	        	 String paddAreaPhone = null;
	        	 String paddNoPhone = null;
	        	 String paddCntryMob = null;
	        	 String paddNoMob = null;
	        	 String paddCntryFax = null;
	        	 String paddAreaFax = null;
	        	 String paddNoFax = null;
	        	 String secAddrReqOrNot = null;
	        	 String saddPlotBuildFloor = null;
	        	 String saddStreet = null;
	        	 String saddLane = null;
	        	 String saddArea = null;
	        	 String saddCity = null;
	        	 String saddCountry = null;
	        	 String saddState = null;
	        	 String saddZip = null;
	        	 String saddCntryPhone = null;
	        	 String saddAreaPhone = null;
	        	 String saddNoPhone = null;
	        	 String saddCntryMob = null;
	        	 String saddNoMob = null;
	        	 String saddCntryFax = null;
	        	 String saddAreaFax = null;
	        	 String saddNoFax = null;
	        	 String PreferredAddrType = null;
	        	 String NonUseaEmail = null;
	        	 String NonUseaMail = null;
	        	 String fileLocation = null;
	        
	        	 

	     
	  	                	 
	  	                	
	  	                	 System.out.println("1");
	  	                	 System.out.println("2");
	  	                	 
	  	                	 String fileSavePath;
	  	        			 String UPLOAD_DIRECTORY ="D://MenschForceLiveDocs//UsersProfilePics//";
	  	        			 List<FilePart> fileList = new ArrayList<FilePart>();
	  	      
	  	                	 
	  	                	//Multiple file upload Start here-----------------------------------------------	
	  	        			String parmName = "";

	  	        			String factlist[] = null;
	  	        			List<String> factList = new ArrayList<String>();
	  	        			 fileSavePath =  UPLOAD_DIRECTORY;/*save uploaded files to a 'Upload' directory in the web app*/
	  	        		        if (!(new File(fileSavePath)).exists()) {
	  	        		            (new File(fileSavePath)).mkdir();    // creates the directory if it does not exist        
	  	        		        }
	  	        		    
	  	        		        String resp = "";
	  	        		        int i = 1;
	  	        		        
	  	        		        resp += "<br>Here is information about uploaded files.<br>";
	  	        		        try {
	  	        		            MultipartParser parser = new MultipartParser(request, 1024 * 1024 * 1024);  /* file limit size of 1GB*/
	  	        		            Part _part;
	  	        		            int k=0;
	  	        		            while ((_part = parser.readNextPart()) != null) {
	  	        		            	System.out.println("LOOP START"+_part.getName());
	  	        		            	parmName = _part.getName();
	  	        		            	
	  	        		                if(_part.isParam()){
	  	        		                	System.out.println("Inside paramPart");
	  	        		                	ParamPart paramPart = (ParamPart)_part;
	  	        		                	if(parmName.equals("USelect")){
	  	        		                		usrSalutation = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("fname")){
	  	        		                		fName = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("mname")){
	  	        		                		mName = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("lname")){
	  	        		                		lName = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("sname")){
	  	        		                		sName = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("dat")){
	  	        		                		usrDOB = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("gender")){
	  	        		                		gender = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("usrname")){
	  	        		                		usrName = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("email")){
	  	        		                		usrEmail = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	/*if(parmName.equals("pwd")){
	  	        		                		usrPwd = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("cpwd")){
	  	        		                		usrCnfPwd = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}*/
	  	        		                	if(parmName.equals("company")){
	  	        		                		usrCompanyDetails = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("category")){
	  	        		                		usrCategory = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("QSelect")){
	  	        		                		secretQuestion = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("ans")){
	  	        		                		secretAns = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("padd_txt_plotNo")){
	  	        		                		paddPlotNo = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("padd_txt_buildNo")){
	  	        		                		paddBuildNo = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("padd_txt_floorNo")){
	  	        		                		paddFloorNo = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("padd_txt_strtAddr")){
	  	        		                		paddStreetNo = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("padd_txt_lane")){
	  	        		                		paddLane = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("padd_txt_area")){
	  	        		                		paddArea = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("pcity_txt")){
	  	        		                		paddCity = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("pcountry_sel")){
	  	        		                		paddCountry = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("pstate_sel")){
	  	        		                		paddState = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("pzip_txt")){
	  	        		                		paddZip = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("country_phone_txt")){
	  	        		                		paddCntryPhn = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("area_phone_txt")){
	  	        		                		paddAreaPhone = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("no_phone_txt")){
	  	        		                		paddNoPhone = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("country_mob_txt")){
	  	        		                		paddCntryMob = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("no_mob_txt")){
	  	        		                		paddNoMob = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("country_fax_txt")){
	  	        		                		paddCntryFax = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("area_fax_txt")){
	  	        		                		paddAreaFax = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("no_fax_txt")){
	  	        		                		paddNoFax = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("secAddrReqOrNot")){
	  	        		                		secAddrReqOrNot = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("sadd_txt")){
	  	        		                		saddPlotBuildFloor = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("sadd_txt_strt_addr")){
	  	        		                		saddStreet = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("sadd_txt_laneNo")){
	  	        		                		saddLane = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("sadd_txt_area")){
	  	        		                		saddArea = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("scity_txt")){
	  	        		                		saddCity = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("scountry_txt")){
	  	        		                		saddCountry = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("sstate_txt")){
	  	        		                		saddState = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("szip_txt")){
	  	        		                		saddZip = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("s_country_phone_txt")){
	  	        		                		saddCntryPhone = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("s_area_phone_txt")){
	  	        		                		saddAreaPhone = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("s_no_phone_txt")){
	  	        		                		saddNoPhone = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("s_country_mob_txt")){
	  	        		                		saddCntryMob = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("s_no_mob_txt")){
	  	        		                		saddNoMob = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("s_country_fax_txt")){
	  	        		                		saddCntryFax = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("s_area_fax_txt")){
	  	        		                		saddAreaFax = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("s_no_fax_txt")){
	  	        		                		saddNoFax = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("PreferredAddrType")){
	  	        		                		PreferredAddrType = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("nonUseaEmail")){
	  	        		                		NonUseaEmail = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	if(parmName.equals("nonUseaMail")){
	  	        		                		NonUseaMail = paramPart.getStringValue();
	  	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
	  	        		                		
	  	        		                	}
	  	        		                	
	  	        		                	
	  	        		                }
	  	        		                
	  	        		              if (_part.isFile()) {
	  	        		                	System.out.println("File Uploadddd");
	  	        		                    FilePart fPart = (FilePart) _part;  // get some info about the file
	  	        		                    String name = fPart.getFileName();
//	  	        		                    fileList = (List) fPart;
	  	        		                    fileList.add(fPart);
	  	        		                    //InputStream in = fPart.getInputStream();
	  	        		                    
	  	        		             
	  	        		                    
	  	        		                    if (name != null) {
	  	        		                        long fileSize = fPart.writeTo(new File(fileSavePath));
	  	        		                        resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
	  	        		                        String saveFile=fileSavePath+name;
	  	        		                      fileLocation = saveFile;
	  	        			                    //db5.adddocumentfromstudent(user_id,name,saveFile);
	  	        		                        String status = "";
	  	        					             //db.adddocumentfromcustomer(userId,name,saveFile);
	  	        					             status = "success";
	  	        					             System.out.println("status from servlet : "+status);
	  	        					             request.setAttribute("status",status);
	  	        		                    } else {
	  	        		                        resp = "<br>The user did not upload a file for this part.";
	  	        		                    }
	  	        		                }
	  	        		                
	  	        		            }// end while 
	  	        		        } catch (java.io.IOException ioe) {
	  	        		            resp = ioe.getMessage();
	  	        		        }
	  	        //Multiple file upload end here-----------------------------------------------		
	  	        			       
	  	   //Test file upload end here-----------------------------------
	  	                	 if(fileList.isEmpty()){
	  	                		 System.out.println("File list is Empty----------------");
	  	                	 }else{
	  	                		 System.out.println("File is not Empty");
	  	                	 }
	  	                	 
	  	                	 
	  /*------------------------------------Generating Password-------------------------------------------------*/ 	      
	   	                	final String AB = "1Ab2Cd3Ef4Gh5IJ6kL7mN8oP9qR0";
	   	          		Random rnd = new Random();
	   	          		int len=10;							
	   	          		StringBuilder sb = new StringBuilder( len );
	   	          		   for( int ij = 0; ij < len; ij++ ) {
	   	          		     sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );			
	   	          		   }							  
	   	          	String password=sb.toString();

	   	          	System.out.println(password);
	   	          usrCnfPwd = password;
	   	          String userNamePass = usrCnfPwd+"-sep-"+usrName;
	   	          String fNameLname = fName+"-sep-"+lName;
	   	          
	 	  	      encryptUserPass = ccc.encrypt(userNamePass, "digiblitz");
	 	  	      encryptfNameLname = ccc.encrypt(fNameLname, "digiblitz");
	   	          
	   /*------------------------------------Generating Password-------------------------------------------------*/ 	
//	                 NewUser nu = new NewUser(usrName, fName, lName, usrCnfPwd, "dBVMS",usrEmail);
	 	  	      
	 	  	      
	                // NewUser nu = new NewUser(usrName, fName, lName, usrCnfPwd, "Sharepoint",usrEmail);
	 	  	      System.out.println("usrName "+usrName +" fName "+ fName +" lName "+ lName +" usrCnfPwd "+ usrCnfPwd +" config.OU "+ properties.getProperty("config.OU") +" usrEmail "+ usrEmail);
	 	  	      
	                 NewUser nu = new NewUser(usrName , fName , lName , usrCnfPwd , properties.getProperty("config.OU"), usrEmail);
	                 
	                 boolean addUsrStatus = false;
	                 addUsrStatus = nu.addUser();
	                // boolean addUsrToGroupStatus = nu.addUserToGroup();
	         		//boolean status = nu.deleteUser("kamal123");
	         		//nu.assignUser("kamal123", "JavaSample");
	         		if (addUsrStatus == true){
	         			System.out.println("user created in Active Directory succcessfully");	
	         		
	/*------------------------------------PRABHU END HERE-------------------------------------------------*/
	        		 
	        		 
	                 textEncryptor.setPassword("sa");	 
	        		 
	                 HLCUserMaster objUserMaster = new HLCUserMaster();
	                 HLCContactDetails objContact = new HLCContactDetails();
	                 HLCMemberDetails objMember = new HLCMemberDetails();
	                 HLCPaymentDetails objPayment = new HLCPaymentDetails();
	               
	                 System.out.println("inside user registration ..........");

	               
	               //String bday = request.getParameter("dob") ;
	                 System.out.println("DOB::::"+usrDOB);
	                  //String bday = request.getParameter("birthyear") + "-" + request.getParameter("birthmonth") + "-" + request.getParameter("birthday");
	                  //System.out.println(bday);
	                  String[] parts = usrDOB.split("-");
	                  String part1 = parts[0]; // mm
	                  String part2 = parts[1]; // dd
	                  String part3 = parts[2]; //yy
	                  String dob=part3+"-"+part1+"-"+part2;
	                  
	                  System.out.println("DOB Split::::"+dob);
	                  objUserMaster.setUserTypeName("");
	                  objUserMaster.setPrefix(usrSalutation);
	                  objUserMaster.setFirstName(fName);
	                  objUserMaster.setMiddleName(mName);
	                  objUserMaster.setLastName(lName);
	                  objUserMaster.setSufix(sName);
	                  objUserMaster.setDob(dob);
	                  objUserMaster.setGender(gender);
	                  objUserMaster.setEmailId(usrEmail);
	                  
	                  String company=usrCompanyDetails;
	                  String category=usrCategory;
	                  System.out.println("company"+company);
	                  
	                  System.out.println("category"+category);
	                 
	                  String encryptedPWD = textEncryptor.encrypt(usrCnfPwd);
	                   objUserMaster.setPassword(encryptedPWD);
	                   objUserMaster.setSecretQuestion(secretQuestion);
	                  objUserMaster.setSecretAnswer(secretAns);
	                  objUserMaster.setLoginName(usrName);
	                  if (NonUseaEmail != null) {
	                      objUserMaster.setNonUseaEmailStatus(true);
	                      System.out.println("commit nonUseaEMail status--true");
	                  } else {
	                      objUserMaster.setNonUseaEmailStatus(false);
	                      System.out.println("commit nonUseaEMail status--false");
	                  }
	                  if (NonUseaMail != null) {
	                      objUserMaster.setNonUseaMailingStatus(true);
	                      System.out.println("commit nonUseaMail status--true");
	                  } else {
	                      objUserMaster.setNonUseaMailingStatus(false);
	                      System.out.println("commit nonUseaMail status--false");
	                  }
	                  if (secAddrReqOrNot.equalsIgnoreCase("Secondary")) {
	                      objUserMaster.setCommunicationAddress("Primary");
	                  }

	                  if (secAddrReqOrNot.equalsIgnoreCase("Primary")) {
	                      objUserMaster.setCommunicationAddress(PreferredAddrType);
	                  }
	                 // String usrCrit=request.getParameter("usrCriteria");
	                  // remote.editUserDetails(objUserMaster);

	                  String usrid = db.addUserRegistration(objUserMaster);
	                  boolean addComStatus=db.addCompanyDetails(company,category,usrName);
	                  if(addComStatus=true){
	                 	 
	                 	 System.out.println("Successfully Inserted Company Details");
	                 	 
	                  }
	                  else{
	                 	 System.out.println("Failed to Insert");
	                  }
	                  
	                  if(usrid != null){
	 			              String user_id=db.getuserid(usrName);
	 			              String status=db.adduserpicture(user_id,fileLocation);
	 			         	 }

	                  Debug.print("remote.addUserRegistration(objUserMaster) usrid :" + usrid);
	                  
	                  /*if(usrid!=null){
	                  	boolean result = humanMemb.addUserCriteria(usrid, usrCrit);
	                  }
	 */
	                  
	                  Vector uid = new Vector();
	         
	                //  uid = (Vector) humanMemb.getUserIdByLoginName(request.getParameter("usrname"));
	                 // String usrId = (String) uid.elementAt(0);
	                 // Debug.print("usrId after reg :" + usrId);
	                  
	                  objContact.setUserId(usrid);
	                  String finalPrimaryPh = "";
	 String isdno=paddCntryPhn;
	 String areacode=paddAreaPhone;
	 String phno=paddNoPhone;
	 String primaryphno=isdno+"-"+areacode+"-"+phno;
	 System.out.println("isdno;;;;;;;;"+isdno);
	 System.out.println("areacode;;;;;;;;"+areacode);
	 System.out.println("phno;;;;;;;;"+phno);
	 System.out.println("primaryphno;;;;;;;;"+primaryphno);

	                  if (primaryphno != null) {
	                      Debug.print("request.getParameter(pphone_txt) value:" + primaryphno);

	                      StringTokenizer strTkns = new StringTokenizer(primaryphno, "[](),.-{}");
	 System.out.println("Phone;;;;;;;;"+strTkns);
	                      while (strTkns.hasMoreTokens()) {
	                          try {
	                              String phNo = (String) strTkns.nextToken();

	                              if (phNo != null && phNo.trim().length() != 0) {
	                                  Debug.print("ph no Added from Stokenizer:" + phNo);
	                                  finalPrimaryPh = finalPrimaryPh + phNo;
	                                  System.out.println("finalPrimaryPh;;;;;;;;"+finalPrimaryPh);
	                              }
	                          } catch (Exception e) {
	                              Debug.print("ph no tokanizing exception:" + e);
	                          }
	                      }

	                      Debug.print("finally appended primary phNo :" + finalPrimaryPh);
	                      System.out.println("finally appended primary phNo;;;;;;;;"+finalPrimaryPh);
	                  }

	                  String finalPrimaryMob = "";
	                  String isdmob=paddCntryMob;
	                  String areamob=paddNoMob;
	                  //String mobno=request.getParameter("pphone_txt");
	                  String primarymob=isdmob+"-"+areamob;
	                  System.out.println("isdmob;;;;;;;;"+isdmob);
	                  System.out.println("areamob;;;;;;;;"+isdmob);
	                  //System.out.println("phno;;;;;;;;"+phno);
	                  System.out.println("primarymob;;;;;;;;"+primarymob);
	                  if (primarymob != null) {
	                      Debug.print("request.getParameter(pmob_txt) value:" + primarymob);

	                      StringTokenizer strTkns = new StringTokenizer(primarymob, "[](),.-{}");

	                      while (strTkns.hasMoreTokens()) {
	                          try {
	                              String phNo = (String) strTkns.nextToken();

	                              if (phNo != null && phNo.trim().length() != 0) {
	                                  Debug.print("ph no Added from Stokenizer:" + phNo);
	                                  finalPrimaryMob = finalPrimaryMob + phNo;
	                              }
	                          } catch (Exception e) {
	                              Debug.print("ph no tokanizing exception:" + e);
	                          }
	                      }

	                      Debug.print("finally appended primary finalPrimaryMob :" + finalPrimaryMob);
	                  }

	                  String finalPrimaryFax = "";
	                  String isdfax=paddCntryFax;
	                  String areafax=paddAreaFax;
	                  String faxno=paddNoFax;
	                  String primaryfax=isdfax+"-"+areafax+"-"+faxno;
	                  System.out.println("isdmob;;;;;;;;"+isdmob);
	                  System.out.println("areamob;;;;;;;;"+isdmob);
	                  //System.out.println("phno;;;;;;;;"+phno);
	                  System.out.println("primaryfax;;;;;;;;"+primaryfax);

	                  if (primaryfax != null) {
	                      Debug.print("request.getParameter(pfax_txt) value:" + primaryfax);

	                      StringTokenizer strTkns = new StringTokenizer(primaryfax, "[](),.-{}");

	                      while (strTkns.hasMoreTokens()) {
	                          try {
	                              String phNo = (String) strTkns.nextToken();

	                              if (phNo != null && phNo.trim().length() != 0) {
	                                  Debug.print("ph no Added from Stokenizer:" + phNo);
	                                  finalPrimaryFax = finalPrimaryFax + phNo;
	                              }
	                          } catch (Exception e) {
	                              Debug.print("ph no tokanizing exception:" + e);
	                          }
	                      }

	                      Debug.print("finally appended primary finalPrimaryFax :" + finalPrimaryFax);
	                  }

	                  objContact.setContactType("Primary");
	                  String address1 = paddPlotNo+" "+paddBuildNo+" "+paddFloorNo+" "+paddStreetNo;
	                  String parea = paddArea;
	                  //String address2 = request.getParameter("padd_txt2");
	                  String pLane = paddLane;
	                  String setAddressOne = address1;
	                  String setAddresstwo = parea+"\n"+pLane;
	                  System.out.println("setAddressOne :::::::::::::"+setAddressOne);
	                  System.out.println("setAddresstwo :::::::::::::"+setAddresstwo);
	                  
	                  objContact.setAddress1(setAddressOne);
	                  objContact.setAddress2(setAddresstwo);
	                  objContact.setCity(paddCity);
	                  objContact.setState(paddState);
	                  objContact.setCountry(paddCountry);
	                  objContact.setZip(paddZip);
	                  objContact.setPhoneNo(finalPrimaryPh);
	                  objContact.setMobileNo(finalPrimaryMob);
	                  objContact.setFaxNo(finalPrimaryFax);
	                  db.addContactDetails(objContact,objUserMaster.getLoginName());


	                  boolean result = new MySQLDAO().insertUserDetailToMqSQL(objUserMaster, objContact);
	                  Debug.print("                MySql Result :" + result);


	                  if (secAddrReqOrNot.equalsIgnoreCase("Primary")) {
	                 	 objContact.setUserId(usrid);
	                      String finalSecPh = "";
	                      String sisdno=saddCntryPhone;
	                      String sareacode=saddAreaPhone;
	                      String sphno=saddNoPhone;
	                      String secondaryphno=sisdno+"-"+sareacode+"-"+sphno;
	                      System.out.println("sisdno;;;;;;;;"+sisdno);
	                      System.out.println("sareacode;;;;;;;;"+sareacode);
	                      System.out.println("sphno;;;;;;;;"+sphno);
	                      System.out.println("secondaryphno;;;;;;;;"+secondaryphno);
	                      if (secondaryphno != null) {

	                          StringTokenizer strTkns1 = new StringTokenizer(secondaryphno, "[](),.-{}");


	                          while (strTkns1.hasMoreTokens()) {
	                              try {
	                                  String phNo = (String) strTkns1.nextToken();

	                                  if (phNo != null && phNo.trim().length() != 0) {
	                                      Debug.print("ph no Added from Stokenizer:" + phNo);
	                                      finalSecPh = finalSecPh + phNo;
	                                  }
	                              } catch (Exception e) {
	                                  Debug.print("Secondary ph no tokanizing exception:" + e);
	                              }
	                          }

	                          Debug.print("finally appended Secondary phNo :" + finalSecPh);
	                      }

	                      String finalSecMob = "";
	                      String sisdmob=saddCntryMob;
	                      //String sareacode=request.getParameter("smob_txt");
	                      String smobno=saddNoMob;
	                      String secondarymob=sisdmob+"-"+smobno;
	                      System.out.println("sisdmob;;;;;;;;"+sisdmob);
	                      //System.out.println("areacode;;;;;;;;"+areacode);
	                      System.out.println("secondarymob;;;;;;;;"+secondarymob);
	                     System.out.println("smobno;;;;;;;;"+smobno);
	                      if (saddNoMob != null) {

	                          StringTokenizer strTkns1 = new StringTokenizer(saddNoMob, "[](),.-{}");


	                          while (strTkns1.hasMoreTokens()) {
	                              try {
	                                  String phNo = (String) strTkns1.nextToken();

	                                  if (phNo != null && phNo.trim().length() != 0) {
	                                      Debug.print("ph no Added from Stokenizer:" + phNo);
	                                      finalSecMob = finalSecMob + phNo;
	                                  }
	                              } catch (Exception e) {
	                                  Debug.print("Secondary ph no tokanizing exception:" + e);
	                              }
	                          }

	                          Debug.print("finally appended Secondary finalSecMob :" + finalSecMob);
	                      }

	                      String finalSecFax = "";
	                      String sisdfax=saddCntryFax;
	                      String sareafax=saddAreaFax;
	                      String sfax=saddNoFax;
	                      String secondaryfax=sisdfax+"-"+sareafax+"-"+sfax;
	                      System.out.println("sisdno;;;;;;;;"+sisdno);
	                      System.out.println("sareacode;;;;;;;;"+sareacode);
	                      System.out.println("sphno;;;;;;;;"+sphno);
	                      System.out.println("secondaryphno;;;;;;;;"+secondaryphno);
	                      if (secondaryfax != null) {

	                          StringTokenizer strTkns1 = new StringTokenizer(secondaryfax, "[](),.-{}");


	                          while (strTkns1.hasMoreTokens()) {
	                              try {
	                                  String phNo = (String) strTkns1.nextToken();

	                                  if (phNo != null && phNo.trim().length() != 0) {
	                                      Debug.print("ph no Added from Stokenizer:" + phNo);
	                                      finalSecFax = finalSecFax + phNo;
	                                  }
	                              } catch (Exception e) {
	                                  Debug.print("Secondary ph no tokanizing exception:" + e);
	                              }
	                          }

	                          Debug.print("finally appended Secondary finalSecFax :" + finalSecFax);
	                      }

	                      objContact.setContactType("Secondary");
	                      String address11 = saddPlotBuildFloor;
	                      String sarea = saddArea;
	                      String address22 = saddStreet;
	                      String sLane = saddLane;
	                      String ssetAddressOne = address11+"\n"+sarea;
	                      String ssetAddresstwo = address22+"\n"+sLane;
	                      System.out.println("ssetAddressOne :::::::::::::"+ssetAddressOne);
	                      System.out.println("ssetAddresstwo :::::::::::::"+ssetAddresstwo);
	                      objContact.setAddress1(ssetAddressOne);
	                      objContact.setAddress2(ssetAddresstwo);
	                   
	                      objContact.setCity(saddCity);
	                      objContact.setState(saddState);
	                      objContact.setCountry(saddCountry);
	                      objContact.setZip(saddZip);
	                      objContact.setPhoneNo(finalSecPh);
	                      objContact.setMobileNo(finalSecMob);
	                      objContact.setFaxNo(finalSecFax);
	                      db.addContactDetails(objContact,objUserMaster.getLoginName());
	                      
	                  }

	                  request.setAttribute("pwd", usrCnfPwd);
	                  request.setAttribute("loginName", usrName);       
	          
	                  /* =====================================
	                  *
	                  * Sending confirmation E-mail
	                  *
	                  * ====================================*/
	                  
	                //String fromAddress = "prabhu.pandi@digiblitz.in";
	                  String fromAddress = properties.getProperty("infusionMail.fromAddress");
	                  String toAddress = usrEmail;
	                  String ccAddresses = "";
	                  ccAddresses = properties.getProperty("infusionMail.ccAddress");
	                  String bccAddresses = "";
	                  bccAddresses = properties.getProperty("infusionMail.bccAddress");
	                  String contentType = "HTML";
	                  String subject = "Your Username";
	                  String htmlBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
	                          "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
	                          "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
	                          " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
	                          "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Congratulations "+fName+" "+lName+"</h4><p style=\"   font-size: 19px;" +
	                            "line-height: 27px; margin-bottom: 16px;margin-top: 16px; text-align: center;\">You are registered Successfully... </p><table style=\" clear: both; margin: 0 auto;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
	                            "<tbody> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Registration Details</h4></td>" +
	                            "</tr> </tbody> </table> <p>&nbsp; </p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td align=\"center\" valign=\"top\" style=\"background-color: #fff;  padding: 16px; text-align: center; vertical-align: top;\">" +
	                            "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody> <tr> <td style=\"color: #888;\" align=\"center\" valign=\"top\" style=\" padding: 16px; text-align: center; vertical-align: top;\">" +
	                            " <p style=\" line-height: 23px; margin-bottom: 24px; margin-top: 16px;font-size: 15px;\">" +
	 							"<strong>E-mail ID :</strong><br /> "+usrEmail+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
	 							"<strong>Visit the Site :</strong><br /> <a href=\"http://www.menschforce.com\" target=\"_blank\">www.menschforce.com</a></p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
	 							"<strong>User Name :</strong><br /> "+usrName+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
	                            "<strong>Password :</strong><br /> "+usrCnfPwd+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
	                            "<strong>"+usrCategory+" Id :</strong><br /> "+usrCompanyDetails+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
	                            "</td> </tr> </tbody></table></td></tr></tbody></table></div></div></div></div></body></html>";
	                  String textBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
	                          "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
	                          "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
	                          " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
	                          "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Congratulations "+fName+" "+lName+"</h4><p style=\"   font-size: 19px;" +
	                            "line-height: 27px; margin-bottom: 16px;margin-top: 16px; text-align: center;\">You are registered Successfully... </p><table style=\" clear: both; margin: 0 auto;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
	                            "<tbody> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Registration Details</h4></td>" +
	                            "</tr> </tbody> </table> <p>&nbsp; </p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td align=\"center\" valign=\"top\" style=\"background-color: #fff;  padding: 16px; text-align: center; vertical-align: top;\">" +
	                            "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody> <tr> <td style=\"color: #888;\" align=\"center\" valign=\"top\" style=\" padding: 16px; text-align: center; vertical-align: top;\">" +
	                            " <p style=\" line-height: 23px; margin-bottom: 24px; margin-top: 16px;font-size: 15px;\">" +
	 							"<strong>E-mail ID :</strong><br /> "+usrEmail+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
	 							"<strong>Visit the Site :</strong><br /> <a href=\"http://www.menschforce.com\" target=\"_blank\">www.menschforce.com</a></p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
	 							"<strong>User Name :</strong><br /> "+usrName+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
	                            "<strong>Password :</strong><br /> "+usrCnfPwd+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
	                            "<strong>"+usrCategory+" Id :</strong><br /> "+usrCompanyDetails+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
	                            "</td> </tr> </tbody></table></td></tr></tbody></table></div></div></div></div></body></html>";
	                  
	                  System.out.println("infusion soft property values: ----> "+fromAddress+" toAddress "+toAddress+" subject "+subject+" htmlBody "+" textBody "+textBody);
	                  System.out.println("ccAddresses "+ccAddresses+" bccAddresses "+bccAddresses);
	                  //Infusion Soft mail starts
	                                   
	         //obj1.sendEmail(properties.getProperty("infusionMail.fromAddress"), usrEmail, properties.getProperty("infusionMail.ccAddresses"), properties.getProperty("infusionMail.bccAddresses"), properties.getProperty("infusionMail.contentType"), properties.getProperty("infusionMail.subject"), properties.getProperty("infusionMail.htmlBody"), properties.getProperty("infusionMail.textBody"));
	              try {
	             	 obj1.createAndCheckDuplicateContact(fName, lName, toAddress);
	             	 obj1.optin_outEmail(toAddress);
	 				obj1.sendEmail(fromAddress, toAddress, ccAddresses, bccAddresses, contentType, subject, htmlBody, textBody);
	 			} catch (XmlRpcException e) {
	 			// TODO Auto-generated catch block
	 				e.printStackTrace();
	 			}
                              
                              System.out.println("Mail has been send successfully");                                 
                     //Infusion Soft mail ends
	                  
	                  
	                 String emailid = usrEmail;
	                 String toMailIds[] = {emailid};
	                 EmailContent email = new EmailContent();
	                 email.setTo(toMailIds);
	                 email.setFrom("dhivya@digiblitz.in");
	                 email.setSubject("Your Account Info !");

	                 String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
	                         " <tr>" +
	                         " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
	                         " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
	                         "<tr>" +
	                         "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
	                         " </tr>" +
	                         "  <tr>" +
	                         "<td valign=\"top\">" +
	                         "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
	                         "<tr>" +
	                         "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopLeft.jpg\" width=\"13\" height=\"12\" /></td> " +
	                         "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>" +
	                         "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopRght.jpg\" width=\"13\" height=\"12\" /></td>" +
	                         "</tr>" +
	                         "<tr>" +
	                         "<td valign=\"top\" background=\"images/left.jpg\">&nbsp;</td>" +
	                         "<td valign=\"top\" bgcolor=\"#FBF2F2\">" +
	                         "<span class=\"boldTxt\">Dear " + fName + "</span>,<br /><br />" +
	                         "<p>Please save this email for your records. Your account information is as follows:.<p>" + "<p>----------------------------<p>" + "<p>UserName :" + request.getParameter("usrname") + "<p> password: " + request.getParameter("cpwd") + "<p> ----------------------------<p>" +
	                         /*"<p>Your account would be activated through your confirmation by visiting the following link: <p>"+
	                         "<a href=http://192.168.3.98:8090/dashboad-war/uservalidate.do?email="+request.getParameter("email")+"> Click Here to Activate your Account </a>"+*/
	                         "Thank you for using the service provided by <span class=\"boldTxt\">United States Eventing Association</span>.</p>" +
	                         "Thank You <br />" +
	                         "------------------ <br />" +
	                         "<span class=\"boldRedTxt\">HLC Team</span></td>" +
	                         "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>" +
	                         "</tr>" +
	                         "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>" +
	                         "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>" +
	                         "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>" +
	                         "</tr>" +
	                         " </table>" +
	                         "</td></tr>" +
	                         "+<tr>" +
	                         "<td valign=\"top\" style=\"padding:10px;\">" +
	                         "<img src=\"images/pic.jpg\" width=\"272\" height=\"76\" style=\"float:right; padding-left:8px; padding-bottom:8px;\" />" +
	                         "<p>The easiest way to access your day to day HLC activities online or offline where ever you are and when ever you want." +
	                         "</p>If you are a NEW VISITOR, register now and ENJOY the following privileges:" +
	                         "<ul>" +
	                         "<li>Unlimited shopping online.</li>" +
	                         "<li>Place advertisements online and/or on-site.</li>" +
	                         "<li>Sponsor competitions held by HLC.</li>" +
	                         "</ul>" +
	                         "Also, REGISTER NOW! and become a member of HLC to access and 	enjoy the following privileges as per your Membership Type and as " +
	                         "per your �Role� assigned:" +
	                         "<ul>" +
	                         "<li>Compete in Equestrian Events held by HLC.</li>" +
	                         "<li>Take part in other events like; Annual Meetings, Educational events," +
	                         "Activity Meetings held by HLC etc.</li>" +
	                         "<li>Send Messages to other members.</li>" +
	                         "<li>Create your own Distribution Lists.</li>" +
	                         "<li>Create/Join a group and share your thoughts and common ideas.</li>" +
	                         " <li>Unlimited Shopping online.</li>" +
	                         " <li>Place advertisements online and/or on-site.</li>" +
	                         " <li>Sponsor competitions held by HLC.</li>" +
	                         "</ul>" +
	                         "and much more..." +
	                         "So go ahead and <a href=http://dashboard.useventing.com/>LOGIN NOW!</a></td>" +
	                         "</tr>" +
	                         " <tr>" +
	                         "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>" +
	                         "</tr>" +
	                         "</table>";


	                 email.setBody(content);
	                 //email.setAttachments();

	                 MailMail mail = new MailMail();
	                 boolean emailFlag = mail.sendMimeEmail(email);
	                 Debug.print("Email sent sucessfully :" + emailFlag);
	                 request.setAttribute("source", source);  
	                
	/*-----------------------------------Auto role asign start here--------------------------------------------------*/

	                String roleName = null;
	                String userId = null;
	                boolean addUsrToGroupStatus = false;
	           	 try{
	           		 
	           	 //String roleId = request.getParameter("roleIds");
	           		 if(usrCategory != null && usrCategory.equalsIgnoreCase("Workseeker")){
	           			usrCategory = "Candidate";
	           		 }
	           		 String roleCategory = usrCategory;
	           	 //System.out.println("RoleId from Jsp : "+roleId);
	           	 
	                //String roleName = db.getRoleNameByRoleId(roleId);
	           		 roleName = roleCategory;
	                request.setAttribute("roleNameValue", roleName);
	                System.out.println("Role name in servlet : "+roleName);
	                
	                //String first_Name = request.getParameter("firstname");
	                String first_Name = fName;
	                request.setAttribute("firstName", first_Name);
	                System.out.println("First name in servlet : "+first_Name);
	                
	                //String last_Name = request.getParameter("lastname");
	                String last_Name = lName;
	                request.setAttribute("lastName", last_Name);
	                System.out.println("Last name in servlet : "+last_Name);
	                
	                //String user_Name = request.getParameter("username");
	                String user_Name = usrName;
	                userId = db.getUserIDByUserName(user_Name);
	                System.out.println("User name in servlet : "+user_Name);
	                
	                NewUser nu1 = new NewUser();
	                addUsrToGroupStatus = nu1.addUserToGroup(first_Name, last_Name, roleName, user_Name);
	                //addUsrToGroupStatus = nu.addUserToGroup(first_Name1, last_Name1);
	                
	           	 }catch(Exception eLDAP){
	                    Debug.print("Failed to Map User with Role in Active Directory:" + eLDAP);
	           	 }
	                if(addUsrToGroupStatus == true){
	                    System.out.println("Successfully user mapped with Role in Active Directory ::");
	                    
	                    

	                ArrayList userContactDetail = new ArrayList();
	               try{
	                   Debug.print("KlgRoleAction.mapUserRoles()");
	                   
	                   
	                  String roleIds = db.getRoleIdByRoleName(roleName);
	                   //String roleIds = request.getParameter("roleIds");
	                   // String empScreen=request.getParameter("empScreen");
	                     ArrayList roleList = new ArrayList();
	                    roleList = (ArrayList)db.getAllRoles();
	                    String rolId=null;String rolName=null;
	                   Debug.print("roleIds:" + roleIds);
	                   //String userIdVal = request.getParameter("userId");
	                   String userIdVal = userId;
	               //==================Dhivya Here:User View==============================    
	                   
	                /*  // String viewPntCnt = request.getParameter("viewPntCnt");
	                  // String usrCrit = request.getParameter("usrCrit");
	                   
	                   
	                   
	                   int viewpntSize = 0;
	                     if(viewPntCnt!=null && viewPntCnt.trim().length()!=0){
	                   	  viewpntSize = Integer.parseInt(viewPntCnt);
	                     }   
	                    
	                     for(int j=0; i<viewpntSize; j++){
	                   	  if(request.getParameter("viewPnt"+j)!=null && request.getParameter("viewPnt"+j)!=""){	                    		 	                    		  
	          	               String viewPnt = request.getParameter("viewPnt"+j); 
	          	               
	          	               
	          	           boolean result1=humanMemb.updateUserCriteria(userIdVal,viewPnt,usrCrit);    
	          	                                
	                   	  }  
	                     }*/
	                     
	                   
	                    StringTokenizer strTkns = new StringTokenizer(roleIds,"#");
	                  String userList="";
	                   while(strTkns.hasMoreTokens()){
	                       try{
	                           String roleId = (String)strTkns.nextToken();
	                            Iterator listName=roleList.iterator();
	                          while(listName.hasNext())
	                          {
	                             String strRoleName[] =(String[])listName.next();
	                              rolId=strRoleName[0];
	                             rolName=strRoleName[1];
	                             if(roleId.equalsIgnoreCase(rolId))
	                             {
	                                 //userList=rolName;
	                                 userList=userList+rolName+",";

	                             }


	                       }

	                       }
	                       catch(Exception e){
	                           Debug.print("Exception while spliting privilegeIds KlgRoleAction.mapUserRoles() :" + e);
	                       }
	                   }

	                      request.setAttribute("userNameList",userList);
	                      System.out.println("user name list....."+userList);
	                   

	                    //For Debug Starts
	                      humanMemb.deactivateRequestStatus(userIdVal,true);
	                    //For Debug Ends
	                   if(userIdVal!=null){
	                       userContactDetail = (ArrayList)humanMemb.getUserContactDetails(userIdVal);
	                   }


	                   request.setAttribute("userId",userIdVal);



	                   strTkns = new StringTokenizer(roleIds,"#");
	                   ArrayList rolesList = new ArrayList();
	                   while(strTkns.hasMoreTokens()){
	                       try{
	                           String roleId = (String)strTkns.nextToken();
	                           if(roleId!=null && roleId.trim().length()!=0){
	                               Debug.print("KlgRoleAction.mapUserRoles() Added from Stokenizer:" + roleId);
	                               rolesList.add(roleId);
	                               
	                           }
	                       }
	                       catch(Exception e){
	                           Debug.print("Exception while spliting privilegeIds KlgRoleAction.mapUserRoles() :" + e);
	                       }
	                   }
	                   //String userCode=(String)session.getAttribute("userCode");
	                   //String roleIdtemp="VMSSite_"+userCode;
	                   //rolesList.add(roleIdtemp);
	                   if(userIdVal!=null){
	                       Debug.print("KlgRoleAction.mapUserRoles() All Ids Are valid");
	                       db.createMappingUserWithRoles(userIdVal, rolesList);
	                   }

	               }
	               catch(Exception eDisp){
	                   Debug.print("while getting mapUserRoles:" + eDisp);
	               }
	               request.setAttribute("userContactDetails" ,userContactDetail);
	               Debug.print("KlgRoleAction.mapUserRoles() sucessfully comes from servlet.");
	               
	               System.out.println("frmRolAssignCnf::::::::::::::::::::::::::::::::");
	               
	               //return new ModelAndView("frmRolAssignCnf"); 
//	               String redirectsite = "http://198.71.58.51:19075/ResetPassLocal.aspx?UN="+encryptfNameLname+"&PWD="+encryptUserPass;
	               
	               
	               
	               //String redirectsite = "http://198.71.58.51:56122/ResetPassword.aspx?UN="+encryptfNameLname+"&PWD="+encryptUserPass;
	               String redirectsite = properties.getProperty("config.SP_reset_pwd_url")+"/ResetPassword.aspx?UN="+encryptfNameLname+"&PWD="+encryptUserPass;
	               System.out.println("rediredt url :::::::::::::::::::"+redirectsite);
	               response.sendRedirect(redirectsite);
	               
	               
	                }
	           	
	     /*------------------------------Auto role asign end here-------------------------------------------------*/     
	                
	/*-----------------------------------------------PRABHU START HERE----------------------------------------------   */             
	                
	         		} else{
	         			System.out.println("Cannot registred in ldap: ");
	         		}	
	/*-----------------------------------------------PRABHU START HERE----------------------------------------------*/
	        	 }catch (Exception ex) {
	                 System.err.println("Caught an exception.");
	                 ex.printStackTrace();
	             }
	        	 return new ModelAndView("frmMemberUserRegiCnf");	
		}
		
		
		
		return new ModelAndView("frmHome");
	}
/*
	@RequestMapping(method=RequestMethod.GET)
	public String printHello(ModelMap Model){
		Model.addAttribute("message","Welcome to Digiblitz Technology");
		return "Hello";
	}
	*/
}

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
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.naming.Context;
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
import org.springframework.web.servlet.mvc.Controller;
import org.xml.sax.SAXException;

import com.AD.action.NewUser;
import com.ContactUs.ContactClient;
import com.ContactUs.ContactUser;
import com.ContactUs.ContactUserCRMMethods;
import com.artifact.action.CreditCard;
import com.db.GeneralDBAction;
import com.google.gson.Gson;
import com.hlccommon.util.Debug;
import com.hlcform.stateless.HLCkaverystatelessBean;
import com.hlcform.util.HLCContactDetails;
import com.hlcform.util.HLCMemberDetails;
import com.hlcform.util.HLCPaymentDetails;
import com.hlcform.util.HLCUserMaster;
import com.hlchorse.form.display.HLCKaverySessionStatefulBean;
import com.hlchorse.form.util.HLCUserRegVO;
import com.hlcmsg.groups.HLCMessageSessionBean;
import com.hlcrole.management.HLCBrahmaputraSessionBean;
import com.infusionejb.session.InfusionSessionBean;
import com.jobvacancy.contacts.VendorContactDetailsBean;
import com.mysql.dao.MySQLDAO;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

import com.suitecrm.session.DBCRMSessionBean;
import com.util.email.EmailContent;
import com.util.email.MailMail;


public class KlgUserAction implements Controller  {

	String userId = null;
    Vector vObj = new Vector();
    String status=null;
    Properties properties;
    static Logger log = Logger.getLogger(KlgUserAction.class.getName());
    
    CreditCard ccard= new CreditCard();
    VendorContactDetailsBean vendorBean = new VendorContactDetailsBean();
    ContactUser contactUser=new ContactUser();
    ContactClient contactClient=new ContactClient();
    
    private InfusionSessionBean obj1;
	{
		try{
			obj1 = new InfusionSessionBean();
		}catch(Exception e){
			 throw new RuntimeException(e);
		}
	}

	
	public static final String LOG_FILE = "Log4j.properties";
	InputStream is;
	@SuppressWarnings("static-access")
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
        	properties.load(NewUser.class.getResourceAsStream("/config.properties"));
        	properties.load(KlgUserAction.class.getResourceAsStream("/paypal.properties"));
        	properties.load(getClass().getClassLoader().getResourceAsStream("/infusionMail.properties"));
        	
        	//properties.load(KlgLoginAction.class.getResourceAsStream("/paypal.properties"));
        	System.out.println("Property file loaded successfully");
            } catch (Exception e) {
                try {
                  //properties.load(new FileInputStream("src/companyDetails.properties"));
                properties.load(KlgUserAction.class.getResourceAsStream("/paypal.properties"));
                  properties.load(new FileInputStream("/config.properties"));
                }catch(Exception ee) {
                   Debug.print("Could not load the config.properties");
                }
            }
	
         
         HLCkaverystatelessBean humanMemb=new HLCkaverystatelessBean();
         HLCMessageSessionBean msgBean=new HLCMessageSessionBean();
         HLCBrahmaputraSessionBean roleBean=new HLCBrahmaputraSessionBean(); 
         HLCKaverySessionStatefulBean hrsBean=new HLCKaverySessionStatefulBean();
         
       
        
         HLCUserRegVO userregvo=new HLCUserRegVO();
         GeneralDBAction db=new GeneralDBAction();
         Crypt ccc = new Crypt();
        
         HttpSession session=request.getSession(true); 
         String usrProcess=request.getParameter("cmd");
         String source = request.getParameter("source");
         
         
         if(usrProcess.equals("initUsr")){
                     
         return new ModelAndView("frmMemberUserSignup");
        
         }else if(usrProcess.equals("initUsrReg")){
        	 String id = request.getParameter("id");
        	 String status = request.getParameter("add");
        	 request.setAttribute("id", id);
        	 ArrayList companyList = db.getAllCompanyDetails();
        	 request.setAttribute("companyList", companyList);
        	 //request.setAttribute("Userstatus",status ); 
        	 if(status != null && status.equalsIgnoreCase("addnewuser")){
        		 return new ModelAndView("frmAdminUserRegistration");
        	 }else{
        		 return new ModelAndView("frmMemberUserRegistration");	 
        	 }
        	 
         }else if(usrProcess.equals("usrReg")){
        	 String refName = null;
        	 String relName = null;
        	 String rePass = null;
        	 String encryptUserPass= null;
	          String encryptfNameLname= null;
         try {
        		 
        	
         			
/*------------------------------------PRABHU END HERE-------------------------------------------------*/
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
  	        		                		rePass = usrCnfPwd;
  	        		                		
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
//	        		                    fileList = (List) fPart;
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
         			
  	               	// /*------------------------------------PRABHU START HERE-------------------------------------------------/
  	                  /* String username = request.getParameter("usrname");
  	                   String firstname = request.getParameter("fname");
  	                   String lastname = request.getParameter("lname");
  	                   String usrPassword = request.getParameter("cpwd");*/
	  	      
	  	      
  	                   //NewUser nu = new NewUser(usrName , fName , lName , usrCnfPwd , "Sharepoint", usrEmail);
	  	    System.out.println("usrName "+usrName +" fName "+ fName +" lName "+ lName +" usrCnfPwd "+ usrCnfPwd +" config.OU "+ properties.getProperty("config.OU") +" usrEmail "+ usrEmail);
	  	    NewUser nu = new NewUser(usrName , fName , lName , usrCnfPwd , properties.getProperty("config.OU"), usrEmail);
  	                 
  	                 
//  	                 NewUser nu = new NewUser(usrName , fName , lName , usrCnfPwd , "dBVMS", usrEmail);
  	                   
  	                   boolean addUsrStatus = nu.addUser();
  	                  // boolean addUsrToGroupStatus = nu.addUserToGroup();
  	           		//boolean status = nu.deleteUser("kamal123");
  	           		//nu.assignUser("kamal123", "JavaSample");
  	           		if (addUsrStatus == true){
  	           			System.out.println("user created in Active Directory succcessfully");	
  	           		
  	  /*------------------------------------PRABHU END HERE-------------------------------------------------*/
         			
         			
         			
         			
         			
        		 
  	           		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
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
                 String dob = part3+"-"+part1+"-"+part2;
                 
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
                	 request.setAttribute("response_status", "fail");
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
                        	 request.setAttribute("response_status", "fail");
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
                        	 request.setAttribute("response_status", "fail");
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
                        	 request.setAttribute("response_status", "fail");
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
                            	 request.setAttribute("response_status", "fail");
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
                            	 request.setAttribute("response_status", "fail");
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
                            	 request.setAttribute("response_status", "fail");
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
                 request.setAttribute("email", usrEmail);
                 System.out.print("password:"+usrCnfPwd);
                 System.out.print("loginmane:"+usrName);
                 System.out.print("email:"+usrEmail);
                 /* =====================================
                 *
                 * Sending confirmation E-mail
                 *
                 * ====================================*/
                 
           //=========Adding vendor details============//
                 
                 if(category != null && category.equalsIgnoreCase("vendor")){
                	 
                	 vendorBean.setvendor_fname(fName);
                     vendorBean.setvendor_lname(lName);
                     vendorBean.setvendor_email(usrEmail);
                     vendorBean.setvendor_company(company);
                     vendorBean.setvendor_homePhone(finalPrimaryMob);
                     vendorBean.setvendor_id(company);
                
                     String Vendor_email=null;
                     if(usrid !=null){
                    
                  	   Vendor_email=db.getAllVendorDetailsByEmail(usrEmail);
                     
                  	 if(Vendor_email == null){
                  		  boolean insertvendorstatus=db.insertVendorContacts(vendorBean);
                  		  System.out.println("Updating vendor Query"+insertvendorstatus);  
                  	  }else{
                  		  System.out.println("vender email already exixts");
                  		 boolean updatevendorstatus=db.UpdateVendorByEmailID(vendorBean);
                    		System.out.println("Updating vendor Query"+updatevendorstatus); 
                  	  }
                 }
                 
                 /* // Account manager role to vendor -shamili
                     String vendorUserId1 = db.getUserIdByEmail(usrEmail);
            		 String roleId = "F7FC20DE-3FD4-4474-8529-E8068EC6BFE4";
            		 
            		 String roleName = db.getRoleNameByRoleId(roleId);
            		 
            		 boolean roleFlag = db.insertUserWithRoleMapping(vendorUserId1, roleId);
            		 boolean rolestatusSP= nu.addUserToGroup(fName, lName, roleName, usrName);
            		 System.out.println("Role status in sharepoint===="+rolestatusSP);

            		 System.out.println("roleFlag --> "+roleFlag);*/
              	 
                /*if(Vendor_email !=null && usrEmail != null && Vendor_email.equals(usrEmail)) {
                	boolean updatevendorstatus= db.UpdateVendorUniqueID(vendorBean, usrid);
                	System.out.println("Updating vendor Query"+updatevendorstatus);
                }
                else{	
                	boolean insertvendorstatus=db.insertVendorContacts(vendorBean);
                	System.out.println("Updating vendor Query"+insertvendorstatus);
                	
     			}*/
     		}  
     			
                //====================================================Email changing from infusion to menschforce.io START HERE====================================================//
                 
                 
               //String fromAddress = "prabhu.pandi@digiblitz.in";
                // String fromAddress = properties.getProperty("infusionMail.fromAddress");
               //  String toAddress = usrEmail;
               //  String ccAddresses = "";
               //  ccAddresses = properties.getProperty("infusionMail.ccAddress");
               //  String bccAddresses = "";
               //  bccAddresses = properties.getProperty("infusionMail.bccAddress");
               //  String contentType = "HTML";
                // String subject = "Your Username";
                 
                 String emailid1 = usrEmail;
                 String toMailIds1[] = {emailid1};// instance toMailds1
                 EmailContent email1 = new EmailContent();// instance email1
                 email1.setTo(toMailIds1);
                 email1.setFrom("contact@menschforce.io");
                 email1.setSubject("Your Username");

                 
                 
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
                 
            //     System.out.println("infusion soft property values: ----> "+fromAddress+" toAddress "+toAddress+" subject "+subject+" htmlBody "+" textBody "+textBody);
              //   System.out.println("ccAddresses "+ccAddresses+" bccAddresses "+bccAddresses);
                 //Infusion Soft mail starts
                                  
        //obj1.sendEmail(properties.getProperty("infusionMail.fromAddress"), usrEmail, properties.getProperty("infusionMail.ccAddresses"), properties.getProperty("infusionMail.bccAddresses"), properties.getProperty("infusionMail.contentType"), properties.getProperty("infusionMail.subject"), properties.getProperty("infusionMail.htmlBody"), properties.getProperty("infusionMail.textBody"));
         
                 /*
                 
                 try {
            	 obj1.createAndCheckDuplicateContact(fName, lName, toAddress);
            	 obj1.optin_outEmail(toAddress);
				obj1.sendEmail(fromAddress, toAddress, ccAddresses, bccAddresses, contentType, subject, htmlBody, textBody);
			} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
                         
                         System.out.println("Mail has been send successfully");     
                  
                         
   */
                     //Infusion Soft mail ends
                 
                 email1.setBody(htmlBody);//content=htmlBoady
                 //email.setAttachments();

                 MailMail mail1 = new MailMail();
                 boolean emailFlag1 = mail1.sendMimeEmail(email1);
                 Debug.print("Email sent sucessfully :" + emailFlag1);
                 
               //====================================================Email changing from infusion to menschforce.io END HERE====================================================//
                 
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
                        "<p>Please save this email for your records. Your account information is as follows:.<p>" + "<p>----------------------------<p>" + "<p>UserName :" + usrName + "<p> password: " + usrCnfPwd + "<p> ----------------------------<p>" +
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
                
/*-----------------------------------------------PRABHU START HERE----------------------------------------------   */             
                
         		} else{
         			System.out.println("Cannot registred in ldap: ");
         			request.setAttribute("response_status", "fail");
         		}	
/*-----------------------------------------------PRABHU START HERE----------------------------------------------*/
        	 }catch (Exception ex) {
                 System.err.println("Caught an exception.");
                 request.setAttribute("response_status", "fail");
                 ex.printStackTrace();
                 
             }
        	 
        	 
        	 //return new ModelAndView("frmMemberUserRegiCnf");	
//         String redirectsite = "http://198.71.58.51:19075/ResetPassLocal.aspx?UN="+encryptfNameLname+"&PWD="+encryptUserPass;
         
         
         
         //String redirectsite = "http://198.71.58.51:56122/ResetPassword.aspx?UN="+encryptfNameLname+"&PWD="+encryptUserPass;
         String redirectsite = properties.getProperty("config.SP_reset_pwd_url")+"/ResetPassword.aspx?UN="+encryptfNameLname+"&PWD="+encryptUserPass;
         
         System.out.println("rediredt url :::::::::::::::::::"+redirectsite);
         response.sendRedirect(redirectsite);
         //return new ModelAndView("frmMemberUserRegiCnf");	
        	// return mapping.findForward("Confirmation");
        	 
         }
         	
			
         
         
         else if(usrProcess != null && usrProcess.equals("edit")) 
         {
             Vector EditusrVect=new Vector();
             String usrId=(String)session.getAttribute("userIdInIndex");
             System.out.println("User id in edit servlet:::::::::::::::::::::::"+usrId);
             Debug.print("loginName :"+usrId);
         
             userregvo = hrsBean.selectUserRegistrationForm1(usrId);

             request.setAttribute("EditusrVect",userregvo);
                                       
             System .out.println("inside status =" +status);
            // fwd="EditUserReg";
        
       	   return new ModelAndView("frmEditMemberUserSignUp");    
       	   
          }else if(usrProcess != null && usrProcess.equals("updateUsr")){
        	  
              HLCUserMaster objUserMaster = new HLCUserMaster();
              HLCContactDetails objContact = new HLCContactDetails();

              /*
               * Updating User Details by ejb/HLCMemberRegistrationJNDI JNDI
               */

             // UpdateUserRegActionForm usrbean = (UpdateUserRegActionForm) form;

            /*  String bday = request.getParameter("dob") ;
              System.out.println("DOB::::"+bday);
              //String bday = request.getParameter("birthyear") + "-" + request.getParameter("birthmonth") + "-" + request.getParameter("birthday");
              //System.out.println(bday);
              String[] parts = bday.split("-");
              String part1 = parts[0]; // mm
              String part2 = parts[1]; // dd
              String part3 = parts[2]; //yy
              String dob = part3+"-"+part1+"-"+part2;
*/
              String userId = request.getParameter("userId");


              objUserMaster.setPrefix(request.getParameter("uselect"));
              objUserMaster.setFirstName(request.getParameter("fname"));
              objUserMaster.setMiddleName(request.getParameter("mname"));
              objUserMaster.setLastName(request.getParameter("lname"));
              objUserMaster.setSufix(request.getParameter("sname"));
             // objUserMaster.setDob(dob);
              objUserMaster.setGender(request.getParameter("gender"));
              objUserMaster.setCommunicationAddress(request.getParameter("comAdd_sel"));
              objUserMaster.setEmailId(request.getParameter("email"));
              objUserMaster.setUserId(userId);
              objUserMaster.setLoginName(request.getParameter("usrname"));
              objUserMaster.setSecretQuestion(request.getParameter("QSelect"));
              objUserMaster.setSecretAnswer(request.getParameter("ans"));

              request.setAttribute("usrname", request.getParameter("usrname"));

             /* if (request.getParameter("confPwd") != null && request.getParameter("confPwd").trim().length() != 0) {
                  request.setAttribute("cpwd", request.getParameter("confPwd"));
              } else {
                  String pwd = request.getParameter("confPwd");

                  Debug.print("context.getAttribute(pwd) :" + pwd);
                  request.setAttribute("cpwd", pwd);
              }*/

              String finalPrimaryPh = "";

              if (request.getParameter("pphone_txt") != null) {
                  Debug.print("request.getParameter(pphone_txt) value:" + request.getParameter("pphone_txt"));

                  StringTokenizer strTkns = new StringTokenizer(request.getParameter("pphone_txt"), "[](),.-{}");

                  while (strTkns.hasMoreTokens()) {
                      try {
                          String phNo = (String) strTkns.nextToken();

                          if (phNo != null && phNo.trim().length() != 0) {
                              Debug.print("ph no Added from Stokenizer:" + phNo);
                              finalPrimaryPh = finalPrimaryPh + phNo;
                          }
                      } catch (Exception e) {
                          Debug.print("ph no tokanizing exception:" + e);
                          
                      }
                  }

                  Debug.print("finally appended primary phNo :" + finalPrimaryPh);
              }

              String finalPrimaryMob = "";

              if (request.getParameter("pmob_txt") != null) {
                  Debug.print("request.getParameter(pmob_txt) value:" + request.getParameter("pmob_txt"));

                  StringTokenizer strTkns = new StringTokenizer(request.getParameter("pmob_txt"), "[](),.-{}");

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

                  Debug.print("finally appended finalPrimaryMob :" + finalPrimaryMob);
              }

              String finalPrimaryFax = "";

              if (request.getParameter("pfax_txt") != null) {
                  Debug.print("request.getParameter(pfax_txt) value:" + request.getParameter("pfax_txt"));

                  StringTokenizer strTkns = new StringTokenizer(request.getParameter("pfax_txt"), "[](),.-{}");

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

                  Debug.print("finally appended finalPrimaryFax :" + finalPrimaryFax);
              }

              objContact.setContactType(request.getParameter("priAdd_cbx"));
              objContact.setAddress1(request.getParameter("padd_txt"));
              objContact.setAddress2(request.getParameter("padd_txt2"));
              objContact.setCity(request.getParameter("pcity_txt"));
              objContact.setState(request.getParameter("pstate_sel"));
              objContact.setCountry(request.getParameter("pcountry_sel"));
              objContact.setZip(request.getParameter("pzip_txt"));
              objContact.setPhoneNo(finalPrimaryPh);
              objContact.setMobileNo(finalPrimaryMob);
              objContact.setFaxNo(finalPrimaryFax);
              objContact.setUserId(userId);
              System.out.println("nonUseaEmail.status:=" + request.getParameter("nonUseaEmail"));
              System.out.println("nonUseaMail.status:=" + request.getParameter("nonUseaMail"));

              if (request.getParameter("nonUseaEmail") != null) {
                  objUserMaster.setNonUseaEmailStatus(true);
                  System.out.println("commit nonUseaEMail status--true");
              } else {
                  objUserMaster.setNonUseaEmailStatus(false);
                  System.out.println("commit nonUseaEMail status--false");
              }
              if (request.getParameter("nonUseaMail") != null) {
                  objUserMaster.setNonUseaMailingStatus(true);
                  System.out.println("commit nonUseaMail status--true");
              } else {
                  objUserMaster.setNonUseaMailingStatus(false);
                  System.out.println("commit nonUseaMail status--false");
              }
              db.editUserDetails(objUserMaster, objContact);
              
              try {
    				db.updateRowUserMaster(objUserMaster);
    			} catch (SQLException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}

              Debug.print("objUserMaster.getLoginName() :" + objUserMaster.getLoginName());
              Debug.print("objUserMaster.getEmailId() :" + objUserMaster.getEmailId());

              boolean result = new MySQLDAO().updateUserDetailToMqSQL(objUserMaster, objContact);
              Debug.print("                MySql Result :" + result);

              /*
               * If Secondary address is updated or added
               */

              String secstat = request.getParameter("cttyp");
              String userid = request.getParameter("userid");

              System.out.println("sec status :" + secstat);
              System.out.println("user id :" + userid);


              if (request.getParameter("secAdd_cbx") != null) {
                  objContact.setUserId(userid);
                  objContact.setContactType(request.getParameter("secAdd_cbx"));
                  objContact.setAddress1(request.getParameter("sadd_txt"));
                  objContact.setAddress2(request.getParameter("sadd_txt1"));
                  objContact.setCity(request.getParameter("scity_txt"));
                  objContact.setState(request.getParameter("sState_sel"));
                  objContact.setCountry(request.getParameter("sCountry_sel"));
                  objContact.setZip(request.getParameter("szip_txt"));

                  Debug.print("request.getParameter(sphone_txt) value:" + request.getParameter("sphone_txt"));

                  String finalSecPh = "";
                  if (request.getParameter("sphone_txt") != null) {

                      StringTokenizer strTkns1 = new StringTokenizer(request.getParameter("sphone_txt"), "[](),.-{}");


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
                  if (request.getParameter("smob_txt") != null) {

                      StringTokenizer strTkns1 = new StringTokenizer(request.getParameter("smob_txt"), "[](),.-{}");


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
                  if (request.getParameter("sfax_txt") != null) {

                      StringTokenizer strTkns1 = new StringTokenizer(request.getParameter("sfax_txt"), "[](),.-{}");


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

                  objContact.setPhoneNo(finalSecPh);
                  objContact.setFaxNo(finalSecFax);
                  objContact.setMobileNo(finalSecMob);
                
                  db.editUserDetails(objUserMaster, objContact);

              }

              if (secstat != null && request.getParameter("secAdd_cbx") == null) {
                  boolean delstat = humanMemb.deleteContactDetail(userId, "Secondary");
                  System.out.println("Secondary Contact Delete Status :" + delstat);
              }
              request.setAttribute("source", source);
              
              boolean status1=false;
           
              return new ModelAndView("index");
          }
         
          else if(usrProcess != null && usrProcess.equals("initchgPwd")){
        	  
        	  
        	  return new ModelAndView("frmChangePassword");  
        	  
          }else if(usrProcess != null && usrProcess.equals("chngPwd")){
        	  
        	  String encryptUserPass= null;
	          String encryptfNameLname= null;
	          String userId=(String)session.getAttribute("userId");
        	  
  	          
	  	      
        	        	 
              
              String oldPass=request.getParameter("currPwd");
              String newPass=request.getParameter("newPwd");
              String reNewPass=request.getParameter("reNewPwd");
              
              String password=db.getPasswordByLoginId(userId);
              BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
             textEncryptor.setPassword("sa");
              String  decryptPwd=textEncryptor.decrypt(password);
              
              System.out.println("decrypted Password : "+decryptPwd);
              System.out.println("Prabhu here-------------");
              
              
              if(decryptPwd.equals(oldPass) && newPass.equals(reNewPass)){
            	  
             
              
              Debug.print("user id from session :" +userId);
             
              Debug.print("changed password :" +newPass);
              BasicTextEncryptor textEncryptor1 = new BasicTextEncryptor();
             textEncryptor1.setPassword("sa");
              boolean status = humanMemb.changePassword(userId,textEncryptor1.encrypt(newPass));
              
              Debug.print("changePassword status :" +status);
              
              if(status==true)
                  
              {
                  System.out.println("Password Changed Successfully");
                  System.out.println("New Password is : "+newPass);
                  /* =====================================
                       *
                       * Sending confirmation E-mail
                       *
                       * ====================================*/
                  		
                      //String emailid=(String)session.getAttribute("emailId");
                  	  String emailid = db.getEmailIdByPassword(userId);
                      System.out.println("User emailid :"+emailid);
                      String toMailIds[] = {emailid};
                      EmailContent email=new EmailContent();
                      email.setTo(toMailIds);
                      email.setFrom("dhivya@digiblitz.in");
                      email.setSubject("Your Account Info !");
                      
                      String content = "<table width=\"530\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                              " <tr>" +
                              " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                              " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                              "<tr>" +
                              "<td height=\"70\" valign=\"top\"><img src=\"images/logo3.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
                              " </tr>"+
                              "  <tr>"+
                              "<td valign=\"top\">"+
                              "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"+
                              "<tr>"+
                              "<td width=\"7\" height=\"12\" valign=\"top\">&nbsp;</td> " +
                              
                              "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>" +
                              "<td width=\"7\" height=\"12\" valign=\"top\">&nbsp;</td>   " +
                           
                              "</tr>"+
                              "<tr>"+
                              "<td valign=\"top\" background=\"https://dashboard.useventing.com/dashboard/images/left.jpg\">&nbsp;</td>"+
                              "<td valign=\"top\" bgcolor=\"#FBF2F2\">"+
                              "<span class=\"boldTxt\">Dear User"+"</span>,<br /><br />"+
                              "<p>Please save this email for your records. Your Password Change Request is Succesful !! Your account information is as follows:.<p>"+"<p>----------------------------<p>"+"<p> New Password : "+newPass+"<p> ----------------------------<p>"+
                              /*"<p>Your account would be activated through your confirmation by visiting the following link: <p>"+
                              "<a href=http://192.168.3.98:8090/dashboad-war/uservalidate.do?email="+request.getParameter("email")+"> Click Here to Activate your Account </a>"+*/
                              "Thank you for using the service provided by <span class=\"boldTxt\">United States Eventing Association</span>.</p>"+
                              "Thank You <br />"+
                              "------------------ <br />"+
                              "<span class=\"boldRedTxt\">HLC Team</span></td>"+
                              "<td valign=\"top\" background=\"https://dashboard.useventing.com/dashboard/images/Rght.jpg\">&nbsp;</td>"+
                              "</tr>"+
                              "<tr><td valign=\"top\" background=\"https://dashboard.useventing.com/dashboard/images/cornerBotLeft.jpg\">&nbsp;</td>"+
                              "<td valign=\"top\" background=\"https://dashboard.useventing.com/dashboard/images/cornerBot.jpg\">&nbsp;</td>"+
                              "<td valign=\"top\" background=\"https://dashboard.useventing.com/dashboard/images/cornerBotRght.jpg\">&nbsp;</td>"+
                              "</tr>"+
                              " </table>"+
                              "</td></tr>"+
                              "<tr>"+
                              "<td valign=\"top\" style=\"padding:10px;\">"+                            
                              "and much more..."+
                              "So go ahead and <a href=\"http://localhost:8080/klugwerks/login.html?cmd=initLogin\">LOGIN NOW!</a></td>"+
                              "</tr>"+
                              " <tr>"+
                              "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"></td>"+
                              "</tr>"+
                              "</table>";
                      
                      
                      email.setBody(content);
                      //email.setAttachments();
                      
                     /* EmailEngine emailEngine = new EmailEngine();
                      boolean emailFlag = emailEngine.sendMimeEmail(email);
                      Debug.print("Email sent sucessfully :"+emailFlag);*/
                      
                      Debug.print("Email ID :"+email);
                	  MailMail mail = new MailMail();
                	  boolean emailFlag = mail.sendMimeEmail(email);
                      Debug.print("Email sent sucessfully :"+emailFlag);
                  
             /*--------------Active Directory Password reset start here---------------------------------*/
                      String firstName = null;
                      String lastName = null;
                      String username = null;
                      ArrayList userNameDetails = db.getFirstLastUserNameByUserId(userId);
                      if(userNameDetails!=null && userNameDetails.size()!=0){
      					Iterator ituserNameDetails = userNameDetails.iterator();
      					int i=1;
      					while(ituserNameDetails.hasNext()){
      					
      						String strUserList[]= (String[])ituserNameDetails.next();
      						firstName = strUserList[0];
      						lastName = strUserList[1];
      						username = strUserList[2];
      						System.out.println("email_id :::::::::::::::::::::"+firstName);
      						System.out.println("passwordusr :::::::::::::::::::::"+lastName);
      						System.out.println("username :::::::::::::::::::::"+username);
      					}
      				}
                      String userNamePass = reNewPass+"-sep-"+username;
          	          String fNameLname = firstName+"-sep-"+lastName;
          	        try {
						encryptUserPass = ccc.encrypt(userNamePass, "digiblitz");
					} catch (InvalidKeyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchPaddingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvalidAlgorithmParameterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalBlockSizeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BadPaddingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        	  	      try {
						encryptfNameLname = ccc.encrypt(fNameLname, "digiblitz");
					} catch (InvalidKeyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchPaddingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvalidAlgorithmParameterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalBlockSizeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BadPaddingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        	  	      
        	  	    //String redirectsite = "http://198.71.58.51:56122/ResetPassword.aspx?UN="+encryptfNameLname+"&PWD="+encryptUserPass;
        	  	    String redirectsite = properties.getProperty("config.SP_reset_pwd_url")+"/ResetPassword.aspx?UN="+encryptfNameLname+"&PWD="+encryptUserPass;
        	         System.out.println("rediredt url :::::::::::::::::::"+redirectsite);
        	         response.sendRedirect(redirectsite);
        	 /*------------------Active directory password reset end here-------------------------*/
                      
                    //  return new ModelAndView("frmChange-pwd-conf");  
              }

              }
              
              else
              {
                  String stat="fail";
                  request.setAttribute("status",stat);
                  //return new ModelAndView("frmChange-pwd-conf");
                  return new ModelAndView("frmChangePassword");
               }
              }
         
         
//karthika started here-----------------------------------------------------
         
          else  if(usrProcess.equalsIgnoreCase("eConfig"))
          {
        	  
        	  String firstName = request.getParameter("name");
              String emailId = request.getParameter("email");
              String phone = request.getParameter("phone");
              String company = request.getParameter("company");
              String description = request.getParameter("message");
              
              
              
              String contactFormType = null;
              ContactUserCRMMethods contactUserCRMMethods = new ContactUserCRMMethods();
        	  String status1 = null;
        	               
              String crmType = "Target";
              
              
              contactUser.setFirstName(firstName);
              contactUser.setLastName("");
              contactUser.setEmailId(emailId);
              contactUser.setPhoneNo(phone);
              contactUser.setCompanyName(company);  
              contactUser.setDescription(description);
              
              contactUser.setConsultation("");
              contactUser.setFreeDemo("");
              contactUser.setWebinar("");
              contactUser.setMailingList("");
              
              contactUser.setCrmType(crmType);
              
              contactFormType = "ContactUs";
              
              contactUser.setContactFormType(contactFormType);
              
              //contactService.suiteCRMTarget(contactUser);
              
              //contactClient.contact(contactUser);
              try{
      			status1 = contactUserCRMMethods.suiteCRMTarget(firstName, "", emailId, phone, company, description);
      			System.out.println("status1 ---> "+status1);
      			 	  		
      		}catch(Exception e){
      			e.printStackTrace();
      		}
             
              log.info("in login action ");
              log.info("firstName ---> "+firstName+" emailId---> "+emailId+" phone---> "+phone);
              log.info("company ---> "+company+" description---> "+description);
              
/*       	   System.out.println("inside eConfig:::::::::::::::::::::::");
       	   String usrname= "";
       	   usrname = request.getParameter("name");
           String emailid = "";
           emailid = request.getParameter("email");
       	   String phn = "";
       	   phn = request.getParameter("phone");
       	   String company = "";
       	   company = request.getParameter("company");
       	   String msg = "";
       	   msg = request.getParameter("message");
       	   boolean status = false;
       	   String dbStatus = "fails";
       	   if((usrname != "") && (emailid != "") && (phn != "") && (msg != "")&& (company != "")){
       	   status = db.insertUserEnquriyDetails(usrname, emailid, phn, msg, company);
       	   System.out.println("inside eConfig status:::::::::::::::::::::::"+status);
       	   request.setAttribute("status",dbStatus );
       	   }else{
       		request.setAttribute("name",usrname );
            request.setAttribute("email",emailid );
            request.setAttribute("phone",phn );
            request.setAttribute("company",company );
            request.setAttribute("message",msg );
       		request.setAttribute("status",dbStatus );
       		System.out.println("parameter value is empty::::::::::");
       	   }
       	   
           if(status==true)
           {
        	   dbStatus = "success";
              System.out.println("inside eConfig status:::::::::::::::::::::::"+status);
              request.setAttribute("name",usrname );
              request.setAttribute("email",emailid );
              request.setAttribute("phone",phn );
              request.setAttribute("company",company );
              request.setAttribute("message",msg );
              request.setAttribute("status",dbStatus );
        	   String toMailIds[] = {emailid};
              EmailContent email = new EmailContent();
              email.setTo(toMailIds);
              email.setFrom("karthikadec1@gmail.com");
              email.setSubject("Acknowledgement !");
             
              String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                      " <tr>" +
                      " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                      " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                      "<tr>" +
                      "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
                      " </tr>"+
                      "  <tr>"+
                      "<td valign=\"top\">"+
                      "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"+
                      "<tr>"+
                      "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopLeft.jpg\" width=\"13\" height=\"12\" /></td> " +
                      "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>" +
                      "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopRght.jpg\" width=\"13\" height=\"12\" /></td>" +
                      "</tr>"+
                      "<tr>"+
                      "<td valign=\"top\" background=\"images/left.jpg\">&nbsp;</td>"+
                      "<td valign=\"top\" bgcolor=\"#FBF2F2\">"+
                      "<span class=\"boldTxt\">Dear user,</span>,<br /><br />"+
                      "<p>Please save this email for your records. Your account information is as follows:.<p>"+
                      "<p> --------------------- <p>"+
                     
                      "<p> Your Password has been changed !!<p>"+
                      "<p> --------------------- <p>"+
                      "Thank you for using the service provided by <span class=\"boldTxt\">United States Eventing Association</span>.</p>"+
                      "Thank You <br />"+
                      "------------------ <br />"+
                      "<span class=\"boldRedTxt\">HLC Team</span></td>"+
                      "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>"+
                      "</tr>"+
                      "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>"+
                      "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>"+
                      "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>"+
                      "</tr>"+
                      " </table>"+
                      "</td></tr>"+
                      "+<tr>"+
                      "<td valign=\"top\" style=\"padding:10px;\">"+
                      "<img src=\"images/pic.jpg\" width=\"272\" height=\"76\" style=\"float:right; padding-left:8px; padding-bottom:8px;\" />"+
                      "<p>The easiest way to access your day to day HLC activities online or offline where ever you are and when ever you want."+
                      "</p>If you are a NEW VISITOR, register now and ENJOY the following privileges:"+
                      "<ul>"+
                      "<li>Unlimited shopping online.</li>"+
                      "<li>Place advertisements online and/or on-site.</li>"+
                      "<li>Sponsor competitions held by HLC.</li>"+
                      "</ul>"+
                      
                      
                      "Also, REGISTER NOW! and become a member of HLC to access and 	enjoy the following privileges as per your Membership Type and as "+
                      "per your �Role� assigned:"+
                      
                      "<ul>"+
                      "<li>Compete in Equestrian Events held by HLC.</li>"+
                      "<li>Take part in other events like; Annual Meetings, Educational events,"+
                      "Activity Meetings held by HLC etc.</li>"+
                      "<li>Send Messages to other members.</li>"+
                      "<li>Create your own Distribution Lists.</li>"+
                      "<li>Create/Join a group and share your thoughts and common ideas.</li>"+
                      " <li>Unlimited Shopping online.</li>"+
                      " <li>Place advertisements online and/or on-site.</li>"+
                      " <li>Sponsor competitions held by HLC.</li>"+
                      "</ul>"+
                      
                      "and much more..."+
                      "So go ahead and <a href=\"#\">REGISTER NOW!</a></td>"+
                      "</tr>"+
                      " <tr>"+
                      "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>"+
                      "</tr>"+
                      "</table>";
              
              
              email.setBody(content);
              //email.setAttachments();
 
              MailMail mail = new MailMail();
              boolean emailFlag = mail.sendMimeEmail(email);
              Debug.print("Email sent sucessfully :"+emailFlag);
           }*/
                   	   return new ModelAndView("frmContacts");
          }
          else  if(usrProcess.equalsIgnoreCase("eConfig"))
          {
        	  ContactUserCRMMethods contactUserCRMMethods = new ContactUserCRMMethods();
        	  String status1 = null;
        	  
        	  String firstName = "amalesh";
              String emailId = request.getParameter("email");
              String phone = request.getParameter("phone");
              String company = request.getParameter("company");
              String description = request.getParameter("message");
        		
        		try{
        			status1 = contactUserCRMMethods.suiteCRMTarget(firstName, "", emailId, "", "", description);
        			System.out.println("status1 ---> "+status1);
        			 	  		
        		}catch(Exception e){
        			e.printStackTrace();
        		}
        	  
        	  
        	  return new ModelAndView("frmContacts");
          }
          else if(usrProcess.equalsIgnoreCase("initConfig")){
        	  
        	 
        	  
        	  request.setAttribute("status", "init");
        	  return new ModelAndView("frmContacts");
          }
         
 // karthika ends here---------------------------------------------------------------------------------  
         
         
          else if(usrProcess!=null)

              if(usrProcess.equalsIgnoreCase("view"))
              { 
            	  
            	  return new ModelAndView("frmForgetPassword");  
              }
              
              if(usrProcess.equalsIgnoreCase("Password"))
              {
                  Debug.print("inside forget user name block ...........");
                  String usrEmail=request.getParameter("e_mail");
                  Debug.print("usrname :" +usrEmail);
                 String firstName = null;
                 String lastName = null;
                 
                 ArrayList fullUserDetails=new ArrayList();
                 fullUserDetails = db.getFullLoginDetailsByEmailId(usrEmail);
                 
                 if(fullUserDetails!=null && fullUserDetails.size()!=0){
						Iterator itEntList = fullUserDetails.iterator();
						int i=1;
						while(itEntList.hasNext()){
						
							String strRolelist[]= (String[])itEntList.next();
							firstName = strRolelist[1];
							lastName = strRolelist[2];
							System.out.println("firstName  in forget username"+firstName);
							System.out.println("lastName in forget username"+lastName);;
						}
                 }
							
							
                   ArrayList det=new ArrayList();
                   det=humanMemb.getLoginDetailsByEmailId(usrEmail);
                   
                 
                  if(det.size() == 1){
                       for(int i=0;i<det.size();i++){
                          String lname=(String)det.get(i);
                          if(lname==null){
                              Debug.print("Login Name is "+lname+"     Redirected to user sign up page ");
                              System.out.println("Login Name:-----------------------"+lname);
                              request.setAttribute("loginName","notFound");
                              request.setAttribute("response_status","fail");
                              
                              //String fromAddress = "prabhu.pandi@digiblitz.in";
                               String fromAddress = properties.getProperty("infusionMail.fromAddress");
                               String toAddress = usrEmail;
                               String ccAddresses = "";
         	                   ccAddresses = properties.getProperty("infusionMail.ccAddress");
         	                   String bccAddresses = "";
         	                   bccAddresses = properties.getProperty("infusionMail.bccAddress");
                               String contentType = "HTML";
                               String subject = "Forget User Details!";
                               String htmlBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
         	                          "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
        	                          "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
        	                          " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
        	                          "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Dear Visiter</h4><table style=\" clear: both; margin: 0 auto;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
        	                            "<tbody> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Registration Details</h4></td>" +
        	                            "</tr> </tbody> </table> <p>&nbsp; </p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td align=\"center\" valign=\"top\" style=\"background-color: #fff;  padding: 16px; text-align: center; vertical-align: top;\">" +
        	                            "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody> <tr> <td style=\"color: #888;\" align=\"center\" valign=\"top\" style=\" padding: 16px; text-align: center; vertical-align: top;\">" +
        	                            "<p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
        	                            "<strong>User Name :</strong><br /> Not Found! Please Visit the Product site and Register</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
        	                            "</td> </tr> </tbody></table></td></tr></tbody></table></div></div></div></div></body></html>";
                               
                               String textBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
         	                          "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
        	                          "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
        	                          " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
        	                          "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Dear Visiter</h4><table style=\" clear: both; margin: 0 auto;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
        	                            "<tbody> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Registration Details</h4></td>" +
        	                            "</tr> </tbody> </table> <p>&nbsp; </p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td align=\"center\" valign=\"top\" style=\"background-color: #fff;  padding: 16px; text-align: center; vertical-align: top;\">" +
        	                            "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody> <tr> <td style=\"color: #888;\" align=\"center\" valign=\"top\" style=\" padding: 16px; text-align: center; vertical-align: top;\">" +
        	                            "<p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
        	                            "<strong>User Name :</strong><br /> Not Found! Please Visit the Product site and Register</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
        	                            "</td> </tr> </tbody></table></td></tr></tbody></table></div></div></div></div></body></html>";
                               
                               System.out.println("infusion soft property values: ----> "+fromAddress+" toAddress "+toAddress+" subject "+subject+" htmlBody "+" textBody "+textBody);
                               System.out.println("ccAddresses "+ccAddresses+" bccAddresses "+bccAddresses);
                               //Infusion Soft mail starts
                                                
                               //obj1.sendEmail(properties.getProperty("infusionMail.fromAddress"), usrEmail, properties.getProperty("infusionMail.ccAddresses"), properties.getProperty("infusionMail.bccAddresses"), properties.getProperty("infusionMail.contentType"), properties.getProperty("infusionMail.subject"), properties.getProperty("infusionMail.htmlBody"), properties.getProperty("infusionMail.textBody"));
	                           try {
	                        	   obj1.createAndCheckDuplicateContact(firstName, lastName, toAddress);
	                        	   obj1.optin_outEmail(toAddress);
								obj1.sendEmail(fromAddress, toAddress, ccAddresses, bccAddresses, contentType, subject, htmlBody, textBody);
							   } catch (XmlRpcException e) {
							    // TODO Auto-generated catch block
								e.printStackTrace();
							   }
                                       
                               System.out.println("Mail has been send successfully");                                 
                               //Infusion Soft mail ends
                              
                              return new ModelAndView("frmMemberUserSignup"); 
                          }
                       }
                  }
                   
                  if(det!=null)
                  {                     
                  
                  if(det.size()!=0)
                  {                   
                      String username="";              
                      Debug.print("det.size() :"+det.size());
                            
                      for(int i=0;i<det.size();i++)
                      {
                              String lname=(String)det.get(i);
                              if(lname!=null)
                              {
                                  username=username+lname+",";
                                  
                              }
                              
                              //String fromAddress = "prabhu.pandi@digiblitz.in";
                              String fromAddress = properties.getProperty("infusionMail.fromAddress");
                              String toAddress = usrEmail;
                              String ccAddresses = "";
        	                  ccAddresses = properties.getProperty("infusionMail.ccAddress");
        	                  String bccAddresses = "";
        	                  bccAddresses = properties.getProperty("infusionMail.bccAddress");
                              String contentType = "HTML";
                              String subject = "Forget User Details!";
                              String htmlBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
        	                          "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
       	                          "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
       	                          " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
       	                          "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Dear "+firstName+" "+lastName+"</h4><table style=\" clear: both; margin: 0 auto;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
       	                            "<tbody> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Registration Details</h4></td>" +
       	                            "</tr> </tbody> </table> <p>&nbsp; </p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td align=\"center\" valign=\"top\" style=\"background-color: #fff;  padding: 16px; text-align: center; vertical-align: top;\">" +
       	                            "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody> <tr> <td style=\"color: #888;\" align=\"center\" valign=\"top\" style=\" padding: 16px; text-align: center; vertical-align: top;\">" +
       	                            "<p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
       	                            "<strong>User Name :</strong><br /> "+lname+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
       	                            "</td> </tr> </tbody></table></td></tr></tbody></table></div></div></div></div></body></html>";
                              
                              String textBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
         	                          "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
        	                          "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
        	                          " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
        	                          "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Dear "+firstName+" "+lastName+"</h4><table style=\" clear: both; margin: 0 auto;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
        	                            "<tbody> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Registration Details</h4></td>" +
        	                            "</tr> </tbody> </table> <p>&nbsp; </p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td align=\"center\" valign=\"top\" style=\"background-color: #fff;  padding: 16px; text-align: center; vertical-align: top;\">" +
        	                            "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody> <tr> <td style=\"color: #888;\" align=\"center\" valign=\"top\" style=\" padding: 16px; text-align: center; vertical-align: top;\">" +
        	                            "<p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
        	                            "<strong>User Name :</strong><br /> "+lname+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
        	                            "</td> </tr> </tbody></table></td></tr></tbody></table></div></div></div></div></body></html>";
                              
                              System.out.println("infusion soft property values: ----> "+fromAddress+" toAddress "+toAddress+" subject "+subject+" htmlBody "+" textBody "+textBody);
                              System.out.println("ccAddresses "+ccAddresses+" bccAddresses "+bccAddresses);
                              //Infusion Soft mail starts
                                               
		                       //obj1.sendEmail(properties.getProperty("infusionMail.fromAddress"), usrEmail, properties.getProperty("infusionMail.ccAddresses"), properties.getProperty("infusionMail.bccAddresses"), properties.getProperty("infusionMail.contentType"), properties.getProperty("infusionMail.subject"), properties.getProperty("infusionMail.htmlBody"), properties.getProperty("infusionMail.textBody"));
		                          try {
		                       	   obj1.createAndCheckDuplicateContact(firstName, lastName, toAddress);
		                       	   obj1.optin_outEmail(toAddress);
									obj1.sendEmail(fromAddress, toAddress, ccAddresses, bccAddresses, contentType, subject, htmlBody, textBody);
								} catch (XmlRpcException e) {
								// TODO Auto-generated catch block
									e.printStackTrace();
								}
                                       
                               System.out.println("Mail has been send successfully");                                 
                               //Infusion Soft mail ends
                              
                              
                              Debug.print("username:======="+username);
                              Debug.print("emailid======"+usrEmail);
                              Debug.print("usrname's fetched :"+lname);
                              Debug.print("usrname's consolidated :"+username);
                              
                              
                            
                      }
                      
                  /* =====================================
                   *
                   * Sending Password E-mail
                   *
                   * ====================================*/
                      
                  String toMailIds[] = {usrEmail};
                  EmailContent email=new EmailContent();
                  email.setTo(toMailIds);
                  email.setFrom("dhivya@digiblitz.in");
                  email.setSubject("Your Account Details");
                  
                  String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                          " <tr>" +
                          " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                          " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                          "<tr>" +
                          "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
                          " </tr>"+
                          "  <tr>"+
                          "<td valign=\"top\">"+
                          "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"+
                          "<tr>"+
                          "<td width=\"13\" height=\"12\" valign=\"top\"></td> " +
                          "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>" +
                          "<td width=\"13\" height=\"12\" valign=\"top\"></td>" +
                          "</tr>"+
                          "<tr>"+
                          "<td valign=\"top\" background=\"images/left.jpg\">&nbsp;</td>"+
                          "<td valign=\"top\" bgcolor=\"#FBF2F2\">"+
                          "<span class=\"boldTxt\">Dear user,</span>,<br /><br />"+
                          "<p>Please save this email for your records. Your account information is as follows:.<p>"+
                          "<p> --------------------- <p>"+
                                                                             
                          "<p> User Name : "+username+"<p>"+                    
                                                
                          "<p> --------------------- <p>"+
                          "Thank you for using the service provided by <span class=\"boldTxt\">United States Eventing Association</span>.</p>"+
                          "Thank You <br />"+
                          "------------------ <br />"+
                          "<span class=\"boldRedTxt\">HLC Team</span></td>"+
                          "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>"+
                          "</tr>"+
                          "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>"+
                          "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>"+
                          "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>"+
                          "</tr>"+
                          " </table>"+
                          "</td></tr>"+
                          "<tr>"+
                          "<td valign=\"top\" style=\"padding:10px;\">"+                                                                                                                                                                   
                          "and much more..."+
                          "So go ahead and <a href=\"#\">REGISTER NOW!</a></td>"+
                          "</tr>"+
                          " <tr>"+
                          "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"></td>"+
                          "</tr>"+
                          "</table>";
                  
                  
                  email.setBody(content);
                  //email.setAttachments();
                  
                  MailMail mail = new MailMail();
                  boolean emailFlag = mail.sendMimeEmail(email);
                  Debug.print("Email sent sucessfully :"+emailFlag);
                  String stat="success";
                  request.setAttribute("status1",stat);
                  request.setAttribute("email",usrEmail);
                  System.out.println("email in jsp:"+usrEmail);
                  request.setAttribute("username",username);
                  System.out.println("username in jsp:"+username);
                  request.setAttribute("response_status","success");
                  return new ModelAndView("frmForgetPassword"); 
               
                  
                  }
                  else
                  {
                      String stat="fail";
                      request.setAttribute("status",stat);
                      request.setAttribute("response_status","fail");
                      return new ModelAndView("frmForgetPassword");   
                    
                      
                  }
                  }
                 
              }
              
               if(usrProcess.equalsIgnoreCase("User"))
              {    
            	   String firstName = null;
            	   String lastName = null;
                  
                  Debug.print("Inside forget pwd block ..........");
                  String secretqus=request.getParameter("sQustion");
                  String sAnswer=request.getParameter("sAnswer");
                  String usrname=request.getParameter("usrname");
                 
                  System.out.print("secretqus:"+secretqus);
                  System.out.print("sAnswer:"+sAnswer);
                  System.out.print("usrname:"+usrname);
                  //Debug.print("usrname :" +usrname);
                  //Debug.print("sAnswer :" +secretqus);
                 // Debug.print("secretqus :" +sAnswer);
                                   
                  // context.setAttribute("usrname",usrname);
                  
                  boolean status=humanMemb.checkSecurityQuestionByLoginName(usrname,secretqus,sAnswer);
                  Debug.print("remote.checkSecurityQuestionByLoginName(usrname,secretqus,sAnswer) :" +status);
                
                  if(status == true)
                  {
                      
                      Debug.print(" ::::::::::::::: inside secret q/a fail block send pwd mail ::::::::" );
                      String emailid = null;
                      emailid = humanMemb.getEmailByLoginName(usrname);
                      Debug.print("remote.getEmailByLoginName(usrname) :" +emailid);
                      Debug.print("remote.getPasswordByLoginName(usrname) :" +usrname);
                      String password=humanMemb.getPasswordByLoginName(usrname);
                      System.out.print("Password:"+password);
                      BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
                     textEncryptor.setPassword("sa");
                      String  decryptPwd=textEncryptor.decrypt(password);
                      //request.setAttribute("usrname", usrname);
                      
                      if(emailid != null && !emailid.isEmpty() && emailid != ""){
                      ArrayList fullUserDetails=new ArrayList();
                      fullUserDetails = db.getFullLoginDetailsByEmailId(emailid);
                      
                      if(fullUserDetails!=null && fullUserDetails.size()!=0){
     						Iterator itEntList = fullUserDetails.iterator();
     						int i=1;
     						while(itEntList.hasNext()){
     						
     							String strRolelist[]= (String[])itEntList.next();
     							firstName = strRolelist[1];
     							lastName = strRolelist[2];
     							System.out.println("firstName  in forget password"+firstName);
     							System.out.println("lastName in forget password"+lastName);;
     						}
                      }
                      }
                      
                       //String fromAddress = "prabhu.pandi@digiblitz.in";
                       String fromAddress = properties.getProperty("infusionMail.fromAddress");
                       String toAddress = emailid;
                       String ccAddresses = "";
 	                   ccAddresses = properties.getProperty("infusionMail.ccAddress");
 	                   String bccAddresses = "";
 	                   bccAddresses = properties.getProperty("infusionMail.bccAddress");
                       String contentType = "HTML";
                       String subject = "Forget User Password Details!";
                       String htmlBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
         	                          "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
        	                          "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
        	                          " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
        	                          "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Dear "+firstName+" "+lastName+"</h4><table style=\" clear: both; margin: 0 auto;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
        	                            "<tbody> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Registration Details</h4></td>" +
        	                            "</tr> </tbody> </table> <p>&nbsp; </p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td align=\"center\" valign=\"top\" style=\"background-color: #fff;  padding: 16px; text-align: center; vertical-align: top;\">" +
        	                            "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody> <tr> <td style=\"color: #888;\" align=\"center\" valign=\"top\" style=\" padding: 16px; text-align: center; vertical-align: top;\">" +
        	                            "<p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
        	                            "<strong>User Password :</strong><br /> "+decryptPwd+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
        	                            "</td> </tr> </tbody></table></td></tr></tbody></table></div></div></div></div></body></html>";
                       String textBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
  	                          "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
 	                          "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
 	                          " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
 	                          "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Dear "+firstName+" "+lastName+"</h4><table style=\" clear: both; margin: 0 auto;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
 	                            "<tbody> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Registration Details</h4></td>" +
 	                            "</tr> </tbody> </table> <p>&nbsp; </p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td align=\"center\" valign=\"top\" style=\"background-color: #fff;  padding: 16px; text-align: center; vertical-align: top;\">" +
 	                            "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody> <tr> <td style=\"color: #888;\" align=\"center\" valign=\"top\" style=\" padding: 16px; text-align: center; vertical-align: top;\">" +
 	                            "<p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
 	                            "<strong>User Password :</strong><br /> "+decryptPwd+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
 	                            "</td> </tr> </tbody></table></td></tr></tbody></table></div></div></div></div></body></html>";
                       
                       System.out.println("infusion soft property values: ----> "+fromAddress+" toAddress "+toAddress+" subject "+subject+" htmlBody "+" textBody "+textBody);
                       System.out.println("ccAddresses "+ccAddresses+" bccAddresses "+bccAddresses);
                       
                       //Infusion Soft mail starts
                       //obj1.sendEmail(properties.getProperty("infusionMail.fromAddress"), usrEmail, properties.getProperty("infusionMail.ccAddresses"), properties.getProperty("infusionMail.bccAddresses"), properties.getProperty("infusionMail.contentType"), properties.getProperty("infusionMail.subject"), properties.getProperty("infusionMail.htmlBody"), properties.getProperty("infusionMail.textBody"));
                   try {
                	   obj1.createAndCheckDuplicateContact(firstName, lastName, toAddress);
                	   obj1.optin_outEmail(toAddress);
					obj1.sendEmail(fromAddress, toAddress, ccAddresses, bccAddresses, contentType, subject, htmlBody, textBody);
                   } catch (XmlRpcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
                   }
                       System.out.println("Mail has been send successfully");                                 
                       //Infusion Soft mail ends
                      
                     
                      
                     
                      Debug.print("remote.getPasswordByLoginName(usrname) :" +decryptPwd);
                      
                  /* =====================================
                   *
                   * Sending User Name E-mail
                   *
                   * ====================================*/
                      
                  String toMailIds[] = {emailid};
                  EmailContent email=new EmailContent();
                  email.setTo(toMailIds);
                  email.setFrom("dhivya@digiblitz.in");
                  email.setSubject("Your Account Details");
                  
                  String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                          " <tr>" +
                          " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                          " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                          "<tr>" +
                          "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
                          " </tr>"+
                          "  <tr>"+
                          "<td valign=\"top\">"+
                          "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"+
                          "<tr>"+
                          "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopLeft.jpg\" width=\"13\" height=\"12\" /></td> " +
                          "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>" +
                          "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopRght.jpg\" width=\"13\" height=\"12\" /></td>" +
                          "</tr>"+
                          "<tr>"+
                          "<td valign=\"top\" background=\"images/left.jpg\">&nbsp;</td>"+
                          "<td valign=\"top\" bgcolor=\"#FBF2F2\">"+
                          "<span class=\"boldTxt\">Dear user,</span>,<br /><br />"+
                          "<p>Please save this email for your records. Your account information is as follows:.<p>"+
                          "<p> --------------------- <p>"+
                         
                          "<p> Password :"+decryptPwd+"<p>"+
                          "<p> --------------------- <p>"+
                          "Thank you for using the service provided by <span class=\"boldTxt\">United States Eventing Association</span>.</p>"+
                          "Thank You <br />"+
                          "------------------ <br />"+
                          "<span class=\"boldRedTxt\">HLC Team</span></td>"+
                          "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>"+
                          "</tr>"+
                          "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>"+
                          "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>"+
                          "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>"+
                          "</tr>"+
                          " </table>"+
                          "</td></tr>"+
                          "+<tr>"+
                          "<td valign=\"top\" style=\"padding:10px;\">"+
                          "<img src=\"images/pic.jpg\" width=\"272\" height=\"76\" style=\"float:right; padding-left:8px; padding-bottom:8px;\" />"+
                          "<p>The easiest way to access your day to day HLC activities online or offline where ever you are and when ever you want."+
                          "</p>If you are a NEW VISITOR, register now and ENJOY the following privileges:"+
                          "<ul>"+
                          "<li>Unlimited shopping online.</li>"+
                          "<li>Place advertisements online and/or on-site.</li>"+
                          "<li>Sponsor competitions held by HLC.</li>"+
                          "</ul>"+
                          
                          
                          "Also, REGISTER NOW! and become a member of HLC to access and 	enjoy the following privileges as per your Membership Type and as "+
                          "per your �Role� assigned:"+
                          
                          "<ul>"+
                          "<li>Compete in Equestrian Events held by HLC.</li>"+
                          "<li>Take part in other events like; Annual Meetings, Educational events,"+
                          "Activity Meetings held by HLC etc.</li>"+
                          "<li>Send Messages to other members.</li>"+
                          "<li>Create your own Distribution Lists.</li>"+
                          "<li>Create/Join a group and share your thoughts and common ideas.</li>"+
                          " <li>Unlimited Shopping online.</li>"+
                          " <li>Place advertisements online and/or on-site.</li>"+
                          " <li>Sponsor competitions held by HLC.</li>"+
                          "</ul>"+
                          
                          "and much more..."+
                          "So go ahead and <a href=\"#\">REGISTER NOW!</a></td>"+
                          "</tr>"+
                          " <tr>"+
                          "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>"+
                          "</tr>"+
                          "</table>";
                  
                  
                  email.setBody(content);
                  //email.setAttachments();
                  
                  MailMail mail = new MailMail();
                  boolean emailFlag = mail.sendMimeEmail(email);
                  Debug.print("Email sent sucessfully :"+emailFlag);
                  String stat="success";
                  request.setAttribute("status",stat);
                  request.setAttribute("usrname", usrname);
                  System.out.print("username:"+usrname);
                  request.setAttribute("emailid", emailid);
                  System.out.print("E-mail id:"+emailid);
                  request.setAttribute("pass",decryptPwd);
                  System.out.print("Password:"+decryptPwd);
                  request.setAttribute("response_status","success");
                  return new ModelAndView("frmForgetPassword");  
                    
                  }
                  
                  else
                  {
                      String stat="fail";
                      request.setAttribute("idstatus",stat);
                      request.setAttribute("response_status","fail");
                      return new ModelAndView("frmForgetPassword");   
                    
                      
                  }
                  
               
              }
              
               if(usrProcess.equalsIgnoreCase("resetpw"))
              { 
                  
                  Debug.print("::::::::::::::::::: Inside reset pwd update block ::::::::::::");
                  String usrname=request.getParameter("usrname");
                  Debug.print("context.getAttribute(usrname) :"+usrname);
                  
                  String password=request.getParameter("password");
                  Debug.print("request.getParameter(password) :"+password);
                  
                  boolean stat=humanMemb.updateLoginDetailsByLoginName(usrname,password);
                  
                  Debug.print("remote.updateLoginDetailsByLoginName(usrname,password) :"+stat);
                  
                   String emailid=humanMemb.getEmailByLoginName(usrname);
                   Debug.print("remote.getEmailByLoginName(usrname) :" +emailid);
                   
                   String firstName = null;
                   String lastName = null;
                   
                   ArrayList fullUserDetails=new ArrayList();
                   fullUserDetails = db.getFullLoginDetailsByEmailId(emailid);
                   
                   if(fullUserDetails!=null && fullUserDetails.size()!=0){
  						Iterator itEntList = fullUserDetails.iterator();
  						int i=1;
  						while(itEntList.hasNext()){
  						
  							String strRolelist[]= (String[])itEntList.next();
  							firstName = strRolelist[1];
  							lastName = strRolelist[2];
  							System.out.println("firstName  in forget username"+firstName);
  							System.out.println("lastName in forget username"+lastName);;
  						}
                   }
                   
                    /* =====================================
                   *
                   * Sending User Name E-mail
                   *
                   * ====================================*/
                      
                  String toMailIds[] = {emailid};
                  EmailContent email=new EmailContent();
                  email.setTo(toMailIds);
                  email.setFrom("dhivya@digiblitz.in");
                  email.setSubject("Your Account Details");
                  
                  String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                          " <tr>" +
                          " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                          " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                          "<tr>" +
                          "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
                          " </tr>"+
                          "  <tr>"+
                          "<td valign=\"top\">"+
                          "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"+
                          "<tr>"+
                          "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopLeft.jpg\" width=\"13\" height=\"12\" /></td> " +
                          "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>" +
                          "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopRght.jpg\" width=\"13\" height=\"12\" /></td>" +
                          "</tr>"+
                          "<tr>"+
                          "<td valign=\"top\" background=\"images/left.jpg\">&nbsp;</td>"+
                          "<td valign=\"top\" bgcolor=\"#FBF2F2\">"+
                          "<span class=\"boldTxt\">Dear user,</span>,<br /><br />"+
                          "<p>Please save this email for your records. Your account information is as follows:.<p>"+
                          "<p> --------------------- <p>"+
                         
                          "<p> Your Password has been changed !!<p>"+
                          "<p> --------------------- <p>"+
                          "Thank you for using the service provided by <span class=\"boldTxt\">United States Eventing Association</span>.</p>"+
                          "Thank You <br />"+
                          "------------------ <br />"+
                          "<span class=\"boldRedTxt\">HLC Team</span></td>"+
                          "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>"+
                          "</tr>"+
                          "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>"+
                          "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>"+
                          "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>"+
                          "</tr>"+
                          " </table>"+
                          "</td></tr>"+
                          "+<tr>"+
                          "<td valign=\"top\" style=\"padding:10px;\">"+
                          "<img src=\"images/pic.jpg\" width=\"272\" height=\"76\" style=\"float:right; padding-left:8px; padding-bottom:8px;\" />"+
                          "<p>The easiest way to access your day to day HLC activities online or offline where ever you are and when ever you want."+
                          "</p>If you are a NEW VISITOR, register now and ENJOY the following privileges:"+
                          "<ul>"+
                          "<li>Unlimited shopping online.</li>"+
                          "<li>Place advertisements online and/or on-site.</li>"+
                          "<li>Sponsor competitions held by HLC.</li>"+
                          "</ul>"+
                          
                          
                          "Also, REGISTER NOW! and become a member of HLC to access and 	enjoy the following privileges as per your Membership Type and as "+
                          "per your �Role� assigned:"+
                          
                          "<ul>"+
                          "<li>Compete in Equestrian Events held by HLC.</li>"+
                          "<li>Take part in other events like; Annual Meetings, Educational events,"+
                          "Activity Meetings held by HLC etc.</li>"+
                          "<li>Send Messages to other members.</li>"+
                          "<li>Create your own Distribution Lists.</li>"+
                          "<li>Create/Join a group and share your thoughts and common ideas.</li>"+
                          " <li>Unlimited Shopping online.</li>"+
                          " <li>Place advertisements online and/or on-site.</li>"+
                          " <li>Sponsor competitions held by HLC.</li>"+
                          "</ul>"+
                          
                          "and much more..."+
                          "So go ahead and <a href=\"#\">REGISTER NOW!</a></td>"+
                          "</tr>"+
                          " <tr>"+
                          "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>"+
                          "</tr>"+
                          "</table>";
                  
                  
                  email.setBody(content);
                  //email.setAttachments();
                  
                  MailMail mail = new MailMail();
                  boolean emailFlag = mail.sendMimeEmail(email);
                  Debug.print("Email sent sucessfully :"+emailFlag);
                  
                  String fromAddress = properties.getProperty("infusionMail.fromAddress");
                  String toAddress = emailid;
                  String ccAddresses = "";
                  ccAddresses = properties.getProperty("infusionMail.ccAddress");
                  String bccAddresses = "";
                  bccAddresses = properties.getProperty("infusionMail.bccAddress");
                  String contentType = "HTML";
                  String subject = "Your Password has been changed !!";
                  String htmlBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
    	                          "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
   	                          "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
   	                          " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
   	                          "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Dear "+firstName+" "+lastName+"</h4><table style=\" clear: both; margin: 0 auto;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
   	                            "<tbody> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Registration Details</h4></td>" +
   	                            "</tr> </tbody> </table> <p>&nbsp; </p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td align=\"center\" valign=\"top\" style=\"background-color: #fff;  padding: 16px; text-align: center; vertical-align: top;\">" +
   	                            "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody> <tr> <td style=\"color: #888;\" align=\"center\" valign=\"top\" style=\" padding: 16px; text-align: center; vertical-align: top;\">" +
   	                            "<p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
   	                            "<strong>User Password :</strong><br /> "+password+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
   	                            "</td> </tr> </tbody></table></td></tr></tbody></table></div></div></div></div></body></html>";
                  String textBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
	                          "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
                          "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
                          " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
                          "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Dear "+firstName+" "+lastName+"</h4><table style=\" clear: both; margin: 0 auto;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
                            "<tbody> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Registration Details</h4></td>" +
                            "</tr> </tbody> </table> <p>&nbsp; </p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td align=\"center\" valign=\"top\" style=\"background-color: #fff;  padding: 16px; text-align: center; vertical-align: top;\">" +
                            "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody> <tr> <td style=\"color: #888;\" align=\"center\" valign=\"top\" style=\" padding: 16px; text-align: center; vertical-align: top;\">" +
                            "<p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
                            "<strong>User Password :</strong><br /> "+password+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
                            "</td> </tr> </tbody></table></td></tr></tbody></table></div></div></div></div></body></html>";
                  
                  System.out.println("infusion soft property values: ----> "+fromAddress+" toAddress "+toAddress+" subject "+subject+" htmlBody "+" textBody "+textBody);
                  System.out.println("ccAddresses "+ccAddresses+" bccAddresses "+bccAddresses);
                  
                  //Infusion Soft mail starts
                  //obj1.sendEmail(properties.getProperty("infusionMail.fromAddress"), usrEmail, properties.getProperty("infusionMail.ccAddresses"), properties.getProperty("infusionMail.bccAddresses"), properties.getProperty("infusionMail.contentType"), properties.getProperty("infusionMail.subject"), properties.getProperty("infusionMail.htmlBody"), properties.getProperty("infusionMail.textBody"));
	              try {
	           	   obj1.createAndCheckDuplicateContact(firstName, lastName, toAddress);
	           	   obj1.optin_outEmail(toAddress);
					obj1.sendEmail(fromAddress, toAddress, ccAddresses, bccAddresses, contentType, subject, htmlBody, textBody);
	              } catch (XmlRpcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
	              }
                          
                  System.out.println("Mail has been send successfully");                                 
                  //Infusion Soft mail ends
                  
                  return new ModelAndView("frmResetPasswordCnf");  
                 
              }
               if(usrProcess.equalsIgnoreCase("homeDash"))
               {  
            	   
                return new ModelAndView("dashboard");  
               }
               else if(usrProcess != null && usrProcess.equals("initwhymenschforce")){
             	  
             	  
             	  return new ModelAndView("whymenschForce"); 
               }
               else if(usrProcess != null && usrProcess.equals("initManagedServices")){
              	  
              	  
              	  return new ModelAndView("frmManagedServices"); 
                }
               else if(usrProcess != null && usrProcess.equals("initHome")){
              	  
              	  
              	  return new ModelAndView("frmHome"); 
                }
               else if(usrProcess != null && usrProcess.equals("initFAQs")){
               	  
               	  
               	  return new ModelAndView("frmFAQ_Forums"); 
                 }
               else if(usrProcess != null && usrProcess.equals("privacy")){
                	  
                	  
                	  return new ModelAndView("Privacypolicy"); 
                  }
               else if(usrProcess != null && usrProcess.equals("memberRegiCnf")){
            	   
             	  String RegUserName = request.getParameter("userName");
             	  String email_id =null;
             	  String passwordusr = null;
             	 request.setAttribute("loginName",RegUserName);
             	 ArrayList emailPass = (ArrayList)db.getEmailAndPassByUsrname(RegUserName);
             	
				if(emailPass!=null && emailPass.size()!=0){
					Iterator itemailPass = emailPass.iterator();
					int i=1;
					while(itemailPass.hasNext()){
					
						String strcompanyList[]= (String[])itemailPass.next();
						email_id = strcompanyList[0];
						passwordusr = strcompanyList[1];
						System.out.println("email_id :::::::::::::::::::::"+email_id);
						System.out.println("passwordusr :::::::::::::::::::::"+passwordusr);
					}
				}
				BasicTextEncryptor textDecrypt = new BasicTextEncryptor();
				textDecrypt.setPassword("sa");
				String decryptPass = textDecrypt.decrypt(passwordusr);
             	request.setAttribute("pwd", decryptPass);
             	System.out.println("password send to user :::::::::::::::::::::"+decryptPass);
             	request.setAttribute("email",email_id);
             	request.setAttribute("response_status", "success");
             	  
             	  return new ModelAndView("frmMemberUserRegiCnf"); 
               }
               
               else if(usrProcess != null && usrProcess.equals("couponname")){

          		 String json = null;
          		 String couponcode=request.getParameter("prog");
          		 String cost=request.getParameter("cost");
          		
          		 String offer=db.getcouponoffer(couponcode);
          		 System.out.println("parasu checking -----offer--->"+offer);
          		 
          		if(offer != null){
          			int off = Integer.parseInt(offer);
          			double aa=Double.parseDouble(cost);  
          			int amount = (int)Math.round(aa);
             		 
          			int to=amount*off/100;
          			int total=amount-to;
          			db.updatecouponstatus(couponcode);
          			json = new Gson().toJson(total);				 
          		     response.setContentType("application/json");	             
          		     response.getWriter().write(json);
          		}else{
          			
          			json = new Gson().toJson(offer);				 
          		     response.setContentType("application/json");	             
          		     response.getWriter().write(json);
          		}
            }
               else if(usrProcess != null && usrProcess.equals("demorequest")){
            	   
            	   String firstName = request.getParameter("inf_field_FirstName");
                   String emailId = request.getParameter("inf_field_Email");
                   String phone = request.getParameter("inf_field_Phone1");
                  
                   
                   
                   String contactFormType = null;
                   
                  
                   String crmType = "Lead";
                   
                   contactUser.setFirstName(firstName);
                   contactUser.setLastName("");
                   contactUser.setEmailId(emailId);
                   contactUser.setPhoneNo(phone);
                   contactUser.setCompanyName("");  
                   contactUser.setDescription("");
                   
                   contactUser.setConsultation("");
                   contactUser.setFreeDemo("");
                   contactUser.setWebinar("");
                   contactUser.setMailingList("");
                   
                   contactUser.setCrmType(crmType);
                   
                   contactFormType = "ContactUs";
                   
                   contactUser.setContactFormType(contactFormType);
                   
                   //contactService.suiteCRMTarget(contactUser);
                   contactClient.contact(contactUser);
                   
                   log.info("in login action ");
                   log.info("firstName ---> "+firstName+" emailId---> "+emailId+" phone---> "+phone);
                  // log.info("company ---> "+company+" description---> "+description);
             	  
             	  
             	  return new ModelAndView("demoRequest"); 
               }
               else if(usrProcess != null && usrProcess.equals("emailQuery")){
            	Debug.print("Inside Email Query Action");
            	 
            	String eqName = request.getParameter("eqName");
            	String eqEmail = request.getParameter("eqEmail");
            	String eqDescription = request.getParameter("eqDescription");
            	String eqSubject = request.getParameter("eqSubject");
            	
            	String toAddress = null;
            	String contentType =null;
            	String subject =null;
            	String htmlBody =null;
            	String textBody=null;
            	
            	boolean emailStatus = false;
            	
            	if(eqSubject != null && eqSubject.equalsIgnoreCase("sales")){
            		
            		toAddress = properties.getProperty("infusionMail.salesAddress");
            		
            	}else if(eqSubject != null && eqSubject.equalsIgnoreCase("billing")){
            		
            		toAddress = properties.getProperty("infusionMail.billingAddress");
            		
            	}else if(eqSubject != null && eqSubject.equalsIgnoreCase("support")){
            		
            		toAddress = properties.getProperty("infusionMail.supportAddress");
            		
            	}
            	
            	 System.out.println("toAddress===="+toAddress);
            	 contentType = "HTML";
                 subject = eqSubject;
                 htmlBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
                        "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
                        "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
                        " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
                        "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 30px; margin: 16px 0 8px;padding: 0;text-align: left;\">Hello "+eqSubject+" team,</h4> </td> </tr><tr><td style=\"padding:0px 10px\"><p style=\"line-height: 10px;text-align:left;\">You have received query from "+eqName+".</td></tr> <tr><td style=\"padding:0px 10px\"><p style=\"line-height: 20px;text-align:left;\">Query : <span style=\"font-weight:bold\"> "+eqDescription+"</span></p></td></tr><tr><td style=\"padding:0px 10px\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>"+eqName+"</td></tr></tbody> </table> </td> </tr> </tbody> </table> </div></div></div></div></body></html>";            	
                /*String textBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
                        "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
                        "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
                        " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
                        "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Congratulations "+eqSubject+"</h4><p>"+eqDescription+"</p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </div></div></div></div></body></html>";*/
                textBody="";
                try{
	            	obj1.createAndCheckDuplicateContact(eqName, eqName, eqEmail);
	         	    obj1.optin_outEmail(eqEmail);
	         	   emailStatus = obj1.sendEmail(eqEmail, toAddress ,properties.getProperty("infusionMail.ccAddress"), properties.getProperty("infusionMail.bccAddress"), contentType, subject, htmlBody, textBody);
                }catch(Exception e){
                	e.printStackTrace();
                }
             	
                String status= null;
                Debug.print("status of query ====="+emailStatus);
                if(emailStatus){
                	status="eqSuccess";
                	subject = "Notification from "+eqSubject+" Team";
                    htmlBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
                           "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
                           "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
                           " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
                           "<tr><td  valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 25px; margin: 16px 0 8px;padding: 0;text-align: left;\">Dear "+eqName+",</h4></td> </tr><tr></tr> <tr><td style=\"padding:0px 10px\"><p style=\"margin: 20px 0 10px;text-align:left;\">Thank you for your interest in menschForce. Your query has been successfully submitted. You will be get in touch with our team soon.</td></tr><tr><td style=\"padding:0px 10px\"><p style=\"line-height: 20px;text-align:left;\">Query : <span style=\"font-weight:bold\"> "+eqDescription+"</span></p></td></tr><tr><td style=\"padding:0px 10px\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>"+eqSubject+" Team</td></tr></tbody> </table> </td> </tr> </tbody> </table> </div></div></div></div></body></html>";
		                  
                    	try{
		    	            	obj1.createAndCheckDuplicateContact(eqName, eqName, eqEmail);
		    	         	    obj1.optin_outEmail(eqEmail);
		    	         	   emailStatus = obj1.sendEmail(toAddress, eqEmail , "", "", contentType, subject, htmlBody, textBody);
		                    }catch(Exception e){
		                    	e.printStackTrace();
		                    }
                    	
                    	Debug.print("Sending mail to user====="+emailStatus);
                }else{
                	status="eqFail";
                	
                }
                
                request.setAttribute("status",status);
                request.setAttribute("eqSubject",eqSubject);
                return new ModelAndView("frmHome");
               
             	
               }
               else if(usrProcess.equals("adminusrReg")){
              	 String refName = null;
              	 String relName = null;
              	 String rePass = null;
              	 String encryptUserPass= null;
      	          String encryptfNameLname= null;
               try {
              		 
              	
               			
      /*------------------------------------PRABHU END HERE-------------------------------------------------*/
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
              	 String dob=null;
              	 
              
              	 

           
        	                	 
        	                	
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
        	        		                	if(parmName.equals("pwd")){
        	        		                		usrPwd = paramPart.getStringValue();
        	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
        	        		                		
        	        		                	}
        	        		                	if(parmName.equals("cpwd")){
        	        		                		usrCnfPwd = paramPart.getStringValue();
        	        		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
        	        		                		rePass = usrCnfPwd;
        	        		                		
        	        		                	}
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
//      	        		                    fileList = (List) fPart;
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
        	                	/*final String AB = "1Ab2Cd3Ef4Gh5IJ6kL7mN8oP9qR0";
        	          		Random rnd = new Random();
        	          		int len=10;							
        	          		StringBuilder sb = new StringBuilder( len );
        	          		   for( int ij = 0; ij < len; ij++ ) {
        	          		     sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );			
        	          		   }							  
        	          	String password=sb.toString();

        	          	System.out.println(password);*/
        	          //usrCnfPwd = password;
        	          String userNamePass = usrCnfPwd+"-sep-"+usrName;
        	          String fNameLname = fName+"-sep-"+lName;
        	          
      	  	      encryptUserPass = ccc.encrypt(userNamePass, "digiblitz");
      	  	      encryptfNameLname = ccc.encrypt(fNameLname, "digiblitz");
        	          
        /*------------------------------------Generating Password-------------------------------------------------*/ 	
               			
        	               	// /*------------------------------------PRABHU START HERE-------------------------------------------------/
        	                  /* String username = request.getParameter("usrname");
        	                   String firstname = request.getParameter("fname");
        	                   String lastname = request.getParameter("lname");
        	                   String usrPassword = request.getParameter("cpwd");*/
      	  	      
      	  	      
        	                   //NewUser nu = new NewUser(usrName , fName , lName , usrCnfPwd , "Sharepoint", usrEmail);
      	  	    System.out.println("usrName "+usrName +" fName "+ fName +" lName "+ lName +" usrCnfPwd "+ usrCnfPwd +" config.OU "+ properties.getProperty("config.OU") +" usrEmail "+ usrEmail);
      	  	    if(usrEmail.equalsIgnoreCase("") || usrEmail=="" ){
      	  	    	usrEmail="null";
      	  	    }
      	  	    NewUser nu = new NewUser(usrName , fName , lName , usrCnfPwd , properties.getProperty("config.OU"), usrEmail);
        	                 
      	  	System.out.println("User Email----->"+usrEmail);       
//        	                 NewUser nu = new NewUser(usrName , fName , lName , usrCnfPwd , "dBVMS", usrEmail);
        	                   
        	                   boolean addUsrStatus = nu.addUser();
        	                  // boolean addUsrToGroupStatus = nu.addUserToGroup();
        	           		//boolean status = nu.deleteUser("kamal123");
        	           		//nu.assignUser("kamal123", "JavaSample");
        	           		if (addUsrStatus == true){
        	           			System.out.println("user created in Active Directory succcessfully");	
        	           		
        	  /*------------------------------------PRABHU END HERE-------------------------------------------------*/
               			
               			
               			
               			
               			
              		 
        	           		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
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
                      if(usrDOB != null && usrDOB != "" && !usrDOB.equalsIgnoreCase("")){
                       String[] parts = usrDOB.split("-");
                       String part1 = parts[0]; // mm
                       String part2 = parts[1]; // dd
                       String part3 = parts[2]; //yy
                        dob = part3+"-"+part1+"-"+part2;
                      } 
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
                      	 request.setAttribute("response_status", "fail");
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
                              	 request.setAttribute("response_status", "fail");
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
                              	 request.setAttribute("response_status", "fail");
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
                              	 request.setAttribute("response_status", "fail");
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
                                  	 request.setAttribute("response_status", "fail");
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
                                  	 request.setAttribute("response_status", "fail");
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
                                  	 request.setAttribute("response_status", "fail");
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
                       request.setAttribute("email", usrEmail);
                       System.out.print("password:"+usrCnfPwd);
                       System.out.print("loginmane:"+usrName);
                       System.out.print("email:"+usrEmail);
                       /* =====================================
                       *
                       * Sending confirmation E-mail
                       *
                       * ====================================*/
                       
                 //=========Adding vendor details============//
                       
                       if(category != null && category.equalsIgnoreCase("vendor")){
                      	 
                      	 vendorBean.setvendor_fname(fName);
                           vendorBean.setvendor_lname(lName);
                           vendorBean.setvendor_email(usrEmail);
                           vendorBean.setvendor_company(company);
                           vendorBean.setvendor_homePhone(finalPrimaryMob);
                           vendorBean.setvendor_id(company);
                      
                           String Vendor_email=null;
                           if(usrid !=null){
                          
                        	   Vendor_email=db.getAllVendorDetailsByEmail(usrEmail);
                           
                        	 if(Vendor_email == null){
                        		  boolean insertvendorstatus=db.insertVendorContacts(vendorBean);
                        		  System.out.println("Updating vendor Query"+insertvendorstatus);  
                        	  }else{
                        		  System.out.println("vender email already exixts");
                        		 boolean updatevendorstatus=db.UpdateVendorByEmailID(vendorBean);
                          		System.out.println("Updating vendor Query"+updatevendorstatus); 
                        	  }
                       }
                       
                    	 
                      /*if(Vendor_email !=null && usrEmail != null && Vendor_email.equals(usrEmail)) {
                      	boolean updatevendorstatus= db.UpdateVendorUniqueID(vendorBean, usrid);
                      	System.out.println("Updating vendor Query"+updatevendorstatus);
                      }
                      else{	
                      	boolean insertvendorstatus=db.insertVendorContacts(vendorBean);
                      	System.out.println("Updating vendor Query"+insertvendorstatus);
                      	
           			}*/
           		}  
           			
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
                	   
                	 if( usrEmail.equalsIgnoreCase("null")){
                		 toAddress = (String)session.getAttribute("jobPostUserEmailID");
                	 }else{
                		 ccAddresses = (String)session.getAttribute("jobPostUserEmailID");
                	 }
                	 
                	 System.out.println("ccAddresses "+ccAddresses+" toAddress "+toAddress);
                  	 obj1.createAndCheckDuplicateContact(fName, lName, toAddress);
                  	 obj1.optin_outEmail(toAddress);
      				obj1.sendEmail(fromAddress, toAddress, ccAddresses, bccAddresses, contentType, subject, htmlBody, textBody);
                	 } catch (XmlRpcException e) {
      			// TODO Auto-generated catch block
      				e.printStackTrace();
      			}
                               
                               System.out.println("Mail has been send successfully");                                 
                      //Infusion Soft mail ends
                       
                      /*String emailid = usrEmail;
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
                              "<p>Please save this email for your records. Your account information is as follows:.<p>" + "<p>----------------------------<p>" + "<p>UserName :" + usrName + "<p> password: " + usrCnfPwd + "<p> ----------------------------<p>" +
                              "<p>Your account would be activated through your confirmation by visiting the following link: <p>"+
                              "<a href=http://192.168.3.98:8090/dashboad-war/uservalidate.do?email="+request.getParameter("email")+"> Click Here to Activate your Account </a>"+
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
                      Debug.print("Email sent sucessfully :" + emailFlag);*/
                      request.setAttribute("source", source);     
                      
      /*-----------------------------------------------PRABHU START HERE----------------------------------------------   */             
                      
               		} else{
               			System.out.println("Cannot registred in ldap: ");
               			request.setAttribute("response_status", "fail");
               		}	
      /*-----------------------------------------------PRABHU START HERE----------------------------------------------*/
              	 }catch (Exception ex) {
                       System.err.println("Caught an exception.");
                       request.setAttribute("response_status", "fail");
                       ex.printStackTrace();
                       
                   }
              	 
              	 
              	 //return new ModelAndView("frmMemberUserRegiCnf");	
//               String redirectsite = "http://198.71.58.51:19075/ResetPassLocal.aspx?UN="+encryptfNameLname+"&PWD="+encryptUserPass;
               
               
               
               //String redirectsite = "http://198.71.58.51:56122/ResetPassword.aspx?UN="+encryptfNameLname+"&PWD="+encryptUserPass;
               String redirectsite = properties.getProperty("config.SP_reset_pwd_url")+"/ResetPassword.aspx?UN="+encryptfNameLname+"&PWD="+encryptUserPass;
               
               System.out.println("rediredt url :::::::::::::::::::"+redirectsite);
               response.sendRedirect(redirectsite);
               //return new ModelAndView("frmMemberUserRegiCnf");	
              	// return mapping.findForward("Confirmation");
              	 
               }
               else if(usrProcess.equals("SignUpforFreeTrial")){
                	 String refName = null;
                	 String relName = null;
                	 String rePass = null;
                	 String encryptUserPass= null;
        	          String encryptfNameLname= null;
                
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
                    String product_plan =null;
   				 String subscriptionType =null;
   				 String regStatusId=null;
   				 String credit_card_type=null;
   				 String credit_card_no=null;
   				 String cvv_no=null;
   				 String name_on_card= null;
   				 String expiry_date=null;
   				 String payOption =null;
   				 String coupon_valid=null;
   				 String coupon_status=null;
   				 String coupon_value=null;
   				 String coupon_description=null;
   				 String coupon_code=null;
   				 String productPrice=null;
   				 
        	          try{
        	        	  
  							usrSalutation = request.getParameter("USelect");
                 		
  							fName= request.getParameter("fname");
                 		
                 		mName = request.getParameter("mname");
                 		
                 		lName = request.getParameter("lname");
                 		
                 		sName = request.getParameter("sname");
                 		
                 		usrDOB = request.getParameter("dat");
                 		
                 		gender = request.getParameter("gender");
                 		
                 		usrName = request.getParameter("usrname");
                 		
                 		usrEmail = request.getParameter("email");
                 		
                 		usrCompanyDetails = request.getParameter("company");
                 		
                 		usrCategory = request.getParameter("category");
                 		
                 		secretQuestion = request.getParameter("QSelect");
                 		
                 		secretAns = request.getParameter("ans");
                 		
                 		paddPlotNo = request.getParameter("padd_txt_plotNo");
                 		
                 		paddBuildNo = request.getParameter("padd_txt_buildNo");
                 		
                 		paddFloorNo = request.getParameter("padd_txt_floorNo");
                 		
                 		paddStreetNo = request.getParameter("padd_txt_strtAddr");
                 		
                 		paddLane = request.getParameter("padd_txt_lane");
                 		
                 		paddArea = request.getParameter("padd_txt_area");
                 		
                 		paddCity = request.getParameter("pcity_txt");
                 		
                 		paddCountry = request.getParameter("pcountry_sel");
                 		
                 		paddState = request.getParameter("pstate_sel");
                 		
                 		paddZip = request.getParameter("pzip_txt");
                 		
                 		paddCntryPhn = request.getParameter("country_phone_txt");
                 		
                 		paddAreaPhone = request.getParameter("area_phone_txt");
                 		
                 		paddNoPhone = request.getParameter("no_phone_txt");
                 		
                 		paddCntryMob = request.getParameter("country_mob_txt");
                 		
                 		paddNoMob = request.getParameter("no_mob_txt");
                 		
                 		paddCntryFax = request.getParameter("country_fax_txt");
                 		
                 		paddAreaFax = request.getParameter("area_fax_txt");
                 		
                 		paddNoFax = request.getParameter("no_fax_txt");
                 		
                 		secAddrReqOrNot =  request.getParameter("secAddrReqOrNot");
                 		
                 		saddPlotBuildFloor =  request.getParameter("sadd_txt");
                 		
                 		saddStreet =  request.getParameter("sadd_txt_strt_addr");
                 		
                 		saddLane =  request.getParameter("sadd_txt_laneNo");
                 		
                 		saddArea =  request.getParameter("sadd_txt_area");
                 		
                 		saddCity =  request.getParameter("scity_txt");
                 		
                 		saddCountry =  request.getParameter("scountry_txt");
                 		
                 		saddState =  request.getParameter("sstate_txt");
                 		
                 		saddZip =  request.getParameter("szip_txt");
                 		
                 		saddCntryPhone =  request.getParameter("s_country_phone_txt");
                 		
                 		saddAreaPhone =   request.getParameter("s_area_phone_txt");
                 		
                 		saddNoPhone =  request.getParameter("s_no_phone_txt");
                 		
                 		saddCntryMob =  request.getParameter("s_country_mob_txt");
                 		
                 		saddNoMob =  request.getParameter("s_no_mob_txt");
                 	
                 		saddCntryFax =  request.getParameter("s_country_fax_txt");
                 		
                 		saddAreaFax =  request.getParameter("s_area_fax_txt");
                 		
                 		saddNoFax = request.getParameter("s_no_fax_txt");
                 		
                 		PreferredAddrType = request.getParameter("PreferredAddrType");
                 		
                 		NonUseaEmail = request.getParameter("nonUseaEmail");
                 		
                 		NonUseaMail = request.getParameter("nonUseaMail");
                 		
                 		productPrice=request.getParameter("amount");
                 		
        coupon_status = request.getParameter("coupon_status");
        coupon_value = request.getParameter("coupon_value");
        coupon_description = request.getParameter("coupon_description");
        coupon_valid = request.getParameter("coupon_valid");
        coupon_code = request.getParameter("coupon_code");
        
         credit_card_type=request.getParameter("credit_card_type");
        credit_card_no=request.getParameter("credit_card_no");
        cvv_no=request.getParameter("cvv_no");
        name_on_card=request.getParameter("name_on_card");
        String expiry_month=request.getParameter("expiry_month");
        String expiry_year=request.getParameter("expiry_year");
        expiry_date = expiry_month+"/"+expiry_year;
        
        System.out.println("coupon_status --> "+coupon_status+" coupon_value --> "+coupon_value+" coupon_description --> "+coupon_description);
        System.out.println("coupon_valid --> "+coupon_valid+" coupon_code --> "+coupon_code+" coupon_code --> "+coupon_code);
        System.out.println("credit_card_type --> "+credit_card_type+" credit_card_no --> "+credit_card_no+" cvv_no --> "+cvv_no);
        System.out.println("name_on_card --> "+name_on_card+" expiry_month --> "+expiry_month+" expiry_year --> "+expiry_year);
        
        System.out.println("---------------------------------------------------------");
        System.out.println("starts");
        
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

  	


    //NewUser nu = new NewUser(usrName , fName , lName , usrCnfPwd , "Sharepoint", usrEmail);
  System.out.println("usrName "+usrName +" fName "+ fName +" lName "+ lName +" usrCnfPwd "+ usrCnfPwd +" config.OU "+ properties.getProperty("config.OU") +" usrEmail "+ usrEmail);
  NewUser nu = new NewUser(usrName , fName , lName , usrCnfPwd , properties.getProperty("config.OU"), usrEmail);


  //NewUser nu = new NewUser(usrName , fName , lName , usrCnfPwd , "dBVMS", usrEmail);
    
    boolean addUsrStatus = nu.addUser();
   // boolean addUsrToGroupStatus = nu.addUserToGroup();
  	//boolean status = nu.deleteUser("kamal123");
  	//nu.assignUser("kamal123", "JavaSample");
  	if (addUsrStatus == true){
  		System.out.println("user created in Active Directory succcessfully");	
  	
  /*------------------------------------PRABHU END HERE-------------------------------------------------*/






  	BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
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
  String dob = part3+"-"+part1+"-"+part2;

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
     product_plan = "basic";
  subscriptionType = "trail for 30 days";
  System.out.println("inside try_now :::::::::");

  payOption = "try_now";

  if(coupon_code == null){
  	
  	System.out.println("Without coupon code process:::::::::::");
  	String aCard = "";
      aCard = credit_card_no;
      System.out.print("Card number : ");
      String cvvno= cvv_no; 
      System.out.print("CVV number : ");
      System.out.print("Expire Date : ");
      String dt= expiry_date;
      
    if (ccard.getCardID(aCard) > -1) {
  	  
  	System.out.println("This card is supported.");
    		if(ccard.CVV(cvvno) == 4){
    			System.out.println("This CVV is supported.");
    		}       					   	   		
    else{
  			System.out.println("This CVV is not supported.");
  			request.setAttribute("cvvstatus","fail");
  			request.setAttribute("cardstatus","success");
  			request.setAttribute("fName",fName);
  			request.setAttribute("lName",lName);
  			request.setAttribute("emailId",usrEmail);
  			request.setAttribute("pay_productPlan",product_plan);
  			//String fromAddress = "prabhu.pandi@digiblitz.in";
      String fromAddress = properties.getProperty("infusionMail.fromAddress");
      String toAddress = usrEmail;
      String ccAddresses = properties.getProperty("infusionMail.ccPaymentAddress");
      String bccAddresses = properties.getProperty("infusionMail.bccPaymentAddress");
      String contentType = "HTML";
      String subject = "Registration Failure";
      String htmlBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
               "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
               "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
               " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
               "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Dear "+fName+" "+lName+"</h4><p style=\"   font-size: 19px;" +
                 "line-height: 27px; margin-bottom: 16px;margin-top: 16px; text-align: center;\">Sorry! Your Registration has failed</p><table style=\" clear: both; margin: 0 auto;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
                 "<tbody> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><a style=\"font-weight: 700; color:#fff;\" href=\"https://www.digiblitzonline.com:8843/menschforce/signUp.html?signUpProcess=getStarted\" target=\"_blank\"><span style=\" display: block;text-align: center; color: #fff;\">Please Click here to try again</span></a></td>" +
                 "</tr> </tbody> </table> <p>&nbsp; </p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td align=\"center\" valign=\"top\" style=\"background-color: #fff;  padding: 16px; text-align: center; vertical-align: top;\">" +
                 "</td></tr></tbody></table></div></div></div></div></body></html>";
      String textBody = "Sorry! Your Registration has failed";

      //Infusion Soft mail starts
                   
      //obj1.sendEmail(properties.getProperty("infusionMail.fromAddress"), usrEmail, properties.getProperty("infusionMail.ccAddresses"), properties.getProperty("infusionMail.bccAddresses"), properties.getProperty("infusionMail.contentType"), properties.getProperty("infusionMail.subject"), properties.getProperty("infusionMail.htmlBody"), properties.getProperty("infusionMail.textBody"));
      try {
     	 obj1.sendEmail(fromAddress, toAddress, ccAddresses, bccAddresses, contentType, subject, htmlBody, textBody);
     	 } 
      catch (XmlRpcException e) {
     	 e.printStackTrace();
     	 }
      System.out.println("Mail had been send successfully");                                 

      //Infusion Soft mail ends
      return new ModelAndView("signUpFail");
      }
    System.out.println("This a " + ccard.getCardName(ccard.getCardID(aCard)));
  		System.out.println("The card number " + aCard + " is good");              						  
    }
  else{
  	System.out.println("This card is invalid or unsupported!");
      request.setAttribute("cardstatus","fail");
      request.setAttribute("cvvstatus","success");
      request.setAttribute("fName",fName);
      request.setAttribute("lName",lName);
      request.setAttribute("emailId",usrEmail);
      request.setAttribute("pay_productPlan",product_plan);
      //String fromAddress = "prabhu.pandi@digiblitz.in";
      String fromAddress = properties.getProperty("infusionMail.fromAddress");
      String toAddress = usrEmail;
      String ccAddresses = properties.getProperty("infusionMail.ccPaymentAddress");
      String bccAddresses = properties.getProperty("infusionMail.bccPaymentAddress");
      String contentType = "HTML";
      String subject = "Registration Failure!";
      String htmlBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
                "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
                "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
                " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
                "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Dear "+fName+" "+lName+"</h4><p style=\"   font-size: 19px;" +
                  "line-height: 27px; margin-bottom: 16px;margin-top: 16px; text-align: center;\">Sorry! Your Registration has failed</p><table style=\" clear: both; margin: 0 auto;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
                  "<tbody> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><a style=\"font-weight: 700; color:#fff;\" href=\"https://www.digiblitzonline.com:8843/menschforce/signUp.html?signUpProcess=getStarted\" target=\"_blank\"><span style=\" display: block;text-align: center; color: #fff;\">Please Click here to try again</span></a></td>" +
                  "</tr> </tbody> </table> <p>&nbsp; </p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td align=\"center\" valign=\"top\" style=\"background-color: #fff;  padding: 16px; text-align: center; vertical-align: top;\">" +
                  "</td></tr></tbody></table></div></div></div></div></body></html>";
      String textBody = "Sorry! Your Registration has failed";
      //Infusion Soft mail starts
      //obj1.sendEmail(properties.getProperty("infusionMail.fromAddress"), usrEmail, properties.getProperty("infusionMail.ccAddresses"), properties.getProperty("infusionMail.bccAddresses"), properties.getProperty("infusionMail.contentType"), properties.getProperty("infusionMail.subject"), properties.getProperty("infusionMail.htmlBody"), properties.getProperty("infusionMail.textBody"));
      try {
      	obj1.sendEmail(fromAddress, toAddress, ccAddresses, bccAddresses, contentType, subject, htmlBody, textBody);
      	} 
      catch (XmlRpcException e) {
      	e.printStackTrace();
      	}
      System.out.println("Mail had been send successfully");
      //Infusion Soft mail ends
      return new ModelAndView("signUpFail");
      }
  }

//Setting Parent member id in session after creating a member for payment rollback
  int intVId = 0;
 String inVoiceId1="1";
 if (inVoiceId1.equalsIgnoreCase("0")) {
     intVId = 1;
 } else {
     intVId = 1;
 }
 
System.out.println("usrEmail --> "+usrEmail+" product_plan --> "+product_plan+" subscriptionType --> "+subscriptionType);
  String userid=db.getUserIdbyEmailPlan(usrEmail, product_plan, subscriptionType);
  System.out.println("userid in 1----------->"+userid);
  String companyStatus = null;
  companyStatus = db.getUserIdbyCompany(company);
  System.out.println("companyStatus in 2----------->"+companyStatus);
  if(companyStatus == null || companyStatus == "null" || companyStatus.equalsIgnoreCase("null") || companyStatus.equalsIgnoreCase(null)){
	  System.out.println("inside companyStatus if----------->");
  	  //status=db.updateusersignup(firstname,lastname,e_mail,mobileno,institutionName,country,state, city, credit_card_type, credit_card_no, cvv_no, name_on_card, expiry_date, productPrice, payOption, subscriptionType, product_plan,coupon_valid,coupon_status,coupon_value,coupon_description,coupon_code,street_name,zipcode);
  	  regStatusId=db.insertsignup(fName,lName,usrEmail,paddNoMob,company,paddCountry,paddState, paddCity, credit_card_type, credit_card_no, cvv_no, name_on_card, expiry_date, productPrice, subscriptionType, payOption, product_plan,coupon_valid,coupon_status,coupon_value,coupon_description,coupon_code,paddStreetNo,paddZip,category); 
  }/*else{
   regStatusId = db.updateusersignup(fName,lName,usrEmail,paddNoMob,company,paddCountry,paddState, paddCity, credit_card_type, credit_card_no, cvv_no, name_on_card, expiry_date, productPrice, subscriptionType,payOption, product_plan,coupon_valid,coupon_status,coupon_value,coupon_description,coupon_code,paddStreetNo,paddZip,category);
  	//regStatusId=db.insertsignup(firstname,lastname,e_mail,mobileno,institutionName,country,state, city, credit_card_type, credit_card_no, cvv_no, name_on_card, expiry_date, productPrice, subscriptionType, payOption, product_plan,coupon_valid,coupon_status,coupon_value,coupon_description,coupon_code,street_name,zipcode);
  }*/

  String usrid=null;
  boolean addComStatus=false;

  request.setAttribute("intVId", String.valueOf(intVId));
  System.out.println("intVId in servlet&&&&&&&" + intVId);
  request.setAttribute("purpose", "memberregistration");
  String paypal_ENVIRON = null;
  paypal_ENVIRON = properties.getProperty("paypal.environ");
  
  String reqIp=request.getRemoteAddr();
  
  System.out.println("regStatusId:::::::in KlgUserAction"+regStatusId);
  
/*  if(regStatusId != null && regStatusId != "null" && paypal_ENVIRON != null){
  	
	  try{
			 request.setAttribute("paypal_ENVIRON", paypal_ENVIRON);
			 request.setAttribute("CUSTOMER_REGISTERID", regStatusId);
			 System.out.println("paypal_ENVIRON==============inside action  ============"+paypal_ENVIRON);
			 
			  // session.setAttribute("parentId",parentId);
           session.setAttribute("sessionInvoiceId","1");
         request.setAttribute("AMT", productPrice);
         request.setAttribute("PAYMENTACTION", "Authorization");
         request.setAttribute("CREDITCARDTYPE", credit_card_type);
         request.setAttribute("ACCT", credit_card_no);
         request.setAttribute("EXPDATE", expiry_month+expiry_year);
         request.setAttribute("IPADDRESS", reqIp);
         request.setAttribute("FIRSTNAME", fName);
         request.setAttribute("LASTNAME", lName);
         request.setAttribute("CVVNo", cvv_no);
         request.setAttribute("STREET", paddStreetNo);
         request.setAttribute("CITY", paddCity);
         request.setAttribute("STATE", paddState);
         request.setAttribute("ZIP", paddZip);
         request.setAttribute("COUNTRYCODE", paddCountry);
         request.setAttribute("EMAIL", usrEmail);
         request.setAttribute("INSTITUTIONNAME", usrCompanyDetails);
         String instituteId = db.selectTryInstitutionId(usrEmail, regStatusId);
         request.setAttribute("INSTITUTIONID", instituteId);
         //session.setAttribute("objPayment", objPayment);//no need
        // session.setAttribute("URLPATH", "MembershipReg.do?process=reg");
         
         
         request.setAttribute("pay_firstname", fName);
	          request.setAttribute("pay_lastname", lName);
	          request.setAttribute("pay_e_mail", usrEmail);
	          request.setAttribute("pay_mobileno", paddCntryPhn+paddAreaPhone+paddNoPhone);
	          request.setAttribute("pay_institutionName", usrCompanyDetails);
	          request.setAttribute("pay_country", paddCountry);
	          request.setAttribute("pay_state", paddState);
	          request.setAttribute("pay_city", paddCity);
	          request.setAttribute("pay_productPrice", productPrice);
	          request.setAttribute("pay_credit_card_type", credit_card_type);
	          request.setAttribute("pay_credit_card_no", credit_card_no);
	          request.setAttribute("pay_cvv_no", cvv_no);
	          request.setAttribute("pay_name_on_card", name_on_card);
	          request.setAttribute("pay_expiry_month", expiry_month);
	          request.setAttribute("pay_expiry_year", expiry_year);
	          request.setAttribute("pay_expiry_date", expiry_date);
	          request.setAttribute("pay_subscriptionType", subscriptionType);
	          request.setAttribute("pay_purchase_type", payOption);
	          request.setAttribute("pay_productPlan",product_plan);
	          request.setAttribute("pay_street_name",paddStreetNo);
	          request.setAttribute("pay_zipcode",paddZip);
	          
	          
	          System.out.println("pay_street_name::::::::::::::::::"+paddStreetNo);
	          System.out.println("pay_zipcode::::::::::::::::::"+paddZip);
	          System.out.println("pay_firstname::::::::::::::::::"+fName);
	          System.out.println("pay_lastname::::::::::::::::::"+lName);
	          System.out.println("pay_e_mail::::::::::::::::::"+usrEmail);
	          System.out.println("pay_mobileno::::::::::::::::::"+paddCntryPhn+paddAreaPhone+paddNoPhone);
	          System.out.println("pay_institutionName::::::::::::::::::"+usrCompanyDetails);
	          System.out.println("pay_country::::::::::::::::::"+paddCountry);
	          System.out.println("pay_state::::::::::::::::::"+paddState);
	          System.out.println("pay_city::::::::::::::::::"+paddCity);
	          System.out.println("pay_productPrice::::::::::::::::::"+productPrice);
	          System.out.println("pay_credit_card_type::::::::::::::::::"+credit_card_type);
	          System.out.println("pay_credit_card_no::::::::::::::::::"+credit_card_no);
	          System.out.println("pay_cvv_no::::::::::::::::::"+cvv_no);
	          System.out.println("pay_name_on_card::::::::::::::::::"+name_on_card);
	          System.out.println("pay_expiry_month::::::::::::::::::"+expiry_month);
	          System.out.println("pay_expiry_year::::::::::::::::::"+expiry_year);
	          System.out.println("pay_expiry_date::::::::::::::::::"+expiry_date);
	          System.out.println("pay_subscriptionType::::::::::::::::::"+subscriptionType);
	          System.out.println("pay_purchase_type::::::::::::::::::"+payOption);
	          System.out.println("pay_productPlan::::::::::::::::::"+product_plan);
	          
	          System.out.println("instituteId:::::::::::::::::::::::"+instituteId);
	          System.out.println("institutionName:::::::::::::::::::::::"+usrCompanyDetails);
	          
	          
			 return new ModelAndView("testpaypal");
			 }	catch(Exception e){
				 e.printStackTrace();
				 //return new ModelAndView("signUpFail");
			 }
		
  
     String productStatus = "active";
     boolean updateProductStatus = false;
     updateProductStatus = db.updateProductStatus(usrEmail, regStatusId, productStatus);
     DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
     Date date = new Date();
     if(updateProductStatus == true){
        	
         log.info("Dear Customer:"+fName+" "+lName+"\n");  
         log.info("Customer email :"+usrEmail);  
         log.info("Timestamp :"+dateFormat.format(date));
         log.info("Product Status :"+productStatus);
         log.info("Product status updated on database---------------->");

         }else{
         	
         	log.info("Dear Customer:"+fName+" "+lName+"\n");  
  	        log.info("Customer email :"+usrEmail);  
  	        log.info("Timestamp :"+dateFormat.format(date));
  	        log.info("Product Status :"+productStatus);
  	        log.info("Product status was not updated on database---------------->");
         } 
  }/*else{
		 return new ModelAndView("signUpFail");
	 }*/
  
  usrid = db.addUserRegistration(objUserMaster);
  addComStatus=db.addCompanyDetails(company,category,usrName);
  
  if(addComStatus=true){
  	 
  	 System.out.println("Successfully Inserted Company Details");
  	 
  }
  else{
  	 request.setAttribute("response_status", "fail");
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
  request.setAttribute("response_status", "fail");
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
  request.setAttribute("response_status", "fail");
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
  request.setAttribute("response_status", "fail");
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

  System.out.println("after addContactDetails ");

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
  request.setAttribute("response_status", "fail");
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
  request.setAttribute("response_status", "fail");
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
  request.setAttribute("response_status", "fail");
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
  request.setAttribute("email", usrEmail);
  System.out.print("password:"+usrCnfPwd);
  System.out.print("loginmane:"+usrName);
  System.out.print("email:"+usrEmail);
  /* =====================================
  *
  * Sending confirmation E-mail
  *
  * ====================================*/

  //=========Adding vendor details============//

  if(category != null && category.equalsIgnoreCase("vendor")){
 	 
 	 vendorBean.setvendor_fname(fName);
      vendorBean.setvendor_lname(lName);
      vendorBean.setvendor_email(usrEmail);
      vendorBean.setvendor_company(company);
      vendorBean.setvendor_homePhone(finalPrimaryMob);
      vendorBean.setvendor_id(company);
      String Vendor_email=null;
       
      if(usrid !=null){
     
   	   Vendor_email=db.getAllVendorDetailsByEmail(usrEmail);
      
   	 if(Vendor_email == null){
   		  boolean insertvendorstatus=db.insertVendorContacts(vendorBean);
   		  System.out.println("inserting vendor Query"+insertvendorstatus);  
   	  }else{
   		  System.out.println("vender email already exixts");
   		 boolean updatevendorstatus=db.UpdateVendorByEmailID(vendorBean);
   		System.out.println("Updating vendor Query"+updatevendorstatus); 
   	  }
  }
  //Account manager role to vendor -shamili
      /*  String vendorUserId1 = db.getUserIdByEmail(usrEmail);
		 String roleId = "F7FC20DE-3FD4-4474-8529-E8068EC6BFE4";
		 
		 String roleName = db.getRoleNameByRoleId(roleId);
		 boolean roleFlag = db.insertUserWithRoleMapping(vendorUserId1, roleId);
		 System.out.println("fName=="+fName+"lName==="+lName+"roleName==="+roleName+"usrName==="+usrName);
		boolean rolestatusSP= nu.addUserToGroup(fName, lName, roleName, usrName);
		 System.out.println("Role status in sharepoint===="+rolestatusSP);

		 System.out.println("roleFlag --> "+roleFlag);
  
  if(Vendor_email !=null && usrEmail != null && Vendor_email.equals(usrEmail)) {
  	System.out.println("Vendor_email======"+Vendor_email+"=======usrid"+usrid);
  boolean updatevendorstatus= db.UpdateVendorUniqueID(vendorBean, usrid);
  System.out.println("Updating vendor Query"+updatevendorstatus);
  }
  else{	
  boolean insertvendorstatus=db.insertVendorContacts(vendorBean);
  System.out.println("Updating vendor Query"+insertvendorstatus);

  }*/
  }  

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
  //obj1.createAndCheckDuplicateContact(fName, lName, toAddress);
  
  int contactId = obj1.createBuyerInfusion(fName, lName, usrName, password, usrCategory, toAddress,usrCompanyDetails);
  
  obj1.optin_outEmail(toAddress);
  obj1.sendEmail(fromAddress, toAddress, ccAddresses, bccAddresses, contentType, subject, textBody, "");
  
  
  } catch (XmlRpcException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
  }

  System.out.println("Mail has been send successfully");                                 
  //Infusion Soft mail ends

/*  String emailid = usrEmail;
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
  "<p>Please save this email for your records. Your account information is as follows:.<p>" + "<p>----------------------------<p>" + "<p>UserName :" + usrName + "<p> password: " + usrCnfPwd + "<p> ----------------------------<p>" +
  "<p>Your account would be activated through your confirmation by visiting the following link: <p>"+
  "<a href=http://192.168.3.98:8090/dashboad-war/uservalidate.do?email="+request.getParameter("email")+"> Click Here to Activate your Account </a>"+
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
  request.setAttribute("source", source);  */   


  }
  else{
  	System.out.println("Cannot registred in ldap: ");
  	request.setAttribute("response_status", "fail");
  	
  	//return new ModelAndView("signUpFail");
  }	




  //return new ModelAndView("frmMemberUserRegiCnf");	
  //String redirectsite = "http://198.71.58.51:19075/ResetPassLocal.aspx?UN="+encryptfNameLname+"&PWD="+encryptUserPass;



  //String redirectsite = "http://198.71.58.51:56122/ResetPassword.aspx?UN="+encryptfNameLname+"&PWD="+encryptUserPass;
  String redirectsite = properties.getProperty("config.SP_reset_pwd_url")+"/ResetPassword.aspx?UN="+encryptfNameLname+"&PWD="+encryptUserPass;

  System.out.println("rediredt url :::::::::::::::::::"+redirectsite);
  response.sendRedirect(redirectsite);
  //return new ModelAndView("frmMemberUserRegiCnf");	
  // return mapping.findForward("Confirmation");

     	           		

        	        	  
        	          }catch(Exception e){
        	        	  e.printStackTrace();
        	          }
        	          
        	          
                 }
        	          
  				
               
             return null;

	}               
               
              
	              
              

public static Context getInitialContext() throws javax.naming.NamingException {
    Properties p =new Properties();
    p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
    p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
    p.setProperty( "java.naming.provider.url", "localhost:11199" );
    return new javax.naming.InitialContext(p);
  }           
}

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
package com.db.buyer.candidate;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.xmlrpc.XmlRpcException;
import org.jasypt.util.text.BasicTextEncryptor;

import com.AD.action.NewUser;
import com.db.GeneralDBAction;
import com.hlccommon.util.Debug;
import com.hlcform.util.HLCContactDetails;
import com.hlcform.util.HLCUserMaster;
import com.infusionejb.session.InfusionSessionBean;
import com.mysql.dao.MySQLDAO;
import com.user.action.Crypt;
import com.user.action.KlgUserAction;

//@WebService(endpointInterface="com.db.buyer.candidate.BuyerCandidateServiceInterface")
//public class BuyerCandidateService implements BuyerCandidateServiceInterface{
	public class BuyerCandidateService{
	
	static Logger log = Logger.getLogger(BuyerCandidateService.class.getName());
	Properties properties;
    
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
	
	Thread t1 = new Thread();
	
	//@Override
	public String pushBuyerCandidateDetailsToRegister(String input) {
		// TODO Auto-generated method stub

		String status = null;

		// ======================log file properties configuration started====================
	       Properties logProp = new Properties();  
		      is = (InputStream) getClass().getClassLoader().getResourceAsStream(LOG_FILE);
		      try {
				logProp.load(is);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		      PropertyConfigurator.configure(logProp);      
		      log.info("Logging enabled");  
		   // ======================log file properties configuration ended====================
		
		
		/*
		 * *===================Property file loaded here======================================
		 */
		/** Creates a new instance of EmailEngine */
        properties = new Properties(); 
        
        try {               
            // properties.load(new FileInputStream(fileName));
     	//properties.load(KlgLoginAction.class.getResourceAsStream("/companyDetails.properties"));
     	properties.load(KlgUserAction.class.getResourceAsStream("/config.properties"));
     	properties.load(NewUser.class.getResourceAsStream("/config.properties"));

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
      Crypt ccc = new Crypt();
    
         String usrSalutation = "NA";
    	 String fName = "NA";
    	 String mName = "NA";
    	 String lName = "NA";
    	 String sName = "NA";
    	 String usrDOB = "01-01-2000";
    	 String gender = "NA";
    	 String usrName = "NA";
    	 String usrEmail = "NA";
    	 //String usrPwd = null;
    	 String usrCnfPwd = "NA";
    	 String usrCompanyDetails = "NA";
    	 String usrCategory = "Buyer";
    	 String secretQuestion = "NA";
    	 String secretAns = "NA";
    	 String paddPlotNo = "NA";
    	 String paddBuildNo = "NA";
    	 String paddFloorNo = "NA";
    	 String paddStreetNo = "NA";
    	 String paddLane = "NA";
    	 String paddArea = "NA";
    	 String paddCity = "NA";
    	 String paddCountry = "NA";
    	 String paddState = "NA";
    	 String paddZip = "NA";
    	 String paddCntryPhn = "NA";
    	 String paddAreaPhone = "NA";
    	 String paddNoPhone = "NA";
    	 String paddCntryMob = "NA";
    	 String paddNoMob = "NA";
    	 String paddCntryFax = "NA";
    	 String paddAreaFax = "NA";
    	 String paddNoFax = "NA";
    	 String secAddrReqOrNot = "Primary";
    	 String saddPlotBuildFloor = "NA";
    	 String saddStreet = "NA";
    	 String saddLane = "NA";
    	 String saddArea = "NA";
    	 String saddCity = "NA";
    	 String saddCountry = "NA";
    	 String saddState = "NA";
    	 String saddZip = "NA";
    	 String saddCntryPhone = "NA";
    	 String saddAreaPhone = "NA";
    	 String saddNoPhone = "NA";
    	 String saddCntryMob = "NA";
    	 String saddNoMob = "NA";
    	 String saddCntryFax = "NA";
    	 String saddAreaFax = "NA";
    	 String saddNoFax = "NA";
    	 String PreferredAddrType = "NA";
    	 String NonUseaEmail = "NA";
    	 //String NonUseaMail = null;
    	 
    	 String encryptUserPass= "NA";
         String encryptfNameLname= "NA";
         
         ArrayList buyerList = null;
         boolean buyerActiveStatus = false; 
        		 
		try{
		
			if(input != null && input != ""){
			
			
			
			buyerList = new ArrayList();
			buyerList = db.getBuyerDetails();
			
			if(buyerList != null && buyerList.size() != 0){
				
				Iterator itr = buyerList.iterator();
				while(itr.hasNext()){
					String strarr[] = (String[])itr.next();
					
					//sNo = strarr[0];
					usrCompanyDetails = strarr[1];					
					//website = strarr[2];
					//contactPerson = strarr[3];
					fName = strarr[3];
					lName = strarr[3];
					usrName = strarr[3];
					usrEmail = strarr[4];
					paddNoMob = strarr[5];
					paddNoFax = strarr[6];
					
					System.out.println(" in servlet method ===> lName ---> "+lName+" fName ---> "+fName
        					+" usrEmail ---> "+usrEmail+" paddNoMob ---> "+paddNoMob
        					+" paddNoFax ---> "+paddNoFax);
					
			
					String buyerUserId = db.getUserIdByEmail(usrEmail);
					
					if(buyerUserId == null){
					

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
           
           
//           NewUser nu = new NewUser(usrName , fName , lName , usrCnfPwd , "dBVMS", usrEmail);
             
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
    // HLCMemberDetails objMember = new HLCMemberDetails();
    // HLCPaymentDetails objPayment = new HLCPaymentDetails();
   
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
     
     if (secAddrReqOrNot.equalsIgnoreCase("Secondary")) {
         objUserMaster.setCommunicationAddress("Primary");
     }

     if (secAddrReqOrNot.equalsIgnoreCase("Primary")) {
         objUserMaster.setCommunicationAddress(PreferredAddrType);
     }
    // String usrCrit=request.getParameter("usrCriteria");
     // remote.editUserDetails(objUserMaster);

     String usrid = db.addUserRegistration(objUserMaster);
     System.out.println("--------------------------");
     System.out.println("usrid ---> "+usrid);
     boolean addComStatus = db.addCompanyDetails(company,category,usrName);
     
     if(addComStatus){    	 
    	 System.out.println("Successfully Inserted Company Details");    	 
     }
     else{
    	 System.out.println("Failed to Insert");
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

     System.out.print("password:"+usrCnfPwd);
     System.out.print("loginmane:"+usrName);
     System.out.print("email:"+usrEmail);
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
     String subject = "Welcome User";
     
     String htmlBody="<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;text-align: center;vertical-align: top; width: 100%\">" +
				"<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #333;border-radius: 4px 4px 0 0;padding-bottom: 25px; text-align: center\"><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px\"><a href=\"#\"><img style=\"height: auto; max-width: 156px\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a>" +
				"</td></tr>	</tbody> </table></td> </tr> </tbody></table><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>	<tr><td align=\"right\" valign=\"top\" style=\"background-color: #fff; width: 937px\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding: 16px;text-align: center; vertical-align: top\"><h4 style=\"font-size: 20px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: left\">Dear Staffing Partner, </h4>" +
				"<p style=\"font-size: 15px;line-height: 27px; margin-bottom: 16px;margin-top: 16px; text-align: left\">Being a valued customer of digiBlitz for a long time we have taken privilege to bring you into our newly developed staffing system called “menschForce”. The system brings you,</p>"+
				"<li style=\"font-size: 13px;line-height: 27px; margin-bottom: 16px; margin-left: 10px;margin-top: 16px; text-align: left\">Capability to manage your own set of 1000s of suppliers/vendors dedicatedly working for you.</li>"+
				"<li style=\"font-size: 13px;line-height: 27px; margin-bottom: 16px; margin-left: 10px;margin-top: 16px; text-align: left\">Capability to Post and send requirements to more than 20,000 open market suppliers.</li>"+
				"<li style=\"font-size: 13px;line-height: 27px; margin-bottom: 16px; margin-left: 10px;margin-top: 16px; text-align: left\">Capability to search candidates.</li>"+
				"<li style=\"font-size: 13px;line-height: 27px; margin-bottom: 16px; margin-left: 10px;margin-top: 16px; text-align: left\">ERP solutions like HR and Finance for your organization.</li>"+
				"<li style=\"font-size: 13px;line-height: 27px; margin-bottom: 16px; margin-left: 10px;margin-top: 16px; text-align: left\">Payroll for your staff.</li>"+
				"<li style=\"font-size: 13px;line-height: 27px; margin-bottom: 16px; margin-left: 10px;margin-top: 16px; text-align: left\">H1B Compliance for your entire contingent workforce( DOL, DOS and USCIS).</li>"+
				"<li style=\"font-size: 15px;line-height: 27px; margin-bottom: 16px;margin-top: 16px; text-align: left\">All for one simple monthly fee as you see in <a href=\"http://www.menschforce.com\">www.menschforce.com</a>. But as a valued client, we are happy to offer you free account for 30 days. No credit card required to use the system during trial. You can use it for commercial purpose and gain. No action needed If you don’t want to continue to use the system now or after 30 days. You can simply stop logging in.</p>"+
				
				"<p style=\"font-size: 15px;line-height: 27px; margin-bottom: 16px;margin-top: 16px; text-align: left;color:red;\">Login Details</p>"+
				"<table style=\"width:85%;margin:0 auto\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody style=\"width:85%\"><tr  align=\"center\" valign=\"top\" style=\"padding: 16px;text-align: center; vertical-align: top\"><tr style=\"line-height:30px\">" +
				"<td><strong>User Name</strong></td> <td>"+usrName+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>Password</strong></td> <td>"+usrCnfPwd+"</td> </tr><tr style=\"line-height:25px\">" +
				"<td><strong>"+"Buyer"+" Id</strong></td> <td>"+usrCompanyDetails+"</td> </tr><tr style=\"line-height:30px\">" +				
				"<td >Regards,</td></tr><tr style=\"line-height:10px;\"><td>menschForce Team</td></tr><tr style=\"line-height:25px;text-align: left><td>For login related queries mail to ashwini.ramesh@digiblitz.com</tbody></table></td></tr></tbody> </table> </td> </tr>  </tbody></table></div>" +
				
				"</div></body></html>";
     
     String htmlBody1 = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
             "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
             "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
             " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
             "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Congratulations "+fName+"</h4><p style=\"   font-size: 19px;" +
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
     
     System.out.println("infusion soft property values: ----> "+fromAddress+" toAddress "+toAddress+" subject "+subject+" htmlBody ");
     System.out.println("ccAddresses "+ccAddresses+" bccAddresses "+bccAddresses);
     //Infusion Soft mail starts
                      
//obj1.sendEmail(properties.getProperty("infusionMail.fromAddress"), usrEmail, properties.getProperty("infusionMail.ccAddresses"), properties.getProperty("infusionMail.bccAddresses"), properties.getProperty("infusionMail.contentType"), properties.getProperty("infusionMail.subject"), properties.getProperty("infusionMail.htmlBody"), properties.getProperty("infusionMail.textBody"));
 try {
	 
	 //int contactId = obj1.createAndCheckDuplicateContact("amalesh", "r", toAddress);
	 obj1.optin_outEmail(toAddress);
	obj1.sendEmail(fromAddress, toAddress, ccAddresses, bccAddresses, contentType, subject, htmlBody, "");			
	int groupId = Integer.parseInt(properties.getProperty("infusionMail.buyerGroupId"));	
	//obj1.addContactToGroupInfusion(contactId, groupId);
	
	
	int contactId = obj1.createBuyerInfusion(fName, lName, usrName, password, usrCategory, toAddress , usrCompanyDetails);
	obj1.addContactToGroupInfusion(contactId, groupId);
	System.out.println("contactId ---> "+contactId);
	
	
		} catch (XmlRpcException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
 
				 buyerActiveStatus = db.updateBuyerActiveStatus(usrEmail);
				 System.out.println("buyerActiveStatus --> "+buyerActiveStatus);
				 if(buyerActiveStatus){
				
					 status = "Buyer Candidate details inserted Successfully";
					 
					 String buyerUserId1 = db.getUserIdByEmail(usrEmail);
					 String roleId = "E6D7C5FC-9442-4AF3-973F-AE67FDDB9BD9";
					 
					 
					 
					 boolean roleFlag = db.insertUserWithRoleMapping(buyerUserId1, roleId);
					 System.out.println("roleFlag --> "+roleFlag);
				 }
 
     			}
     		
    		t1.sleep(250);
				}
     		}
		}		
	}
			
	}catch(Exception e){
		e.printStackTrace();
	}
		return status;
	}
	
	
	public boolean assignRoleToBuyer(){
		
		
		try{
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	

}

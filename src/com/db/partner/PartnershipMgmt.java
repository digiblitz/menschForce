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
package com.db.partner;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.xmlrpc.XmlRpcException;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.AD.action.NewUser;
import com.db.GeneralDBAction;
import com.db.buyer.candidate.BuyerCandidateService;
import com.db.email.service.EmailServiceClient;
import com.hlccommon.util.Debug;
import com.infusionejb.session.InfusionSessionBean;
import com.user.action.KlgUserAction;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import com.util.email.EmailNotification;
import com.db.partner.PartnershipMgmt;
import com.db.partner.service.PartnerClient;
import com.db.reminder.QA.QAReminderClient;
import com.emailservice.QASession.QAClient;

@Controller
public class PartnershipMgmt {

	public static final String config_prop = "config.properties";
	public static final String LOG_FILE = "Log4j.properties";
	InputStream is;
	InputStream config_inputStream;
GeneralDBAction db=new GeneralDBAction();
EmailNotification emailobj=new EmailNotification();	
	static Logger log = Logger.getLogger(PartnershipMgmt.class.getName());
	Properties properties;
    
	private InfusionSessionBean obj1;
	{
		try{
			obj1 = new InfusionSessionBean();
		}catch(Exception e){
			 throw new RuntimeException(e);
		}
	}
	
	private Pattern pattern;
	 private Matcher matcher;

	 private static final String EMAIL_PATTERN =
	 "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	 + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	 /**
	  * Validate hex with regular expression
	  *
	  * @param hex
	  *            hex for validation
	  * @return true valid hex, false invalid hex
	  */
	 public boolean emailValidate(final String hex) {

	 matcher = pattern.matcher(hex);
	 return matcher.matches();

	 }
	 PartnershipMgmt(){
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
	     
	     
	     pattern = Pattern.compile(EMAIL_PATTERN);
	     
		}
	 
	 @RequestMapping("/initPartner.html")
	 public ModelAndView initPartner(HttpServletRequest request,
	   HttpServletResponse response) throws SQLException, IOException{
		 
		 
		 return new ModelAndView("partner/frmPartner");
	 
	 }
	 
	 @RequestMapping("/CheckEmailExist.html")
	 public ModelAndView CheckEmailExist(HttpServletRequest request,
	   HttpServletResponse response) throws SQLException, IOException, AddressException, MessagingException{
		 String process=request.getParameter("cmd");
		 PrintWriter writer = response.getWriter(); 
		 if(process.equalsIgnoreCase("checkEmail"))
	        {
	        	boolean usrStat=false;
	        	 String emailid=request.getParameter("email");
	            System.out.println("request.getParameter(email) :"+emailid);

	            usrStat=db.checkEmailExist(emailid);

	            System.out.println("UsrSignupAjaxAction.checkEmailExist(usrId)"+usrStat);

	            //get the member id existing or not status and respond 

	            writer.println("<userstatus><![CDATA[" + usrStat + "]]></userstatus>"); 
	        
	        }
	        
	return null;
}
	 
	 @RequestMapping("/BecomePartner.html")
	 public ModelAndView BecomePartner(HttpServletRequest request,
	   HttpServletResponse response) throws SQLException, IOException, AddressException, MessagingException{
		 
		 HttpSession session=request.getSession(true); 
		 String firstname=null;
		 String lastname=null;
		 String email=null;
		 String company=null;
		 String Designation=null;
		 String AddressLine1=null;
		 String AddressLine2=null;
		 String City=null;
		 String State=null;
		 String Zipcode=null;
		 String Country=null;
		 String PhoneNumber=null;
		 String Website=null;
		 String business_type=null;
		 String publicorprivate=null;
		 String no_of_customer=null;
		 String no_of_employee=null;
		 String interest=null;
		 String territory=null;
		 String menschforce="false";
		 String guildkraft="false";
		 String smartlehren="false";
		 String klugwerks="false";
		 String reselling=null;
		 
		 firstname=request.getParameter("inf_field_FirstName");
		 lastname=request.getParameter("inf_field_LastName");
		 email=request.getParameter("inf_field_Email");
		 company=request.getParameter("inf_field_Company");
		 Designation=request.getParameter("inf_field_JobTitle");
		 AddressLine1=request.getParameter("inf_field_Address3Street1");
		
		 City=request.getParameter("inf_field_City3");
		 State=request.getParameter("inf_field_State3");
		 Zipcode=request.getParameter("inf_field_PostalCode3");
		 Country=request.getParameter("inf_field_Country3");
		 PhoneNumber=request.getParameter("inf_field_Phone1");
		 Website=request.getParameter("inf_field_Website");
		 business_type=request.getParameter("inf_field_SourceType");
		 publicorprivate=request.getParameter("inf_option_PublicorPrivate");
		 no_of_customer=request.getParameter("inf_field_SSN");
		 no_of_employee=request.getParameter("inf_custom_RegNo");
		 interest=request.getParameter("inf_custom_NPSComments");
		 territory=request.getParameter("inf_custom_PositionDescription");
		 menschforce=request.getParameter("inf_option_menshcforce");
		 guildkraft=request.getParameter("inf_option_guildkraft");
		 smartlehren=request.getParameter("inf_option_klugwerks");
		 klugwerks=request.getParameter("inf_option_smartLehren");
		 reselling=menschforce+","+guildkraft+","+smartlehren+","+klugwerks;
		 
		 
		 System.out.println("firstname from partnership****************"+firstname);
		 System.out.println("lastname from partnership****************"+lastname);
		 System.out.println("email from partnership****************"+email);
		 System.out.println("company from partnership****************"+company);
		 System.out.println("Designation from partnership****************"+Designation);
		 System.out.println("AddressLine1 from partnership****************"+AddressLine1);
		 
		 System.out.println("City from partnership****************"+City);
		 System.out.println("State from partnership****************"+State);
		 System.out.println("Zipcode from partnership****************"+Zipcode);
		 System.out.println("Country from partnership****************"+Country);
		 System.out.println("PhoneNumber from partnership****************"+PhoneNumber);
		 System.out.println("Website from partnership****************"+Website);
		 System.out.println("business_type from partnership****************"+business_type);
		 System.out.println("publicorprivate from partnership****************"+publicorprivate);
		 System.out.println("no_of_customer from partnership****************"+no_of_customer);
		 System.out.println("no_of_employee from partnership****************"+no_of_employee);
		 System.out.println("interest from partnership****************"+interest);
		 System.out.println("territory from partnership****************"+territory);
		 System.out.println("menschforce from partnership****************"+menschforce);
		 System.out.println("guildkraft from partnership****************"+guildkraft);
		 System.out.println("smartlehren from partnership****************"+smartlehren);
		 System.out.println("klugwerks from partnership****************"+klugwerks);
		 System.out.println("reselling from partnership****************"+reselling);
		 
		 
		 
		String partnershipSignup = null;
        PartnerClient partnerclientobj = new PartnerClient();
         partnershipSignup = partnerclientobj.BecomeAPartner(firstname,lastname,email,company,Designation,AddressLine1,City,State,
			 Zipcode,Country,PhoneNumber,Website,business_type,publicorprivate,no_of_customer,no_of_employee,interest,territory,reselling);
	            
       if(partnershipSignup != null && partnershipSignup.equalsIgnoreCase("Success")){	
    	   
    	   /* =====================================
		    *
		    * Sending confirmation E-mail
		    *
		    * ====================================*/
		  EmailContent sendemail=new EmailContent();
		   String emailid = email;
		   String toMailIds[] = {emailid};
		   
		   sendemail.setTo(toMailIds);
		   sendemail.setFrom("priya.karunanidhi@digiblitz.in");
		   sendemail.setSubject("Welcome Aboard!");

		   String htmlbodytocandidate = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
                   "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
                   "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
                   " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
                   "<tr><td  valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 25px; margin: 16px 0 8px;padding: 0;text-align: left;\">Welcome "+firstname+",</h4></td> </tr><tr></tr> <tr><td style=\"padding:0px 10px\"><p style=\"margin: 20px 0 10px;text-align:left;\">"+
                   "Thank You for Partnering with digiblitz.Please click on the below link to sign up for your introductory partner call. <a href=\"http://192.168.1.142:8280/menschforce/initPartnerCall.html?cmd="+email+"\">Click Here to Schedule your call</a></td></tr>"+
                   "<tr><td style=\"padding:0px 10px\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>MenschForce Team</td></tr></tbody> </table> </td> </tr> </tbody> </table> </div></div></div></div></body></html>";

		   
		   
		   sendemail.setBody(htmlbodytocandidate);
		  
		   //email.setAttachments();

		   EmailEngine emailEngine = new EmailEngine();
		   boolean emailFlag = emailEngine.sendMimeEmail(sendemail);
		   System.out.println("Email sent sucessfully :" + emailFlag);
		   
		   //Email to Admin starts here
		   
		   EmailContent sendemailtoadmin=new EmailContent();
		   String adminemail = "finance@digiblitz.com";
		 String toMailId[] = {adminemail};
		   
		   sendemailtoadmin.setTo(toMailId);
		   sendemailtoadmin.setFrom("priya.karunanidhi@digiblitz.in");
		   sendemailtoadmin.setSubject("New Partner Signup!");

		   String htmlbodytoAdmin = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
                   "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
                   "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
                   " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
                   "<tr><td  valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 25px; margin: 16px 0 8px;padding: 0;text-align: left;\">Dear Admin,</h4></td> </tr><tr></tr> <tr><td style=\"padding:0px 10px\"><p style=\"margin: 20px 0 10px;text-align:left;\">"+
                   "New Partnering have signedup with digiblitz.Please click on the below link track <a href=\"https://www.menschforce.com\">Click Here to track</a></td></tr>"+
                   "<tr><td style=\"padding:0px 10px\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>MenschForce Team</td></tr></tbody> </table> </td> </tr> </tbody> </table> </div></div></div></div></body></html>";

		   
		   
		   sendemailtoadmin.setBody(htmlbodytoAdmin);
		  
		   //email.setAttachments();

		   EmailEngine emailEngine1 = new EmailEngine();
		   boolean emailFlag1 = emailEngine1.sendMimeEmail(sendemailtoadmin);
		   System.out.println("Email sent sucessfully to admin :" + emailFlag1);
		   
    	   
				
		 return new ModelAndView("partner/frmThankYou");
			 
			 
	       }else{
	    	  return new ModelAndView("partner/frmBecomePartnerFail");
	       }
		
	 }
	 
	
	 
		@RequestMapping("/ListPartnerDetails.html")
		public ModelAndView ListEmployeeDetails(HttpServletRequest request,
				HttpServletResponse response) throws RemoteException{
			
			HttpSession session=request.getSession(true); 
			GeneralDBAction db=new GeneralDBAction();
			
			
			Debug.print("PartnershipMgmt.ListPartnerDetails()");
			ArrayList partnerList = new ArrayList();
			partnerList = (ArrayList)db.getAllPartnerDetails();
			
	        request.setAttribute("partnerList",null);
	        request.setAttribute("partnerList",partnerList);
	        
			return new ModelAndView("partner/frmPartnerList");
			
		}
		
		@SuppressWarnings({ "unused", "rawtypes" })
		@RequestMapping("/ViewListPartnerDetailsByPartnerId.html")
		public ModelAndView ViewListEmployeeDetailsByUniqueId(HttpServletRequest request,
				HttpServletResponse response) throws Exception{
			
			HttpSession session=request.getSession(true); 
			GeneralDBAction db=new GeneralDBAction();
			
			Debug.print("PartnershipMgmt.ViewListPartnerDetailsByPartnerId()");
			String partnerId = null;
			partnerId = request.getParameter("partnerId");
			System.out.println("partnerid form viewpartnerdetails----"+partnerId);
			if(partnerId != null){
		        ArrayList partnerDetails = new ArrayList();
		        partnerDetails = (ArrayList)db.getAllPartnerDetailsByPartnerId(partnerId);
		        request.setAttribute("partnerDetails",null);
		        request.setAttribute("partnerDetails",partnerDetails);
		        
				return new ModelAndView("partner/frmViewPartnerDetails");
			}else{
				throw new Exception("Partner Details Not Found");
			}
		}
		
		
		@SuppressWarnings({ "unused", "rawtypes" })
		@RequestMapping("/initPartnerCall.html")
		public ModelAndView initPartnerCall(HttpServletRequest request,
				HttpServletResponse response) throws Exception{
			
			HttpSession session=request.getSession(true); 
			GeneralDBAction db=new GeneralDBAction();
			
			
		        
				return new ModelAndView("partner/frmPartnerCall");
			
		}
		
		
		
		
		
		
		@SuppressWarnings({ "unused", "rawtypes" })
		@RequestMapping("/PartnerIntroductoryCall.html")
		public ModelAndView PartnerIntroductoryCall(HttpServletRequest request,
				HttpServletResponse response) throws Exception{
			
			HttpSession session=request.getSession(true); 
			GeneralDBAction db=new GeneralDBAction();
			
			String eventDate=null;
			String eventmail=null;
			String receiveremail=null;
			String partnerid=null;
			String email_status="Scheduled";
			receiveremail=request.getParameter("receiveremail");
			eventDate=request.getParameter("eventDate");
			 eventmail=request.getParameter("eventmail");
			 System.out.println("event Date and time from introductory call==="+eventDate);
			 System.out.println("eventmail from introductory call==="+eventmail);
			 System.out.println("receiveremail from introductory call==="+receiveremail);
			 ArrayList partnerIdbyEmail = new ArrayList();
			 partnerIdbyEmail = (ArrayList)db.getAllPartnerIdByEmailId(receiveremail);
			 Iterator itrt = partnerIdbyEmail.iterator();
			    while (itrt.hasNext()) {    
			        String TempList[] = (String[])itrt.next();
			        partnerid=TempList[19];
						    
							System.out.println("partnerid"+partnerid);
			    }
			 boolean eventDetails = false;
			 eventDetails = db.insertPartnerEventDetails(eventDate,eventmail,partnerid,email_status);
			 
			 if(eventDetails){	
		    	   
		    	   /* =====================================
				    *
				    * Sending confirmation E-mail
				    *
				    * ====================================*/
				  EmailContent sendemail=new EmailContent();
				   String emailid = eventmail;
				   String toMailIds[] = {emailid};
				   
				   sendemail.setTo(toMailIds);
				   sendemail.setFrom("priya.karunanidhi@digiblitz.in");
				   sendemail.setSubject("Introductory call confirmation!");

				   String htmlbodytocandidate = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
		                   "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
		                   "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
		                   " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
		                   "<tr><td  valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 25px; margin: 16px 0 8px;padding: 0;text-align: left;\">Dear Partner,</h4></td> </tr><tr></tr> <tr><td style=\"padding:0px 10px\"><p style=\"margin: 20px 0 10px;text-align:left;\">"+
		                   "Thank You for Partnering with digiblitz. You have successfully scheduled the partner introductory call at "+eventDate+" . </td></tr>"+
		                   
		                 
		                  
		                   "<tr><td style=\"padding:0px 10px\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>MenschForce Team</td></tr></tbody> </table> </td> </tr> </tbody> </table> </div></div></div></div></body></html>";

				   
				   
				   sendemail.setBody(htmlbodytocandidate);
				  
				   //email.setAttachments();

				   EmailEngine emailEngine = new EmailEngine();
				   boolean emailFlag = emailEngine.sendMimeEmail(sendemail);
				   System.out.println("Email sent sucessfully :" + emailFlag);
				   
				   //Email to Admin starts here
				   
				   EmailContent sendemailtoadmin=new EmailContent();
				   String adminemail = "priya.karunanidhi@digiblitz.in";
				 String toMailId[] = {adminemail};
				   
				   sendemailtoadmin.setTo(toMailId);
				   sendemailtoadmin.setFrom("priya.karunanidhi@digiblitz.in");
				   sendemailtoadmin.setSubject("New introductory call scheduled to you !");

				   String htmlbodytoAdmin = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
		                   "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
		                   "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
		                   " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
		                   "<tr><td  valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 25px; margin: 16px 0 8px;padding: 0;text-align: left;\">Dear Admin,</h4></td> </tr><tr></tr> <tr><td style=\"padding:0px 10px\"><p style=\"margin: 20px 0 10px;text-align:left;\">"+
		                   ""+eventmail+" has registered for partnership onboarding introductory call.</td></tr>"+
		                  
		                   "<tr><td style=\"padding:0px 10px\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>MenschForce Team</td></tr></tbody> </table> </td> </tr> </tbody> </table> </div></div></div></div></body></html>";

				   sendemailtoadmin.setBody(htmlbodytoAdmin);
				  
				   //email.setAttachments();

				   EmailEngine emailEngine1 = new EmailEngine();
				   boolean emailFlag1 = emailEngine1.sendMimeEmail(sendemailtoadmin);
				   System.out.println("Email sent sucessfully to admin :" + emailFlag1);
				   
				   String output=null;
					 EmailServiceClient emailClientobj = new EmailServiceClient();
					 output = emailClientobj.EmailRemainder();
					 
					System.out.println("output in the email in partner mgt::: "+output); 
					
					
					 String email=null;
					 QAClient QAobj = new QAClient();
					 email = QAobj.QAEmailRemainder();
					 
					 String QAemail=null;
					 QAReminderClient QAReminderobj = new QAReminderClient();
					 QAemail = QAReminderobj.QAEmailRemainder();
					 
					System.out.println("output in the email in partner mgt::: "+email); 
					 return new ModelAndView("partner/frmThankYou");
			 }
			 
		//=======================Dhivya Here:Reminder email============================================================
			
			 else
			 {
				 return new ModelAndView("partner/frmBecomePartnerFail"); 
			 }
			
		}
		
		@SuppressWarnings({ "unused", "rawtypes" })
		@RequestMapping("/ConfirmPartnerCall.html")
		public ModelAndView ConfirmPartnerCall(HttpServletRequest request,
				HttpServletResponse response) throws Exception{
			
			HttpSession session=request.getSession(true); 
			GeneralDBAction db=new GeneralDBAction();
			
			
			String partnerconfirmation="confirm";
			System.out.println("partner confirmation is true");
			String receiveremail=request.getParameter("cmd");
			System.out.println("receiveremail from introductory call==="+receiveremail);
			
			boolean statusupdate=db.updatePartnerstatus(partnerconfirmation,receiveremail);
			
			
			
		        
				return new ModelAndView("partner/frmThankYou");
			
		}
		
		
		@SuppressWarnings({ "unused", "rawtypes" })
		@RequestMapping("/initQACall.html")
		public ModelAndView initQACall(HttpServletRequest request,
				HttpServletResponse response) throws Exception{
			
			HttpSession session=request.getSession(true); 
			GeneralDBAction db=new GeneralDBAction();
			
			
		        
				return new ModelAndView("partner/frmQACall");
			
		}
		
		@SuppressWarnings({ "unused", "rawtypes" })
		@RequestMapping("/QAIntroductoryCall.html")
		public ModelAndView QAIntroductoryCall(HttpServletRequest request,
				HttpServletResponse response) throws Exception{
			
			HttpSession session=request.getSession(true); 
			GeneralDBAction db=new GeneralDBAction();
			
			String eventDate=null;
			String eventmail=null;
			String receiveremail=null;
			String partnerid=null;
			String email_status="QAScheduled";
			receiveremail=request.getParameter("receiveremail");
			eventDate=request.getParameter("eventDate");
			 eventmail=request.getParameter("eventmail");
			 System.out.println("event Date and time from introductory call==="+eventDate);
			 System.out.println("eventmail from introductory call==="+eventmail);
			 System.out.println("receiveremail from introductory call==="+receiveremail);
			 ArrayList partnerIdbyEmail = new ArrayList();
			 partnerIdbyEmail = (ArrayList)db.getAllPartnerIdByEmailId(receiveremail);
			 Iterator itrt = partnerIdbyEmail.iterator();
			    while (itrt.hasNext()) {    
			        String TempList[] = (String[])itrt.next();
			        partnerid=TempList[19];
						    
							System.out.println("partnerid"+partnerid);
			    }
			 boolean eventDetails = false;
			 eventDetails = db.insertPartnerEventDetails(eventDate,eventmail,partnerid,email_status);
			 
			 if(eventDetails){	
		    	   
		    	   /* =====================================
				    *
				    * Sending confirmation E-mail
				    *
				    * ====================================*/
				  EmailContent sendemail=new EmailContent();
				   String emailid = eventmail;
				   String toMailIds[] = {emailid};
				   
				   sendemail.setTo(toMailIds);
				   sendemail.setFrom("priya.karunanidhi@digiblitz.in");
				   sendemail.setSubject("QA call confirmation!");

				   String htmlbodytocandidate = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
		                   "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
		                   "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
		                   " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
		                   "<tr><td  valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 25px; margin: 16px 0 8px;padding: 0;text-align: left;\">Dear Partner,</h4></td> </tr><tr></tr> <tr><td style=\"padding:0px 10px\"><p style=\"margin: 20px 0 10px;text-align:left;\">"+
		                   "Thank You for Partnering with digiblitz. You have successfully scheduled the QA partner call at "+eventDate+" . </td></tr>"+
		                   
		                 
		                  
		                   "<tr><td style=\"padding:0px 10px\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>MenschForce Team</td></tr></tbody> </table> </td> </tr> </tbody> </table> </div></div></div></div></body></html>";

				   
				   
				   sendemail.setBody(htmlbodytocandidate);
				  
				   //email.setAttachments();

				   EmailEngine emailEngine = new EmailEngine();
				   boolean emailFlag = emailEngine.sendMimeEmail(sendemail);
				   System.out.println("Email sent sucessfully :" + emailFlag);
				   
				   //Email to Admin starts here
				   
				   EmailContent sendemailtoadmin=new EmailContent();
				   String adminemail = "priya.karunanidhi@digiblitz.in";
				 String toMailId[] = {adminemail};
				   
				   sendemailtoadmin.setTo(toMailId);
				   sendemailtoadmin.setFrom("priya.karunanidhi@digiblitz.in");
				   sendemailtoadmin.setSubject("New QA call scheduled to you !");

				   String htmlbodytoAdmin = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
		                   "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
		                   "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
		                   " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
		                   "<tr><td  valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 25px; margin: 16px 0 8px;padding: 0;text-align: left;\">Dear Admin,</h4></td> </tr><tr></tr> <tr><td style=\"padding:0px 10px\"><p style=\"margin: 20px 0 10px;text-align:left;\">"+
		                   ""+eventmail+" has registered for partnership QA Session call.</td></tr>"+
		                  
		                   "<tr><td style=\"padding:0px 10px\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>MenschForce Team</td></tr></tbody> </table> </td> </tr> </tbody> </table> </div></div></div></div></body></html>";

				   sendemailtoadmin.setBody(htmlbodytoAdmin);
				  
				   //email.setAttachments();

				   EmailEngine emailEngine1 = new EmailEngine();
				   boolean emailFlag1 = emailEngine1.sendMimeEmail(sendemailtoadmin);
				   System.out.println("Email sent sucessfully to admin :" + emailFlag1);
				   
				  
					 return new ModelAndView("partner/frmThankYou");
			 }
			 
		//=======================Dhivya Here:Reminder email============================================================
			
			 else
			 {
				 return new ModelAndView("partner/frmBecomePartnerFail"); 
			 }
			
		}
}

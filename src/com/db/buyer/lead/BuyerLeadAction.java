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
package com.db.buyer.lead;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.hlccommon.util.Debug;
import com.infusionejb.session.InfusionSessionBean;
import com.user.action.KlgUserAction;
import com.db.buyer.lead.BuyerLeadAction;

@Controller
public class BuyerLeadAction {

	public static final String config_prop = "config.properties";
	public static final String LOG_FILE = "Log4j.properties";
	InputStream is;
	InputStream config_inputStream;
GeneralDBAction db=new GeneralDBAction();
	
	static Logger log = Logger.getLogger(BuyerLeadAction.class.getName());
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
	 BuyerLeadAction(){
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
	 
	 @RequestMapping("/sendEmail.html")
	 public ModelAndView sendEmail(HttpServletRequest request,
	   HttpServletResponse response) throws SQLException, IOException{
		 
		 String emailId = null;
		 String first_name=null;
		 String last_name=null;
		 String company_c=null;
		 String title=null;
		 String business_type_c=null;
		 String website=null;
		 String phone_work=null;
		 String primary_address_street=null;
		 String primary_address_city=null;
		 String primary_address_state=null;
		 String primary_address_postalcode=null;
		 String primary_address_country=null;
		 String lead_source=null;
		 String account_name="menschforce";
		 String description ="Buyer Lead";
		 int contactId;
		 int groupId=1125;
		
			
		 
		 try{
		
			 System.out.println("in sendEmail method ");
			 emailId = request.getParameter("email");
			 first_name=request.getParameter("first_name");
			 last_name=request.getParameter("last_name");
			 company_c=request.getParameter("company_c");
			 title=request.getParameter("title");
			 business_type_c=request.getParameter("business_type_c");
			 website=request.getParameter("website");
			 phone_work=request.getParameter("phone_work");
			 primary_address_street=request.getParameter("primary_address_street");
			 primary_address_city=request.getParameter("primary_address_city");
			 primary_address_state=request.getParameter("primary_address_state");
			 primary_address_postalcode=request.getParameter("primary_address_postalcode");
			 primary_address_country=request.getParameter("primary_address_country");
			 lead_source=request.getParameter("lead_source");
			 
			
			 
			 System.out.println("email ---> "+emailId);
			 System.out.println("first_name ---> "+first_name);
			 System.out.println("last_name ---> "+last_name);
			 System.out.println("company_c ---> "+company_c);
			 System.out.println("title ---> "+title);
			 System.out.println("business_type_c ---> "+business_type_c);
			 System.out.println("website ---> "+website);
			 System.out.println("phone_work ---> "+phone_work);
			 System.out.println("primary_address_street ---> "+primary_address_street);
			 System.out.println("primary_address_city ---> "+primary_address_city);
			 System.out.println("primary_address_state ---> "+primary_address_state);
			 System.out.println("primary_address_postalcode ---> "+primary_address_postalcode);
			 System.out.println("primary_address_country ---> "+primary_address_country);
			 System.out.println("lead_source ---> "+lead_source);
			 
			 boolean insertBuyerLeadInquiryDets = false;
			 
			 insertBuyerLeadInquiryDets = db.insertBuyerLeadInquiryDets(first_name,last_name,company_c,title,business_type_c,website,phone_work,emailId,primary_address_street,
					 primary_address_city,primary_address_state,primary_address_postalcode,primary_address_country,lead_source,account_name,description);
			 
			 contactId= obj1.createBuyerLeadInquiryContact(first_name, last_name, emailId, company_c, website, title, business_type_c, phone_work,
					 primary_address_street, primary_address_city, primary_address_state, primary_address_postalcode, primary_address_country, lead_source);
			 
			 
			 obj1.addContactToGroupInfusion(contactId,groupId);
			 
			 String htmlbodytocandidate = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
	                    "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
	                    "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
	                    " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
	                    "<tr><td  valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 25px; margin: 16px 0 8px;padding: 0;text-align: left;\">Hello,</h4></td> </tr><tr></tr> <tr><td style=\"padding:0px 10px\"><p style=\"margin: 20px 0 10px;text-align:left;\">"+
	                    "We are glad to know your interest in MenschForce. We received your product inquiry. you will be contacted by one of our member from sales team.</td></tr><tr><td style=\"padding:0px 10px\"><p style=\"line-height: 20px;text-align:left;\">You can reach us anytime at sales@menschforce.com <span style=\"font-weight:bold\"> </span></p></td></tr><tr><td style=\"padding:0px 10px\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>MenschForce Team</td></tr></tbody> </table> </td> </tr> </tbody> </table> </div></div></div></div></body></html>";
	   		
			 String htmlBodytoSalesTeam="<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;text-align: center;vertical-align: top; width: 100%\">" +
 					"<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #333;border-radius: 4px 4px 0 0;padding-bottom: 25px; text-align: center\"><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px\"><a href=\"#\"><img style=\"height: auto; max-width: 156px\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a>" +
 					"</td></tr>	</tbody> </table></td> </tr> </tbody></table><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>	<tr><td align=\"right\" valign=\"top\" style=\"background-color: #fff; width: 937px\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding: 16px;text-align: center; vertical-align: top\"><h4 style=\"font-size: 20px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: left\">Dear partner, </h4>" +
 					"<p style=\"font-size: 15px;line-height: 27px; margin-bottom: 16px;margin-top: 16px; text-align: left\">A new buyer lead has been generated for menschforce in crm system. Here are the contact details:</p><table style=\"width:85%;margin:0 auto\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody style=\"width:85%\"><tr  align=\"center\" valign=\"top\" style=\"padding: 16px;text-align: center; vertical-align: top\"><tr style=\"line-height:30px\">" +
 					"<td><strong>Name</strong></td> <td>"+first_name+"</td> </tr><tr style=\"line-height:25px\">" +
 					"<td><strong>Email Id</strong></td> <td>"+emailId+"</td> </tr><tr style=\"line-height:25px\">" +
 					"<td><strong>Contact Number</strong></td> <td>"+phone_work+"</td> </tr><tr style=\"line-height:25px\">" +
 					"<td><strong>company</strong></td> <td>"+company_c+"</td> </tr><tr style=\"line-height:25px\">" +
 					"<td><strong>Business Type</strong></td> <td>"+business_type_c+"</td> </tr><tr style=\"line-height:25px\">" +
 					"<td><strong>Account Name</strong></td> <td>menschForce</td> </tr><tr style=\"line-height:25px\">" +
 					" </p>" +
 					"<td><p style=\"line-height: 20px;text-align:left;\">Regards,<br>CRM Team</td>"+
 					"</tr></tbody></table></td></tr></tbody> </table> </td> </tr>  </tbody></table></div>" +
 					
 					"</div></body></html>";
	   	
	   		try {
	   			
	   			//obj1.createAndCheckDuplicateContact("NA", "NA", emailId);
	   			//System.out.println("jobPostUserEmailID====="+jobPostUserEmailID);
	   			obj1.sendEmail("sales@menschforce.com",emailId , "", "priya.karunanidhi@digiblitz.in", "Html", "menschForce ",htmlbodytocandidate,"" );
	   			//obj1.sendEmail("crm@digiblitz.com","sales.group@digiblitz.com" , "jeyaprakash@digiblitz.in", "", "Html", "Buyer Lead Inquiry ",htmlBodytoSalesTeam,"" );
	   			//obj1.sendEmail(fromAddress, toAddress, ccAddresses, bccAddresses, contentType, subject, htmlBody, textBody)
	   			/*PostReqList = (ArrayList)db.getPostReqByUniqueId(req_uniqueID);
	   			String Recruiter_mail=null;
		            Iterator itr = PostReqList.iterator();
		            while (itr.hasNext()) {    
		                String TempList[] = (String[])itr.next();
		        		 Recruiter_mail=TempList[14];*/
		        		
				} catch (XmlRpcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	   		 
	   	 
		 }catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		 
		
		 
		 request.setAttribute("first_name",first_name);
		 request.setAttribute("last_name",last_name);
		 request.setAttribute("company_c",company_c);
		 request.setAttribute("title",title);
		 request.setAttribute("business_type_c",business_type_c);
		 request.setAttribute("website",website);
		 request.setAttribute("phone_work",phone_work);
		 request.setAttribute("primary_address_street",primary_address_street);
		 request.setAttribute("primary_address_city",primary_address_city);
		 request.setAttribute("primary_address_state",primary_address_state);
		 request.setAttribute("primary_address_postalcode",primary_address_postalcode);
		 request.setAttribute("primary_address_country",primary_address_country);
		 request.setAttribute("lead_source",lead_source);
		 request.setAttribute("emailId",emailId);
		 request.setAttribute("buyerlead","buyerlead");
		
		 return new ModelAndView("frmPricing");
	 }
	 
	 
		
	@RequestMapping("/buyerLeadForm.html")
	public ModelAndView buyerLeadForm(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
						
		String blFName = request.getParameter("blFName");
		String blLName = request.getParameter("blLName");
		String blComp = request.getParameter("blComp");
		String blTitle = request.getParameter("blTitle");
		String blTypoBusiness = request.getParameter("blTypoBusiness");
		String blWebsite = request.getParameter("blWebsite");
		String blOffPh = request.getParameter("blOffPh");
		String blEmailId = request.getParameter("blEmailId");	
		String blPriAddStrt = request.getParameter("blPriAddStrt");
		String blPriAddCity = request.getParameter("blPriAddCity");		
		String blPriAddState = request.getParameter("blPriAddState");
		String blPriAddzip = request.getParameter("blPriAddzip");
		String blPriAddCountry = request.getParameter("blPriAddCountry");
		String blLeadSrc = request.getParameter("blLeadSrc");
		String account_name = request.getParameter("account_name");
		String description = request.getParameter("description");
			
		
		
		return new ModelAndView("frmLeadSuccess");
	}
}

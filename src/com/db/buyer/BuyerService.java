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
package com.db.buyer;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.xmlrpc.XmlRpcException;

import com.artifact.action.GldSignUpAction;
import com.db.GeneralDBAction;
import com.hlcmrm.util.Debug;
import com.infusionejb.session.InfusionSessionBean;

//@WebService(endpointInterface="com.db.buyer.BuyerServiceInterface")
//public class BuyerService implements BuyerServiceInterface {
	public class BuyerService {
	
	static Logger log = Logger.getLogger(BuyerService.class.getName());
	public static final String LOG_FILE = "Log4j.properties";
	InputStream is;
	static Properties properties; 
	
	private InfusionSessionBean obj1;
	{
		try{
			obj1 = new InfusionSessionBean();
		}catch(Exception e){
			 throw new RuntimeException(e);
		}
	}
	
	BuyerService(){
		//=====================Properties file configuration started here==============================
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
	    //=====================Properties file configuration ended here==============================
	      
	      /*
			 * *===================Property file loaded here======================================
			 */
	        properties = new Properties();        
	        try {               
	        	properties.load(GldSignUpAction.class.getResourceAsStream("/config.properties"));
	        	properties.load(GldSignUpAction.class.getResourceAsStream("/paypal.properties"));
	        	properties.load(getClass().getClassLoader().getResourceAsStream("/infusionMail.properties"));
	        	
	        	System.out.println("Property file loaded successfully");
	            } catch (Exception e) {
	                try {
	                  properties.load(GldSignUpAction.class.getResourceAsStream("/config.properties"));
	                  properties.load(GldSignUpAction.class.getResourceAsStream("/paypal.properties"));
	                }catch(Exception ee) {
	                   Debug.print("Could not load the config.properties");
	                }
	            }
	        /*
			 * *===================Property file loaded here======================================
			 */
	}
	
	/* ==================================== Buyer details  ==============================================  */
	 

	 /*Pushing Buyer details
	  * Date: 13-05-2017
	  * Author:Amalesh R
	  * digiBlitz Inc.
	 */
	//@Override
	public String pushBuyerDetailsToSignUp(String input) {
		// TODO Auto-generated method stub
		
		new BuyerService();
		
		GeneralDBAction db = new GeneralDBAction();
		
		ArrayList buyerList = null;
		String sNo = null;
		String clientName = null;
		String website = null;
		String contactPerson = null;
		String emailId = null;
		String contactPhone = null;
		String fax = null;
		
		String regStatusId = null;
		
		 String country = "NA";
         String state = "NA";
         String city = "NA";
         
         String credit_card_type = "MasterCard";
         String credit_card_no = "5500005555555559";
         String cvv_no = "111";
         String name_on_card = "Dhivya S";
         String expiry_date = "12/2018";
         
         String productPrice = "NA";
         String payOption = "try_now";
         String coupon_valid = null;
         String coupon_status = "NA";
         String coupon_value = "NA";
         String coupon_description = "NA";
         String coupon_code = "NA";
         String street_name = "NA";
         String zipcode = "NA";
         String customerCategory = "Buyer";
         String product_plan = "Basic";
         String subscriptionType = "trail for 30 days";
                
         String status = null;
		
		try{
			
			if(input != null && input != ""){				
						
			buyerList = new ArrayList();
			buyerList = db.getBuyerDetails();
			
			if(buyerList != null && buyerList.size() != 0){
				
				Iterator itr = buyerList.iterator();
				while(itr.hasNext()){
					String strarr[] = (String[])itr.next();
					
					sNo = strarr[0];
					clientName = strarr[1];
					website = strarr[2];
					contactPerson = strarr[3];
					emailId = strarr[4];
					contactPhone = strarr[5];
					fax = strarr[6];
					
					System.out.println(" in servlet method ===> sNo ---> "+sNo+" clientName ---> "+clientName
        					+" website ---> "+website+" contactPerson ---> "+contactPerson
        					+" emailId ---> "+emailId+" contactPhone ---> "+contactPhone
        					+" fax ---> "+fax);
					
								          
			          String userid = db.getUserIdbyCompany(clientName);
			          			          
			          if(userid == null){
			        	  
			        	  System.out.println("userid ---> "+userid);
			        	  //status=db.updateusersignup(firstname,lastname,e_mail,mobileno,institutionName,country,state, city, credit_card_type, credit_card_no, cvv_no, name_on_card, expiry_date, productPrice, payOption, subscriptionType, product_plan,coupon_valid,coupon_status,coupon_value,coupon_description,coupon_code,street_name,zipcode);
						  regStatusId=db.insertsignup(clientName,clientName,emailId,
								  contactPhone,clientName,country,state, city, credit_card_type,
								  credit_card_no, cvv_no, name_on_card, expiry_date, productPrice, subscriptionType,
								  payOption, product_plan,coupon_valid,coupon_status,coupon_value,coupon_description,
								  coupon_code,street_name,zipcode,customerCategory); 
			          /*else{
			            regStatusId = db.updateusersignup(clientName,clientName,emailId,
			            		contactPhone,clientName,country,state, city, credit_card_type,
			            		credit_card_no, cvv_no, name_on_card, expiry_date, productPrice, 
			            		payOption, subscriptionType, product_plan,coupon_valid,
			            		coupon_status,coupon_value,coupon_description,coupon_code,
			            		street_name,zipcode);
						//regStatusId=db.insertsignup(firstname,lastname,e_mail,mobileno,institutionName,country,state, city, credit_card_type, credit_card_no, cvv_no, name_on_card, expiry_date, productPrice, subscriptionType, payOption, product_plan,coupon_valid,coupon_status,coupon_value,coupon_description,coupon_code,street_name,zipcode);
			          }*/
			            
			          if(regStatusId != null && regStatusId != "null"){
			             
			             String instituteId = db.selectTryInstitutionId(emailId, regStatusId);			            
			             String registrationId = db.selectRegistrationId(emailId, regStatusId);			            
			             String CustomerUserName = db.selectCustomerUserName(emailId, regStatusId);
			             String Customerpassword = db.selectCustomerpassword(emailId, regStatusId);
			             String customerOrderId = db.selectTryCustomerOrderId(emailId, regStatusId);
			             
			             String productStatus = "active";
			             boolean updateProductStatus = false;
			             updateProductStatus = db.updateProductStatus(emailId, regStatusId, productStatus);
			             DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			             Date date = new Date();
			             
			             if(updateProductStatus == true){
					        	
			 		        log.info("Dear Customer:"+clientName+" "+clientName+"\n");  
			 		        log.info("Customer email :"+emailId);  
			 		        log.info("Timestamp :"+dateFormat.format(date));
			 		        log.info("Product Status :"+productStatus);
			 		        log.info("Product status updated on database---------------->");

			 		        }else{
			 		        	
			 		        	log.info("Dear Customer:"+clientName+" "+clientName+"\n");  
				 		        log.info("Customer email :"+emailId);  
				 		        log.info("Timestamp :"+dateFormat.format(date));
				 		        log.info("Product Status :"+productStatus);
			 			        log.info("Product status was not updated on database---------------->");
			 		        }
			             
			             
			             
			             //session.setAttribute("aftFName", instituteId);
			/*--------------------mail config start here-------------------------------*/
			             
			             
			           //String fromAddress = "prabhu.pandi@digiblitz.in";
			             String fromAddress = properties.getProperty("infusionMail.fromAddress");
			             String toAddress = emailId;
			             String ccAddresses = properties.getProperty("infusionMail.ccPaymentAddress");
			             String bccAddresses = properties.getProperty("infusionMail.bccPaymentAddress");
			             String contentType = "HTML";
			             String subject = "Registration Success!";
			             String htmlBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
		                         "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
		                         "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
		                         " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
		                         "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Congratulations "+clientName+"</h4><p style=\"   font-size: 19px;" +
		                           "line-height: 27px; margin-bottom: 16px;margin-top: 16px; text-align: center;\">You are registered Successfully... </p><table style=\" clear: both; margin: 0 auto;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
		                           "<tbody> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Registration Details</h4></td>" +
		                           "</tr> </tbody> </table> <p>&nbsp; </p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td align=\"center\" valign=\"top\" style=\"background-color: #fff;  padding: 16px; text-align: center; vertical-align: top;\">" +
		                           "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody> <tr> <td style=\"color: #888;\" align=\"center\" valign=\"top\" style=\" padding: 16px; text-align: center; vertical-align: top;\">" +
		                           " <p style=\" line-height: 23px; margin-bottom: 24px; margin-top: 16px;font-size: 15px;\">" +
										"<strong>E-mail ID :</strong><br /> "+emailId+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
		                           "<strong>Customer ID :</strong><br /> "+instituteId+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
		                           "<strong>Registration ID :</strong><br /> "+registrationId+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
		                           "<strong>Customer Order ID</strong><br /> "+customerOrderId+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
									  "<strong>Username and Password :</strong><br /> Will be sent to you shortly..</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
		                           "</td> </tr> </tbody></table></td></tr></tbody></table></div></div></div></div></body></html>";
			             
			             //String textBody = "Congradulations! You are registered successfully and your username: "+CustomerUserName+" and the password: "+Customerpassword;
			             String textBody = "Congratulations! You are registered successfully.";
			       
			             //Infusion Soft mail starts
			                              
			    //obj1.sendEmail(properties.getProperty("infusionMail.fromAddress"), usrEmail, properties.getProperty("infusionMail.ccAddresses"), properties.getProperty("infusionMail.bccAddresses"), properties.getProperty("infusionMail.contentType"), properties.getProperty("infusionMail.subject"), properties.getProperty("infusionMail.htmlBody"), properties.getProperty("infusionMail.textBody"));
			         /*try {
			        	 obj1.createAndCheckDuplicateContact(clientName, clientName, toAddress);
			        	 obj1.optin_outEmail(toAddress);			        	 
						obj1.sendEmail(fromAddress, toAddress, ccAddresses, bccAddresses, contentType, subject, htmlBody, textBody);
					} catch (XmlRpcException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
			         
			         status = "Buyer details signed up successfully";			         
				}
				}
			}
		}
	}
	}catch(Exception e){
			e.printStackTrace();
	}
		
		
		return status;
	}

}

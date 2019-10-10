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
package com.artifact.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.xml.sax.SAXException;

import com.db.GeneralDBAction;
import com.hlcmrm.util.Debug;
import com.infusionejb.session.InfusionSessionBean;
import com.util.email.EmailContent;
import com.util.email.MailMail;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.xmlrpc.XmlRpcException;

public class GldSignUpAction implements Controller{
	GeneralDBAction db = new GeneralDBAction();
	CreditCard ccard= new CreditCard();
	static Properties properties; 
	
	static Logger log = Logger.getLogger(GldSignUpAction.class.getName());
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
			IOException, ParserConfigurationException, SAXException, ParseException {
		
		HttpSession session=request.getSession(true);
		
		//=====================Properties file configuration started here==============================
		 Properties logProp = new Properties();  
	      is = (InputStream) getClass().getClassLoader().getResourceAsStream(LOG_FILE);
	      logProp.load(is); 
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
	      
		
		String signUpProcess = request.getParameter("signUpProcess");
		
		if(signUpProcess.equalsIgnoreCase("getStarted"))
        { 
			String mF_dBStore_url = null;
			mF_dBStore_url = properties.getProperty("config.mF_dBStore_url");
			System.out.println("mF_dBStore_url is got successfully from property file");
			request.setAttribute("mF_dBStore_url", mF_dBStore_url);
			return new ModelAndView("frmPricing"); 
        }
		
		if(signUpProcess.equalsIgnoreCase("pricingDetails"))
        { 
			String  price = (String)request.getParameter("getProductPrice");
			String  customerCategory = (String)request.getParameter("category");
			String purchase = "try_now";
			if(price!=null){
				purchase = "buy_now";
			//Double temp = Double.valueOf(price);
				String productPrice = price;
			System.out.println("Product Price :::: "+productPrice);
			//double productPrice = temp;
			String  productPlan = (String)request.getParameter("productPlan");
			request.setAttribute("productPlan", productPlan);
			request.setAttribute("productPrice", productPrice);
			request.setAttribute("purchase", purchase);
			request.setAttribute("category", customerCategory);
			return new ModelAndView("onlineEdition"); 
			}
			request.setAttribute("category", customerCategory);
			request.setAttribute("purchase", purchase);
			return new ModelAndView("onlineEdition"); 
        }
		
		if(signUpProcess.equalsIgnoreCase("preaskcoupon"))
        { 
			String  customerCategory = (String)request.getParameter("category");
			String  productPrice = (String)request.getParameter("getProductPrice");
			System.out.println("productPrice==========="+productPrice);
			String  productPlan = (String)request.getParameter("productPlan");
			System.out.println("productPlan==========="+productPlan);
			
			   System.out.println("Company category==========="+customerCategory);
			   request.setAttribute("category",customerCategory);
			   request.setAttribute("productPrice",productPrice);
			   request.setAttribute("productPlan",productPlan);
			return new ModelAndView("AskCoupon"); 
        }
		if(signUpProcess.equalsIgnoreCase("askcoupon"))
        { 
			String requiredCoupon = null;
			requiredCoupon = request.getParameter("requiredcoupon");
			
			String  customerCategory = (String)request.getParameter("category");
			   System.out.println("Company category==========="+customerCategory);
			   String  productPrice = (String)request.getParameter("productPrice");
				System.out.println("productPrice==========="+productPrice);
				String  productPlan = (String)request.getParameter("productPlan");
				System.out.println("productPlan==========="+productPlan);
				
			   request.setAttribute("category",customerCategory);
			   request.setAttribute("productPrice",productPrice);
			   request.setAttribute("productPlan",productPlan);
			   
			if(requiredCoupon != null && requiredCoupon.equalsIgnoreCase("yescoupon")){
				
				return new ModelAndView("getCoupon");
			}else{
				request.setAttribute("purchase", "try_now");
				//return new ModelAndView("onlineEdition");
				return new ModelAndView("frmvendorsignuptrial");
			}
			
        }
		
		if(signUpProcess.equalsIgnoreCase("getcoupon"))
        { 
			String getCoupon = null;
			String coupon_status = null;
	          String coupon_value = null;
	          String coupon_description = null;
	          String coupon_valid = null;
			getCoupon = request.getParameter("coupon_code");
			System.out.println("coupon code=="+getCoupon);
			String  customerCategory = (String)request.getParameter("category");
			   System.out.println("Company category==========="+customerCategory);
			   request.setAttribute("category",customerCategory);
			   
			if(getCoupon != null){
				 boolean coupon_status_db = false;
				 coupon_status_db = db.getCouponStatusIndb(getCoupon);
					 if(coupon_status_db == true){
						 ArrayList couponDetails = db.getCouponDetails(getCoupon);
						 if(couponDetails!=null && couponDetails.size()!=0){
								Iterator itEntList = couponDetails.iterator();
								int i=1;
								while(itEntList.hasNext()){
								
									String strCouponList[]= (String[])itEntList.next();
									coupon_status = strCouponList[0];
									coupon_value = strCouponList[1];
									coupon_description = strCouponList[2];
									coupon_valid = strCouponList[3];
								}
						 }
					 }else{
						 request.setAttribute("statusCoupon","fail");
						 return new ModelAndView("couponValidate"); 
					 }
					 request.setAttribute("coupon_status",coupon_status);
					 request.setAttribute("coupon_code",getCoupon);
					 request.setAttribute("coupon_value",coupon_value);
					 request.setAttribute("coupon_description",coupon_description);
					 request.setAttribute("coupon_valid",coupon_valid);
					 request.setAttribute("statusCoupon","success");
					 return new ModelAndView("couponValidate");
			 }else{
				request.setAttribute("purchase", "try_now");
				//return new ModelAndView("onlineEdition");
				return new ModelAndView("frmvendorsignuptrial");
			}
			
        }
		
		if(signUpProcess.equalsIgnoreCase("aftergetcoupon"))
        { 
			String  customerCategory = (String)request.getParameter("category");
			   System.out.println("Company category==========="+customerCategory);
			   request.setAttribute("category",customerCategory);
			   
			  String coupon_code = null;
	          coupon_code = request.getParameter("coupon_code");
	          String coupon_status = null;
	          String coupon_value = null;
	          String coupon_description = null;
	          String coupon_valid = null;
	        		  
	          coupon_status = request.getParameter("coupon_status");
	          coupon_value = request.getParameter("coupon_value");
	          coupon_description = request.getParameter("coupon_description");
	          coupon_valid = request.getParameter("coupon_valid");
	        
	          request.setAttribute("coupon_code",coupon_code);
				 request.setAttribute("coupon_status",coupon_status);
				 request.setAttribute("coupon_value",coupon_value);
				 request.setAttribute("coupon_description",coupon_description);
				 request.setAttribute("coupon_valid",coupon_valid);
				request.setAttribute("purchase", "try_now");
				//return new ModelAndView("onlineEdition");
				return new ModelAndView("frmvendorsignuptrial");			
        }
		
		 if(signUpProcess.equalsIgnoreCase("signup"))
         {  
			 String customerCategory = null;
			 String regStatusId=null;
	          String firstname=request.getParameter("firstname");
	          String lastname=request.getParameter("lastname");
	          String e_mail=request.getParameter("email");
	          String mobileno=request.getParameter("mobileno");
	          String institutionName=request.getParameter("institutionName");
	          String country=request.getParameter("country");
	          String state=request.getParameter("state");
	          String city=request.getParameter("city");
	          String productPrice = (String) request.getParameter("productPrice");
	          String credit_card_type=request.getParameter("credit_card_type");
	          String credit_card_no=request.getParameter("credit_card_no");
	          String cvv_no=request.getParameter("cvv_no");
	          String name_on_card=request.getParameter("name_on_card");
	          String expiry_month=request.getParameter("expiry_month");
	          String expiry_year=request.getParameter("expiry_year");
	          String expiry_date = expiry_month+"/"+expiry_year;
	          String product_plan = null;
	          String subscriptionType = null;
	          String street_name = request.getParameter("street");
	          String zipcode = request.getParameter("zipcode");
	          
	          String coupon_code = null;
	          coupon_code = request.getParameter("coupon_code");
	          String coupon_status = null;
	          String coupon_value = null;
	          String coupon_description = null;
	          String coupon_valid = null;
	        		  
	          coupon_status = request.getParameter("coupon_status");
	          coupon_value = request.getParameter("coupon_value");
	          coupon_description = request.getParameter("coupon_description");
	          coupon_valid = request.getParameter("coupon_valid");
	          
	          customerCategory = request.getParameter("category");
	          System.out.println("category------------>"+customerCategory);
	          
	          
	          System.out.println("street name ::::"+street_name);
	          System.out.println("zipcode ::::"+zipcode);
	          System.out.println("productPrice ::::"+productPrice);
	          System.out.println("firstname ::::"+firstname);
	          System.out.println("lastname ::::"+lastname);
	          System.out.println("e_mail ::::"+e_mail);
	          System.out.println("mobileno ::::"+mobileno);
	          System.out.println("institutionName ::::"+institutionName);
	          System.out.println("country ::::"+country);
	          System.out.println("state ::::"+state);
	          System.out.println("city ::::"+city);
	          System.out.println("credit_card_type ::::"+credit_card_type);
	          System.out.println("credit_card_no ::::"+credit_card_no);
	          System.out.println("cvv_no ::::"+cvv_no);
	          System.out.println("name_on_card ::::"+name_on_card);
	          System.out.println("expiry_date ::::"+expiry_date);
	          
	          System.out.println("coupon_code ::::"+coupon_code);
	          
	          
			 String payOption = (String)request.getParameter("payOption");
			 System.out.println("payOption :::: hi"+payOption);
			 
				 if(payOption.equalsIgnoreCase("try_now")){
					 product_plan = "basic";
					 subscriptionType = "trail for 30 days";
					 System.out.println("inside try_now :::::::::");
					 
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
					   	   			request.setAttribute("fName",firstname);
					   	   			request.setAttribute("lName",lastname);
					   	   			request.setAttribute("emailId",e_mail);
					   	   			request.setAttribute("pay_productPlan",product_plan);
					   	   			//String fromAddress = "prabhu.pandi@digiblitz.in";
					                 String fromAddress = properties.getProperty("infusionMail.fromAddress");
					                 String toAddress = e_mail;
					                 String ccAddresses = properties.getProperty("infusionMail.ccPaymentAddress");
							         String bccAddresses = properties.getProperty("infusionMail.bccPaymentAddress");
					                 String contentType = "HTML";
					                 String subject = "Registration Failure";
					                 String htmlBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
			                                  "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
			                                  "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
			                                  " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
			                                  "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Dear "+firstname+" "+lastname+"</h4><p style=\"   font-size: 19px;" +
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
					        request.setAttribute("fName",firstname);
					        request.setAttribute("lName",lastname);
					        request.setAttribute("emailId",e_mail);
					        request.setAttribute("pay_productPlan",product_plan);
					        //String fromAddress = "prabhu.pandi@digiblitz.in";
				            String fromAddress = properties.getProperty("infusionMail.fromAddress");
				            String toAddress = e_mail;
				            String ccAddresses = properties.getProperty("infusionMail.ccPaymentAddress");
				            String bccAddresses = properties.getProperty("infusionMail.bccPaymentAddress");
				            String contentType = "HTML";
				            String subject = "Registration Failure!";
				            String htmlBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
	                                  "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
	                                  "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
	                                  " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
	                                  "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Dear "+firstname+" "+lastname+"</h4><p style=\"   font-size: 19px;" +
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
	         // double 
	          
	          System.out.println("firstname :::"+firstname);
	          System.out.println("e_mail :::"+e_mail);
	          System.out.println("institutionName :::"+institutionName);
	          String userid=db.getUserIdbyEmailPlan(e_mail, product_plan, subscriptionType);
	          
	          System.out.println("customer category ---------------"+customerCategory);
	          if(userid == null){
	        	  //status=db.updateusersignup(firstname,lastname,e_mail,mobileno,institutionName,country,state, city, credit_card_type, credit_card_no, cvv_no, name_on_card, expiry_date, productPrice, payOption, subscriptionType, product_plan,coupon_valid,coupon_status,coupon_value,coupon_description,coupon_code,street_name,zipcode);
				  regStatusId=db.insertsignup(firstname,lastname,e_mail,mobileno,institutionName,country,state, city, credit_card_type, credit_card_no, cvv_no, name_on_card, expiry_date, productPrice, subscriptionType, payOption, product_plan,coupon_valid,coupon_status,coupon_value,coupon_description,coupon_code,street_name,zipcode,customerCategory); 
	          }else{
	            regStatusId = db.updateusersignup(firstname,lastname,e_mail,mobileno,institutionName,country,state, city, credit_card_type, credit_card_no, cvv_no, name_on_card, expiry_date, productPrice, payOption, subscriptionType, product_plan,coupon_valid,coupon_status,coupon_value,coupon_description,coupon_code,street_name,zipcode,customerCategory);
				//regStatusId=db.insertsignup(firstname,lastname,e_mail,mobileno,institutionName,country,state, city, credit_card_type, credit_card_no, cvv_no, name_on_card, expiry_date, productPrice, subscriptionType, payOption, product_plan,coupon_valid,coupon_status,coupon_value,coupon_description,coupon_code,street_name,zipcode);
	          }
	            
	          if(regStatusId != null && regStatusId != "null"){
	             request.setAttribute("fName", firstname);
	             request.setAttribute("lName", lastname);
	             request.setAttribute("emailId", e_mail);
	             request.setAttribute("instituteName", institutionName);
	             request.setAttribute("pay_productPlan","Basic");
	             String instituteId = db.selectTryInstitutionId(e_mail, regStatusId);
	             request.setAttribute("instituteID", instituteId);
	             String registrationId = db.selectRegistrationId(e_mail, regStatusId);
	             request.setAttribute("registrationId", registrationId);
	             String CustomerUserName = db.selectCustomerUserName(e_mail, regStatusId);
	             request.setAttribute("CustomerUserName", CustomerUserName);
	             String Customerpassword = db.selectCustomerpassword(e_mail, regStatusId);
	             request.setAttribute("Customerpassword", Customerpassword);
	             String customerOrderId = db.selectTryCustomerOrderId(e_mail, regStatusId);
	             request.setAttribute("customerOrderId", customerOrderId);
	             
	             String productStatus = "active";
	             boolean updateProductStatus = false;
	             updateProductStatus = db.updateProductStatus(e_mail, regStatusId, productStatus);
	             DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	             Date date = new Date();
	             if(updateProductStatus == true){
			        	
	 		        log.info("Dear Customer:"+firstname+" "+lastname+"\n");  
	 		        log.info("Customer email :"+e_mail);  
	 		        log.info("Timestamp :"+dateFormat.format(date));
	 		        log.info("Product Status :"+productStatus);
	 		        log.info("Product status updated on database---------------->");

	 		        }else{
	 		        	
	 		        	log.info("Dear Customer:"+firstname+" "+lastname+"\n");  
		 		        log.info("Customer email :"+e_mail);  
		 		        log.info("Timestamp :"+dateFormat.format(date));
		 		        log.info("Product Status :"+productStatus);
	 			        log.info("Product status was not updated on database---------------->");
	 		        }
	             
	             request.setAttribute("coupon_code", coupon_code);
	             request.setAttribute("coupon_valid", coupon_valid);
	             
	             session.setAttribute("aftFName", firstname);
	             session.setAttribute("aftLastname", lastname);
	             session.setAttribute("aftE_mail", e_mail);
	             session.setAttribute("aftInstitutionName", institutionName);
	             session.setAttribute("aftInstituteId", instituteId);
	             session.setAttribute("aftRegistrationId", registrationId);
	             session.setAttribute("aftCustomerUserName", CustomerUserName);
	             session.setAttribute("aftCustomermobileno", mobileno);
	             session.setAttribute("aftcoupon_code", coupon_code);
	             session.setAttribute("aftCustomerOrderId", customerOrderId);
	             session.setAttribute("aftcoupon_valid", coupon_valid);
	             
	             //session.setAttribute("aftFName", instituteId);
	/*--------------------mail config start here-------------------------------*/
	             
	             
	           //String fromAddress = "prabhu.pandi@digiblitz.in";
	             String fromAddress = properties.getProperty("infusionMail.fromAddress");
	             String toAddress = e_mail;
	             String ccAddresses = properties.getProperty("infusionMail.ccPaymentAddress");
	             String bccAddresses = properties.getProperty("infusionMail.bccPaymentAddress");
	             String contentType = "HTML";
	             String subject = "Registration Success!";
	             String htmlBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
                         "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
                         "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
                         " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
                         "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Congratulations "+firstname+" "+lastname+"</h4><p style=\"   font-size: 19px;" +
                           "line-height: 27px; margin-bottom: 16px;margin-top: 16px; text-align: center;\">You are registered Successfully... </p><table style=\" clear: both; margin: 0 auto;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
                           "<tbody> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Registration Details</h4></td>" +
                           "</tr> </tbody> </table> <p>&nbsp; </p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td align=\"center\" valign=\"top\" style=\"background-color: #fff;  padding: 16px; text-align: center; vertical-align: top;\">" +
                           "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody> <tr> <td style=\"color: #888;\" align=\"center\" valign=\"top\" style=\" padding: 16px; text-align: center; vertical-align: top;\">" +
                           " <p style=\" line-height: 23px; margin-bottom: 24px; margin-top: 16px;font-size: 15px;\">" +
								"<strong>E-mail ID :</strong><br /> "+e_mail+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
                           "<strong>Customer ID :</strong><br /> "+instituteId+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
                           "<strong>Registration ID :</strong><br /> "+registrationId+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
                           "<strong>Customer Order ID</strong><br /> "+customerOrderId+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
							  "<strong>Username and Password :</strong><br /> Will be sent to you shortly..</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
                           "</td> </tr> </tbody></table></td></tr></tbody></table></div></div></div></div></body></html>";
	             
	             String textBody = "Congradulations! You are registered successfully and your username: "+CustomerUserName+" and the password: "+Customerpassword;
	       
	             //Infusion Soft mail starts
	                              
	    //obj1.sendEmail(properties.getProperty("infusionMail.fromAddress"), usrEmail, properties.getProperty("infusionMail.ccAddresses"), properties.getProperty("infusionMail.bccAddresses"), properties.getProperty("infusionMail.contentType"), properties.getProperty("infusionMail.subject"), properties.getProperty("infusionMail.htmlBody"), properties.getProperty("infusionMail.textBody"));
	         try {
				obj1.sendEmail(fromAddress, toAddress, ccAddresses, bccAddresses, contentType, subject, htmlBody, textBody);
			} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
	                     
	                     System.out.println("Mail had been send successfully");                                 
	            //Infusion Soft mail ends
	             
	             String emailToUSEA="parasu88@gmail.com";
	             String toMailIds[] = {emailToUSEA,e_mail};
	             EmailContent email = new EmailContent();
	             email.setTo(toMailIds);
	             email.setFrom("info@digiblitz.com");
	             email.setSubject("Free Subscribe Details");
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
	             "<p>Your subscription  has been made successfully and the details are as follows:<p>" +
	             "<span class=\"boldTxt\">Free Subscribe Details</span></p>" +
	             
	             "Email ID:" + e_mail + "<br/></p>" +
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
	             "</td>" +
	             "</tr>" +
	             " <tr>" +
	             "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>" +
	             "</tr>" +
	             "</table>";
	             email.setBody(content);
	             MailMail mail = new MailMail();
	             boolean emailFlag = mail.sendMimeEmail(email);
	             Debug.print("Email sent sucessfully :" + emailFlag);
	             
	             
	             /*-----------WELCOME MAIL----------*/
	             EmailContent welcome_email = new EmailContent();
	             welcome_email.setTo(toMailIds);
	             welcome_email.setFrom("info@digiblitz.com");
	             welcome_email.setSubject("Welcome");
	             String welcome_content = "<table align=\"center\" width=\"500px\" cellspacing=\"5\" bgcolor =\"#CCCCCC\">"+
	                     "<tr>"+
	             		"<td height=\"122\" colspan=\"3\" align=\"left\">"+
	             		"<p>"+"Hi,"+
	             		  "<br>"+
	             		  "We are glad to know your interest in menschForce: A Contingent workforce management system powered by ELMT"+
	             		 
	                       "</p>"+
	                   "</td>"+
	                   "</tr>"+
	                   "<tr align=\"left\">"+
	                   "<td width=\"49%\" height=\"155\">"+
	                   "<br>"+
	                   "<br>"+
	                   "<br>"+
	                   "<h4>"+
	                   "<font color=\"red\">"+
	                   "HR-hiring process"+
	                   "</font>"+
	                   "</h4>"+
	                     "<p>"+
	                     "Allows you to post a comprehensive, tailor made requirement. " +
	                     "The suppliers and consultants can submit resumes, rates and multiple supporting documents." +
	                     "You will have process to manage and measure performances through reports and score cards."+
	                     "</p>"+  
	                     "</td>"+
	                     "<td width=\"7%\">"+
	                     "<b>"+"What would you enjoy using our product?"+
	                     "</b>"+
	                     "<br>"+
	                     "<br>"+
	                     "<br>"+
	                     "<br>"+
	                     "<br>"+
	                     "<br>"+
	                     "<br>"+
	                     "<br>"+
	                     "<br>"+
	                     "<br>"+
	                     "</td>"+
	                     "<td width=\"44%\" height=\"155\">"+
	                     "<br>"+"<br>"+"<br>"+
	                     "<h4>"+
	                     "<font color=\"red\">"+
	                     "Finance "+
	                     "</font>"+
	                     "</h4>"+
	                     "<p>"+
	                   "You could create, modify & optimize resource rates as per the project budgets and market scenarios. The system provides resource costing aggregated by projects and suppliers. The complete process is automated seamlessly & works effectively with other organizational processes."+
	                     "</p>"+   
	                   "</td>"+
						  "</tr>"+
	                   "<tr align=\"left\">"+
	                   "<td width=\"49%\" height=\"155\">"+
	                   "<br>"+
	                   "<br>"+
	                   "<br>"+
	                   "<h4>"+
	                   "<font color=\"red\">"+
	                   " Automated Work Flow"+
	                   "</font>"+
	                   "</h4>"+
	                     "<p>"+
	                     "Fully Automated Work Flow through our state-of-the-art BPM process template dBBPM. Automated Communications between the stakeholders makes the   system completely effective in closing the positions quick. In-built Modeller to create custom business process." +
	                     "</p>"+  
	                     "</td>"+"<td>"+"</td>"+
	                     "<td width=\"44%\" height=\"155\">"+
	                     "<br>"+"<br>"+"<br>"+
	                     "<h4>"+
	                     "<font color=\"red\">"+
	                     "Immigration Law Compliance "+
	                     "</font>"+
	                     "</h4>"+
	                     "<P>"+
	                   "<ul>"+
	                   "<li type =\"square\">"+"Corporate Legal Compliance"+"<BR>"+
	                   "<li type =\"square\">"+	"Contracts Control/compliance"+"</li>"+
	                   "<li type =\"square\">"+"Department of Labor Process Compliance"+"</li>"+
	                   "<li type =\"square\">"+"USCIS Regulations Compliance"+"</li>"+"</ul>"+"</P>"+
	                   "<li type =\"square\">"+"DOS Process Compliance "+"</li>"+"</ul>"+"</P>"+
	                   "</td>"+
	                   "</tr>"+
	                   "<tr>"+"<td height=\"100\" colspan=\"3\">"+
	                   "<P>"+
						  "You will be contacted by our support  executives shortly to take you for a "+
						  "<BR>"+"virtual walk explain the product"+"&rsquo;s  "+"features and functionality."+"<BR>"+"<BR>"+ "<BR>"+ "<BR>"+ "You can reach us anytime at  support@menchforce.com"+"<BR>"+ "<BR>"+
						  "Talk to us at: +1-571-297-2288"+
	                    
	                            "</td>"+"</tr>"+
	                           "</table>";
	             welcome_email.setBody(welcome_content);
	             MailMail wel_mail = new MailMail();
	             boolean wel_emailFlag = wel_mail.sendMimeEmail(welcome_email);
	             Debug.print("Email sent sucessfully :" + wel_emailFlag);
	             
	             
	             
	             
	          }
	      	   
	          return new ModelAndView("signUpDetails");  
				 }
			 else if(payOption.equalsIgnoreCase("buy_now")){
				 
				 String pay_firstname=request.getParameter("firstname");
				 System.out.println("PAY_FIRSTNAME IN GUILD ACTION ::::::::::::::::::::::"+pay_firstname);
		          String pay_lastname=request.getParameter("lastname");
		          String pay_e_mail=request.getParameter("email");
		          String pay_mobileno=request.getParameter("mobileno");
		          String pay_institutionName=request.getParameter("institutionName");
		          String pay_country=request.getParameter("country");
		          String pay_state=request.getParameter("state");
		          String pay_city=request.getParameter("city");
		          String pay_productPrice = (String) request.getParameter("productPrice");
		          String pay_credit_card_type=request.getParameter("credit_card_type");
		          String pay_credit_card_no=request.getParameter("credit_card_no");
		          String pay_cvv_no=request.getParameter("cvv_no");
		          String pay_name_on_card=request.getParameter("name_on_card");
		          String pay_expiry_month=request.getParameter("expiry_month");
		          String pay_expiry_year=request.getParameter("expiry_year");
		          String pay_expiry_date = pay_expiry_month+"/"+pay_expiry_year;
		          String pay_subscriptionType = request.getParameter("plan");
		          String pay_productPlan = request.getParameter("Productplan");
		          
		          String pay_street_name = request.getParameter("street");
		          String pay_zipcode = request.getParameter("zipcode");
		          String couponcode = "";
		          couponcode = request.getParameter("couponcode");
		          
		          
		          if(couponcode != null && !(couponcode.equalsIgnoreCase("null")) && couponcode != "" ){
						
								 ArrayList couponDetails = db.getCouponDetails(couponcode);
								 if(couponDetails!=null && couponDetails.size()!=0){
										Iterator itEntList = couponDetails.iterator();
										int i=1;
										while(itEntList.hasNext()){
										
											String strCouponList[]= (String[])itEntList.next();
											coupon_status = strCouponList[0];
											coupon_value = strCouponList[1];
											coupon_description = strCouponList[2];
											coupon_valid = strCouponList[3];
										}
								 }else{
									System.out.println("No coupon information in database-------------------------->");
								 }
								
					 }else{
						 couponcode = "";
						 System.out.println("with out coupon process-------------------------->");
					}
		          
		          
		          String aCard = "";
		          
			         aCard = pay_credit_card_no;
			         System.out.print("Card number : ");

			         String cvvno= pay_cvv_no; 
			         System.out.print("CVV number : ");

			       	      System.out.print("Expire Date : ");
			       	      String dt= pay_expiry_date;
			       if (ccard.getCardID(aCard) > -1) {
			          System.out.println("This card is supported.");
			   	   		if(ccard.CVV(cvvno) == 4){
			   	   			System.out.println("This CVV is supported.");
			   	   		} 
			   	   		else{
			   	   			System.out.println("This CVV is not supported.");
			   	   			request.setAttribute("cvvstatus","fail");
			   	   		request.setAttribute("cardstatus","success");
			   	     request.setAttribute("fName",pay_firstname);
			          request.setAttribute("lName",pay_lastname);
			          request.setAttribute("emailId",pay_e_mail);
			          request.setAttribute("pay_productPlan",pay_productPlan);
			          
			          //String fromAddress = "prabhu.pandi@digiblitz.in";
		                 String fromAddress = properties.getProperty("infusionMail.fromAddress");
		                 String toAddress = pay_e_mail;
		                 String ccAddresses = "";
		                 String bccAddresses = "";
		                 String contentType = "HTML/TEXT";
		                 String subject = "Your Username";
		                 String htmlBody = "Sorry! Your payment has failed";
		                 String textBody = "Sorry! Your payment has failed";
		           
		                 //Infusion Soft mail starts
		                                  
		        //obj1.sendEmail(properties.getProperty("infusionMail.fromAddress"), usrEmail, properties.getProperty("infusionMail.ccAddresses"), properties.getProperty("infusionMail.bccAddresses"), properties.getProperty("infusionMail.contentType"), properties.getProperty("infusionMail.subject"), properties.getProperty("infusionMail.htmlBody"), properties.getProperty("infusionMail.textBody"));
		             try {
						obj1.sendEmail(fromAddress, toAddress, ccAddresses, bccAddresses, contentType, subject, htmlBody, textBody);
					} catch (XmlRpcException e) {
					// TODO Auto-generated catch block
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
			          request.setAttribute("fName",pay_firstname);
			          request.setAttribute("lName",pay_lastname);
			          request.setAttribute("emailId",pay_e_mail);
			          request.setAttribute("pay_productPlan",pay_productPlan);
			          
			        //String fromAddress = "prabhu.pandi@digiblitz.in";
		                 String fromAddress = properties.getProperty("infusionMail.fromAddress");
		                 String toAddress = pay_e_mail;
		                 String ccAddresses = "";
		                 String bccAddresses = "";
		                 String contentType = "HTML/TEXT";
		                 String subject = "Your Username";
		                 String htmlBody = "Sorry! Your payment has failed";
		                 String textBody = "Sorry! Your payment has failed";
		           
		                 //Infusion Soft mail starts
		                                  
		        //obj1.sendEmail(properties.getProperty("infusionMail.fromAddress"), usrEmail, properties.getProperty("infusionMail.ccAddresses"), properties.getProperty("infusionMail.bccAddresses"), properties.getProperty("infusionMail.contentType"), properties.getProperty("infusionMail.subject"), properties.getProperty("infusionMail.htmlBody"), properties.getProperty("infusionMail.textBody"));
		             try {
						obj1.sendEmail(fromAddress, toAddress, ccAddresses, bccAddresses, contentType, subject, htmlBody, textBody);
					} catch (XmlRpcException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}
		                         
		                         System.out.println("Mail had been send successfully");                                 
		                //Infusion Soft mail ends
			          
		   	   			return new ModelAndView("signUpFail");

			       }
				 
					
				  int CcExpMonth = 0;
			        int CcExpYear = 0;
			        int CcCvvid =0;
			        double amount =0;
			        long CcNumber = 0;
			        long checkNumber=0;
			        String reqIp=request.getRemoteAddr();
				 //String paymenttype=request.getParameter("paytype");  
				  float total=0;
				
				   //String coupan_no=request.getParameter("coupan_no");
				   // System.out.println("user_id"+user_id);
				     
				    if(request.getParameter("expiry_month")==null){
			                CcExpMonth =0;
			            } else {
			                CcExpMonth=Integer.parseInt(request.getParameter("expiry_month"));
			            }
			            
			            if(request.getParameter("expiry_year")==null){
			                CcExpYear =0;
			            } else{
			                CcExpYear = Integer.parseInt(request.getParameter("expiry_year"));
			            }
			            
			            if(request.getParameter("cvv_no")==null && request.getParameter("cvv_no").trim().length()==0){
			                CcCvvid =0;
			            } else if(!request.getParameter("cvv_no").equals("") && request.getParameter("cvv_no").trim().length()!=0){
			                CcCvvid = Integer.parseInt(request.getParameter("cvv_no"));
			            } else {
			                CcCvvid =0;
			            }
			            
			            if(request.getParameter("credit_card_no")==null){
			                CcNumber =0;
			            } else{
			                CcNumber = Long.parseLong(request.getParameter("credit_card_no"));
			            }
			            
			            if(request.getParameter("productPrice")!=null && request.getParameter("productPrice").trim().length()!=0) {
			                amount = Double.valueOf(request.getParameter("productPrice")).doubleValue();
			            } else {
			                amount = 0.00;
			            }
			            
			            // Setting Parent member id in session after creating a member for payment rollback
			             int intVId = 0;
			            String inVoiceId1="1";
			            if (inVoiceId1.equalsIgnoreCase("0")) {
			                intVId = 1;
			            } else {
			                intVId = 1;
			            }
			             String expYear = request.getParameter("expiry_year");

			            String expDate = request.getParameter("expiry_month") + expYear;
			         
				          
				          String userid=db.getUserIdbyEmailPlan(pay_e_mail,pay_productPlan,pay_subscriptionType);
				          if(userid == null){
				        	try {
				        		regStatusId = db.insertsignup(pay_firstname,pay_lastname,pay_e_mail,pay_mobileno,pay_institutionName,pay_country,pay_state, pay_city, pay_credit_card_type, pay_credit_card_no, pay_cvv_no, pay_name_on_card, pay_expiry_date, pay_productPrice, pay_subscriptionType, payOption, pay_productPlan,coupon_valid,coupon_status,coupon_value,coupon_description,couponcode,pay_street_name,pay_zipcode,customerCategory);
				        		//status=db.insertsignup(firstname,lastname,e_mail,mobileno,institutionName,country,state, city, credit_card_type, credit_card_no, cvv_no, name_on_card, expiry_date, productPrice, pay_subscriptionType, payOption, pay_productPlan, coupon_valid,coupon_status,coupon_value,coupon_description,coupon_code);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				          }else{
				            regStatusId = db.updateusersignup(pay_firstname,pay_lastname,pay_e_mail,pay_mobileno,pay_institutionName,pay_country,pay_state, pay_city, pay_credit_card_type, pay_credit_card_no, pay_cvv_no, pay_name_on_card, pay_expiry_date, pay_productPrice, payOption, pay_subscriptionType, pay_productPlan,coupon_valid,coupon_status,coupon_value,coupon_description,couponcode,street_name,zipcode,customerCategory);
							//regStatusId = db.insertsignup(pay_firstname,pay_lastname,pay_e_mail,pay_mobileno,pay_institutionName,pay_country,pay_state, pay_city, pay_credit_card_type, pay_credit_card_no, pay_cvv_no, pay_name_on_card, pay_expiry_date, pay_productPrice, pay_subscriptionType, payOption, pay_productPlan,coupon_valid,coupon_status,coupon_value,coupon_description,coupon_code,pay_street_name,pay_zipcode);
							//status=db.insertsignup(firstname,lastname,e_mail,mobileno,institutionName,country,state, city, credit_card_type, credit_card_no, cvv_no, name_on_card, expiry_date, productPrice, pay_subscriptionType, payOption, pay_productPlan, coupon_valid,coupon_status,coupon_value,coupon_description,coupon_code);
				          }
			               request.setAttribute("intVId", String.valueOf(intVId));
			            System.out.println("intVId in servlet&&&&&&&" + intVId);
			            request.setAttribute("purpose", "memberregistration");
			            String paypal_ENVIRON = null;
			            paypal_ENVIRON = properties.getProperty("paypal.environ");
			            
			            System.out.println("regStatusId:::::::in GldSignUpAction"+regStatusId);
						 if(paypal_ENVIRON != null && regStatusId !=null){
							 try{
							 request.setAttribute("paypal_ENVIRON", paypal_ENVIRON);
							 request.setAttribute("CUSTOMER_REGISTERID", regStatusId);
							 System.out.println("paypal_ENVIRON==============inside action  ============"+paypal_ENVIRON);
							 
							  // session.setAttribute("parentId",parentId);
				              session.setAttribute("sessionInvoiceId","1");
				            request.setAttribute("AMT", request.getParameter("productPrice"));
				            request.setAttribute("PAYMENTACTION", "Authorization");
				            request.setAttribute("CREDITCARDTYPE", request.getParameter("credit_card_type"));
				            request.setAttribute("ACCT", request.getParameter("credit_card_no"));
				            request.setAttribute("EXPDATE", expDate);
				            request.setAttribute("IPADDRESS", reqIp);
				            request.setAttribute("FIRSTNAME", request.getParameter("firstname"));
				            request.setAttribute("LASTNAME", request.getParameter("lastname"));
				            request.setAttribute("CVVNo", request.getParameter("cvv_no"));
				            request.setAttribute("STREET", request.getParameter("street"));
				            request.setAttribute("CITY", request.getParameter("city"));
				            request.setAttribute("STATE", request.getParameter("state"));
				            request.setAttribute("ZIP", request.getParameter("zipcode"));
				            request.setAttribute("COUNTRYCODE", request.getParameter("country"));
				            request.setAttribute("EMAIL", request.getParameter("email"));
				            request.setAttribute("INSTITUTIONNAME", institutionName);
				            String instituteId = db.selectBuyInstitutionId(request.getParameter("email"), regStatusId);
				            request.setAttribute("INSTITUTIONID", instituteId);
				            //session.setAttribute("objPayment", objPayment);//no need
				           // session.setAttribute("URLPATH", "MembershipReg.do?process=reg");
				            
				            
				            request.setAttribute("pay_firstname", pay_firstname);
					          request.setAttribute("pay_lastname", pay_lastname);
					          request.setAttribute("pay_e_mail", pay_e_mail);
					          request.setAttribute("pay_mobileno", pay_mobileno);
					          request.setAttribute("pay_institutionName", pay_institutionName);
					          request.setAttribute("pay_country", pay_country);
					          request.setAttribute("pay_state", pay_state);
					          request.setAttribute("pay_city", pay_city);
					          request.setAttribute("pay_productPrice", pay_productPrice);
					          request.setAttribute("pay_credit_card_type", pay_credit_card_type);
					          request.setAttribute("pay_credit_card_no", pay_credit_card_no);
					          request.setAttribute("pay_cvv_no", pay_cvv_no);
					          request.setAttribute("pay_name_on_card", pay_name_on_card);
					          request.setAttribute("pay_expiry_month", pay_expiry_month);
					          request.setAttribute("pay_expiry_year", pay_expiry_year);
					          request.setAttribute("pay_expiry_date", pay_expiry_date);
					          request.setAttribute("pay_subscriptionType", pay_subscriptionType);
					          request.setAttribute("pay_purchase_type", payOption);
					          request.setAttribute("pay_productPlan",pay_productPlan);
					          request.setAttribute("pay_street_name",pay_street_name);
					          request.setAttribute("pay_zipcode",pay_zipcode);
					          
					          
					          System.out.println("pay_street_name::::::::::::::::::"+pay_street_name);
					          System.out.println("pay_zipcode::::::::::::::::::"+pay_zipcode);
					          System.out.println("pay_firstname::::::::::::::::::"+pay_firstname);
					          System.out.println("pay_lastname::::::::::::::::::"+pay_lastname);
					          System.out.println("pay_e_mail::::::::::::::::::"+pay_e_mail);
					          System.out.println("pay_mobileno::::::::::::::::::"+pay_mobileno);
					          System.out.println("pay_institutionName::::::::::::::::::"+pay_institutionName);
					          System.out.println("pay_country::::::::::::::::::"+pay_country);
					          System.out.println("pay_state::::::::::::::::::"+pay_state);
					          System.out.println("pay_city::::::::::::::::::"+pay_city);
					          System.out.println("pay_productPrice::::::::::::::::::"+pay_productPrice);
					          System.out.println("pay_credit_card_type::::::::::::::::::"+pay_credit_card_type);
					          System.out.println("pay_credit_card_no::::::::::::::::::"+pay_credit_card_no);
					          System.out.println("pay_cvv_no::::::::::::::::::"+pay_cvv_no);
					          System.out.println("pay_name_on_card::::::::::::::::::"+pay_name_on_card);
					          System.out.println("pay_expiry_month::::::::::::::::::"+pay_expiry_month);
					          System.out.println("pay_expiry_year::::::::::::::::::"+pay_expiry_year);
					          System.out.println("pay_expiry_date::::::::::::::::::"+pay_expiry_date);
					          System.out.println("pay_subscriptionType::::::::::::::::::"+pay_subscriptionType);
					          System.out.println("pay_purchase_type::::::::::::::::::"+payOption);
					          System.out.println("pay_productPlan::::::::::::::::::"+pay_productPlan);
					          
					          
							 return new ModelAndView("testpaypal");	
							 }catch(Exception e){
								 e.printStackTrace();
								 return new ModelAndView("signUpFail");
							 }
						 }else{
							 return new ModelAndView("signUpFail");
						 }
			            
			            			 
			 }
         }
		 
		 if(signUpProcess.equalsIgnoreCase("afterSignup"))
         {  
			 String afterFname = (String)session.getAttribute("aftFName");
			 String aftLastname = (String)session.getAttribute("aftLastname");
			 String aftE_mail = (String)session.getAttribute("aftE_mail");
			 String aftInstitutionName = (String)session.getAttribute("aftInstitutionName");
			 String aftInstituteId = (String)session.getAttribute("aftInstituteId");
			 String aftRegistrationId = (String)session.getAttribute("aftRegistrationId");
			 String aftCustomerUserName = (String)session.getAttribute("aftCustomerUserName");
			 System.out.println("afterFname::::::::::::::::::::::::::"+afterFname);
			 System.out.println("aftLastname::::::::::::::::::::::::::"+aftLastname);
			 System.out.println("aftE_mail::::::::::::::::::::::::::"+aftE_mail);
			 System.out.println("aftInstitutionName::::::::::::::::::::::::::"+aftInstitutionName);
			 System.out.println("aftInstituteId::::::::::::::::::::::::::"+aftInstituteId);
			 System.out.println("aftRegistrationId::::::::::::::::::::::::::"+aftRegistrationId);
			 System.out.println("aftCustomerUserName::::::::::::::::::::::::::"+aftCustomerUserName);
			 
			 try
		      {
		         //URL url = new URL("http://74.208.110.192:8480/yes-shop/registration");
				 URL url = new URL(properties.getProperty("config.dbstore_reg_url"));
		         URLConnection urlConnection = url.openConnection();
		         HttpURLConnection connection = null;
		         if(urlConnection instanceof HttpURLConnection)
		         {
		            connection = (HttpURLConnection) urlConnection;
		         }
		         else
		         {
		            System.out.println("Please enter an HTTP URL.");
		            return null;
		         }
		         BufferedReader in = new BufferedReader(
		         new InputStreamReader(connection.getInputStream()));
		         String urlString = "";
		         String current;
		         while((current = in.readLine()) != null)
		         {
		            urlString += current;
		         }
		         String splitableStr = "./registration;jsessionid=";
		         String title= StringUtils.substringBetween(urlString, splitableStr, "?");
		         System.out.println(title);
		         request.setAttribute("y_shopJSessionId", title);
//		         String [] splitOut = null;
//		         splitOut = urlString;
//		         for(int i=0;i<splitOut.length;i++){
//		         System.out.println(splitOut[i]);
//		         }
		      }catch(IOException e)
		      {
		         e.printStackTrace();
		      }
			 return new ModelAndView("testAfterSignUp"); 
			 
         }
		 
		 else if(signUpProcess!=null && signUpProcess.equalsIgnoreCase("payPalPreAction")){}
          
         else if(signUpProcess!=null && signUpProcess.equalsIgnoreCase("getcompanydetails")){
      	    String user_id=request.getParameter("user_id");
      	  
      	    String city=null;
      	    String state=null;
      	    String country=null;
      	   
      	    String phone=null;
      	   
      	  
      	    String urllink=null;
      	    String suges=null;
      	    String agree=null;
      	    String date=null;
      	    String month=null;
      	    String year=null;
      	    
      	     try {
      	     
      	   
      	     }catch(Exception e){
      	      e.printStackTrace();
      	     }
      	    
      	        return new ModelAndView("optionindex"); 
      	       }









		
		  return new ModelAndView("frmHome");
	}

}

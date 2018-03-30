/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paypal.action;



import com.db.GeneralDBAction;
import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlccommon.util.HLCPaymentDetailVO;
import com.hlccommon.util.HLCPaymentResultVO;
import com.hlcform.stateless.HLCkaverystatelessBean;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcmrm.util.Debug;
import com.hlcpayment.HLCPaymentSessionRemote;
import com.hlcpayment.HLCPaymentSessionRemoteHome;
import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.core.nvp.NVPEncoder;
import com.paypal.action.PayPalPayments;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import com.util.email.MailMail;
import com.hlcform.util.HLCPaymentDetails;
import com.infusionejb.session.InfusionSessionBean;
import com.login.action.KlgLoginAction;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.struts.util.MessageResources;
import org.apache.xmlrpc.XmlRpcException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.xml.sax.SAXException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author dhivya
 */


    /* forward name="success" path="" */
   // private final static String SUCCESS = "success";
   // NVPEncoder encoder = new NVPEncoder();
    //NVPDecoder decoder = new NVPDecoder();

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public class PayPalAction implements Controller {
    	
    	static Logger log = Logger.getLogger(PayPalAction.class.getName());
    	
    	public static final String LOG_FILE = "PayPalLog4j.properties";
    	InputStream is;
    	
    	private InfusionSessionBean obj1;
    	{
    		try{
    			obj1 = new InfusionSessionBean();
    		}catch(Exception e){
    			 throw new RuntimeException(e);
    		}
    	}
    	
    	private final static String SUCCESS = "success"; 
    	Properties properties; 
    	PayPalPayments payPalPayments = new PayPalPayments();
    	public ModelAndView handleRequest(HttpServletRequest request,
    			HttpServletResponse response) throws ServletException, IOException,ProtocolException,
    			IOException, ParserConfigurationException, SAXException {
    		
        HttpSession session = request.getSession();
        System.out.println("inside paypal action");
       
       
        // ======================log file properties configuration started====================
        Properties logProp = new Properties();  
	    is = (InputStream) getClass().getClassLoader().getResourceAsStream(LOG_FILE);
	    logProp.load(is); 
	    PropertyConfigurator.configure(logProp);      
	    log.info("Logging enabled");  
	    // ======================log file properties configuration ended====================
       
       /** Creates a new instance of EmailEngine */
       properties = new Properties();        
       InputStream in = this.getClass().getClassLoader().getResourceAsStream("/paypal.properties");
       try {               
       	properties.load(PayPalAction.class.getResourceAsStream("/paypal.properties"));
       //	properties.load(getClass().getClassLoader().getResourceAsStream("/infusionMail.properties"));
       	
       	properties.load(getClass().getClassLoader().getResourceAsStream("/mail.properties"));
       	
       	System.out.println("Property file loaded successfully");
           } catch (Exception e) {
               try {
                 properties.load(in);
               }catch(Exception ee) {
                  Debug.print("Could not load the companyDetails.properties");
               }
           }
       
       
       
        String[] payDet = new String[7];
        ArrayList finalPayResults = new ArrayList();
        // PaymentDetails objPayment = new PaymentDetails();
        //String fwd="success";
        String pay_firstname = request.getParameter("pay_firstname");
        System.out.println("pay_firstname in paypall action::::::::::::::::::::::::::::::::::::::::"+pay_firstname);
          String pay_lastname= request.getParameter("pay_lastname");
          String pay_e_mail=  request.getParameter("pay_e_mail");
          String pay_mobileno=  request.getParameter("pay_mobileno");
          String pay_institutionName=  request.getParameter("pay_institutionName");
          String pay_country=  request.getParameter("pay_country");
          String pay_state=  request.getParameter("pay_state");
          String pay_city=  request.getParameter("pay_city");
          String pay_productPrice =   request.getParameter("pay_productPrice");
          String pay_credit_card_type=  request.getParameter("pay_credit_card_type");
          String pay_credit_card_no=  request.getParameter("pay_credit_card_no");
          String pay_cvv_no=  request.getParameter("pay_cvv_no");
          String pay_name_on_card=  request.getParameter("pay_name_on_card");
          String pay_expiry_month=  request.getParameter("pay_expiry_month");
          String pay_expiry_year=  request.getParameter("pay_expiry_year");
          String pay_expiry_date = pay_expiry_month+"/"+pay_expiry_year;
          String pay_subscriptionType=  request.getParameter("pay_subscriptionType");
          String pay_productPlan=  request.getParameter("pay_productPlan");
          
          
          String purpose =  (String) request.getParameter("purpose");
//        String API_username ="prabhu.pandi-facilitator_api1.digiblitz.in";
//        String API_password ="33M5P9H3UMNF49Q6";
//        String API_Signature ="AiPC9BjkCyDFQXbSkoZcgqH3hpacAqRcYithSl8PM-MpvR0vtNpqDXT-";
        String API_username=properties.getProperty("paypal.apiusername");
        String API_password=properties.getProperty("paypal.apipassword");
        String API_Signature=properties.getProperty("paypal.apisignature");
        
        
        String payAction = request.getParameter("PAYMENTACTION");
        String ccType = request.getParameter("CREDITCARDTYPE");
        String acct = request.getParameter("ACCT");
        String expDt = request.getParameter("EXPDATE");
        String ipAdd = request.getParameter("IPADDRESS");
        String fName = request.getParameter("FIRSTNAME");
        String cvvNo = request.getParameter("CVVNO");
        String street = request.getParameter("STREET");
        String city = request.getParameter("CITY");
        String state = request.getParameter("STATE");
        String zip = request.getParameter("ZIP");
        String amount = request.getParameter("AMT");
        String ccode = request.getParameter("COUNTRYCODE");
        String method = request.getParameter("METHOD");
        String version = request.getParameter("VERSION");
        String Environ = request.getParameter("ENVIRON");
        String emailId = request.getParameter("EMAIL");
        String currCode = request.getParameter("CURRENCYCODE");
        
        String lName = request.getParameter("LASTNAME");
        String instituteName = request.getParameter("INSTITUTENAME");
        String instituteID = request.getParameter("INSTITUTEID");
        String Customer_Reg_Id = request.getParameter("CUSTOMER_REGISTERID");

        try {
        	System.out.println("Inside PayPalAction Environ-------------->"+Environ);
        	System.out.println("Inside PayPalAction STREET-------------->"+street);
        	System.out.println("Inside PayPalAction CITY-------------->"+city);
        	System.out.println("Inside PayPalAction STATE-------------->"+state);
        	System.out.println("Inside PayPalAction ZIP-------------->"+zip);
        	System.out.println("Inside PayPalAction CCODE-------------->"+ccode);
        	System.out.println("Inside PayPalAction LASTNAME-------------->"+lName);
        	System.out.println("Inside PayPalAction Customer_Reg_Id-------------->"+Customer_Reg_Id);
        	System.out.println("Inside PayPalAction instituteID-------------->"+instituteID);
        	System.out.println("Inside PayPalAction instituteName-------------->"+instituteName);
            
            System.out.println("paypal_firstname::::::::::::::::::"+pay_firstname);
            System.out.println("paypal_lastname::::::::::::::::::"+pay_lastname);
            System.out.println("paypal_e_mail::::::::::::::::::"+pay_e_mail);
            System.out.println("paypal_mobileno::::::::::::::::::"+pay_mobileno);
            System.out.println("paypal_institutionName::::::::::::::::::"+pay_institutionName);
            System.out.println("paypal_country::::::::::::::::::"+pay_country);
            System.out.println("paypal_state::::::::::::::::::"+pay_state);
            System.out.println("paypal_city::::::::::::::::::"+pay_city);
            System.out.println("paypal_productPrice::::::::::::::::::"+pay_productPrice);
            System.out.println("paypal_credit_card_type::::::::::::::::::"+pay_credit_card_type);
            System.out.println("paypal_credit_card_no::::::::::::::::::"+pay_credit_card_no);
            System.out.println("paypal_cvv_no::::::::::::::::::"+pay_cvv_no);
            System.out.println("paypal_name_on_card::::::::::::::::::"+pay_name_on_card);
            System.out.println("paypal_expiry_month::::::::::::::::::"+pay_expiry_month);
            System.out.println("paypal_expiry_year::::::::::::::::::"+pay_expiry_year);
            System.out.println("paypal_expiry_date::::::::::::::::::"+pay_expiry_date);
            System.out.println("pay_subscriptionType::::::::::::::::::"+pay_subscriptionType);
            System.out.println("pay_productPlan::::::::::::::::::"+pay_productPlan);
            
            boolean updatePaymentStatus= false;
            
            // smtp email format
            
            boolean emailFlagTesting = false;
    		
    		String htmlbodytocandidate = null;
          /*=========================Create Contact in infusion soft=================================*/
          /*  boolean email_optinStatus = false;
        	obj1.createContactInfusion(pay_firstname, pay_lastname, pay_e_mail);
        	email_optinStatus = obj1.optin_outEmail(pay_e_mail);
        	System.out.println("Email optin status in infusionsoft---------------->"+email_optinStatus); */
            
        	/*=========================Create Contact in infusion soft=================================*/
        	
        	GeneralDBAction db = new GeneralDBAction();
        	HLCkaverystatelessBean humanMemb=new HLCkaverystatelessBean();
            HLCPaymentResultVO payRes = new HLCPaymentResultVO();
            String forwardPage = null;

            String sessionInvoiceId = (String) session.getAttribute("sessionInvoiceId");
            String inVoiceId1 = request.getParameter("INVId");
            String INVId = (String) session.getAttribute("INVId");
            System.out.println("SESSION INVId in servlet:::" + INVId);
            String inVId = null;
            if (INVId == null || INVId.equalsIgnoreCase("null")) {
                inVId = "start";
            } else {
                inVId = INVId;
            }
            session.setAttribute("inVoiceId1", inVoiceId1);

            /*session.setAttribute("API_username", API_username);
            session.setAttribute("API_password", API_password);
            session.setAttribute("API_Signature", API_Signature);*/
            session.setAttribute("currCode", currCode);
            session.setAttribute("amount", amount);
            session.setAttribute("purpose", purpose); 

            /* String API_username="dhivya_1237881559_biz_api1.digiblitz.com";
            String API_password="1237881603";
            String API_Signature="AZz-.wpdCAiKvEaTAQDfbvLNOC1RA9dqJQtBGucKZ9D.wxwoO6D7nQy2";
            String method="DoDirectPayment";
            String amount="10.00";
            String version="51.0";
            String Environ="sandbox";
            String payAction="Sale";
            String ccType="Visa";
            String acct="4169580619448012";
            String expDt="032019";
            String ipAdd="192.168.1.5"; 
            String fName="dhivya";
            String lName="AB";
            String street="1 Main St";
            String city="San Jose";
            String state="CA";  
            String zip="95131";
            String ccode="US";*/

//System.out.println("API_username:" + API_username);
//System.out.println("API_password:" + API_password);
//System.out.println("API_Signature:" + API_Signature);
            
            System.out.println("amount:" + amount);

            String results[] = null;
          
            if (sessionInvoiceId.equalsIgnoreCase("1")) {
                results = payPalPayments.DoDirectPaymentCode(API_username, API_password, API_Signature, method, amount, version, Environ, payAction, ccType, acct, expDt, cvvNo, fName, ipAdd, emailId, inVId, inVoiceId1,street,city,state,zip,ccode,lName);
            }
            System.out.println("results:" + results);
            session.setAttribute("results", results);
            String statusId3=null;
            if (results != null) {

                statusId3 = (String) results[0];
                String transactionId3 = (String) results[2];
                String compType3 = "Complete";
                String inVoiceId3 = (String) results[6];
                String ppAmt3 = (String) results[3];
                String sslAvsResponse = (String) results[4];
                String sslCvv2Response = (String) results[5];
                String lngMsg = (String) results[7];
                String errorCd = (String) results[8];
                String tmStmp = (String) results[9];
                System.out.println("TimeStramp from Paypal Server:::::::::::::::::::::::::::::::"+tmStmp);
                String Environ1 = (String) results[10];
                String sslResultMessage = (String) results[0];
                String finalSslAvsResponse = "";
                String finalSslCvv2Response = "";
               
                if(statusId3.equalsIgnoreCase("Failure")|| transactionId3 == null || transactionId3.equalsIgnoreCase("null")){
                	request.setAttribute("cardstatus","success");
			        request.setAttribute("cvvstatus","success");
			        request.setAttribute("fName",pay_firstname);
		            request.setAttribute("lName",pay_lastname);
		            request.setAttribute("emailId",pay_e_mail);
		            request.setAttribute("pay_productPlan",pay_productPlan);
		            request.setAttribute("transStatus","fail");
		            request.setAttribute("failure_reason",lngMsg);
		        
		            String paymentStatus = "pending";
		            String productStatus = "deactive";
		        
		            updatePaymentStatus = db.updatePaymentStatus(transactionId3, emailId, Customer_Reg_Id, paymentStatus, productStatus);
		            if(updatePaymentStatus == true){
				        log.info("Dear Customer:"+pay_firstname+" "+pay_lastname+"\n");  
				        log.info("The reason is :"+lngMsg);
				        log.info("Customer email :"+pay_e_mail);  
				        log.info("Timestamp :"+tmStmp);
				        log.info("Payment Status :"+paymentStatus);
				        log.info("Payment status updated on database---------------->");
		            }else{
			        	log.info("Dear Customer:"+pay_firstname+" "+pay_lastname+"\n");  
				        log.info("The reason is :"+lngMsg);
				        log.info("Customer email :"+pay_e_mail);  
				        log.info("Timestamp :"+tmStmp);
				        log.info("Payment Status :"+paymentStatus);
				        log.info("Payment status was not updated on database---------------->");
		            }
		        
		      // infusion mailing      
		            
				      /*  String fromAddress = properties.getProperty("infusionMail.fromAddress");
		                String toAddress = pay_e_mail;
		               String ccAddresses = properties.getProperty("infusionMail.ccPaymentAddress");
		               String bccAddresses = properties.getProperty("infusionMail.bccPaymentAddress");
		                String contentType = "HTML";
		                String Failsubject = "Payment Declined!"; */
		            
		            // smtp mail sending
		              
		            try{
		            
		            EmailContent sendemail=new EmailContent();
		   	     String emailid = pay_e_mail;
		   	     String toMailIds[] = {emailid};
		   	  System.out.println(pay_e_mail+"=============================");
		   	     sendemail.setTo(toMailIds);
		   	     sendemail.setFrom("contact@menschforce.io");
		   	     sendemail.setSubject("Payment Declined!");
		            
		            
		                String htmlBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
                        "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
                        "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
                        " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
                        "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Dear "+pay_firstname+" "+pay_lastname+"</h4><p style=\"   font-size: 19px;" +
                          "line-height: 27px; margin-bottom: 16px;margin-top: 16px; text-align: center;\">Your payment has been declined..</p><table style=\" clear: both; margin: 0 auto;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
                          "<tbody> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><a style=\"font-weight: 700; color:#fff;\" href=\"https://www.digiblitzonline.com:8843/menschforce/signUp.html?signUpProcess=getStarted\" target=\"_blank\"><span style=\" display: block;text-align: center; color: #fff;\">Please Click here to try again</span></a></td>" +
                          "</tr> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Payment Failure Details</h4></td>" +
                          "</tr> </tbody> </table> <p>&nbsp; </p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td align=\"center\" valign=\"top\" style=\"background-color: #fff;  padding: 16px; text-align: center; vertical-align: top;\">" +
                          "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody> <tr> <td style=\"color: #888;\" align=\"center\" valign=\"top\" style=\" padding: 16px; text-align: center; vertical-align: top;\">" +
                          " <p style=\" line-height: 23px; margin-bottom: 24px; margin-top: 16px;font-size: 15px;\">" +
							"<strong>E-mail ID</strong><br /> "+pay_e_mail+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
                          "<strong>Error Code :</strong><br />"+errorCd+"</p> <p style=\" line-height: 23px; margin-bottom: 24px;margin-top: 16px;font-size: 15px;\">" +
							"<strong>Reason :</strong><br />"+lngMsg+"</p> <p style=\" line-height: 23px; margin-bottom: 24px;margin-top: 16px;font-size: 15px;\">" +
                          "<strong>Timestamp : </strong><br />"+tmStmp+"</p> <p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\">" +
                          "</td> </tr> </tbody></table></td></tr></tbody></table></div></div></div></div></body></html>";
                		
		                String textBody = "Dear "+fName+" "+lName+" your payment has been declined \n" +
                		"Error Code : "+errorCd+"\n"+
                		"Reason : "+lngMsg+"\n"+
                		"Timestamp : "+tmStmp;
          
		                //Infusion Soft mail starts
                                 
		                //obj1.sendEmail(properties.getProperty("infusionMail.fromAddress"), usrEmail, properties.getProperty("infusionMail.ccAddresses"), properties.getProperty("infusionMail.bccAddresses"), properties.getProperty("infusionMail.contentType"), properties.getProperty("infusionMail.subject"), properties.getProperty("infusionMail.htmlBody"), properties.getProperty("infusionMail.textBody"));
		              
		                /*
		                 
		                try {
		                	obj1.sendEmail(fromAddress, toAddress, ccAddresses, bccAddresses, contentType, Failsubject, htmlBody, textBody);
		                } catch (XmlRpcException e) {
		                	e.printStackTrace();
		                }
		                
		                
		                */
		                
		                sendemail.setBody(htmlBody);
		                
		                
		                EmailEngine emailEngine = new EmailEngine();
		       	     emailFlagTesting = emailEngine.sendMimeEmail(sendemail);
		       	     System.out.println("Email sent sucessfully Testing :" + emailFlagTesting);
		       		
		       		}catch(Exception e){
		       			e.printStackTrace();
		       		}
		                
		                return new ModelAndView("signUpFail");
                }

                if (sslAvsResponse == null || sslAvsResponse.equalsIgnoreCase("")) {
                    finalSslAvsResponse = "NG";
                } else {
                    finalSslAvsResponse = sslAvsResponse;
                }
                if (sslCvv2Response == null || sslCvv2Response.equalsIgnoreCase("")) {
                    finalSslCvv2Response = "NG";
                } else {
                    finalSslCvv2Response = sslCvv2Response;
                }

                session.setAttribute("sessionInvoiceId", inVoiceId3);

                payRes.setAmount(amount);
                payRes.setErrorCd(errorCd);
                payRes.setLngMsg(lngMsg);
                payRes.setSslAvsResponse(finalSslAvsResponse);
                payRes.setSslCvv2Response(finalSslCvv2Response);
                payRes.setTmStmp(tmStmp);

                session.setAttribute("payRes", payRes);

                HLCPaymentDetails objPayment = null;
                if (session.getAttribute("objPayment") != null && session.getAttribute("objPayment") instanceof HLCPaymentDetails) {
                    objPayment = (HLCPaymentDetails) session.getAttribute("objPayment");
                }
 
                Debug.print("objPayment"+objPayment);                
                /*if(objPayment.getPaymentId()!=null){
                String payId=objPayment.getPaymentId();
                session.setAttribute("payId", payId);
                }*/
                
                HLCPaymentDetailVO objPayDet = new HLCPaymentDetailVO();

                //System.out.println("ppAmt in DoDirectPaymentCode#####" + ppAmt3);
                //System.out.println("statusId3 in servlet#####" + statusId3);
                //System.out.println("Transaction Id ----:" + transactionId3);

                String currCode1 = (String) session.getAttribute("currCode");
                String amount1 = (String) session.getAttribute("amount");
                System.out.println("amount1 in paypal: " + amount1);
                //System.out.println("currCode in paypal:........... " + currCode1);
                String method1 = "DoCapture";
                String version1 = "51.0";
               // String Environ1 = "sandbox";
                String payAction1 = "Authorization";
                String method2 = "DoAuthorization";
                //Success
                if (statusId3.equalsIgnoreCase("Success")) {
                    //String authorRes[] = payPalPayments.DoAuthorizationCode(API_username, API_password, API_Signature, method2, amount1, version1, Environ1, transactionId3, currCode1);
                    if (results != null) {
                    	// String statusId2 = (String) authorRes[0];
                        //  String authorId = (String) authorRes[1];
                        //  String payAmt = (String) authorRes[2];
                        //System.out.println("authorId in doAuthorization" + authorId + "satatus Id:" + statusId2 + " Payment amount" + payAmt);
                        String results1[] = payPalPayments.doCapture(API_username, API_password, API_Signature, method1, amount1, version1, Environ1, payAction1, transactionId3, compType3, inVoiceId3);
                        String statusId1 = (String) results1[0];
                        String ppAuthorizationID = null;
                        if (results1[1] != null) {
                            ppAuthorizationID = (String) results1[1];
                        } else {
                            String correctAction = humanMemb.getErrorDets(errorCd);
                            String finalCorecAction = "";
                            if (correctAction != null && !(correctAction.equalsIgnoreCase(""))) {
                                finalCorecAction = correctAction;
                            } else {
                                finalCorecAction = "NG";
                            }
                            //System.out.println("Inside Paypal ACtion "+finalSslAvsResponse+"  "+finalSslCvv2Response);
                           // String emailToUSEA=mr.getMessage("hlc.emailid");
                            String emailToUSEA="jeyaprakash.sankarraj@digiblitz.com";
                            String toMailIds[] = {emailToUSEA, emailId};
                            EmailContent email = new EmailContent();
                            email.setTo(toMailIds);
                            email.setFrom("contact@menschforce.io");// info@digiblitz.com
                            String subject=null;
                            if (purpose.trim().equalsIgnoreCase("newhorseregistration")) {
                            	subject="Horse Registration Decline";
                            } else if (purpose.trim().equalsIgnoreCase("horseriderownerchange")) {
                            	subject="Change Rider Owner Decline";
                            } else if (purpose.trim().equalsIgnoreCase("addriderowner")) {
                            	subject="Add Rider Owner Decline";
                            } else if (purpose.trim().equalsIgnoreCase("notregisteredhorse")) {
                            	subject="Not Registered Horse Decline";
                            } else if (purpose.trim().equalsIgnoreCase("membershiprenewal")) {
                            	subject="Membership Renewal  Decline";
                            } else if (purpose.trim().equalsIgnoreCase("memberregistration")) {
                            	subject="Membership Registration  Decline";
                            } else if (purpose.trim().equalsIgnoreCase("educationregistration")) {
                            	subject="Education Activity Registration Decline";
                            } else if (purpose.trim().equalsIgnoreCase("upgradehorse")) {
                            	subject="Upgrade Horse Decline";
                            } else if (purpose.trim().equalsIgnoreCase("areaMemb")) {
                            	subject="Area Membership Decline";
                            } else if (purpose.trim().equalsIgnoreCase("endorsedForm")) {
                            	subject="Event Registration Decline";
                            } else if (purpose.trim().equalsIgnoreCase("annualmeetingreg")) {
                            	subject="Annual Meeting Registration Decline";
                            } else if(purpose.trim().equalsIgnoreCase("newDonation")){
                               subject="Donation Decline";
                            } else if(purpose.trim().equalsIgnoreCase("purchasingTickets")){
                               subject="Purchasing Tickets for Area Reception Decline";
                            } else if(purpose.trim().equalsIgnoreCase("endorsedRetryForm")){
                              
                            }
                            System.out.println("====================Sucessmail testing===================");
                            email.setSubject(subject);
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
                                    "<p>Payment has been declined and the details are as follows:<p>" +
                                    "<span class=\"boldTxt\">Declined Details:</span>.</p>" +
                                    "TIMESTAMP:" + tmStmp + " <br/>" +
                                    "L_ERRORCODE0:" + errorCd + "<br/>" +
                                    "L_LONGMESSAGE0:" + lngMsg + "<br/>" +
                                    "AMT:" + amount + "<br/>" +
                                    "AVSCODE: " + finalSslAvsResponse + "<br/>" +
                                    "CVV2MATCH:" + finalSslCvv2Response + "<br/>" +
                                    "Corrective action:" + finalCorecAction + "<br/></p>" +
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
                            EmailEngine mail = new EmailEngine();
                            boolean emailFlag = mail.sendMimeEmail(email);
                            Debug.print("Email sent sucessfully :" + emailFlag);
                            request.setAttribute("msg", "Payment Declined");
                            request.setAttribute("errorCd", errorCd);
                            request.setAttribute("lngMsg", lngMsg);
                            /*session.removeAttribute("API_username1");
                            session.removeAttribute("API_password1");
                            session.removeAttribute("API_Signature1");*/
                            session.removeAttribute("currCode1");
                            session.removeAttribute("amount1");
                            //return new ModelAndView("payDecline");
                            
                            String paymentStatus = "pending";
            		        String productStatus = "deactive";
            		        
            		        
            		        updatePaymentStatus = db.updatePaymentStatus(transactionId3, emailId, Customer_Reg_Id, paymentStatus, productStatus);
            		        if(updatePaymentStatus == true){
            		        	
            		        log.info("Dear Customer:"+pay_firstname+" "+pay_lastname+"\n");  
            		        log.info("The reason is :"+lngMsg);
            		        log.info("Customer email :"+pay_e_mail);  
            		        log.info("Timestamp :"+tmStmp);
            		        log.info("Payment Status :"+paymentStatus);
            		        log.info("Payment status updated on database---------------->");

            		        }else{
            		        	
            		        	log.info("Dear Customer:"+pay_firstname+" "+pay_lastname+"\n");  
            			        log.info("The reason is :"+lngMsg);
            			        log.info("Customer email :"+pay_e_mail);  
            			        log.info("Timestamp :"+tmStmp);
            			        log.info("Payment Status :"+paymentStatus);
            			        log.info("Payment status was not updated on database---------------->");
            		        }
            		        
                            
                       /*     String fromAddress = properties.getProperty("infusionMail.fromAddress");
                            String toAddress = pay_e_mail;
                            String ccAddresses = properties.getProperty("infusionMail.ccPaymentAddress");
                            String bccAddresses = properties.getProperty("infusionMail.bccPaymentAddress");
                            String contentType = "HTML";
                            String Failsubject = "Payment Declined";
                            */
                            
            		        String emailId1=pay_e_mail;
                            String toMailIds1[] = { emailId1};
                            EmailContent email1 = new EmailContent();
                            email1.setTo(toMailIds1);
                            email1.setFrom("contact@menschforce.in");
                            email1.setSubject("Payment Declined");
                            System.out.println("====================Sucessmail testing001===================");
                            String htmlBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
                                    "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
                                    "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
                                    " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
                                    "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Dear "+pay_firstname+" "+pay_lastname+"</h4><p style=\"   font-size: 19px;" +
                                      "line-height: 27px; margin-bottom: 16px;margin-top: 16px; text-align: center;\">Your payment has been declined..</p><table style=\" clear: both; margin: 0 auto;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
                                      "<tbody> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><a style=\"font-weight: 700; color:#fff;\" href=\"https://www.digiblitzonline.com:8843/menschforce/signUp.html?signUpProcess=getStarted\" target=\"_blank\"><span style=\" display: block;text-align: center; color: #fff;\">Please Click here to try again</span></a></td>" +
                                      "</tr> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Payment Failure Details</h4></td>" +
                                      "</tr> </tbody> </table> <p>&nbsp; </p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td align=\"center\" valign=\"top\" style=\"background-color: #fff;  padding: 16px; text-align: center; vertical-align: top;\">" +
                                      "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody> <tr> <td style=\"color: #888;\" align=\"center\" valign=\"top\" style=\" padding: 16px; text-align: center; vertical-align: top;\">" +
                                      " <p style=\" line-height: 23px; margin-bottom: 24px; margin-top: 16px;font-size: 15px;\">" +
            							"<strong>E-mail ID</strong><br /> "+pay_e_mail+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
                                      "<strong>Error Code :</strong><br />"+errorCd+"</p> <p style=\" line-height: 23px; margin-bottom: 24px;margin-top: 16px;font-size: 15px;\">" +
            							"<strong>Reason :</strong><br />"+lngMsg+"</p> <p style=\" line-height: 23px; margin-bottom: 24px;margin-top: 16px;font-size: 15px;\">" +
                                      "<strong>Timestamp : </strong><br />"+tmStmp+"</p> <p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\">" +
                                      "</td> </tr> </tbody></table></td></tr></tbody></table></div></div></div></div></body></html>";
                            		
                            String textBody = "Dear "+fName+" "+lName+" your payment has been declined \n" +
                            		"Error Code : "+errorCd+"\n"+
                            		"Reason : "+lngMsg+"\n"+
                            		"Timestamp : "+tmStmp;
                      
                            //Infusion Soft mail starts
                                             
                            //obj1.sendEmail(properties.getProperty("infusionMail.fromAddress"), usrEmail, properties.getProperty("infusionMail.ccAddresses"), properties.getProperty("infusionMail.bccAddresses"), properties.getProperty("infusionMail.contentType"), properties.getProperty("infusionMail.subject"), properties.getProperty("infusionMail.htmlBody"), properties.getProperty("infusionMail.textBody"));
                           /*
                            *  try {
                            	obj1.sendEmail(fromAddress, toAddress, ccAddresses, bccAddresses, contentType, Failsubject, htmlBody, textBody);
                            } catch (XmlRpcException e) {
                            	e.printStackTrace();
                            }
                            */
                            email1.setBody(htmlBody);
                            EmailEngine emailEngine = new EmailEngine();
       		       	     emailFlagTesting = emailEngine.sendMimeEmail(email1);
       		       	     System.out.println("Email sent sucessfully Testing :" + emailFlagTesting);
                            System.out.println("Mail had been send successfully");  
                            request.setAttribute("cardstatus","success");
      			          	request.setAttribute("cvvstatus","success");
      			          	request.setAttribute("fName",pay_firstname);
      			          	request.setAttribute("lName",pay_lastname);
      			          	request.setAttribute("emailId",pay_e_mail);
      			          	request.setAttribute("pay_productPlan",pay_productPlan);
      			          	request.setAttribute("transStatus","fail");
      			          	request.setAttribute("failure_reason",lngMsg);
      			          	log.info("Dear Customer:"+pay_firstname+" "+pay_lastname+"\n");  
      			          	log.info("The reason is :"+lngMsg);
      			          	log.info("Customer email :"+pay_e_mail);  
      			          	log.info("Timestamp :"+tmStmp);
                            return new ModelAndView("signUpFail");
                            }
                        
                        String payInfo = (String) results1[2];
                        String sslTxnId = (String) results1[3];
                        String sslTransactionType = (String) results1[4];
                        String ppPaymentType = (String) results1[5];
                        String payDt = (String) results1[6].replace("%2d", "-");
                        payDt=(String)payDt.replace("%3a",":");
                        String grsAmt = (String) results1[7].replace("%2e", ".");
                        String ppFeeAmt = (String) results1[8].replace("%2e", ".");
                        String ppSettleAmt = (String) results1[9].replace("%2e", ".");
                        String ppTaxAmt = (String) results1[10].replace("%2e", ".");
                        String ppExchangeRate = (String) results1[11];
                        String ppPaymentStatus = (String) results1[12];
                        String ppPendingReason = (String) results1[13];
                        String PP_back1 = (String) results1[14];
                        String sslInvoiceNo = (String) results1[15].replace("%2d", "-");
                        String timeStmp = (String) results1[16];
                        System.out.println("timeStmp in doCapture::::::::::::::::::"+timeStmp);
                        String ppCorrelationID = (String) results1[17];
                        String ppParentTransactionID = (String) results1[18];
                        String ppReasonCode = (String) results1[19];

                        /* System.out.println("grsAmt::::::"+grsAmt);
                        System.out.println("ppFeeAmt::::::"+ppFeeAmt);
                        System.out.println("ppSettleAmt::::::"+ppSettleAmt);
                        System.out.println("ppTaxAmt::::::"+ppTaxAmt);
                        System.out.println("sslInvoiceNo::::::"+sslInvoiceNo);
                        System.out.println("sslTxnId::::::"+sslTxnId);*/


                        /*System.out.println("results1 in doCapture"+results1); 
                        System.out.println("statusId1 in doCapture"+statusId1); 
                        System.out.println("authorId1 in doCapture"+authorId1); 
                        System.out.println("payInfo in doCapture"+payInfo);  
                        System.out.println("transacId in doCapture"+transacId);
                        System.out.println("transacTyp in doCapture"+transacTyp);
                        System.out.println("payType in doCapture"+payType);
                        System.out.println("payDt in doCapture"+payDt);
                        System.out.println("grsAmt in doCapture"+grsAmt);
                        System.out.println("feAmt in doCapture"+feAmt);
                        System.out.println("sAmt in doCapture"+sAmt);
                        System.out.println("tAmt in doCapture"+tAmt);
                        System.out.println("exRt in doCapture"+exRt);
                        System.out.println("payStat in doCapture"+payStat);
                        System.out.println("pendR in doCapture"+pendR);
                        System.out.println("PP_back1 in doCapture"+PP_back1);
                        System.out.println("recpId in doCapture"+recpId);
                        System.out.println("timeStmp in doCapture"+timeStmp);
                        System.out.println("correlId in doCapture"+correlId);
                        System.out.println("parentId in doCapture"+parentId);
                        System.out.println("reasonCode in doCapture"+reasonCode);*/

                        if (session.getAttribute("objPayment") != null && session.getAttribute("objPayment") instanceof HLCPaymentDetails) {
                            System.out.println("objPayment instanceof PaymentDetails");
                            objPayDet.setPaymentId(objPayment.getPaymentId());
                            objPayDet.setUserId(objPayment.getUserId());
                            objPayDet.setCcName(objPayment.getCcName());
                            objPayDet.setCcType(objPayment.getCcType());
                            objPayDet.setCcExpMonth(objPayment.getCcExpMonth());
                            objPayDet.setCcExpYear(objPayment.getCcExpYear());
                            objPayDet.setCcCvvid(objPayment.getCcCvvid());
                            objPayDet.setBankName(objPayment.getBankName());
                            objPayDet.setAmount(objPayment.getAmount());
                            objPayDet.setPaymentStatus(objPayment.getPaymentStatus());
                            objPayDet.setCcNumber(objPayment.getCcNumber());
                            objPayDet.setIpAddress(objPayment.getIpAddress());
                        }

                        objPayDet.setSslResultMessage(sslResultMessage);
                        String sslResult = "0";
                        if (statusId3.equalsIgnoreCase("Success")) {
                            objPayDet.setSslResult(sslResult);
                        } else {
                            sslResult = "1";
                            objPayDet.setSslResult(sslResult);
                        }

                        objPayDet.setSslTxnId(sslTxnId);
                        // objPayDet.setSslApprovalCode(sslApprovalCode);
                        objPayDet.setSslCvv2Response(sslCvv2Response);
                        objPayDet.setSslAvsResponse(sslAvsResponse);
                        objPayDet.setSslTransactionType(sslTransactionType);
                        objPayDet.setSslInvoiceNo(sslInvoiceNo);
                        objPayDet.setSslEmail(String.valueOf(session.getAttribute("emailId")));
                        objPayDet.setPpAuthorizationID(ppAuthorizationID);
                        objPayDet.setPpPaymentType(ppPaymentType);
                        if (ppFeeAmt != null && !(ppFeeAmt.equalsIgnoreCase(""))) {
                            objPayDet.setPpFeeAmt(Double.parseDouble(ppFeeAmt));
                        } else {
                            objPayDet.setPpFeeAmt(0);
                        }
                        if (ppSettleAmt != null && !(ppSettleAmt.equalsIgnoreCase(""))) {
                            objPayDet.setPpSettleAmt(Double.parseDouble(ppSettleAmt));
                        } else {
                            objPayDet.setPpSettleAmt(0);
                        }
                        if (ppTaxAmt != null && !(ppTaxAmt.equalsIgnoreCase(""))) {
                            objPayDet.setPpTaxAmt(Double.parseDouble(ppTaxAmt));
                        } else {
                            objPayDet.setPpTaxAmt(0);
                        }
                        objPayDet.setPpExchangeRate(ppExchangeRate);
                        objPayDet.setPpPaymentStatus(ppPaymentStatus);
                        objPayDet.setPpPendingReason(ppPendingReason);
                        objPayDet.setPpCorrelationID(ppCorrelationID);
                        objPayDet.setPpParentTransactionID(ppParentTransactionID);
                        objPayDet.setPpReasonCode(ppReasonCode);
                        objPayDet.setInVoiceID(inVoiceId3);
                        objPayDet.setAmount(Double.parseDouble(amount1));

                        //System.out.println("objPayDet in servlet:" + objPayDet);
                        // }

                        //if(results!=null){
                        String statusId = (String) results[0];
                        String correlationId = (String) results[1];
                        String transactionId = (String) results[2];
                        String ppAmt = (String) results[3];
                        String avsCode = (String) results[4];
                        String cvv2 = (String) results[5];
                        String inVoiceId = (String) results[6];


                        System.out.println("statusId:" + statusId);
                      //  System.out.println("payDt:"+payDt);
                      //  System.out.println("invoiceid3:"+inVoiceId3);
                      //  System.out.println("amount:"+amount1);
                     //  System.out.println("ppFeeAmount:"+ppFeeAmt);
                         //System.out.println("transactionId:" + transactionId);
                        session.setAttribute("statusId3", statusId3);
                        session.setAttribute("statusId", statusId);
                        session.setAttribute("objPayDet", objPayDet);
                        request.setAttribute("transactionId", transactionId);
                        /*if (statusId.equalsIgnoreCase("Success")) {
                        boolean resultPay = objPaySessRemote.createPayment(objPayDet);
                        request.setAttribute("result", String.valueOf(resultPay));
                        System.out.println("resultPay in PayPalAction::::::" + resultPay);
                        String ccNumber1 = "";
                        if (!(objPayment.getCcNumber().equals("0"))) {
                        String temp = objPayment.getCcNumber().substring(0, 2);
                        String temp1 = objPayment.getCcNumber().substring(2, 12);
                        String temp2 = objPayment.getCcNumber().substring(12);
                        temp1 = "***";
                        ccNumber1 = temp + temp1 + temp2;
                        }*/
                         
                        //String emailToUSEA=mr.getMessage("hlc.emailid");
                        String emailToUSEA="jeyaprakash.sankarraj@digiblitz.com";
                        String toMailIds[] = {emailToUSEA,emailId};
                        EmailContent email = new EmailContent();
                        email.setTo(toMailIds);
                        email.setFrom("contact@menschforce.io");//info@digiblitz.com
                        email.setSubject("Payment Details");
                        System.out.println("====================Sucessmail testing002===================");
                     /*   String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
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
                        "<p>Payment has been made successfully and the details are as follows:<p>" +
                        "<span class=\"boldTxt\">Payment Details:</span></p>" +
                        "Payment Date:" + payDt + " <br/>" +
                        "Invoice ID:" + inVoiceId3 + "<br/>" +
                        "Amount:" + amount1 + "<br/>" +
                        "Fee Amount:" + ppFeeAmt + "<br/>" +
                        "cc Name: " + ccType + "<br/>" +
                        "cc Number:" + acct+ "<br/>" +
                        "Email ID:" + emailId + "<br/></p>" +
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
                        "</table>"; */
                        
                        String content ="<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: left-side;\"><div style=\"max-width: 580px; text-align: left-side;margin: 0 auto;width: 100%; display: inline-block;text-align: left-side;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"left-side\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: left-side;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>"+
                        		"</tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"left-side\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"left-side\" valign=\"top\" style=\"  padding: 16px;text-align: left-side; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: left-side;\">Dear "+pay_firstname+" "+pay_lastname+"</h4><p style=\" font-size: 16px; line-height: 27px; margin-bottom: 16px;margin-top: 16px; text-align: left-side;\">Thank you for showing interest in menschForce. Your Payment has been made successfully and the details are as follows:</p> <table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td align=\"left-side\" valign=\"top\" style=\"background-color: #fff;  padding: 16px; text-align: left-side; vertical-align: top;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody> <tr> <td style=\"color: #888;\" align=\"left-side\" valign=\"top\" style=\" padding: 16px; text-align: left-side; vertical-align: top;\">"+
                        		"<p style=\" line-height: 23px; margin-bottom: 24px; margin-top: 16px;font-size: 18px;\"><strong>Payment Details:</strong><br /></p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"><strong>Payment Date:</strong>"+ payDt +"</p> <p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"><strong>Invoice ID: </strong>"+ inVoiceId3 +"</p> <p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"><strong>Amount:</strong>"+ amount1 +"</p> <p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\">"+
                        		"<strong>Fee Amount:</strong>"+ ppFeeAmt +"</p> <p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"><strong>cc Name: </strong>"+ ccType +"</p> <p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"><strong>cc Number: </strong>"+ acct+ "</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"><strong>Email ID: </strong>"+ emailId +"</p><table style=\"width:100%; border: 1px solid black; border-collapse: collapse; padding: 15px;\"><tr  style=\"border: 1px solid black; border-collapse: collapse; padding: 15px;\">"+
                        		"<th colspan=\"2\" align=\"left\" style=\"padding: 15px;\" >e-Receipt </th>"+    
                        		"</tr><tr style=\"border: 1px solid black; border-collapse: collapse; padding: 15px;\"><td style=\"border: 1px solid black; border-collapse: collapse; padding: 15px;\">Membership subscription fee</td><td style=\"border: 1px solid black; border-collapse: collapse; padding: 15px;\">$891</td>"+
                        		  "</tr><tr style=\"border: 1px solid black; border-collapse: collapse; padding: 15px;\"><td style=\"border: 1px solid black; border-collapse: collapse; padding: 15px;\">One time setup cost</td><td style=\"border: 1px solid black; border-collapse: collapse; padding: 15px;\">$1100</td>"+
                        		  "</tr><tr style=\"border: 1px solid black; border-collapse: collapse; padding: 15px;\"><td style=\"border: 1px solid black; border-collapse: collapse; padding: 15px;\">Total</td><td style=\"border: 1px solid black; border-collapse: collapse; padding: 15px;\">$1991</td></tr>"+
                        		"</table><br>" +
                        		"<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td style=\"color: #888;\" align=\"left-side\" valign=\"top\" style=\" padding: 16px; text-align: left-side; vertical-align: top;\"><p style=\" line-height: 1px;  margin-top: 16px;\">Regards<br /></p><p style=\" line-height: 1px; margin-top: 16px;\">Menschforce.io</p><br><p style=\" line-height: 10px; margin-bottom: 24px; margin-top: 6px;\">If you have any quires, you can write a email to contact@menschforce.io</p><br></td></tr></table>" + 
                        		"</td> </tr> </tbody></table></td> </tr> </tbody></table></td></tr></tbody></table></div></div></div></div></body><body></html>";
                        email.setBody(content);
                        EmailEngine mail = new EmailEngine();
                        boolean emailFlag = mail.sendMimeEmail(email);
                        Debug.print("Email sent sucessfully :" + emailFlag);
                        
                        
                        /*-----------WELCOME MAIL----------*/
                        /*===================================================kesavan
                        EmailContent welcome_email = new EmailContent();
                        String toMailIds1[] = {emailId};
                        welcome_email.setTo(toMailIds1);
                        welcome_email.setFrom("contact@menschforce.io");//info@digiblitz.com
                        welcome_email.setSubject("Welcome");
                        System.out.println("====================Sucessmail testing003===================");
                        
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
                                      
                                      ======================================================================kesavan
                                      */
                        
                   /*     String welcome_content ="<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;"+
                        "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>"+
                        		"<tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"
                        				+"<tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td></tr>"+
                        						"</tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"+
                        				"<tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>"+
                                                "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Dear "+pay_firstname+" "+pay_firstname+"</h4><p style=\" font-size: 19px; line-height: 27px; margin-bottom: 16px;margin-top: 16px; text-align: center;\">Your payment has been declined..</p>"+
                        				"<table style=\" clear: both; margin: 0 auto;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><a style=\"font-weight: 700; color:#fff;\" href=\"https://www.digiblitzonline.com:8843/menschforce\" target=\"_blank\"><span style=\" display: block;text-align: center; color: #fff;\">Please Click here to login</span></a></td>"+ 
                        				"</tr><tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Welcome..</h4></td></tr>"+ 
                        						  "</tbody> </table> <p>&nbsp; </p> </td> </tr> </tbody></table> </td> </tr> </tbody> </table> <table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td align=\"center\" valign=\"top\" style=\"background-color: #fff;  padding: 16px; text-align: center; vertical-align: top;\">"+
                                        "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody> <tr> <td style=\"color: #888;\" align=\"right-side\" valign=\"top\" style=\" padding: 16px; text-align: center; vertical-align: top;\"><p style=\" line-height: 23px; margin-bottom: 24px; margin-top: 16px;font-size: 15px;\">"+
                        							"<strong>You're ready to start using menschFoce Online Edition</strong><br /></p>  <p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"><strong>Name:</strong>"+pay_firstname+" "+pay_firstname+"</p>"+
                                        "<p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"><strong>E-mail: </strong>"+emailId+"</p> <p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"><strong>User Name:</strong>"+CustomerUserName+"</p>"+
                        						  "<p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"><strong>password:</strong>"+Customerpassword+"</p> <p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"><strong>Registration Id: </strong>"+registrationId+"</p>"+ 
                                                  "</td> </tr> </tbody></table></td></tr></tbody></table></div></div></div></div></body></html>"; */
                        		
                        /*		
                        		======================================================================kesavan
                        welcome_email.setBody(welcome_content);
                        EmailEngine wel_mail = new EmailEngine();
                        boolean wel_emailFlag = wel_mail.sendMimeEmail(welcome_email);
                        Debug.print("Email sent sucessfully :" + wel_emailFlag);
                        
                        ======================================================================kesavan
                        
                       */
                        
                        /*request.setAttribute("correlationId", correlationId);
                        request.setAttribute("transactionId", transactionId);
                        request.setAttribute("ppAmt", amount);
                        request.setAttribute("avsCode", avsCode);
                        request.setAttribute("cvv2", cvv2);
                        request.setAttribute("INVId", inVoiceId);
                        session.removeAttribute("API_username1");
                        session.removeAttribute("API_password1");
                        session.removeAttribute("API_Signature1");
                        session.removeAttribute("currCode1");
                        session.removeAttribute("amount1");
                        }*/
                        System.out.println("purpose here: " + purpose);
                        if (purpose.trim().equalsIgnoreCase("newhorseregistration")) {
                            session.setAttribute("purpose","newhorseregistration");
                            forwardPage = "horseRegistration";
                        } else if (purpose.trim().equalsIgnoreCase("horseriderownerchange")) {
                             session.setAttribute("purpose","horseriderownerchange");
                            forwardPage = "riderOwnerChange";
                           
                        } else if (purpose.trim().equalsIgnoreCase("addriderowner")) {
                             session.setAttribute("purpose","addriderowner");
                            forwardPage = "addRiderOwner";
                        } else if (purpose.trim().equalsIgnoreCase("notregisteredhorse")) {
                            session.setAttribute("purpose","notregisteredhorse");
                            forwardPage = "notRegisteredHorse";
                        } else if (purpose.trim().equalsIgnoreCase("membershiprenewal")) {
                            session.setAttribute("purpose","membershiprenewal");
                            forwardPage = "membershipRenewal";
                        } else if (purpose.trim().equalsIgnoreCase("memberregistration")) {
                            session.setAttribute("purpose","memberregistration");
                            String registrationId = db.selectRegistrationId(pay_e_mail, Customer_Reg_Id);
                            request.setAttribute("registrationId", registrationId);
                            String CustomerUserName = db.selectCustomerUserName(pay_e_mail, Customer_Reg_Id);
                            request.setAttribute("CustomerUserName", CustomerUserName);
                            String Customerpassword = db.selectCustomerpassword(pay_e_mail, Customer_Reg_Id);
                            request.setAttribute("Customerpassword", Customerpassword);
                            String instituteId = db.selectBuyInstitutionId(emailId, Customer_Reg_Id);
                            request.setAttribute("instituteID", instituteId);
                            String customerOrderId = db.selectCustomerOrderId(emailId, Customer_Reg_Id);
                            request.setAttribute("customerOrderId", customerOrderId);
                            
                            session.setAttribute("aftFName", fName);
                            session.setAttribute("aftLastname", lName);
                            session.setAttribute("aftE_mail", emailId);
                            session.setAttribute("aftInstitutionName", instituteName);
                            session.setAttribute("aftInstituteId", instituteId);
                            session.setAttribute("aftRegistrationId", registrationId);
                            session.setAttribute("aftCustomerUserName", CustomerUserName);
                            session.setAttribute("aftCustomermobileno", pay_mobileno);
                            session.setAttribute("aftCustomerOrderId", customerOrderId);
                            
                            String paymentStatus = "success";
                            String productStatus = "active";
                            String tmStmpSuccess = (String) results[9];
                            
                            updatePaymentStatus = db.updatePaymentStatus(transactionId, emailId, Customer_Reg_Id, paymentStatus, productStatus);
                            if(updatePaymentStatus == true){
                           	 forwardPage = "signUpDetails";
                           	 log.info("Payment Success");
                           	 log.info("customer registration id: "+registrationId+"\n"+
                           	 "Customer transactionId :"+transactionId+"\n"+
                           			 "TimeStamp :"+tmStmpSuccess
                           			 );
                            }else{
                           	 log.info("Payment Success");
                           	 log.info("customer registration id: "+registrationId+"\n"+
                           	 "Customer transactionId : Not Stored in Database"+"\n"+
                           			 "TimeStamp :"+tmStmpSuccess
                           			 );
                            }
                            
                            //forwardPage = "memberRegistration";
                            forwardPage = "signUpDetails";
                        } else if (purpose.trim().equalsIgnoreCase("educationregistration")) {
                            forwardPage = "educationRegistration";
                        } else if (purpose.trim().equalsIgnoreCase("upgradehorse")) {
                             session.setAttribute("purpose","upgradehorse");
                            forwardPage = "upgradeHorseRegistration";
                        } else if (purpose.trim().equalsIgnoreCase("areaMemb")) {
                            forwardPage = "AreaMembership";
                        }
                       else if (purpose.trim().equalsIgnoreCase("endorsedForm")) {
                            forwardPage = "EndorsedFormRegistration";
                           }
                          else if (purpose.trim().equalsIgnoreCase("annualmeetingreg")) {
                            forwardPage = "annualMeetingRegistration";
                           } 
                          else if(purpose.trim().equalsIgnoreCase("newDonation"))
                          {
                              forwardPage="donateServe";
                          }
                          else if(purpose.trim().equalsIgnoreCase("endorsedRetryForm"))
                          {
                              forwardPage="EndorsedFormRetryRegistration";
                          }
                        else if(purpose.trim().equalsIgnoreCase("purchasingTickets"))
                          {
                             session.setAttribute("purpose","purchasingTickets");
                              forwardPage="addtnlTcktsPurchase";
                          }  
                        
                        else {

                            //forwardPage = "paySuccess";
                        	 String registrationId = db.selectRegistrationId(pay_e_mail, Customer_Reg_Id);
                             request.setAttribute("registrationId", registrationId);
                             String CustomerUserName = db.selectCustomerUserName(pay_e_mail, Customer_Reg_Id);
                             request.setAttribute("CustomerUserName", CustomerUserName);
                             String Customerpassword = db.selectCustomerpassword(pay_e_mail, Customer_Reg_Id);
                             request.setAttribute("Customerpassword", Customerpassword);
                             String instituteId = db.selectBuyInstitutionId(emailId, Customer_Reg_Id);
                             request.setAttribute("instituteID", instituteId);
                             request.setAttribute("instituteName", instituteName);
                             String customerOrderId = db.selectCustomerOrderId(emailId, Customer_Reg_Id);
                             request.setAttribute("customerOrderId", customerOrderId);
                             
                             
                             session.setAttribute("aftFName", fName);
                             session.setAttribute("aftLastname", lName);
                             session.setAttribute("aftE_mail", emailId);
                             session.setAttribute("aftInstitutionName", instituteName);
                             session.setAttribute("aftInstituteId", instituteId);
                             session.setAttribute("aftRegistrationId", registrationId);
                             session.setAttribute("aftCustomerUserName", CustomerUserName);
                             session.setAttribute("aftCustomermobileno", pay_mobileno);
                             session.setAttribute("aftCustomerOrderId", customerOrderId);
                             
                             String paymentStatus = "success";
                             String productStatus = "active";
                             String tmStmpSuccess = (String) results[9];
                             
                             updatePaymentStatus = db.updatePaymentStatus(transactionId, emailId, Customer_Reg_Id, paymentStatus, productStatus);
                             if(updatePaymentStatus == true){
                            	 forwardPage = "signUpDetails";
                            	 log.info("Payment Success");
                            	 log.info("customer registration id: "+registrationId+"\n"+
                            	 "Customer transactionId :"+transactionId+"\n"+
                            			 "TimeStamp :"+tmStmpSuccess
                            			 );
                             }else{
                            	 log.info("Payment Success");
                            	 log.info("customer registration id: "+registrationId+"\n"+
                            	 "Customer transactionId : Not Stored in Database"+"\n"+
                            			 "TimeStamp :"+tmStmpSuccess
                            			 );
                             }
                             
                        	forwardPage = "signUpDetails";
                        }
                        String status=null;
                      /*  String userid=db.getuseridbyemail(pay_e_mail);
                        if(userid != null && forwardPage.equalsIgnoreCase("signUpDetails")){
                            status=db.updateusersignup(pay_firstname,pay_lastname,pay_e_mail,pay_mobileno,pay_institutionName,pay_country,pay_state, pay_city, pay_credit_card_type, pay_credit_card_no, pay_cvv_no, pay_name_on_card, pay_expiry_date, pay_productPrice); 
                           }else{
                             status=db.insertsignup(pay_firstname,pay_lastname,pay_e_mail,pay_mobileno,pay_institutionName,pay_country,pay_state, pay_city, pay_credit_card_type, pay_credit_card_no, pay_cvv_no, pay_name_on_card, pay_expiry_date, pay_productPrice, pay_subscriptionType);
                           //qa.mail(e_mail);
                           }
                           */
                        //if(status != null){
                        request.setAttribute("transactionId", transactionId);
                        request.setAttribute("emailId", emailId);
                        request.setAttribute("fName", fName);
                        request.setAttribute("lName", lName);
                        request.setAttribute("instituteName", instituteName);
                        request.setAttribute("pay_productPlan", pay_productPlan);
                        request.setAttribute("pay_subscriptionType", pay_subscriptionType);
                        String instituteId = db.selectBuyInstitutionId(emailId, Customer_Reg_Id);
                        request.setAttribute("instituteID", instituteId);
                        String customerOrderId = db.selectCustomerOrderId(emailId, Customer_Reg_Id);
                        
                        String tmStmpSuccess = timeStmp;
                        
                        /*-----------------------prabhu here----------------------*/     
                        
                        System.out.println("tmStmpSuccess in Prabhu here-------------->"+tmStmpSuccess);
                        System.out.println("instituteId in Prabhu here-------------->"+instituteId);
                        System.out.println("customerOrderId in Prabhu here-------------->"+customerOrderId);
                        
                        //======================================================kesavan here===========================
                        String registrationId = db.selectRegistrationId(pay_e_mail, Customer_Reg_Id);
                        request.setAttribute("registrationId", registrationId);
                        String CustomerUserName = db.selectCustomerUserName(pay_e_mail, Customer_Reg_Id);
                        request.setAttribute("CustomerUserName", CustomerUserName);
                        String Customerpassword = db.selectCustomerpassword(pay_e_mail, Customer_Reg_Id);
                        request.setAttribute("Customerpassword", Customerpassword);
                        String instituteId1 = db.selectBuyInstitutionId(emailId, Customer_Reg_Id);
                        request.setAttribute("instituteID", instituteId1);
                        request.setAttribute("instituteName", instituteName);
                        String customerOrderId1 = db.selectCustomerOrderId(emailId, Customer_Reg_Id);
                        request.setAttribute("customerOrderId", customerOrderId1);
                        
                       
                        
                      //String fromAddress = "prabhu.pandi@digiblitz.in";
                       /*
                        String fromAddress = properties.getProperty("infusionMail.fromAddress");
                        String toAddress = emailId;
                        String ccAddresses = properties.getProperty("infusionMail.ccPaymentAddress");
                        String bccAddresses = properties.getProperty("infusionMail.bccPaymentAddress");
                        String contentType = "HTML";
                        String subject = "Payment Success";
                        */
                        
                        String emailId1=emailId;
                        String toMailIds2[] = { emailId1};
                        EmailContent email1 = new EmailContent();
                        email1.setTo(toMailIds2);
                        email1.setFrom("contact@menschforce.in");
                        email1.setSubject("Payment Success");
                        System.out.println("====================Sucessmail testing004===================");
                        /*
                        String htmlBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
                                "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
                                "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
                                " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
                                "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Congratulations "+fName+" "+lName+"</h4><p style=\"   font-size: 19px;" +
                                  "line-height: 27px; margin-bottom: 16px;margin-top: 16px; text-align: center;\">Your payment has accepted and you are registered Successfully... </p><table style=\" clear: both; margin: 0 auto;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
                                  "<tbody> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Payment Details</h4></td>" +
                                  "</tr> </tbody> </table> <p>&nbsp; </p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td align=\"center\" valign=\"top\" style=\"background-color: #fff;  padding: 16px; text-align: center; vertical-align: top;\">" +
                                  "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody> <tr> <td style=\"color: #888;\" align=\"center\" valign=\"top\" style=\" padding: 16px; text-align: center; vertical-align: top;\">" +
                                  " <p style=\" line-height: 23px; margin-bottom: 24px; margin-top: 16px;font-size: 15px;\">" +
									"<strong>E-mail ID</strong><br /> "+emailId+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
                                  "<strong>Your transaction id is :</strong><br />"+transactionId+"</p> <p style=\" line-height: 23px; margin-bottom: 24px;margin-top: 16px;font-size: 15px;\">" +
									"<strong>Amount Charged :</strong><br />"+amount+"</p> <p style=\" line-height: 23px; margin-bottom: 24px;margin-top: 16px;font-size: 15px;\">" +
                                  "<strong>Timestamp : </strong><br />"+tmStmpSuccess+"</p> <p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\">" +
                                  "<strong>Customer ID :</strong><br /> "+instituteId+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
                                  "<strong>Registration ID :</strong><br /> "+Customer_Reg_Id+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
                                  "<strong>Customer Order ID</strong><br /> "+customerOrderId+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
                                  "</td> </tr> </tbody></table></td></tr></tbody></table></div></div></div></div></body></html>";
                        
                        */
                        String textBody = "congratulations! "+fName+" "+lName+" Your payment has accepted and you are registered successfully... \n" +
                        		"Your transaction id is :"+transactionId+"\n"+
                        		"Timestamp : "+tmStmpSuccess;
                  
                        //Infusion Soft mail starts
                                         
               //obj1.sendEmail(properties.getProperty("infusionMail.fromAddress"), usrEmail, properties.getProperty("infusionMail.ccAddresses"), properties.getProperty("infusionMail.bccAddresses"), properties.getProperty("infusionMail.contentType"), properties.getProperty("infusionMail.subject"), properties.getProperty("infusionMail.htmlBody"), properties.getProperty("infusionMail.textBody"));
                  /*
                        try {
           			obj1.sendEmail(fromAddress, toAddress, ccAddresses, bccAddresses, contentType, subject, htmlBody, textBody);
           			System.out.println("Mail had been send successfully"); 
           		} catch (XmlRpcException e) {
           		// TODO Auto-generated catch block
           			e.printStackTrace();
           		}
                                
                 */                                               
                       //Infusion Soft mail ends
                        
                        //}
                            
                        String htmlBody ="<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;"+
                        "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>"+
                        		"<tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"
                        				+"<tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td></tr>"+
                        						"</tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"+
                        				"<tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>"+
                                                "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Dear "+pay_firstname+" "+pay_firstname+"</h4><p style=\" font-size: 19px; line-height: 27px; margin-bottom: 16px;margin-top: 16px; text-align: center;\">Your payment has been declined..</p>"+
                        				"<table style=\" clear: both; margin: 0 auto;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><a style=\"font-weight: 700; color:#fff;\" href=\"https://www.digiblitzonline.com:8843/menschforce\" target=\"_blank\"><span style=\" display: block;text-align: center; color: #fff;\">Please Click here to login</span></a></td>"+ 
                        				"</tr><tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Welcome..</h4></td></tr>"+ 
                        						  "</tbody> </table> <p>&nbsp; </p> </td> </tr> </tbody></table> </td> </tr> </tbody> </table> <table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td align=\"center\" valign=\"top\" style=\"background-color: #fff;  padding: 16px; text-align: center; vertical-align: top;\">"+
                                        "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody> <tr> <td style=\"color: #888;\" align=\"right-side\" valign=\"top\" style=\" padding: 16px; text-align: center; vertical-align: top;\"><p style=\" line-height: 23px; margin-bottom: 24px; margin-top: 16px;font-size: 15px;\">"+
                        							"<strong>You're ready to start using menschFoce Online Edition</strong><br /></p>  <p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"><strong>Name:</strong>"+pay_firstname+" "+pay_firstname+"</p>"+
                                        "<p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"><strong>E-mail: </strong>"+emailId+"</p> <p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"><strong>User Name:</strong>"+CustomerUserName+"</p>"+
                        						  "<p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"><strong>password:</strong>"+Customerpassword+"</p> <p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"><strong>Registration Id: </strong>"+registrationId+"</p>"+ 
                                                  "</td> </tr> </tbody></table></td></tr></tbody></table></div></div></div></div></body></html>";
                        email1.setBody(htmlBody);
                        EmailEngine emailEngine = new EmailEngine();
   		       	     emailFlagTesting = emailEngine.sendMimeEmail(email1);
   		       	     System.out.println("Email sent sucessfully Testing :" + emailFlagTesting);
                        
                        
                        
                       
                        return new ModelAndView(forwardPage); 
                       
                    }
                /*else if (statusId3.equalsIgnoreCase("Failure")) {
                System.out.println("Inside Failure11111");
                String correctAction = remote.getErrorDets(errorCd);
                String finalCorecAction = "";
                if (correctAction != null && correctAction.equalsIgnoreCase("")) {
                finalCorecAction = correctAction;
                } else {
                finalCorecAction = "NG";
                }
                String toMailIds[] = {"payments@useventing.com"};
                EmailContent email = new EmailContent();
                email.setTo(toMailIds);
                email.setFrom("dashboard@useventing.com");
                email.setSubject("Donation");
                String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                " <tr>" +
                " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                "<tr>" +
                "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"USEA Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
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
                "<p>Payment has been declined and the details are as follows:<p>" +
                "<span class=\"boldTxt\">Declined Details:</span>.</p>" +
                "TIMESTAMP:" + tmStmp + " <br/>" +
                "L_ERRORCODE0:" + errorCd + "<br/>" +
                "L_LONGMESSAGE0:" + lngMsg + "<br/>" +
                "AMT:" + amount + "<br/>" +
                "AVSCODE: " + sslAvsResponse + "<br/>" +
                "CVV2MATCH:" + sslCvv2Response + "<br/>" +
                "Corrective action:" + finalCorecAction + "<br/></p>" +
                "<span class=\"boldRedTxt\">USEA Team</span></td>" +
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
                EmailEngine emailEngine = new EmailEngine();
                boolean emailFlag = emailEngine.sendMimeEmail(email);
                Debug.print("Email sent sucessfully :" + emailFlag);
                request.setAttribute("msg", "Payment Declined");
                request.setAttribute("errorCd", errorCd);
                request.setAttribute("lngMsg", lngMsg);
                session.removeAttribute("API_username1");
                session.removeAttribute("API_password1");
                session.removeAttribute("API_Signature1");
                session.removeAttribute("currCode1");
                session.removeAttribute("amount1");
                return mapping.findForward("payDecline");
                }*/
                } else {
                	System.out.println("Inside Failure");
                    System.out.println("Inside Failure22222");

                    String correctAction = humanMemb.getErrorDets(errorCd);
                    System.out.println("Inside correctAction"+correctAction);
                    String finalCorecAction = "";

                    if (correctAction != null || !(correctAction.equalsIgnoreCase(""))) {
                        finalCorecAction = correctAction;
                    } else {
                        finalCorecAction = "NG";
                    }
                    payRes.setFinalCorecAction(finalCorecAction);
                    session.setAttribute("payRes", payRes);
                    session.setAttribute("statusId3", statusId3);
                    //System.out.println("Inside Paypal ACtion "+finalSslAvsResponse+"  "+finalSslCvv2Response);
                    // String emailToUSEA=mr.getMessage("hlc.emailid");
                     String emailToUSEA="jeyaprakash.sankarraj@digiblitz.com";
                     String toMailIds[] = {emailToUSEA, emailId};
                    EmailContent email = new EmailContent();
                    email.setTo(toMailIds);
                    email.setFrom("contact@menschforce.io");//info@digiblitz.com
                    String subject=null;
                              if (purpose.trim().equalsIgnoreCase("newhorseregistration")) {
                            subject="Horse Registration Decline";
                        } else if (purpose.trim().equalsIgnoreCase("horseriderownerchange")) {
                             subject="Horse Rider/Owner Change Decline";
                           
                        } else if (purpose.trim().equalsIgnoreCase("addriderowner")) {
                            subject="Add Rider/Owner Decline";
                        } else if (purpose.trim().equalsIgnoreCase("notregisteredhorse")) {
                            subject="Not Registered Horse Decline";
                        } else if (purpose.trim().equalsIgnoreCase("membershiprenewal")) {
                            subject="Membership Renewal  Decline";
                        } else if (purpose.trim().equalsIgnoreCase("memberregistration")) {
                             subject="Membership Registration  Decline";
                        } else if (purpose.trim().equalsIgnoreCase("educationregistration")) {
                            subject="Education Activity Registration Decline";
                        } else if (purpose.trim().equalsIgnoreCase("upgradehorse")) {
                             subject="Upgrade Horse Decline";
                        } else if (purpose.trim().equalsIgnoreCase("areaMemb")) {
                            subject="Area Membership Decline";
                        }
                       else if (purpose.trim().equalsIgnoreCase("endorsedForm")) {
                            subject="Event Registration Decline";
                           }
                          else if (purpose.trim().equalsIgnoreCase("annualmeetingreg")) {
                             subject="Annual Meeting Registration Decline";
                           } 
                          else if(purpose.trim().equalsIgnoreCase("newDonation"))
                          {
                               subject="Donation Decline";
                          }
                         else if(purpose.trim().equalsIgnoreCase("purchasingTickets"))
                          {
                               subject="Purchasing Tickets for Area Reception Decline";
                          }
                          else if(purpose.trim().equalsIgnoreCase("endorsedRetryForm"))
                          {
                              
                          }
                            
                     email.setSubject(subject);
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
                    "<p>Payment has been declined and the details are as follows:<p>" +
                    "<span class=\"boldTxt\">Declined Details:</span>.</p>" +
                    "TIMESTAMP:" + tmStmp + " <br/>" +
                    "L_ERRORCODE0:" + errorCd + "<br/>" +
                    "L_LONGMESSAGE0:" + lngMsg + "<br/>" +
                    "AMT:" + amount + "<br/>" +
                    "AVSCODE: " + finalSslAvsResponse + "<br/>" +
                    "CVV2MATCH:" + finalSslCvv2Response + "<br/>" +
                    "Corrective action:" + finalCorecAction + "<br/></p>" +
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
                    EmailEngine mail = new EmailEngine();
                    System.out.println("====================Sucessmail testing005===================");
                    boolean emailFlag = mail.sendMimeEmail(email);
                    Debug.print("Email sent sucessfully :" + emailFlag);
                    /* session.removeAttribute("API_username1");
                    session.removeAttribute("API_password1");
                    session.removeAttribute("API_Signature1");
                    session.removeAttribute("currCode1");
                    session.removeAttribute("amount1");*/
                    
                    System.out.println("purpose in decline: " + purpose);
                    if (purpose.trim().equalsIgnoreCase("newhorseregistration")) {
                        session.setAttribute("purpose","newhorseregistration");
                        forwardPage = "horseRegistration";
                    } else if (purpose.trim().equalsIgnoreCase("horseriderownerchange")) {
                        session.setAttribute("purpose","horseriderownerchange");
                        forwardPage = "riderOwnerChange";
                    } else if (purpose.trim().equalsIgnoreCase("addriderowner")) {
                        session.setAttribute("purpose","addriderowner");
                        forwardPage = "addRiderOwner";
                    } else if (purpose.trim().equalsIgnoreCase("notregisteredhorse")) {
                        session.setAttribute("purpose","notregisteredhorse");
                        forwardPage = "notRegisteredHorse";
                    } else if (purpose.trim().equalsIgnoreCase("membershiprenewal")) {
                        session.setAttribute("purpose","membershiprenewal");
                        forwardPage = "membershipRenewal";
                    } else if (purpose.trim().equalsIgnoreCase("memberregistration")) {
                        session.setAttribute("purpose","memberregistration");
                        forwardPage = "memberRegistration";
                    } else if (purpose.trim().equalsIgnoreCase("educationregistration")) {
                        forwardPage = "educationRegistration";
                    } else if (purpose.trim().equalsIgnoreCase("upgradehorse")) {
                         session.setAttribute("purpose","upgradehorse");
                        forwardPage = "upgradeHorseRegistration";
                    } else if (purpose.trim().equalsIgnoreCase("areaMemb")) {
                        forwardPage = "AreaMembership";
                    } 
                     else if (purpose.trim().equalsIgnoreCase("endorsedForm")) {
                            forwardPage = "EndorsedFormRegistration";
                           }
                          else if (purpose.trim().equalsIgnoreCase("annualmeetingreg")) {
                            forwardPage = "annualMeetingRegistration";
                           } 
                    else if(purpose.trim().equalsIgnoreCase("newDonation"))
                          {
                              forwardPage="donateServe";
                          }
                     else if(purpose.trim().equalsIgnoreCase("endorsedRetryForm"))
                          {
                              forwardPage="EndorsedFormRetryRegistration";
                          }
                     else if(purpose.trim().equalsIgnoreCase("purchasingTickets"))
                          {
                              session.setAttribute("purpose","purchasingTickets");
                              forwardPage="addtnlTcktsPurchase";
                          }  
                    else {
                        //forwardPage = "payDecline";
                    	 request.setAttribute("fName",pay_firstname);
   			          request.setAttribute("lName",pay_lastname);
   			          request.setAttribute("emailId",pay_e_mail);
   			       request.setAttribute("pay_productPlan",pay_productPlan);
                        forwardPage = "signUpFail";
                    }
                    request.setAttribute("cardstatus","success");
			          request.setAttribute("cvvstatus","success");
			          request.setAttribute("fName",pay_firstname);
			          request.setAttribute("lName",pay_lastname);
			          request.setAttribute("emailId",pay_e_mail);
			          request.setAttribute("pay_productPlan",pay_productPlan);
                    return new ModelAndView("signUpFail"); 
                }
            } else {
                /*request.setAttribute("msg", "Payment Already Made");
                session.removeAttribute("API_username1");
                session.removeAttribute("API_password1");
                session.removeAttribute("API_Signature1");
                session.removeAttribute("currCode1");
                session.removeAttribute("amount1");
                return mapping.findForward("payDecline");*/
               
                session.setAttribute("statusId3", statusId3);
                System.out.println("purpose in result null: " + purpose);

                if (purpose.trim().equalsIgnoreCase("newhorseregistration")) {
                   session.setAttribute("purpose","newhorseregistration");  
                    forwardPage = "horseRegistration";
                } else if (purpose.trim().equalsIgnoreCase("horseriderownerchange")) {
                    session.setAttribute("purpose","horseriderownerchange");
                    forwardPage = "riderOwnerChange";
                } else if (purpose.trim().equalsIgnoreCase("addriderowner")) {
                     session.setAttribute("purpose","addriderowner");
                    forwardPage = "addRiderOwner";
                } else if (purpose.trim().equalsIgnoreCase("notregisteredhorse")) {
                    session.setAttribute("purpose","notregisteredhorse");  
                    forwardPage = "notRegisteredHorse";
                } else if (purpose.trim().equalsIgnoreCase("membershiprenewal")) {
                     session.setAttribute("purpose","membershiprenewal");
                    forwardPage = "membershipRenewal";
                } else if (purpose.trim().equalsIgnoreCase("educationregistration")) {
                    forwardPage = "educationRegistration";
                } else if (purpose.trim().equalsIgnoreCase("upgradehorse")) {
                    session.setAttribute("purpose","upgradehorse"); 
                    forwardPage = "upgradeHorseRegistration";
                } else if (purpose.trim().equalsIgnoreCase("areaMemb")) {
                    forwardPage = "AreaMembership";
                } 
                else if (purpose.trim().equalsIgnoreCase("endorsedForm")) {
                    forwardPage = "EndorsedFormRegistration";
                }
                else if (purpose.trim().equalsIgnoreCase("annualmeetingreg")) {
                forwardPage = "annualMeetingRegistration";
                }
                else if(purpose.trim().equalsIgnoreCase("newDonation"))
                          {
                              forwardPage="donateServe";
                          }
                else if (purpose.trim().equalsIgnoreCase("memberregistration")) {
                             session.setAttribute("purpose","memberregistration");
                            forwardPage = "memberRegistration";
                        }
                 else if(purpose.trim().equalsIgnoreCase("endorsedRetryForm"))
                          {
                              forwardPage="EndorsedFormRetryRegistration";
                          }
                 else if(purpose.trim().equalsIgnoreCase("purchasingTickets"))
                          {
                              session.setAttribute("purpose","purchasingTickets");
                              forwardPage="addtnlTcktsPurchase";
                          }  
                else {
                    //forwardPage = "payDecline";
                    request.setAttribute("cardstatus","success");
			          request.setAttribute("cvvstatus","success");
			          request.setAttribute("fName",pay_firstname);
			          request.setAttribute("lName",pay_lastname);
			          request.setAttribute("emailId",pay_e_mail);
			          request.setAttribute("pay_productPlan",pay_productPlan);
                	forwardPage = "signUpFail";
                }
                return new ModelAndView(forwardPage); 

            }

        } catch (Exception e) {
            System.out.print("in the catch block");
            e.printStackTrace();
            //return new ModelAndView("payDecline"); 
            System.out.println("outside the try :::::::::::::::::::::::::::::::::::");
            String emailToUSEA="jeyaprakash.sankarraj@digiblitz.com";
            String toMailIds[] = {emailToUSEA, emailId};
            EmailContent email = new EmailContent();
            email.setTo(toMailIds);
            email.setFrom("contact@menschforce.io");//info@digiblitz.com
            String subject=null;
             
            System.out.println("====================Sucessmail testing006===================");
            email.setSubject(subject);
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
                    "<p>Payment has been declined and the details are as follows:<p>" +
                    "<span class=\"boldTxt\">Declined Details:</span>.</p>" +
                    "You transaction was faild ! Please try again :" + " <br/>" +
                   
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
            EmailEngine mail = new EmailEngine();
            boolean emailFlag = mail.sendMimeEmail(email);
            Debug.print("Email sent sucessfully :" + emailFlag);
            request.setAttribute("cardstatus","success");
	        request.setAttribute("cvvstatus","success");
	        request.setAttribute("fName",pay_firstname);
	          request.setAttribute("lName",pay_lastname);
	          request.setAttribute("emailId",pay_e_mail);
	          request.setAttribute("pay_productPlan",pay_productPlan);
            return new ModelAndView("signUpFail"); 
            
        }
        return null;

    }
}

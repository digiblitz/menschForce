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
package com.user.action;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.db.GeneralDBAction;
import com.hlcform.stateless.HLCkaverystatelessBean;
import com.hlchorse.form.display.HLCKaverySessionStatefulBean;
import com.hlchorse.form.util.HLCUserRegVO;
import com.hlcmsg.groups.HLCMessageSessionBean;
import com.hlcrole.management.HLCBrahmaputraSessionBean;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Masteraccount implements Controller {


	static Logger log = Logger.getLogger(Masteraccount.class.getName());
	
	public static final String LOG_FILE = "Log4j.properties";
	InputStream is;
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		
		// ======================log file properties configuration started====================
	       Properties logProp = new Properties();  
		      is = (InputStream) getClass().getClassLoader().getResourceAsStream(LOG_FILE);
		      logProp.load(is); 
		      PropertyConfigurator.configure(logProp);      
		      log.info("Logging enabled");  
		   // ======================log file properties configuration ended==================== 
	      
		// TODO Auto-generated method stub
		 HLCkaverystatelessBean humanMemb=new HLCkaverystatelessBean();
         HLCMessageSessionBean msgBean=new HLCMessageSessionBean();
         HLCBrahmaputraSessionBean roleBean=new HLCBrahmaputraSessionBean(); 
         HLCKaverySessionStatefulBean hrsBean=new HLCKaverySessionStatefulBean();
         
       
        
         HLCUserRegVO userregvo=new HLCUserRegVO();
         GeneralDBAction db=new GeneralDBAction();	 
        
         HttpSession session=request.getSession(true); 
         String masterProcess=request.getParameter("cmd");
        // String source = request.getParameter("source");
         
         if(masterProcess != null && masterProcess.equals("profiledetails")){
         	String userId=(String)session.getAttribute("userId");
         	System.out.println("------USerACtion----userid"+userId);
         	String emailId=db.getemail_id(userId);
         	System.out.println("------USerACtion----emailId"+emailId);
         	ArrayList userdetails=db.getprofile_details(emailId);
         	String details[];
         	String username=null;
         	String password=null;
         	String institution_name=null;
         	String e_mail=null;
         	String customer_id=null;
         	if(userdetails!=null){
    			 Iterator itr=userdetails.iterator();			 
    			 while(itr.hasNext()){
    				details=(String[]) itr.next();
    				username=details[0];
    				password=details[1];
    				institution_name=details[2];
    				e_mail=details[3];
    				customer_id=details[4];
    			 }}
    			 request.setAttribute("username", username);
    			 System.out.println("------USerACtion----Username"+username);
    			 request.setAttribute("password", password );
    			 request.setAttribute("institutionname", institution_name);
    			 request.setAttribute("e_mail", e_mail);
    			 request.setAttribute("customer_id", customer_id);	
         	  return new ModelAndView("profiledetails");  
         	  
           }
           else if(masterProcess != null && masterProcess.equals("product_package")){
           	String userId=(String)session.getAttribute("userId");
           	String emailId=db.getemail_id(userId);
           	System.out.print("userId"+userId);
           	System.out.println("emailId"+emailId);
           	ArrayList packagedetails=db.getproduct_package(emailId);
           	String packages[];
           	String Productplan=null;
           	String subscription_plan=null;
           	String amount=null;
           	if(packagedetails!=null){
      			 Iterator itr=packagedetails.iterator();			 
      			 while(itr.hasNext()){
      				packages=(String[]) itr.next();
      				Productplan=packages[0];
      				subscription_plan=packages[1];
      				amount=packages[2];
      			
      			 }}
      			 request.setAttribute("Productplan", Productplan);
      			 request.setAttribute("subscription_plan",subscription_plan);
      			 request.setAttribute("amount", amount);
      			 System.out.println("Productplan"+Productplan);
      			System.out.println("Subscription_Plan"+subscription_plan);
      			System.out.println(amount);
      			 String dateStr=db.getdate(emailId);	 				
      							 String difference=null;
      							 //subscription status Area---------------------------------------				  	  
      							  SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
      								
      								try {
      									Date date=formatter.parse(dateStr);
      									long lo=30;					
      									Date thirty = new Date(date.getTime() + lo * 1000 * 60 * 60 * 24);						
      									System.out.println("Tomorrow"+thirty);						
      									String subsenddate=formatter.format(thirty);						
      									System.out.println(subsenddate);			
      									
      									Date currentdate=new Date();
      									String strcurrent=formatter.format(currentdate);	
      									
      				int diffInDays = (int) ((thirty.getTime() - currentdate.getTime()) / (1000 * 60 * 60 * 24));

      									if(strcurrent.equals(subsenddate)){
      										diffInDays=0;
      									}		
      									else{
      										System.out.println(diffInDays);
      										difference=Integer.toString(diffInDays);
      									}
      								} catch (ParseException e) {					
      									e.printStackTrace();
      								}
      								
      								request.setAttribute("difference", difference);

      								return new ModelAndView("productpackage");  
           	  
             }
           else if(masterProcess != null && masterProcess.equals("security")){
             	String userId=(String)session.getAttribute("userId");
             	String emailId=db.getemail_id(userId);
             	ArrayList securitydetails=db.getsecurity_details(emailId);
             	String packages[];
             	String Productplan=null;
             	String mobile=null;
             	String e_mail=null;
             	String last_login=null;
             	if(securitydetails!=null){
        			 Iterator itr=securitydetails.iterator();			 
        			 while(itr.hasNext()){
        				packages=(String[]) itr.next();
        				Productplan=packages[0];
        				mobile=packages[1];
        				e_mail=packages[2];
        				last_login=packages[3];
        			
        			 }}
        			// request.setAttribute("Productplan", Productplan);
        			// request.setAttribute("mobile",mobile);
        			// request.setAttribute("e_mail", e_mail);
        			// request.setAttribute("lastlogin", last_login);
             	  return new ModelAndView("security");  
             	  
               }
        // update profile-----------------------------------
else if(masterProcess!=null && masterProcess.equalsIgnoreCase("updateprofile")){
			    
			    System.out.println("user picture upload here");
			    String new_email=request.getParameter("e_mail");
			     //String usernamee=db1.getusername(user_id);
			     
			   String username=request.getParameter("username");
			   String password=request.getParameter("password");
			   String e_mail=request.getParameter("email");
			   String institutionname=request.getParameter("institutionname");
			   String customerid=request.getParameter("customerid");
			   System.out.println("");
			 System.out.println("User action----------new_email"+new_email);
			 System.out.println("--------username"+username);
			 System.out.println("customerid"+customerid);
			   String updateprofile=db.updateprofile(new_email,username, password, e_mail);
			   String updatesignup=db.updatesignupuserdetails(new_email,username, password, e_mail, institutionname);
			   request.setAttribute("username", username);
  			 System.out.println("------USerACtion----Username"+username);
  			 request.setAttribute("password", password );
  			 request.setAttribute("institutionname", institutionname);
  			 request.setAttribute("e_mail", e_mail);
  			 request.setAttribute("customer_id", customerid);		
			   if(updateprofile != null && updatesignup != null){
				   request.setAttribute("upstatus", "Updated Succesfully!!!");
			   }else{
				   request.setAttribute("upstatus", "Updated Failed!!!!!");
			   }
			   request.setAttribute("upstatus", updateprofile);
			   return new ModelAndView("profiledetails");
			   }
         else if(masterProcess!=null && masterProcess.equalsIgnoreCase("updateproductppackage")){
			    
			    System.out.println("user picture upload here");
			     String userId=request.getParameter("userId");
			     //String usernamee=db1.getusername(user_id);
			     
			   String plantype=request.getParameter("plan_type");
			   String subscriptiontype=request.getParameter("subs_type");
			   String paymentHistory=request.getParameter("pay_history");
			   String subscriptionExpiry=request.getParameter("Subs_expiry");
			   String new_email=request.getParameter("email");
			   
			  
			   String updateproductppackage=db.updateproductpackage(new_email,plantype, subscriptiontype, paymentHistory,subscriptionExpiry); 
			   request.setAttribute("updatestatus", updateproductppackage);
			   request.setAttribute("Productplan", plantype);
    			 request.setAttribute("subscription_plan",subscriptiontype);
    			 request.setAttribute("amount", paymentHistory);
    			 System.out.println("Productplan"+plantype);
    			System.out.println("Subscription_Plan"+subscriptiontype);
    			System.out.println("Payment History"+paymentHistory);
			   return new ModelAndView("productpackage");
			   }
         else if(masterProcess!=null && masterProcess.equalsIgnoreCase("updatesecurity")){
			    
			    System.out.println("user picture upload here");
			     String userId=request.getParameter("userId");
			     //String usernamee=db1.getusername(user_id);
			     
			   String Productplan=request.getParameter("plan_type");
			   String mobile=request.getParameter("Rec_phno");
			   String e_mail=request.getParameter("rec_email");
			   String last_login=request.getParameter("last_login");
			   String new_email=request.getParameter("email");
			   
			  
			   String updatesecurity=db.updatesecurity(Productplan,mobile, e_mail, last_login); 
			   request.setAttribute("updatesecurity", updatesecurity);
			   request.setAttribute("Productplan", Productplan);
 			 request.setAttribute("mobile",mobile);
 			 request.setAttribute("e_mail", e_mail);
 			 request.setAttribute("lastlogin",last_login);
 			 System.out.println("Productplan"+Productplan);
 			System.out.println("mobile"+mobile);
 			System.out.println("e_mail"+e_mail);
 		System.out.println("lastlogin"+last_login);
			   return new ModelAndView("productpackage");
			   }

        //----------------------------------------masster account ends here--------------------
         
		return null;
	}

}

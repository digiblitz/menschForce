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
package com.db.reminder.QA;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.xmlrpc.XmlRpcException;

import com.artifact.action.GldSignUpAction;
import com.db.GeneralDBAction;
import com.hlcmrm.util.Debug;
import com.infusionejb.session.InfusionSessionBean;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;

@WebService(endpointInterface="com.db.reminder.QA.QAReminderInterface")
public class QAReminderService extends Thread implements QAReminderInterface {

	Calendar cal  = Calendar.getInstance();
	boolean executed=false;
	 
	GeneralDBAction db=new GeneralDBAction();
	
	
	 String prov_schedule_id=null;
	 String provisional_partner_id=null;
	 String prov_schedule_date=null;
	 String prov_emailid=null;
	 String active_status=null;
	 String addDate=null;
	 String prov_status=null;
	 String email_status=null;
	 
	 String val=null;
	
		@Override
	public String QAreminderemailservice( String input) {
		
			
		boolean status = true;
		
		while(status){
		System.out.println("Thread alive check");
		new Thread(){
		//boolean check = checkremainderdate();
		
		
		public void run(){
			
								boolean res=false;
				 ArrayList evePartDets =(ArrayList) db.SelectEventPartnerDetails();
								 			
				if(evePartDets!=null && evePartDets.size()!=0){
					 Iterator itr = evePartDets.iterator();
					    while (itr.hasNext()) {    
					    	String TempList[] = (String[])itr.next();
					    	prov_schedule_id=TempList[0];
					    	provisional_partner_id=TempList[1];
					    	prov_schedule_date=TempList[2];
					    	prov_emailid=TempList[3];
					    	active_status=TempList[4];
					    	addDate=TempList[5];
					    	prov_status=TempList[6];
					    	email_status=TempList[7];
					    	System.out.println("prov_schedule_id==="+prov_schedule_id);
					    	 System.out.println("prov_emailid in Email Service----"+prov_emailid);
					    	 System.out.println("emailstatus==="+email_status);
					    	 
				
					    	  EmailContent sendemail=new EmailContent();
							   String emailid = prov_emailid;
							   String toMailIds[] = {emailid};
							   
							   sendemail.setTo(toMailIds);
							   sendemail.setFrom("priya.karunanidhi@digiblitz.in");
							   sendemail.setSubject("Reminder QA !");
							   		
							    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						        Date scheduled_date=null;
								try {
									scheduled_date = sdf.parse(prov_schedule_date);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}//scheduled date
						        System.out.println("date1 : " + sdf.format(scheduled_date));
						        

						          Date date2 = new Date(); //CURRENT DATE
								 String strCurrtDt=sdf.format(date2);
								 Date current_date = null;
								try {
									current_date = sdf.parse(strCurrtDt);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							
							 
							    Calendar cal1 = Calendar.getInstance(); //previous date
								cal1.setTime(scheduled_date);
								cal1.add(Calendar.DAY_OF_YEAR, -1);
								Date previousDate = cal1.getTime();
								
								System.out.println("scheduled_date======="+scheduled_date);
								System.out.println("current_date======="+current_date);
								System.out.println("email_status======="+email_status);
								
								
//								 if(scheduled_date.compareTo(current_date) == 0 ){
//						                System.out.println("Date1 is before Date2");
//						                res=true;
//						            }
//								 
//								 else if(previousDate.compareTo(current_date) == 0){
//						                System.out.println("Date1 is equal Date2");
//						                res=true;
//						                
//						            }else{
//						               
//						                res=false;
//						            }
								
								
								  if(previousDate.compareTo(current_date) == 0 && email_status.equalsIgnoreCase("QAScheduled"))
								   {
									   System.out.println("in if method");
									   
									   String htmlbodytocandidate = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
							                   "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
							                   "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
							                   " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
							                   "<tr><td  valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 25px; margin: 16px 0 8px;padding: 0;text-align: left;\">Good Morning ,</h4></td> </tr><tr></tr> <tr><td style=\"padding:0px 10px\"><p style=\"margin: 20px 0 10px;text-align:left;\">"+
							                   "We are so excited for the event tomorrow at "+prov_schedule_date+"! Be sure to have your questions ready. </td></tr>"+
							                   "<td style=\"border-radius:4px; padding:12px 24px;text-align:center;vertical-align:middle;background-color:#22aaa0;font-size:16px\" class=\"m_3811281845977288554font_default\">"+
							                   " <a  href=\"http://192.168.1.142:8280/menschforce/ConfirmPartnerCall.html?cmd="+emailid+"\" style=\"font-weight:700;color:#fff\"><span style=\"display:block;text-align:center;color:#fff\">Confirm</span></a>"+
							                   "</td>"+
							                   "<td style=\"border-radius:4px;padding:12px 24px;text-align:center;vertical-align:middle;background-color:#22aaa0;font-size:16px\" class=\"m_3811281845977288554font_default\">"+
							                   "<a style=\"font-weight:700;color:#fff\"> <span style=\"display:block;text-align:right;color:#fff\">No</span></a>"+
							                   "</td>"+
							                   "<tr><td style=\"padding:0px 10px\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>MenschForce Team</td></tr></tbody> </table> </td> </tr> </tbody> </table> </div></div></div></div></body></html>";
									   
									 
									   
									   
									   sendemail.setBody(htmlbodytocandidate);
									   EmailEngine emailEngine1 = new EmailEngine();
									   boolean emailFlag1 = emailEngine1.sendMimeEmail(sendemail);
									   System.out.println("Email sent sucessfully on date:" + emailFlag1);
									   String emailstatus="QAFirstReminder";
									   
										boolean statusupdate=db.updatePartnerEmailStatus(emailstatus,prov_schedule_id);
										System.out.println("statusupdate from FirstReminder "+statusupdate);
									   
								   }
							
							 if(scheduled_date.compareTo(current_date) == 0 && email_status.equalsIgnoreCase("QAFirstReminder"))
							   {

								 System.out.println("in if method 1");
								   
								  String htmlbodytocandidateondate = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
						                   "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
						                   "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
						                   " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
						                   "<tr><td  valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 25px; margin: 16px 0 8px;padding: 0;text-align: left;\">Hi ,</h4></td> </tr><tr></tr> <tr><td style=\"padding:0px 10px\"><p style=\"margin: 20px 0 10px;text-align:left;\">"+
						                   "The Introductory partner onboarding call on menschforce-venor management system starts promptly at 12:00 pm EST. </td></tr>"+
						                   "<tr><td style=\"padding:0px 10px\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>MenschForce Team</td></tr></tbody> </table> </td> </tr> </tbody> </table> </div></div></div></div></body></html>";
								   
								   sendemail.setBody(htmlbodytocandidateondate);
								   EmailEngine emailEngine = new EmailEngine();
								   boolean emailFlag = emailEngine.sendMimeEmail(sendemail);
								   System.out.println("Email sent sucessfully :" + emailFlag);
								   String emailstatus="QAFinalReminder";
								   
									boolean statusupdate=db.updatePartnerEmailStatus(emailstatus,prov_schedule_id);
									System.out.println("statusupdate from FinalReminder"+statusupdate);
							   }
							   
							 
							 
							
							  
							   
				  
			}}
				
				
			System.out.println("Thread sleeping");
			val ="success";
			System.out.println("from run method"+val);
					 }
						
					
			}.start();	
			
			try {
				TimeUnit.MINUTES.sleep(60);
				//sleep(1*24*60*60*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				}
		return val ;

		
			
			}

	
	
	
	
	
	
}

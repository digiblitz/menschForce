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
package com.jobvacancy.requirements;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.xmlrpc.XmlRpcException;

import com.db.GeneralDBAction;
import com.hlccommon.util.Debug;
import com.infusionejb.session.InfusionSessionBean;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import com.util.email.MailMail;

public class RequirementActionThread extends Thread{
	
	private String JobTitle;
	private String LocationField;
	private String Notes;
	private String RID;
	private String Position;
	private String minExperience;
	private String maxExperience;
	private String RequirementStatus;
	private String jobPostCompanyName;
	private String RecruiterEmail;
	
	private InfusionSessionBean obj1;
	
	public RequirementActionThread (String JobTitle,String LocationField,String Notes,String RID,String Position,String minExperience,String maxExperience,String RequirementStatus,String jobPostCompanyName,String RecruiterEmail){
		
		System.out.println("Calling Thread Constructor to send requirement mail------------------>");
			/*try{
				obj1 = new InfusionSessionBean();
			}catch(Exception e){
				 throw new RuntimeException(e);
			}*/
		
		
		this.JobTitle = JobTitle;
		this.LocationField = LocationField;
		this.Notes = Notes;
		this.RID = RID;
		this.Position = Position;
		this.minExperience = minExperience;
		this.maxExperience = maxExperience;
		this.RequirementStatus = RequirementStatus;
		this.jobPostCompanyName = jobPostCompanyName;
		this.RecruiterEmail = RecruiterEmail;
	}

	@Override
    public void run(){
		System.out.println("Calling run method in Thread------------------>");
		
		GeneralDBAction db=new GeneralDBAction();
		ArrayList ContactIDS = new ArrayList();
		
		
		ArrayList VendorContactList = new ArrayList();
		try {
			VendorContactList = (ArrayList)db.getAllVendorDetails();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
       	 Iterator itr = VendorContactList.iterator();
			while (itr.hasNext()) {    
		        String TempList[] = (String[])itr.next();
		        String unique_id =TempList[0];
				 String vendor_id=TempList[1];
				 String vendor_fname = TempList[2];
				 String vendor_lname=TempList[3];
				 String vendor_email=TempList[4];
				 String vendor_company=TempList[5];
				 String vendor_homePhone=TempList[6];
				 String vendor_businessPhone=TempList[7];
				 String vendor_contactPerson=TempList[8];
				 //====================================================Email changing from infusion to menschforce.io START HERE====================================================//
				 				 
                  String toMailIds1[] = {vendor_email};// instance toMailds1
                  EmailContent email1 = new EmailContent();// instance email1
                  email1.setTo(toMailIds1);
                  email1.setFrom("jeyaprakash.sankarraj@digiblitz.com");
                  email1.setSubject(Position);
		 
			String htmlBody = "<html><head></head><body><div style=\"background-color: #d8dde4; padding: 32px 10px; text-align:center!important;\"><div style=\"max-width: 580px; text-align: center; margin: 0 auto; width: 100%; display: inline-block; vertical-align: top;\">"+
	        		   " <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;\"><tbody><tr><td align=\"center\" style=\"background-color: #333; border-radius: 4px 4px 0 0;padding-bottom: 25px; text-align: center;\" valign=\"top\">"+
	        		   "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;\"><tbody><tr><td align=\"center\" style=\"padding-top: 16px;\" valign=\"top\"><a class=\"inf-track-no\" href=\"#\"><img alt=\"Logo\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" style=\"height: auto; max-width: 156px;\" /></a>"+
	        		   "</td></tr></tbody></table></td></tr></tbody></table><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;\"><tbody><tr><td align=\"right\" style=\"background-color: #fff; width: 937px;\" valign=\"top\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;\"><tbody>"+
	        		   "<tr><td align=\"center\" style=\"padding: 16px; text-align: center; vertical-align:top;\" valign=\"top\"><h4 style=\"font-size: 20px; font-weight: bold; line-height: 30px; margin: 16px 0 8px; padding: 0; color: #383d42; text-align:left;\"> Dear Staffing Partner,</h4>"+
	        		   "<p style=\"font-size: 15px; line-height: 27px; margin-bottom: 16px; margin-top:16px; text-align: left;\">A new requirement has been posted on menschForce for "+JobTitle+" at "+LocationField+"</p>"+
                  "<p style=\"font-size: 15px; line-height: 15px; margin-bottom: 16px; margin-top:16px; text-align: left;\"><strong>Job Description:</strong></p>"+    
                  "<p style=\"font-size: 15px; margin-bottom: 16px; margin-top:16px; text-align: left;\">"+Notes+"</p>"+
                  "<p style=\"font-size: 15px; line-height: 10px; margin-bottom: 16px; margin-top:16px; text-align: left;\"><font style=\"color: #0000ff;\"><a  href=\"http://www.menschforce.com/ViewRequirementByRIDfromMail.html?RID="+RID+"\" target=\"_blank\">Click here</a></font> to view more job details.</p>"+
                  "<p style=\"font-size: 15px;  margin-bottom: 16px; margin-top:16px; text-align: left;\"> Already a registered vendor with us? <a  href=\"http://www.menschforce.com\" target=\"_blank\">Click here</a> to login and submit your candidates resume.</p>"+
                  "<p style=\"font-size: 15px; line-height: 20px; margin-bottom: 16px; margin-top:16px; text-align: left;\">New User and want to sign up with us to submit your candidates, Use this Coupon Code  <b>'DBFREESUPPLIER'</b> for 30days free trial <a href=\"http://www.menschforce.com/frmHomeForTrial.html\" target=\"_blank\"> Click here</a>.</p>"+
                  " <p style=\"font-size: 15px; line-height: 10px; margin-bottom: 16px; margin-top:16px; text-align: left; color: red;\">Requirement Details:</p>"+           
                  "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 85%; margin: 0 auto;\"><tbody style=\"width: 85%;\"><tr align=\"left\" style=\"padding: 16px; text-align: center; vertical-align:top;\" valign=\"top\"></tr><tr style=\"line-height: 25px;\">"+
                  "<td style=\"width: 50%; text-align: left;\"><strong>Job Title</strong></td><td style=\"width: 50%; text-align: left;\"> "+JobTitle+"</td> </tr><tr style=\"line-height: 25px;\">"+
                  "<td style=\"width: 50%; text-align: left;\"><strong>Requirement ID</strong></td><td style=\"width: 50%; text-align: left;\">"+RID+"</td></tr><tr style=\"line-height: 25px;\">"+
                  "<td style=\"width: 50%; text-align: left;\"><strong>Position</strong></td><td style=\"width: 50%; text-align: left;\">"+Position+"</td></tr><tr style=\"line-height: 25px;\">"+
                  "<td style=\"width: 50%; text-align: left;\"><strong>Location</strong></td><td style=\"width: 50%; text-align: left;\"> "+LocationField+"</td></tr><tr style=\"line-height: 25px;\">"+
                  "<td style=\"width: 50%; text-align: left;\"><strong>Minimum Experience</strong></td><td style=\"width: 50%; text-align: left;\">"+minExperience+"</td></tr><tr style=\"line-height: 25px;\">"+
                  "<td style=\"width: 50%; text-align: left;\"><strong>Maximum Experience</strong></td><td style=\"width: 50%; text-align: left;\">"+maxExperience+"</td></tr><tr style=\"line-height: 25px;\">"+
                  "<td style=\"width: 50%; text-align: left;\"><strong>Requirement Status</strong></td><td style=\"width: 50%; text-align: left;\">"+RequirementStatus+"</td></tr><tr style=\"line-height: 35px; text-align: left;\">"+
                  "<td style=\"text-align: left;\"> Regards,</td></tr><tr style=\"line-height: 10px;\"><td style=\"text-align: left;\">HR "+jobPostCompanyName+".</td></tr><tr style=\"line-height: 15px; text-align: left;\"><td>"+
                  "<p></p></td></tr><tr style=\"line-height: 15px; text-align: left;\"></tr></tbody></table><p align=\"left\"><font size=\"2\"><strong>For trial sign up and other related queries, please contact jeyaprakash@digiblitz.in</strong></font>"+
                  "</p></td></tr></tbody></table></td></tr></tbody></table></div></div></body></html>"; 
				 
			// email1.setBody(htmlBody);	 
			// EmailEngine mail1 = new EmailEngine();
           //  boolean emailFlag1 = mail1.sendMimeEmail(email1);
           //  Debug.print("Email sent sucessfully while posting requirements :" + emailFlag1);	 
             
             
             
             email1.setBody(htmlBody);// content=htmlBoady
				
				MailMail mail = new MailMail();
				boolean emailFlag1 = mail.sendMimeEmail(email1);
				Debug.print("Email sent sucessfully :" + emailFlag1);
             
             
				 
			}	 
			
			
	}
    
	
}

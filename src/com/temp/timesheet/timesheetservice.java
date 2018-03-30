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
package com.temp.timesheet;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
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

@WebService(endpointInterface="com.temp.timesheet.timesheetinterface")
public class timesheetservice extends Thread implements timesheetinterface {

	Calendar cal  = Calendar.getInstance();
	boolean executed=false;
	 
	GeneralDBAction db=new GeneralDBAction();
	
	 ArrayList emplist = (ArrayList)db.getAllEmployeeDetails();
	 
	 String project_status=null;
	 String empEmailId=null;
	 String firstname=null;
	 String lastname=null;
	 
	 String val=null;
	private InfusionSessionBean obj1;
		{
			try{
				obj1 = new InfusionSessionBean();
			}catch(Exception e){
				 throw new RuntimeException(e);
			}
		}
		
		@Override
	public String timesheetremainder( String input) {
		
		
		
		boolean status = true;
		
		while(status){
		System.out.println("Thread alive check");
		new Thread(){
		boolean check = checkremainderdate();
		
		
		public void run(){
			
			if(check){	
				
				SimpleDateFormat format2 = new SimpleDateFormat("EEE");
				SimpleDateFormat format3 = new SimpleDateFormat("MM-dd-yyyy");
				Calendar cal  = Calendar.getInstance();
				String sunday=null;
				String saturday=null;
				String period1=null;
				String period=null;
				
			for(int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++) {
				
				cal.set(Calendar.DAY_OF_WEEK, i);
				  
				
				  period1=format2.format(cal.getTime());
				
				 if (i== Calendar.SUNDAY)
				 {
					 sunday = format3.format(cal.getTime());
					System.out.println("sunday date"+sunday);
					}
				
				if(i == Calendar.SATURDAY)
				 {
					saturday = format3.format(cal.getTime());
					System.out.println("saturday date"+saturday);
				 }
				
				period=sunday +" to "+saturday;
				
			}
				
				
				
				if(emplist!=null && emplist.size()!=0){
					 Iterator itr = emplist.iterator();
					    while (itr.hasNext()) {    
					    	String TempList[] = (String[])itr.next();
					    	firstname=TempList[8];
					    	lastname=TempList[10];
					    	 empEmailId=TempList[14];
					    	 project_status=TempList[51];
					    	 System.out.println("empEmailId in timesheet remainder----"+empEmailId);
					    	 String name=firstname+""+lastname;
				System.out.println("project status===="+project_status);
				  if(project_status.equalsIgnoreCase("UnderProject"))
				  {
					String htmlbodytocandidate = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
		                    "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
		                    "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
		                    " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
		                    "<tr><td  valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 25px; margin: 16px 0 8px;padding: 0;text-align: left;\">Dear <span style=\"font-weight:bold\"> "+name+"</span>,</h4></td> </tr><tr></tr> <tr><td style=\"padding:0px 10px\"><p style=\"margin: 20px 0 10px;text-align:left;\">"+
		                    "A timesheet should be submitted for the week "+period+".</br> Please ignore if already done. <b></b> Click here to login and submit <a href=\"http://www.menschforce.com\">www.menschforce.com</a></td></tr><tr><td style=\"padding:0px 10px\"><p style=\"line-height: 20px;text-align:left;\"><span style=\"font-weight:bold\"> </span></p></td></tr><tr><td style=\"padding:0px 10px\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>MenschForce Team</td></tr></tbody> </table> </td> </tr> </tbody> </table> </div></div></div></div></body></html>";
		   		
		   	
		   		try {
		   			
		   			
		   			obj1.sendEmail("reminder@menschforce.com", empEmailId , "", "priya.karunanidhi@digiblitz.in", "Html", "Timesheet Reminder ",htmlbodytocandidate,"" );
		   			} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  }
			}}
				
				
			System.out.println("Thread sleeping");
			val ="success";
			System.out.println("from run method"+val);
					 }
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

	
	public boolean checkremainderdate(){
	
		int a=cal.get(Calendar.DAY_OF_WEEK);
		System.out.println("day of the week"+ a);
		if(a==6 || a==4){
		
		return true;
		}else{
			System.out.println("timehseet remainder  not send");
			return false;
		}
		
	}
}

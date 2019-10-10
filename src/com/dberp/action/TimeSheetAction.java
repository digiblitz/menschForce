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
package com.dberp.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.xmlrpc.XmlRpcException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;

import com.db.GeneralDBAction;
import com.hlccommon.util.Debug;
import com.infusionejb.session.InfusionSessionBean;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

@Controller
public class TimeSheetAction {

	public static final String config_prop = "config.properties";
	public static final String LOG_FILE = "Log4j.properties";
	InputStream is;
	InputStream config_inputStream;
	
	private InfusionSessionBean obj1;
	{
		try{
			obj1 = new InfusionSessionBean();
		}catch(Exception e){
			 throw new RuntimeException(e);
		}
	}
	
	
	@RequestMapping("CreateTimesheet.html")
	 public ModelAndView frmHomeForTrial(HttpServletRequest request,
	   HttpServletResponse response) throws SQLException, IOException{
		
	 HttpSession session=request.getSession(true); 
	 ArrayList createnewtimesheet=new ArrayList();
	 String empuniqueid=request.getParameter("uniqueId");
	 String empUserId=null;
		String empId=null;
		String empdberpPartyid=null;
		String empSSNNo=null;
		String project_name=null;
		String client_name=null;
		String week_period=null;
		String overtime_hrs=null;
		String sick_hrs=null;
		String total_hrs=null;
		String timesheet_path=null;
		String hr_status=null;
		String period=null;
		String saturday=null;
		String sunday=null;
		String timesheet_id=null;		
		String rejectionstatus =null;
		String firstname=null;
		String lastname=null;
		
	
	   
	 request.setAttribute("uniqueId",empuniqueid);
	 GeneralDBAction db=new GeneralDBAction();
	 ArrayList empList=db.getAllEmployeeDetailsByUniqueId(empuniqueid);
	   if(empList!=null && empList.size()!=0){
			Iterator itr = empList.iterator();
		    while (itr.hasNext()) {    
		    	String TempList[] = (String[])itr.next();
		    	 empUserId =TempList[1];
		    	 empId = TempList[3];
		    	  empdberpPartyid = TempList[4];
		    	  empSSNNo = TempList[5];
		    	  client_name=TempList[43];
		    	  project_name=TempList[45];
		    
		    }}
	   
	   Calendar cal = Calendar.getInstance();
		
		SimpleDateFormat format1 = new SimpleDateFormat("MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("EEE");
		SimpleDateFormat format3 = new SimpleDateFormat("MM-dd-yyyy");
		String period1=null;
		String text=null;
	for(int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++) {
		
		cal.set(Calendar.DAY_OF_WEEK, i);
		  
		
		  period1=format2.format(cal.getTime());
		 text = format1.format(cal.getTime());
		System.out.println(text);
		System.out.println(period1);
		
		String TempList[] = {period1,text};
		createnewtimesheet.add(TempList);
		
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
		period=sunday + " to " +saturday;
	}
	   
	   ArrayList getEmployeeTimesheet=db.getEmployeeTimesheet(empuniqueid,period);
	   if(getEmployeeTimesheet!=null && getEmployeeTimesheet.size()!=0){
			Iterator itrs = getEmployeeTimesheet.iterator();
		    while (itrs.hasNext()) {    
		    	
		    	
		    	String TempList[] = (String[])itrs.next();
		    	empId =TempList[0];
		    	empdberpPartyid = TempList[1];
		    	empSSNNo = TempList[2];
		    	week_period = TempList[3];
		    	overtime_hrs=TempList[4];
		    	sick_hrs	=TempList[5];
		    	total_hrs = TempList[6];
		    	timesheet_path = TempList[7];
		    	hr_status = TempList[8];
		    	timesheet_id=TempList[9];
		    	rejectionstatus=TempList[10];
		    
		    }}
	   
	   String userId=(String)session.getAttribute("userId");
		  boolean payDurationStatus = false;
			payDurationStatus = db.checkEmpTimesheetDurationExist(userId,period);
			
	   System.out.println("project name from createtimesheet==>"+project_name);
	   System.out.println("client name from createtimesheet==>"+client_name);
	   
	   System.out.println("empId from createtimesheet==>"+empId);
	   System.out.println("empdberpPartyid from createtimesheet==>"+empdberpPartyid);
	   System.out.println("empSSNNo from createtimesheet==>"+empSSNNo);
	   System.out.println("week_period from createtimesheet==>"+week_period);
	   System.out.println("overtime_hrs from createtimesheet==>"+overtime_hrs);
	   System.out.println("sick_hrs from createtimesheet==>"+sick_hrs);
	   System.out.println("total_hrs from createtimesheet==>"+total_hrs);
	   System.out.println("hr_status from createtimesheet==>"+hr_status);
	   System.out.println("timesheet_id from createtimesheet==>"+timesheet_id);
	   
	   request.setAttribute("client_name",client_name);
	   request.setAttribute("project_name",project_name);
	   request.setAttribute("pageStatusvalue","createTimesheet");
	   request.setAttribute("empList",empList);
	   request.setAttribute("createnewtimesheet",createnewtimesheet);
	   request.setAttribute("sunday",sunday);
	   request.setAttribute("saturday",saturday);
	  request.setAttribute("getEmployeeTimesheet",getEmployeeTimesheet);
	  if(payDurationStatus)
		 {
		 request.setAttribute("Durationstatus","Durationstatus");
		 }
	   String rolename=(String)session.getAttribute("roleName");
		System.out.println("rolename."+rolename);
	 
	 if(rolename != null && rolename.equalsIgnoreCase("Consultant"))
	 {
		 return new ModelAndView ("requirements/frmCreateTimesheet");
	 }
	 else
	 {
		 return new ModelAndView ("requirements/frmViewTimesheet");
	 }
	 }
	
	@RequestMapping("/Timesheet.html")
	public ModelAndView Timesheet(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("RequirementsAction.Timesheet()");
		String uniqueEmployeeId = null;
		
		String stPeriod2=null;
		String stPeriod3=null;
		String stPeriod4=null;
		String stPeriod5=null;
		String stPeriod6=null;
		String stPeriod7=null;
		String stPeriod8=null;
		String stPeriod9=null;
		
		
		String OTPeriod2=null;
		String OTPeriod3=null;
		String OTPeriod4=null;
		String OTPeriod5=null;
		String OTPeriod6=null;
		String OTPeriod7=null;
		String OTPeriod8=null;
		String OTPeriod9=null;
		
		
		String timeOff2=null;
		String timeOff3=null;
		String timeOff4=null;
		String timeOff5=null;
		String timeOff6=null;
		String timeOff7=null;
		String timeOff8=null;
		String timeOff9=null;
		
		String status=null;
		String payperiodfrom=null;
		String payperiodto=null;
		String filepath=null;
		String empUserId=null;
		String empId=null;
		String empdberpPartyid=null;
		String empSSNNo=null;
		String project_name=null;
		String client_name=null;
		String timesheet_id=null;
		String firstname=null;
		String lastname=null;
		
		uniqueEmployeeId = request.getParameter("uniqueId");
		ArrayList empList=db.getAllEmployeeDetailsByUniqueId(uniqueEmployeeId);
		   if(empList!=null && empList.size()!=0){
				Iterator itr = empList.iterator();
			    while (itr.hasNext()) {    
			    	String TempList[] = (String[])itr.next();
			    	 empUserId =TempList[1];
			    	 empId = TempList[3];
			    	  empdberpPartyid = TempList[4];
			    	  empSSNNo = TempList[5];
			    	  client_name=TempList[43];
			    	  project_name=TempList[45];
			    
			    }
		   }
		   
		   Properties config_property = new Properties();
			config_inputStream = getClass().getClassLoader().getResourceAsStream(config_prop);

			if (config_inputStream != null) {
				config_property.load(config_inputStream);
			} else {
				throw new FileNotFoundException("property file '" + config_prop + "' not found in the classpath");
			}
				
				
				  String fileLocation = null;
			 
	    	   try {
	    		 
	    		   
	    		  
	    		  
	        	  // String status = null;	
	        	   
	        	   String UPLOAD_DIRECTORY = null;
	        	   UPLOAD_DIRECTORY = config_property.getProperty("config.Timesheet");
	        	   List<FilePart> fileList = new ArrayList<FilePart>();
	        	   
		    	   if (!(new File(UPLOAD_DIRECTORY)).exists()) {
				    	(new File(UPLOAD_DIRECTORY)).mkdir();    // creates the directory if it does not exist        
				    }
				String parmName = "";
				String resp = "";
			    int i = 1;
			    resp += "<br>Here is information about uploaded files.<br>";
			      MultipartParser parser = new MultipartParser(request, 1024 * 1024 * 1024);  /* file limit size of 1GB*/
		            Part _part;
		            int k=0;
		            while ((_part = parser.readNextPart()) != null) {
		            	parmName = _part.getName();
		            	
		            	if(_part.isParam()){
  		                	System.out.println("Inside paramPart");
  		                	ParamPart paramPart = (ParamPart)_part;
  		                	if(parmName.equals("stPeriod2")){
  		                		stPeriod2 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("stPeriod3")){
  		                		stPeriod3 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("stPeriod4")){
  		                		stPeriod4 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("stPeriod5")){
  		                		stPeriod5 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("stPeriod6")){
  		                		stPeriod6 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("stPeriod7")){
  		                		stPeriod7 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("stPeriod8")){
  		                		stPeriod8 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("stPeriod9")){
  		                		stPeriod9 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("OTPeriod2")){
  		                		OTPeriod2 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("OTPeriod3")){
  		                		OTPeriod3 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("OTPeriod4")){
  		                		OTPeriod4 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("OTPeriod5")){
  		                		OTPeriod5 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("OTPeriod6")){
  		                		OTPeriod6 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("OTPeriod7")){
  		                		OTPeriod7 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("OTPeriod8")){
  		                		OTPeriod8 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("OTPeriod9")){
  		                		OTPeriod9 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("timeOff2")){
  		                		timeOff2 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("timeOff3")){
  		                		timeOff3 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("timeOff4")){
  		                		timeOff4 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("timeOff5")){
  		                		timeOff5 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("timeOff6")){
  		                		timeOff6 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("timeOff7")){
  		                		timeOff7 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("timeOff8")){
  		                		timeOff8 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("timeOff9")){
  		                		timeOff9 = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("fromdate")){
  		                		payperiodfrom = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	if(parmName.equals("todate")){
  		                		payperiodto = paramPart.getStringValue();
  		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
  		                		
  		                	}
  		                	
  		                	
  		                	
  		                	
  		                	
  		                	
  		                	
  		                	
		            	}
  		                	
		            
			                
			                if(_part.isFile()){
			                	System.out.println("Inside _part.getName() in upload timesheet action--------->"+_part.getName());
			                	 FilePart fPart = (FilePart) _part;  // get some info about the file
				                    String name = fPart.getFileName();
				                    System.out.println("filename"+name);
						          if (name != null ) {
						        	  System.out.println("fPart.getFileName() in upload timesheet action--------->"+name);
						        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
						        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
						        	  fileLocation = UPLOAD_DIRECTORY+name;
						        	  //Access_fileFile = fileLocation;
						        	  System.out.println("filelocation in upload timesheet"+fileLocation);
						          }
					          
		            }
			               
		              
		        }}

	    	   catch (IOException ioe) {
		        	ioe.printStackTrace();
		        	throw new Exception(ioe);
		        }catch (NullPointerException npe) {
		        	npe.printStackTrace();
		        	throw new Exception(npe);
		        }catch (MultipartException mpe) {
		        	mpe.printStackTrace();
		        	throw new Exception(mpe);
		        }  
	    	  
		   
		  
				System.out.println("projectname===="+project_name);  
				System.out.println("client_name===="+client_name); 
		status=request.getParameter("status");
		System.out.println("status===="+status); 
		
		
		
		filepath = request.getParameter("filepath");
	
		 String payperiod =payperiodfrom+" to "+payperiodto;  
		 String straight_time= stPeriod2+ ","+stPeriod3+ ","+stPeriod4+ ","+stPeriod5+ ","+stPeriod6+ ","+stPeriod7+ ","+stPeriod8+ ","+stPeriod9;
		 String over_time= OTPeriod2+ ","+OTPeriod3+ ","+OTPeriod4+ ","+OTPeriod5+ ","+OTPeriod6+ ","+OTPeriod7+ ","+OTPeriod8+ ","+OTPeriod9;
		 String time_Off= timeOff2+ ","+timeOff3+ ","+timeOff4+ ","+timeOff5+ ","+timeOff6+ ","+timeOff7+ ","+timeOff8+ ","+timeOff9;
		
		 String userId=(String)session.getAttribute("userId");
			System.out.println("userId."+userId);
		 boolean payDurationStatus = false;
			payDurationStatus = db.checkEmpTimesheetDurationExist(userId,payperiod);
			
			
		System.out.println("STTotalHours=====>"+stPeriod9);
		
		System.out.println("OTTotalHours=====>"+OTPeriod9);
		
		System.out.println("TimeoffActualhours=====>"+timeOff9);
		
		System.out.println("filepath=====>"+fileLocation);
		
		System.out.println("unique id==============>"+uniqueEmployeeId);
		
		 ArrayList createTimesheet = new ArrayList();
		 boolean timesheetStatus = false;
		 createTimesheet = (ArrayList)db.insertTimesheet(empUserId,empId,empdberpPartyid,empSSNNo,payperiod,over_time,time_Off,straight_time,fileLocation);
		 ArrayList employeeList = new ArrayList();
			employeeList = (ArrayList)db.getAllEmployeeDetails();
			
			if(employeeList!=null && employeeList.size()!=0){
				 Iterator itr = employeeList.iterator();
				    while (itr.hasNext()) {    
				    	String TempList[] = (String[])itr.next();
				    	firstname=TempList[8];
				    	lastname=TempList[10];
				    }
			}
			
			String name=firstname+""+lastname;
			 
	        System.out.println("name form timesheet"+name);
	        
				    
	        
			String htmlbodytocandidate = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
                    "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
                    "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
                    " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
                    "<tr><td  valign=\"top\" style=\"  padding: 0px 10px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 18px;font-weight: 400;line-height: 25px; margin: 16px 0 8px;padding: 0;text-align: left;\">Dear HR,</h4></td> </tr><tr></tr> <tr><td style=\"padding:0px 10px\"><p style=\"margin: 20px 0 10px;text-align:left;\">"+
                    "A new timesheet has been submitted by " + name +" for the period of <b> "+payperiod+".</b> You can access web timesheet at <a href=\"http://www.menschforce.com\">www.menschforce.com</a></td></tr><tr><td style=\"padding:0px 10px\"><p style=\"line-height: 20px;text-align:left;\">Employee ID : <span style=\"font-weight:bold\"> "+empId+"</span></p></td></tr>"+
                    "<tr><td style=\"padding:0px 10px\"><p style=\"line-height: 20px;text-align:left;\">Employee Name : <span style=\"font-weight:bold\"> "+name+"</span></p></td></tr><tr><td style=\"padding:0px 10px\"><p style=\"line-height: 20px;text-align:left;\">Regards,<br>MenschForce Team</td></tr></tbody> </table> </td> </tr> </tbody> </table> </div></div></div></div></body></html>";
   		
			try {
				
				ArrayList Recruiter = new ArrayList();
				 Recruiter = db.getAllUserEmailBasedOnRoleName("HR");
					   Iterator itr1 = Recruiter.iterator();
					    while (itr1.hasNext()) {    
					        String TempList[] = (String[])itr1.next();
					        String ManagerEmailId= TempList[2];
			     System.out.println("ManagerEmailId=============="+ManagerEmailId);
				        obj1.createAndCheckDuplicateContact("", "", ManagerEmailId);
				        obj1.sendEmail("timesheet@menschforce.com",ManagerEmailId,"", "priya.karunanidhi@digiblitz.in", "Html", "Timesheet is waiting for approval",htmlbodytocandidate,"" );
				   
					    }
				    
			} catch (XmlRpcException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				    
			   	 request.setAttribute("employeeList",null);
			     request.setAttribute("employeeList",employeeList);
		   		request.setAttribute("client_name",client_name);
			    request.setAttribute("project_name",project_name);
			    request.setAttribute("empList",empList);
			    request.setAttribute("createTimesheet",createTimesheet);
			    
	       
	        request.setAttribute("pageStatus","read");
	        if(payDurationStatus)
	   	 {
	   	 request.setAttribute("Durationstatus","Durationstatus");
	   	 }
	        String rolename=(String)session.getAttribute("roleName");
	        if(rolename != null && rolename.equalsIgnoreCase("Consultant"))
	      	 {
	           	
	        	ArrayList employeeDetails = new ArrayList();
	           	employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUserId(userId);
	           	 request.setAttribute("employeeDetails",employeeDetails);
	      		 return new ModelAndView ("requirements/frmEmployeeTimesheet");
	      		 
	      	 }
	        else
	        {
			return new ModelAndView("frmEmployeeList");
	        }		
		
	}
	
	@RequestMapping("/updateTimesheet.html")
	public ModelAndView updateTimesheet(HttpServletRequest request,
			HttpServletResponse response) throws Exception{

		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		
	String uniqueEmployeeId=null;
	String empUserId=null;
	String empId=null;
	String empdberpPartyid=null;
	String empSSNNo=null;
	
	String week_period=null;
	String overtime_hrs=null;
	String sick_hrs=null;
	String total_hrs=null;
	String timesheet_path=null;
	String hr_status=null;
	String timesheet_id=null;
	
		
		String stPeriod2=null;
		String stPeriod3=null;
		String stPeriod4=null;
		String stPeriod5=null;
		String stPeriod6=null;
		String stPeriod7=null;
		String stPeriod8=null;
		String stPeriod9=null;
		
		
		String OTPeriod2=null;
		String OTPeriod3=null;
		String OTPeriod4=null;
		String OTPeriod5=null;
		String OTPeriod6=null;
		String OTPeriod7=null;
		String OTPeriod8=null;
		String OTPeriod9=null;
		
		
		String timeOff2=null;
		String timeOff3=null;
		String timeOff4=null;
		String timeOff5=null;
		String timeOff6=null;
		String timeOff7=null;
		String timeOff8=null;
		String timeOff9=null;
		
		String status=null;
		String payperiodfrom=null;
		String payperiodto=null;
		String filepath=null;
		
		String project_name=null;
		String client_name=null;
		
		uniqueEmployeeId = request.getParameter("uniqueId");
		ArrayList empList=db.getAllEmployeeDetailsByUniqueId(uniqueEmployeeId);
		   if(empList!=null && empList.size()!=0){
				Iterator itr = empList.iterator();
			    while (itr.hasNext()) {    
			    	String TempList[] = (String[])itr.next();
			    	 empUserId =TempList[1];
			    	 empId = TempList[3];
			    	  empdberpPartyid = TempList[4];
			    	  empSSNNo = TempList[5];
			    	  client_name=TempList[43];
			    	  project_name=TempList[45];
			    
			    }
		   }
		   
		   Properties config_property = new Properties();
			config_inputStream = getClass().getClassLoader().getResourceAsStream(config_prop);

			if (config_inputStream != null) {
				config_property.load(config_inputStream);
			} else {
				throw new FileNotFoundException("property file '" + config_prop + "' not found in the classpath");
			}
				
				
				  String fileLocation = null;
			 
	    	   try {
	    		 
	    		   
	    		  
	    		  
	        	  // String status = null;	
	        	   
	        	   String UPLOAD_DIRECTORY = null;
	        	   UPLOAD_DIRECTORY = config_property.getProperty("config.Timesheet");
	        	   List<FilePart> fileList = new ArrayList<FilePart>();
	        	   
		    	   if (!(new File(UPLOAD_DIRECTORY)).exists()) {
				    	(new File(UPLOAD_DIRECTORY)).mkdir();    // creates the directory if it does not exist        
				    }
				String parmName = "";
				String resp = "";
			    int i = 1;
			    resp += "<br>Here is information about uploaded files.<br>";
			      MultipartParser parser = new MultipartParser(request, 1024 * 1024 * 1024);  /* file limit size of 1GB*/
		            Part _part;
		            int k=0;
		            while ((_part = parser.readNextPart()) != null) {
		            	parmName = _part.getName();
		            	
		            	if(_part.isParam()){
 		                	System.out.println("Inside paramPart");
 		                	ParamPart paramPart = (ParamPart)_part;
 		                	if(parmName.equals("stPeriod2")){
 		                		stPeriod2 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("stPeriod3")){
 		                		stPeriod3 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("stPeriod4")){
 		                		stPeriod4 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("stPeriod5")){
 		                		stPeriod5 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("stPeriod6")){
 		                		stPeriod6 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("stPeriod7")){
 		                		stPeriod7 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("stPeriod8")){
 		                		stPeriod8 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("stPeriod9")){
 		                		stPeriod9 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("OTPeriod2")){
 		                		OTPeriod2 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("OTPeriod3")){
 		                		OTPeriod3 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("OTPeriod4")){
 		                		OTPeriod4 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("OTPeriod5")){
 		                		OTPeriod5 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("OTPeriod6")){
 		                		OTPeriod6 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("OTPeriod7")){
 		                		OTPeriod7 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("OTPeriod8")){
 		                		OTPeriod8 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("OTPeriod9")){
 		                		OTPeriod9 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("timeOff2")){
 		                		timeOff2 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("timeOff3")){
 		                		timeOff3 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("timeOff4")){
 		                		timeOff4 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("timeOff5")){
 		                		timeOff5 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("timeOff6")){
 		                		timeOff6 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("timeOff7")){
 		                		timeOff7 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("timeOff8")){
 		                		timeOff8 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("timeOff9")){
 		                		timeOff9 = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("fromdate")){
 		                		payperiodfrom = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("todate")){
 		                		payperiodto = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	if(parmName.equals("status")){
 		                		status = paramPart.getStringValue();
 		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
 		                		
 		                	}
 		                	
 		                	
 		                	
 		                	
 		                	
 		                	
 		                	
 		                	
		            	}
 		                	
		            
			                
			                if(_part.isFile()){
			                	System.out.println("Inside _part.getName() in upload timesheet action--------->"+_part.getName());
			                	 FilePart fPart = (FilePart) _part;  // get some info about the file
				                    String name = fPart.getFileName();
				                    System.out.println("filename"+name);
						          if (name != null ) {
						        	  System.out.println("fPart.getFileName() in upload timesheet action--------->"+name);
						        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
						        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
						        	  fileLocation = UPLOAD_DIRECTORY+name;
						        	  //Access_fileFile = fileLocation;
						        	  System.out.println("filelocation in upload timesheet"+fileLocation);
						          }
					          
		            }
			               
		              
		        }}

	    	   catch (IOException ioe) {
		        	ioe.printStackTrace();
		        	throw new Exception(ioe);
		        }catch (NullPointerException npe) {
		        	npe.printStackTrace();
		        	throw new Exception(npe);
		        }catch (MultipartException mpe) {
		        	mpe.printStackTrace();
		        	throw new Exception(mpe);
		        }  
	    	  
		  
				System.out.println("projectname===="+project_name);  
				System.out.println("client_name===="+client_name); 
		
		System.out.println("status===="+status); 
	
	
		
		 String payperiod =payperiodfrom+" to "+payperiodto;  
		 String straight_time= stPeriod2+ ","+stPeriod3+ ","+stPeriod4+ ","+stPeriod5+ ","+stPeriod6+ ","+stPeriod7+ ","+stPeriod8+ ","+stPeriod9;
		 String over_time= OTPeriod2+ ","+OTPeriod3+ ","+OTPeriod4+ ","+OTPeriod5+ ","+OTPeriod6+ ","+OTPeriod7+ ","+OTPeriod8+ ","+OTPeriod9;
		 String time_Off= timeOff2+ ","+timeOff3+ ","+timeOff4+ ","+timeOff5+ ","+timeOff6+ ","+timeOff7+ ","+timeOff8+ ","+timeOff9;
		
		 String userId=(String)session.getAttribute("userId");
			System.out.println("userId."+userId);
		 boolean payDurationStatus = false;
			payDurationStatus = db.checkEmpTimesheetDurationExist(userId,payperiod);
			
			
		System.out.println("STTotalHours=====>"+stPeriod9);
		
		System.out.println("OTTotalHours=====>"+OTPeriod9);
		
		System.out.println("TimeoffActualhours=====>"+timeOff9);
		
		System.out.println("filepath=====>"+fileLocation);
		
		System.out.println("unique id==============>"+uniqueEmployeeId);
		
		ArrayList getEmployeeTimesheet=db.getEmployeeTimesheet(uniqueEmployeeId,payperiod);
		   if(getEmployeeTimesheet!=null && getEmployeeTimesheet.size()!=0){
				Iterator itrs = getEmployeeTimesheet.iterator();
			    while (itrs.hasNext()) {    
			    	
			    	
			    	String TempList[] = (String[])itrs.next();
			    	
			    	timesheet_id=TempList[9];
			    
			    }}
		   
		   ArrayList employeeList = new ArrayList();
			employeeList = (ArrayList)db.getAllEmployeeDetails();
		ArrayList updateTimesheet = (ArrayList)db.updateTimesheetByUniqueId(
				payperiod,over_time,time_Off,straight_time,fileLocation,status,timesheet_id);
		 request.setAttribute("empList",empList);
		 request.setAttribute("updateTimesheet",updateTimesheet);
		    
		 request.setAttribute("employeeList",null);
	     request.setAttribute("employeeList",employeeList);
	     request.setAttribute("pageStatus","update");
	     if(payDurationStatus)
		 {
		 request.setAttribute("Durationstatus","Durationstatus");
		 }    
	     String rolename=(String)session.getAttribute("roleName");
	        if(rolename != null && rolename.equalsIgnoreCase("Consultant"))
	      	 {
	           	
	        	ArrayList employeeDetails = new ArrayList();
	           	employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUserId(userId);
	           	 request.setAttribute("employeeDetails",employeeDetails);
	      		 return new ModelAndView ("requirements/frmEmployeeTimesheet");
	      		 
	      	 }
	        else
	        {
			return new ModelAndView("frmEmployeeList");
	        }		
	        }
		
	
	@RequestMapping("/updateTimesheetByHR.html")
	public ModelAndView updateTimesheetByHR(HttpServletRequest request,
			HttpServletResponse response) throws Exception{

		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		
		String uniqueEmployeeId=null;
		
		String hr_status=null;
		String timesheet_id=null;
		String rejectionReason=null;
		
		
		String empUserId=null;
		String empId=null;
		String empdberpPartyid=null;
		String empSSNNo=null;
		
		String week_period=null;
		String overtime_hrs=null;
		String sick_hrs=null;
		String total_hrs=null;
		String timesheet_path=null;
		
			
			
			String status=null;
			String payperiodfrom=null;
			String payperiodto=null;
			String filepath=null;
			
			String project_name=null;
			String client_name=null;
			
			uniqueEmployeeId = request.getParameter("uniqueId");
			ArrayList empList=db.getAllEmployeeDetailsByUniqueId(uniqueEmployeeId);
			   if(empList!=null && empList.size()!=0){
					Iterator itr = empList.iterator();
				    while (itr.hasNext()) {    
				    	String TempList[] = (String[])itr.next();
				    	 empUserId =TempList[1];
				    	 empId = TempList[3];
				    	  empdberpPartyid = TempList[4];
				    	  empSSNNo = TempList[5];
				    	  client_name=TempList[43];
				    	  project_name=TempList[45];
				    
				    }
			   }
		
		
	
		  status=request.getParameter("status");
		  rejectionReason=request.getParameter("reasonforrejection");
		 uniqueEmployeeId=request.getParameter("uniqueId");
		 payperiodfrom=request.getParameter("fromdate");
		 payperiodto=request.getParameter("todate");
		 String payperiod =payperiodfrom+" to "+payperiodto;  
		System.out.println("payperiod========"+payperiod);
		
		System.out.println("unique id==============>"+uniqueEmployeeId);
		System.out.println("rejectionReason=============>"+rejectionReason);
		
		ArrayList getEmployeeTimesheet=db.getEmployeeTimesheet(uniqueEmployeeId,payperiod);
		   if(getEmployeeTimesheet!=null && getEmployeeTimesheet.size()!=0){
				Iterator itrs = getEmployeeTimesheet.iterator();
			    while (itrs.hasNext()) {    
			    	
			    	
			    	String TempList[] = (String[])itrs.next();
			    	
			    	timesheet_id=TempList[9];
			    
			    }}
		   
		  
			
		   System.out.println("timesheet_id"+timesheet_id);
		   System.out.println("status===="+status); 
		   ArrayList employeeList = new ArrayList();
			employeeList = (ArrayList)db.getAllEmployeeDetails();
			
			 boolean statusbyHR = false;
			 statusbyHR = db.updateTimesheetByHR(status,rejectionReason,timesheet_id);
		/*ArrayList updateTimesheet = (ArrayList)db.updateTimesheetByUniqueId(
				payperiod,over_time,time_Off,straight_time,fileLocation,status,timesheet_id);
		 request.setAttribute("empList",empList);
		 request.setAttribute("updateTimesheet",updateTimesheet);*/
			 request.setAttribute("empList",empList);
		 request.setAttribute("employeeList",null);
	     request.setAttribute("employeeList",employeeList);
	     request.setAttribute("statusbyHR",statusbyHR);
	     request.setAttribute("pageStatus","updateHR");
	  	    
			    	return new ModelAndView("frmEmployeeList");  
				
			    
	        }
	
		
	
	@RequestMapping("/PreviousTimesheet.html")
	public ModelAndView PreviousTimesheet(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList previoustimesheet = new ArrayList();
		//ArrayList nexttimesheet = new ArrayList();
		
		Debug.print("RequirementsAction.PreviousTimesheet()");
        Calendar cal = Calendar.getInstance();
		
		SimpleDateFormat format1 = new SimpleDateFormat("MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("EEE");
		SimpleDateFormat format3 = new SimpleDateFormat("MM-dd-yyyy");
		String previous_date=null;
		String previous_day=null;
		String previous_val=null;
		
		String empUserId=null;
		String empId=null;
		String empdberpPartyid=null;
		String empSSNNo=null;
		String project_name=null;
		String client_name=null;
		String saturday=null;
		String sunday=null;
		String week_period=null;
		String overtime_hrs=null;
		String sick_hrs=null;
		String total_hrs=null;
		String timesheet_path=null;
		String hr_status=null;
		String period=null;
		String timesheet_id=null;
		 String uniqueEmployeeId = request.getParameter("uniqueId");
		 ArrayList empList=db.getAllEmployeeDetailsByUniqueId(uniqueEmployeeId);
		   if(empList!=null && empList.size()!=0){
				Iterator itr = empList.iterator();
			    while (itr.hasNext()) {    
			    	String TempList[] = (String[])itr.next();
			    	 empUserId =TempList[1];
			    	 empId = TempList[3];
			    	  empdberpPartyid = TempList[4];
			    	  empSSNNo = TempList[5];
			    	  client_name=TempList[43];
			    	  project_name=TempList[45];
			    
			    }}
		int value=0;
		previous_val = request.getParameter("previous_val");
		System.out.println("previous_val before parsing==="+previous_val);
		
		int previousweek=Integer.parseInt(previous_val);
		System.out.println("previous_val==="+previousweek);
		
		value=previousweek+7;
		System.out.println("value ---> "+value);
		
		
		
		
		 int j = cal.get(Calendar.DAY_OF_WEEK) - cal.getFirstDayOfWeek();
		 System.out.println((Calendar.DATE));
		 cal.add(Calendar.DATE, -j - value );
		 
	for(int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++) {
		
		 
		//cal.add(Calendar.DATE, 6);
		cal.set(Calendar.DAY_OF_WEEK, i);
		  
		
		previous_date=format2.format(cal.getTime());
		  previous_day = format1.format(cal.getTime());
		System.out.println(previous_day);
		System.out.println(previous_date);
		
		String TempList[] = {previous_date,previous_day};
		 previoustimesheet.add(TempList);
		 
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
		
		period =sunday+ " to "+saturday; 
		
		
	}
	
	
	  ArrayList getEmployeeTimesheet=db.getEmployeeTimesheet(uniqueEmployeeId,period);
	  if(getEmployeeTimesheet!=null && getEmployeeTimesheet.size()!=0){
			Iterator itrs = getEmployeeTimesheet.iterator();
		    while (itrs.hasNext()) {    
		    	
		    	
		    	String TempList[] = (String[])itrs.next();
		    	empId =TempList[0];
		    	empdberpPartyid = TempList[1];
		    	empSSNNo = TempList[2];
		    	week_period = TempList[3];
		    	overtime_hrs=TempList[4];
		    	sick_hrs	=TempList[5];
		    	total_hrs = TempList[6];
		    	timesheet_path = TempList[7];
		    	hr_status = TempList[8];
		    	timesheet_id=TempList[9];
		    
		    }}
	  String userId=(String)session.getAttribute("userId");
	  boolean payDurationStatus = false;
		payDurationStatus = db.checkEmpTimesheetDurationExist(userId,period);
		
		System.out.println("uniqueEmployeeId frm previoustimesheet====="+uniqueEmployeeId);
		
		String rolename=(String)session.getAttribute("roleName");
		System.out.println("rolename."+rolename);
		 
	
	String tempvalue=Integer.toString(value);
	System.out.println("tempvalue -> "+tempvalue);
	
	 request.setAttribute("tempvalue",tempvalue);
	 request.setAttribute("pageStatus","previousdate");  
	 request.setAttribute("client_name",client_name);
	 request.setAttribute("project_name",project_name);
	  request.setAttribute("empList",empList);
	 request.setAttribute("uniqueId",uniqueEmployeeId);
	 request.setAttribute("previoustimesheet",previoustimesheet);
	 request.setAttribute("sunday",sunday);
	   request.setAttribute("saturday",saturday);
	
	 request.setAttribute("getEmployeeTimesheet",getEmployeeTimesheet);
	 if(payDurationStatus)
	 {
	 request.setAttribute("Durationstatus","Durationstatus");
	 }
	 if(rolename != null && rolename.equalsIgnoreCase("Consultant"))
	 {
		 return new ModelAndView ("requirements/frmCreateTimesheet");
	 }
	 else
	 {
		 return new ModelAndView ("requirements/frmViewTimesheet");
	 }
		 
	
	
	}
	
	@RequestMapping("/NextTimesheet.html")

	public ModelAndView NextTimesheet(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList nexttimesheet = new ArrayList();
		
		Debug.print("RequirementsAction.NextTimesheet()");
        Calendar cal = Calendar.getInstance();
		
		SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("EEE");
		 SimpleDateFormat format3 = new SimpleDateFormat("MM-dd");
		 
		
		String Next_date=null;
		String Next_day=null;
		String Next_day_val=null;
		String Next_val=null;
		
		String empUserId=null;
		String empId=null;
		String empdberpPartyid=null;
		String empSSNNo=null;
		String project_name=null;
		String client_name=null;
		String saturday=null;
		String sunday=null;
		String week_period=null;
		String overtime_hrs=null;
		String sick_hrs=null;
		String total_hrs=null;
		String timesheet_path=null;
		String hr_status=null;
		String period=null;
		String timesheet_id=null;
		
		
		 String uniqueEmployeeId = request.getParameter("uniqueId");
		 ArrayList empList=db.getAllEmployeeDetailsByUniqueId(uniqueEmployeeId);
		   if(empList!=null && empList.size()!=0){
				Iterator itr = empList.iterator();
			    while (itr.hasNext()) {    
			    	String TempList[] = (String[])itr.next();
			    	 empUserId =TempList[1];
			    	 empId = TempList[3];
			    	  empdberpPartyid = TempList[4];
			    	  empSSNNo = TempList[5];
			    	  client_name=TempList[43];
			    	  project_name=TempList[45];
			    
			    }}
		
		
		Next_val = request.getParameter("Next_val");
		System.out.println("Next_val before parsing==="+Next_val);
		int value=0;
		int Nextweek=Integer.parseInt(Next_val);
		System.out.println("Nextweek==="+Nextweek);
		
		 int j = cal.get(Calendar.DAY_OF_WEEK) - cal.getFirstDayOfWeek();
		 System.out.println((Calendar.DATE));
		 cal.add(Calendar.DATE, -j - Nextweek );
		 
	for(int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++) {
		
		 
		//cal.add(Calendar.DATE, 6);
		cal.set(Calendar.DAY_OF_WEEK, i);
		  
		
		Next_date=format2.format(cal.getTime());
		Next_day = format1.format(cal.getTime());
		System.out.println(Next_day);
		System.out.println(Next_date);
		
		
		
		
		if(i == Calendar.SATURDAY)
		 {
			Next_day = format1.format(cal.getTime());
		 }
		
		 
	}
	for(int k = Calendar.SUNDAY; k <= Calendar.SATURDAY; k++)
	 {
	
		
		cal.setTime(format1.parse(Next_day));
		cal.add(Calendar.DATE, 1); // number of days to add
		
		if (k == Calendar.SUNDAY)
		 {
			sunday = format1.format(cal.getTime());
			 System.out.println("sunday date from next"+sunday);
			}
	
	  if (k == Calendar.SATURDAY)
	 {
		saturday = format1.format(cal.getTime());
		 System.out.println("satuday date from next"+saturday);
		}
	  
		Next_day = format1.format(cal.getTime());
		Next_day_val = format3.format(cal.getTime());
		Next_date = format2.format(cal.getTime());
		System.out.println(Next_day_val);
		period =sunday+ " to "+saturday; 
		
		
		String TempList[] = {Next_date,Next_day_val};
		nexttimesheet.add(TempList);
	 }
	
	 ArrayList getEmployeeTimesheet=db.getEmployeeTimesheet(uniqueEmployeeId,period);
	  if(getEmployeeTimesheet!=null && getEmployeeTimesheet.size()!=0){
			Iterator itrs = getEmployeeTimesheet.iterator();
		    while (itrs.hasNext()) {    
		    	
		    	
		    	String TempList[] = (String[])itrs.next();
		    	empId =TempList[0];
		    	empdberpPartyid = TempList[1];
		    	empSSNNo = TempList[2];
		    	week_period = TempList[3];
		    	overtime_hrs=TempList[4];
		    	sick_hrs	=TempList[5];
		    	total_hrs = TempList[6];
		    	timesheet_path = TempList[7];
		    	hr_status = TempList[8];
		    	timesheet_id=TempList[9];
		    
		    }}
	
	
	  String userId=(String)session.getAttribute("userId");
	  boolean payDurationStatus = false;
		payDurationStatus = db.checkEmpTimesheetDurationExist(userId,period);
		
	
		System.out.println("uniqueEmployeeId frm previoustimesheet====="+uniqueEmployeeId);
		
		 
	value=Nextweek-7;
	System.out.println("value ---> "+value);
	String tempvalue=Integer.toString(value);
	System.out.println("tempvalue -> "+tempvalue);
	 
	 request.setAttribute("tempvalue",tempvalue);
	 request.setAttribute("pageStatus","nextdate"); 
	 request.setAttribute("client_name",client_name);
	 request.setAttribute("project_name",project_name);
	 request.setAttribute("empList",empList);
	 request.setAttribute("uniqueId",uniqueEmployeeId);
	 request.setAttribute("nexttimesheet",nexttimesheet);
	 request.setAttribute("sunday",sunday);
	   request.setAttribute("saturday",saturday);
	 request.setAttribute("getEmployeeTimesheet",getEmployeeTimesheet);
	 if(payDurationStatus)
	 {
	 request.setAttribute("Durationstatus","Durationstatus");
	 }
	 String rolename=(String)session.getAttribute("roleName");
		System.out.println("rolename."+rolename);
	 
	 if(rolename != null && rolename.equalsIgnoreCase("Consultant"))
	 {
		 return new ModelAndView ("requirements/frmCreateTimesheet");
	 }
	 else
	 {
		 return new ModelAndView ("requirements/frmViewTimesheet");
	 }
	
	}
	
	
	
	
	@RequestMapping("/SearchDate.html")

	public ModelAndView SearchDate(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		
		Debug.print("RequirementsAction.SearchDate()");
        Calendar cal = Calendar.getInstance();
       

		String empUserId=null;
		String empId=null;
		String empdberpPartyid=null;
		String empSSNNo=null;
		String project_name=null;
		String client_name=null;
		String saturday=null;
		String sunday=null;
		String week_period=null;
		String overtime_hrs=null;
		String sick_hrs=null;
		String total_hrs=null;
		String timesheet_path=null;
		String hr_status=null;
		String period=null;
		String timesheet_id=null;
        
		
		
		SimpleDateFormat format2 = new SimpleDateFormat("EEE");
		 SimpleDateFormat format3 = new SimpleDateFormat("MM-dd");
		 
		 String startdate=request.getParameter("goto");
		 System.out.println("selected date from search===="+startdate);
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(startdate));
			startdate = sdf.format(c.getTime());
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
			c.add(Calendar.DAY_OF_MONTH, -dayOfWeek);
			Date firstDate = c.getTime();
			 sunday = sdf.format(c.getTime());
			ArrayList createnewtimesheet =new ArrayList();
			for(int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++) {
					
					c.set(c.DAY_OF_WEEK, i);
					  
					
					String  period1=format2.format(c.getTime());
					String text = format3.format(c.getTime());
					System.out.println(text);
					System.out.println(period1);
					
					String TempList[] = {period1,text};
					createnewtimesheet.add(TempList);
			  }
			//c.add(Calendar.DATE, 6);
			System.out.println("sunday date----"+sunday);
	         saturday = sdf.format(c.getTime());
		    System.out.println("saturday date from next"+saturday);
		    String uniqueEmployeeId = request.getParameter("uniqueId");
		     period=sunday+ " to "+ saturday;
		     System.out.println("period============="+period);
		     System.out.println("uniqueEmployeeId============="+uniqueEmployeeId);
		     ArrayList empList=db.getAllEmployeeDetailsByUniqueId(uniqueEmployeeId);
			   if(empList!=null && empList.size()!=0){
					Iterator itr = empList.iterator();
				    while (itr.hasNext()) {    
				    	String TempList[] = (String[])itr.next();
				    	 empUserId =TempList[1];
				    	 empId = TempList[3];
				    	  empdberpPartyid = TempList[4];
				    	  empSSNNo = TempList[5];
				    	  client_name=TempList[43];
				    	  project_name=TempList[45];
				    
				    }}
		    ArrayList getEmployeeTimesheet=db.getEmployeeTimesheet(uniqueEmployeeId,period);
			  if(getEmployeeTimesheet!=null && getEmployeeTimesheet.size()!=0){
					Iterator itrs = getEmployeeTimesheet.iterator();
				    while (itrs.hasNext()) {    
				    	
				    	
				    	String TempList[] = (String[])itrs.next();
				    	empId =TempList[0];
				    	empdberpPartyid = TempList[1];
				    	empSSNNo = TempList[2];
				    	week_period = TempList[3];
				    	overtime_hrs=TempList[4];
				    	sick_hrs	=TempList[5];
				    	total_hrs = TempList[6];
				    	timesheet_path = TempList[7];
				    	hr_status = TempList[8];
				    	timesheet_id=TempList[9];
				    
				    }}
			  
			
			
			  request.setAttribute("getEmployeeTimesheet",getEmployeeTimesheet);
			  request.setAttribute("empList",empList);
			  request.setAttribute("uniqueId",uniqueEmployeeId);
			  request.setAttribute("client_name",client_name);
			  request.setAttribute("createnewtimesheet",createnewtimesheet);
				 request.setAttribute("project_name",project_name);
				 request.setAttribute("pageStatus","searchdate");
				 request.setAttribute("sunday",sunday);
				   request.setAttribute("saturday",saturday);
			  String rolename=(String)session.getAttribute("roleName");
				System.out.println("rolename."+rolename);
			 
			 if(rolename != null && rolename.equalsIgnoreCase("Consultant"))
			 {
				 return new ModelAndView ("requirements/frmCreateTimesheet");
			 }
			 else
			 {
				 return new ModelAndView ("requirements/frmViewTimesheet");
			 }
		
		
	}
	
	
}

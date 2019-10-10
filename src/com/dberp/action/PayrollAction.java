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

import java.rmi.RemoteException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.servlet.http.HttpSession;
import com.db.GeneralDBAction;
import com.db.exceptionhandling.CustomGenericException;
import com.dberp.payroll.session.DbPayrollERPSessionBean;
import com.hlccommon.util.Debug;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;
import com.infusionejb.session.InfusionSessionBean;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


@Controller
public class PayrollAction {
	
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
	
	@SuppressWarnings("unused")
	private static final Font ALIGN_RIGHT = null;
	
	
	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping("/ListEmployeeDetails.html")
	public ModelAndView ListEmployeeDetails(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		
		Debug.print("PayrollAction.ListEmployeeDetails()");
		ArrayList employeeList = new ArrayList();
		employeeList = (ArrayList)db.getAllEmployeeDetails();
		ArrayList employeeDetails = new ArrayList();
        request.setAttribute("employeeList",null);
        request.setAttribute("employeeList",employeeList);
        request.setAttribute("pageStatus","read");
        String rolename=(String)session.getAttribute("roleName");
        String userId = (String)session.getAttribute("userId");
        if(rolename != null && rolename.equalsIgnoreCase("Consultant"))
   	 {
        	
        
        	employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUserId(userId);
        	 request.setAttribute("employeeDetails",employeeDetails);
   		 return new ModelAndView ("requirements/frmEmployeeTimesheet");
   		 
   	 }
       
        else
        {
		return new ModelAndView("frmEmployeeList");
        }
        
      
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping("/ViewListEmployeeDetailsByUniqueId.html")
	public ModelAndView ViewListEmployeeDetailsByUniqueId(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("RequirementsAction.ViewListEmployeeDetailsByUniqueId()");
		String uniqueEmployeeId = null;
		uniqueEmployeeId = request.getParameter("uniqueId");
		
		if(uniqueEmployeeId != null){
	        ArrayList employeeDetails = new ArrayList();
	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(uniqueEmployeeId);
	        request.setAttribute("employeeDetails",null);
	        request.setAttribute("employeeDetails",employeeDetails);
	        request.setAttribute("pageStatus","update");
			return new ModelAndView("frmViewEmployeeDetails");
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@SuppressWarnings({ "unused" })
	@RequestMapping("/initPayrollFormUpload.html")
	public ModelAndView initPayrollFormUpload(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		try{
			GeneralDBAction db=new GeneralDBAction();
			HttpSession session=request.getSession(true); 
	 	   	System.out.println("Inside initFedStateUploadfile process** ");
	 	   	
	 	    String userId = (String)session.getAttribute("userId");
	 	    String roleId = db.getRoleIdByUserId(userId);
	 	    System.out.println("userId Inside initFedStateUploadfile process**  : "+userId);
	 	    System.out.println("roleId Inside initFedStateUploadfile process**  : "+roleId);
	 	    
	 	    ArrayList fedStateFormDetails = null;
	 	    ArrayList employeeDetails = new ArrayList();
	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUserId(userId);
	        if(employeeDetails != null && employeeDetails.size() != 0 && roleId.equalsIgnoreCase("574CC000-1E33-43F9-B5C6-5A86BE56F269")){
	        Iterator itr = employeeDetails.iterator();
			    while (itr.hasNext()) {    
				    String TempList[] = (String[])itr.next();
				    String empuniqueId = TempList[0];
				    db.makeConnection();
			        if(db.checkEmployeeFedStateFormExist(empuniqueId)){
			        	db.releaseConnection();
			        	fedStateFormDetails = new ArrayList();
			        	fedStateFormDetails = db.getFederalStateFormDetails(empuniqueId);
			        	request.setAttribute("employeeDetails",null);
				        request.setAttribute("employeeDetails",employeeDetails);
				        request.setAttribute("fedStateFormDetails",fedStateFormDetails);
				        request.setAttribute("pageStatus","edit");
			 	    	return new ModelAndView("requirements/frmFederalStateFormUpl");	
			        }else{
				 	    request.setAttribute("employeeDetails",null);
					    request.setAttribute("employeeDetails",employeeDetails);
					    request.setAttribute("pageStatus","init");
				 	    return new ModelAndView("requirements/frmFederalStateFormUpl");	
			        }
			    }
	        }else{
	 	    	throw new Exception("No Permission to Upload the Federal and State Witholding Form");
	 	    }
		}catch (Exception e) {
			throw new Exception(e);
		}
		return null;
	}
	
	@SuppressWarnings({ "unused" })
	@RequestMapping("/payrollFormUpload.html")
	public ModelAndView payrollFormUpload(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		//config properties
	    Properties config_property = new Properties();
	    config_inputStream = getClass().getClassLoader().getResourceAsStream(config_prop);

			if (config_inputStream != null) {
				config_property.load(config_inputStream);
			} else {
				System.out.println("inside throw exception handling------------------>");
				throw new FileNotFoundException("property file '" + config_prop + "' not found in the classpath");
			}
			
				HttpSession session=request.getSession(true); 
			  	long creationTime = session.getCreationTime();
		        String sessionId = session.getId();
		        long lastAccessedTime = session.getLastAccessedTime();
		        Date createDate= new Date(creationTime);
		        Date lastlogin= new Date(lastAccessedTime);
		        System.out.println("lastAccessedDate"+lastlogin); 
		        System.out.println("createDate"+createDate); 
		        
		        GeneralDBAction db=new GeneralDBAction();
			
		        ArrayList fedStateFormDetails = null;
		 	    ArrayList employeeDetails = new ArrayList();
		        
		
		//========================Dhivya Start Here: Federal & State form Upload===========	   		             	   
		     
		   String empuniqueId = null;
		   String empSSNNo = null;
		   String taxYear = null;
		   String country = null;
		   String state = null;
		   
		   String uploadfederalnewCheck = null;
		   String oldfileupload = null;
		   String uploadstatenewCheck = null;
		   String oldfileupload1 = null;
		   String customuploadnewCheck = null;
		   String oldfile = "";
		   
		   String federalWHFile = null;
		   String stateWHFile = null;
		   String otherFile = "";
		   
		   
		   ArrayList <String>fedStateFileList = new ArrayList<String>();
    	   
    	   String parmName = "";
    	   String fileSavePath;
    	   String status = "";	
    	   String UPLOAD_DIRECTORY =config_property.getProperty("config.federalStateWHForm");
    	   List<FilePart> fileList = new ArrayList<FilePart>();
		 
    	   fileSavePath =  UPLOAD_DIRECTORY;
		        if (!(new File(fileSavePath)).exists()) {
		            (new File(fileSavePath)).mkdir();        
		        } 
    	       
		        try {
		            MultipartParser parser = new MultipartParser(request, 1024 * 1024 * 1024); 
		            
		            Part _part;
		            int k=0;
		            while ((_part = parser.readNextPart()) != null) {
		            	System.out.println("LOOP START"+_part.getName());
		            	parmName = _part.getName();
		            	if(_part.isParam()){
		                	System.out.println("Inside paramPart");
		                	ParamPart paramPart = (ParamPart)_part;
		                	if(parmName.equals("empuniqueId")){
		                		empuniqueId = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("empSSNNo")){
		                		empSSNNo = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("taxYear")){
		                		taxYear = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("country")){
		                		country = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("state")){
		                		state = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("uploadfederalnewCheck")){
		                		uploadfederalnewCheck = paramPart.getStringValue();
		                		System.out.println("uploadfederalnewCheck----- :::: "+paramPart.getStringValue());
		                	}
		                	if(parmName.equals("oldfileupload")){
		                		oldfileupload = paramPart.getStringValue();
		                		System.out.println("oldfileupload----- :::: "+paramPart.getStringValue());
		                	}
		                	if(parmName.equals("uploadstatenewCheck")){
		                		uploadstatenewCheck = paramPart.getStringValue();
		                		System.out.println("uploadstatenewCheck----- :::: "+paramPart.getStringValue());
		                	}
		                	if(parmName.equals("oldfileupload1")){
		                		oldfileupload1 = paramPart.getStringValue();
		                		System.out.println("oldfileupload1----- :::: "+paramPart.getStringValue());
		                	}
		                	if(parmName.equals("customuploadnewCheck")){
		                		customuploadnewCheck = paramPart.getStringValue();
		                		System.out.println("customuploadnewCheck----- :::: "+paramPart.getStringValue());
		                	}
		                	if(parmName.equals("oldfile")){
		                		oldfile = paramPart.getStringValue();
		                		System.out.println("oldfile----- :::: "+paramPart.getStringValue());
		                	}
		                	
		                }// end If
		            	
		                if (_part.isFile()) {
		                	
		                    FilePart fPart = (FilePart) _part;  
		                    String name = fPart.getFileName();
		                    System.out.println("name in the PayrollAction **** "+name);
		                    System.out.println("FilePart.getName()::::::::::::::::::::"+fPart.getName());
		                    fileList.add(fPart);
		        
		                    if (name != null && fPart.getName().equalsIgnoreCase("fileupload")) {
		                        long fileSize = fPart.writeTo(new File(fileSavePath));
		                        String saveFile=fileSavePath+name;		                    
					            //status = "success";
					            //System.out.println("status from servlet : "+status);
					            //fedStateFileList.add(saveFile);
		                        federalWHFile = saveFile;
		                    } else if (name != null && fPart.getName().equalsIgnoreCase("fileupload1")) {
		                        long fileSize = fPart.writeTo(new File(fileSavePath));
		                        String saveFile=fileSavePath+name;		                    
					            //status = "success";
					            //System.out.println("status from servlet : "+status);
					            //fedStateFileList.add(saveFile);
		                        stateWHFile = saveFile;
		                    }else if (name != null && fPart.getName().equalsIgnoreCase("file")) {
		                        long fileSize = fPart.writeTo(new File(fileSavePath));
		                        String saveFile=fileSavePath+name;		                    
					            //status = "success";
					            //System.out.println("status from servlet : "+status);
					            //fedStateFileList.add(saveFile);
		                        otherFile = saveFile;
		                    }
		                    
		                    if((uploadfederalnewCheck == null || uploadfederalnewCheck == "" || uploadfederalnewCheck.equalsIgnoreCase("")) &&
		                    		(name == null || name.equalsIgnoreCase("") || name.equalsIgnoreCase("null")) &&
		                    		(fPart.getName().equalsIgnoreCase("fileupload"))){
			                      //resp = "<br>The user did not upload a file for this part.";
					        	  System.out.println("oldfileupload-------------in-----if uploadfederalnewCheck--action--->"+oldfileupload);
					        	  //System.out.println("oldAdditionalfile-------------in-----else--Stage1 action--->"+oldAdditionalfile);
					        	  //fedStateFileList.add(oldfileupload);
					        	  federalWHFile = oldfileupload;
			             }
					    if((uploadstatenewCheck == null || uploadstatenewCheck == "" || uploadstatenewCheck.equalsIgnoreCase(""))
					        	  && (name == null || name.equalsIgnoreCase("") || name.equalsIgnoreCase("null")) &&
					        	  (fPart.getName().equalsIgnoreCase("fileupload1"))){
			                      //resp = "<br>The user did not upload a file for this part.";
					        	  //System.out.println("oldVisaform-------------in-----else--Stage1 action--->"+oldVisaform);
					        	  System.out.println("oldfileupload1-------------in-----if uploadstatenewCheck--action--->"+oldfileupload1);
					        	  //dosFileList.add(oldVisaform);
					        	  //fedStateFileList.add(oldfileupload1);
					        	  stateWHFile = oldfileupload1;
			             }
					    if((customuploadnewCheck == null || customuploadnewCheck == "" || customuploadnewCheck.equalsIgnoreCase(""))
					        	  && (name == null || name.equalsIgnoreCase("") || name.equalsIgnoreCase("null")) && 
					        	  (fPart.getName().equalsIgnoreCase("file"))){
			                      //resp = "<br>The user did not upload a file for this part.";
					        	  //System.out.println("oldVisaform-------------in-----else--Stage1 action--->"+oldVisaform);
					        	  System.out.println("oldfile-------------in-----if customuploadnewCheck--action--->"+oldfile);
					        	  //dosFileList.add(oldVisaform);
					        	  //fedStateFileList.add(oldfile);
					        	  otherFile = oldfile;
			             }
		                }
		            }// end while 
		            
		            boolean insertStatus = false;
		            boolean updateEmpMastTblStatus = false;
		            if(empuniqueId != null && empSSNNo != null && taxYear != null && country != null
		            		&& state != null && federalWHFile != null && stateWHFile != null){
		            	System.out.println("Inside if ------->");
			            insertStatus = db.insertFederalStateFormDetails(empuniqueId,empSSNNo,taxYear,country,state,federalWHFile,stateWHFile,otherFile);
			            updateEmpMastTblStatus = db.updateFedStateEmpMastTbl(empuniqueId,insertStatus);
			            System.out.println("insertStatus in PayrollAction::::::::"+insertStatus);
			            if(insertStatus && updateEmpMastTblStatus){
			            	status = "success";
			            	request.setAttribute("status",status);
			            	request.setAttribute("pageStatus","edit");
			            	fedStateFormDetails = new ArrayList();
				        	fedStateFormDetails = db.getFederalStateFormDetails(empuniqueId);
				        	employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empuniqueId);
				        	request.setAttribute("fedStateFormDetails",fedStateFormDetails);
					        request.setAttribute("employeeDetails",employeeDetails);
			         	   return new ModelAndView("requirements/frmFederalStateFormUpl");
			            }else{
			            	status = "fail";
			            	request.setAttribute("status",status);
			            	request.setAttribute("pageStatus","init");
			            	employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empuniqueId);
					        request.setAttribute("employeeDetails",employeeDetails);
			         	   return new ModelAndView("requirements/frmFederalStateFormUpl");
			            }
		            }else{
		            	throw new Exception("With the parameters");
		            }
		        }catch (IOException ioe) {
		        	ioe.printStackTrace();
		        	throw new Exception(ioe);
		        }catch (NullPointerException npe) {
		        	npe.printStackTrace();
		        	throw new Exception(npe);
		        }catch (MultipartException mpe) {
		        	mpe.printStackTrace();
		        	throw new Exception(mpe);
		        }
       	
//======================Dhivya End Here=============================================================	
		
	}
	
	@RequestMapping("/viewPayrollFormUpload.html")
	public ModelAndView viewPayrollFormUpload(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		try{
			GeneralDBAction db=new GeneralDBAction();
			HttpSession session=request.getSession(true); 
	 	   	System.out.println("Inside initFedStateUploadfile process** ");
	 	   	
	 	   	ArrayList fedStateFormDetails = null;
	 	   	
	 	   String empuniqueId = request.getParameter("empUniqueId");
	 	   
	 	   ArrayList employeeDetails = new ArrayList();
	       employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empuniqueId);
	 	   	
			db.makeConnection();
		    if(db.checkEmployeeFedStateFormExist(empuniqueId)){
		    	db.releaseConnection();
		    	fedStateFormDetails = new ArrayList();
		    	fedStateFormDetails = db.getFederalStateFormDetails(empuniqueId);
		    	request.setAttribute("employeeDetails",null);
			    request.setAttribute("employeeDetails",employeeDetails);
			    request.setAttribute("fedStateFormDetails",fedStateFormDetails);
			    request.setAttribute("pageStatus","view");
			 	return new ModelAndView("requirements/frmFederalStateFormUpl");	
		    }else{
		    	throw new Exception("Employee not uploaded the Federal and state form!");	
		    }
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	@RequestMapping("/initPayrollCalc.html")
	public ModelAndView initPayrollCalc(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		double gen_pay_cp = 0.0;
		double ot_pay_cp = 0.0;
		double fed_pay_cp = 0.0;
		double ss_pay_cp = 0.0;
		double mc_pay_cp = 0.0;
		double state_pay_cp = 0.0;
		double after_tax_income_cp = 0.0;
		double after_tax_deduction_cp = 0.0;
		
		Debug.print("RequirementsAction.initPayrollCalc()");
		String uniqueEmployeeId = null;
		uniqueEmployeeId = request.getParameter("uniqueId");
		if(uniqueEmployeeId == null){
			throw new Exception("Employee Not Found!");
			}else{
				ArrayList employeeDetails = new ArrayList();
				ArrayList employeeYTDAmount = new ArrayList();
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(uniqueEmployeeId);
		        
		        employeeYTDAmount = db.getAllYTDAmountByUniqueId(uniqueEmployeeId);
		        if(employeeYTDAmount!=null && employeeYTDAmount.size()!=0){
					Iterator itr = employeeYTDAmount.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    gen_pay_cp = gen_pay_cp + Double.parseDouble(TempList[1]);
	        			fed_pay_cp = fed_pay_cp + Double.parseDouble(TempList[3]);
	        			ss_pay_cp = ss_pay_cp + Double.parseDouble(TempList[4]);
	        			mc_pay_cp = mc_pay_cp + Double.parseDouble(TempList[5]);
	        			state_pay_cp = state_pay_cp + Double.parseDouble(TempList[6]);
	        			after_tax_income_cp = after_tax_income_cp + Double.parseDouble(TempList[7]);
	        			after_tax_deduction_cp = after_tax_deduction_cp + Double.parseDouble(TempList[8]);
	        			
		        			if(TempList[2] != null || TempList[2] != "" || !(TempList[2].equalsIgnoreCase(""))){
		        			String otString_cp = TempList[2];
		        			String []otArray_cp = otString_cp.split(",");
			        			for(int i=0;i<otArray_cp.length;i++){
			        			ot_pay_cp = ot_pay_cp + Double.parseDouble(otArray_cp[i]);
			        			}
		        			}
				    }
		        }
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        
		        System.out.println("gen_pay_cp-------------------"+gen_pay_cp);
		        System.out.println("ot_pay_cp-------------------"+ot_pay_cp);
		        System.out.println("fed_pay_cp-------------------"+fed_pay_cp);
		        System.out.println("ss_pay_cp-------------------"+ss_pay_cp);
		        System.out.println("mc_pay_cp-------------------"+mc_pay_cp);
		        System.out.println("state_pay_cp-------------------"+state_pay_cp);
		        System.out.println("after_tax_income_cp-------------------"+after_tax_income_cp);
		        System.out.println("after_tax_deduction_cp-------------------"+after_tax_deduction_cp);
		        
		        request.setAttribute("gen_pay_cp_ytd",String.valueOf(gen_pay_cp));
		        request.setAttribute("ot_pay_cp_ytd",String.valueOf(ot_pay_cp));
		        request.setAttribute("fed_pay_cp_ytd",String.valueOf(fed_pay_cp));
		        request.setAttribute("ss_pay_cp_ytd",String.valueOf(ss_pay_cp));
		        request.setAttribute("mc_pay_cp_ytd",String.valueOf(mc_pay_cp));
		        request.setAttribute("state_pay_cp_ytd",String.valueOf(state_pay_cp));
		        request.setAttribute("after_tax_income_cp_ytd",String.valueOf(after_tax_income_cp));
		        request.setAttribute("after_tax_deduction_cp_ytd",String.valueOf(after_tax_deduction_cp));
		        
		        request.setAttribute("pageStatus","init");
		        request.setAttribute("status",null);
				return new ModelAndView("requirements/frmPayrollCalculator");
			}
	}
	
	@SuppressWarnings("unused")
	@RequestMapping("/printPayStub.html")
	public ModelAndView printPayStub(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(true); 
		DbPayrollERPSessionBean payRollBean=new DbPayrollERPSessionBean();
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("RequirementsAction.initPayrollCalc()");
		String uniqueEmployeeId = null;
		String uniquePayrollId = null;
		
		ArrayList employeeDetails = new ArrayList();
		uniqueEmployeeId = request.getParameter("uniqueId");
		uniquePayrollId = request.getParameter("payrollId");
		
		if(uniqueEmployeeId == null){
			throw new Exception("Employee Not Found!");
			}else{
				
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(uniqueEmployeeId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("uniquePayrollId",uniquePayrollId);
			}
		
		String chequeNo = null;
		String chequeDate = null;
		double netAmount = 0.0;
		double social_security_amount = 0.0;
		double medicare_amount = 0.0;
		double total_ot_amount = 0.0;
		String taxyear = null;
		String state = null;
		String NofExemptions = null;
		String age_exemptions = null;
		String additional_withholding = null;
		String fWHITMarital = null;
		String fWHITNoFAllowances = null;
		String PayPeriod = null;
		String tax_paydurationFrom = null;
		String tax_paydurationTo = null;
		String PayRateType = null;
		String overtime = null;
		String Hourly_payrate =null;
		String total_hours = null;
		String YTD_Hourly = null;
		String total_gross_pay_hourly = null;
		//String Monthly_payrate = null;
		//String YTD_Monthly = null;
		//String total_gross_pay_monthly = null;
		
		String federal_it_rate = null;
		String federal_it_annual = null;
		String federal_it_ytd = null;
		String federal_it_cp = null;
		String social_rate = null;
		String social_annual = null;
		String social_ytd = null;
		String social_cp = null;
		String Medicare_rate = null;
		String Medicare_annual = null;
		String Medicare_ytd = null;
		String Medicare_cp = null;
		String State_IT_rate = null;
		String State_IT_annual = null;
		String State_IT_ytd = null;
		String State_IT_cp = null;
		String After_Tax_Income = null;
		String After_Tax_Income_YTD = null;
		String After_Tax_Income_CP = null;
		String After_Tax_deduction = null;
		String After_Tax_deduction_YTD = null;
		String After_Tax_deduction_CP = null;
		
		String totalGross = null;
		String ot_row_count = null;
		double StateWHITAmount = 0;
		int personalAllowances = 0;
		int dependentAllowances = 0;
		String filingStatus = null;
		int totalAllowances = 0;
		double additionalStateWithHolding = 0;
		int additionalAllowances = 0;
		String texarnaResident = null;
		String withHoldingCode = null;
		int basicAllowances = 0;
		int personalDeduction = 0;
		int dependentDeduction = 0;
		int WHExemptions = 0;
		String RateCode = null;
		
		String SpouseBlind = null;
		String PersonalBlind = null;
		String HouseHold = null;
		String Student = null;
		
		int extendedAllowances = 0;
		int regularAllowances = 0;
		double exemptionAmount = 0;
		double WithholdingRate = 0;
		double DistrictRate = 0;
		int DistrictNo = 0;
		double schoolDistITAmount = 0.0;
		
	
		taxyear = (String) request.getParameter("taxyear");
		state = (String) request.getParameter("state");
		
		chequeNo = (String) request.getParameter("chequeNo");
		chequeDate = (String) request.getParameter("chequeDate");
		
		
		fWHITMarital = (String) request.getParameter("fWHITMarital");
		fWHITNoFAllowances = (String) request.getParameter("fWHITNoFAllowances");
		PayPeriod = (String) request.getParameter("PayPeriod");
		tax_paydurationFrom = (String)request.getParameter("payDurationFrom");
		tax_paydurationTo = (String)request.getParameter("payDurationTo");
		
		if(uniqueEmployeeId != null && tax_paydurationFrom != null && tax_paydurationTo != null
				&& !(tax_paydurationFrom.equalsIgnoreCase("null")) && !(tax_paydurationTo.equalsIgnoreCase("null"))
				&& !(tax_paydurationFrom.equalsIgnoreCase("")) && !(tax_paydurationTo.equalsIgnoreCase(""))){
			boolean payDurationStatus = false;
			payDurationStatus = db.checkEmpPayDurationExist(uniqueEmployeeId,tax_paydurationFrom,tax_paydurationTo);
			if(payDurationStatus){
				double gen_pay_cp = 0.0;
				double ot_pay_cp = 0.0;
				double fed_pay_cp = 0.0;
				double ss_pay_cp = 0.0;
				double mc_pay_cp = 0.0;
				double state_pay_cp = 0.0;
				double after_tax_income_cp = 0.0;
				double after_tax_deduction_cp = 0.0;
				
				ArrayList employeeYTDAmount = new ArrayList();
		        
		        employeeYTDAmount = db.getAllYTDAmountByUniqueId(uniqueEmployeeId);
		        if(employeeYTDAmount!=null && employeeYTDAmount.size()!=0){
					Iterator itr = employeeYTDAmount.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    gen_pay_cp = gen_pay_cp + Double.parseDouble(TempList[1]);
	        			fed_pay_cp = fed_pay_cp + Double.parseDouble(TempList[3]);
	        			ss_pay_cp = ss_pay_cp + Double.parseDouble(TempList[4]);
	        			mc_pay_cp = mc_pay_cp + Double.parseDouble(TempList[5]);
	        			state_pay_cp = state_pay_cp + Double.parseDouble(TempList[6]);
	        			after_tax_income_cp = after_tax_income_cp + Double.parseDouble(TempList[7]);
	        			after_tax_deduction_cp = after_tax_deduction_cp + Double.parseDouble(TempList[8]);
	        			
		        			if(TempList[2] != null || TempList[2] != "" || !(TempList[2].equalsIgnoreCase(""))){
		        			String otString_cp = TempList[2];
		        			String []otArray_cp = otString_cp.split(",");
			        			for(int i=0;i<otArray_cp.length;i++){
			        			ot_pay_cp = ot_pay_cp + Double.parseDouble(otArray_cp[i]);
			        			}
		        			}
				    }
		        }
		        
		        System.out.println("gen_pay_cp-------------------"+gen_pay_cp);
		        System.out.println("ot_pay_cp-------------------"+ot_pay_cp);
		        System.out.println("fed_pay_cp-------------------"+fed_pay_cp);
		        System.out.println("ss_pay_cp-------------------"+ss_pay_cp);
		        System.out.println("mc_pay_cp-------------------"+mc_pay_cp);
		        System.out.println("state_pay_cp-------------------"+state_pay_cp);
		        System.out.println("after_tax_income_cp-------------------"+after_tax_income_cp);
		        System.out.println("after_tax_deduction_cp-------------------"+after_tax_deduction_cp);
		        
		        request.setAttribute("gen_pay_cp_ytd",String.valueOf(gen_pay_cp));
		        request.setAttribute("ot_pay_cp_ytd",String.valueOf(ot_pay_cp));
		        request.setAttribute("fed_pay_cp_ytd",String.valueOf(fed_pay_cp));
		        request.setAttribute("ss_pay_cp_ytd",String.valueOf(ss_pay_cp));
		        request.setAttribute("mc_pay_cp_ytd",String.valueOf(mc_pay_cp));
		        request.setAttribute("state_pay_cp_ytd",String.valueOf(state_pay_cp));
		        request.setAttribute("after_tax_income_cp_ytd",String.valueOf(after_tax_income_cp));
		        request.setAttribute("after_tax_deduction_cp_ytd",String.valueOf(after_tax_deduction_cp));
		        
		        request.setAttribute("pageStatus","init");
		        request.setAttribute("status","Pay Duration already Exist for this Employee");
				return new ModelAndView("requirements/frmPayrollCalculator");
			}else{
				
			}
		}
		PayRateType = (String) request.getParameter("PayRateType");
		overtime = (String) request.getParameter("overtime");
		
		total_gross_pay_hourly = (String) request.getParameter("total_gross_pay_hourly");
		
		//total_gross_pay_monthly = (String) request.getParameter("total_gross_pay_monthly");
	
		
		federal_it_cp = (String) request.getParameter("federal_it_cp");
		
		social_cp = (String) request.getParameter("social_cp");
		
		Medicare_cp = (String) request.getParameter("Medicare_cp");
		
		State_IT_cp = (String) request.getParameter("State_IT_cp");
		
		After_Tax_Income_CP = (String) request.getParameter("After_Tax_Income_CP");
		
		After_Tax_deduction_CP = (String) request.getParameter("After_Tax_deduction_CP");
		
		Hourly_payrate = request.getParameter("Hourly_payrate");
		total_hours = request.getParameter("total_hours");
		YTD_Hourly = request.getParameter("YTD_Hourly");
		//Monthly_payrate = request.getParameter("Monthly_payrate");
		//YTD_Monthly = request.getParameter("YTD_Monthly");
		
		ot_row_count = request.getParameter("overtimecount");
		
		if(overtime != null && overtime != "null" && !(overtime.equalsIgnoreCase("null"))){
		System.out.println("ot_row_count-------------->"+ot_row_count);
		int ot_row_count_int = 0;
		ot_row_count_int = Integer.parseInt(ot_row_count);
		String[] overtimerate = new String[ot_row_count_int+1];
		String[] overtimepaytype = new String[ot_row_count_int+1];
		String[] Hour_ot = new String[ot_row_count_int+1];
		String[] ytd_ot = new String[ot_row_count_int+1];
		String[] cp_ot = new String[ot_row_count_int+1];
		
		
		for(int i=0;i<=ot_row_count_int;i++){
			System.out.println("request.getParameter(overtimerate"+i+")"+request.getParameter("overtimerate"+i));
			//System.out.println("overtimerate["+i+"]------------->"+overtimerate[i]);
		overtimerate[i] = request.getParameter("overtimerate"+i);
		overtimepaytype[i] = request.getParameter("overtimepaytype"+i);
		Hour_ot[i] = request.getParameter("Hour_ot"+i);
		ytd_ot[i] = request.getParameter("ytd_ot"+i);
		cp_ot[i] = request.getParameter("cp_ot"+i);
		
		total_ot_amount = (total_ot_amount + Double.parseDouble(cp_ot[i]));
		
		System.out.println("overtimerate["+i+"]---------->"+overtimerate[i]);
		System.out.println("overtimepaytype["+i+"]---------->"+overtimepaytype[i]);
		System.out.println("Hour_ot["+i+"]---------->"+Hour_ot[i]);
		System.out.println("ytd_ot["+i+"]---------->"+ytd_ot[i]);
		System.out.println("cp_ot["+i+"]---------->"+cp_ot[i]);
		
		System.out.println("total_ot_amount-------------->"+total_ot_amount);
		
		request.setAttribute("overtimerate"+i,overtimerate[i]);
		request.setAttribute("overtimepaytype"+i,overtimepaytype[i]);
		request.setAttribute("Hour_ot"+i,Hour_ot[i]);
		request.setAttribute("ytd_ot"+i,ytd_ot[i]);
		request.setAttribute("cp_ot"+i,cp_ot[i]);
		}
		}
		
		federal_it_rate = request.getParameter("federal_it_rate");
		federal_it_annual = request.getParameter("federal_it_annual");
		federal_it_ytd = request.getParameter("federal_it_ytd");
		social_rate = request.getParameter("social_rate");
		social_annual = request.getParameter("social_annual");
		social_ytd = request.getParameter("social_ytd");
		Medicare_rate = request.getParameter("Medicare_rate");
		Medicare_annual = request.getParameter("Medicare_annual");
		Medicare_ytd = request.getParameter("Medicare_ytd");
		State_IT_rate = request.getParameter("State_IT_rate");
		State_IT_annual = request.getParameter("State_IT_annual");
		State_IT_ytd = request.getParameter("State_IT_ytd");
		After_Tax_Income = request.getParameter("After_Tax_Income");
		After_Tax_Income_YTD = request.getParameter("After_Tax_Income_YTD");
		After_Tax_deduction = request.getParameter("After_Tax_deduction");
		After_Tax_deduction_YTD = request.getParameter("After_Tax_deduction_YTD");
		
		/*if(PayRateType != null && PayRateType.equalsIgnoreCase("Hourly")){
			totalGross = total_gross_pay_hourly;
		}else if(PayRateType != null && PayRateType.equalsIgnoreCase("Monthly")){
			totalGross = total_gross_pay_monthly;
		}else{
			throw new Exception("Problem with Total Gross Amount!");
		}
		*/
		
		if(PayRateType != null){
			totalGross = total_gross_pay_hourly;
		}else{
			throw new Exception("Problem with Total Gross Amount!");
		}
		
		totalGross = String.valueOf((Double.parseDouble(totalGross) + total_ot_amount));
		
		/*
		 * Start-------------Calculate federal Withheld Amount-----------*/
		
		double federalWHITAmount = (double)payRollBean.calculateFederalITWithholdAmount(fWHITMarital,Integer.parseInt(fWHITNoFAllowances),Double.parseDouble(totalGross),PayPeriod);
		
		/*
		 * Start-------------Calculate federal Withheld Amount-----------*/
		
		
		
		/*
		 * Start-------------Calculate Social Security Amount and Medicare Amount-----------*/
		
		social_security_amount = (Double.parseDouble(totalGross) * Double.parseDouble(social_rate));
		social_security_amount = Math.round(social_security_amount * 100D) / 100D;
		
		medicare_amount = (Double.parseDouble(totalGross) * Double.parseDouble(Medicare_rate));
		medicare_amount = Math.round(medicare_amount * 100D) / 100D;
		
		social_cp = String.valueOf(social_security_amount);
		Medicare_cp = String.valueOf(medicare_amount);
		
		/*
		 * End-------------Calculate Social Security Amount and Medicare Amount-----------*/
		
		
		if(state != null && state.equalsIgnoreCase("Virginia")){
			NofExemptions = (String) request.getParameter("VI_NofExemptions");
			age_exemptions = (String) request.getParameter("VI_age_exemptions");
			additional_withholding = (String) request.getParameter("VI_additional_withholding");
			
			System.out.println("NofExemptions--------------->"+NofExemptions);
			System.out.println("age_exemptions--------------->"+age_exemptions);
			System.out.println("additional_withholding--------------->"+additional_withholding);
			
			request.setAttribute("VI_NofExemptions",NofExemptions);
			request.setAttribute("VI_age_exemptions",age_exemptions);
			request.setAttribute("VI_additional_withholding",additional_withholding);
			
			
			
			StateWHITAmount = (double)payRollBean.calculateVirginiaStateWithHold(Double.parseDouble(totalGross), PayPeriod,
					Double.parseDouble(additional_withholding), Integer.parseInt(age_exemptions), Integer.parseInt(NofExemptions));
		}else if(state != null && state.equalsIgnoreCase("Georgia")){
			personalAllowances = Integer.parseInt(request.getParameter("personal_allowances"));
			dependentAllowances = Integer.parseInt(request.getParameter("dependent_allowances"));
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("total_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			additionalAllowances = Integer.parseInt(request.getParameter("additional_allowances"));
			
			request.setAttribute("personal_allowances",String.valueOf(personalAllowances));
			request.setAttribute("dependent_allowances",String.valueOf(dependentAllowances));
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("total_allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			request.setAttribute("additional_allowances",String.valueOf(additionalAllowances));
			StateWHITAmount = (double)payRollBean.calculateGeorgiaWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, additionalAllowances, personalAllowances, dependentAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Arkansas")){
			totalAllowances = Integer.parseInt(request.getParameter("total_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			texarnaResident = request.getParameter("texarna_resident");
			
			request.setAttribute("total_allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			request.setAttribute("texarna_resident",String.valueOf(texarnaResident));
			
			StateWHITAmount = (double)payRollBean.calculateArkansasWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, texarnaResident);
		}else if(state != null && state.equalsIgnoreCase("Idaho")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("total_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("total_allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateIdahoWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Alabama")){
			filingStatus = request.getParameter("filing_status");
			dependentAllowances = Integer.parseInt(request.getParameter("dependent_allowances"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("dependent_allowances",String.valueOf(dependentAllowances));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateAlabamaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, dependentAllowances, filingStatus, federalWHITAmount);
		}else if(state != null && state.equalsIgnoreCase("Connecticut")){
			withHoldingCode = request.getParameter("withholding_code");
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("withholding_code",String.valueOf(withHoldingCode));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculcateConnecticutWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, withHoldingCode);
		}else if(state != null && state.equalsIgnoreCase("Illinois")){
			additionalAllowances = Integer.parseInt(request.getParameter("additional_allowances"));
			basicAllowances = Integer.parseInt(request.getParameter("basic_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("additional_allowances",String.valueOf(additionalAllowances));
			request.setAttribute("basic_allowances",String.valueOf(basicAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateIllinoisWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, additionalAllowances, basicAllowances);
		}else if(state != null && state.equalsIgnoreCase("Indiana")){
			personalDeduction = Integer.parseInt(request.getParameter("personal_deduction"));
			dependentDeduction = Integer.parseInt(request.getParameter("dependent_deduction"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("personal_deduction",String.valueOf(personalDeduction));
			request.setAttribute("dependent_deduction",String.valueOf(dependentDeduction));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateIndianaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, personalDeduction, dependentDeduction);
		}else if(state != null && state.equalsIgnoreCase("Kentucky")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateKentuckyWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("Maine")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMaineWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Minnesota")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMinnesotaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Wisconsin")){
			filingStatus = request.getParameter("filing_status");
			WHExemptions = Integer.parseInt(request.getParameter("WHExemptions"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("WHExemptions",String.valueOf(WHExemptions));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateWisconsinWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, WHExemptions, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Rhode Island")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateRhodeislandWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("Vermont")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateVermontWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("New Jersey")){
			filingStatus = request.getParameter("filing_status");
			RateCode = request.getParameter("rate_code");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("rate_code",String.valueOf(RateCode));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateNewJerseyWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, RateCode, fWHITMarital);
		}else if(state != null && state.equalsIgnoreCase("District of Columbia")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateColumbiaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("Nebraska")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateNebraskaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Montana")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMontanaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("New Mexico")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateNewMexicoWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("West Virginia")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateWestVirginiaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Hawaii")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateHawaiiWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Louisiana")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			personalAllowances = Integer.parseInt(request.getParameter("personal_allowances"));
			dependentAllowances = Integer.parseInt(request.getParameter("dependent_allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("personal_allowances",String.valueOf(personalAllowances));
			request.setAttribute("dependent_allowances",String.valueOf(dependentAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateLouisianaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, personalAllowances, dependentAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Massachusetts")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			SpouseBlind = request.getParameter("spouse_blind");
			PersonalBlind = request.getParameter("personal_blind");
			HouseHold = request.getParameter("house_hold");
			Student = request.getParameter("student");
			
			request.setAttribute("spouse_blind",String.valueOf(SpouseBlind));
			request.setAttribute("personal_blind",String.valueOf(PersonalBlind));
			request.setAttribute("house_hold",String.valueOf(HouseHold));
			request.setAttribute("student",String.valueOf(Student));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMassachusettsWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, social_security_amount, medicare_amount, SpouseBlind, PersonalBlind, HouseHold, Student);
		}else if(state != null && state.equalsIgnoreCase("Oregon")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateOregonWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, federalWHITAmount);
		}else if(state != null && state.equalsIgnoreCase("South Carolina")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateSouthCarolinaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("North Carolina")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateNorthCarolinaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("North Dakota")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateNorthDakotaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Utah")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateUtahWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Delaware")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateDelawareWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Colorado")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateColoradoWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("California")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			extendedAllowances = Integer.parseInt(request.getParameter("extended_allowances"));
			regularAllowances = Integer.parseInt(request.getParameter("regular_allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("extended_allowances",String.valueOf(extendedAllowances));
			request.setAttribute("regular_allowances",String.valueOf(regularAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateCaliforniaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, extendedAllowances, regularAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Iowa")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateIowaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, federalWHITAmount);
		}else if(state != null && state.equalsIgnoreCase("Kansas")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateKansasWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Mississippi")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			exemptionAmount = Double.parseDouble(request.getParameter("exemption_amount"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("exemption_amount",String.valueOf(exemptionAmount));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMississippiWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, exemptionAmount, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Michigan")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMichiganWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("Missouri")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMissouriWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, federalWHITAmount);
		}else if(state != null && state.equalsIgnoreCase("Pennsylvania")){
						
			StateWHITAmount = (double)payRollBean.calculatePennsylvaniaWithHeld(Double.parseDouble(totalGross), PayPeriod);
		}else if(state != null && state.equalsIgnoreCase("Arizona")){
			WithholdingRate = Double.parseDouble(request.getParameter("withholding_rate"));
			
			request.setAttribute("withholding_rate",String.valueOf(WithholdingRate));
			
			StateWHITAmount = (double)payRollBean.calculateArizonaWithHeld(Double.parseDouble(totalGross), PayPeriod, WithholdingRate);
		}else if(state != null && state.equalsIgnoreCase("Oklahoma")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateOklahomaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Ohio")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			DistrictRate = Double.parseDouble(request.getParameter("district_rate"));
			DistrictNo = Integer.parseInt(request.getParameter("district_no"));
			
			request.setAttribute("district_rate",String.valueOf(DistrictRate));
			request.setAttribute("district_no",String.valueOf(DistrictNo));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateOhioWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
			schoolDistITAmount = (double)payRollBean.calculateOhioSchoolDistrictTax(Double.parseDouble(totalGross), PayPeriod, totalAllowances, DistrictRate);
			request.setAttribute("schoolDistITAmount",String.valueOf(schoolDistITAmount));
		}
		
		System.out.println("Hourly_payrate-------------------->"+Hourly_payrate);
		System.out.println("total_hours-------------------->"+total_hours);
		System.out.println("YTD_Hourly-------------------->"+YTD_Hourly);
		//System.out.println("Monthly_payrate-------------------->"+Monthly_payrate);
		//System.out.println("YTD_Monthly-------------------->"+YTD_Monthly);
				
		System.out.println("ot_row_count-------------------->"+ot_row_count);
		
		
		
		
		System.out.println("taxyear------------------->"+taxyear);
		System.out.println("state------------------->"+state);
		System.out.println("chequeNo------------------->"+chequeNo);
		System.out.println("chequeDate------------------->"+chequeDate);
		System.out.println("VI_NofExemptions------------------->"+NofExemptions);
		System.out.println("VI_age_exemptions------------------->"+age_exemptions);
		System.out.println("VI_additional_withholding------------------->"+additional_withholding);
		System.out.println("fWHITMarital------------------->"+fWHITMarital);
		System.out.println("fWHITNoFAllowances------------------->"+fWHITNoFAllowances);
		System.out.println("PayPeriod------------------->"+PayPeriod);
		System.out.println("tax_paydurationFrom------------->"+tax_paydurationFrom);
		System.out.println("tax_paydurationTo------------->"+tax_paydurationTo);
		System.out.println("PayRateType------------------->"+PayRateType);
		System.out.println("overtime------------------->"+overtime);
		System.out.println("total_gross_pay_hourly------------------->"+total_gross_pay_hourly);
		//System.out.println("gross_pay_monthly------------------->"+total_gross_pay_monthly);
		System.out.println("federal_it_cp------------------->"+federal_it_cp);
		System.out.println("social_cp------------------->"+social_cp);
		System.out.println("Medicare_cp------------------->"+Medicare_cp);
		System.out.println("State_IT_cp------------------->"+State_IT_cp);
		System.out.println("After_Tax_Income_CP------------------->"+After_Tax_Income_CP);
		System.out.println("After_Tax_deduction_CP------------------->"+After_Tax_deduction_CP);
		
		System.out.println("federal_it_rate-------------------->"+federal_it_rate);
		System.out.println("federal_it_annual-------------------->"+federal_it_annual);
		System.out.println("federal_it_ytd-------------------->"+federal_it_ytd);
		System.out.println("social_rate-------------------->"+social_rate);
		System.out.println("social_annual-------------------->"+social_annual);
		System.out.println("social_ytd-------------------->"+social_ytd);
		System.out.println("Medicare_rate-------------------->"+Medicare_rate);
		System.out.println("Medicare_annual-------------------->"+Medicare_annual);
		System.out.println("Medicare_ytd-------------------->"+Medicare_ytd);
		System.out.println("State_IT_rate-------------------->"+State_IT_rate);
		System.out.println("State_IT_annual-------------------->"+State_IT_annual);
		System.out.println("State_IT_ytd-------------------->"+State_IT_ytd);
		System.out.println("After_Tax_Income-------------------->"+After_Tax_Income);
		System.out.println("After_Tax_Income_YTD-------------------->"+After_Tax_Income_YTD);
		System.out.println("After_Tax_deduction-------------------->"+After_Tax_deduction);
		System.out.println("After_Tax_deduction_YTD-------------------->"+After_Tax_deduction_YTD);
		
		
		System.out.println("Total federalWHITAmount----------------------->"+federalWHITAmount);
		System.out.println("Total StateWHITAmount----------------------->"+StateWHITAmount);
		System.out.println("Social security amount----------------------->"+social_security_amount);
		System.out.println("Medicare amount----------------------->"+medicare_amount);
		
		
		request.setAttribute("taxyear",taxyear);
		request.setAttribute("state",state);
		request.setAttribute("chequeNo",chequeNo);
		request.setAttribute("chequeDate",chequeDate);
		request.setAttribute("fWHITMarital",fWHITMarital);
		request.setAttribute("fWHITNoFAllowances",fWHITNoFAllowances);
		request.setAttribute("PayPeriod",PayPeriod);
		request.setAttribute("payDurationFrom",tax_paydurationFrom);
		request.setAttribute("payDurationTo",tax_paydurationTo);
		request.setAttribute("PayRateType",PayRateType);
		request.setAttribute("overtime",overtime);
		request.setAttribute("total_gross_pay_hourly",total_gross_pay_hourly);
		//request.setAttribute("gross_pay_monthly",total_gross_pay_monthly);
		request.setAttribute("federal_it_cp",String.valueOf(federalWHITAmount));
		request.setAttribute("social_cp",social_cp);
		request.setAttribute("Medicare_cp",Medicare_cp);
		request.setAttribute("State_IT_cp",String.valueOf(StateWHITAmount));
		request.setAttribute("After_Tax_Income_CP",After_Tax_Income_CP);
		request.setAttribute("After_Tax_deduction_CP",After_Tax_deduction_CP);
		request.setAttribute("federal_it_rate",federal_it_rate);
		request.setAttribute("federal_it_annual",federal_it_annual);
		request.setAttribute("federal_it_ytd",federal_it_ytd);
		request.setAttribute("social_rate",social_rate);
		request.setAttribute("social_annual",social_annual);
		request.setAttribute("social_ytd",social_ytd);
		request.setAttribute("Medicare_rate",Medicare_rate);
		request.setAttribute("Medicare_annual",Medicare_annual);
		request.setAttribute("Medicare_ytd",Medicare_ytd);
		request.setAttribute("State_IT_rate",State_IT_rate);
		request.setAttribute("State_IT_annual",State_IT_annual);
		request.setAttribute("State_IT_ytd",State_IT_ytd);
		request.setAttribute("After_Tax_Income",After_Tax_Income);
		request.setAttribute("After_Tax_Income_YTD",After_Tax_Income_YTD);
		request.setAttribute("After_Tax_deduction",After_Tax_deduction);
		request.setAttribute("After_Tax_deduction_YTD",After_Tax_deduction_YTD);
		
		request.setAttribute("Hourly_payrate",Hourly_payrate);
		request.setAttribute("total_hours",total_hours);
		request.setAttribute("YTD_Hourly",YTD_Hourly);
		//request.setAttribute("Monthly_payrate",Monthly_payrate);
		//request.setAttribute("YTD_Monthly",YTD_Monthly);
		
		request.setAttribute("overtimecount",ot_row_count);
		request.setAttribute("totalGross", totalGross);
		
		request.setAttribute("federalWHITAmount", String.valueOf(federalWHITAmount));
		request.setAttribute("StateWHITAmount_VA", String.valueOf(StateWHITAmount));
		
		netAmount = (Double.parseDouble(totalGross) - 
				(federalWHITAmount + StateWHITAmount + social_security_amount + medicare_amount));
		netAmount = Math.round(netAmount * 100D) / 100D;
		
		//do After tax income calculation
		netAmount = netAmount + Double.parseDouble(After_Tax_Income_CP);
		
		//do After tax deduction calculation
		netAmount = netAmount - Double.parseDouble(After_Tax_deduction_CP);
		
		//Final Net Amount 
		request.setAttribute("netAmount", String.valueOf(netAmount));
		
		request.setAttribute("pageStatus","view");
		
		return new ModelAndView("requirements/frmViewPayrollCalculator");
		
	}
	
	@RequestMapping("/initEditPayroll.html")
	public ModelAndView initEditPayroll(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(true); 
		DbPayrollERPSessionBean payRollBean=new DbPayrollERPSessionBean();
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("RequirementsAction.initPayrollCalc()");
		String uniqueEmployeeId = null;
		String uniquePayrollId = null;
		
		ArrayList employeeDetails = new ArrayList();
		uniqueEmployeeId = request.getParameter("uniqueId");
		uniquePayrollId = request.getParameter("payrollId");
		
		if(uniqueEmployeeId == null){
			throw new Exception("Employee Not Found!");
			}else{
				
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(uniqueEmployeeId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("uniquePayrollId",uniquePayrollId);
			}
		double netAmount = 0.0;
		double social_security_amount = 0.0;
		double medicare_amount = 0.0;
		double total_ot_amount = 0.0;
		String taxyear = null;
		String state = null;
		String chequeNo = null;
		String chequeDate = null;
		String NofExemptions = null;
		String age_exemptions = null;
		String additional_withholding = null;
		String fWHITMarital = null;
		String fWHITNoFAllowances = null;
		String PayPeriod = null;
		
		String PayRateType = null;
		String overtime = null;
		String Hourly_payrate =null;
		String total_hours = null;
		String YTD_Hourly = null;
		String total_gross_pay_hourly = null;
		//String Monthly_payrate = null;
		//String YTD_Monthly = null;
		//String total_gross_pay_monthly = null;
		
		String federal_it_rate = null;
		String federal_it_annual = null;
		String federal_it_ytd = null;
		String federal_it_cp = null;
		String social_rate = null;
		String social_annual = null;
		String social_ytd = null;
		String social_cp = null;
		String Medicare_rate = null;
		String Medicare_annual = null;
		String Medicare_ytd = null;
		String Medicare_cp = null;
		String State_IT_rate = null;
		String State_IT_annual = null;
		String State_IT_ytd = null;
		String State_IT_cp = null;
		String After_Tax_Income = null;
		String After_Tax_Income_YTD = null;
		String After_Tax_Income_CP = null;
		String After_Tax_deduction = null;
		String After_Tax_deduction_YTD = null;
		String After_Tax_deduction_CP = null;
		
		String totalGross = null;
		String ot_row_count = null;
		double StateWHITAmount = 0;
		int personalAllowances = 0;
		int dependentAllowances = 0;
		String filingStatus = null;
		int totalAllowances = 0;
		double additionalStateWithHolding = 0;
		int additionalAllowances = 0;
		String texarnaResident = null;
		String tax_paydurationFrom = null;
		String tax_paydurationTo = null;
		
		String withHoldingCode = null;
		int basicAllowances = 0;
		int personalDeduction = 0;
		int dependentDeduction = 0;
		int WHExemptions = 0;
		String RateCode = null;
		
		String SpouseBlind = null;
		String PersonalBlind = null;
		String HouseHold = null;
		String Student = null;
		
		int extendedAllowances = 0;
		int regularAllowances = 0;
		double exemptionAmount = 0;
		double WithholdingRate = 0;
		double DistrictRate = 0;
		int DistrictNo = 0;
		double schoolDistITAmount = 0.0;
		
		taxyear = (String) request.getParameter("taxyear");
		state = (String) request.getParameter("state");
		chequeNo = (String) request.getParameter("chequeNo");
		chequeDate = (String) request.getParameter("chequeDate");
		
		fWHITMarital = (String) request.getParameter("fWHITMarital");
		fWHITNoFAllowances = (String) request.getParameter("fWHITNoFAllowances");
		PayPeriod = (String) request.getParameter("PayPeriod");
		tax_paydurationFrom = (String) request.getParameter("payDurationFrom");
		tax_paydurationTo = (String) request.getParameter("payDurationTo");
		PayRateType = (String) request.getParameter("PayRateType");
		overtime = (String) request.getParameter("overtime");
		
		total_gross_pay_hourly = (String) request.getParameter("total_gross_pay_hourly");
		
		//total_gross_pay_monthly = (String) request.getParameter("total_gross_pay_monthly");

		
		federal_it_cp = (String) request.getParameter("federal_it_cp");
		
		social_cp = (String) request.getParameter("social_cp");
		
		Medicare_cp = (String) request.getParameter("Medicare_cp");
		
		State_IT_cp = (String) request.getParameter("State_IT_cp");
		
		After_Tax_Income_CP = (String) request.getParameter("After_Tax_Income_CP");
		
		After_Tax_deduction_CP = (String) request.getParameter("After_Tax_deduction_CP");
		
		Hourly_payrate = request.getParameter("Hourly_payrate");
		total_hours = request.getParameter("total_hours");
		YTD_Hourly = request.getParameter("YTD_Hourly");
		//Monthly_payrate = request.getParameter("Monthly_payrate");
		//YTD_Monthly = request.getParameter("YTD_Monthly");
		
		ot_row_count = request.getParameter("overtimecount");
		System.out.println("ot_row_count-------------->"+ot_row_count);
		
		if(overtime != null && overtime != "null" && !(overtime.equalsIgnoreCase("null"))){
		int ot_row_count_int = 0;
		ot_row_count_int = Integer.parseInt(ot_row_count);
		String[] overtimerate = new String[ot_row_count_int+1];
		String[] overtimepaytype = new String[ot_row_count_int+1];
		String[] Hour_ot = new String[ot_row_count_int+1];
		String[] ytd_ot = new String[ot_row_count_int+1];
		String[] cp_ot = new String[ot_row_count_int+1];
		
		
		for(int i=0;i<=ot_row_count_int;i++){
			System.out.println("request.getParameter(overtimerate"+i+")"+request.getParameter("overtimerate"+i));
			//System.out.println("overtimerate["+i+"]------------->"+overtimerate[i]);
		overtimerate[i] = request.getParameter("overtimerate"+i);
		overtimepaytype[i] = request.getParameter("overtimepaytype"+i);
		Hour_ot[i] = request.getParameter("Hour_ot"+i);
		ytd_ot[i] = request.getParameter("ytd_ot"+i);
		cp_ot[i] = request.getParameter("cp_ot"+i);
		
		total_ot_amount = (total_ot_amount + Double.parseDouble(cp_ot[i]));
		
		System.out.println("overtimerate["+i+"]---------->"+overtimerate[i]);
		System.out.println("overtimepaytype["+i+"]---------->"+overtimepaytype[i]);
		System.out.println("Hour_ot["+i+"]---------->"+Hour_ot[i]);
		System.out.println("ytd_ot["+i+"]---------->"+ytd_ot[i]);
		System.out.println("cp_ot["+i+"]---------->"+cp_ot[i]);
		
		System.out.println("total_ot_amount-------------->"+total_ot_amount);
		
		request.setAttribute("overtimerate"+i,overtimerate[i]);
		request.setAttribute("overtimepaytype"+i,overtimepaytype[i]);
		request.setAttribute("Hour_ot"+i,Hour_ot[i]);
		request.setAttribute("ytd_ot"+i,ytd_ot[i]);
		request.setAttribute("cp_ot"+i,cp_ot[i]);
		}
		}
		
		federal_it_rate = request.getParameter("federal_it_rate");
		federal_it_annual = request.getParameter("federal_it_annual");
		federal_it_ytd = request.getParameter("federal_it_ytd");
		social_rate = request.getParameter("social_rate");
		social_annual = request.getParameter("social_annual");
		social_ytd = request.getParameter("social_ytd");
		Medicare_rate = request.getParameter("Medicare_rate");
		Medicare_annual = request.getParameter("Medicare_annual");
		Medicare_ytd = request.getParameter("Medicare_ytd");
		State_IT_rate = request.getParameter("State_IT_rate");
		State_IT_annual = request.getParameter("State_IT_annual");
		State_IT_ytd = request.getParameter("State_IT_ytd");
		After_Tax_Income = request.getParameter("After_Tax_Income");
		After_Tax_Income_YTD = request.getParameter("After_Tax_Income_YTD");
		After_Tax_deduction = request.getParameter("After_Tax_deduction");
		After_Tax_deduction_YTD = request.getParameter("After_Tax_deduction_YTD");
		
		/*if(PayRateType != null && PayRateType.equalsIgnoreCase("Hourly")){
			totalGross = total_gross_pay_hourly;
		}else if(PayRateType != null && PayRateType.equalsIgnoreCase("Monthly")){
			totalGross = total_gross_pay_monthly;
		}else{
			throw new Exception("Problem with Total Gross Amount!");
		}
		*/
		
		if(PayRateType != null){
			totalGross = total_gross_pay_hourly;
		}else{
			throw new Exception("Problem with Total Gross Amount!");
		}
		
		totalGross = String.valueOf((Double.parseDouble(totalGross) + total_ot_amount));
		
		/*
		 * Start-------------Calculate federal Withheld Amount-----------*/
		
		double federalWHITAmount = (double)payRollBean.calculateFederalITWithholdAmount(fWHITMarital,Integer.parseInt(fWHITNoFAllowances),Double.parseDouble(totalGross),PayPeriod);
		
		/*
		 * Start-------------Calculate federal Withheld Amount-----------*/
		
		
		
		/*
		 * Start-------------Calculate Social Security Amount and Medicare Amount-----------*/
		
		social_security_amount = (Double.parseDouble(totalGross) * Double.parseDouble(social_rate));
		social_security_amount = Math.round(social_security_amount * 100D) / 100D;
		
		medicare_amount = (Double.parseDouble(totalGross) * Double.parseDouble(Medicare_rate));
		medicare_amount = Math.round(medicare_amount * 100D) / 100D;
		
		social_cp = String.valueOf(social_security_amount);
		Medicare_cp = String.valueOf(medicare_amount);
		
		/*
		 * End-------------Calculate Social Security Amount and Medicare Amount-----------*/
		
		
		if(state != null && state.equalsIgnoreCase("Virginia")){
			NofExemptions = (String) request.getParameter("VI_NofExemptions");
			age_exemptions = (String) request.getParameter("VI_age_exemptions");
			additional_withholding = (String) request.getParameter("VI_additional_withholding");
			
			System.out.println("NofExemptions--------------->"+NofExemptions);
			System.out.println("age_exemptions--------------->"+age_exemptions);
			System.out.println("additional_withholding--------------->"+additional_withholding);
			
			request.setAttribute("VI_NofExemptions",NofExemptions);
			request.setAttribute("VI_age_exemptions",age_exemptions);
			request.setAttribute("VI_additional_withholding",additional_withholding);
			
			StateWHITAmount = (double)payRollBean.calculateVirginiaStateWithHold(Double.parseDouble(totalGross), PayPeriod,
					Double.parseDouble(additional_withholding), Integer.parseInt(age_exemptions), Integer.parseInt(NofExemptions));
		}else if(state != null && state.equalsIgnoreCase("Georgia")){
			personalAllowances = Integer.parseInt(request.getParameter("personal_allowances"));
			dependentAllowances = Integer.parseInt(request.getParameter("dependent_allowances"));
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("total_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			additionalAllowances = Integer.parseInt(request.getParameter("additional_allowances"));
			
			request.setAttribute("personal_allowances",String.valueOf(personalAllowances));
			request.setAttribute("dependent_allowances",String.valueOf(dependentAllowances));
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("total_allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			request.setAttribute("additional_allowances",String.valueOf(additionalAllowances));
			StateWHITAmount = (double)payRollBean.calculateGeorgiaWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, additionalAllowances, personalAllowances, dependentAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Arkansas")){
			totalAllowances = Integer.parseInt(request.getParameter("total_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			texarnaResident = request.getParameter("texarna_resident");
			
			request.setAttribute("total_allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			request.setAttribute("texarna_resident",String.valueOf(texarnaResident));
			
			StateWHITAmount = (double)payRollBean.calculateArkansasWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, texarnaResident);
		}else if(state != null && state.equalsIgnoreCase("Idaho")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("total_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("total_allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateIdahoWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Alabama")){
			filingStatus = request.getParameter("filing_status");
			dependentAllowances = Integer.parseInt(request.getParameter("dependent_allowances"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("dependent_allowances",String.valueOf(dependentAllowances));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateAlabamaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, dependentAllowances, filingStatus, federalWHITAmount);
		}else if(state != null && state.equalsIgnoreCase("Connecticut")){
			withHoldingCode = request.getParameter("withholding_code");
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("withholding_code",String.valueOf(withHoldingCode));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculcateConnecticutWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, withHoldingCode);
		}else if(state != null && state.equalsIgnoreCase("Illinois")){
			additionalAllowances = Integer.parseInt(request.getParameter("additional_allowances"));
			basicAllowances = Integer.parseInt(request.getParameter("basic_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("additional_allowances",String.valueOf(additionalAllowances));
			request.setAttribute("basic_allowances",String.valueOf(basicAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateIllinoisWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, additionalAllowances, basicAllowances);
		}else if(state != null && state.equalsIgnoreCase("Indiana")){
			personalDeduction = Integer.parseInt(request.getParameter("personal_deduction"));
			dependentDeduction = Integer.parseInt(request.getParameter("dependent_deduction"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("personal_deduction",String.valueOf(personalDeduction));
			request.setAttribute("dependent_deduction",String.valueOf(dependentDeduction));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateIndianaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, personalDeduction, dependentDeduction);
		}else if(state != null && state.equalsIgnoreCase("Kentucky")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateKentuckyWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("Maine")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMaineWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Minnesota")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMinnesotaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Wisconsin")){
			filingStatus = request.getParameter("filing_status");
			WHExemptions = Integer.parseInt(request.getParameter("WHExemptions"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("WHExemptions",String.valueOf(WHExemptions));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateWisconsinWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, WHExemptions, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Rhode Island")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateRhodeislandWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("Vermont")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateVermontWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("New Jersey")){
			filingStatus = request.getParameter("filing_status");
			RateCode = request.getParameter("rate_code");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("rate_code",String.valueOf(RateCode));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateNewJerseyWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, RateCode, fWHITMarital);
		}else if(state != null && state.equalsIgnoreCase("District of Columbia")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateColumbiaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("Nebraska")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateNebraskaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Montana")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMontanaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("New Mexico")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateNewMexicoWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("West Virginia")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateWestVirginiaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Hawaii")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateHawaiiWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Louisiana")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			personalAllowances = Integer.parseInt(request.getParameter("personal_allowances"));
			dependentAllowances = Integer.parseInt(request.getParameter("dependent_allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("personal_allowances",String.valueOf(personalAllowances));
			request.setAttribute("dependent_allowances",String.valueOf(dependentAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateLouisianaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, personalAllowances, dependentAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Massachusetts")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			SpouseBlind = request.getParameter("spouse_blind");
			PersonalBlind = request.getParameter("personal_blind");
			HouseHold = request.getParameter("house_hold");
			Student = request.getParameter("student");
			
			request.setAttribute("spouse_blind",String.valueOf(SpouseBlind));
			request.setAttribute("personal_blind",String.valueOf(PersonalBlind));
			request.setAttribute("house_hold",String.valueOf(HouseHold));
			request.setAttribute("student",String.valueOf(Student));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMassachusettsWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, social_security_amount, medicare_amount, SpouseBlind, PersonalBlind, HouseHold, Student);
		}else if(state != null && state.equalsIgnoreCase("Oregon")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateOregonWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, federalWHITAmount);
		}else if(state != null && state.equalsIgnoreCase("South Carolina")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateSouthCarolinaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("North Carolina")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateNorthCarolinaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("North Dakota")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateNorthDakotaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Utah")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateUtahWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Delaware")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateDelawareWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Colorado")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateColoradoWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("California")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			extendedAllowances = Integer.parseInt(request.getParameter("extended_allowances"));
			regularAllowances = Integer.parseInt(request.getParameter("regular_allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("extended_allowances",String.valueOf(extendedAllowances));
			request.setAttribute("regular_allowances",String.valueOf(regularAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateCaliforniaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, extendedAllowances, regularAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Iowa")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateIowaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, federalWHITAmount);
		}else if(state != null && state.equalsIgnoreCase("Kansas")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateKansasWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Mississippi")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			exemptionAmount = Double.parseDouble(request.getParameter("exemption_amount"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("exemption_amount",String.valueOf(exemptionAmount));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMississippiWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, exemptionAmount, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Michigan")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMichiganWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("Missouri")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMissouriWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, federalWHITAmount);
		}else if(state != null && state.equalsIgnoreCase("Pennsylvania")){
						
			StateWHITAmount = (double)payRollBean.calculatePennsylvaniaWithHeld(Double.parseDouble(totalGross), PayPeriod);
		}else if(state != null && state.equalsIgnoreCase("Arizona")){
			WithholdingRate = Double.parseDouble(request.getParameter("withholding_rate"));
			
			request.setAttribute("withholding_rate",String.valueOf(WithholdingRate));
			
			StateWHITAmount = (double)payRollBean.calculateArizonaWithHeld(Double.parseDouble(totalGross), PayPeriod, WithholdingRate);
		}else if(state != null && state.equalsIgnoreCase("Oklahoma")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateOklahomaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Ohio")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			DistrictRate = Double.parseDouble(request.getParameter("district_rate"));
			DistrictNo = Integer.parseInt(request.getParameter("district_no"));
			
			request.setAttribute("district_rate",String.valueOf(DistrictRate));
			request.setAttribute("district_no",String.valueOf(DistrictNo));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateOhioWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
			schoolDistITAmount = (double)payRollBean.calculateOhioSchoolDistrictTax(Double.parseDouble(totalGross), PayPeriod, totalAllowances, DistrictRate);
			request.setAttribute("schoolDistITAmount",String.valueOf(schoolDistITAmount));
		}
		
		System.out.println("Hourly_payrate-------------------->"+Hourly_payrate);
		System.out.println("total_hours-------------------->"+total_hours);
		System.out.println("YTD_Hourly-------------------->"+YTD_Hourly);
		//System.out.println("Monthly_payrate-------------------->"+Monthly_payrate);
		//System.out.println("YTD_Monthly-------------------->"+YTD_Monthly);
				
		System.out.println("ot_row_count-------------------->"+ot_row_count);
		
		
		
		
		System.out.println("taxyear------------------->"+taxyear);
		System.out.println("state------------------->"+state);
		System.out.println("chequeNo------------------->"+chequeNo);
		System.out.println("chequeDate------------------->"+chequeDate);
		System.out.println("VI_NofExemptions------------------->"+NofExemptions);
		System.out.println("VI_age_exemptions------------------->"+age_exemptions);
		System.out.println("VI_additional_withholding------------------->"+additional_withholding);
		System.out.println("fWHITMarital------------------->"+fWHITMarital);
		System.out.println("fWHITNoFAllowances------------------->"+fWHITNoFAllowances);
		System.out.println("PayPeriod------------------->"+PayPeriod);
		System.out.println("tax_paydurationFrom------------------->"+tax_paydurationFrom);
		System.out.println("tax_paydurationTo------------------->"+tax_paydurationTo);
		System.out.println("PayRateType------------------->"+PayRateType);
		System.out.println("overtime------------------->"+overtime);
		System.out.println("total_gross_pay_hourly------------------->"+total_gross_pay_hourly);
		//System.out.println("gross_pay_monthly------------------->"+total_gross_pay_monthly);
		System.out.println("federal_it_cp------------------->"+federal_it_cp);
		System.out.println("social_cp------------------->"+social_cp);
		System.out.println("Medicare_cp------------------->"+Medicare_cp);
		System.out.println("State_IT_cp------------------->"+State_IT_cp);
		System.out.println("After_Tax_Income_CP------------------->"+After_Tax_Income_CP);
		System.out.println("After_Tax_deduction_CP------------------->"+After_Tax_deduction_CP);
		
		System.out.println("federal_it_rate-------------------->"+federal_it_rate);
		System.out.println("federal_it_annual-------------------->"+federal_it_annual);
		System.out.println("federal_it_ytd-------------------->"+federal_it_ytd);
		System.out.println("social_rate-------------------->"+social_rate);
		System.out.println("social_annual-------------------->"+social_annual);
		System.out.println("social_ytd-------------------->"+social_ytd);
		System.out.println("Medicare_rate-------------------->"+Medicare_rate);
		System.out.println("Medicare_annual-------------------->"+Medicare_annual);
		System.out.println("Medicare_ytd-------------------->"+Medicare_ytd);
		System.out.println("State_IT_rate-------------------->"+State_IT_rate);
		System.out.println("State_IT_annual-------------------->"+State_IT_annual);
		System.out.println("State_IT_ytd-------------------->"+State_IT_ytd);
		System.out.println("After_Tax_Income-------------------->"+After_Tax_Income);
		System.out.println("After_Tax_Income_YTD-------------------->"+After_Tax_Income_YTD);
		System.out.println("After_Tax_deduction-------------------->"+After_Tax_deduction);
		System.out.println("After_Tax_deduction_YTD-------------------->"+After_Tax_deduction_YTD);
		
		

		System.out.println("Total federalWHITAmount----------------------->"+federalWHITAmount);
		System.out.println("Total StateWHITAmount----------------------->"+StateWHITAmount);
		System.out.println("Social security amount----------------------->"+social_security_amount);
		System.out.println("Medicare amount----------------------->"+medicare_amount);
		
		
		request.setAttribute("taxyear",taxyear);
		request.setAttribute("state",state);
		request.setAttribute("chequeNo",chequeNo);
		request.setAttribute("chequeDate",chequeDate);
		request.setAttribute("fWHITMarital",fWHITMarital);
		request.setAttribute("fWHITNoFAllowances",fWHITNoFAllowances);
		request.setAttribute("PayPeriod",PayPeriod);
		request.setAttribute("payDurationFrom", tax_paydurationFrom);
		request.setAttribute("payDurationTo", tax_paydurationTo);
		request.setAttribute("PayRateType",PayRateType);
		request.setAttribute("overtime",overtime);
		request.setAttribute("total_gross_pay_hourly",total_gross_pay_hourly);
		//request.setAttribute("gross_pay_monthly",total_gross_pay_monthly);
		request.setAttribute("federal_it_cp",String.valueOf(federalWHITAmount));
		request.setAttribute("social_cp",social_cp);
		request.setAttribute("Medicare_cp",Medicare_cp);
		request.setAttribute("State_IT_cp",String.valueOf(StateWHITAmount));
		request.setAttribute("After_Tax_Income_CP",After_Tax_Income_CP);
		request.setAttribute("After_Tax_deduction_CP",After_Tax_deduction_CP);
		request.setAttribute("federal_it_rate",federal_it_rate);
		request.setAttribute("federal_it_annual",federal_it_annual);
		request.setAttribute("federal_it_ytd",federal_it_ytd);
		request.setAttribute("social_rate",social_rate);
		request.setAttribute("social_annual",social_annual);
		request.setAttribute("social_ytd",social_ytd);
		request.setAttribute("Medicare_rate",Medicare_rate);
		request.setAttribute("Medicare_annual",Medicare_annual);
		request.setAttribute("Medicare_ytd",Medicare_ytd);
		request.setAttribute("State_IT_rate",State_IT_rate);
		request.setAttribute("State_IT_annual",State_IT_annual);
		request.setAttribute("State_IT_ytd",State_IT_ytd);
		request.setAttribute("After_Tax_Income",After_Tax_Income);
		request.setAttribute("After_Tax_Income_YTD",After_Tax_Income_YTD);
		request.setAttribute("After_Tax_deduction",After_Tax_deduction);
		request.setAttribute("After_Tax_deduction_YTD",After_Tax_deduction_YTD);
		
		request.setAttribute("Hourly_payrate",Hourly_payrate);
		request.setAttribute("total_hours",total_hours);
		request.setAttribute("YTD_Hourly",YTD_Hourly);
		//request.setAttribute("Monthly_payrate",Monthly_payrate);
		//request.setAttribute("YTD_Monthly",YTD_Monthly);
		
		request.setAttribute("overtimecount",ot_row_count);
		request.setAttribute("totalGross", totalGross);
		
		request.setAttribute("federalWHITAmount", String.valueOf(federalWHITAmount));
		request.setAttribute("StateWHITAmount_VA", String.valueOf(StateWHITAmount));
		
		netAmount = (Double.parseDouble(totalGross) - 
				(federalWHITAmount + StateWHITAmount + social_security_amount + medicare_amount));
		netAmount = (Math.round(netAmount * 100D) / 100D);
		
		//do After tax income calculation
		netAmount = netAmount + Double.parseDouble(After_Tax_Income_CP);
				
		//do After tax deduction calculation
		netAmount = netAmount - Double.parseDouble(After_Tax_deduction_CP);
				
		//Final Net Amount 
		request.setAttribute("netAmount", String.valueOf(netAmount));
						
		request.setAttribute("pageStatus","edit");
		
		return new ModelAndView("requirements/frmEditPayrollCalculator");
		
	}
	
	@SuppressWarnings("unused")
	@RequestMapping("/initDoEditPayroll.html")
	public ModelAndView initDoEditPayroll(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(true); 
		DbPayrollERPSessionBean payRollBean=new DbPayrollERPSessionBean();
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("RequirementsAction.initPayrollCalc()");
		String uniqueEmployeeId = null;
		String uniquePayrollId = null;
		
		ArrayList employeeDetails = new ArrayList();
		uniqueEmployeeId = request.getParameter("uniqueId");
		uniquePayrollId = request.getParameter("payrollId");
		
		if(uniqueEmployeeId == null){
			throw new Exception("Employee Not Found!");
			}else{
				
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(uniqueEmployeeId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("uniquePayrollId",uniquePayrollId);
			}
		double netAmount = 0.0;
		double social_security_amount = 0.0;
		double medicare_amount = 0.0;
		double total_ot_amount = 0.0;
		String taxyear = null;
		String state = null;
		String chequeNo = null;
		String chequeDate = null;
		String NofExemptions = null;
		String age_exemptions = null;
		String additional_withholding = null;
		String fWHITMarital = null;
		String fWHITNoFAllowances = null;
		String PayPeriod = null;
		String tax_paydurationFrom = null;
		String tax_paydurationTo = null;
		String PayRateType = null;
		String overtime = null;
		String Hourly_payrate =null;
		String total_hours = null;
		String YTD_Hourly = null;
		String total_gross_pay_hourly = null;
		//String Monthly_payrate = null;
		//String YTD_Monthly = null;
		//String total_gross_pay_monthly = null;
		
		String federal_it_rate = null;
		String federal_it_annual = null;
		String federal_it_ytd = null;
		String federal_it_cp = null;
		String social_rate = null;
		String social_annual = null;
		String social_ytd = null;
		String social_cp = null;
		String Medicare_rate = null;
		String Medicare_annual = null;
		String Medicare_ytd = null;
		String Medicare_cp = null;
		String State_IT_rate = null;
		String State_IT_annual = null;
		String State_IT_ytd = null;
		String State_IT_cp = null;
		String After_Tax_Income = null;
		String After_Tax_Income_YTD = null;
		String After_Tax_Income_CP = null;
		String After_Tax_deduction = null;
		String After_Tax_deduction_YTD = null;
		String After_Tax_deduction_CP = null;
		
		String totalGross = null;
		String ot_row_count = null;
		double StateWHITAmount = 0;
		int personalAllowances = 0;
		int dependentAllowances = 0;
		String filingStatus = null;
		int totalAllowances = 0;
		double additionalStateWithHolding = 0;
		int additionalAllowances = 0;
		String texarnaResident = null;
		String withHoldingCode = null;
		int basicAllowances = 0;
		int personalDeduction = 0;
		int dependentDeduction = 0;
		int WHExemptions = 0;
		String RateCode = null;
		
		String SpouseBlind = null;
		String PersonalBlind = null;
		String HouseHold = null;
		String Student = null;
		
		int extendedAllowances = 0;
		int regularAllowances = 0;
		double exemptionAmount = 0;
		double WithholdingRate = 0;
		double DistrictRate = 0;
		int DistrictNo = 0;
		double schoolDistITAmount = 0.0;
	
		taxyear = (String) request.getParameter("taxyear");
		state = (String) request.getParameter("state");
		chequeNo = (String) request.getParameter("chequeNo");
		chequeDate = (String) request.getParameter("chequeDate");
		
		fWHITMarital = (String) request.getParameter("fWHITMarital");
		fWHITNoFAllowances = (String) request.getParameter("fWHITNoFAllowances");
		PayPeriod = (String) request.getParameter("PayPeriod");
		tax_paydurationFrom = (String)request.getParameter("payDurationFrom");
		tax_paydurationTo = (String)request.getParameter("payDurationTo");
		
		PayRateType = (String) request.getParameter("PayRateType");
		overtime = (String) request.getParameter("overtime");
		
		total_gross_pay_hourly = (String) request.getParameter("total_gross_pay_hourly");
		
		//total_gross_pay_monthly = (String) request.getParameter("total_gross_pay_monthly");
	
		
		federal_it_cp = (String) request.getParameter("federal_it_cp");
		
		social_cp = (String) request.getParameter("social_cp");
		
		Medicare_cp = (String) request.getParameter("Medicare_cp");
		
		State_IT_cp = (String) request.getParameter("State_IT_cp");
		
		After_Tax_Income_CP = (String) request.getParameter("After_Tax_Income_CP");
		
		After_Tax_deduction_CP = (String) request.getParameter("After_Tax_deduction_CP");
		
		Hourly_payrate = request.getParameter("Hourly_payrate");
		total_hours = request.getParameter("total_hours");
		YTD_Hourly = request.getParameter("YTD_Hourly");
		//Monthly_payrate = request.getParameter("Monthly_payrate");
		//YTD_Monthly = request.getParameter("YTD_Monthly");
		
		ot_row_count = request.getParameter("overtimecount");
		
		if(overtime != null && overtime != "null" && !(overtime.equalsIgnoreCase("null"))){
		System.out.println("ot_row_count-------------->"+ot_row_count);
		int ot_row_count_int = 0;
		ot_row_count_int = Integer.parseInt(ot_row_count);
		String[] overtimerate = new String[ot_row_count_int+1];
		String[] overtimepaytype = new String[ot_row_count_int+1];
		String[] Hour_ot = new String[ot_row_count_int+1];
		String[] ytd_ot = new String[ot_row_count_int+1];
		String[] cp_ot = new String[ot_row_count_int+1];
		
		
		for(int i=0;i<=ot_row_count_int;i++){
			System.out.println("request.getParameter(overtimerate"+i+")"+request.getParameter("overtimerate"+i));
			//System.out.println("overtimerate["+i+"]------------->"+overtimerate[i]);
		overtimerate[i] = request.getParameter("overtimerate"+i);
		overtimepaytype[i] = request.getParameter("overtimepaytype"+i);
		Hour_ot[i] = request.getParameter("Hour_ot"+i);
		ytd_ot[i] = request.getParameter("ytd_ot"+i);
		cp_ot[i] = request.getParameter("cp_ot"+i);
		
		total_ot_amount = (total_ot_amount + Double.parseDouble(cp_ot[i]));
		
		System.out.println("overtimerate["+i+"]---------->"+overtimerate[i]);
		System.out.println("overtimepaytype["+i+"]---------->"+overtimepaytype[i]);
		System.out.println("Hour_ot["+i+"]---------->"+Hour_ot[i]);
		System.out.println("ytd_ot["+i+"]---------->"+ytd_ot[i]);
		System.out.println("cp_ot["+i+"]---------->"+cp_ot[i]);
		
		System.out.println("total_ot_amount-------------->"+total_ot_amount);
		
		request.setAttribute("overtimerate"+i,overtimerate[i]);
		request.setAttribute("overtimepaytype"+i,overtimepaytype[i]);
		request.setAttribute("Hour_ot"+i,Hour_ot[i]);
		request.setAttribute("ytd_ot"+i,ytd_ot[i]);
		request.setAttribute("cp_ot"+i,cp_ot[i]);
		}
		}
		
		federal_it_rate = request.getParameter("federal_it_rate");
		federal_it_annual = request.getParameter("federal_it_annual");
		federal_it_ytd = request.getParameter("federal_it_ytd");
		social_rate = request.getParameter("social_rate");
		social_annual = request.getParameter("social_annual");
		social_ytd = request.getParameter("social_ytd");
		Medicare_rate = request.getParameter("Medicare_rate");
		Medicare_annual = request.getParameter("Medicare_annual");
		Medicare_ytd = request.getParameter("Medicare_ytd");
		State_IT_rate = request.getParameter("State_IT_rate");
		State_IT_annual = request.getParameter("State_IT_annual");
		State_IT_ytd = request.getParameter("State_IT_ytd");
		After_Tax_Income = request.getParameter("After_Tax_Income");
		After_Tax_Income_YTD = request.getParameter("After_Tax_Income_YTD");
		After_Tax_deduction = request.getParameter("After_Tax_deduction");
		After_Tax_deduction_YTD = request.getParameter("After_Tax_deduction_YTD");
		
		/*if(PayRateType != null && PayRateType.equalsIgnoreCase("Hourly")){
			totalGross = total_gross_pay_hourly;
		}else if(PayRateType != null && PayRateType.equalsIgnoreCase("Monthly")){
			totalGross = total_gross_pay_monthly;
		}else{
			throw new Exception("Problem with Total Gross Amount!");
		}
		*/
		
		if(PayRateType != null){
			totalGross = total_gross_pay_hourly;
		}else{
			throw new Exception("Problem with Total Gross Amount!");
		}
		
		totalGross = String.valueOf((Double.parseDouble(totalGross) + total_ot_amount));
		
		/*
		 * Start-------------Calculate federal Withheld Amount-----------*/
		
		double federalWHITAmount = (double)payRollBean.calculateFederalITWithholdAmount(fWHITMarital,Integer.parseInt(fWHITNoFAllowances),Double.parseDouble(totalGross),PayPeriod);
		
		/*
		 * Start-------------Calculate federal Withheld Amount-----------*/
		
		
		
		/*
		 * Start-------------Calculate Social Security Amount and Medicare Amount-----------*/
		
		social_security_amount = (Double.parseDouble(totalGross) * Double.parseDouble(social_rate));
		social_security_amount = Math.round(social_security_amount * 100D) / 100D;
		
		medicare_amount = (Double.parseDouble(totalGross) * Double.parseDouble(Medicare_rate));
		medicare_amount = Math.round(medicare_amount * 100D) / 100D;
		
		social_cp = String.valueOf(social_security_amount);
		Medicare_cp = String.valueOf(medicare_amount);
		
		/*
		 * End-------------Calculate Social Security Amount and Medicare Amount-----------*/
		
		
		if(state != null && state.equalsIgnoreCase("Virginia")){
			NofExemptions = (String) request.getParameter("VI_NofExemptions");
			age_exemptions = (String) request.getParameter("VI_age_exemptions");
			additional_withholding = (String) request.getParameter("VI_additional_withholding");
			
			System.out.println("NofExemptions--------------->"+NofExemptions);
			System.out.println("age_exemptions--------------->"+age_exemptions);
			System.out.println("additional_withholding--------------->"+additional_withholding);
			
			request.setAttribute("VI_NofExemptions",NofExemptions);
			request.setAttribute("VI_age_exemptions",age_exemptions);
			request.setAttribute("VI_additional_withholding",additional_withholding);
			
			StateWHITAmount = (double)payRollBean.calculateVirginiaStateWithHold(Double.parseDouble(totalGross), PayPeriod,
					Double.parseDouble(additional_withholding), Integer.parseInt(age_exemptions), Integer.parseInt(NofExemptions));
		}else if(state != null && state.equalsIgnoreCase("Georgia")){
			personalAllowances = Integer.parseInt(request.getParameter("personal_allowances"));
			dependentAllowances = Integer.parseInt(request.getParameter("dependent_allowances"));
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("total_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			additionalAllowances = Integer.parseInt(request.getParameter("additional_allowances"));
			
			request.setAttribute("personal_allowances",String.valueOf(personalAllowances));
			request.setAttribute("dependent_allowances",String.valueOf(dependentAllowances));
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("total_allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			request.setAttribute("additional_allowances",String.valueOf(additionalAllowances));
			StateWHITAmount = (double)payRollBean.calculateGeorgiaWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, additionalAllowances, personalAllowances, dependentAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Arkansas")){
			totalAllowances = Integer.parseInt(request.getParameter("total_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			texarnaResident = request.getParameter("texarna_resident");
			
			request.setAttribute("total_allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			request.setAttribute("texarna_resident",String.valueOf(texarnaResident));
			
			StateWHITAmount = (double)payRollBean.calculateArkansasWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, texarnaResident);
		}else if(state != null && state.equalsIgnoreCase("Idaho")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("total_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("total_allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateIdahoWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Alabama")){
			filingStatus = request.getParameter("filing_status");
			dependentAllowances = Integer.parseInt(request.getParameter("dependent_allowances"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("dependent_allowances",String.valueOf(dependentAllowances));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateAlabamaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, dependentAllowances, filingStatus, federalWHITAmount);
		}else if(state != null && state.equalsIgnoreCase("Connecticut")){
			withHoldingCode = request.getParameter("withholding_code");
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("withholding_code",String.valueOf(withHoldingCode));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculcateConnecticutWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, withHoldingCode);
		}else if(state != null && state.equalsIgnoreCase("Illinois")){
			additionalAllowances = Integer.parseInt(request.getParameter("additional_allowances"));
			basicAllowances = Integer.parseInt(request.getParameter("basic_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("additional_allowances",String.valueOf(additionalAllowances));
			request.setAttribute("basic_allowances",String.valueOf(basicAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateIllinoisWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, additionalAllowances, basicAllowances);
		}else if(state != null && state.equalsIgnoreCase("Indiana")){
			personalDeduction = Integer.parseInt(request.getParameter("personal_deduction"));
			dependentDeduction = Integer.parseInt(request.getParameter("dependent_deduction"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("personal_deduction",String.valueOf(personalDeduction));
			request.setAttribute("dependent_deduction",String.valueOf(dependentDeduction));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateIndianaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, personalDeduction, dependentDeduction);
		}else if(state != null && state.equalsIgnoreCase("Kentucky")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateKentuckyWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("Maine")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMaineWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Minnesota")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMinnesotaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Wisconsin")){
			filingStatus = request.getParameter("filing_status");
			WHExemptions = Integer.parseInt(request.getParameter("WHExemptions"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("WHExemptions",String.valueOf(WHExemptions));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateWisconsinWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, WHExemptions, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Rhode Island")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateRhodeislandWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("Vermont")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateVermontWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("New Jersey")){
			filingStatus = request.getParameter("filing_status");
			RateCode = request.getParameter("rate_code");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("rate_code",String.valueOf(RateCode));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateNewJerseyWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, RateCode, fWHITMarital);
		}else if(state != null && state.equalsIgnoreCase("District of Columbia")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateColumbiaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("Nebraska")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateNebraskaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Montana")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMontanaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("New Mexico")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateNewMexicoWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("West Virginia")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateWestVirginiaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Hawaii")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateHawaiiWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Louisiana")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			personalAllowances = Integer.parseInt(request.getParameter("personal_allowances"));
			dependentAllowances = Integer.parseInt(request.getParameter("dependent_allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("personal_allowances",String.valueOf(personalAllowances));
			request.setAttribute("dependent_allowances",String.valueOf(dependentAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateLouisianaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, personalAllowances, dependentAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Massachusetts")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			SpouseBlind = request.getParameter("spouse_blind");
			PersonalBlind = request.getParameter("personal_blind");
			HouseHold = request.getParameter("house_hold");
			Student = request.getParameter("student");
			
			request.setAttribute("spouse_blind",String.valueOf(SpouseBlind));
			request.setAttribute("personal_blind",String.valueOf(PersonalBlind));
			request.setAttribute("house_hold",String.valueOf(HouseHold));
			request.setAttribute("student",String.valueOf(Student));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMassachusettsWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, social_security_amount, medicare_amount, SpouseBlind, PersonalBlind, HouseHold, Student);
		}else if(state != null && state.equalsIgnoreCase("Oregon")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateOregonWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, federalWHITAmount);
		}else if(state != null && state.equalsIgnoreCase("South Carolina")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateSouthCarolinaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("North Carolina")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateNorthCarolinaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("North Dakota")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateNorthDakotaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Utah")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateUtahWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Delaware")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateDelawareWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Colorado")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateColoradoWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("California")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			extendedAllowances = Integer.parseInt(request.getParameter("extended_allowances"));
			regularAllowances = Integer.parseInt(request.getParameter("regular_allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("extended_allowances",String.valueOf(extendedAllowances));
			request.setAttribute("regular_allowances",String.valueOf(regularAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateCaliforniaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, extendedAllowances, regularAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Iowa")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateIowaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, federalWHITAmount);
		}else if(state != null && state.equalsIgnoreCase("Kansas")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateKansasWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Mississippi")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			exemptionAmount = Double.parseDouble(request.getParameter("exemption_amount"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("exemption_amount",String.valueOf(exemptionAmount));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMississippiWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, exemptionAmount, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Michigan")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMichiganWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("Missouri")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMissouriWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, federalWHITAmount);
		}else if(state != null && state.equalsIgnoreCase("Pennsylvania")){
						
			StateWHITAmount = (double)payRollBean.calculatePennsylvaniaWithHeld(Double.parseDouble(totalGross), PayPeriod);
		}else if(state != null && state.equalsIgnoreCase("Arizona")){
			WithholdingRate = Double.parseDouble(request.getParameter("withholding_rate"));
			
			request.setAttribute("withholding_rate",String.valueOf(WithholdingRate));
			
			StateWHITAmount = (double)payRollBean.calculateArizonaWithHeld(Double.parseDouble(totalGross), PayPeriod, WithholdingRate);
		}else if(state != null && state.equalsIgnoreCase("Oklahoma")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateOklahomaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Ohio")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			DistrictRate = Double.parseDouble(request.getParameter("district_rate"));
			DistrictNo = Integer.parseInt(request.getParameter("district_no"));
			
			request.setAttribute("district_rate",String.valueOf(DistrictRate));
			request.setAttribute("district_no",String.valueOf(DistrictNo));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateOhioWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
			schoolDistITAmount = (double)payRollBean.calculateOhioSchoolDistrictTax(Double.parseDouble(totalGross), PayPeriod, totalAllowances, DistrictRate);
			request.setAttribute("schoolDistITAmount",String.valueOf(schoolDistITAmount));
		}
		
		System.out.println("Hourly_payrate-------------------->"+Hourly_payrate);
		System.out.println("total_hours-------------------->"+total_hours);
		System.out.println("YTD_Hourly-------------------->"+YTD_Hourly);
		//System.out.println("Monthly_payrate-------------------->"+Monthly_payrate);
		//System.out.println("YTD_Monthly-------------------->"+YTD_Monthly);
				
		System.out.println("ot_row_count-------------------->"+ot_row_count);
		
		
		
		
		System.out.println("taxyear------------------->"+taxyear);
		System.out.println("state------------------->"+state);
		System.out.println("chequeNo------------------->"+chequeNo);
		System.out.println("chequeDate------------------->"+chequeDate);
		System.out.println("VI_NofExemptions------------------->"+NofExemptions);
		System.out.println("VI_age_exemptions------------------->"+age_exemptions);
		System.out.println("VI_additional_withholding------------------->"+additional_withholding);
		System.out.println("fWHITMarital------------------->"+fWHITMarital);
		System.out.println("fWHITNoFAllowances------------------->"+fWHITNoFAllowances);
		System.out.println("PayPeriod------------------->"+PayPeriod);
		System.out.println("tax_paydurationFrom------------->"+tax_paydurationFrom);
		System.out.println("tax_paydurationTo------------->"+tax_paydurationTo);
		System.out.println("PayRateType------------------->"+PayRateType);
		System.out.println("overtime------------------->"+overtime);
		System.out.println("total_gross_pay_hourly------------------->"+total_gross_pay_hourly);
		//System.out.println("gross_pay_monthly------------------->"+total_gross_pay_monthly);
		System.out.println("federal_it_cp------------------->"+federal_it_cp);
		System.out.println("social_cp------------------->"+social_cp);
		System.out.println("Medicare_cp------------------->"+Medicare_cp);
		System.out.println("State_IT_cp------------------->"+State_IT_cp);
		System.out.println("After_Tax_Income_CP------------------->"+After_Tax_Income_CP);
		System.out.println("After_Tax_deduction_CP------------------->"+After_Tax_deduction_CP);
		
		System.out.println("federal_it_rate-------------------->"+federal_it_rate);
		System.out.println("federal_it_annual-------------------->"+federal_it_annual);
		System.out.println("federal_it_ytd-------------------->"+federal_it_ytd);
		System.out.println("social_rate-------------------->"+social_rate);
		System.out.println("social_annual-------------------->"+social_annual);
		System.out.println("social_ytd-------------------->"+social_ytd);
		System.out.println("Medicare_rate-------------------->"+Medicare_rate);
		System.out.println("Medicare_annual-------------------->"+Medicare_annual);
		System.out.println("Medicare_ytd-------------------->"+Medicare_ytd);
		System.out.println("State_IT_rate-------------------->"+State_IT_rate);
		System.out.println("State_IT_annual-------------------->"+State_IT_annual);
		System.out.println("State_IT_ytd-------------------->"+State_IT_ytd);
		System.out.println("After_Tax_Income-------------------->"+After_Tax_Income);
		System.out.println("After_Tax_Income_YTD-------------------->"+After_Tax_Income_YTD);
		System.out.println("After_Tax_deduction-------------------->"+After_Tax_deduction);
		System.out.println("After_Tax_deduction_YTD-------------------->"+After_Tax_deduction_YTD);
		
		
		System.out.println("Total federalWHITAmount----------------------->"+federalWHITAmount);
		System.out.println("Total StateWHITAmount----------------------->"+StateWHITAmount);
		System.out.println("Social security amount----------------------->"+social_security_amount);
		System.out.println("Medicare amount----------------------->"+medicare_amount);
		
		
		request.setAttribute("taxyear",taxyear);
		request.setAttribute("state",state);
		request.setAttribute("chequeNo",chequeNo);
		request.setAttribute("chequeDate",chequeDate);
		request.setAttribute("fWHITMarital",fWHITMarital);
		request.setAttribute("fWHITNoFAllowances",fWHITNoFAllowances);
		request.setAttribute("PayPeriod",PayPeriod);
		request.setAttribute("payDurationFrom",tax_paydurationFrom);
		request.setAttribute("payDurationTo",tax_paydurationTo);
		request.setAttribute("PayRateType",PayRateType);
		request.setAttribute("overtime",overtime);
		request.setAttribute("total_gross_pay_hourly",total_gross_pay_hourly);
		//request.setAttribute("gross_pay_monthly",total_gross_pay_monthly);
		request.setAttribute("federal_it_cp",String.valueOf(federalWHITAmount));
		request.setAttribute("social_cp",social_cp);
		request.setAttribute("Medicare_cp",Medicare_cp);
		request.setAttribute("State_IT_cp",String.valueOf(StateWHITAmount));
		request.setAttribute("After_Tax_Income_CP",After_Tax_Income_CP);
		request.setAttribute("After_Tax_deduction_CP",After_Tax_deduction_CP);
		request.setAttribute("federal_it_rate",federal_it_rate);
		request.setAttribute("federal_it_annual",federal_it_annual);
		request.setAttribute("federal_it_ytd",federal_it_ytd);
		request.setAttribute("social_rate",social_rate);
		request.setAttribute("social_annual",social_annual);
		request.setAttribute("social_ytd",social_ytd);
		request.setAttribute("Medicare_rate",Medicare_rate);
		request.setAttribute("Medicare_annual",Medicare_annual);
		request.setAttribute("Medicare_ytd",Medicare_ytd);
		request.setAttribute("State_IT_rate",State_IT_rate);
		request.setAttribute("State_IT_annual",State_IT_annual);
		request.setAttribute("State_IT_ytd",State_IT_ytd);
		request.setAttribute("After_Tax_Income",After_Tax_Income);
		request.setAttribute("After_Tax_Income_YTD",After_Tax_Income_YTD);
		request.setAttribute("After_Tax_deduction",After_Tax_deduction);
		request.setAttribute("After_Tax_deduction_YTD",After_Tax_deduction_YTD);
		
		request.setAttribute("Hourly_payrate",Hourly_payrate);
		request.setAttribute("total_hours",total_hours);
		request.setAttribute("YTD_Hourly",YTD_Hourly);
		//request.setAttribute("Monthly_payrate",Monthly_payrate);
		//request.setAttribute("YTD_Monthly",YTD_Monthly);
		
		request.setAttribute("overtimecount",ot_row_count);
		request.setAttribute("totalGross", totalGross);
		
		request.setAttribute("federalWHITAmount", String.valueOf(federalWHITAmount));
		request.setAttribute("StateWHITAmount_VA", String.valueOf(StateWHITAmount));
		
		netAmount = (Double.parseDouble(totalGross) - 
				(federalWHITAmount + StateWHITAmount + social_security_amount + medicare_amount));
		netAmount = Math.round(netAmount * 100D) / 100D;
		
		//do After tax income calculation
		netAmount = netAmount + Double.parseDouble(After_Tax_Income_CP);
		
		//do After tax deduction calculation
		netAmount = netAmount - Double.parseDouble(After_Tax_deduction_CP);
		
		//Final Net Amount 
		request.setAttribute("netAmount", String.valueOf(netAmount));
		
		if(uniqueEmployeeId != null && tax_paydurationFrom != null && tax_paydurationTo != null
				&& !(tax_paydurationFrom.equalsIgnoreCase("null")) && !(tax_paydurationTo.equalsIgnoreCase("null"))
				&& !(tax_paydurationFrom.equalsIgnoreCase("")) && !(tax_paydurationTo.equalsIgnoreCase(""))){
			boolean payDurationStatus = false;
			payDurationStatus = db.checkEmpPayDurationExist(uniqueEmployeeId,tax_paydurationFrom,tax_paydurationTo);
			if(payDurationStatus){
				
		        request.setAttribute("pageStatus","edit");
		        request.setAttribute("status","Pay Duration already Exist for this Employee");
				return new ModelAndView("requirements/frmEditPayrollCalculator");
			}else{
				request.setAttribute("pageStatus","view");
				return new ModelAndView("requirements/frmViewPayrollCalculator");
			}
		}
		request.setAttribute("pageStatus","view");
		return new ModelAndView("requirements/frmViewPayrollCalculator");
		
		
	}
	
	@RequestMapping("/doEditPayroll.html")
	public ModelAndView doEditPayroll(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(true); 
		DbPayrollERPSessionBean payRollBean=new DbPayrollERPSessionBean();
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("RequirementsAction.initPayrollCalc()");
		String uniqueEmployeeId = null;
		String uniquePayrollId = null;
		
		ArrayList employeeDetails = new ArrayList();
		uniqueEmployeeId = request.getParameter("uniqueId");
		uniquePayrollId = request.getParameter("payrollId");
		
		if(uniqueEmployeeId == null){
			throw new Exception("Employee Not Found!");
			}else{
				
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(uniqueEmployeeId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("uniquePayrollId",uniquePayrollId);
			}
		double netAmount = 0.0;
		double social_security_amount = 0.0;
		double medicare_amount = 0.0;
		double total_ot_amount = 0.0;
		String taxyear = null;
		String state = null;
		String chequeNo = null;
		String chequeDate = null;
		String NofExemptions = null;
		String age_exemptions = null;
		String additional_withholding = null;
		String fWHITMarital = null;
		String fWHITNoFAllowances = null;
		String PayPeriod = null;
		String tax_paydurationFrom = null;
		String tax_paydurationTo = null;
		String PayRateType = null;
		String overtime = null;
		String Hourly_payrate =null;
		String total_hours = null;
		String YTD_Hourly = null;
		String total_gross_pay_hourly = null;
		//String Monthly_payrate = null;
		//String YTD_Monthly = null;
		//String total_gross_pay_monthly = null;
		
		String federal_it_rate = null;
		String federal_it_annual = null;
		String federal_it_ytd = null;
		String federal_it_cp = null;
		String social_rate = null;
		String social_annual = null;
		String social_ytd = null;
		String social_cp = null;
		String Medicare_rate = null;
		String Medicare_annual = null;
		String Medicare_ytd = null;
		String Medicare_cp = null;
		String State_IT_rate = null;
		String State_IT_annual = null;
		String State_IT_ytd = null;
		String State_IT_cp = null;
		String After_Tax_Income = null;
		String After_Tax_Income_YTD = null;
		String After_Tax_Income_CP = null;
		String After_Tax_deduction = null;
		String After_Tax_deduction_YTD = null;
		String After_Tax_deduction_CP = null;
		
		String totalGross = null;
		String ot_row_count = null;
		double StateWHITAmount = 0;
		int personalAllowances = 0;
		int dependentAllowances = 0;
		String filingStatus = null;
		int totalAllowances = 0;
		double additionalStateWithHolding = 0;
		int additionalAllowances = 0;
		String texarnaResident = null;
		String withHoldingCode = null;
		int basicAllowances = 0;
		int personalDeduction = 0;
		int dependentDeduction = 0;
		int WHExemptions = 0;
		String RateCode = null;
		
		String SpouseBlind = null;
		String PersonalBlind = null;
		String HouseHold = null;
		String Student = null;
		
		int extendedAllowances = 0;
		int regularAllowances = 0;
		double exemptionAmount = 0;
		double WithholdingRate = 0;
		double DistrictRate = 0;
		int DistrictNo = 0;
		double schoolDistITAmount = 0.0;
	
		taxyear = (String) request.getParameter("taxyear");
		state = (String) request.getParameter("state");
		chequeNo = (String) request.getParameter("chequeNo");
		chequeDate = (String) request.getParameter("chequeDate");
		
		fWHITMarital = (String) request.getParameter("fWHITMarital");
		fWHITNoFAllowances = (String) request.getParameter("fWHITNoFAllowances");
		PayPeriod = (String) request.getParameter("PayPeriod");
		tax_paydurationFrom = (String)request.getParameter("payDurationFrom");
		tax_paydurationTo = (String)request.getParameter("payDurationTo");
		PayRateType = (String) request.getParameter("PayRateType");
		overtime = (String) request.getParameter("overtime");
		
		total_gross_pay_hourly = (String) request.getParameter("total_gross_pay_hourly");
		
		//total_gross_pay_monthly = (String) request.getParameter("total_gross_pay_monthly");
	
		
		federal_it_cp = (String) request.getParameter("federal_it_cp");
		
		social_cp = (String) request.getParameter("social_cp");
		
		Medicare_cp = (String) request.getParameter("Medicare_cp");
		
		State_IT_cp = (String) request.getParameter("State_IT_cp");
		
		After_Tax_Income_CP = (String) request.getParameter("After_Tax_Income_CP");
		
		After_Tax_deduction_CP = (String) request.getParameter("After_Tax_deduction_CP");
		
		Hourly_payrate = request.getParameter("Hourly_payrate");
		total_hours = request.getParameter("total_hours");
		YTD_Hourly = request.getParameter("YTD_Hourly");
		//Monthly_payrate = request.getParameter("Monthly_payrate");
		//YTD_Monthly = request.getParameter("YTD_Monthly");
		
		ot_row_count = request.getParameter("overtimecount");
		
		if(overtime != null && overtime != "null" && !(overtime.equalsIgnoreCase("null"))){
		System.out.println("ot_row_count-------------->"+ot_row_count);
		int ot_row_count_int = 0;
		ot_row_count_int = Integer.parseInt(ot_row_count);
		String[] overtimerate = new String[ot_row_count_int+1];
		String[] overtimepaytype = new String[ot_row_count_int+1];
		String[] Hour_ot = new String[ot_row_count_int+1];
		String[] ytd_ot = new String[ot_row_count_int+1];
		String[] cp_ot = new String[ot_row_count_int+1];
		
		
		for(int i=0;i<=ot_row_count_int;i++){
			System.out.println("request.getParameter(overtimerate"+i+")"+request.getParameter("overtimerate"+i));
			//System.out.println("overtimerate["+i+"]------------->"+overtimerate[i]);
		overtimerate[i] = request.getParameter("overtimerate"+i);
		overtimepaytype[i] = request.getParameter("overtimepaytype"+i);
		Hour_ot[i] = request.getParameter("Hour_ot"+i);
		ytd_ot[i] = request.getParameter("ytd_ot"+i);
		cp_ot[i] = request.getParameter("cp_ot"+i);
		
		total_ot_amount = (total_ot_amount + Double.parseDouble(cp_ot[i]));
		
		System.out.println("overtimerate["+i+"]---------->"+overtimerate[i]);
		System.out.println("overtimepaytype["+i+"]---------->"+overtimepaytype[i]);
		System.out.println("Hour_ot["+i+"]---------->"+Hour_ot[i]);
		System.out.println("ytd_ot["+i+"]---------->"+ytd_ot[i]);
		System.out.println("cp_ot["+i+"]---------->"+cp_ot[i]);
		
		System.out.println("total_ot_amount-------------->"+total_ot_amount);
		
		request.setAttribute("overtimerate"+i,overtimerate[i]);
		request.setAttribute("overtimepaytype"+i,overtimepaytype[i]);
		request.setAttribute("Hour_ot"+i,Hour_ot[i]);
		request.setAttribute("ytd_ot"+i,ytd_ot[i]);
		request.setAttribute("cp_ot"+i,cp_ot[i]);
		}
		}
		
		federal_it_rate = request.getParameter("federal_it_rate");
		federal_it_annual = request.getParameter("federal_it_annual");
		federal_it_ytd = request.getParameter("federal_it_ytd");
		social_rate = request.getParameter("social_rate");
		social_annual = request.getParameter("social_annual");
		social_ytd = request.getParameter("social_ytd");
		Medicare_rate = request.getParameter("Medicare_rate");
		Medicare_annual = request.getParameter("Medicare_annual");
		Medicare_ytd = request.getParameter("Medicare_ytd");
		State_IT_rate = request.getParameter("State_IT_rate");
		State_IT_annual = request.getParameter("State_IT_annual");
		State_IT_ytd = request.getParameter("State_IT_ytd");
		After_Tax_Income = request.getParameter("After_Tax_Income");
		After_Tax_Income_YTD = request.getParameter("After_Tax_Income_YTD");
		After_Tax_deduction = request.getParameter("After_Tax_deduction");
		After_Tax_deduction_YTD = request.getParameter("After_Tax_deduction_YTD");
		
		/*if(PayRateType != null && PayRateType.equalsIgnoreCase("Hourly")){
			totalGross = total_gross_pay_hourly;
		}else if(PayRateType != null && PayRateType.equalsIgnoreCase("Monthly")){
			totalGross = total_gross_pay_monthly;
		}else{
			throw new Exception("Problem with Total Gross Amount!");
		}
		*/
		
		if(PayRateType != null){
			totalGross = total_gross_pay_hourly;
		}else{
			throw new Exception("Problem with Total Gross Amount!");
		}
		
		totalGross = String.valueOf((Double.parseDouble(totalGross) + total_ot_amount));
		
		/*
		 * Start-------------Calculate federal Withheld Amount-----------*/
		
		double federalWHITAmount = (double)payRollBean.calculateFederalITWithholdAmount(fWHITMarital,Integer.parseInt(fWHITNoFAllowances),Double.parseDouble(totalGross),PayPeriod);
		
		/*
		 * Start-------------Calculate federal Withheld Amount-----------*/
		
		
		
		/*
		 * Start-------------Calculate Social Security Amount and Medicare Amount-----------*/
		
		social_security_amount = (Double.parseDouble(totalGross) * Double.parseDouble(social_rate));
		social_security_amount = Math.round(social_security_amount * 100D) / 100D;
		
		medicare_amount = (Double.parseDouble(totalGross) * Double.parseDouble(Medicare_rate));
		medicare_amount = Math.round(medicare_amount * 100D) / 100D;
		
		social_cp = String.valueOf(social_security_amount);
		Medicare_cp = String.valueOf(medicare_amount);
		
		/*
		 * End-------------Calculate Social Security Amount and Medicare Amount-----------*/
		
		
		if(state != null && state.equalsIgnoreCase("Virginia")){
			NofExemptions = (String) request.getParameter("VI_NofExemptions");
			age_exemptions = (String) request.getParameter("VI_age_exemptions");
			additional_withholding = (String) request.getParameter("VI_additional_withholding");
			
			System.out.println("NofExemptions--------------->"+NofExemptions);
			System.out.println("age_exemptions--------------->"+age_exemptions);
			System.out.println("additional_withholding--------------->"+additional_withholding);
			
			request.setAttribute("VI_NofExemptions",NofExemptions);
			request.setAttribute("VI_age_exemptions",age_exemptions);
			request.setAttribute("VI_additional_withholding",additional_withholding);
			
			StateWHITAmount = (double)payRollBean.calculateVirginiaStateWithHold(Double.parseDouble(totalGross), PayPeriod,
					Double.parseDouble(additional_withholding), Integer.parseInt(age_exemptions), Integer.parseInt(NofExemptions));
		}else if(state != null && state.equalsIgnoreCase("Georgia")){
			personalAllowances = Integer.parseInt(request.getParameter("personal_allowances"));
			dependentAllowances = Integer.parseInt(request.getParameter("dependent_allowances"));
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("total_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			additionalAllowances = Integer.parseInt(request.getParameter("additional_allowances"));
			
			request.setAttribute("personal_allowances",String.valueOf(personalAllowances));
			request.setAttribute("dependent_allowances",String.valueOf(dependentAllowances));
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("total_allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			request.setAttribute("additional_allowances",String.valueOf(additionalAllowances));
			StateWHITAmount = (double)payRollBean.calculateGeorgiaWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, additionalAllowances, personalAllowances, dependentAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Arkansas")){
			totalAllowances = Integer.parseInt(request.getParameter("total_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			texarnaResident = request.getParameter("texarna_resident");
			
			request.setAttribute("total_allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			request.setAttribute("texarna_resident",String.valueOf(texarnaResident));
			
			StateWHITAmount = (double)payRollBean.calculateArkansasWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, texarnaResident);
		}else if(state != null && state.equalsIgnoreCase("Idaho")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("total_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("total_allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateIdahoWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Alabama")){
			filingStatus = request.getParameter("filing_status");
			dependentAllowances = Integer.parseInt(request.getParameter("dependent_allowances"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("dependent_allowances",String.valueOf(dependentAllowances));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateAlabamaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, dependentAllowances, filingStatus, federalWHITAmount);
		}else if(state != null && state.equalsIgnoreCase("Connecticut")){
			withHoldingCode = request.getParameter("withholding_code");
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("withholding_code",String.valueOf(withHoldingCode));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculcateConnecticutWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, withHoldingCode);
		}else if(state != null && state.equalsIgnoreCase("Illinois")){
			additionalAllowances = Integer.parseInt(request.getParameter("additional_allowances"));
			basicAllowances = Integer.parseInt(request.getParameter("basic_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("additional_allowances",String.valueOf(additionalAllowances));
			request.setAttribute("basic_allowances",String.valueOf(basicAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateIllinoisWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, additionalAllowances, basicAllowances);
		}else if(state != null && state.equalsIgnoreCase("Indiana")){
			personalDeduction = Integer.parseInt(request.getParameter("personal_deduction"));
			dependentDeduction = Integer.parseInt(request.getParameter("dependent_deduction"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("personal_deduction",String.valueOf(personalDeduction));
			request.setAttribute("dependent_deduction",String.valueOf(dependentDeduction));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateIndianaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, personalDeduction, dependentDeduction);
		}else if(state != null && state.equalsIgnoreCase("Kentucky")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateKentuckyWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("Maine")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMaineWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Minnesota")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMinnesotaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Wisconsin")){
			filingStatus = request.getParameter("filing_status");
			WHExemptions = Integer.parseInt(request.getParameter("WHExemptions"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("WHExemptions",String.valueOf(WHExemptions));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateWisconsinWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, WHExemptions, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Rhode Island")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateRhodeislandWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("Vermont")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateVermontWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("New Jersey")){
			filingStatus = request.getParameter("filing_status");
			RateCode = request.getParameter("rate_code");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("rate_code",String.valueOf(RateCode));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateNewJerseyWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, RateCode, fWHITMarital);
		}else if(state != null && state.equalsIgnoreCase("District of Columbia")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateColumbiaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("Nebraska")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateNebraskaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Montana")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMontanaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("New Mexico")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateNewMexicoWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("West Virginia")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateWestVirginiaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Hawaii")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateHawaiiWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Louisiana")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			personalAllowances = Integer.parseInt(request.getParameter("personal_allowances"));
			dependentAllowances = Integer.parseInt(request.getParameter("dependent_allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("personal_allowances",String.valueOf(personalAllowances));
			request.setAttribute("dependent_allowances",String.valueOf(dependentAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateLouisianaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, personalAllowances, dependentAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Massachusetts")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			SpouseBlind = request.getParameter("spouse_blind");
			PersonalBlind = request.getParameter("personal_blind");
			HouseHold = request.getParameter("house_hold");
			Student = request.getParameter("student");
			
			request.setAttribute("spouse_blind",String.valueOf(SpouseBlind));
			request.setAttribute("personal_blind",String.valueOf(PersonalBlind));
			request.setAttribute("house_hold",String.valueOf(HouseHold));
			request.setAttribute("student",String.valueOf(Student));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMassachusettsWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, social_security_amount, medicare_amount, SpouseBlind, PersonalBlind, HouseHold, Student);
		}else if(state != null && state.equalsIgnoreCase("Oregon")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateOregonWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, federalWHITAmount);
		}else if(state != null && state.equalsIgnoreCase("South Carolina")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateSouthCarolinaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("North Carolina")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateNorthCarolinaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("North Dakota")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateNorthDakotaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Utah")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateUtahWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Delaware")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateDelawareWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Colorado")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateColoradoWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("California")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			extendedAllowances = Integer.parseInt(request.getParameter("extended_allowances"));
			regularAllowances = Integer.parseInt(request.getParameter("regular_allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("extended_allowances",String.valueOf(extendedAllowances));
			request.setAttribute("regular_allowances",String.valueOf(regularAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateCaliforniaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, extendedAllowances, regularAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Iowa")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateIowaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, federalWHITAmount);
		}else if(state != null && state.equalsIgnoreCase("Kansas")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateKansasWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Mississippi")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			exemptionAmount = Double.parseDouble(request.getParameter("exemption_amount"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("exemption_amount",String.valueOf(exemptionAmount));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMississippiWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, exemptionAmount, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Michigan")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMichiganWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
		}else if(state != null && state.equalsIgnoreCase("Missouri")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateMissouriWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, federalWHITAmount);
		}else if(state != null && state.equalsIgnoreCase("Pennsylvania")){
						
			StateWHITAmount = (double)payRollBean.calculatePennsylvaniaWithHeld(Double.parseDouble(totalGross), PayPeriod);
		}else if(state != null && state.equalsIgnoreCase("Arizona")){
			WithholdingRate = Double.parseDouble(request.getParameter("withholding_rate"));
			
			request.setAttribute("withholding_rate",String.valueOf(WithholdingRate));
			
			StateWHITAmount = (double)payRollBean.calculateArizonaWithHeld(Double.parseDouble(totalGross), PayPeriod, WithholdingRate);
		}else if(state != null && state.equalsIgnoreCase("Oklahoma")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateOklahomaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Ohio")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			DistrictRate = Double.parseDouble(request.getParameter("district_rate"));
			DistrictNo = Integer.parseInt(request.getParameter("district_no"));
			
			request.setAttribute("district_rate",String.valueOf(DistrictRate));
			request.setAttribute("district_no",String.valueOf(DistrictNo));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			StateWHITAmount = (double)payRollBean.calculateOhioWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
			schoolDistITAmount = (double)payRollBean.calculateOhioSchoolDistrictTax(Double.parseDouble(totalGross), PayPeriod, totalAllowances, DistrictRate);
			request.setAttribute("schoolDistITAmount",String.valueOf(schoolDistITAmount));
		}
		
		System.out.println("Hourly_payrate-------------------->"+Hourly_payrate);
		System.out.println("total_hours-------------------->"+total_hours);
		System.out.println("YTD_Hourly-------------------->"+YTD_Hourly);
		//System.out.println("Monthly_payrate-------------------->"+Monthly_payrate);
		//System.out.println("YTD_Monthly-------------------->"+YTD_Monthly);
				
		System.out.println("ot_row_count-------------------->"+ot_row_count);
		
		
		
		
		System.out.println("taxyear------------------->"+taxyear);
		System.out.println("state------------------->"+state);
		System.out.println("chequeNo------------------->"+chequeNo);
		System.out.println("chequeDate------------------->"+chequeDate);
		System.out.println("VI_NofExemptions------------------->"+NofExemptions);
		System.out.println("VI_age_exemptions------------------->"+age_exemptions);
		System.out.println("VI_additional_withholding------------------->"+additional_withholding);
		System.out.println("fWHITMarital------------------->"+fWHITMarital);
		System.out.println("fWHITNoFAllowances------------------->"+fWHITNoFAllowances);
		System.out.println("PayPeriod------------------->"+PayPeriod);
		System.out.println("tax_paydurationFrom------------->"+tax_paydurationFrom);
		System.out.println("tax_paydurationTo------------->"+tax_paydurationTo);
		System.out.println("PayRateType------------------->"+PayRateType);
		System.out.println("overtime------------------->"+overtime);
		System.out.println("total_gross_pay_hourly------------------->"+total_gross_pay_hourly);
		//System.out.println("gross_pay_monthly------------------->"+total_gross_pay_monthly);
		System.out.println("federal_it_cp------------------->"+federal_it_cp);
		System.out.println("social_cp------------------->"+social_cp);
		System.out.println("Medicare_cp------------------->"+Medicare_cp);
		System.out.println("State_IT_cp------------------->"+State_IT_cp);
		System.out.println("After_Tax_Income_CP------------------->"+After_Tax_Income_CP);
		System.out.println("After_Tax_deduction_CP------------------->"+After_Tax_deduction_CP);
		
		System.out.println("federal_it_rate-------------------->"+federal_it_rate);
		System.out.println("federal_it_annual-------------------->"+federal_it_annual);
		System.out.println("federal_it_ytd-------------------->"+federal_it_ytd);
		System.out.println("social_rate-------------------->"+social_rate);
		System.out.println("social_annual-------------------->"+social_annual);
		System.out.println("social_ytd-------------------->"+social_ytd);
		System.out.println("Medicare_rate-------------------->"+Medicare_rate);
		System.out.println("Medicare_annual-------------------->"+Medicare_annual);
		System.out.println("Medicare_ytd-------------------->"+Medicare_ytd);
		System.out.println("State_IT_rate-------------------->"+State_IT_rate);
		System.out.println("State_IT_annual-------------------->"+State_IT_annual);
		System.out.println("State_IT_ytd-------------------->"+State_IT_ytd);
		System.out.println("After_Tax_Income-------------------->"+After_Tax_Income);
		System.out.println("After_Tax_Income_YTD-------------------->"+After_Tax_Income_YTD);
		System.out.println("After_Tax_deduction-------------------->"+After_Tax_deduction);
		System.out.println("After_Tax_deduction_YTD-------------------->"+After_Tax_deduction_YTD);
		
		
		System.out.println("Total federalWHITAmount----------------------->"+federalWHITAmount);
		System.out.println("Total StateWHITAmount----------------------->"+StateWHITAmount);
		System.out.println("Social security amount----------------------->"+social_security_amount);
		System.out.println("Medicare amount----------------------->"+medicare_amount);
		
		
		request.setAttribute("taxyear",taxyear);
		request.setAttribute("state",state);
		request.setAttribute("chequeNo",chequeNo);
		request.setAttribute("chequeDate",chequeDate);
		request.setAttribute("fWHITMarital",fWHITMarital);
		request.setAttribute("fWHITNoFAllowances",fWHITNoFAllowances);
		request.setAttribute("PayPeriod",PayPeriod);
		request.setAttribute("payDurationFrom",tax_paydurationFrom);
		request.setAttribute("payDurationTo",tax_paydurationTo);
		request.setAttribute("PayRateType",PayRateType);
		request.setAttribute("overtime",overtime);
		request.setAttribute("total_gross_pay_hourly",total_gross_pay_hourly);
		//request.setAttribute("gross_pay_monthly",total_gross_pay_monthly);
		request.setAttribute("federal_it_cp",String.valueOf(federalWHITAmount));
		request.setAttribute("social_cp",social_cp);
		request.setAttribute("Medicare_cp",Medicare_cp);
		request.setAttribute("State_IT_cp",String.valueOf(StateWHITAmount));
		request.setAttribute("After_Tax_Income_CP",After_Tax_Income_CP);
		request.setAttribute("After_Tax_deduction_CP",After_Tax_deduction_CP);
		request.setAttribute("federal_it_rate",federal_it_rate);
		request.setAttribute("federal_it_annual",federal_it_annual);
		request.setAttribute("federal_it_ytd",federal_it_ytd);
		request.setAttribute("social_rate",social_rate);
		request.setAttribute("social_annual",social_annual);
		request.setAttribute("social_ytd",social_ytd);
		request.setAttribute("Medicare_rate",Medicare_rate);
		request.setAttribute("Medicare_annual",Medicare_annual);
		request.setAttribute("Medicare_ytd",Medicare_ytd);
		request.setAttribute("State_IT_rate",State_IT_rate);
		request.setAttribute("State_IT_annual",State_IT_annual);
		request.setAttribute("State_IT_ytd",State_IT_ytd);
		request.setAttribute("After_Tax_Income",After_Tax_Income);
		request.setAttribute("After_Tax_Income_YTD",After_Tax_Income_YTD);
		request.setAttribute("After_Tax_deduction",After_Tax_deduction);
		request.setAttribute("After_Tax_deduction_YTD",After_Tax_deduction_YTD);
		
		request.setAttribute("Hourly_payrate",Hourly_payrate);
		request.setAttribute("total_hours",total_hours);
		request.setAttribute("YTD_Hourly",YTD_Hourly);
		//request.setAttribute("Monthly_payrate",Monthly_payrate);
		//request.setAttribute("YTD_Monthly",YTD_Monthly);
		
		request.setAttribute("overtimecount",ot_row_count);
		request.setAttribute("totalGross", totalGross);
		
		request.setAttribute("federalWHITAmount", String.valueOf(federalWHITAmount));
		request.setAttribute("StateWHITAmount_VA", String.valueOf(StateWHITAmount));
		
		netAmount = (Double.parseDouble(totalGross) - 
				(federalWHITAmount + StateWHITAmount + social_security_amount + medicare_amount));
		netAmount = Math.round(netAmount * 100D) / 100D;
		
		//do After tax income calculation
		netAmount = netAmount + Double.parseDouble(After_Tax_Income_CP);
		
		//do After tax deduction calculation
		netAmount = netAmount - Double.parseDouble(After_Tax_deduction_CP);
		
		//Final Net Amount 
		request.setAttribute("netAmount", String.valueOf(netAmount));
		
		request.setAttribute("pageStatus","view");
		
		return new ModelAndView("requirements/frmViewPayrollCalculator");
		
	}
	
	@SuppressWarnings("unused")
	@RequestMapping("/genertePayStubPdf.html")
	public ModelAndView genertePayStubPdf(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
    	String empId = null;
	    String empdberpPartyid = null;
	    String empSSNNo = null;
	    String empSalutation = null;
	    String empFname = null;
	    String empLname = null;
	    String empGender = null;
	    String empMaritalStatus = null;
	    String empEmailId = null;
	    String empAddress1c = null;
	    String empCityc = null;
	    String empStatec = null;
	    String empCountryc = null;
	    String empPostalCodec = null;
	    String empCompany = null;
	    String empJobTitle = null;
	    String empDepartment = null;
	    String empJobLocation = null;
	    String empPayGroup = null;
	    String empPayratePerhour = null;
	    String empPayPeriod = null;
		
		Debug.print("RequirementsAction.initPayrollCalc()");
		String uniqueEmployeeId = null;
		ArrayList employeeDetails = new ArrayList();
		uniqueEmployeeId = request.getParameter("uniqueId");
		if(uniqueEmployeeId == null){
			throw new Exception("Employee Not Found!");
			}else{
				
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(uniqueEmployeeId);
		        
		        if(employeeDetails!=null && employeeDetails.size()!=0){
					Iterator itr = employeeDetails.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
				    	empId = TempList[3];
					    empdberpPartyid = TempList[4];
					    empSSNNo = TempList[5];
					    empSalutation = TempList[6];
					    empFname = TempList[8];
					    empLname = TempList[10];
					    empGender = TempList[11];
					    empMaritalStatus = TempList[12];
					    empEmailId = TempList[14];
					    empAddress1c = TempList[19];
					    empCityc = TempList[23];
					    empStatec = TempList[25];
					    empCountryc = TempList[27];
					    empPostalCodec = TempList[29];
					    empCompany = TempList[30];
					    empJobTitle = TempList[32];
					    empDepartment = TempList[33];
					    empJobLocation = TempList[34];
					    empPayGroup = TempList[35];
					    empPayratePerhour = TempList[36];
					    empPayPeriod = TempList[37];
					    
					 //empSSNNo = String.valueOf(empSSNNo).substring(5);
				    }
		        }
			}
		
		
			 
		
		double netAmount = 0.0;
		double social_security_amount = 0.0;
		double medicare_amount = 0.0;
		double total_ot_amount = 0.0;
		double total_ot_ytd_amount = 0.0;
		int total_ot_hours=0;
		String totalFinalHours=null;
		String taxyear = null;
		String state = null;
		String chequeNo = null;
		String chequeDate = null;
		String NofExemptions = null;
		String age_exemptions = null;
		String additional_withholding = null;
		String fWHITMarital = null;
		String fWHITNoFAllowances = null;
		String PayPeriod = null;
		String PayRateType = null;
		String overtime = null;
		String Hourly_payrate =null;
		String total_hours = null;
		String YTD_Hourly = null;
		String total_gross_pay_hourly = null;
		//String Monthly_payrate = null;
		//String YTD_Monthly = null;
		//String total_gross_pay_monthly = null;
		
		String federal_it_rate = null;
		String federal_it_annual = null;
		String federal_it_ytd = null;
		String federal_it_cp = null;
		String social_rate = null;
		String social_annual = null;
		String social_ytd = null;
		String social_cp = null;
		String Medicare_rate = null;
		String Medicare_annual = null;
		String Medicare_ytd = null;
		String Medicare_cp = null;
		String State_IT_rate = null;
		String State_IT_annual = null;
		String State_IT_ytd = null;
		String State_IT_cp = null;
		String After_Tax_Income = null;
		String After_Tax_Income_YTD = null;
		String After_Tax_Income_CP = null;
		String After_Tax_deduction = null;
		String After_Tax_deduction_YTD = null;
		String After_Tax_deduction_CP = null;
		
		String totalGross = null;
		String totalFinalGross = null;
		String totalYTDGross = null;
		String totalFinalYTDGross = null;
		String ot_row_count = null;
		double StateWHITAmount = 0;
		int personalAllowances = 0;
		int dependentAllowances = 0;
		String filingStatus = null;
		int totalAllowances = 0;
		double additionalStateWithHolding = 0;
		int additionalAllowances = 0;
		String texarnaResident = null;
		
		String withHoldingCode = null;
		int basicAllowances = 0;
		int personalDeduction = 0;
		int dependentDeduction = 0;
		int WHExemptions = 0;
		String RateCode = null;
		
		String SpouseBlind = null;
		String PersonalBlind = null;
		String HouseHold = null;
		String Student = null;
		
		int extendedAllowances = 0;
		int regularAllowances = 0;
		double exemptionAmount = 0;
		double WithholdingRate = 0;
		double DistrictRate = 0;
		int DistrictNo = 0;
		double schoolDistITAmount = 0.0;
		
		String state_wh_info = null;
		String tax_paydurationFrom = null;
		String tax_paydurationTo = null;
		String ot_payrateArray = "0";
		String ot_payrate_typeArray = "0";
		String ot_total_hours_workedArray = "0";
		String ot_pay_ytdArray = "0";
		String ot_pay_cpArray = "0";
		
		
		//boolean empPayrollInsertStatus = false;
		
		taxyear = (String) request.getParameter("taxyear");
		state = (String) request.getParameter("state");
		chequeNo = (String) request.getParameter("chequeNo");
		DateFormat format = new SimpleDateFormat( "MM-dd-yyy ");
        chequeDate = (String) request.getParameter("chequeDate");
        DateFormat inputFormatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        Date date = inputFormatter.parse(chequeDate);

        DateFormat outputFormatter = new SimpleDateFormat("MM-dd-yyyy");
        chequeDate= outputFormatter.format(date); 
        System.out.println(chequeDate);
        
		netAmount = Double.parseDouble(request.getParameter("netAmount"));
		fWHITMarital = (String) request.getParameter("fWHITMarital");
		fWHITNoFAllowances = (String) request.getParameter("fWHITNoFAllowances");
		PayPeriod = (String) request.getParameter("PayPeriod");
		tax_paydurationFrom = (String)request.getParameter("payDurationFrom");
		tax_paydurationTo = (String)request.getParameter("payDurationTo");
		PayRateType = (String) request.getParameter("PayRateType");
		System.out.println("Payratetype==========="+PayRateType);
		overtime = (String) request.getParameter("overtime");
		
		Hourly_payrate = request.getParameter("Hourly_payrate");
		total_hours = request.getParameter("total_hours");
		YTD_Hourly = request.getParameter("YTD_Hourly");
		total_gross_pay_hourly = (String) request.getParameter("total_gross_pay_hourly");
		
		//Monthly_payrate = request.getParameter("Monthly_payrate");
		//YTD_Monthly = request.getParameter("YTD_Monthly");
		//total_gross_pay_monthly = (String) request.getParameter("total_gross_pay_monthly");

		ot_row_count = request.getParameter("overtimecount");
		System.out.println("ot_row_count-------------->"+ot_row_count);
		String[] overtimerate = null;
		String[] overtimepaytype = null;
		String[] Hour_ot = null;
		String[] ytd_ot = null;
		String[] cp_ot = null;
		int ot_row_count_int = 0;
		
		if(overtime != null && overtime != "null" && !(overtime.equalsIgnoreCase("null"))){
		
		ot_row_count_int = Integer.parseInt(ot_row_count);
		overtimerate = new String[ot_row_count_int+1];
		overtimepaytype = new String[ot_row_count_int+1];
		Hour_ot = new String[ot_row_count_int+1];
		ytd_ot = new String[ot_row_count_int+1];
		cp_ot = new String[ot_row_count_int+1];
		
		
		for(int i=0;i<=ot_row_count_int;i++){
			System.out.println("request.getParameter(overtimerate"+i+")"+request.getParameter("overtimerate"+i));
			//System.out.println("overtimerate["+i+"]------------->"+overtimerate[i]);
		overtimerate[i] = request.getParameter("overtimerate"+i);
		overtimepaytype[i] = request.getParameter("overtimepaytype"+i);
		Hour_ot[i] = request.getParameter("Hour_ot"+i);
		ytd_ot[i] = request.getParameter("ytd_ot"+i);
		cp_ot[i] = request.getParameter("cp_ot"+i);
		
		ot_payrateArray = ot_payrateArray+","+overtimerate[i];
		ot_payrate_typeArray = ot_payrate_typeArray+","+overtimepaytype[i];
		ot_total_hours_workedArray = ot_total_hours_workedArray+","+Hour_ot[i];
		ot_pay_ytdArray = ot_pay_ytdArray+","+ytd_ot[i];
		ot_pay_cpArray = ot_pay_cpArray+","+cp_ot[i];
		
		total_ot_amount = (total_ot_amount + Double.parseDouble(cp_ot[i]));
		total_ot_hours = (total_ot_hours + Integer.parseInt(Hour_ot[i]));
		total_ot_ytd_amount = (total_ot_ytd_amount + Double.parseDouble(ytd_ot[i]) + Double.parseDouble(cp_ot[i]));
		
		
		System.out.println("overtimerate["+i+"]---------->"+overtimerate[i]);
		System.out.println("overtimepaytype["+i+"]---------->"+overtimepaytype[i]);
		System.out.println("Hour_ot["+i+"]---------->"+Hour_ot[i]);
		System.out.println("ytd_ot["+i+"]---------->"+ytd_ot[i]);
		System.out.println("cp_ot["+i+"]---------->"+cp_ot[i]);
		
		System.out.println("total_ot_amount-------------->"+total_ot_amount);
		System.out.println("total_ot_ytd_amount----------->"+total_ot_ytd_amount);
		}
		}
		
		federal_it_rate = request.getParameter("federal_it_rate");
		federal_it_annual = request.getParameter("federal_it_annual");
		federal_it_ytd = request.getParameter("federal_it_ytd");
		//federal_it_ytd = String.valueOf(Math.round(Double.parseDouble(federal_it_ytd) * 100D) / 100D);
		federal_it_cp = (String) request.getParameter("federal_it_cp");
		federal_it_ytd = String.valueOf(Math.round((Double.parseDouble(federal_it_ytd)+Double.parseDouble(federal_it_cp)) * 100D) / 100D);
		
		social_rate = request.getParameter("social_rate");
		social_annual = request.getParameter("social_annual");
		social_ytd = request.getParameter("social_ytd");
		//social_ytd = String.valueOf(Math.round(Double.parseDouble(social_ytd) * 100D) / 100D);
		social_cp = (String) request.getParameter("social_cp");
		social_ytd = String.valueOf(Math.round((Double.parseDouble(social_ytd) + Double.parseDouble(social_cp)) * 100D) / 100D);
		
		Medicare_rate = request.getParameter("Medicare_rate");
		Medicare_annual = request.getParameter("Medicare_annual");
		Medicare_ytd = request.getParameter("Medicare_ytd");
		//Medicare_ytd = String.valueOf(Math.round(Double.parseDouble(Medicare_ytd) * 100D) / 100D);
		Medicare_cp = (String) request.getParameter("Medicare_cp");
		Medicare_ytd = String.valueOf(Math.round((Double.parseDouble(Medicare_ytd) + Double.parseDouble(Medicare_cp)) * 100D) / 100D);
		
		State_IT_rate = request.getParameter("State_IT_rate");
		State_IT_annual = request.getParameter("State_IT_annual");
		State_IT_ytd = request.getParameter("State_IT_ytd");
		//State_IT_ytd = String.valueOf(Math.round(Double.parseDouble(State_IT_ytd) * 100D) / 100D);
		State_IT_cp = (String) request.getParameter("State_IT_cp");
		State_IT_ytd = String.valueOf(Math.round((Double.parseDouble(State_IT_ytd) + Double.parseDouble(State_IT_cp)) * 100D) / 100D);
		
		After_Tax_Income = request.getParameter("After_Tax_Income");
		After_Tax_Income_YTD = request.getParameter("After_Tax_Income_YTD");
		//After_Tax_Income_YTD = String.valueOf(Math.round(Double.parseDouble(After_Tax_Income_YTD) * 100D) / 100D);
		After_Tax_Income_CP = (String) request.getParameter("After_Tax_Income_CP");
		After_Tax_Income_YTD = String.valueOf(Math.round((Double.parseDouble(After_Tax_Income_YTD) + Double.parseDouble(After_Tax_Income_CP)) * 100D) / 100D);
		
		After_Tax_deduction = request.getParameter("After_Tax_deduction");
		After_Tax_deduction_YTD = request.getParameter("After_Tax_deduction_YTD");
		//After_Tax_deduction_YTD = String.valueOf(Math.round(Double.parseDouble(After_Tax_deduction_YTD) * 100D) / 100D);
		After_Tax_deduction_CP = (String) request.getParameter("After_Tax_deduction_CP");
		After_Tax_deduction_YTD = String.valueOf(Math.round((Double.parseDouble(After_Tax_deduction_YTD) + Double.parseDouble(After_Tax_deduction_CP)) * 100D) / 100D);
		
		
		
		/*if(PayRateType != null && PayRateType.equalsIgnoreCase("Hourly")){
			totalGross = total_gross_pay_hourly;
			totalYTDGross = YTD_Hourly;
		}else if(PayRateType != null && PayRateType.equalsIgnoreCase("Monthly")){
			totalGross = total_gross_pay_monthly;
			totalYTDGross = YTD_Monthly;
		}else{
			throw new Exception("Problem with Total Gross Amount!");
		}*/
		
		if(PayRateType != null){
			totalGross = total_gross_pay_hourly;
			totalYTDGross = YTD_Hourly;
		}else{
			throw new Exception("Problem with Total Gross Amount!");
		}
		
		totalFinalGross = String.valueOf((Double.parseDouble(totalGross) + total_ot_amount));
		totalFinalGross = String.valueOf(Math.round(Double.parseDouble(totalFinalGross) * 100D) / 100D);
		System.out.println("totalFinalGross====="+totalFinalGross+"==========totalYTDGross"+totalYTDGross);
		totalYTDGross = String.valueOf((Double.parseDouble(totalYTDGross) + Double.parseDouble(	totalGross)));
		totalYTDGross = String.valueOf(Math.round(Double.parseDouble(totalYTDGross) * 100D) / 100D);
		totalFinalYTDGross = String.valueOf((Double.parseDouble(totalYTDGross) + total_ot_ytd_amount));
		totalFinalYTDGross = String.valueOf(Math.round(Double.parseDouble(totalFinalYTDGross) * 100D) / 100D);
		//double federalWHITAmount = (double)payRollBean.calculateFederalITWithholdAmount(fWHITMarital,Integer.parseInt(fWHITNoFAllowances),Double.parseDouble(totalGross),PayPeriod);
		totalFinalHours = String.valueOf(Integer.parseInt(total_hours) + total_ot_hours);
		
		if(state != null && state.equalsIgnoreCase("Virginia")){
			NofExemptions = (String) request.getParameter("VI_NofExemptions");
			age_exemptions = (String) request.getParameter("VI_age_exemptions");
			additional_withholding = (String) request.getParameter("VI_additional_withholding");
			
			System.out.println("NofExemptions--------------->"+NofExemptions);
			System.out.println("age_exemptions--------------->"+age_exemptions);
			System.out.println("additional_withholding--------------->"+additional_withholding);
			
			state_wh_info = NofExemptions+","+age_exemptions+","+additional_withholding;
			//StateWHITAmount = (double)payRollBean.calculateVirginiaStateWithHold(Double.parseDouble(totalGross), PayPeriod,
					//Double.parseDouble(additional_withholding), Integer.parseInt(age_exemptions), Integer.parseInt(NofExemptions));
		}else if(state != null && state.equalsIgnoreCase("Georgia")){
			personalAllowances = Integer.parseInt(request.getParameter("personal_allowances"));
			dependentAllowances = Integer.parseInt(request.getParameter("dependent_allowances"));
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("total_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			additionalAllowances = Integer.parseInt(request.getParameter("additional_allowances"));
			state_wh_info = personalAllowances+","+dependentAllowances+","+filingStatus+","+totalAllowances+","+
					additionalStateWithHolding+","+additionalAllowances;
			//StateWHITAmount = (double)payRollBean.calculateGeorgiaWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, additionalAllowances, personalAllowances, dependentAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Arkansas")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("total_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			texarnaResident = request.getParameter("texarna_resident");
			state_wh_info = filingStatus+","+totalAllowances+","+additionalStateWithHolding+","+texarnaResident;
			//StateWHITAmount = (double)payRollBean.calculateArkansasWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, texarnaResident);
		}else if(state != null && state.equalsIgnoreCase("Idaho")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("total_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			state_wh_info = filingStatus+","+totalAllowances+","+additionalStateWithHolding;
			//StateWHITAmount = (double)payRollBean.calculateIdahoWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Alabama")){
			//marital_status = request.getParameter("marital_status");
			filingStatus = request.getParameter("filing_status");
			dependentAllowances = Integer.parseInt(request.getParameter("dependent_allowances"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateAlabamaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, dependentAllowances, filingStatus, federalWHITAmount);
			state_wh_info = filingStatus+","+dependentAllowances+","+totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Connecticut")){
			withHoldingCode = request.getParameter("withholding_code");
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			state_wh_info = withHoldingCode+","+additionalStateWithHolding;
			//StateWHITAmount = (double)payRollBean.calculcateConnecticutWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, withHoldingCode);
		}else if(state != null && state.equalsIgnoreCase("Illinois")){
			additionalAllowances = Integer.parseInt(request.getParameter("additional_allowances"));
			basicAllowances = Integer.parseInt(request.getParameter("basic_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateIllinoisWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, additionalAllowances, basicAllowances);
			state_wh_info =additionalAllowances+","+basicAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Indiana")){
			personalDeduction = Integer.parseInt(request.getParameter("personal_deduction"));
			dependentDeduction = Integer.parseInt(request.getParameter("dependent_deduction"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateIndianaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, personalDeduction, dependentDeduction);
			state_wh_info = personalDeduction+","+dependentDeduction+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Kentucky")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateKentuckyWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
			state_wh_info = totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Maine")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateMaineWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = filingStatus+","+totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Minnesota")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateMinnesotaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = filingStatus+","+totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Wisconsin")){
			filingStatus = request.getParameter("filing_status");
			WHExemptions = Integer.parseInt(request.getParameter("WHExemptions"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateWisconsinWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, WHExemptions, filingStatus);
			state_wh_info = filingStatus+","+WHExemptions+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Rhode Island")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateRhodeislandWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
			state_wh_info = totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Vermont")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateVermontWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = filingStatus+","+totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("New Jersey")){
			filingStatus = request.getParameter("filing_status");
			RateCode = request.getParameter("rate_code");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateNewJerseyWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, RateCode, fWHITMarital);
			state_wh_info = filingStatus+","+RateCode+","+totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("District of Columbia")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateColumbiaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
			state_wh_info = totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Nebraska")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateNebraskaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Montana")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateMontanaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances);
			state_wh_info = totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("New Mexico")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateNewMexicoWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("West Virginia")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateWestVirginiaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Hawaii")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateHawaiiWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Louisiana")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			personalAllowances = Integer.parseInt(request.getParameter("personal_allowances"));
			dependentAllowances = Integer.parseInt(request.getParameter("dependent_allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("personal_allowances",String.valueOf(personalAllowances));
			request.setAttribute("dependent_allowances",String.valueOf(dependentAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateLouisianaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //personalAllowances, dependentAllowances, filingStatus);
			state_wh_info = additionalStateWithHolding+","+personalAllowances+","+dependentAllowances+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Massachusetts")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			SpouseBlind = request.getParameter("spouse_blind");
			PersonalBlind = request.getParameter("personal_blind");
			HouseHold = request.getParameter("house_hold");
			Student = request.getParameter("student");
			
			request.setAttribute("spouse_blind",String.valueOf(SpouseBlind));
			request.setAttribute("personal_blind",String.valueOf(PersonalBlind));
			request.setAttribute("house_hold",String.valueOf(HouseHold));
			request.setAttribute("student",String.valueOf(Student));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateMassachusettsWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, SocialsecureAmt, MedicareAmt, SpouseBlind, PersonalBlind, HouseHold, Student);
			state_wh_info = additionalStateWithHolding+","+totalAllowances+","+SpouseBlind+","+PersonalBlind+","+HouseHold
			+","+Student;
		}else if(state != null && state.equalsIgnoreCase("Oregon")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateOregonWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, filingStatus, federalWHITAmount);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("South Carolina")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateSouthCarolinaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances);
			state_wh_info = totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("North Carolina")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateNorthCarolinaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("North Dakota")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateNorthDakotaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Utah")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateUtahWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Delaware")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateDelawareWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Colorado")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateColoradoWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("California")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			extendedAllowances = Integer.parseInt(request.getParameter("extended_allowances"));
			regularAllowances = Integer.parseInt(request.getParameter("regular_allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("extended_allowances",String.valueOf(extendedAllowances));
			request.setAttribute("regular_allowances",String.valueOf(regularAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateCaliforniaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, extendedAllowances, regularAllowances, filingStatus);
			state_wh_info = additionalStateWithHolding+","+extendedAllowances+","+regularAllowances+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Iowa")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateIowaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, federalWHITAmount);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Kansas")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateKansasWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Mississippi")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			exemptionAmount = Double.parseDouble(request.getParameter("exemption_amount"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("exemption_amount",String.valueOf(exemptionAmount));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateMississippiWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, exemptionAmount, filingStatus);
			state_wh_info = exemptionAmount+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Michigan")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateMichiganWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
			state_wh_info = additionalStateWithHolding+","+totalAllowances;
		}else if(state != null && state.equalsIgnoreCase("Missouri")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateMissouriWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, federalWHITAmount);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Pennsylvania")){
						
			//StateWHITAmount = (double)payRollBean.calculatePennsylvaniaWithHeld(Double.parseDouble(totalGross), PayPeriod);
		}else if(state != null && state.equalsIgnoreCase("Arizona")){
			WithholdingRate = Double.parseDouble(request.getParameter("withholding_rate"));
			
			request.setAttribute("withholding_rate",String.valueOf(WithholdingRate));
			
			//StateWHITAmount = (double)payRollBean.calculateArizonaWithHeld(Double.parseDouble(totalGross), PayPeriod, WithholdingRate);
			state_wh_info = String.valueOf(WithholdingRate);
		}else if(state != null && state.equalsIgnoreCase("Oklahoma")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateOklahomaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Ohio")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			DistrictRate = Double.parseDouble(request.getParameter("district_rate"));
			DistrictNo = Integer.parseInt(request.getParameter("district_no"));
			
			request.setAttribute("district_rate",String.valueOf(DistrictRate));
			request.setAttribute("district_no",String.valueOf(DistrictNo));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+DistrictRate+","+DistrictNo;
			request.setAttribute("schoolDistITAmount",String.valueOf(schoolDistITAmount));
		}
		
	/*	empPayrollInsertStatus = db.insertEmployeePayrollDetails(uniqueEmployeeId,empId,empSSNNo,empFname,empLname,taxyear,"US",
				state,fWHITMarital,fWHITNoFAllowances,state_wh_info,PayPeriod,tax_payduration,PayRateType,
				overtime,totalGross,PayRateType,total_hours,totalYTDGross,totalGross,ot_row_count,ot_payrateArray,
				ot_payrate_typeArray,ot_total_hours_workedArray,ot_pay_ytdArray,ot_pay_cpArray,federal_it_ytd,
				federal_it_cp,social_rate,social_annual,social_ytd,social_cp,Medicare_rate,Medicare_ytd,
				Medicare_cp,State_IT_ytd,State_IT_cp,After_Tax_Income,After_Tax_Income_YTD,After_Tax_Income_CP,
				After_Tax_deduction,After_Tax_deduction_YTD,After_Tax_deduction_CP,String.valueOf(netAmount));
		
		System.out.println("db.insertEmployeePayrollDetails status-------------->"+empPayrollInsertStatus);
		
	*/
		System.out.println("Hourly_payrate-------------------->"+Hourly_payrate);
		System.out.println("total_hours-------------------->"+total_hours);
		System.out.println("YTD_Hourly-------------------->"+YTD_Hourly);
		//System.out.println("Monthly_payrate-------------------->"+Monthly_payrate);
		//System.out.println("YTD_Monthly-------------------->"+YTD_Monthly);
				
		System.out.println("ot_row_count-------------------->"+ot_row_count);
		
		
		System.out.println("taxyear------------------->"+taxyear);
		System.out.println("state------------------->"+state);
		System.out.println("chequeNo------------------->"+chequeNo);
		System.out.println("chequeDate------------------->"+chequeDate);
		System.out.println("VI_NofExemptions------------------->"+NofExemptions);
		System.out.println("VI_age_exemptions------------------->"+age_exemptions);
		System.out.println("VI_additional_withholding------------------->"+additional_withholding);
		System.out.println("fWHITMarital------------------->"+fWHITMarital);
		System.out.println("fWHITNoFAllowances------------------->"+fWHITNoFAllowances);
		System.out.println("PayPeriod------------------->"+PayPeriod);
		System.out.println("tax_paydurationFrom--------------------->"+tax_paydurationFrom);
		System.out.println("tax_paydurationTo--------------------->"+tax_paydurationTo);
		System.out.println("PayRateType------------------->"+PayRateType);
		System.out.println("overtime------------------->"+overtime);
		System.out.println("total_gross_pay_hourly------------------->"+total_gross_pay_hourly);
		//System.out.println("gross_pay_monthly------------------->"+total_gross_pay_monthly);
		System.out.println("federal_it_cp------------------->"+federal_it_cp);
		System.out.println("social_cp------------------->"+social_cp);
		System.out.println("Medicare_cp------------------->"+Medicare_cp);
		System.out.println("State_IT_cp------------------->"+State_IT_cp);
		System.out.println("After_Tax_Income_CP------------------->"+After_Tax_Income_CP);
		System.out.println("After_Tax_deduction_CP------------------->"+After_Tax_deduction_CP);
		
		System.out.println("federal_it_rate-------------------->"+federal_it_rate);
		System.out.println("federal_it_annual-------------------->"+federal_it_annual);
		System.out.println("federal_it_ytd-------------------->"+federal_it_ytd);
		System.out.println("social_rate-------------------->"+social_rate);
		System.out.println("social_annual-------------------->"+social_annual);
		System.out.println("social_ytd-------------------->"+social_ytd);
		System.out.println("Medicare_rate-------------------->"+Medicare_rate);
		System.out.println("Medicare_annual-------------------->"+Medicare_annual);
		System.out.println("Medicare_ytd-------------------->"+Medicare_ytd);
		System.out.println("State_IT_rate-------------------->"+State_IT_rate);
		System.out.println("State_IT_annual-------------------->"+State_IT_annual);
		System.out.println("State_IT_ytd-------------------->"+State_IT_ytd);
		System.out.println("After_Tax_Income-------------------->"+After_Tax_Income);
		System.out.println("After_Tax_Income_YTD-------------------->"+After_Tax_Income_YTD);
		System.out.println("After_Tax_deduction-------------------->"+After_Tax_deduction);
		System.out.println("After_Tax_deduction_YTD-------------------->"+After_Tax_deduction_YTD);
		
		
		
		
		//social_security_amount = (Double.parseDouble(totalGross) * Double.parseDouble(social_rate));
		//medicare_amount = (Double.parseDouble(totalGross) * Double.parseDouble(Medicare_rate));
		
		//social_cp = String.valueOf(social_security_amount);
		//Medicare_cp = String.valueOf(medicare_amount);
		
		String totalCpTaxes = null;
		totalCpTaxes = String.valueOf(Double.parseDouble(federal_it_cp) + Double.parseDouble(State_IT_cp)
				+ Double.parseDouble(social_cp) + Double.parseDouble(Medicare_cp));
		totalCpTaxes = String.valueOf(Math.round(Double.parseDouble(totalCpTaxes) * 100D) / 100D);
		request.setAttribute("netAmount", String.valueOf(netAmount));
		String totalYTDTaxes = null;
		totalYTDTaxes = String.valueOf(Double.parseDouble(federal_it_ytd) + Double.parseDouble(State_IT_ytd)
				+ Double.parseDouble(social_ytd) + Double.parseDouble(Medicare_ytd));
		totalYTDTaxes = String.valueOf(Math.round(Double.parseDouble(totalYTDTaxes) * 100D) / 100D);
		
		System.out.println("Total federalWHITAmount----------------------->"+federal_it_cp);
		System.out.println("Total StateWHITAmount----------------------->"+State_IT_cp);
		System.out.println("Social security amount----------------------->"+social_cp);
		System.out.println("Medicare amount----------------------->"+Medicare_cp);
		
		System.out.println("Total Current Pay(CP) Taxes----------------->"+totalCpTaxes);
		System.out.println("Total Year to Pay(YTD) Taxes----------------->"+totalYTDTaxes);
		
		try{
		       OutputStream file = new FileOutputStream(new File("D:\\EmployeePaystub.pdf"));
	          Document document = new Document();
	          
	          PdfWriter writer = PdfWriter.getInstance(document, file);
		        document.open();
		        Font subtitleFont = FontFactory.getFont("Calibri(Body)", 12, BaseColor.BLACK);
		        Font Bold = FontFactory.getFont("Calibri(Body)", 12, Font.BOLD);
		      
		        final DefaultResourceLoader loader = new DefaultResourceLoader();               
		        //LOGGER.info(loader.getResource("classpath:META-INF/resources/img/copyright.png").exists());             
		        Resource resource = loader.getResource("classpath:images/db.jpg"); 
		        System.out.println("resource.getURL()-----------------"+resource.getURL());
		        // Image image = Image.getInstance ("src/images/db.jpg");
		        Image image = Image.getInstance(resource.getURL());
		        
			    image.scaleAbsolute(60f, 60f);//image width,height
		         
			   /*------------------End of image inserting-----------------------------*/
			   
			    PdfPTable table=new PdfPTable(4);//No.of.columns
			    table.setSpacingBefore(10);
			    table.setWidthPercentage(100);
			    table.setWidths(new float[]{ 1f, 2f, 1f, 2f});
			   
			    PdfPCell cell = new PdfPCell (new Paragraph ( "General",Bold));
		   
			    cell.setFixedHeight(22f);
		   
			    cell.setColspan (6);
		   
			    cell.setBorderColor(BaseColor.WHITE);
			    cell.setHorizontalAlignment (Element.ALIGN_LEFT);
			   
			    cell.setBackgroundColor (new BaseColor (202,207,210));					               
			   
			    table.addCell(cell);
			    table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			    //first row
			    PdfPCell cell01 = new PdfPCell();
			
			    table.addCell("Employee Id:");
			    table.addCell(empId);
		   	
		   		table.addCell("Pay Day:");
		   		table.addCell(chequeDate);
		   
		   		table.addCell("SSN:");
		   		table.addCell("***-**-"+String.valueOf(empSSNNo).substring(5));
		  
		   		table.addCell("Pay period:");
		   		table.addCell(tax_paydurationFrom +" - "+tax_paydurationTo);
		   
		 
		   		PdfPCell cell02 = new PdfPCell();
		   
		  
			   table.addCell("Department:");
			   table.addCell(empDepartment);
			   table.addCell("Pay Cycle:");
			   table.addCell(PayPeriod);
			   
			   table.addCell("Location:");
			   table.addCell(empJobLocation);
			   table.addCell("Pay Rate($):");
			   table.addCell(Hourly_payrate);

		 
		   
			    PdfPTable table2=new PdfPTable(5);//No.of.columns
			    table2.setSpacingBefore(10);
			   
			    table2.setWidthPercentage(100);
			    
		   PdfPCell cell2 = new PdfPCell (new Paragraph ( "Earnings",Bold));
		   
		   cell2.setFixedHeight(22f);
		  
		   
		   cell2.setBorderColor(BaseColor.WHITE);
		   cell2.setHorizontalAlignment (Element.ALIGN_LEFT);
			 cell2.setColspan(6);  
		   cell2.setBackgroundColor (new BaseColor (202,207,210));					               
			   
		   table2.addCell(cell2);
		   table2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			   
		   PdfPTable table3 = new PdfPTable(5);
		   table3.setWidthPercentage(100);
		   table3.setSpacingBefore(10f); //Space before table
		   table3.setSpacingAfter(10f);
		   table3.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		   table3.setWidths(new float[]{ 2f, 2f, 2f, 2f, 2f});
		  
		   PdfPCell cell3;
		   PdfPCell cell4;
		   PdfPCell cell5;
		   PdfPCell cell6;
		   PdfPCell cell7;
		   PdfPCell cell8;
		   
		   cell3 = new PdfPCell(new Phrase("Description",Bold));
		   cell3.setBorderColor(BaseColor.WHITE);
		   
		   cell4 = new PdfPCell(new Phrase("Pay Rate Type",Bold));
		   cell4.setBorderColor(BaseColor.WHITE);
		   
		   cell5 = new PdfPCell(new Phrase("Hours",Bold));
		   cell5.setBorderColor(BaseColor.WHITE);
		   
		   cell6 = new PdfPCell(new Phrase("Current Pay($)",Bold));
		   cell6.setBorderColor(BaseColor.WHITE);
		   
		   /*cell7 = new PdfPCell(new Phrase("",Bold));
		   cell7.setBorderColor(BaseColor.WHITE);*/
		   
		   cell8 = new PdfPCell(new Phrase("Year-To-Date($)",Bold));
		   cell8.setBorderColor(BaseColor.WHITE);
		   table3.addCell(cell3);
		   table3.addCell(cell4);
		   table3.addCell(cell5);
		   table3.addCell(cell6);
		   //table3.addCell(cell7);
		   table3.addCell(cell8);
		   
		   
		   
		   
		   table3.addCell("Salary");
		   table3.addCell(PayRateType);
		   table3.addCell(total_hours);
		   table3.addCell(totalGross);
		   //table3.addCell("");3760.00 
		   table3.addCell(totalYTDGross);
		   
		   if(overtime != null && overtime != "null" && !(overtime.equalsIgnoreCase("null"))){
	             for(int i=0;i<=ot_row_count_int;i++){
		     		overtimerate[i] = request.getParameter("overtimerate"+i);
		     		overtimepaytype[i] = request.getParameter("overtimepaytype"+i);
		     		Hour_ot[i] = request.getParameter("Hour_ot"+i);
		     		ytd_ot[i] = request.getParameter("ytd_ot"+i);
		     		
		     		cp_ot[i] = request.getParameter("cp_ot"+i);
		     		ytd_ot[i] = String.valueOf(Math.round((Double.parseDouble(cp_ot[i]) + Double.parseDouble(ytd_ot[i]))* 100D) / 100D);
		     	
		     		table3.addCell("Overtime"+i);
		     		table3.addCell(overtimepaytype[i]);
		     		table3.addCell(Hour_ot[i]);
		     		table3.addCell(cp_ot[i]);
		             //table3.addCell("");
		     		table3.addCell(ytd_ot[i]);
	             }
	             }
		  
		   	PdfPCell cell03 = new PdfPCell(new Phrase(""));
			    cell03.setColspan(6);
			    cell03.setBorder(0);
			    cell03.setFixedHeight(20f);
			    //cell03.setSpaceCharRatio(20f);
			    table3.addCell(cell03);
		   
			    PdfPCell cell04 = new PdfPCell(new Phrase("Total Earnings",Bold));
			    cell04.setBorder(0);
			    
		   table3.addCell(cell04);
		   table3.addCell("");
		   table3.addCell(totalFinalHours);
		   table3.addCell(totalFinalGross);
		   //table3.addCell("");
		   table3.addCell(totalFinalYTDGross);
		   /*---------------------End of Paycheck details-----------------------------*/
		   
		   PdfPTable table4=new PdfPTable(3);//No.of.columns
		   table4.setSpacingBefore(10);
		   table4.setWidthPercentage(100);
		   
			    
		   PdfPCell cell9 = new PdfPCell (new Paragraph ( "Taxes",Bold));
		   
		   
		   
		   cell9.setFixedHeight(22f);
		   cell9.setColspan(6);
		   cell9.setBorderColor(BaseColor.WHITE);
		   cell9.setHorizontalAlignment (Element.ALIGN_LEFT);
			   
		   cell9.setBackgroundColor (new BaseColor (202,207,210));					               
			   
		   table4.addCell(cell9);
		   table4.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			   
		  PdfPTable table5 = new PdfPTable(3);
		  
		   table5.setWidthPercentage(100);
		   table5.setSpacingBefore(10f); //Space before table
		   table5.setSpacingAfter(10f);
		   table5.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		   table5.setWidths(new float[]{ 6f, 2f, 2f});
		   
		   PdfPCell cell10;
		   PdfPCell cell11;
		   PdfPCell cell12;
		   
		   cell10 = new PdfPCell(new Phrase("Description",Bold));
		   //cell10.setColspan(2);
		   cell10.setBorderColor(BaseColor.WHITE);
		   
		   cell11 = new PdfPCell(new Phrase("This Period",Bold));
		   cell11.setBorderColor(BaseColor.WHITE);
		   
		   cell12 = new PdfPCell(new Phrase("YTD",Bold));
		   cell12.setBorderColor(BaseColor.WHITE);
		   table5.addCell(cell10);
		   table5.addCell(cell11);
		   table5.addCell(cell12);
		   
		   
		   table5.addCell("Federal Income Tax");
		   table5.addCell(federal_it_cp);
		   table5.addCell(federal_it_ytd);
		   
		   //third row
		   table5.addCell("Social security Tax");
		   table5.addCell(social_cp);
		   table5.addCell(social_ytd);
		   
		   table5.addCell("Medicare");
		   table5.addCell(Medicare_cp);
		   table5.addCell(Medicare_ytd); 
		   
		   table5.addCell("State income Tax");
		   table5.addCell(State_IT_cp);
		   table5.addCell(State_IT_ytd);
		   
		  
		   table5.addCell(cell03);
		   
		   PdfPCell cell05 = new PdfPCell(new Phrase("Total Taxes($)",Bold));
		   cell05.setBorder(0);
		   table5.addCell(cell05);
		   table5.addCell(totalCpTaxes);
		   table5.addCell(totalYTDTaxes);
		   
		 
		  
		   
		   /*---------------------End of Tax details-----------------------------*/
		   
		  
		   PdfPTable table6 = new PdfPTable(2); // 2 columns.
		   table6.setWidthPercentage(100); //Width 100%
		   table6.setSpacingBefore(10f); //Space before table
		   PdfPCell cell13 = new PdfPCell(new Paragraph("After-Tax Income",Bold));
		  
		   cell13.setBorderColor(BaseColor.WHITE);
		   cell13.setPaddingLeft(10);
		   cell13.setFixedHeight(22f);
		   cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
		   cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell13.setBackgroundColor (new BaseColor (202,207,210));	

		   PdfPCell cell14 = new PdfPCell(new Paragraph("After-Tax Deduction",Bold));
		   cell14.setBorderColor(BaseColor.WHITE);
		   cell14.setPaddingLeft(10);
		   cell14.setFixedHeight(22f);
		   cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
		   cell14.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell14.setBackgroundColor (new BaseColor (202,207,210));	
		   table6.addCell(cell13);
		   table6.addCell(cell14);
		   
		   PdfPTable table7 = new PdfPTable(6);
		   table7.setWidthPercentage(100);
		   table7.setSpacingBefore(10f); //Space before table
		   table7.setSpacingAfter(10f);
		   table7.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		   table7.setWidths(new int[]{ 3, 2, 2, 3, 2, 2});
		   PdfPCell cell15;
		   PdfPCell cell16;
		   PdfPCell cell17;
		   PdfPCell cell18;
		   PdfPCell cell19;
		   PdfPCell cell20;
		   PdfPCell cell21;
		   
		   
		   cell15 = new PdfPCell(new Phrase("Description",Bold));
		   cell15.setBorderColor(BaseColor.WHITE);
		   
		   cell16 = new PdfPCell(new Phrase("This Period",Bold));
		   cell16.setBorderColor(BaseColor.WHITE);
		   
		   cell17 = new PdfPCell(new Phrase("YTD",Bold));
		   cell17.setBorderColor(BaseColor.WHITE);
		   /*
		   cell18 = new PdfPCell(new Phrase(""));
		   cell18.setBorderColor(BaseColor.WHITE);*/
		   
		   cell19 = new PdfPCell(new Phrase("Description",Bold));
		   cell19.setBorderColor(BaseColor.WHITE);
		   
		   cell20 = new PdfPCell(new Phrase("This Period",Bold));
		   cell20.setBorderColor(BaseColor.WHITE);
		   
		   cell21 = new PdfPCell(new Phrase("YTD",Bold));
		   cell21.setBorderColor(BaseColor.WHITE);
		   
		   table7.addCell(cell15);
		   table7.addCell(cell16);
		   table7.addCell(cell17);
		   //table7.addCell(cell18);
		   table7.addCell(cell19);
		   table7.addCell(cell20);
		   table7.addCell(cell21);
		   
		//   PdfContentByte canvas = writer.getDirectContent();
		//   
//		    canvas.moveTo(298, 107);
//		    canvas.lineTo(298, 194);
//		    canvas.closePathStroke();
		   
		    table7.addCell("Income");//column 1
		    table7.addCell(After_Tax_Income_CP);//column 2
		    table7.addCell(After_Tax_Income_YTD);//column 3
		    //table7.addCell("");//column 4 
		    table7.addCell("Deduction");//column 5
		    table7.addCell(After_Tax_deduction_CP);//column 6
		    table7.addCell(After_Tax_deduction_YTD);//column 7
		   
		  
		  
		 
		   /*---------------------End of TAX DEDUCTION details-----------------------------*/
		   
		   PdfPTable table8=new PdfPTable(4);//No.of.columns
		   table8.setSpacingBefore(10);
			   
		   table8.setWidthPercentage(100);
		  
		   PdfPCell cell22 = new PdfPCell (new Paragraph ( "Netpay",Bold));
		   
		   cell22.setFixedHeight(22f);
		   cell22.setColspan (6);
		   
		   cell22.setBorderColor(BaseColor.WHITE);
		   cell22.setHorizontalAlignment (Element.ALIGN_LEFT);
			   
		   cell22.setBackgroundColor (new BaseColor (202,207,210));					               
			   
		   table8.addCell(cell22);
		   table8.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			    
		   PdfPTable table9 = new PdfPTable(4);
		   table9.setWidthPercentage(100);
		   table9.setSpacingBefore(10f); //Space before table
		   table9.setSpacingAfter(10f);
		   table9.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		  // table9.setWidths(new int[]{ 2, 2, 2, 2});
		   PdfPCell cell23;
		   PdfPCell cell24;
		   PdfPCell cell25;
		   PdfPCell cell26;
		   
		   cell23 = new PdfPCell(new Phrase("Payment Type",Bold));
		   cell23.setBorderColor(BaseColor.WHITE);
		   
		   cell24 = new PdfPCell(new Phrase("Cheque Number",Bold));
		   cell24.setBorderColor(BaseColor.WHITE);
		   
		   cell25 = new PdfPCell(new Phrase("Pay Day",Bold));
		   cell25.setBorderColor(BaseColor.WHITE);
		   
		   cell26 = new PdfPCell(new Phrase("Net Amount($)",Bold));
		   cell26.setBorderColor(BaseColor.WHITE);
		   
		   table9.addCell(cell23);
		   table9.addCell(cell24);
		   table9.addCell(cell25);
		   table9.addCell(cell26);
		  
		  
		   table9.addCell("Cheque");
		   table9.addCell(chequeNo);
		   table9.addCell(chequeDate);
		   table9.addCell(String.valueOf(netAmount));
		   
		  
		   /*---------------------End of net pay details-----------------------------*/
		   
		   PdfPTable table10 = new PdfPTable(1); // 3 columns.
		   table10.setWidthPercentage(100);//Width 100%
		   
		    //document.add(new Paragraph("Paystub Generated On - "+new Date().toString(),Bold));
		    document.add(new Paragraph(new Date().toString()));
		    
		    
		    
		  /* PdfPCell cell27 = new PdfPCell(new Paragraph("PAYSTUB",Bold));
		   cell27.setBorderColor(BaseColor.WHITE);
		   cell27.setPaddingLeft(10);
		   cell27.setHorizontalAlignment(Element.ALIGN_CENTER);
		   cell27.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   table10.addCell(cell27);
		   */
		   
		   
		   
		    
		 /*----------------------end of tax header details----------------------------*/
		    
		   PdfPTable table11 = new PdfPTable(2);
		  
		 // table11.setSpacingBefore(0);
		   table11.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		   table11.setWidths(new float[]{ 2f,2f});
		 
		   table11.setWidthPercentage(100);
		   
		  
		   PdfPCell cell30;
		   
		  // Image image = Image.getInstance ("src/images/db.jpg");
		   image.scaleAbsolute(60f, 60f);
		   cell30 = new PdfPCell();
		   cell30.setColspan(1);
		   cell30.setFixedHeight(50f);
		   //cell30.setHorizontalAlignment (Element.ALIGN_RIGHT);
		   cell30.setBorderColor(BaseColor.WHITE);
		   cell30.addElement(image);
		   image.setAlignment(Image.ALIGN_RIGHT);
		   
			   PdfPCell cell31;
			   //PdfPCell cell32;
			 
		
			  cell31 = new PdfPCell();
			  cell31.setPaddingLeft(12);
			  cell31.addElement(new Phrase(empFname+" "+empLname));
			  cell31.addElement(new Paragraph(new Phrase(empAddress1c)));
			  cell31.setFixedHeight(20f);
			  cell31.setBorder(0);
			   
			  table11.addCell(cell30);
			 
			  
			   table11.addCell(cell31 );
			   
			   
			  PdfPTable table12 = new PdfPTable(2);
			  
				 // table11.setSpacingBefore(0);
			  table12.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			  table12.setWidths(new float[]{ 2f,1f});
				 
			  table12.setWidthPercentage(100);
			  PdfPCell cell32 = new PdfPCell();
			  PdfPCell cell33 = new PdfPCell();
			  
			  cell33.addElement(new Paragraph(""));
			  cell33.setColspan(1);
			  cell33.setBorder(0);
			  cell33.setFixedHeight(50f);
			 
			  cell32.addElement(new Paragraph("NET PAY($):"+String.valueOf(netAmount),Bold));
			  cell33.setFixedHeight(20f);
			
			  // cell31.setRowspan(2);
			  cell32.setBorder(0);
			   
			 
			  table12.addCell(cell33);
			  table12.addCell(cell32);
			  
			  
		   
		   /*------------------------------End of header logo------------------------*/
		   
		   document.add(table11);
		   document.add(table12);
		   
		  
		 
		      document.add(table);
		      
		      //document.add(table1);
		      document.add(table2);
		     
		      document.add(table3);
		      
		      document.add(table4);
		      document.add(table5);
		      document.add(table6);
		      document.add(table7);
		      document.add(table8);
		     document.add(table9);
		    // document.add(table10);
		      
		      document.add(Chunk.NEWLINE);
		     
		  
		      document.close();
		        
		        final int BUFFER_SIZE = 4096;
		        // get absolute path of the application
			    ServletContext context = request.getSession().getServletContext();
			    // construct the complete absolute path of the file
			    String fullPath ="D:\\EmployeePaystub.pdf";  
		        File downloadFile = new File(fullPath);
		        FileInputStream inputStream = new FileInputStream(downloadFile);
		         
		        // get MIME type of the file
		        String mimeType = context.getMimeType(fullPath);
		        if (mimeType == null) {
		            // set to binary type if MIME mapping not found
		            mimeType = "application/octet-stream";
		        }
		        			 
		        // set content attributes for the response
		        response.setContentType(mimeType);
		        response.setContentLength((int) downloadFile.length());
		 
		        // set headers for the response
		        String headerKey = "Content-Disposition";
		        String headerValue = String.format("attachment; filename=\"%s\"",
		                downloadFile.getName());
		        response.setHeader(headerKey, headerValue);
		 
		        // get output stream of the response
				
		        OutputStream outStream = response.getOutputStream();
		 
		        byte[] buffer = new byte[BUFFER_SIZE];
		        int bytesRead = -1;
		 
		        // write bytes read from the input stream into the output stream
		        while ((bytesRead = inputStream.read(buffer)) != -1) {
		            outStream.write(buffer, 0, bytesRead);
		        }
		 
		        outStream.flush();
		        inputStream.close();
		        outStream.close();
		  }
		        catch (Exception e) {
		            e.printStackTrace();
		        }
		
		return new ModelAndView("requirements/frmListEmployeePayrollDetails");
		
	}



	
	@SuppressWarnings("unused")
	@RequestMapping("/savePayStub.html")
	public ModelAndView savePayStub(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
    	String empId = null;
	    String empdberpPartyid = null;
	    String empSSNNo = null;
	    String empSalutation = null;
	    String empFname = null;
	    String empLname = null;
	    String empGender = null;
	    String empMaritalStatus = null;
	    String empEmailId = null;
	    String empAddress1c = null;
	    String empCityc = null;
	    String empStatec = null;
	    String empCountryc = null;
	    String empPostalCodec = null;
	    String empCompany = null;
	    String empJobTitle = null;
	    String empDepartment = null;
	    String empJobLocation = null;
	    String empPayGroup = null;
	    String empPayratePerhour = null;
	    String empPayPeriod = null;
		
		Debug.print("RequirementsAction.initPayrollCalc()");
		String uniqueEmployeeId = null;
		ArrayList employeeDetails = new ArrayList();
		uniqueEmployeeId = request.getParameter("uniqueId");
		if(uniqueEmployeeId == null){
			throw new Exception("Employee Not Found!");
			}else{
				
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(uniqueEmployeeId);
		        
		        if(employeeDetails!=null && employeeDetails.size()!=0){
					Iterator itr = employeeDetails.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
				    	empId = TempList[3];
					    empdberpPartyid = TempList[4];
					    empSSNNo = TempList[5];
					    empSalutation = TempList[6];
					    empFname = TempList[8];
					    empLname = TempList[10];
					    empGender = TempList[11];
					    empMaritalStatus = TempList[12];
					    empEmailId = TempList[14];
					    empAddress1c = TempList[19];
					    empCityc = TempList[23];
					    empStatec = TempList[25];
					    empCountryc = TempList[27];
					    empPostalCodec = TempList[29];
					    empCompany = TempList[30];
					    empJobTitle = TempList[32];
					    empDepartment = TempList[33];
					    empJobLocation = TempList[34];
					    empPayGroup = TempList[35];
					    empPayratePerhour = TempList[36];
					    empPayPeriod = TempList[37];
					    
					 //empSSNNo = String.valueOf(empSSNNo).substring(5);
				    }
		        }
			}
		
		
			 
		
		double netAmount = 0.0;
		double social_security_amount = 0.0;
		double medicare_amount = 0.0;
		double total_ot_amount = 0.0;
		double total_ot_ytd_amount = 0.0;
		String taxyear = null;
		String state = null;
		String chequeNo = null;
		String chequeDate = null;
		String NofExemptions = null;
		String age_exemptions = null;
		String additional_withholding = null;
		String fWHITMarital = null;
		String fWHITNoFAllowances = null;
		String PayPeriod = null;
		String PayRateType = null;
		String overtime = null;
		String Hourly_payrate =null;
		String total_hours = null;
		String YTD_Hourly = null;
		String total_gross_pay_hourly = null;
		//String Monthly_payrate = null;
		//String YTD_Monthly = null;
		//String total_gross_pay_monthly = null;
		
		String federal_it_rate = null;
		String federal_it_annual = null;
		String federal_it_ytd = null;
		String federal_it_cp = null;
		String social_rate = null;
		String social_annual = null;
		String social_ytd = null;
		String social_cp = null;
		String Medicare_rate = null;
		String Medicare_annual = null;
		String Medicare_ytd = null;
		String Medicare_cp = null;
		String State_IT_rate = null;
		String State_IT_annual = null;
		String State_IT_ytd = null;
		String State_IT_cp = null;
		String After_Tax_Income = null;
		String After_Tax_Income_YTD = null;
		String After_Tax_Income_CP = null;
		String After_Tax_deduction = null;
		String After_Tax_deduction_YTD = null;
		String After_Tax_deduction_CP = null;
		
		String totalGross = null;
		String totalFinalGross = null;
		String totalYTDGross = null;
		String totalFinalYTDGross = null;
		String ot_row_count = null;
		double StateWHITAmount = 0;
		int personalAllowances = 0;
		int dependentAllowances = 0;
		String filingStatus = null;
		int totalAllowances = 0;
		double additionalStateWithHolding = 0;
		int additionalAllowances = 0;
		String texarnaResident = null;
		
		String withHoldingCode = null;
		int basicAllowances = 0;
		int personalDeduction = 0;
		int dependentDeduction = 0;
		int WHExemptions = 0;
		String RateCode = null;
		
		String SpouseBlind = null;
		String PersonalBlind = null;
		String HouseHold = null;
		String Student = null;
		
		int extendedAllowances = 0;
		int regularAllowances = 0;
		double exemptionAmount = 0;
		double WithholdingRate = 0;
		double DistrictRate = 0;
		int DistrictNo = 0;
		double schoolDistITAmount = 0.0;
		
		String state_wh_info = null;
		String tax_paydurationFrom = null;
		String tax_paydurationTo = null;
		String ot_payrateArray = "0";
		String ot_payrate_typeArray = "0";
		String ot_total_hours_workedArray = "0";
		String ot_pay_ytdArray = "0";
		String ot_pay_cpArray = "0";
		boolean empPayrollInsertStatus = false;
		
		taxyear = (String) request.getParameter("taxyear");
		state = (String) request.getParameter("state");
		chequeNo = (String) request.getParameter("chequeNo");
		chequeDate = (String) request.getParameter("chequeDate");
		netAmount = Double.parseDouble(request.getParameter("netAmount"));
		fWHITMarital = (String) request.getParameter("fWHITMarital");
		fWHITNoFAllowances = (String) request.getParameter("fWHITNoFAllowances");
		PayPeriod = (String) request.getParameter("PayPeriod");
		tax_paydurationFrom = (String)request.getParameter("payDurationFrom");
		tax_paydurationTo = (String)request.getParameter("payDurationTo");
		PayRateType = (String) request.getParameter("PayRateType");
		overtime = (String) request.getParameter("overtime");
		
		Hourly_payrate = request.getParameter("Hourly_payrate");
		total_hours = request.getParameter("total_hours");
		YTD_Hourly = request.getParameter("YTD_Hourly");
		total_gross_pay_hourly = (String) request.getParameter("total_gross_pay_hourly");
		
		//Monthly_payrate = request.getParameter("Monthly_payrate");
		//YTD_Monthly = request.getParameter("YTD_Monthly");
		//total_gross_pay_monthly = (String) request.getParameter("total_gross_pay_monthly");

		ot_row_count = request.getParameter("overtimecount");
		System.out.println("ot_row_count-------------->"+ot_row_count);
		String[] overtimerate = null;
		String[] overtimepaytype = null;
		String[] Hour_ot = null;
		String[] ytd_ot = null;
		String[] cp_ot = null;
		int ot_row_count_int = 0;
		
		if(overtime != null && overtime != "null" && !(overtime.equalsIgnoreCase("null"))){
		
		ot_row_count_int = Integer.parseInt(ot_row_count);
		overtimerate = new String[ot_row_count_int+1];
		overtimepaytype = new String[ot_row_count_int+1];
		Hour_ot = new String[ot_row_count_int+1];
		ytd_ot = new String[ot_row_count_int+1];
		cp_ot = new String[ot_row_count_int+1];
		
		
		for(int i=0;i<=ot_row_count_int;i++){
			System.out.println("request.getParameter(overtimerate"+i+")"+request.getParameter("overtimerate"+i));
			//System.out.println("overtimerate["+i+"]------------->"+overtimerate[i]);
		overtimerate[i] = request.getParameter("overtimerate"+i);
		overtimepaytype[i] = request.getParameter("overtimepaytype"+i);
		Hour_ot[i] = request.getParameter("Hour_ot"+i);
		ytd_ot[i] = request.getParameter("ytd_ot"+i);
		cp_ot[i] = request.getParameter("cp_ot"+i);
		
		ot_payrateArray = ot_payrateArray+","+overtimerate[i];
		ot_payrate_typeArray = ot_payrate_typeArray+","+overtimepaytype[i];
		ot_total_hours_workedArray = ot_total_hours_workedArray+","+Hour_ot[i];
		ot_pay_ytdArray = ot_pay_ytdArray+","+ytd_ot[i];
		ot_pay_cpArray = ot_pay_cpArray+","+cp_ot[i];
		
		total_ot_amount = (total_ot_amount + Double.parseDouble(cp_ot[i]));
		
		total_ot_ytd_amount = (total_ot_ytd_amount + Double.parseDouble(ytd_ot[i]));
		
		
		System.out.println("overtimerate["+i+"]---------->"+overtimerate[i]);
		System.out.println("overtimepaytype["+i+"]---------->"+overtimepaytype[i]);
		System.out.println("Hour_ot["+i+"]---------->"+Hour_ot[i]);
		System.out.println("ytd_ot["+i+"]---------->"+ytd_ot[i]);
		System.out.println("cp_ot["+i+"]---------->"+cp_ot[i]);
		
		System.out.println("total_ot_amount-------------->"+total_ot_amount);
		System.out.println("total_ot_ytd_amount----------->"+total_ot_ytd_amount);
		}
		}
		
		federal_it_rate = request.getParameter("federal_it_rate");
		federal_it_annual = request.getParameter("federal_it_annual");
		federal_it_ytd = request.getParameter("federal_it_ytd");
		federal_it_ytd = String.valueOf(Math.round(Double.parseDouble(federal_it_ytd) * 100D) / 100D);
		federal_it_cp = (String) request.getParameter("federal_it_cp");
		
		social_rate = request.getParameter("social_rate");
		social_annual = request.getParameter("social_annual");
		social_ytd = request.getParameter("social_ytd");
		social_ytd = String.valueOf(Math.round(Double.parseDouble(social_ytd) * 100D) / 100D);
		social_cp = (String) request.getParameter("social_cp");
		
		Medicare_rate = request.getParameter("Medicare_rate");
		Medicare_annual = request.getParameter("Medicare_annual");
		Medicare_ytd = request.getParameter("Medicare_ytd");
		Medicare_ytd = String.valueOf(Math.round(Double.parseDouble(Medicare_ytd) * 100D) / 100D);
		Medicare_cp = (String) request.getParameter("Medicare_cp");
		
		State_IT_rate = request.getParameter("State_IT_rate");
		State_IT_annual = request.getParameter("State_IT_annual");
		State_IT_ytd = request.getParameter("State_IT_ytd");
		State_IT_ytd = String.valueOf(Math.round(Double.parseDouble(State_IT_ytd) * 100D) / 100D);
		State_IT_cp = (String) request.getParameter("State_IT_cp");
		
		After_Tax_Income = request.getParameter("After_Tax_Income");
		After_Tax_Income_YTD = request.getParameter("After_Tax_Income_YTD");
		After_Tax_Income_YTD = String.valueOf(Math.round(Double.parseDouble(After_Tax_Income_YTD) * 100D) / 100D);
		After_Tax_Income_CP = (String) request.getParameter("After_Tax_Income_CP");
		
		After_Tax_deduction = request.getParameter("After_Tax_deduction");
		After_Tax_deduction_YTD = request.getParameter("After_Tax_deduction_YTD");
		After_Tax_deduction_YTD = String.valueOf(Math.round(Double.parseDouble(After_Tax_deduction_YTD) * 100D) / 100D);
		After_Tax_deduction_CP = (String) request.getParameter("After_Tax_deduction_CP");
		
		
		
		/*if(PayRateType != null && PayRateType.equalsIgnoreCase("Hourly")){
			totalGross = total_gross_pay_hourly;
			totalYTDGross = YTD_Hourly;
		}else if(PayRateType != null && PayRateType.equalsIgnoreCase("Monthly")){
			totalGross = total_gross_pay_monthly;
			totalYTDGross = YTD_Monthly;
		}else{
			throw new Exception("Problem with Total Gross Amount!");
		}*/
		
		if(PayRateType != null){
			totalGross = total_gross_pay_hourly;
			totalYTDGross = YTD_Hourly;
		}else{
			throw new Exception("Problem with Total Gross Amount!");
		}
		
		totalFinalGross = String.valueOf((Double.parseDouble(totalGross) + total_ot_amount));
		totalFinalGross = String.valueOf(Math.round(Double.parseDouble(totalFinalGross) * 100D) / 100D);
		totalYTDGross = String.valueOf(Math.round(Double.parseDouble(totalYTDGross) * 100D) / 100D);
		totalFinalYTDGross = String.valueOf((Double.parseDouble(totalYTDGross) + total_ot_ytd_amount));
		totalFinalYTDGross = String.valueOf(Math.round(Double.parseDouble(totalFinalYTDGross) * 100D) / 100D);
		//double federalWHITAmount = (double)payRollBean.calculateFederalITWithholdAmount(fWHITMarital,Integer.parseInt(fWHITNoFAllowances),Double.parseDouble(totalGross),PayPeriod);
		
		if(state != null && state.equalsIgnoreCase("Virginia")){
			NofExemptions = (String) request.getParameter("VI_NofExemptions");
			age_exemptions = (String) request.getParameter("VI_age_exemptions");
			additional_withholding = (String) request.getParameter("VI_additional_withholding");
			state_wh_info = NofExemptions+","+age_exemptions+","+additional_withholding;
			//StateWHITAmount = (double)payRollBean.calculateVirginiaStateWithHold(Double.parseDouble(totalGross), PayPeriod,
					//Double.parseDouble(additional_withholding), Integer.parseInt(age_exemptions), Integer.parseInt(NofExemptions));
		}else if(state != null && state.equalsIgnoreCase("Georgia")){
			personalAllowances = Integer.parseInt(request.getParameter("personal_allowances"));
			dependentAllowances = Integer.parseInt(request.getParameter("dependent_allowances"));
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("total_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			additionalAllowances = Integer.parseInt(request.getParameter("additional_allowances"));
			state_wh_info = personalAllowances+","+dependentAllowances+","+filingStatus+","+totalAllowances+","+
					additionalStateWithHolding+","+additionalAllowances;
			//StateWHITAmount = (double)payRollBean.calculateGeorgiaWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, additionalAllowances, personalAllowances, dependentAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Arkansas")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("total_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			texarnaResident = request.getParameter("texarna_resident");
			state_wh_info = filingStatus+","+totalAllowances+","+additionalStateWithHolding+","+texarnaResident;
			//StateWHITAmount = (double)payRollBean.calculateArkansasWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, texarnaResident);
		}else if(state != null && state.equalsIgnoreCase("Idaho")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("total_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			state_wh_info = filingStatus+","+totalAllowances+","+additionalStateWithHolding;
			//StateWHITAmount = (double)payRollBean.calculateIdahoWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Alabama")){
			//marital_status = request.getParameter("marital_status");
			filingStatus = request.getParameter("filing_status");
			dependentAllowances = Integer.parseInt(request.getParameter("dependent_allowances"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateAlabamaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, dependentAllowances, filingStatus, federalWHITAmount);
			state_wh_info = filingStatus+","+dependentAllowances+","+totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Connecticut")){
			withHoldingCode = request.getParameter("withholding_code");
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			state_wh_info = withHoldingCode+","+additionalStateWithHolding;
			//StateWHITAmount = (double)payRollBean.calculcateConnecticutWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, withHoldingCode);
		}else if(state != null && state.equalsIgnoreCase("Illinois")){
			additionalAllowances = Integer.parseInt(request.getParameter("additional_allowances"));
			basicAllowances = Integer.parseInt(request.getParameter("basic_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateIllinoisWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, additionalAllowances, basicAllowances);
			state_wh_info =additionalAllowances+","+basicAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Indiana")){
			personalDeduction = Integer.parseInt(request.getParameter("personal_deduction"));
			dependentDeduction = Integer.parseInt(request.getParameter("dependent_deduction"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateIndianaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, personalDeduction, dependentDeduction);
			state_wh_info = personalDeduction+","+dependentDeduction+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Kentucky")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateKentuckyWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
			state_wh_info = totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Maine")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateMaineWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = filingStatus+","+totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Minnesota")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateMinnesotaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = filingStatus+","+totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Wisconsin")){
			filingStatus = request.getParameter("filing_status");
			WHExemptions = Integer.parseInt(request.getParameter("WHExemptions"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateWisconsinWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, WHExemptions, filingStatus);
			state_wh_info = filingStatus+","+WHExemptions+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Rhode Island")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateRhodeislandWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
			state_wh_info = totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Vermont")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateVermontWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = filingStatus+","+totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("New Jersey")){
			filingStatus = request.getParameter("filing_status");
			RateCode = request.getParameter("rate_code");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateNewJerseyWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, RateCode, fWHITMarital);
			state_wh_info = filingStatus+","+RateCode+","+totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("District of Columbia")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateColumbiaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
			state_wh_info = totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Nebraska")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateNebraskaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Montana")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateMontanaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances);
			state_wh_info = totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("New Mexico")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateNewMexicoWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("West Virginia")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateWestVirginiaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Hawaii")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateHawaiiWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Louisiana")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			personalAllowances = Integer.parseInt(request.getParameter("personal_allowances"));
			dependentAllowances = Integer.parseInt(request.getParameter("dependent_allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("personal_allowances",String.valueOf(personalAllowances));
			request.setAttribute("dependent_allowances",String.valueOf(dependentAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateLouisianaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //personalAllowances, dependentAllowances, filingStatus);
			state_wh_info = additionalStateWithHolding+","+personalAllowances+","+dependentAllowances+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Massachusetts")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			SpouseBlind = request.getParameter("spouse_blind");
			PersonalBlind = request.getParameter("personal_blind");
			HouseHold = request.getParameter("house_hold");
			Student = request.getParameter("student");
			
			request.setAttribute("spouse_blind",String.valueOf(SpouseBlind));
			request.setAttribute("personal_blind",String.valueOf(PersonalBlind));
			request.setAttribute("house_hold",String.valueOf(HouseHold));
			request.setAttribute("student",String.valueOf(Student));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateMassachusettsWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, SocialsecureAmt, MedicareAmt, SpouseBlind, PersonalBlind, HouseHold, Student);
			state_wh_info = additionalStateWithHolding+","+totalAllowances+","+SpouseBlind+","+PersonalBlind+","+HouseHold
			+","+Student;
		}else if(state != null && state.equalsIgnoreCase("Oregon")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateOregonWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, filingStatus, federalWHITAmount);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("South Carolina")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateSouthCarolinaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances);
			state_wh_info = totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("North Carolina")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateNorthCarolinaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("North Dakota")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateNorthDakotaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Utah")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateUtahWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Delaware")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateDelawareWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Colorado")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateColoradoWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("California")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			extendedAllowances = Integer.parseInt(request.getParameter("extended_allowances"));
			regularAllowances = Integer.parseInt(request.getParameter("regular_allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("extended_allowances",String.valueOf(extendedAllowances));
			request.setAttribute("regular_allowances",String.valueOf(regularAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateCaliforniaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, extendedAllowances, regularAllowances, filingStatus);
			state_wh_info = additionalStateWithHolding+","+extendedAllowances+","+regularAllowances+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Iowa")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateIowaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, federalWHITAmount);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Kansas")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateKansasWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Mississippi")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			exemptionAmount = Double.parseDouble(request.getParameter("exemption_amount"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("exemption_amount",String.valueOf(exemptionAmount));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateMississippiWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, exemptionAmount, filingStatus);
			state_wh_info = exemptionAmount+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Michigan")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateMichiganWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
			state_wh_info = additionalStateWithHolding+","+totalAllowances;
		}else if(state != null && state.equalsIgnoreCase("Missouri")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateMissouriWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, federalWHITAmount);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Pennsylvania")){
						
			//StateWHITAmount = (double)payRollBean.calculatePennsylvaniaWithHeld(Double.parseDouble(totalGross), PayPeriod);
		}else if(state != null && state.equalsIgnoreCase("Arizona")){
			WithholdingRate = Double.parseDouble(request.getParameter("withholding_rate"));
			
			request.setAttribute("withholding_rate",String.valueOf(WithholdingRate));
			
			//StateWHITAmount = (double)payRollBean.calculateArizonaWithHeld(Double.parseDouble(totalGross), PayPeriod, WithholdingRate);
			state_wh_info = String.valueOf(WithholdingRate);
		}else if(state != null && state.equalsIgnoreCase("Oklahoma")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			request.setAttribute("filing_status",String.valueOf(filingStatus));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			//StateWHITAmount = (double)payRollBean.calculateOklahomaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Ohio")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			DistrictRate = Double.parseDouble(request.getParameter("district_rate"));
			DistrictNo = Integer.parseInt(request.getParameter("district_no"));
			
			request.setAttribute("district_rate",String.valueOf(DistrictRate));
			request.setAttribute("district_no",String.valueOf(DistrictNo));
			request.setAttribute("allowances",String.valueOf(totalAllowances));
			request.setAttribute("additional_state_withholding",String.valueOf(additionalStateWithHolding));
			
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+DistrictRate+","+DistrictNo;
			request.setAttribute("schoolDistITAmount",String.valueOf(schoolDistITAmount));
		}
		
		empPayrollInsertStatus = db.insertEmployeePayrollDetails(uniqueEmployeeId,empId,empSSNNo,empFname,empLname,taxyear,chequeNo,chequeDate,"US",
				state,fWHITMarital,fWHITNoFAllowances,state_wh_info,PayPeriod,tax_paydurationFrom,tax_paydurationTo,PayRateType,
				overtime,Hourly_payrate,PayRateType,total_hours,totalYTDGross,totalGross,ot_row_count,ot_payrateArray,
				ot_payrate_typeArray,ot_total_hours_workedArray,ot_pay_ytdArray,ot_pay_cpArray,federal_it_ytd,
				federal_it_cp,social_rate,social_annual,social_ytd,social_cp,Medicare_rate,Medicare_ytd,
				Medicare_cp,State_IT_ytd,State_IT_cp,After_Tax_Income,After_Tax_Income_YTD,After_Tax_Income_CP,
				After_Tax_deduction,After_Tax_deduction_YTD,After_Tax_deduction_CP,String.valueOf(netAmount));
		
		System.out.println("db.insertEmployeePayrollDetails status-------------->"+empPayrollInsertStatus);
		System.out.println("Hourly_payrate-------------------->"+Hourly_payrate);
		System.out.println("total_hours-------------------->"+total_hours);
		System.out.println("YTD_Hourly-------------------->"+YTD_Hourly);
		//System.out.println("Monthly_payrate-------------------->"+Monthly_payrate);
		//System.out.println("YTD_Monthly-------------------->"+YTD_Monthly);
				
		System.out.println("ot_row_count-------------------->"+ot_row_count);
		
		
		System.out.println("taxyear------------------->"+taxyear);
		System.out.println("state------------------->"+state);
		System.out.println("chequeNo------------------->"+chequeNo);
		System.out.println("chequeDate------------------->"+chequeDate);
		System.out.println("VI_NofExemptions------------------->"+NofExemptions);
		System.out.println("VI_age_exemptions------------------->"+age_exemptions);
		System.out.println("VI_additional_withholding------------------->"+additional_withholding);
		System.out.println("fWHITMarital------------------->"+fWHITMarital);
		System.out.println("fWHITNoFAllowances------------------->"+fWHITNoFAllowances);
		System.out.println("PayPeriod------------------->"+PayPeriod);
		System.out.println("tax_paydurationFrom--------------------->"+tax_paydurationFrom);
		System.out.println("tax_paydurationTo--------------------->"+tax_paydurationTo);
		System.out.println("PayRateType------------------->"+PayRateType);
		System.out.println("overtime------------------->"+overtime);
		System.out.println("total_gross_pay_hourly------------------->"+total_gross_pay_hourly);
		//System.out.println("gross_pay_monthly------------------->"+total_gross_pay_monthly);
		System.out.println("federal_it_cp------------------->"+federal_it_cp);
		System.out.println("social_cp------------------->"+social_cp);
		System.out.println("Medicare_cp------------------->"+Medicare_cp);
		System.out.println("State_IT_cp------------------->"+State_IT_cp);
		System.out.println("After_Tax_Income_CP------------------->"+After_Tax_Income_CP);
		System.out.println("After_Tax_deduction_CP------------------->"+After_Tax_deduction_CP);
		
		System.out.println("federal_it_rate-------------------->"+federal_it_rate);
		System.out.println("federal_it_annual-------------------->"+federal_it_annual);
		System.out.println("federal_it_ytd-------------------->"+federal_it_ytd);
		System.out.println("social_rate-------------------->"+social_rate);
		System.out.println("social_annual-------------------->"+social_annual);
		System.out.println("social_ytd-------------------->"+social_ytd);
		System.out.println("Medicare_rate-------------------->"+Medicare_rate);
		System.out.println("Medicare_annual-------------------->"+Medicare_annual);
		System.out.println("Medicare_ytd-------------------->"+Medicare_ytd);
		System.out.println("State_IT_rate-------------------->"+State_IT_rate);
		System.out.println("State_IT_annual-------------------->"+State_IT_annual);
		System.out.println("State_IT_ytd-------------------->"+State_IT_ytd);
		System.out.println("After_Tax_Income-------------------->"+After_Tax_Income);
		System.out.println("After_Tax_Income_YTD-------------------->"+After_Tax_Income_YTD);
		System.out.println("After_Tax_deduction-------------------->"+After_Tax_deduction);
		System.out.println("After_Tax_deduction_YTD-------------------->"+After_Tax_deduction_YTD);
		
		
		
		
		//social_security_amount = (Double.parseDouble(totalGross) * Double.parseDouble(social_rate));
		//medicare_amount = (Double.parseDouble(totalGross) * Double.parseDouble(Medicare_rate));
		
		//social_cp = String.valueOf(social_security_amount);
		//Medicare_cp = String.valueOf(medicare_amount);
		
		String totalCpTaxes = null;
		totalCpTaxes = String.valueOf(Double.parseDouble(federal_it_cp) + Double.parseDouble(State_IT_cp)
				+ Double.parseDouble(social_cp) + Double.parseDouble(Medicare_cp));
		totalCpTaxes = String.valueOf(Math.round(Double.parseDouble(totalCpTaxes) * 100D) / 100D);
		request.setAttribute("netAmount", String.valueOf(netAmount));
		String totalYTDTaxes = null;
		totalYTDTaxes = String.valueOf(Double.parseDouble(federal_it_ytd) + Double.parseDouble(State_IT_ytd)
				+ Double.parseDouble(social_ytd) + Double.parseDouble(Medicare_ytd));
		totalYTDTaxes = String.valueOf(Math.round(Double.parseDouble(totalYTDTaxes) * 100D) / 100D);
		
		System.out.println("Total federalWHITAmount----------------------->"+federal_it_cp);
		System.out.println("Total StateWHITAmount----------------------->"+State_IT_cp);
		System.out.println("Social security amount----------------------->"+social_cp);
		System.out.println("Medicare amount----------------------->"+Medicare_cp);
		
		System.out.println("Total Current Pay(CP) Taxes----------------->"+totalCpTaxes);
		System.out.println("Total Year to Pay(YTD) Taxes----------------->"+totalYTDTaxes);
		
	/*	
		try{
		       OutputStream file = new FileOutputStream(new File("D:\\EmployeePaystub.pdf"));
	          Document document = new Document();
	          
	          PdfWriter writer = PdfWriter.getInstance(document, file);
		        document.open();
		        Font subtitleFont = FontFactory.getFont("Calibri(Body)", 12, BaseColor.BLACK);
		        Font Bold = FontFactory.getFont("Calibri(Body)", 12, Font.BOLD);
		      
		        final DefaultResourceLoader loader = new DefaultResourceLoader();               
		        //LOGGER.info(loader.getResource("classpath:META-INF/resources/img/copyright.png").exists());             
		        Resource resource = loader.getResource("classpath:images/db.jpg"); 
		        System.out.println("resource.getURL()-----------------"+resource.getURL());
		        // Image image = Image.getInstance ("src/images/db.jpg");
		        Image image = Image.getInstance(resource.getURL());
		        
			    image.scaleAbsolute(60f, 60f);//image width,height
		         
			   //------------------End of image inserting-----------------------------
			   
			    PdfPTable table=new PdfPTable(4);//No.of.columns
			    table.setSpacingBefore(20);
			    table.setWidthPercentage(100);
			   
             PdfPCell cell = new PdfPCell (new Paragraph ( "General",Bold));
             
             cell.setFixedHeight(22f);
             
             cell.setColspan (4);
             
			    cell.setBorderColor(BaseColor.BLACK);
			    cell.setHorizontalAlignment (Element.ALIGN_LEFT);
			   
			    cell.setBackgroundColor (new BaseColor (95,158,160));					               
			   
			    table.addCell(cell);
			    table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			    //first row
             table.addCell("Name");//column 1
             table.addCell(empFname+" "+empLname);//column 2
             //table.addCell("Business unit:");//column 3
             //table.addCell("cubus");//column 4
            //second row
             table.addCell("Employee Id");
             table.addCell(empId);
             table.addCell("Pay Group");
             table.addCell(empPayGroup);
             //third row
             table.addCell("SSN");
             table.addCell("***-**-"+String.valueOf(empSSNNo).substring(5));
             table.addCell("Department");
             table.addCell(empDepartment);
            //fourth row
             table.addCell("Address");
             table.addCell(empAddress1c);
             table.addCell("Location");
             table.addCell(empJobLocation);
             //fifth row
             table.addCell("");
             table.addCell("");
             table.addCell("Pay Rate");
             table.addCell(empPayratePerhour+"/Hour");
             
             table.addCell("");
             table.addCell("");
             table.addCell("Pay Period");
             table.addCell(empPayPeriod);
             /*---------------------End of general info-----------------------------*/
             
            /* 
             PdfPTable table1=new PdfPTable(4);//No.of.columns
             table1.setSpacingBefore(10);
             
			    table1.setWidthPercentage(100);
			    
             PdfPCell cell1 = new PdfPCell (new Paragraph ( "Tax Data",Bold));
             
             cell1.setFixedHeight(22f);
             cell1.setColspan (4);
             
             cell1.setBorderColor(BaseColor.BLACK);
             cell1.setHorizontalAlignment (Element.ALIGN_LEFT);
			   
			    cell1.setBackgroundColor (new BaseColor (95,158,160));					               
			   
			    table1.addCell(cell1);
			    table1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			    //first row
			    table1.addCell("Fed Marital Status:");//column 1
			    table1.addCell(fWHITMarital);//column 2
			    table1.addCell("State Marital status:");//column 3
			    table1.addCell("Single or Head of Household");//column 4
            //second row
			    table1.addCell("Fed Allowances:");
			    table1.addCell(fWHITNoFAllowances);
			    table1.addCell("State Allowances:");
			    table1.addCell("0");
             //third row
			    table1.addCell("Fed ADDI percent:");
			    table1.addCell("0.000");
			    table1.addCell("State ADDI percent:");
			    table1.addCell("0.000");
            //fourth row
			    table1.addCell("Fed ADDI Amount:");
			    table1.addCell("$45.67");
			    table1.addCell("State ADDI Amount:");
			    table1.addCell("$45.67");
			   
			   
			    //---------------------End of Tax data-----------------------------             
			    PdfPTable table2=new PdfPTable(1);//No.of.columns
			    table2.setSpacingBefore(10);
			   
			    table2.setWidthPercentage(100);
			    
             PdfPCell cell2 = new PdfPCell (new Paragraph ( "Earnings",Bold));
             
             cell2.setFixedHeight(22f);
            
             
             cell2.setBorderColor(BaseColor.BLACK);
             cell2.setHorizontalAlignment (Element.ALIGN_LEFT);
			   
             cell2.setBackgroundColor (new BaseColor (95,158,160));					               
			   
             table2.addCell(cell2);
             table2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			   
             PdfPTable table3 = new PdfPTable(6);
             table3.setWidthPercentage(100);
             table3.setSpacingBefore(10f); //Space before table
             table3.setSpacingAfter(10f);
             table3.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
             table3.setWidths(new float[]{ 2f, 2f, 2f, 2f, 2f, 2f});
            
             PdfPCell cell3;
             PdfPCell cell4;
             PdfPCell cell5;
             PdfPCell cell6;
             PdfPCell cell7;
             PdfPCell cell8;
             
             cell3 = new PdfPCell(new Phrase("Description",Bold));
             cell3.setBorderColor(BaseColor.WHITE);
             
             cell4 = new PdfPCell(new Phrase("Pay Type",Bold));
             cell4.setBorderColor(BaseColor.WHITE);
             
             cell5 = new PdfPCell(new Phrase("Hours",Bold));
             cell5.setBorderColor(BaseColor.WHITE);
             
             cell6 = new PdfPCell(new Phrase("Current Pay",Bold));
             cell6.setBorderColor(BaseColor.WHITE);
             
             cell7 = new PdfPCell(new Phrase("",Bold));
             cell7.setBorderColor(BaseColor.WHITE);
             
             cell8 = new PdfPCell(new Phrase("Year-To-Date",Bold));
             cell8.setBorderColor(BaseColor.WHITE);
             table3.addCell(cell3);
             table3.addCell(cell4);
             table3.addCell(cell5);
             table3.addCell(cell6);
             table3.addCell(cell7);
             table3.addCell(cell8);
             
             
             
             
             table3.addCell("Salary");
             table3.addCell(PayRateType);
             table3.addCell(total_hours);
             table3.addCell(totalGross);
             table3.addCell("");
             table3.addCell(totalYTDGross);
             
             if(overtime != null && overtime != "null" && !(overtime.equalsIgnoreCase("null"))){
             for(int i=0;i<=ot_row_count_int;i++){
	     		overtimerate[i] = request.getParameter("overtimerate"+i);
	     		overtimepaytype[i] = request.getParameter("overtimepaytype"+i);
	     		Hour_ot[i] = request.getParameter("Hour_ot"+i);
	     		ytd_ot[i] = request.getParameter("ytd_ot"+i);
	     		cp_ot[i] = request.getParameter("cp_ot"+i);
	     	
	             table3.addCell("Overtime"+i);
	             table3.addCell(overtimepaytype[i]);
	             table3.addCell(Hour_ot[i]);
	             table3.addCell(cp_ot[i]);
	             table3.addCell("");
	             table3.addCell(ytd_ot[i]);
             }
             }
            
             table3.addCell("Total Gross");
             table3.addCell("");
             table3.addCell("");
             table3.addCell(totalFinalGross);
             table3.addCell("");
             table3.addCell(totalFinalYTDGross);
             /*---------------------End of Paycheck details-----------------------------
             
             PdfPTable table4=new PdfPTable(1);//No.of.columns
             table4.setSpacingBefore(10);
             table4.setWidthPercentage(100);
             
			    
             PdfPCell cell9 = new PdfPCell (new Paragraph ( "Taxes",Bold));
             
             
             
             cell9.setFixedHeight(22f);
             
             cell9.setBorderColor(BaseColor.BLACK);
             cell9.setHorizontalAlignment (Element.ALIGN_LEFT);
			   
             cell9.setBackgroundColor (new BaseColor (95,158,160));					               
			   
             table4.addCell(cell9);
             table4.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			   
             PdfPTable table5 = new PdfPTable(3);
            
             table5.setWidthPercentage(100);
             table5.setSpacingBefore(10f); //Space before table
             table5.setSpacingAfter(10f);
             table5.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
             table5.setWidths(new float[]{ 6f, 2f, 2f});
            
             PdfPCell cell10;
             PdfPCell cell11;
             PdfPCell cell12;
             
             cell10 = new PdfPCell(new Phrase("Description",Bold));
             cell10.setBorderColor(BaseColor.WHITE);
             
             cell11 = new PdfPCell(new Phrase("This Period",Bold));
             cell11.setBorderColor(BaseColor.WHITE);
             
             cell12 = new PdfPCell(new Phrase("YTD",Bold));
             cell12.setBorderColor(BaseColor.WHITE);
             table5.addCell(cell10);
             table5.addCell(cell11);
             table5.addCell(cell12);
             
             
             table5.addCell("Federal Income Tax");
             table5.addCell(federal_it_cp);
             table5.addCell(federal_it_ytd);
             
             //third row
             table5.addCell("Social security Tax");
             table5.addCell(social_cp);
             table5.addCell(social_ytd);
             
             table5.addCell("Medicare");
             table5.addCell(Medicare_cp);
             table5.addCell(Medicare_ytd); 
             
             table5.addCell("State income Tax");
             table5.addCell(State_IT_cp);
             table5.addCell(State_IT_ytd);
             
             table5.addCell("Total Taxes");
             table5.addCell(totalCpTaxes);
             table5.addCell(totalYTDTaxes);
             
           
            
             
             /*---------------------End of Tax details-----------------------------
             
            
             PdfPTable table6 = new PdfPTable(2); // 2 columns.
             table6.setWidthPercentage(100); //Width 100%
             table6.setSpacingBefore(10f); //Space before table
             PdfPCell cell13 = new PdfPCell(new Paragraph("After-Tax Income",Bold));
            
             cell13.setBorderColor(BaseColor.BLACK);
             cell13.setPaddingLeft(10);
             cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
             cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
             cell13.setBackgroundColor (new BaseColor (95,158,160));	
      
             PdfPCell cell14 = new PdfPCell(new Paragraph("After-Tax Deduction",Bold));
             cell14.setBorderColor(BaseColor.BLACK);
             cell14.setPaddingLeft(10);
             cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
             cell14.setVerticalAlignment(Element.ALIGN_MIDDLE);
             cell14.setBackgroundColor (new BaseColor (95,158,160));	
             table6.addCell(cell13);
             table6.addCell(cell14);
             
             PdfPTable table7 = new PdfPTable(7);
             table7.setWidthPercentage(100);
             table7.setSpacingBefore(10f); //Space before table
             table7.setSpacingAfter(10f);
             table7.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
             table7.setWidths(new int[]{ 2, 2, 2, 2, 2, 2, 2});
             PdfPCell cell15;
             PdfPCell cell16;
             PdfPCell cell17;
             PdfPCell cell18;
             PdfPCell cell19;
             PdfPCell cell20;
             PdfPCell cell21;
             
             
             cell15 = new PdfPCell(new Phrase("Description",Bold));
             cell15.setBorderColor(BaseColor.WHITE);
             
             cell16 = new PdfPCell(new Phrase("This Period",Bold));
             cell16.setBorderColor(BaseColor.WHITE);
             
             cell17 = new PdfPCell(new Phrase("YTD",Bold));
             cell17.setBorderColor(BaseColor.WHITE);
             
             cell18 = new PdfPCell(new Phrase(""));
             cell18.setBorderColor(BaseColor.WHITE);
             
             cell19 = new PdfPCell(new Phrase("Description",Bold));
             cell19.setBorderColor(BaseColor.WHITE);
             
             cell20 = new PdfPCell(new Phrase("This Period",Bold));
             cell20.setBorderColor(BaseColor.WHITE);
             
             cell21 = new PdfPCell(new Phrase("YTD",Bold));
             cell21.setBorderColor(BaseColor.WHITE);
             
             table7.addCell(cell15);
             table7.addCell(cell16);
             table7.addCell(cell17);
             table7.addCell(cell18);
             table7.addCell(cell19);
             table7.addCell(cell20);
             table7.addCell(cell21);
             
             PdfContentByte canvas = writer.getDirectContent();
             
              canvas.moveTo(298, 107);
              canvas.lineTo(298, 194);
              canvas.closePathStroke();
             
              table7.addCell("Income");//column 1
              table7.addCell(After_Tax_Income_CP);//column 2
              table7.addCell(After_Tax_Income_YTD);//column 3
              table7.addCell("");//column 4 
              table7.addCell("Deduction");//column 5
              table7.addCell(After_Tax_deduction_CP);//column 6
              table7.addCell(After_Tax_deduction_YTD);//column 7
             
            
            
           
             /*---------------------End of TAX DEDUCTION details-----------------------------
             
             PdfPTable table8=new PdfPTable(1);//No.of.columns
             table8.setSpacingBefore(10);
			   
             table8.setWidthPercentage(100);
            
             PdfPCell cell22 = new PdfPCell (new Paragraph ( "Netpay",Bold));
             
             cell22.setFixedHeight(22f);
             cell22.setColspan (6);
             
             cell22.setBorderColor(BaseColor.BLACK);
             cell22.setHorizontalAlignment (Element.ALIGN_LEFT);
			   
             cell22.setBackgroundColor (new BaseColor (95,158,160));					               
			   
             table8.addCell(cell22);
             table8.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			    
             PdfPTable table9 = new PdfPTable(4);
             table9.setWidthPercentage(100);
             table9.setSpacingBefore(10f); //Space before table
             table9.setSpacingAfter(10f);
             table9.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
             table9.setWidths(new int[]{ 2, 2, 2, 2});
             PdfPCell cell23;
             PdfPCell cell24;
             PdfPCell cell25;
             PdfPCell cell26;
             
             cell23 = new PdfPCell(new Phrase("Payment Type",Bold));
             cell23.setBorderColor(BaseColor.WHITE);
             
             cell24 = new PdfPCell(new Phrase("payment Number",Bold));
             cell24.setBorderColor(BaseColor.WHITE);
             
             cell25 = new PdfPCell(new Phrase("AmountType",Bold));
             cell25.setBorderColor(BaseColor.WHITE);
             
             cell26 = new PdfPCell(new Phrase("Amount",Bold));
             cell26.setBorderColor(BaseColor.WHITE);
             
             table9.addCell(cell23);
             table9.addCell(cell24);
             table9.addCell(cell25);
             table9.addCell(cell26);
            
            
             table9.addCell("cheque");
             table9.addCell("89675");
             table9.addCell("issue cheque");
             table9.addCell(String.valueOf(netAmount));
             
            
             /*---------------------End of net pay details-----------------------------
             
             PdfPTable table10 = new PdfPTable(1); // 3 columns.
             table10.setWidthPercentage(50); //Width 100%
             
              //document.add(new Paragraph("Paystub Generated On - "+new Date().toString(),Bold));
              document.add(new Paragraph(new Date().toString()));
              
              
              
            /* PdfPCell cell27 = new PdfPCell(new Paragraph("PAYSTUB",Bold));
             cell27.setBorderColor(BaseColor.WHITE);
             cell27.setPaddingLeft(10);
             cell27.setHorizontalAlignment(Element.ALIGN_CENTER);
             cell27.setVerticalAlignment(Element.ALIGN_MIDDLE);
             table10.addCell(cell27);
             */
             
             
             
              
           /*----------------------end of tax header details----------------------------
              
             PdfPTable table11 = new PdfPTable(3);
             table11.setSpacingBefore(10);
             table11.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
             table11.setWidths(new int[]{ 1,1,2});
             PdfPCell cell28;
             PdfPCell cell29;
             PdfPCell cell30;
             
             
             cell28 = new PdfPCell();
             cell28.setBorderColor(BaseColor.WHITE);
             cell28.addElement(image);
             image.setAlignment(0);
             
             cell29 = new PdfPCell();
             cell29.setBorderColor(BaseColor.WHITE);
             
             //cell30 = new PdfPCell(new Phrase("13241 Woodland Park Road, Suite 110,Herndon, Virginia 20171, USA",Bold));
             cell30 = new PdfPCell(new Phrase("13241 Woodland Park Road, Suite 110,Herndon, Virginia 20171, USA"));
             cell30.setBorderColor(BaseColor.WHITE);
             cell30.setHorizontalAlignment (Element.ALIGN_LEFT);
             
            
             table11.addCell(cell28);
             table11.addCell(cell29);
             table11.addCell(cell30);
             
             /*------------------------------End of header logo------------------------
             
             document.add(table11);
             document.add(table10);
		        document.add(table);
		        
		        //document.add(table1);
		        document.add(table2);
		       
		        document.add(table3);
		        
		        document.add(table4);
		        document.add(table5);
		        document.add(table6);
		        document.add(table7);
		        document.add(table8);
		        document.add(table9);
		       
		        
		        document.add(Chunk.NEWLINE);
		       
				
		        document.close();
		        
		        final int BUFFER_SIZE = 4096;
		        // get absolute path of the application
			    ServletContext context = request.getSession().getServletContext();
			    // construct the complete absolute path of the file
			    String fullPath ="D:\\EmployeePaystub.pdf";  
		        File downloadFile = new File(fullPath);
		        FileInputStream inputStream = new FileInputStream(downloadFile);
		         
		        // get MIME type of the file
		        String mimeType = context.getMimeType(fullPath);
		        if (mimeType == null) {
		            // set to binary type if MIME mapping not found
		            mimeType = "application/octet-stream";
		        }
		        			 
		        // set content attributes for the response
		        response.setContentType(mimeType);
		        response.setContentLength((int) downloadFile.length());
		 
		        // set headers for the response
		        String headerKey = "Content-Disposition";
		        String headerValue = String.format("attachment; filename=\"%s\"",
		                downloadFile.getName());
		        response.setHeader(headerKey, headerValue);
		 
		        // get output stream of the response
				
		        OutputStream outStream = response.getOutputStream();
		 
		        byte[] buffer = new byte[BUFFER_SIZE];
		        int bytesRead = -1;
		 
		        // write bytes read from the input stream into the output stream
		        while ((bytesRead = inputStream.read(buffer)) != -1) {
		            outStream.write(buffer, 0, bytesRead);
		        }
		 
		        outStream.flush();
		        inputStream.close();
		        outStream.close();
		  }catch (Exception e) {
		            e.printStackTrace();
		            }
	*/	        
		      
		ArrayList employeePayrollList = new ArrayList();
		employeePayrollList = (ArrayList)db.getAllEmployeePayrollDetails();
        request.setAttribute("employeePayrollList",null);
        request.setAttribute("employeePayrollList",employeePayrollList);
        request.setAttribute("pageStatus","read");
		return new ModelAndView("requirements/frmListEmployeePayrollDetails");
		
	}
	
	@RequestMapping("/ListAllEmployeePayrollDetails.html")
	public ModelAndView ListAllEmployeePayrollDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("PayrollAction.ListEmployeePayrollDetails()");
		ArrayList employeePayrollList = new ArrayList();
		employeePayrollList = (ArrayList)db.getAllEmployeePayrollDetails();
        request.setAttribute("employeePayrollList",null);
        request.setAttribute("employeePayrollList",employeePayrollList);
        request.setAttribute("pageStatus","read");
		return new ModelAndView("requirements/frmListEmployeePayrollDetails");
	}

	
	@SuppressWarnings("unused")
	@RequestMapping("/updatePayStub.html")
	public ModelAndView updatePayStub(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
    	String empId = null;
	    String empdberpPartyid = null;
	    String empSSNNo = null;
	    String empSalutation = null;
	    String empFname = null;
	    String empLname = null;
	    String empGender = null;
	    String empMaritalStatus = null;
	    String empEmailId = null;
	    String empAddress1c = null;
	    String empCityc = null;
	    String empStatec = null;
	    String empCountryc = null;
	    String empPostalCodec = null;
	    String empCompany = null;
	    String empJobTitle = null;
	    String empDepartment = null;
	    String empJobLocation = null;
	    String empPayGroup = null;
	    String empPayratePerhour = null;
	    String empPayPeriod = null;
		
		Debug.print("RequirementsAction.initPayrollCalc()");
		String uniqueEmployeeId = null;
		String uniquePayrollId = null;
		
		ArrayList employeeDetails = new ArrayList();
		uniqueEmployeeId = request.getParameter("uniqueId");
		uniquePayrollId = request.getParameter("payrollId");
		
		if(uniqueEmployeeId == null){
			throw new Exception("Employee Not Found!");
			}else{
				
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(uniqueEmployeeId);
		        
		        if(employeeDetails!=null && employeeDetails.size()!=0){
					Iterator itr = employeeDetails.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
				    	empId = TempList[3];
					    empdberpPartyid = TempList[4];
					    empSSNNo = TempList[5];
					    empSalutation = TempList[6];
					    empFname = TempList[8];
					    empLname = TempList[10];
					    empGender = TempList[11];
					    empMaritalStatus = TempList[12];
					    empEmailId = TempList[14];
					    empAddress1c = TempList[19];
					    empCityc = TempList[23];
					    empStatec = TempList[25];
					    empCountryc = TempList[27];
					    empPostalCodec = TempList[29];
					    empCompany = TempList[30];
					    empJobTitle = TempList[32];
					    empDepartment = TempList[33];
					    empJobLocation = TempList[34];
					    empPayGroup = TempList[35];
					    empPayratePerhour = TempList[36];
					    empPayPeriod = TempList[37];
					    
					 //empSSNNo = String.valueOf(empSSNNo).substring(5);
				    }
		        }
			}
		
		
			 
		
		double netAmount = 0.0;
		double social_security_amount = 0.0;
		double medicare_amount = 0.0;
		double total_ot_amount = 0.0;
		double total_ot_ytd_amount = 0.0;
		String taxyear = null;
		String state = null;
		String chequeNo = null;
		String chequeDate = null;
		String NofExemptions = null;
		String age_exemptions = null;
		String additional_withholding = null;
		String fWHITMarital = null;
		String fWHITNoFAllowances = null;
		String PayPeriod = null;
		String PayRateType = null;
		String overtime = null;
		String Hourly_payrate =null;
		String total_hours = null;
		String YTD_Hourly = null;
		String total_gross_pay_hourly = null;
		//String Monthly_payrate = null;
		//String YTD_Monthly = null;
		//String total_gross_pay_monthly = null;
		
		String federal_it_rate = null;
		String federal_it_annual = null;
		String federal_it_ytd = null;
		String federal_it_cp = null;
		String social_rate = null;
		String social_annual = null;
		String social_ytd = null;
		String social_cp = null;
		String Medicare_rate = null;
		String Medicare_annual = null;
		String Medicare_ytd = null;
		String Medicare_cp = null;
		String State_IT_rate = null;
		String State_IT_annual = null;
		String State_IT_ytd = null;
		String State_IT_cp = null;
		String After_Tax_Income = null;
		String After_Tax_Income_YTD = null;
		String After_Tax_Income_CP = null;
		String After_Tax_deduction = null;
		String After_Tax_deduction_YTD = null;
		String After_Tax_deduction_CP = null;
		
		String totalGross = null;
		String totalFinalGross = null;
		String totalYTDGross = null;
		String totalFinalYTDGross = null;
		String ot_row_count = null;
		double StateWHITAmount = 0;
		int personalAllowances = 0;
		int dependentAllowances = 0;
		String filingStatus = null;
		int totalAllowances = 0;
		double additionalStateWithHolding = 0;
		int additionalAllowances = 0;
		String texarnaResident = null;
		
		String withHoldingCode = null;
		int basicAllowances = 0;
		int personalDeduction = 0;
		int dependentDeduction = 0;
		int WHExemptions = 0;
		String RateCode = null;
		
		String SpouseBlind = null;
		String PersonalBlind = null;
		String HouseHold = null;
		String Student = null;
		
		int extendedAllowances = 0;
		int regularAllowances = 0;
		double exemptionAmount = 0;
		double WithholdingRate = 0;
		double DistrictRate = 0;
		int DistrictNo = 0;
		double schoolDistITAmount = 0.0;
		
		String state_wh_info = null;
		String tax_paydurationFrom = null;
		String tax_paydurationTo = null;
		String ot_payrateArray = "0";
		String ot_payrate_typeArray = "0";
		String ot_total_hours_workedArray = "0";
		String ot_pay_ytdArray = "0";
		String ot_pay_cpArray = "0";
		boolean empPayrollUpdateStatus = false;
		
		taxyear = (String) request.getParameter("taxyear");
		state = (String) request.getParameter("state");
		chequeNo = (String) request.getParameter("chequeNo");
		chequeDate = (String) request.getParameter("chequeDate");
		netAmount = Double.parseDouble(request.getParameter("netAmount"));
		fWHITMarital = (String) request.getParameter("fWHITMarital");
		fWHITNoFAllowances = (String) request.getParameter("fWHITNoFAllowances");
		PayPeriod = (String) request.getParameter("PayPeriod");
		tax_paydurationFrom = (String)request.getParameter("payDurationFrom");
		tax_paydurationTo = (String)request.getParameter("payDurationTo");
		PayRateType = (String) request.getParameter("PayRateType");
		overtime = (String) request.getParameter("overtime");
		
		Hourly_payrate = request.getParameter("Hourly_payrate");
		total_hours = request.getParameter("total_hours");
		YTD_Hourly = request.getParameter("YTD_Hourly");
		total_gross_pay_hourly = (String) request.getParameter("total_gross_pay_hourly");
		
		//Monthly_payrate = request.getParameter("Monthly_payrate");
		//YTD_Monthly = request.getParameter("YTD_Monthly");
		//total_gross_pay_monthly = (String) request.getParameter("total_gross_pay_monthly");

		ot_row_count = request.getParameter("overtimecount");
		System.out.println("ot_row_count-------------->"+ot_row_count);
		String[] overtimerate = null;
		String[] overtimepaytype = null;
		String[] Hour_ot = null;
		String[] ytd_ot = null;
		String[] cp_ot = null;
		int ot_row_count_int = 0;
		
		if(overtime != null && overtime != "null" && !(overtime.equalsIgnoreCase("null"))){
		
		ot_row_count_int = Integer.parseInt(ot_row_count);
		overtimerate = new String[ot_row_count_int+1];
		overtimepaytype = new String[ot_row_count_int+1];
		Hour_ot = new String[ot_row_count_int+1];
		ytd_ot = new String[ot_row_count_int+1];
		cp_ot = new String[ot_row_count_int+1];
		
		
		for(int i=0;i<=ot_row_count_int;i++){
			System.out.println("request.getParameter(overtimerate"+i+")"+request.getParameter("overtimerate"+i));
			//System.out.println("overtimerate["+i+"]------------->"+overtimerate[i]);
		overtimerate[i] = request.getParameter("overtimerate"+i);
		overtimepaytype[i] = request.getParameter("overtimepaytype"+i);
		Hour_ot[i] = request.getParameter("Hour_ot"+i);
		ytd_ot[i] = request.getParameter("ytd_ot"+i);
		cp_ot[i] = request.getParameter("cp_ot"+i);
		
		ot_payrateArray = ot_payrateArray+","+overtimerate[i];
		ot_payrate_typeArray = ot_payrate_typeArray+","+overtimepaytype[i];
		ot_total_hours_workedArray = ot_total_hours_workedArray+","+Hour_ot[i];
		ot_pay_ytdArray = ot_pay_ytdArray+","+ytd_ot[i];
		ot_pay_cpArray = ot_pay_cpArray+","+cp_ot[i];
		
		total_ot_amount = (total_ot_amount + Double.parseDouble(cp_ot[i]));
		
		total_ot_ytd_amount = (total_ot_ytd_amount + Double.parseDouble(ytd_ot[i]));
		
		System.out.println("overtimerate["+i+"]---------->"+overtimerate[i]);
		System.out.println("overtimepaytype["+i+"]---------->"+overtimepaytype[i]);
		System.out.println("Hour_ot["+i+"]---------->"+Hour_ot[i]);
		System.out.println("ytd_ot["+i+"]---------->"+ytd_ot[i]);
		System.out.println("cp_ot["+i+"]---------->"+cp_ot[i]);
		
		System.out.println("total_ot_amount-------------->"+total_ot_amount);
		System.out.println("total_ot_ytd_amount----------->"+total_ot_ytd_amount);
		}
		}
		
		federal_it_rate = request.getParameter("federal_it_rate");
		federal_it_annual = request.getParameter("federal_it_annual");
		federal_it_ytd = request.getParameter("federal_it_ytd");
		federal_it_cp = (String) request.getParameter("federal_it_cp");
		
		social_rate = request.getParameter("social_rate");
		social_annual = request.getParameter("social_annual");
		social_ytd = request.getParameter("social_ytd");
		social_cp = (String) request.getParameter("social_cp");
		
		Medicare_rate = request.getParameter("Medicare_rate");
		Medicare_annual = request.getParameter("Medicare_annual");
		Medicare_ytd = request.getParameter("Medicare_ytd");
		Medicare_cp = (String) request.getParameter("Medicare_cp");
		
		State_IT_rate = request.getParameter("State_IT_rate");
		State_IT_annual = request.getParameter("State_IT_annual");
		State_IT_ytd = request.getParameter("State_IT_ytd");
		State_IT_cp = (String) request.getParameter("State_IT_cp");
		
		After_Tax_Income = request.getParameter("After_Tax_Income");
		After_Tax_Income_YTD = request.getParameter("After_Tax_Income_YTD");
		After_Tax_Income_CP = (String) request.getParameter("After_Tax_Income_CP");
		
		After_Tax_deduction = request.getParameter("After_Tax_deduction");
		After_Tax_deduction_YTD = request.getParameter("After_Tax_deduction_YTD");
		After_Tax_deduction_CP = (String) request.getParameter("After_Tax_deduction_CP");
		
		
		
		/*if(PayRateType != null && PayRateType.equalsIgnoreCase("Hourly")){
			totalGross = total_gross_pay_hourly;
			totalYTDGross = YTD_Hourly;
		}else if(PayRateType != null && PayRateType.equalsIgnoreCase("Monthly")){
			totalGross = total_gross_pay_monthly;
			totalYTDGross = YTD_Monthly;
		}else{
			throw new Exception("Problem with Total Gross Amount!");
		}*/
		
		if(PayRateType != null){
			totalGross = total_gross_pay_hourly;
			totalYTDGross = YTD_Hourly;
		}else{
			throw new Exception("Problem with Total Gross Amount!");
		}
		
		totalFinalGross = String.valueOf((Double.parseDouble(totalGross) + total_ot_amount));
		totalFinalGross = String.valueOf(Math.round(Double.parseDouble(totalFinalGross) * 100D) / 100D);
		totalFinalYTDGross = String.valueOf((Double.parseDouble(totalYTDGross) + total_ot_ytd_amount));
		totalFinalYTDGross = String.valueOf(Math.round(Double.parseDouble(totalFinalYTDGross) * 100D) / 100D);
		//double federalWHITAmount = (double)payRollBean.calculateFederalITWithholdAmount(fWHITMarital,Integer.parseInt(fWHITNoFAllowances),Double.parseDouble(totalGross),PayPeriod);
		
		if(state != null && state.equalsIgnoreCase("Virginia")){
			NofExemptions = (String) request.getParameter("VI_NofExemptions");
			age_exemptions = (String) request.getParameter("VI_age_exemptions");
			additional_withholding = (String) request.getParameter("VI_additional_withholding");
			state_wh_info = NofExemptions+","+age_exemptions+","+additional_withholding;
			//StateWHITAmount = (double)payRollBean.calculateVirginiaStateWithHold(Double.parseDouble(totalGross), PayPeriod,
					//Double.parseDouble(additional_withholding), Integer.parseInt(age_exemptions), Integer.parseInt(NofExemptions));
		}else if(state != null && state.equalsIgnoreCase("Georgia")){
			personalAllowances = Integer.parseInt(request.getParameter("personal_allowances"));
			dependentAllowances = Integer.parseInt(request.getParameter("dependent_allowances"));
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("total_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			additionalAllowances = Integer.parseInt(request.getParameter("additional_allowances"));
			state_wh_info = personalAllowances+","+dependentAllowances+","+filingStatus+","+totalAllowances+","+
					additionalStateWithHolding+","+additionalAllowances;
			//StateWHITAmount = (double)payRollBean.calculateGeorgiaWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, additionalAllowances, personalAllowances, dependentAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Arkansas")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("total_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			texarnaResident = request.getParameter("texarna_resident");
			state_wh_info = filingStatus+","+totalAllowances+","+additionalStateWithHolding+","+texarnaResident;
			//StateWHITAmount = (double)payRollBean.calculateArkansasWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, texarnaResident);
		}else if(state != null && state.equalsIgnoreCase("Idaho")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("total_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			state_wh_info = filingStatus+","+totalAllowances+","+additionalStateWithHolding;
			//StateWHITAmount = (double)payRollBean.calculateIdahoWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Alabama")){
			//marital_status = request.getParameter("marital_status");
			filingStatus = request.getParameter("filing_status");
			dependentAllowances = Integer.parseInt(request.getParameter("dependent_allowances"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateAlabamaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, dependentAllowances, filingStatus, federalWHITAmount);
			state_wh_info = filingStatus+","+dependentAllowances+","+totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Connecticut")){
			withHoldingCode = request.getParameter("withholding_code");
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			state_wh_info = withHoldingCode+","+additionalStateWithHolding;
			//StateWHITAmount = (double)payRollBean.calculcateConnecticutWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, withHoldingCode);
		}else if(state != null && state.equalsIgnoreCase("Illinois")){
			additionalAllowances = Integer.parseInt(request.getParameter("additional_allowances"));
			basicAllowances = Integer.parseInt(request.getParameter("basic_allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateIllinoisWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, additionalAllowances, basicAllowances);
			state_wh_info =additionalAllowances+","+basicAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Indiana")){
			personalDeduction = Integer.parseInt(request.getParameter("personal_deduction"));
			dependentDeduction = Integer.parseInt(request.getParameter("dependent_deduction"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateIndianaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, personalDeduction, dependentDeduction);
			state_wh_info = personalDeduction+","+dependentDeduction+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Kentucky")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateKentuckyWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
			state_wh_info = totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Maine")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateMaineWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = filingStatus+","+totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Minnesota")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateMinnesotaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = filingStatus+","+totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Wisconsin")){
			filingStatus = request.getParameter("filing_status");
			WHExemptions = Integer.parseInt(request.getParameter("WHExemptions"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateWisconsinWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, WHExemptions, filingStatus);
			state_wh_info = filingStatus+","+WHExemptions+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Rhode Island")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateRhodeislandWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
			state_wh_info = totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Vermont")){
			filingStatus = request.getParameter("filing_status");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateVermontWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = filingStatus+","+totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("New Jersey")){
			filingStatus = request.getParameter("filing_status");
			RateCode = request.getParameter("rate_code");
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateNewJerseyWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, RateCode, fWHITMarital);
			state_wh_info = filingStatus+","+RateCode+","+totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("District of Columbia")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			//StateWHITAmount = (double)payRollBean.calculateColumbiaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
			state_wh_info = totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("Nebraska")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			
			//StateWHITAmount = (double)payRollBean.calculateNebraskaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Montana")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			
			
			//StateWHITAmount = (double)payRollBean.calculateMontanaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances);
			state_wh_info = totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("New Mexico")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			
			//StateWHITAmount = (double)payRollBean.calculateNewMexicoWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("West Virginia")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			//StateWHITAmount = (double)payRollBean.calculateWestVirginiaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Hawaii")){
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			filingStatus = request.getParameter("filing_status");
			
			
			//StateWHITAmount = (double)payRollBean.calculateHawaiiWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Louisiana")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			personalAllowances = Integer.parseInt(request.getParameter("personal_allowances"));
			dependentAllowances = Integer.parseInt(request.getParameter("dependent_allowances"));
			filingStatus = request.getParameter("filing_status");
			
			
			//StateWHITAmount = (double)payRollBean.calculateLouisianaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //personalAllowances, dependentAllowances, filingStatus);
			state_wh_info = additionalStateWithHolding+","+personalAllowances+","+dependentAllowances+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Massachusetts")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			SpouseBlind = request.getParameter("spouse_blind");
			PersonalBlind = request.getParameter("personal_blind");
			HouseHold = request.getParameter("house_hold");
			Student = request.getParameter("student");
			
			
			//StateWHITAmount = (double)payRollBean.calculateMassachusettsWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, SocialsecureAmt, MedicareAmt, SpouseBlind, PersonalBlind, HouseHold, Student);
			state_wh_info = additionalStateWithHolding+","+totalAllowances+","+SpouseBlind+","+PersonalBlind+","+HouseHold
			+","+Student;
		}else if(state != null && state.equalsIgnoreCase("Oregon")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			
			//StateWHITAmount = (double)payRollBean.calculateOregonWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, filingStatus, federalWHITAmount);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("South Carolina")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			
			//StateWHITAmount = (double)payRollBean.calculateSouthCarolinaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances);
			state_wh_info = totalAllowances+","+additionalStateWithHolding;
		}else if(state != null && state.equalsIgnoreCase("North Carolina")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			//StateWHITAmount = (double)payRollBean.calculateNorthCarolinaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("North Dakota")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			
			//StateWHITAmount = (double)payRollBean.calculateNorthDakotaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, //totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Utah")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			
			//StateWHITAmount = (double)payRollBean.calculateUtahWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Delaware")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			
			//StateWHITAmount = (double)payRollBean.calculateDelawareWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Colorado")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			//StateWHITAmount = (double)payRollBean.calculateColoradoWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("California")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			extendedAllowances = Integer.parseInt(request.getParameter("extended_allowances"));
			regularAllowances = Integer.parseInt(request.getParameter("regular_allowances"));
			filingStatus = request.getParameter("filing_status");
			
			
			//StateWHITAmount = (double)payRollBean.calculateCaliforniaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, extendedAllowances, regularAllowances, filingStatus);
			state_wh_info = additionalStateWithHolding+","+extendedAllowances+","+regularAllowances+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Iowa")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			
			//StateWHITAmount = (double)payRollBean.calculateIowaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, federalWHITAmount);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Kansas")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			
			//StateWHITAmount = (double)payRollBean.calculateKansasWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Mississippi")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			exemptionAmount = Double.parseDouble(request.getParameter("exemption_amount"));
			filingStatus = request.getParameter("filing_status");
			
			
			//StateWHITAmount = (double)payRollBean.calculateMississippiWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, exemptionAmount, filingStatus);
			state_wh_info = exemptionAmount+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Michigan")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			
			//StateWHITAmount = (double)payRollBean.calculateMichiganWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances);
			state_wh_info = additionalStateWithHolding+","+totalAllowances;
		}else if(state != null && state.equalsIgnoreCase("Missouri")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			
			//StateWHITAmount = (double)payRollBean.calculateMissouriWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus, federalWHITAmount);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Pennsylvania")){
						
			//StateWHITAmount = (double)payRollBean.calculatePennsylvaniaWithHeld(Double.parseDouble(totalGross), PayPeriod);
		}else if(state != null && state.equalsIgnoreCase("Arizona")){
			WithholdingRate = Double.parseDouble(request.getParameter("withholding_rate"));
			
			//StateWHITAmount = (double)payRollBean.calculateArizonaWithHeld(Double.parseDouble(totalGross), PayPeriod, WithholdingRate);
			state_wh_info = String.valueOf(WithholdingRate);
		}else if(state != null && state.equalsIgnoreCase("Oklahoma")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			filingStatus = request.getParameter("filing_status");
			
			//StateWHITAmount = (double)payRollBean.calculateOklahomaWithHeld(Double.parseDouble(totalGross), PayPeriod, additionalStateWithHolding, totalAllowances, filingStatus);
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+filingStatus;
		}else if(state != null && state.equalsIgnoreCase("Ohio")){
			additionalStateWithHolding = Double.parseDouble(request.getParameter("additional_state_withholding"));
			totalAllowances = Integer.parseInt(request.getParameter("allowances"));
			DistrictRate = Double.parseDouble(request.getParameter("district_rate"));
			DistrictNo = Integer.parseInt(request.getParameter("district_no"));
			
			state_wh_info = totalAllowances+","+additionalStateWithHolding+","+DistrictRate+","+DistrictNo;
			request.setAttribute("schoolDistITAmount",String.valueOf(schoolDistITAmount));
		}
		
		if(uniquePayrollId != null && uniquePayrollId != "" && uniquePayrollId != "null"){
		empPayrollUpdateStatus = db.updateEmployeePayrollDetails(uniquePayrollId,uniqueEmployeeId,empId,empSSNNo,empFname,empLname,taxyear,chequeNo,chequeDate,"US",
				state,fWHITMarital,fWHITNoFAllowances,state_wh_info,PayPeriod,tax_paydurationFrom,tax_paydurationTo,PayRateType,
				overtime,Hourly_payrate,PayRateType,total_hours,totalYTDGross,totalGross,ot_row_count,ot_payrateArray,
				ot_payrate_typeArray,ot_total_hours_workedArray,ot_pay_ytdArray,ot_pay_cpArray,federal_it_ytd,
				federal_it_cp,social_rate,social_annual,social_ytd,social_cp,Medicare_rate,Medicare_ytd,
				Medicare_cp,State_IT_ytd,State_IT_cp,After_Tax_Income,After_Tax_Income_YTD,After_Tax_Income_CP,
				After_Tax_deduction,After_Tax_deduction_YTD,After_Tax_deduction_CP,String.valueOf(netAmount));
		}else{
			throw new Exception("Payroll Details not found to update!");
		}
		
		System.out.println("db.updateEmployeePayrollDetails status-------------->"+empPayrollUpdateStatus);
		System.out.println("Hourly_payrate-------------------->"+Hourly_payrate);
		System.out.println("total_hours-------------------->"+total_hours);
		System.out.println("YTD_Hourly-------------------->"+YTD_Hourly);
		//System.out.println("Monthly_payrate-------------------->"+Monthly_payrate);
		//System.out.println("YTD_Monthly-------------------->"+YTD_Monthly);
				
		System.out.println("ot_row_count-------------------->"+ot_row_count);
		
		
		System.out.println("taxyear------------------->"+taxyear);
		System.out.println("state------------------->"+state);
		System.out.println("chequeNo------------------->"+chequeNo);
		System.out.println("chequeDate------------------->"+chequeDate);
		System.out.println("VI_NofExemptions------------------->"+NofExemptions);
		System.out.println("VI_age_exemptions------------------->"+age_exemptions);
		System.out.println("VI_additional_withholding------------------->"+additional_withholding);
		System.out.println("fWHITMarital------------------->"+fWHITMarital);
		System.out.println("fWHITNoFAllowances------------------->"+fWHITNoFAllowances);
		System.out.println("PayPeriod------------------->"+PayPeriod);
		System.out.println("tax_paydurationFrom--------------------->"+tax_paydurationFrom);
		System.out.println("tax_paydurationTo--------------------->"+tax_paydurationTo);
		System.out.println("PayRateType------------------->"+PayRateType);
		System.out.println("overtime------------------->"+overtime);
		System.out.println("total_gross_pay_hourly------------------->"+total_gross_pay_hourly);
		//System.out.println("gross_pay_monthly------------------->"+total_gross_pay_monthly);
		System.out.println("federal_it_cp------------------->"+federal_it_cp);
		System.out.println("social_cp------------------->"+social_cp);
		System.out.println("Medicare_cp------------------->"+Medicare_cp);
		System.out.println("State_IT_cp------------------->"+State_IT_cp);
		System.out.println("After_Tax_Income_CP------------------->"+After_Tax_Income_CP);
		System.out.println("After_Tax_deduction_CP------------------->"+After_Tax_deduction_CP);
		
		System.out.println("federal_it_rate-------------------->"+federal_it_rate);
		System.out.println("federal_it_annual-------------------->"+federal_it_annual);
		System.out.println("federal_it_ytd-------------------->"+federal_it_ytd);
		System.out.println("social_rate-------------------->"+social_rate);
		System.out.println("social_annual-------------------->"+social_annual);
		System.out.println("social_ytd-------------------->"+social_ytd);
		System.out.println("Medicare_rate-------------------->"+Medicare_rate);
		System.out.println("Medicare_annual-------------------->"+Medicare_annual);
		System.out.println("Medicare_ytd-------------------->"+Medicare_ytd);
		System.out.println("State_IT_rate-------------------->"+State_IT_rate);
		System.out.println("State_IT_annual-------------------->"+State_IT_annual);
		System.out.println("State_IT_ytd-------------------->"+State_IT_ytd);
		System.out.println("After_Tax_Income-------------------->"+After_Tax_Income);
		System.out.println("After_Tax_Income_YTD-------------------->"+After_Tax_Income_YTD);
		System.out.println("After_Tax_deduction-------------------->"+After_Tax_deduction);
		System.out.println("After_Tax_deduction_YTD-------------------->"+After_Tax_deduction_YTD);
		
		
		
		
		//social_security_amount = (Double.parseDouble(totalGross) * Double.parseDouble(social_rate));
		//medicare_amount = (Double.parseDouble(totalGross) * Double.parseDouble(Medicare_rate));
		
		//social_cp = String.valueOf(social_security_amount);
		//Medicare_cp = String.valueOf(medicare_amount);
		
		String totalCpTaxes = null;
		totalCpTaxes = String.valueOf(Double.parseDouble(federal_it_cp) + Double.parseDouble(State_IT_cp)
				+ Double.parseDouble(social_cp) + Double.parseDouble(Medicare_cp));
		totalCpTaxes = String.valueOf(Math.round(Double.parseDouble(totalCpTaxes) * 100D) / 100D);
		request.setAttribute("netAmount", String.valueOf(netAmount));
		String totalYTDTaxes = null;
		totalYTDTaxes = String.valueOf(Double.parseDouble(federal_it_ytd) + Double.parseDouble(State_IT_ytd)
				+ Double.parseDouble(social_ytd) + Double.parseDouble(Medicare_ytd));
		totalYTDTaxes = String.valueOf(Math.round(Double.parseDouble(totalYTDTaxes) * 100D) / 100D);
		
		System.out.println("Total federalWHITAmount----------------------->"+federal_it_cp);
		System.out.println("Total StateWHITAmount----------------------->"+State_IT_cp);
		System.out.println("Social security amount----------------------->"+social_cp);
		System.out.println("Medicare amount----------------------->"+Medicare_cp);
		
		System.out.println("Total Current Pay(CP) Taxes----------------->"+totalCpTaxes);
		System.out.println("Total Year to Pay(YTD) Taxes----------------->"+totalYTDTaxes);
		
	   
		      
		ArrayList employeePayrollList = new ArrayList();
		employeePayrollList = (ArrayList)db.getAllEmployeePayrollDetails();
        request.setAttribute("employeePayrollList",null);
        request.setAttribute("employeePayrollList",employeePayrollList);
        request.setAttribute("pageStatus","read");
		return new ModelAndView("requirements/frmListEmployeePayrollDetails");
		
	}
	
	
	@RequestMapping("/empViewPayroll.html")
	public ModelAndView empViewPayroll(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Debug.print("PayrollAction.empViewPayroll()");
		
		String unique_payroll_id = null;
		String emp_unique_id = null;
		String emp_id = null;
		String emp_ssn_no = null;
		String emp_name = null;
		String tax_year = null;
		String tax_country = null;
		String cheque_no = null;
		String cheque_date = null;
		String tax_state = null;
		String fed_marital_status = null;
		String fed_nof_allowance = null;
		String state_wh_info = null;
		String tax_payperiod = null;
		String tax_payduration_from = null;
		String tax_payduration_to = null;
		String tax_payrate_type = null;
		String if_overtime = null;
		String gen_payrate = null;
		String gen_payrate_type = null;
		String gen_total_hours_worked = null;
		String gen_pay_ytd = null;
		String gen_pay_cp = null;
		String ot_row_count = null;
		String ot_payrate = null;
		String ot_payrate_type = null;
		String ot_total_hours_worked = null;
		String ot_pay_ytd = null;
		String ot_pay_cp = null;
		String fed_pay_ytd = null;
		String fed_pay_cp = null;
		String ss_payrate = null;
		String ss_annual_max = null;
		String ss_pay_ytd = null;
		String ss_pay_cp = null;
		String mc_payrate = null;
		String mc_pay_ytd = null;
		String mc_pay_cp = null;
		String state_pay_ytd = null;
		String state_pay_cp = null;
		String after_tax_income_pay = null;
		String after_tax_income_ytd = null;
		String after_tax_income_cp = null;
		String after_tax_deduction_pay = null;
		String after_tax_deduction_ytd = null;
		String after_tax_deduction_cp = null;
		String total_netpay = null;
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		
		unique_payroll_id = request.getParameter("payrollId");
		emp_unique_id = request.getParameter("empUniqueId");
		
		System.out.println("payrollId------------->"+unique_payroll_id);
		System.out.println("empUniqueId------------->"+emp_unique_id);
		
		ArrayList employeePayrollDetails = new ArrayList();
		
		if(unique_payroll_id == null){
			throw new Exception("Employee Payroll Details Not Found!");
			}else{
				
		        employeePayrollDetails = (ArrayList)db.getEmployeePayrollDetailsByUniqueId(unique_payroll_id);
		        
		        if(employeePayrollDetails!=null && employeePayrollDetails.size()!=0){
					Iterator itr = employeePayrollDetails.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_payroll_id = TempList[0];
					    emp_unique_id = TempList[1];
					    emp_id = TempList[2];
					    emp_ssn_no = TempList[3];
					    emp_name = TempList[4];
					    tax_year = TempList[5];
					    tax_country = TempList[6];
					    tax_state = TempList[7];
					    fed_marital_status = TempList[8];
					    fed_nof_allowance = TempList[9];
					    state_wh_info = TempList[10];
					    tax_payperiod = TempList[11];
					    tax_payduration_from = TempList[12];
					    tax_payduration_to = TempList[13];
					    tax_payrate_type = TempList[14];
					    if_overtime = TempList[15];
					    gen_payrate = TempList[16];
					    gen_payrate_type = TempList[17];
					    gen_total_hours_worked = TempList[18];
					    gen_pay_ytd = TempList[19];
					    gen_pay_cp = TempList[20];
					    ot_row_count = TempList[21];
					    ot_payrate = TempList[22];
					    ot_payrate_type = TempList[23];
					    ot_total_hours_worked = TempList[24];
					    ot_pay_ytd = TempList[25];
					    ot_pay_cp = TempList[26];
					    fed_pay_ytd = TempList[27];
					    fed_pay_cp = TempList[28];
					    ss_payrate = TempList[29];
					    ss_annual_max = TempList[30];
					    ss_pay_ytd = TempList[31];
					    ss_pay_cp = TempList[32];
					    mc_payrate = TempList[33];
					    mc_pay_ytd = TempList[34];
					    mc_pay_cp = TempList[35];
					    state_pay_ytd = TempList[36];
					    state_pay_cp = TempList[37];
					    after_tax_income_pay = TempList[38];
					    after_tax_income_ytd = TempList[39];
					    after_tax_income_cp = TempList[40];
					    after_tax_deduction_pay = TempList[41];
					    after_tax_deduction_ytd = TempList[42];
					    after_tax_deduction_cp = TempList[43];
					    total_netpay = TempList[44];
					    cheque_no = TempList[45];
					    cheque_date = TempList[46];
				    }
		        }
			}
		
		if(emp_unique_id == null){
			throw new Exception("Employee Not Found!");
			}else{
				ArrayList employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
				request.setAttribute("employeeDetails",employeeDetails);	
				request.setAttribute("uniquePayrollId", unique_payroll_id);
			}
		 
		if(if_overtime != null && if_overtime != "null" && !(if_overtime.equalsIgnoreCase("null"))){
		String[] overtimerate = new String[Integer.parseInt(ot_row_count)+1];
		String[] overtimepaytype = new String[Integer.parseInt(ot_row_count)+1];
		String[] Hour_ot = new String[Integer.parseInt(ot_row_count)+1];
		String[] ytd_ot = new String[Integer.parseInt(ot_row_count)+1];
		String[] cp_ot = new String[Integer.parseInt(ot_row_count)+1];
		
		overtimerate = ot_payrate.split(",");
		overtimepaytype = ot_payrate_type.split(",");
		Hour_ot = ot_total_hours_worked.split(",");
		ytd_ot = ot_pay_ytd.split(",");
		cp_ot = ot_pay_cp.split(",");
		
		for(int i=0;i<=Integer.parseInt(ot_row_count);i++){
		request.setAttribute("overtimerate"+i,overtimerate[i+1]);
		request.setAttribute("overtimepaytype"+i,overtimepaytype[i+1]);
		request.setAttribute("Hour_ot"+i,Hour_ot[i+1]);
		request.setAttribute("ytd_ot"+i,ytd_ot[i+1]);
		request.setAttribute("cp_ot"+i,cp_ot[i+1]);
		
		System.out.println("overtimerate["+i+"]---------->"+overtimerate[i+1]);
		System.out.println("overtimepaytype["+i+"]---------->"+overtimepaytype[i+1]);
		System.out.println("Hour_ot["+i+"]---------->"+Hour_ot[i+1]);
		System.out.println("ytd_ot["+i+"]---------->"+ytd_ot[i+1]);
		System.out.println("cp_ot["+i+"]---------->"+cp_ot[i+1]);
		}
		}

		System.out.println("taxyear----------------------->"+tax_year);
		System.out.println("state----------------------->"+tax_state);
		System.out.println("chequeNo------------------->"+cheque_no);
		System.out.println("chequeDate------------------->"+cheque_date);
		System.out.println("fWHITMarital----------------------->"+fed_marital_status);
		System.out.println("fWHITNoFAllowances----------------------->"+fed_nof_allowance);
		System.out.println("PayPeriod----------------------->"+tax_payperiod);
		System.out.println("tax_payduration_from----------------------->"+ tax_payduration_from);
		System.out.println("tax_payduration_to----------------------->"+ tax_payduration_to);
//		String []tempTaxPayDuration = tax_payduration.split("/");
//		tax_paydurationFrom = tempTaxPayDuration[0];
//		tax_paydurationTo = tempTaxPayDuration[1];
		System.out.println("PayRateType----------------------->"+tax_payrate_type);
		System.out.println("overtime----------------------->"+if_overtime);
		System.out.println("total_gross_pay_hourly----------------------->"+gen_pay_cp);
		System.out.println("gross_pay_monthly----------------------->"+gen_pay_cp);
		System.out.println("federal_it_cp----------------------->"+fed_pay_cp);
		System.out.println("social_cp----------------------->"+ss_pay_cp);
		System.out.println("Medicare_cp----------------------->"+mc_pay_cp);
		System.out.println("State_IT_cp----------------------->"+state_pay_cp);
		System.out.println("After_Tax_Income_CP----------------------->"+after_tax_income_cp);
		System.out.println("After_Tax_deduction_CP----------------------->"+after_tax_deduction_cp);
		System.out.println("federal_it_rate----------------------->"+"");
		System.out.println("federal_it_annual----------------------->"+"");
		System.out.println("federal_it_ytd----------------------->"+fed_pay_ytd);
		System.out.println("social_rate----------------------->"+ss_payrate);
		System.out.println("social_annual----------------------->"+ss_annual_max);
		System.out.println("social_ytd----------------------->"+ss_pay_ytd);
		System.out.println("Medicare_rate----------------------->"+mc_payrate);
		System.out.println("Medicare_annual----------------------->"+"");
		System.out.println("Medicare_ytd----------------------->"+mc_pay_ytd);
		System.out.println("State_IT_rate----------------------->"+"");
		System.out.println("State_IT_annual----------------------->"+"");
		System.out.println("State_IT_ytd----------------------->"+state_pay_ytd);
		System.out.println("After_Tax_Income----------------------->"+after_tax_income_pay);
		System.out.println("After_Tax_Income_YTD----------------------->"+after_tax_income_ytd);
		System.out.println("After_Tax_deduction----------------------->"+after_tax_deduction_pay);
		System.out.println("After_Tax_deduction_YTD----------------------->"+after_tax_deduction_ytd);
		System.out.println("Hourly_payrate----------------------->"+gen_payrate);
		System.out.println("total_hours----------------------->"+gen_total_hours_worked);
		System.out.println("YTD_Hourly----------------------->"+gen_pay_ytd);
		//System.out.println("Monthly_payrate----------------------->"+gen_payrate);
		//System.out.println("YTD_Monthly----------------------->"+gen_pay_ytd);
		System.out.println("overtimecount----------------------->"+ot_row_count);
		System.out.println("totalGross----------------------->"+ "");
		System.out.println("federalWHITAmount----------------------->"+ fed_pay_cp);
		System.out.println("StateWHITAmount_VA----------------------->"+ state_pay_cp);
		System.out.println("netAmount----------------------->"+ total_netpay);
		
		if(tax_state != null && tax_state.equalsIgnoreCase("Virginia")){
				
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("VI_NofExemptions---------->"+stateWitholdingInfo[0]);
			System.out.println("VI_age_exemptions---------->"+stateWitholdingInfo[1]);
			System.out.println("VI_additional_withholding---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("VI_NofExemptions",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("VI_age_exemptions",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("VI_additional_withholding",String.valueOf(stateWitholdingInfo[2]));
				
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Georgia")){
			
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("personal_allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("dependent_allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			System.out.println("total_allowances---------->"+stateWitholdingInfo[3]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[4]);
			System.out.println("additional_allowances---------->"+stateWitholdingInfo[5]);
			
			request.setAttribute("personal_allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("dependent_allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
			request.setAttribute("total_allowances",String.valueOf(stateWitholdingInfo[3]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[4]));
			request.setAttribute("additional_allowances",String.valueOf(stateWitholdingInfo[5]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Arkansas")){
			
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("total_allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
			System.out.println("texarna_resident---------->"+stateWitholdingInfo[3]);
						
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("total_allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[2]));
			request.setAttribute("texarna_resident",String.valueOf(stateWitholdingInfo[3]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Idaho")){
			
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("total_allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
						
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("total_allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[2]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Alabama")){
			
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("dependentAllowances---------->"+stateWitholdingInfo[1]);
			System.out.println("allowances---------->"+stateWitholdingInfo[2]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[3]);
						
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("dependent_allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[2]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[3]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Connecticut")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("withholding_code---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
						
			request.setAttribute("withholding_code",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));

		}else if(tax_state != null && tax_state.equalsIgnoreCase("Illinois")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("additional_allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("basic_allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
						
			request.setAttribute("additional_allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("basic_allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Indiana")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("personal_deduction---------->"+stateWitholdingInfo[0]);
			System.out.println("dependent_deduction---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
						
			request.setAttribute("personal_deduction",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("dependent_deduction",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[2]));

		}else if(tax_state != null && tax_state.equalsIgnoreCase("Kentucky")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
						
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));

		}else if(tax_state != null && tax_state.equalsIgnoreCase("Maine")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
						
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[2]));

		}else if(tax_state != null && tax_state.equalsIgnoreCase("Minnesota")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
						
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Wisconsin")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("WHExemptions---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
						
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("WHExemptions",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Rhode Island")){
			String []stateWitholdingInfo = state_wh_info.split(",");
		
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
				
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Vermont")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
						
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("New Jersey")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("rate_code---------->"+stateWitholdingInfo[1]);
			System.out.println("allowances---------->"+stateWitholdingInfo[2]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[3]);
						
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("rate_code",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[2]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[3]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("District of Columbia")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Nebraska")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Montana")){
		
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("New Mexico")){
		
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("West Virginia")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Hawaii")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Louisiana")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[0]);
			System.out.println("personal_allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("dependent_allowances---------->"+stateWitholdingInfo[2]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[3]);
			
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("personal_allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("dependent_allowances",String.valueOf(stateWitholdingInfo[2]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[3]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Massachusetts")){
			
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[0]);
			System.out.println("allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("spouse_blind---------->"+stateWitholdingInfo[2]);
			System.out.println("personal_blind---------->"+stateWitholdingInfo[3]);
			System.out.println("house_hold---------->"+stateWitholdingInfo[4]);
			System.out.println("student---------->"+stateWitholdingInfo[5]);
			
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("spouse_blind",String.valueOf(stateWitholdingInfo[2]));
			request.setAttribute("personal_blind",String.valueOf(stateWitholdingInfo[3]));
			request.setAttribute("house_hold",String.valueOf(stateWitholdingInfo[4]));
			request.setAttribute("student",String.valueOf(stateWitholdingInfo[5]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Oregon")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("South Carolina")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("North Carolina")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("North Dakota")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Utah")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Delaware")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Colorado")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("California")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[0]);
			System.out.println("extendedAllowances---------->"+stateWitholdingInfo[1]);
			System.out.println("regularAllowances---------->"+stateWitholdingInfo[2]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[3]);
			
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("extended_allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("regular_allowances",String.valueOf(stateWitholdingInfo[2]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[3]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Iowa")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Kansas")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Mississippi")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[0]);
			System.out.println("exemption_amount---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("exemption_amount",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Michigan")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[0]);
			System.out.println("allowances---------->"+stateWitholdingInfo[1]);
			
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[1]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Missouri")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Pennsylvania")){
						
			//StateWHITAmount = (double)payRollBean.calculatePennsylvaniaWithHeld(Double.parseDouble(totalGross), PayPeriod);
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Arizona")){
			
			System.out.println("withholding_rate---------->"+state_wh_info);
			
			request.setAttribute("withholding_rate",String.valueOf(state_wh_info));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Oklahoma")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Ohio")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("DistrictRate---------->"+stateWitholdingInfo[2]);
			System.out.println("DistrictNo---------->"+stateWitholdingInfo[3]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("district_rate",String.valueOf(stateWitholdingInfo[2]));
			request.setAttribute("district_no",String.valueOf(stateWitholdingInfo[3]));
		}
		
		request.setAttribute("taxyear",tax_year);
		request.setAttribute("state",tax_state);
		request.setAttribute("chequeNo",cheque_no);
		request.setAttribute("chequeDate",cheque_date);
		request.setAttribute("fWHITMarital",fed_marital_status);
		request.setAttribute("fWHITNoFAllowances",fed_nof_allowance);
		request.setAttribute("PayPeriod",tax_payperiod);
		request.setAttribute("payDurationFrom", tax_payduration_from);
		request.setAttribute("payDurationTo", tax_payduration_to);
		request.setAttribute("PayRateType",tax_payrate_type);
		request.setAttribute("overtime",if_overtime);
		request.setAttribute("total_gross_pay_hourly",gen_pay_cp);
		//request.setAttribute("gross_pay_monthly",gen_pay_cp);
		request.setAttribute("federal_it_cp",fed_pay_cp);
		request.setAttribute("social_cp",ss_pay_cp);
		request.setAttribute("Medicare_cp",mc_pay_cp);
		request.setAttribute("State_IT_cp",state_pay_cp);
		request.setAttribute("After_Tax_Income_CP",after_tax_income_cp);
		request.setAttribute("After_Tax_deduction_CP",after_tax_deduction_cp);
		request.setAttribute("federal_it_rate","");
		request.setAttribute("federal_it_annual","");
		request.setAttribute("federal_it_ytd",fed_pay_ytd);
		request.setAttribute("social_rate",ss_payrate);
		request.setAttribute("social_annual",ss_annual_max);
		request.setAttribute("social_ytd",ss_pay_ytd);
		request.setAttribute("Medicare_rate",mc_payrate);
		request.setAttribute("Medicare_annual","");
		request.setAttribute("Medicare_ytd",mc_pay_ytd);
		request.setAttribute("State_IT_rate","");
		request.setAttribute("State_IT_annual","");
		request.setAttribute("State_IT_ytd",state_pay_ytd);
		request.setAttribute("After_Tax_Income",after_tax_income_pay);
		request.setAttribute("After_Tax_Income_YTD",after_tax_income_ytd);
		request.setAttribute("After_Tax_deduction",after_tax_deduction_pay);
		request.setAttribute("After_Tax_deduction_YTD",after_tax_deduction_ytd);
		request.setAttribute("Hourly_payrate",gen_payrate);
		request.setAttribute("total_hours",gen_total_hours_worked);
		request.setAttribute("YTD_Hourly",gen_pay_ytd);
		//request.setAttribute("Monthly_payrate",gen_payrate);
		//request.setAttribute("YTD_Monthly",gen_pay_ytd);
		request.setAttribute("overtimecount",ot_row_count);
		request.setAttribute("totalGross", "");
		request.setAttribute("federalWHITAmount", fed_pay_cp);
		request.setAttribute("StateWHITAmount_VA", state_pay_cp);
		request.setAttribute("netAmount", total_netpay);
		request.setAttribute("pageStatus","view");
		
		return new ModelAndView("requirements/frmViewPayrollCalculator");
	}
	
	@RequestMapping("/empEditPayroll.html")
	public ModelAndView empEditPayroll(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Debug.print("PayrollAction.empEditPayroll()");
		
		String unique_payroll_id = null;
		String emp_unique_id = null;
		String emp_id = null;
		String emp_ssn_no = null;
		String emp_name = null;
		String tax_year = null;
		String tax_country = null;
		String cheque_no = null;
		String cheque_date = null;
		String tax_state = null;
		String fed_marital_status = null;
		String fed_nof_allowance = null;
		String state_wh_info = null;
		String tax_payperiod = null;
		String tax_payduration_from = null;
		String tax_payduration_to = null;
		String tax_payrate_type = null;
		String if_overtime = null;
		String gen_payrate = null;
		String gen_payrate_type = null;
		String gen_total_hours_worked = null;
		String gen_pay_ytd = null;
		String gen_pay_cp = null;
		String ot_row_count = null;
		String ot_payrate = null;
		String ot_payrate_type = null;
		String ot_total_hours_worked = null;
		String ot_pay_ytd = null;
		String ot_pay_cp = null;
		String fed_pay_ytd = null;
		String fed_pay_cp = null;
		String ss_payrate = null;
		String ss_annual_max = null;
		String ss_pay_ytd = null;
		String ss_pay_cp = null;
		String mc_payrate = null;
		String mc_pay_ytd = null;
		String mc_pay_cp = null;
		String state_pay_ytd = null;
		String state_pay_cp = null;
		String after_tax_income_pay = null;
		String after_tax_income_ytd = null;
		String after_tax_income_cp = null;
		String after_tax_deduction_pay = null;
		String after_tax_deduction_ytd = null;
		String after_tax_deduction_cp = null;
		String total_netpay = null;
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		
		unique_payroll_id = request.getParameter("payrollId");
		emp_unique_id = request.getParameter("empUniqueId");
		
		System.out.println("payrollId------------->"+unique_payroll_id);
		System.out.println("empUniqueId------------->"+emp_unique_id);
		
		ArrayList employeePayrollDetails = new ArrayList();
		
		if(unique_payroll_id == null){
			throw new Exception("Employee Payroll Details Not Found!");
			}else{
				
		        employeePayrollDetails = (ArrayList)db.getEmployeePayrollDetailsByUniqueId(unique_payroll_id);
		        
		        if(employeePayrollDetails!=null && employeePayrollDetails.size()!=0){
					Iterator itr = employeePayrollDetails.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_payroll_id = TempList[0];
					    emp_unique_id = TempList[1];
					    emp_id = TempList[2];
					    emp_ssn_no = TempList[3];
					    emp_name = TempList[4];
					    tax_year = TempList[5];
					    tax_country = TempList[6];
					    tax_state = TempList[7];
					    fed_marital_status = TempList[8];
					    fed_nof_allowance = TempList[9];
					    state_wh_info = TempList[10];
					    tax_payperiod = TempList[11];
					    tax_payduration_from = TempList[12];
					    tax_payduration_to = TempList[13];
					    tax_payrate_type = TempList[14];
					    if_overtime = TempList[15];
					    gen_payrate = TempList[16];
					    gen_payrate_type = TempList[17];
					    gen_total_hours_worked = TempList[18];
					    gen_pay_ytd = TempList[19];
					    gen_pay_cp = TempList[20];
					    ot_row_count = TempList[21];
					    ot_payrate = TempList[22];
					    ot_payrate_type = TempList[23];
					    ot_total_hours_worked = TempList[24];
					    ot_pay_ytd = TempList[25];
					    ot_pay_cp = TempList[26];
					    fed_pay_ytd = TempList[27];
					    fed_pay_cp = TempList[28];
					    ss_payrate = TempList[29];
					    ss_annual_max = TempList[30];
					    ss_pay_ytd = TempList[31];
					    ss_pay_cp = TempList[32];
					    mc_payrate = TempList[33];
					    mc_pay_ytd = TempList[34];
					    mc_pay_cp = TempList[35];
					    state_pay_ytd = TempList[36];
					    state_pay_cp = TempList[37];
					    after_tax_income_pay = TempList[38];
					    after_tax_income_ytd = TempList[39];
					    after_tax_income_cp = TempList[40];
					    after_tax_deduction_pay = TempList[41];
					    after_tax_deduction_ytd = TempList[42];
					    after_tax_deduction_cp = TempList[43];
					    total_netpay = TempList[44];
					    cheque_no = TempList[45];
					    cheque_date = TempList[46];
				    }
		        }
			}
		
		if(emp_unique_id == null){
			throw new Exception("Employee Not Found!");
			}else{
				ArrayList employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
				request.setAttribute("employeeDetails",employeeDetails);	
				request.setAttribute("uniquePayrollId",unique_payroll_id);
			}
		  
		if(if_overtime != null && if_overtime != "null" && !(if_overtime.equalsIgnoreCase("null"))){
		String[] overtimerate = new String[Integer.parseInt(ot_row_count)+1];
		String[] overtimepaytype = new String[Integer.parseInt(ot_row_count)+1];
		String[] Hour_ot = new String[Integer.parseInt(ot_row_count)+1];
		String[] ytd_ot = new String[Integer.parseInt(ot_row_count)+1];
		String[] cp_ot = new String[Integer.parseInt(ot_row_count)+1];
		
		overtimerate = ot_payrate.split(",");
		overtimepaytype = ot_payrate_type.split(",");
		Hour_ot = ot_total_hours_worked.split(",");
		ytd_ot = ot_pay_ytd.split(",");
		cp_ot = ot_pay_cp.split(",");
		
		for(int i=0;i<=Integer.parseInt(ot_row_count);i++){
		request.setAttribute("overtimerate"+i,overtimerate[i+1]);
		request.setAttribute("overtimepaytype"+i,overtimepaytype[i+1]);
		request.setAttribute("Hour_ot"+i,Hour_ot[i+1]);
		request.setAttribute("ytd_ot"+i,ytd_ot[i+1]);
		request.setAttribute("cp_ot"+i,cp_ot[i+1]);
		
		System.out.println("overtimerate["+i+"]---------->"+overtimerate[i+1]);
		System.out.println("overtimepaytype["+i+"]---------->"+overtimepaytype[i+1]);
		System.out.println("Hour_ot["+i+"]---------->"+Hour_ot[i+1]);
		System.out.println("ytd_ot["+i+"]---------->"+ytd_ot[i+1]);
		System.out.println("cp_ot["+i+"]---------->"+cp_ot[i+1]);
		}
		}

		System.out.println("taxyear----------------------->"+tax_year);
		System.out.println("state----------------------->"+tax_state);
		System.out.println("chequeNo------------------->"+cheque_no);
		System.out.println("chequeDate------------------->"+cheque_date);
		System.out.println("fWHITMarital----------------------->"+fed_marital_status);
		System.out.println("fWHITNoFAllowances----------------------->"+fed_nof_allowance);
		System.out.println("PayPeriod----------------------->"+tax_payperiod);
		System.out.println("tax_payduration_from--------------------->"+tax_payduration_from);
		System.out.println("tax_payduration_to--------------------->"+tax_payduration_to);
		System.out.println("PayRateType----------------------->"+tax_payrate_type);
		System.out.println("overtime----------------------->"+if_overtime);
		System.out.println("total_gross_pay_hourly----------------------->"+gen_pay_cp);
		//System.out.println("gross_pay_monthly----------------------->"+gen_pay_cp);
		System.out.println("federal_it_cp----------------------->"+fed_pay_cp);
		System.out.println("social_cp----------------------->"+ss_pay_cp);
		System.out.println("Medicare_cp----------------------->"+mc_pay_cp);
		System.out.println("State_IT_cp----------------------->"+state_pay_cp);
		System.out.println("After_Tax_Income_CP----------------------->"+after_tax_income_cp);
		System.out.println("After_Tax_deduction_CP----------------------->"+after_tax_deduction_cp);
		System.out.println("federal_it_rate----------------------->"+"");
		System.out.println("federal_it_annual----------------------->"+"");
		System.out.println("federal_it_ytd----------------------->"+fed_pay_ytd);
		System.out.println("social_rate----------------------->"+ss_payrate);
		System.out.println("social_annual----------------------->"+ss_annual_max);
		System.out.println("social_ytd----------------------->"+ss_pay_ytd);
		System.out.println("Medicare_rate----------------------->"+mc_payrate);
		System.out.println("Medicare_annual----------------------->"+"");
		System.out.println("Medicare_ytd----------------------->"+mc_pay_ytd);
		System.out.println("State_IT_rate----------------------->"+"");
		System.out.println("State_IT_annual----------------------->"+"");
		System.out.println("State_IT_ytd----------------------->"+state_pay_ytd);
		System.out.println("After_Tax_Income----------------------->"+after_tax_income_pay);
		System.out.println("After_Tax_Income_YTD----------------------->"+after_tax_income_ytd);
		System.out.println("After_Tax_deduction----------------------->"+after_tax_deduction_pay);
		System.out.println("After_Tax_deduction_YTD----------------------->"+after_tax_deduction_ytd);
		System.out.println("Hourly_payrate----------------------->"+gen_payrate);
		System.out.println("total_hours----------------------->"+gen_total_hours_worked);
		System.out.println("YTD_Hourly----------------------->"+gen_pay_ytd);
		//System.out.println("Monthly_payrate----------------------->"+gen_payrate);
		//System.out.println("YTD_Monthly----------------------->"+gen_pay_ytd);
		System.out.println("overtimecount----------------------->"+ot_row_count);
		System.out.println("totalGross----------------------->"+ "");
		System.out.println("federalWHITAmount----------------------->"+ fed_pay_cp);
		System.out.println("StateWHITAmount_VA----------------------->"+ state_pay_cp);
		System.out.println("netAmount----------------------->"+ total_netpay);
		
		if(tax_state != null && tax_state.equalsIgnoreCase("Virginia")){
			
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("VI_NofExemptions---------->"+stateWitholdingInfo[0]);
			System.out.println("VI_age_exemptions---------->"+stateWitholdingInfo[1]);
			System.out.println("VI_additional_withholding---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("VI_NofExemptions",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("VI_age_exemptions",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("VI_additional_withholding",String.valueOf(stateWitholdingInfo[2]));
				
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Georgia")){
			
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("personal_allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("dependent_allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			System.out.println("total_allowances---------->"+stateWitholdingInfo[3]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[4]);
			System.out.println("additional_allowances---------->"+stateWitholdingInfo[5]);
			
			request.setAttribute("personal_allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("dependent_allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
			request.setAttribute("total_allowances",String.valueOf(stateWitholdingInfo[3]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[4]));
			request.setAttribute("additional_allowances",String.valueOf(stateWitholdingInfo[5]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Arkansas")){
			
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("total_allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
			System.out.println("texarna_resident---------->"+stateWitholdingInfo[3]);
						
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("total_allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[2]));
			request.setAttribute("texarna_resident",String.valueOf(stateWitholdingInfo[3]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Idaho")){
			
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("total_allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
						
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("total_allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[2]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Alabama")){
			
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("dependentAllowances---------->"+stateWitholdingInfo[1]);
			System.out.println("allowances---------->"+stateWitholdingInfo[2]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[3]);
						
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("dependent_allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[2]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[3]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Connecticut")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("withholding_code---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
						
			request.setAttribute("withholding_code",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));

		}else if(tax_state != null && tax_state.equalsIgnoreCase("Illinois")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("additional_allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("basic_allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
						
			request.setAttribute("additional_allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("basic_allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Indiana")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("personal_deduction---------->"+stateWitholdingInfo[0]);
			System.out.println("dependent_deduction---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
						
			request.setAttribute("personal_deduction",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("dependent_deduction",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[2]));

		}else if(tax_state != null && tax_state.equalsIgnoreCase("Kentucky")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
						
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));

		}else if(tax_state != null && tax_state.equalsIgnoreCase("Maine")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
						
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[2]));

		}else if(tax_state != null && tax_state.equalsIgnoreCase("Minnesota")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
						
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Wisconsin")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("WHExemptions---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
						
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("WHExemptions",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Rhode Island")){
			String []stateWitholdingInfo = state_wh_info.split(",");
		
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
				
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Vermont")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
						
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("New Jersey")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("rate_code---------->"+stateWitholdingInfo[1]);
			System.out.println("allowances---------->"+stateWitholdingInfo[2]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[3]);
						
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("rate_code",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[2]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[3]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("District of Columbia")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Nebraska")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Montana")){
		
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("New Mexico")){
		
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("West Virginia")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Hawaii")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Louisiana")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[0]);
			System.out.println("personal_allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("dependent_allowances---------->"+stateWitholdingInfo[2]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[3]);
			
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("personal_allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("dependent_allowances",String.valueOf(stateWitholdingInfo[2]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[3]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Massachusetts")){
			
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[0]);
			System.out.println("allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("spouse_blind---------->"+stateWitholdingInfo[2]);
			System.out.println("personal_blind---------->"+stateWitholdingInfo[3]);
			System.out.println("house_hold---------->"+stateWitholdingInfo[4]);
			System.out.println("student---------->"+stateWitholdingInfo[5]);
			
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("spouse_blind",String.valueOf(stateWitholdingInfo[2]));
			request.setAttribute("personal_blind",String.valueOf(stateWitholdingInfo[3]));
			request.setAttribute("house_hold",String.valueOf(stateWitholdingInfo[4]));
			request.setAttribute("student",String.valueOf(stateWitholdingInfo[5]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Oregon")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("South Carolina")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("North Carolina")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("North Dakota")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Utah")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Delaware")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Colorado")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("California")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[0]);
			System.out.println("extendedAllowances---------->"+stateWitholdingInfo[1]);
			System.out.println("regularAllowances---------->"+stateWitholdingInfo[2]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[3]);
			
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("extended_allowances",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("regular_allowances",String.valueOf(stateWitholdingInfo[2]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[3]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Iowa")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Kansas")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Mississippi")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[0]);
			System.out.println("exemption_amount---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("exemption_amount",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Michigan")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[0]);
			System.out.println("allowances---------->"+stateWitholdingInfo[1]);
			
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[1]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Missouri")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Pennsylvania")){
						
			//StateWHITAmount = (double)payRollBean.calculatePennsylvaniaWithHeld(Double.parseDouble(totalGross), PayPeriod);
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Arizona")){
			
			System.out.println("withholding_rate---------->"+state_wh_info);
			
			request.setAttribute("withholding_rate",String.valueOf(state_wh_info));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Oklahoma")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("filing_status",String.valueOf(stateWitholdingInfo[2]));
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Ohio")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("DistrictRate---------->"+stateWitholdingInfo[2]);
			System.out.println("DistrictNo---------->"+stateWitholdingInfo[3]);
			
			request.setAttribute("allowances",String.valueOf(stateWitholdingInfo[0]));
			request.setAttribute("additional_state_withholding",String.valueOf(stateWitholdingInfo[1]));
			request.setAttribute("district_rate",String.valueOf(stateWitholdingInfo[2]));
			request.setAttribute("district_no",String.valueOf(stateWitholdingInfo[3]));
		}
		
		request.setAttribute("taxyear",tax_year);
		request.setAttribute("state",tax_state);
		request.setAttribute("chequeNo",cheque_no);
		request.setAttribute("chequeDate",cheque_date);
		request.setAttribute("fWHITMarital",fed_marital_status);
		request.setAttribute("fWHITNoFAllowances",fed_nof_allowance);
		request.setAttribute("PayPeriod",tax_payperiod);
		request.setAttribute("payDurationFrom", tax_payduration_from);
		request.setAttribute("payDurationTo", tax_payduration_to);
		request.setAttribute("PayRateType",tax_payrate_type);
		request.setAttribute("overtime",if_overtime);
		request.setAttribute("total_gross_pay_hourly",gen_pay_cp);
		//request.setAttribute("gross_pay_monthly",gen_pay_cp);
		request.setAttribute("federal_it_cp",fed_pay_cp);
		request.setAttribute("social_cp",ss_pay_cp);
		request.setAttribute("Medicare_cp",mc_pay_cp);
		request.setAttribute("State_IT_cp",state_pay_cp);
		request.setAttribute("After_Tax_Income_CP",after_tax_income_cp);
		request.setAttribute("After_Tax_deduction_CP",after_tax_deduction_cp);
		request.setAttribute("federal_it_rate","");
		request.setAttribute("federal_it_annual","");
		request.setAttribute("federal_it_ytd",fed_pay_ytd);
		request.setAttribute("social_rate",ss_payrate);
		request.setAttribute("social_annual",ss_annual_max);
		request.setAttribute("social_ytd",ss_pay_ytd);
		request.setAttribute("Medicare_rate",mc_payrate);
		request.setAttribute("Medicare_annual","");
		request.setAttribute("Medicare_ytd",mc_pay_ytd);
		request.setAttribute("State_IT_rate","");
		request.setAttribute("State_IT_annual","");
		request.setAttribute("State_IT_ytd",state_pay_ytd);
		request.setAttribute("After_Tax_Income",after_tax_income_pay);
		request.setAttribute("After_Tax_Income_YTD",after_tax_income_ytd);
		request.setAttribute("After_Tax_deduction",after_tax_deduction_pay);
		request.setAttribute("After_Tax_deduction_YTD",after_tax_deduction_ytd);
		request.setAttribute("Hourly_payrate",gen_payrate);
		request.setAttribute("total_hours",gen_total_hours_worked);
		request.setAttribute("YTD_Hourly",gen_pay_ytd);
		//request.setAttribute("Monthly_payrate",gen_payrate);
		//request.setAttribute("YTD_Monthly",gen_pay_ytd);
		request.setAttribute("overtimecount",ot_row_count);
		request.setAttribute("totalGross", "");
		request.setAttribute("federalWHITAmount", fed_pay_cp);
		request.setAttribute("StateWHITAmount_VA", state_pay_cp);
		request.setAttribute("netAmount", total_netpay);
        request.setAttribute("pageStatus","edit");
		return new ModelAndView("requirements/frmEditPayrollCalculator");
	}
	
	@RequestMapping("/empDeletePayroll.html")
	public ModelAndView empDeletePayroll(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Debug.print("PayrollAction.empDeletePayroll()");
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		ArrayList employeePayrollList = new ArrayList();
		
		String payrollId = null;
		String empUniqueId = null;
		
		payrollId = request.getParameter("payrollId");
		empUniqueId = request.getParameter("empUniqueId");
		
		System.out.println("payrollId------------->"+payrollId);
		System.out.println("empUniqueId------------->"+empUniqueId);
		
		boolean deleteStatus = false;
		if(payrollId == null || payrollId == ""){
			throw new Exception("Employee Payroll Details Not Found!");
		}else{
		deleteStatus = db.deleteEmployeePayrollDetailsByUniqueId(payrollId);
			if(deleteStatus){
		        request.setAttribute("status","success");
				employeePayrollList = (ArrayList)db.getAllEmployeePayrollDetails();
		        request.setAttribute("employeePayrollList",null);
		        request.setAttribute("employeePayrollList",employeePayrollList);
		        request.setAttribute("pageStatus","read");
		        return new ModelAndView("requirements/frmListEmployeePayrollDetails");
			}
		}
		employeePayrollList = (ArrayList)db.getAllEmployeePayrollDetails();
        request.setAttribute("employeePayrollList",null);
        request.setAttribute("employeePayrollList",employeePayrollList);
		request.setAttribute("pageStatus","read");
		return new ModelAndView("requirements/frmListEmployeePayrollDetails");
		
	}
	
	@RequestMapping("/empPrintPayroll.html")
	public ModelAndView empPrintPayroll(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Debug.print("PayrollAction.empPrintPayroll()");
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		String unique_payroll_id = null;
		String emp_unique_id = null;
		
		unique_payroll_id = request.getParameter("payrollId");
		emp_unique_id = request.getParameter("empUniqueId");
		
		System.out.println("payrollId------------->"+unique_payroll_id);
		System.out.println("empUniqueId------------->"+emp_unique_id);
		
		String emp_id = null;
		String emp_ssn_no = null;
		String emp_name = null;
		String tax_year = null;
		String tax_country = null;
		String tax_state = null;
		String cheque_no = null;
		String cheque_date = null;
		String fed_marital_status = null;
		String fed_nof_allowance = null;
		String state_wh_info = null;
		String tax_payperiod = null;
		String tax_payduration_from = null;
		String tax_payduration_to = null;
		String tax_payrate_type = null;
		String if_overtime = null;
		String gen_payrate = null;
		String gen_payrate_type = null;
		String gen_total_hours_worked = null;
		String gen_pay_ytd = null;
		String gen_pay_cp = null;
		String ot_row_count = null;
		String ot_payrate = null;
		String ot_payrate_type = null;
		String ot_total_hours_worked = null;
		String ot_pay_ytd = null;
		String ot_pay_cp = null;
		String fed_pay_ytd = null;
		String fed_pay_cp = null;
		String ss_payrate = null;
		String ss_annual_max = null;
		String ss_pay_ytd = null;
		String ss_pay_cp = null;
		String mc_payrate = null;
		String mc_pay_ytd = null;
		String mc_pay_cp = null;
		String state_pay_ytd = null;
		String state_pay_cp = null;
		String after_tax_income_pay = null;
		String after_tax_income_ytd = null;
		String after_tax_income_cp = null;
		String after_tax_deduction_pay = null;
		String after_tax_deduction_ytd = null;
		String after_tax_deduction_cp = null;
		String total_netpay = null;
		
		
		unique_payroll_id = request.getParameter("payrollId");
		emp_unique_id = request.getParameter("empUniqueId");
		
		System.out.println("payrollId------------->"+unique_payroll_id);
		System.out.println("empUniqueId------------->"+emp_unique_id);
		
		ArrayList employeePayrollDetails = new ArrayList();
		
		if(unique_payroll_id == null){
			throw new Exception("Employee Payroll Details Not Found!");
			}else{
				
		        employeePayrollDetails = (ArrayList)db.getEmployeePayrollDetailsByUniqueId(unique_payroll_id);
		        
		        if(employeePayrollDetails!=null && employeePayrollDetails.size()!=0){
					Iterator itr = employeePayrollDetails.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_payroll_id = TempList[0];
					    emp_unique_id = TempList[1];
					    emp_id = TempList[2];
					    emp_ssn_no = TempList[3];
					    emp_name = TempList[4];
					    tax_year = TempList[5];
					    tax_country = TempList[6];
					    tax_state = TempList[7];
					    fed_marital_status = TempList[8];
					    fed_nof_allowance = TempList[9];
					    state_wh_info = TempList[10];
					    tax_payperiod = TempList[11];
					    tax_payduration_from = TempList[12];
					    tax_payduration_to = TempList[13];
					    tax_payrate_type = TempList[14];
					    if_overtime = TempList[15];
					    gen_payrate = TempList[16];
					    gen_payrate_type = TempList[17];
					    gen_total_hours_worked = TempList[18];
					    gen_pay_ytd = TempList[19];
					    gen_pay_cp = TempList[20];
					    ot_row_count = TempList[21];
					    ot_payrate = TempList[22];
					    ot_payrate_type = TempList[23];
					    ot_total_hours_worked = TempList[24];
					    ot_pay_ytd = TempList[25];
					    ot_pay_cp = TempList[26];
					    fed_pay_ytd = TempList[27];
					    fed_pay_cp = TempList[28];
					    ss_payrate = TempList[29];
					    ss_annual_max = TempList[30];
					    ss_pay_ytd = TempList[31];
					    ss_pay_cp = TempList[32];
					    mc_payrate = TempList[33];
					    mc_pay_ytd = TempList[34];
					    mc_pay_cp = TempList[35];
					    state_pay_ytd = TempList[36];
					    state_pay_cp = TempList[37];
					    after_tax_income_pay = TempList[38];
					    after_tax_income_ytd = TempList[39];
					    after_tax_income_cp = TempList[40];
					    after_tax_deduction_pay = TempList[41];
					    after_tax_deduction_ytd = TempList[42];
					    after_tax_deduction_cp = TempList[43];
					    total_netpay = TempList[44];
					    cheque_no = TempList[45];
					    cheque_date = TempList[46];
				    }
		        }
			}
		
	/* ------------------Pay Stub Generation Code start Here----------------------*/
		
		String empId = null;
	    String empdberpPartyid = null;
	    String empSSNNo = null;
	    String empSalutation = null;
	    String empFname = null;
	    String empLname = null;
	    String empGender = null;
	    String empMaritalStatus = null;
	    String empEmailId = null;
	    String empAddress1c = null;
	    String empCityc = null;
	    String empStatec = null;
	    String empCountryc = null;
	    String empPostalCodec = null;
	    String empCompany = null;
	    String empJobTitle = null;
	    String empDepartment = null;
	    String empJobLocation = null;
	    String empPayGroup = null;
	    String empPayratePerhour = null;
	    String empPayPeriod = null;
		
		ArrayList employeeDetails = new ArrayList();
		
		if(emp_unique_id == null){
			throw new Exception("Employee Not Found!");
			}else{
				
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
		        
		        if(employeeDetails!=null && employeeDetails.size()!=0){
					Iterator itr = employeeDetails.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
				    	empId = TempList[3];
					    empdberpPartyid = TempList[4];
					    empSSNNo = TempList[5];
					    empSalutation = TempList[6];
					    empFname = TempList[8];
					    empLname = TempList[10];
					    empGender = TempList[11];
					    empMaritalStatus = TempList[12];
					    empEmailId = TempList[14];
					    empAddress1c = TempList[19];
					    empCityc = TempList[23];
					    empStatec = TempList[25];
					    empCountryc = TempList[27];
					    empPostalCodec = TempList[29];
					    empCompany = TempList[30];
					    empJobTitle = TempList[32];
					    empDepartment = TempList[33];
					    empJobLocation = TempList[34];
					    empPayGroup = TempList[35];
					    empPayratePerhour = TempList[36];
					    empPayPeriod = TempList[37];
					    
					 //empSSNNo = String.valueOf(empSSNNo).substring(5);
				    }
		        }
			}
		
		
			 
		
		double netAmount = 0.0;
		double social_security_amount = 0.0;
		double medicare_amount = 0.0;
		double total_ot_amount = 0.0;
		int total_ot_hours = 0;
		double total_ot_ytd_amount = 0.0;
		String taxyear = null;
		String state = null;
		String chequeNo = null;
		String chequeDate = null;
		String NofExemptions = null;
		String age_exemptions = null;
		String additional_withholding = null;
		String fWHITMarital = null;
		String fWHITNoFAllowances = null;
		String PayPeriod = null;
		String PayRateType = null;
		String overtime = null;
		String Hourly_payrate =null;
		String total_hours = null;
		String YTD_Hourly = null;
		String total_gross_pay_hourly = null;
		//String Monthly_payrate = null;
		//String YTD_Monthly = null;
		//String total_gross_pay_monthly = null;
		
		String federal_it_rate = null;
		String federal_it_annual = null;
		String federal_it_ytd = null;
		String federal_it_cp = null;
		String social_rate = null;
		String social_annual = null;
		String social_ytd = null;
		String social_cp = null;
		String Medicare_rate = null;
		String Medicare_annual = null;
		String Medicare_ytd = null;
		String Medicare_cp = null;
		String State_IT_rate = null;
		String State_IT_annual = null;
		String State_IT_ytd = null;
		String State_IT_cp = null;
		String After_Tax_Income = null;
		String After_Tax_Income_YTD = null;
		String After_Tax_Income_CP = null;
		String After_Tax_deduction = null;
		String After_Tax_deduction_YTD = null;
		String After_Tax_deduction_CP = null;
		
		String totalGross = null;
		String totalFinalGross = null;
		String totalFinalHours = null;
		String totalYTDGross = null;
		String totalFinalYTDGross = null;
		//String ot_row_count = null;
		double StateWHITAmount = 0;
		int personalAllowances = 0;
		int dependentAllowances = 0;
		String filingStatus = null;
		int totalAllowances = 0;
		double additionalStateWithHolding = 0;
		int additionalAllowances = 0;
		String texarnaResident = null;
		
		String withHoldingCode = null;
		int basicAllowances = 0;
		int personalDeduction = 0;
		int dependentDeduction = 0;
		int WHExemptions = 0;
		String RateCode = null;
		
		String SpouseBlind = null;
		String PersonalBlind = null;
		String HouseHold = null;
		String Student = null;
		
		int extendedAllowances = 0;
		int regularAllowances = 0;
		double exemptionAmount = 0;
		double WithholdingRate = 0;
		double DistrictRate = 0;
		int DistrictNo = 0;
		double schoolDistITAmount = 0.0;
		
		//String state_wh_info = null;
		String ot_payrateArray = "";
		String ot_payrate_typeArray = "";
		String ot_total_hours_workedArray = "";
		String ot_pay_ytdArray = "";
		String ot_pay_cpArray = "";
		boolean empPayrollInsertStatus = false;
		
		taxyear = tax_year;
		state = tax_state;
		chequeNo = cheque_no;
		chequeDate = cheque_date;

		netAmount = Double.parseDouble(total_netpay);
		fWHITMarital = fed_marital_status;
		fWHITNoFAllowances = fed_nof_allowance;
		PayPeriod = tax_payperiod;
		PayRateType = tax_payrate_type;
		overtime = if_overtime;

		Hourly_payrate = gen_payrate;
		total_hours = gen_total_hours_worked;
		YTD_Hourly = gen_pay_ytd;
		total_gross_pay_hourly = gen_pay_cp;

		//Monthly_payrate = gen_payrate;
		//YTD_Monthly = gen_pay_ytd;
		//total_gross_pay_monthly = gen_pay_cp;
		System.out.println("ot_row_count-------------->"+ot_row_count);
		int ot_row_count_int = 0;
		String[] overtimerate = null;
		String[] overtimepaytype = null;
		String[] Hour_ot = null;
		String[] ytd_ot = null;
		String[] cp_ot = null;
		
		if(if_overtime != null && if_overtime != "null" && !(if_overtime.equalsIgnoreCase("null"))){
		ot_row_count_int = Integer.parseInt(ot_row_count);
		
		overtimerate = new String[ot_row_count_int+1];
		overtimepaytype = new String[ot_row_count_int+1];
		Hour_ot = new String[ot_row_count_int+1];
		ytd_ot = new String[ot_row_count_int+1];
		cp_ot = new String[ot_row_count_int+1];

		overtimerate = ot_payrate.split(",");
		overtimepaytype = ot_payrate_type.split(",");
		Hour_ot = ot_total_hours_worked.split(",");
		ytd_ot = ot_pay_ytd.split(",");
		cp_ot = ot_pay_cp.split(",");

		for(int i=0;i<=ot_row_count_int;i++){

		overtimerate[i] = overtimerate[i+1];
		overtimepaytype[i] = overtimepaytype[i+1];
		Hour_ot[i] = Hour_ot[i+1];
		ytd_ot[i] = ytd_ot[i+1];
		cp_ot[i] = cp_ot[i+1];

		total_ot_amount = (total_ot_amount + Double.parseDouble(cp_ot[i]));
		total_ot_hours = (total_ot_hours + Integer.parseInt(Hour_ot[i]));
		total_ot_ytd_amount = (total_ot_ytd_amount + Double.parseDouble(ytd_ot[i]));

		System.out.println("overtimerate["+i+"]---------->"+overtimerate[i+1]);
		System.out.println("overtimepaytype["+i+"]---------->"+overtimepaytype[i+1]);
		System.out.println("Hour_ot["+i+"]---------->"+Hour_ot[i+1]);
		System.out.println("ytd_ot["+i+"]---------->"+ytd_ot[i+1]);
		System.out.println("cp_ot["+i+"]---------->"+cp_ot[i+1]);

		System.out.println("total_ot_amount-------------->"+total_ot_amount);
		System.out.println("total_ot_ytd_amount----------->"+total_ot_ytd_amount);

		}
		}
		
		federal_it_rate = "";
		federal_it_annual = "";
		federal_it_ytd = fed_pay_ytd;
		federal_it_cp = fed_pay_cp;

		social_rate = ss_payrate;
		social_annual = ss_annual_max;
		social_ytd = ss_pay_ytd;
		social_cp = ss_pay_cp;

		Medicare_rate = mc_payrate;
		Medicare_annual = "";
		Medicare_ytd = mc_pay_ytd;
		Medicare_cp = mc_pay_cp;

		State_IT_rate = "";
		State_IT_annual = "";
		State_IT_ytd = state_pay_ytd;
		State_IT_cp = state_pay_cp;

		After_Tax_Income = after_tax_income_pay;
		After_Tax_Income_YTD = after_tax_income_ytd;
		After_Tax_Income_CP = after_tax_income_cp;

		After_Tax_deduction = after_tax_deduction_pay;
		After_Tax_deduction_YTD = after_tax_deduction_ytd;
		After_Tax_deduction_CP = after_tax_deduction_cp;
		
		
		
		/*if(PayRateType != null && PayRateType.equalsIgnoreCase("Hourly")){
			totalGross = total_gross_pay_hourly;
			totalYTDGross = YTD_Hourly;
		}else if(PayRateType != null && PayRateType.equalsIgnoreCase("Monthly")){
			totalGross = total_gross_pay_monthly;
			totalYTDGross = YTD_Monthly;
		}else{
			throw new Exception("Problem with Total Gross Amount!");
		}*/
		
		if(PayRateType != null){
			totalGross = total_gross_pay_hourly;
			totalYTDGross = YTD_Hourly;
		}else{
			throw new Exception("Problem with Total Gross Amount!");
		}
		
		totalFinalGross = String.valueOf((Double.parseDouble(totalGross) + total_ot_amount));
		totalFinalGross = String.valueOf(Math.round(Double.parseDouble(totalFinalGross) * 100D) / 100D);
		totalYTDGross = String.valueOf(Math.round(Double.parseDouble(totalYTDGross) * 100D) / 100D);
		totalFinalYTDGross = String.valueOf((Double.parseDouble(totalYTDGross) + total_ot_ytd_amount));
		totalFinalYTDGross = String.valueOf(Math.round(Double.parseDouble(totalFinalYTDGross) * 100D) / 100D);
		//double federalWHITAmount = (double)payRollBean.calculateFederalITWithholdAmount(fWHITMarital,Integer.parseInt(fWHITNoFAllowances),Double.parseDouble(totalGross),PayPeriod);
		
		totalFinalHours = String.valueOf(Integer.parseInt(total_hours) + total_ot_hours);
		
		if(state != null && state.equalsIgnoreCase("Virginia")){

			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("VI_NofExemptions---------->"+stateWitholdingInfo[0]);
			System.out.println("VI_age_exemptions---------->"+stateWitholdingInfo[1]);
			System.out.println("VI_additional_withholding---------->"+stateWitholdingInfo[2]);
			
			NofExemptions = stateWitholdingInfo[0];
			age_exemptions = stateWitholdingInfo[1];
			additional_withholding = stateWitholdingInfo[2];
			//state_wh_info = NofExemptions+","+age_exemptions+","+additional_withholding;
			//StateWHITAmount = (double)payRollBean.calculateVirginiaStateWithHold(Double.parseDouble(totalGross), PayPeriod,
			//Double.parseDouble(additional_withholding), Integer.parseInt(age_exemptions), Integer.parseInt(NofExemptions));
		}else if(state != null && state.equalsIgnoreCase("Georgia")){

			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("personal_allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("dependent_allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			System.out.println("total_allowances---------->"+stateWitholdingInfo[3]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[4]);
			System.out.println("additional_allowances---------->"+stateWitholdingInfo[5]);
			
			personalAllowances = Integer.parseInt(stateWitholdingInfo[0]);
			dependentAllowances = Integer.parseInt(stateWitholdingInfo[1]);
			filingStatus = stateWitholdingInfo[2];
			totalAllowances = Integer.parseInt(stateWitholdingInfo[3]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[4]);
			additionalAllowances = Integer.parseInt(stateWitholdingInfo[5]);
			//state_wh_info = personalAllowances+","+dependentAllowances+","+filingStatus+","+totalAllowances+","+
			//additionalStateWithHolding+","+additionalAllowances;
			//StateWHITAmount = (double)payRollBean.calculateGeorgiaWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, additionalAllowances, personalAllowances, dependentAllowances, filingStatus);
		}else if(state != null && state.equalsIgnoreCase("Arkansas")){

			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("total_allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
			System.out.println("texarna_resident---------->"+stateWitholdingInfo[3]);
			
			filingStatus = stateWitholdingInfo[0];
			totalAllowances = Integer.parseInt(stateWitholdingInfo[1]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[2]);
			texarnaResident = stateWitholdingInfo[3];
			//state_wh_info = filingStatus+","+totalAllowances+","+additionalStateWithHolding+","+texarnaResident;
			//StateWHITAmount = (double)payRollBean.calculateArkansasWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, texarnaResident);
		}else if(state != null && state.equalsIgnoreCase("Idaho")){

			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("total_allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
			
			filingStatus = stateWitholdingInfo[0];
			totalAllowances = Integer.parseInt(stateWitholdingInfo[1]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[2]);
			//state_wh_info = filingStatus+","+totalAllowances+","+additionalStateWithHolding;
			//StateWHITAmount = (double)payRollBean.calculateIdahoWithHold(Double.parseDouble(totalGross), PayPeriod, totalAllowances, additionalStateWithHolding, filingStatus);
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Alabama")){
			
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("dependentAllowances---------->"+stateWitholdingInfo[1]);
			System.out.println("allowances---------->"+stateWitholdingInfo[2]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[3]);
						
			filingStatus = stateWitholdingInfo[0];
			dependentAllowances = Integer.parseInt(stateWitholdingInfo[1]);
			totalAllowances = Integer.parseInt(stateWitholdingInfo[2]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[3]);
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Connecticut")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("withHoldingCode---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
						
			withHoldingCode = stateWitholdingInfo[0];
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[1]);

		}else if(tax_state != null && tax_state.equalsIgnoreCase("Illinois")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("basicAllowances---------->"+stateWitholdingInfo[2]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[3]);
						
			filingStatus = stateWitholdingInfo[0];
			additionalAllowances = Integer.parseInt(stateWitholdingInfo[1]);
			basicAllowances = Integer.parseInt(stateWitholdingInfo[2]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[3]);
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Indiana")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("personalDeduction---------->"+stateWitholdingInfo[0]);
			System.out.println("dependentDeduction---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
						
			personalDeduction = Integer.parseInt(stateWitholdingInfo[0]);
			dependentDeduction = Integer.parseInt(stateWitholdingInfo[1]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[2]);

		}else if(tax_state != null && tax_state.equalsIgnoreCase("Kentucky")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("total_allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
						
			totalAllowances = Integer.parseInt(stateWitholdingInfo[0]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[1]);

		}else if(tax_state != null && tax_state.equalsIgnoreCase("Maine")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("total_allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
						
			filingStatus = stateWitholdingInfo[0];
			totalAllowances = Integer.parseInt(stateWitholdingInfo[1]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[2]);

		}else if(tax_state != null && tax_state.equalsIgnoreCase("Minnesota")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("total_allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
						
			filingStatus = stateWitholdingInfo[0];
			totalAllowances = Integer.parseInt(stateWitholdingInfo[1]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[2]);
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Wisconsin")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("WHExemptions---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
						
			filingStatus = stateWitholdingInfo[0];
			WHExemptions = Integer.parseInt(stateWitholdingInfo[1]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[2]);
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Rhode Island")){
			String []stateWitholdingInfo = state_wh_info.split(",");
		
			System.out.println("total_allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
				
			totalAllowances = Integer.parseInt(stateWitholdingInfo[0]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[1]);
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Vermont")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("total_allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[2]);
						
			filingStatus = stateWitholdingInfo[0];
			totalAllowances = Integer.parseInt(stateWitholdingInfo[1]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[2]);
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("New Jersey")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("filing_status---------->"+stateWitholdingInfo[0]);
			System.out.println("RateCode---------->"+stateWitholdingInfo[1]);
			System.out.println("total_allowances---------->"+stateWitholdingInfo[2]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[3]);
						
			filingStatus = stateWitholdingInfo[0];
			RateCode = stateWitholdingInfo[1];
			totalAllowances = Integer.parseInt(stateWitholdingInfo[2]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[3]);
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("District of Columbia")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("total_allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			
			totalAllowances = Integer.parseInt(stateWitholdingInfo[0]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[1]);
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Nebraska")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			totalAllowances = Integer.parseInt(stateWitholdingInfo[0]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[1]);
			filingStatus = stateWitholdingInfo[2];
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Montana")){
		
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			
			totalAllowances = Integer.parseInt(stateWitholdingInfo[0]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[1]);
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("New Mexico")){
		
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			totalAllowances = Integer.parseInt(stateWitholdingInfo[0]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[1]);
			filingStatus = stateWitholdingInfo[2];
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("West Virginia")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			totalAllowances = Integer.parseInt(stateWitholdingInfo[0]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[1]);
			filingStatus = stateWitholdingInfo[2];
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Hawaii")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			totalAllowances = Integer.parseInt(stateWitholdingInfo[0]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[1]);
			filingStatus = stateWitholdingInfo[2];
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Louisiana")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[0]);
			System.out.println("personal_allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("dependent_allowances---------->"+stateWitholdingInfo[2]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[3]);
			
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[0]);
			personalAllowances = Integer.parseInt(stateWitholdingInfo[1]);
			dependentAllowances = Integer.parseInt(stateWitholdingInfo[2]);
			filingStatus = stateWitholdingInfo[3];
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Massachusetts")){
			
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[0]);
			System.out.println("allowances---------->"+stateWitholdingInfo[1]);
			System.out.println("spouse_blind---------->"+stateWitholdingInfo[2]);
			System.out.println("personal_blind---------->"+stateWitholdingInfo[3]);
			System.out.println("house_hold---------->"+stateWitholdingInfo[4]);
			System.out.println("student---------->"+stateWitholdingInfo[5]);
			
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[0]);
			totalAllowances = Integer.parseInt(stateWitholdingInfo[1]);
			SpouseBlind = stateWitholdingInfo[2];
			PersonalBlind = stateWitholdingInfo[3];
			HouseHold = stateWitholdingInfo[4];
			Student = stateWitholdingInfo[5];
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Oregon")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			totalAllowances = Integer.parseInt(stateWitholdingInfo[0]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[1]);
			filingStatus = stateWitholdingInfo[2];
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("South Carolina")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			
			totalAllowances = Integer.parseInt(stateWitholdingInfo[0]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[1]);
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("North Carolina")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			totalAllowances = Integer.parseInt(stateWitholdingInfo[0]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[1]);
			filingStatus = stateWitholdingInfo[2];
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("North Dakota")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			totalAllowances = Integer.parseInt(stateWitholdingInfo[0]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[1]);
			filingStatus = stateWitholdingInfo[2];
			
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Utah")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			totalAllowances = Integer.parseInt(stateWitholdingInfo[0]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[1]);
			filingStatus = stateWitholdingInfo[2];
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Delaware")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			totalAllowances = Integer.parseInt(stateWitholdingInfo[0]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[1]);
			filingStatus = stateWitholdingInfo[2];
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Colorado")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			totalAllowances = Integer.parseInt(stateWitholdingInfo[0]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[1]);
			filingStatus = stateWitholdingInfo[2];
		}else if(tax_state != null && tax_state.equalsIgnoreCase("California")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[0]);
			System.out.println("extendedAllowances---------->"+stateWitholdingInfo[1]);
			System.out.println("regularAllowances---------->"+stateWitholdingInfo[2]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[3]);
			
		
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[0]);
			extendedAllowances = Integer.parseInt(stateWitholdingInfo[1]);
			regularAllowances = Integer.parseInt(stateWitholdingInfo[1]);
			filingStatus = stateWitholdingInfo[2];
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Iowa")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			totalAllowances = Integer.parseInt(stateWitholdingInfo[0]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[1]);
			filingStatus = stateWitholdingInfo[2];
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Kansas")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			totalAllowances = Integer.parseInt(stateWitholdingInfo[0]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[1]);
			filingStatus = stateWitholdingInfo[2];
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Mississippi")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[0]);
			System.out.println("exemptionAmount---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[0]);
			exemptionAmount = Double.parseDouble(stateWitholdingInfo[1]);
			filingStatus = stateWitholdingInfo[2];
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Michigan")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[0]);
			System.out.println("allowances---------->"+stateWitholdingInfo[1]);
			
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[0]);
			totalAllowances = Integer.parseInt(stateWitholdingInfo[1]);
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Missouri")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			totalAllowances = Integer.parseInt(stateWitholdingInfo[0]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[1]);
			filingStatus = stateWitholdingInfo[2];
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Pennsylvania")){
						
			//StateWHITAmount = (double)payRollBean.calculatePennsylvaniaWithHeld(Double.parseDouble(totalGross), PayPeriod);
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Arizona")){
			
			System.out.println("withholdingRate---------->"+state_wh_info);
			
			WithholdingRate = Double.parseDouble(state_wh_info);
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Oklahoma")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("filing_status---------->"+stateWitholdingInfo[2]);
			
			totalAllowances = Integer.parseInt(stateWitholdingInfo[0]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[1]);
			filingStatus = stateWitholdingInfo[2];
		}else if(tax_state != null && tax_state.equalsIgnoreCase("Ohio")){
			String []stateWitholdingInfo = state_wh_info.split(",");
			System.out.println("allowances---------->"+stateWitholdingInfo[0]);
			System.out.println("additional_state_withholding---------->"+stateWitholdingInfo[1]);
			System.out.println("DistrictRate---------->"+stateWitholdingInfo[2]);
			System.out.println("DistrictNo---------->"+stateWitholdingInfo[3]);
			
			totalAllowances = Integer.parseInt(stateWitholdingInfo[0]);
			additionalStateWithHolding = Integer.parseInt(stateWitholdingInfo[1]);
			DistrictRate = Double.parseDouble(stateWitholdingInfo[2]);
			DistrictNo = Integer.parseInt(stateWitholdingInfo[3]);
			request.setAttribute("schoolDistITAmount",String.valueOf(schoolDistITAmount));
		}

		/*
		empPayrollInsertStatus = db.insertEmployeePayrollDetails(emp_unique_id,empId,empSSNNo,empFname,empLname,taxyear,"US",
				state,fWHITMarital,fWHITNoFAllowances,state_wh_info,PayPeriod,tax_payduration,PayRateType,
				overtime,totalGross,PayRateType,total_hours,totalYTDGross,totalGross,ot_row_count,ot_payrateArray,
				ot_payrate_typeArray,ot_total_hours_workedArray,ot_pay_ytdArray,ot_pay_cpArray,federal_it_ytd,
				federal_it_cp,social_rate,social_annual,social_ytd,social_cp,Medicare_rate,Medicare_ytd,
				Medicare_cp,State_IT_ytd,State_IT_cp,After_Tax_Income,After_Tax_Income_YTD,After_Tax_Income_CP,
				After_Tax_deduction,After_Tax_deduction_YTD,After_Tax_deduction_CP,String.valueOf(netAmount));
		
		System.out.println("db.insertEmployeePayrollDetails status-------------->"+empPayrollInsertStatus);
		*/
		System.out.println("Hourly_payrate-------------------->"+Hourly_payrate);
		System.out.println("total_hours-------------------->"+total_hours);
		System.out.println("YTD_Hourly-------------------->"+YTD_Hourly);
		//System.out.println("Monthly_payrate-------------------->"+Monthly_payrate);
		//System.out.println("YTD_Monthly-------------------->"+YTD_Monthly);
				
		System.out.println("ot_row_count-------------------->"+ot_row_count);
		
		
		System.out.println("taxyear------------------->"+taxyear);
		System.out.println("state------------------->"+state);
		System.out.println("chequeNo------------------->"+chequeNo);
		System.out.println("chequeDate------------------->"+chequeDate);
		System.out.println("VI_NofExemptions------------------->"+NofExemptions);
		System.out.println("VI_age_exemptions------------------->"+age_exemptions);
		System.out.println("VI_additional_withholding------------------->"+additional_withholding);
		System.out.println("fWHITMarital------------------->"+fWHITMarital);
		System.out.println("fWHITNoFAllowances------------------->"+fWHITNoFAllowances);
		System.out.println("PayPeriod------------------->"+PayPeriod);
		System.out.println("tax_payduration_from--------------------->"+tax_payduration_from);
		System.out.println("tax_payduration_to--------------------->"+tax_payduration_to);
		
		
		System.out.println("PayRateType------------------->"+PayRateType);
		System.out.println("overtime------------------->"+overtime);
		System.out.println("total_gross_pay_hourly------------------->"+total_gross_pay_hourly);
		//System.out.println("gross_pay_monthly------------------->"+total_gross_pay_monthly);
		System.out.println("federal_it_cp------------------->"+federal_it_cp);
		System.out.println("social_cp------------------->"+social_cp);
		System.out.println("Medicare_cp------------------->"+Medicare_cp);
		System.out.println("State_IT_cp------------------->"+State_IT_cp);
		System.out.println("After_Tax_Income_CP------------------->"+After_Tax_Income_CP);
		System.out.println("After_Tax_deduction_CP------------------->"+After_Tax_deduction_CP);
		
		System.out.println("federal_it_rate-------------------->"+federal_it_rate);
		System.out.println("federal_it_annual-------------------->"+federal_it_annual);
		System.out.println("federal_it_ytd-------------------->"+federal_it_ytd);
		System.out.println("social_rate-------------------->"+social_rate);
		System.out.println("social_annual-------------------->"+social_annual);
		System.out.println("social_ytd-------------------->"+social_ytd);
		System.out.println("Medicare_rate-------------------->"+Medicare_rate);
		System.out.println("Medicare_annual-------------------->"+Medicare_annual);
		System.out.println("Medicare_ytd-------------------->"+Medicare_ytd);
		System.out.println("State_IT_rate-------------------->"+State_IT_rate);
		System.out.println("State_IT_annual-------------------->"+State_IT_annual);
		System.out.println("State_IT_ytd-------------------->"+State_IT_ytd);
		System.out.println("After_Tax_Income-------------------->"+After_Tax_Income);
		System.out.println("After_Tax_Income_YTD-------------------->"+After_Tax_Income_YTD);
		System.out.println("After_Tax_deduction-------------------->"+After_Tax_deduction);
		System.out.println("After_Tax_deduction_YTD-------------------->"+After_Tax_deduction_YTD);
		
		
		
		
		//social_security_amount = (Double.parseDouble(totalGross) * Double.parseDouble(social_rate));
		//medicare_amount = (Double.parseDouble(totalGross) * Double.parseDouble(Medicare_rate));
		
		//social_cp = String.valueOf(social_security_amount);
		//Medicare_cp = String.valueOf(medicare_amount);
		
		String totalCpTaxes = null;
		totalCpTaxes = String.valueOf(Double.parseDouble(federal_it_cp) + Double.parseDouble(State_IT_cp)
				+ Double.parseDouble(social_cp) + Double.parseDouble(Medicare_cp));
		totalCpTaxes = String.valueOf(Math.round(Double.parseDouble(totalCpTaxes) * 100D) / 100D);
		request.setAttribute("netAmount", String.valueOf(netAmount));
		String totalYTDTaxes = null;
		totalYTDTaxes = String.valueOf(Double.parseDouble(federal_it_ytd) + Double.parseDouble(State_IT_ytd)
				+ Double.parseDouble(social_ytd) + Double.parseDouble(Medicare_ytd));
		totalYTDTaxes = String.valueOf(Math.round(Double.parseDouble(totalYTDTaxes) * 100D) / 100D);
		
		System.out.println("Total federalWHITAmount----------------------->"+federal_it_cp);
		System.out.println("Total StateWHITAmount----------------------->"+State_IT_cp);
		System.out.println("Social security amount----------------------->"+social_cp);
		System.out.println("Medicare amount----------------------->"+Medicare_cp);
		
		System.out.println("Total Current Pay(CP) Taxes----------------->"+totalCpTaxes);
		System.out.println("Total Year to Pay(YTD) Taxes----------------->"+totalYTDTaxes);
		
		try{
		       OutputStream file = new FileOutputStream(new File("D:\\EmployeePaystub.pdf"));
		          Document document = new Document();
		          
		          PdfWriter writer = PdfWriter.getInstance(document, file);
			        document.open();
			        Font subtitleFont = FontFactory.getFont("Calibri(Body)", 12, BaseColor.BLACK);
			        Font Bold = FontFactory.getFont("Calibri(Body)", 12, Font.BOLD);
			      
			        final DefaultResourceLoader loader = new DefaultResourceLoader();               
			        //LOGGER.info(loader.getResource("classpath:META-INF/resources/img/copyright.png").exists());             
			        Resource resource = loader.getResource("classpath:images/db.jpg"); 
			        System.out.println("resource.getURL()-----------------"+resource.getURL());
			        // Image image = Image.getInstance ("src/images/db.jpg");
			        Image image = Image.getInstance(resource.getURL());
			        
				    image.scaleAbsolute(60f, 60f);//image width,height
			         
				   /*------------------End of image inserting-----------------------------*/
				   
				    PdfPTable table=new PdfPTable(4);//No.of.columns
				    table.setSpacingBefore(10);
				    table.setWidthPercentage(100);
				    table.setWidths(new float[]{ 1f, 2f, 1f, 2f});
				   
				    PdfPCell cell = new PdfPCell (new Paragraph ( "General",Bold));
			   
				    cell.setFixedHeight(22f);
			   
				    cell.setColspan (6);
			   
				    cell.setBorderColor(BaseColor.WHITE);
				    cell.setHorizontalAlignment (Element.ALIGN_LEFT);
				   
				    cell.setBackgroundColor (new BaseColor (202,207,210));					               
				   
				    table.addCell(cell);
				    table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				    //first row
				    PdfPCell cell01 = new PdfPCell();
				
				    table.addCell("Employee Id:");
				    table.addCell(empId);
			   	
			   		table.addCell("Pay Day:");
			   		table.addCell(chequeDate);
			   
			   		table.addCell("SSN:");
			   		table.addCell("***-**-"+String.valueOf(empSSNNo).substring(5));
			  
			   		table.addCell("Pay period:");
			   		table.addCell(tax_payduration_from +" - "+tax_payduration_to);
			   
			 
			   		PdfPCell cell02 = new PdfPCell();
			   
			  
				   table.addCell("Department:");
				   table.addCell(empDepartment);
				   table.addCell("Pay Cycle:");
				   table.addCell(PayPeriod);
				   
				   table.addCell("Location:");
				   table.addCell(empJobLocation);
				   table.addCell("Pay Rate($):");
				   table.addCell(Hourly_payrate);

			 
			   
				    PdfPTable table2=new PdfPTable(5);//No.of.columns
				    table2.setSpacingBefore(10);
				   
				    table2.setWidthPercentage(100);
				    
			   PdfPCell cell2 = new PdfPCell (new Paragraph ( "Earnings",Bold));
			   
			   cell2.setFixedHeight(22f);
			  
			   
			   cell2.setBorderColor(BaseColor.WHITE);
			   cell2.setHorizontalAlignment (Element.ALIGN_LEFT);
				 cell2.setColspan(6);  
			   cell2.setBackgroundColor (new BaseColor (202,207,210));					               
				   
			   table2.addCell(cell2);
			   table2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				   
			   PdfPTable table3 = new PdfPTable(5);
			   table3.setWidthPercentage(100);
			   table3.setSpacingBefore(10f); //Space before table
			   table3.setSpacingAfter(10f);
			   table3.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			   table3.setWidths(new float[]{ 2f, 2f, 2f, 2f, 2f});
			  
			   PdfPCell cell3;
			   PdfPCell cell4;
			   PdfPCell cell5;
			   PdfPCell cell6;
			   PdfPCell cell7;
			   PdfPCell cell8;
			   
			   cell3 = new PdfPCell(new Phrase("Description",Bold));
			   cell3.setBorderColor(BaseColor.WHITE);
			   
			   cell4 = new PdfPCell(new Phrase("Pay Rate Type",Bold));
			   cell4.setBorderColor(BaseColor.WHITE);
			   
			   cell5 = new PdfPCell(new Phrase("Hours",Bold));
			   cell5.setBorderColor(BaseColor.WHITE);
			   
			   cell6 = new PdfPCell(new Phrase("Current Pay($)",Bold));
			   cell6.setBorderColor(BaseColor.WHITE);
			   
			   /*cell7 = new PdfPCell(new Phrase("",Bold));
			   cell7.setBorderColor(BaseColor.WHITE);*/
			   
			   cell8 = new PdfPCell(new Phrase("Year-To-Date($)",Bold));
			   cell8.setBorderColor(BaseColor.WHITE);
			   table3.addCell(cell3);
			   table3.addCell(cell4);
			   table3.addCell(cell5);
			   table3.addCell(cell6);
			   //table3.addCell(cell7);
			   table3.addCell(cell8);
			   
			   
			   
			   
			   table3.addCell("Salary");
			   table3.addCell(PayRateType);
			   table3.addCell(total_hours);
			   table3.addCell(totalGross);
			   //table3.addCell("");3760.00 
			   table3.addCell(totalYTDGross);
			   
			   if(overtime != null && overtime != "null" && !(overtime.equalsIgnoreCase("null"))){
		             for(int i=0;i<=ot_row_count_int;i++){
			     		overtimerate[i] = request.getParameter("overtimerate"+i);
			     		overtimepaytype[i] = request.getParameter("overtimepaytype"+i);
			     		Hour_ot[i] = request.getParameter("Hour_ot"+i);
			     		ytd_ot[i] = request.getParameter("ytd_ot"+i);
			     		
			     		cp_ot[i] = request.getParameter("cp_ot"+i);
			     		ytd_ot[i] = String.valueOf(Math.round((Double.parseDouble(cp_ot[i]) + Double.parseDouble(ytd_ot[i]))* 100D) / 100D);
			     	
			     		table3.addCell("Overtime"+i);
			     		table3.addCell(overtimepaytype[i]);
			     		table3.addCell(Hour_ot[i]);
			     		table3.addCell(cp_ot[i]);
			             //table3.addCell("");
			     		table3.addCell(ytd_ot[i]);
		             }
		             }
			  
			   	PdfPCell cell03 = new PdfPCell(new Phrase(""));
				    cell03.setColspan(6);
				    cell03.setBorder(0);
				    cell03.setFixedHeight(20f);
				    //cell03.setSpaceCharRatio(20f);
				    table3.addCell(cell03);
			   
				    PdfPCell cell04 = new PdfPCell(new Phrase("Total Earnings",Bold));
				    cell04.setBorder(0);
				    
			   table3.addCell(cell04);
			   table3.addCell("");
			   table3.addCell(totalFinalHours);
			   table3.addCell(totalFinalGross);
			   //table3.addCell("");
			   table3.addCell(totalFinalYTDGross);
			   /*---------------------End of Paycheck details-----------------------------*/
			   
			   PdfPTable table4=new PdfPTable(3);//No.of.columns
			   table4.setSpacingBefore(10);
			   table4.setWidthPercentage(100);
			   
				    
			   PdfPCell cell9 = new PdfPCell (new Paragraph ( "Taxes",Bold));
			   
			   
			   
			   cell9.setFixedHeight(22f);
			   cell9.setColspan(6);
			   cell9.setBorderColor(BaseColor.WHITE);
			   cell9.setHorizontalAlignment (Element.ALIGN_LEFT);
				   
			   cell9.setBackgroundColor (new BaseColor (202,207,210));					               
				   
			   table4.addCell(cell9);
			   table4.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				   
			  PdfPTable table5 = new PdfPTable(3);
			  
			   table5.setWidthPercentage(100);
			   table5.setSpacingBefore(10f); //Space before table
			   table5.setSpacingAfter(10f);
			   table5.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			   table5.setWidths(new float[]{ 6f, 2f, 2f});
			   
			   PdfPCell cell10;
			   PdfPCell cell11;
			   PdfPCell cell12;
			   
			   cell10 = new PdfPCell(new Phrase("Description",Bold));
			   //cell10.setColspan(2);
			   cell10.setBorderColor(BaseColor.WHITE);
			   
			   cell11 = new PdfPCell(new Phrase("This Period",Bold));
			   cell11.setBorderColor(BaseColor.WHITE);
			   
			   cell12 = new PdfPCell(new Phrase("YTD",Bold));
			   cell12.setBorderColor(BaseColor.WHITE);
			   table5.addCell(cell10);
			   table5.addCell(cell11);
			   table5.addCell(cell12);
			   
			   
			   table5.addCell("Federal Income Tax");
			   table5.addCell(federal_it_cp);
			   table5.addCell(federal_it_ytd);
			   
			   //third row
			   table5.addCell("Social security Tax");
			   table5.addCell(social_cp);
			   table5.addCell(social_ytd);
			   
			   table5.addCell("Medicare");
			   table5.addCell(Medicare_cp);
			   table5.addCell(Medicare_ytd); 
			   
			   table5.addCell("State income Tax");
			   table5.addCell(State_IT_cp);
			   table5.addCell(State_IT_ytd);
			   
			  
			   table5.addCell(cell03);
			   
			   PdfPCell cell05 = new PdfPCell(new Phrase("Total Taxes($)",Bold));
			   cell05.setBorder(0);
			   table5.addCell(cell05);
			   table5.addCell(totalCpTaxes);
			   table5.addCell(totalYTDTaxes);
			   
			 
			  
			   
			   /*---------------------End of Tax details-----------------------------*/
			   
			  
			   PdfPTable table6 = new PdfPTable(2); // 2 columns.
			   table6.setWidthPercentage(100); //Width 100%
			   table6.setSpacingBefore(10f); //Space before table
			   PdfPCell cell13 = new PdfPCell(new Paragraph("After-Tax Income",Bold));
			  
			   cell13.setBorderColor(BaseColor.WHITE);
			   cell13.setPaddingLeft(10);
			   cell13.setFixedHeight(22f);
			   cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
			   cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   cell13.setBackgroundColor (new BaseColor (202,207,210));	

			   PdfPCell cell14 = new PdfPCell(new Paragraph("After-Tax Deduction",Bold));
			   cell14.setBorderColor(BaseColor.WHITE);
			   cell14.setPaddingLeft(10);
			   cell14.setFixedHeight(22f);
			   cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
			   cell14.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   cell14.setBackgroundColor (new BaseColor (202,207,210));	
			   table6.addCell(cell13);
			   table6.addCell(cell14);
			   
			   PdfPTable table7 = new PdfPTable(6);
			   table7.setWidthPercentage(100);
			   table7.setSpacingBefore(10f); //Space before table
			   table7.setSpacingAfter(10f);
			   table7.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			   table7.setWidths(new int[]{ 3, 2, 2, 3, 2, 2});
			   PdfPCell cell15;
			   PdfPCell cell16;
			   PdfPCell cell17;
			   PdfPCell cell18;
			   PdfPCell cell19;
			   PdfPCell cell20;
			   PdfPCell cell21;
			   
			   
			   cell15 = new PdfPCell(new Phrase("Description",Bold));
			   cell15.setBorderColor(BaseColor.WHITE);
			   
			   cell16 = new PdfPCell(new Phrase("This Period",Bold));
			   cell16.setBorderColor(BaseColor.WHITE);
			   
			   cell17 = new PdfPCell(new Phrase("YTD",Bold));
			   cell17.setBorderColor(BaseColor.WHITE);
			   /*
			   cell18 = new PdfPCell(new Phrase(""));
			   cell18.setBorderColor(BaseColor.WHITE);*/
			   
			   cell19 = new PdfPCell(new Phrase("Description",Bold));
			   cell19.setBorderColor(BaseColor.WHITE);
			   
			   cell20 = new PdfPCell(new Phrase("This Period",Bold));
			   cell20.setBorderColor(BaseColor.WHITE);
			   
			   cell21 = new PdfPCell(new Phrase("YTD",Bold));
			   cell21.setBorderColor(BaseColor.WHITE);
			   
			   table7.addCell(cell15);
			   table7.addCell(cell16);
			   table7.addCell(cell17);
			   //table7.addCell(cell18);
			   table7.addCell(cell19);
			   table7.addCell(cell20);
			   table7.addCell(cell21);
			   
			//   PdfContentByte canvas = writer.getDirectContent();
			//   
//			    canvas.moveTo(298, 107);
//			    canvas.lineTo(298, 194);
//			    canvas.closePathStroke();
			   
			    table7.addCell("Income");//column 1
			    table7.addCell(After_Tax_Income_CP);//column 2
			    table7.addCell(After_Tax_Income_YTD);//column 3
			    //table7.addCell("");//column 4 
			    table7.addCell("Deduction");//column 5
			    table7.addCell(After_Tax_deduction_CP);//column 6
			    table7.addCell(After_Tax_deduction_YTD);//column 7
			   
			  
			  
			 
			   /*---------------------End of TAX DEDUCTION details-----------------------------*/
			   
			   PdfPTable table8=new PdfPTable(4);//No.of.columns
			   table8.setSpacingBefore(10);
				   
			   table8.setWidthPercentage(100);
			  
			   PdfPCell cell22 = new PdfPCell (new Paragraph ( "Netpay",Bold));
			   
			   cell22.setFixedHeight(22f);
			   cell22.setColspan (6);
			   
			   cell22.setBorderColor(BaseColor.WHITE);
			   cell22.setHorizontalAlignment (Element.ALIGN_LEFT);
				   
			   cell22.setBackgroundColor (new BaseColor (202,207,210));					               
				   
			   table8.addCell(cell22);
			   table8.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				    
			   PdfPTable table9 = new PdfPTable(4);
			   table9.setWidthPercentage(100);
			   table9.setSpacingBefore(10f); //Space before table
			   table9.setSpacingAfter(10f);
			   table9.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			  // table9.setWidths(new int[]{ 2, 2, 2, 2});
			   PdfPCell cell23;
			   PdfPCell cell24;
			   PdfPCell cell25;
			   PdfPCell cell26;
			   
			   cell23 = new PdfPCell(new Phrase("Payment Type",Bold));
			   cell23.setBorderColor(BaseColor.WHITE);
			   
			   cell24 = new PdfPCell(new Phrase("Cheque Number",Bold));
			   cell24.setBorderColor(BaseColor.WHITE);
			   
			   cell25 = new PdfPCell(new Phrase("Pay Day",Bold));
			   cell25.setBorderColor(BaseColor.WHITE);
			   
			   cell26 = new PdfPCell(new Phrase("Net Amount($)",Bold));
			   cell26.setBorderColor(BaseColor.WHITE);
			   
			   table9.addCell(cell23);
			   table9.addCell(cell24);
			   table9.addCell(cell25);
			   table9.addCell(cell26);
			  
			  
			   table9.addCell("Cheque");
			   table9.addCell(chequeNo);
			   table9.addCell(chequeDate);
			   table9.addCell(String.valueOf(netAmount));
			   
			  
			   /*---------------------End of net pay details-----------------------------*/
			   
			   PdfPTable table10 = new PdfPTable(1); // 3 columns.
			   table10.setWidthPercentage(100);//Width 100%
			   
			    //document.add(new Paragraph("Paystub Generated On - "+new Date().toString(),Bold));
			    document.add(new Paragraph(new Date().toString()));
			    
			    
			    
			  /* PdfPCell cell27 = new PdfPCell(new Paragraph("PAYSTUB",Bold));
			   cell27.setBorderColor(BaseColor.WHITE);
			   cell27.setPaddingLeft(10);
			   cell27.setHorizontalAlignment(Element.ALIGN_CENTER);
			   cell27.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   table10.addCell(cell27);
			   */
			   
			   
			   
			    
			 /*----------------------end of tax header details----------------------------*/
			    
			   PdfPTable table11 = new PdfPTable(2);
			  
			 // table11.setSpacingBefore(0);
			   table11.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			   table11.setWidths(new float[]{ 2f,2f});
			 
			   table11.setWidthPercentage(100);
			   
			  
			   PdfPCell cell30;
			   
			  // Image image = Image.getInstance ("src/images/db.jpg");
			   image.scaleAbsolute(60f, 60f);
			   cell30 = new PdfPCell();
			   cell30.setColspan(1);
			   cell30.setFixedHeight(50f);
			   //cell30.setHorizontalAlignment (Element.ALIGN_RIGHT);
			   cell30.setBorderColor(BaseColor.WHITE);
			   cell30.addElement(image);
			   image.setAlignment(Image.ALIGN_RIGHT);
			   
				   PdfPCell cell31;
				   //PdfPCell cell32;
				 
			
				  cell31 = new PdfPCell();
				  cell31.setPaddingLeft(12);
				  cell31.addElement(new Phrase(empFname+" "+empLname));
				  cell31.addElement(new Paragraph(new Phrase(empAddress1c)));
				  cell31.setFixedHeight(20f);
				  cell31.setBorder(0);
				   
				  table11.addCell(cell30);
				 
				  
				   table11.addCell(cell31 );
				   
				   
				  PdfPTable table12 = new PdfPTable(2);
				  
					 // table11.setSpacingBefore(0);
				  table12.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				  table12.setWidths(new float[]{ 2f,1f});
					 
				  table12.setWidthPercentage(100);
				  PdfPCell cell32 = new PdfPCell();
				  PdfPCell cell33 = new PdfPCell();
				  
				  cell33.addElement(new Paragraph(""));
				  cell33.setColspan(1);
				  cell33.setBorder(0);
				  cell33.setFixedHeight(50f);
				 
				  cell32.addElement(new Paragraph("NET PAY($):"+String.valueOf(netAmount),Bold));
				  cell33.setFixedHeight(20f);
				
				  // cell31.setRowspan(2);
				  cell32.setBorder(0);
				   
				 
				  table12.addCell(cell33);
				  table12.addCell(cell32);
				  
				  
			   
			   /*------------------------------End of header logo------------------------*/
			   
			   document.add(table11);
			   document.add(table12);
			   
			  
			 
			      document.add(table);
			      
			      //document.add(table1);
			      document.add(table2);
			     
			      document.add(table3);
			      
			      document.add(table4);
			      document.add(table5);
			      document.add(table6);
			      document.add(table7);
			      document.add(table8);
			     document.add(table9);
			    // document.add(table10);
			      
			      document.add(Chunk.NEWLINE);
			     
			  
			      document.close();
			        
			        final int BUFFER_SIZE = 4096;
			        // get absolute path of the application
				    ServletContext context = request.getSession().getServletContext();
				    // construct the complete absolute path of the file
				    String fullPath ="D:\\EmployeePaystub.pdf";  
			        File downloadFile = new File(fullPath);
			        FileInputStream inputStream = new FileInputStream(downloadFile);
			         
			        // get MIME type of the file
			        String mimeType = context.getMimeType(fullPath);
			        if (mimeType == null) {
			            // set to binary type if MIME mapping not found
			            mimeType = "application/octet-stream";
			        }
			        			 
			        // set content attributes for the response
			        response.setContentType(mimeType);
			        response.setContentLength((int) downloadFile.length());
			 
			        // set headers for the response
			        String headerKey = "Content-Disposition";
			        String headerValue = String.format("attachment; filename=\"%s\"",
			                downloadFile.getName());
			        response.setHeader(headerKey, headerValue);
			 
			        // get output stream of the response
					
			        OutputStream outStream = response.getOutputStream();
			 
			        byte[] buffer = new byte[BUFFER_SIZE];
			        int bytesRead = -1;
			 
			        // write bytes read from the input stream into the output stream
			        while ((bytesRead = inputStream.read(buffer)) != -1) {
			            outStream.write(buffer, 0, bytesRead);
			        }
			 
			        outStream.flush();
			        inputStream.close();
			        outStream.close();
			  }
		        catch (Exception e) {
		            e.printStackTrace();
		        }
		/*----------------------Pay Stun Generation Code End Here------------------------*/
        request.setAttribute("pageStatus","read");
		return new ModelAndView("requirements/frmListEmployeePayrollDetails");
	}
	
	
	@RequestMapping("/SearchAllEmployeePayrollDetails.html")
	public ModelAndView searchAllEmployeePayrollDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("PayrollAction.SearchAllEmployeePayrollDetails()");
		
		ArrayList employeePayrollList = null;
		
		String empId = null;
		String empName = null;
		String ssnNo = null;
		//String checkDate = null;
		String payDurationFrm = null;
		String payDurationTo = null;
		String payCycle = null;
		
		try{
			
			empId = request.getParameter("empid");
			empName = request.getParameter("empname");
			ssnNo = request.getParameter("ssnNo");
			//checkDate = request.getParameter("checkdate");
			payDurationFrm = request.getParameter("paydurationFrm");
			payDurationTo = request.getParameter("paydurationTo");
			payCycle = request.getParameter("paycycle");
			//empId, empName, checkDate, payDuration, payCycle
			Debug.print("empId --> "+empId+" empName ---> "+empName+
					" payDurationFrm ---> "+payDurationFrm+" payDurationTo---> "+
					payDurationTo+" payCycle---> "+payCycle+" ssnNo ---> "+ssnNo);
			
			employeePayrollList = new ArrayList();
			
			employeePayrollList = (ArrayList)db.searchAllEmployeePayrollDetails(empId, empName, ssnNo, payDurationFrm, payDurationTo, payCycle);
	        
			request.setAttribute("employeePayrollList",null);
	        request.setAttribute("employeePayrollList",employeePayrollList);
	        request.setAttribute("pageStatus","read");
	        
	        request.setAttribute("empId",empId);
	        request.setAttribute("empName",empName);
	        request.setAttribute("ssnNo",ssnNo);
	        //request.setAttribute("checkDate",checkDate);
	        request.setAttribute("payDurationFrm",payDurationFrm);
	        request.setAttribute("payDurationTo",payDurationTo);
	        request.setAttribute("payCycle",payCycle);
							
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ModelAndView("requirements/frmListEmployeePayrollDetails");
	}
	
	
	@RequestMapping("/ListEmployeePayrollDetails.html")
	public ModelAndView ListEmployeePayrollDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("PayrollAction.ListEmployeePayrollDetails()");
		ArrayList employeePayrollList = new ArrayList();
		String userId = (String)session.getAttribute("jobPostUserId");
		String roleId = (String)session.getAttribute("jobPostUserRoleId");
		
		String empUniqueId = db.getUniqueEmployeeId(userId);
		
		employeePayrollList = (ArrayList)db.getEmployeePayrollDetails(empUniqueId);
        request.setAttribute("employeePayrollList",null);
        request.setAttribute("employeePayrollList",employeePayrollList);
        request.setAttribute("pageStatus","read");
		return new ModelAndView("requirements/frmSearchEmployeePaystubListing");
	}
	
	@RequestMapping("/SearchEmployeePayrollDetails.html")
	public ModelAndView searchEmployeePayrollDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("PayrollAction.SearchEmployeePayrollDetails()");
		
		ArrayList employeePayrollList = null;
		
		String payDurationFrm = null;
		String payDurationTo = null;
		String payCycle = null;
		//String payment_status="Completed";
		
		try{
			
			String userId = (String)session.getAttribute("jobPostUserId");
			String roleId = (String)session.getAttribute("jobPostUserRoleId");
			
			String empUniqueId = db.getUniqueEmployeeId(userId);
			
			payDurationFrm = request.getParameter("paydurationFrm");
			payDurationTo = request.getParameter("paydurationTo");
			payCycle = request.getParameter("paycycle");
			Debug.print("payDurationFrm ---> "+payDurationFrm+" payDurationTo---> "+
					payDurationTo+" payCycle---> "+payCycle);
			
			employeePayrollList = new ArrayList();
			
			employeePayrollList = (ArrayList)db.searchEmployeePayrollDetails(payDurationFrm, payDurationTo, payCycle, empUniqueId);
	        
			request.setAttribute("employeePayrollList",null);
	        request.setAttribute("employeePayrollList",employeePayrollList);
	        request.setAttribute("pageStatus","read");
	        
	        request.setAttribute("payDurationFrm",payDurationFrm);
	        request.setAttribute("payDurationTo",payDurationTo);
	        request.setAttribute("payCycle",payCycle);
	       // request.setAttribute("payment_status",payment_status);
							
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ModelAndView("requirements/frmSearchEmployeePaystubListing");
	}
	
	@RequestMapping("/EditListEmployeeDetailsByUniqueId.html")
	public ModelAndView EditListEmployeeDetailsByUniqueId(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("RequirementsAction.UpdateListEmployeeDetailsByUniqueId()");
		String uniqueEmployeeId = null;
		uniqueEmployeeId = request.getParameter("uniqueId");
		
		if(uniqueEmployeeId != null){
	        ArrayList employeeDetails = new ArrayList();
	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(uniqueEmployeeId);
	        request.setAttribute("employeeDetails",null);
	        request.setAttribute("employeeDetails",employeeDetails);
	        request.setAttribute("pageStatus","update");
			return new ModelAndView("frmUpdateEmployeeDetails");
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/UpdateListEmployeeDetailsByUniqueId.html")
	public ModelAndView UpdateListEmployeeDetailsByUniqueId(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("RequirementsAction.UpdateListEmployeeDetailsByUniqueId()");
		    String uniqueEmployeeId = null;
		    String empPayGroup = null;
		    String empPayratePerhour = null;
		    String empPayPeriod = null;
		    
			String client_name = null;
			String client_address=null;
			String project_name=null;
			String client_manager=null;
			String manager_phone=null;
			String manager_email=null;
			String emp_role=null;
			String start_date=null;
			String end_date=null;
			String project_status=null;
		uniqueEmployeeId = request.getParameter("uniqueId");
		empPayGroup = request.getParameter("PayRateType");
		empPayratePerhour = request.getParameter("payrate");
		empPayPeriod = request.getParameter("PayPeriod");
		client_name = request.getParameter("clientName");
		client_address = request.getParameter("clientAddress");
		project_name = request.getParameter("projectName");
		client_manager = request.getParameter("clientManager");
		manager_phone = request.getParameter("managerPhone");
		manager_email = request.getParameter("managerEmail");
		
		start_date = request.getParameter("startDate");
		end_date = request.getParameter("endDate");
		project_status = request.getParameter("project_status");
		
		if(uniqueEmployeeId != null){
	        ArrayList employeeDetails = new ArrayList();
	        employeeDetails = (ArrayList)db.updateEmployeeDetailsByUniqueId(uniqueEmployeeId,empPayGroup,empPayratePerhour,empPayPeriod,client_name,
	        		client_address,project_name,client_manager,manager_phone,manager_email,start_date,end_date,project_status);
	        ArrayList employeeList = new ArrayList();
			employeeList = (ArrayList)db.getAllEmployeeDetails();
	        request.setAttribute("employeeDetails",null);
	        request.setAttribute("employeeDetails",employeeDetails);
	        request.setAttribute("pageStatus","update");
	        request.setAttribute("employeeList",employeeList);
			return new ModelAndView("frmEmployeeList");
			
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	
}

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
package com.db.immigration.action.DOS;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;

import com.db.GeneralDBAction;
import com.db.immigration.action.DOS.service.client.DOSClientStage1;
import com.db.immigration.action.DOS.service.client.DOSClientStage2;
import com.db.immigration.action.DOS.service.client.DOSClientStage3;
import com.db.immigration.action.DOS.service.client.DOSClientStage4;
import com.db.immigration.action.DOS.service.client.DOSClientStage5;
import com.db.immigration.action.DOS.service.client.DOSClientStage6;
import com.hlccommon.util.Debug;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;


@Controller

public class DepartmentStateAction {
	
	public static final String config_prop = "config.properties";
	public static final String LOG_FILE = "Log4j.properties";
	InputStream is;
	InputStream config_inputStream;
	
	@RequestMapping("/initDepartmentStateStage1.html")
	public ModelAndView initDepartmentStateStage1(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOSStageOneData = new ArrayList();
            DOSStageOneData = db.getDOSStageOneDetailsByUniqueEmpId(empUniqueId);
            
            if(DOSStageOneData != null && DOSStageOneData.size() > 0){
            
	            String unique_DOS_id = null;
	        	String emp_unique_id = null;
	        	String stage1CreateDate = null;
	 		    String stage1AcknowledgementDate = null;
	 		    String stage1SystemDate = null;
	 		    String stage1ApproveStatus = null;
	        	String stage1Data = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	            String[] stageOneDataArray = null;
	            String[] stageOneStatusArray = null;
	            if(DOSStageOneData!=null && DOSStageOneData.size()!=0){
	            	
					Iterator itr = DOSStageOneData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOS_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage1Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stageOneStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status};
					    
					    if(stage1Data != null && stage1Data != "" && !(stage1Data.equalsIgnoreCase(""))){
		        			String []tempStage1DataArray = stage1Data.split("-sep-");
		        			stageOneDataArray = new String[tempStage1DataArray.length - 4];
		        			stage1CreateDate = tempStage1DataArray[0];
		        			stage1AcknowledgementDate = tempStage1DataArray[1];
		        			stage1SystemDate = tempStage1DataArray[2];
		        			stage1ApproveStatus = tempStage1DataArray[3];
		        			for(int j=0;j<(tempStage1DataArray.length - 4);j++){
		        				stageOneDataArray[j] = tempStage1DataArray[4 + j];
		        				System.out.println("DepartmentStateAction-Stage1----->stageOneDataArray["+j+"]-------------->"+stageOneDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageOneStatusArray", stageOneStatusArray);
		        				return new ModelAndView("DOS/frmDepartmentStateStage1");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_DOS_id", unique_DOS_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage1CreateDate", stage1CreateDate);
	            request.setAttribute("stage1AcknowledgementDate", stage1AcknowledgementDate);
	            request.setAttribute("stage1SystemDate", stage1SystemDate);
	            request.setAttribute("stage1ApproveStatus", stage1ApproveStatus);
	            if(empUniqueId != null){
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageOneDataArray", stageOneDataArray);
	            request.setAttribute("stageOneStatusArray", stageOneStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOS/frmDepartmentStateStage1");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOS/frmDepartmentStateStage1");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDepartmentStateStage1.html")
	public ModelAndView doDepartmentStateStage1(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Properties config_property = new Properties();
		config_inputStream = getClass().getClassLoader().getResourceAsStream(config_prop);

		if (config_inputStream != null) {
			config_property.load(config_inputStream);
		} else {
			throw new FileNotFoundException("property file '" + config_prop + "' not found in the classpath");
		}
			
			HttpSession session=request.getSession(true); 

		 
    	   try {
    		   GeneralDBAction db=new GeneralDBAction();
    		   
    		   String oldVisaform = null;
    		   String oldAdditionalfile = "";
    		   String visaformCheck = null;
    		   String additionalfileCheck = null;
    		   
    		   String visaformFile = null;
    		   String additionalfileFile = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
    		   ArrayList <String>dosFileList = new ArrayList<String>();
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfState");
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
		                	if(parmName.equals("empUniqueId")){
		                		empUniqueId = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("empId")){
		                		empId = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("CreateDate")){
		                		createDate = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("AcknowledgementDate")){
		                		acknowledgementDate = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("SystemDate")){
		                		systemDate = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("ApprovalStatus")){
		                		approveStatus = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("visaformCheck")){
		                		visaformCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldVisaform")){
		                		oldVisaform = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("additionalfileCheck")){
		                		additionalfileCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldAdditionalfile")){
		                		oldAdditionalfile = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in DOS-Stage1 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("visaform")) {
					        	  System.out.println("fPart.getFileName() in DOS-Stage1 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  dosFileList.add(fileLocation);
								  visaformFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("additionalfile")) {
					        	  System.out.println("fPart.getFileName() in DOS-Stage1 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  //dosFileList.add(fileLocation);
								  additionalfileFile = fileLocation;
					          }
					        
					          System.out.println("File name side Stage1 action --------->"+name);
					        if((visaformCheck == null || visaformCheck == "" || visaformCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("visaform"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldVisaform-------------in-----if visaformCheck--Stage1 action--->"+oldVisaform);
						        	  //System.out.println("oldAdditionalfile-------------in-----else--Stage1 action--->"+oldAdditionalfile);
						        	  dosFileList.add(oldVisaform);
									  visaformFile = oldVisaform;
				             }
						    if((additionalfileCheck == null || additionalfileCheck == "" || additionalfileCheck.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("additionalfile"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  //System.out.println("oldVisaform-------------in-----else--Stage1 action--->"+oldVisaform);
						        	  System.out.println("oldAdditionalfile-------------in-----if additionalfileCheck--Stage1 action--->"+oldAdditionalfile);
						        	  //dosFileList.add(oldAdditionalfile);
									  additionalfileFile = oldAdditionalfile;
				             }
				                  
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && visaformFile != null){
	            	
	            	String dosBPELStatus = null;
	            	DOSClientStage1 clientStage1 = new DOSClientStage1();
	            	dosBPELStatus = clientStage1.stage1dos(empUniqueId, visaformFile, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	if(dosBPELStatus != null && dosBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertDOSStageOneDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,visaformFile,additionalfileFile);
			        }else{
			            	throw new Exception("Stage One Was Failed in BPEL Process");
			        }
		            
	            System.out.println("insertStatus in DepartmentStateAction-Stage1::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOSStageOneData = new ArrayList();
			            DOSStageOneData = db.getDOSStageOneDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOS_id = null;
		            	String emp_unique_id = null;
		            	String stage1CreateDate = null;
		     		    String stage1AcknowledgementDate = null;
		     		    String stage1SystemDate = null;
		     		    String stage1ApproveStatus = null;
		            	String stage1Data = null;
		            	String stage1_status = null;
		            	String stage2_status = null;
		            	String stage3_status = null;
		            	String stage4_status = null;
		            	String stage5_status = null;
		            	String stage6_status = null;
			            String[] stageOneDataArray = null;
			            String[] stageOneStatusArray = null;
			            if(DOSStageOneData!=null && DOSStageOneData.size()!=0){
			            	
							Iterator itr = DOSStageOneData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOS_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage1Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stageOneStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status};
							    
							    if(stage1Data != null && stage1Data != "" && !(stage1Data.equalsIgnoreCase(""))){
				        			String []tempStage1DataArray = stage1Data.split("-sep-");
				        			stageOneDataArray = new String[tempStage1DataArray.length - 4];
				        			stage1CreateDate = tempStage1DataArray[0];
				        			stage1AcknowledgementDate = tempStage1DataArray[1];
				        			stage1SystemDate = tempStage1DataArray[2];
				        			stage1ApproveStatus = tempStage1DataArray[3];
				        			for(int j=0;j<(tempStage1DataArray.length - 4);j++){
				        				stageOneDataArray[j] = tempStage1DataArray[4 + j];
				        				System.out.println("DepartmentStateAction-Stage1----->stageOneDataArray["+j+"]-------------->"+stageOneDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOS_id", unique_DOS_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage1CreateDate", stage1CreateDate);
			            request.setAttribute("stage1AcknowledgementDate", stage1AcknowledgementDate);
			            request.setAttribute("stage1SystemDate", stage1SystemDate);
			            request.setAttribute("stage1ApproveStatus", stage1ApproveStatus);
			            if(empUniqueId != null){
			    			ArrayList employeeDetails = new ArrayList();
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageOneDataArray", stageOneDataArray);
			            request.setAttribute("stageOneStatusArray", stageOneStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOS/frmDepartmentStateStage1");
		            }
	            }else{
	            	throw new Exception("Input parameters or Uploaded file are not reached to Server");
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
    	   request.setAttribute("status","fail");
    	   request.setAttribute("pageStatus","init");
    	   return new ModelAndView("DOS/frmDepartmentStateStage1");
	       
	}
	
	@RequestMapping("/initDepartmentStateStage2.html")
	public ModelAndView initDepartmentStateStage2(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOSStageTwoData = new ArrayList();
	        DOSStageTwoData = db.getDOSStageTwoDetailsByUniqueEmpId(empUniqueId);
	        
	        if(DOSStageTwoData != null && DOSStageTwoData.size() > 0){
		        String unique_DOS_id = null;
		    	String emp_unique_id = null;
		    	String stage2CreateDate = null;
				String stage2AcknowledgementDate = null;
				String stage2SystemDate = null;
				String stage2ApproveStatus = null;
		    	String stage2Data = null;
		    	String stage1_status = null;
		    	String stage2_status = null;
		    	String stage3_status = null;
		    	String stage4_status = null;
		    	String stage5_status = null;
		    	String stage6_status = null;
		        String[] stageTwoDataArray = null;
		        String[] stageTwoStatusArray = null;
		        //if(DOSStageTwoData!=null && DOSStageTwoData.size()!=0){
		        	
					Iterator itr = DOSStageTwoData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOS_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage2Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stageTwoStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status};
					    
					    if(stage2Data != null && stage2Data != "" && !(stage2Data.equalsIgnoreCase(""))){
		        			String []tempStage2DataArray = stage2Data.split("-sep-");
		        			stageTwoDataArray = new String[tempStage2DataArray.length - 4];
		        			stage2CreateDate = tempStage2DataArray[0];
		        			stage2AcknowledgementDate = tempStage2DataArray[1];
		        			stage2SystemDate = tempStage2DataArray[2];
		        			stage2ApproveStatus = tempStage2DataArray[3];
		        			for(int j=0;j<(tempStage2DataArray.length - 4);j++){
		        				stageTwoDataArray[j] = tempStage2DataArray[4 + j];
		        				System.out.println("DepartmentStateAction-Stage2----->stageTwoDataArray["+j+"]-------------->"+stageTwoDataArray[j]);
		        				}
		        		}else{
		        			employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
					        request.setAttribute("employeeDetails",null);
					        request.setAttribute("employeeDetails",employeeDetails);
					        request.setAttribute("pageStatus","init");
					        request.setAttribute("stageTwoStatusArray", stageTwoStatusArray);
							return new ModelAndView("DOS/frmDepartmentStateStage2");
		        		}
				    }
		        //}
		        
		        request.setAttribute("unique_DOS_id", unique_DOS_id);
		        request.setAttribute("emp_unique_id", emp_unique_id);
		        request.setAttribute("stage2CreateDate", stage2CreateDate);
		        request.setAttribute("stage2AcknowledgementDate", stage2AcknowledgementDate);
		        request.setAttribute("stage2SystemDate", stage2SystemDate);
		        request.setAttribute("stage2ApproveStatus", stage2ApproveStatus);
		        if(empUniqueId != null){
		        	System.out.println("empUniqueId------------------->"+empUniqueId);
			        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			        request.setAttribute("employeeDetails",null);
			        request.setAttribute("employeeDetails",employeeDetails);
		        }
		        request.setAttribute("stageTwoDataArray", stageTwoDataArray);
		        request.setAttribute("stageTwoStatusArray", stageTwoStatusArray);
		        status = "success";
		        request.setAttribute("status",status);
		        request.setAttribute("pageStatus","update");
				return new ModelAndView("DOS/frmDepartmentStateStage2");
				}else{
			        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
			        request.setAttribute("employeeDetails",null);
			        request.setAttribute("employeeDetails",employeeDetails);
			        request.setAttribute("pageStatus","init");
					return new ModelAndView("DOS/frmDepartmentStateStage2");
				}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	
	@RequestMapping("/doDepartmentStateStage2.html")
	public ModelAndView doDepartmentStateStage2(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Properties config_property = new Properties();
		config_inputStream = getClass().getClassLoader().getResourceAsStream(config_prop);

		if (config_inputStream != null) {
			config_property.load(config_inputStream);
		} else {
			throw new FileNotFoundException("property file '" + config_prop + "' not found in the classpath");
		}
			
			HttpSession session=request.getSession(true); 
//			  	long creationTime = session.getCreationTime();
//		        String sessionId = session.getId();
//		        long lastAccessedTime = session.getLastAccessedTime();
//		        Date createDate= new Date(creationTime);
//		        Date lastlogin= new Date(lastAccessedTime);
//		        System.out.println("lastAccessedDate"+lastlogin); 
//		        System.out.println("createDate"+createDate); 
		 
    	   try {
    		   GeneralDBAction db=new GeneralDBAction();
    			
    		   String oldFormI_94 = null;
    		   String oldAdditionalfile = "";
    		   String formI_94Check = null;
    		   String additionalfileCheck = null;
			   
			   String formI_94File = null;
    		   String additionalfileFile = null;
    			   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
    		   ArrayList <String>dosFileList = new ArrayList<String>();
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfState");
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
		                	if(parmName.equals("empUniqueId")){
		                		empUniqueId = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("empId")){
		                		empId = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("CreateDate")){
		                		createDate = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("AcknowledgementDate")){
		                		acknowledgementDate = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("SystemDate")){
		                		systemDate = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("ApprovalStatus")){
		                		approveStatus = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("formI_94Check")){
		                		formI_94Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldFormI_94")){
		                		oldFormI_94 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("additionalfileCheck")){
		                		additionalfileCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldAdditionalfile")){
		                		oldAdditionalfile = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("formI_94")) {
					        	  System.out.println("fPart.getFileName() in DOS-Stage2 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  //dosFileList.add(fileLocation);
								  formI_94File = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("additionalfile")) {
					        	  System.out.println("fPart.getFileName() in DOS-Stage2 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  //dosFileList.add(fileLocation);
								  additionalfileFile = fileLocation;
					          }
					          
					          System.out.println("File name side Stage1 action --------->"+name);
						        if((formI_94Check == null || formI_94Check == "" || formI_94Check.equalsIgnoreCase("")) &&
							        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("formI_94"))){
					                      //resp = "<br>The user did not upload a file for this part.";
							        	  System.out.println("oldFormI_94-------------in-----if visaformCheck--Stage2 action--->"+oldFormI_94);
							        	  //System.out.println("oldAdditionalfile-------------in-----else--Stage1 action--->"+oldAdditionalfile);
							        	  //dosFileList.add(oldFormI_94);
										  formI_94File = oldFormI_94;
					             }
							    if((additionalfileCheck == null || additionalfileCheck == "" || additionalfileCheck.equalsIgnoreCase(""))
							        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("additionalfile"))){
					                      //resp = "<br>The user did not upload a file for this part.";
							        	  //System.out.println("oldVisaform-------------in-----else--Stage1 action--->"+oldVisaform);
							        	  System.out.println("oldAdditionalfile-------------in-----if additionalfileCheck--Stage2 action--->"+oldAdditionalfile);
							        	  //dosFileList.add(oldAdditionalfile);
										  additionalfileFile = oldAdditionalfile;
					             }
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && formI_94File != null){
	            	
	            	String dosBPELStatus = null;
	            	DOSClientStage2 clientStage2 = new DOSClientStage2();
	            	dosBPELStatus = clientStage2.stage2dos(formI_94File, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	if(dosBPELStatus != null && dosBPELStatus.equalsIgnoreCase("Success2")){	
	            		insertStatus = db.insertDOSStageTwoDetails(empUniqueId,createDate,acknowledgementDate,systemDate,
	    	            		approveStatus,formI_94File,additionalfileFile);
			        }else{
			            	throw new Exception("Stage Two Was Failed in BPEL Process");
			        }
	            	
	            System.out.println("insertStatus in DepartmentStateAction-Stage2::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOSStageTwoData = new ArrayList();
			            DOSStageTwoData = db.getDOSStageTwoDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOS_id = null;
		            	String emp_unique_id = null;
		            	String stage2CreateDate = null;
		     		    String stage2AcknowledgementDate = null;
		     		    String stage2SystemDate = null;
		     		    String stage2ApproveStatus = null;
		            	String stage2Data = null;
		            	String stage1_status = null;
		            	String stage2_status = null;
		            	String stage3_status = null;
		            	String stage4_status = null;
		            	String stage5_status = null;
		            	String stage6_status = null;
			            String[] stageTwoDataArray = null;
			            String[] stageTwoStatusArray = null;
			            if(DOSStageTwoData!=null && DOSStageTwoData.size()!=0){
			            	
							Iterator itr = DOSStageTwoData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOS_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage2Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stageTwoStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status};
							    
							    if(stage2Data != null && stage2Data != "" && !(stage2Data.equalsIgnoreCase(""))){
				        			String []tempStage2DataArray = stage2Data.split("-sep-");
				        			stageTwoDataArray = new String[tempStage2DataArray.length - 4];
				        			stage2CreateDate = tempStage2DataArray[0];
				        			stage2AcknowledgementDate = tempStage2DataArray[1];
				        			stage2SystemDate = tempStage2DataArray[2];
				        			stage2ApproveStatus = tempStage2DataArray[3];
				        			for(int j=0;j<(tempStage2DataArray.length - 4);j++){
				        				stageTwoDataArray[j] = tempStage2DataArray[4 + j];
				        				System.out.println("DepartmentStateAction-Stage2----->stageTwoDataArray["+j+"]-------------->"+stageTwoDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOS_id", unique_DOS_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage2CreateDate", stage2CreateDate);
			            request.setAttribute("stage2AcknowledgementDate", stage2AcknowledgementDate);
			            request.setAttribute("stage2SystemDate", stage2SystemDate);
			            request.setAttribute("stage2ApproveStatus", stage2ApproveStatus);
			            if(empUniqueId != null){
			            	System.out.println("empUniqueId------------------->"+empUniqueId);
			    			ArrayList employeeDetails = new ArrayList();
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageTwoDataArray", stageTwoDataArray);
			            request.setAttribute("stageTwoStatusArray", stageTwoStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOS/frmDepartmentStateStage2");
		            }else{
		            	throw new Exception("Information not stored in Server. Please Try again!");
		            }
	            }else{
	            	throw new Exception("Input parameters or Uploaded file are not reached to Server");
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
    	   //request.setAttribute("status","fail");
    	   //request.setAttribute("pageStatus","init");
    	   //return new ModelAndView("DOS/frmDepartmentStatestage2");
	       
	}
	
	@RequestMapping("/initDepartmentStateStage3.html")
	public ModelAndView initDepartmentStateStage3(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			ArrayList DOSStageThreeData = new ArrayList();
            DOSStageThreeData = db.getDOSStageThreeDetailsByUniqueEmpId(empUniqueId);
            
            if(DOSStageThreeData != null && DOSStageThreeData.size() > 0){
	            String unique_DOS_id = null;
	        	String emp_unique_id = null;
	        	String stage3StartDate = null;
	 		    String stage3EndDate = null;
	 		    String stage3CreateDate = null;
			    String stage3AcknowledgementDate = null;
	 		    String stage3SystemDate = null;
	 		    String stage3ApproveStatus = null;
	        	String stage3Data = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	            String[] stageThreeStatusArray = null;
	            if(DOSStageThreeData!=null && DOSStageThreeData.size()!=0){
					Iterator itr = DOSStageThreeData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOS_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage3Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stageThreeStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status};
					    
					    if(stage3Data != null && stage3Data != "" && !(stage3Data.equalsIgnoreCase(""))){
		        			String []tempstage3DataArray = stage3Data.split("-sep-");
		        			stage3StartDate = tempstage3DataArray[0];
		        			stage3EndDate = tempstage3DataArray[1];
		        			stage3CreateDate = tempstage3DataArray[2];
		        			stage3AcknowledgementDate = tempstage3DataArray[3];
		        			stage3SystemDate = tempstage3DataArray[4];
		        			stage3ApproveStatus = tempstage3DataArray[5];
					    }else{
					    	employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
					        request.setAttribute("employeeDetails",null);
					        request.setAttribute("employeeDetails",employeeDetails);
					        request.setAttribute("pageStatus","init");
					        request.setAttribute("stageThreeStatusArray", stageThreeStatusArray);
							return new ModelAndView("DOS/frmDepartmentStateStage3");
					    }
					    }
				    }
	            request.setAttribute("unique_DOS_id", unique_DOS_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("StartDate", stage3StartDate);
	            request.setAttribute("EndDate", stage3EndDate);
	            request.setAttribute("CreateDate", stage3CreateDate);
	            request.setAttribute("AcknowledgementDate", stage3AcknowledgementDate);
	            request.setAttribute("SystemDate", stage3SystemDate);
	            request.setAttribute("ApprovalStatus", stage3ApproveStatus);
	            request.setAttribute("stageThreeStatusArray", stageThreeStatusArray);
	            if(empUniqueId != null){
	            	System.out.println("empUniqueId------------------->"+empUniqueId);
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOS/frmDepartmentStateStage3");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOS/frmDepartmentStateStage3");
			}
        }else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDepartmentStateStage3.html")
	public ModelAndView doDepartmentStateStage3(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		String uniqueDOSId = null;
		String empUniqueId = null;
		String empId = null;
		String startDate = null;
		String endDate = null;
		String createDate = null;
		String acknowledgementDate = null;
		String systemDate = null;
		String approveStatus = null;
		
		String status = null;	
		
		empUniqueId  = request.getParameter("empUniqueId");
		empId  = request.getParameter("empId");
		startDate  = request.getParameter("StartDate");
		endDate  = request.getParameter("EndDate");
		createDate  = request.getParameter("CreateDate");
		acknowledgementDate  = request.getParameter("AcknowledgementDate");
		systemDate  = request.getParameter("SystemDate");
		approveStatus  = request.getParameter("ApprovalStatus");
		
		ArrayList employeeDetails = new ArrayList();
		
		boolean insertStatus = false;
        if(empUniqueId != null && startDate != null && endDate != null && createDate != null && 
        		acknowledgementDate != null && systemDate != null && approveStatus != null){
        	
        	String dosBPELStatus = null;
        	DOSClientStage3 clientStage3 = new DOSClientStage3();
        	dosBPELStatus = clientStage3.stage3dos(startDate, endDate, createDate, acknowledgementDate, systemDate, approveStatus);
	            
        	if(dosBPELStatus != null && dosBPELStatus.equalsIgnoreCase("Success3")){	
        		insertStatus = db.insertDOSStageThreeDetails(empUniqueId,startDate,endDate,createDate,acknowledgementDate,
                		systemDate,approveStatus);
	        }else{
	            	throw new Exception("Stage Three Was Failed in BPEL Process");
	        }
        	
        	System.out.println("insertStatus in DepartmentStateAction-Stage3::::::::"+insertStatus);
            if(insertStatus){
	            ArrayList DOSStageThreeData = new ArrayList();
	            DOSStageThreeData = db.getDOSStageThreeDetailsByUniqueEmpId(empUniqueId);
	            
	            String unique_DOS_id = null;
            	String emp_unique_id = null;
            	String stage3StartDate = null;
     		    String stage3EndDate = null;
     		    String stage3CreateDate = null;
    		    String stage3AcknowledgementDate = null;
     		    String stage3SystemDate = null;
     		    String stage3ApproveStatus = null;
            	String stage3Data = null;
            	String stage1_status = null;
            	String stage2_status = null;
            	String stage3_status = null;
            	String stage4_status = null;
            	String stage5_status = null;
            	String stage6_status = null;
	            String[] stageThreeStatusArray = null;
	            if(DOSStageThreeData!=null && DOSStageThreeData.size()!=0){
					Iterator itr = DOSStageThreeData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOS_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage3Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stageThreeStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status};
					    
					    if(stage3Data != null && stage3Data != "" && !(stage3Data.equalsIgnoreCase(""))){
		        			String []tempstage3DataArray = stage3Data.split("-sep-");
		        			stage3StartDate = tempstage3DataArray[0];
		        			stage3EndDate = tempstage3DataArray[1];
		        			stage3CreateDate = tempstage3DataArray[2];
		        			stage3AcknowledgementDate = tempstage3DataArray[3];
		        			stage3SystemDate = tempstage3DataArray[4];
		        			stage3ApproveStatus = tempstage3DataArray[5];
					    }else{
					    	employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
					        request.setAttribute("employeeDetails",null);
					        request.setAttribute("employeeDetails",employeeDetails);
					        request.setAttribute("pageStatus","init");
					        request.setAttribute("stageThreeStatusArray", stageThreeStatusArray);
							return new ModelAndView("DOS/frmDepartmentStateStage3");
					    }
				    }
            }
	            request.setAttribute("unique_DOS_id", unique_DOS_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("StartDate", stage3StartDate);
	            request.setAttribute("EndDate", stage3EndDate);
	            request.setAttribute("CreateDate", stage3CreateDate);
	            request.setAttribute("AcknowledgementDate", stage3AcknowledgementDate);
	            request.setAttribute("SystemDate", stage3SystemDate);
	            request.setAttribute("ApprovalStatus", stage3ApproveStatus);
	            request.setAttribute("stageThreeStatusArray", stageThreeStatusArray);
	            if(empUniqueId != null){
	            	System.out.println("empUniqueId------------------->"+empUniqueId);
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOS/frmDepartmentStateStage3");
	        }else{
	        	throw new Exception("Information not stored in Server. Please Try again!");
	        }
     }else{
    	 throw new Exception("Input parameters or Uploaded file are not reached to Server");
    	 }
		
	}
	
	@RequestMapping("/initDepartmentStateStage4.html")
	public ModelAndView initDepartmentStateStage4(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			ArrayList DOSStageFourData = new ArrayList();
	        DOSStageFourData = db.getDOSStageFourDetailsByUniqueEmpId(empUniqueId);
	        
	            if(DOSStageFourData != null && DOSStageFourData.size() > 0){
		            String unique_DOS_id = null;
	            	String emp_unique_id = null;
	            	String stage4CreateDate = null;
	     		    String stage4AcknowledgementDate = null;
	     		    String stage4SystemDate = null;
	     		    String stage4ApproveStatus = null;
	            	String stage4Data = null;
	            	String stage1_status = null;
	            	String stage2_status = null;
	            	String stage3_status = null;
	            	String stage4_status = null;
	            	String stage5_status = null;
	            	String stage6_status = null;
		            String[] stageFourDataArray = null;
		            String[] stageFourStatusArray = null;
		            if(DOSStageFourData!=null && DOSStageFourData.size()!=0){
		            	
						Iterator itr = DOSStageFourData.iterator();
					    while (itr.hasNext()) {    
						    String TempList[] = (String[])itr.next();
						    unique_DOS_id = TempList[0];
						    emp_unique_id = TempList[1];
						    stage4Data = TempList[2];
						    stage1_status = TempList[3];
						    stage2_status = TempList[4];
						    stage3_status = TempList[5];
						    stage4_status = TempList[6];
						    stage5_status = TempList[7];
						    stage6_status = TempList[8];
						    stageFourStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status};
						    
						    if(stage4Data != null && stage4Data != "" && !(stage4Data.equalsIgnoreCase(""))){
			        			String []tempstage4DataArray = stage4Data.split("-sep-");
			        			stageFourDataArray = new String[tempstage4DataArray.length - 4];
			        			stage4CreateDate = tempstage4DataArray[0];
			        			stage4AcknowledgementDate = tempstage4DataArray[1];
			        			stage4SystemDate = tempstage4DataArray[2];
			        			stage4ApproveStatus = tempstage4DataArray[3];
			        			for(int j=0;j<(tempstage4DataArray.length - 4);j++){
			        				stageFourDataArray[j] = tempstage4DataArray[4 + j];
			        				System.out.println("DepartmentStateAction-stage4----->stageFourDataArray["+j+"]-------------->"+stageFourDataArray[j]);
			        				}
			        		}else{
			        			employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
				    	        request.setAttribute("employeeDetails",null);
				    	        request.setAttribute("employeeDetails",employeeDetails);
				    	        request.setAttribute("pageStatus","init");
				    	        request.setAttribute("stageFourStatusArray", stageFourStatusArray);
				    			return new ModelAndView("DOS/frmDepartmentStateStage4");
			        		}
					    }
			        }
		            
		            request.setAttribute("unique_DOS_id", unique_DOS_id);
		            request.setAttribute("emp_unique_id", emp_unique_id);
		            request.setAttribute("CreateDate", stage4CreateDate);
		            request.setAttribute("AcknowledgementDate", stage4AcknowledgementDate);
		            request.setAttribute("SystemDate", stage4SystemDate);
		            request.setAttribute("ApprovalStatus", stage4ApproveStatus);
		            if(empUniqueId != null){
		            	System.out.println("empUniqueId------------------->"+empUniqueId);
		    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
		    	        request.setAttribute("employeeDetails",null);
		    	        request.setAttribute("employeeDetails",employeeDetails);
		            }
		            request.setAttribute("stageFourDataArray", stageFourDataArray);
		            request.setAttribute("stageFourStatusArray", stageFourStatusArray);
		            status = "success";
		            request.setAttribute("status",status);
		            request.setAttribute("pageStatus","update");
		    		return new ModelAndView("DOS/frmDepartmentStateStage4");
		    	}else{
	            	employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	    	        request.setAttribute("pageStatus","init");
	    			return new ModelAndView("DOS/frmDepartmentStateStage4");
	    		}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDepartmentStateStage4.html")
	public ModelAndView doDepartmentStateStage4(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Properties config_property = new Properties();
		config_inputStream = getClass().getClassLoader().getResourceAsStream(config_prop);

		if (config_inputStream != null) {
			config_property.load(config_inputStream);
		} else {
			throw new FileNotFoundException("property file '" + config_prop + "' not found in the classpath");
		}
			
			HttpSession session=request.getSession(true); 
//			  	long creationTime = session.getCreationTime();
//		        String sessionId = session.getId();
//		        long lastAccessedTime = session.getLastAccessedTime();
//		        Date createDate= new Date(creationTime);
//		        Date lastlogin= new Date(lastAccessedTime);
//		        System.out.println("lastAccessedDate"+lastlogin); 
//		        System.out.println("createDate"+createDate); 
		 
    	   try {
    		   GeneralDBAction db=new GeneralDBAction();
    			
    		   String oldNewI_9form = null;
    		   String newI_9formCheck = null;
			   
			   String newI_9formFile = null;
    				
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
   			ArrayList employeeDetails = new ArrayList();
    		   
    		   ArrayList <String>dosFileList = new ArrayList<String>();
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfState");
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
		                	if(parmName.equals("empUniqueId")){
		                		empUniqueId = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("empId")){
		                		empId = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("CreateDate")){
		                		createDate = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("AcknowledgementDate")){
		                		acknowledgementDate = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("SystemDate")){
		                		systemDate = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("ApprovalStatus")){
		                		approveStatus = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("newI_9formCheck")){
		                		newI_9formCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldNewI_9form")){
		                		oldNewI_9form = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("newI_9form")) {
					        	  System.out.println("fPart.getFileName() in DOS-Stage4 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  //dosFileList.add(fileLocation);
								  newI_9formFile = fileLocation;
					          }
					          
					          System.out.println("File name side Stage1 action --------->"+name);
						        if((newI_9formCheck == null || newI_9formCheck == "" || newI_9formCheck.equalsIgnoreCase("")) &&
							        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("newI_9form"))){
					                      //resp = "<br>The user did not upload a file for this part.";
							        	  System.out.println("oldNewI_9form-------------in-----if visaformCheck--Stage4 action--->"+oldNewI_9form);
							        	  //System.out.println("oldAdditionalfile-------------in-----else--Stage1 action--->"+oldAdditionalfile);
							        	  //dosFileList.add(oldNewI_9form);
										  newI_9formFile = oldNewI_9form;
					             }
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && newI_9formFile != null){
	            	
	            	String dosBPELStatus = null;
	            	DOSClientStage4 clientStage4 = new DOSClientStage4();
	            	dosBPELStatus = clientStage4.stage4dos(newI_9formFile, createDate, acknowledgementDate, systemDate, approveStatus);
	    	            
	            	if(dosBPELStatus != null && dosBPELStatus.equalsIgnoreCase("Success4")){	
	            		insertStatus = db.insertDOSStageFourDetails(empUniqueId,createDate,acknowledgementDate,systemDate,
	    	            		approveStatus,newI_9formFile);
	    	        }else{
	    	            	throw new Exception("Stage Four Was Failed in BPEL Process");
	    	        }
	            
	            System.out.println("insertStatus in DepartmentStateAction-Stage4::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOSStageFourData = new ArrayList();
			            DOSStageFourData = db.getDOSStageFourDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOS_id = null;
		            	String emp_unique_id = null;
		            	String stage4CreateDate = null;
		     		    String stage4AcknowledgementDate = null;
		     		    String stage4SystemDate = null;
		     		    String stage4ApproveStatus = null;
		            	String stage4Data = null;
		            	String stage1_status = null;
		            	String stage2_status = null;
		            	String stage3_status = null;
		            	String stage4_status = null;
		            	String stage5_status = null;
		            	String stage6_status = null;
			            String[] stageFourDataArray = null;
			            String[] stageFourStatusArray = null;
			            if(DOSStageFourData!=null && DOSStageFourData.size()!=0){
			            	
							Iterator itr = DOSStageFourData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOS_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage4Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stageFourStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status};
							    
							    if(stage4Data != null && stage4Data != "" && !(stage4Data.equalsIgnoreCase(""))){
				        			String []tempstage4DataArray = stage4Data.split("-sep-");
				        			stageFourDataArray = new String[tempstage4DataArray.length - 4];
				        			stage4CreateDate = tempstage4DataArray[0];
				        			stage4AcknowledgementDate = tempstage4DataArray[1];
				        			stage4SystemDate = tempstage4DataArray[2];
				        			stage4ApproveStatus = tempstage4DataArray[3];
				        			for(int j=0;j<(tempstage4DataArray.length - 4);j++){
				        				stageFourDataArray[j] = tempstage4DataArray[4 + j];
				        				System.out.println("DepartmentStateAction-stage4----->stageFourDataArray["+j+"]-------------->"+stageFourDataArray[j]);
				        				}
				        		}else{
				        			employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
					    	        request.setAttribute("employeeDetails",null);
					    	        request.setAttribute("employeeDetails",employeeDetails);
					    	        request.setAttribute("pageStatus","init");
					    	        request.setAttribute("stageFourStatusArray", stageFourStatusArray);
					    			return new ModelAndView("DOS/frmDepartmentStateStage4");
				        		}
						    }
				        }
			            
			            request.setAttribute("unique_DOS_id", unique_DOS_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("CreateDate", stage4CreateDate);
			            request.setAttribute("AcknowledgementDate", stage4AcknowledgementDate);
			            request.setAttribute("SystemDate", stage4SystemDate);
			            request.setAttribute("ApprovalStatus", stage4ApproveStatus);
			            if(empUniqueId != null){
			            	System.out.println("empUniqueId------------------->"+empUniqueId);
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageFourDataArray", stageFourDataArray);
			            request.setAttribute("stageFourStatusArray", stageFourStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOS/frmDepartmentStateStage4");
		            }else{
		            	employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		    	        request.setAttribute("employeeDetails",null);
		    	        request.setAttribute("employeeDetails",employeeDetails);
		    	        request.setAttribute("pageStatus","init");
		    			return new ModelAndView("DOS/frmDepartmentStateStage4");
		            }
	            }else{
	            	throw new Exception("Input parameters or Uploaded file are not reached to Server");
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
    	   //request.setAttribute("status","fail");
    	   //request.setAttribute("pageStatus","init");
    	   //return new ModelAndView("DOS/frmDepartmentStatestage4");
	       
	}
	
	@RequestMapping("/initDepartmentStateStage5.html")
	public ModelAndView initDepartmentStateStage5(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOSStageFiveData = new ArrayList();
            DOSStageFiveData = db.getDOSStageFiveDetailsByUniqueEmpId(empUniqueId);
            
            if(DOSStageFiveData != null && DOSStageFiveData.size() > 0){
	            String unique_DOS_id = null;
	        	String emp_unique_id = null;
	        	String stage5CreateDate = null;
	 		    String stage5AcknowledgementDate = null;
	 		    String stage5SystemDate = null;
	 		    String stage5ApproveStatus = null;
	        	String stage5Data = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	            String[] stageFiveDataArray = null;
	            String[] stageFiveStatusArray = null;
	            if(DOSStageFiveData!=null && DOSStageFiveData.size()!=0){
	            	
					Iterator itr = DOSStageFiveData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOS_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage5Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stageFiveStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status};
					    
					    if(stage5Data != null && stage5Data != "" && !(stage5Data.equalsIgnoreCase(""))){
		        			String []tempstage5DataArray = stage5Data.split("-sep-");
		        			stageFiveDataArray = new String[tempstage5DataArray.length - 4];
		        			stage5CreateDate = tempstage5DataArray[0];
		        			stage5AcknowledgementDate = tempstage5DataArray[1];
		        			stage5SystemDate = tempstage5DataArray[2];
		        			stage5ApproveStatus = tempstage5DataArray[3];
		        			for(int j=0;j<(tempstage5DataArray.length - 4);j++){
		        				stageFiveDataArray[j] = tempstage5DataArray[4 + j];
		        				System.out.println("DepartmentStateAction-stage5----->stageFiveDataArray["+j+"]-------------->"+stageFiveDataArray[j]);
		        				}
		        		}else{
		        			employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		    		        request.setAttribute("employeeDetails",null);
		    		        request.setAttribute("employeeDetails",employeeDetails);
		    		        request.setAttribute("pageStatus","init");
		    		        request.setAttribute("stageFiveStatusArray", stageFiveStatusArray);
		    				return new ModelAndView("DOS/frmDepartmentStateStage5");
		        		}
				    }
		        }
	            
	            request.setAttribute("unique_DOS_id", unique_DOS_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("CreateDate", stage5CreateDate);
	            request.setAttribute("AcknowledgementDate", stage5AcknowledgementDate);
	            request.setAttribute("SystemDate", stage5SystemDate);
	            request.setAttribute("ApprovalStatus", stage5ApproveStatus);
	            if(empUniqueId != null){
	            	System.out.println("empUniqueId------------------->"+empUniqueId);
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageFiveDataArray", stageFiveDataArray);
	            request.setAttribute("stageFiveStatusArray", stageFiveStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOS/frmDepartmentStateStage5");
	        }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOS/frmDepartmentStateStage5");
	        }
       }else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDepartmentStateStage5.html")
	public ModelAndView doDepartmentStateStage5(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Properties config_property = new Properties();
		config_inputStream = getClass().getClassLoader().getResourceAsStream(config_prop);

		if (config_inputStream != null) {
			config_property.load(config_inputStream);
		} else {
			throw new FileNotFoundException("property file '" + config_prop + "' not found in the classpath");
		}
			
			HttpSession session=request.getSession(true); 
//			  	long creationTime = session.getCreationTime();
//		        String sessionId = session.getId();
//		        long lastAccessedTime = session.getLastAccessedTime();
//		        Date createDate= new Date(creationTime);
//		        Date lastlogin= new Date(lastAccessedTime);
//		        System.out.println("lastAccessedDate"+lastlogin); 
//		        System.out.println("createDate"+createDate); 
		 
    	   try {
    		   GeneralDBAction db=new GeneralDBAction();
    			
    		   String oldFileAR_1 = null;
    		   String oldAdditional_address_docs = "";
    		   String FileAR_1Check = null;
    		   String additional_address_docsCheck = null;
			   
			   String FileAR_1File = null;
    		   String additional_address_docsFile = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
   			   ArrayList employeeDetails = new ArrayList();
    		   ArrayList <String>dosFileList = new ArrayList<String>();
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfState");
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
		                	if(parmName.equals("empUniqueId")){
		                		empUniqueId = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("empId")){
		                		empId = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("CreateDate")){
		                		createDate = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("AcknowledgementDate")){
		                		acknowledgementDate = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("SystemDate")){
		                		systemDate = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("ApprovalStatus")){
		                		approveStatus = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("FileAR_1Check")){
		                		FileAR_1Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldFileAR_1")){
		                		oldFileAR_1 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("additional_address_docsCheck")){
		                		additional_address_docsCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldAdditional_address_docs")){
		                		oldAdditional_address_docs = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("FileAR_1")) {
					        	  System.out.println("fPart.getFileName() in DOS-Stage5 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  //dosFileList.add(fileLocation);
								  FileAR_1File = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("additional_address_docs")) {
					        	  System.out.println("fPart.getFileName() in DOS-Stage5 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  //dosFileList.add(fileLocation);
								  additional_address_docsFile = fileLocation;
					          }
					          
					          System.out.println("File name side Stage1 action --------->"+name);
						        if((FileAR_1Check == null || FileAR_1Check == "" || FileAR_1Check.equalsIgnoreCase("")) &&
							        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("FileAR_1"))){
					                      //resp = "<br>The user did not upload a file for this part.";
							        	  System.out.println("oldFileAR_1-------------in-----if visaformCheck--Stage5 action--->"+oldFileAR_1);
							        	  //System.out.println("oldAdditionalfile-------------in-----else--Stage1 action--->"+oldAdditionalfile);
							        	  //dosFileList.add(oldFileAR_1);
										  FileAR_1File = oldFileAR_1;
					             }
							    if((additional_address_docsCheck == null || additional_address_docsCheck == "" || additional_address_docsCheck.equalsIgnoreCase(""))
							        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("additional_address_docs"))){
					                      //resp = "<br>The user did not upload a file for this part.";
							        	  //System.out.println("oldVisaform-------------in-----else--Stage1 action--->"+oldVisaform);
							        	  System.out.println("oldAdditional_address_docs-------------in-----if additionalfileCheck--Stage5 action--->"+oldAdditional_address_docs);
							        	  //dosFileList.add(oldAdditional_address_docs);
										  additional_address_docsFile = oldAdditional_address_docs;
					             }
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && FileAR_1File != null){
	            	
	            	String dosBPELStatus = null;
	            	DOSClientStage5 clientStage5 = new DOSClientStage5();
	            	dosBPELStatus = clientStage5.stage5dos(FileAR_1File, additional_address_docsFile, createDate, acknowledgementDate, systemDate, approveStatus);
	    	            
	            	if(dosBPELStatus != null && dosBPELStatus.equalsIgnoreCase("Success5")){	
	            		insertStatus = db.insertDOSStageFiveDetails(empUniqueId,createDate,acknowledgementDate,systemDate,
	    	            		approveStatus,FileAR_1File,additional_address_docsFile);
	    	        }else{
	    	            	throw new Exception("Stage Five Was Failed in BPEL Process");
	    	        }
	            	
	            System.out.println("insertStatus in DepartmentStateAction-Stage4::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOSStageFiveData = new ArrayList();
			            DOSStageFiveData = db.getDOSStageFiveDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOS_id = null;
		            	String emp_unique_id = null;
		            	String stage5CreateDate = null;
		     		    String stage5AcknowledgementDate = null;
		     		    String stage5SystemDate = null;
		     		    String stage5ApproveStatus = null;
		            	String stage5Data = null;
		            	String stage1_status = null;
		            	String stage2_status = null;
		            	String stage3_status = null;
		            	String stage4_status = null;
		            	String stage5_status = null;
		            	String stage6_status = null;
			            String[] stageFiveDataArray = null;
			            String[] stageFiveStatusArray = null;
			            if(DOSStageFiveData!=null && DOSStageFiveData.size()!=0){
			            	
							Iterator itr = DOSStageFiveData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOS_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage5Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stageFiveStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status};
							    
							    if(stage5Data != null && stage5Data != "" && !(stage5Data.equalsIgnoreCase(""))){
				        			String []tempstage5DataArray = stage5Data.split("-sep-");
				        			stageFiveDataArray = new String[tempstage5DataArray.length - 4];
				        			stage5CreateDate = tempstage5DataArray[0];
				        			stage5AcknowledgementDate = tempstage5DataArray[1];
				        			stage5SystemDate = tempstage5DataArray[2];
				        			stage5ApproveStatus = tempstage5DataArray[3];
				        			for(int j=0;j<(tempstage5DataArray.length - 4);j++){
				        				stageFiveDataArray[j] = tempstage5DataArray[4 + j];
				        				System.out.println("DepartmentStateAction-stage5----->stageFiveDataArray["+j+"]-------------->"+stageFiveDataArray[j]);
				        				}
				        		}else{
				        			employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
				    		        request.setAttribute("employeeDetails",null);
				    		        request.setAttribute("employeeDetails",employeeDetails);
				    		        request.setAttribute("pageStatus","init");
				    		        request.setAttribute("stageFiveStatusArray", stageFiveStatusArray);
				    				return new ModelAndView("DOS/frmDepartmentStateStage5");
				        		}
						    }
				        }
			            
			            request.setAttribute("unique_DOS_id", unique_DOS_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("CreateDate", stage5CreateDate);
			            request.setAttribute("AcknowledgementDate", stage5AcknowledgementDate);
			            request.setAttribute("SystemDate", stage5SystemDate);
			            request.setAttribute("ApprovalStatus", stage5ApproveStatus);
			            if(empUniqueId != null){
			            	System.out.println("empUniqueId------------------->"+empUniqueId);
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageFiveDataArray", stageFiveDataArray);
			            request.setAttribute("stageFiveStatusArray", stageFiveStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOS/frmDepartmentStateStage5");
		            }else{
		            	throw new Exception("Information not stored in Server. Please Try again!");
		            }
	            }else{
	            	throw new Exception("Input parameters or Uploaded file are not reached to Server");
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
    	   //request.setAttribute("status","fail");
    	   //request.setAttribute("pageStatus","init");
    	   //return new ModelAndView("DOS/frmDepartmentStatestage5");
	       
	}
	
	@RequestMapping("/initDepartmentStateStage6.html")
	public ModelAndView initDepartmentStateStage6(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			ArrayList DOSStageSixData = new ArrayList();
            DOSStageSixData = db.getDOSStageSixDetailsByUniqueEmpId(empUniqueId);
            
            if(DOSStageSixData!=null && DOSStageSixData.size()!=0){
	            String unique_DOS_id = null;
	        	String emp_unique_id = null;
	        	String stage6StartDate = null;
	 		    String stage6EndDate = null;
	 		    String stage6CreateDate = null;
	 		    String stage6AcknowledgementDate = null;
	 		    String stage6SystemDate = null;
	 		    String stage6ApproveStatus = null;
	        	String stage6Data = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	            String[] stageSixDataArray = null;
	            String[] stageSixStatusArray = null;
	            if(DOSStageSixData!=null && DOSStageSixData.size()!=0){
	            	
					Iterator itr = DOSStageSixData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOS_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage6Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stageSixStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status};
					    
					    if(stage6Data != null && stage6Data != "" && !(stage6Data.equalsIgnoreCase(""))){
		        			String []tempstage6DataArray = stage6Data.split("-sep-");
		        			stageSixDataArray = new String[tempstage6DataArray.length - 6];
		        			stage6StartDate = tempstage6DataArray[0];
		        			stage6EndDate = tempstage6DataArray[1];
		        			stage6CreateDate = tempstage6DataArray[2];
		        			stage6AcknowledgementDate = tempstage6DataArray[3];
		        			stage6SystemDate = tempstage6DataArray[4];
		        			stage6ApproveStatus = tempstage6DataArray[5];
		        			for(int j=0;j<(tempstage6DataArray.length - 6);j++){
		        				stageSixDataArray[j] = tempstage6DataArray[6 + j];
		        				System.out.println("DepartmentStateAction-stage6----->stageSixDataArray["+j+"]-------------->"+stageSixDataArray[j]);
		        				}
		        		}else{
		        			employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		    		        request.setAttribute("employeeDetails",null);
		    		        request.setAttribute("employeeDetails",employeeDetails);
		    		        request.setAttribute("pageStatus","init");
		    		        request.setAttribute("stageSixStatusArray", stageSixStatusArray);
		    				return new ModelAndView("DOS/frmDepartmentStateStage6");
		        		}
				    }
		        }
	            
	            request.setAttribute("unique_DOS_id", unique_DOS_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("StartDate", stage6StartDate);
	            request.setAttribute("EndDate", stage6EndDate);
	            request.setAttribute("CreateDate", stage6CreateDate);
	            request.setAttribute("AcknowledgementDate", stage6AcknowledgementDate);
	            request.setAttribute("SystemDate", stage6SystemDate);
	            request.setAttribute("ApprovalStatus", stage6ApproveStatus);
	            if(empUniqueId != null){
	            	System.out.println("empUniqueId------------------->"+empUniqueId);
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageSixDataArray", stageSixDataArray);
	            request.setAttribute("stageSixStatusArray", stageSixStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOS/frmDepartmentStateStage6");
	        }else{
	        	employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOS/frmDepartmentStateStage6");
	        }
        }else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDepartmentStateStage6.html")
	public ModelAndView doDepartmentStateStage6(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Properties config_property = new Properties();
		config_inputStream = getClass().getClassLoader().getResourceAsStream(config_prop);

		if (config_inputStream != null) {
			config_property.load(config_inputStream);
		} else {
			throw new FileNotFoundException("property file '" + config_prop + "' not found in the classpath");
		}
			
			HttpSession session=request.getSession(true); 
//			  	long creationTime = session.getCreationTime();
//		        String sessionId = session.getId();
//		        long lastAccessedTime = session.getLastAccessedTime();
//		        Date createDate= new Date(creationTime);
//		        Date lastlogin= new Date(lastAccessedTime);
//		        System.out.println("lastAccessedDate"+lastlogin); 
//		        System.out.println("createDate"+createDate); 
		 
    	   try {
    		   GeneralDBAction db=new GeneralDBAction();
    			
    		   String AdditionalDocsStage6Check = null;

    		   String oldAdditionalDocsStage6 = "";
			   
			   String AdditionalDocsStage6File = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String startDate = null;
    		   String endDate = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
   			   ArrayList employeeDetails = new ArrayList();
    		   ArrayList <String>dosFileList = new ArrayList<String>();
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfState");
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
		                	if(parmName.equals("empUniqueId")){
		                		empUniqueId = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("empId")){
		                		empId = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("StartDate")){
		                		startDate = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("EndDate")){
		                		endDate = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("CreateDate")){
		                		createDate = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("AcknowledgementDate")){
		                		acknowledgementDate = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("SystemDate")){
		                		systemDate = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("ApprovalStatus")){
		                		approveStatus = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("AdditionalDocsStage6Check")){
		                		AdditionalDocsStage6Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldAdditionalDocsStage6")){
		                		oldAdditionalDocsStage6 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("AdditionalDocsStage6")) {
					        	  System.out.println("fPart.getFileName() in DOS-Stage6 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  //dosFileList.add(fileLocation);
								  AdditionalDocsStage6File = fileLocation;
					          }
					          if((AdditionalDocsStage6Check == null || AdditionalDocsStage6Check == "" || AdditionalDocsStage6Check.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("AdditionalDocsStage6"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  //System.out.println("oldVisaform-------------in-----else--Stage1 action--->"+oldVisaform);
						        	  System.out.println("oldAdditionalDocsStage6-------------in-----if additionalfileCheck--Stage6 action--->"+oldAdditionalDocsStage6);
						        	  //dosFileList.add(oldAdditionalDocsStage6);
									  AdditionalDocsStage6File = oldAdditionalDocsStage6;
				             }
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && startDate != null && endDate != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null){
	            	
	            	String dosBPELStatus = null;
	            	DOSClientStage6 clientStage6 = new DOSClientStage6();
	            	dosBPELStatus = clientStage6.stage6dos(AdditionalDocsStage6File, startDate, endDate, createDate, acknowledgementDate, systemDate, approveStatus);
	    	            
	            	if(dosBPELStatus != null && dosBPELStatus.equalsIgnoreCase("Success6")){	
	            		insertStatus = db.insertDOSStageSixDetails(empUniqueId,startDate,endDate,createDate,acknowledgementDate,systemDate,
	    	            		approveStatus,AdditionalDocsStage6File);
	    	        }else{
	    	            	throw new Exception("Stage Six Was Failed in BPEL Process");
	    	        }
	            	
	            	
	            System.out.println("insertStatus in DepartmentStateAction-Stage6::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOSStageSixData = new ArrayList();
			            DOSStageSixData = db.getDOSStageSixDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOS_id = null;
		            	String emp_unique_id = null;
		            	String stage6StartDate = null;
		     		    String stage6EndDate = null;
		     		    String stage6CreateDate = null;
		     		    String stage6AcknowledgementDate = null;
		     		    String stage6SystemDate = null;
		     		    String stage6ApproveStatus = null;
		            	String stage6Data = null;
		            	String stage1_status = null;
		            	String stage2_status = null;
		            	String stage3_status = null;
		            	String stage4_status = null;
		            	String stage5_status = null;
		            	String stage6_status = null;
			            String[] stageSixDataArray = null;
			            String[] stageSixStatusArray = null;
			            if(DOSStageSixData!=null && DOSStageSixData.size()!=0){
			            	
							Iterator itr = DOSStageSixData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOS_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage6Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stageSixStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status};
							    
							    if(stage6Data != null && stage6Data != "" && !(stage6Data.equalsIgnoreCase(""))){
				        			String []tempstage6DataArray = stage6Data.split("-sep-");
				        			stageSixDataArray = new String[tempstage6DataArray.length - 6];
				        			stage6StartDate = tempstage6DataArray[0];
				        			stage6EndDate = tempstage6DataArray[1];
				        			stage6CreateDate = tempstage6DataArray[2];
				        			stage6AcknowledgementDate = tempstage6DataArray[3];
				        			stage6SystemDate = tempstage6DataArray[4];
				        			stage6ApproveStatus = tempstage6DataArray[5];
				        			for(int j=0;j<(tempstage6DataArray.length - 6);j++){
				        				stageSixDataArray[j] = tempstage6DataArray[6 + j];
				        				System.out.println("DepartmentStateAction-stage6----->stageSixDataArray["+j+"]-------------->"+stageSixDataArray[j]);
				        				}
				        		}else{
				        			employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
				    		        request.setAttribute("employeeDetails",null);
				    		        request.setAttribute("employeeDetails",employeeDetails);
				    		        request.setAttribute("pageStatus","init");
				    		        request.setAttribute("stageSixStatusArray", stageSixStatusArray);
				    				return new ModelAndView("DOS/frmDepartmentStateStage6");
				        		}
						    }
				        }
			            
			            request.setAttribute("unique_DOS_id", unique_DOS_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("StartDate", stage6StartDate);
			            request.setAttribute("EndDate", stage6EndDate);
			            request.setAttribute("CreateDate", stage6CreateDate);
			            request.setAttribute("AcknowledgementDate", stage6AcknowledgementDate);
			            request.setAttribute("SystemDate", stage6SystemDate);
			            request.setAttribute("ApprovalStatus", stage6ApproveStatus);
			            if(empUniqueId != null){
			            	System.out.println("empUniqueId------------------->"+empUniqueId);
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageSixDataArray", stageSixDataArray);
			            request.setAttribute("stageSixStatusArray", stageSixStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOS/frmDepartmentStateStage6");
		            }else{
		            	throw new Exception("Information not stored in Server. Please Try again!");
		            }
	            }else{
	            	throw new Exception("Input parameters or Uploaded file are not reached to Server");
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
    	   //request.setAttribute("status","fail");
    	   //request.setAttribute("pageStatus","init");
    	   //return new ModelAndView("DOS/frmDepartmentStatestage6");
	       
	}
	
	@RequestMapping("/DownloadFiles.html")
	public ModelAndView DownloadFiles(HttpServletRequest request,
			HttpServletResponse response) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, IOException{
		Debug.print("DepartmentStateAction.DownloadFiles()");
		
		Properties config_property = new Properties();
		config_inputStream = getClass().getClassLoader().getResourceAsStream(config_prop);

		if (config_inputStream != null) {
			config_property.load(config_inputStream);
		} else {
			throw new FileNotFoundException("property file '" + config_prop + "' not found in the classpath");
		}
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		String filePath = null;
		filePath = request.getParameter("filePath");
        
		System.out.println("filePath------in--------DepartmentStateAction.DownloadFiles()-->"+filePath);
		
		final int BUFFER_SIZE = 4096;		    
			  // get absolute path of the application
			     ServletContext context = request.getSession().getServletContext();
			       // String appPath = context.getRealPath("");
			     			 
			        // construct the complete absolute path of the file
			        String fullPath =filePath;      
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
			    
			 
			 return null;//new ModelAndView("approvenroll");	 
	}
}

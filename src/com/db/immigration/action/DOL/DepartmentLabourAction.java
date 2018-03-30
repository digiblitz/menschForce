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
package com.db.immigration.action.DOL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;

import com.db.GeneralDBAction;
//import com.db.immigration.action.DOL.service.client.DOLClientStage1;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

@Controller
public class DepartmentLabourAction {
	
	public static final String config_prop = "config.properties";
	public static final String LOG_FILE = "Log4j.properties";
	InputStream is;
	InputStream config_inputStream;

	@RequestMapping("/initDOLStage1.html")
	public ModelAndView initDOLStage1(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOLStageOneData = new ArrayList();
            DOLStageOneData = db.getDOLStageOneDetailsByUniqueEmpId(empUniqueId);
            
            if(DOLStageOneData != null && DOLStageOneData.size() > 0){
            
	            String unique_DOL_id = null;
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
	        	String stage7_status = null;
	        	String stage8_status = null;
	        	String stage9_status = null;
	        	String stage10_status = null;
	        	String stage11_status = null;
	        	String stage12_status = null;
	        	String stage13_status = null;
	        	String stage14_status = null;
	        	String stage15_status = null;
	        	String stage16_status = null;
	        	String stage17_status = null;
	        	String stage18_status = null;
	        	String stage19_status = null;
	        	String stage20_status = null;
	            String[] stageOneDataArray = null;
	            String[] stageOneStatusArray = null;
	            if(DOLStageOneData!=null && DOLStageOneData.size()!=0){
	            	
					Iterator itr = DOLStageOneData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOL_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage1Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stage7_status = TempList[9];
					    stage8_status = TempList[10];
					    stage9_status = TempList[11];
					    stage10_status = TempList[12];
					    stage11_status = TempList[13];
					    stage12_status = TempList[14];
					    stage13_status = TempList[15];
					    stage14_status = TempList[16];
					    stage15_status = TempList[17];
					    stage16_status = TempList[18];
					    stage17_status = TempList[19];
					    stage18_status = TempList[20];
					    stage19_status = TempList[21];
					    stage20_status = TempList[22];
					    
					    stageOneStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
					    
					    if(stage1Data != null && stage1Data != "" && !(stage1Data.equalsIgnoreCase(""))){
		        			String []tempStage1DataArray = stage1Data.split("-sep-");
		        			stageOneDataArray = new String[tempStage1DataArray.length - 4];
		        			stage1CreateDate = tempStage1DataArray[0];
		        			stage1AcknowledgementDate = tempStage1DataArray[1];
		        			stage1SystemDate = tempStage1DataArray[2];
		        			stage1ApproveStatus = tempStage1DataArray[3];
		        			for(int j=0;j<(tempStage1DataArray.length - 4);j++){
		        				stageOneDataArray[j] = tempStage1DataArray[4 + j];
		        				System.out.println("Department of Labor Action-Stage1----->stageOneDataArray["+j+"]-------------->"+stageOneDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageOneStatusArray", stageOneStatusArray);
		        				return new ModelAndView("DOL/frmDepartmentLabourStage1");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_DOL_id", unique_DOL_id);
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
	    		return new ModelAndView("DOL/frmDepartmentLabourStage1");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOL/frmDepartmentLabourStage1");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDOLStage1.html")
	public ModelAndView doDOLStage1(HttpServletRequest request,
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
    		   
    		   String oldAccess_file = null;
    		   String oldCustomDocs1 = "";
    		   String Access_fileCheck = null;
    		   String CustomDocs1Check = null;
    		   
    		   String Access_fileFile = null;
    		   String CustomDocs1File = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfLabor");
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
		                	if(parmName.equals("Access_fileCheck")){
		                		Access_fileCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldAccess_file")){
		                		oldAccess_file = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("CustomDocs1Check")){
		                		CustomDocs1Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldCustomDocs1")){
		                		oldCustomDocs1 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in DOL-Stage1 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("Access_file")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage1 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  Access_fileFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("CustomDocs1")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage1 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  CustomDocs1File = fileLocation;
					          }
					        
					          System.out.println("File name side Stage1 action --------->"+name);
					        if((Access_fileCheck == null || Access_fileCheck == "" || Access_fileCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("Access_file"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldAccess_file-------------in-----if Access_fileCheck--Stage1 action--->"+oldAccess_file);
						        	  Access_fileFile = oldAccess_file;
				             }
						    if((CustomDocs1Check == null || CustomDocs1Check == "" || CustomDocs1Check.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("CustomDocs1"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldCustomDocs1-------------in-----if additionalfileCheck--Stage1 action--->"+oldCustomDocs1);
						        	  CustomDocs1File = oldCustomDocs1;
				             }
				                  
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && Access_fileFile != null){
	            	
	            	//String dolBPELStatus = null;
	            	//DOLClientStage1 clientStage1 = new DOLClientStage1();
	            	//dolBPELStatus = clientStage1.stage1dos(empUniqueId, visaformFile, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	//if(dolBPELStatus != null && dolBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertDOLStageOneDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,Access_fileFile,CustomDocs1File);
			       // }else{
			        //    	throw new Exception("Stage One Was Failed in BPEL Process");
			       // }
		            
	            System.out.println("insertStatus in Department of Labor Action-Stage1::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOLStageOneData = new ArrayList();
			            DOLStageOneData = db.getDOLStageOneDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOL_id = null;
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
			        	String stage7_status = null;
			        	String stage8_status = null;
			        	String stage9_status = null;
			        	String stage10_status = null;
			        	String stage11_status = null;
			        	String stage12_status = null;
			        	String stage13_status = null;
			        	String stage14_status = null;
			        	String stage15_status = null;
			        	String stage16_status = null;
			        	String stage17_status = null;
			        	String stage18_status = null;
			        	String stage19_status = null;
			        	String stage20_status = null;
			            String[] stageOneDataArray = null;
			            String[] stageOneStatusArray = null;
			            if(DOLStageOneData!=null && DOLStageOneData.size()!=0){
			            	
							Iterator itr = DOLStageOneData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOL_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage1Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stage7_status = TempList[9];
							    stage8_status = TempList[10];
							    stage9_status = TempList[11];
							    stage10_status = TempList[12];
							    stage11_status = TempList[13];
							    stage12_status = TempList[14];
							    stage13_status = TempList[15];
							    stage14_status = TempList[16];
							    stage15_status = TempList[17];
							    stage16_status = TempList[18];
							    stage17_status = TempList[19];
							    stage18_status = TempList[20];
							    stage19_status = TempList[21];
							    stage20_status = TempList[22];
							    stageOneStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
							    
							    if(stage1Data != null && stage1Data != "" && !(stage1Data.equalsIgnoreCase(""))){
				        			String []tempStage1DataArray = stage1Data.split("-sep-");
				        			stageOneDataArray = new String[tempStage1DataArray.length - 4];
				        			stage1CreateDate = tempStage1DataArray[0];
				        			stage1AcknowledgementDate = tempStage1DataArray[1];
				        			stage1SystemDate = tempStage1DataArray[2];
				        			stage1ApproveStatus = tempStage1DataArray[3];
				        			for(int j=0;j<(tempStage1DataArray.length - 4);j++){
				        				stageOneDataArray[j] = tempStage1DataArray[4 + j];
				        				System.out.println("Department of Labor Action-Stage1----->stageOneDataArray["+j+"]-------------->"+stageOneDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOL_id", unique_DOL_id);
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
			    		return new ModelAndView("DOL/frmDepartmentLabourStage1");
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
    	   return new ModelAndView("DOL/frmDepartmentLabourStage1");
	       
	}
	
	@RequestMapping("/initDOLStage2.html")
	public ModelAndView initDOLStage2(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOLStageTwoData = new ArrayList();
			DOLStageTwoData = db.getDOLStageTwoDetailsByUniqueEmpId(empUniqueId);
            
            if(DOLStageTwoData != null && DOLStageTwoData.size() > 0){
            
	            String unique_DOL_id = null;
	        	String emp_unique_id = null;
	        	String stage2CreateDate = null;
	 		    String stage2AcknowledgementDate = null;
	 		    String stage2SystemDate = null;
	 		    String stage2ApproveStatus = null;
	 		    String stage2SalaryLevel = null;
	        	String stage2Data = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	        	String stage7_status = null;
	        	String stage8_status = null;
	        	String stage9_status = null;
	        	String stage10_status = null;
	        	String stage11_status = null;
	        	String stage12_status = null;
	        	String stage13_status = null;
	        	String stage14_status = null;
	        	String stage15_status = null;
	        	String stage16_status = null;
	        	String stage17_status = null;
	        	String stage18_status = null;
	        	String stage19_status = null;
	        	String stage20_status = null;
	            String[] stageTwoDataArray = null;
	            String[] stageTwoStatusArray = null;
	            if(DOLStageTwoData!=null && DOLStageTwoData.size()!=0){
	            	
					Iterator itr = DOLStageTwoData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOL_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage2Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stage7_status = TempList[9];
					    stage8_status = TempList[10];
					    stage9_status = TempList[11];
					    stage10_status = TempList[12];
					    stage11_status = TempList[13];
					    stage12_status = TempList[14];
					    stage13_status = TempList[15];
					    stage14_status = TempList[16];
					    stage15_status = TempList[17];
					    stage16_status = TempList[18];
					    stage17_status = TempList[19];
					    stage18_status = TempList[20];
					    stage19_status = TempList[21];
					    stage20_status = TempList[22];
					    
					    stageTwoStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
					    
					    if(stage2Data != null && stage2Data != "" && !(stage2Data.equalsIgnoreCase(""))){
		        			String []tempStage2DataArray = stage2Data.split("-sep-");
		        			stageTwoDataArray = new String[tempStage2DataArray.length - 4];
		        			stage2CreateDate = tempStage2DataArray[0];
		        			stage2AcknowledgementDate = tempStage2DataArray[1];
		        			stage2SystemDate = tempStage2DataArray[2];
		        			stage2ApproveStatus = tempStage2DataArray[3];
		        			stage2SalaryLevel = tempStage2DataArray[4];
		        			for(int j=0;j<(tempStage2DataArray.length - 5);j++){
		        				stageTwoDataArray[j] = tempStage2DataArray[5 + j];
		        				System.out.println("Department of Labor Action-Stage2----->stageTwoDataArray["+j+"]-------------->"+stageTwoDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageTwoStatusArray", stageTwoStatusArray);
		        				return new ModelAndView("DOL/frmDepartmentLabourStage2");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_DOL_id", unique_DOL_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage2SalaryLevel", stage2SalaryLevel);
	            request.setAttribute("stage2CreateDate", stage2CreateDate);
	            request.setAttribute("stage2AcknowledgementDate", stage2AcknowledgementDate);
	            request.setAttribute("stage2SystemDate", stage2SystemDate);
	            request.setAttribute("stage2ApproveStatus", stage2ApproveStatus);
	            if(empUniqueId != null){
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageTwoDataArray", stageTwoDataArray);
	            request.setAttribute("stageTwoStatusArray", stageTwoStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOL/frmDepartmentLabourStage2");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOL/frmDepartmentLabourStage2");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDOLStage2.html")
	public ModelAndView doDOLStage2(HttpServletRequest request,
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
    		   
    		   String oldJobFormat = null;
    		   String JobFormatCheck = null;
    		   
    		   String JobFormatFile = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfLabor");
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
		                	if(parmName.equals("JobFormatCheck")){
		                		JobFormatCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldJobFormat")){
		                		oldJobFormat = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in DOL-Stage2 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("JobFormat")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage2 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  JobFormatFile = fileLocation;
					          }
					        
					          System.out.println("File name side Stage2 action --------->"+name);
					        if((JobFormatCheck == null || JobFormatCheck == "" || JobFormatCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("JobFormat"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldJobFormat-------------in-----if JobFormatCheck--Stage2 action--->"+oldJobFormat);
						        	  JobFormatFile = oldJobFormat;
				             }
						    
				                  
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && JobFormatFile != null){
	            	
	            	//String dolBPELStatus = null;
	            	//DOLClientStage2 clientStage2 = new DOLClientStage2();
	            	//dolBPELStatus = clientStage2.stage2dos(empUniqueId, visaformFile, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	//if(dolBPELStatus != null && dolBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertDOLStageTwoDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,JobFormatFile);
			       // }else{
			        //    	throw new Exception("Stage Two Was Failed in BPEL Process");
			       // }
		            
	            System.out.println("insertStatus in Department of Labor Action-Stage2::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOLStageTwoData = new ArrayList();
			            DOLStageTwoData = db.getDOLStageTwoDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOL_id = null;
		            	String emp_unique_id = null;
		            	String stage2CreateDate = null;
		     		    String stage2AcknowledgementDate = null;
		     		    String stage2SystemDate = null;
		     		    String stage2ApproveStatus = null;
		     		    String stage2SalaryLevel = null;
		            	String stage2Data = null;
		            	String stage1_status = null;
			        	String stage2_status = null;
			        	String stage3_status = null;
			        	String stage4_status = null;
			        	String stage5_status = null;
			        	String stage6_status = null;
			        	String stage7_status = null;
			        	String stage8_status = null;
			        	String stage9_status = null;
			        	String stage10_status = null;
			        	String stage11_status = null;
			        	String stage12_status = null;
			        	String stage13_status = null;
			        	String stage14_status = null;
			        	String stage15_status = null;
			        	String stage16_status = null;
			        	String stage17_status = null;
			        	String stage18_status = null;
			        	String stage19_status = null;
			        	String stage20_status = null;
			            String[] stageTwoDataArray = null;
			            String[] stageTwoStatusArray = null;
			            if(DOLStageTwoData!=null && DOLStageTwoData.size()!=0){
			            	
							Iterator itr = DOLStageTwoData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOL_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage2Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stage7_status = TempList[9];
							    stage8_status = TempList[10];
							    stage9_status = TempList[11];
							    stage10_status = TempList[12];
							    stage11_status = TempList[13];
							    stage12_status = TempList[14];
							    stage13_status = TempList[15];
							    stage14_status = TempList[16];
							    stage15_status = TempList[17];
							    stage16_status = TempList[18];
							    stage17_status = TempList[19];
							    stage18_status = TempList[20];
							    stage19_status = TempList[21];
							    stage20_status = TempList[22];
							    stageTwoStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
							    
							    if(stage2Data != null && stage2Data != "" && !(stage2Data.equalsIgnoreCase(""))){
				        			String []tempStage2DataArray = stage2Data.split("-sep-");
				        			stageTwoDataArray = new String[tempStage2DataArray.length - 4];
				        			stage2CreateDate = tempStage2DataArray[0];
				        			stage2AcknowledgementDate = tempStage2DataArray[1];
				        			stage2SystemDate = tempStage2DataArray[2];
				        			stage2ApproveStatus = tempStage2DataArray[3];
				        			stage2SalaryLevel = tempStage2DataArray[4];
				        			for(int j=0;j<(tempStage2DataArray.length - 5);j++){
				        				stageTwoDataArray[j] = tempStage2DataArray[5 + j];
				        				System.out.println("Department of Labor Action-Stage2----->stageTwoDataArray["+j+"]-------------->"+stageTwoDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOL_id", unique_DOL_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage2CreateDate", stage2CreateDate);
			            request.setAttribute("stage2AcknowledgementDate", stage2AcknowledgementDate);
			            request.setAttribute("stage2SystemDate", stage2SystemDate);
			            request.setAttribute("stage2ApproveStatus", stage2ApproveStatus);
			            request.setAttribute("stage2SalaryLevel", stage2SalaryLevel);
			            if(empUniqueId != null){
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
			    		return new ModelAndView("DOL/frmDepartmentLabourStage2");
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
    	   return new ModelAndView("DOL/frmDepartmentLabourStage2");
	       
	}
	
	@RequestMapping("/initDOLStage3.html")
	public ModelAndView initDOLStage3(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOLStageThreeData = new ArrayList();
            DOLStageThreeData = db.getDOLStageThreeDetailsByUniqueEmpId(empUniqueId);
            
            if(DOLStageThreeData != null && DOLStageThreeData.size() > 0){
            
	            String unique_DOL_id = null;
	        	String emp_unique_id = null;
	        	String stage3CreateDate = null;
	 		    String stage3AcknowledgementDate = null;
	 		    String stage3SystemDate = null;
	 		    String stage3ApproveStatus = null;
	 		    String stage3ActualWageYesNo = null;
	        	String stage3Data = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	        	String stage7_status = null;
	        	String stage8_status = null;
	        	String stage9_status = null;
	        	String stage10_status = null;
	        	String stage11_status = null;
	        	String stage12_status = null;
	        	String stage13_status = null;
	        	String stage14_status = null;
	        	String stage15_status = null;
	        	String stage16_status = null;
	        	String stage17_status = null;
	        	String stage18_status = null;
	        	String stage19_status = null;
	        	String stage20_status = null;
	            String[] stageThreeDataArray = null;
	            String[] stageThreeStatusArray = null;
	            if(DOLStageThreeData!=null && DOLStageThreeData.size()!=0){
	            	
					Iterator itr = DOLStageThreeData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOL_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage3Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stage7_status = TempList[9];
					    stage8_status = TempList[10];
					    stage9_status = TempList[11];
					    stage10_status = TempList[12];
					    stage11_status = TempList[13];
					    stage12_status = TempList[14];
					    stage13_status = TempList[15];
					    stage14_status = TempList[16];
					    stage15_status = TempList[17];
					    stage16_status = TempList[18];
					    stage17_status = TempList[19];
					    stage18_status = TempList[20];
					    stage19_status = TempList[21];
					    stage20_status = TempList[22];
					    
					    stageThreeStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
					    
					    if(stage3Data != null && stage3Data != "" && !(stage3Data.equalsIgnoreCase(""))){
		        			String []tempStage3DataArray = stage3Data.split("-sep-");
		        			stageThreeDataArray = new String[tempStage3DataArray.length - 4];
		        			stage3CreateDate = tempStage3DataArray[0];
		        			stage3AcknowledgementDate = tempStage3DataArray[1];
		        			stage3SystemDate = tempStage3DataArray[2];
		        			stage3ApproveStatus = tempStage3DataArray[3];
		        			stage3ActualWageYesNo = tempStage3DataArray[4];
		        			
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageThreeStatusArray", stageThreeStatusArray);
		        				return new ModelAndView("DOL/frmDepartmentLabourStage3");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_DOL_id", unique_DOL_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage3CreateDate", stage3CreateDate);
	            request.setAttribute("stage3AcknowledgementDate", stage3AcknowledgementDate);
	            request.setAttribute("stage3SystemDate", stage3SystemDate);
	            request.setAttribute("stage3ApproveStatus", stage3ApproveStatus);
	            request.setAttribute("stage3ActualWageYesNo", stage3ActualWageYesNo);
	            if(empUniqueId != null){
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageThreeStatusArray", stageThreeStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOL/frmDepartmentLabourStage3");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOL/frmDepartmentLabourStage3");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDOLStage3.html")
	public ModelAndView doDOLStage3(HttpServletRequest request,
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
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String ActualWageYesNo = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
    		        
        	   String status = null;	
        	   
        	    empUniqueId  = request.getParameter("empUniqueId");
	       		empId  = request.getParameter("empId");
	       		ActualWageYesNo  = request.getParameter("ActualWageYesNo");
	       		createDate  = request.getParameter("CreateDate");
	       		acknowledgementDate  = request.getParameter("AcknowledgementDate");
	       		systemDate  = request.getParameter("SystemDate");
	       		approveStatus  = request.getParameter("ApprovalStatus");
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && ActualWageYesNo != null){
	            	
	            	//String dolBPELStatus = null;
	            	//DOLClientStage3 clientStage3 = new DOLClientStage3();
	            	//dolBPELStatus = clientStage3.stage3dos(empUniqueId, visaformFile, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	//if(dolBPELStatus != null && dolBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertDOLStageThreeDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,ActualWageYesNo);
			       // }else{
			        //    	throw new Exception("Stage Three Was Failed in BPEL Process");
			       // }
		            
	            System.out.println("insertStatus in Department of Labor Action-Stage3::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOLStageThreeData = new ArrayList();
			            DOLStageThreeData = db.getDOLStageThreeDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOL_id = null;
		            	String emp_unique_id = null;
		            	String stage3ActualWageYesNo = null;
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
			        	String stage7_status = null;
			        	String stage8_status = null;
			        	String stage9_status = null;
			        	String stage10_status = null;
			        	String stage11_status = null;
			        	String stage12_status = null;
			        	String stage13_status = null;
			        	String stage14_status = null;
			        	String stage15_status = null;
			        	String stage16_status = null;
			        	String stage17_status = null;
			        	String stage18_status = null;
			        	String stage19_status = null;
			        	String stage20_status = null;
			            String[] stageThreeDataArray = null;
			            String[] stageThreeStatusArray = null;
			            if(DOLStageThreeData!=null && DOLStageThreeData.size()!=0){
			            	
							Iterator itr = DOLStageThreeData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOL_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage3Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stage7_status = TempList[9];
							    stage8_status = TempList[10];
							    stage9_status = TempList[11];
							    stage10_status = TempList[12];
							    stage11_status = TempList[13];
							    stage12_status = TempList[14];
							    stage13_status = TempList[15];
							    stage14_status = TempList[16];
							    stage15_status = TempList[17];
							    stage16_status = TempList[18];
							    stage17_status = TempList[19];
							    stage18_status = TempList[20];
							    stage19_status = TempList[21];
							    stage20_status = TempList[22];
							    stageThreeStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
							    
							    if(stage3Data != null && stage3Data != "" && !(stage3Data.equalsIgnoreCase(""))){
				        			String []tempStage3DataArray = stage3Data.split("-sep-");
				        			stageThreeDataArray = new String[tempStage3DataArray.length - 4];
				        			stage3CreateDate = tempStage3DataArray[0];
				        			stage3AcknowledgementDate = tempStage3DataArray[1];
				        			stage3SystemDate = tempStage3DataArray[2];
				        			stage3ApproveStatus = tempStage3DataArray[3];
				        			stage3ActualWageYesNo = tempStage3DataArray[4];
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOL_id", unique_DOL_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage3CreateDate", stage3CreateDate);
			            request.setAttribute("stage3AcknowledgementDate", stage3AcknowledgementDate);
			            request.setAttribute("stage3SystemDate", stage3SystemDate);
			            request.setAttribute("stage3ApproveStatus", stage3ApproveStatus);
			            request.setAttribute("stage3ActualWageYesNo", stage3ActualWageYesNo);
			            if(empUniqueId != null){
			    			ArrayList employeeDetails = new ArrayList();
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageThreeStatusArray", stageThreeStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOL/frmDepartmentLabourStage3");
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
    	   return new ModelAndView("DOL/frmDepartmentLabourStage3");
	       
	}
	
	@RequestMapping("/initDOLStage4.html")
	public ModelAndView initDOLStage4(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOLStageFourData = new ArrayList();
            DOLStageFourData = db.getDOLStageFourDetailsByUniqueEmpId(empUniqueId);
            
            if(DOLStageFourData != null && DOLStageFourData.size() > 0){
            
	            String unique_DOL_id = null;
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
	        	String stage7_status = null;
	        	String stage8_status = null;
	        	String stage9_status = null;
	        	String stage10_status = null;
	        	String stage11_status = null;
	        	String stage12_status = null;
	        	String stage13_status = null;
	        	String stage14_status = null;
	        	String stage15_status = null;
	        	String stage16_status = null;
	        	String stage17_status = null;
	        	String stage18_status = null;
	        	String stage19_status = null;
	        	String stage20_status = null;
	            String[] stageFourDataArray = null;
	            String[] stageFourStatusArray = null;
	            if(DOLStageFourData!=null && DOLStageFourData.size()!=0){
	            	
					Iterator itr = DOLStageFourData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOL_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage4Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stage7_status = TempList[9];
					    stage8_status = TempList[10];
					    stage9_status = TempList[11];
					    stage10_status = TempList[12];
					    stage11_status = TempList[13];
					    stage12_status = TempList[14];
					    stage13_status = TempList[15];
					    stage14_status = TempList[16];
					    stage15_status = TempList[17];
					    stage16_status = TempList[18];
					    stage17_status = TempList[19];
					    stage18_status = TempList[20];
					    stage19_status = TempList[21];
					    stage20_status = TempList[22];
					    
					    stageFourStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
					    
					    if(stage4Data != null && stage4Data != "" && !(stage4Data.equalsIgnoreCase(""))){
		        			String []tempStage4DataArray = stage4Data.split("-sep-");
		        			stageFourDataArray = new String[tempStage4DataArray.length - 4];
		        			stage4CreateDate = tempStage4DataArray[0];
		        			stage4AcknowledgementDate = tempStage4DataArray[1];
		        			stage4SystemDate = tempStage4DataArray[2];
		        			stage4ApproveStatus = tempStage4DataArray[3];
		        			for(int j=0;j<(tempStage4DataArray.length - 4);j++){
		        				stageFourDataArray[j] = tempStage4DataArray[4 + j];
		        				System.out.println("Department of Labor Action-Stage4----->stageFourDataArray["+j+"]-------------->"+stageFourDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageFourStatusArray", stageFourStatusArray);
		        				return new ModelAndView("DOL/frmDepartmentLabourStage4");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_DOL_id", unique_DOL_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage4CreateDate", stage4CreateDate);
	            request.setAttribute("stage4AcknowledgementDate", stage4AcknowledgementDate);
	            request.setAttribute("stage4SystemDate", stage4SystemDate);
	            request.setAttribute("stage4ApproveStatus", stage4ApproveStatus);
	            if(empUniqueId != null){
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageFourDataArray", stageFourDataArray);
	            request.setAttribute("stageFourStatusArray", stageFourStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOL/frmDepartmentLabourStage4");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOL/frmDepartmentLabourStage4");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDOLStage4.html")
	public ModelAndView doDOLStage4(HttpServletRequest request,
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
    		   
    		   String oldStateDeptLabour = null;
    		   String oldNewsPaper = null;
			   String oldOnlineJobPortal = null;
    		   String StateDeptLabourCheck = null;
    		   String NewsPaperCheck = null;
			   String OnlineJobPortalCheck = null;
    		   
    		   String StateDeptLabourFile = null;
    		   String NewsPaperFile = null;
			   String OnlineJobPortalFile = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfLabor");
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
		                	if(parmName.equals("StateDeptLabourCheck")){
		                		StateDeptLabourCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldStateDeptLabour")){
		                		oldStateDeptLabour = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("NewsPaperCheck")){
		                		NewsPaperCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldNewsPaper")){
		                		oldNewsPaper = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
							if(parmName.equals("OnlineJobPortalCheck")){
		                		OnlineJobPortalCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldOnlineJobPortal")){
		                		oldOnlineJobPortal = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in DOL-Stage4 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("StateDeptLabour")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage4 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  StateDeptLabourFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("NewsPaper")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage4 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  NewsPaperFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("OnlineJobPortal")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage4 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  OnlineJobPortalFile = fileLocation;
					          }
					        
					          System.out.println("File name side Stage4 action --------->"+name);
					        if((StateDeptLabourCheck == null || StateDeptLabourCheck == "" || StateDeptLabourCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("StateDeptLabour"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldStateDeptLabour-------------in-----if StateDeptLabourCheck--Stage4 action--->"+oldStateDeptLabour);
						        	  StateDeptLabourFile = oldStateDeptLabour;
				             }
						    if((NewsPaperCheck == null || NewsPaperCheck == "" || NewsPaperCheck.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("NewsPaper"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldNewsPaper-------------in-----if NewsPaperCheck--Stage4 action--->"+oldNewsPaper);
						        	  NewsPaperFile = oldNewsPaper;
				             }
							 if((OnlineJobPortalCheck == null || OnlineJobPortalCheck == "" || OnlineJobPortalCheck.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("OnlineJobPortal"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldOnlineJobPortal-------------in-----if OnlineJobPortalCheck--Stage4 action--->"+oldOnlineJobPortal);
						        	  OnlineJobPortalFile = oldOnlineJobPortal;
				             }
				                  
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && StateDeptLabourFile != null && NewsPaperFile != null && OnlineJobPortalFile != null){
	            	
	            	//String dolBPELStatus = null;
	            	//DOLClientStage4 clientStage4 = new DOLClientStage4();
	            	//dolBPELStatus = clientStage4.stage4dos(empUniqueId, visaformFile, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	//if(dolBPELStatus != null && dolBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertDOLStageFourDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,StateDeptLabourFile,NewsPaperFile,OnlineJobPortalFile);
			       // }else{
			        //    	throw new Exception("Stage Four Was Failed in BPEL Process");
			       // }
		            
	            System.out.println("insertStatus in Department of Labor Action-Stage4::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOLStageFourData = new ArrayList();
			            DOLStageFourData = db.getDOLStageFourDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOL_id = null;
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
			        	String stage7_status = null;
			        	String stage8_status = null;
			        	String stage9_status = null;
			        	String stage10_status = null;
			        	String stage11_status = null;
			        	String stage12_status = null;
			        	String stage13_status = null;
			        	String stage14_status = null;
			        	String stage15_status = null;
			        	String stage16_status = null;
			        	String stage17_status = null;
			        	String stage18_status = null;
			        	String stage19_status = null;
			        	String stage20_status = null;
			            String[] stageFourDataArray = null;
			            String[] stageFourStatusArray = null;
			            if(DOLStageFourData!=null && DOLStageFourData.size()!=0){
			            	
							Iterator itr = DOLStageFourData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOL_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage4Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stage7_status = TempList[9];
							    stage8_status = TempList[10];
							    stage9_status = TempList[11];
							    stage10_status = TempList[12];
							    stage11_status = TempList[13];
							    stage12_status = TempList[14];
							    stage13_status = TempList[15];
							    stage14_status = TempList[16];
							    stage15_status = TempList[17];
							    stage16_status = TempList[18];
							    stage17_status = TempList[19];
							    stage18_status = TempList[20];
							    stage19_status = TempList[21];
							    stage20_status = TempList[22];
							    stageFourStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
							    
							    if(stage4Data != null && stage4Data != "" && !(stage4Data.equalsIgnoreCase(""))){
				        			String []tempStage4DataArray = stage4Data.split("-sep-");
				        			stageFourDataArray = new String[tempStage4DataArray.length - 4];
				        			stage4CreateDate = tempStage4DataArray[0];
				        			stage4AcknowledgementDate = tempStage4DataArray[1];
				        			stage4SystemDate = tempStage4DataArray[2];
				        			stage4ApproveStatus = tempStage4DataArray[3];
				        			for(int j=0;j<(tempStage4DataArray.length - 4);j++){
				        				stageFourDataArray[j] = tempStage4DataArray[4 + j];
				        				System.out.println("Department of Labor Action-Stage4----->stageFourDataArray["+j+"]-------------->"+stageFourDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOL_id", unique_DOL_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage4CreateDate", stage4CreateDate);
			            request.setAttribute("stage4AcknowledgementDate", stage4AcknowledgementDate);
			            request.setAttribute("stage4SystemDate", stage4SystemDate);
			            request.setAttribute("stage4ApproveStatus", stage4ApproveStatus);
			            if(empUniqueId != null){
			    			ArrayList employeeDetails = new ArrayList();
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageFourDataArray", stageFourDataArray);
			            request.setAttribute("stageFourStatusArray", stageFourStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOL/frmDepartmentLabourStage4");
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
    	   return new ModelAndView("DOL/frmDepartmentLabourStage4");
	       
	}
	
	@RequestMapping("/initDOLStage5.html")
	public ModelAndView initDOLStage5(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOLStageFiveData = new ArrayList();
            DOLStageFiveData = db.getDOLStageFourDetailsByUniqueEmpId(empUniqueId);
            
            if(DOLStageFiveData != null && DOLStageFiveData.size() > 0){
            
	            String unique_DOL_id = null;
	        	String emp_unique_id = null;
	        	String stage5CreateDate = null;
	 		    String stage5AcknowledgementDate = null;
	 		    String stage5SystemDate = null;
	 		    String stage5ApproveStatus = null;
				String stage5NoOfDays = null;
	 		    String stage5FromDate = null;
				String stage5ToDate = null;
	        	String stage5Data = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	        	String stage7_status = null;
	        	String stage8_status = null;
	        	String stage9_status = null;
	        	String stage10_status = null;
	        	String stage11_status = null;
	        	String stage12_status = null;
	        	String stage13_status = null;
	        	String stage14_status = null;
	        	String stage15_status = null;
	        	String stage16_status = null;
	        	String stage17_status = null;
	        	String stage18_status = null;
	        	String stage19_status = null;
	        	String stage20_status = null;
	            String[] stageFiveDataArray = null;
	            String[] stageFiveStatusArray = null;
	            if(DOLStageFiveData!=null && DOLStageFiveData.size()!=0){
	            	
					Iterator itr = DOLStageFiveData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOL_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage5Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stage7_status = TempList[9];
					    stage8_status = TempList[10];
					    stage9_status = TempList[11];
					    stage10_status = TempList[12];
					    stage11_status = TempList[13];
					    stage12_status = TempList[14];
					    stage13_status = TempList[15];
					    stage14_status = TempList[16];
					    stage15_status = TempList[17];
					    stage16_status = TempList[18];
					    stage17_status = TempList[19];
					    stage18_status = TempList[20];
					    stage19_status = TempList[21];
					    stage20_status = TempList[22];
					    
					    stageFiveStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
					    
					    if(stage5Data != null && stage5Data != "" && !(stage5Data.equalsIgnoreCase(""))){
		        			String []tempStage5DataArray = stage5Data.split("-sep-");
		        			stageFiveDataArray = new String[tempStage5DataArray.length - 4];
		        			stage5CreateDate = tempStage5DataArray[0];
		        			stage5AcknowledgementDate = tempStage5DataArray[1];
		        			stage5SystemDate = tempStage5DataArray[2];
		        			stage5ApproveStatus = tempStage5DataArray[3];
							stage5NoOfDays = tempStage5DataArray[4];
							stage5FromDate = tempStage5DataArray[5];
							stage5ToDate = tempStage5DataArray[6];
		        			for(int j=0;j<(tempStage5DataArray.length - 7);j++){
		        				stageFiveDataArray[j] = tempStage5DataArray[7 + j];
		        				System.out.println("Department of Labor Action-Stage5----->stageFiveDataArray["+j+"]-------------->"+stageFiveDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageFiveStatusArray", stageFiveStatusArray);
		        				return new ModelAndView("DOL/frmDepartmentLabourStage5");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_DOL_id", unique_DOL_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage5CreateDate", stage5CreateDate);
	            request.setAttribute("stage5AcknowledgementDate", stage5AcknowledgementDate);
	            request.setAttribute("stage5SystemDate", stage5SystemDate);
	            request.setAttribute("stage5ApproveStatus", stage5ApproveStatus);
				request.setAttribute("stage5NoOfDays", stage5NoOfDays);
	            request.setAttribute("stage5FromDate", stage5FromDate);
	            request.setAttribute("stage5ToDate", stage5ToDate);
	            if(empUniqueId != null){
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageFiveDataArray", stageFiveDataArray);
	            request.setAttribute("stageFiveStatusArray", stageFiveStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOL/frmDepartmentLabourStage5");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOL/frmDepartmentLabourStage5");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDOLStage5.html")
	public ModelAndView doDOLStage5(HttpServletRequest request,
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
    		   
    		   String oldCommunicationDet = null;
    		   String oldCustomDocsStage5 = "";
    		   String CommunicationDetCheck = null;
    		   String CustomDocsStage5Check = null;
    		   
    		   String CommunicationDetFile = null;
    		   String CustomDocsStage5File = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfLabor");
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
		                	if(parmName.equals("CommunicationDetCheck")){
		                		CommunicationDetCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldCommunicationDet")){
		                		oldCommunicationDet = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("CustomDocsStage5Check")){
		                		CustomDocsStage5Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldCustomDocsStage5")){
		                		oldCustomDocsStage5 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in DOL-Stage5 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("CommunicationDet")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage5 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  CommunicationDetFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("CustomDocsStage5")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage5 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  CustomDocsStage5File = fileLocation;
					          }
					        
					          System.out.println("File name side Stage5 action --------->"+name);
					        if((CommunicationDetCheck == null || CommunicationDetCheck == "" || CommunicationDetCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("CommunicationDet"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldCommunicationDet-------------in-----if CommunicationDetCheck--Stage5 action--->"+oldCommunicationDet);
						        	  CommunicationDetFile = oldCommunicationDet;
				             }
						    if((CustomDocsStage5Check == null || CustomDocsStage5Check == "" || CustomDocsStage5Check.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("CustomDocsStage5"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldCustomDocsStage5-------------in-----if CustomDocsStage5Check--Stage5 action--->"+oldCustomDocsStage5);
						        	  CustomDocsStage5File = oldCustomDocsStage5;
				             }
				                  
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && CommunicationDetFile != null){
	            	
	            	//String dolBPELStatus = null;
	            	//DOLClientStage5 clientStage5 = new DOLClientStage5();
	            	//dolBPELStatus = clientStage5.stage5dos(empUniqueId, visaformFile, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	//if(dolBPELStatus != null && dolBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertDOLStageFiveDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,CommunicationDetFile,CustomDocsStage5File);
			       // }else{
			        //    	throw new Exception("Stage Five Was Failed in BPEL Process");
			       // }
		            
	            System.out.println("insertStatus in Department of Labor Action-Stage5::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOLStageFiveData = new ArrayList();
			            DOLStageFiveData = db.getDOLStageFiveDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOL_id = null;
		            	String emp_unique_id = null;
		            	String stage5CreateDate = null;
		     		    String stage5AcknowledgementDate = null;
		     		    String stage5SystemDate = null;
		     		    String stage5ApproveStatus = null;
						String stage5NoOfDays = null;
						String stage5FromDate = null;
						String stage5ToDate = null;
		            	String stage5Data = null;
		            	String stage1_status = null;
			        	String stage2_status = null;
			        	String stage3_status = null;
			        	String stage4_status = null;
			        	String stage5_status = null;
			        	String stage6_status = null;
			        	String stage7_status = null;
			        	String stage8_status = null;
			        	String stage9_status = null;
			        	String stage10_status = null;
			        	String stage11_status = null;
			        	String stage12_status = null;
			        	String stage13_status = null;
			        	String stage14_status = null;
			        	String stage15_status = null;
			        	String stage16_status = null;
			        	String stage17_status = null;
			        	String stage18_status = null;
			        	String stage19_status = null;
			        	String stage20_status = null;
			            String[] stageFiveDataArray = null;
			            String[] stageFiveStatusArray = null;
			            if(DOLStageFiveData!=null && DOLStageFiveData.size()!=0){
			            	
							Iterator itr = DOLStageFiveData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOL_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage5Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stage7_status = TempList[9];
							    stage8_status = TempList[10];
							    stage9_status = TempList[11];
							    stage10_status = TempList[12];
							    stage11_status = TempList[13];
							    stage12_status = TempList[14];
							    stage13_status = TempList[15];
							    stage14_status = TempList[16];
							    stage15_status = TempList[17];
							    stage16_status = TempList[18];
							    stage17_status = TempList[19];
							    stage18_status = TempList[20];
							    stage19_status = TempList[21];
							    stage20_status = TempList[22];
							    stageFiveStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
							    
							    if(stage5Data != null && stage5Data != "" && !(stage5Data.equalsIgnoreCase(""))){
				        			String []tempStage5DataArray = stage5Data.split("-sep-");
				        			stageFiveDataArray = new String[tempStage5DataArray.length - 4];
				        			stage5CreateDate = tempStage5DataArray[0];
				        			stage5AcknowledgementDate = tempStage5DataArray[1];
				        			stage5SystemDate = tempStage5DataArray[2];
				        			stage5ApproveStatus = tempStage5DataArray[3];
									stage5NoOfDays = tempStage5DataArray[4];
									stage5FromDate = tempStage5DataArray[5];
									stage5ToDate = tempStage5DataArray[6];
				        			for(int j=0;j<(tempStage5DataArray.length - 7);j++){
				        				stageFiveDataArray[j] = tempStage5DataArray[7 + j];
				        				System.out.println("Department of Labor Action-Stage5----->stageFiveDataArray["+j+"]-------------->"+stageFiveDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOL_id", unique_DOL_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage5CreateDate", stage5CreateDate);
			            request.setAttribute("stage5AcknowledgementDate", stage5AcknowledgementDate);
			            request.setAttribute("stage5SystemDate", stage5SystemDate);
			            request.setAttribute("stage5ApproveStatus", stage5ApproveStatus);
						request.setAttribute("stage5NoOfDays", stage5NoOfDays);
						request.setAttribute("stage5FromDate", stage5FromDate);
						request.setAttribute("stage5ToDate", stage5ToDate);
			            if(empUniqueId != null){
			    			ArrayList employeeDetails = new ArrayList();
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageFiveDataArray", stageFiveDataArray);
			            request.setAttribute("stageFiveStatusArray", stageFiveStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOL/frmDepartmentLabourStage5");
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
    	   return new ModelAndView("DOL/frmDepartmentLabourStage5");
	       
	}
	
	@RequestMapping("/initDOLStage6.html")
	public ModelAndView initDOLStage6(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOLStageSixData = new ArrayList();
            DOLStageSixData = db.getDOLStageSixDetailsByUniqueEmpId(empUniqueId);
            
            if(DOLStageSixData != null && DOLStageSixData.size() > 0){
            
	            String unique_DOL_id = null;
	        	String emp_unique_id = null;
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
	        	String stage7_status = null;
	        	String stage8_status = null;
	        	String stage9_status = null;
	        	String stage10_status = null;
	        	String stage11_status = null;
	        	String stage12_status = null;
	        	String stage13_status = null;
	        	String stage14_status = null;
	        	String stage15_status = null;
	        	String stage16_status = null;
	        	String stage17_status = null;
	        	String stage18_status = null;
	        	String stage19_status = null;
	        	String stage20_status = null;
	            String[] stageSixDataArray = null;
	            String[] stageSixStatusArray = null;
	            if(DOLStageSixData!=null && DOLStageSixData.size()!=0){
	            	
					Iterator itr = DOLStageSixData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOL_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage6Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stage7_status = TempList[9];
					    stage8_status = TempList[10];
					    stage9_status = TempList[11];
					    stage10_status = TempList[12];
					    stage11_status = TempList[13];
					    stage12_status = TempList[14];
					    stage13_status = TempList[15];
					    stage14_status = TempList[16];
					    stage15_status = TempList[17];
					    stage16_status = TempList[18];
					    stage17_status = TempList[19];
					    stage18_status = TempList[20];
					    stage19_status = TempList[21];
					    stage20_status = TempList[22];
					    
					    stageSixStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
					    
					    if(stage6Data != null && stage6Data != "" && !(stage6Data.equalsIgnoreCase(""))){
		        			String []tempStage6DataArray = stage6Data.split("-sep-");
		        			stageSixDataArray = new String[tempStage6DataArray.length - 4];
		        			stage6CreateDate = tempStage6DataArray[0];
		        			stage6AcknowledgementDate = tempStage6DataArray[1];
		        			stage6SystemDate = tempStage6DataArray[2];
		        			stage6ApproveStatus = tempStage6DataArray[3];
		        			for(int j=0;j<(tempStage6DataArray.length - 4);j++){
		        				stageSixDataArray[j] = tempStage6DataArray[4 + j];
		        				System.out.println("Department of Labor Action-Stage6----->stageSixDataArray["+j+"]-------------->"+stageSixDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageSixStatusArray", stageSixStatusArray);
		        				return new ModelAndView("DOL/frmDepartmentLabourStage6");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_DOL_id", unique_DOL_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage6CreateDate", stage6CreateDate);
	            request.setAttribute("stage6AcknowledgementDate", stage6AcknowledgementDate);
	            request.setAttribute("stage6SystemDate", stage6SystemDate);
	            request.setAttribute("stage6ApproveStatus", stage6ApproveStatus);
	            if(empUniqueId != null){
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageSixDataArray", stageSixDataArray);
	            request.setAttribute("stageSixStatusArray", stageSixStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOL/frmDepartmentLabourStage6");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOL/frmDepartmentLabourStage6");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDOLStage6.html")
	public ModelAndView doDOLStage6(HttpServletRequest request,
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
    		   
    		   String oldEducationQualify = null;
			   String oldExperienceSkill = null;
    		   String oldCustomDocsStage6 = "";
    		   String EducationQualifyCheck = null;
			   String ExperienceSkillCheck = null;
    		   String CustomDocsStage6Check = null;
    		   
    		   String EducationQualifyFile = null;
			   String ExperienceSkillFile = null;
    		   String CustomDocsStage6File = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfLabor");
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
		                	if(parmName.equals("EducationQualifyCheck")){
		                		EducationQualifyCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldEducationQualify")){
		                		oldEducationQualify = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("ExperienceSkillCheck")){
		                		ExperienceSkillCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldExperienceSkill")){
		                		oldExperienceSkill = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
							if(parmName.equals("CustomDocsStage6Check")){
		                		CustomDocsStage6Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldCustomDocsStage6")){
		                		oldCustomDocsStage6 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in DOL-Stage6 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("Access_file")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage6 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  EducationQualifyFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("CustomDocs1")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage6 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  ExperienceSkillFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("CustomDocs1")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage6 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  CustomDocsStage6File = fileLocation;
					          }
					        
					          System.out.println("File name side Stage6 action --------->"+name);
					        if((EducationQualifyCheck == null || EducationQualifyCheck == "" || EducationQualifyCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("Access_file"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldEducationQualify-------------in-----if EducationQualifyCheck--Stage6 action--->"+oldEducationQualify);
						        	  EducationQualifyFile = oldEducationQualify;
				             }
						    if((ExperienceSkillCheck == null || ExperienceSkillCheck == "" || ExperienceSkillCheck.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("CustomDocs1"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldExperienceSkill-------------in-----if ExperienceSkillCheck--Stage6 action--->"+oldExperienceSkill);
						        	  ExperienceSkillFile = oldExperienceSkill;
				             }
							 if((CustomDocsStage6Check == null || CustomDocsStage6Check == "" || CustomDocsStage6Check.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("CustomDocs1"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldCustomDocsStage6-------------in-----if CustomDocsStage6Check--Stage6 action--->"+oldCustomDocsStage6);
						        	  CustomDocsStage6File = oldCustomDocsStage6;
				             }
				                  
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && EducationQualifyFile != null && ExperienceSkillFile != null){
	            	
	            	//String dolBPELStatus = null;
	            	//DOLClientStage6 clientStage6 = new DOLClientStage6();
	            	//dolBPELStatus = clientStage6.stage6dos(empUniqueId, visaformFile, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	//if(dolBPELStatus != null && dolBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertDOLStageSixDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,EducationQualifyFile,ExperienceSkillFile,CustomDocsStage6File);
			       // }else{
			        //    	throw new Exception("Stage Six Was Failed in BPEL Process");
			       // }
		            
	            System.out.println("insertStatus in Department of Labor Action-Stage6::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOLStageSixData = new ArrayList();
			            DOLStageSixData = db.getDOLStageSixDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOL_id = null;
		            	String emp_unique_id = null;
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
			        	String stage7_status = null;
			        	String stage8_status = null;
			        	String stage9_status = null;
			        	String stage10_status = null;
			        	String stage11_status = null;
			        	String stage12_status = null;
			        	String stage13_status = null;
			        	String stage14_status = null;
			        	String stage15_status = null;
			        	String stage16_status = null;
			        	String stage17_status = null;
			        	String stage18_status = null;
			        	String stage19_status = null;
			        	String stage20_status = null;
			            String[] stageSixDataArray = null;
			            String[] stageSixStatusArray = null;
			            if(DOLStageSixData!=null && DOLStageSixData.size()!=0){
			            	
							Iterator itr = DOLStageSixData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOL_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage6Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stage7_status = TempList[9];
							    stage8_status = TempList[10];
							    stage9_status = TempList[11];
							    stage10_status = TempList[12];
							    stage11_status = TempList[13];
							    stage12_status = TempList[14];
							    stage13_status = TempList[15];
							    stage14_status = TempList[16];
							    stage15_status = TempList[17];
							    stage16_status = TempList[18];
							    stage17_status = TempList[19];
							    stage18_status = TempList[20];
							    stage19_status = TempList[21];
							    stage20_status = TempList[22];
							    stageSixStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
							    
							    if(stage6Data != null && stage6Data != "" && !(stage6Data.equalsIgnoreCase(""))){
				        			String []tempStage6DataArray = stage6Data.split("-sep-");
				        			stageSixDataArray = new String[tempStage6DataArray.length - 4];
				        			stage6CreateDate = tempStage6DataArray[0];
				        			stage6AcknowledgementDate = tempStage6DataArray[1];
				        			stage6SystemDate = tempStage6DataArray[2];
				        			stage6ApproveStatus = tempStage6DataArray[3];
				        			for(int j=0;j<(tempStage6DataArray.length - 4);j++){
				        				stageSixDataArray[j] = tempStage6DataArray[4 + j];
				        				System.out.println("Department of Labor Action-Stage6----->stageSixDataArray["+j+"]-------------->"+stageSixDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOL_id", unique_DOL_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage6CreateDate", stage6CreateDate);
			            request.setAttribute("stage6AcknowledgementDate", stage6AcknowledgementDate);
			            request.setAttribute("stage6SystemDate", stage6SystemDate);
			            request.setAttribute("stage6ApproveStatus", stage6ApproveStatus);
			            if(empUniqueId != null){
			    			ArrayList employeeDetails = new ArrayList();
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageSixDataArray", stageSixDataArray);
			            request.setAttribute("stageSixStatusArray", stageSixStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOL/frmDepartmentLabourStage6");
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
    	   return new ModelAndView("DOL/frmDepartmentLabourStage6");
	       
	}
	
	@RequestMapping("/initDOLStage7.html")
	public ModelAndView initDOLStage7(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOLStageSevenData = new ArrayList();
            DOLStageSevenData = db.getDOLStageSixDetailsByUniqueEmpId(empUniqueId);
            
            if(DOLStageSevenData != null && DOLStageSevenData.size() > 0){
            
	            String unique_DOL_id = null;
	        	String emp_unique_id = null;
	        	String stage7CreateDate = null;
	 		    String stage7AcknowledgementDate = null;
	 		    String stage7SystemDate = null;
	 		    String stage7ApproveStatus = null;
	        	String stage7Data = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	        	String stage7_status = null;
	        	String stage8_status = null;
	        	String stage9_status = null;
	        	String stage10_status = null;
	        	String stage11_status = null;
	        	String stage12_status = null;
	        	String stage13_status = null;
	        	String stage14_status = null;
	        	String stage15_status = null;
	        	String stage16_status = null;
	        	String stage17_status = null;
	        	String stage18_status = null;
	        	String stage19_status = null;
	        	String stage20_status = null;
	            String[] stageSevenDataArray = null;
	            String[] stageSevenStatusArray = null;
	            if(DOLStageSevenData!=null && DOLStageSevenData.size()!=0){
	            	
					Iterator itr = DOLStageSevenData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOL_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage7Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stage7_status = TempList[9];
					    stage8_status = TempList[10];
					    stage9_status = TempList[11];
					    stage10_status = TempList[12];
					    stage11_status = TempList[13];
					    stage12_status = TempList[14];
					    stage13_status = TempList[15];
					    stage14_status = TempList[16];
					    stage15_status = TempList[17];
					    stage16_status = TempList[18];
					    stage17_status = TempList[19];
					    stage18_status = TempList[20];
					    stage19_status = TempList[21];
					    stage20_status = TempList[22];
					    
					    stageSevenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
					    
					    if(stage7Data != null && stage7Data != "" && !(stage7Data.equalsIgnoreCase(""))){
		        			String []tempStage7DataArray = stage7Data.split("-sep-");
		        			stageSevenDataArray = new String[tempStage7DataArray.length - 4];
		        			stage7CreateDate = tempStage7DataArray[0];
		        			stage7AcknowledgementDate = tempStage7DataArray[1];
		        			stage7SystemDate = tempStage7DataArray[2];
		        			stage7ApproveStatus = tempStage7DataArray[3];
		        			for(int j=0;j<(tempStage7DataArray.length - 4);j++){
		        				stageSevenDataArray[j] = tempStage7DataArray[4 + j];
		        				System.out.println("Department of Labor Action-Stage7----->stageSevenDataArray["+j+"]-------------->"+stageSevenDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageSevenStatusArray", stageSevenStatusArray);
		        				return new ModelAndView("DOL/frmDepartmentLabourStage7");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_DOL_id", unique_DOL_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage7CreateDate", stage7CreateDate);
	            request.setAttribute("stage7AcknowledgementDate", stage7AcknowledgementDate);
	            request.setAttribute("stage7SystemDate", stage7SystemDate);
	            request.setAttribute("stage7ApproveStatus", stage7ApproveStatus);
	            if(empUniqueId != null){
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageSevenDataArray", stageSevenDataArray);
	            request.setAttribute("stageSevenStatusArray", stageSevenStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOL/frmDepartmentLabourStage7");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOL/frmDepartmentLabourStage7");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDOLStage7.html")
	public ModelAndView doDOLStage7(HttpServletRequest request,
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
    		   
    		   String oldAccessFileForm = null;
    		   String oldCustomDocsStage7 = "";
    		   String AccessFileFormCheck = null;
    		   String CustomDocsStage7Check = null;
    		   
    		   String AccessFileFormFile = null;
    		   String CustomDocsStage7File = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfLabor");
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
		                	if(parmName.equals("AccessFileFormCheck")){
		                		AccessFileFormCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldAccessFileForm")){
		                		oldAccessFileForm = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("CustomDocsStage7Check")){
		                		CustomDocsStage7Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldCustomDocsStage7")){
		                		oldCustomDocsStage7 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in DOL-Stage7 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("Access_file")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage7 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  AccessFileFormFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("CustomDocs1")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage7 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  CustomDocsStage7File = fileLocation;
					          }
					        
					          System.out.println("File name side Stage7 action --------->"+name);
					        if((AccessFileFormCheck == null || AccessFileFormCheck == "" || AccessFileFormCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("Access_file"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldAccessFileForm-------------in-----if AccessFileFormCheck--Stage7 action--->"+oldAccessFileForm);
						        	  AccessFileFormFile = oldAccessFileForm;
				             }
						    if((CustomDocsStage7Check == null || CustomDocsStage7Check == "" || CustomDocsStage7Check.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("CustomDocs1"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldCustomDocsStage7-------------in-----if CustomDocsStage7Check--Stage7 action--->"+oldCustomDocsStage7);
						        	  CustomDocsStage7File = oldCustomDocsStage7;
				             }
				                  
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && AccessFileFormFile != null){
	            	
	            	//String dolBPELStatus = null;
	            	//DOLClientStage7 clientStage7 = new DOLClientStage7();
	            	//dolBPELStatus = clientStage7.stage7dos(empUniqueId, visaformFile, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	//if(dolBPELStatus != null && dolBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertDOLStageSevenDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,AccessFileFormFile,CustomDocsStage7File);
			       // }else{
			        //    	throw new Exception("Stage Seven Was Failed in BPEL Process");
			       // }
		            
	            System.out.println("insertStatus in Department of Labor Action-Stage7::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOLStageSevenData = new ArrayList();
			            DOLStageSevenData = db.getDOLStageSevenDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOL_id = null;
		            	String emp_unique_id = null;
		            	String stage7CreateDate = null;
		     		    String stage7AcknowledgementDate = null;
		     		    String stage7SystemDate = null;
		     		    String stage7ApproveStatus = null;
		            	String stage7Data = null;
		            	String stage1_status = null;
			        	String stage2_status = null;
			        	String stage3_status = null;
			        	String stage4_status = null;
			        	String stage5_status = null;
			        	String stage6_status = null;
			        	String stage7_status = null;
			        	String stage8_status = null;
			        	String stage9_status = null;
			        	String stage10_status = null;
			        	String stage11_status = null;
			        	String stage12_status = null;
			        	String stage13_status = null;
			        	String stage14_status = null;
			        	String stage15_status = null;
			        	String stage16_status = null;
			        	String stage17_status = null;
			        	String stage18_status = null;
			        	String stage19_status = null;
			        	String stage20_status = null;
			            String[] stageSevenDataArray = null;
			            String[] stageSevenStatusArray = null;
			            if(DOLStageSevenData!=null && DOLStageSevenData.size()!=0){
			            	
							Iterator itr = DOLStageSevenData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOL_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage7Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stage7_status = TempList[9];
							    stage8_status = TempList[10];
							    stage9_status = TempList[11];
							    stage10_status = TempList[12];
							    stage11_status = TempList[13];
							    stage12_status = TempList[14];
							    stage13_status = TempList[15];
							    stage14_status = TempList[16];
							    stage15_status = TempList[17];
							    stage16_status = TempList[18];
							    stage17_status = TempList[19];
							    stage18_status = TempList[20];
							    stage19_status = TempList[21];
							    stage20_status = TempList[22];
							    stageSevenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
							    
							    if(stage7Data != null && stage7Data != "" && !(stage7Data.equalsIgnoreCase(""))){
				        			String []tempStage7DataArray = stage7Data.split("-sep-");
				        			stageSevenDataArray = new String[tempStage7DataArray.length - 4];
				        			stage7CreateDate = tempStage7DataArray[0];
				        			stage7AcknowledgementDate = tempStage7DataArray[1];
				        			stage7SystemDate = tempStage7DataArray[2];
				        			stage7ApproveStatus = tempStage7DataArray[3];
				        			for(int j=0;j<(tempStage7DataArray.length - 4);j++){
				        				stageSevenDataArray[j] = tempStage7DataArray[4 + j];
				        				System.out.println("Department of Labor Action-Stage7----->stageSevenDataArray["+j+"]-------------->"+stageSevenDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOL_id", unique_DOL_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage7CreateDate", stage7CreateDate);
			            request.setAttribute("stage7AcknowledgementDate", stage7AcknowledgementDate);
			            request.setAttribute("stage7SystemDate", stage7SystemDate);
			            request.setAttribute("stage7ApproveStatus", stage7ApproveStatus);
			            if(empUniqueId != null){
			    			ArrayList employeeDetails = new ArrayList();
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageSevenDataArray", stageSevenDataArray);
			            request.setAttribute("stageSevenStatusArray", stageSevenStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOL/frmDepartmentLabourStage7");
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
    	   return new ModelAndView("DOL/frmDepartmentLabourStage7");
	       
	}
	
	@RequestMapping("/initDOLStage8.html")
	public ModelAndView initDOLStage8(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOLStageEightData = new ArrayList();
            DOLStageEightData = db.getDOLStageEightDetailsByUniqueEmpId(empUniqueId);
            
            if(DOLStageEightData != null && DOLStageEightData.size() > 0){
            
	            String unique_DOL_id = null;
	        	String emp_unique_id = null;
	        	String stage8CreateDate = null;
	 		    String stage8AcknowledgementDate = null;
	 		    String stage8SystemDate = null;
	 		    String stage8ApproveStatus = null;
	        	String stage8Data = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	        	String stage7_status = null;
	        	String stage8_status = null;
	        	String stage9_status = null;
	        	String stage10_status = null;
	        	String stage11_status = null;
	        	String stage12_status = null;
	        	String stage13_status = null;
	        	String stage14_status = null;
	        	String stage15_status = null;
	        	String stage16_status = null;
	        	String stage17_status = null;
	        	String stage18_status = null;
	        	String stage19_status = null;
	        	String stage20_status = null;
	            String[] stageEightDataArray = null;
	            String[] stageEightStatusArray = null;
	            if(DOLStageEightData!=null && DOLStageEightData.size()!=0){
	            	
					Iterator itr = DOLStageEightData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOL_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage8Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stage7_status = TempList[9];
					    stage8_status = TempList[10];
					    stage9_status = TempList[11];
					    stage10_status = TempList[12];
					    stage11_status = TempList[13];
					    stage12_status = TempList[14];
					    stage13_status = TempList[15];
					    stage14_status = TempList[16];
					    stage15_status = TempList[17];
					    stage16_status = TempList[18];
					    stage17_status = TempList[19];
					    stage18_status = TempList[20];
					    stage19_status = TempList[21];
					    stage20_status = TempList[22];
					    
					    stageEightStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
					    
					    if(stage8Data != null && stage8Data != "" && !(stage8Data.equalsIgnoreCase(""))){
		        			String []tempStage8DataArray = stage8Data.split("-sep-");
		        			stageEightDataArray = new String[tempStage8DataArray.length - 4];
		        			stage8CreateDate = tempStage8DataArray[0];
		        			stage8AcknowledgementDate = tempStage8DataArray[1];
		        			stage8SystemDate = tempStage8DataArray[2];
		        			stage8ApproveStatus = tempStage8DataArray[3];
		        			for(int j=0;j<(tempStage8DataArray.length - 4);j++){
		        				stageEightDataArray[j] = tempStage8DataArray[4 + j];
		        				System.out.println("Department of Labor Action-Stage8----->stageEightDataArray["+j+"]-------------->"+stageEightDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageEightStatusArray", stageEightStatusArray);
		        				return new ModelAndView("DOL/frmDepartmentLabourStage8");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_DOL_id", unique_DOL_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage8CreateDate", stage8CreateDate);
	            request.setAttribute("stage8AcknowledgementDate", stage8AcknowledgementDate);
	            request.setAttribute("stage8SystemDate", stage8SystemDate);
	            request.setAttribute("stage8ApproveStatus", stage8ApproveStatus);
	            if(empUniqueId != null){
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageEightDataArray", stageEightDataArray);
	            request.setAttribute("stageEightStatusArray", stageEightStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOL/frmDepartmentLabourStage8");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOL/frmDepartmentLabourStage8");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDOLStage8.html")
	public ModelAndView doDOLStage8(HttpServletRequest request,
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
    		   
    		   String oldPrevailingWageReport = null;
    		   String oldCustomDocsStage8 = "";
    		   String PrevailingWageReportCheck = null;
    		   String CustomDocsStage8Check = null;
    		   
    		   String PrevailingWageReportFile = null;
    		   String CustomDocsStage8File = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfLabor");
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
		                	if(parmName.equals("PrevailingWageReportCheck")){
		                		PrevailingWageReportCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldPrevailingWageReport")){
		                		oldPrevailingWageReport = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("CustomDocsStage8Check")){
		                		CustomDocsStage8Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldCustomDocsStage8")){
		                		oldCustomDocsStage8 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in DOL-Stage8 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("PrevailingWageReport")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage8 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  PrevailingWageReportFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("CustomDocsStage8")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage8 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  CustomDocsStage8File = fileLocation;
					          }
					        
					          System.out.println("File name side Stage8 action --------->"+name);
					        if((PrevailingWageReportCheck == null || PrevailingWageReportCheck == "" || PrevailingWageReportCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("PrevailingWageReport"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldPrevailingWageReport-------------in-----if PrevailingWageReportCheck--Stage8 action--->"+oldPrevailingWageReport);
						        	  PrevailingWageReportFile = oldPrevailingWageReport;
				             }
						    if((CustomDocsStage8Check == null || CustomDocsStage8Check == "" || CustomDocsStage8Check.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("CustomDocsStage8"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldCustomDocsStage8-------------in-----if CustomDocsStage8Check--Stage8 action--->"+oldCustomDocsStage8);
						        	  CustomDocsStage8File = oldCustomDocsStage8;
				             }
				                  
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && PrevailingWageReportFile != null){
	            	
	            	//String dolBPELStatus = null;
	            	//DOLClientStage8 clientStage8 = new DOLClientStage8();
	            	//dolBPELStatus = clientStage8.stage8dos(empUniqueId, visaformFile, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	//if(dolBPELStatus != null && dolBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertDOLStageEightDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,PrevailingWageReportFile,CustomDocsStage8File);
			       // }else{
			        //    	throw new Exception("Stage Eight Was Failed in BPEL Process");
			       // }
		            
	            System.out.println("insertStatus in Department of Labor Action-Stage8::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOLStageEightData = new ArrayList();
			            DOLStageEightData = db.getDOLStageEightDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOL_id = null;
		            	String emp_unique_id = null;
		            	String stage8CreateDate = null;
		     		    String stage8AcknowledgementDate = null;
		     		    String stage8SystemDate = null;
		     		    String stage8ApproveStatus = null;
		            	String stage8Data = null;
		            	String stage1_status = null;
			        	String stage2_status = null;
			        	String stage3_status = null;
			        	String stage4_status = null;
			        	String stage5_status = null;
			        	String stage6_status = null;
			        	String stage7_status = null;
			        	String stage8_status = null;
			        	String stage9_status = null;
			        	String stage10_status = null;
			        	String stage11_status = null;
			        	String stage12_status = null;
			        	String stage13_status = null;
			        	String stage14_status = null;
			        	String stage15_status = null;
			        	String stage16_status = null;
			        	String stage17_status = null;
			        	String stage18_status = null;
			        	String stage19_status = null;
			        	String stage20_status = null;
			            String[] stageEightDataArray = null;
			            String[] stageEightStatusArray = null;
			            if(DOLStageEightData!=null && DOLStageEightData.size()!=0){
			            	
							Iterator itr = DOLStageEightData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOL_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage8Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stage7_status = TempList[9];
							    stage8_status = TempList[10];
							    stage9_status = TempList[11];
							    stage10_status = TempList[12];
							    stage11_status = TempList[13];
							    stage12_status = TempList[14];
							    stage13_status = TempList[15];
							    stage14_status = TempList[16];
							    stage15_status = TempList[17];
							    stage16_status = TempList[18];
							    stage17_status = TempList[19];
							    stage18_status = TempList[20];
							    stage19_status = TempList[21];
							    stage20_status = TempList[22];
							    stageEightStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
							    
							    if(stage8Data != null && stage8Data != "" && !(stage8Data.equalsIgnoreCase(""))){
				        			String []tempStage8DataArray = stage8Data.split("-sep-");
				        			stageEightDataArray = new String[tempStage8DataArray.length - 4];
				        			stage8CreateDate = tempStage8DataArray[0];
				        			stage8AcknowledgementDate = tempStage8DataArray[1];
				        			stage8SystemDate = tempStage8DataArray[2];
				        			stage8ApproveStatus = tempStage8DataArray[3];
				        			for(int j=0;j<(tempStage8DataArray.length - 4);j++){
				        				stageEightDataArray[j] = tempStage8DataArray[4 + j];
				        				System.out.println("Department of Labor Action-Stage8----->stageEightDataArray["+j+"]-------------->"+stageEightDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOL_id", unique_DOL_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage8CreateDate", stage8CreateDate);
			            request.setAttribute("stage8AcknowledgementDate", stage8AcknowledgementDate);
			            request.setAttribute("stage8SystemDate", stage8SystemDate);
			            request.setAttribute("stage8ApproveStatus", stage8ApproveStatus);
			            if(empUniqueId != null){
			    			ArrayList employeeDetails = new ArrayList();
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageEightDataArray", stageEightDataArray);
			            request.setAttribute("stageEightStatusArray", stageEightStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOL/frmDepartmentLabourStage8");
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
    	   return new ModelAndView("DOL/frmDepartmentLabourStage8");
	       
	}
	
	@RequestMapping("/initDOLStage9.html")
	public ModelAndView initDOLStage9(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOLStageNineData = new ArrayList();
            DOLStageNineData = db.getDOLStageNineDetailsByUniqueEmpId(empUniqueId);
            
            if(DOLStageNineData != null && DOLStageNineData.size() > 0){
            
	            String unique_DOL_id = null;
	        	String emp_unique_id = null;
	        	String stage9CreateDate = null;
	 		    String stage9AcknowledgementDate = null;
	 		    String stage9SystemDate = null;
	 		    String stage9ApproveStatus = null;
				String Statge9PrevailingWage = null;
				String Statge9LessPrevailingWage = null;
	        	String stage9Data = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	        	String stage7_status = null;
	        	String stage8_status = null;
	        	String stage9_status = null;
	        	String stage10_status = null;
	        	String stage11_status = null;
	        	String stage12_status = null;
	        	String stage13_status = null;
	        	String stage14_status = null;
	        	String stage15_status = null;
	        	String stage16_status = null;
	        	String stage17_status = null;
	        	String stage18_status = null;
	        	String stage19_status = null;
	        	String stage20_status = null;
	            String[] stageNineDataArray = null;
	            String[] stageNineStatusArray = null;
	            if(DOLStageNineData!=null && DOLStageNineData.size()!=0){
	            	
					Iterator itr = DOLStageNineData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOL_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage9Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stage7_status = TempList[9];
					    stage8_status = TempList[10];
					    stage9_status = TempList[11];
					    stage10_status = TempList[12];
					    stage11_status = TempList[13];
					    stage12_status = TempList[14];
					    stage13_status = TempList[15];
					    stage14_status = TempList[16];
					    stage15_status = TempList[17];
					    stage16_status = TempList[18];
					    stage17_status = TempList[19];
					    stage18_status = TempList[20];
					    stage19_status = TempList[21];
					    stage20_status = TempList[22];
					    
					    stageNineStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
					    
					    if(stage9Data != null && stage9Data != "" && !(stage9Data.equalsIgnoreCase(""))){
		        			String []tempStage9DataArray = stage9Data.split("-sep-");
		        			stageNineDataArray = new String[tempStage9DataArray.length - 4];
		        			stage9CreateDate = tempStage9DataArray[0];
		        			stage9AcknowledgementDate = tempStage9DataArray[1];
		        			stage9SystemDate = tempStage9DataArray[2];
		        			stage9ApproveStatus = tempStage9DataArray[3];
							Statge9PrevailingWage = tempStage9DataArray[4];
							Statge9LessPrevailingWage = tempStage9DataArray[5];
		        			for(int j=0;j<(tempStage9DataArray.length - 6);j++){
		        				stageNineDataArray[j] = tempStage9DataArray[6 + j];
		        				System.out.println("Department of Labor Action-Stage9----->stageNineDataArray["+j+"]-------------->"+stageNineDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageNineStatusArray", stageNineStatusArray);
		        				return new ModelAndView("DOL/frmDepartmentLabourStage9");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_DOL_id", unique_DOL_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage9CreateDate", stage9CreateDate);
	            request.setAttribute("stage9AcknowledgementDate", stage9AcknowledgementDate);
	            request.setAttribute("stage9SystemDate", stage9SystemDate);
	            request.setAttribute("stage9ApproveStatus", stage9ApproveStatus);
				request.setAttribute("Statge9PrevailingWage", Statge9PrevailingWage);
				request.setAttribute("Statge9LessPrevailingWage", Statge9LessPrevailingWage);
	            if(empUniqueId != null){
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageNineDataArray", stageNineDataArray);
	            request.setAttribute("stageNineStatusArray", stageNineStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOL/frmDepartmentLabourStage9");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOL/frmDepartmentLabourStage9");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDOLStage9.html")
	public ModelAndView doDOLStage9(HttpServletRequest request,
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
    		   
    		   String oldPrevailingWageForm = "";
    		   String PrevailingWageFormCheck = null;
    		   
    		   String PrevailingWageFormFile = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
			   String PrevailingWage = null;
			   String LessPrevailingWage = null;
    		   
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfLabor");
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
							if(parmName.equals("PrevailingWage")){
		                		PrevailingWage = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
							if(parmName.equals("LessPrevailingWage")){
		                		LessPrevailingWage = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("PrevailingWageFormCheck")){
		                		PrevailingWageFormCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldPrevailingWageForm")){
		                		oldPrevailingWageForm = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in DOL-Stage9 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("PrevailingWageForm")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage9 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  PrevailingWageFormFile = fileLocation;
					          }
					        
					          System.out.println("File name side Stage9 action --------->"+name);
					        if((PrevailingWageFormCheck == null || PrevailingWageFormCheck == "" || PrevailingWageFormCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("PrevailingWageForm"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldPrevailingWageForm-------------in-----if PrevailingWageFormCheck--Stage9 action--->"+oldPrevailingWageForm);
						        	  PrevailingWageFormFile = oldPrevailingWageForm;
				             }
						   
				                  
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && PrevailingWage != null){
	            	
	            	//String dolBPELStatus = null;
	            	//DOLClientStage9 clientStage9 = new DOLClientStage9();
	            	//dolBPELStatus = clientStage9.stage9dos(empUniqueId, visaformFile, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	//if(dolBPELStatus != null && dolBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertDOLStageNineDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,PrevailingWage,LessPrevailingWage,PrevailingWageFormFile);
			       // }else{
			        //    	throw new Exception("Stage Nine Was Failed in BPEL Process");
			       // }
		            
	            System.out.println("insertStatus in Department of Labor Action-Stage9::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOLStageNineData = new ArrayList();
			            DOLStageNineData = db.getDOLStageNineDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOL_id = null;
		            	String emp_unique_id = null;
		            	String stage9CreateDate = null;
		     		    String stage9AcknowledgementDate = null;
		     		    String stage9SystemDate = null;
		     		    String stage9ApproveStatus = null;
						String Statge9PrevailingWage = null;
						String Statge9LessPrevailingWage = null;
		            	String stage9Data = null;
		            	String stage1_status = null;
			        	String stage2_status = null;
			        	String stage3_status = null;
			        	String stage4_status = null;
			        	String stage5_status = null;
			        	String stage6_status = null;
			        	String stage7_status = null;
			        	String stage8_status = null;
			        	String stage9_status = null;
			        	String stage10_status = null;
			        	String stage11_status = null;
			        	String stage12_status = null;
			        	String stage13_status = null;
			        	String stage14_status = null;
			        	String stage15_status = null;
			        	String stage16_status = null;
			        	String stage17_status = null;
			        	String stage18_status = null;
			        	String stage19_status = null;
			        	String stage20_status = null;
			            String[] stageNineDataArray = null;
			            String[] stageNineStatusArray = null;
			            if(DOLStageNineData!=null && DOLStageNineData.size()!=0){
			            	
							Iterator itr = DOLStageNineData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOL_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage9Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stage7_status = TempList[9];
							    stage8_status = TempList[10];
							    stage9_status = TempList[11];
							    stage10_status = TempList[12];
							    stage11_status = TempList[13];
							    stage12_status = TempList[14];
							    stage13_status = TempList[15];
							    stage14_status = TempList[16];
							    stage15_status = TempList[17];
							    stage16_status = TempList[18];
							    stage17_status = TempList[19];
							    stage18_status = TempList[20];
							    stage19_status = TempList[21];
							    stage20_status = TempList[22];
							    stageNineStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
							    
							    if(stage9Data != null && stage9Data != "" && !(stage9Data.equalsIgnoreCase(""))){
				        			String []tempStage9DataArray = stage9Data.split("-sep-");
				        			stageNineDataArray = new String[tempStage9DataArray.length - 4];
				        			stage9CreateDate = tempStage9DataArray[0];
				        			stage9AcknowledgementDate = tempStage9DataArray[1];
				        			stage9SystemDate = tempStage9DataArray[2];
				        			stage9ApproveStatus = tempStage9DataArray[3];
									Statge9PrevailingWage = tempStage9DataArray[4];
									Statge9LessPrevailingWage = tempStage9DataArray[5];
				        			for(int j=0;j<(tempStage9DataArray.length - 6);j++){
				        				stageNineDataArray[j] = tempStage9DataArray[6 + j];
				        				System.out.println("Department of Labor Action-Stage9----->stageNineDataArray["+j+"]-------------->"+stageNineDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOL_id", unique_DOL_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage9CreateDate", stage9CreateDate);
			            request.setAttribute("stage9AcknowledgementDate", stage9AcknowledgementDate);
			            request.setAttribute("stage9SystemDate", stage9SystemDate);
			            request.setAttribute("stage9ApproveStatus", stage9ApproveStatus);
						request.setAttribute("Statge9PrevailingWage", Statge9PrevailingWage);
			            request.setAttribute("Statge9LessPrevailingWage", Statge9LessPrevailingWage);
			            if(empUniqueId != null){
			    			ArrayList employeeDetails = new ArrayList();
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageNineDataArray", stageNineDataArray);
			            request.setAttribute("stageNineStatusArray", stageNineStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOL/frmDepartmentLabourStage9");
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
    	   return new ModelAndView("DOL/frmDepartmentLabourStage9");
	       
	}
	
	@RequestMapping("/initDOLStage10.html")
	public ModelAndView initDOLStage10(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOLStageTenData = new ArrayList();
            DOLStageTenData = db.getDOLStageTenDetailsByUniqueEmpId(empUniqueId);
            
            if(DOLStageTenData != null && DOLStageTenData.size() > 0){
            
	            String unique_DOL_id = null;
	        	String emp_unique_id = null;
	        	String stage10CreateDate = null;
	 		    String stage10AcknowledgementDate = null;
	 		    String stage10SystemDate = null;
	 		    String stage10ApproveStatus = null;
	        	String stage10Data = null;
				String stage10ActualWageYesNo = null;
				String stage10NoPayDocumentNotes = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	        	String stage7_status = null;
	        	String stage8_status = null;
	        	String stage9_status = null;
	        	String stage10_status = null;
	        	String stage11_status = null;
	        	String stage12_status = null;
	        	String stage13_status = null;
	        	String stage14_status = null;
	        	String stage15_status = null;
	        	String stage16_status = null;
	        	String stage17_status = null;
	        	String stage18_status = null;
	        	String stage19_status = null;
	        	String stage20_status = null;
	            String[] stageTenDataArray = null;
	            String[] stageTenStatusArray = null;
	            if(DOLStageTenData!=null && DOLStageTenData.size()!=0){
	            	
					Iterator itr = DOLStageTenData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOL_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage10Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stage7_status = TempList[9];
					    stage8_status = TempList[10];
					    stage9_status = TempList[11];
					    stage10_status = TempList[12];
					    stage11_status = TempList[13];
					    stage12_status = TempList[14];
					    stage13_status = TempList[15];
					    stage14_status = TempList[16];
					    stage15_status = TempList[17];
					    stage16_status = TempList[18];
					    stage17_status = TempList[19];
					    stage18_status = TempList[20];
					    stage19_status = TempList[21];
					    stage20_status = TempList[22];
					    
					    stageTenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
					    
					    if(stage10Data != null && stage10Data != "" && !(stage10Data.equalsIgnoreCase(""))){
		        			String []tempStage10DataArray = stage10Data.split("-sep-");
		        			stageTenDataArray = new String[tempStage10DataArray.length - 4];
		        			stage10CreateDate = tempStage10DataArray[0];
		        			stage10AcknowledgementDate = tempStage10DataArray[1];
		        			stage10SystemDate = tempStage10DataArray[2];
		        			stage10ApproveStatus = tempStage10DataArray[3];
							stage10ActualWageYesNo = tempStage10DataArray[4];
							stage10NoPayDocumentNotes = tempStage10DataArray[5];
		        			for(int j=0;j<(tempStage10DataArray.length - 6);j++){
		        				stageTenDataArray[j] = tempStage10DataArray[6 + j];
		        				System.out.println("Department of Labor Action-Stage10----->stageTenDataArray["+j+"]-------------->"+stageTenDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageTenStatusArray", stageTenStatusArray);
		        				return new ModelAndView("DOL/frmDepartmentLabourStage10");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_DOL_id", unique_DOL_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage10CreateDate", stage10CreateDate);
	            request.setAttribute("stage10AcknowledgementDate", stage10AcknowledgementDate);
	            request.setAttribute("stage10SystemDate", stage10SystemDate);
	            request.setAttribute("stage10ApproveStatus", stage10ApproveStatus);
				request.setAttribute("stage10ActualWageYesNo", stage10ActualWageYesNo);
	            request.setAttribute("stage10NoPayDocumentNotes", stage10NoPayDocumentNotes);
	            if(empUniqueId != null){
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageTenDataArray", stageTenDataArray);
	            request.setAttribute("stageTenStatusArray", stageTenStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOL/frmDepartmentLabourStage10");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOL/frmDepartmentLabourStage10");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDOLStage10.html")
	public ModelAndView doDOLStage10(HttpServletRequest request,
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
    		   
    		   String oldPayDocumentForm = null;
    		   String oldCustomDocsStage10 = "";
    		   String PayDocumentFormCheck = null;
    		   String CustomDocsStage10Check = null;
    		   
    		   String PayDocumentFormFile = null;
    		   String CustomDocsStage10File = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
			   String PayDocument = null;
			   String NoPayDocumentNotes = null;
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfLabor");
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
							if(parmName.equals("PayDocument")){
		                		PayDocument = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("NoPayDocumentNotes")){
		                		NoPayDocumentNotes = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("PayDocumentFormCheck")){
		                		PayDocumentFormCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldPayDocumentForm")){
		                		oldPayDocumentForm = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("CustomDocsStage10Check")){
		                		CustomDocsStage10Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldCustomDocsStage10")){
		                		oldCustomDocsStage10 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in DOL-Stage10 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("PayDocumentForm")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage10 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  PayDocumentFormFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("CustomDocsStage10")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage10 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  CustomDocsStage10File = fileLocation;
					          }
					        
					          System.out.println("File name side Stage10 action --------->"+name);
					        if((PayDocumentFormCheck == null || PayDocumentFormCheck == "" || PayDocumentFormCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("PayDocumentForm"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldPayDocumentForm-------------in-----if PayDocumentFormCheck--Stage10 action--->"+oldPayDocumentForm);
						        	  PayDocumentFormFile = oldPayDocumentForm;
				             }
						    if((CustomDocsStage10Check == null || CustomDocsStage10Check == "" || CustomDocsStage10Check.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("CustomDocsStage10"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldCustomDocsStage10-------------in-----if CustomDocsStage10Check--Stage10 action--->"+oldCustomDocsStage10);
						        	  CustomDocsStage10File = oldCustomDocsStage10;
				             }
				                  
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && PayDocument != null && PayDocumentFormFile != null){
	            	
	            	//String dolBPELStatus = null;
	            	//DOLClientStage10 clientStage10 = new DOLClientStage10();
	            	//dolBPELStatus = clientStage10.stage10dos(empUniqueId, visaformFile, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	//if(dolBPELStatus != null && dolBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertDOLStageTenDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,PayDocument,NoPayDocumentNotes,PayDocumentFormFile,CustomDocsStage10File);
			       // }else{
			        //    	throw new Exception("Stage Ten Was Failed in BPEL Process");
			       // }
		            
	            System.out.println("insertStatus in Department of Labor Action-Stage10::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOLStageTenData = new ArrayList();
			            DOLStageTenData = db.getDOLStageTenDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOL_id = null;
		            	String emp_unique_id = null;
		            	String stage10CreateDate = null;
		     		    String stage10AcknowledgementDate = null;
		     		    String stage10SystemDate = null;
		     		    String stage10ApproveStatus = null;
						String stage10ActualWageYesNo = null;
						String stage10NoPayDocumentNotes = null;
		            	String stage10Data = null;
		            	String stage1_status = null;
			        	String stage2_status = null;
			        	String stage3_status = null;
			        	String stage4_status = null;
			        	String stage5_status = null;
			        	String stage6_status = null;
			        	String stage7_status = null;
			        	String stage8_status = null;
			        	String stage9_status = null;
			        	String stage10_status = null;
			        	String stage11_status = null;
			        	String stage12_status = null;
			        	String stage13_status = null;
			        	String stage14_status = null;
			        	String stage15_status = null;
			        	String stage16_status = null;
			        	String stage17_status = null;
			        	String stage18_status = null;
			        	String stage19_status = null;
			        	String stage20_status = null;
			            String[] stageTenDataArray = null;
			            String[] stageTenStatusArray = null;
			            if(DOLStageTenData!=null && DOLStageTenData.size()!=0){
			            	
							Iterator itr = DOLStageTenData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOL_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage10Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stage7_status = TempList[9];
							    stage8_status = TempList[10];
							    stage9_status = TempList[11];
							    stage10_status = TempList[12];
							    stage11_status = TempList[13];
							    stage12_status = TempList[14];
							    stage13_status = TempList[15];
							    stage14_status = TempList[16];
							    stage15_status = TempList[17];
							    stage16_status = TempList[18];
							    stage17_status = TempList[19];
							    stage18_status = TempList[20];
							    stage19_status = TempList[21];
							    stage20_status = TempList[22];
							    stageTenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
							    
							    if(stage10Data != null && stage10Data != "" && !(stage10Data.equalsIgnoreCase(""))){
				        			String []tempStage10DataArray = stage10Data.split("-sep-");
				        			stageTenDataArray = new String[tempStage10DataArray.length - 4];
				        			stage10CreateDate = tempStage10DataArray[0];
				        			stage10AcknowledgementDate = tempStage10DataArray[1];
				        			stage10SystemDate = tempStage10DataArray[2];
				        			stage10ApproveStatus = tempStage10DataArray[3];
									stage10ActualWageYesNo = tempStage10DataArray[4];
									stage10NoPayDocumentNotes = tempStage10DataArray[5];
				        			for(int j=0;j<(tempStage10DataArray.length - 6);j++){
				        				stageTenDataArray[j] = tempStage10DataArray[6 + j];
				        				System.out.println("Department of Labor Action-Stage10----->stageTenDataArray["+j+"]-------------->"+stageTenDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOL_id", unique_DOL_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage10CreateDate", stage10CreateDate);
			            request.setAttribute("stage10AcknowledgementDate", stage10AcknowledgementDate);
			            request.setAttribute("stage10SystemDate", stage10SystemDate);
			            request.setAttribute("stage10ApproveStatus", stage10ApproveStatus);
						request.setAttribute("stage10ActualWageYesNo", stage10ActualWageYesNo);
						request.setAttribute("stage10NoPayDocumentNotes", stage10NoPayDocumentNotes);
			            if(empUniqueId != null){
			    			ArrayList employeeDetails = new ArrayList();
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageTenDataArray", stageTenDataArray);
			            request.setAttribute("stageTenStatusArray", stageTenStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOL/frmDepartmentLabourStage10");
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
    	   return new ModelAndView("DOL/frmDepartmentLabourStage10");
	       
	}
	
	@RequestMapping("/initDOLStage11.html")
	public ModelAndView initDOLStage11(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOLStageElevenData = new ArrayList();
            DOLStageElevenData = db.getDOLStageElevenDetailsByUniqueEmpId(empUniqueId);
            
            if(DOLStageElevenData != null && DOLStageElevenData.size() > 0){
            
	            String unique_DOL_id = null;
	        	String emp_unique_id = null;
	        	String stage11CreateDate = null;
	 		    String stage11AcknowledgementDate = null;
	 		    String stage11SystemDate = null;
	 		    String stage11ApproveStatus = null;
	        	String stage11Data = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	        	String stage7_status = null;
	        	String stage8_status = null;
	        	String stage9_status = null;
	        	String stage10_status = null;
	        	String stage11_status = null;
	        	String stage12_status = null;
	        	String stage13_status = null;
	        	String stage14_status = null;
	        	String stage15_status = null;
	        	String stage16_status = null;
	        	String stage17_status = null;
	        	String stage18_status = null;
	        	String stage19_status = null;
	        	String stage20_status = null;
	            String[] stageElevenDataArray = null;
	            String[] stageElevenStatusArray = null;
	            if(DOLStageElevenData!=null && DOLStageElevenData.size()!=0){
	            	
					Iterator itr = DOLStageElevenData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOL_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage11Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stage7_status = TempList[9];
					    stage8_status = TempList[10];
					    stage9_status = TempList[11];
					    stage10_status = TempList[12];
					    stage11_status = TempList[13];
					    stage12_status = TempList[14];
					    stage13_status = TempList[15];
					    stage14_status = TempList[16];
					    stage15_status = TempList[17];
					    stage16_status = TempList[18];
					    stage17_status = TempList[19];
					    stage18_status = TempList[20];
					    stage19_status = TempList[21];
					    stage20_status = TempList[22];
					    
					    stageElevenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
					    
					    if(stage11Data != null && stage11Data != "" && !(stage11Data.equalsIgnoreCase(""))){
		        			String []tempStage11DataArray = stage11Data.split("-sep-");
		        			stageElevenDataArray = new String[tempStage11DataArray.length - 4];
		        			stage11CreateDate = tempStage11DataArray[0];
		        			stage11AcknowledgementDate = tempStage11DataArray[1];
		        			stage11SystemDate = tempStage11DataArray[2];
		        			stage11ApproveStatus = tempStage11DataArray[3];
		        			for(int j=0;j<(tempStage11DataArray.length - 4);j++){
		        				stageElevenDataArray[j] = tempStage11DataArray[4 + j];
		        				System.out.println("Department of Labor Action-Stage11----->stageElevenDataArray["+j+"]-------------->"+stageElevenDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageElevenStatusArray", stageElevenStatusArray);
		        				return new ModelAndView("DOL/frmDepartmentLabourStage11");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_DOL_id", unique_DOL_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage11CreateDate", stage11CreateDate);
	            request.setAttribute("stage11AcknowledgementDate", stage11AcknowledgementDate);
	            request.setAttribute("stage11SystemDate", stage11SystemDate);
	            request.setAttribute("stage11ApproveStatus", stage11ApproveStatus);
	            if(empUniqueId != null){
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageElevenDataArray", stageElevenDataArray);
	            request.setAttribute("stageElevenStatusArray", stageElevenStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOL/frmDepartmentLabourStage11");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOL/frmDepartmentLabourStage11");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDOLStage11.html")
	public ModelAndView doDOLStage11(HttpServletRequest request,
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
    		   
    		   String oldResponseDoc = null;
    		   String oldCustomDocsStage11 = "";
    		   String ResponseDocCheck = null;
    		   String CustomDocsStage11Check = null;
    		   
    		   String ResponseDocFile = null;
    		   String CustomDocsStage11File = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfLabor");
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
		                	if(parmName.equals("ResponseDocCheck")){
		                		ResponseDocCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldResponseDoc")){
		                		oldResponseDoc = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("CustomDocsStage11Check")){
		                		CustomDocsStage11Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldCustomDocsStage11")){
		                		oldCustomDocsStage11 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in DOL-Stage11 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("ResponseDoc")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage11 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  ResponseDocFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("CustomDocsStage11")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage11 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  CustomDocsStage11File = fileLocation;
					          }
					        
					          System.out.println("File name side Stage11 action --------->"+name);
					        if((ResponseDocCheck == null || ResponseDocCheck == "" || ResponseDocCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("ResponseDoc"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldResponseDoc-------------in-----if ResponseDocCheck--Stage11 action--->"+oldResponseDoc);
						        	  ResponseDocFile = oldResponseDoc;
				             }
						    if((CustomDocsStage11Check == null || CustomDocsStage11Check == "" || CustomDocsStage11Check.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("CustomDocsStage11"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldCustomDocsStage11-------------in-----if CustomDocsStage11Check--Stage11 action--->"+oldCustomDocsStage11);
						        	  CustomDocsStage11File = oldCustomDocsStage11;
				             }
				                  
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && ResponseDocFile != null){
	            	
	            	//String dolBPELStatus = null;
	            	//DOLClientStage11 clientStage11 = new DOLClientStage11();
	            	//dolBPELStatus = clientStage11.stage11dos(empUniqueId, visaformFile, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	//if(dolBPELStatus != null && dolBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertDOLStageElevenDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,ResponseDocFile,CustomDocsStage11File);
			       // }else{
			        //    	throw new Exception("Stage Eleven Was Failed in BPEL Process");
			       // }
		            
	            System.out.println("insertStatus in Department of Labor Action-Stage11::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOLStageElevenData = new ArrayList();
			            DOLStageElevenData = db.getDOLStageElevenDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOL_id = null;
		            	String emp_unique_id = null;
		            	String stage11CreateDate = null;
		     		    String stage11AcknowledgementDate = null;
		     		    String stage11SystemDate = null;
		     		    String stage11ApproveStatus = null;
		            	String stage11Data = null;
		            	String stage1_status = null;
			        	String stage2_status = null;
			        	String stage3_status = null;
			        	String stage4_status = null;
			        	String stage5_status = null;
			        	String stage6_status = null;
			        	String stage7_status = null;
			        	String stage8_status = null;
			        	String stage9_status = null;
			        	String stage10_status = null;
			        	String stage11_status = null;
			        	String stage12_status = null;
			        	String stage13_status = null;
			        	String stage14_status = null;
			        	String stage15_status = null;
			        	String stage16_status = null;
			        	String stage17_status = null;
			        	String stage18_status = null;
			        	String stage19_status = null;
			        	String stage20_status = null;
			            String[] stageElevenDataArray = null;
			            String[] stageElevenStatusArray = null;
			            if(DOLStageElevenData!=null && DOLStageElevenData.size()!=0){
			            	
							Iterator itr = DOLStageElevenData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOL_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage11Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stage7_status = TempList[9];
							    stage8_status = TempList[10];
							    stage9_status = TempList[11];
							    stage10_status = TempList[12];
							    stage11_status = TempList[13];
							    stage12_status = TempList[14];
							    stage13_status = TempList[15];
							    stage14_status = TempList[16];
							    stage15_status = TempList[17];
							    stage16_status = TempList[18];
							    stage17_status = TempList[19];
							    stage18_status = TempList[20];
							    stage19_status = TempList[21];
							    stage20_status = TempList[22];
							    stageElevenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
							    
							    if(stage11Data != null && stage11Data != "" && !(stage11Data.equalsIgnoreCase(""))){
				        			String []tempStage11DataArray = stage11Data.split("-sep-");
				        			stageElevenDataArray = new String[tempStage11DataArray.length - 4];
				        			stage11CreateDate = tempStage11DataArray[0];
				        			stage11AcknowledgementDate = tempStage11DataArray[1];
				        			stage11SystemDate = tempStage11DataArray[2];
				        			stage11ApproveStatus = tempStage11DataArray[3];
				        			for(int j=0;j<(tempStage11DataArray.length - 4);j++){
				        				stageElevenDataArray[j] = tempStage11DataArray[4 + j];
				        				System.out.println("Department of Labor Action-Stage11----->stageElevenDataArray["+j+"]-------------->"+stageElevenDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOL_id", unique_DOL_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage11CreateDate", stage11CreateDate);
			            request.setAttribute("stage11AcknowledgementDate", stage11AcknowledgementDate);
			            request.setAttribute("stage11SystemDate", stage11SystemDate);
			            request.setAttribute("stage11ApproveStatus", stage11ApproveStatus);
			            if(empUniqueId != null){
			    			ArrayList employeeDetails = new ArrayList();
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageElevenDataArray", stageElevenDataArray);
			            request.setAttribute("stageElevenStatusArray", stageElevenStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOL/frmDepartmentLabourStage11");
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
    	   return new ModelAndView("DOL/frmDepartmentLabourStage11");
	       
	}
	
	@RequestMapping("/initDOLStage12.html")
	public ModelAndView initDOLStage12(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOLStageTwelveData = new ArrayList();
            DOLStageTwelveData = db.getDOLStageTwelveDetailsByUniqueEmpId(empUniqueId);
            
            if(DOLStageTwelveData != null && DOLStageTwelveData.size() > 0){
            
	            String unique_DOL_id = null;
	        	String emp_unique_id = null;
	        	String stage12CreateDate = null;
	 		    String stage12AcknowledgementDate = null;
	 		    String stage12SystemDate = null;
	 		    String stage12ApproveStatus = null;
	        	String stage12Data = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	        	String stage7_status = null;
	        	String stage8_status = null;
	        	String stage9_status = null;
	        	String stage10_status = null;
	        	String stage11_status = null;
	        	String stage12_status = null;
	        	String stage13_status = null;
	        	String stage14_status = null;
	        	String stage15_status = null;
	        	String stage16_status = null;
	        	String stage17_status = null;
	        	String stage18_status = null;
	        	String stage19_status = null;
	        	String stage20_status = null;
	            String[] stageTwelveDataArray = null;
	            String[] stageTwelveStatusArray = null;
	            if(DOLStageTwelveData!=null && DOLStageTwelveData.size()!=0){
	            	
					Iterator itr = DOLStageTwelveData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOL_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage12Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stage7_status = TempList[9];
					    stage8_status = TempList[10];
					    stage9_status = TempList[11];
					    stage10_status = TempList[12];
					    stage11_status = TempList[13];
					    stage12_status = TempList[14];
					    stage13_status = TempList[15];
					    stage14_status = TempList[16];
					    stage15_status = TempList[17];
					    stage16_status = TempList[18];
					    stage17_status = TempList[19];
					    stage18_status = TempList[20];
					    stage19_status = TempList[21];
					    stage20_status = TempList[22];
					    
					    stageTwelveStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
					    
					    if(stage12Data != null && stage12Data != "" && !(stage12Data.equalsIgnoreCase(""))){
		        			String []tempStage12DataArray = stage12Data.split("-sep-");
		        			stageTwelveDataArray = new String[tempStage12DataArray.length - 4];
		        			stage12CreateDate = tempStage12DataArray[0];
		        			stage12AcknowledgementDate = tempStage12DataArray[1];
		        			stage12SystemDate = tempStage12DataArray[2];
		        			stage12ApproveStatus = tempStage12DataArray[3];
		        			for(int j=0;j<(tempStage12DataArray.length - 4);j++){
		        				stageTwelveDataArray[j] = tempStage12DataArray[4 + j];
		        				System.out.println("Department of Labor Action-Stage12----->stageTwelveDataArray["+j+"]-------------->"+stageTwelveDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageTwelveStatusArray", stageTwelveStatusArray);
		        				return new ModelAndView("DOL/frmDepartmentLabourStage12");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_DOL_id", unique_DOL_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage12CreateDate", stage12CreateDate);
	            request.setAttribute("stage12AcknowledgementDate", stage12AcknowledgementDate);
	            request.setAttribute("stage12SystemDate", stage12SystemDate);
	            request.setAttribute("stage12ApproveStatus", stage12ApproveStatus);
	            if(empUniqueId != null){
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageTwelveDataArray", stageTwelveDataArray);
	            request.setAttribute("stageTwelveStatusArray", stageTwelveStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOL/frmDepartmentLabourStage12");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOL/frmDepartmentLabourStage12");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDOLStage12.html")
	public ModelAndView doDOLStage12(HttpServletRequest request,
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
    		   
    		   String oldH1BCandidate = null;
    		   String oldCustomDocsStage12 = "";
    		   String H1BCandidateCheck = null;
    		   String CustomDocsStage12Check = null;
    		   
    		   String H1BCandidateFile = null;
    		   String CustomDocsStage12File = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfLabor");
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
		                	if(parmName.equals("H1BCandidateCheck")){
		                		H1BCandidateCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldH1BCandidate")){
		                		oldH1BCandidate = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("CustomDocsStage12Check")){
		                		CustomDocsStage12Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldCustomDocsStage12")){
		                		oldCustomDocsStage12 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in DOL-Stage12 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("H1BCandidate")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage12 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  H1BCandidateFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("CustomDocs12")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage12 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  CustomDocsStage12File = fileLocation;
					          }
					        
					          System.out.println("File name side Stage12 action --------->"+name);
					        if((H1BCandidateCheck == null || H1BCandidateCheck == "" || H1BCandidateCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("H1BCandidate"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldH1BCandidate-------------in-----if H1BCandidateCheck--Stage12 action--->"+oldH1BCandidate);
						        	  H1BCandidateFile = oldH1BCandidate;
				             }
						    if((CustomDocsStage12Check == null || CustomDocsStage12Check == "" || CustomDocsStage12Check.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("CustomDocs12"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldCustomDocsStage12-------------in-----if CustomDocsStage12Check--Stage12 action--->"+oldCustomDocsStage12);
						        	  CustomDocsStage12File = oldCustomDocsStage12;
				             }
				                  
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && H1BCandidateFile != null){
	            	
	            	//String dolBPELStatus = null;
	            	//DOLClientStage12 clientStage12 = new DOLClientStage12();
	            	//dolBPELStatus = clientStage12.stage12dos(empUniqueId, visaformFile, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	//if(dolBPELStatus != null && dolBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertDOLStageTwelveDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,H1BCandidateFile,CustomDocsStage12File);
			       // }else{
			        //    	throw new Exception("Stage Twelve Was Failed in BPEL Process");
			       // }
		            
	            System.out.println("insertStatus in Department of Labor Action-Stage12::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOLStageTwelveData = new ArrayList();
			            DOLStageTwelveData = db.getDOLStageTwelveDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOL_id = null;
		            	String emp_unique_id = null;
		            	String stage12CreateDate = null;
		     		    String stage12AcknowledgementDate = null;
		     		    String stage12SystemDate = null;
		     		    String stage12ApproveStatus = null;
		            	String stage12Data = null;
		            	String stage1_status = null;
			        	String stage2_status = null;
			        	String stage3_status = null;
			        	String stage4_status = null;
			        	String stage5_status = null;
			        	String stage6_status = null;
			        	String stage7_status = null;
			        	String stage8_status = null;
			        	String stage9_status = null;
			        	String stage10_status = null;
			        	String stage11_status = null;
			        	String stage12_status = null;
			        	String stage13_status = null;
			        	String stage14_status = null;
			        	String stage15_status = null;
			        	String stage16_status = null;
			        	String stage17_status = null;
			        	String stage18_status = null;
			        	String stage19_status = null;
			        	String stage20_status = null;
			            String[] stageTwelveDataArray = null;
			            String[] stageTwelveStatusArray = null;
			            if(DOLStageTwelveData!=null && DOLStageTwelveData.size()!=0){
			            	
							Iterator itr = DOLStageTwelveData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOL_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage12Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stage7_status = TempList[9];
							    stage8_status = TempList[10];
							    stage9_status = TempList[11];
							    stage10_status = TempList[12];
							    stage11_status = TempList[13];
							    stage12_status = TempList[14];
							    stage13_status = TempList[15];
							    stage14_status = TempList[16];
							    stage15_status = TempList[17];
							    stage16_status = TempList[18];
							    stage17_status = TempList[19];
							    stage18_status = TempList[20];
							    stage19_status = TempList[21];
							    stage20_status = TempList[22];
							    stageTwelveStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
							    
							    if(stage12Data != null && stage12Data != "" && !(stage12Data.equalsIgnoreCase(""))){
				        			String []tempStage12DataArray = stage12Data.split("-sep-");
				        			stageTwelveDataArray = new String[tempStage12DataArray.length - 4];
				        			stage12CreateDate = tempStage12DataArray[0];
				        			stage12AcknowledgementDate = tempStage12DataArray[1];
				        			stage12SystemDate = tempStage12DataArray[2];
				        			stage12ApproveStatus = tempStage12DataArray[3];
				        			for(int j=0;j<(tempStage12DataArray.length - 4);j++){
				        				stageTwelveDataArray[j] = tempStage12DataArray[4 + j];
				        				System.out.println("Department of Labor Action-Stage12----->stageTwelveDataArray["+j+"]-------------->"+stageTwelveDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOL_id", unique_DOL_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage12CreateDate", stage12CreateDate);
			            request.setAttribute("stage12AcknowledgementDate", stage12AcknowledgementDate);
			            request.setAttribute("stage12SystemDate", stage12SystemDate);
			            request.setAttribute("stage12ApproveStatus", stage12ApproveStatus);
			            if(empUniqueId != null){
			    			ArrayList employeeDetails = new ArrayList();
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageTwelveDataArray", stageTwelveDataArray);
			            request.setAttribute("stageTwelveStatusArray", stageTwelveStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOL/frmDepartmentLabourStage12");
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
    	   return new ModelAndView("DOL/frmDepartmentLabourStage12");
	       
	}
	
	@RequestMapping("/initDOLStage13.html")
	public ModelAndView initDOLStage13(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOLStageThirteenData = new ArrayList();
            DOLStageThirteenData = db.getDOLStageThirteenDetailsByUniqueEmpId(empUniqueId);
            
            if(DOLStageThirteenData != null && DOLStageThirteenData.size() > 0){
            
	            String unique_DOL_id = null;
	        	String emp_unique_id = null;
	        	String stage13CreateDate = null;
	 		    String stage13AcknowledgementDate = null;
	 		    String stage13SystemDate = null;
	 		    String stage13ApproveStatus = null;
	        	String stage13Data = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	        	String stage7_status = null;
	        	String stage8_status = null;
	        	String stage9_status = null;
	        	String stage10_status = null;
	        	String stage11_status = null;
	        	String stage12_status = null;
	        	String stage13_status = null;
	        	String stage14_status = null;
	        	String stage15_status = null;
	        	String stage16_status = null;
	        	String stage17_status = null;
	        	String stage18_status = null;
	        	String stage19_status = null;
	        	String stage20_status = null;
	            String[] stageThirteenDataArray = null;
	            String[] stageThirteenStatusArray = null;
	            if(DOLStageThirteenData!=null && DOLStageThirteenData.size()!=0){
	            	
					Iterator itr = DOLStageThirteenData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOL_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage13Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stage7_status = TempList[9];
					    stage8_status = TempList[10];
					    stage9_status = TempList[11];
					    stage10_status = TempList[12];
					    stage11_status = TempList[13];
					    stage12_status = TempList[14];
					    stage13_status = TempList[15];
					    stage14_status = TempList[16];
					    stage15_status = TempList[17];
					    stage16_status = TempList[18];
					    stage17_status = TempList[19];
					    stage18_status = TempList[20];
					    stage19_status = TempList[21];
					    stage20_status = TempList[22];
					    
					    stageThirteenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
					    
					    if(stage13Data != null && stage13Data != "" && !(stage13Data.equalsIgnoreCase(""))){
		        			String []tempStage13DataArray = stage13Data.split("-sep-");
		        			stageThirteenDataArray = new String[tempStage13DataArray.length - 4];
		        			stage13CreateDate = tempStage13DataArray[0];
		        			stage13AcknowledgementDate = tempStage13DataArray[1];
		        			stage13SystemDate = tempStage13DataArray[2];
		        			stage13ApproveStatus = tempStage13DataArray[3];
		        			for(int j=0;j<(tempStage13DataArray.length - 4);j++){
		        				stageThirteenDataArray[j] = tempStage13DataArray[4 + j];
		        				System.out.println("Department of Labor Action-Stage13----->stageThirteenDataArray["+j+"]-------------->"+stageThirteenDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageThirteenStatusArray", stageThirteenStatusArray);
		        				return new ModelAndView("DOL/frmDepartmentLabourStage13");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_DOL_id", unique_DOL_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage13CreateDate", stage13CreateDate);
	            request.setAttribute("stage13AcknowledgementDate", stage13AcknowledgementDate);
	            request.setAttribute("stage13SystemDate", stage13SystemDate);
	            request.setAttribute("stage13ApproveStatus", stage13ApproveStatus);
	            if(empUniqueId != null){
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageThirteenDataArray", stageThirteenDataArray);
	            request.setAttribute("stageThirteenStatusArray", stageThirteenStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOL/frmDepartmentLabourStage13");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOL/frmDepartmentLabourStage13");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDOLStage13.html")
	public ModelAndView doDOLStage13(HttpServletRequest request,
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
    		   
    		   String oldLCAForm = null;
    		   String oldCustomDocsStage13 = "";
    		   String LCAFormCheck = null;
    		   String CustomDocsStage13Check = null;
    		   
    		   String LCAFormFile = null;
    		   String CustomDocsStage13 = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfLabor");
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
		                	if(parmName.equals("LCAFormCheck")){
		                		LCAFormCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldLCAForm")){
		                		oldLCAForm = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("CustomDocsStage13Check")){
		                		CustomDocsStage13Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldCustomDocsStage13")){
		                		oldCustomDocsStage13 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in DOL-Stage13 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("Access_file")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage13 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  LCAFormFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("CustomDocs1")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage13 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  CustomDocsStage13 = fileLocation;
					          }
					        
					          System.out.println("File name side Stage13 action --------->"+name);
					        if((LCAFormCheck == null || LCAFormCheck == "" || LCAFormCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("Access_file"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldLCAForm-------------in-----if LCAFormCheck--Stage13 action--->"+oldLCAForm);
						        	  LCAFormFile = oldLCAForm;
				             }
						    if((CustomDocsStage13Check == null || CustomDocsStage13Check == "" || CustomDocsStage13Check.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("CustomDocs1"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldCustomDocsStage13-------------in-----if CustomDocsStage13Check--Stage13 action--->"+oldCustomDocsStage13);
						        	  CustomDocsStage13 = oldCustomDocsStage13;
				             }
				                  
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && LCAFormFile != null){
	            	
	            	//String dolBPELStatus = null;
	            	//DOLClientStage13 clientStage13 = new DOLClientStage13();
	            	//dolBPELStatus = clientStage13.stage13dos(empUniqueId, visaformFile, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	//if(dolBPELStatus != null && dolBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertDOLStageThirteenDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,LCAFormFile,CustomDocsStage13);
			       // }else{
			        //    	throw new Exception("Stage Thirteen Was Failed in BPEL Process");
			       // }
		            
	            System.out.println("insertStatus in Department of Labor Action-Stage13::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOLStageThirteenData = new ArrayList();
			            DOLStageThirteenData = db.getDOLStageThirteenDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOL_id = null;
		            	String emp_unique_id = null;
		            	String stage13CreateDate = null;
		     		    String stage13AcknowledgementDate = null;
		     		    String stage13SystemDate = null;
		     		    String stage13ApproveStatus = null;
		            	String stage13Data = null;
		            	String stage1_status = null;
			        	String stage2_status = null;
			        	String stage3_status = null;
			        	String stage4_status = null;
			        	String stage5_status = null;
			        	String stage6_status = null;
			        	String stage7_status = null;
			        	String stage8_status = null;
			        	String stage9_status = null;
			        	String stage10_status = null;
			        	String stage11_status = null;
			        	String stage12_status = null;
			        	String stage13_status = null;
			        	String stage14_status = null;
			        	String stage15_status = null;
			        	String stage16_status = null;
			        	String stage17_status = null;
			        	String stage18_status = null;
			        	String stage19_status = null;
			        	String stage20_status = null;
			            String[] stageThirteenDataArray = null;
			            String[] stageThirteenStatusArray = null;
			            if(DOLStageThirteenData!=null && DOLStageThirteenData.size()!=0){
			            	
							Iterator itr = DOLStageThirteenData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOL_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage13Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stage7_status = TempList[9];
							    stage8_status = TempList[10];
							    stage9_status = TempList[11];
							    stage10_status = TempList[12];
							    stage11_status = TempList[13];
							    stage12_status = TempList[14];
							    stage13_status = TempList[15];
							    stage14_status = TempList[16];
							    stage15_status = TempList[17];
							    stage16_status = TempList[18];
							    stage17_status = TempList[19];
							    stage18_status = TempList[20];
							    stage19_status = TempList[21];
							    stage20_status = TempList[22];
							    stageThirteenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
							    
							    if(stage13Data != null && stage13Data != "" && !(stage13Data.equalsIgnoreCase(""))){
				        			String []tempStage13DataArray = stage13Data.split("-sep-");
				        			stageThirteenDataArray = new String[tempStage13DataArray.length - 4];
				        			stage13CreateDate = tempStage13DataArray[0];
				        			stage13AcknowledgementDate = tempStage13DataArray[1];
				        			stage13SystemDate = tempStage13DataArray[2];
				        			stage13ApproveStatus = tempStage13DataArray[3];
				        			for(int j=0;j<(tempStage13DataArray.length - 4);j++){
				        				stageThirteenDataArray[j] = tempStage13DataArray[4 + j];
				        				System.out.println("Department of Labor Action-Stage13----->stageThirteenDataArray["+j+"]-------------->"+stageThirteenDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOL_id", unique_DOL_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage13CreateDate", stage13CreateDate);
			            request.setAttribute("stage13AcknowledgementDate", stage13AcknowledgementDate);
			            request.setAttribute("stage13SystemDate", stage13SystemDate);
			            request.setAttribute("stage13ApproveStatus", stage13ApproveStatus);
			            if(empUniqueId != null){
			    			ArrayList employeeDetails = new ArrayList();
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageThirteenDataArray", stageThirteenDataArray);
			            request.setAttribute("stageThirteenStatusArray", stageThirteenStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOL/frmDepartmentLabourStage13");
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
    	   return new ModelAndView("DOL/frmDepartmentLabourStage13");
	       
	}
	
	@RequestMapping("/initDOLStage14.html")
	public ModelAndView initDOLStage14(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOLStageFourteenData = new ArrayList();
            DOLStageFourteenData = db.getDOLStageFourteenDetailsByUniqueEmpId(empUniqueId);
            
            if(DOLStageFourteenData != null && DOLStageFourteenData.size() > 0){
            
	            String unique_DOL_id = null;
	        	String emp_unique_id = null;
	        	String stage14CreateDate = null;
	 		    String stage14AcknowledgementDate = null;
	 		    String stage14SystemDate = null;
	 		    String stage14ApproveStatus = null;
	        	String stage14Data = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	        	String stage7_status = null;
	        	String stage8_status = null;
	        	String stage9_status = null;
	        	String stage10_status = null;
	        	String stage11_status = null;
	        	String stage12_status = null;
	        	String stage13_status = null;
	        	String stage14_status = null;
	        	String stage15_status = null;
	        	String stage16_status = null;
	        	String stage17_status = null;
	        	String stage18_status = null;
	        	String stage19_status = null;
	        	String stage20_status = null;
	            String[] stageFourteenDataArray = null;
	            String[] stageFourteenStatusArray = null;
	            if(DOLStageFourteenData!=null && DOLStageFourteenData.size()!=0){
	            	
					Iterator itr = DOLStageFourteenData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOL_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage14Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stage7_status = TempList[9];
					    stage8_status = TempList[10];
					    stage9_status = TempList[11];
					    stage10_status = TempList[12];
					    stage11_status = TempList[13];
					    stage12_status = TempList[14];
					    stage13_status = TempList[15];
					    stage14_status = TempList[16];
					    stage15_status = TempList[17];
					    stage16_status = TempList[18];
					    stage17_status = TempList[19];
					    stage18_status = TempList[20];
					    stage19_status = TempList[21];
					    stage20_status = TempList[22];
					    
					    stageFourteenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
					    
					    if(stage14Data != null && stage14Data != "" && !(stage14Data.equalsIgnoreCase(""))){
		        			String []tempStage14DataArray = stage14Data.split("-sep-");
		        			stageFourteenDataArray = new String[tempStage14DataArray.length - 4];
		        			stage14CreateDate = tempStage14DataArray[0];
		        			stage14AcknowledgementDate = tempStage14DataArray[1];
		        			stage14SystemDate = tempStage14DataArray[2];
		        			stage14ApproveStatus = tempStage14DataArray[3];
		        			for(int j=0;j<(tempStage14DataArray.length - 4);j++){
		        				stageFourteenDataArray[j] = tempStage14DataArray[4 + j];
		        				System.out.println("Department of Labor Action-Stage14----->stageFourteenDataArray["+j+"]-------------->"+stageFourteenDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageFourteenStatusArray", stageFourteenStatusArray);
		        				return new ModelAndView("DOL/frmDepartmentLabourStage14");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_DOL_id", unique_DOL_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage14CreateDate", stage14CreateDate);
	            request.setAttribute("stage14AcknowledgementDate", stage14AcknowledgementDate);
	            request.setAttribute("stage14SystemDate", stage14SystemDate);
	            request.setAttribute("stage14ApproveStatus", stage14ApproveStatus);
	            if(empUniqueId != null){
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageFourteenDataArray", stageFourteenDataArray);
	            request.setAttribute("stageFourteenStatusArray", stageFourteenStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOL/frmDepartmentLabourStage14");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOL/frmDepartmentLabourStage14");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDOLStage14.html")
	public ModelAndView doDOLStage14(HttpServletRequest request,
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
    		   
    		   String oldPostingWorkForm = null;
    		   String oldCustomDocsStage14 = "";
    		   String PostingWorkFormCheck = null;
    		   String CustomDocsStage14Check = null;
    		   
    		   String PostingWorkFormFile = null;
    		   String CustomDocsStage14File = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfLabor");
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
		                	if(parmName.equals("PostingWorkFormCheck")){
		                		PostingWorkFormCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldPostingWorkForm")){
		                		oldPostingWorkForm = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("CustomDocsStage14Check")){
		                		CustomDocsStage14Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldCustomDocsStage14")){
		                		oldCustomDocsStage14 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in DOL-Stage14 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("PostingWorkForm")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage14 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  PostingWorkFormFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("CustomDocsStage14")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage14 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  CustomDocsStage14File = fileLocation;
					          }
					        
					          System.out.println("File name side Stage14 action --------->"+name);
					        if((PostingWorkFormCheck == null || PostingWorkFormCheck == "" || PostingWorkFormCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("PostingWorkForm"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldPostingWorkForm-------------in-----if PostingWorkFormCheck--Stage14 action--->"+oldPostingWorkForm);
						        	  PostingWorkFormFile = oldPostingWorkForm;
				             }
						    if((CustomDocsStage14Check == null || CustomDocsStage14Check == "" || CustomDocsStage14Check.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("CustomDocsStage14"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldCustomDocsStage14-------------in-----if CustomDocsStage14Check--Stage14 action--->"+oldCustomDocsStage14);
						        	  CustomDocsStage14File = oldCustomDocsStage14;
				             }
				                  
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && PostingWorkFormFile != null){
	            	
	            	//String dolBPELStatus = null;
	            	//DOLClientStage14 clientStage14 = new DOLClientStage14();
	            	//dolBPELStatus = clientStage14.stage14dos(empUniqueId, visaformFile, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	//if(dolBPELStatus != null && dolBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertDOLStageFourteenDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,PostingWorkFormFile,CustomDocsStage14File);
			       // }else{
			        //    	throw new Exception("Stage Fourteen Was Failed in BPEL Process");
			       // }
		            
	            System.out.println("insertStatus in Department of Labor Action-Stage14::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOLStageFourteenData = new ArrayList();
			            DOLStageFourteenData = db.getDOLStageFourteenDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOL_id = null;
		            	String emp_unique_id = null;
		            	String stage14CreateDate = null;
		     		    String stage14AcknowledgementDate = null;
		     		    String stage14SystemDate = null;
		     		    String stage14ApproveStatus = null;
		            	String stage14Data = null;
		            	String stage1_status = null;
			        	String stage2_status = null;
			        	String stage3_status = null;
			        	String stage4_status = null;
			        	String stage5_status = null;
			        	String stage6_status = null;
			        	String stage7_status = null;
			        	String stage8_status = null;
			        	String stage9_status = null;
			        	String stage10_status = null;
			        	String stage11_status = null;
			        	String stage12_status = null;
			        	String stage13_status = null;
			        	String stage14_status = null;
			        	String stage15_status = null;
			        	String stage16_status = null;
			        	String stage17_status = null;
			        	String stage18_status = null;
			        	String stage19_status = null;
			        	String stage20_status = null;
			            String[] stageFourteenDataArray = null;
			            String[] stageFourteenStatusArray = null;
			            if(DOLStageFourteenData!=null && DOLStageFourteenData.size()!=0){
			            	
							Iterator itr = DOLStageFourteenData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOL_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage14Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stage7_status = TempList[9];
							    stage8_status = TempList[10];
							    stage9_status = TempList[11];
							    stage10_status = TempList[12];
							    stage11_status = TempList[13];
							    stage12_status = TempList[14];
							    stage13_status = TempList[15];
							    stage14_status = TempList[16];
							    stage15_status = TempList[17];
							    stage16_status = TempList[18];
							    stage17_status = TempList[19];
							    stage18_status = TempList[20];
							    stage19_status = TempList[21];
							    stage20_status = TempList[22];
							    stageFourteenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
							    
							    if(stage14Data != null && stage14Data != "" && !(stage14Data.equalsIgnoreCase(""))){
				        			String []tempStage14DataArray = stage14Data.split("-sep-");
				        			stageFourteenDataArray = new String[tempStage14DataArray.length - 4];
				        			stage14CreateDate = tempStage14DataArray[0];
				        			stage14AcknowledgementDate = tempStage14DataArray[1];
				        			stage14SystemDate = tempStage14DataArray[2];
				        			stage14ApproveStatus = tempStage14DataArray[3];
				        			for(int j=0;j<(tempStage14DataArray.length - 4);j++){
				        				stageFourteenDataArray[j] = tempStage14DataArray[4 + j];
				        				System.out.println("Department of Labor Action-Stage14----->stageFourteenDataArray["+j+"]-------------->"+stageFourteenDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOL_id", unique_DOL_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage14CreateDate", stage14CreateDate);
			            request.setAttribute("stage14AcknowledgementDate", stage14AcknowledgementDate);
			            request.setAttribute("stage14SystemDate", stage14SystemDate);
			            request.setAttribute("stage14ApproveStatus", stage14ApproveStatus);
			            if(empUniqueId != null){
			    			ArrayList employeeDetails = new ArrayList();
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageFourteenDataArray", stageFourteenDataArray);
			            request.setAttribute("stageFourteenStatusArray", stageFourteenStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOL/frmDepartmentLabourStage14");
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
    	   return new ModelAndView("DOL/frmDepartmentLabourStage14");
	       
	}
	
	@RequestMapping("/initDOLStage15.html")
	public ModelAndView initDOLStage15(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOLStageFifteenData = new ArrayList();
            DOLStageFifteenData = db.getDOLStageFifteenDetailsByUniqueEmpId(empUniqueId);
            
            if(DOLStageFifteenData != null && DOLStageFifteenData.size() > 0){
            
	            String unique_DOL_id = null;
	        	String emp_unique_id = null;
	        	String stage15CreateDate = null;
	 		    String stage15AcknowledgementDate = null;
	 		    String stage15SystemDate = null;
	 		    String stage15ApproveStatus = null;
				String stage15PostedDate = null;
	        	String stage15Data = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	        	String stage7_status = null;
	        	String stage8_status = null;
	        	String stage9_status = null;
	        	String stage10_status = null;
	        	String stage11_status = null;
	        	String stage12_status = null;
	        	String stage13_status = null;
	        	String stage14_status = null;
	        	String stage15_status = null;
	        	String stage16_status = null;
	        	String stage17_status = null;
	        	String stage18_status = null;
	        	String stage19_status = null;
	        	String stage20_status = null;
	            String[] stageFifteenDataArray = null;
	            String[] stageFifteenStatusArray = null;
	            if(DOLStageFifteenData!=null && DOLStageFifteenData.size()!=0){
	            	
					Iterator itr = DOLStageFifteenData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOL_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage15Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stage7_status = TempList[9];
					    stage8_status = TempList[10];
					    stage9_status = TempList[11];
					    stage10_status = TempList[12];
					    stage11_status = TempList[13];
					    stage12_status = TempList[14];
					    stage13_status = TempList[15];
					    stage14_status = TempList[16];
					    stage15_status = TempList[17];
					    stage16_status = TempList[18];
					    stage17_status = TempList[19];
					    stage18_status = TempList[20];
					    stage19_status = TempList[21];
					    stage20_status = TempList[22];
					    
					    stageFifteenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
					    
					    if(stage15Data != null && stage15Data != "" && !(stage15Data.equalsIgnoreCase(""))){
		        			String []tempStage15DataArray = stage15Data.split("-sep-");
		        			stageFifteenDataArray = new String[tempStage15DataArray.length - 4];
		        			stage15CreateDate = tempStage15DataArray[0];
		        			stage15AcknowledgementDate = tempStage15DataArray[1];
		        			stage15SystemDate = tempStage15DataArray[2];
		        			stage15ApproveStatus = tempStage15DataArray[3];
							stage15PostedDate = tempStage15DataArray[4];
		        			for(int j=0;j<(tempStage15DataArray.length - 5);j++){
		        				stageFifteenDataArray[j] = tempStage15DataArray[5 + j];
		        				System.out.println("Department of Labor Action-Stage15----->stageFifteenDataArray["+j+"]-------------->"+stageFifteenDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageFifteenStatusArray", stageFifteenStatusArray);
		        				return new ModelAndView("DOL/frmDepartmentLabourStage15");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_DOL_id", unique_DOL_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage15CreateDate", stage15CreateDate);
	            request.setAttribute("stage15AcknowledgementDate", stage15AcknowledgementDate);
	            request.setAttribute("stage15SystemDate", stage15SystemDate);
	            request.setAttribute("stage15ApproveStatus", stage15ApproveStatus);
				request.setAttribute("stage15PostedDate", stage15PostedDate);
	            if(empUniqueId != null){
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageFifteenDataArray", stageFifteenDataArray);
	            request.setAttribute("stageFifteenStatusArray", stageFifteenStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOL/frmDepartmentLabourStage15");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOL/frmDepartmentLabourStage15");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDOLStage15.html")
	public ModelAndView doDOLStage15(HttpServletRequest request,
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
    		   
    		   String oldTakenPhoto = null;
    		   String oldSignClarification = null;
			   String oldCustomDocsStage15 = "";
    		   String TakenPhotoCheck = null;
    		   String SignClarificationCheck = null;
			   String CustomDocsStage15Check = null;
			   
			   String TakenPhotoFile = null;
    		   String SignClarificationFile = null;
			   String CustomDocsStage15File = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
			   String PostedDate = null;
    		   
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfLabor");
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
							if(parmName.equals("PostedDate")){
		                		PostedDate = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("TakenPhotoCheck")){
		                		TakenPhotoCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldTakenPhoto")){
		                		oldTakenPhoto = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("SignClarificationCheck")){
		                		SignClarificationCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldSignClarification")){
		                		oldSignClarification = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
							if(parmName.equals("CustomDocsStage15Check")){
		                		CustomDocsStage15Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldCustomDocsStage15")){
		                		oldCustomDocsStage15 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in DOL-Stage15 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("TakenPhoto")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage15 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  TakenPhotoFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("SignClarification")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage15 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  SignClarificationFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("CustomDocsStage15")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage15 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  CustomDocsStage15File = fileLocation;
					          }
					        
					          System.out.println("File name side Stage15 action --------->"+name);
					        if((TakenPhotoCheck == null || TakenPhotoCheck == "" || TakenPhotoCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("TakenPhoto"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldTakenPhoto-------------in-----if TakenPhotoCheck--Stage15 action--->"+oldTakenPhoto);
						        	  TakenPhotoFile = oldTakenPhoto;
				             }
						    if((SignClarificationCheck == null || SignClarificationCheck == "" || SignClarificationCheck.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("SignClarification"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldSignClarification-------------in-----if additionalfileCheck--Stage15 action--->"+oldSignClarification);
						        	  SignClarificationFile = oldSignClarification;
				             }
							 if((CustomDocsStage15Check == null || CustomDocsStage15Check == "" || CustomDocsStage15Check.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("CustomDocsStage15"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldCustomDocsStage15-------------in-----if CustomDocsStage15Check--Stage15 action--->"+oldCustomDocsStage15);
						        	  CustomDocsStage15File = oldCustomDocsStage15;
				             }
				                  
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && TakenPhotoFile != null && SignClarificationFile != null){
	            	
	            	//String dolBPELStatus = null;
	            	//DOLClientStage15 clientStage15 = new DOLClientStage15();
	            	//dolBPELStatus = clientStage15.stage15dos(empUniqueId, visaformFile, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	//if(dolBPELStatus != null && dolBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertDOLStageFifteenDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,TakenPhotoFile,SignClarificationFile,CustomDocsStage15File);
			       // }else{
			        //    	throw new Exception("Stage Fifteen Was Failed in BPEL Process");
			       // }
		            
	            System.out.println("insertStatus in Department of Labor Action-Stage15::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOLStageFifteenData = new ArrayList();
			            DOLStageFifteenData = db.getDOLStageFifteenDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOL_id = null;
		            	String emp_unique_id = null;
		            	String stage15CreateDate = null;
		     		    String stage15AcknowledgementDate = null;
		     		    String stage15SystemDate = null;
		     		    String stage15ApproveStatus = null;
						String stage15PostedDate = null;
		            	String stage15Data = null;
		            	String stage1_status = null;
			        	String stage2_status = null;
			        	String stage3_status = null;
			        	String stage4_status = null;
			        	String stage5_status = null;
			        	String stage6_status = null;
			        	String stage7_status = null;
			        	String stage8_status = null;
			        	String stage9_status = null;
			        	String stage10_status = null;
			        	String stage11_status = null;
			        	String stage12_status = null;
			        	String stage13_status = null;
			        	String stage14_status = null;
			        	String stage15_status = null;
			        	String stage16_status = null;
			        	String stage17_status = null;
			        	String stage18_status = null;
			        	String stage19_status = null;
			        	String stage20_status = null;
			            String[] stageFifteenDataArray = null;
			            String[] stageFifteenStatusArray = null;
			            if(DOLStageFifteenData!=null && DOLStageFifteenData.size()!=0){
			            	
							Iterator itr = DOLStageFifteenData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOL_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage15Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stage7_status = TempList[9];
							    stage8_status = TempList[10];
							    stage9_status = TempList[11];
							    stage10_status = TempList[12];
							    stage11_status = TempList[13];
							    stage12_status = TempList[14];
							    stage13_status = TempList[15];
							    stage14_status = TempList[16];
							    stage15_status = TempList[17];
							    stage16_status = TempList[18];
							    stage17_status = TempList[19];
							    stage18_status = TempList[20];
							    stage19_status = TempList[21];
							    stage20_status = TempList[22];
							    stageFifteenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
							    
							    if(stage15Data != null && stage15Data != "" && !(stage15Data.equalsIgnoreCase(""))){
				        			String []tempStage15DataArray = stage15Data.split("-sep-");
				        			stageFifteenDataArray = new String[tempStage15DataArray.length - 4];
				        			stage15CreateDate = tempStage15DataArray[0];
				        			stage15AcknowledgementDate = tempStage15DataArray[1];
				        			stage15SystemDate = tempStage15DataArray[2];
				        			stage15ApproveStatus = tempStage15DataArray[3];
									stage15PostedDate = tempStage15DataArray[4];
				        			for(int j=0;j<(tempStage15DataArray.length - 5);j++){
				        				stageFifteenDataArray[j] = tempStage15DataArray[5 + j];
				        				System.out.println("Department of Labor Action-Stage15----->stageFifteenDataArray["+j+"]-------------->"+stageFifteenDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOL_id", unique_DOL_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage15CreateDate", stage15CreateDate);
			            request.setAttribute("stage15AcknowledgementDate", stage15AcknowledgementDate);
			            request.setAttribute("stage15SystemDate", stage15SystemDate);
			            request.setAttribute("stage15ApproveStatus", stage15ApproveStatus);
						request.setAttribute("stage15PostedDate", stage15PostedDate);
			            if(empUniqueId != null){
			    			ArrayList employeeDetails = new ArrayList();
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageFifteenDataArray", stageFifteenDataArray);
			            request.setAttribute("stageFifteenStatusArray", stageFifteenStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOL/frmDepartmentLabourStage15");
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
    	   return new ModelAndView("DOL/frmDepartmentLabourStage15");
	       
	}
	
	@RequestMapping("/initDOLStage16.html")
	public ModelAndView initDOLStage16(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOLStageSixteenData = new ArrayList();
            DOLStageSixteenData = db.getDOLStageSixteenDetailsByUniqueEmpId(empUniqueId);
            
            if(DOLStageSixteenData != null && DOLStageSixteenData.size() > 0){
            
	            String unique_DOL_id = null;
	        	String emp_unique_id = null;
	        	String stage16CreateDate = null;
	 		    String stage16AcknowledgementDate = null;
	 		    String stage16SystemDate = null;
	 		    String stage16ApproveStatus = null;
	        	String stage16Data = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	        	String stage7_status = null;
	        	String stage8_status = null;
	        	String stage9_status = null;
	        	String stage10_status = null;
	        	String stage11_status = null;
	        	String stage12_status = null;
	        	String stage13_status = null;
	        	String stage14_status = null;
	        	String stage15_status = null;
	        	String stage16_status = null;
	        	String stage17_status = null;
	        	String stage18_status = null;
	        	String stage19_status = null;
	        	String stage20_status = null;
	            String[] stageSixteenDataArray = null;
	            String[] stageSixteenStatusArray = null;
	            if(DOLStageSixteenData!=null && DOLStageSixteenData.size()!=0){
	            	
					Iterator itr = DOLStageSixteenData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOL_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage16Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stage7_status = TempList[9];
					    stage8_status = TempList[10];
					    stage9_status = TempList[11];
					    stage10_status = TempList[12];
					    stage11_status = TempList[13];
					    stage12_status = TempList[14];
					    stage13_status = TempList[15];
					    stage14_status = TempList[16];
					    stage15_status = TempList[17];
					    stage16_status = TempList[18];
					    stage17_status = TempList[19];
					    stage18_status = TempList[20];
					    stage19_status = TempList[21];
					    stage20_status = TempList[22];
					    
					    stageSixteenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
					    
					    if(stage16Data != null && stage16Data != "" && !(stage16Data.equalsIgnoreCase(""))){
		        			String []tempStage16DataArray = stage16Data.split("-sep-");
		        			stageSixteenDataArray = new String[tempStage16DataArray.length - 4];
		        			stage16CreateDate = tempStage16DataArray[0];
		        			stage16AcknowledgementDate = tempStage16DataArray[1];
		        			stage16SystemDate = tempStage16DataArray[2];
		        			stage16ApproveStatus = tempStage16DataArray[3];
		        			for(int j=0;j<(tempStage16DataArray.length - 4);j++){
		        				stageSixteenDataArray[j] = tempStage16DataArray[4 + j];
		        				System.out.println("Department of Labor Action-Stage16----->stageSixteenDataArray["+j+"]-------------->"+stageSixteenDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageSixteenStatusArray", stageSixteenStatusArray);
		        				return new ModelAndView("DOL/frmDepartmentLabourStage16");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_DOL_id", unique_DOL_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage16CreateDate", stage16CreateDate);
	            request.setAttribute("stage16AcknowledgementDate", stage16AcknowledgementDate);
	            request.setAttribute("stage16SystemDate", stage16SystemDate);
	            request.setAttribute("stage16ApproveStatus", stage16ApproveStatus);
	            if(empUniqueId != null){
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageSixteenDataArray", stageSixteenDataArray);
	            request.setAttribute("stageSixteenStatusArray", stageSixteenStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOL/frmDepartmentLabourStage16");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOL/frmDepartmentLabourStage16");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDOLStage16.html")
	public ModelAndView doDOLStage16(HttpServletRequest request,
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
    		   
    		   String oldDayNotification = null;
    		   String oldCustomDocsStage16 = "";
    		   String DayNotificationCheck = null;
    		   String CustomDocsStage16Check = null;
    		   
    		   String DayNotificationFile = null;
    		   String CustomDocsStage16File = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfLabor");
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
		                	if(parmName.equals("DayNotificationCheck")){
		                		DayNotificationCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldDayNotification")){
		                		oldDayNotification = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("CustomDocsStage16Check")){
		                		CustomDocsStage16Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldCustomDocsStage16")){
		                		oldCustomDocsStage16 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in DOL-Stage16 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("DayNotification")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage16 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  DayNotificationFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("CustomDocsStage16")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage16 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  CustomDocsStage16File = fileLocation;
					          }
					        
					          System.out.println("File name side Stage16 action --------->"+name);
					        if((DayNotificationCheck == null || DayNotificationCheck == "" || DayNotificationCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("DayNotification"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldDayNotification-------------in-----if DayNotificationCheck--Stage16 action--->"+oldDayNotification);
						        	  DayNotificationFile = oldDayNotification;
				             }
						    if((CustomDocsStage16Check == null || CustomDocsStage16Check == "" || CustomDocsStage16Check.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("CustomDocsStage16"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldCustomDocsStage16-------------in-----if CustomDocsStage16Check--Stage16 action--->"+oldCustomDocsStage16);
						        	  CustomDocsStage16File = oldCustomDocsStage16;
				             }
				                  
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && DayNotificationFile != null){
	            	
	            	//String dolBPELStatus = null;
	            	//DOLClientStage16 clientStage16 = new DOLClientStage16();
	            	//dolBPELStatus = clientStage16.stage16dos(empUniqueId, visaformFile, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	//if(dolBPELStatus != null && dolBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertDOLStageSixteenDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,DayNotificationFile,CustomDocsStage16File);
			       // }else{
			        //    	throw new Exception("Stage Sixteen Was Failed in BPEL Process");
			       // }
		            
	            System.out.println("insertStatus in Department of Labor Action-Stage16::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOLStageSixteenData = new ArrayList();
			            DOLStageSixteenData = db.getDOLStageSixteenDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOL_id = null;
		            	String emp_unique_id = null;
		            	String stage16CreateDate = null;
		     		    String stage16AcknowledgementDate = null;
		     		    String stage16SystemDate = null;
		     		    String stage16ApproveStatus = null;
		            	String stage16Data = null;
		            	String stage1_status = null;
			        	String stage2_status = null;
			        	String stage3_status = null;
			        	String stage4_status = null;
			        	String stage5_status = null;
			        	String stage6_status = null;
			        	String stage7_status = null;
			        	String stage8_status = null;
			        	String stage9_status = null;
			        	String stage10_status = null;
			        	String stage11_status = null;
			        	String stage12_status = null;
			        	String stage13_status = null;
			        	String stage14_status = null;
			        	String stage15_status = null;
			        	String stage16_status = null;
			        	String stage17_status = null;
			        	String stage18_status = null;
			        	String stage19_status = null;
			        	String stage20_status = null;
			            String[] stageSixteenDataArray = null;
			            String[] stageSixteenStatusArray = null;
			            if(DOLStageSixteenData!=null && DOLStageSixteenData.size()!=0){
			            	
							Iterator itr = DOLStageSixteenData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOL_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage16Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stage7_status = TempList[9];
							    stage8_status = TempList[10];
							    stage9_status = TempList[11];
							    stage10_status = TempList[12];
							    stage11_status = TempList[13];
							    stage12_status = TempList[14];
							    stage13_status = TempList[15];
							    stage14_status = TempList[16];
							    stage15_status = TempList[17];
							    stage16_status = TempList[18];
							    stage17_status = TempList[19];
							    stage18_status = TempList[20];
							    stage19_status = TempList[21];
							    stage20_status = TempList[22];
							    stageSixteenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
							    
							    if(stage16Data != null && stage16Data != "" && !(stage16Data.equalsIgnoreCase(""))){
				        			String []tempStage16DataArray = stage16Data.split("-sep-");
				        			stageSixteenDataArray = new String[tempStage16DataArray.length - 4];
				        			stage16CreateDate = tempStage16DataArray[0];
				        			stage16AcknowledgementDate = tempStage16DataArray[1];
				        			stage16SystemDate = tempStage16DataArray[2];
				        			stage16ApproveStatus = tempStage16DataArray[3];
				        			for(int j=0;j<(tempStage16DataArray.length - 4);j++){
				        				stageSixteenDataArray[j] = tempStage16DataArray[4 + j];
				        				System.out.println("Department of Labor Action-Stage16----->stageSixteenDataArray["+j+"]-------------->"+stageSixteenDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOL_id", unique_DOL_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage16CreateDate", stage16CreateDate);
			            request.setAttribute("stage16AcknowledgementDate", stage16AcknowledgementDate);
			            request.setAttribute("stage16SystemDate", stage16SystemDate);
			            request.setAttribute("stage16ApproveStatus", stage16ApproveStatus);
			            if(empUniqueId != null){
			    			ArrayList employeeDetails = new ArrayList();
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageSixteenDataArray", stageSixteenDataArray);
			            request.setAttribute("stageSixteenStatusArray", stageSixteenStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOL/frmDepartmentLabourStage16");
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
    	   return new ModelAndView("DOL/frmDepartmentLabourStage16");
	       
	}
	
	@RequestMapping("/initDOLStage17.html")
	public ModelAndView initDOLStage17(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOLStageSeventeenData = new ArrayList();
            DOLStageSeventeenData = db.getDOLStageSeventeenDetailsByUniqueEmpId(empUniqueId);
            
            if(DOLStageSeventeenData != null && DOLStageSeventeenData.size() > 0){
            
	            String unique_DOL_id = null;
	        	String emp_unique_id = null;
	        	String stage17CreateDate = null;
	 		    String stage17AcknowledgementDate = null;
	 		    String stage17SystemDate = null;
	 		    String stage17ApproveStatus = null;
				String stage17PhotoTime = null;
	        	String stage17Data = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	        	String stage7_status = null;
	        	String stage8_status = null;
	        	String stage9_status = null;
	        	String stage10_status = null;
	        	String stage11_status = null;
	        	String stage12_status = null;
	        	String stage13_status = null;
	        	String stage14_status = null;
	        	String stage15_status = null;
	        	String stage16_status = null;
	        	String stage17_status = null;
	        	String stage18_status = null;
	        	String stage19_status = null;
	        	String stage20_status = null;
	            String[] stageSeventeenDataArray = null;
	            String[] stageSeventeenStatusArray = null;
	            if(DOLStageSeventeenData!=null && DOLStageSeventeenData.size()!=0){
	            	
					Iterator itr = DOLStageSeventeenData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOL_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage17Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stage7_status = TempList[9];
					    stage8_status = TempList[10];
					    stage9_status = TempList[11];
					    stage10_status = TempList[12];
					    stage11_status = TempList[13];
					    stage12_status = TempList[14];
					    stage13_status = TempList[15];
					    stage14_status = TempList[16];
					    stage15_status = TempList[17];
					    stage16_status = TempList[18];
					    stage17_status = TempList[19];
					    stage18_status = TempList[20];
					    stage19_status = TempList[21];
					    stage20_status = TempList[22];
					    
					    stageSeventeenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
					    
					    if(stage17Data != null && stage17Data != "" && !(stage17Data.equalsIgnoreCase(""))){
		        			String []tempStage17DataArray = stage17Data.split("-sep-");
		        			stageSeventeenDataArray = new String[tempStage17DataArray.length - 4];
		        			stage17CreateDate = tempStage17DataArray[0];
		        			stage17AcknowledgementDate = tempStage17DataArray[1];
		        			stage17SystemDate = tempStage17DataArray[2];
		        			stage17ApproveStatus = tempStage17DataArray[3];
							stage17PhotoTime = tempStage17DataArray[4];
		        			for(int j=0;j<(tempStage17DataArray.length - 5);j++){
		        				stageSeventeenDataArray[j] = tempStage17DataArray[5 + j];
		        				System.out.println("Department of Labor Action-Stage17----->stageSeventeenDataArray["+j+"]-------------->"+stageSeventeenDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageSeventeenStatusArray", stageSeventeenStatusArray);
		        				return new ModelAndView("DOL/frmDepartmentLabourStage17");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_DOL_id", unique_DOL_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage17CreateDate", stage17CreateDate);
	            request.setAttribute("stage17AcknowledgementDate", stage17AcknowledgementDate);
	            request.setAttribute("stage17SystemDate", stage17SystemDate);
	            request.setAttribute("stage17ApproveStatus", stage17ApproveStatus);
				request.setAttribute("stage17PhotoTime", stage17PhotoTime);
	            if(empUniqueId != null){
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageSeventeenDataArray", stageSeventeenDataArray);
	            request.setAttribute("stageSeventeenStatusArray", stageSeventeenStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOL/frmDepartmentLabourStage17");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOL/frmDepartmentLabourStage17");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDOLStage17.html")
	public ModelAndView doDOLStage17(HttpServletRequest request,
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
    		   
    		   String oldTakenPhotoStage17 = null;
    		   String oldSystemTaken = null;
    		   String TakenPhotoStage17Check = null;
    		   String SystemTakenCheck = null;
    		   
    		   String TakenPhotoStage17File = null;
    		   String SystemTakenFile = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
			   String PhotoTime = null;
    		   
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfLabor");
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
							if(parmName.equals("PhotoTime")){
		                		PhotoTime = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("TakenPhotoStage17Check")){
		                		TakenPhotoStage17Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldTakenPhotoStage17")){
		                		oldTakenPhotoStage17 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("SystemTakenCheck")){
		                		SystemTakenCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldSystemTaken")){
		                		oldSystemTaken = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in DOL-Stage17 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("TakenPhotoStage17")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage17 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  TakenPhotoStage17File = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("SystemTaken")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage17 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  SystemTakenFile = fileLocation;
					          }
					        
					          System.out.println("File name side Stage17 action --------->"+name);
					        if((TakenPhotoStage17Check == null || TakenPhotoStage17Check == "" || TakenPhotoStage17Check.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("TakenPhotoStage17"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldTakenPhotoStage17-------------in-----if TakenPhotoStage17Check--Stage17 action--->"+oldTakenPhotoStage17);
						        	  TakenPhotoStage17File = oldTakenPhotoStage17;
				             }
						    if((SystemTakenCheck == null || SystemTakenCheck == "" || SystemTakenCheck.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("SystemTaken"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldSystemTaken-------------in-----if additionalfileCheck--Stage17 action--->"+oldSystemTaken);
						        	  SystemTakenFile = oldSystemTaken;
				             }
				                  
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && PhotoTime != null && TakenPhotoStage17File != null && SystemTakenFile != null){
	            	
	            	//String dolBPELStatus = null;
	            	//DOLClientStage17 clientStage17 = new DOLClientStage17();
	            	//dolBPELStatus = clientStage17.stage17dos(empUniqueId, visaformFile, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	//if(dolBPELStatus != null && dolBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertDOLStageSeventeenDetails(empUniqueId,createDate,acknowledgementDate,systemDate,
							approveStatus,PhotoTime,TakenPhotoStage17File,SystemTakenFile);
			       // }else{
			        //    	throw new Exception("Stage Seventeen Was Failed in BPEL Process");
			       // }
		            
	            System.out.println("insertStatus in Department of Labor Action-Stage17::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOLStageSeventeenData = new ArrayList();
			            DOLStageSeventeenData = db.getDOLStageSeventeenDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOL_id = null;
		            	String emp_unique_id = null;
		            	String stage17CreateDate = null;
		     		    String stage17AcknowledgementDate = null;
		     		    String stage17SystemDate = null;
		     		    String stage17ApproveStatus = null;
						String stage17PhotoTime = null;
		            	String stage17Data = null;
		            	String stage1_status = null;
			        	String stage2_status = null;
			        	String stage3_status = null;
			        	String stage4_status = null;
			        	String stage5_status = null;
			        	String stage6_status = null;
			        	String stage7_status = null;
			        	String stage8_status = null;
			        	String stage9_status = null;
			        	String stage10_status = null;
			        	String stage11_status = null;
			        	String stage12_status = null;
			        	String stage13_status = null;
			        	String stage14_status = null;
			        	String stage15_status = null;
			        	String stage16_status = null;
			        	String stage17_status = null;
			        	String stage18_status = null;
			        	String stage19_status = null;
			        	String stage20_status = null;
			            String[] stageSeventeenDataArray = null;
			            String[] stageSeventeenStatusArray = null;
			            if(DOLStageSeventeenData!=null && DOLStageSeventeenData.size()!=0){
			            	
							Iterator itr = DOLStageSeventeenData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOL_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage17Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stage7_status = TempList[9];
							    stage8_status = TempList[10];
							    stage9_status = TempList[11];
							    stage10_status = TempList[12];
							    stage11_status = TempList[13];
							    stage12_status = TempList[14];
							    stage13_status = TempList[15];
							    stage14_status = TempList[16];
							    stage15_status = TempList[17];
							    stage16_status = TempList[18];
							    stage17_status = TempList[19];
							    stage18_status = TempList[20];
							    stage19_status = TempList[21];
							    stage20_status = TempList[22];
							    stageSeventeenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
							    
							    if(stage17Data != null && stage17Data != "" && !(stage17Data.equalsIgnoreCase(""))){
				        			String []tempStage17DataArray = stage17Data.split("-sep-");
				        			stageSeventeenDataArray = new String[tempStage17DataArray.length - 4];
				        			stage17CreateDate = tempStage17DataArray[0];
				        			stage17AcknowledgementDate = tempStage17DataArray[1];
				        			stage17SystemDate = tempStage17DataArray[2];
				        			stage17ApproveStatus = tempStage17DataArray[3];
									stage17PhotoTime = tempStage17DataArray[4];
				        			for(int j=0;j<(tempStage17DataArray.length - 5);j++){
				        				stageSeventeenDataArray[j] = tempStage17DataArray[5 + j];
				        				System.out.println("Department of Labor Action-Stage17----->stageSeventeenDataArray["+j+"]-------------->"+stageSeventeenDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOL_id", unique_DOL_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage17CreateDate", stage17CreateDate);
			            request.setAttribute("stage17AcknowledgementDate", stage17AcknowledgementDate);
			            request.setAttribute("stage17SystemDate", stage17SystemDate);
			            request.setAttribute("stage17ApproveStatus", stage17ApproveStatus);
						request.setAttribute("stage17PhotoTime", stage17PhotoTime);
			            if(empUniqueId != null){
			    			ArrayList employeeDetails = new ArrayList();
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageSeventeenDataArray", stageSeventeenDataArray);
			            request.setAttribute("stageSeventeenStatusArray", stageSeventeenStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOL/frmDepartmentLabourStage17");
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
    	   return new ModelAndView("DOL/frmDepartmentLabourStage17");
	       
	}
	
	@RequestMapping("/initDOLStage18.html")
	public ModelAndView initDOLStage18(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOLStageEighteenData = new ArrayList();
            DOLStageEighteenData = db.getDOLStageEighteenDetailsByUniqueEmpId(empUniqueId);
            
            if(DOLStageEighteenData != null && DOLStageEighteenData.size() > 0){
            
	            String unique_DOL_id = null;
	        	String emp_unique_id = null;
	        	String stage18CreateDate = null;
	 		    String stage18AcknowledgementDate = null;
	 		    String stage18SystemDate = null;
	 		    String stage18ApproveStatus = null;
	        	String stage18Data = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	        	String stage7_status = null;
	        	String stage8_status = null;
	        	String stage9_status = null;
	        	String stage10_status = null;
	        	String stage11_status = null;
	        	String stage12_status = null;
	        	String stage13_status = null;
	        	String stage14_status = null;
	        	String stage15_status = null;
	        	String stage16_status = null;
	        	String stage17_status = null;
	        	String stage18_status = null;
	        	String stage19_status = null;
	        	String stage20_status = null;
	            String[] stageEighteenDataArray = null;
	            String[] stageEighteenStatusArray = null;
	            if(DOLStageEighteenData!=null && DOLStageEighteenData.size()!=0){
	            	
					Iterator itr = DOLStageEighteenData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOL_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage18Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stage7_status = TempList[9];
					    stage8_status = TempList[10];
					    stage9_status = TempList[11];
					    stage10_status = TempList[12];
					    stage11_status = TempList[13];
					    stage12_status = TempList[14];
					    stage13_status = TempList[15];
					    stage14_status = TempList[16];
					    stage15_status = TempList[17];
					    stage16_status = TempList[18];
					    stage17_status = TempList[19];
					    stage18_status = TempList[20];
					    stage19_status = TempList[21];
					    stage20_status = TempList[22];
					    
					    stageEighteenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
					    
					    if(stage18Data != null && stage18Data != "" && !(stage18Data.equalsIgnoreCase(""))){
		        			String []tempStage18DataArray = stage18Data.split("-sep-");
		        			stageEighteenDataArray = new String[tempStage18DataArray.length - 4];
		        			stage18CreateDate = tempStage18DataArray[0];
		        			stage18AcknowledgementDate = tempStage18DataArray[1];
		        			stage18SystemDate = tempStage18DataArray[2];
		        			stage18ApproveStatus = tempStage18DataArray[3];
		        			for(int j=0;j<(tempStage18DataArray.length - 4);j++){
		        				stageEighteenDataArray[j] = tempStage18DataArray[4 + j];
		        				System.out.println("Department of Labor Action-Stage18----->stageEighteenDataArray["+j+"]-------------->"+stageEighteenDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageEighteenStatusArray", stageEighteenStatusArray);
		        				return new ModelAndView("DOL/frmDepartmentLabourStage18");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_DOL_id", unique_DOL_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage18CreateDate", stage18CreateDate);
	            request.setAttribute("stage18AcknowledgementDate", stage18AcknowledgementDate);
	            request.setAttribute("stage18SystemDate", stage18SystemDate);
	            request.setAttribute("stage18ApproveStatus", stage18ApproveStatus);
	            if(empUniqueId != null){
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageEighteenDataArray", stageEighteenDataArray);
	            request.setAttribute("stageEighteenStatusArray", stageEighteenStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOL/frmDepartmentLabourStage18");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOL/frmDepartmentLabourStage18");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDOLStage18.html")
	public ModelAndView doDOLStage18(HttpServletRequest request,
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
    		   
    		   String oldLCAapprovedDoc = null;
    		   String oldCustomDocsStage18 = "";
    		   String LCAapprovedDocCheck = null;
    		   String CustomDocsStage18Check = null;
    		   
    		   String LCAapprovedDocFile = null;
    		   String CustomDocsStage18File = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfLabor");
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
		                	if(parmName.equals("LCAapprovedDocCheck")){
		                		LCAapprovedDocCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldLCAapprovedDoc")){
		                		oldLCAapprovedDoc = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("CustomDocsStage18Check")){
		                		CustomDocsStage18Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldCustomDocsStage18")){
		                		oldCustomDocsStage18 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in DOL-Stage18 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("LCAapprovedDoc")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage18 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  LCAapprovedDocFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("CustomDocsStage18")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage18 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  CustomDocsStage18File = fileLocation;
					          }
					        
					          System.out.println("File name side Stage18 action --------->"+name);
					        if((LCAapprovedDocCheck == null || LCAapprovedDocCheck == "" || LCAapprovedDocCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("LCAapprovedDoc"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldLCAapprovedDoc-------------in-----if LCAapprovedDocCheck--Stage18 action--->"+oldLCAapprovedDoc);
						        	  LCAapprovedDocFile = oldLCAapprovedDoc;
				             }
						    if((CustomDocsStage18Check == null || CustomDocsStage18Check == "" || CustomDocsStage18Check.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("CustomDocsStage18"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldCustomDocsStage18-------------in-----if additionalfileCheck--Stage18 action--->"+oldCustomDocsStage18);
						        	  CustomDocsStage18File = oldCustomDocsStage18;
				             }
				                  
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && LCAapprovedDocFile != null){
	            	
	            	//String dolBPELStatus = null;
	            	//DOLClientStage18 clientStage18 = new DOLClientStage18();
	            	//dolBPELStatus = clientStage18.stage18dos(empUniqueId, visaformFile, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	//if(dolBPELStatus != null && dolBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertDOLStageEighteenDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,LCAapprovedDocFile,CustomDocsStage18File);
			       // }else{
			        //    	throw new Exception("Stage Eighteen Was Failed in BPEL Process");
			       // }
		            
	            System.out.println("insertStatus in Department of Labor Action-Stage18::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOLStageEighteenData = new ArrayList();
			            DOLStageEighteenData = db.getDOLStageEighteenDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOL_id = null;
		            	String emp_unique_id = null;
		            	String stage18CreateDate = null;
		     		    String stage18AcknowledgementDate = null;
		     		    String stage18SystemDate = null;
		     		    String stage18ApproveStatus = null;
		            	String stage18Data = null;
		            	String stage1_status = null;
			        	String stage2_status = null;
			        	String stage3_status = null;
			        	String stage4_status = null;
			        	String stage5_status = null;
			        	String stage6_status = null;
			        	String stage7_status = null;
			        	String stage8_status = null;
			        	String stage9_status = null;
			        	String stage10_status = null;
			        	String stage11_status = null;
			        	String stage12_status = null;
			        	String stage13_status = null;
			        	String stage14_status = null;
			        	String stage15_status = null;
			        	String stage16_status = null;
			        	String stage17_status = null;
			        	String stage18_status = null;
			        	String stage19_status = null;
			        	String stage20_status = null;
			            String[] stageEighteenDataArray = null;
			            String[] stageEighteenStatusArray = null;
			            if(DOLStageEighteenData!=null && DOLStageEighteenData.size()!=0){
			            	
							Iterator itr = DOLStageEighteenData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOL_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage18Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stage7_status = TempList[9];
							    stage8_status = TempList[10];
							    stage9_status = TempList[11];
							    stage10_status = TempList[12];
							    stage11_status = TempList[13];
							    stage12_status = TempList[14];
							    stage13_status = TempList[15];
							    stage14_status = TempList[16];
							    stage15_status = TempList[17];
							    stage16_status = TempList[18];
							    stage17_status = TempList[19];
							    stage18_status = TempList[20];
							    stage19_status = TempList[21];
							    stage20_status = TempList[22];
							    stageEighteenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
							    
							    if(stage18Data != null && stage18Data != "" && !(stage18Data.equalsIgnoreCase(""))){
				        			String []tempStage18DataArray = stage18Data.split("-sep-");
				        			stageEighteenDataArray = new String[tempStage18DataArray.length - 4];
				        			stage18CreateDate = tempStage18DataArray[0];
				        			stage18AcknowledgementDate = tempStage18DataArray[1];
				        			stage18SystemDate = tempStage18DataArray[2];
				        			stage18ApproveStatus = tempStage18DataArray[3];
				        			for(int j=0;j<(tempStage18DataArray.length - 4);j++){
				        				stageEighteenDataArray[j] = tempStage18DataArray[4 + j];
				        				System.out.println("Department of Labor Action-Stage18----->stageEighteenDataArray["+j+"]-------------->"+stageEighteenDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOL_id", unique_DOL_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage18CreateDate", stage18CreateDate);
			            request.setAttribute("stage18AcknowledgementDate", stage18AcknowledgementDate);
			            request.setAttribute("stage18SystemDate", stage18SystemDate);
			            request.setAttribute("stage18ApproveStatus", stage18ApproveStatus);
			            if(empUniqueId != null){
			    			ArrayList employeeDetails = new ArrayList();
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageEighteenDataArray", stageEighteenDataArray);
			            request.setAttribute("stageEighteenStatusArray", stageEighteenStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOL/frmDepartmentLabourStage18");
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
    	   return new ModelAndView("DOL/frmDepartmentLabourStage18");
	       
	}
	
	@RequestMapping("/initDOLStage19.html")
	public ModelAndView initDOLStage19(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOLStageNinetenData = new ArrayList();
            DOLStageNinetenData = db.getDOLStageNineteenDetailsByUniqueEmpId(empUniqueId);
            
            if(DOLStageNinetenData != null && DOLStageNinetenData.size() > 0){
            
	            String unique_DOL_id = null;
	        	String emp_unique_id = null;
	        	String stage19CreateDate = null;
	 		    String stage19AcknowledgementDate = null;
	 		    String stage19SystemDate = null;
	 		    String stage19ApproveStatus = null;
	        	String stage19Data = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	        	String stage7_status = null;
	        	String stage8_status = null;
	        	String stage9_status = null;
	        	String stage10_status = null;
	        	String stage11_status = null;
	        	String stage12_status = null;
	        	String stage13_status = null;
	        	String stage14_status = null;
	        	String stage15_status = null;
	        	String stage16_status = null;
	        	String stage17_status = null;
	        	String stage18_status = null;
	        	String stage19_status = null;
	        	String stage20_status = null;
	            String[] stageNinetenDataArray = null;
	            String[] stageNinetenStatusArray = null;
	            if(DOLStageNinetenData!=null && DOLStageNinetenData.size()!=0){
	            	
					Iterator itr = DOLStageNinetenData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOL_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage19Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stage7_status = TempList[9];
					    stage8_status = TempList[10];
					    stage9_status = TempList[11];
					    stage10_status = TempList[12];
					    stage11_status = TempList[13];
					    stage12_status = TempList[14];
					    stage13_status = TempList[15];
					    stage14_status = TempList[16];
					    stage15_status = TempList[17];
					    stage16_status = TempList[18];
					    stage17_status = TempList[19];
					    stage18_status = TempList[20];
					    stage19_status = TempList[21];
					    stage20_status = TempList[22];
					    
					    stageNinetenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
					    
					    if(stage19Data != null && stage19Data != "" && !(stage19Data.equalsIgnoreCase(""))){
		        			String []tempStage19DataArray = stage19Data.split("-sep-");
		        			stageNinetenDataArray = new String[tempStage19DataArray.length - 4];
		        			stage19CreateDate = tempStage19DataArray[0];
		        			stage19AcknowledgementDate = tempStage19DataArray[1];
		        			stage19SystemDate = tempStage19DataArray[2];
		        			stage19ApproveStatus = tempStage19DataArray[3];
		        			for(int j=0;j<(tempStage19DataArray.length - 4);j++){
		        				stageNinetenDataArray[j] = tempStage19DataArray[4 + j];
		        				System.out.println("Department of Labor Action-Stage19----->stageNinetenDataArray["+j+"]-------------->"+stageNinetenDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageNinetenStatusArray", stageNinetenStatusArray);
		        				return new ModelAndView("DOL/frmDepartmentLabourStage19");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_DOL_id", unique_DOL_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage19CreateDate", stage19CreateDate);
	            request.setAttribute("stage19AcknowledgementDate", stage19AcknowledgementDate);
	            request.setAttribute("stage19SystemDate", stage19SystemDate);
	            request.setAttribute("stage19ApproveStatus", stage19ApproveStatus);
	            if(empUniqueId != null){
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageNinetenDataArray", stageNinetenDataArray);
	            request.setAttribute("stageNinetenStatusArray", stageNinetenStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOL/frmDepartmentLabourStage19");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOL/frmDepartmentLabourStage19");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDOLStage19.html")
	public ModelAndView doDOLStage19(HttpServletRequest request,
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
    		   
    		   String oldEmailProcess = null;
    		   String oldSystemRecord = null;
			   String oldMailPosting = null;
    		   String EmailProcessCheck = null;
    		   String SystemRecordCheck = null;
			   String MailPostingCheck = null;
    		   
    		   String EmailProcessFile = null;
    		   String SystemRecordFile = null;
			   String MailPostingFile = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfLabor");
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
		                	if(parmName.equals("EmailProcessCheck")){
		                		EmailProcessCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldEmailProcess")){
		                		oldEmailProcess = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("SystemRecordCheck")){
		                		SystemRecordCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldSystemRecord")){
		                		oldSystemRecord = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
							if(parmName.equals("MailPostingCheck")){
		                		MailPostingCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldMailPosting")){
		                		oldMailPosting = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in DOL-Stage19 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("EmailProcess")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage19 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  EmailProcessFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("SystemRecord")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage19 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  SystemRecordFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("MailPosting")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage19 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  MailPostingFile = fileLocation;
					          }
					        
					          System.out.println("File name side Stage19 action --------->"+name);
					        if((EmailProcessCheck == null || EmailProcessCheck == "" || EmailProcessCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("EmailProcess"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldEmailProcess-------------in-----if EmailProcessCheck--Stage19 action--->"+oldEmailProcess);
						        	  EmailProcessFile = oldEmailProcess;
				             }
						    if((SystemRecordCheck == null || SystemRecordCheck == "" || SystemRecordCheck.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("SystemRecord"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldSystemRecord-------------in-----if additionalfileCheck--Stage19 action--->"+oldSystemRecord);
						        	  SystemRecordFile = oldSystemRecord;
				             }
							 if((MailPostingCheck == null || MailPostingCheck == "" || MailPostingCheck.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("MailPosting"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldMailPosting-------------in-----if additionalfileCheck--Stage19 action--->"+oldMailPosting);
						        	  MailPostingFile = oldMailPosting;
				             }
				                  
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && EmailProcessFile != null && SystemRecordFile != null && MailPostingFile != null){
	            	
	            	//String dolBPELStatus = null;
	            	//DOLClientStage19 clientStage19 = new DOLClientStage19();
	            	//dolBPELStatus = clientStage19.stage19dos(empUniqueId, visaformFile, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	//if(dolBPELStatus != null && dolBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertDOLStageNineteenDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,
							EmailProcessFile,SystemRecordFile,MailPostingFile);
			       // }else{
			        //    	throw new Exception("Stage Nineten Was Failed in BPEL Process");
			       // }
		            
	            System.out.println("insertStatus in Department of Labor Action-Stage19::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOLStageNinetenData = new ArrayList();
			            DOLStageNinetenData = db.getDOLStageNineteenDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOL_id = null;
		            	String emp_unique_id = null;
		            	String stage19CreateDate = null;
		     		    String stage19AcknowledgementDate = null;
		     		    String stage19SystemDate = null;
		     		    String stage19ApproveStatus = null;
		            	String stage19Data = null;
		            	String stage1_status = null;
			        	String stage2_status = null;
			        	String stage3_status = null;
			        	String stage4_status = null;
			        	String stage5_status = null;
			        	String stage6_status = null;
			        	String stage7_status = null;
			        	String stage8_status = null;
			        	String stage9_status = null;
			        	String stage10_status = null;
			        	String stage11_status = null;
			        	String stage12_status = null;
			        	String stage13_status = null;
			        	String stage14_status = null;
			        	String stage15_status = null;
			        	String stage16_status = null;
			        	String stage17_status = null;
			        	String stage18_status = null;
			        	String stage19_status = null;
			        	String stage20_status = null;
			            String[] stageNinetenDataArray = null;
			            String[] stageNinetenStatusArray = null;
			            if(DOLStageNinetenData!=null && DOLStageNinetenData.size()!=0){
			            	
							Iterator itr = DOLStageNinetenData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOL_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage19Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stage7_status = TempList[9];
							    stage8_status = TempList[10];
							    stage9_status = TempList[11];
							    stage10_status = TempList[12];
							    stage11_status = TempList[13];
							    stage12_status = TempList[14];
							    stage13_status = TempList[15];
							    stage14_status = TempList[16];
							    stage15_status = TempList[17];
							    stage16_status = TempList[18];
							    stage17_status = TempList[19];
							    stage18_status = TempList[20];
							    stage19_status = TempList[21];
							    stage20_status = TempList[22];
							    stageNinetenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
							    
							    if(stage19Data != null && stage19Data != "" && !(stage19Data.equalsIgnoreCase(""))){
				        			String []tempStage19DataArray = stage19Data.split("-sep-");
				        			stageNinetenDataArray = new String[tempStage19DataArray.length - 4];
				        			stage19CreateDate = tempStage19DataArray[0];
				        			stage19AcknowledgementDate = tempStage19DataArray[1];
				        			stage19SystemDate = tempStage19DataArray[2];
				        			stage19ApproveStatus = tempStage19DataArray[3];
				        			for(int j=0;j<(tempStage19DataArray.length - 4);j++){
				        				stageNinetenDataArray[j] = tempStage19DataArray[4 + j];
				        				System.out.println("Department of Labor Action-Stage19----->stageNinetenDataArray["+j+"]-------------->"+stageNinetenDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOL_id", unique_DOL_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage19CreateDate", stage19CreateDate);
			            request.setAttribute("stage19AcknowledgementDate", stage19AcknowledgementDate);
			            request.setAttribute("stage19SystemDate", stage19SystemDate);
			            request.setAttribute("stage19ApproveStatus", stage19ApproveStatus);
			            if(empUniqueId != null){
			    			ArrayList employeeDetails = new ArrayList();
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageNinetenDataArray", stageNinetenDataArray);
			            request.setAttribute("stageNinetenStatusArray", stageNinetenStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOL/frmDepartmentLabourStage19");
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
    	   return new ModelAndView("DOL/frmDepartmentLabourStage19");
	       
	}
	
	@RequestMapping("/initDOLStage20.html")
	public ModelAndView initDOLStage20(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList DOLStageTwentyData = new ArrayList();
            DOLStageTwentyData = db.getDOLStageTwentyDetailsByUniqueEmpId(empUniqueId);
            
            if(DOLStageTwentyData != null && DOLStageTwentyData.size() > 0){
            
	            String unique_DOL_id = null;
	        	String emp_unique_id = null;
	        	String stage20CreateDate = null;
	 		    String stage20AcknowledgementDate = null;
	 		    String stage20SystemDate = null;
	 		    String stage20ApproveStatus = null;
	        	String stage20Data = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	        	String stage7_status = null;
	        	String stage8_status = null;
	        	String stage9_status = null;
	        	String stage10_status = null;
	        	String stage11_status = null;
	        	String stage12_status = null;
	        	String stage13_status = null;
	        	String stage14_status = null;
	        	String stage15_status = null;
	        	String stage16_status = null;
	        	String stage17_status = null;
	        	String stage18_status = null;
	        	String stage19_status = null;
	        	String stage20_status = null;
	            String[] stageTwentyDataArray = null;
	            String[] stageTwentyStatusArray = null;
	            if(DOLStageTwentyData!=null && DOLStageTwentyData.size()!=0){
	            	
					Iterator itr = DOLStageTwentyData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_DOL_id = TempList[0];
					    emp_unique_id = TempList[1];
					    stage20Data = TempList[2];
					    stage1_status = TempList[3];
					    stage2_status = TempList[4];
					    stage3_status = TempList[5];
					    stage4_status = TempList[6];
					    stage5_status = TempList[7];
					    stage6_status = TempList[8];
					    stage7_status = TempList[9];
					    stage8_status = TempList[10];
					    stage9_status = TempList[11];
					    stage10_status = TempList[12];
					    stage11_status = TempList[13];
					    stage12_status = TempList[14];
					    stage13_status = TempList[15];
					    stage14_status = TempList[16];
					    stage15_status = TempList[17];
					    stage16_status = TempList[18];
					    stage17_status = TempList[19];
					    stage18_status = TempList[20];
					    stage19_status = TempList[21];
					    stage20_status = TempList[22];
					    
					    stageTwentyStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
					    
					    if(stage20Data != null && stage20Data != "" && !(stage20Data.equalsIgnoreCase(""))){
		        			String []tempStage20DataArray = stage20Data.split("-sep-");
		        			stageTwentyDataArray = new String[tempStage20DataArray.length - 4];
		        			stage20CreateDate = tempStage20DataArray[0];
		        			stage20AcknowledgementDate = tempStage20DataArray[1];
		        			stage20SystemDate = tempStage20DataArray[2];
		        			stage20ApproveStatus = tempStage20DataArray[3];
		        			for(int j=0;j<(tempStage20DataArray.length - 4);j++){
		        				stageTwentyDataArray[j] = tempStage20DataArray[4 + j];
		        				System.out.println("Department of Labor Action-Stage20----->stageTwentyDataArray["+j+"]-------------->"+stageTwentyDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageTwentyStatusArray", stageTwentyStatusArray);
		        				return new ModelAndView("DOL/frmDepartmentLabourStage20");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_DOL_id", unique_DOL_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage20CreateDate", stage20CreateDate);
	            request.setAttribute("stage20AcknowledgementDate", stage20AcknowledgementDate);
	            request.setAttribute("stage20SystemDate", stage20SystemDate);
	            request.setAttribute("stage20ApproveStatus", stage20ApproveStatus);
	            if(empUniqueId != null){
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageTwentyDataArray", stageTwentyDataArray);
	            request.setAttribute("stageTwentyStatusArray", stageTwentyStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("DOL/frmDepartmentLabourStage20");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("DOL/frmDepartmentLabourStage20");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
	}
	
	@RequestMapping("/doDOLStage20.html")
	public ModelAndView doDOLStage20(HttpServletRequest request,
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
    		   
    		   String oldEmployeeAgreement = null;
    		   String oldCustomDocsStage20 = "";
    		   String EmployeeAgreementCheck = null;
    		   String CustomDocsStage20Check = null;
    		   
    		   String EmployeeAgreementFile = null;
    		   String CustomDocsStage20File = null;
    		   
    		   String empUniqueId = null;
    		   String empId = null;
    		   String createDate = null;
    		   String acknowledgementDate = null;
    		   String systemDate = null;
    		   String approveStatus = null;
    		   
    		        
        	   String status = null;	
        	   String fileLocation = null;
        	   String UPLOAD_DIRECTORY = null;
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.DepartmentOfLabor");
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
		                	if(parmName.equals("EmployeeAgreementCheck")){
		                		EmployeeAgreementCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldEmployeeAgreement")){
		                		oldEmployeeAgreement = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("CustomDocsStage20Check")){
		                		CustomDocsStage20Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldCustomDocsStage20")){
		                		oldCustomDocsStage20 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in DOL-Stage20 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("Access_file")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage20 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  EmployeeAgreementFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("CustomDocs1")) {
					        	  System.out.println("fPart.getFileName() in DOL-Stage20 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  CustomDocsStage20File = fileLocation;
					          }
					        
					          System.out.println("File name side Stage20 action --------->"+name);
					        if((EmployeeAgreementCheck == null || EmployeeAgreementCheck == "" || EmployeeAgreementCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("Access_file"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldEmployeeAgreement-------------in-----if EmployeeAgreementCheck--Stage20 action--->"+oldEmployeeAgreement);
						        	  EmployeeAgreementFile = oldEmployeeAgreement;
				             }
						    if((CustomDocsStage20Check == null || CustomDocsStage20Check == "" || CustomDocsStage20Check.equalsIgnoreCase(""))
						        	  && ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("CustomDocs1"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldCustomDocsStage20-------------in-----if additionalfileCheck--Stage20 action--->"+oldCustomDocsStage20);
						        	  CustomDocsStage20File = oldCustomDocsStage20;
				             }
				                  
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && EmployeeAgreementFile != null){
	            	
	            	//String dolBPELStatus = null;
	            	//DOLClientStage20 clientStage20 = new DOLClientStage20();
	            	//dolBPELStatus = clientStage20.stage20dos(empUniqueId, visaformFile, additionalfileFile, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	//if(dolBPELStatus != null && dolBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertDOLStageTwentyDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,EmployeeAgreementFile,CustomDocsStage20File);
			       // }else{
			        //    	throw new Exception("Stage Twenty Was Failed in BPEL Process");
			       // }
		            
	            System.out.println("insertStatus in Department of Labor Action-Stage20::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList DOLStageTwentyData = new ArrayList();
			            DOLStageTwentyData = db.getDOLStageTwentyDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_DOL_id = null;
		            	String emp_unique_id = null;
		            	String stage20CreateDate = null;
		     		    String stage20AcknowledgementDate = null;
		     		    String stage20SystemDate = null;
		     		    String stage20ApproveStatus = null;
		            	String stage20Data = null;
		            	String stage1_status = null;
			        	String stage2_status = null;
			        	String stage3_status = null;
			        	String stage4_status = null;
			        	String stage5_status = null;
			        	String stage6_status = null;
			        	String stage7_status = null;
			        	String stage8_status = null;
			        	String stage9_status = null;
			        	String stage10_status = null;
			        	String stage11_status = null;
			        	String stage12_status = null;
			        	String stage13_status = null;
			        	String stage14_status = null;
			        	String stage15_status = null;
			        	String stage16_status = null;
			        	String stage17_status = null;
			        	String stage18_status = null;
			        	String stage19_status = null;
			        	String stage20_status = null;
			            String[] stageTwentyDataArray = null;
			            String[] stageTwentyStatusArray = null;
			            if(DOLStageTwentyData!=null && DOLStageTwentyData.size()!=0){
			            	
							Iterator itr = DOLStageTwentyData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_DOL_id = TempList[0];
							    emp_unique_id = TempList[1];
							    stage20Data = TempList[2];
							    stage1_status = TempList[3];
							    stage2_status = TempList[4];
							    stage3_status = TempList[5];
							    stage4_status = TempList[6];
							    stage5_status = TempList[7];
							    stage6_status = TempList[8];
							    stage7_status = TempList[9];
							    stage8_status = TempList[10];
							    stage9_status = TempList[11];
							    stage10_status = TempList[12];
							    stage11_status = TempList[13];
							    stage12_status = TempList[14];
							    stage13_status = TempList[15];
							    stage14_status = TempList[16];
							    stage15_status = TempList[17];
							    stage16_status = TempList[18];
							    stage17_status = TempList[19];
							    stage18_status = TempList[20];
							    stage19_status = TempList[21];
							    stage20_status = TempList[22];
							    stageTwentyStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
							    
							    if(stage20Data != null && stage20Data != "" && !(stage20Data.equalsIgnoreCase(""))){
				        			String []tempStage20DataArray = stage20Data.split("-sep-");
				        			stageTwentyDataArray = new String[tempStage20DataArray.length - 4];
				        			stage20CreateDate = tempStage20DataArray[0];
				        			stage20AcknowledgementDate = tempStage20DataArray[1];
				        			stage20SystemDate = tempStage20DataArray[2];
				        			stage20ApproveStatus = tempStage20DataArray[3];
				        			for(int j=0;j<(tempStage20DataArray.length - 4);j++){
				        				stageTwentyDataArray[j] = tempStage20DataArray[4 + j];
				        				System.out.println("Department of Labor Action-Stage20----->stageTwentyDataArray["+j+"]-------------->"+stageTwentyDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_DOL_id", unique_DOL_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage20CreateDate", stage20CreateDate);
			            request.setAttribute("stage20AcknowledgementDate", stage20AcknowledgementDate);
			            request.setAttribute("stage20SystemDate", stage20SystemDate);
			            request.setAttribute("stage20ApproveStatus", stage20ApproveStatus);
			            if(empUniqueId != null){
			    			ArrayList employeeDetails = new ArrayList();
			    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
			    	        request.setAttribute("employeeDetails",null);
			    	        request.setAttribute("employeeDetails",employeeDetails);
			            }
			            request.setAttribute("stageTwentyDataArray", stageTwentyDataArray);
			            request.setAttribute("stageTwentyStatusArray", stageTwentyStatusArray);
			            status = "success";
			            request.setAttribute("status",status);
			            request.setAttribute("pageStatus","update");
			    		return new ModelAndView("DOL/frmDepartmentLabourStage20");
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
    	   return new ModelAndView("DOL/frmDepartmentLabourStage20");
	       
	}
}

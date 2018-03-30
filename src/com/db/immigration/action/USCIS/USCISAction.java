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
package com.db.immigration.action.USCIS;

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
import com.db.immigration.action.USCIS.service.client.USCISClientStage2;
import com.db.immigration.action.USCIS.service.client.USCISClientStage3;
import com.db.immigration.action.USCIS.service.client.USCISClientStage1;
import com.db.immigration.action.USCIS.service.client.USCISClientStage4;
import com.db.immigration.action.USCIS.service.client.USCISClientStage5;
import com.db.immigration.action.USCIS.service.client.USCISClientStage6;
import com.db.immigration.action.USCIS.service.client.USCISClientStage7;
import com.db.immigration.action.USCIS.service.client.USCISClientStage8;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

@Controller

public class USCISAction {
	
	public static final String config_prop = "config.properties";
	public static final String LOG_FILE = "Log4j.properties";
	InputStream is;
	InputStream config_inputStream;
	
	@RequestMapping("/initUSCISStage1.html")
	public ModelAndView initUSCISStage1(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList USCISStageOneData = new ArrayList();
            USCISStageOneData = db.getUSCISStageOneDetailsByUniqueEmpId(empUniqueId);
            
            if(USCISStageOneData != null && USCISStageOneData.size() > 0){
            
	            String unique_USCIS_id = null;
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
	            String[] stageOneDataArray = null;
	            String[] stageOneStatusArray = null;
	            if(USCISStageOneData!=null && USCISStageOneData.size()!=0){
	            	
					Iterator itr = USCISStageOneData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_USCIS_id = TempList[0];
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
					    stageOneStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
					    
					    if(stage1Data != null && stage1Data != "" && !(stage1Data.equalsIgnoreCase(""))){
		        			String []tempStage1DataArray = stage1Data.split("-sep-");
		        			stageOneDataArray = new String[tempStage1DataArray.length - 4];
		        			stage1CreateDate = tempStage1DataArray[0];
		        			stage1AcknowledgementDate = tempStage1DataArray[1];
		        			stage1SystemDate = tempStage1DataArray[2];
		        			stage1ApproveStatus = tempStage1DataArray[3];
		        			for(int j=0;j<(tempStage1DataArray.length - 4);j++){
		        				stageOneDataArray[j] = tempStage1DataArray[4 + j];
		        				System.out.println("USCISAction-Stage1----->stageOneDataArray["+j+"]-------------->"+stageOneDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageOneStatusArray", stageOneStatusArray);
		        				return new ModelAndView("USCIS/frmUSCISStage1");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_USCIS_id", unique_USCIS_id);
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
	    		return new ModelAndView("USCIS/frmUSCISStage1");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("USCIS/frmUSCISStage1");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
		
	}
	
	@RequestMapping("/doUSCISStage1.html")
	public ModelAndView doUSCISStage1(HttpServletRequest request,
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
    		   
    		   String oldContractForm = null;
    		   String oldEmploymentProof = null;
    		   String oldClientLetter = null;
    		   String oldWorkOrder = null;
    		   String oldLabourClearance = null;
    		   
    		   String ContractFormCheck = null;
    		   String EmploymentProofCheck = null;
    		   String ClientLetterCheck = null;
    		   String WorkOrderCheck = null;
    		   String LabourClearanceCheck = null;
    		   
    		   String ContractFormFile = null;
    		   String EmploymentProofFile = null;
    		   String ClientLetterFile = null;
    		   String WorkOrderFile = null;
    		   String LabourClearanceFile = null;
    		   
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
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.USCIS");
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
		                	if(parmName.equals("ContractFormCheck")){
		                		ContractFormCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldContractForm")){
		                		oldContractForm = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		         		   
		                	if(parmName.equals("EmploymentProofCheck")){
		                		EmploymentProofCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldEmploymentProof")){
		                		oldEmploymentProof = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("ClientLetterCheck")){
		                		ClientLetterCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldClientLetter")){
		                		oldClientLetter = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("WorkOrderCheck")){
		                		WorkOrderCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldWorkOrder")){
		                		oldWorkOrder = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("LabourClearanceCheck")){
		                		LabourClearanceCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldLabourClearance")){
		                		oldLabourClearance = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in USCIS-Stage1 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("ContractForm")) {
					        	  System.out.println("fPart.getFileName() in USCIS-Stage1 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  //dosFileList.add(fileLocation);
					        	  ContractFormFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("EmploymentProof")) {
					        	  System.out.println("fPart.getFileName() in USCIS-Stage1 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  //dosFileList.add(fileLocation);
					        	  EmploymentProofFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("ClientLetter")) {
					        	  System.out.println("fPart.getFileName() in USCIS-Stage1 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  //dosFileList.add(fileLocation);
					        	  ClientLetterFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("WorkOrder")) {
					        	  System.out.println("fPart.getFileName() in USCIS-Stage1 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  //dosFileList.add(fileLocation);
					        	  WorkOrderFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("LabourClearance")) {
					        	  System.out.println("fPart.getFileName() in USCIS-Stage1 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  //dosFileList.add(fileLocation);
					        	  LabourClearanceFile = fileLocation;
					          }
					        
					          System.out.println("File name side Stage1 action --------->"+name);
					        if((ContractFormCheck == null || ContractFormCheck == "" || ContractFormCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
				                    		(fPart.getName().equalsIgnoreCase("ContractForm"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldContractForm-------------in-----if ContractFormCheck--Stage1 action--->"+oldContractForm);
						        	  //System.out.println("oldAdditionalfile-------------in-----else--Stage1 action--->"+oldAdditionalfile);
						        	//dosFileList.add(fileLocation);
						        	  ContractFormFile = oldContractForm;
				             }
					        if((EmploymentProofCheck == null || EmploymentProofCheck == "" || EmploymentProofCheck.equalsIgnoreCase("")) &&
					        		(name == null || name.equalsIgnoreCase("") || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("EmploymentProof"))){
			                      //resp = "<br>The user did not upload a file for this part.";
					        	  System.out.println("oldEmploymentProof-------------in-----if EmploymentProofCheck--Stage1 action--->"+oldEmploymentProof);
					        	  //System.out.println("oldAdditionalfile-------------in-----else--Stage1 action--->"+oldAdditionalfile);
					        	//dosFileList.add(fileLocation);
					        	  EmploymentProofFile = oldEmploymentProof;
			             }
					        if((ClientLetterCheck == null || ClientLetterCheck == "" || ClientLetterCheck.equalsIgnoreCase("")) &&
					        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("ClientLetter"))){
			                      //resp = "<br>The user did not upload a file for this part.";
					        	  System.out.println("oldClientLetter-------------in-----if ClientLetterCheck--Stage1 action--->"+oldClientLetter);
					        	  //System.out.println("oldAdditionalfile-------------in-----else--Stage1 action--->"+oldAdditionalfile);
					        	//dosFileList.add(fileLocation);
					        	  ClientLetterFile = oldClientLetter;
			             }
					        if((WorkOrderCheck == null || WorkOrderCheck == "" || WorkOrderCheck.equalsIgnoreCase("")) &&
					        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("WorkOrder"))){
			                      //resp = "<br>The user did not upload a file for this part.";
					        	  System.out.println("oldWorkOrder-------------in-----if WorkOrderCheck--Stage1 action--->"+oldWorkOrder);
					        	  //System.out.println("oldAdditionalfile-------------in-----else--Stage1 action--->"+oldAdditionalfile);
					        	//dosFileList.add(fileLocation);
					        	  WorkOrderFile = oldWorkOrder;
			             }
					        if((LabourClearanceCheck == null || LabourClearanceCheck == "" || LabourClearanceCheck.equalsIgnoreCase("")) &&
					        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("LabourClearance"))){
			                      //resp = "<br>The user did not upload a file for this part.";
					        	  System.out.println("oldLabourClearance-------------in-----if LabourClearanceCheck--Stage1 action--->"+oldLabourClearance);
					        	  //System.out.println("oldAdditionalfile-------------in-----else--Stage1 action--->"+oldAdditionalfile);
					        	//dosFileList.add(fileLocation);
					        	  LabourClearanceFile = oldLabourClearance;
			             }
						      
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && ContractFormFile != null && EmploymentProofFile != null &&
	            		ClientLetterFile != null && WorkOrderFile != null && LabourClearanceFile != null){
	            	
	            	String dosBPELStatus = null;
	            	USCISClientStage1 clientStage1 = new USCISClientStage1();
	            	dosBPELStatus = clientStage1.stage1USCIS(empUniqueId, ContractFormFile, EmploymentProofFile,
	            			ClientLetterFile, WorkOrderFile, LabourClearanceFile, createDate, acknowledgementDate,
	            			systemDate, approveStatus,"","");
			            
	            	if(dosBPELStatus != null && dosBPELStatus.equalsIgnoreCase("Success1")){	
			            	insertStatus = db.insertUSCISStageOneDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,
			            			ContractFormFile,EmploymentProofFile,ClientLetterFile,WorkOrderFile,LabourClearanceFile);
			        }else{
			            	throw new Exception("Stage One Was Failed in BPEL Process");
			        }
		            
	            System.out.println("insertStatus in USCISAction-Stage1::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList USCISStageOneData = new ArrayList();
			            USCISStageOneData = db.getUSCISStageOneDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_USCIS_id = null;
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
			            String[] stageOneDataArray = null;
			            String[] stageOneStatusArray = null;
			            if(USCISStageOneData!=null && USCISStageOneData.size()!=0){
			            	
							Iterator itr = USCISStageOneData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_USCIS_id = TempList[0];
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
							    stageOneStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
							    
							    if(stage1Data != null && stage1Data != "" && !(stage1Data.equalsIgnoreCase(""))){
				        			String []tempStage1DataArray = stage1Data.split("-sep-");
				        			stageOneDataArray = new String[tempStage1DataArray.length - 4];
				        			stage1CreateDate = tempStage1DataArray[0];
				        			stage1AcknowledgementDate = tempStage1DataArray[1];
				        			stage1SystemDate = tempStage1DataArray[2];
				        			stage1ApproveStatus = tempStage1DataArray[3];
				        			for(int j=0;j<(tempStage1DataArray.length - 4);j++){
				        				stageOneDataArray[j] = tempStage1DataArray[4 + j];
				        				System.out.println("USCISAction-Stage1----->stageOneDataArray["+j+"]-------------->"+stageOneDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_USCIS_id", unique_USCIS_id);
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
			    		return new ModelAndView("USCIS/frmUSCISStage1");
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
    	   return new ModelAndView("USCIS/frmUSCISStage1");
	       
	}
	
	@RequestMapping("/initUSCISStage2.html")
	public ModelAndView initUSCISStage2(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList USCISStageTwoData = new ArrayList();
            USCISStageTwoData = db.getUSCISStageTwoDetailsByUniqueEmpId(empUniqueId);
            
            if(USCISStageTwoData != null && USCISStageTwoData.size() > 0){
            
	            String unique_USCIS_id = null;
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
	        	String stage7_status = null;
	        	String stage8_status = null;
	            String[] stageTwoDataArray = null;
	            String[] stageTwoStatusArray = null;
	            if(USCISStageTwoData!=null && USCISStageTwoData.size()!=0){
	            	
					Iterator itr = USCISStageTwoData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_USCIS_id = TempList[0];
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
					    stageTwoStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
					    
					    if(stage2Data != null && stage2Data != "" && !(stage2Data.equalsIgnoreCase(""))){
		        			String []tempStage2DataArray = stage2Data.split("-sep-");
		        			stageTwoDataArray = new String[tempStage2DataArray.length - 4];
		        			stage2CreateDate = tempStage2DataArray[0];
		        			stage2AcknowledgementDate = tempStage2DataArray[1];
		        			stage2SystemDate = tempStage2DataArray[2];
		        			stage2ApproveStatus = tempStage2DataArray[3];
		        			for(int j=0;j<(tempStage2DataArray.length - 4);j++){
		        				stageTwoDataArray[j] = tempStage2DataArray[4 + j];
		        				System.out.println("USCIS Action-Stage2----->stageTwoDataArray["+j+"]-------------->"+stageTwoDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageTwoStatusArray", stageTwoStatusArray);
		        				return new ModelAndView("USCIS/frmUSCISStage2");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_USCIS_id", unique_USCIS_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
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
	    		return new ModelAndView("USCIS/frmUSCISStage2");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("USCIS/frmUSCISStage2");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
		
	}
	
	@RequestMapping("/doUSCISStage2.html")
	public ModelAndView doUSCISStage2(HttpServletRequest request,
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
    		   
    		   String oldFormI_129 = null;
    		   
    		   String FormI_129Check = null;
    		   
    		   String FormI_129File = null;
    		   
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
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.USCIS");
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
		                	if(parmName.equals("FormI_129Check")){
		                		FormI_129Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldFormI_129")){
		                		oldFormI_129 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in USCIS-Stage2 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("FormI_129")) {
					        	  System.out.println("fPart.getFileName() in USCIS-Stage2 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  //dosFileList.add(fileLocation);
					        	  FormI_129File = fileLocation;
					          }
					        
					          System.out.println("File name side Stage1 action --------->"+name);
					        if((FormI_129Check == null || FormI_129Check == "" || FormI_129Check.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
				                    		(fPart.getName().equalsIgnoreCase("FormI_129"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldFormI_129-------------in-----if FormI_129Check--Stage2 action--->"+oldFormI_129);
						        	  //System.out.println("oldAdditionalfile-------------in-----else--Stage1 action--->"+oldAdditionalfile);
						        	  //dosFileList.add(oldFormI_129);
						        	  FormI_129File = oldFormI_129;
				             }
						      
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && FormI_129File != null){
	            	
	            	String dosBPELStatus = null;
	            	USCISClientStage2 clientStage2 = new USCISClientStage2();
	            	dosBPELStatus = clientStage2.stage2USCIS(FormI_129File, createDate, acknowledgementDate, systemDate, approveStatus,"","");
			            
	            	if(dosBPELStatus != null && dosBPELStatus.equalsIgnoreCase("Success2")){	
			            	insertStatus = db.insertUSCISStageTwoDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,FormI_129File);
			        }else{
			            	throw new Exception("Stage One Was Failed in BPEL Process");
			        }
		            
	            System.out.println("insertStatus in USCISAction-Stage2::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList USCISStageTwoData = new ArrayList();
			            USCISStageTwoData = db.getUSCISStageTwoDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_USCIS_id = null;
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
		            	String stage7_status = null;
		            	String stage8_status = null;
			            String[] stageTwoDataArray = null;
			            String[] stageTwoStatusArray = null;
			            if(USCISStageTwoData!=null && USCISStageTwoData.size()!=0){
			            	
							Iterator itr = USCISStageTwoData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_USCIS_id = TempList[0];
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
							    stageTwoStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
							    
							    if(stage2Data != null && stage2Data != "" && !(stage2Data.equalsIgnoreCase(""))){
				        			String []tempstage2DataArray = stage2Data.split("-sep-");
				        			stageTwoDataArray = new String[tempstage2DataArray.length - 4];
				        			stage2CreateDate = tempstage2DataArray[0];
				        			stage2AcknowledgementDate = tempstage2DataArray[1];
				        			stage2SystemDate = tempstage2DataArray[2];
				        			stage2ApproveStatus = tempstage2DataArray[3];
				        			for(int j=0;j<(tempstage2DataArray.length - 4);j++){
				        				stageTwoDataArray[j] = tempstage2DataArray[4 + j];
				        				System.out.println("USCISAction-stage2----->stageTwoDataArray["+j+"]-------------->"+stageTwoDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_USCIS_id", unique_USCIS_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage2CreateDate", stage2CreateDate);
			            request.setAttribute("stage2AcknowledgementDate", stage2AcknowledgementDate);
			            request.setAttribute("stage2SystemDate", stage2SystemDate);
			            request.setAttribute("stage2ApproveStatus", stage2ApproveStatus);
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
			    		return new ModelAndView("USCIS/frmUSCISStage2");
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
    	   return new ModelAndView("USCIS/frmUSCISStage2");
	       
	}
	
	@RequestMapping("/initUSCISStage3.html")
	public ModelAndView initUSCISStage3(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList USCISStageThreeData = new ArrayList();
            USCISStageThreeData = db.getUSCISStageThreeDetailsByUniqueEmpId(empUniqueId);
            
            if(USCISStageThreeData != null && USCISStageThreeData.size() > 0){
            
	            String unique_USCIS_id = null;
	        	String emp_unique_id = null;
	        	String stage3CreateDate = null;
	 		    String stage3AcknowledgementDate = null;
	 		    String stage3SystemDate = null;
	 		    String stage3H1BTransferStatus = null;
	        	String stage3Data = null;
	        	String stage1_status = null;
	        	String stage2_status = null;
	        	String stage3_status = null;
	        	String stage4_status = null;
	        	String stage5_status = null;
	        	String stage6_status = null;
	        	String stage7_status = null;
	        	String stage8_status = null;
	            String[] stageThreeDataArray = null;
	            String[] stageThreeStatusArray = null;
	            if(USCISStageThreeData!=null && USCISStageThreeData.size()!=0){
	            	
					Iterator itr = USCISStageThreeData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_USCIS_id = TempList[0];
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
					    stageThreeStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
					    
					    if(stage3Data != null && stage3Data != "" && !(stage3Data.equalsIgnoreCase(""))){
		        			String []tempstage3DataArray = stage3Data.split("-sep-");
		        			stageThreeDataArray = new String[tempstage3DataArray.length - 4];
		        			stage3CreateDate = tempstage3DataArray[0];
		        			stage3AcknowledgementDate = tempstage3DataArray[1];
		        			stage3SystemDate = tempstage3DataArray[2];
		        			stage3H1BTransferStatus = tempstage3DataArray[3];
		        			for(int j=0;j<(tempstage3DataArray.length - 4);j++){
		        				stageThreeDataArray[j] = tempstage3DataArray[4 + j];
		        				System.out.println("USCIS Action-Stage3----->stageThreeDataArray["+j+"]-------------->"+stageThreeDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageThreeStatusArray", stageThreeStatusArray);
		        				return new ModelAndView("USCIS/frmUSCISStage3");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_USCIS_id", unique_USCIS_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage3CreateDate", stage3CreateDate);
	            request.setAttribute("stage3AcknowledgementDate", stage3AcknowledgementDate);
	            request.setAttribute("stage3SystemDate", stage3SystemDate);
	            request.setAttribute("stage3H1BTransferStatus", stage3H1BTransferStatus);
	            if(empUniqueId != null){
	    	        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(emp_unique_id);
	    	        request.setAttribute("employeeDetails",null);
	    	        request.setAttribute("employeeDetails",employeeDetails);
	            }
	            request.setAttribute("stageThreeDataArray", stageThreeDataArray);
	            request.setAttribute("stageThreeStatusArray", stageThreeStatusArray);
	            status = "success";
	            request.setAttribute("status",status);
	            request.setAttribute("pageStatus","update");
	    		return new ModelAndView("USCIS/frmUSCISStage3");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("USCIS/frmUSCISStage3");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
		
	}
	
	@RequestMapping("/doUSCISStage3.html")
	public ModelAndView doUSCISStage3(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		String uniqueUSCISId = null;
		String empUniqueId = null;
		String empId = null;
		String createDate = null;
		String acknowledgementDate = null;
		String systemDate = null;
		String H1_BTransfer = null;
		
		String status = null;	
		
		empUniqueId  = request.getParameter("empUniqueId");
		empId  = request.getParameter("empId");
		createDate  = request.getParameter("CreateDate");
		acknowledgementDate  = request.getParameter("AcknowledgementDate");
		systemDate  = request.getParameter("SystemDate");
		H1_BTransfer  = request.getParameter("H1_BTransfer");
		
		ArrayList employeeDetails = new ArrayList();
		
		boolean insertStatus = false;
        if(empUniqueId != null && createDate != null && 
        		acknowledgementDate != null && systemDate != null && H1_BTransfer != null){
        	
        	String dosBPELStatus = null;
        	USCISClientStage3 clientStage3 = new USCISClientStage3();
        	dosBPELStatus = clientStage3.stage3USCIS(H1_BTransfer, createDate, acknowledgementDate, systemDate);
	            
        	if(dosBPELStatus != null && dosBPELStatus.equalsIgnoreCase("Success3")){	
        		insertStatus = db.insertUSCISStageThreeDetails(empUniqueId,createDate,acknowledgementDate,
                		systemDate,H1_BTransfer);
	        }else{
	            	throw new Exception("Stage Three Was Failed in BPEL Process");
	        }
        	
        	System.out.println("insertStatus in USCISAction-Stage3::::::::"+insertStatus);
            if(insertStatus){
	            ArrayList USCISStageThreeData = new ArrayList();
	            USCISStageThreeData = db.getUSCISStageThreeDetailsByUniqueEmpId(empUniqueId);
	            
	            String unique_USCIS_id = null;
            	String emp_unique_id = null;
     		    String stage3CreateDate = null;
    		    String stage3AcknowledgementDate = null;
     		    String stage3SystemDate = null;
     		    String stage3H1_BTransfer = null;
            	String stage3Data = null;
            	String stage1_status = null;
            	String stage2_status = null;
            	String stage3_status = null;
            	String stage4_status = null;
            	String stage5_status = null;
            	String stage6_status = null;
            	String stage7_status = null;
            	String stage8_status = null;
	            String[] stageThreeStatusArray = null;
	            if(USCISStageThreeData!=null && USCISStageThreeData.size()!=0){
					Iterator itr = USCISStageThreeData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_USCIS_id = TempList[0];
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
					    stageThreeStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
					    
					    if(stage3Data != null && stage3Data != "" && !(stage3Data.equalsIgnoreCase(""))){
		        			String []tempstage3DataArray = stage3Data.split("-sep-");
		        			stage3CreateDate = tempstage3DataArray[0];
		        			stage3AcknowledgementDate = tempstage3DataArray[1];
		        			stage3SystemDate = tempstage3DataArray[2];
		        			stage3H1_BTransfer = tempstage3DataArray[3];
					    }else{
					    	employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
					        request.setAttribute("employeeDetails",null);
					        request.setAttribute("employeeDetails",employeeDetails);
					        request.setAttribute("pageStatus","init");
					        request.setAttribute("stageThreeStatusArray", stageThreeStatusArray);
							return new ModelAndView("USCIS/frmUSCISStage3");
					    }
				    }
            }
	            request.setAttribute("unique_USCIS_id", unique_USCIS_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage3CreateDate", stage3CreateDate);
	            request.setAttribute("stage3AcknowledgementDate", stage3AcknowledgementDate);
	            request.setAttribute("stage3SystemDate", stage3SystemDate);
	            request.setAttribute("stage3H1BTransferStatus", stage3H1_BTransfer);
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
	    		return new ModelAndView("USCIS/frmUSCISStage3");
	        }else{
	        	throw new Exception("Information not stored in Server. Please Try again!");
	        }
     }else{
    	 throw new Exception("Input parameters or Uploaded file are not reached to Server");
    	 }
		
	}
	@RequestMapping("/initUSCISStage4.html")
	public ModelAndView initUSCISStage4(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList USCISStageFourData = new ArrayList();
            USCISStageFourData = db.getUSCISStageFourDetailsByUniqueEmpId(empUniqueId);
            
            if(USCISStageFourData != null && USCISStageFourData.size() > 0){
            
	            String unique_USCIS_id = null;
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
	            String[] stageFourDataArray = null;
	            String[] stageFourStatusArray = null;
	            if(USCISStageFourData!=null && USCISStageFourData.size()!=0){
	            	
					Iterator itr = USCISStageFourData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_USCIS_id = TempList[0];
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
					    stageFourStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
					    
					    if(stage4Data != null && stage4Data != "" && !(stage4Data.equalsIgnoreCase(""))){
		        			String []tempStage4DataArray = stage4Data.split("-sep-");
		        			stageFourDataArray = new String[tempStage4DataArray.length - 4];
		        			stage4CreateDate = tempStage4DataArray[0];
		        			stage4AcknowledgementDate = tempStage4DataArray[1];
		        			stage4SystemDate = tempStage4DataArray[2];
		        			stage4ApproveStatus = tempStage4DataArray[3];
		        			for(int j=0;j<(tempStage4DataArray.length - 4);j++){
		        				stageFourDataArray[j] = tempStage4DataArray[4 + j];
		        				System.out.println("USCIS Action-Stage4----->stageFourDataArray["+j+"]-------------->"+stageFourDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageFourStatusArray", stageFourStatusArray);
		        				return new ModelAndView("USCIS/frmUSCISStage4");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_USCIS_id", unique_USCIS_id);
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
	    		return new ModelAndView("USCIS/frmUSCISStage4");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("USCIS/frmUSCISStage4");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
		
	}
	
	@RequestMapping("/doUSCISStage4.html")
	public ModelAndView doUSCISStage4(HttpServletRequest request,
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
    		   
    		   String oldPostingI_129 = null;
    		   
    		   String PostingI_129Check = null;
    		   
    		   String PostingI_129File = null;
    		   
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
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.USCIS");
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
		                	if(parmName.equals("PostingI_129Check")){
		                		PostingI_129Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldPostingI_129")){
		                		oldPostingI_129 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in USCIS-Stage4 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("PostingI_129")) {
					        	  System.out.println("fPart.getFileName() in USCIS-Stage4 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  //dosFileList.add(fileLocation);
					        	  PostingI_129File = fileLocation;
					          }
					        
					          System.out.println("File name side Stage1 action --------->"+name);
					        if((PostingI_129Check == null || PostingI_129Check == "" || PostingI_129Check.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
				                    		(fPart.getName().equalsIgnoreCase("FormI_129"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldFormI_129-------------in-----if PostingI_129Check--Stage4 action--->"+oldPostingI_129);
						        	  //System.out.println("oldAdditionalfile-------------in-----else--Stage1 action--->"+oldAdditionalfile);
						        	  //dosFileList.add(oldPostingI_129);
						        	  PostingI_129File = oldPostingI_129;
				             }
						    		                  
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && PostingI_129File != null){
	            	
	            	String dosBPELStatus = null;
	            	USCISClientStage4 clientStage4 = new USCISClientStage4();
	            	dosBPELStatus = clientStage4.stage4USCIS(PostingI_129File, createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	if(dosBPELStatus != null && dosBPELStatus.equalsIgnoreCase("Success4")){	
			            	insertStatus = db.insertUSCISStageFourDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,PostingI_129File);
			        }else{
			            	throw new Exception("Stage One Was Failed in BPEL Process");
			        }
		            
	            System.out.println("insertStatus in USCISAction-Stage4::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList USCISStageFourData = new ArrayList();
			            USCISStageFourData = db.getUSCISStageFourDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_USCIS_id = null;
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
			            String[] stageFourDataArray = null;
			            String[] stageFourStatusArray = null;
			            if(USCISStageFourData!=null && USCISStageFourData.size()!=0){
			            	
							Iterator itr = USCISStageFourData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_USCIS_id = TempList[0];
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
							    stageFourStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
							    
							    if(stage4Data != null && stage4Data != "" && !(stage4Data.equalsIgnoreCase(""))){
				        			String []tempstage4DataArray = stage4Data.split("-sep-");
				        			stageFourDataArray = new String[tempstage4DataArray.length - 4];
				        			stage4CreateDate = tempstage4DataArray[0];
				        			stage4AcknowledgementDate = tempstage4DataArray[1];
				        			stage4SystemDate = tempstage4DataArray[2];
				        			stage4ApproveStatus = tempstage4DataArray[3];
				        			for(int j=0;j<(tempstage4DataArray.length - 4);j++){
				        				stageFourDataArray[j] = tempstage4DataArray[4 + j];
				        				System.out.println("USCISAction-stage4----->stageFourDataArray["+j+"]-------------->"+stageFourDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_USCIS_id", unique_USCIS_id);
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
			    		return new ModelAndView("USCIS/frmUSCISStage4");
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
    	   return new ModelAndView("USCIS/frmUSCISStage4");
	       
	}
	
	@RequestMapping("/initUSCISStage5.html")
	public ModelAndView initUSCISStage5(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList USCISStageFiveData = new ArrayList();
            USCISStageFiveData = db.getUSCISStageFiveDetailsByUniqueEmpId(empUniqueId);
            
            if(USCISStageFiveData != null && USCISStageFiveData.size() > 0){
            
	            String unique_USCIS_id = null;
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
	        	String stage7_status = null;
	        	String stage8_status = null;
	            String[] stageFiveDataArray = null;
	            String[] stageFiveStatusArray = null;
	            if(USCISStageFiveData!=null && USCISStageFiveData.size()!=0){
	            	
					Iterator itr = USCISStageFiveData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_USCIS_id = TempList[0];
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
					    stageFiveStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
					    
					    if(stage5Data != null && stage5Data != "" && !(stage5Data.equalsIgnoreCase(""))){
		        			String []tempStage5DataArray = stage5Data.split("-sep-");
		        			stageFiveDataArray = new String[tempStage5DataArray.length - 4];
		        			stage5CreateDate = tempStage5DataArray[0];
		        			stage5AcknowledgementDate = tempStage5DataArray[1];
		        			stage5SystemDate = tempStage5DataArray[2];
		        			stage5ApproveStatus = tempStage5DataArray[3];
		        			for(int j=0;j<(tempStage5DataArray.length - 4);j++){
		        				stageFiveDataArray[j] = tempStage5DataArray[4 + j];
		        				System.out.println("USCIS Action-Stage5----->stageFiveDataArray["+j+"]-------------->"+stageFiveDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageFiveStatusArray", stageFiveStatusArray);
		        				return new ModelAndView("USCIS/frmUSCISStage5");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_USCIS_id", unique_USCIS_id);
	            request.setAttribute("emp_unique_id", emp_unique_id);
	            request.setAttribute("stage5CreateDate", stage5CreateDate);
	            request.setAttribute("stage5AcknowledgementDate", stage5AcknowledgementDate);
	            request.setAttribute("stage5SystemDate", stage5SystemDate);
	            request.setAttribute("stage5ApproveStatus", stage5ApproveStatus);
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
	    		return new ModelAndView("USCIS/frmUSCISStage5");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("USCIS/frmUSCISStage5");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
		
	}
	
	@RequestMapping("/doUSCISStage5.html")
	public ModelAndView doUSCISStage5(HttpServletRequest request,
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
    		   
    		   String oldonBoarding = null;
    		   
    		   String onBoardingCheck = null;
    		   
    		   String onBoardingFile = null;
    		   
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
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.USCIS");
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
		                	if(parmName.equals("onBoardingCheck")){
		                		onBoardingCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldonBoarding")){
		                		oldonBoarding = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in USCIS-Stage5 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("onBoarding")) {
					        	  System.out.println("fPart.getFileName() in USCIS-Stage5 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  //dosFileList.add(fileLocation);
					        	  onBoardingFile = fileLocation;
					          }
					        
					          System.out.println("File name side Stage1 action --------->"+name);
					        if((onBoardingCheck == null || onBoardingCheck == "" || onBoardingCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
				                    		(fPart.getName().equalsIgnoreCase("onBoarding"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldonBoarding-------------in-----if onBoardingCheck--Stage5 action--->"+oldonBoarding);
						        	  //System.out.println("oldAdditionalfile-------------in-----else--Stage1 action--->"+oldAdditionalfile);
						        	  //dosFileList.add(oldonBoarding);
						        	  onBoardingFile = oldonBoarding;
				             }
						     
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && onBoardingFile != null){
	            	
	            	String dosBPELStatus = null;
	            	USCISClientStage5 clientStage5 = new USCISClientStage5();
	            	dosBPELStatus = clientStage5.stage5USCIS(onBoardingFile, createDate, acknowledgementDate, systemDate, approveStatus,"","");
			            
	            	if(dosBPELStatus != null && dosBPELStatus.equalsIgnoreCase("Success5")){	
			            	insertStatus = db.insertUSCISStageFiveDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,onBoardingFile);
			        }else{
			            	throw new Exception("Stage One Was Failed in BPEL Process");
			        }
		            
	            System.out.println("insertStatus in USCISAction-Stage5::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList USCISStageFiveData = new ArrayList();
			            USCISStageFiveData = db.getUSCISStageFiveDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_USCIS_id = null;
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
		            	String stage7_status = null;
		            	String stage8_status = null;
			            String[] stageFiveDataArray = null;
			            String[] stageFiveStatusArray = null;
			            if(USCISStageFiveData!=null && USCISStageFiveData.size()!=0){
			            	
							Iterator itr = USCISStageFiveData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_USCIS_id = TempList[0];
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
							    stageFiveStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
							    
							    if(stage5Data != null && stage5Data != "" && !(stage5Data.equalsIgnoreCase(""))){
				        			String []tempstage5DataArray = stage5Data.split("-sep-");
				        			stageFiveDataArray = new String[tempstage5DataArray.length - 4];
				        			stage5CreateDate = tempstage5DataArray[0];
				        			stage5AcknowledgementDate = tempstage5DataArray[1];
				        			stage5SystemDate = tempstage5DataArray[2];
				        			stage5ApproveStatus = tempstage5DataArray[3];
				        			for(int j=0;j<(tempstage5DataArray.length - 4);j++){
				        				stageFiveDataArray[j] = tempstage5DataArray[4 + j];
				        				System.out.println("USCISAction-stage5----->stageFiveDataArray["+j+"]-------------->"+stageFiveDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_USCIS_id", unique_USCIS_id);
			            request.setAttribute("emp_unique_id", emp_unique_id);
			            request.setAttribute("stage5CreateDate", stage5CreateDate);
			            request.setAttribute("stage5AcknowledgementDate", stage5AcknowledgementDate);
			            request.setAttribute("stage5SystemDate", stage5SystemDate);
			            request.setAttribute("stage5ApproveStatus", stage5ApproveStatus);
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
			    		return new ModelAndView("USCIS/frmUSCISStage5");
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
    	   return new ModelAndView("USCIS/frmUSCISStage5");
	       
	}
	
	@RequestMapping("/initUSCISStage6.html")
	public ModelAndView initUSCISStage6(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList USCISStageSixData = new ArrayList();
            USCISStageSixData = db.getUSCISStageSixDetailsByUniqueEmpId(empUniqueId);
            
            if(USCISStageSixData != null && USCISStageSixData.size() > 0){
            
	            String unique_USCIS_id = null;
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
	            String[] stageSixDataArray = null;
	            String[] stageSixStatusArray = null;
	            if(USCISStageSixData!=null && USCISStageSixData.size()!=0){
	            	
					Iterator itr = USCISStageSixData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_USCIS_id = TempList[0];
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
					    stageSixStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
					    
					    if(stage6Data != null && stage6Data != "" && !(stage6Data.equalsIgnoreCase(""))){
		        			String []tempStage6DataArray = stage6Data.split("-sep-");
		        			stageSixDataArray = new String[tempStage6DataArray.length - 4];
		        			stage6CreateDate = tempStage6DataArray[0];
		        			stage6AcknowledgementDate = tempStage6DataArray[1];
		        			stage6SystemDate = tempStage6DataArray[2];
		        			stage6ApproveStatus = tempStage6DataArray[3];
		        			for(int j=0;j<(tempStage6DataArray.length - 4);j++){
		        				stageSixDataArray[j] = tempStage6DataArray[4 + j];
		        				System.out.println("USCIS Action-Stage6----->stageSixDataArray["+j+"]-------------->"+stageSixDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageSixStatusArray", stageSixStatusArray);
		        				return new ModelAndView("USCIS/frmUSCISStage6");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_USCIS_id", unique_USCIS_id);
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
	    		return new ModelAndView("USCIS/frmUSCISStage6");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("USCIS/frmUSCISStage6");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
		
	}
	
	@RequestMapping("/doUSCISStage6.html")
	public ModelAndView doUSCISStage6(HttpServletRequest request,
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
    		   
    		   String oldForm1I_94 = null;
    		   
    		   String Form1I_94Check = null;
    		   
    		   String Form1I_94File = null;
    		   
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
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.USCIS");
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
		                	if(parmName.equals("Form1I_94Check")){
		                		Form1I_94Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldForm1I_94")){
		                		oldForm1I_94 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in USCIS-Stage6 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("Form1I_94")) {
					        	  System.out.println("fPart.getFileName() in USCIS-Stage6 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  //dosFileList.add(fileLocation);
					        	  Form1I_94File = fileLocation;
					          }
					        
					          System.out.println("File name side Stage1 action --------->"+name);
					        if((Form1I_94Check == null || Form1I_94Check == "" || Form1I_94Check.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
				                    		(fPart.getName().equalsIgnoreCase("Form1I_94"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldForm1I_94-------------in-----if Form1I_94Check--Stage6 action--->"+oldForm1I_94);
						        	  //System.out.println("oldAdditionalfile-------------in-----else--Stage1 action--->"+oldAdditionalfile);
						        	  //dosFileList.add(oldForm1I_94);
						        	  Form1I_94File = oldForm1I_94;
				             }
						   
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && Form1I_94File != null){
	            	
	            	String dosBPELStatus = null;
	            	USCISClientStage6 clientStage6 = new USCISClientStage6();
	            	dosBPELStatus = clientStage6.stage6USCIS(Form1I_94File, "","","",createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	if(dosBPELStatus != null && dosBPELStatus.equalsIgnoreCase("Success6")){	
			            	insertStatus = db.insertUSCISStageSixDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,Form1I_94File);
			        }else{
			            	throw new Exception("Stage One Was Failed in BPEL Process");
			        }
		            
	            System.out.println("insertStatus in USCISAction-Stage6::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList USCISStageSixData = new ArrayList();
			            USCISStageSixData = db.getUSCISStageSixDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_USCIS_id = null;
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
			            String[] stageSixDataArray = null;
			            String[] stageSixStatusArray = null;
			            if(USCISStageSixData!=null && USCISStageSixData.size()!=0){
			            	
							Iterator itr = USCISStageSixData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_USCIS_id = TempList[0];
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
							    stageSixStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
							    
							    if(stage6Data != null && stage6Data != "" && !(stage6Data.equalsIgnoreCase(""))){
				        			String []tempstage6DataArray = stage6Data.split("-sep-");
				        			stageSixDataArray = new String[tempstage6DataArray.length - 4];
				        			stage6CreateDate = tempstage6DataArray[0];
				        			stage6AcknowledgementDate = tempstage6DataArray[1];
				        			stage6SystemDate = tempstage6DataArray[2];
				        			stage6ApproveStatus = tempstage6DataArray[3];
				        			for(int j=0;j<(tempstage6DataArray.length - 4);j++){
				        				stageSixDataArray[j] = tempstage6DataArray[4 + j];
				        				System.out.println("USCISAction-stage6----->stageSixDataArray["+j+"]-------------->"+stageSixDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_USCIS_id", unique_USCIS_id);
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
			    		return new ModelAndView("USCIS/frmUSCISStage6");
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
    	   return new ModelAndView("USCIS/frmUSCISStage6");
	       
	}
	
	@RequestMapping("/initUSCISStage7.html")
	public ModelAndView initUSCISStage7(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList USCISStageSevenData = new ArrayList();
            USCISStageSevenData = db.getUSCISStageSevenDetailsByUniqueEmpId(empUniqueId);
            
            if(USCISStageSevenData != null && USCISStageSevenData.size() > 0){
            
	            String unique_USCIS_id = null;
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
	            String[] stageSevenDataArray = null;
	            String[] stageSevenStatusArray = null;
	            if(USCISStageSevenData!=null && USCISStageSevenData.size()!=0){
	            	
					Iterator itr = USCISStageSevenData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_USCIS_id = TempList[0];
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
					    stageSevenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
					    
					    if(stage7Data != null && stage7Data != "" && !(stage7Data.equalsIgnoreCase(""))){
		        			String []tempStage7DataArray = stage7Data.split("-sep-");
		        			stageSevenDataArray = new String[tempStage7DataArray.length - 4];
		        			stage7CreateDate = tempStage7DataArray[0];
		        			stage7AcknowledgementDate = tempStage7DataArray[1];
		        			stage7SystemDate = tempStage7DataArray[2];
		        			stage7ApproveStatus = tempStage7DataArray[3];
		        			for(int j=0;j<(tempStage7DataArray.length - 4);j++){
		        				stageSevenDataArray[j] = tempStage7DataArray[4 + j];
		        				System.out.println("USCIS Action-Stage7----->stageSevenDataArray["+j+"]-------------->"+stageSevenDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageSevenStatusArray", stageSevenStatusArray);
		        				return new ModelAndView("USCIS/frmUSCISStage7");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_USCIS_id", unique_USCIS_id);
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
	    		return new ModelAndView("USCIS/frmUSCISStage7");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("USCIS/frmUSCISStage7");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
		
	}
	
	@RequestMapping("/doUSCISStage7.html")
	public ModelAndView doUSCISStage7(HttpServletRequest request,
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
    		   
    		   String oldFormI_9 = null;
    		   
    		   String FormI_9Check = null;
    		   
    		   String FormI_9File = null;
    		   
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
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.USCIS");
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
		                	if(parmName.equals("FormI_9Check")){
		                		FormI_9Check = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldFormI_9")){
		                		oldFormI_9 = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in USCIS-Stage7 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("FormI_9")) {
					        	  System.out.println("fPart.getFileName() in USCIS-Stage7 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  //dosFileList.add(fileLocation);
					        	  FormI_9File = fileLocation;
					          }
					        
					          System.out.println("File name side Stage1 action --------->"+name);
					        if((FormI_9Check == null || FormI_9Check == "" || FormI_9Check.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
				                    		(fPart.getName().equalsIgnoreCase("FormI_9"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldFormI_9-------------in-----if FormI_9Check--Stage7 action--->"+oldFormI_9);
						        	  //System.out.println("oldAdditionalfile-------------in-----else--Stage1 action--->"+oldAdditionalfile);
						        	  //dosFileList.add(oldFormI_9);
						        	  FormI_9File = oldFormI_9;
				             }
						   
					        
					          
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && FormI_9File != null){
	            	
	            	String dosBPELStatus = null;
	            	USCISClientStage7 clientStage7 = new USCISClientStage7();
	            	dosBPELStatus = clientStage7.stage7USCIS(FormI_9File, "","", createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	if(dosBPELStatus != null && dosBPELStatus.equalsIgnoreCase("Success7")){	
			            	insertStatus = db.insertUSCISStageSevenDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,FormI_9File);
			        }else{
			            	throw new Exception("Stage One Was Failed in BPEL Process");
			        }
		            
	            System.out.println("insertStatus in USCISAction-Stage7::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList USCISStageSevenData = new ArrayList();
			            USCISStageSevenData = db.getUSCISStageSevenDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_USCIS_id = null;
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
			            String[] stageSevenDataArray = null;
			            String[] stageSevenStatusArray = null;
			            if(USCISStageSevenData!=null && USCISStageSevenData.size()!=0){
			            	
							Iterator itr = USCISStageSevenData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_USCIS_id = TempList[0];
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
							    stageSevenStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
							    
							    if(stage7Data != null && stage7Data != "" && !(stage7Data.equalsIgnoreCase(""))){
				        			String []tempstage7DataArray = stage7Data.split("-sep-");
				        			stageSevenDataArray = new String[tempstage7DataArray.length - 4];
				        			stage7CreateDate = tempstage7DataArray[0];
				        			stage7AcknowledgementDate = tempstage7DataArray[1];
				        			stage7SystemDate = tempstage7DataArray[2];
				        			stage7ApproveStatus = tempstage7DataArray[3];
				        			for(int j=0;j<(tempstage7DataArray.length - 4);j++){
				        				stageSevenDataArray[j] = tempstage7DataArray[4 + j];
				        				System.out.println("USCISAction-stage7----->stageSevenDataArray["+j+"]-------------->"+stageSevenDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_USCIS_id", unique_USCIS_id);
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
			    		return new ModelAndView("USCIS/frmUSCISStage7");
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
    	   return new ModelAndView("USCIS/frmUSCISStage7");
	       
	}
	
	@RequestMapping("/initUSCISStage8.html")
	public ModelAndView initUSCISStage8(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		ArrayList employeeDetails = new ArrayList();
		
		String empUniqueId = null;
		String status = null;
		empUniqueId  = request.getParameter("empUniqueId");
		
		if(empUniqueId != null){
			
			ArrayList USCISStageEightData = new ArrayList();
            USCISStageEightData = db.getUSCISStageEightDetailsByUniqueEmpId(empUniqueId);
            
            if(USCISStageEightData != null && USCISStageEightData.size() > 0){
            
	            String unique_USCIS_id = null;
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
	            String[] stageEightDataArray = null;
	            String[] stageEightStatusArray = null;
	            if(USCISStageEightData!=null && USCISStageEightData.size()!=0){
	            	
					Iterator itr = USCISStageEightData.iterator();
				    while (itr.hasNext()) {    
					    String TempList[] = (String[])itr.next();
					    unique_USCIS_id = TempList[0];
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
					    stageEightStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
					    
					    if(stage8Data != null && stage8Data != "" && !(stage8Data.equalsIgnoreCase(""))){
		        			String []tempStage8DataArray = stage8Data.split("-sep-");
		        			stageEightDataArray = new String[tempStage8DataArray.length - 4];
		        			stage8CreateDate = tempStage8DataArray[0];
		        			stage8AcknowledgementDate = tempStage8DataArray[1];
		        			stage8SystemDate = tempStage8DataArray[2];
		        			stage8ApproveStatus = tempStage8DataArray[3];
		        			for(int j=0;j<(tempStage8DataArray.length - 4);j++){
		        				stageEightDataArray[j] = tempStage8DataArray[4 + j];
		        				System.out.println("USCISAction-Stage8----->stageEightDataArray["+j+"]-------------->"+stageEightDataArray[j]);
		        				}
		        			}else{
		        				employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        		        request.setAttribute("employeeDetails",null);
		        		        request.setAttribute("employeeDetails",employeeDetails);
		        		        request.setAttribute("pageStatus","init");
		        		        request.setAttribute("stageEightStatusArray", stageEightStatusArray);
		        				return new ModelAndView("USCIS/frmUSCISStage8");
		        			}
				    }
		        }
	            
	            request.setAttribute("unique_USCIS_id", unique_USCIS_id);
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
	    		return new ModelAndView("USCIS/frmUSCISStage8");
            }else{
		        employeeDetails = (ArrayList)db.getAllEmployeeDetailsByUniqueId(empUniqueId);
		        request.setAttribute("employeeDetails",null);
		        request.setAttribute("employeeDetails",employeeDetails);
		        request.setAttribute("pageStatus","init");
				return new ModelAndView("USCIS/frmUSCISStage8");
			}
		}else{
			throw new Exception("Employee Not Found");
		}
		
	}

	@RequestMapping("/doUSCISStage8.html")
	public ModelAndView doUSCISStage8(HttpServletRequest request,
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
    		   
    		   String oldContractDoc = null;
    		   String oldTimesheetDoc = null;
    		   String oldClientDoc = null;
    		   String oldWeeklyReviewDoc = null;
    		   
    		   String ContractDocCheck = null;
    		   String TimesheetDocCheck = null;
    		   String ClientDocCheck = null;
    		   String WeeklyReviewDocCheck = null;
    		   
    		   String ContractDocFile = null;
    		   String TimesheetDocFile = null;
    		   String ClientDocFile = null;
    		   String WeeklyReviewDocFile = null;
    		   
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
        	   UPLOAD_DIRECTORY = config_property.getProperty("config.USCIS");
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
		                	if(parmName.equals("ContractDocCheck")){
		                		ContractDocCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldContractDoc")){
		                		oldContractDoc = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		         		   
		                	if(parmName.equals("TimesheetDocCheck")){
		                		TimesheetDocCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldTimesheetDoc")){
		                		oldTimesheetDoc = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("ClientDocCheck")){
		                		ClientDocCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldClientDoc")){
		                		oldClientDoc = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("WeeklyReviewDocCheck")){
		                		WeeklyReviewDocCheck = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	if(parmName.equals("oldWeeklyReviewDoc")){
		                		oldWeeklyReviewDoc = paramPart.getStringValue();
		                		System.out.println("Inside param :::: "+paramPart.getStringValue());
		                		
		                	}
		                	
		                }// end If
		                
		                if(_part.isFile()){
		                	System.out.println("Inside _part.getName() in USCIS-Stage8 action--------->"+_part.getName());
		                	 FilePart fPart = (FilePart) _part;  // get some info about the file
			                    String name = fPart.getFileName();
					          if (name != null && fPart.getName().equalsIgnoreCase("ContractDoc")) {
					        	  System.out.println("fPart.getFileName() in USCIS-Stage8 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  //dosFileList.add(fileLocation);
					        	  ContractDocFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("TimesheetDoc")) {
					        	  System.out.println("fPart.getFileName() in USCIS-Stage8 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  //dosFileList.add(fileLocation);
					        	  TimesheetDocFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("ClientDoc")) {
					        	  System.out.println("fPart.getFileName() in USCIS-Stage8 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  //dosFileList.add(fileLocation);
					        	  ClientDocFile = fileLocation;
					          }else if (name != null && fPart.getName().equalsIgnoreCase("WeeklyReviewDoc")) {
					        	  System.out.println("fPart.getFileName() in USCIS-Stage8 action--------->"+name);
					        	  long fileSize = fPart.writeTo(new File(UPLOAD_DIRECTORY));
					        	  resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
					        	  fileLocation = UPLOAD_DIRECTORY+name;
					        	  //dosFileList.add(fileLocation);
					        	  WeeklyReviewDocFile = fileLocation;
					          }
					        
					          System.out.println("File name side Stage8 action --------->"+name);
					        if((ContractDocCheck == null || ContractDocCheck == "" || ContractDocCheck.equalsIgnoreCase("")) &&
						        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
				                    		(fPart.getName().equalsIgnoreCase("ContractDoc"))){
				                      //resp = "<br>The user did not upload a file for this part.";
						        	  System.out.println("oldContractDoc-------------in-----if ContractDocCheck--Stage8 action--->"+oldContractDoc);
						        	  //System.out.println("oldAdditionalfile-------------in-----else--Stage8 action--->"+oldAdditionalfile);
						        	  //dosFileList.add(oldContractDoc);
						        	  ContractDocFile = oldContractDoc;
				             }
					        if((TimesheetDocCheck == null || TimesheetDocCheck == "" || TimesheetDocCheck.equalsIgnoreCase("")) &&
					        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("TimesheetDoc"))){
			                      //resp = "<br>The user did not upload a file for this part.";
					        	  System.out.println("oldTimesheetDoc-------------in-----if TimesheetDocCheck--Stage8 action--->"+oldTimesheetDoc);
					        	  //System.out.println("oldAdditionalfile-------------in-----else--Stage8 action--->"+oldAdditionalfile);
					        	  //dosFileList.add(oldTimesheetDoc);
					        	  TimesheetDocFile = oldTimesheetDoc;
			             }
					        if((ClientDocCheck == null || ClientDocCheck == "" || ClientDocCheck.equalsIgnoreCase("")) &&
					        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("ClientDoc"))){
			                      //resp = "<br>The user did not upload a file for this part.";
					        	  System.out.println("oldClientDoc-------------in-----if ClientDocCheck--Stage8 action--->"+oldClientDoc);
					        	  //System.out.println("oldAdditionalfile-------------in-----else--Stage8 action--->"+oldAdditionalfile);
					        	  //dosFileList.add(oldClientDoc);
					        	  ClientDocFile = oldClientDoc;
			             }
					        if((WeeklyReviewDocCheck == null || WeeklyReviewDocCheck == "" || WeeklyReviewDocCheck.equalsIgnoreCase("")) &&
					        		  ((name == null || name == "" || name.equalsIgnoreCase("")) || name.equalsIgnoreCase("null")) &&
			                    		(fPart.getName().equalsIgnoreCase("WeeklyReviewDoc"))){
			                      //resp = "<br>The user did not upload a file for this part.";
					        	  System.out.println("oldWeeklyReviewDoc-------------in-----if WeeklyReviewDocCheck--Stage8 action--->"+oldWeeklyReviewDoc);
					        	  //System.out.println("oldAdditionalfile-------------in-----else--Stage8 action--->"+oldAdditionalfile);
					        	  //dosFileList.add(oldWeeklyReviewDoc);
					        	  WeeklyReviewDocFile = oldWeeklyReviewDoc;
			             }
					           
		                }// end If
		                
	            }// end while 
	            
	            boolean insertStatus = false;
	            if(empUniqueId != null && createDate != null && acknowledgementDate != null && systemDate != null
	            		&& approveStatus != null && ContractDocFile != null && TimesheetDocFile != null && ClientDocFile != null && WeeklyReviewDocFile != null){
	            	
	            	String dosBPELStatus = null;
	            	USCISClientStage8 clientStage8 = new USCISClientStage8();
	            	dosBPELStatus = clientStage8.stage8USCIS(ContractDocFile, TimesheetDocFile, ClientDocFile, WeeklyReviewDocFile, "", "", createDate, acknowledgementDate, systemDate, approveStatus);
			            
	            	if(dosBPELStatus != null && dosBPELStatus.equalsIgnoreCase("Success8")){	
			            	insertStatus = db.insertUSCISStageEightDetails(empUniqueId,createDate,acknowledgementDate,systemDate,approveStatus,ContractDocFile,TimesheetDocFile,ClientDocFile,WeeklyReviewDocFile);
			        }else{
			            	throw new Exception("Stage Eight Was Failed in BPEL Process");
			        }
		            
	            System.out.println("insertStatus in USCISAction-Stage8::::::::"+insertStatus);
		            if(insertStatus){
		            	
			            ArrayList USCISStageEightData = new ArrayList();
			            USCISStageEightData = db.getUSCISStageEightDetailsByUniqueEmpId(empUniqueId);
			            
			            String unique_USCIS_id = null;
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
			            String[] stageEightDataArray = null;
			            String[] stageEightStatusArray = null;
			            if(USCISStageEightData!=null && USCISStageEightData.size()!=0){
			            	
							Iterator itr = USCISStageEightData.iterator();
						    while (itr.hasNext()) {    
							    String TempList[] = (String[])itr.next();
							    unique_USCIS_id = TempList[0];
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
							    stageEightStatusArray = new String[] {stage1_status,stage2_status,stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
							    
							    if(stage8Data != null && stage8Data != "" && !(stage8Data.equalsIgnoreCase(""))){
				        			String []tempStage8DataArray = stage8Data.split("-sep-");
				        			stageEightDataArray = new String[tempStage8DataArray.length - 4];
				        			stage8CreateDate = tempStage8DataArray[0];
				        			stage8AcknowledgementDate = tempStage8DataArray[1];
				        			stage8SystemDate = tempStage8DataArray[2];
				        			stage8ApproveStatus = tempStage8DataArray[3];
				        			for(int j=0;j<(tempStage8DataArray.length - 4);j++){
				        				stageEightDataArray[j] = tempStage8DataArray[4 + j];
				        				System.out.println("USCISAction-Stage8----->stageEightDataArray["+j+"]-------------->"+stageEightDataArray[j]);
				        				}
				        			}
						    }
				        }
			            
			            request.setAttribute("unique_USCIS_id", unique_USCIS_id);
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
			    		return new ModelAndView("USCIS/frmUSCISStage8");
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
    	   return new ModelAndView("USCIS/frmUSCISStage8");
	       
	}
	
}

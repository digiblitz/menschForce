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
package com.db.immigration.action.USCIS.service;

import javax.jws.WebService;





@WebService(endpointInterface="com.db.immigration.action.USCIS.service.USCISStagesService")
public class USCISService implements USCISStagesService {
	
	/* ==================================== Department of State  ==============================================  */
	 

	 /*Department Of State
	  * Date: 14-04-2017
	  * Author:Priya
	  * digiBlitz Inc.
	 */
	@Override
	public String Stage1(String userId,String contract, String POEFilepath,String clientLetterFilepath, String workOrderFilepath,
			String labourClearanceFilepath,String createdDate, String ackDate, 
			String systemDate, String status,String documentName, String MultipleFilePath) {
			   
			   String tempStatus = null;
			  
			   
			  if(userId!=null && contract != null && POEFilepath != null && clientLetterFilepath !=null
					  && workOrderFilepath !=null && labourClearanceFilepath !=null && createdDate !=null
					  && ackDate !=null && status !=null)
			  {
			  tempStatus = "Success1";
			   System.out.println("tempStatus ---> "+tempStatus);
			  }
			  else
			  {
			    tempStatus = "Failure1";
			   System.out.println("tempStatus ---> "+tempStatus);
			  }
			  
			  return tempStatus;
			 }
	
	
	@Override
	public String Stage2(String filePath, String createDate, String ackDate, 
			String systemDate, String status, String documentName, String multipleFilePath){
			   
			   String tempStatus = null;
			  
			   
			  if(filePath != null && status != null && createDate != null && ackDate!=null)
			  {
			  tempStatus = "Success2";
			   System.out.println("tempStatus ---> "+tempStatus);
			  }
			  else
			  {
			    tempStatus = "Failure2";
			   System.out.println("tempStatus ---> "+tempStatus);
			  }
			  
			  return tempStatus;
			 }

	@Override
	public String Stage3(String h1BTransferStatus, String createDate, String ackDate, 
			String systemDate)
	{
	 
	   String tempStatus="null";
	   
	  if(h1BTransferStatus != null && createDate !=null && ackDate!=null)
	  {
	  tempStatus = "Success3";
	   System.out.println("tempStatus ---> "+tempStatus);
	  }
	  else
	  {
	    tempStatus = "Failure3";
	   System.out.println("tempStatus ---> "+tempStatus);
	  }
	  
	  return tempStatus;
	 }
	
	@Override
	public String Stage4(String filePath, String createDate, String ackDate, 
			String systemDate, String status) {
			   
			   String tempStatus = null;
				  if(filePath != null && status != null  &&
						  createDate != null && ackDate!=null)
				  {
				  tempStatus = "Success4";
				   System.out.println("tempStatus ---> "+tempStatus);
				  }
			   
		
			  else
			  {
			    tempStatus = "Failure4";
			   System.out.println("tempStatus ---> "+tempStatus);
			  }
			  
			  return tempStatus;
			 }
	
	@Override
	public String Stage5(String filePath,  String createDate, String ackDate, 
			String systemDate, String status,String documentName,String MultipleFilePath) {
			   
			   String tempStatus = null;
			   
			  if(filePath != null && status != null 
					  && createDate!=null && ackDate!=null )
			  {
			  tempStatus = "Success5";
			   System.out.println("tempStatus ---> "+tempStatus);
			  }
			  else
			  {
			    tempStatus = "Failure5";
			   System.out.println("tempStatus ---> "+tempStatus);
			  }
			  
			  return tempStatus;
			 }
	@Override
	public String Stage6(String Form94filePath,String Form94IIfilePath,String fileDescription, String filePath, 
			String createDate, String ackDate, String systemDate, String status) {
			   
			   String tempStatus = null;
			 
			   
			  if(Form94filePath != null && Form94IIfilePath != null && status !=null
					  && createDate != null && ackDate!=null)
			  {
			  tempStatus = "Success6";
			   System.out.println("tempStatus ---> "+tempStatus);
			  }
			  else
			  {
			    tempStatus = "Failure6";
			   System.out.println("tempStatus ---> "+tempStatus);
			  }
			  
			  return tempStatus;
			 }
	
	@Override
	public String Stage7(String FormI9filePath, String fileDescription, String filePath,  String createDate, String ackDate, 
			String systemDate, String status)
	{
	 
	   String tempStatus = null;
	 
	   
	  if(FormI9filePath != null &&  createDate!=null
			  && ackDate != null && status!=null)
	  {
	  tempStatus = "Success7";
	   System.out.println("tempStatus ---> "+tempStatus);
	  }
	  else
	  {
	    tempStatus = "Failure7";
	   System.out.println("tempStatus ---> "+tempStatus);
	  }
	  
	  return tempStatus;
	 }
	
	@Override
	public String Stage8(String DOC, String DOT, String DOClient, String DOW, String fileDescription,String filePath, String createDate, String ackDate, 
			String systemDate, String status)
	{
		 
		   String tempStatus = null;
		 
		   
		  if(DOC != null  && DOT != null && DOClient!=null && DOW !=null && createDate!=null
				  && ackDate != null && status!=null)
		  {
		  tempStatus = "Success8";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  else
		  {
		    tempStatus = "Failure8";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  
		  return tempStatus;
		 }
}

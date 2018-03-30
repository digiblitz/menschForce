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
package com.db.immigration.action.DOL.service;

import javax.jws.WebService;



@WebService(endpointInterface="com.db.immigration.action.DOL.service.DOLStagesService")
public class DOLService implements DOLStagesService {
	
	/* ==================================== Department of Labour  ==============================================  */
	 

	 /*Department Of Labour
	  * Date: 20-04-2017
	  * Author:Priya
	  * digiBlitz Inc.
	 */
	@Override
	public String Stage1(String userId, String PAFFilepath,String createDate, String ackDate, 
			String systemDate, String status,String documentName, String MultipleFilePath) {
			   
			   String tempStatus = null;
			  
			   
			  if(userId!=null && PAFFilepath != null && createDate != null && ackDate !=null && status !=null)
			  {
			  tempStatus = "Success1DOL";
			   System.out.println("tempStatus ---> "+tempStatus);
			  }
			  else
			  {
			    tempStatus = "Failure1DOL";
			   System.out.println("tempStatus ---> "+tempStatus);
			  }
			  
			  return tempStatus;
			 }
	
	
	@Override
	public String Stage2(String filePath, String salaryLevel, String createDate, String ackDate, 
			String systemDate, String status){
			   
			   String tempStatus = null;
			  
			   
			  if(filePath != null && salaryLevel != null && createDate != null && ackDate!=null && status != null)
			  {
			  tempStatus = "Success2DOL";
			   System.out.println("tempStatus ---> "+tempStatus);
			  }
			  else
			  {
			    tempStatus = "Failure2DOL";
			   System.out.println("tempStatus ---> "+tempStatus);
			  }
			  
			  return tempStatus;
			 }

	@Override
	public String Stage3(String actualwagewithoutPrevailingwage, String createDate, String ackDate, 
			String systemDate, String status)
	{
	 
	   String tempStatus="null";
	   
	  if(actualwagewithoutPrevailingwage != null && createDate !=null && ackDate!=null && status != null)
	  {
	  tempStatus = "Success3DOL";
	   System.out.println("tempStatus ---> "+tempStatus);
	  }
	  else
	  {
	    tempStatus = "Failure3DOL";
	   System.out.println("tempStatus ---> "+tempStatus);
	  }
	  
	  return tempStatus;
	 }
	
	@Override
	public String Stage4(String DepartmentLabourFilePath, String NewspaperFilePath,String onlinePortalFilePath, 
			String createDate, String ackDate, String systemDate, String status) {
			   
			   String tempStatus = null;
			  
			   
			  if(DepartmentLabourFilePath != null && NewspaperFilePath != null && onlinePortalFilePath != null &&
					  createDate != null && ackDate!=null && status != null)
			  {
			  tempStatus = "Success4DOL";
			   System.out.println("tempStatus ---> "+tempStatus);
			  }
			  else
			  {
			    tempStatus = "Failure4DOL";
			   System.out.println("tempStatus ---> "+tempStatus);
			  }
			  
			  return tempStatus;
			 }
	
	@Override
	public String Stage5(String NumberofDays,String fromdate, String todate, String filePath, String createDate, String ackDate, 
			String systemDate, String status, String documentName, String MultipleFilePath) {
			   
			   String tempStatus = null;
			   
			  if(NumberofDays != null && fromdate != null && todate!=null && filePath!=null 
					  && createDate!=null && ackDate!=null && status != null )
			  {
			  tempStatus = "Success5DOL";
			   System.out.println("tempStatus ---> "+tempStatus);
			  }
			  else
			  {
			    tempStatus = "Failure5DOL";
			   System.out.println("tempStatus ---> "+tempStatus);
			  }
			  
			  return tempStatus;
			 }
	@Override
	public String Stage6(String EduQualificationFilepath, String ExperienceFilePath, String PeopleNotSelected,
			String createdDate, String ackDate, String systemDate, String status, String documentName, String MultipleFilePath) {
			   
			   String tempStatus = null;
			 
			   
			  if(EduQualificationFilepath != null && ExperienceFilePath != null && PeopleNotSelected!=null
					  && createdDate != null && ackDate!=null && status != null)
			  {
			  tempStatus = "Success6DOL";
			   System.out.println("tempStatus ---> "+tempStatus);
			  }
			  else
			  {
			    tempStatus = "Failure6DOL";
			   System.out.println("tempStatus ---> "+tempStatus);
			  }
			  
			  return tempStatus;
			 }
	
	@Override
	public String Stage7(String fileDescription, String filePath,  String createDate, String ackDate, 
			String systemDate, String status,String documentName,String MultipleFilePath)
	{
	 
	   String tempStatus = null;
	 
	   
	  if(fileDescription != null && filePath != null && createDate!=null
			  && ackDate != null && status!=null)
	  {
	  tempStatus = "Success7DOL";
	   System.out.println("tempStatus ---> "+tempStatus);
	  }
	  else
	  {
	    tempStatus = "Failure7DOL";
	   System.out.println("tempStatus ---> "+tempStatus);
	  }
	  
	  return tempStatus;
	 }
	
	@Override
	public String Stage8(String filePath, String createDate, String ackDate, 
			String systemDate, String status,String documentName,String MultipleFilePath)
	{
		 
		   String tempStatus = null;
		 
		   
		  if(filePath != null  && createDate!=null
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
	
	@Override
	public String Stage9( String prevailingWageTrue, String filePath,String prevailingWageFalse, String reason, String createDate, String ackDate, 
			String systemDate, String status)
	{
		 
		   String tempStatus = null;
		 
		   
		  if( filePath!=null &&  reason!=null && createDate!=null 
				  && ackDate != null && status!=null)
		  {
		  tempStatus = "Success9DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  else
		  {
		    tempStatus = "Failure9DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  
		  return tempStatus;
		 }
	
	@Override
	public String Stage10(String IncreasepayTrue, String filePath,String IncreasepayFalse, String reason, String createDate, String ackDate, 
			String systemDate, String status,String documentName,String MultipleFilePath)
	{
		  
		   String tempStatus = null;
		 
		   
		  if (filePath != null  && reason != null && createDate!=null
				  && ackDate != null && status!=null)
		  {
		  tempStatus = "Success10DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  else
		  {
		    tempStatus = "Failure10DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  
		  return tempStatus;
		 }
	
	@Override
	public String Stage11(String Filepath,String createDate, String ackDate, 
			String systemDate, String status,String documentName, String MultipleFilePath)
	{
		 
		   String tempStatus = null;
		 
		   
		  if(Filepath != null  && createDate!=null
				  && ackDate != null && status!=null)
		  {
		  tempStatus = "Success11DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  else
		  {
		    tempStatus = "Failure11DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  
		  return tempStatus;
		 }
	
	@Override
	public String Stage12(String Filepath,String createDate, String ackDate, 
			String systemDate, String status,String documentName, String MultipleFilePath)
	{
		 
		   String tempStatus = null;
		 
		   
		  if(Filepath != null  && createDate!=null
				  && ackDate != null && status!=null)
		  {
		  tempStatus = "Success12DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  else
		  {
		    tempStatus = "Failure12DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  
		  return tempStatus;
		 }
	
	@Override
	public String Stage13(String Filepath,String createDate, String ackDate, 
			String systemDate, String status,String documentName, String MultipleFilePath)
	{
		 
		   String tempStatus = null;
		 
		   
		  if(Filepath != null  && createDate!=null
				  && ackDate != null && status!=null)
		  {
		  tempStatus = "Success13DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  else
		  {
		    tempStatus = "Failure13DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  
		  return tempStatus;
		 }
	
	@Override
	public String Stage14(String Filepath,String createDate, String ackDate, 
			String systemDate, String status,String documentName, String MultipleFilePath)
	{
		 
		   String tempStatus = null;
		 
		   
		  if(Filepath != null  && createDate!=null
				  && ackDate != null && status!=null)
		  {
		  tempStatus = "Success14DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  else
		  {
		    tempStatus = "Failure14DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  
		  return tempStatus;
		 }
	
	@Override
	public String Stage15(String photo,String signature,String postedDate,String createDate, String ackDate, 
			String systemDate, String status,String documentName, String MultipleFilePath)
	{
		 
		   String tempStatus = null;
		 
		   
		  if(photo != null  && signature != null && postedDate != null && createDate!=null
				  && ackDate != null && status!=null)
		  {
		  tempStatus = "Success15DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  else
		  {
		    tempStatus = "Failure15DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  
		  return tempStatus;
		 }
	
	@Override
	public String Stage16(String Filepath,String createDate, String ackDate, 
			String systemDate, String status,String documentName, String MultipleFilePath)
	{
		 
		   String tempStatus = null;
		 
		   
		  if(Filepath != null  && createDate!=null
				  && ackDate != null && status!=null)
		  {
		  tempStatus = "Success16DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  else
		  {
		    tempStatus = "Failure16DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  
		  return tempStatus;
		 }
	
	@Override
	public String Stage17(String photo,String systemTaken,String photoTakenTime,String createDate, String ackDate, 
			String systemDate, String status)
	{
		 
		   String tempStatus = null;
		 
		   
		  if(photo != null  && systemTaken != null && photoTakenTime != null && createDate!=null
				  && ackDate != null && status!=null)
		  {
		  tempStatus = "Success17DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  else
		  {
		    tempStatus = "Failure17DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  
		  return tempStatus;
		 }
	
	@Override
	public String Stage18(String Filepath,String createDate, String ackDate, 
			String systemDate, String status,String documentName, String MultipleFilePath)
	{
		 
		   String tempStatus = null;
		 
		   
		  if(Filepath != null  && createDate!=null
				  && ackDate != null && status!=null)
		  {
		  tempStatus = "Success18DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  else
		  {
		    tempStatus = "Failure18DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  
		  return tempStatus;
		 }
	
	@Override
	public String Stage19(String EmailFilepath,String systemRecordFilepath,String emailPostingFilepath, String createDate, String ackDate, 
			String systemDate, String status)
	{
		 
		   String tempStatus = null;
		 
		   
		  if(EmailFilepath != null  && systemRecordFilepath!=null && emailPostingFilepath !=null && createDate!=null 
				  && ackDate != null && status!=null)
		  {
		  tempStatus = "Success19DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  else
		  {
		    tempStatus = "Failure19DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  
		  return tempStatus;
		 }
	
	@Override
	public String Stage20(String Filepath,String createDate, String ackDate, 
			String systemDate, String status,String documentName, String MultipleFilePath)
	{
		 
		   String tempStatus = null;
		 
		   
		  if(Filepath != null  && createDate!=null
				  && ackDate != null && status!=null)
		  {
		  tempStatus = "Success20DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  else
		  {
		    tempStatus = "Failure20DOL";
		   System.out.println("tempStatus ---> "+tempStatus);
		  }
		  
		  return tempStatus;
		 }
}

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
package com.db.immigration.action.DOS.service;

import javax.jws.WebService;



@WebService(endpointInterface="com.db.immigration.action.DOS.service.DOSStagesService")
public class DOSService implements DOSStagesService {
	
	/* ==================================== Department of State  ==============================================  */
	 

	 /*Department Of State
	  * Date: 10-04-2017
	  * Author:Priya
	  * digiBlitz Inc.
	 */
	@Override
	public String Stage1(String userid, String File_Path, String multiple_file_path, String created_date, String ack_date, 
			String system_date, String status) {
			   
			   String tempStatus = null;
			  
			   
			  if(File_Path != null && created_date != null && ack_date!=null)
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
	public String Stage2( String File_Path, String multiple_file_path, String created_date, String ack_date, 
			String system_date, String status){
			   
			   String tempStatus = null;
			  
			   
			  if(File_Path != null && created_date != null && ack_date!=null)
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
	public String Stage3(String Start_Date, String End_Date, String Created_Date, String ack_date, 
			String system_date, String status)
	{
	 
	   String tempStatus="null";
	   
	  if(Start_Date != null && End_Date != null && Created_Date !=null && ack_date!=null)
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
	public String Stage4( String File_Path, String created_date, String ack_date, 
			String system_date, String status) {
			   
			   String tempStatus = null;
			  
			   
			  if(File_Path != null && created_date != null && ack_date!=null)
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
	public String Stage5(String File_Path, String multiple_file_path, String created_date, String ack_date, 
			String system_date, String status) {
			   
			   String tempStatus = null;
			   
			  if(File_Path != null && created_date != null && ack_date!=null)
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
	public String Stage6(String File_Path, String Start_Date, String End_Date, String created_date, String ack_date, 
			String system_date, String status) {
			   
			   String tempStatus = null;
			 
			   
			  if(File_Path != null && created_date != null && ack_date!=null && Start_Date != null && End_Date!=null)
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
}

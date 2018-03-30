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

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style=Style.RPC)
public interface DOSStagesService {
	
	/* ==================================== Department of State  ==============================================  */
	 

	 /*Department Of State
	  * Date: 10-04-2017
	  * Author:Priya
	  * digiBlitz Inc.
	 */
	
	@WebMethod
	public String Stage1(String userid, String File_Path, String multiple_file_path, String created_date, String ack_date, 
			String system_date, String status);
	@WebMethod
	public String Stage2( String File_Path, String multiple_file_path, String created_date, String ack_date, 
			String system_date, String status);
	@WebMethod
	public String Stage3(String Start_Date, String End_Date, String Created_Date, String ack_date, 
			String system_date, String status);
	@WebMethod
	public String Stage4( String File_Path, String created_date, String ack_date, 
			String system_date, String status);
	@WebMethod
	public String Stage5(String File_Path, String multiple_file_path, String created_date, String ack_date, 
			String system_date, String status);
	@WebMethod
	public String Stage6(String File_Path, String Start_Date, String End_Date, String created_date, String ack_date, 
			String system_date, String status);
}

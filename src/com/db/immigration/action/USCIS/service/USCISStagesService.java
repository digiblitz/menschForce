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


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style=Style.RPC)
public interface USCISStagesService {
	
	/* ==================================== Department of State  ==============================================  */
	 

	 /*Department Of State
	  * Date: 14-04-2017
	  * Author:Priya
	  * digiBlitz Inc.
	 */
	
	@WebMethod
	public String Stage1(String userId,String contract, String POEFilepath,String clientLetterFilepath, String workOrderFilepath,
			String labourClearanceFilepath,String createdDate, String ackDate, 
			String systemDate, String status,String documentName, String MultipleFilePath);
	@WebMethod
	public String Stage2(String filePath, String createDate, String ackDate, 
			String systemDate, String status, String documentName, String multipleFilePath);
	@WebMethod
	public String Stage3(String h1BTransferStatus, String createDate, String ackDate, 
			String systemDate);
	@WebMethod
	public String Stage4( String filePath, String createDate, String ackDate, 
			String systemDate, String status);
	@WebMethod
	public String Stage5(String filePath,  String createDate, String ackDate, 
			String systemDate, String status,String documentName,String MultipleFilePath);
	@WebMethod
	public String Stage6(String Form94filePath,String Form94IIfilePath,String fileDescription, String filePath,  String createDate, String ackDate, 
			String systemDate, String status);
	@WebMethod
	public String Stage7(String FormI9filePath, String fileDescription, String filePath,  String createDate, String ackDate, 
			String systemDate, String status);
	
	@WebMethod
	public String Stage8(String DOC, String DOT, String DOClient, String DOW, String fileDescription,String filePath, String createDate, String ackDate, 
			String systemDate, String status);

}


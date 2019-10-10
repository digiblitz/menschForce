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
package com.db.immigration.action.DOL.service;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style=Style.RPC)
public interface DOLStagesService {
	
	/* ==================================== Department of State  ==============================================  */
	 

	
	@WebMethod
	public String Stage1(String userId, String PAFFilepath,String createDate, String ackDate, 
			String systemDate, String status,String documentName, String MultipleFilePath);
	@WebMethod
	public String Stage2(String filePath, String salaryLevel, String createDate, String ackDate, 
			String systemDate, String status);
	@WebMethod
	public String Stage3(String actualwagewithoutPrevailingwage, String createDate, String ackDate, 
			String systemDate, String status);
	@WebMethod
	public String Stage4( String DepartmentLabourFilePath, String NewspaperFilePath,String onlinePortalFilePath, 
			String createDate, String ackDate, String systemDate, String status);
	@WebMethod
	public String Stage5(String NumberofDays,String fromdate, String todate, String filePath, String createDate, String ackDate, 
			String systemDate, String status, String documentName, String MultipleFilePath);
	@WebMethod
	public String Stage6(String EduQualificationFilepath, String ExperienceFilePath, String PeopleNotSelected,
			String createdDate, String ackDate, String systemDate, String status, String documentName, String MultipleFilePath);
	@WebMethod
	public String Stage7(String fileDescription, String filePath,  String createDate, String ackDate, 
			String systemDate, String status,String documentName,String MultipleFilePath);
	
	@WebMethod
	public String Stage8(String filePath, String createDate, String ackDate, 
			String systemDate, String status,String documentName,String MultipleFilePath);
	@WebMethod
	public String Stage9( String prevailingWageTrue, String filePath,String prevailingWageFalse, String reason, String createDate, String ackDate, 
			String systemDate, String status);
	@WebMethod
	public String Stage10(String IncreasepayTrue, String filePath,String IncreasepayFalse, String reason, String createDate, String ackDate, 
			String systemDate, String status,String documentName,String MultipleFilePath);

	@WebMethod
	public String Stage11(String Filepath,String createDate, String ackDate, 
			String systemDate, String status,String documentName, String MultipleFilePath);
	
	@WebMethod
	public String Stage12(String Filepath,String createDate, String ackDate, 
			String systemDate, String status,String documentName, String MultipleFilePath);
	
	@WebMethod
	public String Stage13(String Filepath,String createDate, String ackDate, 
			String systemDate, String status,String documentName, String MultipleFilePath);
	
	@WebMethod
	public String Stage14(String Filepath,String createDate, String ackDate, 
			String systemDate, String status,String documentName, String MultipleFilePath);
	
	@WebMethod
	public String Stage15(String photo,String signature,String postedDate,String createDate, String ackDate, 
			String systemDate, String status,String documentName, String MultipleFilePath);
	
	@WebMethod
	public String Stage16(String Filepath,String createDate, String ackDate, 
			String systemDate, String status,String documentName, String MultipleFilePath);
	
	@WebMethod
	public String Stage17(String photo,String systemTaken,String photoTakenTime,String createDate, String ackDate, 
			String systemDate, String status);
	@WebMethod
	public String Stage18(String Filepath,String createDate, String ackDate, 
			String systemDate, String status,String documentName, String MultipleFilePath);
	
	@WebMethod
	public String Stage19(String EmailFilepath,String systemRecordFilepath,String emailPostingFilepath, String createDate, String ackDate, 
			String systemDate, String status);
	
	@WebMethod
	public String Stage20(String Filepath,String createDate, String ackDate, 
			String systemDate, String status,String documentName, String MultipleFilePath);
}

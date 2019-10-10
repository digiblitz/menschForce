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
package com.db.partner.service;




import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style=Style.RPC)
public interface PartnerStagesService {
	
	/* ==================================== Department of State  ==============================================  */
	 

	 /*Department Of State
	  * Date: 13-04-2017
	  * Author:Priya
	  * digiBlitz Inc.
	 */
	
	@WebMethod
	public String BecomeAPartner(String firstname, String lastname,String email, String company, 
			String designation, String address,String city, String zipcode, String country, String state,
			String phonenumber, String url, String businesstype, String publirorprivate,  String numberofcustomers,
			String numberofemployees, String whydb,String geographic, String Product) throws RemoteException, SQLException;
	
}

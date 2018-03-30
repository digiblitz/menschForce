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
package com.db.partner.service;



import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.jws.WebService;

import com.db.GeneralDBAction;



@WebService(endpointInterface="com.db.partner.service.PartnerStagesService")
public class PartnerService implements PartnerStagesService {
	GeneralDBAction db=new GeneralDBAction();
	
	@Override
	public String BecomeAPartner(String firstname, String lastname,String email, String company, 
			String Designation, String AddressLine1,String City, String Zipcode, String Country, String State,
			String PhoneNumber, String Website, String business_type, String publicorprivate,  String no_of_customer,
			String no_of_employee, String interest,String territory, String reselling) throws RemoteException, SQLException {
			   
			   String partnershipSignup = null;
			  
			   
			  if(firstname!=null && lastname != null && email != null && company !=null && Designation !=null
					  && AddressLine1 !=null && City !=null&& Zipcode !=null&& Country !=null&&
							  State !=null && PhoneNumber !=null && Website !=null && business_type !=null 
					  && publicorprivate !=null && no_of_customer != null&& no_of_employee !=null &&  interest !=null && territory !=null && reselling !=null)
			  {
				  boolean insertstatus = false;
					 insertstatus = db.insertPartnershipDetails(firstname,lastname,email,company,Designation,AddressLine1,City,State,
							 Zipcode,Country,PhoneNumber,Website,business_type,publicorprivate,no_of_customer,no_of_employee,interest,territory,reselling);
					 System.out.println("insertStatus in paternship mgmt::::::::"+insertstatus);
				  partnershipSignup = "Success";
			  
			  
			   System.out.println("partnershipSignup ---> "+partnershipSignup);
			  }
			  else
			  {
				  partnershipSignup = "Failure";
			   System.out.println("partnershipSignup ---> "+partnershipSignup);
			  }
			  
			  return partnershipSignup;
			 }
}

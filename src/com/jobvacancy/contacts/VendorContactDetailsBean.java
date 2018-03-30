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
package com.jobvacancy.contacts;

public class VendorContactDetailsBean {

	String vendor_id = null;
	String vendor_fname =  null;
	String vendor_lname = null;
	String vendor_email = null;
	String vendor_company = null;
	String vendor_homePhone = null;
	String vendor_businessPhone = null;
	String vendor_contactPerson = null;
	
	public String getvendor_id() {
		return vendor_id;
		}
	public void setvendor_id(String vendor_id) {
		this.vendor_id = vendor_id;
		}
	public String getvendor_fname() {
		return vendor_fname;
		}
	public void setvendor_fname(String vendor_fname) {
		this.vendor_fname = vendor_fname;
		}
	public String getvendor_lname() {
		return vendor_lname;
		}
	public void setvendor_lname(String vendor_lname) {
		this.vendor_lname = vendor_lname;
		}
	public String getvendor_email() {
		return vendor_email;
		}
	public void setvendor_email(String vendor_email) {
		this.vendor_email = vendor_email;
		}
	public String getvendor_company() {
		return vendor_company;
		}
	public void setvendor_company(String vendor_company) {
	this.vendor_company = vendor_company;
	}
	public String getvendor_homePhone() {
		return vendor_homePhone;
		}
	public void setvendor_homePhone(String vendor_homePhone) {
	this.vendor_homePhone = vendor_homePhone;
	}
	public String getvendor_businessPhone() {
		return vendor_businessPhone;
		}
	public void setvendor_businessPhone(String vendor_businessPhone) {
	this.vendor_businessPhone = vendor_businessPhone;
	}
	public String getvendor_contactPerson() {
		return vendor_contactPerson;
		}
	public void setvendor_contactPerson(String vendor_contactPerson) {
	this.vendor_contactPerson = vendor_contactPerson;
	}
	
	
	/** Creates a new instance of PostRequirementsBean */
	public VendorContactDetailsBean() {

	}
		
	public VendorContactDetailsBean(String vendor_id, String vendor_fname, String vendor_lname,
			String vendor_email, String vendor_company,String vendor_homePhone, String vendor_businessPhone, 
			String vendor_contactPerson){
		
		this.vendor_id = vendor_id;
		this.vendor_fname = vendor_fname;
		this.vendor_lname = vendor_lname;
		this.vendor_email = vendor_email;
		this.vendor_company = vendor_company;
		this.vendor_homePhone = vendor_homePhone;
		this.vendor_businessPhone = vendor_businessPhone;
		this.vendor_contactPerson = vendor_contactPerson;
		
		}
	
}

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
package com.jobvacancy.contacts;

public class ClientContactDetailsBean {

	String client_id = null;
	String client_name =  null;
	String responsible_person = null;
	String client_email = null;
	String client_company = null;
	String client_jobTitle = null;
	String client_businessPhone = null;
	String client_contactId = null;
	
	
	public String getclient_id() {
		return client_id;
		}
	public void setclient_id(String client_id) {
		this.client_id = client_id;
		}
	public String getclient_name() {
		return client_name;
		}
	public void setclient_name(String client_name) {
		this.client_name = client_name;
		}
	public String getresponsible_person() {
		return responsible_person;
		}
	public void setresponsible_person(String responsible_person) {
		this.responsible_person = responsible_person;
		}
	public String getclient_email() {
		return client_email;
		}
	public void setclient_email(String client_email) {
		this.client_email = client_email;
		}
	public String getclient_company() {
		return client_company;
		}
	public void setclient_company(String client_company) {
	this.client_company = client_company;
	}
	public String getclient_jobTitle() {
		return client_jobTitle;
		}
	public void setclient_jobTitle(String client_jobTitle) {
	this.client_jobTitle = client_jobTitle;
	}
	public String getclient_businessPhone() {
		return client_businessPhone;
		}
	public void setclient_businessPhone(String client_businessPhone) {
	this.client_businessPhone = client_businessPhone;
	}
	public String getclient_contactId() {
		return client_contactId;
		}
	public void setclient_contactId(String client_contactId) {
	this.client_contactId = client_contactId;
	}
	
	
	/** Creates a new instance of PostRequirementsBean */
	public ClientContactDetailsBean(){
	}
	
	public ClientContactDetailsBean(String client_id, String client_name, String responsible_person,
			String client_email, String client_company,String client_jobTitle, String client_businessPhone, 
			String client_contactId){
		
		this.client_id = client_id;
		this.client_name = client_name;
		this.responsible_person = responsible_person;
		this.client_email = client_email;
		this.client_company = client_company;
		this.client_jobTitle = client_jobTitle;
		this.client_businessPhone = client_businessPhone;
		this.client_contactId = client_contactId;
		
		}
	
}

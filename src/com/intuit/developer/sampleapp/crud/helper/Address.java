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
package com.intuit.developer.sampleapp.crud.helper;

import com.intuit.ipp.data.PhysicalAddress;
import com.intuit.ipp.data.WebSiteAddress;

/**
 * @author dderose
 *
 */
public final class Address {
	
	private Address() {
		
	}

	public static PhysicalAddress getPhysicalAddress() {
		PhysicalAddress billingAdd = new PhysicalAddress();
		billingAdd.setLine1("123 Main St");
		billingAdd.setCity("Mountain View");
		billingAdd.setCountry("United States");
		billingAdd.setCountrySubDivisionCode("CA");
		billingAdd.setPostalCode("94043");
		return billingAdd;
	}
	
	public static WebSiteAddress getWebSiteAddress() {
		WebSiteAddress webSite = new WebSiteAddress();
		webSite.setURI("http://abccorp.com");
		webSite.setDefault(true);
		webSite.setTag("Business");
		return webSite;
	}

}

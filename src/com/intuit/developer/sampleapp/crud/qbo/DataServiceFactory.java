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
package com.intuit.developer.sampleapp.crud.qbo;

import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;

/**
 * 
 * @author dderose
 *
 */

public class DataServiceFactory {

	
	/**
	 * Initializes DataService for a given app/company profile
	 * 
	 * @return
	 * @throws FMSException
	 */
	public static DataService getDataService() throws FMSException {
		//create dataservice
		return new DataService(ContextFactory.getContext());
	}
}

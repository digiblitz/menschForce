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
package com.intuit.developer.sampleapp.crud.entities.vendor;

import java.util.List;

import com.intuit.developer.sampleapp.crud.helper.VendorHelper;
import com.intuit.developer.sampleapp.crud.qbo.DataServiceFactory;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.data.Vendor;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to delete vendor data
 * Name-list resources can only be soft deleted meaning, marked as inactive
 * VendorDelete.java
 * 
 * @author dderose
 *
 */
public class VendorDelete {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	public static void main(String[] args) {
		try {
			deleteVendor();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void deleteVendor() throws FMSException {
		
		try {
			DataService service = DataServiceFactory.getDataService();
			
			// create vendor
			Vendor vendor = VendorHelper.getVendorWithMandatoryFields();
			Vendor addVendor = service.add(vendor);
			LOG.info("Vendor added : " + addVendor.getId() + " active flag ::: " + addVendor.isActive());

			// set active flag as false to soft delete
			addVendor.setActive(false);
			Vendor deletedVendor = service.update(addVendor);		
			LOG.info("Vendor deleted : " + deletedVendor.getId() + " active flag ::: " + deletedVendor.isActive());
			
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			for(Error item : list){
				LOG.error("Error while deleting entity :: " + item.getMessage());
				//System.out.println(item.getMessage());
			}
		}
		
	}
}

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

import java.util.List;

import com.intuit.ipp.data.ReferenceType;
import com.intuit.ipp.data.TaxCode;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;

/**
 * @author dderose
 *
 */
public final class TaxCodeInfo {
	
	private TaxCodeInfo() {
		
	}

	public static TaxCode getTaxCode(DataService service) throws FMSException {
		List<TaxCode> taxcodes = (List<TaxCode>) service.findAll(new TaxCode());
		return taxcodes.get(0); 
	}
	
	  public static ReferenceType getTaxCodeRef(TaxCode taxcode) {
			ReferenceType taxcodeRef = new ReferenceType();
			taxcodeRef.setName(taxcode.getName());
			taxcodeRef.setValue(taxcode.getId());
			return taxcodeRef;
	  }

}

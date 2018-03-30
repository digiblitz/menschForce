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
package com.intuit.developer.sampleapp.crud.entities.employee;

import java.util.List;

import com.intuit.developer.sampleapp.crud.helper.EmployeeHelper;
import com.intuit.developer.sampleapp.crud.qbo.DataServiceFactory;
import com.intuit.ipp.data.Employee;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to read employee data using employee id
 * Note: We'll create an entity first and then read the same
 * 
 * @author dderose
 *
 */
public class EmployeeRead {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	public static void main(String[] args) {
		try {
			getEmployee();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void getEmployee() throws FMSException {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// add employee with minimum mandatory fields
			Employee employee = EmployeeHelper.getEmployeeWithMandatoryFields();
			Employee savedEmployee = service.add(employee);
			LOG.info("Employee with mandatory fields created: " + savedEmployee.getId() + " ::employee name: " + savedEmployee.getDisplayName());
			
			Employee employeeOut = service.findById(savedEmployee);
			LOG.info("Employee Display name: " + employeeOut.getDisplayName());
			
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			for(Error item : list){
				LOG.error("Error while deleting entity :: " + item.getMessage());
				//System.out.println(item.getMessage());
			}	
		}
		
	}
	
}

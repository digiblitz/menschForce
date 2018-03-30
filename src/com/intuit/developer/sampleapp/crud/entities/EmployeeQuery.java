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
package com.intuit.developer.sampleapp.crud.entities;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.db.GeneralDBAction;
import com.intuit.developer.sampleapp.crud.helper.EmployeeHelper;
import com.intuit.developer.sampleapp.crud.qbo.DataServiceFactory;
import com.intuit.ipp.data.Employee;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.services.QueryResult;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to query employee data
 * 1. Query all records
 * 2. Query by id, note we'll add the entity first and then query
 * 
 * @author dderose
 *
 */
@SuppressWarnings("unused")
@Controller
public class EmployeeQuery {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
	static GeneralDBAction db=new GeneralDBAction();
	
//	public static void main(String[] args) {
//		try {
//			queryEmployees();
//		} catch (Exception e) {
//			LOG.error("Error during CRUD", e.getCause());
//		}
//	}
	
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping("/syncGetEmployee.html")
	public ModelAndView syncEmployee(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{

		boolean empSyncStatus = false;
		try {
			empSyncStatus = queryEmployees();
			if(empSyncStatus == true){
				ArrayList empArrayList = new ArrayList();
				empArrayList = db.getEmployeeList();
			request.setAttribute("empSyncStatus", "success");
			}else{
			request.setAttribute("empSyncStatus", "fail");
			}
			return new ModelAndView("QB_jsp/QBEmployeePortal");
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
			request.setAttribute("empSyncStatus", "fail");
			return new ModelAndView("QB_jsp/QBEmployeePortal");
		}
			
	}
	
	public static boolean queryEmployees() throws FMSException {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// get all employees
			
			String sql = "select * from employee";
			QueryResult queryResult = service.executeQuery(sql);
			int count = queryResult.getEntities().size();
			int QBSyncStatus = 0;
			
			LOG.info("Total number of employees: " + count);

		/*	// add employee with minimum mandatory fields
			Employee employee = EmployeeHelper.getEmployeeWithMandatoryFields();
			Employee savedEmployee = service.add(employee);
			LOG.info("Employee with mandatory fields created: " + savedEmployee.getId() + " ::employee name: " + savedEmployee.getDisplayName());

			// get employee data based on id
			sql = "select * from employee where id = '" + savedEmployee.getId() + "'"; 
			queryResult = service.executeQuery(sql);
		*/
			for(int i=0;i<count;i++){
			Employee employee = new Employee();
			employee = (Employee)queryResult.getEntities().get(i);
			LOG.info("Employee ssn details------"+employee.getSSN());
			LOG.info("Employee given name details------"+employee.getGivenName());
			LOG.info("Employee family details------"+employee.getFamilyName());
			LOG.info("Employee display name details------"+employee.getDisplayName());
			LOG.info("Employee primary phonedetails------"+employee.getMobile().getFreeFormNumber());
			LOG.info("Employee address details------"+employee.getPrimaryAddr().getCity());
			LOG.info("Employee address details------"+employee.getPrimaryAddr().getCountry());
			LOG.info("Employee address details------"+employee.getPrimaryAddr().getCountrySubDivisionCode());
			LOG.info("Employee address details------"+employee.getPrimaryAddr().getLine1());
			LOG.info("Employee address details------"+employee.getPrimaryAddr().getPostalCode());
			LOG.info("Employee entity details------"+employee.getId());
			db.insertQBEmployeeDetails(employee.getSSN(),employee.getGivenName(),employee.getFamilyName(),employee.getDisplayName(),employee.getMobile(),employee.getPrimaryAddr(),employee.getId(),QBSyncStatus);
			
			}
			return true;
			
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			for(Error item : list){
				LOG.error("Error while deleting entity :: " + item.getMessage());
				//System.out.println(item.getMessage());
			}	
		}
		return false;
		
	}
}

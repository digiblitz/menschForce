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

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to create employee
 * 1. Using mandatory fields
 * 2. Using all fields
 * 
 * @author dderose
 *
 */
//@Controller
public class EmployeeCreate {
	
	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	static GeneralDBAction db=new GeneralDBAction();
	
//	public static void main(String[] args) {
//		try {
//			createEmployee();
//		} catch (Exception e) {
//			LOG.error("Error during CRUD", e.getCause());
//		}
//	}
	
	//@RequestMapping("/syncEmployee.html")
	public ModelAndView syncEmployee(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{

		boolean empSyncStatus = false;
		try {
			empSyncStatus = createEmployee();
			if(empSyncStatus == true){
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
	
	

	@SuppressWarnings("rawtypes")
	public static boolean createEmployee() throws Exception {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// add employee with minimum mandatory fields
//			Employee employee = EmployeeHelper.getEmployeeWithMandatoryFields();
//			Employee savedEmployee = service.add(employee);
//			LOG.info("Employee with mandatory fields created: " + savedEmployee.getId() + " ::employee name: " + savedEmployee.getDisplayName());
			
			List <String>empEntityList = new ArrayList<String>();
			ArrayList empArrayList = db.getEmployeeList();
							
				if(empArrayList != null){
				Iterator itr = empArrayList.iterator();
					while(itr.hasNext()){
						String strarr[] = (String[])itr.next();
						
						String unique_emp_id = strarr[0];
			            //String emp_id = strarr[1];
			            String emp_SSN_QB = strarr[2];
			            String emp_GivenName = strarr[3];
			            
			            String emp_FamilyName = strarr[4];
			            String emp_PrimaryPhone = strarr[5];
			            String emp_Address1 = strarr[6];
			            String emp_City = strarr[7];
			            String emp_State = strarr[8];
			            String emp_Country = strarr[9];
			            String emp_CountrySubDivisionCode = strarr[10];
			            String emp_PostalCode = strarr[11];
			            //String emp_QBSync_Status = strarr[12];
			            //String emp_register_date = strarr[13];
			            
						// add employee with all fields
						Employee employee = EmployeeHelper.getEmployeeWithAllFields(emp_GivenName,emp_FamilyName,emp_SSN_QB,emp_PrimaryPhone,emp_Address1,emp_City,emp_State,emp_Country,emp_CountrySubDivisionCode,emp_PostalCode);
						Employee savedEmployee = service.add(employee);
						LOG.info("Employee with all fields created: " + savedEmployee.getId() + " ::employee name: " + savedEmployee.getDisplayName());
						empEntityList.add(savedEmployee.getId());
							if(!(empEntityList.isEmpty()) && empEntityList != null){
							return db.updateEmpQBStatus(unique_emp_id, savedEmployee.getId());
							}else{
								System.out.println("empEntityList-------------is Empty");
								return db.updateEmpQBStatus(unique_emp_id, savedEmployee.getId());
							}
						}
					}
			
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

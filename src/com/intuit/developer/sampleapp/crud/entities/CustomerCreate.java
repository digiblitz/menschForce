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
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.db.GeneralDBAction;
import com.intuit.developer.sampleapp.crud.helper.CustomerHelper;
import com.intuit.developer.sampleapp.crud.qbo.DataServiceFactory;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to create customer
 * 1. Using mandatory fields
 * 2. Using all fields
 * 
 * @author dderose
 *
 */
@Controller
public class CustomerCreate {
	
	private static final org.slf4j.Logger LOG = Logger.getLogger();
	static GeneralDBAction db=new GeneralDBAction();
		
//	public static void main(String[] args) {
//		try {
//			createCustomer();
//		} catch (Exception e) {
//			LOG.error("Error during CRUD", e.getCause());
//		}
//	}
	
	@RequestMapping("/syncCustomer.html")
	public ModelAndView syncEmployee(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{

		boolean customerSyncStatus = false;
		try {
			customerSyncStatus = createCustomer();
			if(customerSyncStatus == true){
			request.setAttribute("customerSyncStatus", "success");
			}else{
			request.setAttribute("customerSyncStatus", "fail");
			}
			return new ModelAndView("QB_jsp/QBCustomerPortal");
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
			request.setAttribute("customerSyncStatus", "fail");
			return new ModelAndView("QB_jsp/QBCustomerPortal");
		}
			
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean createCustomer() throws Exception {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// add customer with minimum mandatory fields
//			Customer customer = CustomerHelper.getCustomerWithMandatoryFields();
//			Customer savedCustomer = service.add(customer);
//			LOG.info("Customer with mandatory fields created: " + savedCustomer.getId() + " ::customer name: " + savedCustomer.getDisplayName());
			
			List <String>customerEntityList = new ArrayList<String>();
			ArrayList customerArrayList = db.getCustomerList();
							
				if(customerArrayList != null){
				Iterator itr = customerArrayList.iterator();
					while(itr.hasNext()){
						String strarr[] = (String[])itr.next();
						
						String unique_customer_id = strarr[0];
						String customer_companyName = strarr[1];
						
						Customer customer = CustomerHelper.getCustomerWithMandatoryFields(customer_companyName);
						Customer savedCustomer = service.add(customer);
						LOG.info("Customer with MandatoryFields created: " + savedCustomer.getId() + " ::customer name: " + savedCustomer.getDisplayName());
						customerEntityList.add(savedCustomer.getId());
						if(!(customerEntityList.isEmpty()) && customerEntityList != null){
						return db.updateCustomerQBStatus(unique_customer_id, savedCustomer.getId());
						}else{
							System.out.println("customerEntityList-------------is Empty");
							return db.updateCustomerQBStatus(unique_customer_id, savedCustomer.getId());
						}
					}
				}
			// add customer with all fields
			
			
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

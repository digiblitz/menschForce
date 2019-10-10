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
package com.intuit.developer.sampleapp.crud.entities;

import java.rmi.RemoteException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.intuit.developer.sampleapp.crud.helper.ItemHelper;
import com.intuit.developer.sampleapp.crud.qbo.DataServiceFactory;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.data.Item;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to create item
 * 
 * @author dderose
 *
 */
@Controller
public class ItemCreate {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
//	public static void main(String[] args) {
//		try {
//			createItem();
//		} catch (Exception e) {
//			LOG.error("Error during CRUD", e.getCause());
//		}
//	}
	
	@RequestMapping("/syncServiceItems.html")
	public ModelAndView syncEmployee(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{

		boolean ServiceItemsSyncStatus = false;
		try {
			ServiceItemsSyncStatus = createItem();
			if(ServiceItemsSyncStatus == true){
			request.setAttribute("ServiceItemsSyncStatus", "success");
			}else{
			request.setAttribute("ServiceItemsSyncStatus", "fail");
			}
			return new ModelAndView("QB_jsp/QBServiceItemsPortal");
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
			request.setAttribute("ServiceItemsSyncStatus", "fail");
			return new ModelAndView("QB_jsp/QBServiceItemsPortal");
		}
			
	}
	
	public static boolean createItem() throws Exception {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// add item
			Item item = ItemHelper.getItemFields(service);
			Item savedItem = service.add(item);
			LOG.info("Item created: " + savedItem.getId());
						
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

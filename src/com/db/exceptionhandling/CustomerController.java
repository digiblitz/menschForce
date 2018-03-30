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
package com.db.exceptionhandling;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomerController {

//	@RequestMapping(value = "/customError.html", method = RequestMethod.GET)
//	public ModelAndView getPages() throws Exception {
//
//		//throw new CustomGenericException("E888", "This is custom message X");
//		throw new Exception("This is custom error");
//
//	}
	
	@ExceptionHandler(CustomGenericException.class)
	public ModelAndView handleCustomException(CustomGenericException ex) {

		ModelAndView model = new ModelAndView("ErrorPages/generic_error");
		model.addObject("exception", ex);
		return model;

	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		
		ModelAndView model = new ModelAndView("ErrorPages/exception_error");
		System.out.println("Error Message in  CustomerController---------------->"+ex.getMessage());
		model.addObject("exception", ex.getMessage());
		return model;

	}
	
	
}

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
package com.dberp.action;

import java.io.InputStream;
import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.db.GeneralDBAction;
import com.hlccommon.util.Debug;
import com.infusionejb.session.InfusionSessionBean;
import com.itextpdf.text.Font;

@Controller
public class IndianPayrollAction {
	
	public static final String config_prop = "config.properties";
	public static final String LOG_FILE = "Log4j.properties";
	InputStream is;
	InputStream config_inputStream;
	
	private InfusionSessionBean obj1;
	{
		try{
			obj1 = new InfusionSessionBean();
		}catch(Exception e){
			 throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unused")
	private static final Font ALIGN_RIGHT = null;
	
	
	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping("/initInidanPayroll.html")
	public ModelAndView initInidanPayroll(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		
		Debug.print("IndianPayrollAction.initInidanPayroll()");
		
		return new ModelAndView("requirements/frmCreateEmployee");
	}
	
	
	@RequestMapping("/CreateIndianPayroll.html")
	public ModelAndView CreateIndianPayroll(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		
		Debug.print("IndianPayrollAction.CreateIndianPayroll()");
		String Emp_number=null;
		String first_name=null;
		String last_name=null;
		String designation=null;
		String Doj=null;
		String email=null;
		String Address=null;
		String City=null;
		String State=null;
		String postal_code=null;
		String Country=null;
		String personal_id=null;
		String PF_number=null;
		String ESI_number=null;
		String salary=null;
		String name_on_passbook=null;
		String bank_name=null;
		String branch=null;
		String ifsc=null;
		String phone=null;
		
		Emp_number=request.getParameter("EmpNo");
		first_name=request.getParameter("first_name");
		last_name=request.getParameter("last_name");
		designation=request.getParameter("designation");
		Doj=request.getParameter("Doj");
		email=request.getParameter("email");
		phone=request.getParameter("phone");
		Address=request.getParameter("Address");
		City=request.getParameter("City");
		State=request.getParameter("State");
		postal_code=request.getParameter("postal_code");
		Country=request.getParameter("Country");
		personal_id=request.getParameter("personal_id");
		PF_number=request.getParameter("PF_number");
		ESI_number=request.getParameter("ESI_number");
		name_on_passbook=request.getParameter("name_on_passbook");
		bank_name=request.getParameter("bank_name");
		branch=request.getParameter("branch");
		ifsc=request.getParameter("ifsc");
		salary=request.getParameter("salary");
		
		
		
		System.out.println("Employee number in indianpayroll====="+Emp_number);
		System.out.println("first_name  in indianpayroll====="+first_name);
		System.out.println("last_name in indianpayroll====="+last_name);
		System.out.println("designation in indianpayroll====="+designation);
		System.out.println("Doj  in indianpayroll====="+Doj);
		System.out.println("email  in indianpayroll====="+email);
		System.out.println("phone  in indianpayroll====="+phone);
		System.out.println("Address  in indianpayroll====="+Address);
		System.out.println("City  in indianpayroll====="+City);
		System.out.println("State  in indianpayroll====="+State);
		System.out.println("postal_code  in indianpayroll====="+postal_code);
		System.out.println("Country  in indianpayroll====="+Country);
		System.out.println("personal_id  in indianpayroll====="+personal_id);
		System.out.println("PF_number  in indianpayroll====="+PF_number);
		System.out.println("ESI_number  in indianpayroll====="+ESI_number);
		System.out.println("name_on_passbook  in indianpayroll====="+name_on_passbook);
		System.out.println("bank_name  in indianpayroll====="+bank_name);
		System.out.println("branch  in indianpayroll====="+branch);
		System.out.println("ifsc  in indianpayroll====="+ifsc);
		System.out.println("salary  in indianpayroll====="+salary);
		
		
		return new ModelAndView("requirements/frmIndianPayroll");
	}

}

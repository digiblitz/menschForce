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
package com.intuit.developer.sampleapp.crud.helper;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;

import com.intuit.ipp.data.Employee;
import com.intuit.ipp.data.Gender;
import com.intuit.ipp.data.PhysicalAddress;
import com.intuit.ipp.data.ReferenceType;
import com.intuit.ipp.data.TelephoneNumber;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.DateUtils;

/**
 * @author dderose
 *
 */
public final class EmployeeHelper {
	
	private EmployeeHelper() {
		
	}

	public static Employee getEmployeeWithMandatoryFields() throws FMSException {
		Employee employee = new Employee();
		// Mandatory Fields
		employee.setGivenName(RandomStringUtils.randomAlphanumeric(4));
		employee.setFamilyName(RandomStringUtils.randomAlphanumeric(1));
		employee.setDisplayName(RandomStringUtils.randomAlphanumeric(4));
		
		return employee;

	}
	
	public static Employee getEmployeeWithAllFields() throws FMSException, ParseException {
		Employee employee = new Employee();
		// Mandatory Fields
		employee.setGivenName(RandomStringUtils.randomAlphanumeric(4));
		employee.setMiddleName(RandomStringUtils.randomAlphanumeric(1));
		employee.setFamilyName(RandomStringUtils.randomAlphanumeric(4));
		employee.setFullyQualifiedName(RandomStringUtils.randomAlphanumeric(4));
		employee.setCompanyName(RandomStringUtils.randomAlphanumeric(2));
		employee.setDisplayName(RandomStringUtils.randomAlphanumeric(4));
		employee.setPrintOnCheckName(RandomStringUtils.randomAlphanumeric(3));

		// Optional Fields
		employee.setPrimaryPhone(Telephone.getPrimaryPhone());
		employee.setMobile(Telephone.getMobilePhone());
		
		employee.setPrimaryAddr(Address.getPhysicalAddress());
		
		employee.setSSN("empSSN_"+ RandomStringUtils.randomAlphanumeric(6));

		employee.setHiredDate(DateUtils.getDateWithPrevDays(300));
		employee.setReleasedDate(DateUtils.getDateWithNextDays(300));
		employee.setBirthDate(DateUtils.getDateWithPrevDays(6200));
		
		employee.setGender(Gender.MALE);
		employee.setEmployeeNumber("emp_no"+ RandomStringUtils.randomAlphanumeric(7));

		return employee;

	}
	
	public static Employee getEmployee(DataService service) throws FMSException, ParseException {
		List<Employee> employees = (List<Employee>) service.findAll(new Employee());
		if (!employees.isEmpty()) {
			return employees.get(0);
		}
		return createEmployee(service);
	}

	private static Employee createEmployee(DataService service) throws FMSException, ParseException {
		return service.add(getEmployeeWithAllFields());
	}

	public static ReferenceType getEmployeeRef(Employee employee) {
		ReferenceType employeeRef = new ReferenceType();
		employeeRef.setName(employee.getDisplayName());
		employeeRef.setValue(employee.getId());
		return employeeRef;
	}

	public static Employee getEmployeeWithAllFields(String emp_GivenName,
			String emp_FamilyName, String emp_SSN_QB, String emp_PrimaryPhone,
			String emp_Address1, String emp_City, String emp_State,
			String emp_Country, String emp_CountrySubDivisionCode,
			String emp_PostalCode) {
		
		Employee employee = new Employee();
		employee.setSSN(emp_SSN_QB);
		employee.setGivenName(emp_GivenName);
		employee.setFamilyName(emp_FamilyName);
		//employee.setDisplayName("PrabhudB");
		
		
		TelephoneNumber priPhone = new TelephoneNumber();
		//priPhone.setAreaCode("044");
		//priPhone.setCountryCode("91");
		priPhone.setFreeFormNumber(emp_PrimaryPhone);
		
		employee.setPrimaryPhone(priPhone);
		
		PhysicalAddress priAddress = new PhysicalAddress();
		//priAddress.setId("67");
		priAddress.setLine1(emp_Address1);
		priAddress.setLine2(emp_State);
		priAddress.setCity(emp_City);
		priAddress.setCountry(emp_Country);
		priAddress.setCountrySubDivisionCode(emp_CountrySubDivisionCode);
		priAddress.setPostalCode(emp_PostalCode);
		
		employee.setPrimaryAddr(priAddress);
		return employee;
	}


}

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
package com.jobvacancy.contacts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.db.GeneralDBAction;
import com.hlccommon.util.Debug;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.Part;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.jobvacancy.requirements.*;


@Controller
public class ContactDetailsActionServlet {
	
	Logger logger = LoggerFactory.getLogger("RequirementAction.class");
	public static final String config_prop = "config.properties";
	public static final String LOG_FILE = "Log4j.properties";
	InputStream is;
	InputStream config_inputStream;
	
	int totalNofPages = 1;
	RequirementsAction obj  = new RequirementsAction();
	@RequestMapping("/initCreateClientContacts.html")
	public ModelAndView initCreateClientContacts(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		return new ModelAndView("requirements/createClientContacts");
	}
	@RequestMapping("/CreateClientContacts.html")
	public ModelAndView CreateClientContacts(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		
		ClientContactDetailsBean objClientContacts = new ClientContactDetailsBean();
		GeneralDBAction db=new GeneralDBAction();
		
		String client_id = null;
		String client_name =  null;
		String responsible_person = null;
		String client_email = null;
		String client_company = null;
		//String client_jobTitle = null;
		String client_businessPhone = null;
		//String client_contactId = null;
		
//		client_id = "test";
//		client_name = "test";
//		responsible_person = "test";
//		client_email = "test";
//		client_company = "test";
//		client_jobTitle = "test";
//		client_businessPhone = "test";
//		client_contactId = "test";
		
		client_id = request.getParameter("client_id");
		client_name = request.getParameter("client_name");
		responsible_person = request.getParameter("responsible_person");
		client_email = request.getParameter("client_email");
		client_company = request.getParameter("client_company");
		//client_jobTitle = request.getParameter("client_jobTitle");
		client_businessPhone = request.getParameter("client_businessPhone");
		//client_contactId = request.getParameter("client_contactId");
		
		System.out.println("client_id--------------->"+client_id);
		System.out.println("client_name--------------->"+client_name);
		System.out.println("responsible_person--------------->"+responsible_person);
		System.out.println("client_email--------------->"+client_email);
		System.out.println("client_company--------------->"+client_company);
		//System.out.println("client_jobTitle--------------->"+client_jobTitle);
		System.out.println("client_businessPhone--------------->"+client_businessPhone);
		//System.out.println("client_contactId--------------->"+client_contactId);
		
		
		objClientContacts.setclient_id(client_id);
		objClientContacts.setclient_name(client_name);
		objClientContacts.setresponsible_person(responsible_person);
		objClientContacts.setclient_email(client_email);
		objClientContacts.setclient_company(client_company);
		//objClientContacts.setclient_jobTitle(client_jobTitle);
		objClientContacts.setclient_businessPhone(client_businessPhone);
		//objClientContacts.setclient_contactId(client_contactId);
		
		boolean insertClientContactStatus = false;
		insertClientContactStatus = db.insertClientContacts(objClientContacts);
		if(insertClientContactStatus = true){
			System.out.println("Insert Client Contacts was successfully comes from Servlet------------>"+insertClientContactStatus);
			Debug.print("ContactDetailsActionServlet.clientContactList()");
	        ArrayList ClientContactList = new ArrayList();
	        ClientContactList = (ArrayList)db.getAllClientDetails();

	        request.setAttribute("allClientContactList",null);
	        request.setAttribute("allClientContactList",ClientContactList);
			return new ModelAndView("requirements/listClientContacts");
		}else{
			System.out.println("Insert Job Post was not successfully comes from Servlet------------>"+insertClientContactStatus);
			Debug.print("ContactDetailsActionServlet.clientContactList()");
	        ArrayList ClientContactList = new ArrayList();
	        ClientContactList = (ArrayList)db.getAllClientDetails();

	        request.setAttribute("allClientContactList",null);
	        request.setAttribute("allClientContactList",ClientContactList);
			return new ModelAndView("requirements/listClientContacts");
		}
	}
	
	@RequestMapping("/ListClientContacts.html")
	public ModelAndView ListClientContacts(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("ContactDetailsActionServlet.clientContactList()");
        ArrayList ClientContactList = new ArrayList();
        ClientContactList = (ArrayList)db.getAllClientDetails();
        String currentPageNo = (String)request.getParameter("currentPageNo");
        
        ClientContactList = doPagination(ClientContactList,currentPageNo);

        System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
        request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
        System.out.println("currentPageNo------------------>"+currentPageNo);
	 	request.setAttribute("currentPageNo", currentPageNo);
        request.setAttribute("allClientContactList",null);
        request.setAttribute("allClientContactList",ClientContactList);
		return new ModelAndView("requirements/listClientContacts");
	}
	
	@RequestMapping("/ViewClientContactsByUniqueId.html")
	public ModelAndView ViewClientContactsByUniqueId(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("ContactDetailsActionServlet.ViewListClientContactsByUniqueId()");
		String clientContactByUniqueId = null;
		clientContactByUniqueId = request.getParameter("uniqueId");
        ArrayList ClientContactList = new ArrayList();
        ClientContactList = (ArrayList)db.getClientContactByUniqueId(clientContactByUniqueId);

        request.setAttribute("ClientContactList",null);
        request.setAttribute("ClientContactList",ClientContactList);
		return new ModelAndView("requirements/viewClientContact");
	}
	
/*----------------------Vendor Contact Action Start here----------------------------------*/
	@RequestMapping("/initCreateVendrContacts.html")
	public ModelAndView initCreateVendorContacts(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		return new ModelAndView("requirements/CreateVendorContacts");
	}
	@RequestMapping("/CreateVendrContacts.html")
	public ModelAndView CreateVendorContacts(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		
		VendorContactDetailsBean objVendorContacts = new VendorContactDetailsBean();
		GeneralDBAction db=new GeneralDBAction();
		
		String vendor_id = null;
		String vendor_fname =  null;
		String vendor_lname = null;
		String vendor_email = null;
		String vendor_company = null;
		String vendor_homePhone = null;
		String vendor_businessPhone = null;
		String vendor_contactPerson = null;
		
//		vendor_id = "test";
//		vendor_fname = "test";
//		vendor_lname = "test";
//		vendor_email = "test";
//		vendor_company = "test";
//		vendor_homePhone = "test";
//		vendor_businessPhone = "test";
//		vendor_contactPerson = "test";
		
		vendor_id = request.getParameter("vendor_id");
		vendor_fname = request.getParameter("vendor_fname");
		vendor_lname = request.getParameter("vendor_lname");
		vendor_email = request.getParameter("vendor_email");
		vendor_company = request.getParameter("vendor_company");
		vendor_homePhone = request.getParameter("vendor_homePhone");
		vendor_businessPhone = request.getParameter("vendor_businessPhone");
		vendor_contactPerson = request.getParameter("vendor_contactPerson");
		
		System.out.println("vendor_id--------------->"+vendor_id);
		System.out.println("vendor_fname--------------->"+vendor_fname);
		System.out.println("vendor_lname--------------->"+vendor_lname);
		System.out.println("vendor_email--------------->"+vendor_email);
		System.out.println("vendor_company--------------->"+vendor_company);
		System.out.println("vendor_homePhone--------------->"+vendor_homePhone);
		System.out.println("vendor_businessPhone--------------->"+vendor_businessPhone);
		System.out.println("vendor_contactPerson--------------->"+vendor_contactPerson);
		
		
		objVendorContacts.setvendor_id(vendor_id);
		objVendorContacts.setvendor_fname(vendor_fname);
		objVendorContacts.setvendor_lname(vendor_lname);
		objVendorContacts.setvendor_email(vendor_email);
		objVendorContacts.setvendor_company(vendor_company);
		objVendorContacts.setvendor_homePhone(vendor_homePhone);
		objVendorContacts.setvendor_businessPhone(vendor_businessPhone);
		objVendorContacts.setvendor_contactPerson(vendor_contactPerson);
		
		boolean insertVendorContactStatus = false;
		insertVendorContactStatus = db.insertVendorContacts(objVendorContacts);
		if(insertVendorContactStatus = true){
			System.out.println("Insert Vendor Contacts was successfully comes from Servlet------------>"+insertVendorContactStatus);
			Debug.print("ContactDetailsActionServlet.vendorContactList()");
	        ArrayList VendorContactList = new ArrayList();
	        VendorContactList = (ArrayList)db.getAllVendorDetails();

	        request.setAttribute("allVendorContactList",null);
	        request.setAttribute("allVendorContactList",VendorContactList);
			return new ModelAndView("requirements/listVendorContacts");
		}else{
			System.out.println("Insert Job Post was not successfully comes from Servlet------------>"+insertVendorContactStatus);
			Debug.print("ContactDetailsActionServlet.vendorContactList()");
	        ArrayList VendorContactList = new ArrayList();
	        VendorContactList = (ArrayList)db.getAllVendorDetails();

	        request.setAttribute("allVendorContactList",null);
	        request.setAttribute("allVendorContactList",VendorContactList);
			return new ModelAndView("requirements/listVendorContacts");
		}
	}
	
	@RequestMapping("/ListVendrContacts.html")
	public ModelAndView ListVendorContacts(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("ContactDetailsActionServlet.VendorContactList()");
        ArrayList VendorContactList = new ArrayList();
        VendorContactList = (ArrayList)db.getAllVendorDetails();
        String currentPageNo = (String)request.getParameter("currentPageNo");
        
        VendorContactList = doPagination(VendorContactList,currentPageNo);

        System.out.println("totalNofPages------------------>"+String.valueOf(totalNofPages));
        request.setAttribute("totalNofPages", String.valueOf(totalNofPages));
        System.out.println("currentPageNo------------------>"+currentPageNo);
	 	request.setAttribute("currentPageNo", currentPageNo);
        request.setAttribute("allVendorContactList",null);
        request.setAttribute("allVendorContactList",VendorContactList);
		return new ModelAndView("requirements/listVendorContacts");
	}
	
	@RequestMapping("/ViewVendrContactsByUniqueId.html")
	public ModelAndView ViewVendorContactsByUniqueId(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("ContactDetailsActionServlet.ViewListVendorContactsByUniqueId()");
		String VendorContactByUniqueId = null;
		VendorContactByUniqueId = request.getParameter("uniqueId");
        ArrayList VendorContactList = new ArrayList();
        VendorContactList = (ArrayList)db.getVendorContactByUniqueId(VendorContactByUniqueId);

        request.setAttribute("VendorContactList",null);
        request.setAttribute("VendorContactList",VendorContactList);
		return new ModelAndView("requirements/viewVendorContact");
	}

/*----------------------Vendor Contact Action Start here----------------------------------*/
	
	@RequestMapping("/initImportXLSClientData.html")
	public ModelAndView initImportXLSDataToClientContact(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		
		return new ModelAndView("requirements/ImportXLSDataToClient");
	}
	
	@RequestMapping("/importXLSDataToClientContact.html")
	public ModelAndView importXLSDataToClientContact(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		
		Properties config_property = new Properties();
		config_inputStream = getClass().getClassLoader().getResourceAsStream(config_prop);

		if (config_inputStream != null) {
			config_property.load(config_inputStream);
		} else {
			throw new FileNotFoundException("property file '" + config_prop + "' not found in the classpath");
		}
		
		
		ArrayList ClientContactList = new ArrayList();
		GeneralDBAction db=new GeneralDBAction();
		
		
		      MultipartParser parser = new MultipartParser(request, 1024 * 1024 * 1024);  /* file limit size of 1GB*/
	            Part _part;
	            int k=0;
	            while ((_part = parser.readNextPart()) != null) {
	            	
	                if(_part.isFile()){
	                	
	                	 FilePart fPart = (FilePart) _part;  // get some info about the file
		                    String name = fPart.getFileName();
//		                    fileList = (List) fPart;
		                    //InputStream in = fPart.getInputStream();
		                    
		             
		                    
		          if (name != null) {
		        	  String saveDirectory = config_property.getProperty("config.clientContactsPath");
		        	  long fileSize = fPart.writeTo(new File(saveDirectory));
		        	  
					//aFile.transferTo(new File(saveDirectory + aFile.getOriginalFilename()));
					ArrayList listData = null;
					 FileInputStream inputStream = new FileInputStream(new File(saveDirectory+ name));
					 Workbook workbook = getWorkbook(inputStream, saveDirectory+ name);
				        //Workbook workbook = new XSSFWorkbook(inputStream);
				        Sheet firstSheet = workbook.getSheetAt(0);
				        Iterator<Row> iterator = firstSheet.iterator();
				         
				        while (iterator.hasNext()) {
				            Row nextRow = iterator.next();
				            if(nextRow.getRowNum()==0 ){
				            	   continue; 
				            	  }
				            Iterator<Cell> cellIterator = nextRow.cellIterator();
				            int i = 0;
				            listData = new ArrayList();
				            while (cellIterator.hasNext()) {
				            	
				            	
				                Cell cell = cellIterator.next();
				                
				                 
				                switch (cell.getCellType()) {
				                    case Cell.CELL_TYPE_STRING:
				                        //System.out.print(cell.getStringCellValue());
				                        listData.add(cell.getStringCellValue());
				                        break;
//				                    case Cell.CELL_TYPE_BOOLEAN:
//				                        System.out.print(cell.getBooleanCellValue());
//				                        break;
				                    case Cell.CELL_TYPE_NUMERIC:
				                        //System.out.print(cell.getNumericCellValue());
				                        listData.add(cell.getNumericCellValue());
				                        break;
				                }
				                //System.out.print(" - ");
				                
				                
				                i++;
				            }
				            sqlStmt(listData);
				            System.out.println();
				            boolean importStatus = false;
				            if(listData.size()>0){
				            importStatus = db.importClientContactByXLS(listData);
				            }
				            
				            
				            
				        }
				         
				        workbook.close();
				        inputStream.close();
				}
			}
		}
	            request.setAttribute("allClientContactList",null);
	            ClientContactList = (ArrayList)db.getAllClientDetails();
                request.setAttribute("allClientContactList",ClientContactList);
		return new ModelAndView("requirements/listClientContacts");
	}
	
	
	@RequestMapping("/initImportXLSVendrData.html")
	public ModelAndView initImportXLSVendorData(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		
		return new ModelAndView("requirements/ImportXLSDataToVendor");
	}
	
	
	@RequestMapping("/importXLSDataToVendrContact.html")
	public ModelAndView importXLSDataToVendorContact(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		
		Properties config_property = new Properties();
		config_inputStream = getClass().getClassLoader().getResourceAsStream(config_prop);

		if (config_inputStream != null) {
			config_property.load(config_inputStream);
		} else {
			throw new FileNotFoundException("property file '" + config_prop + "' not found in the classpath");
		}
		
		
		ArrayList VendorContactList = new ArrayList();
		GeneralDBAction db=new GeneralDBAction();
		
		
		      MultipartParser parser = new MultipartParser(request, 1024 * 1024 * 1024);  /* file limit size of 1GB*/
	            Part _part;
	            int k=0;
	            while ((_part = parser.readNextPart()) != null) {
	            	
	                if(_part.isFile()){
	                	
	                	 FilePart fPart = (FilePart) _part;  // get some info about the file
		                    String name = fPart.getFileName();
//		                    fileList = (List) fPart;
		                    //InputStream in = fPart.getInputStream();
		                    
		             
		                    
		          if (name != null) {
		        	  String saveDirectory = config_property.getProperty("config.vendorContactsPath");
		        	  long fileSize = fPart.writeTo(new File(saveDirectory));
		        	  
					//aFile.transferTo(new File(saveDirectory + aFile.getOriginalFilename()));
					ArrayList listData = null;
					 FileInputStream inputStream = new FileInputStream(new File(saveDirectory+ name));
					 Workbook workbook = getWorkbook(inputStream, saveDirectory+ name);
				        //Workbook workbook = new XSSFWorkbook(inputStream);
				        Sheet firstSheet = workbook.getSheetAt(0);
				        Iterator<Row> iterator = firstSheet.iterator();
				         
				        while (iterator.hasNext()) {
				            Row nextRow = iterator.next();
				            if(nextRow.getRowNum()==0 ){
				            	   continue; 
				            	  }
				            Iterator<Cell> cellIterator = nextRow.cellIterator();
				            int i = 0;
				            listData = new ArrayList();
				            while (cellIterator.hasNext()) {
				            	
				            	
				                Cell cell = cellIterator.next();
				                
				                 
				                switch (cell.getCellType()) {
				                    case Cell.CELL_TYPE_STRING:
				                        //System.out.print(cell.getStringCellValue());
				                        listData.add(cell.getStringCellValue());
				                        break;
//				                    case Cell.CELL_TYPE_BOOLEAN:
//				                        System.out.print(cell.getBooleanCellValue());
//				                        break;
				                    case Cell.CELL_TYPE_NUMERIC:
				                        //System.out.print(cell.getNumericCellValue());
				                        listData.add(cell.getNumericCellValue());
				                        break;
				                }
				                //System.out.print(" - ");
				                
				                
				                i++;
				            }
				            sqlStmt(listData);
				            System.out.println();
				            boolean importStatus = false;
				            if(listData.size()>0){
				            importStatus = db.importVendorContactByXLS(listData);
				            }
				            
				            
				            
				        }
				         
				        workbook.close();
				        inputStream.close();
				}
			}
		}
	            request.setAttribute("allVendorContactList",null);
	            VendorContactList = (ArrayList)db.getAllVendorDetails();
                request.setAttribute("allVendorContactList",VendorContactList);
		return new ModelAndView("requirements/listVendorContacts");
	}
	
	@RequestMapping("/DeleteClientContactsByUniqueId.html")
	public ModelAndView DeleteClientContactsByUniqueId(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("ContactDetailsActionServlet.DeleteClientContactsByUniqueId()");
		String clientContactByUniqueId = null;
		clientContactByUniqueId = request.getParameter("uniqueId");
        ArrayList ClientContactList = new ArrayList();
       String clientContactDeleteStatus = db.DeleteClientContactByUniqueId(clientContactByUniqueId);

        ClientContactList = (ArrayList)db.getAllClientDetails();

        request.setAttribute("allClientContactList",null);
        request.setAttribute("allClientContactList",ClientContactList);
		return new ModelAndView("requirements/listClientContacts");
	}
	
	@RequestMapping("/EditClientContactsByUniqueId.html")
	public ModelAndView EditClientContactsByUniqueId(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("ContactDetailsActionServlet.EditClientContactsByUniqueId()");
		String clientContactByUniqueId = null;
		clientContactByUniqueId = request.getParameter("uniqueId");
        ArrayList ClientContactList = new ArrayList();
        ClientContactList = (ArrayList)db.getClientContactByUniqueId(clientContactByUniqueId);

        request.setAttribute("ClientContactList",null);
        request.setAttribute("ClientContactList",ClientContactList);
		return new ModelAndView("requirements/EditClientContact");
	}
	
	@RequestMapping("/UpdateClientContacts.html")
	public ModelAndView UpdateClientContacts(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		
		ClientContactDetailsBean objClientContacts = new ClientContactDetailsBean();
		GeneralDBAction db=new GeneralDBAction();
		
		String client_id = null;
		String client_name =  null;
		String responsible_person = null;
		String client_email = null;
		String client_company = null;
		//String client_jobTitle = null;
		String client_businessPhone = null;
		//String client_contactId = null;
		String unique_id=null;
		
//		client_id = "test";
//		client_name = "test";
//		responsible_person = "test";
//		client_email = "test";
//		client_company = "test";
//		client_jobTitle = "test";
//		client_businessPhone = "test";
//		client_contactId = "test";
		
		client_id = request.getParameter("client_id");
		client_name = request.getParameter("client_name");
		responsible_person = request.getParameter("responsible_person");
		client_email = request.getParameter("client_email");
		client_company = request.getParameter("client_company");
		//client_jobTitle = request.getParameter("client_jobTitle");
		client_businessPhone = request.getParameter("client_businessPhone");
		//client_contactId = request.getParameter("client_contactId");
	     unique_id= request.getParameter("unique_id");

		
		System.out.println("client_id--------------->"+client_id);
		System.out.println("client_name--------------->"+client_name);
		System.out.println("responsible_person--------------->"+responsible_person);
		System.out.println("client_email--------------->"+client_email);
		System.out.println("client_company--------------->"+client_company);
		//System.out.println("client_jobTitle--------------->"+client_jobTitle);
		System.out.println("client_businessPhone--------------->"+client_businessPhone);
		//System.out.println("client_contactId--------------->"+client_contactId);
		
		
		objClientContacts.setclient_id(client_id);
		objClientContacts.setclient_name(client_name);
		objClientContacts.setresponsible_person(responsible_person);
		objClientContacts.setclient_email(client_email);
		objClientContacts.setclient_company(client_company);
		//objClientContacts.setclient_jobTitle(client_jobTitle);
		objClientContacts.setclient_businessPhone(client_businessPhone);
		//objClientContacts.setclient_contactId(client_contactId);
		//objClientContacts.setunique_id(unique_id);
		

		
		
		boolean updateClientContactStatus = false;
		updateClientContactStatus = db.UpdateClientContact(objClientContacts,unique_id);
		if(updateClientContactStatus = true){
			System.out.println("Update Client Contacts was successfully comes from Servlet------------>"+updateClientContactStatus);
			Debug.print("ContactDetailsActionServlet.clientContactList()");
	        ArrayList ClientContactList = new ArrayList();
	        ClientContactList = (ArrayList)db.getAllClientDetails();

	        request.setAttribute("allClientContactList",null);
	        request.setAttribute("allClientContactList",ClientContactList);
			return new ModelAndView("requirements/listClientContacts");
		}else{
			System.out.println("Update Job Post was not successfully comes from Servlet------------>"+updateClientContactStatus);
			Debug.print("ContactDetailsActionServlet.clientContactList()");
	        ArrayList ClientContactList = new ArrayList();
	        ClientContactList = (ArrayList)db.getAllClientDetails();

	        request.setAttribute("allClientContactList",null);
	        request.setAttribute("allClientContactList",ClientContactList);
			return new ModelAndView("requirements/listClientContacts");
		}
	}
	
	@RequestMapping("/EditVendorContactsByUniqueId.html")
	public ModelAndView EditVendorContactsByUniqueId(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("ContactDetailsActionServlet.EditVendorContactsByUniqueId()");
		String VendorContactByUniqueId = null;
		VendorContactByUniqueId = request.getParameter("uniqueId");
        ArrayList ClientContactList = new ArrayList();
        ClientContactList =(ArrayList)db.getVendorContactByUniqueId(VendorContactByUniqueId);

        request.setAttribute("ClientContactList",null);
        request.setAttribute("ClientContactList",ClientContactList);
		return new ModelAndView("requirements/EditVendorContact");
	}
	
	@RequestMapping("/UpdateVendorContacts.html")
	public ModelAndView UpdateVendorContacts(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		
		VendorContactDetailsBean objVendorContacts = new VendorContactDetailsBean();
		GeneralDBAction db=new GeneralDBAction();
		
		String vendor_id = null;
		String vendor_fname =  null;
		String vendor_lname = null;
		String vendor_email = null;
		String vendor_company = null;
		String vendor_homePhone = null;
		String vendor_businessPhone = null;
		String vendor_contactPerson = null;
		String unique_id=null;
		
//		vendor_id = "test";
//		vendor_fname = "test";
//		vendor_lname = "test";
//		vendor_email = "test";
//		vendor_company = "test";
//		vendor_homePhone = "test";
//		vendor_businessPhone = "test";
//		vendor_contactPerson = "test";
		
		vendor_id = request.getParameter("vendor_id");
		vendor_fname = request.getParameter("vendor_fname");
		vendor_lname = request.getParameter("vendor_lname");
		vendor_email = request.getParameter("vendor_email");
		vendor_company = request.getParameter("vendor_company");
		vendor_homePhone = request.getParameter("vendor_homePhone");
		vendor_businessPhone = request.getParameter("vendor_businessPhone");
		vendor_contactPerson = request.getParameter("vendor_contactPerson");
		unique_id= request.getParameter("unique_id");
		
		System.out.println("vendor_id--------------->"+vendor_id);
		System.out.println("vendor_fname--------------->"+vendor_fname);
		System.out.println("vendor_lname--------------->"+vendor_lname);
		System.out.println("vendor_email--------------->"+vendor_email);
		System.out.println("vendor_company--------------->"+vendor_company);
		System.out.println("vendor_homePhone--------------->"+vendor_homePhone);
		System.out.println("vendor_businessPhone--------------->"+vendor_businessPhone);
		System.out.println("vendor_contactPerson--------------->"+vendor_contactPerson);
		
		
		objVendorContacts.setvendor_id(vendor_id);
		objVendorContacts.setvendor_fname(vendor_fname);
		objVendorContacts.setvendor_lname(vendor_lname);
		objVendorContacts.setvendor_email(vendor_email);
		objVendorContacts.setvendor_company(vendor_company);
		objVendorContacts.setvendor_homePhone(vendor_homePhone);
		objVendorContacts.setvendor_businessPhone(vendor_businessPhone);
		objVendorContacts.setvendor_contactPerson(vendor_contactPerson);
		
		boolean insertVendorContactStatus = false;
		insertVendorContactStatus = db.UpdateVendorContact(objVendorContacts,unique_id);
		if(insertVendorContactStatus = true){
			System.out.println("update Vendor Contacts was successfully comes from Servlet------------>"+insertVendorContactStatus);
			Debug.print("ContactDetailsActionServlet.vendorContactList()");
	        ArrayList VendorContactList = new ArrayList();
	        VendorContactList = (ArrayList)db.getAllVendorDetails();

	        request.setAttribute("allVendorContactList",null);
	        request.setAttribute("allVendorContactList",VendorContactList);
			return new ModelAndView("requirements/listVendorContacts");
		}else{
			System.out.println("update Job Post was not successfully comes from Servlet------------>"+insertVendorContactStatus);
			Debug.print("ContactDetailsActionServlet.vendorContactList()");
	        ArrayList VendorContactList = new ArrayList();
	        VendorContactList = (ArrayList)db.getAllVendorDetails();

	        request.setAttribute("allVendorContactList",null);
	        request.setAttribute("allVendorContactList",VendorContactList);
			return new ModelAndView("requirements/listVendorContacts");
		}
	}
	
	@RequestMapping("/DeleteVendorContactsByUniqueId.html")
	public ModelAndView DeleteVendorContactsByUniqueId(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException{
		
		HttpSession session=request.getSession(true); 
		GeneralDBAction db=new GeneralDBAction();
		
		Debug.print("ContactDetailsActionServlet.DeleteVendorContactsByUniqueId()");
		String vendorContactByUniqueId = null;
		vendorContactByUniqueId = request.getParameter("uniqueId");
        ArrayList VendorContactList = new ArrayList();
       String clientContactDeleteStatus = db.DeleteVendorContactByUniqueId(vendorContactByUniqueId);

       VendorContactList = (ArrayList)db.getAllVendorDetails();

        request.setAttribute("allVendorContactList",null);
        request.setAttribute("allVendorContactList",VendorContactList);
		return new ModelAndView("requirements/listVendorContacts");
	}
	
	private static Workbook getWorkbook(FileInputStream inputStream, String excelFilePath)
	        throws IOException {
	    Workbook workbook = null;
	 
	    if (excelFilePath.endsWith("xlsx")) {
	        workbook = new XSSFWorkbook(inputStream);
	    } else if (excelFilePath.endsWith("xls")) {
	        workbook = (Workbook) new HSSFWorkbook(inputStream);
	    } else {
	        throw new IllegalArgumentException("The specified file is not Excel file");
	    }
	 
	    return workbook;
	}
	
	private static void sqlStmt(ArrayList listData) {

		System.out.println("size----------"+listData.size());
		for (int i = 0; i < listData.size(); i++) {
			System.out.println("array list data ---- "+i+"---------"+listData.get(i));
		}
	}
	public ArrayList doPagination(ArrayList postReqList,String page){
		Map<Integer, ArrayList> map = new HashMap<Integer, ArrayList>();
     System.out.println("checksize-----"+postReqList.size());
	//  ArrayList req_mail = new ArrayList();
    
  	 
  	  
	  ArrayList al = new ArrayList();
	  int i = 1;
	  int j = 15;
	  //int count = 1;
	  totalNofPages = 1;
	  
	  Iterator itr1 = postReqList.iterator();
	  while(itr1.hasNext()){
	   String[] tempI =  (String[]) itr1.next();
	 
	   al.add(tempI);
	   
	   if(i == j){    
	    map.put(totalNofPages, al);
	    j=j+15;
	    totalNofPages = totalNofPages+1;
	    al = new ArrayList();
	    
	   }
	   i++; 
	  }
	  System.out.println("al size --- "+al.size());
	  System.out.println("map size --- "+map.size());
	 
	  
	  int key = 0;
	int pagenumber=1;
	 
	
	if(page!=null){
 pagenumber = Integer.parseInt(page);
	}
	 
	 
	  
	  Iterator<Map.Entry<Integer, ArrayList>> entries = map.entrySet().iterator();
	  while (entries.hasNext()) {
	      Map.Entry<Integer, ArrayList> entry = entries.next();
	      
	      key = entry.getKey();
	    
	  
	      if(pagenumber == key){
	       al = entry.getValue();
	      }
	     
	  }
	   System.out.println("al "+al.size());
	   
	   System.out.println("totalNofPages in doPagination Method------------------>"+totalNofPages);
	   
	  // Iterator itrr = al.iterator();
	//   while (itrr.hasNext()) {
	 //  System.out.println("itr "+itrr.next());
	//  }
	  //String [] array = (String[]) res.toArray();
	  
	  //System.out.println(array);
	  
  return al;
	}
	
}

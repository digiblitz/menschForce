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
package com.resume.search;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.css.sac.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.google.gson.Gson;
import com.solr.session.SolrSessionBean;


public class ResumeSearch implements Controller{
static Logger log = Logger.getLogger(ResumeSearch.class.getName());
	
	public static final String config_prop = "config.properties";
	public static final String LOG_FILE = "Log4j.properties";
	InputStream is;
	InputStream config_inputStream;
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,ProtocolException,
			IOException, ParserConfigurationException, SAXException {
		
		 //config properties
	       Properties config_property = new Properties();
	       ResumeSearch details=new ResumeSearch();
		     
	       config_inputStream = getClass().getClassLoader().getResourceAsStream(config_prop);

			if (config_inputStream != null) {
				config_property.load(config_inputStream);
			} else {
				throw new FileNotFoundException("property file '" + config_prop + "' not found in the classpath");
			}
		
		Properties logProp = new Properties();  
	      is = (InputStream) getClass().getClassLoader().getResourceAsStream(LOG_FILE);
	      logProp.load(is); 
	      PropertyConfigurator.configure(logProp);      
	      log.info("Logging enabled");  
		
		HttpSession session=request.getSession(true); 
		  long creationTime = session.getCreationTime();
	        String sessionId = session.getId();
	        long lastAccessedTime = session.getLastAccessedTime();
	        Date createDate= new Date(creationTime);
	        Date lastlogin= new Date(lastAccessedTime);
	        System.out.println("lastAccessedDate"+lastlogin); 
	        System.out.println("createDate"+createDate); 
	        
	        SolrSessionBean solrBeanObj = new SolrSessionBean();
		
	        String cmdProcess = request.getParameter("cmd");
		
		log.info("cmdProcess "+cmdProcess);
		if(cmdProcess != null && cmdProcess.equals("initSearchResume")){
			
			log.info("inside init resume search");
			
			 return new ModelAndView("resumesearch");  
		}else if(cmdProcess != null && cmdProcess.equals("getResumes")){
			
			String requestData = request.getParameter("requestData");
			log.info("requestData "+requestData);
			String json = null;
			StringBuffer documentResponse = null;
			String solrUrl = null;
			String url = null;
			try{
				solrUrl = config_property.getProperty("config.solrUrl");
				log.info("solrUrl "+solrUrl);
				if(requestData != null && solrUrl != null){
					url = solrUrl+requestData+"&wt=json";
					log.info(" url "+url);
					documentResponse = solrBeanObj.getDocumentsSolr(url);
					System.out.println("check..."+documentResponse);
				}
				
				log.info("documentResponse "+documentResponse);
				//details.check();
				json = new Gson().toJson(documentResponse);					 
	            response.setContentType("application/json");		             
	            response.getWriter().write(json);
	            
			}catch(Exception e){
				e.printStackTrace();
			}			
		}else if(cmdProcess != null && cmdProcess.equalsIgnoreCase("initResumeDownload")){
		
			String resumePath = request.getParameter("resumePath");
			System.out.println("resumePath "+resumePath);
			String resumeFinalPath = resumePath.substring(2);
			System.out.println("resumeFialPath "+resumeFinalPath);
			
			final int BUFFER_SIZE = 2 * 1024 * 1024;
					
			
			String user = config_property.getProperty("config.dbuserName");
			String pass = config_property.getProperty("config.dbpassword");		 
	        String host = config_property.getProperty("config.dbhost");	       
	        
	        System.out.println("user --> "+user+" pass --> "+pass+" host --> "+host);
	        
	        
            
			  try{
				  
									      
		        String filePath = resumeFinalPath;
		        
		        String strarr[] = filePath.split("/");
		        String fileName = strarr[strarr.length-1];
		        
		        //ftp url format- "ftp://URLEncoder.encode(username):URLEncoder.encode(password)@host:port/filepath"
		        String ftpUrl = "ftp://"+URLEncoder.encode(user)+":"+URLEncoder.encode(pass)+"@"+host+":21/"+filePath ;
				System.out.println("ftpUrl "+ftpUrl);
				URL url = new URL(ftpUrl);
				URLConnection conn =  url.openConnection();
				InputStream inputStream = conn.getInputStream() ;
			
				//=============================================================//
				
			  // get absolute path of the application
			     ServletContext context = request.getSession().getServletContext();
			   
			     // construct the complete absolute path of the file			     
			     String fullPath = filePath;      
			     File downloadFile = new File(fullPath);
			     
			     // get MIME type of the file
			     String mimeType = context.getMimeType(fullPath);
			        if (mimeType == null) {
			            // set to binary type if MIME mapping not found
			            mimeType = "application/octet-stream";
			        }
			      
			        // set content attributes for the response
			        response.setContentType(mimeType);
			     
			        // set headers for the response
			        String headerKey = "Content-Disposition";
			        String headerValue = String.format("attachment; filename=\"%s\"",
			                downloadFile.getName());
			        response.setHeader(headerKey, headerValue);
			
			        // get output stream of the response						
			        OutputStream outStream = response.getOutputStream();
			
			       
			        ByteArrayOutputStream out = new ByteArrayOutputStream();
		        
		        byte[] bytes = new byte[1024];
		        int count;
		        while ((count = inputStream.read(bytes)) > 0) {
		            out.write(bytes, 0, count);
		        }
		        
		        response.setContentLength(out.size());
		        //System.out.println("out.size() "+out.size());
		        out.writeTo(outStream);
		    
		        outStream.flush();
			    inputStream.close();
			    outStream.close();
			    
			 System.out.println("success");
			 
			  }catch(Exception e){
				  e.printStackTrace();
			  }
			 return null;//new ModelAndView("approvenroll");
		}		
		
		
		
		//return null;
	//}
	//public void check()
	 //{
		if(cmdProcess != null && cmdProcess.equalsIgnoreCase("initResumeDetail")){
			
			String resumeName = request.getParameter("resumeName");
			try {

				URL url = new URL("https://www.digiblitzonline.com/sp-portal/dBConsult/digiblitz/_vti_bin/ListData.svc/ReceivedResumes?$filter=Name%20eq%20%27"+resumeName+"%27");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/atom+xml");
				conn.setRequestProperty("Authorization", "Basic c3VyZXNoOkdvbGQxMjM=");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}
				System.out.println("check....");

				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
				
			
				/*String output;
			System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);	}
				*/
				
				DocumentBuilderFactory dbFactory 
	            = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(conn.getInputStream());
//	         Document doc1 = dBuilder.parse(fis);
	         doc.getDocumentElement().normalize();
	         System.out.println("Root element :" 
	            + doc.getDocumentElement().getNodeName());
	         NodeList nList = doc.getElementsByTagName("entry");
	         System.out.println("----------------------------");
	         String download=null;
	         String name=null;
	         String reqid=null;
	         String Fname=null;
	         String Lname=null;
	         String Mname=null;
	         String Con_num=null;
	         String Can_mail=null;
	         String Sub_Id=null;
	         String Dob=null;
	         String Tot_Exp=null;
	         String empname=null;
	         String Emp_CN=null;
	         String Emp_maail=null;
	         String Con_per=null;
	         String Current_loc=null;
	         String bywhom_id=null;
	         String pref_loc=null;
	         String Rate=null;
	         String avail=null;
	         String visa_type=null;
	         String Visa_app=null;
	         String Form=null;
	         String Form_94=null;
	         String Time=null;
	         String Exp=null;
	         String skill=null;
	         String Relo=null;
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :" 
	               + nNode.getNodeName());
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               System.out.println("inside loop........");
	             
	               download=eElement .getElementsByTagName("content")
			                  .item(0)
			                   .getAttributes().getNamedItem("src").getNodeValue();
		               System.out.println("Document : " 
		                  + download);
	            
	               name=eElement
		                  .getElementsByTagName("d:Name")
		                  .item(0)
		                  .getTextContent();
	               System.out.println("Name : " 
	                  + name);
	                reqid =eElement
	 	                  .getElementsByTagName("d:RequirementIDId")
		                  .item(0)
		                  .getTextContent();
	               System.out.println(" RequirementId: " 
	               + reqid);
	                Fname=eElement
		 	                  .getElementsByTagName("d:FirstName")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("FirstName : " 
	 	                  + Fname);
	                Lname=eElement
		 	                  .getElementsByTagName("d:LastName")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("LastName : " 
	 	                  + Lname);
	                Mname= eElement
		 	                  .getElementsByTagName("d:MiddleName")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("MiddleName : " 
	 	                  +Mname);
	               Con_num=eElement
		 	                  .getElementsByTagName("d:CandidateContactNumber")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("CandidateContactNumber : " 
	 	                  + Con_num);
	               
	                Can_mail=eElement
		 	                  .getElementsByTagName("d:CandidateEMail")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("CandidateEMail : " 
	 	                  + Can_mail);
	                Sub_Id= eElement
		 	                  .getElementsByTagName("d:SubmittedById")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("SubmittedById : " 
	            		   +Sub_Id);
	                Dob= eElement
		 	                  .getElementsByTagName("d:DateOfBirth")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("DateOfBirth : " 
	 	                  +Dob);
	                Tot_Exp=eElement
		 	                  .getElementsByTagName("d:TotalExperience")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("TotalExperience : " 
	 	                  +Tot_Exp); 
	                empname=eElement
		 	                  .getElementsByTagName("d:EmployerName")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("EmployerName : " 
	 	                  + empname);
	               Emp_CN= eElement
	 	                  .getElementsByTagName("d:EmployerContactNumber")
	 	                  .item(0)
	 	                  .getTextContent();
	               System.out.println("EmployerContactNumber : " 
	 	                  +Emp_CN);
	                Emp_maail= eElement
		 	                  .getElementsByTagName("d:EmployerMailID")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("EmployerMailID : " 
	 	                  +Emp_maail);
	                Con_per= eElement
		 	                  .getElementsByTagName("d:ContactPerson")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("ContactPerson : " 
	 	                  +Con_per);
	                Current_loc=eElement
		 	                  .getElementsByTagName("d:CurrentLocation")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("CurrentLocation : " 
	 	                  + Current_loc);
	                bywhom_id= eElement
		 	                  .getElementsByTagName("d:ByWhomValue")
		 	                  .item(0)
		 	                  .getTextContent();
	               
	               System.out.println("ByWhomValue : " 
	 	                  +bywhom_id);
	                pref_loc= eElement
		 	                  .getElementsByTagName("d:PreferredLocation")
		 	                  .item(0)
		 	                  .getTextContent();
	               
	               System.out.println("PreferredLocation : " 
		 	                  + pref_loc);
	                Rate= eElement
		 	                  .getElementsByTagName("d:RateInDollar")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("RateInDollar : " 
		 	                  + Rate);
	                avail=eElement
		 	                  .getElementsByTagName("d:Availability")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("Availability : " 
		 	                  + avail);
	                visa_type= eElement
		 	                  .getElementsByTagName("d:VisaTypeValue")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("VisaTypeValue : " 
		 	                  +visa_type);
	                Visa_app=eElement
		 	                  .getElementsByTagName("d:VisaApprovalValue")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("VisaApprovalValue : " 
		 	                  + Visa_app);
	                Form= eElement
		 	                  .getElementsByTagName("d:FormI797AvailableValue")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("FormI797AvailableValue : " 
		 	                  +Form);
	                Form_94= eElement
		 	                  .getElementsByTagName("d:FormI94AvailableValue")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("FormI94AvailableValue : " 
		 	                  +Form_94);
	                Time=eElement
		 	                  .getElementsByTagName("d:BestTimeForTelephonicInterview")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("BestTimeForTelephonicInterview : " 
		 	                  + Time);
	                Exp=eElement
		 	                  .getElementsByTagName("d:ExperienceInUSA")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("ExperienceInUSA : " 
		 	                  + Exp);
	                skill= eElement
		 	                  .getElementsByTagName("d:Skills")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("Skills : " 
		 	                  +skill);
	                Relo=eElement
		 	                  .getElementsByTagName("d:ReLocationValue")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("ReLocationValue : " 
		 	                  + Relo);
	               
	            }
	            request.setAttribute("name",name);
	            request.setAttribute("reqid",reqid);
	            request.setAttribute("Fname",Fname);
	            request.setAttribute("Lname",Lname);
	            request.setAttribute("Mname",Mname);
	            request.setAttribute("Con_num",Con_num);
	            request.setAttribute("Can_mail",Can_mail);
	            request.setAttribute("Sub_Id",Sub_Id);
	            request.setAttribute("Dob",Dob);
	            request.setAttribute("Tot_Exp",Tot_Exp);
	            request.setAttribute("empname",empname);
	            request.setAttribute("Emp_CN",Emp_CN);
	            request.setAttribute("Emp_maail",Emp_maail);
	            request.setAttribute("Con_per",Con_per);
	            request.setAttribute("Current_loc",Current_loc);
	            request.setAttribute("bywhom_id",bywhom_id);
	            request.setAttribute("pref_loc",pref_loc);
	            request.setAttribute("Rate",Rate);
	            request.setAttribute("avail",avail);
	            request.setAttribute("visa_type",visa_type);
	            request.setAttribute("Visa_app",Visa_app);

	            request.setAttribute("Form",Form);
	            request.setAttribute("Form_94",Form_94);
	            request.setAttribute("Time",Time);
	            request.setAttribute("Exp",Exp);
	            request.setAttribute("skill",skill);
	            request.setAttribute("Relo",Relo);
	            request.setAttribute("download",download);
	            return new ModelAndView("ResumeDetail");
	         }
			
			
				
				

				conn.disconnect();

			  } catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (IOException e) {

				e.printStackTrace();

			  }
		}

		else if(cmdProcess != null && cmdProcess.equalsIgnoreCase("initPostRequirement")){
	
	
	try {

		URL url = new URL("https://www.digiblitzonline.com/sp-portal/dBConsult/digiblitz/_vti_bin/ListData.svc/JobVacancy?");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/atom+xml");
		conn.setRequestProperty("Authorization", "Basic c3VyZXNoOkdvbGQxMjM=");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
		System.out.println("check....");

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
		
	
		/*String output;
	System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);	}
		*/
		
		DocumentBuilderFactory dbFactory 
        = DocumentBuilderFactory.newInstance();
     DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
     Document doc = dBuilder.parse(conn.getInputStream());

     doc.getDocumentElement().normalize();
     System.out.println("Root element :" 
        + doc.getDocumentElement().getNodeName());
     NodeList nList = doc.getElementsByTagName("entry");
     System.out.println("----------------------------");
   
     String Reqid=null;
     String Industry=null;
     String Interview_process=null;
     String VisasAccepted=null;
     String Position=null;
     String NumberOfOpenings=null;
     String JobTypeValue=null;
     String RequirementStatusValue=null;
     String DigiBlitzRate=null;
     String StartDate=null;
     String LocationField=null;
     String db_recruiter=null;
     
     String ContentTypeID=null;
     String ClientReferenceID= null;
     String ClientResponsiblePerson=null;
     String PositionID=null;
     String ExtraDocumentsRequired=null;
     String Skills=null;
    String Responsiblities=null;
    String ConsultantInfo=null;
    String EndDate=null;
    String ClientRate=null;
    String Duration=null;
    String LocalRequired=null;
    String FlexabilityOnSalary=null;
    String FlexabilityOnHrlyRate = null;
    String DateOnHold=null;
    String Notes=null;
    String Salary=null;
    String RequiredExperience=null;
    String Modified=null;
    String Created=null;
    String id=null;
    String DBRequirementID = null;
     ArrayList list= new ArrayList();
   
     for (int temp = 0; temp < nList.getLength(); temp++) 
     {
    	
     
    	 System.out.println("list..."+nList.getLength());
        Node nNode = nList.item(temp);
        
        
        System.out.println("\nCurrent Element :" 
           + nNode.getNodeName());
        
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
        	
           Element eElement = (Element) nNode;
           System.out.println("inside loop........");
          
      
           id =eElement .getElementsByTagName("d:UniqueReference") .item(0).getTextContent();
        
           ContentTypeID =eElement .getElementsByTagName("d:ContentTypeID") .item(0).getTextContent();
          
           ClientReferenceID=eElement .getElementsByTagName("d:ClientReferenceID") .item(0).getTextContent();
           
           ClientResponsiblePerson=eElement .getElementsByTagName("d:ClientResponsiblePerson") .item(0).getTextContent();
           
           PositionID =eElement .getElementsByTagName("d:PositionID") .item(0).getTextContent();
           
           Skills =eElement .getElementsByTagName("d:Skills") .item(0).getTextContent();
           
           Responsiblities =eElement .getElementsByTagName("d:Responsiblities") .item(0).getTextContent();
           
           ConsultantInfo =eElement .getElementsByTagName("d:ConsultantInfo") .item(0).getTextContent();
           
           EndDate =eElement .getElementsByTagName("d:EndDate") .item(0).getTextContent();
           
           ClientRate =eElement .getElementsByTagName("d:ClientRate") .item(0).getTextContent();
           
           Duration =eElement .getElementsByTagName("d:Duration") .item(0).getTextContent();
           
           LocalRequired =eElement .getElementsByTagName("d:LocalRequired") .item(0).getTextContent();
           
           FlexabilityOnSalary =eElement .getElementsByTagName("d:FlexabilityOnSalary") .item(0).getTextContent();
           
           FlexabilityOnHrlyRate =eElement .getElementsByTagName("d:FlexabilityOnHrlyRate") .item(0).getTextContent();
           
           DateOnHold =eElement .getElementsByTagName("d:DateOnHold") .item(0).getTextContent();
           
           Notes =eElement .getElementsByTagName("d:Notes") .item(0).getTextContent();
           
           Salary =eElement .getElementsByTagName("d:Salary") .item(0).getTextContent();
           
           RequiredExperience =eElement .getElementsByTagName("d:RequiredExperience") .item(0).getTextContent();
           Created =eElement .getElementsByTagName("d:Created") .item(0).getTextContent();
           Modified =eElement .getElementsByTagName("d:Modified") .item(0).getTextContent();
           
           
           
           
           Reqid=eElement  .getElementsByTagName("d:DBRequirementID") .item(0) .getTextContent();
                  
          Industry =eElement .getElementsByTagName("d:ClientIndustry") .item(0).getTextContent();
	                 
          Interview_process=eElement.getElementsByTagName("d:InterviewProcess") .item(0)  .getTextContent();
 	                  
          VisasAccepted=eElement .getElementsByTagName("d:VisasAccepted") .item(0) .getTextContent();
 	                 
 	      Position= eElement.getElementsByTagName("d:Position").item(0) .getTextContent();
 	                  
 	     NumberOfOpenings=eElement .getElementsByTagName("d:NumberOfOpenings") .item(0)  .getTextContent();
 	                 
 	       JobTypeValue=eElement .getElementsByTagName("d:JobTypeValue") .item(0) .getTextContent();
 	                  
 	       RequirementStatusValue= eElement .getElementsByTagName("d:RequirementStatusValue").item(0) .getTextContent();

	       DigiBlitzRate= eElement.getElementsByTagName("d:DigiBlitzRate")  .item(0) .getTextContent();
 	                  
           StartDate=eElement .getElementsByTagName("d:StartDate")  .item(0) .getTextContent();
 	                 
 	       LocationField=eElement.getElementsByTagName("d:LocationField") .item(0) .getTextContent();
 	                  
 	       db_recruiter= eElement .getElementsByTagName("d:ClientResponsiblePerson") .item(0) .getTextContent();
	                 
	        ExtraDocumentsRequired= eElement.getElementsByTagName("d:ExtraDocumentsRequired") .item(0)  .getTextContent();
 	                  
	        DBRequirementID = eElement.getElementsByTagName("d:DBRequirementID") .item(0)  .getTextContent();
           
        }
        
        String tempstr[] = {ClientReferenceID,Industry,ClientResponsiblePerson,PositionID,Position,Interview_process,VisasAccepted,Skills,JobTypeValue,
        		LocationField,NumberOfOpenings,Responsiblities,RequiredExperience,ConsultantInfo,StartDate,EndDate,ClientRate,RequirementStatusValue,
        		DigiBlitzRate,Reqid,Duration,LocalRequired,Notes,Salary,FlexabilityOnSalary,FlexabilityOnHrlyRate,DateOnHold,ExtraDocumentsRequired,
        		Created,Modified,id,DBRequirementID};
        
        list.add(tempstr);
        
       // System.out.println("list.size "+list.size());
       
       // System.out.println("----"+Reqid);

     }
     
     
     request.setAttribute("list",list);

		conn.disconnect();

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }
	return new ModelAndView("PostRequirement");
}
		
		

		
		else if(cmdProcess != null && cmdProcess.equalsIgnoreCase("getPostDetailsByid")){
			String id = request.getParameter("id");
		
		try {

			URL url = new URL("https://www.digiblitzonline.com/sp-portal/dBConsult/digiblitz/_vti_bin/ListData.svc/JobVacancy%28"+id+"%29");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/atom+xml");
			conn.setRequestProperty("Authorization", "Basic c3VyZXNoOkdvbGQxMjM=");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			System.out.println("check....");

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			
		
			/*String output;
		System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);	}
			*/
			
			DocumentBuilderFactory dbFactory 
	        = DocumentBuilderFactory.newInstance();
	     DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	     Document doc = dBuilder.parse(conn.getInputStream());

	     doc.getDocumentElement().normalize();
	     System.out.println("Root element :" 
	        + doc.getDocumentElement().getNodeName());
	     NodeList nList = doc.getElementsByTagName("entry");
	     System.out.println("----------------------------");
	   
	     String Reqid=null;
	     String Industry=null;
	     String Interview_process=null;
	     String VisasAccepted=null;
	     String Position=null;
	     String NumberOfOpenings=null;
	     String JobTypeValue=null;
	     String RequirementStatusValue=null;
	     String DigiBlitzRate=null;
	     String StartDate=null;
	     String LocationField=null;
	     String db_recruiter=null;
	     
	     String ContentTypeID=null;
	     String ClientReferenceID= null;
	     String ClientResponsiblePerson=null;
	     String PositionID=null;
	     String ExtraDocumentsRequired=null;
	     String Skills=null;
	    String Responsiblities=null;
	    String ConsultantInfo=null;
	    String EndDate=null;
	    String ClientRate=null;
	    String Duration=null;
	    String LocalRequired=null;
	    String FlexabilityOnSalary=null;
	    String FlexabilityOnHrlyRate = null;
	    String DateOnHold=null;
	    String Notes=null;
	    String Salary=null;
	    String RequiredExperience=null;
	    String Modified=null;
	    String Created=null;
	   // String id=null;
	     ArrayList list= new ArrayList();
	   
	     for (int temp = 0; temp < nList.getLength(); temp++) 
	     {
	    	
	     
	    	 System.out.println("list..."+nList.getLength());
	        Node nNode = nList.item(temp);
	        
	        
	        System.out.println("\nCurrent Element :" 
	           + nNode.getNodeName());
	        
	        if (nNode.getNodeType() == Node.ELEMENT_NODE)
	        {
	        	
	           Element eElement = (Element) nNode;
	           System.out.println("inside loop........");
	          
	      
	           id =eElement .getElementsByTagName("d:UniqueReference") .item(0).getTextContent();
	        
	           ContentTypeID =eElement .getElementsByTagName("d:ContentTypeID") .item(0).getTextContent();
	          
	           ClientReferenceID=eElement .getElementsByTagName("d:ClientReferenceID") .item(0).getTextContent();
	           
	           ClientResponsiblePerson=eElement .getElementsByTagName("d:ClientResponsiblePerson") .item(0).getTextContent();
	           
	           PositionID =eElement .getElementsByTagName("d:PositionID") .item(0).getTextContent();
	           
	           Skills =eElement .getElementsByTagName("d:Skills") .item(0).getTextContent();
	           
	           Responsiblities =eElement .getElementsByTagName("d:Responsiblities") .item(0).getTextContent();
	           
	           ConsultantInfo =eElement .getElementsByTagName("d:ConsultantInfo") .item(0).getTextContent();
	           
	           EndDate =eElement .getElementsByTagName("d:EndDate") .item(0).getTextContent();
	           
	           ClientRate =eElement .getElementsByTagName("d:ClientRate") .item(0).getTextContent();
	           
	           Duration =eElement .getElementsByTagName("d:Duration") .item(0).getTextContent();
	           
	           LocalRequired =eElement .getElementsByTagName("d:LocalRequired") .item(0).getTextContent();
	           
	           FlexabilityOnSalary =eElement .getElementsByTagName("d:FlexabilityOnSalary") .item(0).getTextContent();
	           
	           FlexabilityOnHrlyRate =eElement .getElementsByTagName("d:FlexabilityOnHrlyRate") .item(0).getTextContent();
	           
	           DateOnHold =eElement .getElementsByTagName("d:DateOnHold") .item(0).getTextContent();
	           
	           Notes =eElement .getElementsByTagName("d:Notes") .item(0).getTextContent();
	           
	           Salary =eElement .getElementsByTagName("d:Salary") .item(0).getTextContent();
	           
	           RequiredExperience =eElement .getElementsByTagName("d:RequiredExperience") .item(0).getTextContent();
	           Created =eElement .getElementsByTagName("d:Created") .item(0).getTextContent();
	           Modified =eElement .getElementsByTagName("d:Modified") .item(0).getTextContent();
	           
	           
	           
	           
	           Reqid=eElement  .getElementsByTagName("d:DBRequirementID") .item(0) .getTextContent();
	                  
	          Industry =eElement .getElementsByTagName("d:ClientIndustry") .item(0).getTextContent();
		                 
	          Interview_process=eElement.getElementsByTagName("d:InterviewProcess") .item(0)  .getTextContent();
	 	                  
	          VisasAccepted=eElement .getElementsByTagName("d:VisasAccepted") .item(0) .getTextContent();
	 	                 
	 	      Position= eElement.getElementsByTagName("d:Position").item(0) .getTextContent();
	 	                  
	 	     NumberOfOpenings=eElement .getElementsByTagName("d:NumberOfOpenings") .item(0)  .getTextContent();
	 	                 
	 	       JobTypeValue=eElement .getElementsByTagName("d:JobTypeValue") .item(0) .getTextContent();
	 	                  
	 	       RequirementStatusValue= eElement .getElementsByTagName("d:RequirementStatusValue").item(0) .getTextContent();

		       DigiBlitzRate= eElement.getElementsByTagName("d:DigiBlitzRate")  .item(0) .getTextContent();
	 	                  
	           StartDate=eElement .getElementsByTagName("d:StartDate")  .item(0) .getTextContent();
	 	                 
	 	       LocationField=eElement.getElementsByTagName("d:LocationField") .item(0) .getTextContent();
	 	                  
	 	       db_recruiter= eElement .getElementsByTagName("d:ClientResponsiblePerson") .item(0) .getTextContent();
		                 
		        ExtraDocumentsRequired= eElement.getElementsByTagName("d:ExtraDocumentsRequired") .item(0)  .getTextContent();
	 	                  
	 	     
	           
	        }
	        
	        
	        request.setAttribute("ClientReferenceID",ClientReferenceID);
	        request.setAttribute("Industry",Industry);
	        request.setAttribute("ClientResponsiblePerson",ClientResponsiblePerson);
	        request.setAttribute("PositionID",PositionID);
	        request.setAttribute("Position",Position);
	        request.setAttribute("Interview_process",Interview_process);
	        request.setAttribute("VisasAccepted",VisasAccepted);
	        request.setAttribute("Skills",Skills);
	        request.setAttribute("JobTypeValue",JobTypeValue);
	        request.setAttribute("LocationField",LocationField);
	        request.setAttribute("NumberOfOpenings",NumberOfOpenings);
	        request.setAttribute("Responsiblities",Responsiblities);
	        request.setAttribute("RequiredExperience",RequiredExperience);
	        request.setAttribute("ConsultantInfo",ConsultantInfo);
	        request.setAttribute("StartDate",StartDate);
	        request.setAttribute("EndDate",EndDate);
	        request.setAttribute("ClientRate",ClientRate);
	        request.setAttribute("RequirementStatusValue",RequirementStatusValue);
	        request.setAttribute("DigiBlitzRate",DigiBlitzRate);
	        request.setAttribute("Reqid",Reqid);
	        request.setAttribute("Duration",Duration);
	        request.setAttribute("LocalRequired",LocalRequired);
	        request.setAttribute("Notes",Notes);
	        request.setAttribute("Salary",Salary);
	        request.setAttribute("FlexabilityOnSalary",FlexabilityOnSalary);
	        request.setAttribute("FlexabilityOnHrlyRate",FlexabilityOnHrlyRate);
	        request.setAttribute("DateOnHold",DateOnHold);
	        request.setAttribute("ExtraDocumentsRequired",ExtraDocumentsRequired);
	        request.setAttribute("id",id);
	       
	        
	        
	        
	       // System.out.println("list.size "+list.size());
	       
	       // System.out.println("----"+Reqid);

	     }
	     
	     
	   

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		return new ModelAndView("RequirementDetails");
		}
		
		else if(cmdProcess != null && cmdProcess.equalsIgnoreCase("initReceivedResumes")){
			
			String resumeName = request.getParameter("resumeName");
			try {

				URL url = new URL("https://www.digiblitzonline.com/sp-portal/dBConsult/digiblitz/_vti_bin/ListData.svc/ReceivedResumes");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/atom+xml");
				conn.setRequestProperty("Authorization", "Basic c3VyZXNoOkdvbGQxMjM=");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}
				System.out.println("check....");

				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
				
			
				/*String output;
			System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);	}
				*/
				
				DocumentBuilderFactory dbFactory 
	            = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(conn.getInputStream());
//	         Document doc1 = dBuilder.parse(fis);
	         doc.getDocumentElement().normalize();
	         System.out.println("Root element :" 
	            + doc.getDocumentElement().getNodeName());
	         NodeList nList = doc.getElementsByTagName("entry");
	         System.out.println("----------------------------");
	         String id=null;
	         String download=null;
	         String name=null;
	         String reqid=null;
	         String Fname=null;
	         String Lname=null;
	         String Mname=null;
	         String Con_num=null;
	         String Can_mail=null;
	         String Sub_Id=null;
	         String Dob=null;
	         String Tot_Exp=null;
	         String empname=null;
	         String Emp_CN=null;
	         String Emp_maail=null;
	         String Con_per=null;
	         String Current_loc=null;
	         String bywhom_id=null;
	         String pref_loc=null;
	         String Rate=null;
	         String avail=null;
	         String visa_type=null;
	         String Visa_app=null;
	         String Form=null;
	         String Form_94=null;
	         String Time=null;
	         String Exp=null;
	         String skill=null;
	         String Relo=null;
	         ArrayList receivedlist=new ArrayList();
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :" 
	               + nNode.getNodeName());
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               System.out.println("inside loop........");
	             
	               download=eElement .getElementsByTagName("content")
			                  .item(0)
			                   .getAttributes().getNamedItem("src").getNodeValue();
		               System.out.println("Document : " 
		                  + download);
	            
	               name=eElement
		                  .getElementsByTagName("d:Name")
		                  .item(0)
		                  .getTextContent();
	               System.out.println("Name : " 
	                  + name);
	                reqid =eElement
	 	                  .getElementsByTagName("d:RequirementIDId")
		                  .item(0)
		                  .getTextContent();
	               System.out.println(" RequirementId: " 
	               + reqid);
	                Fname=eElement
		 	                  .getElementsByTagName("d:FirstName")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("FirstName : " 
	 	                  + Fname);
	                Lname=eElement
		 	                  .getElementsByTagName("d:LastName")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("LastName : " 
	 	                  + Lname);
	                Mname= eElement
		 	                  .getElementsByTagName("d:MiddleName")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("MiddleName : " 
	 	                  +Mname);
	               Con_num=eElement
		 	                  .getElementsByTagName("d:CandidateContactNumber")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("CandidateContactNumber : " 
	 	                  + Con_num);
	               
	                Can_mail=eElement
		 	                  .getElementsByTagName("d:CandidateEMail")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("CandidateEMail : " 
	 	                  + Can_mail);
	                Sub_Id= eElement
		 	                  .getElementsByTagName("d:SubmittedById")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("SubmittedById : " 
	            		   +Sub_Id);
	                Dob= eElement
		 	                  .getElementsByTagName("d:DateOfBirth")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("DateOfBirth : " 
	 	                  +Dob);
	                Tot_Exp=eElement
		 	                  .getElementsByTagName("d:TotalExperience")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("TotalExperience : " 
	 	                  +Tot_Exp); 
	                empname=eElement
		 	                  .getElementsByTagName("d:EmployerName")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("EmployerName : " 
	 	                  + empname);
	               Emp_CN= eElement
	 	                  .getElementsByTagName("d:EmployerContactNumber")
	 	                  .item(0)
	 	                  .getTextContent();
	               System.out.println("EmployerContactNumber : " 
	 	                  +Emp_CN);
	                Emp_maail= eElement
		 	                  .getElementsByTagName("d:EmployerMailID")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("EmployerMailID : " 
	 	                  +Emp_maail);
	                Con_per= eElement
		 	                  .getElementsByTagName("d:ContactPerson")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("ContactPerson : " 
	 	                  +Con_per);
	                Current_loc=eElement
		 	                  .getElementsByTagName("d:CurrentLocation")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("CurrentLocation : " 
	 	                  + Current_loc);
	                bywhom_id= eElement
		 	                  .getElementsByTagName("d:ByWhomValue")
		 	                  .item(0)
		 	                  .getTextContent();
	               
	               System.out.println("ByWhomValue : " 
	 	                  +bywhom_id);
	                pref_loc= eElement
		 	                  .getElementsByTagName("d:PreferredLocation")
		 	                  .item(0)
		 	                  .getTextContent();
	               
	               System.out.println("PreferredLocation : " 
		 	                  + pref_loc);
	                Rate= eElement
		 	                  .getElementsByTagName("d:RateInDollar")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("RateInDollar : " 
		 	                  + Rate);
	                avail=eElement
		 	                  .getElementsByTagName("d:Availability")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("Availability : " 
		 	                  + avail);
	                visa_type= eElement
		 	                  .getElementsByTagName("d:VisaTypeValue")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("VisaTypeValue : " 
		 	                  +visa_type);
	                Visa_app=eElement
		 	                  .getElementsByTagName("d:VisaApprovalValue")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("VisaApprovalValue : " 
		 	                  + Visa_app);
	                Form= eElement
		 	                  .getElementsByTagName("d:FormI797AvailableValue")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("FormI797AvailableValue : " 
		 	                  +Form);
	                Form_94= eElement
		 	                  .getElementsByTagName("d:FormI94AvailableValue")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("FormI94AvailableValue : " 
		 	                  +Form_94);
	                Time=eElement
		 	                  .getElementsByTagName("d:BestTimeForTelephonicInterview")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("BestTimeForTelephonicInterview : " 
		 	                  + Time);
	                Exp=eElement
		 	                  .getElementsByTagName("d:ExperienceInUSA")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("ExperienceInUSA : " 
		 	                  + Exp);
	                skill= eElement
		 	                  .getElementsByTagName("d:Skills")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("Skills : " 
		 	                  +skill);
	                Relo=eElement
		 	                  .getElementsByTagName("d:ReLocationValue")
		 	                  .item(0)
		 	                  .getTextContent();
	               System.out.println("ReLocationValue : " 
		 	                  + Relo);
	               id=eElement
		 	                  .getElementsByTagName("d:Id")
		 	                  .item(0)
		 	                  .getTextContent();
	               
	            }
	            String temps[] = {name,reqid,Fname,Can_mail,id};
	            request.setAttribute("name",name);
	            request.setAttribute("reqid",reqid);
	            request.setAttribute("Fname",Fname);
	            request.setAttribute("Lname",Lname);
	            request.setAttribute("Mname",Mname);
	            request.setAttribute("Con_num",Con_num);
	            request.setAttribute("Can_mail",Can_mail);
	            request.setAttribute("Sub_Id",Sub_Id);
	            request.setAttribute("Dob",Dob);
	            request.setAttribute("Tot_Exp",Tot_Exp);
	            request.setAttribute("empname",empname);
	            request.setAttribute("Emp_CN",Emp_CN);
	            request.setAttribute("Emp_maail",Emp_maail);
	            request.setAttribute("Con_per",Con_per);
	            request.setAttribute("Current_loc",Current_loc);
	            request.setAttribute("bywhom_id",bywhom_id);
	            request.setAttribute("pref_loc",pref_loc);
	            request.setAttribute("Rate",Rate);
	            request.setAttribute("avail",avail);
	            request.setAttribute("visa_type",visa_type);
	            request.setAttribute("Visa_app",Visa_app);

	            request.setAttribute("Form",Form);
	            request.setAttribute("Form_94",Form_94);
	            request.setAttribute("Time",Time);
	            request.setAttribute("Exp",Exp);
	            request.setAttribute("skill",skill);
	            request.setAttribute("Relo",Relo);
	            request.setAttribute("download",download);
	            receivedlist.add(temps);
	         }
			
			
	       
	         request.setAttribute("receivedlist",receivedlist);

				conn.disconnect();

			  } catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (IOException e) {

				e.printStackTrace();

			  }
			return new ModelAndView("receivedresume");
			
		}
		
		else if(cmdProcess != null && cmdProcess.equalsIgnoreCase("getDetailsByid"))
		{
		
		String id = request.getParameter("id");
		System.out.println("id value"+id);
		try {

			URL url = new URL("https://www.digiblitzonline.com/sp-portal/dBConsult/digiblitz/_vti_bin/ListData.svc/ReceivedResumes%28"+id+"%29");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/atom+xml");
			conn.setRequestProperty("Authorization", "Basic c3VyZXNoOkdvbGQxMjM=");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			System.out.println("check....");

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			
		
			/*String output;
		System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);	}
			*/
			
			DocumentBuilderFactory dbFactory 
            = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(conn.getInputStream());
//         Document doc1 = dBuilder.parse(fis);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" 
            + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("entry");
         System.out.println("----------------------------");
       //  String id=null;
         String download=null;
         String name=null;
         String reqid=null;
         String Fname=null;
         String Lname=null;
         String Mname=null;
         String Con_num=null;
         String Can_mail=null;
         String Sub_Id=null;
         String Dob=null;
         String Tot_Exp=null;
         String empname=null;
         String Emp_CN=null;
         String Emp_maail=null;
         String Con_per=null;
         String Current_loc=null;
         String bywhom_id=null;
         String pref_loc=null;
         String Rate=null;
         String avail=null;
         String visa_type=null;
         String Visa_app=null;
         String Form=null;
         String Form_94=null;
         String Time=null;
         String Exp=null;
         String skill=null;
         String Relo=null;
         ArrayList receivedlist=new ArrayList();
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" 
               + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println("inside loop........");
             
  
               
               download=eElement .getElementsByTagName("content")
		                  .item(0)
		                   .getAttributes().getNamedItem("src").getNodeValue();
	               System.out.println("Document : " 
	                  + download);
            
               name=eElement
	                  .getElementsByTagName("d:Name")
	                  .item(0)
	                  .getTextContent();
               System.out.println("Name : " 
                  + name);
               
               
                reqid =eElement
 	                  .getElementsByTagName("d:RequirementIDId")
	                  .item(0)
	                  .getTextContent();
               System.out.println(" RequirementId: " 
               + reqid);
                Fname=eElement
	 	                  .getElementsByTagName("d:FirstName")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("FirstName : " 
 	                  + Fname);
                Lname=eElement
	 	                  .getElementsByTagName("d:LastName")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("LastName : " 
 	                  + Lname);
                Mname= eElement
	 	                  .getElementsByTagName("d:MiddleName")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("MiddleName : " 
 	                  +Mname);
               Con_num=eElement
	 	                  .getElementsByTagName("d:CandidateContactNumber")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("CandidateContactNumber : " 
 	                  + Con_num);
               
                Can_mail=eElement
	 	                  .getElementsByTagName("d:CandidateEMail")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("CandidateEMail : " 
 	                  + Can_mail);
                Sub_Id= eElement
	 	                  .getElementsByTagName("d:SubmittedById")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("SubmittedById : " 
            		   +Sub_Id);
                Dob= eElement
	 	                  .getElementsByTagName("d:DateOfBirth")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("DateOfBirth : " 
 	                  +Dob);
                Tot_Exp=eElement
	 	                  .getElementsByTagName("d:TotalExperience")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("TotalExperience : " 
 	                  +Tot_Exp); 
                empname=eElement
	 	                  .getElementsByTagName("d:EmployerName")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("EmployerName : " 
 	                  + empname);
               Emp_CN= eElement
 	                  .getElementsByTagName("d:EmployerContactNumber")
 	                  .item(0)
 	                  .getTextContent();
               System.out.println("EmployerContactNumber : " 
 	                  +Emp_CN);
                Emp_maail= eElement
	 	                  .getElementsByTagName("d:EmployerMailID")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("EmployerMailID : " 
 	                  +Emp_maail);
                Con_per= eElement
	 	                  .getElementsByTagName("d:ContactPerson")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("ContactPerson : " 
 	                  +Con_per);
                Current_loc=eElement
	 	                  .getElementsByTagName("d:CurrentLocation")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("CurrentLocation : " 
 	                  + Current_loc);
                bywhom_id= eElement
	 	                  .getElementsByTagName("d:ByWhomValue")
	 	                  .item(0)
	 	                  .getTextContent();
               
               System.out.println("ByWhomValue : " 
 	                  +bywhom_id);
                pref_loc= eElement
	 	                  .getElementsByTagName("d:PreferredLocation")
	 	                  .item(0)
	 	                  .getTextContent();
               
               System.out.println("PreferredLocation : " 
	 	                  + pref_loc);
                Rate= eElement
	 	                  .getElementsByTagName("d:RateInDollar")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("RateInDollar : " 
	 	                  + Rate);
                avail=eElement
	 	                  .getElementsByTagName("d:Availability")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("Availability : " 
	 	                  + avail);
                visa_type= eElement
	 	                  .getElementsByTagName("d:VisaTypeValue")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("VisaTypeValue : " 
	 	                  +visa_type);
                Visa_app=eElement
	 	                  .getElementsByTagName("d:VisaApprovalValue")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("VisaApprovalValue : " 
	 	                  + Visa_app);
                Form= eElement
	 	                  .getElementsByTagName("d:FormI797AvailableValue")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("FormI797AvailableValue : " 
	 	                  +Form);
                Form_94= eElement
	 	                  .getElementsByTagName("d:FormI94AvailableValue")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("FormI94AvailableValue : " 
	 	                  +Form_94);
                Time=eElement
	 	                  .getElementsByTagName("d:BestTimeForTelephonicInterview")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("BestTimeForTelephonicInterview : " 
	 	                  + Time);
                Exp=eElement
	 	                  .getElementsByTagName("d:ExperienceInUSA")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("ExperienceInUSA : " 
	 	                  + Exp);
                skill= eElement
	 	                  .getElementsByTagName("d:Skills")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("Skills : " 
	 	                  +skill);
                Relo=eElement
	 	                  .getElementsByTagName("d:ReLocationValue")
	 	                  .item(0)
	 	                  .getTextContent();
               System.out.println("ReLocationValue : " 
	 	                  + Relo);
               id=eElement
	 	                  .getElementsByTagName("id")
	 	                  .item(0)
	 	                  .getTextContent();
               
            }
            String temps[] = {name,reqid,Fname,Can_mail,id};
            request.setAttribute("name",name);
            request.setAttribute("reqid",reqid);
            request.setAttribute("Fname",Fname);
            request.setAttribute("Lname",Lname);
            request.setAttribute("Mname",Mname);
            request.setAttribute("Con_num",Con_num);
            request.setAttribute("Can_mail",Can_mail);
            request.setAttribute("Sub_Id",Sub_Id);
            request.setAttribute("Dob",Dob);
            request.setAttribute("Tot_Exp",Tot_Exp);
            request.setAttribute("empname",empname);
            request.setAttribute("Emp_CN",Emp_CN);
            request.setAttribute("Emp_maail",Emp_maail);
            request.setAttribute("Con_per",Con_per);
            request.setAttribute("Current_loc",Current_loc);
            request.setAttribute("bywhom_id",bywhom_id);
            request.setAttribute("pref_loc",pref_loc);
            request.setAttribute("Rate",Rate);
            request.setAttribute("avail",avail);
            request.setAttribute("visa_type",visa_type);
            request.setAttribute("Visa_app",Visa_app);

            request.setAttribute("Form",Form);
            request.setAttribute("Form_94",Form_94);
            request.setAttribute("Time",Time);
            request.setAttribute("Exp",Exp);
            request.setAttribute("skill",skill);
            request.setAttribute("Relo",Relo);
            request.setAttribute("download",download);
            receivedlist.add(temps);
         }
		
		
       
         request.setAttribute("receivedlist",receivedlist);

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		return new ModelAndView("ResumeDetail");

}
/*---------------------------------------End of Received ResumeList-----------------------------------------------*/
		
		

		else if(cmdProcess != null && cmdProcess.equalsIgnoreCase("initCandidateStatus")){
		
		try {

			URL url = new URL("https://www.digiblitzonline.com/sp-portal/dBConsult/digiblitz/_vti_bin/ListData.svc/CandidateStatus");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/atom+xml");
			conn.setRequestProperty("Authorization", "Basic c3VyZXNoOkdvbGQxMjM=");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			System.out.println("check....");

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			
		
			/*String output;
		System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);	}
			*/
			
			DocumentBuilderFactory dbFactory 
            = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(conn.getInputStream());
//         Document doc1 = dBuilder.parse(fis);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" 
            + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("entry");
         System.out.println("----------------------------");
       
         String id=null;
         String title=null;
         String ContentTypeID=null;
         String CandidateFullName=null;
         String CandidateStatusValue=null;
         String UniqueReference=null;
         String CANID=null;
         String CandidateContactNumber=null;
         String RequirementID=null;
         String CandidateEMail=null;
         String Modified=null;
         String Created=null;
         String AccountManagerAssignedToValue=null;
         String RecruiterAssignedToValue=null;
         String Id_ref=null;
         String ContentType=null;
         String SubmittedById=null;
        
        
         ArrayList CandidateList=new ArrayList();
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" 
               + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println("inside loop........");
             
  
               id=eElement .getElementsByTagName("id")  .item(0) .getTextContent(); 
               title=eElement .getElementsByTagName("title")  .item(0) .getTextContent();          
               ContentTypeID =eElement .getElementsByTagName("d:ContentTypeID") .item(0) .getTextContent();          
               CandidateFullName=eElement .getElementsByTagName("d:CandidateFullName") .item(0)  .getTextContent(); 
               CandidateStatusValue=eElement .getElementsByTagName("d:CandidateStatusValue") .item(0) .getTextContent();
               UniqueReference= eElement.getElementsByTagName("d:UniqueReference").item(0) .getTextContent();
               CANID=eElement  .getElementsByTagName("d:CANID").item(0) .getTextContent();
               CandidateContactNumber=eElement .getElementsByTagName("d:CandidateContactNumber").item(0) .getTextContent();      
               RequirementID= eElement .getElementsByTagName("d:RequirementID") .item(0)  .getTextContent();
               CandidateEMail= eElement .getElementsByTagName("d:CandidateEMail") .item(0)  .getTextContent();
               Modified= eElement .getElementsByTagName("d:Modified") .item(0)  .getTextContent();
               Created=eElement .getElementsByTagName("d:Created")  .item(0) .getTextContent();
               AccountManagerAssignedToValue=eElement .getElementsByTagName("d:AccountManagerAssignedToValue") .item(0) .getTextContent();          
               RecruiterAssignedToValue= eElement .getElementsByTagName("d:RecruiterAssignedToValue") .item(0) .getTextContent();      
               Id_ref= eElement .getElementsByTagName("d:Id").item(0) .getTextContent();
               ContentType= eElement .getElementsByTagName("d:ContentType") .item(0)  .getTextContent();
               SubmittedById= eElement .getElementsByTagName("d:SubmittedById") .item(0)  .getTextContent();
              
	 	                 
	 	                  
	 	                  
               
            }
            String TempList[] = {CANID,CandidateFullName,CandidateContactNumber,RequirementID,CandidateEMail,CandidateStatusValue,SubmittedById,UniqueReference};
            request.setAttribute("id",id);
            request.setAttribute("title",title);
            request.setAttribute("ContentTypeID",ContentTypeID);
            request.setAttribute("CandidateFullName",CandidateFullName);
            request.setAttribute("CandidateStatusValue",CandidateStatusValue);
            request.setAttribute("UniqueReference",UniqueReference);
            request.setAttribute("CANID",CANID);
            request.setAttribute("CandidateContactNumber",CandidateContactNumber);
            request.setAttribute("RequirementID",RequirementID);
            request.setAttribute("CandidateEMail",CandidateEMail);
            request.setAttribute("Modified",Modified);
            request.setAttribute("Created",Created);
            request.setAttribute("AccountManagerAssignedToValue",AccountManagerAssignedToValue);
            request.setAttribute("RecruiterAssignedToValue",RecruiterAssignedToValue);
            request.setAttribute("Id_ref",Id_ref);
            request.setAttribute("ContentType",ContentType);
            request.setAttribute("SubmittedById",SubmittedById);
            CandidateList.add(TempList);
         }
		
		
       
         request.setAttribute("CandidateList",CandidateList);

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		return new ModelAndView("Candidatestatus");
		
	}
		
		

		else if(cmdProcess != null && cmdProcess.equalsIgnoreCase("getCandidateStatusByid"))
		{
			
			String Uniqueid = request.getParameter("id");
			System.out.println("Valuelist"+Uniqueid);
		try {

			URL url = new URL("https://www.digiblitzonline.com/sp-portal/dBConsult/digiblitz/_vti_bin/ListData.svc/CandidateStatus%28"+Uniqueid+"%29");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/atom+xml");
			conn.setRequestProperty("Authorization", "Basic c3VyZXNoOkdvbGQxMjM=");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			System.out.println("check....");

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			
		
			/*String output;
		System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);	}
			*/
			
			DocumentBuilderFactory dbFactory 
            = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(conn.getInputStream());
//         Document doc1 = dBuilder.parse(fis);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" 
            + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("entry");
         System.out.println("----------------------------");
       
         String id=null;
         String title=null;
         String ContentTypeID=null;
         String CandidateFullName=null;
         String CandidateStatusValue=null;
         String UniqueReference=null;
         String CANID=null;
         String CandidateContactNumber=null;
         String RequirementID=null;
         String CandidateEMail=null;
         String Modified=null;
         String Created=null;
         String AccountManagerAssignedToValue=null;
         String RecruiterAssignedToValue=null;
         String Id_ref=null;
         String ContentType=null;
         String SubmittedById=null;
        
        
         ArrayList CandidateList=new ArrayList();
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" 
               + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println("inside loop........");
             
  
               id=eElement .getElementsByTagName("id")  .item(0) .getTextContent(); 
               title=eElement .getElementsByTagName("title")  .item(0) .getTextContent();          
               ContentTypeID =eElement .getElementsByTagName("d:ContentTypeID") .item(0) .getTextContent();          
               CandidateFullName=eElement .getElementsByTagName("d:CandidateFullName") .item(0)  .getTextContent(); 
               CandidateStatusValue=eElement .getElementsByTagName("d:CandidateStatusValue") .item(0) .getTextContent();
               UniqueReference= eElement.getElementsByTagName("d:UniqueReference").item(0) .getTextContent();
               CANID=eElement  .getElementsByTagName("d:CANID").item(0) .getTextContent();
               CandidateContactNumber=eElement .getElementsByTagName("d:CandidateContactNumber").item(0) .getTextContent();      
               RequirementID= eElement .getElementsByTagName("d:RequirementID") .item(0)  .getTextContent();
               CandidateEMail= eElement .getElementsByTagName("d:CandidateEMail") .item(0)  .getTextContent();
               Modified= eElement .getElementsByTagName("d:Modified") .item(0)  .getTextContent();
               Created=eElement .getElementsByTagName("d:Created")  .item(0) .getTextContent();
               AccountManagerAssignedToValue=eElement .getElementsByTagName("d:AccountManagerAssignedToValue") .item(0) .getTextContent();          
               RecruiterAssignedToValue= eElement .getElementsByTagName("d:RecruiterAssignedToValue") .item(0) .getTextContent();      
               Id_ref= eElement .getElementsByTagName("d:Id").item(0) .getTextContent();
               ContentType= eElement .getElementsByTagName("d:ContentType") .item(0)  .getTextContent();
               SubmittedById= eElement .getElementsByTagName("d:SubmittedById") .item(0)  .getTextContent();
              
	 	                 
	 	                  
	 	                  
               
            }
            String TempList[] = {CANID,CandidateFullName,CandidateContactNumber,RequirementID,CandidateEMail,CandidateStatusValue,SubmittedById,UniqueReference,Id_ref};
            request.setAttribute("id",id);
            request.setAttribute("title",title);
            request.setAttribute("ContentTypeID",ContentTypeID);
            request.setAttribute("CandidateFullName",CandidateFullName);
            request.setAttribute("CandidateStatusValue",CandidateStatusValue);
            request.setAttribute("UniqueReference",UniqueReference);
            request.setAttribute("CANID",CANID);
            request.setAttribute("CandidateContactNumber",CandidateContactNumber);
            request.setAttribute("RequirementID",RequirementID);
            request.setAttribute("CandidateEMail",CandidateEMail);
            request.setAttribute("Modified",Modified);
            request.setAttribute("Created",Created);
            request.setAttribute("AccountManagerAssignedToValue",AccountManagerAssignedToValue);
            request.setAttribute("RecruiterAssignedToValue",RecruiterAssignedToValue);
            request.setAttribute("Id_ref",Id_ref);
            request.setAttribute("ContentType",ContentType);
            request.setAttribute("SubmittedById",SubmittedById);
            CandidateList.add(TempList);
         }
		
		
       
         request.setAttribute("CandidateList",CandidateList);

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		return new ModelAndView("CandidateStatusList");
		
	
		
		}
		
		
		return null;
		}
	}

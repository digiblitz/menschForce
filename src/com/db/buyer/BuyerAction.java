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
package com.db.buyer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.AD.action.NewUser;
import com.db.GeneralDBAction;
import com.db.buyer.candidate.BuyerCandidateService;
import com.hlccommon.util.Debug;
import com.infusionejb.session.InfusionSessionBean;
import com.user.action.Crypt;
import com.user.action.KlgUserAction;

@Controller
public class BuyerAction {
	
	BuyerService buyerService = new BuyerService();
	BuyerCandidateService buyerCandidateService = new BuyerCandidateService();

	public static final String config_prop = "config.properties";
	public static final String LOG_FILE = "Log4j.properties";
	InputStream is;
	InputStream config_inputStream;
	
	GeneralDBAction db=new GeneralDBAction();
	
	static Logger log = Logger.getLogger(BuyerAction.class.getName());
	Properties properties;
    
	private InfusionSessionBean obj1;
	{
		try{
			obj1 = new InfusionSessionBean();
		}catch(Exception e){
			 throw new RuntimeException(e);
		}
	}
	
	private Pattern pattern;
	 private Matcher matcher;

	 private static final String EMAIL_PATTERN =
	 "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	 + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	 /**
	  * Validate hex with regular expression
	  *
	  * @param hex
	  *            hex for validation
	  * @return true valid hex, false invalid hex
	  */
	 public boolean emailValidate(final String hex) {

	 matcher = pattern.matcher(hex);
	 return matcher.matches();

	 }
	 
	 
	
	BuyerAction(){
		// ======================log file properties configuration started====================
	       Properties logProp = new Properties();  
		      is = (InputStream) getClass().getClassLoader().getResourceAsStream(LOG_FILE);
		      try {
				logProp.load(is);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		      PropertyConfigurator.configure(logProp);      
		      log.info("Logging enabled");  
		   // ======================log file properties configuration ended====================
		
		
		/*
		 * *===================Property file loaded here======================================
		 */
		/** Creates a new instance of EmailEngine */
     properties = new Properties(); 
     
     try {               
         // properties.load(new FileInputStream(fileName));
  	//properties.load(KlgLoginAction.class.getResourceAsStream("/companyDetails.properties"));
  	properties.load(KlgUserAction.class.getResourceAsStream("/config.properties"));
  	properties.load(NewUser.class.getResourceAsStream("/config.properties"));

  	properties.load(getClass().getClassLoader().getResourceAsStream("/infusionMail.properties"));
  	
  	//properties.load(KlgLoginAction.class.getResourceAsStream("/paypal.properties"));
  	System.out.println("Property file loaded successfully");
      } catch (Exception e) {
          try {
            //properties.load(new FileInputStream("src/companyDetails.properties"));
            properties.load(new FileInputStream("/config.properties"));
          }catch(Exception ee) {
             Debug.print("Could not load the config.properties");
          }
      }
     
     
     pattern = Pattern.compile(EMAIL_PATTERN);
     
	}
	
	@RequestMapping("/pushBuyerDetails.html")
	public ModelAndView pushBuyerDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
				
		BuyerClient buyerClient = BuyerClient.getInstance();
		String pushBuyer = request.getParameter("pushBuyer");
		
		
		Debug.print("PayrollAction.pushBuyerDetails()");
		
		String buyerServiceStatus = null;
		String buyerCandidateServiceStatus = null;
		
		
		try{
			//status = buyerClient.buyerToSignUpClient(pushBuyer);
			//System.out.println("status ---> "+status);
				
			System.out.println("starts");
			buyerServiceStatus = buyerService.pushBuyerDetailsToSignUp(pushBuyer);
			System.out.println("buyerServiceStatus --> "+buyerServiceStatus);
			
			buyerCandidateServiceStatus = buyerCandidateService.pushBuyerCandidateDetailsToRegister(pushBuyer);
						
			System.out.println("buyerCandidateServiceStatus --> "+buyerCandidateServiceStatus);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ModelAndView("frmHome");
	}
	
	@RequestMapping("/pushBuyerToMasterTable.html")
	public ModelAndView pushBuyerToMasterTable(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		
		String FILENAME = "D:\\mf-client-list.txt";
		
		BufferedReader br = null;
		FileReader fr = null;
		
		int count = 0;
		int sNo = 0;
		
		
		String name = null;
		//String fName = null;
		//String lName = null;
		String company = null;
		//String client = null;
		
		String clientName = "NA";
		String website = "NA";
		String contactPerson = "NA";
		String email = null;
		String contactPhone = "00000";
		String fax = "00000";
		
				
		String buyerEmail = null;
		 String buyerInsertionStatus = null;

		 ArrayList<String> buyerEmailList = null;
		 
		try {

			buyerEmailList = new ArrayList<String>();
			
		fr = new FileReader(FILENAME);
		br = new BufferedReader(fr);

		String sCurrentLine;

		br = new BufferedReader(new FileReader(FILENAME));

		
		/*count = db.countMasterBuyerDetails();
		System.out.println("count --> "+count);*/
				
		while ((sCurrentLine = br.readLine()) != null) {
			buyerEmailList.add(sCurrentLine);			
		}
		System.out.println("buyerEmailList "+buyerEmailList.size());
		
		Set<String> hs = new HashSet<String>();
		hs.addAll(buyerEmailList);
		buyerEmailList.clear();
		buyerEmailList.addAll(hs);
		
		count = db.countMasterBuyerDetails();
		System.out.println("count out --> "+count);
		
		if(buyerEmailList != null && buyerEmailList.size() != 0){
			
			Iterator<String> itr = buyerEmailList.iterator();
			
			while(itr.hasNext()){
			//System.out.println("itr.next --> "+itr.next());
			
			email = itr.next();
			
			
			
			buyerEmail = db.checkBuyerEmail(email);
			System.out.println(" buyerEmial -->"+buyerEmail);
			
			if(buyerEmail == null){
				sNo = count;
				System.out.println("sno --> "+sNo);
				System.out.println("email --> "+email);
				
				email = email.replaceAll("<", "");
				email = email.replaceAll(">", "");
				
				boolean emailFlag = emailValidate(email);
				
				if(emailFlag){
					
								
				String str[] = email.split("@");
				if(str.length > 1){
				for(int i=0; i<str.length; i++){
				name = str[0];
				company = str[1];
				}
				//System.out.println("name --> "+name+" compoany --> "+company);
				String str1[] = name.split("\\.");
				if(str1.length > 1){
				for(int i=0; i<str1.length; i++){
					//clientName = str1[0];
					contactPerson = str1[0];
				}
				}else{
					//clientName = name;
					contactPerson = name;
				}
				System.out.println("clientName --> "+clientName+" contactPerson --> "+contactPerson);
				String str2[] = company.split("\\.");
				if(str2.length > 1){
				for(int i=0; i<str2.length; i++){
					clientName = str2[0];
				}
				}else{
					clientName = company;
				}
				System.out.println("clientName --> "+clientName);
				}else{
				}
				
				count = count+1;
				System.out.println("count in --> "+count);
				
				buyerInsertionStatus = db.insertBuyerEmail(sNo, clientName, website, contactPerson, email, contactPhone, fax);
				//System.out.println("buyerInsertionStatus --> "+buyerInsertionStatus);
				
			}else{
				System.out.println("email is not a valida one");
			}
			
			}else{
				System.out.println("in else");
			}
			
			}			
			
		}
		
		/*
					
		
	//System.out.println(sCurrentLine);
	*/
		
		System.out.println("buyerInsertionStatus ---> "+buyerInsertionStatus);
		

		} catch (IOException e) {

		e.printStackTrace();

		} finally {

		try {

		if (br != null)
		br.close();

		if (fr != null)
		fr.close();

		} catch (IOException ex) {

		ex.printStackTrace();

		}

		}

		
		
		return new ModelAndView("frmHome");
	}
	
	@RequestMapping("/updateContactDetail.html")
	public ModelAndView updateContactDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		 ArrayList buyerList = null;
		String emailId = null;
		String buyerUserId = null;
		
		ArrayList<String> userIdList = null;
		
		try{
			
			buyerList = new ArrayList();
			buyerList = db.getRegisteredBuyerDetails();
			
			userIdList = new ArrayList<String>();
			
if(buyerList != null && buyerList.size() != 0){
				
				Iterator itr = buyerList.iterator();
				while(itr.hasNext()){
					String strarr[] = (String[])itr.next();
					
					emailId = strarr[4];
					System.out.println("email --> "+emailId);
					buyerUserId = db.getUserIdByEmail(emailId);
					userIdList.add(buyerUserId);
				}				
}

System.out.println("userIdList --> "+userIdList.size());

		if(userIdList != null && userIdList.size() != 0){
			
			Iterator<String> itr = userIdList.iterator();
			while(itr.hasNext()){
				
				String tempBuyerUserId = itr.next();
				System.out.println("tempBuyerUserId ---> "+tempBuyerUserId);
				boolean contactFlag = db.updateBuyerContactID(tempBuyerUserId);
				System.out.println("contactFlag --> "+contactFlag);
			}
			
		}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ModelAndView("frmHome");
	}
	
	@RequestMapping("/updateBuyerUserNamePassword.html")
	public ModelAndView updateUserNamePassword(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		ArrayList<String[]> alist = null;
		ArrayList<String[]> buyerList = null;
		
		String emailId = null;
		String buyerUserId = null;
		
		try{
			
			alist = new ArrayList<String[]>();
			buyerList = new ArrayList<String[]>(); 
			
			alist = db.getRegisteredBuyerDetails();
			
			if(alist != null && alist.size() != 0){
				
				Iterator<String[]> itr = alist.iterator();
				
				while(itr.hasNext()){
				
					String strList[] = itr.next();
					
					emailId = strList[4];
					buyerUserId = db.getUserIdByEmail(emailId);
					String str[] = db.getByuerUserLoginCredentials(buyerUserId); 
					buyerList.add(str);					
				}
			}
			
			
			buyerUserId = db.getUserIdByEmail("rinku@amtexsystems.com");
			System.out.println("out ---> "+buyerUserId);
			String str[] = db.getByuerUserLoginCredentials("019E780F-8C65-425A-9BC3-08D6671F182A"); 
			
			for(int i=0; i<str.length; i++){
				System.out.println(str[i]);
				System.out.println("-----------");
				
				String login_name = str[0];
	        	String password = str[1];
	        	String email_id = str[2];
	        	String company = str[3];
	        	String category = str[4];
				
				BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
                textEncryptor.setPassword("sa");
                String  decryptPwd = textEncryptor.decrypt(password);
                 System.out.println("decryptPwd --> "+decryptPwd);
                 
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return new ModelAndView("frmHome");
	}
	
	 
	@RequestMapping("/updateBuyerDetails.html")
	public ModelAndView updateBuyerDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		ArrayList<String[]> alist = null;
		ArrayList<String[]> buyerList = null;
		
		String emailId = null;
		String buyerUserId = null;
		
		try{
			
			alist = new ArrayList<String[]>();
			buyerList = new ArrayList<String[]>(); 
			
			alist = db.getRegisteredBuyerDetails();
			
			if(alist != null && alist.size() != 0){
				
				Iterator<String[]> itr = alist.iterator();
				
				while(itr.hasNext()){
				
					String strList[] = itr.next();
					
					emailId = strList[4];
					buyerUserId = db.getUserIdByEmail(emailId);
					String str[] = db.getByuerUserLoginCredentials(buyerUserId); 
					buyerList.add(str);					
				}
			}
			
			
			if(buyerList != null && buyerList.size() != 0){
				
				Iterator<String[]> itr = buyerList.iterator();
				
				while(itr.hasNext()){
					String str[] = itr.next();
					
					String firstName = str[0];
					String lastName = str[1];
					String loginName = str[2];
		        	String password = str[3];
		        	String email_id = str[4];
		        	String company = str[5];
		        	String category = str[6];
		        	
		        	
		        	BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
	                textEncryptor.setPassword("sa");
	                String  decryptPwd = textEncryptor.decrypt(password);
	                 
		        	System.out.println("loginName --> "+loginName+" decryptPwd --> "+decryptPwd);
		        	System.out.println("email_id --> "+email_id+" company --> "+company);
		        	System.out.println("category --> "+category);
		        	
		        	int contactId = obj1.createAndCheckDuplicateContact(firstName , lastName , email_id);
		        	
		        	System.out.println("contactId ---> "+contactId);
		        	
		        	if(contactId != 0){
		        		int updatedid = obj1.updateBuyerDetails(firstName , lastName , loginName , decryptPwd , category , email_id , company , contactId  );
		        		System.out.println("updatedid ---> "+updatedid);
		        	}
		        	
		        	
				}
			}
					
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return new ModelAndView("frmHome");
	}
	
	@RequestMapping("/updatepasswordrole.html")
	public ModelAndView updatepasswordrole(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		ArrayList<String[]> alist = null;
		ArrayList<String[]> buyerList = null;
		
		Crypt ccc = new Crypt();
		boolean addUsrToGroupStatus = false;
		String emailId = null;
		String roleId = "9CED2154-1FAA-4773-90C9-BBC5CE54EB5D";
		String buyerUserId = null;
		String encryptUserPass = null;
		String encryptfNameLname = null;
		try{
			
			alist = new ArrayList<String[]>();
			buyerList = new ArrayList<String[]>(); 
			
			alist = db.getRegisteredBuyerDetails();
			
			if(alist != null && alist.size() != 0){
				
				Iterator<String[]> itr = alist.iterator();
				
				while(itr.hasNext()){
				
					String strList[] = itr.next();
					
					emailId = strList[4];
			
					buyerUserId = db.getUserIdByEmail(emailId);
					String str1[] = db.getByuerUserLoginCredentials(buyerUserId); 
					buyerList.add(str1);					
				}
			}
			
			
			if(buyerList != null && buyerList.size() != 0){
				
				Iterator<String[]> itr = buyerList.iterator();
				
				while(itr.hasNext()){
					String str[] = itr.next();
					
					String firstName = str[0];
					String lastName = str[1];
					String loginName = str[2];
		        	String password = str[3];
		        	String email_id = str[4];
		        	String company = str[5];
		        	String category = str[6];
		        	
		        	
		        	BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
	                textEncryptor.setPassword("sa");
	                String  decryptPwd = textEncryptor.decrypt(password);
	                
	                String userNamePass = decryptPwd+"-sep-"+loginName;
	                String fNameLname = firstName+"-sep-"+lastName;
          	        try {
						encryptUserPass = ccc.encrypt(userNamePass, "digiblitz");
					} catch (InvalidKeyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchPaddingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvalidAlgorithmParameterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalBlockSizeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BadPaddingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        	  	      try {
						encryptfNameLname = ccc.encrypt(fNameLname, "digiblitz");
					} catch (InvalidKeyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchPaddingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvalidAlgorithmParameterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalBlockSizeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BadPaddingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        	  	      
        	  	    NewUser nu = new NewUser();	
        	  	    System.out.println("Username and password ---> "+fNameLname+"--------------"+decryptPwd);
        	  	  String roleName = db.getRoleNameByRoleId(roleId); 
        	  	  addUsrToGroupStatus = nu.addUserToGroup(firstName, lastName, roleName, loginName); 
        	  	  /*String redirectsite = properties.getProperty("config.SP_reset_pwd_url")+"/ResetPassword.aspx?UN="+encryptfNameLname+"&PWD="+encryptUserPass;
		        	
        	  	System.out.println("rediredt url :::::::::::::::::::"+redirectsite);
                response.sendRedirect(redirectsite);*/
        	  	System.out.println("rediredt url :::::::::::::::::::"+encryptUserPass);  
        	  	URL url = new URL("http://198.71.58.51:56124/ResetPassword.aspx?UN="+encryptfNameLname+"&PWD="+encryptUserPass);
        	  	System.out.println("rediredt url :::::::::::::::::::"+url);
    			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    			 System.out.println("conn.getResponseCode()----->"+conn.getResponseCode());
    			
				}
			}
					
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return new ModelAndView("frmHome");
	}
	
}

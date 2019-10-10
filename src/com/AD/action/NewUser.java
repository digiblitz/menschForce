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
package com.AD.action;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.AttributeInUseException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.StartTlsRequest;
import javax.naming.ldap.StartTlsResponse;

import com.hlccommon.util.Debug;
import com.login.action.KlgLoginAction;

public class NewUser {
	static Properties properties;
	

    //private static final String DOMAIN_NAME = "u18643868.onlinehome-server";
    private static String DOMAIN_NAME = null;
    
//	private static final String DOMAIN_NAME = "menschForce.com";
    
    
    //private static final String DOMAIN_ROOT = "DC=u18643868,DC=onlinehome-server";
    private static String DOMAIN_ROOT = null;
    
//	private static final String DOMAIN_ROOT = "DC=menschForce,DC=com";
    
    
//    private static final String DOMAIN_URL = "ldap://192.168.1.105:389";
    //private static final String DOMAIN_URL = "ldap://198.71.58.51:389";
    private static String DOMAIN_URL = null;
    
    
    //private static final String ADMIN_NAME = "USdev@u18643868.onlinehome-server";
    private static String ADMIN_NAME = null;
//    private static final String ADMIN_NAME = "Administrator@menschForce.com";
    
    
//    private static final String ADMIN_PASS = "dev1@digiBlitz0505#";
    //private static final String ADMIN_PASS = "digiBlitz@dev44#";
    private static String ADMIN_PASS = null;
    
    
    
    static DirContext ctx      = null;
    static Hashtable env       = new Hashtable();
 /*   private static final String USERS_OU =
            "OU=SharePoint,DC=ELMT-DEVSERVER,DC=COM";  */
   /* -----------BENDING------------------- 
    private static final String USERS_OU =
            "OU=kamal123,DC=ELMT-DEVSERVER,DC=COM";
    
    private static final String GROUPS_OU =
            "OU=JavaSample,DC=ELMT-DEVSERVER,DC=COM";
            ------------------------------------BENDING-------------------*/
    private static String GROUPS_OU = null;
//    private static final String GROUPS_OU ="OU=dBVMS,DC=menschForce,DC=com";
    
    private static String auth_method = null;

    
    private String userName, firstName, lastName, password, organisationUnit, userEmail;
    private LdapContext context;
    
    
    public NewUser(){
    	
    	
    	  
    	/** Creates a new instance of EmailEngine */
        properties = new Properties();        
       // String fileName = System.getProperty("user.home")+File.separator+"companyDetails.properties";
       // Debug.print("FileName path::::::::::::::::::::::::::::::"+fileName);
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("/companyDetails.properties");
        
        try {               
               // properties.load(new FileInputStream(fileName));
        	properties.load(NewUser.class.getResourceAsStream("/companyDetails.properties"));
        	//properties.load(NewUser.class.getResourceAsStream("/config.properties"));
        	properties.load(getClass().getClassLoader().getResourceAsStream("/config.properties"));
        	
        	//properties.load(KlgLoginAction.class.getResourceAsStream("/paypal.properties"));
        	System.out.println("Property file loaded successfully");
            } catch (Exception e) {
                try {
                  //properties.load(new FileInputStream("src/companyDetails.properties"));
                  properties.load(in);
                }catch(Exception ee) {
                   Debug.print("Could not load the companyDetails.properties");
                }
            }
    	 
        DOMAIN_NAME = properties.getProperty("config.DOMAIN_NAME");
        DOMAIN_ROOT = properties.getProperty("config.DOMAIN_ROOT");
        DOMAIN_URL = properties.getProperty("config.DOMAIN_URL");
        ADMIN_NAME = properties.getProperty("config.ldap_domain_name");
        ADMIN_PASS = properties.getProperty("config.ldap_password");
        GROUPS_OU = "OU="+properties.getProperty("config.OU")+","+properties.getProperty("config.DOMAIN_ROOT");
        
      	 //String auth_method  = "simple";
      	auth_method  = properties.getProperty("config.auth_method");
      	
      	
         // set the LDAP client Version
         //String ldap_version = "3";
      	String ldap_version = properties.getProperty("config.ldap_version");
      	
      	
         // This is our LDAP Server's IP
         //String ldap_host    = "198.71.58.51";
      	String ldap_host    = properties.getProperty("config.ldap_host");
      	
      	
//         String ldap_host    = "192.168.1.105";
      	
      	
         // This is our LDAP Server's Port
         //String ldap_port    = "389";
      	String ldap_port    = properties.getProperty("config.ldap_port");
      	
      	
         // This is our access ID
         //String ldap_dn      = loginName+"@ELMT-DEVSERVER.COM";
//         String ldap_dn      = "Administrator@menschForce.com";
      	//String ldap_dn      = "USdev@u18643868.onlinehome-server";
      	String ldap_dn      = properties.getProperty("config.ldap_domain_name");
         
         
         
        // This is our access PW
//         String ldap_pw      = "dev1@digiBlitz0505#";
        // String ldap_pw      = password;
      	//String ldap_pw      = "digiBlitz@dev44#";
      	String ldap_pw      = properties.getProperty("config.ldap_password");
      	
         // This is our base DN
         //String base_dn      = "DC=ELMT-DEVSERVER,DC=com";


         // Here we store the returned LDAP object data
         // This will hold the returned attribute list

         env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
         env.put(Context.PROVIDER_URL,"ldap://" + ldap_host + ":" + ldap_port);
         env.put(Context.SECURITY_AUTHENTICATION, auth_method);
         env.put(Context.SECURITY_PRINCIPAL, ldap_dn);
         env.put(Context.SECURITY_CREDENTIALS, ldap_pw);
         env.put("java.naming.ldap.version", ldap_version);
      

          try{
        	  System.out.println("Connecting to host " + ldap_host + " at port " + ldap_port + "...");
              System.out.println();

              this.ctx = new InitialDirContext(env);
              System.out.println("LDAP authentication successful!");
              // Specify the attribute list to be returned
              //String[] attrIDs = { "memberOf","sn","givenname", "mail","telephonenumber","samaccountname"};
          }catch (Exception e) {
            e.printStackTrace();
         }
      }
    

    public NewUser(String userName, String firstName, String lastName,
                    String password, String organisationUnit, String userEmail) throws IOException {
    	
    	/*properties = new Properties();    	
    	properties.load(getClass().getClassLoader().getResourceAsStream("/config.properties"));
    	ADMIN_NAME = properties.getProperty("config.ldap_domain_name");
        ADMIN_PASS = properties.getProperty("config.ldap_password");
        DOMAIN_URL = properties.getProperty("config.DOMAIN_URL");*/
    	
    	/** Creates a new instance of EmailEngine */
        properties = new Properties();        
       // String fileName = System.getProperty("user.home")+File.separator+"companyDetails.properties";
       // Debug.print("FileName path::::::::::::::::::::::::::::::"+fileName);
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("/companyDetails.properties");
        
        try {               
               // properties.load(new FileInputStream(fileName));
        	properties.load(NewUser.class.getResourceAsStream("/companyDetails.properties"));
        	//properties.load(NewUser.class.getResourceAsStream("/config.properties"));
        	properties.load(getClass().getClassLoader().getResourceAsStream("/config.properties"));
        	
        	//properties.load(KlgLoginAction.class.getResourceAsStream("/paypal.properties"));
        	System.out.println("Property file loaded successfully");
            } catch (Exception e) {
                try {
                  //properties.load(new FileInputStream("src/companyDetails.properties"));
                  properties.load(in);
                }catch(Exception ee) {
                   Debug.print("Could not load the companyDetails.properties");
                }
            }
    	 
        DOMAIN_NAME = properties.getProperty("config.DOMAIN_NAME");
        DOMAIN_ROOT = properties.getProperty("config.DOMAIN_ROOT");
        DOMAIN_URL = properties.getProperty("config.DOMAIN_URL");
        ADMIN_NAME = properties.getProperty("config.ldap_domain_name");
        ADMIN_PASS = properties.getProperty("config.ldap_password");
        GROUPS_OU = "OU="+properties.getProperty("config.OU")+","+properties.getProperty("config.DOMAIN_ROOT");
        
      	 //String auth_method  = "simple";
      	auth_method  = properties.getProperty("config.auth_method");
      	
        
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.organisationUnit = organisationUnit;
        this.userEmail = userEmail;
                
        Hashtable<String, String> env = new Hashtable<String, String>();

        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

        // set security credentials, note using simple cleartext authentication
        //env.put(Context.SECURITY_AUTHENTICATION, "simple");
        System.out.println("auth_method------------------------------------------>"+auth_method);
        System.out.println("ADMIN_NAME------------------------------------------>"+ADMIN_NAME);
        System.out.println("ADMIN_PASS------------------------------------------>"+ADMIN_PASS);
        System.out.println("DOMAIN_URL------------------------------------------>"+DOMAIN_URL);
        
        env.put(Context.SECURITY_AUTHENTICATION, auth_method);
        env.put(Context.SECURITY_PRINCIPAL, ADMIN_NAME);
        env.put(Context.SECURITY_CREDENTIALS, ADMIN_PASS);
       
        // connect to my domain controller
        env.put(Context.PROVIDER_URL, DOMAIN_URL);
     
        try {
            this.context = new InitialLdapContext(env, null);
            System.out.println("Connected to LDAP Active Directory Successfully ");
        } catch (NamingException e) {
            System.err.println("Problem creating object: ");
            e.printStackTrace();
        }

    }
/*=====================================Create User Start Here================================================================*/
    public boolean addUser() throws NamingException, UnsupportedEncodingException {

        // Create a container set of attributes
        Attributes container = new BasicAttributes();

        // Create the objectclass to add
        Attribute objClasses = new BasicAttribute("objectClass");
        objClasses.add("top");
        objClasses.add("person");
        objClasses.add("organizationalPerson");
        objClasses.add("user");

        // Assign the username, first name, and last name
        String cnValue = new StringBuffer(firstName).append(" ").append(lastName).toString();
        
        Attribute cn = new BasicAttribute("cn", cnValue);
        Attribute sAMAccountName = new BasicAttribute("sAMAccountName", userName);
        Attribute principalName = new BasicAttribute("userPrincipalName", userName
                + "@" + DOMAIN_NAME);
        Attribute givenName = new BasicAttribute("givenName", firstName);
        Attribute sn = new BasicAttribute("sn", lastName);
        Attribute uid = new BasicAttribute("uid", userName);
        Attribute mail = new BasicAttribute("mail", userEmail);

        // Add password
        Attribute userPassword = new BasicAttribute("userpassword", password);
        int UF_ACCOUNTDISABLE = 0x0002;
        int UF_PASSWD_NOTREQD = 0x0020;
        int UF_PASSWD_CANT_CHANGE = 0x0040;
        int UF_NORMAL_ACCOUNT = 0x0200;
        int UF_DONT_EXPIRE_PASSWD = 0x10000;
        int UF_PASSWORD_EXPIRED = 0x800000;
        int UF_LOCKOUT = 0x0010;
        Attribute userAccCont = new BasicAttribute("userAccountControl",Integer.toString(UF_NORMAL_ACCOUNT + UF_PASSWD_NOTREQD + UF_DONT_EXPIRE_PASSWD + UF_LOCKOUT));
//      Attribute userAccCont = new BasicAttribute("userAccountControl","544");

        // Add these to the container
        container.put(objClasses);
        container.put(sAMAccountName);
        container.put(principalName);
        container.put(cn);
        container.put(sn);
        container.put(givenName);
        container.put(uid);
        container.put(mail);
       
        container.put(userPassword);
        container.put(userAccCont);
        

        // Create the entry
        try {
            context.createSubcontext(getUserDN(cnValue, organisationUnit), container);
           
            System.out.println("Set password & updated userccountControl sucessfully ");
            return true;
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }
    
//    private byte[] encodePassword(String password) throws UnsupportedEncodingException {
//        String newQuotedPassword = "\"" + password + "\"";
//        System.out.println(newQuotedPassword.getBytes("UTF-16LE"));
//        return newQuotedPassword.getBytes("UTF-16LE");
//    }

    private static String getUserDN(String aUsername, String aOU) {
        return "cn=" + aUsername + ",ou=" + aOU + "," + DOMAIN_ROOT;
    }
    
/*=====================================Create User End Here================================================================*/
    
/*=====================================Mapping User with Role Start Here=====================================================*/
      public boolean addUserToGroup(String firstName, String lastName, String roleName, String login_name) throws NamingException {
    //public boolean addUserToGroup(String firstName1, String lastName1) throws NamingException {
    	  
    	  String cnValue = new StringBuffer(firstName).append(" ").append(lastName).toString();
    	System.out.println("cnValue in Servlet : "+cnValue);
    	
    	 //String userName = "CN="+cnValue+",OU=Sharepoint,DC=u18643868, DC=onlinehome-server";
    	String userName = "CN="+cnValue+",OU="+properties.getProperty("config.OU")+","+properties.getProperty("config.DOMAIN_ROOT");
    	 
//   	 String userName = "CN="+cnValue+",OU=dBVMS,DC=menschForce, DC=com";

	        //String groupName = "CN="+roleName+",OU=Sharepoint,DC=u18643868, DC=onlinehome-server";
    	String groupName = "CN="+roleName+",OU="+properties.getProperty("config.OU")+","+properties.getProperty("config.DOMAIN_ROOT");
    	
    	
//     String groupName = "CN="+roleName+",OU=dBVMS,DC=menschForce, DC=com";
	        
    	  try {

    	


	        	// System.out.println("Connecting to host " + ldap_host + " at port " + ldap_port + "...");
	            // System.out.println();

	            // context = new InitialDirContext(env);
	            // System.out.println("LDAP authentication successful!");
/*--------------------------Prabhu here---------------------------------------------*/
	        String dn           = "";
	        Attributes attrs;
	        String[] attrIDs = {"samaccountname"};
	        String sAMAloginName = null;
            
            SearchControls ctls = new SearchControls();
            ctls.setReturningAttributes(attrIDs);
            ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            // Specify the search filter to match
            String filter = "(&(objectClass=user)(sAMAccountName="+login_name+"))";

            // Search the subtree for objects using the given filter
            NamingEnumeration answer = ctx.search(DOMAIN_ROOT, filter, ctls);

            // Print the answer
            //Search.printSearchEnumeration(answer);
      	 
            while (answer.hasMoreElements()) {
      		  
              SearchResult sr = (SearchResult)answer.next();
              dn = sr.getName();
      		     
              attrs = sr.getAttributes();

              System.out.println("Found Object: " + dn + "," + DOMAIN_ROOT);
              if (attrs != null) {
                // we have some attributes for this object
                NamingEnumeration ae = attrs.getAll();
                while (ae.hasMoreElements()) {
                  Attribute attr = (Attribute)ae.next();
                  String attrId = attr.getID();
                  System.out.println("Found Attribute: " + attrId);
                  Enumeration vals = attr.getAll();
                  while (vals.hasMoreElements()) {
                	  sAMAloginName = (String)vals.nextElement();
                    System.out.println(attrId + ": " + sAMAloginName);
      			  //System.out.println("givenname "+ attrs.get("givenname"));

                  }
                }
              }
           }

            System.out.println("sAMAloginName OUTSIDE THE LDAP : "+sAMAloginName);
            
            if((sAMAloginName != null) &&(sAMAloginName.equals(login_name))){
            	
            	
 /*===============================================Prabhu here-----------------------------------*/
	            

	            	System.out.println("Inside the add group ldap");
	                ModificationItem member[] = new ModificationItem[1];
	                member[0] = new ModificationItem(DirContext.ADD_ATTRIBUTE,
	                        new BasicAttribute("member", userName));

	                ctx.modifyAttributes(groupName, member);
	                System.out.println("Added user to group : " + groupName);
	                
            }
            		return true;

	            } catch (NameAlreadyBoundException e) {
	                System.err.println("User "+cnValue+" was already mapped with the Role "+groupName+" in Active Directory");
	                return true;
	            } catch (NamingException e){
	            	System.err.println("Problem adding user with the group "+e);
	                return false;

	            }
	            
	            

    	
    }
/*=====================================Mapping User with Role End Here=====================================================*/
      
    /*
    
    public boolean deleteUser(String username) throws NamingException {
        try {
            context.destroySubcontext(getUserDN(username));
            return true;
        } catch (NameNotFoundException e) {
        	return false;
            // If the user is not found, ignore the error
        }
    }
    
    private String getUserDN(String username) {
        return new StringBuffer()
                .append("uid=")
                .append(username)
                .append(",")
                .append(USERS_OU)
                .toString();
    }   */
    
    /*-------------------------BENDING--------------------------
    
    public void assignUser(String username, String groupName)
            throws NamingException {

            try {
                ModificationItem[] mods = new ModificationItem[1];

                Attribute mod =
                    new BasicAttribute("uniqueMember",
                                       getUserDN(username));
                mods[0] =
                    new ModificationItem(DirContext.ADD_ATTRIBUTE, mod);
                context.modifyAttributes(getGroupDN(groupName), mods);
            } catch (AttributeInUseException e) {
                // If user is already added, ignore exception
            }
        }
    
    private String getUserDN(String username) {
        return new StringBuffer()
                .append("uid=")
                .append(username)
                .append(",")
                .append(USERS_OU)
                .toString();
    }
    
    private String getGroupDN(String name) {
        return new StringBuffer()
                .append("cn=")
                .append(name)
                .append(",")
                .append(GROUPS_OU)
                .toString();
    }
    -----------------------------BENDING----------------------*/
/*=====================================Login Validation Start Here=================================================================*/
    public static boolean active(String loginName, String password) {
    	  
    	  String sAMAloginName = null;
    	  boolean validate = false;
    	  
    	 //String auth_method  = "simple";
    	  String auth_method  = properties.getProperty("config.auth_method");
    	  
    	  
       // set the LDAP client Version
       //String ldap_version = "3";
    	  String ldap_version = properties.getProperty("config.ldap_version");
    	  
    	  
       // This is our LDAP Server's IP
//       String ldap_host    = "192.168.1.105";
       //String ldap_host    = "198.71.58.51";
       String ldap_host    = properties.getProperty("config.ldap_host");
       
       
       // This is our LDAP Server's Port
       //String ldap_port    = "389";
       String ldap_port    = properties.getProperty("config.ldap_port");
       
       
       // This is our access ID
       //String ldap_dn      = loginName+"@ELMT-DEVSERVER.COM";
       //String ldap_dn      = "USdev@u18643868.onlinehome-server";
       String ldap_dn      = properties.getProperty("config.ldap_domain_name");
       
       
//     String ldap_dn      = "Administrator@menschForce.com";

       // This is our access PW
       //String ldap_pw      = "digiBlitz@dev44#";
       String ldap_pw      = properties.getProperty("config.ldap_password");
//     String ldap_pw      = "dev1@digiBlitz0505#";

     // String ldap_pw      = password;
       // This is our base DN
       //String base_dn      = "DC=ELMT-DEVSERVER,DC=com";

     // DirContext ctx      = null;
      //Hashtable env       = new Hashtable();

       // Here we store the returned LDAP object data
       String dn           = "";
       // This will hold the returned attribute list
       Attributes attrs;

       env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
       env.put(Context.PROVIDER_URL,"ldap://" + ldap_host + ":" + ldap_port);
       env.put(Context.SECURITY_AUTHENTICATION, auth_method);
       env.put(Context.SECURITY_PRINCIPAL, ldap_dn);
       env.put(Context.SECURITY_CREDENTIALS, ldap_pw);
       env.put("java.naming.ldap.version", ldap_version);
    

        try{
      	  System.out.println("Connecting to host " + ldap_host + " at port " + ldap_port + "...");
            System.out.println();

            ctx = new InitialDirContext(env);
            System.out.println("LDAP authentication successful!");
            // Specify the attribute list to be returned
            //String[] attrIDs = { "memberOf","sn","givenname", "mail","telephonenumber","samaccountname"};
      	  String[] attrIDs = {"samaccountname"};
                                                               
            SearchControls ctls = new SearchControls();
            ctls.setReturningAttributes(attrIDs);
            ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);

            // Specify the search filter to match
            String filter = "(&(objectClass=user)(sAMAccountName="+loginName+"))";

            // Search the subtree for objects using the given filter
            NamingEnumeration answer = ctx.search(DOMAIN_ROOT, filter, ctls);

            // Print the answer
            //Search.printSearchEnumeration(answer);
      	 
       System.out.println("<-----------------------------PRABHU HERE----------------------------------->");
            while (answer.hasMoreElements()) {
      		  
              SearchResult sr = (SearchResult)answer.next();
              dn = sr.getName();
      		     
              attrs = sr.getAttributes();

              System.out.println("Found Object: " + dn + "," + DOMAIN_ROOT);
              if (attrs != null) {
                // we have some attributes for this object
                NamingEnumeration ae = attrs.getAll();
                while (ae.hasMoreElements()) {
                  Attribute attr = (Attribute)ae.next();
                  String attrId = attr.getID();
                  System.out.println("Found Attribute: " + attrId);
                  Enumeration vals = attr.getAll();
                  while (vals.hasMoreElements()) {
                	  sAMAloginName = (String)vals.nextElement();
                    System.out.println(attrId + ": " + sAMAloginName);
      			  //System.out.println("givenname "+ attrs.get("givenname"));

                  }
                }
              }
           }

            // Close the context when we're done
            ctx.close();
          } catch (AuthenticationException authEx) {
        	  authEx.printStackTrace();
          System.out.println("LDAP authentication failed!");
        } catch (NamingException namEx) {
          System.out.println("LDAP connection failed!");
          namEx.printStackTrace();
        } catch (Exception e) {
          e.printStackTrace();
       }
        System.out.println("sAMAloginName OUTSIDE THE LDAP : "+sAMAloginName);
        
        if((sAMAloginName != null) &&(sAMAloginName.equals(loginName))){
        	
        	validate = true;
        	
        }
        System.out.println("VALIDATING THE USERNAME : "+validate);
        return validate;
        
      }
/*=====================================Login Validation End Here=================================================================*/
    
/*=====================================Creating New Group Start Here=========================================================*/
    public boolean CreateNewGroup(String name, String description)
            throws NamingException {
    	try{
            // Create a container set of attributes
            Attributes container = new BasicAttributes();

            // Create the objectclass to add
            Attribute objClasses = new BasicAttribute("objectClass");
            objClasses.add("top");
            objClasses.add("group");

            // Assign the name and description to the group
            Attribute cn = new BasicAttribute("CN", name);
            Attribute desc = new BasicAttribute("description", description);

            // Add these to the container
            container.put(objClasses);
            container.put(cn);
            container.put(desc);
            System.out.println("PRABHU HERE : ");
            // Create the entry
            ctx.createSubcontext(getGroupDN(name), container);
            return true;
            
    	} catch (NameAlreadyBoundException e){
    		System.err.println("Group "+name+" was already Created in Active Directory");
            return true;
        } catch (NamingException e){
        	System.err.println("Problem adding user with the group "+e);
            return false;

        }
     }
    
    private String getGroupDN(String name) {
    	   
        return new StringBuffer()
                .append("CN=")
                .append(name)
                .append(",")
                .append(GROUPS_OU)
                .toString();
    }
/*=====================================Creating New Group End Here=========================================================*/
    
/*==========================================Destroying SubContext Start Here=================================================*/
    public boolean deleteGroup(String name)
            throws NamingException {
    	try{
        	ctx.destroySubcontext(getGroupDN(name));
       
        	System.out.println("Role deleted from Active Directory");
        	return true;
        } catch (NameNotFoundException e){
        	System.err.println("Role was not found in Active Directory "+e);
        	return true;
        } catch (NamingException e){
        	System.err.println("Problem was deleting Role fron Active Directory "+e);
        	return false;
        }
    }
/*==========================================Destroying SubContext End Here===================================================*/      
    
}

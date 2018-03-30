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
package com.user.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



public class SharePointEncrypt 
{
	
	static Logger log = Logger.getLogger(SharePointEncrypt.class.getName());
	
	public static final String LOG_FILE = "Log4j.properties";
	InputStream is;
	
	public SharePointEncrypt(){
		// ======================log file properties configuration started====================
	       Properties logProp = new Properties();  
		      is = (InputStream) getClass().getClassLoader().getResourceAsStream(LOG_FILE);
		      try {
				logProp.load(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		      PropertyConfigurator.configure(logProp);      
		      log.info("Logging enabled");  
		   // ======================log file properties configuration ended==================== 
	}
   public String encrypt(String line)
    {


       String keys = "28";
       
       
       StringBuilder result = new StringBuilder();

       char[] codeList = line.toCharArray();
       char[] keyList  = keys.toCharArray();

       int maxCount = keys.length();
       System.out.println("The length is "+maxCount);
       int i = 0;

        for (Character code : codeList) {

            int key = Character.getNumericValue(keyList[i]);

                if(key % 2 == 0)
                   {
                     int res = code+key;
                     result.append((char)res);
                   }
                   else
                   {
                      int res = code-key;
                     result.append((char)res);
                   }
                i++;
                if(i==maxCount)
                {
                   i = 0;
                }
            }
       String res=result.toString();
       
		return res;
} 
   public String decrypt(String line)
   {


      String keys = "28";
      
      
      StringBuilder result = new StringBuilder();

      char[] codeList = line.toCharArray();
      char[] keyList  = keys.toCharArray();

      int maxCount = keys.length();
      System.out.println("The length is "+maxCount);
      int i = 0;

       for (Character code : codeList) {

           int key = Character.getNumericValue(keyList[i]);

               if(key % 2 == 0)
                  {
                    int res = code-key;
                    result.append((char)res);
                  }
                  else
                  {
                     int res = code+key;
                    result.append((char)res);
                  }
               i++;
               if(i==maxCount)
               {
                  i = 0;
               }
           }
      String res=result.toString();
      
		return res;
} 
}

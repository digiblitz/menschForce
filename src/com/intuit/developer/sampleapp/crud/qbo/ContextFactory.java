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
package com.intuit.developer.sampleapp.crud.qbo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.security.OAuthAuthorizer;
import com.intuit.ipp.util.Logger;

/**
 * 
 * @author dderose
 *
 */

public class ContextFactory {

	private static final org.slf4j.Logger LOG = Logger.getLogger();

	private static final String companyID = "company.id";
	private static final String consumerKey = "consumer.key";
	private static final String consumerSecret = "consumer.secret";
	private static final String accessToken = "oauth.accessToken";
	private static final String accessTokenSecret = "oauth.accessTokenSecret";
	

	private static Properties prop;
	
	
	/**
	 * Initializes Context for a given app/company profile
	 * 
	 * @return
	 * @throws FMSException
	 */
	public static Context getContext() throws FMSException {
		
		try {
			loadProperties();
		} catch (IOException e) {
			LOG.error("Error while loading properties", e.getCause());
		}
		//create oauth object
		OAuthAuthorizer oauth = new OAuthAuthorizer(prop.getProperty(consumerKey), prop.getProperty(consumerSecret), prop.getProperty(accessToken), prop.getProperty(accessTokenSecret));
		//create context
		Context context = new Context(oauth, ServiceType.QBO, prop.getProperty(companyID));
		
		return context;
	}
	
	private static void loadProperties() throws IOException {
		 
		try {
			prop = new Properties();
			String propFileName = "config - QB.properties";
 
			InputStream inputStream = ContextFactory.class.getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			inputStream.close();
		} catch (Exception e) {
			LOG.error("Error during loadProperties", e.getCause());
		} 
	}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.model.api.DesignConfig;
import org.eclipse.birt.report.model.api.IDesignEngine;
import org.eclipse.birt.report.model.api.IDesignEngineFactory;
import javax.servlet.ServletContext;
import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.IPlatformContext;
import org.eclipse.birt.core.framework.PlatformServletContext;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;

import com.hlccommon.util.Debug;
import com.user.action.SharePointEncrypt;
/**
 *
 * @author parteek
 */
public class BIRTGraphsConfig {
	public static Properties properties; 
	static Logger log = Logger.getLogger(BIRTGraphsConfig.class.getName());
	
	public static final String LOG_FILE = "Log4j.properties";
	InputStream is;
	
	public BIRTGraphsConfig(){
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
    
    public static void initializeDesignConfig(ServletContext theApplicationsServletContext)
    {
       
        IPlatformContext context = new PlatformServletContext(theApplicationsServletContext);
		IReportEngine birtEngine=null;
		EngineConfig config = new EngineConfig();
                config.setEngineHome("");
                config.setPlatformContext(context);
                config.setPlatformContext( context );
                try {
            Platform.startup(config);
        } catch (BirtException e) {
        	e.printStackTrace();
            //logger.error(e.getMessage(), e);
        }

        IReportEngineFactory factory = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
        
       
        birtEngine = factory.createReportEngine(config);
        //System.out.println("Inside New");
    }
    public static void initializeDesignConfig()
    {
    	//=====================Properties file configuration syarted here==============================
    	properties = new Properties();        
        
        try {               
        	properties.load(BIRTGraphsConfig.class.getResourceAsStream("/config.properties"));
        	System.out.println("Property file loaded successfully");
            } catch (Exception e) {
                try {
                	properties.load(BIRTGraphsConfig.class.getResourceAsStream("/config.properties"));

                }catch(Exception ee) {
                   Debug.print("Could not load the companyDetails.properties");
                }
            }
        
      //=====================Properties file configuration ended here==============================
        DesignConfig config = new DesignConfig( );

		//config.setProperty("BIRT_HOME", "C:/birt-runtime-2_3_2_1/birt-runtime-2_3_2/ReportEngine");
        config.setProperty("BIRT_HOME", properties.getProperty("config.brit_home"));
		IDesignEngine engine = null;
		try{
					
			
		Platform.startup( config );
		IDesignEngineFactory factory = (IDesignEngineFactory) Platform
		.createFactoryObject( IDesignEngineFactory.EXTENSION_DESIGN_ENGINE_FACTORY );
		engine = factory.createDesignEngine( config );

		}catch( Exception ex){
			ex.printStackTrace();
		}
    }

}

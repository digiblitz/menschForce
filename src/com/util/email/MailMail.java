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
package com.util.email;
//testing
import com.hlccommon.util.Debug;
import com.user.action.KlgUserAction;

import java.util.Date;
import java.util.Properties;
import org.apache.commons.mail.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailSender;  
import org.springframework.mail.SimpleMailMessage;  
   
public final class MailMail{  
    private MailSender mailSender;  
    private final Properties properties; 
    
    static Logger log = Logger.getLogger(MailMail.class.getName());
	
	public static final String LOG_FILE = "Log4j.properties";
	InputStream is;
   
    public MailMail()  {  
    	   
  
     /** Creates a new instance of EmailEngine */
    properties = new Properties();        
    //String fileName = System.getProperty("user.home")+File.separator+"mail.properties";
    //Debug.print("FileName path:"+fileName);
   //InputStream in = this.getClass().getClassLoader().getResourceAsStream("email.properties");
    try {               
            //properties.load(in);
    	properties.put("mail.smtp.host", "mail.menschforce.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); //TLS
        properties.put("mail.username","jeyaprakash.sankarraj@menschforce.com");
        properties.put("mail.password","J@9865789881p");
        properties.put("mail.smtp.ssl.trust", "mail.menschforce.com");
        
        } catch (Exception e) {
            try {
              properties.load(new FileInputStream("mail.properties"));
            }catch(Exception ee) {
               Debug.print("Could not load the mail.properties");
               properties.put("mail.smtp.host", "mail.menschforce.com");
               properties.put("mail.smtp.port", "465");
               properties.put("mail.smtp.auth", "true");
               properties.put("mail.smtp.starttls.enable", "true"); //TLS
               properties.put("mail.username","jeyaprakash.sankarraj@menschforce.com");
               properties.put("mail.password","J@9865789881p");
               properties.put("mail.smtp.ssl.trust", "mail.menschforce.com");
            }
        }
    
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
    
    
    
    public void setMailSender(MailSender mailSender) {  
        this.mailSender = mailSender;  
    }  
   
    public boolean sendMail(String emailid) {  
    	
    	  final String username= properties.getProperty("mail.username");
    	  final String password = properties.getProperty("mail.password");
    	  /*properties.getProperty("mail.host");
    	  properties.getProperty("mail.port");
    	  properties.getProperty("mail.smtp.starttls.enable");
    	  properties.getProperty("mail.smtp.auth");
    	  properties.getProperty("mail.transport.protocol");*/
    	 
    	  Session session = Session.getInstance(properties,
    	    new javax.mail.Authenticator() {
    	   protected PasswordAuthentication getPasswordAuthentication() {
    	    return new PasswordAuthentication(username, password);
    	   }
    	    });
    	 
    	  try {
    	 
    	   Message message = new MimeMessage(session);
    	   message.setFrom(new InternetAddress("from@menschforce.com"));
           String email=emailid;

    	   message.setRecipients(Message.RecipientType.TO,
    	    InternetAddress.parse(email));
    	   message.setSubject("Your Account Details");
    	   message.setText("Dear");
    	 
    	   Transport.send(message);
    	 
    	   System.out.println("Done");
    	   return true;
    	  } catch (MessagingException e) {
    	   throw new RuntimeException(e);
    	  }
    }
    
    /*
     * Dhivya Here: Mail Send
     * 
     */
    public boolean sendMimeEmail(EmailContent emailContent)  {    
        boolean success = false;
        String hostName = properties.getProperty("mail.smtp.host");
        final  String userName = properties.getProperty("mail.username");
        final  String passWord = properties.getProperty("mail.password"); 
         Debug.print("Mail Configuration :\n"+"HostName :"+hostName+" " +
            "UserName:"+userName+" Password:"+passWord); 
       
         Session session = Session.getInstance(properties,
      			  new javax.mail.Authenticator() {
      				protected PasswordAuthentication getPasswordAuthentication() {
      					return new PasswordAuthentication(userName, passWord);
      				}
      			  });
         
       
         Message email = new MimeMessage(session); 
         
         
          try {
           
              for (int i = 0; i < emailContent.getTo().length; i++) {
              	System.out.println("emailContent.getTo()"+emailContent.getTo());
                  email.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(emailContent.getTo()[i]));
              }                   
              email.setFrom(new InternetAddress(emailContent.getFrom()));
              email.setSubject(emailContent.getSubject());
              email.setSentDate(new Date());
              
              setHTMLContent(email,emailContent.getBody());
            
            // email.setText("Your email client does not support HTML messages");
              
              Transport.send(email);
              
            
              success = true;
           } catch (MessagingException ex) {
              ex.printStackTrace();           
          }

         return success;
      }  
    
    

    // Set a single part html content.
       // Sending data of any type is similar.
       public static void setHTMLContent(Message msg, String content) throws MessagingException {
    
           // HTMLDataSource is a static nested class
           msg.setDataHandler(new DataHandler(new HTMLDataSource(content)));
       }
    
    
       /*
        * Static nested class to act as a JAF datasource to send HTML e-mail content
        */
       static class HTMLDataSource implements DataSource {
           private String html;
    
           public HTMLDataSource(String htmlString) {
               html = htmlString;
           }
    
           // Return html string in an InputStream.
           // A new stream must be returned each time.
           public InputStream getInputStream() throws IOException {
               if (html == null) throw new IOException("Null HTML");
               return new ByteArrayInputStream(html.getBytes());
           }
    
           public OutputStream getOutputStream() throws IOException {
               throw new IOException("This DataHandler cannot write HTML");
           }
    
           public String getContentType() {
               return "text/html";
           }
    
           public String getName() {
               return "JAF text/html dataSource to send e-mail only";
           }
       }
       
       public static void main(String[] args) throws Exception{
    	   MailMail mail = new MailMail();
    	   //mail.sendMail("jprakazjp@gmail.com");
    	   
    	   String htmlBody = "<html><body><div style=\" background-color: #d8dde4;  padding: 32px 10px;text-align: center!important;\"><div style=\"max-width: 580px; text-align: center;margin: 0 auto;width: 100%; display: inline-block;" +
                   "text-align: center;vertical-align: top; width: 100%;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr><td align=\"center\" valign=\"top\" style=\" background-color: #2f68b4;border-radius: 4px 4px 0 0;padding-bottom: 16px; text-align: center;\">" +
                   "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"padding-top: 16px;\"><a href=\"#\"><img style=\"height: auto; max-width: 156px;\" src=\"https://www.digiblitzonline.com:8843/menschforce/img/menschForce_logo.png\" alt=\"Logo\"/></a></td>" +
                   " </tr></tbody></table></td></tr></tbody></table><div ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"background-color: #fff;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>" +
                   "<tr><td align=\"center\" valign=\"top\" style=\"  padding: 16px;text-align: center; vertical-align: top;\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Congratulations "+"Test"+" "+"User"+"</h4><p style=\"   font-size: 19px;" +
                     "line-height: 27px; margin-bottom: 16px;margin-top: 16px; text-align: center;\">You are registered Successfully... </p><table style=\" clear: both; margin: 0 auto;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
                     "<tbody> <tr> <td style=\"border-radius: 4px;padding: 12px 24px;text-align: center;vertical-align: middle;background-color: #22aaa0; font-size: 16px;\" class=\"font_default\"><h4 style=\"font-size: 22px;font-weight: 700;line-height: 30px; margin: 16px 0 8px;padding: 0;color: #383d42;text-align: center;\">Registration Details</h4></td>" +
                     "</tr> </tbody> </table> <p>&nbsp; </p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody> <tr> <td align=\"center\" valign=\"top\" style=\"background-color: #fff;  padding: 16px; text-align: center; vertical-align: top;\">" +
                     "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody> <tr> <td style=\"color: #888;\" align=\"center\" valign=\"top\" style=\" padding: 16px; text-align: center; vertical-align: top;\">" +
                     " <p style=\" line-height: 23px; margin-bottom: 24px; margin-top: 16px;font-size: 15px;\">" +
						"<strong>E-mail ID :</strong><br /> "+"test@menschorce.com"+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
						"<strong>Visit the Site :</strong><br /> <a href=\"http://www.menschforce.com\" target=\"_blank\">www.menschforce.com</a></p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
                     "<strong>User Name :</strong><br /> "+"test"+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
                     "<strong>Password :</strong><br /> "+"testpassword"+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
                     "<strong>"+"Buyer"+" Id :</strong><br /> "+"digiblitz"+"</p><p style=\" line-height: 23px;margin-bottom: 24px; margin-top: 16px;font-size: 15px;\"> " +
                     "</td> </tr> </tbody></table></td></tr></tbody></table></div></div></div></div></body></html>";
    	   EmailContent email = new EmailContent();
    	   String toMailIds[] = {"jprakazjp@gmail.com"};
           email.setTo(toMailIds);
           email.setFrom("crm@menschforce.com");
           email.setSubject("Test Menschforce Mail");
           email.setBody(htmlBody);
           mail.sendMimeEmail(email);
       }


}






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
package com.db;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.sql.Connection;
//import java.sql.Date;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.jasypt.util.text.BasicTextEncryptor;

//import com.hlccommon.util.DBHelper;
//import com.hlccommon.util.Debug;
import com.encryption.CEncrypt;
import com.hlccommon.util.HLCMenuListVO;
import com.hlcform.user.HLCArabianSeaEntityUserLocal;
import com.hlcform.util.HLCContactDetails;
import com.hlcform.util.HLCMemberUpdateDAO;
import com.hlcform.util.HLCPassword;
import com.hlcform.util.HLCUserMaster;
import com.hlcform.util.DBHelper;
import com.hlcform.util.Debug;
import com.intuit.ipp.data.PhysicalAddress;
import com.intuit.ipp.data.TelephoneNumber;
import com.jobvacancy.apply.MFAppliedCandidateBean;
import com.jobvacancy.contacts.ClientContactDetailsBean;
import com.jobvacancy.contacts.VendorContactDetailsBean;
import com.jobvacancy.requirements.PostRequirementsBean;
import com.lowagie.toolbox.plugins.Split;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class GeneralDBAction {
	
	
	public static final String dbName = "java:/HLCMSSQLDS";
	public static final String mySqldbName = "java:/HLCMYSQLDS";
    public Connection con = null;
    public PreparedStatement prepStmt = null;
    public ResultSet rs = null;
	
    HLCArabianSeaEntityUserLocal remote;
    
//    static Logger log = Logger.getLogger(GeneralDBAction.class.getName());
//	
//	public static final String LOG_FILE = "Log4j.properties";
//	InputStream is;
//	
//	public GeneralDBAction(){
//		Properties logProp = new Properties();  
//	      is = (InputStream) getClass().getClassLoader().getResourceAsStream(LOG_FILE);
//	      try {
//			logProp.load(is);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//	      PropertyConfigurator.configure(logProp);      
//	      log.info("Logging enabled");  
//	}
//    
    

public void makeConnection() {
    try {
        InitialContext ic = new InitialContext();
        DataSource ds = (DataSource) ic.lookup(dbName);
        con = ds.getConnection();
        Debug.print(" Opening a connection...");
    } catch (Exception ex) {
        Debug.print("Unable to connect to database. " + ex.getMessage());
    }
}

public void releaseConnection() {
    try {
        //prepStmt.close();
        //rs.close();
        if(!con.isClosed()){
            con.close();
        }
        Debug.print(" Closing a connection...");
    } catch (SQLException ex) {
        Debug.print("releaseConnection: " + ex.getMessage());
    }

}

/*---------------------------mysql connection started here------------------------------*/
public void makeMySqlConnection() {
    try {
        InitialContext ic = new InitialContext();
        DataSource ds = (DataSource) ic.lookup(mySqldbName);
        con = ds.getConnection();
        // Debug.print(" Opening a connection...");
    } catch (Exception ex) {
        //Debug.print("Unable to connect to database. " + ex.getMessage());
    }
}


    public int totalMessageCount(String toUserId) {
        Debug.print("GeneralDBAction.totalMessageCount():" + toUserId);
        int count = 0;
     
        makeConnection();
        try {
            String selectStatement = " SELECT count(*) from tblMessages where view_status = 0 and to_user_id= ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, toUserId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("GeneralDBAction.totalMessageCount():" + count);
        } catch (SQLException sql) {
            releaseConnection();
            Debug.print("SQL Exception in GeneralDBAction.totalMessageCount():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection();
            Debug.print("General Exception  in GeneralDBAction.totalMessageCount():" + e.getMessage());
        }
        return count;
    }
	
    public ArrayList getAllRolesBasedOnUser(String userId){
        Debug.print("GeneralDBAction.getAllRolesBasedOnUser():");
        ArrayList rolesList = null;
       
        makeConnection();
   	try {
            String selectStatement = " SELECT A.user_map_id, A.user_id, A.role_id, B.role_name from tblMapUserPrivilege A, tblRoleMaster B  where A.role_id = B.role_id and A.user_id = ? order by B.role_name" ;
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,userId);
            rs = prepStmt.executeQuery();
            rolesList = new ArrayList();
            while(rs.next()){
                String userMapId = rs.getString(1);
                String userIdVal = rs.getString(2);
                String roleId = rs.getString(3);
                String roleName = rs.getString(4);
                String[] rlList = {userMapId, userIdVal, roleId, roleName};
                rolesList.add(rlList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
           // Debug.print("RoleManagementDAOImpl.selectAllRolesBasedOnUser():" + rolesList);
        } 
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in GeneralDBAction.getAllRolesBasedOnUser():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in GeneralDBAction.getAllRolesBasedOnUser():" + e.getMessage());
        }
        return rolesList;
      }
    
    public ArrayList getMappingDetailsForRoleAndPrivileges(String roleId){
        Debug.print("GeneralDBAction.getMappingDetailsForRoleAndPrivileges():");
        ArrayList roleEntityMapList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
            String selectStatement=" SELECT a.map_entity_id , a.role_id ,a.entity_id, b.role_name, c.entity_name,c.entity_access_url from tblMapEntity a , " +
            		"tblRoleMaster b , tblEntityMaster c " +
                    " where a.entity_id = c.entity_id and a.role_id = b.role_id and a.role_id = ? order by c.entity_name";
         
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,roleId);
            Debug.print(selectStatement);
            rs = prepStmt.executeQuery();
            roleEntityMapList = new ArrayList();
            while(rs.next()){
                String mapEntityId = rs.getString(1);
                String roleIdVal = rs.getString(2);
                String entityId = rs.getString(3);
                String roleName = rs.getString(4);
                String entityName = rs.getString(5);
                String entityUrl = rs.getString(6);
                String[] entMapList = {mapEntityId, roleIdVal, entityId, roleName, entityName,entityUrl};
                roleEntityMapList.add(entMapList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            //Debug.print("RoleManagementDAOImpl.selectAllMappingDetailsForRole():" + roleEntityMapList);
        } 
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in GeneralDBAction.getMappingDetailsForRoleAndPrivileges():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in GeneralDBAction.getMappingDetailsForRoleAndPrivileges():" + e.getMessage());
        }
        return roleEntityMapList;
    }
    
 
    public String [] getRole(String roleId) {
        Debug.print("GeneralDBAction.getRole():");
       
        String[] strRoleList = {};
        makeConnection();
   	try {

            String selectStatement=" select case when role_id in(select role_id from tblMapEntity union all select role_id from tblMapUserPrivilege"+
                                   " union all select role_id from tblMapRole) then '0' else '1' end flag, "+
                                   " role_id, role_name,role_desc,active_status,role_vms_dashboard from tblRoleMaster where role_id = ? ";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,roleId);
            rs = prepStmt.executeQuery();
            if(rs.next()){
                String roleIdVal = rs.getString(2);
                String roleName = rs.getString(3);

                String roledesc = rs.getString("role_desc");
                String status = rs.getString("active_status");
                String flag = rs.getString("flag");
                String vmsDashboard = rs.getString("role_vms_dashboard");

                String tempStrRoleList[] = {roleIdVal, roleName,roledesc,status,flag,vmsDashboard};
                strRoleList = tempStrRoleList;
            }
  
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("GeneralDBAction.getRole():" + strRoleList);
        } 
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in GeneralDBAction.getRole():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in GeneralDBAction.getRole():" + e.getMessage());
        }
        return strRoleList;
    }   
    
    public ArrayList getMappingDetailsForRlEntPrivPerm(String roleId, String entId){
        Debug.print("GeneralDBAction.getMappingDetailsForRlEntPriv():");
        ArrayList roleEntityMapList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
            String selectStatement="SELECT distinct a.map_entity_id , a.role_id ,a.entity_id, b.role_name, " +
            		"c.entity_name, c.entity_access_url, d.privilege_name, " +
            		"e.privilege_id, f.access_name,f.access_des, f.access_url " +
            		"from tblMapEntity a , tblRoleMaster b , tblEntityMaster c, " +
            		"tblPrivilegeMaster d, tblMapRole e, tblMapPermission f " +
            		"where a.entity_id = c.entity_id and a.role_id = b.role_id and " +
            		"f.permission_id=e.permission_id and f.privilege_id=e.privilege_id and " +
            		"d.privilege_id=e.privilege_id and d.privilege_id=f.privilege_id " +
            		"and a.entity_id=c.entity_id and a.entity_id=e.entity_id " +
            		"and c.entity_id=e.entity_id and a.role_id = e.role_id and b.role_id = e.role_id " +
            		"and e.role_id =? and e.entity_id=? order by c.entity_name";
            
         
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,roleId);
            prepStmt.setString(2,entId);
            Debug.print(selectStatement);
            rs = prepStmt.executeQuery();
            roleEntityMapList = new ArrayList();
            while(rs.next()){
                String mapEntityId = rs.getString(1);
                String roleIdVal = rs.getString(2);
                String entityId = rs.getString(3);      
               String roleName = rs.getString(4);
               String entityName = rs.getString(5);
               String entityUrl = rs.getString(6);
              String privName= rs.getString(7);
               String priviId = rs.getString(8);
              String accessName = rs.getString(9);
              String accessDes = rs.getString(10);
              String accessUrl = rs.getString(11);
              
            
               String[] entMapList = {mapEntityId, roleIdVal, entityId, roleName, entityName,entityUrl,privName,priviId,accessName,accessDes,accessUrl};
               roleEntityMapList.add(entMapList);    
      
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            //Debug.print("RoleManagementDAOImpl.selectAllMappingDetailsForRole():" + roleEntityMapList);
        } 
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in GeneralDBAction.getMappingDetailsForRlEntPriv():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in GeneralDBAction.getMappingDetailsForRlEntPriv():" + e.getMessage());
        }
        return roleEntityMapList;
    }   
    
    
    public ArrayList getMappingDetailsForRlEnt(String roleId){
        Debug.print("GeneralDBAction.getMappingDetailsForRlEnt():");
        ArrayList roleEntityMapList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
          /*String selectStatement=" SELECT distinct a.map_entity_id , a.role_id ,a.entity_id, b.role_name, c.entity_name, " +
            		"c.entity_access_url, d.privilege_name, e.privilege_id from tblMapEntity a , " +
            		"tblRoleMaster b , tblEntityMaster c, tblPrivilegeMaster d, " +
            		"tblMapRole e where a.entity_id = c.entity_id and a.role_id = b.role_id and " +
            		"d.privilege_id=e.privilege_id and a.entity_id=c.entity_id and " +
            		"a.entity_id=e.entity_id and c.entity_id=e.entity_id and " +
            		"a.role_id = e.role_id and b.role_id = e.role_id " +
            		"and e.role_id =? and e.entity_id=? order by c.entity_name";*/
   		
   	String selectStatement="SELECT distinct a.map_entity_id , a.role_id ,a.entity_id, b.role_name, c.entity_name, " +
        		"c.entity_access_url,c.entity_des from tblMapEntity a, tblRoleMaster b , tblEntityMaster " +
        		"c where a.entity_id = c.entity_id and a.role_id = b.role_id and " +
        		"a.entity_id=c.entity_id and a.role_id =? order by c.entity_name";
         
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,roleId);
           
            Debug.print(selectStatement);
            rs = prepStmt.executeQuery();
            roleEntityMapList = new ArrayList();
            while(rs.next()){
                String mapEntityId = rs.getString(1);
                String roleIdVal = rs.getString(2);
                String entityId = rs.getString(3);      
               String roleName = rs.getString(4);
               String entityName = rs.getString(5);
               String entityUrl = rs.getString(6);
               String entityDes = rs.getString(7);
            // String privName= rs.getString(7);
          // String priviId = rs.getString(8);
             
               String[] entMapList = {mapEntityId, roleIdVal, entityId, roleName, entityName,entityUrl,entityDes};
               roleEntityMapList.add(entMapList);    
      
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
          
        } 
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in GeneralDBAction.getMappingDetailsForRlEnt():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in GeneralDBAction.getMappingDetailsForRlEnt():" + e.getMessage());
        }
        return roleEntityMapList;
    }  
    
    
    public ArrayList getMappingDetailsForRlEntPriv(String roleId, String entId){
        Debug.print("GeneralDBAction.getMappingDetailsForRlEntPriv():");
        ArrayList roleEntityMapList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
          String selectStatement=" SELECT distinct a.map_entity_id , a.role_id ,a.entity_id, b.role_name, c.entity_name, " +
            		"c.entity_access_url, d.privilege_name, e.privilege_id from tblMapEntity a , " +
            		"tblRoleMaster b , tblEntityMaster c, tblPrivilegeMaster d, " +
            		"tblMapRole e where a.entity_id = c.entity_id and a.role_id = b.role_id and " +
            		"d.privilege_id=e.privilege_id and a.entity_id=c.entity_id and " +
            		"a.entity_id=e.entity_id and c.entity_id=e.entity_id and " +
            		"a.role_id = e.role_id and b.role_id = e.role_id " +
            		"and e.role_id =? and e.entity_id=? order by c.entity_name";
   		
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,roleId);
            prepStmt.setString(2,entId);
            Debug.print(selectStatement);
            rs = prepStmt.executeQuery();
            roleEntityMapList = new ArrayList();
            while(rs.next()){
                String mapEntityId = rs.getString(1);
                String roleIdVal = rs.getString(2);
                String entityId = rs.getString(3);      
               String roleName = rs.getString(4);
               String entityName = rs.getString(5);
               String entityUrl = rs.getString(6);
            String privName= rs.getString(7);
          String priviId = rs.getString(8);
             
               String[] entMapList = {mapEntityId, roleIdVal, entityId, roleName, entityName,entityUrl,privName,priviId};
               roleEntityMapList.add(entMapList);    
      
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
          
        } 
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in GeneralDBAction.getMappingDetailsForRlEntPriv():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in GeneralDBAction.getMappingDetailsForRlEntPriv():" + e.getMessage());
        }
        return roleEntityMapList;
    }   
    
    
  
    
    
    public ArrayList getMappingDetailsForPrivPerm(String roleId){
        Debug.print("GeneralDBAction.getMappingDetailsForPrivPerm():");
        ArrayList roleEntityMapList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
          String selectStatement=" SELECT distinct a.map_entity_id , a.role_id ,a.entity_id, b.role_name, c.entity_name, " +
            		"c.entity_access_url, d.privilege_name, e.privilege_id from tblMapEntity a , " +
            		"tblRoleMaster b , tblEntityMaster c, tblPrivilegeMaster d, " +
            		"tblMapRole e where a.entity_id = c.entity_id and a.role_id = b.role_id and " +
            		"d.privilege_id=e.privilege_id and a.entity_id=c.entity_id and " +
            		"a.entity_id=e.entity_id and c.entity_id=e.entity_id and " +
            		"a.role_id = e.role_id and b.role_id = e.role_id " +
            		"and e.role_id =? order by c.entity_name";   		
   	   /*String selectStatement="SELECT distinct a.map_entity_id , a.role_id ,a.entity_id, b.role_name, c.entity_name, " +
        		"c.entity_access_url from tblMapEntity a, tblRoleMaster b , tblEntityMaster " +
        		"c where a.entity_id = c.entity_id and a.role_id = b.role_id and " +
        		"a.entity_id=c.entity_id and a.role_id =? order by c.entity_name";*/
         
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,roleId);
            Debug.print(selectStatement);
            rs = prepStmt.executeQuery();
            roleEntityMapList = new ArrayList();
            while(rs.next()){
                String mapEntityId = rs.getString(1);
                String roleIdVal = rs.getString(2);
                String entityId = rs.getString(3);      
               String roleName = rs.getString(4);
               String entityName = rs.getString(5);
               String entityUrl = rs.getString(6);
              String privName= rs.getString(7);
             String priviId = rs.getString(8);
             
               String[] entMapList = {mapEntityId, roleIdVal, entityId, roleName, entityName,entityUrl,privName,priviId};
               roleEntityMapList.add(entMapList);    
      
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            //Debug.print("RoleManagementDAOImpl.selectAllMappingDetailsForRole():" + roleEntityMapList);
        } 
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in GeneralDBAction.getMappingDetailsForPrivPerm():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in GeneralDBAction.getMappingDetailsForPrivPerm():" + e.getMessage());
        }
        return roleEntityMapList;
    }  
  
//====================Edit User Details=====================================================
    private static String userId;
    private String contactTypeId = null;
    public void editUserDetails(HLCUserMaster objUserMaster, HLCContactDetails objContact) throws RemoteException{
        System.out.print("Email ID In Kavery Session Bean : "+objUserMaster.getEmailId());
        boolean bol = false;
        String commAddrId="";
        
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        if (objUserMaster == null && (objUserMaster.getEmailId()).equals("") ) {
            throw new EJBException("Email ID can't be empty");
        }
        
        makeConnection();
        Debug.print(" USer Id in Kavery Session Bean : "+objUserMaster.getUserId());
        if (objUserMaster.getUserId() == null) {
            try {
                userId = DBHelper.getUserId(con, objUserMaster.getEmailId());
                Debug.print(" User Id Based on email Id in Kavery Session Bean : "+userId);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            this.userId = objUserMaster.getUserId();
        }
        
        if (objContact.getContactType() != null) {
            try {
                contactTypeId = DBHelper.getContacttypeId(con,objContact.getContactType());
                commAddrId = DBHelper.getContacttypeId(con,objUserMaster.getCommunicationAddress());
                Debug.print("commAddrId for comm name - "+objUserMaster.getCommunicationAddress()+" :" +commAddrId);
                objUserMaster.setCommunicationAddress(commAddrId);
                
                bol = objDAO.isContactTypeExist(contactTypeId,userId);
                Debug.print(" contact type Exist in Kavery Session Bean: "+bol);
                Debug.print(" Contact Details Received From JSP: \n"+objContact);
                
                if (bol == false){
                    objContact.setContactTypeId(contactTypeId);
                    insertRowContactDetails(objUserMaster,objContact,contactTypeId);
                    Debug.print(" New Record added to the contact Details");
                    Debug.print(" Contact Details Received From JSP After insertion: \n"+objContact);
                    releaseConnection();
                    return;
                    
                }else{
                	objContact.setContactTypeId(contactTypeId);
                	updateContactDetails( objUserMaster,  objContact,  contactTypeId);
                	Debug.print("Update record to the contact Details");
                }
                
            }
           
            catch (Exception e){
                releaseConnection();
                e.printStackTrace();
            }
        }
        
        releaseConnection();
        
       
        
        if (memberExists(userId,objUserMaster.getEmailId()) == false) {
            throw new EJBException("Email ID Not Exists : " + userId);
        }
        
        Debug.print("objUserMaster.getLoginName() :"+objUserMaster.getLoginName());
        Debug.print("objUserMaster.getEmailId() :"+objUserMaster.getEmailId());
        
      
    }
    
   
    /**====================Insert for Contact Details==============================*/
    private void insertRowContactDetails(HLCUserMaster objUserMaster, HLCContactDetails objContact, String contactTypeId) throws SQLException {
        Debug.print("ArabianSeaEntityBean insertRowContactDetails");

        makeConnection();
        
        if (objUserMaster.getLoginName()!= null){
            try {
                userId = DBHelper.getUserId(con, objUserMaster.getLoginName());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.print("User Id is from insertRowContactDetails: "+userId);
        String insertStatement = "insert into tblContactDetails (contact_type_id,user_id,suite,address1,"+
            "address2,city,state,country,zip,phone_no, mobile_no, fax_no)" +
                " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?) ";
        prepStmt = con.prepareStatement(insertStatement);
        System.out.println("Inside the Contact Details ....\n\n ");
        prepStmt.setString(1, contactTypeId);
        System.out.print("contactTypeId: "+contactTypeId);
        prepStmt.setString(2, userId);
        System.out.print("userId: "+userId);
        prepStmt.setString(3, objContact.getSuite());
        System.out.print("suite: "+objContact.getSuite());
        prepStmt.setString(4, objContact.getAddress1());
        prepStmt.setString(5, objContact.getAddress2());
        prepStmt.setString(6, objContact.getCity());
        prepStmt.setString(7, objContact.getState());
        prepStmt.setString(8, objContact.getCountry());
        prepStmt.setString(9, objContact.getZip());
        prepStmt.setString(10, objContact.getPhoneNo());
        System.out.print("phoneNo: "+objContact.getPhoneNo());
        prepStmt.setString(11, objContact.getMobileNo());
        System.out.print("mobileNo: "+objContact.getMobileNo());
        prepStmt.setString(12, objContact.getFaxNo());
        System.out.print("faxNo: "+objContact.getFaxNo());
        int cnt = prepStmt.executeUpdate();
        System.out.print("Record Inserted succefully  "+cnt);
        Debug.print(" Insert Contact Detail Data : \n"+objContact);
        prepStmt.close();
        releaseConnection();
    }
    
    
    String mailId;
    private boolean memberExists(String userId, String emailId) {
        Debug.print("Kavery Session Bean memberExists");
        
        // if ( !(userId.equals(this.userId)) ) {
        try {
            boolean res = (boolean)selectByPrimaryKey(userId);
            mailId = emailId;
        } catch (Exception ex) {
            return false;
        }
        // }
        return true;
    } 
    
    private boolean selectByPrimaryKey(String userId) throws SQLException {
        Debug.print("ArabianSeaEntityBean selectByPrimaryKey");

        makeConnection();
        String selectStatement = "SELECT user_id from tblUserMaster WHERE user_id = ?";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, userId);
        ResultSet rs = prepStmt.executeQuery();
        System.out.println("selectByPrimaryKey : Working ");
        boolean result = rs.next();
        prepStmt.close();
        releaseConnection();
        return true;
    }  
    
    
    
    public boolean changePasword(String userId,String pass) throws Exception {
        Debug.print("GeneralDBAction changePasword");
        int cnt = 0;
        String encryptPassword=null;
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("sa");
        try {
            makeConnection();
            
            if (  (pass != null && pass.trim().length() > 0) &&
                    ( userId != null && userId.trim().length() > 0 )) {
            	
            	
            	encryptPassword=textEncryptor.encrypt(pass);	
            	
                String str = "update  tblUserMaster set password = ?  WHERE user_id = ?";
                PreparedStatement prepStmt = con.prepareStatement(str);
                prepStmt.setString(1, encryptPassword);
                prepStmt.setString(2, userId);
             
                cnt = prepStmt.executeUpdate();
              
                Debug.print("Successfully Password Changed......"+cnt);
                prepStmt.close();
            }
        }catch (Exception e){
           
            releaseConnection();
            Debug.print("Error while change password  : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        if (cnt >0)
            return true;
        else
            return false;
    }
    
    
    public String addUserRegistration(HLCUserMaster objUserMaster) throws RemoteException{
       
        String contactTypeId = null;
        String usrId = null;
        System.out.println("objUserMaster.getCommunicationAddress() inside the addUserRegistration"+objUserMaster.getCommunicationAddress());
        try{
             contactTypeId = (String)getDetFrmContyId(objUserMaster.getCommunicationAddress());
            
            objUserMaster.setContactTypeId(contactTypeId);
            Debug.print("Contant Of objUserMaster : "+objUserMaster);
            
            Debug.print("user Registration getNextUserId");
            makeConnection();
            
            try{
                String selectStatement = "select newid() as userId";
                Debug.print("GeneralDBAction getNextUserId:" + selectStatement);
                PreparedStatement prepSelect = con.prepareStatement(selectStatement);
                ResultSet rs = prepSelect.executeQuery();
                rs.next();
                usrId = rs.getString(1);
                rs.close();
                prepSelect.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in getNextUserId:" + sql.getMessage());
            }
            Debug.print("getNextUserId :" + usrId);
            
            objUserMaster.setUserId(usrId);
            insertRowUserMaster(objUserMaster);
        } catch(Exception exp){
        	exp.printStackTrace();
        }
        
        return usrId;
    }
    
    
    
   
    
    
    private void insertRowUserMaster(HLCUserMaster objUserMaster) throws SQLException {
        Debug.print("ArabianSeaEntityBean insertRowUserMaster");
        java.sql.Date dt = null;
       String userTypeId="";
     if(objUserMaster.getDob()!=null)
        {
            dt = java.sql.Date.valueOf(objUserMaster.getDob());
        }
        Debug.print("After converting DOB");
       
        
         makeConnection();
        Debug.print("USer code : "+objUserMaster.getUserCode());
        String str = "SELECT user_type_id FROM tblUserTypeMaster where user_type_name = ?";
        prepStmt = con.prepareStatement(str);
        prepStmt.setString(1, "Human".trim());
        rs = prepStmt.executeQuery();
        if (rs.next()) {
            userTypeId = rs.getString(1);
        } 
        
        rs.close();
        
        if (objUserMaster.getUserTypeName()!= null){
            str = "SELECT user_type_id FROM tblUserTypeMaster where user_type_name = ?";
            prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, objUserMaster.getUserTypeName());
            rs = prepStmt.executeQuery();
            if (rs.next()) {
            	userTypeId = rs.getString(1);
            } 
            rs.close();
        }
        
        
        if (objUserMaster.getUserTypeName().startsWith("USEA Staff")){
            String password = HLCPassword.getPassword();
        }
        
        //=============================Checking for apostopies in the string and removing==========================
        
        String secQes=objUserMaster.getSecretQuestion();
        String s1 = secQes;
        String s2 = "'";
        String s3 = "";
        String str1 = null;
        Debug.print(" Replace String : "+s1);
        if (s1 != null && s1.trim().length() > 0) {
            str1 = DBHelper.replace(s1,s2,s3);
            Debug.print(" Replace String : "+str1);
            int index = s1.indexOf("'");    
            if (index >= 0){
            	secQes = str1;
            }
        }
        Debug.print(" Final String : "+secQes);  
        
        
        
        String insertStatement = "insert into tblusermaster (contact_type_id,user_type_id,prefix,first_name,middle_name,"+
                "last_name,sufix,dob,gender,email_id, password, secret_question, secret_answer,user_code,login_name,non_usea_mailing_status,non_usea_email_status,user_id)" +
                    " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?,?,?,?,?,?) ";

        prepStmt = con.prepareStatement(insertStatement);
        System.out.println("Inside the User Master ....\n\n ");
       // prepStmt.setString(1, membershipTypeId);
       //System.out.println("membershipTypeId : "+membershipTypeId);
        
        prepStmt.setString(1, objUserMaster.getContactTypeId());
        System.out.println("contactTypeId :  "+contactTypeId);
        prepStmt.setString(2, userTypeId);
        System.out.println("userTypeId :  "+userTypeId);
        prepStmt.setString(3, objUserMaster.getPrefix());
         System.out.println("prefix :  "+objUserMaster.getPrefix());
        prepStmt.setString(4, objUserMaster.getFirstName());
         System.out.println("firstName :  "+objUserMaster.getFirstName());
        prepStmt.setString(5, objUserMaster.getMiddleName());
        prepStmt.setString(6, objUserMaster.getLastName());
        prepStmt.setString(7, objUserMaster.getSufix());
       prepStmt.setDate(8, dt);
	   
         System.out.println("Date :  "+dt);
        prepStmt.setString(9, objUserMaster.getGender());
        prepStmt.setString(10, objUserMaster.getEmailId());
        System.out.println("emailId :  "+objUserMaster.getEmailId());
        prepStmt.setString(11, objUserMaster.getPassword());
        prepStmt.setString(12, secQes);
        System.out.println("secretQuestion :  "+secQes);
        prepStmt.setString(13, objUserMaster.getSecretAnswer());
        System.out.println("secretAnswer :  "+objUserMaster.getSecretAnswer());
        prepStmt.setString(14, objUserMaster.getUserCode());
        System.out.println("userCode :  "+objUserMaster.getUserCode());
        prepStmt.setString(15, objUserMaster.getLoginName());
        System.out.println("loginName :  "+objUserMaster.getLoginName());
        prepStmt.setBoolean(16, objUserMaster.nonUseaMailingStatus);
        System.out.println("nonUseaMailingStatus :  "+objUserMaster.nonUseaMailingStatus);
        prepStmt.setBoolean(17, objUserMaster.nonUseaEmailStatus);
        System.out.println("nonUseaEmailStatus :  "+objUserMaster.nonUseaEmailStatus);
        prepStmt.setString(18, objUserMaster.getUserId());
        System.out.println("userId :  "+userId);
        
        int cnt = prepStmt.executeUpdate();
      
        prepStmt.close();
        releaseConnection();
        System.out.println("Succefully inserted :  "+cnt);
       
        }

    
    public void addContactDetails(HLCContactDetails objContact, String logName) throws RemoteException {
       
        String contactTypeId = null;;
       System.out.println("in addContactDetails ");
        try{
        	contactTypeId = (String)getDetFrmContyId(objContact.getContactType());
        	
        	System.out.println("in addContactDetails contactTypeId "+contactTypeId);
        	
            objContact.setContactTypeId(contactTypeId);
            System.out.println("after setContactTypeId");
            objContact.setUserId(objContact.getUserId());
            System.out.println("after setUserId");
            insertRowContactDetails(objContact,logName);
            System.out.println("after insertRowContactDetails");
        } catch(Exception exp){
            throw new EJBException("addContactDetails: " + exp.getMessage());
        }
    }
    
    private void insertRowContactDetails(HLCContactDetails objContact, String logName) throws SQLException {
        Debug.print("GeneralDBAction insertRowContactDetails");

        makeConnection();
        if (logName != null){
            try {
                userId = DBHelper.getUserId(con, logName);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.print("User Id is from insertRowContactDetails: "+userId);
        String insertStatement = "insert into tblContactDetails (contact_type_id,user_id,suite,address1,"+
            "address2,city,state,country,zip,phone_no, mobile_no, fax_no)" +
                " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?) ";
        prepStmt = con.prepareStatement(insertStatement);
        System.out.println("Inside the Contact Details ....\n\n ");
        prepStmt.setString(1, objContact.getContactTypeId());
        System.out.print("contactTypeId: "+objContact.getContactTypeId());
        prepStmt.setString(2, objContact.getUserId());
        System.out.print("userId: "+objContact.getUserId());
        prepStmt.setString(3, objContact.getSuite());
        System.out.print("suite: "+objContact.getSuite());
        prepStmt.setString(4, objContact.getAddress1());
        prepStmt.setString(5, objContact.getAddress2());
        prepStmt.setString(6, objContact.getCity());
        prepStmt.setString(7, objContact.getState());
        prepStmt.setString(8, objContact.getCountry());
        prepStmt.setString(9, objContact.getZip());
        prepStmt.setString(10, objContact.getPhoneNo());
        System.out.print("phoneNo: "+objContact.getPhoneNo());
        prepStmt.setString(11, objContact.getMobileNo());
        System.out.print("mobileNo: "+objContact.getMobileNo());
        prepStmt.setString(12, objContact.getFaxNo());
        System.out.print("faxNo: "+objContact.getFaxNo());
        int cnt = prepStmt.executeUpdate();
        System.out.print("Record Inserted succefully  "+cnt);
        Debug.print(" Insert Contact Detail Data : \n"+objContact);
        prepStmt.close();
        releaseConnection();
    }

    
 public String getDetFrmContyId(String contactType){
        
        ArrayList array = new ArrayList();
        String contYId=null;
        try {
            makeConnection();
            String selectStatement = "SELECT contact_type_id FROM tblContactTypeMaster WHERE contact_type_name = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, contactType.trim());
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                contYId=rs.getString(1);
            } 
             prepStmt.close();
             releaseConnection();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {releaseConnection(); }
        
        return contYId;
    }
 
 
 
 public ArrayList getAllRoles() throws RemoteException { 
     Debug.print("GeneralDBAction getAllRole");
     ArrayList results =(ArrayList)selectAllRole();
     return results;
  }
 
 public ArrayList selectAllRole(){
     Debug.print("GeneralDBAction.selectAllRole():");
     ArrayList rolesList = null;
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     makeConnection();
	try {

             String selectStatement=" select case when role_id in(select role_id from tblMapEntity union all select role_id from tblMapUserPrivilege "+
                                    " union all select role_id from tblMapRole) then '0' else '1' end flag, "+
                                    " role_id, role_name,role_desc,active_status,role_path from tblRoleMaster order by role_name";

         prepStmt = con.prepareStatement(selectStatement);
         rs = prepStmt.executeQuery();
         rolesList = new ArrayList();
         while(rs.next()){
             String roleId = rs.getString(2);
             String roleName = rs.getString(3);

             String roledesc = rs.getString("role_desc");
             String status = rs.getString("active_status");
             String flag = rs.getString("flag");
             String rolePath = rs.getString(6);

             String[] rlList = {roleId, roleName,roledesc,status,flag,rolePath};
             rolesList.add(rlList);
         }
         rs.close();
         prepStmt.close();
         releaseConnection();
       
     } 
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.selectAllRole():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.selectAllRole():" + e.getMessage());
     }
     return rolesList;
 }    
 
 public ArrayList getAllViews() throws RemoteException { 
     System.out.println("GeneralDBAction getAllViews()");
     ArrayList results = (ArrayList)selectAllViews();
     return results;
  }
 
 public ArrayList selectAllViews(){
 	  System.out.println("GeneralDBAction: selectAllViews()");
       ArrayList viewList = new ArrayList();
       PreparedStatement prepStmt = null;
       ResultSet rs = null;
       makeConnection();
  	try {

 String selectStatement = " SELECT view_point_id, view_point_name from tblviewpointmaster order by view_point_name";
            
 
 prepStmt = con.prepareStatement(selectStatement);

 rs = prepStmt.executeQuery();
          
           while(rs.next()){
               String viewId = rs.getString(1);
               String viewName = rs.getString(2);
               String[] viList = {viewId, viewName};
               viewList.add(viList);
           }
           rs.close();
           prepStmt.close();
           releaseConnection();
          
       } 
       catch(SQLException sql){
           releaseConnection();
           System.out.println("SQL Exception in GeneralDBAction.selectAllViews():" + sql.getMessage());
       }
       catch(Exception e){
           releaseConnection();
           e.printStackTrace();
       }
       return viewList;
   }
 
 
 //==============================Mapping  User  with Role ==========================================================
 public boolean createMappingUserWithRoles(String userId, ArrayList roleList) throws RemoteException{
     Debug.print("GeneralDBAction createMappingUserWithRoles UserId:" + userId);
     boolean result = false;
     boolean flag = false;
     if(userId!=null && userId.trim().length()!=0){
         flag =(boolean)deleteRow("user_id", userId,  "tblMapUserPrivilege","");
     }
     Debug.print("GeneralDBAction createMappingUserWithRoles():: deleteRow Deleted Enities Result:" + flag);
     if(flag){
         Iterator itRoleIds = roleList.iterator();
         while(itRoleIds.hasNext()){
             String roleId = (String)itRoleIds.next();
             if(roleId!=null){
                result =(boolean)insertUserWithRoleMapping(userId, roleId);
             }
         }
     }
     return result;
 }
 
 
//=============================================Delete Mapping Records with fieldValue details========================================= 
 public boolean deleteRow(String fieldName, String fieldValue, String tableName,String deptId) {
    Debug.print("GeneralDBAction.deleteRow()" +deptId);
    boolean result = false;
    makeConnection();
    try{
    	
    	
    	  String deleteStatement = "delete from " + tableName + "  where " + fieldName  + " = ? ";
          Debug.print("GeneralDBAction.deleteRow():" + "\n" + deleteStatement + ":" +  fieldValue);
          PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
          prepStmt.setString(1, fieldValue);
          prepStmt.executeUpdate();
          prepStmt.close();
          releaseConnection();
          result = true;
    }
    catch(SQLException sql){
       releaseConnection();
       Debug.print("SQL Exception in GeneralDBAction deleteRow:" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in  GeneralDBAction deleteRow:" + e.getMessage());
    }        
    return result;
}
 
 
//=============================================  User Map with Roles details=========================================      
 public boolean insertUserWithRoleMapping(String userId, String roleId) {
   Debug.print("GeneralDBAction.insertUserWithRoleMapping():");
   PreparedStatement prepStmt = null;
   boolean result = false;
   makeConnection();
   try {
       String insertStatement = "insert into tblMapUserPrivilege (user_id , role_id) " +
               " values( ? , ? )";
       prepStmt = con.prepareStatement(insertStatement);
       prepStmt.setString(1, userId);
       prepStmt.setString(2, roleId);
       prepStmt.executeUpdate();
       prepStmt.close();
       releaseConnection();
       result = true;
   } 
   catch(SQLException sql){
       releaseConnection();
       Debug.print("SQL Exception in GeneralDBAction.insertUserWithRoleMapping():" + sql.getMessage());
   }
   catch(Exception e){
       releaseConnection();
       Debug.print("General Exception  in GeneralDBAction.insertUserWithRoleMapping():" + e.getMessage());
   }
   return result;
} 
 
 
 public ArrayList getMappingDetailsForEnitityAndPrivileges(String  entityId)  throws RemoteException {
     Debug.print("GeneralDBAction getMappingDetailsForEnitityAndPrivileges");
     ArrayList results =(ArrayList)selectAllMappingDetailsForEntity(entityId); 
     return results;
 }
 
 
//=============================================Getting Mapping permission with privilege details=========================================      
 public ArrayList selectAllMappingDetailsForEntity(String entityId){
     Debug.print("GeneralDBAction.selectAllMappingDetailsForEntity():");
     ArrayList entityMapList = null;
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     makeConnection();
	try {
         String selectStatement=" SELECT a.map_privilege_id , a.entity_id ,a.privilege_id, b.entity_name, c.privilege_name from tblMapPrivilege " +
         		"a , tblEntityMaster b , tblPrivilegeMaster c " +
                 " where a.privilege_id = c.privilege_id and a.entity_id = b.entity_id and a.entity_id =? order by b.entity_name";
         
         prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1,entityId);
         Debug.print(selectStatement);
         rs = prepStmt.executeQuery();
         entityMapList = new ArrayList();
         while(rs.next()){
             String mapPrivilegeId = rs.getString(1);
             String entityIdVal = rs.getString(2);
             String privilegeId = rs.getString(3);
             String entityName = rs.getString(4);
             String privilegeName = rs.getString(5);
             
             String[] entMapList = {mapPrivilegeId, entityIdVal, privilegeId, entityName, privilegeName};
             entityMapList.add(entMapList);
         }

         rs.close();
         prepStmt.close();
         releaseConnection();
         //Debug.print("RoleManagementDAOImpl.selectAllMappingDetailsForEntity():" + entityMapList);
     } 
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.selectAllMappingDetailsForEntity():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.selectAllMappingDetailsForEntity():" + e.getMessage());
     }
     return entityMapList;
 }
 
 public ArrayList getPermissionBasedOnEntityRole(String  roleId, String entityId)  throws RemoteException{
     Debug.print("GeneralDBAction getPermissionBasedOnEntityRole");
     ArrayList results =(ArrayList)selectPermissionBasedRoleEntity(roleId, entityId);
     return results;
 } 
 
 //============================================= Get Permissions based on Role, Entity  =========================================      
 public ArrayList selectPermissionBasedRoleEntity(String roleId, String entityId){
     Debug.print("GeneralDBAction.selectPermissionBasedRoleEntity():");
     ArrayList roleEntityMapList = null;
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     makeConnection();
	try {
         String selectStatement="SELECT A.role_name, B.entity_name, C.privilege_name, D.permission_name, E.privilege_id, E.permission_id  from " +
         		"tblRoleMaster A, tblEntityMaster B, tblPrivilegeMaster C, tblPermissionMaster D, tblMapRole E " +
                 " WHERE E.role_id = A.role_id and E.entity_id = B.entity_id and  " +
                 " E.privilege_id = C.privilege_id and E.permission_id = D.permission_id and " +
                 " E.role_id = ? and E.entity_id = ? order by C.privilege_name" ;

         prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1,roleId);
         prepStmt.setString(2,entityId);
         
         Debug.print(selectStatement);
         rs = prepStmt.executeQuery();
         roleEntityMapList = new ArrayList();
         while(rs.next()){
             String roleName = rs.getString(1);
             String entityName = rs.getString(2);
             String privilegeName = rs.getString(3);
             String permissionName = rs.getString(4);
             String privilegeIdVal = rs.getString(5);
             String permissionId = rs.getString(6);
             String[] entMapList = {roleName, entityName, privilegeName, permissionName, privilegeIdVal, permissionId};
             roleEntityMapList.add(entMapList);
         }

         rs.close();
         prepStmt.close();
         releaseConnection();
         //Debug.print("RoleManagementDAOImpl.selectPermissionBasedRoleEntity():" + roleEntityMapList);
     } 
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.selectPermissionBasedRoleEntity():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.selectPermissionBasedRoleEntity():" + e.getMessage());
     }
     return roleEntityMapList;
 }

 public ArrayList getMappingDetailsForRoleSubPerm()  throws RemoteException{
     Debug.print("GeneralDBAction getPermissionBasedOnEntityRole");
     ArrayList results =(ArrayList)selectAllSubPermission();
     return results;
 }
 
 public ArrayList selectAllSubPermission() {
     Debug.print("GeneralDBAction.getAllPermission():");
     ArrayList permissionList = null;
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     makeConnection();
	try {
         
         String selectStatement="SELECT permission_id,sub_permission_name,active_status from tblSubMenuList";
        
         Debug.print(" selectStatement "+selectStatement);
       
         prepStmt = con.prepareStatement(selectStatement);
         rs = prepStmt.executeQuery();
         permissionList = new ArrayList();
         while(rs.next()){

             String permissionId = rs.getString(1);
             String subPermissionId = rs.getString(2);
             String status = rs.getString(3);
               
             String[] perList = {permissionId, subPermissionId,status};
             permissionList.add(perList);
         }
         rs.close();
         prepStmt.close();
         releaseConnection();
      
     }
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.getAllPermission():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.getAllPermission():" + e.getMessage());
     }
     return permissionList;
 }

 public ArrayList getAllPermission() throws RemoteException {
     Debug.print("GeneralDBAction getAllPermission");
     ArrayList results =(ArrayList)selectAllPermission();
     return results;
  }
 
 
//=============================================Getting All Permission details=========================================      
 public ArrayList selectAllPermission() {
     Debug.print("GeneralDBAction.getAllPermission():");
     ArrayList permissionList = null;
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     
     makeConnection();
	try {
       
         String selectStatement=" SELECT permission_id, permission_name from tblPermissionMaster order by permission_name" ;
     
         Debug.print(" selectStatement "+selectStatement);
      
         prepStmt = con.prepareStatement(selectStatement);
         rs = prepStmt.executeQuery();
         permissionList = new ArrayList();
         while(rs.next()){
             
             String privilegeId = rs.getString(1);
             String permissionId = rs.getString(2);
            
             String[] perList = {privilegeId,permissionId};
             permissionList.add(perList);
         }
         rs.close();
         prepStmt.close();
         releaseConnection();
        
     } 
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.getAllPermission():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.getAllPermission():" + e.getMessage());
     }
     return permissionList;
 }
 
 
 public ArrayList getAllMapPrivilege(String roleId, String entityId) throws RemoteException {
     Debug.print("GeneralDBAction getAllMapPrivilege");
     ArrayList results =(ArrayList)selectAllMapPrivilege(roleId, entityId);
     return results;
  }

 
 //=============================================Getting All Privilege details=========================================
 public ArrayList selectAllMapPrivilege(String roleId, String entityId){
     Debug.print("GeneralDBAction.getAllPrivilege():");
     ArrayList privilegeList = null;
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     makeConnection();
	try {
        
     String selectStatement="select 'Map' chk, privilege_id , privilege_name from tblPrivilegeMaster where privilege_id in( select privilege_id from tblMapRole where role_id=? and entity_id=? ) "
             +" union "
             +" select 'All' chk, privilege_id , privilege_name from tblPrivilegeMaster where privilege_id not in( select privilege_id from tblMapRole where role_id=? and entity_id=? ) "
             +" and privilege_id in(select a.privilege_id from tblMapPermission a where a.access_name != '') order by privilege_name";


         prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1, roleId);
         prepStmt.setString(2, entityId);
         prepStmt.setString(3, roleId);
         prepStmt.setString(4, entityId);

         rs = prepStmt.executeQuery();

         privilegeList = new ArrayList();
         while(rs.next()){
             String chk = rs.getString(1);
              String privilegeId = rs.getString(2);
             String privilegeName = rs.getString(3);

             String[] priList = {chk, privilegeId, privilegeName};
             privilegeList.add(priList);
         }

         rs.close();
         prepStmt.close();
         releaseConnection();
         //Debug.print("RoleManagementDAOImpl.getAllPrivilege():" + privilegeList);
     }
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.selectAllMapPrivilege():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.selectAllMapPrivilege():" + e.getMessage());
     }
     return privilegeList;
 }
 
 
 public ArrayList getAllMapPermission(String roleId, String entityId) throws RemoteException {
     Debug.print("GeneralDBAction getAllMapPrivilege");
     ArrayList results =(ArrayList)selectAllMapPermission(roleId, entityId);
     return results;
  }
 public ArrayList selectAllMapPermission(String roleId, String entityId){
     Debug.print("GeneralDBAction.getAllPermission():");
     ArrayList permissionList = null;
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     makeConnection();
	try {
       
       

         String selectStatement="select distinct 'Map' chk, a.privilege_id, a.permission_id , a.access_name from tblMapPermission a, tblMapRole b where a.privilege_id = b.privilege_id and a.permission_id = b.permission_id and b.role_id =? and b.entity_id =? and a.access_name ! = '' "
                 +"union"
                 +" select distinct 'All' chk, b.privilege_id, a.permission_id , b.access_name from tblPermissionMaster a, tblMapPermission b where a.permission_id = b.permission_id and b.access_name !='' "
                 +" and a.permission_id not in(select permission_id from tblMapRole where privilege_id = b.privilege_id and role_id =? and entity_id =?) order by a.access_name " ;


         Debug.print(" selectStatement "+selectStatement);
        
         prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1, roleId);
         prepStmt.setString(2, entityId);
         prepStmt.setString(3, roleId);
         prepStmt.setString(4, entityId);
        
         rs = prepStmt.executeQuery();
         permissionList = new ArrayList();
         while(rs.next()){

             String chk = rs.getString(1);
             String privilegeId = rs.getString(2);
             String permissionId = rs.getString(3);
               String accessName = rs.getString(4);
             String[] perList = {chk, privilegeId,permissionId, accessName};
             permissionList.add(perList);
         }
         rs.close();
         prepStmt.close();
         releaseConnection();
       
     }
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.getAllPermission():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.getAllPermission():" + e.getMessage());
     }
     return permissionList;
 }
 
 
 //==============================Mapping  Role  with Entities and Privilege==========================================================
 
 public boolean generateMappingRoleWithEntitiesAndPrivileges(String roleId, String entityId, ArrayList priPerList) throws RemoteException {
     Debug.print("GeneralDBAction generateMappingRoleWithEntitiesAndPrivileges() : " + roleId + ": Entity :" + entityId);
     boolean result =false;
     boolean flag = false;
     if(roleId!=null && roleId.trim().length()!=0 && entityId!=null && entityId.trim().length()!=0){
         flag =(boolean)deleteRow("role_id", roleId, "entity_id" , entityId , "tblMapRole");
     }
     
     Debug.print("GeneralDBAction generateMappingRoleWithEntities(): Deleted Enities Result:" + flag);
     if(flag){
         Iterator itPriPerList = priPerList.iterator();
         while(itPriPerList.hasNext()){
             String []  strPriPerList = (String [])itPriPerList.next();
             String privilegeId = strPriPerList[0];
             String permissionId = strPriPerList[1];
             if(privilegeId!=null && permissionId!=null && privilegeId.trim().length()!=0 && permissionId.trim().length()!=0){
            	 result=(boolean)insertRoleEntityPrivilegeMapping(roleId, entityId, privilegeId, permissionId);
             }
         }
         result = true;
     }
      return result;
  } 
 
 
//=============================================Delete Mapping Records with fieldValue details========================================= 
 public boolean deleteRow(String fieldName1, String fieldValue1, String fieldName2, String fieldValue2, String tableName) {
    Debug.print("GeneralDBAction.deleteRow()");
    boolean result = false;
    makeConnection();
    try{
        String deleteStatement = "delete from " + tableName + "  where " + fieldName1  + " = ? and " + fieldName2 + " = ?" ;
        PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
        prepStmt.setString(1, fieldValue1);
        prepStmt.setString(2, fieldValue2);
        prepStmt.executeUpdate();
        prepStmt.close();
        releaseConnection();
        result = true;
    }
    catch(SQLException sql){
       releaseConnection();
       Debug.print("SQL Exception in GeneralDBAction deleteRow:" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction deleteRow:" + e.getMessage());
    }        
    return result;
}
 
//=============================================  Mapping Entites with privilege details=========================================      
 public boolean insertRoleEntityPrivilegeMapping(String roleId, String entityId, String privilegeId, String permissionId) {
    Debug.print("GeneralDBAction.insertRoleEntityPrivilegeMapping():");
    PreparedStatement prepStmt = null;
    makeConnection();
    try {
        String insertStatement = "insert into tblMapRole (role_id , entity_id, privilege_id, permission_id) " +
                " values( ? , ? , ? , ?)";
        prepStmt = con.prepareStatement(insertStatement);
        prepStmt.setString(1, roleId);
        prepStmt.setString(2, entityId);
        prepStmt.setString(3, privilegeId);
        prepStmt.setString(4, permissionId);
        prepStmt.executeUpdate();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.insertRoleEntityPrivilegeMapping():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.insertRoleEntityPrivilegeMapping():" + e.getMessage());
    }
    return true;
} 
 
 public ArrayList getAllEntity() throws RemoteException { 
     Debug.print("GeneralDBAction getAllEntity");
     ArrayList results =(ArrayList)selectAllEntity();
     return results;    
  }
 
//=============================================Getting All Enities details=========================================      
 public ArrayList selectAllEntity(){
     Debug.print("GeneralDBAction.selectAllEntity():");
     ArrayList entitiesList = null;
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     makeConnection();
	try {
         String selectStatement = " SELECT entity_id, entity_name,entity_des from tblEntityMaster order by entity_name";
         prepStmt = con.prepareStatement(selectStatement);
         rs = prepStmt.executeQuery();
         entitiesList = new ArrayList();
         while(rs.next()){
             String entityId = rs.getString(1);
             String entityName = rs.getString(2);
             String entityDes = rs.getString(3);
             String[] entList = {entityId, entityName,entityDes};
             entitiesList.add(entList);
         }
         rs.close();
         prepStmt.close();
         releaseConnection();
        // Debug.print("RoleManagementDAOImpl.selectAllEntity():" + entitiesList);
     } 
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.selectAllEntity():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.selectAllEntity():" + e.getMessage());
     }
     return entitiesList;
 }  
 
 public boolean generateMappingRoleWithEntities(String roleId, ArrayList entitesList) throws RemoteException {
     Debug.print("GeneralDBAction generateMappingRoleWithEntities()" + roleId);
     boolean result =false;
     Iterator itEntityList = entitesList.iterator();
     boolean flag =(boolean)deleteRow("role_id", roleId, "tblMapEntity","");
     Debug.print("GeneralDBAction generateMappingRoleWithEntities(): Deleted Enities Result:" + flag);
     if(flag){
         if(entitesList!=null && entitesList.size()!=0){
             while(itEntityList.hasNext()){
                 String entityId = (String)itEntityList.next();
                 if(entityId!=null && entityId.trim().length()!=0){
                	 result=insertRoleEntityMapping(roleId, entityId);
                 }
             }
         }
         result = true;
     }
      return result;
  }
 
 
//=============================================  Mapping Entites with privilege details=========================================      
 public boolean insertRoleEntityMapping(String roleId, String entityId) {
     Debug.print("GeneralDBAction.insertRoleEntityMapping():");
     PreparedStatement prepStmt = null;
     makeConnection();
     try {
         String insertStatement = "insert into tblMapEntity (role_id , entity_id) " +
                 " values( ? , ? )";
         prepStmt = con.prepareStatement(insertStatement);
         prepStmt.setString(1, roleId);
         prepStmt.setString(2, entityId);
         prepStmt.executeUpdate();
         prepStmt.close();
         releaseConnection();
     } 
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.insertRoleEntityMapping():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.insertRoleEntityMapping():" + e.getMessage());
     }
     return true;
 }
 
 
 
 public boolean createRole(String roleName, String roledesc, String status) throws RemoteException{
	
	        Debug.print("GeneralDBAction createRole");
	        boolean result = false;
	        if(roleName!=null && roleName.trim().length()!=0 && isRoleNameExist(roleName)){
	
	            result = (boolean)insertRole(roleName,roledesc,status);
	
	        }
	        Debug.print("GeneralDBAction createRole");
	        return result;
	    } 
 
 
 public boolean isRoleNameExist(String roleName) {
     Debug.print("GeneralDBAction.isRoleNameExist():" + roleName);
     boolean result = true;
     makeConnection();
	try {
         String selectStatement = "select role_id from tblRoleMaster where role_name = ? " ;
         PreparedStatement prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1,roleName);
         ResultSet rs = prepStmt.executeQuery();
         if (rs.next()){
             result = false;
         }
         rs.close();
         prepStmt.close();
         releaseConnection();
     } 
     catch (SQLException e) {
         releaseConnection();
         Debug.print("GeneralDBAction:: Could not find any from roleName" + e.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction isRoleNameExist:" + e.getMessage());
     }
     Debug.print("GeneralDBAction isRoleNameExist():" + result);
     return result;
 }  
 
 public boolean insertRole(String roleName,String roledesc,String status) {
	
	            Debug.print("GeneralDBAction.insertRole():");
	            PreparedStatement prepStmt = null;
	            makeConnection();
	            try {
		
	                String insertStatement = "insert into tblRoleMaster (role_name,role_desc,active_status) " +
	                        " values( ?,?,?  )";
		
	                prepStmt = con.prepareStatement(insertStatement);
	                prepStmt.setString(1, roleName);
	
	                 prepStmt.setString(2, roledesc);
	                  prepStmt.setString(3, status);
	
	                prepStmt.executeUpdate();
	                prepStmt.close();
	                releaseConnection();
	            } 
	            catch(SQLException sql){
	                releaseConnection();
	                Debug.print("SQL Exception in GeneralDBAction.insertRole():" + sql.getMessage());
	            }
	            catch(Exception e){
	                releaseConnection();
	                Debug.print("General Exception  in GeneralDBAction.insertRole():" + e.getMessage());
	            }
	            return true;
	        } 
 
 
 public boolean editRole(String roleId, String roleName,String roledesc,String status) throws RemoteException{
	    
	        Debug.print("GeneralDBAction editRole");
	        boolean result = false;

	       
	        if(roleId!=null&& roleId.trim().length()!=0 && roleName!=null && roleName.trim().length()!=0){
	            boolean chkResult =isRoleNameEditExist(roleId, roleName);
	        
	            Debug.print("GeneralDBAction editRole chkResult:" + chkResult);
	            if(chkResult==true){
	
	                result =updateRole(roleId, roleName,roledesc,status);
	
	            }
	        }
	        Debug.print("GeneralDBAction editRole Result:" + result);
	        return result;
	    }
 
 public boolean isRoleNameEditExist(String roleId, String roleName) {
	    
	        Debug.print("GeneralDBAction.isRoleNameEditExist():" + roleName);
	        boolean result = true;
	        makeConnection();
	   	try {
	            String selectStatement = "select role_id from tblRoleMaster where role_name = ? and role_id != ? " ;
	            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
	            prepStmt.setString(1,roleName);
	            prepStmt.setString(2,roleId);
	            
	            ResultSet rs = prepStmt.executeQuery();
	            if (rs.next()){
	                result = false;
	            }
	            
	            rs.close();
	            prepStmt.close();
	            releaseConnection();
	            Debug.print("GeneralDBAction isRoleNameEditExist result:" + result);  
	        } 
	        catch (SQLException e) {
	            releaseConnection();
	            Debug.print("GeneralDBAction Could not find any from isRoleNameEditExist" + e.getMessage());
	        }
	        catch(Exception e){
	            releaseConnection();
	            Debug.print("General Exception  in GeneralDBAction isRoleNameEditExist:" + e.getMessage());
	        }
	        Debug.print("GeneralDBAction isRoleNameEditExist():" + result);
	        return result;
	    } 
 
 public boolean updateRole(String roleId, String roleName,String roledesc,String status) {
	    
	        Debug.print("GeneralDBAction.updateRole():");
	        PreparedStatement prepStmt = null;
	        makeConnection();
	        try {
	            String insertStatement = "update tblRoleMaster set role_name = ?,role_desc =? " +
		
	                    " ,active_status = ? where role_id = ?";
		
	                prepStmt = con.prepareStatement(insertStatement);
	                prepStmt.setString(1, roleName);
	               
	                 prepStmt.setString(2, roledesc);
	                prepStmt.setString(3, status);
	                 prepStmt.setString(4, roleId);
	             
	                prepStmt.executeUpdate();
	                prepStmt.close();
	       

	            releaseConnection();
	        }
	        
	        catch(SQLException sql){
	            releaseConnection();
	            Debug.print("SQL Exception in GeneralDBAction.updateRole():" + sql.getMessage());
	        }
	        catch(Exception e){
	            releaseConnection();
	            Debug.print("General Exception  in GeneralDBAction.updateRole():" + e.getMessage());
	        }
	        return true;
	    }
 
 public boolean createEntity(String entityName, String entityDes) throws RemoteException{
     Debug.print("GeneralDBAction createEntity");
     boolean result = false;
     if(entityName!=null && entityName.trim().length()!=0){
         if(isEntityNameExist(entityName)){
             result =insertEntity(entityName,entityDes);
         }
     }
     Debug.print("GeneralDBAction createEntity");
     return result;
 }
 
 
 public boolean isEntityNameExist(String entityName) {
     Debug.print("GeneralDBAction.isEntityNameExist():" + entityName);
     boolean result = true;
     makeConnection();
	try {
         String selectStatement = "select entity_id from tblEntityMaster where entity_name = ? " ;
         PreparedStatement prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1,entityName);
         ResultSet rs = prepStmt.executeQuery();
         if (rs.next()){
             result = false;
         }
         rs.close();
         prepStmt.close();
         releaseConnection();
     } 
     catch (SQLException e) {
         releaseConnection();
         Debug.print("GeneralDBAction Could not find any from entityName" + e.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction isEntityNameExist:" + e.getMessage());
     }
     Debug.print("isEntityNameExist():" + result);
     return result;
 }
 
 public boolean insertEntity(String entityName, String entityDes) {
     Debug.print("GeneralDBAction.insertEntity():");
     PreparedStatement prepStmt = null;
     makeConnection();
     try {
         String insertStatement = "insert into tblEntityMaster (entity_name,entity_des) " +
                 " values(?)";
         prepStmt = con.prepareStatement(insertStatement);
         prepStmt.setString(1, entityName);
         prepStmt.setString(2, entityDes);
         prepStmt.executeUpdate();
         prepStmt.close();
         releaseConnection();
     } 
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.insertEntity():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.insertEntity():" + e.getMessage());
     }
     return true;
 } 
 
 public String [] getEntity(String entityId){
     Debug.print("GeneralDBAction.selectEntity():");
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     String[] entList = {};
     makeConnection();
	try {
         String selectStatement=" SELECT entity_id, entity_name,entity_des from tblEntityMaster where entity_id = ? " ;
         prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1,entityId);
         rs = prepStmt.executeQuery();
         if(rs.next()){
             String entityIdVal = rs.getString(1);
             String entityName = rs.getString(2);
             String entityDes = rs.getString(3);
             String[] tempEntList = {entityIdVal, entityName,entityDes};
             entList = tempEntList;
         }

         rs.close();
         prepStmt.close();
         releaseConnection();
        
     } 
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.selectEntity():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.selectEntity():" + e.getMessage());
     }
     return entList;
 }
 
 public boolean editEntity(String entityId, String entityName, String entityDes) throws RemoteException{
     Debug.print("GeneralDBAction editEntity");
     boolean result = false;
     if(entityId!=null && entityId.trim().length()!=0 && entityName!=null && entityName.trim().length()!=0){
         boolean chkResult =isEntityNameEditExist(entityId, entityName);
         Debug.print("GeneralDBAction editEntity chkResult:" + chkResult);
         if(chkResult==true){
             result =updateEntity(entityId, entityName,entityDes);
         }
     }
     Debug.print("GeneralDBAction editEntity Result:" + result);
     return result;
 } 
 
 public boolean isEntityNameEditExist(String entityId, String entityName) {
     Debug.print("GeneralDBAction.isEntityNameEditExist():" + entityName);
     boolean result = true;
     makeConnection();
	try {
         String selectStatement = "select entity_id from tblEntityMaster where entity_name = ? and entity_id != ?" ;
         PreparedStatement prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1,entityName);
         prepStmt.setString(2,entityId);
         
         ResultSet rs = prepStmt.executeQuery();
         if (rs.next()){
             result = false;
         }
         
         rs.close();
         prepStmt.close();
         releaseConnection();
     } 
     catch (SQLException e) {
         releaseConnection();
         Debug.print("GeneralDBAction Could not find any from isEntityNameEditExist" + e.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction isEntityNameEditExist:" + e.getMessage());
     }
     Debug.print("isEntityNameEditExist():" + result);
     return result;
 }   
 
 public boolean updateEntity(String entityId, String entityName, String entityDes) {
     Debug.print("GeneralDBAction.updateEntity():");
     PreparedStatement prepStmt = null;
     makeConnection();
     try {
         String insertStatement = "update tblEntityMaster set entity_name = ?,entity_des=? " +
                 " where entity_id = ? ";
         
         prepStmt = con.prepareStatement(insertStatement);
         prepStmt.setString(1, entityName);
         prepStmt.setString(2, entityDes);
         prepStmt.setString(3, entityId);
         prepStmt.executeUpdate();
         prepStmt.close();
         releaseConnection();
     }
     
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.updateEntity():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.updateEntity():" + e.getMessage());
     }
     return true;
 }  
 
 
 public boolean createPermission(String permissionName,String permissionDescrption) throws RemoteException{
     Debug.print("GeneralDBAction createPermission");
     boolean result = false;
     if(isPermissionNameExist(permissionName)){
         result =insertPermission(permissionName,permissionDescrption);
     }
     Debug.print("GeneralDBAction createPermission");
     return result;
 }
 
 public boolean isPermissionNameExist(String permissionName) {
     Debug.print("GeneralDBAction.isPermissionNameExist():" + permissionName);
     boolean result = true;
     makeConnection();
	try {
         String selectStatement = "select permission_id from tblPermissionMaster where permission_name = ? " ;
         PreparedStatement prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1,permissionName);
         ResultSet rs = prepStmt.executeQuery();
         if (rs.next()){
             result = false;
         }
         rs.close();
         prepStmt.close();
         releaseConnection();
     } 
     catch (SQLException e) {
         releaseConnection();
         Debug.print("GeneralDBAction Could not find any from permissionName" + e.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction isPermissionNameExist:" + e.getMessage());
     }
     Debug.print("GeneralDBAction isPermissionNameExist():" + result);
     return result;
 }

 public boolean insertPermission(String permissionName, String permissionDescrption){
     Debug.print("GeneralDBAction.insertPermission():");
     PreparedStatement prepStmt = null;
     boolean result = false;
     makeConnection();
     try {
         String insertStatement = "insert into tblPermissionMaster (permission_name,permission_description) " +
                 " values(?,?)";
         prepStmt = con.prepareStatement(insertStatement);
         prepStmt.setString(1, permissionName);
         prepStmt.setString(2, permissionDescrption);
         prepStmt.executeUpdate();
         prepStmt.close();
         releaseConnection();
         result = true;
     } 
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.insertPermission():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.insertPermission():" + e.getMessage());
     }
     return result;
 }
 
 
 public String [] getPermission(String permissionId){
     Debug.print("GeneralDBAction.selectPrivilege():");
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     String[] perList = {};
     makeConnection();
	try {
         String selectStatement=" SELECT permission_id, permission_name,permission_description from tblPermissionMaster where permission_id = ? " ;
         prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1,permissionId);
         rs = prepStmt.executeQuery();
         if(rs.next()){
             String permissionIdVal = rs.getString(1);
             String permissionName = rs.getString(2);
             String permissionDescription = rs.getString(3);
             String[] tempPerList = {permissionIdVal, permissionName,permissionDescription};
             perList = tempPerList;
         }

         rs.close();
         prepStmt.close();
         releaseConnection();
        // Debug.print("RoleManagementDAOImpl.selectPrivilege():" + perList);
     } 
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.selectPrivilege():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.selectPrivilege():" + e.getMessage());
     }
     return perList;
 }   
 
 
 public boolean editPermission(String permissionId, String permissionName, String permissionDescrption) throws RemoteException{
     Debug.print("GeneralDBAction editPermission");
     boolean result = false;
     boolean chkResult =isPermissionNameEditExist(permissionId, permissionName);
     Debug.print("GeneralDBAction editPermission chkResult:" + chkResult);
     if(chkResult==true){
         result =updatePermission(permissionId, permissionName,permissionDescrption);
     }
     Debug.print("GeneralDBAction editPermission Result:" + result);
     return result;
 }
 
 public boolean isPermissionNameEditExist(String permissionId, String permissionName){
     Debug.print("GeneralDBAction.isPermissionNameEditExist():" + permissionName);
     boolean result = true;
     makeConnection();
	try {
         String selectStatement = "select permission_id from tblPermissionMaster where permission_name = ? and permission_id != ?" ;
         PreparedStatement prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1,permissionName);
         prepStmt.setString(2,permissionId);
         
         ResultSet rs = prepStmt.executeQuery();
         if (rs.next()){
             result = false;
         }
         
         rs.close();
         prepStmt.close();
         releaseConnection();
     } 
     catch (SQLException e) {
         releaseConnection();
         Debug.print("GeneralDBAction Could not find any from isPermissionNameEditExist" + e.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction isPermissionNameEditExist:" + e.getMessage());
     }
     Debug.print("GeneralDBAction isPrivilegeNameExist():" + result);
     return result;
 } 
 
 public boolean updatePermission(String permissionId, String permissionName, String permissionDescrption) {
     Debug.print("GeneralDBAction.updatePermission():");
     PreparedStatement prepStmt = null;
     boolean result = false;
     makeConnection();
     try {
         String insertStatement = "update tblPermissionMaster set permission_name = ?, permission_description=? " +
                 " where permission_id = ?";
         
         prepStmt = con.prepareStatement(insertStatement);
         prepStmt.setString(1, permissionName);
         prepStmt.setString(2, permissionDescrption);
         prepStmt.setString(3, permissionId);
         prepStmt.executeUpdate();
         prepStmt.close();
         releaseConnection();
         result = true;
     }
     
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.updatePermission():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.updatePermission():" + e.getMessage());
     }
     return result;
 }    
 
 public boolean createPrivilege(String privilegeName) throws RemoteException{
     Debug.print("GeneralDBAction createPrivilege");
     boolean result = false;
     if(privilegeName!=null && privilegeName.trim().length()!=0){
         if(isPrivilegeNameExist(privilegeName)){
             result =insertPrivilege(privilegeName);
         }
     }
     return result;
 }
 
 public boolean isPrivilegeNameExist(String privilegeName) {
     Debug.print("GeneralDBAction.isPrivilegeNameExist():" + privilegeName);
     boolean result = true;
     makeConnection();
	try {
         String selectStatement = "select privilege_id from tblPrivilegeMaster where privilege_name = ? " ;
         PreparedStatement prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1,privilegeName);
         ResultSet rs = prepStmt.executeQuery();
         if (rs.next()){
             result = false;
         }
         rs.close();
         prepStmt.close();
         releaseConnection();
     } 
     catch (SQLException e) {
         releaseConnection();
         Debug.print("GeneralDBAction Could not find any from privilegeName" + e.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction isPrivilegeNameExist:" + e.getMessage());
     }
     Debug.print("isPrivilegeNameExist():" + result);
     return result;
 }
 
 public boolean insertPrivilege(String privilegeName) {
     Debug.print("GeneralDBAction.insertPrivilege():");
     PreparedStatement prepStmt = null;
     makeConnection();
     try {
         String insertStatement = "insert into tblPrivilegeMaster (privilege_name) " +
                 " values( ?  )";
         prepStmt = con.prepareStatement(insertStatement);
         prepStmt.setString(1, privilegeName);
         prepStmt.executeUpdate();
         prepStmt.close();
         releaseConnection();
     } 
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.insertPrivilege():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.insertPrivilege():" + e.getMessage());
     }
     return true;
 } 
 
 public ArrayList getAllPrivilege(){
     Debug.print("GeneralDBAction.getAllPrivilege():");
     ArrayList privilegeList = null;
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     makeConnection();
	try {
         String selectStatement=" SELECT privilege_id, privilege_name, privilege_path from tblPrivilegeMaster order by privilege_name";
         prepStmt = con.prepareStatement(selectStatement);
         rs = prepStmt.executeQuery();
         privilegeList = new ArrayList();
         while(rs.next()){
             String privilegeId = rs.getString(1);
             String privilegeName = rs.getString(2);
             String privilegePath = rs.getString(3);
             String[] priList = {privilegeId, privilegeName, privilegePath};
             privilegeList.add(priList);
         }

         rs.close();
         prepStmt.close();
         releaseConnection();
        
     } 
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.getAllPrivilege():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.getAllPrivilege():" + e.getMessage());
     }
     return privilegeList;
 }

 public String [] getPrivilege(String privilegeId){
     Debug.print("GeneralDBAction.selectPrivilege():");
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     String[] priList = {};
     makeConnection();
	try {
         String selectStatement=" SELECT privilege_id, privilege_name, privilege_path from tblPrivilegeMaster where privilege_id = ? " ;
         prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1,privilegeId);
         rs = prepStmt.executeQuery();
         if(rs.next()){
             String privilegeIdVal = rs.getString(1);
             String privilegeName = rs.getString(2);
             String privilegePath = rs.getString(3);
             String tempPriList[] = {privilegeIdVal, privilegeName, privilegePath};
             priList = tempPriList;
         }

         rs.close();
         prepStmt.close();
         releaseConnection();
      
     } 
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.selectPrivilege():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.selectPrivilege():" + e.getMessage());
     }
     return priList;
 } 
 
 public boolean editPrivilege(String privilegeId, String privilegeName) throws RemoteException{
     Debug.print("GeneralDBAction editPrivilege");
     boolean result = false;
     if(privilegeId!=null && privilegeId.trim().length()!=0){
         boolean chkResult =isPrivilegeNameEditExist(privilegeId, privilegeName);
         Debug.print("GeneralDBAction editPrivilege chkResult:" + chkResult);
         if(chkResult==true){
             result =updatePrivilege(privilegeId, privilegeName);
         }
     }
     Debug.print("GeneralDBAction editPrivilege Result:" + result);
     return result;
 }
 
 public boolean isPrivilegeNameEditExist(String privilegeId, String privilegeName) {
     Debug.print("GeneralDBAction.isPrivilegeNameEditExist():" + privilegeName);
     boolean result = true;
     makeConnection();
	try {
         String selectStatement = "select privilege_id from tblPrivilegeMaster where privilege_name = ? and privilege_id != ?" ;
         PreparedStatement prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1,privilegeName);
         prepStmt.setString(2,privilegeId);
         
         ResultSet rs = prepStmt.executeQuery();
         if (rs.next()){
             result = false;
         }
         
         rs.close();
         prepStmt.close();
         releaseConnection();
     } 
     catch (SQLException e) {
         releaseConnection();
         Debug.print("Could not find any from isPrivilegeNameEditExist" + e.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in isPrivilegeNameEditExist:" + e.getMessage());
     }
     Debug.print("isPrivilegeNameExist():" + result);
     return result;
 }  
 
 
 public boolean updatePrivilege(String privilegeId, String privilegeName){
     Debug.print("GeneralDBAction.updatePrivilege():");
     PreparedStatement prepStmt = null;
     makeConnection();
     try {
         String insertStatement = "update tblPrivilegeMaster set privilege_name = ? " +
                 " where privilege_id = ?";
         
         prepStmt = con.prepareStatement(insertStatement);
         prepStmt.setString(1, privilegeName);
         prepStmt.setString(2, privilegeId);
         prepStmt.executeUpdate();
         prepStmt.close();
         releaseConnection();
     }
     
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.updatePrivilege():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.updatePrivilege():" + e.getMessage());
     }
     return true;
 }  
 
 public ArrayList getMappingDetailsForPermissionAndPrivilege(String privilegeId){
     Debug.print("GeneralDBAction.selectAllMappingDetailsForPrivilege():");
     ArrayList permissionMapList = null;
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     makeConnection();
	try {
         String selectStatement=" SELECT a.map_permission_id , b.permission_name ,a.access_name,a.access_des, a.access_url " +
         		"from tblMapPermission a , tblPermissionMaster b where a.permission_id = b.permission_id and a.privilege_id = ? ";
         
         prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1,privilegeId);
         Debug.print(selectStatement);
         rs = prepStmt.executeQuery();
         permissionMapList = new ArrayList();
         while(rs.next()){
             String mapPermissionId = rs.getString(1);
             String permissionName = rs.getString(2);
             String accessName = rs.getString(3);
             String accessDes = rs.getString(4);
             String accessUrl = rs.getString(5);
             String[] priMapList = {mapPermissionId, permissionName, accessName,accessDes,accessUrl};
             permissionMapList.add(priMapList);
         }

         rs.close();
         prepStmt.close();
         releaseConnection();
       
     } 
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.selectAllMappingDetailsForPrivilege():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.selectAllMappingDetailsForPrivilege():" + e.getMessage());
     }
     return permissionMapList;
 }
 
 public boolean generateMappingEnitityWithPrivileges(String entityId, ArrayList privilegeList) throws RemoteException {
     Debug.print("GeneralDBAction generateMappingEnitityWithPrivileges()" + entityId);
     boolean result =false;
     boolean flag = deleteRow("entity_id", entityId, "tblMapPrivilege","");
     Debug.print("GeneralDBAction generateMappingEnitityWithPrivileges(): Deleted Enities Result:" + flag);
     if(flag){
         if(privilegeList!=null && privilegeList.size()!=0){
             Iterator itPrivilege = privilegeList.iterator();
             while(itPrivilege.hasNext()){
                 String privilegeId = (String)itPrivilege.next();
                 if(privilegeId!=null && privilegeId.trim().length()!=0){
                     insertEntityPrivilegeMapping(entityId, privilegeId);
                 }
             }
         }
         result = true;
     }
      return result;
  }
 
 
 public boolean insertEntityPrivilegeMapping(String entityId, String privilegeId) {
     Debug.print("GeneralDBAction.insertEntityPrivilegeMapping():");
     PreparedStatement prepStmt = null;
     makeConnection();
     try {
         String insertStatement = "insert into tblMapPrivilege (entity_id , privilege_id) " +
                 " values( ? , ? )";
         prepStmt = con.prepareStatement(insertStatement);
         prepStmt.setString(1, entityId);
         prepStmt.setString(2, privilegeId);
         prepStmt.executeUpdate();
         prepStmt.close();
         releaseConnection();
     } 
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.insertEntityPrivilegeMapping():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.insertEntityPrivilegeMapping():" + e.getMessage());
     }
     return true;
 }
 
 
 public void editMappingPermissionToPrivilege(ArrayList mapPriPerList,String privId) throws RemoteException{
     Debug.print("GeneralDBAction.editMappingPermissionToPrivilege()");
     Iterator itMapPriPer = mapPriPerList.iterator();
     while(itMapPriPer.hasNext()){
         String priList[] = (String [])itMapPriPer.next();
         String mapPermissionId = priList[0];
         String accessName = priList[1];
         String accessDes = priList[2];
         String accessUrl = priList[3];
         
         if(accessName!=null && accessName.trim().length()!=0 ){
             Debug.print("GeneralDBAction.editMappingPermissionToPrivilege() accessName not empty");
             if(isAccessNameExistinEdit(mapPermissionId,accessName,"","")){
                 updatePermissionPrivilegeMapping( mapPermissionId, accessName,accessDes,accessUrl,privId);
             }
             else{
             	insertPermissionPrivilegeMapping(privId, mapPermissionId, accessName,accessDes,accessUrl);
             }
         }
         else{
         	if(isAccessNameExistinEdit("","",privId,mapPermissionId)){
         		Debug.print("GeneralDBAction.editMappingPermissionToPrivilege() empty accessName");
         		updatePermissionPrivilegeMapping( mapPermissionId, accessName,accessDes,accessUrl,privId);
         	}
         	else {
         		insertPermissionPrivilegeMapping(privId, mapPermissionId, accessName,accessDes,accessUrl);
         	}
         }
     }
 }
 
 
 public boolean isAccessNameExistinEdit(String mapPermissionId, String accessName,String privId, String permissionId) {
     Debug.print("GeneralDBAction.isAccessNameExistinEdit():" + accessName);
     boolean result = true;
     String selectStatement=null;
     PreparedStatement prepStmt=null;
     ResultSet rs=null;
     makeConnection();
	try {
		 	
		if(!mapPermissionId.equals("") && !accessName.equals("")) {
			
			selectStatement = "select map_permission_id from tblMapPermission where access_name = ? and map_permission_id != ?" ;
         prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1,accessName);
         prepStmt.setString(2,mapPermissionId);
         rs = prepStmt.executeQuery();
         if (rs.next()){
             result = false;
         }
		}
         
         if(!privId.equals("") && !permissionId.equals("")) {            	

	            selectStatement = "select count(1) from tblMapPermission where privilege_id = ?" ;
	       		prepStmt = con.prepareStatement(selectStatement);
	       		prepStmt.setString(1,privId);
	       		
	       		rs = prepStmt.executeQuery();
	       		
	            if (rs.next()){
	                int recCnt = rs.getInt(1);
	                    if(recCnt==12){
	                    Debug.print("If Inside recCnt==="+recCnt);
	                    result=true;
	                }
	                else {
	                	 Debug.print("else Inside recCnt==="+recCnt);
	                	result = false;
	                }
	            }
         }
         
         rs.close();
         prepStmt.close();
         releaseConnection();
     } 
     catch (SQLException e) {
         releaseConnection();
         Debug.print("GeneralDBAction Could not find any from accessName" + e.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction isAccessNameExistinEdit:" + e.getMessage());
     }
     Debug.print("GeneralDBAction isAccessNameExistinEdit():" + result);
     return result;
 } 
 
 
 public boolean updatePermissionPrivilegeMapping(String mapPermissionId, String accessName,String accessDes,String accessUrl,String privId){
     Debug.print("GeneralDBAction.updatePermissionPrivilegeMapping():");
     PreparedStatement prepStmt = null;
     makeConnection();
     String statementQuery="";
     try {
     	
     		statementQuery = "update tblMapPermission set access_name = ? ,access_des = ?, access_url = ? " +
     				" where map_permission_id = ?";   		
     
                 
         
         prepStmt = con.prepareStatement(statementQuery);
         prepStmt.setString(1, accessName);
         prepStmt.setString(2, accessDes);
         prepStmt.setString(3, accessUrl);
         
        
         prepStmt.setString(4, mapPermissionId);
       
         prepStmt.executeUpdate();
         prepStmt.close();
         releaseConnection();
     }
     
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.updatePermissionPrivilegeMapping():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.updatePermissionPrivilegeMapping():" + e.getMessage());
     }
     return true;
 }
 
 
 public boolean insertPermissionPrivilegeMapping(String privilegeId, String permissionId, String accessName,String accessDes,String accessUrl){
     Debug.print("GeneralDBAction.insertPermissionPrivilegeMapping():");
     PreparedStatement prepStmt = null;
     makeConnection();
     try {
         String insertStatement = "insert into tblMapPermission (privilege_id , permission_id, access_name,access_des,access_url) " +
                 " values( ? , ? , ? , ? )";
         prepStmt = con.prepareStatement(insertStatement);
         prepStmt.setString(1, privilegeId);
         prepStmt.setString(2, permissionId);
         prepStmt.setString(3, accessName);
         prepStmt.setString(4, accessName);
         prepStmt.setString(5, accessUrl);
         prepStmt.executeUpdate();
         prepStmt.close();
         releaseConnection();
     } 
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.insertPermissionPrivilegeMapping():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.insertPermissionPrivilegeMapping():" + e.getMessage());
     }
     return true;
 }
 
 public void createMappingPermissionToPrivilege(String privilegeId, ArrayList mapPriPerList) throws RemoteException{
     Debug.print("GeneralDBAction getAllPermission");
     Iterator itMapPriPer = mapPriPerList.iterator();
     while(itMapPriPer.hasNext()){
         String priList[] = (String [])itMapPriPer.next();
         String permissionId = priList[0];
         String accessName = priList[1];
         String accessDes = priList[2];
         String accessUrl = priList[3];
         if(accessName.trim().length()!=0 && accessName !=null){
             if(isAccessNameExist(accessName)){
                 insertPermissionPrivilegeMapping(privilegeId, permissionId, accessName,accessDes,accessUrl);
             }
             else{
                  insertPermissionPrivilegeMapping(privilegeId, permissionId, "","", accessUrl);
             }
         }
         else{
             insertPermissionPrivilegeMapping(privilegeId, permissionId, accessName,accessDes,accessUrl);
         }
     }
 }
 
 public boolean isAccessNameExist(String accessName) {
     Debug.print("GeneralDBAction.isAccessNameExist():" + accessName);
     boolean result = true;
     makeConnection();
	try {
         String selectStatement = "select map_permission_id from tblMapPermission where access_name = ? " ;
         PreparedStatement prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1,accessName);
         ResultSet rs = prepStmt.executeQuery();
         if (rs.next()){
             result = false;
         }
         rs.close();
         prepStmt.close();
         releaseConnection();
     } 
     catch (SQLException e) {
         releaseConnection();
         Debug.print("GeneralDBAction Could not find any from accessName" + e.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction isAccessNameExist:" + e.getMessage());
     }
     Debug.print("GeneralDBAction isAccessNameExist():" + result);
     return result;
 }
 
 public boolean deleteRole(String chkRoleIdArr[]) throws RemoteException{
     Debug.print("GeneralDBAction deleteRole");
     boolean result = false;


     if(chkRoleIdArr!=null){

         //boolean chkResult =dao.isRoleNameExist(roleId);
         boolean chkResult =isRoleNameExist(chkRoleIdArr[0]);

         Debug.print("GeneralDBAction deleteRole chkResult:" + chkResult);
         if(chkResult==true){
             //result = dao.deleteRole(roleId);
             result =deleteRoleDet(chkRoleIdArr);
         }
     }
     Debug.print("GeneralDBAction deleteRole Result:" + result);
     return result;
 }
 
 public boolean deleteRoleDet(String chkRoleIdArr[]) {
     Debug.print("GeneralDBAction.deleteRole():");
     PreparedStatement prepStmt = null;
     makeConnection();
     try {
         String deleteStatement = "delete from tblRoleMaster where role_id = ?";
                 
         for(int i=0;i<chkRoleIdArr.length;i++)
         {
             prepStmt = con.prepareStatement(deleteStatement);
             //prepStmt.setString(1, roleId);
             prepStmt.setString(1, chkRoleIdArr[i]);

             Debug.print("GeneralDBAction.deleteRole():"+deleteStatement+"=="+chkRoleIdArr[i]);
             prepStmt.executeUpdate();
             prepStmt.close();
         }

         releaseConnection();
     }

     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.deleteRole:" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.deleteRole:" + e.getMessage());
     }
     return true;
 }
 
 public String getExternalAppURL(String perName)
 {
     Debug.print("GeneralDBAction.getExternalAppURL():");
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     makeConnection();
     String perUrl = "";
     try
     {
         String sqlQuery = "select access_url from tblmappermission where access_name=?";
         prepStmt = con.prepareStatement(sqlQuery);
         prepStmt.setString(1, perName);
         rs = prepStmt.executeQuery();
         rs.next();
         perUrl = rs.getString(1);
         rs.close();
         prepStmt.close();
         releaseConnection();
     }
     catch(SQLException sql)
     {
         releaseConnection();
         Debug.print((new StringBuilder()).append("SQL Exception in GeneralDBAction.getExternalAppURL():").append(sql.getMessage()).toString());
     }
     catch(Exception e)
     {
         releaseConnection();
         Debug.print((new StringBuilder()).append("General Exception  in GeneralDBAction.getExternalAppURL():").append(e.getMessage()).toString());
     }
     return perUrl;
 }
 
 
 
 public boolean isLifecycleDBExist(String lifecycleName)
 {  
 	boolean result=false;
 	String name="";
 	makeConnection();
       try {
    	   if(lifecycleName!=null){
     	  String selectStmt="select lifecycleProName from tblLifecycleprocessinsert where lifecycleProName = ?";          	  
           
         
           prepStmt = con.prepareStatement(selectStmt);
           prepStmt.setString(1, lifecycleName);
           rs=prepStmt.executeQuery();
           if(rs.next())
           {
         	
         	   result = true;
           }
          
          
           
           System.out.println("GeneralDBAction isLifecycleDBExist() Name :" + name);
           prepStmt.close();
           releaseConnection();}
    	   else{
    		   result = false;   
    	   }
       } catch(SQLException sql){
           releaseConnection();
           System.out.println("SQL Exception in GeneralDBAction.isLifecycleDBExist():" + sql.getMessage());
       } catch(Exception e){
           releaseConnection();
           System.out.println("General Exception  in GeneralDBAction.isLifecycleExist():" + e.getMessage());
       }
 	
 	
 	return result;
 }    
 
 
 public String adddocumentfromcustomer(String userId,String fileename,String saveFile)
 {
 	 
 	String allow_date=null;
		    
		    	  
		        try {
		        	makeConnection();
		        	 String str="insert into tblProcessDetails(user_id,file_name,file_path) values (?,?,?)";
				     				     
				        prepStmt = con.prepareStatement(str);
			            prepStmt.setString(1,userId);
			            prepStmt.setString(2,fileename);
			            prepStmt.setString(3,saveFile);
			            
			            prepStmt.executeUpdate();

			 	       prepStmt.close();
			       
			       releaseConnection(); 
			    } 
		        catch (Exception ex) {
			        	ex.printStackTrace();
			             //Debug.print("Exception:" + ex.getMessage());
			        }finally {
			            releaseConnection();
			        }
		       
		    return allow_date;
 }

 
 public void getProcessList(ArrayList<String> firstNameList,ArrayList<String> lastNameList,ArrayList<String> fileNameList,ArrayList<String> filePathList){
     Debug.print("GeneralDBAction.selectAll Process Details For Developer():");
     ArrayList<String> processList = null;
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     makeConnection();
	try {
         String selectStatement=" SELECT a.first_name , a.last_name ,b.file_name, b.file_path " +
         		"from tblUserMaster a , tblProcessDetails b where a.user_id = b.user_id";
         
         prepStmt = con.prepareStatement(selectStatement);
         Debug.print(selectStatement);
         rs = prepStmt.executeQuery();
         processList = new ArrayList<String>();
         while(rs.next()){
             firstNameList.add(rs.getString("first_name"));
             lastNameList.add(rs.getString("last_name"));
             fileNameList.add(rs.getString("file_name"));
             filePathList.add(rs.getString("file_path"));
         }

         rs.close();
         prepStmt.close();
         releaseConnection();
       
     } 
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.selectAllMappingDetailsForPrivilege():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.selectAllMappingDetailsForPrivilege():" + e.getMessage());
     }
 }
 
 
 public String getPasswordByLoginId(String userid)
 {
     Debug.print("GeneralDBAction.getPasswordByLoginId():");
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     makeConnection();
     String password = "";
     try
     {
         String sqlQuery = "select password from tblUserMaster where user_id=?";
         prepStmt = con.prepareStatement(sqlQuery);
         prepStmt.setString(1, userid);
         rs = prepStmt.executeQuery();
         rs.next();
         password = rs.getString(1);
         rs.close();
         prepStmt.close();
         releaseConnection();
     }
     catch(SQLException sql)
     {
         releaseConnection();
         Debug.print((new StringBuilder()).append("SQL Exception in GeneralDBAction.getExternalAppURL():").append(sql.getMessage()).toString());
     }
     catch(Exception e)
     {
         releaseConnection();
         Debug.print((new StringBuilder()).append("General Exception  in GeneralDBAction.getExternalAppURL():").append(e.getMessage()).toString());
     }
     return password;
 }
 
 public String getEmailIdByPassword(String userId){
	 Debug.print("GeneralDBAction.getEmailIdByPassword():");
	 PreparedStatement prepstmt = null;
	 ResultSet rs = null;
	 makeConnection();
	 String emailid = "";
	 try{
		 String sqlQuery = "Select email_id from tblUserMaster where user_id=?";
		 prepstmt = con.prepareStatement(sqlQuery);
		 prepstmt.setString(1, userId);
		 rs = prepstmt.executeQuery();
		 rs.next();
		 emailid = rs.getString(1);
		 rs.close();
		 prepstmt.close();
		 releaseConnection();
	 }
	 catch(SQLException sql)
     {
         releaseConnection();
         Debug.print((new StringBuilder()).append("SQL Exception in GeneralDBAction.getEmailIdByPassword():").append(sql.getMessage()).toString());
     }
     catch(Exception e)
     {
         releaseConnection();
         Debug.print((new StringBuilder()).append("General Exception  in GeneralDBAction.getEmailIdByPassword():").append(e.getMessage()).toString());
     }
	 
	 return emailid;
	 
 }
 
 
 public String getRoleNameByRoleId(String roleId){
	 Debug.print("GeneralDBAction.getRoleNameByRoleId():");
	 PreparedStatement prepstmt = null;
	 ResultSet rs = null;
	 makeConnection();
	 String roleName = "";
	 try{
		 String sqlQuery = "Select role_name from tblRoleMaster where role_id=?";
		 prepstmt = con.prepareStatement(sqlQuery);
		 prepstmt.setString(1, roleId);
		 rs = prepstmt.executeQuery();
		 rs.next();
		 roleName = rs.getString(1);
		 rs.close();
		 prepstmt.close();
		 releaseConnection();
	 }
	 catch(SQLException sql)
     {
         releaseConnection();
         Debug.print((new StringBuilder()).append("SQL Exception in GeneralDBAction.getRoleNameByRoleId():").append(sql.getMessage()).toString());
     }
     catch(Exception e)
     {
         releaseConnection();
         Debug.print((new StringBuilder()).append("General Exception  in GeneralDBAction.getRoleNameByRoleId():").append(e.getMessage()).toString());
     }
	 
	 return roleName;
	 
 }
 
 //======================================Artifact Management===============================================
 
 public ArrayList getCount(){
     int cnt = 0;
     ArrayList cntRecord = new ArrayList();
     PreparedStatement prepStmt = null;
     String selectStatement="";
     makeConnection();
     System.out.println("GeneralDBAction: getCount()");
     try {
   
              selectStatement = "select count(*) from tblFrameworkMaster";
             
             prepStmt = con.prepareStatement(selectStatement);
            
             ResultSet rs = prepStmt.executeQuery();
             if(rs.next()){
                 cnt = rs.getInt(1);
                 
                  selectStatement = "select A.layer_value_id, A.layer_value, B.master_name, B.master_id from " +
                 		"tblframeworklayermap A, tblframeworkmaster B where " +
                 		"A.master_id=B.master_id and master_sequence=?";
                  
                  prepStmt = con.prepareStatement(selectStatement);
                  prepStmt.setInt(1, cnt);
                  rs = prepStmt.executeQuery();
                  
                  while(rs.next()){
                 	 
                 	 String layerValId = rs.getString(1);
                      String layerValName = rs.getString(2);
                      String masterId = rs.getString(3);
                      String masterName = rs.getString(4);
                      String[] cntList = {layerValId, layerValName,masterId,masterName,String.valueOf(cnt)};
                      cntRecord.add(cntList);	 
                 	 
                  }
             }
             rs.close();
             prepStmt.close();
        
         releaseConnection();
         
     } catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.getCount():" + sql.getMessage());
     } catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.getCount():" + e.getMessage());
     }
     return cntRecord;
 }
 
 public boolean insertArtifactMapDetails(HLCMenuListVO objArti)
 {
     Debug.print("RoleManagementDAOImpl.insertArtifactMapDetails():");
     Debug.print("RoleManagementDAOImpl.insertArtifactMapDetails():"+objArti);
     
     Debug.print("RoleManagementDAOImpl.objArti.getGroupsId()():"+objArti.getGroupsId());
     Debug.print("RoleManagementDAOImpl.objArti.getProcessDomainId()():"+objArti.getProcessDomainId());
     
     PreparedStatement prepStmt = null;
     boolean result = false;
     
  
     makeConnection();
     try
     {
     	

     	
   if(objArti.getViewPtId()!=null && objArti.getLobLayerId()==null && objArti.getViewId()==null &&  objArti.getGroupsId()==null && objArti.getProcessDomainId()==null && objArti.getArtifactId()!=null && objArti.getArtifactDesc()!=null){
     	
         String insertStatement = "insert into tblMapArtifact (view_point_value_id, artifact_id, artifact_description) values( ?,?,?)";
         prepStmt = con.prepareStatement(insertStatement);
         prepStmt.setString(1, objArti.getViewPtId());         
         prepStmt.setString(2, objArti.getArtifactId());                     
         prepStmt.setString(3, objArti.getArtifactDesc());
   }else if(objArti.getViewPtId()!=null && objArti.getLobLayerId()!=null && objArti.getViewId()==null && objArti.getGroupsId()==null && objArti.getProcessDomainId()==null && objArti.getArtifactId()!=null && objArti.getArtifactDesc()!=null){
 	  
 	  String insertStatement = "insert into tblMapArtifact (view_point_value_id, lob_value_id, artifact_id, artifact_description) values( ?,?,?,?)";
       prepStmt = con.prepareStatement(insertStatement);
       prepStmt.setString(1, objArti.getViewPtId());
       prepStmt.setString(2, objArti.getLobLayerId());       
       prepStmt.setString(3, objArti.getArtifactId());                     
       prepStmt.setString(4, objArti.getArtifactDesc());  
 	  
   }else if(objArti.getViewPtId()!=null && objArti.getLobLayerId()!=null && objArti.getViewId()!=null && objArti.getGroupsId()==null && objArti.getProcessDomainId()==null && objArti.getArtifactId()!=null && objArti.getArtifactDesc()!=null){
 	  
 	  String insertStatement = "insert into tblMapArtifact (view_point_value_id, lob_value_id, view_value_id, artifact_id, artifact_description) values( ?,?,?,?,?)";
       prepStmt = con.prepareStatement(insertStatement);
       prepStmt.setString(1, objArti.getViewPtId());
       prepStmt.setString(2, objArti.getLobLayerId());     
       prepStmt.setString(3, objArti.getViewId());
       prepStmt.setString(4, objArti.getArtifactId());                     
       prepStmt.setString(5, objArti.getArtifactDesc());  
 	  
   }else if(objArti.getViewPtId()!=null && objArti.getLobLayerId()!=null && objArti.getViewId()!=null && objArti.getGroupsId()!=null && objArti.getProcessDomainId()==null && objArti.getArtifactId()!=null && objArti.getArtifactDesc()!=null){
 	  
 	  String insertStatement = "insert into tblMapArtifact (view_point_value_id, lob_value_id, view_value_id, group_value_id, artifact_id, artifact_description) values( ?,?,?,?,?,?)";
       prepStmt = con.prepareStatement(insertStatement);
       prepStmt.setString(1, objArti.getViewPtId());
       prepStmt.setString(2, objArti.getLobLayerId());     
       prepStmt.setString(3, objArti.getViewId());
       prepStmt.setString(4, objArti.getGroupsId());
       prepStmt.setString(5, objArti.getArtifactId());                     
       prepStmt.setString(6, objArti.getArtifactDesc());  
 	  
   }else if(objArti.getViewPtId()!=null && objArti.getLobLayerId()!=null && objArti.getViewId()!=null && objArti.getGroupsId()!=null && objArti.getProcessDomainId()!=null && objArti.getArtifactId()!=null && objArti.getArtifactDesc()!=null){
 	  
 	  String insertStatement = "insert into tblMapArtifact (view_point_value_id, lob_value_id, view_value_id, group_value_id, process_domain_value_id, artifact_id, artifact_description) values( ?,?,?,?,?,?,?)";
       prepStmt = con.prepareStatement(insertStatement);
       prepStmt.setString(1, objArti.getViewPtId());
       prepStmt.setString(2, objArti.getLobLayerId());
       prepStmt.setString(3, objArti.getViewId());  
       prepStmt.setString(4, objArti.getGroupsId());  
       prepStmt.setString(5, objArti.getProcessDomainId());  
       prepStmt.setString(6, objArti.getArtifactId());                     
       prepStmt.setString(7, objArti.getArtifactDesc());  
 	  
   }
   
        
         prepStmt.executeUpdate();
         prepStmt.close();
         releaseConnection();
         result = true;
     }
     catch(SQLException sql)
     {
         releaseConnection();
         Debug.print((new StringBuilder()).append("SQL Exception in RoleManagementDAOImpl.insertArtifactMapDetails():").append(sql.getMessage()).toString());
     }
     catch(Exception e)
     {
         releaseConnection();
         Debug.print((new StringBuilder()).append("General Exception  in RoleManagementDAOImpl.insertArtifactMapDetails():").append(e.getMessage()).toString());
     }
     
     Debug.print("RoleManagementDAOImpl.insertArtifactMapDetails():"+result);
     return result;
 }
 
 public ArrayList getAllGroup(){
 	  System.out.println("GeneralDBAction: getAllGroup()");
       ArrayList groupList = new ArrayList();
       PreparedStatement prepStmt = null;
       ResultSet rs = null;
       makeConnection();
  	try {

 String selectStatement = "select master_id, master_name, master_sequence from tblframeworkmaster " +
 		"where active_status='1' and show_master='1' order by master_sequence";
               
 prepStmt = con.prepareStatement(selectStatement);
 rs = prepStmt.executeQuery();
          
           while(rs.next()){
               String masterId = rs.getString(1);
               String masterName = rs.getString(2);
               String masterSeq = rs.getString(3);
               
               String[] grpList = {masterId, masterName,masterSeq};
               groupList.add(grpList);
           }
           rs.close();
           prepStmt.close();
           releaseConnection();
          
       } 
       catch(SQLException sql){
           releaseConnection();
           System.out.println("SQL Exception in GeneralDBAction.getAllGroup():" + sql.getMessage());
       }
       catch(Exception e){
           releaseConnection();
           e.printStackTrace();
       }
       return groupList;
   }
 
 public ArrayList getGrpDetails(){
	  System.out.println("RoleManagementDAOImpl: getGrpDetails()");
     ArrayList groupList = new ArrayList();
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     makeConnection();
	try {

String selectStatement = "select b.master_id, b.master_name,a.layer_value,a.layer_value_id, b.master_sequence from tblframeworklayermap a, " +
		"tblframeworkmaster b where a.master_id = b.master_id " +
		"and b.active_status='1' and b.show_master='1' order by b.master_sequence";
             
prepStmt = con.prepareStatement(selectStatement);

rs = prepStmt.executeQuery();
        
         while(rs.next()){
             String masterId = rs.getString(1);
             String masterName = rs.getString(2);
             String layerVal = rs.getString(3);
             String layerValId = rs.getString(4);
             Integer seq = rs.getInt(5);
       
             String[] grpList = {masterId,masterName,layerVal, layerValId,String.valueOf(seq)};
             groupList.add(grpList);
         }
         rs.close();
         prepStmt.close();
         releaseConnection();
        
     } 
     catch(SQLException sql){
         releaseConnection();
         System.out.println("SQL Exception in RoleManagementDAOImpl.getGrpDetails():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         e.printStackTrace();
     }
     return groupList;
 }
 
 
 public ArrayList getAllViewMapList(String viewPointId){
 	  System.out.println("RoleManagementDAOImpl: getAllViewMapList()");
       ArrayList viewPointList = new ArrayList();
       PreparedStatement prepStmt = null;
       ResultSet rs = null;
       makeConnection();
  	try {

 String selectStatement = "select (select view_point_name from tblviewpointmaster where view_point_id = a.view_point_value_id ) as View_Point, " +
 		" (select layer_value from tblframeworklayermap where layer_value_id = a.lob_value_id) as LOB,(select layer_value from tblframeworklayermap " +
 		" where layer_value_id =a.view_id) as Views,(select layer_value from tblframeworklayermap where layer_value_id = a.group_value_id) " +
 		" as GroupId,(select layer_value from tblframeworklayermap where layer_value_id = a.process_domain_value_id)  as Process_Domain_Id " +
 		" from tblframeworkmapping a,tblviewpointmaster b where a.view_point_value_id = b.view_point_id and a.view_point_value_id = ?";                
 prepStmt = con.prepareStatement(selectStatement);
 prepStmt.setString(1, viewPointId);

 rs = prepStmt.executeQuery();
          
           while(rs.next()){
               String View_Point = rs.getString(1);
               String LOB = rs.getString(2);
               String Views = rs.getString(3);
               String Groups = rs.getString(4);
               String Process_Domain = rs.getString(5);
         
               String[] viewPoint = {View_Point,LOB,Views, Groups,Process_Domain};
               viewPointList.add(viewPoint);
           }
           rs.close();
           prepStmt.close();
           releaseConnection();
          
       } 
       catch(SQLException sql){
           releaseConnection();
           System.out.println("SQL Exception in RoleManagementDAOImpl.getAllViewMapList():" + sql.getMessage());
       }
       catch(Exception e){
           releaseConnection();
           e.printStackTrace();
       }
       return viewPointList;
   }
 
 /*
  * 
  * Dhivya Here: View Point Mapping Existence
  * 
  */
 
 public boolean viewPointMapExists(String viewPntId, String lobId, String viewId, String grpId, String domProcId)
 {
     Debug.print("RoleManagementDAOImpl.viewPointMapExists() View Pnt:"+viewPntId);
     Debug.print("RoleManagementDAOImpl.viewPointMapExists() LOB:"+lobId);
     Debug.print("RoleManagementDAOImpl.viewPointMapExists() View:"+viewId);
     Debug.print("RoleManagementDAOImpl.viewPointMapExists() Grp:"+grpId);
     Debug.print("RoleManagementDAOImpl.viewPointMapExists() ProDom:"+domProcId);
    
     
     
     
     PreparedStatement prepStmt = null;
     boolean result = false;
     int cnt=0;
     
   
     makeConnection();
     try
     {
     	
         String selectStatement = "select count(*) from tblframeworkmapping where view_point_value_id=? and " +
         		"lob_value_id=? and view_id=? and group_value_id=? and process_domain_value_id=?";
         
         prepStmt = con.prepareStatement(selectStatement);
  		prepStmt.setString(1, viewPntId);  			
		prepStmt.setString(2, lobId);
		prepStmt.setString(3,viewId);  			
		prepStmt.setString(4, grpId);
		prepStmt.setString(5, domProcId);
		
                     
	   ResultSet rs = prepStmt.executeQuery();
    if(rs.next()){
         cnt = rs.getInt(1);
        if(cnt>0){
     	   result=true;   
        }
    }
           rs.close();
         prepStmt.close();
         releaseConnection();
         
     }
     catch(SQLException sql)
     {
         releaseConnection();
         Debug.print((new StringBuilder()).append("SQL Exception in RoleManagementDAOImpl.viewPointMapExists():").append(sql.getMessage()).toString());
     }
     catch(Exception e)
     {
         releaseConnection();
         Debug.print((new StringBuilder()).append("General Exception  in RoleManagementDAOImpl.viewPointMapExists():").append(e.getMessage()).toString());
     }
     
     Debug.print("RoleManagementDAOImpl.viewPointMapExists():"+result);
     return result;
 }
 
//==========================Dhivya Ends Here:=================================================  
 public Vector getLOBs(String viewPntId){
	  System.out.println("RoleManagementDAOImpl: selectLOBs()");
	Vector lobsLst = new Vector();
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     makeConnection();
	try {

String selectStatement = " select A.lob_value_id, B.layer_value from tblframeworkmapping A, tblFrameworkLayerMap B where " +
		"A.lob_value_id=B.layer_value_id and A.view_point_value_id=?";
          

prepStmt = con.prepareStatement(selectStatement);
prepStmt.setString(1, viewPntId);
rs = prepStmt.executeQuery();
        
         while(rs.next()){
             String lobValueId=rs.getString(1);
             String lobValueName= rs.getString(2);
                           
             String[] lobList = {lobValueId, lobValueName};
             lobsLst.add(lobList);
         }
         rs.close();
         prepStmt.close();
         releaseConnection();
        
     } 
     catch(SQLException sql){
         releaseConnection();
         System.out.println("SQL Exception in RoleManagementDAOImpl.selectLOBs():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         System.out.println("General Exception  in RoleManagementDAOImpl.selectLOBs():" + e.getMessage());
     }
     return lobsLst;
 }

 
 public Vector getViews(String viewPntId, String lobId){
	  System.out.println("RoleManagementDAOImpl: selectViews()");
	  Vector viewObj = new Vector();
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     String selectStatement="";
     makeConnection();
	try {

		
		selectStatement = "select A.view_id, B.layer_value from tblframeworkmapping A," +
				" tblFrameworkLayerMap B where A.view_id=B.layer_value_id and " +
				"A.view_point_value_id=? and " +
				"A.lob_value_id=?";
		
		 prepStmt = con.prepareStatement(selectStatement);
		 prepStmt.setString(1, viewPntId);
		 prepStmt.setString(2, lobId);

rs = prepStmt.executeQuery();
        
         while(rs.next()){
             String viewId = rs.getString(1);
             String viewName = rs.getString(2);
             String[] viewList = {viewId, viewName};
             viewObj.add(viewList);
         }
         rs.close();
         prepStmt.close();
         releaseConnection();
        
     } 
     catch(SQLException sql){
         releaseConnection();
         System.out.println("SQL Exception in RoleManagementDAOImpl.selectViews():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         System.out.println("General Exception  in RoleManagementDAOImpl.selectViews():" + e.getMessage());
     }
     return viewObj;
 }

public Vector getGroups(String viewPntId, String lobId){
	  System.out.println("RoleManagementDAOImpl: selectGroups()");
	  Vector grpObj = new Vector();
   PreparedStatement prepStmt = null;
   ResultSet rs = null;
   String selectStatement="";
   makeConnection();
	try {

		
		selectStatement = "select A.group_value_id, B.layer_value from tblframeworkmapping A, tblFrameworkLayerMap B where " +
				"A.group_value_id=B.layer_value_id and A.view_point_value_id=? and " +
				"A.lob_value_id=?";
		
		 prepStmt = con.prepareStatement(selectStatement);
		prepStmt.setString(1, viewPntId);
	 prepStmt.setString(2, lobId);

rs = prepStmt.executeQuery();
      
       while(rs.next()){
           String grpId = rs.getString(1);
           String grpName = rs.getString(2);
           String[] grpList = {grpId, grpName};
           grpObj.add(grpList);
       }
       rs.close();
       prepStmt.close();
       releaseConnection();
      
   } 
   catch(SQLException sql){
       releaseConnection();
       System.out.println("SQL Exception in RoleManagementDAOImpl.selectGroups():" + sql.getMessage());
   }
   catch(Exception e){
       releaseConnection();
       System.out.println("General Exception  in RoleManagementDAOImpl.selectGroups():" + e.getMessage());
   }
   return grpObj;
}

public Vector getProcessDomain(String viewPntId, String lobId, String grpId){
	  System.out.println("RoleManagementDAOImpl: selectProcessDomain()");
	  Vector proDomObj = new Vector();
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     String selectStatement="";
     makeConnection();
	try {

		
		selectStatement = "select A.process_domain_value_id, B.layer_value from tblframeworkmapping A, tblFrameworkLayerMap B where " +
				"A.process_domain_value_id=B.layer_value_id and A.view_point_value_id=? and " +
				"A.lob_value_id=? and A.group_value_id=?";
		
		 prepStmt = con.prepareStatement(selectStatement);
		prepStmt.setString(1, viewPntId);
		 prepStmt.setString(2, lobId);
		prepStmt.setString(3, grpId);

rs = prepStmt.executeQuery();
        
         while(rs.next()){
             String proDomId = rs.getString(1);
             String proDomName= rs.getString(2);
             String[] proDomList = {proDomId, proDomName};
             proDomObj.add(proDomList);
         }
         rs.close();
         prepStmt.close();
         releaseConnection();
        
     } 
     catch(SQLException sql){
         releaseConnection();
         System.out.println("SQL Exception in RoleManagementDAOImpl.selectProcessDomain():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         System.out.println("General Exception  in RoleManagementDAOImpl.selectProcessDomain():" + e.getMessage());
     }
     return proDomObj;
 }


public boolean artifactMapExists(String viewPntId, String lobId, String viewId, String grpId, String domProcId, String artifactId)
{
    Debug.print("RoleManagementDAOImpl.artifactMapExists():"+viewPntId);
    Debug.print("RoleManagementDAOImpl.artifactMapExists():"+lobId);
    Debug.print("RoleManagementDAOImpl.artifactMapExists():"+viewId);
    Debug.print("RoleManagementDAOImpl.artifactMapExists():"+grpId);
    Debug.print("RoleManagementDAOImpl.artifactMapExists():"+domProcId);
    Debug.print("RoleManagementDAOImpl.artifactMapExists():"+artifactId); 
    
    
    PreparedStatement prepStmt = null;
    boolean result = false;
    int cnt=0;
    
    
    if(lobId.equals("")){
    	lobId=null;
    	
    }
    if(viewId.equals("")){
    	viewId=null;
    	
    }
    if(grpId.equals("")){
    	grpId=null;
    	
    }
    
    if(domProcId.equals("")){
    	
    	domProcId=null;
    }
    makeConnection();
    try
    {
    	if(viewPntId!=null && lobId==null && viewId==null && grpId==null && domProcId==null && artifactId!=null){
    	
        String selectStatement = "select count(*) from tblMapArtifact where view_point_value_id=? and artifact_id=?";
        
        prepStmt = con.prepareStatement(selectStatement);
 		prepStmt.setString(1, viewPntId);  			
		prepStmt.setString(2, artifactId);
    	}else if(viewPntId!=null && lobId!=null && viewId==null && grpId==null && domProcId==null && artifactId!=null){
    		String selectStatement = "select count(*) from tblMapArtifact where view_point_value_id=? and lob_value_id=? " +
    				"and artifact_id=?";	
    	
    		 prepStmt = con.prepareStatement(selectStatement);
      		prepStmt.setString(1, viewPntId);
   		 prepStmt.setString(2, lobId);
   		prepStmt.setString(3, artifactId);
   		
    	}
    	else if(viewPntId!=null && lobId!=null && viewId!=null && grpId==null && domProcId==null && artifactId!=null){
    		String selectStatement = "select count(*) from tblMapArtifact where view_point_value_id=? and lob_value_id=? " +
    				"and view_value_id=? and artifact_id=?";	
    	
    		 prepStmt = con.prepareStatement(selectStatement);
      		prepStmt.setString(1, viewPntId);
   		 prepStmt.setString(2, lobId);
   		 prepStmt.setString(3, viewId);
   		prepStmt.setString(4, artifactId);
   		
    	}
    	else if(viewPntId!=null && lobId!=null && viewId!=null && grpId!=null && domProcId==null && artifactId!=null){
    		String selectStatement = "select count(*) from tblMapArtifact where view_point_value_id=? and lob_value_id=? " +
    				"and view_value_id=? and group_value_id=? and artifact_id=?";	
    	
    		 prepStmt = con.prepareStatement(selectStatement);
      		prepStmt.setString(1, viewPntId);
   		 prepStmt.setString(2, lobId);
   		 prepStmt.setString(3, viewId);
   		prepStmt.setString(4, grpId);
   		prepStmt.setString(5, artifactId);
   		
    	}else if(viewPntId!=null && lobId!=null && viewId!=null && grpId!=null && domProcId!=null && artifactId!=null){
    		String selectStatement = "select count(*) from tblMapArtifact where view_point_value_id=? and lob_value_id=? " +
    				"and view_value_id=? and group_value_id=? and process_domain_value_id=? and artifact_id=?";	
    	
    		 prepStmt = con.prepareStatement(selectStatement);
      		prepStmt.setString(1, viewPntId);
   		 prepStmt.setString(2, lobId);
   		prepStmt.setString(3, viewId);
   		prepStmt.setString(4, grpId);
   		prepStmt.setString(5, domProcId);
   		prepStmt.setString(6, artifactId);
    	}
       
       
       
	   ResultSet rs = prepStmt.executeQuery();
   if(rs.next()){
        cnt = rs.getInt(1);
       if(cnt>0){
    	   result=true;   
       }
   }
          rs.close();
        prepStmt.close();
        releaseConnection();
        
    }
    catch(SQLException sql)
    {
        releaseConnection();
        Debug.print((new StringBuilder()).append("SQL Exception in RoleManagementDAOImpl.artifactMapExists():").append(sql.getMessage()).toString());
    }
    catch(Exception e)
    {
        releaseConnection();
        Debug.print((new StringBuilder()).append("General Exception  in RoleManagementDAOImpl.artifactMapExists():").append(e.getMessage()).toString());
    }
    
    Debug.print("RoleManagementDAOImpl.artifactMapExists():"+result);
    return result;
}

public ArrayList getAllViewsBasedOnViewPoint(String viewPointId){
	  System.out.println("RoleManagementDAOImpl: getAllViewsBasedOnViewPoint()");
    ArrayList viewList = new ArrayList();
    PreparedStatement prepStmt = null;
    ResultSet rs = null;
    makeConnection();
	try {

String selectStatement = "select a.layer_value_id,a.layer_value from tblframeworklayermap a,tblviewpointmaster b where a.view_point_id=b.view_point_id and a.view_point_id = ?";
         

prepStmt = con.prepareStatement(selectStatement);
prepStmt.setString(1, viewPointId);

rs = prepStmt.executeQuery();
       
        while(rs.next()){
            String layerId = rs.getString(1);
            String layerName = rs.getString(2);
            String[] viList = {layerId, layerName};
            viewList.add(viList);
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
       
    } 
    catch(SQLException sql){
        releaseConnection();
        System.out.println("SQL Exception in RoleManagementDAOImpl.getAllViewsBasedOnViewPoint():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        e.printStackTrace();
    }
    return viewList;
}


public boolean addCompanyDetails(String company,String category ,String loginname)
{
    Debug.print("company-----------------"+company);
    Debug.print("Category-----------------"+category);
    
    PreparedStatement prepStmt = null;
    boolean result = false;
    makeConnection();
    try
    {
        String insertStatement = "UPDATE tblUserMaster SET company=?,category=? where login_name=?";
        prepStmt = con.prepareStatement(insertStatement);
        prepStmt.setString(1, company);
        prepStmt.setString(2, category);
        prepStmt.setString(3, loginname);
        //prepStmt.setString(3, objMap.getViewPtId());
       // prepStmt.setString(4, objMap.getGroupsId());
       // prepStmt.setString(5, objMap.getProcessDomainId());
      
        prepStmt.executeUpdate();
        prepStmt.close();
        releaseConnection();
        result = true;
        System.out.println("DBCompany------------"+company);
        System.out.println("DBcategory------------"+category);
    }
    catch(SQLException sql)
    {
        releaseConnection();
        //Debug.print((new StringBuilder()).append("SQL Exception in RoleManagementDAOImpl.insertFrameworkMapDetails():").append(sql.getMessage()).toString());
    }
    catch(Exception e)
    {
        releaseConnection();
        //Debug.print((new StringBuilder()).append("General Exception  in RoleManagementDAOImpl.insertFrameworkMapDetails():").append(e.getMessage()).toString());
    }
    
    //Debug.print("RoleManagementDAOImpl.insertFrameworkMapDetails():"+result);
    return result;
}

    
   
    /*----------------------------------user sign up action-----------------------------------------------*/
    public String insertsignup(String firstname,String lastname,String e_mail,String mobileno,
    							String institution_name, String country, String state, String city, 
    							String credit_card_type, String credit_card_no, String cvv_no,
    							String name_on_card, String expiry_date, String productPrice, 
    							String pay_subscriptionType, String purchaseType, 
    							String productPlan, String coupon_valid, String coupon_status, 
    							String coupon_value, String coupon_description, String coupon_code, 
    							String street_name, String zipcode, String customerCategory) throws RemoteException, ParseException
    {
    	String username=null;
    	String comp_id=selectcompany_id();
    	String reg_id = selectRegistration_id();
		
//		if(lastname.length()>=4){
//			username=lastname.substring(0, 4);					
//		}
//		
//		else if(lastname.length()!=0 && lastname!=null){
//			if(firstname.length()>=4){
//				username=firstname.substring(0, 4);						
//			}
//			else if(firstname.length()<4 && firstname.length()==3){
//				username=firstname+lastname.substring(0, 1);						
//			}			
//		}
//		else{
			if(firstname.length()>=4){
				username=firstname.substring(0, 4);						
			}
			else if(firstname.length()<4 && firstname.length()==3){
				username=firstname+'W';						
			}				
		//}
		
		int countcmid=reg_id.length()-4;
		  username=username+"MF"+reg_id.substring(countcmid);

		System.out.println(username);
		


		  final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			Random rnd = new Random();
			int len=8;							
			StringBuilder sb = new StringBuilder( len );
			   for( int i = 0; i < len; i++ ) {
			     sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );			
			   }							  
		String password=sb.toString();

		System.out.println(password);
    String regStatusId=null;   
    
    System.out.println("comp_id in insert method ::::::::::::::::::"+comp_id);
    //String sub_id=selectSubscriptionID();
    //String order_id = selectOrderID();
    String sub_id = getRandomNumber();
    String order_id = generateRandomString();
    
    System.out.println("coupon_valid in db action::::::::::"+coupon_valid);
    int coupon_valid_int = 1;
    Date expiryDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
    
    if(purchaseType.equalsIgnoreCase("try_now")){
    	
    	if(coupon_valid != null && coupon_valid != "null" && !(coupon_valid.equalsIgnoreCase("null"))){
    		coupon_valid_int = Integer.parseInt(coupon_valid);
    		//System.out.println("inside coupon valid coupon_valid_int "+coupon_valid_int);
    	
    
    System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(expiryDate));
    
    Calendar cal1 = Calendar.getInstance();
    cal1.setTime(expiryDate);
    cal1.set(Calendar.MONTH, (cal1.get(Calendar.MONTH)+coupon_valid_int));
    expiryDate = cal1.getTime();
	//System.out.println(expiryDate);
	System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(expiryDate));
	//String dateinsql = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(expiryDate);
	//Date newdate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(dateinsql);
	//java.sql.Date d1 = new java.sql.Date(newdate.getTime());
	//System.out.println("new date "+d1);
    
    	}else{
    		
    		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(expiryDate));
    	    
    	    Calendar cal1 = Calendar.getInstance();
    	    cal1.setTime(expiryDate);
    	    cal1.set(Calendar.MONTH, (cal1.get(Calendar.MONTH)+coupon_valid_int));
    	    expiryDate = cal1.getTime();
    		//System.out.println(expiryDate);
    		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(expiryDate));
    	}
    

    try{


    makeConnection(); 
    String insertStmt=  "Insert into tblSignUpDetails(first_name,last_name,e_mail,mobile,institution_name,country,state,city,credit_card_type,credit_card_no,cvv_no,name_on_card,expiry_date,customer_id, product_price, subscription_type, subscription_id, order_id, purchase_type, product_plan, user_name, user_password, registration_id, product_expiry_date, coupon_code, coupon_value, coupon_status, street, zipcode, company_category) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      

      prepStmt = con.prepareStatement(insertStmt);
      
      prepStmt.setString(1,firstname);
      prepStmt.setString(2,lastname);
      prepStmt.setString(3, e_mail);
      prepStmt.setString(4,mobileno);
      prepStmt.setString(5,institution_name);
      prepStmt.setString(6,country);
      prepStmt.setString(7,state);
      prepStmt.setString(8,city);
      prepStmt.setString(9,credit_card_type);
      prepStmt.setString(10,credit_card_no);
      prepStmt.setString(11,cvv_no);
      prepStmt.setString(12, name_on_card);
      prepStmt.setString(13, expiry_date);
      prepStmt.setString(14,comp_id);
      prepStmt.setString(15,productPrice);
      prepStmt.setString(16,pay_subscriptionType);
      prepStmt.setString(17,sub_id);
      prepStmt.setString(18,order_id);
      prepStmt.setString(19,purchaseType);
      prepStmt.setString(20,productPlan);
      prepStmt.setString(21,username);
      prepStmt.setString(22,password);
      prepStmt.setString(23,reg_id);
      prepStmt.setString(24,new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(expiryDate));
      prepStmt.setString(25,coupon_code);
      prepStmt.setString(26,coupon_value);
      prepStmt.setString(27,coupon_status);
      prepStmt.setString(28, street_name);
      prepStmt.setString(29, zipcode);
      prepStmt.setString(30, customerCategory);
      
      
      
     
      
      
      int cnt= prepStmt.executeUpdate();
      if(cnt>0){
          System.out.println("Customer data's are successfully inserted on database----------------->");
       regStatusId=reg_id;  
        }
       prepStmt.close();

    } 
    catch (SQLException sqe) {
      releaseConnection();
     sqe.printStackTrace();       
    } finally {
      releaseConnection();
    }
    
    }else if(purchaseType.equalsIgnoreCase("buy_now")){
    	 System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(expiryDate));
    	    
    	 if(coupon_valid != null && coupon_valid != "null" && !(coupon_valid.equalsIgnoreCase("null"))){
     		coupon_valid_int = Integer.parseInt(coupon_valid);
     		//System.out.println("inside coupon valid coupon_valid_int "+coupon_valid_int);
     	
     
		     System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(expiryDate));
		     
		     Calendar cal1 = Calendar.getInstance();
		     cal1.setTime(expiryDate);
		     cal1.set(Calendar.MONTH, (cal1.get(Calendar.MONTH)+coupon_valid_int));
		     expiryDate = cal1.getTime();
		 	//System.out.println(expiryDate);
		 	System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(expiryDate));
		 	//String dateinsql = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(expiryDate);
		 	//Date newdate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(dateinsql);
		 	//java.sql.Date d1 = new java.sql.Date(newdate.getTime());
		 	//System.out.println("new date "+d1);
		     
		     	}else{
		     		
		     		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(expiryDate));
		     	    
		     	    Calendar cal1 = Calendar.getInstance();
		     	    cal1.setTime(expiryDate);
		     	    cal1.set(Calendar.MONTH, (cal1.get(Calendar.MONTH)+coupon_valid_int));
		     	    expiryDate = cal1.getTime();
		     		//System.out.println(expiryDate);
		     		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(expiryDate));
		     	}

        try{


        makeConnection(); 
        String insertStmt=  "Insert into tblSignUpDetails(first_name,last_name,e_mail,mobile,institution_name,country,state,city,credit_card_type,credit_card_no,cvv_no,name_on_card,expiry_date,customer_id, product_price, subscription_type, subscription_id, order_id, purchase_type, product_plan, user_name, user_password, registration_id, product_expiry_date, coupon_code, coupon_value, coupon_status, street, zipcode,company_category) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
          

          prepStmt = con.prepareStatement(insertStmt);
          
          prepStmt.setString(1,firstname);
          prepStmt.setString(2,lastname);
          prepStmt.setString(3, e_mail);
          prepStmt.setString(4,mobileno);
          prepStmt.setString(5,institution_name);
          prepStmt.setString(6,country);
          prepStmt.setString(7,state);
          prepStmt.setString(8,city);
          prepStmt.setString(9,credit_card_type);
          prepStmt.setString(10,credit_card_no);
          prepStmt.setString(11,cvv_no);
          prepStmt.setString(12, name_on_card);
          prepStmt.setString(13, expiry_date);
          prepStmt.setString(14,comp_id);
          prepStmt.setString(15,productPrice);
          prepStmt.setString(16,pay_subscriptionType);
          prepStmt.setString(17,sub_id);
          prepStmt.setString(18,order_id);
          prepStmt.setString(19,purchaseType);
          prepStmt.setString(20,productPlan);
          prepStmt.setString(21,username);
          prepStmt.setString(22,password);
          prepStmt.setString(23,reg_id);
          prepStmt.setString(24,new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(expiryDate));
          prepStmt.setString(25,coupon_code);
          prepStmt.setString(26,coupon_value);
          prepStmt.setString(27,coupon_status);
          prepStmt.setString(28, street_name);
          prepStmt.setString(29, zipcode);
          prepStmt.setString(30, customerCategory);

          
          int cnt= prepStmt.executeUpdate();
          if(cnt>0){
              System.out.println("Customer data's are successfully inserted on database----------------->");
        	  regStatusId=reg_id;  
            }
           prepStmt.close();

        } 
        catch (SQLException sqe) {
          releaseConnection();
         sqe.printStackTrace();       
        } finally {
          releaseConnection();
        }
    }
    return regStatusId;

    }



    public String getUserIdbyEmailPlan(String email, String pay_productPlan, String pay_subscriptionType)
    {

    String userid=null;

       
        try {
         makeConnection();
          String str="SELECT user_id FROM tblSignUpDetails WHERE e_mail=? and product_plan=? and subscription_type=?";
                
          prepStmt = con.prepareStatement(str);
             prepStmt.setString(1,email);
             prepStmt.setString(2,pay_productPlan);
             prepStmt.setString(3,pay_subscriptionType);
             
             
             rs = prepStmt.executeQuery();
             if (rs.next()){
              userid = rs.getString(1);
                           }
              
        
             rs.close();
        prepStmt.close();
        
        releaseConnection(); 
     } 
        catch (Exception ex) {
          ex.printStackTrace();
              //Debug.print("Exception:" + ex.getMessage());
         }finally {
             releaseConnection();
         }
       
    return userid;
    }

    public String updateusersignup(String firstname,String lastname,String e_mail,String mobileno,String institution_name, String country, String state, String city, String credit_card_type, String credit_card_no, String cvv_no,String name_on_card, String expiry_date, String productPrice, String pay_subscriptionType, String purchaseType, String productPlan, String coupon_valid, String coupon_status, String coupon_value, String coupon_description, String coupon_code, String street_name, String zipcode, String customerCategory) throws RemoteException
    {
    	String username=null;
    	String comp_id=selectcompany_id();
    	String reg_id = selectRegistration_id();
		
//		if(lastname.length()>=4){
//			username=lastname.substring(0, 4);					
//		}
//		
//		else if(lastname.length()!=0 && lastname!=null){
//			if(firstname.length()>=4){
//				username=firstname.substring(0, 4);						
//			}
//			else if(firstname.length()<4 && firstname.length()==3){
//				username=firstname+lastname.substring(0, 1);						
//			}			
//		}
//		else{
			if(firstname.length()>=4){
				username=firstname.substring(0, 4);						
			}
			else if(firstname.length()<4 && firstname.length()==3){
				username=firstname+'W';						
			}				
		//}
		
		int countcmid=reg_id.length()-4;
		  username=username+"MF"+reg_id.substring(countcmid);

		System.out.println(username);
		


		  final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			Random rnd = new Random();
			int len=8;							
			StringBuilder sb = new StringBuilder( len );
			   for( int i = 0; i < len; i++ ) {
			     sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );			
			   }							  
		String password=sb.toString();

		System.out.println(password);
    
    System.out.println("comp_id in insert method ::::::::::::::::::"+comp_id);
    //String sub_id=selectSubscriptionID();
    //String order_id = selectOrderID();
    String sub_id = getRandomNumber();
    String order_id = generateRandomString();
    
    System.out.println("coupon_valid in db action::::::::::"+coupon_valid);
    int coupon_valid_int = 1;
    Date expiryDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
    
    if(coupon_valid != null && coupon_valid != "null" && !(coupon_valid.equalsIgnoreCase("null"))){
		coupon_valid_int = Integer.parseInt(coupon_valid);
		//System.out.println("inside coupon valid coupon_valid_int "+coupon_valid_int);
	

			System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(expiryDate));
			
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(expiryDate);
			cal1.set(Calendar.MONTH, (cal1.get(Calendar.MONTH)+coupon_valid_int));
			expiryDate = cal1.getTime();
			//System.out.println(expiryDate);
			System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(expiryDate));
			//String dateinsql = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(expiryDate);
			//Date newdate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(dateinsql);
			//java.sql.Date d1 = new java.sql.Date(newdate.getTime());
			//System.out.println("new date "+d1);
			
				}else{
					
					System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(expiryDate));
				    
				    Calendar cal1 = Calendar.getInstance();
				    cal1.setTime(expiryDate);
				    cal1.set(Calendar.MONTH, (cal1.get(Calendar.MONTH)+coupon_valid_int));
				    expiryDate = cal1.getTime();
					//System.out.println(expiryDate);
					System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(expiryDate));
				}
    
    String regStatusId=null;   

    try{

    makeConnection(); 

    String insertStmt=  "update tblSignUpDetails SET first_name = ?, last_name = ?, e_mail = ?, mobile = ?, institution_name = ?, country = ?, state = ?, city = ?, credit_card_type = ?, credit_card_no = ?, cvv_no = ?, name_on_card = ?, expiry_date = ?, product_price = ? , subscription_type = ? , subscription_id = ? , order_id = ?, purchase_type = ? , product_plan = ?  , registration_id = ? , product_expiry_date = ? ,coupon_code = ? ,coupon_value = ? ,coupon_status = ?, street = ?, zipcode = ?, company_category= ? where e_mail = ? ";

      prepStmt = con.prepareStatement(insertStmt);
      
      prepStmt.setString(1,firstname);
      prepStmt.setString(2,lastname);
      prepStmt.setString(3, e_mail);
      prepStmt.setString(4,mobileno);
      prepStmt.setString(5,institution_name);
      prepStmt.setString(6,country);
      prepStmt.setString(7,state);
      prepStmt.setString(8,city);
      prepStmt.setString(9,credit_card_type);
      prepStmt.setString(10,credit_card_no);
      prepStmt.setString(11,cvv_no);
      prepStmt.setString(12, name_on_card);
      prepStmt.setString(13, expiry_date);
      //prepStmt.setString(14,comp_id);
      prepStmt.setString(14,productPrice);
      prepStmt.setString(15,pay_subscriptionType);
      prepStmt.setString(16,sub_id);
      prepStmt.setString(17,order_id);
      prepStmt.setString(18,purchaseType);
      prepStmt.setString(19,productPlan);
      //prepStmt.setString(21,username);
      //prepStmt.setString(22,password);
      prepStmt.setString(20,reg_id);
      prepStmt.setString(21,new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(expiryDate));
      prepStmt.setString(22,coupon_code);
      prepStmt.setString(23,coupon_value);
      prepStmt.setString(24,coupon_status);
      prepStmt.setString(25, street_name);
      prepStmt.setString(26, zipcode);
      prepStmt.setString(27, customerCategory);
      prepStmt.setString(28, e_mail);
      
      
     
      
      
      int cnt= prepStmt.executeUpdate();
      if(cnt>0){
          System.out.println("Customer data's are successfully updated on database----------------->");
    	  regStatusId=reg_id;  
        }
       prepStmt.close();

    } 
    catch (SQLException sqe) {
    sqe.printStackTrace();
      releaseConnection();
            
    } finally {
      releaseConnection();
    }
    return regStatusId;

    }
    public String selectBuyInstitutionId(String email, String regStatusId)
    {
     String map_id=null;
      
         
          try {
           makeConnection();
            //String str="SELECT customer_id FROM tblSignUpDetails WHERE e_mail=? and purchase_type='buy_now' and registration_id = ?";
           String str="SELECT customer_id FROM tblSignUpDetails WHERE e_mail=? and registration_id = ?";
                  
            prepStmt = con.prepareStatement(str);
               prepStmt.setString(1,email);
               prepStmt.setString(2,regStatusId);
              
               rs = prepStmt.executeQuery();
               if (rs.next()){
                map_id = rs.getString(1);
                             }
                
          
               rs.close();
          prepStmt.close();
          
          releaseConnection(); 
       } 
          catch (Exception ex) {
            ex.printStackTrace();
                //Debug.print("Exception:" + ex.getMessage());
           }finally {
               releaseConnection();
           }
         
      return map_id;
    }
    
    public String selectCustomerOrderId(String email, String regStatusId)
    {
     String order_id=null;
      
         
          try {
           makeConnection();
            //String str="SELECT order_id FROM tblSignUpDetails WHERE e_mail=? and purchase_type='buy_now' and registration_id = ?";
           String str="SELECT order_id FROM tblSignUpDetails WHERE e_mail=? and registration_id = ?";
                  
            prepStmt = con.prepareStatement(str);
               prepStmt.setString(1,email);
               prepStmt.setString(2,regStatusId);
              
               rs = prepStmt.executeQuery();
               if (rs.next()){
            	   order_id = rs.getString(1);
                             }
                
          
               rs.close();
          prepStmt.close();
          
          releaseConnection(); 
       } 
          catch (Exception ex) {
            ex.printStackTrace();
                //Debug.print("Exception:" + ex.getMessage());
           }finally {
               releaseConnection();
           }
         
      return order_id;
    }
    
    public String selectTryCustomerOrderId(String email, String regStatusId)
    {
     String order_id=null;
      
         
          try {
           makeConnection();
            String str="SELECT order_id FROM tblSignUpDetails WHERE e_mail=? and purchase_type='try_now' and registration_id = ?";
                  
            prepStmt = con.prepareStatement(str);
               prepStmt.setString(1,email);
               prepStmt.setString(2,regStatusId);
              
               rs = prepStmt.executeQuery();
               if (rs.next()){
            	   order_id = rs.getString(1);
                             }
                
          
               rs.close();
          prepStmt.close();
          
          releaseConnection(); 
       } 
          catch (Exception ex) {
            ex.printStackTrace();
                //Debug.print("Exception:" + ex.getMessage());
           }finally {
               releaseConnection();
           }
         
      return order_id;
    }
    
    public String selectTryInstitutionId(String email, String regStatusId)
    {
     String map_id=null;
      
         
          try {
           makeConnection();
            String str="SELECT customer_id FROM tblSignUpDetails WHERE e_mail=? and purchase_type='try_now' and registration_id=?";
                  
            prepStmt = con.prepareStatement(str);
               prepStmt.setString(1,email);
               prepStmt.setString(2,regStatusId);
              
               rs = prepStmt.executeQuery();
               if (rs.next()){
                map_id = rs.getString(1);
                             }
                
          
               rs.close();
          prepStmt.close();
          
          releaseConnection(); 
       } 
          catch (Exception ex) {
            ex.printStackTrace();
                //Debug.print("Exception:" + ex.getMessage());
           }finally {
               releaseConnection();
           }
         
      return map_id;
    }
    

    public String selectcompany_id()  throws RemoteException {
        
        String empid = null;
        String regYearMonth = null;
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		   //get current date time with Date()
		   Date date = new Date();
		   System.out.println("Here is registred date :::::::::::::::::"+dateFormat.format(date));
		   String dateval = dateFormat.format(date);
		   String [] regSplitDate = dateval.split("/");
		   String regYear = regSplitDate[0];
		  String regMonth = regSplitDate[1];
		  String regshortYear = regYear.substring(2);
		  regYearMonth = regshortYear+regMonth;
		  System.out.println("regYearMonth :::::::"+regYearMonth);
        
        PreparedStatement prepSelect = null;
        makeConnection();
        try {
        	System.out.println("inside the try :::::::::::::::::::");
            String selectStatement = "SELECT MAX(CAST(SUBSTRING(customer_id, 7, len(customer_id)-6) AS int)) FROM tblSignUpDetails";
            System.out.println("before prepare stmt ::::::::::::::::");
            prepSelect = con.prepareStatement(selectStatement);
            
            ResultSet rs = prepSelect.executeQuery();
            while (rs.next()) {
             empid = rs.getString(1);
             System.out.println("emp id in while loop"+empid);
            }
            if (empid == null)
             empid = "0";
           
            long nextId = Long.valueOf(empid).longValue();
            
            if(nextId==0){
            	
                //nextId = 10101000;
     		   
     		   nextId = 1;
     		   System.out.println("nextId in ==0 :::::::::::"+nextId);
            } else{
            	
                nextId = nextId+1;
                System.out.println("nextId in != 0 :::::::::::"+nextId);
            }
            rs.close();
            prepSelect.close();
            empid = regYearMonth+"MF"+Long.toString(nextId);
            System.out.println("empid in select cust id :::::::::::::::::"+empid);
           
            //prepStmt.close();
            releaseConnection();
           
        } catch(SQLException sql){
        	System.out.println(sql);
            releaseConnection();
                  }
       
        return empid;
    }
    
    
public String getUserIDByUserName(String globalUserName)  throws RemoteException {
        
        String userId = "";
        PreparedStatement prepSelect = null;
        makeConnection();
        try {
            String selectStatement = "SELECT user_id from tblUserMaster where login_name = ?";
            
            prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,globalUserName);
            ResultSet rs = prepSelect.executeQuery();
            while (rs.next()) {
            	userId = rs.getString(1);
            }
           
            rs.close();
            prepSelect.close();
           
           
            //prepStmt.close();
            releaseConnection();
           
        } catch(SQLException sql){
            releaseConnection();
                  }
       
        return userId;
    }


public boolean insertFrameworkMapDetails(String viewPointId,HLCMenuListVO objMap, HLCMenuListVO objArti)
{
    Debug.print("RoleManagementDAOImpl.insertFrameworkMapDetails():");
    Debug.print("RoleManagementDAOImpl.insertFrameworkMapDetails():"+viewPointId);
    
    PreparedStatement prepStmt = null;
    boolean result = false;
    makeConnection();
    try
    {
        String insertStatement = "insert into tblframeworkmapping (view_point_value_id, lob_value_id, view_id, group_value_id, process_domain_value_id) values( ?,?,?,?,?)";
        prepStmt = con.prepareStatement(insertStatement);
        prepStmt.setString(1, viewPointId);
        prepStmt.setString(2, objMap.getLobLayerId());
        prepStmt.setString(3, objMap.getViewPtId());
        prepStmt.setString(4, objMap.getGroupsId());
        prepStmt.setString(5, objMap.getProcessDomainId());
      
        prepStmt.executeUpdate();
        prepStmt.close();
        releaseConnection();
        result = true;
    }
    catch(SQLException sql)
    {
        releaseConnection();
        Debug.print((new StringBuilder()).append("SQL Exception in RoleManagementDAOImpl.insertFrameworkMapDetails():").append(sql.getMessage()).toString());
    }
    catch(Exception e)
    {
        releaseConnection();
        Debug.print((new StringBuilder()).append("General Exception  in RoleManagementDAOImpl.insertFrameworkMapDetails():").append(e.getMessage()).toString());
    }
    
    Debug.print("RoleManagementDAOImpl.insertFrameworkMapDetails():"+result);
    return result;
}
    
public String getCompanyDetByUserId(String login_name)  throws RemoteException {
    
    String user_code = "";
    PreparedStatement prepSelect = null;
    makeConnection();
    try {
        String selectStatement = "SELECT company from tblUserMaster where login_name = ?";
        
        prepSelect = con.prepareStatement(selectStatement);
        prepSelect.setString(1,login_name);
        ResultSet rs = prepSelect.executeQuery();
        while (rs.next()) {
        	user_code = rs.getString(1);
        }
       
        rs.close();
        prepSelect.close();
       
       
        //prepStmt.close();
        releaseConnection();
       
    } catch(SQLException sql){
        releaseConnection();
              }
   
    return user_code;
}

public ArrayList getPostCompanyDetailsByUserId(String companyName)  throws RemoteException {
    
    ArrayList postCompDetails = new ArrayList();
    PreparedStatement prepSelect = null;
    makeConnection();
    try {
        String selectStatement = "SELECT user_id, customer_id, institution_name, company_category from tblSignUpDetails where institution_name = ?";
        
        prepSelect = con.prepareStatement(selectStatement);
        prepSelect.setString(1,companyName);
        ResultSet rs = prepSelect.executeQuery();
        while (rs.next()) {
        	String uniquePostCompanyId = rs.getString(1);
        	String postCompanyId = rs.getString(2);
        	String postCompanyName = rs.getString(3);
        	String PostCompanyCategory = rs.getString(4);
        	
        	String[] custtemp={uniquePostCompanyId,postCompanyId,postCompanyName,PostCompanyCategory};
      	  		System.out.println("-----IN DB---------uniquePostCompanyId"+uniquePostCompanyId);
	            System.out.println("-----IN DB---------postCustomerId"+postCompanyId);
	            System.out.println("-----IN DB---------postCompanyName"+postCompanyName);
	            System.out.println("-----IN DB---------PostCompanyCategory"+PostCompanyCategory);
	            postCompDetails.add(custtemp);
        }
       
        rs.close();
        prepSelect.close();
       
       
        //prepStmt.close();
        releaseConnection();
       
    } catch(SQLException sql){
        releaseConnection();
              }
   
    return postCompDetails;
}



public ArrayList getAllCompanyDetails(){
	  System.out.println("GeneralDBAction: getAllGroup()");
     ArrayList companyList = new ArrayList();
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     makeConnection();
	try {

String selectStatement = "select institution_name from tblSignUpDetails";
             
prepStmt = con.prepareStatement(selectStatement);
rs = prepStmt.executeQuery();
        
         while(rs.next()){
             String companyName = rs.getString(1);
             System.out.println("companyname in db Action :::::::::::::::::::"+companyName);
             
             String[] compList = {companyName};
             companyList.add(compList);
             
         }
         rs.close();
         prepStmt.close();
         releaseConnection();
        
     } 
     catch(SQLException sql){
         releaseConnection();
         System.out.println("SQL Exception in GeneralDBAction.getAllCompanyDetails():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         e.printStackTrace();
     }
     return companyList;
 }


public String getRoleIdByRoleName(String roleName) {
    Debug.print("GeneralDBAction.getRoleIdByRoleName():" + roleName);
    String roleId = null;
    makeConnection();
	try {
        String selectStatement = "select role_id from tblRoleMaster where role_name = ? " ;
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1,roleName);
        ResultSet rs = prepStmt.executeQuery();
        if (rs.next()){
        	roleId = rs.getString(1);
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch (SQLException e) {
        releaseConnection();
        Debug.print("GeneralDBAction:: Could not find any from roleName" + e.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction getRoleIdByRoleName:" + e.getMessage());
    }
    Debug.print("GeneralDBAction isRoleNameExist():" + roleId);
    return roleId;
}  

//----------------------------------Inserting For Contact  ------------------------------------------

public boolean insertUserEnquriyDetails(String name,String email,String phone,String message,String company)
{
	Debug.print("user same sucess:"+name);
	Debug.print("user same sucess:"+email);
	Debug.print("user same sucess:"+phone);
	Debug.print("user same sucess:"+message);
	
	PreparedStatement preStmt = null;
  boolean result = false;
  makeConnection();
  try
  {
      String insertStmt = "insert into tblUserEnquiryDetails(name,e_mail,phone,msg,company_name)values(?,?,?,?,?)";
      preStmt = con.prepareStatement(insertStmt);
      preStmt.setString(1,name);
      preStmt.setString(2,email);
      preStmt.setString(3,phone);
      preStmt.setString(4,message);
      preStmt.setString(5,company);
     
      preStmt.executeUpdate();
      preStmt.close();
      releaseConnection();
      result = true;
  }
catch(SQLException sql)
      {
          releaseConnection();
          Debug.print((new StringBuilder()).append("SQL Exception in insertUserEnquriyDetails:").append(sql.getMessage()).toString());
      }
      catch(Exception e)
      {
          releaseConnection();
          Debug.print((new StringBuilder()).append("General Exception  in insertUserEnquriyDetails:").append(e.getMessage()).toString());
      }
      
      Debug.print("result sucess:"+result);
      return result;
  }





//-------------------------------------------finished here--------------------------------------------------------------------------
   


//------------------------------------select subscription id-------------------------------------
public String selectSubscriptionID()  throws RemoteException {
    
    String empid = null;
    PreparedStatement prepSelect = null;
    makeConnection();
    try {
        String selectStatement = "SELECT max(cast(subscription_id as int)) from tblSignUpDetails";
        
        prepSelect = con.prepareStatement(selectStatement);
        
        ResultSet rs = prepSelect.executeQuery();
        while (rs.next()) {
         empid = rs.getString(1);
        }
        if (empid == null)
         empid = "0";
        
        long nextId = Long.valueOf(empid).longValue();
        
        if(nextId==0){
            nextId = 1921294000; //this id reffered by subid000 == 1921294000
        } else{
            nextId = nextId+1;
        }
        rs.close();
        prepSelect.close();
        empid = Long.toString(nextId);
       
        //prepStmt.close();
        releaseConnection();
       
    } catch(SQLException sql){
        releaseConnection();
              }
   
    return empid;
}
//----------------------------------------------------------------------------------------------------------

//------------------------------------select subscription id-------------------------------------
public String selectOrderID()  throws RemoteException {
  
  String empid = null;
  PreparedStatement prepSelect = null;
  makeConnection();
  try {
      String selectStatement = "SELECT max(cast(order_id as int)) from tblSignUpDetails";
      
      prepSelect = con.prepareStatement(selectStatement);
      
      ResultSet rs = prepSelect.executeQuery();
      while (rs.next()) {
       empid = rs.getString(1);
      }
      if (empid == null)
       empid = "0";
      
      long nextId = Long.valueOf(empid).longValue();
      
      if(nextId==0){
          nextId = 1518494000;  //this id reffered by ordid000 == 1518494000
      } else{
          nextId = nextId+1;
      }
      rs.close();
      prepSelect.close();
      empid = Long.toString(nextId);
     
      //prepStmt.close();
      releaseConnection();
     
  } catch(SQLException sql){
      releaseConnection();
            }
 
  return empid;
}
//----------------------------------------------------------------------------------------------------------

/** * This method generates random string * @return */
public String generateRandomString()
{ 
	  final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	  final int RANDOM_STRING_LENGTH = 8;
	StringBuffer randStr = new StringBuffer();
	for(int i=0; i<RANDOM_STRING_LENGTH; i++)
	{ 
		int number = getRandomNumberForAlphaNum(); 
		char ch = CHAR_LIST.charAt(number); 
		randStr.append(ch); 
		} 
	return randStr.toString(); 
	} 
/** * This method generates random numbers * @return int */ 
private int getRandomNumberForAlphaNum() 
{ 
	final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	final int RANDOM_STRING_LENGTH = 8;
	int randomInt = 0; Random randomGenerator = new Random();
	randomInt = randomGenerator.nextInt(CHAR_LIST.length());
	if (randomInt - 1 == -1)
	{ 
		return randomInt;
	} else 
{
	return randomInt - 1;
}
	} 

private String getRandomNumber() 
{ 
	String randomInt = new String(); 
	Random r = new Random();
	String otp = new String();
	for(int i=0 ; i < 16 ; i++) {
		randomInt += r.nextInt(10);
	}
	
	return randomInt;
}

//===================profile pic starts here====================

public String getuserid(String usrname){
	 Debug.print("GeneralDBAction.getRoleNameByRoleId():");
	 PreparedStatement prepstmt = null;
	 ResultSet rs = null;
	 makeConnection();
	 String userid = "";
	 try{
		 String sqlQuery = "Select user_id from tblUserMaster where login_name=?";
		 prepstmt = con.prepareStatement(sqlQuery);
		 prepstmt.setString(1, usrname);
		 rs = prepstmt.executeQuery();
		 rs.next();
		 userid = rs.getString(1);
		 rs.close();
		 prepstmt.close();
		 releaseConnection();
	 }
	 catch(SQLException sql)
 {
     releaseConnection();
     Debug.print((new StringBuilder()).append("SQL Exception in GeneralDBAction.getRoleNameByRoleId():").append(sql.getMessage()).toString());
 }
 catch(Exception e)
 {
     releaseConnection();
     Debug.print((new StringBuilder()).append("General Exception  in GeneralDBAction.getRoleNameByRoleId():").append(e.getMessage()).toString());
 }
	 
	 return userid;
	 
}

public String getuser_status(String login){
	 Debug.print("GeneralDBAction.getRoleNameByRoleId():");
	 PreparedStatement prepstmt = null;
	 ResultSet rs = null;
	 makeConnection();
	 String userid = "";
	 try{
		 String sqlQuery = "Select user_id from tblUserMaster where login_name=?";
		 prepstmt = con.prepareStatement(sqlQuery);
		 prepstmt.setString(1, login);
		
		 rs = prepstmt.executeQuery();
		 rs.next();
		 userid = rs.getString(1);
		 rs.close();
		 prepstmt.close();
		 releaseConnection();
	 }
	 catch(SQLException sql)
 {
     releaseConnection();
     Debug.print((new StringBuilder()).append("SQL Exception in GeneralDBAction.getRoleNameByRoleId():").append(sql.getMessage()).toString());
 }
 catch(Exception e)
 {
     releaseConnection();
     Debug.print((new StringBuilder()).append("General Exception  in GeneralDBAction.getRoleNameByRoleId():").append(e.getMessage()).toString());
 }
	 
	 return userid;
	 
}

public String getpicture_path(String user_id){
	 Debug.print("GeneralDBAction.getRoleNameByRoleId():");
	 PreparedStatement prepstmt = null;
	 ResultSet rs = null;
	 makeConnection();
	 String filepath = "";
	 try{
		 String sqlQuery = "Select picture_path from tbluserpictures where user_id=?";
		 prepstmt = con.prepareStatement(sqlQuery);
		 prepstmt.setString(1, user_id);
		 rs = prepstmt.executeQuery();
		 rs.next();
		 filepath = rs.getString(1);
		 rs.close();
		 prepstmt.close();
		 releaseConnection();
	 }
	 catch(SQLException sql)
 {
     releaseConnection();
     Debug.print((new StringBuilder()).append("SQL Exception in GeneralDBAction.getRoleNameByRoleId():").append(sql.getMessage()).toString());
 }
 catch(Exception e)
 {
     releaseConnection();
     Debug.print((new StringBuilder()).append("General Exception  in GeneralDBAction.getRoleNameByRoleId():").append(e.getMessage()).toString());
 }
	 
	 return filepath;
	 
}
public String adduserpicture(String user_id,String fileLocation)
{
	 
	String status=null;
		    
		    	  
		        try {
		        	makeConnection();
		        	 String str="insert into tbluserpictures(user_id,picture_path) values (?,?)";
				     				     
				        prepStmt = con.prepareStatement(str);
			            prepStmt.setString(1,user_id);
			            prepStmt.setString(2,fileLocation);
			            
			            
			            prepStmt.executeUpdate();
                    status="Insert Successfully";
			 	       prepStmt.close();
			       
			       releaseConnection(); 
			    } 
		        catch (Exception ex) {
			        	ex.printStackTrace();
			             //Debug.print("Exception:" + ex.getMessage());
			        }finally {
			            releaseConnection();
			        }
		       
		    return status;
}

public String selectRegistration_id()  throws RemoteException {
    
    String empid = null;
    PreparedStatement prepSelect = null;
    makeConnection();
    try {
        String selectStatement = "SELECT max(cast(registration_id as int)) from tblSignUpDetails";
        
        prepSelect = con.prepareStatement(selectStatement);
        
        ResultSet rs = prepSelect.executeQuery();
        while (rs.next()) {
         empid = rs.getString(1);
        }
        if (empid == null)
         empid = "0";
        
        long nextId = Long.valueOf(empid).longValue();
        
        if(nextId==0){
            nextId = 10101000;
        } else{
            nextId = nextId+1;
        }
        rs.close();
        prepSelect.close();
        empid = Long.toString(nextId);
       
        //prepStmt.close();
        releaseConnection();
       
    } catch(SQLException sql){
        releaseConnection();
              }
   
    return empid;
}


public String selectRegistrationId(String email, String regStatusId)
{
 String reg_id=null;
  
     
      try {
       makeConnection();
        String str="SELECT registration_id FROM tblSignUpDetails WHERE e_mail=? and registration_id=?";
              
        prepStmt = con.prepareStatement(str);
           prepStmt.setString(1,email);
           prepStmt.setString(2,regStatusId);
          
           rs = prepStmt.executeQuery();
           if (rs.next()){
            reg_id = rs.getString(1);
                         }
            
      
           rs.close();
      prepStmt.close();
      
      releaseConnection(); 
   } 
      catch (Exception ex) {
        ex.printStackTrace();
            //Debug.print("Exception:" + ex.getMessage());
       }finally {
           releaseConnection();
       }
     
  return reg_id;
}


public String selectCustomerUserName(String email, String regStatusId)
{
 String CustomerUserName=null;
  
     
      try {
       makeConnection();
        String str="SELECT user_name FROM tblSignUpDetails WHERE e_mail=? and registration_id=?";
              
        prepStmt = con.prepareStatement(str);
           prepStmt.setString(1,email);
           prepStmt.setString(2,regStatusId);
          
           rs = prepStmt.executeQuery();
           if (rs.next()){
        	   CustomerUserName = rs.getString(1);
                         }
            
      
           rs.close();
      prepStmt.close();
      
      releaseConnection(); 
   } 
      catch (Exception ex) {
        ex.printStackTrace();
            //Debug.print("Exception:" + ex.getMessage());
       }finally {
           releaseConnection();
       }
     
  return CustomerUserName;
}


public String selectCustomerpassword(String email, String regStatusId)
{
 String Customerpassword=null;
  
     
      try {
       makeConnection();
        String str="SELECT user_password FROM tblSignUpDetails WHERE e_mail=? and registration_id=?";
              
        prepStmt = con.prepareStatement(str);
           prepStmt.setString(1,email);
           prepStmt.setString(2,regStatusId);
          
           rs = prepStmt.executeQuery();
           if (rs.next()){
        	   Customerpassword = rs.getString(1);
                         }
            
      
           rs.close();
      prepStmt.close();
      
      releaseConnection(); 
   } 
      catch (Exception ex) {
        ex.printStackTrace();
            //Debug.print("Exception:" + ex.getMessage());
       }finally {
           releaseConnection();
       }
     
  return Customerpassword;
}


public String selectCustomerEmailandPass(String email)
{
 String Customerpassword=null;
  
     
      try {
    	  makeMySqlConnection();
        String str="select EMAIL from tcustomer where EMAIL=?";
              
        prepStmt = con.prepareStatement(str);
           prepStmt.setString(1,email);
          
           rs = prepStmt.executeQuery();
           if (rs.next()){
        	   Customerpassword = rs.getString(1);
                         }
            
      
           rs.close();
      prepStmt.close();
      
      releaseConnection(); 
   } 
      catch (Exception ex) {
        ex.printStackTrace();
            //Debug.print("Exception:" + ex.getMessage());
       }finally {
           releaseConnection();
       }
     
  return Customerpassword;
}





//=============================Mastrer Account starts here=======================================
  
//=============================updateprofile=======================================
public String updateprofile(String new_email,String username, String password,String e_mail) {
		
		System.out.println(" ---------------------from inside database----------------------");	
	     System.out.println("username:"+username);
	     System.out.println("password:"+password);
	     System.out.println("e_mail:"+e_mail);
	     System.out.println("new_email:"+new_email);
	     String result=null;
	     try{
	      makeConnection(); 
	     String insertStmt=  "update tblSignUpDetails SET user_name = ?, user_password = ?, e_mail =? where e_mail=?";
	      
	     prepStmt = con.prepareStatement(insertStmt);
	       
	        prepStmt.setString(1,username);
	        prepStmt.setString(2,password);
	        prepStmt.setString(3,e_mail);
	        prepStmt.setString(4,new_email);            
	        
	      int cnt= prepStmt.executeUpdate();
	       if(cnt>0){        
	        result ="User update successfully";  
	       }
	       prepStmt.close();
	       System.out.println("==========username:"+username);
		     System.out.println("=======password:"+password);
		     System.out.println("======e_mail:"+e_mail);
		     System.out.println("=======new_email:"+new_email);
	    } 
	    catch (SQLException sqe) {
	    	sqe.printStackTrace();
	        releaseConnection();
	              
	    } finally {
	        releaseConnection();
	    }
	  return result;
	     
	    }

//=============================updateproductpackage=======================================
public String updateproductpackage(String new_email,String plantype,String subscriptiontype, String paymentHistory,String subscriptionExpiry) {
	System.out.println(" from inside databaseuser");	
   System.out.println("plantype:"+plantype);
   System.out.println("subscriptiontype:"+subscriptiontype);
   System.out.println("paymentHistory:"+paymentHistory);
   System.out.println("subscriptionExpiry:"+subscriptionExpiry);
   String packageresult="User update Failed";
   try{
    makeConnection(); 
   String insertStmt=  "update tblSignUpDetails SET product_plan = ?, subscription_type = ?, product_price = ? where e_mail=?";
      
   prepStmt = con.prepareStatement(insertStmt);
     
      prepStmt.setString(1,plantype);
      prepStmt.setString(2,subscriptiontype);
      prepStmt.setString(3,paymentHistory);
      prepStmt.setString(4,new_email);
      
       
     int cnt= prepStmt.executeUpdate();
     if(cnt>0){        
      packageresult ="User update successfully";  
     }
     prepStmt.close();
    
  } 
  catch (SQLException sqe) {
  	sqe.printStackTrace();
      releaseConnection();
            
  } finally {
      releaseConnection();
  }
return packageresult;
   
}
//=============================updatesignupuserdetails=======================================
public String updatesignupuserdetails(String new_email,String username, String password,String e_mail,String institutionname) {
		
		System.out.println(" from inside database updatesignupuserdetails");	
	     System.out.println("username:"+username);
	     System.out.println("password:"+password);
	     System.out.println("e_mail:"+e_mail);
	     System.out.println("new_email:"+new_email);
	     System.out.println("institutionname:"+institutionname);
	     String result=null;
	     try{
	      makeConnection(); 
	     String insertStmt=  "update tblSignUpDetails SET user_name = ?, user_password = ?, e_mail =?, institution_name=? where e_mail=?";
	      
	     prepStmt = con.prepareStatement(insertStmt);
	       
	        prepStmt.setString(1,username);
	        prepStmt.setString(2,password);
	        prepStmt.setString(3,e_mail);
	        prepStmt.setString(4,institutionname);  
	        prepStmt.setString(5,new_email);
	        
	      int cnt= prepStmt.executeUpdate();
	       if(cnt>0){        
	        result ="User update successfully";  
	       }
	       prepStmt.close();
	       System.out.println(" ========from inside database updatesignupuserdetails");	
		     System.out.println("======username:"+username);
		     System.out.println("==========password:"+password);
		     System.out.println("====e_mail:"+e_mail);
		     System.out.println("========new_email:"+new_email);
		     System.out.println("======institutionname:"+institutionname);
	    } 
	    catch (SQLException sqe) {
	    	sqe.printStackTrace();
	        releaseConnection();
	              
	    } finally {
	        releaseConnection();
	    }
	  return result;
	     
	    }
//==================================update Security===============================
public String updatesecurity(String Productplan, String mobile, String e_mail, String last_login){
	System.out.println(" from inside databaseuser");	
   System.out.println("Productplan:"+Productplan);
   System.out.println("mobile:"+mobile);
   System.out.println("e_mail:"+e_mail);
  System.out.println("last_login:"+last_login);
  String packageresult="User update Failed";
   try{
    makeConnection(); 
   String insertStmt=  "update tblSignUpDetails SET product_plan = ?, mobile = ?, e_mail = ? last_login=? where e_mail=?";
      
   prepStmt = con.prepareStatement(insertStmt);
     
      prepStmt.setString(1,Productplan);
      prepStmt.setString(2,mobile);
      prepStmt.setString(3,e_mail);
     prepStmt.setString(4,last_login);
     prepStmt.setString(5,e_mail);
      
       
     int cnt= prepStmt.executeUpdate();
     if(cnt>0){        
      packageresult ="User update successfully";  
      System.out.print("==============Security=================");
      System.out.print("plantype"+Productplan);
      System.out.print("mobile"+mobile);
      System.out.println("e_mail"+e_mail);
     System.out.println("login_date"+last_login);
     }
     prepStmt.close();
    
  } 
  catch (SQLException sqe) {
  	sqe.printStackTrace();
      releaseConnection();
            
  } finally {
      releaseConnection();
  }
return packageresult;
   
}

//=============================getemail_id=======================================
public String getemail_id(String userId){
	 Debug.print("GeneralDBAction.getRoleNameByRoleId():");
	 PreparedStatement prepstmt = null;
	 ResultSet rs = null;
	 makeConnection();
	 String emailId = "";
	 try{
		 String sqlQuery = "Select email_id from tblUserMaster where user_id=?";
		 prepstmt = con.prepareStatement(sqlQuery);
		 prepstmt.setString(1, userId);
		 rs = prepstmt.executeQuery();
		 rs.next();
		 emailId = rs.getString(1);
		 rs.close();
		 prepstmt.close();
		 releaseConnection();
	 }
	 catch(SQLException sql)
     {
         releaseConnection();
         Debug.print((new StringBuilder()).append("SQL Exception in GeneralDBAction.getRoleNameByRoleId():").append(sql.getMessage()).toString());
     }
     catch(Exception e)
     {
         releaseConnection();
         Debug.print((new StringBuilder()).append("General Exception  in GeneralDBAction.getRoleNameByRoleId():").append(e.getMessage()).toString());
     }
	 
	 return emailId;
	 
 }
//===========================product getprofile_details starts here=========================================
public ArrayList getprofile_details(String emailId)
  {
	 ArrayList prof_det= new ArrayList(); 
  	String username=null;
  	String password=null;
  	String institution_name=null;
  	String e_mail=null;
  	String customer_id=null;
     try {
		        	makeConnection();
		        	 String str="SELECT user_name,user_password,institution_name,e_mail,customer_id FROM tblSignUpDetails WHERE e_mail=?";
				     				     
				        prepStmt = con.prepareStatement(str);
			            prepStmt.setString(1,emailId);
			            
			            
			            rs = prepStmt.executeQuery();
			            while (rs.next()){
			            	username = rs.getString(1);
			            	password = rs.getString(2);
			            	institution_name=rs.getString(3);
			            	e_mail=rs.getString(4);
			            	customer_id=rs.getString(5);
			            	
			            	 
			            	String[] temp={username,password,institution_name,e_mail,customer_id};
			            	  System.out.println("-----IN DB---------emailId"+emailId);
					            System.out.println("-----IN DB---------username"+username);
					            System.out.println("-----IN DB---------password"+password);
					            System.out.println("-----IN DB---------institution_name"+institution_name);
					            System.out.println("-----IN DB---------e_mail"+e_mail);
			            	prof_det.add(temp);
			                          }
			            
			          
			            
			            rs.close();
			       prepStmt.close();
			       
			       releaseConnection(); 
			    } 
		        catch (Exception ex) {
			        	ex.printStackTrace();
			             //Debug.print("Exception:" + ex.getMessage());
			        }finally {
			            releaseConnection();
			        }
		       
		    return prof_det;
  }

//===========================product package starts here=========================================
public ArrayList getproduct_package(String emailId)
  {
	 ArrayList prod_package= new ArrayList(); 
  	String Productplan=null;
  	String subscription_plan=null;
  	String amount=null;
  
     try {
		        	makeConnection();
		        	 String str="SELECT product_plan,subscription_type,product_price FROM tblSignUpDetails WHERE e_mail=?";
				     				     
				        prepStmt = con.prepareStatement(str);
			            prepStmt.setString(1,emailId);
			            
			            
			            rs = prepStmt.executeQuery();
			            while (rs.next()){
			            	Productplan = rs.getString(1);
			            	subscription_plan = rs.getString(2);
			            	amount=rs.getString(3);
			                String[] temp={Productplan,subscription_plan,amount};
			            	prod_package.add(temp);
			                          }
				     			    
			       
			            rs.close();
			       prepStmt.close();
			       
			       releaseConnection(); 
			    } 
		        catch (Exception ex) {
			        	ex.printStackTrace();
			             //Debug.print("Exception:" + ex.getMessage());
			        }finally {
			            releaseConnection();
			        }
		       
		    return prod_package;
  }

//===========================getdate=========================================
  public String getdate(String emailId) {
		Debug.print("GeneralDBAction.getRoleNameByRoleId():");
		 PreparedStatement prepstmt = null;
		 ResultSet rs = null;
		 makeConnection();
		 String date = "";
		 try{
			 String sqlQuery = "Select date from tblSignUpDetails where e_mail=?";
			 prepstmt = con.prepareStatement(sqlQuery);
			 prepstmt.setString(1, emailId);
			 rs = prepstmt.executeQuery();
			 rs.next();
			 date = rs.getString(1);
			 rs.close();
			 prepstmt.close();
			 releaseConnection();
		 }
		 catch(SQLException sql)
	     {
	         releaseConnection();
	         Debug.print((new StringBuilder()).append("SQL Exception in GeneralDBAction.getRoleNameByRoleId():").append(sql.getMessage()).toString());
	     }
	     catch(Exception e)
	     {
	         releaseConnection();
	         Debug.print((new StringBuilder()).append("General Exception  in GeneralDBAction.getRoleNameByRoleId():").append(e.getMessage()).toString());
	     }
		 
		 return date;
		
	}

//===========================getsecurity_details=========================================
  public ArrayList getsecurity_details(String emailId)
	    {
		 ArrayList security_det= new ArrayList(); 
	    	String Productplan=null;
	    	String mobile=null;
	    	String e_mail=null;
	    	String last_login=null;
	    	String notifi_setting=null;
	    
	       try {
			        	makeConnection();
			        	 String str="SELECT product_plan,mobile,e_mail,last_login FROM tblSignUpDetails WHERE e_mail=?";
					     				     
					        prepStmt = con.prepareStatement(str);
				            prepStmt.setString(1,emailId);
				            
				            
				            rs = prepStmt.executeQuery();
				            while (rs.next()){
				            	Productplan = rs.getString(1);
				            	mobile = rs.getString(2);
				            	e_mail=rs.getString(3);
				            	last_login=rs.getString(4);
				            	//notifi_setting=rs.getString(5);
				            	
				                String[] temp={Productplan,mobile,e_mail,last_login,notifi_setting};
				                System.out.print("--------------------In DB------------------");
				                System.out.print("Product Plan"+Productplan);
				                System.out.print("mobile"+mobile);
				                System.out.print("last_login"+last_login);
				                System.out.print("notifi_settings"+notifi_setting);
				                security_det.add(temp);
				                          }
					     			    
				       
				            rs.close();
				       prepStmt.close();
				       
				       releaseConnection(); 
				    } 
			        catch (Exception ex) {
				        	ex.printStackTrace();
				             //Debug.print("Exception:" + ex.getMessage());
				        }finally {
				            releaseConnection();
				        }
			       
			    return security_det;
	    }
  

  // ends here=======================================
  //==========================customer support stats here========================
  

  public ArrayList getCustomerDetailsById(String customerId){
  	ArrayList custDetails = null;
  	 makeConnection();
  		try {
  	       
  	       

  	         String selectStatement="select * from tblSignUpDetails where customer_id=?" ;


  	         Debug.print(" selectStatement "+selectStatement);
  	        
  	         prepStmt = con.prepareStatement(selectStatement);
  	         prepStmt.setString(1, customerId);
  	       
  	        
  	         rs = prepStmt.executeQuery();
  	         custDetails = new ArrayList();
  	         while(rs.next()){

  	        	
  	             String first_name = rs.getString(1);
  	             String last_name = rs.getString(2);
  	             String e_mail = rs.getString(3);
  	             String mobile = rs.getString(4);
  	             String institution_name = rs.getString(5);
  	             String country = rs.getString(6);
  	             String state = rs.getString(7);
  	             String city = rs.getString(8);
  	             String credit_card_type = rs.getString(9);
  	             String credit_card_no = rs.getString(10);
  	             String cvv_no = rs.getString(11);
  	             String name_on_card = rs.getString(12);
  	             String expiry_date = rs.getString(13);
  	             String customer_id = rs.getString(14);
  	             String date = rs.getString(15);
  	             String product_price = rs.getString(17);
  	             String subscription_type = rs.getString(18);
  	             String subscription_id = rs.getString(19);
  	             String order_id = rs.getString(20);
  	             String purchase_type = rs.getString(21);
  	             String product_plan = rs.getString(22);
  	             String user_name = rs.getString(23);
  	             String user_password = rs.getString(24);
  	             String registration_id = rs.getString(25);
  	             System.out.println("first_name in the db action :::::::::::::::::::::::"+first_name);
  	             
  	             
  	             String[] custList = {first_name, last_name,e_mail, mobile, institution_name, country, state,city, credit_card_type, credit_card_no,
  	            		 cvv_no, name_on_card, expiry_date, customer_id, date,product_price, subscription_type, subscription_id,
  	            		 order_id, purchase_type, product_plan,user_name, user_password, registration_id};
  	             custDetails.add(custList);
  	         }
  	         rs.close();
  	         prepStmt.close();
  	         releaseConnection();
  	       
  	     }
  	    
  	     catch(Exception e){
  	         releaseConnection();
  	         Debug.print("General Exception  in GeneralDBAction.getAllPermission():" + e.getMessage());
  	     }
  	
  	return custDetails;
  }
  
  
 public boolean  changeCustomerPass(String getCustomerId){
	   makeConnection();
	   boolean result = false;
	   
	   final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();
		int len=8;							
		StringBuilder sb = new StringBuilder( len );
		   for( int i = 0; i < len; i++ ) {
		     sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );			
		   }							  
	String updatePassword=sb.toString();

	System.out.println(updatePassword);
		try {
	       
	       

	         String selectStatement="update tblSignUpDetails set user_password = ? where customer_id=?" ;


	         Debug.print(" selectStatement "+selectStatement);
	        
	         prepStmt = con.prepareStatement(selectStatement);
	         prepStmt.setString(1, updatePassword);
	         prepStmt.setString(2, getCustomerId);
	       
	        
	        
	         int cnt= prepStmt.executeUpdate();
		       if(cnt>0){        
		        result =true;  
		       }
		       prepStmt.close();
		}catch(Exception e){
			System.err.println();
		}finally{
			releaseConnection();
		}
	   return result;
 }
  
  
  
  
  
  
//===================================SQL Query End here=================================================   
  
  
  
  
  
  public String getLastLoginDetails(String userId) {
		Debug.print("GeneralDBAction.getRoleNameByRoleId():");
		 PreparedStatement prepstmt = null;
		 ResultSet rs = null;
		 makeConnection();
		 String last_login = "";
		 try{
			 String sqlQuery = "Select login_date from tblUserMaster where user_id=?";
			 prepstmt = con.prepareStatement(sqlQuery);
			 prepstmt.setString(1, userId);
			 rs = prepstmt.executeQuery();
			 rs.next();
			 last_login = rs.getString(1);
			 rs.close();
			 prepstmt.close();
			 releaseConnection();
		 }
		 catch(SQLException sql)
	     {
	         releaseConnection();
	         Debug.print((new StringBuilder()).append("SQL Exception in GeneralDBAction.getRoleNameByRoleId():").append(sql.getMessage()).toString());
	     }
	     catch(Exception e)
	     {
	         releaseConnection();
	         Debug.print((new StringBuilder()).append("General Exception  in GeneralDBAction.getRoleNameByRoleId():").append(e.getMessage()).toString());
	     }
		 
		 return last_login;
		
	}
  
  public ArrayList getEmailAndPassByUsrname(String regUserName) {

	  ArrayList emailPassDetails = null;
	  	 makeConnection();
	  		try {
	  	       
	  	       

	  	         String selectStatement="select email_id,password from tblUserMaster where login_name=?" ;


	  	         Debug.print(" selectStatement "+selectStatement);
	  	        
	  	         prepStmt = con.prepareStatement(selectStatement);
	  	         prepStmt.setString(1, regUserName);
	  	       
	  	        
	  	         rs = prepStmt.executeQuery();
	  	       emailPassDetails = new ArrayList();
	  	         while(rs.next()){

	  	        	
	  	             String email_id = rs.getString(1);
	  	             String user_pass = rs.getString(2);
	  	             
	  	     
	  	             
	  	             
	  	             String[] emailPassList = {email_id, user_pass};
	  	           emailPassDetails.add(emailPassList);
	  	         }
	  	         rs.close();
	  	         prepStmt.close();
	  	         releaseConnection();
	  	       
	  	     }
	  	    
	  	     catch(Exception e){
	  	         releaseConnection();
	  	         Debug.print("General Exception  in GeneralDBAction.getAllPermission():" + e.getMessage());
	  	     }
	  	
	  	return emailPassDetails;
	}
  
  public ArrayList getFirstLastUserNameByUserId(String userId) {

	  ArrayList userNameDetails = null;
	  	 makeConnection();
	  		try {
	  	       
	  	       

	  	         String selectStatement="select first_name,last_name,login_name from tblUserMaster where user_id=?" ;


	  	         Debug.print(" selectStatement "+selectStatement);
	  	        
	  	         prepStmt = con.prepareStatement(selectStatement);
	  	         prepStmt.setString(1, userId);
	  	       
	  	        
	  	         rs = prepStmt.executeQuery();
	  	       userNameDetails = new ArrayList();
	  	         while(rs.next()){

	  	        	
	  	             String firstName = rs.getString(1);
	  	             String lastName = rs.getString(2);
	  	           String userName = rs.getString(3);
	  	             
	  	     
	  	             
	  	             
	  	             String[] userList = {firstName, lastName,userName};
	  	           userNameDetails.add(userList);
	  	         }
	  	         rs.close();
	  	         prepStmt.close();
	  	         releaseConnection();
	  	       
	  	     }
	  	    
	  	     catch(Exception e){
	  	         releaseConnection();
	  	         Debug.print("General Exception  in GeneralDBAction.getFirstLastUserNameByUserId():" + e.getMessage());
	  	     }
	  	
	  	return userNameDetails;
	}


/*
  
  public String getCompanyCategoryByLoginId(String login_name)  throws RemoteException {
	    
	    String companyCategory = "";
	    PreparedStatement prepSelect = null;
	    makeConnection();
	    try {
	        String selectStatement = "SELECT category from tblUserMaster where login_name = ?";
	        
	        prepSelect = con.prepareStatement(selectStatement);
	        prepSelect.setString(1,login_name);
	        ResultSet rs = prepSelect.executeQuery();
	        while (rs.next()) {
	        	companyCategory = rs.getString(1);
	        }
	       
	        rs.close();
	        prepSelect.close();
	       
	       
	        //prepStmt.close();
	        releaseConnection();
	       
	    } catch(SQLException sql){
	        releaseConnection();
	              }
	   
	    return companyCategory;
	}
	
	*/
//==========================================Profile Picture Upload========================================
  
  public String getLoginDetailsByLoginName(String login_name)  throws RemoteException {
	    
	    String org_pass = "";
	    PreparedStatement prepSelect = null;
	    makeConnection();
	    try {
	        String selectStatement = "SELECT password from tblUserMaster where login_name = ?";
	        
	        prepSelect = con.prepareStatement(selectStatement);
	        prepSelect.setString(1,login_name);
	        ResultSet rs = prepSelect.executeQuery();
	        while (rs.next()) {
	        	org_pass = rs.getString(1);
	        }
	        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
	        textEncryptor.setPassword("sa");
	        org_pass = textEncryptor.decrypt(org_pass);
	       
	        rs.close();
	        prepSelect.close();
	       
	       
	        //prepStmt.close();
	        releaseConnection();
	       
	    } catch(SQLException sql){
		    	org_pass = null;
		        releaseConnection();
	       } catch(Exception e){
	            	  org_pass = null;
	            	  releaseConnection();
	              }
	   
	    return org_pass;
	}
  
  
  public String getLoginNameStatusLoginName(String login_name)  throws RemoteException {
	    
	    String org_username = "";
	    PreparedStatement prepSelect = null;
	    makeConnection();
	    try {
	        String selectStatement = "SELECT login_name from tblUserMaster where login_name = ?";
	        
	        prepSelect = con.prepareStatement(selectStatement);
	        prepSelect.setString(1,login_name);
	        ResultSet rs = prepSelect.executeQuery();
	        while (rs.next()) {
	        	org_username = rs.getString(1);
	        }
	        	       
	        rs.close();
	        prepSelect.close();
	       
	       
	        //prepStmt.close();
	        releaseConnection();
	       
	    } catch(SQLException sql){
	    	org_username = null;
		        releaseConnection();
	       } catch(Exception e){
	    	   org_username = null;
	            	  releaseConnection();
	              }
	   
	    return org_username;
	}


	

	public ArrayList getCouponDetails(String coupon_code) {
		Debug.print("GeneralDBAction.getCouponDetails():");
	     ArrayList coupon_details = null;
	     PreparedStatement prepStmt = null;
	     ResultSet rs = null;
	     makeConnection();
		try {
	         
	         String selectStatement="SELECT coupon_status, coupon_offer, coupon_description, valid_month from tblCouponDetails where coupon_code=?";
	        
	         Debug.print(" selectStatement "+selectStatement);
	       
	         prepStmt = con.prepareStatement(selectStatement);
	         prepStmt.setString(1,coupon_code);
	         rs = prepStmt.executeQuery();
	         coupon_details = new ArrayList();
	         while(rs.next()){

	             String coupon_status = rs.getString(1);
	             String coupon_value = rs.getString(2);
	             String coupon_description = rs.getString(3);
	             String valid_month = rs.getString(4);
	               
	             String[] couponList = {coupon_status, coupon_value,coupon_description, valid_month};
	             coupon_details.add(couponList);
	         }
	         rs.close();
	         prepStmt.close();
	         releaseConnection();
	      
	     }
	     catch(SQLException sql){
	         releaseConnection();
	         Debug.print("SQL Exception in GeneralDBAction.getCouponDetails():" + sql.getMessage());
	     }
	     catch(Exception e){
	         releaseConnection();
	         Debug.print("General Exception  in GeneralDBAction.getCouponDetails():" + e.getMessage());
	     }
	     return coupon_details;
	}

	public boolean getCouponStatusIndb(String coupon_code) {
		
		boolean result=false;
	 	makeConnection();
	       try {
	    	   if(coupon_code!=null){
	     	  String selectStmt="select coupon_code from tblCouponDetails where coupon_code = ? and coupon_status='active'";          	  
	           
	         
	           prepStmt = con.prepareStatement(selectStmt);
	           prepStmt.setString(1, coupon_code);
	           rs=prepStmt.executeQuery();
	           if(rs.next())
	           {
	         	
	         	   result = true;
	           }
	          
	          
	           
	           prepStmt.close();
	           releaseConnection();}
	    	   else{
	    		   result = false;   
	    	   }
	    	   System.out.println("coupon result=="+result);
	       } catch(SQLException sql){
	           releaseConnection();
	           System.out.println("SQL Exception in GeneralDBAction.getCouponStatusIndb():" + sql.getMessage());
	       } catch(Exception e){
	           releaseConnection();
	           System.out.println("General Exception  in GeneralDBAction.getCouponStatusIndb():" + e.getMessage());
	       }
	 	
		return result;
	}
	
	public String getcouponoffer(String coupon)
    {
    	 
    	String off=null;
		    
		    	  
		        try {
		        	makeConnection();
		        	 String str="SELECT coupon_offer FROM tblCouponDetails WHERE coupon_code=? and coupon_status='active'";
				     				     
				        prepStmt = con.prepareStatement(str);
			            prepStmt.setString(1,coupon);
			            
			            
			            rs = prepStmt.executeQuery();
			            if (rs.next()){
			            	off = rs.getString(1);
			            	System.out.println("offer inside the dBAction----------------"+off);
			                          }
				      rs.close();
			       prepStmt.close();
			       
			       releaseConnection(); 
			    } 
		        catch (Exception ex) {
			        	ex.printStackTrace();
			             //Debug.print("Exception:" + ex.getMessage());
			        }finally {
			        	releaseConnection();
			        }
		       
		    return off;
    }
 
 public void  updatecouponstatus(String couponcode)
    {
    	 
    	String couponstatus="deactive";
		    
		    	  
		        try {
		        	makeConnection();
		        	 String str="update tblCouponDetails set coupon_status=? WHERE coupon_code=?";
				     				     
				        prepStmt = con.prepareStatement(str);
			            prepStmt.setString(1,couponstatus);
			            prepStmt.setString(2,couponcode);
			            
			            prepStmt.executeUpdate();
				      rs.close();
			       prepStmt.close();
			       
			       releaseConnection(); 
			    } 
		        catch (Exception ex) {
			        	ex.printStackTrace();
			             //Debug.print("Exception:" + ex.getMessage());
			        }finally {
			        	releaseConnection();
			        }
		       
		    
    }

 public boolean updatePaymentStatus(String transactionId, String emailId,
			String registrationId, String paymentStatus, String productStatus) {
		boolean updatetransStatus = false;
		try {
				if(transactionId != null && !(transactionId.equalsIgnoreCase("null"))){
			    	makeConnection();
			    	 String str="update tblSignUpDetails set transaction_id=? , payment_status = ?, product_status = ? WHERE registration_id = ? and e_mail = ?";
				     				     
				        prepStmt = con.prepareStatement(str);
			            prepStmt.setString(1,transactionId);
			            prepStmt.setString(2,paymentStatus);
			            prepStmt.setString(3,productStatus);
			            prepStmt.setString(4,registrationId);
			            prepStmt.setString(5,emailId);
			            
			            prepStmt.executeUpdate();
			            updatetransStatus = true;
				      rs.close();
				      prepStmt.close();
			       
			       releaseConnection(); 
				}else if(transactionId == null || transactionId.equalsIgnoreCase("null")){
					makeConnection();
			    	 String str="update tblSignUpDetails set payment_status = ?, product_status = ? WHERE registration_id = ? and e_mail = ?";
				     				     
				        prepStmt = con.prepareStatement(str);
			            prepStmt.setString(1,paymentStatus);
			            prepStmt.setString(2,productStatus);
			            prepStmt.setString(3,registrationId);
			            prepStmt.setString(4,emailId);
			            
			            prepStmt.executeUpdate();
			            updatetransStatus = true;
				      rs.close();
				      prepStmt.close();
			       
			       releaseConnection(); 
				}
		    } 
		    catch (Exception ex) {
		        	ex.printStackTrace();
		        	updatetransStatus = false;
		        	}finally {
		        	releaseConnection();
		        }
		return updatetransStatus;
	}
 
 public boolean updateProductStatus(String emailId,
			String registrationId, String productStatus) {
		boolean updatetransStatus = false;
		try {
	    	makeConnection();
	    	 String str="update tblSignUpDetails set product_status = ? WHERE registration_id = ? and e_mail = ?";
		     				     
		        prepStmt = con.prepareStatement(str);
	            prepStmt.setString(1,productStatus);
	            prepStmt.setString(2,registrationId);
	            prepStmt.setString(3,emailId);
	            
	            prepStmt.executeUpdate();
	            updatetransStatus = true;
		      rs.close();
	       prepStmt.close();
	       
	       releaseConnection(); 
		    } 
		    catch (Exception ex) {
		        	ex.printStackTrace();
		        	updatetransStatus = false;
		        	}finally {
		        	releaseConnection();
		        }
		return updatetransStatus;
	}
    
  
  /*---------------------------mysql connection ended here------------------------------*/
 
 public ArrayList getFullLoginDetailsByEmailId(String emailId) {
     Debug.print("GeneralDBAction.getFullLoginDetailsByEmailId():");
     PreparedStatement prepStmt = null;
     ArrayList loginDet = new ArrayList();
     makeConnection();
     try {
         String selectStatement = "SELECT login_name, first_name, last_name FROM  " + DBHelper.USEA_MMS_USERMASTER +
                 "  WHERE  email_id = ?";
         
         prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1, emailId);
         
         rs = prepStmt.executeQuery();
         while (rs.next()) {
             String loginName = rs.getString(1);
             String firstName = rs.getString(2);
             String lastName = rs.getString(3);
             
             String[] fullList = {loginName,firstName,lastName};
             loginDet.add(fullList);
         }
         rs.close();
         prepStmt.close();
         releaseConnection();
     } catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.getFullLoginDetailsByEmailId():" + sql.getMessage());
     } catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.getFullLoginDetailsByEmailId():" + e.getMessage());
     }
     return loginDet;
 }
 
 
 
 
 
 /*------------------------job Requirements------------------------------*/
 
 public String selectJobPostId()  throws RemoteException {
	    
	    String jobPostId = null;
	    PreparedStatement prepSelect = null;
	    makeConnection();
	    try {
	        String selectStatement = "SELECT MAX(CAST(SUBSTRING(jobPostId, 5, len(jobPostId)-4) AS int)) from tblMFPostRequirements";
	        
	        prepSelect = con.prepareStatement(selectStatement);
	        
	        ResultSet rs = prepSelect.executeQuery();
	        while (rs.next()) {
	        	jobPostId = rs.getString(1);
	        	System.out.println("jobPostId------------>"+jobPostId);
	        }
	        if (jobPostId == null)
	        	jobPostId = "0";
	        
	        long nextId = Long.valueOf(jobPostId).longValue();
	        
	        if(nextId==0){
	        	System.out.println("nextId-----if==0 ------>"+nextId);
	            nextId = 100;
	        } else{
	            nextId = nextId+1;
	        	System.out.println("nextId-----if!=0 ------>"+nextId);

	        }
	        rs.close();
	        prepSelect.close();
	        jobPostId = "POST"+Long.toString(nextId);
	       
	        //prepStmt.close();
	        releaseConnection();
	       
	    } catch(SQLException sql){
	        releaseConnection();
	              }
	   
	    return jobPostId;
	}
 
 public String selectRequirementId()  throws RemoteException {
	    
	    String ReqId = null;
	    
	    PreparedStatement prepSelect = null;
	    makeConnection();
	    try {
	    	System.out.println("inside the try :::::::::::::::::::");
	        String selectStatement = "SELECT MAX(CAST(SUBSTRING(requirement_id, 4, len(requirement_id)-3) AS int)) FROM tblMFPostRequirements";
	        System.out.println("before prepare stmt ::::::::::::::::");
	        prepSelect = con.prepareStatement(selectStatement);
	        
	        ResultSet rs = prepSelect.executeQuery();
	        while (rs.next()) {
	        	ReqId = rs.getString(1);
	         System.out.println("emp id in while loop"+ReqId);
	        }
	        if (ReqId == null)
	        	ReqId = "0";
	       
	        long nextId = Long.valueOf(ReqId).longValue();
	        
	        if(nextId==0){
	        	
	            //nextId = 10101000;
	 		   
	 		   nextId = 1;
	 		   System.out.println("nextId in ==0 :::::::::::"+nextId);
	        } else{
	        	
	            nextId = nextId+1;
	            System.out.println("nextId in != 0 :::::::::::"+nextId);
	        }
	        rs.close();
	        prepSelect.close();
	        ReqId = "REQ"+Long.toString(nextId);
	        System.out.println("ReqId :::::::::::::::::"+ReqId);
	       
	        //prepStmt.close();
	        releaseConnection();
	       
	    } catch(SQLException sql){
	    	System.out.println(sql);
	        releaseConnection();
	              }
	   
	    return ReqId;
	}

public boolean insertPostRequirementDetails(PostRequirementsBean objPostReq) throws RemoteException, SQLException{
	String jobPostId = null;
	jobPostId = selectJobPostId();
	boolean postJobStatus = false;
	try{


	    makeConnection(); 
	    String insertStmt=  "INSERT INTO tblMFPostRequirements (jobPostId, requirement_id, uniqueJobPostCompanyId, jobPostCompanyId, jobPostCompanyName,jobPostUserId, jobPostUserName, jobPostUserRoleId, jobPostUserRoleName,"+
            "ClientReferenceID, ReferenceID, ClientIndustry, PositionID, Position, InterviewProcess, VisaAccepted, Skills, JobType, LocationField, NumberOfOpenings, RecruiterEmail,"+
            "StartDate, EndDate, RequirementStatus, Rate, Duration, LocalRequired, Salary, FlexiblityOnSalary, FlexiblityOnHrlyRate, DateOnHold, ExtraDocumentsRequired, minExperience,"+
            "Notes, jobResponsibilites, JobTitle, jobPostCompanyCategory,maxExperience) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	      

	      prepStmt = con.prepareStatement(insertStmt);
	      
	      prepStmt.setString(1,jobPostId);
	      prepStmt.setString(2,selectRequirementId());
	      prepStmt.setString(3,objPostReq.getuniqueJobPostCompanyId());
	      prepStmt.setString(4,objPostReq.getjobPostCompanyId());
	      prepStmt.setString(5,objPostReq.getjobPostCompanyName());
	      prepStmt.setString(6,objPostReq.getjobPostUserId());
	      prepStmt.setString(7,objPostReq.getjobPostUserName());
	      prepStmt.setString(8,objPostReq.getjobPostUserRoleId());
	      prepStmt.setString(9,objPostReq.getjobPostUserRoleName());
	      prepStmt.setString(10,objPostReq.getClientReferenceID());
	      prepStmt.setString(11,objPostReq.getReferenceID());
	      prepStmt.setString(12, objPostReq.getClientIndustry());
	      prepStmt.setString(13, objPostReq.getPositionID());
	      prepStmt.setString(14,objPostReq.getPosition());
	      prepStmt.setString(15,objPostReq.getInterviewProcess());
	      prepStmt.setString(16,objPostReq.getVisaAccepted());
	      prepStmt.setString(17,objPostReq.getSkills());
	      prepStmt.setString(18,objPostReq.getJobType());
	      prepStmt.setString(19,objPostReq.getLocationField());
	      prepStmt.setString(20,objPostReq.getNumberOfOpenings());
	      prepStmt.setString(21,objPostReq.getRecruiterEmail());
	      prepStmt.setString(22,objPostReq.getStartDate());
	      prepStmt.setString(23,objPostReq.getEndDate());
	      prepStmt.setString(24,objPostReq.getRequirementStatus());
//	      float rateInFloat = Float.parseFloat(objPostReq.getRate());
//	        System.out.println(rateInFloat);
	      prepStmt.setString(25,objPostReq.getRate());
	      prepStmt.setString(26,objPostReq.getDuration());
	      prepStmt.setString(27,objPostReq.getLocalRequired());
	      prepStmt.setString(28, objPostReq.getSalary());
	      prepStmt.setString(29, objPostReq.getFlexiblityOnSalary());
	      prepStmt.setString(30, objPostReq.getFlexiblityOnHrlyRate());
	      prepStmt.setString(31, objPostReq.getDateOnHold());
	      prepStmt.setString(32, objPostReq.getExtraDocumentsRequired());
	      //prepStmt.setString(33, objPostReq.getRequiredExperience());
	      prepStmt.setString(33, objPostReq.getminExperience());
	      prepStmt.setString(34, objPostReq.getNotes());
	      prepStmt.setString(35, objPostReq.getjobResponsibilites());
	      prepStmt.setString(36, objPostReq.getJobTitle());
	      prepStmt.setString(37, objPostReq.getjobPostCompanyCategory());
	      prepStmt.setString(38, objPostReq.getmaxExperience());
	      
	      int cnt= prepStmt.executeUpdate();
	      if(cnt>0){
	          System.out.println("Post Requirements are successfully inserted into database----------------->");
	          postJobStatus = true;
	      }
	       prepStmt.close();

	    } 
	    catch (SQLException sqe) {
	      releaseConnection();
	     sqe.printStackTrace();  
	     postJobStatus = false;
	    } finally {
	      releaseConnection();
	    }
	return postJobStatus;
}

public ArrayList getAllPostReq(String jobPostCompanyUniqueId, String companyJobPostCategory) throws RemoteException { 
    Debug.print("GeneralDBAction getAllPostReq");
    ArrayList results =(ArrayList)selectAllPostReq(jobPostCompanyUniqueId,companyJobPostCategory);
    return results;    
 }

public ArrayList selectAllPostReq(String jobPostCompanyUniqueId,String companyJobPostCategory){
    Debug.print("GeneralDBAction.selectAllPostReq():");
    ArrayList postReqList = null;
    PreparedStatement prepStmt = null;
    ResultSet rs = null;
    makeConnection();
   
	try {
		String selectStatement = null;
		if(companyJobPostCategory.equalsIgnoreCase("buyer")){
        selectStatement = " SELECT requirement_id ,jobPostCompanyName ,ClientReferenceID ,ReferenceID ,ClientIndustry ,PositionID ,Position"+
      ",InterviewProcess ,VisaAccepted ,Skills ,JobType ,LocationField ,NumberOfOpenings ,RecruiterEmail ,StartDate ,EndDate ,RequirementStatus"+
      ",Rate ,Duration ,LocalRequired ,Salary ,FlexiblityOnSalary ,FlexiblityOnHrlyRate ,DateOnHold ,ExtraDocumentsRequired ,minExperience"+
      ",Notes ,jobResponsibilites ,jobPostDate, post_req_uniqueId, JobTitle, jobPostCompanyCategory, uniqueJobPostCompanyId"+
      ", jobPostCompanyId, jobPostUserId, jobPostUserName, jobPostUserRoleId, jobPostUserRoleName,maxExperience from tblMFPostRequirements where"+
      " uniqueJobPostCompanyId=? order by jobPostDate DESC";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, jobPostCompanyUniqueId);
		}else{
			selectStatement = " SELECT requirement_id ,jobPostCompanyName ,ClientReferenceID ,ReferenceID ,ClientIndustry ,PositionID ,Position"+
				      ",InterviewProcess ,VisaAccepted ,Skills ,JobType ,LocationField ,NumberOfOpenings ,RecruiterEmail ,StartDate ,EndDate ,RequirementStatus"+
				      ",Rate ,Duration ,LocalRequired ,Salary ,FlexiblityOnSalary ,FlexiblityOnHrlyRate ,DateOnHold ,ExtraDocumentsRequired ,minExperience"+
				      ",Notes ,jobResponsibilites ,jobPostDate, post_req_uniqueId, JobTitle, jobPostCompanyCategory, uniqueJobPostCompanyId"+
				      ", jobPostCompanyId, jobPostUserId, jobPostUserName, jobPostUserRoleId, jobPostUserRoleName,maxExperience from tblMFPostRequirements order by jobPostDate DESC";
		prepStmt = con.prepareStatement(selectStatement);
		}
        
        rs = prepStmt.executeQuery();
        postReqList = new ArrayList();
        while(rs.next()){
    		String requirementId = rs.getString(1);
    		String jobPostCompanyName = rs.getString(2);
        	String ClientReferenceID = rs.getString(3);
    		String ReferenceID = rs.getString(4);
    		String ClientIndustry = rs.getString(5);
    		String PositionID = rs.getString(6);
    		String Position = rs.getString(7);
    		String InterviewProcess = rs.getString(8);
    		String VisaAccepted = rs.getString(9);
    		String Skills = rs.getString(10);
    		String JobType = rs.getString(11);
    		String LocationField = rs.getString(12);
    		String NumberOfOpenings = rs.getString(13);
    		String RecruiterEmail = rs.getString(14);
    		String StartDate = rs.getString(15);
    		String EndDate = rs.getString(16);
    		String RequirementStatus = rs.getString(17); 
    		String Rate = rs.getString(18);
    		String Duration = rs.getString(19);
    		String LocalRequired = rs.getString(20);
    		String Salary = rs.getString(21);
    		String FlexiblityOnSalary = rs.getString(22);
    		String FlexiblityOnHrlyRate = rs.getString(23);
    		String DateOnHold = rs.getString(24);
    		String ExtraDocumentsRequired = rs.getString(25);
    		String minExperience = rs.getString(26);
    		String Notes = rs.getString(27);
    		String jobResponsibilites = rs.getString(28);
    		String jobPostDate = rs.getString(29);
    		String postReqUniqueId = rs.getString (30);
    		String JobTitle = rs.getString(31);
    		String jobPostCompanyCategory = rs.getString(32);
    		String uniqueJobPostCompanyId = rs.getString(33);
    		String jobPostCompanyId = rs.getString(34);
    		String jobPostUserId = rs.getString(35);
    		String jobPostUserName = rs.getString(36);
    		String jobPostUserRoleId = rs.getString(37);
    		String jobPostUserRoleName = rs.getString(38);
    		String maxExperience = rs.getString(39);
            String[] ReqList = {postReqUniqueId, requirementId,jobPostCompanyName, ClientReferenceID, ReferenceID,
            		ClientIndustry, PositionID, Position, InterviewProcess, VisaAccepted, Skills, JobType, LocationField,
            		NumberOfOpenings, RecruiterEmail, StartDate, EndDate, RequirementStatus, Rate, Duration, LocalRequired,
            		Salary, FlexiblityOnSalary, FlexiblityOnHrlyRate, DateOnHold, ExtraDocumentsRequired,
            		minExperience, Notes, jobResponsibilites, jobPostDate, JobTitle, jobPostCompanyCategory,
            		uniqueJobPostCompanyId, jobPostCompanyId, jobPostUserId, jobPostUserName, jobPostUserRoleId,
            		jobPostUserRoleName,maxExperience};
            postReqList.add(ReqList);
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.selectAllPostReq():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.selectAllPostReq():" + e.getMessage());
    }
    return postReqList;
}  

public ArrayList getPostReqByUniqueId(String postReqByUniqueId) throws RemoteException { 
    Debug.print("GeneralDBAction getPostReqByUniqueId");
    ArrayList results =(ArrayList)selectPostReqByUniqueId(postReqByUniqueId);
    return results;    
 }

public ArrayList selectPostReqByUniqueId(String postReqByUniqueId){
    Debug.print("GeneralDBAction.selectPostReqByUniqueId():");
    ArrayList postReqList = null;
    PreparedStatement prepStmt = null;
    ResultSet rs = null;
    makeConnection();
	try {
        String selectStatement = " SELECT requirement_id ,jobPostCompanyName ,ClientReferenceID ,ReferenceID ,ClientIndustry ,PositionID ,Position"+
      ",InterviewProcess ,VisaAccepted ,Skills ,JobType ,LocationField ,NumberOfOpenings ,RecruiterEmail ,StartDate ,EndDate ,RequirementStatus"+
      ",Rate ,Duration ,LocalRequired ,Salary ,FlexiblityOnSalary ,FlexiblityOnHrlyRate ,DateOnHold ,ExtraDocumentsRequired ,minExperience"+
      ",Notes ,jobResponsibilites ,jobPostDate, post_req_uniqueId, JobTitle, jobPostUserId, maxExperience from tblMFPostRequirements where post_req_uniqueId = ?"+
      " order by jobPostDate DESC";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, postReqByUniqueId);
        rs = prepStmt.executeQuery();
        postReqList = new ArrayList();
        while(rs.next()){
    		String requirementId = rs.getString(1);
    		String jobPostCompanyName = rs.getString(2);
        	String ClientReferenceID = rs.getString(3);
    		String ReferenceID = rs.getString(4);
    		String ClientIndustry = rs.getString(5);
    		String PositionID = rs.getString(6);
    		String Position = rs.getString(7);
    		String InterviewProcess = rs.getString(8);
    		String VisaAccepted = rs.getString(9);
    		String Skills = rs.getString(10);
    		String JobType = rs.getString(11);
    		String LocationField = rs.getString(12);
    		String NumberOfOpenings = rs.getString(13);
    		String RecruiterEmail = rs.getString(14);
    		String StartDate = rs.getString(15);
    		String EndDate = rs.getString(16);
    		String RequirementStatus = rs.getString(17); 
    		String Rate = rs.getString(18);
    		String Duration = rs.getString(19);
    		String LocalRequired = rs.getString(20);
    		String Salary = rs.getString(21);
    		String FlexiblityOnSalary = rs.getString(22);
    		String FlexiblityOnHrlyRate = rs.getString(23);
    		String DateOnHold = rs.getString(24);
    		String ExtraDocumentsRequired = rs.getString(25);
    		//String RequiredExperience = rs.getString(26);
    		String minExperience = rs.getString(26);
    		String Notes = rs.getString(27);
    		String jobResponsibilites = rs.getString(28);
    		String jobPostDate = rs.getString(29);
    		String postReqUniqueId = rs.getString (30);
    		String JobTitle = rs.getString(31);
    		String jobPostUserId = rs.getString(32);
    		String maxExperience = rs.getString(33);
            String[] ReqList = {postReqUniqueId, requirementId,jobPostCompanyName, ClientReferenceID, ReferenceID,
            		ClientIndustry, PositionID, Position, InterviewProcess, VisaAccepted, Skills, JobType, LocationField,
            		NumberOfOpenings, RecruiterEmail, StartDate, EndDate, RequirementStatus, Rate, Duration, LocalRequired,
            		Salary, FlexiblityOnSalary, FlexiblityOnHrlyRate, DateOnHold, ExtraDocumentsRequired, minExperience,
            		Notes, jobResponsibilites, jobPostDate, JobTitle, jobPostUserId,maxExperience};
            postReqList.add(ReqList);
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.selectPostReqByUniqueId():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.selectAllPostReq():" + e.getMessage());
    }
    return postReqList;
}   

/*--------------------------Quickbooks Method----------------------*/

public boolean updateEmpQBStatus(String unique_emp_id, String emp_QB_entityId) throws Exception {
    Debug.print("GeneralDBAction updateEmpQBStatus()");
    int cnt = 0;
     
    try {
        makeConnection();
        
        if ( ( unique_emp_id != null && unique_emp_id.trim().length() > 0 )) {
        	           	
            String str = "update  tblEmployeeDetails set emp_QBSync_Status = '0', emp_QB_entityId = ?  WHERE unique_emp_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(str);
            
            prepStmt.setInt(1, Integer.parseInt(emp_QB_entityId));
            prepStmt.setString(2, unique_emp_id);
         
            cnt = prepStmt.executeUpdate();
          
            Debug.print("Successfully Employee QB status Updated......"+cnt);
            prepStmt.close();
        }
    }catch (Exception e){
       
        releaseConnection();
        Debug.print("Error while updating QB status  : "+e.getMessage());
    }finally {
        releaseConnection();
    }
    if (cnt >0)
        return true;
    else
        return false;
}

public ArrayList getEmployeeList(){
    Debug.print("GeneralDBAction.getEmployeeList():");
    ArrayList empArrayList = null;
   
    makeConnection();
	try {
        String selectStatement = "SELECT unique_emp_id,emp_id,emp_SSN_QB,emp_GivenName,emp_FamilyName,emp_PrimaryPhone,emp_Address1,emp_City,emp_State,emp_Country,emp_CountrySubDivisionCode,emp_PostalCode,emp_QBSync_Status,emp_register_date FROM tblEmployeeDetails where emp_QBSync_Status='1'" ;
        prepStmt = con.prepareStatement(selectStatement);
        
        rs = prepStmt.executeQuery();
        empArrayList = new ArrayList();
        while(rs.next()){
            String unique_emp_id = rs.getString(1);
            String emp_id = rs.getString(2);
            String emp_SSN_QB = rs.getString(3);
            String emp_GivenName = rs.getString(4);
            
            String emp_FamilyName = rs.getString(5);
            String emp_PrimaryPhone = rs.getString(6);
            String emp_Address1 = rs.getString(7);
            String emp_City = rs.getString(8);
            String emp_State = rs.getString(9);
            String emp_Country = rs.getString(10);
            String emp_CountrySubDivisionCode = rs.getString(11);
            String emp_PostalCode = rs.getString(12);
            String emp_QBSync_Status = Integer.toString(rs.getInt(13));
            String emp_register_date = rs.getString(14);
            
            String[] empList = {unique_emp_id, emp_id, emp_SSN_QB, emp_GivenName, emp_FamilyName, emp_PrimaryPhone, emp_Address1, emp_City,
            		emp_State, emp_Country, emp_CountrySubDivisionCode, emp_PostalCode,emp_QBSync_Status, emp_register_date};
            empArrayList.add(empList);
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.getEmployeeList():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.getEmployeeList():" + e.getMessage());
    }
    return empArrayList;
  }

public ArrayList getCustomerList() {

	Debug.print("GeneralDBAction.getCustomerList():");
    ArrayList customerArrayList = null;
   
    makeConnection();
	try {
        String selectStatement = "SELECT user_id,institution_name FROM tblSignUpDetails where customer_QBSync_Status='1'" ;
        prepStmt = con.prepareStatement(selectStatement);
        
        rs = prepStmt.executeQuery();
        customerArrayList = new ArrayList();
        while(rs.next()){
            String unique_customer_id = rs.getString(1);
            String customer_companyName = rs.getString(2);
            
            
            String[] customerList = {unique_customer_id, customer_companyName};
            customerArrayList.add(customerList);
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.getCustomerList():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.getCustomerList():" + e.getMessage());
    }
    return customerArrayList;

}

public boolean updateCustomerQBStatus(String unique_customer_id, String customer_QB_entityId) {
	Debug.print("GeneralDBAction updateCustomerQBStatus()");
    int cnt = 0;
     
    try {
        makeConnection();
        
        if ( ( unique_customer_id != null && unique_customer_id.trim().length() > 0 )) {
        	           	
            String str = "update  tblSignUpDetails set customer_QBSync_Status = '0', customer_QB_entityId = ?  WHERE unique_customer_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(str);
            
            prepStmt.setInt(1, Integer.parseInt(customer_QB_entityId));
            prepStmt.setString(2, unique_customer_id);
         
            cnt = prepStmt.executeUpdate();
          
            Debug.print("Successfully Customer QB status Updated......"+cnt);
            prepStmt.close();
        }
    }catch (Exception e){
       
        releaseConnection();
        Debug.print("Error while updating QB status  : "+e.getMessage());
    }finally {
        releaseConnection();
    }
    if (cnt >0)
        return true;
    else
        return false;
}

public boolean insertQBEmployeeDetails(String ssn, String givenName,
		String familyName, String displayName, TelephoneNumber telephoneNumber, PhysicalAddress primaryAddr,
		String id, int qBSyncStatus) {
	
	boolean insertStatus = false;
	try{

	    makeConnection(); 
	    String insertStmt=  "INSERT INTO tblEmployeeDetails(emp_SSN_QB,emp_GivenName,emp_FamilyName,emp_DisplayName,emp_PrimaryPhone,emp_Address1,emp_City"+
	    	    ",emp_Country,emp_CountrySubDivisionCode,emp_PostalCode,emp_QB_entityId,emp_QBSync_Status)"+
	    	    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	      

	      prepStmt = con.prepareStatement(insertStmt);
	      
	      prepStmt.setString(1,ssn);
	      prepStmt.setString(2,givenName);
	      prepStmt.setString(3, familyName);
	      prepStmt.setString(4,displayName);
	      prepStmt.setString(5,telephoneNumber.getFreeFormNumber());
	      prepStmt.setString(6,primaryAddr.getLine1());
	      prepStmt.setString(7,primaryAddr.getCity());
	      prepStmt.setString(8,primaryAddr.getCountry());
	      prepStmt.setString(9,primaryAddr.getCountrySubDivisionCode());
	      prepStmt.setString(10,primaryAddr.getPostalCode());
	      prepStmt.setInt(11, Integer.parseInt(id));
	      prepStmt.setInt(12,qBSyncStatus);
	      
	      
	      int cnt= prepStmt.executeUpdate();
	      if(cnt>0){
	          System.out.println("successfully inserted insertQBEmployeeDetails into database----------------->");
	          insertStatus = true;
	      }
	       prepStmt.close();

	    } 
	    catch (SQLException sqe) {
	      releaseConnection();
	     System.err.println("error in insertQBEmployeeDetails------------------->"+sqe.getMessage());  
	     insertStatus = true;
	    } finally {
	      releaseConnection();
	    }
	return insertStatus;
	
}

public boolean insertReceivedResumeDet(String appliedUserId, String candidateName,
		String txtlastname, String txtemailaddress, String txtcontactnumber,
		String txtcurrentlocation, String drpvisaapproval, String drpvisatype,
		String drpi797available, String drpI97available, String txtrate,
		String txtmiddlename, String txtdateofbirth, String txttotalexperience,
		String txtexperienceinUSA, String drprelocation,
		String txtavailability, String txtpreferredlocation, String drpbywhom,
		String txtskills, String txtbesttimefortelephonicinterview,
		String drltime, String drpwillinginperson, String txtempname,
		String txtempmailID, String txtempcontactnumber, String txtcontactperson, String RID,
		String candidateCompanyUniqueId,String candidateCompany,String candidateCompanyCategory,
		String txtempcompany) throws RemoteException {

	boolean insertStatus = false;
	try{

	    makeConnection(); 
	    String insertStmt=  "INSERT INTO tblCandidateMaster(FirstName,LastName,CandidateEMail,CandidateContactNumber,CurrentLocation,VisaApprovalValue,VisaTypeValue"+
	    	    ",FormI797AvailableValue,FormI94AvailableValue,RateInDollar,MiddleName,DateOfBirth"+
	    		",TotalExperience,ExperienceInUSA,ReLocationValue,Availability,PreferredLocation,ByWhomValue"+
	    	    ",Skills,BestTimeForTelephonicInterview,WillingnessForAnInPersonInterviewAtOwnExpenseValue,EmployerName"+
	    		",EmployerMailID,EmployerContactNumber,ContactPerson,RequirementId,CANID,user_id,can_company_uniqueid,"+
	    	    "can_companname,can_company_category,EmployerCompany)"+
	    	    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	      

	      prepStmt = con.prepareStatement(insertStmt);
	      
	      prepStmt.setString(1,candidateName);
	      prepStmt.setString(2,txtlastname);
	      prepStmt.setString(3,txtemailaddress);
	      prepStmt.setString(4,txtcontactnumber);
	      prepStmt.setString(5,txtcurrentlocation);
	      prepStmt.setString(6,drpvisaapproval);
	      prepStmt.setString(7,drpvisatype);
	      prepStmt.setString(8,drpi797available);
	      prepStmt.setString(9,drpI97available);
	      prepStmt.setString(10,txtrate);
	      prepStmt.setString(11,txtmiddlename);
	      prepStmt.setString(12,txtdateofbirth);
	      prepStmt.setString(13,txttotalexperience);
	      prepStmt.setString(14,txtexperienceinUSA);
	      prepStmt.setString(15,drprelocation);
	      prepStmt.setString(16,txtavailability);
	      prepStmt.setString(17,txtpreferredlocation);
	      prepStmt.setString(18,drpbywhom);
	      prepStmt.setString(19,txtskills);
	      prepStmt.setString(20,txtbesttimefortelephonicinterview+" "+drltime);
	      prepStmt.setString(21,drpwillinginperson);
	      prepStmt.setString(22,txtempname);
	      prepStmt.setString(23,txtempmailID);
	      prepStmt.setString(24,txtempcontactnumber);
	      prepStmt.setString(25,txtcontactperson);
	      prepStmt.setString(26,RID);
	      prepStmt.setString(27,selectCandidateId());
	      prepStmt.setString(28,appliedUserId);
	      prepStmt.setString(29, candidateCompanyUniqueId);
	      prepStmt.setString(30, candidateCompany);
	      prepStmt.setString(31, candidateCompanyCategory);
	      prepStmt.setString(32, txtempcompany);

	      
	      
	      
	      int cnt= prepStmt.executeUpdate();
	      if(cnt>0){
	          System.out.println("Candidaten details have been successfully inserted into database----------------->");
	          insertStatus = true;
	      }
	       prepStmt.close();

	    } 
	    catch (SQLException sqe) {
	      releaseConnection();
	      
	     System.err.println("error in insertQBEmployeeDetails------------------->"+sqe.getMessage()); 
	     sqe.printStackTrace();
	     //insertStatus = true;
	    } finally {
	      releaseConnection();
	    }
	return insertStatus;
	
}


public String selectCandidateId()  throws RemoteException {
    
    String CanId = null;
    
    PreparedStatement prepSelect = null;
    makeConnection();
    try {
    	System.out.println("inside the try :::::::::::::::::::");
        String selectStatement = "SELECT MAX(CAST(SUBSTRING(CANID, 4, len(CANID)-3) AS int)) FROM tblCandidateMaster";
        System.out.println("before prepare stmt ::::::::::::::::");
        prepSelect = con.prepareStatement(selectStatement);
        
        ResultSet rs = prepSelect.executeQuery();
        while (rs.next()) {
        	CanId = rs.getString(1);
         System.out.println("emp id in while loop"+CanId);
        }
        if (CanId == null)
        	CanId = "0";
       
        long nextId = Long.valueOf(CanId).longValue();
        
        if(nextId==0){
        	
            //nextId = 10101000;
 		   
 		   nextId = 1;
 		   System.out.println("nextId in ==0 :::::::::::"+nextId);
        } else{
        	
            nextId = nextId+1;
            System.out.println("nextId in != 0 :::::::::::"+nextId);
        }
        rs.close();
        prepSelect.close();
        CanId = "CAN"+Long.toString(nextId);
        System.out.println("empid in select cust id :::::::::::::::::"+CanId);
       
        //prepStmt.close();
        releaseConnection();
       
    } catch(SQLException sql){
    	System.out.println(sql);
        releaseConnection();
              }
   
    return CanId;
}

/*--------------------------Quickbooks Method----------------------*/



public boolean updateReceivedResumeDet(String candidateStatusValue,
		String requirementID, String candidateEMail) {
	 PreparedStatement prepStmt = null;
     makeConnection();
     try {
         String insertStatement = "update tblCandidateMaster set candidateStatusValue = ?,CandidateType = 'Employee'," +
	
                 "sp_sync_status='1' where RequirementId = ? and CandidateEMail = ? and sp_sync_status = '0'";
	
             prepStmt = con.prepareStatement(insertStatement);
             prepStmt.setString(1, candidateStatusValue);
            
             prepStmt.setString(2, requirementID);
             prepStmt.setString(3, candidateEMail);

          
             prepStmt.executeUpdate();
             prepStmt.close();
    

         releaseConnection();
     }
     
     catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in GeneralDBAction.updateReceivedResumeDet():" + sql.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction.updateReceivedResumeDet():" + e.getMessage());
     }
     return true;
}

public ArrayList getReceivedResumeDetByEmailId(String candidateEMail) {

	Debug.print("GeneralDBAction.getReceivedResumeDetByEmailId():");
    ArrayList candidateArrayList = null;
   
    makeConnection();
	try {
        String selectStatement = "SELECT FirstName,LastName,CurrentLocation FROM tblCandidateMaster where CandidateEMail=?" ;
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, candidateEMail);
        
        rs = prepStmt.executeQuery();
        candidateArrayList = new ArrayList();
        while(rs.next()){
            String FirstName = rs.getString(1);
            String LastName = rs.getString(2);
            String CurrentLocation = rs.getString(3);
            System.out.println("FirstName----------->in db----"+FirstName);
            System.out.println("LastName----------->in db----"+LastName);
            System.out.println("CurrentLocation----------->in db----"+CurrentLocation);
            
            
            String[] customerList = {FirstName, LastName,CurrentLocation};
            candidateArrayList.add(customerList);
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.getReceivedResumeDetByEmailId():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.getReceivedResumeDetByEmailId():" + e.getMessage());
    }
    return candidateArrayList;
    
}


public String getSecurityGroupIdbyRoleId(String roleId){
	  Debug.print("GeneralDBAction.getRoleNameByRoleId():");
	  PreparedStatement prepstmt = null;
	  ResultSet rs = null;
	  makeConnection();
	  String securityGroupId = "";
	  try{
	   String sqlQuery = "Select dbERP_security_group from tblRoleMaster where role_id=?";
	   prepstmt = con.prepareStatement(sqlQuery);
	   prepstmt.setString(1, roleId);
	   rs = prepstmt.executeQuery();
	   rs.next();
	   securityGroupId = rs.getString(1);
	   rs.close();
	   prepstmt.close();
	   releaseConnection();
	  }
	  catch(SQLException sql)
	     {
	         releaseConnection();
	         Debug.print((new StringBuilder()).append("SQL Exception in GeneralDBAction.getRoleNameByRoleId():").append(sql.getMessage()).toString());
	     }
	     catch(Exception e)
	     {
	         releaseConnection();
	         Debug.print((new StringBuilder()).append("General Exception  in GeneralDBAction.getRoleNameByRoleId():").append(e.getMessage()).toString());
	     }
	  
	  return securityGroupId;
	  
	 }


public ArrayList getAllClientDetails() throws RemoteException { 
    Debug.print("GeneralDBAction getAllClientDetails");
    ArrayList results =(ArrayList)selectAllClientDetails();
    return results;    
 }

public ArrayList selectAllClientDetails(){
    Debug.print("GeneralDBAction.selectAllClientDetails():");
    ArrayList clientContactList = null;
    PreparedStatement prepStmt = null;
    ResultSet rs = null;
    makeConnection();
	try {
        String selectStatement = " SELECT unique_id ,client_id ,client_name ,responsible_person ,client_email"+
      ",client_company ,client_businessPhone  from tblClientMasterDetails order by reg_date DESC";
        prepStmt = con.prepareStatement(selectStatement);
        rs = prepStmt.executeQuery();
        clientContactList = new ArrayList();
        while(rs.next()){
    		String unique_id = rs.getString(1);
    		String client_id = rs.getString(2);
        	String client_name = rs.getString(3);
    		String responsible_person = rs.getString(4);
    		String client_email = rs.getString(5);
    		String client_company = rs.getString(6);
    		//String client_jobTitle = rs.getString(7);
    		String client_businessPhone = rs.getString(7);
    		//String client_contactId = rs.getString(9);
    		
            String[] ContactList = {unique_id, client_id,client_name, responsible_person, client_email, client_company,
            		 client_businessPhone};
            clientContactList.add(ContactList);
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.selectAllClientDetails():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.selectAllClientDetails():" + e.getMessage());
    }
    return clientContactList;
}

public boolean insertClientContacts(ClientContactDetailsBean objClientContacts) {
	
	boolean clientContactStatus = false;
	try{


	    makeConnection(); 
	    String insertStmt=  "INSERT INTO tblClientMasterDetails (client_id ,client_name ,responsible_person ,"+
	    "client_email,client_company ,client_businessPhone ) VALUES (?,?,?,?,?,?)";
	      

	      prepStmt = con.prepareStatement(insertStmt);
	      prepStmt.setString(1,objClientContacts.getclient_id());
	      prepStmt.setString(2,objClientContacts.getclient_name());
	      prepStmt.setString(3, objClientContacts.getresponsible_person());
	      prepStmt.setString(4,objClientContacts.getclient_email());
	      prepStmt.setString(5,objClientContacts.getclient_company());
	      //prepStmt.setString(6,objClientContacts.getclient_jobTitle());
	      prepStmt.setString(6,objClientContacts.getclient_businessPhone());
	      //prepStmt.setString(8,objClientContacts.getclient_contactId());
	      	      
	      int cnt= prepStmt.executeUpdate();
	      if(cnt>0){
	          System.out.println("Client details are successfully inserted into database----------------->");
	          clientContactStatus = true;
	      }
	       prepStmt.close();

	    } 
	    catch (SQLException sqe) {
	      releaseConnection();
	     sqe.printStackTrace();  
	     clientContactStatus = true;
	    } finally {
	      releaseConnection();
	    }
	return clientContactStatus;
}

public ArrayList getClientContactByUniqueId(String clientContactByUniqueId) {
	 Debug.print("GeneralDBAction getClientContactByUniqueId");
	    ArrayList results =(ArrayList)selectClientContactByUniqueId(clientContactByUniqueId);
	    return results;    
}

private ArrayList selectClientContactByUniqueId(String clientContactByUniqueId) {
	 Debug.print("GeneralDBAction.selectClientContactByUniqueId():");
	    ArrayList clientContactList = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
		try {
	        String selectStatement = " SELECT unique_id ,client_id ,client_name ,responsible_person ,client_email"+
	      ",client_company ,client_businessPhone  from tblClientMasterDetails where unique_id=?";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, clientContactByUniqueId);
	        rs = prepStmt.executeQuery();
	        clientContactList = new ArrayList();
	        while(rs.next()){
	    		String unique_id = rs.getString(1);
	    		String client_id = rs.getString(2);
	        	String client_name = rs.getString(3);
	    		String responsible_person = rs.getString(4);
	    		String client_email = rs.getString(5);
	    		String client_company = rs.getString(6);
	    		//String client_jobTitle = rs.getString(7);
	    		String client_businessPhone = rs.getString(7);
	    		//String client_contactId = rs.getString(9);
	    		
	            String[] ContactList = {unique_id, client_id,client_name, responsible_person, client_email, client_company,
	            		client_businessPhone};
	            clientContactList.add(ContactList);
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        Debug.print("SQL Exception in GeneralDBAction.selectClientContactByUniqueId():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        Debug.print("General Exception  in GeneralDBAction.selectClientContactByUniqueId():" + e.getMessage());
	    }
	    return clientContactList;
}

/*-----------------------vendor Contacts query start here-----------------------------*/

public ArrayList getAllVendorDetails() throws RemoteException { 
    Debug.print("GeneralDBAction getAllVendorDetails");
    ArrayList results =(ArrayList)selectAllVendorDetails();
    return results;    
 }

public ArrayList selectAllVendorDetails(){
    Debug.print("GeneralDBAction.selectAllVendorDetails():");
    ArrayList vendorContactList = null;
    PreparedStatement prepStmt = null;
    ResultSet rs = null;
    makeConnection();
	try {
        String selectStatement = " SELECT DISTINCT unique_id ,vendor_id ,vendor_fname ,vendor_lname ,vendor_email"+
      ",vendor_company ,vendor_homePhone ,vendor_businessPhone ,vendor_contactPerson from tblVendorMasterDetails";
        prepStmt = con.prepareStatement(selectStatement);
        rs = prepStmt.executeQuery();
        vendorContactList = new ArrayList();
        while(rs.next()){
    		String unique_id = rs.getString(1);
    		String vendor_id = rs.getString(2);
        	String vendor_fname = rs.getString(3);
    		String vendor_lname = rs.getString(4);
    		String vendor_email = rs.getString(5);
    		String vendor_company = rs.getString(6);
    		String vendor_homePhone = rs.getString(7);
    		String vendor_businessPhone = rs.getString(8);
    		String vendor_contactPerson = rs.getString(9);
    		
            String[] ContactList = {unique_id, vendor_id,vendor_fname, vendor_lname, vendor_email, vendor_company,
            		vendor_homePhone, vendor_businessPhone, vendor_contactPerson};
            vendorContactList.add(ContactList);
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.selectAllVendorDetails():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.selectAllVendorDetails():" + e.getMessage());
    }
    return vendorContactList;
}

public boolean insertVendorContacts(VendorContactDetailsBean objVendorContacts) {
	
	boolean vendorContactStatus = false;
	try{


	    makeConnection(); 
	    String insertStmt=  "INSERT INTO tblVendorMasterDetails (vendor_id ,vendor_fname ,vendor_lname ,"+
	    "vendor_email,vendor_company ,vendor_homePhone ,vendor_businessPhone ,vendor_contactPerson) VALUES (?,?,?,?,?,?,?,?)";
	      

	      prepStmt = con.prepareStatement(insertStmt);
	      prepStmt.setString(1,objVendorContacts.getvendor_id());
	      prepStmt.setString(2,objVendorContacts.getvendor_fname());
	      prepStmt.setString(3, objVendorContacts.getvendor_lname());
	      prepStmt.setString(4,objVendorContacts.getvendor_email());
	      prepStmt.setString(5,objVendorContacts.getvendor_company());
	      prepStmt.setString(6,objVendorContacts.getvendor_homePhone());
	      prepStmt.setString(7,objVendorContacts.getvendor_businessPhone());
	      prepStmt.setString(8,objVendorContacts.getvendor_contactPerson());
	      	      
	      int cnt= prepStmt.executeUpdate();
	      if(cnt>0){
	          System.out.println("Vendor details are successfully inserted into database----------------->");
	          vendorContactStatus = true;
	      }
	       prepStmt.close();

	    } 
	    catch (SQLException sqe) {
	      releaseConnection();
	     sqe.printStackTrace();  
	     vendorContactStatus = true;
	    } finally {
	      releaseConnection();
	    }
	return vendorContactStatus;
}

public ArrayList getVendorContactByUniqueId(String vendorContactByUniqueId) {
	 Debug.print("GeneralDBAction getVendorContactByUniqueId");
	    ArrayList results =(ArrayList)selectVendorContactByUniqueId(vendorContactByUniqueId);
	    return results;    
}

private ArrayList selectVendorContactByUniqueId(String vendorContactByUniqueId) {
	 Debug.print("GeneralDBAction.selectVendorContactByUniqueId():");
	    ArrayList vendorContactList = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
		try {
	        String selectStatement = " SELECT unique_id ,vendor_id ,vendor_fname ,vendor_lname ,vendor_email"+
	      ",vendor_company ,vendor_homePhone ,vendor_businessPhone ,vendor_contactPerson from tblVendorMasterDetails where unique_id=?";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, vendorContactByUniqueId);
	        rs = prepStmt.executeQuery();
	        vendorContactList = new ArrayList();
	        while(rs.next()){
	    		String unique_id = rs.getString(1);
	    		String vendor_id = rs.getString(2);
	        	String vendor_fname = rs.getString(3);
	    		String vendor_lname = rs.getString(4);
	    		String vendor_email = rs.getString(5);
	    		String vendor_company = rs.getString(6);
	    		String vendor_homePhone = rs.getString(7);
	    		String vendor_businessPhone = rs.getString(8);
	    		String vendor_contactPerson = rs.getString(9);
	    		
	            String[] ContactList = {unique_id, vendor_id,vendor_fname, vendor_lname, vendor_email, vendor_company,
	            		vendor_homePhone, vendor_businessPhone, vendor_contactPerson};
	            vendorContactList.add(ContactList);
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        Debug.print("SQL Exception in GeneralDBAction.selectVendorContactByUniqueId():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        Debug.print("General Exception  in GeneralDBAction.selectVendorContactByUniqueId():" + e.getMessage());
	    }
	    return vendorContactList;
}

/*-----------------------vendor Contacts query End here-----------------------------*/


public boolean importClientContactByXLS(ArrayList listData) {
	String client_id = null;
	String client_name =  null;
	String responsible_person = null;
	String client_email = null;
	String client_company = null;
	String client_jobTitle = null;
	String client_businessPhone = null;
	String client_contactId = null;
	
	client_id = (String) listData.get(0);
	client_name = (String) listData.get(1);
	responsible_person = (String) listData.get(2);
	client_email = (String) listData.get(3);
	client_company = (String) listData.get(4);
	client_jobTitle = (String) listData.get(5);
	client_businessPhone =  listData.get(6).toString();
	client_contactId = (String) listData.get(7);
	
	boolean clientContactStatus = false;
	try{


	    makeConnection(); 
	    String insertStmt=  "INSERT INTO tblClientMasterDetails (client_id ,client_name ,responsible_person ,"+
	    "client_email,client_company ,client_jobTitle ,client_businessPhone ,client_contactId) VALUES (?,?,?,?,?,?,?,?)";
	      

	      prepStmt = con.prepareStatement(insertStmt);
	      prepStmt.setString(1,client_id);
	      prepStmt.setString(2,client_name);
	      prepStmt.setString(3, responsible_person);
	      prepStmt.setString(4,client_email);
	      prepStmt.setString(5,client_company);
	      prepStmt.setString(6,client_jobTitle);
	      prepStmt.setString(7,client_businessPhone);
	      prepStmt.setString(8,client_contactId);
	      	      
	      int cnt= prepStmt.executeUpdate();
	      if(cnt>0){
	          System.out.println("Client details are successfully imported into database----------------->");
	          clientContactStatus = true;
	      }
	       prepStmt.close();

	    } 
	    catch (SQLException sqe) {
	      releaseConnection();
	     sqe.printStackTrace();  
	     clientContactStatus = true;
	    } finally {
	      releaseConnection();
	    }
	return clientContactStatus;
}

public boolean importVendorContactByXLS(ArrayList listData) {
	String vendor_id = null;
	String vendor_fname =  null;
	String vendor_lname = null;
	String vendor_email = null;
	String vendor_company = null;
	String vendor_homePhone = null;
	String vendor_businessPhone = null;
	String vendor_contactPerson = null;
	
	vendor_id = (String) listData.get(0);
	vendor_fname = (String) listData.get(1);
	vendor_lname = (String) listData.get(2);
	vendor_email = (String) listData.get(3);
	vendor_company = (String) listData.get(4);
	vendor_homePhone = (String) listData.get(5);
	vendor_businessPhone =  listData.get(6).toString();
	vendor_contactPerson = (String) listData.get(7);
	
	boolean vendorContactStatus = false;
	try{


		makeConnection(); 
	    String insertStmt=  "INSERT INTO tblVendorMasterDetails (vendor_id ,vendor_fname ,vendor_lname ,"+
	    "vendor_email,vendor_company ,vendor_homePhone ,vendor_businessPhone ,vendor_contactPerson) VALUES (?,?,?,?,?,?,?,?)";
	      

	      prepStmt = con.prepareStatement(insertStmt);
	      prepStmt.setString(1,vendor_id);
	      prepStmt.setString(2,vendor_fname);
	      prepStmt.setString(3, vendor_lname);
	      prepStmt.setString(4,vendor_email);
	      prepStmt.setString(5,vendor_company);
	      prepStmt.setString(6,vendor_homePhone);
	      prepStmt.setString(7,vendor_businessPhone);
	      prepStmt.setString(8,vendor_contactPerson);
	      	      
	      int cnt= prepStmt.executeUpdate();
	      if(cnt>0){
	          System.out.println("Vendor details are successfully inserted into database----------------->");
	          vendorContactStatus = true;
	      }
	       prepStmt.close();

	    } 
	    catch (SQLException sqe) {
	      releaseConnection();
	     sqe.printStackTrace();  
	     vendorContactStatus = true;
	    } finally {
	      releaseConnection();
	    }
	return vendorContactStatus;
}

public ArrayList getAllPostReqByStatus(String jobStatus, String jobPostCompanyUniqueId, String companyJobPostCategory) {
	 Debug.print("GeneralDBAction getAllPostReqByStatus");
	    ArrayList results =(ArrayList)selectAllPostReqByStatus(jobStatus,jobPostCompanyUniqueId, companyJobPostCategory);
	    return results;    
	 }

	public ArrayList selectAllPostReqByStatus(String jobStatus, String jobPostCompanyUniqueId, String companyJobPostCategory){
	    Debug.print("GeneralDBAction.selectAllPostReqByStatus():");
	    ArrayList postReqList = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
		try {
			String selectStatement = null;
			if(companyJobPostCategory.equalsIgnoreCase("buyer")){
	        selectStatement = "SELECT requirement_id ,jobPostCompanyName ,ClientReferenceID ,ReferenceID ,ClientIndustry ,PositionID ,Position"+
      ",InterviewProcess ,VisaAccepted ,Skills ,JobType ,LocationField ,NumberOfOpenings ,RecruiterEmail ,StartDate ,EndDate ,RequirementStatus"+
      ",Rate ,Duration ,LocalRequired ,Salary ,FlexiblityOnSalary ,FlexiblityOnHrlyRate ,DateOnHold ,ExtraDocumentsRequired ,minExperience"+
      ",Notes ,jobResponsibilites ,jobPostDate, post_req_uniqueId, JobTitle, jobPostCompanyCategory, uniqueJobPostCompanyId"+
      ", jobPostCompanyId, jobPostUserId, jobPostUserName, jobPostUserRoleId, jobPostUserRoleName, maxExperience from tblMFPostRequirements"+
      " where RequirementStatus=? and uniqueJobPostCompanyId=? order by jobPostDate DESC";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1,jobStatus);
	        prepStmt.setString(2, jobPostCompanyUniqueId);
			}else{
				selectStatement = "SELECT requirement_id ,jobPostCompanyName ,ClientReferenceID ,ReferenceID ,ClientIndustry ,PositionID ,Position"+
					      ",InterviewProcess ,VisaAccepted ,Skills ,JobType ,LocationField ,NumberOfOpenings ,RecruiterEmail ,StartDate ,EndDate ,RequirementStatus"+
					      ",Rate ,Duration ,LocalRequired ,Salary ,FlexiblityOnSalary ,FlexiblityOnHrlyRate ,DateOnHold ,ExtraDocumentsRequired ,minExperience"+
					      ",Notes ,jobResponsibilites ,jobPostDate, post_req_uniqueId, JobTitle, jobPostCompanyCategory, uniqueJobPostCompanyId"+
					      ", jobPostCompanyId, jobPostUserId, jobPostUserName, jobPostUserRoleId, jobPostUserRoleName, maxExperience from tblMFPostRequirements"+
					      " where RequirementStatus=?  order by jobPostDate DESC";
						        prepStmt = con.prepareStatement(selectStatement);
						        prepStmt.setString(1,jobStatus);
						        //prepStmt.setString(2, jobPostCompanyUniqueId);
			}
	        rs = prepStmt.executeQuery();
	        postReqList = new ArrayList();
	        while(rs.next()){
	        	String requirementId = rs.getString(1);
	    		String jobPostCompanyName = rs.getString(2);
	        	String ClientReferenceID = rs.getString(3);
	    		String ReferenceID = rs.getString(4);
	    		String ClientIndustry = rs.getString(5);
	    		String PositionID = rs.getString(6);
	    		String Position = rs.getString(7);
	    		String InterviewProcess = rs.getString(8);
	    		String VisaAccepted = rs.getString(9);
	    		String Skills = rs.getString(10);
	    		String JobType = rs.getString(11);
	    		String LocationField = rs.getString(12);
	    		String NumberOfOpenings = rs.getString(13);
	    		String RecruiterEmail = rs.getString(14);
	    		String StartDate = rs.getString(15);
	    		String EndDate = rs.getString(16);
	    		String RequirementStatus = rs.getString(17); 
	    		String Rate = rs.getString(18);
	    		String Duration = rs.getString(19);
	    		String LocalRequired = rs.getString(20);
	    		String Salary = rs.getString(21);
	    		String FlexiblityOnSalary = rs.getString(22);
	    		String FlexiblityOnHrlyRate = rs.getString(23);
	    		String DateOnHold = rs.getString(24);
	    		String ExtraDocumentsRequired = rs.getString(25);
	    		//String RequiredExperience = rs.getString(26);
	    		String minExperience = rs.getString(26);
	    		String Notes = rs.getString(27);
	    		String jobResponsibilites = rs.getString(28);
	    		String jobPostDate = rs.getString(29);
	    		String postReqUniqueId = rs.getString (30);
	    		String JobTitle = rs.getString(31);
	    		String jobPostCompanyCategory = rs.getString(32);
	    		String uniqueJobPostCompanyId = rs.getString(33);
	    		String jobPostCompanyId = rs.getString(34);
	    		String jobPostUserId = rs.getString(35);
	    		String jobPostUserName = rs.getString(36);
	    		String jobPostUserRoleId = rs.getString(37);
	    		String jobPostUserRoleName = rs.getString(38);
	    		String maxExperience = rs.getString(39);
	            String[] ReqList = {postReqUniqueId, requirementId,jobPostCompanyName, ClientReferenceID, ReferenceID,
	            		ClientIndustry, PositionID, Position, InterviewProcess, VisaAccepted, Skills, JobType, LocationField,
	            		NumberOfOpenings, RecruiterEmail, StartDate, EndDate, RequirementStatus, Rate, Duration, LocalRequired,
	            		Salary, FlexiblityOnSalary, FlexiblityOnHrlyRate, DateOnHold, ExtraDocumentsRequired,
	            		minExperience, Notes, jobResponsibilites, jobPostDate, JobTitle, jobPostCompanyCategory,
	            		uniqueJobPostCompanyId, jobPostCompanyId, jobPostUserId, jobPostUserName, jobPostUserRoleId,
	            		jobPostUserRoleName,maxExperience};
	            postReqList.add(ReqList);
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        Debug.print("SQL Exception in GeneralDBAction.selectAllPostReq():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        Debug.print("General Exception  in GeneralDBAction.selectAllPostReq():" + e.getMessage());
	    }
	    return postReqList;
	}

	public ArrayList getAllAppliedCandidateListByUserId(String userId) {
		 Debug.print("GeneralDBAction.getAllAppliedCandidateListByUserId():");
		    ArrayList AppliedCandidateList = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = " SELECT FirstName,LastName,CandidateEMail,CandidateContactNumber,CurrentLocation,VisaTypeValue,VisaApprovalValue"+
	    	    ",FormI797AvailableValue,FormI94AvailableValue,RateInDollar,MiddleName,DateOfBirth"+
	    		",TotalExperience,ExperienceInUSA,ReLocationValue,Availability,PreferredLocation,ByWhomValue"+
	    	    ",Skills,BestTimeForTelephonicInterview,WillingnessForAnInPersonInterviewAtOwnExpenseValue,EmployerName"+
	    		",EmployerMailID,EmployerContactNumber,ContactPerson,RequirementId,unique_id,CANID,CandidateStatusValue"+
	    	    ",CandidateResumeLoc,user_id,can_company_uniqueid,can_companname,can_company_category,can_joined_status"+
	    		",EmployerCompany from tblCandidateMaster where user_id=? order by reg_date";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, userId);
		        rs = prepStmt.executeQuery();
		        AppliedCandidateList = new ArrayList();
		        while(rs.next()){
		        	
		        	String txtfirstname = rs.getString(1);
		        	String txtlastname =  rs.getString(2);
		        	String txtemailaddress = rs.getString(3);
		        	String txtcontactnumber = rs.getString(4);
		        	String txtcurrentlocation = rs.getString(5);
		        	String drpvisatype = rs.getString(6);
		        	String drpvisaapproval = rs.getString(7);
		        	String drpi797available = rs.getString(8);
		        	String drpI97available = rs.getString(9);
		        	String txtrate = rs.getString(10);
		        	String txtmiddlename = rs.getString(11);
		        	String txtdateofbirth = rs.getString(12);
		        	String txttotalexperience = rs.getString(13);
		        	String txtexperienceinUSA = rs.getString(14);
		        	String drprelocation = rs.getString(15);
		        	String txtavailability = rs.getString( 16);
		        	String txtpreferredlocation = rs.getString(17);
		        	String drpbywhom = rs.getString(18);
		        	String txtskills = rs.getString(19);
		        	String txtbesttimefortelephonicinterview = rs.getString(20);
		        	String drpwillinginperson = rs.getString(21);
		        	String txtempname = rs.getString(22);
		        	String txtempmailID = rs.getString(23);
		        	String txtempcontactnumber = rs.getString(24);
		        	String txtcontactperson = rs.getString(25);
		        	String RID = rs.getString(26);
		        	String unique_id = rs.getString(27);
		        	String CANID = rs.getString(28);
		        	String CandidateStatusValue = rs.getString(29);
		        	String CandidateResumeLoc = rs.getString(30);
		        	String appliedUserId = rs.getString(31);
		        	String candidateCompanyUniqueId = rs.getString(32);
		        	String candidateCompany = rs.getString(33);
		        	String candidateCompanyCategory = rs.getString(34);
		        	int canJoinedStatusInt = rs.getInt(35);
		        	String candidateJoinedStatus = Integer.toString(canJoinedStatusInt);
		        	String EmployerCompany = rs.getString(36);
		        	
		            String[] CandidateList = {txtfirstname,
		            		txtlastname, txtemailaddress, txtcontactnumber,
		            		txtcurrentlocation, drpvisatype, drpvisaapproval,
		            		drpi797available, drpI97available, txtrate,
		            		txtmiddlename, txtdateofbirth, txttotalexperience,
		            		txtexperienceinUSA, drprelocation,
		            		txtavailability, txtpreferredlocation, drpbywhom,
		            		txtskills, txtbesttimefortelephonicinterview,
		            		drpwillinginperson, txtempname,
		            		txtempmailID, txtempcontactnumber, txtcontactperson, RID, unique_id,
		            		CANID,CandidateStatusValue,CandidateResumeLoc,appliedUserId,candidateCompanyUniqueId,
		            		candidateCompany,candidateCompanyCategory,candidateJoinedStatus,EmployerCompany};
		            AppliedCandidateList.add(CandidateList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getAllAppliedCandidateListByUserId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getAllAppliedCandidateListByUserId():" + e.getMessage());
		    }
		    return AppliedCandidateList;
	}
	
	public ArrayList getAllAppliedCandidateList(String companyJobPostCategory, String jobPostCompanyUniqueId) {
		 Debug.print("GeneralDBAction.getAllAppliedCandidateList():");
		    ArrayList AppliedCandidateList = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
				String selectStatement = null;
				if(companyJobPostCategory != null && companyJobPostCategory.equalsIgnoreCase("buyer")){
		        selectStatement = " SELECT FirstName,LastName,CandidateEMail,CandidateContactNumber,CurrentLocation,VisaTypeValue,VisaApprovalValue"+
	    	    ",FormI797AvailableValue,FormI94AvailableValue,RateInDollar,MiddleName,DateOfBirth"+
	    		",TotalExperience,ExperienceInUSA,ReLocationValue,Availability,PreferredLocation,ByWhomValue"+
	    	    ",Skills,BestTimeForTelephonicInterview,WillingnessForAnInPersonInterviewAtOwnExpenseValue,EmployerName"+
	    		",EmployerMailID,EmployerContactNumber,ContactPerson,RequirementId,unique_id,CANID,CandidateStatusValue"+
	    	    ",CandidateResumeLoc,user_id,can_company_uniqueid,can_companname,can_company_category,can_joined_status"+
	    		",EmployerCompany from tblCandidateMaster where can_company_uniqueid=? order by reg_date desc";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, jobPostCompanyUniqueId);
				}else if(companyJobPostCategory != null && companyJobPostCategory.equalsIgnoreCase("vendor")){
				selectStatement = " SELECT FirstName,LastName,CandidateEMail,CandidateContactNumber,CurrentLocation,VisaTypeValue,VisaApprovalValue"+
				    	    ",FormI797AvailableValue,FormI94AvailableValue,RateInDollar,MiddleName,DateOfBirth"+
				    		",TotalExperience,ExperienceInUSA,ReLocationValue,Availability,PreferredLocation,ByWhomValue"+
				    	    ",Skills,BestTimeForTelephonicInterview,WillingnessForAnInPersonInterviewAtOwnExpenseValue,EmployerName"+
				    		",EmployerMailID,EmployerContactNumber,ContactPerson,RequirementId,unique_id,CANID,CandidateStatusValue"+
				    	    ",CandidateResumeLoc,user_id,can_company_uniqueid,can_companname,can_company_category,can_joined_status"+
				    		",EmployerCompany from tblCandidateMaster where can_company_uniqueid=? order by reg_date desc";
				prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, jobPostCompanyUniqueId);
				}
		        rs = prepStmt.executeQuery();
		        AppliedCandidateList = new ArrayList();
		        while(rs.next()){
		        	
		        	String txtfirstname = rs.getString(1);
		        	String txtlastname =  rs.getString(2);
		        	String txtemailaddress = rs.getString(3);
		        	String txtcontactnumber = rs.getString(4);
		        	String txtcurrentlocation = rs.getString(5);
		        	String drpvisatype = rs.getString(6);
		        	String drpvisaapproval = rs.getString(7);
		        	String drpi797available = rs.getString(8);
		        	String drpI97available = rs.getString(9);
		        	String txtrate = rs.getString(10);
		        	String txtmiddlename = rs.getString(11);
		        	String txtdateofbirth = rs.getString(12);
		        	String txttotalexperience = rs.getString(13);
		        	String txtexperienceinUSA = rs.getString(14);
		        	String drprelocation = rs.getString(15);
		        	String txtavailability = rs.getString( 16);
		        	String txtpreferredlocation = rs.getString(17);
		        	String drpbywhom = rs.getString(18);
		        	String txtskills = rs.getString(19);
		        	String txtbesttimefortelephonicinterview = rs.getString(20);
		        	String drpwillinginperson = rs.getString(21);
		        	String txtempname = rs.getString(22);
		        	String txtempmailID = rs.getString(23);
		        	String txtempcontactnumber = rs.getString(24);
		        	String txtcontactperson = rs.getString(25);
		        	String RID = rs.getString(26);
		        	String unique_id = rs.getString(27);
		        	String CANID = rs.getString(28);
		        	String CandidateStatusValue = rs.getString(29);
		        	String CandidateResumeLoc = rs.getString(30);
		        	String appliedUserId = rs.getString(31);
		        	String candidateCompanyUniqueId = rs.getString(32);
		        	String candidateCompany = rs.getString(33);
		        	String candidateCompanyCategory = rs.getString(34);
		        	int canJoinedStatusInt = rs.getInt(35);
		        	String candidateJoinedStatus = Integer.toString(canJoinedStatusInt);
		        	String EmployerCompany = rs.getString(36);
		        	
		            String[] CandidateList = {txtfirstname,
		            		txtlastname, txtemailaddress, txtcontactnumber,
		            		txtcurrentlocation, drpvisatype, drpvisaapproval,
		            		drpi797available, drpI97available, txtrate,
		            		txtmiddlename, txtdateofbirth, txttotalexperience,
		            		txtexperienceinUSA, drprelocation,
		            		txtavailability, txtpreferredlocation, drpbywhom,
		            		txtskills, txtbesttimefortelephonicinterview,
		            		drpwillinginperson, txtempname,
		            		txtempmailID, txtempcontactnumber, txtcontactperson, RID, unique_id,
		            		CANID,CandidateStatusValue,CandidateResumeLoc,appliedUserId,candidateCompanyUniqueId,
		            		candidateCompany,candidateCompanyCategory,candidateJoinedStatus,EmployerCompany};
		            AppliedCandidateList.add(CandidateList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getAllAppliedCandidateList():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getAllAppliedCandidateList():" + e.getMessage());
		    }
		    return AppliedCandidateList;
	}

	public ArrayList getCanDetailsByUniqueId(String canDetailsByUniqueId) {
		Debug.print("GeneralDBAction.getCanDetailsByUniqueId():");
	    ArrayList AppliedCandidateList = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
		try {
	        String selectStatement = " SELECT FirstName,LastName,CandidateEMail,CandidateContactNumber,CurrentLocation,VisaTypeValue,VisaApprovalValue"+
    	    ",FormI797AvailableValue,FormI94AvailableValue,RateInDollar,MiddleName,DateOfBirth"+
    		",TotalExperience,ExperienceInUSA,ReLocationValue,Availability,PreferredLocation,ByWhomValue"+
    	    ",Skills,BestTimeForTelephonicInterview,WillingnessForAnInPersonInterviewAtOwnExpenseValue,EmployerName"+
    	    ",EmployerMailID,EmployerContactNumber,ContactPerson,RequirementId,unique_id,CANID,CandidateStatusValue"+
    	    ",CandidateResumeLoc,user_id,can_company_uniqueid,can_companname,can_company_category,can_joined_status"+
    	    ",EmployerCompany from tblCandidateMaster where unique_id=? order by reg_date";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, canDetailsByUniqueId);
	        rs = prepStmt.executeQuery();
	        AppliedCandidateList = new ArrayList();
	        while(rs.next()){
	        	
	        	String txtfirstname = rs.getString(1);
	        	String txtlastname =  rs.getString(2);
	        	String txtemailaddress = rs.getString(3);
	        	String txtcontactnumber = rs.getString(4);
	        	String txtcurrentlocation = rs.getString(5);
	        	String drpvisatype = rs.getString(6);
	        	String drpvisaapproval = rs.getString(7);
	        	String drpi797available = rs.getString(8);
	        	String drpI97available = rs.getString(9);
	        	String txtrate = rs.getString(10);
	        	String txtmiddlename = rs.getString(11);
	        	String txtdateofbirth = rs.getString(12);
	        	String txttotalexperience = rs.getString(13);
	        	String txtexperienceinUSA = rs.getString(14);
	        	String drprelocation = rs.getString(15);
	        	String txtavailability = rs.getString( 16);
	        	String txtpreferredlocation = rs.getString(17);
	        	String drpbywhom = rs.getString(18);
	        	String txtskills = rs.getString(19);
	        	String txtbesttimefortelephonicinterview = rs.getString(20);
	        	String drpwillinginperson = rs.getString(21);
	        	String txtempname = rs.getString(22);
	        	String txtempmailID = rs.getString(23);
	        	String txtempcontactnumber = rs.getString(24);
	        	String txtcontactperson = rs.getString(25);
	        	String RID = rs.getString(26);
	        	String unique_id = rs.getString(27);
	        	String CANID = rs.getString(28);
	        	String CandidateStatusValue = rs.getString(29);
	        	String CandidateResumeLoc = rs.getString(30);
	        	String appliedUserId = rs.getString(31);
	        	String candidateCompanyUniqueId = rs.getString(32);
	        	String candidateCompany = rs.getString(33);
	        	String candidateCompanyCategory = rs.getString(34);
	        	int canJoinedStatusInt = rs.getInt(35);
	        	String candidateJoinedStatus = Integer.toString(canJoinedStatusInt);
	        	String EmployerCompany = rs.getString(36);
	        	
	            String[] CandidateList = {txtfirstname,
	            		txtlastname, txtemailaddress, txtcontactnumber,
	            		txtcurrentlocation, drpvisatype, drpvisaapproval,
	            		drpi797available, drpI97available, txtrate,
	            		txtmiddlename, txtdateofbirth, txttotalexperience,
	            		txtexperienceinUSA, drprelocation,
	            		txtavailability, txtpreferredlocation, drpbywhom,
	            		txtskills, txtbesttimefortelephonicinterview,
	            		drpwillinginperson, txtempname,
	            		txtempmailID, txtempcontactnumber, txtcontactperson, RID, unique_id,
	            		CANID, CandidateStatusValue, CandidateResumeLoc,appliedUserId,candidateCompanyUniqueId,
	            		candidateCompany,candidateCompanyCategory,candidateJoinedStatus,EmployerCompany};
	            AppliedCandidateList.add(CandidateList);
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        Debug.print("SQL Exception in GeneralDBAction.getCanDetailsByUniqueId():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        Debug.print("General Exception  in GeneralDBAction.getAllAppliedCandidateList():" + e.getMessage());
	    }
	    return AppliedCandidateList;

	}
	
	public MFAppliedCandidateBean getCanDetailBeanByUniqueId(String canDetailsByUniqueId) {
		Debug.print("GeneralDBAction.getCanDetailBeanByUniqueId():");
		MFAppliedCandidateBean objCandidatebean = new MFAppliedCandidateBean();
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
		try {
	        String selectStatement = " SELECT FirstName,LastName,CandidateEMail,CandidateContactNumber,CurrentLocation,VisaTypeValue,VisaApprovalValue"+
    	    ",FormI797AvailableValue,FormI94AvailableValue,RateInDollar,MiddleName,DateOfBirth"+
    		",TotalExperience,ExperienceInUSA,ReLocationValue,Availability,PreferredLocation,ByWhomValue"+
    	    ",Skills,BestTimeForTelephonicInterview,WillingnessForAnInPersonInterviewAtOwnExpenseValue,EmployerName"+
    	    ",EmployerMailID,EmployerContactNumber,ContactPerson,RequirementId,unique_id,CANID,CandidateStatusValue"+
    	    ",CandidateResumeLoc,user_id,can_company_uniqueid,can_companname,can_company_category,can_joined_status"+
    	    ",EmployerCompany from tblCandidateMaster where unique_id=? order by reg_date";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, canDetailsByUniqueId);
	        rs = prepStmt.executeQuery();
	        while(rs.next()){
	        	
	        	objCandidatebean.settxtfirstname(rs.getString(1));
	        	objCandidatebean.settxtlastname(rs.getString(2));
	        	objCandidatebean.settxtemailaddress(rs.getString(3));
	        	objCandidatebean.settxtcontactnumber(rs.getString(4));
	        	objCandidatebean.settxtcurrentAddress1(rs.getString(5));
	        	objCandidatebean.setdrpvisatype(rs.getString(6));
	        	objCandidatebean.setdrpvisaapproval(rs.getString(7));
	        	objCandidatebean.setdrpi797available(rs.getString(8));
	        	objCandidatebean.setdrpI97available(rs.getString(9));
	        	objCandidatebean.settxtrate(rs.getString(10));
	        	objCandidatebean.settxtmiddlename(rs.getString(11));
	        	objCandidatebean.settxtdateofbirth(rs.getString(12));
	        	objCandidatebean.settxttotalexperience(rs.getString(13));
	        	objCandidatebean.settxtexperienceinUSA(rs.getString(14));
	        	objCandidatebean.setdrprelocation(rs.getString(15));
	        	objCandidatebean.settxtavailability(rs.getString( 16));
	        	objCandidatebean.settxtpreferredlocation(rs.getString(17));
	        	objCandidatebean.setdrpbywhom(rs.getString(18));
	        	objCandidatebean.settxtskills(rs.getString(19));
	        	objCandidatebean.settxtbesttimefortelephonicinterview(rs.getString(20));
	        	objCandidatebean.setdrpwillinginperson(rs.getString(21));
	        	objCandidatebean.settxtempname(rs.getString(22));
	        	objCandidatebean.settxtempmailID(rs.getString(23));
	        	objCandidatebean.settxtempcontactnumber(rs.getString(24));
	        	objCandidatebean.settxtcontactperson(rs.getString(25));
	        	objCandidatebean.setRID(rs.getString(26));
	        	objCandidatebean.setunique_id(rs.getString(27));
	        	objCandidatebean.setCANID(rs.getString(28));
	        	objCandidatebean.setCandidateStatusValue(rs.getString(29));
	        	objCandidatebean.setCandidateResumeLoc(rs.getString(30));
	        	objCandidatebean.setappliedUserId(rs.getString(31));
	        	objCandidatebean.setCandidateCompanyUniqueId(rs.getString(32));
	        	objCandidatebean.setCandidateCompany(rs.getString(33));
	        	objCandidatebean.setCandidateCompanyCategory(rs.getString(34));
	        	objCandidatebean.setCandidateJoinedStatus(rs.getInt(35));
	        	//objCandidatebean.setCandidateMaritalStatus(rs.getString(36));
	        	
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        Debug.print("SQL Exception in GeneralDBAction.getCanDetailsByUniqueId():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        Debug.print("General Exception  in GeneralDBAction.getAllAppliedCandidateList():" + e.getMessage());
	    }
	    return objCandidatebean;

	}

	public boolean updateResumeToCandidate(String txtemailaddress, String rID,
			String fileLocation) {
		int cnt = 0;
		PreparedStatement prepStmt = null;
	     makeConnection();
	     try {
	         String insertStatement = "update tblCandidateMaster set CandidateResumeLoc = ?" +
	                 " where CandidateEMail = ? and RequirementId = ?";
		
	             prepStmt = con.prepareStatement(insertStatement);
	             prepStmt.setString(1, fileLocation);
	             prepStmt.setString(2, txtemailaddress);
	             prepStmt.setString(3, rID);

	          
	             cnt = prepStmt.executeUpdate();
	             prepStmt.close();
	    

	         releaseConnection();
	         return true;
	     }
	     
	     catch(SQLException sql){
	         releaseConnection();
	         Debug.print("SQL Exception in GeneralDBAction.updateReceivedResumeDet():" + sql.getMessage());
	     }
	     catch(Exception e){
	         releaseConnection();
	         Debug.print("General Exception  in GeneralDBAction.updateReceivedResumeDet():" + e.getMessage());
	     }finally {
	            releaseConnection();
	        }
	        if (cnt >0)
	            return true;
	        else
	            return false;
	}

	public boolean updateCandidateStatusByUniqueId(String canDetailsByUniqueId,
			String candidateStatus) {
		int cnt = 0;
		PreparedStatement prepStmt = null;
	     makeConnection();
	     try {
	    	 String insertStatement = null;
	    	 if(candidateStatus != null && candidateStatus.equalsIgnoreCase("Joined")){
	         insertStatement = "update tblCandidateMaster set candidateStatusValue = ?," +
	                 "CandidateType = 'Employee', can_joined_status = '1' where unique_id = ?";
	    	 }else{
	    		 insertStatement = "update tblCandidateMaster set candidateStatusValue = ?" +
		                 " where unique_id = ?";
	    	 }
	             prepStmt = con.prepareStatement(insertStatement);
	             prepStmt.setString(1, candidateStatus);
	             prepStmt.setString(2, canDetailsByUniqueId);
	             cnt = prepStmt.executeUpdate();
	             prepStmt.close();
	         releaseConnection();
	     }
	     catch(SQLException sql){
	         releaseConnection();
	         Debug.print("SQL Exception in GeneralDBAction.updateReceivedResumeDet():" + sql.getMessage());
	     }
	     catch(Exception e){
	         releaseConnection();
	         Debug.print("General Exception  in GeneralDBAction.updateReceivedResumeDet():" + e.getMessage());
	     }finally{
	    	 releaseConnection();
	     }
	     if (cnt >0)
	            return true;
	        else
	            return false;
	}

	public boolean insertRateNegotiation(String postReqByUniqueId,String postReqId,String negotiateRate, String negotiateReason,
			String uniqueRateNegotiateCompanyId,String rateNegotiateCompanyName,String rateNegotiateCompanyCategory,
			String rateNegotiateCompanyId,String rateNegotiateUserId, String rateNegotiateUserName,
			String rateNegotiateUserRoleId, String rateNegotiateUserRoleName) {

		boolean status = false;
		try{
			makeConnection(); 
		    String insertStmt=  "INSERT INTO tblMfRateNegotiate (postReqByUniqueId,negotiateRate,negotiateReason,"+
			"uniqueRateNegotiateCompanyId,rateNegotiateCompanyName,rateNegotiateCompanyCategory,rateNegotiateCompanyId,"+
		    "rateNegotiateUserId,rateNegotiateUserName,rateNegotiateUserRoleId,rateNegotiateUserRoleName,postReqId)"+
			" VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		      
		      prepStmt = con.prepareStatement(insertStmt);
		      prepStmt.setString(1,postReqByUniqueId);
		      prepStmt.setString(2,negotiateRate);
		      prepStmt.setString(3, negotiateReason);
		      prepStmt.setString(4,uniqueRateNegotiateCompanyId);
		      prepStmt.setString(5,rateNegotiateCompanyName);
		      prepStmt.setString(6,rateNegotiateCompanyCategory);
		      prepStmt.setString(7,rateNegotiateCompanyId);
		      prepStmt.setString(8,rateNegotiateUserId);
		      prepStmt.setString(9,rateNegotiateUserName);
		      prepStmt.setString(10,rateNegotiateUserRoleId);
		      prepStmt.setString(11,rateNegotiateUserRoleName);
		      prepStmt.setString(12, postReqId);
		      	      
		      int cnt= prepStmt.executeUpdate();
		      if(cnt>0){
		          System.out.println("insertRateNegotiation details are successfully inserted into database----------------->");
		          status = true;
		      }
		       prepStmt.close();

		    } 
		    catch (SQLException sqe) {
		      releaseConnection();
		     sqe.printStackTrace();  
		     status = true;
		    } finally {
		      releaseConnection();
		    }
		return status;
	}

	public ArrayList getListReqRateNegotiationByRequesterId(String rateNegotiateUserId) {
		  Debug.print("GeneralDBAction.getListReqRateNegotiationByRequesterId():");
		    ArrayList RateNegotiationList = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = " SELECT unique_id,postReqByUniqueId,negotiateRate,negotiateReason,"+
			"negotiateStatus,uniqueRateNegotiateCompanyId,rateNegotiateCompanyName,rateNegotiateCompanyCategory,"+
		    "rateNegotiateCompanyId,rateNegotiateUserId,rateNegotiateUserName,rateNegotiateUserRoleId,"+
			"rateNegotiateUserRoleName,postReqId from tblMfRateNegotiate where rateNegotiateUserId = ? order by reg_date";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, rateNegotiateUserId);
		        rs = prepStmt.executeQuery();
		        RateNegotiationList = new ArrayList();
		        while(rs.next()){
		        	String unique_id = rs.getString(1);
		        	String postReqByUniqueId = rs.getString(2);
		    		String negotiateRate = rs.getString(3);
		    		String negotiateReason = rs.getString(4);
		    		String negotiateStatus = rs.getString(5);
		    		String uniqueRateNegotiateCompanyId = rs.getString(6);
		    		String rateNegotiateCompanyName = rs.getString(7);
		    		String rateNegotiateCompanyCategory = rs.getString(8);
		    		String rateNegotiateCompanyId = rs.getString(9);
		    		String rateNegotiationUserId = rs.getString(10);
		    		String rateNegotiateUserName = rs.getString(11);
		    		String rateNegotiateUserRoleId = rs.getString(12);
		    		String rateNegotiateUserRoleName = rs.getString(13);
		    		String postReqId = rs.getString(14);
		    		
		            String[] strList = {unique_id, postReqByUniqueId,negotiateRate, negotiateReason, negotiateStatus,
		            uniqueRateNegotiateCompanyId, rateNegotiateCompanyName, rateNegotiateCompanyCategory, rateNegotiateCompanyId,
		            rateNegotiationUserId, rateNegotiateUserName, rateNegotiateUserRoleId, rateNegotiateUserRoleName,postReqId};
		            RateNegotiationList.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getListReqRateNegotiationByRequesterId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getListReqRateNegotiationByRequesterId():" + e.getMessage());
		    }
		    return RateNegotiationList;
	}

	public ArrayList getListAllReqRateNegotiation(String jobPostCompanyUniqueId, String companyJobPostCategory
			,String rateNegotiateUserId) {

		Debug.print("GeneralDBAction.getListAllReqRateNegotiation():");
	    ArrayList RateNegotiationList = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
		try {
			String selectStatement = null;
			if(companyJobPostCategory.equalsIgnoreCase("vendor") || companyJobPostCategory.equalsIgnoreCase("buyer")){
	        selectStatement = " SELECT unique_id,postReqByUniqueId,negotiateRate,negotiateReason,"+
		"negotiateStatus,uniqueRateNegotiateCompanyId,rateNegotiateCompanyName,rateNegotiateCompanyCategory,"+
	    "rateNegotiateCompanyId,rateNegotiateUserId,rateNegotiateUserName,rateNegotiateUserRoleId,"+
		"rateNegotiateUserRoleName,postReqId from tblMfRateNegotiate where uniqueRateNegotiateCompanyId=? order by reg_date";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, jobPostCompanyUniqueId);
			}else{
				selectStatement = " SELECT unique_id,postReqByUniqueId,negotiateRate,negotiateReason,"+
						"negotiateStatus,uniqueRateNegotiateCompanyId,rateNegotiateCompanyName,rateNegotiateCompanyCategory,"+
					    "rateNegotiateCompanyId,rateNegotiateUserId,rateNegotiateUserName,rateNegotiateUserRoleId,"+
						"rateNegotiateUserRoleName,postReqId from tblMfRateNegotiate where rateNegotiateUserId=? order by reg_date";
					      	prepStmt = con.prepareStatement(selectStatement);
					        prepStmt.setString(1, rateNegotiateUserId);
			}
	        rs = prepStmt.executeQuery();
	        RateNegotiationList = new ArrayList();
	        while(rs.next()){
	        	String unique_id = rs.getString(1);
	        	String postReqByUniqueId = rs.getString(2);
	    		String negotiateRate = rs.getString(3);
	    		String negotiateReason = rs.getString(4);
	    		String negotiateStatus = rs.getString(5);
	    		String uniqueRateNegotiateCompanyId = rs.getString(6);
	    		String rateNegotiateCompanyName = rs.getString(7);
	    		String rateNegotiateCompanyCategory = rs.getString(8);
	    		String rateNegotiateCompanyId = rs.getString(9);
	    		String rateNegotiationUserId = rs.getString(10);
	    		String rateNegotiateUserName = rs.getString(11);
	    		String rateNegotiateUserRoleId = rs.getString(12);
	    		String rateNegotiateUserRoleName = rs.getString(13);
	    		String postReqId = rs.getString(14);
	    		
	            String[] strList = {unique_id, postReqByUniqueId,negotiateRate, negotiateReason, negotiateStatus,
	            uniqueRateNegotiateCompanyId, rateNegotiateCompanyName, rateNegotiateCompanyCategory, rateNegotiateCompanyId,
	            rateNegotiationUserId, rateNegotiateUserName, rateNegotiateUserRoleId, rateNegotiateUserRoleName,postReqId};
	            RateNegotiationList.add(strList);
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        Debug.print("SQL Exception in GeneralDBAction.getListAllReqRateNegotiation():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        Debug.print("General Exception  in GeneralDBAction.getListAllReqRateNegotiation():" + e.getMessage());
	    }
	    return RateNegotiationList;
	    
	}

	public ArrayList getReqRateNegotiationByUniqueId(String rateNegUniqueId) {
		Debug.print("GeneralDBAction.getReqRateNegotiationByUniqueId():");
	    ArrayList RateNegotiationList = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
		try {
	        String selectStatement = " SELECT unique_id,postReqByUniqueId,negotiateRate,negotiateReason,"+
		"negotiateStatus,uniqueRateNegotiateCompanyId,rateNegotiateCompanyName,rateNegotiateCompanyCategory,"+
	    "rateNegotiateCompanyId,rateNegotiateUserId,rateNegotiateUserName,rateNegotiateUserRoleId,"+
		"rateNegotiateUserRoleName,postReqId from tblMfRateNegotiate where unique_id = ? order by reg_date";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, rateNegUniqueId);
	        rs = prepStmt.executeQuery();
	        RateNegotiationList = new ArrayList();
	        while(rs.next()){
	        	String unique_id = rs.getString(1);
	        	String postReqByUniqueId = rs.getString(2);
	    		String negotiateRate = rs.getString(3);
	    		String negotiateReason = rs.getString(4);
	    		String negotiateStatus = rs.getString(5);
	    		String uniqueRateNegotiateCompanyId = rs.getString(6);
	    		String rateNegotiateCompanyName = rs.getString(7);
	    		String rateNegotiateCompanyCategory = rs.getString(8);
	    		String rateNegotiateCompanyId = rs.getString(9);
	    		String rateNegotiationUserId = rs.getString(10);
	    		String rateNegotiateUserName = rs.getString(11);
	    		String rateNegotiateUserRoleId = rs.getString(12);
	    		String rateNegotiateUserRoleName = rs.getString(13);
	    		String postReqId = rs.getString(14);
	    		
	            String[] strList = {unique_id, postReqByUniqueId,negotiateRate, negotiateReason, negotiateStatus,
	            uniqueRateNegotiateCompanyId, rateNegotiateCompanyName, rateNegotiateCompanyCategory, rateNegotiateCompanyId,
	            rateNegotiationUserId, rateNegotiateUserName, rateNegotiateUserRoleId, rateNegotiateUserRoleName,postReqId};
	            RateNegotiationList.add(strList);
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        Debug.print("SQL Exception in GeneralDBAction.getReqRateNegotiationByUniqueId():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        Debug.print("General Exception  in GeneralDBAction.getReqRateNegotiationByUniqueId():" + e.getMessage());
	    }
	    return RateNegotiationList;
	}
	
	public boolean findRateNegotiateExistByPostUserId(String postReqByUniqueId,String rateNegotiateUserId) throws SQLException {
        Debug.print("ArabianSeaEntityBean findRateNegotiateExistByPostUserId");

        makeConnection();
        String selectStatement = "SELECT unique_id from tblMfRateNegotiate WHERE postReqByUniqueId = ? "+
        "and rateNegotiateUserId = ? and negotiateStatus = 'pending'";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, postReqByUniqueId);
        prepStmt.setString(2, rateNegotiateUserId);
        ResultSet rs = prepStmt.executeQuery();
        System.out.println("findRateNegotiateExistByPostUserId : Working ");
        boolean result = rs.next();
        prepStmt.close();
        releaseConnection();
        return result;
    }
	
	public HLCUserMaster getUserMasterDetailsByUserId(String userId) throws SQLException {
		HLCUserMaster objUserMaster = new HLCUserMaster();
        Debug.print(" displayUserMasterDetails() User Id:" + userId);
        try {
            makeConnection();
            
            String selectStatement = "SELECT user_id,login_name,user_code,membership_type_id,user_type_id,contact_type_id,"+
            "prefix,first_name,middle_name,last_name,sufix,dob,gender,email_id,citizenship,citizenship_id,password,"+
            "secret_question,secret_answer,register_date,login_date,active_status,non_usea_mailing_status,non_usea_email_status,"+
            "address_status,spl_notes,request_status,company,category FROM tblUserMaster where user_id=?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
            	objUserMaster.setUserId(rs.getString("user_id"));
            	objUserMaster.setLoginName(rs.getString("login_name"));
            	objUserMaster.setUserCode(rs.getString("user_code"));
            	objUserMaster.setMemeberTypeId(rs.getString("membership_type_id"));
            	objUserMaster.setUserTypeId(rs.getString("user_type_id"));
            	objUserMaster.setContactTypeId(rs.getString("contact_type_id"));
            	objUserMaster.setPrefix(rs.getString("prefix"));
            	objUserMaster.setFirstName(rs.getString("first_name"));
            	objUserMaster.setLastName(rs.getString("last_name"));
            	objUserMaster.setSufix(rs.getString("sufix"));
            	objUserMaster.setDob(rs.getString("dob"));
            	objUserMaster.setGender(rs.getString("gender"));
            	objUserMaster.setEmailId(rs.getString("email_id"));
            	objUserMaster.setPassword(rs.getString("password"));
            	objUserMaster.setSecretQuestion(rs.getString("secret_question"));
            	objUserMaster.setSecretAnswer(rs.getString("secret_answer"));
            	objUserMaster.setCompanyName(rs.getString("company"));
            	objUserMaster.setCompanyCategory(rs.getString("category"));
                
            }
            Debug.print("User Master Details \n"+objUserMaster);
            prepStmt.close();
            
        } catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while getting User Id : "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return objUserMaster;
    }
	
	public String getRoleIdByUserId(String userId) {
		Debug.print("GeneralDBAction.getRoleIdByUserId():");
		 PreparedStatement prepstmt = null;
		 ResultSet rs = null;
		 makeConnection();
		 String userRoleId = null;
		 try{
			 String sqlQuery = "Select role_id from tblMapUserPrivilege where user_id=?";
			 prepstmt = con.prepareStatement(sqlQuery);
			 prepstmt.setString(1, userId);
			 rs = prepstmt.executeQuery();
			 rs.next();
			 userRoleId = rs.getString(1);
			 rs.close();
			 prepstmt.close();
			 releaseConnection();
		 }
		 catch(SQLException sql)
	     {
	         releaseConnection();
	         Debug.print((new StringBuilder()).append("SQL Exception in GeneralDBAction.getRoleIdByUserId():").append(sql.getMessage()).toString());
	     }
	     catch(Exception e)
	     {
	         releaseConnection();
	         Debug.print((new StringBuilder()).append("General Exception  in GeneralDBAction.getRoleIdByUserId():").append(e.getMessage()).toString());
	     }
		 
		 return userRoleId;
		
	}

	public ArrayList getAllEmployeeDetails() {
		Debug.print("GeneralDBAction.getAllEmployeeDetails():");
	    ArrayList employeeList = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
		try {
	        String selectStatement = " SELECT unique_id,user_id,role_id,emp_id,emp_dberp_partyid,"+
		    		"emp_ssn_no,emp_salutation,emp_suffix,emp_fname,emp_mname,emp_lname,emp_gender,emp_marital_status,"+
		    		"emp_dob,emp_emailid,emp_mobileno,emp_contactno,emp_passportno,emp_address1,"+
		    		"emp_address1c,emp_address2,emp_address2c,emp_city,emp_cityc,emp_state,emp_statec,"+
		    		"emp_country,emp_countryc,emp_postalcode,emp_postalcodec,emp_company,emp_company_category,"+
		    		"emp_jobtitle,emp_department,emp_joblocation,emp_paygroup,emp_payrate_perhour,emp_payperiod,"+
		    		"emp_federaltax_whform_loc,emp_statetax_whform_loc,emp_joining_date,active_status,federalStateFormStatus,"+
		    		"client_name,client_address,project_name,client_manager,manager_phone,manager_email,emp_role,start_date,end_date,project_status"+
		    		" from tblEmployeeMasterDetails order by reg_date";
	        prepStmt = con.prepareStatement(selectStatement);
	        rs = prepStmt.executeQuery();
	        employeeList = new ArrayList();
	        while(rs.next()){
	        			String empuniqueId = rs.getString(1);
	        			String empUserId = rs.getString(2);
	        			String empRoleId = rs.getString(3);
	        			String empId = rs.getString(4);
	        			String empdberpPartyid = rs.getString(5);
	        			String empSSNNo = rs.getString(6);
	        			String empSalutation = rs.getString(7);
	        			String empSuffix = rs.getString(8);
	        			String empFname = rs.getString(9);
	        			String empMname = rs.getString(10);
	        			String empLname = rs.getString(11);
	        			String empGender = rs.getString(12);
	        			String empMaritalStatus = rs.getString(13);
	        			String empDOB = rs.getString(14);
	        			String empEmailId = rs.getString(15);
	        			String empMobileNo = rs.getString(16);
	        			String empContactNo = rs.getString(17);
	        			String empPassportNo = rs.getString(18);
	        			String empAddress1 = rs.getString(19);
	        			String empAddress1c = rs.getString(20);
	        			String empAddress2 = rs.getString(21);
	        			String empAddress2c = rs.getString(22);
	        			String empCity = rs.getString(23);
	        			String empCityc = rs.getString(24);
	        			String empState = rs.getString(25);
	        			String empStatec = rs.getString(26);
	        			String empCountry = rs.getString(27);
	        			String empCountryc = rs.getString(28);
	        			String empPostalCode = rs.getString(29);
	        			String empPostalCodec = rs.getString(30);
	        			String empCompany = rs.getString(31);
	        			String empCompanyCategory = rs.getString(32);
	        			String empJobTitle = rs.getString(33);
	        			String empDepartment = rs.getString(34);
	        			String empJobLocation = rs.getString(35);
	        			String empPayGroup = rs.getString(36);
	        			String empPayratePerhour = Double.toString(rs.getDouble(37));
	        			String empPayPeriod = rs.getString(38);
	        			String empFederalTaxWHFormLocation = rs.getString(39);
	        			String empStateTaxWHFormLocation = rs.getString(40);
	        			String empJoiningDate = rs.getString(41);
	    	        	String empActiveStatus = Boolean.toString(rs.getBoolean(42));
	    	        	String client_name = rs.getString(44);
	    	        	String client_address=rs.getString(45);
	    	        	String project_name=rs.getString(46);
	    	        	String client_manager=rs.getString(47);
	    	        	String manager_phone=rs.getString(48);
	    	        	String manager_email=rs.getString(49);
	    	        	String emp_role=rs.getString(50);
	    	        	String start_date=rs.getString(51);
	    	        	String end_date=rs.getString(52);
	    	        	String project_status=rs.getString(53);
	    	        	String FedStateEmpMastTblStatus = Boolean.toString(rs.getBoolean(43));
	    	        	System.out.println("employee client details======>"+client_name+client_address+project_name);
	    		
	            String[] strList = {empuniqueId,empUserId,empRoleId,empId,empdberpPartyid,empSSNNo,
	            		empSalutation,empSuffix,empFname,empMname,empLname,empGender,empMaritalStatus,empDOB,
	            		empEmailId,empMobileNo,empContactNo,empPassportNo,empAddress1,empAddress1c,empAddress2,
	            		empAddress2c,empCity,empCityc,empState,empStatec,empCountry,empCountryc,empPostalCode,
	            		empPostalCodec,empCompany,empCompanyCategory,empJobTitle,empDepartment,empJobLocation,
	            		empPayGroup,empPayratePerhour,empPayPeriod,empFederalTaxWHFormLocation,empStateTaxWHFormLocation,
	            		empJoiningDate,empActiveStatus,client_name,client_address,project_name,
	            		client_manager,manager_phone,manager_email,emp_role,start_date,end_date,project_status,FedStateEmpMastTblStatus};
	            employeeList.add(strList);
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        Debug.print("SQL Exception in GeneralDBAction.getAllEmployeeDetails():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        Debug.print("General Exception  in GeneralDBAction.getAllEmployeeDetails():" + e.getMessage());
	    }
	    return employeeList;
	    
	}
	
	public ArrayList getAllEmployeeDetailsByUniqueId(String uniqueId) {
		Debug.print("GeneralDBAction.getAllEmployeeDetailsByUniqueId():");
	    ArrayList employeeList = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
		try {
	        String selectStatement = " SELECT unique_id,user_id,role_id,emp_id,emp_dberp_partyid,"+
		    		"emp_ssn_no,emp_salutation,emp_suffix,emp_fname,emp_mname,emp_lname,emp_gender,emp_marital_status,"+
		    		"emp_dob,emp_emailid,emp_mobileno,emp_contactno,emp_passportno,emp_address1,"+
		    		"emp_address1c,emp_address2,emp_address2c,emp_city,emp_cityc,emp_state,emp_statec,"+
		    		"emp_country,emp_countryc,emp_postalcode,emp_postalcodec,emp_company,emp_company_category,"+
		    		"emp_jobtitle,emp_department,emp_joblocation,emp_paygroup,emp_payrate_perhour,emp_payperiod,"+
		    		"emp_federaltax_whform_loc,emp_statetax_whform_loc,emp_joining_date,active_status,federalStateFormStatus,"+
		    		"client_name,client_address,project_name,client_manager,manager_phone,manager_email,emp_role,start_date,end_date,project_status"+
		    		" from tblEmployeeMasterDetails where unique_id=?";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, uniqueId);
	        rs = prepStmt.executeQuery();
	        employeeList = new ArrayList();
	        while(rs.next()){
	        			String empuniqueId = rs.getString(1);
	        			String empUserId = rs.getString(2);
	        			String empRoleId = rs.getString(3);
	        			String empId = rs.getString(4);
	        			String empdberpPartyid = rs.getString(5);
	        			String empSSNNo = rs.getString(6);
	        			String empSalutation = rs.getString(7);
	        			String empSuffix = rs.getString(8);
	        			String empFname = rs.getString(9);
	        			String empMname = rs.getString(10);
	        			String empLname = rs.getString(11);
	        			String empGender = rs.getString(12);
	        			String empMaritalStatus = rs.getString(13);
	        			String empDOB = rs.getString(14);
	        			String empEmailId = rs.getString(15);
	        			String empMobileNo = rs.getString(16);
	        			String empContactNo = rs.getString(17);
	        			String empPassportNo = rs.getString(18);
	        			String empAddress1 = rs.getString(19);
	        			String empAddress1c = rs.getString(20);
	        			String empAddress2 = rs.getString(21);
	        			String empAddress2c = rs.getString(22);
	        			String empCity = rs.getString(23);
	        			String empCityc = rs.getString(24);
	        			String empState = rs.getString(25);
	        			String empStatec = rs.getString(26);
	        			String empCountry = rs.getString(27);
	        			String empCountryc = rs.getString(28);
	        			String empPostalCode = rs.getString(29);
	        			String empPostalCodec = rs.getString(30);
	        			String empCompany = rs.getString(31);
	        			String empCompanyCategory = rs.getString(32);
	        			String empJobTitle = rs.getString(33);
	        			String empDepartment = rs.getString(34);
	        			String empJobLocation = rs.getString(35);
	        			String empPayGroup = rs.getString(36);
	        			String empPayratePerhour = Double.toString(rs.getDouble(37));
	        			String empPayPeriod = rs.getString(38);
	        			String empFederalTaxWHFormLocation = rs.getString(39);
	        			String empStateTaxWHFormLocation = rs.getString(40);
	        			String empJoiningDate = rs.getString(41);
	    	        	String empActiveStatus = Boolean.toString(rs.getBoolean(42));
	    	        	String FedStateEmpMastTblStatus = Boolean.toString(rs.getBoolean(43));
	    	        	String client_name = rs.getString(44);
	    	        	String client_address=rs.getString(45);
	    	        	String project_name=rs.getString(46);
	    	        	String client_manager=rs.getString(47);
	    	        	String manager_phone=rs.getString(48);
	    	        	String manager_email=rs.getString(49);
	    	        	String emp_role=rs.getString(50);
	    	        	String start_date=rs.getString(51);
	    	        	String end_date=rs.getString(52);
	    	        	String project_status=rs.getString(53);
	    		
	            String[] strList = {empuniqueId,empUserId,empRoleId,empId,empdberpPartyid,empSSNNo,
	            		empSalutation,empSuffix,empFname,empMname,empLname,empGender,empMaritalStatus,empDOB,
	            		empEmailId,empMobileNo,empContactNo,empPassportNo,empAddress1,empAddress1c,empAddress2,
	            		empAddress2c,empCity,empCityc,empState,empStatec,empCountry,empCountryc,empPostalCode,
	            		empPostalCodec,empCompany,empCompanyCategory,empJobTitle,empDepartment,empJobLocation,
	            		empPayGroup,empPayratePerhour,empPayPeriod,empFederalTaxWHFormLocation,empStateTaxWHFormLocation,
	            		empJoiningDate,empActiveStatus,FedStateEmpMastTblStatus,client_name,client_address,project_name,
	            		client_manager,manager_phone,manager_email,emp_role,start_date,end_date,project_status};
	            employeeList.add(strList);
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        Debug.print("SQL Exception in GeneralDBAction.getAllEmployeeDetailsByUniqueId():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        Debug.print("General Exception  in GeneralDBAction.getAllEmployeeDetailsByUniqueId():" + e.getMessage());
	    }
	    return employeeList;
	    
	}

	public ArrayList selectAllPostReqByDays(int days, String minimum_Exp,String maximum_Exp,String salary_range_from,
			String salary_range_to,String rate_range_from, String rate_range_to,String skills_value, String position_value,
			String Industry_value,String jobtype_value,String location_value){
	    Debug.print("GeneralDBAction.selectAllPostReqByExp():");
	    ArrayList postReqList = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    
	    makeConnection();
	    
	   
	  
	    Calendar cal1 = Calendar.getInstance();
	    java.util.Date today = new java.sql.Date(cal1.getTime().getTime());
	    System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(today));
	    cal1.setTime(today);
	    cal1.set(Calendar.DATE, (cal1.get(Calendar.DATE)-days));
	    today = cal1.getTime();
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(today));
	    String date= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(today);
	    System.out.println("date---"+date);
		
	   
		try {
	        String selectStatement = " SELECT requirement_id ,jobPostCompanyName ,ClientReferenceID ,ReferenceID ,ClientIndustry ,PositionID ,Position"+
	      ",InterviewProcess ,VisaAccepted ,Skills ,JobType ,LocationField ,NumberOfOpenings ,RecruiterEmail ,StartDate ,EndDate ,RequirementStatus"+
	      ",Rate ,Duration ,LocalRequired ,Salary ,FlexiblityOnSalary ,FlexiblityOnHrlyRate ,DateOnHold ,ExtraDocumentsRequired ,minExperience"+
	      ",Notes ,jobResponsibilites ,jobPostDate, post_req_uniqueId, JobTitle, jobPostCompanyCategory, uniqueJobPostCompanyId"+
	      ", jobPostCompanyId, jobPostUserId, jobPostUserName, jobPostUserRoleId, jobPostUserRoleName,maxExperience from tblMFPostRequirements where jobPostDate >= ? ";
	        
	        if(minimum_Exp!=null && maximum_Exp!=null){
              
                selectStatement = selectStatement + "and minExperience  >='" + minimum_Exp + "' and maxExperience  <= '" + maximum_Exp+"'" ;
                Debug.print("Query : " + selectStatement);
	        }
	        
	        
	        
	        if(salary_range_from!=null && salary_range_to!=null){
	        	 selectStatement = selectStatement + " and Salary Between '" + (salary_range_from) +
	        			 "' and '" + (salary_range_to) + "' " ;
	            }
	        if(rate_range_from!=null && rate_range_to!=null){
	        	 selectStatement = selectStatement + " and Rate Between '" + (rate_range_from) +
	        			 "' and '" + (rate_range_to) + "' " ;
	            }
	        
	        String temp = "";
	        if(skills_value!=null && skills_value.trim().length()!=0){
	        	String [] skills_array = skills_value .split(",");
	         selectStatement = selectStatement + " and ";
	         Debug.print("Query : " + selectStatement);
	          for(int i=0; i<skills_array.length;i++){
	           temp = temp + "Skills like '%" + skills_array[i] + "%' OR ";
	           }
	          temp = temp.substring(0,temp.length()-4);
	         selectStatement = selectStatement + temp;
	         Debug.print("Query : " + selectStatement);
	        }
	        
	        String position_temp = "";
	        if(position_value!=null && position_value.trim().length()!=0){
	        	String [] position_array = position_value .split(",");
	         selectStatement = selectStatement + " and ";
	         Debug.print("Query : " + selectStatement);
	          for(int i=0; i<position_array.length;i++){
	        	  position_temp = position_temp + "Position like '%" + position_array[i] + "%' OR ";
	           }
	          position_temp = position_temp.substring(0,position_temp.length()-4);
	         selectStatement = selectStatement + position_temp;
	         Debug.print("Query : " + selectStatement);
	        }
	        
	       
	        
	        if(Industry_value!=null && Industry_value.trim().length()!=0){
	        	 selectStatement = selectStatement + " and ClientIndustry like '%" + Industry_value + "%' ";
	            }
	        if(jobtype_value!=null && jobtype_value.trim().length()!=0){
	        	 selectStatement = selectStatement + " and JobType like '%" + jobtype_value + "%' ";
	            }
	        
	        
	        String location_temp = "";
	        if(location_value!=null && location_value.trim().length()!=0){
	        	String [] location_array = location_value .split(",");
	         selectStatement = selectStatement + " and ";
	         Debug.print("Query : " + selectStatement);
	          for(int i=0; i<location_array.length;i++){
	        	  location_temp = location_temp + "Position like '%" + location_array[i] + "%' OR ";
	           }
	          location_temp = location_temp.substring(0,location_temp.length()-4);
	         selectStatement = selectStatement + location_temp;
	         Debug.print("Query : " + selectStatement);
	        }
	        
	        
	       
	        
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1,new SimpleDateFormat("yyyy-MM-dd").format(today));
	       
	       
	        rs = prepStmt.executeQuery();
	        postReqList = new ArrayList();
	        while(rs.next()){
	    		String requirementId = rs.getString(1);
	    		String jobPostCompanyName = rs.getString(2);
	        	String ClientReferenceID = rs.getString(3);
	    		String ReferenceID = rs.getString(4);
	    		String ClientIndustry = rs.getString(5);
	    		String PositionID = rs.getString(6);
	    		String Position = rs.getString(7);
	    		String InterviewProcess = rs.getString(8);
	    		String VisaAccepted = rs.getString(9);
	    		String Skills = rs.getString(10);
	    		String JobType = rs.getString(11);
	    		String LocationField = rs.getString(12);
	    		String NumberOfOpenings = rs.getString(13);
	    		String RecruiterEmail = rs.getString(14);
	    		String StartDate = rs.getString(15);
	    		String EndDate = rs.getString(16);
	    		String RequirementStatus = rs.getString(17); 
	    		String Rate = rs.getString(18);
	    		String Duration = rs.getString(19);
	    		String LocalRequired = rs.getString(20);
	    		String Salary = rs.getString(21);
	    		String FlexiblityOnSalary = rs.getString(22);
	    		String FlexiblityOnHrlyRate = rs.getString(23);
	    		String DateOnHold = rs.getString(24);
	    		String ExtraDocumentsRequired = rs.getString(25);
	    		String minExperience = rs.getString(26);
	    		String Notes = rs.getString(27);
	    		String jobResponsibilites = rs.getString(28);
	    		String jobPostDate = rs.getString(29);
	    		String postReqUniqueId = rs.getString (30);
	    		String JobTitle = rs.getString(31);
	    		String jobPostCompanyCategory = rs.getString(32);
	    		String uniqueJobPostCompanyId = rs.getString(33);
	    		String jobPostCompanyId = rs.getString(34);
	    		String jobPostUserId = rs.getString(35);
	    		String jobPostUserName = rs.getString(36);
	    		String jobPostUserRoleId = rs.getString(37);
	    		String jobPostUserRoleName = rs.getString(38);
	    		String maxExperience = rs.getString(39);
	    		
	            String[] ReqList = {postReqUniqueId, requirementId,jobPostCompanyName, ClientReferenceID, ReferenceID,
	            		ClientIndustry, PositionID, Position, InterviewProcess, VisaAccepted, Skills, JobType, LocationField,
	            		NumberOfOpenings, RecruiterEmail, StartDate, EndDate, RequirementStatus, Rate, Duration, LocalRequired,
	            		Salary, FlexiblityOnSalary, FlexiblityOnHrlyRate, DateOnHold, ExtraDocumentsRequired,
	            		minExperience, Notes, jobResponsibilites, jobPostDate, JobTitle, jobPostCompanyCategory,
	            		uniqueJobPostCompanyId, jobPostCompanyId, jobPostUserId, jobPostUserName, jobPostUserRoleId,
	            		jobPostUserRoleName,maxExperience};
	            postReqList.add(ReqList);
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        Debug.print("SQL Exception in GeneralDBAction.selectAllPostReqByDays():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        Debug.print("General Exception  in GeneralDBAction.selectAllPostReqByDays():" + e.getMessage());
	    }
	    return postReqList;
	}
	
	public boolean insertEmployeePayrollDetails(String uniqueEmployeeId, String empId, String empSSNNo,
			String empFname, String empLname, String taxyear, String chequeNo, String chequeDate, String taxCountry, String state,
			String fWHITMarital, String fWHITNoFAllowances, String state_wh_info, String PayPeriod,
			String tax_payduration_from,String tax_payduration_to, String PayRateType, String overtime, String gen_payRate, String gen_PayRateType,
			String gen_total_hours, String gen_totalYTDGross, String gen_totalGross_CP, String ot_row_count,
			String ot_payrateArray, String ot_payrate_typeArray, String ot_total_hours_workedArray,
			String ot_pay_ytdArray, String ot_pay_cpArray, String federal_it_ytd, String federal_it_cp,
			String social_rate, String social_annual, String social_ytd, String social_cp, String 
			Medicare_rate, String Medicare_ytd, String Medicare_cp, String State_IT_ytd, String State_IT_cp,
			String After_Tax_Income, String After_Tax_Income_YTD, String After_Tax_Income_CP,
			String After_Tax_deduction, String After_Tax_deduction_YTD, String After_Tax_deduction_CP,
			String netAmount) throws RemoteException, ParseException {
		
		   String fromDate = tax_payduration_from;
		   String toDate = tax_payduration_to;
		   
		   SimpleDateFormat sdfFromDate= new SimpleDateFormat("MM-dd-yyyy");
		   java.util.Date frmUtilDate = sdfFromDate.parse(fromDate);
		   java.sql.Date sqlFromDate = new java.sql.Date(frmUtilDate.getTime());
		   
		   SimpleDateFormat sdfToDate= new SimpleDateFormat("MM-dd-yyyy");
		   java.util.Date toUtilDate = sdfFromDate.parse(toDate);
		   java.sql.Date sqlToDate = new java.sql.Date(toUtilDate.getTime());


		boolean insertStatus = false;
		try{

		    makeConnection(); 
		    String insertStmt=  "INSERT INTO tblEmployeePayrollDetails(emp_unique_id,emp_id,emp_ssn_no,emp_name"
		    		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
		    		+",tax_payperiod,tax_payduration_from,tax_payduration_to,tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
		    		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
		    		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate,ss_annual_max"
					+",ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd,state_pay_cp"
		    		+",after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp,after_tax_deduction_pay"
					+",after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay,cheque_no,cheque_date)VALUES"
		    		+"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		      

		      prepStmt = con.prepareStatement(insertStmt);
		      
		      prepStmt.setString(1, uniqueEmployeeId);
		      prepStmt.setString(2, empId);
		      prepStmt.setString(3, empSSNNo);
		      prepStmt.setString(4, empFname+" "+empLname);
		      prepStmt.setString(5, taxyear);
		      prepStmt.setString(6, taxCountry);
		      prepStmt.setString(7, state);
		      prepStmt.setString(8, fWHITMarital);
		      prepStmt.setString(9, fWHITNoFAllowances);
		      prepStmt.setString(10, state_wh_info);
		      prepStmt.setString(11, PayPeriod);
		      prepStmt.setDate(12, sqlFromDate);
		      prepStmt.setDate(13, sqlToDate);
		      prepStmt.setString(14, PayRateType);
		      prepStmt.setString(15, overtime);
		      prepStmt.setString(16, gen_payRate);
		      prepStmt.setString(17, gen_PayRateType);
		      prepStmt.setString(18, gen_total_hours);
		      prepStmt.setString(19, gen_totalYTDGross);
		      prepStmt.setString(20, gen_totalGross_CP);
		      prepStmt.setString(21, ot_row_count);
		      prepStmt.setString(22, ot_payrateArray);
		      prepStmt.setString(23, ot_payrate_typeArray);
		      prepStmt.setString(24, ot_total_hours_workedArray);
		      prepStmt.setString(25, ot_pay_ytdArray);
		      prepStmt.setString(26, ot_pay_cpArray);
		      prepStmt.setString(27, federal_it_ytd);
		      prepStmt.setString(28, federal_it_cp);
		      prepStmt.setString(29, social_rate);
		      prepStmt.setString(30, social_annual);
		      prepStmt.setString(31, social_ytd);
		      prepStmt.setString(32, social_cp);
		      prepStmt.setString(33, Medicare_rate);
		      prepStmt.setString(34, Medicare_ytd);
		      prepStmt.setString(35, Medicare_cp);
		      prepStmt.setString(36, State_IT_ytd);
		      prepStmt.setString(37, State_IT_cp);
		      prepStmt.setString(38, After_Tax_Income);
		      prepStmt.setString(39, After_Tax_Income_YTD);
		      prepStmt.setString(40, After_Tax_Income_CP);
		      prepStmt.setString(41, After_Tax_deduction);
		      prepStmt.setString(42, After_Tax_deduction_YTD);
		      prepStmt.setString(43, After_Tax_deduction_CP);
		      prepStmt.setString(44, netAmount);
		      prepStmt.setString(45, chequeNo);
		      prepStmt.setString(46, chequeDate);
		      
		      int cnt= prepStmt.executeUpdate();
		      if(cnt>0){
		          System.out.println("Employee Payroll Details have been successfully inserted into database----------------->");
		          insertStatus = true;
		      }
		       prepStmt.close();

		    } 
		    catch (SQLException sqe) {
		      releaseConnection();
		      
		     System.err.println("error in insertEmployeePayrollDetails------------------->"+sqe.getMessage()); 
		     sqe.printStackTrace();
		    } finally {
		      releaseConnection();
		    }
		return insertStatus;
		
	}
	
	public boolean updateEmployeePayrollDetails(String uniquePayrollId, String uniqueEmployeeId, String empId, String empSSNNo,
			String empFname, String empLname, String taxyear, String chequeNo, String chequeDate, String taxCountry, String state,
			String fWHITMarital, String fWHITNoFAllowances, String state_wh_info, String PayPeriod,
			String tax_payduration_from, String tax_payduration_to, String PayRateType, String overtime, String gen_payRate, String gen_PayRateType,
			String gen_total_hours, String gen_totalYTDGross, String gen_totalGross_CP, String ot_row_count,
			String ot_payrateArray, String ot_payrate_typeArray, String ot_total_hours_workedArray,
			String ot_pay_ytdArray, String ot_pay_cpArray, String federal_it_ytd, String federal_it_cp,
			String social_rate, String social_annual, String social_ytd, String social_cp, String 
			Medicare_rate, String Medicare_ytd, String Medicare_cp, String State_IT_ytd, String State_IT_cp,
			String After_Tax_Income, String After_Tax_Income_YTD, String After_Tax_Income_CP,
			String After_Tax_deduction, String After_Tax_deduction_YTD, String After_Tax_deduction_CP,
			String netAmount) throws RemoteException, ParseException {
		
			Debug.print("Inside db.updateEmployeePayrollDetails------->");
		
			String fromDate = tax_payduration_from;
			   String toDate = tax_payduration_to;
			   
			   SimpleDateFormat sdfFromDate= new SimpleDateFormat("MM-dd-yyyy");
			   java.util.Date frmUtilDate = sdfFromDate.parse(fromDate);
			   java.sql.Date sqlFromDate = new java.sql.Date(frmUtilDate.getTime());
			   
			   SimpleDateFormat sdfToDate= new SimpleDateFormat("MM-dd-yyyy");
			   java.util.Date toUtilDate = sdfFromDate.parse(toDate);
			   java.sql.Date sqlToDate = new java.sql.Date(toUtilDate.getTime());

		boolean updateStatus = false;
		try{

		    makeConnection(); 
		    String insertStmt=  "UPDATE tblEmployeePayrollDetails SET emp_unique_id = ?,emp_id = ?,emp_ssn_no = ?"
		    		+",emp_name = ?,tax_year = ?,tax_country = ?,tax_state = ?,fed_marital_status = ?"
		    		+",fed_nof_allowance = ?,state_wh_info = ?,tax_payperiod = ?,tax_payduration_from = ?"
		    		+",tax_payduration_to = ?,tax_payrate_type = ?,if_overtime = ?,gen_payrate = ?,gen_payrate_type = ?"
		    		+",gen_total_hours_worked = ?,gen_pay_ytd = ?,gen_pay_cp = ?,ot_row_count = ?,ot_payrate = ?"
		    		+",ot_payrate_type = ?,ot_total_hours_worked = ?,ot_pay_ytd = ?,ot_pay_cp = ?,fed_pay_ytd = ?"
		    		+",fed_pay_cp = ?,ss_payrate = ?,ss_annual_max = ?,ss_pay_ytd = ?,ss_pay_cp = ?,mc_payrate = ?"
		    		+",mc_pay_ytd = ?,mc_pay_cp = ?,state_pay_ytd = ?,state_pay_cp = ?,after_tax_income_pay = ?"
		    		+",after_tax_income_ytd = ?,after_tax_income_cp = ?,after_tax_deduction_pay = ?"
		    		+",after_tax_deduction_ytd = ?,after_tax_deduction_cp = ?,total_netpay = ?"
		    		+",cheque_no = ?, cheque_date = ? WHERE unique_payroll_id=?";
		      

		      prepStmt = con.prepareStatement(insertStmt);
		      
		      prepStmt.setString(1, uniqueEmployeeId);
		      prepStmt.setString(2, empId);
		      prepStmt.setString(3, empSSNNo);
		      prepStmt.setString(4, empFname+" "+empLname);
		      prepStmt.setString(5, taxyear);
		      prepStmt.setString(6, taxCountry);
		      prepStmt.setString(7, state);
		      prepStmt.setString(8, fWHITMarital);
		      prepStmt.setString(9, fWHITNoFAllowances);
		      prepStmt.setString(10, state_wh_info);
		      prepStmt.setString(11, PayPeriod);
		      prepStmt.setDate(12, sqlFromDate);
		      prepStmt.setDate(13, sqlToDate);
		      prepStmt.setString(14, PayRateType);
		      prepStmt.setString(15, overtime);
		      prepStmt.setString(16, gen_payRate);
		      prepStmt.setString(17, gen_PayRateType);
		      prepStmt.setString(18, gen_total_hours);
		      prepStmt.setString(19, gen_totalYTDGross);
		      prepStmt.setString(20, gen_totalGross_CP);
		      prepStmt.setString(21, ot_row_count);
		      prepStmt.setString(22, ot_payrateArray);
		      prepStmt.setString(23, ot_payrate_typeArray);
		      prepStmt.setString(24, ot_total_hours_workedArray);
		      prepStmt.setString(25, ot_pay_ytdArray);
		      prepStmt.setString(26, ot_pay_cpArray);
		      prepStmt.setString(27, federal_it_ytd);
		      prepStmt.setString(28, federal_it_cp);
		      prepStmt.setString(29, social_rate);
		      prepStmt.setString(30, social_annual);
		      prepStmt.setString(31, social_ytd);
		      prepStmt.setString(32, social_cp);
		      prepStmt.setString(33, Medicare_rate);
		      prepStmt.setString(34, Medicare_ytd);
		      prepStmt.setString(35, Medicare_cp);
		      prepStmt.setString(36, State_IT_ytd);
		      prepStmt.setString(37, State_IT_cp);
		      prepStmt.setString(38, After_Tax_Income);
		      prepStmt.setString(39, After_Tax_Income_YTD);
		      prepStmt.setString(40, After_Tax_Income_CP);
		      prepStmt.setString(41, After_Tax_deduction);
		      prepStmt.setString(42, After_Tax_deduction_YTD);
		      prepStmt.setString(43, After_Tax_deduction_CP);
		      prepStmt.setString(44, netAmount);
		      prepStmt.setString(45, chequeNo);
		      prepStmt.setString(46, chequeDate);
		      prepStmt.setString(47, uniquePayrollId);
		      
		      int cnt= prepStmt.executeUpdate();
		      if(cnt>0){
		          System.out.println("Employee Payroll Details have been successfully updated into database----------------->");
		          updateStatus = true;
		      }
		       prepStmt.close();

		    } 
		    catch (SQLException sqe) {
		      releaseConnection();
		      
		     System.err.println("error in db.updateEmployeePayrollDetails------------------->"+sqe.getMessage()); 
		     sqe.printStackTrace();
		    } finally {
		      releaseConnection();
		    }
		return updateStatus;
		
	}
	
	public ArrayList getAllEmployeePayrollDetails() {
		Debug.print("GeneralDBAction.getAllEmployeePayrollDetails():");
	    ArrayList employeePayrollList = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
	    	    
	    
		try {
	        String selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
	        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
	        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
	        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,tax_payrate_type,"
	        		+"if_overtime,gen_payrate,gen_payrate_type"
	        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
	        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
	        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
	        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
	        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
	        		+",reg_date, payment_status from tblEmployeePayrollDetails order by reg_date desc";
	        prepStmt = con.prepareStatement(selectStatement);
	        rs = prepStmt.executeQuery();
	        employeePayrollList = new ArrayList();
	        while(rs.next()){
	        			String unique_payroll_id = rs.getString(1);
	        			String emp_unique_id = rs.getString(2);
	        			String emp_id = rs.getString(3);
	        			String emp_ssn_no = rs.getString(4);
	        			String emp_name = rs.getString(5);
	        			String tax_year = rs.getString(6);
	        			String tax_country = rs.getString(7);
	        			String tax_state = rs.getString(8);
	        			String fed_marital_status = rs.getString(9);
	        			String fed_nof_allowance = rs.getString(10);
	        			String state_wh_info = rs.getString(11);
	        			String tax_payperiod = rs.getString(12);
	        			String tax_payduration_from = rs.getString(13);
	        			String tax_payduration_to = rs.getString(14);
	        			String tax_payrate_type = rs.getString(15);
	        			String if_overtime = rs.getString(16);
	        			String gen_payrate = rs.getString(17);
	        			String gen_payrate_type = rs.getString(18);
	        			String gen_total_hours_worked = rs.getString(19);
	        			String gen_pay_ytd = rs.getString(20);
	        			String gen_pay_cp = rs.getString(21);
	        			String ot_row_count = rs.getString(22);
	        			String ot_payrate = rs.getString(23);
	        			String ot_payrate_type = rs.getString(24);
	        			String ot_total_hours_worked = rs.getString(25);
	        			String ot_pay_ytd = rs.getString(26);
	        			String ot_pay_cp = rs.getString(27);
	        			String fed_pay_ytd = rs.getString(28);
	        			String fed_pay_cp = rs.getString(29);
	        			String ss_payrate = rs.getString(30);
	        			String ss_annual_max = rs.getString(31);
	        			String ss_pay_ytd = rs.getString(32);
	        			String ss_pay_cp = rs.getString(33);
	        			String mc_payrate = rs.getString(34);
	        			String mc_pay_ytd = rs.getString(35);
	        			String mc_pay_cp = rs.getString(36);
	        			String state_pay_ytd = rs.getString(37);
	        			String state_pay_cp = rs.getString(38);
	        			String after_tax_income_pay = rs.getString(39);
	        			String after_tax_income_ytd = rs.getString(40);
	        			String after_tax_income_cp = rs.getString(41);
	        			String after_tax_deduction_pay = rs.getString(42);
	    	        	String after_tax_deduction_ytd = rs.getString(43);
	    	        	String after_tax_deduction_cp = rs.getString(44);
	    	        	String total_netpay = rs.getString(45);
	    	        	String payment_status = rs.getString(46);
	    		
	            String[] strList = {unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name
	            		,tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info
	            		,tax_payperiod,tax_payduration_from,tax_payduration_to,tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type
	            		,gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type
	            		,ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate
	            		,ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd
	            		,state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp
	            		,after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay,payment_status};
	            employeePayrollList.add(strList);
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        Debug.print("SQL Exception in GeneralDBAction.getAllEmployeePayrollDetails():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        Debug.print("General Exception  in GeneralDBAction.getAllEmployeePayrollDetails():" + e.getMessage());
	    }
	    return employeePayrollList;
	    
	}
	
	public ArrayList getEmployeePayrollDetailsByUniqueId(String payrollId) {
		Debug.print("GeneralDBAction.getAllEmployeePayrollDetails():");
	    ArrayList employeePayrollList = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
	    
		try {
	        String selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
	        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
	        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
	        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
	        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
	        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
	        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
	        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
	        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
	        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
	        		+",cheque_no,cheque_date,reg_date from tblEmployeePayrollDetails where unique_payroll_id=? order by reg_date desc";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, payrollId);
	        rs = prepStmt.executeQuery();
	        employeePayrollList = new ArrayList();
	        while(rs.next()){
	        			String unique_payroll_id = rs.getString(1);
	        			String emp_unique_id = rs.getString(2);
	        			String emp_id = rs.getString(3);
	        			String emp_ssn_no = rs.getString(4);
	        			String emp_name = rs.getString(5);
	        			String tax_year = rs.getString(6);
	        			String tax_country = rs.getString(7);
	        			String tax_state = rs.getString(8);
	        			String fed_marital_status = rs.getString(9);
	        			String fed_nof_allowance = rs.getString(10);
	        			String state_wh_info = rs.getString(11);
	        			String tax_payperiod = rs.getString(12);
	        			String tax_payduration_from = rs.getString(13);
	        			String tax_payduration_to = rs.getString(14);
	        			String tax_payrate_type = rs.getString(15);
	        			String if_overtime = rs.getString(16);
	        			String gen_payrate = rs.getString(17);
	        			String gen_payrate_type = rs.getString(18);
	        			String gen_total_hours_worked = rs.getString(19);
	        			String gen_pay_ytd = rs.getString(20);
	        			String gen_pay_cp = rs.getString(21);
	        			String ot_row_count = rs.getString(22);
	        			String ot_payrate = rs.getString(23);
	        			String ot_payrate_type = rs.getString(24);
	        			String ot_total_hours_worked = rs.getString(25);
	        			String ot_pay_ytd = rs.getString(26);
	        			String ot_pay_cp = rs.getString(27);
	        			String fed_pay_ytd = rs.getString(28);
	        			String fed_pay_cp = rs.getString(29);
	        			String ss_payrate = rs.getString(30);
	        			String ss_annual_max = rs.getString(31);
	        			String ss_pay_ytd = rs.getString(32);
	        			String ss_pay_cp = rs.getString(33);
	        			String mc_payrate = rs.getString(34);
	        			String mc_pay_ytd = rs.getString(35);
	        			String mc_pay_cp = rs.getString(36);
	        			String state_pay_ytd = rs.getString(37);
	        			String state_pay_cp = rs.getString(38);
	        			String after_tax_income_pay = rs.getString(39);
	        			String after_tax_income_ytd = rs.getString(40);
	        			String after_tax_income_cp = rs.getString(41);
	        			String after_tax_deduction_pay = rs.getString(42);
	    	        	String after_tax_deduction_ytd = rs.getString(43);
	    	        	String after_tax_deduction_cp = rs.getString(44);
	    	        	String total_netpay = rs.getString(45);
	    	        	String cheque_no = rs.getString(46);
	    	        	String cheque_date = rs.getString(47);
	    		
	            String[] strList = {unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name
	            		,tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info
	            		,tax_payperiod,tax_payduration_from,tax_payduration_to,tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type
	            		,gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type
	            		,ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate
	            		,ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd
	            		,state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp
	            		,after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay,
	            		cheque_no,cheque_date};
	            employeePayrollList.add(strList);
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        Debug.print("SQL Exception in GeneralDBAction.getAllEmployeePayrollDetails():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        Debug.print("General Exception  in GeneralDBAction.getAllEmployeePayrollDetails():" + e.getMessage());
	    }
	    return employeePayrollList;
	    
	}

	
	public boolean deleteEmployeePayrollDetailsByUniqueId(String payrollId) {
	    Debug.print("GeneralDBAction.deleteEmployeePayrollDetailsByUniqueId()" +payrollId);
	    boolean result = false;
	    makeConnection();
	    try{
	    	
	    	
	    	  String deleteStatement = "delete from tblEmployeePayrollDetails where unique_payroll_id = ? ";
	          Debug.print("GeneralDBAction.deleteEmployeePayrollDetailsByUniqueId():" + "\n" + deleteStatement + ":" +  payrollId);
	          PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
	          prepStmt.setString(1, payrollId);
	          prepStmt.executeUpdate();
	          prepStmt.close();
	          releaseConnection();
	          result = true;
	    }
	    catch(SQLException sql){
	       releaseConnection();
	       Debug.print("SQL Exception in GeneralDBAction deleteEmployeePayrollDetailsByUniqueId:" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        Debug.print("General Exception  in  GeneralDBAction deleteEmployeePayrollDetailsByUniqueId:" + e.getMessage());
	    }        
	    return result;
	}
	
	public ArrayList getAllYTDAmountByUniqueId(String uniqueId) {
		Debug.print("GeneralDBAction.getAllYTDAmountByUniqueId():");
	    ArrayList employeeYTDAmountList = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
		try {
	        String selectStatement = "SELECT unique_payroll_id,gen_pay_cp,ot_pay_cp,fed_pay_cp,ss_pay_cp"
	        		+",mc_pay_cp,state_pay_cp,after_tax_income_cp,after_tax_deduction_cp,reg_date "
	        		+"FROM tblEmployeePayrollDetails where emp_unique_id=? and tax_year=?";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, uniqueId);
	        prepStmt.setString(2, String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
	        rs = prepStmt.executeQuery();
	        employeeYTDAmountList = new ArrayList();
	        while(rs.next()){
	        			String unique_payroll_id = rs.getString(1);
	        			String gen_pay_cp = rs.getString(2);
	        			String ot_pay_cp = rs.getString(3);
	        			String fed_pay_cp = rs.getString(4);
	        			String ss_pay_cp = rs.getString(5);
	        			String mc_pay_cp = rs.getString(6);
	        			String state_pay_cp = rs.getString(7);
	        			String after_tax_income_cp = rs.getString(8);
	        			String after_tax_deduction_cp = rs.getString(9);
	        			String reg_date = rs.getString(10);
	    		
	            String[] strList = {unique_payroll_id,gen_pay_cp,ot_pay_cp,fed_pay_cp,ss_pay_cp,mc_pay_cp,
	            		state_pay_cp,after_tax_income_cp,after_tax_deduction_cp,reg_date};
	            employeeYTDAmountList.add(strList);
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        Debug.print("SQL Exception in GeneralDBAction.getAllYTDAmountByUniqueId():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        Debug.print("General Exception  in GeneralDBAction.getAllYTDAmountByUniqueId():" + e.getMessage());
	    }
	    return employeeYTDAmountList;
	    
	}

	public boolean insertDOSStageOneDetails(String empUniqueId,
			String createDate, String acknowledgementDate, String systemDate,
			String approveStatus, String visaformFile,String additionalfileFile) {
		
		Debug.print("Inside GeneralDBAction.insertDOSStageOneDetails()::::::::::::::::::::");
				
		String stage1ParmData = null;
		stage1ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
		
		String stage1UploadData = "-sep-";
			stage1UploadData = stage1UploadData + visaformFile + "-sep-" + additionalfileFile + "-sep-";
		stage1UploadData = stage1UploadData.substring(0, stage1UploadData.length() - 5);
		
		boolean insertStatus = false;
		
		PreparedStatement prepStmt = null;
	    ResultSet rs = null;
		try{

		    makeConnection(); 
		    String insertStmt = null;
		    if(checkEmployeeDOSExist(empUniqueId)){
		    	insertStmt=  "update tblDepartmentOfState set stage1=?,stage1_status=? where"
		    			+" emp_unique_id=?";
		    	 prepStmt = con.prepareStatement(insertStmt);
			     prepStmt.setString(1, stage1ParmData + stage1UploadData);
			     prepStmt.setBoolean(2, true);
			     prepStmt.setString(3, empUniqueId);
		    }else{
		    	insertStmt=  "INSERT INTO tblDepartmentOfState(emp_unique_id,stage1,stage1_status)VALUES"
		    		+"(?,?,?)";
		    	prepStmt = con.prepareStatement(insertStmt);
		        prepStmt.setString(1, empUniqueId);
		        prepStmt.setString(2, stage1ParmData + stage1UploadData);
		        prepStmt.setBoolean(3, true);
		    }
		      int cnt= prepStmt.executeUpdate();
		      if(cnt>0){
		          System.out.println("Employee Department of State Stage1 details have been successfully inserted into database----------------->");
		          insertStatus = true;
		      }
		       prepStmt.close();

		    } 
		    catch (SQLException sqe) {
		      releaseConnection();
		      Debug.print("SQL Exception in GeneralDBAction.insertDOSStageOneDetails():" + sqe.getMessage()); 
		     sqe.printStackTrace();
		    }catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.insertDOSStageOneDetails():" + e.getMessage());
		    }finally {
		      releaseConnection();
		    }
		return insertStatus;
	}

	public ArrayList getDOSStageOneDetailsByUniqueEmpId(String empUniqueId) {
		Debug.print("GeneralDBAction.getDOSStageOneDetailsByUniqueEmpId():");
	    ArrayList DOSStageOneData = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
		try {
	        String selectStatement = "SELECT unique_DOS_id,emp_unique_id,stage1,stage1_status,stage2_status"
	        		+",stage3_status,stage4_status,stage5_status,stage6_status "
	        		+"FROM tblDepartmentOfState where emp_unique_id=?";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, empUniqueId);
	        rs = prepStmt.executeQuery();
	        DOSStageOneData = new ArrayList();
	        while(rs.next()){
	        			String unique_DOS_id = rs.getString(1);
	        			String emp_unique_id = rs.getString(2);
	        			String stage1Data = rs.getString(3);
	        			String stage1_status = String.valueOf(rs.getBoolean(4));
	        			String stage2_status = String.valueOf(rs.getBoolean(5));
	        			String stage3_status = String.valueOf(rs.getBoolean(6));
	        			String stage4_status = String.valueOf(rs.getBoolean(7));
	        			String stage5_status = String.valueOf(rs.getBoolean(8));
	        			String stage6_status = String.valueOf(rs.getBoolean(9));
	        			
	            String[] strList = {unique_DOS_id,emp_unique_id,stage1Data,stage1_status,stage2_status,
	            		stage3_status,stage4_status,stage5_status,stage6_status};
	            DOSStageOneData.add(strList);
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        Debug.print("SQL Exception in GeneralDBAction.getDOSStageOneDetailsByUniqueEmpId():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        Debug.print("General Exception  in GeneralDBAction.getDOSStageOneDetailsByUniqueEmpId():" + e.getMessage());
	    }
	    return DOSStageOneData;
	}
	
	public boolean checkEmployeeDOSExist(String empUniqueId) throws SQLException{
		Debug.print("GeneralDBAction checkEmployeeDOSExist");
	     boolean result = false;
	     //makeConnection();
		try {
	         String selectStatement = "SELECT emp_unique_id from tblDepartmentOfState WHERE emp_unique_id = ?" ;
	         PreparedStatement prepStmt = con.prepareStatement(selectStatement);
	         prepStmt.setString(1,empUniqueId);
	         ResultSet rs = prepStmt.executeQuery();
	         if (rs.next()){
	             result = true;
	         }
	         rs.close();
	         prepStmt.close();
	         //releaseConnection();
	     } 
	     catch (SQLException e) {
	         releaseConnection();
	         Debug.print("GeneralDBAction:: Could not find any from checkEmployeeDOSExist" + e.getMessage());
	     }
	     catch(Exception e){
	         releaseConnection();
	         Debug.print("General Exception  in GeneralDBAction checkEmployeeDOSExist:" + e.getMessage());
	     }
	     Debug.print("GeneralDBAction checkEmployeeDOSExist():" + result);
	     return result;
	}
	public boolean insertDOSStageTwoDetails(String empUniqueId,
			String createDate, String acknowledgementDate, String systemDate,
			String approveStatus, String formI_94File,String additionalfileFile) {
		
		Debug.print("Inside GeneralDBAction.insertDOSStageTwoDetails()::::::::::::::::::::");
				
		String stage2ParmData = null;
		stage2ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
		
		String stage2UploadData = "-sep-";
			stage2UploadData = stage2UploadData + formI_94File + "-sep-" + additionalfileFile + "-sep-";
		stage2UploadData = stage2UploadData.substring(0, stage2UploadData.length() - 5);
		
		boolean insertStatus = false;
		PreparedStatement prepStmt = null;
	    ResultSet rs = null;
		try{

		    makeConnection(); 
		    String insertStmt = null;
		    if(checkEmployeeDOSExist(empUniqueId)){
		    	insertStmt=  "update tblDepartmentOfState set stage2=?,stage2_status=? where"
		    			+" emp_unique_id=?";
		    	 prepStmt = con.prepareStatement(insertStmt);
			     prepStmt.setString(1, stage2ParmData + stage2UploadData);
			     prepStmt.setBoolean(2, true);
			     prepStmt.setString(3, empUniqueId);
		    }else{
		    	throw new Exception("Completed the Previous Stages");
		    }

		      int cnt= prepStmt.executeUpdate();
		      if(cnt>0){
		          System.out.println("Employee Department of State Stage2 details have been successfully inserted into database----------------->");
		          insertStatus = true;
		      }
		       prepStmt.close();
		       //releaseConnection();

		    } 
		    catch (SQLException sqe) {
		      releaseConnection();
		      Debug.print("SQL Exception in GeneralDBAction.insertDOSStageTwoDetails():" + sqe.getMessage()); 
		     sqe.printStackTrace();
		    }catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.insertDOSStageTwoDetails():" + e.getMessage());
		    }finally {
		      releaseConnection();
		    }
		return insertStatus;
	}

	public ArrayList getDOSStageTwoDetailsByUniqueEmpId(String empUniqueId) {
		Debug.print("GeneralDBAction.getDOSStageTwoDetailsByUniqueEmpId():");
	    ArrayList DOSStageTwoData = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
		try {
	        String selectStatement = "SELECT unique_DOS_id,emp_unique_id,stage2,stage1_status,stage2_status"
	        		+",stage3_status,stage4_status,stage5_status,stage6_status "
	        		+"FROM tblDepartmentOfState where emp_unique_id=?";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, empUniqueId);
	        rs = prepStmt.executeQuery();
	        DOSStageTwoData = new ArrayList();
	        while(rs.next()){
	        			String unique_DOS_id = rs.getString(1);
	        			String emp_unique_id = rs.getString(2);
	        			String stage2Data = rs.getString(3);
	        			String stage1_status = String.valueOf(rs.getBoolean(4));
	        			String stage2_status = String.valueOf(rs.getBoolean(5));
	        			String stage3_status = String.valueOf(rs.getBoolean(6));
	        			String stage4_status = String.valueOf(rs.getBoolean(7));
	        			String stage5_status = String.valueOf(rs.getBoolean(8));
	        			String stage6_status = String.valueOf(rs.getBoolean(9));
	        			
	            String[] strList = {unique_DOS_id,emp_unique_id,stage2Data,stage1_status,stage2_status,
	            		stage3_status,stage4_status,stage5_status,stage6_status};
	            DOSStageTwoData.add(strList);
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        sql.printStackTrace();
	        Debug.print("SQL Exception in GeneralDBAction.getDOSStageTwoDetailsByUniqueEmpId():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        e.printStackTrace();
	        Debug.print("General Exception  in GeneralDBAction.getDOSStageTwoDetailsByUniqueEmpId():" + e.getMessage());
	    }
	    return DOSStageTwoData;
	}
	
	public boolean insertDOSStageThreeDetails(String empUniqueId, String startDate, String endDate, 
			String createDate, String acknowledgementDate, String systemDate, String approveStatus) {
		
		Debug.print("Inside GeneralDBAction.insertDOSStageThreeDetails()::::::::::::::::::::");
				
		String stage2ParmData = null;
		stage2ParmData = startDate + "-sep-" + endDate + "-sep-" + createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
		
		boolean insertStatus = false;
		PreparedStatement prepStmt = null;
	    ResultSet rs = null;
		try{

		    makeConnection(); 
		    String insertStmt = null;
		    if(checkEmployeeDOSExist(empUniqueId)){
		    	insertStmt=  "update tblDepartmentOfState set stage3=?,stage3_status=? where"
		    			+" emp_unique_id=?";
		    	 prepStmt = con.prepareStatement(insertStmt);
			     prepStmt.setString(1, stage2ParmData);
			     prepStmt.setBoolean(2, true);
			     prepStmt.setString(3, empUniqueId);
		    }else{
		    	throw new Exception("Completed the Previous Stages");
		    }

		      int cnt= prepStmt.executeUpdate();
		      if(cnt>0){
		          System.out.println("Employee Department of State Stage3 details have been successfully inserted into database----------------->");
		          insertStatus = true;
		      }
		       prepStmt.close();
		       //releaseConnection();

		    } 
		    catch (SQLException sqe) {
		      releaseConnection();
		      Debug.print("SQL Exception in GeneralDBAction.insertDOSStageThreeDetails():" + sqe.getMessage()); 
		     sqe.printStackTrace();
		    }catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.insertDOSStageThreeDetails():" + e.getMessage());
		    }finally {
		      releaseConnection();
		    }
		return insertStatus;
	}

	public ArrayList getDOSStageThreeDetailsByUniqueEmpId(String empUniqueId) {
		Debug.print("GeneralDBAction.getDOSStageThreeDetailsByUniqueEmpId():");
	    ArrayList DOSStageThreeData = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
		try {
	        String selectStatement = "SELECT unique_DOS_id,emp_unique_id,stage3,stage1_status,stage2_status"
	        		+",stage3_status,stage4_status,stage5_status,stage6_status "
	        		+"FROM tblDepartmentOfState where emp_unique_id=?";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, empUniqueId);
	        rs = prepStmt.executeQuery();
	        DOSStageThreeData = new ArrayList();
	        while(rs.next()){
	        			String unique_DOS_id = rs.getString(1);
	        			String emp_unique_id = rs.getString(2);
	        			String stage3Data = rs.getString(3);
	        			String stage1_status = String.valueOf(rs.getBoolean(4));
	        			String stage2_status = String.valueOf(rs.getBoolean(5));
	        			String stage3_status = String.valueOf(rs.getBoolean(6));
	        			String stage4_status = String.valueOf(rs.getBoolean(7));
	        			String stage5_status = String.valueOf(rs.getBoolean(8));
	        			String stage6_status = String.valueOf(rs.getBoolean(9));
	        			
	            String[] strList = {unique_DOS_id,emp_unique_id,stage3Data,stage1_status,stage2_status,
	            		stage3_status,stage4_status,stage5_status,stage6_status};
	            DOSStageThreeData.add(strList);
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        sql.printStackTrace();
	        Debug.print("SQL Exception in GeneralDBAction.getDOSStageTwoDetailsByUniqueEmpId():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        e.printStackTrace();
	        Debug.print("General Exception  in GeneralDBAction.getDOSStageTwoDetailsByUniqueEmpId():" + e.getMessage());
	    }
	    return DOSStageThreeData;
	}

	public boolean insertDOSStageFourDetails(String empUniqueId,
			String createDate, String acknowledgementDate, String systemDate,
			String approveStatus, String newI_9formFile) {
		Debug.print("Inside GeneralDBAction.insertDOSStageFourDetails()::::::::::::::::::::");
		
		String stage4ParmData = null;
		stage4ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
		
		String stage4UploadData = "-sep-";
			stage4UploadData = stage4UploadData + newI_9formFile + "-sep-";
		stage4UploadData = stage4UploadData.substring(0, stage4UploadData.length() - 5);
		
		boolean insertStatus = false;
		PreparedStatement prepStmt = null;
	    ResultSet rs = null;
		try{

		    makeConnection(); 
		    String insertStmt = null;
		    if(checkEmployeeDOSExist(empUniqueId)){
		    	insertStmt=  "update tblDepartmentOfState set stage4=?,stage4_status=? where"
		    			+" emp_unique_id=?";
		    	 prepStmt = con.prepareStatement(insertStmt);
			     prepStmt.setString(1, stage4ParmData + stage4UploadData);
			     prepStmt.setBoolean(2, true);
			     prepStmt.setString(3, empUniqueId);
		    }else{
		    	throw new Exception("Completed the Previous Stages");
		    }

		      int cnt= prepStmt.executeUpdate();
		      if(cnt>0){
		          System.out.println("Employee Department of State Stage4 details have been successfully inserted into database----------------->");
		          insertStatus = true;
		      }
		       prepStmt.close();
		       //releaseConnection();

		    } 
		    catch (SQLException sqe) {
		      releaseConnection();
		      Debug.print("SQL Exception in GeneralDBAction.insertDOSStageFourDetails():" + sqe.getMessage()); 
		     sqe.printStackTrace();
		    }catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.insertDOSStageFourDetails():" + e.getMessage());
		    }finally {
		      releaseConnection();
		    }
		return insertStatus;
	}

	public ArrayList getDOSStageFourDetailsByUniqueEmpId(String empUniqueId) {
		Debug.print("GeneralDBAction.getDOSStageFourDetailsByUniqueEmpId():");
	    ArrayList DOSStageFourData = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
		try {
	        String selectStatement = "SELECT unique_DOS_id,emp_unique_id,stage4,stage1_status,stage2_status"
	        		+",stage3_status,stage4_status,stage5_status,stage6_status "
	        		+"FROM tblDepartmentOfState where emp_unique_id=?";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, empUniqueId);
	        rs = prepStmt.executeQuery();
	        DOSStageFourData = new ArrayList();
	        while(rs.next()){
	        			String unique_DOS_id = rs.getString(1);
	        			String emp_unique_id = rs.getString(2);
	        			String stage4Data = rs.getString(3);
	        			String stage1_status = String.valueOf(rs.getBoolean(4));
	        			String stage2_status = String.valueOf(rs.getBoolean(5));
	        			String stage3_status = String.valueOf(rs.getBoolean(6));
	        			String stage4_status = String.valueOf(rs.getBoolean(7));
	        			String stage5_status = String.valueOf(rs.getBoolean(8));
	        			String stage6_status = String.valueOf(rs.getBoolean(9));
	        			
	            String[] strList = {unique_DOS_id,emp_unique_id,stage4Data,stage1_status,stage2_status,
	            		stage3_status,stage4_status,stage5_status,stage6_status};
	            DOSStageFourData.add(strList);
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        sql.printStackTrace();
	        Debug.print("SQL Exception in GeneralDBAction.getDOSStageFourDetailsByUniqueEmpId():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        e.printStackTrace();
	        Debug.print("General Exception  in GeneralDBAction.getDOSStageFourDetailsByUniqueEmpId():" + e.getMessage());
	    }
	    return DOSStageFourData;
	}
	
	public boolean insertDOSStageFiveDetails(String empUniqueId,
			String createDate, String acknowledgementDate, String systemDate,
			String approveStatus, String FileAR_1File,String additional_address_docsFile) {
		Debug.print("Inside GeneralDBAction.insertDOSStageFiveDetails()::::::::::::::::::::");
		
		String stage5ParmData = null;
		stage5ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
		
		String stage5UploadData = "-sep-";
			stage5UploadData = stage5UploadData + FileAR_1File + "-sep-" + additional_address_docsFile + "-sep-";
		stage5UploadData = stage5UploadData.substring(0, stage5UploadData.length() - 5);
		
		boolean insertStatus = false;
		PreparedStatement prepStmt = null;
	    ResultSet rs = null;
		try{

		    makeConnection(); 
		    String insertStmt = null;
		    if(checkEmployeeDOSExist(empUniqueId)){
		    	insertStmt=  "update tblDepartmentOfState set stage5=?,stage5_status=? where"
		    			+" emp_unique_id=?";
		    	 prepStmt = con.prepareStatement(insertStmt);
			     prepStmt.setString(1, stage5ParmData + stage5UploadData);
			     prepStmt.setBoolean(2, true);
			     prepStmt.setString(3, empUniqueId);
		    }else{
		    	throw new Exception("Completed the Previous Stages");
		    }

		      int cnt= prepStmt.executeUpdate();
		      if(cnt>0){
		          System.out.println("Employee Department of State Stage5 details have been successfully inserted into database----------------->");
		          insertStatus = true;
		      }
		       prepStmt.close();
		       //releaseConnection();

		    } 
		    catch (SQLException sqe) {
		      releaseConnection();
		      Debug.print("SQL Exception in GeneralDBAction.insertDOSStageFiveDetails():" + sqe.getMessage()); 
		     sqe.printStackTrace();
		    }catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.insertDOSStageFiveDetails():" + e.getMessage());
		    }finally {
		      releaseConnection();
		    }
		return insertStatus;
	}

	public ArrayList getDOSStageFiveDetailsByUniqueEmpId(String empUniqueId) {
		Debug.print("GeneralDBAction.getDOSStageFiveDetailsByUniqueEmpId():");
	    ArrayList DOSStageFiveData = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
		try {
	        String selectStatement = "SELECT unique_DOS_id,emp_unique_id,stage5,stage1_status,stage2_status"
	        		+",stage3_status,stage4_status,stage5_status,stage6_status "
	        		+"FROM tblDepartmentOfState where emp_unique_id=?";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, empUniqueId);
	        rs = prepStmt.executeQuery();
	        DOSStageFiveData = new ArrayList();
	        while(rs.next()){
	        			String unique_DOS_id = rs.getString(1);
	        			String emp_unique_id = rs.getString(2);
	        			String stage5Data = rs.getString(3);
	        			String stage1_status = String.valueOf(rs.getBoolean(4));
	        			String stage2_status = String.valueOf(rs.getBoolean(5));
	        			String stage3_status = String.valueOf(rs.getBoolean(6));
	        			String stage4_status = String.valueOf(rs.getBoolean(7));
	        			String stage5_status = String.valueOf(rs.getBoolean(8));
	        			String stage6_status = String.valueOf(rs.getBoolean(9));
	        			
	            String[] strList = {unique_DOS_id,emp_unique_id,stage5Data,stage1_status,stage2_status,
	            		stage3_status,stage4_status,stage5_status,stage6_status};
	            DOSStageFiveData.add(strList);
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        sql.printStackTrace();
	        Debug.print("SQL Exception in GeneralDBAction.getDOSStageFiveDetailsByUniqueEmpId():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        e.printStackTrace();
	        Debug.print("General Exception  in GeneralDBAction.getDOSStageFiveDetailsByUniqueEmpId():" + e.getMessage());
	    }
	    return DOSStageFiveData;
	}
	
	public boolean insertDOSStageSixDetails(String empUniqueId, String startDate, String endDate, 
			String createDate, String acknowledgementDate, String systemDate, String approveStatus,
			String AdditionalDocsStage6File) {
		Debug.print("Inside GeneralDBAction.insertDOSStageSixDetails()::::::::::::::::::::");
		
		String stage6ParmData = null;
		stage6ParmData = startDate + "-sep-" + endDate + "-sep-" + createDate + "-sep-" + acknowledgementDate 
				+ "-sep-" + systemDate + "-sep-" + approveStatus;
		
		String stage6UploadData = "-sep-";
			stage6UploadData = stage6UploadData + AdditionalDocsStage6File + "-sep-";
		stage6UploadData = stage6UploadData.substring(0, stage6UploadData.length() - 5);
		
		boolean insertStatus = false;
		PreparedStatement prepStmt = null;
	    ResultSet rs = null;
		try{

		    makeConnection(); 
		    String insertStmt = null;
		    if(checkEmployeeDOSExist(empUniqueId)){
		    	insertStmt=  "update tblDepartmentOfState set stage6=?,stage6_status=? where"
		    			+" emp_unique_id=?";
		    	 prepStmt = con.prepareStatement(insertStmt);
			     prepStmt.setString(1, stage6ParmData + stage6UploadData);
			     prepStmt.setBoolean(2, true);
			     prepStmt.setString(3, empUniqueId);
		    }else{
		    	throw new Exception("Completed the Previous Stages");
		    }

		      int cnt= prepStmt.executeUpdate();
		      if(cnt>0){
		          System.out.println("Employee Department of State Stage6 details have been successfully inserted into database----------------->");
		          insertStatus = true;
		      }
		       prepStmt.close();
		       //releaseConnection();

		    } 
		    catch (SQLException sqe) {
		      releaseConnection();
		      Debug.print("SQL Exception in GeneralDBAction.insertDOSStageSixDetails():" + sqe.getMessage()); 
		     sqe.printStackTrace();
		    }catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.insertDOSStageSixDetails():" + e.getMessage());
		    }finally {
		      releaseConnection();
		    }
		return insertStatus;
	}

	public ArrayList getDOSStageSixDetailsByUniqueEmpId(String empUniqueId) {
		Debug.print("GeneralDBAction.getDOSStageSixDetailsByUniqueEmpId():");
	    ArrayList DOSStageSixData = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
		try {
	        String selectStatement = "SELECT unique_DOS_id,emp_unique_id,stage6,stage1_status,stage2_status"
	        		+",stage3_status,stage4_status,stage5_status,stage6_status "
	        		+"FROM tblDepartmentOfState where emp_unique_id=?";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, empUniqueId);
	        rs = prepStmt.executeQuery();
	        DOSStageSixData = new ArrayList();
	        while(rs.next()){
	        			String unique_DOS_id = rs.getString(1);
	        			String emp_unique_id = rs.getString(2);
	        			String stage6Data = rs.getString(3);
	        			String stage1_status = String.valueOf(rs.getBoolean(4));
	        			String stage2_status = String.valueOf(rs.getBoolean(5));
	        			String stage3_status = String.valueOf(rs.getBoolean(6));
	        			String stage4_status = String.valueOf(rs.getBoolean(7));
	        			String stage5_status = String.valueOf(rs.getBoolean(8));
	        			String stage6_status = String.valueOf(rs.getBoolean(9));
	        			
	            String[] strList = {unique_DOS_id,emp_unique_id,stage6Data,stage1_status,stage2_status,
	            		stage3_status,stage4_status,stage5_status,stage6_status};
	            DOSStageSixData.add(strList);
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        sql.printStackTrace();
	        Debug.print("SQL Exception in GeneralDBAction.getDOSStageSixDetailsByUniqueEmpId():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        e.printStackTrace();
	        Debug.print("General Exception  in GeneralDBAction.getDOSStageSixDetailsByUniqueEmpId():" + e.getMessage());
	    }
	    return DOSStageSixData;
	}
	
	public ArrayList getAllEmployeeDetailsByUserId(String userId) {
		Debug.print("GeneralDBAction.getAllEmployeeDetailsByUserId():");
	    ArrayList employeeList = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
		try {
	        String selectStatement = " SELECT unique_id,user_id,role_id,emp_id,emp_dberp_partyid,"+
		    		"emp_ssn_no,emp_salutation,emp_suffix,emp_fname,emp_mname,emp_lname,emp_gender,emp_marital_status,"+
		    		"emp_dob,emp_emailid,emp_mobileno,emp_contactno,emp_passportno,emp_address1,"+
		    		"emp_address1c,emp_address2,emp_address2c,emp_city,emp_cityc,emp_state,emp_statec,"+
		    		"emp_country,emp_countryc,emp_postalcode,emp_postalcodec,emp_company,emp_company_category,"+
		    		"emp_jobtitle,emp_department,emp_joblocation,emp_paygroup,emp_payrate_perhour,emp_payperiod,"+
		    		"emp_federaltax_whform_loc,emp_statetax_whform_loc,emp_joining_date,active_status from"+
		    		" tblEmployeeMasterDetails where user_id=?";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, userId);
	        rs = prepStmt.executeQuery();
	        employeeList = new ArrayList();
	        while(rs.next()){
	        			String empuniqueId = rs.getString(1);
	        			String empUserId = rs.getString(2);
	        			String empRoleId = rs.getString(3);
	        			String empId = rs.getString(4);
	        			String empdberpPartyid = rs.getString(5);
	        			String empSSNNo = rs.getString(6);
	        			String empSalutation = rs.getString(7);
	        			String empSuffix = rs.getString(8);
	        			String empFname = rs.getString(9);
	        			String empMname = rs.getString(10);
	        			String empLname = rs.getString(11);
	        			String empGender = rs.getString(12);
	        			String empMaritalStatus = rs.getString(13);
	        			String empDOB = rs.getString(14);
	        			String empEmailId = rs.getString(15);
	        			String empMobileNo = rs.getString(16);
	        			String empContactNo = rs.getString(17);
	        			String empPassportNo = rs.getString(18);
	        			String empAddress1 = rs.getString(19);
	        			String empAddress1c = rs.getString(20);
	        			String empAddress2 = rs.getString(21);
	        			String empAddress2c = rs.getString(22);
	        			String empCity = rs.getString(23);
	        			String empCityc = rs.getString(24);
	        			String empState = rs.getString(25);
	        			String empStatec = rs.getString(26);
	        			String empCountry = rs.getString(27);
	        			String empCountryc = rs.getString(28);
	        			String empPostalCode = rs.getString(29);
	        			String empPostalCodec = rs.getString(30);
	        			String empCompany = rs.getString(31);
	        			String empCompanyCategory = rs.getString(32);
	        			String empJobTitle = rs.getString(33);
	        			String empDepartment = rs.getString(34);
	        			String empJobLocation = rs.getString(35);
	        			String empPayGroup = rs.getString(36);
	        			String empPayratePerhour = Double.toString(rs.getDouble(37));
	        			String empPayPeriod = rs.getString(38);
	        			String empFederalTaxWHFormLocation = rs.getString(39);
	        			String empStateTaxWHFormLocation = rs.getString(40);
	        			String empJoiningDate = rs.getString(41);
	    	        	String empActiveStatus = Boolean.toString(rs.getBoolean(42));
	    		
	            String[] strList = {empuniqueId,empUserId,empRoleId,empId,empdberpPartyid,empSSNNo,
	            		empSalutation,empSuffix,empFname,empMname,empLname,empGender,empMaritalStatus,empDOB,
	            		empEmailId,empMobileNo,empContactNo,empPassportNo,empAddress1,empAddress1c,empAddress2,
	            		empAddress2c,empCity,empCityc,empState,empStatec,empCountry,empCountryc,empPostalCode,
	            		empPostalCodec,empCompany,empCompanyCategory,empJobTitle,empDepartment,empJobLocation,
	            		empPayGroup,empPayratePerhour,empPayPeriod,empFederalTaxWHFormLocation,empStateTaxWHFormLocation,
	            		empJoiningDate,empActiveStatus};
	            employeeList.add(strList);
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        Debug.print("SQL Exception in GeneralDBAction.getAllEmployeeDetailsByUserId():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        Debug.print("General Exception  in GeneralDBAction.getAllEmployeeDetailsByUserId():" + e.getMessage());
	    }
	    return employeeList;
	    
	}
	
	public boolean insertFederalStateFormDetails(String empUniqueId,
			String empSSNNo, String taxYear, String country,
			String state, String federalWHFile, String stateWHFile, String otherFile) {
		
		Debug.print("Inside GeneralDBAction.insertFederalStateFormDetails()::::::::::::::::::::");
		
		boolean insertStatus = false;
		
		PreparedStatement prepStmt = null;
	    ResultSet rs = null;
		try{

		    makeConnection(); 
		    String insertStmt = null;
		    if(checkEmployeeFedStateFormExist(empUniqueId)){
		    	insertStmt=  "UPDATE tblFederalStateFormDetails SET emp_unique_id =?,emp_ssn_no =?"
		    			+",emp_tax_year =?,emp_country =?,emp_state =?,emp_federalform_path =?,emp_stateform_path =?"
		    			+",emp_additionalform_path =?,upload_status =? WHERE emp_unique_id=?";
		    	prepStmt = con.prepareStatement(insertStmt);
		        prepStmt.setString(1, empUniqueId);
		        prepStmt.setString(2, empSSNNo);
			    prepStmt.setInt(3, Integer.parseInt(taxYear));
			    prepStmt.setString(4, country);
			    prepStmt.setString(5, state);
			    prepStmt.setString(6, federalWHFile);
			    prepStmt.setString(7, stateWHFile);
			    prepStmt.setString(8, otherFile);
			    prepStmt.setBoolean(9, true);
			    prepStmt.setString(10, empUniqueId);
		    }else{
			    insertStmt=  "INSERT INTO tblFederalStateFormDetails(emp_unique_id,emp_ssn_no,emp_tax_year"
		    			+",emp_country,emp_state,emp_federalform_path,emp_stateform_path,emp_additionalform_path"
		    			+",upload_status) VALUES(?,?,?,?,?,?,?,?,?)";
		    	 prepStmt = con.prepareStatement(insertStmt);
			     prepStmt.setString(1, empUniqueId);
			     prepStmt.setString(2, empSSNNo);
			     prepStmt.setInt(3, Integer.parseInt(taxYear));
			     prepStmt.setString(4, country);
			     prepStmt.setString(5, state);
			     prepStmt.setString(6, federalWHFile);
			     prepStmt.setString(7, stateWHFile);
			     prepStmt.setString(8, otherFile);
			     prepStmt.setBoolean(9, true);
		    }
		      int cnt= prepStmt.executeUpdate();
		      if(cnt>0){
		          System.out.println("Employee Federal and State form details have been successfully inserted into database----------------->");
		          insertStatus = true;
		      }
		       prepStmt.close();

		    } 
		    catch (SQLException sqe) {
		      releaseConnection();
		      Debug.print("SQL Exception in GeneralDBAction.insertFederalStateFormDetails():" + sqe.getMessage()); 
		     sqe.printStackTrace();
		    }catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.insertFederalStateFormDetails():" + e.getMessage());
		    }finally {
		      releaseConnection();
		    }
		return insertStatus;
	}
	
	public boolean checkEmployeeFedStateFormExist(String empUniqueId) throws SQLException{
		Debug.print("GeneralDBAction checkEmployeeFedStateFormExist");
	     boolean result = false;
	     //makeConnection();
		try {
	         String selectStatement = "SELECT emp_unique_id from tblFederalStateFormDetails WHERE emp_unique_id = ?" ;
	         PreparedStatement prepStmt = con.prepareStatement(selectStatement);
	         prepStmt.setString(1,empUniqueId);
	         ResultSet rs = prepStmt.executeQuery();
	         if (rs.next()){
	             result = true;
	         }
	         rs.close();
	         prepStmt.close();
	         //releaseConnection();
	     } 
	     catch (SQLException e) {
	         //releaseConnection();
	         Debug.print("GeneralDBAction:: Could not find any from checkEmployeeFedStateFormExist" + e.getMessage());
	     }
	     catch(Exception e){
	         //releaseConnection();
	         Debug.print("General Exception  in GeneralDBAction checkEmployeeFedStateFormExist:" + e.getMessage());
	     }
	     Debug.print("GeneralDBAction checkEmployeeFedStateFormExist():" + result);
	     return result;
	}
	
	public ArrayList getFederalStateFormDetails(String empUniqueId) {
		Debug.print("GeneralDBAction.getFederalStateFormDetails():");
	    ArrayList fedStateFormDetails = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
		try {
	        String selectStatement = "SELECT upload_id,emp_unique_id,emp_ssn_no,emp_tax_year,emp_country"
	        		+",emp_state,emp_federalform_path,emp_stateform_path,emp_additionalform_path,upload_date"
	        		+",upload_status FROM tblFederalStateFormDetails where emp_unique_id=?";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, empUniqueId);
	        rs = prepStmt.executeQuery();
	        fedStateFormDetails = new ArrayList();
	        while(rs.next()){
	        			String upload_id = rs.getString(1);
	        			String emp_unique_id = rs.getString(2);
	        			String emp_ssn_no = rs.getString(3);
	        			String emp_tax_year = String.valueOf(rs.getInt(4));
	        			String emp_country = rs.getString(5);
	        			String emp_state = rs.getString(6);
	        			String emp_federalform_path = rs.getString(7);
	        			String emp_stateform_path = rs.getString(8);
	        			String emp_additionalform_path = rs.getString(9);
	        			String upload_date = rs.getString(10);
	        			String upload_status = String.valueOf(rs.getBoolean(11));
	        			
	            String[] strList = {upload_id,emp_unique_id,emp_ssn_no,emp_tax_year,emp_country,
	            		emp_state,emp_federalform_path,emp_stateform_path,emp_additionalform_path,upload_date,
	            		upload_status};
	            fedStateFormDetails.add(strList);
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        sql.printStackTrace();
	        Debug.print("SQL Exception in GeneralDBAction.getFederalStateFormDetails():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        e.printStackTrace();
	        Debug.print("General Exception  in GeneralDBAction.getFederalStateFormDetails():" + e.getMessage());
	    }
	    return fedStateFormDetails;
	}
	
	public boolean checkEmpPayDurationExist(String empUniqueId, String tax_paydurationFrom, String tax_paydurationTo) throws SQLException{
		Debug.print("GeneralDBAction checkEmpPayDurationExist");
	     boolean result = false;
	     makeConnection();
		try {
	         String selectStatement = "SELECT tax_payduration_from,tax_paydurationTo from tblEmployeePayrollDetails WHERE emp_unique_id = ?"
	         		+" and tax_payduration_from = ? and tax_payduration_to = ?" ;
	         PreparedStatement prepStmt = con.prepareStatement(selectStatement);
	         prepStmt.setString(1,empUniqueId);
	         prepStmt.setString(2,tax_paydurationFrom);
	         prepStmt.setString(3,tax_paydurationTo);
	         ResultSet rs = prepStmt.executeQuery();
	         if (rs.next()){
	             result = true;
	         }
	         rs.close();
	         prepStmt.close();
	         releaseConnection();
	     } 
	     catch (SQLException e) {
	         releaseConnection();
	         Debug.print("GeneralDBAction:: Could not find any from checkEmpPayDurationExist" + e.getMessage());
	     }
	     catch(Exception e){
	         releaseConnection();
	         Debug.print("General Exception  in GeneralDBAction checkEmpPayDurationExist:" + e.getMessage());
	     }
	     Debug.print("GeneralDBAction checkEmpPayDurationExist():" + result);
	     return result;
	}

	public boolean updateFedStateEmpMastTbl(String empuniqueId, boolean insertStatus) {
Debug.print("Inside GeneralDBAction.insertFederalStateFormDetails()::::::::::::::::::::");
		
		boolean updateEmpMastTblStatus = false;
		
		PreparedStatement prepStmt = null;
	    ResultSet rs = null;
		try{

		    makeConnection(); 
		    String insertStmt = null;
		    	insertStmt=  "UPDATE tblEmployeeMasterDetails SET federalStateFormStatus =? WHERE unique_id=?";
		    	prepStmt = con.prepareStatement(insertStmt);
		        prepStmt.setBoolean(1, insertStatus);
			    prepStmt.setString(2, empuniqueId);
		    
		      int cnt= prepStmt.executeUpdate();
		      if(cnt>0){
		          System.out.println("Employee Federal and State form details have been successfully inserted into database----------------->");
		          updateEmpMastTblStatus = true;
		      }
		       prepStmt.close();

		    } 
		    catch (SQLException sqe) {
		      releaseConnection();
		      Debug.print("SQL Exception in GeneralDBAction.insertFederalStateFormDetails():" + sqe.getMessage()); 
		     sqe.printStackTrace();
		    }catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.insertFederalStateFormDetails():" + e.getMessage());
		    }finally {
		      releaseConnection();
		    }
		return updateEmpMastTblStatus;
	}
	
	//Method for searching employee
		public ArrayList searchAllEmployeePayrollDetails(String employeeId, String empName, String ssnNo, 
				String frmD, String toD, String payCycle) throws ParseException {
			
			Debug.print("GeneralDBAction.searchAllEmployeePayrollDetails():");
		    ArrayList employeePayrollList = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
		    
		    SimpleDateFormat sdfFromDate = null;
		    SimpleDateFormat sdfToDate = null;
		    
		    java.sql.Date sqlFromDate = null;
		    java.sql.Date sqlToDate = null;
		    if((frmD != null && toD != null) &&
					(frmD != "" && toD != "") &&
					(frmD != "null" && toD != "null") &&
					(!(frmD.equalsIgnoreCase("")) && !(toD.equalsIgnoreCase(""))) &&
					(!(frmD.equalsIgnoreCase("null")) && !(toD.equalsIgnoreCase("null")))) {
			   
			sdfFromDate= new SimpleDateFormat("MM-dd-yyyy");
			java.util.Date frmUtilDate = sdfFromDate.parse(frmD);
			sqlFromDate = new java.sql.Date(frmUtilDate.getTime());
			   
			sdfToDate= new SimpleDateFormat("MM-dd-yyyy");
			java.util.Date toUtilDate = sdfFromDate.parse(toD);
			sqlToDate = new java.sql.Date(toUtilDate.getTime());
			
		    }
					    
			String selectStatement = null;
			
			try {
				
				if((employeeId != null && payCycle != null && frmD != null && toD != null && empName != null && ssnNo != null) &&
				(employeeId != "" && payCycle != "" && frmD != "" && toD != "" && empName != "" && ssnNo != "") &&
				(employeeId != "null" && payCycle != "null" && frmD != "null" && toD != "null" && empName != "null" && ssnNo != "null") &&
				(!(employeeId.equalsIgnoreCase("")) && !(payCycle.equalsIgnoreCase("")) && !(frmD.equalsIgnoreCase(""))
				&& !(toD.equalsIgnoreCase("")) && !(empName.equalsIgnoreCase("")) && !(ssnNo.equalsIgnoreCase(""))) &&
				(!(employeeId.equalsIgnoreCase("null")) && !(payCycle.equalsIgnoreCase("null"))&& !(frmD.equalsIgnoreCase("null"))
				&& !(toD.equalsIgnoreCase("null")) && !(empName.equalsIgnoreCase("null")) && !(ssnNo.equalsIgnoreCase("null")) )) {
					System.out.println("in one if----> search with all parameters");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (emp_id=? and emp_name=? and emp_ssn_no=? and tax_payperiod=? and "
			        		+"(tax_payduration_from >= ? and tax_payduration_to <= ?)) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, employeeId);
			        prepStmt.setString(2, empName);
			        prepStmt.setString(3, ssnNo);
			        prepStmt.setString(4, payCycle);
			        prepStmt.setDate(5, sqlFromDate);
			        prepStmt.setDate(6, sqlToDate);
			        //prepStmt.setString(5, checkDate);
					
				}else if((employeeId != null && payCycle != null && empName != null && ssnNo != null) &&
						(employeeId != "" && payCycle != "" && empName != "" && ssnNo != "") &&
						(employeeId != "null" && payCycle != "null" && empName != "null" && ssnNo != "null") &&
						(!(employeeId.equalsIgnoreCase("")) && !(payCycle.equalsIgnoreCase("")) && !(empName.equalsIgnoreCase("")) && !(ssnNo.equalsIgnoreCase(""))) &&
						(!(employeeId.equalsIgnoreCase("null")) && !(payCycle.equalsIgnoreCase("null")) && !(empName.equalsIgnoreCase("null")) && !(ssnNo.equalsIgnoreCase("null")))) {
					System.out.println("in else");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (emp_id=? and emp_name=? and emp_ssn_no=? and tax_payperiod=?) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
					prepStmt.setString(1, employeeId);
			        prepStmt.setString(2, empName);
			        prepStmt.setString(3, ssnNo);
			        prepStmt.setString(4, payCycle);
			        				
				}else if((employeeId != null && frmD != null && toD != null && empName != null && ssnNo != null) &&
						(employeeId != "" && frmD != "" && toD != "" && empName != "" && ssnNo != "") &&
						(employeeId != "null" && frmD != "null" && toD != "null" && empName != "null" && ssnNo != "null") &&
						(!(employeeId.equalsIgnoreCase("")) && !(frmD.equalsIgnoreCase(""))
						&& !(toD.equalsIgnoreCase("")) && !(empName.equalsIgnoreCase("")) && !(ssnNo.equalsIgnoreCase(""))) &&
						(!(employeeId.equalsIgnoreCase("null")) && !(frmD.equalsIgnoreCase("null"))
						&& !(toD.equalsIgnoreCase("null")) && !(empName.equalsIgnoreCase("null")) && !(ssnNo.equalsIgnoreCase("null")))) {
		        
					System.out.println("in one if----> search with all parameters");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (emp_id=? and emp_name=? and emp_ssn_no=? and "
			        		+"(tax_payduration_from >= ? and tax_payduration_to <= ?)) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, employeeId);
			        prepStmt.setString(2, empName);
			        prepStmt.setString(3, ssnNo);
			        prepStmt.setDate(4, sqlFromDate);
			        prepStmt.setDate(5, sqlToDate);
			        //prepStmt.setString(5, checkDate);
					
				}else  if((employeeId != null && payCycle != null && frmD != null && toD != null && empName != null) &&
						(employeeId != "" && payCycle != "" && frmD != "" && toD != "" && empName != "") &&
						(employeeId != "null" && payCycle != "null" && frmD != "null" && toD != "null" && empName != "null") &&
						(!(employeeId.equalsIgnoreCase("")) && !(payCycle.equalsIgnoreCase("")) && !(frmD.equalsIgnoreCase(""))
						&& !(toD.equalsIgnoreCase("")) && !(empName.equalsIgnoreCase(""))) &&
						(!(employeeId.equalsIgnoreCase("null")) && !(payCycle.equalsIgnoreCase("null"))&& !(frmD.equalsIgnoreCase("null"))
						&& !(toD.equalsIgnoreCase("null")) && !(empName.equalsIgnoreCase("null")))) {
		        
					System.out.println("in one if----> search with all parameters");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (emp_id=? and emp_name=? and tax_payperiod=? and "
			        		+"(tax_payduration_from >= ? and tax_payduration_to <= ?)) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, employeeId);
			        prepStmt.setString(2, empName);
			        prepStmt.setString(3, payCycle);
			        prepStmt.setDate(4, sqlFromDate);
			        prepStmt.setDate(5, sqlToDate);
			        //prepStmt.setString(5, checkDate);
					
				}else  if((payCycle != null && frmD != null && toD != null && empName != null && ssnNo != null) &&
						(payCycle != "" && frmD != "" && toD != "" && empName != "" && ssnNo != "") &&
						(payCycle != "null" && frmD != "null" && toD != "null" && empName != "null" && ssnNo != "null") &&
						(!(payCycle.equalsIgnoreCase("")) && !(frmD.equalsIgnoreCase(""))
						&& !(toD.equalsIgnoreCase("")) && !(empName.equalsIgnoreCase("")) && !(ssnNo.equalsIgnoreCase(""))) &&
						(!(payCycle.equalsIgnoreCase("null"))&& !(frmD.equalsIgnoreCase("null"))
						&& !(toD.equalsIgnoreCase("null")) && !(empName.equalsIgnoreCase("null")) && !(ssnNo.equalsIgnoreCase("null")))) {
		        
					System.out.println("in one if----> search with all parameters");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (emp_name=? and emp_ssn_no=? and tax_payperiod=? and "
			        		+"(tax_payduration_from >= ? and tax_payduration_to <= ?)) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, empName);
			        prepStmt.setString(2, ssnNo);
			        prepStmt.setString(3, payCycle);
			        prepStmt.setDate(4, sqlFromDate);
			        prepStmt.setDate(5, sqlToDate);
			        //prepStmt.setString(5, checkDate);
					
				}else  if((employeeId != null && payCycle != null && frmD != null && toD != null && ssnNo != null) &&
						(employeeId != "" && payCycle != "" && frmD != "" && toD != "" && ssnNo != "") &&
						(employeeId != "null" && payCycle != "null" && frmD != "null" && toD != "null" && ssnNo != "null") &&
						(!(employeeId.equalsIgnoreCase("")) && !(payCycle.equalsIgnoreCase("")) && !(frmD.equalsIgnoreCase(""))
						&& !(toD.equalsIgnoreCase("")) && !(ssnNo.equalsIgnoreCase(""))) &&
						(!(employeeId.equalsIgnoreCase("null")) && !(payCycle.equalsIgnoreCase("null"))&& !(frmD.equalsIgnoreCase("null"))
						&& !(toD.equalsIgnoreCase("null")) && !(ssnNo.equalsIgnoreCase("null")))) {
		        
					System.out.println("in one if----> search with all parameters");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (emp_id=? and emp_ssn_no=? and tax_payperiod=? and "
			        		+"(tax_payduration_from >= ? and tax_payduration_to <= ?)) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, employeeId);
			        prepStmt.setString(2, ssnNo);
			        prepStmt.setString(3, payCycle);
			        prepStmt.setDate(4, sqlFromDate);
			        prepStmt.setDate(5, sqlToDate);
			        //prepStmt.setString(5, checkDate);
					
				}else  if((employeeId != null && payCycle != null && empName != null) &&
						(employeeId != "" && payCycle != "" && empName != "") &&
						(employeeId != "null" && payCycle != "null" && empName != "null") &&
						(!(employeeId.equalsIgnoreCase("")) && !(payCycle.equalsIgnoreCase("")) && !(empName.equalsIgnoreCase(""))) &&
						(!(employeeId.equalsIgnoreCase("null")) && !(payCycle.equalsIgnoreCase("null"))&& !(empName.equalsIgnoreCase("null")))) {
		        
					System.out.println("in one if----> search with all parameters");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (emp_id=? and emp_name=? and tax_payperiod=?) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, employeeId);
			        prepStmt.setString(2, empName);
			        prepStmt.setString(3, payCycle);
			        //prepStmt.setString(5, checkDate);
					
				}else  if((employeeId != null && empName != null && ssnNo != null) &&
						(employeeId != ""  && empName != "" && ssnNo != "") &&
						(employeeId != "null" && empName != "null" && ssnNo != "null") &&
						(!(employeeId.equalsIgnoreCase("")) && !(empName.equalsIgnoreCase("")) && !(ssnNo.equalsIgnoreCase(""))) &&
						(!(employeeId.equalsIgnoreCase("null")) && !(empName.equalsIgnoreCase("null")) && !(ssnNo.equalsIgnoreCase("null")))) {
		        
					System.out.println("in one if----> search with all parameters");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (emp_id=? and emp_name=? and emp_ssn_no=?) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, employeeId);
			        prepStmt.setString(2, empName);
			        prepStmt.setString(3, ssnNo);
			  
					
				}else  if((employeeId != null && payCycle != null && ssnNo != null) &&
						(employeeId != "" && payCycle != "" && ssnNo != "") &&
						(employeeId != "null" && payCycle != "null" && ssnNo != "null") &&
						(!(employeeId.equalsIgnoreCase("")) && !(payCycle.equalsIgnoreCase("")) && !(ssnNo.equalsIgnoreCase(""))) &&
						(!(employeeId.equalsIgnoreCase("null")) && !(payCycle.equalsIgnoreCase("null")) && !(ssnNo.equalsIgnoreCase("null")))) {
		        
					System.out.println("in one if----> search with all parameters");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (emp_id=? and emp_ssn_no=? and tax_payperiod=?) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, employeeId);
			        prepStmt.setString(2, ssnNo);
			        prepStmt.setString(3, payCycle);
					
				}else  if((employeeId != null && frmD != null && toD != null && empName != null) &&
						(employeeId != "" && frmD != "" && toD != "" && empName != "") &&
						(employeeId != "null" && frmD != "null" && toD != "null" && empName != "null") &&
						(!(employeeId.equalsIgnoreCase("")) && !(frmD.equalsIgnoreCase(""))
						&& !(toD.equalsIgnoreCase("")) && !(empName.equalsIgnoreCase(""))) &&
						(!(employeeId.equalsIgnoreCase("null")) && !(frmD.equalsIgnoreCase("null"))
						&& !(toD.equalsIgnoreCase("null")) && !(empName.equalsIgnoreCase("null")))) {
		        
					System.out.println("in one if----> search with all parameters");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (emp_id=? and emp_name=? and "
			        		+"(tax_payduration_from >= ? and tax_payduration_to <= ?)) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, employeeId);
			        prepStmt.setString(2, empName);
			        prepStmt.setDate(3, sqlFromDate);
			        prepStmt.setDate(4, sqlToDate);
			        
				}else  if((employeeId != null && frmD != null && toD != null && ssnNo != null) &&
						(employeeId != "" && frmD != "" && toD != "" && ssnNo != "") &&
						(employeeId != "null" && frmD != "null" && toD != "null" && ssnNo != "null") &&
						(!(employeeId.equalsIgnoreCase("")) && !(frmD.equalsIgnoreCase(""))
						&& !(toD.equalsIgnoreCase("")) && !(ssnNo.equalsIgnoreCase(""))) &&
						(!(employeeId.equalsIgnoreCase("null")) && !(frmD.equalsIgnoreCase("null"))
						&& !(toD.equalsIgnoreCase("null")) && !(ssnNo.equalsIgnoreCase("null")))) {
		        
					System.out.println("in one if----> search with all parameters");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (emp_id=? and emp_ssn_no=? and "
			        		+"(tax_payduration_from >= ? and tax_payduration_to <= ?)) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, employeeId);
			        prepStmt.setString(2, ssnNo);
			        prepStmt.setDate(3, sqlFromDate);
			        prepStmt.setDate(4, sqlToDate);
					
				}else  if((employeeId != null && empName != null) &&
						(employeeId != "" && empName != "") &&
						(employeeId != "null" && empName != "null") &&
						(!(employeeId.equalsIgnoreCase("")) && !(empName.equalsIgnoreCase(""))) &&
						(!(employeeId.equalsIgnoreCase("null")) && !(empName.equalsIgnoreCase("null")))) {
		        
					System.out.println("in one if----> search with all parameters");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (emp_id=? and emp_name=?) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, employeeId);
			        prepStmt.setString(2, empName);
					
				}else  if((employeeId != null && ssnNo != null) &&
						(employeeId != "" && ssnNo != "") &&
						(employeeId != "null" && ssnNo != "null") &&
						(!(employeeId.equalsIgnoreCase("")) && !(ssnNo.equalsIgnoreCase(""))) &&
						(!(employeeId.equalsIgnoreCase("null")) && !(ssnNo.equalsIgnoreCase("null")))) {
		        
					System.out.println("in one if----> search with all parameters");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (emp_id=? and emp_ssn_no=?) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, employeeId);
			        prepStmt.setString(2, ssnNo);
			        
					
				}else  if((employeeId != null && payCycle != null) &&
						(employeeId != "" && payCycle != "") &&
						(employeeId != "null" && payCycle != "null") &&
						(!(employeeId.equalsIgnoreCase("")) && !(payCycle.equalsIgnoreCase(""))) &&
						(!(employeeId.equalsIgnoreCase("null")) && !(payCycle.equalsIgnoreCase("null")))) {
		        
					System.out.println("in one if----> search with all parameters");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (emp_id=? and tax_payperiod=?) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, employeeId);
			        prepStmt.setString(2, payCycle);
					
				}else  if((employeeId != null && frmD != null && toD != null) &&
						(employeeId != "" && frmD != "" && toD != "") &&
						(employeeId != "null" && frmD != "null" && toD != "null" && empName != "null" && ssnNo != "null") &&
						(!(employeeId.equalsIgnoreCase("")) && !(frmD.equalsIgnoreCase(""))
						&& !(toD.equalsIgnoreCase(""))) &&
						(!(employeeId.equalsIgnoreCase("null")) && !(frmD.equalsIgnoreCase("null"))
						&& !(toD.equalsIgnoreCase("null")))) {
		        
					System.out.println("in one if----> search with all parameters");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (emp_id=? and (tax_payduration_from >= ? and tax_payduration_to <= ?))"
			        		+" order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, employeeId);
			        prepStmt.setDate(2, sqlFromDate);
			        prepStmt.setDate(3, sqlToDate);
					
				}else  if((employeeId != null) &&
						(employeeId != "") &&
						(employeeId != "null") &&
						(!(employeeId.equalsIgnoreCase(""))) &&
						(!(employeeId.equalsIgnoreCase("null")))) {
		        
					System.out.println("in one if----> search with all parameters");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (emp_id=?) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, employeeId);
			      
					
				}else if((empName != null) &&
						(empName != "") &&
						(empName != "null") &&
						(!(empName.equalsIgnoreCase(""))) &&
						(!(empName.equalsIgnoreCase("null")))) {
		        
					System.out.println("in one if----> search with all parameters");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (emp_name=?) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, empName);
			       
					
				}else if((ssnNo != null) &&
						(ssnNo != "") &&
						(ssnNo != "null") &&
						(!(ssnNo.equalsIgnoreCase(""))) &&
						(!(ssnNo.equalsIgnoreCase("null")))) {
		        
					System.out.println("in one if----> search with all parameters");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (emp_ssn_no=?) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, ssnNo);
			      
				}else if((payCycle != null) &&
						(payCycle != "") &&
						(payCycle != "null") &&
						(!(payCycle.equalsIgnoreCase(""))) &&
						(!(payCycle.equalsIgnoreCase("null")))) {
		        
					System.out.println("in one if----> search with all parameters");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (tax_payperiod=?) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, payCycle);
			       
			        
				}else if((frmD != null && toD != null) &&
						(frmD != "" && toD != "") &&
						(frmD != "null" && toD != "null") &&
						(!(frmD.equalsIgnoreCase("")) && !(toD.equalsIgnoreCase(""))) &&
						(!(frmD.equalsIgnoreCase("null")) && !(toD.equalsIgnoreCase("null")))) {
		        
					System.out.println("in one if----> search with all parameters");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where ((tax_payduration_from >= ? and tax_payduration_to <= ?)) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setDate(1, sqlFromDate);
			        prepStmt.setDate(2, sqlToDate);
			       
					
				}else{
		        
					System.out.println("in one if----> search with all parameters");
					selectStatement = " SELECT top 5 unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
					
				}
		        
		        rs = prepStmt.executeQuery();
		        employeePayrollList = new ArrayList();
		        
		        while(rs.next()){
		        			String unique_payroll_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String emp_id = rs.getString(3);
		        			String emp_ssn_no = rs.getString(4);
		        			String emp_name = rs.getString(5);
		        			String tax_year = rs.getString(6);
		        			String tax_country = rs.getString(7);
		        			String tax_state = rs.getString(8);
		        			String fed_marital_status = rs.getString(9);
		        			String fed_nof_allowance = rs.getString(10);
		        			String state_wh_info = rs.getString(11);
		        			String tax_payperiod = rs.getString(12);
		        			String tax_payduration_from = rs.getString(13);
		        			String tax_payduration_to = rs.getString(14);
		        			String tax_payrate_type = rs.getString(15);
		        			String if_overtime = rs.getString(16);
		        			String gen_payrate = rs.getString(17);
		        			String gen_payrate_type = rs.getString(18);
		        			String gen_total_hours_worked = rs.getString(19);
		        			String gen_pay_ytd = rs.getString(20);
		        			String gen_pay_cp = rs.getString(21);
		        			String ot_row_count = rs.getString(22);
		        			String ot_payrate = rs.getString(23);
		        			String ot_payrate_type = rs.getString(24);
		        			String ot_total_hours_worked = rs.getString(25);
		        			String ot_pay_ytd = rs.getString(26);
		        			String ot_pay_cp = rs.getString(27);
		        			String fed_pay_ytd = rs.getString(28);
		        			String fed_pay_cp = rs.getString(29);
		        			String ss_payrate = rs.getString(30);
		        			String ss_annual_max = rs.getString(31);
		        			String ss_pay_ytd = rs.getString(32);
		        			String ss_pay_cp = rs.getString(33);
		        			String mc_payrate = rs.getString(34);
		        			String mc_pay_ytd = rs.getString(35);
		        			String mc_pay_cp = rs.getString(36);
		        			String state_pay_ytd = rs.getString(37);
		        			String state_pay_cp = rs.getString(38);
		        			String after_tax_income_pay = rs.getString(39);
		        			String after_tax_income_ytd = rs.getString(40);
		        			String after_tax_income_cp = rs.getString(41);
		        			String after_tax_deduction_pay = rs.getString(42);
		    	        	String after_tax_deduction_ytd = rs.getString(43);
		    	        	String after_tax_deduction_cp = rs.getString(44);
		    	        	String total_netpay = rs.getString(45);
		    		
		    	        	
		    	    			
		    	    			String[] strList = {unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name
		    		            		,tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info
		    		            		,tax_payperiod,tax_payduration_from,tax_payduration_to,tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type
		    		            		,gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type
		    		            		,ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate
		    		            		,ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd
		    		            		,state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp
		    		            		,after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay};
		    		            employeePayrollList.add(strList);
		    		            
		    	    		
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.searchAllEmployeePayrollDetails():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.searchAllEmployeePayrollDetails():" + e.getMessage());
		    }
		    return employeePayrollList;
		    
		}
		
		public ArrayList searchEmployeePayrollDetails(String frmD, String toD, String payCycle, String empUniqueId) throws ParseException {
			
			Debug.print("GeneralDBAction.searchEmployeePayrollDetails():");
		    ArrayList employeePayrollList = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
		    
			
		    SimpleDateFormat sdfFromDate = null;
		    SimpleDateFormat sdfToDate = null;
		    
		    java.sql.Date sqlFromDate = null;
		    java.sql.Date sqlToDate = null;
		    if((empUniqueId != null && frmD != null && toD != null) &&
					(empUniqueId != "" && frmD != "" && toD != "") &&
					(empUniqueId != "null" && frmD != "null" && toD != "null") &&
					(!(empUniqueId.equalsIgnoreCase("")) && !(frmD.equalsIgnoreCase("")) && !(toD.equalsIgnoreCase(""))) &&
					(!(empUniqueId.equalsIgnoreCase("null")) && !(frmD.equalsIgnoreCase("null")) && !(toD.equalsIgnoreCase("null")))) {
			   
			sdfFromDate= new SimpleDateFormat("MM-dd-yyyy");
			java.util.Date frmUtilDate = sdfFromDate.parse(frmD);
			sqlFromDate = new java.sql.Date(frmUtilDate.getTime());
			   
			sdfToDate= new SimpleDateFormat("MM-dd-yyyy");
			java.util.Date toUtilDate = sdfFromDate.parse(toD);
			sqlToDate = new java.sql.Date(toUtilDate.getTime());
			
		    }
			   
			String selectStatement = null;
			
			try {
				
				if((empUniqueId != null && payCycle != null && frmD != null && toD != null) &&
						(empUniqueId != "" && payCycle != "" && frmD != "" && toD != "") &&
						(empUniqueId != "null" && payCycle != "null" && frmD != "null" && toD != "null") &&
						(!(empUniqueId.equalsIgnoreCase("")) && !(payCycle.equalsIgnoreCase("")) && !(frmD.equalsIgnoreCase(""))
								&& !(toD.equalsIgnoreCase(""))) &&(!(empUniqueId.equalsIgnoreCase("null")) && !(payCycle.equalsIgnoreCase("null"))
										&& !(frmD.equalsIgnoreCase("null")) && !(toD.equalsIgnoreCase("null")))) {
					System.out.println("in one if payCycle, frm and to pay period date is there");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (emp_unique_id=? and tax_payperiod=? and "
			        		+"(tax_payduration_from >= ? and tax_payduration_to <= ?)) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, payCycle);
			        prepStmt.setDate(3, sqlFromDate);
			        prepStmt.setDate(4, sqlToDate);
			        
			        System.out.println("empuniqueid-----"+empUniqueId);
			        System.out.println("payCycle-----"+payCycle);
			        System.out.println("sqlFromDate-----"+sqlFromDate);
			        System.out.println("sqlToDate-----"+sqlToDate);
			        
			        
					
				}else if((empUniqueId != null && payCycle != null) &&
						(empUniqueId != "" && payCycle != "") &&
						(empUniqueId != "null" && payCycle != "null") &&
						(!(empUniqueId.equalsIgnoreCase("")) && !(payCycle.equalsIgnoreCase(""))) &&
						(!(empUniqueId.equalsIgnoreCase("null")) && !(payCycle.equalsIgnoreCase("null")))) {
					System.out.println("in two if only payCycle is there");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (emp_unique_id=? and tax_payperiod=?) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, payCycle);
			        
					
				}else if((empUniqueId != null && frmD != null && toD != null) &&
						(empUniqueId != "" && frmD != "" && toD != "") &&
						(empUniqueId != "null" && frmD != "null" && toD != "null") &&
						(!(empUniqueId.equalsIgnoreCase("")) && !(frmD.equalsIgnoreCase("")) && !(toD.equalsIgnoreCase(""))) &&
						(!(empUniqueId.equalsIgnoreCase("null")) && !(frmD.equalsIgnoreCase("null")) && !(toD.equalsIgnoreCase("null")))) {
					System.out.println("in three if only frm and to pay period is there");
					selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where (emp_unique_id=? and"
			        		+" (tax_payduration_from >= ? and tax_payduration_to <= ?)) order by reg_date desc";
					
					prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setDate(2, sqlFromDate);
			        prepStmt.setDate(3, sqlToDate);
					
				}else{
					System.out.println("in else none of search ");
					selectStatement = " SELECT top 5 unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
			        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
			        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
			        		+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
			        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
			        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
			        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
			        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
			        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
			        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
			        		+",reg_date from tblEmployeePayrollDetails where emp_unique_id=? order by reg_date DESC";
					
					prepStmt = con.prepareStatement(selectStatement);
					prepStmt.setString(1, empUniqueId);
			        				
				}
		        
		        
		        
		        rs = prepStmt.executeQuery();
		        employeePayrollList = new ArrayList();
		        
		        while(rs.next()){
		        			String unique_payroll_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String emp_id = rs.getString(3);
		        			String emp_ssn_no = rs.getString(4);
		        			String emp_name = rs.getString(5);
		        			String tax_year = rs.getString(6);
		        			String tax_country = rs.getString(7);
		        			String tax_state = rs.getString(8);
		        			String fed_marital_status = rs.getString(9);
		        			String fed_nof_allowance = rs.getString(10);
		        			String state_wh_info = rs.getString(11);
		        			String tax_payperiod = rs.getString(12);
		        			String tax_payduration_from = rs.getString(13);
		        			String tax_payduration_to = rs.getString(14);
		        			String tax_payrate_type = rs.getString(15);
		        			String if_overtime = rs.getString(16);
		        			String gen_payrate = rs.getString(17);
		        			String gen_payrate_type = rs.getString(18);
		        			String gen_total_hours_worked = rs.getString(19);
		        			String gen_pay_ytd = rs.getString(20);
		        			String gen_pay_cp = rs.getString(21);
		        			String ot_row_count = rs.getString(22);
		        			String ot_payrate = rs.getString(23);
		        			String ot_payrate_type = rs.getString(24);
		        			String ot_total_hours_worked = rs.getString(25);
		        			String ot_pay_ytd = rs.getString(26);
		        			String ot_pay_cp = rs.getString(27);
		        			String fed_pay_ytd = rs.getString(28);
		        			String fed_pay_cp = rs.getString(29);
		        			String ss_payrate = rs.getString(30);
		        			String ss_annual_max = rs.getString(31);
		        			String ss_pay_ytd = rs.getString(32);
		        			String ss_pay_cp = rs.getString(33);
		        			String mc_payrate = rs.getString(34);
		        			String mc_pay_ytd = rs.getString(35);
		        			String mc_pay_cp = rs.getString(36);
		        			String state_pay_ytd = rs.getString(37);
		        			String state_pay_cp = rs.getString(38);
		        			String after_tax_income_pay = rs.getString(39);
		        			String after_tax_income_ytd = rs.getString(40);
		        			String after_tax_income_cp = rs.getString(41);
		        			String after_tax_deduction_pay = rs.getString(42);
		    	        	String after_tax_deduction_ytd = rs.getString(43);
		    	        	String after_tax_deduction_cp = rs.getString(44);
		    	        	String total_netpay = rs.getString(45);
		    		
		    	    			String[] strList = {unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name
		    		            		,tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info
		    		            		,tax_payperiod,tax_payduration_from,tax_payduration_to,tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type
		    		            		,gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type
		    		            		,ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate
		    		            		,ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd
		    		            		,state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp
		    		            		,after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay};
		    		            employeePayrollList.add(strList);
		    		            
		    	    		    	    		
		    	    	
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.searchEmployeePayrollDetails():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.searchEmployeePayrollDetails():" + e.getMessage());
		    }
		    return employeePayrollList;
		    
		}
		
		public boolean checkEmployeeUSCISExist(String empUniqueId) throws SQLException{
			Debug.print("GeneralDBAction checkEmployeeUSCISExist");
		     boolean result = false;
		     //makeConnection();
			try {
		         String selectStatement = "SELECT emp_unique_id from tblUSCIS WHERE emp_unique_id = ?" ;
		         PreparedStatement prepStmt = con.prepareStatement(selectStatement);
		         prepStmt.setString(1,empUniqueId);
		         ResultSet rs = prepStmt.executeQuery();
		         if (rs.next()){
		             result = true;
		         }
		         rs.close();
		         prepStmt.close();
		         //releaseConnection();
		     } 
		     catch (SQLException e) {
		         releaseConnection();
		         Debug.print("GeneralDBAction:: Could not find any from checkEmployeeUSCISExist" + e.getMessage());
		     }
		     catch(Exception e){
		         releaseConnection();
		         Debug.print("General Exception  in GeneralDBAction checkEmployeeUSCISExist:" + e.getMessage());
		     }
		     Debug.print("GeneralDBAction checkEmployeeUSCISExist():" + result);
		     return result;
		}
		
		public boolean insertUSCISStageOneDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String ContractFormFile, String EmploymentProofFile,
				String ClientLetterFile, String WorkOrderFile, String LabourClearanceFile) {
			
			Debug.print("Inside GeneralDBAction.insertUSCISStageOneDetails()::::::::::::::::::::");
					
			String stage1ParmData = null;
			stage1ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage1UploadData = "-sep-";
				stage1UploadData = stage1UploadData + ContractFormFile + "-sep-" + EmploymentProofFile + "-sep-" + ClientLetterFile + "-sep-" + WorkOrderFile + "-sep-" + LabourClearanceFile + "-sep-";
			stage1UploadData = stage1UploadData.substring(0, stage1UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeUSCISExist(empUniqueId)){
			    	insertStmt=  "update tblUSCIS set stage1=?,stage1_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage1ParmData + stage1UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblUSCIS(emp_unique_id,stage1,stage1_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage1ParmData + stage1UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee USCIS Stage1 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertUSCISStageOneDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertUSCISStageOneDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public ArrayList getUSCISStageOneDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getUSCISStageOneDetailsByUniqueEmpId():");
		    ArrayList DOSStageOneData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_USCIS_id,emp_unique_id,stage1,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status "
		        		+"FROM tblUSCIS where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOSStageOneData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOS_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage1Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
		        			String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			
		            String[] strList = {unique_DOS_id,emp_unique_id,stage1Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
		            DOSStageOneData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getUSCISStageOneDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getUSCISStageOneDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOSStageOneData;
		}
		
		public boolean insertUSCISStageTwoDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String FormI_129File) {
			
			Debug.print("Inside GeneralDBAction.insertUSCISStageTwoDetails()::::::::::::::::::::");
					
			String stage2ParmData = null;
			stage2ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage2UploadData = "-sep-";
				stage2UploadData = stage2UploadData + FormI_129File + "-sep-";
			stage2UploadData = stage2UploadData.substring(0, stage2UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeUSCISExist(empUniqueId)){
			    	insertStmt=  "update tblUSCIS set stage2=?,stage2_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage2ParmData + stage2UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblUSCIS(emp_unique_id,stage2,stage2_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage2ParmData + stage2UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee USCIS stage2 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertUSCISStageTwoDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertUSCISStageTwoDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public ArrayList getUSCISStageTwoDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getUSCISStageTwoDetailsByUniqueEmpId():");
		    ArrayList USCISStageTwoData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_USCIS_id,emp_unique_id,stage2,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status "
		        		+"FROM tblUSCIS where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        USCISStageTwoData = new ArrayList();
		        while(rs.next()){
		        			String unique_USCIS_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage2Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
		        			String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			
		            String[] strList = {unique_USCIS_id,emp_unique_id,stage2Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
		            USCISStageTwoData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        sql.printStackTrace();
		        Debug.print("SQL Exception in GeneralDBAction.getUSCISStageTwoDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        e.printStackTrace();
		        Debug.print("General Exception  in GeneralDBAction.getUSCISStageTwoDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return USCISStageTwoData;
		}

		public ArrayList getUSCISStageThreeDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getUSCISStageThreeDetailsByUniqueEmpId():");
		    ArrayList USCISStageThreeData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_USCIS_id,emp_unique_id,stage3,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status "
		        		+"FROM tblUSCIS where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        USCISStageThreeData = new ArrayList();
		        while(rs.next()){
		        			String unique_USCIS_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage3Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
		        			String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			
		            String[] strList = {unique_USCIS_id,emp_unique_id,stage3Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
		            USCISStageThreeData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        sql.printStackTrace();
		        Debug.print("SQL Exception in GeneralDBAction.getUSCISStageThreeDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        e.printStackTrace();
		        Debug.print("General Exception  in GeneralDBAction.getUSCISStageThreeDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return USCISStageThreeData;
		}
		
		public boolean insertUSCISStageThreeDetails(String empUniqueId, String createDate, String acknowledgementDate, String systemDate, String H1_BTransfer) {
			
			Debug.print("Inside GeneralDBAction.insertUSCISStageThreeDetails()::::::::::::::::::::");
					
			String stage3ParmData = null;
			stage3ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + H1_BTransfer;
			
			boolean insertStatus = false;
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeDOSExist(empUniqueId)){
			    	insertStmt=  "update tblUSCIS set stage3=?,stage3_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage3ParmData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	throw new Exception("Completed the Previous Stages");
			    }

			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee USCIS Stage3 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();
			       //releaseConnection();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertUSCISStageThreeDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertUSCISStageThreeDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public ArrayList getUSCISStageFourDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getUSCISStageFourDetailsByUniqueEmpId():");
		    ArrayList USCISStageFourData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_USCIS_id,emp_unique_id,stage4,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status "
		        		+"FROM tblUSCIS where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        USCISStageFourData = new ArrayList();
		        while(rs.next()){
		        			String unique_USCIS_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage2Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
		        			String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			
		            String[] strList = {unique_USCIS_id,emp_unique_id,stage2Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
		            USCISStageFourData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        sql.printStackTrace();
		        Debug.print("SQL Exception in GeneralDBAction.getUSCISStageFourDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        e.printStackTrace();
		        Debug.print("General Exception  in GeneralDBAction.getUSCISStageFourDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return USCISStageFourData;
		}
		
		public boolean insertUSCISStageFourDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String PostingI_129File) {
			
			Debug.print("Inside GeneralDBAction.insertUSCISStageFourDetails()::::::::::::::::::::");
					
			String stage4ParmData = null;
			stage4ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage4UploadData = "-sep-";
				stage4UploadData = stage4UploadData + PostingI_129File + "-sep-";
			stage4UploadData = stage4UploadData.substring(0, stage4UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeUSCISExist(empUniqueId)){
			    	insertStmt=  "update tblUSCIS set stage4=?,stage4_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage4ParmData + stage4UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblUSCIS(emp_unique_id,stage4,stage4_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage4ParmData + stage4UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee USCIS stage4 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertUSCISStageFourDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertUSCISStageFourDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public ArrayList getUSCISStageFiveDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getUSCISStageFiveDetailsByUniqueEmpId():");
		    ArrayList USCISStageFiveData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_USCIS_id,emp_unique_id,stage5,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status "
		        		+"FROM tblUSCIS where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        USCISStageFiveData = new ArrayList();
		        while(rs.next()){
		        			String unique_USCIS_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage5Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
		        			String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			
		            String[] strList = {unique_USCIS_id,emp_unique_id,stage5Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
		            USCISStageFiveData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        sql.printStackTrace();
		        Debug.print("SQL Exception in GeneralDBAction.getUSCISStageFiveDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        e.printStackTrace();
		        Debug.print("General Exception  in GeneralDBAction.getUSCISStageFiveDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return USCISStageFiveData;
		}

		public boolean insertUSCISStageFiveDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String onBoardingFile) {
			
			Debug.print("Inside GeneralDBAction.insertUSCISStageFiveDetails()::::::::::::::::::::");
					
			String stage5ParmData = null;
			stage5ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage5UploadData = "-sep-";
				stage5UploadData = stage5UploadData + onBoardingFile + "-sep-";
			stage5UploadData = stage5UploadData.substring(0, stage5UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeUSCISExist(empUniqueId)){
			    	insertStmt=  "update tblUSCIS set stage5=?,stage5_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage5ParmData + stage5UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblUSCIS(emp_unique_id,stage5,stage5_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage5ParmData + stage5UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee USCIS stage5 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertUSCISStageFiveDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertUSCISStageFiveDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public String getuniqueID(String reqID) {
			Debug.print("GeneralDBAction.getCanDetailsByUniqueId():");
		    String req_uniqueID = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = " SELECT post_req_uniqueId from tblMFPostRequirements where requirement_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, reqID);
		        rs = prepStmt.executeQuery();
		        
		        while(rs.next()){
		        	
		        	 req_uniqueID = rs.getString(1);
		        	
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getuniqueID():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getuniqueID():" + e.getMessage());
		    }
		    return req_uniqueID;

		}
		
		public boolean insertUSCISStageSixDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String Form1I_94File) {
			
			Debug.print("Inside GeneralDBAction.insertUSCISStageSixDetails()::::::::::::::::::::");
					
			String stage6ParmData = null;
			stage6ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage6UploadData = "-sep-";
				stage6UploadData = stage6UploadData + Form1I_94File + "-sep-";
			stage6UploadData = stage6UploadData.substring(0, stage6UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeUSCISExist(empUniqueId)){
			    	insertStmt=  "update tblUSCIS set stage6=?,stage6_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage6ParmData + stage6UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblUSCIS(emp_unique_id,stage6,stage6_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage6ParmData + stage6UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee USCIS stage6 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertUSCISStageSixDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertUSCISStageSixDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public ArrayList getUSCISStageSixDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getUSCISStageSixDetailsByUniqueEmpId():");
		    ArrayList USCISStageSixData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_USCIS_id,emp_unique_id,stage6,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status "
		        		+"FROM tblUSCIS where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        USCISStageSixData = new ArrayList();
		        while(rs.next()){
		        			String unique_USCIS_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage6Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
		        			String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			
		            String[] strList = {unique_USCIS_id,emp_unique_id,stage6Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
		            USCISStageSixData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        sql.printStackTrace();
		        Debug.print("SQL Exception in GeneralDBAction.getUSCISStageSixDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        e.printStackTrace();
		        Debug.print("General Exception  in GeneralDBAction.getUSCISStageSixDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return USCISStageSixData;
		}
		
		public ArrayList getUSCISStageSevenDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getUSCISStageSevenDetailsByUniqueEmpId():");
		    ArrayList USCISStageSevenData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_USCIS_id,emp_unique_id,stage7,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status "
		        		+"FROM tblUSCIS where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        USCISStageSevenData = new ArrayList();
		        while(rs.next()){
		        			String unique_USCIS_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage7Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
		        			String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			
		            String[] strList = {unique_USCIS_id,emp_unique_id,stage7Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
		            USCISStageSevenData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        sql.printStackTrace();
		        Debug.print("SQL Exception in GeneralDBAction.getUSCISStageSevenDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        e.printStackTrace();
		        Debug.print("General Exception  in GeneralDBAction.getUSCISStageSevenDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return USCISStageSevenData;
		}
		
		public boolean insertUSCISStageSevenDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String FormI_9File) {
			
			Debug.print("Inside GeneralDBAction.insertUSCISStageSevenDetails()::::::::::::::::::::");
					
			String stage7ParmData = null;
			stage7ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage7UploadData = "-sep-";
				stage7UploadData = stage7UploadData + FormI_9File + "-sep-";
			stage7UploadData = stage7UploadData.substring(0, stage7UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeUSCISExist(empUniqueId)){
			    	insertStmt=  "update tblUSCIS set stage7=?,stage7_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage7ParmData + stage7UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblUSCIS(emp_unique_id,stage7,stage7_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage7ParmData + stage7UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee USCIS stage7 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertUSCISStageSevenDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertUSCISStageSevenDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public ArrayList getUSCISStageEightDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getUSCISStageEightDetailsByUniqueEmpId():");
		    ArrayList DOSStageEightData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_USCIS_id,emp_unique_id,stage8,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status "
		        		+"FROM tblUSCIS where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOSStageEightData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOS_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage8Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
		        			String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			
		            String[] strList = {unique_DOS_id,emp_unique_id,stage8Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status};
		            DOSStageEightData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getUSCISStageEightDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getUSCISStageEightDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOSStageEightData;
		}
		public boolean insertUSCISStageEightDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String ContractDocFile,String TimesheetDocFile,
				String ClientDocFile,String WeeklyReviewDocFile) {
			
			Debug.print("Inside GeneralDBAction.insertUSCISStageEightDetails()::::::::::::::::::::");
					
			String stage8ParmData = null;
			stage8ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage8UploadData = "-sep-";
				stage8UploadData = stage8UploadData + ContractDocFile + "-sep-" + TimesheetDocFile + "-sep-" + ClientDocFile + "-sep-" + WeeklyReviewDocFile + "-sep-";
			stage8UploadData = stage8UploadData.substring(0, stage8UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeUSCISExist(empUniqueId)){
			    	insertStmt=  "update tblUSCIS set stage8=?,stage8_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage8ParmData + stage8UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblUSCIS(emp_unique_id,stage8,stage8_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage8ParmData + stage8UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee USCIS Stage8 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertUSCISStageEightDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertUSCISStageEightDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public ArrayList getDOLStageOneDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getDOLStageOneDetailsByUniqueEmpId():");
		    ArrayList DOLStageOneData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_DOL_id,emp_unique_id,stage1,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status"
						+",stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status"
						+",stage17_status,stage18_status,stage19_status,stage20_status "
		        		+"FROM tblDepartmentOfLabor where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOLStageOneData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOL_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage1Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
							String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			String stage9_status = String.valueOf(rs.getBoolean(12));
		        			String stage10_status = String.valueOf(rs.getBoolean(13));
		        			String stage11_status = String.valueOf(rs.getBoolean(14));
		        			String stage12_status = String.valueOf(rs.getBoolean(15));
							String stage13_status = String.valueOf(rs.getBoolean(16));
		        			String stage14_status = String.valueOf(rs.getBoolean(17));
		        			String stage15_status = String.valueOf(rs.getBoolean(18));
		        			String stage16_status = String.valueOf(rs.getBoolean(19));
		        			String stage17_status = String.valueOf(rs.getBoolean(20));
		        			String stage18_status = String.valueOf(rs.getBoolean(21));
							String stage19_status = String.valueOf(rs.getBoolean(22));
		        			String stage20_status = String.valueOf(rs.getBoolean(23));
		        			
		            String[] strList = {unique_DOL_id,emp_unique_id,stage1Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,
		            		stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,
		            		stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
		            DOLStageOneData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getDOLStageOneDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getDOLStageOneDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOLStageOneData;
		}
		
		public ArrayList getDOLStageTwoDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getDOLStageTwoDetailsByUniqueEmpId():");
		    ArrayList DOLStageTwoData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_DOL_id,emp_unique_id,stage2,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status"
						+",stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status"
						+",stage17_status,stage18_status,stage19_status,stage20_status "
		        		+"FROM tblDepartmentOfLabor where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOLStageTwoData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOL_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage2Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
							String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			String stage9_status = String.valueOf(rs.getBoolean(12));
		        			String stage10_status = String.valueOf(rs.getBoolean(13));
		        			String stage11_status = String.valueOf(rs.getBoolean(14));
		        			String stage12_status = String.valueOf(rs.getBoolean(15));
							String stage13_status = String.valueOf(rs.getBoolean(16));
		        			String stage14_status = String.valueOf(rs.getBoolean(17));
		        			String stage15_status = String.valueOf(rs.getBoolean(18));
		        			String stage16_status = String.valueOf(rs.getBoolean(19));
		        			String stage17_status = String.valueOf(rs.getBoolean(20));
		        			String stage18_status = String.valueOf(rs.getBoolean(21));
							String stage19_status = String.valueOf(rs.getBoolean(22));
		        			String stage20_status = String.valueOf(rs.getBoolean(23));
		        			
		            String[] strList = {unique_DOL_id,emp_unique_id,stage2Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,
		            		stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,
		            		stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
		            DOLStageTwoData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getDOLStageTwoDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getDOLStageTwoDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOLStageTwoData;
		}
		
		public ArrayList getDOLStageThreeDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getDOLStageThreeDetailsByUniqueEmpId():");
		    ArrayList DOLStageThreeData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_DOL_id,emp_unique_id,stage3,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status"
						+",stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status"
						+",stage17_status,stage18_status,stage19_status,stage20_status "
		        		+"FROM tblDepartmentOfLabor where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOLStageThreeData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOL_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage3Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
							String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			String stage9_status = String.valueOf(rs.getBoolean(12));
		        			String stage10_status = String.valueOf(rs.getBoolean(13));
		        			String stage11_status = String.valueOf(rs.getBoolean(14));
		        			String stage12_status = String.valueOf(rs.getBoolean(15));
							String stage13_status = String.valueOf(rs.getBoolean(16));
		        			String stage14_status = String.valueOf(rs.getBoolean(17));
		        			String stage15_status = String.valueOf(rs.getBoolean(18));
		        			String stage16_status = String.valueOf(rs.getBoolean(19));
		        			String stage17_status = String.valueOf(rs.getBoolean(20));
		        			String stage18_status = String.valueOf(rs.getBoolean(21));
							String stage19_status = String.valueOf(rs.getBoolean(22));
		        			String stage20_status = String.valueOf(rs.getBoolean(23));
		        			
		            String[] strList = {unique_DOL_id,emp_unique_id,stage3Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,
		            		stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,
		            		stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
		            DOLStageThreeData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getDOLStageThreeDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getDOLStageThreeDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOLStageThreeData;
		}
		
		public ArrayList getDOLStageFourDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getDOLStageFourDetailsByUniqueEmpId():");
		    ArrayList DOLStageFourData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_DOL_id,emp_unique_id,stage4,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status"
						+",stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status"
						+",stage17_status,stage18_status,stage19_status,stage20_status "
		        		+"FROM tblDepartmentOfLabor where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOLStageFourData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOL_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage4Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
							String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			String stage9_status = String.valueOf(rs.getBoolean(12));
		        			String stage10_status = String.valueOf(rs.getBoolean(13));
		        			String stage11_status = String.valueOf(rs.getBoolean(14));
		        			String stage12_status = String.valueOf(rs.getBoolean(15));
							String stage13_status = String.valueOf(rs.getBoolean(16));
		        			String stage14_status = String.valueOf(rs.getBoolean(17));
		        			String stage15_status = String.valueOf(rs.getBoolean(18));
		        			String stage16_status = String.valueOf(rs.getBoolean(19));
		        			String stage17_status = String.valueOf(rs.getBoolean(20));
		        			String stage18_status = String.valueOf(rs.getBoolean(21));
							String stage19_status = String.valueOf(rs.getBoolean(22));
		        			String stage20_status = String.valueOf(rs.getBoolean(23));
		        			
		            String[] strList = {unique_DOL_id,emp_unique_id,stage4Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,
		            		stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,
		            		stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
		            DOLStageFourData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getDOLStageFourDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getDOLStageFourDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOLStageFourData;
		}
		
		public ArrayList getDOLStageFiveDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getDOLStageFiveDetailsByUniqueEmpId():");
		    ArrayList DOLStageFiveData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_DOL_id,emp_unique_id,stage5,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status"
						+",stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status"
						+",stage17_status,stage18_status,stage19_status,stage20_status "
		        		+"FROM tblDepartmentOfLabor where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOLStageFiveData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOL_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage5Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
							String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			String stage9_status = String.valueOf(rs.getBoolean(12));
		        			String stage10_status = String.valueOf(rs.getBoolean(13));
		        			String stage11_status = String.valueOf(rs.getBoolean(14));
		        			String stage12_status = String.valueOf(rs.getBoolean(15));
							String stage13_status = String.valueOf(rs.getBoolean(16));
		        			String stage14_status = String.valueOf(rs.getBoolean(17));
		        			String stage15_status = String.valueOf(rs.getBoolean(18));
		        			String stage16_status = String.valueOf(rs.getBoolean(19));
		        			String stage17_status = String.valueOf(rs.getBoolean(20));
		        			String stage18_status = String.valueOf(rs.getBoolean(21));
							String stage19_status = String.valueOf(rs.getBoolean(22));
		        			String stage20_status = String.valueOf(rs.getBoolean(23));
		        			
		            String[] strList = {unique_DOL_id,emp_unique_id,stage5Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,
		            		stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,
		            		stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
		            DOLStageFiveData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getDOLStageFiveDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getDOLStageFiveDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOLStageFiveData;
		}
		
		public ArrayList getDOLStageSixDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getDOLStageSixDetailsByUniqueEmpId():");
		    ArrayList DOLStageSixData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_DOL_id,emp_unique_id,stage5,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status"
						+",stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status"
						+",stage17_status,stage18_status,stage19_status,stage20_status "
		        		+"FROM tblDepartmentOfLabor where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOLStageSixData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOL_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage6Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
							String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			String stage9_status = String.valueOf(rs.getBoolean(12));
		        			String stage10_status = String.valueOf(rs.getBoolean(13));
		        			String stage11_status = String.valueOf(rs.getBoolean(14));
		        			String stage12_status = String.valueOf(rs.getBoolean(15));
							String stage13_status = String.valueOf(rs.getBoolean(16));
		        			String stage14_status = String.valueOf(rs.getBoolean(17));
		        			String stage15_status = String.valueOf(rs.getBoolean(18));
		        			String stage16_status = String.valueOf(rs.getBoolean(19));
		        			String stage17_status = String.valueOf(rs.getBoolean(20));
		        			String stage18_status = String.valueOf(rs.getBoolean(21));
							String stage19_status = String.valueOf(rs.getBoolean(22));
		        			String stage20_status = String.valueOf(rs.getBoolean(23));
		        			
		            String[] strList = {unique_DOL_id,emp_unique_id,stage6Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,
		            		stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,
		            		stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
		            DOLStageSixData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getDOLStageSixDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getDOLStageSixDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOLStageSixData;
		}
		
		public ArrayList getDOLStageSevenDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getDOLStageSevenDetailsByUniqueEmpId():");
		    ArrayList DOLStageSevenData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_DOL_id,emp_unique_id,stage7,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status"
						+",stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status"
						+",stage17_status,stage18_status,stage19_status,stage20_status "
		        		+"FROM tblDepartmentOfLabor where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOLStageSevenData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOL_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage7Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
							String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			String stage9_status = String.valueOf(rs.getBoolean(12));
		        			String stage10_status = String.valueOf(rs.getBoolean(13));
		        			String stage11_status = String.valueOf(rs.getBoolean(14));
		        			String stage12_status = String.valueOf(rs.getBoolean(15));
							String stage13_status = String.valueOf(rs.getBoolean(16));
		        			String stage14_status = String.valueOf(rs.getBoolean(17));
		        			String stage15_status = String.valueOf(rs.getBoolean(18));
		        			String stage16_status = String.valueOf(rs.getBoolean(19));
		        			String stage17_status = String.valueOf(rs.getBoolean(20));
		        			String stage18_status = String.valueOf(rs.getBoolean(21));
							String stage19_status = String.valueOf(rs.getBoolean(22));
		        			String stage20_status = String.valueOf(rs.getBoolean(23));
		        			
		            String[] strList = {unique_DOL_id,emp_unique_id,stage7Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,
		            		stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,
		            		stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
		            DOLStageSevenData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getDOLStageSevenDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getDOLStageSevenDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOLStageSevenData;
		}
		
		public ArrayList getDOLStageEightDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getDOLStageEightDetailsByUniqueEmpId():");
		    ArrayList DOLStageEightData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_DOL_id,emp_unique_id,stage8,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status"
						+",stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status"
						+",stage17_status,stage18_status,stage19_status,stage20_status "
		        		+"FROM tblDepartmentOfLabor where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOLStageEightData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOL_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage8Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
							String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			String stage9_status = String.valueOf(rs.getBoolean(12));
		        			String stage10_status = String.valueOf(rs.getBoolean(13));
		        			String stage11_status = String.valueOf(rs.getBoolean(14));
		        			String stage12_status = String.valueOf(rs.getBoolean(15));
							String stage13_status = String.valueOf(rs.getBoolean(16));
		        			String stage14_status = String.valueOf(rs.getBoolean(17));
		        			String stage15_status = String.valueOf(rs.getBoolean(18));
		        			String stage16_status = String.valueOf(rs.getBoolean(19));
		        			String stage17_status = String.valueOf(rs.getBoolean(20));
		        			String stage18_status = String.valueOf(rs.getBoolean(21));
							String stage19_status = String.valueOf(rs.getBoolean(22));
		        			String stage20_status = String.valueOf(rs.getBoolean(23));
		        			
		            String[] strList = {unique_DOL_id,emp_unique_id,stage8Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,
		            		stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,
		            		stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
		            DOLStageEightData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getDOLStageEightDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getDOLStageEightDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOLStageEightData;
		}
		
		public ArrayList getDOLStageNineDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getDOLStageNineDetailsByUniqueEmpId():");
		    ArrayList DOLStageNineData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_DOL_id,emp_unique_id,stage9,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status"
						+",stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status"
						+",stage17_status,stage18_status,stage19_status,stage20_status "
		        		+"FROM tblDepartmentOfLabor where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOLStageNineData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOL_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage9Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
							String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			String stage9_status = String.valueOf(rs.getBoolean(12));
		        			String stage10_status = String.valueOf(rs.getBoolean(13));
		        			String stage11_status = String.valueOf(rs.getBoolean(14));
		        			String stage12_status = String.valueOf(rs.getBoolean(15));
							String stage13_status = String.valueOf(rs.getBoolean(16));
		        			String stage14_status = String.valueOf(rs.getBoolean(17));
		        			String stage15_status = String.valueOf(rs.getBoolean(18));
		        			String stage16_status = String.valueOf(rs.getBoolean(19));
		        			String stage17_status = String.valueOf(rs.getBoolean(20));
		        			String stage18_status = String.valueOf(rs.getBoolean(21));
							String stage19_status = String.valueOf(rs.getBoolean(22));
		        			String stage20_status = String.valueOf(rs.getBoolean(23));
		        			
		            String[] strList = {unique_DOL_id,emp_unique_id,stage9Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,
		            		stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,
		            		stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
		            DOLStageNineData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getDOLStageNineDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getDOLStageNineDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOLStageNineData;
		}
		
		public ArrayList getDOLStageTenDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getDOLStageTenDetailsByUniqueEmpId():");
		    ArrayList DOLStageTenData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_DOL_id,emp_unique_id,stage10,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status"
						+",stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status"
						+",stage17_status,stage18_status,stage19_status,stage20_status "
		        		+"FROM tblDepartmentOfLabor where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOLStageTenData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOL_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage10Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
							String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			String stage9_status = String.valueOf(rs.getBoolean(12));
		        			String stage10_status = String.valueOf(rs.getBoolean(13));
		        			String stage11_status = String.valueOf(rs.getBoolean(14));
		        			String stage12_status = String.valueOf(rs.getBoolean(15));
							String stage13_status = String.valueOf(rs.getBoolean(16));
		        			String stage14_status = String.valueOf(rs.getBoolean(17));
		        			String stage15_status = String.valueOf(rs.getBoolean(18));
		        			String stage16_status = String.valueOf(rs.getBoolean(19));
		        			String stage17_status = String.valueOf(rs.getBoolean(20));
		        			String stage18_status = String.valueOf(rs.getBoolean(21));
							String stage19_status = String.valueOf(rs.getBoolean(22));
		        			String stage20_status = String.valueOf(rs.getBoolean(23));
		        			
		            String[] strList = {unique_DOL_id,emp_unique_id,stage10Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,
		            		stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,
		            		stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
		            DOLStageTenData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getDOLStageTenDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getDOLStageTenDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOLStageTenData;
		}
		
		public ArrayList getDOLStageElevenDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getDOLStageElevenDetailsByUniqueEmpId():");
		    ArrayList DOLStageElevenData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_DOL_id,emp_unique_id,stage11,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status"
						+",stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status"
						+",stage17_status,stage18_status,stage19_status,stage20_status "
		        		+"FROM tblDepartmentOfLabor where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOLStageElevenData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOL_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage11Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
							String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			String stage9_status = String.valueOf(rs.getBoolean(12));
		        			String stage10_status = String.valueOf(rs.getBoolean(13));
		        			String stage11_status = String.valueOf(rs.getBoolean(14));
		        			String stage12_status = String.valueOf(rs.getBoolean(15));
							String stage13_status = String.valueOf(rs.getBoolean(16));
		        			String stage14_status = String.valueOf(rs.getBoolean(17));
		        			String stage15_status = String.valueOf(rs.getBoolean(18));
		        			String stage16_status = String.valueOf(rs.getBoolean(19));
		        			String stage17_status = String.valueOf(rs.getBoolean(20));
		        			String stage18_status = String.valueOf(rs.getBoolean(21));
							String stage19_status = String.valueOf(rs.getBoolean(22));
		        			String stage20_status = String.valueOf(rs.getBoolean(23));
		        			
		            String[] strList = {unique_DOL_id,emp_unique_id,stage11Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,
		            		stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,
		            		stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
		            DOLStageElevenData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getDOLStageElevenDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getDOLStageElevenDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOLStageElevenData;
		}
		
		public ArrayList getDOLStageTwelveDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getDOLStageTwelveDetailsByUniqueEmpId():");
		    ArrayList DOLStageTwelveData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_DOL_id,emp_unique_id,stage12,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status"
						+",stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status"
						+",stage17_status,stage18_status,stage19_status,stage20_status "
		        		+"FROM tblDepartmentOfLabor where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOLStageTwelveData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOL_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage12Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
							String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			String stage9_status = String.valueOf(rs.getBoolean(12));
		        			String stage10_status = String.valueOf(rs.getBoolean(13));
		        			String stage11_status = String.valueOf(rs.getBoolean(14));
		        			String stage12_status = String.valueOf(rs.getBoolean(15));
							String stage13_status = String.valueOf(rs.getBoolean(16));
		        			String stage14_status = String.valueOf(rs.getBoolean(17));
		        			String stage15_status = String.valueOf(rs.getBoolean(18));
		        			String stage16_status = String.valueOf(rs.getBoolean(19));
		        			String stage17_status = String.valueOf(rs.getBoolean(20));
		        			String stage18_status = String.valueOf(rs.getBoolean(21));
							String stage19_status = String.valueOf(rs.getBoolean(22));
		        			String stage20_status = String.valueOf(rs.getBoolean(23));
		        			
		            String[] strList = {unique_DOL_id,emp_unique_id,stage12Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,
		            		stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,
		            		stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
		            DOLStageTwelveData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getDOLStageTwelveDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getDOLStageTwelveDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOLStageTwelveData;
		}
		
		public ArrayList getDOLStageThirteenDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getDOLStageThirteenDetailsByUniqueEmpId():");
		    ArrayList DOLStageThirteenData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_DOL_id,emp_unique_id,stage13,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status"
						+",stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status"
						+",stage17_status,stage18_status,stage19_status,stage20_status "
		        		+"FROM tblDepartmentOfLabor where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOLStageThirteenData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOL_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage13Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
							String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			String stage9_status = String.valueOf(rs.getBoolean(12));
		        			String stage10_status = String.valueOf(rs.getBoolean(13));
		        			String stage11_status = String.valueOf(rs.getBoolean(14));
		        			String stage12_status = String.valueOf(rs.getBoolean(15));
							String stage13_status = String.valueOf(rs.getBoolean(16));
		        			String stage14_status = String.valueOf(rs.getBoolean(17));
		        			String stage15_status = String.valueOf(rs.getBoolean(18));
		        			String stage16_status = String.valueOf(rs.getBoolean(19));
		        			String stage17_status = String.valueOf(rs.getBoolean(20));
		        			String stage18_status = String.valueOf(rs.getBoolean(21));
							String stage19_status = String.valueOf(rs.getBoolean(22));
		        			String stage20_status = String.valueOf(rs.getBoolean(23));
		        			
		            String[] strList = {unique_DOL_id,emp_unique_id,stage13Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,
		            		stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,
		            		stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
		            DOLStageThirteenData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getDOLStageThirteenDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getDOLStageThirteenDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOLStageThirteenData;
		}
		
		public ArrayList getDOLStageFourteenDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getDOLStageFourteenDetailsByUniqueEmpId():");
		    ArrayList DOLStageFourteenData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_DOL_id,emp_unique_id,stage14,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status"
						+",stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status"
						+",stage17_status,stage18_status,stage19_status,stage20_status "
		        		+"FROM tblDepartmentOfLabor where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOLStageFourteenData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOL_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage14Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
							String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			String stage9_status = String.valueOf(rs.getBoolean(12));
		        			String stage10_status = String.valueOf(rs.getBoolean(13));
		        			String stage11_status = String.valueOf(rs.getBoolean(14));
		        			String stage12_status = String.valueOf(rs.getBoolean(15));
							String stage13_status = String.valueOf(rs.getBoolean(16));
		        			String stage14_status = String.valueOf(rs.getBoolean(17));
		        			String stage15_status = String.valueOf(rs.getBoolean(18));
		        			String stage16_status = String.valueOf(rs.getBoolean(19));
		        			String stage17_status = String.valueOf(rs.getBoolean(20));
		        			String stage18_status = String.valueOf(rs.getBoolean(21));
							String stage19_status = String.valueOf(rs.getBoolean(22));
		        			String stage20_status = String.valueOf(rs.getBoolean(23));
		        			
		            String[] strList = {unique_DOL_id,emp_unique_id,stage14Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,
		            		stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,
		            		stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
		            DOLStageFourteenData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getDOLStageFourteenDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getDOLStageFourteenDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOLStageFourteenData;
		}
		
		public ArrayList getDOLStageFifteenDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getDOLStageFifteenDetailsByUniqueEmpId():");
		    ArrayList DOLStageFifteenData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_DOL_id,emp_unique_id,stage15,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status"
						+",stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status"
						+",stage17_status,stage18_status,stage19_status,stage20_status "
		        		+"FROM tblDepartmentOfLabor where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOLStageFifteenData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOL_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage15Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
							String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			String stage9_status = String.valueOf(rs.getBoolean(12));
		        			String stage10_status = String.valueOf(rs.getBoolean(13));
		        			String stage11_status = String.valueOf(rs.getBoolean(14));
		        			String stage12_status = String.valueOf(rs.getBoolean(15));
							String stage13_status = String.valueOf(rs.getBoolean(16));
		        			String stage14_status = String.valueOf(rs.getBoolean(17));
		        			String stage15_status = String.valueOf(rs.getBoolean(18));
		        			String stage16_status = String.valueOf(rs.getBoolean(19));
		        			String stage17_status = String.valueOf(rs.getBoolean(20));
		        			String stage18_status = String.valueOf(rs.getBoolean(21));
							String stage19_status = String.valueOf(rs.getBoolean(22));
		        			String stage20_status = String.valueOf(rs.getBoolean(23));
		        			
		            String[] strList = {unique_DOL_id,emp_unique_id,stage15Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,
		            		stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,
		            		stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
		            DOLStageFifteenData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getDOLStageFifteenDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getDOLStageFifteenDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOLStageFifteenData;
		}
		
		public ArrayList getDOLStageSixteenDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getDOLStageSixteenDetailsByUniqueEmpId():");
		    ArrayList DOLStageSixteenData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_DOL_id,emp_unique_id,stage16,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status"
						+",stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status"
						+",stage17_status,stage18_status,stage19_status,stage20_status "
		        		+"FROM tblDepartmentOfLabor where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOLStageSixteenData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOL_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage16Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
							String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			String stage9_status = String.valueOf(rs.getBoolean(12));
		        			String stage10_status = String.valueOf(rs.getBoolean(13));
		        			String stage11_status = String.valueOf(rs.getBoolean(14));
		        			String stage12_status = String.valueOf(rs.getBoolean(15));
							String stage13_status = String.valueOf(rs.getBoolean(16));
		        			String stage14_status = String.valueOf(rs.getBoolean(17));
		        			String stage15_status = String.valueOf(rs.getBoolean(18));
		        			String stage16_status = String.valueOf(rs.getBoolean(19));
		        			String stage17_status = String.valueOf(rs.getBoolean(20));
		        			String stage18_status = String.valueOf(rs.getBoolean(21));
							String stage19_status = String.valueOf(rs.getBoolean(22));
		        			String stage20_status = String.valueOf(rs.getBoolean(23));
		        			
		            String[] strList = {unique_DOL_id,emp_unique_id,stage16Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,
		            		stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,
		            		stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
		            DOLStageSixteenData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getDOLStageSixteenDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getDOLStageSixteenDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOLStageSixteenData;
		}
		
		public ArrayList getDOLStageSeventeenDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getDOLStageSeventeenDetailsByUniqueEmpId():");
		    ArrayList DOLStageSeventeenData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_DOL_id,emp_unique_id,stage17,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status"
						+",stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status"
						+",stage17_status,stage18_status,stage19_status,stage20_status "
		        		+"FROM tblDepartmentOfLabor where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOLStageSeventeenData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOL_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage17Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
							String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			String stage9_status = String.valueOf(rs.getBoolean(12));
		        			String stage10_status = String.valueOf(rs.getBoolean(13));
		        			String stage11_status = String.valueOf(rs.getBoolean(14));
		        			String stage12_status = String.valueOf(rs.getBoolean(15));
							String stage13_status = String.valueOf(rs.getBoolean(16));
		        			String stage14_status = String.valueOf(rs.getBoolean(17));
		        			String stage15_status = String.valueOf(rs.getBoolean(18));
		        			String stage16_status = String.valueOf(rs.getBoolean(19));
		        			String stage17_status = String.valueOf(rs.getBoolean(20));
		        			String stage18_status = String.valueOf(rs.getBoolean(21));
							String stage19_status = String.valueOf(rs.getBoolean(22));
		        			String stage20_status = String.valueOf(rs.getBoolean(23));
		        			
		            String[] strList = {unique_DOL_id,emp_unique_id,stage17Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,
		            		stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,
		            		stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
		            DOLStageSeventeenData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getDOLStageSeventeenDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getDOLStageSeventeenDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOLStageSeventeenData;
		}
		
		public ArrayList getDOLStageEighteenDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getDOLStageEighteenDetailsByUniqueEmpId():");
		    ArrayList DOLStageEighteenData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_DOL_id,emp_unique_id,stage18,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status"
						+",stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status"
						+",stage17_status,stage18_status,stage19_status,stage20_status "
		        		+"FROM tblDepartmentOfLabor where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOLStageEighteenData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOL_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage18Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
							String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			String stage9_status = String.valueOf(rs.getBoolean(12));
		        			String stage10_status = String.valueOf(rs.getBoolean(13));
		        			String stage11_status = String.valueOf(rs.getBoolean(14));
		        			String stage12_status = String.valueOf(rs.getBoolean(15));
							String stage13_status = String.valueOf(rs.getBoolean(16));
		        			String stage14_status = String.valueOf(rs.getBoolean(17));
		        			String stage15_status = String.valueOf(rs.getBoolean(18));
		        			String stage16_status = String.valueOf(rs.getBoolean(19));
		        			String stage17_status = String.valueOf(rs.getBoolean(20));
		        			String stage18_status = String.valueOf(rs.getBoolean(21));
							String stage19_status = String.valueOf(rs.getBoolean(22));
		        			String stage20_status = String.valueOf(rs.getBoolean(23));
		        			
		            String[] strList = {unique_DOL_id,emp_unique_id,stage18Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,
		            		stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,
		            		stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
		            DOLStageEighteenData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getDOLStageEighteenDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getDOLStageEighteenDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOLStageEighteenData;
		}
		
		public ArrayList getDOLStageNineteenDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getDOLStageNineteenDetailsByUniqueEmpId():");
		    ArrayList DOLStageNineteenData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_DOL_id,emp_unique_id,stage19,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status"
						+",stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status"
						+",stage17_status,stage18_status,stage19_status,stage20_status "
		        		+"FROM tblDepartmentOfLabor where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOLStageNineteenData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOL_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage19Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
							String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			String stage9_status = String.valueOf(rs.getBoolean(12));
		        			String stage10_status = String.valueOf(rs.getBoolean(13));
		        			String stage11_status = String.valueOf(rs.getBoolean(14));
		        			String stage12_status = String.valueOf(rs.getBoolean(15));
							String stage13_status = String.valueOf(rs.getBoolean(16));
		        			String stage14_status = String.valueOf(rs.getBoolean(17));
		        			String stage15_status = String.valueOf(rs.getBoolean(18));
		        			String stage16_status = String.valueOf(rs.getBoolean(19));
		        			String stage17_status = String.valueOf(rs.getBoolean(20));
		        			String stage18_status = String.valueOf(rs.getBoolean(21));
							String stage19_status = String.valueOf(rs.getBoolean(22));
		        			String stage20_status = String.valueOf(rs.getBoolean(23));
		        			
		            String[] strList = {unique_DOL_id,emp_unique_id,stage19Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,
		            		stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,
		            		stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
		            DOLStageNineteenData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getDOLStageNineteenDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getDOLStageNineteenDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOLStageNineteenData;
		}
		
		public ArrayList getDOLStageTwentyDetailsByUniqueEmpId(String empUniqueId) {
			Debug.print("GeneralDBAction.getDOLStageTwentyDetailsByUniqueEmpId():");
		    ArrayList DOLStageTwentyData = null;
		    PreparedStatement prepStmt = null;
		    ResultSet rs = null;
		    makeConnection();
			try {
		        String selectStatement = "SELECT unique_DOL_id,emp_unique_id,stage20,stage1_status,stage2_status"
		        		+",stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,stage9_status"
						+",stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,stage15_status,stage16_status"
						+",stage17_status,stage18_status,stage19_status,stage20_status "
		        		+"FROM tblDepartmentOfLabor where emp_unique_id=?";
		        prepStmt = con.prepareStatement(selectStatement);
		        prepStmt.setString(1, empUniqueId);
		        rs = prepStmt.executeQuery();
		        DOLStageTwentyData = new ArrayList();
		        while(rs.next()){
		        			String unique_DOL_id = rs.getString(1);
		        			String emp_unique_id = rs.getString(2);
		        			String stage20Data = rs.getString(3);
		        			String stage1_status = String.valueOf(rs.getBoolean(4));
		        			String stage2_status = String.valueOf(rs.getBoolean(5));
		        			String stage3_status = String.valueOf(rs.getBoolean(6));
		        			String stage4_status = String.valueOf(rs.getBoolean(7));
		        			String stage5_status = String.valueOf(rs.getBoolean(8));
		        			String stage6_status = String.valueOf(rs.getBoolean(9));
							String stage7_status = String.valueOf(rs.getBoolean(10));
		        			String stage8_status = String.valueOf(rs.getBoolean(11));
		        			String stage9_status = String.valueOf(rs.getBoolean(12));
		        			String stage10_status = String.valueOf(rs.getBoolean(13));
		        			String stage11_status = String.valueOf(rs.getBoolean(14));
		        			String stage12_status = String.valueOf(rs.getBoolean(15));
							String stage13_status = String.valueOf(rs.getBoolean(16));
		        			String stage14_status = String.valueOf(rs.getBoolean(17));
		        			String stage15_status = String.valueOf(rs.getBoolean(18));
		        			String stage16_status = String.valueOf(rs.getBoolean(19));
		        			String stage17_status = String.valueOf(rs.getBoolean(20));
		        			String stage18_status = String.valueOf(rs.getBoolean(21));
							String stage19_status = String.valueOf(rs.getBoolean(22));
		        			String stage20_status = String.valueOf(rs.getBoolean(23));
		        			
		            String[] strList = {unique_DOL_id,emp_unique_id,stage20Data,stage1_status,stage2_status,
		            		stage3_status,stage4_status,stage5_status,stage6_status,stage7_status,stage8_status,
		            		stage9_status,stage10_status,stage11_status,stage12_status,stage13_status,stage14_status,
		            		stage15_status,stage16_status,stage17_status,stage18_status,stage19_status,stage20_status};
		            DOLStageTwentyData.add(strList);
		        }
		        rs.close();
		        prepStmt.close();
		        releaseConnection();
		    } 
		    catch(SQLException sql){
		        releaseConnection();
		        Debug.print("SQL Exception in GeneralDBAction.getDOLStageTwentyDetailsByUniqueEmpId():" + sql.getMessage());
		    }
		    catch(Exception e){
		        releaseConnection();
		        Debug.print("General Exception  in GeneralDBAction.getDOLStageTwentyDetailsByUniqueEmpId():" + e.getMessage());
		    }
		    return DOLStageTwentyData;
		}

		public boolean insertDOLStageOneDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String visaformFile,String additionalfileFile) {
			
			Debug.print("Inside GeneralDBAction.insertDOLStageOneDetails()::::::::::::::::::::");
					
			String stage1ParmData = null;
			stage1ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage1UploadData = "-sep-";
				stage1UploadData = stage1UploadData + visaformFile + "-sep-" + additionalfileFile + "-sep-";
			stage1UploadData = stage1UploadData.substring(0, stage1UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeDOLExist(empUniqueId)){
			    	insertStmt=  "update tblDepartmentOfLabor set stage1=?,stage1_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage1ParmData + stage1UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblDepartmentOfLabor(emp_unique_id,stage1,stage1_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage1ParmData + stage1UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee Department of Labor Stage1 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertDOLStageOneDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertDOLStageOneDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public boolean insertDOLStageTwoDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String JobFormatFile) {
			
			Debug.print("Inside GeneralDBAction.insertDOLStageTwoDetails()::::::::::::::::::::");
					
			String stage2ParmData = null;
			stage2ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage2UploadData = "-sep-";
				stage2UploadData = stage2UploadData + JobFormatFile + "-sep-";
			stage2UploadData = stage2UploadData.substring(0, stage2UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeDOLExist(empUniqueId)){
			    	insertStmt=  "update tblDepartmentOfLabor set stage2=?,stage2_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage2ParmData + stage2UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblDepartmentOfLabor(emp_unique_id,stage2,stage2_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage2ParmData + stage2UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee Department of Labor Stage2 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertDOLStageTwoDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertDOLStageTwoDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public boolean insertDOLStageThreeDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String ActualWageYesNo) {
			
			Debug.print("Inside GeneralDBAction.insertDOLStageThreeDetails()::::::::::::::::::::");
					
			String stage3ParmData = null;
			stage3ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus + "-sep-" + ActualWageYesNo;
			
			
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeDOLExist(empUniqueId)){
			    	insertStmt=  "update tblDepartmentOfLabor set stage3=?,stage3_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage3ParmData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblDepartmentOfLabor(emp_unique_id,stage3,stage3_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage3ParmData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee Department of Labor Stage3 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertDOLStageThreeDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertDOLStageThreeDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public boolean insertDOLStageFourDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String StateDeptLabourFile,String NewsPaperFile,
				String OnlineJobPortalFile) {
			
			Debug.print("Inside GeneralDBAction.insertDOLStageFourDetails()::::::::::::::::::::");
					
			String stage4ParmData = null;
			stage4ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage4UploadData = "-sep-";
				stage4UploadData = stage4UploadData + StateDeptLabourFile + "-sep-" + NewsPaperFile + "-sep-" + OnlineJobPortalFile + "-sep-";
			stage4UploadData = stage4UploadData.substring(0, stage4UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeDOLExist(empUniqueId)){
			    	insertStmt=  "update tblDepartmentOfLabor set stage4=?,stage4_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage4ParmData + stage4UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblDepartmentOfLabor(emp_unique_id,stage4,stage4_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage4ParmData + stage4UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee Department of Labor Stage4 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertDOLStageFourDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertDOLStageFourDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public boolean insertDOLStageFiveDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String visaformFile,String additionalfileFile) {
			
			Debug.print("Inside GeneralDBAction.insertDOLStageFiveDetails()::::::::::::::::::::");
					
			String stage5ParmData = null;
			stage5ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage5UploadData = "-sep-";
				stage5UploadData = stage5UploadData + visaformFile + "-sep-" + additionalfileFile + "-sep-";
			stage5UploadData = stage5UploadData.substring(0, stage5UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeDOLExist(empUniqueId)){
			    	insertStmt=  "update tblDepartmentOfLabor set stage5=?,stage5_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage5ParmData + stage5UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblDepartmentOfLabor(emp_unique_id,stage5,stage5_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage5ParmData + stage5UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee Department of Labor Stage5 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertDOLStageFiveDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertDOLStageFiveDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public boolean insertDOLStageSixDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String EducationQualifyFile,String ExperienceSkillFile,
				String CustomDocsStage6File) {
			
			Debug.print("Inside GeneralDBAction.insertDOLStageSixDetails()::::::::::::::::::::");
					
			String stage6ParmData = null;
			stage6ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage6UploadData = "-sep-";
				stage6UploadData = stage6UploadData + EducationQualifyFile + "-sep-" + ExperienceSkillFile + "-sep-" + CustomDocsStage6File + "-sep-";
			stage6UploadData = stage6UploadData.substring(0, stage6UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeDOLExist(empUniqueId)){
			    	insertStmt=  "update tblDepartmentOfLabor set stage6=?,stage6_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage6ParmData + stage6UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblDepartmentOfLabor(emp_unique_id,stage6,stage6_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage6ParmData + stage6UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee Department of Labor Stage6 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertDOLStageSixDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertDOLStageSixDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public boolean insertDOLStageSevenDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String visaformFile,String additionalfileFile) {
			
			Debug.print("Inside GeneralDBAction.insertDOLStageSevenDetails()::::::::::::::::::::");
					
			String stage7ParmData = null;
			stage7ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage7UploadData = "-sep-";
				stage7UploadData = stage7UploadData + visaformFile + "-sep-" + additionalfileFile + "-sep-";
			stage7UploadData = stage7UploadData.substring(0, stage7UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeDOLExist(empUniqueId)){
			    	insertStmt=  "update tblDepartmentOfLabor set stage7=?,stage7_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage7ParmData + stage7UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblDepartmentOfLabor(emp_unique_id,stage7,stage7_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage7ParmData + stage7UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee Department of Labor Stage7 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertDOLStageSevenDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertDOLStageSevenDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public boolean insertDOLStageEightDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String visaformFile,String additionalfileFile) {
			
			Debug.print("Inside GeneralDBAction.insertDOLStageEightDetails()::::::::::::::::::::");
					
			String stage8ParmData = null;
			stage8ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage8UploadData = "-sep-";
				stage8UploadData = stage8UploadData + visaformFile + "-sep-" + additionalfileFile + "-sep-";
			stage8UploadData = stage8UploadData.substring(0, stage8UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeDOLExist(empUniqueId)){
			    	insertStmt=  "update tblDepartmentOfLabor set stage8=?,stage8_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage8ParmData + stage8UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblDepartmentOfLabor(emp_unique_id,stage8,stage8_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage8ParmData + stage8UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee Department of Labor Stage8 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertDOLStageEightDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertDOLStageEightDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public boolean insertDOLStageNineDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String PrevailingWage,String LessPrevailingWage,
				String PrevailingWageFormFile) {
			
			Debug.print("Inside GeneralDBAction.insertDOLStageNineDetails()::::::::::::::::::::");
					
			String stage9ParmData = null;
			stage9ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus + "-sep-" + PrevailingWage;
			
			String stage9UploadData = "-sep-";
				stage9UploadData = stage9UploadData + LessPrevailingWage + "-sep-" + PrevailingWageFormFile + "-sep-";
			stage9UploadData = stage9UploadData.substring(0, stage9UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeDOLExist(empUniqueId)){
			    	insertStmt=  "update tblDepartmentOfLabor set stage9=?,stage9_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage9ParmData + stage9UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblDepartmentOfLabor(emp_unique_id,stage9,stage9_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage9ParmData + stage9UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee Department of Labor Stage9 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertDOLStageNineDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertDOLStageNineDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public boolean insertDOLStageTenDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String PayDocument,String NoPayDocumentNotes,
				String PayDocumentFormFile,String CustomDocsStage10File) {
			
			Debug.print("Inside GeneralDBAction.insertDOLStageTenDetails()::::::::::::::::::::");
					
			String stage10ParmData = null;
			stage10ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus + "-sep-" + PayDocument + "-sep-" + NoPayDocumentNotes;
			
			String stage10UploadData = "-sep-";
				stage10UploadData = stage10UploadData + PayDocumentFormFile + "-sep-" + CustomDocsStage10File + "-sep-";
			stage10UploadData = stage10UploadData.substring(0, stage10UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeDOLExist(empUniqueId)){
			    	insertStmt=  "update tblDepartmentOfLabor set stage10=?,stage10_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage10ParmData + stage10UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblDepartmentOfLabor(emp_unique_id,stage10,stage10_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage10ParmData + stage10UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee Department of Labor Stage10 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertDOLStageTenDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertDOLStageTenDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public boolean insertDOLStageElevenDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String visaformFile,String additionalfileFile) {
			
			Debug.print("Inside GeneralDBAction.insertDOLStageElevenDetails()::::::::::::::::::::");
					
			String stage11ParmData = null;
			stage11ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage11UploadData = "-sep-";
				stage11UploadData = stage11UploadData + visaformFile + "-sep-" + additionalfileFile + "-sep-";
			stage11UploadData = stage11UploadData.substring(0, stage11UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeDOLExist(empUniqueId)){
			    	insertStmt=  "update tblDepartmentOfLabor set stage11=?,stage11_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage11ParmData + stage11UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblDepartmentOfLabor(emp_unique_id,stage11,stage11_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage11ParmData + stage11UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee Department of Labor Stage11 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertDOLStageElevenDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertDOLStageElevenDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public boolean insertDOLStageTwelveDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String visaformFile,String additionalfileFile) {
			
			Debug.print("Inside GeneralDBAction.insertDOLStageTwelveDetails()::::::::::::::::::::");
					
			String stage12ParmData = null;
			stage12ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage12UploadData = "-sep-";
				stage12UploadData = stage12UploadData + visaformFile + "-sep-" + additionalfileFile + "-sep-";
			stage12UploadData = stage12UploadData.substring(0, stage12UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeDOLExist(empUniqueId)){
			    	insertStmt=  "update tblDepartmentOfLabor set stage12=?,stage12_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage12ParmData + stage12UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblDepartmentOfLabor(emp_unique_id,stage12,stage12_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage12ParmData + stage12UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee Department of Labor Stage12 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertDOLStageTwelveDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertDOLStageTwelveDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public boolean insertDOLStageThirteenDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String visaformFile,String additionalfileFile) {
			
			Debug.print("Inside GeneralDBAction.insertDOLStageThirteenDetails()::::::::::::::::::::");
					
			String stage13ParmData = null;
			stage13ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage13UploadData = "-sep-";
				stage13UploadData = stage13UploadData + visaformFile + "-sep-" + additionalfileFile + "-sep-";
			stage13UploadData = stage13UploadData.substring(0, stage13UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeDOLExist(empUniqueId)){
			    	insertStmt=  "update tblDepartmentOfLabor set stage13=?,stage13_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage13ParmData + stage13UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblDepartmentOfLabor(emp_unique_id,stage13,stage13_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage13ParmData + stage13UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee Department of Labor Stage13 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertDOLStageThirteenDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertDOLStageThirteenDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public boolean insertDOLStageFourteenDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String visaformFile,String additionalfileFile) {
			
			Debug.print("Inside GeneralDBAction.insertDOLStageFourteenDetails()::::::::::::::::::::");
					
			String stage14ParmData = null;
			stage14ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage14UploadData = "-sep-";
				stage14UploadData = stage14UploadData + visaformFile + "-sep-" + additionalfileFile + "-sep-";
			stage14UploadData = stage14UploadData.substring(0, stage14UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeDOLExist(empUniqueId)){
			    	insertStmt=  "update tblDepartmentOfLabor set stage14=?,stage14_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage14ParmData + stage14UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblDepartmentOfLabor(emp_unique_id,stage14,stage14_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage14ParmData + stage14UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee Department of Labor Stage14 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertDOLStageFourteenDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertDOLStageFourteenDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public boolean insertDOLStageFifteenDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String TakenPhotoFile,String SignClarificationFile,String CustomDocsStage15File) {
			
			Debug.print("Inside GeneralDBAction.insertDOLStageFifteenDetails()::::::::::::::::::::");
					
			String stage15ParmData = null;
			stage15ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage15UploadData = "-sep-";
				stage15UploadData = stage15UploadData + TakenPhotoFile + "-sep-" + SignClarificationFile + "-sep-" + CustomDocsStage15File + "-sep-";
			stage15UploadData = stage15UploadData.substring(0, stage15UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeDOLExist(empUniqueId)){
			    	insertStmt=  "update tblDepartmentOfLabor set stage15=?,stage15_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage15ParmData + stage15UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblDepartmentOfLabor(emp_unique_id,stage15,stage15_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage15ParmData + stage15UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee Department of Labor Stage15 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertDOLStageFifteenDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertDOLStageFifteenDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public boolean insertDOLStageSixteenDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String visaformFile,String additionalfileFile) {
			
			Debug.print("Inside GeneralDBAction.insertDOLStageSixteenDetails()::::::::::::::::::::");
					
			String stage16ParmData = null;
			stage16ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage16UploadData = "-sep-";
				stage16UploadData = stage16UploadData + visaformFile + "-sep-" + additionalfileFile + "-sep-";
			stage16UploadData = stage16UploadData.substring(0, stage16UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeDOLExist(empUniqueId)){
			    	insertStmt=  "update tblDepartmentOfLabor set stage16=?,stage16_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage16ParmData + stage16UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblDepartmentOfLabor(emp_unique_id,stage16,stage16_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage16ParmData + stage16UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee Department of Labor Stage16 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertDOLStageSixteenDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertDOLStageSixteenDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public boolean insertDOLStageSeventeenDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String PhotoTime,String TakenPhotoStage17File,String SystemTakenFile) {
			
			Debug.print("Inside GeneralDBAction.insertDOLStageSeventeenDetails()::::::::::::::::::::");
					
			String stage17ParmData = null;
			stage17ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus + "-sep-" + PhotoTime;
			
			String stage17UploadData = "-sep-";
				stage17UploadData = stage17UploadData + TakenPhotoStage17File + "-sep-" + SystemTakenFile + "-sep-";
			stage17UploadData = stage17UploadData.substring(0, stage17UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeDOLExist(empUniqueId)){
			    	insertStmt=  "update tblDepartmentOfLabor set stage17=?,stage17_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage17ParmData + stage17UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblDepartmentOfLabor(emp_unique_id,stage17,stage17_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage17ParmData + stage17UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee Department of Labor Stage17 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertDOLStageSeventeenDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertDOLStageSeventeenDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public boolean insertDOLStageEighteenDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String visaformFile,String additionalfileFile) {
			
			Debug.print("Inside GeneralDBAction.insertDOLStageEighteenDetails()::::::::::::::::::::");
					
			String stage18ParmData = null;
			stage18ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage18UploadData = "-sep-";
				stage18UploadData = stage18UploadData + visaformFile + "-sep-" + additionalfileFile + "-sep-";
			stage18UploadData = stage18UploadData.substring(0, stage18UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeDOLExist(empUniqueId)){
			    	insertStmt=  "update tblDepartmentOfLabor set stage18=?,stage18_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage18ParmData + stage18UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblDepartmentOfLabor(emp_unique_id,stage18,stage18_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage18ParmData + stage18UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee Department of Labor Stage18 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertDOLStageEighteenDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertDOLStageEighteenDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public boolean insertDOLStageNineteenDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String EmailProcessFile,String SystemRecordFile, String MailPostingFile) {
			
			Debug.print("Inside GeneralDBAction.insertDOLStageNineteenDetails()::::::::::::::::::::");
					
			String stage19ParmData = null;
			stage19ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage19UploadData = "-sep-";
				stage19UploadData = stage19UploadData + EmailProcessFile + "-sep-" + SystemRecordFile + "-sep-" + MailPostingFile + "-sep-";
			stage19UploadData = stage19UploadData.substring(0, stage19UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeDOLExist(empUniqueId)){
			    	insertStmt=  "update tblDepartmentOfLabor set stage19=?,stage19_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage19ParmData + stage19UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblDepartmentOfLabor(emp_unique_id,stage19,stage19_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage19ParmData + stage19UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee Department of Labor Stage19 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertDOLStageNineteenDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertDOLStageNineteenDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		public boolean insertDOLStageTwentyDetails(String empUniqueId,
				String createDate, String acknowledgementDate, String systemDate,
				String approveStatus, String visaformFile,String additionalfileFile) {
			
			Debug.print("Inside GeneralDBAction.insertDOLStageTwentyDetails()::::::::::::::::::::");
					
			String stage20ParmData = null;
			stage20ParmData = createDate + "-sep-" + acknowledgementDate + "-sep-" + systemDate + "-sep-" + approveStatus;
			
			String stage20UploadData = "-sep-";
				stage20UploadData = stage20UploadData + visaformFile + "-sep-" + additionalfileFile + "-sep-";
			stage20UploadData = stage20UploadData.substring(0, stage20UploadData.length() - 5);
			
			boolean insertStatus = false;
			
			PreparedStatement prepStmt = null;
		    ResultSet rs = null;
			try{

			    makeConnection(); 
			    String insertStmt = null;
			    if(checkEmployeeDOLExist(empUniqueId)){
			    	insertStmt=  "update tblDepartmentOfLabor set stage20=?,stage20_status=? where"
			    			+" emp_unique_id=?";
			    	 prepStmt = con.prepareStatement(insertStmt);
				     prepStmt.setString(1, stage20ParmData + stage20UploadData);
				     prepStmt.setBoolean(2, true);
				     prepStmt.setString(3, empUniqueId);
			    }else{
			    	insertStmt=  "INSERT INTO tblDepartmentOfLabor(emp_unique_id,stage20,stage20_status)VALUES"
			    		+"(?,?,?)";
			    	prepStmt = con.prepareStatement(insertStmt);
			        prepStmt.setString(1, empUniqueId);
			        prepStmt.setString(2, stage20ParmData + stage20UploadData);
			        prepStmt.setBoolean(3, true);
			    }
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Employee Department of Labor Stage20 details have been successfully inserted into database----------------->");
			          insertStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			      Debug.print("SQL Exception in GeneralDBAction.insertDOLStageTwentyDetails():" + sqe.getMessage()); 
			     sqe.printStackTrace();
			    }catch(Exception e){
			        releaseConnection();
			        Debug.print("General Exception  in GeneralDBAction.insertDOLStageTwentyDetails():" + e.getMessage());
			    }finally {
			      releaseConnection();
			    }
			return insertStatus;
		}
		
		public boolean checkEmployeeDOLExist(String empUniqueId) throws SQLException{
			Debug.print("GeneralDBAction checkEmployeeDOSExist");
		     boolean result = false;
		     //makeConnection();
			try {
		         String selectStatement = "SELECT emp_unique_id from tblDepartmentOfState WHERE emp_unique_id = ?" ;
		         PreparedStatement prepStmt = con.prepareStatement(selectStatement);
		         prepStmt.setString(1,empUniqueId);
		         ResultSet rs = prepStmt.executeQuery();
		         if (rs.next()){
		             result = true;
		         }
		         rs.close();
		         prepStmt.close();
		         //releaseConnection();
		     } 
		     catch (SQLException e) {
		         releaseConnection();
		         Debug.print("GeneralDBAction:: Could not find any from checkEmployeeDOSExist" + e.getMessage());
		     }
		     catch(Exception e){
		         releaseConnection();
		         Debug.print("General Exception  in GeneralDBAction checkEmployeeDOSExist:" + e.getMessage());
		     }
		     Debug.print("GeneralDBAction checkEmployeeDOSExist():" + result);
		     return result;
		}
		
		public boolean updatePostRequirementDetails(PostRequirementsBean objPostReq , String postReqUniqueId)
				throws RemoteException, SQLException{
			String jobPostId = null;
			jobPostId = selectJobPostId();
			boolean postJobStatus = false;
			try{


			    makeConnection(); 
			    String insertStmt=  "Update tblMFPostRequirements  SET jobPostId=?, requirement_id =?, uniqueJobPostCompanyId =?, jobPostCompanyId=?, jobPostCompanyName=?,jobPostUserId=?, jobPostUserName=?, jobPostUserRoleId=?, jobPostUserRoleName=?,"+
		            "ClientReferenceID=?, ReferenceID=?, ClientIndustry=?, PositionID=?, Position=?, InterviewProcess=?, VisaAccepted=?, Skills=?, JobType=?, LocationField=?, NumberOfOpenings=?, RecruiterEmail=?,"+
		            "StartDate=?, EndDate=?, RequirementStatus=?, Rate=?, Duration=?, LocalRequired=?, Salary=?, FlexiblityOnSalary=?, FlexiblityOnHrlyRate=?, DateOnHold=?, ExtraDocumentsRequired=?, minExperience=?,"+
		            "Notes=?, jobResponsibilites=?, JobTitle=?, jobPostCompanyCategory=?,maxExperience=?,jobPostDate=getdate() where post_req_uniqueId = ? ";
			      

			      prepStmt = con.prepareStatement(insertStmt);
			      
			      prepStmt.setString(1,jobPostId);
			      prepStmt.setString(2,objPostReq.getrequirementId());
			      prepStmt.setString(3,objPostReq.getuniqueJobPostCompanyId());
			      prepStmt.setString(4,objPostReq.getjobPostCompanyId());
			      prepStmt.setString(5,objPostReq.getjobPostCompanyName());
			      prepStmt.setString(6,objPostReq.getjobPostUserId());
			      prepStmt.setString(7,objPostReq.getjobPostUserName());
			      prepStmt.setString(8,objPostReq.getjobPostUserRoleId());
			      prepStmt.setString(9,objPostReq.getjobPostUserRoleName());
			      prepStmt.setString(10,objPostReq.getClientReferenceID());
			      prepStmt.setString(11,objPostReq.getReferenceID());
			      prepStmt.setString(12, objPostReq.getClientIndustry());
			      prepStmt.setString(13, objPostReq.getPositionID());
			      prepStmt.setString(14,objPostReq.getPosition());
			      prepStmt.setString(15,objPostReq.getInterviewProcess());
			      prepStmt.setString(16,objPostReq.getVisaAccepted());
			      prepStmt.setString(17,objPostReq.getSkills());
			      prepStmt.setString(18,objPostReq.getJobType());
			      prepStmt.setString(19,objPostReq.getLocationField());
			      prepStmt.setString(20,objPostReq.getNumberOfOpenings());
			      prepStmt.setString(21,objPostReq.getRecruiterEmail());
			      prepStmt.setString(22,objPostReq.getStartDate());
			      prepStmt.setString(23,objPostReq.getEndDate());
			      prepStmt.setString(24,objPostReq.getRequirementStatus());
//			      float rateInFloat = Float.parseFloat(objPostReq.getRate());
//			        System.out.println(rateInFloat);
			      prepStmt.setString(25,objPostReq.getRate());
			      prepStmt.setString(26,objPostReq.getDuration());
			      prepStmt.setString(27,objPostReq.getLocalRequired());
			      prepStmt.setString(28, objPostReq.getSalary());
			      prepStmt.setString(29, objPostReq.getFlexiblityOnSalary());
			      prepStmt.setString(30, objPostReq.getFlexiblityOnHrlyRate());
			      prepStmt.setString(31, objPostReq.getDateOnHold());
			      prepStmt.setString(32, objPostReq.getExtraDocumentsRequired());
			      //prepStmt.setString(33, objPostReq.getRequiredExperience());
			      prepStmt.setString(33, objPostReq.getminExperience());
			      prepStmt.setString(34, objPostReq.getNotes());
			      prepStmt.setString(35, objPostReq.getjobResponsibilites());
			      prepStmt.setString(36, objPostReq.getJobTitle());
			      prepStmt.setString(37, objPostReq.getjobPostCompanyCategory());
			      prepStmt.setString(38, objPostReq.getmaxExperience());
			      prepStmt.setString(39,postReqUniqueId);
			      
			      int cnt= prepStmt.executeUpdate();
			      if(cnt>0){
			          System.out.println("Post Requirements are successfully updated into database----------------->");
			          postJobStatus = true;
			      }
			       prepStmt.close();

			    } 
			    catch (SQLException sqe) {
			      releaseConnection();
			     sqe.printStackTrace();  
			     postJobStatus = false;
			    } finally {
			      releaseConnection();
			    }
			return postJobStatus;
		}

		

public String DeleteClientContactByUniqueId(String clientContactByUniqueId) {
	 Debug.print("GeneralDBAction.DeleteClientContactsByUniqueId():");
	    String clientContactDeleteStatus = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
		try {
	        String selectStatement = " DELETE FROM tblClientMasterDetails where unique_id=?";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, clientContactByUniqueId);
	        rs = prepStmt.executeQuery();
	      
	      
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        Debug.print("SQL Exception in GeneralDBAction.selectClientContactByUniqueId():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        Debug.print("General Exception  in GeneralDBAction.selectClientContactByUniqueId():" + e.getMessage());
	    }
	    return clientContactDeleteStatus;
	}

public ArrayList getCanDetailsByRequirementID(String reqid, String status , String availability,String experience,String location ) {
	Debug.print("GeneralDBAction.getCanDetailsByRequirementID():");
    ArrayList AppliedCandidateList = null;
   // PreparedStatement prepStmt = null;
    ResultSet rs = null;
    makeConnection();
    String selectStatement=null;
	try {
		System.out.println("redid in else"+reqid);
		System.out.println("status in else"+status);
		
         selectStatement = " SELECT FirstName,LastName,CandidateEMail,CandidateContactNumber,CurrentLocation,VisaTypeValue,VisaApprovalValue"+
	    ",FormI797AvailableValue,FormI94AvailableValue,RateInDollar,MiddleName,DateOfBirth"+
		",TotalExperience,ExperienceInUSA,ReLocationValue,Availability,PreferredLocation,ByWhomValue"+
	    ",Skills,BestTimeForTelephonicInterview,WillingnessForAnInPersonInterviewAtOwnExpenseValue,EmployerName"+
	    ",EmployerMailID,EmployerContactNumber,ContactPerson,RequirementId,unique_id,CANID,CandidateStatusValue"+
	    ",CandidateResumeLoc,user_id,can_company_uniqueid,can_companname,can_company_category,can_joined_status"+
	    ",can_marital_status from tblCandidateMaster where  CandidateStatusValue = ?";
        
        	      
		if(reqid!=null && reqid!="" && !reqid.equalsIgnoreCase("null") ){
			selectStatement = selectStatement +  " and RequirementId = '" + reqid+ "'";
			//selectStatement = selectStatement + "and RequirementId like %'" + reqid "'";
             Debug.print("Query reqid: " + selectStatement);
	        }
		if(availability!=null && availability!="" && !availability.equalsIgnoreCase("null")){
			selectStatement = selectStatement +  " and Availability = " + availability+  "'";
			//selectStatement = selectStatement + "and RequirementId like %'" + reqid "'";
             Debug.print("Query  availability: " + selectStatement);
	        }
		
		if(location!=null && location!="" && !location.equalsIgnoreCase("null") ){
			selectStatement = selectStatement +  " and PreferredLocation = " + location + "'";
			//selectStatement = selectStatement + "and RequirementId like %'" + reqid "'";
             Debug.print("Query  PreferredLocation: " + selectStatement);
	        }
		
         prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1,status);	
         
        rs = prepStmt.executeQuery();
        AppliedCandidateList = new ArrayList();
        while(rs.next()){
        	
        	String txtfirstname = rs.getString(1);
        	String txtlastname =  rs.getString(2);
        	String txtemailaddress = rs.getString(3);
        	String txtcontactnumber = rs.getString(4);
        	String txtcurrentlocation = rs.getString(5);
        	String drpvisatype = rs.getString(6);
        	String drpvisaapproval = rs.getString(7);
        	String drpi797available = rs.getString(8);
        	String drpI97available = rs.getString(9);
        	String txtrate = rs.getString(10);
        	String txtmiddlename = rs.getString(11);
        	String txtdateofbirth = rs.getString(12);
        	String txttotalexperience = rs.getString(13);
        	String txtexperienceinUSA = rs.getString(14);
        	String drprelocation = rs.getString(15);
        	String txtavailability = rs.getString( 16);
        	String txtpreferredlocation = rs.getString(17);
        	String drpbywhom = rs.getString(18);
        	String txtskills = rs.getString(19);
        	String txtbesttimefortelephonicinterview = rs.getString(20);
        	String drpwillinginperson = rs.getString(21);
        	String txtempname = rs.getString(22);
        	String txtempmailID = rs.getString(23);
        	String txtempcontactnumber = rs.getString(24);
        	String txtcontactperson = rs.getString(25);
        	String RID = rs.getString(26);
        	String unique_id = rs.getString(27);
        	String CANID = rs.getString(28);
        	String CandidateStatusValue = rs.getString(29);
        	String CandidateResumeLoc = rs.getString(30);
        	String appliedUserId = rs.getString(31);
        	String candidateCompanyUniqueId = rs.getString(32);
        	String candidateCompany = rs.getString(33);
        	String candidateCompanyCategory = rs.getString(34);
        	int canJoinedStatusInt = rs.getInt(35);
        	String candidateJoinedStatus = Integer.toString(canJoinedStatusInt);
        	String canMaritalStatus = rs.getString(36);
        	
            String[] CandidateList = {txtfirstname,
            		txtlastname, txtemailaddress, txtcontactnumber,
            		txtcurrentlocation, drpvisatype, drpvisaapproval,
            		drpi797available, drpI97available, txtrate,
            		txtmiddlename, txtdateofbirth, txttotalexperience,
            		txtexperienceinUSA, drprelocation,
            		txtavailability, txtpreferredlocation, drpbywhom,
            		txtskills, txtbesttimefortelephonicinterview,
            		drpwillinginperson, txtempname,
            		txtempmailID, txtempcontactnumber, txtcontactperson, RID, unique_id,
            		CANID, CandidateStatusValue, CandidateResumeLoc,appliedUserId,candidateCompanyUniqueId,
            		candidateCompany,candidateCompanyCategory,candidateJoinedStatus,canMaritalStatus};
            AppliedCandidateList.add(CandidateList);
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.getCanDetailsByUniqueId():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.getAllAppliedCandidateList():" + e.getMessage());
    }
    return AppliedCandidateList;

}


public boolean UpdateClientContact(ClientContactDetailsBean objClientContacts,String unique_id) {
	
	boolean clientContactStatus = false;

	try{


	    makeConnection(); 
	    String insertStmt=  "UPDATE  tblClientMasterDetails set  client_id=? ,client_name=? ,responsible_person =? ,"+
	    "client_email=?,client_company=? ,client_businessPhone=?  where unique_id=? ";
	      

	      prepStmt = con.prepareStatement(insertStmt);
	      prepStmt.setString(1,objClientContacts.getclient_id());
	      prepStmt.setString(2,objClientContacts.getclient_name());
	      prepStmt.setString(3, objClientContacts.getresponsible_person());
	      prepStmt.setString(4,objClientContacts.getclient_email());
	      prepStmt.setString(5,objClientContacts.getclient_company());
	     // prepStmt.setString(6,objClientContacts.getclient_jobTitle());
	      prepStmt.setString(6,objClientContacts.getclient_businessPhone());
	    //  prepStmt.setString(8,objClientContacts.getclient_contactId());
	      prepStmt.setString(7,unique_id);
	      	      
	      int cnt= prepStmt.executeUpdate();
	      if(cnt>0){
	          System.out.println("Client details are successfully Updated into database----------------->");
	          clientContactStatus = true;
	      }
	       prepStmt.close();

	    } 
	    catch (SQLException sqe) {
	      releaseConnection();
	     sqe.printStackTrace();  
	     clientContactStatus = true;
	    } finally {
	      releaseConnection();
	    }
	return clientContactStatus;
}

public ArrayList getAllUserEmailBasedOnRoleName(String roleName){
    Debug.print("GeneralDBAction.getAllUserEmailBasedOnRoleName():");
    ArrayList EmailIdList = null;
   
    makeConnection();
	try {
		String selectStatement = " SELECT A.role_id, B.user_id, C.email_id from tblRoleMaster A, tblMapUserPrivilege B, tblUserMaster C  where  C.category = ? and C.user_id = B.user_id and A.role_id = B.role_id  and A.role_name = ? " ;
		 prepStmt = con.prepareStatement(selectStatement);
		 prepStmt.setString(1,"Buyer");
        prepStmt.setString(2,roleName);
        
        rs = prepStmt.executeQuery();
        EmailIdList = new ArrayList();
        while(rs.next()){
            String role_id = rs.getString(1);
            String user_id = rs.getString(2);
            String email_id = rs.getString(3);
            
            String[] rlList = {role_id, user_id, email_id};
            EmailIdList.add(rlList);
           System.out.println("=========="+email_id);
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
      
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.getAllUserEmailBasedOnRoleName():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.getAllUserEmailBasedOnRoleName():" + e.getMessage());
    }
    return EmailIdList;
  }

public boolean UpdateVendorContact(VendorContactDetailsBean objVendorContacts,String unique_id) {
	
	boolean vendorContactStatus = false;
	try{
		 
	    makeConnection(); 
	    String insertStmt=   "UPDATE  tblVendorMasterDetails set  vendor_id=? ,vendor_fname=? ,vendor_lname =? ,"+
	    	    "vendor_email=?,vendor_company=? ,vendor_homePhone=?, vendor_businessPhone=?, vendor_contactPerson=? where unique_id=? ";
	      
	      

	      prepStmt = con.prepareStatement(insertStmt);
	      prepStmt.setString(1,objVendorContacts.getvendor_id());
	      prepStmt.setString(2,objVendorContacts.getvendor_fname());
	      prepStmt.setString(3, objVendorContacts.getvendor_lname());
	      prepStmt.setString(4,objVendorContacts.getvendor_email());
	      prepStmt.setString(5,objVendorContacts.getvendor_company());
	      prepStmt.setString(6,objVendorContacts.getvendor_homePhone());
	      prepStmt.setString(7,objVendorContacts.getvendor_businessPhone());
	      prepStmt.setString(8,objVendorContacts.getvendor_contactPerson());
	      prepStmt.setString(9,unique_id);
	      	      
	      int cnt= prepStmt.executeUpdate();
	      if(cnt>0){
	          System.out.println("Vendor details are successfully updated into database----------------->");
	          vendorContactStatus = true;
	      }
	       prepStmt.close();

	    } 
	    catch (SQLException sqe) {
	      releaseConnection();
	     sqe.printStackTrace();  
	     vendorContactStatus = true;
	    } finally {
	      releaseConnection();
	    }
	return vendorContactStatus;
}
public String DeleteVendorContactByUniqueId(String vendorContactByUniqueId) {
	 Debug.print("GeneralDBAction.DeleteClientContactsByUniqueId():");
	    String vendorContactDeleteStatus = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
		try {
	        String selectStatement = " DELETE FROM tblVendorMasterDetails where unique_id=?";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, vendorContactByUniqueId);
	        rs = prepStmt.executeQuery();
	      
	      
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        Debug.print("SQL Exception in GeneralDBAction.selectVendorContactByUniqueId():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        Debug.print("General Exception  in GeneralDBAction.selectVendorContactByUniqueId():" + e.getMessage());
	    }
	    return vendorContactDeleteStatus;
	}

public ArrayList getBuyerDetails() {
	
	Debug.print("GeneralDBAction.getBuyerDetails():");
    ArrayList buyerList = null;
    PreparedStatement prepStmt = null;
    ResultSet rs = null;
    makeConnection();
    
	try {
		
        String selectStatement = " SELECT * from tblMasterBuyerDetails where active_status=? ";
        
        prepStmt = con.prepareStatement(selectStatement);	        
        prepStmt.setString(1, "NotRegistered");
        rs = prepStmt.executeQuery();
        
        buyerList = new ArrayList();
        
        while(rs.next()){
        			System.out.println("while starts");
        			String sNo = rs.getString(1);
        			String clientName = rs.getString(2);
        			String website = rs.getString(3);
        			String contactPerson = rs.getString(4);
        			String emailId = rs.getString(5);
        			String contactPhone = rs.getString(6);
        			String fax = rs.getString(7);
        			
        			System.out.println(" in db method ===> sNo ---> "+sNo+" clientName ---> "+clientName
        					+" website ---> "+website+" contactPerson ---> "+contactPerson
        					+" emailId ---> "+emailId+" contactPhone ---> "+contactPhone
        					+" fax ---> "+fax);
        			
            String[] strList = {sNo, clientName, website, contactPerson, 
            		emailId, contactPhone, fax};
            buyerList.add(strList);
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.getBuyerDetails():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.getBuyerDetails():" + e.getMessage());
    }
    return buyerList;
    
}

public ArrayList getRegisteredBuyerDetails() {
	
	Debug.print("GeneralDBAction.getRegisteredBuyerDetails():");
    ArrayList registeredBuyerList = null;
    PreparedStatement prepStmt = null;
    ResultSet rs = null;
    makeConnection();
    
	try {
		
        String selectStatement = " SELECT * from tblMasterBuyerDetails where active_status=? ";
        
        prepStmt = con.prepareStatement(selectStatement);	        
        prepStmt.setString(1, "Registered");
        rs = prepStmt.executeQuery();
        
        registeredBuyerList = new ArrayList();
        
        while(rs.next()){
        			System.out.println("while starts");
        			String sNo = rs.getString(1);
        			String clientName = rs.getString(2);
        			String website = rs.getString(3);
        			String contactPerson = rs.getString(4);
        			String emailId = rs.getString(5);
        			String contactPhone = rs.getString(6);
        			String fax = rs.getString(7);
        			
        			System.out.println(" in db method ===> sNo ---> "+sNo+" clientName ---> "+clientName
        					+" website ---> "+website+" contactPerson ---> "+contactPerson
        					+" emailId ---> "+emailId+" contactPhone ---> "+contactPhone
        					+" fax ---> "+fax);
        			
            String[] strList = {sNo, clientName, website, contactPerson, 
            		emailId, contactPhone, fax};
            registeredBuyerList.add(strList);
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.getRegisteredBuyerDetails():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.getRegisteredBuyerDetails():" + e.getMessage());
    }
    return registeredBuyerList;
    
}

public String getUserIdbyCompany(String clientName)
{

String userid=null;

   
    try {
     makeConnection();
      String str="SELECT user_id FROM tblSignUpDetails WHERE institution_name=?";
            
      prepStmt = con.prepareStatement(str);
         prepStmt.setString(1,clientName);
                      
         rs = prepStmt.executeQuery();
         if (rs.next()){
          userid = rs.getString(1);
                       }
          
    
         rs.close();
    prepStmt.close();
    
    releaseConnection(); 
 } 
    catch (Exception ex) {
      ex.printStackTrace();
          //Debug.print("Exception:" + ex.getMessage());
     }finally {
         releaseConnection();
     }
   
return userid;
}

public String getAllVendorDetailsByEmail(String email_id) {
	 Debug.print("GeneralDBAction.getAllVendorDetailsByEmail():");
	    ArrayList vendorContactList = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    String vendor_email=null;
	    makeConnection();
		try {
	        String selectStatement = " SELECT vendor_email  from tblVendorMasterDetails where vendor_email =?";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, email_id);
	        rs = prepStmt.executeQuery();
	       
	        while(rs.next()){
	    		
	    		 vendor_email = rs.getString(1);
	    		
	    		
	            
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        Debug.print("SQL Exception in GeneralDBAction.getAllVendorDetailsByEmail():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        Debug.print("General Exception  in GeneralDBAction.getAllVendorDetailsByEmail():" + e.getMessage());
	    }
	    return vendor_email;
}


public boolean UpdateVendorByEmailID(VendorContactDetailsBean objVendorContacts) {
	
	boolean VendorContactStatus = false;

	try{


	    makeConnection(); 
	    String insertStmt=  "UPDATE  tblVendorMasterDetails set vendor_id = ?,vendor_fname = ?,vendor_lname = ?,"+
	    "vendor_email= ?,vendor_company = ?,vendor_homePhone = ?,vendor_businessPhone = ?,vendor_contactPerson= ? where vendor_email=? ";
	      

	      prepStmt = con.prepareStatement(insertStmt);
	      prepStmt.setString(1,objVendorContacts.getvendor_id());
	      prepStmt.setString(2,objVendorContacts.getvendor_fname());
	      prepStmt.setString(3, objVendorContacts.getvendor_lname());
	      prepStmt.setString(4,objVendorContacts.getvendor_email());
	      prepStmt.setString(5,objVendorContacts.getvendor_company());
	      prepStmt.setString(6,objVendorContacts.getvendor_homePhone());
	      prepStmt.setString(7,objVendorContacts.getvendor_businessPhone());
	      prepStmt.setString(8,objVendorContacts.getvendor_contactPerson());
	      
	      prepStmt.setString(9,objVendorContacts.getvendor_email());
	      	      
	      int cnt= prepStmt.executeUpdate();
	      if(cnt>0){
	          System.out.println("Vendor details are successfully Updated into database----------------->");
	          VendorContactStatus = true;
	      }
	       prepStmt.close();

	    } 
	    catch (SQLException sqe) {
	      releaseConnection();
	     sqe.printStackTrace();  
	     VendorContactStatus = false;
	    } finally {
	      releaseConnection();
	    }
	return VendorContactStatus;
}



public int countMasterBuyerDetails() {
    Debug.print("GeneralDBAction.getRole():");
   
    String strCount = null;
    int count = 0;
    makeConnection();
	try {

        String selectStatement="select Top 1 SNo from tblMasterBuyerDetails order by SNo DESC";

        prepStmt = con.prepareStatement(selectStatement);
        
        rs = prepStmt.executeQuery();
        if(rs.next()){
        	strCount = rs.getString(1);
            System.out.println("strCount in db --> "+strCount);
        }
        if(strCount != null){
        	float f = Float.parseFloat(strCount);
        	count = (int) f;
        }

        rs.close();
        prepStmt.close();
        releaseConnection();
        //Debug.print("GeneralDBAction.getRole():" + count);
    } 
    catch(SQLException sql){
        releaseConnection();
        //Debug.print("SQL Exception in GeneralDBAction.getRole():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.getRole():" + e.getMessage());
    }
    return count;
}   

public String checkBuyerEmail(String email) {
    Debug.print("GeneralDBAction.getRole():");
   
    String buyerEmail = null;
   
    makeConnection();
	try {

        String selectStatement="select E_MailID from tblMasterBuyerDetails where E_MailID=? ";

        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, email);
        
        rs = prepStmt.executeQuery();
        
        
        if(rs.next()){
        	buyerEmail = rs.getString(1);            
        }
        

        rs.close();
        prepStmt.close();
        releaseConnection();
        Debug.print("GeneralDBAction.getRole():" + buyerEmail);
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.getRole():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.getRole():" + e.getMessage());
    }
    return buyerEmail;
}  

public String insertBuyerEmail(int sNo, String clientName, String website, String contactPerson, String emailId, String phoneNo, String fax) {
    Debug.print("GeneralDBAction.insertBuyerEmail():");
    PreparedStatement prepStmt = null;
    String buyerInsertionStatus = null;
    makeConnection();
    try {
     
        String insertStatement = "insert into tblMasterBuyerDetails (SNo,CLIENT,Website,Contact_Person,E_MailID,Contact_Phone,FAX) " +
                " values(?,?,?,?,?,?,?)";
        
        prepStmt = con.prepareStatement(insertStatement);
       
        prepStmt.setInt(1, sNo);
        prepStmt.setString(2, clientName);
        prepStmt.setString(3, website);
        prepStmt.setString(4, contactPerson);
        prepStmt.setString(5, emailId);
        prepStmt.setString(6, phoneNo);
        prepStmt.setString(7, fax);
        
        //System.out.println("sno in db--> "+sNo);
		//System.out.println("emailId in db--> "+emailId);
        
        int cnt = prepStmt.executeUpdate();
        if(cnt>0){
        	buyerInsertionStatus = "Buyer Inserted successfully";
        }
        
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        //Debug.print("SQL Exception in GeneralDBAction.insertEntity():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.insertEntity():" + e.getMessage());
    }
    return buyerInsertionStatus;
} 


public boolean updateBuyerActiveStatus(String email) throws Exception {
    Debug.print("GeneralDBAction changePasword");
    int cnt = 0;
    
    try {
        makeConnection();
        
            String str = "update  tblMasterBuyerDetails set active_status = ?  WHERE E_MailID = ?";
            PreparedStatement prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, "Registered");
            prepStmt.setString(2, email);
                     
            cnt = prepStmt.executeUpdate();
          
            Debug.print("Successfully Buyer status updated......"+cnt);
            prepStmt.close();
        
    }catch (Exception e){
       
        releaseConnection();
        Debug.print("Error while updating buyer status  : "+e.getMessage());
    }finally {
        releaseConnection();
    }
    if (cnt >0)
        return true;
    else
        return false;
}

public String getUserIdByEmail(String email) {
    Debug.print("GeneralDBAction.getRole():");
   
    String buyerUserId = null;
   
    makeConnection();
	try {

        String selectStatement="select user_id from tblUserMaster where email_id=? ";

        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, email);
        
        rs = prepStmt.executeQuery();
        
        
        if(rs.next()){
        	buyerUserId = rs.getString(1);            
        }
        

        rs.close();
        prepStmt.close();
        releaseConnection();
        Debug.print("GeneralDBAction.getRole():" + buyerUserId);
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.getRole():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.getRole():" + e.getMessage());
    }
    return buyerUserId;
} 

public String[] getByuerUserLoginCredentials(String userID) {
    Debug.print("GeneralDBAction.getByuerUserLoginCredentials():");
   
    String buyerUserLogin[] = new String[7];
   
    makeConnection();
	try {

        String selectStatement="select first_name, last_name, login_name, password, email_id, company, category from tblUserMaster where user_id=? ";

        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, userID);
        
        rs = prepStmt.executeQuery();
        
        
        if(rs.next()){
        	
        	String first_name = rs.getString(1);
        	String last_name = rs.getString(2);
        	String login_name = rs.getString(3);
        	String password = rs.getString(4);
        	String email_id = rs.getString(5);
        	String company = rs.getString(6);
        	String category = rs.getString(7);
        	
        	buyerUserLogin[0] = first_name;
        	buyerUserLogin[1] = last_name;
        	buyerUserLogin[2] = login_name;
        	buyerUserLogin[3] = password;
        	buyerUserLogin[4] = email_id;
        	buyerUserLogin[5] = company;
        	buyerUserLogin[6] = category;
        	
        	System.out.println("first_name --> "+first_name+" last_name --> "+last_name);
        	System.out.println("login_name --> "+login_name+" password --> "+password);
        	System.out.println("email_id --> "+email_id+" company --> "+company);
        	System.out.println("category --> "+category);
        	
        }
        

        rs.close();
        prepStmt.close();
        releaseConnection();
        Debug.print("GeneralDBAction.getByuerUserLoginCredentials():" + buyerUserLogin);
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.getByuerUserLoginCredentials():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.getByuerUserLoginCredentials():" + e.getMessage());
    }
    return buyerUserLogin;
} 

public boolean updateBuyerContactID(String buyerUserId) throws Exception {
    Debug.print("GeneralDBAction changePasword");
    int cnt = 0;
    
    try {
        makeConnection();
        
            String str = "update  tblUserMaster set contact_type_id = ?  WHERE user_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, "0CC42D09-340C-42E8-B773-54BDF0688527");
            prepStmt.setString(2, buyerUserId);
                     
            cnt = prepStmt.executeUpdate();
          
            Debug.print("Successfully Buyer status updated......"+cnt);
            prepStmt.close();
        
    }catch (Exception e){
       
        releaseConnection();
        Debug.print("Error while updating buyer status  : "+e.getMessage());
    }finally {
        releaseConnection();
    }
    if (cnt >0)
        return true;
    else
        return false;
}

public String getUniqueEmployeeId(String userId2) {
	Debug.print("GeneralDBAction.getByuerUserLoginCredentials():");
	   
    String empUniqueId = null;
   
    makeConnection();
	try {

        String selectStatement="SELECT unique_id FROM tblEmployeeMasterDetails where user_id=?";

        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, userId2);
        
        rs = prepStmt.executeQuery();
        
        
        if(rs.next()){
        	empUniqueId = rs.getString(1);

        	System.out.println("empUniqueId --> "+empUniqueId);
        	
        }
        

        rs.close();
        prepStmt.close();
        releaseConnection();
        Debug.print("GeneralDBAction.getByuerUserLoginCredentials():" + empUniqueId);
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.getByuerUserLoginCredentials():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.getByuerUserLoginCredentials():" + e.getMessage());
    }
    return empUniqueId;
}

public ArrayList getEmployeePayrollDetails(String empUniqueId) {
	Debug.print("GeneralDBAction.getEmployeePayrollDetails():");
    ArrayList employeePayrollList = null;
    PreparedStatement prepStmt = null;
    ResultSet rs = null;
    makeConnection();
	try {
        String selectStatement = " SELECT unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name"
        		+",tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info"
        		+",tax_payperiod,replace(convert(varchar(10),tax_payduration_from,101), '/', '-') as tax_payduration_from,"
	        	+"replace(convert(varchar(10),tax_payduration_to,101), '/', '-') as tax_payduration_to,"
        		+"tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type"
        		+",gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type"
        		+",ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate"
        		+",ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd"
        		+",state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp"
        		+",after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay"
        		+",reg_date,payment_status from tblEmployeePayrollDetails where emp_unique_id = ? order by reg_date desc";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, empUniqueId);
        rs = prepStmt.executeQuery();
        employeePayrollList = new ArrayList();
        while(rs.next()){
        			String unique_payroll_id = rs.getString(1);
        			String emp_unique_id = rs.getString(2);
        			String emp_id = rs.getString(3);
        			String emp_ssn_no = rs.getString(4);
        			String emp_name = rs.getString(5);
        			String tax_year = rs.getString(6);
        			String tax_country = rs.getString(7);
        			String tax_state = rs.getString(8);
        			String fed_marital_status = rs.getString(9);
        			String fed_nof_allowance = rs.getString(10);
        			String state_wh_info = rs.getString(11);
        			String tax_payperiod = rs.getString(12);
        			String tax_payduration_from = rs.getString(13);
        			String tax_payduration_to = rs.getString(14);
        			String tax_payrate_type = rs.getString(15);
        			String if_overtime = rs.getString(16);
        			String gen_payrate = rs.getString(17);
        			String gen_payrate_type = rs.getString(18);
        			String gen_total_hours_worked = rs.getString(19);
        			String gen_pay_ytd = rs.getString(20);
        			String gen_pay_cp = rs.getString(21);
        			String ot_row_count = rs.getString(22);
        			String ot_payrate = rs.getString(23);
        			String ot_payrate_type = rs.getString(24);
        			String ot_total_hours_worked = rs.getString(25);
        			String ot_pay_ytd = rs.getString(26);
        			String ot_pay_cp = rs.getString(27);
        			String fed_pay_ytd = rs.getString(28);
        			String fed_pay_cp = rs.getString(29);
        			String ss_payrate = rs.getString(30);
        			String ss_annual_max = rs.getString(31);
        			String ss_pay_ytd = rs.getString(32);
        			String ss_pay_cp = rs.getString(33);
        			String mc_payrate = rs.getString(34);
        			String mc_pay_ytd = rs.getString(35);
        			String mc_pay_cp = rs.getString(36);
        			String state_pay_ytd = rs.getString(37);
        			String state_pay_cp = rs.getString(38);
        			String after_tax_income_pay = rs.getString(39);
        			String after_tax_income_ytd = rs.getString(40);
        			String after_tax_income_cp = rs.getString(41);
        			String after_tax_deduction_pay = rs.getString(42);
    	        	String after_tax_deduction_ytd = rs.getString(43);
    	        	String after_tax_deduction_cp = rs.getString(44);
    	        	String total_netpay = rs.getString(45);
    	        	String payment_status=rs.getString(47);
    		
            String[] strList = {unique_payroll_id,emp_unique_id,emp_id,emp_ssn_no,emp_name
            		,tax_year,tax_country,tax_state,fed_marital_status,fed_nof_allowance,state_wh_info
            		,tax_payperiod,tax_payduration_from,tax_payduration_to,tax_payrate_type,if_overtime,gen_payrate,gen_payrate_type
            		,gen_total_hours_worked,gen_pay_ytd,gen_pay_cp,ot_row_count,ot_payrate,ot_payrate_type
            		,ot_total_hours_worked,ot_pay_ytd,ot_pay_cp,fed_pay_ytd,fed_pay_cp,ss_payrate
            		,ss_annual_max,ss_pay_ytd,ss_pay_cp,mc_payrate,mc_pay_ytd,mc_pay_cp,state_pay_ytd
            		,state_pay_cp,after_tax_income_pay,after_tax_income_ytd,after_tax_income_cp
            		,after_tax_deduction_pay,after_tax_deduction_ytd,after_tax_deduction_cp,total_netpay,payment_status};
            employeePayrollList.add(strList);
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.getAllEmployeePayrollDetails():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.getAllEmployeePayrollDetails():" + e.getMessage());
    }
    return employeePayrollList;
    
}


private void updateContactDetails(HLCUserMaster objUserMaster, HLCContactDetails objContact, String contactTypeId)  throws SQLException {
    Debug.print("GeneralDBAction.updateContactDetails()");
    java.sql.Date jsqlD =  new java.sql.Date( new Date().getTime() );
   
   try {
    makeConnection();
    String updateStatement =
            "update tblContactDetails   set suite = ? , address1 = ? , address2 = ? , city = ? , " +
            "state = ? , country = ? ,zip = ? , phone_no = ? , mobile_no = ? , fax_no = ?   where   user_id = ? AND contact_type_id = ?";
        
    PreparedStatement prepStmt = con.prepareStatement(updateStatement);
    prepStmt.setString(1, objContact.getSuite());
    prepStmt.setString(2, objContact.getAddress1());
    prepStmt.setString(3, objContact.getAddress2());
    prepStmt.setString(4, objContact.getCity());
    prepStmt.setString(5, objContact.getState());
    prepStmt.setString(6, objContact.getCountry());
    prepStmt.setString(7, objContact.getZip());
    prepStmt.setString(8, objContact.getPhoneNo());
    prepStmt.setString(9, objContact.getMobileNo());
    prepStmt.setString(10, objContact.getFaxNo());
    /*prepStmt.setString(11, objUserMaster.getEmailId());
    prepStmt.setString(12, objUserMaster.getSecretQuestion());
    prepStmt.setString(13, objUserMaster.getSecretAnswer());
    prepStmt.setString(14, objUserMaster.getUserId());*/
    prepStmt.setString(11, objContact.getUserId());
    prepStmt.setString(12, contactTypeId);
    Debug.print("Update Data In Contact Details : \n"+objContact);
    int rowCount = prepStmt.executeUpdate();
    Debug.print("Contact Details Sucessfully Updated." + rowCount);
    prepStmt.close();
   }catch (Exception e){
        releaseConnection();
        Debug.print(" Error While Updating contact Details : "+e.getMessage());
   }finally {
        releaseConnection();
   }
}     
 
public void updateRowUserMaster(HLCUserMaster objUserMaster) throws SQLException {
    Debug.print("GeneralDBAction UpdateRowUserMaster");
    
    String secQes=objUserMaster.getSecretQuestion();
    String s1 = secQes;
    String s2 = "'";
    String s3 = "";
    String str1 = null;
    Debug.print(" Replace String : "+s1);
    if (s1 != null && s1.trim().length() > 0) {
        str1 = DBHelper.replace(s1,s2,s3);
        Debug.print(" Replace String : "+str1);
        int index = s1.indexOf("'");    
        if (index >= 0){
        	secQes = str1;
        }
    }
 
    try {
        makeConnection();
    String updateStatement =
            "update tblUserMaster set secret_question = ? , secret_answer = ? , email_id = ? , non_usea_mailing_status = ? , non_usea_email_status = ?  where   user_id = ?";
    
    System.out.println("Secret Question------>"+str1);
    PreparedStatement prepStmt = con.prepareStatement(updateStatement);
    prepStmt.setString(1,str1);
    prepStmt.setString(2,objUserMaster.getSecretAnswer());
    prepStmt.setString(3, objUserMaster.getEmailId());
    prepStmt.setBoolean(4, objUserMaster.nonUseaMailingStatus);
    prepStmt.setBoolean(5, objUserMaster.nonUseaEmailStatus);
    prepStmt.setString(6, objUserMaster.getUserId());
    
    
    int rowCount = prepStmt.executeUpdate();
    Debug.print("User Details Sucessfully Updated." + rowCount);
    prepStmt.close();
    
    }catch (Exception e){
        releaseConnection();
        Debug.print(" Error While Updating user Details : "+e.getMessage());
   }finally {
        releaseConnection();
   }
}  

public ArrayList getAllUserMasterDetails(String company) throws SQLException {
	ArrayList ListAllRegisteredUsers = new ArrayList();
    Debug.print(" getAllUserMasterDetails --->");
    try {
        makeConnection();
        
        String selectStatement = "SELECT user_id,login_name,user_code,membership_type_id,user_type_id,contact_type_id,"+
        "prefix,first_name,middle_name,last_name,sufix,dob,gender,email_id,citizenship,citizenship_id,password,"+
        "secret_question,secret_answer,register_date,login_date,active_status,non_usea_mailing_status,non_usea_email_status,"+
        "address_status,spl_notes,request_status,category FROM tblUserMaster where company=? order by register_date desc";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, company);
        rs = prepStmt.executeQuery();
        while (rs.next()) {
        	String user_id = rs.getString("user_id");
        	String userName = rs.getString("login_name");
        	String userCode = rs.getString("user_code");
        	String membership_type_id = rs.getString("membership_type_id");
        	String user_type_id = rs.getString("user_type_id");
        	String contact_type_id = rs.getString("contact_type_id");
        	String prefix =  rs.getString("prefix");
        	String first_name = rs.getString("first_name");
        	String last_name = rs.getString("last_name");
        	String sufix = rs.getString("sufix");
        	String dob = rs.getString("dob");
        	String active_status = rs.getString("active_status");
        	String email_id = rs.getString("email_id");
        	String register_date = rs.getString("register_date");
        	String secret_question = rs.getString("secret_question");
        	String secret_answer = rs.getString("secret_answer");
        	String category = rs.getString("category");
        	String request_status = rs.getString("request_status");
        	
            String UsersList[] = {user_id,userName,first_name,last_name,email_id,company,category,userCode,register_date,active_status,request_status,membership_type_id
            		,user_type_id,contact_type_id,prefix,sufix,dob,secret_question,secret_answer};
            		
            ListAllRegisteredUsers.add(UsersList);
        }
        
        prepStmt.close();
        
    } catch (SQLException sqe) {
        releaseConnection();
        Debug.print("Error while getting User Id : "+sqe.getMessage());
        //sqe.printStackTrace();
    } finally {
        releaseConnection();
    }
    return ListAllRegisteredUsers;
}

public boolean insertHotListCandidate(String appliedUserId, String candidateName,
		String txtlastname, String txtemailaddress, String txtcontactnumber,
		String txtcurrentlocation, String drpvisaapproval, String drpvisatype,
		String drpi797available, String drpI97available, String txtrate,
		String txtmiddlename, String txtdateofbirth, String txttotalexperience,
		String txtexperienceinUSA, String drprelocation,
		String txtavailability, String txtpreferredlocation, String hotlistAvl,
		String txtskills, String txtbesttimefortelephonicinterview,
		String drltime, String drpwillinginperson, String txtempname,
		String txtempmailID, String txtempcontactnumber, String txtcontactperson, String RID,
		String candidateCompanyUniqueId,String candidateCompany,String candidateCompanyCategory, 
		String fileLocation, String txtempcompany) throws RemoteException {

	boolean insertStatus = false;
	try{

	    makeConnection(); 
	    String insertStmt=  "INSERT INTO tblHotlistCandidate(FirstName,LastName,CandidateEMail,CandidateContactNumber,CurrentLocation,VisaApprovalValue,VisaTypeValue"+
	    	    ",FormI797AvailableValue,FormI94AvailableValue,RateInDollar,MiddleName,DateOfBirth"+
	    		",TotalExperience,ExperienceInUSA,ReLocationValue,Availability,PreferredLocation,hotlistAvl"+
	    	    ",Skills,BestDateForTelephonicInterview,BestTimeForTelephonicInterview,WillingnessForAnInPersonInterviewAtOwnExpenseValue,EmployerName"+
	    		",EmployerMailID,EmployerContactNumber,ContactPerson,CANID,user_id,can_company_uniqueid,"+
	    	    "can_companname,can_company_category,CandidateResumeLoc,EmployerCompany)"+
	    	    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	      

	      prepStmt = con.prepareStatement(insertStmt);
	      
	      prepStmt.setString(1,candidateName);
	      prepStmt.setString(2,txtlastname);
	      prepStmt.setString(3,txtemailaddress);
	      prepStmt.setString(4,txtcontactnumber);
	      prepStmt.setString(5,txtcurrentlocation);
	      prepStmt.setString(6,drpvisaapproval);
	      prepStmt.setString(7,drpvisatype);
	      prepStmt.setString(8,drpi797available);
	      prepStmt.setString(9,drpI97available);
	      prepStmt.setString(10,txtrate);
	      prepStmt.setString(11,txtmiddlename);
	      prepStmt.setString(12,txtdateofbirth);
	      prepStmt.setString(13,txttotalexperience);
	      prepStmt.setString(14,txtexperienceinUSA);
	      prepStmt.setString(15,drprelocation);
	      prepStmt.setString(16,txtavailability);
	      prepStmt.setString(17,txtpreferredlocation);
	      prepStmt.setString(18,hotlistAvl);
	      prepStmt.setString(19,txtskills);
	      prepStmt.setString(20,txtbesttimefortelephonicinterview);
	      prepStmt.setString(21,drltime);
	      prepStmt.setString(22,drpwillinginperson);
	      prepStmt.setString(23,txtempname);
	      prepStmt.setString(24,txtempmailID);
	      prepStmt.setString(25,txtempcontactnumber);
	      prepStmt.setString(26,txtcontactperson);
	      //prepStmt.setString(26,RID);
	      prepStmt.setString(27,selectHotlistCandidateId());
	      prepStmt.setString(28,appliedUserId);
	      prepStmt.setString(29, candidateCompanyUniqueId);
	      prepStmt.setString(30, candidateCompany);
	      prepStmt.setString(31, candidateCompanyCategory);
	      //prepStmt.setString(32, canMaritalStatus);
	      prepStmt.setString(32, fileLocation);
	      prepStmt.setString(33, txtempcompany);

	      
	      
	      
	      int cnt= prepStmt.executeUpdate();
	      if(cnt>0){
	          System.out.println("Candidaten details have been successfully inserted into database----------------->");
	          insertStatus = true;
	      }
	       prepStmt.close();

	    } 
	    catch (SQLException sqe) {
	      releaseConnection();
	      
	     System.err.println("error in insertQBEmployeeDetails------------------->"+sqe.getMessage()); 
	     sqe.printStackTrace();
	     //insertStatus = true;
	    } finally {
	      releaseConnection();
	    }
	return insertStatus;
	
}

public boolean updateHotListCandidate(String appliedUserId, String candidateName,
		String txtlastname, String txtemailaddress, String txtcontactnumber,
		String txtcurrentlocation, String drpvisaapproval, String drpvisatype,
		String drpi797available, String drpI97available, String txtrate,
		String txtmiddlename, String txtdateofbirth, String txttotalexperience,
		String txtexperienceinUSA, String drprelocation,
		String txtavailability, String txtpreferredlocation, String hotlistAvl,
		String txtskills, String txtbesttimefortelephonicinterview,
		String drltime, String drpwillinginperson, String txtempname,
		String txtempmailID, String txtempcontactnumber, String txtcontactperson, String RID,
		String candidateCompanyUniqueId,String candidateCompany,String candidateCompanyCategory, 
		String fileLocation, String uniqueId, String employerCompany) throws RemoteException {

	boolean insertStatus = false;
	try{

	    makeConnection(); 
	    String insertStmt=  "update tblHotlistCandidate set FirstName=?,LastName=?,CandidateEMail=?,CandidateContactNumber=?,CurrentLocation=?,VisaApprovalValue=?,VisaTypeValue=?"+
	    	    ",FormI797AvailableValue=?,FormI94AvailableValue=?,RateInDollar=?,MiddleName=?,DateOfBirth=?"+
	    		",TotalExperience=?,ExperienceInUSA=?,ReLocationValue=?,Availability=?,PreferredLocation=?,hotlistAvl=?"+
	    	    ",Skills=?,BestDateForTelephonicInterview=?,BestTimeForTelephonicInterview=?,WillingnessForAnInPersonInterviewAtOwnExpenseValue=?,EmployerName=?"+
	    		",EmployerMailID=?,EmployerContactNumber=?,ContactPerson=?,user_id=?,can_company_uniqueid=?,"+
	    	    "can_companname=?,can_company_category=?,CandidateResumeLoc=?,EmployerCompany=? where unique_id=?";
	      

	      prepStmt = con.prepareStatement(insertStmt);
	      
	      prepStmt.setString(1,candidateName);
	      prepStmt.setString(2,txtlastname);
	      prepStmt.setString(3,txtemailaddress);
	      prepStmt.setString(4,txtcontactnumber);
	      prepStmt.setString(5,txtcurrentlocation);
	      prepStmt.setString(6,drpvisaapproval);
	      prepStmt.setString(7,drpvisatype);
	      prepStmt.setString(8,drpi797available);
	      prepStmt.setString(9,drpI97available);
	      prepStmt.setString(10,txtrate);
	      prepStmt.setString(11,txtmiddlename);
	      prepStmt.setString(12,txtdateofbirth);
	      prepStmt.setString(13,txttotalexperience);
	      prepStmt.setString(14,txtexperienceinUSA);
	      prepStmt.setString(15,drprelocation);
	      prepStmt.setString(16,txtavailability);
	      prepStmt.setString(17,txtpreferredlocation);
	      prepStmt.setString(18,hotlistAvl);
	      prepStmt.setString(19,txtskills);
	      prepStmt.setString(20,txtbesttimefortelephonicinterview);
	      prepStmt.setString(21,drltime);
	      prepStmt.setString(22,drpwillinginperson);
	      prepStmt.setString(23,txtempname);
	      prepStmt.setString(24,txtempmailID);
	      prepStmt.setString(25,txtempcontactnumber);
	      prepStmt.setString(26,txtcontactperson);
	      //prepStmt.setString(26,RID);
	      //prepStmt.setString(27,selectHotlistCandidateId());
	      prepStmt.setString(27,appliedUserId);
	      prepStmt.setString(28, candidateCompanyUniqueId);
	      prepStmt.setString(29, candidateCompany);
	      prepStmt.setString(30, candidateCompanyCategory);
	      //prepStmt.setString(32, canMaritalStatus);
	      prepStmt.setString(31, fileLocation);
	      prepStmt.setString(32, employerCompany);
	      prepStmt.setString(33, uniqueId);

	      
	      
	      
	      int cnt= prepStmt.executeUpdate();
	      if(cnt>0){
	          System.out.println("Candidaten details have been successfully inserted into database----------------->");
	          insertStatus = true;
	      }
	       prepStmt.close();

	    } 
	    catch (SQLException sqe) {
	      releaseConnection();
	      
	     System.err.println("error in insertQBEmployeeDetails------------------->"+sqe.getMessage()); 
	     sqe.printStackTrace();
	     //insertStatus = true;
	    } finally {
	      releaseConnection();
	    }
	return insertStatus;
	
}

public ArrayList getAllHotlistCandidateListByuniqueJobPostCompanyId(String userId) {
	 Debug.print("GeneralDBAction.getAllHotlistCandidateListByUserId():");
	    ArrayList AppliedCandidateList = null;
	    PreparedStatement prepStmt = null;
	    ResultSet rs = null;
	    makeConnection();
		try {
	        String selectStatement = " SELECT FirstName,LastName,CandidateEMail,CandidateContactNumber,CurrentLocation,VisaTypeValue,VisaApprovalValue"+
   	    ",FormI797AvailableValue,FormI94AvailableValue,RateInDollar,MiddleName,DateOfBirth"+
   		",TotalExperience,ExperienceInUSA,ReLocationValue,Availability,PreferredLocation,hotlistAvl"+
   	    ",Skills,BestDateForTelephonicInterview,BestTimeForTelephonicInterview,WillingnessForAnInPersonInterviewAtOwnExpenseValue,EmployerName"+
   		",EmployerMailID,EmployerContactNumber,ContactPerson,unique_id,CANID,CandidateStatusValue"+
   	    ",CandidateResumeLoc,user_id,can_company_uniqueid,can_companname,can_company_category,can_joined_status"+
   		",EmployerCompany from tblHotlistCandidate where can_company_uniqueid=? order by reg_date";
	        prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, userId);
	        rs = prepStmt.executeQuery();
	        AppliedCandidateList = new ArrayList();
	        while(rs.next()){
	        	
	        	String txtfirstname = rs.getString(1);
	        	String txtlastname =  rs.getString(2);
	        	String txtemailaddress = rs.getString(3);
	        	String txtcontactnumber = rs.getString(4);
	        	String txtcurrentlocation = rs.getString(5);
	        	String drpvisatype = rs.getString(6);
	        	String drpvisaapproval = rs.getString(7);
	        	String drpi797available = rs.getString(8);
	        	String drpI97available = rs.getString(9);
	        	String txtrate = rs.getString(10);
	        	String txtmiddlename = rs.getString(11);
	        	String txtdateofbirth = rs.getString(12);
	        	String txttotalexperience = rs.getString(13);
	        	String txtexperienceinUSA = rs.getString(14);
	        	String drprelocation = rs.getString(15);
	        	String txtavailability = rs.getString( 16);
	        	String txtpreferredlocation = rs.getString(17);
	        	String hotlistAvl = rs.getString(18);
	        	String txtskills = rs.getString(19);
	        	String txtbestDatefortelephonicinterview = rs.getString(20);
	        	String txtbestTimefortelephonicinterview = rs.getString(21);
	        	String drpwillinginperson = rs.getString(22);
	        	String txtempname = rs.getString(23);
	        	String txtempmailID = rs.getString(24);
	        	String txtempcontactnumber = rs.getString(25);
	        	String txtcontactperson = rs.getString(26);
	        	//String RID = rs.getString(26);
	        	String unique_id = rs.getString(27);
	        	String CANID = rs.getString(28);
	        	String CandidateStatusValue = rs.getString(29);
	        	String CandidateResumeLoc = rs.getString(30);
	        	String appliedUserId = rs.getString(31);
	        	String candidateCompanyUniqueId = rs.getString(32);
	        	String candidateCompany = rs.getString(33);
	        	String candidateCompanyCategory = rs.getString(34);
	        	int canJoinedStatusInt = rs.getInt(35);
	        	String candidateJoinedStatus = Integer.toString(canJoinedStatusInt);
	        	String employerCompany = rs.getString(36);
	        	
	            String[] CandidateList = {txtfirstname,
	            		txtlastname, txtemailaddress, txtcontactnumber,
	            		txtcurrentlocation, drpvisatype, drpvisaapproval,
	            		drpi797available, drpI97available, txtrate,
	            		txtmiddlename, txtdateofbirth, txttotalexperience,
	            		txtexperienceinUSA, drprelocation,
	            		txtavailability, txtpreferredlocation, hotlistAvl,
	            		txtskills, txtbestDatefortelephonicinterview,txtbestTimefortelephonicinterview,
	            		drpwillinginperson, txtempname,
	            		txtempmailID, txtempcontactnumber, txtcontactperson, unique_id,
	            		CANID,CandidateStatusValue,CandidateResumeLoc,appliedUserId,candidateCompanyUniqueId,
	            		candidateCompany,candidateCompanyCategory,candidateJoinedStatus,employerCompany};
	            AppliedCandidateList.add(CandidateList);
	        }
	        rs.close();
	        prepStmt.close();
	        releaseConnection();
	    } 
	    catch(SQLException sql){
	        releaseConnection();
	        Debug.print("SQL Exception in GeneralDBAction.getAllAppliedCandidateListByUserId():" + sql.getMessage());
	    }
	    catch(Exception e){
	        releaseConnection();
	        Debug.print("General Exception  in GeneralDBAction.getAllAppliedCandidateListByUserId():" + e.getMessage());
	    }
	    return AppliedCandidateList;
}

public ArrayList getHotlistCanDetailsByUniqueId(String canDetailsByUniqueId) {
	Debug.print("GeneralDBAction.getHotlistCanDetailsByUniqueId():");
    ArrayList AppliedCandidateList = null;
    PreparedStatement prepStmt = null;
    ResultSet rs = null;
    makeConnection();
	try {
        String selectStatement = "SELECT FirstName,LastName,CandidateEMail,CandidateContactNumber,CurrentLocation,VisaTypeValue,VisaApprovalValue"+
   	    ",FormI797AvailableValue,FormI94AvailableValue,RateInDollar,MiddleName,DateOfBirth"+
   		",TotalExperience,ExperienceInUSA,ReLocationValue,Availability,PreferredLocation,hotlistAvl"+
   	    ",Skills,BestDateForTelephonicInterview,BestTimeForTelephonicInterview,WillingnessForAnInPersonInterviewAtOwnExpenseValue,EmployerName"+
   		",EmployerMailID,EmployerContactNumber,ContactPerson,unique_id,CANID,CandidateStatusValue"+
   	    ",CandidateResumeLoc,user_id,can_company_uniqueid,can_companname,can_company_category,can_joined_status"+
   		",EmployerCompany from tblHotlistCandidate where unique_id=? order by reg_date";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, canDetailsByUniqueId);
        rs = prepStmt.executeQuery();
        AppliedCandidateList = new ArrayList();
        while(rs.next()){
        	
        	String txtfirstname = rs.getString(1);
        	String txtlastname =  rs.getString(2);
        	String txtemailaddress = rs.getString(3);
        	String txtcontactnumber = rs.getString(4);
        	String txtcurrentlocation = rs.getString(5);
        	String drpvisatype = rs.getString(6);
        	String drpvisaapproval = rs.getString(7);
        	String drpi797available = rs.getString(8);
        	String drpI97available = rs.getString(9);
        	String txtrate = rs.getString(10);
        	String txtmiddlename = rs.getString(11);
        	String txtdateofbirth = rs.getString(12);
        	String txttotalexperience = rs.getString(13);
        	String txtexperienceinUSA = rs.getString(14);
        	String drprelocation = rs.getString(15);
        	String txtavailability = rs.getString( 16);
        	String txtpreferredlocation = rs.getString(17);
        	String hotlistAvl = rs.getString(18);
        	String txtskills = rs.getString(19);
        	String txtbestDatefortelephonicinterview = rs.getString(20);
        	String txtbestTimefortelephonicinterview = rs.getString(21);
        	String drpwillinginperson = rs.getString(22);
        	String txtempname = rs.getString(23);
        	String txtempmailID = rs.getString(24);
        	String txtempcontactnumber = rs.getString(25);
        	String txtcontactperson = rs.getString(26);
        	//String RID = rs.getString(26);
        	String unique_id = rs.getString(27);
        	String CANID = rs.getString(28);
        	String CandidateStatusValue = rs.getString(29);
        	String CandidateResumeLoc = rs.getString(30);
        	String appliedUserId = rs.getString(31);
        	String candidateCompanyUniqueId = rs.getString(32);
        	String candidateCompany = rs.getString(33);
        	String candidateCompanyCategory = rs.getString(34);
        	int canJoinedStatusInt = rs.getInt(35);
        	String candidateJoinedStatus = Integer.toString(canJoinedStatusInt);
        	String employerCompany = rs.getString(36);
        	
            String[] CandidateList = {txtfirstname,
            		txtlastname, txtemailaddress, txtcontactnumber,
            		txtcurrentlocation, drpvisatype, drpvisaapproval,
            		drpi797available, drpI97available, txtrate,
            		txtmiddlename, txtdateofbirth, txttotalexperience,
            		txtexperienceinUSA, drprelocation,
            		txtavailability, txtpreferredlocation, hotlistAvl,
            		txtskills, txtbestDatefortelephonicinterview,txtbestTimefortelephonicinterview,
            		drpwillinginperson, txtempname,
            		txtempmailID, txtempcontactnumber, txtcontactperson, unique_id,
            		CANID,CandidateStatusValue,CandidateResumeLoc,appliedUserId,candidateCompanyUniqueId,
            		candidateCompany,candidateCompanyCategory,candidateJoinedStatus,employerCompany};
            AppliedCandidateList.add(CandidateList);
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.getHotlistCanDetailsByUniqueId():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.getHotlistCanDetailsByUniqueId():" + e.getMessage());
    }
    return AppliedCandidateList;

}

public ArrayList SearchHotlistCanDetailsByParameters(String minExp,String maxExp,String visaType,String fromRate,String toRate) {
	Debug.print("GeneralDBAction.SearchHotlistCanDetailsByParameters():");
    ArrayList AppliedCandidateList = null;
    PreparedStatement prepStmt = null;
    ResultSet rs = null;
    makeConnection();
    
    String selectStatement = null;
	try {
		
		if((minExp != null && maxExp != null && visaType != null && fromRate != null && toRate != null) &&
				(minExp != "" && maxExp != "" && visaType != "" && fromRate != "" && toRate != "") &&
				(minExp != "null" && maxExp != "null" && visaType != "null" && fromRate != "null" && toRate != "null") &&
				(!(minExp.equalsIgnoreCase("")) && !(maxExp.equalsIgnoreCase("")) && !(visaType.equalsIgnoreCase(""))
				&& !(fromRate.equalsIgnoreCase("")) && !(toRate.equalsIgnoreCase(""))) &&
				(!(minExp.equalsIgnoreCase("null")) && !(maxExp.equalsIgnoreCase("null"))&& !(visaType.equalsIgnoreCase("null"))
				&& !(fromRate.equalsIgnoreCase("null")) && !(toRate.equalsIgnoreCase("null")))){
			
			System.out.println("inside if -------1------------->all parameters");
			
        selectStatement = "SELECT FirstName,LastName,CandidateEMail,CandidateContactNumber,CurrentLocation,VisaTypeValue,VisaApprovalValue"+
   	    ",FormI797AvailableValue,FormI94AvailableValue,RateInDollar,MiddleName,DateOfBirth"+
   		",TotalExperience,ExperienceInUSA,ReLocationValue,Availability,PreferredLocation,hotlistAvl"+
   	    ",Skills,BestDateForTelephonicInterview,BestTimeForTelephonicInterview,WillingnessForAnInPersonInterviewAtOwnExpenseValue,EmployerName"+
   		",EmployerMailID,EmployerContactNumber,ContactPerson,unique_id,CANID,CandidateStatusValue"+
   	    ",CandidateResumeLoc,user_id,can_company_uniqueid,can_companname,can_company_category,can_joined_status"+
   		",EmployerCompany from tblHotlistCandidate where (TotalExperience between ? and ?) and (VisaTypeValue=?) and (RateInDollar between ? and ?) order by reg_date desc";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, minExp);
        prepStmt.setString(2, maxExp);
        prepStmt.setString(3, visaType);
        prepStmt.setString(4, fromRate);
        prepStmt.setString(5, toRate);
		}else if((minExp != null && maxExp != null && visaType != null) &&
				(minExp != "" && maxExp != "" && visaType != "") &&
				(minExp != "null" && maxExp != "null" && visaType != "null") &&
				(!(minExp.equalsIgnoreCase("")) && !(maxExp.equalsIgnoreCase("")) && !(visaType.equalsIgnoreCase(""))) &&
				(!(minExp.equalsIgnoreCase("null")) && !(maxExp.equalsIgnoreCase("null"))&& !(visaType.equalsIgnoreCase("null")))){
			
			System.out.println("inside if -------2------------->only experience and visa type");
			
			selectStatement = "SELECT FirstName,LastName,CandidateEMail,CandidateContactNumber,CurrentLocation,VisaTypeValue,VisaApprovalValue"+
   	    ",FormI797AvailableValue,FormI94AvailableValue,RateInDollar,MiddleName,DateOfBirth"+
   		",TotalExperience,ExperienceInUSA,ReLocationValue,Availability,PreferredLocation,hotlistAvl"+
   	    ",Skills,BestDateForTelephonicInterview,BestTimeForTelephonicInterview,WillingnessForAnInPersonInterviewAtOwnExpenseValue,EmployerName"+
   		",EmployerMailID,EmployerContactNumber,ContactPerson,unique_id,CANID,CandidateStatusValue"+
   	    ",CandidateResumeLoc,user_id,can_company_uniqueid,can_companname,can_company_category,can_joined_status"+
   		",EmployerCompany from tblHotlistCandidate where (TotalExperience between ? and ?) and (VisaTypeValue=?) order by reg_date desc";
			prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, minExp);
	        prepStmt.setString(2, maxExp);
	        prepStmt.setString(3, visaType);
	        
		}else if((minExp != null && maxExp != null) &&
				(minExp != "" && maxExp != "") &&
				(minExp != "null" && maxExp != "null") &&
				(!(minExp.equalsIgnoreCase("")) && !(maxExp.equalsIgnoreCase(""))) &&
				(!(minExp.equalsIgnoreCase("null")) && !(maxExp.equalsIgnoreCase("null")))){
			
			System.out.println("inside if -------3------------->only experience");
			
			selectStatement = "SELECT FirstName,LastName,CandidateEMail,CandidateContactNumber,CurrentLocation,VisaTypeValue,VisaApprovalValue"+
   	    ",FormI797AvailableValue,FormI94AvailableValue,RateInDollar,MiddleName,DateOfBirth"+
   		",TotalExperience,ExperienceInUSA,ReLocationValue,Availability,PreferredLocation,hotlistAvl"+
   	    ",Skills,BestDateForTelephonicInterview,BestTimeForTelephonicInterview,WillingnessForAnInPersonInterviewAtOwnExpenseValue,EmployerName"+
   		",EmployerMailID,EmployerContactNumber,ContactPerson,unique_id,CANID,CandidateStatusValue"+
   	    ",CandidateResumeLoc,user_id,can_company_uniqueid,can_companname,can_company_category,can_joined_status"+
   		",EmployerCompany from tblHotlistCandidate where (TotalExperience between ? and ?) order by reg_date desc";
			prepStmt = con.prepareStatement(selectStatement);
	        prepStmt.setString(1, minExp);
	        prepStmt.setString(2, maxExp);
	        
		}else if((visaType != null && fromRate != null && toRate != null) &&
				(visaType != "" && fromRate != "" && toRate != "") &&
				(visaType != "null" && fromRate != "null" && toRate != "null") &&
				(!(visaType.equalsIgnoreCase(""))
				&& !(fromRate.equalsIgnoreCase("")) && !(toRate.equalsIgnoreCase(""))) &&
				(!(visaType.equalsIgnoreCase("null"))
				&& !(fromRate.equalsIgnoreCase("null")) && !(toRate.equalsIgnoreCase("null")))){
			
			System.out.println("inside if -------4------------->only rate and visa type");
			
			selectStatement = "SELECT FirstName,LastName,CandidateEMail,CandidateContactNumber,CurrentLocation,VisaTypeValue,VisaApprovalValue"+
   	    ",FormI797AvailableValue,FormI94AvailableValue,RateInDollar,MiddleName,DateOfBirth"+
   		",TotalExperience,ExperienceInUSA,ReLocationValue,Availability,PreferredLocation,hotlistAvl"+
   	    ",Skills,BestDateForTelephonicInterview,BestTimeForTelephonicInterview,WillingnessForAnInPersonInterviewAtOwnExpenseValue,EmployerName"+
   		",EmployerMailID,EmployerContactNumber,ContactPerson,unique_id,CANID,CandidateStatusValue"+
   	    ",CandidateResumeLoc,user_id,can_company_uniqueid,can_companname,can_company_category,can_joined_status"+
   		",EmployerCompany from tblHotlistCandidate where (VisaTypeValue=?) and (RateInDollar between ? and ?) order by reg_date desc";
			        prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, visaType);
			        prepStmt.setString(2, fromRate);
			        prepStmt.setString(3, toRate);
			        
		}else if((minExp != null && maxExp != null && fromRate != null && toRate != null) &&
				(minExp != "" && maxExp != "" && fromRate != "" && toRate != "") &&
				(minExp != "null" && maxExp != "null" && fromRate != "null" && toRate != "null") &&
				(!(minExp.equalsIgnoreCase("")) && !(maxExp.equalsIgnoreCase("")) && !(fromRate.equalsIgnoreCase("")) && !(toRate.equalsIgnoreCase(""))) &&
				(!(minExp.equalsIgnoreCase("null")) && !(maxExp.equalsIgnoreCase("null"))&& !(fromRate.equalsIgnoreCase("null")) && !(toRate.equalsIgnoreCase("null")))){
			
			System.out.println("inside if -------5------------->only experience and rate");
			
			selectStatement = "SELECT FirstName,LastName,CandidateEMail,CandidateContactNumber,CurrentLocation,VisaTypeValue,VisaApprovalValue"+
   	    ",FormI797AvailableValue,FormI94AvailableValue,RateInDollar,MiddleName,DateOfBirth"+
   		",TotalExperience,ExperienceInUSA,ReLocationValue,Availability,PreferredLocation,hotlistAvl"+
   	    ",Skills,BestDateForTelephonicInterview,BestTimeForTelephonicInterview,WillingnessForAnInPersonInterviewAtOwnExpenseValue,EmployerName"+
   		",EmployerMailID,EmployerContactNumber,ContactPerson,unique_id,CANID,CandidateStatusValue"+
   	    ",CandidateResumeLoc,user_id,can_company_uniqueid,can_companname,can_company_category,can_joined_status"+
   		",EmployerCompany from tblHotlistCandidate where (TotalExperience between ? and ?) and (RateInDollar between ? and ?) order by reg_date desc";
			        prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, minExp);
			        prepStmt.setString(2, maxExp);
			        prepStmt.setString(3, fromRate);
			        prepStmt.setString(4, toRate);
			        
		}else if((fromRate != null && toRate != null) &&
				(fromRate != "" && toRate != "") &&
				(fromRate != "null" && toRate != "null") &&
				(!(fromRate.equalsIgnoreCase("")) && !(toRate.equalsIgnoreCase(""))) &&
				(!(fromRate.equalsIgnoreCase("null")) && !(toRate.equalsIgnoreCase("null")))){
			
			System.out.println("inside if -------6------------->only rate");
			
			selectStatement = "SELECT FirstName,LastName,CandidateEMail,CandidateContactNumber,CurrentLocation,VisaTypeValue,VisaApprovalValue"+
   	    ",FormI797AvailableValue,FormI94AvailableValue,RateInDollar,MiddleName,DateOfBirth"+
   		",TotalExperience,ExperienceInUSA,ReLocationValue,Availability,PreferredLocation,hotlistAvl"+
   	    ",Skills,BestDateForTelephonicInterview,BestTimeForTelephonicInterview,WillingnessForAnInPersonInterviewAtOwnExpenseValue,EmployerName"+
   		",EmployerMailID,EmployerContactNumber,ContactPerson,unique_id,CANID,CandidateStatusValue"+
   	    ",CandidateResumeLoc,user_id,can_company_uniqueid,can_companname,can_company_category,can_joined_status"+
   		",EmployerCompany from tblHotlistCandidate where (RateInDollar between ? and ?) order by reg_date desc";
			        prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, fromRate);
			        prepStmt.setString(2, toRate);
			        
		}else if((visaType != null) &&
				(visaType != "") &&
				(visaType != "null") &&
				(!(visaType.equalsIgnoreCase(""))) &&
				(!(visaType.equalsIgnoreCase("null")))){
			
			System.out.println("inside if -------7------------->only visa type");
			
			selectStatement = "SELECT FirstName,LastName,CandidateEMail,CandidateContactNumber,CurrentLocation,VisaTypeValue,VisaApprovalValue"+
   	    ",FormI797AvailableValue,FormI94AvailableValue,RateInDollar,MiddleName,DateOfBirth"+
   		",TotalExperience,ExperienceInUSA,ReLocationValue,Availability,PreferredLocation,hotlistAvl"+
   	    ",Skills,BestDateForTelephonicInterview,BestTimeForTelephonicInterview,WillingnessForAnInPersonInterviewAtOwnExpenseValue,EmployerName"+
   		",EmployerMailID,EmployerContactNumber,ContactPerson,unique_id,CANID,CandidateStatusValue"+
   	    ",CandidateResumeLoc,user_id,can_company_uniqueid,can_companname,can_company_category,can_joined_status"+
   		",EmployerCompany from tblHotlistCandidate where (VisaTypeValue=?) order by reg_date desc";
			        prepStmt = con.prepareStatement(selectStatement);
			        prepStmt.setString(1, visaType);
			        
		}else{
			
			System.out.println("inside if -------8------------->none of parameters");
			
			selectStatement = "SELECT FirstName,LastName,CandidateEMail,CandidateContactNumber,CurrentLocation,VisaTypeValue,VisaApprovalValue"+
   	    ",FormI797AvailableValue,FormI94AvailableValue,RateInDollar,MiddleName,DateOfBirth"+
   		",TotalExperience,ExperienceInUSA,ReLocationValue,Availability,PreferredLocation,hotlistAvl"+
   	    ",Skills,BestDateForTelephonicInterview,BestTimeForTelephonicInterview,WillingnessForAnInPersonInterviewAtOwnExpenseValue,EmployerName"+
   		",EmployerMailID,EmployerContactNumber,ContactPerson,unique_id,CANID,CandidateStatusValue"+
   	    ",CandidateResumeLoc,user_id,can_company_uniqueid,can_companname,can_company_category,can_joined_status"+
   		",EmployerCompany from tblHotlistCandidate order by reg_date desc";
			        prepStmt = con.prepareStatement(selectStatement);
			        
		}
        rs = prepStmt.executeQuery();
        AppliedCandidateList = new ArrayList();
        while(rs.next()){
        	
        	String txtfirstname = rs.getString(1);
        	String txtlastname =  rs.getString(2);
        	String txtemailaddress = rs.getString(3);
        	String txtcontactnumber = rs.getString(4);
        	String txtcurrentlocation = rs.getString(5);
        	String drpvisatype = rs.getString(6);
        	String drpvisaapproval = rs.getString(7);
        	String drpi797available = rs.getString(8);
        	String drpI97available = rs.getString(9);
        	String txtrate = rs.getString(10);
        	String txtmiddlename = rs.getString(11);
        	String txtdateofbirth = rs.getString(12);
        	String txttotalexperience = rs.getString(13);
        	String txtexperienceinUSA = rs.getString(14);
        	String drprelocation = rs.getString(15);
        	String txtavailability = rs.getString( 16);
        	String txtpreferredlocation = rs.getString(17);
        	String hotlistAvl = rs.getString(18);
        	String txtskills = rs.getString(19);
        	String txtbestDatefortelephonicinterview = rs.getString(20);
        	String txtbestTimefortelephonicinterview = rs.getString(21);
        	String drpwillinginperson = rs.getString(22);
        	String txtempname = rs.getString(23);
        	String txtempmailID = rs.getString(24);
        	String txtempcontactnumber = rs.getString(25);
        	String txtcontactperson = rs.getString(26);
        	//String RID = rs.getString(26);
        	String unique_id = rs.getString(27);
        	String CANID = rs.getString(28);
        	String CandidateStatusValue = rs.getString(29);
        	String CandidateResumeLoc = rs.getString(30);
        	String appliedUserId = rs.getString(31);
        	String candidateCompanyUniqueId = rs.getString(32);
        	String candidateCompany = rs.getString(33);
        	String candidateCompanyCategory = rs.getString(34);
        	int canJoinedStatusInt = rs.getInt(35);
        	String candidateJoinedStatus = Integer.toString(canJoinedStatusInt);
        	String employerCompany = rs.getString(36);
        	
            String[] CandidateList = {txtfirstname,
            		txtlastname, txtemailaddress, txtcontactnumber,
            		txtcurrentlocation, drpvisatype, drpvisaapproval,
            		drpi797available, drpI97available, txtrate,
            		txtmiddlename, txtdateofbirth, txttotalexperience,
            		txtexperienceinUSA, drprelocation,
            		txtavailability, txtpreferredlocation, hotlistAvl,
            		txtskills, txtbestDatefortelephonicinterview,txtbestTimefortelephonicinterview,
            		drpwillinginperson, txtempname,
            		txtempmailID, txtempcontactnumber, txtcontactperson, unique_id,
            		CANID,CandidateStatusValue,CandidateResumeLoc,appliedUserId,candidateCompanyUniqueId,
            		candidateCompany,candidateCompanyCategory,candidateJoinedStatus,employerCompany};
            AppliedCandidateList.add(CandidateList);
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.SearchHotlistCanDetailsByParameters():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.SearchHotlistCanDetailsByParameters():" + e.getMessage());
    }
    return AppliedCandidateList;

}

public String selectHotlistCandidateId()  throws RemoteException {
    
    String CanId = null;
    
    PreparedStatement prepSelect = null;
    makeConnection();
    try {
    	System.out.println("inside the try :::::::::::::::::::");
        String selectStatement = "SELECT MAX(CAST(SUBSTRING(CANID, 7, len(CANID)-6) AS int)) FROM tblHotlistCandidate";
        System.out.println("before prepare stmt ::::::::::::::::");
        prepSelect = con.prepareStatement(selectStatement);
        
        ResultSet rs = prepSelect.executeQuery();
        while (rs.next()) {
        	CanId = rs.getString(1);
         System.out.println("emp id in while loop"+CanId);
        }
        if (CanId == null)
        	CanId = "0";
       
        long nextId = Long.valueOf(CanId).longValue();
        
        if(nextId==0){
        	
            //nextId = 10101000;
 		   
 		   nextId = 1;
 		   System.out.println("nextId in ==0 :::::::::::"+nextId);
        } else{
        	
            nextId = nextId+1;
            System.out.println("nextId in != 0 :::::::::::"+nextId);
        }
        rs.close();
        prepSelect.close();
        CanId = "HOTCAN"+Long.toString(nextId);
        System.out.println("empid in select cust id :::::::::::::::::"+CanId);
       
        //prepStmt.close();
        releaseConnection();
       
    } catch(SQLException sql){
    	System.out.println(sql);
        releaseConnection();
              }
   
    return CanId;
}

public boolean deleteHotListCandidate(String[] unique_id) throws RemoteException{
	 boolean delStatus = false;
    PreparedStatement prepStmt = null;
    ResultSet rs = null;
    makeConnection();
    try {

        String selectStatement = "delete from tblHotlistCandidate where unique_id=?";
        for (int i = 0; i < unique_id.length; i++) {

            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, unique_id[i]);
            int delResult = prepStmt.executeUpdate();

            if (delResult > 0) {
                delStatus = true;
            }

        }
        rs.close();
        prepStmt.close();
        releaseConnection();

    } catch (SQLException sql) {
        releaseConnection();
       
    } catch (Exception e) {
        releaseConnection();
      
    }
    return delStatus;
}


public ArrayList getAllFreshCandidateListByUniqueId(String UniqueId) {
	Debug.print("GeneralDBAction.getAllFreshCandidateListByUniqueId():");
    ArrayList FreshCandidateList = null;
    PreparedStatement prepStmt = null;
    ResultSet rs = null;
    makeConnection();
	try {
        String selectStatement = " SELECT Name,Email,DocumentStatus,Comments,can_Status,VisaType,MobileNumber"+
	    ",RecruitedBy,unique_id,user_id,can_company_uniqueid,can_companyname,can_company_category,CANID,FreshCandidateResumeLoc from tblFreshCandidate where unique_id=? ";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, UniqueId);
        rs = prepStmt.executeQuery();
        FreshCandidateList = new ArrayList();
        while(rs.next()){
        	String Name = rs.getString(1);
        	String Email = rs.getString(2);
        	String DocumentStatus = rs.getString(3);
        	String Comments = rs.getString(4);
        	String can_Status = rs.getString(5);
        	String VisaType = rs.getString(6);
        	String MobileNumber = rs.getString(7);
        	String RecruitedBy = rs.getString(8);
        	String unique_id = rs.getString(9);
        	String user_id = rs.getString(10);
        	String can_company_uniqueid = rs.getString(11);
        	String can_companname = rs.getString(12);
        	String can_company_category = rs.getString(13);
        	String CANID = rs.getString(14);
        	String ResumeLoc = rs.getString(15);
        	
        	String CanList[] = {Name,Email,DocumentStatus,Comments,can_Status,VisaType,MobileNumber,RecruitedBy,unique_id,user_id,can_company_uniqueid,can_companname,can_company_category,ResumeLoc };
        	
        	FreshCandidateList.add(CanList);
        }
        
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.getAllFreshCandidateByUserId():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.getAllFreshCandidateByUserId():" + e.getMessage());
    }
    return FreshCandidateList;
    
 }

public String selectFreshCandidateId()  throws RemoteException {
    
    String CanId = null;
    
    PreparedStatement prepSelect = null;
    makeConnection();
    try {
    	System.out.println("inside the try :::::::::::::::::::");
        String selectStatement = "SELECT MAX(CAST(SUBSTRING(CANID, 9, len(CANID)-8) AS int)) FROM tblFreshCandidate";
        System.out.println("before prepare stmt ::::::::::::::::");
        prepSelect = con.prepareStatement(selectStatement);
        
        ResultSet rs = prepSelect.executeQuery();
        while (rs.next()) {
        	CanId = rs.getString(1);
         System.out.println("Fresh Candidate ID in while loop"+CanId);
        }
        if (CanId == null)
        	CanId = "0";
       
        long nextId = Long.valueOf(CanId).longValue();
        
        if(nextId==0){
        	
            //nextId = 10101000;
 		   
 		   nextId = 1;
 		   System.out.println("nextId in ==0 :::::::::::"+nextId);
        } else{
        	
            nextId = nextId+1;
            System.out.println("nextId in != 0 :::::::::::"+nextId);
        }
        rs.close();
        prepSelect.close();
        CanId = "FRESHCAN"+Long.toString(nextId);
        System.out.println("Fresh Candidate ID in select cust id :::::::::::::::::"+CanId);
       
        //prepStmt.close();
        releaseConnection();
       
    } catch(SQLException sql){
    	System.out.println(sql);
        releaseConnection();
              }
   
    return CanId;
}
public boolean insertFreshCandidate(String Name,String DocumentStatus,String VisaType,String Email,
		String Comments,String Status,String Mobilenum,String RecruitedBy,String userId, String CompanyId,String CompanyCategory,String CompanyName,String ResumeLoc) throws RemoteException{
	
boolean insertStatus = false;
try{
    makeConnection(); 
    String insertStmt=  "INSERT INTO tblFreshCandidate(Name,DocumentStatus,VisaType,Email,Comments,can_Status,MobileNumber"+
    	    ",RecruitedBy,user_id,can_company_uniqueid,can_company_category,can_companyname,CANID,FreshCandidateResumeLoc)"+
    		"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      

      prepStmt = con.prepareStatement(insertStmt);
      
      prepStmt.setString(1,Name);
      prepStmt.setString(2,DocumentStatus);
      prepStmt.setString(3,VisaType);
      prepStmt.setString(4,Email);
      prepStmt.setString(5,Comments);
      prepStmt.setString(6,Status);
      prepStmt.setString(7,Mobilenum);
      prepStmt.setString(8,RecruitedBy);
      prepStmt.setString(9,userId);
      prepStmt.setString(10,CompanyId);
      prepStmt.setString(11,CompanyCategory);
      prepStmt.setString(12,CompanyName);
      prepStmt.setString(13,selectFreshCandidateId());
      prepStmt.setString(14,ResumeLoc);
      
      int cnt= prepStmt.executeUpdate();
      if(cnt>0){
          System.out.println("Fresh Candidate details have been successfully inserted into database----------------->");
          insertStatus = true;
      }
       prepStmt.close();

    } 
    catch (SQLException sqe) {
      releaseConnection();
      
     System.err.println("error in insert Fresh Candidate Details------------------->"+sqe.getMessage()); 
     sqe.printStackTrace();
     //insertStatus = true;
    } finally {
      releaseConnection();
    }
return insertStatus;

}

public ArrayList getAllFreshCandidateListByCompany(String jobPostCompanyName) {
	Debug.print("GeneralDBAction.getAllFreshCandidateListByUserId():");
    ArrayList FreshCandidateList = null;
    PreparedStatement prepStmt = null;
    ResultSet rs = null;
    makeConnection();
	try {
        String selectStatement = " SELECT Name,Email,DocumentStatus,Comments,can_Status,VisaType,MobileNumber"+
	    ",RecruitedBy,unique_id,user_id,can_company_uniqueid,can_companyname,can_company_category,CANID,FreshCandidateResumeLoc from tblFreshCandidate where can_company_uniqueid=? order by reg_date";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, jobPostCompanyName);
        rs = prepStmt.executeQuery();
        FreshCandidateList = new ArrayList();
        while(rs.next()){
        	String Name = rs.getString(1);
        	String Email = rs.getString(2);
        	String DocumentStatus = rs.getString(3);
        	String Comments = rs.getString(4);
        	String can_Status = rs.getString(5);
        	String VisaType = rs.getString(6);
        	String MobileNumber = rs.getString(7);
        	String RecruitedBy = rs.getString(8);
        	String unique_id = rs.getString(9);
        	String user_id = rs.getString(10);
        	String can_company_uniqueid = rs.getString(11);
        	String can_companname = rs.getString(12);
        	String can_company_category = rs.getString(13);
        	String CANID = rs.getString(14);
        	String ResumeLoc = rs.getString(15);
        	
        	String CanList[] = {Name,Email,DocumentStatus,Comments,can_Status,VisaType,MobileNumber,RecruitedBy,unique_id,user_id,can_company_uniqueid,can_companname,can_company_category,ResumeLoc };
        	
        	FreshCandidateList.add(CanList);
        }
        
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.getAllFreshCandidateByUserId():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.getAllFreshCandidateByUserId():" + e.getMessage());
    }
    return FreshCandidateList;
    
}

public boolean updateFreshCandidate(String Name,
		String DocumentStatus, String VisaType, String Email,
		String Comments, String Status, String Mobilenum,
		String RecruitedBy, String userId, String can_uniqueId,String ResumeLoc) {
	
	boolean updateStatus = false;
	try{
	    makeConnection(); 
	    String UpdateStmt=  "update tblFreshCandidate set Name=?,DocumentStatus=?,VisaType=?,Email=?,Comments=?,can_Status=?,MobileNumber=?"+
	    	    ",RecruitedBy=?,FreshCandidateResumeLoc=? where unique_id=?";

	      prepStmt = con.prepareStatement(UpdateStmt);
	      
	      prepStmt.setString(1,Name);
	      prepStmt.setString(2,DocumentStatus);
	      prepStmt.setString(3,VisaType);
	      prepStmt.setString(4,Email);
	      prepStmt.setString(5,Comments);
	      prepStmt.setString(6,Status);
	      prepStmt.setString(7,Mobilenum);
	      prepStmt.setString(8,RecruitedBy);
	      prepStmt.setString(9,ResumeLoc);
	      prepStmt.setString(10,can_uniqueId);
	    
	      
	      int cnt= prepStmt.executeUpdate();
	      if(cnt>0){
	          System.out.println("Fresh Candidate details have been successfully updated into database----------------->");
	          updateStatus = true;
	      }
	       prepStmt.close();

	    } 
	    catch (SQLException sqe) {
	      releaseConnection();
	      
	     System.err.println("error in updating Fresh Candidate Details------------------->"+sqe.getMessage()); 
	     sqe.printStackTrace();
	     //insertStatus = true;
	    } finally {
	      releaseConnection();
	    }
	return updateStatus;

	}

public boolean deleteFreshCandidate(String[] chkRoleIdArr) {
	Debug.print("GeneralDBAction.deleteFreshCandidate");
	boolean DeleteStatus = false;
	try{
	    makeConnection(); 
	    
	    for(int i=0;i<chkRoleIdArr.length;i++){
	    	
	    	System.out.println("chkRoleIdArr[i]-------------->"+chkRoleIdArr[i]);
	    	String DeleteStmt=  "Delete from tblFreshCandidate where unique_id=?";

	      prepStmt = con.prepareStatement(DeleteStmt);
	      
	      prepStmt.setString(1,chkRoleIdArr[i]);
	     
	      
	      int cnt= prepStmt.executeUpdate();
	      if(cnt>0){
	          System.out.println("Fresh Candidate details have been successfully deleted from the database----------------->");
	          DeleteStatus = true;
	      }
	      
	    }
	       prepStmt.close();

	    } 
	    catch (SQLException sqe) {
	      releaseConnection();
	      
	     System.err.println("error in Deleting Fresh Candidate Details------------------->"+sqe.getMessage()); 
	     sqe.printStackTrace();
	     //insertStatus = true;
	    } finally {
	      releaseConnection();
	    }
	return DeleteStatus;

}

public ArrayList updateEmployeeDetailsByUniqueId(String uniqueEmployeeId,String empPayGroup,String empPayratePerhour,String empPayPeriod,String client_name,
		String client_address,String project_name,String client_manager,String manager_phone,String manager_email,String start_date,String end_date,String project_status) {
	Debug.print("GeneralDBAction.updateEmployeeDetailsByUniqueId():");
    
   
    ArrayList updateEmployeeDetailsByUniqueId = null;
   
	try {
		 makeConnection();
		 String insertStatement = "update tblEmployeeMasterDetails set  emp_paygroup=?,emp_payrate_perhour=?,emp_payperiod=?,"+
	    	
	    		"client_name =?,client_address =?,project_name =?,client_manager =?,manager_phone =?,manager_email =?,start_date=?,end_date=?,project_status=? where unique_id=?";
		
	    prepStmt = con.prepareStatement(insertStatement);
	   
	    prepStmt.setString(1, empPayGroup);
	    prepStmt.setString(2, empPayratePerhour);
	    prepStmt.setString(3, empPayPeriod);
	    prepStmt.setString(4, client_name);
	    prepStmt.setString(5, client_address);
	    prepStmt.setString(6, project_name);
	    prepStmt.setString(7, client_manager);
	    prepStmt.setString(8, manager_phone);
	    prepStmt.setString(9, manager_email);
	    prepStmt.setString(10, start_date);
	    prepStmt.setString(11, end_date);
	    prepStmt.setString(12, project_status);
	    prepStmt.setString(13, uniqueEmployeeId);
	 
	    
	   
        prepStmt.executeUpdate();
        			
        	
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.getAllEmployeeDetailsByUniqueId():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.getAllEmployeeDetailsByUniqueId():" + e.getMessage());
    }
	return updateEmployeeDetailsByUniqueId;
   
    
}



public ArrayList insertTimesheet(String empUserId, String empId,String empdberpPartyid,String empSSNNo,
		String payperiod,String over_time,String time_Off,String straight_time,String filepath ) throws RemoteException, SQLException{
	
	ArrayList insertTimesheet=null;
	
	try{
		

	    makeConnection(); 
	 
	    		
	    String insertStmt=  "INSERT INTO tblEmployeeTimesheetDetails (user_id,emp_id,emp_dberp_partyid,emp_ssn_no,week_period,overtime_hrs,sick_hrs,total_hrs,timesheet_path)  VALUES (?,?,?,?,?,?,?,?,?) ";
	    		
      
	      

	      prepStmt = con.prepareStatement(insertStmt);
	      
	      prepStmt.setString(1,empUserId);
	      prepStmt.setString(2,empId);
	      prepStmt.setString(3,empdberpPartyid);
	      prepStmt.setString(4,empSSNNo);
	      prepStmt.setString(5,payperiod); 
	      prepStmt.setString(6,over_time); 
	      prepStmt.setString(7,time_Off); 
	      prepStmt.setString(8,straight_time); 
	      prepStmt.setString(9,filepath); 
	     
	      
	      int cnt= prepStmt.executeUpdate();
	      if(cnt>0){
	          System.out.println("new timesheet entry are successfully inserted into database----------------->");
	         // insertTimesheet = true;
	      }
	       prepStmt.close();

	    } 
	    catch (SQLException sqe) {
	      releaseConnection();
	     sqe.printStackTrace();  
	     //insertTimesheet = false;
	    } finally {
	      releaseConnection();
	    }
	//return insertTimesheet;
	return insertTimesheet ;
}

public ArrayList getEmployeeTimesheet(String uniqueId,String period) {
	Debug.print("GeneralDBAction.getEmployeeTimesheet():");
    ArrayList getEmployeeTimesheet = null;
    PreparedStatement prepStmt = null;
    ResultSet rs = null;
    makeConnection();
	try {
		/*SELECT a.map_permission_id , b.permission_name ,a.access_name,a.access_des, a.access_url " +
 		"from tblMapPermission a , tblPermissionMaster b where a.permission_id = b.permission_id and a.privilege_id = ? ";*/
		
			    		  
        String selectStatement = " SELECT a.user_id, b.emp_id,b.emp_dberp_partyid,b.emp_ssn_no,b.week_period,b.overtime_hrs,b.sick_hrs,b.total_hrs,b.timesheet_path,b.hr_status,b.timesheet_id,b.rejected_reason from tblEmployeeMasterDetails a,  tblEmployeeTimesheetDetails b where a.user_id =b.user_id and a.unique_id=? and b.week_period=?";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, uniqueId);
        prepStmt.setString(2, period);
        rs = prepStmt.executeQuery();
        getEmployeeTimesheet = new ArrayList();
        while(rs.next()){
        	
        			String empId = rs.getString(2);
        			String emp_dberp_partyid = rs.getString(3);
        			String emp_ssn_no = rs.getString(4);
        			String week_period = rs.getString(5);
        			String overtime_hrs = rs.getString(6);
        			String sick_hrs = rs.getString(7);
        			String total_hrs = rs.getString(8);
        			String timesheet_path = rs.getString(9);
        			String hr_status = rs.getString(10);
        			String timesheet_id = rs.getString(11);
        			String rejectionstatus = rs.getString(12);
        		
        		
    		
            String[] strList = {empId,emp_dberp_partyid,emp_ssn_no,week_period,overtime_hrs,sick_hrs,total_hrs,timesheet_path,hr_status,timesheet_id,rejectionstatus};
            getEmployeeTimesheet.add(strList);
            
            System.out.println("empId from createtimesheet==>"+empId);
     	   System.out.println("empdberpPartyid from createtimesheet==>"+emp_dberp_partyid);
     	   System.out.println("empSSNNo from createtimesheet==>"+emp_ssn_no);
     	   System.out.println("week_period from createtimesheet==>"+week_period);
     	   System.out.println("overtime_hrs from createtimesheet==>"+overtime_hrs);
     	   System.out.println("sick_hrs from createtimesheet==>"+sick_hrs);
     	   System.out.println("total_hrs from createtimesheet==>"+total_hrs);
     	   System.out.println("hr_status from createtimesheet==>"+hr_status);
     	  System.out.println("rejectionstatus from createtimesheet==>"+rejectionstatus);
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.getEmployeeTimesheet():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.getEmployeeTimesheet():" + e.getMessage());
    }
    return getEmployeeTimesheet;
    
}


public ArrayList updateTimesheetByUniqueId (
		String week_period,String overtime_hrs,String sick_hrs,String total_hrs,String timesheet_path,String hr_status, String timesheet_id) {
    Debug.print("GeneralDBAction.updateTimesheetByUniqueId():");
    PreparedStatement prepStmt = null;
    makeConnection();
    try {
        String insertStatement = "update tblEmployeeTimesheetDetails set week_period = ?,overtime_hrs=? ,sick_hrs=?,total_hrs=?,timesheet_path=?,hr_status=?" +
                " where  timesheet_id = ? ";
        
        prepStmt = con.prepareStatement(insertStatement);
        prepStmt.setString(1, week_period);
        prepStmt.setString(2, overtime_hrs);
        prepStmt.setString(3, sick_hrs);
        prepStmt.setString(4, total_hrs);
        prepStmt.setString(5, timesheet_path);
        prepStmt.setString(6, hr_status);
        prepStmt.setString(7, timesheet_id);
       
        
        prepStmt.executeUpdate();
        prepStmt.close();
        releaseConnection();
    }
    
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.updateTimesheetByUniqueId():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.updateTimesheetByUniqueId():" + e.getMessage());
    }
    return null;
}  

public boolean updateTimesheetByHR (
		String hr_status, String rejectionReason, String timesheet_id) {
    Debug.print("GeneralDBAction.updateTimesheetByHR():");
    boolean result = false;
    PreparedStatement prepStmt = null;
    makeConnection();
    try {
        String insertStatement = "update tblEmployeeTimesheetDetails set hr_status=?, rejected_reason =? where  timesheet_id = ? ";
        
        prepStmt = con.prepareStatement(insertStatement);
        prepStmt.setString(1, hr_status);
       
        prepStmt.setString(2, rejectionReason);
        prepStmt.setString(3, timesheet_id);
       
        
        prepStmt.executeUpdate();
        prepStmt.close();
        releaseConnection();
        System.out.println("timesheet by HR updated successfully");
    }
    
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.updateTimesheetByHR():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.updateTimesheetByHR():" + e.getMessage());
    }
    return result;
}

public boolean checkEmpTimesheetDurationExist(String userId, String payperiod) throws SQLException{
	Debug.print("GeneralDBAction checkEmpTimesheetDurationExist");
     boolean result = false;
     makeConnection();
	try {
         String selectStatement = "SELECT week_period from tblEmployeeTimesheetDetails WHERE user_id = ? and week_period = ?" ;
         PreparedStatement prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1,userId);
         prepStmt.setString(2,payperiod);
         ResultSet rs = prepStmt.executeQuery();
         if (rs.next()){
             result = true;
         }
         rs.close();
         prepStmt.close();
         releaseConnection();
     } 
     catch (SQLException e) {
         releaseConnection();
         Debug.print("GeneralDBAction:: Could not find any from checkEmpPayDurationExist" + e.getMessage());
     }
     catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in GeneralDBAction checkEmpTimesheetDurationExist():" + e.getMessage());
     }
     Debug.print("GeneralDBAction checkEmpTimesheetDurationExist():" + result);
     return result;
}


public boolean insertBuyerLeadInquiryDets(String blFName, String blLName,String blComp, String blTitle, String blTypoBusiness, String blWebsite, String blOffPh, String blEmailId,String blPriAddStrt, String blPriAddCity, String blPriAddState, String blPriAddzip, String blPriAddCountry, String blLeadSrc, String account_name,String description) throws SQLException {
    Debug.print("GeneralDBAction insertBuyerLeadInquiryDets");
    int cnt = 0;
    boolean res=false;
    makeConnection();
     
    String insertStatement = "insert into tblBuyerLeadInquiryDetails (bl_Fname,bl_Lname,bl_company,bl_Title,bl_type_business,bl_website,"+
        "bl_off_phone,bl_emailId,primary_street,primary_city, primary_state, primary_country,lead_source,account_name,description,primary_zip)" +
            " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?, ?, ?, ?, ?) ";
    prepStmt = con.prepareStatement(insertStatement);
    System.out.println("Inside the insertBuyerLeadInquiryDets ....\n\n ");
    prepStmt.setString(1, blFName);   
    prepStmt.setString(2, blLName);   
    prepStmt.setString(3, blComp);
    prepStmt.setString(4, blTitle);
    prepStmt.setString(5, blTypoBusiness);
    prepStmt.setString(6, blWebsite);
    prepStmt.setString(7, blOffPh);
    prepStmt.setString(8, blEmailId);
    prepStmt.setString(9, blPriAddStrt);
    prepStmt.setString(10, blPriAddCity);  
    prepStmt.setString(11, blPriAddState);   
    prepStmt.setString(12, blPriAddCountry);     
    prepStmt.setString(13, blLeadSrc);  
    prepStmt.setString(14, account_name);   
    prepStmt.setString(15, description);
    prepStmt.setString(16, blPriAddzip);  
    
      
     cnt = prepStmt.executeUpdate();
       
    System.out.print("Record Inserted succefully  "+cnt);
    
    if (cnt >0)
    	res=true;
    else
        res=false;
    
    Debug.print(" Inserted the Buyer Lead Data : \n");
    prepStmt.close();
    releaseConnection();
    
	return res;
}

public boolean insertPartnershipDetails(String firstname,String lastname,String email,String company,String Designation,String AddressLine1,
		String City,String State,String Zipcode,String Country,String PhoneNumber,String Website,String business_type,String publicorprivate,
		String no_of_customer,String no_of_employee,String interest,String territory,String reselling) throws RemoteException, SQLException{
	
	boolean insertstatus = false;
	
	try{
		

	    makeConnection(); 
	 
	    		
	    String insertStmt=  "INSERT INTO tblProvisionalPartnerDetails (prov_firstname,prov_lastname,prov_emailid,prov_company,prov_designation,prov_address," +
	    		"prov_city,prov_state,prov_zipcode,prov_country,prov_phone,prov_compweburl,prov_businesstype,prov_sector,prov_custNumb,prov_empNumb,prov_interest," +
	    		"prov_proposedesc,prov_prodresell)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
	    		
      
	      

	      prepStmt = con.prepareStatement(insertStmt);
	      
	      prepStmt.setString(1,firstname);
	      prepStmt.setString(2,lastname);
	      prepStmt.setString(3,email);
	      prepStmt.setString(4,company);
	      prepStmt.setString(5,Designation); 
	      prepStmt.setString(6,AddressLine1); 
	      prepStmt.setString(7,City); 
	      prepStmt.setString(8,State); 
	      prepStmt.setString(9,Zipcode); 
	      prepStmt.setString(10,Country); 
	      prepStmt.setString(11,PhoneNumber);
	      prepStmt.setString(12,Website);
	      prepStmt.setString(13,business_type);
	      prepStmt.setString(14,publicorprivate);
	      prepStmt.setString(15,no_of_customer); 
	      prepStmt.setString(16,no_of_employee); 
	      prepStmt.setString(17,interest); 
	      prepStmt.setString(18,territory); 
	      prepStmt.setString(19,reselling); 
	     
	      
	      int cnt= prepStmt.executeUpdate();
	      if(cnt>0){
	          System.out.println("new partnership details  are successfully inserted into database----------------->");
	          insertstatus = true;
	      }
	       prepStmt.close();

	    } 
	    catch (SQLException sqe) {
	      releaseConnection();
	     sqe.printStackTrace();  
	     //insertTimesheet = false;
	    } finally {
	      releaseConnection();
	    }
	//return insertTimesheet;
	return insertstatus ;
}


public ArrayList getAllPartnerDetails() {
	Debug.print("GeneralDBAction.getAllPartnerDetails():");
    ArrayList partnerList = null;
    PreparedStatement prepStmt = null;
    ResultSet rs = null;
    makeConnection();
	try {
        String selectStatement = " SELECT prov_firstname,prov_lastname,prov_emailid,prov_company,prov_designation,prov_address," +
	    		"prov_city,prov_state,prov_zipcode,prov_country,prov_phone,prov_compweburl,prov_businesstype,prov_sector," +
	    		"prov_custNumb,prov_empNumb,prov_interest,prov_proposedesc,prov_prodresell,provisional_partner_id from tblProvisionalPartnerDetails order by add_date";
        prepStmt = con.prepareStatement(selectStatement);
        rs = prepStmt.executeQuery();
        partnerList = new ArrayList();
        while(rs.next()){
        			String prov_firstname = rs.getString(1);
        			String prov_lastname = rs.getString(2);
        			String prov_emailid = rs.getString(3);
        			String prov_company = rs.getString(4);
        			String prov_designation = rs.getString(5);
        			String prov_address = rs.getString(6);
        			String prov_city = rs.getString(7);
        			String prov_state = rs.getString(8);
        			String prov_zipcode = rs.getString(9);
        			String prov_country = rs.getString(10);
        			String prov_phone = rs.getString(11);
        			String prov_compweburl = rs.getString(12);
        			String prov_businesstype = rs.getString(13);
        			String prov_sector = rs.getString(14);
        			String prov_custNumb = rs.getString(15);
        			String prov_empNumb = rs.getString(16);
        			String prov_interest = rs.getString(17);
        			String prov_proposedesc = rs.getString(18);
        			String prov_prodresell = rs.getString(19);
        			String provisional_partner_id = rs.getString(20);
        			
    	        	
    		
            String[] strList = {prov_firstname,prov_lastname,prov_emailid,prov_company,prov_designation,prov_address,
    	    		prov_city,prov_state,prov_zipcode,prov_country,prov_phone,prov_compweburl,prov_businesstype,prov_sector,prov_custNumb,prov_empNumb,prov_interest,
    	    		prov_proposedesc,prov_prodresell,provisional_partner_id};
            partnerList.add(strList);
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.getAllPartnerDetails():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.getAllPartnerDetails():" + e.getMessage());
    }
    return partnerList;
    
}


public ArrayList getAllPartnerDetailsByPartnerId(String PartnerId) {
	Debug.print("GeneralDBAction.getAllPartnerDetailsByPartnerId():");
    ArrayList partnerList = null;
    PreparedStatement prepStmt = null;
    ResultSet rs = null;
    makeConnection();
    System.out.println("in getpartnerdetails by partnerid");
	try {
        String selectStatement = " SELECT prov_firstname,prov_lastname,prov_emailid,prov_company,prov_designation,prov_address," +
	    		"prov_city,prov_state,prov_zipcode,prov_country,prov_phone,prov_compweburl,prov_businesstype,prov_sector," +
	    		"prov_custNumb,prov_empNumb,prov_interest,prov_proposedesc,prov_prodresell,provisional_partner_id from tblProvisionalPartnerDetails where provisional_partner_id=?";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, PartnerId);
        rs = prepStmt.executeQuery();
        partnerList = new ArrayList();
        while(rs.next()){
        			String prov_firstname = rs.getString(1);
        			String prov_lastname = rs.getString(2);
        			String prov_emailid = rs.getString(3);
        			String prov_company = rs.getString(4);
        			String prov_designation = rs.getString(5);
        			String prov_address = rs.getString(6);
        			String prov_city = rs.getString(7);
        			String prov_state = rs.getString(8);
        			String prov_zipcode = rs.getString(9);
        			String prov_country = rs.getString(10);
        			String prov_phone = rs.getString(11);
        			String prov_compweburl = rs.getString(12);
        			String prov_businesstype = rs.getString(13);
        			String prov_sector = rs.getString(14);
        			String prov_custNumb = rs.getString(15);
        			String prov_empNumb = rs.getString(16);
        			String prov_interest = rs.getString(17);
        			String prov_proposedesc = rs.getString(18);
        			String prov_prodresell = rs.getString(19);
        			String provisional_partner_id = rs.getString(20);
        			
    	        	
    		
            String[] strList = {prov_firstname,prov_lastname,prov_emailid,prov_company,prov_designation,prov_address,
    	    		prov_city,prov_state,prov_zipcode,prov_country,prov_phone,prov_compweburl,prov_businesstype,prov_sector,prov_custNumb,prov_empNumb,prov_interest,
    	    		prov_proposedesc,prov_prodresell,provisional_partner_id};
            partnerList.add(strList);
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.getAllPartnerDetailsByPartnerId():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.getAllPartnerDetailsByPartnerId():" + e.getMessage());
    }
    return partnerList;
    
}

public boolean insertPartnerEventDetails(String eventDate,String eventmail, String partnerid, String email_status ) throws RemoteException, SQLException{
	
	boolean eventDetails=false;
	
	try{
		

	    makeConnection(); 
	 
	    		
	    String insertStmt=  "INSERT INTO tblProvPartnerEventScheduleDetails (prov_schedule_date,prov_emailid,provisional_partner_id,email_status)  VALUES (?,?,?,?) ";
	    		
      
	      

	      prepStmt = con.prepareStatement(insertStmt);
	      
	      prepStmt.setString(1,eventDate);
	      prepStmt.setString(2,eventmail);
	      prepStmt.setString(3,partnerid);
	      prepStmt.setString(4,email_status);
	       
	     
	      
	      int cnt= prepStmt.executeUpdate();
	      if(cnt>0){
	          System.out.println("PartnerEvent Details entry are successfully inserted into database----------------->");
	          eventDetails = true;
	      }
	       prepStmt.close();

	    } 
	    catch (SQLException sqe) {
	      releaseConnection();
	     sqe.printStackTrace();  
	     //insertTimesheet = false;
	    } finally {
	      releaseConnection();
	    }
	//return insertTimesheet;
	return eventDetails ;
}

public boolean checkEmailExist(String email) throws SQLException {        
    boolean usrStat = false;
    try {
     makeConnection();
        
        String selectStatement = "select prov_emailid from tblProvisionalPartnerDetails where prov_emailid = ? ";
        
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, email);
        
        rs = prepStmt.executeQuery();
        if (rs.next()) {
        	System.out.println("email exists");
        	usrStat = true;
        }
        rs.close();             
        prepStmt.close();
    } catch (SQLException sqe) {
      releaseConnection();            
        //sqe.printStackTrace();
    } finally {
    	releaseConnection();
    }
    return usrStat;
}

public ArrayList getAllPartnerIdByEmailId(String receiveremail) {
	Debug.print("GeneralDBAction.getAllPartnerIdByEmailId():");
    ArrayList partnerList = null;
    PreparedStatement prepStmt = null;
    ResultSet rs = null;
    makeConnection();
    System.out.println("in getAllPartnerIdByEmailId by email");
	try {
        String selectStatement = " SELECT prov_firstname,prov_lastname,prov_emailid,prov_company,prov_designation,prov_address," +
	    		"prov_city,prov_state,prov_zipcode,prov_country,prov_phone,prov_compweburl,prov_businesstype,prov_sector," +
	    		"prov_custNumb,prov_empNumb,prov_interest,prov_proposedesc,prov_prodresell,provisional_partner_id from tblProvisionalPartnerDetails where prov_emailid=?";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, receiveremail);
        rs = prepStmt.executeQuery();
        partnerList = new ArrayList();
        while(rs.next()){
        			String prov_firstname = rs.getString(1);
        			String prov_lastname = rs.getString(2);
        			String prov_emailid = rs.getString(3);
        			String prov_company = rs.getString(4);
        			String prov_designation = rs.getString(5);
        			String prov_address = rs.getString(6);
        			String prov_city = rs.getString(7);
        			String prov_state = rs.getString(8);
        			String prov_zipcode = rs.getString(9);
        			String prov_country = rs.getString(10);
        			String prov_phone = rs.getString(11);
        			String prov_compweburl = rs.getString(12);
        			String prov_businesstype = rs.getString(13);
        			String prov_sector = rs.getString(14);
        			String prov_custNumb = rs.getString(15);
        			String prov_empNumb = rs.getString(16);
        			String prov_interest = rs.getString(17);
        			String prov_proposedesc = rs.getString(18);
        			String prov_prodresell = rs.getString(19);
        			String provisional_partner_id = rs.getString(20);
        			
    	        	
    		
            String[] strList = {prov_firstname,prov_lastname,prov_emailid,prov_company,prov_designation,prov_address,
    	    		prov_city,prov_state,prov_zipcode,prov_country,prov_phone,prov_compweburl,prov_businesstype,prov_sector,prov_custNumb,prov_empNumb,prov_interest,
    	    		prov_proposedesc,prov_prodresell,provisional_partner_id};
            partnerList.add(strList);
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.getAllPartnerIdByEmailId():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.getAllPartnerIdByEmailId():" + e.getMessage());
    }
    return partnerList;
    
}

public ArrayList SelectEventPartnerDetails() {
	Debug.print("GeneralDBAction.SelectEventPartnerDetails():");
    ArrayList EventPartnerDetails = null;
    PreparedStatement prepStmt = null;
    ResultSet rs = null;
    makeConnection();
    System.out.println("in SelectEventPartnerDetails ");
	try {
		
		
			    		  
        String selectStatement = " SELECT prov_schedule_id,provisional_partner_id,prov_schedule_date,prov_emailid,active_status,add_date,prov_status,email_status from tblProvPartnerEventScheduleDetails";
        prepStmt = con.prepareStatement(selectStatement);
      //  prepStmt.setString(1, receiveremail);
        rs = prepStmt.executeQuery();
        EventPartnerDetails=new ArrayList();
        while(rs.next()){
        			String prov_schedule_id = rs.getString(1);
        			String provisional_partner_id = rs.getString(2);
        			String prov_schedule_date = rs.getString(3);
        			String prov_emailid = rs.getString(4);
        			String active_status = rs.getString(5);
        			String addDate = rs.getString(6);
        			String prov_status = rs.getString(7);
        			String email_status = rs.getString(8);
        		
        			System.out.println("prov_schedule_id from db===="+prov_schedule_id);
            String[] strList = {prov_schedule_id,provisional_partner_id,prov_schedule_date,prov_emailid,active_status,addDate,prov_status,email_status};
            EventPartnerDetails.add(strList);
           
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.SelectEventPartnerDetails():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.SelectEventPartnerDetails():" + e.getMessage());
    }
    return EventPartnerDetails;
    
}

public boolean updatePartnerstatus (String status, String receiveremail) {
    Debug.print("GeneralDBAction.updatePartnerstatus():");
    boolean result = false;
    PreparedStatement prepStmt = null;
    makeConnection();
    try {
        String insertStatement = "update tblProvPartnerEventScheduleDetails SET prov_status= ? where prov_emailid=?";
        
        prepStmt = con.prepareStatement(insertStatement);
        prepStmt.setString(1, status);
        prepStmt.setString(2, receiveremail);
       
       
        
        prepStmt.executeUpdate();
        prepStmt.close();
        releaseConnection();
        System.out.println("updatePartnerstatus updated successfully");
    }
    
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.updatePartnerstatus():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.updatePartnerstatus():" + e.getMessage());
    }
    return result;
}

public boolean updatePartnerEmailStatus (String emailstatus, String scheduledid) {
	
	System.out.println("emailstatus "+emailstatus);
	System.out.println("scheduled id===>"+scheduledid);
    Debug.print("GeneralDBAction.updatePartnerEmailStatus():");
    boolean result = false;
    PreparedStatement prepStmt = null;
    makeConnection();
    try {
        String insertStatement = "update tblProvPartnerEventScheduleDetails SET email_status= ? where prov_schedule_id=?";
        
        prepStmt = con.prepareStatement(insertStatement);
        prepStmt.setString(1, emailstatus);
        prepStmt.setString(2, scheduledid);
       
        int cnt= prepStmt.executeUpdate();
	       if(cnt>0){  
	    	   System.out.println("updatePartnerEmailStatus updated successfully");
	    	   result=true;
	       }
        
        prepStmt.executeUpdate();
        prepStmt.close();
        releaseConnection();
      
       
    }
    
    catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in GeneralDBAction.updatePartnerEmailStatus():" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in GeneralDBAction.updatePartnerEmailStatus():" + e.getMessage());
    }
    return result;
}

}

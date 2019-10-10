#-------------------------------------------------------------------------------
# /*******************************************************************************
# * Copyright: 2019 digiBlitz Foundation
# * 
# * License: digiBlitz Public License 1.0 (DPL) 
# * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
# * 
# * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
# * 
# * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
# * 
# * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
#-------------------------------------------------------------------------------
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Klugwerks</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
 <!--CSS-->
<link rel="stylesheet" href="css/bootstrap.css" >
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/camera.css">
<link rel="stylesheet" href="css/jquery.fancybox.css">
<link rel="stylesheet" href="fonts/font-awesome.css">

<!--JS-->
<script src="js/jquery.js"></script>
<script src="js/jquery-migrate-1.2.1.min.js"></script>
<script src="js/jquery.easing.1.3.js"></script>
<script src="js/jquery.mobilemenu.js"></script>
<script src="js/jquery.equalheights.js"></script> 
<script src="js/camera.js"></script>
<!--[if (gt IE 9)|!(IE)]><!-->
<script src="js/jquery.mobile.customized.min.js"></script>
<!--<![endif]-->
<script src="js/jquery.fancybox.pack.js"></script>
<script src="js/jquery.carouFredSel-6.1.0-packed.js"></script>
<script src="js/jquery.touchSwipe.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/tm-scripts.js"></script>
<script language="javascript">

function checkAll() {
     el = document.forms['frmRoleUserMap'].elements;
     for (i=0; i < el.length; i++) {
       if(el[i].type == "checkbox") {
          if(el[i].checked== true && el[i].value== "ChkAll") {
            //alert("ok");
            for (j=0; j < el.length; j++) {
               if(el[j].type == "checkbox") {
                  void(el[j].checked=1);
               }
            }
            break;
          }
          if(el[i].checked== false && el[i].value== "ChkAll") {
            //alert("uncheck");
            for (j=0; j < el.length; j++) {
               if(el[j].type == "checkbox") {
                  void(el[j].checked=0);
               }
            }
          }  
       }
     }
 }
 
function DelAll(){
	var chkValue = "";var chk="";
	e = document.forms['frmRoleUserMap'].elements;
	var count =0;
        //Start:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011
        if(e.length!=undefined && e.length!='undefined' && e.length > 1)
	{
        //End:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011
            for(i=0 ; i< e.length; i++){
		//Start:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011
                //if(e[i].type == "checkbox"){
                  if(e[i].type == "radio" && e[i].name == "role"){
                //End:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011
			 if(e[i].checked == true && e[i].value != "ChkAll") {
				 count++;
				 //Start:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011
                                 //chkValue +=  e[i].value + "#";chk+=e[i]+",";
                                 chkValue +=  e[i].value;
                                 //End:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011

			}
		}
            }
        //Start:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011
        }
        //End:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011
		document.frmRoleUserMap.roleIds.value = chkValue;
                 //Start:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011
                 //document.frmRoleUserMap.roleIdValue.value = chk;
                 //End:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011
}


function cancelEditRole()
    {
        strURL = "./roles.do?roleProcess=roleList";
	window.location.href = strURL;
    }

</script>
</head>


<body>
<!--========================================================
HEADER
=========================================================-->
  <div id="header">
    <!-- HEADER STARTS HERE -->
  <%@ include file = "../../include/menschForceHeader.jsp" %>
      <!-- HEADER ENDS HERE -->
    </div>
	
<%
	
	String userId1 = (String) request.getAttribute("userId");
	
	String usrCrit = (String) request.getAttribute("usrCrit");
	if(userId1==null){
		userId1 = "";
	}
	
ArrayList listContact = (ArrayList) request.getAttribute("userContactDetails");
	
		String prefix1 = "";
		String first_name = "";
		String middle_name = "";
		String last_name = "";
		String sufix = "";
		String email_id = "";
		String suite = "";
		String address1 = "";
		String address2 = "";
		String city = "";
		String state = "";
		String country = "";
		String zip = "";
		String phone_no = "";
		String mobile_no = "";
		String fax_no = "";
		
		if(listContact !=null && listContact.size()!=0){
			Iterator it = listContact.iterator();
			while(it.hasNext()){
				prefix1 = (String)it.next();
				if(prefix1==null)
				prefix1 = "";
				first_name  = (String)it.next();
				if(first_name==null)
				first_name = "";
				middle_name  = (String)it.next();
				if(middle_name==null)
				middle_name = "";
				last_name = (String)it.next();
				if(last_name==null)
				last_name = "";
				sufix =  (String)it.next();
				if(sufix==null)
				sufix = "";
				email_id  = (String)it.next();
				if(email_id==null)
				email_id = "";
				suite =  (String)it.next();
				if(suite==null)
				suite = "";
				address1 =  (String)it.next();
				if(address1==null)
				address1 = "";
				address2 = (String)it.next();
				if(address2==null)
				address2 = "";
				city = (String)it.next();
				if(city==null)
				city = "";
				state =  (String)it.next();
				if(state==null)
				state = "";
				country = (String)it.next();
				if(country==null)
				country = "";
				zip = (String)it.next();
				if(zip==null)
				zip = "";
				phone_no = (String)it.next();
				if(phone_no==null)
				phone_no = "";
				mobile_no = (String)it.next();
				if(mobile_no==null)
				mobile_no = "";
				fax_no = (String)it.next();
				if(fax_no==null)
				fax_no = "";
			}
		}	
	
	
	//String privId = "";
	
	
	
%>
<%String user_Name = (String)request.getAttribute("login_name");
System.out.println("Login name :"+user_Name);
%>

<!--=======content================================-->

<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">
			
				<div class="col-lg-12 col-md-12 col-sm-12">
					
					<h3 class="title">Map: User Role Mapping</h3>
					
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-2 col-md-2 col-sm-2">
					</div>
					<div class="col-lg-10 col-md-10 col-sm-10">
						Select the required 'Role' to be assigned to a selected 'User'.
					</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
			
                  <%String empScreen = (String)request.getAttribute("empMapScr");
				  System.out.println("first Name : "+first_name);
				   System.out.println("first Name : "+last_name);
				  %>
                    <form name="frmRoleUserMap" id="frmRoleUserMap" action="roles.html" onsubmit="DelAll();"><input type="hidden" name="roleProcess" value="mapUserRoles" />
                      <input type="hidden" name="roleIds" value="sury" />
                      <input type="hidden" name="empScreen" value="<%=empScreen%>"/>
                      <input type="hidden" name="roleIdValue"/>
                      <input type="hidden" name="userId" value="<%=userId1%>" />
                      <input type="hidden" name="lastname" value="<%=last_name%>" />
                      <input type="hidden" name="firstname" value="<%=first_name%>" />
                      <input type="hidden" name="username" value="<%=user_Name%>" />
					  
					  <div class="col-lg-12 col-md-12 col-sm-12">
						
						<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-3">
							<label class="name form-div-6 subtitle">
							Assign  Role To User  
							</label>
						</div>
					</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
					
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							User Name 
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3x">
							<%=first_name%>&nbsp;<%=last_name%> 
							</div> 
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
                     
                                <%
									ArrayList arrayRoleList = (ArrayList)request.getAttribute("roleDetails");
									if(arrayRoleList!=null && arrayRoleList.size()!=0){
										Iterator itRoleList = arrayRoleList.iterator();
										while(itRoleList.hasNext()){
											String[] roleList = (String [])itRoleList.next(); //{privilegeId, privilegeName, privilegePath};
											String roleId = roleList[0];
											String rolName = roleList[1];
											boolean roleIdStatus = false;
											ArrayList arrayMapUSerRoleList = (ArrayList)request.getAttribute("userRoleDetails");
											if(arrayMapUSerRoleList!=null && arrayMapUSerRoleList.size()!=0){
												Iterator itUserRoleList = arrayMapUSerRoleList.iterator();
												while(itUserRoleList.hasNext()){
													String[] mapUserRoleList = (String [])itUserRoleList.next(); //{privilegeId, privilegeName, privilegePath};
													String rolId = mapUserRoleList[2];
													if(roleId.equals(rolId)){
														roleIdStatus = true;
														break;
													}
													//{mapPrivilegeId, entityIdVal, privilegeId, entityName, privilegeName};
												}
											}
											if(roleIdStatus==true){
											%>
                              
                                  <!--Start:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011-->
                                  <%--<td colspan="2" class="tableLeftTxtArea">
													<input type="checkbox" class="tblMainHead" name="checkbox5" value="<%=roleId%>" checked="checked" />
													<span class="tblMainHead"><%=rolName%></span>
												</td>--%>
                                  <!--Start: Dhivya Here(Included the checked option)-->
								  <div class="col-lg-12 col-md-12 col-sm-12">
									
									<div class="col-lg-1 col-md-1 col-sm-1 col-md-offset-3">
									
									<input type="radio" name="role" checked="checked" value="<%=roleId%>"/>
									
									</div>
									<div class="col-lg-4 col-md-4 col-sm-4">
									<%=rolName%>
									</div> 
									
								</div>
								  
								 
                                  <!--End:Divya Here-->
                                  <!--End:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011-->
                               
                                <%
											 }
											 else{
											 %>
                               
                                  <!--Start:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011-->
                                  <!--<td colspan="2" class="tableLeftTxtArea">
													<input type="checkbox" class="tblMainHead" name="checkbox5" value="<%=roleId%>" />
													<span class="tblMainHead"><%=rolName%></span>
												</td>-->
												
								<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="col-lg-1 col-md-1 col-sm-1 col-md-offset-3">
									
									  <input type="radio" name="role" value="<%=roleId%>"/>
									
									</div>
									<div class="col-lg-4 col-md-4 col-sm-4">
									<%=rolName%>
									</div> 
									
								</div>
                                
                                  <!--End:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011-->
                               
                                <%
											 }
										}
									}
							%>
                                
                          <!--tr>
					<td class="tblRowHead" colspan="9">View Points:</td>
					</tr> 
					<tr>
					<td class="tableLeft" colspan="9">User Criteria :
				
								<font color="#FF0000"><strong><%=usrCrit%></strong></font>
					
							</td>
					</tr>
					
							<%
									 ArrayList viewPoint = (ArrayList) request.getAttribute("viewPoint");
									 ArrayList usrViewPnt = (ArrayList) request.getAttribute("usrViewPnt");
									 
          					 if(viewPoint!=null && viewPoint.size()!=0){
							 int j=0;
							Iterator itview = viewPoint.iterator();
							while(itview.hasNext()){
								String[] sview = (String[])itview.next();
								String viewId = sview[0];
								String viewName = sview[1];		
									boolean usrViewStatus=false;
					if(usrViewPnt!=null && usrViewPnt.size()!=0){
							
							Iterator itUsrview = usrViewPnt.iterator();
							while(itUsrview.hasNext()){
								String[] sUsrview = (String[])itUsrview.next();
								String usrViewPntId = sUsrview[0];
								String usrViewPntName = sUsrview[1];
								if(viewId.equals(usrViewPntId)){
														usrViewStatus = true;
														break;
													}
								}
								}
								if(usrViewStatus==true){		
											 %>
											 <tr>
											 <td class="tableRight" colspan="9">
											<input checked="checked" type="checkbox" name="viewPnt<%=j%>" id="viewPnt<%=j%>" value="<%=viewId%>"/><%=viewName%>
										</td>
											</tr>
											<%}else{%>
										 <tr>
											 <td class="tableRight" colspan="9">
											<input type="checkbox" name="viewPnt<%=j%>" id="viewPnt<%=j%>" value="<%=viewId%>"/><%=viewName%>
										</td>
											</tr>	
											
											 <%
											 }
											j++; }
										}
									


							%>
							<input type="hidden" name="viewPntCnt" id="viewPntCnt" value="<%=viewPoint.size()%>">	
							
							<input type="hidden" name="usrCrit" id="usrCrit" value="<%=usrCrit%>">	
							
				  
						  
						  
						  
						  
						  
						  
						  
						  <tr> 
							<td colspan="2" class="alignCenter">
							<input type="submit" value="Assign" class="gradBtn" />
						    <input name="button" type="button" class="gradBtn" value="Cancel" onClick="history.back();"/></td>
						  </tr>
						  <tr> 
							<td colspan="2" class="alignCenter">&nbsp;<!-- DO NOT DELETE THIS ROW -->
                          <!--/td>
						  </tr!-->
						  <div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						  <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
								<button name="submit" type="submit" class="button-add" value="Assign">Assign</button>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
								<button name="button" type="button" class="button-dang" value="Cancel" onclick="history.back();">Cancel</button>
							</div>
						</div>
						
						
                         
                    </form>
				</div>
			</div>
		</div>
	</div>
              
<!--========================================================
FOOTER
=========================================================-->
  <div>
   
    
    <!-- FOOTER STARTS HERE -->
    
  		<%@ include file = "../../include/menschForceFooter.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
  </div>  

</body>
</html>

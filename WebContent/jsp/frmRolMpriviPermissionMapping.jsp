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
<%@ page import="java.util.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.hlccommon.util.*"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
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
</head>

<script language="javascript">
	function privPerValidate(){
		if(document.frmMeeRoleMgnPriPerm.privId.value==""){
			alert("Select any one privilege.");
			document.frmMeeRoleMgnPriPerm.privId.focus();
			return false;
		}
		return true;
	}
	
	
	function postData(){
		if(document.frmMeeRoleMgnPriPermSelect.privId.value!=""){
			document.frmMeeRoleMgnPriPermSelect.roleProcess.value = "privPermissionSelect";
			//alert(frmRewalList.eventProcess.value);
			document.frmMeeRoleMgnPriPermSelect.method="post";
			document.frmMeeRoleMgnPriPermSelect.action="roles.html";
			document.frmMeeRoleMgnPriPermSelect.submit();
		}
		else{
			document.frmMeeRoleMgnPriPermSelect.privId.focus();
		}
	}
	
	
	function clearField(obj)
{

	for(var i=0;i<obj.elements.length;i++)
	{
		if(obj.elements[i].type=='text')
		{
			obj.elements[i].value = "";
		}
	}
}
</script>

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
	String privId = (String) request.getAttribute("privId");
	if(privId==null){
		privId = "";
	}

%>

<!--=======content================================-->

<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">
			
				<div class="col-lg-12 col-md-12 col-sm-12">
					
						<h3 class="title">Map : Privilege Permission Mapping</h3>
				  
				 </div>
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-2 col-md-2 col-sm-2">
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8">
						 <h4><font color="">Fill the required Menu Name for the link to be shown and it's&nbsp;relevant Link URL too.</font></h4>
				   </div>
				 </div>
				 
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>


                      <form  action="roles.html" name="frmMeeRoleMgnPriPermSelect" id="frmMeeRoleMgnPriPermSelect" >
                        <input type="hidden" name="roleProcess" value="" />
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Select a Privilege<span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							<select name="privId" id="privId" class="form-control" onchange="postData();">
                              <option selected="selected" value="">Select One</option>
                              <%
									ArrayList arrayPrivList = (ArrayList)request.getAttribute("privillegeDetails");
									if(arrayPrivList!=null && arrayPrivList.size()!=0){
										Iterator itPrivList = arrayPrivList.iterator();
										while(itPrivList.hasNext()){
											String[] priList = (String [])itPrivList.next(); //{privilegeId, privilegeName, privilegePath};
											String privilegeId = priList[0];
											String privilegeName = priList[1];
											if(privId.equals(privilegeId)){
											%>
                              <option value="<%=privilegeId%>" selected="selected"><%=privilegeName%></option>
                              <%
											 }
											 
											 else{
											 %>
                              <option value="<%=privilegeId%>"><%=privilegeName%></option>
                              <%
											 }
										}
									}
							%>
                            </select>
							</div> 
							
						</div>
                     </form>
					 
					 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
					 
					 
                    <form action="roles.html" name="frmMeeRoleMgnPriPerm" id="frmMeeRoleMgnPriPerm" onsubmit="return privPerValidate();">
                        <input type="hidden" name="roleProcess" value="mappingPrivPermission" />
                        <input type="hidden" name="privId" value="<%=privId%>"/>
                        <%
						    String viewId = "";
							String deleteId = "";
							String createId = "";
							String editId = "";
							String viewName = "";
							String viewDes = "";
							String viewURL = "";
							String editName = "";
							String editDes = "";
							String editURL = "";
							String createName = "";
							String createDes = "";
							String createUrl = "";
							String deleteName = "";
							String deleteDes = "";
							String deleteURL = "";
							String mapCreateId = "";
							String mapEditId = "";
							String mapViewId = "";
							String mapDeleteId = "";
							//String editStatus = "";
							String perm1 = "";
							String perm2 = "";
							String perm3 = "";
							String perm4 = "";
							String perm5 = "";
							String perm6 = "";
							String perm7 = "";
							String perm8 = "";

							String permName1 = "";
							String permDes1 = "";
							String permURL1 = "";
							String permName2 = "";
							String permDes2 = "";
							String permURL2 = "";
							String permName3 = "";
							String permDes3 = "";
							String permURL3 = "";
							String permName4 = "";
							String permDes4 = "";
							String permURL4= "";
							String permName5 = "";
							String permDes5 = "";
							String permURL5 = "";
							String permName6 = "";
							String permDes6 = "";
							String permURL6 = "";
							String permName7 = "";
							String permDes7 = "";
							String permURL7= "";
							String permName8 = "";
							String permDes8 = "";
							String permURL8 = "";

						
							String mapPermId1 = "";
							String mapPermId2 = "";
							String mapPermId3 = "";
							String mapPermId4 = "";
							String mapPermId5 = "";
							String mapPermId6 = "";
							String mapPermId7 = "";
							String mapPermId8 = "";
							
							String editStatus = "";
							
																												
							// Get Selected PrivMapid
							ArrayList arrayMapPriPermList = (ArrayList)request.getAttribute("mapDetails");
							if(arrayMapPriPermList!=null && arrayMapPriPermList.size() !=0){
								Iterator itMapPrivPermList = arrayMapPriPermList.iterator();
									while(itMapPrivPermList.hasNext()){
										editStatus = "editMapPrivPerm";
										String[] mapPrivPer = (String [])itMapPrivPermList.next(); 
										//{mapPermissionId, permissionName, accessName, accessUrl};
										String mapId = mapPrivPer[0];
										String accessName = mapPrivPer[2];
										String accessDes = mapPrivPer[3];
										String accessUrl = mapPrivPer[4];
										String mapPermissionName = mapPrivPer[1];
										if(mapPermissionName.equalsIgnoreCase("View")) {
											mapViewId = mapId;
											viewName = accessName;
											viewDes = accessDes;
											viewURL = accessUrl;
										}
										else if(mapPermissionName.equalsIgnoreCase("Delete")) {
											mapDeleteId = mapId;
											deleteName = accessName;
											deleteDes = accessDes;
											deleteURL = accessUrl;
										}
										else if(mapPermissionName.equalsIgnoreCase("Add")) {
											mapCreateId = mapId;
											createName = accessName;
											createDes = accessDes;
											createUrl = accessUrl;
										}
										else if(mapPermissionName.equalsIgnoreCase("Edit")) {
											mapEditId = mapId;
											editName = accessName;
											editDes = accessDes;
											editURL = accessUrl;
										}
										else if(mapPermissionName.equalsIgnoreCase("permission1")) {
											mapPermId1 = mapId;
											permName1 = accessName;
											permDes1 = accessDes;
											permURL1 = accessUrl;
										}
										else if(mapPermissionName.equalsIgnoreCase("permission2")) {
											mapPermId2 = mapId;
											permName2 = accessName;
											permDes2 = accessDes;
											permURL2 = accessUrl;
										}
										else if(mapPermissionName.equalsIgnoreCase("permission3")) {
											mapPermId3 = mapId;
											permName3 = accessName;
											permDes3 = accessDes;
											permURL3 = accessUrl;
										}
										else if(mapPermissionName.equalsIgnoreCase("permission4")) {
										mapPermId4 = mapId;
											permName4 = accessName;
											permDes4 = accessDes;
											permURL4 = accessUrl;
										}
										else if(mapPermissionName.equalsIgnoreCase("permission5")) {
											mapPermId5 = mapId;
											permName5 = accessName;
											permDes5 = accessDes;
											permURL5 = accessUrl;
										}
										else if(mapPermissionName.equalsIgnoreCase("permission6")) {
											mapPermId6 = mapId;
											permName6 = accessName;
											permDes6 = accessDes;
											permURL6 = accessUrl;
										}
										else if(mapPermissionName.equalsIgnoreCase("permission7")) {
											mapPermId7 = mapId;
											permName7 = accessName;
											permDes7 = accessDes;
											permURL7 = accessUrl;
										}
										else if(mapPermissionName.equalsIgnoreCase("permission8")) {
											mapPermId8 = mapId;
											permName8 = accessName;
											permDes8 = accessDes;
											permURL8 = accessUrl;
										}
									}
							}
							
							// Get All Permission
							ArrayList arrayPermList = (ArrayList)request.getAttribute("permissionDetails");
								if(arrayPermList!=null && arrayPermList.size()!=0){
									Iterator itPermList = arrayPermList.iterator();
									while(itPermList.hasNext()){
										String [] perList = (String [])itPermList.next(); //{permissionId, permissionName};
										String permissionId = perList[0];
										String permissionName = perList[1];
										if(permissionName.equalsIgnoreCase("View")) viewId = permissionId;
										if(permissionName.equalsIgnoreCase("Delete")) deleteId = permissionId;
										if(permissionName.equalsIgnoreCase("Add")) createId = permissionId;
										if(permissionName.equalsIgnoreCase("Edit")) editId = permissionId;
										if(permissionName.equalsIgnoreCase("permission1")) perm1 = permissionId;
										if(permissionName.equalsIgnoreCase("permission2")) perm2 = permissionId;
										if(permissionName.equalsIgnoreCase("permission3")) perm3 = permissionId;
										if(permissionName.equalsIgnoreCase("permission4")) perm4 = permissionId;
										if(permissionName.equalsIgnoreCase("permission5")) perm5 = permissionId;
										if(permissionName.equalsIgnoreCase("permission6")) perm6 = permissionId;
										if(permissionName.equalsIgnoreCase("permission7")) perm7 = permissionId;
										if(permissionName.equalsIgnoreCase("permission8")) perm8 = permissionId;
									}
								}
								//	out.print("editStatus:" + editStatus);
								if(editStatus.equals("editMapPrivPerm")){
								%>
                        <input type="hidden" name="editStatus" value="editMapPrivPerm" />
                        <%if(mapViewId!=null && mapViewId!=""){ %>
                        <input type="hidden" name="viewId" value="<%=mapViewId%>" />
                        <%}else{ %>
                        <input type="hidden" name="viewId" value="<%=viewId%>" />
                        <%} %>
                        <%if(mapEditId!=null && mapEditId!=""){ %>
                        <input type="hidden" name="editId" value="<%=mapEditId%>" />
                        <%}else{ %>
                        <input type="hidden" name="editId" value="<%=editId%>" />
                        <%} %>
                        <%if(mapDeleteId!=null && mapDeleteId!=""){ %>
                        <input type="hidden" name="deleteId" value="<%=mapDeleteId%>" />
                        <%}else{ %>
                        <input type="hidden" name="deleteId" value="<%=deleteId%>" />
                        <%} %>
                        <%if(mapCreateId!=null && mapCreateId!=""){ %>
                        <input type="hidden" name="createId" value="<%=mapCreateId%>" />
                        <%}else{ %>
                        <input type="hidden" name="createId" value="<%=createId%>" />
                        <%} %>
                        <%if(mapPermId1!=null && mapPermId1!=""){ %>
                        <input type="hidden" name="permissionId1" value="<%=mapPermId1%>" />
                        <%}else{ %>
                        <input type="hidden" name="permissionId1" value="<%=perm1%>" />
                        <%} %>
                        <%if(mapPermId2!=null && mapPermId2!=""){ %>
                        <input type="hidden" name="permissionId2" value="<%=mapPermId2%>" />
                        <%}else{ %>
                        <input type="hidden" name="permissionId2" value="<%=perm2%>" />
                        <%} %>
                        <%if(mapPermId3!=null && mapPermId3!=""){ %>
                        <input type="hidden" name="permissionId3" value="<%=mapPermId3%>" />
                        <%}else{ %>
                        <input type="hidden" name="permissionId3" value="<%=perm3%>" />
                        <%} %>
                        <%if(mapPermId4!=null && mapPermId4!=""){ %>
                        <input type="hidden" name="permissionId4" value="<%=mapPermId4%>" />
                        <%}else{ %>
                        <input type="hidden" name="permissionId4" value="<%=perm4%>" />
                        <%} %>
                        <%if(mapPermId5!=null && mapPermId5!=""){ %>
                        <input type="hidden" name="permissionId5" value="<%=mapPermId5%>" />
                        <%}else{ %>
                        <input type="hidden" name="permissionId5" value="<%=perm5%>" />
                        <%} %>
                        <%if(mapPermId6!=null && mapPermId6!=""){ %>
                        <input type="hidden" name="permissionId6" value="<%=mapPermId6%>" />
                        <%}else{ %>
                        <input type="hidden" name="permissionId6" value="<%=perm6%>" />
                        <%} %>
                        <%if(mapPermId7!=null && mapPermId7!=""){ %>
                        <input type="hidden" name="permissionId7" value="<%=mapPermId7%>" />
                        <%}else{ %>
                        <input type="hidden" name="permissionId7" value="<%=perm7%>" />
                        <%} %>
                        <%if(mapPermId8!=null && mapPermId8!=""){ %>
                        <input type="hidden" name="permissionId8" value="<%=mapPermId8%>" />
                        <%}else{ %>
                        <input type="hidden" name="permissionId8" value="<%=perm8%>" />
                        <%} %>
                        <%
								}
								else{
								%>
                        <input type="hidden" name="editStatus" value=" " />
                        <input type="hidden" name="viewId" value="<%=viewId%>" />
                        <input type="hidden" name="deleteId" value="<%=deleteId%>" />
                        <input type="hidden" name="createId" value="<%=createId%>" />
                        <input type="hidden" name="editId" value="<%=editId%>" />
                        <input type="hidden" name="permissionId1" value="<%=perm1%>" />
                        <input type="hidden" name="permissionId2" value="<%=perm2%>" />
                        <input type="hidden" name="permissionId3" value="<%=perm3%>" />
                        <input type="hidden" name="permissionId4" value="<%=perm4%>" />
                        <input type="hidden" name="permissionId5" value="<%=perm5%>" />
                        <input type="hidden" name="permissionId6" value="<%=perm6%>" />
                        <input type="hidden" name="permissionId7" value="<%=perm7%>" />
                        <input type="hidden" name="permissionId8" value="<%=perm8%>" />
                        <%
								}
						  %>
						  
						  <div class="col-lg-12 col-md-12 col-sm-12">
						  <div class="table-row-line wrapper header">
							
							<div class="col-lg-2 col-md-2 col-sm-1">
							<label class="name form-div-6 subtitle" >
							Permission
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<label class="name form-div-6 subtitle" >
							Description
							</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<label class="name form-div-6 subtitle">
							Application Name 
                            </label>
							</div> 
							<div class="col-lg-3 col-md-3 col-sm-3">
							<label class="name form-div-6 subtitle">
							Application URL
                            </label>
							</div> 
							</div>
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="table-row-line">
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
							Permission1
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<textarea name="viewDes" class="form-control" placeHolder="Description"><%=viewDes%></textarea>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="viewName" type="text" class="form-control" value="<%=viewName%>" />
							</div> 
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="viewURL" type="text" class="form-control" value="<%=viewURL%>"/>
							</div> 
							</div>
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="table-row-line">
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
							Permission2
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<textarea name="editDes" class="form-control" placeHolder="Description"><%=editDes%></textarea>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="editName" type="text" class="form-control" size="25" value="<%=editName%>" />
							</div> 
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="editURL" type="text" class="form-control" size="35" value="<%=editURL%>"/>  
							</div> 
							</div>
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="table-row-line">
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
							Permission3
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<textarea name="createDes" class="form-control" placeHolder="Description"><%=createDes%></textarea>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="createName" type="text" class="form-control" value="<%=createName%>"/> 
							</div> 
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="createUrl" type="text" class="form-control" value="<%=createUrl%>"/>
							</div> 
							</div>
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="table-row-line">
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
							Permission4
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<textarea name="deleteDes" class="form-control" placeHolder="Description"><%=deleteDes%></textarea>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="deleteName" type="text" class="form-control" value="<%=deleteName%>"/> 
							</div> 
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="deleteURL" type="text" class="form-control" value="<%=deleteURL%>"/> 
							</div> 
							</div>
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="table-row-line">
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
							Permission5
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<textarea name="perDes1" class="form-control" placeHolder="Description"><%=permDes1%></textarea>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="perName1" type="text" class="form-control" size="25" value="<%=permName1%>" /> 
							</div> 
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="perURL1" type="text" class="form-control" size="35" value="<%=permURL1%>"/> 
							</div> 
							</div>
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="table-row-line">
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
							Permission6
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<textarea name="perDes2" class="form-control" placeHolder="Description"><%=permDes2%></textarea>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="perName2" type="text" class="form-control" size="25" value="<%=permName2%>" />
							</div> 
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="perURL2" type="text" class="form-control" size="35" value="<%=permURL2%>"/> 
							</div> 
							</div>
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="table-row-line">
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
							Permission7
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<textarea name="perDes3" class="form-control" placeHolder="Description"><%=permDes3%></textarea>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="perName3" type="text" class="form-control" size="25" value="<%=permName3%>"/>
							</div> 
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="perURL3" type="text" class="form-control" size="35" value="<%=permURL3%>"/> 
							</div> 
						</div>
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="table-row-line">
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
							Permission8
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<textarea name="perDes4" class="form-control" placeHolder="Description"><%=permDes4%></textarea>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="perName4" type="text" class="form-control" value="<%=permName4%>"/>
							</div> 
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="perURL4" type="text" class="form-control" value="<%=permURL4%>"/> 
							</div> 
							</div>
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="table-row-line">
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
							Permission9
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<textarea name="perDes5" class="form-control" placeHolder="Description"><%=permDes5%></textarea>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="perName5" type="text" class="form-control" value="<%=permName5%>"/>
							</div> 
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="perURL5" type="text" class="form-control" value="<%=permURL5%>"/>
							</div> 
						</div>
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="table-row-line">
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
							Permission10
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<textarea name="perDes6" class="form-control" placeHolder="Description"><%=permDes6%></textarea>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="perName6" type="text" class="form-control" value="<%=permName6%>"/>
							</div> 
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="perURL6" type="text" class="form-control" value="<%=permURL6%>"/> 
							</div> 
							</div>
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="table-row-line">
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
							Permission11
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<textarea name="perDes7" class="form-control" placeHolder="Description"><%=permDes7%></textarea>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="perName7" type="text" class="form-control" value="<%=permName7%>"/> 
							</div> 
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="perURL7" type="text" class="form-control" value="<%=permURL7%>"/>
							</div> 
						</div>
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="table-row-line">
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
							Permission12
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<textarea name="perDes8" class="form-control" placeHolder="Description"><%=permDes8%></textarea>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="perName8" type="text" class="form-control" value="<%=permName8%>"/>
							</div> 
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="perURL8" type="text" class="form-control" value="<%=permURL8%>"/> 
							</div> 
						</div>
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-5 col-md-5 col-sm-5">
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
								<button name="submit" type="submit" class="button-add" value="Submit">Submit</button>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
								<button name="button" type="button" class="button-dang" value="Clear" onclick = "clearField(this.form)">Clear</button>
								
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
    
  		<%@ include file = "../../include/FooterProcess.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
  </div>  
</body>
</html>

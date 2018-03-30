<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>menschForce</title>
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
     el = document.forms['frmRoleEnt'].elements;
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
	var chkValue = "";
	e = document.forms['frmRoleEnt'].elements;
	var count =0;
	for(i=0 ; i< e.length; i++){
		if(e[i].type == "checkbox"){
			 if(e[i].checked == true && e[i].value != "ChkAll") {
				 count++;
				 chkValue +=  e[i].value + "#";
			}
		}
	}
		//alert(chkValue);
		document.frmRoleEnt.entityIds.value = chkValue;
}

function entPrivValidate(){
	if(document.frmRoleEnt.roleId.value==""){
		alert("Select any one Role.");
		document.frmRoleEnt.roleId.focus();
		return false;
	}
	DelAll();
	return true;
}
	
	
function postData(){
	if(document.frmRoleEntSelect.roleId.value!=""){
		document.frmRoleEntSelect.roleProcess.value = "roleEntSelect";
		//alert(frmRewalList.eventProcess.value);
		document.frmRoleEntSelect.method="post";
		document.frmRoleEntSelect.action="roles.html";
		document.frmRoleEntSelect.submit();
	}
	else{
		alert("Select any one Role");
		document.frmRoleEntSelect.roleId.focus();
	}
}


function clearField(obj)
{

	for(var i=0;i<obj.elements.length;i++)
	{
		
		if(obj.elements[i].type=='checkbox')
		{
			obj.elements[i].checked = false;
		}
	}
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
	String roleRoleId = (String) request.getAttribute("roleId");
	if(roleRoleId==null){
		roleRoleId = "";
	}
	//String privId = "";

%>


<!--=======content================================-->

<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
           
		   <div class="row">
			
				<div class="col-lg-12 col-md-12 col-sm-12">
					
                   <h3 class="title">Map : Role Entity Mapping</h3>
				  
				 </div>
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-2 col-md-2 col-sm-2">
					</div>
					<div class="col-lg-10 col-md-10 col-sm-10">
						 <h4><font color="#018dce">You can map an Entity or Multiple Entities for a given role as given below.</font></h4>
				   </div>
				 </div>
				 
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>

                      <form name="frmRoleEntSelect" id="frmRoleEntSelect" >
                        <input type="hidden" name="roleProcess" value="" />
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
							Select a Role :
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<select name="roleId" id="roleId" class="form-control" onchange="postData();">
                              <option selected="selected" value="">Select One</option>
                              <%
									ArrayList arrayRoleList = (ArrayList)request.getAttribute("roleDetails");
									if(arrayRoleList!=null && arrayRoleList.size()!=0){
										Iterator itRoleList = arrayRoleList.iterator();
										while(itRoleList.hasNext()){
											String[] rRoleList = (String [])itRoleList.next();
											//String[] roleList = {roleId, roleName};
											String rRolId = rRoleList[0];
											String roleRoleName = rRoleList[1];
											if(rRolId.equals(roleRoleId)){
											%>
                              <option value="<%=rRolId%>" selected="selected"><%=roleRoleName%></option>
                              <%
											 }
											 
											 else{
											 %>
                              <option value="<%=rRolId%>"><%=roleRoleName%></option>
                              <%
											 }
										}
									}
							%>
                            </select>
							</div> 
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
						</div>
						
                      </form>
					  
					  <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
					  
                    <form name="frmRoleEnt" id="frmRoleEnt" onsubmit="return entPrivValidate();">
                        <input type="hidden" name="roleProcess" value="mappingRoleEnt" />
                        <input type="hidden" name="entityIds" value="sury" />
                        <input type="hidden" name="roleId" value="<%=roleRoleId%>" />
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
							Accessible Entity(ies) :
                            </label>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							<input  type="checkbox" name="chkAll" value="ChkAll" alt="Select or Deselect All" onclick="checkAll();">
                                Select All
							</div> 
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
						</div>
						
                              <%
									ArrayList arrayEntityList = (ArrayList)request.getAttribute("enityDetails");
									if(arrayEntityList!=null && arrayEntityList.size()!=0){
										Iterator itEntList = arrayEntityList.iterator();
										while(itEntList.hasNext()){
											String[] entList = (String [])itEntList.next();
											//String[] entList = {entityId, entityName};
											String roleEntityId = entList[0];
											String roleEntityName = entList[1];
											boolean entIdStatus = false;
											ArrayList arrayMapRoleEntList = (ArrayList)request.getAttribute("mapDetails");
											if(arrayMapRoleEntList!=null && arrayMapRoleEntList.size()!=0){
												Iterator itRoleEntList = arrayMapRoleEntList.iterator();
												while(itRoleEntList.hasNext()){
													String[] mapEntList = (String [])itRoleEntList.next();
													//{mapEntityId, roleIdVal, entityId, roleName, entityName};
													String entId = mapEntList[2];
													if(roleEntityId.equals(entId)){
														entIdStatus = true;
														break;
													}
												}
											}
											if(entIdStatus==true){
											%>
											
											
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							
							<div class="col-lg-4 col-md-4 col-sm-4">
							<input type="checkbox" name="checkbox5" checked="checked" value="<%=roleEntityId%>" />
							<%=roleEntityName%>
                               
							</div> 
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
						</div>
											
                              
                              <%}else{%>
							  
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							
							<div class="col-lg-4 col-md-4 col-sm-4">
							<input type="checkbox" name="checkbox5" value="<%=roleEntityId%>" />
							<%=roleEntityName%>
                               
							</div> 
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
						</div>
                              
                              <%
											 }
										}
									}
									%>
									
									
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
					   <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
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
    
  		<%@ include file = "../../include/menschForceFooter.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
  </div>  
</body>
</html>

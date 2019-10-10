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
<%@ page import="java.lang.*"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<script src="js/basic.js" type="text/javascript" ></script>
<script src="js/frmrRolePrevilageValidate.js" type="text/javascript" ></script>

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
<script>
    function cancelEditRole()
    {
        if(confirm("Do you want to Cancel and go back to List Page?"))
	{
        strURL = "./roles.html?roleProcess=roleList";
	window.location.href = strURL;
        }
       else
	{
		return;
	}
    }
	function clearField(obj)
{

	for(var i=0;i<obj.elements.length;i++)
	{
		if(obj.elements[i].type=='text')
		{
			obj.roledesc.value = "";
		}

		if(obj.elements[i].type=='radio')
		{
			obj.elements[i].checked = false;
		}
	}
}

</script>
<!--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
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
	
	<div class="content indent">
    
   <div class="thumb-box14">
        <div class="container">
            <div class="row">
			
					<div class="col-lg-12 col-md-12 col-sm-12">
					
						<h3 class="title">Maintain Roles : Edit</h3>
					</div>
				
				
				  <div class="col-lg-12 col-md-12 col-sm-12">	
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-8 col-md-2 col-sm-2">
							<label class="name form-div-6">
									Edit/Update the Role 
						</label>
					</div>
				</div>
	
				
                      <%
							String roleId = "";
							String rolName = "";
                                                        String roledesc = "";
                                                         String status = "";

							String[] s = (String[])request.getAttribute("roleDetails");
							System.out.print("Role Details:" + s);
							if(s!=null){
								roleId = s[0];
								rolName = s[1];
                                                                roledesc= s[2];
                                                                status =s[3];
							}
						%>
                    
                         <form name="frmRoleMgtRoleEdit" id="frmRoleMgtRoleEdit" method="post" action="roles.html" onsubmit="return formValidate(this);">
                       
                        <input type="hidden" name="roleProcess"	value="editRole"/>
                        <input type="hidden" name="roleId" value="<%=roleId%>"/>
						
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-8 col-md-2 col-sm-2">
							<label class="name form-div-6">
								<font color="#FF0000">Fields marked with an asterisk (*) are required.</font>
							</label>
							</div>
						</div>
	
                       <div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
							</div>
                            
                          
                          <%
						  	String err = request.getParameter("err");
						  	if(err!=null) {
						  %>
						  
                <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-2 col-md-2 col-sm-2">
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8">
					<label class="name form-div-6">
						<div class="styleerror">Role already exist</div>
						</label>
					</div>
				</div>
				
                          <%
							  rolName=(String)request.getAttribute("rolname");
						  }
				         %>
						 
						 
						  <div class="col-lg-12 col-md-12 col-sm-12">	
						  
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">Role:</label>
							 </div>
							 <div class="col-lg-3 col-md-3 col-sm-3">
              		         <input name="rolename" id="rolename" type="text" class="form-control" value="<%=rolName%>" readonly="true"/>
							 </div>
						</div>
						
							
							<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
							</div>
							
							
							
						  <div class="col-lg-12 col-md-12 col-sm-12">	
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
                     			Description
							 </label>
							 </div>
							 <div class="col-lg-3 col-md-3 col-sm-3">
								<input name="roledesc" id="roledesc" type="text" class="form-control" value="<%=roledesc%>" />
							 </div>
						</div>
							
							<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
							</div>
						 
                        <div class="col-lg-12 col-md-12 col-sm-12">	
						  
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
                     			Status
							 </label>
							 </div>
                           
                            <%   if(Integer.parseInt(status)==1) { %>
																			
							 <div class="col-lg-4 col-md-4 col-sm-4">
              		      <input type="radio" name="status" id="status1" value="1" checked="checked" />
                              Active
                              <input type="radio" name="status" id="status2" value="0" />
                            Inactive  <font color="#FF0000">*</font> 
							  	</div>
																			
                        
                            <% }else { %>
																	
								 <div class="col-lg-4 col-md-4 col-sm-4">
									<input type="radio" name="status" id="status1" value="1" />Active
                              
                              <input type="radio" name="status" id="status2" value="0" checked="checked"/>
                            Inactive  <font color="#FF0000">*</font>
							  	</div>
							
                            <% } %> 
							
							</div>
						
                          <%--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011--%>
						  
							<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
							</div>	
						  <div class="col-lg-12 col-md-12 col-sm-12">
						   
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
							<button name="submit" type="submit" class="button-add" value="Update">update</button>
								</div>
                             <div class="col-lg-1 col-md-1 col-sm-1">	
							
						       <button  name="button" type="button" class="button-edit" value="Clear" onclick = "clearField(this.form)">Clear</button>
						     </div>
							  <div class="col-lg-1 col-md-1 col-sm-1">	
							
						      <button name="button" type="button" class="button-dang" value="Cancel" onclick ="cancelEditRole()">Cancel</button>
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

<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "javax.sql.*" %>
<%@ page import = "java.util.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<script src="js/basic.js" type="text/javascript" ></script>
<script src="js/frmRolMCreatePermissionValidate.js" type="text/javascript" ></script>

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
        strURL = "./roles.html?roleProcess=permissionList";
	window.location.href = strURL;
        }
       else
	{
		return;
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
	
<div class="content indent">
    
   <div class="thumb-box14">
        <div class="container">
            <div class="row">
			
			<div class="col-lg-12 col-md-12 col-sm-12">
					
						<h3 class="title">Roles &amp; Privileges :  Edit Permission</h3>
					
				</div>
				
				
				 <div class="col-lg-12 col-md-12 col-sm-12">	
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-8 col-md-2 col-sm-2">
							<label class="name form-div-6">
								<p>You can Edit/Update the  Permission  created by you as given below. </p>
						</label>
					</div>
				</div>
	
	
 
                
                
                  <%  
							String permissionId = "";
							String permissionName = ""; 
							String permissionDescrption = ""; 
							String[] s = (String[])request.getAttribute("permDetails");
							//out.print("Role Details:" + s);
							if(s!=null){
								permissionId = s[0];
								permissionName = s[1]; 
								permissionDescrption = s[2];
							}
						  %>
						  
					  <form name="frmRolMEditPermission" id="myform" action="roles.html" onsubmit="return myvalidate1();"><input name="roleProcess" type="hidden" value="editPerm" />
                      <input name="permissionId" type="hidden" value="<%=permissionId%>" />
                       
                          
                          <%
						  	String err = request.getParameter("err");
						  	if(err!=null) {
						  %>
                           
				<div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-8 col-md-8 col-sm-8 col-sm-offset-3">
						<font color="#FF0000">This Permission Name Already Exist</font>
					</div>
				</div>
                          <%
						  }
						  %>
						  
						   <div class="col-lg-12 col-md-12 col-sm-12">	
						   </div>
						   <div class="col-lg-12 col-md-12 col-sm-12">	
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
									<label class="name form-div-6">
									Permission Name
									
									 </label>
								 </div>
								 <div class="col-lg-3 col-md-3 col-sm-3">
									<input name="permissionName" type="text" id="permissionName" class="form-control" value="<%=permissionName%>" size="25">
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12">	
						   </div>
						   <div class="col-lg-12 col-md-12 col-sm-12">	
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
									<label class="name form-div-6">
									Permission Descrption
									
									 </label>
								 </div>
								 <div class="col-lg-3 col-md-3 col-sm-3">
									<textarea name="permissionDescrption"  id="permissionDescrption" class="form-control"><%=permissionDescrption%></textarea>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
							</div>
							
						  <div class="col-lg-12 col-md-12 col-sm-12">
						   
							<div class="col-lg-5 col-md-5 col-sm-5">
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
							
								<button name="submit" type="submit" class="button-add" value="Update">Update</button>
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

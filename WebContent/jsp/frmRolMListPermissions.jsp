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
			
					
						<h3 class="title"> Roles &amp; Privileges : Permission Listings </h3>
					
				</div>
				
				 <div class="col-lg-12 col-md-12 col-sm-12">	
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
							<div class="col-lg-8 col-md-2 col-sm-2">
							<label class="name form-div-6">
						You are viewing the list of Permissions created by you. To	edit	a permission click on the Edit button beside each record. To deactivate a permission click on the 'Deactivate' button beside the corresponding record.
						</label>
					</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
					
					 <div class="col-lg-6 col-md-6 col-sm-6">
					 <label class="name form-div-6 subtitle" >
					 Name of permission 
					 </label>
					 </div>
					 <div class="col-lg-4 col-md-4 col-sm-4">
					 <label class="name form-div-6 subtitle" >
					 Edit
					 </label>
					 </div>
					 </div>
               
                      
                       
                      <%  
					   ArrayList roleList = (ArrayList) request.getAttribute("allPermList");
      					 if(roleList!=null && roleList.size()!=0){
							Iterator it = roleList.iterator();
							while(it.hasNext()){
								String[] s = (String[])it.next();
								//String privilegeId = s[0];
								String permissionId = s[0];
								String permissionName = s[1];
							%>
                       <form name="frmRolMListPermissions" id="myform" action="roles.html" onsubmit="return myvalidate(this);">
                        <input name="roleProcess" type="hidden" value="initEditPerm" />
                        <input type="hidden" name="permissionId" value="<%=permissionId%>">
						
						
						
                        						
						<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
							
							
							 <div class="col-lg-6 col-md-6 col-sm-6">
							<%=permissionName%>
							</div>
							
                           <div class="col-lg-4 col-md-4 col-sm-4">
						  
                           <button name="Submit2" type="submit" class="button-edit" value="Edit" >Edit</button>
						 </div>
                       </div>
					   
						<div class="col-lg-2 col-md-2 col-sm-2">
						&nbsp;
							</div>
                      </form>
                    <!--
					  		<tr>
							<td height="25" colspan="5"  bgcolor="#ffffff" class="alignRight">
								<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
							<a href="#" class="linkThree">Prev</a> | <a href="#" class="linkThree">Next</a>			</td>
						   </tr>
						 -->
                      
                        <%
					   } 
					   }
					    else {
						%>
						
						
                     	<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-4 col-md-4 col-sm-4">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6">
						<h3> There is no Record available</h3>
					</div>
					</div>
				
                      <% } %>
					  
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

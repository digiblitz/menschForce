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


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<script src="js/basic.js" type="text/javascript" ></script>
<!--script src="js/frmMgtEntity.js" type="text/javascript" ></script>-->

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
<style>
#frmRoleMgtEntity label.error{
	color:red;
}
</style>
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
		
<script src="js/jquery.validate.js"></script>
<script src="js/additional-methods.js"></script>
<script src="js/profilefieldvalidation.js"></script>

	<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">
			
			<div class="col-lg-12 col-md-12 col-sm-12">
					
						<h3 class="title">Create an Entity</h3>
				
				</div>
				 <div class="col-lg-12 col-md-12 col-sm-12">	
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-8 col-md-2 col-sm-2">
							<label class="name form-div-6">
						Create an Entity for all the departments using our Online Services Dashboard, right here!
						</label>
					</div>
				</div>

 						<form name="frmRoleMgtEntityCreate" id="frmRoleMgtEntity" method="post" action="roles.html" onsubmit="return mgtEntityAdd();">
                      <input type="hidden" name="roleProcess" value="createEntity" />
					  
					  
                     
                        <%
						  	String err = request.getParameter("err");
						  	if(err!=null) {
						  %>
						  
                       <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-2 col-md-2 col-sm-2">
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8">
						This Permission Name Already Exist
					</div>
					</div>
                        <%
						  }
						  %>
						  <div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
							</div>
                       <div class="col-lg-12 col-md-12 col-sm-12">	
						  
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
                     		Entity Name<span class="asterisk">*</span>
							
							 </label>
							 </div>
							 <div class="col-lg-3 col-md-3 col-sm-3">
							 <input name="entityName" id="txtEntityName" type="text" class="form-control" size="25" />
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
                     		Entity Description<span class="asterisk">*</span>
							
							 </label>
							 </div>
							 <div class="col-lg-3 col-md-3 col-sm-3">
							 <textarea name="entityDes" id="txtEntityDes" class="form-control" ></textarea>
							 </div>
						</div>
							
							<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
							</div>
                      
					  <div class="col-lg-12 col-md-12 col-sm-12">
						   
							<div class="col-lg-5 col-md-5 col-sm-5">
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">	
                       		<button name="submit" type="submit" class="button-add" value="Create" >submit</button>
							</div>
                             <div class="col-lg-1 col-md-1 col-sm-1">	
                             <button name="button" type="reset" class="button-dang" value="Clear" >reset</button>
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

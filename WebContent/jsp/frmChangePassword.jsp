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
<link rel="stylesheet" href="css/bootstrap.css" >
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/contact-form.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/camera.css">
<link rel="stylesheet" href="css/jquery.fancybox.css">
<link rel="stylesheet" href="fonts/font-awesome.css">


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
 <script language="javascript" />

function remloadVal(){

if(document.changePassword.currPwd.value!=null){
document.changePassword.currPwd.value="";

}else{

document.changePassword.currPwd.value="";
}
return true;
		}

</script>
<style>
#contact-form label.error{
	color:red;
}
.asterisk{
	color:red;
}
</style>

	<% 
				String status = (String)request.getAttribute("status");
				String usrname=(String)request.getAttribute("username");
				String firstName=(String)request.getAttribute("firstName");
				String lastName=(String)request.getAttribute("lastName");
				System.out.println("a username-----------------"+	usrname);
								System.out.println("a firstName-----------------"+	firstName);
												System.out.println("a lastName-----------------"+	lastName);			%>
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
           
				
				
			
				
			
				<form name="changePassword" id="contact-form" method="post" action="user.html?cmd=chngPwd" onsubmit="return myvalidate(this);" >
				<input type="hidden" id="uname" name="uname" value="<%=usrname%>" />
				
			 <div class="row">	
				<div class="col-lg-12 col-md-12 col-sm-12">
					
                   <h3 class="title">Membership: Change Password </h3>
				  
				 </div>
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
				<div class="col-lg-12 col-md-12 col-sm-12"> 
					<div class="col-lg-3 col-md-3 col-sm-3">
					</div>
					<div class="col-lg-9 col-md-9 col-sm-9">
					<h4>
					<font color="#018dce">You can change your account Password right here.</font>
					</h4>
					</div>
				</div>
               
			   <div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
				<div class="col-lg-12 col-md-12 col-sm-12"> 
					<div class="col-lg-3 col-md-3 col-sm-3">
					</div>
					<div class="col-lg-9 col-md-9 col-sm-9">
					    <h4>Change Password</h4>
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
					<%if(status != null && status.equals("fail")){ %>
				<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-3">
						<span class="asterisk">Please check the password you entered</span>	
						</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div>
					<%}%>
			</div>
				<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">	Current Password <span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							  <input type="password" name="currPwd" id="currPwd" class="form-control" placeholder="Current Password"/>
							
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">	New Password <span class="asterisk">*</span></label>
								
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							  <input type="password" name="newPwd" id="newPwd" class="form-control" placeholder="New Password"/>
							
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">Re-Type New Password <span class="asterisk">*</span>	</label>
								
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							   <input type="password" name="reNewPwd" id="reNewPwd" class="form-control" placeholder="Re-Type New Password"/>
							
							</div>
							
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
					</div>
				 
			   
			  
			   <div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-sm-5">
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
						   <button type="submit" class="button-add" name="submit" value="Change">Change</button>
						 </div>
						 <div class="col-lg-1 col-md-1 col-sm-1">
						   <button type="button" class="button-dang" name="button" value="cancel" onclick="javascript:history.back(-1);">cancel</button>
						  </div>
						  
					</div>
			</div>
				  
			
			 
			 
			 </form>
                </div>
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

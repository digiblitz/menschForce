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
<title>menschForce</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<script src="js/basic.js" type="text/javascript" ></script>
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
<!--LINK HREF="../../css/core-ie.css" TYPE="text/css" REL="stylesheet" /--> 
</head>


<body>


<%@ include file = "../../include/HeaderProcess.jsp" %>
<div class="content">


  <table width=100% border="0" cellpadding="0" cellspacing="0" height="380px"  align="center">
    
    <tr>
	
      
     
      <td  align="center" valign="middle" class="tableCommonBg" >
	  <table width="887"  border="1" align="center" cellpadding="0" cellspacing="0" id="mainTab" style="border:thin;border-style:groove;">
          <td width="887" height="28" ><img src="img/register_icon.png" alt="" width="20px" height="20px" /><strong>&nbsp;Register for SignIn</strong></td>
        </tr>
        <tr>
          <td align="center" valign="middle"><div class="divider2"></div>
		  		  <form name="memberUsrSgnUp" id="myform" method="post" action="user.html?cmd=initUsr" onsubmit="return myvalidate(this);" >

             <table width="887" border="1" cellspacing="0" cellpadding="0">
                <tr>
                  <td colspan="2" align="center"><label style="color:#FF0000";>
				    <%
			String status = (String) request.getAttribute("status");
            if (status != null) {
                if (status.equals("fail")) {
				System.out.println("Status" + status);%>
				
	<div class="styleError">Invalid Login! Try logging in again.</div>
	<%} else if (status.equals("expired")) {%>
	<div class="styleError">License Expired.</div>
	 <% } else if (status.equals("conFailed")) {%>
	 <div class="styleError">Connection Problem ! Please Login Again.</div>
	 <% } else if (status.equals("users")) {%>
	 <div class="styleError">Licensed Users Exceeded.</div>
	 <% } } %>
</label> <br />Please click <strong>SignIn</strong>!! again or create an account in our new system by clicking <strong>Register</strong> button.</td>
                </tr>
                <tr>
                  <td colspan="2" align="center">&nbsp;</td>
                </tr>
                <tr>
                  <!--td width="254" height="29" align="right"><a href="user.html?cmd=initUsrReg" ><img src="img/register_button.jpg" alt="" width="20px" height="20px"/>&nbsp;<strong>Register</strong></a>
                    &emsp;</td-->
                  <td width="252" align="center" colspan="2">&nbsp;
					  <a href="login.html?cmd=initLogin" ><img src="img/signIn_icon.jpg" alt="" width="20px" height="20px"/>&nbsp;<strong>Sign In</strong></a>
					  </td>
                </tr>
              </table>
			  </form>
            </td>
        </tr>
      </table></td>
      
	  
    </tr>
    
  </table></div>
<!--=======footer=================================-->
 		 
			
                    <!-- FOOTER STARTS HERE -->
                        <%@ include file = "../../include/FooterProcess.jsp" %>
						
                    <!-- FOOTER ENDS HERE -->
               

</body>
</html>

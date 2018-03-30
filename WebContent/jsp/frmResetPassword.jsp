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
<script src="js/validate.js" type="text/javascript" ></script>
<script src="js/resetPassword.js" type="text/javascript" ></script>

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


<header id="header">
<!-- HEADER STARTS HERE -->
<%@ include file = "../../include/menschForceHeader.jsp" %>
  <!-- HEADER ENDS HERE -->
</header>
<div class="content">
<br /><br />
  <table width=100% height="350" border="0" cellpadding="0" cellspacing="0" class="content_new" align="center">
    
    <tr>
	
      <td  align="center" valign="middle" class="tableCommonBg" >
	  <table width="630" border="1" cellpadding="0" cellspacing="0" class="tblInnerContainer" height="100" style="border:thin;border-style:groove;">
        <tr valign="middle">
          <td height="19" colspan="2" class="tblMainHead" > <strong>Account Settings: Reset Password </strong><div class="divider3"></div></td>
        </tr>
        <tr>
          <td colspan="2" class="tblDescrp">&nbsp;</td>
        </tr>
        <tr>
          <td align="center" valign="middle"><form name="frmUserSignup" id="myform" method="post" action="user.html?cmd=resetpw" onsubmit="return myvalidate();">
              <table width="400" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">
                <input type="hidden" name="process" value="resetpw" />
                <tr >
                  <td height="29" colspan="2" valign="middle" class="tblRowHead"><strong> Reset Your Password </strong></td>
                </tr>
                <tr>
                  <td height="30" valign="middle" class="tableLeft">Enter your Password:</td>
                  <td valign="middle" class="tableRight"><input type="password" name="Password" id="password_id" class="textboxOne"  size="20" /></td>
                </tr>
                <tr>
                  <td height="31" valign="middle" class="tableLeft">Re-Type your Password:</td>
                  <td valign="middle" class="tableRight"><input type="password" name="rpwd" id="rpwd" class="textboxOne"  size="20" /></td>
                </tr>
                <tr>
                  <td height="33" colspan="2" align="center" valign="middle" class="alignCenter"><input name="submit" type="submit" value="Reset Password" class="gradBtn" /></td>
                </tr>
                <tr>
                  <td colspan="2" height="25" class="alignCenter">&nbsp;</td>
                </tr>
              </table>
          </form></td>
        </tr>
      </table></td>
    </tr>
  </table></div>
<!--=======footer=================================-->
 <footer>			 
			
                    <!-- FOOTER STARTS HERE -->
                        
						<%@ include file = "../../include/menschForceFooter.jsp" %>
                    <!-- FOOTER ENDS HERE -->
               
 </footer>
</body>
</html>

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
</head>


<body>

<header id="header">
<!-- HEADER STARTS HERE -->
<%@ include file = "../../include/HeaderProcess.jsp" %>
  <!-- HEADER ENDS HERE -->
</header>
<div class="content">
<br /><br />
  <table width=100% border="0" cellpadding="0" cellspacing="0" height="350" class="content_new" align="center">
    
    <tr>
	
      
     
      <td  align="center" valign="middle" class="tableCommonBg" >
	  <table width="630" border="1" align="center" cellpadding="0" cellspacing="0" id="mainTab" height="100" style="border:thin;border-style:groove;">
       
        <tr><td><strong>User Details Retrieval Confirmation </strong><div class="divider3"></div></td></tr>
        <tr>
          <td valign="top" class="tableCommonBg"><!-- CENTER TABLE STARTS HERE -->
              <table width="630" border="0" align="center" cellpadding="0" cellspacing="0" id="centerTab">
                <tr>
                  <td colspan="2" class="cenTablePad"><!-- Welcome Tab Starts Here -->
                      <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="welcomeTab">
                        
                        <tr>
                          <td height="258" rowspan="2" class="welTabLft">&nbsp;</td>
                          <td rowspan="2" align="center" valign="middle" class="loginTabLeft"><span class="styleBoldTwo"></span><br />
                              <br />
                              <br />
                              <!-- Descrip Tab Starts Here -->
                              <table width="437" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="welcomeTab">
                                <tr>
                                  <td width="10" class="loginTabLftTopCrnr"></td>
                                  <td width="390" class="loginDescBg"></td>
                                  <td width="10" class="loginTabRghtTopCrnr"></td>
                                </tr>
                                <tr>
                                  <td class="loginTabLft">&nbsp;</td>
                                  <td class="loginDescBg"><span class="textBold">Dear User</span>,<br />
                                      <br />
                                    Your account details has been sucessfully sent to your EMail! .<br />
                                    <br />
                                    Thank You,<br />
                                    <span class="styleBoldOne">Klugwerks TEAM</span> </td>
                                  <td class="loginTabRght">&nbsp;</td>
                                </tr>
                                <tr>
                                  <td class="loginTabLftBotCrnr"></td>
                                  <td class="loginTabBot"></td>
                                  <td class="loginTabRghtBotCrnr"></td>
                                </tr>
                            </table>
                          <!-- Descrip Tab Ends Here -->                          </td>
                        </tr>
                    </table>
                    <!-- Welcome Tab Ends Here -->                  </td>
                </tr>
            </table>
            <!-- CENTER TABLE ENDS HERE -->          </td>
        </tr>
      </table></td>
    </tr>
  </table></div>
<!--=======footer=================================-->
 <footer>			 
			
                    <!-- FOOTER STARTS HERE -->
                        
						<%@ include file = "../../include/Footer.jsp" %>
                    <!-- FOOTER ENDS HERE -->
               
 </footer>
</body>
</html>

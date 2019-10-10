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
    <%@page import="java.util.ArrayList"  %>
    <%@page import="java.util.Iterator"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<title>menschForce</title>
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
         <form action="ExterApp.html" method="post" name="viewHrsServiceList" id="viewHrsServiceList" >
                     
                    <input type="hidden" name="searchProcessList" value="loginProcess" />
	  <table width="800px" style="border-style:groove;border-width:thin;">
  <tr style="border-bottom-style:groove;border-bottom-width:thin">
    <td width="98"><strong>First name</strong> </td>
    <td width="116"><strong>Last name</strong> </td>
    <td width="137"><strong>File name</strong> </td>
    <td width="103"><strong>File Path</strong></td>
	<td width="120"><strong>Download</strong></td>
  </tr>
  
  
					<%
					ArrayList<String> firstNameList = (ArrayList<String>) request.getAttribute("firstNameList");
					ArrayList<String> lastNameList = (ArrayList<String>) request.getAttribute("lastNameList");
					ArrayList<String> fileNameList = (ArrayList<String>) request.getAttribute("fileNameList");
					ArrayList<String> filePathList = (ArrayList<String>) request.getAttribute("filePathList");
						if(firstNameList!=null && firstNameList.size()!=0){
							for(int i=0;i < firstNameList.size();i++){
															
							%>
							  <tr>
							
    <td><%=firstNameList.get(i)%></td>
    <td><%=lastNameList.get(i)%></td>
    <td><%=fileNameList.get(i)%></td>
    <td><%=filePathList.get(i)%></td>
	<td><label><a href="ExterApp.html?process=initdownloadfile&filename=<%=fileNameList.get(i)%>&pathname=<%=filePathList.get(i)%>"><img src="img/download-icon.png" alt="" width="20" height="20" style="cursor:pointer"/>Click to download</a>
      </label></td></tr>
    
    <%}}else{
 	   %>
       <tr>
         <td colspan="8" align="center"><strong>No DATA </strong></td>
       </tr>
       <%
}
                               %>
</table>
</form>

	  </td>
    </tr>
	<tr><td>&nbsp;</td></tr>
</table></div>
<!--=======footer=================================-->
 <footer>			 
			
                    <!-- FOOTER STARTS HERE -->
                        
						<%@ include file = "../../include/menschForceFooter.jsp" %>
                    <!-- FOOTER ENDS HERE -->
               
 </footer>
</body>
</html>

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

	
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>

<div>
<%@ include file = "../../include/HeaderProcess.jsp" %>

  <!-- HEADER ENDS HERE -->
</div>

<section id="content">
<% String memberCount = (String)request.getAttribute("memberCount"); %>
<div style="margin-left:500px; margin-top:200px; ">
<h1><%=memberCount %> Member data has been Successfully Synchronized with the Quickbooks!</h1>
<div style="text-align:center; color:#b70000; ">
<a href="https://qbo.intuit.com/app/customers" target="_blank">See in QuickBooks</a>
</div>
</div>

	</section> 

	<div>			 
	                <!-- FOOTER STARTS HERE -->
                        
						<%@ include file = "../../include/FooterProcess.jsp" %>
                    <!-- FOOTER ENDS HERE -->
               
</div>

</body>
</html>

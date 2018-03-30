<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<!--========================================================
HEADER
=========================================================-->
  <div id="header">
    <!-- HEADER STARTS HERE -->
 
      <!-- HEADER ENDS HERE -->
  </div>
  
<!--========================================================
CONTENT
=========================================================-->

<script src="js/jquery.validate.js"></script>



<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">
            
			<div id="rectangle" align="center">
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				<div class="badge"align="center"><img src="images/db.jpg"  alt=""></div>
				
				<% String receiveremail=request.getParameter("cmd");
			System.out.println("cmd------>"+receiveremail);%>
			 <input type="hidden" name="receiveremail" id="receiveremail" value="<%=receiveremail%>">

                   <h2 align="center"><span class="styleBoldTwo">Confirmation!</span></strong> </br></h2>
				   
				  
				   
				   
				   <h1 align="center" >You have successfully done!</h1>
				   
				  
				   
				    <h2 align="center"><a  href="https://www.digiblitz.com/partner">Click here to continue</a></h2>
				 
				 </div>
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
    
   
      <!-- FOOTER ENDS HERE -->
      
    </div>	
    </body>
</html>

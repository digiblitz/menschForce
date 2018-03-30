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

<html lang="en">
<head>
<title>Success</title>
	 
</head>
<body>
<!--header-->
<div id="header">
<!-- HEADER STARTS HERE -->
<%@ include file = "../../include/HeaderProcess.jsp" %>
  <!-- HEADER ENDS HERE -->
</div>

<!--content-->
<div class="content"> 
   
     <div class="thumb-box2">
        <div class="container">
            
			<div class="row">
                <div class="col-md-16 col-sm-12 col-xs-12 wow fadeInUp">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                           
                            <div class="caption" >
								<div class="col-lg-6 col-md-6 col-sm-6">
								&nbsp;	
								</div>
					<div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-3" >
						<div class="col-lg-1 col-md-1 col-sm-1" >
							<a href="user.html?cmd=homeDash" style="font-family:sans-serif;font-size:large;font-weight:600;text-decoration:underline;" >Home</a>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" ></div>
						<div class="col-lg-1 col-md-1 col-sm-1" >
							<a href="#" style="font-family:sans-serif;font-size:large;font-weight:600;text-decoration:underline;" onClick="history.go(-1);">Back</a>
						</div>
					</div>
                           </div>  
                        </div>
                    </div>
                </div>
			
            </div>

            <div class="row">
			<div class="col-md-2 col-sm-2 col-xs-2 wow fadeInUp">
			</div>
                <div class="col-md-8 col-sm-8 col-xs-8 wow fadeInUp">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                           
                            <div class="caption" >
                                                            
                              
                            
                              
                              <%
                              String resumeUpdateStatus = null;
                              String reqid = null;
                              resumeUpdateStatus = (String)request.getAttribute("resumeUpdateStatus");
                              if(resumeUpdateStatus != null && resumeUpdateStatus.equalsIgnoreCase("success")){
                              reqid = (String)request.getAttribute("reqid"); 
                              %>
                              <span align="center" style="color:#63b042"><b>congratulation! You have successfully applied for the position</b></span><br>
                              <span align="center"><b>Your Requirement Id <%=reqid%></b></span>
                              <%}else{ %> 
                              <span align="center"><b>Sorry! Resume was not uploaded into database</b></span>
                              <%} %>
                           </div>  
                        </div>
                    </div>
                </div>
           
               
            </div>

			<div class="row">
                <div class="col-md-16 col-sm-12 col-xs-12 wow fadeInUp">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                           
                            <div class="caption" >
                            
                                
                           </div>  
                        </div>
                    </div>
                </div>
           
            </div>

			 <div class="row">
                <div class="col-md-16 col-sm-12 col-xs-12 wow fadeInUp">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                           
                            <div class="caption" >
                            
                              
                                
                           </div>  
                        </div>
                    </div>
                </div>
           
               
            </div>

        </div>

     </div>
     
</div>

<div>
    
    
    <!-- FOOTER STARTS HERE -->
    
   <%@ include file = "../../include/FooterProcess.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>

</body>
</html>

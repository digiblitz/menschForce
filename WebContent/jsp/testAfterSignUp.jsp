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
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<title>Insert title here</title>
</head>

<body>
<!--========================================================
                          HEADER
=========================================================-->

<div id="header">
<!-- HEADER STARTS HERE -->
<%@ include file = "../../include/header_new.jsp" %>
  <!-- HEADER ENDS HERE -->
</div>
<!--========================================================
                          CONTENT
						  
=========================================================-->


<%String afterFname = (String)session.getAttribute("aftFName");
String aftLastname = (String)session.getAttribute("aftLastname");
String aftE_mail = (String)session.getAttribute("aftE_mail");
String aftInstitutionName = (String)session.getAttribute("aftInstitutionName");
String aftInstituteId = (String)session.getAttribute("aftInstituteId");
String aftRegistrationId = (String)session.getAttribute("aftRegistrationId");
String aftCustomerUserName = (String)session.getAttribute("aftCustomerUserName");
String y_shopJSessionId = (String)request.getAttribute("y_shopJSessionId");
String aftCustomermobileno = (String)session.getAttribute("aftCustomermobileno");
%>


<section id="content">
<div class="content">
    
	
	
	
	<div class="thumb-box10">
        <div class="container">
            <div class="row">
                <div class="col-lg-5 col-md-5 col-sm-5">
                    <div class="wow fadeInUp clearfix">
                        <div class="box1 maxheight2">
                            <p class="title">Congrats &nbsp;<%=afterFname %>!</p>
                            <p class="description">You're ready to start using menschFoce Online Edition</p>
							<p class="description">We'll send you a welcome email shortly with some quick tips to get started easily.</p>
                            <p><strong>Login Details</strong></p>
                            <ul class="list2-1">
                            <%if(aftLastname != null){%>
                                <li><strong>Name: </strong><a href="#"><%=afterFname %>&nbsp;<%=aftLastname %></a></li>
                                <%}else{ %>
                                <li><strong>Name: </strong><a href="#"><%=afterFname %>&nbsp;</a></li>
                                <%} %>
                                <li><strong>E-mail: </strong><a href="#"><%=aftE_mail %></a></li>
                                <p><strong>Institution Information</strong></p>
                                <li><strong>User Name: </strong><a href="#"><%=aftCustomerUserName%></a></li>
                                <li><strong>password:</strong><a href="#">Password will be send to your e-mail</a></li>
                                <li><strong>Registration Id: </strong><a href="#"><%=aftRegistrationId%></a></li>
                                <%if(aftInstitutionName != null){%>
                                <li><strong>Institution Name: </strong><a href="#"><%=aftInstitutionName%></a></li>
                                <%}else{%>
                                <li><strong>Institution Name: </strong><a href="#">N/A</a></li>
                                <%} %>
								<li><strong>Institution ID: </strong><a href="#"><%=aftInstituteId%></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                
            </div>
             <div class="row">
                <div class="col-lg-5 col-md-5 col-sm-5">
               <form class="form-horizontal" id="registerForm39" method="post" action="http://74.208.110.192:8480/yes-shop/registration;jsessionid=<%=y_shopJSessionId%>?0-1.IFormSubmitListener-registrationView-registerForm" target='_blank'><div style="width:0px;height:0px;position:absolute;left:-100px;top:-100px;overflow:hidden"><input type="hidden" name="registerForm39_hf_0" id="registerForm39_hf_0" /></div>

<div class="form-group" style="display:none">

<div class="col-xs-12 col-sm-8">
<input type="text" class="form-control" id="registerEmail" value="<%=aftE_mail%>" name="email" placeholder="E-mail"/>
</div>
</div>
<div class="form-group"  style="display:none">

<div class="col-xs-12 col-sm-8">
<input type="text" class="form-control" id="registerFirstName" value="<%=afterFname%>" name="firstname" placeholder="First name"/>
</div>
</div>
<div class="form-group"  style="display:none">

<div class="col-xs-12 col-sm-8">
<input type="text" class="form-control" id="registerLastName" value="<%=aftLastname%>" name="lastname" placeholder="Last name"/>
</div>
</div>
<div class="form-group"  style="display:none">

<div class="col-xs-12 col-sm-8">
<input type="text" class="form-control" id="registerPhone" value="<%=aftCustomermobileno%>" name="phone" placeholder="Phone"/>
</div>
</div>

<div class="form-group"  style="display:none">

</div>
<div class="form-group">
<div class="col-xs-offset-3 col-xs-8 buttons">
<input type="submit" class="btn btn-primary" name="registerBtn" id="registerBtn3a" value="Click to Purchase more Bundles"/>
</div>
</div>
</form>
                </div>
              </div>
        </div>
        <em></em>
    </div>
	
	
    </div>

</section>

<!--========================================================
                          FOOTER
=========================================================-->
<div>			 
			
                    <!-- FOOTER STARTS HERE -->
                        
						<%@ include file = "../../include/Footer.jsp" %>
                    <!-- FOOTER ENDS HERE -->
               
 </div>
</body>
</html>

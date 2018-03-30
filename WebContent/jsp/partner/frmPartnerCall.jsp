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

<script src="js/tm-scripts.js"></script>
 <script  src="js/ts_picker.js"></script>
<script src="js/calendar2.js" type="text/javascript"></script>
<script src="js/datetimepicker_css.js"></script>    

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

<script src="js/jquery.validate.js"></script>
<script>
$('form[name="Partnercall"]').validate({ 
	//alert("working");
	rules:{
		
		eventDate:"required",
		eventmail:"required",
		},
	
	messages:{
		eventDate:"Select the Date for partner Call",
		eventmail:"Email is required",
			
	},
	errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}
	
});
</script>

<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">
			
			
			<% String receiveremail=request.getParameter("cmd");
			System.out.println("cmd------>"+receiveremail);%>
			
			
			
			
			
				<div class="col-lg-12 col-md-12 col-sm-12">

                   <h4 class="title"> Refer the calender below for "digiBlitz Partnering Signup-Introductory call. Choose the Date/Time by referring the calender and Click Submit</h4>
				   
				   <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
					</div>
					
					 <form name="Partnercall" id="Partnercall" method="post" action="PartnerIntroductoryCall.html" class="formcss">
					 <input type="hidden" name="receiveremail" id="receiveremail" value="<%=receiveremail%>">
					 
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Event Date and Time <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="eventDate" id="eventDate" class="form-control" placeholder="Event Date" />
								
								
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 ">
							
							<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:top;cursor:pointer" border="0" onclick="javascript:NewCssCal('eventDate','MMddyyyy','dropdown',true,'24',true)" />
						
		
		</div>
							
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
					</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Email <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="eventmail" id="eventmail" class="form-control" placeholder="Email" />
								
								
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
					</div>
					
					
					<div class="col-lg-12 col-md-12 col-sm-12">
						
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-3">
							<a href="https://calendar.google.com/calendar/embed?src=digiblitz.com_55ge5g4j3bh1rfjm1njfk5dafg%40group.calendar.google.com&ctz=America/New_York">Click here to view calendar</a>
							 
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
					</div>
					
						<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-5">
							 <button type="submit" class="button-add" name="submit" value="submit"  >Submit</button>
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
    
   <%@ include file = "../../include/FooterProcess.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>	
    </body>
</html>

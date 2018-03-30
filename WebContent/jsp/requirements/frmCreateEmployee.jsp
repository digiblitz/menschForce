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

<script src="js/cscombo_new.js" type="text/javascript" ></script>
<body>
<style>

#BecomePartner label.error{
	color:red;
}
</style>


 <!--========================================================
HEADER
=========================================================-->
  <div id="header">
    <!-- HEADER STARTS HERE -->
   <%@ include file = "../../include/menschForceHeader.jsp" %>
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
			
				<div class="col-lg-12 col-md-12 col-sm-12">

                   <h3 class="title">Indian Employee</h3>
				 
				 </div>
				 
				 
				 <form name="AddEmployee" id="AddEmployee" method="post" action="" class="formcss">
				 
				<div class="row">
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									 <h4 class="title">Basic Details</h4>
								</label>
							</div>
							</div>
							</div>
							
						<div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
						</div>						
				 <div class="row">
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Employee No <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="EmpNo" id="EmpNo" class="form-control" placeholder="Employee Number" />
								
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							&nbsp;
							</div>
							
							
						
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						 
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									First Name <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="first_name" id="first_name" class="form-control" placeholder="First Name" />
								
							</div>
							
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
											 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Last Name
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="last_name" id="last_name" class="form-control" placeholder="Last Name" />
								
							</div>
							
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Designation <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="designation" id="designation" class="form-control" placeholder="Designation" />
								
							</div>
							
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
							
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Date of Joining <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="Doj" id="Doj" class="form-control" placeholder="Date of Joining" />
								
							</div>
							
							
						</div>
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									EmailId <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="email" id="email" class="form-control" placeholder="Email" />
								
							</div>
							
							
						</div>
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Phone Number <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="phone" id="phone" class="form-control" placeholder="Phone number" />
								
							</div>
							
							
						</div>
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Address <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="Address" id="Address" class="form-control" placeholder="Address" />
								
							</div>
							
							
						</div>
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									City<span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="City" id="City" class="form-control" placeholder="City" />
								
							</div>
							
							
						</div>
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									State<span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="State" id="State" class="form-control" placeholder="State" />
								
							</div>
							
							
						</div>
						
						
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Postal Code<span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="postal_code" id="postal_code" class="form-control" placeholder="Zipcode" />
								
							</div>
							
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Country <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							 <select name="Country" id="Country" class="form-control"  >
								<option selected="selected">Select One</option>
								<option value="United States of America">United States of America</option>
							  </select>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Passport/PAN/Voter/License ID <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="personal_id" id="personal_id" class="form-control" placeholder="Id number" />
								
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						
						
					
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									PF Number
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="PF_number" id="PF_number" class="form-control" placeholder="PF Number" />
								
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									ESI Number
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="ESI_number" id="ESI_number" class="form-control" placeholder="ESI Number" />
								
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Salary <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="salary" id="salary" class="form-control" placeholder="salary" />
								
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="row">
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									 <h4 class="title">Bank Details</h4>
								</label>
							</div>
							</div>
							</div>
							
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Name on PassBook <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="name_on_passbook" id="name_on_passbook" class="form-control" placeholder="Name On PassBook" />
								
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Bank Name <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="bank_name" id="bank_name" class="form-control" placeholder="Bank Name" />
								
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Branch <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="branch" id="branch" class="form-control" placeholder="branch" />
								
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									IFSC Code <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="ifsc" id="ifsc" class="form-control" placeholder="ifsc" />
								
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-5">
							 <button type="submit" class="button-add" name="Submit" value="Submit" onClick='this.form.action="CreateIndianPayroll.html";'>Submit</button>
							
							</div>
							</div>
						
						
			</form>			
						
						
				 
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

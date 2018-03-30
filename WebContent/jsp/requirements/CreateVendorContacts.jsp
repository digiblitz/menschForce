<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Create Vendor Contacts</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="css/contact-form.css">
<link rel="stylesheet" href="css/style.css">

<style>
#contact-form label.error{
	color:red;
}
</style>
</head>


<body>

 <!--========================================================
HEADER
=========================================================-->
  <div id="header">
    <!-- HEADER STARTS HERE -->
  <%@ include file = "../../include/menschForceHeader.jsp" %>
      <!-- HEADER ENDS HERE -->
  </div>
  
 <script src="js/jquery.validate.js"></script>
<script src="js/additional-methods.js"></script>
<script src="js/fieldvalidation.js"></script>

	
<!--========================================================
CONTENT
=========================================================-->
 
<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">
			
				<div class="col-lg-12 col-md-12 col-sm-12">
					
                   <h3 class="title">Create Vendor Contact</h3>
				   
				 </div>
				 
				 
		 
		<form name="frmcreateVendor" id="contact-form" method="post" action="CreateVendrContacts.html" class="formcss" onsubmit="return validate();" >
		
		
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>

						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">Vendor Id</label>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							  <input type="text" name="vendor_id" id="vendor_id" class="form-control" placeholder="Vendor Id" />
							
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>

						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">First Name <span class="form-validation" style="color:Red;">*</span></label>
							
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							  <input type="text" name="vendor_fname" id="vendor_fname" class="form-control" placeholder="Vendor First Name" />
							
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>

						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">Last Name <span class="form-validation" style="color:Red;">*</span></label>
							
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							  <input type="text" name="vendor_lname" id="vendor_lname" class="form-control" placeholder="Vendor Last Name" />
							
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6" >E-Mail <span class="form-validation" style="color:Red;">*</span></label>
							
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							  <input type="text" name="vendor_email" id="vendor_email" class="form-control" placeholder="Vendor E-Mail" />
							
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">Company Name <span class="form-validation" style="color:Red;">*</span></label>
							
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							 <input type="text" name="vendor_company" id="vendor_company" class="form-control" placeholder="Vendor Company Name" />
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">Cell Phone</label>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							<input type="text" name="vendor_homePhone" class="form-control" placeholder="Vendor Cell Phone"onkeypress="return isNumber(event)"/>
							 
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">Business Phone</label>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							 <input type="text" name="vendor_businessPhone" class="form-control" placeholder="Vendor Business Phone" onkeypress="return isNumber(event)"/>
							
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">Contact Person</label>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							 <input type="text" name="vendor_contactPerson" class="form-control" placeholder="Vendor Contact Person"/>
							
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
										
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-5 col-md-5 col-sm-5 col-sm-offset-5">
							 <button type="submit" class="button-add" name="Submit" value="Submit">Submit</button>
							 <button type="reset" class="button-dang" name="cancel" value="cancel">Cancel</button>
							 <button type="reset" class="button-dang" name="Back" value="Back" onClick="history.go(-1);">Back</button>
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


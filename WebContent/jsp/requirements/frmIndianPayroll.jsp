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

                   <h3 class="title">PAYROLL</h3>
				 
				 </div>
				 
				 
				 <form name="Indianpayroll" id="Indianpayroll" method="post" action="" class="formcss">
				 
				<div class="row">
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2">
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
						
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									Employee No 
								</label>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							
							  <input type="text" name="EmpNo" id="EmpNo" class="form-control" placeholder="Employee Number" />
								
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							&nbsp;
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									Total Working Days
								</label>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							
							  <input type="text" name="total_working_days" id="total_working_days" class="form-control" placeholder="Working Days" />
								
							</div>
							
						</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						 <div class="row">
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									Name
								</label>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							
							  <input type="text" name="Name" id="Name" class="form-control" placeholder="Employee Name" />
								
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							&nbsp;
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									Total Paid Days<span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							
							  <input type="text" name="total_paid_days" id="total_paid_days" class="form-control" placeholder="Paid Days" />
								
							</div>
							
						</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
							 <div class="row">
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									Designation
								</label>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							
							  <input type="text" name="designation" id="designation" class="form-control" placeholder="Designation" />
								
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							&nbsp;
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									Loss of Pay<span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							
							  <input type="text" name="Lop" id="Lop" class="form-control" placeholder="LOP" />
								
							</div>
							
						</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
							 <div class="row">
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									Date of Joining
								</label>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							
							  <input type="text" name="Doj" id="Doj" class="form-control" placeholder="Date of Joining" />
								
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							&nbsp;
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									Payment Mode
								</label>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							
							  <input type="text" name="payment_mode" id="payment_mode" class="form-control" placeholder="Payment Mode" />
								
							</div>
							
						</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="row">
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									PF Number
								</label>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							
							  <input type="text" name="PF" id="PF" class="form-control" placeholder="PF Number" />
								
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							&nbsp;
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									ESI Number
								</label>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							
							  <input type="text" name="ESI" id="ESI" class="form-control" placeholder="ESI Number" />
								
							</div>
							
						</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						
						
						<div class="row">
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									<h4 class="title"> Salary</h4>
								</label>
							</div>
							
							
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
								 <h4 class="title">Actual</h4>
									
								</label>
							</div>
							
							
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									<h4 class="title"> Earned</h4>
								</label>
							</div>
							
							
							<div class="col-lg-4 col-md-4 col-sm-4">
								 <label class="name form-div-6">
									<h4 class="title">Deduction</h4>
								</label>
							</div>
							
							
						</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
							<div class="row">
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									Basic
								</label>
							</div>
							
							
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <input type="text" name="actual_basic" id="actual_basic" class="form-control" placeholder="Actual Basic" />
							</div>
							
							
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <input type="text" name="earned_basic" id="earned_basic" class="form-control" placeholder="Earned Basic" />
							</div>
							
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									PF
								</label>
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <input type="text" name="pf_deduction" id="pf_deduction" class="form-control" placeholder="PF Deduction" />
							</div>
							
							
						</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="row">
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									HRA
								</label>
							</div>
							
							
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <input type="text" name="actual_HRA" id="actual_HRA" class="form-control" placeholder="Actual HRA" />
							</div>
							
							
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <input type="text" name="earned_HRA" id="earned_HRA" class="form-control" placeholder="Earned HRA" />
							</div>
							
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									ESI
								</label>
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <input type="text" name="esi_deduction" id="esi_deduction" class="form-control" placeholder="ESI Deduction" />
							</div>
							
							
						</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
							<div class="row">
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									Travelling Allowance
								</label>
							</div>
							
							
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <input type="text" name="actual_travel_allowance" id="actual_travel_allowance" class="form-control" placeholder="Travel Allowance" />
							</div>
							
							
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <input type="text" name="earned_travel_allowance" id="earned_travel_allowance" class="form-control" placeholder="Travel Allowance" />
							</div>
							
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									TDS
								</label>
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <input type="text" name="tds" id="tds" class="form-control" placeholder="TDS" />
							</div>
							
							
						</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="row">
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									 Performance Incentive
								</label>
							</div>
							
							
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <input type="text" name="actual_incentive" id="actual_incentive" class="form-control" placeholder="Incentive" />
							</div>
							
							
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <input type="text" name="earned_incentive" id="earned_incentive" class="form-control" placeholder="Incentive" />
							</div>
							
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									Loan/Advance
								</label>
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <input type="text" name="loan" id="loan" class="form-control" placeholder="Loan/Advance" />
							</div>
							
							
						</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
							<div class="row">
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									Medical Allowance
								</label>
							</div>
							
							
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <input type="text" name="actual_medical" id="actual_medical" class="form-control" placeholder="Medical" />
							</div>
							
							
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <input type="text" name="earned_medical" id="earned_medical" class="form-control" placeholder="Medical" />
							</div>
							
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									Professional Tax
								</label>
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <input type="text" name="tax" id="tax" class="form-control" placeholder="Tax" />
							</div>
							
							
						</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
							<div class="row">
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
									<h4 class="title"> Total CTC</h4>
								</label>
							</div>
							
							
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <input type="text" name="actual_salary" id="actual_salary" class="form-control" placeholder="Salary" />
							</div>
							
							
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <input type="text" name="earned_salary" id="earned_salary" class="form-control" placeholder="Salary" />
							</div>
							
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name form-div-6">
								 <h4 class="title"> Total Deduction</h4>
									
								</label>
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <input type="text" name="total_deduction" id="total_deduction" class="form-control" placeholder="Total Deduction" />
							</div>
							
							
						</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="row">
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-6 col-md-6 col-sm-6">
								 &nbsp;
								 </div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <label class="name">
									<h4 class="title"> Net Pay</h4>
								</label>
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								 <input type="text" name="netpay" id="netpay" class="form-control" placeholder="NET PAY" />
							</div>
							
							
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

<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.regex.*" %>
<%@ page import="com.hlchorse.form.util.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Product Package</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<script src="js/basic.js" type="text/javascript" ></script>
<script src="js/validate.js" type="text/javascript" ></script>
<script src="js/cscombo_new.js" type="text/javascript" ></script>
<script src="js/frmMembRegi.js" type="text/javascript" ></script>
<script src="js/EditMemberUserReg.js" type="text/javascript" ></script>
</head>
<%! 

String  nullCheck(String fieldName){
	String retValue = "";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
}

%>
<style>
	.asterisk{
		color:red;
	}
    .vertical-line {
        width: 0;
        border: 1px solid #000000;
        height: 1px
		 
    }
</style>

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
<script src="js/profilefieldvalidation.js"></script>
<!--=======content================================-->

<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">

				<div class="col-lg-12 col-md-12 col-sm-12">
					
                   <h3 class="title">Product Package Details </h3>
				   
				 </div>
			</div> 
		 <form   action="master.html?profileCmd=updateproductppackage&email=<%=nullCheck((String)request.getAttribute("e_mail"))%>" method=post name="uploadfile">
			
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-3">
						<h4>Plan and Bundle History</h4>
					</div>
					
					<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
				 </div>
				 
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>	Plan Type<span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							 <input  type="text" name="plan_type" class="form-control" value="<%=nullCheck((String)request.getAttribute("Productplan"))%>" readonly> 

							
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>	Subscription Type<span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
								<input  type="text" name="subs_type"  class="form-control" value="<%=nullCheck((String)request.getAttribute("subscription_plan"))%>" readonly>
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>	Payment History<span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
								<input  type="text" name="pay_history"  class="form-control" value="<%=nullCheck((String)request.getAttribute("amount"))%>" >

							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>	Alter Plan and Bundle <span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
								<input  type="button" name="alt_plan" class="form-control"  value="Purchase" onClick="location.href='https://www.digiblitzonline2.com:8443/yes-shop/category/menschforce'">


							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>	Subscription Expiry(Plan and Bundles) <span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
								<input  type="text" name="Subs_expiry" id="Subs_expiry" class="form-control"  value="<%=nullCheck((String)request.getAttribute("difference"))%>" readonly >
								
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>	Renew Subscription(Plan and Bundles) <span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
								<button  type="button" name="renew_subs" id="renew_subs" class="form-control" value="Renew"  onClick="location.href='signUp.html?signUpProcess=getStarted'">Renew</button>
								
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
				  
					</div>
						 
						
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-5 col-md-5 col-sm-5">
							</div>
							<div>
							   <button type="submit" class="button-add" name="Insert" value="Update">Update</button>

							   <button type="button" class="button-dang" name="button" value="Cancel" onclick="javascript:history.back(-1);">Cancel</button>
							 
							</div>
						</div>
					
					</div>
					</form>
				
				</div>
		</div>
	</div>

               
<!--=======footer=================================-->
			
                    <!-- FOOTER STARTS HERE -->
                        
						<%@ include file = "../../include/menschForceFooter.jsp" %>
                    <!-- FOOTER ENDS HERE -->
               

</body>

</html>

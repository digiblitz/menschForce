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
<title>Customer Details</title>
<style type="text/css">

.tdspace{
width:200px;
}
</style>
<style>
    .vertical-line {
        width: 0;
        border: 1px solid #000000;
        height: 1px
		 
    }
</style>
<style type="text/css">
	   
	.divtext{
    height: 11px;
    width: 610px;
    padding: 15px 0 15px 0;
    margin: 0 auto;
	
	}
	.divPasstext{
    height: 11px;
    width: 610px;
    padding: 15px 0 15px 0;
    margin: 0 auto;
	color: rgba(21, 152, 25, 0.98);
    font-weight: bold;
    font-family: sans-serif;
	}
	
	.labeltext{
	   float:left;
	   font-size:18px;
	}
	.submitlabeltext{
	   float:none;
	   font-size:18px;
	}
	.spantext{
	   float:right
	}
	.container_12{
		width:1170px!important;
		margin:0 auto;
		position:relative;
		
	}
	.container_12 .grid_12 {
    	width: 1170px!important;
		    padding: 10px 0;
	}
	.container_12 .grid_6 {
		width: 565px!important;
		float: left;
	}
	.bg1 {
    position: relative;
    background: url(../images/bg1.jpg) 0 0 repeat;
    padding: 30px 0!important;
}
	button.btn {
    background: green;
    outline: mediumaquamarine;
    border: none;
    padding: 8px 10px;
}
	button.btn1 {
    background: red;
    outline: mediumaquamarine;
    border: none;
    padding: 8px 10px;
}
	.box{    width: 350px!important;
    padding: 7px!important;
    border: none;
    border-radius: 4px;
    box-shadow: 0 1px 7px #ccc;
	margin:0!important;
	text-align:left!important;
	pointer-events:none;
	background-color:#009FBC!important;
	color:#FFFFFF
	
	}
	
	.boxx{    width: 363px!important;
    padding: 7px -1px!important;
    border: none;
    border-radius: 4px;
    box-shadow: 0 1px 7px #ccc;
	margin:0!important;
	color:#FFFFFF;
	text-align:center!important;
	background-color:lightslategray}
	#anchorStyle{
	margin-right: -996px;
    color: #0950EF!important;
    font-family: sans-serif;
    font-weight: bold;
    font-style: italic;
	text-decoration: underline;
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
	
  <!--========================================================
CONTENT
=========================================================-->
 
<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">


<%String customerPassStatus = (String)request.getAttribute("customerPassStatus");
if((customerPassStatus!=null) && (customerPassStatus.equalsIgnoreCase("success"))){%>
			
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-5 col-md-5 col-sm-5">
							</div>
							<div class="col-lg-7 col-md-7 col-sm-7">
							<label class="name form-div-6">
							Password reseted successfully
                            </label>
							</div>
						</div>

			
			<%} else if((customerPassStatus!=null) && (customerPassStatus.equalsIgnoreCase("fail"))){
			%>
			
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-5 col-md-5 col-sm-5">
							</div>
							<div class="col-lg-7 col-md-7 col-sm-7">
							<label class="name form-div-6 asterisk">
							Password can not reseted successfully
                            </label>
							</div>
						</div>

			
			<%}%>
<%ArrayList customerDetails = (ArrayList) request.getAttribute("customerDetails");

          					 if(customerDetails!=null && customerDetails.size()!=0){
							Iterator it = customerDetails.iterator();
							while(it.hasNext()){
								String[] s = (String[])it.next();
								
								 String first_name = s[0];
			    	             String last_name = s[1];
			    	             String e_mail = s[2];
			    	             String mobile = s[3];
			    	             String institution_name = s[4];
			    	             String country = s[5];
			    	             String state = s[6];
			    	             String city = s[7];
			    	             String credit_card_type = s[8];
			    	             String credit_card_no = s[9];
			    	             String cvv_no = s[10];
			    	             String name_on_card = s[11];
			    	             String expiry_date = s[12];
			    	             String customer_id = s[13];
			    	             String date = s[14];
			    	             String product_price = s[15];
			    	             String subscription_type = s[16];
			    	             String subscription_id = s[17];
			    	             String order_id = s[18];
			    	             String purchase_type = s[19];
			    	             String product_plan = s[20];
			    	             String user_name = s[21];
			    	             String user_password = s[22];
			    	             String registration_id = s[23];
								 System.out.println("email id inside the jsp:::::::::::::::::::::::::::"+e_mail);
			    	             %>							 
			
							
				<div class="col-lg-12 col-md-12 col-sm-12">
					
                   <h3 class="title">Customer Details</h3>
				  
				 </div>
				 
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							First name
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=first_name %>" class="form-control" >
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
							<input  type="text" name="username" value="<%=last_name %>" class="form-control" >
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							E-mail 
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=e_mail %>" class="form-control" >
							</div> 
						
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							Mobile
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=mobile %>" class="form-control" >
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							Institution Name
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=institution_name %>" class="form-control" >
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							Country
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=country %>" class="form-control" >
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							State
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=state %>" class="form-control" >
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							City
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=city %>" class="form-control" >
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							Customer ID
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=customer_id %>" class="form-control" >
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							User Name
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=user_name %>" class="form-control" >
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							User Password
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=user_password %>" class="form-control" >
							<a href="./customerAction.html?custProcess=changeCustomerPass&customerId=<%=customer_id%>" id="anchorStyle">change password</a>
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							Registration ID
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=registration_id %>" class="form-control" >
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
				<div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-7 col-md-7 col-sm-7 col-sm-offset-3 subtitle ">
						<label class="name">Customer Card and Purchase Details</label>
				   </div>
				 </div>
				  <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							Expiry Date
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=expiry_date %>" class="form-control" >
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							Date of purchase
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=date %>" class="form-control" >
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							Product Price 
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=product_price %>" class="form-control" >
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							Subscription Type
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=subscription_type %>" class="form-control" >
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							Subscription ID 
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=subscription_id %>" class="form-control" >
							</div> 
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							Order ID
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=order_id %>" class="form-control" >
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							Purchase Type
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=purchase_type %>" class="form-control" >
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							Product plan
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=product_plan %>" class="form-control" >
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							Credit Card Type
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=credit_card_type %>" class="form-control" >
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							Credit Card No.
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=credit_card_no %>" class="form-control" >
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							CVV No
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=cvv_no %>" class="form-control" >
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							Name on card
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input  type="text" name="username" value="<%=name_on_card %>" class="form-control" >
							</div> 
							
						</div>
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-5">
							<button type="reset" class="btn-default btn3" name="Back" value="Back" onClick="history.go(-1);">Back</button>
							</div>
						</div>
				 
 <%}} else{ %>
 
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-5 col-md-5 col-sm-5">
							</div>
							<div class="col-lg-7 col-md-7 col-sm-7">
							No Record Found!
							</div>
							
						</div>
						
						
					
					 <%
          					 }
								 %>
								 
					
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

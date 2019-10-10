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
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<title>Product Pricing</title>

<link rel="stylesheet" href="css/style.css">
<!--CSS-->
<link rel="stylesheet" href="css/bootstrap.css" >
<link rel="stylesheet" href="css/style.css">

<link rel="stylesheet" href="css/stylenew.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/camera.css">
<link rel="stylesheet" href="css/jquery.fancybox.css">
<link rel="stylesheet" href="fonts/font-awesome.css">
<!--JS-->

		
		<script src="js/jquery.js"></script>
		<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="https://digiblitz.com/crm/cache/include/javascript/sugar_grp1.js?v=hGYEVWTZnd4blsxgnJb-TQ"></script>
		<script type="text/javascript" src="https://digiblitz.com/crm/cache/include/javascript/calendar.js?v=hGYEVWTZnd4blsxgnJb-TQ"></script>
		
		
		<script>
		
		
$(function(){
  $("#first_name").change(function(){
   $("#inf_field_FirstName").val($(this).val());
   
  });
})
$(function(){
  $("#last_name").change(function(){
   $("#inf_field_LastName").val($(this).val());
  
  });
})
$(function(){
  $("#company_c").change(function(){
   $("#inf_field_Company").val($(this).val());
  
  });
})
$(function(){
  $("#business_type_c").change(function(){
   $("#inf_custom_JobType").val($(this).val());
   
  });
})
$(function(){
  $("#phone_work").change(function(){
   $("#inf_field_Phone1").val($(this).val());
   
  });
})
$(function(){
  $("#email1").change(function(){
   $("#inf_field_Email").val($(this).val());
   
  });
})
$(function(){
  $("#title").change(function(){
   $("#inf_custom_Title0").val($(this).val());
   
  });
})
$(function(){
  $("#website").change(function(){
   $("#inf_field_Website").val($(this).val());
   
  });
})
$(function(){
  $("#primary_address_street").change(function(){
   $("#inf_field_Address3Street1").val($(this).val());
  
  });
})
$(function(){
  $("#primary_address_city").change(function(){
   $("#inf_field_City3").val($(this).val());
   
  });
})
$(function(){
  $("#primary_address_state").change(function(){
   $("#inf_field_State3").val($(this).val());
   alert(this.value);
  });
})

$(function(){
  $("#primary_address_postalcode").change(function(){
   $("#inf_field_PostalCode3").val($(this).val());
  
  });
})

$(function(){
  $("#primary_address_country").change(function(){
   $("#inf_field_Country3").val($(this).val());
   
  });
})
$(function(){
  $("#lead_source").change(function(){
   $("#inf_field_SourceType").val($(this).val());
   
  });
})

		



function noBack(){
	//alert('working');
}
		var first_name=null;
		var last_name =null;
		var company_c =null;
		var email1=null;
		var title=null;
		var business_type_c=null;
		var website=null;
		var phone_work=null;
		var primary_address_street=null;
		var primary_address_city=null;
		var primary_address_state=null;
		var primary_address_postalcode =null;
		var primary_address_country =null;
		var lead_source =null;
	
	   
	   
		
		$("#buyer_lead").click(function(){
			
			
			var fname=document.getElementById("first_name").value;
			var lname=document.getElementById("last_name").value;
			var email=document.getElementById("email1").value;
			//alert(email);
					
				
		});
		
		
		
		
		</script>
		
		
			<script>
			function tab(ele)
			{
				$(".pricing").removeClass("active");
				$("#"+ele).addClass("active")
			}
			
			function sendEmail(email,first_name,last_name,company_c,title,business_type_c,website,phone_work,
			primary_address_street,primary_address_city,primary_address_state,primary_address_postalcode,primary_address_country,lead_source){
				//alert(first_name);
				

				$.get('sendEmail.html?', {
					email : email,
					first_name : first_name,
					last_name : last_name,
					company_c : company_c,
					title : title,
					business_type_c : business_type_c,
					website : website,
					phone_work : phone_work,
					primary_address_street : primary_address_street,
					primary_address_city : primary_address_city,
					primary_address_state : primary_address_state,
					primary_address_postalcode : primary_address_postalcode,
					primary_address_country : primary_address_country,
					lead_source : lead_source
					
					
					
					
					
			}, function(response) {
				if(response != null){
				 
				}       
				});				
			}
			
			
 function submit_form(){	 
 	if(typeof(validateCaptchaAndSubmit)!='undefined'){
		validateCaptchaAndSubmit();
 	}else{		
		var email=$("#email1").val();
		var first_name=$("#first_name").val();
		var last_name=$("#last_name").val();
		var company_c=$("#company_c").val();
		var title=$("#title").val();
		var business_type_c=$("#business_type_c").val();
		var website=$("#website").val();
		var phone_work=$("#phone_work").val();
		var primary_address_street=$("#primary_address_street").val();
		var primary_address_city=$("#primary_address_city").val();
		var primary_address_state=$("#primary_address_state").val();
		var primary_address_postalcode=$("#primary_address_postalcode").val();
		var primary_address_country=$("#primary_address_country").val();
		var lead_source=$("#lead_source").val();
		sendEmail(email,first_name,last_name,company_c,title,business_type_c,website,phone_work,
			primary_address_street,primary_address_city,primary_address_state,primary_address_postalcode,primary_address_country,lead_source);
 		check_webtolead_fields();
		
		
		
		
 	}
 }
 function check_webtolead_fields(){
	 //alert('working good');
     if(document.getElementById('bool_id') != null){
        var reqs=document.getElementById('bool_id').value;
        bools = reqs.substring(0,reqs.lastIndexOf(';'));
        var bool_fields = new Array();
        var bool_fields = bools.split(';');
        nbr_fields = bool_fields.length;
        for(var i=0;i<nbr_fields;i++){
          if(document.getElementById(bool_fields[i]).value == 'on'){
             document.getElementById(bool_fields[i]).value = 1;
          }
          else{
             document.getElementById(bool_fields[i]).value = 0;
          }
        }
      }
    if(document.getElementById('req_id') != null){
        var reqs=document.getElementById('req_id').value;
        reqs = reqs.substring(0,reqs.lastIndexOf(';'));
        var req_fields = new Array();
        var req_fields = reqs.split(';');
        nbr_fields = req_fields.length;
        var req = true;
        for(var i=0;i<nbr_fields;i++){
          if(document.getElementById(req_fields[i]).value.length <=0 || document.getElementById(req_fields[i]).value==0){
           req = false;
           break;
          }
        }
        if(req){
           
			 document.WebToLeadForm.submit();
			
			
            return true;
        }
        else{
          alert('Please provide all the required fields');
          return false;
         }
        return false
   }
   else{
    document.WebToLeadForm.submit();
	
   }
}
function validateEmailAdd(){
	if(document.getElementById('email1') && document.getElementById('email1').value.length >0) {
		if(document.getElementById('email1').value.match(/^\w+(['\.\-\+]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,})+$/) == null){
		  alert('Not a valid email address');
		}
	}
	if(document.getElementById('email2') && document.getElementById('email2').value.length >0) {
		if(document.getElementById('email2').value.match(/^\w+(['\.\-\+]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,})+$/) == null){
		  alert('Not a valid email address');
		}
	}
}
// ]]></script>
	
<style>
	h1:before,
	h1:after {
	  position: relative;
	  display: inline-block;
	  width: 50%;
	  height: 1px;
	  vertical-align: middle;
	  background: none;
}
</style>
 
</head>
<%String mF_dBStore_url = null;
mF_dBStore_url = (String)request.getAttribute("mF_dBStore_url"); %>
<body>
<!--header-->
<div id="header">
<!-- HEADER STARTS HERE -->
<%@ include file = "../../include/header_new.jsp" %>
  <!-- HEADER ENDS HERE -->
</div>

<div class="content">

<section class="container">
	
	
	<div style="padding:15px 0;">
	</div>

		<div style="border: 1px solid #D6D6D6; margin-bottom:30px;">
		<ul class="nav nav-tabs" style="background:#f3f3f3;">
		  <li class="active"><a style="font-weight:600;" data-toggle="tab"  href="#vendor" >Vendor Product</a></li>
		  <li><a style="font-weight: 600;" data-toggle="tab" href="#msp"> Buyer </a></li>
		</ul>
		
		<div class="tab-content">
		
		<div class="pricing_table_wdg tab-pane fade " id="msp">
		<input type="hidden" value="buyer" name="customerCategory"/>
		<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">
		

<% String first_name = request.getParameter("first_name");
System.out.println("fdfdfdfdfdfdfdfdf"+first_name);
String last_name = request.getParameter("last_name");
String company_c = request.getParameter("company_c");
String title = request.getParameter("title");
String business_type_c = request.getParameter("business_type_c");
String website = request.getParameter("website");
String phone_work = request.getParameter("phone_work");
String email1 = request.getParameter("email1");
String primary_address_street = request.getParameter("primary_address_street");
String primary_address_city = request.getParameter("primary_address_city");
String primary_address_state = request.getParameter("primary_address_state");
String primary_address_postalcode = request.getParameter("primary_address_postalcode");
String primary_address_country = request.getParameter("primary_address_country");
String lead_source = request.getParameter("lead_source");
 %>
 
		<form id="WebToLeadForm" action="https://digiblitz.com/crm/index.php?entryPoint=WebToLeadCapture" method="POST" name="WebToLeadForm">
				  <div class="row">	
				<div class="col-lg-12 col-md-12 col-sm-12">
                   <h3 class="title">Product  Inquiry</h3>
                   </div> 
                </div>

				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				
				</div>
											

	
				 <div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							
								<label>First Name:</label>  <span class="required" style="color: #ff0000;">*</span>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
										<input id="first_name" type="text" name="first_name" class="form-control" onKeyUp="edValueKeyPress()" onKeyPress="edValueKeyPress()"   />

			
							
							</div>
							
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				 <div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-6">Last Name:<span class="required" style="color: #ff0000;">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							<input id="last_name" type="text" name="last_name" class="form-control" />

							
							</div>
							
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				 <div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Company:<span class="required" style="color: #ff0000;">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							<input id="company_c" type="text" name="company_c" class="form-control" />
							
							</div>
							
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				 <div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Title:</label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							<input id="title" type="text" name="title" class="form-control" />

							
							</div>
							
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				 <div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Type of Business:<span class="required" style="color: #ff0000;">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							<input id="business_type_c" type="text" name="business_type_c" class="form-control" />

							
							</div>
							
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				 <div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Website:</label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							<input id="website" type="text" name="website" class="form-control" />

							
							</div>
							
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Office Phone:<span class="required" style="color: #ff0000;">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							<input id="phone_work" type="text" name="phone_work" class="form-control" />

							
							</div>
							
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Email Address:<span class="required" style="color: #ff0000;">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							<input id="email1" type="text" name="email1" onchange="validateEmailAdd();" class="form-control" />

							
							</div>
							
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Primary Address Street:</label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							<input id="primary_address_street" type="text" name="primary_address_street" class="form-control" />

							
							</div>
							
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Primary Address City:</label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							<input id="primary_address_city" type="text" name="primary_address_city" class="form-control" />

							
							</div>
							
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Primary Address State:</label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							<input id="primary_address_state" type="text" name="primary_address_state" class="form-control" />
							
							</div>
							
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-6" >Primary Address Postalcode:</label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
						    <input id="primary_address_postalcode" type="text" name="primary_address_postalcode" class="form-control" />
							
							</div>
							
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Primary Address Country:</label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
						   <input id="primary_address_country" type="text" name="primary_address_country" class="form-control"/>
							
							</div>
							
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Lead Source:</label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
						  <select id="lead_source" name="lead_source" tabindex="1" class="form-control">
								<option value="">-none-</option>
								<option value="Cold Call">Cold Call</option>
								<option value="Existing Customer">Existing Customer</option>
								<option value="Self Generated">Self Generated</option>
								<option value="Employee">Employee</option>
								<option value="Partner">Partner</option>
								<option value="Public Relations">Public Relations</option>
								<option value="Direct Mail">Direct Mail</option>
								<option value="Conference">Conference</option>
								<option value="Trade Show">Trade Show</option>
								<option value="Web Site">Web Site</option>
								<option value="Word of mouth">Word of mouth</option>
								<option value="Email">Email</option>
								<option selected="selected" value="Campaign">Campaign</option>
								<option value="Other">Other</option>
						</select>
							
							</div>
							
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
						
				<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-6 col-md-6 col-sm-6">
						&nbsp;
						</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
						   <input type="button" class="button-edit" onclick="submit_form();"  id="buyer_lead" name="buyer_lead" value="Submit" />
							
							</div>
							
				</div>		
					<input id="campaign_id" type="hidden" name="campaign_id" value="836eab82-576c-86a9-31be-5988cb3629a5" />
					<input id="assigned_user_id" type="hidden" name="assigned_user_id" value="1" />	
					<input id="req_id" type="hidden" name="req_id" value="first_name;last_name;company_c;business_type_c;phone_work;email1;" />
					<input id="account_name_hidden" type="hidden" value="menschforce" name="account_name" />
					<input id="description_hidden" type="hidden" value="Buyer Lead" name="description" />


</form>
			
			</div>
		</div>
	</div>
</div>
		
		
		
		
	</div>

	
			
	<div class="pricing_table_wdg tab-pane fade in active" id="vendor">
	<input type="hidden" value="vendor" name="customerCategory"/>
	<div class="container">
			<ul class="pricing" id="six" onMouseOver="tab('six')">
				<li style="background:#01bf9d;">Basic</li>
				<li>$99/<span style="font-size:12px;">month</span></li>
				<li style="background:#000; padding:5px 7px; color:#fff;">One Time Setup Cost<br><span style="font-size:13px;">$999</span></li>
				<!--<li>Perfect for those getting started with our app.</li>-->
				<li>Features</li>
				<li><a id="btn_21" style="background:none; border:none; padding:0; margin:0;">Managed Service Process</a></li>
			<li><a id="btn_22" style="background:none; border:none; padding:0; margin:0;">Functional and Process Management</a></li>
			<li><a id="btn_23" style="background:none; border:none; padding:0; margin:0;">Business Intelligence & Analytics</a></li>
			<li><a id="btn_24" style="background:none; border:none; padding:0; margin:0;">Business Architecture Management</a></li>
			<li><a id="btn_25" style="background:none; border:none; padding:0; margin:0;">Product Training and Support</a></li>
				
				<li style="border:none"><a style="width:38%; float:left; margin:5px 5px;background:#01bf9d;" href="signUp.html?signUpProcess=preaskcoupon&category=Vendor&getProductPrice=99&productPlan=Basic" class="buy_now">Try Now</a><a style="width:40%; float:left; margin:5px;" href="signUp.html?signUpProcess=pricingDetails&getProductPrice=99&productPlan=Basic" class="buy_now">Buy Now</a></li>
				<!--<li><a href="" class="buy_now">Buy Now</a></li>-->
			</ul>
			
			<ul class="pricing active" id="seven" onMouseOver="tab('seven')">
				<li style="background:coral;">Silver</li>
				<li>$299<span style="font-size:12px;">month</span></li>
				<li style="background:#000; padding:5px 7px; color:#fff;">One Time Setup Cost<br><span style="font-size:13px;">$2999</span></li>
				<!--<li>Perfect for those getting started with our app.</li>-->
				<li>Features</li>
				<li><a id="btn_26" style="background:none; border:none; padding:0; margin:0;">Managed Service Process</a></li>
			<li><a id="btn_27" style="background:none; border:none; padding:0; margin:0;">Functional and Process Management</a></li>
			<li><a id="btn_28" style="background:none; border:none; padding:0; margin:0;">Business Intelligence</a></li>
			<li><a id="btn_29" style="background:none; border:none; padding:0; margin:0;">Business Architecture Management</a></li>
			<li><a id="btn_30" style="background:none; border:none; padding:0; margin:0;">Product Training and Support</a></li>
				<li style="border:none"><a style="width:50%; float:left; margin:10px 50px;" href="signUp.html?signUpProcess=pricingDetails&getProductPrice=299&productPlan=Silver&category=Vendor" class="buy_now">Buy Now</a></li>
			</ul>
			
			<ul class="pricing" id="eight" onMouseOver="tab('eight')">
				<li style="background:goldenrod;">Gold</li>
				<li>$1249<span style="font-size:12px;">month</span></li>
				<li style="background:#000; padding:5px 7px; color:#fff;">One Time Setup Cost<br><span style="font-size:13px;">$4999</span></li>
				<!--<li>Perfect for those getting started with our app.</li>-->
				<li>Features</li>
				<li><a id="btn_31" style="background:none; border:none; padding:0; margin:0;">Managed Service Process</a></li>
			<li><a id="btn_32" style="background:none; border:none; padding:0; margin:0;">Functional and Process Management</a></li>
			<li><a id="btn_33" style="background:none; border:none; padding:0; margin:0;">Business Intelligence</a></li>
			<li><a id="btn_34" style="background:none; border:none; padding:0; margin:0;">Business Architecture Management</a></li>
			<li><a id="btn_35" style="background:none; border:none; padding:0; margin:0;">Product Training and Support</a></li>
				<li style="border:none"><a style="width:50%; float:left; margin:10px 50px;" href="signUp.html?signUpProcess=pricingDetails&getProductPrice=1249&productPlan=Gold&category=Vendor" class="buy_now">Buy Now</a></li>
			</ul>
			
			<ul class="pricing" id="nine" onMouseOver="tab('nine')">
				<li style="background:palevioletred;">Platinum</li>
				<li>$4999/<span style="font-size:12px;">month</span></li>
				<li style="background:#000; padding:5px 7px; color:#fff;">One Time Setup Cost<br><span style="font-size:13px;">$14999</span></li>
				<!--<li>Perfect for those getting started with our app.</li>-->
				<li>Features</li>
				<li><a id="btn_36" style="background:none; border:none; padding:0; margin:0;">Managed Service Process</a></li>
			<li><a id="btn_37" style="background:none; border:none; padding:0; margin:0;">Functional and Process Management</a></li>
			<li><a id="btn_38" style="background:none; border:none; padding:0; margin:0;">Business Intelligence</a></li>
			<li><a id="btn_39" style="background:none; border:none; padding:0; margin:0;">Business Architecture Management</a></li>
			<li><a id="btn_40" style="background:none; border:none; padding:0; margin:0;">Product Training and Support</a></li>
				<li style="border:none"><a style="width:50%; float:left; margin:10px 50px;" href="signUp.html?signUpProcess=pricingDetails&getProductPrice=4999&productPlan=Platinum&category=Vendor" class="buy_now">Buy Now</a></li>
			</ul>
			
			<ul class="pricing" id="ten" onMouseOver="tab('ten')">
				<li style="background:#334960;">Enterprise</li>
				<li>-/<span style="font-size:12px;">month</span></li>
				<li style="background:#000; padding:5px 7px; color:#fff;">One Time Setup Cost<br><span style="font-size:13px;">-</span></li>
				<li>Features</li>
				<li style="border-bottom:none; font-weight:bold; color:red;">Call For Pricing</a></li>
			
			</ul>
			
		
			</div>
			<%if(mF_dBStore_url != null){ %>
<h4 style="text-decoration:underline"><a href="<%=mF_dBStore_url%>" target="_blank">Click Here to Purchase More Bundles</a></h4>
<%}else{ %>
<h4 style="text-decoration:underline"><a href="http://74.208.110.192:8480/yes-shop/category/10491" target="_blank">Click Here to Purchase More Bundles</a></h4>
<%} %>
<h2 style="text-align:center; font-family: Verdana, Arial, Helvetica, sans-serif; margin-top:40px; text-transform:capitalize;">Basic Package Starts With 30 Days Free Trial</h2>
			
		
	</div>
	
	
	
	
	</div>
		
<div style="clear:both;">

</div>

</div>



		</section>
	</div>	
		
		 <script src="js/popup_view.js"></script>
<script>
var popupView = new popup();

document.querySelector('#btn_1').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_1'));
});

document.querySelector('#btn_2').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_2'), function () {
        console.log('show do something');
    });
});

document.querySelector('#btn_3').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_3'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_4').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_4'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_5').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_5'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_6').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_6'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_7').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_7'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_8').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_8'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_9').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_9'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_10').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_10'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_11').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_11'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_12').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_12'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_13').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_13'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_14').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_14'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_15').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_15'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_16').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_16'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_17').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_17'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_18').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_18'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_19').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_19'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_20').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_20'), '', function () {
        console.log('CLOSE');
    });
});

document.querySelector('#btn_21').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_21'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_22').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_22'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_23').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_23'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_24').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_24'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_25').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_25'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_26').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_26'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_27').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_27'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_28').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_28'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_29').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_29'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_30').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_30'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_31').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_31'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_32').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_32'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_33').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_33'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_34').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_34'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_35').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_35'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_36').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_36'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_37').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_37'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_38').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_38'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_39').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_39'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_40').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_40'), '', function () {
        console.log('CLOSE');
    });
});
document.querySelector('#btn_41').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_41'), '', function () {
        console.log('CLOSE');
    });
});

document.querySelector('#btn_42').addEventListener('click', function () {
    popupView.show(document.querySelector('#popup_42'), '', function () {
        console.log('CLOSE');
    });
});

</script>
	

		 <div id="popup_1" class="pop_up popup_hide">
        <h1>Managed Service Process</h1>
        <div class="align">
       
        <div class="membership">
            <div class="member1">
                <h5>User</h5>
                <div class="head" style="height:525px; ">
                <p>MSP : Account manager <span>2</span></p>
                <p>MSP : Hiring Manager <span>1</span></p>
                <p>MSP : Recruiter <span>1</span></p>
                <p>MSP : Hr Manager <span>1</span></p>
                <p>MSP : Finance <span>1</span></p>
				<p>MSP : Admin <span>1</span></p>
				<p>Supplier : Account manager <span>5</span></p>
				<p>Supplier : Recruiter<span>10</span></p>
				<p>Supplier : Hr Manager <span>5</span></p>
				<p>Supplier : Finance  <span>5</span></p>
				<p>Supplier : Admin <span>5</span></p>
				<p>End Client : Hiring Manager <span>1</span></p>
				<p>End Client : Hr Manager <span>1</span></p>
				<p>End Client : Finance<span>1</span></p>
				<p>End Client : Admin <span>1</span></p>
				<p>End Client : Corporate User <span>1</span></p>
				<p>End Client : Business Head User <span>1</span></p>
				<p>End Client : Hr Manager <span>1</span></p>
				<p>Candidate <span>250</span></p>
				
                </div>
            </div>
             <div class="member1">
                <h5>Immigration Compliance Management</h5>
                <div class="head" style="height:180px;">
                <p align="center">-</p>
                
                
                </div>
            </div>
             <div class="member1">
                <h5>Vendor Management</h5>
               <div class="head" style="height:180px">
                <p>No of Vendors <span>5</span></p>
             
                </div>
            </div>
             <div class="member1">
                <h5>Client Management</h5>
                <div class="head"  style="height:110px;">
                <p style="text-align:center;">Included</p>
              
                </div>
            </div>
             <div class="member1">
                <h5>Campaign Management</h5>
                <div class="head" style="height:110px;">
                <p>No of E-mail Campaigns to vendors <span>25</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Recruitment Screening Process</h5>
               <div class="head" style="height:110px;">
                <p style="text-align:center;">Include</p>
               
                </div>
				
            </div>
			<div class="member1">
                <h5>Storage</h5>
                <div class="head" style="height:110px;">
                <p align="center">50GB</p>
                
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
	
		
	<!--functional process.-->
    
    <div id="popup_2" class="pop_up popup_hide">
        <h1>Functional and Process Management</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>ERP</h5>
                <div class="head">
                <p>No. of Modules <span>2</span></p>
                <p>No. of Forms <span>10</span></p>
                <p>No. of Screens <span>40</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Business Services</h5>
                <div class="head">
                <p>No. of Provider Services <span>2</span></p>
                <p>No. of Consumer Services <span>6</span></p>
                <p>No. of Service LifeCycle Design Allowed <span>1</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Process Automation</h5>
               <div class="head">
                <p>No. of Single Instance Process <span>2</span></p>
                <p>No. of Multi-Instance Process <span>1</span></p>
                <p>No. of Process LifeCycle Design Allowed <span>1</span></p>
               </div>
            </div>
             
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
    <!--functional process.-->
    
    
    <!--Business Intelligence.-->
    
    <div id="popup_3" class="pop_up popup_hide">
        <h1>Business Intelligence</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Business Intelligence & Analytics</h5>
                <div class="head">
                <p>No. of Business Intelligence & Analytics Modeler Session <span>100</span></p>
                <p>No. of Business Intelligence & Analytics Consumer Session <span>-</span></p>
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
    <!--Business Intelligence.-->
    
     <!--Business Architecture Management.-->
    
    <div id="popup_4" class="pop_up popup_hide">
        <h1>Business Architecture Management</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Asset/ Artifacts management & <br>Governance</h5>
                <div class="head">
                <p>No of un-governed Assets/Artifacts <span>-</span></p>
                <p>No of Governed Assets/Artifacts <span>1</span></p>
                </div>
            </div>
            
             <div class="member1">
                <h5>Business Architecture <br>Modeling Bundle</h5>
                <div class="head">
                <p>No of BA LifeCycle Design Allowed <span>-</span></p>
                <p>No of Business Mapping Allowed <span>-</span></p>
                <p>No of Business of Process Units aligned <span>-</span></p>
                <p>No of Business Reference Architecture <span>-</span></p>
                <p>No of Blue Prints <span>-</span></p>
                </div>
            </div>
            
            <div class="member1">
                <h5>Enterprise Architecture <br>Planning Bundle</h5>
                <div class="head">
                <p>No of EA frameworks <span>-</span></p>
                <p>No of EA LifeCycle Design Allowed <span>-</span></p>
                <p>No of Technical Reference Architecture <span>-</span></p>
                <p>No of Business Processes Alignment <span>-</span></p>
                <p>No of Business Services Alignment <span>-</span></p>
                <p>No of Web/technical Services Alignment <span>-</span></p>
                <p>No of Application component Alignment <span>-</span></p>
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
    <!--Business Architecture Management.-->
    
     <!--Product Training and Support.-->
    
    <div id="popup_5" class="pop_up popup_hide">
        <h1>Product Training and Support</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Remote Technical Support & Training</h5>
                <div class="head">
                <p>Technical Support - Forum, FAQ & Email <span>FREE 24/7</span></p>
                <p>Remote Training & Technical Support - Telephone, Chat, <br>Remote Login & WebEx <span>-</span></p>
                <p>Training & Technical Support - Onsite <span>-</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Business Engineering Technology Support</h5>
                <div class="head">
                <p>Online Support - Forum, FAQ & Email <span>FREE 24/7</span></p>
                <p>Remote BET Support - Telephone, Chat, <br/> Remote Login & WebEx <span>-</span></p>
                <p>BET Support - Onsite <span>-</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Application Integration(API)</h5>
                <div class="head">
                <p align="center">-</p>
               
                
                </div>
            </div>
            <div class="member1">
                <h5>Database Integration</h5>
                <div class="head">
                 <p align="center">-</p>
               
                
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
		
		
		</div>
		
		
		
		<div id="popup_6" class="pop_up popup_hide">
        <h1>Managed Service Process</h1>
     
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>User</h5>
                 <div class="head" style="height:500px; overflow-y:hidden">
                <p>MSP : Account manager <span>3</span></p>
                <p>MSP : Hiring Manager <span>10</span></p>
                <p>MSP : Recruiter <span>20</span></p>
                <p>MSP : Hr Manager <span>2</span></p>
                <p>MSP : Finance <span>2</span></p>
				<p>MSP : Admin <span>1</span></p>
				<p>Supplier : Account manager <span>25</span></p>
				<p>Supplier : Recruiter<span>250</span></p>
				<p>Supplier : Hr Manager <span>10</span></p>
				<p>Supplier : Finance  <span>10</span></p>
				<p>Supplier : Admin <span>10</span></p>
				<p>End Client : Hiring Manager <span>5</span></p>
				<p>End Client : Hr Manager <span>5</span></p>
				<p>End Client : Finance<span>5</span></p>
				<p>End Client : Admin <span>1</span></p>
				<p>End Client : Corporate User <span>5</span></p>
				<p>End Client : Business Head User <span>5</span></p>
				
				<p>Candidate <span>1500</span></p>
				
                </div>
            </div>
             <div class="member1">
                <h5>Immigration Compliance Management</h5>
                <div class="head" style="height:140px;">
                <p align="center">-</p>
                
                
                </div>
            </div>
             <div class="member1">
                <h5>Vendor Management</h5>
               <div class="head" style="height:140px;">
                <p>No of Vendors <span>10</span></p>
             
                </div>
            </div>
             <div class="member1">
                <h5>Client Management</h5>
                <div class="head"  style="height:130px;">
                <p style="text-align:center;">Included</p>
              
                </div>
            </div>
             <div class="member1">
                <h5>Campaign Management</h5>
                <div class="head" style="height:130px;">
                <p>No of E-mail Campaigns to vendors <span>150</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Recruitment Screening Process</h5>
               <div class="head" style="height:110px;">
                <p style="text-align:center;">Include</p>
               
                </div>
				
            </div>
			<div class="member1">
                <h5>Storage</h5>
                <div class="head" style="height:110px;">
                <p align="center">One Storage Bundle Included</p>
                
                </div>
            </div>
        </div>
		 </div>
		  <div class="pop_up_close"></div>
    </div>
      
	<!--functional process.-->
    
    <div id="popup_7" class="pop_up popup_hide">
        <h1>Functional and Process Management</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>ERP</h5>
                <div class="head">
                <p>No. of Modules <span>2</span></p>
                <p>No. of Forms <span>10</span></p>
                <p>No. of Screens <span>40</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Business Services</h5>
                <div class="head">
                <p>No. of Provider Services <span>30</span></p>
                <p>No. of Consumer Services <span>100</span></p>
                <p>No. of Service LifeCycle<br/> Design Allowed <span>2</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Process Automation</h5>
               <div class="head">
                <p>No. of Single Instance Process <span>15</span></p>
                <p>No. of Multi-Instance Process <span>5</span></p>
                <p>No. of Process LifeCycle<br/> Design Allowed <span>2</span></p>
               </div>
            </div>
             
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
    <!--functional process.-->
    
    
    <!--Business Intelligence.-->
    
    <div id="popup_8" class="pop_up popup_hide">
        <h1>Business Intelligence</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Business Intelligence & Analytics</h5>
                <div class="head">
                <p>No. of Business Intelligence & Analytics Modeler Session <span>500</span></p>
                <p>No. of Business Intelligence & Analytics Consumer Session <span>50</span></p>
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
   
    <div id="popup_9" class="pop_up popup_hide">
        <h1>Business Architecture Management</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Asset/ Artifacts management & <br>Governance</h5>
                <div class="head" style="height:270px; overflow-y:hidden">
                <p>No of un-governed Assets/Artifacts <span>1</span></p>
                <p>No of Governed Assets/Artifacts <span>5</span></p>
                </div>
            </div>
            
             <div class="member1">
                <h5>Business Architecture <br>Modeling Bundle</h5>
                <div class="head" style="height:270px; overflow-y:hidden">
                <p>No of BA LifeCycle Design Allowed <span>1</span></p>
                <p>No of Business Mapping Allowed <span>4</span></p>
                <p>No of Business of Process Units aligned <span>1</span></p>
                <p>No of Business Reference Architecture <span>1</span></p>
                <p>No of Blue Prints <span>1</span></p>
                </div>
            </div>
            
            <div class="member1">
                <h5>Enterprise Architecture <br>Planning Bundle</h5>
                <div class="head" style="height:270px; overflow-y:hidden">
                <p>No of EA frameworks <span>1</span></p>
                <p>No of EA LifeCycle Design Allowed <span>1</span></p>
                <p>No of Technical Reference Architecture <span>1</span></p>
                <p>No of Business Processes Alignment <span>1</span></p>
                <p>No of Business Services Alignment <span>5</span></p>
                <p>No of Web/technical Services Alignment <span>20</span></p>
                <p>No of Application component<br/> Alignment <span>100</span></p>
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
 
     
     <div id="popup_10" class="pop_up popup_hide">
        <h1>Product Training and Support</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Remote Technical Support & Training</h5>
                <div class="head" style="height:200px; width:595px; overflow-y:hidden">
                <p>Technical Support - Forum, FAQ & Email <span>FREE 24/7</span></p>
                <p>Remote Training & Technical Support - Telephone, Chat, Remote Login & WebEx <span>One Remote Training & Support Bundle Included</span></p><br>
                <p>Training & Technical Support - Onsite <span>Call For Pricing</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Business Engineering Technology Support</h5>
                <div class="head" style="height:200px; width:595px; overflow-y:hidden">
                <p>Online Support - Forum, FAQ & Email <span>FREE 24/7</span></p>
                <p>Remote BET Support - Telephone,<br/> Chat, Remote Login & WebEx <span>-</span></p>
                <p>BET Support - Onsite <span>Call For Pricing</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Application Integration(API)</h5>
                <div class="head">
               <p align="center">Call For Pricing</p>
               
                
                </div>
            </div>
            <div class="member1">
                <h5>Database Integration</h5>
                <div class="head">
                 <p align="center">Call For Pricing</p>
               
                
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
		
		
		</div>

    

		<div id="popup_11" class="pop_up popup_hide">
        <h1>Managed Service Process</h1>
     
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>User</h5>
                 <div class="head" style="height:510px; overflow-y:hidden">
                <p>MSP : Account manager <span>9</span></p>
                <p>MSP : Hiring Manager <span>30</span></p>
                <p>MSP : Recruiter <span>60</span></p>
                <p>MSP : Hr Manager <span>6</span></p>
                <p>MSP : Finance <span>6</span></p>
				<p>MSP : Admin <span>3</span></p>
				<p>Supplier : Account manager <span>75</span></p>
				<p>Supplier : Recruiter<span>750</span></p>
				<p>Supplier : Hr Manager <span>30</span></p>
				<p>Supplier : Finance  <span>30</span></p>
				<p>Supplier : Admin <span>30</span></p>
				<p>End Client : Hiring Manager <span>15</span></p>
				<p>End Client : Hr Manager <span>15</span></p>
				<p>End Client : Finance<span>15</span></p>
				<p>End Client : Admin <span>3</span></p>
				<p>End Client : Corporate User <span>15</span></p>
				<p>End Client : Business Head User <span>15</span></p>
				
				<p>Candidate <span>4500</span></p>
				
                </div>
            </div>
             <div class="member1">
                <h5>Immigration Compliance Management</h5>
                <div class="head" style="height:170px">
                <p align="center">Included</p>
                
                
                </div>
            </div>
             <div class="member1">
                <h5>Vendor Management</h5>
               <div class="head" style="height:170px;">
                <p>No of Vendors <span>20</span></p>
             
                </div>
            </div>
             <div class="member1">
                <h5>Client Management</h5>
                <div class="head"  style="height:110px;">
                <p style="text-align:center;">Included</p>
              
                </div>
            </div>
             <div class="member1">
                <h5>Campaign Management</h5>
                <div class="head" style="height:110px;">
                <p>No of E-mail Campaigns to vendors <span>750</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Recruitment Screening Process</h5>
               <div class="head" style="height:110px;">
                <p style="text-align:center;">Include</p>
               
                </div>
				
            </div>
			<div class="member1">
                <h5>Storage</h5>
                <div class="head" style="height:110px;">
                <p align="center">Five Storage Bundle Included</p>
                
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
  
    <div id="popup_12" class="pop_up popup_hide">
        <h1>Functional and Process Management</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>ERP</h5>
				<div class="head" style="height:150px; overflow-y:hidden">
        
                <p>No. of Modules <span>10</span></p>
                <p>No. of Forms <span>50</span></p>
                <p>No. of Screens <span>200</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Business Services</h5>
                <div class="head" style="height:150px; overflow-y:hidden">
                <p>No. of Provider Services <span>2</span></p>
                <p>No. of Consumer Services <span>6</span></p>
                <p>No. of Service LifeCycle Design Allowed <span>1</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Process Automation</h5>
               <div class="head" style="height:150px; overflow-y:hidden">
                <p>No. of Single Instance Process <span>2</span></p>
                <p>No. of Multi-Instance Process <span>1</span></p>
                <p>No. of Process LifeCycle Design Allowed <span>1</span></p>
               </div>
            </div>
             
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
    
    <div id="popup_13" class="pop_up popup_hide">
        <h1>Business Intelligence</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Business Intelligence & Analytics</h5>
                <div class="head">
                <p>No. of Business Intelligence & Analytics Modeler Session <span>500</span></p>
                <p>No. of Business Intelligence & Analytics Consumer Session <span>50</span></p>
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
   
    
    <div id="popup_14" class="pop_up popup_hide">
        <h1>Business Architecture Management</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Asset/ Artifacts management & <br>Governance</h5>
                <div class="head">
                <p>No of un-governed Assets/Artifacts <span>1</span></p>
                <p>No of Governed Assets/Artifacts <span>5</span></p>
                </div>
            </div>
            
             <div class="member1">
                <h5>Business Architecture <br>Modeling Bundle</h5>
                <div class="head">
                <p>No of BA LifeCycle Design Allowed <span>5</span></p>
                <p>No of Business Mapping Allowed <span>20</span></p>
                <p>No of Business of Process Units aligned <span>5</span></p>
                <p>No of Business Reference Architecture <span>3</span></p>
                <p>No of Blue Prints <span>5</span></p>
                </div>
            </div>
            
            <div class="member1">
                <h5>Enterprise Architecture <br>Planning Bundle</h5>
                <div class="head">
                <p>No of EA frameworks <span>2</span></p>
                <p>No of EA LifeCycle Design Allowed <span>5</span></p>
                <p>No of Technical Reference Architecture <span>5</span></p>
                <p>No of Business Processes Alignment <span>5</span></p>
                <p>No of Business Services Alignment <span>25</span></p>
                <p>No of Web/technical Services Alignment <span>100</span></p>
                <p>No of Application component Alignment <span>500</span></p>
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
   
    
    <div id="popup_15" class="pop_up popup_hide">
        <h1>Product Training and Support</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Remote Technical Support & Training</h5>
                <div class="head" style="height:200px; width:595px; overflow-y:hidden">
                <p>Technical Support - Forum, FAQ & Email <span>FREE 24/7</span></p>
                <p>Remote Training & Technical Support - Telephone, Chat, Remote Login & WebEx <span>Three Remote Training & Support Bundle Included</span></p><br/>
                <p>Training & Technical Support - Onsite <span>Call for Pricing</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Business Engineering Technology Support</h5>
                <div class="head" style="height:200px; width:595px; overflow-y:hidden">
                <p>Online Support - Forum, FAQ & Email <span>FREE 24/7</span></p>
                <p>Remote BET Support - Telephone, Chat, Remote Login & WebEx <span>-</span></p>
                <p>BET Support - Onsite <span>Call for Pricing</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Application Integration(API)</h5>
                <div class="head">
                <p align="center">Call For Pricing</p>
               
                
                </div>
            </div>
            <div class="member1">
                <h5>Database Integration</h5>
                <div class="head">
                <p align="center">Call For Pricing</p>
               
                
                </div>
			
		</div>
			 </div>
			 </div>
			 <div class="pop_up_close"></div>
		
		
		
		</div>
		
		
		<div id="popup_16" class="pop_up popup_hide">
        <h1>Managed Service Process</h1>
     
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>User</h5>
                <div class="head" style="height:510px; overflow-y:hidden">
                <p>MSP : Account manager <span>45</span></p>
                <p>MSP : Hiring Manager <span>150</span></p>
                <p>MSP : Recruiter <span>300</span></p>
                <p>MSP : Hr Manager <span>30</span></p>
                <p>MSP : Finance <span>30</span></p>
				<p>MSP : Admin <span>15</span></p>
				<p>Supplier : Account manager <span>375</span></p>
				<p>Supplier : Recruiter<span>3750</span></p>
				<p>Supplier : Hr Manager <span>150</span></p>
				<p>Supplier : Finance  <span>150</span></p>
				<p>Supplier : Admin <span>150</span></p>
				<p>End Client : Hiring Manager <span>75</span></p>
				<p>End Client : Hr Manager <span>75</span></p>
				<p>End Client : Finance<span>75</span></p>
				<p>End Client : Admin <span>15</span></p>
				<p>End Client : Corporate User <span>75</span></p>
				<p>End Client : Business Head User <span>75</span></p>
				
				<p>Candidate <span>22500</span></p>
				
                </div>
            </div>
             <div class="member1">
                <h5>Immigration Compliance Management</h5>
                <div class="head" style="height:140px;">
                <p align="center">Included</p>
                
                
                </div>
            </div>
             <div class="member1">
                <h5>Vendor Management</h5>
               <div class="head" style="height:140px;">
                <p>No of Vendors <span>50</span></p>
             
                </div>
            </div>
             <div class="member1">
                <h5>Client Management</h5>
                <div class="head"  style="height:130px;">
                <p style="text-align:center;">Included</p>
              
                </div>
            </div>
             <div class="member1">
                <h5>Campaign Management</h5>
                <div class="head" style="height:130px;">
                <p>No of E-mail Campaigns to vendors <span>3000</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Recruitment Screening Process</h5>
               <div class="head" style="height:120px;">
                <p style="text-align:center;">Include</p>
               
                </div>
				
            </div>
			<div class="member1">
                <h5>Storage</h5>
                <div class="head" style="height:120px;">
                <p align="center">Fifteen Storage Bundle Included</p>
                
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
      
		
		
			
	
    <div id="popup_17" class="pop_up popup_hide">
        <h1>Functional and Process Management</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>ERP</h5>
                <div class="head">
                <p>No. of Modules <span>30</span></p>
                <p>No. of Forms <span>150</span></p>
                <p>No. of Screens <span>600</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Business Services</h5>
                <div class="head">
                <p>No. of Provider Services <span>10</span></p>
                <p>No. of Consumer Services <span>30</span></p>
                <p>No. of Service LifeCycle Design Allowed <span>5</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Process Automation</h5>
               <div class="head">
                <p>No. of Single Instance Process <span>10</span></p>
                <p>No. of Multi-Instance Process <span>5</span></p>
                <p>No. of Process LifeCycle Design Allowed <span>5</span></p>
               </div>
            </div>
             
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
    
    
    <div id="popup_18" class="pop_up popup_hide">
        <h1>Business Intelligence</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Business Intelligence & Analytics</h5>
                <div class="head">
                <p>No. of Business Intelligence & Analytics Modeler Session <span>2500</span></p>
                <p>No. of Business Intelligence & Analytics Consumer Session <span>250</span></p>
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
   
    
    <div id="popup_19" class="pop_up popup_hide">
        <h1>Business Architecture Management</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Asset/ Artifacts management & <br>Governance</h5>
                <div class="head">
                <p>No of un-governed Assets/Artifacts <span>5</span></p>
                <p>No of Governed Assets/Artifacts <span>25</span></p>
                </div>
            </div>
            
             <div class="member1">
                <h5>Business Architecture <br>Modeling Bundle</h5>
                <div class="head">
                <p>No of BA LifeCycle Design Allowed <span>25</span></p>
                <p>No of Business Mapping Allowed <span>100</span></p>
                <p>No of Business of Process Units aligned <span>25</span></p>
                <p>No of Business Reference Architecture <span>15</span></p>
                <p>No of Blue Prints <span>25</span></p>
                </div>
            </div>
            
            <div class="member1">
                <h5>Enterprise Architecture <br>Planning Bundle</h5>
                <div class="head">
                <p>No of EA frameworks <span>10</span></p>
                <p>No of EA LifeCycle Design Allowed <span>25</span></p>
                <p>No of Technical Reference Architecture <span>25</span></p>
                <p>No of Business Processes Alignment <span>25</span></p>
                <p>No of Business Services Alignment <span>125</span></p>
                <p>No of Web/technical Services Alignment <span>500</span></p>
                <p>No of Application component Alignment <span>2500</span></p>
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
   
   <div id="popup_20" class="pop_up popup_hide">
        <h1>Product Training and Support</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Remote Technical Support & Training</h5>
                <div class="head">
                <p>Technical Support - Forum, FAQ & Email <span>FREE 24/7</span></p>
                <p>Remote Training & Technical Support - Telephone, Chat, <br>Remote Login & WebEx <span>Seven Remote Training & Support Bundle Included</span></p>
                <p>Training & Technical Support - Onsite <span>Call For Pricing</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Business Engineering Technology Support</h5>
                <div class="head">
                <p>Online Support - Forum, FAQ & Email <span>FREE 24/7</span></p>
                <p>Remote BET Support - Telephone, Chat, Remote Login & WebEx <span>One Remote BET Support Bundle Included</span></p>
                <p>BET Support - Onsite <span>Call For Pricing</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Application Integration(API)</h5>
                <div class="head">
                <p align="center">Call For Pricing</p>
               
                
                </div>
            </div>
            <div class="member1">
                <h5>Database Integration</h5>
                <div class="head">
                <p align="center">Call For Pricing</p>
               
                
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
		
		
		</div>
		
		<div id="popup_21" class="pop_up popup_hide">
        <h1>Managed Service Process</h1>
     
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>User</h5>
                <div class="head" style="height:260px; overflow-y:hidden;">
                <p>Account manager <span>1</span></p>
                <p>Recruiter <span>5</span></p>
                <p>Hr Manager <span>1</span></p>
                <p>Finance <span>1</span></p>
				<p>Admin <span>1</span></p>
				<p>Corporate User <span>1</span></p>
				<p>Business Head User <span>1</span></p>
				
				<p>Candidate <span>250</span></p>
				
                </div>
            </div>
             <div class="member1">
                <h5>Immigration Compliance Management</h5>
                <div class="head" style="height:85px">
                <p align="center">-</p>
                
                
                </div>
            </div>
             <div class="member1">
                <h5>Sub-Vendor Management</h5>
               <div class="head" style="height:85px;">
                <p>No of Sub-Vendors <span>100</span></p>
             
                </div>
            </div>
             <div class="member1">
                <h5>Client Management</h5>
                <div class="head"  style="height:95px;">
                <p style="text-align:center;">Included</p>
              
                </div>
            </div>
             <div class="member1">
                <h5>Campaign Management</h5>
                <div class="head" style="height:95px;">
                <p>No of E-mail Campaigns to Sub-Vendors <span>25</span></p>
                <p>No of E-mails <span>10000</span></p>
                </div>
            </div>
             <div class="member1" style="width:50%">
                <h5>Recruitment Screening Process</h5>
               <div class="head" style="height:60px;">
                <p style="text-align:center;">Include</p>
               
                </div>
				
            </div>
			<div class="member1" style="width:50%">
                <h5>Storage</h5>
                <div class="head" style="height:60px;">
                <p align="center">50GB</p>
                
                </div>
            </div>
        </div>
         </div>
		
        <div class="pop_up_close"></div>
       
		</div>	
	
    <div id="popup_22" class="pop_up popup_hide">
        <h1>Functional and Process Management</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>ERP</h5>
                <div class="head">
                <p>No. of Modules <span>2</span></p>
                <p>No. of Forms <span>10</span></p>
                <p>No. of Screens <span>40</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Business Services</h5>
                <div class="head">
                <p>No. of Provider Services <span>2</span></p>
                <p>No. of Consumer Services <span>6</span></p>
                <p>No. of Service LifeCycle Design Allowed <span>1</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Process Automation</h5>
               <div class="head">
                <p>No. of Single Instance Process <span>2</span></p>
                <p>No. of Multi-Instance Process <span>1</span></p>
                <p>No. of Process LifeCycle Design Allowed <span>1</span></p>
               </div>
            </div>
             
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
   
    
    <div id="popup_23" class="pop_up popup_hide">
        <h1>Business Intelligence</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Business Intelligence & <br/> Analytics</h5>
                <div class="head">
                <p>No. of Business Intelligence & Analytics Modeler Session <span>-</span></p>
                <p>No. of Business Intelligence & Analytics Consumer Session <span>1</span></p>
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
   
    
    <div id="popup_24" class="pop_up popup_hide">
        <h1>Business Architecture Management</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Asset/ Artifacts management & <br>Governance</h5>
                <div class="head">
                <p>No of un-governed Assets/Artifacts <span>100</span></p>
                <p>No of Governed Assets/Artifacts <span>-</span></p>
                </div>
            </div>
            
             <div class="member1">
                <h5>Business Architecture <br>Modeling Bundle</h5>
                <div class="head">
                <p>No of BA LifeCycle Design Allowed <span>-</span></p>
                <p>No of Business Mapping Allowed <span>-</span></p>
                <p>No of Business of Process Units aligned <span>-</span></p>
                <p>No of Business Reference Architecture <span>-</span></p>
                <p>No of Blue Prints <span>-</span></p>
                </div>
            </div>
            
            <div class="member1">
                <h5>Enterprise Architecture <br>Planning Bundle</h5>
                <div class="head">
                <p>No of EA frameworks <span>-</span></p>
                <p>No of EA LifeCycle Design Allowed <span>-</span></p>
                <p>No of Technical Reference Architecture <span>-</span></p>
                <p>No of Business Processes Alignment <span>-</span></p>
                <p>No of Business Services Alignment <span>-</span></p>
                <p>No of Web/technical Services Alignment <span>-</span></p>
                <p>No of Application component Alignment <span>-</span></p>
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
   
    
		
		
		
		<div id="popup_25" class="pop_up popup_hide">
        <h1>Manage Service Process</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Remote Technical Support & Training</h5>
                <div class="head">
                <p>Technical Support - Forum, FAQ & Email <span>FREE 24/7</span></p>
                <p>Remote Training & Technical Support - Telephone, Chat, <br>Remote Login & WebEx <span>-</span></p>
                <p>Training & Technical Support - Onsite <span>-</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Business Engineering Technology Support</h5>
                <div class="head">
                <p>Online Support - Forum, FAQ & Email <span>FREE 24/7</span></p>
                <p>Remote BET Support - Telephone, Chat, Remote Login & WebEx <span>-</span></p>
                <p>BET Support - Onsite <span>-</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Application Integration(API)</h5>
                <div class="head" style="height:60px;">
                <p align="center">-</p>
               
                
                </div>
            </div>
            <div class="member1">
                <h5>Database Integration</h5>
                <div class="head" style="height:60px;">
                <p align="center">-</p>
               
                
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
		
		
		</div>
		
			
		
		
		
	<div id="popup_26" class="pop_up popup_hide">
        <h1>Managed Service Process</h1>
     
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>User</h5>
                <div class="head" style="height:260px; overflow-y:hidden;">
                <p>Account manager <span>25</span></p>
                <p>Recruiter <span>25</span></p>
                <p>Hr Manager <span>5</span></p>
                <p>Finance <span>5</span></p>
				<p>Admin <span>5</span></p>
				<p>Corporate User <span>1500</span></p>
				<p>Business Head User <span>5</span></p>
				
				<p>Candidate <span>5</span></p>
				
                </div>
            </div>
             <div class="member1">
                <h5>Immigration Compliance Management</h5>
                <div class="head" style="height:80px;">
                <p align="center">Included</p>
                
                
                </div>
            </div>
             <div class="member1">
                <h5>Sub-Vendor Management</h5>
               <div class="head" style="height:80px;">
                <p>No of Sub-Vendors <span>1000</span></p>
             
                </div>
            </div>
             <div class="member1">
                <h5>Client Management</h5>
                <div class="head"  style="height:125px;">
                <p style="text-align:center;">Included</p>
              
                </div>
            </div>
             <div class="member1">
                <h5>Campaign Management</h5>
                <div class="head" style="height:125px;">
                <p>No of E-mail Campaigns to Sub-Vendors <span>150</span></p>
                <p>No of E-mails <span>60000</span></p>
                </div>
            </div>
             <div class="member1" style="width:50%">
                <h5>Recruitment Screening Process</h5>
               <div class="head" style="height:60px;">
                <p style="text-align:center;">Include</p>
               
                </div>
				
            </div>
			<div class="member1" style="width:50%">
                <h5>Storage</h5>
                <div class="head" style="height:60px;">
                <p align="center">One Storage Bundle Included</p>
                
                </div>
            </div>
        </div>
         </div>
		
        <div class="pop_up_close"></div>
       </div>
	
    
    <div id="popup_27" class="pop_up popup_hide">
        <h1>Functional and Process Management</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>ERP</h5>
                <div class="head">
                <p>No. of Modules <span>2</span></p>
                <p>No. of Forms <span>10</span></p>
                <p>No. of Screens <span>40</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Business Services</h5>
                <div class="head">
                <p>No. of Provider Services <span>30</span></p>
                <p>No. of Consumer Services <span>100</span></p>
                <p>No. of Service LifeCycle Design Allowed <span>2</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Process Automation</h5>
               <div class="head">
                <p>No. of Single Instance Process <span>15</span></p>
                <p>No. of Multi-Instance Process <span>5</span></p>
                <p>No. of Process LifeCycle Design Allowed <span>2</span></p>
               </div>
            </div>
             
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
  
    
    
    <div id="popup_28" class="pop_up popup_hide">
        <h1>Business Intelligence</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Business Intelligence<br/> & Analytics</h5>
                <div class="head">
                <p>No. of Business Intelligence & Analytics Modeler Session <span>1</span></p>
                <p>No. of Business Intelligence & Analytics Consumer Session <span>5</span></p>
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
  
    
    <div id="popup_29" class="pop_up popup_hide">
        <h1>Business Architecture Management</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Asset/ Artifacts management & <br>Governance</h5>
                <div class="head">
                <p>No of un-governed Assets/Artifacts <span>1</span></p>
                <p>No of Governed Assets/Artifacts <span>5</span></p>
                </div>
            </div>
            
             <div class="member1">
                <h5>Business Architecture <br>Modeling Bundle</h5>
                <div class="head">
                <p>No of BA LifeCycle Design Allowed <span>1</span></p>
                <p>No of Business Mapping Allowed <span>4</span></p>
                <p>No of Business of Process Units aligned <span>1</span></p>
                <p>No of Business Reference Architecture <span>1</span></p>
                <p>No of Blue Prints <span>1</span></p>
                </div>
            </div>
            
            <div class="member1">
                <h5>Enterprise Architecture <br>Planning Bundle</h5>
                <div class="head">
                <p>No of EA frameworks <span>1</span></p>
                <p>No of EA LifeCycle Design Allowed <span>1</span></p>
                <p>No of Technical Reference Architecture <span>1</span></p>
                <p>No of Business Processes Alignment <span>1</span></p>
                <p>No of Business Services Alignment <span>5</span></p>
                <p>No of Web/technical Services Alignment <span>20</span></p>
                <p>No of Application component Alignment <span>100</span></p>
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
    
    
		
		
		
		<div id="popup_30" class="pop_up popup_hide">
        <h1>Product Training & Support</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Remote Technical Support & Training</h5>
                <div class="head">
                <p>Technical Support - Forum, FAQ & Email <span>FREE 24/7</span></p>
                <p>Remote Training & Technical Support - Telephone, Chat, <br>Remote Login & WebEx <span>One Remote Training & Support Bundle Included</span></p>
                <p>Training & Technical Support - Onsite <span>Call For Pricing</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Business Engineering Technology Support</h5>
                <div class="head">
                <p>Online Support - Forum, FAQ & Email <span>FREE 24/7</span></p>
                <p>Remote BET Support - Telephone, Chat, Remote Login & WebEx <span>-</span></p>
                <p>BET Support - Onsite <span>Call For Pricing</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Application Integration(API)</h5>
                <div class="head" style="height:60px;">
                <p align="center">Call For Pricing</p>
               
                
                </div>
            </div>
            <div class="member1">
                <h5>Database Integration</h5>
                <div class="head" style="height:60px;">
                <p align="center">Call For Pricing</p>
               
                
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
		
		
		</div>
		
	
		
		
		
		
		
		<div id="popup_31" class="pop_up popup_hide">
        <h1>Managed Service Process</h1>
     
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>User</h5>
                <div class="head" style="height:240px; overflow-y: hidden;">
                <p>Account manager <span>75</span></p>
                <p>Recruiter <span>75</span></p>
                <p>Hr Manager <span>15</span></p>
                <p>Finance <span>15</span></p>
				<p>Admin <span>15</span></p>
				<p>Corporate User <span>4500</span></p>
				<p>Business Head User <span>15</span></p>
				
				<p>Candidate <span>15</span></p>
				
                </div>
            </div>
             <div class="member1">
                <h5>Immigration Compliance Management</h5>
                <div class="head" style="height:95px;">
                <p align="center">Included</p>
                
                
                </div>
            </div>
             <div class="member1">
                <h5>Sub-Vendor Management</h5>
               <div class="head" style="height:95px;">
                <p>No of Sub-Vendors <span>2000</span></p>
             
                </div>
            </div>
             <div class="member1">
                <h5>Client Management</h5>
                <div class="head"  style="height:85px;">
                <p style="text-align:center;">Included</p>
              
                </div>
            </div>
             <div class="member1">
                <h5>Campaign Management</h5>
                <div class="head" style="height:85px;">
                <p>No of E-mail Campaigns to Sub-Vendors <span>750</span></p>
                <p>No of E-mails <span>300000</span></p>
                </div>
            </div>
             <div class="member1" style="width:50%" >
                <h5>Recruitment Screening Process</h5>
               <div class="head" style="height:55px;">
                <p style="text-align:center;">Include</p>
               
                </div>
				
            </div>
			<div class="member1" style="width:50%" >
                <h5>Storage</h5>
                <div class="head" style="height:55px;">
                <p style="text-align:center;">Five Storage Bundle Included</p>
                
                </div>
            </div>
        </div>
         </div>
		
        <div class="pop_up_close"></div>
       
			</div>
	<!--functional process.-->
    
    <div id="popup_32" class="pop_up popup_hide">
        <h1>Functional and Process Management</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>ERP</h5>
                <div class="head">
                <p>No. of Modules <span>10</span></p>
                <p>No. of Forms <span>50</span></p>
                <p>No. of Screens <span>200</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Business Services</h5>
                <div class="head">
                <p>No. of Provider Services <span>2</span></p>
                <p>No. of Consumer Services <span>6</span></p>
                <p>No. of Service LifeCycle Design Allowed <span>1</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Process Automation</h5>
               <div class="head">
                <p>No. of Single Instance Process <span>2</span></p>
                <p>No. of Multi-Instance Process <span>1</span></p>
                <p>No. of Process LifeCycle Design Allowed <span>1</span></p>
               </div>
            </div>
             
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
    <!--functional process.-->
    
    
    <!--Business Intelligence.-->
    
    <div id="popup_33" class="pop_up popup_hide">
        <h1>Business Intelligence</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Business Intelligence & Analytics</h5>
                <div class="head">
                <p>No. of Business Intelligence & Analytics Modeler Session <span>1</span></p>
                <p>No. of Business Intelligence & Analytics Consumer Session <span>5</span></p>
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
    <!--Business Intelligence.-->
    
     <!--Business Architecture Management.-->
    
    <div id="popup_34" class="pop_up popup_hide">
        <h1>Business Architecture Management</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Asset/ Artifacts management & <br>Governance</h5>
                <div class="head">
                <p>No of un-governed Assets/Artifacts <span>500</span></p>
                <p>No of Governed Assets/Artifacts <span>50</span></p>
                </div>
            </div>
            
             <div class="member1">
                <h5>Business Architecture <br>Modeling Bundle</h5>
                <div class="head">
                <p>No of BA LifeCycle Design Allowed <span>5</span></p>
                <p>No of Business Mapping Allowed <span>20</span></p>
                <p>No of Business of Process Units aligned <span>5</span></p>
                <p>No of Business Reference Architecture <span>3</span></p>
                <p>No of Blue Prints <span>5</span></p>
                </div>
            </div>
            
            <div class="member1">
                <h5>Enterprise Architecture <br>Planning Bundle</h5>
                <div class="head">
                <p>No of EA frameworks <span>2</span></p>
                <p>No of EA LifeCycle Design Allowed <span>5</span></p>
                <p>No of Technical Reference Architecture <span>5</span></p>
                <p>No of Business Processes Alignment <span>5</span></p>
                <p>No of Business Services Alignment <span>25</span></p>
                <p>No of Web/technical Services Alignment <span>100</span></p>
                <p>No of Application component Alignment <span>500</span></p>
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
    <!--Business Architecture Management.-->
    
     <!--Product Training and Support.-->
    
		
		
		
		<div id="popup_35" class="pop_up popup_hide">
        <h1>Product Training & Support</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Remote Technical Support & Training</h5>
                <div class="head">
                <p>Technical Support - Forum, FAQ & Email <span>FREE 24/7</span></p>
                <p>Remote Training & Technical Support - Telephone, Chat, <br>Remote Login & WebEx <span>Three Remote Training & Support Bundle Included</span></p>
                <p>Training & Technical Support - Onsite <span>Call For Pricing</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Business Engineering  Technology Support</h5>
                <div class="head">
                <p>Online Support - Forum, FAQ & Email <span>FREE 24/7</span></p>
                <p>Remote BET Support - Telephone, Chat, Remote Login & WebEx <span>-</span></p>
                <p>BET Support - Onsite <span>Call For Pricing</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Application Integration(API)</h5>
                <div class="head" style="height:60px;">
                <p align="center">Call For Pricing</p>
               
                
                </div>
            </div>
            <div class="member1">
                <h5>Database Integration</h5>
                <div class="head" style="height:60px;">
                <p align="center">Call For Pricing</p>
               
                
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
		
		
		</div>
		
		
		
		
		
		
		<div id="popup_36" class="pop_up popup_hide">
        <h1>Managed Service Process</h1>
     
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>User</h5>
                 <div class="head" style="height:320px; overflow-y:hidden;">
                <p>Account manager <span>375</span></p>
                <p>Recruiter <span>375</span></p>
                <p>Hr Manager <span>75</span></p>
                <p>Finance <span>75</span></p>
				<p>Admin <span>75</span></p>
				<p>Corporate User <span>22500</span></p>
				<p>Business Head User <span>75</span></p>
				
				<p>Candidate <span>75</span></p>
				
                </div>
            </div>
             <div class="member1">
                <h5>Immigration Compliance Management</h5>
                <div class="head" style="height:60px;">
                <p align="center">Included</p>
                
                
                </div>
            </div>
             <div class="member1">
                <h5>Sub-Vendor Management</h5>
              <div class="head" style="height:60px;">
                <p>No of Sub-Vendors <span>5000</span></p>
             
                </div>
            </div>
             <div class="member1">
                <h5>Client Management</h5>
                <div class="head"  style="height:80px;">
                <p style="text-align:center;">Included</p>
              
                </div>
            </div>
             <div class="member1">
                <h5>Campaign Management</h5>
                <div class="head" style="height:80px;">
                <p>No of E-mail Campaigns to Sub-Vendors <span>3000</span></p>
                <p>No of E-mails <span>1200000</span></p>
                </div>
            </div>
             <div class="member1">
                <h5>Recruitment Screening Process</h5>
               <div class="head" style="height:60px;">
                <p style="text-align:center;">Include</p>
               
                </div>
				
            </div>
			<div class="member1">
                <h5>Storage</h5>
                <div class="head" style="height:60px;">
                <p><span>Fifteen Storage Bundle Included</span></p>
                
                </div>
            </div>
        </div>
         </div>
		
        <div class="pop_up_close"></div>
       
		</div>	
	<!--functional process.-->
    
    <div id="popup_37" class="pop_up popup_hide">
        <h1>Functional and Process<br/> Management</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>ERP</h5>
                <div class="head">
                <p>No. of Modules <span>30</span></p>
                <p>No. of Forms <span>150</span></p>
                <p>No. of Screens <span>600</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Business Services</h5>
                <div class="head">
                <p>No. of Provider Services <span>10</span></p>
                <p>No. of Consumer Services <span>30</span></p>
                <p>No. of Service LifeCycle Design Allowed <span>5</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Process Automation</h5>
               <div class="head">
                <p>No. of Single Instance Process <span>10</span></p>
                <p>No. of Multi-Instance Process <span>5</span></p>
                <p>No. of Process LifeCycle Design Allowed <span>5</span></p>
               </div>
            </div>
             
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
    <!--functional process.-->
    
    
    <!--Business Intelligence.-->
    
    <div id="popup_38" class="pop_up popup_hide">
        <h1>Business Intelligence</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Business Intelligence & Analytics</h5>
                <div class="head">
                <p>No. of Business Intelligence & Analytics Modeler Session <span>5</span></p>
                <p>No. of Business Intelligence & Analytics Consumer Session <span>25</span></p>
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
    <!--Business Intelligence.-->
    
     <!--Business Architecture Management.-->
    
    <div id="popup_39" class="pop_up popup_hide">
        <h1>Business Architecture Management</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Asset/ Artifacts management & <br>Governance</h5>
                <div class="head">
                <p>No of un-governed Assets/Artifacts <span>2500</span></p>
                <p>No of Governed Assets/Artifacts <span>250</span></p>
                </div>
            </div>
            
             <div class="member1">
                <h5>Business Architecture <br>Modeling Bundle</h5>
                <div class="head">
                <p>No of BA LifeCycle Design Allowed <span>25</span></p>
                <p>No of Business Mapping Allowed <span>100</span></p>
                <p>No of Business of Process Units aligned <span>25</span></p>
                <p>No of Business Reference Architecture <span>15</span></p>
                <p>No of Blue Prints <span>25</span></p>
                </div>
            </div>
            
            <div class="member1">
                <h5>Enterprise Architecture <br>Planning Bundle</h5>
                <div class="head">
                <p>No of EA frameworks <span>10</span></p>
                <p>No of EA LifeCycle Design Allowed <span>25</span></p>
                <p>No of Technical Reference Architecture <span>25</span></p>
                <p>No of Business Processes Alignment <span>25</span></p>
                <p>No of Business Services Alignment <span>125</span></p>
                <p>No of Web/technical Services Alignment <span>500</span></p>
                <p>No of Application component Alignment <span>2500</span></p>
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
       
    </div>
    
    <!--Business Architecture Management.-->
    
     <!--Product Training and Support.-->
    
		
		
		
		<div id="popup_40" class="pop_up popup_hide">
        <h1>Product Training & Support</h1>
        <div class="align">
        <div class="spacer"></div>
        <div class="membership">
            <div class="member1">
                <h5>Remote Technical Support & Training</h5>
                <div class="head">
                <p>Technical Support - Forum, FAQ & Email <span>FREE 24/7</span></p>
                <p>Remote Training & Technical Support - Telephone, Chat, Remote Login & WebEx <span>Seven Remote Training & Support Bundle Included</span></p>
                <p>Training & Technical Support - Onsite <span>Call For Pricing</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Business Engineering Technology Support</h5>
                <div class="head">
                <p>Online Support - Forum, FAQ & Email <span>FREE 24/7</span></p>
                <p>Remote BET Support - Telephone, Chat, Remote Login & WebEx <span>One Remote BET Support Bundle Included</span></p>
                <p>BET Support - Onsite <span>Call For Pricing</span></p>
                
                </div>
            </div>
             <div class="member1">
                <h5>Application Integration(API)</h5>
                <div class="head" style="height:60px;">
                <p align="center">Call For Pricing</p>
               
                
                </div>
            </div>
            <div class="member1">
                <h5>Database Integration</h5>
                <div class="head" style="height:60px;">
                <p align="center">Call For Pricing</p>
               
                
                </div>
            </div>
        </div>
         </div>
        <div class="pop_up_close"></div>
		
		
		</div>
		
		

<form accept-charset="UTF-8" style="display:none"   action="https://ho192.infusionsoft.com/app/form/process/701dd4618eefd02c651095c54751db3c" class="infusion-form" id="inf_form_701dd4618eefd02c651095c54751db3c" method="POST" name="Web Form submitted" onsubmit="var form = document.forms[0];
var resolution = document.createElement('input');
resolution.setAttribute('id', 'screenResolution');
resolution.setAttribute('type', 'hidden');
resolution.setAttribute('name', 'screenResolution');
var resolutionString = screen.width + 'x' + screen.height;
resolution.setAttribute('value', resolutionString);
form.appendChild(resolution);
var pluginString = '';
if (window.ActiveXObject) {
    var activeXNames = {'AcroPDF.PDF':'Adobe Reader',
        'ShockwaveFlash.ShockwaveFlash':'Flash',
        'QuickTime.QuickTime':'Quick Time',
        'SWCtl':'Shockwave',
        'WMPLayer.OCX':'Windows Media Player',
        'AgControl.AgControl':'Silverlight'};
    var plugin = null;
    for (var activeKey in activeXNames) {
        try {
            plugin = null;
            plugin = new ActiveXObject(activeKey);
        } catch (e) {
            // do nothing, the plugin is not installed
        }
        pluginString += activeXNames[activeKey] + ',';
    }
    var realPlayerNames = ['rmockx.RealPlayer G2 Control',
        'rmocx.RealPlayer G2 Control.1',
        'RealPlayer.RealPlayer(tm) ActiveX Control (32-bit)',
        'RealVideo.RealVideo(tm) ActiveX Control (32-bit)',
        'RealPlayer'];
    for (var index = 0; index &lt; realPlayerNames.length; index++) {
        try {
            plugin = new ActiveXObject(realPlayerNames[index]);
        } catch (e) {
            continue;
        }
        if (plugin) {
            break;
        }
    }
    if (plugin) {
        pluginString += 'RealPlayer,';
    }
} else {
    for (var i = 0; i &lt; navigator.plugins.length; i++) {
        pluginString += navigator.plugins[i].name + ',';
    }
}
pluginString = pluginString.substring(0, pluginString.lastIndexOf(','));
var plugins = document.createElement('input');
plugins.setAttribute('id', 'pluginList');
plugins.setAttribute('type', 'hidden');
plugins.setAttribute('name', 'pluginList');
plugins.setAttribute('value', pluginString);
form.appendChild(plugins);
var java = navigator.javaEnabled();
var javaEnabled = document.createElement('input');
javaEnabled.setAttribute('id', 'javaEnabled');
javaEnabled.setAttribute('type', 'hidden');
javaEnabled.setAttribute('name', 'javaEnabled');
javaEnabled.setAttribute('value', java);
form.appendChild(javaEnabled);">

 
<input name="inf_form_xid" type="hidden" value="701dd4618eefd02c651095c54751db3c" /><input name="inf_form_name" type="hidden" value="Web Form submitted" /><input name="infusionsoft_version" type="hidden" value="1.68.0.59" />
<div class="default beta-base beta-font-b" id="mainContent" style="height:100%">
<table cellpadding="10" cellspacing="0" class="background" style="width: 100%; height: 100%">
<tbody>
<tr>
<td align="center" valign="top">
<table bgcolor="#FFFFFF" cellpadding="20" cellspacing="0" class="bodyContainer webFormBodyContainer" width="100%">
<tbody>
<tr>
<td bgcolor="#FFFFFF" class="body" sectionid="body" valign="top">
<div>
<table class="infusion-field-container" style="width:100%;">
<tbody>
<tr>

<td class="infusion-field-input-container" style="width:200px;">
<input class="infusion-field-input-container" id="inf_field_FirstName" name="inf_field_FirstName" type="text"  />
 <span id="lblValue">The text box contains: </span>
</td>
</tr>
<tr>

<td class="infusion-field-input-container" style="width:200px;">
<input class="infusion-field-input-container" id="inf_field_LastName" name="inf_field_LastName" type="text"  />
</td>
</tr>
</tbody>
</table>
</div>
<div>
<table class="infusion-field-container" style="width:100%;">
<tbody>
<tr>

<td class="infusion-field-input-container" style="width:200px;">
<input class="infusion-field-input-container" id="inf_field_Company" name="inf_field_Company" type="text"  />
</td>
</tr>
</tbody>
</table>
</div>
<div>
<table class="infusion-field-container" style="width:100%;">
<tbody>
<tr>

<td class="infusion-field-input-container" style="width:200px;">
<input class="infusion-field-input-container" id="inf_custom_Title0" name="inf_custom_Title0" type="text"  />
</td>
</tr>
</tbody>
</table>
</div>
<div>
<table class="infusion-field-container" style="width:100%;">
<tbody>
<tr>

<td class="infusion-field-input-container" style="width:200px;">
<input class="infusion-field-input-container" id="inf_custom_JobType" name="inf_custom_JobType" type="text"  />
</td>
</tr>
</tbody>
</table>
</div>
<div>
<table class="infusion-field-container" style="width:100%;">
<tbody>
<tr>

<td class="infusion-field-input-container" style="width:200px;">
<input class="infusion-field-input-container" id="inf_field_Website" name="inf_field_Website" type="text"  />
</td>
</tr>
</tbody>
</table>
</div>
<div>
<table class="infusion-field-container" style="width:100%;">
<tbody>
<tr>

<td class="infusion-field-input-container" style="width:200px;">
<input class="infusion-field-input-container" id="inf_field_Phone1" name="inf_field_Phone1" type="text" />
</td>
</tr>
</tbody>
</table>
</div>
<div>
<table class="infusion-field-container" style="width:100%;">
<tbody>
<tr>

<td class="infusion-field-input-container" style="width:200px;">
<input class="infusion-field-input-container" id="inf_field_Email" name="inf_field_Email" type="text"  />
</td>
</tr>
</tbody>
</table>
</div>
<div>
<table class="infusion-field-container" style="width:100%;">
<tbody>
<tr>

<td class="infusion-field-input-container" style="width:200px;">
<input class="infusion-field-input-container" id="inf_field_Address3Street1" name="inf_field_Address3Street1" type="text"   />
</td>
</tr>
<tr>

<td class="infusion-field-input-container" style="width:200px;">
<input class="infusion-field-input-container" id="inf_field_City3" name="inf_field_City3" type="text"  />
</td>
</tr>
<tr>

<td class="infusion-field-input-container" style="width:200px;">
<input class="infusion-field-input-container" id="inf_field_State3" name="inf_field_State3" type="text"    />
</td>
</tr>
<tr>

<td class="infusion-field-input-container" style="width:200px;">
<input class="infusion-field-input-container" id="inf_field_PostalCode3" name="inf_field_PostalCode3" type="text"   />
</td>
</tr>
<tr>

<td class="infusion-field-input-container" style="width:200px;">
<input type="hidden" id="inf_field_Country3" name="inf_field_Country3"  />
</td>
</tr>
</tbody>
</table>
</div>
<div>
<table class="infusion-field-container" style="width:100%;">
<tbody>
<tr>

<td class="infusion-field-input-container" style="width:200px;">
<input class="infusion-field-input-container" id="inf_field_SourceType" name="inf_field_SourceType" type="text"  />
</td>
</tr>
</tbody>
</table>
</div>
<div>
<div style="height:15px; line-height:15px;">
&nbsp;
</div>
</div>
<div>
<div class="infusion-submit" style="text-align:center;">
<button style="" type="submit" value="Submit"  >Submit</button>

</div>
</div>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
</div>
</form>
		
		
<div>			 
			
                    <!-- FOOTER STARTS HERE -->
                        
						<%@ include file = "../../include/Footer.jsp" %>
                    <!-- FOOTER ENDS HERE -->
               
 </div>
 
 
 

</body>

</html>

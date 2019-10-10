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
   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>MemberUserRegistration</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<script src="js/basic.js" type="text/javascript" ></script>
<script src="js/cscombo_new.js" type="text/javascript" ></script>
<script src="js/frmMembRegi.js" type="text/javascript" ></script>
<script src="js/EditMemberUserReg.js" type="text/javascript" ></script>


<script src="js/bootstrap.min.js"></script>
<script src="js/tm-scripts.js"></script>
<script  src="js/ts_picker.js"></script>
<script src="js/calendar2.js" type="text/javascript"></script>
<script src="js/datetimepicker_css.js"></script>



<link rel="stylesheet" href="build/css/intlTelInput.css">
<style>
img.image1{

    right: 25px;
    position: absolute;
    top: 10px;
}
#frmMembRegi label.error{
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
   <%@ include file = "../../include/header_new.jsp" %>
      <!-- HEADER ENDS HERE -->
  </div>
  
<!--========================================================
CONTENT
=========================================================-->

<script src="js/jquery.validate.js"></script>
<script>
//------------------------------ User Registration Ajax Script -------------------------

var arHttpRequest;

function usrdetails()
{

if(document.frmMembRegi.usrname.value!="" && document.frmMembRegi.usrname.value.indexOf(" ")!=0)
{

    var email=document.frmMembRegi.usrname.value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
            
        var url = "./RiderInfoAjaxAction.do?userName="+email; 

        if (window.ActiveXObject) 
        { 
            arHttpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
           
        } 
        else if (window.XMLHttpRequest) 
        { 
            arHttpRequest = new XMLHttpRequest(); 
        } 
     
        arHttpRequest.open("POST", url, true); 
        
        arHttpRequest.onreadystatechange = function() {emailRequest(); } ; 
        arHttpRequest.send(null); 
   } 
}
   
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function emailRequest() 
    { 
   
        if (arHttpRequest.readyState == 4) 
        { 
            if(arHttpRequest.status == 200) 
            { 
                //get the XML send by the servlet 
                 var arnameXML = arHttpRequest.responseXML.getElementsByTagName("email")[0]; 
                 var arnameText = arnameXML.childNodes[0].nodeValue; 
                 
              //  alert(arnameText);
                if(arnameText!=null)
                {    
                alert("Login Name Exists.Choose Another !"); 
            	document.frmMembRegi.usrname.focus();
                return false;
                }    
            } 
            else 
            { 
                alert("Error loading page\n"+ arHttpRequest.status +":"+ arHttpRequest.statusText); 
            } 
        } 
    } 
var httpRequest;

function usrStat()
{

if(document.frmMembRegi.usrname.value!="" && document.frmMembRegi.usrname.value.indexOf(' ')!=0)
	{

   var chsUserName=document.frmMembRegi.usrname.value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
        url= "UsrSignupAjax.html?cmd=checkusrnam&chsUserName="+chsUserName; 

        if (window.ActiveXObject) 
        { 
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
        } 
        else if (window.XMLHttpRequest) 
        { 
            httpRequest = new XMLHttpRequest(); 
        } 
     
        httpRequest.open("GET", url, true); 
        
        httpRequest.onreadystatechange =processUser; 
        httpRequest.send(null); 
   } 
   }
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function processUser() 
    { 
   
        if (httpRequest.readyState == 4) 
        { 
            if(httpRequest.status == 200) 
            { 
                //get the XML send by the servlet 
                 var salutationXML = httpRequest.responseXML.getElementsByTagName("userstatus")[0]; 
                 var salutationText = salutationXML.childNodes[0].nodeValue; 
     
                //Update the HTML 
                updateHTML(salutationXML); 
            } 
            else 
            { 
                alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
            } 
        } 
    } 
        
   /** 
    * This function parses the XML and updates the  
    * HTML DOM by creating a new text node is not present 
    * or replacing the existing text node. 
    */ 
    function updateHTML(salutationXML) 
    { 
        //The node valuse will give actual data 
        var salutationText = salutationXML.childNodes[0].nodeValue; 

		if(salutationText != "false")
		{
			alert("User Name already Exists Choose Another !");
			document.frmMembRegi.usrname.value="";
			document.frmMembRegi.usrname.focus();
		}
		      
    } 

$(document).ready(function() {
		
		$("#frmMembRegi_fname").blur(function(){  
			var a = $("#frmMembRegi_fname").val();
			var b = a.trim();
			$("#frmMembRegi_fname").val(b);
		});
		$("#frmMembRegi_lname").blur(function(){  
			var a = $("#frmMembRegi_lname").val();
			var b = a.trim();
			$("#frmMembRegi_lname").val(b);
		});
		$("#date").blur(function(){  
			var a = $("#date").val();
			var b = a.trim();
			$("#date").val(b);
		});
		$("#frmMembRegi_usrname").blur(function(){  
			var a = $("#frmMembRegi_usrname").val();
			var b = a.trim();
			$("#frmMembRegi_usrname").val(b);
		});
		$("#frmMembRegi_email").blur(function(){  
			var a = $("#frmMembRegi_email").val();
			var b = a.trim();
			$("#frmMembRegi_email").val(b);
		});
		$("#frmMembRegi_ans").blur(function(){  
			var a = $("#frmMembRegi_ans").val();
			var b = a.trim();
			$("#frmMembRegi_ans").val(b);
		});
		$("#plotNo").blur(function(){  
			var a = $("#plotNo").val();
			var b = a.trim();
			$("#plotNo").val(b);
		});
		$("#buildNo").blur(function(){  
			var a = $("#buildNo").val();
			var b = a.trim();
			$("#buildNo").val(b);
		});
		$("#floorNo").blur(function(){  
			var a = $("#floorNo").val();
			var b = a.trim();
			$("#floorNo").val(b);
		});
		$("#street").blur(function(){  
			var a = $("#street").val();
			var b = a.trim();
			$("#street").val(b);
		});

		$("#lane").blur(function(){  
			var a = $("#lane").val();
			var b = a.trim();
			$("#lane").val(b);
		});
		$("#area").blur(function(){  
			var a = $("#area").val();
			var b = a.trim();
			$("#area").val(b);
		});
		$("#frmMembRegi_pcity_txt").blur(function(){  
			var a = $("#frmMembRegi_pcity_txt").val();
			var b = a.trim();
			$("#frmMembRegi_pcity_txt").val(b);
		});
		$("#frmMembRegi_pzip_txt").blur(function(){  
			var a = $("#frmMembRegi_pzip_txt").val();
			var b = a.trim();
			$("#frmMembRegi_pzip_txt").val(b);
		});
		$("#aphone_txt").blur(function(){  
			var a = $("#aphone_txt").val();
			var b = a.trim();
			$("#aphone_txt").val(b);
		});
		$("#pphone_txt").blur(function(){  
			var a = $("#pphone_txt").val();
			var b = a.trim();
			$("#pphone_txt").val(b);
		});
		$("#pafax_txt").blur(function(){  
			var a = $("#pafax_txt").val();
			var b = a.trim();
			$("#pafax_txt").val(b);
		});
		$("#pfax_txt").blur(function(){  
			var a = $("#pfax_txt").val();
			var b = a.trim();
			$("#pfax_txt").val(b);
		}); 
		$("#frmMembRegi_sadd_txt").blur(function(){  
			var a = $("#frmMembRegi_sadd_txt").val();
			var b = a.trim();
			$("#frmMembRegi_sadd_txt").val(b);
		});
		$("#sadd_txt1").blur(function(){  
			var a = $("#sadd_txt1").val();
			var b = a.trim();
			$("#sadd_txt1").val(b);
		});

		$("#sadd_txt2").blur(function(){  
			var a = $("#sadd_txt2").val();
			var b = a.trim();
			$("#sadd_txt2").val(b);
		});
		$("#sadd_txt3").blur(function(){  
			var a = $("#sadd_txt3").val();
			var b = a.trim();
			$("#sadd_txt3").val(b);
		});
		$("#scity_txt").blur(function(){  
			var a = $("#scity_txt").val();
			var b = a.trim();
			$("#scity_txt").val(b);
		});
		$("#frmMembRegi_szip_txt").blur(function(){  
			var a = $("#frmMembRegi_szip_txt").val();
			var b = a.trim();
			$("#frmMembRegi_szip_txt").val(b);
		});
		$("#frmMembRegi_saphone_txt").blur(function(){  
			var a = $("#frmMembRegi_saphone_txt").val();
			var b = a.trim();
			$("#frmMembRegi_saphone_txt").val(b);
		});
		$("#frmMembRegi_sphone_txt").blur(function(){  
			var a = $("#frmMembRegi_sphone_txt").val();
			var b = a.trim();
			$("#frmMembRegi_sphone_txt").val(b);
		});
		$("#frmMembRegi_safax_txt").blur(function(){  
			var a = $("#frmMembRegi_safax_txt").val();
			var b = a.trim();
			$("#frmMembRegi_safax_txt").val(b);
		});
		$("#frmMembRegi_sfax_txt").blur(function(){  
			var a = $("#frmMembRegi_sfax_txt").val();
			var b = a.trim();
			$("#frmMembRegi_sfax_txt").val(b);
		});
		
	
		
				});
</script>
		
<script type="text/javascript">

function isAlfa(evt) {
  evt = (evt) ? evt : window.event;
  var charCode = (evt.which) ? evt.which : evt.keyCode;
  if (charCode > 32 && (charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122)) {
    return false;
  }
  return true;
}

function isNumber(evt) {
		
	 	var charCode = (evt.which) ? evt.which : evt.keyCode;
          if (charCode != 46 && charCode > 31 
            && (charCode < 48 || charCode > 57))
             return false;

          return true;
	}

	$(document).ready(function() {
	//alert("working");
	
	
		
		/* special character and number validation*/

			$.validator.addMethod(
			"checkspecialcharacter",
			function(value, element) {
				// put your own logic here, this is just a (crappy) example
				return value.match(/^[a-zA-Z\s]+$/);
			},
			"Special characters are not accepted"
			);
			
			
			/* email validation*/
			$.validator.addMethod("emailvalidate", 
			function(value, element) {
				return /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
			}, 
			"Please Enter the valid Email"
		);
			/* mobile number validation*/
			$.validator.addMethod(
			"mobile",
			function(value, element) {
				// put your own logic here, this is just a (crappy) example
				return value.match(/^[0-9\-\(\)\s]+./);
			},
			"alphabets are not accepted"
			);
		 $("#frmMembRegi").validate({
			 
			 	ignore: ":disabled",		
			 			  
			rules: {
				fname: {
					required:true,
					checkspecialcharacter:true,
					maxlength:40
				},

				

				lname:
				 {
					required:true,
					checkspecialcharacter:true,
					maxlength:40
				 },
				dat:{					
			            required: true, 
			            date: true 			   				
				},
				
				usrname:
				{
					required:true
				},
				email:
				{
					required:true,
					emailvalidate:true
					
				},
				company:
				{
					required:true
				},
				QSelect:
				{
					required:true
				},
				ans:
				{
					required:true
				},
				
				padd_txt_strtAddr:
				{
					required:true
				},
				
				pcity_txt:
				{
					required:true
				},
				pcountry_sel:
				{
					required:true
				},
				pstate_sel:
				{
					
					required:true
				},
				pzip_txt:
				{
					required:true,
					mobile:true,
					maxlength:6
				},
				
				no_mob_txt:{
					required:true,
					mobile:true
				},
				
				
				
				
				
				credit_card_type:
				{
					required:true
				},
				credit_card_no:
				{
					required:true,
					mobile:true
				},
				cvv_no:
				{
					required:true,
					mobile:true
				},
				name_on_card:
				{
					required:true,
					checkspecialcharacter:true
				},
				expiry_month:
				{
					required:true,
					mobile:true
				},
				expiry_year:
				{
					required:true,
					mobile:true
				},
				accept:
				{
					required:true,
				}
				
				
				
				
				
		
		},
					
			messages: {
					fname:{
						required:"Enter your firstname",
						checkspecialcharacter:"Special Characters are not allowed"
					},
					
					lname:{
						required:"Enter your Lastname",
						checkspecialcharacter:"Special Characters are not allowed"
					},
					dat:" select DateOfBirth",	
					
					usrname: "Enter your username",
					email:
					{
						required:"Enter your E-mail ID",
						emailvalidate:"Enter valid E-mail Id"
					},
					company:
					{
						required:"Enter your Company Name"
					},
					QSelect:
					{
						required:"Select a Question"
					},
					ans:
					{
						
						required:"Please fill the answer"
					},
					
					padd_txt_strtAddr:
					{
						required:"Enter Street Address"
					},
					
					pcity_txt:
					{
						required:"Enter City Name"
					},
					pcountry_sel:
					{
						required:"Select country"
					},
					pstate_sel:
					{
						required:"Select State"
					},
					pzip_txt:
					{
						required:"Enter ZipCode",
						mobile:"only numbers are allowed"
					},
					no_mob_txt:
					{
						required:"Enter mobile number",
						mobile:"only numbers are allowed"
					},
					
					
					credit_card_type:
				{
					required:"Select Credit Card Type"
				},
				credit_card_no:
				{
					required:"Enter your credit card number",
					mobile:"Only numbers are allowed"
				},
				cvv_no:
				{
					required:"Enter your CVV Number",
					mobile:"Only numbers are allowed"
				},
				name_on_card:
				{
					required:"Enter your Name On Card",
					checkspecialcharacter:"Special Characters are not allowed"
				},
				expiry_month:
				{
					required:"Enter Expiry month On Card",
					mobile:"Only numbers are allowed"
				},
				expiry_year:
				{
					required:"Enter Expiry Year On Card",
					mobile:"Only numbers are allowed"
				},
				accept:
				{
					required:"Please read the terms and conditions"
				}
				
				
				
					
					},

					errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}
		});
	});
	
	function OnScrollDiv (div) {
		//alert("test");
			//alert("you must read condition fully");
            //info.innerHTML = "Horizontal: " + div.scrollLeft
                         //  + "px<br/>Vertical: " + div.scrollTop + "px";
				// document.getElementById("info").disabled= true;
				// document.getElementById("info").innerHTML="you must read condition fully";
				 // document.getElementById("info").innerHTML = "x";
       // document.getElementById("info").innerHTML = "scroll fully";
		//document.form.check.disabled="false";
		//alert(div.scrollTop);
		//var x = document.getElementById ("select").disabled = false ;
		//$("select").attr("disabled",false);
		//  alert(div.scrollTop);
		  if(div.scrollTop>=2000){
				//alert('hi');
			        var x = document.getElementById ("selectAccept").disabled = false ;
					//$("select").attr("disabled",false);
			}
		}
		
</script>
 <%String category = (String)request.getAttribute("category");%>
<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">
			
				<div class="col-lg-12 col-md-12 col-sm-12">

                   <h3 class="title">Sign Up for 30 Days Trial</h3>
				 
				 </div>
				 
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-3" id="commonBG">
						<p>Dear User, please take your time and register to avail the privileges that accompanies with it.</p>
					
				   </div>
				 </div>
<%String usrStat=(String)request.getAttribute("usrStat");
String purchase=(String)request.getAttribute("purchase");
String productPrice=(String)request.getAttribute("productPrice");
String productPlan = (String)request.getAttribute("productPlan");
String responseStatus=null;
responseStatus = (String)request.getAttribute("response_status");
System.out.println("purchase :::: "+purchase);
System.out.println("productPrice :::: "+productPrice);						
String coupon_status = null;
						String coupon_value = null;
						String coupon_description = null;
						String coupon_valid = null;
						String coupon_code = null;


						coupon_status = (String)request.getAttribute("coupon_status");
						coupon_value = (String)request.getAttribute("coupon_value");
						coupon_description = (String)request.getAttribute("coupon_description");
						coupon_valid = (String)request.getAttribute("coupon_valid");
						coupon_code = (String)request.getAttribute("coupon_code"); %>
	<%if(responseStatus != null && responseStatus.equalsIgnoreCase("fail")){ %>
		<div class="alert alert-danger">
				<span style="font-size:22px; align:center;">Registration failed. Try again with different user details</span> 
				
				</div> 
			<%} %>
        <form name="frmMembRegi" id="frmMembRegi" method="post" action="user.html?cmd=SignUpforFreeTrial" class="formcss" >
		 <input type="hidden" name="coupon_status" value="<%=coupon_status%>"/>
                        <input type="hidden" name="coupon_value" value="<%=coupon_value%>"/>
                        <input type="hidden" name="coupon_description" value="<%=coupon_description%>"/>
                        <input type="hidden" name="coupon_valid" value="<%=coupon_valid%>"/>
                        <input type="hidden" name="coupon_code" value="<%=coupon_code%>"/>
                        <input type="hidden" name="amount" id="amo" value="<%=productPrice%>">  
						<input type="hidden" name="productPrice" value="<%=productPrice %>">
						<input type="hidden" name="productPlan" value="<%=productPlan %>">						
		
						
		
		  				<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
                
							<div class="col-lg-12 col-md-12 col-sm-12">
								
								<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-3">
									<label class="name form-div-6 subtitle">
										Basic Information
									</label>
							   </div>
							 </div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
							 
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Salutation
								</label>
							</div>

							<div class="col-lg-3 col-md-3 col-sm-3">
							
							 <select name="USelect" id="select" class="form-control">
								<option value="" selected="selected">Select One</option>
								<option value="Mr">Mr.</option>
								<option value="Mrs">Mrs.</option>
								<option value="Miss">Miss.</option>
								<option value="Ms">Ms.</option>
								<option value="Ms">Dr.</option>
							  </select>
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
							
							  <input type="text" name="fname" id="frmMembRegi_fname" class="form-control" placeholder="First Name" />
								<font color="#FF0000"><span class="asterisk" id="fname"></span></font>
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Middle Name 
								</label>
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3">
							
								<input type="text" name="mname" class="form-control" onkeypress="return isAlfa(event)"   placeholder="Middle Name" />
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Last Name <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								
								<input type="text" name="lname" id="frmMembRegi_lname" class="form-control" placeholder="Last Name"/>
								 <font color="#FF0000"><span id="lname"></span></font>
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Suffix
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								
								<input type="text" name="sname" class="form-control" onkeypress="return isAlfa(event)"  placeholder="Suffix"/>
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Date of Birth <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
								<input name="dat" type="text" readonly="true" id="date" class="form-control"  placeholder="Date of Birth"/>
								
								<font color="#FF0000"><span id="dob"></span></font>					
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal">
								<img class="image1" src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('date')" />
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Gender 
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
								<input type="radio" size="10" name="gender" id="frmMembRegi_gender" value="male"/> Male
								<input type="radio" size="10" name="gender" id="frmMembRegi_gender" value="female"/> Female
								<font color="#FF0000"><span id="gender"></span></font>
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									User Name (Login ID) <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
								<input type="text" name="usrname" id="frmMembRegi_usrname" class="form-control" onblur="usrStat();" placeholder="User Name (Login ID)"/>
								<font color="#FF0000"><span id="usrname"></font>
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									EMail<span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								
								 <input type="text" name="email" id="frmMembRegi_email" class="form-control" placeholder="E-Mail"/>
								<font color="#FF0000"><span id="email"></span></font>
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
					  
          
                             <!-- <tr>
                <td height="28" valign="middle"><span class="label">Choose Your Password:</span></td>
                <td valign="middle"><span class="formX">
                  <input type="password" name="pwd"  id="frmMembRegi_pwd" class="textboxOne" />
                  &nbsp;  <font color="#FF0000"><span class="asterisk">*</span></font>&nbsp;(Abcdef&123)<span id="pwd" style="color:#FF0000;"></span></span></td>
              </tr>
              <tr>
                <td height="28" valign="middle"><span class="label">Re-Type Password</span>:</td>
                <td valign="middle"><span class="formX">
                  <input type="password" name="cpwd" id="frmMembRegi_cpwd" class="textboxOne"/>
                  &nbsp; <font color="#FF0000"><span class="asterisk">*</span><span id="cpwd"></span></font></span></td>
              </tr>
			   <tr>-->
			   
			   <div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Company Name <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input type="text" name="company" id="frmMembRegi_company" class="form-control">
							 
							  <font color="#FF0000"><span id="ncompany"></span></font>
							</div>
							
						</div>
					
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Customer Category <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							
							  <% if (category != null && !category.equalsIgnoreCase("null")){%>
								 <input type="text" name="category" class="form-control" value="<%=category%>" readonly/>
								<%}else{%> 
								<select name="category" class="form-control" >
								<option value="">Select one</option>
								<option value="Buyer">Buyer</option>
								<option value="Vendor">Vendor</option>
								</select>
								<%}%>
							  
							<font color="#FF0000"><span id="category"></span></font>
							</div>

						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
			   
			   			
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Choose A Secret Question <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							 <select name="QSelect" id="frmMembRegi_QSelect" class="form-control">
								<option selected="selected" value="">Select One</option>
								<option value="What is your favorite passtime?">What is your favorite passtime?</option>
								<option value="What is your pet's name?">What is your pet's name?</option>
								<option value="What was your first car?">What was your first car?</option>
								<option value="What is your mother's first name?">What is your mother's first name?</option>
								<option value="Which is your favorite vacation spot?">Which is your favorite vacation spot?</option>
							  </select>
						<font color="#FF0000"><span id="QSelect"></span></font>
							</div>
						
						</div>
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									your Answer To This Question <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="ans" id="frmMembRegi_ans" class="form-control" placeholder="Your Answer To This Question"/>
							  <font color="#FF0000"><span id="ans"></span></font>
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
			  
						
						
							<div class="col-lg-12 col-md-12 col-sm-12">
								
								<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-3">
								<label class="name form-div-6 subtitle">
									Primary Address
								</label>
							   </div>
							 </div>
							 
							  <div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
							 
						
						
							
			  <!----------------------------------- Start  debuged by nihal ------------------------------------------------->
			  
			  			<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Plot No. 
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								
								 <input type="text" name="padd_txt_plotNo" id="plotNo" class="form-control" placeholder="Plot No." />
									  <font color="#FF0000"><span id="plotno"></span></font>
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Building No.
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							 <input type="text" name="padd_txt_buildNo" id="buildNo" class="form-control" placeholder="Building No." />
							<font color="#FF0000"><span id="buildno"></span></font>
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Floor No.
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="padd_txt_floorNo" id="floorNo" class="form-control" placeholder="Floor No." />
								<font color="#FF0000"><span id="floorno"></span></font>
							</div>

						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
             
				
			  <!-----------------------------------END debug by nihal ------------------------------------------------->


<!--              <tr>
                <td height="27" valign="middle"><span class="label">Street Address: </span></td>
                <td valign="middle"><span class="row"><span class="formX">
		       <input type="text" name="padd_txt2" id="gstreet" value="" class="textboxOne" size="35" />
                </span></span></td>
              </tr>
-->

						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Street Address <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							<input type="text" name="padd_txt_strtAddr" id="street" value="" class="form-control" placeholder="Street Address"/>
							<font color="#FF0000"><span id="Street"></span></font>
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
	



<!----------------------------------------------Street  end  Nihal--------------------------------------------------------------------------->			  
<!--Lane edited by   Nihal	-------------------------------------------------------------------->
	<!--	  <tr>
                <td height="27" valign="middle"><span class="label">Lane: </span></td>
                <td valign="middle"><span class="row"><span class="formX">
                  <input type="text" name="padd_txt3" id="frmMembRegi_pAdd3_txt" value="" class="textboxOne" size="35" /><span id="pAdd3_txt"></span>
                </span></span></td>
              </tr>
			-->
			
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Lane 
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							 <input type="text" name="padd_txt_lane" id="lane" value="" class="form-control" placeholder="Lane" />
								<font color="#FF0000"><span id="Lane"></span></font>
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
			
<!-------------------------------------------------------------------------------------------------------------------------->			  
			<!--   <tr>
                <td height="27" valign="middle"><span class="label">Area: </span></td>
                <td valign="middle"><span class="row"><span class="formX">
                  <input type="text" name="padd_txt4" id="frmMembRegi_pAdd4_txt" value="" class="textboxOne" size="35" /><span id="pAdd4_txt"></span>
                </span></span></td>
              </tr-->
			  
			  		<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Area 
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							 <input type="text" name="padd_txt_area" id="area" value="" class="form-control" placeholder="Area"/>
							<font color="#FF0000"><span id="Area"></span></font>
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
              
		
		
<!-----------------------------------------------------------------------------------------area--->			  

						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									City <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" id="frmMembRegi_pcity_txt" name="pcity_txt" value="" class="form-control" placeholder="City"/>
								<font color="#FF0000"><span id="pcity_txt"></span></font>
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
							
							 <select name="pcountry_sel" id="frmMembRegi_pCountry_sel" class="form-control" onchange="FillState(document.frmMembRegi.pcountry_sel, document.frmMembRegi.pstate_sel, '');" >
								<option selected="selected">Select One</option>
								<option value="United States of America">United States of America</option>
							  </select>
							<font color="#FF0000"><span id="pCountry_sel"></span></font>
							</div>
							
						</div>
              
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									State <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							 <select name="pstate_sel" id="frmMembRegi_pstate_sel" class="form-control">
								<option selected="selected">Select One</option>
								<option value="FL">FL</option>
								<option value="VA">VA</option>
							  </select>
							<font color="#FF0000"><span id="pstate_sel"></span></font>
							</div>
						
						</div>
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									ZipCode <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="pzip_txt" id="frmMembRegi_pzip_txt" class="form-control" placeholder="Zipcode" />
								<font color="#FF0000"><span id="pzip_txt"></span></font>
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Phone 
								</label>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
								 <input type="text" name="country_phone_txt"  id="iphone_txt" class="form-control" style="width:90px;"/>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 ">
								 <input type="text" name="area_phone_txt"  id="aphone_txt" class="form-control" style="width:85px;" placeholder="Area Code"/>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1  ">
							  <input type="text" name="no_phone_txt"  id="pphone_txt" class="form-control" style="width:200px;" placeholder="Phone Number"/>
							
							 
							 </div>
							
							<font color="#FF0000"><span id="Iphone_txt"></span><span id="Aphone_txt"></span><span id="phone_txt"></span></font>
							
							
						</div>
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Mobile<span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
								 <input type="text" name="country_mob_txt"  id="pimob_txt" class="form-control" style="width:90px;"/>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 ">
								<input type="text" name="no_mob_txt"  id="pmob_txt" class="form-control" onkeypress="return isNumber(event)" style="width:250px;" placeholder="Mobile Number"/>
							</div>
							
							<span style="color:red" id="imob_txt"></span><span style="color:red" id="mob_txt"></span>

						</div>
						
					
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Fax 
								</label>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
								<input type="text" name="country_fax_txt"  id="pifax_txt" class="form-control" style="width:90px;"/>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 ">
								<input type="text" name="area_fax_txt"  id="pafax_txt" class="form-control"  style="width:85px;" placeholder="Area Code"/>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 ">
								<input type="text" name="no_fax_txt"  id="pfax_txt" class="form-control"  style="width:200px;" placeholder="Fax Number"/>
								<font color="#FF0000"><span id="ifax_txt"></span><span id="afax_txt"></span><span id="fax_txt"></span></font>
							</div>
							
						</div>
						
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
							Do you have secondary address ?
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							 <input type="radio" value="Primary" name="secAddrReqOrNot" size="10" checked="checked" onclick="showHideRadio('secAddrReqOrNot','sAdd','Secondary');" />
                    Yes
					
                    		<input type="radio" value="Secondary" name="secAddrReqOrNot" size="10"  onclick="showHideRadio('secAddrReqOrNot','sAdd','Secondary');" id="addr_sec"/>
                    No 
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div id="sAdd">
						
							<div class="col-lg-12 col-md-12 col-sm-12">
								
								<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-3">
								<label class="name form-div-6 subtitle">
									Secondary Address 
								</label>
							   </div>
							 </div>
							 
							  <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							 
							 
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Plot No / Building No.
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" value="" name="sadd_txt" id="frmMembRegi_sadd_txt" class="form-control"  placeholder="Plot No/Building No/Floor No" />
							  <font color="#FF0000"><span id="sadd_txt"></span></font>
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Street Address<span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" value="" name="sadd_txt_strt_addr" id="sadd_txt1" class="form-control"  placeholder="Street Address" />
							  <span id="sadd_txt"></span>
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Lane
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" value="" name="sadd_txt_laneNo" id="sadd_txt2" class="form-control"  placeholder="Lane No" />
							  <span id="sAdd3_txt"></span>
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Area
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							    <input type="text" value="" name="sadd_txt_area" id="sadd_txt3" class="form-control" placeholder="Area" />
								<span id="sAdd4_txt"></span>
							</div>
						
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									City <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" value="" name="scity_txt"  id="scity_txt" class="form-control" placeholder="City" />
							  <font color="#FF0000"><span id="Scity_txt"></span></font>
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
							
								   <select name="scountry_txt" id="frmMembRegi_sCountry_sel" class="form-control" onchange="FillState(document.frmMembRegi.scountry_txt, document.frmMembRegi.sstate_txt,'');" >
                        </select>
							 <font color="#FF0000"><span id="sCountry_sel"></span></font>
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									State <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
								 <select name="sstate_txt" id="frmMembRegi_sstate_sel" class="form-control">
									 <option selected="selected">Select One</option>
									<option value="FL">FL</option>
									<option value="VA">VA</option>
								</select>
							<font color="#FF0000"><span id="sstate_sel"></span></font>
							</div>
							
						</div>
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									ZipCode<span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							 <input type="text" value="" name="szip_txt"  id="frmMembRegi_szip_txt" class="form-control"  placeholder="Zipcode" />
							 <font color="#FF0000"><span id="szip_txt"></span></font>
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Phone 
								</label>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
								<input type="text" name="s_country_phone_txt"  id="frmMembRegi_siphone_txt" class="form-control"  style="width:90px;"/>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 ">
								<input type="text" name="s_area_phone_txt"  id="frmMembRegi_saphone_txt" class="form-control"  style="width:85px;" placeholder="Area Code"/>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 ">
								<input type="text" name="s_no_phone_txt"  id="frmMembRegi_sphone_txt" class="form-control"  style="width:200px;" placeholder="Phone Number"/>
							
							</div>
							<font color="#FF0000"><span id="s_Iphone_txt"></span><span id="s_Aphone_txt"></span><span id="s_phone_txt"></span></font>
						</div>
						
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Mobile <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 ">
								<input type="text" name="s_country_mob_txt"  id="frmMembRegi_simob_txt" class="form-control"  style="width:90px;" />
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 ">
							
                   				<input type="text" name="s_no_mob_txt"  id="frmMembRegi_smob_txt" class="form-control" onkeypress="return isNumber(event)"  style="width:250px;" placeholder="Mobile Number"/>
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Fax 
								</label>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 ">
								<input type="text" name="s_country_fax_txt"  id="frmMembRegi_sifax_txt" class="form-control" style="width:90px;"/>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 ">
								<input type="text" name="s_area_fax_txt"  id="frmMembRegi_safax_txt" class="form-control"  style="width:85px;" placeholder="Area Code"/>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1  ">
							
							   	<input type="text" name="s_no_fax_txt"  id="frmMembRegi_sfax_txt" class="form-control" style="width:200px;" placeholder="Fax Number"/>
							</div>
							
						</div>
					</div>
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Preferred Communication Address:
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
								<select name="PreferredAddrType" id="comAdd_sel" class="form-control">
								  <option selected="selected" value="Primary">Primary Address</option>
								  <option value="Secondary">Secondary Address</option>
								</select>
							</div>
						
						</div>
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
					<%
                               if(purchase.equalsIgnoreCase("try_now") && purchase != null && coupon_code == null)
                               
                               {
                               
                       %>	
								<div class="col-lg-12 col-md-12 col-sm-12">
								
								<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-3">
								<label class="name form-div-6">
									<font color="#018dce">Credit Card Details</font>
								</label>
							   </div>
							 </div>
							 
							  <div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
								</div>
								
								<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Card Type <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							
								<label class="phone form-div-6" align="left">
                             <input type="radio" size="10" name="credit_card_type" value="visa" />
                             <img src="images/visa.png" id="visa" name="visa" height="35px" width="45px"/>
                             <input type="radio" size="10" name="credit_card_type" value="mastercard" />
                             <img src="images/master.png" id="master" name="master" height="35px" width="45px"/>
                             <input type="radio" size="10" name="credit_card_type" value="amex" />
                             <img src="images/american.png" id="american" name="american" height="35px" width="45px"/>
                              
                            </label>
							</div>
						
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
								</div>
								
							<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Credit Card Number <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
								<input type="text" name="credit_card_no" id="credit_card_no" placeholder="Credit Card No:" value="" class="form-control" data-constraints="@Required @JustNumbers" />
							</div>
						
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
								</div>
								
							
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
								</div>
							
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									CVV Number <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
								<input type="password" name="cvv_no" id="cvv_no" placeholder="CVV no:" value="" class="form-control" data-constraints="@Required @JustNumbers" maxlength="3"/>
							</div>
						
						</div>	
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
								</div>
								
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Name on Card <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
								<input type="text" name="name_on_card" id="name_on_card" placeholder="Name on Card:" class="form-control" value="" data-constraints="@Required @JustLetters"  />
							</div>
						
						</div>	
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
								</div>
								
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Expiry Month <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
								 <input type="text" name="expiry_month" id="expiry_month" placeholder="MM" value="" class="form-control" data-constraints="@Required @JustNumbers" maxlength="2" size="2"/>
							</div>
						
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
								</div>
								
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Expiry Year <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
								 <input type="text" name="expiry_year" id="expiry_year" placeholder="YYYY" value="" class="form-control" data-constraints="@Required @JustNumbers" maxlength="4" size="2"/>
							</div>
						
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
								</div>
							<%} %>
							<div class="col-lg-12 col-md-12 col-sm-12">
								
								<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-3">
								<label class="name form-div-6">
									<font color="#018dce">Contact Options</font>
								</label>
							   </div>
							 </div>
							 
							  <div class="col-lg-12 col-md-12 col-sm-12">
								
								<div class="col-lg-8 col-md-8 col-sm-8 col-sm-offset-3">
									We provide Mail/E-mail information to other affiliates, organizations and vendors on a limited basis. You may choose to not receive these mailings by selecting the appropriate options below:
							   </div>
							 </div>
							 
							  <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							 
							 <div class="col-lg-12 col-md-12 col-sm-12">
								
								<div class="col-lg-8 col-md-8 col-sm-8 col-sm-offset-3">
								
									   <input type="checkbox" name="nonUseaEmail"  id="nonUseaEmail_id" value="true"/>
											Do not release my email address for specific use.
								</div>
							 </div>
							 
							  <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							 
							  <div class="col-lg-12 col-md-12 col-sm-12">
								<div class="col-lg-8 col-md-8 col-sm-8 col-sm-offset-3">
									<input type="checkbox" name="nonUseaMail"  id="nonUseaAdd_id" value="true"/>
										Do not release my mailing address for specific use.
								
							   </div>
							 </div>
							 
							  <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-8 col-md-8 col-sm-8 col-sm-offset-2">
						<label class="phone form-div-6" align="left">
							(*) Please Read the Terms & Conditions by Scrolling the way down
                            </label>
							<label class="phone form-div-6">
							<div style="height:250px; overflow-x:scroll;overflow-y:scroll; font-size:12px;border-style:groove;border-width:1px" align="justify" onscroll="OnScrollDiv (this)">
							<center><strong> RETURN, REFUND AND EXCHANGE POLICY</strong></center>

							&nbsp;&nbsp;Please read the following policies carefully before using any of digiBlitz's products or platform(s). The return policy is provided in addition to any legal return rights that you may have under law. digiBlitz Inc. will accept returns, refunds and exchanges within 15 days from the day of purchasing the product(s) or platform(s). digiBlitz Inc has rights to refuse any return or exchange if it fails to meet the norms.<br/>
							<strong>1.	TERMS OF USE AND RESALE</strong><br/>

							&nbsp;&nbsp;THOUGH DIGIBLITZ INC. ALLOWS YOU TO ACCESS VARIOUS RESOURCES THROUGH ITS ONLINE (CLOUD) OR ENTERPRISE PRODUCT(S) & PLATFORM(S), WEBSITE, DB STORE, AND OTHER SELLABLE GOODS THAT MAY OR MAY NOT BE AVAILABLE FOR PURCHASE, OR DOWNLOAD, INFORMATION OF SOFTWARE, INCLUDING THE  SOFTWARE AND TOOLS (COLLECTIVELY "SERVICES") ARE SUBJECT TO THESE TERMS OF USE AND SALE ("TERMS OF USE AND RESALE" OR "CONTRACT").</br>
							&nbsp;&nbsp;By using digiBlitz's and its Product's Website, Purchasing/ downloading products and services from the digiBlitz's website,  its Products'  website, dB store or through any authorized reseller, you accept and agree to TERMS OF USE AND RESALE, DIGIBLITZ PRIVACY STATEMENT, and APPLICABLE TERMS AND CONDITIONS, policies or disclaimers found in the digiBlitz Inc. or referenced herein and accessible through the related hyperlinks. We allow you to read the digiBlitz Inc. Policies carefully. <br/>
							<strong><center>2.	TERMS RELATING TO YOUR USE OF THE DB STORE</center></strong><br/>


							<strong>&nbsp;2.1.	PERSONAL AND COMMERCIAL USE LIMITATION</strong><br/>
							&nbsp;&nbsp;Unless otherwise specified, the Products, Components Of dB store and Services are only for your personal and commercial use. You may not profitably distribute, circulate, license, or trade any information or services obtained from the Websites, Products, components of dB store and their respective Services.<br/>
							<strong>&nbsp;2.2.	NOTICE SPECIFIC TO DOCUMENTS AVAILABLE ON THE EBSITE OR SERVICES</strong><br/>
							&nbsp;&nbsp;Permission to use Documents and information (such as white papers, press releases, datasheets, Product literatures, Manuals of any kind and FAQs) from the Websites, Products, components of dB store and their respective Services is allowed for your personal and commercial use.<br/>
							&nbsp;&nbsp;Use of such records from digiBlitz Inc. or Services is for informational and profitably or personal use only and will not be copied or posted on any network computer or make public in any media, unless permitted by freedom from a responsibility under applicable copyright
							laws. You could however with prior permission from digiBlitz Inc., could use the information provided, the following Copyright notice: "© 2016 digiBlitz Inc., All rights reserved." appears in all copies and that both the copyright notice and this permission notice appear.<br/>
							&nbsp;&nbsp;Website: Any digiBlitz Inc. owned, operated, licensed or controlled website. Elements of digiBlitz Inc. websites, including the Website, are protected by trademark, unfair competition, and other laws and may not be copied or imitated in whole or in part. No logo, graphic, sound or image from any digiBlitz Inc. website, including the Website, may be copied, retransmitted or made available unless expressly permitted by digiBlitz Inc. or applicable law.<br/>
							&nbsp;&nbsp;The documents and related graphics published on any digiBlitz Inc. owned, operated, licensed or controlled website and dB Store or Services could include technical inaccuracies or typographical errors. Changes may be periodically added to the information without prior notice. digiBlitz Inc. and/or its respective suppliers may make improvements and/or changes in the product(s) and/or the platform(s) and/or dB store described herein at any time.<br/>
							<strong>2.3.	MEMBER ACCOUNT, PASSWORD, AND SECURITY</strong><br/>
							&nbsp;&nbsp;If you want to open any account in any dB products, information should be complete, accurate, and current as required by the applicable registration form. Username and password may or may not be generated by digiBlitz Inc. depending upon the case. However, you are responsible for keeping your account information and password confidential and be responsible for all activity that occurs under your account. You agree to notify digiBlitz Inc. without delay of any unauthorized use of your account or any other breach of security.<br/>
							<strong>2.4.	NO UNLAWFUL OR PROHIBITED USE</strong><br/>
							&nbsp;&nbsp;As a condition of your use of the Services, you pledge to us that you will not use the Services for any purpose that is against the law or disallowed by these terms, conditions, & notices. You may not use our product(s), platform(s) or component(s) of dB store which would cause damage, disable or crash any server, or the network(s) connected to any digiBlitz’s server, or interfere with any other party's use and enjoyment of any Services. You may not try to gain unauthorized access to any Services, computer systems or networks connected to any digiBlitz’s Server or to any of the Server. You may not use the services in a way that violate rights of third parties, carelessly harming a person or entity, including digiBlitz. digiBlitz has right to make any information public.<br/>
							<strong>2.5.	MATERIALS PROVIDED TO digiBlitz Inc. PRODUCTS OR POST ON THE WEBSITE BY YOU</strong><br/>
							&nbsp;&nbsp;digiBlitz Inc does not claim ownership of the materials you provide to digiBlitz Inc. (including feedback, ratings, and suggestions) or post, upload, input or submit to any Product or its connected  services for review by the users, or by the members of any public or private area of people (each a "Submission" and collectively "Submissions").<br/>&nbsp;&nbsp;However, digiBlitz Inc. shall be permitted to use your Submission, including your name, for the purpose for which it was submitted. No compensation will be paid for you Submission. digiBlitz Inc.  has rights to remove any Submission at any time in its own discretion.<br/>
							<strong>2.6.	THIRD-PARTY LINKS IN OUR WEBSITE</strong><br/>
							&nbsp;&nbsp;The dB store or any product(s) or platform(s) may include links to third-party websites that allow you leave the digiBlitz Inc. Website. 
							The digiBlitz Inc is not responsible for any internal link or contents of third party website. These linked sites are not under the control of digiBlitz Inc. digiBlitz Inc. is providing these links only for your convenience. Your use of the third-party website may be subject to that third party’s terms and conditions.<br/>
							<strong>3.	Terms Relating to the Resale of Products to You</strong><br/>

							&nbsp;<strong>3.1.	USERS</strong><br/>
							&nbsp;&nbsp;You must not be a reseller. Only the end users are allowed to buy our product.<br/>
							&nbsp;<strong>3.2.	INFORMATION ABOUT BILLING AND ACCOUNT INFORMATION</strong><br/>
							&nbsp;&nbsp;The information provided to digiBlitz Inc. by you for all purchases should be accurate current and complete and .You agree to update your account and other information immediately, including your email address and credit card numbers and expiration dates, so that we can complete your transactions and contact you as needed.<br/>
							&nbsp;<strong>3.3.	PRODUCT AVAILABILITY AND QUANTITY AND ORDER LIMITS</strong><br/>
							&nbsp;&nbsp;Pricing page of our product can be changed at any time without notice. digiBlitz Inc. may limit on the number that may be purchased per account, per person, per order,  per credit card, or per CPU and core. We have rights to refuse or reject any order at any time, refunding you any money you have paid for the order, for reasons which include, but are not limited to, you have follow the conditions specified at the time of the order, otherwise your payment cannot be processed. In case if we are unable to supply our product, we will contact you and we will make an alternative. If you do not choose to purchase the alternative product, we will cancel your order. If the product has some error, we reserve the right to correct the error and charge you the correct price. In that case will offer you the option of purchasing the product and also for cancelling the order. Credits or refunds will be made to the same method of payment and account used to place the order.<br/>
							<strong>3.4.	SOFTWARE PURCHASES AND LICENSE TERMS</strong><br/>
							&nbsp;&nbsp;Any software made available to download or purchase from the website or dB store is the copyrighted work of digiBlitz Inc. When you purchase software, you are actually purchasing a license to use the software rather than purchasing the software itself. Software licenses purchased form any of digiBlitz’s owned or controlled website or from an authorized reseller are subject to the license agreement that accompanies the software (the "License Agreement"). You will be required to agree to the terms and conditions of the License Agreement when you install the software.<br/>
							&nbsp;&nbsp;Any reproduction or redistribution of software or merchandise not in accordance with the relevant License Agreement and applicable law is expressly prohibited and may result in severe civil and criminal penalties. Violators risk being prosecuted to the maximum extent possible.<br/>
							<strong>3.5.	Refund</strong><br/>
							&nbsp;&nbsp;Refunds will be made in the same method as above. For refund the intimation should be given to digiBlitz Inc.  by customer and it will be processed with 15 days.<br/>

							<center><strong>Terms and Condition</strong></center>

							<strong>1.	Payment and pricing:</strong><br/>
							&nbsp;&nbsp;Pricing of all products are stated in digiBlitz's relevant product or platform websites. Pricing page of our product(s) and platform(s) can be changed at any time without notice. digiBlitz Inc. may limit on the number that may be purchased per order, per account, per credit card, per person or per CPU. Pricing shown in the website will include all tax and charges of the product.<br/>
							<strong>2.	AUTO RENEWAL OF THE PRODUCTS</strong><br/>
							&nbsp;&nbsp;All products of digiBlitz Inc.  will be renewed automatically at the end of each month on a prorate basis or depending up on the date of purchase (depending upon individual cases). We will also inform you about the renewal through email before the subscription of the product. Once we informed you about the auto renewal, we will charge automatically for the product. The intimation will be given before one week from the renewal to digiBlitz Inc. by the customer .We will provide you with instruction on cancelling the product service or for suspending the product. However, no intimation will be given to you for subscription renewal of any dB store components. You must cancel the services before the renewal date to avoid being billed for the renewal.<br/>
							<strong>3.	Return, Refund and Exchange</strong><br/>
							&nbsp;&nbsp;The return policy is provided in addition to any legal return rights that you may have under law. digiBlitz Inc. will accept returns, refunds and exchanges within 30 day after purchasing of product. We have rights to refuse any refund, return or exchange if it fails to meet our norm. For Return, Refund and Exchange the intimation should be given before one week from the renewal to digiBlitz Inc. by the customer and it will be processed within 30 days. We may occasionally extend the 15-day return period during holiday or other periods. If a longer return period was advertised through a promotional campaign on the Website when you made your purchase, that applies within the clause mentioned in the promotional campaign.<br/>
							<strong>4.	Trial-period</strong><br/> 
							&nbsp;&nbsp;If you are taking part in any trial-period, you must cancel the service before the end of the trial period to avoid incurring new charges unless we notify you otherwise. Your trial subscription will be automatically upgraded to basic pack at the end of this free subscription period. This implies that your credit card will be charged for the basic pack. However, you could unsubscribe the product any time before the trial expiry through the Master account management link that is accessible when you login into the product.<br/>

							<strong>5.	Customer support</strong><br/>
							&nbsp;&nbsp;Please visit our Knowledge base site for Customer support and Assistance relating to the product.<br/>
							<strong>6.	CHANGING TERMS</strong><br/>
							&nbsp;&nbsp;digiBlitz Inc. may change the Terms of Return, Refund and Exchange at any time and without notice to you. The Terms of Return, Refund and Exchange in force at the time you place your order will govern your purchase and serve as the purchase contract between us. Before your next purchase, digiBlitz Inc. may change their policies. We advise you to visit the digiBlitz Inc. website, to review the current terms and condition, Refund and Return policy each time. <br/>
							<strong>7.	PROTECTION OF INDIVIDUAL INFORMATION</strong><br/>
							&nbsp;&nbsp;Your privacy is important to us. We may use certain information for the purpose of your product according to your need. <br/>
							<strong>8.	LIMITATION OF LIABILITY</strong><br/>
							&nbsp;&nbsp;In this section of Contract, digiBlitz Inc. is found liable to you for any loss or damage that arises out of or is in any way connected with your use of the dB Store, the Services, or any product or service offered, you agree that your exclusive remedy is to recover from digiBlitz Inc.  or any affiliates, and vendors direct damages up to (1) an amount equal to the price or fee for one month of any service or subscription or (2) US $100 if there was no service, subscription or similar fee.<br/>
							&nbsp;&nbsp;YOU AGREE THAT YOU CAN'T RECOVER ANY OTHER DAMAGES OR LOSSES, INCLUDING, WITHOUT LIMITATION, CONSEQUENTIAL, LOST PROFITS, SPECIAL, INDIRECT, INCIDENTAL, OR CONCERNING PUNISHMENT. THESE LIMITATIONS AND EXCLUSIONS APPLY EVEN IF YOU INCUR DAMAGES AND EVEN IF WE KNEW OR SHOULD HAVE KNOWN ABOUT THE POSSIBILITY OF THE DAMAGES. THESE LIMITATIONS AND EXCLUSIONS APPLY TO ANYTHING RELATED TO THE WEBSITE OWNED AND CONTROLLED BY DIGIBLITZ INC., ITS PRODUCT (S), PLATFORM(S) OR ANY COMPONENTS OF DB STORE, OR SERVICE OFFERED<br/>

							<strong>9.	Interpreting the Contract</strong><br/>
							&nbsp;&nbsp;All parts of this Contract apply to the maximum extent permitted by the relevant law of the United States.<br/>

							</div>
                            </label>
							<label class="name form-div-6" align="left">
							<span id="nselect" style="color: red;"></span>
                             
                              <span class="nagree"></span>
                            </label>
							<label class="name form-div-6" align="left">
                              <input type="checkbox" name="accept"  id="selectAccept"/>I agree to the user License agreement and privacy policy
                                <input type="hidden" name="accept"> 
                              <span class="nagree"></span>
                            </label>
							<label class="name form-div-3">
                              <span id="Nagree" style="color: red;"></span>
                            </label>
						</div>
					</div>	
					 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-5">
							 <button type="submit" class="button-add" name="Submit" value="Register">Register</button>
							
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
    
   <%@ include file = "../../include/Footer.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>

 <script language="javascript">
	FillCountry(document.frmMembRegi.pcountry_sel, document.frmMembRegi.pstate_sel, 'USA' );
	FillState(document.frmMembRegi.pcountry_sel, document.frmMembRegi.pstate_sel, '');

	FillCountry(document.frmMembRegi.scountry_txt, document.frmMembRegi.sstate_txt, 'USA' );
	FillState(document.frmMembRegi.scountry_txt, document.frmMembRegi.sstate_txt, '');

</script>
<!-- Load jQuery from CDN so can run demo immediately -->
<!--script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script-->
<script src="build/js/intlTelInput.js"></script>

<script>
  $("#iphone_txt").intlTelInput({
    //allowExtensions: true,
    //autoFormat: false,
    autoHideDialCode: false,
    //autoPlaceholder: false,
    //defaultCountry: "auto",
    //ipinfoToken: "yolo",
    nationalMode: false,
    //numberType: "MOBILE",
    //onlyCountries: ['us', 'gb', 'ch', 'ca', 'do'],
    //preferredCountries: ['cn', 'jp'],
    //preventInvalidNumbers: true,
   // utilsScript: "build/utils.js"
  });
  $("#pimob_txt").intlTelInput({
    //allowExtensions: true,
    //autoFormat: false,
    autoHideDialCode: false,
    //autoPlaceholder: false,
    //defaultCountry: "auto",
    //ipinfoToken: "yolo",
    nationalMode: false,
    //numberType: "MOBILE",
    //onlyCountries: ['us', 'gb', 'ch', 'ca', 'do'],
    //preferredCountries: ['cn', 'jp'],
    //preventInvalidNumbers: true,
   // utilsScript: "build/utils.js"
  });
   $("#pifax_txt").intlTelInput({
    //allowExtensions: true,
    //autoFormat: false,
    autoHideDialCode: false,
    //autoPlaceholder: false,
    //defaultCountry: "auto",
    //ipinfoToken: "yolo",
    nationalMode: false,
    //numberType: "MOBILE",
    //onlyCountries: ['us', 'gb', 'ch', 'ca', 'do'],
    //preferredCountries: ['cn', 'jp'],
    //preventInvalidNumbers: true,
   // utilsScript: "build/utils.js"
  });
  $("#frmMembRegi_siphone_txt").intlTelInput({
    //allowExtensions: true,
    //autoFormat: false,
    autoHideDialCode: false,
    //autoPlaceholder: false,
    //defaultCountry: "auto",
    //ipinfoToken: "yolo",
    nationalMode: false,
    //numberType: "MOBILE",
    //onlyCountries: ['us', 'gb', 'ch', 'ca', 'do'],
    //preferredCountries: ['cn', 'jp'],
    //preventInvalidNumbers: true,
   // utilsScript: "build/utils.js"
  });
  $("#frmMembRegi_simob_txt").intlTelInput({
    //allowExtensions: true,
    //autoFormat: false,
    autoHideDialCode: false,
    //autoPlaceholder: false,
    //defaultCountry: "auto",
    //ipinfoToken: "yolo",
    nationalMode: false,
    //numberType: "MOBILE",
    //onlyCountries: ['us', 'gb', 'ch', 'ca', 'do'],
    //preferredCountries: ['cn', 'jp'],
    //preventInvalidNumbers: true,
   // utilsScript: "build/utils.js"
  });
  $("#frmMembRegi_sifax_txt").intlTelInput({
    //allowExtensions: true,
    //autoFormat: false,
    autoHideDialCode: false,
    //autoPlaceholder: false,
    //defaultCountry: "auto",
    //ipinfoToken: "yolo",
    nationalMode: false,
    //numberType: "MOBILE",
    //onlyCountries: ['us', 'gb', 'ch', 'ca', 'do'],
    //preferredCountries: ['cn', 'jp'],
    //preventInvalidNumbers: true,
   // utilsScript: "build/utils.js"
  });
</script>
</body>
</html>


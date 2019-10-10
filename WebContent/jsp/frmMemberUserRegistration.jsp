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
		
		$("#street").blur(function(){  
			var a = $("#street").val();
			var b = a.trim();
			$("#street").val(b);
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
				gender:
				{
					required:true
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
				padd_txt_plotNo:
				{
					required:false
				},
				
				
				padd_txt_strtAddr:
				{
					required:false
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
				
				no_phone_txt:
				{
					required:false,
					mobile:false,
					maxlength:15
				},
				area_fax_txt:
				{
					required:false,
					mobile:false
				},
				no_fax_txt:
				{
					required:false,
					mobile:false,
					maxlength:15
				},
				sadd_txt:
				{
					required:true
				},
				sadd_txt_strt_addr:
				{
					required:false
				},
				
				
				scity_txt:
				{
					required:true
				},
				scountry_txt:
				{
					required:true
				},
				sstate_txt:
				{
					
					required:true
				},
				szip_txt:
				{
					required:true,
					mobile:true,
					maxlength:6
				},
				s_area_phone_txt:
				{
					required:false,
					mobile:false
				},
				s_no_phone_txt:
				{
					required:false,
					mobile:false,
					maxlength:15
				},
				s_area_fax_txt:
				{
					required:false,
					mobile:false
				},
				s_no_fax_txt:
				{
					required:false,
					mobile:false,
					maxlength:15
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
					gender:"select gender",
					usrname: "Enter your username",
					email:
					{
						required:"Enter your E-mail ID",
						emailvalidate:"Enter valid E-mail Id"
					},
					company:
					{
						required:"Select a Company"
					},
					QSelect:
					{
						required:"Select a Question"
					},
					ans:
					{
						
						required:"Please fill the answer"
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
								
					
					
					sadd_txt_strt_addr:
					{
						required:"Enter Street Address"
					},
					
					
					scity_txt:
					{
						required:"Enter City Name"
					},
					scountry_sel:
					{
						required:"Select country"
					},
					sstate_sel:
					{
						required:"Select State"
					},
					szip_txt:
					{
					required:"Enter Zipcode",
					mobile:"only numbers are allowed"
					},
					s_area_phone_txt:
					{
						required:"Enter Area Code",
					mobile:"Only numbers are allowed"
					
					},
					s_no_phone_txt:
					{
						required:"Enter Phone Number",
						mobile:"Only numbers are allowed"
					},
					s_area_fax_txt:
					{
					required:"Enter Area code",
					mobile:"Only numbers are allowed"
					},
					s_no_fax_txt:
					{
						required:"Enter Fax number",
						mobile:"Only number are allowed"
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
</script>
 
<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">
			
				<div class="col-lg-12 col-md-12 col-sm-12">

                   <h3 class="title">User Registration</h3>
				 
				 </div>
				 
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-3" id="commonBG">
						<p>Dear User, please take your time and register to avail the privileges that accompanies with it.</p>
					
				   </div>
				 </div>

	
		  <%String formId= (String)request.getAttribute("id");
                  if(formId.equalsIgnoreCase("jobseeker")){System.out.println("inside jobseeker::::::::::::::::::::::::::::::");%>
        <form name="frmMembRegi" id="frmMembRegi" method="post" action="userRole.html?cmd=usrRoleReg" class="formcss" enctype="multipart/form-data">
		<%}else{System.out.println("inside Vendor or MSP::::::::::::::::::::::::::::::");%>
		<form name="frmMembRegi" id="frmMembRegi" method="post" action="user.html?cmd=usrReg" class="formcss" enctype="multipart/form-data">
		<%}%>
		
		
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
							
							  <select name="company" id="frmMembRegi_company" class="form-control">
								<option selected="selected" value="">Select One</option>
								 <%
									ArrayList headcompanyList = (ArrayList)request.getAttribute("companyList");
									if(headcompanyList!=null && headcompanyList.size()!=0){
										Iterator itcompanyList = headcompanyList.iterator();
										int i=1;
										while(itcompanyList.hasNext()){
										
											String strcompanyList[]= (String[])itcompanyList.next();
											String companyList = strcompanyList[0];
											System.out.println("companyList :::::::::::::::::::::"+companyList);
																	
										
										%>
								<option value="<%=companyList%>"><%=companyList%></option>
								
								  <%}
									}%>
							  </select>
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
							
							 <%String id= (String)request.getAttribute("id");
							  if(id.equalsIgnoreCase("vendor")){%>
							  <input type="text" name="category" class="form-control" value="vendor" readonly/>
							  <%}else if(id.equalsIgnoreCase("msp")){ %>
							  <input type="text" name="category" class="form-control"  value="Buyer" readonly/>
							  <%}else if(id.equalsIgnoreCase("jobseeker")){ %>
							  <input type="text" name="category" class="form-control" value="Workseeker" readonly/>
							  <%} %>
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
							&nbsp;
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
									Street Address 
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
			
			
<!-------------------------------------------------------------------------------------------------------------------------->			  
			<!--   <tr>
                <td height="27" valign="middle"><span class="label">Area: </span></td>
                <td valign="middle"><span class="row"><span class="formX">
                  <input type="text" name="padd_txt4" id="frmMembRegi_pAdd4_txt" value="" class="textboxOne" size="35" /><span id="pAdd4_txt"></span>
                </span></span></td>
              </tr-->
			  
			  		
              
		
		
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
									Mobile
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
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Street Address
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
							&nbsp;
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
									Mobile
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


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
   <%@ include file = "../../include/header_new.jsp" %>
      <!-- HEADER ENDS HERE -->
  </div>
  
<!--========================================================
CONTENT
=========================================================-->

<script src="js/jquery.validate.js"></script>

<script type="text/javascript">
   

//-------------------------------- User status validation Ajax Script ------------------------------------------------

var httpRequest;

function usrStat()
{

if(document.BecomePartner.email.value!="" && document.BecomePartner.email.value.indexOf(' ')!=0)
 {

   var chsEmail=document.BecomePartner.email.value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
        url= "CheckEmailExist.html?cmd=checkEmail&email="+chsEmail; 

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
   alert("Email already Exists Choose Another !");
   document.BecomePartner.email.value="";
   document.BecomePartner.email.focus();
  }
        
    } 

    
 </script>
<script>



$(document).ready(function(){

	$("#firstname").blur(function(){  
			var a = $("#firstname").val();
			var b = a.trim();
			$("#firstname").val(b);
		});
		$("#lastname").blur(function(){  
			var a = $("#lastname").val();
			var b = a.trim();
			$("#lastname").val(b);
		});
		$("#email").blur(function(){  
			var a = $("#email").val();
			var b = a.trim();
			$("#email").val(b);
		});
		$("#company").blur(function(){  
			var a = $("#company").val();
			var b = a.trim();
			$("#company").val(b);
		});
		$("#Designation").blur(function(){  
			var a = $("#Designation").val();
			var b = a.trim();
			$("#Designation").val(b);
		});
		$("#AddressLine1").blur(function(){  
			var a = $("#AddressLine1").val();
			var b = a.trim();
			$("#AddressLine1").val(b);
		});
		$("#AddressLine2").blur(function(){  
			var a = $("#AddressLine2").val();
			var b = a.trim();
			$("#AddressLine2").val(b);
		});
		$("#City").blur(function(){  
			var a = $("#City").val();
			var b = a.trim();
			$("#City").val(b);
		});
		$("#State").blur(function(){  
			var a = $("#State").val();
			var b = a.trim();
			$("#State").val(b);
		});
		$("#Zipcode").blur(function(){  
			var a = $("#Zipcode").val();
			var b = a.trim();
			$("#Zipcode").val(b);
		});
		$("#PhoneNumber").blur(function(){  
			var a = $("#PhoneNumber").val();
			var b = a.trim();
			$("#PhoneNumber").val(b);
		});
		$("#Website").blur(function(){  
			var a = $("#Website").val();
			var b = a.trim();
			$("#Website").val(b);
		});
		$("#business_type").blur(function(){  
			var a = $("#business_type").val();
			var b = a.trim();
			$("#business_type").val(b);
		});
		$("#no_of_customer").blur(function(){  
			var a = $("#no_of_customer").val();
			var b = a.trim();
			$("#no_of_customer").val(b);
		});

		$("#no_of_employee").blur(function(){  
			var a = $("#no_of_employee").val();
			var b = a.trim();
			$("#no_of_employee").val(b);
		});
		
		$("#interest").blur(function(){  
			var a = $("#interest").val();
			var b = a.trim();
			$("#interest").val(b);
		});
		$("#territory").blur(function(){  
			var a = $("#territory").val();
			var b = a.trim();
			$("#territory").val(b);
		});
		
		$.validator.addMethod("emailvalidate", 
			function(value, element) {
				return /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
			}, 
			"Please Enter the valid Email"
		);

		$.validator.addMethod(
			"mobile",
			function(value, element) {
				// put your own logic here, this is just a (crappy) example
				return value.match(/^[0-9]*$/);
			},
			"alphabets are not accepted"
			);


	$('form[name="BecomePartner"]').validate({ 
    
	rules:{
		
		firstname:"required",
		lastname:"required",
		email:
				{
					required:true,
					emailvalidate:true
					
				},
		company:"required",
		Designation:"required",
		AddressLine1:"required",
		AddressLine2:"required",
		City:"required",
		State:"required",
		Zipcode:{
					required:true,
					mobile:true
				},
		Country:"required",
		PhoneNumber:{
					required:true,
					mobile:true
				},
		Website:"required",
		business_type:"required",
		publicorprivate:"required",
		no_of_customer:{
					required:true,
					mobile:true
				},
		no_of_employee:{
					required:true,
					mobile:true
				},
		interest:"required",
		territory:"required",
	},
	
	messages:{
		firstname:"FirstName is required",
		lastname:"Lastname is required",
		email:{
			required:"EmailId is required",
			emailvalidate:"Enter Valid EmailId"
		},
		company:"company is required",
		Designation:"Designation is required",
		AddressLine1:"AddressLine is required",
		AddressLine2:"AddressLine2 is required",
		City:"City is required",
		State:"State is required",
		Zipcode:{
			required:"Zipcode is required",
			mobile:"Only numbers are allowed",
			maxlength:6
		},
		Country:"Country is required",
		PhoneNumber:
		{
			required:"PhoneNumber is required",
			mobile:"Only numbers are allowed"
		},
		Website:"Website is required",
		business_type:"BusinessType is required",
		publicorprivate:"Select Public or Private",
		no_of_customer:
		{
			required:"Number of Customers is required",
			mobile:"Only numbers are allowed"
		},
		no_of_employee:
		{
			required:"Number of Employees is required",
			mobile:"Only numbers are allowed"
		},
		interest:"Enter your interest for choosing digiBlitz",
		territory:"Enter your Wish to see products of digiBlitz",
		
		
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

                   <h3 class="title">Become A Partner</h3>
				 
				 </div>
				 
				 
				 <form name="BecomePartner" id="BecomePartner" method="post" action="" class="formcss">
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">

                   <h4 class="title">PLEASE TELL US ABOUT YOURSELF</h4>
				 
				 </div>
				 
				 	<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									First Name <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="inf_field_FirstName" id="firstname" class="form-control" placeholder="First Name" />
								
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
							
							  <input type="text" name="inf_field_LastName" id="lastname" class="form-control" placeholder="Last Name" />
								
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
							
							  <input type="text" name="inf_field_Email" id="email" class="form-control" placeholder="Email" onBlur="usrStat();"/>
								
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Company <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="inf_field_Company" id="company" class="form-control" placeholder="Company" />
								
								
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
							
							  <input type="text" name="inf_field_JobTitle" id="Designation" class="form-control" placeholder="Designation" />
								
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									AddressLine <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="inf_field_Address3Street1" id="AddressLine1" class="form-control" placeholder="AddressLine" />
								
								
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
							
							  <input type="text" name="inf_field_City3" id="City" class="form-control" placeholder="City" />
								
								
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Zipcode <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="inf_field_PostalCode3" id="Zipcode" class="form-control" placeholder="Zipcode" />
								
								
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
							
							 <select name="inf_field_Country3" id="Country" class="form-control" onchange="FillState(document.BecomePartner.Country, document.BecomePartner.State,'');"  >
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
									State <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							 <select name="inf_field_State3" id="State" class="form-control">
								<option selected="selected">Select One</option>
								<option value="FL">FL</option>
								<option value="VA">VA</option>
							  </select>
							 
								
								
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									PhoneNumber <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="inf_field_Phone1" id="PhoneNumber" class="form-control" placeholder="PhoneNumber" />
								
								
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
              
							<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Company Website URL <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="inf_field_Website" id="Website" class="form-control" placeholder="Website" />
								
								
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">

                   <h4 class="title">PLEASE TELL US ABOUT YOUR COMPANY</h4>
				 
				 </div>
				 
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									BusinessType <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							   <select name="inf_field_SourceType" id="business_type" class="form-control" >
								<option selected="selected">Select One</option>
								<option value="Service Provider">Service Provider</option>
								<option value="Help Desk">Help Desk</option>
								<option value="ISV">ISV</option>
								<option value="Reseller">Reseller</option>
								<option value="Software/IT">Software/IT</option>
								<option value="Consulting">Consulting </option>
								<option value="Manufacturing">Manufacturing </option>
								<option value="Other "> Other</option>
								
							  </select>
								
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Public or Private <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							 <input type="radio" size="10" name="inf_option_PublicorPrivate" id="publicorprivate" value="public"/> Public
							 <input type="radio" size="10" name="inf_option_PublicorPrivate" id="publicorprivate" value="Private"/> Private
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Number of Customers<span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="inf_field_SSN" id="no_of_customer" class="form-control" placeholder="Number Of Customers" />
								
								
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								 <label class="name form-div-6">
									Number of Employees<span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="text" name="inf_custom_RegNo" id="no_of_employee" class="form-control" placeholder="Number Of Employee" />
								
								
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">

                        <h4 class="title">PLEASE TELL US ABOUT YOUR INTEREST IN DIGIBLITZ</h4>
				 
				       </div>
				 
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-3 col-md-3 col-sm-3">
						&nbsp;
						</div>
								 <label class="name form-div-6">
								What is driving your interest in digiBlitz?<span class="asterisk">*</span>
								</label>
							
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-3 col-md-3 col-sm-3">
						&nbsp;
						</div>
								
							<div class="col-lg-5 col-md-5 col-sm-5">
								<input type="textare" name="inf_custom_NPSComments" id="interest" rows="4"   class="form-control">
							 
								
								
							</div>
							
						</div>
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-3 col-md-3 col-sm-3">
						&nbsp;
						</div>
						
								 <label class="name form-div-6">
								Please describe the geographic territory(city,state,country etc)where you propose to see digiBlitz products<span class="asterisk">*</span>
								</label>
							</div>
							
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-3 col-md-3 col-sm-3">
						&nbsp;
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							<input type="textarea"  name="inf_custom_PositionDescription" id="territory" rows="4"  class="form-control">
						</div>
							 
						
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-3 col-md-3 col-sm-3">
						&nbsp;
						</div>
								 <label class="name form-div-6">
								what digiBlitz products are you interested in reselling?(Check all that apply)<span class="asterisk">*</span>
								</label>
							
							
							
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-3 col-md-3 col-sm-3">
						&nbsp;
						</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <input type="checkbox" name="inf_option_menshcforce"   value="menschforce"  />menschForce</br>
							  <input type="checkbox" name="inf_option_guildkraft"    value="guildKraft"   />guildKraft </br>
							  <input type="checkbox" name="inf_option_klugwerks"    value="klugwerks"  />klugwerks </br>
							  <input type="checkbox" name="inf_option_smartLehren"   value="smartLehren"   />smartLehren </br>
								
								
							</div>
							
							</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-5">
							 <button type="submit" class="button-add" name="Submit" value="Submit" onClick='this.form.action="BecomePartner.html";'>Submit</button>
							
							</div>
							</div>
						
						
			</form>			
						
						
				 
</div>
</div>
</div>
</div>

<script language="javascript">
	FillCountry(document.BecomePartner.Country, document.BecomePartner.State, 'USA' );
	FillState(document.BecomePartner.Country, document.BecomePartner.State, '');


</script>

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

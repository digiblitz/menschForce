<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
  <!DOCTYPE html>
  <html lang="en">
  <link rel="icon" href="img/favicon.ico" type="image/x-icon">
    <head>
      <title>
        Home
      </title>
      
     <style>
img.image1{

    right: 25px;
    position: absolute;
    top: 10px;
}
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
    <%@ include file = "../../include/header_new.jsp" %>
      <!-- HEADER ENDS HERE -->
    </div>
    
    <!--========================================================
CONTENT
=========================================================-->
<script src="js/jquery.validate.js"></script>
 <script src="js/cscombo_new.js" type="text/javascript" >
      </script>
	 
  

 <script type="text/javascript">
		 //alert("working");
		 $(document).ready(function() {
	//alert("working");
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
	$("#mobileno").blur(function(){        
		var a = $("#mobileno").val();
		var b = a.trim();
		$("#mobileno").val(b);
    });
	$("#institutionName").blur(function(){        
		var a = $("#institutionName").val();
		var b = a.trim();
		$("#institutionName").val(b);
    });
	$("#street").blur(function(){        
		var a = $("#street").val();
		var b = a.trim();
		$("#street").val(b);
    });
	$("#city").blur(function(){        
		var a = $("#city").val();
		var b = a.trim();
		$("#city").val(b);
    });
	$("#zipcode").blur(function(){        
		var a = $("#zipcode").val();
		var b = a.trim();
		$("#zipcode").val(b);
    });
	$("#credit_card_no").blur(function(){        
		var a = $("#credit_card_no").val();
		var b = a.trim();
		$("#credit_card_no").val(b);
    });
	$("#cvv_no").blur(function(){        
		var a = $("#cvv_no").val();
		var b = a.trim();
		$("#cvv_no").val(b);
    });
	$("#name_on_card").blur(function(){        
		var a = $("#name_on_card").val();
		var b = a.trim();
		$("#name_on_card").val(b);
    });
	$("#expiry_month").blur(function(){        
		var a = $("#expiry_month").val();
		var b = a.trim();
		$("#expiry_month").val(b);
    });
	$("#expiry_year").blur(function(){        
		var a = $("#expiry_year").val();
		var b = a.trim();
		$("#expiry_year").val(b);
    });
	
		
		/* special character and number validation*/

			$.validator.addMethod(
			"checkspecialcharacter",
			function(value, element) {
				// put your own logic here, this is just a (crappy) example
				return value.match(/^[a-zA-Z ]*$/);
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
			return value.match(/^[0-9]*(+-)$/);
			},
			"alphabets are not accepted"
			);
			
   		 $("#contact-form").validate({
			 
			ignore: ":disabled",
			 			 
			rules: {
				firstname: {
					required:true,
					checkspecialcharacter:true	
			},
			lastname:
			{
				required:true,
				checkspecialcharacter:true	

			},
				email:
				{
					required:true,
					emailvalidate:true
					
				},
			mobileno:
			{
				required:true,
				mobile:true,
				minlength:16

			},
			institutionName:
			{
				// required:true,
			//	checkspecialcharacter:true	


			},
			street:
			{
				required:true,
				//checkspecialcharacter:true	


			},
			city:
			{
				required:true,
				checkspecialcharacter:true	


			},
		country:			{
				required:true,


			},
			state:
			{
				required:true,


			},
			zipcode:
			{
				required:true,
				mobile:true,
				maxlength:6


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
					accept: {
					required: true
				},
					
			
			},
				messages: {
					firstname:{
						required:"Enter your firstname",
						checkspecialcharacter:"Special Characters are not allowed"
					},
					lastname:{
						required:"Enter your lastname",
						checkspecialcharacter:"Special Characters are not allowed"

					},
				
				
				email:
					{
						required:"Enter your E-mail ID",
						emailvalidate:"Enter valid E-mail Id"
					},
					mobileno:{
						required:"Enter your mobile number",
					    mobile:"Only numbers are allowed"

					},
					institutionName:{
						required:"Enter your institution name",
						checkspecialcharacter:"Special Characters are not allowed"

					    

					},
					street:{
						required:"Enter your street name",
					checkspecialcharacter:"Special Characters are not allowed"

						
					    

					},
					city:{
						required:"Enter your city name",
						checkspecialcharacter:"Special Characters are not allowed"

					    

					},
					country:{
						required:"Select you country",
						

					},
					state:{
						required:"Select your state",
						

					},
					zipcode:{
						required:"Enter your zipcode",
						mobile:"Only digits are allowed"
					  
					},
					credit_card_type:
					{
					required:"Select your CardType",	
					},
					credit_card_no:
					{
						required:"Enter your credit Card No",
						mobile:"Only digits are allowed"
					},
					cvv_no:
					{
						required:"Enter your CVV No",
						mobile:"Only digits are allowed"
					},
					name_on_card:
					{
						required:"Enter your Name On Card",
						checkspecialcharacter:"Special Characters are not allowed"
						
					},
					expiry_month:
					{
						required:"Enter your card Expiry month",
						mobile:"Only digits are allowed"
					},
					expiry_year:
					{
						required:"Enter your card Expiry year",
						mobile:"Only digits are allowed"
					},
					accept:"Please Accept terms and conditions",
				    
					
				},
				errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}
			
				
				
				
						});
											

						
   
      
      $('#plan').click(function(event) {
         var oneTimeCost;
		 var totalCost;
        if($('#plan').val()=="monthly"){
         
          var a = parseInt($('#amo').val());
		   oneTimeCost = parseInt($('#oneTimeCost').val());
          
          var productPrice=a*1;
          
          $("#dp1").show();
          $("#dp2").hide();
          $("#dp3").hide();
        }
        
        $("#fee").val(Math.round(productPrice));
		
		$("#oneTimeSetupPrice").val(oneTimeCost);
		totalCost = productPrice+oneTimeCost;
		$("#total").val(Math.round(totalCost));
      }
                      );
      
      $('#plan1').click(function(event) {
        var totalCost;
        if($('#plan1').val()=="quarterly"){
          var a = parseInt($('#amo').val());
		   var oneTimeCost = parseInt($('#oneTimeCost').val());
         
          var n=a*3;
          var m=n*10/100;
          var productPrice=n-m;
          $("#dp1").hide();
          $("#dp2").show();
          $("#dp3").hide();
        }
        
        $("#fee").val(Math.round(productPrice));
		
		$("#oneTimeSetupPrice").val(oneTimeCost);
		totalCost = productPrice+oneTimeCost;
		$("#total").val(Math.round(totalCost));
		
      }
                       );
      $('#plan2').click(function(event) {
        var totalCost;
        if($('#plan2').val()=="annual"){
          var a = parseInt($('#amo').val());
		     var oneTimeCost = parseInt($('#oneTimeCost').val());
          
          var n=a*12;
          var m=n*25/100;
          var productPrice=n-m;
          //alert(productPrice);
          $("#dp1").hide();
          $("#dp2").hide();
          $("#dp3").show();
        }
        
        $("#fee").val(Math.round(productPrice));
		
		$("#oneTimeSetupPrice").val(oneTimeCost);
		totalCost = productPrice+oneTimeCost;
		$("#total").val(Math.round(totalCost));
      }
                       );
   
					 
	//prabhu working here
	
	
	

      
      $('#Productplan').click(function(event) {
           var productPrice=null;
		  var subtype = null;
		  var feePrice = null;
		  var totalCost;
        if($('#Productplan').val()=="Basic"){
		var a = 99;
          productPrice=99;
		var oneTimeCost = 1100;
		  if($("input[type='radio'].radioBtnClass").is(':checked')) {
    subtype = $("input[type='radio'].radioBtnClass:checked").val();
    //alert(subtype);
}
		  if(subtype=="monthly"){
		  //alert("basic monthly");
		 feePrice = a*1;
		 
		  } else if(subtype=="quarterly"){
		  	  //alert("basic quarterly");
		  var n=a*3;
          var m=n*10/100;
          feePrice=n-m;
		
		  }
		  else if(subtype=="annual"){
		  //alert("basic annual");
		      var n=a*12;
          var m=n*25/100;
          feePrice=n-m;
		 
		  }
		
        }
        $("#fee").val(Math.round(feePrice));
		 $("#amo").val(productPrice);
		 $("#oneTimeCost").val(oneTimeCost);
		 $("#oneTimeSetupPrice").val(oneTimeCost);
		 totalCost = feePrice+oneTimeCost;
		 $("#total").val(Math.round(totalCost));
      }
                      );
					  
					  
      
      $('#Productplan1').click(function(event) {
              var productPrice=null;
		   var subtype = null;
		  var feePrice = null;
		  var totalCost;
        if($('#Productplan1').val()=="Silver"){
		  var a = 299;
          productPrice=299;
		  var oneTimeCost = 1100;

		  if($("input[type='radio'].radioBtnClass").is(':checked')) {
    subtype = $("input[type='radio'].radioBtnClass:checked").val();
    //alert(subtype);
}
		  if(subtype=="monthly"){
		  //alert("Silver monthly");
		 feePrice = a*1;
		 
		  } else if(subtype=="quarterly"){
		  	  //alert("Silver quarterly");
		  var n=a*3;
          var m=n*10/100;
          feePrice=n-m;
		 
		  }
		  else if(subtype=="annual"){
		  //alert("Silver annual");
		      var n=a*12;
          var m=n*25/100;
          feePrice=n-m;
		  
		  }
        }
        $("#fee").val(Math.round(feePrice));
		 $("#amo").val(productPrice);
		 $("#oneTimeSetupPrice").val(oneTimeCost);
		  $("#oneTimeCost").val(oneTimeCost);
		   totalCost = feePrice+oneTimeCost;
		 $("#total").val(Math.round(totalCost));
      }
                       );
					   
					   
      $('#Productplan2').click(function(event) {
             var productPrice=null;
		   var subtype = null;
		  var feePrice = null;
		  var totalCost;
        if($('#Productplan2').val()=="Gold"){
         var a = 1249;
	var oneTimeCost = 3999;
          productPrice=1249;

		  if($("input[type='radio'].radioBtnClass").is(':checked')) {
    subtype = $("input[type='radio'].radioBtnClass:checked").val();
    //alert(subtype);
}
		  if(subtype=="monthly"){
		  //alert("Gold monthly");
		 feePrice = a*1;
		
		  } else if(subtype=="quarterly"){
		  	  //alert("Gold quarterly");
		  var n=a*3;
          var m=n*10/100;
          feePrice=n-m;
		
		  }
		  else if(subtype=="annual"){
		  //alert("Gold annual");
		      var n=a*12;
          var m=n*25/100;
          feePrice=n-m;
		  
		  }
            }
        
        $("#fee").val(Math.round(feePrice));
		 $("#amo").val(productPrice);
		 $("#oneTimeSetupPrice").val(oneTimeCost);
		  $("#oneTimeCost").val(oneTimeCost);
		   totalCost = feePrice+oneTimeCost;
		 $("#total").val(Math.round(totalCost));
      }
                       );
					   

		$('#Productplan3').click(function(event) {
            var productPrice=null;
		   var subtype = null;
		  var feePrice = null;
		  var totalCost;
        if($('#Productplan3').val()=="Platinum"){
		var a = 4999;
	var oneTimeCost = 14999;
          productPrice=4999;

		  if($("input[type='radio'].radioBtnClass").is(':checked')) {
    subtype = $("input[type='radio'].radioBtnClass:checked").val();
    //alert(subtype);
}
		  if(subtype=="monthly"){
		  //alert("Platinum monthly");
		 feePrice = a*1;
		
		  } else if(subtype=="quarterly"){
		  	  //alert("Platinum quarterly");
		  var n=a*3;
          var m=n*10/100;
          feePrice=n-m;
		
		  }
		  else if(subtype=="annual"){
		  //alert("Platinum annual");
		      var n=a*12;
          var m=n*25/100;
          feePrice=n-m;
		  
		  }
        }
        $("#fee").val(Math.round(feePrice));
		 $("#amo").val(productPrice);
		 $("#oneTimeSetupPrice").val(oneTimeCost);
		  $("#oneTimeCost").val(oneTimeCost);
		   totalCost = feePrice+oneTimeCost;
		 $("#total").val(Math.round(totalCost));
      }
                       );
		
		  $('#coupon').click(function(event) {
		        var sports = $("#couponcode").val();
		    	var spo = $("#total").val();
		    	var txt_wrong = "Invalid Coupon Code!";
		    	
		    	$.get('user.html?cmd=couponname', {
		        	prog : sports,
		    		cost : spo
		        }, function(response) {
		        	if(response != null && response !="" && response != "null"){
		        		 $("#total").val(response);
		    			// $( "#total" ).focus();
						 var a = document.getElementById('total');
							a.style.border = '1px solid green';
		        	     
		        	}else{
		    			
		    			$("#total").val(spo);
		    			//alert(txt_wrong);  
						// $( "#total" ).focus();
						  var a = document.getElementById('total');
							a.style.border = '1px solid red';
					document.getElementById("coupon_error").innerHTML=txt_wrong;
		        	}       
		        });
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
		    if(div.scrollTop>=1000){
			        var x = document.getElementById ("selectAccept").disabled = false ;
			}
		}


	
	
</script>	

 

  <%String user_id=(String)request.getAttribute("user_id"); %>
                        <%String usrStat=(String)request.getAttribute("usrStat");
                        String purchase=(String)request.getAttribute("purchase");
                        String productPrice=(String)request.getAttribute("productPrice");
                        String category=(String)request.getAttribute("category");
                        
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
						coupon_code = (String)request.getAttribute("coupon_code");
                        
                        %>
<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">

			<% if(purchase.equalsIgnoreCase("try_now") && purchase != null && coupon_code != null){ %>
                <div class="col-lg-4 col-md-4 col-sm-4">
                    <h3>Congratulations!</h3>
                    <div class="info">
                        <p>You can access our Product free upto 3 Months...</p>
                        <p>Enjoy our Product...</p>
                    </div>
                </div>
				<%} else if(purchase.equalsIgnoreCase("try_now") && purchase != null && coupon_code == null){%>
				<div class="col-lg-4 col-md-4 col-sm-4">
					<h3>We won't charge up to end of the trial period.</h3>
                    <h3>Congratulations!</h3>
                    <div class="info">
                        <p>You can access our Product free upto 30 days...</p>
                        <p>Enjoy our Product...</p>
                    </div>
                </div>
				<%}else{%>
				<div class="col-lg-4 col-md-4 col-sm-4">
                    <h3>Congratulations!</h3>
                    <div class="info">
                        <p>You can access our Product Now!...</p>
                        <p>Enjoy our Product!...</p>
                    </div>
                </div>
				<%}%>

                <div class="col-lg-8 col-md-8 col-sm-8">
                    <h3>Sign Up Form</h3>
                    <form id="contact-form" name="insert" align="center" action="signUp.html?signUpProcess=signup" method="post" onSubmit="return onValidate();">
						<input type="hidden" name="amount" id="amo" value="<%=productPrice%>">    
                        <input type="hidden" name="coupon_status" value="<%=coupon_status%>"/>
                        <input type="hidden" name="coupon_value" value="<%=coupon_value%>"/>
                        <input type="hidden" name="coupon_description" value="<%=coupon_description%>"/>
                        <input type="hidden" name="coupon_valid" value="<%=coupon_valid%>"/>
                        <input type="hidden" name="coupon_code" value="<%=coupon_code%>"/>
                        
                        <input type="hidden" name="category" value="<%=category%>"/>
                        

                          
                          <fieldset>
                            <label class="name form-div-6" align="left">
							<span id="nihalfirst" style="color: red;"></span>
                              <input type="text" name="firstname" id="firstname" placeholder="First Name:" value="" data-constraints="@Required @JustLetters"  />
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
							
							<label class="name form-div-6" align="left">
							<span id="nihallast" style="color: red;"></span>
                              <input type="text" name="lastname" id="lastname" placeholder="Last Name:" value="" data-constraints="@Required @JustLetters"  />
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
							
                            <label class="email form-div-6" align="left">
							<span id="Nemail" style="color: red;"></span>
                              <input type="text" name="email" id="email" placeholder="E-mail:" value="" data-constraints="@Required @Email" onChange="ValidateEmail('email');"/>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid email.</span>
                            </label>
                            
							<%if (purchase.equalsIgnoreCase("try_now") && purchase != null && coupon_code != null){%>
							<%}else if (purchase.equalsIgnoreCase("try_now") && purchase != null && coupon_code == null){%>
                            <label class="phone form-div-6" align="left">
							<span id="Nmobileno" style="color: red;"></span>
                              <input type="text" name="mobileno" id="mobileno" placeholder="Mobile No with country code:" value="" data-constraints="@Required " /><!-- @JustNumbers -->
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid phone.</span>
                            </label>
							<label class="name form-div-6" align="left">
							<span id="NinstitutionName" style="color: red;"></span>
                              <input type="text" name="institutionName" id="institutionName" placeholder="Organization Name:" value=""   />
                              <!--   data-constraints="@Required @JustLetters"
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>   -->
                            </label>
							
							
                            <label class="name form-div-6" align="left">
							<span id="Nstreet" style="color: red;"></span>
                              <input type="text" name="street" id="street" placeholder="Street Name:" value="" data-constraints="@Required "   /> <!-- @JustLetters -->
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
                            <label class="name form-div-6" align="left">
							<span id="Ncity" style="color: red;"></span>
                              <input type="text" name="city" id="city" placeholder="City:" value="" data-constraints="@Required "  /> <!-- @JustLetters -->
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
                            <label class="name form-div-6" align="left" align="left">
                              Country :
							  <span id="Ncountry" style="color: red;"></span>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
							<label class="name form-div-6" align="left" align="left">
                              <select name="country" id="insert_country" onChange="FillState(document.insert.country, document.insert.state, '');" class="large">
							  <option selected="selected" value="selected">
							   Select One
							  </option>
							  <option value="usa">
							   United States of America
							  </option>
						     </select>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
							<label class="name form-div-6" align="left">
                              State :
							  <span id="Nstate" style="color: red;"></span>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
							<label class="name form-div-6" align="left" align="left">
                              <select name="state" class="large" id="insert_state">
							  <option selected ="selected" value="selected">
								Select State
							</option>
							<option value="FL">
								FL
							</option>
							<option value="VA">
								VA
							</option>
							</select>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
                            <label class="name form-div-6" align="left">
							<span id="Nzipcode" style="color: red;"></span>
                              <input type="text" name="zipcode" id="zipcode" placeholder="Zip Code:" value="" data-constraints="@Required @JustNumbers"  />
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
							<%}else{%>
							<label class="phone form-div-6" align="left">
							<span id="Nmobileno" style="color: red;"></span>
                              <input type="text" name="mobileno" id="mobileno" placeholder="Mobile No with country code:" value="" data-constraints="@Required " /> <!-- @JustNumbers -->
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid phone.</span>
                            </label>
							<label class="name form-div-6" align="left">
							<span id="NinstitutionName" style="color: red;"></span>
                              <input type="text" name="institutionName" id="institutionName" placeholder="Organization Name:" value="" data-constraints=""  /><!-- @Required @JustLetters -->
                             
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
							<label class="name form-div-6" align="left">
							<span id="Nstreet" style="color: red;"></span>
                              <input type="text" name="street" id="street" placeholder="Street Name:" value="" data-constraints="@Required " /><!-- @JustLetters  -->
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
                            <label class="name form-div-6" align="left">
							<span id="Ncity" style="color: red;"></span>
                              <input type="text" name="city" id="city" placeholder="City:" value="" data-constraints="@Required @JustLetters"  />
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
                            <label class="name form-div-6" align="left" align="left">
                              Country :
							  <span id="Ncountry" style="color: red;"></span>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
							<label class="name form-div-6" align="left" align="left">
                              <select name="country" id="insert_country" onChange="FillState(document.insert.country, document.insert.state, '');" class="large">
							  <option selected="selected" value="selected">
							   Select One
							  </option>
							  <option value="usa">
							   United States of America
							  </option>
						     </select>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
							<label class="name form-div-6" align="left">
                              State :
							  <span id="Nstate" style="color: red;"></span>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
							<label class="name form-div-6" align="left" align="left">
                              <select name="state" class="large" id="insert_state">
							  <option selected ="selected" value="selected">
								Select State
							</option>
							<option value="FL">
								FL
							</option>
							<option value="VA">
								VA
							</option>
							</select>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
                            <label class="name form-div-6" align="left">
							<span id="Nzipcode" style="color: red;"></span>
                              <input type="text" name="zipcode" id="zipcode" placeholder="Zip Code:" value="" data-constraints="@Required @JustNumbers"  />
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
							  <%}%>
							<%
               if(purchase.equalsIgnoreCase("buy_now") && purchase != null)
               
               {
			   String productPlan=(String)request.getAttribute("productPlan");
               
               %>
							<label class="name form-div-6" align="left">
							Payment Plan:
							</label>
							<label class="name form-div-6" align="left">
							
							 <%  if(productPlan.equalsIgnoreCase("Basic") && productPlan != null)
               
							{%>
                              <input type="radio" name="Productplan" id="Productplan" value="Basic"  checked="checked"/>
								 Basic
								 <input type="radio" name="Productplan" id="Productplan1" value="Silver" />
								 Silver
								 <input type="radio" name="Productplan" id="Productplan2" value="Gold" />
								 Gold 
								 <input type="radio" name="Productplan" id="Productplan3" value="Platinum" />
								 Platinum 
								 <%} else if(productPlan.equalsIgnoreCase("Silver") && productPlan != null){%>
								 <input type="radio" name="Productplan" id="Productplan" value="Basic" />
								 Basic
								 <input type="radio" name="Productplan" id="Productplan1" value="Silver" checked="checked"/>
								 Silver
								 <input type="radio" name="Productplan" id="Productplan2" value="Gold" />
								 Gold 
								 <input type="radio" name="Productplan" id="Productplan3" value="Platinum" />
								 Platinum 
								 <%}else if(productPlan.equalsIgnoreCase("Gold") && productPlan != null){%>
								  <input type="radio" name="Productplan" id="Productplan" value="Basic" />
								 Basic
								 <input type="radio" name="Productplan" id="Productplan1" value="Silver" />
								 Silver
								 <input type="radio" name="Productplan" id="Productplan2" value="Gold" checked="checked"/>
								 Gold 
								 <input type="radio" name="Productplan" id="Productplan3" value="Platinum" />
								 Platinum 
								 <%}else if(productPlan.equalsIgnoreCase("Platinum") && productPlan != null){%>
								 <input type="radio" name="Productplan" id="Productplan" value="Basic" />
								 Basic
								 <input type="radio" name="Productplan" id="Productplan1" value="Silver" />
								 Silver
								 <input type="radio" name="Productplan" id="Productplan2" value="Gold" />
								 Gold 
								 <input type="radio" name="Productplan" id="Productplan3" value="Platinum" checked="checked"/>
								 Platinum 
							<%}%>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
							<label class="name form-div-6" align="left">
							Subscription Type:
							</label>
							<label class="name form-div-6" align="left">
							 
                              <input type="radio" name="plan" id="plan" value="monthly" class="radioBtnClass"/>
                     
							 Monthly
							 <input type="radio" name="plan" id="plan1" value="quarterly" checked="checked" class="radioBtnClass"/>
							 
							 Quarterly(Save 10%)
							 <input type="radio" name="plan" id="plan2" value="annual" class="radioBtnClass"/>
							 Annual(Save 25%)  
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
				<% if(productPrice != null){
				 String productPlan1 = (String)request.getAttribute("productPlan");
                 int ap = Integer.parseInt(productPrice);
				 System.out.println("ap:::::::::::::::::::in JSP :::"+ap);
				 String cost = null;
				 int intCost;
				 String totalCost = null;
                 int n=ap*3;
                 int m=n*10/100;
				
                 int am=n-m;
				 if(productPlan1 !=null && productPlan1.equalsIgnoreCase("Basic")){
				 cost = "1100";
				 
				 }else if(productPlan1 !=null && productPlan1.equalsIgnoreCase("Silver")){
				 cost = "1100";
				 
				 }else if(productPlan1 !=null && productPlan1.equalsIgnoreCase("Gold")){
				 cost = "3999";
				
				 }else if(productPlan1 !=null && productPlan1.equalsIgnoreCase("Platinum")){
				 cost = "14999";
				
				 }
                 productPrice=Integer.toString(am);
				 intCost = am + Integer.parseInt(cost);
				 totalCost=Integer.toString(intCost);
				  System.out.println("productPrice:::::::::::::::::::in JSP :::"+productPrice);
				  System.out.println("totalCost:::::::::::::::::::in JSP :::"+totalCost);
                 %>			
							<label class="phone form-div-6" align="left">
							Subscription Cost: &#36;
                            </label>
							<label class="name form-div-6" align="left">
							<input type="hidden" name="amount" id="oneTimeCost" value="<%=cost%>">
							
                              <input type="text" name="productPrice1" id="fee" placeholder="Subscription Cost:" value="<%=productPrice%>" data-constraints="@Required @JustNumbers"    readonly/>
							  <span class="dp1">Total for a Month</span>
							  <span class="dp2">Total for 3 Month</span>
							  <span class="dp3">Total for 12 Month</span>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
							<label class="phone form-div-6" align="left">
							One Time Setup Cost: &#36;
                            </label>
							<label class="phone form-div-6" align="left">
                              <input type="text" name="oneTimeSetupPrice" id="oneTimeSetupPrice" placeholder="One Time Setup Cost:" value="<%=cost%>" data-constraints="@Required @JustNumbers" readonly/>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid phone.</span>
                            </label>
                            
                           
							<label class="phone form-div-6" align="left">
							Coupon Code:
                            </label>
							<label class="phone form-div-6" align="left">
							<span id="coupon_error" style="color: red;"></span>
                              <input type="text" name="couponcode" id="couponcode" placeholder="Apply Coupon Code here:"/>
							  <input type="button" name="coupon" id="coupon" value="Apply" class="btn-default btn3">
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid phone.</span>
                            </label>
							
							<label class="phone form-div-6" align="left">
							Total Amount to be Charged: &#36;
                            </label>
							<label class="phone form-div-6" align="left">
                              <input type="text" name="productPrice" id="total" placeholder="Total Amount to be Charged:" value="<%=totalCost%>" data-constraints="@Required @JustNumbers" readonly/>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid phone.</span>
                            </label>
						<% }else{%>
							<label class="phone form-div-6" align="left">
							Total Amount to be Charged: &#36;
                            </label>
							<label class="phone form-div-6" align="left">
                              <input type="text" name="fee" id="productPrice" placeholder="Total Amount to be Charged:" value="<%=productPrice%>" data-constraints="@Required @JustNumbers" readonly/>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid phone.</span>
                            </label>
						<%}%>
                       <%}%>
					   <%
                               if(purchase.equalsIgnoreCase("try_now") && purchase != null && coupon_code == null)
                               
                               {
                               
                       %>
							<label class="phone form-div-6" align="left">
							Card Type:
							<span id="Ncredit" style="color: red;"></span>
							<span class="empty-message">*This field is required.</span>
                             <span class="error-message">*This is not a valid phone.</span>
							 </label>
							<label class="phone form-div-6" align="left">
                             <input type="radio" size="10" name="credit_card_type" value="visa" />
                             <img src="images/visa.png" id="visa" name="visa" height="35px" width="45px"/>
                             <input type="radio" size="10" name="credit_card_type" value="mastercard" />
                             <img src="images/master.png" id="master" name="master" height="35px" width="45px"/>
                             <input type="radio" size="10" name="credit_card_type" value="amex" />
                             <img src="images/american.png" id="american" name="american" height="35px" width="45px"/>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid phone.</span>
                            </label>
							<label class="phone form-div-6" align="left">
							<span id="Ncreditno" style="color: red;"></span>
                              <input type="text" name="credit_card_no" id="credit_card_no" placeholder="Card No:" value="" data-constraints="@Required @JustNumbers" />
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid phone.</span>
                            </label>
							<label class="phone form-div-6" align="left">
							<span id="Ncvv" style="color: red;"></span>
                              <input type="password" name="cvv_no" id="cvv_no" placeholder="CVV no:" value="" data-constraints="@Required @JustNumbers" maxlength="3"/>
							  <!--img src="images/cvv.png" id="visa" name="visa" height="35px" width="45px"/-->
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid phone.</span>
                            </label>
							<label class="name form-div-6" align="left">
							<span id="Namec" style="color: red;"></span>
                              <input type="text" name="name_on_card" id="name_on_card" placeholder="Name on Card:" value="" data-constraints="@Required @JustLetters"  />
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
							<label class="phone form-div-6" align="left">
							Expiry Date:
                              <span id="Nexp" style="color: red;"></span>
							   <span id="Nexpy" style="color: red;"></span>
                            </label>
							<label class="phone form-div-6" align="left">
                              <input type="text" name="expiry_month" id="expiry_month" placeholder="MM" value="" data-constraints="@Required @JustNumbers" maxlength="2" size="2"/>
							  
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid phone.</span>
                            </label>
							<label class="phone form-div-6" align="left">
                              
							  <input type="text" name="expiry_year" id="expiry_year" placeholder="YYYY" value="" data-constraints="@Required @JustNumbers" maxlength="4" size="2"/>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid phone.</span>
                            </label>
						<%}else if (purchase.equalsIgnoreCase("try_now") && purchase != null && coupon_code != null){%>
						   
						<%}else{ %>
							<label class="phone form-div-6" align="left">
							Card Type:
							<span id="Ncredit" style="color: red;"></span>
							<span class="empty-message">*This field is required.</span>
                             <span class="error-message">*This is not a valid phone.</span>
							 </label>
							<label class="phone form-div-6" align="left">
                             <input type="radio" size="10" name="credit_card_type" value="visa" />
                             <img src="images/visa.png" id="visa" name="visa" height="35px" width="45px"/>
                             <input type="radio" size="10" name="credit_card_type" value="mastercard" />
                             <img src="images/master.png" id="master" name="master" height="35px" width="45px"/>
                             <input type="radio" size="10" name="credit_card_type" value="amex" />
                             <img src="images/american.png" id="american" name="american" height="35px" width="45px"/>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid phone.</span>
                            </label>
							<label class="phone form-div-6" align="left">
							<span id="Ncreditno" style="color: red;"></span>
                              <input type="text" name="credit_card_no" id="credit_card_no" placeholder="Card No:" value="" data-constraints="@Required @JustNumbers" />
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid phone.</span>
                            </label>
							<label class="phone form-div-6" align="left">
							<span id="Ncvv" style="color: red;"></span>
                              <input type="password" name="cvv_no" id="cvv_no" placeholder="CVV no:" value="" data-constraints="@Required @JustNumbers" maxlength="3"/>
							  <!--img src="images/cvv.png" id="visa" name="visa" height="35px" width="45px"/-->
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid phone.</span>
                            </label>
							<label class="name form-div-6" align="left">
							<span id="Namec" style="color: red;"></span>
                              <input type="text" name="name_on_card" id="name_on_card" placeholder="Name on Card:" value="" data-constraints="@Required @JustLetters"  />
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
							<label class="phone form-div-6" align="left">
							Expiry Date:
                              <span id="Nexp" style="color: red;"></span>
							   <span id="Nexpy" style="color: red;"></span>
                            </label>
							<label class="phone form-div-6" align="left">
                              <input type="text" name="expiry_month" id="expiry_month" placeholder="MM" value="" data-constraints="@Required @JustNumbers" maxlength="2" size="2"/>
							  
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid phone.</span>
                            </label>
							<label class="phone form-div-6" align="left">
                              
							  <input type="text" name="expiry_year" id="expiry_year" placeholder="YYYY" value="" data-constraints="@Required @JustNumbers" maxlength="4" size="2"/>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid phone.</span>
                            </label>
						<%}%>
						<%if (purchase.equalsIgnoreCase("try_now") && purchase != null && coupon_code != null){%>
							<%}else if (purchase.equalsIgnoreCase("try_now") && purchase != null && coupon_code == null){%>
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
							&nbsp;&nbsp;Pricing of all products are stated in digiBlitz’s relevant product or platform websites. Pricing page of our product(s) and platform(s) can be changed at any time without notice. digiBlitz Inc. may limit on the number that may be purchased per order, per account, per credit card, per person or per CPU. Pricing shown in the website will include all tax and charges of the product.<br/>
						<!-- 	<strong>2.	AUTO RENEWAL OF THE PRODUCTS</strong><br/>
							&nbsp;&nbsp;All products of digiBlitz Inc.  will be renewed automatically at the end of each month on a prorate basis or depending up on the date of purchase (depending upon individual cases). We will also inform you about the renewal through email before the subscription of the product. Once we informed you about the auto renewal, we will charge automatically for the product. The intimation will be given before one week from the renewal to digiBlitz Inc. by the customer .We will provide you with instruction on cancelling the product service or for suspending the product. However, no intimation will be given to you for subscription renewal of any dB store components. You must cancel the services before the renewal date to avoid being billed for the renewal.<br/> -->
							<strong>2.	Return, Refund and Exchange</strong><br/>
							&nbsp;&nbsp;The return policy is provided in addition to any legal return rights that you may have under law. digiBlitz Inc. will accept returns, refunds and exchanges within 30 day after purchasing of product. We have rights to refuse any refund, return or exchange if it fails to meet our norm. For Return, Refund and Exchange the intimation should be given before one week from the renewal to digiBlitz Inc. by the customer and it will be processed within 30 days. We may occasionally extend the 15-day return period during holiday or other periods. If a longer return period was advertised through a promotional campaign on the Website when you made your purchase, that applies within the clause mentioned in the promotional campaign.<br/>
							<strong>3.	Trial-period</strong><br/> 
							&nbsp;&nbsp;If you are taking part in any trial-period, you must cancel the service before the end of the trial period to avoid incurring new charges unless we notify you otherwise. Your trial subscription will be automatically upgraded to basic pack at the end of this free subscription period. This implies that your credit card will be charged for the basic pack. However, you could unsubscribe the product any time before the trial expiry through the Master account management link that is accessible when you login into the product.<br/>

							<strong>4.	Customer support</strong><br/>
							&nbsp;&nbsp;Please visit our Knowledge base site for Customer support and Assistance relating to the product.<br/>
							<strong>5.	CHANGING TERMS</strong><br/>
							&nbsp;&nbsp;digiBlitz Inc. may change the Terms of Return, Refund and Exchange at any time and without notice to you. The Terms of Return, Refund and Exchange in force at the time you place your order will govern your purchase and serve as the purchase contract between us. Before your next purchase, digiBlitz Inc. may change their policies. We advise you to visit the digiBlitz Inc. website, to review the current terms and condition, Refund and Return policy each time. <br/>
							<strong>6.	PROTECTION OF INDIVIDUAL INFORMATION</strong><br/>
							&nbsp;&nbsp;Your privacy is important to us. We may use certain information for the purpose of your product according to your need. <br/>
							<strong>7.	LIMITATION OF LIABILITY</strong><br/>
							&nbsp;&nbsp;In this section of Contract, digiBlitz Inc. is found liable to you for any loss or damage that arises out of or is in any way connected with your use of the dB Store, the Services, or any product or service offered, you agree that your exclusive remedy is to recover from digiBlitz Inc.  or any affiliates, and vendors direct damages up to (1) an amount equal to the price or fee for one month of any service or subscription or (2) US $100 if there was no service, subscription or similar fee.<br/>
							&nbsp;&nbsp;YOU AGREE THAT YOU CAN'T RECOVER ANY OTHER DAMAGES OR LOSSES, INCLUDING, WITHOUT LIMITATION, CONSEQUENTIAL, LOST PROFITS, SPECIAL, INDIRECT, INCIDENTAL, OR CONCERNING PUNISHMENT. THESE LIMITATIONS AND EXCLUSIONS APPLY EVEN IF YOU INCUR DAMAGES AND EVEN IF WE KNEW OR SHOULD HAVE KNOWN ABOUT THE POSSIBILITY OF THE DAMAGES. THESE LIMITATIONS AND EXCLUSIONS APPLY TO ANYTHING RELATED TO THE WEBSITE OWNED AND CONTROLLED BY DIGIBLITZ INC., ITS PRODUCT (S), PLATFORM(S) OR ANY COMPONENTS OF DB STORE, OR SERVICE OFFERED<br/>

							<strong>8.	Interpreting the Contract</strong><br/>
							&nbsp;&nbsp;All parts of this Contract apply to the maximum extent permitted by the relevant law of the United States.<br/>

							</div>
                            </label>
							<label class="name form-div-6" align="left">
							<span id="nselect" style="color: red;"></span>
                             
                              <span class="nagree"></span>
                            </label>
							<label class="name form-div-6" align="left">
                              <input type="checkbox" name="accept" disabled="disabled" id="selectAccept"/>
							   <input type="hidden" name="accept"> 
							  I agree to the user License agreement and privacy policy
                              <span class="empty-message">*This field is required.</span>
                              <span class="nagree"></span>
                            </label>
							<label class="name form-div-3">
                              <span id="Nagree" style="color: red;"></span>
                            </label>
							<%}else{%>
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
							&nbsp;&nbsp;Pricing of all products are stated in digiBlitz’s relevant product or platform websites. Pricing page of our product(s) and platform(s) can be changed at any time without notice. digiBlitz Inc. may limit on the number that may be purchased per order, per account, per credit card, per person or per CPU. Pricing shown in the website will include all tax and charges of the product.<br/>
							<!--  <strong>2.	AUTO RENEWAL OF THE PRODUCTS</strong><br/>
							&nbsp;&nbsp;All products of digiBlitz Inc.  will be renewed automatically at the end of each month on a prorate basis or depending up on the date of purchase (depending upon individual cases). We will also inform you about the renewal through email before the subscription of the product. Once we informed you about the auto renewal, we will charge automatically for the product. The intimation will be given before one week from the renewal to digiBlitz Inc. by the customer .We will provide you with instruction on cancelling the product service or for suspending the product. However, no intimation will be given to you for subscription renewal of any dB store components. You must cancel the services before the renewal date to avoid being billed for the renewal.<br/>-->
							<strong>2.	Return, Refund and Exchange</strong><br/>
							&nbsp;&nbsp;The return policy is provided in addition to any legal return rights that you may have under law. digiBlitz Inc. will accept returns, refunds and exchanges within 30 day after purchasing of product. We have rights to refuse any refund, return or exchange if it fails to meet our norm. For Return, Refund and Exchange the intimation should be given before one week from the renewal to digiBlitz Inc. by the customer and it will be processed within 30 days. We may occasionally extend the 15-day return period during holiday or other periods. If a longer return period was advertised through a promotional campaign on the Website when you made your purchase, that applies within the clause mentioned in the promotional campaign.<br/>
							<strong>3.	Trial-period</strong><br/> 
							&nbsp;&nbsp;If you are taking part in any trial-period, you must cancel the service before the end of the trial period to avoid incurring new charges unless we notify you otherwise. Your trial subscription will be automatically upgraded to basic pack at the end of this free subscription period. This implies that your credit card will be charged for the basic pack. However, you could unsubscribe the product any time before the trial expiry through the Master account management link that is accessible when you login into the product.<br/>

							<strong>4.	Customer support</strong><br/>
							&nbsp;&nbsp;Please visit our Knowledge base site for Customer support and Assistance relating to the product.<br/>
							<strong>5.	CHANGING TERMS</strong><br/>
							&nbsp;&nbsp;digiBlitz Inc. may change the Terms of Return, Refund and Exchange at any time and without notice to you. The Terms of Return, Refund and Exchange in force at the time you place your order will govern your purchase and serve as the purchase contract between us. Before your next purchase, digiBlitz Inc. may change their policies. We advise you to visit the digiBlitz Inc. website, to review the current terms and condition, Refund and Return policy each time. <br/>
							<strong>6.	PROTECTION OF INDIVIDUAL INFORMATION</strong><br/>
							&nbsp;&nbsp;Your privacy is important to us. We may use certain information for the purpose of your product according to your need. <br/>
							<strong>7.	LIMITATION OF LIABILITY</strong><br/>
							&nbsp;&nbsp;In this section of Contract, digiBlitz Inc. is found liable to you for any loss or damage that arises out of or is in any way connected with your use of the dB Store, the Services, or any product or service offered, you agree that your exclusive remedy is to recover from digiBlitz Inc.  or any affiliates, and vendors direct damages up to (1) an amount equal to the price or fee for one month of any service or subscription or (2) US $100 if there was no service, subscription or similar fee.<br/>
							&nbsp;&nbsp;YOU AGREE THAT YOU CAN'T RECOVER ANY OTHER DAMAGES OR LOSSES, INCLUDING, WITHOUT LIMITATION, CONSEQUENTIAL, LOST PROFITS, SPECIAL, INDIRECT, INCIDENTAL, OR CONCERNING PUNISHMENT. THESE LIMITATIONS AND EXCLUSIONS APPLY EVEN IF YOU INCUR DAMAGES AND EVEN IF WE KNEW OR SHOULD HAVE KNOWN ABOUT THE POSSIBILITY OF THE DAMAGES. THESE LIMITATIONS AND EXCLUSIONS APPLY TO ANYTHING RELATED TO THE WEBSITE OWNED AND CONTROLLED BY DIGIBLITZ INC., ITS PRODUCT (S), PLATFORM(S) OR ANY COMPONENTS OF DB STORE, OR SERVICE OFFERED<br/>

							<strong>8.	Interpreting the Contract</strong><br/>
							&nbsp;&nbsp;All parts of this Contract apply to the maximum extent permitted by the relevant law of the United States.<br/>

							</div>
                            </label>
							<label class="name form-div-6" align="left">
							<span id="nselect" style="color: red;"></span>
                             
                              <span class="nagree"></span>
                            </label>
							<label class="name form-div-6" align="left">
                              <input type="checkbox" name="accept" disabled="disabled" id="selectAccept"/>
							   <input type="hidden" name="accept"> I agree to the user License agreement and privacy policy
                              <span class="empty-message">*This field is required.</span>
                              <span class="nagree"></span>
                            </label>
							<label class="name form-div-3">
                              <span id="Nagree" style="color: red;"></span>
                            </label>
							<%}%>
                          <%
                               if(purchase.equalsIgnoreCase("try_now") && purchase != null)
                               
                               {
                               
                           %>
                            <!-- <label class="recaptcha"><span class="empty-message">*This field is required.</span></label> -->
                            <div>
							<input type="hidden" id="payOption" name="payOption" value="<%=purchase%>"/>
                              <button type="submit" class="btn-default btn3" id="submit" name="Insert" value="Register" onClick="return(onValidate());">Sign Up</a>
                            </div>
						<%} else if(purchase.equalsIgnoreCase("buy_now") && purchase != null){
                                 
                         %>
							<div>
							<input type="hidden" id="payOption" name="payOption" value="<%=purchase%>"/>
                               <button type="submit" class="btn-default btn3" name="Insert" value="Proceed" id="submit" onClick="return(onValidate());">Sign Up</a>
                            </div>
						 <%}%>
							<div>
							<input type="hidden" id="payOption" name="payOption" value="<%=purchase%>"/>
                              <a href="#" data-type="cancel" class="btn-default btn3" name="button" onclick="window.location.href='signUp.html?signUpProcess=getStarted'" value="Cancel">Cancel</a>
                            </div>

                          </fieldset> 
                          <!--div class="modal fade response-message">
                            <div class="modal-dialog">
                              <div class="modal-content">
                                <div class="modal-header">
                                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                  <h4 class="modal-title">Modal title</h4>
                                </div>
                                <div class="modal-body">
                                  You message has been sent! We will be in touch soon.
                                </div>      
                              </div>
                            </div>
                          </div-->
                    </form>
                </div>
            </div>

			<div class="row">

                <div class="col-lg-4 col-md-4 col-sm-4">
                    <div class="info">
                        <p align="right">
							<a href="https://www.paypal.com/us/verified/pal=paypal%40digiblitz%2ecom" target="_blank"><img src="https://www.paypal.com/en_US/i/icon/verification_seal.gif" border="0" alt="Official PayPal Seal"></a>
						   <a href="#" onClick="window.open('https://www.sitelock.com/verify.php?site=digiblitz.com','SiteLock','width=600,height=500,left=160,top=170');" >
							 <img alt="SiteLock" title="SiteLock" src="//shield.sitelock.com/shield/digiblitz.com" width="80px" height="40px"/>
						   </a>
						   &nbsp;
						   <span id="siteseal">
							 <script type="text/javascript" src="https://seal.godaddy.com/getSeal?sealID=rVTd0BptwwbkRS7ErqCst96o4A8qZWtbwmohz2yg6czXseVnRsCmqb4IHzw4">
							 </script>
						   </span>
					   </p>
                    </div>
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
    
    <%@ include file = "../../include/Footer.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>
    <script language="javascript">
      FillCountry(document.insert.country, document.insert.state, 'USA' );
      FillState(document.insert.country, document.insert.state, '');
      
      FillCountry(document.insert.scountry_txt, document.insert.sstate_txt, 'USA' );
      FillState(document.insert.scountry_txt, document.insert.sstate_txt, '');
      
    </script>
  </body>
  
  
  
  
                                  </html>

<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<!DOCTYPE html>
<html lang="en">
<head>
    <title>sign up success</title>
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
      
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

<script type="text/javascript">
<!-- Include JS File Here-->
window.onload = function() {
// Onload event of Javascript
// Initializing timer variable
var x = 5;
var y = document.getElementById("timer");
// Display count down for 20s
setInterval(function() {
if (x <= 6 && x >= 1) {
x--;
y.innerHTML = '' + x + '';
if (x == 1) {
x = 6;
}
}
}, 200);
// Form Submitting after 20s
var auto_refresh = setInterval(function() {
submitform();
}, 3333);
// Form submit function
function submitform() {

//alert('Form is submitting.....');
document.getElementById("form").submit();

}
// To validate form fields before submission
function validate() {
// Storing Field Values in variables
var name = document.getElementById("name").value;
var email = document.getElementById("email").value;
var contact = document.getElementById("contact").value;
// Regular Expression For Email
var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
// Conditions
if (name != '' && email != '' && contact != '') {
if (email.match(emailReg)) {
if (document.getElementById("male").checked || document.getElementById("female").checked) {
if (contact.length == 10) {
return true;
} else {
alert("The Contact No. must be at least 10 digit long!");
return false;
}
} else {
alert("You must select gender.....!");
return false;
}
} else {
alert("Invalid Email Address...!!!");
return false;
}
} else {
alert("All fields are required.....!");
return false;
}
}
};</script>
<script src="js/auto_submit.js"></script>

<script type="text/javascript" src="https://ho192.infusionsoft.com/app/webTracking/getTrackingCode?trackingId=b592766f8ddb5fc8d319956c6b7b8ce6"></script>

<!--========================================================
                          CONTENT
						  
=========================================================-->

<%String firstname=(String)request.getAttribute("fName"); 
System.out.println("FNAME.............."+firstname);%>
<%String lastname=(String)request.getAttribute("lName");
System.out.println("lastname.............."+lastname); %>
<%String e_mail=(String)request.getAttribute("emailId");
 System.out.println("e_mail.............."+e_mail);%>
<%String instituteName=(String)request.getAttribute("instituteName"); %>
<%String instituteID=(String)request.getAttribute("instituteID"); 
String transactionId = (String)request.getAttribute("transactionId");
String pay_subscriptionType=(String)request.getAttribute("pay_subscriptionType"); 
String pay_productPlan = (String)request.getAttribute("pay_productPlan");
String registrationId = (String)request.getAttribute("registrationId");
String CustomerUserName = (String)request.getAttribute("CustomerUserName");
String Customerpassword = (String)request.getAttribute("Customerpassword");
String coupon_code = (String)request.getAttribute("coupon_code");
String coupon_valid = (String)request.getAttribute("coupon_valid");
%>
<!--<%if(coupon_code != null && coupon_valid != null && coupon_valid.equalsIgnoreCase("3")){%>
<form accept-charset="UTF-8" action="https://ho192.infusionsoft.com/app/form/process/67926189c51da0a2d279b399f4fcc56b" class="infusion-form" method="POST" id="form" style="display:none">
    <input name="inf_form_xid" type="hidden" value="67926189c51da0a2d279b399f4fcc56b" />
    <input name="inf_form_name" type="hidden" value="Coupon Code Success new" />
    <input name="infusionsoft_version" type="hidden" value="1.65.0.53" />
    <div class="infusion-field" style="display:none">
        <label for="inf_field_FirstName">First Name</label>
        <input class="infusion-field-input-container" id="inf_field_FirstName" name="inf_field_FirstName" type="text"  value="<%=firstname%>"/>
    </div>
    <%if(lastname != null){%>
    <div class="infusion-field" style="display:none">
        <label for="inf_field_LastName">Last Name</label>
        <input class="infusion-field-input-container" id="inf_field_LastName" name="inf_field_LastName" type="text" value="<%=lastname%>"/>
    </div>
    <%}else{ %>
    <div class="infusion-field" style="display:none">
        <label for="inf_field_LastName">Last Name</label>
        <input class="infusion-field-input-container" id="inf_field_LastName" name="inf_field_LastName" type="text" value="N/A"/>
    </div>
    <%} %>
    <div class="infusion-field" style="display:none">
        <label for="inf_field_Username">Username *</label>
        <input class="infusion-field-input-container" id="inf_field_Username" name="inf_field_Username" type="text" value="<%=CustomerUserName%>"/>
    </div>
    <div class="infusion-field" style="display:none">
        <label for="inf_field_Password">Password *</label>
        <input class="infusion-field-input-container" id="inf_field_Password" name="inf_field_Password" type="password" value="<%=Customerpassword%>"/>
    </div>
    <div class="infusion-field" style="display:none">
        <label for="inf_field_Email">Email *</label>
        <input class="infusion-field-input-container" id="inf_field_Email" name="inf_field_Email" type="text" value="<%=e_mail%>"/>
    </div>
    <div class="infusion-field" style="display:none">
        <label for="inf_field_Company">Institution Name</label>
        <input class="infusion-field-input-container" id="inf_field_Company" name="inf_field_Company" type="text" value="<%=instituteName%>" />
    </div>
    <div class="infusion-field" style="display:none">
        <label for="inf_custom_CustomerID0">Customer ID</label>
        <input class="infusion-field-input-container" id="inf_custom_CustomerID0" name="inf_custom_CustomerID0" type="text" value="<%=instituteID%>" />
    </div>
    <div class="infusion-submit" style="display:none">
        <input type="submit" value="Ok. Proceed"  style="display:none"/>
    </div>
</form>
<%}else if(coupon_code != null){%>
<form accept-charset="UTF-8" action="https://ho192.infusionsoft.com/app/form/process/3faea5fdc7aa024d9675b2ebec091a0c" class="infusion-form" method="POST" id="form" style="display:none">
    <input name="inf_form_xid" type="hidden" value="3faea5fdc7aa024d9675b2ebec091a0c" />
    <input name="inf_form_name" type="hidden" value="Coupon Code Success" />
    <input name="infusionsoft_version" type="hidden" value="1.56.0.67" />
    <div class="infusion-field" style="display:none">
        <label for="inf_field_FirstName">First Name</label>
        <input class="infusion-field-input-container" id="inf_field_FirstName" name="inf_field_FirstName" type="text" value="<%=firstname%>"/>
    </div>
    <%if(lastname != null){%>
    <div class="infusion-field" style="display:none">
        <label for="inf_field_LastName">Last Name</label>
        <input class="infusion-field-input-container" id="inf_field_LastName" name="inf_field_LastName" type="text" value="<%=lastname%>"/>
    </div>
    <%}else{ %>
    <div class="infusion-field" style="display:none">
        <label for="inf_field_LastName">Last Name</label>
        <input class="infusion-field-input-container" id="inf_field_LastName" name="inf_field_LastName" type="text" value="N/A"/>
    </div>
    <%} %>
    <div class="infusion-field" style="display:none">
        <label for="inf_field_Username">Username *</label>
        <input class="infusion-field-input-container" id="inf_field_Username" name="inf_field_Username" type="text" value="<%=CustomerUserName%>"/>
    </div>
    <div class="infusion-field" style="display:none">
        <label for="inf_field_Password">Password *</label>
        <input class="infusion-field-input-container" id="inf_field_Password" name="inf_field_Password" type="password" value="<%=Customerpassword%>"/>
    </div>
    <div class="infusion-field" style="display:none">
        <label for="inf_field_Email">Email *</label>
        <input class="infusion-field-input-container" id="inf_field_Email" name="inf_field_Email" type="text" value="<%=e_mail%>"/>
    </div>
    <%if(instituteName != null){%>
    <div class="infusion-field" style="display:none">
        <label for="inf_field_Company">Institution Name</label>
        <input class="infusion-field-input-container" id="inf_field_Company" name="inf_field_Company" type="text" value="<%=instituteName%>"/>
    </div>
    <%}else{ %>
    <div class="infusion-field" style="display:none">
        <label for="inf_field_Company">Institution Name</label>
        <input class="infusion-field-input-container" id="inf_field_Company" name="inf_field_Company" type="text" value="N/A"/>
    </div>
    <%} %>
    <div class="infusion-field" style="display:none">
        <label for="inf_custom_CustomerID0">Customer ID</label>
        <input class="infusion-field-input-container" id="inf_custom_CustomerID0" name="inf_custom_CustomerID0" type="text" value="<%=instituteID%>"/>
    </div>
    <div class="infusion-submit" style="display:none">
        <input type="submit" value="Ok. Proceed" style="display:none"/>
    </div>
</form>
<%}else{ %>
<form accept-charset="UTF-8" action="https://ho192.infusionsoft.com/app/form/process/7f440b698cdc29d0b7ac0b7448824d09" class="infusion-form" method="POST" id="form" style="display:none">
    <input name="inf_form_xid" type="hidden" value="7f440b698cdc29d0b7ac0b7448824d09"/>
    <input name="inf_form_name" type="hidden" value="Credit card Details validation&#a;- Success Scenario"/>
    <input name="infusionsoft_version" type="hidden" value="1.49.0.36" />
    <div class="infusion-field" style="display:none">
        <label for="inf_field_FirstName">First Name</label>
        <input class="infusion-field-input-container" id="inf_field_FirstName" name="inf_field_FirstName" type="hidden" value="<%=firstname%>"/>
    </div>
    <%if(lastname != null){%>
    <div class="infusion-field" style="display:none">
        <label for="inf_field_LastName">Last Name</label>
        <input class="infusion-field-input-container" id="inf_field_LastName" name="inf_field_LastName" type="text" value="<%=lastname%>"/>
    </div>
    <%}else{ %>
    <div class="infusion-field" style="display:none">
        <label for="inf_field_LastName">Last Name</label>
        <input class="infusion-field-input-container" id="inf_field_LastName" name="inf_field_LastName" type="text" value="N/A"/>
    </div>
    <%} %>
    <div class="infusion-field" style="display:none">
        <label for="inf_field_Email">Email *</label>
        <input class="infusion-field-input-container" id="inf_field_Email" name="inf_field_Email" type="hidden" value="<%=e_mail%>"/>
    </div>
    <div class="infusion-field">
        <label for="inf_field_Username">Username *</label>
        <input class="infusion-field-input-container" id="inf_field_Username" name="inf_field_Username" type="text" value="<%=CustomerUserName%>"/>
    </div>
    <div class="infusion-field">
        <label for="inf_field_Password">Password *</label>
        <input class="infusion-field-input-container" id="inf_field_Password" name="inf_field_Password" type="password" value="<%=Customerpassword%>"/>
    </div>
    <%if(instituteName != null){%>
    <div class="infusion-field" style="display:none">
        <label for="inf_field_Company">Institution Name</label>
        <input class="infusion-field-input-container" id="inf_field_Company" name="inf_field_Company" type="text" value="<%=instituteName%>"/>
    </div>
    <%}else{ %>
    <div class="infusion-field" style="display:none">
        <label for="inf_field_Company">Institution Name</label>
        <input class="infusion-field-input-container" id="inf_field_Company" name="inf_field_Company" type="text" value="N/A"/>
    </div>
    <%} %>
    <div class="infusion-field" style="display:none">
        <label for="inf_custom_CustomerID0">Customer ID</label>
        <input class="infusion-field-input-container" id="inf_custom_CustomerID0" name="inf_custom_CustomerID0" type="hidden" value="<%=instituteID%>"/>
    </div>
    <div class="infusion-field" style="display:none">
        <label for="inf_custom_Subscriptiontype">Subscription Type</label>
        <input class="infusion-field-input-container" id="inf_custom_Subscriptiontype" name="inf_custom_Subscriptiontype" type="hidden" value="<%=pay_subscriptionType%>"/>
    </div>
    <div class="infusion-field" style="display:none">
        <label for="inf_custom_PaymentPlan">Payment Plan</label>
        <input class="infusion-field-input-container" id="inf_custom_PaymentPlan" name="inf_custom_PaymentPlan" type="hidden" value="<%=pay_productPlan%>"/>
    </div>
    <div class="infusion-submit" style="display:none">
        <input type="submit" value="Ok. Proceed" style="display:none"/>
    </div>
</form>
<%} %>
--->
<section id="content">
<div class="content">
    
	
	
	
	<div class="thumb-box10">
        <div class="container">
            <div class="row">
                <div class="col-lg-5 col-md-5 col-sm-5">
                    <div class="wow fadeInUp clearfix">
                        <div class="box1 maxheight2">
                            <p class="title">Congrats &nbsp;<%=firstname %>!</p>
                            <p class="description">You're ready to start using menschFoce Online Edition</p>
							<p class="description">We'll send you a welcome email shortly with some quick tips to get started easily.</p>
                            <p><strong>Login Details</strong></p>
                            <ul class="list2-1">
                            <%if(lastname != null){%>
                                <li><strong>Name: </strong><a href="#"><%=firstname %>&nbsp;<%=lastname %></a></li>
                                <%}else{ %>
                                <li><strong>Name: </strong><a href="#"><%=firstname %></a></li>
                                <%} %>
                                <li><strong>E-mail: </strong><a href="#"><%=e_mail %></a></li>
                                <p><strong>Institution Information</strong></p>
                                <li><strong>User Name: </strong><a href="#"><%=CustomerUserName%></a></li>
                                <li><strong>password:</strong><a href="#">Password will be send to your e-mail</a></li>
                                <li><strong>Registration Id: </strong><a href="#"><%=registrationId%></a></li>
                                <%if(instituteName != null){%>
                                <li><strong>Institution Name: </strong><a href="#"><%=instituteName%></a></li>
                                <%}else{%>
                                <li><strong>Institution Name: </strong><a href="#">N/A</a></li>
                                <%} %>
								<li><strong>Institution ID: </strong><a href="#"><%=instituteID%></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                
            </div>
        </div>
        <em></em>
    </div>
	
	
    </div>

</section>


<!--========================================================
                          FOOTER
=========================================================-->
<div>			 
			
                    <!-- FOOTER STARTS HERE -->
                        
						<%@ include file = "../../include/Footer.jsp" %>
                    <!-- FOOTER ENDS HERE -->
               
 </div>

</body>

   


</html>

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
<html lang="en">
<head>
<title>Contacts</title>
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<script type="text/javascript">
function ValidateEmail(inputText)
{
var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
if(inputText.value.match(mailformat))
{
document.form1.email.focus();
return true;
}
else
{
//alert("You have entered an invalid email address!");
document.getElementById("emailError").innerHTML="You have entered an invalid email address!";
document.form1.email.focus();
return false;
}
}
</script>
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
document.getElementById("contactForm").submit();

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
</head>
<body>
<!--header-->
<div id="header">
<!-- HEADER STARTS HERE -->
<%@ include file = "../../include/header_new.jsp" %>
  <!-- HEADER ENDS HERE -->
</div>
	<%	String name = (String) request.getAttribute("name");
		String email = (String) request.getAttribute("email");
		String phone = (String) request.getAttribute("phone");
		String company = (String) request.getAttribute("company");
		String message = (String) request.getAttribute("message");
	%>
	
<!-- infusion soft code start here -->
<%String dbinitStatus = null;
dbinitStatus = (String)request.getAttribute("status");
System.out.println("dbinitStatus inside jsp ::::::::::::::::"+dbinitStatus);
if(dbinitStatus != null && !(dbinitStatus.equalsIgnoreCase("init"))){

%>
	<form accept-charset="UTF-8" action="https://ho192.infusionsoft.com/app/form/process/c6ce65cc4cc23827a80a4e6df00eb0a0" class="infusion-form" method="POST" id="contactForm" style="display: none;" name="Contact us" onSubmit="var form = document.forms[0];
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
<input name="inf_form_xid" type="hidden" value="c6ce65cc4cc23827a80a4e6df00eb0a0" /><input name="inf_form_name" type="hidden" value="Contact us" /><input name="infusionsoft_version" type="hidden" value="1.49.0.36" />
<div class="default beta-base beta-font-b" id="mainContent" style="height:100%">
<table cellpadding="10" cellspacing="0" class="background" style="width: 100%; height: 100%">
<tbody>
<tr>
<td align="center" valign="top">
<table bgcolor="#FFFFFF" cellpadding="20" cellspacing="0" class="bodyContainer webFormBodyContainer" width="100%">
<tbody>
<tr>
<td bgcolor="#FFFFFF" class="body" sectionid="body" valign="top">
<div class="text" id="webformErrors" name="errorContent">
</div>
<div>
<div>
<div class="title" style="text-align:left">
<div class="title" contentid="title" style="text-align: left;">

</div>
</div>
</div>
</div>
<div>
<table class="infusion-field-container" style="width:100%;">
<tbody>
<tr>
<td class="infusion-field-label-container">
<label for="inf_field_FirstName">Name *</label>
</td>
<td class="infusion-field-input-container" style="width:200px;">
<input class="infusion-field-input-container" id="inf_field_FirstName" name="inf_field_FirstName" type="text" value="<%=name%>"/>
</td>
</tr>
</tbody>
</table>
</div>
<div>
<table class="infusion-field-container" style="width:100%;">
<tbody>
<tr>
<td class="infusion-field-label-container">
<label for="inf_field_Email">Email *</label>
</td>
<td class="infusion-field-input-container" style="width:200px;">
<input class="infusion-field-input-container" id="inf_field_Email" name="inf_field_Email" type="text" value="<%=email%>"/>
</td>
</tr>
</tbody>
</table>
</div>
<div>
<table class="infusion-field-container" style="width:100%;">
<tbody>
<tr>
<td class="infusion-field-label-container">
<label for="inf_field_Phone1">Phone *</label>
</td>
<td class="infusion-field-input-container" style="width:200px;">
<input class="infusion-field-input-container" id="inf_field_Phone1" name="inf_field_Phone1" type="text" value="<%=phone%>"/>
</td>
</tr>
</tbody>
</table>
</div>
<div>
<table class="infusion-field-container" style="width:100%;">
<tbody>
<tr>
<td class="infusion-field-label-container">
<label for="inf_field_Company">Company *</label>
</td>
<td class="infusion-field-input-container" style="width:200px;">
<input class="infusion-field-input-container" id="inf_field_Company" name="inf_field_Company" type="text" value="<%=company%>"/>
</td>
</tr>
</tbody>
</table>
</div>
<div>
<table class="infusion-field-container" style="width:100%;">
<tbody>
<tr>
<td class="infusion-field-label-container">
<label for="inf_custom_DescriptionOfNeed">Brief description of your requirement *</label>
</td>
<td class="infusion-field-input-container" style="width:200px;">
<input class="infusion-field-input-container" id="inf_custom_DescriptionOfNeed" name="inf_custom_DescriptionOfNeed" type="text" value="<%=message%>"/>
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
<button style="" type="submit" value="Submit">Submit</button>
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
<%}%>

<!-- infusion soft code end here -->
<div class="content indent">
    <div class="container">
        <section>
             
                <div><iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3102.759171003148!2d-77.40540988471282!3d38.952329851309486!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89b6479363b7080d%3A0xf0ba0347f9e7a599!2s13241+Woodland+Park+Rd+%23110%2C+Herndon%2C+VA+20171%2C+USA!5e0!3m2!1sen!2sin!4v1463140827940" width="1200" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>              </div> 
        </section>
    </div>
    <div class="thumb-box14">
        <div class="container">
            <div class="row">
                
				<%String dbStatus = (String)request.getAttribute("status");
				System.out.println("inside jsp::::::::::::::::::::::");%>
				<%
				if(dbStatus != null && dbStatus.equalsIgnoreCase("success")){%>
				
				<div>Thank You!</div>
				<%}else if(dbStatus != null && dbStatus.equalsIgnoreCase("fails")){%>
				<div>Failed! Please try again</div>
				<%} else if(dbStatus != null && dbStatus.equalsIgnoreCase("init")){%>
				<div></div>
				<%}%>
                <div class="col-lg-8 col-md-8 col-sm-8">
                    <h3>Write to us</h3>
                    <form id="contact-form" action="user.html?cmd=eConfig" method="post" onSubmit="return(ValidateEmail(document.form1.email));" name="form1">
                         
                          <fieldset>
                            <label class="name form-div-1">
                              <input type="text" name="name" placeholder="Name:" value="" required/>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
                            <label class="email form-div-2">
                              <input type="text" name="email" placeholder="E-mail:" value="" required/>
                              <span class="empty-message">*This field is required.</span>
                              <span id="emailError" style="color:#FF0000"></span>
                            </label>
                            <label class="phone form-div-3">
                              <input type="text" name="phone" placeholder="Phone:" value="" required/>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid phone.</span>
                            </label>
                            <label class="company form-div-5">
                              <input type="text" name="company" placeholder="Company:" value="" required/>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
                            <label class="message form-div-4">
                              <textarea name="message" placeholder="Message:" required/></textarea>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*The message is too short.</span>
                            </label>
                            <!-- <label class="recaptcha"><span class="empty-message">*This field is required.</span></label> -->
                            <div>
                             <input type="submit" value="submit" name="submit" class="btn-default btn3"/>
                            </div>
                          </fieldset> 
                          <div class="modal fade response-message">
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
                          </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!--footer-->
<div>
     <%@ include file = "../../include/Footer.jsp" %>
</div>


</body>
</html>

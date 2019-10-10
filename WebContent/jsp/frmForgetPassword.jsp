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
    <%@ page import = "javax.sql.*" %>
<%@ page import = "java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>menschForce</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<script src="js/basic.js" type="text/javascript" ></script>
<script src="js/forgotPassword.js" type="text/javascript" ></script>

<!-- Include JS File Here-->

<!-- <script type="text/javascript">

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
document.getElementById("forgotUserPassword").submit();

}
// To validate form fields before submission

};</script> -->

  
 <script language="javascript" />
function dispQDiv(){

	document.getElementById('secretQuest').style.display = "";
document.getElementById('chkEmail').style.display="none";
		}
		
		function submitform(paramValue)
{

if(paramValue!=null && paramValue!="null"){
document.getElementById('chkEmail').style.display="block";
}
}
</script>


</head>


<body onload="dispQDiv();">

<header id="header">
<!-- HEADER STARTS HERE -->
<%@ include file = "../../include/header_new.jsp" %>
  <!-- HEADER ENDS HERE -->
</header>

<!-- <%
								String infuStatusSuccess=(String)request.getAttribute("status");
                                                                System.out.println("idstatus from jsp :" +infuStatusSuccess);
																
								String usrPass = (String)request.getAttribute("pass");
								String usrEmail = (String)request.getAttribute("emailid");
								String usrname = (String)request.getAttribute("usrname");
									
								if(infuStatusSuccess!=null)
								{
									if(infuStatusSuccess.equalsIgnoreCase("success"))
									{
									
									%>

<form accept-charset="UTF-8" action="https://ho192.infusionsoft.com/app/form/process/305bd2c3b81c0bf3c227e5d4c87df0a5" style="display:none" id="forgotUserPassword" class="infusion-form" method="POST" name="Forget password
mf" onsubmit="var form = document.forms[0];
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
<input name="inf_form_xid" type="hidden" value="305bd2c3b81c0bf3c227e5d4c87df0a5" /><input name="inf_form_name" type="hidden" value="Forget password
mf" /><input name="infusionsoft_version" type="hidden" value="1.51.0.54" />
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
<div class="text">
<div class="text" contentid="paragraph">
<div>
<div style="text-align: center;">
<span data-mce-mark="1" style="color: #0000ff; font-size: 14pt;"><span data-mce-mark="1" style="">Welcome to menschForce</span></span><span data-mce-mark="1" style="color: #000000;">.</span>
</div>
<div style="text-align: center;">
<span data-mce-mark="1" style="color: #000000;">we wish you happy days ahead with our product.</span>
</div>
</div>
</div>
</div>
</div>
<div>
<table class="infusion-field-container" style="width:100%;">
<tbody>
<tr>
<td class="infusion-field-label-container">
<label for="inf_field_Username">Username *</label>
</td>
<td class="infusion-field-input-container" style="width:200px;">
<input class="infusion-field-input-container" id="inf_field_Username" name="inf_field_Username" type="text" value="<%=usrname %>" />
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
<label for="inf_custom_ProductName">Password *</label>
</td>
<td class="infusion-field-input-container" style="width:200px;">
<input class="infusion-field-input-container" id="inf_custom_ProductName" name="inf_custom_ProductName" type="text" value="<%=usrPass %>" />
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
<input class="infusion-field-input-container" id="inf_field_Email" name="inf_field_Email" type="text" value="<%=usrEmail%>" />
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
<button style="background-color:#F5F5F5; color:#000000; font-size:15px; font-family:Helvetica; border-color:#000000; border-style:Solid; border-width:1px; -moz-border-radius:3px;border-radius:3px;" type="submit" value="Ok. Proceed">Ok. Proceed</button>
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
<%}}%> 

   <%
								String idstatus2=(String)request.getAttribute("status1");
                                                                System.out.println("idstatus from jsp :" +idstatus2);
																
								String usrEmail1 = (String)request.getAttribute("email");
								String usrname1 = (String)request.getAttribute("username");
									
								if(idstatus2!=null)
								{
									if(idstatus2.equalsIgnoreCase("success"))
									{%>
<form accept-charset="UTF-8" action="https://ho192.infusionsoft.com/app/form/process/817835aa398d081eabe8d6e4443392d0" id="forgotUserPassword" style="display:none;" class="infusion-form" method="POST" name="Forget Username" onsubmit="var form = document.forms[0];
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
<input name="inf_form_xid" type="hidden" value="817835aa398d081eabe8d6e4443392d0" /><input name="inf_form_name" type="hidden" value="Forget Username" /><input name="infusionsoft_version" type="hidden" value="1.49.0.36" />
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
<div class="text">
<div class="text" contentid="paragraph">
<div>
<div style="text-align: center;">
<span data-mce-mark="1" style="color: #0000ff; font-size: 14pt;"><span data-mce-mark="1" style="">Welcome to menschForce</span></span><span data-mce-mark="1" style="color: #000000;">.</span>
</div>
<div style="text-align: center;">
<span data-mce-mark="1" style="color: #000000;">we wish you happy days ahead with our product.</span>
</div>
</div>
</div>
</div>
</div>
<div>
<table class="infusion-field-container" style="width:100%;">
<tbody>
<tr>
<td class="infusion-field-label-container">
<label for="inf_field_Username">Username *</label>
</td>
<td class="infusion-field-input-container" style="width:200px;">
<input class="infusion-field-input-container" id="inf_field_Username" name="inf_field_Username" type="text" value="<%=usrname1%>" />
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
<input class="infusion-field-input-container" id="inf_field_Email" name="inf_field_Email" type="text" value="<%=usrEmail1%>" />
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
<button style="background-color:#F5F5F5; color:#000000; font-size:15px; font-family:Helvetica; border-color:#000000; border-style:Solid; border-width:1px; -moz-border-radius:3px;border-radius:3px;" type="submit" value="Ok. Proceed">Ok. Proceed</button>
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
<%}}%> -->

	
      <div class="content indent">
    	<div class="thumb-box14">
        		<div class="container">
           			 <div class="row">
					 
					 
					 <div class="col-lg-12 col-md-12 col-sm-12">
						<h3 class="title"> Sign-In Problems</h3>
				  
					</div>
					 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-2" id="commonBG">
						
							<p>If you've forgotten your Password, you can retrieve it by filling in your username and answering to the secret question you had given during your Registration Process. If you've forgotten the USERNAME to your account,please enter your  e-Mail ID. Your USERNAME would be mailed to this ID. </p>
						
				   </div>
				 </div>
    					<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">
								
								<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-3" id="commonBG">
									<label class="name form-div-6 subtitle">
										<img src="img/forget-pw_icon.png" alt="" width="18px" height="18px" />FORGOT Password?
									</label>
								</div>
						</div>
                                 
                                  <form name="frmForgetPwd1" id="frmForgetPwd1" action="user.html?cmd=User" method="post" onsubmit="return myvalidate(this);" />
                                 
                                  <%
								String idstatus=(String)request.getAttribute("idstatus");
                                                                System.out.println("idstatus from jsp :" +idstatus);
									
								if(idstatus!=null)
								{
									if(idstatus.equalsIgnoreCase("fail"))
									{%>
								<div class="col-lg-12 col-md-12 col-sm-12">
										&nbsp;
									</div>
								 <div class="col-lg-12 col-md-12 col-sm-12">
					
									<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-3" id="commonBG"> 
                                    <label class="name form-div-6" align="left">
                                    <font color="#FF0000">Sorry, your details does not match!</font>
                                    </label>
									</div>
								</div>
									<div class="col-lg-12 col-md-12 col-sm-12">
										&nbsp;
									</div>
                                  
                                  <%}
								
								}    %>
								
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>	
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3" id="commonBG">
								<label class="name form-div-6">
									Username <span class="asterisk">*</span>
								</label>
							</div>
								<div class="col-lg-3 col-md-3 col-sm-3 ">	
									<input name="usrname" id="usrname" type="text" placeholder="UserName" class="form-control"   onblur="usrStat();" />
											  
											  </label>
								 </div>
							
						</div>
                                  
                           <div class="col-lg-12 col-md-12 col-sm-12">
								&nbsp;
							</div>
							
						 <div class="col-lg-12 col-md-12 col-sm-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3" id="commonBG">
									<label class="name form-div-6">
										Your Secret Question
									</label>
								</div>
								<div class="col-lg-3 col-md-3 col-sm-3 ">	
									<div id="secretQuest">
										 <input name="sQustion" id="sQustion" type="text" readonly="readonly" placeholder=" Your Secret Question:" class="form-control" /> 
									</div>
								</div>
						 </div>                                       
                          <div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>              
						 <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3" id="commonBG">
								<label class="name form-div-6">
									Enter your Answer  <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3 ">					
									<input name="sAnswer" type="text" placeholder=" Enter Your Answer:" class="form-control" id="sAnswer"  />
                             </div>
						</div>
										 
                           <div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>             
                         <div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-5" id="commonBG">
								<label class="name form-div-6">
                                    <button name="log" type="submit" class="button-add" style="width:130px;height:50px" value="Send My Password" >Send My Password</button>
                                 </label> 
							</div>
						</div>
									 
								<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>	
                                    
                                  
                               
                                    <div id="noSecretQuest">
                                     
                                        <%
								String idstatus1=(String)request.getAttribute("status");
                                                                System.out.println("idstatus from jsp :" +idstatus1);
									
								if(idstatus1!=null)
								{
									if(idstatus1.equalsIgnoreCase("success"))
									{%>
								<div class="col-lg-12 col-md-12 col-sm-12">
									&nbsp;
								</div>	
										  
								  <div class="col-lg-12 col-md-12 col-sm-12">
						
									<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-3"   >
									    
                                      <p>  <font color="#009933"> Congrats! Your Username matches. Your Password will be sent to your e-Mail matching this username.</font> </p>
                                        
										
										
										<a href="login.html?cmd=initLogin" ><img src="img/signIn_icon.jpg" alt="" width="20px" height="20px"/>&nbsp;<strong>Sign In</strong></a>									
									</div>
									</div>
										 <%}
								
								}    %>
                                       
                                    </div>
                                 
								</div>
								</div>
                                
                           
							</form>   
                              
                       <div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
                          <div class="col-lg-12 col-md-12 col-sm-12">
					
							<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-3" id="commonBG">
								<label class="name form-div-6 subtitle">
                              
								 <img src="img/forget-usrname_icon.jpg" alt="" width="18px" height="18px" />FORGOT Username
							  </label>
							  </div>
						</div>
                                      
                                <form action="user.html?cmd=Password" method="post" name="frmForgetPwd2" id="frmForgetPwd2" onsubmit="return myvalidate2(this);" />
                                     
                                    <%
									String Passwordstatus=(String)request.getAttribute("status");
                                                                        System.out.println("status from jsp :" +status3);
                                                                        
									if(Passwordstatus!=null)
									{
										if(Passwordstatus.equalsIgnoreCase("fail"))
										{%>
                                     	<div class="col-lg-12 col-md-12 col-sm-12">
										&nbsp;
									</div>	
										  
								  <div class="col-lg-12 col-md-12 col-sm-12">
						
									<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-3">   
                                        <font color="#FF0000">Sorry, your details does not match!</font>
                                      </div>
									 </div>
									 <div class="col-lg-12 col-md-12 col-sm-12">
										&nbsp;
									</div>	
                                      <%}
									}%>
									
							<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
							 <div class="col-lg-12 col-md-12 col-sm-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3" id="commonBG">
									<label class="name form-div-6">
										E-Mail <span class="asterisk">*</span>
									</label>
								</div>
									<div class="col-lg-3 col-md-3 col-sm-3 ">	
                                 
										<input name="e_mail" type="text" placeholder=" e-Mail: " class="form-control" onblur="submitform(this.value);"/>
										 <font color="#018dce">  For example:<strong>person</strong>@domainname.com </font>
									</label>
								</div>
								</div>
                                     
							
									<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>  
                                      
									  
									  <div class="col-lg-12 col-md-12 col-sm-12">
											
											<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-5" id="commonBG">
											
												<button name="log" type="submit" class="button-add" style="width:130px;height:50px" value="Send My Username" >Send My Username</button>
											</div>
									 </div>
									
									<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div> 
								
									   <%
								String idstatus3=(String)request.getAttribute("status1");
                                                                System.out.println("idstatus from jsp :" +idstatus3);
																
																String pw=(String)request.getAttribute("password");
																System.out.println("password from jsp :" +pw);
									
								if(idstatus2!=null)
								{
									if(idstatus2.equalsIgnoreCase("success"))
									{%>
									<div class="col-lg-12 col-md-12 col-sm-12">
										&nbsp;
									</div>	
										  
								  <div class="col-lg-12 col-md-12 col-sm-12">
						
									<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-3">    
									<p><font color="#009933"> Congrats! Your e-Mail matches. Your username will be sent to your e-Mail.</font>	</p>									
                                        
										 <label class="name form-div-6" align="left">
										<a href="login.html?cmd=initLogin" ><img src="img/signIn_icon.jpg" alt="" width="20px" height="20px"/>&nbsp;<strong>Sign In</strong></a>									
										</label>
									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12">
										&nbsp;
									</div>	
										 <%}
								
								}    %>
								
								
								
								
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
  
</body>
</html>

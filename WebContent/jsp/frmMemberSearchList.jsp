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
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>menschforce</title>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<script src="js/basic.js" type="text/javascript" ></script>
<script src="js/frmMembRegi.js" type="text/javascript" ></script>
 <script  src="js/ts_picker.js"></script>
 
    <!--CSS-->
<link rel="stylesheet" href="css/bootstrap.css" >
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/camera.css">
<link rel="stylesheet" href="css/jquery.fancybox.css">
<link rel="stylesheet" href="fonts/font-awesome.css">

<!--JS-->
<script src="js/jquery.js"></script>
<script src="js/jquery-migrate-1.2.1.min.js"></script>
<script src="js/jquery.easing.1.3.js"></script>
<script src="js/jquery.mobilemenu.js"></script>
<script src="js/jquery.equalheights.js"></script> 
<script src="js/camera.js"></script>
<!--[if (gt IE 9)|!(IE)]><!-->
<script src="js/jquery.mobile.customized.min.js"></script>
<!--<![endif]-->
<script src="js/jquery.fancybox.pack.js"></script>
<script src="js/jquery.carouFredSel-6.1.0-packed.js"></script>
<script src="js/jquery.touchSwipe.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/tm-scripts.js"></script>
<script type="text/javascript">
function isnotAlpha(str){
	stringCheck="!@#$%^&*()_+|<>?/=~,`0123456789;:][{}"+"\\"+"\"";
	f1=1;
	for(j=0;j<str.length;j++){
		if(stringCheck.indexOf(str.charAt(j))!=-1){
			f1=0;
		}
	}
	if(f1==0){
		return true; 
	}
	else {
		return false;
	}
}
function isnotSpecial(str){
	stringSpecialCheck="!#$%^&*()+|<>?/=~,;:][{}"+"\\"+"\'";
	f4=1;
	for(j=0;j<str.length;j++){
		if(stringSpecialCheck.indexOf(str.charAt(j))!=-1){
			f4=0;
		}
	}
	if(f4==0){
		return true;
	}
	else{
		return false;
	}
}
function isnotZipcode(str){
	stringZip="0123456789";
	fzip=1;
	for(j=0;j<str.length;j++){
		if(stringZip.indexOf(str.charAt(j))!=-1){
			fzip=0;
		}
	}
	if(fzip==0){
		return false;
	}
	else{
	return true;
	}	
}


function myValidate(){
// function for triming the value
String.prototype.trim = function() {
return this.replace(/^\s+|\s+$/g,"");
}	

var memID=document.frmUserSignup.memberId.value;
var rolID=document.frmUserSignup.roleId.value;
var stDate=document.frmUserSignup.frmDate.value;
var endDate=document.frmUserSignup.toDate.value;
var logName=document.frmUserSignup.loginName.value;
var firstName=document.frmUserSignup.fname.value;
var lastName=document.frmUserSignup.lname.value;
var emailVal=document.frmUserSignup.email.value;
var zip=document.frmUserSignup.zip.value;
	if((memID.trim()=="") && (rolID.trim()=="") && (stDate=="")&& (endDate=="") && (logName.trim()=="") &&
		(firstName.trim()=="") && (lastName.trim()=="") && 
		(emailVal.trim()=="") && (zip.trim()=="") ) {
			alert("Please Provide Any One Of The Information");
			document.frmUserSignup.memberId.focus();
			return false;
		}
		
/*		
		if(((document.frmUserSignup.memberId.value.indexOf('  ')!=-1)||(document.frmUserSignup.memberId.value.indexOf(' ')==0)) && 
		(document.frmUserSignup.loginName.value.indexOf('  ')!=-1)||(document.frmUserSignup.loginName.value.indexOf(' ')==0)) && 
		(document.frmUserSignup.fname.value.indexOf('  ')!=-1)||(document.frmUserSignup.fname.value.indexOf(' ')==0)) &&
		(document.frmUserSignup.lname.value.indexOf('  ')!=-1)||(document.frmUserSignup.lname.value.indexOf(' ')==0)) &&
		(document.frmUserSignup.email.value.indexOf('  ')!=-1)||(document.frmUserSignup.email.value.indexOf(' ')==0)) &&
		(document.frmUserSignup.zip.value.indexOf('  ')!=-1)||(document.frmUserSignup.zip.value.indexOf(' ')==0))){
			alert("Please Provide Any One Of The Information");
			return false;
		}
		*/
		
		
       if(memID.trim()!=""){
			/*if((document.frmUserSignup.memberId.value.indexOf('  ')!=-1)||(document.frmUserSignup.memberId.value.indexOf(' ')==0)){
				alert("Member Id is not valid");
				document.frmUserSignup.memberId.focus();
				return false;
			}*/
			if(isnotSpecial(memID.trim())){
				alert("Member Id is not valid");
				document.frmUserSignup.memberId.focus();
				return false;
			}
			if(memID.trim().length > 80){
				alert("Member Id is not valid");
				document.frmUserSignup.memberId.focus();
				return false;
			}
		}
		
	
	    /*if((document.frmUserSignup.fname.value.indexOf('  ')!=-1)||(document.frmUserSignup.fname.value.indexOf(' ')==0)){
			alert("First Name is not valid");
			document.frmUserSignup.fname.focus();
			return false;
		}*/
		
		 
 var stDate = document.frmUserSignup.frmDate.value;
var enDate = document.frmUserSignup.toDate.value;
//alert("stDate"+stDate);
//alert("enDate"+enDate);
var sDate = new Date();
//alert(stDate.substring(0,2));
//alert(stDate.substring(3,5));
//alert(stDate.substring(6,10));

sDate.setMonth(stDate.substring(0,2)-1);
sDate.setDate(stDate.substring(3,5));
sDate.setYear(stDate.substring(6,10));

var eDate = new Date();
eDate.setMonth(enDate.substring(0,2)-1);
eDate.setDate(enDate.substring(3,5));
eDate.setYear(enDate.substring(6,10));


var stTime = sDate.getTime();
var enTime = eDate.getTime();


if(enTime<stTime){
	alert("Select valid To Date");
	document.frmUserSignup.toDate.focus();
	return false;
}
 
 
 if(stDate!=""){
 if(enDate==""){
alert("Please select To Date");
document.frmUserSignup.toDate.focus();
return false;
}
 
 
 
 }
         if(firstName.trim()!=""){
        if(firstName.trim().length <2){
			alert("Please provide atleast two characters");
			document.frmUserSignup.fname.focus();
			return false;
		}
 }
 
		if(firstName.trim().length > 80){
			alert("First Name is not valid");
			document.frmUserSignup.fname.focus();
			return false;
		}
 		 
		if(isnotAlpha(lastName.trim())){
			alert("Last Name is not valid");
			document.frmUserSignup.lname.focus();
			return false;
		}
		/*if((document.frmUserSignup.lname.value.indexOf('  ')!=-1)||(document.frmUserSignup.lname.value.indexOf(' ')==0)){
			alert("Last Name is not valid");
			document.frmUserSignup.lname.focus();
			return false;
		}*/	
		if(lastName.trim().length > 80){
			alert("Last Name is not valid");
			document.frmUserSignup.lname.focus();
			return false;
		}
		 
		if(!(emailVal.trim()== "")){
			strmail=emailVal.trim();
			firstat = strmail.indexOf("@");
			lastat = strmail.lastIndexOf("@");
			strmain=strmail.substring(0,firstat);
			strsub=strmail.substring(firstat,emailVal.trim().length);
			if(strmail.length>120){
				alert("Email is out of range");
				document.frmUserSignup.email.focus();
				return false;
			}
			/*if(strmain.indexOf('  ')!=-1 || firstat==0 || strsub.indexOf(' ')!=-1 ){
				alert("Enter valid Email ");
				document.frmUserSignup.email.focus();
				return false;
			}*/
			if(isnotSpecial(strmain) || isnotSpecial(strsub)){
				alert("Enter valid Email ");
				document.frmUserSignup.email.focus();
				return false;
			}
			k=0;
			strlen=strsub.length;
			for(i=0;i<strlen-1;i++){
				if(strsub.charAt(i)=='.'){
					k=k+1;
				}
			}
			if(k>3){
				alert("Enter valid Email ");
				document.frmUserSignup.email.focus();
				return false;
			}
			/*if(firstat==-1 || lastat==-1){
				alert("Enter valid Email " );
				document.frmUserSignup.email.focus();
				return false;
			}*/
			if(Number(strmain)){
				alert("Enter valid Email ");
				document.frmUserSignup.email.focus();
				return false;
			}
			if(firstat != lastat ){
				alert("Enter valid Email ");
				document.frmUserSignup.email.focus();
				return false;
			}
			firstdot=strmail.indexOf(".",firstat);
			lastdot=strmail.lastIndexOf(".");
			/*if(firstdot == -1 || lastdot == -1 || lastdot==strmail.length-1){
				alert("Enter valid Email ");
				document.frmUserSignup.email.focus();
				return false;
			}*/
		}
		if(zip.trim()!=""){
			
			if(isnotSpecial(zip.trim())){
				alert("ZipCode is not valid");
				document.frmUserSignup.zip.focus();
				return false;
			}
			if(zip.trim().length > 20){
				alert("ZipCode is not valid");
				document.frmUserSignup.zip.focus();
				return false;
			}
		}
			if(logName.trim()!=""){
			/*if((document.frmUserSignup.loginName.value.indexOf('  ')!=-1)||(document.frmUserSignup.loginName.value.indexOf(' ')==0)){
				alert("Login Name is not valid");
				document.frmUserSignup.loginName.focus();
				return false;
			}*/
			if(isnotSpecial(logName.trim())){
				alert("Login Name is not valid");
				document.frmUserSignup.loginName.focus();
				return false;
			}
			if(logName.trim().length > 80){
				alert("Login Name is not valid");
				document.frmUserSignup.loginName.focus();
				return false;
			}
		}
		
		
	return true;
 }
 
 
function searchSec(){

document.getElementById('showSearchCrite').style.display="none";
document.getElementById('memShow').style.display="none";

document.getElementById('dtShow').style.display="none";
document.getElementById('commShow').style.display="none";


}

 function submitform(paramValue)
{

if(paramValue!=null && paramValue!="null"){

if(paramValue=="members"){
document.getElementById('showSearchCrite').style.display="block";
document.getElementById('memShow').style.display="block";
document.getElementById('dtShow').style.display="block";
document.getElementById('commShow').style.display="block";

document.frmUserSignup.memberId.value="";
document.frmUserSignup.roleId.value="";
document.frmUserSignup.frmDate.value="";
document.frmUserSignup.toDate.value="";
document.frmUserSignup.fname.value="";
document.frmUserSignup.lname.value="";
document.frmUserSignup.email.value="";
document.frmUserSignup.zip.value="";
document.frmUserSignup.loginName.value="";



}else if(paramValue=="nonMembers"){

document.getElementById('showSearchCrite').style.display="block";
document.getElementById('memShow').style.display="none";
document.getElementById('dtShow').style.display="block";
document.getElementById('commShow').style.display="block";

document.frmUserSignup.frmDate.value="";
document.frmUserSignup.toDate.value="";
document.frmUserSignup.fname.value="";
document.frmUserSignup.lname.value="";
document.frmUserSignup.email.value="";
document.frmUserSignup.zip.value="";
document.frmUserSignup.loginName.value="";


}

}

}
 
 
//------------------------------ Member Registration Member Id Existance Validate Ajax Script -------------------------

var arHttpRequest;

function HLCMemberDetails()
{

	if(document.frmUserSignup.memberId.value!="" && document.frmUserSignup.memberId.value.indexOf(" ")!=0)
	{
    var memberid=document.frmUserSignup.memberId.value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
            
        var url = "./RiderInfoAjaxAction.do?memberid="+memberid; 

        if (window.ActiveXObject) 
        { 
            arHttpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
           
        } 
        else if (window.XMLHttpRequest) 
        { 
            arHttpRequest = new XMLHttpRequest(); 
        } 
     
        arHttpRequest.open("POST", url, true); 
        
        arHttpRequest.onreadystatechange = function() {membStatus(); } ; 
        arHttpRequest.send(null); 
   } 
	}
   
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function membStatus() 
    { 
   
        if (arHttpRequest.readyState == 4) 
        { 
            if(arHttpRequest.status == 200) 
            { 
                //get the XML send by the servlet 
                 var arnameXML = arHttpRequest.responseXML.getElementsByTagName("memberstatus")[0]; 
                 var arnameText = arnameXML.childNodes[0].nodeValue; 
				
				//alert
				
                if(arnameText=="false")
                {    
					alert("Member Id Doesn't Exists!"); 
					document.frmUserSignup.memberId.value="";
					document.frmUserSignup.memberId.focus();
					return false;
                }
				/*else    
				{
					famMemberStatus();
				}*/

            } 
            else 
            { 
                alert("Error loading page\n"+ arHttpRequest.status +":"+ arHttpRequest.statusText); 
            } 
        } 
    } 

 

</script>

</head>


<body onload="searchSec()">

<!--========================================================
HEADER
=========================================================-->
  <div id="header">
    <!-- HEADER STARTS HERE -->
<%@ include file = "../../include/menschForceHeader.jsp" %>
 <!-- HEADER ENDS HERE -->
    </div>
	
<div class="content indent">
    
<div class="thumb-box14">
<div class="container">
<div class="row">
			
			<div class="col-lg-12 col-md-12 col-sm-12">
			
					
						<h3 class="title">Membership: Search Members and Non-Members</h3>
					
					
			</div>
				
			<div class="col-lg-12 col-md-12 col-sm-12">	
			
					<div class="col-lg-3 col-md-3 col-sm-3">
					</div>
				
					<p>Please choose the search criteria in order to assign role to user/login into the user's account</p>
					
			</div>


            <form action="SearchList.html"  method="post" name="frmUserSignup" id="frmUserSignup" onsubmit="return myValidate();">
                  <input type="hidden" name="searchProcess" value="humanSearch" />
				 <div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
				 </div>
             <div class="col-lg-12 col-md-12 col-sm-12">
					 
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6">
					<label class="name form-div-6">
					 <font color="#0000FF"><input type="radio" name="radMem" id="radMem" value="members" onclick="submitform(this.value)"/>Members</font>
					 </label>
					 <label class="name form-div-6">
				     <font color="#0000FF"> <input type="radio" name="radMem" id="radMem" value="nonMembers" onclick="submitform(this.value)"/>Non-Members</font>
					 </label>
					</div>
			</div>
				
				  <div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
				 </div>
                    
                 
          <div id="showSearchCrite" style="display:none">
				 
          <div id="memShow" class="tableInner">
						  
				  <div class="col-lg-12 col-md-12 col-sm-12">	
									
						
						<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
						<label class="name form-div-6">
							 Member ID
						 </label>
						 </div>
						 
						 <div class="col-lg-3 col-md-3 col-sm-3">
						 <label class="name form-div-6" >
							<input type="text" name="memberId" id="memberId" class="form-control" size="20" onblur="HLCMemberDetails();"/>
						 </label>
						 </div>
						 
				</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
						</div>	
							
						<div class="col-lg-12 col-md-12 col-sm-12">	
							
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
                     		Roles:
							 </label>
							 </div>
							 <div class="col-lg-3 col-md-3 col-sm-3">
						 	 <label class="name form-div-6" >
							 <select name="roleId" class="form-control" id="roleId">
                                  <option value="">Select One</option>
                                  <%
									 ArrayList roleList = (ArrayList) request.getAttribute("roleList");
          					 if(roleList!=null && roleList.size()!=0){
							Iterator it = roleList.iterator();
							while(it.hasNext()){
								String[] s = (String[])it.next();
								String roleId = s[0];
								String rolname = s[1];		
								String roledesc=s[2];
								String status=s[3];
								String flag=s[4];
											
											 %>
                                  <option value="<%=roleId%>"><%=rolname%></option>
                                  <%
											 }
										}
									


							%>
                                </select>
                             </label>
							
							</div>
							
							</div>
				</div>
							
                            
                             <div class="col-lg-12 col-md-12 col-sm-12">
								&nbsp;
							</div>
                          
                          <div id="dtShow" class="tableInner">
						  
						   <div class="col-lg-12 col-md-12 col-sm-12">	
							
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
                     		From Date <span class="asterisk">*</span>
							 </label>
							 </div>
							 <div class="col-lg-3 col-md-3 col-sm-3">
						 	 <label class="name form-div-6" >
							 
						  		<input  type="text" name="frmDate" id="frmDate" readonly="readonly" class="form-control" value="" size="25" maxlength="20" onClick="return(onValidateDate());"/>
                                  
                             </label>
							
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal" >
							 <a href="javascript:show_calendar('document.frmUserSignup.frmDate', document.frmUserSignup.frmDate.value);"><img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0" /></a> 
							</div>
							
							
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12">
									&nbsp;
							 </div>
							</div>
							
							
							 
                          <div id="dtShow" class="tableInner">
						  
						   <div class="col-lg-12 col-md-12 col-sm-12">	
							
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
                     		To Date <span class="asterisk">*</span>
							 </label>
							 </div>
							 <div class="col-lg-3 col-md-3 col-sm-3">
						 	 
						  		<input  type="text" name="toDate" id="toDate" readonly="readonly" class="form-control" value="" size="25" maxlength="20" onClick="return(onValidateDate());"/>
                                 
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal" >
							  <a href="javascript:show_calendar('document.frmUserSignup.toDate', document.frmUserSignup.toDate.value);"><img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0" /></a> 
							</div>
							
							</div>
									<div class="col-lg-12 col-md-12 col-sm-12">
											&nbsp;
									 </div>
							</div>
							
							
							
							
						
                          <div id="commShow">
						  <div class="col-lg-1 col-md-1 col-sm-1">
							</div>
						  
						  <div class="col-lg-12 col-md-12 col-sm-12">	
							
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
                     		First Name <span class="asterisk">*</span>
							 </label>
							 </div>
                                    
							<div class="col-lg-3 col-md-3 col-sm-3">
						 	 <label class="name form-div-6" >
								<input type="text" name="fname" id="fname" class="form-control" size="20" />
							
							 </label>
							
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
								 <label class="name form-div-6" >
									<input type="text" name="lname" id="lname" class="form-control" size="20" />
									
								 </label>
								
								</div>
								
							</div>
                            <div class="col-lg-12 col-md-12 col-sm-12">
									&nbsp;
							 </div	>
                           <div class="col-lg-12 col-md-12 col-sm-12">	
							
							
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-6">
								e-Mail<span class="asterisk">*</span>
								 </label>
								 </div>
										
								<div class="col-lg-3 col-md-3 col-sm-3">
								 <label class="name form-div-6" >
								<input type="text" name="email" id="email" class="form-control" size="20" />
								
								 </label>
								
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
								 <label class="name form-div-6" >
										<input type="text" name="zip" id="zip" class="form-control" size="20" />
								
								 </label>
								
								</div>
								
							</div>
							
							<div class="col-lg-12 col-md-12 col-sm-12">
									&nbsp;
							 </div>
										
							<div class="col-lg-12 col-md-12 col-sm-12">	
							
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-6">
								User Login Name<span class="asterisk">*</span>
								 </label>
								 </div>
										
								<div class="col-lg-3 col-md-3 col-sm-3">
								 <label class="name form-div-6" >
								<input type="text" name="loginName" id="loginName" class="form-control" size="20"/>
								
								 </label>
								
								</div>
								
							</div>
							
							<div class="col-lg-12 col-md-12 col-sm-12">
									&nbsp;
							 </div>
							
							</div>
							
							   
                            <div class="col-lg-12 col-md-12 col-sm-12">
								<div class="col-lg-5 col-md-5 col-sm-5">
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2">
									
										 <button name="submit" type="submit" class="button-add" value="Search" align="middle" onClick="return(onValidate());"/>Search</button>
									</div>
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
    
  		<%@ include file = "../../include/menschForceFooter.jsp" %>
      <!-- FOOTER ENDS HERE -->
	  </div>


<!--script type="text/javascript">


function onValidateDate()
{
	
	if(document.frmUserSignup.frmDate.value==""){
		alert("Startdate cannot be empty");
		document.frmUserSignup.frmDate.focus();
		return false;
	}
	if(document.frmUserSignup.toDate.value==""){
		alert("Lastdate cannot be empty");
		document.frmUserSignup.toDate.focus();
		return false;
	}
	
	return true;
}
</script-->
</body>
</html>

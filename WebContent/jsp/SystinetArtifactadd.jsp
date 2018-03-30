<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
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

<script src="js/basic.js" type="text/javascript" ></script>
<script src="js/cscombo_new.js" type="text/javascript" ></script>
<script src="js/calendar2.js" type="text/javascript"></script> 
<script  src="js/ts_picker.js"></script>
<script language="javascript" type="text/javascript">


//-----------------------------------is not alpha1----------------------------------------------------------------
function isnotAlpha1(str){
stringCheck="!@#$%^&*|<>?/=~`;:"+"\\"+"\'"+"\"";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
{ f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}

//------------------------DOSPACE--------------------------------------------------------------------------
function Dospace(str){
if(str.indexOf("  ")!=-1)
{return true;}
else {return false;}
}

function isnotInteger(str){
stringCheck="0123456789.";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
{ f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}

/*function myValidation(){
		
	if(document.frmlifecycleproc.frmDate.value==""){
	alert("Select Date");
	document.frmlifecycleproc.frmDate.focus();
	return false;
	}
	
		
	if(document.frmlifecycleproc.SelArtifacttype.value==""){
	alert("Select Artifact Type");
	document.frmlifecycleproc.SelArtifacttype.focus();
	return false;
	}
	if(document.frmlifecycleproc.txtLifeProcees.value==""){
	alert("Lifecycle Process Name can not be empty");
	document.frmlifecycleproc.txtLifeProcees.focus();
	return false;
	}
	
	if(document.frmlifecycleproc.txtLifeProcees.value.indexOf(" ")==0)
 {alert("Please enter a Lifecycle Process Name");
  document.frmlifecycleproc.txtLifeProcees.focus();
 return false;}
 
 if(isnotAlpha1(document.frmlifecycleproc.txtLifeProcees.value))
{ alert("Please enter a valid Lifecycle Process Name. Numbers and Symbols are not allowed");
  document.frmlifecycleproc.txtLifeProcees.focus();
   return false; }
   
    if(Dospace(document.frmlifecycleproc.txtLifeProcees.value)){
			alert("Please enter a valid Lifecycle Process Name");
	     	document.frmlifecycleproc.txtLifeProcees.focus();
	  		return false;
			}
			
			 if(isnotInteger(document.frmlifecycleproc.txtLifeProcees.value)){
			alert("Enter Valid Lifecycle Process Name");
	     	document.frmlifecycleproc.txtLifeProcees.focus();
	  		return false;
			}
	if(document.frmlifecycleproc.txtLifeProcees.value.length>30)
  {alert("Lifecycle Process Name too long");
  document.frmlifecycleproc.txtLifeProcees.focus();
  return false;}
	
	if(document.frmlifecycleproc.selNolifeStage.value==""){
	alert("Select number of lifecycle stages");
	document.frmlifecycleproc.selNolifeStage.focus();
	return false;
	}
	
	if(document.frmlifecycleproc.selNolifeStage.value=="1"){
	alert("Select more than 1 number of lifecycle stages");
	document.frmlifecycleproc.selNolifeStage.focus();
	return false;
	}
	
	if(document.frmlifecycleproc.selStages.value==""){
	alert("Select the lifecycle stage");
	document.frmlifecycleproc.selStages.focus();
	return false;
	}
	
	if(document.frmlifecycleproc.selStages.value!=""){
	var totstagesss=document.frmlifecycleproc.selNolifeStage.value;
	if(document.frmlifecycleproc.selStages.value>totstagesss){
	alert("Stage exceeded the number of lifecycle stages.");
	document.frmlifecycleproc.selStages.focus();
	return false;
	}
	}
	
	
	if(document.frmlifecycleproc.txtstagename.value==""){
	alert("Enter Stage Name");
	document.frmlifecycleproc.txtstagename.focus();
	return false;
	}
	
	if(document.frmlifecycleproc.txtstagename.value.indexOf(" ")==0)
 {alert("Enter a Stage Name");
  document.frmlifecycleproc.txtstagename.focus();
 return false;}
 
 if(isnotAlpha1(document.frmlifecycleproc.txtstagename.value))
{ alert("Enter Valid Stage Name");
  document.frmlifecycleproc.txtstagename.focus();
   return false; }
   
    if(Dospace(document.frmlifecycleproc.txtstagename.value)){
			alert("Enter Valid Stage Name");
	     	document.frmlifecycleproc.txtstagename.focus();
	  		return false;
			}
			
			 if(isnotInteger(document.frmlifecycleproc.txtstagename.value)){
			alert("Enter Valid Stage Name");
	     	document.frmlifecycleproc.txtstagename.focus();
	  		return false;
			}
			 if(document.frmlifecycleproc.txtstagename.value.length>30)
			  {alert("Enter Stage Name within 30");
			  document.frmlifecycleproc.txtstagename.focus();
			  return false;}
	
	if(document.frmlifecycleproc.txtInput.value==""){
	alert("Enter Input");
	document.frmlifecycleproc.txtInput.focus();
	return false;
	}
	
	if(document.frmlifecycleproc.txtInput.value.indexOf(" ")==0)
 {alert("Enter a Input");
  document.frmlifecycleproc.txtInput.focus();
 return false;}
 
 if(isnotAlpha1(document.frmlifecycleproc.txtInput.value))
{ alert("Enter Valid Input");
  document.frmlifecycleproc.txtInput.focus();
   return false; }
   
    if(Dospace(document.frmlifecycleproc.txtInput.value)){
			alert("Enter Valid Input");
	     	document.frmlifecycleproc.txtInput.focus();
	  		return false;
			}
			
			 if(isnotInteger(document.frmlifecycleproc.txtInput.value)){
			alert("Enter Valid Input");
	     	document.frmlifecycleproc.txtInput.focus();
	  		return false;
			}
			 if(document.frmlifecycleproc.txtInput.value.length>30)
			  {alert("Enter Input within 30");
			  document.frmlifecycleproc.txtInput.focus();
			  return false;}
	
	
	if(document.frmlifecycleproc.txtprocess.value==""){
	alert("Enter process");
	document.frmlifecycleproc.txtprocess.focus();
	return false;
	}
	
	if(document.frmlifecycleproc.txtprocess.value.indexOf(" ")==0)
 {alert("Enter a process");
  document.frmlifecycleproc.txtprocess.focus();
 return false;}
 
 if(isnotAlpha1(document.frmlifecycleproc.txtprocess.value))
{ alert("Enter Valid process");
  document.frmlifecycleproc.txtprocess.focus();
   return false; }
   
    if(Dospace(document.frmlifecycleproc.txtprocess.value)){
			alert("Enter Valid process");
	     	document.frmlifecycleproc.txtprocess.focus();
	  		return false;
			}
			
			 if(isnotInteger(document.frmlifecycleproc.txtprocess.value)){
			alert("Enter Valid process");
	     	document.frmlifecycleproc.txtprocess.focus();
	  		return false;
			}
			 if(document.frmlifecycleproc.txtprocess.value.length>30)
			  {alert("Enter Process within 30");
			  document.frmlifecycleproc.txtprocess.focus();
			  return false;}
		
			
	if(document.frmlifecycleproc.txtDesc.value==""){
	alert("Enter Description");
	document.frmlifecycleproc.txtDesc.focus();
	return false;
	}
	if(document.frmlifecycleproc.selControls.value==""){
	alert("Select number of controls/votes");
	document.frmlifecycleproc.selControls.focus();
	return false;
	}
	if(document.frmlifecycleproc.selVotes.value==""){
	alert("Select number of votes required by");
	document.frmlifecycleproc.selVotes.focus();
	return false;
	}
	
	if(document.frmlifecycleproc.txtOutput.value==""){
	alert("Enter output");
	document.frmlifecycleproc.txtOutput.focus();
	return false;
	}
	if(document.frmlifecycleproc.txtOutput.value.indexOf(" ")==0)
 {alert("Enter a output");
  document.frmlifecycleproc.txtOutput.focus();
 return false;}
 
 if(isnotAlpha1(document.frmlifecycleproc.txtOutput.value))
{ alert("Enter Valid output");
  document.frmlifecycleproc.txtOutput.focus();
   return false; }
   
    if(Dospace(document.frmlifecycleproc.txtOutput.value)){
			alert("Enter Valid output");
	     	document.frmlifecycleproc.txtOutput.focus();
	  		return false;
			}
			
			 if(isnotInteger(document.frmlifecycleproc.txtOutput.value)){
			alert("Enter Valid output");
	     	document.frmlifecycleproc.txtOutput.focus();
	  		return false;
			}
			 if(document.frmlifecycleproc.txtOutput.value.length>30)
			  {alert("Enter Output within 30");
			  document.frmlifecycleproc.txtOutput.focus();
			  return false;}
	
	
	return true;
}
*/

var version = navigator.appVersion; 

function showKeyCode(e) { 
var keycode = (window.event) ? event.keyCode : e.keyCode; 

if ((version.indexOf('MSIE') != -1)) { 
if (keycode == 116) { 
event.keyCode = 0; 
event.returnValue = false; 
return false; 
} 
} 
else { 
if (keycode == 116) { 
return false; 
} 
} 
} 

var message="Sorry, right-click has been disabled"; 
/////////////////////////////////// 
function clickIE() {if (document.all) {(message);return false;}} 
function clickNS(e) {if 
(document.layers||(document.getElementById&&!document.all)) { 
if (e.which==2||e.which==3) {(message);return false;}}} 
if (document.layers) 
{document.captureEvents(Event.MOUSEDOWN);document.onmousedown=clickNS;} 
else{document.onmouseup=clickNS;document.oncontextmenu=clickIE;} 
document.oncontextmenu=new Function("return false") 

window.onbeforeunload = function() {
  return "Data will be lost if you leave the page, are you sure?";
};


//-------------------------------- Lifecycle validation Ajax Script ------------------------------------------------

var httpRequest;

function lifecyceStat()
{

if(document.frmlifecycleproc.txtLifeProcees.value!="" && document.frmlifecycleproc.txtLifeProcees.value.indexOf(' ')!=0)
	{

   var chsLifecycleName=document.frmlifecycleproc.txtLifeProcees.value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
        url= "UsrSignupAjax.html?process=checkLifecycle&lifecycleN="+chsLifecycleName; 

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
			alert("Lifecycle Name already Exists Choose Another !");
			document.frmlifecycleproc.txtLifeProcees.value="";
			document.frmlifecycleproc.txtLifeProcees.focus();
		}
		      
    } 



document.getElementById('txtDesc').addEventListener('keyup', function () {
			this.style.height = 0; // this is necessary to make it shrink when deleting
			this.style.height = this.scrollHeight + 'px';
		}, false);
		

		
function stageStat(){


//alert(1);

var artCnt=document.frmlifecycle.artCount.value;
//alert(artCnt);

if(artCnt!=0){
for(var k=1;k<=artCnt;k++){
var chName = "staCount"+k;

 var stageName1=document.getElementById(chName).value; 
 //alert(stageName1);
 if(stageName1==document.frmlifecycleproc.txtstagename.value){
alert("Stage Name already Exists");
document.frmlifecycleproc.txtstagename.value="";
document.frmlifecycleproc.txtstagename.focus();
return false;
}

}
}
return true;
}		
	





</script>-->

<style>
#frmlifecycleproc label.error{
	color:red;
}
img.image1{
top:10px;
position:absolute;
right:25px;
}
</style>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
</head>
<%
 
            String fstname=(String)session.getAttribute("firstName");
			  String userLname=(String)session.getAttribute("lastName");
			  
			  
			  String fullNam=fstname+" "+userLname;
                String lifecycleProNameinputType = (String) request.getAttribute("lifecycleProName");
                String numStage = (String) request.getAttribute("numofStage");
                String dateofCreat = (String) request.getAttribute("dateofCreation");
                String addArtitype = (String) request.getAttribute("addArtifacttype");	
                                
                String cntValue = (String) session.getAttribute("cntValue");
                
                String CurStage=(String) request.getAttribute("CurStage");
                
                String descripitions=(String) request.getAttribute("descripition");
                
				String dupmsg=(String) request.getAttribute("dupMsg");
 
 
 if(dupmsg==null){
 dupmsg="";
 
 }else{
 }
 
                boolean butStatus=true;
                String curStage="";
                if(CurStage==null){
                	 curStage="1";
                }else{
 int tempCurSt=Integer.parseInt(CurStage);
 int finalCurSt=0;
 if(dupmsg!=""){
 finalCurSt=tempCurSt;
 }else{
 finalCurSt=tempCurSt++;
 
 }
 curStage=String.valueOf(tempCurSt);
 
 int tempNumSts=Integer.parseInt(numStage);
 
 if(tempCurSt>tempNumSts){
                	
                		butStatus=false;
                		dateofCreat = "";
                		addArtitype = "";	
                		lifecycleProNameinputType = "";
                		numStage = "";
						curStage="";
						descripitions="";
										 
                	}else{
                		butStatus=true;
                	}
 

                }
                                           
                System.out.println("butStatus"+butStatus);
             /*   if(cntValue.equalsIgnoreCase("0")){
                	
                	cntValue="1";	
                }else{
                	cntValue=cntValue;
                }*/
                if (addArtitype == null) {

                	addArtitype = "";
				}
                
                else{
                	
                	if(addArtitype.equalsIgnoreCase("hpsoaProjectArtifact")){
                		addArtitype="Project";               		
                	}else if(addArtitype.equalsIgnoreCase("hpsoaApplicationArtifact")){
                		addArtitype="Application";  
                	}else if(addArtitype.equalsIgnoreCase("businessServiceArtifact")){
                		addArtitype="Service";  
                	}else if(addArtitype.equalsIgnoreCase("implementationArtifact")){
                		addArtitype="Implementaion";  
                	}else if(addArtitype.equalsIgnoreCase("hpsoaProcessArtifact")){
                		addArtitype="Business Process";  
                	}
                	 
                }
                
                if (descripitions == null) {

                	descripitions = "";
				}
                
                else{
                	
                	
                }
				
                if (lifecycleProNameinputType == null) {

                	lifecycleProNameinputType = "";
				}
				
                
                if (numStage == null) {

                	numStage = "";
				}
                if (dateofCreat == null) {

                	dateofCreat = "";
				}
               
    %>

	
	

<body onload="JavaScript:document.body.focus();" onkeydown="return showKeyCode(event)">

 <!--========================================================
HEADER
=========================================================-->
  <div id="header">
    <!-- HEADER STARTS HERE -->
<%@ include file = "../../include/menschForceHeader.jsp" %>
 <!-- HEADER ENDS HERE -->
    </div>
    <script src="js/jquery.validate.js"></script>
<script src="js/additional-methods.js"></script>
<script src="js/profilefieldvalidation.js"></script>
    <!--========================================================
CONTENT
=========================================================-->
  
	 
     
      
                      <% String msg=(String)request.getAttribute("Success");
%>

  <div class="content indent">
     
	 <div class="thumb-box14">
       
	    <div class="container">
		
            <div class="row">
      

                         <div class="col-lg-12 col-md-12 col-sm-12">

					 	 
						<h3 class="title"> Lifecycle Process : Create</h3>
						
						 </div>
				
                            <%if(msg!=null) {%>
                            <script language="JavaScript" type="text/javascript">
		
 function popitup(url) {
	 if(confirm(msg))
	 {
		newwindow=window.open(msg,'name','height=450,width=800,scrollbars=no,resizable=no,top=300,left=500,toolbar=no,menubar=no,dialog=yes');
                        if (window.focus) {newwindow.focus()}
                        return false;
	 }
	 else
	 {
		 return;
	 }

}
                              </script>
                            <%}%>
							
                           </div>
							
							
											
						  
                         <form name="frmlifecycleproc" id="frmlifecycleproc" method="post" action="SysMgmt.html?process=callArtadd" class="formcss" onsubmit="return myValidation();">

								
								
							<div class="col-lg-12 col-md-12 col-sm-12">
					
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							 Date of Lifecycle <span class="asterisk">*</span>
							</label> </div>
									
								<div class="col-lg-3 col-md-3 col-sm-3">						
                              	
								<span class="formX">
                              			<input name="frmDate" type="text" id="frmDate" class="form-control" value="<%=dateofCreat%>" size="10" readonly="true"/>
                               				
											
						   		  </span>
								
								</div>
								<div class="col-lg-1 col-md-1 col-sm-1 datecal">
									<a href="javascript:show_calendar('document.frmlifecycleproc.frmDate', document.frmlifecycleproc.frmDate.value);">
							   					<img class="image1" src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="17" style="vertical-align:bottom;" border="0" />
							   				</a>
						   		</div>
							
							<div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
								
							<div class="col-lg-12 col-md-12 col-sm-12">	
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-6">
								 Artifact Type <span class="asterisk">*</span>
								 </label>
								 </div>
							 
							  <div class="col-lg-3 col-md-3 col-sm-3">	
							 
								  <select name="SelArtifacttype" class="form-control" id="SelArtifacttype">
                                <option selected="selected" value="">Select One</option>
                                <%
                                                       
                                                            Map<String,String> artiType = (HashMap) session.getAttribute("artifactType");
                                                            if (artiType != null && artiType.size() != 0) {
                                                           Iterator iter = artiType.entrySet().iterator();
                                                                //String [] userType = {ID, name };
                                                                while (iter.hasNext()) {
                                                                	Map.Entry me = (Map.Entry)iter.next();
                                                                    String artifactType =(String) me.getValue();
                                                                    String artifactId =(String) me.getKey(); 
                                                                    if (artifactId.equals(addArtitype)) {

                                                %>
                                <option value="<%=artifactId%>" selected="selected" ><%=artifactType%></option>
                                <%
                                                 } else {
                                                %>
                                <option value="<%=artifactId%>" ><%=artifactType%></option>
                                <%
                                                                    }
                                                                }
                                                            }
                                                %>
                              </select>
                            &nbsp;
							
							</div>
							
						</div>	
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
						
						
					     <div class="col-lg-12 col-md-12 col-sm-12">	
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
                         <label class="name form-div-6">
						  Lifecycle Process Name  <span class="asterisk">*</span>
						 </label>
						 </div>
						  
						  
						    
							  <% if(lifecycleProNameinputType.equalsIgnoreCase("")){ %>
							  						
							 <div class="col-lg-3 col-md-3 col-sm-3">
                           
								<input type="text" name="txtLifeProcees" id="txtLifeProcees" class="form-control" size="20" "/>
								
							
							 </div>
							
							
                            <%} else { %>
							
							<div class="col-lg-3 col-md-3 col-sm-3">
                           
							<input type="text" name="txtLifeProcees" value="<%=lifecycleProNameinputType %>" readonly="readonly"  class="form-control" size="20"/>
							 
							
							 </div>
							
                            

                            <% } %>
                             </div>
							
							
							<div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
						
						
						  
						   <div class="col-lg-12 col-md-12 col-sm-12">	
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
						    <label class="name form-div-6">
                              Number of Lifecycle Stages <span class="asterisk">*</span>
						    </label>
							</div>
						        
								
								<div class="col-lg-3 col-md-3 col-sm-3">
								<% if(numStage=="") {%>
								
								 
                                <select name="selNolifeStage" class="form-control" id="selNolifeStage">
                                  <option selected="selected" value="">Select One</option>
								  
                                  <%
                                                       
                                                            ArrayList numlifeCycle = (ArrayList) session.getAttribute("lifeCycle");
                                                            if (numlifeCycle != null && numlifeCycle.size() != 0) {
                                                            	Iterator  lifeCycle = numlifeCycle.iterator();
                                                                //String [] userType = {ID, name };
                                                                while (lifeCycle.hasNext()) {
                                                                    String strType = (String) lifeCycle.next();
                                                                    String numlifeStage = strType;
                                                                 
                                                                   

                                                %>
                                  <option value="<%=numlifeStage%>"><%=numlifeStage%></option>
                                  <%
                                                                    
                                                                }
                                                            }
                                                %>
								
                                </select>
                                
								  </div>
								  
								
								
								 <div class="col-lg-3 col-md-3 col-sm-3"> 
                               <%}else{%>
								
								<label class="name form-div-6" >
                                <input type="text" name="selNolifeStage" id="selNolifeStage" value="<%=numStage%>" readonly="readonly"  class="textboxOne" size="20"/>
                                <span class="asterisk">*</span>
								</label>
                                <%}%>
                       		   </div>
							  
								</div>		
								
							<div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
						 
						<div class="col-lg-12 col-md-12 col-sm-12">	
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-6">
									Stage <span class="asterisk">*</span>
								 </label>
							 </div>
						  
						  <div class="col-lg-3 col-md-3 col-sm-3">
						  
                            <select name="selStages" class="form-control" id="selStages">
                              <option selected="selected" value="">Select One</option>
                                <%
                                                       
                                                            ArrayList StageCycle = (ArrayList) session.getAttribute("StageCycle");
                                                            if (StageCycle != null && StageCycle.size() != 0) {
                                                            	Iterator  StaCycle = StageCycle.iterator();
                                                              
                                                                while (StaCycle.hasNext()) {
                                                                    String strType = (String) StaCycle.next();
                                                                    String Stage = strType;
                                                                 
                                                                    if (Stage.equals("")) {

                                                %>
                                <option value="<%=Stage%>" selected="selected" ><%=Stage%></option>
                                <%
                                                 } else {
                                                %>
                                <option value="<%=Stage%>" ><%=Stage%></option>
                                <%
                                                                    }
                                                                }
                                                            }
                                                %>
                              </select>
                           
							</div>
							
						</div>		
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>	
							
						<div class="col-lg-12 col-md-12 col-sm-12">	
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
								StageName <span class="asterisk">*</span>
							</label>
							</div>
						 
						  <div class="col-lg-3 col-md-3 col-sm-3">
						  
                            <input type="text" name="txtstagename" class="form-control" size="20" id="txtstagename"/>
                              
							 
						  </div>
						  
						   
						</div>		
							
						<div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
							
						<div class="col-lg-12 col-md-12 col-sm-12">	
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
                      		   Input  <span class="asterisk">*</span>
						 	</label></div>
							
						 	<div class="col-lg-3 col-md-3 col-sm-3">
								
                            <input type="text" name="txtInput" class="form-control" size="20" id="txtInput"/>
                             
							 
							  </div>
							   
						</div>		
							
						<div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>

						<div class="col-lg-12 col-md-12 col-sm-12">	
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
                             Process <span class="asterisk">*</span>
							 </label>
							 </div>
							 
							    <div class="col-lg-3 col-md-3 col-sm-3">
								
                                <input type="text" name="txtprocess" class="form-control" size="20" id="txtprocess"/>
                                
								</div>
								 
						</div>		
							<div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
								
							<div class="col-lg-12 col-md-12 col-sm-12">	
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-6">
								  Description <span class="asterisk">*</span>
								</label>
								</div>
							   
									<div class="col-lg-3 col-md-3 col-sm-3"> 
									
									<textarea rows="3" cols="45" name="txtDesc" class="form-control" id="txtDesc"></textarea>
									
									</div>
								
								
								</div>		
							<div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
								
							<div class="col-lg-12 col-md-12 col-sm-12">	
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-6">
							   Numbers of Controls/Votes <span class="asterisk">*</span>
							   </label>
							  </div>
						   
						   		<div class="col-lg-3 col-md-3 col-sm-3">
							
                              <select name="selControls" class="form-control" id="selControls">
                                <option selected="selected" value="">Select One</option>
                                  <%
                                                       
                                                            ArrayList numberVoters = (ArrayList) session.getAttribute("numberVoters");
                                                            if (numberVoters != null && numberVoters.size() != 0) {
                                                            	Iterator  numberContrVoters = numberVoters.iterator();
                                                                //String [] userType = {ID, name };
                                                                while (numberContrVoters.hasNext()) {
                                                                    String strType = (String) numberContrVoters.next();
                                                                    String ContrVoters = strType;
                                                                 
                                                                    if (ContrVoters.equals("")) {

                                                %>
                                  <option value="<%=ContrVoters%>" selected="selected" ><%=ContrVoters%></option>
                                  <%
                                                 } else {
                                                %>
                                  <option value="<%=ContrVoters%>" ><%=ContrVoters%></option>
                                  <%
                                                                    }
                                                                }
                                                            }
                                                %>
                                </select>
                              &nbsp;
							  </div>
							  
							  
						</div>		
							
							  
							<div class="col-lg-12 col-md-12 col-sm-12">	
							
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
									<label class="name form-div-6">
								   Votes Required By <span class="asterisk">*</span>
								   </label>
								  </div>
							   
								  <div class="col-lg-3 col-md-3 col-sm-3">
								
								
									<select name="selVotes" class="form-control" id="selVotes">
                                  <option selected="selected" value="">Select One</option>
                                  <%
                                                       
                                                            Map<String,String> votesReq = (HashMap) session.getAttribute("votesReq");
                                                            if (votesReq != null && votesReq.size() != 0) {
                                                            	 Iterator iter = votesReq.entrySet().iterator();
                                                                while (iter.hasNext()) {
                                                                	Map.Entry me = (Map.Entry) iter.next();
                                                                    String Voters =(String)me.getValue() ;
                                                                    String votersId=(String)me.getKey();
                                                                    if (Voters.equals("")) {

                                                %>
                                  <option value="<%=votersId%>" selected="selected" ><%=Voters%></option>
                                  <%
                                                 } else {
                                                %>
                                  <option value="<%=votersId%>" ><%=Voters%></option>
                                  <%
                                                                    }
                                                                }
                                                            }
                                                %>
                                </select>
                              &nbsp;
							  </div>
							  
						</div>		
							
							  
							 
							 
						     <div class="col-lg-12 col-md-12 col-sm-12">	
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
									<label class="name form-div-6">
									Output <span class="asterisk">*</span>
									</label>
								</div>
								
								
								 <div class="col-lg-3 col-md-3 col-sm-3">
								
								  <input type="text"name="txtOutput" class="form-control" size="10" id="txtOutput"/>
								  &nbsp; 
								</div>
								 
								 
							</div>		
							
							 
                            <!-- spacer starts-->
                              <div class="spacer">&nbsp;</div>
                            <!-- spacer ends-->
							
							
							<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-5 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							<label class="name form-div-6" >
                                   <input type="submit" value="ADD/SAVE STAGE" class="button-add" style="width:130px" name="method" onClick="return(onValidate());"/>
						 	</label>
								   </div>
								   </div>
                                  
                            <!-- spacer starts-->
                            <!-- spacer ends-->
                              <!-- spacer starts-->
                              <div class="spacer">&nbsp;</div>
							<!-- spacer ends-->
                              <s:text name="Please fill in the form below:" />
                              <!-- **************************************** Part A of the form Ends here *********************************************** -->
                       
                        </form>
					<div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
					</div>
				  
				    <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-5 col-md-4 col-sm-4">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6">
                     <h4>Displaying results 1-5</h4>
					 </div>
					 </div> 
					 
                     <div class="col-lg-12 col-md-12 col-sm-12">
					 <div class="col-lg-1 col-md-1 col-sm-1">
					 <label class="name form-div-6 subtitle" >
					 Lifecycle
					 </label>
					 </div>
					 <div class="col-lg-1 col-md-1 col-sm-1">
					 <label class="name form-div-6 subtitle" >
					 Totalstages
					 </label>
					 </div>
					  <div class="col-lg-2 col-md-2 col-sm-2">
					 <label class="name form-div-6 subtitle " >
					  Date of Creation
					 </label>
					 </div>
					  <div class="col-lg-1 col-md-1 col-sm-1">
					 <label class="name form-div-6 subtitle" >
					  CreatedBy
					 </label>
					 </div>
					  <div class="col-lg-1 col-md-1 col-sm-1">
					 <label class="name form-div-6 subtitle" >
					 Stage
					 </label>
					 </div>
					  <div class="col-lg-1 col-md-1 col-sm-1">
					 <label class="name form-div-6 subtitle " >
					  StageName
					  </label>
					 </div>
					  <div class="col-lg-1 col-md-1 col-sm-1">
					 <label class="name form-div-6 subtitle" >
					  Input
					 </label>
					 </div>
					  <div class="col-lg-1 col-md-1 col-sm-1">
					 <label class="name form-div-6 subtitle" >
					  Process
					 </label>
					 </div>
					  <div class="col-lg-1 col-md-1 col-sm-1">
					 <label class="name form-div-6 subtitle" >
					  Control
					 </label>
					 </div>
					  <div class="col-lg-1 col-md-1 col-sm-1">
					 <label class="name form-div-6 subtitle " >
					  Output
					 </label>
					 </div>
					  <div class="col-lg-1 col-md-1 col-sm-1">
					 <label class="name form-div-6 subtitle" >
					  Description
					 </label>
					 </div></div>
					 
			   <form name="frmlifecycle" id="frmlifecycle" method="post" action="SysMgmt.html?process=systinetLifecycle" class="formcss" onsubmit="">
                        <%
     
    
     
             ArrayList artifactName=(ArrayList)session.getAttribute("artifactData");
     
     if (artifactName != null && artifactName.size() != 0) { 
    	 Iterator evotesartifactName = artifactName.iterator();
    	 //String [] userType = {ID, name };
    	 while (evotesartifactName.hasNext()) {
             String strType[] = (String[]) evotesartifactName.next();
         	
			String lifecycleProName=strType[0];
    	String numofStage=strType[1];
    	String dateofCreation=strType[2];
    	String createdBy=strType[3];
    	String stage=strType[4];
    	String stageName=strType[5];
    	String input=strType[6];
    	String process=strType[7];
    	String voter=strType[8];
    	String output=strType[9];
    	String descripition=strType[10];
        String addArtifacttype=strType[11]; 
        String votes=strType[12]; 		
       

%>



 				<div class="col-lg-12 col-md-12 col-sm-12">
					 <div class="col-lg-1 col-md-1 col-sm-1">
						 <%=lifecycleProName%>
					 </div>
					 <div class="col-lg-1 col-md-1 col-sm-1">
						 <%=numofStage%>
					 </div>
					  <div class="col-lg-2 col-md-2 col-sm-2" style="align:center">
						 <%=dateofCreation%>
					 </div>
					  <div class="col-lg-1 col-md-1 col-sm-1">
						 <%=fullName%>
					 </div>
					  <div class="col-lg-1 col-md-1 col-sm-1">
						<%=stage%>
					 </div>
					  <div class="col-lg-1 col-md-1 col-sm-1">
					 <%=stageName%>
					 </div>
					  <div class="col-lg-1 col-md-1 col-sm-1">
					 <%=input%>
					 </div>
					  <div class="col-lg-1 col-md-1 col-sm-1">
					<%=process%>
					 </div>
					  <div class="col-lg-1 col-md-1 col-sm-1">
					 <%=voter%>
					 </div>
					  <div class="col-lg-1 col-md-1 col-sm-1">
					<%=output%>
					 </div>
					  <div class="col-lg-1 col-md-1 col-sm-1">
					 <%=descripition%>
					 </div>
					 </div>
                       
                        <%
		                           
                                                                                                                                                                       }
   }
   else{
	   %>
                       
				    <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-5 col-md-4 col-sm-4">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6">
                     <h4>NO DATA</h4>
					 </div>
					 </div> 
                        <%
   }
                                               
                                                
                            if (artifactName != null && artifactName.size() != 0) { %>
							
                            <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-6 col-md-6 col-sm-6">
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							 
							
                            <label>
							<input name="submit" type="submit" class="button-add" value="Submit" align="right"/>
                          </label>
                         </div>
						 </div>
                        <%
   } %>
                      </form>
                    
              <!-- CONTENTS START HERE -->
         
    
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
  
  
<!--script type="text/javascript">


function onValidate()
{
	
	if(document.frmlifecycleproc.frmDate.value==""){
		alert("Startdate cannot be empty");
		document.frmlifecycleproc.frmDate.focus();
		return false;
	}
	if(document.frmUserSignup.toDate.value==""){
		alert("Lastdate cannot be empty");
		document.frmUserSignup.toDate.focus();
		return false;
	}
	
	return true;
}
</script>-->
</body>


</html>

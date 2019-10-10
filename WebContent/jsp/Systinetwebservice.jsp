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

<script src="js/calendar2.js" type="text/javascript"></script>

 
 <script language="javascript">

function submit(){
	
	 document.forms["requestIns"].submit();


}
function goBack(){
	strURL = "./SysMgmt.html?process=callArtifactWSList";
	window.location.href = strURL;
}

function callpopupDoc(url) {

	document.getElementById("pop").style.display = 'none'; 
	document.getElementById("popDoc").style.display = 'block'; 
	
}

function callpopup() {
	
	document.getElementById("pop").style.display = 'block'; 

	document.getElementById("popDoc").style.display = 'none';


}
function validate(){
	if(document.requestIns.name.value==""){
		alert("Please enter a name");
		document.requestIns.name.focus();
		return false;
		}
	return true;
}

     </script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
</head>


<body>

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

					 	
							<h3 class="title"> Business Service: Initiate Governance for Business Service</h3>
						
						 </div>
						  
						  
                            <%String id=(String)request.getParameter("id");
			String name=(String)request.getParameter("wsName");
			String version=(String)request.getParameter("wsVersion");
			String govStatus=(String)request.getParameter("govStatus");	
   %>
                             <form name="requestIns" id="requestIns"   method="post" action="SysMgmt.html?process=webserviceProcesubmit" class="formcss">
                              <input type="hidden" name="method" value="insertrequest"/>
                              <input type="hidden" name="id" value="<%=id%>"/>
                              <input type="hidden" name="gov" value="stopGov"/>
                              <input type="hidden" name="govStatus" value="<%=govStatus%>"/>
							  
							<div class="col-lg-12 col-md-12 col-sm-12">
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-6 subtitle">
									Business Service
								 </label>
								 </div>
							 </div>
							  <div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
									<label class="name form-div-6">
									 Name <span class="asterisk">*</span>
									 </label>
								 </div>
										
								<div class="col-lg-3 col-md-3 col-sm-3">
									 
										<input type="text" name="name" id="name" class="form-control" size="20" value="<%=name%>" readonly="readonly"/>
										
									
								</div>
								
							</div>
							
                             <div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>    
                              
							<div class="col-lg-12 col-md-12 col-sm-12">	
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
									<label class="name form-div-6">
									  Description
									 </label>
								 </div>
										
								<div class="col-lg-3 col-md-3 col-sm-3">
									 <label class="name form-div-6" >
									 <textarea rows="4" cols="45" class="form-control" name="textarea"></textarea>
									
									 </label>
								</div>
								
							</div>
                             
							  <div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
							
								<div class="col-lg-12 col-md-12 col-sm-12">
							 	
									<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
										<label class="name form-div-6">
										 Version
										 </label>
									 </div>
											
									<div class="col-lg-3 col-md-3 col-sm-3">
									
										<input type="text" name="version"  class="form-control" size="20" value="<%=version%>" readonly="readonly"/>
									
									</div>
									
								</div>
							
							 <div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
							
							
							 <div class="col-lg-12 col-md-12 col-sm-12">	
							 
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
									<label class="name form-div-6">
									Criticality
									 </label>
								 </div>
										
								<div class="col-lg-3 col-md-3 col-sm-3">
								
									<span class="formX">
											  <select  id="select2" name="criticality" class="form-control">
												<option selected="selected" value="">Select One</option>
												<option value="high">High</option>
												<option value="medium">Medium</option>
												<option value="low">Low</option>
											  </select>
											  &nbsp; </span>
										
										
								 </div>
								
							</div>
							 <div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
						    <div class="col-lg-12 col-md-12 col-sm-12">	
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
									<label class="name form-div-6">
									Consumable
									 </label>
								 </div>
										
								<div class="col-lg-3 col-md-3 col-sm-3">
								 <input type="checkbox" name="cons">
								 
								</div>
								
							</div>
                                
                            <div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
							
                           <div class="col-lg-12 col-md-12 col-sm-12">	 
							
								<div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-3">
							
                              
									<button type="button" value="Add WSDL" style="width:130px;" class="button-add" name="method2"  onclick="callpopup()">Add WSDL</button>
                              
									<button type="button" value="Add Document" style="width:130px;"  class="button-edit" name="method2"  onclick="callpopupDoc()">Add Document</button>
								</div>
							   
							 </div> 
							 <div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
                            <div class="col-lg-12 col-md-12 col-sm-12">
									<div class="col-lg-5 col-md-5 col-sm-5">
									</div>
									<div class="col-lg-1 col-md-1 col-sm-1">
										<button type="button" value="Save" class="button-add" name="method2" onclick="validate();submit()">Save</button>
									</div>
									  
										<div class="col-lg-1 col-md-1 col-sm-1">
									
											<button type="button" value="Cancel"  class="button-dang" name="method2" onclick="goBack();">Cancel</button>
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

</body>
</html>

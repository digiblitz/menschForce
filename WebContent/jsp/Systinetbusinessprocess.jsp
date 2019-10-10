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
    
function callpopup() {

	document.getElementById("pop").style.display = 'block'; 
	document.getElementById("popDoc").style.display = 'none'; 
	
}
function goBack(){
	strURL = "./SysMgmt.html?process=callArtifactBPList";
	window.location.href = strURL;
}

function callpopupDoc() {

	document.getElementById("pop").style.display = 'none'; 
	document.getElementById("popDoc").style.display = 'block'; 
	
}
function validate()
{
	if(document.requestIns.name.value==""){
		alert("Please enter a name");
		document.requestIns.name.focus();
		return false;
		}
	return true;
}

function submit(){
	
	 document.forms["requestIns"].submit();


}
     </script>
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

					 	
							<h3 class="title"> Business Process: Initiate Governance for Business Process</h3>
					
						 </div>
			
      
                            <%
   	String id=(String)request.getParameter("id");
			String Name=(String)request.getParameter("bpName");
			String Version=(String)request.getParameter("bpVersion");
		String govStatus=(String)request.getParameter("govStatus");	

    %>
                            <form name="requestIns" id="requestIns"    method="post" action="SysMgmt.html?process=busineessProceSubmit" class="formcss">
                              <input type="hidden" name="id" value="<%=id%>"/>
                              <input type="hidden" name="gov" value="stopGov"/>
                              <input type="hidden" name="govStatus" value="<%=govStatus%>"/>
							  
							  <div class="col-lg-12 col-md-12 col-sm-12">
							 
									<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
										<label class="name form-div-6 subtitle">
										Business process
										 </label>
									</div>
							 </div>
							<div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							
									<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
										<label class="name form-div-6">
										 Name <span class="asterisk">&nbsp;*</span>
										 </label>
									 </div>
									 <div class="col-lg-3 col-md-3 col-sm-3">
									
										<input	type="text" name="name" id="name" class="form-control" size="20" value ="<%=Name%>" readonly="readonly"/>
									
														
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
								
									<textarea rows="3" cols="46" class="form-control" name="desc"></textarea>
								
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
								 
								  <input type="text" name="version"  class="form-control" size="20" value ="<%=Version%>"  readonly="readonly"/>
								
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
								
									  <select  id="select" name="criticality" class="form-control">
											<option selected="selected" value="">Select One</option>
											<option value="high">High</option>
											<option value="medium">Medium</option>
											<option value="low">Low</option>
										  </select>
										 
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
								<input	type="checkbox" name="cons" />
							</div>
							
							
							
							</div>	
							
							<div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
							
                              <div class="col-lg-12 col-md-12 col-sm-12">	  
									
									<div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-3">
									<label class="name form-div-6" >
									  
									  <button type="button" value="Add WSDL" class="button-add" name="method"  onclick="callpopup()">Add WSDL</button>
									  
									<button type="button" value="Add Document" class="button-edit" name="method"  style="width:130px;" onclick="callpopupDoc()">Add Document</button>
									   </label>
									  </div>   
                                
                             </div> 
							 
							 <div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
							
						  <div id="pop" style="display:none;">
								  
						   <div class="col-lg-12 col-md-12 col-sm-12">	
							
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
									<label class="name form-div-6">
									File Name
									 </label>
								 </div>
                                    
								<div class="col-lg-3 col-md-3 col-sm-3">
									<input type="text" name="bpname"  class="form-control" size="20" />
								
								</div>
							
							</div>	
							
							<div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
							
							
							<div class="col-lg-12 col-md-12 col-sm-12">	
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-6">
								  Path
								 </label>
								 </div>
										
								<div class="col-lg-3 col-md-3 col-sm-3">
								<input type="text" name="bppath"  class="form-control" size="20" />
								
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
								<input type="text" name="bpdesc"  class="form-control" size="20" />
								
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
								<select  id="select" name="bpcriticality" class="form-control">
											<option selected="selected" value="">Select One</option>
											  <option value="high">High</option>

												<option value="medium">Medium</option>
														  <option value="low">Low</option></select>
								 
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
								<input type="text" name="bpversion"  class="form-control" size="20" />
								
								</div>
							
								
							</div>
							
							<div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
							
							
							  <div class="col-lg-12 col-md-12 col-sm-12">	
							
							
							
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-6">
								Target Namespace
								 </label>
								 </div>
										
								<div class="col-lg-3 col-md-3 col-sm-3">
								<input type="text" name="bptrname"  class="form-control" size="20" />
								 
								</div>
							
							</div>
							
							<div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
							
							
							  <div class="col-lg-12 col-md-12 col-sm-12">	
							
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
									<label class="name form-div-6">
									Location
									 </label>
								 </div>
										
								<div class="col-lg-3 col-md-3 col-sm-3">
									<input	type="text" name="bplocation"  class="form-control" size="20" />							
								</div>
							</div>
							
							
							<div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
							
							
	
			</div>
											
							<div id="popDoc" style="display:none;">
							
							
						    <div class="col-lg-12 col-md-12 col-sm-12">	
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-6">
								Document Name
								 </label>
								 </div>
										
								<div class="col-lg-3 col-md-3 col-sm-3">
								<input type="text" name="docname"  class="form-control" size="20" />						
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
									<input type="text" name="docdesc"  class="form-control" size="20" />					
									</div>
									
									
							</div>
							
							<div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
							
							
							<div class="col-lg-12 col-md-12 col-sm-12">	
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-6">
								 From Path
								 </label>
								 </div>
										
								<div class="col-lg-3 col-md-3 col-sm-3">
								<input type="text" name="docpath"  class="form-control" size="20" />
								</div>
							
							</div>
							
							<div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
							
							
							<div class="col-lg-12 col-md-12 col-sm-12">	
							
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-6">
								Location (To Path)
								 </label>
								 </div>
										
								<div class="col-lg-3 col-md-3 col-sm-3">
								<input type="text" name="doclocat"  class="form-control" size="20" />
								</div>
								
							
							</div>
							
							
									
					</div>
							<div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							</div>
														
								
								
								
								   
                            <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-5 col-md-5 col-sm-5">
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
									  <button type="button" value="Save" class="button-add" name="method2" onclick="validate();submit()"> Save </button>
									  </div>
									  
										<div class="col-lg-1 col-md-1 col-sm-1">
									
									 <button name="button" type="button" class="button-dang" onclick="goBack();" value="Cancel">Cancel</button>
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
  		<%@ include file = "../../include/FooterProcess.jsp" %>
      <!-- FOOTER ENDS HERE -->
	  </div>


</body>


</html>

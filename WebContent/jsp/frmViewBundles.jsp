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
 <script language="javascript">

//window.onload=call();
 function popitup(url) {
	 if(confirm('This is a governed WebService. \n Please raise a request approval to modify.'))
	 {
		newwindow=window.open(url,'name','height=450,width=800,scrollbars=no,resizable=no,top=300,left=500,toolbar=no,menubar=no,dialog=yes');
                        if (window.focus) {newwindow.focus()}
                        return false;
	 }
	 else
	 {
		 return;
	 }

}
 
function callEdit(status,artiUid,lifecycleId,processname,proceDesc){
	 
	
	 
	 
	  if(status!='Pending'){

	 if(confirm('This is a governed Web service. \n Please raise a request to modify.'))
	 {
	strURL = "./SysMgmt.html?process=callBundlesEdit&status="+status+"&artUid="+artiUid+"&lifecycleId="+lifecycleId+"&artifactName="+processname+"&description="+proceDesc;
		window.location.href = strURL;
	 }
	 else
	 {
		 return;
	 }
	 }
	 else
		 {
		 alert ("This process has a modify request pending. Please wait until the current request is processed.");
		 
		 }
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
	  
<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">
			
				<div class="col-lg-12 col-md-12 col-sm-12">
					
						<h3 class="title"> View Web Services</h3>
					
				</div>
    
              <%String success=(String)request.getAttribute("success");
  
  if(success!=null&&success.equalsIgnoreCase("success")){%>
             	<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-4 col-md-4 col-sm-4">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6">
						<p class="asterisk">Request Submitted Successfully to the Systinet.</p>
					</div>
				</div>
              <%}%>
			  
			  <div class="col-lg-12 col-md-12 col-sm-12">
			  <div class="col-lg-1 col-md-1 col-sm-1">
			  </div>
					
					 <div class="col-lg-1 col-md-1 col-sm-1">
						 <label class="name form-div-6 subtitle" >
						   Id
						 </label>
					 </div>
					 <div class="col-lg-2 col-md-2 col-sm-2">
						 <label class="name form-div-6 subtitle" >
						  Name
						 </label>
					 </div>
					  <div class="col-lg-1 col-md-1 col-sm-1">
						 <label class="name form-div-6 subtitle" >
						 Version
						 </label>
					 </div>
					  <div class="col-lg-1 col-md-1 col-sm-1">
						 <label class="name form-div-6 subtitle" >
						Category
						 </label>
					 </div>
					<div class="col-lg-1 col-md-1 col-sm-1">
						 <label class="name form-div-6 subtitle " >
						 Domain
						 </label>
					 </div>
					<div class="col-lg-2 col-md-2 col-sm-2">
						 <label class="name form-div-6 subtitle" >
						 ProductionStatus
						  </label>
					 </div>
					 <div class="col-lg-2 col-md-2 col-sm-2">
						 <label class="name form-div-6 subtitle" >
						 GovernanceStatus
						  </label>
					 </div>
					 <div class="col-lg-1 col-md-1 col-sm-1">
 					 </div>
					 
					 </div>
             
                          

                          <%
  ArrayList BundlesData=(ArrayList)request.getAttribute("BundlesData");
    //System.out.println  ("artifactData"+artifactData.size());                                           
                                                 
   if (BundlesData != null && BundlesData.size() != 0) {  
                                                            
   Iterator iter = BundlesData.iterator();
                                                                //String [] userType = {ID, name };
                                                                while (iter.hasNext()) {
                                                                	
                                                                	
                                                                	String [] artiType=(String[])iter.next();
                                                                	String id=artiType[0];
                                                                	String name=artiType[1]; 
                                                                	String version=artiType[2];
                                                                	String status=artiType[3];
                                                                	String artiUid=artiType[4];
                                                                	String lifecycleId=artiType[5];
																	String proceDesc=artiType[6];

                                                %>
                          
						  						
					<div class="col-lg-12 col-md-12 col-sm-12">
					 <div class="col-lg-1 col-md-1 col-sm-1">
			         </div>
					
					<div class="col-lg-1 col-md-1 col-sm-1">
					<%=id%>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
					<%=name%>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
					<%=version%>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
					webservice process
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
					Active
					</div>
					
					 
						  
                            <%if(status.equalsIgnoreCase("Pending")){%>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-2" align="left">Modify Request <%=status%></label>
							</div>
                            
                            <%}else{%>
                             <div class="col-lg-2 col-md-2 col-sm-2">
						     Active
							 </div> 
							 
                            <%}%>
							
							<div class="col-lg-1 col-md-1 col-sm-1">
                           <a href="#" onclick="callEdit('<%=status%>','<%=artiUid%>','<%=lifecycleId%>','<%=name%>','<%=proceDesc%>')" style="border-bottom-style:groove;border-bottom-width:thin;border-bottom-color:#66CCFF;color:#3366FF;">Move Lifecycle Stage</a>
                         </div>
						 </div>
                          <%}} else{ %>
						  
                        <div class="col-lg-12 col-md-12 col-sm-12">
						   
							<div class="col-lg-5 col-md-5 col-sm-5">
							</div>
							<div class="col-lg-7 col-md-7 col-sm-7">
							<label class="name form-div-2" align="left">No DATA</label>
							
						</div>
					</div>
                          <%  }%>
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

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
 <script language="javascript">

//window.onload=call();
 
  function popitup(url) {
	
		newwindow=window.open(url,'name','height=170,width=350,scrollbars=no,resizable=no,top=300,left=500,toolbar=no,menubar=no,dialog=yes');
                        if (window.focus) {newwindow.focus()}
                        return false;
	}
 
  function callAdd(id,name,version,govStat){

		 
		strURL = "./SysMgmt.html?process=webserviceProce&id="+id+"&wsName="+name+"&wsVersion="+version+"&txtName=Add BPEL"+"&govStat="+govStat;
			window.location.href = strURL;
					
		       }
  
  function callEndGov(fwd,artiId,govStat){

		 
		strURL = "./SysMgmt.html?process=callEndGov&fwd="+fwd+"&name=ws"+"&artifactId="+artiId+"&govstatus="+govStat;
				window.location.href = strURL;
					
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
    
    <!--========================================================
CONTENT
=========================================================-->

<div class="content indent">

	<div class="thumb-box14">
        <div class="container">
            <div class="row">
	
      
     
	   					 <div class="col-lg-12 col-md-12 col-sm-12">

							<h3 class="title"> View Web Service Artifact List</h3>
						
						 </div>
	  
	  
              <%
		  String gov=(String)request.getAttribute("stopGov");
		  String id=(String)request.getAttribute("id");
		   String ErrorMsg=(String)request.getAttribute("ErrorMsg");
		  
		  if(ErrorMsg!=null){%>
              <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="col-lg-8 col-md-8 col-sm-8 col-sm-offset-3">
                <p class="asterisk"><%=ErrorMsg%></p>
				</div>
			</div>
                <%
		  }else{	  
		  ErrorMsg="";
		  }
		  if(id==null)id="";
		  if(gov==null)gov="";
		  System.out.println("gov==="+gov+"==id=="+id);
		  %>
              
			  
                      <form action="SysMgmt.html" name="frmWSArti" id="frmWSArti">
                        <input type="hidden" name="process" value="callArtifactWSList"/>
						
						
					 	
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="table-row-line wrapper header">
						 <div class="col-lg-2 col-md-2 col-sm-2 ">
							 <label class="name form-div-6 subtitle" >
							 Name
							 </label>
						 </div>
						  <div class="col-lg-1 col-md-1 col-sm-1">
							 <label class="name form-div-6 subtitle" >
							 Version
							 </label>
						 </div>
						   <div class="col-lg-2 col-md-2 col-sm-2">
							 <label class="name form-div-6 subtitle" >
							  Consumable
							 </label>
						 </div>
						  <div class="col-lg-2 col-md-2 col-sm-2">
							 <label class="name form-div-6 subtitle" >
							  Owner
							 </label>
						 </div>
						 <div class="col-lg-2 col-md-2 col-sm-2">
							 <label class="name form-div-6 subtitle" >
							 Domain
							 </label>
						 </div>
						 <div class="col-lg-2 col-md-2 col-sm-2">
							 <label class="name form-div-6 subtitle" >
							  Governance
							  </label>
						 </div>
					 </div>
					</div>
						
                             <%
              ArrayList list=(ArrayList)request.getAttribute("list");
    //System.out.println  ("artifactData"+artifactData.size());                                           
                                                 
   if (list != null && list.size() != 0) {  
                                                            
   Iterator iter = list.iterator();
                                                                //String [] userType = {ID, name };
                                                                while (iter.hasNext()) {
                                                                	 
                                                                	
                                                                	String [] artiType=(String[])iter.next();
                                                                	//String id=artiType[0];
                                                                	String artId=artiType[0];
                                                                	String name=artiType[1]; 
                                                              
                                                                	String version=artiType[2];
                                                                	System.out.println("Inside frmViewWSArtifact jsp version of BS is ====>>>> "+version);
																	String govStat=artiType[3];
                                                                	if(version==null)version="";
                                                                	

                                                %>
												
							
			 
			 	
				      <div class="col-lg-12 col-md-12 col-sm-12">
						<div class="table-row">
					 <div class="col-lg-2 col-md-2 col-sm-2 ">
					<%=name%>
					 </div>
					 
					 <div class="col-lg-1 col-md-1 col-sm-1">
					 <%=version%>
					 </div>
					  
					 <div class="col-lg-2 col-md-2 col-sm-2">
					 
					 </div>
					 
					 <div class="col-lg-2 col-md-2 col-sm-2">
					 
					 </div>
					 
					 <div class="col-lg-2 col-md-2 col-sm-2">
					 
					 </div>
					 
					 <div class="col-lg-3 col-md-3 col-sm-3">
                             <%if(govStat.equalsIgnoreCase("Start")){%>
                             <img src="img/start-icon-button.png" alt="" width="20px" height="20px"/>
							 <button name="button" type="button" style="width:130px" onclick="callAdd('<%=artId%>','<%=name%>','<%=version%>','<%=govStat%>')" value="Start Governance" class="button-add">Start Governance</button>
								   <%}else if(govStat.equalsIgnoreCase("End")){ %>
                             <img src="img/end-icon-button.png" alt="" width="20px" height="20px" />
				  <button name="button" type="button" style="width:130px" onclick="callEndGov('callArtiWsList','<%=artId%>','<%=govStat%>')" value="End Governance "  class="button-add" >End Governance</button>
                              <%}%>
                            </div>
						</div>
						</div>
                            <%}} else{ %>
							
                     <div class="col-lg-12 col-md-12 col-sm-12">
					 
					<div class="col-lg-4 col-md-4 col-sm-4">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6">
                     <h4>NO DATA</h4>
					 </div>
					 
					
                            <%
   }
                                                %>
                            
                      
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

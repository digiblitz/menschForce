<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.*" %>

<html lang="en">
<head>
<title>Quickbooks OAuth Status</title>

<script type="text/javascript">
 
 function myFunction(){
 var qbEntityList = document.getElementById("qbEntityList").value;
 //window.opener.location.href="/menschforce/getcustomer.html?cmd=success&memberCount="+memberCount;
 if(qbEntityList != null && qbEntityList != ""){
 window.close();
 }
 }
 </script>
 
</head>
<body onLoad="setTimeout(myFunction, 5000);">
<!--header-->
<div id="header">
<!-- HEADER STARTS HERE -->
<%@ include file = "../../include/HeaderProcess.jsp" %>
  <!-- HEADER ENDS HERE -->
</div>

<!--content-->
<div class="content"> 
   
     <div class="thumb-box2">
        <div class="container">
            
			<div class="row">
                <div class="col-md-16 col-sm-12 col-xs-12 wow fadeInUp">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                           
                            <div class="caption" >
                            
                                <% List qbEntityList = (List)request.getAttribute("qbEntityList");
								if(qbEntityList != null && !(qbEntityList.isEmpty())){%>
								<input type="hidden" name="qbEntityList" id="qbEntityList" value="<%=qbEntityList.size()%>">
								OAuth Successful!!
								<%}else{%>
								OAuth Failed!!
								<%}%>
                           </div>  
                        </div>
                    </div>
                </div>
           
            </div>

            <div class="row">
			<div class="col-md-2 col-sm-2 col-xs-2 wow fadeInUp">
			</div>
                <div class="col-md-8 col-sm-8 col-xs-8 wow fadeInUp">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                           
                            <div class="caption" >
                                                            
                                <div> <!-- span>Click this button</span--> <ipp:connectToIntuit></ipp:connectToIntuit></div>
                              
                                
                           </div>  
                        </div>
                    </div>
                </div>
           
               
            </div>

			<div class="row">
                <div class="col-md-16 col-sm-12 col-xs-12 wow fadeInUp">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                           
                            <div class="caption" >
                            
                                
                           </div>  
                        </div>
                    </div>
                </div>
           
            </div>

			 <div class="row">
                <div class="col-md-16 col-sm-12 col-xs-12 wow fadeInUp">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                           
                            <div class="caption" >
                            
                              
                                
                           </div>  
                        </div>
                    </div>
                </div>
           
               
            </div>

        </div>

     </div>
     
</div>

<div>
    
    
    <!-- FOOTER STARTS HERE -->
    
   <%@ include file = "../../include/FooterProcess.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>

</body>
</html>

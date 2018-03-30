<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${exception.errMsg}</title>
</head>
<body>

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
                                                            
                             <c:if test="${not empty exception.errCode}">
								<h2>${exception.errCode} : System Errors</h2>
							 </c:if>
						
							<c:if test="${empty exception.errCode}">
								<h1>System Errors</h1>
							</c:if>
						
							<c:if test="${not empty exception.errMsg}">
								<div class="ads-in-post hide_if_width_less_800">
									<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
									<!-- new 728x90 - After1stH4 -->
									<ins class="adsbygoogle hide_if_width_less_800"
									     style="display:inline-block;width:728px;height:90px"
									     data-ad-client="ca-pub-2836379775501347"
									     data-ad-slot="7391621200"
									     data-ad-region="mkyongregion"></ins>
									<script>
									(adsbygoogle = window.adsbygoogle || []).push({});
									</script>
								</div>
								<h2>${exception.errMsg}</h2>
							</c:if>
	
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

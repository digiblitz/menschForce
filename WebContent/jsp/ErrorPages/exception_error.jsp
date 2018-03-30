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
<title>Sorry!</title>
</head>
<body>

<!--header-->
<div id="header">
<!-- HEADER STARTS HERE -->
<%@ include file = "../../include/header_new.jsp" %>
  <!-- HEADER ENDS HERE -->
</div>

<!--content-->
<div class="content"> 
   
     <div class="thumb-box2">
        <div class="container">
            
		
            <div class="row">
			<div class="col-md-2 col-sm-2 col-xs-2 wow fadeInUp">
			</div>
                <div class="col-md-8 col-sm-8 col-xs-8 wow fadeInUp">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                           
                            <div class="caption" >
                                       
                                <h2>Sorry! Something Went Wrong</h2>                   
								<h2>${exception}</h2>
								
								<div class="col-lg-12 col-md-12 col-sm-12">	
									&nbsp;
								</div>
							
								<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="col-lg-2 col-md-2 col-sm-2">
									</div>
									<div class="col-lg-6 col-md-6 col-sm-6">
										<button type="reset" class="btn-default btn3" name="Back" value="Back" onClick="history.go(-1);">Back</button>
									</div>
								</div>
	
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
    
    <%@ include file = "../../include/Footer.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>


</body>
</html>

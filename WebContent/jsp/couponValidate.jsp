<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Coupon code validation</title>
</head>
<body>
<!--header-->
<div id="header">
<!-- HEADER STARTS HERE -->
<%@ include file = "../../include/header_new.jsp" %>
  <!-- HEADER ENDS HERE -->
</div>

<%String category = (String)request.getAttribute("category");%>
<%String statusCoupon = null;
String coupon_status = null;
String coupon_value = null;
String coupon_description = null;
String coupon_valid = null;
String coupon_code = null;

statusCoupon = (String) request.getAttribute("statusCoupon");

coupon_status = (String)request.getAttribute("coupon_status");
coupon_value = (String)request.getAttribute("coupon_value");
coupon_description = (String)request.getAttribute("coupon_description");
coupon_valid = (String)request.getAttribute("coupon_valid");
coupon_code = (String)request.getAttribute("coupon_code");
%>
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
                             <form action="signUp.html?signUpProcess=aftergetcoupon" name="" method="post">
                             <input type="hidden" name="category" value="<%=category %>">
                             
                             <%if(statusCoupon != null && statusCoupon.equalsIgnoreCase("success")){ %>
                                <p class="title" >Coupon Code Successfully validated ! </p>
                                
                                <input type="hidden" name="coupon_status" value="<%=coupon_status%>"/>
                                <input type="hidden" name="coupon_value" value="<%=coupon_value%>"/>
                                <input type="hidden" name="coupon_description" value="<%=coupon_description%>"/>
                                <input type="hidden" name="coupon_valid" value="<%=coupon_valid%>"/>
                                <input type="hidden" name="coupon_code" value="<%=coupon_code%>"/>
                                
                                <input type="submit" name="Submit" value="Go to Sign Up" class="btn-default btn3"/>
                                
                                <%}else{ %>
                                <p class="title" >Invalid Coupon code ! Please give valid coupon code.</p>
                                <strong><a href="signUp.html?signUpProcess=askcoupon&requiredcoupon=yescoupon">Go back to Apply Coupon Code</a></strong>
                                <%} %>
                                <p style="font-size:14px">
                               
                              	
                                </p>
                                 
                                
                                </form>
                              
                                
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
    
    <%@ include file = "../../include/Footer.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>
</body>
</html>

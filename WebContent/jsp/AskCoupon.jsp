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
<title>Coupon Enquiry</title>
</head>
<body>
<!--header-->
<div id="header">
<!-- HEADER STARTS HERE -->
<%@ include file = "../../include/header_new.jsp" %>
  <!-- HEADER ENDS HERE -->
</div>

<!--content-->
<style>
#coupon label.error{
	color:red;
}
</style>
<script src="js/jquery.validate.js"></script>

<script>
$(document).ready(function() {
	//alert("working");

 $("#coupon").validate({
			 			 
			rules: {
				requiredcoupon: {
					required:true
					
				}
			},
				messages: {
					requiredcoupon:{
						required:"This field is required"
					}
				},
									errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}
  });
    });

  
  </script>
  <%String category = (String)request.getAttribute("category");
  String productPrice = (String)request.getAttribute("productPrice");
  String productPlan = (String)request.getAttribute("productPlan");
  %>
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
                             <form action="signUp.html?signUpProcess=askcoupon" name="" id="coupon" method="post">
                                <input type="hidden" name="category" value="<%=category %>">
								<input type="hidden" name="productPrice" value="<%=productPrice %>">
								<input type="hidden" name="productPlan" value="<%=productPlan %>">
                                
                                <p class="title" >Do you have a Coupon code : 
                                <input type="radio" name="requiredcoupon" value="yescoupon"/><strong>yes</strong>
								&nbsp;

                                <input type="radio" name="requiredcoupon" value="nocoupon"/><strong>no</strong>
                              
                                </p>
                               
                                <input type="submit" name="Submit" value="Submit" class="btn-default btn3"/>
								&nbsp;

								<input type="button" name="Cancel" value="Cancel" 
								onclick="window.location.href='signUp.html?signUpProcess=getStarted'" class="btn-default btn3"/>
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

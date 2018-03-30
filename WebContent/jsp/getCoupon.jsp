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
<title>Get Coupon</title>
</head>
<body>
<!--header-->
<div id="header">
<!-- HEADER STARTS HERE -->
<%@ include file = "../../include/header_new.jsp" %>
  <!-- HEADER ENDS HERE -->
</div>
<style>
#getcoupon label.error{
	color:red;
}
</style>
<!--content-->
<script src="js/jquery.validate.js"></script>
<script>
$(document).ready(function() {
	//alert("working");

 $("#getcoupon").validate({
			 			 
			rules: {
				coupon_code: {
					required:true
					
				}
			},
				messages: {
					coupon_code:{
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
								<form action="signUp.html?signUpProcess=getcoupon" name="" id="getcoupon" method="post">
								<input type="hidden" name="category" value="<%=category %>">
								<input type="hidden" name="productPrice" value="<%=productPrice %>">
								<input type="hidden" name="productPlan" value="<%=productPlan %>">
								
                                <p class="title" >Enter the coupon code : <input type="text" name="coupon_code" value=""/>
								                              
                                </p>
                              
                                <input type="submit" name="Submit" value="Submit" class="btn-default btn3"/>
								 &nbsp;

				<input type="button" name="Cancel" value="Back" onclick="window.location.href='signUp.html?signUpProcess=preaskcoupon'" class="btn-default btn3"/>
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

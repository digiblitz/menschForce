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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<title>Search Page</title>
</head>
<style>
    .vertical-line {
        width: 0;
        border: 1px solid #000000;
        height: 1px
		 
    }
</style>
<style type="text/css">
#customerSearch label.error{
	color:red;
}
.divtext{
      height: 22px;
    width: 610px;
    padding: 15px 0 15px 0;
    margin: 0 auto;
	
	}
	
	.labeltext{
	   float:left;
	   font-size:18px;
	}
	.spantext{
	   float:right
	}
.container_12{
		width:1170px!important;
		margin:0 auto;
		position:relative;
		
	}
	.container_12 .grid_12 {
    	width: 1170px!important;
		    padding: 10px 0;
	}
	.submitlabeltext{
	   float:none;
	   font-size:18px;
	}
	.bg1 {
    position: relative;
    background:  url(../images/bg1.jpg) 0 0 repeat;
	height:289px
}
	.box{    width: 350px!important;
    padding: 7px!important;
    border: none;
    border-radius: 4px;
    box-shadow: 0 1px 7px #C70101;
	margin:0!important;
	text-align:left!important;}
	.btnstyle{
	background-color: transparent;
	border:thin;
	border-style: groove;
	cursor:pointer;  
	width:61px; 
	height:29px;
	outline-color:#009FBC;
	color:009FBC
	}
	.btnstyle:hover{
	background-color: #009FBC;
	border:thin;
	border-style: groove;
	cursor:pointer;  
	width:61px; 
	height:29px;
	outline-color:#009FBC;
	color:#FFFFFF
	}
</style>
<body>
<!--========================================================
HEADER
=========================================================-->
  <div id="header">
    <!-- HEADER STARTS HERE -->
<%@ include file = "../../include/menschForceHeader.jsp" %>
 <!-- HEADER ENDS HERE -->
    </div>
<script src="js/jquery.validate.js"></script>
<script src="js/additional-methods.js"></script>
<script src="js/profilefieldvalidation.js"></script>
<!--=======content================================-->
     
<div class="content indent">
	<div class="thumb-box14">
        <div class="container">
            <div class="row">
			
                
				<div class="col-lg-2 col-md-2 col-sm-2">
				</div>
				 <div class="col-lg-8 col-md-10 col-sm-4">
				 
				 	<fieldset>
					
				 
						<form action="customerAction.html?custProcess=getCustomerDetails" name="customerSearch" method="post">
						     <center>
							 <div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>	Customer Id<span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							<label class="name form-div-6" align="left">
					       <input  type="text" name="customerId"  placeholder="Search Customer Information" class="form-control" >
							</label>
							</div>
						</div>
						 <div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						 </div>
						<label class="submitlabeltext">
						<button type="submit" class="button-add" id="submit" name="Insert" value="Search">Search</button>
                       </label>
					   </center>
					   
					   
						 </form>
						 </fieldset>
						 
						 </div>
						</div>
						</div>
						</div>
						</div>
						
						
		<!--table border="0" width="300" align="center" bgcolor="#e9f" style="margin-right:auto; margin-left:auto;">
			<tr>
				<td colspan=2 style="font-size: 12pt;">
					<h1>Search Customer Information</h1>
				</td>
			</tr>
			<tr>
				<td><b>Customer Id</b></td>
				<td>: <input type="text" name="customerId">
				</td>
			</tr>
			<tr>
				<td colspan=2 align="center"><input type="submit"
					value="Search"></td>
			</tr>
		</table>
	</form>
			</div>
                
 			 </div>
 			 </div-->
 			</div>
			
			 <div>			 
			</div></div>
			
 		 
                 
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

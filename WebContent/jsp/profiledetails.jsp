<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.regex.*" %>
<%@ page import="com.hlchorse.form.util.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Profile Details</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<script src="js/basic.js" type="text/javascript" ></script>
<script src="js/validate.js" type="text/javascript" ></script>
<script src="js/cscombo_new.js" type="text/javascript" ></script>
<script src="js/frmMembRegi.js" type="text/javascript" ></script>
<script src="js/EditMemberUserReg.js" type="text/javascript" ></script>
</head>
<%! 

String  nullCheck(String fieldName){
	String retValue = "";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
}

%>
<style>
    .vertical-line {
        width: 0;
        border: 1px solid #000000;
        height: 1px
		 
    }
	
	.asterisk{
		color:red;
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

<%String mail=(String)request.getAttribute("e_mail");

System.out.println("In JSP =================="+mail);%>
<!--=======content================================-->

<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            
			<div class="row">
			
				<div class="col-lg-12 col-md-12 col-sm-12">
					
                   <h3 class="title">User Profile Details</h3>
				  
				 </div>
			
				 
		<form  action="master.html?profileCmd=updateprofile&e_mail=<%=nullCheck((String)request.getAttribute("e_mail"))%>" method="post" name="uploadfile">
                    <%
					String status=(String)request.getAttribute("upstatus");
					 if(status!=null){
					 %>
			
					
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-2 col-md-2 col-sm-2">
					</div>
					<div class="col-lg-4 col-md-4 col-sm-4">
                     <label class="name form-div-6" align="left">  
                       <font color="#FF0000"><%=status%></font>
					</label>
			   
					</div>
				 </div>
				 
				<%}%>
				
				</div>	<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>	User Name <span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							 <input  type="text" name="username" class="form-control" value="<%=nullCheck((String)request.getAttribute("username"))%>">

							
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
				
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Password <span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							 <input  type="text" name="password" class="form-control" value="<%=nullCheck((String)request.getAttribute("password"))%>" > 


							
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Business Name <span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
								<input  type="text" name="institutionname" class="form-control" value="<%=nullCheck((String)request.getAttribute("institutionname"))%>" >

							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
							
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Email<span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
								<input  type="text" name="email" id="email" class="form-control"  value="<%=nullCheck((String)request.getAttribute("e_mail"))%>" >
  

							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Customer Id<span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
								 <input  name="customerid" id="customerid" class="form-control" value="<%=nullCheck((String)request.getAttribute("customer_id"))%>" readonly>
  
  

							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
					</div>
					
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12">
								<div class="col-lg-5 col-md-5 col-sm-5">
								</div>
								<div class="col-lg-1 col-md-1 col-sm-1">
										<button type="submit" class="button-add" name="Insert" value="Update">Update</button>
								</div>
								<div class="col-lg-1 col-md-1 col-sm-1">
										<button type="button" class="button-dang" name="button" value="Cancel" onclick="javascript:history.back(-1);">Cancel</button>
								</div>
							</div>
						</div>
				</form>

             </div>
			</div>
		</div>
	</div>
</div>
<!--========================================================
FOOTER
=========================================================-->
  <div>
   
    
    <!-- FOOTER STARTS HERE -->
    
  		<%@ include file = "../../include/menschForceFooter.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
  </div>  

</body>

</html>

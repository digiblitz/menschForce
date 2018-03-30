<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
       
            <div class="row">			
				
				
				
				
			
				<div class="col-lg-11 col-md-11 col-sm-11">
					
						<h3 class="title"> Candidate Status Details</h3>
					
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
				
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
					<div class="col-lg-3 col-md-3 col-sm-3">
					</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
							<label class="name form-div-6" >
							
							Candidate FullName
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("CandidateFullName"))%> </label>
						</div> 
						<div class="col-lg-2 col-md-2 col-sm-2">
						</div>
				</div>
				
				 <div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
					<div class="col-lg-3 col-md-3 col-sm-3">
					</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
							<label class="name form-div-6" >
							CandidateStatusValue

                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("CandidateStatusValue"))%> </label>
						</div> 
						<div class="col-lg-2 col-md-2 col-sm-2">
						</div>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 
				  <div class="col-lg-12 col-md-12 col-sm-12">
				
					<div class="col-lg-3 col-md-3 col-sm-3">
					</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
							<label class="name form-div-6" >
							Unique Reference

                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("UniqueReference"))%> </label>
						</div> 
						<div class="col-lg-2 col-md-2 col-sm-2">
						</div>
				</div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
					<div class="col-lg-3 col-md-3 col-sm-3">
					</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
							<label class="name form-div-6" >
							CANID

                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("CANID"))%> </label>
						</div> 
						<div class="col-lg-2 col-md-2 col-sm-2">
						</div>
				</div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
					<div class="col-lg-3 col-md-3 col-sm-3">
					</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
							<label class="name form-div-6" >
							Candidate ContactNumber

                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("CandidateContactNumber"))%> </label>
						</div> 
						<div class="col-lg-2 col-md-2 col-sm-2">
						</div>
				</div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
					<div class="col-lg-3 col-md-3 col-sm-3">
					</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
							<label class="name form-div-6" >
							RequirementID

                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("RequirementID"))%> </label>
						</div> 
						<div class="col-lg-2 col-md-2 col-sm-2">
						</div>
				</div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
					<div class="col-lg-3 col-md-3 col-sm-3">
					</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
							<label class="name form-div-6" >
							Candidate EMail

                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("CandidateEMail"))%> </label>
						</div> 
						<div class="col-lg-2 col-md-2 col-sm-2">
						</div>
				</div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
					<div class="col-lg-3 col-md-3 col-sm-3">
					</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
							<label class="name form-div-6" >
							Account Manager Assigned To Value

                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("AccountManagerAssignedToValue"))%> </label>
						</div> 
						<div class="col-lg-2 col-md-2 col-sm-2">
						</div>
				</div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
					<div class="col-lg-3 col-md-3 col-sm-3">
					</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
							<label class="name form-div-6" >
							Recruiter Assigned To Value

                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("RecruiterAssignedToValue"))%> </label>
						</div> 
						<div class="col-lg-2 col-md-2 col-sm-2">
						</div>
				</div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-5">
							<button type="reset" class="btn-default btn3" name="Back" value="Back" onClick="history.go(-1);">Back</button>
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
    
   <%@ include file = "../../include/FooterProcess.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>
</body>
</html>

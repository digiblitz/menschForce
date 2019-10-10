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
   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Rate Negotiation</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="css/contact-form.css">

<%
String existStatus = null;
existStatus = (String)request.getAttribute("existStatus");
System.out.println("existStatus--------------"+existStatus);
%>

<style>
#contact-form label.error{
	color:red;
}
</style>

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
  <script src="js/jquery.validate.js"></script>
<script src="js/additional-methods.js"></script>
<script src="js/fieldvalidation.js"></script>
<!--========================================================
CONTENT
=========================================================-->
 
<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
        
        <%if(existStatus != null && existStatus.equalsIgnoreCase("exist")){ %>
        
        <div class="row">
			
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
					<div class="col-lg-7 col-md-7 col-sm-7">
                   <h3></h3>
				   </div>
				 </div>
				 
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-5 col-md-5 col-sm-5">
								</div>
					<div class="col-lg-4 col-md-4 col-sm-4">
					<label class="name form-div-6">
						<font color="#018dce">Rate Negotiation</font>
					</label>
				   </div>
				 </div>
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-4 col-md-4 col-sm-4">
					</div>		
									
					<div class="col-lg-4 col-md-4 col-sm-4">
							You already negotiated the rate for this Requirement
							</div>
					<div class="col-lg-4 col-md-4 col-sm-4">
					</div>
				</div>
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-6 col-md-6 col-sm-6">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6">
						<button type="reset" class="btn-default btn3" name="Back" value="Back" onClick="history.go(-1);">Back</button>
					</div>
				</div>
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
					<div class="col-lg-7 col-md-7 col-sm-7">
                   <h3></h3>
				   </div>
				 </div>
				</div>
				<%}else{ %>
            <div class="row">
			
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
					<div class="col-lg-7 col-md-7 col-sm-7">
                   <h3></h3>
				   </div>
				 </div>
				 
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-5 col-md-5 col-sm-5">
								</div>
					<div class="col-lg-4 col-md-4 col-sm-4">
					<label class="name form-div-6">
						<font color="#018dce">Rate Negotiation</font>
					</label>
				   </div>
				 </div>

	<%
						 ArrayList PostReqList = (ArrayList) request.getAttribute("PostReqList");
          					 if(PostReqList!=null && PostReqList.size()!=0){
							Iterator it = PostReqList.iterator();
							while(it.hasNext()){
								String[] s = (String[])it.next();
								String postReqUniqueId = s[0];
								String requirementId = s[1];
								String jobPostCompanyName = s[2];
								String ClientReferenceID = s[3];
								String ReferenceID = s[4];
								String ClientIndustry = s[5];
								String PositionID = s[6];
								String Position = s[7];
								String InterviewProcess = s[8];
								String VisaAccepted = s[9];
								String Skills = s[10];
								String JobType = s[11];
								String LocationField = s[12];
								String NumberOfOpenings = s[13];
								String RecruiterEmail = s[14];
								String StartDate = s[15];
								String EndDate = s[16];
								String RequirementStatus = s[17]; 
								String Rate = s[18];
								String Duration = s[19];
								String LocalRequired = s[20];
								String Salary = s[21];
								String FlexiblityOnSalary = s[22];
								String FlexiblityOnHrlyRate = s[23];
								String DateOnHold = s[24];
								String ExtraDocumentsRequired = s[25];
								String RequiredExperience = s[26];
								String Notes = s[27];
								String jobResponsibilites = s[28];
								String jobPostDate = s[29];
								String JobTitle = s[30];
							

		                %>
		 
		<form name="createRateNegotiation" id="contact-form" method="post" action="createRateNegotiation.html" class="formcss" onsubmit="return validate();" >
		<input type="hidden" name="postReqUniqueId" value="<%=postReqUniqueId%>" />
		<input type="hidden" name="postReqId" value="<%=requirementId%>" />
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>

						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>		
									
							<div class="col-lg-2 col-md-2 col-sm-2">
							Requirement ID
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=requirementId%>
							
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
							<div class="col-lg-2 col-md-2 col-sm-2">
							Negotiate Rate(In Dollar)
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							  <input type="text" name="negotiateRate" id="negotiateRate" class="textboxOne" placeholder="Negotiate Rate(In Dollar)" onkeypress="return isNumber(event)"/>
							
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
							<div class="col-lg-2 col-md-2 col-sm-2">
							Negotiate Reason
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							  
							<textarea name="negotiateReason" class="textboxOne" rows='3' cols='5' placeholder="Negotiate Reason"></textarea>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
										
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-6 col-md-6 col-sm-6">
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							 <button type="submit" class="btn-default btn3" name="Submit" value="Submit">Submit</button>
							 <button type="reset" class="btn-default btn3" name="cancel" value="cancel">Cancel</button>
							 <button type="reset" class="btn-default btn3" name="Back" value="Back" onClick="history.go(-1);">Back</button>
							</div>
							</div>
        		</form>
        		
        		 <%}}%>
				
			</div>
			<%} %>
		</div>
	</div>
</div>

<!--========================================================
FOOTER
=========================================================-->
  <div>
    
    
    <!-- FOOTER STARTS HERE -->
    
   <%@ include file = "../../include/Footer.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>

 
</body>
</html>


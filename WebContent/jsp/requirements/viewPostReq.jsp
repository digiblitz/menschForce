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
   
   
   <%@ page import = "java.util.Base64" %>
   
   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>MemberUserRegistration</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="icon" href="img/favicon.ico" type="image/x-icon">

<script src="js/basic.js" type="text/javascript" ></script>
<script src="js/cscombo_new.js" type="text/javascript" ></script>
<script src="js/frmMembRegi.js" type="text/javascript" ></script>
<script src="js/bootstrap.min.js"></script>

<script  src="js/ts_picker.js"></script>
<script src="js/calendar2.js" type="text/javascript"></script>
<script src="js/datetimepicker_css.js"></script>

<link rel="stylesheet" href="build/css/intlTelInput.css">

<style>
img.image1{

    right: 25px;
    position: absolute;
    top: 10px;
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
  
<!--========================================================
CONTENT
=========================================================-->
 
<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">
			
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
					<div class="col-lg-7 col-md-7 col-sm-7">
                   <h3></h3>
				   </div>
				 </div>
				 
<%String pagestatus = null;
String jobPostUserRoleName=null;
String jobStatus=null;
 jobPostUserRoleName = (String)session.getAttribute("jobPostUserRoleName");
 
  String jobPostCompanyCategory = (String)session.getAttribute("jobPostCompanyCategory");
  
pagestatus =(String) request.getAttribute("reqpageStatus");
jobStatus =(String) request.getAttribute("jobStatus");%>
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
								String minExperience = s[26];
								String Notes = s[27];
								String jobResponsibilites = s[28];
								String jobPostDate = s[29];
								String JobTitle = s[30];
								String maxExperience = s[32];
								System.out.println("Company Category====in jsp"+RecruiterEmail);
				System.out.println("Company Category====in jsp"+jobPostCompanyCategory);
				
				
				 Base64.Encoder encoder = Base64.getUrlEncoder();  
			        // Encoding URL  
			        String encode = encoder.encodeToString(RecruiterEmail.getBytes());  
			        System.out.println("Encoded URL: "+encode);  
				
				
				
				
			    
				
		                %>
		 
		<form name="postReq" id="postReq" method="post" action="#" class="formcss" onsubmit="return validate();" >
		
			
							<div class="col-lg-12 col-md-12 col-sm-12">
								<h3 class="title">Requirement Details</h3>
								
							 </div>
							 
						
					
							
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
		<% if(pagestatus == null){ %>
						 <div class="col-lg-6 col-md-6 col-sm-6">
							
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							<label class="name form-div-6">
							<%if(RequirementStatus!=null && (RequirementStatus.equalsIgnoreCase("open") || RequirementStatus.equalsIgnoreCase("onhold"))){%>
								<font color="#018dce"><a href="ViewApplyJobVacancy.html?RID=<%=requirementId%>&User=<%=encode%>" class="btn-default btn3">Apply To This Job</a></font>
							<%
							System.out.println("encode kdebug0 "+RecruiterEmail);
							System.out.println("encode kdebug1 "+encode);
							
							}else{%>
							<font color="#018dce"><a href="" class="btn-default btn3">Job Closed</a></font>
							<%}%>
							</label>
						   </div>
						  <%if(jobPostCompanyCategory != null && jobPostCompanyCategory.equalsIgnoreCase("Buyer")){ %>
							<div class="col-lg-1 col-md-1 col-sm-1">
								<button  type="button"class="btn-default btn3" value="Edit" onclick="window.location.href='EditPostRequirementByUniquePostId.html?uniqueId=<%=postReqUniqueId%>&jobStatus=<%=jobStatus %>'"> Edit</button>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
								&nbsp;
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
								<button  type="button"class="btn-default btn3" value="Edit" onclick="window.location.href='RepostPostRequirementByUniquePostId.html?uniqueId=<%=postReqUniqueId%>&jobStatus=<%=jobStatus %>'"> Repost</button>
							</div>
						<%}%>
						 </div>
		
		  				<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
                <%} %>
				<div class="row">
					<div class="col-lg-6 col-md-6 col-sm-6">
						<div class="col-lg-12 col-md-12 col-sm-12">
								
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">JobTitle</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=JobTitle%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
							<div class="col-lg-12 col-md-12 col-sm-12">
								
							
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Company Name</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=jobPostCompanyName%>
							
							</div>
						
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
								
									
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Post Date</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=jobPostDate%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
									
									
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Requirement ID</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=requirementId%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>

						<div class="col-lg-12 col-md-12 col-sm-12">
							
									
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Reference ID</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=ReferenceID%>
							
							</div>
						
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
								
									
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Client Reference ID</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=ClientReferenceID%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Client Industry</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=ClientIndustry%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
								
									
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Position ID</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=PositionID%>
							
							</div>
						
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
									
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Position </label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=Position%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
								
										
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Interview Process</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=InterviewProcess%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
								
									
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Visa Accepted</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=VisaAccepted%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
								
									
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Skills</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=Skills%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
								
										
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Job Type:</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=JobType%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Location Field:</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=LocationField%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
						<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">No of Openings:</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=NumberOfOpenings%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
								
									
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Responsibilities</label>
							</div>
							<div class="col-lg-7 col-md-7 col-sm-7">
							  <%=jobResponsibilites%>
							
							</div>
							
						</div>
					</div>	
						
					<div class="col-lg-6 col-md-6 col-sm-6">	
						<div class="col-lg-12 col-md-12 col-sm-12">
								
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Required Experience</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=minExperience%> - <%=maxExperience%>years
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
									
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Recruiter Email</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=RecruiterEmail%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Start Date</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=StartDate%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
							<div class="col-lg-12 col-md-12 col-sm-12">
								
									
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">End Date</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=EndDate%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
					  <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Recruitment Status</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=RequirementStatus%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
					   <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Rate in Dollars</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=Rate%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Duration</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=Duration%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Local Required</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=LocalRequired%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Job Description</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=Notes%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Salary in Dollars</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=Salary%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Flexibility on Salary</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=FlexiblityOnSalary%>
							
							</div>
						
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							 <label class="name">Flexibility on Hourly Rate</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							 <%=FlexiblityOnHrlyRate%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
					<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							<label class="name">Date on Hold</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=DateOnHold%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
				<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							<label class="name">Extra Documents Required</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=ExtraDocumentsRequired%>
							
							</div>
							
						</div>
					</div>	
				</div>
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4  col-md-4 col-sm-4">
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 col-sm-offset-1">
							<button type="reset" class="button-dang" name="Back" value="Back" onClick="history.go(-1);">Back</button>
							</div>
						
						</div>
						
        		
				 <%
                                              }
					 }
					  else {%>
					  
                      <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-5 col-md-5 col-sm-5">
							</div>
							<div class="col-lg-7 col-md-7 col-sm-7">
								No Records are Found
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
                     
                      <% } %>
				</form>
			</div>
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


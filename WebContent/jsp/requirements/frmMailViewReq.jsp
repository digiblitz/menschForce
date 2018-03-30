<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
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
<script src="js/tm-scripts.js"></script>
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
  <%@ include file = "../../include/header_new.jsp" %>
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
							

		                %>
		 
		<form name="postReq" id="postReq" method="post" action="#" class="formcss" onsubmit="return validate();" >
		
			
							<div class="col-lg-12 col-md-12 col-sm-12">
								<h3 class="title">Requirement Details</h3>
								
							 </div>
							 
						
					
							
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
		
						 <div class="col-lg-6 col-md-6 col-sm-6">
							
							<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-1 ">
							<label class="name form-div-6">
								<font color="#018dce"><a href="#Popupuser" class="btn-default btn3">Apply To This Job</a></font>
							</label>
						   </div>
						
						 </div>
		
		  				<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
              
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
							 <label class="name">Notes</label>
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
<style>

.session-overlay {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.7);
  transition: opacity 500ms;
  visibility: hidden;
  opacity: 0;
  padding:150px
}
.session-overlay.session-open:target {
  visibility: visible;
  opacity: 1;
}



.session-popup h2 {
  margin-top: 0;
  color: #333;
  font-family: Tahoma, Arial, sans-serif;
}


.session-popup {
  margin: 70px auto;
  padding: 20px;
  background: #fff;
  border-radius: 5px;
  width: 50%;
  height:30%;
  position: relative;
  transition: all 5s ease-in-out;
}

.session-popup .session-close {
 /* position: absolute; */
  top: 20px;
  right: 30px;
  transition: all 200ms;
 /* font-size: 30px;
  font-weight: bold; */
  text-decoration: none;
  color: #333;
}

 
.session-popup .session-close:hover {
  color: #06D85F;
}
.session-popup .session-content {
  max-height: 30%;
  overflow: auto;
}

@media screen and (max-width: 700px){
  .box{
    width: 100%;
  }
  .popup{
    width: 100%;
  }
}


</style>
<div id="Popupuser" class="session-overlay session-open" style="background: rgba(0, 0, 0, 0.709804); display: block;">
	<div class="session-popup" >	
		<h4 style="text-transform:none;font-size:1.5em;font-family:times new roman;font-weight:500">You need to be logged into the system, to submit your candidates.</h4>
		
		<!--<div class="session-content">
			<a href='frmHomeForTrial.html'>Click here to Login</a>
			
		</div>-->
		<div align="center" style="padding:10px;"><button class=" button-add" style="align:center" onclick="location.href='frmHomeForTrial.html'">Ok</button></div>
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

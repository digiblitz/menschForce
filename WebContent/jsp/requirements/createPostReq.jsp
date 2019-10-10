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
<title>New Post Requirement</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="icon" href="img/favicon.ico" type="image/x-icon">


<link rel="stylesheet" href="css/contact-form.css">
<link rel="stylesheet" href="build/css/intlTelInput.css">





<style>
img.image1{

    right: 25px;
    position: absolute;
    top: 10px;
}
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
<script src="js/bootstrap.min.js"></script>
<script src="js/tm-scripts.js"></script>
<script  src="js/ts_picker.js"></script>
<script src="js/calendar2.js" type="text/javascript"></script>
<script src="js/datetimepicker_css.js"></script>





<!--========================================================
CONTENT
=========================================================-->
 
<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">
			
				<div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-7 col-md-7 col-sm-7 col-sm-offset-3">
                   <h3></h3>
				   </div>
				 </div>
				 
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-3" id="commonBG">
					<label class="name form-div-6">
						<font color="#018dce">Fill Up the details below..</font>
					</label>
				   </div>
				 </div>

	
		 
		<form name="postReq" id="contact-form" method="post" action="PostRequirements.html" class="formcss" onsubmit="return validate();" >
		
		
		
		  				<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
                
							<!--div class="col-lg-12 col-md-12 col-sm-12">
								<div class="col-lg-2 col-md-2 col-sm-2">
								</div>
								<div class="col-lg-10 col-md-10 col-sm-10">
								<label class="name form-div-6">
									<font color="#018dce">Basic Information:</font>
								</label>
							   </div>
							 </div>-->
							 
						
					
							
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
					
						<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Job Title
							 <font color="#FF0000"><span class="asterisk">*</span></font>	
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							  <input type="text" name="JobTitle" id="JobTitle" class="form-control" placeholder="Job Title" />
							
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>

						<!--div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <input type="text" name="requirementId" id="requirementId" class="textboxOne" placeholder="Requirement ID" />
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div-->

						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Reference ID
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							  <input type="text" name="ReferenceID" id="ReferenceID" class="form-control" placeholder="Reference ID" />
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Client Reference ID
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							  <input type="text" name="ClientReferenceID" id="ClientReferenceID" class="form-control" placeholder="Client Reference ID" />
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Client Industry
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							 <input type="text" name="ClientIndustry" class="form-control" placeholder="Client Industry" />
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Position ID
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							<input type="text" name="PositionID" class="form-control" placeholder="Position ID"/>
							 
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Position
							<font color="#FF0000"><span class="asterisk">*</span></font>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							 <input type="text" name="Position" id="Position" class="form-control" placeholder="Position"/>
							 <font color="#FF0000"><span id="position"></span></font>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Interview Process
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							 <input type="text" name="InterviewProcess" class="form-control" placeholder="Interview Process"/>
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Visa Accepted
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							 <input type="text" name="VisaAccepted" class="form-control" placeholder="Visa Accepted"/>
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Skills
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							 <textarea name="Skills" class="form-control" rows='3' cols='5' placeholder="Skills"></textarea>
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Job Type
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							 <select name="JobType" id="select" class="large form-control">
								<option value="" selected="selected">Select One</option>
								<option value="permanent">Permanent</option>
								<option value="contract">Contract</option>
								
							  </select>
							 
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Location Field
							 <font color="#FF0000"><span class="asterisk">*</span></font>	
							</div>
						  <div class="col-lg-4 col-md-4 col-sm-4">
							<input name="LocationField" type="text"  id="LocationField" class="form-control"  placeholder="Location Field"/>
							<!--font color="#FF0000"><span class="asterisk">*</span><span id="location"></span></font>-->							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							No of Openings
							</div>
						  <div class="col-lg-4 col-md-4 col-sm-4">
							<input name="NumberOfOpenings" type="text"   class="form-control"  placeholder="No of Openings" onkeypress="return isNumber(event)"/>
													</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Responsibilities
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							 <textarea name="jobResponsibilities" class="textboxOne" rows='3' cols='5' placeholder="Responsibilities"></textarea>
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Required Experience
							 <font color="#FF0000"><span class="asterisk">*</span></font>
							</div>
							<div class="col-lg- col-md-2 col-sm-2">
							<input name="minExperience" type="text"   id="min_experience" class="form-control"  placeholder="minimum years" onkeypress="return isNumber(event)"/>
													
							
							
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<input name="maxExperience" type="text"   id="max_experience" class="form-control"  placeholder="maximum years" onkeypress="return isNumber(event)"/>
							 
							
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3">
							(For eg: 1, 1+,2,2+)
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Recruiter Email
							 <font color="#FF0000"><span class="asterisk">*</span></font>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							<input type="text" name="RecruiterEmail" id="RecruiterEmail" class="form-control"  placeholder="Recruiter Email"/>
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Start Date
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							 <input type="text" name="StartDate" id="StartDate" class="form-control" placeholder="Start Date" readonly />
							
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal" align="left">
							<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('StartDate')" />
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>

						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							End Date
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							 <input type="text" name="EndDate" id="EndDate" class="form-control" placeholder="End Date" readonly />
							
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal" align="left">
							<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('EndDate')" />
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Recruitment Status
							<span class="asterisk">*</span>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							  <select name="RequirementStatus" id="RequirementStatus" class="large form-control">
								<option selected="selected" value="">Select One</option>
								
								<option value="open">open</option>
								<option value="closed">closed</option>
								<option value="onhold">onhold</option>
								<option value="declined">Declined</option>
								
							  </select>
							 
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
					
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Rate in Dollars
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							<input type="text" name="Rate" id="Rate" class="form-control"  placeholder="$" onkeypress="return isNumber(event)"/>
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Duration
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							<input type="text" name="Duration" id="Duration" class="form-control"  placeholder="Duration"/>
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
			   
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Local Required
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							<input type="text" name="LocalRequired" id="LocalRequired" class="form-control"  placeholder="Local Required"/>
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Job Description
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							 <textarea name="Notes" class="form-control" rows='3' cols='5' placeholder="Job Description"></textarea>
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Salary in Dollars
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							<input type="text" name="Salary" id="Salary" class="form-control"  placeholder="$" onkeypress="return isNumber(event)"/>
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Flexibility on Salary
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							<input type="text" name="FlexiblityOnSalary" id="FlexiblityOnSalary" class="form-control"  placeholder="Flexibility on Salary" />
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
			   
			   			<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Flexibility on Hourly Rate
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							<input type="text" name="FlexiblityOnHrlyRate" id="FlexiblityOnHrlyRate" class="form-control"  placeholder="Flexibility on Hourly Rate" />
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Date on Hold
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							<input type="text" name="DateOnHold" id="DateOnHold" class="form-control" readonly placeholder="Date on Hold"/>
							
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal">
							<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('DateOnHold')" />
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
										
						
					<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							Extra Documents Required
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							 <textarea name="ExtraDocumentsRequired" class="form-control" rows='3' cols='5' placeholder="Extra Documents Required"></textarea>
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							 
							 
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-5">
							 <button type="submit" class="button-add" name="Submit" value="Submit">Submit</button>
							 <button type="reset" class="button-dang" name="cancel" value="cancel">Cancel</button>
							 <button type="reset" class="button-dang" name="Back" value="Back" onClick="history.go(-1);">Back</button>
							</div>
						</div>
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
    
   <%@ include file = "../../include/FooterProcess.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>

 
</body>
</html>


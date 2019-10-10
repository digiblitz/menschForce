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
<title>Edit Hot list Candidate</title>



<style>
#frmEditHotlistCandidate label.error{
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

	<script>

$(document).ready(function(){
	
$(window).load(function(){
	//alert("check");
	$("#uploadNewResumeForm").hide();
});

$("#uploadNewResume").click(function(){
	//alert("check");
	 if($(this).prop("checked") == true){
            $("#uploadNewResumeForm").show();
            
            $("#uploadNewResumeCheck").val("uploadNewResumeCheck");
        }
        else if($(this).prop("checked") == false){
            $("#uploadNewResumeForm").hide();
            $("#uploadNewResumeCheck").val("");
        }
	
});

});
</script>

  <!--========================================================
CONTENT
=========================================================-->
	<div class="content indent">
    
    <div class="thumb-box14">
       
            <div class="row">			
				
				
				<form method="post" name="frmEditHotlistCandidate" id="frmEditHotlistCandidate" action="doEditHotListCandidate.html" id="aspnetForm" enctype="multipart/form-data">
				
				

				<div class="col-lg-11 col-md-11 col-sm-11">
					
						<h3 class="title">Edit : Hot List Candidate Details </h3>
					
				 </div>
				 				 <%ArrayList HotlistCanDetailsList=(ArrayList) request.getAttribute("HotlistCanDetailsList"); 
			//System.out.println(CandidateList.size()+" list ");
            Iterator itr = HotlistCanDetailsList.iterator();
    while (itr.hasNext()) {    
        String TempList[] = (String[])itr.next();
        
		 String firstName = TempList[0];
		 String lastName = TempList[1];
		 String CandidateEMail = TempList[2];
		 String CandidateContactNumber = TempList[3];
		 String CurrentLocation = TempList[4];
		 String VisaTypeValue = TempList[5];
		 String VisaApprovalValue = TempList[6];
		 String FormI797AvailableValue = TempList[7];
		 String FormI94AvailableValue = TempList[8];
		 String RateInDollar = TempList[9];
		 String MiddleName = TempList[10];
		 String DateOfBirth = TempList[11];
		 String TotalExperience = TempList[12];
		 String ExperienceInUSA = TempList[13];
		 String ReLocationValue = TempList[14];
		 String Availability = TempList[15];
		 String PreferredLocation = TempList[16];
		 String hotlistAvl = TempList[17];
		 String Skills = TempList[18];
		 String BestDateForTelephonicInterview = TempList[19];
		 String BestTimeForTelephonicInterview = TempList[20];
		 String WillingnessForAnInPersonInterviewAtOwnExpenseValue = TempList[21];
		 String EmployerName = TempList[22];
		 String EmployerMailID =TempList[23];
		 String EmployerContactNumber = TempList[24];
		 String ContactPerson = TempList[25];
		 String unique_id =TempList[26];
		 String  CANID = TempList[27];
		 String CandidateStatusValue =TempList[28];
		 String CandidateResumeLoc = TempList[29];
		 System.out.println("CandidateResumeLoc------------------>"+CandidateResumeLoc);
		 String[] CandidateResumeName=CandidateResumeLoc.split("/");
		 System.out.println("CandidateResumeName------------------>"+CandidateResumeName[3]);
		 String user_id =TempList[30];
		 String can_company_uniqueid= TempList[31];
		 String can_companname= TempList[32];
		 String can_company_category = TempList[33];
		 String can_joined_status = TempList[34];
		 String employerCompany = TempList[35];
	
		 
%>
	<input type="hidden" name="uniqueId" id="uniqueId" value="<%=unique_id%>"/>
	<input type="hidden" name="oldResumeUpload" value="<%=CandidateResumeLoc%>"/>
	<input type="hidden" name="uploadNewResumeCheck" id="uploadNewResumeCheck" value=""/>
	
	<input type="hidden" name="can_company_uniqueid" value="<%=can_company_uniqueid%>"/>
	<input type="hidden" name="can_companname" value="<%=can_companname%>"/>
	<input type="hidden" name="can_company_category" value="<%=can_company_category%>"/>
				  <div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
			<div class="col-lg-6 col-md-6 col-sm-6">
				
				  <div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-3">
					<label class="name form-div-6">
							Resume
                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
						
								<input type="checkbox" name="uploadNewResume" id="uploadNewResume">Upload New File
							<%if(unique_id != null){
								%>
								<a href="HotlistCanResumeDownload.html?uniqueId=<%=unique_id%>" style="text-decoration:underline;"><%=CandidateResumeName[3]%></a>
							<%}%>
							
						
						<div id="uploadNewResumeForm">
							<div class="col-lg-12 col-md-12 col-sm-12">
								&nbsp;
							</div>
						
									
										<input name="candidateResume" type="file" id="candidateResume" class="form-control" />
									
						</div>
							 
						</div> 
						
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-3">
						<label class="name form-div-6">
								First Name
								
								</label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
								 <input type="text" name="firstName" id="firstName" value="<%=firstName%>" class="form-control"/>
							 </label>
						</div> 
						
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-3">
						<label class="name form-div-6">
								Middle Name
								
								</label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
								 <input type="text" name="MiddleName" id="MiddleName" value="<%=MiddleName%>" class="form-control"/>
							 </label>
						</div> 
						
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-3">
						<label class="name form-div-6">
								Last Name
								
								</label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
								 <input type="text" name="lastName" id="lastName" value="<%=lastName%>" class="form-control"/>
							 </label>
						</div> 
						
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <!--div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-3">
					<label class="name form-div-6">
							Candidate Status Value
							
                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label><%=CandidateStatusValue%></label>
						</div> 
					
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div-->
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-3">
					<label class="name form-div-6">
							Candidate ID
							
                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label><%=CANID%></label>
						</div> 
						
				</div>
				
						<div class="col-lg-2 col-md-2 col-sm-2">
						</div>
				
				 <div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				
				 <div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-3">
					<label class="name form-div-6">
							Candidate Contact Number

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <input type="text" name="CandidateContactNumber" id="CandidateContactNumber" value="<%=CandidateContactNumber%>" class="form-control"/>
							 </label>
						</div> 
					
				</div>
			
				 <div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				
				
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-3">
					<label class="name form-div-6">
							Email

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <input type="text" name="CandidateEMail" id="CandidateEMail" value="<%=CandidateEMail%>" class="form-control"/>
							 </label>
						</div> 
						
				</div>
				 <!--div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-3">
					<label class="name form-div-6">
							Marital Status

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label><%=employerCompany%> </label>
						</div> 
						
				</div-->
				 <div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-3">
					<label class="name form-div-6">
							Total Experience

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <input type="text" name="TotalExperience" id="TotalExperience" value="<%=TotalExperience%>" class="form-control"/>
							 </label>
						</div> 
					
				</div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-3">
					<label class="name form-div-6">Experience in USA</label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							<label>							 
							 <select name="ExperienceInUSA" id="ExperienceInUSA" class="form-control">
								<option value="YES" <%=ExperienceInUSA.equalsIgnoreCase("YES")?"selected":""%>>YES</option>
								<option value="NO" <%=ExperienceInUSA.equalsIgnoreCase("NO")?"selected":""%>>NO</option>
							 </select>
							</label>
						</div> 
						
				</div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				
			
			 <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-3">
					<label class="name form-div-6">
							Current Location

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <input type="text" name="CurrentLocation" id="CurrentLocation" value="<%=CurrentLocation%>" class="form-control"/>
							 </label>
						</div> 
						
				</div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-3">
					<label class="name form-div-6">
							Relocation

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <select name="ReLocationValue" id="ReLocationValue" class="form-control">
								<option value="YES" <%=ReLocationValue.equalsIgnoreCase("YES")?"selected":""%>>YES</option>
								<option value="NO" <%=ReLocationValue.equalsIgnoreCase("NO")?"selected":""%>>NO</option>
							 </select>
							 </label>
						</div> 
						
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
					<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-3">
					<label class="name form-div-6">
							Prefered Location

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <input type="text" name="PreferredLocation" id="PreferredLocation" value="<%=PreferredLocation%>" class="form-control"/>
							 </label>
						</div> 
					
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-3">
							<label class="name form-div-6">
									Availability

									</label>
								</div>
								<div class="col-lg-5 col-md-5 col-sm-5">
									 <label>
									 <select name="Availability" id="Availability" class="form-control">
										<option value="Part Time" <%=Availability.equalsIgnoreCase("Part Time")?"selected":""%>>Part Time</option>
										<option value="Full Time" <%=Availability.equalsIgnoreCase("Full Time")?"selected":""%>>Full Time</option>
										<option value="Hourly" <%=Availability.equalsIgnoreCase("Hourly")?"selected":""%>>Hourly</option>
									 </select>
							 </label>
								</div> 
						
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-3">
					<label class="name form-div-6">
							Visa Approval

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <select name="VisaApprovalValue" id="VisaApprovalValue" class="form-control">
								<option value="YES" <%=VisaApprovalValue.equalsIgnoreCase("YES")?"selected":""%>>YES</option>
								<option value="NO" <%=VisaApprovalValue.equalsIgnoreCase("NO")?"selected":""%>>NO</option>
							</select>
							 </label>
						</div> 
						
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-3">
					<label class="name form-div-6">
							Visa Type

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <select name="VisaTypeValue" id="VisaTypeValue" class="form-control">
								<option value="H1B" <%=VisaTypeValue.equalsIgnoreCase("H1B")?"selected":""%>>H1B</option>
								<option value="L1B" <%=VisaTypeValue.equalsIgnoreCase("L1B")?"selected":""%>>L1B</option>
								<option value="Green Card" <%=VisaTypeValue.equalsIgnoreCase("Green Card")?"selected":""%>>Green Card</option>
								<option value="US Citizen" <%=VisaTypeValue.equalsIgnoreCase("US Citizen")?"selected":""%>>US Citizen</option>
								<option value="OPT EAD" <%=VisaTypeValue.equalsIgnoreCase("OPT EAD")?"selected":""%>>OPT EAD</option>
							</select>
							</label>
						</div> 
					
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6">
				 
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-4 col-md-4 col-sm-4 ">
					<label class="name form-div-6">
							Form I-797 Available

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <select name="FormI797AvailableValue" id="FormI797AvailableValue" class="form-control">
								<option value="YES" <%=FormI797AvailableValue.equalsIgnoreCase("YES")?"selected":""%>>YES</option>
								<option value="NO" <%=FormI797AvailableValue.equalsIgnoreCase("NO")?"selected":""%>>NO</option>
								<option value="">select</option>
							 </select>
							 </label>
						</div> 
						
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-4 col-md-4 col-sm-4 ">
					<label class="name form-div-6">
							Form I-94 Available

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <select name="FormI94AvailableValue" id="FormI94AvailableValue" class="form-control">
								<option value="YES" <%=FormI94AvailableValue.equalsIgnoreCase("YES")?"selected":""%>>YES</option>
								<option value="NO" <%=FormI94AvailableValue.equalsIgnoreCase("NO")?"selected":""%>>NO</option>
								<option value="">select</option>
							 </select>
							 </label>
						</div> 
						
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-4 col-md-4 col-sm-4 ">
					<label class="name form-div-6">
							HotList AVL

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <select name="hotlistAvl" id="hotlistAvl" class="form-control">
								<option value="YES" <%=hotlistAvl.equalsIgnoreCase("YES")?"selected":""%>>YES</option>
								<option value="NO" <%=hotlistAvl.equalsIgnoreCase("NO")?"selected":""%>>NO</option>
							 </select>
							 </label>
						</div> 
						
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-4 col-md-4 col-sm-4 ">
					<label class="name form-div-6">
							DOB

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <input type="text" name="DateOfBirth" id="DateOfBirth" value="<%=DateOfBirth%>" class="form-control" readonly />
							 <img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer;float:left;margin-top:-25px;margin-left:175px" border="0" onclick="javascript:NewCssCal('DateOfBirth','MMddyyyy','dropdown',false,'24',false)"/>
							 </label>
						</div> 
						
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				  <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-4 col-md-4 col-sm-4 ">
					<label class="name form-div-6">
							Rate

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <input type="text" name="RateInDollar" id="RateInDollar" value="<%=RateInDollar%>" class="form-control"/>
							 </label>
						</div> 
					
				</div>
			
		
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-4 col-md-4 col-sm-4 ">
					<label class="name form-div-6">
							Best Date for Telephonic Interview

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <input type="text" name="BestDateForTelephonicInterview" id="BestDateForTelephonicInterview" value="<%=BestDateForTelephonicInterview%>" class="form-control" readonly />
							 <img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer;float:left;margin-top:-25px;margin-left:175px" border="0" onclick="javascript:NewCssCal('BestDateForTelephonicInterview','MMddyyyy','dropdown',false,'24',false)"/>
							 </label>
						</div> 
						
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-4 col-md-4 col-sm-4 ">
					<label class="name form-div-6">
							Best Time for Telephonic Interview

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <select name="BestTimeForTelephonicInterview" id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drltime" class="form-control" style="text-align:left">
								<option value="" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("")?"selected":""%>>Select Time</option>
								<option value="00:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("00:30")?"selected":""%>>00:30</option>
								<option value="01:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("01:00")?"selected":""%>>01:00</option>
								<option value="01:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("01:30")?"selected":""%>>01:30</option>
								<option value="02:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("02:00")?"selected":""%>>02:00</option>
								<option value="02:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("02:30")?"selected":""%>>02:30</option>
								<option value="03:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("03:00")?"selected":""%>>03:00</option>
								<option value="03:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("03:30")?"selected":""%>>03:30</option>
								<option value="04:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("04:00")?"selected":""%>>04:00</option>
								<option value="04:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("04:30")?"selected":""%>>04:30</option>
								<option value="05:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("05:00")?"selected":""%>>05:00</option>
								<option value="05:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("05:30")?"selected":""%>>05:30</option>
								<option value="06:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("06:00")?"selected":""%>>06:00</option>
								<option value="06:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("06:30")?"selected":""%>>06:30</option>
								<option value="07:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("07:00")?"selected":""%>>07:00</option>
								<option value="07:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("07:30")?"selected":""%>>07:30</option>
								<option value="08:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("08:00")?"selected":""%>>08:00</option>
								<option value="08:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("08:30")?"selected":""%>>08:30</option>
								<option value="09:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("09:00")?"selected":""%>>09:00</option>
								<option value="09:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("09:30")?"selected":""%>>09:30</option>
								<option value="10:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("10:00")?"selected":""%>>10:00</option>
								<option value="10:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("10:30")?"selected":""%>>10:30</option>
								<option value="11:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("11:00")?"selected":""%>>11:00</option>
								<option value="11:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("11:30")?"selected":""%>>11:30</option>
								<option value="12:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("12:00")?"selected":""%>>12:00</option>
								<option value="12:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("12:30")?"selected":""%>>12:30</option>
								<option value="13:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("13:00")?"selected":""%>>13:00</option>
								<option value="13:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("13:30")?"selected":""%>>13:30</option>
								<option value="14:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("14:00")?"selected":""%>>14:00</option>
								<option value="14:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("14:30")?"selected":""%>>14:30</option>
								<option value="15:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("15:00")?"selected":""%>>15:00</option>
								<option value="15:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("15:30")?"selected":""%>>15:30</option>
								<option value="16:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("16:00")?"selected":""%>>16:00</option>
								<option value="16:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("16:30")?"selected":""%>>16:30</option>
								<option value="17:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("17:00")?"selected":""%>>17:00</option>
								<option value="17:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("17:30")?"selected":""%>>17:30</option>
								<option value="18:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("18:00")?"selected":""%>>18:00</option>
								<option value="18:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("18:30")?"selected":""%>>18:30</option>
								<option value="19:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("19:00")?"selected":""%>>19:00</option>
								<option value="19:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("19:30")?"selected":""%>>19:30</option>
								<option value="20:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("20:00")?"selected":""%>>20:00</option>
								<option value="20:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("20:30")?"selected":""%>>20:30</option>
								<option value="21:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("21:00")?"selected":""%>>21:00</option>
								<option value="21:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("21:30")?"selected":""%>>21:30</option>
								<option value="22:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("22:00")?"selected":""%>>22:00</option>
								<option value="22:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("22:30")?"selected":""%>>22:30</option>
								<option value="23:00" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("23:00")?"selected":""%>>23:00</option>
								<option value="23:30" <%=BestTimeForTelephonicInterview.equalsIgnoreCase("23:30")?"selected":""%>>23:30</option>
							</select>
							 </label>
						</div> 
						
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-4 col-md-4 col-sm-4 ">
					<label class="name form-div-6">
							Willingness for an Inperson Interview at own Expense

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <select name="WillingnessForAnInPersonInterviewAtOwnExpenseValue" id="WillingnessForAnInPersonInterviewAtOwnExpenseValue" class="form-control">
								<option value="YES" <%=WillingnessForAnInPersonInterviewAtOwnExpenseValue.equalsIgnoreCase("YES")?"selected":""%>>YES</option>
								<option value="NO" <%=WillingnessForAnInPersonInterviewAtOwnExpenseValue.equalsIgnoreCase("NO")?"selected":""%>>NO</option>
							 </select>
							 </label>
						</div> 
						
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-4 col-md-4 col-sm-4 ">
					<label class="name form-div-6">
							Skills

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <textarea name="Skills" id="Skills" class="form-control"><%=Skills%></textarea>
							 </label>
						</div> 
						
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-4 col-md-4 col-sm-4 ">
					<label class="name form-div-6">
							Employer Name

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <input type="text" name="EmployerName" id="EmployerName" value="<%=EmployerName%>" class="form-control"/>
							 </label>
						</div> 
						
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
							<div class="col-lg-4 col-md-4 col-sm-4 ">
					<label class="name form-div-6">
							Contact Person

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <input type="text" name="ContactPerson" id="ContactPerson" value="<%=ContactPerson%>" class="form-control"/>
							 </label>
						</div> 
					
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-4 col-md-4 col-sm-4 ">
					<label class="name form-div-6">
							Employer Contact Number

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <input type="text" name="EmployerContactNumber" id="EmployerContactNumber" value="<%=EmployerContactNumber%>" class="form-control"/>
							 </label>
						</div> 
						
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
							<div class="col-lg-4 col-md-4 col-sm-4 ">
					<label class="name form-div-6">
							Employer Mail ID

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <input type="text" name="EmployerMailID" id="EmployerMailID" value="<%=EmployerMailID%>" class="form-control"/>
							 </label>
						</div> 
						
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
							<div class="col-lg-4 col-md-4 col-sm-4 ">
					<label class="name form-div-6">
							Employer Company

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label>
							 <input type="text" name="EmployerCompany" id="EmployerCompany" value="<%=employerCompany%>" class="form-control"/>
							 </label>
						</div> 
						
				</div>
				 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
				</div>
 <%} %>
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-5 col-md-5 col-sm-5 col-sm-offset-5">
							<button type="submit" class="button-add" name="Update" value="Update">Update</button>
							</div>
						</div>
				</form>
				</div>
				</div>
				</div>
				
		 <div id="footer">
    <!-- Footer STARTS HERE -->
  <%@ include file = "../../include/FooterProcess.jsp" %>
      <!-- Footer ENDS HERE -->
    </div>
			

</body>
</html>

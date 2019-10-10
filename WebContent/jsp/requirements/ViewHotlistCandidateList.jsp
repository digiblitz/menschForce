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
<title>View Hot list Candidate</title>
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
					
						<h3 class="title">Hot List Candidate Details </h3>
					
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
		 //String RequirementId =TempList[25];
		 String unique_id =TempList[26];
		 String  CANID = TempList[27];
		 String CandidateStatusValue =TempList[28];
		 String CandidateResumeLoc = TempList[29];
		 String user_id =TempList[30];
		 String can_company_uniqueid= TempList[31];
		 String can_companname= TempList[32];
		 String can_company_category = TempList[33];
		 String can_joined_status = TempList[34];
		 String employerCompany = TempList[35];
	
		 
%>
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
							 <a href="HotlistCanResumeDownload.html?uniqueId=<%=unique_id%>" style="text-decoration:underline;">Download</a>
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
							 <label><%=firstName%></label>
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
							 <label><%=MiddleName%></label>
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
							 <label><%=lastName%></label>
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
							 <label><%=CandidateContactNumber%> </label>
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
							 <label><%=CandidateEMail%> </label>
						</div> 
						
				</div>
				
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
							 <label><%=TotalExperience%> </label>
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
							 <label><%=ExperienceInUSA%></label>
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
							 <label><%=CurrentLocation%> </label>
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
							 <label><%=ReLocationValue%> </label>
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
							 <label><%=PreferredLocation%> </label>
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
									 <label><%=Availability%> </label>
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
							 <label><%=VisaApprovalValue%> </label>
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
							 <label><%=VisaTypeValue%> </label>
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
							 <label><%=FormI797AvailableValue%> </label>
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
							 <label><%=FormI94AvailableValue%> </label>
						</div> 
						
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-4 col-md-4 col-sm-4 ">
					<label class="name form-div-6">
							HotListAVL

                            </label>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							 <label><%=hotlistAvl%> </label>
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
							 <label><%=DateOfBirth%> </label>
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
							 <label><%=RateInDollar%> </label>
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
							 <label><%=BestDateForTelephonicInterview%> </label>
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
							 <label><%=BestTimeForTelephonicInterview%> </label>
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
							 <label><%=EmployerName%> </label>
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
							 <label><%=WillingnessForAnInPersonInterviewAtOwnExpenseValue%> </label>
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
							 <label><%=Skills%> </label>
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
							 <label><%=ContactPerson%> </label>
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
							 <label><%=EmployerContactNumber%> </label>
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
							 <label><%=EmployerMailID%> </label>
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
							 <label><%=employerCompany%> </label>
						</div> 
						
				</div>
				 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
				</div>
 <%} %>
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-5 col-md-5 col-sm-5 col-sm-offset-5">
							<button type="reset" class="button-dang" name="Back" value="Back" onClick="history.go(-1);">Back</button>
							</div>
						</div>
				
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

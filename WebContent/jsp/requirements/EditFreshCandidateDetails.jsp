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
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="css/contact-form.css">

<style>
#frmfreshcandidate label.error{
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
<script src="js/Candidate.js"></script>
  <!--========================================================
CONTENT
=========================================================-->
	<div class="content indent">
    
    <div class="thumb-box14">
       
            <div class="row">			
				
				
				<%String pagestatus = (String)request.getAttribute("PageStatus");%>
				
			
				<div class="col-lg-12 col-md-12 col-sm-12">
					
						<h3 class="title"> Edit Fresh Candidate Details</h3>
					
				 </div>
				 
				 <%ArrayList FreshCandidate=(ArrayList) request.getAttribute("FreshCandidateDetails"); 
							//System.out.println(CandidateList.size()+" list ");
							 if(FreshCandidate!=null && FreshCandidate.size()!=0){
				            Iterator itr = FreshCandidate.iterator();
				    while (itr.hasNext()) {    
				        String TempList[] = (String[])itr.next();
						String Name=TempList[0];
						 String Email=TempList[1];
						 String DocumentStatus=TempList[2];
						 String Comments=TempList[3];
						 String status=TempList[4];
						 String VisaType=TempList[5];
						 String MobileNumber = TempList[6];
						 String RecruitedBy = TempList[7];
						 String can_uniqueId=TempList[8];
						 String ResumeLoc = TempList[13];
						 System.out.println("DocumentStatus----->"+DocumentStatus);
						 String DocStatus[] = DocumentStatus.split(",");
						 
						 String Passport = DocStatus[0];
						 String Edu_certificate = DocStatus[1];
						 String Exp_letters = DocStatus[2];
						 String Other_achievements = DocStatus[3];
						  
						 
				    %>
			
				
				<form name="frmfreshcandidate" id="frmfreshcandidate" method="post" action="UpdateFreshCandidate.html" enctype="multipart/form-data">	
				<input type="hidden" name="uniqueId" id="uniqueId" value="<%=can_uniqueId%>"/>
				<input type="hidden" name="UploadResumeCheck" id="UploadResumeCheck" value=""/>
				
				<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">Name<span class="asterisk">*</span></label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  <input type="text" name="freshcandidate_name" id="freshcandidate_name" class="form-control"  value="<%=Name%>"/>
							
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div><label class="name form-div-6">
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>

						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">Document Status</label>
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
						<%if(Passport != null && !Passport.equalsIgnoreCase("null") && Passport.equalsIgnoreCase("Passport")){ %>
							 <input type="checkbox" name="Passport" id="Passport"   value="Passport" checked >Passport<br>
						<%}else{ %>
						 <input type="checkbox" name="Passport" id="Passport"   value="Passport" >Passport<br>
						<%} %>
						<%if(Edu_certificate != null && !Edu_certificate.equalsIgnoreCase("null") && Edu_certificate.equalsIgnoreCase("Education Certificates")){ %>
							 <input type="checkbox" name="Education Certificates" id="Education Certificates"  value="Education Certificates" checked >Education Certificates<br>
						<%}else{ %>
						  <input type="checkbox" name="Education Certificates" id="Education Certificates"  value="Education Certificates" >Education Certificates<br>
						<%} %>
						<%if(Exp_letters != null && !Exp_letters.equalsIgnoreCase("null") && Exp_letters.equalsIgnoreCase("Experience Letters")){ %>
							<input type="checkbox" name="Experience Letters" id="Experience Letters"   value="Experience Letters" checked  >Experience Letters<br>
						<%}else{ %>
						<input type="checkbox" name="Experience Letters" id="Experience Letters"   value="Experience Letters"  >Experience Letters<br>
						<%} %>
						<%if(Other_achievements != null && !Other_achievements.equalsIgnoreCase("null") && Other_achievements.equalsIgnoreCase("Other Achievements")){ %>
							 <input type="checkbox" name="Other Achievements" id="Other Achievements"  value="Other Achievements" checked >Other Achievements
						<%}else{ %>
							 <input type="checkbox" name="Other Achievements" id="Other Achievements"  value="Other Achievements" >Other Achievements
						<%} %>
							
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
								<label class="name form-div-6">Visa Type<span class="asterisk">*</span></label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  <select name="visa_type" id="visa_type" class="form-control">
								<option value="H1B" <%=VisaType.equalsIgnoreCase("H1B")?"selected":""%>>H1B</option>
								<option value="L1B" <%=VisaType.equalsIgnoreCase("L1B")?"selected":""%>>L1B</option>
								<option value="Green Card" <%=VisaType.equalsIgnoreCase("Green Card")?"selected":""%>>Green Card</option>
								<option value="US Citizen" <%=VisaType.equalsIgnoreCase("US Citizen")?"selected":""%>>US Citizen</option>
								<option value="OPT EAD" <%=VisaType.equalsIgnoreCase("OPT EAD")?"selected":""%>>OPT EAD</option>
							</select>
							
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
								<label class="name form-div-6">Mobile Number</label>
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  <input type="text" name="mobile_num" id="mobile_num" class="form-control" value="<%=MobileNumber%>" />
							
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
								<label class="name form-div-6">Email<span class="asterisk">*</span></label>
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							 <input type="text" name="freshcandidate_email" id="freshcandidate_email" class="form-control" value="<%=Email%>"/>
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
								<label class="name form-div-6">Comments</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							 <textarea name="comments" class="form-control"  rows="3" cols="5"><%=Comments%></textarea>
							
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
							<label class="name form-div-6">Status</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							 <input type="text" name="status" id="status" class="form-control" value="<%=status%>"/>
							
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
							<label class="name form-div-6">Recruited By</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							 <input type="text" name="recruiter" id="recruiter" class="form-control" value="<%=RecruitedBy%>"/>
							
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">Upload Resume<span class="asterisk">*</span></label>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<input type="checkbox" name="uploadnew" id="uploadnew">Upload New File
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3">
								<%if(ResumeLoc != null && !ResumeLoc.equalsIgnoreCase("null")){
									String TempLoc[]=ResumeLoc.split("/");%>
									
									<input type="hidden" name="OldCanResume" value="<%=ResumeLoc%>">
									<a href="DownloadFiles.html?filePath=<%=ResumeLoc%>" style="text-decoration:underline;font-size:initial;"><%=TempLoc[3]%></a>
								<%}%>
						</div>
						</div>
						
						
					<div style="display:none" id="UploadNewResume">
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-5">
							 <input type="file" name="freshCanResume" id="freshCanResume" class="form-control" placeholder="Upload Resume"/>
							
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>			
					</div>	
					<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-5">
							 <button type="submit" class="button-add" name="submit" id="submit" value="Submit">Update</button>
							 <button type="reset" class="button-dang" name="Back" value="Back" onClick="history.go(-1);">Back</button>
							</div>
							</div>
					</form>		 
							
				  <%} }%>
				
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

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
				
				
				<%String pagestatus = (String)request.getAttribute("PageStatus");%>
				
			
				<div class="col-lg-12 col-md-12 col-sm-12">
					
						<h3 class="title"> View Fresh Candidate Details</h3>
					
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
			
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
					<div class="col-lg-3 col-md-3 col-sm-3">
					</div>
						<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6" >
							
							Name
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=Name%> </label>
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
							<label class="name form-div-6" >
							
							Document Status
                            </label>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
						<%if(Passport != null && !Passport.equalsIgnoreCase("null") && Passport.equalsIgnoreCase("Passport")){ %>
							 <input type="checkbox" name="Passport" id="Passport"   value="Passport" checked disabled>Passport<br>
							 <input type="hidden" name="Passport" id="Passport"   value="Passport"/>
						<%}else{ %>
						 <input type="checkbox" name="Passport" id="Passport"   value="Passport" disabled>Passport<br>
						<%} %>
						<%if(Edu_certificate != null && !Edu_certificate.equalsIgnoreCase("null") && Edu_certificate.equalsIgnoreCase("Education Certificates")){ %>
							 <input type="checkbox" name="Education Certificates" id="Education Certificates"  value="Education Certificates" checked disabled>Education Certificates<br>
							 <input type="hidden" name="Education Certificates" id="Education Certificates"   value="Education Certificates"/>
						<%}else{ %>
						  <input type="checkbox" name="Education Certificates" id="Education Certificates"  value="Education Certificates" disabled>Education Certificates<br>
						<%} %>
						<%if(Exp_letters != null && !Exp_letters.equalsIgnoreCase("null") && Exp_letters.equalsIgnoreCase("Experience Letters")){ %>
							<input type="checkbox" name="Experience Letters" id="Experience Letters"   value="Experience Letters" checked disabled>Experience Letters<br>
							<input type="hidden" name="Experience Letters" id="Experience Letters"   value="Experience Letters"/>
						<%}else{ %>
						<input type="checkbox" name="Experience Letters" id="Experience Letters"   value="Experience Letters" disabled >Experience Letters<br>
						<%} %>
						<%if(Other_achievements != null && !Other_achievements.equalsIgnoreCase("null") && Other_achievements.equalsIgnoreCase("Other Achievements")){ %>
							 <input type="checkbox" name="Other Achievements" id="Other Achievements"  value="Other Achievements" checked disabled>Other Achievements
							 <input type="hidden" name="Other Achievements" id="Other Achievements"   value="Other Achievements"/>
						<%}else{ %>
							 <input type="checkbox" name="Other Achievements" id="Other Achievements"  value="Other Achievements" disabled>Other Achievements
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
							<label class="name form-div-6" >
							MobileNumber

                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=MobileNumber%> </label>
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
							<label class="name form-div-6" >
							Visa Type

                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=VisaType%> </label>
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
							<label class="name form-div-6" >
							Email

                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=Email%> </label>
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
							<label class="name form-div-6" >
							Comments

                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><textarea name="comments" class="form-control"  rows="5" cols="30" readonly><%=Comments%></textarea></label>
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
							<label class="name form-div-6" >
							Status

                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=status%> </label>
						</div> 
						<div class="col-lg-2 col-md-2 col-sm-2">
						</div>
				</div>
							
				 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6"> Resume</label>
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3">
								<%if(ResumeLoc != null && !ResumeLoc.equalsIgnoreCase("null")){
									String TempLoc[]=ResumeLoc.split("/");%>
									
									<input type="hidden" name="OldCanResume" value="<%=ResumeLoc%>">
									<a href="DownloadFiles.html?filePath=<%=ResumeLoc%>" style="text-decoration:underline;font-size:initial;"><%=TempLoc[3]%></a>
								<%}%>
						</div>
						</div>	
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-5 col-md-5 col-sm-5 col-sm-offset-5">
								<button type="reset" class="button-dang" name="Back" value="Back" onClick="history.go(-1);">Back</button>
							</div>
						</div>
						
	 
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

<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.resume.search.ResumeSearch"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/jquery.js"></script>
	<script src="js/jquery.validate.js"></script>

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
				
				
				
				
			
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6">
						<h3> Post Requirement Details</h3>
					</div>
				 </div>
				 
				 
					
				 
				  <div class="col-lg-1 col-md-1 col-sm-1">
					&nbsp;
					</div>
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-1 col-md-1 col-sm-1">
					&nbsp;
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
					<label class="name form-div-6" style="color:#0072c6">
							RequirementID:
                            </label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
				</div>
				
				<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							ClientIndustry:
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					
					
					
				
					<div class="col-lg-2 col-md-2 col-sm-2">
					<label class="name form-div-6" style="color:#0072c6">
							Position:
                            </label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1">
					<label class="name form-div-6" style="color:#0072c6">
							Total Openings:
                            </label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1">
					<label class="name form-div-6" style="color:#0072c6">
							JobType:
                            </label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1">
					<label class="name form-div-6" style="color:#0072c6">
							Status:
                            </label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1">
					<label class="name form-div-6" style="color:#0072c6">
							dbRate:
                            </label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
				</div>
				
				<div class="col-lg-1 col-md-1 col-sm-1">
					<label class="name form-div-6" style="color:#0072c6">
							Location:
                            </label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
				</div>
				
				<div class="col-lg-1 col-md-1 col-sm-1">
					<label class="name form-div-6" style="color:#0072c6">
							RequirementDetails:
                            </label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
				</div>
				
           
            </div>
            
            <% ArrayList list=(ArrayList) request.getAttribute("list"); 
			System.out.println(list.size()+" list ");
            Iterator itr = list.iterator();
    while (itr.hasNext()) {    
        String tempStr[] = (String[])itr.next();
		String ClientReferenceID=tempStr[0];
		 String Industry = tempStr[1];
		 String ClientResponsiblePerson=tempStr[2];
		 String PositionID=tempStr[3];
		 String Position = tempStr[4];
		 String Interview_process = tempStr[5];
		 String VisasAccepted = tempStr[6];
		 String Skills= tempStr[7];
		 String JobTypeValue = tempStr[8];
		 String LocationField = tempStr[9];
		 String NumberOfOpenings = tempStr[10];
		 String Responsiblities= tempStr[11];
		 String RequiredExperience=tempStr[12];
		 String ConsultantInfo=tempStr[13];
		 String StartDate = tempStr[14];
		 String EndDate=tempStr[15];
		 String ClientRate=tempStr[16];
		 String RequirementStatusValue = tempStr[17];
		 String DigiBlitzRate = tempStr[18];
		 String Reqid = tempStr[19];
		 String Duration=tempStr[20];
		 String LocalRequired=tempStr[21];
		 String Notes=tempStr[22];
		 String Salary=tempStr[23];
		 
		 String FlexabilityOnSalary=tempStr[24];
		 String FlexabilityOnHrlyRate=tempStr[25];
		 String DateOnHold=tempStr[26];
		 String ExtraDocumentsRequired=tempStr[27];
		 String Created=tempStr[28];
		 String Modified =tempStr[29];
		 String id=tempStr[30];
		 String DBRequirementID = tempStr[31];
		 %>
 

			 
			
				<div class="col-lg-12 col-md-12 col-sm-12">
				 
				 <div class="col-lg-1 col-md-1 col-sm-1">
				 &nbsp;
				 </div>
				 
				 
					<div class="col-lg-1 col-md-1 col-sm-1">
					<label class="name form-div-6">
							 <label> <%=Reqid%></label>
                            </label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
				</div>
				
				<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6">
							<label> <%=Industry%></label>
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
                           
					</div>
					
					
					
					
					
					
						<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6">
							<label> <%=Position%></label>
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							 <label> </label>
                           
					</div>
					
					<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6">
							 <label> <%=NumberOfOpenings%></label>
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							 <label> </label>
                           
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6">
							<label> <%=JobTypeValue%></label>
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							 <label> </label>
                           
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6">
							<label> <%=RequirementStatusValue%></label>
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							 <label> </label>
                           
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6">
							<label> <%=DigiBlitzRate%></label>
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							 <label> </label>
                           
					</div>
					
					<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6">
							<label> <%=LocationField%></label>
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							 <label> </label>
                           
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6">
					<a href="resumeSearch.html?cmd=getPostDetailsByid&id=<%=id%>">View Details</a>
							
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							 <label> </label>
                           
					</div>
					
					<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6">
					<a href="ViewApplyJobVacancy.html?RID=<%=DBRequirementID%>">Apply</a>
							
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							 <label> </label>
                           
					</div>
					 
					
					
					
							
							
							
							 
							 
						
					</div>
				 
				  
				 
	<%}%>
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

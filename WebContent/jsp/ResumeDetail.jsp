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
<title> JobSeeker Resume Details</title>
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
					<div class="col-lg-4 col-md-4 col-sm-4">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6">
						<h3>JobSeeker Profile Details</h3>
					</div>
				 </div>

				
				<div class="col-lg-12 col-md-12 col-sm-12">
				<%String resume_download=(String)request.getAttribute("download"); %>
					<div class="col-lg-3 col-md-3 col-sm-3">
					</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
							<label class="name form-div-6">
							Download Resume:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <a href="<%=resume_download %>" download style="font-size:1.8em;font-weight:bold;">Click here to Download Resume</a> 
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
							<label class="name form-div-6">
							Name:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("name"))%> </label>
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
							<label class="name form-div-6">
							RequirementID:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("reqid"))%></label>
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
							<label class="name form-div-6">
							FirstName:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("Fname"))%> </label>
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
							<label class="name form-div-6">
							MiddleName:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("Mname"))%> </label>
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
							<label class="name form-div-6">
							LastName:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("Lname"))%></label>
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
							<label class="name form-div-6">
							CandidateContactNumber:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("Con_num"))%></label>
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
							<label class="name form-div-6">
							CandidateE-Mail:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("Can_mail"))%></label>
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
							<label class="name form-div-6">
							Submitted By:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("Sub_Id"))%></label>
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
							<label class="name form-div-6">
							Date of Birth:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("Dob"))%></label>
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
							<label class="name form-div-6">
							Total Experience:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("Tot_Exp"))%></label>
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
							<label class="name form-div-6">
							EmployerName:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("empname"))%></label>
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
							<label class="name form-div-6">
							EmployerContactNumber:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("Emp_CN"))%></label>
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
							<label class="name form-div-6">
							EmployerMailID:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("Emp_maail"))%></label>
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
							<label class="name form-div-6">
							ContactPerson:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("Con_per"))%></label>
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
							<label class="name form-div-6">
							Current Location:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("Current_loc"))%></label>
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
							<label class="name form-div-6">
							By Whom:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("bywhom_id"))%></label>
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
							<label class="name form-div-6">
							Preferred Location:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("pref_loc"))%></label>
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
							<label class="name form-div-6">
							Rate(In Dollar):
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("Rate"))%></label>
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
							<label class="name form-div-6">
							Availability:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("avail"))%></label>
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
							<label class="name form-div-6">
							Visa Type:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("visa_type"))%></label>
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
							<label class="name form-div-6">
							Visa Approval:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("Visa_app"))%></label>
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
							<label class="name form-div-6">
							Form I-797 available:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("Form"))%></label>
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
							<label class="name form-div-6">
							Form I-94 available:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("Form_94"))%></label>
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
							<label class="name form-div-6">
							Best Time for Telephonic Interview:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("Time"))%></label>
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
							<label class="name form-div-6">
							Experience in USA:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("Exp"))%></label>
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
							<label class="name form-div-6">
							Re-Location:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("Relo"))%></label>
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
							<label class="name form-div-6">
							Skills:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label> <%=((String)request.getAttribute("skill"))%></label>
						</div> 
						<div class="col-lg-2 col-md-2 col-sm-2">
						</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
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
    
    <%@ include file = "../../include/menschForceFooter.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>						
							
</body>
</html>

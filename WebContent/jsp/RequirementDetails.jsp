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
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
					<div class="col-lg-3 col-md-3 col-sm-3">
					</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
							<label class="name form-div-6" style="color:#0072c6">
							ClientReferenceID:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("ClientReferenceID"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							Client Industry:
:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("Industry"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							ClientResponsiblePerson:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("ClientResponsiblePerson"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							Position ID:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("PositionID"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							Position :
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("Position"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							Interview Process :
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("Interview_process"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							Visas Accepted:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("VisasAccepted"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							Skills:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("Skills"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							Job Type:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("JobTypeValue"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							Location Field:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("LocationField"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							NumberOfOpenings :
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("NumberOfOpenings"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							Responsiblities:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("Responsiblities"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							Required Experience:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("RequiredExperience"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							ConsultantInfo:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("ConsultantInfo"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							StartDate:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("StartDate"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							EndDate:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("EndDate"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							ClientRate:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("ClientRate"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							DigiBlitzRate:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("DigiBlitzRate"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							Duration:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("Duration"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							Local Required:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("LocalRequired"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							Notes:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("Notes"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							Salary:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("Salary"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							Flexability on Salary:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("FlexabilityOnSalary"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							Flexability on Hrly Rate:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("FlexabilityOnHrlyRate"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							Date OnHold:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("DateOnHold"))%> </label>
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
							<label class="name form-div-6" style="color:#0072c6">
							Extra Documents Required:
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("ExtraDocumentsRequired"))%> </label>
						</div> 
						<div class="col-lg-2 col-md-2 col-sm-2">
						</div>
				</div>
				 
				 
					
				 
				  
				 
				 
					
				
				
				
           
            </div>
				</div>
				</div>
				
				

</body>
</html>

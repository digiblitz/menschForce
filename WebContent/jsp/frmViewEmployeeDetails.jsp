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
<title>Employee Details</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

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
				 
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6">
						<h3>Employee Details</h3>
				   </div>
				 </div>

							<%ArrayList employeeDetails = (ArrayList) request.getAttribute("employeeDetails");
          					 if(employeeDetails!=null && employeeDetails.size()!=0){
          						Iterator itr = employeeDetails.iterator();
          					    while (itr.hasNext()) {    
          					     	String TempList[] = (String[])itr.next();
          					     	String empuniqueId = TempList[0];
	          					    String empUserId = TempList[1];
	          					    String empRoleId = TempList[2];
	          					    String empId = TempList[3];
	          					    String empdberpPartyid = TempList[4];
	          					    String empSSNNo = TempList[5];
	          					    String empSalutation = TempList[6];
	          					    String empSuffix = TempList[7];
	          					    String empFname = TempList[8];
	          					    String empMname = TempList[9];
	          					    String empLname = TempList[10];
	          					    String empGender = TempList[11];
	          					    String empMaritalStatus = TempList[12];
	          					    String empDOB = TempList[13];
	          					    String empEmailId = TempList[14];
	          					    String empMobileNo = TempList[15];
	          					    String empContactNo = TempList[16];
	          					    String empPassportNo = TempList[17];
	          					    String empAddress1 = TempList[18];
	          					    String empAddress1c = TempList[19];
	          					    String empAddress2 = TempList[20];
	          					    String empAddress2c = TempList[21];
	          					    String empCity = TempList[22];
	          					    String empCityc = TempList[23];
	          					    String empState = TempList[24];
	          					    String empStatec = TempList[25];
	          					    String empCountry = TempList[26];
	          					    String empCountryc = TempList[27];
	          					    String empPostalCode = TempList[28];
	          					    String empPostalCodec = TempList[29];
	          					    String empCompany = TempList[30];
	          					    String empCompanyCategory = TempList[31];
	          					    String empJobTitle = TempList[32];
	          					    String empDepartment = TempList[33];
	          					    String empJobLocation = TempList[34];
	          					    String empPayGroup = TempList[35];
	          					    String empPayratePerhour = TempList[36];
	          					    String empPayPeriod = TempList[37];
	          					    String empFederalTaxWHFormLocation = TempList[38];
	          					    String empStateTaxWHFormLocation = TempList[39];
	          					    String empJoiningDate = TempList[40];
	          					    String empActiveStatus = TempList[41];
	          					    String client_name = TempList[43];
									String client_address=TempList[44];
									String project_name=TempList[45];
									String client_manager=TempList[46];
									String manager_phone=TempList[47];
									String manager_email=TempList[48];
									String emp_role=TempList[49];
									String start_date=TempList[50];
									String end_date=TempList[51];
									String project_status=TempList[52];
									
									empSSNNo = String.valueOf(empSSNNo).substring(5);
          						%>
		 
		<form name="employeeDetails" id="employeeDetails" method="post" action="#" class="formcss" onsubmit="return validate();" >
		
		
		
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>

						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									Party Id
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=empdberpPartyid%>							
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
							<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									FirstName
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=empFname%>							
							</div>						
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									LastName
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=empLname%>							
							</div>							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									E-Mail
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=empEmailId%>							
							</div>							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>

						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									Company
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=empCompany%>							
							</div>							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									SSN No
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  ***-**-<%=empSSNNo%>							
							</div>						
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									City
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=empCityc%>							
							</div>						
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									State
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=empStatec%>							
							</div>						
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									Country
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=empCountryc%>							
							</div>						
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									Postal Code
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=empPostalCodec%>							
							</div>						
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									Pay Period
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=empPayPeriod%>							
							</div>							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									Pay Rate
								</label>
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=empPayratePerhour%>							
							</div>							
						</div>
						
						 
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									Client Name
									
								</label>
								
							</div>
							
							<div class="col-lg-6 col-md-6 col-sm-6">
							<%=client_name%>						
							</div>
													
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									Project Name
									
								</label>
								
							</div>
							
							<div class="col-lg-6 col-md-6 col-sm-6">
							<%=project_name%>						
							</div>
							
													
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									Client Manager
									
								</label>
								</label>
							</div>
							
							<div class="col-lg-6 col-md-6 col-sm-6">
							<%=client_manager%>						
							</div>
							
													
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									 Manager Phone
									
								</label>
								
							</div>
							
							<div class="col-lg-6 col-md-6 col-sm-6">
							<%=manager_phone%>						
							</div>
							
													
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									 Manager Email
									
								</label>
							</div>
							
							<div class="col-lg-6 col-md-6 col-sm-6">
							<%=manager_email%>				
							</div>
							
													
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									Employee Project Status
									
								</label>
							</div>
							
							<div class="col-lg-6 col-md-6 col-sm-6">
							<%=project_status%>				
							</div>
							
													
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									 Client Address
									
								</label>
							</div>
							
							<div class="col-lg-6 col-md-6 col-sm-6">
							<%=client_address%>				
							</div>
							
													
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									 StartDate
								
								</label>
							</div>
							
							
					<div class="col-lg-3 col-md-3 col-sm-3">
						<%=start_date %>
					</div>
				<div class="col-lg-1 col-md-1 col-sm-1 ">
						
					</div>
							
													
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									 EndDate
								
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
						 <%=end_date%>	
					</div>
				<div class="col-lg-1 col-md-1 col-sm-1 ">
						
					</div>
							
													
						</div>
						
						 
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
				
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-6 col-md-6 col-sm-6">
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							 <button type="reset" class="btn-default btn3" name="Back" value="Back" onClick="history.go(-1);">Back</button>
							</div>
						</div>
        		</form>
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
                     
                      <% } %>
				
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


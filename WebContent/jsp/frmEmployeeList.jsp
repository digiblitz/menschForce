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
<title>Employee List</title>
<style>
.table-row-line.header
{ 
	 display: block;
	 flex-direction:row;
    background-color: #dddddd;
    font-weight: bold;
    padding-top: 16px;
    padding-bottom: 35px;
   

}
.table-row-line {
    border-bottom: 1px solid #fcf8e3;
    border-collapse: collapse;
    margin-left: 63px;
    margin-right: 30px;
    padding-right: initial;
    padding-left: initial;

}
.table-row {
    border-bottom: 1px solid #fcf8e3;
    border-collapse: collapse;
    margin-left: 63px;
    margin-right: 30px;
    padding-right: initial;
    padding-left: initial;
	padding-top: 16px;
    padding-bottom: 35px;

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

  <!--========================================================
CONTENT
=========================================================-->
	<div class="content indent">
	<div class="thumb-box14">
       
            <div class="row">			
				
			<div class="col-lg-12 col-md-12 col-sm-12">
				<h3 class="title">Employee List</h3>
					
					<!--<div class="col-lg-1 col-md-1 col-sm-1" >
					<a href="#" style="font-family:sans-serif;font-size:large;font-weight:600;text-decoration:underline;" onClick="history.go(-1);">Back</a>
					</div>-->
				 </div>
				 </div>
				 
				 <div class="row">	
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-1 col-md-1 col-sm-1">
					&nbsp;
					</div>
					<div class="table-row-line wrapper header">
					
					<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							SSN No
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					
					<div class="col-lg-1 col-md-1 col-sm-1">
					<label class="name form-div-6" style="color:#0072c6">
							Employee Id
                            </label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						</div>
						
						<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							FullName
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					
					<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							E-mail
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							Company
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						</div>
						
						<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							State
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						</div>
						
					
					<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							Action
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
					&nbsp;
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							Client Details
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					
					<div class="col-lg-1 col-md-1 col-sm-1">
					&nbsp;
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							Timesheet
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					</div>
</div>					
                           
					</div>
					
					
					 <%ArrayList employeeList=(ArrayList) request.getAttribute("employeeList"); 
			//System.out.println(CandidateList.size()+" list ");
			 if(employeeList!=null && employeeList.size()!=0){
            Iterator itr = employeeList.iterator();
    while (itr.hasNext()) {    
        String TempList[] = (String[])itr.next();
		String empuniqueId=TempList[0];
		String empId = TempList[3];
		 String empdberpPartyid = TempList[4];
		 String empSSNNo = TempList[5];
		 String empFname=TempList[8];
		 String empLname=TempList[10];
		 String empEmailId=TempList[14];
		 String empState = TempList[24];
		 String empCompany=TempList[30];
		 String FedStateEmpMastTblStatus = TempList[42];
		empSSNNo = String.valueOf(empSSNNo).substring(5);
    
    %>
	<input type="hidden" name="FedStateEmpMastTblStatus" id="FedStateEmpMastTblStatus" value="<%=FedStateEmpMastTblStatus%>"/>
    <div class="row">	
    <div class="col-lg-12 col-md-12 col-sm-12">	
	<div class="col-lg-1 col-md-1 col-sm-1">
	&nbsp;
	</div>
	<div class="table-row">
	
	<div class="col-lg-1 col-md-1 col-sm-1">
	<a href="ViewListEmployeeDetailsByUniqueId.html?uniqueId=<%=empuniqueId%>">***-**-<%=empSSNNo%></a>
	</div>
	
	<div class="col-lg-1 col-md-1 col-sm-1">
	<label><%=empId%></label>
	</div>
	<div class="col-lg-1 col-md-1 col-sm-1">
	<label><%=empFname%> <%=empLname%></label>
	</div>
	
	<div class="col-lg-2 col-md-2 col-sm-2">
	<label><%=empEmailId%></label>
	</div>
	<div class="col-lg-1 col-md-1 col-sm-1">
	<label><%=empCompany%></label>
	</div>
	<div class="col-lg-1 col-md-1 col-sm-1">
	<label><%=empState%></label>
	</div>
	<div class="col-lg-2 col-md-2 col-sm-2">
	<%if(FedStateEmpMastTblStatus != null && !(FedStateEmpMastTblStatus.equalsIgnoreCase("")) && !(FedStateEmpMastTblStatus.equalsIgnoreCase("null")) && (FedStateEmpMastTblStatus.equalsIgnoreCase("true"))){%>
	<a href="viewPayrollFormUpload.html?empUniqueId=<%=empuniqueId%>">Federal & State Form</a> |
	<%}else{%>
	<a href="#" style="color:#7e7d7d;cursor:default;">Federal & State Form</a> |
	<%}%>
	<a href="initPayrollCalc.html?uniqueId=<%=empuniqueId%>">Payroll</a> |
	<a href="initDepartmentStateStage1.html?empUniqueId=<%=empuniqueId%>">DOS</a> |
	<a href="initDOLStage1.html?empUniqueId=<%=empuniqueId%>">DOL</a> |
	<a href="initUSCISStage1.html?empUniqueId=<%=empuniqueId%>">USCIS</a>
	
	
	</div>
	
	<div class="col-lg-1 col-md-1 col-sm-1">
	<a href="EditListEmployeeDetailsByUniqueId.html?uniqueId=<%=empuniqueId%>">update</a>
	</div>
	
	<div class="col-lg-1 col-md-1 col-sm-1">
	<a href="CreateTimesheet.html?uniqueId=<%=empuniqueId%>">ManageTimesheet</a>
	</div>
	
	</div>
	</div>
				</div>
	<%}%>
	
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1" align="right">
						<button type="reset" class="button-dang" name="Back" value="Back" onClick="history.go(-1);">Back</button>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
					</div>
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
				</div>
							
	<%}else{%>	
					
			  <div class="row">			
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6">
						<h3>No Record Found</h3>
					</div>
				 </div>
				 
				 </div>	
				 <%} %>	
			
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

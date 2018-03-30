<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Employee Details</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

</head>
<style>
#employeeDetails label.error{
 color:red;
}
</style>
 <script  src="js/ts_picker.js"></script>
<script src="js/calendar2.js" type="text/javascript"></script>
<script src="js/datetimepicker_css.js"></script> 
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
 

<script>
$(document).ready(function(){
	
	$("#payrate").blur(function(){  
			var a = $("#payrate").val();
			var b = a.trim();
			$("#payrate").val(b);
		}); 
		
		$("#clientName").blur(function(){  
			var a = $("#clientName").val();
			var b = a.trim();
			$("#clientName").val(b);
		}); 
		
		$("#projectName").blur(function(){  
			var a = $("#projectName").val();
			var b = a.trim();
			$("#projectName").val(b);
		}); 
		
		$("#clientManager").blur(function(){  
			var a = $("#clientManager").val();
			var b = a.trim();
			$("#clientManager").val(b);
		}); 
		
		$("#managerPhone").blur(function(){  
			var a = $("#managerPhone").val();
			var b = a.trim();
			$("#managerPhone").val(b);
		}); 
		
		$("#managerEmail").blur(function(){  
			var a = $("#managerEmail").val();
			var b = a.trim();
			$("#managerEmail").val(b);
			
		});
		
		$("#clientAddress").blur(function(){  
			var a = $("#clientAddress").val();
			var b = a.trim();
			$("#clientAddress").val(b);
		}); 
	
		
	$('form[name="employeeDetails"]').validate({ 
	
	rules:{
		
		payrate:"required",
		clientName:"required",
		projectName:"required",
		clientManager:"required",
		managerPhone:"required",
		managerEmail:"required",
		clientAddress:"required",
		
							
		
	},
	messages:{
		payrate:"Enter the PayRate",
		clientName:"Enter the clientName",
		projectName:"Enter the Project Name",
		clientManager:"Enter the Project Name",
		managerPhone:"Enter the Phone number",
		managerEmail:"Enter the Email-ID",
		clientAddress:"Enter the Client Address"
		
	},
	errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			},
	});
	});	
  
</script>
 
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
						<h3> Edit Employee Details</h3>
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
									Employee Id
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<%=empId%>				
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
							
							<div class="col-lg-3 col-md-3 col-sm-3">
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
							
							<div class="col-lg-3 col-md-3 col-sm-3">
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
							
							<div class="col-lg-3 col-md-3 col-sm-3">
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
							
							<div class="col-lg-3 col-md-3 col-sm-3">
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
							
							<div class="col-lg-3 col-md-3 col-sm-3">
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
							
							<div class="col-lg-3 col-md-3 col-sm-3">
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
							
							<div class="col-lg-3 col-md-3 col-sm-3">
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
							
							<div class="col-lg-3 col-md-3 col-sm-3">
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
							
							<div class="col-lg-3 col-md-3 col-sm-3">
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
									<span class="form-validation" style="color:Red;">*</span>
								</label>
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3">
								

					<select name="PayPeriod" id="PayPeriod" class="form-control">
							<option value="Daily" <%=empPayPeriod.equals("Daily")?"selected":""%>>Daily</option>
							<option value="Weekly" <%=empPayPeriod.equals("Weekly")?"selected":""%>>Weekly</option>
							<option value="Biweekly" <%=empPayPeriod.equals("Biweekly")?"selected":""%>>Bi-Weekly</option>
							<option value="Semimonthly" <%=empPayPeriod.equals("Semimonthly")?"selected":""%>>Semi-Monthly</option>
							<option value="Monthly" <%=empPayPeriod.equals("Monthly")?"selected":""%>>Monthly</option>
							<option value="Quarterly" <%=empPayPeriod.equals("Quarterly")?"selected":""%>>Quarterly</option>
							<option value="Semiannually" <%=empPayPeriod.equals("Semiannually")?"selected":""%>>Semi-Annually</option>
							<option value="Annually" <%=empPayPeriod.equals("Annually")?"selected":""%>>Annually</option>
							<option value="miscellaneous" <%=empPayPeriod.equals("miscellaneous")?"selected":""%>>miscellaneous</option>
						</select>								
							</div>
							
													
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
						<div class="col-lg-2 col-md-2 col-sm-2"  >
						<label class="name form-div-6" >
						 Pay Rate Type
						 <span class="form-validation" style="color:Red;">*</span>
						</label>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
						<select name="PayRateType" id="PayRateType" class="form-control">
							<option value="Hourly" <%=empPayGroup.equals("Hourly")?"selected":""%>>Per Hour</option>
							<option value="Weekly" <%=empPayGroup.equals("Weekly")?"selected":""%>>Per Week</option>
							<option value="Monthly" <%=empPayGroup.equals("Monthly")?"selected":""%>>Per Month</option>
							<option value="Annually" <%=empPayGroup.equals("Annually")?"selected":""%>>Per Year</option>
						</select>
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
									 Rate in dollars
									 <span class="form-validation" style="color:Red;">*</span>
								
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							<input type="text" name="payrate" id="payrate" placeholder="payrate" class="form-control" value="<%=empPayratePerhour%>	">	
							  							
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
									<span class="form-validation" style="color:Red;">*</span>
								</label>
								
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input type="text" name="clientName" id="clientName" placeholder="clientName" class="form-control" value="<%=client_name%>	">						
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
									<span class="form-validation" style="color:Red;">*</span>
								</label>
								
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input type="text" name="projectName" id="projectName" placeholder="projectName" class="form-control" value="<%=project_name%>	">						
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
									<span class="form-validation" style="color:Red;">*</span>
								
								</label>
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input type="text" name="clientManager" id="clientManager" placeholder="clientManager" class="form-control" value="<%=client_manager%>	">						
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
									 <span class="form-validation" style="color:Red;">*</span>
								
								</label>
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input type="text" name="managerPhone" id="managerPhone" placeholder="managerPhone" class="form-control" value="<%=manager_phone%>	">						
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
									 <span class="form-validation" style="color:Red;">*</span>
								</label>
								
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input type="text" name="managerEmail" id="managerEmail" placeholder="managerEmail" class="form-control" value="<%=manager_email%>	">						
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
									 Project Status
									 <span class="form-validation" style="color:Red;">*</span>
								</label>
								
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3">
							 <select name="project_status" id="project_status" class="form-control">
								<option value="NoProject" <%=project_status.equals("NoProject")?"selected":""%>>NoProject</option>
								<option value="UnderProject" <%=project_status.equals("UnderProject")?"selected":""%>>UnderProject</option>
								
							  </select>
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
									 <span class="form-validation" style="color:Red;">*</span>
								</label>
								
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3">
							 <textarea name="clientAddress" id="clientAddress"  placeholder="clientAddress" class="form-control" rows='3' cols='5' placeholder="Client Address" ><%=client_address%></textarea>						
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
						 <input name="startDate" type="text" id="startDate"
							  class="form-control"  placeholder="startDate" value="<%=start_date%>" readonly>
					</div>
				<div class="col-lg-1 col-md-1 col-sm-1 ">
						<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('startDate')" />
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
						 <input name="endDate" type="text" id="endDate"
							  class="form-control"  placeholder="endDate" value="<%=end_date%>" readonly>
					</div>
				<div class="col-lg-1 col-md-1 col-sm-1 ">
						<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('endDate')" />
					</div>
							
													
						</div>
						
						 
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
				
						<div class="col-lg-12 col-md-12 col-sm-12">
						
						<div class="col-lg-5 col-md-5 col-sm-5">
						&nbsp;
						</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							 <button type="reset" class="button-dang" name="Back" value="Back" onClick="history.go(-1);">Back</button>
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							 <button type="submit" class="button-add" name="submit" value="submit" onClick='this.form.action="UpdateListEmployeeDetailsByUniqueId.html?uniqueId=<%=empuniqueId%>";'>Submit</button>
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


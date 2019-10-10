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
<%@ page import="java.util.*" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="icon" href="img/favicon.ico" type="image/x-icon" />
 <!--CSS-->
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/animate.css" />
<link rel="stylesheet" href="css/camera.css" />
<link rel="stylesheet" href="css/jquery.fancybox.css" />
<link rel="stylesheet" href="fonts/font-awesome.css" />


<!--JS-->
<script src="js/jquery.js"></script>
<script src="js/jquery-migrate-1.2.1.min.js"></script>
<script src="js/jquery.easing.1.3.js"></script>
<script src="js/jquery.mobilemenu.js"></script>
<script src="js/jquery.equalheights.js"></script> 
<script src="js/camera.js"></script>
<!--[if (gt IE 9)|!(IE)]><!-->
<script src="js/jquery.mobile.customized.min.js"></script>
<!--<![endif]-->
<script src="js/jquery.fancybox.pack.js"></script>
<script src="js/jquery.carouFredSel-6.1.0-packed.js"></script>
<script src="js/jquery.touchSwipe.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/tm-scripts.js"></script>



<script src="js/datetimepicker_css.js"></script>


<title>Employee Payroll List</title>

<script>

function dashBoard(){
	window.location.href="user.html?cmd=homeDash";
}

</script>

 <%! 

String  nullCheck(String fieldName){
	String retValue = "";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
}

%>

<%
String payCycle = nullCheck((String)request.getAttribute("payCycle"));
%>
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
	
<!--=======content================================-->
 
<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					
                   <h3 class="title">Search Employee</h3>
				  
				 </div>
				
				   <!-- div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
							<button name="button" type="button" onclick="addRow()" value="Add" align="right" class="button-add" >Add</button>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
								<button name="button2" type="button" class="button-edit" onclick="editRow('dataTable')" value="Edit">Edit</button>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<button type="submit" value="Delete" onclick="deleteRow('dataTable')" class="button-dang" align="right" name="del">Delete</button>
							</div>
							   
							
					</div-->
					
					 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
	 
	 <form id="form" name="insert" action="SearchAllEmployeePayrollDetails.html" method="post" >
										
				<div class="row">
				
					<div class="col-lg-12 col-md-12 col-sm-12">
                         <div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
                              <label class="name">Employee Id</label>
						 </div>
						 <div class="col-lg-3 col-md-3 col-sm-3">
								<input type="text" id="empid" name="empid" class="form-control" value="<%=nullCheck((String)request.getAttribute("empId"))%>"  maxlength="50" >
                           </div>
					</div>
					
					 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
					</div>
												
					<div class="col-lg-12 col-md-12 col-sm-12">
                         <div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
                              <label class="name">Employee Name </label>
						 </div>
						 <div class="col-lg-3 col-md-3 col-sm-3">
									<input  type="text" id="empname" name="empname" class="form-control" value="<%=nullCheck((String)request.getAttribute("empName"))%>"  maxlength="50" >
                           </div>
					</div>
					
					 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
					</div>
					
					<div class="col-lg-12 col-md-12 col-sm-12">
                         <div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
                              <label class="name">SSN No. </label>
						 </div>
						 <div class="col-lg-3 col-md-3 col-sm-3">
									<input  type="text" id="ssnNo" name="ssnNo" class="form-control" value="<%=nullCheck((String)request.getAttribute("ssnNo"))%>"  maxlength="50" >
                           </div>
					</div>
					
					
					<!--  div class="col-lg-12 col-md-12 col-sm-12">
                         <div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
                              <label class="name">Check Date</label>
						 </div>
						 <div class="col-lg-2 col-md-2 col-sm-2">
									<input  type="text" id="checkdate" name="checkdate" class="form-control" value="<%=nullCheck((String)request.getAttribute("checkDate"))%>" readonly >
                           </div>
						   <div class="col-lg-1 col-md-1 col-sm-1 datecal">
								<img class="image1" src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('checkdate')" />
							</div>
					</div-->
					
					 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
					</div>
					
					<div class="col-lg-12 col-md-12 col-sm-12">
                         <div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
                              <label class="name">Pay Duration</label>
						 </div>
						 <div class="col-lg-2 col-md-2 col-sm-2">
						 <label class="name">From</label>
								<input  type="text" id="paydurationFrm" name="paydurationFrm" class="form-control" value="<%=nullCheck((String)request.getAttribute("payDurationFrm"))%>" readonly >
                           </div>
						   <div class="col-lg-1 col-md-1 col-sm-1 datecal">
								<img class="image1" src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('paydurationFrm')" />
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name">To</label>
								<input  type="text" id="paydurationTo" name="paydurationTo" class="form-control" value="<%=nullCheck((String)request.getAttribute("payDurationTo"))%>" readonly >
                           </div>
						   <div class="col-lg-1 col-md-1 col-sm-1 datecal">
								<img class="image1" src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('paydurationTo')" />
							</div>
							
					</div>
					
					 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
					</div>
					
						
					<div class="col-lg-12 col-md-12 col-sm-12">
                         <div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
                              <label class="name">Pay Cycle</label>
						 </div>
						 <div class="col-lg-3 col-md-3 col-sm-3">
						 
						 <%if(!(payCycle.equalsIgnoreCase("")) && !(payCycle.equalsIgnoreCase("null")) && payCycle != null && payCycle != "" && payCycle != "null"){ %>
							<select name="paycycle" id="paycycle" class="form-control">
								<option value="Daily" <%=payCycle.equals("Daily")?"selected":""%>>Daily</option>
								<option value="Weekly" <%=payCycle.equals("Weekly")?"selected":""%>>Weekly</option>
								<option value="Biweekly" <%=payCycle.equals("Biweekly")?"selected":""%>>Bi-Weekly</option>
								<option value="Semimonthly" <%=payCycle.equals("Semimonthly")?"selected":""%>>Semi-Monthly</option>
								<option value="Monthly" <%=payCycle.equals("Monthly")?"selected":""%>>Monthly</option>
								<option value="Quarterly" <%=payCycle.equals("Quarterly")?"selected":""%>>Quarterly</option>
								<option value="Semiannually" <%=payCycle.equals("Semiannually")?"selected":""%>>Semi-Annually</option>
								<option value="Annually" <%=payCycle.equals("Annually")?"selected":""%>>Annually</option>
								<option value="miscellaneous" <%=payCycle.equals("miscellaneous")?"selected":""%>>miscellaneous</option>
							
							</select>
							<%}else{ %>
							
							<select name="paycycle" id="paycycle" class="form-control">
								<option value="" selected="selected">choose cycle</option>
								<option value="Daily">Daily</option>
								<option value="Weekly">Weekly</option>
								<option value="Biweekly">Bi-Weekly</option>
								<option value="Semimonthly">Semi-Monthly</option>
								<option value="Monthly">Monthly</option>
								<option value="Quarterly">Quarterly</option>
								<option value="Semiannually">Semi-Annually</option>
								<option value="Annually">Annually</option>
								<option value="miscellaneous">miscellaneous</option>
							
							</select>
							<%} %>
								
                           </div>
					</div>
					
					 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
					</div>
							
				</div>			
							
							
				<div class="row">
																			
					 <div class="col-lg-12 col-md-12 col-sm-12">								
												
						 <div class="col-lg-1 col-md-1 col-sm-1 col-sm-offset-4">	
								  <input type="submit" name="Insert" value="Submit" class="button-add submit" id="submit">
						
						</div>
						 <div class="col-lg-1 col-md-1 col-sm-1">
								 <input type="button" value="Cancel" name="button" class="button-dang" onClick="dashBoard();" >
						</div>
					</div>
				</div>		
		</form>		
		
		 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
					</div>
	 
	 <div class="col-lg-12 col-md-12 col-sm-12">
					
                   <h3 class="title">Maintain Employee Payroll : Listings</h3>
				  
				 </div>
                    <form name="" id="" action="" >
					  
					   <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-1 col-md-1 col-sm-1">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6 subtitle">
							Id
                            </label>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6 subtitle">
							SSN No
                            </label>
							</div> 
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6 subtitle">
							Name
                            </label>
							</div> 
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6 subtitle">
							Pay Duration
                            </label>
							</div> 
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6 subtitle">
							Action
                            </label>
							</div> 
							
						</div>
                      
                      
                      <%
						 ArrayList employeePayrollList = (ArrayList) request.getAttribute("employeePayrollList");
          					 if(employeePayrollList!=null && employeePayrollList.size()!=0){
							Iterator it = employeePayrollList.iterator();
							while(it.hasNext()){
								String[] s = (String[])it.next();
								String unique_payroll_id = s[0];
								String emp_unique_id = s[1];
								String emp_id = s[2];
								String emp_ssn_no = s[3];
								String emp_name = s[4];
								String tax_year = s[5];
								String tax_payperiod = s[11];
								String tax_payduration_from = s[12];
								String tax_payduration_to = s[13];
								
								
								emp_ssn_no = String.valueOf(emp_ssn_no).substring(5);
		                %>
						
                      <input type="hidden" name="payrollId" value="<%=unique_payroll_id%>" />
                      <input type="hidden" name="empUniqueId" value="<%=emp_unique_id%>" />
					  
					   <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-1 col-md-1 col-sm-1">
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							<a href="empViewPayroll.html?payrollId=<%=unique_payroll_id%>&empUniqueId=<%=emp_unique_id%>"><%=emp_id%></a>
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							***-**-<%=emp_ssn_no%>
							</div> 
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							<%=emp_name%>
							</div> 
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							<%=tax_payduration_from%>/<%=tax_payduration_to%>
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							<!--
							<button name="submit" type="button" onclick='this.form.action="empViewPayroll.html";' value="view" align="right" class="button-add" >View</button>
							<button name="submit" type="button" class="button-edit" onclick='this.form.action="empEditPayroll.html";' value="edit">Edit</button>
							<button type="submit" value="delete" onclick='this.form.action="empDeletePayroll.html";' class="button-dang" align="right" name="delete">Delete</button>
							<button type="submit" value="print" onclick='this.form.action="empPrintPayroll.html";' class="button-add" align="right" name="print">Print</button>
							-->
							<a href="empViewPayroll.html?payrollId=<%=unique_payroll_id%>&empUniqueId=<%=emp_unique_id%>">view</a> |
							<a href="empEditPayroll.html?payrollId=<%=unique_payroll_id%>&empUniqueId=<%=emp_unique_id%>">Edit</a> |
							<a href="empDeletePayroll.html?payrollId=<%=unique_payroll_id%>&empUniqueId=<%=emp_unique_id%>">Delete</a> |
							<a href="empPrintPayroll.html?payrollId=<%=unique_payroll_id%>&empUniqueId=<%=emp_unique_id%>">Print</a>
							</div> 
							
						</div>
					  
                      
                      <%}}else {%>
					  
                      <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-5 col-md-5 col-sm-5">
							</div>
							<div class="col-lg-7 col-md-7 col-sm-7">
								No Records are Found
							</div>
						</div>
                     
                      <% } %>
                    </form>
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

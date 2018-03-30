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
<title>Pay Roll Calculator</title>
<!--script src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>	-->


<style>

#paycalc label.error{
	color:red;
}

.name{
	font-family:Archivo Narrow !important;
	font-size:16px;
}
.netvalue{
	 font:bold 18px/24px 'Archivo Narrow';
	 color:#e33a0c;
	 text-transform:uppercase;
}
.table-row-line.header
{ 
	 display: block;
	 flex-direction:row;
     background-color: #fff !important;
 padding-top: 0px !important;
    padding-bottom: 0px !important;	
    font-weight: bold;
    text-transform:uppercase;
   font:bold 18px/24px 'Archivo Narrow';
	color:#428bca;
	padding:10px 10px;
}
.table-row-line {
    border-bottom: 3px solid #cccccc !important;
    border-collapse: collapse;
    margin-left: 63px;
    margin-right: 30px;
    padding-right: initial;
    padding-left: initial;
	color:#e33a0c;

}
.table-row {
    border-bottom: 1px solid #cccccc;
    border-collapse: collapse;
    margin-left: 63px;
    margin-right: 30px;
    padding-right: initial;
    padding-left: initial;
	padding-top: 0px;
    padding-bottom: 20px;

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

<script src="js/jquery.validate.js"></script>
<script src="js/additional-methods.js"></script>
<script src="js/paycalculator.js"></script>
<script src="js/datetimepicker_css.js"></script>

	<div class="content indent">
	<div class="thumb-box14">
				
<%

String pagestatus = (String)request.getAttribute("pageStatus");
String taxyear = (String)request.getAttribute("taxyear");
String state = (String)request.getAttribute("state");
String VI_NofExemptions = (String)request.getAttribute("VI_NofExemptions");
String VI_age_exemptions = (String)request.getAttribute("VI_age_exemptions");
String VI_additional_withholding = (String)request.getAttribute("VI_additional_withholding");
String fWHITMarital = (String)request.getAttribute("fWHITMarital");
String fWHITNoFAllowances = (String)request.getAttribute("fWHITNoFAllowances");
String PayPeriod = (String)request.getAttribute("PayPeriod");
String PayRateType = (String)request.getAttribute("PayRateType");
String overtime = (String)request.getAttribute("overtime");
String total_gross_pay_hourly = (String)request.getAttribute("total_gross_pay_hourly");
String total_gross_pay_monthly = (String)request.getAttribute("gross_pay_monthly");
String federal_it_cp = (String)request.getAttribute("federal_it_cp");
String social_cp = (String)request.getAttribute("social_cp");
String Medicare_cp = (String)request.getAttribute("Medicare_cp");
String State_IT_cp = (String)request.getAttribute("State_IT_cp");
String After_Tax_Income_CP = (String)request.getAttribute("After_Tax_Income_CP");
String After_Tax_deduction_CP = (String)request.getAttribute("After_Tax_deduction_CP");
String federal_it_rate = (String)request.getAttribute("federal_it_rate");
String federal_it_annual = (String)request.getAttribute("federal_it_annual");
String federal_it_ytd = (String)request.getAttribute("federal_it_ytd");
String social_rate = (String)request.getAttribute("social_rate");
String social_annual = (String)request.getAttribute("social_annual");
String social_ytd = (String)request.getAttribute("social_ytd");
String Medicare_rate = (String)request.getAttribute("Medicare_rate");
String Medicare_annual = (String)request.getAttribute("Medicare_annual");
String Medicare_ytd = (String)request.getAttribute("Medicare_ytd");
String State_IT_rate = (String)request.getAttribute("State_IT_rate");
String State_IT_annual = (String)request.getAttribute("State_IT_annual");
String State_IT_ytd = (String)request.getAttribute("State_IT_ytd");
String After_Tax_Income = (String)request.getAttribute("After_Tax_Income");
String After_Tax_Income_YTD = (String)request.getAttribute("After_Tax_Income_YTD");
String After_Tax_deduction = (String)request.getAttribute("After_Tax_deduction");
String After_Tax_deduction_YTD = (String)request.getAttribute("After_Tax_deduction_YTD");
String netAmount = (String)request.getAttribute("netAmount");

String Hourly_payrate = (String)request.getAttribute("Hourly_payrate");
String total_hours = (String)request.getAttribute("total_hours");
String YTD_Hourly = (String)request.getAttribute("YTD_Hourly");
String Monthly_payrate = (String)request.getAttribute("Monthly_payrate");
String YTD_Monthly = (String)request.getAttribute("YTD_Monthly");
		
String overtimecount = (String)request.getAttribute("overtimecount");
String totalGross = (String)request.getAttribute("totalGross");
String federalWHITAmount = (String)request.getAttribute("federalWHITAmount");
String StateWHITAmount_VA = (String)request.getAttribute("StateWHITAmount_VA");

String gen_pay_cp_ytd = "0.0";
String ot_pay_cp_ytd = "0.0";
String fed_pay_cp_ytd = "0.0";
String ss_pay_cp_ytd = "0.0";
String mc_pay_cp_ytd = "0.0";
String state_pay_cp_ytd = "0.0";
String after_tax_income_cp_ytd = "0.0";
String after_tax_deduction_cp_ytd = "0.0";

gen_pay_cp_ytd = (String)request.getAttribute("gen_pay_cp_ytd");
ot_pay_cp_ytd = (String)request.getAttribute("ot_pay_cp_ytd");
fed_pay_cp_ytd = (String)request.getAttribute("fed_pay_cp_ytd");
ss_pay_cp_ytd = (String)request.getAttribute("ss_pay_cp_ytd");
mc_pay_cp_ytd = (String)request.getAttribute("mc_pay_cp_ytd");
state_pay_cp_ytd = (String)request.getAttribute("state_pay_cp_ytd");
after_tax_income_cp_ytd = (String)request.getAttribute("after_tax_income_cp_ytd");
after_tax_deduction_cp_ytd = (String)request.getAttribute("after_tax_deduction_cp_ytd");

int ot_row_count_int = 0;
String[] overtimerate = null;
String[] overtimepaytype = null;
String[] Hour_ot = null;
String[] ytd_ot = null;
String[] cp_ot = null;

if(overtime != null && overtime != "null" && !(overtime.equalsIgnoreCase("null"))){
ot_row_count_int = Integer.parseInt(overtimecount);

overtimerate = new String[ot_row_count_int+1];
overtimepaytype = new String[ot_row_count_int+1];
Hour_ot = new String[ot_row_count_int+1];
ytd_ot = new String[ot_row_count_int+1];
cp_ot = new String[ot_row_count_int+1];

for(int i=0;i<=ot_row_count_int;i++){
overtimerate[i] =  (String)request.getAttribute("overtimerate"+i);
overtimepaytype[i] = (String)request.getAttribute("overtimepaytype"+i);
Hour_ot[i] = (String)request.getAttribute("Hour_ot"+i);
ytd_ot[i] = (String)request.getAttribute("ytd_ot"+i);
cp_ot[i] = (String)request.getAttribute("cp_ot"+i);

System.out.println("overtimerate["+i+"]---------->"+overtimerate[i]);
System.out.println("overtimepaytype["+i+"]---------->"+overtimepaytype[i]);
System.out.println("Hour_ot["+i+"]---------->"+Hour_ot[i]);
System.out.println("ytd_ot["+i+"]---------->"+ytd_ot[i]);
System.out.println("cp_ot["+i+"]---------->"+cp_ot[i]);
}
}

System.out.println("Hourly_payrate-------------------->"+Hourly_payrate);
System.out.println("total_hours-------------------->"+total_hours);
System.out.println("YTD_Hourly-------------------->"+YTD_Hourly);
System.out.println("Monthly_payrate-------------------->"+Monthly_payrate);
System.out.println("YTD_Monthly-------------------->"+YTD_Monthly);

System.out.println("taxyear------------------->"+taxyear);
System.out.println("state------------------->"+state);
System.out.println("VI_NofExemptions------------------->"+VI_NofExemptions);
System.out.println("VI_age_exemptions------------------->"+VI_age_exemptions);
System.out.println("VI_additional_withholding------------------->"+VI_additional_withholding);
System.out.println("fWHITMarital------------------->"+fWHITMarital);
System.out.println("fWHITNoFAllowances------------------->"+fWHITNoFAllowances);
System.out.println("PayPeriod------------------->"+PayPeriod);
System.out.println("PayRateType------------------->"+PayRateType);
System.out.println("overtimecount------------------->"+overtimecount);
System.out.println("overtime------------------->"+overtime);
System.out.println("total_gross_pay_hourly------------------->"+total_gross_pay_hourly);
System.out.println("total_gross_pay_monthly------------------->"+total_gross_pay_monthly);
System.out.println("federal_it_cp------------------->"+federal_it_cp);
System.out.println("social_cp------------------->"+social_cp);
System.out.println("Medicare_cp------------------->"+Medicare_cp);
System.out.println("State_IT_cp------------------->"+State_IT_cp);
System.out.println("After_Tax_Income_CP------------------->"+After_Tax_Income_CP);
System.out.println("After_Tax_deduction_CP------------------->"+After_Tax_deduction_CP);

System.out.println("federal_it_rate-------------------->"+federal_it_rate);
System.out.println("federal_it_annual-------------------->"+federal_it_annual);
System.out.println("federal_it_ytd-------------------->"+federal_it_ytd);
System.out.println("social_rate-------------------->"+social_rate);
System.out.println("social_annual-------------------->"+social_annual);
System.out.println("social_ytd-------------------->"+social_ytd);
System.out.println("Medicare_rate-------------------->"+Medicare_rate);
System.out.println("Medicare_annual-------------------->"+Medicare_annual);
System.out.println("Medicare_ytd-------------------->"+Medicare_ytd);
System.out.println("State_IT_rate-------------------->"+State_IT_rate);
System.out.println("State_IT_annual-------------------->"+State_IT_annual);
System.out.println("State_IT_ytd-------------------->"+State_IT_ytd);
System.out.println("After_Tax_Income-------------------->"+After_Tax_Income);
System.out.println("After_Tax_Income_YTD-------------------->"+After_Tax_Income_YTD);
System.out.println("After_Tax_deduction-------------------->"+After_Tax_deduction);
System.out.println("After_Tax_deduction_YTD-------------------->"+After_Tax_deduction_YTD);

System.out.println("Total federalWHITAmount----------------------->"+federalWHITAmount);
System.out.println("Total StateWHITAmount_VA----------------------->"+StateWHITAmount_VA);
		
%>
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
	          					    
	          					 empSSNNo = String.valueOf(empSSNNo).substring(5);
          						%>
								
	 
	
	<% if(pagestatus != "" && pagestatus != null && pagestatus.equalsIgnoreCase("init")){%>
       <form action="printPayStub.html" name="paycalc" id="paycalc" method="post">
	   <input type="hidden" name="uniqueId" value="<%=empuniqueId%>"/>
	   <input type="hidden" name="overtimecount" id="overtimecount" value="0"/>
	   
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-sm-5">
							&nbsp;
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5">
							<h3>PAYROLL CALCULATOR</h3>
						</div>
						<!-- div class="col-lg-2 col-md-2 col-sm-2">
								<a href="#" style="font-family:sans-serif;font-size:large;font-weight:600;text-decoration:underline;" onClick="history.go(-1);" id="back">Back</a>
						</div-->
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-1 col-md-1 col-sm-1">
								
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="netvalue">Cheque No : </label>
								<input type="text" name="chequeNo" id="chequeNo" value="0" class="form-control">
						</div>
						
						<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="netvalue">Cheque Date : </label>
								<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:top;cursor:pointer" border="0" onclick="javascript:NewCssCal('chequeDate','MMddyyyy','dropdown',true,'24',true)" />
								<input type="text" readonly name="chequeDate" id="chequeDate" class="form-control" placeholder="MM-DD-YYYY HH:MM:SS">
						</div>
						
						<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-4">
								<label class="netvalue">Net pay</label>
								<input type="text" readonly name="netAmount" value="0.00" class="form-control">
						</div>
						
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
	   
	            <div class="row">
					 <div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-6 col-md-6 col-sm-6">
							<div class="table-row-line wrapper header">
								EMPLOYEE DETAILS
							</div>	
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6">
							<div class="table-row-line wrapper header">
								CHOOSE TAX YEAR AND STATE
							</div>	
						</div>
					</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
					<div class="col-lg-6 col-md-6 col-sm-6">
				
						<div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2">
							<label class="name form-div-6" style="color:#428bca; font-weight:bold"  >
								Employee ID
							</label>
						
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							<label class="name form-div-6" style="color:#428bca; font-weight:bold"  >
								Employee SSN Number
							</label>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
							<label class="name form-div-6" style="color:#428bca; font-weight:bold"  >
								Employee Name
							</label>
						</div>
				
						<div class="col-lg-1 col-md-1 col-sm-1">
						&nbsp;
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6">
						<div class="col-lg-2 col-md-2 col-sm-2">
						&nbsp;
						</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<label class="name form-div-6" >
									Tax Year
								</label>
									<select name="taxyear" id="taxyear" class="form-control" >
										<option value="">Select</option>
										<option value="2017" selected>2017</option>
										<option value="2016" >2016</option>
										
									</select>
							</div>
						
							<div class="col-lg-4 col-md-4 col-sm-4">
								<label class="name form-div-6" >
									State
								</label>
								<select name="state" id="state" class="form-control"></select>
							</div>
					</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
					<div class="col-lg-6 col-md-6 col-sm-6">
				
						
						<div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2">
							<label class="name form-div-6" >
								<%=empdberpPartyid%>
							</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							<label class="name form-div-6"  >
								<!--span id="SSNnumber"><%=empSSNNo%></span-->
								<span>***-**-<%=empSSNNo%></span>
							</label>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
							<label class="name form-div-6" >
								<%=empFname%> <%=empLname%>
							</label>
						</div>
						
						<div class="col-lg-1 col-md-1 col-sm-1">
						&nbsp;
						</div>
						
					</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
			<div class="row">
	          <div class="col-lg-12 col-md-12 col-sm-12">
				
				 <div class="col-lg-6 col-md-6 col-sm-6">
					<div class="table-row-line wrapper header">
						
							FEDERAL W-4 INFORMATION
						
					</div>
				</div>
				
				<div class="col-lg-6 col-md-6 col-sm-6">
					<div class="table-row-line wrapper header">
						
							STATE WITHHOLDING INFORMATION
						
					</div>
				</div>
				
			   </div>
			</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
		   <div class="row">
	          <div class="col-lg-12 col-md-12 col-sm-12">
				
				<div class="col-lg-6 col-md-6 col-sm-6">
					<div class="col-lg-2 col-md-2 col-sm-2">
					&nbsp;
					</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
							<label class="name form-div-6" >
								Marital Status
							</label>
								<select name="fWHITMarital" class="form-control">
									<option value="">Select</option>
									<option value="Single">Single or Dual Income married</option>
									<option value="Married">Married(one income)</option>
									<option value="Head of HouseHold">Head of HouseHold</option>
								</select>
						</div>
					
						<div class="col-lg-4 col-md-4 col-sm-4">
							<label class="name form-div-6" >
									No. Of Allowances
							</label>	
				
								<input type="text" id="allowances" name="fWHITNoFAllowances" value="0" class="form-control" onkeypress="return isNumber(event)">
						</div>
				</div>
				<div id="state_form"></div>	
			</div>
		</div>	
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="table-row-line wrapper header">
							GENERAL PAYCHECK INFORMATION
						</div>
					</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-1 col-md-1 col-sm-1">
							&nbsp;
						</div>
						
						<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6" >
								Pay Cycle
							</label>
							<select name="PayPeriod" id="PayPeriod" class="form-control">
								<option value="">choose cycle</option>
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
							<span id="errorperiod" class="asterisk"></span>
						</div>
				
						<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
								Pay Period : 
								<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('payDurationFrom','MMddyyyy','dropdown',false,'24',false)" />
								From
							</label>
							<input type="text" name="payDurationFrom" class="form-control" id="payDurationFrom" placeholder="MM-DD-YYY" readonly>
							<%
							   String payDurationStatus = (String)request.getAttribute("status");
								if(payDurationStatus != null && payDurationStatus != "" && payDurationStatus != null && !(payDurationStatus.equalsIgnoreCase("null")) && !(payDurationStatus.equalsIgnoreCase(""))){
								%>
								<span id="payDurationStatus" class="asterisk"><%=payDurationStatus%></span>
							<%}%>
							
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2">	
							<label class="name form-div-6">
							<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('payDurationTo','MMddyyyy','dropdown',false,'24',false)" />
								To
							</label>
							<input type="text" name="payDurationTo" class="form-control" id="payDurationTo" placeholder="MM-DD-YYY" readonly>
							
						</div>
						
						<div class="col-lg-2 col-md-2 col-sm-2"  >
							<label class="name form-div-6" >
								Choose Pay Rate Type
							</label>
							<select name="PayRateType" id="Payratecycle" class="form-control">
								<option value="">Choose Rate</option>
								<option value="Hourly">Per Hour</option>
								<option value="Weekly">Per Week</option>
								<option value="Monthly">Per Month</option>
								<option value="Annually">Per Year</option>
							</select>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
							<input type="checkbox" name="overtime" id="overtime" >
							<label class="col-sm-offset-1 name"> Check If Overtime Work Applicable</label>
						</div>
						
						</div>
					</div>
				
				
				<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
				
				
			<div id="Hourlyrow" >
							
							
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-1 col-md-1 col-sm-1">
						&nbsp;
						</div>
				
						<div class="col-lg-2 col-md-2 col-sm-2" style="color:#428bca; font-weight:bold"    >
							<label>Pay Rate</label>
							<input type="text"  value="0.0" name="Hourly_payrate" class="form-control" id="Hourly_payrate" onkeypress="return isNumber(event)" onchange="overtimewagemore(Hourly_payrate,Paytype,total_hours,gross_pay_hourly);">
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
						&nbsp;
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2" style="color:#428bca; font-weight:bold"    >
							<label>Pay Rate Type</label>
							<input type="text" name="Paytype" id="Paytype" class="form-control" value="" readonly>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2" style="color:#428bca; font-weight:bold"    >
							<label>Total Hours Worked</label>
							<input type="text" name="total_hours" id="total_hours" class="form-control" onkeypress="return isNumber(event)" onchange="overtimewagemore(Hourly_payrate,Paytype,total_hours,gross_pay_hourly);">
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" style="color:#428bca; font-weight:bold"    >
							<label>YTD</label>
							<input type="text" name="YTD_Hourly" id="YTD_Hourly" value="<%=gen_pay_cp_ytd%>" class="form-control" onkeypress="return isNumber(event)" readonly>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2" style="color:#428bca; font-weight:bold"    >
							<label>Gross Pay</label>
							<input type="text" name="total_gross_pay_hourly" id="gross_pay_hourly" readonly class="form-control" value="0.0">
						</div>
					</div>
				</div>
			
			</div>
				
				
				
				
				
		
				
				
				<!--div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				
				<div class="col-lg-4 col-md-4 col-sm-4"   >
	            Salary
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2" align="center" >
	            Hourly/Unit
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="center" >
	            <input type="text" id="Hour" name="Hour" value="" class="form-control" onkeypress="return isNumber(event)" >
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="center" >
	            <input type="text" id="ytd" name="ytd" value="" class="form-control" onkeypress="return isNumber(event)" >
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="center" >
	           <input type="text" id="cp" name="cp" value="" class="form-control" >
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				</div>
				</div>-->
				
				
				
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
				<div id="overtimerow" style="display:none;">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="table-row-line wrapper header">
							OVERTIME PAYCHECK INFORMATION
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
					</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-1 col-md-1 col-sm-1">
						&nbsp;
						</div>
				
						<div class="col-lg-4 col-md-4 col-sm-4" style="color:#428bca; font-weight:bold"    >
							Salary & Earnings
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2" align="center" style="color:#428bca; font-weight:bold" >
							Pay Type
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="center" style="color:#428bca; font-weight:bold" >
							Hour
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="center" value="0.0" style="color:#428bca; font-weight:bold"  >
							YTD
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="center" style="color:#428bca; font-weight:bold"  >
							CP
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
						&nbsp;
						</div>
					</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-1 col-md-1 col-sm-1">
							&nbsp;
						</div>
				
						<div class="col-lg-2 col-md-2 col-sm-2" >
							<input type="text" value="Overtime0" id="overtimerate0" name="overtimerate0" placeholder="Salary Description" class="form-control ">
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2"   >
							&nbsp;
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2" align="right" >
							<select name="overtimepaytype0" id="overtimepaytype0" class="form-control" onchange="overtimewagemore(Hourly_payrate,overtimepaytype0,Hour_ot0,cp_ot0)">
								<option value="">Select</option>
								<option value="Hourly">Per Hour</option>
								<option value="Weekly">Per Week</option>
								<option value="Monthly">Per Month</option>
								<option value="Annually">Per Year</option>
							</select>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="Hour_ot0" name="Hour_ot0"  class="form-control" onkeypress="return isNumber(event)"  onchange="overtimewagemore(Hourly_payrate,overtimepaytype0,Hour_ot0,cp_ot0)" >
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" value="0.0" id="ytd_ot0" name="ytd_ot0"  class="form-control" value="<%=ot_pay_cp_ytd%>" onkeypress="return isNumber(event)" readonly >
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="cp_ot0" name="cp_ot0" value="0.0" readonly class="form-control" >
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="left" style="padding:10px;">
							<button id="addrow"><i class="fa fa-plus-circle" aria-hidden="true"></i></button>
						</div>
					</div>
				</div>
			</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="table-row-line wrapper header">

							PAYROLL TAXES
					
						</div>
					</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-1 col-md-1 col-sm-1">
								&nbsp;
						</div>
						
						<div class="col-lg-6 col-md-6 col-sm-6" style="color:#428bca; font-weight:bold">
							Taxes
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="center" style="color:#428bca; font-weight:bold" >
							Rate
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="center" style="color:#428bca; font-weight:bold" >
							Annual Max
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="center" style="color:#428bca; font-weight:bold"  >
								YTD
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="center" style="color:#428bca; font-weight:bold"  >
						  CP
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
						&nbsp;
						</div>
					</div>
				</div>
				
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-1 col-md-1 col-sm-1">
							&nbsp;
						</div>
						
						<div class="col-lg-6 col-md-6 col-sm-6"   >
								Federal Income Tax
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="federal_rate" name="federal_it_rate" value="" class="form-control" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="federal_annual" name="federal_it_annual" value="" class="form-control" readonly >
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="federal_ytd" name="federal_it_ytd" value="<%=fed_pay_cp_ytd%>" class="form-control" onkeypress="return isNumber(event)" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="federal_cp" name="federal_it_cp" value="0.0" class="form-control" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
							&nbsp;
						</div>
					</div>
				</div>
				
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
					
						<div class="col-lg-1 col-md-1 col-sm-1">
							&nbsp;
						</div>
						
						<div class="col-lg-6 col-md-6 col-sm-6"   >
							Social Security Tax
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<select name="social_rate" id="social_rate" class="form-control">
								<option value="0.062" selected>0.062</option>
								<!--option value="0.000">Exempt</option-->
							</select>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="social_annual" name="social_annual" value="127200" class="form-control" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="social_ytd" name="social_ytd" value="<%=ss_pay_cp_ytd%>" class="form-control" onkeypress="return isNumber(event)" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="social_cp" name="social_cp" value="0.0" class="form-control" readonly >
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
							&nbsp;
						</div>
					</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-1 col-md-1 col-sm-1">
						&nbsp;
						</div>
				
						<div class="col-lg-6 col-md-6 col-sm-6"   >
								Medicare Tax
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<select name="Medicare_rate" id="Medicare_rate" class="form-control">
								<option value="0.0145" selected>0.0145</option>
								<!--option value="0.0000">Exempt</option-->
							</select>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="Medicare_annual" name="Medicare_annual" value="" class="form-control" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="Medicare_ytd" name="Medicare_ytd" value="<%=mc_pay_cp_ytd%>" class="form-control" onkeypress="return isNumber(event)" readonly >
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="Medicare_cp" name="Medicare_cp" value="0.0" class="form-control" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
								&nbsp;
						</div>
					</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
					
						<div class="col-lg-1 col-md-1 col-sm-1">
						&nbsp;
						</div>
						
						<div class="col-lg-6 col-md-6 col-sm-6"   >
						State Income Tax
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
						 <input type="text" id="State_rate" name="State_IT_rate" value="" class="form-control" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
						<input type="text" id="State_annual" name="State_IT_annual" value="" class="form-control"readonly >
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
						<input type="text" id="State_ytd" name="State_IT_ytd" value="<%=state_pay_cp_ytd%>" class="form-control" onkeypress="return isNumber(event)" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
					   <input type="text" id="State_cp" name="State_IT_cp" value="0.0" class="form-control" readonly >
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
						&nbsp;
						</div>
					</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-6 col-md-6 col-sm-6">
							<div class="table-row-line wrapper header">
				
								After-Tax Income
							</div>
				
						</div>
						
						<div class="col-lg-6 col-md-6 col-sm-6">
							<div class="table-row-line wrapper header">
								After-Tax Deduction
							</div>
						</div>
					</div>
				</div>
				
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-6 col-md-6 col-sm-6">
				
							<div class="col-lg-2 col-md-2 col-sm-2">
								&nbsp;
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<label class="name form-div-6" >
									Income
								</label>
								<input type="text" id="After_Tax_Income" name="After_Tax_Income" value="" class="form-control" placeholder="Item Name">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6" >
								YTD
								</label>
								<input type="text" id="After_Tax_Income_YTD" name="After_Tax_Income_YTD" value="<%=after_tax_income_cp_ytd%>" class="form-control" onkeypress="return isNumber(event)" readonly>
								
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6" >
								CP
								</label>
								<input type="text" id="After_Tax_Income_CP" name="After_Tax_Income_CP" value="0.0" onkeypress="return isNumber(event)" class="form-control">
								
							</div>
				
						</div>
				
						<div class="col-lg-6 col-md-6 col-sm-6">
						
							<div class="col-lg-2 col-md-2 col-sm-2">
								&nbsp;
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<label class="name form-div-6" >
								Deduction
								</label>
								<input type="text" id="After_Tax_deduction" name="After_Tax_deduction" value="" class="form-control" placeholder="Item Name">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6" >
								YTD
								</label>
								<input type="text" id="After_Tax_deduction_YTD" name="After_Tax_deduction_YTD" value="<%=after_tax_deduction_cp_ytd%>" class="form-control" onkeypress="return isNumber(event)"  readonly>
							
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6" >
								CP
								</label>
								<input type="text" id="After_Tax_deduction_CP" name="After_Tax_deduction_CP" value="0.0" onkeypress="return isNumber(event)" class="form-control">
								
							</div>
				
						</div>
				
					</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="table-row-line wrapper header"></div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
				
							<div class="col-lg-1 col-md-1 col-sm-1 col-sm-offset-1">
								<label class="netvalue">Net pay</label>
								
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<input type="text" readonly name="netAmount" value="0.00" class="form-control">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2"  align="right" style="font-weight:bold">
							 <input type="button" value="Back" id="close" class="button-dang" >
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6" style="font-weight:bold">
							 <input type="submit" value="calculate" class="button-add">
							</div>
							
							<div class="col-lg-1 col-md-1 col-sm-1">
							&nbsp;
							
							</div>
						 <div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
					</div>
					</div>
				</div>
			</form>	
	<%}%>
			<%}}%>
	
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
<script language="javascript">	
	
//FillCountry(document.form.country, document.form.state, 'USA' );
FillState(document.paycalc.state, '');
FillState(document.paycalc.state,'<%=state%>');


</script>
	
</body>

</html>

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
<script type="text/javascript">


$( window ).on( "load", function() {

    	//$('#PayPeriod').attr("disabled", true); //Set
		//$('#taxyear').attr("disabled", true); //Set
		//$('#fWHITMarital').attr("disabled", true); //Set
		//$('#state').attr("disabled", true); //Set
		//$('#Payratecycle').attr("disabled", true); //Set
		//$('#paycalc :input').prop("disabled", true);
		//$("#state_form :input").attr("disabled", true);
		//$('#state_form').find('input, textarea, button, select').attr('disabled','disabled');
		$('#paycalc select').attr('readonly', 'readonly');
		$('#paycalc input').attr('readonly', 'readonly');

	});
	
	</script>
	<div class="content indent">
	<div class="thumb-box14">
				
<%

String pagestatus = (String)request.getAttribute("pageStatus");
String taxyear = (String)request.getAttribute("taxyear");
String state = (String)request.getAttribute("state");
String chequeNo = (String)request.getAttribute("chequeNo");
String chequeDate = (String)request.getAttribute("chequeDate");

String uniquePayrollId = null;
uniquePayrollId = (String)request.getAttribute("uniquePayrollId");
System.out.println("uniquePayrollId in view jsp---------------------->"+uniquePayrollId);

String VI_NofExemptions = null;
String VI_age_exemptions = null;
String VI_additional_withholding = null;
String personalAllowances = null;
String dependentAllowances = null;
String filingStatus = null;
String totalAllowances = null;
String additionalStateWithHolding = null;
String additionalAllowances = null;
String texarnaResident = null;
String withHoldingCode = null;
String basicAllowances = null;
String personalDeduction = null;
String dependentDeduction = null;
String WHExemptions = null;
String RateCode = null;

String SpouseBlind = null;
String PersonalBlind = null;
String HouseHold = null;
String Student = null;

String extendedAllowances = null;
String regularAllowances = null;
String exemptionAmount = null;
String WithholdingRate = null;
String DistrictRate = null;
String DistrictNo = null;
String schoolDistITAmount = null;

			
if(state != null && state.equalsIgnoreCase("Virginia")){
			VI_NofExemptions = (String)request.getAttribute("VI_NofExemptions");
			VI_age_exemptions = (String)request.getAttribute("VI_age_exemptions");
			VI_additional_withholding = (String)request.getAttribute("VI_additional_withholding");
		}else if(state != null && state.equalsIgnoreCase("Georgia")){
			personalAllowances = (String)request.getAttribute("personal_allowances");
			dependentAllowances = (String)request.getAttribute("dependent_allowances");
			filingStatus = (String)request.getAttribute("filing_status");
			totalAllowances = (String)request.getAttribute("total_allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			additionalAllowances = (String)request.getAttribute("additional_allowances");
			
		}else if(state != null && state.equalsIgnoreCase("Arkansas")){
			totalAllowances = (String)request.getAttribute("total_allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			texarnaResident = (String)request.getAttribute("texarna_resident");
			
		}else if(state != null && state.equalsIgnoreCase("Idaho")){
			filingStatus = (String)request.getAttribute("filing_status");
			totalAllowances = (String)request.getAttribute("total_allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			
		}else if(state != null && state.equalsIgnoreCase("Alabama")){
			filingStatus = (String)request.getAttribute("filing_status");
			dependentAllowances = (String)request.getAttribute("dependent_allowances");
			totalAllowances = (String)request.getAttribute("allowance");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			
		}else if(state != null && state.equalsIgnoreCase("Connecticut")){
			withHoldingCode = (String)request.getAttribute("withholding_code");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			
		}else if(state != null && state.equalsIgnoreCase("Illinois")){
			additionalAllowances = (String)request.getAttribute("additional_allowances");
			basicAllowances = (String)request.getAttribute("basic_allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			
		}else if(state != null && state.equalsIgnoreCase("Indiana")){
			personalDeduction = (String)request.getAttribute("personal_deduction");
			dependentDeduction = (String)request.getAttribute("dependent_deduction");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			
		}else if(state != null && state.equalsIgnoreCase("Kentucky")){
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			
		}else if(state != null && state.equalsIgnoreCase("Maine")){
			filingStatus = (String)request.getAttribute("filing_status");
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			
		}else if(state != null && state.equalsIgnoreCase("Minnesota")){
			filingStatus = (String)request.getAttribute("filing_status");
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			
		}else if(state != null && state.equalsIgnoreCase("Wisconsin")){
			filingStatus = (String)request.getAttribute("filing_status");
			WHExemptions = (String)request.getAttribute("WHExemptions");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			
		}else if(state != null && state.equalsIgnoreCase("Rhode Island")){
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			
		}else if(state != null && state.equalsIgnoreCase("Vermont")){
			filingStatus = (String)request.getAttribute("filing_status");
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			
		}else if(state != null && state.equalsIgnoreCase("New Jersey")){
			filingStatus = (String)request.getAttribute("filing_status");
			RateCode = (String)request.getAttribute("rate_code");
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			
		}else if(state != null && state.equalsIgnoreCase("District of Columbia")){
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			
		}else if(state != null && state.equalsIgnoreCase("Nebraska")){
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			filingStatus = (String)request.getAttribute("filing_status");
			
		}else if(state != null && state.equalsIgnoreCase("Montana")){
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			
		}else if(state != null && state.equalsIgnoreCase("New Mexico")){
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			filingStatus = (String)request.getAttribute("filing_status");
			
		}else if(state != null && state.equalsIgnoreCase("West Virginia")){
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			filingStatus = (String)request.getAttribute("filing_status");
			
		}else if(state != null && state.equalsIgnoreCase("Hawaii")){
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			filingStatus = (String)request.getAttribute("filing_status");
			
		}else if(state != null && state.equalsIgnoreCase("Louisiana")){
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			personalAllowances = (String)request.getAttribute("personal_allowances");
			dependentAllowances = (String)request.getAttribute("dependent_allowances");
			filingStatus = (String)request.getAttribute("filing_status");
			
		}else if(state != null && state.equalsIgnoreCase("Massachusetts")){
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			totalAllowances = (String)request.getAttribute("allowances");
			SpouseBlind = (String)request.getAttribute("spouse_blind");
			PersonalBlind = (String)request.getAttribute("personal_blind");
			HouseHold = (String)request.getAttribute("house_hold");
			Student = (String)request.getAttribute("student");
			
			System.out.println("SpouseBlind in jsp---------------------->"+SpouseBlind);
			System.out.println("PersonalBlind in jsp---------------------->"+PersonalBlind);
			System.out.println("HouseHold in jsp---------------------->"+HouseHold);
			System.out.println("Student in jsp---------------------->"+Student);
			
			
		}else if(state != null && state.equalsIgnoreCase("Oregon")){
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			filingStatus = (String)request.getAttribute("filing_status");
			
		}else if(state != null && state.equalsIgnoreCase("South Carolina")){
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			
		}else if(state != null && state.equalsIgnoreCase("North Carolina")){
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			filingStatus = (String)request.getAttribute("filing_status");
			
		}else if(state != null && state.equalsIgnoreCase("North Dakota")){
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			filingStatus = (String)request.getAttribute("filing_status");
			
		}else if(state != null && state.equalsIgnoreCase("Utah")){
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			filingStatus = (String)request.getAttribute("filing_status");
		}else if(state != null && state.equalsIgnoreCase("Delaware")){
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			filingStatus = (String)request.getAttribute("filing_status");
		}else if(state != null && state.equalsIgnoreCase("Colorado")){
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			filingStatus = (String)request.getAttribute("filing_status");
		}else if(state != null && state.equalsIgnoreCase("California")){
			extendedAllowances = (String)request.getAttribute("extended_allowances");
			regularAllowances = (String)request.getAttribute("regular_allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			filingStatus = (String)request.getAttribute("filing_status");
			
		}else if(state != null && state.equalsIgnoreCase("Iowa")){
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			filingStatus = (String)request.getAttribute("filing_status");
		}else if(state != null && state.equalsIgnoreCase("Kansas")){
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			filingStatus = (String)request.getAttribute("filing_status");
		}else if(state != null && state.equalsIgnoreCase("Mississippi")){
			exemptionAmount = (String)request.getAttribute("exemption_amount");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			filingStatus = (String)request.getAttribute("filing_status");
		}else if(state != null && state.equalsIgnoreCase("Michigan")){
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
		}else if(state != null && state.equalsIgnoreCase("Missouri")){
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			filingStatus = (String)request.getAttribute("filing_status");
		}else if(state != null && state.equalsIgnoreCase("Pennsylvania")){
						
		}else if(state != null && state.equalsIgnoreCase("Arizona")){
			WithholdingRate = (String)request.getAttribute("withholding_rate");
		}else if(state != null && state.equalsIgnoreCase("Oklahoma")){
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			filingStatus = (String)request.getAttribute("filing_status");
		}else if(state != null && state.equalsIgnoreCase("Ohio")){
			totalAllowances = (String)request.getAttribute("allowances");
			additionalStateWithHolding = (String)request.getAttribute("additional_state_withholding");
			DistrictRate = (String)request.getAttribute("district_rate");
			DistrictNo = (String)request.getAttribute("district_no");
		}
		
String fWHITMarital = (String)request.getAttribute("fWHITMarital");
String fWHITNoFAllowances = (String)request.getAttribute("fWHITNoFAllowances");
String PayPeriod = (String)request.getAttribute("PayPeriod");
String payDurationFrom = (String)request.getAttribute("payDurationFrom");
String payDurationTo = (String)request.getAttribute("payDurationTo");
String PayRateType = (String)request.getAttribute("PayRateType");
String overtime = (String)request.getAttribute("overtime");
String total_gross_pay_hourly = (String)request.getAttribute("total_gross_pay_hourly");
//String total_gross_pay_monthly = (String)request.getAttribute("gross_pay_monthly");
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
//String Monthly_payrate = (String)request.getAttribute("Monthly_payrate");
//String YTD_Monthly = (String)request.getAttribute("YTD_Monthly");
		
String overtimecount = (String)request.getAttribute("overtimecount");
String totalGross = (String)request.getAttribute("totalGross");
String federalWHITAmount = (String)request.getAttribute("federalWHITAmount");
String StateWHITAmount_VA = (String)request.getAttribute("StateWHITAmount_VA");

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
//System.out.println("Monthly_payrate-------------------->"+Monthly_payrate);
//System.out.println("YTD_Monthly-------------------->"+YTD_Monthly);

System.out.println("taxyear------------------->"+taxyear);
System.out.println("state------------------->"+state);
System.out.println("chequeNo------------------->"+chequeNo);
System.out.println("chequeDate------------------->"+chequeDate);
System.out.println("VI_NofExemptions------------------->"+VI_NofExemptions);
System.out.println("VI_age_exemptions------------------->"+VI_age_exemptions);
System.out.println("VI_additional_withholding------------------->"+VI_additional_withholding);
System.out.println("fWHITMarital------------------->"+fWHITMarital);
System.out.println("fWHITNoFAllowances------------------->"+fWHITNoFAllowances);
System.out.println("PayPeriod------------------->"+PayPeriod);
System.out.println("payDurationFrom------------------->"+payDurationFrom);
System.out.println("payDurationTo------------------->"+payDurationTo);
System.out.println("PayRateType------------------->"+PayRateType);
System.out.println("overtimecount------------------->"+overtimecount);
System.out.println("overtime------------------->"+overtime);
System.out.println("total_gross_pay_hourly------------------->"+total_gross_pay_hourly);
//System.out.println("total_gross_pay_monthly------------------->"+total_gross_pay_monthly);
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
						
			<%if(pagestatus.equals("view") && pagestatus != "" && pagestatus != null){%>
			
			
			<form action="initEditPayroll.html" method="post" name="paycalc" id="paycalc">
			
			<input type="hidden" name="uniqueId" value="<%=empuniqueId%>"/>
			<input type="hidden" name="payrollId" value="<%=uniquePayrollId%>"/>
			
			
			
			<input type="hidden" value="<%=VI_NofExemptions%>"  id="VI_NofExemptions">
			<input type="hidden" value="<%=VI_age_exemptions%>"  id="VI_age_exemptions">
			<input type="hidden" value="<%=VI_additional_withholding%>"  id="VI_additional_withholding">
			
			<input type="hidden" value="<%=personalAllowances%>"  id="personal_allowances">
			<input type="hidden" value="<%=dependentAllowances%>"  id="dependent_allowances">
			<input type="hidden" value="<%=filingStatus%>"  id="filing_status">
			<input type="hidden" value="<%=totalAllowances%>"  id="total_allowances">
			<input type="hidden" value="<%=totalAllowances%>"  id="allowances">
			<input type="hidden" value="<%=additionalStateWithHolding%>"  id="additional_state_withholding">
			<input type="hidden" value="<%=additionalAllowances%>"  id="additional_allowances">
			<input type="hidden" value="<%=texarnaResident%>" id="texarna_resident">
			<input type="hidden" value="<%=withHoldingCode%>" id="withholding_code">
			<input type="hidden" value="<%=basicAllowances%>"  id="basic_allowances">
			<input type="hidden" value="<%=personalDeduction%>" id="personal_deduction">
			<input type="hidden" value="<%=dependentDeduction%>"  id="dependent_deduction">
			<input type="hidden" value="<%=WHExemptions%>"  id="WHExemptions">
			<input type="hidden" value="<%=RateCode%>" id="rate_code">
			<input type="hidden" value="<%=SpouseBlind%>"  id="spouse_blind">
			<input type="hidden" value="<%=PersonalBlind%>"  id="personal_blind">
			<input type="hidden" value="<%=HouseHold%>"  id="house_hold">
			<input type="hidden" value="<%=Student%>" id="student">
			
			<input type="hidden" value="<%=extendedAllowances%>"  id="extended_allowances">
			<input type="hidden" value="<%=regularAllowances%>"  id="regular_allowances">
			<input type="hidden" value="<%=exemptionAmount%>"  id="exemption_amount">
			<input type="hidden" value="<%=WithholdingRate%>" id="withholding_rate">
			<input type="hidden" value="<%=DistrictRate%>"  id="district_rate">
			<input type="hidden" value="<%=DistrictNo%>" id="district_no">

				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
						
							<h3 class="title">PAYROLL CALCULATOR</h3>
						
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
								<input type="text" name="chequeNo" id="chequeNo" value="<%=chequeNo%>" class="form-control" readonly>
						</div>
						
						<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="netvalue">Cheque Date : </label>
								<input type="text" readonly name="chequeDate" id="chequeDate" class="form-control" value="<%=chequeDate%>">
						</div>
						
						<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-4">
								<label class="netvalue">Net pay</label>
								<input type="text" readonly name="netAmount" value="<%=netAmount%>" class="form-control">
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
							<label class="name form-div-6" style="color:#428bca; font-weight:bold" >
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
									<!--select name="taxyear" id="taxyear" class="form-control" >
										<option value="<%=taxyear%>" selected><%=taxyear%></option>
										<option value="2017" disabled="disabled">2017</option>
										<option value="2016" disabled="disabled">2016</option>
									</select-->
									
									<input type="text" name="taxyear" id="taxyear" class="form-control" onkeypress="return isNumber(event)" value="<%=taxyear%>" readonly>
									
							</div>
						
							<div class="col-lg-4 col-md-4 col-sm-4">
								<label class="name form-div-6" >
									State
								</label>
								<input type="text" name="state" id="state" value="<%=state%>" class="form-control" readonly>

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
								<!--<span id="SSNnumber"><%=empSSNNo%></span>-->
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
										<!--select name="fWHITMarital" class="form-control" id="fWHITMarital">
											<option value="<%=fWHITMarital%>" selected><%=fWHITMarital%></option>
											<option value="Single" disabled="disabled">Single or Dual Income married</option>
											<option value="Married" disabled="disabled">Married(one income)</option>
											<option value="Single" disabled="disabled">Head of HouseHold</option>
										</select-->
										
										<input type="text" id="fWHITMarital" name="fWHITMarital"  class="form-control" onkeypress="return isNumber(event)" value="<%=fWHITMarital%>" readonly>
								</div>
							
								<div class="col-lg-4 col-md-4 col-sm-4">
									<label class="name form-div-6" >
											No. Of Allowances
									</label>	
						
										<input type="text" id="allowances" name="fWHITNoFAllowances" class="form-control" onkeypress="return isNumber(event)" value="<%=fWHITNoFAllowances%>" readonly>
								</div>
						</div>
						<div id="state_form"></div>	
					</div>
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
						<!--select name="PayPeriod" class="form-control" id="PayPeriod">
							<option value="<%=PayPeriod%>" selected><%=PayPeriod%></option>
							<option value="Daily" disabled="disabled">Daily</option>
							<option value="Weekly" disabled="disabled">Weekly</option>
							<option value="Biweekly" disabled="disabled">Bi-Weekly</option>
							<option value="Semimonthly" disabled="disabled">Semi-Monthly</option>
							<option value="Monthly" disabled="disabled">Monthly</option>
							<option value="Quarterly" disabled="disabled">Quarterly</option>
							<option value="Semiannually" disabled="disabled">Semi-Annually</option>
							<option value="Annually" disabled="disabled">Annually</option>
							<option value="miscellaneous" disabled="disabled">miscellaneous</option>
						</select-->
						
						<input type="text" name="PayPeriod" id="PayPeriod"  class="form-control" onkeypress="return isNumber(event)" value="<%=PayPeriod%>" readonly>
						
					</div>
					
					<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
								Pay Period : From
							</label>
							<input type="text" name="payDurationFrom" value="<%=payDurationFrom%>" class="form-control" id="payDurationFrom" placeholder="MM/DD/YYY">
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
								To
							</label>
							<input type="text" name="payDurationTo" value="<%=payDurationTo%>" class="form-control" id="payDurationTo" placeholder="MM/DD/YYY">
					</div>
				
				<!--div class="col-lg-2 col-md-2 col-sm-2"  >
	            <label class="name form-div-6" >
	            PayRate
				</label>
				<input type="text" id="payRate" name="payRate"  class="form-control" onkeypress="return isNumber(event)" >
				</div>-->
				
					<div class="col-lg-2 col-md-2 col-sm-2" >
						<label class="name form-div-6" >
						Choose Pay Rate Type
						</label>
						<!--select name="PayRateType" id="Payratecycle" class="form-control">
							<option value="<%=PayRateType%>" selected><%=PayRateType%></option>
							<option value="Hourly" disabled="disabled">Hourly</option>
							<option value="Monthly" disabled="disabled">Monthly</option>
							<option value="Annually" disabled="disabled">Annually</option>
						</select-->
						
						<input type="text" name="PayRateType" id="Payratecycle" class="form-control" onkeypress="return isNumber(event)" value="<%=PayRateType%>" readonly>
						
					</div>
					
					<div class="col-lg-3 col-md-3 col-sm-3">
						<!--div class=" "-->
						<% if (overtime != null && overtime.equalsIgnoreCase("on"))	{%>
							<input type="checkbox" name="overtime" id="overtime"  checked disabled="disabled">
							<input type="hidden" name="overtimecount" value="<%=ot_row_count_int%>" id="overtimecount">
							<input type="hidden" name="overtime" id="overtime"  value="on"/>
						<%}else if (overtime == null || overtime.equalsIgnoreCase("OFF")){%>
							<input type="checkbox" name="overtime" id="overtime" disabled="disabled">
							<input type="hidden" name="overtimecount" value="0" id="overtimecount">
						<%}%>
						<!--/div-->
						<label class="col-sm-offset-1 name"> Check If Overtime Work Applicable</label>
					</div>
					
				
				</div>
			</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
			
			
			<div id="Hourlyrow" >
					<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
					</div>			
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-1 col-md-1 col-sm-1">
						&nbsp;
						</div>
				
						<div class="col-lg-2 col-md-2 col-sm-2" style="color:#428bca; font-weight:bold"    >
							<label>Pay Rate</label>
							<input type="text" name="Hourly_payrate" value="<%=Hourly_payrate%>" class="form-control" id="Hourly_payrate" onkeypress="return isNumber(event)" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
						&nbsp;
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2" style="color:#428bca; font-weight:bold"    >
							<label>Pay Rate Type</label>
							<input type="text" name="Paytype" class="form-control" id="Paytype" value="<%=PayRateType%>" readonly>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2" style="color:#428bca; font-weight:bold"    >
							<label>Total Hours Worked</label>
							<input type="text" name="total_hours" id="total_hours" value="<%=total_hours%>" class="form-control" onkeypress="return isNumber(event)" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" style="color:#428bca; font-weight:bold"    >
							<label>YTD</label>
							<input type="text" name="YTD_Hourly" id="YTD_Hourly" class="form-control"  value="<%=YTD_Hourly%>" onkeypress="return isNumber(event)" readonly>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2" style="color:#428bca; font-weight:bold"    >
							<label>Gross Pay</label>
							<input type="text" name="total_gross_pay_hourly" id="gross_pay_hourly"  value="<%=total_gross_pay_hourly%>" readonly class="form-control">
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
				
				
				
				
				
		<% if (overtime != null && overtime.equalsIgnoreCase("on"))	{%>
		
				
				
				<div id="overtimerow" >
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
						<div class="col-lg-1 col-md-1 col-sm-1" align="center" style="color:#428bca; font-weight:bold"  >
							YTD
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="center" style="color:#428bca; font-weight:bold"  >
							CP
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="left" style="padding:10px;">
							<!--button id="addrow" disabled><i class="fa fa-plus-circle" aria-hidden="true"></i></button-->
						</div>
					</div>
				</div>
			<% for(int i=0;i<=ot_row_count_int;i++){ %>
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-1 col-md-1 col-sm-1">
						&nbsp;
						</div>
				
						<div class="col-lg-2 col-md-2 col-sm-2" >
				
							<input type="text" id="overtimerate<%=i%>" name="overtimerate<%=i%>" value="<%=overtimerate[i]%>"  class="form-control" onchange="overtimewagemore(Hourly_payrate,overtimepaytype<%=i%>,Hour_ot<%=i%>,cp_ot<%=i%>)" readonly>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2"   >
						&nbsp;
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2" align="right" >
							<!--select name="overtimepaytype<%=i%>" id="overtimepaytype<%=i%>"  class="form-control" onchange="overtimewagemore(overtimerate<%=i%>,overtimepaytype<%=i%>,Hour_ot<%=i%>,cp_ot<%=i%>)">
								<option value="<%=overtimepaytype[i]%>"><%=overtimepaytype[i]%></option>
								<option value="Hourly" disabled="disabled">Hourly</option>
								<option value="Monthly" disabled="disabled">Monthly</option>
							</select-->
							
							<input type="text" name="overtimepaytype<%=i%>" id="overtimepaytype<%=i%>" class="form-control" onkeypress="return isNumber(event)" value="<%=overtimepaytype[i]%>" readonly>
							
						</div>
				
					
						
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
					
							<input type="text" id="Hour_ot<%=i%>" name="Hour_ot<%=i%>"  class="form-control" value="<%=Hour_ot[i]%>" onkeypress="return isNumber(event)"  onchange="overtimewagemore(Hourly_payrate,overtimepaytype<%=i%>,Hour_ot<%=i%>,cp_ot<%=i%>)" readonly>
					
						</div>
						
			
					<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="ytd_ot<%=i%>" name="ytd_ot<%=i%>"  class="form-control" value="<%=ytd_ot[i]%>"  onkeypress="return isNumber(event)" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="cp_ot<%=i%>" name="cp_ot<%=i%>" readonly value="<%=cp_ot[i]%>" class="form-control" >
						</div>
						<% if(i>0){%>
							<!--input type="hidden" name="count"+i id="count"+i value=""+i>-->
							<input type="hidden" name="count<%=i%>" id="count<%=i%>" value="<%=i%>">
							<div class="col-lg-1 col-md-1 col-sm-1" align="left" style="padding:10px;">
								<!--button id="removerow" onclick="checkcount(count<%=i%>);" disabled><i class="fa fa-minus-circle" aria-hidden="true"></i>
								</button-->
							</div>
						<%}%>
					</div>
				</div>
			<%}%>
				</div>
			<%}else{%>	
			
			
			
			<%}%>
			
			<div id="Hourlyrow" style="display:none;">
							
							
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-1 col-md-1 col-sm-1">
						&nbsp;
						</div>
				
						<div class="col-lg-2 col-md-2 col-sm-2" style="color:#428bca; font-weight:bold"    >
							<label>Pay Rate</label>
							<input type="text" value="0.0" name="Hourly_payrate" class="form-control" id="Hourly_payrate" onkeypress="return isNumber(event)" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
						&nbsp;
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2" style="color:#428bca; font-weight:bold"    >
							<label>Pay Rate Type</label>
							<input type="text" name="Hourly" class="form-control" value="Hourly Rate" readonly>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2" style="color:#428bca; font-weight:bold"    >
							<label>Total Hours Worked</label>
							<input type="text" name="total_hours" id="total_hours" class="form-control" onkeypress="return isNumber(event)" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" style="color:#428bca; font-weight:bold"    >
							<label>YTD</label>
							<input type="text" name="YTD_Hourly" id="YTD_Hourly" value="0.0" class="form-control" onkeypress="return isNumber(event)" readonly>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2" style="color:#428bca; font-weight:bold"    >
							<label>Gross Pay</label>
							<input type="text" name="total_gross_pay_hourly" id="gross_pay_hourly" readonly class="form-control">
						</div>
					</div>
				</div>
			
			</div>
				
				
				
				
				
			<div id="Monthlyrow" style="display:none">
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
				
						<div class="col-lg-1 col-md-1 col-sm-1">
						&nbsp;
						</div>
				
						<div class="col-lg-2 col-md-2 col-sm-2" style="color:#428bca; font-weight:bold" >
							<label>Pay Rate</label>
							<input type="text" value="0.0" name="Monthly_payrate" id="Monthly_payrate" class="form-control" onkeypress="return isNumber(event)" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
						&nbsp;
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2" style="color:#428bca; font-weight:bold">
							<label>Pay Rate Type</label>
							<input type="text" name="Monthly" class="form-control" value="Monthly Rate" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
						&nbsp;
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" style="color:#428bca; font-weight:bold"    >
							<label>YTD</label>
							<input type="text" name="YTD_Monthly" id="YTD_Monthly" value="0.0" class="form-control" onkeypress="return isNumber(event)" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
						&nbsp;
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2" style="color:#428bca; font-weight:bold" >
							<label>Gross Pay</label>
							<input type="text" name="total_gross_pay_monthly" id="gross_pay_monthly" readonly class="form-control">
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
							<input type="text" id="overtimerate0" name="overtimerate0"  class="form-control" onchange="overtimewagemore(overtimerate0,overtimepaytype0,Hour_ot0,cp_ot0)">
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2"   >
							&nbsp;
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2" align="right" >
							<select name="overtimepaytype0" id="overtimepaytype0" class="form-control" onchange="overtimewagemore(overtimerate0,overtimepaytype0,Hour_ot0,cp_ot0)" disabled="disabled">
								<option value="">Select</option>
								<option value="Hourly">Hourly</option>
								<option value="Monthly">Monthly</option>
							</select>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="Hour_ot0" name="Hour_ot0"  class="form-control" onkeypress="return isNumber(event)"  onchange="overtimewagemore(overtimerate0,overtimepaytype0,Hour_ot0,cp_ot0)" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" value="0.0" id="ytd_ot0" name="ytd_ot0"  class="form-control" onkeypress="return isNumber(event)" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="cp_ot0" name="cp_ot0" value="0.0" readonly class="form-control" >
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="left" style="padding:10px;">
							<button id="addrow" disabled><i class="fa fa-plus-circle" aria-hidden="true"></i></button>
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
							<input type="text" id="federal_rate" name="federal_it_rate" value="<%=federal_it_rate%>" class="form-control" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="federal_annual" name="federal_it_annual" value="<%=federal_it_annual%>" class="form-control" readonly >
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="federal_ytd" name="federal_it_ytd" value="<%=federal_it_ytd%>" class="form-control" onkeypress="return isNumber(event)" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="federal_cp" name="federal_it_cp" value="<%=federal_it_cp%>" class="form-control" readonly>
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
							<input type="text" id="social_rate" name="social_rate" class="form-control" value="<%=social_rate%>" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="social_annual" name="social_annual" class="form-control" value="<%=social_annual%>" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="social_ytd" name="social_ytd" class="form-control" value="<%=social_ytd%>" onkeypress="return isNumber(event)" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="social_cp" name="social_cp" class="form-control" value="<%=social_cp%>" readonly >
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
							<input type="text" id="Medicare_rate" name="Medicare_rate" value="<%=Medicare_rate%>" class="form-control" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="Medicare_annual" name="Medicare_annual" value="<%=Medicare_annual%>" class="form-control" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="Medicare_ytd" name="Medicare_ytd" value="<%=Medicare_ytd%>" class="form-control" onkeypress="return isNumber(event)" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="Medicare_cp" name="Medicare_cp" value="<%=Medicare_cp%>" class="form-control" readonly>
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
							<input type="text" id="State_rate" name="State_IT_rate" value="<%=State_IT_rate%>" class="form-control" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="State_annual" name="State_IT_annual" value="<%=State_IT_annual%>" class="form-control"readonly >
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="State_ytd" name="State_IT_ytd" value="<%=State_IT_ytd%>" class="form-control" onkeypress="return isNumber(event)" readonly>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
							<input type="text" id="State_cp" name="State_IT_cp" value="<%=State_IT_cp%>"  class="form-control" readonly >
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
								<input type="text" id="After_Tax_Income" name="After_Tax_Income" value="<%=After_Tax_Income%>" class="form-control" readonly>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6" >
								YTD
								</label>
								<input type="text" id="After_Tax_Income_YTD" name="After_Tax_Income_YTD" value="<%=After_Tax_Income_YTD%>" class="form-control" onkeypress="return isNumber(event)" readonly>
								
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6" >
								CP
								</label>
								<input type="text" id="After_Tax_Income_CP" name="After_Tax_Income_CP" value="<%=After_Tax_Income_CP%>" class="form-control" onkeypress="return isNumber(event)" readonly >
							
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
								<input type="text" id="After_Tax_deduction" name="After_Tax_deduction" value="<%=After_Tax_deduction%>" class="form-control" readonly>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6" >
								YTD
								</label>
								<input type="text" id="After_Tax_deduction_YTD" name="After_Tax_deduction_YTD" value="<%=After_Tax_deduction_YTD%>" class="form-control" onkeypress="return isNumber(event)" readonly>
							
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6" >
								CP
								</label>
								<input type="text" id="After_Tax_deduction_CP" name="After_Tax_deduction_CP" value="<%=After_Tax_deduction_CP%>" class="form-control" onkeypress="return isNumber(event)" readonly >
							
							</div>
				
						</div>
				
					</div>
					
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
								<input type="text" readonly name="netAmount" value="<%=netAmount%>" class="form-control">
							</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2" style="font-weight:bold">
							 <input type="button" value="Back"  class="button-dang" id="back">
							</div>
							
							<%String roleIdInPayroll = null;
							roleIdInPayroll = (String)session.getAttribute("jobPostUserRoleId"); 
							
							if(roleIdInPayroll.equalsIgnoreCase("7B3AB408-3769-4C00-9852-849ACE00A8AF")){%>
							
							<%}else{
								System.out.println("inside else in roleIdInPayroll jsp--------------------------->"+roleIdInPayroll);%>
							
							<div class="col-lg-2 col-md-2 col-sm-2"  style="font-weight:bold">
							 <input type="submit" value="Edit" onclick='this.form.action="initEditPayroll.html";' class="button-edit" >
							</div>
							
							<%if((uniquePayrollId == null) || (uniquePayrollId == "") || (uniquePayrollId == "null") || (uniquePayrollId.equalsIgnoreCase("")) || (uniquePayrollId.equalsIgnoreCase("null"))){
								System.out.println("inside if in view jsp--------------------------->"+uniquePayrollId);%>
							<div class="col-lg-2 col-md-2 col-sm-2" style="font-weight:bold">
							 <input type="submit" value="Save" onclick='this.form.action="savePayStub.html";' class="button-add">
							</div>
							<%}else{
								System.out.println("inside else in view jsp--------------------------->"+uniquePayrollId);%>
							
							<div class="col-lg-2 col-md-2 col-sm-2"  style="font-weight:bold">
							 <input type="submit" value="Save" onclick='this.form.action="updatePayStub.html";' class="button-update">
							</div>
							<%}%>
							<%}%>
							<div class="col-lg-2 col-md-2 col-sm-2" style="font-weight:bold">
							 <input type="submit" value="Print" onclick='this.form.action="genertePayStubPdf.html";' class="button-print">
							</div>
							
							<div class="col-lg-1 col-md-1 col-sm-1">
							&nbsp;
							
							</div>
						 
					</div>
				</div>
				
			</form>
			<%}%><%}}%>
	
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

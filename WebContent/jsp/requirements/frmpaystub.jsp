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
<title>PayStub</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>	
<script>
function myFunction() {
	//alert("check");
	document.getElementById("header").style.display = "none";
	document.getElementById("foot").style.display = "none";
	document.getElementById("generatepdf").style.display = "none";
	//document.getElementById("cancel").style.display = "none";
    window.print();	
	//var empid = document.getElementById("empid").value;
	window.location.assign('GetFreshCandidateList.html');
}
</script>

<style>
.table-row-line.header
{ 
	 display: block;
	 flex-direction:row;
    background-color: #555555;
    font-weight: bold;
    padding-top: 16px;
    padding-bottom: 35px;
   

}
.table-row-line {
    border-bottom: 1px solid #cccccc;
    border-collapse: collapse;
    margin-left: 63px;
    margin-right: 30px;
    padding-right: initial;
    padding-left: initial;

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

<%
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

String Hourly_payrate = (String)request.getAttribute("Hourly_payrate");
String total_hours = (String)request.getAttribute("total_hours");
String YTD_Hourly = (String)request.getAttribute("YTD_Hourly");
String Monthly_payrate = (String)request.getAttribute("Monthly_payrate");
String YTD_Monthly = (String)request.getAttribute("YTD_Monthly");
		
String overtimecount = (String)request.getAttribute("overtimecount");
String totalGross = (String)request.getAttribute("totalGross");
String federalWHITAmount = (String)request.getAttribute("federalWHITAmount");
String StateWHITAmount_VA = (String)request.getAttribute("StateWHITAmount_VA");

int ot_row_count_int = 0;
ot_row_count_int = Integer.parseInt(overtimecount);
String[] overtimerate = new String[ot_row_count_int+1];
String[] overtimepaytype = new String[ot_row_count_int+1];
String[] Hour_ot = new String[ot_row_count_int+1];
String[] ytd_ot = new String[ot_row_count_int+1];
String[] cp_ot = new String[ot_row_count_int+1];

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
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				<img src="images/db.jpg" style="size:20px  margin-left: 39px; padding:0px; position: relative; " alt="logo">
				</div>
				</div>
				</div>
	   
	            <div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				
	            <div class="col-lg-2 col-md-2 col-sm-2"  style="font-weight:bold">
	            PAY TO 
	            </div>
	            <div class="col-lg-8 col-md-8 col-sm-8" style="font-weight:bold" align="right">
	            NET PAY 
	            </div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
	            </div>
	            </div>
	            
	            
	            <div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				
	            <div class="col-lg-2 col-md-2 col-sm-2">
	    		 Vani Aithal
	            </div>
	            <div class="col-lg-8 col-md-8 col-sm-8" align="right">
	             2,142.56
	            </div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
	            </div>
	            </div>
				
				
				
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				
	            <div class="col-lg-2 col-md-2 col-sm-2"  style="font-weight:bold">
	            EMPLOYEE ADDRESS
	            </div>
	            <div class="col-lg-8 col-md-8 col-sm-8" style="font-weight:bold" align="right"  >
	            EMPLOYEE NUMBER
	            </div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
	            </div>
	            </div>
				
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				
	            <div class="col-lg-2 col-md-2 col-sm-2"  >
	            13456 legacy circle Apartment, Herndon
	            </div>
	            <div class="col-lg-8 col-md-8 col-sm-8"  align="right" >
	            13653 
	            </div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
	            </div>
	            </div>
				
				
				
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				
	            <div class="col-lg-2 col-md-2 col-sm-2"  style="font-weight:bold">
	             PAY PERIOD
	            </div>
	            <div class="col-lg-8 col-md-8 col-sm-8" style="font-weight:bold" align="right" >
	             PAY DATE
	            </div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
	            </div>
	            </div>
				
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				
	            <div class="col-lg-2 col-md-2 col-sm-2"  >
	             12/01/2016 to 11/12/2017
	            </div>
	            <div class="col-lg-8 col-md-8 col-sm-8"  align="right" >
	             10-01-2017
	            </div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
	            </div>
	            </div>
				
	            
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				
	            <div class="col-lg-2 col-md-2 col-sm-2" style="font-weight:bold" >
	             PAID FROM
	            </div>
	            <div class="col-lg-8 col-md-8 col-sm-8" style="font-weight:bold" align="right" >
	             PAID BY
	            </div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
	            </div>
	            </div>
	            
				
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				
	            <div class="col-lg-2 col-md-2 col-sm-2"  >
	             Checking(2659)
	            </div>
	            <div class="col-lg-8 col-md-8 col-sm-8"  align="right" >
	             Direct Deposit to (3688)
	            </div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
	            </div>
	            </div>
	            
				
				
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2" style="font-weight:bold" >
				PAY
	            </div>
				</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="table-row-line wrapper header">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				
				<div class="col-lg-6 col-md-6 col-sm-6" style="color:#428bca"    >
	            TYPE
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" style="color:#428bca" >
	            HOURS
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" style="color:#428bca" >
	            RATE
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" style="color:#428bca"  >
	            CURRENT
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" style="color:#428bca"  >
	            YTD
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				</div>
				</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="table-row">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				
				<div class="col-lg-6 col-md-6 col-sm-6"   >
	            Salary
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	            86.67
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	            $36.76
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	            $3125.57
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	            $3125.57
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				</div>
				</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="table-row">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				
				<div class="col-lg-6 col-md-6 col-sm-6"   >
	            Total
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2"  >
	            &nbsp;
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	            $3125.57
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	            $3125.57
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
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
				<div class="col-lg-2 col-md-2 col-sm-2" style="font-weight:bold" >
				EMPLOYEE TAXES
	            </div>
				</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="table-row-line wrapper header">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6" style="color:#428bca"   >
	            TYPE
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2"  >
	            &nbsp;
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" style="color:#428bca"  >
	             CURRENT
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right"  style="color:#428bca" >
	            YTD
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				</div>
				</div>
				
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="table-row">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6"   >
	            Federal Income Tax
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2"  >
	            &nbsp;
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	            $581.56
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	              $581.56
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				</div>
				</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="table-row">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6"   >
	           Social Security
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2"  >
	            &nbsp;
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	            $581.56
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	              $581.56
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				</div>
				</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="table-row">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6"   >
	            Medicare
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2"  >
	            &nbsp;
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	            $581.56
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	              $581.56
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				</div>
				</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="table-row">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6"   >
	            VA Income Tax
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2"  >
	            &nbsp;
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	            $581.56
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	              $581.56
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				</div>
				</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				
				
				</div>
				
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="table-row">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6"  >
	            Total
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2"  >
	            &nbsp;
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	            $581.56
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	              $581.56
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
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
				<div class="col-lg-2 col-md-2 col-sm-2" style="font-weight:bold" >
				EMPLOYER TAXES
	            </div>
				</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="table-row-line wrapper header">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				
				<div class="col-lg-6 col-md-6 col-sm-6"  style="color:#428bca"  >
	            TYPE
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2"  >
	            &nbsp;
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" style="color:#428bca" >
	             CURRENT
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" style="color:#428bca"  >
	            YTD
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				</div>
				</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="table-row">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6"   >
	            Futa Employer
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2"  >
	            &nbsp;
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	            $81.56
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	              $81.56
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				</div>
				</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="table-row">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6"   >
	            social security Employer
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2"  >
	            &nbsp;
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	            $81.56
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	              $81.56
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				</div>
				</div>
				</div>
				
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="table-row">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6"   >
	             Medicare Employer
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2"  >
	            &nbsp;
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	            $81.56
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	              $81.56
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				</div>
				</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="table-row">
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6"   >
	             Total
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2"  >
	            &nbsp;
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	            $81.56
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1" align="right" >
	              $81.56
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
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
				
				<div class="col-lg-2 col-md-2 col-sm-2"  style="font-weight:bold">
	    		 Paid-off-time
	            </div>
				
				<div class="col-lg-2 col-md-2 col-sm-2"  style="font-weight:bold">
	    		 Hours used
	            </div>
				<div class="col-lg-2 col-md-2 col-sm-2"  style="font-weight:bold">
	    		 Hours Available
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
				
				<div class="col-lg-2 col-md-2 col-sm-2"  style="font-weight:bold">
	    		 vacation
	            </div>
				
				<div class="col-lg-2 col-md-2 col-sm-2"  style="font-weight:bold">
	    		 0.00
	            </div>
				<div class="col-lg-2 col-md-2 col-sm-2"  style="font-weight:bold">
	    		 0.00
	            </div>
				</div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="row">
	            <div class="col-lg-12 col-md-12 col-sm-12">
				<div class="table-row-line wrapper header">
				
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				
				<div class="col-lg-2 col-md-2 col-sm-2"  style="font-weight:bold">
	    		 <input type="button" value="cancel" style="height:30px; width:100px;  background-color:#555555; color:white" >
	            </div>
				
				<div class="col-lg-2 col-md-2 col-sm-2">
				&nbsp;
				</div>
				
				
				
				
				<div class="col-lg-2 col-md-2 col-sm-2">
				&nbsp;
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2"  align="right" style="font-weight:bold">
				<button id="generatepdf" onClick="myFunction()" style="height:30px; width:100px;background-color:#555555; color:white">PrintPaystub
				</button>
				</div>
				
				
				<div class="col-lg-1 col-md-1 col-sm-1">
				&nbsp;
				</div>
				</div>
				</div>
				</div>
				
			
	 </div>
	 </div>
	 	<!--========================================================
FOOTER
=========================================================-->
  <div id="foot">
    
    
    <!-- FOOTER STARTS HERE -->
    
    <%@ include file = "../../include/menschForceFooter.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>
			
</body>
</html>

<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@page import="com.hlccommon.util.HLCMenuListVO"%>
<%@ page import="java.util.*"%>
<%@ page import ="java.text.SimpleDateFormat"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Timesheet</title>
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="css/touchTouch.css">
     <link rel="stylesheet" href="css/owl.carousel.css">
     <link rel="stylesheet" href="css/style.css">
            
	   <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.css">
	<link rel="stylesheet" href="css/jquery-ui.css" type="text/css"/>
	<link rel="stylesheet" href="css/ui.theme.css" type="text/css"  />
	

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<!-- <script src="js/resumeSearch.js"></script> -->

	
<style>
#createTimesheet label.error{
	color:red;
}
body{
	height:100%;
	background:white;
}

.table-row-line.header {
    display: block;
    flex-direction: row;
    background-color: #dddddd;
    font-weight: bold;
    padding-top: 16px;
    padding-bottom: 35px;
}


</style>

 <!--CSS-->


<script src="js/tm-scripts.js"></script>
 <script  src="js/ts_picker.js"></script>
<script src="js/calendar2.js" type="text/javascript"></script>
<script src="js/datetimepicker_css.js"></script>    
   

	
</head>

<body id="top">

 <!--========================================================
HEADER
=========================================================-->
  <div id="header">
    <!-- HEADER STARTS HERE -->
  <%@ include file = "../../include/menschForceHeader.jsp" %>
      <!-- HEADER ENDS HERE -->
  </div>

<!--==============================header=================================-->

<script src="js/jquery.validate.js"></script>

<script>




function totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9){
	//alert("working");
		
		var stPeriod2 = stPeriod2.value;
		
		var stPeriod3 = stPeriod3.value;
		var stPeriod4 = stPeriod4.value;
		var stPeriod5 = stPeriod5.value;
		var stPeriod6 = stPeriod6.value;
		var stPeriod7 = stPeriod7.value;
		var stPeriod8 = stPeriod8.value;
		

		
		
		if(stPeriod2 != "" & stPeriod3 != "" & stPeriod4 != "" & stPeriod5 != "" & stPeriod6 != ""
		& stPeriod7 != "" & stPeriod8 != ""){
			
		var totalhours = parseInt(stPeriod2)+ parseInt(stPeriod3)+parseInt(stPeriod4)+parseInt(stPeriod5)+
		parseInt(stPeriod6)+parseInt(stPeriod7)+parseInt(stPeriod8);
		//alert("cheking 2 id==="+totalhours);
		stPeriod9.value=totalhours;

		}
		
	}
	
function overtimehours(OTPeriod2,OTPeriod3,OTPeriod4,OTPeriod5,OTPeriod6,OTPeriod7,OTPeriod8,OTPeriod9){
	//alert("working");
		
		var OTPeriod2 = OTPeriod2.value;
		
		var OTPeriod3 = OTPeriod3.value;
		var OTPeriod4 = OTPeriod4.value;
		var OTPeriod5 = OTPeriod5.value;
		var OTPeriod6 = OTPeriod6.value;
		var OTPeriod7 = OTPeriod7.value;
		var OTPeriod8 = OTPeriod8.value;
		

		
		
		if(OTPeriod2 != "" & OTPeriod3 != "" & OTPeriod4 != "" & OTPeriod5 != "" & OTPeriod6 != ""
		& OTPeriod7 != "" & OTPeriod8 != ""){
			
		var totalhours = parseInt(OTPeriod2)+ parseInt(OTPeriod3)+parseInt(OTPeriod4)+parseInt(OTPeriod5)+
		parseInt(OTPeriod6)+parseInt(OTPeriod7)+parseInt(OTPeriod8);
		//alert("cheking 2 id==="+totalhours);
		OTPeriod9.value=totalhours;

		}
		
	}
	
function timeoffhours(timeOff2,timeOff3,timeOff4,timeOff5,timeOff6,timeOff7,timeOff8,timeOff9){
	//alert("working");
		
		var timeOff2 = timeOff2.value;
		
		var timeOff3 = timeOff3.value;
		var timeOff4 = timeOff4.value;
		var timeOff5 = timeOff5.value;
		var timeOff6 = timeOff6.value;
		var timeOff7 = timeOff7.value;
		var timeOff8 = timeOff8.value;
		

		
		
		if(timeOff2 != "" & timeOff3 != "" & timeOff4 != "" & timeOff5 != "" & timeOff6 != ""
		& timeOff7 != "" & OTPeriod8 != ""){
			
		var totalhours = parseInt(timeOff2)+ parseInt(timeOff3)+parseInt(timeOff4)+parseInt(timeOff5)+
		parseInt(timeOff6)+parseInt(timeOff7)+parseInt(timeOff8);
		//alert("cheking 2 id==="+totalhours);
		timeOff9.value=totalhours;

		}
		
	}
	
	function isNumber(evt) {    
  var charCode = (evt.which) ? evt.which : evt.keyCode;     
  if (charCode != 46 && charCode > 31   && charCode != 43    
  && (charCode < 48 || charCode > 57))         
  return false;      
  return true; }
	
	 $(document).ready(function(){
	 

		 $( window ).on( "load", function() {
	
	var status=document.getElementById("hr_status").value;
			
    	if(status == "approved")
		{
		$('#createTimesheet select').attr('readonly', 'readonly');
		$('#createTimesheet input').attr('readonly', 'readonly');
		}
		

	});

		 
		 $("#stPeriod2").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#stPeriod2").trigger("blur");
	
	 $("#stPeriod3").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#stPeriod3").trigger("blur");
	 $("#stPeriod4").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#stPeriod4").trigger("blur");
	 $("#stPeriod5").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#stPeriod5").trigger("blur");
	 $("#stPeriod6").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#stPeriod6").trigger("blur");
	 $("#stPeriod7").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#stPeriod7").trigger("blur");
	 $("#stPeriod8").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#stPeriod8").trigger("blur");
	
	 $("#timeOff2").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#timeOff2").trigger("blur");
	
	 $("#timeOff3").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#timeOff3").trigger("blur");
	
	 $("#timeOff4").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#timeOff4").trigger("blur");
	
	 $("#timeOff5").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#timeOff5").trigger("blur");
	
	 $("#timeOff6").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#timeOff6").trigger("blur");
	
	 $("#timeOff7").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#timeOff7").trigger("blur");
	 $("#timeOff8").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#timeOff8").trigger("blur");
	 $("#timeOff9").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#timeOff9").trigger("blur");
	
	 $("#OTPeriod2").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#OTPeriod2").trigger("blur");
	
	$("#OTPeriod3").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#OTPeriod3").trigger("blur");
	$("#OTPeriod4").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#OTPeriod4").trigger("blur");
	$("#OTPeriod5").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#OTPeriod5").trigger("blur");
	$("#OTPeriod6").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#OTPeriod6").trigger("blur");
	$("#OTPeriod7").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#OTPeriod7").trigger("blur");
	$("#OTPeriod8").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#OTPeriod8").trigger("blur");
	
	$("#OTPeriod9").on("blur", function () {
	
		if ($(this).val().trim().length == 0) {
        $(this).val("0");
		}
		});

	$("#OTPeriod9").trigger("blur");
		 
		 $.validator.addMethod(
			"mobile",
			function(value, element) {
				// put your own logic here, this is just a (crappy) example
				return value.match(/^[0-9]*$/);
			},
			"alphabets are not accepted"
			);
		 
		 	
		$("#timeOff2").blur(function(){  
			var a = $("#timeOff2").val();
			var b = a.trim();
			$("#timeOff2").val(b);
		});
		
		$("#timeOff3").blur(function(){  
			var a = $("#timeOff3").val();
			var b = a.trim();
			$("#timeOff3").val(b);
		});
		$("#timeOff4").blur(function(){  
			var a = $("#timeOff4").val();
			var b = a.trim();
			$("#timeOff4").val(b);
		});

		$("#timeOff5").blur(function(){  
			var a = $("#timeOff5").val();
			var b = a.trim();
			$("#timeOff5").val(b);
		});

		$("#timeOff6").blur(function(){  
			var a = $("#timeOff6").val();
			var b = a.trim();
			$("#timeOff6").val(b);
		});

		$("#timeOff7").blur(function(){  
			var a = $("#timeOff7").val();
			var b = a.trim();
			$("#timeOff7").val(b);
		});

		$("#timeOff8").blur(function(){  
			var a = $("#timeOff8").val();
			var b = a.trim();
			$("#timeOff8").val(b);
		});
		
		$("#stPeriod2").blur(function(){  
			var a = $("#stPeriod2").val();
			var b = a.trim();
			$("#stPeriod2").val(b);
		});
		
		$("#stPeriod3").blur(function(){  
			var a = $("#stPeriod3").val();
			var b = a.trim();
			$("#stPeriod3").val(b);
		});
		$("#stPeriod4").blur(function(){  
			var a = $("#stPeriod4").val();
			var b = a.trim();
			$("#stPeriod4").val(b);
		});

		$("#stPeriod5").blur(function(){  
			var a = $("#stPeriod5").val();
			var b = a.trim();
			$("#stPeriod5").val(b);
		});

		$("#stPeriod6").blur(function(){  
			var a = $("#stPeriod6").val();
			var b = a.trim();
			$("#stPeriod6").val(b);
		});

		$("#stPeriod7").blur(function(){  
			var a = $("#stPeriod7").val();
			var b = a.trim();
			$("#stPeriod7").val(b);
		});

		$("#stPeriod8").blur(function(){  
			var a = $("#stPeriod8").val();
			var b = a.trim();
			$("#stPeriod8").val(b);
		});
		$("#OTPeriod2").blur(function(){  
			var a = $("#OTPeriod2").val();
			var b = a.trim();
			$("#OTPeriod2").val(b);
		});
		
		$("#OTPeriod3").blur(function(){  
			var a = $("#OTPeriod3").val();
			var b = a.trim();
			$("#OTPeriod3").val(b);
		});
		$("#OTPeriod4").blur(function(){  
			var a = $("#OTPeriod4").val();
			var b = a.trim();
			$("#OTPeriod4").val(b);
		});

		$("#OTPeriod5").blur(function(){  
			var a = $("#OTPeriod5").val();
			var b = a.trim();
			$("#OTPeriod5").val(b);
		});

		$("#OTPeriod6").blur(function(){  
			var a = $("#OTPeriod6").val();
			var b = a.trim();
			$("#OTPeriod6").val(b);
		});

		$("#OTPeriod7").blur(function(){  
			var a = $("#OTPeriod7").val();
			var b = a.trim();
			$("#OTPeriod7").val(b);
		});

		$("#OTPeriod8").blur(function(){  
			var a = $("#OTPeriod8").val();
			var b = a.trim();
			$("#OTPeriod8").val(b);
		});

		



	$('form[name="createTimesheet"]').validate({ 
	//alert("working");
	rules:{
		
		fromdate:"required",
		todate:"required",
		stPeriod2: {
					
					mobile:true,
					maxlength:2
				},
		stPeriod3: {
					
					mobile:true,
					maxlength:2
				},
		stPeriod4: {
					
					mobile:true,
					maxlength:2
				},
	  stPeriod5: {
					
					mobile:true,
					maxlength:2
				},
	  stPeriod6: {
					
					mobile:true,
					maxlength:2
				},
	 stPeriod7: {
					
					mobile:true,
					maxlength:2
				},
				stPeriod8: {
					
					mobile:true,
					maxlength:2
				},
		stPeriod9: {
					
					mobile:true,
					maxlength:2
				},
		
		filepath: {
			required:true,
			extension: "docx|doc|pdf",
		},
		 OTPeriod2: {
					
					mobile:true,
					maxlength:2
				},
				OTPeriod3: {
					
					mobile:true,
					maxlength:2
				},
				OTPeriod4: {
					
					mobile:true,
					maxlength:2
				},
				OTPeriod5: {
					
					mobile:true,
					maxlength:2
				},
				OTPeriod6: {
					
					mobile:true,
					maxlength:2
				},OTPeriod7: {
					
					mobile:true,
					maxlength:2
				},
				OTPeriod8: {
					
					mobile:true,
					maxlength:2
				},
				OTPeriod9: {
					
					mobile:true,
					maxlength:2
				},
				timeOff2: {
					
					mobile:true,
					maxlength:2
				},
				timeOff3: {
					
					mobile:true,
					maxlength:2
				},
				timeOff4: {
					
					mobile:true,
					maxlength:2
				},
				timeOff5: {
					
					mobile:true,
					maxlength:2
				},
				timeOff6: {
					
					mobile:true,
					maxlength:2
				},
				timeOff7: {
					
					mobile:true,
					maxlength:2
				},
				timeOff8: {
					
					mobile:true,
					maxlength:2
				},
				timeOff9: {
					
					mobile:true,
					maxlength:2
				}
		
		
	},
	
	messages:{
		fromdate:"From Date is required",
		todate:"To Date is required",
		stPeriod2:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
		
		stPeriod3:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
		stPeriod4:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
		stPeriod5:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
		stPeriod6:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
		stPeriod7:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
					stPeriod8:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
					stPeriod9:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
		
		filepath:
		{
			required: "Approved Timesheet is required",
			extension: "select valid input type",
			
		},
		OTPeriod2:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
					OTPeriod3:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
					OTPeriod4:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
					OTPeriod5:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
					OTPeriod6:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
					OTPeriod7:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
					OTPeriod8:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
					OTPeriod9:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
					timeOff2:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
					timeOff3:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
					timeOff4:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
					timeOff5:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
					timeOff6:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
					timeOff7:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
					timeOff8:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					},
					timeOff9:{
						
						mobile:"only number are  allowed",
						maxlength:"More than 2 digit is not allowed"
					}
		
		
		
		
		
		
		
		
		
	},
	errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}
	
});
});

</script>




<div class="content indent">
    
    <div class="thumb-box14">
	
        <div class="container">
          
		  <div class="row">
		  
		  <% 
					String pageStatus1 = (String)request.getAttribute("pageStatusvalue");
					System.out.println("pageStatus1================="+pageStatus1);
					%>
		  
					
		<% String formattedDate=request.getParameter("formattedDate");
		
		
		String newvalue=(String)request.getAttribute("tempvalue");
		System.out.println("newvalue------>"+newvalue);
		if(newvalue == null )
		{
			newvalue="0";
		}
		System.out.println("newvalue------>"+newvalue);
		System.out.println("formattedDate------>"+formattedDate);%>
		 
			<input type="hidden" name="previous_val" value="<%=newvalue%>">
		
			
			<div class="col-lg-12 col-md-12 col-sm-12">
					
                   <h3 class="title">Timesheet</h3>
			 </div>
			 
			 

				 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
				</div>
				
				<%
				String empuniqueId=(String)request.getAttribute("uniqueId");
				System.out.println("empuniqueId------>"+empuniqueId);
				String client_name=(String)request.getAttribute("client_name");
				String project_name=(String)request.getAttribute("project_name");
				String status=(String)request.getAttribute("status");
				if(status == null)
				{
					status="PreApproved";
				}
				
				System.out.println("clientname from jsp===="+client_name);
				System.out.println("project_name from jsp==="+project_name);
				
				
				
				
				%>
			 
			   

			<form name="createTimesheet" id="createTimesheet" method="post" action="#" class="formcss" onsubmit="return validate();" enctype="multipart/form-data">
			
				
			
				 <div class="col-lg-12 col-md-12 col-sm-12">
				 
				
					<div class="col-lg-1 col-md-1 col-sm-1">
						<label class="name">Project:</label>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
						<input type="text" name="project"  id="project"
							  class="form-control"  value="<%=project_name %>" readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
						<label class="name">Client Name:</label>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2"> 
						<input type="text" name="clientName"  id="clientName"
							  class="form-control"  value="<%=client_name %>" readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
						<label class="name">Status:</label>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2"> 
					
					 <% ArrayList timesheetstatus=(ArrayList) request.getAttribute("getEmployeeTimesheet"); 
					 String hr_status=null;
				if(timesheetstatus.size() != 0 )
				{
			//System.out.println(CandidateList.size()+" list ");
			System.out.println(timesheetstatus.size());
			
				
            Iterator itrt = timesheetstatus.iterator();
    while (itrt.hasNext()) {    
        String TempList[] = (String[])itrt.next();
				 hr_status=TempList[8];
			    
				System.out.println("hr_status"+hr_status);
	

%>   
				<input type="hidden"  id="hr_status" value="<%=hr_status%>"/>
				
						<input type="text" name="status"  id="status"
							  class="form-control"  value="<%=hr_status%>" readonly>
					</div>
					
				<%}} else {%>	
				<input type="text" name="status"  id="status"
							  class="form-control"  value="NotSubmitted" readonly>
				<%}%>
				
						
				</div>
				
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				
				
				<input type="hidden" name="empuniqueId" value="<%=empuniqueId%>"/>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				 <% ArrayList payperiod=(ArrayList) request.getAttribute("getEmployeeTimesheet"); %>
				<%if(payperiod.size() != 0 )
				{
			//System.out.println(CandidateList.size()+" list ");
			System.out.println(payperiod.size());
			
				
            Iterator itrt = payperiod.iterator();
    while (itrt.hasNext()) {    
        String TempList[] = (String[])itrt.next();
				String weekperiod=TempList[3];
			    
				System.out.println("weekperiod"+weekperiod);
		String[] weekperiods = weekperiod.split(" to ");
				String fromdate = weekperiods[0];
				String todate = weekperiods[1];

		%>
				
				
				<div class="col-lg-1 col-md-1 col-sm-1 ">
				<label class="name">Period:</label>
				</div>
				
					<div class="col-lg-2 col-md-2 col-sm-2 ">
						 <input name="fromdate" type="text" id="fromdate"
							  class="form-control" value="<%=fromdate%>" placeholder="FromDate" readonly>
					</div>
				<div class="col-lg-1 col-md-1 col-sm-1 ">
						<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('fromdate')" />
					</div>
					
					<div class="col-lg-2 col-md-2 col-sm-2 ">
						 <input name="todate" type="text" id="todate"
							  class="form-control" value="<%=todate%>" placeholder="To Date" readonly>
					</div>
				<div class="col-lg-1 col-md-1 col-sm-1 ">
						<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('todate')" />
		
		</div>
		</div>
		
				<%}}else{%>
				
					<%String sunday=(String)request.getAttribute("sunday");
				System.out.println("empuniqueId------>"+empuniqueId);
				String saturday=(String)request.getAttribute("saturday");%>
				
		<div class="col-lg-1 col-md-1 col-sm-1 ">
				<label class="name">Period:</label>
				</div>
				
					<div class="col-lg-2 col-md-2 col-sm-2 ">
						 <input name="fromdate" type="text" id="fromdate"
							  class="form-control"  placeholder="FromDate" value="<%=sunday%>" readonly>
					</div>
				<div class="col-lg-1 col-md-1 col-sm-1 ">
						<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('fromdate')" />
					</div>
					
					<div class="col-lg-2 col-md-2 col-sm-2 ">
						 <input name="todate" type="text" id="todate"
							  class="form-control" placeholder="To Date" value="<%=saturday%>" readonly>
					</div>
				<div class="col-lg-1 col-md-1 col-sm-1 ">
						<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('todate')" />
		
		</div>
				<%}%>

                     <div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
					</div>	


					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-2 col-md-2 col-sm-2" >
						
							<i class="fa fa-arrow-circle-left fa-2x" aria-hidden="true"  id="previous" name="previous" >&nbsp;
							<input type="button" value="Prev" onclick="location.href ='PreviousTimesheet.html?uniqueId=<%=empuniqueId%>&previous_val=<%=newvalue%>';" />
							</i> 
						</div>
						<div class="col-lg-7 col-md-7 col-sm-7" >
						&nbsp;
						</div>
						
						<div class="col-lg-2 col-md-2 col-sm-2" >
						
							<i class="fa fa-arrow-circle-right fa-2x" aria-hidden="true" id="next"  name="next">&nbsp;
							<% if(newvalue.equalsIgnoreCase ("0")){%>
							<input type="button"  name="next" id="next" value="Next"  disabled onclick="location.href ='NextTimesheet.html?uniqueId=<%=empuniqueId%>&Next_val=<%=newvalue%>';" />
							<%} else {%>
							<input type="button"  name="next" id="next" value="Next"  onclick="location.href ='NextTimesheet.html?uniqueId=<%=empuniqueId%>&Next_val=<%=newvalue%>';" />
							<%}%>
							</i>  
						</div>

						
</div>
<div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
					</div>							
					          
		<div class="col-lg-12 col-md-12 col-sm-12" style="display:block; background-color:#dddddd">
							
					<div class="table-row-line wrapper header">
					<div class="col-lg-1 col-md-1 col-sm-1 ">
				&nbsp;
				</div>
						
					<div class="col-lg-2 col-md-2 col-sm-2 " style="color:#0070c0">
					<label class="name"></label>
					</div>
					
					
					
					<% if(pageStatus1 != null &&  pageStatus1.equalsIgnoreCase("createTimesheet")){%>
						<%ArrayList createnewtimesheet=(ArrayList) request.getAttribute("createnewtimesheet"); 
						//System.out.println(CandidateList.size()+" list ");
						System.out.println(createnewtimesheet.size());
						
							
			            Iterator itr = createnewtimesheet.iterator();
			    while (itr.hasNext()) {    
			        String TempList[] = (String[])itr.next();
					String period1=TempList[0];
					 String text = TempList[1];
					
					 
		
		%>
						<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<label class="name"><%=period1%> </br> <%=text%></label>
	
				</div>
				<%
					}}
	%>
					
					
					<%String pageStatus = null;
					pageStatus = (String)request.getAttribute("pageStatus");
					%>
					
					<%if(pageStatus != null &&  pageStatus.equalsIgnoreCase("previousdate")){%>
					
					<%ArrayList previoustimesheet=(ArrayList) request.getAttribute("previoustimesheet"); 
			//System.out.println(CandidateList.size()+" list ");
			System.out.println(previoustimesheet.size());
			
				
            Iterator itr = previoustimesheet.iterator();
    while (itr.hasNext()) {    
        String TempList[] = (String[])itr.next();
		String previous_date=TempList[0];
		 String previous_day = TempList[1];
		
		 
    
    %>

 					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<label class="name"><%=previous_date%> </br> <%=previous_day%></label>
	
				</div>
				
						<%
					}}
	%>
	
	
	<%if(pageStatus != null &&  pageStatus.equalsIgnoreCase("nextdate")){%>
					
					<%ArrayList nexttimesheet=(ArrayList) request.getAttribute("nexttimesheet"); 
			//System.out.println(CandidateList.size()+" list ");
			System.out.println(nexttimesheet.size());
			
				
            Iterator itr = nexttimesheet.iterator();
    while (itr.hasNext()) {    
        String TempList[] = (String[])itr.next();
		String previous_date=TempList[0];
		 String previous_day = TempList[1];
		
		 
    
    %>

 					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<label class="name"><%=previous_date%> </br> <%=previous_day%></label>
	
				</div>
				
						<%
					}}
	%>
	
	
	<%if(pageStatus != null &&  pageStatus.equalsIgnoreCase("searchdate")){%>
					
					<%ArrayList searchdate=(ArrayList) request.getAttribute("createnewtimesheet"); 
			//System.out.println(CandidateList.size()+" list ");
			System.out.println(searchdate.size());
			
				
            Iterator itr = searchdate.iterator();
    while (itr.hasNext()) {    
        String TempList[] = (String[])itr.next();
		String previous_date=TempList[0];
		 String previous_day = TempList[1];
		
		 
    
    %>

 					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<label class="name"><%=previous_date%> </br> <%=previous_day%></label>
	
				</div>
				
						<%
					}}
	%>
			
				
					
						<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<label class="name">TotalHours </label>
					</div>
					</div>
		</div>
		
		 <div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
					</div>	
					
		<div class="col-lg-12 col-md-12 col-sm-12" >
		<% ArrayList getEmployeeTimesheet=(ArrayList) request.getAttribute("getEmployeeTimesheet"); %>
				<%if(getEmployeeTimesheet.size() != 0 )
				{
			//System.out.println(CandidateList.size()+" list ");
			System.out.println(getEmployeeTimesheet.size());
			
				
            Iterator itrt = getEmployeeTimesheet.iterator();
    while (itrt.hasNext()) {    
        String TempList[] = (String[])itrt.next();
			    String overtime_hrs=TempList[4];
		    	String sick_hrs	=TempList[5];
		    	String total_hrs = TempList[6];
				String timesheet_path = TempList[7];
				System.out.println("overtime_hrs"+overtime_hrs);
				System.out.println("sick_hrs"+sick_hrs);
				System.out.println("total_hrs"+total_hrs);
	
				String[] parts = total_hrs.split(",");
				String part1 = parts[0];
				String part2 = parts[1];
				String part3 = parts[2];
				String part4 = parts[3];
				String part5 = parts[4];
				String part6 = parts[5];
				String part7 = parts[6];
				String part8 = parts[7];
				
				String[] overtime = overtime_hrs.split(",");
				String overtimepart1 = overtime[0];
				String overtimepart2 = overtime[1];
				String overtimepart3 = overtime[2];
				String overtimepart4 = overtime[3];
				String overtimepart5 = overtime[4];
				String overtimepart6 = overtime[5];
				String overtimepart7 = overtime[6];
				String overtimepart8 = overtime[7];
				
				String[] sickhours = sick_hrs.split(",");
				String sickhourspart1 = sickhours[0];
				String sickhourspart2 = sickhours[1];
				String sickhourspart3 = sickhours[2];
				String sickhourspart4 = sickhours[3];
				String sickhourspart5 = sickhours[4];
				String sickhourspart6 = sickhours[5];
				String sickhourspart7 = sickhours[6];
				String sickhourspart8 = sickhours[7];

				String[] tempfilename = timesheet_path.split("/");
String tempfilename1 = tempfilename[0];
String tempfilename2 = tempfilename[1];
String tempfilename3 = tempfilename[2];
String tempfilename4 = tempfilename[3];

	
    %>			
					
			
					  
					  <div class="col-lg-1 col-md-1 col-sm-1 ">
				&nbsp;
				</div>
					
					<div class="col-lg-2 col-md-2 col-sm-2 " style="color:#0070c0">
					<input type="text" name="stPeriod1" id="stPeriod1" value="straight-time" class="form-control" readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
					<input type="text" name="stPeriod2" id="stPeriod2" value=<%=part1%> min="0" max="24" class="form-control"   onblur=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)" onkeypress="return isNumber(event)">
					
					</div>
					
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod3" id="stPeriod3"  value=<%=part2%> min="0" max="24" class="form-control"  onblur=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)"onkeypress="return isNumber(event)">
						
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod4" id="stPeriod4" value=<%=part3%> min="0" max="24" class="form-control"   onblur=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)" onkeypress="return isNumber(event)">
						
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod5" id="stPeriod5" value=<%=part4%> min="0" max="24" class="form-control"  onblur=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)" onkeypress="return isNumber(event)">
						
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod6" id="stPeriod6"  value=<%=part5%> min="0" max="24" class="form-control"  onblur=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)" onkeypress="return isNumber(event)">
						
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod7" id="stPeriod7" value=<%=part6%> min="0" max="24" class="form-control"  onblur=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)" onkeypress="return isNumber(event)">
						
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod8" id="stPeriod8" class="form-control" min="0" max="24" value=<%=part7%>  onblur=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)" onkeypress="return isNumber(event)">
						
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod9" id="stPeriod9"class="form-control" value=<%=part8%> readonly  onblur="totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)" onkeypress="return isNumber(event)">
					</div>
	
					</div>
	
					<div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
					</div>		
					
					<div class="col-lg-12 col-md-12 col-sm-12" >
							
					<div class="col-lg-1 col-md-1 col-sm-1 ">
				&nbsp;
				</div>
				
					
					<div class="col-lg-2 col-md-2 col-sm-2 " style="color:#0070c0">
					<input type="text" name="OTPeriod1" id="OTPeriod1" value="over-time" class="form-control" readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
					<input type="text" name="OTPeriod2" id="OTPeriod2" class="form-control"min="0" max="24" value=<%=overtimepart1%> onblur="overtimehours(OTPeriod2,OTPeriod3,OTPeriod4,OTPeriod5,OTPeriod6,OTPeriod7,OTPeriod8,OTPeriod9)" onkeypress="return isNumber(event)">
					</div>
					
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod3" id="OTPeriod3" class="form-control" min="0" max="24" value=<%=overtimepart2%> onblur="overtimehours(OTPeriod2,OTPeriod3,OTPeriod4,OTPeriod5,OTPeriod6,OTPeriod7,OTPeriod8,OTPeriod9)" onkeypress="return isNumber(event)" >
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod4" id="OTPeriod4" class="form-control" min="0" max="24" value=<%=overtimepart3%> onblur="overtimehours(OTPeriod2,OTPeriod3,OTPeriod4,OTPeriod5,OTPeriod6,OTPeriod7,OTPeriod8,OTPeriod9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod5" id="OTPeriod5" class="form-control" min="0" max="24" value=<%=overtimepart4%> onblur="overtimehours(OTPeriod2,OTPeriod3,OTPeriod4,OTPeriod5,OTPeriod6,OTPeriod7,OTPeriod8,OTPeriod9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod6" id="OTPeriod6" class="form-control" min="0" max="24" value=<%=overtimepart5%> onblur="overtimehours(OTPeriod2,OTPeriod3,OTPeriod4,OTPeriod5,OTPeriod6,OTPeriod7,OTPeriod8,OTPeriod9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod7" id="OTPeriod7" class="form-control" min="0" max="24" value=<%=overtimepart6%> onblur="overtimehours(OTPeriod2,OTPeriod3,OTPeriod4,OTPeriod5,OTPeriod6,OTPeriod7,OTPeriod8,OTPeriod9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod8" id="OTPeriod8" class="form-control" min="0" max="24" value=<%=overtimepart7%> onblur="overtimehours(OTPeriod2,OTPeriod3,OTPeriod4,OTPeriod5,OTPeriod6,OTPeriod7,OTPeriod8,OTPeriod9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod9" id="OTPeriod9" class="form-control" value=<%=overtimepart8%> readonly onblur="overtimehours(OTPeriod2,OTPeriod3,OTPeriod4,OTPeriod5,OTPeriod6,OTPeriod7,OTPeriod8,OTPeriod9)" onkeypress="return isNumber(event)">
					</div>
					
					</div>
					
					<div class="col-lg-12 col-md-12 col-sm-12" >
						&nbsp;
					</div>
					
					<div class="col-lg-12 col-md-12 col-sm-12" >
							
					<div class="col-lg-1 col-md-1 col-sm-1 ">
				&nbsp;
				</div>
					
					
					<div class="col-lg-2 col-md-2 col-sm-2 " style="color:#0070c0">
					<input type="text" name="" id="timeOff1" value="Time-off" class="form-control" readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
					<input type="text" name="timeOff2" id="timeOff2" class="form-control"   min="0" max="24" value=<%=sickhourspart1%> onblur="timeoffhours(timeOff2,timeOff3,timeOff4,timeOff5,timeOff6,timeOff7,timeOff8,timeOff9)" onkeypress="return isNumber(event)">
					</div>
					
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff3" id="timeOff3" class="form-control"  min="0" max="24"  value=<%=sickhourspart2%> onblur="timeoffhours(timeOff2,timeOff3,timeOff4,timeOff5,timeOff6,timeOff7,timeOff8,timeOff9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff4" id="timeOff4" class="form-control" min="0" max="24" value=<%=sickhourspart3%> onblur="timeoffhours(timeOff2,timeOff3,timeOff4,timeOff5,timeOff6,timeOff7,timeOff8,timeOff9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff5" id="timeOff5" class="form-control"  min="0" max="24" value=<%=sickhourspart4%> onblur="timeoffhours(timeOff2,timeOff3,timeOff4,timeOff5,timeOff6,timeOff7,timeOff8,timeOff9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff6" id="timeOff6" class="form-control" min="0" max="24"value=<%=sickhourspart5%> onblur="timeoffhours(timeOff2,timeOff3,timeOff4,timeOff5,timeOff6,timeOff7,timeOff8,timeOff9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff7" id="timeOff7" class="form-control"  min="0" max="24" value=<%=sickhourspart6%> onblur="timeoffhours(timeOff2,timeOff3,timeOff4,timeOff5,timeOff6,timeOff7,timeOff8,timeOff9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff8" id="timeOff8" class="form-control"  min="0" max="24" value=<%=sickhourspart7%> onblur="timeoffhours(timeOff2,timeOff3,timeOff4,timeOff5,timeOff6,timeOff7,timeOff8,timeOff9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff9" id="timeOff9" class="form-control" value=<%=sickhourspart8%> readonly onblur="timeoffhours(timeOff2,timeOff3,timeOff4,timeOff5,timeOff6,timeOff7,timeOff8,timeOff9)" onkeypress="return isNumber(event)">
					</div>
	
					</div>
					
					<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12" >
					
					<div class="col-lg-1 col-md-1 col-sm-1 ">
					&nbsp;
					</div>
					
					<div class="col-lg-3 col-md-3 col-sm-3">
						<input name="filepath" type="file" id="filepath" class="form-control"/>
					</div>
					
					
					<div class="col-lg-3 col-md-3 col-sm-3">
						
						<a href="DownloadFiles.html?filePath=<%=timesheet_path%>"   download/><%=tempfilename4%></a>
					</div>
					
				
					
					</div>
				
				<%}} else {%>	
				
				
				<div class="col-lg-12 col-md-12 col-sm-12" >
				
					  
					  <div class="col-lg-1 col-md-1 col-sm-1 ">
				&nbsp;
				</div>
					
					<div class="col-lg-2 col-md-2 col-sm-2 " style="color:#0070c0">
					<input type="text" name="stPeriod1" id="stPeriod1" value="straight-time" class="form-control" readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
					<input type="text" name="stPeriod2" id="stPeriod2" value="0" class="form-control"   onblur=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)" onkeypress="return isNumber(event)">
					</div>
					
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod3" id="stPeriod3"  value="0" class="form-control"  onblur=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod4" id="stPeriod4" value="0" class="form-control"   onblur=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod5" id="stPeriod5" value="0" class="form-control"  onblur=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod6" id="stPeriod6"  value="0" class="form-control"  onblur=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod7" id="stPeriod7" value="0" class="form-control"  onblur=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod8" id="stPeriod8" class="form-control" value="0"  onblur=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod9" id="stPeriod9"class="form-control" value="0" readonly  onblur="totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)" onkeypress="return isNumber(event)">
					</div>
	
					</div>
	
					<div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
					</div>		
					
					<div class="col-lg-12 col-md-12 col-sm-12" >
							
					<div class="col-lg-1 col-md-1 col-sm-1 ">
				&nbsp;
				</div>
				
					
					<div class="col-lg-2 col-md-2 col-sm-2 " style="color:#0070c0">
					<input type="text" name="OTPeriod1" id="OTPeriod1" value="over-time" class="form-control" readonly >
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
					<input type="text" name="OTPeriod2" id="OTPeriod2" class="form-control" value="0" onblur="overtimehours(OTPeriod2,OTPeriod3,OTPeriod4,OTPeriod5,OTPeriod6,OTPeriod7,OTPeriod8,OTPeriod9)" onkeypress="return isNumber(event)" >
					</div>
					
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod3" id="OTPeriod3" class="form-control" value="0" onblur="overtimehours(OTPeriod2,OTPeriod3,OTPeriod4,OTPeriod5,OTPeriod6,OTPeriod7,OTPeriod8,OTPeriod9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod4" id="OTPeriod4" class="form-control" value="0" onblur="overtimehours(OTPeriod2,OTPeriod3,OTPeriod4,OTPeriod5,OTPeriod6,OTPeriod7,OTPeriod8,OTPeriod9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod5" id="OTPeriod5" class="form-control" value="0" onblur="overtimehours(OTPeriod2,OTPeriod3,OTPeriod4,OTPeriod5,OTPeriod6,OTPeriod7,OTPeriod8,OTPeriod9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod6" id="OTPeriod6" class="form-control" value="0" onblur="overtimehours(OTPeriod2,OTPeriod3,OTPeriod4,OTPeriod5,OTPeriod6,OTPeriod7,OTPeriod8,OTPeriod9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod7" id="OTPeriod7" class="form-control" value="0" onblur="overtimehours(OTPeriod2,OTPeriod3,OTPeriod4,OTPeriod5,OTPeriod6,OTPeriod7,OTPeriod8,OTPeriod9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod8" id="OTPeriod8" class="form-control" value="0" onblur="overtimehours(OTPeriod2,OTPeriod3,OTPeriod4,OTPeriod5,OTPeriod6,OTPeriod7,OTPeriod8,OTPeriod9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod9" id="OTPeriod9" class="form-control" value="0" readonly onblur="overtimehours(OTPeriod2,OTPeriod3,OTPeriod4,OTPeriod5,OTPeriod6,OTPeriod7,OTPeriod8,OTPeriod9)" onkeypress="return isNumber(event)">
					</div>
					
					</div>
					
					<div class="col-lg-12 col-md-12 col-sm-12" >
						&nbsp;
					</div>
					
					<div class="col-lg-12 col-md-12 col-sm-12" >
							
					<div class="col-lg-1 col-md-1 col-sm-1 ">
				&nbsp;
				</div>
					
					
					<div class="col-lg-2 col-md-2 col-sm-2 " style="color:#0070c0">
					<input type="text" name="" id="timeOff1" value="Time-off" class="form-control" readonly >
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
					<input type="text" name="timeOff2" id="timeOff2" class="form-control"  value="0" onblur="timeoffhours(timeOff2,timeOff3,timeOff4,timeOff5,timeOff6,timeOff7,timeOff8,timeOff9)" onkeypress="return isNumber(event)">
					</div>
					
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff3" id="timeOff3" class="form-control" value="0" onblur="timeoffhours(timeOff2,timeOff3,timeOff4,timeOff5,timeOff6,timeOff7,timeOff8,timeOff9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff4" id="timeOff4" class="form-control" value="0" onblur="timeoffhours(timeOff2,timeOff3,timeOff4,timeOff5,timeOff6,timeOff7,timeOff8,timeOff9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff5" id="timeOff5" class="form-control" value="0" onblur="timeoffhours(timeOff2,timeOff3,timeOff4,timeOff5,timeOff6,timeOff7,timeOff8,timeOff9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff6" id="timeOff6" class="form-control" value="0" onblur="timeoffhours(timeOff2,timeOff3,timeOff4,timeOff5,timeOff6,timeOff7,timeOff8,timeOff9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff7" id="timeOff7" class="form-control"  value="0" onblur="timeoffhours(timeOff2,timeOff3,timeOff4,timeOff5,timeOff6,timeOff7,timeOff8,timeOff9)" onkeypress="return isNumber(event)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff8" id="timeOff8" class="form-control" value="0" onblur="timeoffhours(timeOff2,timeOff3,timeOff4,timeOff5,timeOff6,timeOff7,timeOff8,timeOff9)" onkeypress="return isNumber(event)" >
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff9" id="timeOff9" class="form-control" value="0" readonly onblur="timeoffhours(timeOff2,timeOff3,timeOff4,timeOff5,timeOff6,timeOff7,timeOff8,timeOff9)" onkeypress="return isNumber(event)">
					</div>
				
					</div>
				
					
					<div class="col-lg-12 col-md-12 col-sm-12" >
						&nbsp;
					</div>
					
					<div class="col-lg-12 col-md-12 col-sm-12" >
					
					<div class="col-lg-1 col-md-1 col-sm-1 ">
					&nbsp;
					</div>
					
					<div class="col-lg-3 col-md-3 col-sm-3">
						<input name="filepath" type="file" id="filepath" class="form-control"/>
					</div>
					
				
					
					</div>
				<%}%>
				
				
							
						<div class="col-lg-12 col-md-12 col-sm-12" >
						&nbsp;
					</div>	
			
				
				
				
				 
					
				
					
						<div class="col-lg-12 col-md-12 col-sm-12" >
						&nbsp;
					</div>	
			
				<div class="col-lg-12 col-md-12 col-sm-12">
						
						
						<div class="col-lg-5 col-md-5 col-sm-5">
						&nbsp;
						</div>	
						<% if(hr_status != null && hr_status.equalsIgnoreCase("approved") )	{%>
							<div class="col-lg-2 col-md -2 col-sm-2">
							 <button type="reset" class="button-dang" name="Back" value="Back" onClick="history.go(-1);">Back</button>
							</div> 
						<%}else{%>
							
								<% ArrayList buttonstatus=(ArrayList) request.getAttribute("getEmployeeTimesheet"); %>
				<%if(buttonstatus.size() != 0 )
				{%>
			
			<div class="col-lg-2 col-md-2 col-sm-2">
							 <button type="submit" class="button-edit" name="submit" value="submit" onClick='this.form.action="updateTimesheet.html?uniqueId=<%=empuniqueId%>";'>Update</button>
							</div>
							
				<%}else{%>
				
				<div class="col-lg-2 col-md-2 col-sm-2">
							 <button type="submit" class="button-add" name="submit" value="submit" onClick='this.form.action="Timesheet.html?uniqueId=<%=empuniqueId%>";'>Submit</button>
							</div>
						<%}}%>	

				<div class="col-lg-2 col-md-2 col-sm-2">
						&nbsp;
						</div>						
								
						</div>
 
 
 <div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
						</div>		
 
 </form>
			
		
						
		
	</div>
	</div>
	
		</div>
</div>
	

	<div>
	<!-- <button id="click" value="Click" >Click</button> -->
	
	<!-- FOOTER STARTS HERE -->
                        
						<%@ include file = "../../include/FooterProcess.jsp" %>
                    <!-- FOOTER ENDS HERE -->
					
	</div>
</body>

   <style>   
<!-- new form starts -->
		.news-box h1 {
    margin: 30px 0;
    color: #3d84e6;
}
		.top-margin {
    margin-top: 20px;
}
		.form-group .form-validation {
        position: absolute;
    top: 20px;
    right: 6px;
}

a:hover{
	color:#fff000;
}

<!-- new form ends -->
</style>

</html>

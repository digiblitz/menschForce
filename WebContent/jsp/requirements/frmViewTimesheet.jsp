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
body{
	height:100%;
	background:white;
}
#viewTimesheet label.error{
	color:red;
}
.table-row-line.header {
    display: block;
    flex-direction: row;
    background-color: #dddddd;
    font-weight: bold;
    padding-top: 16px;
    padding-bottom: 35px;
}
a.disabled {
   pointer-events: none;
   cursor: default;
}

</style>

 
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

$(document).ready(function(){

	$('#goto').blur(function(){
        if($(this).val().length !=0)
		{
			$('#gotosubmit').show();
		}           
        else
		{
			$('#gotosubmit').hide();
		}
    });
	
$('#status').change(function() {
    var selected = $(this).val();
    if(selected == 'Approved'){
      $('#reasonforrejection').hide();
	  $('#reason').hide();
	  $('#updatebyHR').show();
    }
    else if(selected == 'Rejected'){
      $('#reasonforrejection').show();
	  $('#updatebyHR').show();
    }
	else if(selected == 'Select')
	{
	 $('#reasonforrejection').hide();
		$('#updatebyHR').hide();	 
	}
	else
	{
		$('#updatebyHR').hide();
}
	 
});



	$('form[name="viewTimesheet"]').validate({ 
	//alert("working");
	rules:{
		
		fromdate:"required",
		todate:"required",
		stPeriod3:"required",
		stPeriod4:"required",
		stPeriod5:"required",
		stPeriod6:"required",
		stPeriod7:"required",
		reasonforrejection:"required",
		reason:"required"
		
	},
	
	messages:{
		fromdate:"From Date is required",
		todate:"To Date is required",
		stPeriod3:"enter the value",
		stPeriod4:"enter the value",
		stPeriod5:"enter the value",
		stPeriod6:"enter the value",
		stPeriod7:"enter the value",
		reasonforrejection:"Enter Reason for Rejection",
		reason:"Enter Reason for Rejection"
		
		
	},
	errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}
	
});



});
	
	
	function checkIfValid() {
  
 if (newvalue == "0") {
    next.disabled = false;
  } else {
    next.disabled = true;
  }
};



</script>
	
	
	
	
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
				System.out.println("clientname from jsp===="+client_name);
				System.out.println("project_name from jsp==="+project_name);

				
				
				
				%>
			 

						<form name="viewTimesheet" id="viewTimesheet" method="post" action="#" class="formcss" onsubmit="return validate();"  >
						
						<div class="col-lg-12 col-md-12 col-sm-12">
     <div class="col-lg-1 col-md-1 col-sm-1">
      <label class="name">Goto:</label>
     </div>
      <div class="col-lg-2 col-md-2 col-sm-2 ">
       <input name="goto" type="text" id="goto"
         class="form-control"  placeholder="Search Date" readonly>
     </div>
    <div class="col-lg-1 col-md-1 col-sm-1 ">
      <img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('goto')" />
  
         </div>
      
       
       <div class="col-lg-2 col-md-2 col-sm-2">
        <button type="submit" id="gotosubmit" style="display:none;" class="button-add" name="submit" value="submit" onClick='this.form.action="SearchDate.html?uniqueId=<%=empuniqueId%>";'>Goto</button>
       </div>
      </div>
						
			   <div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
					
					
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
					
					
					
						<% ArrayList status =(ArrayList) request.getAttribute("getEmployeeTimesheet"); 
			
				
            Iterator itrs = status.iterator();
    while (itrs.hasNext()) {    
        String Temp[] = (String[])itrs.next();
			    String hr_statuss=Temp[8];
				String rejection=Temp[10];
		    	
		    	
						
					if(hr_statuss != null && hr_statuss.equalsIgnoreCase("PreApproved") )	{%>
					<div class="col-lg-1 col-md-1 col-sm-1">
						<label class="name">Status:</label>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2"> 
					
							<select name="status" id="status" class="form-control" >
										<option value="Select">Select</option>
										
										<option value="Approved" >Approved</option>
										<option value="Rejected" >Rejected</option>
										</select>
						
						<%}else{%>
							
								<% ArrayList getstatus=(ArrayList) request.getAttribute("getEmployeeTimesheet"); %>
				<%if(getstatus.size() != 0 )
				{%>
			<%if(hr_statuss.equalsIgnoreCase("Approved")){%>
				<div class="col-lg-1 col-md-1 col-sm-1">
						<label class="name">Status:</label>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2"> 
				<input type="text" name="status"  id="status"
							  class="form-control"  value="<%=hr_statuss%>" readonly>
			<%}else{%>
			
			<div class="col-lg-1 col-md-1 col-sm-1">
						<label class="name">Status:</label>
					</div>
			<div class="col-lg-2 col-md-2 col-sm-2"> 
			<select name="status" id="status" class="form-control" >
							<option value="Approved" <%=hr_statuss.equals("Approved")?"selected":""%>>Approved</option>
							<option value="Rejected" <%=hr_statuss.equals("Rejected")?"selected":""%>>Rejected</option>
										
										
										</select>
										</div>
			
							  <div class="col-lg-2 col-md-2 col-sm-2">
						<input type="text" name="reason"  id="reason"
							  class="form-control"  value="<%=rejection%>" >
					</div>
	<%}}}}%>	
	
					</div>	
				</div>
				
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				
				
				<input type="hidden" name="empuniqueId" value="<%=empuniqueId%>"/>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				
				
				<% ArrayList getdate=(ArrayList) request.getAttribute("getEmployeeTimesheet"); %>
				<%if(getdate.size() != 0 )
				{
			//System.out.println(CandidateList.size()+" list ");
			System.out.println(getdate.size());
			
				
            Iterator itrt = getdate.iterator();
    while (itrt.hasNext()) {    
        String TempList[] = (String[])itrt.next();
				String week_period=TempList[3];
			    
				System.out.println("week_period"+week_period);
	
String[] parts = week_period.split(" to ");
String fromdate = parts[0];
String todate = parts[1];
%>

				<div class="col-lg-1 col-md-1 col-sm-1 ">
				<label class="name">Period:</label>
				</div>
				
					<div class="col-lg-2 col-md-2 col-sm-2 ">
						 <input name="fromdate" type="text" id="fromdate"
							  class="form-control" value=<%=fromdate%> placeholder="FromDate" readonly>
					</div>
				<div class="col-lg-1 col-md-1 col-sm-1 ">
						<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('fromdate')" />
					</div>
					
					<div class="col-lg-2 col-md-2 col-sm-2 ">
						 <input name="todate" type="text" id="todate"
							  class="form-control" value=<%=todate%> placeholder="To Date" readonly>
					</div>
				<div class="col-lg-1 col-md-1 col-sm-1 ">
						<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('todate')" />
		
		</div>
		
		
					<div class="col-lg-2 col-md-2 col-sm-2">
						<input type="text" name="reasonforrejection"  id="reasonforrejection"
							  class="form-control"  placeholder="Enter Reason" style="display:none;">
					</div>
				<%}} else {%>
				
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
		
		
					<div class="col-lg-2 col-md-2 col-sm-2">
						<input type="text" name="reasonforrejection"  id="reasonforrejection"
							  class="form-control"   placeholder="Enter Reason" style="display:none;">
					</div>
				<%}%>
		</div>
		
		 <div class="col-lg-12 col-md-12 col-sm-12">
					
					</div>

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
					<label class="name">project</label>
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
		<% ArrayList getEmployeeTimesheet=(ArrayList) request.getAttribute("getEmployeeTimesheet"); 
		   String timesheet_path=null;
		   String hr_status=null;
			if(getEmployeeTimesheet.size() != 0 )
				{
			//System.out.println(CandidateList.size()+" list ");
			System.out.println(getEmployeeTimesheet.size());
			
				
            Iterator itrt = getEmployeeTimesheet.iterator();
    while (itrt.hasNext()) {    
        String TempList[] = (String[])itrt.next();
				String week_period=TempList[3];
			    String overtime_hrs=TempList[4];
		    	String sick_hrs	=TempList[5];
		    	String total_hrs = TempList[6];
				 timesheet_path = TempList[7];
				 hr_status = TempList[8];
				System.out.println("week_period"+week_period);
				System.out.println("timesheet_path"+timesheet_path);
	
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
	System.out.println("timesheet_path"+timesheet_path);
    %>			
					
			
					  
					  <div class="col-lg-1 col-md-1 col-sm-1 ">
				&nbsp;
				</div>
					
					<div class="col-lg-2 col-md-2 col-sm-2 " style="color:#0070c0">
					<input type="text" name="stPeriod1" id="stPeriod1" value="straight-time" class="form-control" readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
					<input type="text" name="stPeriod2" id="stPeriod2" value=<%=part1%> class="form-control" readonly  onchange=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)">
					</div>
					
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod3" id="stPeriod3"  value=<%=part2%> class="form-control" readonly onchange=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod4" id="stPeriod4" value=<%=part3%> class="form-control"  readonly onchange=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod5" id="stPeriod5" value=<%=part4%> class="form-control" readonly onchange=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod6" id="stPeriod6"  value=<%=part5%> class="form-control" readonly onchange=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod7" id="stPeriod7" value=<%=part6%> class="form-control" readonly onchange=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod8" id="stPeriod8" class="form-control" value=<%=part7%> readonly onchange=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod9" id="stPeriod9"class="form-control" value=<%=part8%> readonly  onchange="totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)">
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
					<input type="text" name="OTPeriod2" id="OTPeriod2" class="form-control" value=<%=overtimepart1%> readonly>
					</div>
					
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod3" id="OTPeriod3" class="form-control" value=<%=overtimepart2%> readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod4" id="OTPeriod4" class="form-control" value=<%=overtimepart3%> readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod5" id="OTPeriod5" class="form-control" value=<%=overtimepart4%> readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod6" id="OTPeriod6" class="form-control" value=<%=overtimepart5%> readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod7" id="OTPeriod7" class="form-control" value=<%=overtimepart6%> readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod8" id="OTPeriod8" class="form-control" value=<%=overtimepart7%> readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod9" id="OTPeriod9" class="form-control" value=<%=overtimepart8%> readonly>
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
					<input type="text" name="timeOff2" id="timeOff2" class="form-control"  value=<%=sickhourspart1%> readonly>
					</div>
					
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff3" id="timeOff3" class="form-control" value=<%=sickhourspart2%> readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff4" id="timeOff4" class="form-control" value=<%=sickhourspart3%> readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff5" id="timeOff5" class="form-control" value=<%=sickhourspart4%> readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff6" id="timeOff6" class="form-control" value=<%=sickhourspart5%> readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff7" id="timeOff7" class="form-control"  value=<%=sickhourspart6%> readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff8" id="timeOff8" class="form-control" value=<%=sickhourspart7%> readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff9" id="timeOff9" class="form-control" value=<%=sickhourspart8%> readonly>
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
						<label class="name">Approved Timesheet: </label>
					     </div>
					
					<div class="col-lg-2 col-md-2 col-sm-2">
					
						
						
						<a href="DownloadFiles.html?filePath=<%=timesheet_path%>" style="text-decoration:underline;font-size:initial;"><%=tempfilename4%></a>
							
							
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
					<input type="text" name="stPeriod2" id="stPeriod2" value="0" class="form-control" readonly  onchange=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)">
					</div>
					
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod3" id="stPeriod3"  value="0" class="form-control" readonly onchange=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod4" id="stPeriod4" value="0" class="form-control"  readonly onchange=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod5" id="stPeriod5" value="0" class="form-control" readonly onchange=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod6" id="stPeriod6"  value="0" class="form-control" readonly onchange=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod7" id="stPeriod7" value="0" class="form-control" readonly onchange=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod8" id="stPeriod8" class="form-control" value="0" readonly onchange=" totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="stPeriod9" id="stPeriod9"class="form-control" value="0" readonly  onchange="totaltimehours(stPeriod2,stPeriod3,stPeriod4,stPeriod5,stPeriod6,stPeriod7,stPeriod8,stPeriod9)">
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
					<input type="text" name="OTPeriod2" id="OTPeriod2" class="form-control" value="0" readonly>
					</div>
					
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod3" id="OTPeriod3" class="form-control" value="0" readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod4" id="OTPeriod4" class="form-control" value="0" readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod5" id="OTPeriod5" class="form-control" value="0" readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod6" id="OTPeriod6" class="form-control" value="0" readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod7" id="OTPeriod7" class="form-control" value="0" readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod8" id="OTPeriod8" class="form-control" value="0" readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="OTPeriod9" id="OTPeriod9" class="form-control" value="0" readonly>
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
					<input type="text" name="timeOff2" id="timeOff2" class="form-control"  value="0" readonly>
					</div>
					
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff3" id="timeOff3" class="form-control" value="0" readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff4" id="timeOff4" class="form-control" value="0" readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff5" id="timeOff5" class="form-control" value="0" readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff6" id="timeOff6" class="form-control" value="0" readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff7" id="timeOff7" class="form-control"  value="0" readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff8" id="timeOff8" class="form-control" value="0" readonly>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 " style="color:#0070c0">
						<input type="text" name="timeOff9" id="timeOff9" class="form-control" value="0" readonly>
					</div>
				
					</div>
				
					
					<div class="col-lg-12 col-md-12 col-sm-12" >
						&nbsp;
					</div>
					
						<div class="col-lg-12 col-md-12 col-sm-12" >
					
					<div class="col-lg-1 col-md-1 col-sm-1 ">
					&nbsp;
					</div>
					
					
					</div>
				<%}%>
				
							
						<div class="col-lg-12 col-md-12 col-sm-12" >
						&nbsp;
					</div>	
					
				<input type="hidden" id="filepath" name="filepath" value="<%=timesheet_path%>"/>
				<input type="hidden" id="hr_status" name="hr_status" value="<%=hr_status%>"/>					
		
		
				
				<div class="col-lg-12 col-md-12 col-sm-12">
						
						<div class="col-lg-5 col-md-5 col-sm-5">
						&nbsp;
						</div>
							
							<div class="col-lg-2 col-md-2 col-sm-2">
							 <button type="reset" class="button-dang" name="Back" value="Back" onClick="history.go(-1);">Back</button>
							</div>
							
								
							<div class="col-lg-2 col-md-2 col-sm-2">
							 <button type="submit" style="display:none;" id="updatebyHR" class="button-add" name="submit" value="submit" onClick='this.form.action="updateTimesheetByHR.html?uniqueId=<%=empuniqueId%>";'>Submit</button>
							</div>
							
						</div>
 
<div class="col-lg-12 col-md-12 col-sm-12" >
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

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
<title>Department of State - Stage6</title>

<!--script src="js/countries.js"></script>--> 
 
<style>
#form3 label.error{color:red;}
.asterisk{
	color:red;
}


</style>

<%String pageStatus = null;
pageStatus = (String)request.getAttribute("pageStatus");
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
	
	
<script src="js/jquery.validate.js"></script>
<script src="js/additional-methods.js"></script>
<script src="js/DepartmentState.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/tm-scripts.js"></script>
<script  src="js/ts_picker.js"></script>
<script src="js/calendar2.js" type="text/javascript"></script>
<script src="js/datetimepicker_css.js"></script>
<link rel="stylesheet" href="css/style.css"/>

	
  <!--========================================================
CONTENT
=========================================================-->



	<div class="content indent">
	<div class="thumb-box14">
       
	   <%
	ArrayList employeeDetails=(ArrayList) request.getAttribute("employeeDetails"); 
	if(employeeDetails!=null && employeeDetails.size()!=0){
		Iterator itr = employeeDetails.iterator();
		while (itr.hasNext()) {
			String TempList[] = (String[])itr.next();
			String empuniqueId=TempList[0];
			String empId = TempList[3];
		 	String empdberpPartyid = TempList[4];
		 	String empSSNNo = TempList[5];
		 	String empFname=TempList[8];
		 	String empLname=TempList[10];
		 	String empEmailId=TempList[14];
		 	String empCompany=TempList[30];
		 	
			empSSNNo = String.valueOf(empSSNNo).substring(5);
    
			  if(pageStatus != null && pageStatus.equalsIgnoreCase("init")){
				  int stagesize=0;
				  String[] stageSixStatusArray =  (String[])request.getAttribute("stageSixStatusArray"); 
				  if(stageSixStatusArray != null){
					   stagesize= stageSixStatusArray.length; 
				  }else{
					   stagesize = 6;
				  }
    %>
	
	    <form name="frmDSstage1" id="form3" action="doDepartmentStateStage6.html" method="post" enctype="multipart/form-data">
		<input type="hidden" name="empUniqueId" value="<%=empuniqueId%>"/>
            	
				<div class="row">			
					<div class="col-lg-12 col-md-12 col-sm-12">
							
						
							<h3 class="title">Stage 6: Maintain Old I - 94 Form</h3>
						
					</div>
					
				</div>
			 
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
					<div class="row">	
					
					<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Employee Id</label>
								<span class="asterisk">*</span>	
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							  <input name="empId" type="text" id="userid" value="<%=empId%>" readonly class="form-control" />
							
							</div>
							
						</div>
				
			
				
						<div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Additional Documents</label>
								
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="AdditionalDocsStage6" type="file" id="AdditionalDocsStage6" class="form-control"/>
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Start Date</label>
								<span class="asterisk">*</span>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="StartDate" type="text" id="StartDate" class="form-control" readonly />
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal" >
								<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('StartDate','MMddyyyy','dropdown',true,'24',true)" />
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>End Date</label>
								<span class="asterisk">*</span>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="EndDate" type="text" id="EndDate" class="form-control" readonly />
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal" >
								<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('EndDate','MMddyyyy','dropdown',true,'24',true)" />
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Created Date</label>
								<span class="asterisk">*</span>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="CreateDate" type="text" id="CreateDate" class="form-control" readonly />
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal" >
								<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20"  style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('CreateDate','MMddyyyy','dropdown',true,'24',true)" />
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Acknowledgement Date</label>
								<span class="asterisk">*</span>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="AcknowledgementDate" type="text" readonly id="AcknowledgementDate" class="form-control"/>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal" >
								<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('AcknowledgementDate','MMddyyyy','dropdown',true,'24',true)"/>
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>System Date</label>
								
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="SystemDate" type="text" id="SystemDate" readonly class="form-control"/>
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Approval Status</label>
								<span class="asterisk">*</span>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<select name="ApprovalStatus"  id="ApprovalStatus" class="form-control">
									<option value="">Select</option>
									<option value="Approved">Approved</option>
									<option value="Pending">Pending</option>
									<option value="Rejected">Rejected</option>
								</select>
							</div>
							
						</div>
						
						
					</div>
					
					
				<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
				
				
				<div class="row">		
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-1 col-md-1 col-sm-1 col-sm-offset-3">
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1 ">
							<input type="button" class="button-edit" value="Previous"  onclick='location.href="initDepartmentStateStage5.html?empUniqueId=<%=empuniqueId%>";' name="previous" />
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="submit" value="Submit" name="submit" class="button-add"/>
							
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="button" value="Close" class="button-dang" id="close"/>
						</div>
						
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
						<div  class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-5 stagetitle ">
						<label class="">Stages</label>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-3 col-md-3 col-sm-3  col-sm-offset-5" align="center">
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage1" value="<%=stageSixStatusArray[0]%>" onclick='location.href="initDepartmentStateStage1.html?empUniqueId=<%=empuniqueId%>";'>1</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage2" value="<%=stageSixStatusArray[1]%>" onclick='location.href="initDepartmentStateStage2.html?empUniqueId=<%=empuniqueId%>";'  >2</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage3" value="<%=stageSixStatusArray[2]%>" onclick='location.href="initDepartmentStateStage3.html?empUniqueId=<%=empuniqueId%>";' >3</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage4" value="<%=stageSixStatusArray[3]%>" onclick='location.href="initDepartmentStateStage4.html?empUniqueId=<%=empuniqueId%>";' >4</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage5" value="<%=stageSixStatusArray[4]%>" onclick='location.href="initDepartmentStateStage5.html?empUniqueId=<%=empuniqueId%>";'>5</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage6" value="<%=stageSixStatusArray[5]%>" onclick='location.href="initDepartmentStateStage6.html?empUniqueId=<%=empuniqueId%>";'>6</button>
								
							</div>
						</div>
					</div>
				</div>
			
		</form>	
		
		<%}else if(pageStatus != null && pageStatus.equalsIgnoreCase("update")){ 
			String unique_DOS_id = (String)request.getAttribute("unique_DOS_id"); 
			String StartDate = (String)request.getAttribute("StartDate"); 
			String EndDate = (String)request.getAttribute("EndDate"); 
			String CreateDate = (String)request.getAttribute("CreateDate"); 
			String AcknowledgementDate = (String)request.getAttribute("AcknowledgementDate"); 
			String SystemDate = (String)request.getAttribute("SystemDate"); 
			String ApprovalStatus = (String)request.getAttribute("ApprovalStatus"); 
			String[] stageSixDataArray =  (String[])request.getAttribute("stageSixDataArray"); 
			String[] stageSixStatusArray =  (String[])request.getAttribute("stageSixStatusArray"); 
			
			//System.out.println("stageSixDataArray[0]-----------in--------jsp------->"+stageSixDataArray[0]);
			System.out.println("stageSixDataArray.length-----------in--------jsp------->"+stageSixDataArray.length);
			int stagesize= stageSixStatusArray.length;
		%>
		
		<form name="frmDSstage1" id="form3" action="doDepartmentStateStage6.html" method="post" enctype="multipart/form-data">
		<input type="hidden" name="empUniqueId" value="<%=empuniqueId%>"/>
		  <input type="hidden" value="<%=stagesize%>" name="stagesize" id="stagesize">
		  
           <%if(stageSixDataArray.length > 0){
		   if((stageSixDataArray[0] != null) && (stageSixDataArray[0] != "") && (stageSixDataArray[0] != "null" ) && !(stageSixDataArray[0].equalsIgnoreCase("null"))){%>
				<input type="hidden" name="oldAdditionalDocsStage6" value="<%=stageSixDataArray[0]%>"/>
		   <%}}%>
			<input type="hidden" name="AdditionalDocsStage6Check" id="additionalfileCheck" value=""/>
				<div class="row">			
					<div class="col-lg-12 col-md-12 col-sm-12">
							
						
							<h3 class="title">Stage 6: Maintain Old I - 94 Form</h3>
						
					</div>
					
				</div>
			 
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
					<div class="row">	
					
					<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Employee Id</label>
								<span class="asterisk">*</span>	
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							  <input name="empId" type="text" id="userid" value="<%=empId%>" readonly class="form-control" />
							
							</div>
							
						</div>
				
			
				
						<div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Additional Documents</label>
								
							</div>
							<%if(stageSixDataArray.length > 0){%>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<input type="checkbox" name="customuploadnew" id="customuploadnew">Upload New File
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
								<%if((stageSixDataArray[0] != null) && (stageSixDataArray[0] != "") && (stageSixDataArray[0] != "null" ) && !(stageSixDataArray[0].equalsIgnoreCase("null"))){
										String[] tempFileName = stageSixDataArray[0].split("/");
									%>
									<a href="DownloadFiles.html?filePath=<%=stageSixDataArray[0]%>" style="text-decoration:underline;font-size:initial;padding-left:10px;"><%=tempFileName[3]%></a>
									
								</div>
					<%}}else{%>
						<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="AdditionalDocsStage6" type="file" id="AdditionalDocsStage6" class="form-control"/>
							</div>
					<%}%>	
						</div>
						<div id="customuploadnewform">
							<div class="col-lg-12 col-md-12 col-sm-12">
								&nbsp;
							</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3"></div>
								<div class="col-lg-3 col-md-3 col-sm-3">
									
										<input name="AdditionalDocsStage6" type="file" id="additionalfileEdit" class="form-control"/>
									
								</div>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Start Date</label>
								<span class="asterisk">*</span>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="StartDate" value="<%=StartDate%>" type="text" id="StartDate" class="form-control" readonly />
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal" >
								<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('StartDate','MMddyyyy','dropdown',true,'24',true)" />
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>End Date</label>
								<span class="asterisk">*</span>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="EndDate" value="<%=EndDate%>" type="text" id="EndDate" class="form-control" readonly />
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal" >
								<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('EndDate','MMddyyyy','dropdown',true,'24',true)" />
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Created Date</label>
								<span class="asterisk">*</span>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="CreateDate" value="<%=CreateDate%>" type="text" id="CreateDate" class="form-control" readonly />
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal" >
								<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20"  style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('CreateDate','MMddyyyy','dropdown',true,'24',true)" />
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Acknowledgement Date</label>
								<span class="asterisk">*</span>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="AcknowledgementDate" value="<%=AcknowledgementDate%>" type="text" readonly id="AcknowledgementDate" class="form-control"/>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal" >
								<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('AcknowledgementDate','MMddyyyy','dropdown',true,'24',true)" />
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>System Date</label>
								
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="SystemDate" value="<%=SystemDate%>" type="text" id="SystemDate" readonly class="form-control"/>
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Approval Status</label>
								<span class="asterisk">*</span>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<select name="ApprovalStatus"  id="ApprovalStatus" class="form-control">
									<option value="Approved" <%=ApprovalStatus.equals("Approved")?"selected":""%>>Approved</option>
									<option value="Pending" <%=ApprovalStatus.equals("Pending")?"selected":""%>>Pending</option>
									<option value="Rejected" <%=ApprovalStatus.equals("Rejected")?"selected":""%>>Rejected</option>
								</select>
							</div>
							
						</div>
						
						
					</div>
					
					
				<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
				
				
				<div class="row">		
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-1 col-md-1 col-sm-1 col-sm-offset-3">
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1 ">
							<input type="button" class="button-edit" value="Previous"  onclick='location.href="initDepartmentStateStage5.html?empUniqueId=<%=empuniqueId%>";' name="previous" />
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="submit" value="Submit" name="submit" class="button-add"/>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="button" value="Close" class="button-dang" id="close"/>
						</div>
						<!--div class="col-lg-2 col-md-2 col-sm-2">
							<input type="submit" class="button-edit" value="Next" onclick='this.form.action="initDepartmentStateStage6.html?empUniqueId=<%=empuniqueId%>";' name="next" /-->
						</div>
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
				
				<div class="row">		
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div  class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-5 stagetitle ">
						<label class="">Stages</label>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-3 col-md-3 col-sm-3  col-sm-offset-5" align="center">
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage1" value="<%=stageSixStatusArray[0]%>" onclick='location.href="initDepartmentStateStage1.html?empUniqueId=<%=empuniqueId%>";'>1</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage2" value="<%=stageSixStatusArray[1]%>" onclick='location.href="initDepartmentStateStage2.html?empUniqueId=<%=empuniqueId%>";'  >2</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage3" value="<%=stageSixStatusArray[2]%>" onclick='location.href="initDepartmentStateStage3.html?empUniqueId=<%=empuniqueId%>";' >3</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage4" value="<%=stageSixStatusArray[3]%>" onclick='location.href="initDepartmentStateStage4.html?empUniqueId=<%=empuniqueId%>";' >4</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage5" value="<%=stageSixStatusArray[4]%>" onclick='location.href="initDepartmentStateStage5.html?empUniqueId=<%=empuniqueId%>";'>5</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage6" value="<%=stageSixStatusArray[5]%>" onclick='location.href="initDepartmentStateStage6.html?empUniqueId=<%=empuniqueId%>";'>6</button>
								
							</div>
								
							</div>
						</div>
					</div>
				</div>
			
		</form>	
		<%}}}%>
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

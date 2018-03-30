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
<title>Department of State</title>

<!--script src="js/countries.js"></script>--> 
 
<style>
#frmUSCISstage1 label.error{color:red;}
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
<script src="js/USCISforms.js"></script>
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
				  String[] stageOneStatusArray =  (String[])request.getAttribute("stageOneStatusArray"); 
				  if(stageOneStatusArray != null){
					   stagesize= stageOneStatusArray.length; 
				  }else{
					   stagesize = 8;
				  }
					
    %>
	    <form name="frmUSCISstage1" id="frmUSCISstage1" action="doUSCISStage1.html" method="post" enctype="multipart/form-data">
            	<input type="hidden" name="empUniqueId" value="<%=empuniqueId%>"/>
         <input type="hidden" value="<%=stagesize%>" name="stagesize" id="stagesize">
				<div class="row">			
					<div class="col-lg-12 col-md-12 col-sm-12">
							
						
							<h3 class="title">Stage 1: Proof Of Contract</h3>
						
					</div>
					
				</div>
			 
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
					<div class="row">	
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<h4 class="subtitle">Profile Details:</h4>
							</div>
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Employee Id<span class="asterisk">*</span></label>
								
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="empId" type="text" id="empId" class="form-control" readonly value="<%=empId%>"/>
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<h4 class="subtitle">Proof Of Contract:</h4>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Contract  <span class="asterisk">*</span></label>
								
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="ContractForm" type="file" id="ContractForm" class="form-control"/>
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Proof of Employment  <span class="asterisk">*</span></label>
								
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="EmploymentProof" type="file" id="EmploymentProof" class="form-control"/>
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Client Letter<span class="asterisk">*</span></label>
								
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="ClientLetter" type="file" id="ClientLetter" class="form-control"/>
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Work Order<span class="asterisk">*</span></label>
								
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="WorkOrder" type="file" id="WorkOrder" class="form-control"/>
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Labour Clearance<span class="asterisk">*</span></label>
								
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="LabourClearance" type="file" id="LabourClearance" class="form-control"/>
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
							<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Created Date and time:<span class="asterisk">*</span></label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="CreateDate" type="text" id="CreateDate" readonly class="form-control"/>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal" >
								<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('CreateDate','MMddyyyy','dropdown',true,'24',true)" />
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
								<input name="AcknowledgementDate" type="text" id="AcknowledgementDate" readonly class="form-control"/>
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
						
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
							
					</div>
					
					
				<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
				
				
				<div class="row">		
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-1 col-md-1 col-sm-1 col-sm-offset-3">
						</div>
						
						<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="submit"  value="Submit" class="button-add" />
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="button" id="close" value="Close" class="button-dang"/>
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
						<div  class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-4 stagetitle ">
						<label class="">Stages</label>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-4 col-md-4 col-sm-4  col-sm-offset-4" align="center">
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage1" value="" onclick='location.href="initUSCISStage1.html?empUniqueId=<%=empuniqueId%>";' >1</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage2" value="" onclick='location.href="initUSCISStage2.html?empUniqueId=<%=empuniqueId%>";' >2</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage3" value="" onclick='location.href="initUSCISStage3.html?empUniqueId=<%=empuniqueId%>";' >3</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage4" value="" onclick='location.href="initUSCISStage4.html?empUniqueId=<%=empuniqueId%>";' >4</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage5" value="" onclick='location.href="initUSCISStage5.html?empUniqueId=<%=empuniqueId%>";' >5</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage6" value="" onclick='location.href="initUSCISStage6.html?empUniqueId=<%=empuniqueId%>";' >6</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage7" value="" onclick='location.href="initUSCISStage7.html?empUniqueId=<%=empuniqueId%>";' >7</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage8" value="" onclick='location.href="initUSCISStage8.html?empUniqueId=<%=empuniqueId%>";' >8</button>
							</div>
						</div>
					</div>
				</div>
			
		</form>	
		
		<%}else if(pageStatus != null && pageStatus.equalsIgnoreCase("update")){ 
			String unique_USCIS_id = (String)request.getAttribute("unique_USCIS_id"); 
			String[] stageOneDataArray =  (String[])request.getAttribute("stageOneDataArray"); 
			String[] stageOneStatusArray =  (String[])request.getAttribute("stageOneStatusArray"); 
			System.out.println("stageOneDataArray[0]-----------in--------jsp------->"+stageOneDataArray[0]);
			System.out.println("stageOneDataArray[1]-----------in--------jsp------->"+stageOneDataArray[1]);
			System.out.println("stageOneDataArray[2]-----------in--------jsp------->"+stageOneDataArray[2]);
			System.out.println("stageOneDataArray[3]-----------in--------jsp------->"+stageOneDataArray[3]);
			System.out.println("stageOneDataArray[4]-----------in--------jsp------->"+stageOneDataArray[4]);
			int stagesize= stageOneStatusArray.length;
				System.out.println("stageOneDataArray.length---------in--------jsp------->"+stageOneDataArray.length);
					
		%>
		
		<form name="frmUSCISstage1" id="frmUSCISstage1" action="doUSCISStage1.html" method="post" enctype="multipart/form-data">
            	<input type="hidden" name="empUniqueId" value="<%=empuniqueId%>"/>
         <input type="hidden" value="<%=stagesize%>" name="stagesize" id="stagesize">
		 
		 <%if(stageOneDataArray[0] != null){%>
			<input type="hidden" name="oldContractForm" value="<%=stageOneDataArray[0]%>"/>
		<%}%>
		<%if(stageOneDataArray[1] != null){%>
			<input type="hidden" name="oldEmploymentProof" value="<%=stageOneDataArray[1]%>"/>
		<%}%>
		<%if(stageOneDataArray[2] != null){%>
			<input type="hidden" name="oldClientLetter" value="<%=stageOneDataArray[2]%>"/>
		<%}%>
		<%if(stageOneDataArray[3] != null){%>
			<input type="hidden" name="oldWorkOrder" value="<%=stageOneDataArray[3]%>"/>
		<%}%>
		<%if(stageOneDataArray[4] != null){%>
			<input type="hidden" name="oldLabourClearance" value="<%=stageOneDataArray[4]%>"/>
		<%}%>
		
		<input type="hidden" name="ContractFormCheck" id="ContractFormCheck" value=""/>
		<input type="hidden" name="EmploymentProofCheck" id="EmploymentProofCheck" value=""/>
		<input type="hidden" name="ClientLetterCheck" id="ClientLetterCheck" value=""/>
		<input type="hidden" name="WorkOrderCheck" id="WorkOrderCheck" value=""/>
		<input type="hidden" name="LabourClearanceCheck" id="LabourClearanceCheck" value=""/>
		
				<div class="row">			
					<div class="col-lg-12 col-md-12 col-sm-12">
							
						
							<h3 class="title">Stage 1: Proof Of Contract</h3>
						
					</div>
					
				</div>
			 
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
					<div class="row">	
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<h4 class="subtitle">Profile Details:</h4>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Employee Id<span class="asterisk">*</span></label>
								
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="empId" type="text" id="empId" class="form-control" readonly value="<%=empId%>"/>
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<h4 class="subtitle">Proof Of Contract:</h4>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Contract  <span class="asterisk">*</span></label>
								
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<input type="checkbox" name="uploadNewContract" id="uploadNewContract">Upload New File
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<%if(stageOneDataArray[0] != null){
								String[] tempFileName = stageOneDataArray[0].split("/");
								%>
								<a href="DownloadFiles.html?filePath=<%=stageOneDataArray[0]%>" style="text-decoration:underline;font-size:initial;"><%=tempFileName[3]%></a>
							<%}%>
							</div>
							
						</div>
						<div id="uploadNewContractForm">
							<div class="col-lg-12 col-md-12 col-sm-12">
								&nbsp;
							</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3"></div>
								<div class="col-lg-3 col-md-3 col-sm-3">
									<input name="ContractForm" type="file" id="ContractForm" class="form-control"/>
								</div>
							</div>
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Proof of Employment  <span class="asterisk">*</span></label>
								
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<input type="checkbox" name="uploadNewEmploymentProof" id="uploadNewEmploymentProof">Upload New File
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<%if(stageOneDataArray[1] != null){
								String[] tempFileName = stageOneDataArray[1].split("/");
								%>
								<a href="DownloadFiles.html?filePath=<%=stageOneDataArray[1]%>" style="text-decoration:underline;font-size:initial;"><%=tempFileName[3]%></a>
							<%}%>
							</div>
							
						</div>
						<div id="uploadNewEmploymentProofForm">
							<div class="col-lg-12 col-md-12 col-sm-12">
								&nbsp;
							</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3"></div>
								<div class="col-lg-3 col-md-3 col-sm-3">
									<input name="EmploymentProof" type="file" id="EmploymentProof" class="form-control"/>
								</div>
							</div>
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Client Letter<span class="asterisk">*</span></label>
								
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<input type="checkbox" name="uploadNewClientLetter" id="uploadNewClientLetter">Upload New File
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<%if(stageOneDataArray[2] != null){
								String[] tempFileName = stageOneDataArray[2].split("/");
								%>
								<a href="DownloadFiles.html?filePath=<%=stageOneDataArray[2]%>" style="text-decoration:underline;font-size:initial;"><%=tempFileName[3]%></a>
							<%}%>
							</div>
							
						</div>
						<div id="uploadNewClientLetterForm">
							<div class="col-lg-12 col-md-12 col-sm-12">
								&nbsp;
							</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3"></div>
								<div class="col-lg-3 col-md-3 col-sm-3">
									<input name="ClientLetter" type="file" id="ClientLetter" class="form-control"/>
								</div>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Work Order<span class="asterisk">*</span></label>
								
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<input type="checkbox" name="uploadNewWorkOrder" id="uploadNewWorkOrder">Upload New File
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<%if(stageOneDataArray[3] != null){
								String[] tempFileName = stageOneDataArray[3].split("/");
								%>
								<a href="DownloadFiles.html?filePath=<%=stageOneDataArray[3]%>" style="text-decoration:underline;font-size:initial;"><%=tempFileName[3]%></a>
							<%}%>
							</div>
							
						</div>
						<div id="uploadNewWorkOrderForm">
							<div class="col-lg-12 col-md-12 col-sm-12">
								&nbsp;
							</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3"></div>
								<div class="col-lg-3 col-md-3 col-sm-3">
									<input name="WorkOrder" type="file" id="WorkOrder" class="form-control"/>
								</div>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Labour Clearance<span class="asterisk">*</span></label>
								
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<input type="checkbox" name="uploadNewLabourClearance" id="uploadNewLabourClearance">Upload New File
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<%if(stageOneDataArray[4] != null){
								String[] tempFileName = stageOneDataArray[4].split("/");
								%>
								<a href="DownloadFiles.html?filePath=<%=stageOneDataArray[4]%>" style="text-decoration:underline;font-size:initial;"><%=tempFileName[3]%></a>
							<%}%>
							</div>
							
						</div>
						<div id="uploadNewLabourClearanceForm">
							<div class="col-lg-12 col-md-12 col-sm-12">
								&nbsp;
							</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3"></div>
								<div class="col-lg-3 col-md-3 col-sm-3">
									<input name="LabourClearance" type="file" id="LabourClearance" class="form-control"/>
								</div>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
							<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Created Date and time:<span class="asterisk">*</span></label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="CreateDate" type="text" id="CreateDate" readonly class="form-control" value="<%=(String)request.getAttribute("stage1CreateDate")%>"/>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal" >
								<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('CreateDate','MMddyyyy','dropdown',true,'24',true)" />
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
								<input name="AcknowledgementDate" type="text" id="AcknowledgementDate" readonly class="form-control" value="<%=(String)request.getAttribute("stage1AcknowledgementDate")%>"/>
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
								<input name="SystemDate" value="<%=(String)request.getAttribute("stage1SystemDate")%>" type="text" readonly id="SystemDate" class="form-control"/>
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
									<option value="Approved" <%=((String)request.getAttribute("stage1ApproveStatus")).equals("Approved")?"selected":""%>>Approved</option>
									<option value="Pending" <%=((String)request.getAttribute("stage1ApproveStatus")).equals("Pending")?"selected":""%>>Pending</option>
									<option value="Rejected" <%=((String)request.getAttribute("stage1ApproveStatus")).equals("Rejected")?"selected":""%>>Rejected</option>
								</select>
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						
							
					</div>
					
					
				<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
				
				
				<div class="row">		
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-1 col-md-1 col-sm-1 col-sm-offset-3">
						</div>
						
						<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="submit"  value="Submit" class="button-add" />
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="button" id="close" value="Close" class="button-dang"/>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2">
							<input type="button" value="Next" onclick='location.href="initUSCISStage2.html?empUniqueId=<%=empuniqueId%>";' class="button-edit" name="next" />
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
						<div  class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-4 stagetitle ">
						<label class="">Stages</label>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-4 col-md-4 col-sm-4  col-sm-offset-4" align="center">
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage1" value="<%=stageOneStatusArray[0]%>" onclick='location.href="initUSCISStage1.html?empUniqueId=<%=empuniqueId%>";' >1</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage2" value="<%=stageOneStatusArray[1]%>" onclick='location.href="initUSCISStage2.html?empUniqueId=<%=empuniqueId%>";' >2</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage3" value="<%=stageOneStatusArray[2]%>" onclick='location.href="initUSCISStage3.html?empUniqueId=<%=empuniqueId%>";' >3</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage4" value="<%=stageOneStatusArray[3]%>" onclick='location.href="initUSCISStage4.html?empUniqueId=<%=empuniqueId%>";' >4</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage5" value="<%=stageOneStatusArray[4]%>" onclick='location.href="initUSCISStage5.html?empUniqueId=<%=empuniqueId%>";' >5</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage6" value="<%=stageOneStatusArray[5]%>" onclick='location.href="initUSCISStage6.html?empUniqueId=<%=empuniqueId%>";' >6</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage7" value="<%=stageOneStatusArray[6]%>" onclick='location.href="initUSCISStage7.html?empUniqueId=<%=empuniqueId%>";' >7</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage8" value="<%=stageOneStatusArray[7]%>" onclick='location.href="initUSCISStage8.html?empUniqueId=<%=empuniqueId%>";' >8</button>
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

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
#frmDLstage10 label.error{color:red;}
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
<script src="js/DepartmentLabour.js"></script>
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
				  String[] stageTenStatusArray =  (String[])request.getAttribute("stageTenStatusArray"); 
				  if(stageTenStatusArray != null){
					   stagesize= stageTenStatusArray.length; 
				  }else{
					   stagesize = 20;
				  }
					
    %>
	    <form name="frmDLstage10" id="frmDLstage10" action="doDOLStage10.html" method="post" enctype="multipart/form-data">
            	<input type="hidden" name="empUniqueId" value="<%=empuniqueId%>"/>
         <input type="hidden" value="<%=stagesize%>" name="stagesize" id="stagesize">
				<div class="row">			
					<div class="col-lg-12 col-md-12 col-sm-12">
							
						
							<h3 class="title">Stage 10 : Document for Increasing the Pay</h3>
						
					</div>
					
				</div>
			 
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
					<div class="row">	
					
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
								
							<div class="col-lg-2 col-md-2 col-sm-2  col-sm-offset-3">
								<label>Document for Increase the Pay & communicate  
 to  all candidates who might re- consider their earlier decisions <span class="asterisk">*</span></label>
							</div >
							<div class="col-lg-2 col-md-2 col-sm-2">
								<input type="checkbox" value="yes" name="PayDocument" id="PayDocumentyes" checked>Yes
								<input type="checkbox" value="no" name="PayDocument" id="PayDocumentno">No
							</div>
							
						</div>
				
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2  col-sm-offset-3">
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<div id="Documentform">
									<input type="file" name="PayDocumentForm" class="form-control"/>
								</div>
								<div id="Documentnote" style="display:none;">
									<textarea rows="2" cols="3" name="NoPayDocumentNotes" Placeholder="Enter the Reason" class="form-control"></textarea>
								</div>
							</div>
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Created Date <span class="asterisk">*</span></label>
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
								<label>Acknowledgement Date <span class="asterisk">*</span></label>
								
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
								<label>System Date and Time</label>
								
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<input name="SystemDate" type="text" Placeholder="System Date" id="SystemDate" class="form-control" readonly />
							</div>
							
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Approval Status <span class="asterisk">*</span></label>
								
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
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<h4 class="subtitle">Custom Documents:</h4>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								

							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="CustomDocsStage10" type="file" id="CustomDocsStage10" class="form-control"/>
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
							<input type="button" class="button-edit" value="Previous" onclick='location.href="initDOLStage9.html?empUniqueId=<%=empuniqueId%>";' name="previous" />
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="submit"  value="Submit" class="button-add" />
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="button" class="button-dang" value="Close" />
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
						<div  class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-4 stagetitle ">
						<label class="">Stages</label>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-sm-5  col-sm-offset-4" >
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage1" value="" onclick='location.href="initDOLStage1.html?empUniqueId=<%=empuniqueId%>";' >1</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage2" value="" onclick='location.href="initDOLStage2.html?empUniqueId=<%=empuniqueId%>";' >2</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage3" value="" onclick='location.href="initDOLStage3.html?empUniqueId=<%=empuniqueId%>";' >3</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage4" value="" onclick='location.href="initDOLStage4.html?empUniqueId=<%=empuniqueId%>";' >4</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage5" value="" onclick='location.href="initDOLStage5.html?empUniqueId=<%=empuniqueId%>";' >5</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage6" value="" onclick='location.href="initDOLStage6.html?empUniqueId=<%=empuniqueId%>";' >6</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage7" value="" onclick='location.href="initDOLStage7.html?empUniqueId=<%=empuniqueId%>";' >7</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage8" value="" onclick='location.href="initDOLStage8.html?empUniqueId=<%=empuniqueId%>";' >8</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage9" value="" onclick='location.href="initDOLStage9.html?empUniqueId=<%=empuniqueId%>";' >9</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage10" value="" onclick='location.href="initDOLStage10.html?empUniqueId=<%=empuniqueId%>";' >10</button>
								
							</div>
						</div>
							
					</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-sm-5  col-sm-offset-4" >
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage11" value="" onclick='location.href="initDOLStage11.html?empUniqueId=<%=empuniqueId%>";' >11</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage12" value="" onclick='location.href="initDOLStage12.html?empUniqueId=<%=empuniqueId%>";' >12</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage13" value="" onclick='location.href="initDOLStage13.html?empUniqueId=<%=empuniqueId%>";' >13</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage14" value="" onclick='location.href="initDOLStage14.html?empUniqueId=<%=empuniqueId%>";' >14</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage15" value="" onclick='location.href="initDOLStage15.html?empUniqueId=<%=empuniqueId%>";' >15</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage16" value="" onclick='location.href="initDOLStage16.html?empUniqueId=<%=empuniqueId%>";' >16</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage17" value="" onclick='location.href="initDOLStage17.html?empUniqueId=<%=empuniqueId%>";' >17</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage18" value="" onclick='location.href="initDOLStage18.html?empUniqueId=<%=empuniqueId%>";' >18</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage19" value="" onclick='location.href="initDOLStage19.html?empUniqueId=<%=empuniqueId%>";' >19</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage20" value="" onclick='location.href="initDOLStage20.html?empUniqueId=<%=empuniqueId%>";' >20</button>
								
							</div>
					</div>
				</div>
			</div>
			
		</form>	
		
		<%}else if(pageStatus != null && pageStatus.equalsIgnoreCase("update")){ 
			String unique_DOL_id = (String)request.getAttribute("unique_DOL_id"); 
			String[] stageTenDataArray =  (String[])request.getAttribute("stageTenDataArray"); 
			String[] stageTenStatusArray =  (String[])request.getAttribute("stageTenStatusArray"); 
			
			int stagesize= stageTenStatusArray.length;
				System.out.println("stageTenDataArray.length---------in--------jsp------->"+stageTenDataArray.length);
					
		%>
		<%if(stageTenDataArray[0] != null){%>
			<input type="hidden" name="oldPayDocumentForm" value="<%=stageTenDataArray[0]%>"/>
		<%}%>
		<%if(stageTenDataArray[1] != null){%>
			<input type="hidden" name="oldCustomDocsStage10" value="<%=stageTenDataArray[1]%>"/>
		<%}%>
		<input type="hidden" name="PayDocumentFormCheck" id="PrevailingWageFormCheck" value=""/>
		<input type="hidden" name="CustomDocsStage10Check" id="CustomDocsStage10Check" value=""/>
		
		<form name="frmDLstage10" id="frmDLstage10" action="doDOLStage10.html" method="post" enctype="multipart/form-data">
            	<input type="hidden" name="empUniqueId" value="<%=empuniqueId%>"/>
         <input type="hidden" value="<%=stagesize%>" name="stagesize" id="stagesize">
				<div class="row">			
					<div class="col-lg-12 col-md-12 col-sm-12">
							
						
							<h3 class="title">Stage 10 : Document for Increasing the Pay</h3>
						
					</div>
					
				</div>
			 
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
					<div class="row">	
					
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
						
						<%if(((String)request.getAttribute("stage10ActualWageYesNo")).equalsIgnoreCase("yes")){
						%>
						<div class="col-lg-12 col-md-12 col-sm-12">
								
							<div class="col-lg-2 col-md-2 col-sm-2  col-sm-offset-3">
								<label>Document for Increase the Pay & communicate  
 to  all candidates who might re- consider their earlier decisions <span class="asterisk">*</span></label>
							</div >
							<div class="col-lg-2 col-md-2 col-sm-2">
								<input type="checkbox" value="yes" name="PayDocument" id="PayDocumentyes" checked>Yes
								<input type="checkbox" value="no" name="PayDocument" id="PayDocumentno">No
							</div>
							
						</div>
				
						<%}else{%>
						<div class="col-lg-12 col-md-12 col-sm-12">
								
							<div class="col-lg-2 col-md-2 col-sm-2  col-sm-offset-3">
								<label>Document for Increase the Pay & communicate  
 to  all candidates who might re- consider their earlier decisions <span class="asterisk">*</span></label>
							</div >
							<div class="col-lg-2 col-md-2 col-sm-2">
								<input type="checkbox" value="yes" name="PayDocument" id="PayDocumentyes">Yes
								<input type="checkbox" value="no" name="PayDocument" id="PayDocumentno" checked>No
							</div>
							
						</div>
						<%}%>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2  col-sm-offset-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<input type="checkbox" name="uploadPayDocumentForm" id="uploadPayDocumentForm">Upload New File
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<div id="Documentform">
								<%if(stageTenDataArray[0] != null){
									String[] tempFileName = stageTenDataArray[0].split("/");
									%>
									<a href="DownloadFiles.html?filePath=<%=stageTenDataArray[0]%>" style="text-decoration:underline;font-size:initial;"><%=tempFileName[3]%></a>
								<%}%>
								<div id="uploadPayDocumentFormForm">
									<div class="col-lg-12 col-md-12 col-sm-12">
										&nbsp;
									</div>
								
								<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3"></div>
										<div class="col-lg-3 col-md-3 col-sm-3">
											<input type="file" name="PayDocumentForm" class="form-control"/>
										</div>
									</div>
								</div>
									
								</div>
								<div id="Documentnote" style="display:none;">
									<textarea rows="2" cols="3" name="NoPayDocumentNotes" Placeholder="Enter the Reason" class="form-control"><%=(String)request.getAttribute("stage10ActualWageYesNo")%></textarea>
								</div>
							</div>
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Created Date <span class="asterisk">*</span></label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="CreateDate" type="text" id="CreateDate" readonly class="form-control" value="<%=(String)request.getAttribute("stage10CreateDate")%>"/>
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
								<label>Acknowledgement Date <span class="asterisk">*</span></label>
								
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="AcknowledgementDate" type="text" id="AcknowledgementDate" readonly class="form-control" value="<%=(String)request.getAttribute("stage10AcknowledgementDate")%>"/>
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
								<label>System Date and Time</label>
								
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<input name="SystemDate" type="text" Placeholder="System Date" id="SystemDate" class="form-control" readonly value="<%=(String)request.getAttribute("stage10SystemDate")%>"/>
							</div>
							
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Approval Status <span class="asterisk">*</span></label>
								
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<select name="ApprovalStatus"  id="ApprovalStatus" class="form-control">
									<option value="Approved" <%=((String)request.getAttribute("stage10ApproveStatus")).equals("Approved")?"selected":""%>>Approved</option>
									<option value="Pending" <%=((String)request.getAttribute("stage10ApproveStatus")).equals("Pending")?"selected":""%>>Pending</option>
									<option value="Rejected" <%=((String)request.getAttribute("stage10ApproveStatus")).equals("Rejected")?"selected":""%>>Rejected</option>
								</select>
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<h4 class="subtitle">Custom Documents:</h4>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								

							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<input type="checkbox" name="uploadCustomDocsStage10" id="uploadCustomDocsStage10">Upload New File
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<%if(stageTenDataArray[1] != null){
									String[] tempFileName = stageTenDataArray[1].split("/");
									%>
									<a href="DownloadFiles.html?filePath=<%=stageTenDataArray[1]%>" style="text-decoration:underline;font-size:initial;"><%=tempFileName[3]%></a>
								<%}%>
								
							</div>
							
						</div>
					
						<div id="uploadCustomDocsStage10Form">
							<div class="col-lg-12 col-md-12 col-sm-12">
								&nbsp;
							</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3"></div>
								<div class="col-lg-3 col-md-3 col-sm-3">
									<input name="CustomDocsStage10" type="file" id="CustomDocsStage10" class="form-control"/>
								</div>
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
							<input type="button" class="button-edit" value="Previous" onclick='location.href="initDOLStage9.html?empUniqueId=<%=empuniqueId%>";' name="previous" />
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="submit"  value="Submit" class="button-add" />
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="button" class="button-dang" value="Close" />
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
						<input type="button" value="Next" onclick='location.href="initDOLStage11.html?empUniqueId=<%=empuniqueId%>";' class="button-edit" name="next" />
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
						<div  class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-4 stagetitle ">
						<label class="">Stages</label>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-sm-5  col-sm-offset-4" >
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage1" value="<%=stageTenStatusArray[0]%>" onclick='location.href="initDOLStage1.html?empUniqueId=<%=empuniqueId%>";' >1</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage2" value="<%=stageTenStatusArray[1]%>" onclick='location.href="initDOLStage2.html?empUniqueId=<%=empuniqueId%>";' >2</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage3" value="<%=stageTenStatusArray[2]%>" onclick='location.href="initDOLStage3.html?empUniqueId=<%=empuniqueId%>";' >3</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage4" value="<%=stageTenStatusArray[3]%>" onclick='location.href="initDOLStage4.html?empUniqueId=<%=empuniqueId%>";' >4</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage5" value="<%=stageTenStatusArray[4]%>" onclick='location.href="initDOLStage5.html?empUniqueId=<%=empuniqueId%>";' >5</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage6" value="<%=stageTenStatusArray[5]%>" onclick='location.href="initDOLStage6.html?empUniqueId=<%=empuniqueId%>";' >6</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage7" value="<%=stageTenStatusArray[6]%>" onclick='location.href="initDOLStage7.html?empUniqueId=<%=empuniqueId%>";' >7</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage8" value="<%=stageTenStatusArray[7]%>" onclick='location.href="initDOLStage8.html?empUniqueId=<%=empuniqueId%>";' >8</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage9" value="<%=stageTenStatusArray[8]%>" onclick='location.href="initDOLStage9.html?empUniqueId=<%=empuniqueId%>";' >9</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage10" value="<%=stageTenStatusArray[9]%>" onclick='location.href="initDOLStage10.html?empUniqueId=<%=empuniqueId%>";' >10</button>
								
							</div>
						</div>
							
					</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-sm-5  col-sm-offset-4" >
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage11" value="<%=stageTenStatusArray[10]%>" onclick='location.href="initDOLStage11.html?empUniqueId=<%=empuniqueId%>";' >11</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage12" value="<%=stageTenStatusArray[11]%>" onclick='location.href="initDOLStage12.html?empUniqueId=<%=empuniqueId%>";' >12</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage13" value="<%=stageTenStatusArray[12]%>" onclick='location.href="initDOLStage13.html?empUniqueId=<%=empuniqueId%>";' >13</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage14" value="<%=stageTenStatusArray[13]%>" onclick='location.href="initDOLStage14.html?empUniqueId=<%=empuniqueId%>";' >14</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage15" value="<%=stageTenStatusArray[14]%>" onclick='location.href="initDOLStage15.html?empUniqueId=<%=empuniqueId%>";' >15</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage16" value="<%=stageTenStatusArray[15]%>" onclick='location.href="initDOLStage16.html?empUniqueId=<%=empuniqueId%>";' >16</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage17" value="<%=stageTenStatusArray[16]%>" onclick='location.href="initDOLStage17.html?empUniqueId=<%=empuniqueId%>";' >17</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage18" value="<%=stageTenStatusArray[17]%>" onclick='location.href="initDOLStage18.html?empUniqueId=<%=empuniqueId%>";' >18</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage19" value="<%=stageTenStatusArray[18]%>" onclick='location.href="initDOLStage19.html?empUniqueId=<%=empuniqueId%>";' >19</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage20" value="<%=stageTenStatusArray[19]%>" onclick='location.href="initDOLStage20.html?empUniqueId=<%=empuniqueId%>";' >20</button>
								
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

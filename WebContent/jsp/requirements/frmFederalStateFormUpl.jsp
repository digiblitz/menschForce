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
<title>Federal and State Withholding Form</title>

<!--script src="js/countries.js"></script>--> 
 
<style>
#form3 label.error{color:red;}
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
	
  <!--========================================================
CONTENT
=========================================================-->

<script src="js/formw4.js"></script>
<script src="js/cscombo_new.js" type="text/javascript" ></script>
<script src="js/jquery.validate.js"></script>
<script src="js/additional-methods.js"></script>

	<div class="content indent">
	<div class="thumb-box14">
       <% String emp_state =null;
	ArrayList employeeDetails=(ArrayList) request.getAttribute("employeeDetails"); 
	if(employeeDetails!=null && employeeDetails.size()!=0){
		Iterator itr = employeeDetails.iterator();
		while (itr.hasNext()) {
			String TempList[] = (String[])itr.next();
			String empuniqueId=TempList[0];
			String empUserId=TempList[1];
			String empRoleId=TempList[2];
		 	String empSSNNo = TempList[5];
		 	
			//empSSNNo = String.valueOf(empSSNNo).substring(5);
    
			 if(pageStatus != null && pageStatus.equalsIgnoreCase("init")){
    %>
	    <form name="form" id="form3" action="payrollFormUpload.html" method="post" enctype="multipart/form-data">
	    	<input type="hidden" name="empSSNNo" value="<%=empSSNNo%>"/>
	    	<input type="hidden" name="empuniqueId" value="<%=empuniqueId%>"/>
            	<div class="row">			
				
				

					<div class="col-lg-12 col-md-12 col-sm-12">
					
						<h3 class="title">Upload : (Federal and State Tax withholding Form)</h3>

					
					</div>
			 
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
					<div class="col-lg-12 col-md-12 col-sm-12">
					
						<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							
								<label class="name">Tax Year<span class="asterisk">*</span></label>
								
						</div>
						<div  class="col-lg-3 col-md-3 col-sm-3">
								<select name="taxYear" id="taxYear" class="form-control" > 
							 		<option value="2018">2018</option>
							 		<option value="2017" selected>2017</option>
							 		<option value="2016">2016</option>
							 	</select>
						</div>
					
					</div>
			
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
					<div class="col-lg-12 col-md-12 col-sm-12">
					
						<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							
								<label class="name">Select a federal tax withholding signed form ( Form W-4) <span class="asterisk">*</span></label>
								
						</div>
						<div  class="col-lg-3 col-md-3 col-sm-3">
									<input name="fileupload" type="file" id="fileupload"  class="form-control" />
							</div>
					
					</div>
			
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
						
						<label class="name">Country</label>
					</div>
					<div  class="col-lg-3 col-md-3 col-sm-3">
						<!--input type="text" value="USA" name="country"class="form-control"  readonly>-->
						 <select name="country" id="country" class="form-control" onChange="FillState(document.form.country, document.form.state, '');"> 
						 
						 </select>
						 
						
						</div>
					</div>
					
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
						<label class="name">State</label>
					</div>
					<div  class="col-lg-3 col-md-3 col-sm-3">
					 <select name="state" id="state" class="form-control" ></select>
					</div>
					
				 </div>
			
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				
					
				<div class="col-lg-12 col-md-12 col-sm-12">
					
						<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
						
							<label class="name">Select a  State tax withholding signed form <span class="asterisk">*</span></label>
							
						</div>
						<div  class="col-lg-3 col-md-3 col-sm-3">
							<input name="fileupload1" type="file" id="fileupload1"  class="form-control" />
						</div>
					
					
					
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				
					
				<div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
						<label class="name"> Upload Other Document:</label>
								
					</div>
					<div  class="col-lg-3 col-md-3 col-sm-3">
						<input name="file" type="file" id="fileupload"  class="form-control" />
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
					<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-4">
					<input id="back" type="button" class="button-dang" name="Back" value="Back" onClick="history.go(-1);" />
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
					<input type="submit" class="button-add" value="UploadFile" />
					</div>
					</div>
				</div>
			
		</form>	
		<%}else if(pageStatus != null && pageStatus.equalsIgnoreCase("edit")){
			
			ArrayList fedStateFormDetails=(ArrayList) request.getAttribute("fedStateFormDetails"); 
			if(fedStateFormDetails!=null && fedStateFormDetails.size()!=0){
				Iterator fedItr = fedStateFormDetails.iterator();
				while (fedItr.hasNext()) {
					String fedTempList[] = (String[])fedItr.next();
					String upload_id = fedTempList[0];
        			String emp_unique_id = fedTempList[1];
        			String emp_ssn_no = fedTempList[2];
        			String emp_tax_year = fedTempList[3];
        			String emp_country = fedTempList[4];
        			 emp_state = fedTempList[5];
        			String emp_federalform_path = fedTempList[6];
        			String emp_stateform_path = fedTempList[7];
        			String emp_additionalform_path = fedTempList[8];
        			String upload_date = fedTempList[9];
        			String upload_status = fedTempList[10];
					//System.out.println("================="+emp_additionalform_path.length);
		%>
		
		<form name="form" id="form3" action="payrollFormUpload.html" method="post" enctype="multipart/form-data">
	    	<input type="hidden" name="empSSNNo" value="<%=empSSNNo%>"/>
	    	<input type="hidden" name="empuniqueId" value="<%=empuniqueId%>"/>
	    	
	    	<input type="hidden" name="oldfileupload" value="<%=emp_federalform_path%>"/>
	    	<input type="hidden" name="oldfileupload1" value="<%=emp_stateform_path%>"/>
	    	<input type="hidden" name="oldfile" value="<%=emp_additionalform_path%>"/>
	    	
	    	<input type="hidden" name="uploadfederalnewCheck" id="uploadfederalnewCheck" value=""/>
			<input type="hidden" name="uploadstatenewCheck" id="uploadstatenewCheck" value=""/>
			<input type="hidden" name="customuploadnewCheck" id="customuploadnewCheck" value=""/>
	    	
            	<div class="row">			
				
				
				
				<div class="col-lg-12 col-md-12 col-sm-12">
					
						<h3 class="title">Edit : (Federal and State Tax withholding Form)</h3>
					
					</div>
			 
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
								<%String status= null;
				status = (String)request.getAttribute("status");
				if(status != null && status.equalsIgnoreCase("success")){
				%>

					<div class="col-lg-12 col-md-12 col-sm-12">
					
						<p class="asterisk col-sm-offset-3">Federal and State Tax withholding Form Successfully Uploaded</p>
					
					</div>
			 
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
			<%}else if(status != null && status.equalsIgnoreCase("fail")){%>	

			<div class="col-lg-12 col-md-12 col-sm-12">
					
						<p class="asterisk col-sm-offset-3">Failed to upload the Federal and State Tax withholding Form</p>
					
					</div>
			 
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				<%}else{}%>	
					<div class="col-lg-12 col-md-12 col-sm-12">
					
						<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							
								<label class="name">Tax Year<span class="asterisk">*</span></label>
								
						</div>
						<div  class="col-lg-3 col-md-3 col-sm-3">
								<select name="taxYear" id="taxYear" class="form-control" > 
							 		<option value="2018" <%=emp_tax_year.equals("2018")?"selected":""%>>2018</option>
							 		<option value="2017" <%=emp_tax_year.equals("2017")?"selected":""%>>2017</option>
							 		<option value="2016" <%=emp_tax_year.equals("2016")?"selected":""%>>2016</option>
							 	</select>
						</div>
					
					</div>
			
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
					<div class="col-lg-12 col-md-12 col-sm-12">
					
						<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							
								<label class="name">Select a federal tax withholding signed form ( Form W-4) <span class="asterisk">*</span></label>
								
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<input type="checkbox" name="uploadfederalnew" id="uploadfederalnew">Upload New File
							</div>
						<div  class="col-lg-3 col-md-3 col-sm-3">
							<%if(emp_federalform_path != null){
								String[] tempFileName = emp_federalform_path.split("/");
								%>
								<a href="DownloadFiles.html?filePath=<%=emp_federalform_path%>" style="text-decoration:underline;font-size:initial;">View Form</a>
								<input type="hidden" name="oldFederalForm" value="<%=emp_federalform_path%>"/>
							<%}%>
							
						</div>
					
					</div>
				<div id="uploadnewfederalform">
							<div class="col-lg-12 col-md-12 col-sm-12">
								&nbsp;
							</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3"></div>
								<div class="col-lg-3 col-md-3 col-sm-3">
									
										<input name="fileupload" type="file" id="fileupload" class="form-control" />
									
								</div>
							</div>
						</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
						
						<label class="name">Country</label>
					</div>
					<div  class="col-lg-3 col-md-3 col-sm-3">
						<!--input type="text" value="USA" name="country"class="form-control"  readonly>accept=".docx,.jpg,.gif,.doc,.pdf,.png" -->
						 <select name="country" id="country" class="form-control" > 
						 	
						 </select>
						 
						
						</div>
					</div>
					
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
						<label class="name">State</label>
					</div>
					<div  class="col-lg-3 col-md-3 col-sm-3">
					 <select name="state" id="state" class="form-control" >
					 	
					 </select>
					</div>
					
				 </div>
			
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				
					
				<div class="col-lg-12 col-md-12 col-sm-12">
					
						<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
						
							<label class="name">Select a  State tax withholding signed form <span class="asterisk">*</span></label>
							
						</div>
						<%if(emp_stateform_path != null){%>
						<div class="col-lg-2 col-md-2 col-sm-2">
								<input type="checkbox" name="uploadstatenew" id="uploadstatenew">Upload New File
							</div>
						<div  class="col-lg-3 col-md-3 col-sm-3">
							
								<%String[] tempFileName = emp_stateform_path.split("/");
								%>
								<a href="DownloadFiles.html?filePath=<%=emp_stateform_path%>" style="text-decoration:underline;font-size:initial;">View Form</a>
								<input type="hidden" name="oldStateForm" value="<%=emp_stateform_path%>"/>
								</div>
							<%}else{%>
							<div  class="col-lg-3 col-md-3 col-sm-3">
								<input name="fileupload1" type="file" id="fileupload1"  class="form-control" />
							</div>
							<%}%>
						
					
					
					
				</div>
				
				<div id="uploadnewstateform">
							<div class="col-lg-12 col-md-12 col-sm-12">
								&nbsp;
							</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3"></div>
								<div class="col-lg-3 col-md-3 col-sm-3">
									
										<input name="fileupload1" type="file" id="fileupload1"  class="form-control" />
									
								</div>
							</div>
						</div>
						
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				
					
				<div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
						<label class="name"> Upload Other Document:</label>
								
					</div>
				
						<div class="col-lg-2 col-md-2 col-sm-2">
								<input type="checkbox" name="customuploadnew" id="customuploadnew">Upload New File
							</div>
					
						<%if(emp_additionalform_path != null && emp_additionalform_path != "null" && !(emp_additionalform_path.equalsIgnoreCase("null")) && !(emp_additionalform_path.equalsIgnoreCase(""))){
								String[] tempFileName = emp_additionalform_path.split("/");
								%>
							<div  class="col-lg-3 col-md-3 col-sm-3">
								<a href="DownloadFiles.html?filePath=<%=emp_additionalform_path%>" style="text-decoration:underline;font-size:initial;">View Form</a>
								<input type="hidden" name="oldAdditionalForm" value="<%=emp_additionalform_path%>"/>
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
									
										<input name="file" type="file" id="fileupload"  class="form-control" />
									
								</div>
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
					<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-4">
					<input id="back" type="button" class="button-dang" name="Back" value="Back" onClick="history.go(-1);" />
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
					<input type="submit" class="button-add" value="UploadFile" />
					</div>
					</div>
				</div>
			
		</form>	
		<%
		}}}else if(pageStatus != null && pageStatus.equalsIgnoreCase("view")){

			ArrayList fedStateFormDetails=(ArrayList) request.getAttribute("fedStateFormDetails"); 
			if(fedStateFormDetails!=null && fedStateFormDetails.size()!=0){
				Iterator fedItr = fedStateFormDetails.iterator();
				while (fedItr.hasNext()) {
					String fedTempList[] = (String[])fedItr.next();
					String upload_id = fedTempList[0];
        			String emp_unique_id = fedTempList[1];
        			String emp_ssn_no = fedTempList[2];
        			String emp_tax_year = fedTempList[3];
        			String emp_country = fedTempList[4];
        			 emp_state = fedTempList[5];
        			String emp_federalform_path = fedTempList[6];
        			String emp_stateform_path = fedTempList[7];
        			String emp_additionalform_path = fedTempList[8];
        			String upload_date = fedTempList[9];
        			String upload_status = fedTempList[10];
					//System.out.println("================="+emp_additionalform_path.length);
		
			%>
			<form name="form" id="form3" action="payrollFormUpload.html" method="post" enctype="multipart/form-data">
	    	<input type="hidden" name="empSSNNo" value="<%=empSSNNo%>"/>
	    	<input type="hidden" name="empuniqueId" value="<%=empuniqueId%>"/>
	    	
            	<div class="row">			
				
				<div class="col-lg-12 col-md-12 col-sm-12">
					
						<h3 class="title">View : (Federal and State Tax withholding Form)</h3>
					
					</div>
			 
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
					
						<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							
								<label class="name">Tax Year<span class="asterisk">*</span></label>
								
						</div>
						<div  class="col-lg-3 col-md-3 col-sm-3">
								<!--select name="taxYear" id="taxYear" class="form-control" readonly> 
							 		<option value="2018" <%=emp_tax_year.equals("2018")?"selected":""%>>2018</option>
							 		<option value="2017" <%=emp_tax_year.equals("2017")?"selected":""%>>2017</option>
							 		<option value="2016" <%=emp_tax_year.equals("2016")?"selected":""%>>2016</option>
							 	</select-->
								<label class="name"><%=emp_tax_year%></label>
						</div>
					
					</div>
			
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
					<div class="col-lg-12 col-md-12 col-sm-12">
					
						<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							
								<label class="name">federal tax withholding signed form ( Form W-4) <span class="asterisk">*</span></label>
								
						</div>
							
						<div  class="col-lg-3 col-md-3 col-sm-3">
							<%if(emp_federalform_path != null){
								String[] tempFileName = emp_federalform_path.split("/");
								%>
								<a href="DownloadFiles.html?filePath=<%=emp_federalform_path%>" style="text-decoration:underline;font-size:initial;">View Form</a>
								
							<%}%>
							
						</div>
					
					</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
						
						<label class="name">Country</label>
					</div>
					<div  class="col-lg-3 col-md-3 col-sm-3">
						<!--input type="text" value="USA" name="country"class="form-control"  readonly>-->
						 <!--select name="country" id="country" class="form-control" readonly> 
						 	
						 </select-->
						 <label class="name"><%=emp_country%></label>

						</div>
					</div>
					
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
						<label class="name">State</label>
					</div>
					<div  class="col-lg-3 col-md-3 col-sm-3">
					 <!--select name="state" id="state" class="form-control" readonly>
					 	
					 </select-->
					 <label class="name"><%=emp_state%></label>
					</div>
					
				 </div>
			
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				
					
				<div class="col-lg-12 col-md-12 col-sm-12">
					
						<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
						
							<label class="name">State tax withholding signed form <span class="asterisk">*</span></label>
							
						</div>
						<%if(emp_stateform_path != null){%>
						<div  class="col-lg-3 col-md-3 col-sm-3">
							
								<%String[] tempFileName = emp_stateform_path.split("/");
								%>
								<a href="DownloadFiles.html?filePath=<%=emp_stateform_path%>" style="text-decoration:underline;font-size:initial;">View Form</a>
								
								</div>
							<%}%>
					
					
				</div>
				
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
				
					
				<div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
						<label class="name"> Other Document:</label>
								
					</div>
				
					<%if(emp_additionalform_path != null && emp_additionalform_path != "null" && !(emp_additionalform_path.equalsIgnoreCase("null")) && !(emp_additionalform_path.equalsIgnoreCase(""))){
								String[] tempFileName = emp_additionalform_path.split("/");
								%>
						<div  class="col-lg-3 col-md-3 col-sm-3">
							<a href="DownloadFiles.html?filePath=<%=emp_additionalform_path%>" style="text-decoration:underline;font-size:initial;">View Form</a>
								
						</div>
					<%}else{%>
						<div  class="col-lg-3 col-md-3 col-sm-3">
							None
								
						</div>
					<%}%>
					
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
					
					<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-5">
					<input type="button" class="button-dang" name="Back" value="Back" onClick="history.go(-1);" />
					</div>
					</div>
				</div>
			
		</form>	
			<%}}}}}%>
	</div>
	</div>
<!--========================================================
FOOTER
=========================================================-->
  <div>
    
    
    <!-- FOOTER STARTS HERE -->
    
   <%@ include file = "../../include/FooterProcess.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>
	<script language="javascript">	
	
FillCountry(document.form.country, document.form.state,'USA' );
FillState(document.form.country, document.form.state, '<%=emp_state%>');

</script>
</body>


</html>

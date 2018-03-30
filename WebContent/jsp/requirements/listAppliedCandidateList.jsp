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
<title>Applied Candidate Details</title>
<style>
.table-row-line.header
{ 
	 display: block;
	 flex-direction:row;
    background-color: #dddddd;
    font-weight: bold;
    padding-top: 16px;
    padding-bottom: 35px;
   

}
.table-row-line {
    border-bottom: 1px solid #fcf8e3;
    border-collapse: collapse;
    margin-left: 63px;
    margin-right: 30px;
    padding-right: initial;
    padding-left: initial;

}
.table-row {
    border-bottom: 1px solid #fcf8e3;
    border-collapse: collapse;
    margin-left: 63px;
    margin-right: 30px;
    padding-right: initial;
    padding-left: initial;
	padding-top: 16px;
    padding-bottom: 35px;

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
	<script>
function filterSearchpopup(){
	//alert("hello cool");
	document.getElementById("filtersearch").style.display="block";
	document.getElementById("filterbutton").style.display="none";
}
function postDataForPagination() {
	//alert("check");
	var currentPageNo = document.getElementById("currentPageNo").value;
	strURL = "ListAppliedCanDetails.html?currentPageNo="+currentPageNo;
	window.location.href = strURL;
}
</script>
  <!--========================================================
CONTENT
=========================================================-->
	<div class="content indent">
	<div class="thumb-box14">
       
            <div class="row">			
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				
					
					<% if(pageStatus != null && pageStatus.equalsIgnoreCase("CandidateResumeList")){%>
					<h3 class="title">Candidate Resume List</h3>
					<%}else if(pageStatus != null && pageStatus.equalsIgnoreCase("AppliedCandidateList")){ %>
					<h3 class="title">Candidate Status List</h3>
					<%}%>
					<%	
						String nofPages = (String) request.getAttribute("totalNofPages");
								int pages = Integer.parseInt(nofPages);
								String currentPageNo = (String) request.getAttribute("currentPageNo");
								int totalNofPages=0;
								if(currentPageNo==null){
									totalNofPages = 1;
								}
								else{
							   totalNofPages = Integer.parseInt(currentPageNo);
								}		
								
							%>  
					
					<!-- div class="col-lg-3 col-md-3 col-sm-3" >
						<div class="col-lg-1 col-md-1 col-sm-1" >
							<a href="user.html?cmd=homeDash" style="font-family:sans-serif;font-size:large;font-weight:600;text-decoration:underline;color:#272727" >Home</a>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" ></div>
						<div class="col-lg-1 col-md-1 col-sm-1" >
							<a href="#" style="font-family:sans-serif;font-size:large;font-weight:600;text-decoration:underline;color:#272727" onClick="history.go(-1);">Back</a>
						</div>
					</div> -->
				 </div>
				 </div>
				<div class="col-lg-12 col-md-12 col-sm-12 ">
				  
				  <div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-1">
				  <label class="name form-div-6" style="color:#0072c6">Pages :</label>
					 
					
					<select name="currentPageNo" id="currentPageNo" class="form-control" onchange="postDataForPagination();">
													<%
														for(int i = 1; i <=pages; i++) {
															
															if(totalNofPages==i){
															%>
																<option selected="selected" value="<%=i%>"><%=i%></option>
															<%
															}
															else{
															%>
																<option value="<%=i%>"><%=i%></option>
															<%
															}
														}
														%>
											  </select>
					</div>
				<div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset1" id="filterbutton">
				  <div class="col-lg-5 col-md-5 col-sm-5" >
				  <label>&nbsp;</label>
						<button type="button" onclick="filterSearchpopup();"  style="width:100%;"class="button-add">Search by Candidate</button>
					</div>
				  </div>
					
				  </div>
				   <div class="col-lg-12 col-md-12 col-sm-12">
				   &nbsp;
				   </div>
				  <div id="filtersearch" style="display:none">
				<form name="CandidateStatus" id="postReq" method="post" action="#" class="formcss" onsubmit="return validate();" >

						<input type="hidden" name="viewDetails" value="viewdetails"/>
						 <div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-4" >
									<label class="name">RequirementID</label>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2">
									<input type="text" name="Search_req_id"  class="form-control" id="Search_req_id">
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12">
							   &nbsp;
							   </div>
							<div class="col-lg-12 col-md-12 col-sm-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-4" >
									<label class="name">Status</label>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2">
									<select name="Search_req_status" class="form-control" id="Search_req_status" required>
											<option value="">Select One</option>
										   <option value="Applied">Applied</option>
										  <option value="Shortlisted">Shortlisted</option>
										  <option value="Rejected">Rejected</option>
										  <option value="Rejected By Rate">Rejected By Rate</option>
										  <option value="Submitted To Client" >Submitted To Client</option>
										  <option value="Client Result Awaited">Client Result Awaited</option>
										  <option value="Accepted By Client">Accepted By Client</option>
										  <option value="Rejected By Client">Rejected By Client</option>
										  <option value="Interview Scheduled" >Interview Scheduled</option>
										  <option value="Joined">Joined</option>
								   </select>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12">
							   &nbsp;
							   </div>
							<!-- <div class="col-lg-12 col-md-12 col-sm-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-4" >
									<label class="name">Experience</label>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2">
									<input type="text" name="Search_Exp"  class="form-control" id="Search_Exp">
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12">
							   &nbsp;
							   </div> -->
							<div class="col-lg-12 col-md-12 col-sm-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-4" >
									<label class="name">Location</label>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2">
									<input type="text" name="Search_Location"  class="form-control" id="Search_Location">
								</div>
							</div>
							< <div class="col-lg-12 col-md-12 col-sm-12">
							   &nbsp;
							   </div>
							<div class="col-lg-12 col-md-12 col-sm-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-4" >
									<label class="name">Availability</label>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2">
									
									<select name="Search_Availability"  class="form-control" id="Search_Availability">
										<option value="">Select One</option>
										<option value="Part Time">Part Time</option>
										<option value="Full Time">Full Time</option>
										<option value="Hourly">Hourly</option>
									</select>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12">
							   &nbsp;
							   </div>
							<div class="col-lg-12 col-md-12 col-sm-12">
							
								   <div class="col-lg-1 col-md-1 col-sm-1 col-sm-offset-5">
									<button type="submit" class="button-add" name="Edit" value="Edit" onclick='this.form.action="SearchCandidateStatus.html";'>Search</button>
								   </div>
				   
							</div>
					</div>
				   <div class="col-lg-12 col-md-12 col-sm-12">
				   &nbsp;
				   </div>
				</form>	 </div>		
				 <div class="row">	
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-1 col-md-1 col-sm-1">
					&nbsp;
					</div>
					<div class="table-row-line wrapper header">
					<div class="col-lg-1 col-md-1 col-sm-1">
					<label class="name form-div-6" style="color:#0072c6">
							CANID
                            </label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						</div>
						
						<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							FullName
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							RequirementId
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3">
				
					<label class="name form-div-6" style="color:#0072c6">
							E-mail
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							Status
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						</div>
						
						<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							Resume
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						</div>
						
							
							
                           
					
					
					<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							ViewDetails
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					</div>
</div>					
                           
					</div>
					
					
					 <%ArrayList AppliedCandidateList=(ArrayList) request.getAttribute("allAppliedCandidateList"); 
			//System.out.println(CandidateList.size()+" list ");
			 if(AppliedCandidateList!=null && AppliedCandidateList.size()!=0){
            Iterator itr = AppliedCandidateList.iterator();
    while (itr.hasNext()) {    
        String TempList[] = (String[])itr.next();
		String CANID=TempList[27];
		 String firstName = TempList[0];
		 String lastName = TempList[1];
		 String CandidateContactNumber=TempList[3];
		 String RequirementID=TempList[25];
		 String CandidateEMail=TempList[2];
		 String CandidateStatusValue=TempList[28];
		 String UniqueId=TempList[26];
		 String CandidateResumeLoc = TempList[29];
    
    %>
    <div class="row">	
    <div class="col-lg-12 col-md-12 col-sm-12">	
	<div class="col-lg-1 col-md-1 col-sm-1">
	&nbsp;
	</div>
	<div class="table-row">
	<div class="col-lg-1 col-md-1 col-sm-1">
	<a href="ViewListAppliedCanDetailsByUniqueId.html?uniqueId=<%=UniqueId%>" ><label style="text-decoration:underline;"><%=CANID%></label></a>
	</div>
	<div class="col-lg-2 col-md-2 col-sm-2">
	<label><%=firstName%> <%=lastName%></label>
	</div>
	
	<div class="col-lg-1 col-md-1 col-sm-1">
	<a href="viewjobDetailsbyreqID.html?RID=<%=RequirementID%>" ><label style="text-decoration:underline;"><%=RequirementID%></label></a>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-3">
	<label><%=CandidateEMail%></label>
	</div>
	<div class="col-lg-1 col-md-1 col-sm-1">
	<label><%=CandidateStatusValue%></label>
	</div>
	<div class="col-lg-1 col-md-1 col-sm-1">
	<a href="ResumeDownload.html?uniqueId=<%=UniqueId%>">Download</a>
	</div>
	<div class="col-lg-1 col-md-1 col-sm-1">
	<a href="ViewListAppliedCanDetailsByUniqueId.html?uniqueId=<%=UniqueId%>">Click Here</a>
	</div>
	
	</div>
	</div>
				</div>
	<%}%>
	
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1" align="right">
						<button type="reset" class="button-dang" name="Back" value="Back" onClick="history.go(-1);">Back</button>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
					</div>
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
				</div>
						
	<%}else{%>	
					
			  <div class="row">			
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6">
						<h3>No Record Found</h3>
					</div>
				 </div>
				 </div>	
				 <%} %>	
			
				</div>
				</div>
				
<!--========================================================
FOOTER
=========================================================-->
  <div>
    
    
    <!-- FOOTER STARTS HERE -->
    
   <%@ include file = "../../include/Footer.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>

</body>
</html>

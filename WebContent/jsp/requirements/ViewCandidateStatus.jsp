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
<title>Update Candidate Status</title>
<link rel="stylesheet" href="css/contact-form.css"/>
<script src="js/tm-scripts.js"></script>
<script  src="js/ts_picker.js"></script>
<script src="js/calendar2.js" type="text/javascript"></script>
<script src="js/datetimepicker_css.js"></script>
<script src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">


$( window ).on( "load", function() {
	
	var candidateJoinedStatus = null;
	candidateJoinedStatus = $('#candidateJoinedStatus').val();//Get
	if(candidateJoinedStatus != null && candidateJoinedStatus == "1"){
    	$('#candidateStatus').attr("disabled", true); //Set
	}else{
		
	}
	});
	
$(document).ready(function(){
    $("#candidateStatus").change(function(){
       // alert("test");
	   var date ="date";
		var candidateStatus = null;
		candidateStatus = $('#candidateStatus').val();//Get
	if(candidateStatus != null && (candidateStatus == "Joined" || candidateStatus == "joined")){
		var newrow =jQuery('<div class="col-lg-12 col-md-12 col-sm-12"><div class="col-lg-4 col-md-4 col-sm-4"></div><div class="col-lg-2 col-md-2 col-sm-2"><label class="name form-div-6">Marital Status:</label></div><div class="col-lg-3 col-md-3 col-sm-3"><input type="text" name="empMaritalStatus" class="form-control" placeholder="Marital Status"/></div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12"><div class="col-lg-4 col-md-4 col-sm-4"></div><div class="col-lg-2 col-md-2 col-sm-2"><label class="name form-div-6">Employee SSN No</label></div><div class="col-lg-3 col-md-3 col-sm-3"><label><input type="text" name="empSSNNo" class="form-control" placeholder="SSN No"/> </label></div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12"><div class="col-lg-4 col-md-4 col-sm-4"></div><div class="col-lg-2 col-md-2 col-sm-2"><label class="name form-div-6">Passport No</label></div><div class="col-lg-3 col-md-3 col-sm-3"><label><input type="text" name="empPassportNo" class="form-control" placeholder="Passport No"/> </label></div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12"><div class="col-lg-4 col-md-4 col-sm-4"></div><div class="col-lg-2 col-md-2 col-sm-2"><label class="name form-div-6">Contact No</label></div><div class="col-lg-3 col-md-3 col-sm-3"><input type="text" name="empContactNo" class="form-control" placeholder="Contact No"/></div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12"><div class="col-lg-4 col-md-4 col-sm-4"></div><div class="col-lg-2 col-md-2 col-sm-2"><label class="name form-div-6">Job Title</label></div><div class="col-lg-3 col-md-3 col-sm-3"><input type="text" name="empJobTitle" class="form-control" placeholder="Job Title"/></div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12"><div class="col-lg-4 col-md-4 col-sm-4"></div><div class="col-lg-2 col-md-2 col-sm-2"><label class="name form-div-6">Department</label></div><div class="col-lg-3 col-md-3 col-sm-3"><input type="text" name="empDepartment" class="form-control" placeholder="Department"/></div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12"><div class="col-lg-4 col-md-4 col-sm-4"></div><div class="col-lg-2 col-md-2 col-sm-2"><label class="name form-div-6">Pay Group</label></div><div class="col-lg-3 col-md-3 col-sm-3"><input type="text" name="empPayGroup" class="form-control" placeholder="Pay Group"/></div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12"><div class="col-lg-4 col-md-4 col-sm-4"></div><div class="col-lg-2 col-md-2 col-sm-2"><label class="name form-div-6">Payrate Per hour</label></div><div class="col-lg-3 col-md-3 col-sm-3"><input type="text" name="empPayratePerhour" value="0" class="form-control" placeholder="Payrate Per hour"/></div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12"><div class="col-lg-4 col-md-4 col-sm-4"></div><div class="col-lg-2 col-md-2 col-sm-2"><label class="name form-div-6">Pay Period</label></div><div class="col-lg-3 col-md-3 col-sm-3"><input type="text" name="empPayPeriod" class="form-control" placeholder="Pay Period"/></div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12"><div class="col-lg-4 col-md-4 col-sm-4"></div><div class="col-lg-2 col-md-2 col-sm-2"><label class="name form-div-6">Job Location</label></div><div class="col-lg-3 col-md-3 col-sm-3"><input type="text" name="empJobLocation" class="form-control" placeholder="Job Location"/></div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12"><div class="col-lg-4 col-md-4 col-sm-4"></div><div class="col-lg-2 col-md-2 col-sm-2"><label class="name form-div-6">Joining Date</label></div><div class="col-lg-3 col-md-3 col-sm-3"><input type="text" name="empJoiningDate"  class="form-control" id="date" placeholder="yyyy-MM-dd"/> </div><div class="col-lg-1 col-md-1 col-sm-1 datecal"> <img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal(\'' + date + '\')"/></div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div>');     
		 
		jQuery('#update-field').append(newrow);
	
		return false;
	}else{
		 //alert("else");
		 //$('#update-field').hide();
		 jQuery('#update-field').empty();
	}
    });
});
</script>
<%String pageStatus = null;
pageStatus = (String)request.getAttribute("pageStatus");%>
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
       
            <div class="row" id="newrow">			
				
				
			<%String Search_req_status = (String)request.getAttribute("Search_req_status");
			String Search_req_id = (String)request.getAttribute("Search_req_id");%>		
				
				
				<div class="col-lg-12 col-md-12 col-sm-12">
					
					<%if(pageStatus != null & pageStatus.equalsIgnoreCase("read")){ %>
						<h3 class="title"> Candidate Status Details</h3>
						<%}else if(pageStatus != null & pageStatus.equalsIgnoreCase("Update")){%>
						<h3 class="title"> Update Candidate Status</h3>
						<%} %>
					
				 </div>
				 
				 <%ArrayList AppliedCandidateList=(ArrayList) request.getAttribute("AppliedCanDetailsList"); 
			//System.out.println(CandidateList.size()+" list ");
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
		 String appliedUserId = TempList[30];
		 String candidateJoinedStatus = TempList[34];
		 String employerMailID = TempList[22];
		 String txtempname=TempList[21];
		 
		 System.out.println("=====employerMailID"+employerMailID);
    
    %>
    
				
				
				<form name="updateCandidate" id="contact-form" method="post" action="UpdateCandidateStatus.html" class="formcss">
				<input type="hidden" name="uniqueId" value="<%=UniqueId%>"/>
				<input type="hidden" name="appliedUserId" value="<%=appliedUserId%>"/>
				<input type="hidden" name="candidateJoinedStatus" id="candidateJoinedStatus" value="<%=candidateJoinedStatus%>"/>
				<input type="hidden" name="firstName" id="firstName" value="<%=firstName %>"/>
				<input type="hidden" name="appliedUserId" id="appliedUserId" value="<%=appliedUserId %>"/>
				<input type="hidden" name="CandidateEMail" id="CandidateEMail" value="<%= CandidateEMail%>"/>
				<input type="hidden" name="CANID" id="CANID" value="<%=CANID %>"/>
				<input type="hidden" name="RequirementID" id="RequirementID" value="<%=RequirementID %>"/>
				<input type="hidden" name="employerMailID" id="employerMailID" value="<%=employerMailID %>"/>
				<input type="hidden" name="txtempname" id="txtempname" value="<%=txtempname %>"/>
				
				
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-4 col-md-4 col-sm-4">
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
					<label class="name form-div-6">
							Resume
                            </label>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6">
							 <a href="ResumeDownload.html?uniqueId=<%=UniqueId%>" style="text-decoration:underline;">Download</a>
						</div> 
						
				</div>
				
				 <div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-4 col-md-4 col-sm-4">
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
					<label class="name form-div-6">
							Candidate Full Name
                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=firstName%> <%=lastName%></label>
						</div> 
						
				</div>
				
				 <div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
					<div class="col-lg-4 col-md-4 col-sm-4">
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
					<label class="name form-div-6">
							Candidate Status Value

                            </label>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
						<%if(pageStatus != null & pageStatus.equalsIgnoreCase("read")){ %>
						<label><%=CandidateStatusValue%> </label>
						<%}else if(pageStatus != null & pageStatus.equalsIgnoreCase("Update")){%>
						<select name="candidateStatus" id="candidateStatus" class="form-control" class="large">
						   <option value="Applied" <%=CandidateStatusValue.equals("Applied")?"selected":""%>>Applied</option>
						  <option value="Shortlisted" <%=CandidateStatusValue.equals("Shortlisted")?"selected":""%>>Shortlisted</option>
						  <option value="Rejected" <%=CandidateStatusValue.equals("Rejected")?"selected":""%>>Rejected</option>
						  <option value="Rejected By Rate" <%=CandidateStatusValue.equals("Rejected By Rate")?"selected":""%>>Rejected By Rate</option>
						  <option value="Submitted To Client" <%=CandidateStatusValue.equals("Submitted To Client")?"selected":""%>>Submitted To Client</option>
						  <option value="Client Result Awaited" <%=CandidateStatusValue.equals("Client Result Awaited")?"selected":""%>>Client Result Awaited</option>
						  <option value="Accepted By Client" <%=CandidateStatusValue.equals("Accepted By Client")?"selected":""%>>Accepted By Client</option>
						  <option value="Rejected By Client" <%=CandidateStatusValue.equals("Rejected By Client")?"selected":""%>>Rejected By Client</option>
						  <option value="Interview Scheduled" <%=CandidateStatusValue.equals("Interview Scheduled")?"selected":""%>>Interview Scheduled</option>
						  <option value="Joined" <%=CandidateStatusValue.equals("Joined")?"selected":""%>>Joined</option>
						</select>
						<%} %>
							 
						</div> 
						
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 
				  <!--div class="col-lg-12 col-md-12 col-sm-12">
				
					<div class="col-lg-3 col-md-3 col-sm-3">
					</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
							<label class="name form-div-6" style="color:#0072c6">
							UniqueReference:

                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("UniqueReference"))%> </label>
						</div> 
						<div class="col-lg-2 col-md-2 col-sm-2">
						</div>
				</div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div-->
				
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-4 col-md-4 col-sm-4">
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
					<label class="name form-div-6">
							CANID

                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=CANID%> </label>
						</div> 
						
				</div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-4 col-md-4 col-sm-4">
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
					<label class="name form-div-6">
							Candidate Contact Number

                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=CandidateContactNumber%> </label>
						</div> 
						
				</div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-4 col-md-4 col-sm-4">
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
					<label class="name form-div-6">
							RequirementID

                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=RequirementID%> </label>
						</div> 
						
				</div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-4 col-md-4 col-sm-4">
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
					<label class="name form-div-6">
							Candidate EMail

                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=CandidateEMail%> </label>
						</div> 
						
				</div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <!--div class="col-lg-12 col-md-12 col-sm-12">
				
					<div class="col-lg-3 col-md-3 col-sm-3">
					</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
							<label class="name form-div-6" style="color:#0072c6">
							AccountManagerAssignedToValue:

                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("AccountManagerAssignedToValue"))%> </label>
						</div> 
						<div class="col-lg-2 col-md-2 col-sm-2">
						</div>
				</div-->
				 <!--div class="col-lg-12 col-md-12 col-sm-12">
				 &nbsp;
				 </div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
				
					<div class="col-lg-3 col-md-3 col-sm-3">
					</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
							<label class="name form-div-6" style="color:#0072c6">
							RecruiterAssignedToValue:

                            </label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							 <label><%=((String)request.getAttribute("RecruiterAssignedToValue"))%> </label>
						</div> 
						<div class="col-lg-2 col-md-2 col-sm-2">
						</div>
				</div-->
				<div id="update-field"></div>
				
				<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-1 col-md-1 col-sm-1 col-sm-offset-4">
							 <button type="submit" class="button-add" name="Submit" value="Submit">Submit</button>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
							<button type="reset" class="button-dang" name="cancel" value="cancel">Reset</button>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
							<button type="reset" class="button-dang" name="Back" value="Back" 
							onClick="history.go(-1)">Back</button>
							</div>
							</div>
							
								<input type="hidden" name="Search_req_status" value="Applied" >
				<input type="hidden" name="Search_req_id" value="<%=Search_req_id%>" >
				<%}%>
				</div>
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

</body>
</html>

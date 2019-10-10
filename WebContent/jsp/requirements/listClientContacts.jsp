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
<title>List Client Contacts</title>
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
function postDataForPagination() {
	//alert("check");
	var currentPageNo = document.getElementById("currentPageNo").value;
	strURL = "ListClientContacts.html?currentPageNo="+currentPageNo;
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
					
						<h3 class="title"> Client Contact List</h3>
					
						
				 </div>
				 </div>
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
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-1 col-md-1 col-sm-1">
					&nbsp;
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
						<h4 style="color:#16a086">
							<a href="initCreateClientContacts.html" style="color:#16a086;text-decoration: underline;">Add Contact</a>
						</h4>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3">
						<h4 style="color:#16a086">
							<a href="initImportXLSClientData.html" style="color:#16a086;text-decoration: underline;">Import Contacts from Excel</a>
						</h4>
					</div>
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
					<!-- <div class="col-lg-5 col-md-5 col-sm-5" align="right">
					<a href="user.html?cmd=homeDash" style="font-family:sans-serif;font-size:large;font-weight:600;text-decoration:underline;" >Home</a> &nbsp;&nbsp;
					<a href="#" style="font-family:sans-serif;font-size:large;font-weight:600;text-decoration:underline;" onClick="history.go(-1);">Back</a>
					</div> -->
				</div>
						
				
				
				 
				
				 
				<div class="row">	
				
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="table-row-line wrapper header">
					
					<div class="col-lg-1 col-md-1 col-sm-1">
					<label class="name form-div-6" style="color:#0072c6">
							ClientId
                            </label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						</div>
						
						<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							ClientName
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							Responsible Person
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							E-mail
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							Company
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						</div>
						
							
							
                           <!-- div class="col-lg-1 col-md-1 col-sm-1">
                           <label class="name form-div-6" style="color:#0072c6">
							Jobtitle
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
	</div-->
					
					
					<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							BusinessPhone
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					
					<!-- div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							ContactId
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div-->
						<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							Details
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>	
					<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							Delete
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					
					</div>    
                           
					</div>
					</div>
					
					
					 <%ArrayList allClientContactList=(ArrayList)request.getAttribute("allClientContactList"); 
			//System.out.println(CandidateList.size()+" list ");
			if(allClientContactList!=null && allClientContactList.size()!=0){
            Iterator itr = allClientContactList.iterator();
    while (itr.hasNext()) {    
        String TempList[] = (String[])itr.next();
         String unique_id =TempList[0];
		 String client_id=TempList[1];
		 String client_name = TempList[2];
		 String responsible_person=TempList[3];
		 String client_email=TempList[4];
		 String client_company=TempList[5];
		 //String client_jobTitle=TempList[6];
		 String client_businessPhone=TempList[6];
		 //String client_contactId=TempList[8];
		
    
    %>
    
    <div class="row">	
    <div class="col-lg-12 col-md-12 col-sm-12">	
	

	<div class="table-row">
	
	<div class="col-lg-1 col-md-1 col-sm-1">
	<label><%=client_id%></label>
	</div>
	<div class="col-lg-1 col-md-1 col-sm-1">
	<label><%=client_name%></label>
	</div>
	
	<div class="col-lg-2 col-md-2 col-sm-2">
	<label><%=responsible_person%></label>
	</div>
	<div class="col-lg-2 col-md-2 col-sm-2">
	<label><%=client_email%></label>
	</div>
	<div class="col-lg-2 col-md-2 col-sm-2">
	<label><%=client_company%></label>
	</div>
	
	<div class="col-lg-1 col-md-1 col-sm-1">
	<label><%=client_businessPhone%></label>
	</div>
	
	<div class="col-lg-1 col-md-1 col-sm-1">
	<a href="ViewClientContactsByUniqueId.html?uniqueId=<%=unique_id%>" style="text-decoration: underline;">Click Here</a>
	</div>
	 <div class="col-lg-1 col-md-1 col-sm-1">
	<a href="DeleteClientContactsByUniqueId.html?uniqueId=<%=unique_id%>" style="text-decoration: underline;" onclick="return confirm('Do you want to permanently delete this record?');" >Delete</a>
	</div>
	</div>
	
	</div>
	</div>
	
	<%}%>	
	<div class="col-lg-12 col-md-12 col-sm-12">
	&nbsp;
	</div>
				<!--<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1" align="right">
						<button type="reset" class="btn-default btn3" name="Back" value="Back" onClick="history.go(-1);">Back</button>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
					</div>
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
				</div>	-->	
			
<%}else{%>	
				<div class="col-lg-12 col-md-12 col-sm-12">
	&nbsp;
	</div>	
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
    
   <%@ include file = "../../include/FooterProcess.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>
    
</body>
</html>

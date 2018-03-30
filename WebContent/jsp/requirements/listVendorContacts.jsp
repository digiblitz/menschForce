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
<title>List Vendor Contacts</title>
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
	strURL = "ListVendrContacts.html?currentPageNo="+currentPageNo;
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
					
						<h3 class="title"> Client Vendor List</h3>
					
						
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
							<a href="initCreateVendrContacts.html" style="color:#16a086;text-decoration: underline;">Add Contact</a>
						</h4>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3">
						<h4 style="color:#16a086">
							<a href="initImportXLSVendrData.html" style="color:#16a086;text-decoration: underline;">Import Contacts from Excel</a>
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
				</div>
			
			
				 
				
				 
				</div>
            <div class="row">			
								 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					
					
					<div class="table-row-line wrapper header">
					
						<div class="col-lg-1 col-md-1 col-sm-1">
							<label class="name form-div-6 " style="color:#0072c6">
									Client Id
							 </label>
								
						</div>
						
						<div class="col-lg-1 col-md-1 col-sm-1">
				
							<label class="name form-div-6 " style="color:#0072c6">
									First Name
									</label>
									<div class="col-lg-12 col-md-12 col-sm-12">	
									&nbsp;
								</div>        
						</div>
						
						<div class="col-lg-1 col-md-1 col-sm-1">
				
							<label class="name form-div-6"  style="color:#0072c6">
									Last Name
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
						
                           <div class="col-lg-1 col-md-1 col-sm-1">
						
							<label class="name form-div-6" style="color:#0072c6">
									Company
									</label>
									<div class="col-lg-12 col-md-12 col-sm-12">	
									&nbsp;
								</div>
						</div>
						
							
							
                           <div class="col-lg-1 col-md-1 col-sm-1">
								   <label class="name form-div-6" style="color:#0072c6">
									Cell Phone
									</label>
									<div class="col-lg-12 col-md-12 col-sm-12">	
									&nbsp;
								</div>
							</div>
						
					
							<div class="col-lg-1 col-md-1 col-sm-1">
				
								<label class="name form-div-6" style="color:#0072c6">
										Business Phone
										</label>
										<div class="col-lg-12 col-md-12 col-sm-12">	
										&nbsp;
									</div>
							</div>
					
							<div class="col-lg-1 col-md-1 col-sm-1">
				
								<label class="name form-div-6" style="color:#0072c6">
										Contact Person
										</label>
										<div class="col-lg-12 col-md-12 col-sm-12">	
										&nbsp;
									</div>
							</div>
					
							
							<div class="col-lg-1 col-md-1 col-sm-1">
				
								<label class="name form-div-6" style="color:#0072c6">
										View Details
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
					
					 <%ArrayList allVendorContactList=(ArrayList)request.getAttribute("allVendorContactList"); 
			//System.out.println(CandidateList.size()+" list ");
			if(allVendorContactList!=null && allVendorContactList.size()!=0){
            Iterator itr = allVendorContactList.iterator();
    while (itr.hasNext()) {    
        String TempList[] = (String[])itr.next();
         String unique_id =TempList[0];
		 String vendor_id=TempList[1];
		 String vendor_fname = TempList[2];
		 String vendor_lname=TempList[3];
		 String vendor_email=TempList[4];
		 String vendor_company=TempList[5];
		 String vendor_homePhone=TempList[6];
		 String vendor_businessPhone=TempList[7];
		 String vendor_contactPerson=TempList[8];
		
    
    %>
	<div class="row">		
		<div class="col-lg-12 col-md-12 col-sm-12">	
	

	
		<div class="table-row">
			<div class="col-lg-1 col-md-1 col-sm-1">
				<label><%=vendor_id%></label>
			</div>
			<div class="col-lg-1 col-md-1 col-sm-1">
				<label><%=vendor_fname%></label>
			</div>
	
			<div class="col-lg-1 col-md-1 col-sm-1">
				<label><%=vendor_lname%></label>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-2">
				<label><%=vendor_email%></label>
			</div>
			<div class="col-lg-1 col-md-1 col-sm-1">
				<label><%=vendor_company%></label>
			</div>
			<div class="col-lg-1 col-md-1 col-sm-1">
				<label><%=vendor_homePhone%></label>
			</div>	
			<div class="col-lg-1 col-md-1 col-sm-1">
				<label><%=vendor_businessPhone%></label>
			</div>
			<div class="col-lg-1 col-md-1 col-sm-1">
				<label><%=vendor_contactPerson%></label>
			</div>
	
			<div class="col-lg-1 col-md-1 col-sm-1">
				<a href="ViewVendrContactsByUniqueId.html?uniqueId=<%=unique_id%>" style="text-decoration: underline;">Click Here</a>
			</div>
			<div class="col-lg-1 col-md-1 col-sm-1">
	<a href="DeleteVendorContactsByUniqueId.html?uniqueId=<%=unique_id%>" onclick="return confirm('Do you want to permanently delete this record?');"s style="text-decoration: underline;">Delete</a>
	</div>
	 
		</div>
						
	
	</div>
	</div>
	<%}%>	
	
			<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
			</div>
		<!--  <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1" align="right">
						<button type="reset" class="btn-default btn3" name="Back" value="Back" onClick="history.go(-1);">Back</button>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
					</div>
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
				</div>		-->
			
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
    
   <%@ include file = "../../include/FooterProcess.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>
    
</body>
</html>

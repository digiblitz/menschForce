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
<title>Employee List</title>
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

  <!--========================================================
CONTENT
=========================================================-->
	<div class="content indent">
	<div class="thumb-box14">
       
            <div class="row">			
				
			<div class="col-lg-12 col-md-12 col-sm-12">
				<h3 class="title">Partner List</h3>
					
					<!--<div class="col-lg-1 col-md-1 col-sm-1" >
					<a href="#" style="font-family:sans-serif;font-size:large;font-weight:600;text-decoration:underline;" onClick="history.go(-1);">Back</a>
					</div>-->
				 </div>
				 </div>
				 
				 <div class="row">	
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-1 col-md-1 col-sm-1">
					&nbsp;
					</div>
					<div class="table-row-line wrapper header">
					
				<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							Name
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					
					<div class="col-lg-2 col-md-2 col-sm-2">
					<label class="name form-div-6" style="color:#0072c6">
						EmailId
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
					
					<div class="col-lg-2 col-md-2 col-sm-2">
					<label class="name form-div-6" style="color:#0072c6">
							BusinessType
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					
						
						<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							Website
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
						
						
						
					</div>
</div>					
                           
					</div>
					
					
					 <%ArrayList PartnerList=(ArrayList) request.getAttribute("partnerList"); 
			//System.out.println(CandidateList.size()+" list ");
			 if(PartnerList!=null && PartnerList.size()!=0){
            Iterator itr = PartnerList.iterator();
    while (itr.hasNext()) {    
        String TempList[] = (String[])itr.next();
		String prov_firstname=TempList[0];
		String prov_lastname = TempList[1];
		 String prov_emailid = TempList[2];
		 String prov_company = TempList[3];
		 String prov_designation=TempList[4];
		 String prov_compweburl=TempList[11];
		 String prov_businesstype=TempList[12];
		 String provisional_partner_id=TempList[19];
		
    
    %>
	
    <div class="row">	
    <div class="col-lg-12 col-md-12 col-sm-12">	
	<div class="col-lg-1 col-md-1 col-sm-1">
	&nbsp;
	</div>
	<div class="table-row">
	
	
	
	<div class="col-lg-2 col-md-2 col-sm-2">
	<label><%=prov_firstname%> <%=prov_lastname%></label>
	</div>
	
	
	<div class="col-lg-2 col-md-2 col-sm-2">
	<label><%=prov_emailid%></label>
	</div>
	<div class="col-lg-2 col-md-2 col-sm-2">
	<label><%=prov_company%></label>
	</div>
	
	<div class="col-lg-2 col-md-2 col-sm-2">
	<label><%=prov_businesstype%></label>
	</div>
	
	<div class="col-lg-2 col-md-2 col-sm-2">
	<label><%=prov_compweburl%></label>
	</div>
	
	<div class="col-lg-1 col-md-1 col-sm-1">
	<a href="ViewListPartnerDetailsByPartnerId.html?partnerId=<%=provisional_partner_id%>">ViewDetails</a>
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

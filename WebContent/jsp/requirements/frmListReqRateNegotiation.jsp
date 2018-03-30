<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Rate Negotiation</title>
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
				
					<h3 class="title">Rate Negotiation List</h3>
					
					<!--<div class="col-lg-3 col-md-3 col-sm-3" >
						<div class="col-lg-1 col-md-1 col-sm-1" >
							<a href="user.html?cmd=homeDash" style="font-family:sans-serif;font-size:large;font-weight:600;text-decoration:underline;color:#272727" >Home</a>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" ></div>
						<div class="col-lg-1 col-md-1 col-sm-1" >
							<a href="#" style="font-family:sans-serif;font-size:large;font-weight:600;text-decoration:underline;color:#272727" onClick="history.go(-1);">Back</a>
						</div>
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
							Requirement Id
                            </label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						</div>
						
						<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							Negotiate Rate
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
					<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							Negotiated By
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							Negotiated Company
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
					
					 <%ArrayList listReqRateNegotiation=(ArrayList)request.getAttribute("listReqRateNegotiation"); 
			//System.out.println(CandidateList.size()+" list ");
			if(listReqRateNegotiation!=null && listReqRateNegotiation.size()!=0){
            Iterator itr = listReqRateNegotiation.iterator();
    while (itr.hasNext()) {    
        String TempList[] = (String[])itr.next();
        String unique_id = TempList[0];
     	String postReqByUniqueId = TempList[1];
 		String negotiateRate = TempList[2];
 		String negotiateReason = TempList[3];
 		String negotiateStatus = TempList[4];
 		String uniqueRateNegotiateCompanyId = TempList[5];
 		String rateNegotiateCompanyName = TempList[6];
 		String rateNegotiateCompanyCategory = TempList[7];
 		String rateNegotiateCompanyId = TempList[8];
 		String rateNegotiationUserId = TempList[9];
 		String rateNegotiateUserName = TempList[10];
 		String rateNegotiateUserRoleId = TempList[11];
 		String rateNegotiateUserRoleName = TempList[12];
 		String postReqId = TempList[13];
		
    
    %>
	<div class="row">		
    <div class="col-lg-12 col-md-12 col-sm-12">	
	

	<div class="col-lg-1 col-md-1 col-sm-1">
	&nbsp;
	</div>
	
	<div class="table-row">
	<div class="col-lg-2 col-md-2 col-sm-2">
	<label><a href="ViewPostRequirementByUniquePostId.html?uniqueId=<%=postReqByUniqueId%>"><%=postReqId%></a></label>
	</div>
	<div class="col-lg-2 col-md-2 col-sm-2">
	<label><%=negotiateRate%></label>
	</div>
	
	<div class="col-lg-1 col-md-1 col-sm-1">
	<label><%=negotiateStatus%></label>
	</div>
	<div class="col-lg-2 col-md-2 col-sm-2">
	<label><%=rateNegotiateUserName%> - <%=rateNegotiateUserRoleName%></label>
	</div>
	<div class="col-lg-2 col-md-2 col-sm-2">
	<label><%=rateNegotiateCompanyCategory%> - <%=rateNegotiateCompanyName%></label>
	</div>
	<div class="col-lg-1 col-md-1 col-sm-1">
	<a href="ViewRateNegotiation.html?uniqueId=<%=unique_id%>" style="color:#16a086;text-decoration: underline;">Click Here</a>
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

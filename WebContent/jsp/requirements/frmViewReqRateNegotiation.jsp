<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>View Rate Negotiation</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

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
        <div class="container">
            <div class="row">
			
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
					<div class="col-lg-7 col-md-7 col-sm-7">
                   <h3></h3>
				   </div>
				 </div>
				 
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6">
						<h3>View Rate Negotiation</h3>
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
		 
		<form name="clientContact" id="postReq" method="post" action="#" class="formcss" onsubmit="return validate();" >
		
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>

						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
								Requirement Id
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=postReqId%>
							
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
									Negotiate Rate
									</label>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-6">
									<%=negotiateRate%>
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
									Negotiate Reason
									</label>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-6">
									<%=negotiateReason%>
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
									Status
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=negotiateStatus%>
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
									Negotiated By
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=rateNegotiateUserName%> - <%=rateNegotiateUserRoleName%>
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
									Negotiated Company
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=rateNegotiateCompanyCategory%> - <%=rateNegotiateCompanyName%>
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
				
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-6 col-md-6 col-sm-6">
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							 <button type="reset" class="btn-default btn3" name="Back" value="Back" onClick="history.go(-1);">Back</button>
							</div>
						</div>
        		</form>
				 <%
                                              }
					 }
					  else {%>
					  
                      <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-5 col-md-5 col-sm-5">
							</div>
							<div class="col-lg-7 col-md-7 col-sm-7">
								No Records are Found
							</div>
						</div>
                     
                      <% } %>
				
			</div>
		</div>
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


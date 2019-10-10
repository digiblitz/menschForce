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
<title>Insert title here</title>
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
				<div class="col-lg-1 col-md-1 col-sm-1">
				</div>
					<div class="col-lg-5 col-md-5 col-sm-5">
					<h3>Candidate Status List</h3>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3">
					&nbsp;	
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3" >
						<div class="col-lg-1 col-md-1 col-sm-1" >
							<a href="user.html?cmd=homeDash" style="font-family:sans-serif;font-size:large;font-weight:600;text-decoration:underline;color:#272727" >Home</a>
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1" ></div>
						<div class="col-lg-1 col-md-1 col-sm-1" >
							<a href="#" style="font-family:sans-serif;font-size:large;font-weight:600;text-decoration:underline;color:#272727" onClick="history.go(-1);">Back</a>
						</div>
					</div>
				 </div>	
				 
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
							Full Name
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
				
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
					<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							Status
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
					
					
					 <%ArrayList CandidateList=(ArrayList) request.getAttribute("CandidateList"); 
			//System.out.println(CandidateList.size()+" list ");
            Iterator itr = CandidateList.iterator();
    while (itr.hasNext()) {    
        String TempList[] = (String[])itr.next();
		String CANID=TempList[0];
		 String CandidateFullName = TempList[1];
		 String CandidateContactNumber=TempList[2];
		 String RequirementID=TempList[3];
		 String CandidateEMail=TempList[4];
		 String CandidateStatusValue=TempList[5];
		 String SubmittedById=TempList[6];
		 String UniqueReference=TempList[7];
		 String Id_ref=TempList[7];
    
    %>
	<div class="row">	
    <div class="col-lg-12 col-md-12 col-sm-12">	
	<div class="col-lg-1 col-md-1 col-sm-1">
	&nbsp;
	</div>
	<div class="table-row">
	<div class="col-lg-1 col-md-1 col-sm-1">
	<label><%=CANID%></label>
	</div>
	<div class="col-lg-2 col-md-2 col-sm-2">
	<label><%=CandidateFullName%></label>
	</div>
	
	<div class="col-lg-2 col-md-2 col-sm-2">
	<label><%=RequirementID%></label>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-3">
	<label><%=CandidateEMail%></label>
	</div>
	<div class="col-lg-2 col-md-2 col-sm-2">
	<label><%=CandidateStatusValue%></label>
	</div>
	<div class="col-lg-1 col-md-1 col-sm-1">
	<a href="resumeSearch.html?cmd=getCandidateStatusByid&id=<%=Id_ref%>">Click Here</a>
	</div>
	</div>
	</div>
	</div>
	<%}%>	
					
		 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-5">
							<button type="reset" class="btn-default btn3" name="Back" value="Back" onClick="history.go(-1);">Back</button>
							</div>
						</div>			
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

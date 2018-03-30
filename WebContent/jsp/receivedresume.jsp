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
<title> JobSeeker Resume Details</title>
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
  <%@ include file = "../../../include/menschForceHeader.jsp" %>
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
					<div class="col-lg-4 col-md-4 col-sm-4">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6">
						<h3>Received Resume</h3>
					</div>
				 </div>
				 </div>
				 <div class="row">
						    <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="table-row-line wrapper header">
					<div class="col-lg-2 col-md-2 col-sm-2">
					<label class="name form-div-6" style="color:#0072c6">
							DocumentName:
                            </label>
							
							
				</div>
				
				
					<div class="col-lg-2 col-md-2 col-sm-2">
					<label class="name form-div-6" style="color:#0072c6">
							RequirementID:
                            </label>
							
							
				</div>
				
					<div class="col-lg-2 col-md-2 col-sm-2">
					<label class="name form-div-6" style="color:#0072c6">
							FirstName:
                            </label>
							
							
				</div>
				<div class="col-lg-3 col-md-3 col-sm-3">
					<label class="name form-div-6" style="color:#0072c6">
							CandidateE-Mail:
                            </label>
							
							
				</div>
				
				<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							ViewDetails
							</label>
							
						</div>
						</div>
					</div>
					</div>
				
				 <% ArrayList receivedlist=(ArrayList) request.getAttribute("receivedlist"); 
			System.out.println(receivedlist.size()+" receivedlist ");
            Iterator itr = receivedlist.iterator();
    while (itr.hasNext()) {    
        String temps[] = (String[])itr.next();
		String name=temps[0];
		String reqid=temps[1];
		String Fname=temps[2];
		String Can_mail=temps[3];
		String id=temps[4];
		
			%>
			
			
			<div class="row">	
			<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="table-row">
					<div class="col-lg-2 col-md-2 col-sm-2">
					<label class="name form-div-6" >
							<label><%=name%></label>
                            </label>
							
							
				</div>
				
				
					<div class="col-lg-2 col-md-2 col-sm-2">
					<label class="name form-div-6" >
							<label><%=reqid%></label>
                            </label>
							
							
				</div>
				
					<div class="col-lg-2 col-md-2 col-sm-2">
					<label class="name form-div-6">
							<label><%=Fname%></label>
                            </label>
							
							
				</div>
				<div class="col-lg-3 col-md-3 col-sm-3">
					<label class="name form-div-6" >
							<label><%=Can_mail%></label>
                            </label>
							
							
				</div>
				
				<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6">
							<a href="resumeSearch.html?cmd=getDetailsByid&id=<%=id%>">View Details</a>
							
							</label>
							
						</div>
					</div>
	<%}%>
						
						
				
				
				</div>
				</div>
				</div>
				</div>

				
				
				
				
	
							
							
	<!--========================================================
FOOTER
=========================================================-->
  <div>
    
    
    <!-- FOOTER STARTS HERE -->
    
    <%@ include file = "../../../include/menschForceFooter.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>						
							
</body>
</html>

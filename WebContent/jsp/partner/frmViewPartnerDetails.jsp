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
<title>Employee Details</title>
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
						<h3>Partner Details</h3>
				   </div>
				 </div>

							<%ArrayList partnerDetails = (ArrayList) request.getAttribute("partnerDetails");
          					 if(partnerDetails!=null && partnerDetails.size()!=0){
          						Iterator itr = partnerDetails.iterator();
          					    while (itr.hasNext()) {    
          					     	String TempList[] = (String[])itr.next();
          					     	String prov_firstname = TempList[0];
	          					    String prov_lastname = TempList[1];
	          					    String prov_emailid = TempList[2];
	          					    String prov_company = TempList[3];
	          					    String prov_designation = TempList[4];
	          					    String prov_address = TempList[5];
	          					    String prov_city = TempList[6];
	          					    String prov_state = TempList[7];
	          					    String prov_zipcode = TempList[8];
	          					    String prov_country = TempList[9];
	          					    String prov_phone = TempList[10];
	          					    String prov_compweburl = TempList[11];
	          					    String prov_businesstype = TempList[12];
	          					    String prov_sector = TempList[13];
	          					    String prov_custNumb = TempList[14];
	          					    String prov_empNumb = TempList[15];
	          					    String prov_interest = TempList[16];
	          					    String prov_proposedesc = TempList[17];
	          					    String prov_prodresell = TempList[18];
	          					    String provisional_partner_id = TempList[19];
	          					  
          						%>
		 
		<form name="partnerDetails" id="partnerDetails" method="post" action="#" class="formcss" onsubmit="return validate();" >
		
		
		
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>

						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">
									FirstName
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=prov_firstname%>							
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
									LastName
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=prov_lastname%>							
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
									Email
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=prov_emailid%>							
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
									Company
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=prov_company%>							
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
									Designation
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=prov_designation%>							
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
									Address
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=prov_address%>							
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
									City
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=prov_city%>							
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
									State
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=prov_state%>							
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
									Country
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=prov_country%>							
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
									Postal Code
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=prov_zipcode%>							
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
									PhoneNumber
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=prov_phone%>							
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
									Website
								</label>
								</label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							  <%=prov_compweburl%>							
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
									Business Type
									
								</label>
								
							</div>
							
							<div class="col-lg-6 col-md-6 col-sm-6">
							<%=prov_businesstype%>						
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
									Sector Type
									
								</label>
								
							</div>
							
							<div class="col-lg-6 col-md-6 col-sm-6">
							<%=prov_sector%>						
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
									Number of Customers
									
								</label>
								</label>
							</div>
							
							<div class="col-lg-6 col-md-6 col-sm-6">
							<%=prov_custNumb%>						
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
									Number of Employees
									
								</label>
								
							</div>
							
							<div class="col-lg-6 col-md-6 col-sm-6">
							<%=prov_empNumb%>						
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
									 What is driving your interest in digiBlitz?
									
								</label>
							</div>
							
							<div class="col-lg-6 col-md-6 col-sm-6">
							<%=prov_interest%>				
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
									where you propose to see digiBlitz products
									
								</label>
							</div>
							
							<div class="col-lg-6 col-md-6 col-sm-6">
							<%=prov_proposedesc%>				
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
									what digiBlitz products are you interested in reselling?
									
								</label>
							</div>
							
							<div class="col-lg-6 col-md-6 col-sm-6">
							<%=prov_prodresell%>				
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


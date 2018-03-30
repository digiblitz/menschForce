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
<title>Edit Client Contacts</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="css/contact-form.css">
<style>
#contact-form label.error{
 color:red;
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
  <script src="js/jquery.validate.js"></script>
<script src="js/additional-methods.js"></script>
<script src="js/fieldvalidation.js"></script>


<!--========================================================
CONTENT
=========================================================-->
 
<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">
			
				<div class="col-lg-12 col-md-12 col-sm-12">
					
                   <h3 class="title">Edit Client Contact</h3>
				  
				 </div>
				 
				 
				 <%
						 ArrayList ClientContactList = (ArrayList) request.getAttribute("ClientContactList");
          					 if(ClientContactList!=null && ClientContactList.size()!=0){
          						Iterator itr = ClientContactList.iterator();
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
	
		 
		<form name="EditClientContact" id="contact-form" method="post" action="UpdateClientContacts.html" class="formcss" onsubmit="return validate();" >
		<input type="hidden" name="unique_id" value="<%=unique_id%>"></input>
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>

						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">Client Id</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  <input type="text" name="client_id" id="client_id" class="form-control" value="<%=client_id%>" />
							
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div><label class="name form-div-6">
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>

						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">Name <span class="form-validation" style="color:Red;">*</span></label>
							
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  <input type="text" name="client_name" id="client_name" class="form-control" placeholder="Client Name" value="<%=client_name%>" />
							
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>

						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">Responsible Person</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  <input type="text" name="responsible_person" id="responsible_person" class="form-control" value="<%=responsible_person%>"  placeholder="Responsible Person" />
							
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">E-Mail <span class="form-validation" style="color:Red;">*</span></label>
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  <input type="text" name="client_email" id="client_email" class="form-control" value="<%=client_email%>" placeholder="Client E-Mail" />
							
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">Company Name <span class="form-validation" style="color:Red;">*</span></label>
							
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							 <input type="text" name="client_company" id="client_company" class="form-control" value="<%=client_company%>" placeholder="Client Company Name" />
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<!-- div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">Job Title</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input type="text" name="client_jobTitle" class="form-control" value="" placeholder="Client Job Title"/>
							 
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div> -->
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<label class="name form-div-6">Business Phone</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							 <input type="text" name="client_businessPhone" value="<%=client_businessPhone%>" class="form-control" placeholder="Client Business Phone" onkeypress="return isNumber(event)"/>
							
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<!-- div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-3 col-md-3 col-sm-3">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">Contact Id</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							 <input type="text" name="client_contactId" value="" class="form-control" placeholder="Client ContactId"/>
							
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div> -->
						
										
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-5">
							 <button type="submit" class="button-add" name="clientcontact" id="clientcontact" value="Submit">Submit</button>
							<% } %>


							</div>
							</div>
        		</form>
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
    
   <%@ include file = "../../include/FooterProcess.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>

 
</body>
</html>


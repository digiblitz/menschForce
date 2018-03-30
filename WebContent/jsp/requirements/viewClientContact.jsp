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
<title>Client Contact</title>
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
				
                   <h3 class="title">View Client Contact</h3>
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
		 
		<form name="clientContact" id="postReq" method="post" action="#" class="formcss" onsubmit="return validate();" >
		
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>

						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-4">
								<label class="name form-div-6">
								Client Id
								</label>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							  <%=client_id%>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
							<div class="col-lg-12 col-md-12 col-sm-12">
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-4">
									<label class="name form-div-6">
										Client Name
									</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4">
									<%=client_name%>
								</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-4">
								<label class="name form-div-6">
									Responsible Person
								</label>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							  <%=responsible_person%>
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-4">
								<label class="name form-div-6">
									Client E-Mail
								</label>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							  <%=client_email%>
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>

						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-4">
								<label class="name form-div-6">
									Client Company
								</label>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							  <%=client_company%>
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-4">
								<label class="name form-div-6">
									Client Business Phone
								</label>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							  <%=client_businessPhone%>							
							</div>							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						
				
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-5">
							 <button type="reset" class="button-dang" name="Back" value="Back" 
							 onClick="window.location.href='ListClientContacts.html'">Back</button>
							 <button type="submit" class="button-edit" name="Edit" value="Edit" onClick='this.form.action="EditClientContactsByUniqueId.html?uniqueId=<%=unique_id%>";'>Edit</button>
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
    
   <%@ include file = "../../include/FooterProcess.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>

 
</body>
</html>


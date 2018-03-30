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
<title>Import Client Contacts</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<style>
#importClient label.error{
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
					
                   <h3 class="title">Import the Client Contacts</h3>
				 
				 </div>
				 
			
		 
		<form name="frmimportClient" id="importClient" method="post" action="importXLSDataToClientContact.html" class="formcss" onsubmit="return validate();" enctype="multipart/form-data">
							
							 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							
							<div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-3" style="Color:red">
							  <label>Note:upload xlsx and xls format only</label>
							
							</div>
							
						</div>
		
		
		  									
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>

						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">Upload File</label>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							  <input type="file" name="file" id="file" class="form-control" />
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-1 col-md-1 col-sm-1 col-sm-offset-4">
							 <button type="submit" class="button-add" name="Submit" value="Submit">Submit</button>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
							<button type="reset" class="button-dang" name="cancel" value="cancel">Cancel</button>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
							<button type="reset" class="button-dang" name="Back" value="Back" onClick="history.go(-1);">Back</button>
							</div>
						</div>
							 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-5 col-md-5 col-sm-5">
							</div>
							<div class="col-lg-7 col-md-7 col-sm-7">
						   <h3></h3>
						   </div>
						 </div>
        		</form>
				
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


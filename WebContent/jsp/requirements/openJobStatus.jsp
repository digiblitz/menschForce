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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
 <!--CSS-->
<link rel="stylesheet" href="css/bootstrap.css" >
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/camera.css">
<link rel="stylesheet" href="css/jquery.fancybox.css">
<link rel="stylesheet" href="fonts/font-awesome.css">


<!--JS-->
<script src="js/jquery.js"></script>
<script src="js/jquery-migrate-1.2.1.min.js"></script>
<script src="js/jquery.easing.1.3.js"></script>
<script src="js/jquery.mobilemenu.js"></script>
<script src="js/jquery.equalheights.js"></script> 
<script src="js/camera.js"></script>
<!--[if (gt IE 9)|!(IE)]><!-->
<script src="js/jquery.mobile.customized.min.js"></script>
<!--<![endif]-->
<script src="js/jquery.fancybox.pack.js"></script>
<script src="js/jquery.carouFredSel-6.1.0-packed.js"></script>
<script src="js/jquery.touchSwipe.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/tm-scripts.js"></script>
<!--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->

<title>menschForce</title>
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
	
<!--=======content================================-->

<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-4 col-md-4 col-sm-4">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6">
                   <h3>Post Requirements: Listings</h3>
				   </div>
				 </div>
				 
				
				 
				  
					
					 <div class="col-lg-12 col-md-12 col-sm-12">
					 <div class="col-lg-2 col-md-2 col-sm-2">
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							
							<a href="CreatePostRequirements.html" style="font-family: sans-serif;
    font-size: large;
    font-weight: 600;
    text-decoration: underline;">Post New Requirement</a>
							</div>
						</div>

						<div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
						</div>
	 

                    <form name="frmRoleMgtListRole" id="frmRoleMgtListRole" action="roles.do" >
                      <input type="hidden" name="roleProcess" value="initeditRole" />
					  
					   
                      
                      
                      <%
						 ArrayList PostReqList = (ArrayList) request.getAttribute("allPostReqList");
          					 if(PostReqList!=null && PostReqList.size()!=0){
							Iterator it = PostReqList.iterator();
							while(it.hasNext()){
								String[] s = (String[])it.next();
								String postReqUniqueId = s[0];
								String ClientIndustry = s[5];
								String Position = s[7];
								String Skills = s[10];
								String LocationField = s[12];
								String Salary = s[21];
								String RequiredExperience = s[26];
								String jobPostDate = s[29];
								String JobTitle = s[30];
							

		                %>
						
                      <input type="hidden" name="postReqUniqueId" value="<%=postReqUniqueId%>" />
					  
					   <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
							
							
							<div class="col-lg-8 col-md-8 col-sm-8" style="border-color: #0092d6;
    border-style: groove;
    border-width: thin;">
							<a href="ViewPostRequirementByUniquePostId.html?uniqueId=<%=postReqUniqueId%>" style="">
								<span class="jobtitle" style="display:block;font-size:14px;"><%=JobTitle%></span>							
								<span class="client" style="display:block;padding-right: 110px;font-weight:300;"><%=ClientIndustry%></span>
								<span class="exp" style="float:left;width:25%;padding-right:10px;font-weight: 300;">
								<img src="images/exp_icon.png"  width="20px" height="20px" style="padding-top: 0px">
								<%=RequiredExperience%></span>
								<span class="location" style="float:left;width:75%;word-wrap:break-word;">
								<img src="img/page1_icon1.png" alt="" style="background-color:#666;">
								<%=LocationField%></span>
								<div class="more" style="float: left;
    width: 100%;
    font-size: 13px;
    color: #444;
    line-height: 20px;
    font-weight: 300;
    text-align: justify;">
								<span class="postlabel" style="float:left;clear:left;
    width:15%;line-height:20px;padding-right:10px;font-weight:300;">Key Skills :</span>
								<span class="skills" style="font-size:13px;color:#444;line-height:20px;font-weight:300;text-align:justify;"><%=Skills%></span>
								</div><br>
								<div class="otherDetails"style="    background-color: #f9f9f9;
    overflow: hidden;
    clear: left;
    border-bottom: 2px solid #f9f9f9;">
	<span class="postlabel" style="border-right: 1px solid #f4f4f4;
    padding: 2px 2px;
    float: left;
    height: 30px;
    line-height: 30px;
    min-width: 25px;
    font-weight: 300;
	color: #666;">Salary </span>
								<span class="salary" style="border-right: 1px solid #f4f4f4;
    padding: 2px 2px;
    float: left;
    height: 30px;
    line-height: 30px;
    min-width: 25px;
    font-weight: 500;">
	
	<%=Salary%></span>
	<span class="postlabel" style="border-right: 1px solid #f4f4f4;
    padding: 2px 2px;
    float: left;
    height: 30px;
    line-height: 30px;
    min-width: 25px;
    font-weight: 300;
	color: #666;">Position </span>
								<span class="salary" style="border-right: 1px solid #f4f4f4;
    padding: 2px 2px;
    float: left;
    height: 30px;
    line-height: 30px;
    min-width: 25px;
    font-weight: 500;">
	
	<%=Position%></span>
	<span class="postlabel" style="border-right: 1px solid #f4f4f4;
    padding: 2px 2px;
    float: left;
    height: 30px;
    line-height: 30px;
    min-width: 25px;
    font-weight: 300;
	color: #666;">Posted On </span>
								<span class="salary" style="border-right: 1px solid #f4f4f4;
    padding: 2px 2px;
    float: left;
    height: 30px;
    line-height: 30px;
    min-width: 25px;
    font-weight: 500;">
	
	<%=jobPostDate%></span>
	
								
								</div>

</a>
							</div> 
							
							
							
							 
							
						</div>
					  
                      
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
    
  		<%@ include file = "../../include/menschForceFooter.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
  </div>  
</body>
</html>

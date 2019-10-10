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
<%@page import="com.hlccommon.util.HLCMenuListVO"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
     <head>
     <title>Home</title>
     <meta charset="utf-8">    
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
<meta name="description" content="Your description">
<meta name="keywords" content="Your keywords">
<meta name="author" content="Your name">
<meta name = "format-detection" content = "telephone=no" />
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
    
   
       <script>
    $(document).ready(function(){
        jQuery('.camera_wrap').camera();
    });
</script>
<script>
	$(window).load(function() {
		$(function() {
            $('.foo1').carouFredSel({
				auto: false,
				responsive: true,
				width: '100%',
				scroll: 1,
                prev: '.prev1',
				next: '.next1',
				items: {
					height: 'auto',
					width:200,
					visible: {
						min: 1,
						max: 6
					}
				},
				mousewheel: true,
				swipe: {
					onMouse: true,
					onTouch: true
				}
			});
            $('#foo2').carouFredSel({
				auto: false,
				responsive: true,
				width: '100%',
				scroll: 1,
                pagination  : "#foo2_pag",
				items: {
					height: 'auto',
					width:300,
					visible: {
						min: 1,
						max: 1
					}
				},
				mousewheel: true,
				swipe: {
					onMouse: true,
					onTouch: true
				}
			});
		}); 	 				
    });
</script>
		<style type="text/css">
		.onclick-menu {
    position: relative;
    display: inline-block;
}
.onclick-menu:before {
    content: "click to save!";
}
.onclick-menu:focus .onclick-menu-content {
    display: block;
}
.onclick-menu-content {
    position: absolute;
    z-index: 1;

    display: none;
}
		</style>
       

<script language="javascript">
	
	  function noBack() {  
		 
		window.history.forward(); }

    noBack();
    window.onload = noBack;
    window.onpageshow = function (evt) { if (evt.persisted) noBack(); }
    window.onunload = function () { void (0); }
  </script>

     </head>
<body>

<header id="header">

    <div class="container">  
        <h1 class="navbar-brand navbar-brand_ wow fadeInLeft"><a href="index.html"><img src="img/digiBlitz  _MenschForce_Updated_New.png" alt="logo"></a></h1>     
        
        <nav class="navbar navbar-default navbar-static-top tm_navbar clearfix" role="navigation">
            <ul class="nav sf-menu clearfix">
					 <li><a href="#">My Profile</a><ul>
					<li><a href="user.html?cmd=edit">Edit Profile</a></li>
					<li><a href="user.html?cmd=initchgPwd">Change Password</a></li>
					<!--li><a href="ExterApp.html?process=processList">Process List</a></li-->
					</ul></li>
					
					
					<%
						ArrayList headEntityList = (ArrayList)session.getAttribute("privList");
						if(headEntityList!=null && headEntityList.size()!=0){
							Iterator itEntList = headEntityList.iterator();
							
							while(itEntList.hasNext()){
							
								String strRolelist[]= (String[])itEntList.next();
								String heRoleId = strRolelist[1];
								String heRoleName = strRolelist[3];
								String heEntityId = strRolelist[2];
								String heEntityName = strRolelist[4];
								String heEntityUrl = strRolelist[5];
								String privName= strRolelist[6];
					            String priviId = strRolelist[7];
					            
								if(heEntityUrl==null){	
								//System.out.println(heEntityName);
							%>
                        <li><a href="frmHome.jsp"><%=privName%></a>
                            <ul>
							
							<%
						ArrayList headAllList = (ArrayList)session.getAttribute("DBAllList");
						if(headAllList!=null && headAllList.size()!=0){
							Iterator itAllList = headAllList.iterator();
							
							while(itAllList.hasNext()){
							
								String strAlllist[]= (String[])itAllList.next();
								
								String allEntityId = strAlllist[2];
								String allEntityName = strAlllist[4];
								
								String hePrivName = strAlllist[6];
								String hePrivId = strAlllist[7];
								String accessName = strAlllist[8];
								String accessUrl = strAlllist[9];
								
								//System.out.println("accessUrl in jsp::"+accessUrl);
								
								String tempAccesURL=accessUrl.replaceAll("do","html");
								
									//System.out.println("tempAccesURL in jsp::"+tempAccesURL);
						if(hePrivId.equals(priviId)){	
									  			
						%>
							
                            <li><a href="<%=tempAccesURL%>"><span><%=accessName%></span></a>
															
									
								</li>

								
								
						<%
						
							} %>
						
							<%
							}}%>		
								
								
								
                           </ul>
                        </li>
						
							<%
						  	}else{%>
						  		
						  		<li><a href="<%=request.getContextPath()+heEntityUrl%>"><span><%=heEntityName%></span></a></li>
						  	<%}
						
							}} %>	
							<% String urlName1=(String)request.getAttribute("urlName");
							System.out.println("urlName1 : "+urlName1);
							
							if(urlName1!=null && (urlName1.equalsIgnoreCase("BpDesigner"))){
							%>
			
					
<!--li><a href="ExterApp.html?process=upLoadButton">UpLoad File</a></li-->
<li></li>



				<%}%>	
				
                    </ul>
						
                </nav>
				
            </div>
    </div>
  
</header>






</body>
</html>

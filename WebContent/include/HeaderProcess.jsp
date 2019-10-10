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

<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico"  />
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
<script src="js/bootstrap.min.js"></script>
<script src="js/superfish.js"></script>
<script src="js/script.js"></script>
<script src="js/sForm.js"></script> 

<%
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

 %>
 
     <!--<![endif]-->
     <!--[if lt IE 8]>
        <link rel="stylesheet" type="text/css" media="screen" href="css/ie.css">

       <div style=' clear: both; text-align:center; position: relative;'>
         <a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode">
           <img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." />
         </a>
      </div>
    <![endif]-->
    <!--[if lt IE 9]>
   		<script src="js/html5shiv.js"></script>
    	<link rel="stylesheet" type="text/css" media="screen" href="css/ie.css">
    <![endif]-->
   
      <script>
    $(window).load(function() {
        $(function() {$("a.various").fancybox();});
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



      <script src="js/wow/wow.js"></script>
<script src="js/wow/device.min.js"></script>
<script src="js/jquery.mobile.customized.min.js"></script> 

     </head>
<body id="top">

<script src="js/jquery.validate.js"></script>
<script src="js/additional-methods.js"></script>

<script>
 var name=null;
 $(document).ready(function(){
	 //alert("chek");
	   $(window).on('load', function(){
		 name = document.getElementById("userName").value; 
		 if(name=="null" || name==null){
			 //alert (name);
			 return window.location.assign("login.html");
		 }
		
	 });
 });
</script>

<!--==============================header=================================-->

<%String fstnam=(String)session.getAttribute("firstName");
String lstnam=(String)session.getAttribute("lastName");
//System.out.println("last name : "+lstnam);
String imagedata=(String)session.getAttribute("imagedata");

String roleName=(String)session.getAttribute("roleName");
String fullName=fstnam+" "+lstnam;

System.out.println("fstnam::::"+fullName);

String userId=(String)session.getAttribute("userId");
System.out.println("UserId in headerProcess : "+userId);
String userCompanyDet = (String) session.getAttribute("userCompanyCode");
String matchCompanyDet = null;
matchCompanyDet = (String) session.getAttribute("matchCompanyDet");
System.out.println("userCompanyDet:::::::::::::::::::::"+userCompanyDet);
System.out.println("matchCompanyDet:::::::::::::::::::::"+matchCompanyDet);




 

%>

<input type="hidden" name="userName" id="userName" value="<%=matchCompanyDet%>"/>

<header class="indent" style="height:134px">
<div class="">
        <div class="container"> 
           
            <ul class="follow_icon">
               <%if(!(fullName.equalsIgnoreCase("NULL NULL"))){%>	
                       
						<li><a href="user.html?cmd=homeDash"><img src="img/home (1).png" alt="" width="30px" height="25px"></a></li>
						
						
						<li class="current"><a href="#"><!-- img src="data:image/jpeg;base64,<%=imagedata %>" alt="" width="30px" height="25px"-->
							<%=fullName%></a>                   				
                            <!--ul>
                                <li><a href="user.html?cmd=edit">My profile</a></li>
                                <li><a href="user.html?cmd=initchgPwd">Change your password</a></li>                              
							</ul-->						
						</li>
						
						<li><a href="#">Current Role: <%=roleName%></a></li>
						<li><a href="logout.html?cmd=logout">Sign out</a></li><%}%>
                 
            </ul>
            </div>
            </div>
            <div class="container">  
					 <h1 style="margin-top: -24px;margin-left: -46px;visibility: visible; animation-name: fadeInLeft;" class="navbar-brand navbar-brand_ wow fadeInLeft animated"><a href="user.html?cmd=homeDash">
					  
					   <%if(matchCompanyDet != null && !matchCompanyDet.equalsIgnoreCase("null")){
					  if((matchCompanyDet != null) && matchCompanyDet.equalsIgnoreCase(userCompanyDet)){ %>
					  <img src="images/menschForce_<%=matchCompanyDet.toLowerCase()%>.png" alt="logo">
					  <%} else{%>
					  <img src="images/menschForce_digiblitz.png" alt="logo">
					  <%}}%>
					  </a></h1>
					 
					  </div></header>
					  
					  <!--<script src="js/jquery-migrate-1.2.1.min.js"></script>-->
					  
					  
					  </body></html>

<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
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
<script src="js/bootstrap.min.js"></script>
<!--<script src="js/tm-scripts.js"></script>-->
<script src="js/superfish.js"></script>
<script src="js/script.js"></script>
<script src="js/sForm.js"></script> 


 
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
			// alert (name);
			 return window.location.assign("login.html");
		 }
		
	 });
 });
</script>

<!--==============================header=================================-->

<%String fstnam=(String)session.getAttribute("firstName");
String lstnam=(String)session.getAttribute("lastName");
//System.out.println("last name : "+lstnam);

String roleName=(String)session.getAttribute("roleName");
String fullName=fstnam+" "+lstnam;

System.out.println("fstnam::::"+fullName);

String userId=(String)session.getAttribute("userIdInIndex");
System.out.println("UserId in headerProcess : "+userId);

String username=(String)session.getAttribute("userName");
String password=(String)session.getAttribute("userPassword");
String rolename=(String)session.getAttribute("roleName");
//System.out.println("UN:"+username);

 String userCompanyDet = (String) session.getAttribute("userCompanyCode");
String matchCompanyDet = (String) session.getAttribute("matchCompanyDet");
System.out.println("userCompanyDet:::::::::::::::::::::"+userCompanyDet);
System.out.println("matchCompanyDet:::::::::::::::::::::"+matchCompanyDet);
%>

<input type="hidden" name="userCode" id="userName" value="<%=matchCompanyDet%>"/>

<header class="indent">
<div class="">
        <div class="container"> 
            
            <ul class="follow_icon" >
               <%if(!(fullName.equalsIgnoreCase("NULL NULL"))){%>	
                        
						<!--li><a href="user.html?cmd=homeDash"><img src="img/home (1).png" alt="" width="30px" height="25px" style="padding-top: 0px"></a></li>-->
						<li><a href="user.html?cmd=homeDash"><i class="fa fa-home fa-2x" alt=""  style="color:#ba2322"></i></a></li>
						
						<li class="current"><a href="#"><img src="img/icon-profile.png" alt="" width="30px" height="25px">
							<%=fullName%></a>                   				
                            <!--ul>
                                <li><a href="user.html?cmd=edit">My profile</a></li>
                                <li><a href="user.html?cmd=initchgPwd">Change your password</a></li>                              
							</ul-->						
						</li>
						
						<%if(roleName !=null && !roleName.equalsIgnoreCase("null")){%>
						<li><a href="#">Current Role: <%=roleName%></a></li>
						<%}else{%>
						<li><a href="#">Current Role: N/A</a></li>
						<%}%>
						<li><a href="logout.html?cmd=logout">Sign out</a></li><%}%>
                 
            </ul>
            </div>
            </div>
         
             <div class="container">  
			 <h1 style="margin-top: -24px;margin-left: -46px;visibility: visible; animation-name: fadeInLeft;" class="navbar-brand navbar-brand_ wow fadeInLeft animated">
					 <a href="user.html?cmd=homeDash">
					 <%if(matchCompanyDet != null && !matchCompanyDet.equalsIgnoreCase("null")){
					 if(matchCompanyDet.equalsIgnoreCase(userCompanyDet)){ %>
					 <!--img src="images/menschForce_<%=matchCompanyDet.toLowerCase()%>.png" style="size:20px  margin-left: 39px; padding:0px; position: relative; " alt="logo">-->
					 <img src="img/menschForce_logo.png" style="size:20px  margin-left: 39px; padding:0px; position: relative; " alt="logo">
					   <%}} %>
					   </a>
					 </h1>
					
					 <nav class="navbar navbar-default navbar-static-top tm_navbar clearfix" role="navigation"  style="margin-top:50px; margin-right: 200px;">
          
            <ul class="nav sf-menu clearfix"> 
                <li class="sub-menu"><a href="#">My Profile</a><span></span>
                	<ul class="submenu" style="width:200px">
                		<li class="submenu" style="width:200px"><a href="user.html?cmd=edit">Edit Profile</a></li>
						<li style="width:200px"><a href="user.html?cmd=initchgPwd">Change Password</a></li>
						<li style="width:200px"><a href="master.html?profileCmd=profiledetails">Profile Details</a></li>
						<!-- li style="width:200px"><a href="master.html?profileCmd=product_package">Plan & Package</a></li-->
						<!-- li style="width:200px"><a href="master.html?profileCmd=security">Security</a></li-->
                    </ul>
                 </li>
              <%
						ArrayList headEntityList1 = (ArrayList)session.getAttribute("privList");
						if(headEntityList1!=null && headEntityList1.size()!=0){
							Iterator itEntList = headEntityList1.iterator();
							
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
							
                        <li class="sub-menu" ><a href="frmHome.jsp"><%=privName%></a><span></span>
                            <ul style="width:290px">
							
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
								String accessDes = strAlllist[9];
								String accessUrl = strAlllist[10];
								
								//System.out.println("accessUrl in jsp::"+accessUrl);
								
								String result = "UN="+username+"&PWD="+password+"&RN="+rolename;
								String tempAccesURL="";
								boolean tempURL = accessUrl.contains("viewMaster.do?process");
								boolean tempname=accessUrl.contains("UN=:U");
								if(tempname == true){
								tempAccesURL = accessUrl.replace("UN=:U",result);
								}
								else if(tempURL == true){
								tempAccesURL=accessUrl.replace("viewMaster.do?process","artifactMapping.html?artiMapProcess");
								}
								else{
								tempAccesURL = accessUrl.replaceAll("do","html");
								}
								
						if(hePrivId.equals(priviId)){	
									  			
						%>
							
                            <li class="submenu" style="margin-right:30px;position:relative;width:270px"><a href="<%=tempAccesURL%>"><%=accessName%></a>
															
									
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
				
                    </ul></nav>
              </div>
              
    
    <!--div class="container_12">
        <div class="grid_12">
            <h1><a href="frmHome.jsp"><img src="images/logo3.jpg" alt=""></a></h1>
        </div>     
    </div-->
</header>

</div>


	<div class="container" style="background-color:white;width:100%;padding:10px 10px">
		<div class="col-lg-9 col-md-7 col-sm-5" >
			&nbsp;
		</div>
				<div class="col-lg-3 col-md-4 col-sm-5" >
						<div class="col-lg-2 col-md-2 col-sm-2" >
						
							<i class="fa fa-home fa-2x">&nbsp;<a href="user.html?cmd=homeDash" style="font-family:sans-serif;font-size:large;font-weight:600;" >Home</a></i> 
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2" ></div>
						<div class="col-lg-2 col-md-2 col-sm-2" >
							<i class="fa fa-arrow-circle-left fa-2x" aria-hidden="true">&nbsp;<a href="#" style="font-family:sans-serif;font-size:large;font-weight:600;" onClick="history.go(-1);">Back</a></i>
						</div>
					</div>
	</div>

</body>
</html>

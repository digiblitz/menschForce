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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resume Search</title>
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="css/touchTouch.css">
     <link rel="stylesheet" href="css/owl.carousel.css">
     <link rel="stylesheet" href="css/style.css">
            
	   <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.css">
	<link rel="stylesheet" href="css/jquery-ui.css" type="text/css"/>
	<link rel="stylesheet" href="css/ui.theme.css" type="text/css"  />
	

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<!-- <script src="js/resumeSearch.js"></script> -->
<script src="js/jquery.js"></script>
	<script src="js/jquery.validate.js"></script>

	<script>
	

		
		function getDocumentName(tempDocument){
			var tempDocument1 = tempDocument.split("\\");
			var size = tempDocument1.length;			
			return tempDocument1[size-1];
		}
		function formatDate(date){
			//var date_string = moment(uploadeddate, "YYYY-MM-DD").format("YYYY-MM-DD");
			var strDate = date.toString('yyyy-MM-dd');
			var date_string = strDate.split("T");
			//alert('format date function '+date_string[0]);
			return date_string[0];
		}
		
		

$(document).ready(function(){

//alert('ok');	


// validate signup form on keyup and submit
		var validator = $("#form").validate({
			rules: {				
				description: "required",				
				experience: "required",
				skillSet: "required",
				university: "required",
				college: "required",
				degree: "required",
				specialization: "required",
				certification: "required"
			},
			messages: {				
				description: "Job description is reuired",
				experience: "Experience is required",
				skillSet: "Skillset is required",
				university: "University name is required",
				college: "College name is required",
				degree: "Degree is required",
				specialization: "Specialization is required",
				certification: "Certification is required"
			},
			// the errorPlacement has to take the table layout into account
			errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}
			
			
		});
		
		

		$(".submit").click(function(){				
		
		//alert('inside click');
		$('#wait').show();	
		 $('#resume').empty();
		 $('.pp').hide();
		 
		var search = $("#search").val();
		//alert('search  '+search);
		var jobdesc = $("#jobdesc").val();
		var exp = $("#exp").val();
		var skillset = $("#skillset").val();
		var university = $("#university").val();
		var college = $("#college").val();
		var degree = $("#degree").val();
		var specialization = $("#specialization").val();
		var certification = $("#certification").val();
		
		if(search == 'all'){
			//alert('search '+search);
	if(jobdesc != "" && exp != "" && skillset != "" && university != "" && college != "" && degree != "" && specialization != "" && certification != ""){				
			myFuction();			
			}else{
				$('#wait').hide();
				$("#form").valid();
			}
		}else{			
			myFuction();
		}      
	});
	
	function myFuction(){
		
		var search = $("#search").val();
		var requestData = null;		
		if(search == 'all'){		
requestData = $("#jobdesc").val()+'%20AND%20'+$("#exp").val()+'%20AND%20'+$("#skillset").val()+'%20AND%20'+$("#university").val()+'%20AND%20'+$("#college").val()+'%20AND%20'+$("#degree").val()+'%20AND%20'+$("#specialization").val()+'%20AND%20'+$("#certification").val();		
		}else if(search == 'any'){		
		requestData = $("#jobdesc").val()+','+$("#exp").val()+','+$("#skillset").val()+','+$("#university").val()+','+$("#college").val()+','+$("#degree").val()+','+$("#specialization").val()+','+$("#certification").val();		
		}
		//alert(requestData);	
		
		$.get('resumeSearch.html?cmd=getResumes', {
        	requestData: requestData
        }, function(response) {			
			//alert(response);
			if(response != null){
        		$('#wait').hide();	

				 var json = JSON.stringify(eval("(" + response + ")"));
				var obj = JSON.parse(json);
				var temp = obj.response.docs;
				var numFound = obj.response.numFound;
				
				if(numFound != 0){
				$.each(temp, function(i, item) {					
					 
			var tempDate = item.date;						
			var uploadedDate = formatDate(tempDate);					
			var tempModifiedDate = item.last_modified;			
			var modifiedDate = formatDate(tempModifiedDate);			
			var tempDocument = item.id;
			var documents = getDocumentName(tempDocument);
						
			$('.pp').show();
            $('#resume').append($('<div class="col-md-8"><div class="form-group"><label>'+documents +'</label> </br> <label>Uploaded Date:</label>'+uploadedDate+'<br/><label>Last Modified Date:</label>'+modifiedDate+'<br/><a class="aUrl" href="resumeSearch.html?cmd=initResumeDownload&resumePath='+tempDocument+'" > Save Resume' + '</a> &nbsp; <a class="aUrl" href="resumeSearch.html?cmd=initResumeDetail&resumeName='+documents+'" >View Profile Details' + '</a> '));
				});
				}else{
				 $('#wait').hide();	
				$('.pp').show();
            $('#resume').append($('<div class="col-md-8"><div class="form-group"><label> Sorry, No documents</label> </div></div></br></br>'));	
				}
        	}else{
				$('#wait').hide();	
				$('.pp').show();
            $('#resume').append($('<div class="col-md-8"><div class="form-group"><label> Sorry, No documents</label> </div></div></br></br>'));	
			}       
        });
	}	
	
$("#all").click(function(){
	validator.resetForm();
	var search = $("#all").val();	
	$("#search").val(search);		
	$("#form").show();		
	$("#submit1").hide();
	$("#submit").show();
	$(".form-validation").show();
	//alert($("#search").val());
});

$("#any").click(function(){
	validator.resetForm();
	var search = $("#any").val();
	$("#search").val(search);			
	$("#form").show();	
	$("#submit1").show();
	$("#submit").hide();
	$(".form-validation").hide();
	//alert($("#search").val());
});


});

	
	</script>
	
	
<style>
body{
	height:100%;
	background:white;
}
#form label.error{
	color:red;
}

</style>

 <!--CSS-->
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/style.css" >
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/camera.css">
<link rel="stylesheet" href="css/jquery.fancybox.css">
<link rel="stylesheet" href="fonts/font-awesome.css">

<!--JS-->

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

<header class="indent">
<div class="">
        <div class="container"> 
            
            <ul class="follow_icon" >
               <%if(!(fullName.equalsIgnoreCase("NULL NULL"))){%>	
                        
						<li><a href="user.html?cmd=homeDash"><img src="img/home (1).png" alt="" width="30px" height="25px" style="padding-top: 0px"></a></li>
						
						<li class="current"><a href="#"><img src="img/icon-profile.png" alt="" width="30px" height="25px">
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
			 <h1 style="margin-top: -24px;margin-left: -46px;visibility: visible; animation-name: fadeInLeft;" class="navbar-brand navbar-brand_ wow fadeInLeft animated">
					 <a href="user.html?cmd=homeDash">
					 <%if(matchCompanyDet.equalsIgnoreCase(userCompanyDet)){ %>
					 <img src="images/menschForce_<%=matchCompanyDet.toLowerCase()%>.png" style="size:20px  margin-left: 39px; padding:0px; position: relative; " alt="logo">
					   <%} %>
					   </a>
					 </h1>
					
					 <nav class="navbar navbar-default navbar-static-top tm_navbar clearfix" role="navigation"  style="margin-top:50px; margin-right: 200px;">
          
            <ul class="nav sf-menu clearfix"> 
                <li class="sub-menu"><a href="#">My Profile</a><span></span>
                	<ul class="submenu" style="width:200px">
                		<li class="submenu" style="width:200px"><a href="user.html?cmd=edit">Edit Profile</a></li>
						<li style="width:200px"><a href="user.html?cmd=initchgPwd">Change Password</a></li>
						<li style="width:200px"><a href="master.html?profileCmd=profiledetails">Profile Details</a></li>
						<li style="width:200px"><a href="master.html?profileCmd=product_package">Plan & Package</a></li>
						<li style="width:200px"><a href="master.html?profileCmd=security">Security</a></li>
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
								String accessUrl = strAlllist[9];
								
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




<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
          
		  <div class="row">
			
				<div class="col-lg-12 col-md-12 col-sm-12">
					
                   <h3 class="title">Resume Search</h3>
				  
				 </div>

				 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
				</div>
						
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
						<label class="name">Kind of Search:</label>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3"> 
						<input type="radio"  name="yesno" id="all" value="all" >All the Fields
						<input type="radio"  name="yesno" id="any" value="any" >Any of the Fields  
					</div>
				</div>
		</div>

                     <div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
					</div>					 
					          
		
		
		<form id="form" name="insert" style="display:none" action="" method="post" >
										
			<input type="hidden" id="search" name="search" value="">
							
				<div class="row">
												
					<div class="col-lg-12 col-md-12 col-sm-12">
                         <div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
                              <label class="name">Job Description <span class="form-validation" style="color:Red;">*</span></label>
						 </div>
						 <div class="col-lg-3 col-md-3 col-sm-3">
									<input  type="text" id="jobdesc" name="description" class="form-control" value=""  maxlength="50" >
                           </div>
					</div>
					
					 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
					</div>
					
					<div class="col-lg-12 col-md-12 col-sm-12">
                         <div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
                              <label class="name">Experience <span class="form-validation" style="color:Red;">*</span></label>
						 </div>
						 <div class="col-lg-3 col-md-3 col-sm-3">
									<input  type="text" id="exp" name="experience" class="form-control" value=""  maxlength="50" >
                           </div>
					</div>
					
					 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
					</div>
					
					<div class="col-lg-12 col-md-12 col-sm-12">
                         <div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
                              <label class="name">Skill Set <span class="form-validation" style="color:Red;">*</span></label>
						 </div>
						 <div class="col-lg-3 col-md-3 col-sm-3">
								<input  type="text" id="skillset" name="skillSet" class="form-control" value=""  maxlength="50" >
                           </div>
					</div>
					
					 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
					</div>
					
						<div class="col-lg-12 col-md-12 col-sm-12">
							 <div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
                                <div class="form-group">
                                    <label class="name subtitle" >Education-</label>
                                </div>
                            </div>	
						</div>	
					<div class="col-lg-12 col-md-12 col-sm-12">
                         <div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
                              <label class="name">University <span class="form-validation" style="color:Red;">*</span></label>
						 </div>
						 <div class="col-lg-3 col-md-3 col-sm-3">
								<input  type="text" id="university" name="university" class="form-control" value=""  maxlength="50" >
                           </div>
					</div>
					
					 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
					</div>
							
					<div class="col-lg-12 col-md-12 col-sm-12">
                         <div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
                              <label class="name">College <span class="form-validation" style="color:Red;">*</span></label>
						 </div>
						 <div class="col-lg-3 col-md-3 col-sm-3">
								<input  type="text" id="college" name="college" class="form-control" value=""  maxlength="50" >
                           </div>
					</div>
					
					 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
					</div>

					<div class="col-lg-12 col-md-12 col-sm-12">
                         <div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
                              <label class="name">Degree <span class="form-validation" style="color:Red;">*</span></label>
						 </div>
						 <div class="col-lg-3 col-md-3 col-sm-3">
								<input  type="text" id="degree" name="degree" class="form-control" value=""  maxlength="50" >
                           </div>
					</div>
					
					 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
					</div>
							
					<div class="col-lg-12 col-md-12 col-sm-12">
                         <div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
                              <label class="name">Specialization <span class="form-validation" style="color:Red;">*</span></label>
						 </div>
						 <div class="col-lg-3 col-md-3 col-sm-3">
								<input  type="text" id="specialization" name="specialization" class="form-control" value=""  maxlength="50" >
                           </div>
					</div>
					
					 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
					</div>
							
					<div class="col-lg-12 col-md-12 col-sm-12">
                         <div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
                              <label class="name">Certification <span class="form-validation" style="color:Red;">*</span></label>
						 </div>
						 <div class="col-lg-3 col-md-3 col-sm-3">
								<input  type="text" id="certification" name="certification" class="form-control" value=""  maxlength="50" >
                           </div>
					</div>
					
					 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
					</div>	
				</div>			
							
							
				<div class="row">
																			
					 <div class="col-lg-12 col-md-12 col-sm-12">								
												
						 <div class="col-lg-1 col-md-1 col-sm-1 col-sm-offset-4">	
								  <input type="button" name="Insert" value="Submit" class="button-add submit" id="submit">
						
								<input formnovalidate="formnovalidate" type="button" class="button-add submit" id="submit1" value="Submit" />
						</div>
						 <div class="col-lg-1 col-md-1 col-sm-1">
								 <input type="button" value="Cancel" name="button" class="button-dang" onClick="javascript:history.back(-1);" >
						</div>
					</div>
				</div>		
		</form>		


		<div  id="wait" style="display:none;" >
			<div class="loading" align="center" style=" padding:10px; margin-top:100px; margin-left:50px;">Processing...Please wait</div>
			<div class="loading" align="center" style="padding:10px;"><img src="images/processing.gif" /></div>
		</div>

		<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
					</div>	
  
<div class="pp" style="display:none; margin: 16px 0 16px 0;  padding: 10px;  BACKGROUND: #F2F2EB;  -moz-border-radius: 12px 12px 0 0;  -webkit-border-radius: 12px 12px 0 0;  border-radius: 12px 12px 0 0;"> 
 <div class="ppp" style="font-family: Candara, Trebuchet MS, Verdana, Arial, Helvetica, sans-serif;  font-size: 22px;  font-weight: bold;  color: #4e4e4e;  margin-bottom: 8px;  TEXT-SHADOW: 0 1px 0 rgba(255, 255, 255, 0.85);">Resumes List</div>
<div class="pps" style=" border: 2px SOLID #e2e2d6;  background: white;  padding: 10px;  font-size: 15px; color:black;">
	
		
	<div class="row" id="resume" >
	
						
	
	
	</div>
	</div>
	</div>
	
		</div>
</div></div>
	

	<div>
	<!-- <button id="click" value="Click" >Click</button> -->
	
	<!-- FOOTER STARTS HERE -->
                        
						<%@ include file = "../../include/FooterProcess.jsp" %>
                    <!-- FOOTER ENDS HERE -->
					
	</div>
</body>

   <style>   
<!-- new form starts -->
		.news-box h1 {
    margin: 30px 0;
    color: #3d84e6;
}
		.top-margin {
    margin-top: 20px;
}
		.form-group .form-validation {
        position: absolute;
    top: 20px;
    right: 6px;
}

a:hover{
	color:#fff000;
}

<!-- new form ends -->
</style>

</html>

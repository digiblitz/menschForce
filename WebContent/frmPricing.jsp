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
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Product_Pricing</title>

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<!--CSS-->
<link rel="stylesheet" href="css/bootstrap.css" >
<link rel="stylesheet" href="css/style.css">

<link rel="stylesheet" href="css/stylenew.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/camera.css">
<link rel="stylesheet" href="css/jquery.fancybox.css">
<link rel="stylesheet" href="fonts/font-awesome.css">
<!--JS-->


    <script type="text/javascript">
		$( document ).ready(function( $ ) {
		$(".overlay").click(function() {
		$(".overlay").fadeOut();
		$(".popCont").fadeOut();
		
	});
		});

	</script>

	<script type="text/javascript">
	
		function showPop(cont){
		$("#overlay").fadeIn(300);
		$("#"+cont).fadeIn();
		}
	</script>
	 <script type="text/javascript">
		$( document ).ready(function( $ ) {
		$(".overlay").click(function() {
		$(".overlay").fadeOut();
		$(".popCont").fadeOut();
	});
		});

	</script>

	<script type="text/javascript">
	
		function showPop(cont){
		$("#overlay").fadeIn(300);
		$("#"+cont).fadeIn();
		}
	</script>
	
	<style>
		
	.overlay{

		width:100%;
		
		height:100%;
		
		background:#333;
		
		opacity:0.85;
		
		position:fixed;
		
		z-index:9999;
		
		left:0;
		
		top:0;
		
		}
		
		.popCont {
		
			background: hsl(0, 0%, 100%) none repeat scroll 0 0;
			height: auto;
			left: 15%;
			overflow: auto;
		    padding: 20px 0 20px 30px;
			position: fixed;
			top: 35px;
			width: 70%;
			z-index: 999999;
			bottom:0;
			height:370px;
		
		
		}
		

		.popCont1{left:5%}

		.footer-section ul li span a{font-size:12px;}



		.popCont h2 {
		
			color: #fff;
			/*background: none repeat scroll 0 0 #FF9933;*/
			margin: 0 0 0px 0;
			padding: 10px 0 15px 2px;
			font-size:18px;
			text-align:center;
		
		}

		.popCont h4 {
		
			color: #fff;
			
			background: none repeat scroll 0 0 #df9626;
			
			margin: 0 0 0px 0;
			
			padding: 10px 0 10px 0;
			
		}

		.popCont p{color:#333; font-family:Arial, Helvetica, sans-serif; font-size:14px; font-weight:normal; padding:0px 10px;}
		.popCont p span{ margin:0 0 0 10%; color:#01bf9d;}
		.popCont ul li{list-style:circle; font-size:15px;}
		.overlay,.popCont{
			display:none;
			}
		/*	
		#tabs{ position:relative; width:100%;}
		.tabs{ float:left; display:block; overflow:hidden;}*/
		
	</style>
	
</head>

<body>
<!--header-->
<div id="header">
<!-- HEADER STARTS HERE -->
<%@ include file = "../../include/header_new.jsp" %>
  <!-- HEADER ENDS HERE -->
</div>

<div class="content">
<div id="overlay" class="overlay"></div>
<section class="container">
	
	
	<div style="padding:15px 0;"><a  style="text-align: left;
    font-family: Verdana, Arial, Helvetica, sans-serif;
    font-size: 20px;
    font-weight: bold;
    margin-left: -10px;
    padding: 10px;
    margin-bottom: 25px;
    color: #158d8e; text-transform:uppercase;" href="javascript:void(0);" onClick="showPop('popCont40');">Beta</a></div>

		<div style="border: 1px solid #D6D6D6; margin-bottom:30px;">
		<ul class="nav nav-tabs" style="background:#f3f3f3;">
		  <li class="active"><a style="font-weight:600;" data-toggle="tab" href="#msp">MSP Product</a></li>
		  <li><a style="font-weight: 600;" data-toggle="tab" href="#vendor">Vendor Product</a></li>
		</ul>
		
		<div class="tab-content">
		
		<div class="pricing_table_wdg tab-pane fade in active" id="msp">
		<div class="container">
			<ul>
				<li style="background:#01bf9d;">Basic</li>
				<li>$99/<span style="font-size:12px;">month</span></li>
				<li style="background:#000; padding:5px 7px; color:#fff;">One Time Setup Cost<br><span style="font-size:13px;">-</span></li>
				<!--<li>Perfect for those getting started with our app.</li>-->
				<li>Features</li>
				<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont');">Managed Service Process</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont1');">Functional and Process Management</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont2');">Business Intelligence</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont3');">Business Architecture Management</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont4');">Product Training and <br>Support</a></li>
				<li style="border:none"><a style="width:38%; float:left; margin:5px;background:#01bf9d;" href="signUp.html?signUpProcess=pricingDetails" class="buy_now">Try Now</a><a style="width:40%; float:left; margin:5px;" href="signUp.html?signUpProcess=pricingDetails&getProductPrice=99" class="buy_now">Buy Now</a></li>
				<!--<li><a href="" class="buy_now">Buy Now</a></li>-->
			</ul>
			
			<ul class="active">
				<li style="background:coral;">Silver</li>
				<li>$299/<span style="font-size:12px;">month</span></li>
				<li style="background:#000; padding:5px 7px; color:#fff;">One Time Setup Cost<br><span style="font-size:13px;">$999</span></li>
				<!--<li>Perfect for those getting started with our app.</li>-->
				<li>Features</li>
				<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont5');">Managed Service Process</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont6');">Functional and Process Management</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont7');">Business Intelligence</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont8');">Business Architecture Management</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont9');">Product Training and Support</a></li>
				<li style="border:none"><a style="width:50%; float:left; margin:10px 50px;" href="signUp.html?signUpProcess=pricingDetails&getProductPrice=299" class="buy_now">Buy Now</a></li>
			</ul>
			
			<ul>
				<li style="background:goldenrod;">Gold</li>
				<li>$1249/<span style="font-size:12px;">month</span></li>
				<li style="background:#000; padding:5px 7px; color:#fff;">One Time Setup Cost<br><span style="font-size:13px;">$3999</span></li>
				<!--<li>Perfect for those getting started with our app.</li>-->
				<li>Features</li>
				<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont10');">Managed Service Process</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont11');">Functional and Process Management</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont12');">Business Intelligence</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont13');">Business Architecture Management</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont14');">Product Training and Support</a></li>
				<li style="border:none"><a style="width:50%; float:left; margin:10px 50px;" href="signUp.html?signUpProcess=pricingDetails&getProductPrice=1249" class="buy_now">Buy Now</a></li>
			</ul>
			
			<ul>
				<li style="background:palevioletred;">Platinum</li>
				
				<li>$4999/<span style="font-size:12px;">month</span></li>
				<li style="background:#000; padding:5px 7px; color:#fff;">One Time Setup Cost<br><span style="font-size:13px;">$14999</span></li>
				<!--<li>Perfect for those getting started with our app.</li>-->
				<li>Features</li>
				<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont15');">Managed Service Process</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont16');">Functional and Process Management</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont17');">Business Intelligence</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont18');">Business Architecture Management</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont19');">Product Training and Support</a></li>
				<li style="border:none"><a style="width:50%; float:left; margin:10px 50px;" href="signUp.html?signUpProcess=pricingDetails&getProductPrice=4999" class="buy_now">Buy Now</a></li>
			</ul>
			
			<ul>
				<li style="background:#334960;">Enterprise</li>
				<li>-/<span style="font-size:12px;">month</span></li>
				<li style="background:#000; padding:5px 7px; color:#fff;">One Time Setup Cost<br><span style="font-size:13px;">-</span></li>
				<li>Features</li>
				<li style="border-bottom:none; font-weight:bold; color:red;">Call For Pricing</a></li>
				
			<!--	<li>$120</li>
				<li>Perfect for those getting started with our app.</li>
				<li>15 Projects</li>
				<li>5GB Storage</li>
				<li>Unlimited Users</li>
				<li>No Time Tracking</li>
				<li>Enchaned Security</li>
				<li></li>-->
				<!--<li><a style="width:30%; float:left; margin:10px;background:#334960;" href="" class="buy_now">Try</a><a style="width:30%; float:left; margin:10px;" href="" class="buy_now">Buy</a></li>-->
			</ul>
			
			
			<div class="popCont" id="popCont40" style="width:35%; height:150px; left:32%; top:15%">
				<p>Its an evolution from the previous version in terms of Fixing Bugs, Countering Errors, Improved Ease of Access and User Interface. We have added features that makes the user maximise his productivity to a greater scale.</p>
				
			</div>
			
			<div class="popContt" id="popCont40" style="width:35%; height:150px; left:32%; top:15%">
				<p>Its an evolution from the previous version in terms of Fixing Bugs, Countering Errors, Improved Ease of Access and User Interface. We have added features that makes the user maximise his productivity to a greater scale.</p>
				
			</div>
			
			
			<div class="popCont" id="popCont">
				<div class="wid" style="overflow-y: auto;
		height: 220px;">
					<h2 class="back">Users</h2>
					<p>MSP : Account Manager<span>2</span></p>
				<p>MSP : Hiring Manager<span>1</span></p>
				<p>MSP : Recruiter<span>1</span></p>
				<p>MSP : HR Manager<span>1</span></p>
				<p>MSP : Finance<span>1</span></p>
				<p>MSP : Admin<span>1</span></p>
				<p>Supplier : Account Manager<span>5</span></p>
				<p>Supplier : Recruiter<span>10</span></p>
				<p>Supplier : HR Manager<span>5</span></p>
				<p>Supplier : Finance<span>5</span></p>
				<p>Supplier : Admin<span>5</span></p>
				<p>End Client : Hiring Manager<span>1</span></p>
				<p>End Client : HR Manager<span>1</span></p>
				<p>End Client : Finance<span>1</span></p>
				<p>End Client : Admin<span>1</span></p>
				<p>End Client : Corporate User<span>1</span></p>
				<p>End Client : Business Head User<span>1</span></p>
				<p>Candidate<span>250</span></p>
				</div>
				<div class="wid" style="overflow-y: auto;
		height: 220px;">
					<h2 class="back">Immigration Compliance Management</h2>
				<p><span>-</span></p>
					
				</div>
				<div class="wid" style="overflow-y: auto;
		height: 220px;">
					<h2 class="back">Vendor Management</h2>				
				<p>No of Vendors<span>5</span></p>	
					
				</div>
				<div class="wid" style="overflow-y: auto;
		height: 100px;">
					<h2 class="back">Client Management</h2>
				<p><span>Included</span></p>
				</div>
				<div class="wid" style="overflow-y: auto;
		height: 100px;">
					<h2 class="back">Campaign Management</h2>
				<p>No of E-mail Campaign to vendors<span>25</span></p>
				</div>
				<div class="wid" style="overflow-y: auto;
		height: 100px;">
					<h2 class="back">Recruitment Screening Process</h2>
					<p><span>Included</span></p>
				</div>
				<div class="wid" style="overflow-y: auto;
		height: 100px;">
					<h2 class="back">Storage</h2>
					<p><span>50GB</span></p>
				</div>
			</div>
			<div class="popCont" id="popCont1" style="padding: 15px 0 0 30px;">
			<div class="wid" style="overflow-y: auto;">
				<h2 class="back">ERP</h2>
			<p>No of Modules<span>2</span></p>
			<p>No of Forms<span>10</span></p>
			<p>No of Screens<span>40</span></p>
			</div>
			<div class="wid" style="overflow-y: auto;">
					<h2 class="back">Business Services</h2>
				<p>No of Provider Services<span>2</span></p>
				<p>No of Consumer Services<span>6</span></p>
				<p>No of Service LifeCycle Design Allowed<span>1</span></p>
			</div>
			<div class="wid" style="overflow-y: auto;">
					<h2 class="back">Process Automation</h2>
				<p>No of Single Instance Process<span>2</span></p>
				<p>No of Multi-Instance Process<span>1</span></p>
				<p>No of Process LifeCycle Design Allowed<span>1</span></p>
			</div>
			</div>
			<div class="popCont" id="popCont2" style="padding: 20px 20px 0; height:320px;">
			<div class="wid" style="width:100%; overflow-y: auto;">
				<h2 class="back">Business intelligence & Analytics</h2>
				<p>No of Business Intelligence & Analytics Modeler Session<span>100</span></p>
			<p>No of Business Intelligence & Analytics Consumer Session<span>-</span></p>
			</div>	
			</div>
			<div class="popCont" id="popCont3" style="height:300px;">
			<div class="wid">
				<h2 class="back">Asset/ Artifacts management & Governance</h2>
			<p>No of un-governed Assets/Artifacts<span>-</span></p>
			<p>No of Governed Assets/Artifacts<span>1</span></p>
			</div>	
			<div class="wid">
				<h2 class="back">Business Architecture Modeling Bundle</h2>
				<p>No of BA LifeCycle Design Allowed<span>-</span></p>
			<p>No of Business Mapping Allowed<span>-</span></p>
			<p>No of Business of Process Units aligned<span>-</span></p>
			<p>No of Business Reference Architecture<span>-</span></p>
			<p>No of Blue Prints<span>-</span></p>
			</div>	
			<div class="wid">
				<h2 class="back">Enterprise Architecture Planning Bundle</h2>
				<p>No of EA frameworks<span>-</span></p>
			<p>No of EA LifeCycle Design Allowed<span>-</span></p>
			<p>No of Technical Reference Architecture<span>-</span></p>
			<p>No of Business Processes  Alignment<span>-</span></p>
			<p>No of  Business Services Alignment<span>-</span></p>
			<p>No of  Web/technical Services Alignment<span>-</span></p>
			<p>No of  Application component Alignment<span>-</span></p>
			</div>
			</div>
			<div class="popCont" id="popCont4">
			<div class="wid" style="overflow-y:auto;">
				<h2 class="back">Remote Technical Support & Training</h2>
				<p>Technical Support - Forum, FAQ & Email<span>FREE 24/7</span></p>
				<p>Remote Training & Technical Support - Telephone, Chat, Remote Login & WebEx<span>-</span></p>
				<p>Training & Technical Support - Onsite<span>-</span></p>
				
			</div>	
			<div class="wid" style="overflow-y:auto;">
				<h2 class="back">Business Engineering Technology Support</h2>
				<p>Technical Support - Forum, FAQ & Email<span>FREE 24/7</span></p>
			<p>Remote Training & Technical Support - Telephone, Chat, Remote Login & WebEx<span>-</span></p>
			<p>Training & Technical Support - Onsite<span>-</span></p>
				
			</div>
			<div class="wid" style="overflow-y:auto; height:auto;">
				<h2 class="back">Application Integration(API)</h2>
				<p><span>-</span></p>
			</div>	
			
			<div class="wid" style="overflow-y:auto; height:auto;">
				<h2 class="back">Database Integration</h2>
				<p><span>-</span></p>
			</div>	
			</div>
			
			
			<div class="popCont" id="popCont5">
			<div class="wid1" style="height:225px;">
				<h2 class="back1">Users</h2>
				<p>MSP : Account Manager<span>3</span></p>
				<p>MSP : Hiring Manager<span>10</span></p>
				<p>MSP : Recruiter<span>20</span></p>
				<p>MSP : HR Manager<span>2</span></p>
				<p>MSP : Finance<span>2</span></p>
				<p>MSP : Admin<span>1</span></p>
				<p>Supplier : Account Manager<span>25</span></p>
				<p>Supplier : Recruiter<span>250</span></p>
				<p>Supplier : HR Manager<span>10</span></p>
				<p>Supplier : Finance<span>10</span></p>
				<p>Supplier : Admin<span>10</span></p>
				<p>End Client : Hiring Manager<span>5</span></p>
				<p>End Client : HR Manager<span>5</span></p>
				<p>End Client : Finance<span>5</span></p>
				<p>End Client : Admin<span>1</span></p>
				<p>End Client : Corporate User<span>5</span></p>
				<p>End Client : Business Head User<span>5</span></p>
				<p>Candidate<span>1500</span></p>
				
			</div>		
			<div class="wid1" style="overflow-y: auto; height:225px;">
					<h2 class="back1">Immigration Compliance Management</h2>
				<p><span>Included</span></p>
				
			</div>	
			<div class="wid1" style="overflow-y: auto; height:225px;">
				<h2 class="back1">Vendor Management</h2>
				
				<p>No of Vendors<span>10</span></p>	
	
	
				
			</div>	
			<div class="wid1" style="overflow-y: auto; height:100px;">
					<h2 class="back1">Client Management</h2>
				<p><span>Included</span></p>
				</div>
				<div class="wid1" style="overflow-y: auto; height:100px;">
					<h2 class="back1">Campaign Management</h2>
				<p>No of E-mail Campaign to vendors<span>150</span></p>
				</div>
				<div class="wid1" style="overflow-y: auto; height:100px;">
					<h2 class="back1">Recruitment Screening Process</h2>
				<p><span>Included</span></p>
				</div>	
				<div class="wid1" style="overflow-y: auto; height:100px;">
					<h2 class="back1">Storage</h2>
				<p><span style="margin-left:7%;">One Storage Bundle Included</span></p>
				</div>	
			</div>
			<div class="popCont" id="popCont6" style="padding: 15px 0 0 30px;">
			<div class="wid1" style="overflow-y: auto;">
				<h2 class="back1">ERP</h2>
				<p>No of Modules<span>2</span></p>
			<p>No of Forms<span>10</span></p>
			<p>No of Screens<span>40</span></p>
			
				
			</div>	
			
			<div class="wid1" style="overflow-y: auto;">
				<h2 class="back1">Business Services</h2>
				<p>No of Provider Services<span>30</span></p>
			<p>No of Consumer Services<span>100</span></p>
			<p>No of Service LifeCycle Design Allowed<span>2</span></p>
				
			</div>	
			
			<div class="wid1" style="overflow-y: auto;">
				<h2 class="back1">Process Automation</h2>
				<p>No of Single Instance Process<span>15</span></p>
			<p>No of Multi-Instance Process<span>5</span></p>
			<p>No of Process LifeCycle Design Allowed<span>2</span></p>
				
			</div>	
			
			
			</div>
			
			
			<div class="popCont" id="popCont7" style="padding: 20px 20px 0; height:320px;">
				
			<div class="wid1" style="width:100%; overflow-y: auto;">
				<h2 class="back1">Business intelligence & Analytics</h2>
				<p>No of Business Intelligence & Analytics Modeler Session<span>500</span></p>
			<p>No of Business Intelligence & Analytics Consumer Session<span>50</span></p>
			</div>	
			
			</div>
			
			<div class="popCont" id="popCont8" style="height:300px;">
					
			<div class="wid1">
				<h2 class="back1">Asset/ Artifacts management & Governance</h2>
				<p>No of un-governed Assets/Artifacts<span>1</span></p>
			<p>No of Governed Assets/Artifacts<span>5</span></p>
			</div>	
			
			<div class="wid1">
				<h2 class="back1">Business Architecture Modeling Bundle</h2>
				<p>No of BA LifeCycle Design Allowed<span>1</span></p>
			<p>No of Business Mapping Allowed<span>4</span></p>
			<p>No of Business of Process Units aligned<span>1</span></p>
			<p>No of Business Reference Architecture<span>1</span></p>
			<p>No of Blue Prints<span>1</span></p>
			</div>	
			
			<div class="wid1">
				<h2 class="back1">Enterprise Architecture Planning Bundle</h2>
				<p>No of EA frameworks<span>1</span></p>
			<p>No of EA LifeCycle Design Allowed<span>1</span></p>
			<p>No of Technical Reference Architecture<span>1</span></p>
			<p>No of Business Processes  Alignment<span>1</span></p>
			<p>No of  Business Services Alignment<span>5</span></p>
			<p>No of  Web/technical Services Alignment<span>20</span></p>
			<p>No of  Application component Alignment<span>100</span></p>
			</div>	
			
			</div>
			
			<div class="popCont" id="popCont9">
			
			<div class="wid1" style="overflow-y:auto; height:260px;">
				<h2 class="back1">Remote Technical Support & Training</h2>
				<p>Technical Support - Forum, FAQ & Email<span>FREE 24/7</span></p>
			<p>Remote Training & Technical Support - Telephone, Chat, Remote Login & WebEx<span>One Remote Training & Support Bundle Included</span></p>
			<p>Training & Technical Support - Onsite<span>Call For Pricing</span></p>
			</div>	
			
			<div class="wid1" style="overflow-y:auto; height:260px;">
				<h2 class="back1">Business Engineering Technology Support</h2>
				<p>Online Support - Forum, FAQ & Email<span>FREE 24/7</span></p>
			<p>Remote BET Support - Telephone, Chat, Remote Login & WebEx<span>-</span></p>
			<p>BET Support - Onsite<span>Call For Pricing</span></p>
			</div>	
			
			<div class="wid1" style="overflow-y:auto; height:auto;">
				<h2 class="back1">Application Integration(API)</h2>
				<p><span>Call For Pricing</span></p>
			</div>	
			
			<div class="wid1" style="overflow-y:auto; height:auto;">
				<h2 class="back1">Database Integration</h2>
				<p><span>Call For Pricing</span></p>
			</div>	
			
			</div>
			
			<div class="popCont" id="popCont10">
					
			<div class="wid2" style="overflow-y: auto; height:225px;">
				<h2 class="back2">Users</h2>
				<p>MSP : Account Manager<span>9</span></p>
				<p>MSP : Hiring Manager<span>30</span></p>
				<p>MSP : Recruiter<span>60</span></p>
				<p>MSP : HR Manager<span>6</span></p>
				<p>MSP : Finance<span>6</span></p>
				<p>MSP : Admin<span>3</span></p>
				<p>Supplier : Account Manager<span>75</span></p>
				<p>Supplier : Recruiter<span>750</span></p>
				<p>Supplier : HR Manager<span>30</span></p>
				<p>Supplier : Finance<span>30</span></p>
				<p>Supplier : Admin<span>30</span></p>
				<p>End Client : Hiring Manager<span>15</span></p>
				<p>End Client : HR Manager<span>15</span></p>
				<p>End Client : Finance<span>15</span></p>
				<p>End Client : Admin<span>3</span></p>
				<p>End Client : Corporate User<span>15</span></p>
				<p>End Client : Business Head User<span>15</span></p>
				<p>Candidate<span>4500</span></p>
				
			</div>		
			<div class="wid2" style="overflow-y: auto; height:225px;">
				<h2 class="back2">Immigration Compliance Management</h2>
				<p><span>Included</span></p>
				
			</div>	
			<div class="wid2" style="overflow-y: auto; height:225px;">
				<h2 class="back2">Vendor Management</h2>				
				<p>No of Vendors<span>20</span></p>		
	
	
				
			</div>	
			<div class="wid2" style="overflow-y: auto; height:100px;">
					<h2 class="back2">Client Management</h2>
				<p><span>Included</span></p>
				</div>
				<div class="wid2" style="overflow-y: auto; height:100px;">
					<h2 class="back2">Campaign Management</h2>
				<p>No of E-mail Campaign to vendors<span>750</span></p>
				</div>
				<div class="wid2" style="overflow-y: auto; height:100px;">
					<h2 class="back2">Recruitment Screening Process</h2>
				<p><span>Included</span></p>
				</div>
				
				<div class="wid2" style="overflow-y: auto; height:100px;">
						<h2 class="back2">Storage</h2>
				<p><span>Five Storage Bundle Included</span></p>
				</div>
			</div>
			
			
			<div class="popCont" id="popCont11">
				<div class="wid2" style="overflow-y: auto;">
				<h2 class="back2">ERP</h2>
				<p>No of Modules<span>10</span></p>
			<p>No of Forms<span>50</span></p>
			<p>No of Screens<span>200</span></p>
				
			</div>	
			
			<div class="wid2" style="overflow-y: auto;">
				<h2 class="back2">Business Services</h2>
				<p>No of Provider Services<span>2</span></p>
			<p>No of Consumer Services<span>6</span></p>
			<p>No of Service LifeCycle Design Allowed<span>1</span></p>
				
			</div>	
			
			<div class="wid2" style="overflow-y: auto;">
				<h2 class="back2">Process Automation</h2>
				<p>No of Single Instance Process<span>2</span></p>
			<p>No of Multi-Instance Process<span>1</span></p>
			<p>No of Process LifeCycle Design Allowed<span>1</span></p>
				
			</div>	
			</div>
			
			
			<div class="popCont" id="popCont12">
			<div class="wid2" style="width:100%; overflow-y: auto;">
				<h2 class="back2">Business intelligence & Analytics</h2>
				<p>No of Business Intelligence & Analytics Modeler Session<span>500</span></p>
			<p>No of Business Intelligence & Analytics Consumer Session<span>50</span></p>
			</div>	
			</div>
			
			
			<div class="popCont" id="popCont13">
				<div class="wid2" style="height:305px; overflow-y: auto;">
				<h2 class="back2">Asset/ Artifacts management & Governance</h2>
				<p>No of un-governed Assets/Artifacts<span>1</span></p>
			<p>No of Governed Assets/Artifacts<span>5</span></p>
			</div>	
			
			<div class="wid2" style="height:305px; overflow-y: auto;">
				<h2 class="back2">Business Architecture Modeling Bundle</h2>
				<p>No of BA LifeCycle Design Allowed<span>5</span></p>
			<p>No of Business Mapping Allowed<span>20</span></p>
			<p>No of Business of Process Units aligned<span>5</span></p>
			<p>No of Business Reference Architecture<span>3</span></p>
			<p>No of Blue Prints<span>5</span></p>
			</div>	
			
			<div class="wid2" style="height:305px; overflow-y: auto;">
				<h2 class="back2">Enterprise Architecture Planning Bundle</h2>
				<p>No of EA frameworks<span>2</span></p>
			<p>No of EA LifeCycle Design Allowed<span>5</span></p>
			<p>No of Technical Reference Architecture<span>5</span></p>
			<p>No of Business Processes  Alignment<span>5</span></p>
			<p>No of  Business Services Alignment<span>25</span></p>
			<p>No of  Web/technical Services Alignment<span>100</span></p>
			<p>No of  Application component Alignment<span>500</span></p>
			</div>	
			</div>
			
			
			<div class="popCont" id="popCont14">
				<div class="wid2" style="overflow-y:auto; height:260px;">
				<h2 class="back2">Remote Technical Support & Training</h2>
				<p>Technical Support - Forum, FAQ & Email<span>FREE 24/7</span></p>
			<p>Remote Training & Technical Support - Telephone, Chat, Remote Login & WebEx<span>Three Remote Training & Support Bundle Included</span></p>
			<p>Training & Technical Support - Onsite<span>Call For Pricing</span></p>

			</div>	
			
			<div class="wid2" style="overflow-y:auto; height:260px;">
				<h2 class="back2">Business Engineering Technology Support</h2>
				<p>Online Support - Forum, FAQ & Email<span>FREE 24/7</span></p>
			<p>Remote BET Support - Telephone, Chat, Remote Login & WebEx<span>-</span></p>
			<p>BET Support - Onsite<span>Call For Pricing</span></p>
			</div>	
			
			<div class="wid2" style="overflow-y:auto; height:auto;">
				<h2 class="back2">Application Integration(API)</h2>
				<p><span>Call For Pricing</span></p>
			</div>	
			
			<div class="wid2" style="overflow-y:auto; height:auto;">
				<h2 class="back2">Database Integration</h2>
				<p><span>Call For Pricing</span></p>
			</div>
			</div>
			
			
			
				
			<div class="popCont" id="popCont15">
				
			<div class="wid3" style="overflow-y: auto; height:225px;">
				<h2 class="back3">Users</h2>
				<p>Organizer User<span>15</span></p>
				<p>Member User<span>375</span></p>
				<p>SubChapter User<span>15</span></p>
				<p>Corporate User<span>15</span></p>
				<p>Business Head User<span>15</span></p>
				
			</div>		
			<div class="wid3" style="overflow-y: auto; height:225px;">
				<h2 class="back3">Events Management</h2>
				<p>No of events<span>20</span></p>
				<p>No of event entries<span>1000</span></p>
				
			</div>	
			<div class="wid3" style="overflow-y: auto; height:225px;">
				<h2 class="back3">Training Management</h2>
				<p>Interactive Assignment / Lab<span>25</span></p>
				<p>Descriptive Assignment / Lab<span>125</span></p>
				<p>No of Multiple Choice / Objective Tests<span>125</span></p>
				<p>No of Elaborative / Descriptive Tests<span>25</span></p>
	
	
				
			</div>	
			<div class="wid3" style="overflow-y: auto; height:100px;">
					<h2 class="back3">Membership Management</h2>
					<p><span>Included</span></p>
				</div>
				<div class="wid3" style="overflow-y: auto; height:100px;">
					<h2 class="back3">Subchapter</h2>
					<p><span>Five respective subchapter bundle included</span></p>
				</div>
				<div class="wid3" style="overflow-y: auto; height:100px;">
					<h2 class="back3">Web store</h2>
					<p><span>Included</span></p>
				</div>
				
				<div class="wid3" style="overflow-y: auto; height:100px;">
					<h2 class="back3">Storage</h2>
					<p><span>Fifteen Storage Bundle Included</span></p>
				</div>
			
			</div>
			<div class="popCont" id="popCont16" style="padding: 15px 0 0 30px;">
			<div class="wid3" style="overflow-y: auto;">
				<h2 class="back3">ERP</h2>
				<p>No. of Modules<span>30</span></p>
				<p>No. of Forms<span>150</span></p>
				<p>No. of Screens<span>600</span></p>
				
			</div>	
			
			<div class="wid3" style="overflow-y: auto;">
				<h2 class="back3">Business Services</h2>
				<p>No. of Provider Services<span>10</span></p>
				<p>No. of Consumer Services<span>30</span></p>
				<p>No of Service LifeCycle Design Allowed<span>5</span></p>
				
			</div>	
			
			<div class="wid3" style="overflow-y: auto;">
				<h2 class="back3">Process Automation</h2>
				<p>No of Single Instance Process<span>10</span></p>
				<p>No of Multi-Instance Process<span>5</span></p>
				<p>No of Process LifeCycle Design Allowed<span>5</span></p>
				
			</div>	
			</div>
			
			<div class="popCont" id="popCont17" style="padding: 20px 20px 0; height:320px;">
						
			<div class="wid3" style="width:100%; overflow-y: auto;">
				<h2 class="back3">Business intelligence & Analytics</h2>
				<p>No of Business Intelligence & Analytics Modeler Session<span>5</span></p>
				<p>No of Business Intelligence & Analytics Consumer Session<span>25</span></p>
			</div>	
			
			</div>
			
			<div class="popCont" id="popCont18">
			<div class="wid3" style="height:305px; overflow-y: auto;">
				<h2 class="back3">Asset/ Artifacts management & Governance</h2>
				<p>No of un-governed Assets/Artifacts<span>2500</span></p>
				<p>No of Governed Assets/Artifacts<span>250</span></p>
			</div>	
			
			<div class="wid3" style="height:305px; overflow-y: auto;">
				<h2 class="back3">Business Architecture Modeling Bundle</h2>
				<p>No of BA LifeCycle Design Allowed<span>25</span></p>
				<p>No of Business Mapping Allowed<span>100</span></p>
				<p>No. of Business of Process Units aligned<span>25</span></p>
				<p>No. of Business Reference Architecture<span>15</span></p>
				<p>No of Blue Prints<span>25</span></p>
			</div>	
			
			<div class="wid3" style="height:305px; overflow-y: auto;">
				<h2 class="back3">Enterprise Architecture Planning Bundle</h2>
				<p>No of EA frameworks<span>10</span></p>
				<p>No of EA LifeCycle Design Allowed<span>25</span></p>
				<p>No. of Technical Reference Architecture<span>25</span></p>
				<p>No of Business Processes  Alignment<span>25</span></p>
				<p>No of  Business Services Alignment<span>125</span></p>
				<p>No of  Web/technical Services Alignment<span>500</span></p>
				<p>No of  Application component Alignment<span>2500</span></p>
			</div>	
			</div>
			<div class="popCont" id="popCont19">
			<div class="wid3" style="overflow-y:auto; height:260px;">
				<h2 class="back3">Remote Technical Support & Training</h2>
				<p>Technical Support - Forum, FAQ & Email<span>FREE 24/7</span></p>
				<p>Remote Training & Technical Support - Telephone, Chat, Remote Login & WebEx<span>Seven Remote Training & Support Bundle Included</span></p>
				<p>Training & Technical Support - Onsite<span>Call For Pricing</span></p>
			</div>	
			
			<div class="wid3" style="overflow-y:auto; height:260px;">
				<h2 class="back3">Business Engineering Technology Support</h2>
				<p>Online Support - Forum, FAQ & Email<span>FREE 24/7</span></p>
				<p>Remote BET Support - Telephone, Chat, Remote Login & WebEx<span>One Remote BET Support Bundle Included</span></p>
				<p>BET Support - Onsite<span>Call For Pricing</span></p>
			</div>	
			
			<div class="wid3" style="overflow-y:auto; height:auto;">
				<h2 class="back3">Application Integration(API)</h2>
				<p><span>Call For Pricing</span></p>
			</div>	
			
			<div class="wid3" style="overflow-y:auto; height:auto;">
				<h2 class="back3">Database Integration</h2>
				<p><span>Call For Pricing</span></p>
			</div>	
			</div>
			
			 
		</div>
	</div>

	
			
	<div class="pricing_table_wdg tab-pane fade" id="vendor">
	<div class="container">
			<ul class="active">
				<li style="background:#01bf9d;">Basic</li>
				<li>$99/<span style="font-size:12px;">month</span></li>
				<li style="background:#000; padding:5px 7px; color:#fff;">One Time Setup Cost<br><span style="font-size:13px;">-</span></li>
				<!--<li>Perfect for those getting started with our app.</li>-->
				<li>Features</li>
				<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont20');">Managed Service Process</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont21');">Functional and Process Management</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont22');">Business Intelligence & Analytics</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont23');">Business Architecture Management</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont24');">Product Training and Support</a></li>
				
				<li style="border:none"><a style="width:38%; float:left; margin:5px 5px;background:#01bf9d;" href="signUp.html?signUpProcess=pricingDetails" class="buy_now">Try Now</a><a style="width:40%; float:left; margin:5px;" href="" class="buy_now">Buy Now</a></li>
				<!--<li><a href="" class="buy_now">Buy Now</a></li>-->
			</ul>
			
			<ul>
				<li style="background:coral;">Silver</li>
				<li>$299<span style="font-size:12px;">month</span></li>
				<li style="background:#000; padding:5px 7px; color:#fff;">One Time Setup Cost<br><span style="font-size:13px;">$499</span></li>
				<!--<li>Perfect for those getting started with our app.</li>-->
				<li>Features</li>
				<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont25');">Managed Service Process</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont26');">Functional and Process Management</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont27');">Business Intelligence</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont28');">Business Architecture Management</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont29');">Product Training and Support</a></li>
				<li style="border:none"><a style="width:50%; float:left; margin:10px 50px;" href="signUp.html?signUpProcess=pricingDetails&getProductPrice=299" class="buy_now">Buy Now</a></li>
			</ul>
			
			<ul>
				<li style="background:goldenrod;">Gold</li>
				<li>$1249<span style="font-size:12px;">month</span></li>
				<li style="background:#000; padding:5px 7px; color:#fff;">One Time Setup Cost<br><span style="font-size:13px;">$1499</span></li>
				<!--<li>Perfect for those getting started with our app.</li>-->
				<li>Features</li>
				<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont30');">Managed Service Process</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont31');">Functional and Process Management</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont32');">Business Intelligence</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont33');">Business Architecture Management</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont34');">Product Training and Support</a></li>
				<li style="border:none"><a style="width:50%; float:left; margin:10px 50px;" href="signUp.html?signUpProcess=pricingDetails&getProductPrice=1249" class="buy_now">Buy Now</a></li>
			</ul>
			
			<ul>
				<li style="background:palevioletred;">Platinum</li>
				<li>$4999/<span style="font-size:12px;">month</span></li>
				<li style="background:#000; padding:5px 7px; color:#fff;">One Time Setup Cost<br><span style="font-size:13px;">$2999</span></li>
				<!--<li>Perfect for those getting started with our app.</li>-->
				<li>Features</li>
				<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont35');">Managed Service Process</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont36');">Functional and Process Management</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont37');">Business Intelligence</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont38');">Business Architecture Management</a></li>
			<li><a style="background:none; border:none; padding:0; margin:0;" href="javascript:void(0);" onClick="showPop('popCont39');">Product Training and Support</a></li>
				<li style="border:none"><a style="width:50%; float:left; margin:10px 50px;" href="4999" class="buy_now">Buy Now</a></li>
			</ul>
			
			<ul>
				<li style="background:#334960;">Enterprise</li>
				<li>-/<span style="font-size:12px;">month</span></li>
				<li style="background:#000; padding:5px 7px; color:#fff;">One Time Setup Cost<br><span style="font-size:13px;">-</span></li>
				<li>Features</li>
				<li style="border-bottom:none; font-weight:bold; color:red;">Call For Pricing</a></li>
			
			</ul>
			
			<div class="popCont">
				<div class="wid" style="overflow-y: auto;
		height: 220px;">
					<h2 class="back">Users</h2>
					<p>Account Manager<span>1</span></p>
				<p>Recuriter<span>5</span></p>
				<p>HR Maganer<span>1</span></p>
				<p>Finance<span>1</span></p>
				<p>Admin<span>1</span></p>
				<p>Corporate User<span>1</span></p>
				<p>Business Head User<span>1</span></p>
				<p>Candidate<span>250</span></p>
				</div>
				<div class="wid" style="overflow-y: auto;
		height: 220px;">
					<h2 class="back">Immigration Compliance Management</h2>
				<p><span>-</span></p>
					
				</div>
				<div class="wid" style="overflow-y: auto;
		height: 220px;">
					<h2 class="back">Sub- Vendor Management</h2>
				<p>No. of Sub-Vendor<span>100</span></p>
					
				</div>
				<div class="wid" style="overflow-y: auto;
		height: 100px;">
					<h2 class="back">Client Management</h2>
				<p><span>Included</span></p>
				</div>
				<div class="wid" style="overflow-y: auto;
		height: 100px;">
					<h2 class="back">Campaign Management</h2>
				<p>No. of E-mails Campagins to Sub-vendors<span>25</span></p>
				<p>No. of E-mails<span>10000</span></p>
				</div>
				<div class="wid" style="overflow-y: auto;
		height: 100px;">
					<h2 class="back">Recruitment Screening Process</h2>
					<p><span>Included</span></p>
				</div>
				<div class="wid" style="overflow-y: auto;
		height: 100px;">
					<h2 class="back">Storage</h2>
					<p><span>50GB</span></p>
				</div>
			</div>
			<div class="popCont" id="popCont21" style="padding: 15px 0 0 30px;">
			<div class="wid" style="overflow-y: auto;">
				<h2 class="back">ERP</h2>
			<p>No of Modules<span>2</span></p>
			<p>No of Forms<span>10</span></p>
			<p>No of Screens<span>40</span></p>
			</div>
			<div class="wid" style="overflow-y: auto;">
					<h2 class="back">Business Services</h2>
				<p>No of Provider Services<span>2</span></p>
				<p>No of Consumer Services<span>6</span></p>
				<p>No of Service LifeCycle Design Allowed<span>1</span></p>
			</div>
			<div class="wid" style="overflow-y: auto;">
					<h2 class="back">Process Automation</h2>
				<p>No of Single Instance Process<span>2</span></p>
				<p>No of Multi-Instance Process<span>1</span></p>
				<p>No of Process LifeCycle Design Allowed<span>1</span></p>
			</div>
			</div>
			<div class="popCont" id="popCont22" style="padding: 20px 20px 0; height:320px;">
			<div class="wid" style="width:100%; overflow-y: auto;">
				<h2 class="back">Business intelligence & Analytics</h2>
				<p>No. of Business Intelligence & Analytics Modeler Session<span>-</span></p>
			<p>No. of Business Intelligence & Analytics Consumer Session<span>1</span></p>
			</div>	
			</div>
			<div class="popCont" id="popCont23" style="height:300px;">
			<div class="wid">
				<h2 class="back">Asset/ Artifacts management & Governance</h2>
			<p>No. of un-governed Assets/Artifacts<span>100</span></p>
			<p>No. of Governed Assets/Artifacts<span>-</span></p>
			</div>	
			<div class="wid">
				<h2 class="back">Business Architecture Modeling Bundle</h2>
				<p>No. of BA LifeCycle Design Allowed<span>-</span></p>
			<p>No. of Business Mapping Allowed<span>-</span></p>
			<p>No. of Business of Process Units aligned<span>-</span></p>
			<p>No. of Business Reference Architecture<span>-</span></p>
			<p>No. of Blue Prints<span>-</span></p>
			</div>	
			<div class="wid">
				<h2 class="back">Enterprise Architecture Planning Bundle</h2>
				<p>No. of EA frameworks<span>-</span></p>
			<p>No. of EA LifeCycle Design Allowed<span>-</span></p>
			<p>No. of Technical Reference Architecture<span>-</span></p>
			<p>No. of Business Processes  Alignment<span>-</span></p>
			<p>No. of  Business Services Alignment<span>-</span></p>
			<p>No. of  Web/technical Services Alignment<span>-</span></p>
			<p>No. of  Application component Alignment<span>-</span></p>
			</div>
			</div>
			<div class="popCont" id="popCont24">
			<div class="wid" style="overflow-y:auto;">
				<h2 class="back">Remote Technical Support & Training</h2>
				<p>Technical Support - Forum, FAQ & Email<span>FREE 24/7</span></p>
			<p>Remote Training & Technical Support - Telephone, Chat, Remote Login & WebEx<span>-</span></p>
			<p>Training & Technical Support - Onsite<span>-</span></p><p>Technical Support - Forum, FAQ & Email<span>FREE 24/7</span></p>
			<p>Remote Training & Technical Support - Telephone, Chat, Remote Login & WebEx<span>-</span></p>
			<p>Training & Technical Support - Onsite<span>-</span></p>
				
			</div>	
			<div class="wid" style="overflow-y:auto;">
				<h2 class="back">Business Engineering Technology Support</h2>
				<p>Technical Support - Forum, FAQ & Email<span>FREE 24/7</span></p>
			<p>Remote Training & Technical Support - Telephone, Chat, Remote Login & WebEx<span>-</span></p>
			<p>Training & Technical Support - Onsite<span>-</span></p>
				
			</div>
			<div class="wid" style="overflow-y:auto; height:auto;">
				<h2 class="back">Application Integration(API)</h2>
				<p><span>-</span></p>
			</div>	
			
			<div class="wid" style="overflow-y:auto; height:auto;">
				<h2 class="back">Database Integration</h2>
				<p><span>-</span></p>
			</div>	
			</div>
			
			
			<div class="popCont" id="popCont25">
			<div class="wid1" style="height:225px;">
				<h2 class="back1">Users</h2>
				<p>Account Manager<span>25</span></p>
				<p>Recuriter<span>25</span></p>
				<p>HR Maganer<span>5</span></p>
				<p>Finance<span>5</span></p>
				<p>Admin<span>5</span></p>
				<p>Corporate User<span>1500</span></p>
				<p>Business Head User<span>5</span></p>
				<p>Candidate<span>5</span></p>
				
			</div>		
			<div class="wid1" style="overflow-y: auto; height:225px;">
					<h2 class="back1">Immigration Compliance Management</h2>
				<p><span>Included</span></p>
				
			</div>	
			<div class="wid1" style="overflow-y: auto; height:225px;">
				<h2 class="back1">Vendor Management</h2>
				
				<p>No. of Sub-Vendors<span>1000</span></p>
	
	
				
			</div>	
			<div class="wid1" style="overflow-y: auto; height:100px;">
					<h2 class="back1">Client Management</h2>
					<p><span>Included</span></p>
				</div>
				<div class="wid1" style="overflow-y: auto; height:100px;">
					<h2 class="back1">Campaign Management</h2>
					<p>No. of E-mail Campaigns to Sub-Vendors<span>150</span></p>
					<p>No. of E-mails<span>60000</span></p>
				</div>
				<div class="wid1" style="overflow-y: auto; height:100px;">
					<h2 class="back1">Recruitment Screening Process</h2>
				<p><span>Include</span></p>
				</div>	
				<div class="wid1" style="overflow-y: auto; height:100px;">
					<h2 class="back1">Storage</h2>
				<p><span style="margin-left:7%;">One Storage Bundle Included</span></p>
				</div>	
			</div>
			<div class="popCont" id="popCont26" style="padding: 15px 0 0 30px;">
			<div class="wid1" style="overflow-y: auto;">
				<h2 class="back1">ERP</h2>
				<p>No of Modules<span>2</span></p>
			<p>No of Forms<span>10</span></p>
			<p>No of Screens<span>40</span></p>
			
				
			</div>	
			
			<div class="wid1" style="overflow-y: auto;">
				<h2 class="back1">Business Services</h2>
				<p>No of Provider Services<span>30</span></p>
			<p>No of Consumer Services<span>100</span></p>
			<p>No of Service LifeCycle Design Allowed<span>2</span></p>
				
			</div>	
			
			<div class="wid1" style="overflow-y: auto;">
				<h2 class="back1">Process Automation</h2>
				<p>No of Single Instance Process<span>15</span></p>
			<p>No of Multi-Instance Process<span>5</span></p>
			<p>No of Process LifeCycle Design Allowed<span>2</span></p>
				
			</div>	
			
			
			</div>
			
			
			<div class="popCont" id="popCont27" style="padding: 20px 20px 0; height:320px;">
				
			<div class="wid1" style="width:100%; overflow-y: auto;">
				<h2 class="back1">Business intelligence & Analytics</h2>
				<p>No. of Business Intelligence & Analytics Modeler Session<span>1</span></p>
			<p>No. of Business Intelligence & Analytics Consumer Session<span>5</span></p>
			</div>	
			
			</div>
			
			<div class="popCont" id="popCont28" style="height:300px;">
					
			<div class="wid1">
				<h2 class="back1">Asset/ Artifacts management & Governance</h2>
			<p>No. of un-governed Assets/Artifacts<span>500</span></p>
			<p>No. of Governed Assets/Artifacts<span>50</span></p>
			</div>	
			
			<div class="wid1">
				<h2 class="back1">Business Architecture Modeling Bundle</h2>
				<p>No of BA LifeCycle Design Allowed<span>1</span></p>
			<p>No of Business Mapping Allowed<span>4</span></p>
			<p>No of Business of Process Units aligned<span>1</span></p>
			<p>No of Business Reference Architecture<span>1</span></p>
			<p>No of Blue Prints<span>1</span></p>
			</div>	
			
			<div class="wid1">
				<h2 class="back1">Enterprise Architecture Planning Bundle</h2>
				<p>No of EA frameworks<span>1</span></p>
			<p>No of EA LifeCycle Design Allowed<span>1</span></p>
			<p>No of Technical Reference Architecture<span>1</span></p>
			<p>No of Business Processes  Alignment<span>1</span></p>
			<p>No of  Business Services Alignment<span>5</span></p>
			<p>No of  Web/technical Services Alignment<span>20</span></p>
			<p>No of  Application component Alignment<span>100</span></p>
			</div>	
			
			</div>
			
			<div class="popCont" id="popCont29">
			
			<div class="wid1" style="overflow-y:auto; height:260px;">
				<h2 class="back1">Remote Technical Support & Training</h2>
				<p>Technical Support - Forum, FAQ & Email<span>FREE 24/7</span></p>
			<p>Remote Training & Technical Support - Telephone, Chat, Remote Login & WebEx<span>One Remote Training & Support Bundle Included</span></p>
			<p>Training & Technical Support - Onsite<span>Call For Pricing</span></p>
			</div>	
			
			<div class="wid1" style="overflow-y:auto; height:260px;">
				<h2 class="back1">Business Engineering Technology Support</h2>
				<p>Online Support - Forum, FAQ & Email<span>FREE 24/7</span></p>
			<p>Remote BET Support - Telephone, Chat, Remote Login & WebEx<span>-</span></p>
			<p>BET Support - Onsite<span>Call For Pricing</span></p>
			</div>	
			
			<div class="wid1" style="overflow-y:auto; height:auto;">
				<h2 class="back1">Application Integration(API)</h2>
				<p><span>Call For Pricing</span></p>
			</div>	
			
			<div class="wid1" style="overflow-y:auto; height:auto;">
				<h2 class="back1">Database Integration</h2>
				<p><span>Call For Pricing</span></p>
			</div>	
			
			</div>
			
			<div class="popCont" id="popCont30">
			<div class="wid2" style="overflow-y: auto; height:225px;">
			<h2 class="back2">ERP</h2>		
			
				<p>Account Manager<span>75</span></p>
				<p>Recuriter<span>75</span></p>
				<p>HR Maganer<span>15</span></p>
				<p>Finance<span>15</span></p>
				<p>Admin<span>15</span></p>
				<p>Corporate User<span>4500</span></p>
				<p>Business Head User<span>15</span></p>
				<p>Candidate<span>15</span></p>
				
			</div>		
			<div class="wid2" style="overflow-y: auto; height:225px;">
				<h2 class="back2">Immigration Compliance Management</h2>
				<p><span>Included</span></p>
				
			</div>	
			<div class="wid2" style="overflow-y: auto; height:225px;">
				<h2 class="back2">Sub-Vendor Management</h2>				
			    <p>No. of Sub-Vendors<span>2000</span></p>	
	
	
				
			</div>	
			<div class="wid2" style="overflow-y: auto; height:100px;">
					<h2 class="back2">Client Management</h2>
				<p><span>Included</span></p>
				</div>
				<div class="wid2" style="overflow-y: auto; height:100px;">
					<h2 class="back2">Campaign Management</h2>
				<p>No. of E-mail Campagins to Sub-Vendors<span>750</span></p>
				<p>No. of E-mails<span>300000</span></p>
				</div>
				<div class="wid2" style="overflow-y: auto; height:100px;">
					<h2 class="back2">Recruitment Screening Process</h2>
				<p><span>Included</span></p>
				</div>
				
				<div class="wid2" style="overflow-y: auto; height:100px;">
						<h2 class="back2">Storage</h2>
				<p><span>Five Storage Bundle Included</span></p>
				</div>
			</div>
			
			
			<div class="popCont" id="popCont31">
				<div class="wid2" style="overflow-y: auto;">
				<h2 class="back2">ERP</h2>
				<p>No of Modules<span>10</span></p>
			<p>No of Forms<span>50</span></p>
			<p>No of Screens<span>200</span></p>
				
			</div>	
			
			<div class="wid2" style="overflow-y: auto;">
				<h2 class="back2">Business Services</h2>
				<p>No of Provider Services<span>2</span></p>
			<p>No of Consumer Services<span>6</span></p>
			<p>No of Service LifeCycle Design Allowed<span>1</span></p>
				
			</div>	
			
			<div class="wid2" style="overflow-y: auto;">
				<h2 class="back2">Process Automation</h2>
				<p>No of Single Instance Process<span>2</span></p>
			<p>No of Multi-Instance Process<span>1</span></p>
			<p>No of Process LifeCycle Design Allowed<span>1</span></p>
				
			</div>	
			</div>
			
			
			<div class="popCont" id="popCont32">
			<div class="wid2" style="width:100%; overflow-y: auto;">
				<h2 class="back2">Business intelligence & Analytics</h2>
				<p>No of Business Intelligence & Analytics Modeler Session<span>1</span></p>
			<p>No of Business Intelligence & Analytics Consumer Session<span>5</span></p>
			</div>	
			</div>
			
			
			<div class="popCont" id="popCont33">
				<div class="wid2" style="height:305px; overflow-y: auto;">
				<h2 class="back2">Asset/ Artifacts management & Governance</h2>
				<p>No of un-governed Assets/Artifacts<span>500</span></p>
			<p>No of Governed Assets/Artifacts<span>50</span></p>
			</div>	
			
			<div class="wid2" style="height:305px; overflow-y: auto;">
				<h2 class="back2">Business Architecture Modeling Bundle</h2>
				<p>No of BA LifeCycle Design Allowed<span>5</span></p>
			<p>No of Business Mapping Allowed<span>20</span></p>
			<p>No of Business of Process Units aligned<span>5</span></p>
			<p>No of Business Reference Architecture<span>3</span></p>
			<p>No of Blue Prints<span>5</span></p>
			</div>	
			
			<div class="wid2" style="height:305px; overflow-y: auto;">
				<h2 class="back2">Enterprise Architecture Planning Bundle</h2>
				<p>No of EA frameworks<span>2</span></p>
			<p>No of EA LifeCycle Design Allowed<span>5</span></p>
			<p>No of Technical Reference Architecture<span>5</span></p>
			<p>No of Business Processes  Alignment<span>5</span></p>
			<p>No of  Business Services Alignment<span>25</span></p>
			<p>No of  Web/technical Services Alignment<span>100</span></p>
			<p>No of  Application component Alignment<span>500</span></p>
			</div>	
			</div>
			
			
			<div class="popCont" id="popCont34">
				<div class="wid2" style="overflow-y:auto; height:260px;">
				<h2 class="back2">Remote Technical Support & Training</h2>
				<p>Technical Support - Forum, FAQ & Email<span>FREE 24/7</span></p>
			<p>Remote Training & Technical Support - Telephone, Chat, Remote Login & WebEx<span>Three Remote Training & Support Bundle Included</span></p>
			<p>Training & Technical Support - Onsite<span>Call For Pricing</span></p>

			</div>	
			
			<div class="wid2" style="overflow-y:auto; height:260px;">
				<h2 class="back2">Business Engineering Technology Support</h2>
				<p>Online Support - Forum, FAQ & Email<span>FREE 24/7</span></p>
			<p>Remote BET Support - Telephone, Chat, Remote Login & WebEx<span>-</span></p>
			<p>BET Support - Onsite<span>Call For Pricing</span></p>
			</div>	
			
			<div class="wid2" style="overflow-y:auto; height:auto;">
				<h2 class="back2">Application Integration(API)</h2>
				<p><span>Call For Pricing</span></p>
			</div>	
			
			<div class="wid2" style="overflow-y:auto; height:auto;">
				<h2 class="back2">Database Integration</h2>
				<p><span>Call For Pricing</span></p>
			</div>
			</div>
			
			
			
				
			<div class="popCont" id="popCont35">
				
			<div class="wid3" style="overflow-y: auto; height:225px;">
				<h2 class="back3">Users</h2>
			<p>Account Manager<span>375</span></p>
				<p>Recuriter<span>375</span></p>
				<p>HR Maganer<span>75</span></p>
				<p>Finance<span>75</span></p>
				<p>Admin<span>75</span></p>
				<p>Corporate User<span>22500</span></p>
				<p>Business Head User<span>75</span></p>
				<p>Candidate<span>75</span></p>
				
			</div>		
			<div class="wid3" style="overflow-y: auto; height:225px;">
				<h2 class="back3">Immigration Compliance Management</h2>
			<p><span>Included</span></p>
				
			</div>	
			<div class="wid3" style="overflow-y: auto; height:225px;">
				<h2 class="back3">Sub-Vendor Management</h2>
			<p>No. of Sub-Vendors<span>5000</span></p>
	
				
			</div>	
			<div class="wid3" style="overflow-y: auto; height:100px;">
					<h2 class="back3">Client Management</h2>
				<p><span>Included</span></p>
				</div>
				<div class="wid3" style="overflow-y: auto; height:100px;">
					<h2 class="back3">Subchapter</h2>
					<p><span>Five respective subchapter bundle included</span></p>
				</div>
				<div class="wid3" style="overflow-y: auto; height:100px;">
				<h2 class="back3">Campaign Management</h2>
				<p>No. of E-mail Campagins to Sub-Vendors<span>3000</span></p>
				<p>No. of E-mails<span>1200000</span></p>
				</div>
				
				<div class="wid3" style="overflow-y: auto; height:100px;">
					<h2 class="back3">Recruitment Screening Process</h2>
				<p><span>Included</span></p>
				</div>
				
				<div class="wid3" style="overflow-y: auto; height:100px;">
					<h2 class="back3">Storage</h2>
				<p>Storage Space<span>Fifteen Storage Bundle Included</span></p>
				</div>
			
			</div>
			<div class="popCont" id="popCont36" style="padding: 15px 0 0 30px;">
			<div class="wid3" style="overflow-y: auto;">
				<h2 class="back3">ERP</h2>
				<p>No. of Modules<span>30</span></p>
				<p>No. of Forms<span>150</span></p>
				<p>No. of Screens<span>600</span></p>
				
			</div>	
			
			<div class="wid3" style="overflow-y: auto;">
				<h2 class="back3">Business Services</h2>
				<p>No. of Provider Services<span>10</span></p>
				<p>No. of Consumer Services<span>30</span></p>
				<p>No of Service LifeCycle Design Allowed<span>5</span></p>
				
			</div>	
			
			<div class="wid3" style="overflow-y: auto;">
				<h2 class="back3">Process Automation</h2>
				<p>No of Single Instance Process<span>10</span></p>
				<p>No of Multi-Instance Process<span>5</span></p>
				<p>No of Process LifeCycle Design Allowed<span>5</span></p>
				
			</div>	
			</div>
			
			<div class="popCont" id="popCont37" style="padding: 20px 20px 0; height:320px;">
						
			<div class="wid3" style="width:100%; overflow-y: auto;">
				<h2 class="back3">Business intelligence & Analytics</h2>
				<p>No of Business Intelligence & Analytics Modeler Session<span>5</span></p>
				<p>No of Business Intelligence & Analytics Consumer Session<span>25</span></p>
			</div>	
			
			</div>
			
			<div class="popCont" id="popCont38">
			<div class="wid3" style="height:305px; overflow-y: auto;">
				<h2 class="back3">Asset/ Artifacts management & Governance</h2>
				<p>No of un-governed Assets/Artifacts<span>2500</span></p>
				<p>No of Governed Assets/Artifacts<span>250</span></p>
			</div>	
			
			<div class="wid3" style="height:305px; overflow-y: auto;">
				<h2 class="back3">Business Architecture Modeling Bundle</h2>
				<p>No of BA LifeCycle Design Allowed<span>25</span></p>
				<p>No of Business Mapping Allowed<span>100</span></p>
				<p>No. of Business of Process Units aligned<span>25</span></p>
				<p>No. of Business Reference Architecture<span>15</span></p>
				<p>No of Blue Prints<span>25</span></p>
			</div>	
			
			<div class="wid3" style="height:305px; overflow-y: auto;">
				<h2 class="back3">Enterprise Architecture Planning Bundle</h2>
				<p>No of EA frameworks<span>10</span></p>
				<p>No of EA LifeCycle Design Allowed<span>25</span></p>
				<p>No. of Technical Reference Architecture<span>25</span></p>
				<p>No of Business Processes  Alignment<span>25</span></p>
				<p>No of  Business Services Alignment<span>125</span></p>
				<p>No of  Web/technical Services Alignment<span>500</span></p>
				<p>No of  Application component Alignment<span>2500</span></p>
			</div>	
			</div>
			<div class="popCont" id="popCont39">
			<div class="wid3" style="overflow-y:auto; height:260px;">
				<h2 class="back3">Remote Technical Support & Training</h2>
				<p>Technical Support - Forum, FAQ & Email<span>FREE 24/7</span></p>
				<p>Remote Training & Technical Support - Telephone, Chat, Remote Login & WebEx<span>Seven Remote Training & Support Bundle Included</span></p>
				<p>Training & Technical Support - Onsite<span>Call For Pricing</span></p>
			</div>	
			
			<div class="wid3" style="overflow-y:auto; height:260px;">
				<h2 class="back3">Business Engineering Technology Support</h2>
				<p>Online Support - Forum, FAQ & Email<span>FREE 24/7</span></p>
				<p>Remote BET Support - Telephone, Chat, Remote Login & WebEx<span>One Remote BET Support Bundle Included</span></p>
				<p>BET Support - Onsite<span>Call For Pricing</span></p>
			</div>	
			
			<div class="wid3" style="overflow-y:auto; height:auto;">
				<h2 class="back3">Application Integration(API)</h2>
				<p><span>Call For Pricing</span></p>
			</div>	
			
			<div class="wid3" style="overflow-y:auto; height:auto;">
				<h2 class="back3">Database Integration</h2>
				<p><span>Call For Pricing</span></p>
			</div>	
			</div>
			
			</div>
		
	</div>
	
	
	
	</div>
		
<br style="clear:both;" />

</div>

<h2 style="text-align:center; margin-top:40px;">BASIC PACKAGE START WITH 30 DAYS FREE TRIAL</h2>
</section></div>
<div>			 
			
                    <!-- FOOTER STARTS HERE -->
                        
						<%@ include file = "../../include/FooterProcess.jsp" %>
                    <!-- FOOTER ENDS HERE -->
               
 </div>

</body>

</html>

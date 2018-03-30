<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
<title>Untitled Document</title>
<script type="text/javascript">
function toggle(div_id) {
    var el = document.getElementById(div_id);
    if ( el.style.display == 'none' ) { el.style.display = 'block';}
    else {el.style.display = 'none';}
}
function blanket_size(popUpDivVar) {
    if (typeof window.innerWidth != 'undefined') {
        viewportheight = window.innerHeight;
    } else {
        viewportheight = document.documentElement.clientHeight;
    }
    if ((viewportheight > document.body.parentNode.scrollHeight) && (viewportheight > document.body.parentNode.clientHeight)) {
        blanket_height = viewportheight;
    } else {
        if (document.body.parentNode.clientHeight > document.body.parentNode.scrollHeight) {
            blanket_height = document.body.parentNode.clientHeight;
        } else {
            blanket_height = document.body.parentNode.scrollHeight;
        }
    }
    var blanket = document.getElementById('blanket');
    blanket.style.height = blanket_height + 'px';
    var popUpDiv = document.getElementById(popUpDivVar);
    popUpDiv_height=blanket_height/2-200;//200 is half popup's height
    popUpDiv.style.top = popUpDiv_height + 'px';
}
function window_pos(popUpDivVar) {
    if (typeof window.innerWidth != 'undefined') {
        viewportwidth = window.innerHeight;
    } else {
        viewportwidth = document.documentElement.clientHeight;
    }
    if ((viewportwidth > document.body.parentNode.scrollWidth) && (viewportwidth > document.body.parentNode.clientWidth)) {
        window_width = viewportwidth;
    } else {
        if (document.body.parentNode.clientWidth > document.body.parentNode.scrollWidth) {
            window_width = document.body.parentNode.clientWidth;
        } else {
            window_width = document.body.parentNode.scrollWidth;
        }
    }
    var popUpDiv = document.getElementById(popUpDivVar);
    window_width=window_width/2-200;//200 is half popup's width
    popUpDiv.style.left = window_width + 'px';
}
function popup(windowname) {
    blanket_size(windowname);
    window_pos(windowname);
    toggle('blanket');
    toggle(windowname);     
}
</script>
<style>
	.box1 {
  width: 40%;
  margin: 0 auto;
  background: rgba(255,255,255,0.2);
 <!-- border: 2px solid #fff; -->
  border-radius: 20px/50px;
  background-clip: padding-box;
  text-align: center;
}


.button {
   font-size: 1.5em!important;
    padding: 11px 57px 38px 22px;
    color: #ffffff;
    border: -36psolid #06D85F;
    border-radius: 0px;
    text-decoration: none;
    cursor: pointer;
    transition: all 0.3s ease-out;
    position: fixed;
    width: 50px;
    height: 43px;
    top: 300px;
    transform: rotate(90deg);
    z-index: 1000;
    right: -17px!important;
    background: #06D85F;
    font-family: 'Open Sans', sans-serif!important;
    right: 0;
    font-weight: bold;
}
.button:hover {
    border: 2px solid #06D85F;
    color: #06D85F;
    text-decoration: none;
    background-color: #FFFFFF;

}
.overlay {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.7);
  transition: opacity 500ms;
  visibility: hidden;
  opacity: 0;
  z-index:999;
}
.overlay:target {
  visibility: visible;
  opacity: 1;
}

.popup {
  margin: 250px auto;
  padding: 20px;
  background: #fff;
  border-radius: 5px;
  width: 30%;
  position: relative;
  
}

.popup h2 {
  margin-top: 0;
  color: #333;
  font-family: Tahoma, Arial, sans-serif;
}
.popup .close {
  position: absolute;
  top: 20px;
  right: 26px;
  transition: all 200ms;
  font-size: 30px;
  font-weight: bold;
  text-decoration: none;
  color: #333;
}
a.hov {
    color: #fff;
}
a.hov:hover {
    color: red;
}
.popup .close:hover {
  color: #06D85F;
}
.popup .content {
  max-height: 30%;
  overflow: auto;
}
@media screen and (max-width: 700px){
  .box1{
    width: 70%;
  }
  .popup{
    width: 70%;
  }
 .follow_icon_foo {
	padding: 0;
	margin: -4px 0 0 0;
	list-style: none;
	display: inline-block;
}

.h6 {
    font-size: 21px;
    color: #fff;
}
.circleBase {
    border-radius: 50%;
    behavior: url(PIE.htc); /* remove if you don't care about IE8 */
}

.type1 {
    width: 10px;
    height: 10px;
    background: yellow;
    border: 3px solid red;
}
 
</style>
<%
	String userCompanyDetFooter = (String) session.getAttribute("userCompanyCode");
	String matchCompanyDetFooter = null;
	matchCompanyDetFooter = (String) session.getAttribute("matchCompanyDet");
	System.out.println("userCompanyDetFooter:::::::::::::::::::::"+userCompanyDetFooter);
	System.out.println("matchCompanyDetFooter:::::::::::::::::::::"+matchCompanyDetFooter);
%>
</head>
<body>
<div class="box1">
		<a class="button" href="#popup1">Chat</a>
	</div>
	
	<div id="popup1" class="overlay">
		<div class="popup" >
		<a href="#"><%@ include file = "../application.jsp" %></a>
			<a class="close" href="#">&times;</a>
			<div class="content">
				
			</div>
		</div>
	</div>
    
     
			<%if((matchCompanyDetFooter != null) && matchCompanyDetFooter.equalsIgnoreCase(userCompanyDetFooter) && matchCompanyDetFooter.equalsIgnoreCase("digiblitz")){ %>
			<div class="back" style=" padding:0px">
			<div class="container" style="padding:6px 0; ">
			
			<div class="col-md-1">
				<img style="margin-top:40px;" src="img/DB-Logo-new.png" alt="" width="80" height="60" align="left"></img>
			</div>
			
			
			<div class="col-md-5 linkk">
			<h6>Address</h6>
			<ul class="list" style="list-style-type:none; padding:0px">
				<li><i class="fa fa-home" style="color:#FFFFFF"></i><a>13241 WOODLAND PARK ROAD, SUITE 110,<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;HERNDON, VIRGINIA 20171, USA</a></li>
			    <li><i class="fa fa-reply" style="color:#FFFFFF"></i><a href="mailto:sales@menschforce.com">Sales - sales@menschforce.com</a></li>
				<li><i class="fa fa-reply" style="color:#FFFFFF"></i><a href="mailto:billing@menschforce.com">Billing & Finance - billing@menschforce.com</a></li>
				<li><i class="fa fa-reply" style="color:#FFFFFF"></i><a href="mailto:support@menschforce.com">Customer Service - support@menschforce.com</a></li>
			    
			
			</ul>
			</div>
			
				<div class="col-md-4 link" style="margin-bottom:1px; position:relative; bottom:0px; padding:50px">
					<div class="col-md-12" style=" margin-left:-116px"> 
					 <p class="privacy wow fadeInUp"  style="color:#fff; margin-bottom:1px; font-size:16px">
					<em id="copyright-year" style="color:#fff; font-size:16px"></em> <span id="copyright-year" style="color:#fff; font-size:16px"></span>&nbsp;&copy;&nbsp;Powered by <a href="https://www.digiblitz.com" target="_blank" style="color:#fff; text-decoration:underline; font-weight:600; font-size:16px;">digiBlitz</a><br/>
					<a href="user.html?cmd=privacy" target="_blank">Privacy Policy</a>
					</p>
					</div>
					
				</div>
    
				<div class="col-md-2 linkk">
				<h6>Follow Us</h6>
				<ul class="follow_icon_foo wow fadeInUp animated" style="margin-top:10px;">
				
					<li><a href="https://www.facebook.com/MenschForce-621208418017277/" target="_blank"><i class="fa fa-facebook"></i></a></li>
				
					<li><a href="https://www.linkedin.com/company/digiblitz-technologies" target="_blank"><i class="fa fa-linkedin"></i></a></li>
					<li><a href="https://www.twitter.com/digiblitz" target="_blank"><i class="fa fa-twitter"></i></a></li>
					
					
				 </ul>
				</div>
				</div>
				
				
     		</div>
			 <%} else if((matchCompanyDetFooter != null) && matchCompanyDetFooter.equalsIgnoreCase(userCompanyDetFooter) && matchCompanyDetFooter.equalsIgnoreCase("tekivy")){%>
			<div class="back" style=" padding:0px">
			<div class="container" style="padding:6px 0; ">
			
			<div class="col-md-1">
				<!--img style="margin-top:40px;" src="img/DB-Logo-new.png" alt="" width="80" height="60" align="left"></img-->
			</div>
			
			
			<div class="col-md-5 linkk">
			<h6>Address</h6>
			<ul class="list" style="list-style-type:none; padding:0px">
				<li><i class="fa fa-home" style="color:#FFFFFF; "></i><a>400 West Capitol Avenue, Suite 1700.<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Little Rock, 
Arkansas, 72201, USA</a></li>
			    <li><i class="fa fa-reply" style="color:#FFFFFF"></i><a href="mailto:contact@tekivy.com">contact@tekivy.com</a></li>
			    <li><i class="fa fa-phone" style="color:#FFFFFF"></i><a>+1 240-252-1501</a></li>
			
			</ul>
			</div>
			
				<div class="col-md-4 link" style="margin-bottom:1px; position:relative; bottom:0px; padding:50px">
					<div class="col-md-12" style=" margin-left:-116px"> 
					 <p class="privacy wow fadeInUp"  style="color:#fff; margin-bottom:1px; font-size:16px">
					<em id="copyright-year" style="color:#fff; font-size:16px"></em> <span id="copyright-year" style="color:#fff; font-size:16px"></span>&nbsp;&copy;&nbsp;Powered by <a href="https://www.digiblitz.com" target="_blank" style="color:#fff; text-decoration:underline; font-weight:600; font-size:16px;">digiBlitz</a><br/>
					<a href="user.html?cmd=privacy" target="_blank">Privacy Policy</a>
					</p>
					</div>
					
				</div>
    
				<div class="col-md-2 linkk">
				<h6>Follow Us</h6>
				<ul class="follow_icon_foo wow fadeInUp animated" style="margin-top:10px;">
				
					<li><a href="https://www.facebook.com/tekivyinc/" target="_blank"><i class="fa fa-facebook"></i></a></li>
				
					<li><a href="https://www.linkedin.com/company/digiblitz-technologies" target="_blank"><i class="fa fa-linkedin"></i></a></li>
					<li><a href="https://twitter.com/tekIVYInc" target="_blank"><i class="fa fa-twitter"></i></a></li>
					
					
				 </ul>
				</div>
				</div>
				
				
     		</div>
			 <%}else if((matchCompanyDetFooter != null) && matchCompanyDetFooter.equalsIgnoreCase(userCompanyDetFooter) && matchCompanyDetFooter.equalsIgnoreCase("dbenterprise")){ %>
			<div class="back" style=" padding:0px">
			<div class="container" style="padding:6px 0; ">
			
			<div class="col-md-1">
				<img style="margin-top:40px;" src="img/DB-Logo-new.png" alt="" width="80" height="60" align="left"></img>
			</div>
			
			
			<div class="col-md-5 linkk">
			<h6>Address</h6>
			<ul class="list" style="list-style-type:none; padding:0px">
				<li><i class="fa fa-home" style="color:#FFFFFF"></i><a>13241 WOODLAND PARK ROAD, SUITE 110,<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;HERNDON, VIRGINIA 20171, USA</a></li>
			    <li><i class="fa fa-reply" style="color:#FFFFFF"></i><a href="mailto:sales@menschforce.com">Sales - sales@menschforce.com</a></li>
				<li><i class="fa fa-reply" style="color:#FFFFFF"></i><a href="mailto:billing@menschforce.com">Billing & Finance - billing@menschforce.com</a></li>
				<li><i class="fa fa-reply" style="color:#FFFFFF"></i><a href="mailto:support@menschforce.com">Customer Service - support@menschforce.com</a></li>
			    
			
			</ul>
			</div>
			
				<div class="col-md-4 link" style="margin-bottom:1px; position:relative; bottom:0px; padding:50px">
					<div class="col-md-12" style=" margin-left:-116px"> 
					 <p class="privacy wow fadeInUp"  style="color:#fff; margin-bottom:1px; font-size:16px">
					<em id="copyright-year" style="color:#fff; font-size:16px"></em> <span id="copyright-year" style="color:#fff; font-size:16px"></span>&nbsp;&copy;&nbsp;Powered by <a href="https://www.digiblitz.com" target="_blank" style="color:#fff; text-decoration:underline; font-weight:600; font-size:16px;">digiBlitz</a><br/>
					<a href="user.html?cmd=privacy" target="_blank">Privacy Policy</a>
					</p>
					</div>
					
				</div>
    
				<div class="col-md-2 linkk">
				<h6>Follow Us</h6>
				<ul class="follow_icon_foo wow fadeInUp animated" style="margin-top:10px;">
				
					<li><a href="https://www.facebook.com/MenschForce-621208418017277/" target="_blank"><i class="fa fa-facebook"></i></a></li>
				
					<li><a href="https://www.linkedin.com/company/digiblitz-technologies" target="_blank"><i class="fa fa-linkedin"></i></a></li>
					<li><a href="https://www.twitter.com/digiblitz" target="_blank"><i class="fa fa-twitter"></i></a></li>
					
					
				 </ul>
				</div>
				</div>
				
				
     		</div>
			 <%}else{ %>
			<div class="back" style=" padding:0px">
			<div class="container" style="padding:6px 0; ">
			
			<div class="col-md-1">
				<img style="margin-top:40px;" src="img/DB-Logo-new.png" alt="" width="80" height="60" align="left"></img>
			</div>
			
			
			<div class="col-md-5 linkk">
			<h6>Address</h6>
			<ul class="list" style="list-style-type:none; padding:0px">
				<li><i class="fa fa-home" style="color:#FFFFFF"></i><a>13241 WOODLAND PARK ROAD, SUITE 110,<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;HERNDON, VIRGINIA 20171, USA</a></li>
			    <li><i class="fa fa-reply" style="color:#FFFFFF"></i><a href="mailto:sales@menschforce.com">Sales - sales@menschforce.com</a></li>
				<li><i class="fa fa-reply" style="color:#FFFFFF"></i><a href="mailto:billing@menschforce.com">Billing & Finance - billing@menschforce.com</a></li>
				<li><i class="fa fa-reply" style="color:#FFFFFF"></i><a href="mailto:support@menschforce.com">Customer Service - support@menschforce.com</a></li>
			    
			
			</ul>
			</div>
			
				<div class="col-md-4 link" style="margin-bottom:1px; position:relative; bottom:0px; padding:50px">
					<div class="col-md-12" style=" margin-left:-116px"> 
					 <p class="privacy wow fadeInUp"  style="color:#fff; margin-bottom:1px; font-size:16px">
					<em id="copyright-year" style="color:#fff; font-size:16px"></em> <span id="copyright-year" style="color:#fff; font-size:16px"></span>&nbsp;&copy;&nbsp;Powered by <a href="https://www.digiblitz.com" target="_blank" style="color:#fff; text-decoration:underline; font-weight:600; font-size:16px;">digiBlitz</a><br/>
					<a href="user.html?cmd=privacy" target="_blank">Privacy Policy</a>
					</p>
					</div>
					
				</div>
    
				<div class="col-md-2 linkk">
				<h6>Follow Us</h6>
				<ul class="follow_icon_foo wow fadeInUp animated" style="margin-top:10px;">
				
					<li><a href="https://www.facebook.com/MenschForce-621208418017277/" target="_blank"><i class="fa fa-facebook"></i></a></li>
				
					<li><a href="https://www.linkedin.com/company/digiblitz-technologies" target="_blank"><i class="fa fa-linkedin"></i></a></li>
					<li><a href="https://www.twitter.com/digiblitz" target="_blank"><i class="fa fa-twitter"></i></a></li>
					
					
				 </ul>
				</div>
				</div>
				
				
     		</div>
			 <%}%>
			
</body>
</html>

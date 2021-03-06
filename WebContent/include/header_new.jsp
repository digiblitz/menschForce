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
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">

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
<link rel="stylesheet" href="css/contact-form.css">
<!--JS-->
<script src="js/jquery.js"></script>
<script src="js/TMForm.js"></script>
<script src="js/modal.js"></script>
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
<script type="text/javascript" charset="utf-8" src="js/jquery.leanModal.min.js"></script>


<%
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

 %>
 
<!--script type="text/javascript">
//-------------------------------- User status validation Ajax Script ------------------------------------------------

var httpRequest;

function usrStat()
{

if(document.loginform.username.value!="" && document.loginform.username.value.indexOf(' ')!=0)
	{

   var chsUserName=document.loginform.username.value;
alert("test");
   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
        url= "UsrSignupAjax.html?cmd=checkusrnam&chsUserName="+chsUserName; 

        if (window.ActiveXObject) 
        { 
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
        } 
        else if (window.XMLHttpRequest) 
        { 
            httpRequest = new XMLHttpRequest(); 
        } 
     
        httpRequest.open("GET", url, true); 
        
        httpRequest.onreadystatechange =processUser; 
        httpRequest.send(null); 
   } 
   }
   
   
   
    /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function processUser() 
    { 
   
        if (httpRequest.readyState == 4) 
        { 
            if(httpRequest.status == 200) 
            { 
                //get the XML send by the servlet 
                 var salutationXML = httpRequest.responseXML.getElementsByTagName("userstatus")[0]; 
                 var salutationText = salutationXML.childNodes[0].nodeValue; 
     
                //Update the HTML 
                updateHTML(salutationXML); 
            } 
            else 
            { 
                alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
            } 
        } 
    } 
        
   /** 
    * This function parses the XML and updates the  
    * HTML DOM by creating a new text node is not present 
    * or replacing the existing text node. 
    */ 
    function updateHTML(salutationXML) 
    { 
        //The node valuse will give actual data 
        var salutationText = salutationXML.childNodes[0].nodeValue; 

		if(salutationText == "false")
		{
			alert("User Name doesn't Exists!");
			document.loginform.username.value="";
			document.loginform.username.focus();
		}
		      
    } 
   </script-->



<script>
    $(window).load(function() {
        $(function() {$("a.various").fancybox();});
    });
</script>
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
<script type="text/javascript">
$(function(){
  $('#loginform').submit(function(e){
    return true;
  });
  
  $('#modaltrigger').leanModal({ top: 110, overlay: 0.9, closeButton: ".hidemodal" });
  
  $('#modaltrigger1').leanModal({ top: 110, overlay: 0.9, closeButton: ".hidemodal" });
  
  $('#modaltrigger2').leanModal({ top: 110, overlay: 0.9, closeButton: ".hidemodal" });
  
  $('#modaltrigger3').leanModal({ top: 110, overlay: 0.9, closeButton: ".hidemodal" });
  
  $('#modaltrigger4').leanModal({ top: 110, overlay: 0.9, closeButton: ".hidemodal" });
  
  $('#modaltrigger5').leanModal({ top: 110, overlay: 0.9, closeButton: ".hidemodal" });
  
  $('#modaltrigger6').leanModal({ top: 110, overlay: 0.9, closeButton: ".hidemodal" });
});
</script>
<!--[if (gt IE 9)|!(IE)]><!-->
<script src="js/wow/wow.js"></script>
<script src="js/wow/device.min.js"></script>
<script src="js/jquery.mobile.customized.min.js"></script>
<script>
    $(document).ready(function () {       
      if ($('html').hasClass('desktop')) {
        new WOW().init();
      }   
    });
</script>
<script src="js/bootstrap.min.js"></script>
<script src="js/superfish.js"></script>
<script src="js/script.js"></script>
<script src="js/sForm.js"></script> 		

<!--<![endif]-->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>

    <div id="ie6-alert" style="width: 100%; text-align:center;">
    <img src="http://beatie6.frontcube.com/images/ie6.jpg" alt="Upgrade IE 6" width="640" height="344" border="0" usemap="#Map" longdesc="http://die6.frontcube.com" />
      <map name="Map" id="Map"><area shape="rect" coords="496,201,604,329" href="http://www.microsoft.com/windows/internet-explorer/default.aspx" target="_blank" alt="Download Interent Explorer" /><area shape="rect" coords="380,201,488,329" href="http://www.apple.com/safari/download/" target="_blank" alt="Download Apple Safari" /><area shape="rect" coords="268,202,376,330" href="http://www.opera.com/download/" target="_blank" alt="Download Opera" /><area shape="rect" coords="155,202,263,330" href="http://www.mozilla.com/" target="_blank" alt="Download Firefox" />
        <area shape="rect" coords="35,201,143,329" href="http://www.google.com/chrome" target="_blank" alt="Download Google Chrome" />
      </map>
  </div>
  <![endif]-->
</head>
<style type="text/css">
/** resets **/
html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn, em, img, ins, kbd, q, s, samp, small, strike, strong, sub, sup, tt, var, b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas, details, embed, figure, figcaption, footer, header, hgroup, menu, nav, output, ruby, section, summary, time, mark, audio, video {
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  font: inherit;
  vertical-align: baseline;
  outline: none;
  -webkit-font-smoothing: antialiased;
  -webkit-text-size-adjust: 100%;
  -ms-text-size-adjust: 100%;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}
html { height: 101%; }
body { 
  background: #f0f0f0 url('images/bg.gif'); 
  font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
  color: #313131;
  font-size: 62.5%; 
  line-height: 1; 
}

::selection { background: #a4dcec; }
::-moz-selection { background: #a4dcec; }
::-webkit-selection { background: #a4dcec; }

::-webkit-input-placeholder { /* WebKit browsers */
  color: #ccc;
  font-style: italic;
}
:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
  color: #ccc;
  font-style: italic;
}
::-moz-placeholder { /* Mozilla Firefox 19+ */
  color: #ccc;
  font-style: italic;
}
:-ms-input-placeholder { /* Internet Explorer 10+ */
  color: #ccc !important;
  font-style: italic;  
}

br { display: block; line-height: 2.2em; } 

article, aside, details, figcaption, figure, footer, header, hgroup, menu, nav, section { display: block; }
ol, ul { list-style: none; }

input, textarea { 
  -webkit-font-smoothing: antialiased;
  -webkit-text-size-adjust: 100%;
  -ms-text-size-adjust: 100%;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  outline: none; 
}

blockquote, q { quotes: none; }
blockquote:before, blockquote:after, q:before, q:after { content: ''; content: none; }
strong { font-weight: bold; } 

table { border-collapse: collapse; border-spacing: 0; }
img { border: 0; max-width: 100%; }

#topbar {
  background: #4f4a41;
  padding: 10px 0 10px 0;
  text-align: center;
}

#topbar a {
  color: #fff;
  font-size:1.3em;
  line-height: 1.25em;
  text-decoration: none;
  opacity: 0.5;
  font-weight: bold;
}

#topbar a:hover {
  opacity: 1;
}

/** typography **/
h1 {
  font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
  font-size: 2.5em;
  line-height: 1.5em;
  letter-spacing: -0.05em;
  margin-bottom: 20px;
  padding: .1em 0;
  color: #444;
	position: relative;
	overflow: hidden;
	white-space: nowrap;
	text-align: center;
}
h1:before,
h1:after {
  content: "";
  position: relative;
  display: inline-block;
  width: 50%;
  height: 1px;
  vertical-align: middle;
  background: #f0f0f0;
}
h1:before {    
  left: -.5em;
  margin: 0 0 0 -50%;
}
h1:after {    
  left: .5em;
  margin: 0 -50% 0 0;
}
h1 > span {
  display: inline-block;
  vertical-align: middle;
  white-space: normal;
}

p {
  display: block;
  font-size: 1.35em;
  line-height: 1.5em;
  margin-bottom: 22px;
}

.center { display: block; text-align: center; }


/** page structure **/
#w {
  display: block;
  width: 750px;
  margin: 0 auto;
  padding-top: 30px;
}

#content {
  display: block;
  width: 100%;
  background: #fff;
  padding: 25px 20px;
  padding-bottom: 35px;
  -webkit-box-shadow: rgba(0, 0, 0, 0.1) 0px 1px 2px 0px;
  -moz-box-shadow: rgba(0, 0, 0, 0.1) 0px 1px 2px 0px;
  box-shadow: rgba(0, 0, 0, 0.1) 0px 1px 2px 0px;
}


.flatbtn {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  display: inline-block;
  outline: 0;
  border: 0;
  color: #f3faef;
  text-decoration: none;
  background-color: #6bb642;
  border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
  font-size: 1.2em;
  font-weight: bold;
  padding: 12px 22px 12px 22px;
  line-height: normal;
  text-align: center;
  vertical-align: middle;
  cursor: pointer;
  text-transform: uppercase;
  text-shadow: 0 1px 0 rgba(0,0,0,0.3);
  -webkit-border-radius: 3px;
  -moz-border-radius: 3px;
  border-radius: 3px;
  -webkit-box-shadow: 0 1px 0 rgba(15, 15, 15, 0.3);
  -moz-box-shadow: 0 1px 0 rgba(15, 15, 15, 0.3);
  box-shadow: 0 1px 0 rgba(15, 15, 15, 0.3);
}
.flatbtn:hover {
  color: #fff;
  background-color: #73c437;
}
.flatbtn:active {
  -webkit-box-shadow: inset 0 1px 5px rgba(0, 0, 0, 0.1);
  -moz-box-shadow:inset 0 1px 5px rgba(0, 0, 0, 0.1);
  box-shadow:inset 0 1px 5px rgba(0, 0, 0, 0.1);
}

/** custom login button **/
.flatbtn-blu { 
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  display: inline-block;
  outline: 0;
  border: 0;
  color: #edf4f9;
  text-decoration: none;
  background-color: #4f94cf;
  border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
  font-size: 1.3em;
  font-weight: bold;
  padding: 12px 26px 12px 26px;
  line-height: normal;
  text-align: center;
  vertical-align: middle;
  cursor: pointer;
  text-transform: uppercase;
  text-shadow: 0 1px 0 rgba(0,0,0,0.3);
  -webkit-border-radius: 3px;
  -moz-border-radius: 3px;
  border-radius: 3px;
  -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
  -moz-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
}
.flatbtn-blu:hover {
  color: #fff;
  background-color: #519dde;
}
.flatbtn-blu:active {
  -webkit-box-shadow: inset 0 1px 5px rgba(0, 0, 0, 0.1);
  -moz-box-shadow:inset 0 1px 5px rgba(0, 0, 0, 0.1);
  box-shadow:inset 0 1px 5px rgba(0, 0, 0, 0.1);
}


/** modal window styles **/
#lean_overlay {
    position: fixed;
  	opacity:0.5!important;
    top: 0px;
    left: 0px;
    height:100%;
    width:100%;
    background: #000;
    display: none;
}

#loginmodal {
  
  padding: 15px 20px;
  -webkit-border-radius: 6px;
  -moz-border-radius: 6px;
  border-radius: 6px;
  -webkit-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5);
  -moz-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5);
  box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5);
}

#loginmodal1 {
  
  padding: 15px 20px;
  -webkit-border-radius: 6px;
  -moz-border-radius: 6px;
  border-radius: 6px;
  -webkit-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5);
  -moz-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5);
  box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5);
}

#loginmodal2 {
    padding: 15px 20px;
  -webkit-border-radius: 6px;
  -moz-border-radius: 6px;
  border-radius: 6px;
  -webkit-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5);
  -moz-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5);
  box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5);
}

#loginmodal3 {
    padding: 15px 20px;
  -webkit-border-radius: 6px;
  -moz-border-radius: 6px;
  border-radius: 6px;
  -webkit-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5);
  -moz-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5);
  box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5);
}

#loginmodal4 {
    padding: 15px 20px;
  -webkit-border-radius: 6px;
  -moz-border-radius: 6px;
  border-radius: 6px;
  -webkit-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5);
  -moz-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5);
  box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5);
}

#loginmodal5 {
    padding: 15px 20px;
  -webkit-border-radius: 6px;
  -moz-border-radius: 6px;
  border-radius: 6px;
  -webkit-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5);
  -moz-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5);
  box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5);
}



#loginform { /* no default styles */ }

#loginform label { display: block; font-size: 1.1em; font-weight: bold; color: #7c8291; margin-bottom: 3px; }


.txtfield { 
  display: block;
  width: 100%;
  padding: 6px 5px;
  margin-bottom: 15px;
  font-family: 'Helvetica Neue', Helvetica, Verdana, sans-serif;
  color: #7988a3;
  font-size: 1.4em;
  text-shadow: 1px 1px 0 rgba(255, 255, 255, 0.8);
  background-color: #fff;
  background-image: -webkit-gradient(linear, left top, left bottom, from(#edf3f9), to(#fff));
  background-image: -webkit-linear-gradient(top, #edf3f9, #fff);
  background-image: -moz-linear-gradient(top, #edf3f9, #fff);
  background-image: -ms-linear-gradient(top, #edf3f9, #fff);
  background-image: -o-linear-gradient(top, #edf3f9, #fff);
  background-image: linear-gradient(top, #edf3f9, #fff);
  border: 1px solid;
  border-color: #abbce8 #c3cae0 #b9c8ef;
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  border-radius: 4px;
  -webkit-box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.25), 0 1px rgba(255, 255, 255, 0.4);
  -moz-box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.25), 0 1px rgba(255, 255, 255, 0.4);
  box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.25), 0 1px rgba(255, 255, 255, 0.4);
  -webkit-transition: all 0.25s linear;
  -moz-transition: all 0.25s linear;
  transition: all 0.25s linear;
}

.txtfield:focus {
  outline: none;
  color: #525864;
  border-color: #84c0ee;
  -webkit-box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.15), 0 0 7px #96c7ec;
  -moz-box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.15), 0 0 7px #96c7ec;
  box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.15), 0 0 7px #96c7ec;
}

input.button-add {
    background-image: url(images/bg1.jpg); /* 16px x 16px */
    background-color: transparent; /* make the button transparent */
    border: none;           /* assuming we don't want any borders */
    cursor: pointer;        /* make the cursor like hovering over an <a> element */
    height: 20px;           /* make this the size of your image */
    vertical-align: middle; /* align the text vertically centered */
	font-weight:600;
		
}

nav#nav ul li {
    display: inline-block;
    position: relative;
}

nav#nav ul li a {
    
     padding: 0 4px 0px 34px;
    font-size: 13px;
    color: #fff;
    font-weight: 500;
    text-transform: uppercase;
}

nav#nav ul li a.active {
    color: #F13939;
}
nav#nav ul li a:hover{
	color:#F13939;
}

/*.primary_nav_wrap
{
	margin-top:15px
}*/

.primary_nav_wrap ul
{
	list-style:none;
	position:relative;
	float:right;
	
}

.primary_nav_wrap ul a
{
	display: block;
    color: #fff;
    text-decoration: none;
    font-weight: 600;
    font-size: 16px;
    /* line-height: 32px; */
    padding: 0 15px;
    text-transform: uppercase;
}

.primary_nav_wrap ul li
{
	position:relative;
	float:left;
	margin:0;
	padding:0
}

.primary_nav_wrap ul li.current-menu-item
{
	background:none
}

.primary_nav_wrap ul li:hover
{
	background:none;
}

.primary_nav_wrap ul ul
{
	    display: none;
    position: absolute;
    top: 100%;
    left: 0;
    background: #2a2a2a;
    padding: 0;
    border-radius: 10px;
}

.primary_nav_wrap ul ul li
{
	float:none;
	width:120px
}

.primary_nav_wrap ul ul a
{
	padding:9px 15px;
	color:#fff;
	font-size:16px;
	font-size: 14px;
    text-transform: capitalize;
}

.primary_nav_wrap ul ul a:hover
{
	color:#F13939;
}
.primary_nav_wrap ul ul ul
{
	top:0;
	left:100%
}

.primary_nav_wrap ul li:hover > ul
{
	display:block
}

nav#nav {
    margin-top: 35px;
}
.primary_nav_wrap {
    margin-top: 35px;
}

.primary_nav_wrap ul li a span {
    text-align: center;
    position: absolute;
    height: 4px;
    width: 100%;
    display: inline-block;
    left: 0;
    bottom: -9px;
    z-index: 0;
    background: url(../img/marker.png) center 0 no-repeat;
}
.styleError{
	color:#FF0000;
	font-weight:bolder;
}
</style>

<script>
	$(function() {
     var pgurl = window.location.href.substr(window.location.href
.lastIndexOf("/")+1);
     $("nav ul li a").each(function(){
          if($(this).attr("href") == pgurl || $(this).attr("href") == '' )
          $(this).addClass("active");
     })
});

function poupOnBody(){ // On DOM ready

document.getElementById("modaltrigger").click();
};
function poupOnBody1(){ // On DOM ready

document.getElementById("modaltrigger1").click();
};
function poupOnBody2(){ // On DOM ready

document.getElementById("modaltrigger2").click();
};
function poupOnBody3(){ // On DOM ready

	document.getElementById("modaltrigger6").click();
	};
function poupOnBody4(){ // On DOM ready

	document.getElementById("modaltrigger4").click();
	};
function poupOnBody5(){ // On DOM ready

	document.getElementById("modaltrigger5").click();
	};
function test1(){ // On DOM ready
/*
document.getElementById("lean_overlay").style.display = "none";*/

document.getElementById("loginmodal").display = "block";
document.getElementById("loginmodal1").style.display = "none";
document.getElementById("loginmodal2").style.display = "none";
document.getElementById("loginmodal3").style.display = "none";
document.getElementById("loginmodal4").style.display = "none";
document.getElementById("loginmodal5").style.display = "none";

};
function test2(){ // On DOM ready
/*
document.getElementById("lean_overlay").style.display = "none";*/

document.getElementById("loginmodal1").display = "block";
document.getElementById("loginmodal").style.display = "none";
document.getElementById("loginmodal2").style.display = "none";
document.getElementById("loginmodal3").style.display = "none";
document.getElementById("loginmodal4").style.display = "none";
document.getElementById("loginmodal5").style.display = "none";

};
function test3(){ // On DOM ready
/*
document.getElementById("lean_overlay").style.display = "none";*/

document.getElementById("loginmodal2").display = "block";
document.getElementById("loginmodal").style.display = "none";
document.getElementById("loginmodal1").style.display = "none";
document.getElementById("loginmodal3").style.display = "none";
document.getElementById("loginmodal4").style.display = "none";
document.getElementById("loginmodal5").style.display = "none";

};

function test4(){ // On DOM ready
/*
document.getElementById("lean_overlay").style.display = "none";*/

document.getElementById("loginmodal3").display = "block";
document.getElementById("loginmodal").style.display = "none";
document.getElementById("loginmodal1").style.display = "none";
document.getElementById("loginmodal2").style.display = "none";
document.getElementById("loginmodal4").style.display = "none";
document.getElementById("loginmodal5").style.display = "none";

};

function test5(){ // On DOM ready
/*
document.getElementById("lean_overlay").style.display = "none";*/

document.getElementById("loginmodal4").display = "block";
document.getElementById("loginmodal").style.display = "none";
document.getElementById("loginmodal1").style.display = "none";
document.getElementById("loginmodal2").style.display = "none";
document.getElementById("loginmodal3").style.display = "none";
document.getElementById("loginmodal5").style.display = "none";

};

function test6(){ // On DOM ready
/*
document.getElementById("lean_overlay").style.display = "none";*/

document.getElementById("loginmodal5").display = "block";
document.getElementById("loginmodal").style.display = "none";
document.getElementById("loginmodal1").style.display = "none";
document.getElementById("loginmodal2").style.display = "none";
document.getElementById("loginmodal3").style.display = "none";
document.getElementById("loginmodal4").style.display = "none";

};

function ClosePopup(){ // On DOM ready
/*
document.getElementById("lean_overlay").style.display = "none";*/

document.getElementById("loginmodal5").style.display = "none";
document.getElementById("loginmodal").style.display = "none";
document.getElementById("loginmodal1").style.display = "none";
document.getElementById("loginmodal2").style.display = "none";
document.getElementById("loginmodal3").style.display = "none";
document.getElementById("loginmodal4").style.display = "none";

};
/*function noBack(){
	window.history.forward();
}
window.onpageshow=function(evt){if(evt.persisted)noBack();}
window.onload=function(){noBack();}
window.onunload=function(){void(0);}
*/
</script>



<% String popUpStatus = (String) request.getAttribute("status");
String custStatus = (String) request.getAttribute("custStatus");
String eqSubject = (String) request.getAttribute("eqSubject");
System.out.println("popUpStatus=============="+popUpStatus);
	if(popUpStatus!=null && popUpStatus.equalsIgnoreCase("urlFailvendor")){
	System.out.println("Inside the vendor Fail block");%>
<body onLoad="poupOnBody();">
<%} else if(popUpStatus!=null && popUpStatus.equalsIgnoreCase("urlFailMSP")){
System.out.println("Inside the MSP Fail block");%>
<body onLoad="poupOnBody1();">
<%} else if(popUpStatus!=null && popUpStatus.equalsIgnoreCase("urlFailjobSeeker")){
System.out.println("Inside the jobSeeker Fail block");
%>
<body onLoad="poupOnBody2();">
<%} else if(popUpStatus!=null && popUpStatus.equalsIgnoreCase("loginfail") && custStatus !=null && custStatus.equalsIgnoreCase("vendor")){
	System.out.println("invalid username or password");
	%>
	<body onLoad="poupOnBody();">
	<%}else if(popUpStatus!=null && popUpStatus.equalsIgnoreCase("loginfail") && custStatus !=null && custStatus.equalsIgnoreCase("MSP")){
		System.out.println("invalid username or password");
		%>
		<body onLoad="poupOnBody1();">
		<%}else if(popUpStatus!=null && popUpStatus.equalsIgnoreCase("loginfail") && custStatus !=null && custStatus.equalsIgnoreCase("jobSeeker")){
			System.out.println("invalid username or password");
			%>
			<body onLoad="poupOnBody2();">
			<%}else if (popUpStatus!=null  && eqSubject != null && eqSubject.equalsIgnoreCase("sales")){
				System.out.println("Messages for Sales Query");%>
				<body onLoad="poupOnBody3();">
			<%}else if (popUpStatus!=null  && eqSubject != null && eqSubject.equalsIgnoreCase("billing")){
				System.out.println("Messages for Billing Query");%>
				<body onLoad="poupOnBody4();">
			<%}else if (popUpStatus!=null  && eqSubject != null && eqSubject.equalsIgnoreCase("support")){
				System.out.println("Messages for Support Query");%>
				<body onLoad="poupOnBody5();">
			<%}else {
				System.out.println("Inside none of the Fail block");%>
				<body onLoad="noBack();">
				<%} %>


<%String userId=(String)session.getAttribute("userId");
System.out.println("UserId in headerProcess : "+userId);
%>


<!--header-->
<header class="indent">
    <div class="follow-box">
        <div class="container"> 
            <ul class="address_icon">
               
				<li><a href="ViewChatBox.html?cmd=openChatBox" style="font-weight:600;color:#f03639" class="hov" ><img src="jsp/LavlitaChatApp/images/Lavlita-AI_verysmall.jpg" style="border-radius:50%;height:45px;width:45px;margin-top: -10px;">Chat with Lavlita.AI</a></li>
            </ul>
            <ul class="follow_icon">
                <li><a href="https://www.facebook.com/MenschForce-1408440452755557/" target="_blank"><img src="img/follow_icon1.png" alt=""></a></li>

                <li><a href="https://www.linkedin.com/company/digiblitz-technologies" target="_blank"><img src="img/follow_icon5.png" alt=""></a></li>
				<li><a href="https://www.twitter.com/digiblitz" target="_blank"><i class="fa fa-twitter" style="color: #828383;"></i></a></li>
            </ul>
        </div>
    </div> 
    <div class="container">  
        <h1 class="navbar-brand navbar-brand_ wow fadeInLeft"><a href="user.html?cmd=initHome"><img src="img/menschForce_logo.png" alt="logo"></a></h1>     
         
        <nav class="navbar navbar-default navbar-static-top tm_navbar clearfix" role="navigation">
            <ul class="nav sf-menu clearfix">
                <li class="active"><a href="user.html?cmd=initHome">Home</a></li>
               
                <li><a href="user.html?cmd=initwhymenschforce">Why menschForce?</a></li>
                <li><a href="user.html?cmd=initManagedServices">The Process</a></li>
                <li><a href="signUp.html?signUpProcess=getStarted">Pricing</a></li>
				<li><a href="https://www.digiblitz.com/support/?cat=7" target="_blank">Support</a></li>
                <li><a href="https://digiblitz.com/community/" target="_blank">Community</a></li>
                <li><a href="user.html?cmd=initConfig">Contact Us</a></li>
                <li><a href="initPartner.html">Partnership</a></li>
				<li class="sub-menu"><a href="#">SignIn</a><span></span>
                    <ul class="submenu">
        				<li><a href="#loginmodal" id="modaltrigger" onClick="test1();">Vendor</a></li>
						<li><a href="#loginmodal1" id="modaltrigger1" onClick="test2();">Buyer</a></li>
						<li><a href="#loginmodal2" id="modaltrigger2" onClick="test3();">Work Seeker</a></li>
        			</ul>
              </li>
            </ul>
        </nav> 
    </div>



     <div id="loginmodal" style="display:none; background-color:#FFF" class="bg1">
  <label style="size:25px"><h1>Vendor Login</h1></label>
  <%String usercode=(String)request.getAttribute("vendorId"); %>
      <form id="loginform" name="loginform" method="post" action="login.html?cmd=initDash">
	 	  <input type="hidden" name="customerType" value="vendor"/>
	 	  <input type="hidden" name="from" value="${param.from}">
    <div align="left" style="size:25px">Username:
	
	  <%
			String status = (String) request.getAttribute("status");
            if (status != null) {
                if (status.equals("fail")) {%>
				
	<div class="styleError">Invalid Login! Try logging in again.</div>
	<%} else if (status.equals("expired")) {%>
	<div class="styleError">License Expired.</div>
	 <% } else if (status.equals("conFailed")) {%>
	 <div class="styleError">Connection Problem ! Please Login Again.</div>
	 <% } else if (status.equals("users")) {%>
	 <div class="styleError">Licensed Users Exceeded.</div>
	 <% } else if (status.equalsIgnoreCase("urlFailvendor")) { %>
	 <div class="styleError" style="color:#FF0000;font-weight:bolder">Invalid Vendor Id !</div>
	 <% }else if (status.equalsIgnoreCase("loginfail")) { %>
	 <div class="styleError" style="color:#FF0000;font-weight:bolder">Invalid Username or Password !</div>
	 <% }}%>
	</div>
      <input type="text" name="username" id="username" class="txtfield" tabindex="1" onBlur="usrStat();" required/>
      
        <div align="left"  style="size:25px">Password:</div>
      <input type="password" name="password" id="password" class="txtfield" tabindex="2" required/>
      <div align="left"  style="size:25px">Vendor_Id:       </div>
	    <input type="text" name="userCode" id="userCode" class="txtfield" tabindex="2" required/>
      <div class="center"><label><!--img src="img/register_button.jpg" alt="" width="20px" height="20px" style="cursor:pointer; "/--><input type="submit" name="Submit" id="loginbtn"  value="Sign In" tabindex="3" class="button-dang" style="border:1px solid;border-radius:6px;"></label></div>
	
	  
	  <div align="center" style="margin-top:10px"><a href="user.html?cmd=initUsrReg&id=vendor" style="color:#0000FF">New user signup</a>  <a href="user.html?cmd=view" style="color:#0000FF">&emsp;&emsp;&emsp;&emsp;Forget password?</a></div> 
    </form>
  </div>
  
  
  <div id="loginmodal1" style="display:none; background-color:#FFF" class="bg1">
  <label style="size:25px"><h1>Buyer Login</h1></label>
  <%String usercode1=(String)request.getAttribute("vendorId"); %>
      <form id="loginform" name="loginform" method="post" action="login.html?cmd=initDash">
	 	  <input type="hidden" name="customerType" value="MSP"/>
    <div align="left" style="size:25px">Username:
	
	  <%
			String status1 = (String) request.getAttribute("status");
            if (status1 != null) {
                if (status1.equals("fail")) {%>
				
	<div class="styleError">Invalid Login! Try logging in again.</div>
	<%} else if (status1.equals("expired")) {%>
	<div class="styleError">License Expired.</div>
	 <% } else if (status1.equals("conFailed")) {%>
	 <div class="styleError">Connection Problem ! Please Login Again.</div>
	 <% } else if (status1.equals("users")) {%>
	 <div class="styleError">Licensed Users Exceeded.</div>
	 <% } else if (status.equalsIgnoreCase("urlFailMSP")) { %>
	 <div class="styleError">Invalid Buyer Id !</div>
	 <% }else if (status1.equalsIgnoreCase("loginfail")) { %>
	 <div class="styleError" style="color:#FF0000;font-weight:bolder">Invalid Username or Password !</div>
	 <% }}%>
	</div>
      <input type="text" name="username" id="username" class="txtfield" tabindex="1" onBlur="usrStat();" required/>
      
        <div align="left"  style="size:25px">Password:</div>
      <input type="password" name="password" id="password" class="txtfield" tabindex="2" required/>
      <div align="left"  style="size:25px">Buyer ID:       </div>
	    <input type="text" name="userCode" id="userCode" class="txtfield" tabindex="2" required/>
      <div class="center"><label><!--img src="img/register_button.jpg" alt="" width="20px" height="20px" style="cursor:pointer; "/--><input type="submit" name="Submit" id="loginbtn"  value="Sign In" tabindex="3" class="button-dang" style="border:1px solid;border-radius:6px;"></label></div>
	
	  
	  <div align="center" style="margin-top:10px"><a href="user.html?cmd=initUsrReg&id=msp" style="color:#0000FF">New user signup</a>  <a href="user.html?cmd=view" style="color:#0000FF">&emsp;&emsp;&emsp;&emsp;Forget password?</a></div> 
    </form>
  </div>
  
  
  <div id="loginmodal2" style="display:none; background-color:#FFF" class="bg1">
  <label style="size:25px"><h1>Work Seeker Login</h1></label>
  <%String usercode2=(String)request.getAttribute("vendorId"); %>
      <form id="loginform" name="loginform" method="post" action="login.html?cmd=initDash">
	 	  <input type="hidden" name="customerType" value="jobSeeker"/>
    <div align="left" style="size:25px">Username:
	
	  <%
			String status2 = (String) request.getAttribute("status");
            if (status2 != null) {
                if (status2.equals("fail")) {%>
				
	<div class="styleError">Invalid Login! Try logging in again.</div>
	<%} else if (status2.equals("expired")) {%>
	<div class="styleError">License Expired.</div>
	 <% } else if (status2.equals("conFailed")) {%>
	 <div class="styleError">Connection Problem ! Please Login Again.</div>
	 <% } else if (status2.equals("users")) {%>
	 <div class="styleError">Licensed Users Exceeded.</div>
	 <% } else if (status.equalsIgnoreCase("urlFailjobSeeker")) { %>
	 <div class="styleError">Invalid Work Seeker Id !</div>
	 <% }else if (status2.equalsIgnoreCase("loginfail")) { %>
	 <div class="styleError" style="color:#FF0000;font-weight:bolder">Invalid Username or Password !</div>
	 <% }}%>
	</div>
      <input type="text" name="username" id="username" class="txtfield" tabindex="1" onBlur="usrStat();" required/>
      
        <div align="left"  style="size:25px">Password:</div>
      <input type="password" name="password" id="password" class="txtfield" tabindex="2" required/>
      <div align="left"  style="size:25px">Work Seeker Id:       </div>
	    <input type="text" name="userCode" id="userCode" class="txtfield" tabindex="2" required/>
      <div class="center"><label><!--img src="img/register_button.jpg" alt="" width="20px" height="20px" style="cursor:pointer; "/--><input type="submit" name="Submit" id="loginbtn"  value="Sign In" tabindex="3" class="button-dang" style="border:1px solid;border-radius:6px;"></label></div>
	
	  
	  <div align="center" style="margin-top:10px"><a href="user.html?cmd=initUsrReg&id=jobseeker" style="color:#0000FF">New user signup</a>  <a href="user.html?cmd=view" style="color:#0000FF">&emsp;&emsp;&emsp;&emsp;Forget password?</a></div> 
    </form>
  </div>
  
  <div id="loginmodal3" style="display:none; background-color:#FFF" class="bg1">
   <div ><span style="margin-left:90%;color:#000;" ><a  href="#"  onClick="ClosePopup();"><i class="fa fa-times" ></i></a></span></div>
  <label style="size:25px"><h1>Send Query</h1></label>
      <form id="loginform" name="loginform" method="post" action="user.html?cmd=emailQuery">
	 	  <input type="hidden" name="eqSubject" value="sales"/>
		  <%	String status3 = (String) request.getAttribute("status");
            if (status3 != null) {
                if (status3.equals("eqFail")) {%>
				<div class="styleError">Query Not Send ! Try again.</div>
				<%} else if (status3.equals("eqSuccess")) {%>
				<div style="color:#206303">Your query has been submitted successfully.</div>
		<% }}%>
    <div align="left" style="size:25px">Name:</div>
	 <input type="text" name="eqName" id="eqName" class="txtfield" tabindex="1" onBlur="usrStat();" required/>
      <div align="left"  style="size:25px">email:</div>
      <input type="text" name="eqEmail" id="eqEmail" class="txtfield" tabindex="2" required/>
      <div align="left"  style="size:25px">Query:</div>
	    <textarea type="text" name="eqDescription" id="eqDescription" class="txtfield" tabindex="2" required></textarea>
      <div class="center"><label><!--img src="img/register_button.jpg" alt="" width="20px" height="20px" style="cursor:pointer; "/--><input type="submit" name="Submit" id="loginbtn"  value="Send" tabindex="3" class="button-dang" style="border:1px solid;border-radius:6px;"></label></div>
	
	  
	 
    </form>
  </div>
  
  <div id="loginmodal4" style="display:none; background-color:#FFF" class="bg1">
   <div ><span style="margin-left:90%" ><a  href="#" onClick="ClosePopup();"><i class="fa fa-times" aria-hidden="true"></i></a></span></div>
  <label style="size:25px"><h1>Send Query</h1></label>
      <form id="loginform" name="loginform" method="post" action="user.html?cmd=emailQuery">
	 	  <input type="hidden" name="eqSubject" value="billing"/>
		  <%	String status4 = (String) request.getAttribute("status");
            if (status4 != null) {
                if (status4.equals("eqFail")) {%>
				<div class="styleError">Query Not Send ! Try again.</div>
				<%} else if (status4.equals("eqSuccess")) {%>
				<div style="color:#206303">Your query has been submitted successfully.</div>
		<% }}%>
    <div align="left" style="size:25px">Name:</div>
      <input type="text" name="eqName" id="eqName" class="txtfield" tabindex="1" onBlur="usrStat();" required/>
      
        <div align="left"  style="size:25px">email:</div>
      <input type="text" name="eqEmail" id="eqEmail" class="txtfield" tabindex="2" required/>
      <div align="left"  style="size:25px">Query:</div>
	    <textarea type="text" name="eqDescription" id="eqDescription" class="txtfield" tabindex="2" required></textarea>
      <div class="center"><label><!--img src="img/register_button.jpg" alt="" width="20px" height="20px" style="cursor:pointer; "/--><input type="submit" name="Submit" id="loginbtn"  value="Send" tabindex="3" class="button-dang" style="border:1px solid;border-radius:6px;"></label></div>
	
	  
	 
    </form>
  </div>
  
  <div id="loginmodal5" style="display:none; background-color:#FFF" class="bg1">
  <div ><span style="margin-left:90%" ><a  href="#" onClick="ClosePopup();"><i class="fa fa-times" aria-hidden="true"></i></a></span></div>
  <label style="size:25px"><h1>Send Query</h1></label>
      <form id="loginform" name="loginform" method="post" action="user.html?cmd=emailQuery">
	 	  <input type="hidden" name="eqSubject" value="support"/>
		  	<%	String status5 = (String) request.getAttribute("status");
            if (status5 != null) {
                if (status5.equals("eqFail")) {%>
				<div class="styleError">Query Not Send ! Try again.</div>
				<%} else if (status5.equals("eqSuccess")) {%>
				<div style="color:#206303">Your query has been submitted successfully.</div>
		<% }}%>
    <div align="left" style="size:25px">Name:</div>
      <input type="text" name="eqName" id="eqName" class="txtfield" tabindex="1" onBlur="usrStat();" required/>
      
        <div align="left"  style="size:25px">email:</div>
      <input type="text" name="eqEmail" id="eqEmail" class="txtfield" tabindex="2" required/>
      <div align="left"  style="size:25px">Query:</div>
	    <textarea type="text" name="eqDescription" id="eqDescription" class="txtfield" tabindex="2" required></textarea>
      <div class="center"><label><!--img src="img/register_button.jpg" alt="" width="20px" height="20px" style="cursor:pointer; "/--><input type="submit" name="Submit" id="loginbtn"  value="Send" tabindex="3" class="button-dang" style="border:1px solid;border-radius:6px;"></label></div>
	
	  
	 
    </form>
  </div>
  
  
  
</header>
</body>
</html>

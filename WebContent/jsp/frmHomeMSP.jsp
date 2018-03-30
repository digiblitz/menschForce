<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<!DOCTYPE html>
<html lang="en">
<head>
<title>Home</title>
<meta charset="utf-8">    
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
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
<script type="text/javascript" charset="utf-8" src="js/jquery.leanModal.min.js"></script>
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
  
  $('#modaltrigger').leanModal({ top: 110, overlay: 0.45, closeButton: ".hidemodal" });
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
    z-index:100;
    top: 0px;
    left: 0px;
    height:100%;
    
   
    display: none;
}


#loginmodal {
  width: 300px;
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

</style>
<body>
<!--header-->
<header class="indent">
    <div class="follow-box">
        <div class="container"> 
            <ul class="address_icon">
               <li><img src="img/page1_icon1.png" alt=""> 20130 Lakeview Center Plaza, Suite 400.Ashburn, VA 20147, USA</li>
                <li><img src="img/page1_icon2.png" alt=""> <a href="#">info@digiblitz.com</a></li>
                <li><img src="img/page1_icon3.png" alt=""> +1-571-297-2288.</li>
            </ul>
            <ul class="follow_icon">
                <li><a href="https://www.facebook.com/digiblitz?_rdr=p" target="_blank"><img src="img/follow_icon1.png" alt=""></a></li>
                <li><a href="https://twitter.com/digiBlitz" target="_blank"><img src="img/follow_icon2.png" alt="" width="20" height="20"></a></li>
                <li><a href="https://www.youtube.com/user/digiBlitzTube?feature=creators_cornier-%2F%2Fs.ytimg.com%2Fyt%2Fimg

%2Fcreators_corner%2FYouTube%2Fyoutube_32x32.png" target="_blank"><img src="img/follow_icon3.png" alt=""></a></li>
                <li><a href="https://www.xing.com/companies/digiblitzeuropegmbh" target="_blank"><img src="img/follow_icon4.png" alt=""></a></li>
                <li><a href="https://www.linkedin.com/company/digiblitz-technologies" target="_blank"><img src="img/follow_icon5.png" alt=""></a></li>
				
            </ul>
        </div>
    </div> 
    <div class="container">  
        <h1  class="navbar-brand navbar-brand_ wow fadeInLeft"><a href=""><img src="img/menschForce_logo.png" alt="logo"></a></h1>     
         
        <nav class="navbar navbar-default navbar-static-top tm_navbar clearfix" role="navigation">
            <ul class="nav sf-menu clearfix">
                <li class="active"><a href="frmHome.jsp">Home</a></li>
                <li class="sub-menu"><a href="frmServices.jsp">Why menschForce?</a>
                    
              </li>
                <li><a href="frmPricing.jsp">Pricing</a></li>
                <li><a href="frmProjects.jsp">Resource Center</a></li>
                <li><a href="frmContacts.jsp">Contacts</a></li>
               <li><a href="#loginmodal" id="modaltrigger">&nbsp;&nbsp;Log In</a></li>
                 
            </ul>
        </nav> 
    </div>
     <div id="loginmodal" style="display:none; background-color:#FFF" class="bg1">
  <label style="size:25px"><h1>User Login</h1></label>
      <form id="loginform" name="loginform" method="post" action="login.html?cmd=initDash">
	 	  
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
	 <% } } %>
	 
	</div>
      <input type="text" name="textfield" id="textfield" class="txtfield" tabindex="1" onBlur="usrStat();">
      
        <div align="left"  style="size:25px">Password:</div>
      <input type="password" name="textfield2" id="textfield2" class="txtfield" tabindex="2">
      
       <div align="left"  style="size:25px">MSP_Id:
              
       </div>
      <input type="text" name="userCode" id="textfield3" class="txtfield" tabindex="2">
      
            
      <div class="center"><label><img src="img/register_button.jpg" alt="" width="20px" height="20px" style="cursor:pointer; "/><input type="submit" name="Submit" id="loginbtn"  value="Sign In" tabindex="3" class="button-add"></label></div>
	
	  
	  <div align="center" style="margin-top:10px"><a href="user.html?cmd=initUsrReg" style="color:#0000FF">New user signup</a>  <a href="user.html?cmd=view" style="color:#0000FF">&emsp;&emsp;&emsp;&emsp;Forget password?</a></div> 

    </form>
  </div>
</header>
<div class="slider">    
    <div class="camera_wrap">
        <div data-src="img/picture1.jpg"><div class="camera-caption fadeIn"><p class="title" style="font-size:28px">Achieve HR</p><p class="description" style="font-size:28px">Legal Compliances for your contingent workforce</p><a href="signUp.html?signUpProcess=getStarted" class="btn-default btn1">Get Started></a></div></div>
		<div data-src="img/picture2.jpg"><div class="camera-caption fadeIn"><p class="title" style="font-size:28px">Transform your ad-hoc staffing functions into an effective</p><p class="description" style="font-size:28px">automated business process digiBlitz menschForce!</p></div></div>
       
    </div>
</div>
<!--content-->
<div class="content"> 
    <div class="thumb-box1">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="box1 maxheight">
                        <div class="wow fadeInUp clearfix">
                            <p  ></p>
                          <p class="title" style="font-size:18px" align="justify">menschForce helps your organization's contingent workforce Business Automation using BPM & SOA technologies. It is powered by dBETE platfom which helps you to successfully implement your Business-Technology Landscapte transformation projects. The solution is specifically tailor made for staffing industry.We use state-of-the-art Processes,Manpower,Management System,Private social Media and Customer Relationship Management to raise the bar excellence of our Practice</p>

                                                    </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6 subbox2">
                    <div class="box2 maxheight">
                        <div class="wow fadeInUp clearfix" data-wow-delay="0.1s">
                            <p class="title">What Best's about menschForce?</p>
                            <ul class="list1-1">
                                <li><a href="">Accounts Receivable/Payables </a></li>
                                <li><a href="">Human Asset Management</a></li>
                                <li><a href="">Budget Control</a></li>
                                <li><a href="">Commission Mark-up</a></li>
								 <li><a href="">QuickBooks Integration</a></li>
                                 <li><a href="">Invoice Automation</a></li>
                                <li><a href="">Aggregated reports</a></li>
                                <li><a href="">Financial Score Cards</a></li>
                            </ul>
                                                   </div>
                        <svg class="bigTriangleColor" xmlns="http://www.w3.org/2000/svg" version="1.1" width="100.5" height="100.1%" viewBox="0 0 100 102" preserveAspectRatio="none">
            				<path d="M100 0 L0 102 L100 102 Z"></path>
            			</svg>
                    </div>
                </div>
            </div>
        </div>
        <em></em>
    </div>
     <div class="thumb-box2">
        <div class="container">
            
            <div class="row">
                <div class="col-md-4 col-sm-12 col-xs-12 wow fadeInUp">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                            <div class="badge"><img src="img/page1_badge1.png" alt=""></div>
                            <div class="caption" >
                                <p class="title" >HR-Hiring Processes</p>
                                <p style="font-size:14px" align="justify">Completely automate the staff recruitment process via menschForce.It allows you to post a comprehensive, tailor made requirement. The suppliers and consultants can submit resumes, rates and multiple supporting documents. You can associate a requirement with contingent parameters that will help you to get best possible match. Multiple recruiters from multiple vendors can work  individually on fining and submitting best candidates. You will have process to manage , measure vendorwise, recruiter-wise, account manager-wise performances through reports and score cards.</p>
                                                           </div>  
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-sm-12 col-xs-12 wow fadeInUp" data-wow-delay="0.1s">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                            <div class="badge"><img src="img/page1_badge2.png" alt=""></div>
                            <div class="caption">
                                <p class="title" >Finance Processes</p>
                                <p style="font-size:14px" align="justify">Integrate your project costing with contingent workforce management and have better control over the financials.  menschForce allows you to create, modify and optimize resource rates as per the project budgets and market scenarios to  attract better talent . The system will provide you with resource costing aggregated by projects and suppliers.Your human resources management is now automated seamlessly and work effectively with other organizational processes </p>
                                                            </div>  
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-sm-12 col-xs-12 wow fadeInUp" data-wow-delay="0.2s">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                            <div class="badge"><img src="img/page1_badge3.png" alt=""></div>
                            <div class="caption">
                                <p class="title" >Automated Work Flow & Communication  </p>
                                <p style="font-size:14px" align="justify">Fully Automated Work Flow through our state-of-the-art BPM process template dBBPM. Automated Communications between the stakeholders makes the   system completely effective in closing the positions quick. Applications talk & share information while you focus on your core business. Modeler to create custom business process.      </p>
                                                           </div>  
                        </div>
                    </div>
                </div>
               
            </div>
        </div>
     </div>
     
    
     
     
</div>
<!--footer-->
  <%@ include file = "../../include/FooterProcess.jsp" %>
<script src="js/bootstrap.min.js"></script>
<script src="js/tm-scripts.js"></script>
</body>
</html>

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
</style>

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
	
    <div  style="margin:auto; text-align:center; margin-top:40px  " >
     <p class="privacy wow fadeInUp" style="margin-bottom:20px"> <em id="copyright-year"></em> <a href="index-5.html">   <span id="copyright-year" ></span>&nbsp;&copy;&nbsp;Powered by digiBlitz</a></p>
            <ul class="follow_icon_foo wow fadeInUp" >
            <li><a href="https://www.facebook.com/MenschForce-621208418017277/" target="_blank"><img src="img/follow_icon1.png" alt=""></a></li>
                <li><a href="https://twitter.com/digiBlitz" target="_blank"><img src="img/follow_icon2.png" alt="" width="20" height="20"></a></li>
                <li><a href="https://www.youtube.com/user/digiBlitzTube?feature=creators_cornier-%2F%2Fs.ytimg.com%2Fyt%2Fimg

%2Fcreators_corner%2FYouTube%2Fyoutube_32x32.png" target="_blank"><img src="img/follow_icon3.png" alt=""></a></li>
                <li><a href="https://www.xing.com/companies/digiblitzeuropegmbh" target="_blank"><img src="img/follow_icon4.png" alt=""></a></li>
                <li><a href="https://www.linkedin.com/company/digiblitz-technologies" target="_blank"><img src="img/follow_icon5.png" alt=""></a></li>
			 
        </ul>
    </div>


  
</body>
</html>

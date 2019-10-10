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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<script src="js/basic.js" type="text/javascript" ></script>
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
</head>


<body>

<header id="header">
<%@ include file = "../../include/menschForceHeader.jsp" %>
  <!-- HEADER ENDS HERE -->
</header>
<div class="content">
<br /><br />
  <table width=100% height="350" border="0" cellpadding="0" cellspacing="0" class="content_new" align="center">
        <tr><td></td></tr>

    <tr>
	
      <td  align="center" valign="middle" class="tableCommonBg" >
	  <table width="630" height="100" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab" style="border:thin;border-style:groove;">
        <tr>
          <td width=100% valign="top" class="subDeptTablePad">
		  <table border="1" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer" width="630">
              <tr >
                <td colspan="2" class="tblMainHead"><strong> Membership</strong><div class="divider3"></div></td>
              </tr>
              <%
  	String source = (String) request.getAttribute("source");
	String eventTypeId = (String)request.getAttribute("eventTypeId");
	String compYear = (String)request.getAttribute("compYear");
	String regBtn = "";
	String noBtn = "";
	if(source!=null && source.equalsIgnoreCase("fromEventEntry")){
		regBtn = "MemberUsrSignUp.do?process=usrReg&source=fromEventEntry&eventTypeId"+eventTypeId+"&compYear="+compYear;
		noBtn = "MemberUsrSignUp.do?process=view&source=fromEventEntry&eventTypeId"+eventTypeId+"&compYear="+compYear;
	}else{
		//regBtn = "MemberUsrSignUp.do?process=usrReg";
		regBtn = "MemberUsrSignUp.do?process=usrEmpReg";
				
		noBtn = "MemberUsrSignUp.do?process=view";
	}
  %>
              <tr>
                <td height=100% colspan="2" class="tblDescrp" style="padding:10px;"><p><strong> Do you want to register as a new member?<br />
                          <br />
                    </strong> <span class="asterisk"> IMPORTANT </span> -- If you are a previous or current member, do not create a new member account.
                  If you need help logging in, please contact us at <span class="textTwo"> <a href="mailto:anandv@digiblitz.com">anandv@digiblitz.com</a> </span> <br />
                  or call (703) 779-0440 and press 1</p>
                    <p><strong> <br />
                          <span>
                          <input name="button" type="button" class="gradBtn" value="Yes, Register Me" onclick="location.href='<%=regBtn%>'" />
                            &nbsp;
                            <input name="button2" type="button" class="gradBtn" value="No, Try Again" onclick="location.href='<%=noBtn%>'" />
                          </span><br />
                  </strong></p></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td height="20">&nbsp;
                    <!-- DO NOT DELETE THIS ROW --></td>
              </tr>
            </table>
            <!-- CONTENTS END HERE -->          </td>
        </tr>
      </table></td>
    </tr>
		<tr><td>&nbsp;</td></tr>

  </table></div>
<!--=======footer=================================-->
 <footer>			 
			
                    <!-- FOOTER STARTS HERE -->
                        
						<%@ include file = "../../include/menschForceFooter.jsp" %>
                    <!-- FOOTER ENDS HERE -->
               
 </footer>
</body>
</html>

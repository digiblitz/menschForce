<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.hlcform.util.*" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<script src="js/basic.js" type="text/javascript" ></script>
<script src="js/validate.js" type="text/javascript" ></script>
<script src="js/memberUserSignupAuth.js" type="text/javascript" ></script>

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
       
    <tr>
	
      
     
      <td  align="center" valign="middle" class="tableCommonBg">
	  <table width="630" height="300" border="1" cellpadding="0" cellspacing="0" class="tblInnerContainer" style="border:thin;border-style:groove;">
        <tr >
          <td colspan="2" class="tblMainHead"> <strong>Member SignUp: Create Dashboard Login </strong><div class="divider3"></div></td>
        </tr>
        <tr>
          <td><%
									String source = (String)request.getAttribute("source");
									String eventTypeId = (String)request.getAttribute("eventTypeId");
									String compYear = (String)request.getAttribute("compYear");
								%>
              <form name="myform" id="myform" method="post" action="./MemberUsrSignUp.do" onsubmit="return myvalidate();">
                <input type="hidden" name="source" value="<%=source%>" />
                <input type="hidden" name="eventTypeId" id="eventTypeId" value="<%=eventTypeId%>" />
                <input type="hidden" name="compYear" id="compYear" value="<%=compYear%>" />
                <table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout" width="630">
                  <tr >
                    <td colspan="2" class="tblRowHead"><strong>&nbsp;Member  Detail </strong></td>
                  </tr>
                  <tr> </tr>
                  <input type="hidden" name="process" value="validate" />
                  <% 
										HLCUsrSignUpVO userDet = new HLCUsrSignUpVO();
                                                                        
                                                                        userDet=(HLCUsrSignUpVO)request.getAttribute("userDet");
                                                                        
									
								%>
                  <tr>
                    <td colspan="2" height="25" class="alignCenter">
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
                        <tr class="tableInner">
                          <td class="tableLeft">First Name:</td>
                          <td class="tableRight"><input name="firstName" id="firstNameId" class="textboxOne" value="<%=userDet.getFname()%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="20" readonly="readonly" />                          </td>
                        </tr>
                        <tr class="tableInner">
                          <td class="tableLeft">Last Name:</td>
                          <td class="tableRight"><input name="lastName" id="lastNameId" class="textboxOne" value="<%=userDet.getLname()%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="20" readonly="readonly" />                          </td>
                        </tr>
                        <%

												String digno="";
												if(userDet.getPhone()!=null)
												{
				
													String ph=userDet.getPhone();
													int phlen=ph.length();

													if(phlen>4)
													{
														int rstsiz=phlen-4;
														digno=ph.substring(rstsiz,phlen);
													}
													else
													{
														digno=userDet.getPhone();
													}													
												}

												String emailId="";
												if(userDet.getEmail()!=null){
													String email=userDet.getEmail();
													int indx = email.indexOf("@");
													emailId = email.substring(indx+1);                                                                                                    
													System.out.print("emailId :"+emailId);
												}
												
												String zp="";
												if(userDet.getZip()!=null){
													String zip = userDet.getZip();
													if(zip.length()>2){
														zp = zip.substring(0,2);
													}else{
														zp = zip;
													}                                                        System.out.print("zip : " +zp);                                           
												}


String usid="";
												if(userDet.getUserId()!=null){
													String hlcid=userDet.getUserId();
													if(hlcid.length()>2){
														usid=hlcid.substring(0,2);                                                                                                        
													}else{
														usid = hlcid;                                                                                                                
													}
												}

                                                String mId="N/A";
                                                                                                
												if(userDet.getMembid()!=null)
												{
													String hlcid=userDet.getMembid();
													
													if(hlcid.length()>2){
														mId=hlcid.substring(0,2);                                                                                                        
													}else{
														mId = hlcid;                                                                                                               													
													}
													
                                                }
                                                                                                
												%>
                        <tr class="tableInner">
                          <td class="tableLeft">Email  (Last domain):</td>
                          <th class="tableRight">xxxxxx@
                              <input name="phoneNumber" id="phoneNumberId" class="textboxOne" value="<%=emailId%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="20" readonly="readonly" />                          </th>
                        </tr>
                        <tr class="tableInner">
                          <td class="tableLeft">Phone Number (Last 4 digits):</td>
                          <th class="tableRight">xxx-xxx-
                              <input name="phoneNumber" id="phoneNumberId" class="textboxOne" value="<%=digno%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="8" readonly="readonly" />                          </th>
                        </tr>
                        <tr class="tableInner">
                          <td class="tableLeft">Zip (First 2 digits):</td>
                          <th class="tableRight"><input name="phoneNumber" id="phoneNumberId" class="textboxOne" value="<%=zp%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="2" readonly="readonly" />
                            xxx</th>
                        </tr>
                        <tr>
                          <td class="tableLeft">Member ID (First 2 digits):</td>
                          <th class="tableRight"><input name="phoneNumber" id="phoneNumberId" class="textboxOne" value="<%=mId%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="2" readonly="readonly" />
                            xxxxxx</th>
                        </tr>
                    </table></td>
                  </tr>
                  <input type="hidden" name="userId" value="<%=userDet.getUserId()%>" />
                  <tr>
                    <td colspan="2" class="tblDescrp">Existing HLC Members please enter any two of below details to validate your identity.</td>
                  </tr>
                  <tr class="tableInner">
                    <td class="tableLeft">Phone:</td>
                    <td class="tableRight"><input type="text" name="phone" id="phone" class="textboxOne" size="15" />                    </td>
                  </tr>
                  <tr> </tr>
                  <tr class="tableInner">
                    <td class="tableLeft">e-Mail:</td>
                    <td class="tableRight"><input type="text" name="email" id="email" class="textboxOne" size="20" />                    </td>
                  </tr>
                  <tr class="tableInner">
                    <td class="tableLeft">Zipcode:</td>
                    <td class="tableRight"><input type="text" name="zip" id="zip" class="textboxOne" size="20" />                    </td>
                  </tr>
                  <tr>
                    <td colspan="2" align="center" class="alignCenter"><input name="submit" type="submit" class="gradBtn" value="Submit" /></td>
                  </tr>
                  
                </table>
              </form></td>
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

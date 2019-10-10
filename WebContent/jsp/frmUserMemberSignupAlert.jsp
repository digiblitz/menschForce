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

<%@ page import="com.hlcform.util.*" %>




<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
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

<script language="javascript">


function radValid(){

var lenques=document.frmUserSignup.info_rad.length;

if(lenques==undefined)
{

if(document.frmUserSignup.prof_rad[1].checked==true){
document.frmUserSignup.info_rad.checked=false;
}
}
else {
for(i=0;i<lenques;i++)
{
if(document.frmUserSignup.prof_rad[1].checked==true)
 { document.frmUserSignup.info_rad[i].checked=false; }
}


}
}


function chstat()
{
var lenques=document.frmUserSignup.info_rad.length;

if(lenques==undefined)
{
	if(document.frmUserSignup.info_rad.checked)
	{
		document.frmUserSignup.prof_rad[0].checked=true;
		document.frmUserSignup.prof_rad[1].checked=false;

	}
	
}
else
{

for(i=0;i<lenques;i++)
{
if(document.frmUserSignup.info_rad[i].checked)
 { chosenquest= document.frmUserSignup.info_rad[i].value; }

}
if(chosenquest!="")
{
	document.frmUserSignup.prof_rad[0].checked=true;
	document.frmUserSignup.prof_rad[1].checked=false;	
	
}
}

}


function myValidate()
{
var lenques=document.frmUserSignup.info_rad.length;
var chosenquest="";

if(lenques==undefined)
{
	if(document.frmUserSignup.info_rad.checked)
	{
		if(document.frmUserSignup.prof_rad[0].checked==false && document.frmUserSignup.prof_rad[1].checked==false)
		{
			alert("Please Check any Option for Profile match !");
			return false;
		}		

	}
	else
	{
		if(document.frmUserSignup.prof_rad[0].checked)
		{
			alert("Please Check any One Of Profiles that Matches you !");
			return false;
		}
	}
	
}
else
{

for(i=0;i<lenques;i++)
{
if(document.frmUserSignup.info_rad[i].checked)
 { chosenquest= document.frmUserSignup.info_rad[i].value; }

}
if(chosenquest!="" && chosenquest!="null")
{
	if(document.frmUserSignup.prof_rad[0].checked==false && document.frmUserSignup.prof_rad[1].checked==false)
		{
			alert("Please Check any Option for Profile match !");
			return false;
		}	
	
}
else
{
	if(document.frmUserSignup.prof_rad[0].checked)
		{
			alert("Please Check any One Of Profiles that Matches you !");
			return false;
		}
}

}

}

</script>

</head>


<body>

<header id="header">
<%@ include file = "../../include/menschForceHeader.jsp" %>
</header>
<div class="content">
<br /><br />
  <table width=100% height="350" border="0" cellpadding="0" cellspacing="0"   align="center">
        

    <tr>
	
      
     
      <td  align="center" valign="top" class="tableCommonBg" >
	  <table width="630" height="350" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab" style="border:thin;border-style:groove;">
        <tr>
          <td width=100% class="subDeptTablePad"><!-- CONTENTS START HERE -->
              <table width="630" border="1" cellpadding="0" cellspacing="0" class="tblInnerContainer">
                <tr>
                  <td><%
									String source = (String)request.getAttribute("source");
									String eventTypeId = (String)request.getAttribute("eventTypeId");
									String compYear = (String)request.getAttribute("compYear");
								%>
                      <form name="frmUserSignup" method="post" id="myform" action="MemberUsrSignUp.do" onsubmit="return myValidate();">
                        <table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout" width="630">
                          <input type="hidden" name="source" value="<%=source%>" />
                          <input type="hidden" name="eventTypeId" id="eventTypeId" value="<%=eventTypeId%>" />
                          <input type="hidden" name="compYear" id="compYear" value="<%=compYear%>" />
                          <input type="hidden" name="process" value="auth" />
                          <%    HLCUsrSignUpVO usrVal1=new HLCUsrSignUpVO();
									  		int j=0;
											int count1=0;
											String memberID="";
                                        	ArrayList usrDet1=new ArrayList();
											usrDet1=(java.util.ArrayList)request.getAttribute("membDetails");
									  		if(usrDet1!=null){
												for(j=0;j<usrDet1.size();j++){
												usrVal1=(HLCUsrSignUpVO)usrDet1.get(j);
												String statusName=usrVal1.getStatusName();
												memberID=usrVal1.getMembid();
													if((statusName!=null && statusName.equalsIgnoreCase("Duplicate")) || (statusName!=null && statusName.equalsIgnoreCase("Merged"))){
													count1=count1+1;
													}
												}
											}
									  		System.out.println("usrDet1 :"+usrDet1.size());
									  		System.out.println("count1 :"+count1);
									  		if(usrDet1.size()!=count1){%>
                          <tr >
                            <td colspan="2" class="tblMainHead"> <strong>User Registration: Account Matching Result</strong><div class="divider3"></div></td>
                          </tr>
                          <tr >
                            <td colspan="2" class="tblRowHead"><div class="divider3"></div><strong>&nbsp;Matching Results:</strong><div class="divider3"></div></td>
                          </tr>
                          <tr>
                            <td colspan="2" height="40" class="tableSpan"><strong>Similar account(s) already exists. They are as such:</strong> <br />
                                <br />
                              Select the result that matches your profile to proceed with the sign up process. </td>
                          </tr>
                          <%HLCUsrSignUpVO usrVal=new HLCUsrSignUpVO();
											int i=0;
                                                                                        
											ArrayList usrDet=new ArrayList();
											usrDet=(java.util.ArrayList)request.getAttribute("membDetails");
											
											 if(usrDet!=null)
											{
                                                                                            
                                            System.out.println("usrDet.size() in jsp :"+usrDet.size());

											for(i=0;i<usrDet.size();i++)
											{
												usrVal=(HLCUsrSignUpVO)usrDet.get(i);
												
												String statusName="";
												statusName=usrVal.getStatusName();
												System.out.println("statusName :"+statusName);
										%>
                          <%
											  if((statusName!=null) && (!(statusName.equalsIgnoreCase("Duplicate")))){	
											 
											  if((statusName!=null) && (!(statusName.equalsIgnoreCase("Merged")))){
											  
											  %>
                          <tr>
                            <td colspan="2" height="25" class="alignCenter"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
                                <tr>
                                  <td class="tblMainHead" colspan="2"><input type="radio" name="info_rad" value="<%=usrVal.getUserId()%>" onclick="chstat();"/>
                                      <span class="styleBoldOne">Information <%=i+1%></span></td>
                                </tr>
                                <tr class="tableInner">
                                  <td class="tableLeft">First Name:</td>
                                  <td class="tableRight"><input name="firstName" id="firstNameId" class="textboxOne" value="<%=usrVal.getFname()%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="20" readonly="readonly" />                                  </td>
                                </tr>
                                <tr class="tableInner">
                                  <td class="tableLeft">Last Name:</td>
                                  <td class="tableRight"><input name="lastName" id="lastNameId" class="textboxOne" value="<%=usrVal.getLname()%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="20" readonly="readonly" />                                  </td>
                                </tr>
                                <%

												String digno="";
												if(usrVal.getPhone()!=null)
												{
				
													String ph=usrVal.getPhone();
													int phlen=ph.length();

													if(phlen>4)
													{
														int rstsiz=phlen-4;
														digno=ph.substring(rstsiz,phlen);
													}
													else
													{
														digno=usrVal.getPhone();
													}													
												}

												String emailId="";
												if(usrVal.getEmail()!=null){
													String email=usrVal.getEmail();
													int indx = email.indexOf("@");
													emailId = email.substring(indx+1);                                                                                                    
													System.out.print("emailId :"+emailId);
												}
												
												String zp="";
												if(usrVal.getZip()!=null){
													String zip = usrVal.getZip();
													if(zip.length()>2){

														zp = zip.substring(0,2);
													}else{
														zp = zip;
													}                                                        System.out.print("zip : " +zp);                                           
												}


String usid="";
												if(usrVal.getUserId()!=null){
													String hlcid=usrVal.getUserId();
													if(hlcid.length()>2){
														usid=hlcid.substring(0,2);                                                                                                        
													}else{
														usid = hlcid;                                                                                                                
													}
												}

                                                String mId="N/A";
                                                                                                
												if(usrVal.getMembid()!=null)
												{
													String hlcid=usrVal.getMembid();
													
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
                                      <input name="phoneNumber" id="phoneNumberId" class="textboxOne" value="<%=emailId%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="20" readonly="readonly" />                                  </th>
                                </tr>
                                <tr class="tableInner">
                                  <td class="tableLeft">Phone Number (Last 4 digits):</td>
                                  <th class="tableRight">xxx-xxx-
                                      <input name="phoneNumber" id="phoneNumberId" class="textboxOne" value="<%=digno%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="8" readonly="readonly" />                                  </th>
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
                          <%}}else if(statusName==null){ System.out.println("2");%>
                          <tr>
                            <td colspan="2" height="25" class="alignCenter"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
                                <tr>
                                  <td class="tblMainHead" colspan="2"><input type="radio" name="info_rad" value="<%=usrVal.getUserId()%>" onclick="chstat();"/>
                                      <span class="styleBoldOne">Information <%=i+1%></span></td>
                                </tr>
                                <tr class="tableInner">
                                  <td class="tableLeft">First Name:</td>
                                  <td class="tableRight"><input name="firstName" id="firstNameId" class="textboxOne" value="<%=usrVal.getFname()%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="20" readonly="readonly" />                                  </td>
                                </tr>
                                <tr class="tableInner">
                                  <td class="tableLeft">Last Name:</td>
                                  <td class="tableRight"><input name="lastName" id="lastNameId" class="textboxOne" value="<%=usrVal.getLname()%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="20" readonly="readonly" />                                  </td>
                                </tr>
                                <%

												String digno="";
												if(usrVal.getPhone()!=null)
												{
				
													String ph=usrVal.getPhone();
													int phlen=ph.length();

													if(phlen>4)
													{
														int rstsiz=phlen-4;
														digno=ph.substring(rstsiz,phlen);
													}
													else
													{
														digno=usrVal.getPhone();
													}													
												}

												String emailId="";
												if(usrVal.getEmail()!=null){
													String email=usrVal.getEmail();
													int indx = email.indexOf("@");
													emailId = email.substring(indx+1);                                                                                                    
													System.out.print("emailId :"+emailId);
												}
												
												String zp="";
												if(usrVal.getZip()!=null){
													String zip = usrVal.getZip();
													if(zip.length()>2){

														zp = zip.substring(0,2);
													}else{
														zp = zip;
													}                                                        System.out.print("zip : " +zp);                                           
												}


String usid="";
												if(usrVal.getUserId()!=null){
													String hlcid=usrVal.getUserId();
													if(hlcid.length()>2){
														usid=hlcid.substring(0,2);                                                                                                        
													}else{
														usid = hlcid;                                                                                                                
													}
												}

                                                String mId="N/A";
                                                                                                
												if(usrVal.getMembid()!=null)
												{
													String hlcid=usrVal.getMembid();
													
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
                                      <input name="phoneNumber" id="phoneNumberId" class="textboxOne" value="<%=emailId%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="20" readonly="readonly" />                                  </th>
                                </tr>
                                <tr class="tableInner">
                                  <td class="tableLeft">Phone Number (Last 4 digits):</td>
                                  <th class="tableRight">xxx-xxx-
                                      <input name="phoneNumber" id="phoneNumberId" class="textboxOne" value="<%=digno%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="8" readonly="readonly" />                                  </th>
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
                          <%}else{ System.out.println("3");}%>
                          <%}
										 }
									  %>
                          <tr>
                            <td colspan="2" class="tableSpan">&nbsp;</td>
                          </tr>
                          <tr>
                            <td width="291" class="tableLeft">Are you sure the selection matches your profile? </td>
                            <td width="339" class="tableRight"><input type="radio" name="prof_rad" value="yes" checked="checked"  onclick="" />
                              Yes, it matches my profile.<br />
                              <input type="radio" name="prof_rad" value="no" onclick="radValid();" />
                            No, it doesn't match my profile. </td>
                          </tr>
                          <tr>
                            <td colspan="2" class="alignCenter" id="existMemb"></td>
                          </tr>
                          <tr>
                            <td colspan="2" class="alignCenter" id="secretQuest"><table width="100%" border="0" cellpadding="0" align="center" cellspacing="0" >
                                <tr>
                                  <td class="tblMainHead">&nbsp;</td>
                                </tr>
                            </table></td>
                          </tr>
                          <tr>
                            <td colspan="2" align="center" class="alignCenter"><input name="submit" type="submit" class="gradBtn" value="Continue" /></td>
                          </tr>
                          <%}else{System.out.println("4");%>
                          <%
													//String source = (String) request.getAttribute("source");
													//String eventTypeId = (String)request.getAttribute("eventTypeId");
													//String compYear = (String)request.getAttribute("compYear");
													String regBtn = "";
													String noBtn = "";
													if(source!=null && source.equalsIgnoreCase("fromEventEntry")){
													regBtn = "MemberUsrSignUp.do?process=usrReg&source=fromEventEntry&eventTypeId="+eventTypeId+"&compYear="+compYear;
													noBtn = "MemberUsrSignUp.do?process=view&source=fromEventEntry&eventTypeId="+eventTypeId+"&compYear="+compYear;
													}else{
													//regBtn = "MemberUsrSignUp.do?process=usrReg";
													regBtn = "MemberUsrSignUp.do?process=usrEmpReg";
													
													noBtn = "MemberUsrSignUp.do?process=view";
											}
											%>
                          <tr>
                            <td colspan="2" class="tblMainHead"><div class="divider3"></div><strong>Membership</strong> <div class="divider3"></div></td>
                          </tr>
                          <tr>
                            <td colspan="2" class="tblDescrp" style="padding:10px;"><strong> The Member ID [<%=memberID%>] has been merged with an older account, please search again using your first initial and last name</strong><br />
                                <br />
                                <span class="asterisk"> IMPORTANT </span> -- If you are a previous or current member, do not create a new member account.
                              If you need help logging in, please contact us at <span class="textTwo"> <a href="mailto:anandv@digiblitz.com">anandv@digiblitz.com</a> </span> <br />
                              or call (703) 779-0440 and press 1
                              </p>
                              <br />
                              <br />
                              <span>
                                <input name="button" type="button" class="gradBtn" value="Yes, Register Me" onclick="location.href='<%=regBtn%>'" />
                                &nbsp;
                                <input name="button" type="button" class="gradBtn" value="No, Try Again" onclick="location.href='<%=noBtn%>'" />
                              </span><br />
                              </strong></td>
                          </tr>
                          <%}%>
                        </table>
                      </form></td>
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

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
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.Date"%>
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

<script  src="js/ts_picker.js"></script>

 <script language="javascript">
        function callUni(){
	
   var val= Math.random().toString();
      var ch=val.substring(2,val.length);
document.getElementById("reqId").value=ch;  
        }

function validate()
{
	if(document.requestIns.reqId.value==""){
		alert("Id cannot be empty. Please click on the 'Generate ID' button");
		document.requestIns.reqId.focus();
		return false;
		}
	else
	return true;
}
	  

     </script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
</head>


<body>

<header id="header">
<%@ include file = "../../include/menschForceHeader.jsp" %>
</header>
<div class="content">
<br /><br />
  <table width=100% height="350" border="0" cellpadding="0" cellspacing="0" class="content_new" align="center">
      
    <tr>
	
      
     
      <td  align="center" valign="middle" class="tableCommonBg" >
	  <table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab" style="border:thin;border-style:groove;">
        
        <% String artUid=(String)request.getAttribute("artUid");
 String lifecycleId=(String)request.getAttribute("lifecycleId");
 String artifactName=(String)request.getAttribute("artifactName");
 String description=(String)request.getAttribute("description");
 String version=(String)request.getAttribute("version");
 
 %>
        <tr>
          <td class="tableCommonBg"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="centerTab">
              <tr>
                <td><table width="100%" border="1" align="center" cellpadding="" cellspacing="" id="centerTab">
                    <tr>
                      <td><!-- CONTENTS START HERE -->
                          <div class="cmmnForm">
                            <div class="colspan" style="border-bottom-style:groove;border-bottom-width:thin"> <strong>Request:<span class="styleBoldTwo">Request Approval</span></strong> </div>
                            <form name="requestIns" id="requestIns" method="post" action="SysMgmt.html?process=subBpRequest" class="formcss">
                              <input type="hidden" name="method" value="insertrequest"/>
                              <input type="hidden" name="artifactName" id="artifactName" value="<%=artifactName%>"/>
                              <input type="hidden" name="description" id="description" value="<%=description%>"/>
                              <input type="hidden" name="version" id="version" value="<%=version%>"/>
                              <div class="rowHead">&emsp;<strong>Request Approval Information:</strong></div>
                              <div class="row">
							   <table width="100%">
							   	<tr>
									<td width="33%" height="23">
							   <span class="label"><strong>&emsp;Request ID:*</strong></span>							   </td>
							   <td width="67%" height="23">
							   <span class="formX">
                                <input
												type="text" name="reqId" id="reqId" class="textboxOne" size="20" readonly="readonly"/>
                                <input name="button" type="button" onclick="callUni(); " value="Generate ID"   size="20" class="button-add">
                              </span>
							  </td>
							  </tr>
							  </table>
						      </div>
                              <div class="row"> 
							  <table width="100%">
							   	<tr>
									<td width="33%" height="23">
							  <span class="label"><strong>&emsp;Request Title:*</strong></span>							  </td>
							  <td>
							  <span class="formX">
                                <input
												type="text" name="reqTitle" class="textboxOne" size="30" value="<%=artifactName%>" readonly="readonly"/>
                              </span>
							  </td>
							  </tr>
							  </table>
						      </div>
                              <div class="row"> 
							  <table width="100%">
							   	<tr>
									<td width="33%" height="23">
									<span class="label"><strong>&emsp;Date of the BPM in :</strong></span>									</td>
									<td>
									<span class="formX">
                                <input
												type="text" name="date1" class="textboxOne" size="20" />
                              <a href="javascript:show_calendar('document.requestIns.date1', document.requestIns.date1.value);">
							   					<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="17" style="vertical-align:bottom;" border="0" />
							   				</a> </span>
							  </td>
							  </tr>
							  </table>
						      </div>
                              <div class="row"> 
							  <table width="100%">
							   	<tr>
									<td width="33%" height="23">
									<span class="label"><strong>&emsp;Date by when approval is required :</strong></span>									</td>
									<td>
									<span class="formX">
                                <input
												type="text" name="date2" class="textboxOne" size="20" />
                              <a href="javascript:show_calendar('document.requestIns.date2', document.requestIns.date2.value);">
							   					<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="17" style="vertical-align:bottom;" border="0" />
							   				</a></span>
							  </td>
							  </tr>
							  </table>
						      </div>
                              <%
   ArrayList Stagename=(ArrayList)request.getAttribute("Stagename");
    //System.out.println  ("artifactData"+artifactData.size());                                           
                                                 
   if (Stagename != null && Stagename.size() != 0) {  
                                                            
   Iterator iter = Stagename.iterator();
                                                                //String [] userType = {ID, name };
                                                                while (iter.hasNext()) {
                                                                	
                                                                	
                                                                	String [] artiType=(String[])iter.next();
                                                                	String stagename=artiType[0]; 
                                                                	String Stagevalue=artiType[1]; 
                                                                	
                                                                	 %>
                              <div class="row"> 
							   <table width="100%">
							   	<tr>
									<td width="33%" height="23">
							  <span class="label"><strong>&emsp;Stage Name:</strong></span>							  </td>
							  <td>
							  <span class="formX">
                                <input
												type="text" name="stagename" value="<%=stagename %>" class="textboxOne" size="20" readonly="readonly"/>
                                <input
												type="hidden" name="Stagevalue" value="<%=Stagevalue %>" class="textboxOne" size="20" />
                                <input
												type="hidden" name="artifactId" value="<%=artUid %>" class="textboxOne" size="20" />
                                <input
												type="hidden" name="lifecycleId" value="<%=lifecycleId %>" class="textboxOne" size="20" />
                              </span>
							  </td>
							  </tr>
							  </table> </div>
                              <%
                                                                }
   }
                                                %>
                              <div class="row"> 
							   <table width="100%">
							   	<tr>
									<td width="33%" height="23">
									<span class="label"><strong>&emsp;Current Version:</strong></span>									</td>
									<td>
									<span class="formX">
                                <input
												type="text" name="curVersion" class="textboxOne" size="10" value="<%=version%> " readonly="readonly"/>
                              </span>
							  </td>
							  </tr>
							  </table>
						      </div>
                              <div class="row"> 
							   <table width="100%">
							   	<tr>
									<td width="33%" height="23">
									<span class="label"><strong>&emsp;Modified Version:</strong></span>									</td>
									<td>
									<span class="formX">
                                <input
												type="text" name="modVersion" class="textboxOne" size="10"/>
                              </span>
							  </td>
							  </tr>
							  </table>
						      </div>
                              <div class="row"> 
							   <table width="100%">
							   	<tr>
									<td width="33%" height="23">
									<span class="label"><strong>&emsp;Request Priority:</strong></span>									</td>
									<td>
									<span class="formX">
                                <select name="reqPri" id="select"  class="selectboxOne">
                                <option value="high">High</option>
                                <option value="medium">Medium</option>
                                <option value="low">Low</option>
                                </select>
                                &nbsp; </span>
								</td>
								</tr>
								</table>
							  </div>
                              <div class="row"> 
							   <table width="100%">
							   	<tr>
									<td width="33%" height="23">
									<span class="label"><strong>&emsp;Request Description:</strong></span>									</td>
									<td>
									<span>
                                <textarea rows="4" cols="20" name="desc"></textarea>
                              </span>
							  </td>
							  </tr>
							  </table>
						      </div>
							  <div id="spacer">&nbsp;</div>
                              <div class="row"> 
                                <div align="center"><span class="label">&nbsp;</span> <span class="formX">
                                  <input type="submit" value="Request Approval" class="button-add" name="method2" onclick="validate();"/>
                                  </span> </div>
                              </div>
                            </form>
                          </div></td>
                    </tr>
                  </table>
                    <!-- CONTENTS END HERE -->
                </td>
              </tr>
          </table></td>
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
<script type="text/javascript">


function onValidate()
{
	
	if(document.requestIns.date1.value==""){
		alert("Startdate cannot be empty");
		document.requestIns.date1.focus();
		return false;
	}
	if(document.requestIns.date2.value==""){
		alert("Lastdate cannot be empty");
		document.requestIns.date2.focus();
		return false;
	}
	
	return true;
}
</script>
</body>


</html>

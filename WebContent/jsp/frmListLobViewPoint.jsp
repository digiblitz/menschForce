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
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.hlcform.util.HLCUserSearchResultVO" %>



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
<script src="js/basic.js" type="text/javascript" ></script>

<!-- <script src="javascripts/frmSearchPattern.js" type="text/javascript" ></script>--> 
</head>

<body>
<header id="header">
<%@ include file = "../../include/menschForceHeader.jsp" %>
</header>
<div class="content">
<br /><br />
<table width="100%" border="0" cellpadding="0" cellspacing="0" height="500px" class="content_new" align="center">
  <tr>
    <td  align="center" valign="middle" class="tableCommonBg" ><table width="760" border="1" align="center" cellpadding="0" cellspacing="0" id="mainTab">
      <tr>
        <td class="tableCommonBg"><%
			System.out.print("session.getAttribute(sessionId) in search page :"+session.getAttribute("sessionId")); 
		     String viewLobIdObj=(String)request.getAttribute("viewLobId");
		     String viewRoleIdObj=(String)request.getAttribute("viewRoleId");
		     String viewUserId=(String)request.getAttribute("viewUserId");
		%>
              <input type="hidden" name="viewId" value="<%=viewRoleIdObj%>"/>
              <input type="hidden" name="userId" value="<%=viewUserId%>"/>
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
                <tr>
                  <td width="100%" class="subDeptTablePad"><!-- CONTENTS START HERE -->
                      <table  border="0" cellpadding="0" cellspacing="0"  align="center" class="tblInnerContainer" width="100%">
                        <form name="frmListLobViewPoint" id="frmListLobViewPoint" method="post" action="artifactMapping.html">
                          <input type="hidden" name="artiMapProcess" value="insertLobViewpointMapping" />
                          <tr>
                            <td class="tableLeft" >:</td>
                            <td class="tableRight" ><select name="lobId" class="selectboxOne" >
                                <option  selected="selected" value="">Select One</option>
                                <%
                                                ArrayList viewRoleLOBList = (ArrayList)request.getAttribute("viewRoleLOBList");
           									 if(viewRoleLOBList!=null && viewRoleLOBList.size()!=0){
           							Iterator itViewLob = viewRoleLOBList.iterator();
           							while(itViewLob.hasNext()){
           								String[] viewLobList = (String[])itViewLob.next();
           								String lob_id = viewLobList[0];
           								String lob_name = viewLobList[1];
           							   if (lob_id.equals(viewLobIdObj)){
                                                                                            
										%>
                                <option  value="<%=lob_id%>" selected="selected" ><%=lob_name%></option>
                                <%
											}
											else{
											%>
                                <option  value="<%=lob_id%>" ><%=lob_name%></option>
                                <%
											}
										}
           									 }									
								%>
                              </select>
                                <span class="asterisk">*</span></td>
                          </tr>
                          <tr>
                            <td colspan="2" class="tblMainHead"><span class="styleBoldTwo">Views related User List</span> </td>
                          </tr>
                          <tr>
                            <td><table border="0" cellpadding="0" align="center" cellspacing="1" class="formLayout" width="100%">
                                <%ArrayList viewRoleViewpointList = (ArrayList)request.getAttribute("viewRoleViewpointList");
           									 if(viewRoleViewpointList!=null && viewRoleViewpointList.size()!=0){
           							Iterator itViewPoint = viewRoleViewpointList.iterator();
           							while(itViewPoint.hasNext()){
           								String[] viewPointList = (String[])itViewPoint.next();
           								String viewPointId = viewPointList[0];
           								String viewPointName = viewPointList[1];
           								
		   %>
                                <tr>
                                  <td  height="26" bgcolor="#E3E1D2" class="alignCenter"><input type="checkbox" name="viewPointId" value="<%=viewPointId%>" /></td>
                                  <td  height="26" bgcolor="#E3E1D2" class="alignCenter"><%=viewPointName%></td>
                                </tr>
                                <%}
           							}else{ %>
                                <tr>
                                  <td height="26" colspan="2" bgcolor="#E3E1D2" class="alignCenter"><strong>No Records were Found !</strong></td>
                                  <%} %>
                                </tr>
                                <tr>
                                  <td><input name="submit" type="submit" class="gradBtn" value="Submit" align="middle"/>
                                  </td>
                                </tr>
                            </table></td>
                          </tr>
                          <!--end of the file-->
                        </form>
                      </table>
                    <!-- CONTENTS END HERE -->
                  </td>
                </tr>
            </table></td>
      </tr>
    </table></td>
  </tr>
</table></div>
<!--=======footer=================================-->
 <footer>			 
			
                    <!-- FOOTER STARTS HERE -->
                        
						<%@ include file = "../../include/menschForceFooter.jsp" %>
                    <!-- FOOTER ENDS HERE -->
               
 </footer>
</body>
</html>

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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<script src=js/basic.js" type="text/javascript" ></script>

</head>

<body>
  <%
                        String uname=(String)request.getAttribute("usrname");
                        String pwd=(String)request.getAttribute("cpwd");
			System.out.print("uname :"+uname +" "+ "pwd :"+pwd);
                    %>
					
					<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
<%if(uname=="" && uname==null){%>
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
		<%@ include file = "../../include/login_header.jsp" %>
		<!-- HEADER ENDS HERE -->
	</td>
  </tr>
  <tr>
    <td class="infoBar">
		<!-- INFO BAR STARTS HERE -->
		<%//@ include file = "../../include/infobar.jsp" %>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <%}else{%>

  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
		<%@ include file = "../../include/header.jsp" %>
		<!-- HEADER ENDS HERE -->
	</td>
  </tr>
  <tr>
    <td class="infoBar">
		<!-- INFO BAR STARTS HERE -->
		<%@ include file = "../../include/infobar.jsp" %>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <%}%>
  <tr>
    <td class="tableCommonBg">
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<%
				String source = (String)request.getAttribute("source");
				String eventTypeId = (String)request.getAttribute("eventTypeId");
				String compYear = (String)request.getAttribute("compYear");
				String action = "";
				if(source!=null && source.equalsIgnoreCase("fromEventEntry")){
					action = "OEELogin.do";
				}else{
					action = "MemberLogin.do";
				}
			%>
					<form name="confrm" method="post" action="<%=action%>"/>
				  <input type="hidden" name="cmd" value="checkLogin" />
				  <input type="hidden" name="eventTypeId" id="eventTypeId" value="<%=eventTypeId%>" />
				  <input type="hidden" name="compYear" id="compYear" value="<%=compYear%>" />
				
                    <input type="hidden" name="textfield" value="<%=uname%>" />
		    <input type="hidden" name="textfield2" value="<%=pwd%>" />
<%if(uname=="" && uname==null){%>
			<td width="500" class="subDeptTablePad">
		
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
		Membership: <span class="styleBoldTwo">Confirmation</span></td>
  </tr>
  <tr>

    <td colspan="2" class="tblDescrp" style="padding:10px;"><strong> You have successfully updated your profile </strong>
		<br />
        <br />
          Login now to access your information. <br />
    <br />
    <span>
    <input name="button" type="submit" class="gradBtn" value=" Login " />&nbsp;</span><br />
	</td>
  </tr>
  <tr>
  	<td>&nbsp;</td>
  </tr>
  </form>
  <tr>
    <td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
  </tr>
</table>

<!-- CONTENTS END HERE -->		
			</td>
			<%}else{%>
  <td colspan="2" class="tblDescrp" style="padding:10px;"><strong> You have successfully updated your profile </strong></td>
  <%}%>
		  </tr>
	  </table>
	
	</td>
  </tr>
  <tr>
    <td class="footerBg">
		<!-- FOOTER STARTS HERE -->
			<%@ include file = "../../include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
</table>

</body>
</html>

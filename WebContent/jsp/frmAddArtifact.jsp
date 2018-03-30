<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>

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
<script src="js/frmMeeEduActRecap.js" type="text/javascript" ></script>
<script src="js/frmrRolePrevilageValidate.js" type="text/javascript" ></script>

<script>
    function cancelAddArtifact(mainArtiId)
    {
        if(confirm("Do you want to Cancel and go back to List Page?"))
	{
        strURL = "./artifactMapping.html?artiMapProcess=artifactList&mapId="+mainArtiId;
	window.location.href = strURL;
        }
	else
	{
		return;
	}
    }
	
	function clearFields(obj)
{

	for(var i=0;i<obj.elements.length;i++)
	{
		if(obj.elements[i].type=='text')
		{
			obj.subArtiName.value = "";
		}

		
	}
}
</script>
</head>


<body>


<header id="header">
<%@ include file = "../../include/menschForceHeader.jsp" %>
  <!-- HEADER ENDS HERE -->
</header>
<div class="content">
<br /><br />
  <table width=100% height="350" border="0" cellpadding="0" cellspacing="0"  class="content_new" align="center">
            

    <tr>
	
      <td  align="center" valign="middle" class="tableCommonBg" >
	  <table border="1" cellpadding="0" align="center" cellspacing="0" class="formLayout" style="border:thin;border-style:groove;">
        <tr>
          <td colspan="2"  class="tblMainHead"> Sub Artifact: <span  class="styleBoldTwo"> Create </span></td>
        </tr>
        <tr>
          <td colspan="2" class="tblDescrp"> You can <strong>create</strong> a <strong>New Sub Artifact</strong> for all members and non-members Online Services Dashboard, right here! <br />
          </td>
        </tr>
        <% 
			 String mainArtiId="";
			 String mainArtifactName=(String) request.getAttribute("mainArtifactName");
				   mainArtiId=(String) request.getAttribute("mainArtiId");%>
        <table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
          <form name="frmRoleMgtRolePrev" id="frmRoleMgtRolePrev" action="artifactMapping.html" onsubmit="return formValidate(this);">
            <input type="hidden" name="artiMapProcess" value="createArtifact"/>
            <input type="hidden" name="mainArtiId" value="<%=mainArtiId%>"/>
            <tr>
              <td  colspan="2" class="tblDescrp"> Fields marked with an asterisk (<span class="asterisk">*</span>) are required. </td>
            </tr>
            <%
						  	String err = request.getParameter("err");
						  	if(err!=null) {
							 //mainArtiId=(String) request.getAttribute("mainArtiId");
						  %>
            <tr>
              <td class="styleError" colspan="2"><strong>Sub Artifact already exist</strong></td>
            </tr>
            <%
						  }
						  %>
            <tr>
              <td class="tableLeft"> Main Artifact:</td>
              <td class="tableRight"><input name="mainArtiname" id="mainArtiname" type="text" class="textboxOne" size="25" maxlength = "100" readonly="true" value="<%=mainArtifactName%>"/>
                  <span class="asterisk">*</span> </td>
            </tr>
            <tr>
              <td class="tableLeft">Sub Artifact:</td>
              <td class="tableRight"><input name="subArtiName" id="subArtiName" type="text" class="textboxOne" size="25" maxlength = "100"/>
                  <span class="asterisk">*</span></td>
            </tr>
            <tr>
              <td colspan="2" style="text-align:center" height="25" class="tableLeft"><input name="submit" type="submit" class="gradBtn" value="Create" />
                &nbsp;&nbsp;
                <input name="button" type="button" class="gradBtn" value="Clear" onclick = "clearFields(this.form)"/>
                &nbsp;&nbsp;
                <input name="button" type="button" class="gradBtn" value="Cancel" onclick ="cancelAddArtifact('<%=mainArtiId%>')" />
              </td>
            </tr>
          </form>
        </table>
      </table></td>
    </tr>
	<tr><td>&nbsp;</td></tr>
  </table></div>
<!--=======footer=================================-->
		 
			
                    <!-- FOOTER STARTS HERE -->
                        
						<%@ include file = "../../include/menschForceFooter.jsp" %>
                    <!-- FOOTER ENDS HERE -->
               

</body>
</html>

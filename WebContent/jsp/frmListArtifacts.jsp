<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<script language="javascript">
 function addRow(mainArtiName, mainArtifatId)
{

	strURL = "./artifactMapping.html?artiMapProcess=initCreateArtifact&mainArtifactName="+mainArtiName+"&mainArtiId="+mainArtifatId;
	window.location.href = strURL;
}

function editRow(tableID,mainArtiName, mainArtifatId)
{
	var artifactId;
	var chkCnt=0;
	var vFlag = false;
	var chkBoxCnt = document.frmRoleMgtListRole.chk.length;

    if(chkBoxCnt!=undefined && chkBoxCnt!='undefined' && chkBoxCnt > 1)
	{
		for(var i=0;i<chkBoxCnt;i++)
		{
			if(document.frmRoleMgtListRole.chk[i].checked==true)
			{
				   artifactId = document.frmRoleMgtListRole.chk[i].value;
				   chkCnt++;
			}

		}
	}
	else
	{
			if(document.frmRoleMgtListRole.chk.checked==true)
			{
				   artifactId = document.frmRoleMgtListRole.chk.value;
				   chkCnt++;
			}

	}
//Starts for checkbox
                if(chkCnt>1)
                {
                    for(var j=0;j<chkBoxCnt;j++)
                    {
                        vFlag = true;
                        document.frmRoleMgtListRole.chk[j].checked = false;
                        document.frmRoleMgtListRole.chkAll.checked = false;
                    }

                }
                //Ends for checkbox
		if(chkCnt==0){

			 alert("Please check one record to edit");
			return;
		}

			if(vFlag == true)
			{
				alert("Only one record can be edited.Please check one record to edit.");
                                return;
			}
//alert(mainArtiName);
                strURL = "./artifactMapping.html?artifactId="+artifactId+"&artiMapProcess=initeditArtifact&mainArtifactName="+mainArtiName+"&mainArtiId="+mainArtifatId;
		window.location.href = strURL;
}
    function deleteRow(tableID) {

	var roleId;
	var chkCnt=0;
	var vFlag = 0;
	var cannotDelete=0;
	var canDelete = 0;
	var chkBoxCnt = document.frmRoleMgtListRole.chk.length;
	if(chkBoxCnt!=undefined && chkBoxCnt!='undefined' && chkBoxCnt > 1)
	{
		for(var i=0;i<chkBoxCnt;i++)
		{
			if(document.frmRoleMgtListRole.chk[i].checked==true)
			{
				   if(document.frmRoleMgtListRole.flag[i].value==0)
				   {
					cannotDelete++;
					document.frmRoleMgtListRole.chk[i].checked = false;
                                         //For Debug Starts
                                document.frmRoleMgtListRole.chkAll.checked = false;
                                //For Debug Ends
				   }

				   if(document.frmRoleMgtListRole.flag[i].value==1)
				   {
					canDelete++;
				   }
			}

			if(cannotDelete > 0 && canDelete > 0)
			{
							vFlag = 1;
			}
			else if(cannotDelete > 0 && canDelete == 0)
			{
							vFlag = 2;
			}
			else if(cannotDelete == 0 && canDelete == 0)
			{
							vFlag = 3;
			}
			else if(cannotDelete == 0 && canDelete > 0)
			{
							vFlag = 4;

			}
		}
	}
	else
	{
			if(document.frmRoleMgtListRole.chk.checked==true)
			{
				   if(document.frmRoleMgtListRole.flag.value==0)
				   {
					cannotDelete++;
					document.frmRoleMgtListRole.chk.checked = false;
                                          //For Debug Starts
                                document.frmRoleMgtListRole.chkAll.checked = false;
                                //For Debug Ends
				   }

				   if(document.frmRoleMgtListRole.flag.value==1)
				   {
					canDelete++;
				   }
			}

			if(cannotDelete > 0 && canDelete == 0)
			{
							vFlag = 2;
			}
			else if(cannotDelete == 0 && canDelete == 0)
			{
							vFlag = 3;
			}
			else if(cannotDelete == 0 && canDelete > 0)
			{
							vFlag = 4;

			}

	}

	if(vFlag == 1)
	{
		confirm("Only the checked records will be deleted. Click the Delete button again");
	}

	if(vFlag == 2)
	{
	   alert("Checked records cannot be deleted as it is being referred or mapped.");
	}

	if(vFlag == 3)
	{
		alert("Please check the record(s) to be deleted");
	}

	if(vFlag == 4)
	{
		if(confirm("Only the checked records will be deleted. \nAre you sure you want to delete these records?"))
		{
			 document.frmRoleMgtListRole.artiMapProcess.value = 'deleteArtifact';
			 document.frmRoleMgtListRole.submit();
		}
	}

}


function checkAll()
{

		var chkBoxCnt = document.frmRoleMgtListRole.chk.length;
		if(chkBoxCnt!=undefined && chkBoxCnt!='undefined' && chkBoxCnt > 1)
		{
			for(var i=0;i<chkBoxCnt;i++)
			{
				 if(document.frmRoleMgtListRole.chkAll.checked==true)
				 {
					 document.frmRoleMgtListRole.chk[i].checked = true;
				 }
				 else
				 {
					document.frmRoleMgtListRole.chk[i].checked = false;
				 }
			}
		}
		else
		{
				 if(document.frmRoleMgtListRole.chkAll.checked==true)
				 {
					 document.frmRoleMgtListRole.chk.checked = true;
				 }
				 else
				 {
					document.frmRoleMgtListRole.chk.checked = false;
				 }

		}



}


function postData(){
	if(document.frmView.viewId.value!=""){
		document.frmView.artiMapProcess.value = "artifactList";
		//alert(frmRewalList.eventProcess.value);
		document.frmView.method="post";
		document.frmView.action="artifactMapping.html";
		document.frmView.submit();
	}
	else{
		alert("Select any one View");
		document.frmView.viewId.focus();
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
	  <table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab" style="border:thin;border-style:groove;">
        <tr>
         
          <% String mainArtifactName1=(String) request.getAttribute("artifactName");
				  String mainArtiId1=(String) request.getAttribute("mapPerId");
				   String retViewId=(String) request.getAttribute("viewId");
				  
				  %>
          <td width="500" class="subDeptTablePad"><!-- CONTENTS START HERE -->
              <table width="100%"  border="1"  align="center" cellpadding="0" cellspacing="0" class="formLayout">
                <tr>
                  <td colspan="5" class="tblMainHead"><strong><font color="#FF0000"><%=mainArtifactName1%> Artifact Table</font></strong></td>
                </tr>
                <tr>
                  <td colspan="5" class="tblDescrp"> To create a Sub Artifact for the respective Main Artifact click <strong>Add</strong> button.<br/>
                    To edit a Sub Artifact select the checkbox before each record and then click on the <strong>Edit</strong> button.<br/>                  </td>
                </tr>
                <tr height="30">
                  <td align= "right"><input type="hidden" name="mainArtifactName" value="<%=mainArtifactName1%>" />
                      <input type="hidden" name="mainArtiId" value="<%=mainArtiId1%>" />
                      <input name="button" type="button" class="gradBtn" onclick="addRow('<%=mainArtifactName1%>','<%=mainArtiId1%>')" value="Add" align="right"/>
                      <input name="button" type="button" class="gradBtn" onclick="editRow('dataTable','<%=mainArtifactName1%>','<%=mainArtiId1%>')" value="Edit" align="right"/>
                      <!--<input type="submit" value="Delete" onclick="deleteRow('dataTable')" class="gradBtn" align="right" name="del"/>-->                  </td>
                  <% 
				if(sessionRoleName!=null && sessionRoleName.equalsIgnoreCase("Admin")){
				
				%>
                  <form name="frmView" id="frmView">
                    <input type="hidden" name="artiMapProcess" value="" />
                  </form>
                </tr>
                <tr height="10">
                  <td align= "right"><font color="#0000FF"><strong>Select a View:</strong></font>
                      <select name="viewId" id="viewId" class="selectboxOne" onchange="postData();">
                        <option selected="selected" value="">Select One</option>
                        <%
									ArrayList viewDrop = (ArrayList)request.getAttribute("viewDrop");
									if(viewDrop!=null && viewDrop.size()!=0){
										Iterator itViewDropList = viewDrop.iterator();
										while(itViewDropList.hasNext()){
											String[] viewObj = (String [])itViewDropList.next();
											
											String privViewId = viewObj[0];
											String privViewName = viewObj[1];
											String viewRoleId = viewObj[2];
											String viewEntityId = viewObj[3];
											if(privViewId.equals(retViewId)){
											%>
                        <option value="<%=privViewId%>" selected="selected"><%=privViewName%></option>
                        <%
											 }
											 
											 else{
											 %>
                        <option value="<%=privViewId%>"><%=privViewName%></option>
                        <%
											 }
										}
									}
							%>
                      </select>                  </td>
                </tr>
            </table></td>
          <%}%>
        </tr>
        <tr>
          <%
				  
				                String artifactId=null;
								String subArtifactName ="";						
                                String artifactName1="";
                                                               
						 ArrayList artiList = (ArrayList) request.getAttribute("allartifactList");
          					 if(artiList!=null && artiList.size()!=0){
							Iterator it = artiList.iterator();
							%>
          <td><table id="dataTable" width="760" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout" style='table-layout:fixed'>
              <form name="frmRoleMgtListRole" id="frmRoleMgtListRole">
                <input type="hidden" name="artiMapProcess" value="initeditArtifact" />
                <tr>
                  <td width="15" class="tblRowHeadTwo"><input type="checkbox" name="chkAll" onclick = "checkAll()" />                  </td>
                  <td width="360" height="27" class="tblRowHeadTwo">Sub Artifact Name </td>
                </tr>
                <%
						
							while(it.hasNext()){
								String[] s = (String[])it.next();
								 artifactId = s[0];
								 subArtifactName = s[1];
							
                                                                 //artifactName1=s[2];
                                                               
                                                                int chkBoxIndex = 0;
							

		                %>
                <input type="hidden" name="artifactId" value="<%=artifactId%>" />
                <tr>
                  <td class="listCellBg"><input type="checkbox" name="chk" value ="<%=artifactId%>" /></td>
                  <td height="26" class="listCellBg" style = "text-align: center;word-wrap: break-word;" ><%=subArtifactName%></td>
                </tr>
                <%
                                               
                                                chkBoxIndex++;
                                              
                                                        }
					 }
					  else {%>
                <tr>
                  <td colspan="5" height="27" align="center" class="tblRowHeadTwo"> No Records are found</td>
                </tr>
                <% } %>
              </form>
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
</body>
</html>

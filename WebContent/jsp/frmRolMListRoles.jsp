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
<%@ page import="java.util.*" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
<!--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
<script language="javascript">
 function addRow()
{
	strURL = "./roles.html?roleProcess=initCreateRole";
	window.location.href = strURL;
}

function editRow(tableID)
{
	var roleId;
	var chkCnt=0;
	var vFlag = false;
	var chkBoxCnt = document.frmRoleMgtListRole.chk.length;

    if(chkBoxCnt!=undefined && chkBoxCnt!='undefined' && chkBoxCnt > 1)
	{
		for(var i=0;i<chkBoxCnt;i++)
		{
			if(document.frmRoleMgtListRole.chk[i].checked==true)
			{
				   roleId = document.frmRoleMgtListRole.chk[i].value;
				   chkCnt++;
			}

		}
	}
	else
	{
			if(document.frmRoleMgtListRole.chk.checked==true)
			{
				   roleId = document.frmRoleMgtListRole.chk.value;
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

                strURL = "./roles.html?roleId="+roleId+"&roleProcess=initeditRole&Submit2=Edit";
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
			 document.frmRoleMgtListRole.roleProcess.value = 'deleteRole';
			 document.frmRoleMgtListRole.submit();
		}
	}

}

/*function multiChkBoxValidation(tableID)
{
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
            var chkCnt = 0;

            for(var i=0; i<rowCount; i++) {
                var row = table.rows[i];
                var chkbox = row.cells[0].childNodes[0];
                var rolename = row.cells[1].childNodes[0];
                var roledesc = row.cells[2].childNodes[0];
                var activeradio = row.cells[3].childNodes[0];
                var inactiveradio = row.cells[4].childNodes[0];

                if(null != chkbox && true == chkbox.checked)
                {

					if(document.frmRoleMgtListRole.chk.checked==true)
					{
						alert("First enter the details for new Role and click Save button");
						chkbox.checked = false;
						return;

					}

                }
				else{

						rolename.disabled =true;
						roledesc.disabled =true;
						activeradio.disabled=true;
						inactiveradio.disabled =true;

				}


            }
}*/

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

    </script>
<!--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
<title>menschForce</title>
</head>

<body>
<!--========================================================
HEADER
=========================================================-->
  <div id="header">
    <!-- HEADER STARTS HERE -->
  <%@ include file = "../../include/menschForceHeader.jsp" %>
      <!-- HEADER ENDS HERE -->
    </div>
	
<!--=======content================================-->

<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					
                   <h3 class="title">Maintain Roles : Listings</h3>
				  
				 </div>
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-2 col-md-2 col-sm-2">
					</div>
					<div class="col-lg-10 col-md-10 col-sm-10">
						 <p>You are viewing the list of Roles created by you.</p>
				   </div>
				 </div>
				 
				  <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-2 col-md-2 col-sm-2">
					</div>
					<div class="col-lg-10 col-md-10 col-sm-10">
						To create a Role click Add button.<br/>
            			To edit a Role select the checkbox before each record and then click on the Edit button.<br/>
            			To delete Roles select the checkbox(s) before each record and then click on the Delete button.
				   </div>
				 </div>
				 
				  <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
                          
				 
				   <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
							<button name="button" type="button" onclick="addRow()" value="Add" align="right" class="button-add" >Add</button>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
								<button name="button2" type="button" class="button-edit" onclick="editRow('dataTable')" value="Edit">Edit</button>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
								<button type="submit" value="Delete" onclick="deleteRow('dataTable')" class="button-dang" align="right" name="del">Delete</button>
							</div>
							   
							
					</div>
					
					 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
	 

                    <form name="frmRoleMgtListRole" id="frmRoleMgtListRole" action="roles.do" >
                      <input type="hidden" name="roleProcess" value="initeditRole" />
					  
					   <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="checkbox" name="chkAll" onclick = "checkAll()" />
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6 subtitle">
							Role
                            </label>
							</div> 
							<div class="col-lg-3 col-md-3 col-sm-3">
							<label class="name form-div-6 subtitle">
							Description
                            </label>
							</div> 
							<div class="col-lg-1 col-md-1 col-sm-1">
							<label class="name form-div-6 subtitle">
							Active
                            </label>
							</div> 
							<div class="col-lg-1 col-md-1 col-sm-1">
							<label class="name form-div-6 subtitle">
							Inactive
                            </label>
							</div> 
							
						</div>
                      
                      
                      <%
						 ArrayList roleList = (ArrayList) request.getAttribute("allRoleList");
          					 if(roleList!=null && roleList.size()!=0){
							Iterator it = roleList.iterator();
							while(it.hasNext()){
								String[] s = (String[])it.next();
								String roleId = s[0];
								String rolName = s[1];
							//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                                                                String roledesc=s[2];
                                                                String status=s[3];
                                                                String flag=s[4];
                                                                int chkBoxIndex = 0;
							//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011

		                %>
						
                      <input type="hidden" name="roleId" value="<%=roleId%>" />
					  
					   <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="checkbox" name="chk" value ="<%=roleId%>" />
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<%=rolName%>
							</div> 
							<div class="col-lg-3 col-md-3 col-sm-3">
							<%=roledesc%>
							</div> 
							
							 <%   if(Integer.parseInt(status)==1){  %>
							 
							<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="radio" name="<%=roleId%>" value="1" checked="checked" disabled="disabled" />
							</div> 
							
							<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="radio" name="<%=roleId%>" value="0"  disabled="disabled" />
							</div> 
							
							  <%}else{%>
							  
							<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="radio" name="<%=roleId%>" value="1"  disabled="disabled" />
							</div> 
							
							<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="radio" name="<%=roleId%>" value="0" checked="checked" disabled="disabled">
							</div> 
							
							 <% } %>
                        <input type ="hidden" name ="flag" value ="<%=flag%>"/>
							
						</div>
					  
                      
                      <%
                                                //Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                                                chkBoxIndex++;
                                                //End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                                                        }
					 }
					  else {%>
					  
                      <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-5 col-md-5 col-sm-5">
							</div>
							<div class="col-lg-7 col-md-7 col-sm-7">
								No Records are Found
							</div>
						</div>
                     
                      <% } %>
                    </form>
               	</div>
			</div>
		</div>
	</div>
<!--========================================================
FOOTER
=========================================================-->
  <div>
   
    
    <!-- FOOTER STARTS HERE -->
    
  		<%@ include file = "../../include/menschForceFooter.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
  </div>  
</body>
</html>

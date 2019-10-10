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
<script src="js/validate.js" type="text/javascript" ></script>
<script>


function search()
{
	
			var masterId=document.frmSearchListgroups.masterId.value;
			   strURL = "./artifactMapping.html?artiMapProcess=searchList&masterId="+masterId;
               window.location.href = strURL;
			
		
	
	}
	
	function AddGroups()
	{
		
		var masterId=document.frmSearchListgroups.masterId.value;
		   strURL = "./artifactMapping.html?artiMapProcess=AddGroups&masterId="+masterId;
           window.location.href = strURL;
	}
	function EditGroups()
	{
	
	var layer_value_id;
	var chkCnt=0;
	var vFlag = false;
	var masterId=document.frmSearchListgroups.masterId.value;
	var chkBoxCnt = document.frmSearchListgroups.chk.length;

    if(chkBoxCnt!=undefined && chkBoxCnt!='undefined' && chkBoxCnt > 1)
	{
		for(var i=0;i<chkBoxCnt;i++)
		{
			if(document.frmSearchListgroups.chk[i].checked==true)
			{
				layer_value_id = document.frmSearchListgroups.chk[i].value;
				   chkCnt++;
			}

		}
	}
	else
	{
			if(document.frmSearchListgroups.chk.checked==true)
			{
				layer_value_id = document.frmSearchListgroups.chk.value;
				   chkCnt++;
			}

	}
//Starts for checkbox
                if(chkCnt>1)
                {
                    for(var j=0;j<chkBoxCnt;j++)
                    {
                        vFlag = true;
                        document.frmSearchListgroups.chk[j].checked = false;
                        document.frmSearchListgroups.chkall.checked = false;
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
				
			strURL = "./artifactMapping.html?artiMapProcess=EditGroups&layer_value_id="+layer_value_id+"&masterId="+masterId;
		window.location.href = strURL;
}
	
	  function deleteGroups() {
			 var checkId=document.getElementById('chk');

			var chkCnt=0;					
					
						var canDelete = 0;
						var chkBoxCnt = document.frmSearchListgroups.chk.length;
						//alert("chkBoxCnt---"+chkBoxCnt);

						if(chkBoxCnt!=undefined && chkBoxCnt!='undefined' && chkBoxCnt > 1)
						{
							for(var i=0;i<chkBoxCnt;i++)
							{
								if(document.frmSearchListgroups.chk[i].checked==true)
								{
									canDelete++;
								}

							}
						}
						else
						{
								if(document.frmSearchListgroups.chk.checked==true)
								{
									canDelete++;
								}

						}

						if(canDelete == 0)
						{
							alert("Please check the record(s) for Delete.");
						}

						if(canDelete > 0)
						{
							if(confirm("Only selected records will be deleted.Are you sure you want to delete the same?"))

							{
									//alert("canDelete---"+canDelete);
								document.frmSearchListgroups.artiMapProcess.value ='deleteGroups';
								document.frmSearchListgroups.submit();

							}
						}
				 }
</script>
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
	
	
	<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">
				
				<div class="col-lg-11 col-md-11 col-sm-11">
					
                     <h3 class="title">Group - Details : List</h3>
				   
				    </div>
				 
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-1 col-md-1 col-sm-1 col-sm-offset-4">
							 <button type="button" class="button-add" name="add" value="Add" onclick="AddGroups();">Add</button>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
							<button type="button" name="edit" class="button-edit" value="Edit" onclick="EditGroups();">Edit</button>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
							 <button type="button" name="delete" class="button-dang" value="Delete" onclick="deleteGroups();">Delete</button>
							</div>
						</div>
					
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
				
				  <form name="frmSearchListgroups" id="frmSearchListgroups" method="post" action="artifactMapping.html">
                     <input type="hidden" name="artiMapProcess" value="" />
					 
                     <% String groupIdObj=(String)request.getAttribute("groupId"); %>
					 
					 
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-2" align="left">
								Groups    <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<select name="masterId" class="form-control" onchange="search(); ">
                                    <option  selected="selected" value="">Select One</option>
                                    <%
                                                ArrayList viewGroupSrchList = (ArrayList)request.getAttribute("groupList");
           									 if(viewGroupSrchList!=null && viewGroupSrchList.size()!=0){
           							Iterator itGprSrch = viewGroupSrchList.iterator();
           							while(itGprSrch.hasNext()){
           								String[] groupSrchList = (String[])itGprSrch.next();
           								String group_id_srch = groupSrchList[0];
           								String group_name_srch = groupSrchList[1];
           							   if (group_id_srch.equals(groupIdObj)){
                                                                                            
										%>
                                    <option  value="<%=group_id_srch%>" selected="selected" ><%=group_name_srch%></option>
                                    <%
											}
											else{
											%>
                                    <option  value="<%=group_id_srch%>" ><%=group_name_srch%></option>
                                    <%
											}
										}
           									 }									
								%>
                                  </select>
                             
							</div>
						
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						
							
				<div class="col-lg-12 col-md-12 col-sm-12">
				
					<div class="table-row-line wrapper header">
					<div class="col-lg-1 col-md-1 col-sm-1 col-sm-offset-2">
					<label class="name form-div-2" >  <input type="checkbox" name="chkall" value="" /> </label>
					</div>
					
					<div class="col-lg-4 col-md-4 col-sm-4">
					<label class="name form-div-2 subtitle">Group</label>
					</div>
					
					<div class="col-lg-3 col-md-3 col-sm-3">
					<label class="name form-div-2 subtitle">Details</label>
					</div>
                    </div>       
				
				</div>
				
				
                            
                              <%
  ArrayList viewGroupAllList = (ArrayList)request.getAttribute("viewGroupList");
	 if(viewGroupAllList!=null && viewGroupAllList.size()!=0){
Iterator itViewGrp = viewGroupAllList.iterator();
while(itViewGrp.hasNext()){
String[] viewGroupAllStr = (String[])itViewGrp.next();
String layer_value_id = viewGroupAllStr[0];
String masterIdList = viewGroupAllStr[1];
String layer_value = viewGroupAllStr[2];
String masterNameList = viewGroupAllStr[3];
  %>
  
  
			<div class="col-lg-12 col-md-12 col-sm-12">
				
				<div class="table-row">	
					<div class="col-lg-1 col-md-1 col-sm-1 col-sm-offset-2">
					<input type="checkbox" name="chk" value="<%=layer_value_id%>" />
					</div>
					
					<div class="col-lg-4 col-md-4 col-sm-4">
						<%=masterNameList%>
					</div>
					
					<div class="col-lg-3 col-md-3 col-sm-3">
					<%=layer_value%>
					</div>
                           
				</div>
				</div>
                              
                           <%}}else{ %>
						   
						   
						   <div class="col-lg-12 col-md-12 col-sm-12">
						   
							<div class="col-lg-5 col-md-5 col-sm-5">
							</div>
							<div class="col-lg-7 col-md-7 col-sm-7">
							<label class="name form-div-2">No Records Found</label>
							
							</div>
						</div>
                              
							  <%}%>
							  
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
    
    <%@ include file = "../../include/FooterProcess.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>
</body>
</html>

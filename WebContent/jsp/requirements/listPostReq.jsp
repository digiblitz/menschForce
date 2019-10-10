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

<style>
as {
    list-style-type: none;
    margin: 0;
    padding: 0;
    width: 200px;
    background-color: ;
}

aa a {
    display: block;
    color: #000;
    padding: 8px 16px;
    text-decoration: none;
}
aa a.active {
    background-color: #f23739;
    color: white;
}

aa a:hover:not(.active) {
    background-color: #555;
    color: white;
}

#Post_Requirement label.error{
	color:red;
}
</style>
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
function isNumber(evt) {    
  var charCode = (evt.which) ? evt.which : evt.keyCode;     
  if (charCode != 46 && charCode > 31   && charCode != 43    
  && (charCode < 48 || charCode > 57))         
  return false;      
  return true; }
  
   $(document).ready(function(){

  
		 $('form[name="Post_Requirement"]').validate({
			rules: {
				
				
				minExperience:
				{
				   required:true, 
				   maxlength:3,   
				   CheckExperience:true 
				},
				maxExperience:{
				   required:true, 
				   maxlength:3,   
				   CheckExperience:true   
				 }
				
			},
			messages:{
				
				
				minExperience:{
					required:"Please select the minimum required Experience",
				},
				maxExperience:{
					required:"Please select the maximum required Experience",
				},
				
			},
			errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}
			
	});
  
	 
	 
	 $.validator.addMethod("CheckExperience",  function(value, element) {   
  // put your own logic here, this is just a (crappy) example 
  return value.match(/^\d*[0-9,+](|.\d*[0-9,+]|,\d*[0-9,+])?$/);     }, 
  "Enter valid experience eg. 2+,2"   );
	 
	$('#btnsubmit').click(function() {
      checked = $("input[type=checkbox]:checked").length;

      if(!checked) {
        alert("You must check at least one checkbox.");
        return false;
      }

    });

$('#skills').change(function(){
   $("#skills_value").prop("disabled", !$(this).is(':checked'))
    
});	
$('#Industry').change(function(){
   $('#Industry_value').prop("disabled", !$(this).is(':checked'))
    
});	
$('#position').change(function(){
   $("#position_value").prop("disabled", !$(this).is(':checked'))
    
});	
$('#jobtype').change(function(){
   $("#jobtype_value").prop("disabled", !$(this).is(':checked'))
    
});	
$('#location').change(function(){
   $("#location_value").prop("disabled", !$(this).is(':checked'))
    
});	
	 
	
$('#Rate_Hour').change(function(){
   $("#rate_range_from").prop("disabled", !$(this).is(':checked')) &&
    $("#rate_range_to").prop("disabled", !$(this).is(':checked'))
});

$('#Salary_Annum').change(function(){
   $("#salary_range_from").prop("disabled", !$(this).is(':checked')) &&
    $("#salary_range_to").prop("disabled", !$(this).is(':checked'))
});

$('#Freshness_Days').change(function(){
   $("#days").prop("disabled", !$(this).is(':checked')) 
    
});
$('#Experience_Years').change(function(){
   $("#minExperience").prop("disabled", !$(this).is(':checked')) &&
    $("#maxExperience").prop("disabled", !$(this).is(':checked')) 
    
});

 $(function () {
        $("#skills").click(function () {
            if ($(this).is(":checked")) {
                $("#skills_value1").show();
            } else {
                $("#skills_value1").hide();
            }
        });
    });
	
	$(function () {
        $("#Industry").click(function () {
            if ($(this).is(":checked")) {
                $("#Industry_value1").show();
            } else {
                $("#Industry_value1").hide();
            }
        });
    });
	
	$(function () {
        $("#position").click(function () {
            if ($(this).is(":checked")) {
                $("#position_value1").show();
            } else {
                $("#position_value1").hide();
            }
        });
    });
	
	$(function () {
        $("#jobtype").click(function () {
            if ($(this).is(":checked")) {
                $("#jobtype_value1").show();
            } else {
                $("#jobtype_value1").hide();
            }
        });
    });
	$(function () {
        $("#location").click(function () {
            if ($(this).is(":checked")) {
                $("#location_value1").show();
            } else {
                $("#location_value1").hide();
            }
        });
    });



 });

function postDataForPagination() {
	//alert("check");
	var currentPageNo = document.ListPostRequirements.currentPageNo.value;
	strURL = "ListPostRequirements.html?currentPageNo="+currentPageNo;
	window.location.href = strURL;
}
function postDataForPagination1() {
	//alert("yyyy");
	var currentPageNo = document.ListPostRequirements.currentPageNo.value;
location.href="ListPostRequirementsByOpenStatus.html?currentPageNo="+currentPageNo;
}

function postDataForPagination2() {
	//alert("yyyy");
	var currentPageNo = document.ListPostRequirements.currentPageNo.value;
	//alert(currentPageNo);
	
	location.href="ListPostRequirementsByClosedStatus.html?currentPageNo="+currentPageNo;
}
function postDataForPagination3() {
	//alert("yyyy");
	var currentPageNo = document.ListPostRequirements.currentPageNo.value;
	//alert(currentPageNo);
	
	location.href="ListPostRequirementsByOnHoldStatus.html?currentPageNo="+currentPageNo;
}
function postDataForPagination4() {
	//alert("yyyy");
	var currentPageNo = document.ListPostRequirements.currentPageNo.value;
	//alert(currentPageNo);
	
	location.href="ListPostRequirementsByDeclinedStatus.html?currentPageNo="+currentPageNo;
}
function postDataForPagination5() {
	//alert("yyyy");
	var currentPageNo = document.ListPostRequirements.currentPageNo.value;
	//alert(currentPageNo);
	
	location.href="PostRequirements.html?currentPageNo="+currentPageNo;
}
function postDataForPagination6() {
	//alert("yyyy");
	var currentPageNo = document.ListPostRequirements.currentPageNo.value;
	//alert(currentPageNo);
	
	location.href="ListPostRequirementsByFreshness.html?currentPageNo="+currentPageNo;
}




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
<title>Post Requirements List</title>
<%
String pageStatus = null;
pageStatus = (String)request.getAttribute("pageStatus");
System.out.println("pageStatus--------------"+pageStatus);
%>
<%String pageName = null;
pageName = (String)request.getAttribute("page");
System.out.println("pageName--------------"+pageName);

String reqMails = null;
reqMails = (String)request.getAttribute("reqMails");
System.out.println("reqMails--------------"+reqMails);

String createStatus = null;
createStatus = (String)request.getAttribute("createStatus");
System.out.println("createStatus--------------"+createStatus);

%>
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
            
            <div class="col-lg-12 col-md-12 col-sm-12" >
			
			
            	<%if(createStatus != null && createStatus.equalsIgnoreCase("success")){ %>
				
				<div class="alert alert-success">
				<span style="font-size:22px; align:center;">Successfully Applied for the Job!</span> 
				</div> 
            		
            	<%}else if(createStatus != null && createStatus.equalsIgnoreCase("fail")){ %>
				
				<div class="alert alert-danger">
				<span style="font-size:22px; align:center;">Failed Applied for the Job!</span> 
				
				</div>
            		
            	<%}else{ %>
            	<%}%>
			</div>
				 
				<div class="col-lg-12 col-md-12 col-sm-12" style="height:20px">
					
								  <%if(pageStatus != null && pageStatus.equalsIgnoreCase("postReqList") && reqMails == null){ %>

										 <h3 class="title">Post Requirements: Listing</h3>
										
								<%}else if(pageStatus != null && pageStatus.equalsIgnoreCase("open")){ %>
								
										 <h3 class="title">Opened Jobs</h3>
										
									<%}else if(pageStatus != null && pageStatus.equalsIgnoreCase("closed")){ %>
									
									 <h3 class="title"> Closed Jobs </h3>
									
									<%}else if(pageStatus != null && pageStatus.equalsIgnoreCase("onhold")){ %>
									
									 <h3 class="title">OnHold Jobs </h3>
									<%}else if(pageStatus != null && pageStatus.equalsIgnoreCase("declined")){ %>
									 <h3 class="title">Declined Jobs</h3>
									<%}else if(reqMails != null && reqMails.equalsIgnoreCase("RequirementMails")){ %>
									
									 <h3 class="title">Requirement List</h3>
									<%}else{%>
									
									 <h3 class="title">Post Requirements: Listing</h3>
									<%}%>
										
										
						
				  
				   
				 </div>
				 
					
					 <%	
						String nofPages = (String) request.getAttribute("totalNofPages");
								int pages = Integer.parseInt(nofPages);
								String currentPageNo = (String) request.getAttribute("currentPageNo");
								int totalNofPages=0;
								if(currentPageNo==null){
									totalNofPages = 1;
								}
								else{
							   totalNofPages = Integer.parseInt(currentPageNo);
								}		
								
							%>  
						
					
					<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-1 col-md-1 col-sm-1">
					  &nbsp;
					  </div>
					<div class="col-lg-1 col-md-1 col-sm-1">
					<form class='form-horizontal'  name="ListPostRequirements" id="myform" method="post" action="ListPostRequirements.html">
					 <label class="name form-div-6" style="color:#0072c6">Pages :</label>
					  <%if(pageName != null &&  pageName.equalsIgnoreCase("ListPostRequirement")){%>
					
					<select name="currentPageNo" id="currentPageNo" class="form-control" onchange="postDataForPagination();">
													<%
														for(int i = 1; i <=pages; i++) {
															
															if(totalNofPages==i){
															%>
																<option selected="selected" value="<%=i%>"><%=i%></option>
															<%
															}
															else{
															%>
																<option value="<%=i%>"><%=i%></option>
															<%
															}
														}
														%>
											  </select>
					<%}else if(pageName != null &&  pageName.equalsIgnoreCase("OpenStatus")){%>
					<select name="currentPageNo" id="currentPageNo" class="form-control" onchange="postDataForPagination1();">
													<%
														for(int i = 1; i <=pages; i++) {
															
															if(totalNofPages==i){
															%>
																<option selected="selected" value="<%=i%>"><%=i%></option>
															<%
															}
															else{
															%>
																<option value="<%=i%>"><%=i%></option>
															<%
															}
														}
														%>
					</select>
					<%}else if(pageName != null &&  pageName.equalsIgnoreCase("ClosedStatus")){%>
					<select name="currentPageNo" id="currentPageNo" class="form-control" onchange="postDataForPagination2();">
													<%
														for(int i = 1; i <=pages; i++) {
															
															if(totalNofPages==i){
															%>
																<option selected="selected" value="<%=i%>"><%=i%></option>
															<%
															}
															else{
															%>
																<option value="<%=i%>"><%=i%></option>
															<%
															}
														}
														%>
					</select>
					<%}else if(pageName != null &&  pageName.equalsIgnoreCase("OnHoldStatus")){%>
					<select name="currentPageNo" id="currentPageNo" class="form-control" onchange="postDataForPagination3();">
													<%
														for(int i = 1; i <=pages; i++) {
															
															if(totalNofPages==i){
															%>
																<option selected="selected" value="<%=i%>"><%=i%></option>
															<%
															}
															else{
															%>
																<option value="<%=i%>"><%=i%></option>
															<%
															}
														}
														%>
					</select>
					<%}else if(pageName != null &&  pageName.equalsIgnoreCase("DeclinedStatus")){%>
					<select name="currentPageNo" id="currentPageNo" class="form-control" onchange="postDataForPagination4();">
													<%
														for(int i = 1; i <=pages; i++) {
															
															if(totalNofPages==i){
															%>
																<option selected="selected" value="<%=i%>"><%=i%></option>
															<%
															}
															else{
															%>
																<option value="<%=i%>"><%=i%></option>
															<%
															}
														}
														%>
					</select>
					<%}else if(pageName != null &&  pageName.equalsIgnoreCase("PostRequirement")){%>
					<select name="currentPageNo" id="currentPageNo" class="form-control" onchange="postDataForPagination5();">
													<%
														for(int i = 1; i <=pages; i++) {
															
															if(totalNofPages==i){
															%>
																<option selected="selected" value="<%=i%>"><%=i%></option>
															<%
															}
															else{
															%>
																<option value="<%=i%>"><%=i%></option>
															<%
															}
														}
														%>
					</select>
					<%}else if(pageName != null &&  pageName.equalsIgnoreCase("PostRequirementElse")){%>
					<select name="currentPageNo" id="currentPageNo" class="form-control" onchange="postDataForPagination5();">
													<%
														for(int i = 1; i <=pages; i++) {
															
															if(totalNofPages==i){
															%>
																<option selected="selected" value="<%=i%>"><%=i%></option>
															<%
															}
															else{
															%>
																<option value="<%=i%>"><%=i%></option>
															<%
															}
														}
														%>
					</select>
					<%}else if(pageName != null &&  pageName.equalsIgnoreCase("ListPostReqByFreshness")){%>
					<select name="currentPageNo" id="currentPageNo" class="form-control" onchange="postDataForPagination6();">
													<%
														for(int i = 1; i <=pages; i++) {
															
															if(totalNofPages==i){
															%>
																<option selected="selected" value="<%=i%>"><%=i%></option>
															<%
															}
															else{
															%>
																<option value="<%=i%>"><%=i%></option>
															<%
															}
														}
														%>
					</select>
					<%}%>of <%=pages%> 
					</form>	
					</div>
					
					</div>
					
						
								 	
							  
					
					
					
						
				 	
				 
				  <%if(pageStatus != null && pageStatus.equalsIgnoreCase("postReqList")){ %>
					
					 <div class="col-lg-12 col-md-12 col-sm-12" style="bottom: 5px;">
					 
						<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4" align="right">
								
								<%if(reqMails != null){%>
								
								<%}else{%>
								
								<a href="CreatePostRequirements.html" style="font-family: sans-serif;
    font-size: large;
    font-weight: 600;
    /* text-decoration: underline; */
    border-style: solid;
    border-color: #f23739;
    background-color: #f23739;
    color: #ffffff;
    border-radius: 4%;"><i class="fa fa-plus-square fa-lg" aria-hidden="true"></i>&nbsp;Post New Requirement</a>
								<%}%>
							
						    </div>
						</div>
						
						<%}else if(pageStatus != null && pageStatus.equalsIgnoreCase("open")){ %>
						
						
						<%}else if(pageStatus != null && pageStatus.equalsIgnoreCase("closed")){ %>
						
						
						<%}else if(pageStatus != null && pageStatus.equalsIgnoreCase("onhold")){ %>
						
						
						<%}else if(pageStatus != null && pageStatus.equalsIgnoreCase("declined")){ %>
						
						
						<%}else if(pageStatus != null && pageStatus.equalsIgnoreCase("ListPostReqByFreshness")){ %>
						
						
						<%}else{ %>
						
						
						<%} %>

						
					
					

                    <form name="Post_Requirement" id="Post_Requirement" action="" >
					
                      
					 
					 <div class ="container-fluid">
					 <div class ="row" >
					   
					  <div class="col-lg-12 col-md-12 col-sm-12">
					  <div class="col-lg-1 col-md-1 col-sm-1">
					  &nbsp;
					  </div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							
							<as>
						  <aa><a class="active" >Search Criteria</a></aa>
						  <div class="col-lg-12 col-md-12 col-sm-12">
						  &nbsp;
						  </div></br>
							<input type="checkbox" name="skills"  id="skills">  &nbsp;  Skills</br>
							<div id="skills_value1" style="display: none">
    
							<input type="text" id="skills_value" name="skills_value" disabled="" placeholder="Enter a KeySkills" class="form-control" />
							</div>						 
							<input type="checkbox" name="Industry"  id="Industry" > &nbsp;  Industry</br>
							<div id="Industry_value1" style="display: none">
    
							<input type="text" id="Industry_value" name="Industry_value" disabled="" placeholder="Enter a IndustryType" class="form-control" />
							</div>							 

							<input type="checkbox" name="position"  id="position" > &nbsp;  Position</br>
							<div id="position_value1" style="display: none">
    
							<input type="text" id="position_value" name="position_value" disabled="" placeholder="Enter a Position" class="form-control" />
							</div>						 
		
							<input type="checkbox" name="jobtype"  id="jobtype"  > &nbsp;  Jobtype</br>
							<div id="jobtype_value1" style="display: none">
    
							<input type="text" id="jobtype_value"  name="jobtype_value" disabled="" placeholder="Enter a Jobtype" class="form-control" />
							</div>
							
							<input type="checkbox" name="location"  id="location" > &nbsp;  Location</br>
						  
							<div id="location_value1" style="display: none">
    
							<input type="text" id="location_value" name="location_value" disabled="" placeholder="Enter a location" class="form-control" />
							</div>
						  
						
						 </br>
						  
						  
						  
						  <aa><a class="active" class="check" >Experience</a></aa>
						  <div class="col-lg-12 col-md-12 col-sm-12">
						  &nbsp;
						  </div></br>
						 
						  <input type="checkbox" name="Experience_Years" id="Experience_Years">
						  <div class="row">
						  <div class="col-sm-6">Min
						   <input name="minExperience" type="text"   id="minExperience" disabled="" class="form-control"  placeholder="years" onkeypress="return isNumber(event)" required/>
							
							
							</div>
						  
						  <div class="col-sm-6">Max
						  <input name="maxExperience" type="text"   id="maxExperience" disabled="" class="form-control"  placeholder="years" onkeypress="return isNumber(event)" required/>
							
							
							</div></br>
							</div>
							
							 <div class="col-lg-12 col-md-12 col-sm-12">
						  &nbsp;
						  </div></br>
						  
						  <aa><a class="active" >Rate/Hour</a></aa>
						    <div class="col-lg-12 col-md-12 col-sm-12">
						  &nbsp;
						  </div></br>
						  <input type="checkbox" name="Rate_Hour" id="Rate_Hour">
						   <div class="row">
						   
						    
							
						   <div class="col-sm-6">
						   From<input type="text" name="rate_range_from" disabled="" id="rate_range_from" class="form-control" value="" placeholder="$" onkeypress="return isNumber(event)" required>
						   </div>
						   <div class="col-sm-6">
						  To<input type="text" name="rate_range_to" disabled="" id="rate_range_to" value="" class="form-control"placeholder="$" onkeypress="return isNumber(event)" required> 
						   </div>
						   </div>
						   
						    <div class="col-lg-12 col-md-12 col-sm-12">
						  &nbsp;
						  </div></br>
						  
						   <aa><a class="active" >Salary/Annum</a></aa>
						    <div class="col-lg-12 col-md-12 col-sm-12">
						  &nbsp;
						  </div></br>
						  <input type="checkbox" name="Salary_Annum" id="Salary_Annum">
						   <div class="row">
						   
						    
							
						   <div class="col-sm-6">
						   From<input type="text" name="salary_range_from" disabled="" id="salary_range_from" class="form-control" value="" placeholder="$" onkeypress="return isNumber(event)" required>
						   </div>
						   <div class="col-sm-6">
						  To<input type="text" name="salary_range_to" disabled="" id="salary_range_to" value="" class="form-control"placeholder="$" onkeypress="return isNumber(event)" required> 
						   </div>
						   </div>
						   
						    <div class="col-lg-12 col-md-12 col-sm-12">
						  &nbsp;
						  </div></br>
						   
						    <aa><a class="active" href="#news">Freshness</a></aa>
							 <div class="col-lg-12 col-md-12 col-sm-12">
						  &nbsp;
						  </div></br>
						   <input type="checkbox" name="Freshness_Days" id="Freshness_Days"  required> </br>
						  <select name="days" id="days" disabled="" class="form-control"  >
								
								<option value="0">Select</option>
								<option value="30">Last 30 Days</option>
								<option value="15">Last 15 Days</option>
								<option value="7">Last 7 Days</option>
								<option value="3">Last 3 Days</option>
								<option value="1">Last 1 Day</option>
							  </select> 
						</as>
						<div class="col-lg-12 col-md-12 col-sm-12">
						  &nbsp;
						  </div></br>
						  
						<button type="submit" name="search" id="btnsubmit" value="search" onclick='this.form.action="ListPostRequirementsByFreshness.html";'> &nbsp;  search</button>
						</div>
						 
						 </form>
							
					   <div class="col-lg-8 col-md-8 col-sm-8" style="border-color: #ffffff;background-color: #f9f9f9;border-style: groove;
    border-width: thin;">
                      
                      
                      <%
						 ArrayList PostReqList = (ArrayList) request.getAttribute("allPostReqList");
          					 if(PostReqList!=null && PostReqList.size()!=0){
							Iterator it = PostReqList.iterator();
							while(it.hasNext()){
								String[] s = (String[])it.next();
								String postReqUniqueId = s[0];
								String ClientIndustry = s[5];
								String Position = s[7];
								String Skills = s[10];
								String JobType = s[11];
								String LocationField = s[12];
								String Salary = s[21];
								String minExperience = s[26];
								String jobPostDate = s[29];
								String JobTitle = s[30];
								String jobPostCompanyCategory = s[31];
								String uniqueJobPostCompanyId = s[32];
					    		String jobPostCompanyId = s[33];
					    		String jobPostUserId = s[34];
					    		String jobPostUserName = s[35];
					    		String jobPostUserRoleId = s[36];
					    		String jobPostUserRoleName = s[37];
					    		String jobPostCompanyName = s[2];
					    		String maxExperience = s[38];
							

		                %>
						
						
                      <input type="hidden" name="postReqUniqueId" value="<%=postReqUniqueId%>" />
					  
							
							<div style="border-color: #ffffff;background-color: #f9f9f9;border-style: groove;
    border-width: thin;">
							<a href="ViewPostRequirementByUniquePostId.html?uniqueId=<%=postReqUniqueId%>&jobStatus=<%=pageStatus %>" style="">
								<span class="jobtitle" style="display:block;font-size:22px;background-color:#f23739;color:#fff;white-space:nowrap;overflow:hidden;text-overflow:ellipsis"><%=JobTitle%></span>
								</a>
<span class="jobtitle" style="display:block;float:right;color:#6dab3c;font-size:15px;text-decoration: underline;"><a href="initCreateRateNegotiation.html?uniqueId=<%=postReqUniqueId%>" style="color:#ef2748">Negotiate Rate</a></span>								
<a href="ViewPostRequirementByUniquePostId.html?uniqueId=<%=postReqUniqueId%>&jobStatus=<%=pageStatus %>" style="">
								<span class="client" style="display:block;padding-right:110px;font-weight:500;color:#ef2748;white-space:nowrap;overflow:hidden;text-overflow:ellipsis"><%=ClientIndustry%></span>
								<span class="exp" style="float:left;width:25%;padding-right:10px;font-weight: 300;color:#ef2748;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">
								<img src="images/exp_icon.png"  width="20px" height="20px" style="padding-top: 0px">
								<%=minExperience%> - <%=maxExperience%> years</span>
								<span class="location" style="float:left;width:25%;word-wrap:break-word;color:#ef2748;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">
								<img src="img/page1_icon1.png" alt="" style="background-color:#666;">
								<%=LocationField%></span>
								<span class="postlabel" style="float:left;width:25%;font-weight:500;color:#666;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">Job Type:
								<span class="skills" style="font-size:13px;color:#ef2748;line-height:20px;font-weight:500;text-align:justify;"><%=JobType%></span>
								</span>
								
								<div class="more" style="float: left;
    width: 100%;
    font-size: 13px;
    color: #666;
    line-height: 20px;
    font-weight: 300;
    text-align: justify;">
								<span class="postlabel" style="float:left;clear:left;
    width:50%;line-height:20px;font-weight:500;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">Key Skills :
	<span class="skills" style="font-size:13px;color:#ef2748;line-height:20px;font-weight:500;text-align:justify;"><%=Skills%></span>
	</span>
								
								</div><br>
								<div class="otherDetails"style="    background-color: #f9f9f9;
    overflow: hidden;
    clear: left;
    border-bottom: 2px solid #f9f9f9;">
	<span class="postlabel" style="border-right: 1px solid #f4f4f4;
    padding: 2px 2px;
    float: left;
    height: 30px;
    line-height: 30px;
    min-width: 25px;
    font-weight: 500;
	color: #666;">Salary :</span>
								<span class="salary" style="border-right: 1px solid #f4f4f4;
    padding: 2px 2px;
    float: left;
    height: 30px;
    line-height: 30px;
    min-width: 25px;
    font-weight: 500;
	color:#ef2748;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">
	
	<%=Salary%></span>
	<span class="postlabel" style="border-right: 1px solid #f4f4f4;
    padding: 2px 2px;
    float: left;
    height: 30px;
    line-height: 30px;
    min-width: 25px;
    font-weight: 500;
	color: #666;">Position :</span>
								<span class="salary" style="border-right: 1px solid #f4f4f4;
    padding: 2px 2px;
    float: left;
    height: 30px;
    line-height: 30px;
    min-width: 25px;
    font-weight: 500;
	color:#ef2748;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">
	
	<%=Position%></span>
	
	
								</div>
								<div style="float:right;color:#b7b7b9;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">Posted By - <%=jobPostCompanyCategory%> (<%=jobPostCompanyName%>), <%=jobPostDate%>
	</div>
								
</a>
	
							
							<br>
							
							</div>
							
						
						
                      <%}%>
					  </div>
						
						
						<!--<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-5 col-md-5 col-sm-5">
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							<button type="reset" class="btn-default btn3" name="Back" value="Back" onClick="history.go(-1);">Back</button>
							</div>
						</div>-->
						
					 <%}else {%>
					  
                      <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-5 col-md-5 col-sm-5">
							</div>
							<div class="col-lg-7 col-md-7 col-sm-7">
								<p>No Records are Found</p>
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<!--<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-6 col-md-6 col-sm-6">
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							<button type="reset" class="btn-default btn3" name="Back" value="Back" onClick="history.go(-1);">Back</button>
							</div>
						</div>-->
                     
                      <% } %>
					  </div>
					  </div>
					  
					  </div>	
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
    
   <%@ include file = "../../include/Footer.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>
</body>
</html>

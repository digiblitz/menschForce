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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Applied Candidate Details</title>
<style>
.table-row-line.header
{ 
	 display: block;
	 flex-direction:row;
    background-color: #dddddd;
    font-weight: bold;
    padding-top: 16px;
    padding-bottom: 35px;
   

}
.table-row-line {
    border-bottom: 1px solid #fcf8e3;
    border-collapse: collapse;
    margin-left: 63px;
    margin-right: 30px;
    padding-right: initial;
    padding-left: initial;

}
.table-row {
    border-bottom: 1px solid #fcf8e3;
    border-collapse: collapse;
    margin-left: 63px;
    margin-right: 30px;
    padding-right: initial;
    padding-left: initial;
	padding-top: 16px;
    padding-bottom: 35px;

}
</style>
<%String pageStatus = null;
String createStatus = null;
String editStatus = null;
pageStatus = (String)request.getAttribute("pageStatus");
createStatus = (String)request.getAttribute("createStatus");
editStatus = (String)request.getAttribute("editStatus");
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
	<script>
function filterSearchpopup(){
	//alert("hello cool");
	document.getElementById("filtersearch").style.display="block";
	document.getElementById("filterbutton").style.display="none";
}

function editRow(tableID)
{
	var uniqueId;
	var chkCnt=0;
	var vFlag = false;
	var chkBoxCnt = document.HotListCandidateList.chk.length;

    if(chkBoxCnt!=undefined && chkBoxCnt!='undefined' && chkBoxCnt > 1)
	{
		for(var i=0;i<chkBoxCnt;i++)
		{
			if(document.HotListCandidateList.chk[i].checked==true)
			{
				   uniqueId = document.HotListCandidateList.chk[i].value;
				   chkCnt++;
			}

		}
	}
	else
	{
			if(document.HotListCandidateList.chk.checked==true)
			{
				   uniqueId = document.HotListCandidateList.chk.value;
				   chkCnt++;
			}

	}
//Starts for checkbox
                if(chkCnt>1)
                {
                    for(var j=0;j<chkBoxCnt;j++)
                    {
                        vFlag = true;
                        document.HotListCandidateList.chk[j].checked = false;
                        document.HotListCandidateList.chkAll.checked = false;
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

                strURL = "./editHotListCandidate.html?uniqueId="+uniqueId;
		window.location.href = strURL;
}


function deleteRow(tableID) {

	var roleId;
	var chkCnt=0;
	var vFlag = 0;
	var cannotDelete=0;
	var canDelete = 0;
	var chkBoxCnt = document.HotListCandidateList.chk.length;
	if(chkBoxCnt!=undefined && chkBoxCnt!='undefined' && chkBoxCnt > 1)
	{
		
		for(var i=0;i<chkBoxCnt;i++)
		{
			if(document.HotListCandidateList.chk[i].checked==true)
			{
				   if(document.HotListCandidateList.flag[i].value==0)
				   {
					cannotDelete++;
					document.HotListCandidateList.chk[i].checked = false;
                                         //For Debug Starts
                                document.HotListCandidateList.chkAll.checked = false;
                                //For Debug Ends
				   }

				   if(document.HotListCandidateList.flag[i].value==1)
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
			if(document.HotListCandidateList.chk.checked==true)
			{
				   if(document.HotListCandidateList.flag.value==0)
				   {
					cannotDelete++;
					document.HotListCandidateList.chk.checked = false;
                                          //For Debug Starts
                                document.HotListCandidateList.chkAll.checked = false;
                                //For Debug Ends
				   }

				   if(document.HotListCandidateList.flag.value==1)
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
		if(confirm("Only the checked records will be deleted. Click the Delete button again")){
			var x = document.getElementsByName('HotListCandidateList');
			x[0].submit(); // Form submission
		}
			 
	}

	if(vFlag == 2)
	{
	   alert("Checked records cannot be deleted as it is being referred or mapped.");
	   return false;
	}

	if(vFlag == 3)
	{
		alert("Please check the record(s) to be deleted");
	}

	if(vFlag == 4)
	{
		if(confirm("Only the checked records will be deleted. \nAre you sure you want to delete these records?"))
		{
			var x = document.getElementsByName('HotListCandidateList');
			x[0].submit(); // Form submission
		}
	}
	
	

}


function checkAll()
{

		var chkBoxCnt = document.HotListCandidateList.chk.length;
		if(chkBoxCnt!=undefined && chkBoxCnt!='undefined' && chkBoxCnt > 1)
		{
			for(var i=0;i<chkBoxCnt;i++)
			{
				 if(document.HotListCandidateList.chkAll.checked==true)
				 {
					 document.HotListCandidateList.chk[i].checked = true;
				 }
				 else
				 {
					document.HotListCandidateList.chk[i].checked = false;
				 }
			}
		}
		else
		{
				 if(document.HotListCandidateList.chkAll.checked==true)
				 {
					 document.HotListCandidateList.chk.checked = true;
				 }
				 else
				 {
					document.HotListCandidateList.chk.checked = false;
				 }

		}



} 

$(document).ready(function(){
	
$("#minExp").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
             // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
             // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
	$("#maxExp").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
             // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
             // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
	$("#toRate").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
             // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
             // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
	$("#fromRate").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
             // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
             // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
});
</script>
  <!--========================================================
CONTENT
=========================================================-->
	<div class="content indent">
	<div class="thumb-box14">
       
       <div class="row">
       		<div class="col-lg-12 col-md-12 col-sm-12">
			<% if(createStatus != null && pageStatus.equalsIgnoreCase("success")){%>
				<p class="title" style="color:green">HotList Candidate created successfully!</p>
			<%}else if(createStatus != null && pageStatus.equalsIgnoreCase("fail")){ %>
				<p class="title" style="color:red">Failed to insert the HotList candidate!</p>
			<%}else{%>
			<%}%>
			</div>
		</div>
		
		<div class="row">
       		<div class="col-lg-12 col-md-12 col-sm-12">
			<% if(editStatus != null && editStatus.equalsIgnoreCase("success")){%>
				<p class="title" style="color:green">HotList Candidate updated successfully!</p>
			<%}else if(editStatus != null && editStatus.equalsIgnoreCase("fail")){ %>
				<p class="title" style="color:red">Failed to update the HotList candidate!</p>
			<%}else{%>
			<%}%>
			</div>
		</div>
				 
            <div class="row">			
				
				<div class="col-lg-12 col-md-12 col-sm-12">
				
					
					<% if(pageStatus != null && pageStatus.equalsIgnoreCase("CandidateResumeList")){%>
					<h3 class="title">Candidate Resume List</h3>
					<%}else if(pageStatus != null && pageStatus.equalsIgnoreCase("HotlistCandidateList")){ %>
					<h3 class="title">Hot List Candidate List</h3>
					<%}%>
					
					
					
				 </div>
				 </div>
				 <form name="HotListCandidateList" id="HotListCandidateList" method="post" action="DeleteHotListCandidate.html" class="formcss"  >
				   <input type="hidden" name="hotListDelProcess" value="" />
				
				  
				  <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
							<button name="button" type="button" onclick="window.location.href='initCreateHotListCandidate.html';"  value="Add" align="right" class="button-add" >Add</button>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
								<button name="button2" type="button" class="button-edit" onclick="editRow('dataTable')" value="Edit">Edit</button>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
								<button type="button" name="del" value="Delete" onclick="deleteRow('dataTable')" class="button-dang" align="right">Delete</button>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1" id="filterbutton">
							<button name="button" type="button" onclick="filterSearchpopup();"  value="Search" align="right" class="button-add" >Search</button>
							</div>
							   
							
					</div>
					
				   <div class="col-lg-12 col-md-12 col-sm-12">
				   &nbsp;
				   </div>
				   
				   
				  <div id="filtersearch" style="display:none">
				

						<input type="hidden" name="viewDetails" value="viewdetails"/>
						 <div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-4" >
									<label class="name">Total Experience (In Years)</label>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2">
									Min<input type="text" name="minExp"  class="form-control" id="minExp" placeholder="Example - 5">
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2">
									Max<input type="text" name="maxExp"  class="form-control" id="maxExp" placeholder="Example - 10">
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12">
							   &nbsp;
							   </div>
							<div class="col-lg-12 col-md-12 col-sm-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-4" >
									<label class="name">VISA Type</label>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2">
									<select name="visaType" class="form-control" id="visaType">
											<option value="">select</option>
											<option value="H1B">H1B</option>
											<option value="L1B">L1B</option>
											<option value="Green Card">Green Card</option>
											<option value="US Citizen">US Citizen</option>
											<option value="OPT EAD">OPT EAD</option>
								   </select>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12">
							   &nbsp;
							   </div>
							
							<div class="col-lg-12 col-md-12 col-sm-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-4" >
									<label class="name">Rate (In Dollors)</label>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2">
									From<input type="text" name="fromRate"  class="form-control" id="fromRate" placeholder="Example - 100">
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2">
									To<input type="text" name="toRate"  class="form-control" id="toRate" placeholder="Example - 500">
								</div>
							</div>
							
							<div class="col-lg-12 col-md-12 col-sm-12">
							   &nbsp;
							   </div>
							<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="col-lg-1 col-md-1 col-sm-1">
										
									 </div>
								   <div class="col-lg-1 col-md-1 col-sm-1 col-sm-offset-5">
									<button type="submit" class="button-add" name="Edit" value="Edit" onclick='this.form.action="SearchHotCandidateStatus.html";'>Search</button>
								   </div>
				   
							</div>
					</div>
				   <div class="col-lg-12 col-md-12 col-sm-12">
				   &nbsp;
				   </div>
					 </div>		
				 <div class="row">	
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-1 col-md-1 col-sm-1">
					&nbsp;
					</div>
					<div class="table-row-line wrapper header">
					<div class="col-lg-1 col-md-1 col-sm-1">
						<label class="name form-div-6" style="color:#0072c6">
							<input type="checkbox" name="chkAll" onClick = "checkAll()">
                        </label>
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
					</div>
						
					<div class="col-lg-1 col-md-1 col-sm-1">
						<label class="name form-div-6" style="color:#0072c6">
							Can Id
                        </label>
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
					</div>
						
						<div class="col-lg-1 col-md-1 col-sm-1">
					<label class="name form-div-6" style="color:#0072c6">
							First Name
                            </label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						</div>
						
						<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							Last Name
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					<!--div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							Phone
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div-->
					<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							E-mail
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							Total Experience
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						</div>
						
						<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							Current Location
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						</div>
						
					
					<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							VISA Type
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					
					<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							Rate
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					</div>
</div>					
                           
					</div>
					
					
					 <%ArrayList HotlistCandidateList=(ArrayList) request.getAttribute("HotlistCandidateList"); 
			//System.out.println(CandidateList.size()+" list ");
			 if(HotlistCandidateList!=null && HotlistCandidateList.size()!=0){
            Iterator itr = HotlistCandidateList.iterator();
    while (itr.hasNext()) {    
        String TempList[] = (String[])itr.next();
		String CANID=TempList[27];
		 String firstName = TempList[0];
		 String lastName = TempList[1];
		 String CandidateContactNumber=TempList[3];
		 String txttotalexperience = TempList[12];
		 String txtcurrentlocation=TempList[4];
		 String CandidateEMail=TempList[2];
		 String txtrate=TempList[9];
		 String visaType=TempList[5];
		 String UniqueId=TempList[26];
		 String CandidateResumeLoc = TempList[29];
    
    %>
	<input type="hidden" name="uniqueId" value="<%=UniqueId%>" />
	<input type="hidden" name="flag" value="1" />
	
    <div class="row">	
    <div class="col-lg-12 col-md-12 col-sm-12">	
	<div class="col-lg-1 col-md-1 col-sm-1">
	&nbsp;
	</div>
	<div class="table-row">
	<div class="col-lg-1 col-md-1 col-sm-1">
	<label><input type="checkbox" name="chk" value ="<%=UniqueId%>" /></label>
	</div>
	<div class="col-lg-1 col-md-1 col-sm-1">
	<a href="ViewListHotlistCandidateByUniqueId.html?uniqueId=<%=UniqueId%>" ><label style="text-decoration:underline;cursor:pointer"><%=CANID%></label></a>
	</div>
	<div class="col-lg-1 col-md-1 col-sm-1">
	<label><%=firstName%> </label>
	</div>
	<div class="col-lg-1 col-md-1 col-sm-1">
	<label><%=lastName%></label>
	</div>
	
	<!--div class="col-lg-1 col-md-1 col-sm-1">
	<label><%=CandidateContactNumber%></label>
	</div-->
	<div class="col-lg-2 col-md-2 col-sm-2">
	<label><%=CandidateEMail%></label>
	</div>
	<div class="col-lg-1 col-md-1 col-sm-1">
	<label><%=txttotalexperience%></label>
	</div>
	<div class="col-lg-2 col-md-2 col-sm-2">
	<label><%=txtcurrentlocation%></label>
	</div>
	<div class="col-lg-1 col-md-1 col-sm-1">
	<label><%=visaType%></label>
	</div>
	<div class="col-lg-1 col-md-1 col-sm-1">
	<label><%=txtrate%></label>
	</div>
	</label>
	</div>
	</div>
				</div>
	<%}%>
	
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1" align="right">
						<button type="reset" class="button-dang" name="Back" value="Back" onClick="history.go(-1);">Back</button>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
					</div>
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
				</div>
						
	<%}else{%>	
					
			  <div class="row">			
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6">
						<h3>No Record Found</h3>
					</div>
				 </div>
				 </div>	
				 <%} %>	
			</form>
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

<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
	var chkBoxCnt = document.frmfreshCandidate.chk.length;

    if(chkBoxCnt!=undefined && chkBoxCnt!='undefined' && chkBoxCnt > 1)
	{
		for(var i=0;i<chkBoxCnt;i++)
		{
			if(document.frmfreshCandidate.chk[i].checked==true)
			{
				   uniqueId = document.frmfreshCandidate.chk[i].value;
				   chkCnt++;
			}

		}
	}
	else
	{
			if(document.frmfreshCandidate.chk.checked==true)
			{
				   uniqueId = document.frmfreshCandidate.chk.value;
				   chkCnt++;
			}

	}
//Starts for checkbox
                if(chkCnt>1)
                {
                    for(var j=0;j<chkBoxCnt;j++)
                    {
                        vFlag = true;
                        document.frmfreshCandidate.chk[j].checked = false;
                        document.frmfreshCandidate.chkAll.checked = false;
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

                strURL = "./EditFreshCandidateByUniqueId.html?uniqueId="+uniqueId;
		window.location.href = strURL;
}


function deleteRow(tableID) {

	var roleId;
	var chkCnt=0;
	var vFlag = 0;
	var cannotDelete=0;
	var canDelete = 0;
	var chkBoxCnt = document.frmfreshCandidate.chk.length;
	if(chkBoxCnt!=undefined && chkBoxCnt!='undefined' && chkBoxCnt > 1)
	{
		
		for(var i=0;i<chkBoxCnt;i++)
		{
			if(document.frmfreshCandidate.chk[i].checked==true)
			{
				   if(document.frmfreshCandidate.flag[i].value==0)
				   {
					cannotDelete++;
					document.frmfreshCandidate.chk[i].checked = false;
                                         //For Debug Starts
                                document.frmfreshCandidate.chkAll.checked = false;
                                //For Debug Ends
				   }

				   if(document.frmfreshCandidate.flag[i].value==1)
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
			if(document.frmfreshCandidate.chk.checked==true)
			{
				   if(document.frmfreshCandidate.flag.value==0)
				   {
					cannotDelete++;
					document.frmfreshCandidate.chk.checked = false;
                                          //For Debug Starts
                                document.frmfreshCandidate.chkAll.checked = false;
                                //For Debug Ends
				   }

				   if(document.frmfreshCandidate.flag.value==1)
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
			var x = document.getElementsByName('frmfreshCandidate');
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
			var x = document.getElementsByName('frmfreshCandidate');
			x[0].submit(); // Form submission
		}
	}
	
	

}


function checkAll()
{

		var chkBoxCnt = document.frmfreshCandidate.chk.length;
		if(chkBoxCnt!=undefined && chkBoxCnt!='undefined' && chkBoxCnt > 1)
		{
			for(var i=0;i<chkBoxCnt;i++)
			{
				 if(document.frmfreshCandidate.chkAll.checked==true)
				 {
					 document.frmfreshCandidate.chk[i].checked = true;
				 }
				 else
				 {
					document.frmfreshCandidate.chk[i].checked = false;
				 }
			}
		}
		else
		{
				 if(document.frmfreshCandidate.chkAll.checked==true)
				 {
					 document.frmfreshCandidate.chk.checked = true;
				 }
				 else
				 {
					document.frmfreshCandidate.chk.checked = false;
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
	
  <!--========================================================
CONTENT
=========================================================-->
	<div class="content indent">
	<div class="thumb-box14">
       
            <div class="row">			
			
			<div class="col-lg-12 col-md-12 col-sm-12">
				
					<h3 class="title" >Fresh Candidate List</h3>
					
					
				 </div>	
				 
				 <form name="frmfreshCandidate" id="frmfreshcandidate" method="post" action="DeleteFreshCandidate.html"> 
					<input type="hidden" name="process" value="">
					
					
					
				<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
							<button name="button" type="button" onClick="window.location.href='initCreateFreshCandidate.html';"  value="Add" align="right" class="button-add" >Add</button>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
								<button name="button2" type="button" class="button-edit" onClick="editRow('dataTable')" value="Edit">Edit</button>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
								<button type="button" name="del" value="Delete" onClick="deleteRow('dataTable')" class="button-dang" align="right">Delete</button>
							</div>
							<!--div class="col-lg-1 col-md-1 col-sm-1" id="filterbutton">
							<button name="button" type="button" onClick="filterSearchpopup();"  value="Search" align="right" class="button-add" >Search</button>
							</div>-->
							   
							
					</div>
					
				   <div class="col-lg-12 col-md-12 col-sm-12">
				   &nbsp;
				   </div>
			
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="table-row-line wrapper header">
					<div class="col-lg-1 col-md-1 col-sm-1">
						<input type="checkbox" name="chkAll" onClick = "checkAll()">
                     </div>
					<div class="col-lg-2 col-md-2 col-sm-2">
					<label class="name form-div-6" style="color:#0072c6">
							Name
                            </label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						</div>
						
						
							
                           
				
					<div class="col-lg-3 col-md-3 col-sm-3">
				
					<label class="name form-div-6" style="color:#0072c6">
							E-mail
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							Phone
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							status
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						</div>
					
					<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							View Details
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					</div>
</div>					
                           
					
						
					
					 <%ArrayList FreshCandidate=(ArrayList) request.getAttribute("FreshCandidateList"); 
			//System.out.println(CandidateList.size()+" list ");
			 if(FreshCandidate!=null && FreshCandidate.size()!=0){
            Iterator itr = FreshCandidate.iterator();
   		 while (itr.hasNext()) {    
        String TempList[] = (String[])itr.next();
		String Name=TempList[0];
		 String MobileNumber = TempList[6];
		 String Email=TempList[1];
		 String Status=TempList[4];
		String can_uniqueId=TempList[8];
    
    %>
	<input type="hidden" name="uniqueId" value="<%=can_uniqueId%>" />
	<input type="hidden" name="flag" value="1" />
		<div class="col-lg-12 col-md-12 col-sm-12">	
			
			<div class="table-row">
			<div class="col-lg-1 col-md-1 col-sm-1">
				<input type="checkbox" name="chk" id="chk" value="<%=can_uniqueId %>" />
			</div>
			<div class="col-lg-2 col-md-2 col-sm-2">
			<label><%=Name%></label>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-3">
			<label><%=Email%></label>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-2">
			<label><%=MobileNumber%></label>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-2">
			<label><%=Status%></label>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-2">
			<a href="ViewFreshCandidateByUniqueId.html?uniqueId=<%=can_uniqueId%>">Click Here</a>
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
	<%}%>
		 </form>
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

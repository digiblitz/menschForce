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
    <%@ page import="java.util.*" errorPage="error.jsp"%>
	<%@ page import ="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

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
<script language="javascript">
function assignBtn(uId, login){
	strURL = "roles.html?roleProcess=initUserRole&userId="+uId+"&login_name="+login;
	window.location.href = strURL;
}


function ListUserForPagination() {
	//alert("check");
	var currentPageNo = document.getElementByID("currentPageNo").value;
	strURL = "ListRegisteredUsers.html?currentPageNo="+currentPageNo;
	window.location.href = strURL;
}
</script>
  <!--========================================================
CONTENT
=========================================================-->
	<div class="content indent">
	<div class="thumb-box14">
       
            <div class="row">			
			
			<div class="col-lg-12 col-md-12 col-sm-12">
				
					<h3 class="title" >Registered Users List</h3>
					
					
				 </div>	
			
			 <%			String jobCompanycategory = (String)session.getAttribute("jobPostCompanyCategory");
						String nofPages = (String) request.getAttribute("totalNofPages");
						System.out.println("nofPages----------->"+jobCompanycategory);
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
			 	 <div class="col-lg-1 col-md-1 col-sm-1 col-sm-offset-2">	
					<label class="name form-div-6" style="color:#0072c6">Pages :</label>
				</div>
				 <div class="col-lg-2 col-md-2 col-sm-2">
						<select name="currentPageNo" id="currentPageNo" class="form-control" onchange="ListUserForPagination();">
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
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-4">
								
								<%if(jobCompanycategory != null && jobCompanycategory.equalsIgnoreCase("Buyer")){%>	
										<button name="submit" type="submit" class="button-dang"  style="width:130px"  onclick="window.open('user.html?cmd=initUsrReg&id=msp&add=addnewuser','mywindow',
			'width=1100,height=700,toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,copyhistory=yes,resizable=yes')" value="Add New User" align="alignLeft" ><i class="fa fa-plus-square fa-lg" aria-hidden="true"></i>&nbsp;Add New User</button>
			<%}else if(jobCompanycategory != null && jobCompanycategory.equalsIgnoreCase("Vendor")){ %>
			<button name="submit" type="submit" class="button-dang"  style="width:130px"  onclick="window.open('user.html?cmd=initUsrReg&id=vendor&add=addnewuser','mywindow',
			'width=1100,height=700,toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,copyhistory=yes,resizable=yes')" value="Add New User" align="alignLeft" ><i class="fa fa-plus-square fa-lg" aria-hidden="true"></i>&nbsp;Add New User</button>
			<%} %>
						
   
							</div>
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
					</div>
				 <div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="table-row-line wrapper header">
					<div class="col-lg-2 col-md-2 col-sm-2">
					<label class="name form-div-6" style="color:#0072c6">
							Name
                            </label>
							
						</div>
					
					<div class="col-lg-3 col-md-3 col-sm-3">
				
					<label class="name form-div-6" style="color:#0072c6">
							E-mail
							</label>
							
							
                           
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							Date of Registration
							</label>
							
                           
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
				
					<label class="name form-div-6" style="color:#0072c6">
							Category
							</label>
							
						</div>
						
					<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							Active/Deactive
							</label>
							
							
                           
					</div>		
					
					<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							Assign Role
							</label>
							
							
                           
					</div>
					</div>
</div>					
                           
					
						
					
					 <%ArrayList RegisteredUserList=(ArrayList) request.getAttribute("RegisteredUserList"); 
			//System.out.println(CandidateList.size()+" list ");
			SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
			String registered_date=null;
			 if(RegisteredUserList!=null && RegisteredUserList.size()!=0){
            Iterator itr = RegisteredUserList.iterator();
    while (itr.hasNext()) {    
        String TempList[] = (String[])itr.next();
		 String user_Id=TempList[0];
		  String loginName=TempList[1];
		 String firstName = TempList[2];
		 String lastName = TempList[3];
		 String Email=TempList[4];
		 String category=TempList[6];
		 registered_date=TempList[8];
		 String active_status=TempList[9];
		 String request_status=TempList[10];
		Date registered_date1 = sdf.parse(registered_date);
		
    %>
	
		<div class="col-lg-12 col-md-12 col-sm-12">	
			
			<div class="table-row">
			<div class="col-lg-2 col-md-2 col-sm-2">
			<label><%=firstName%> <%=lastName %></label>
			</div>
			
			
			<div class="col-lg-3 col-md-3 col-sm-3">
			<label><%=Email%></label>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-2 ">
			<label><%=sdf.format(registered_date1)%></label>
			</div>
			<div class="col-lg-1 col-md-1 col-sm-1">
			<label><%=category%></label>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-2">
				<%
					if(active_status.equals("1"))
											{
					%>
										 
				<button class='button-dang' type='button' value="Deactivate" onclick="location.href='UpdateUserActiveStatus.html?user_Id=<%=user_Id%>&activeStatus=False';">Deactivate</button>
										
						<%}else{%>
											
						<button class='button-add' type='button' value="Activate" onclick="location.href='UpdateUserActiveStatus.html?user_Id=<%=user_Id%>&activeStatus=True';">Activate</button>
											 <%}%>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-2">
			 <%if(request_status!= null && request_status.equals("1")) { %>
				<button class='button-dang' type='button' value="Pending" onclick="location.href='roles.html?roleProcess=initUserRole&userId=<%=user_Id%>';">Pending</button>
			<% } else { %>
				<button name="button" type="button" class="button-edit" onclick="assignBtn('<%=user_Id%>','<%=loginName%>')"  value="Assign" >Assign</button>
			<% }%>
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

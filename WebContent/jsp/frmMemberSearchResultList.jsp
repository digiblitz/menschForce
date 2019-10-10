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
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.hlcform.util.HLCUserSearchResultVO" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<script src="js/basic.js" type="text/javascript" ></script>

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
<script language="javascript">
function assignBtn(uId, login){
	strURL = "roles.html?roleProcess=initUserRole&userId="+uId+"&login_name="+login;
	window.location.href = strURL;
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
	
<!--=======content================================-->

<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">
			
								<div class="col-lg-12 col-md-12 col-sm-12">
									
								   <h3 class="title">Membership: Search Result List</h3>
								   
								</div>
			
                      <%
		String radName=(String)session.getAttribute("radMem");
		
        String jobCompanycategory = (String)session.getAttribute("jobPostCompanyCategory");
		if(radName!=null && radName.equalsIgnoreCase("members")){
		
		%>
		
								<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="col-lg-1 col-md-1 col-sm-1">
									</div>
									<div class="col-lg-1 col-md-1 col-sm-1">
									<label class="name form-div-6 subtitle">
									Member ID
									</label>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2">
									<label class="name form-div-6 subtitle">
									Name
									</label>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2">
									<label class="name form-div-6 subtitle">
									State
									</label>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2">
									<label class="name form-div-6 subtitle">
									Member Type
									</label>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2">
									<label class="name form-div-6 subtitle">
									Status
									</label>
									</div>
									<div class="col-lg-1 col-md-1 col-sm-1">
									<label class="name form-div-6 subtitle">
									Role
									</label>
									</div>
									<div class="col-lg-1 col-md-1 col-sm-1">
									<label class="name form-div-6 subtitle">
									Login
									</label>
									</div>
									
								</div>
								
								<div class="col-lg-12 col-md-12 col-sm-12">
									&nbsp;
								</div>
                     
                      <%
						ArrayList  memberList = (ArrayList) request.getAttribute("memberDetails");
						
						if(memberList!=null && memberList.size()!=0){
							Iterator itUserList = memberList.iterator();
							while(itUserList.hasNext()){
								HLCUserSearchResultVO objUserSearch = (HLCUserSearchResultVO)itUserList.next();
								String firstName =  objUserSearch.getFirstName();
								String lastName = objUserSearch.getLastName();
								String loginName = objUserSearch.getLoginName();
								String Password = objUserSearch.getPassword();
								String memberId = objUserSearch.getMemberId();
								String emailId = objUserSearch.getEmailId();
								String userIdM = objUserSearch.getUserId();
								String state = objUserSearch.getState();
								String memberType = objUserSearch.getMembershipTypeName();
								String memberStatus = objUserSearch.getStatusName();
								//out.println("Login Name:" + loginName);
								String lastFirstName = firstName+", "+lastName;
							
								if(loginName==null || loginName.trim().length()==0)  loginName = "N/A";
								if(memberId==null || memberId.trim().length()==0)  memberId = "N/A";
								if(memberType==null || memberType.trim().length()==0)  memberType = "N/A";
								else {
								        String memtyp[] = memberType.split(" ");
										memberType = memtyp[0];
								}
								if(memberStatus==null || memberStatus.trim().length()==0)  memberStatus = "N/A";
						%>
                      <form action="SearchList.html" method="post" name="viewHrsServiceList" id="viewHrsServiceList" >
                     
                    <input type="hidden" name="searchProcess" value="loginProcess" />
                      <input type="hidden" name="userId" value="<%=userIdM%>"/>
                      <input type="hidden" name="memberId" value="<%=memberId%>"/>
                      <input type="hidden" name="adduserstatus" value="addnewuser"/>
					  
					   
					  
					  			<div class="col-lg-12 col-md-12 col-sm-12">
								
								
									<div class="col-lg-1 col-md-1 col-sm-1">
									</div>
								<% if (memberId.equals("N/A")) {%>
									<div class="col-lg-1 col-md-1 col-sm-1">
									<!--a href="meeting.html?meeProcess=userDetailsViewLogin&amp;uid=<%=userIdM%>"><%=memberId%></a>-->
									<%=memberId%>
									</div>
								 <% } else { %>	
								 	<div class="col-lg-1 col-md-1 col-sm-1">
									<!--a href="MembershipReg.html?process=familyView&amp;status=approve&amp;memberId=<%=memberId%>&amp;userId=<%=userIdM%>"><%=memberId%> </a>-->
									<%=memberId%> 
									</div>
								 <% }%>
									<div class="col-lg-2 col-md-2 col-sm-2">
									<%=lastFirstName%>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2">
									<%=state%>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2">
									<%=memberType%>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2">
									<%=memberStatus%>
									</div>
									<div class="col-lg-1 col-md-1 col-sm-1">
									<button name="button" type="button" class="button-edit" onclick="assignBtn('<%=userIdM%>')"  value="Assign" > Assign</button>
									</div>
									<div class="col-lg-1 col-md-1 col-sm-1">
									<button type="submit" name="Submit5" value="Login" class="button-add">Login</button>
									</div>
									
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12">
									&nbsp;
								</div>
					  
                      </form>
                       
                      <%
						}
				   }
					else{
				   %>
				   
				   				<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="col-lg-5 col-md-5 col-sm-5">
									</div>
									
									<div class="col-lg-7 col-md-7 col-sm-7">
									No Records were Found !
									</div>
								</div>
                        
                      
                      <%
				   }
				   %>
                      <%}else if(radName!=null && radName.equalsIgnoreCase("nonMembers")){%>
					  
					  			<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="col-lg-1 col-md-1 col-sm-1">
									</div>
									<div class="col-lg-3 col-md-3 col-sm-3">
										<label class="name form-div-6 subtitle">
										Name
										</label>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-3">
										<label class="name form-div-6 subtitle">
										State
										</label>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-3">
										<label class="name form-div-6 subtitle">
										Role
										</label>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2">
										<label class="name form-div-6 subtitle">
										Login
										</label>
									</div>
									
									
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12">
									&nbsp;
								</div>
                      
                      <%
				
				
						ArrayList  NonMemberList = (ArrayList) request.getAttribute("memberDetails");
						
						if(NonMemberList!=null && NonMemberList.size()!=0){
							Iterator itDUserList = NonMemberList.iterator();
							while(itDUserList.hasNext()){
								HLCUserSearchResultVO objNUserSearch = (HLCUserSearchResultVO)itDUserList.next();
								String firstNameN =  objNUserSearch.getFirstName();
								String lastNameN = objNUserSearch.getLastName();
								String loginNameN = objNUserSearch.getLoginName();
								String PasswordN = objNUserSearch.getPassword();
								String userIdN = objNUserSearch.getUserId();
								String emailIdN = objNUserSearch.getEmailId();
								String stateN = objNUserSearch.getState();
								
								String lastFirstNameN = firstNameN+", "+lastNameN;
						
															
						%>
                      <form action="SearchList.html" method="post" name="viewHrsServiceList" id="viewHrsServiceList" >
                     
                    <input type="hidden" name="searchProcess" value="loginProcess" />
                      <input type="hidden" name="userId" value="<%=userIdN%>"/>
					  
					  			<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="col-lg-1 col-md-1 col-sm-1">
									</div>
									<div class="col-lg-3 col-md-3 col-sm-3">
									<a href="meeting.do?meeProcess=userDetailsViewLogin&amp;uid=<%=userIdN%>"><%=lastFirstNameN%></a>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-3">
									<%=stateN%>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-3">
									<button name="button" type="button" class="button-edit" onclick="assignBtn('<%=userIdN%>','<%=loginNameN%>')"  value="Assign" >Assign</button>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2">
									<button type="submit" name="Submit5" value="Login" class="button-add">Login</button>
									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12">
									&nbsp;
								</div>
                      
                      <%
						}
				   }
					else{
				   %>
				   
				   				<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="col-lg-5 col-md-5 col-sm-5">
									</div>
									
									<div class="col-lg-7 col-md-7 col-sm-7">
									No Records were Found !
									</div>
								</div>
                    
                      <%
				   }
				   %>
                      <%}%>
					  
					  <div class="col-lg-12 col-md-12 col-sm-12">
					  &nbsp;
					  </div>
					  
					   <div class="col-lg-12 col-md-12 col-sm-12">
					  &nbsp;
					  </div>
					 
					 
					 	<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="col-lg-4 col-md-4 col-sm-4">
									</div>
									
									<div class="col-lg-2 col-md-2 col-sm-2">
									
									<button name="submit" type="button" class="button-add" style="width:130px" onclick="javascript:location.href='SearchList.html?searchProcess=initViewDet'" value="Search Again" align="middle" >Search Again</button>
									</div>
									
										<div class="col-lg-2 col-md-2 col-sm-2">
									<%if(jobCompanycategory != null && jobCompanycategory.equalsIgnoreCase("Buyer")){%>	
										<button name="submit" type="submit" class="button-dang"  style="width:130px"  onclick="window.open('user.html?cmd=initUsrReg&id=msp&add=addnewuser','mywindow',
			'width=1100,height=700,toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,copyhistory=yes,resizable=yes')" value="Add New User" align="alignLeft" >Add New User</button>
			<%}else if(jobCompanycategory != null && jobCompanycategory.equalsIgnoreCase("Vendor")){ %>
			<button name="submit" type="submit" class="button-dang"  style="width:130px"  onclick="window.open('user.html?cmd=initUsrReg&id=vendor&add=addnewuser','mywindow',
			'width=1100,height=700,toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,copyhistory=yes,resizable=yes')" value="Add New User" align="alignLeft" >Add New User</button>
			<%} %>
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
    
  		<%@ include file = "../../include/menschForceFooter.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
  </div>  
</body>
</html>

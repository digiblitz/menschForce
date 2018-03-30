<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@page import="com.hlccommon.util.HLCMenuListVO"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title>Index</title>
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

<div class="content">
	<%
						ArrayList headEntityListIndex = (ArrayList)session.getAttribute("privList");
						if(headEntityListIndex!=null && headEntityListIndex.size()!=0){
							Iterator itEntListIndex = headEntityListIndex.iterator();
							
							while(itEntListIndex.hasNext()){
							
								String strRolelistIndex[]= (String[])itEntListIndex.next();
								String heRoleId = strRolelistIndex[1];
								String heRoleName = strRolelistIndex[3];
								String heEntityId = strRolelistIndex[2];
								String heEntityName = strRolelistIndex[4];
								String heEntityUrl = strRolelistIndex[5];
								String privName= strRolelistIndex[6];
					            String priviId = strRolelistIndex[7];
					            
								if(heEntityUrl==null){	
								//System.out.println(heEntityName);
							%>
    <div class="thumb-box2" style="padding-top:0px">
        <div class="container">
		 
            <h2 class="wow fadeIn animated" style="visibility: visible; animation-name: fadeIn;"><%=privName%></h2>
            <div class="row">
					<%
						ArrayList headAllListIndex = (ArrayList)session.getAttribute("DBAllList");
						if(headAllListIndex!=null && headAllListIndex.size()!=0){
							Iterator itAllListIndex = headAllListIndex.iterator();
							
							while(itAllListIndex.hasNext()){
							
								String strAlllistIndex[]= (String[])itAllListIndex.next();
								
								String allEntityId = strAlllistIndex[2];
								String allEntityName = strAlllistIndex[4];
								
								String hePrivName = strAlllistIndex[6];
								String hePrivId = strAlllistIndex[7];
								String accessName = strAlllistIndex[8];
								String accessDes = strAlllistIndex[9];
								String accessUrl = strAlllistIndex[10];
								
								
								System.out.println("accessDes in jsp::"+accessDes);
								
								String result = "UN="+username+"&PWD="+password+"&RN="+rolename;
								String tempAccesURL="";
								boolean tempURL = accessUrl.contains("viewMaster.do?process");
								boolean tempname=accessUrl.contains("UN=:U");
								if(tempname == true){
								tempAccesURL = accessUrl.replace("UN=:U",result);
								}
								else if(tempURL == true){
								tempAccesURL=accessUrl.replace("viewMaster.do?process","artifactMapping.html?artiMapProcess");
								}
								else{
								tempAccesURL = accessUrl.replaceAll("do","html");
								}
								
						if(hePrivId.equals(priviId)){	
									  			
						%>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 wow fadeInUp">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                            <div class="badge"><img src="img/page1_badge1.png" alt=""></div>
                            <div class="caption">
							
                                <p class="title"><%=accessName%></p>
                                <!--p>If a requirement is posted by a recruited and the position of the job is not assigned to any one then the status of the job is open. This will be presented under opened.</p-->
								<p><%=accessDes%></p>
                                <a href="<%=tempAccesURL%>" class="btn-default btn4" target="_parent">Click here</a>    
						
							</div>  
                        </div>
                    </div>
					</div>
					<%}%><%}}%>	
            </div>
		</div>  
    </div>
	 <%}else{%>
	 <div class="thumb-box2">
        <div class="container">
			<h2 class="wow fadeIn"><%=heEntityName%></h2>
				<div class="row">
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 wow fadeInUp">
						<div class="thumb-pad1">
							<div class="thumbnail">
								<div class="badge"><img src="img/page1_badge1.png" alt=""></div>
								<div class="caption">
									<p class="title"><%=heEntityName%></p>
									<p>If a requirement is posted by a recruited and the position of the job is not assigned to any one then the status of the job is open. This will be presented under opened.</p>
									<a href="<%=request.getContextPath()+heEntityUrl%>" class="btn-default btn4">Click here</a>  
								</div>  
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	 <%}}}%>
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

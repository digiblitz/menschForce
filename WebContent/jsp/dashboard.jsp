<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%> - DashBoard</title>
 <link rel="icon" href="img/favicon.ico" type="image/x-icon"> 

</head>

<body>


<%
//String companyURLInProperties = (String)request.getAttribute("companyURLInProperties");
String companyURLInProperties = (String)session.getAttribute("companyURLInProperties");
String username=(String)session.getAttribute("userName");
String password=(String)session.getAttribute("userPassword");
String rolename=(String)session.getAttribute("roleName");
String encryptedroleName = (String)session.getAttribute("encryptedroleName");
String encryptedLogin = (String)session.getAttribute("encryptedLogin");
String encryptedPass = (String)session.getAttribute("encryptedPass");
String encryptedUserNPass = encryptedLogin+"~"+encryptedroleName;
String encryptedUserNameWithRole = (String) session.getAttribute("encryptedUserNameWithRole");
//String encryptedPassAndCategory = (String) session.getAttribute("encryptedPassAndCategory");
System.out.println("encryptedUserNameWithRole::::::::::::::::::::::In JSP::"+encryptedUserNameWithRole);
System.out.println("encryptedPass::::::::::::::::::::::In JSP::"+encryptedPass);
//System.out.println("encryptedUserNameWithRole::::::::::::::::::::::In JSP::"+encryptedPassAndCategory);
//String result = "UN="+username+"&PWD="+password+"&RN="+rolename;
String result = "UN="+encryptedUserNameWithRole+"&PWD="+encryptedPass;
//String result = "UN="+encryptedLogin+"~"+encryptedroleName+"&PWD="+encryptedPass;

String concatURLName =  companyURLInProperties+result;
System.out.println("concatURLName:::::::::::::::::::::::"+concatURLName);

%>
<%String []prabhu = {};ArrayList <String[]>roleEntityMapList = new ArrayList();%>

<!-- HEADER STARTS HERE -->
<div>
<%@ include file = "../../include/HeaderProcess.jsp" %>

  <!-- HEADER ENDS HERE -->
</div>

<div class="content">
	
    <div class="thumb-box2">
        <div class="container">
		 
            <h2 class="wow fadeIn">DashBoard</h2>
            <div class="row">
					<%
						ArrayList headEntityList = (ArrayList)session.getAttribute("DBEntityList");
						if(headEntityList!=null && headEntityList.size()!=0){
							Iterator itEntList = headEntityList.iterator();
							int i=1;
							while(itEntList.hasNext()){
							
								String strRolelist[]= (String[])itEntList.next();
								String heRoleId = strRolelist[1];
								String heRoleName = strRolelist[3];
								String heEntityId = strRolelist[2];
								String heEntityName = strRolelist[4];
								String heEntityUrl = strRolelist[5];
								String heEntityDes = strRolelist[6];
								
								String []newname = 	{strRolelist[4]};
								System.out.println("newname ::::::"+i+":::::::"+newname[0]);
								
								prabhu=newname;
								roleEntityMapList.add(prabhu);
								
								
								
											
					            
								if(heEntityUrl==null){	
									
							%>
					
	<div class="col-lg-4 col-md-4 col-sm-8 col-xs-8 wow fadeInUp" >
                    <div class="thumb-pad1">
                        <div class="thumbnail">
						<a href="<%=request.getContextPath()%>/login.html?cmd=initIndex&entityId=<%=heEntityId%>&roleId=<%=heRoleId%>&UsrId=<%=userId%>">
                            <div class="badge"><a href="<%=request.getContextPath()%>/login.html?cmd=initIndex&entityId=<%=heEntityId%>&roleId=<%=heRoleId%>&UsrId=<%=userId%>"><img src="img/page1_badge1.png" alt=""></a></div>
                            <div class="caption">
							
                                <p class="title"><a href="<%=request.getContextPath()%>/login.html?cmd=initIndex&entityId=<%=heEntityId%>&roleId=<%=heRoleId%>&UsrId=<%=userId%>" ><%=heEntityName%></a></p>
								<label style="height:220px; " >
                                <p><%=heEntityDes%></p>
                                <a href="<%=request.getContextPath()%>/login.html?cmd=initIndex&entityId=<%=heEntityId%>&roleId=<%=heRoleId%>&UsrId=<%=userId%>" class="btn-default btn4">Click here</a>    
						</label>
							</div>  
							</a>
                        </div>
                    </div>
					</div>
					
					<%}else{%>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 wow fadeInUp">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                            <div class="badge"><a href="<%=concatURLName%>"><img src="img/page1_badge1.png" alt=""></a></div>
                            <div class="caption">
							
                                <p class="title"><a href="<%=concatURLName%>"><%=heEntityName%></a></p>
                                <p><%=heEntityDes%></p>
                                <a href="<%=concatURLName%>" class="btn-default btn4">Click here</a>    
						
							</div>  
                        </div>
                    </div>
					</div>
					<%}i++;}}%>
            </div>
		</div>  
    </div>
</div>	 



<!--=======footer=================================-->

 		
                    <!-- FOOTER STARTS HERE -->
                        
						<%@ include file = "../../include/FooterProcess.jsp" %>
                    <!-- FOOTER ENDS HERE -->
               

</body>
</html>

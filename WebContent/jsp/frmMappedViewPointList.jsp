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
<%@ page import="java.sql.*"%>
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
function callMapList(viewPointId) 
{
	
	strURL = "./artifactMapping.html?artiMapProcess=searchMappedGroup&viewPoint="+viewPointId;
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
					
                   <h3 class="title">View Point : Search Mapped Groups List</h3>
				   
				 </div>
				 
				  <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
				<div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-8 col-md-8 col-sm-8 col-sm-offset-3">
						 Please choose the any one of the view point in order to view the mapped group list
				   </div>
				 </div>
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
	

                <form name="frmSearchMappedViewPoint" action="artifactMapping.html"  method="post" onsubmit="return myValidate();">
                  <input type="hidden" name="artiMapProcess" value="searchMappedGroup">
				  
                  <div id="showSearchCrite">
                   
                          <%String viewPointId = (String)request.getAttribute("viewPointId");
				
				 %>
                       <div id="memShow">
							
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							View Point
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<select name="viewPoint" id="viewPoint" class="form-control" onchange="callMapList(this.value);">
                                  <option selected="selected" value="">Select One</option>
                                  <%
									 ArrayList viewList = (ArrayList) request.getAttribute("viewList");
          					 if(viewList!=null && viewList.size()!=0){
							Iterator it = viewList.iterator();
							while(it.hasNext()){
								String[] s = (String[])it.next();
								String viewId = s[0];
								String viewName = s[1];		
								
											

											
											if (viewId.equals(viewPointId)){
													System.out.println(" View Name : "+viewName);

											 %>
                                  <option value="<%=viewId%>" selected="selected"><%=viewName%></option>
                                  <%
											 }
								 
					           else{
					           %>
                                  <option  value="<%=viewId%>" ><%=viewName%></option>
                                  <%
					           }
											 
											 }
										}
									


							%>
                              </select>
							</div> 
							
						</div>
                     </div>
					 
					  <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
					 
					 <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-5 col-md-5 col-sm-">
							</div>
							<div>
							   <button type="submit" class="button-add" name="submit" value="Search">Search</button>
							  
							</div>
					</div>
                  </div>
                </form>
				
				 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
				
                <form action="artifactMapping.html" name="frmMappedViewPoint">
                  <input type="hidden" name="artiMapProcess" value="">
				  
				  
				  <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="table-row-line wrapper header">
					<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-1">
					 <label class="name form-div-6 subtitle" align="left">View Point</label>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
					 <label class="name form-div-6 subtitle" align="left">LOB</label>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3">
					 <label class="name form-div-6 subtitle" align="left">Views</label>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
					 <label class="name form-div-6 subtitle" align="left">Groups</label>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
					 <label class="name form-div-6 subtitle" align="left">Process/Domain</label>
					</div>
					</div>		
				</div>
				  
				  
                  
                    <%ArrayList userMapPointList = (ArrayList)request.getAttribute("userMapPointList");
           									 if(userMapPointList!=null && userMapPointList.size()!=0){
           							Iterator ituserMapPoint = userMapPointList.iterator();
           							while(ituserMapPoint.hasNext()){
           								String[] viewPointUser = (String[])ituserMapPoint.next();
           								String viewPoint = viewPointUser[0];
           								String lob = viewPointUser[1];
           								String view = viewPointUser[2];
           								String group = viewPointUser[3];
           								String processdDom = viewPointUser[4];
           		if(lob!=null && view!=null && group !=null && processdDom !=null){						
		   %>
		   
		   
		   
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="table-row">						
					<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-1">
					<%=viewPoint%>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
					<%=lob%>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3">
					 <%=view%>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
					 <%=group%>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
					 <%=processdDom%>
					</div>
							
				</div>
                  
				 </div> 
                    <%}}}else{ %>
					
					<div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
					</div>
					
					<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-5 col-md-5 col-sm-5 col-sm-offset-5">
							
							
							  <label class="name">No Records were Found !</label>
							</div>
					</div>
                  
                      <%} %>
                </form>
			</div>
		 </div>
		</div>
	</div>
<<!--========================================================
FOOTER
=========================================================-->
  <div>
   
    
    <!-- FOOTER STARTS HERE -->
    
  		<%@ include file = "../../include/FooterProcess.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
  </div>  

</body>
</html>

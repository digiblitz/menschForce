<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<script src="js/validate.js" type="text/javascript" ></script>

<script src="js/basic.js" type="text/javascript" ></script>
 <script>
function loadSec(){

	document.getElementById('showLobs').style.display = "none";
	document.getElementById('showViews').style.display="none";
	document.getElementById('showGrps').style.display="none";
	document.getElementById('showProDom').style.display="none";

	}
	
</script>
<script src="js/frmArtifactMapList.js" type="text/javascript" ></script>

</head>


<body onload="loadSec();">
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
			
			<div class="col-lg-12 col-md-12 col-sm-12">
					
                   <h3 class="title">Manage Artifact Mapping</h3>
				  
				 </div>
				 
				   <%
						 String errorMsg="";
						 if(request.getAttribute("errorMsg")!=null){
						 
						  errorMsg="Error while Mapping";
						
						
						 
					%>
					
					
					<div class="col-lg-12 col-md-12 col-sm-12">
					
						<div class="col-lg-7 col-md-7 col-sm-7 col-sm-offset-3">
						
						   <font color="#FF0000"><%=errorMsg%></font>
						
				   
						</div>
				    </div>
				 
				   <%}%>
	
                
                <form name="frmArtifactMap" id="frmArtifactMap" action="artifactMapping.html?artiMapProcess=mapArtiDets" method="post" onsubmit="return myValidate();" enctype="multipart/form-data" commandName="uploadForm">
                  <input type="hidden" name="artiMapProcess" value="mapArtiDets" />
				  
				
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-2" align="left">
								View Points
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<select name="viewPntId" id="viewPntId" class="form-control" onchange="retrieveURL('lobsList',this);">
                              <option selected="selected" value="">Select One</option>
                              <%
									 ArrayList viewPntList = (ArrayList) request.getAttribute("viewPntList");
          					 if(viewPntList!=null && viewPntList.size()!=0){
							Iterator itvPnt = viewPntList.iterator();
							while(itvPnt.hasNext()){
								String[] sVPnt = (String[])itvPnt.next();
								String viewPntId = sVPnt[0];
								String viewPntName = sVPnt[1];		
								
											

											 %>
                              <option value="<%=viewPntId%>"><%=viewPntName%></option>
                              <%
											 }
										}
									


							%>
                          </select>
						</div>
						
						
					</div>
					
					<div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
					</div>
					
							
						
						
						<div id="showLobs">
							<div class="col-lg-12 col-md-12 col-sm-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
									<label class="name form-div-2" align="left">
									LOBs
									</label>
								</div>
								<div class="col-lg-3 col-md-3 col-sm-3">
									<select name="lobId" id="lobId" class="form-control" onchange="retrieveURLs('viewGrpList',this);">
									  <option selected="selected" value="">Select One</option>
								  </select>
								</div>
							</div>
							
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						</div>
					
						
						
					
					<div id="showViews">
						<div class="col-lg-12 col-md-12 col-sm-12">	
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-2" align="left">
								Views
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<select name="viewId" id="viewId" class="form-control">
								  <option selected="selected" value="">Select One</option>
								</select>
							</div>
						</div>
							<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
					</div>
					
					
						
						
					
					<div id="showGrps">
						<div class="col-lg-12 col-md-12 col-sm-12">	
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-2" align="left">
								Groups
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<select name="grpId" id="grpId" class="form-control" onchange="retrieveURL('processDomainList',this);">
								  <option selected="selected" value="">Select One</option>
								</select>
							</div>
							
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
					</div>
					
					
						
						
					
					<div id="showProDom">
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-2" align="left">
								Domains / Processes
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<select name="domProcId" id="domProcId" class="form-control">
								  <option selected="selected" value="">Select One</option>
								</select>
							</div>
							
						</div>
						
							<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
							</div>
					</div>
					
					
						
					
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-2" align="left">
									Artifacts
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<%
										 ArrayList cntList = (ArrayList) request.getAttribute("cntList");
								 if(cntList!=null && cntList.size()!=0){
								 int j=0;
								Iterator itcnt = cntList.iterator();
								while(itcnt.hasNext()){
									String[] scnt = (String[])itcnt.next();
									String layerCntId = scnt[0];
									String layerCntName = scnt[1];	
									String masterCntId = scnt[2];
									String masterCntName = scnt[3];		
						
												 %>
								  <br />
								  <input type="checkbox" name="artiFact<%=j%>" id="artiFact<%=j%>" value="<%=layerCntId%>" onclick="retrieveURLD('MappingDetExists',this);"/>
								<%=layerCntName%>
								 &nbsp; <input name="uploads[<%=j%>]" type="file" id="uploads<%=j%>" multiple="muliple" required/>
								  <%
												 j++;}
											}
			
								%>
								  <input type="hidden" name="artiCnt" id="artiCnt" value="<%=cntList.size()%>"/>
						</div>
						
						
						</div>
						
					<div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
					</div>
						
						
						 
					 <div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-sm-5">
						</div>
						<div>
						   <button type="submit" class="button-add" name="submit" style="width:130px" value="Map Artifact">Map Artifact</button>
						  
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
    
    <%@ include file = "../../include/FooterProcess.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>

</body>
</html>

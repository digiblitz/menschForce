<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
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
<script src="js/calendar2.js" type="text/javascript"></script>
</head>

<script type="text/javascript">

function callMapList()
{
	strURL = "./artifactMapping.html?artiMapProcess=listViewPointMap";
	window.location.href = strURL;
}



function callViewPoint(selViewPointId) {
	
	//alert('asi');
	strURL = "./artifactMapping.html?artiMapProcess=listViewBasedOnViewPoint&selVPointId="+selViewPointId;
	window.location.href = strURL; 	

}


function showArtifact() {

	//alert("test "+document.frmGroupsMap.viewId.selectedIndex+"=="+chkCount);

	
 /* if(document.frmGroupsMap.viewId.selectedIndex==0)
	{
		document.getElementById('divArtifact').style.visibility="hidden";
	}
	else
	{
		document.getElementById('divArtifact').style.visibility="visible";
	}*/
	var comboCnt =0;
	var showArtifactFlag = false;

      for(var i=0;i<document.frmGroupsMap.elements.length;i++) {
	  
		   if(document.frmGroupsMap.elements[i].type=='select-one') {

				//alert('if '+document.frmGroupsMap.elements[i].selectedIndex+"=="+comboCnt);

				if(comboCnt==0) {
					if(document.frmGroupsMap.elements[i].selectedIndex!=0) {
						showArtifactFlag=true;
					}
					else if(document.frmGroupsMap.elements[i].selectedIndex==0) {
						showArtifactFlag=false;
					}
				} 
				
				if(comboCnt==1) {
					if(document.frmGroupsMap.elements[i].selectedIndex!=0 && document.frmGroupsMap.elements[i-1].selectedIndex!=0) {
						showArtifactFlag=true;
					}
					else if(document.frmGroupsMap.elements[i].selectedIndex==0 && document.frmGroupsMap.elements[i-1].selectedIndex==0) {
						showArtifactFlag=false;
					}
				} 
			
				if(comboCnt>1) {
					if(document.frmGroupsMap.elements[i].selectedIndex!=0) {
						showArtifactFlag=false;
						break;
					}
					else if(document.frmGroupsMap.elements[i].selectedIndex==0) {
						showArtifactFlag=true;
						break;
					}
				
				}


				comboCnt++;
		   }
	  }

	  if(showArtifactFlag==true)
		  document.getElementById('divArtifact').style.visibility="visible";
	  else 
		  document.getElementById('divArtifact').style.visibility="hidden";	
}






function myValidate(){
	if(document.getElementById("viewId").value=="")
{alert("Select View point");
 document.getElementById("viewId").focus();
 return false;}
	

grpSize=document.getElementById("grpSize").value;

		for(d=0;d<grpSize;d++){
		
		masteName=document.getElementById("masteName"+d).value;

//alert(masteName);
	
	if(document.getElementById("viewId").value!="")
{
if(document.getElementById("grpsDet"+d)!=null){
if(document.getElementById("grpsDet"+d).value==""){

alert("Select any one"+masteName);
document.getElementById("grpsDet"+d).focus();
return false;
}



}
}
	}	
		

	 return true;
	 
	 
}
	



var requ;
  
function retrieveURL(methodName) {  
 
// alert(methodName);



  var url = null;

		viewPntId=document.getElementById("viewId").value;
		
	grpSize=document.getElementById("grpSize").value;
	lobId=null;
	viewId=null;
	grpId=null;
	procDom=null;
	
	
	
	
	for(d=0;d<grpSize;d++){	
	if(document.getElementById("grpsDet"+d)!=null){
	//masterId=document.getElementById("masterId"+d).value;
	var grpDet=document.getElementById("grpsDet"+d).value;	
	
	if(grpDet!=null && lobId==null){	
var splitval = grpDet.split("#");	
	
	lobId=splitval[1];
	}else if(grpDet!=null && lobId!=null && viewId==null){
	
	var splitval = grpDet.split("#");	
	
	viewId=splitval[1];
	
	}else if(grpDet!=null && lobId!=null && viewId!=null && grpId==null){
	
	var splitval = grpDet.split("#");	
	
	grpId=splitval[1];
	
	}else if(grpDet!=null && lobId!=null && viewId!=null && grpId!=null && procDom==null){
	
	var splitval = grpDet.split("#");	
	
	procDom=splitval[1];
	
	}
	
}	
	}
	
if(viewPntId!=null && lobId!=null && viewId!=null && grpId!=null && procDom!=null)	{	
 urlsd = "UsrSignupAjax.html?cmd="+escape(methodName)+"&viewPntId="+escape(viewPntId)+"&lobId="+escape(lobId)+"&viewId="+escape(viewId)+"&grpId="+escape(grpId)+"&domProcId="+escape(procDom);  
 
 } 
  
      if (window.XMLHttpRequest) {
           requ = new XMLHttpRequest();
		   
       } else if (window.ActiveXObject) {
           requ = new ActiveXObject("Microsoft.XMLHTTP");
       }
     
           requ.onreadystatechange = displayExistsStatus;
		 
           
       requ.open("GET", urlsd, true);
       requ.send(null);

  
  }  
  
  
  function displayExistsStatus() {
	if (requ.readyState == 4) { // Complete
		if (requ.status == 200) { // OK response  
			var arnameXML = requ.responseXML.getElementsByTagName("outValue")[0]; 
			var arnameText = arnameXML.childNodes[0].nodeValue; 		
			
			 updateHTML(arnameXML); 
			
		} 
		else {
			alert("Problem: " + requ.statusText);
		}
	}
}
  
  
   function updateHTML(arnameXML) 
    { 
        //The node valuse will give actual data 
        var arnameText = arnameXML.childNodes[0].nodeValue; 

		if(arnameText != "false")
		{
			alert("Records are already Mapped. Choose another set");
			document.getElementById("viewId").value="";
			grpSizes=document.getElementById("grpSize").value;
		
		for(d=0;d<grpSizes;d++){
		if(document.getElementById("grpsDet"+d)!=null){
		document.getElementById("grpsDet"+d).value="";
		}
		}
		document.getElementById("subutton").disabled = true;
		
		document.getElementById("subutton").className='button-dang';
		}else{
		
		document.getElementById("subutton").disabled = false;
	
		document.getElementById("subutton").className='button-add';
		
		}
		      
    } 


</script>




<style type="text/css">

.styleNew {font-family:Tahoma; color:#FFF;	font-size:11px;	font-weight:bold; background:url(file:///C|/Users/user/Downloads/Artifact_pages/images/grad_02.jpg); cursor:hand;
			height:20px; background-color:#aaaaaa; border:1px solid #003366;}


</style>
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
			
			
			 <% String success = (String)request.getAttribute("success");%>
			 
			 <div class="col-lg-12 col-md-12 col-sm-12">
					
                   <h3 class="title">View Point Mapping with Groups</h3>
				  
				 </div>
				 
				  <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
				<div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-3">
						<% if(success!=null && success != ""){%>
                      <p><font color="#FF0000">Note: Successfully Mapped</font></p>
                      <% }else{%>
                      <p><font color="#FF0000">Note: Please select any value from all the dropdowns</font></p>
                  <%}%>
				   </div>
				 </div>
				 
				  <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>

                <form name="frmGroupsMap" id="frmGroupsMap" action="artifactMapping.html"  method="post" onsubmit="return myValidate();">
                  <input type="hidden" name="artiMapProcess" value="insertArtifactMap">
                  
                        <%String viewPointId=(String)request.getAttribute("viewPointId");
						
						

  %>
  
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-6">
								View Point
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<select name="viewId" id="viewId" class="form-control" onchange="callViewPoint(this.value);">
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
											 %>
                              <option value="<%=viewId%>" selected="selected"><%=viewName%></option>
                              <%
											 }
								 
					           else{
					           %>
                              <option  value="<%=viewId%>" ><%=viewName%></option>
                              <%
					           }
										}}
									


							%>
                          </select>
							</div> 
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
                        
                        <%
				
				 ArrayList grpList = (ArrayList) request.getAttribute("grpList");
				 
				  if(grpList!=null && grpList.size()!=0){
				   grpList.remove(grpList.size() - 1);
							 int i=0; 
							 int iViewsCnt=0;
							Iterator itgrp = grpList.iterator();
							while(itgrp.hasNext()){
								String[] sgrp = (String[])itgrp.next();
								String masteId = sgrp[0];
								String masteName = sgrp[1];
			
				 ArrayList grpDetList = (ArrayList) request.getAttribute("grpDetList");
				 
          					 if(grpDetList!=null && grpDetList.size()!=0){
							
							 String layerId=null;
							Iterator itlob = grpDetList.iterator();
						%>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-6">
								<%=masteName%>:
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input type="hidden" name="masteName<%=i%>" id="masteName<%=i%>" value="<%=masteName%>" />
                              <select name="grpsDet<%=i%>" id="grpsDet<%=i%>" class="form-control" onchange="retrieveURL('mappingExist');">
                                <option selected="selected" value="">Select One</option>
                                <%	
							while(itlob.hasNext()){
								String[] slob = (String[])itlob.next();
								String masteIds = slob[0];
								String masteNames = slob[1];	
								String layerVak = slob[2];
								 layerId = slob[3];	
								String sqe = slob[4];
								
			              if(masteId.equals(masteIds)){
						
						 String cntValue=masteIds+"#"+layerId+"#"+sqe;
						if(i==1 ){
							String tempVpointName="";
							
							ArrayList viewPointList = (ArrayList) request.getAttribute("viewPointList");
							//System.out.println("viewPointList=="+viewPointList.size());
							
							if(viewPointList!=null && viewPointList.size()!=0 && iViewsCnt < viewPointList.size()){
								Iterator itViewPoint = viewPointList.iterator();
								while(itViewPoint.hasNext()){
									String[] sViewPoint = (String[])itViewPoint.next();
									String vPointId = sViewPoint[0];
									String vPointName = sViewPoint[1];	
									tempVpointName=vPointName;
									 String cntValue1=masteIds+"#"+vPointId+"#"+sqe;
									//System.out.println("inside if"+i+"=====vPointName==="+vPointName+"=====cntValue1==="+cntValue1);
							
											 %>
                                <option value="<%=cntValue1%>"><%=vPointName%></option>
                                <%
								iViewsCnt++;
											 }}}else {
												// System.out.println("inside else"+i);%>
                                <option value="<%=cntValue%>"><%=layerVak%></option>
                                <%}
											 }
						
											 } %>
                                <%
				

				
							%>
                              </select>
                              <%}%>
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						 <% i++;}}%>
						 
						
						
                        <input type="hidden" name="grpSize" id="grpSize" value="<%=grpList.size()%>" />
  
						<div class="col-lg-12 col-md-12 col-sm-12">
							
								<div class="col-lg-1 col-md-1 col-sm-1 col-sm-offset-5">
							<button type="submit" name="subutton" value="Map" id="subutton" class="button-add" disabled="disabled" >Map</button>
							   
						</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							   <button type="button" class="button-edit" name="button" onclick="callMapList();" style="width:130px" value="Go to List Page">Go to List Page</button>
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

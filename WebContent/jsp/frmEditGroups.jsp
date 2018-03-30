<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page import="com.hlcmrm.util.HLCBreedVO"%>
<%@ page import="java.util.*"%>

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
<!--script src="js/validate.js" type="text/javascript" ></script>-->
<script>

/*function validate()
{
	
	var vFlag = false;
	var obj=document.frmAddListgroups;

	if(obj.masterId.selectedIndex==0 )
    {
		alert("Please select any one of the Group");
		return false;
    }
	if(obj.groupDet.value=="" )
    {
		alert("Details cannot be empty");
		return false;
    }

	for(var i=0;i<obj.elements.length;i++)
	{
		if(obj.elements[i].type=='text')
		{
			if((obj.elements[i].value.indexOf('	') == 0) || (obj.elements[i].value.lastIndexOf('	') == (obj.elements[i].value.length-1)) ||
				(obj.elements[i].value.indexOf(' ') == 0) || (obj.elements[i].value.lastIndexOf(' ') == (obj.elements[i].value.length-1)))
			{
				obj.elements[i].value = obj.elements[i].value.trim();
				//obj.elements[i].focus();
				vFlag = true;
			}
		}
	}

	if(vFlag==true)
	{
		alert("Leading and Trailing spaces will be trimmed. Please Submit again");
		return false;
	}
	
}
*/
function cancelGroup()
{
	var masterId=document.frmAddListgroups.groupId.value;
	   strURL = "./artifactMapping.html?artiMapProcess=searchList&masterId="+masterId;
    window.location.href = strURL;	
	}


</script>
<style type="text/css">
<!--
.style1 {font-weight: bold}
-->
#frmAddListgroups label.error{
	color:red;
}
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
<script src="js/jquery.validate.js"></script>
<script src="js/additional-methods.js"></script>
<script src="js/profilefieldvalidation.js"></script>
<!--=======content================================-->

<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">
			
			<div class="col-lg-12 col-md-12 col-sm-12">
					
                   <h3 class="title">Group - Details : Edit</h3>
				  
				 </div>
				 
				<div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-3">
						 <p>You can Edit a Group - Detail for all users.</p>
				   </div>
				 </div>
				 
				 <form name="frmAddListgroups" id="frmAddListgroups" method="post" action="artifactMapping.html" onsubmit="return validate();">
                              <input type="hidden" name="artiMapProcess" value="createGroups" />
                              <%
String groupIdObj=(String)request.getAttribute("groupId");
    		String layerValue=(String)request.getAttribute("layerValue");
%>
                              <input type="hidden" name="groupId" value="<%=groupIdObj%>" />
							  
							  
				<div class="col-lg-12 col-md-12 col-sm-12">
					
					<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-3">
					<label class="name form-div-6" align="left"> 
						<font color="#FF0000"> Fields marked with an asterisk (<span class="asterisk">*</span>) are required.</font>
					</label>
				   </div>
				 </div>
				 
							<div class="col-lg-12 col-md-12 col-sm-12">
								&nbsp;
							 </div>
			      
                              <% String statuscheck = (String)request.getAttribute("status");
					if(statuscheck!=null && statuscheck.equals("error")){
					%>
					
							<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-10 col-md-10 col-sm-10 col-sm-offset-3">
								<font color="#FF0000">Detail already exist.</font>
						   </div>
						 </div>
                             <div class="col-lg-12 col-md-12 col-sm-12">
								&nbsp;
							 </div>
                              <%}%>
							  
					<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
							<label class="name form-div-6">
							Groups <span class="asterisk">*</span>
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<select name="masterId" class="form-control"  >
                                    <option  selected="selected" value="">Select One</option>
                                    <%
                                                ArrayList viewGroupList = (ArrayList)request.getAttribute("groupList");
           									 if(viewGroupList!=null && viewGroupList.size()!=0){
           							Iterator it = viewGroupList.iterator();
           							while(it.hasNext()){
           								String[] groupList = (String[])it.next();
           								String group_id = groupList[0];
           								String group_name = groupList[1];
           							   if (group_id.equals(groupIdObj)){
                                                                                            
										%>
                                    <option  value="<%=group_id%>" selected="selected" ><%=group_name%></option>
                                    <%
											}
											else{
											%>
                                    <option  value="<%=group_id%>" ><%=group_name%></option>
                                    <%
											}
										}
           									 }									
								%>
                                  </select>
                                
							</div> 
							
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
					
					
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-6">
								Detail <span class="asterisk">*</span>
								</label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<input name="groupDet" type="text" class="form-control" value="<%=layerValue%>" />
                             
							</div> 
							
						</div>
						
					   <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
					
						 <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
							   <button type="submit" class="button-add" name="submit" value="Update">Update</button>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
							   <button type="button" class="button-edit" name="Clear" value="Clear" onclick="clearFields(this.form)">Clear</button>
							 </div>
							 <div class="col-lg-1 col-md-1 col-sm-1">
							   <button type="button" class="button-dang" name="Cancel" value="Cancel" onclick="cancelGroup();">Cancel</button>
							  </div>
							   
							</div>
					
						
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

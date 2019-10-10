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

</head>
<script language="javascript">

function checkAll() {
     el = document.forms['frmRoleEntPriv'].elements;
     for (i=0; i < el.length; i++) {
       if(el[i].type == "checkbox") {
          if(el[i].checked== true && el[i].value== "ChkAll") {
            //alert("ok");
            for (j=0; j < el.length; j++) {
               if(el[j].type == "checkbox") {
                  void(el[j].checked=1);
               }
            }
            break;
          }
          if(el[i].checked== false && el[i].value== "ChkAll") {
            //alert("uncheck");
            for (j=0; j < el.length; j++) {
               if(el[j].type == "checkbox") {
                  void(el[j].checked=0);
               }
            }
          }   
       }
     }
 }
 
function DelAll(){
	var chkValue = "";
	e = document.forms['frmRoleEntPriv'].elements;
	
	var count =0;
	for(i=0 ; i< e.length; i++){
		if(e[i].type == "checkbox"){
			 if(e[i].checked == true && e[i].value != "ChkAll") {
				 count++;
				 chkValue +=  e[i].value + "#";
			}
		}
	}
		//alert(chkValue);
		//return chkValue;
		document.frmRoleEntPriv.privilegeIds.value=chkValue;
}

function entPrivValidate(){
	if(document.frmRoleEntPriv.entityId.value==""){
		alert("Select any one Entity.");
		document.frmRoleEntPriv.entityId.focus();
		return false;
	}
	DelAll();
	return true;
}
	
	
	
function postData(){
	if(document.frmRoleSelectEntPriv.entityId.value!=""){
		document.frmRoleSelectEntPriv.roleProcess.value = "entPrivSelect";
		//alert(frmRewalList.eventProcess.value);
		document.frmRoleSelectEntPriv.method="post";
		document.frmRoleSelectEntPriv.action="roles.html";
		document.frmRoleSelectEntPriv.submit();
	}
	else{
		alert("Select any one Entity");
		document.frmRoleSelectEntPriv.entityId.focus();
	}
}


function clearField(obj)
{

	for(var i=0;i<obj.elements.length;i++)
	{
		
		if(obj.elements[i].type=='checkbox')
		{
			obj.elements[i].checked = false;
		}
	}
}
</script>
<body>

<!--========================================================
HEADER
=========================================================-->
  <div id="header">
    <!-- HEADER STARTS HERE -->
  <%@ include file = "../../include/menschForceHeader.jsp" %>
      <!-- HEADER ENDS HERE -->
    </div>
	
<%
	String entityId = (String) request.getAttribute("entityId");
	if(entityId==null){
		entityId = "";
	}
	//String privId = "";

%>

<!--=======content================================-->

<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
            <div class="row">
			
				<div class="col-lg-12 col-md-12 col-sm-12">
					
                   <h3 class="title">Map : Privilege Entity Mapping</h3>
				  
				 </div>
				 
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-2 col-md-2 col-sm-2">
					</div>
					<div class="col-lg-10 col-md-10 col-sm-10">
						 <h4> <font color="#018dce">You can map a Privilege or Multiple Privileges for a given Entity as shown below.</font></h4>
				   </div>
				 </div>
				 
				  <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>

                  <form name="frmRoleSelectEntPriv" id="frmRoleSelectEntPriv">
                        <input type="hidden" name="roleProcess" value="" />
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
							Select an Entity:
                            </label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							<select name="entityId" id="entityId" class="form-control" onchange="postData();">
                              <option selected="selected" value="">Select One</option>
                              <%
									ArrayList arrayEntityList = (ArrayList)request.getAttribute("enityDetails");
									if(arrayEntityList!=null && arrayEntityList.size()!=0){
										Iterator itEntList = arrayEntityList.iterator();
										while(itEntList.hasNext()){
											String[] entList = (String [])itEntList.next();
											//String[] entList = {entityId, entityName};
											String entId = entList[0];
											String entityName = entList[1];
											if(entityId.equals(entId)){
											%>
                              <option value="<%=entId%>" selected="selected"><%=entityName%></option>
                              <%
											 }
											 
											 else{
											 %>
                              <option value="<%=entId%>"><%=entityName%></option>
                              <%
											 }
										}
									}
							%>
                            </select>

							</div> 
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
						</div>
                     </form>
					 
					  <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
					 
					 
                    <form name="frmRoleEntPriv" id="frmRoleEntPriv" method="post" action="roles.html" onsubmit="return entPrivValidate();">
                        <input type="hidden" name="roleProcess" value="mappingEntPriv" />
                        <input type="hidden" name="privilegeIds" value="" />
                        <input type="hidden" name="entityId" value="<%=entityId%>" />
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2">
							<label class="name form-div-6">
							Available Privileges:
                            </label>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4">
							<input  type="checkbox" name="chkAll" value="ChkAll" alt="Select or Deselect All" onclick="checkAll();">
                          Select All
							</div> 
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
						</div>
						
						
                        
                        <%
									ArrayList arrayPrivList = (ArrayList)request.getAttribute("privillegeDetails");
									if(arrayPrivList!=null && arrayPrivList.size()!=0){
										Iterator itPrivList = arrayPrivList.iterator();
										while(itPrivList.hasNext()){
											String[] priList = (String [])itPrivList.next(); //{privilegeId, privilegeName, privilegePath};
											String privilegeId = priList[0];
											String privilegeName = priList[1];
											boolean privIdStatus = false;
											ArrayList arrayMapEntPrivList = (ArrayList)request.getAttribute("mapDetails");
											if(arrayMapEntPrivList!=null && arrayMapEntPrivList.size()!=0){
												Iterator itEntPrivList = arrayMapEntPrivList.iterator();
												while(itEntPrivList.hasNext()){
													String[] mapPriList = (String [])itEntPrivList.next(); //{privilegeId, privilegeName, privilegePath};
													String privId = mapPriList[2];
													if(privilegeId.equals(privId)){
														privIdStatus = true;
														break;
													}
													//{mapPrivilegeId, entityIdVal, privilegeId, entityName, privilegeName};
												}
											}
											if(privIdStatus==true){
											%>
											
							<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							
							<div class="col-lg-4 col-md-4 col-sm-4">
							 <input type="checkbox" name="checkbox5" value="<%=privilegeId%>" checked="checked" />
                          <%=privilegeName%> 
							</div> 
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
						</div>
                       
                        <%} else{ %>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							
							<div class="col-lg-4 col-md-4 col-sm-4">
							 <input type="checkbox" name="checkbox5" value="<%=privilegeId%>" />
                          <%=privilegeName%>
							</div> 
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
						</div>
                        
                        <%
											 }
										}
									}
							%>
							
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4">
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
								<button name="submit" type="submit" value="Submit" class="button-add">Submit</button>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
								<button name="button" type="button" class="button-dang" value="Clear" onclick = "clearField(this.form)">Clear</button>
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

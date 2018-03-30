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
<%@ page import="java.util.Date"%>
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

 <script language="javascript">
 
 function approved() {
	 
	 var chkBoxCnt = document.artifactlist.chk.length;
	  
	 		 if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
	 			   {
	 					for(var i=0;i<chkBoxCnt;i++)
	 					{

	 						 	if(document.artifactlist.chk[i].checked==true)
	 						 {
	 								var StageValue= document.artifactlist.StageValue[i].value;
	 								var StageName= document.artifactlist.StageName[i].value;
	 								var ArtifactId= document.artifactlist.ArtifactId[i].value;
	 								var ProcessId= document.artifactlist.ProcessId[i].value;
									var stageNo= document.artifactlist.StageNo[i].value;
									var ReqquestId= document.artifactlist.ReqquestId[i].value;

	 								 strURL = "./SysMgmt.html?process=WSapproved&StageValue="+StageValue+"&StageName="+StageName+"&ArtifactId="+ArtifactId+"&ProcessId="+ProcessId+"&StageNo="+stageNo+"&RequestID="+ReqquestId;
	                                  window.location.href = strURL;


	 						 }
	 						
	 					}
	 			   }


	 			   else{
	 			 
	 			   	if(document.artifactlist.chk.checked==true)
	 			{

	 var StageValue= document.artifactlist.StageValue.value;
	 								var StageName= document.artifactlist.StageName.value;
	 								var ArtifactId= document.artifactlist.ArtifactId.value;
	 								var ProcessId= document.artifactlist.ProcessId.value;
									var stageNo= document.artifactlist.StageNo.value;
                                    var ReqquestId= document.artifactlist.ReqquestId.value;
									
	 								 strURL = "./SysMgmt.html?process=WSapproved&StageValue="+StageValue+"&StageName="+StageName+"&ArtifactId="+ArtifactId+"&ProcessId="+ProcessId+"&StageNo="+stageNo+"&RequestID="+ReqquestId;
	                                  window.location.href = strURL;



	 			}
	 			}




	 	 
	 		
	 	}
	  
	   function denied() {
	 		
	 var chkBoxCnt = document.artifactlist.chk.length;
	 		 if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
	 			   {
	 					for(var i=0;i<chkBoxCnt;i++)
	 					{

	 						 	if(document.artifactlist.chk[i].checked==true)
	 						 {
	 								
	 var StageValue= document.artifactlist.StageValue[i].value;
	 								var StageName= document.artifactlist.StageName[i].value;
	 								var ArtifactId= document.artifactlist.ArtifactId[i].value;
	 								var ProcessId= document.artifactlist.ProcessId[i].value;
									var stageNo= document.artifactlist.StageNo[i].value;
									var ReqquestId= document.artifactlist.ReqquestId[i].value;
									
	 								 strURL = "./SysMgmt.html?process=WSdenied&StageValue="+StageValue+"&StageName="+StageName+"&ArtifactId="+ArtifactId+"&ProcessId="+ProcessId+"&StageNo="+stageNo;
	       window.location.href = strURL;


	 						 }
	 						
	 					}
	 			   }


	 			   else{
	 			   
	 			   	if(document.artifactlist.chk.checked==true)
	 			{
	 					
	 					var StageValue= document.artifactlist.StageValue.value;
	 								var StageName= document.artifactlist.StageName.value;
	 								var ArtifactId= document.artifactlist.ArtifactId.value;
	 								var ProcessId= document.artifactlist.ProcessId.value;
                                   var stageNo= document.artifactlist.StageNo.value;
								   var ReqquestId= document.artifactlist.ReqquestId.value;
								   
	  strURL = "./SysMgmt.html?process=WSdenied&StageValue="+StageValue+"&StageName="+StageName+"&ArtifactId="+ArtifactId+"&ProcessId="+ProcessId+"&StageNo="+stageNo+"&RequestID="+ReqquestId;
	  
	       window.location.href = strURL;
	 			}
	 			   }


	 	 
	 	}

 
 
 

//window.onload=call();
 
 

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
  
	 
                      <form action="SysMgmt.html" name="artifactlist" id="artifactlist">
                       <div class="col-lg-12 col-md-12 col-sm-12">
						
                  			 <h3 class="title">Business Services Voting List</h3>
				  		 
				 		</div>
						
						
					 <div class="col-lg-12 col-md-12 col-sm-12">
						&nbsp;
					 </div>
					<div class="col-lg-12 col-md-12 col-sm-12">	
					
						 <div class="col-lg-1 col-md-1 col-sm-1 col-sm-offset-1">
						 <label class="name form-div-6 subtitle" >
						  SerialId
						 </label>
						 </div>
						 <div class="col-lg-2 col-md-2 col-sm-2">
						 <label class="name form-div-6 subtitle" >
							Artifact Name
						 </label>
						 </div>
						 <div class="col-lg-2 col-md-2 col-sm-2">
						 <label class="name form-div-6 subtitle" >
						  Artifact Description
						 </label>
						 </div>
						 <div class="col-lg-2 col-md-2 col-sm-2">
						 <label class="name form-div-6 subtitle" >
						 Date of Request Raised
						 </label>
						 </div>
						 <div class="col-lg-1 col-md-1 col-sm-1">
						 <label class="name form-div-6 subtitle" >
						 StageName
						 </label>
						 </div>
						  <div class="col-lg-1 col-md-1 col-sm-1">
						 <label class="name form-div-6 subtitle" >
						 Actions
						  </label>
						 </div>
						  <div class="col-lg-1 col-md-1 col-sm-1">
						 <label class="name form-div-6 subtitle" >
						  Comments
						 </label>
						 </div>
					 </div>
					  
                         
                             
                            <%
   Map <Integer,String[]> artifactlist=(HashMap)request.getAttribute("WSvotinglist");
    //System.out.println  ("artifactData"+artifactData.size());                                           
                                                 
   if (artifactlist != null && artifactlist.size() != 0) {  
                                                            
   Iterator iter = artifactlist.entrySet().iterator();
                                                                //String [] userType = {ID, name };
                                                                while (iter.hasNext()) {
                                                                	Map.Entry mEntry = (Map.Entry) iter.next();
                                                                	
                                                                	String [] artiType=(String[])mEntry.getValue();
                                                                	int id=(Integer)mEntry.getKey();
                                                                	String ArtifactName=artiType[0];
                                                                	String ArtifactDescription=artiType[1];
                                                                	String DORequest=artiType[2]; 
                                                                	String StageName=artiType[3];
                                                                	String Comments=artiType[4];
                                                                	String ProcessId=artiType[5];
                                                                	String ArtifactId=artiType[6];
                                                                	String StageValue=artiType[7];
																	String StageNo=artiType[8];
																	String reqId=artiType[9];
																	
																	 if(Comments==null){
																	 Comments="NA";
																	 }

                                                %>
					 <div class="col-lg-12 col-md-12 col-sm-12">
						 <div class="col-lg-1 col-md-1 col-sm-1 col-sm-offset-1">
						 
						 <input type="radio" name="chk" value="<%=id%>" />
						 
						 </div>
						 
						 <div class="col-lg-2 col-md-2 col-sm-2">
							<%=ArtifactName%>
						 </div>
						  <div class="col-lg-2 col-md-2 col-sm-2">
							 <%=ArtifactDescription%>
						 </div>
						  <div class="col-lg-2 col-md-2 col-sm-2">
							<%=DORequest%>
						 </div>
						  <div class="col-lg-2 col-md-2 col-sm-2">
							  <%=StageName%>
						 </div>
						  <div class="col-lg-1 col-md-1 col-sm-1">
							  <div class="col-lg-1 col-md-1 col-sm-1">
							   
							<input name="button" type="button" onclick="approved()" value="Approve" class="button-add" style="border-bottom-style:groove;border-bottom-width:thin;border-bottom-color:#66CCFF;color:#3366FF;"/>
							
							 </div>
							  <div class="col-lg-1 col-md-1 col-sm-1">
							 
								<input name="button" type="button" onclick="denied()" value="Deny" class="button-dang" style="border-bottom-style:groove;border-bottom-width:thin;border-bottom-color:#66CCFF;color:#3366FF;"/>
							
							 </div>
							</div>
						 <div class="col-lg-1 col-md-1 col-sm-1">
						 
							<%=Comments%>
						
						 </div>
					 </div>					
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>						
						 		  <input type="hidden" value="<%=StageName%>" name="StageName"/>
                                <input type="hidden" value="<%=reqId%>" name="ReqquestId"/>
                                <input type="hidden" value="<%=StageNo%>" name="StageNo"/>
                                <input type="hidden" value="<%=StageValue%>" name="StageValue"/>
                                <input type="hidden" value="<%=ArtifactId%>"  name="ArtifactId"/>
                                <input type="hidden" value="<%=ProcessId%>" name="ProcessId"/></td>
                            								  
                          
                            <%
                                                 
                                               	
                                                                   
                                                                }
   }
   else{
	   %>
	   				
                     <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-5 col-md-5 col-sm-5">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6">
                     <h4>NO DATA</h4>
					 </div>
					 </div> 
                            <%
   }
                                                %>
                            <!-- CONTENTS START HERE -->
                        
                    
              <!-- CONTENTS END HERE -->  
			          
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

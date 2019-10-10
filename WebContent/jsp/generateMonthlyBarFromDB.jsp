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
<!--==============================================IMPORT THE CLASS START HERE============================================-->
<!--==============================================IMPORT THE CLASS END HERE============================================-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Integrated Enterprise Dashboard</title>
<meta charset="utf-8">    
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
<meta name="description" content="Your description">
<meta name="keywords" content="Your keywords">
<meta name="author" content="Your name">
<meta name = "format-detection" content = "telephone=no" />
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
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="js/basic.js" type="text/javascript" ></script>
 <script src="js/calendar2.js" type="text/javascript"></script>
<!--==============================================JAVASCRIPT AND CSS LINK START HERE============================================-->


<!--==============================================JAVASCRIPT AND CSS LINK END HERE============================================-->
</head>


<body>

<!--==============================================header start here============================================-->
<div>
 <!-- HEADER START HERE -->
<%@ include file = "../../include/menschForceHeader.jsp" %>
  <!-- HEADER ENDS HERE -->
</div>
<!--==============================================header end here============================================-->

<div class="content">
<!--==============================================content start here============================================-->
<section id="content">

  <table width=100% height="350" border="0" cellpadding="0" cellspacing="0" class="content_new" align="center">
     

  <tr>
      <td align="center" valign="middle" class="tableCommonBg">
	  <!--==============================================import content code start here============================================-->
	  <table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab" style="border:thin;border-color:#000000;border-style:groove;border-width:thin">
            
            
            <tr>
                <td class="tableCommonBg">
                    
                    <table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
                         <tr>
                            <td height="64" width="500" style="border-bottom:thin;border-bottom-color:#000000;border-bottom-style:groove;border-bottom-width:thin">
                              <h2 align="center">Monthly Sales Report</h2>
                            </td>
                        </tr>
                         <tr>
                            
                            <td width="500" class="subDeptTablePad">
                                <!-- CONTENTS START HERE -->
								<% String graphNew=(String)request.getAttribute("graphsCount");
								           String graph[]=graphNew.split(",");
										   int arrayLen=graph.length;
										   if(arrayLen==4) {
								 %>
                                 <table width="90%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
                                    <!-- <tr>
                                         <td colspan="2" class="tblMainHead"><strong>Create</strong> Project: <span class="styleBoldTwo">Compose a Message </span></td>
                                   </tr> -->
                                    <tr>
                                        
                                        <td rowspan="2" class="tblDescrp">
									<img src="images/chart.png" width="500" height="400" border="2"  ></img>
        
										</td>
									</tr>
									<tr>
									<td rowspan="2" class="tblDescrp">
									<img src="images/PyramidChart.png" width="500" height="400"  border="2" ></img>
									
									</td>
									</tr>
							  </table>
									<table width="90%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
									<tr>
									<td rowspan="2" class="tblDescrp">
									<img src="images/Linechart.png" width="500" height="400" border="2"  ></img>
									</td>
									</tr>
									<tr>
									<td rowspan="2" class="tblDescrp">
									<img src="images/pie.png" width="500" height="400" border="2"   ></img>
									</td>
									</tr>
									
								</table>
								<% } else if(arrayLen==2) { %>
								 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
                                    <!-- <tr>
                                         <td colspan="2" class="tblMainHead"><strong>Create</strong> Project: <span class="styleBoldTwo">Compose a Message </span></td>
                                   </tr> -->
								   <% for(int i=0;i<arrayLen;i++) { %>
                                    <tr>
                                        
                                        <td rowspan="2" class="tblDescrp">
									<img src="images/<%=graph[i]%>.png" width="500" height="400" border="2"  ></img>
        
										</td>
									</tr>
									<% } %> 
									
							  </table>
									<% } else if(arrayLen==3) { %>
									<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
                                    <!-- <tr>
                                         <td colspan="2" class="tblMainHead"><strong>Create</strong> Project: <span class="styleBoldTwo">Compose a Message </span></td>
                                   </tr> -->
								   <% for(int i=0;i<arrayLen;i++) { 
									   if(i<=1) { %>
                                    <tr>
                                        
                                        <td rowspan="2" class="tblDescrp">
									<img src="images/<%=graph[i]%>.png" width="500" height="400" border="2"  ></img>
        
										</td>
									</tr>
									<% }  else { %>
							  </table>
				      <tr>
                                        
                                        <td rowspan="2" class="tblDescrp">
									<img src="images/<%=graph[i]%>.png" width="500" height="400" border="2"  ></img>
        
										</td>
					  </tr>
									<% } } %>
				  </table>
									<% } else if(arrayLen==1) { %>
										<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formLayout" align="center">
                                    <!-- <tr>
                                         <td colspan="2" class="tblMainHead"><strong>Create</strong> Project: <span class="styleBoldTwo">Compose a Message </span></td>
                                   </tr> -->
                                    <tr>

                                        <td rowspan="2" class="tblDescrp">
									<img src="images/<%=graph[0]%>.png" width="500" height="400" border="2"  ></img>

										</td>
									</tr>
					
									

				  </table>
									<% } %>
						
			  </td>
					</tr>
					<tr>
                <td>
                 <center>  
                   <input type="submit" value="close" name="close" onclick="window.close('DailySalesGraph');return false;" class="btn text_3 color_3" />
				   </center>
                </td>
            </tr>
					</table>
	  
	  
	  
	  
	  
	  <!--==============================================import content code end here============================================-->
	  
	  
	  </td>
    </tr>
	
		

  </table>
  </section>
  
<!--==============================================content end here============================================-->
</div>
<!--==============================================footer start here============================================-->
 <div>			 
			
                    <!-- FOOTER STARTS HERE -->
                        
						<%@ include file = "../../include/menschForceFooter.jsp" %>
                    <!-- FOOTER ENDS HERE -->
               
 </div>
<!--==============================================footer end here============================================-->

</body>
</html>

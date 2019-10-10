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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta charset="utf-8">    
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" href="img/favicon.ico" type="image/x-icon">

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
<!--==============================================JAVASCRIPT AND CSS LINK START HERE============================================-->
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<script language="javascript">
            
            function validate(){
                if(document.generateChartByXLS.reportTitle.value=="" || document.generateChartByXLS.reportTitle.value.indexOf(' ')==0){
                    alert("Please enter the Report Title");
                    document.generateChartByXLS.reportTitle.focus();
                    return false;
                }
				 if(document.generateChartByXLS.xlsFile.value=="" || document.generateChartByXLS.xlsFile.value.indexOf(' ')==0){
                    alert("Please upload SpreadSheet");
                    document.generateChartByXLS.xlsFile.focus();
                    return false;
                }
                
                if(document.generateChartByXLS.projectDescription.value.length==0 || document.generateChartByXLS.projectDescription.value.indexOf(' ')==0){
                    alert("Please enter the Project Description");
                    document.createProject.projectDescription.focus();
                    return false; 
                }
                return true;
            }
            function popUp_report(type){
                var val =null;
                var year =document.getElementById("year").value;
                var paymentMode =document.getElementById("paymentMode").value;
                var view=document.getElementById("view").value;
				 var numArr = new Array ();
				j=0;
				var graph=document.getElementsByName("graph");
				
				for (i = 0; i < graph.length; i++)
				{
	                        if(graph[i].checked)
					{
								
								numArr[j]=graph[i].value;
								j++;
								
					}
					          
					               
                          }
                if(type=='daily'){
                var month = document.getElementById("month").value;
           val=  window.open("MakeChart.html?process="+type+"&month="+month+"&year="+year+"&paymentMode="+paymentMode+"&Submit=Submit&message=xlsToGraph"+"&view="+view+"&graph="+numArr.join()
             ,'DailySalesGraph',
			  'width=1200,height=700,toolbar=no,location=no,directories=yes,status=yes,menubar=no,scrollbars=yes,copyhistory=no,resizable=yes')
              if(val == null) {
                alert("Plz Enable Site Popup Allowed");
               }
                }
                else if(type='monthly'){
                val=  window.open("MakeChart.html?process="+type+"&year="+year+"&paymentMode="+paymentMode+"&Submit=Submit&message=xlsToGraph"+"&view="+view+"&graph="+numArr.join()
             ,'DailySalesGraph',
			  'width=1200,height=700,toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,copyhistory=yes,resizable=yes')
                if(val == null) {
                alert("Plz Enable Site Popup Allowed");
               }
                }
    }
        function UpdateSelect()
{
select_value = document.generateChartByXLS.view.value;
var id = 'hide_this_row';
var obj = '';
obj = (document.getElementById) ? document.getElementById(id) : ((document.all) ? document.all[id] : ((document.layers) ? document.layers[id] : false));

if(select_value =2)
{
obj.style.display = ( obj.style.display != "none" ) ? "none" : "";//Hide Fields
}
else
{
obj.visibility = "show";//Show Fields
}
}
                 
            
        </script>
<!--==============================================JAVASCRIPT AND CSS LINK END HERE============================================-->
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
					
							<h3 class="title"> 
								<% 
                                            String type=(String)request.getAttribute("type");
                                          
                                            if( (type.equals("daily")) && (type!=null) )
                                                { %>
                                                   Daily Sales Report
                                                   <% }
                                            else{ %>
                                               Monthly Sales Report
                                           <% } %>
										   </h3>
				 
				 </div>
			
                                           
                                             
                                      

       <form   name="generateChartByXLS" method="post"   onsubmit="return validate();" action="MakeChart.html">

                                             
            
                                                            
                                                            <div id="inboxTbl">
                      <% String month[]={"Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"}; %>
                                                             <% 
                                                             if(type.equalsIgnoreCase("daily") && (type!=null))
                                                            
                                                { %>                   <input type="hidden" name="process" value="daily" />
												
												
												<div class="col-lg-12 col-md-12 col-sm-12">
													
													<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
														<label class="name form-div-6">
														Select Month <span class="asterisk">*</span>
														</label>
													</div>
													<div class="col-lg-3 col-md-3 col-sm-3">
													<select  name="month" id="month" class="form-control">          
															<% for(int i=0;i<12;i++) { %>
															<option value=<%=(i+1)+""%> ><%=month[i]%></option>  <% } %>
													</select>
													
													</div> 
												
												</div>
												
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
												
												 <% } %>
                                              <input type="hidden" name="process" value="monthly" id="process" />
												
												
												<div class="col-lg-12 col-md-12 col-sm-12">
													
													<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
													<label class="name form-div-6">
													Select Year <span class="asterisk">*</span>
													</label>
													</div>
													<div class="col-lg-3 col-md-3 col-sm-3">
													 <select  name="year" id="year" class="form-control">
																	<option value="2006">2006</option>
																	<option value="2007">2007</option>
																	<option value="2008">2008</option>
																	<option value="2009">2009</option>
																	<option value="2010">2010</option>
																	<option value="2011">2011</option>
																	<option value="2012">2012</option>
																	<option value="2013">2013</option>
																	<option value="2014">2014</option>
																	<option value="2015">2015</option>
																	<option value="2016">2016</option>
                                                                                                                                        
																	
													</select>
                                                     <div id="statusId"></div>
													</div> 
													
												</div>
												
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
                                                                
                                                
												<div class="col-lg-12 col-md-12 col-sm-12">
													
													<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
													<label class="name form-div-6">
													Payment Mode <span class="asterisk">*</span>
													</label>
													</div>
													<div class="col-lg-3 col-md-3 col-sm-3">
													<select  name="paymentMode" id="paymentMode" class="form-control">
																	<option value="Visa">Visa</option>
																	<option value="MasterCard">MasterCard</option>
																	<option value="check">check</option>
													</select>
													
													</div> 
													
												</div>
												
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
                                                                
                                                                <!-- <tr>
                                                   <th height="25" class="tblMainHead"><span class="fonts">Chart Type :</span></th>
                                                                  <td height="25" class="listCellBg">
																	<select  name="chartType">
																	<option value="Pie">Pie</option>
																	<option value="Bar">Bar</option>
                                                                                                                                        <option value="Line">Line</option>
                                                                                                                                        <option value="Pyramid">Pyramid</option>
																	<option value="Area">Area</option>
																	
                                                                                                                                        
																	</select>
																	<span class="asterisk">*</span>
																</td>
                                                               </tr> -->
															   
															   
											<div class="col-lg-12 col-md-12 col-sm-12">
													
													<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
													<label class="name form-div-6">
													Report type 
													</label>
													</div>
													<div class="col-lg-3 col-md-3 col-sm-3">
													<select name="view" id="view" onChange="UpdateSelect();" class="form-control">
														<option value='1'>Graphical</option>
														<option value='2'>Tabular</option></select>
													</div> 
													
												</div>
                                                             
                            <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
                            
                            
                      
                         <div id="hide_this_row">
						 
							<div class="col-lg-12 col-md-12 col-sm-12">
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name form-div-6">
								Graph types 
								</label>
								</div>
								<div class="col-lg-3 col-md-3 col-sm-3">
								 <% 
                                 
                               
                                 if(type.equalsIgnoreCase("daily") || type.equalsIgnoreCase("monthly"))  { %>
                                 <input type="checkbox" name="graph"  value="pie" checked>Pie<br>
								 <input type="checkbox" name="graph" value="Linechart">Line<br>
								 <input type="checkbox" name="graph" value="chart">Bar<br>
								 <input type="checkbox" name="graph" value="PyramidChart">Pyramid<br>
								 
								  

<% } else { %>
<input type="checkbox" name="graph" value="pie">Pie<br>
 
<input type="checkbox" name="graph" value="line">Line<br> 
<input type="checkbox" name="graph" value="PyramidChart">Pyramid<br>
<% } %>
    
								</div> 
								
						</div>
                            
                        </div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-5 col-md-5 col-sm-5">
							</div>
							<div>
							  
							    <button type="submit" name="Submit" value="Submit" class="button-add" 
                                                                                           onclick="javascript:popUp_report('<%=request.getAttribute("type")%>')">Submit</button>
							   
							</div>
					</div>
                             
							</div>
                                                        
                                                <input type="hidden" name="message" value="xlsToGraph" />
                                                
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

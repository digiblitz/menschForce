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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Department of State</title>

<!--script src="js/countries.js"></script>--> 
 
<style>
#frmUSCISstage1 label.error{color:red;}
.asterisk{
	color:red;
}


</style>
<%String pageStatus = null;
pageStatus = (String)request.getAttribute("pageStatus");
%>
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
<script src="js/USCISforms.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/tm-scripts.js"></script>
<script  src="js/ts_picker.js"></script>
<script src="js/calendar2.js" type="text/javascript"></script>
<script src="js/datetimepicker_css.js"></script>
<link rel="stylesheet" href="css/style.css"/>

	
  <!--========================================================
CONTENT
=========================================================-->



	<div class="content indent">
	<div class="thumb-box14">
       <%
	ArrayList employeeDetails=(ArrayList) request.getAttribute("employeeDetails"); 
	if(employeeDetails!=null && employeeDetails.size()!=0){
		Iterator itr = employeeDetails.iterator();
		while (itr.hasNext()) {
			String TempList[] = (String[])itr.next();
			String empuniqueId=TempList[0];
			String empId = TempList[3];
		 	String empdberpPartyid = TempList[4];
		 	String empSSNNo = TempList[5];
		 	String empFname=TempList[8];
		 	String empLname=TempList[10];
		 	String empEmailId=TempList[14];
		 	String empCompany=TempList[30];
		 	
			empSSNNo = String.valueOf(empSSNNo).substring(5);
				
			  if(pageStatus != null && pageStatus.equalsIgnoreCase("init")){
				  int stagesize=0;
				  String[] stageThreeStatusArray =  (String[])request.getAttribute("stageThreeStatusArray"); 
				   if(stageThreeStatusArray != null){
					   stagesize= stageThreeStatusArray.length; 
				  }else{
					   stagesize = 8;
				  }
					
					
    %>
	    <form name="frmUSCISstage3" id="frmUSCISstage3" action="doUSCISStage3.html" method="post">
          <input type="hidden" name="empUniqueId" value="<%=empuniqueId%>"/>
          <input type="hidden" value="<%=stagesize%>" name="stagesize" id="stagesize"> 
				<div class="row">			
					<div class="col-lg-12 col-md-12 col-sm-12">
							
						
							<h3 class="title">Stage 3 : H1 - B Transfer or Extension</h3>
						
					</div>
					
				</div>
			 
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
					<div class="row">	
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Employee Id<span class="asterisk">*</span></label>
								
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="empId" type="text" id="empId" class="form-control" readonly value="<%=empId%>"/>
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>IS H1-B Transfer (or) Extension <span class="asterisk">*</span></label>
								
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1" align="right">
								<input name="H1_BTransfer" type="checkbox" value="yes" id="H1_BTransfer" />yes
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="H1_BTransfer" type="checkbox" id="H1_BTransfer" value="no"/>No <span class="asterisk">(* Wait until I-797 Form is Approved)</span>
							</div>
							
						</div>
					
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
							<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Created Date:<span class="asterisk">*</span></label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="CreateDate" type="text" id="CreateDate" readonly class="form-control"/>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal" >
								<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('CreateDate','MMddyyyy','dropdown',true,'24',true)" />
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Acknowledgement Date</label>
								<span class="asterisk">*</span>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="AcknowledgementDate" type="text" id="AcknowledgementDate" readonly class="form-control"/>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal" >
								<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('AcknowledgementDate','MMddyyyy','dropdown',true,'24',true)" />
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>System Date</label>
								
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="SystemDate" type="text" Placeholder="System Date" id="SystemDate" class="form-control" readonly />
							</div>
							
							
						</div>
						
						
					</div>
					
					
				<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
				
				
				<div class="row">		
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-1 col-md-1 col-sm-1 col-sm-offset-3">
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="button" class="button-edit" value="Previous" onclick='location.href="initUSCISStage2.html?empUniqueId=<%=empuniqueId%>";' name="previous" />

						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="submit"  value="Submit" class="button-add" />
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="button" class="button-dang" value="Close" />
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2">
							<input type="button" class="button-edit" value="Next" onclick='location.href="initUSCISStage4.html?empUniqueId=<%=empuniqueId%>";' name="next" />
						</div>
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
				<div class="row">		
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div  class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-4 stagetitle ">
						<label class="">Stages</label>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-3 col-md-3 col-sm-3  col-sm-offset-5" align="center">
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage1" value="<%=stageThreeStatusArray[0]%>" onclick='location.href="initUSCISStage1.html?empUniqueId=<%=empuniqueId%>";'>1</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage2" value="<%=stageThreeStatusArray[1]%>" onclick='location.href="initUSCISStage2.html?empUniqueId=<%=empuniqueId%>";'  >2</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage3" value="<%=stageThreeStatusArray[2]%>" onclick='location.href="initUSCISStage3.html?empUniqueId=<%=empuniqueId%>";' >3</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage4" value="<%=stageThreeStatusArray[3]%>" onclick='location.href="initUSCISStage4.html?empUniqueId=<%=empuniqueId%>";' >4</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage5" value="<%=stageThreeStatusArray[4]%>" onclick='location.href="initUSCISStage5.html?empUniqueId=<%=empuniqueId%>";'>5</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage6" value="<%=stageThreeStatusArray[5]%>" onclick='location.href="initUSCISStage6.html?empUniqueId=<%=empuniqueId%>";'>6</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage7" value="<%=stageThreeStatusArray[6]%>" onclick='location.href="initUSCISStage7.html?empUniqueId=<%=empuniqueId%>";'>7</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage8" value="<%=stageThreeStatusArray[7]%>" onclick='location.href="initUSCISStage8.html?empUniqueId=<%=empuniqueId%>";'>8</button>
								
							</div>
						</div>
					</div>
				</div>
			
		</form>	
		<%}else if(pageStatus != null && pageStatus.equalsIgnoreCase("update")){ 
			String unique_USCIS_id = (String)request.getAttribute("unique_USCIS_id"); 
			String[] stageThreeDataArray =  (String[])request.getAttribute("stageThreeDataArray"); 
			String[] stageThreeStatusArray =  (String[])request.getAttribute("stageThreeStatusArray"); 
			//System.out.println("stageThreeDataArray[0]-----------in--------jsp------->"+stageThreeDataArray[0]);
			//System.out.println("stageThreeDataArray[1]-----------in--------jsp------->"+stageThreeDataArray[1]);
			int stagesize= stageThreeStatusArray.length;
		
		%>
		<form name="frmUSCISstage3" id="frmUSCISstage3" action="doUSCISStage3.html" method="post">
          <input type="hidden" name="empUniqueId" value="<%=empuniqueId%>"/>
          <input type="hidden" value="<%=stagesize%>" name="stagesize" id="stagesize"> 
				<div class="row">			
					<div class="col-lg-12 col-md-12 col-sm-12">
							
						
							<h3 class="title">Stage 3 : H1 - B Transfer or Extension</h3>
						
					</div>
					
				</div>
			 
				<div class="col-lg-12 col-md-12 col-sm-12">
				&nbsp;
				</div>
				
					<div class="row">	
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Employee Id<span class="asterisk">*</span></label>
								
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="empId" type="text" id="empId" class="form-control" readonly value="<%=empId%>"/>
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
					
						<%if(((String)request.getAttribute("stage3H1BTransferStatus")).equalsIgnoreCase("yes")){
						%>
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>IS H1-B Transfer (or) Extension <span class="asterisk">*</span></label>
								
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1" align="right">
								<input name="H1_BTransfer" type="checkbox" checked value="yes" id="H1_BTransfer"/>yes
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="H1_BTransfer" type="checkbox" id="H1_BTransfer" value="no"/>No <span class="asterisk">(* Wait until I-797 Form is Approved)</span>
							</div>
							
						</div>
					<%}else{%>
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>IS H1-B Transfer (or) Extension <span class="asterisk">*</span></label>
								
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1" align="right">
								<input name="H1_BTransfer" type="checkbox" value="yes" id="H1_BTransfer" />yes
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="H1_BTransfer" type="checkbox" checked id="H1_BTransfer" value="no"/>No <span class="asterisk">(* Wait until I-797 Form is Approved)</span>
							</div>
							
						</div>
					<%}%>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
							<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Created Date:<span class="asterisk">*</span></label>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="CreateDate" type="text" id="CreateDate" readonly class="form-control" value="<%=(String)request.getAttribute("stage3CreateDate")%>"/>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal" >
								<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('CreateDate','MMddyyyy','dropdown',true,'24',true)" />
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>Acknowledgement Date</label>
								<span class="asterisk">*</span>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="AcknowledgementDate" type="text" id="AcknowledgementDate" readonly class="form-control" value="<%=(String)request.getAttribute("stage3AcknowledgementDate")%>"/>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal" >
								<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('AcknowledgementDate','MMddyyyy','dropdown',true,'24',true)" />
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label>System Date</label>
								
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input name="SystemDate" type="text" Placeholder="System Date" id="SystemDate" class="form-control" readonly value="<%=(String)request.getAttribute("stage3SystemDate")%>"/>
							</div>
							
							
						</div>
						
						
					</div>
					
					
				<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
				
				
				<div class="row">		
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-1 col-md-1 col-sm-1 col-sm-offset-3">
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="button" class="button-edit" value="Previous" onclick='location.href="initUSCISStage2.html?empUniqueId=<%=empuniqueId%>";' name="previous" />

						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="submit"  value="Submit" class="button-add" />
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
							<input type="button" class="button-dang" value="Close" />
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2">
							<input type="button" class="button-edit" value="Next" onclick='location.href="initUSCISStage4.html?empUniqueId=<%=empuniqueId%>";' name="next" />
						</div>
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
				<div class="row">		
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div  class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-4 stagetitle ">
						<label class="">Stages</label>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
				</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-lg-3 col-md-3 col-sm-3  col-sm-offset-5" align="center">
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage1" value="<%=stageThreeStatusArray[0]%>" onclick='location.href="initUSCISStage1.html?empUniqueId=<%=empuniqueId%>";'>1</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage2" value="<%=stageThreeStatusArray[1]%>" onclick='location.href="initUSCISStage2.html?empUniqueId=<%=empuniqueId%>";'  >2</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage3" value="<%=stageThreeStatusArray[2]%>" onclick='location.href="initUSCISStage3.html?empUniqueId=<%=empuniqueId%>";' >3</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage4" value="<%=stageThreeStatusArray[3]%>" onclick='location.href="initUSCISStage4.html?empUniqueId=<%=empuniqueId%>";' >4</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage5" value="<%=stageThreeStatusArray[4]%>" onclick='location.href="initUSCISStage5.html?empUniqueId=<%=empuniqueId%>";'>5</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage6" value="<%=stageThreeStatusArray[5]%>" onclick='location.href="initUSCISStage6.html?empUniqueId=<%=empuniqueId%>";'>6</button>
								
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage7" value="<%=stageThreeStatusArray[6]%>" onclick='location.href="initUSCISStage7.html?empUniqueId=<%=empuniqueId%>";'>7</button>
							</div>
							<div  class="col-lg-1 col-md-1 col-sm-1 displaypageNum ">
								<button type="button" id="stage8" value="<%=stageThreeStatusArray[7]%>" onclick='location.href="initUSCISStage8.html?empUniqueId=<%=empuniqueId%>";'>8</button>
								
							</div>
						</div>
					</div>
				</div>
			
		</form>	
		<%}}}%>
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

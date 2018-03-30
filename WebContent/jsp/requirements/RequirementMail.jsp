<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ pagelanguage="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>



<script>
function mydocument(){
	//alert("mmmmm");
	var pn = "1";
	pn = document.RequirementMails.pn.value;
	var fromdate=document.getElementById("fromdate").value;
	var todate=document.getElementById("todate").value;
	//alert(fromdate+"----"+todate);
	location.href="RequirementMailsByDate.html?fromdate="+fromdate+"&todate="+todate+"&pn="+pn;
	
}
function postDataForPagination() {
	//alert("check");
	var pn = document.RequirementMails.pn.value;
	strURL = "RequirementMails.html?pn="+pn;
	window.location.href = strURL;

	
}
function postDataForPagination1() {
	//alert("yyyy");
	var pn = document.RequirementMails.pn.value;
	alert(pn);
	var fromdate=document.RequirementMails.fromdate1.value;
	var todate=document.RequirementMails.todate1.value;
	alert(fromdate+"----"+todate);
	location.href="RequirementMailsByDate.html?fromdate="+fromdate+"&todate="+todate+"&pn="+pn;
}

</script>
<style>
.table-row-line.header
{ 
	 display: block;
	 flex-direction:row;
    background-color: #dddddd;
    font-weight: bold;
    padding-top: 16px;
    padding-bottom: 35px;
   

}
.table-row-line {
    border-bottom: 1px solid #fcf8e3;
    border-collapse: collapse;
    margin-left: 63px;
    margin-right: 30px;
    padding-right: initial;
    padding-left: initial;

}
.table-row {
    border-bottom: 1px solid #fcf8e3;
    border-collapse: collapse;
    margin-left: 63px;
    margin-right: 30px;
	
    padding-right: initial;
    padding-left: initial;
	padding-top: 16px;
    padding-bottom: 35px;

}
</style>
<%String pageName = null;
String fromdate = null;
String todate = null;
fromdate = (String)request.getAttribute("fromdate");
todate = (String)request.getAttribute("todate");
System.out.println("fromdate in jsp-----------------------"+fromdate);
System.out.println("todate in jsp-----------------------"+todate);
pageName = (String)request.getAttribute("page");%>
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
	
  <!--========================================================
CONTENT
=========================================================-->

<script src="js/datetimepicker_css.js"></script>
<script src="js/tm-scripts.js"></script>
<script  src="js/ts_picker.js"></script>

	<div class="content indent">
	<div class="thumb-box14">
	
	
       
	   
            <div class="row">			
				
			<div class="col-lg-12 col-md-12 col-sm-12">
					
						<h3 class="title">Requirement Mails</h3>
					
				 </div>
				 
				 
				            
							 
							<div class="col-lg-12 col-md-12 col-sm-12">
								&nbsp;
							</div>
							
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-1 col-md-1 col-sm-1">
								&nbsp;
							</div>
							 <%if(pageName != null &&  pageName.equalsIgnoreCase("RequirementMails")){%>
							 <div class="col-lg-2 col-md-3 col-sm-3">
							 
							 <input name="fromdate" type="text" class="form-control" id="fromdate"
							    placeholder="From Date" readonly>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal">
								<img class="image1" src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('fromdate')" />
							</div>
							<div class="col-lg-2 col-md-3 col-sm-3">
							
							 <input name="todate" type="text" id="todate"
							  class="form-control"  placeholder="To Date" readonly>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal">
								<img class="image1" src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('todate')" />
							</div>
							<%}else if(pageName != null &&  pageName.equalsIgnoreCase("RequirementMailsByDate")){%>
							<div class="col-lg-2 col-md-3 col-sm-3">
							 
							 <input name="fromdate" type="text" id="fromdate"
							  value="<%=fromdate%>" class="form-control" placeholder="From Date" readonly>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal">
								<img class="image1" src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('fromdate')" />
							</div>
					
							<div class="col-lg-2 col-md-3 col-sm-3">
							
							 <input name="todate" type="text" id="todate"
							  value="<%=todate%>" class="form-control"  placeholder="To Date" readonly>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 datecal">
								<img class="image1" src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer" border="0" onclick="javascript:NewCssCal('todate')" />
							</div>
							
							<%}%>
							<div class="col-lg-1 col-md-1 col-sm-1">
							<button type="button" class="button-add"  onClick="mydocument();">Submit</button>
							 </div>
						</div> 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
					</div>
					
					 <form class='form-horizontal'  name="RequirementMails" id="myform" method="post" action="RequirementMails.html">
					 <input type="hidden" value="<%=fromdate%>" name="fromdate1" id="fromdate1"/>
					 <input type="hidden" value="<%=todate%>" name="todate1" id="todate1"/>
						 <%	
						String pno = (String) request.getAttribute("pNo");
								int pages = Integer.parseInt(pno);
								String pagenumber = (String) request.getAttribute("pagenumber");
								int pNo=0;
								if(pagenumber==null){
									pNo = 1;
								}
								else{
							   pNo = Integer.parseInt(pagenumber);
								}		
								
							%>   
					
					 <div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-1 col-md-1 col-sm-1">
					&nbsp;
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1">
					 <label class="name form-div-6" style="color:#0072c6">View :</label>
					 <%if(pageName != null &&  pageName.equalsIgnoreCase("RequirementMails")){%>
					<select name="pn" id="pn" class="form-control" onchange="postDataForPagination();">
													<%
														for(int i = 1; i <=pages; i++) {
															
															if(pNo==i){
															%>
																<option selected="selected" value="<%=i%>"><%=i%></option>
															<%
															}
															else{
															%>
																<option value="<%=i%>"><%=i%></option>
															<%
															}
														}
														%>
											  </select>
					<%}else if(pageName != null &&  pageName.equalsIgnoreCase("RequirementMailsByDate")){%>
					<select name="pn" id="pn" class="form-control" onchange="postDataForPagination1();">
													<%
														for(int i = 1; i <=pages; i++) {
															
															if(pNo==i){
															%>
																<option selected="selected" value="<%=i%>"><%=i%></option>
															<%
															}
															else{
															%>
																<option value="<%=i%>"><%=i%></option>
															<%
															}
														}
														%>
					</select>
					<%}%>
											 of <%=pages%> 
					</div>
					</div>
					</div>
					 
						
								 	
							  
					</form>
							  
					 
					
					 <div class="col-lg-12 col-md-12 col-sm-12">
					&nbsp;
					</div>
					
					
				 
				 <div class="row">	
				 
				 <div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-1 col-md-1 col-sm-1">
					&nbsp;
					</div>
					<div class="table-row-line wrapper header">
					<div class="col-lg-1 col-md-1 col-sm-1">
					<label class="name form-div-6" style="color:#0072c6">
							ID
                            </label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						</div>
						
						
							
                           
				
					<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							DocumentType
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					<div class="col-lg-5 col-md-5 col-sm-5">
				
					<label class="name form-div-6" style="color:#0072c6">
						Name
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					
							
							
                           
					
					
					<div class="col-lg-2 col-md-2 col-sm-2">
				
					<label class="name form-div-6" style="color:#0072c6">
							Modified
							</label>
							<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
                           
					</div>
					</div>
</div>					
                           
					</div>
					
					
					 <%ArrayList req_mail=(ArrayList) request.getAttribute("req_mail"); 
			//System.out.println(CandidateList.size()+" list ");
			System.out.println(req_mail.size());
			
				
            Iterator itr = req_mail.iterator();
    while (itr.hasNext()) {    
        String TempList[] = (String[])itr.next();
		String Id=TempList[0];
		 String ContentType = TempList[1];
		 String Name=TempList[2];
		 String Modified=TempList[3];
		String download=TempList[4];
		 
    
    %>
	<div class="row">	
    <div class="col-lg-12 col-md-12 col-sm-12">	
	<div class="col-lg-1 col-md-1 col-sm-1">
	&nbsp;
	</div>
	<div class="table-row">
	<div class="col-lg-1 col-md-1 col-sm-1">
	<label><%=Id%></label>
	</div>
	
	
	<div class="col-lg-2 col-md-2 col-sm-2">
	<label><%=ContentType%></label>
	</div>
	<div class="col-lg-5 col-md-5 col-sm-5">
				
	<a href="<%=download%>" download ><%=Name%></a>
	
	</div>
	<div class="col-lg-2 col-md-2 col-sm-2">
	<label><%=Modified%></label>
	</div>
	
	
	</div>
	
	<%}%>	
					
					
			</div>
			
			
				</div>
				</div>
				</div>
				
				

</body>
</html>

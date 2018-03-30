<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.regex.*" %>
<%@ page import="com.hlchorse.form.util.*" %>
  <!DOCTYPE html>
  <html lang="en">
  <link rel="icon" href="file:///D|/Jboss 4.2.3GA-18080/server/default/tmp/deploy/tmp5502137464769960005menschForce_Development-exp.war/jsp/img/favicon.ico" type="image/x-icon">
    <head>
      <title>
        Home
      </title>
      
      
  
     <title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<script src="js/basic.js" type="text/javascript" ></script>

<script src="js/cscombo_new.js" type="text/javascript" ></script>
<script src="js/frmMembRegi.js" type="text/javascript" ></script>
<script src="js/EditMemberUserReg.js" type="text/javascript" ></script>

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

<style>
#frmMembRegi label.error{
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
    <!--========================================================
CONTENT
=========================================================-->
 
<div class="content indent">
    
    <div class="thumb-box14">
        <div class="container">
           
		   <div class="row">
			
				<div class="col-lg-12 col-md-12 col-sm-12">
					
                   <h3 class="title">Edit User Registration</h3>
				 
				 </div>
				 
				  <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-2 col-md-2 col-sm-2">
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8" id="commonBG">
					<label class="name form-div-6">
						<p>Please use the form below to Edit/Update your registration details.You can also create your Login name and Password if you don't have one.</p>
					</label>
				   </div>
				 </div>
				 
				  <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
                
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-3 col-md-3 col-sm-3">
					</div>
					<div class="col-lg-9 col-md-9 col-sm-9" id="commonBG">
					<label class="name form-div-6">
						<font color="#018dce">Basic Information</font>
					</label>
				   </div>
				 </div>

               
                      <%
	String source = (String)request.getAttribute("source");
	String eventTypeId = (String)request.getAttribute("eventTypeId");
	String compYear = (String)request.getAttribute("compYear");
%>
					 
					
				<form name="frmMembRegi" id="frmMembRegi" method="post" class="formcss" action="user.html" onSubmit="return myvalidate(this);" onMouseOver="hide_switchDiv();" >
							<input type="hidden" name="eventTypeId" id="eventTypeId" value="<%=eventTypeId%>" />
							<input type="hidden" name="compYear" id="compYear" value="<%=compYear%>" />

                          
                            
							 <%
                                    HLCUserRegVO HLCUserRegVO=new HLCUserRegVO();
						
                         			HLCUserRegVO=(HLCUserRegVO)request.getAttribute("EditusrVect");
						               
							%>
						
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>	
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">	Salutation </label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							  <%=HLCUserRegVO.getPrefix()%>
							
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">	First Name </label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							  <%=HLCUserRegVO.getFirstName()%>
							
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
							
						
							
							
						
							
							 <%

			String mname="";
			
			if(HLCUserRegVO.getMiddleName()!=null && HLCUserRegVO.getMiddleName().trim().length()!=0)
			{
				mname=HLCUserRegVO.getMiddleName();
			}
			else
			{
				mname="";
			}
			
			%>
			
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">	Middle Name</label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							  <%=mname%>
							
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name" >	Last Name</label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							 <%=HLCUserRegVO.getLastName()%>
							
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
			
							
						
							<%

			String suff="";
			
			if(HLCUserRegVO.getSufix()!=null && HLCUserRegVO.getSufix().trim().length()!=0)
			{
				suff=HLCUserRegVO.getSufix();
			}
			else
			{
				suff="";
			}
			
			%>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">Suffix</label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							 <%=suff%>
							
							</div>
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">Date of Birth</label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
					
							
							
						
							 <%
																					
																					    if(HLCUserRegVO.getDob()!=null)
																						{
                                                                                        String[] dob1=HLCUserRegVO.getDob().split(" ");
                                                                                        String[] dob=dob1[0].split("-");
                                                                                        
                                                                                        String mm=dob[1];
                                                                                        String dd=dob[2];
                                                                                        String yy=dob[0];
                                                                                                                                                                                
                                                                                       String[] mon={"January","February","March","April","May","June","July","August","September","October","November","December"};
                                                                                       String month="";
                                                                                       
                                                                                    if(mm.equals("01"))
                                                                                        month=mon[0];
                                                                                    else if(mm.equals("02"))
                                                                                        month=mon[1];
                                                                                    else if(mm.equals("03"))
                                                                                        month=mon[2];
                                                                                    else if(mm.equals("04"))

                                                                                        month=mon[3];
                                                                                    else if(mm.equals("05"))
                                                                                        month=mon[4];
                                                                                    else if(mm.equals("06"))
                                                                                        month=mon[5];
                                                                                    else if(mm.equals("07"))
                                                                                        month=mon[6];
                                                                                    else if(mm.equals("08"))
                                                                                        month=mon[7];
                                                                                    else if(mm.equals("09"))
                                                                                        month=mon[8];
                                                                                    else if(mm.equals("10"))
                                                                                        month=mon[9];
                                                                                    else if(mm.equals("11"))
                                                                                        month=mon[10];
                                                                                    else if(mm.equals("12"))
                                                                                        month=mon[11];
                                                                                    else
                                                                                           
                                                                                       
                                                                                    %>
                                            
                                              <%
													int selmth=Integer.parseInt(mm);
													System.out.println("selmth :"+selmth);

													for(int mth=1;mth<=12;mth++)
													{
														if(selmth==mth)
														{%>
														
														
                                             <label><%=mth%></label>
											 
											 
                                              <%}else{%>
                                              
                                              <%}}%>
                                            
                                      
                                              <%
													int dat=Integer.parseInt(dd);
													for(int d=1;d<=31;d++)
													{
														if(dat==d)
														{%>
                                              
                                              <label>/<%=d%></label>
                                              
	<%}else{%>
                                              
                                              <%}}%>
                                           
                                              <%  														            												  
														int selyr=Integer.parseInt(yy);
														java.util.Calendar c5 = java.util.Calendar.getInstance();
                                                        //int day = c.get(Calendar.DAY);
                                                        //int month = c.get(Calendar.MONTH);
                                                        int year5 = c5.get(java.util.Calendar.YEAR);
                                                        //String date = day+" / "+month+" / "+year;
                                                        System.out.println("Current Date = "+year5);
                                                        for(int yr=1900;yr<=year5;yr++)
                                                                                                                    {
															if(yr==selyr)
															{%>
                                              
                                              <label>/<%=yr%></label>
                                              
<%}else{%>
                                              
                                              <%}}%>
                                            
                                            <%}
										else
										{
										%>
                                            
                                             
                                              <%
                                                                                                  java.util.Calendar c5 = java.util.Calendar.getInstance();
                                                                                                  //int day = c.get(Calendar.DAY);
                                                                                                  //int month = c.get(Calendar.MONTH);
                                                                                                  int year5 = c5.get(java.util.Calendar.YEAR);
                                                                                                  //String date = day+" / "+month+" / "+year;
                                                                                                  System.out.println("Current Date = "+year5);

                                                                                                  for(int f=1900;f<=year5;f++) 
                                                                                                  {%>
                                             
                                              <%}
                                                                                                  %>
                                           
                                            <%}%>
											
											
                                 </div> 
							
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
                                          <%
									 if(HLCUserRegVO.getGender()!=null)
                                     {%>
							
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">Gender</label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							   <%=HLCUserRegVO.getGender()%>
							
							</div>
							
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
								 <%}%>	  
							
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">EMail  <span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							 <input type="text" name="email" value="<%=HLCUserRegVO.getEmailId()%>" class="form-control"/>
							
							</div>
							
						</div>
						
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
									 
								
							<%
                                                                          String logName="";
                                                                          
                                                                          if(HLCUserRegVO.getLoginName()!=null && HLCUserRegVO.getLoginName().trim().length()!=0)
                                                                          {
                                                                            logName=HLCUserRegVO.getLoginName();
                                                                          }
                                                                          
                                                                          String userId1="";
                                                                          userId1=HLCUserRegVO.getUserId();
                                                                          
                                                                        %>
                                          <input type="hidden" name="userId" value="<%=userId1%>" />
                                          <input type="hidden" name="cmd" value="updateUsr" />
										  
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">User Name (Login ID)  </label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							 <%=logName%>
							
							</div>
							
						</div>
						
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">Choose A Secret Question <span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
								 <select name="QSelect" id="select" class="form-control">
                                            <%
																														
										String[] secQuest={"What is your favorite passtime?","What is your pets name?","What was your first car?","What is your mothers first name?","Which is your favorite vacation spot?"}; 
									
									int i=0;
									int j=0;
									
									for(i=0;i<secQuest.length;i++)
									{
										if(HLCUserRegVO.getSecretQuestion()!=null && HLCUserRegVO.getSecretQuestion().trim().length()!=0)
										{											
											if(HLCUserRegVO.getSecretQuestion().equalsIgnoreCase(secQuest[i]))
											{
												System.out.print("secQuest[i]) match :"+secQuest[i]);
											%>
                                            <option selected="selected" value="<%=secQuest[i]%>"><%=secQuest[i]%></option>
                                            <%}
										  else{
										  
										  if(j==0)
											{
										  %>
                                            <option value="">Select One</option>
                                            <%}%>
                                            <option value="<%=secQuest[i]%>"><%=secQuest[i]%></option>
                                            <%}}
										  else
										  {
										  	if(j==0)
											{
										  %>
                                            <option selected="selected" value="">Select One</option>
                                            <%}%>
                                            <option value="<%=secQuest[i]%>"><%=secQuest[i]%></option>
                                            <%										  
										  }
										  j++;
										  }%>
                                          </select>
										  
                                  
                                   
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
							 
                           
						   <%
									  		String secAns="";
											
											if(HLCUserRegVO.getSecretAnswer()!=null)
											{
												secAns=HLCUserRegVO.getSecretAnswer();
											}
									  %>

						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">Your Answer To This Question <span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							   <input type="text"name="ans" value="<%=secAns%>" class="form-control" size="30" />
							
							</div>
							
						</div>
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">Address Types</label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							 
							 <input type="checkbox" name="priAdd_cbx" id="priAdd_cbx" value="Primary" size="10" checked="checked" onClick="defaultPriAdd();"/>
                             Primary Address
							 <%
							 if(HLCUserRegVO.getSecondaryContactTypeName()!=null)
                              {
                                if(HLCUserRegVO.getSecondaryContactTypeName().equalsIgnoreCase("Secondary"))
                                                                                                                                                                                                                                                               
                              {%>
                                                <input type="checkbox" checked="checked" name="secAdd_cbx" value="Secondary" id="secAdd_cbx" size="10" onClick="return hide_switchDiv();"/>
                                                Secondary Address
                                              <input type="hidden" name="cttyp" value="Secondary" />
                                              <%}}else{%>
                                             
                                                <input type="checkbox" name="secAdd_cbx" value="Secondary" id="secAdd_cbx" size="10" onClick="return hide_switchDiv();"/>
                                                Secondary Address
                                              <% }%>
							</div> 
							
						</div>
							
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div id="pAdd">
						
						<div class="col-lg-12 col-md-12 col-sm-12">
								<div class="col-lg-3 col-md-2 col-sm-3">
								</div>
								<div class="col-lg-9 col-md-9 col-sm-9" id="commonBG">
								<label class="name form-div-6">
									<font color="#018dce">Primary Address</font>
								</label>
							   </div>
							 </div>
							 
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">Address 1<span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							   <input type="text" value="<%=HLCUserRegVO.getPrimaryAddress1()%>" name="padd_txt" id="padd_txt" class="form-control" />
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
							
							
							 <%

			String add2="";
			
			if(HLCUserRegVO.getPrimaryAddress2()!=null && HLCUserRegVO.getPrimaryAddress2().trim().length()!=0)
			{
				add2=HLCUserRegVO.getPrimaryAddress2();
			}
			else
			{
				add2="";
			}
			
			%>
					
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">Address 2</label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							   <input type="text" value="<%=add2%>" name="padd_txt2" id="padd_txt2" class="form-control"  />
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">City <span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							  <input type="pcity_txt" name="pcity_txt" value="<%=HLCUserRegVO.getPrimaryCity()%>" id="pcity_txt" class="form-control" />
							
							</div>
							
						</div>
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">State <span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							 <select name="pstate_sel" id="pstate_sel" class="form-control"> </select>
							
							</div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name" >Zipcode <span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  
							 <input type="text" value="<%=HLCUserRegVO.getPrmaryZip()%>" name="pzip_txt" id="pzip_txt" class="form-control" />
							
							</div>
							
						</div>
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">Country <span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
						
							 <%System.out.print(HLCUserRegVO.getPrimaryCountry());
																   System.out.print(HLCUserRegVO.getPrimaryState());
																    System.out.print(HLCUserRegVO.getSecondaryCountry());
																   System.out.print(HLCUserRegVO.getSecondaryState());
																	%>
                                                    <select name="pcountry_sel" id="pcountry_sel" class="form-control" onChange="FillState(document.frmMembRegi.pcountry_sel, document.frmMembRegi.pstate_sel,'');" >
                                                    </select>
                                                   
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">Phone <span class="asterisk">*</span></label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							 <input type="text" name="pphone_txt" value="<%=HLCUserRegVO.getPrimaryPhoneNo()%>" class="form-control"  />
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1">
								&nbsp;(e.g. xxx yyy zzzz)
							</div> 
							
						</div>
							
							
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
							
							<%

			String pmobil="";
			
			if(HLCUserRegVO.getPromaryMobileNo()!=null && HLCUserRegVO.getPromaryMobileNo().trim().length()!=0)
			{
				pmobil=HLCUserRegVO.getPromaryMobileNo();
			}
			else
			{
				pmobil="";
			}

			String pfax="";
			
			if(HLCUserRegVO.getPrimaryFaxNo()!=null && HLCUserRegVO.getPrimaryFaxNo().trim().length()!=0)
			{
				pfax=HLCUserRegVO.getPrimaryFaxNo();
			}
			else
			{
				pfax="";
			}
			
			%>
			
					<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">Mobile </label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
					
							  <input type="text" name="pmob_txt" value="<%=pmobil%>" class="form-control" />
							  
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">Fax </label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
						
							   <input type="text" name="pfax_txt" value="<%=pfax%>" class="form-control"  />
							</div> 
							
						</div>
							
				</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div id="sAdd">
						
						<div class="col-lg-12 col-md-12 col-sm-12">
								<div class="col-lg-3 col-md-3 col-sm-3">
								</div>
								<div class="col-lg-9 col-md-9 col-sm-9" id="commonBG">
								<label class="name form-div-6">
									<font color="#018dce">Secondary Address</font>
								</label>
							   </div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">Address 1 <span class="asterisk">*</span> </label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
						
							
							  <%
										 String sec_add1="";
                                                                                       if(HLCUserRegVO.getSecondaryAddress1()!=null)
                                                                                       {
	                                                                                       	sec_add1=HLCUserRegVO.getSecondaryAddress1();
                                                                                        } %>
                                                   
                              <input type="text" name="sadd_txt" id="sadd_txt" class="form-control" value="<%=sec_add1%>"/>
                              
							 </div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">Address 2 </label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							 <%
                                                                                    																						String sadd2="";
																						
																						if(HLCUserRegVO.getSecondaryAddress2()!=null)
																						{
																							sadd2=HLCUserRegVO.getSecondaryAddress2();
																						}
																						
																						%>
                                                    
								<input type="text" value="<%=sadd2%>" name="sadd_txt1" id="sadd_txt1" class="form-control"/>
							 </div>
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">City <span class="asterisk">*</span> </label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  <%
												 String sec_city="";
                                                                                       if(HLCUserRegVO.getSecondaryCity()!=null)
                                                                                       {
																					   sec_city=HLCUserRegVO.getSecondaryCity();
																					   }
                                                                                      %>
                                                   
                             <input type="text" value="<%=sec_city%>" name="scity_txt" id="scity_txt" class="form-control"/>
                            
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name">State <span class="asterisk">*</span> </label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							    <select name="sstate_txt" id="sState_sel" class="form-control"> </select>
                                                   
                             <!--<input type="text" name="sstate_txt" value="<%=HLCUserRegVO.getSecondaryState()%>" class="textboxOne" size="20" />-->
                            
							</div> 
							
						</div>
							
							
						<div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
							
							
							<%
											String sec_zip="";
										
											if(HLCUserRegVO.getSecondaryZip()!=null)
											{
												sec_zip=HLCUserRegVO.getSecondaryZip();
											}
										%>
										
										
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name"> Zip <span class="asterisk">*</span> </label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  <input type="text" value="<%=sec_zip%>" name="szip_txt" id="szip_txt" class="form-control"/>
                           
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name"> Country <span class="asterisk">*</span> </label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <select name="scountry_txt" id="sCountry_sel" class="form-control" onChange="FillState(document.frmMembRegi.scountry_txt, document.frmMembRegi.sstate_txt,'');" >
                            </select>
                            <!--<input type="text" name="scountry_txt" value="<%=HLCUserRegVO.getSecondaryCountry()%>" class="textboxOne" size="20" />-->
                            
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
							
							
							 <%
String ph="";
if(HLCUserRegVO.getSecondaryPhoneNo()!=null)
{
	ph=HLCUserRegVO.getSecondaryPhoneNo();
}

%>

						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name"> Phone <span class="asterisk">*</span> </label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
								<input type="text" name="sphone_txt" value="<%=ph%>" class="form-control"/>
                            </div>
							<div class="col-lg-1 col-md-1 col-sm-1">
								(e.g. xxx yyy zzzz)
							</div> 
							
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
							
						
							 <%

			String smob2="";
			
			if(HLCUserRegVO.getSecondaryMobileNo()!=null)
			{
				smob2=HLCUserRegVO.getSecondaryMobileNo();
			}
			
			String sfax2="";
			
			if(HLCUserRegVO.getSecondaryFaxNo()!=null)
			{
				sfax2=HLCUserRegVO.getSecondaryFaxNo();
			}
			
			
			%>
							
							
							
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name"> Mobile </label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							 <input type="text" name="smob_txt" value="<%=smob2%>" class="form-control"/>
							</div> 
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name"> Fax </label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							  <input type="text" name="sfax_txt" value="<%=sfax2%>" class="form-control"/>
							</div> 
							<div class="col-lg-2 col-md-2 col-sm-2">
							</div>
						</div>
					</div>
						
						
						 <div class="col-lg-12 col-md-12 col-sm-12">	
							&nbsp;
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-sm-12">
						
							<div class="col-lg-2 col-md-2 col-sm-2 col-sm-offset-3">
								<label class="name"> Preferred Communication Address </label>
									
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3">
							
							  <select name="comAdd_sel" id="comAdd_sel" class="form-control" onChange="return hideTwo_switchDiv();">
                                              <%
                                                                                       if(HLCUserRegVO.getPreferedCommunication()!=null)
                                                                                       {
                                                                                       if(HLCUserRegVO.getPreferedCommunication().equalsIgnoreCase("Secondary"))
                                                                                        {    System.out.println("HLCUserRegVO.getPreferedCommunication() :"+HLCUserRegVO.getPreferedCommunication());                                                                                                                                                                   
                                                                                     %>
                                              <option value="Primary">Primary Address</option>
                                              <option selected="selected" value="Secondary">Secondary Address</option>
                                              <%}else if(HLCUserRegVO.getPreferedCommunication().equalsIgnoreCase("Primary")){
										System.out.println("HLCUserRegVO.getPreferedCommunication() :"+HLCUserRegVO.getPreferedCommunication()); 
										%>
                                              <option selected="selected" value="Primary">Primary Address</option>
                                              <option value="Secondary">Secondary Address</option>
                                              <%}}%>
                                        </select>
							</div> 
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							&nbsp;
						</div>
						
							<div class="col-lg-12 col-md-12 col-sm-12">
								<div class="col-lg-2 col-md-2 col-sm-2">
								</div>
								<div class="col-lg-10 col-md-10 col-sm-10">
								<label class="name form-div-6">
									<font color="#018dce">Contact Options</font>
								</label>
							   </div>
							 </div>
							 
							  <div class="col-lg-12 col-md-12 col-sm-12">
								<div class="col-lg-2 col-md-2 col-sm-2">
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8">
								
									We provide Mail/E-mail information to other affiliates, organizations and vendors on a limited basis. You may choose to not receive these mailings by selecting the appropriate options below:
								
							   </div>
							 </div>
							 
							  <div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
							  </div>
						
						  <div class="col-lg-12 col-md-12 col-sm-12">
								<div class="col-lg-2 col-md-2 col-sm-2">
								</div>
								<div class="col-lg-10 col-md-10 col-sm-10">
				  				 <% 
											System.out.println("HLCUserRegVO.isNonUseaEmailStatus() :"+HLCUserRegVO.isNonUseaEmailStatus());
											
											if(HLCUserRegVO.isNonUseaEmailStatus()){%>
                                              <input type="checkbox" name="nonUseaEmail"  id="nonUseaEmail_id" value="true" checked="checked" />
                                            Do not release my email address for specific use. <br />
                                            <%}else {%>
                                            <input type="checkbox" name="nonUseaEmail"  id="nonUseaEmail_id" value="true" />
                                            Do not release my email address for specific use. <br />
                                            <%}%>
								
							   </div>
							 </div>
							 
							  <div class="col-lg-12 col-md-12 col-sm-12">	
								&nbsp;
								</div>
						
							<div class="col-lg-12 col-md-12 col-sm-12">
								<div class="col-lg-2 col-md-2 col-sm-2">
								</div>
								<div class="col-lg-10 col-md-10 col-sm-10">
				  				 <%System.out.println("nonUseaMail:testing:"+HLCUserRegVO.isNonUseaMailingStatus()); 
											  if(HLCUserRegVO.isNonUseaMailingStatus()){
											%>
                                            <input type="checkbox" name="nonUseaMail"  id="nonUseaAdd_id" value="true" checked="checked" />
                                            Do not release my mailing address for specific use.<br/>
                                            <%} else {%>
                                            <input type="checkbox" name="nonUseaMail"  id="nonUseaAdd_id" value="true" />
                                            Do not release my mailing address for specific use.<br/>
                                            <%}%>
							   </div>
							 </div>
							 
							  <div class="col-lg-12 col-md-12 col-sm-12">	
									&nbsp;
								</div>
						
						</div>
						
					<div class="row">
						
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="col-lg-6 col-md-6 col-sm-6">
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
							 <button type="submit" class="button-add" name="submit" value="Update">Update</button>
							
							</div>
						</div>
							
							 <%
                           
                        String pctry="";
			
			if(HLCUserRegVO.getPrimaryCountry()!=null && HLCUserRegVO.getPrimaryCountry().trim().length()!=0)
			{
				pctry=HLCUserRegVO.getPrimaryCountry();
			}
			else
			{
				pctry="USA";
			}
                        
                        String pstat="";
			
			if(HLCUserRegVO.getPrimaryState()!=null && HLCUserRegVO.getPrimaryState().trim().length()!=0)
			{
				pstat=HLCUserRegVO.getPrimaryState();
			}
			else
			{
				pstat="";
			}
			
			String sctry="";
			
			if(HLCUserRegVO.getSecondaryCountry()!=null && HLCUserRegVO.getSecondaryCountry().trim().length()!=0)
			{
				sctry=HLCUserRegVO.getSecondaryCountry();
			}
			else
			{
				sctry="USA";
			}
                        
                        String sstat="";
			
			if(HLCUserRegVO.getSecondaryState()!=null && HLCUserRegVO.getSecondaryState().trim().length()!=0)
			{
				sstat=HLCUserRegVO.getSecondaryState();
			}
			else
			{
				sstat="";
			}

			%>
				        
                      </div>    
                    </form>
               
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
  <script language="javascript">

	FillCountry(document.frmMembRegi.pcountry_sel, document.frmMembRegi.pstate_sel, '<%=pctry%>' );
	FillState(document.frmMembRegi.pcountry_sel, document.frmMembRegi.pstate_sel, '<%=pstat%>');

	FillCountry(document.frmMembRegi.scountry_txt, document.frmMembRegi.sstate_txt, '<%=sctry%>' );
	FillState(document.frmMembRegi.scountry_txt, document.frmMembRegi.sstate_txt, '<%=sstat%>');

</script>
  
  
  
 </html>

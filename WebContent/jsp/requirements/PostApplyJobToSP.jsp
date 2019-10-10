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
    <%@ page import = "javax.sql.*" %>
<%@ page import = "java.util.*" %>

<html lang="en">
<head>
	  
		
		<%
		String txtfirstname  = (String)request.getAttribute("txtfirstname");
		String txtlastname = (String)request.getAttribute("txtlastname");
		String txtemailaddress = (String)request.getAttribute("txtemailaddress");
		String txtcontactnumber = (String)request.getAttribute("txtcontactnumber");
		String txtcurrentAddress1 = (String)request.getAttribute("txtcurrentAddress1");
		String drpvisaapproval = (String)request.getAttribute("drpvisaapproval");
		String drpvisatype = (String)request.getAttribute("drpvisatype");
		String drpi797available = (String)request.getAttribute("drpi797available");
		String drpI97available = (String)request.getAttribute("drpI97available");
		String txtrate = (String)request.getAttribute("txtrate");
		
		String RID = (String)request.getAttribute("RID");
		
		String email = (String)request.getAttribute("recEmail");
		
		System.out.println("RID--------------->"+RID);
		System.out.println("recEmail--------------->"+email);
		String txtmiddlename = null;
		String txtdateofbirth = null;
		String txttotalexperience = null;
		String txtexperienceinUSA = null;
		String drprelocation = null;
		String txtavailability = null;
		String txtpreferredlocation = null;
		String drpbywhom = null;
		String txtskills = null;
		String txtbesttimefortelephonicinterview = null;
		String drltime = null;
		String drpwillinginperson = null;
		String txtempname = null;
		String txtempmailID = null;
		String txtempcontactnumber = null;
		String txtcontactperson = null;
		
		txtmiddlename = (String)request.getAttribute("txtmiddlename");
		txtdateofbirth = (String)request.getAttribute("txtdateofbirth");
		txttotalexperience = (String)request.getAttribute("txttotalexperience");
		txtexperienceinUSA = (String)request.getAttribute("txtexperienceinUSA");
		drprelocation = (String)request.getAttribute("drprelocation");
		txtavailability = (String)request.getAttribute("txtavailability");
		txtpreferredlocation = (String)request.getAttribute("txtpreferredlocation");
		drpbywhom = (String)request.getAttribute("drpbywhom");
		txtskills = (String)request.getAttribute("txtskills");
		txtbesttimefortelephonicinterview = (String)request.getAttribute("txtbesttimefortelephonicinterview");
		drltime = (String)request.getAttribute("drltime");
		drpwillinginperson = (String)request.getAttribute("drpwillinginperson");
		txtempname = (String)request.getAttribute("txtempname");
		txtempmailID = (String)request.getAttribute("txtempmailID");
		txtempcontactnumber = (String)request.getAttribute("txtempcontactnumber");
		txtcontactperson = (String)request.getAttribute("txtcontactperson");
		%>
   <title>
	
	dBConsult - UploadResume

</title>
        
        
        
        <style type="text/css">//
            <![CDATA[
        footer {
	padding: 65px 0;
	overflow: hidden;
	text-align: center;
}
footer em {
	font-style: normal;
}
footer p {
	font: 12px/18px 'Archivo Narrow';
	color: #bbb;
	margin-bottom: 17px;
	text-transform: uppercase;
}
.title {
	font: bold 48px/48px 'Archivo Narrow';
	margin-bottom: 3px;
	color: #ffffff;
	text-transform: none;
}
footer .privacy {
	font: 12px 'Archivo Narrow';
	color: #ffffff;
	margin-bottom: 13px;
	text-transform: uppercase;
}
footer .privacy a {
	color: #ffffff;
	-webkit-transition: all 0.25s;
	-o-transition: all 0.25s;
	transition: all 0.25s;
}
footer .privacy a:hover {
	color: #f1373a;
}

 #suiteLinksBox {
    display:none;
} 

#Ribbon.Read-title {display:none;}

#Ribbon.WikiPageTab-title{display:none;}
#ms-designer-ribbon {

display: none;

}

#suiteBarLeft{
background-color: red;
}
     
        //]]>
        </style>
       
    
</head>
<body style="overflow:inherit!important" onhashchange="if (typeof(_spBodyOnHashChange) != 'undefined') _spBodyOnHashChange();">
<!--header-->
<div id="header">
<!-- HEADER STARTS HERE -->
<%@ include file = "../../include/menschForceHeader.jsp" %>
  <!-- HEADER ENDS HERE -->
</div>

<script src="js/jquery.validate.js"></script>
<script src="js/additional-methods.js"></script>
<script src="js/fieldvalidation.js"></script>

<!--content-->
<div class="content"> 
   
     <div class="thumb-box2">
        <div class="container">

            <div class="row">
			
                <div class="col-md-12 col-sm-12 col-xs-12 wow fadeInUp">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                           
                            <div class="caption" >
                              
                               <form method="post" name="frmuploadresume" id="frmuploadresume" action="getCandidateResume.html" id="aspnetForm" enctype="multipart/form-data">
      
            <input type="hidden" name="txtemailaddress" id="_wpcmWpid" value="<%=txtemailaddress%>" />
            <input type="hidden" name="RID" id="wpcmVal" value="<%=RID%>" />
            <input type="hidden" name="txtfirstname" id="txtfirstname" value="<%=txtfirstname%>"/>
            <input type="hidden" name="txtcontactnumber" id="txtcontactnumber" value="<%=txtcontactnumber%>"/>
            <input type="hidden" name="txtrate" id="txtrate" value="<%=txtrate%>"/>
            <input type="hidden" name="txttotalexperience" id="txttotalexperience" value="<%=txttotalexperience%>"/>
            <input type="hidden" name="txtskills" id="txtskills" value="<%=txtskills%>"/>
            <input type="hidden" name="txtempmailID" id="txtempmailID" value="<%=txtempmailID%>"/>
            
         <input type="hidden" name="email" id="email" value="<%=email%>"/>
       
      
        <div id="">
            <div id="">
               
                <div id="s4-workspace">
                    <div id="s4-bodyContainer">
                        <!--header-->
                        
<!--content-->
<div class="content">
    <div class="container" style="width:1250px">
        <div data-name="ContentPlaceHolderMain">
            <span id="DeltaPlaceHolderMain">
                <span id="ctl00_PlaceHolderMain_wikiPageNameDisplay" style="display: none;">
		UploadResume
	</span>
                <span id="ctl00_PlaceHolderMain_wikiPageNameEdit" style="display:none">
                    <input name="ctl00$PlaceHolderMain$wikiPageNameEditTextBox" type="text" value="UploadResume" maxlength="123" id="ctl00_PlaceHolderMain_wikiPageNameEditTextBox" />
                </span>
                <div style="display:none">
                    <input type="submit" name="ctl00$PlaceHolderMain$btnWikiEdit" value="edit" onclick="javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions(&quot;ctl00$PlaceHolderMain$btnWikiEdit&quot;, &quot;&quot;, true, &quot;&quot;, &quot;&quot;, false, false))" id="ctl00_PlaceHolderMain_btnWikiEdit" />
                </div>
               <div style="display:none">
                    <input type="submit" name="ctl00$PlaceHolderMain$btnWikiSave" value="edit" onclick="javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions(&quot;ctl00$PlaceHolderMain$btnWikiSave&quot;, &quot;&quot;, true, &quot;&quot;, &quot;&quot;, false, false))" id="ctl00_PlaceHolderMain_btnWikiSave" />
                </div>
               <div style="display:none">
                    <input type="submit" name="ctl00$PlaceHolderMain$btnWikiRevert" value="Revert" onclick="javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions(&quot;ctl00$PlaceHolderMain$btnWikiRevert&quot;, &quot;&quot;, true, &quot;&quot;, &quot;&quot;, false, false))" id="ctl00_PlaceHolderMain_btnWikiRevert" />
                </div>
                <div id="ctl00_PlaceHolderMain_WikiField">
                    <div class="ms-wikicontent ms-rtestate-field" style="padding-right: 10px;">
                        <div class="ExternalClassCD829296F630434F91463833311C7FA2">
                            <table id="layoutsTable" style="width&#58;100%;">
                                <tbody>
                                    <tr style="vertical-align&#58;top;">
                                        <td style="width&#58;100%;">
                                            <div class="ms-rte-layoutszone-outer" style="width&#58;100%;">
                                                <div class="ms-rte-layoutszone-inner" role="textbox" aria-haspopup="true" aria-autocomplete="both" aria-multiline="true">
                                                    <div class="ms-rtestate-read ms-rte-wpbox" unselectable="on">
                                                        <div class="ms-rtestate-notify  ms-rtestate-read 8c61ba77-3b64-4668-b517-fbf9cca590da" id="div_8c61ba77-3b64-4668-b517-fbf9cca590da" unselectable="on">
                                                            <div id="MSOZoneCell_WebPartctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da" class="s4-wpcell-plain ms-webpartzone-cell ms-webpart-cell-vertical ms-fullWidth ">
                                                                <div class="ms-webpart-chrome ms-webpart-chrome-vertical ms-webpart-chrome-fullWidth ">
                                                                    <div WebPartID="18848605-3615-4fff-b186-d780843c5d60" WebPartID2="8c61ba77-3b64-4668-b517-fbf9cca590da" HasPers="false" id="WebPartctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da" width="100%" class="ms-WPBody noindex " allowRemove="false" allowDelete="false" allowExport="false" style="" >
                                                                        <div id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da">

                                                                          
<style type="text/css">
#frmuploadresume label.error{
	color:red;
}
.news-box h1 {
    margin: 30px 0;
    color: #3d84e6;
}
		.top-margin {
    margin-top: 20px;
    color:teal;
}
		.form-group-sm.form-validation {
        position: absolute;
    top: 20px;
    right: 6px;
}

        footer {
    padding: 65px 0;
    overflow: hidden;
    text-align: center;
    background: #2d2d2b url("../../img/pattern1.png") repeat;
}
        .ms-cui-topBar2 {
    border-bottom: 1px solid rgba( 239,239,239,0.78 );
}
     body {
    background: #2d2d2b url("../../img/pattern1.png") repeat;
    font: 13px 'Ubuntu', "Helvetica Neue", Helvetica, Arial, sans-serif;
    line-height: 1.428571429;
    color: #5f5e5e;
}
     .move{
    padding-top: 29px;
    margin-left: 50px;
	}
     .dis{ display:inline-block;}

	   .btn1 {display: inline-block;
    /* float: right; */
    position: absolute;
   top: 25px;
    right: -60px;}
       .shadow{
           box-shadow:0px 10px 6px -6px #777;

       }

       .container_9{ width:1000px; margin:0 auto;}
</style>
<div id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_header">
    <h4>
        <span  class="label label-warning shadow">Register and Apply to this Job</span>
    </h4>
    <section class="news-box">
        <div class="container">
            <img id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_Image1" src="https://www.digiblitzonline.com/_layouts/15/images/UploadResume/SubmitIcon.png" style="height:60px;width:76px;" />
            <span id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_lblopeningid" class="top-margin" style="font-weight:bold;">Submit Candidate for the Opening </span>&nbsp;
            <span  class="label label-success shadow" style="font-size:small">    Requirement ID :
            
                <span id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_RID2" style="font-weight: bold;color:white;"><%=RID%></span>
            </span>
            <p> The Information related to submitting a candidate is separated into section shown below. All are Required fields have a asterisk
and need values to submit a candidate. To Upload a resume click Browse, select the file, and click the Upload button, when you are finished
adding information you can submit the candidate </p>
        </div>
    </section>
</div>
<!-- div id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_tblhotlist">
    <h5 class="dis" style="text-transform: capitalize;">Select Candidate from the Hot List Sheet</h5>
    <div class="btn-group">
        <input type="submit" name="ctl00$ctl38$g_8c61ba77_3b64_4668_b517_fbf9cca590da$ctl00$ctl00" value="::" onclick="javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions(&quot;ctl00$ctl38$g_8c61ba77_3b64_4668_b517_fbf9cca590da$ctl00$ctl00&quot;, &quot;&quot;, true, &quot;&quot;, &quot;&quot;, false, false))" class="btn btn-warning" />
    </div>
</div-->
<div id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_tbluploadresume">
    <section class="news-box">
        <span class="label label-primary col-xs-12 well well-sm shadow"  style="text-align:left;Background:#447DC4;font-size:14px;color:#FFFFFF" >(A) Consultant Details</span>
        <div class="container_9">
            <div class="row">
                <div class="col-md-4">
                    <div class="form-group-sm">
                        <label >Upload Resume :</label>
                        <span style="color:red">*</span>
                        <span id="fileuploaderror" style="color:red"></span>
                        <input type="file" name="ctl00$ctl38$g_8c61ba77_3b64_4668_b517_fbf9cca590da$ctl00$FileUpload1" id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_FileUpload1" class="form-control" />
                    </div>
                </div>
               
                           
                        </div>
                    </div>
                    
<p class="text-center top-margin">
    <input type="submit" name="btnupload" value="Submit" id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_btnupload" class="btn btn-info"/>
</p>
</section>
</div>
</div>
</div>

</div>
</div>
</div>
</div>

</div>
</div>
</td>
</tr>
</tbody>
</table>

</div>
</div>
</div>

</div>
</span>
</div>
</div>
</div>


</form>

                                
                           </div>  
                        </div>
                    </div>
                </div>
           
               
            </div>

        </div>

     </div>
     
</div>

<div>
    
    
    <!-- FOOTER STARTS HERE -->
    
   <%@ include file = "../../include/FooterProcess.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
    </div>

</body>
</html>

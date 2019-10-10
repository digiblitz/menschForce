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
<%@ page import="com.hlcform.util.*" %>

<html lang="en">
<head>
	  
        
        
   <title>
	Create HotList Candidate
</title>

<style>
#frmCreateHotlistCandidate label.error{
	color:red;
}
</style>


</head>
<body>
<!--header-->
<div id="header">
    <!-- HEADER STARTS HERE -->
  <%@ include file = "../../include/menschForceHeader.jsp" %>
      <!-- HEADER ENDS HERE -->
    </div>

<script src="js/jquery.validate.js"></script>
<script src="js/additional-methods.js"></script>
<script src="js/fieldvalidation.js"></script>

<script src="js/bootstrap.min.js"></script>
<script src="js/tm-scripts.js"></script>
<script  src="js/ts_picker.js"></script>
<script src="js/calendar2.js" type="text/javascript"></script>
<script src="js/datetimepicker_css.js"></script>

<!--content-->
<div class="content"> 
     <div class="thumb-box2">
        <div class="container">
            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12 wow fadeInUp">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                            <div class="caption" >
                               <form method="post" name="frmCreateHotlistCandidate" id="frmCreateHotlistCandidate" action="doCreateHotListCandidate.html" id="aspnetForm" enctype="multipart/form-data">
       
        
        <div id="">
            <div id="">
               
                <div id="s4-workspace">
                    <div id="s4-bodyContainer">
                        <!--header-->
                        
<!--content-->
<div class="content">
    <!-- div class="container" style="width:1250px">
        <div data-name="ContentPlaceHolderMain">
            <span id="DeltaPlaceHolderMain">
                <span id="ctl00_PlaceHolderMain_wikiPageNameDisplay">
		UploadResume
	</span-->
                
                <div id="ctl00_PlaceHolderMain_WikiField">
                    <div class="ms-wikicontent ms-rtestate-field" style="padding-right: 10px">
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

<script type="text/javascript">
        $(function () {

            var date = new Date();
            var currentMonth = date.getMonth();
            var currentDate = date.getDate();
            var currentYear = date.getFullYear();


            $("#ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtdateofbirth").datepicker({
                dateFormat: 'mm/dd/yy',
                changeMonth: true,
                changeYear: true,
                yearRange: '1960:2016',
                maxDate: new Date(currentYear, currentMonth, currentDate)

            });

        });
    </script>
<script type="text/javascript">
     $(function () {


         var date = new Date();
         var currentMonth = date.getMonth();
         var currentDate = date.getDate();
         var currentYear = date.getFullYear();

         $("#ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtbesttimefortelephonicinterview").datepicker({
             dateFormat: 'mm/dd/yy',
             changeMonth: true,
             changeYear: true,
             yearRange: '2016:2030',
             minDate: new Date(currentYear, currentMonth, currentDate)
         });

     });
    </script>
<style type="text/css">
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



<div id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_tblhotlist">
    
																				<h2>Hot List Candidate</h2>
</div>

<div id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_tbluploadresume">
    <section class="news-box">
        <span class="label label-primary col-xs-12 well well-sm shadow"  style="text-align:left;Background:#447DC4;color:#FFFFFF;font-size:14px;" >(A) Consultant Details</span>
        <div class="container_9">
		
            <div class="row">

<div class="col-md-4">
    <div class="form-group-sm">
        <label>First Name :</label>
        <span style="color:red">*</span>
        <span id="nameerror" style="color:red"></span>
        <input name="txtfirstname" value="" type="text" id="txtfirstname" class="form-control"  />
    </div>
</div>
<div class="col-md-4">
    <div class="form-group-sm">
        <label>Middle Name :</label>
        <input name="txtmiddlename" value="" type="text" id="txtmiddlename" class="form-control"  />
    </div>
</div>
<div class="col-md-4">
    <div class="form-group-sm">
        <label>Last Name :</label>
        <span style="color:red">*</span>
        <span id="lastnameerror" style="color:red"></span>
        <input name="txtlastname" value="" type="text" id="txtlastname" class="form-control"  />
    </div>
</div>
<div class="col-md-4">
    <div class="form-group-sm">
        <label>Date of Birth :</label>
        <input name="txtdateofbirth" value="" type="text" id="txtdateofbirth" title="MM/DD/YYYY" class="form-control"  PlaceHolder="MM/DD/YYYY" style="width:140px" readonly />
		<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer;float:left;margin-top:-25px;margin-left:112px" border="0" onclick="javascript:NewCssCal('txtdateofbirth','MMddyyyy','dropdown',false,'24',false)"/>
    </div>
</div>
<!--div class="col-md-4">
    <div class="form-group-sm">
        <label>Marital Status</label>
		 <span style="color:red">*</span>
        <select name="canMaritalStatus" class="form-control">
				<option value="" >Select</option>
				<option value="Single">Single</option>
				<option value="Married">Married</option>
				
		</select>
    </div>
</div-->
<div class="col-md-4">
    <div class="form-group-sm">
        <label>Email Address :</label>
        <span style="color:red">*</span>
        <span id="emailerror" style="color:red"></span>
        <input name="txtemailaddress" value="" type="text" id="txtemailaddress" class="form-control"  />
    </div>
</div>
<div class="col-md-4">
    <div class="form-group-sm">
        <label>Total Experience :</label>
		 <span style="color:red">*</span>
        <input name="txttotalexperience" type="text" id="txttotalexperience" class="form-control" />
    </div>
</div>
<div class="col-md-4">
    <div class="form-group-sm">
        <label>Experience in USA :</label>
		<select name="txtexperienceinUSA" id="txtexperienceinUSA" class="form-control" onchange="test()">
            <option value="">select</option>
            <option value="YES">YES</option>
            <option value="NO">NO</option>
        </select>
    </div>
</div>
<div class="col-md-4">
    <div class="form-group-sm">
        <label>Contact Number :</label>
        <span style="color:red">*</span>
        <span id="contacterror" style="color:red"></span>
        <input name="txtcontactnumber" value="" type="text" id="txtcontactnumber" class="form-control"  onBlur="addDashes(this)" />
    </div>
</div>
<div class="col-md-4">
    <div class="form-group-sm">
        <label>Current Address :</label>
        <span style="color:red">*</span>
        <span id="locationerror" style="color:red"></span>
        <textarea name="txtcurrentAddress1" type="text" id="txtcurrentAddress1" class="form-control" rows="3" cols="5" /></textarea>
    </div>
</div>

<div class="col-md-4">
    <div class="form-group-sm">
        <label>Visa Approval :</label>
        <span style="color:red">*</span>
        <span id="visaaprovalerror" style="color:red"></span>
        <select name="drpvisaapproval" id="drpvisaapproval" class="form-control" onchange="test()">
            <option value="">select</option>
            <option value="YES">YES</option>
            <option value="NO">NO</option>
        </select>
    </div>
</div>
<div class="col-md-4">
    <div class="form-group-sm">
        <label id="visatype">Visa Type :
            <span id="visaspan" style="color:red;display:inline;">*</span>
            <span id="visatypeerror"  style="color:red;display:inline;"></span>
        </label>
        <select name="drpvisatype" id="drpvisatype" class="form-control" onchange="drpvisa()">
            <option value="">select</option>
            <option value="H1B">H1B</option>
            <option value="L1B">L1B</option>
            <option value="Green Card">Green Card</option>
            <option value="US Citizen">US Citizen</option>
            <option value="OPT EAD">OPT EAD</option>
        </select>
    </div>
</div>
<div class="col-md-4">
    <div class="form-group-sm">
        <label>Form I-797 available :</label>
        <span style="color:red">*</span>
        <span id="formI797error" style="color:red"></span>
        <select name="drpi797available" id="drpi797available" class="form-control" onchange="formI797()">
            <option value="">select</option>
            <option value="YES">YES</option>
            <option value="NO">NO</option>
        </select>
     <!--input type="hidden" name="drpi797available" id="drpi797availableHidden" value=""/-->
           
    </div>
</div>
<div class="col-md-4">
    <div class="form-group-sm">
        <label>Form I-94 available :</label>
        <span style="color:red">*</span>
        <span id="formI94error" style="color:red"></span>
        <select name="drpI97available" id="drpI97available" class="form-control" onchange="FormI94()">
            <option value="">select</option>
            <option value="YES">YES</option>
            <option value="NO">NO</option>
        </select>
      <!--input type="hidden" name="drpI97available" id="drpI97availableHidden" value=""/-->
             
    </div>
</div>
<div class="col-md-4">
    <div class="form-group-sm">
        <label>Willing to Relocate :</label>
        <select name="drprelocation" id="drprelocation" class="form-control" onchange="Relocate()">
            <option value="select">select</option>
            <option value="YES">YES</option>
            <option value="NO">NO</option>
        </select>
    </div>
</div>
<div class="col-md-4">
    <div class="form-group-sm">
        <label>Availability :</label>
        <select  name="txtavailability" id="txtavailability" class="form-control">
			<option value="">select</option>
            <option value="Part Time">Part Time</option>
            <option value="Full Time">Full Time</option>
			<option value="Hourly">Hourly</option>
		</select>
    </div>
</div>
<div class="col-md-4">
    <div class="form-group-sm">
        <label>Rate(In Dollar) :</label>
        
        <span id="rateerror" style="color:red"></span>
        <input name="txtrate" type="text"  id="txtrate" class="form-control" />
    </div>
</div>
<div class="col-md-4">
    <div class="form-group-sm">
        <label id="preferredlocation">Preferred Location :</label>
        <input name="txtpreferredlocation" type="text" id="txtpreferredlocation" class="form-control" />
    </div>
</div>
<div class="col-md-4" >
    <div class="form-group-sm">
        <label>HotList AVL :</label>
		<span style="color:red">*</span>
		<span id="hotlistAvlError" style="color:red"></span>
        <select name="hotlistAvl" id="hotlistAvl" class="form-control">
            <option value="">Select One</option>
            <option value="YES">YES</option>
            <option value="NO">NO</option>
        </select>
      
    </div>
</div>
<div class="col-md-4">
                                <div class = "form-group-sm">
                                    <label for = "name">Skills :</label>
									<span style="color:red">*</span>
                                    <textarea name="txtskills" style="resize:none" rows="5" cols="36" id="txtskills" >
</textarea>
                                </div>
</div>
	<div class="col-md-4">
        <div class="form-group-sm">
            <div>
                <label>Best Date and Time for Telephonic Interview :</label>
            </div>
        <div class="col-md-8" style="padding:0;">
			<input name="txtbesttimefortelephonicinterview" type="text" id="txtbesttimefortelephonicinterview" title="MM/DD/YYYY" class="form-control" PlaceHolder="MM/DD/YYYY" style="width:140px" readonly />
			<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer;float:left;margin-top:-25px;margin-left:112px" border="0" onclick="javascript:NewCssCal('txtbesttimefortelephonicinterview','MMddyyyy','dropdown',false,'24',false)"/>
		</div>
    <div class="col-md-4" style="padding:0 0 0 0px;">
        <select name="drltime" id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drltime" class="form-control" style="text-align:left">
            <option value="" selected>Select Time</option>
            <option value="00:30">00:30</option>
            <option value="01:00">01:00</option>
            <option value="01:30">01:30</option>
            <option value="02:00">02:00</option>
            <option value="02:30">02:30</option>
            <option value="03:00">03:00</option>
            <option value="03:30">03:30</option>
            <option value="04:00">04:00</option>
            <option value="04:30">04:30</option>
            <option value="05:00">05:00</option>
            <option value="05:30">05:30</option>
            <option value="06:00">06:00</option>
            <option value="06:30">06:30</option>
            <option value="07:00">07:00</option>
            <option value="07:30">07:30</option>
            <option value="08:00">08:00</option>
            <option value="08:30">08:30</option>
            <option value="09:00">09:00</option>
            <option value="09:30">09:30</option>
            <option value="10:00">10:00</option>
            <option value="10:30">10:30</option>
            <option value="11:00">11:00</option>
            <option value="11:30">11:30</option>
            <option value="12:00">12:00</option>
            <option value="12:30">12:30</option>
            <option value="13:00">13:00</option>
            <option value="13:30">13:30</option>
            <option value="14:00">14:00</option>
            <option value="14:30">14:30</option>
            <option value="15:00">15:00</option>
            <option value="15:30">15:30</option>
            <option value="16:00">16:00</option>
            <option value="16:30">16:30</option>
            <option value="17:00">17:00</option>
            <option value="17:30">17:30</option>
            <option value="18:00">18:00</option>
            <option value="18:30">18:30</option>
            <option value="19:00">19:00</option>
            <option value="19:30">19:30</option>
            <option value="20:00">20:00</option>
            <option value="20:30">20:30</option>
            <option value="21:00">21:00</option>
            <option value="21:30">21:30</option>
            <option value="22:00">22:00</option>
            <option value="22:30">22:30</option>
            <option value="23:00">23:00</option>
            <option value="23:30">23:30</option>
        </select>
    </div>
</div>
</div>
<div class="col-md-4">
    <div class="form-group-sm">
        <label>Willingness for an in-person interview at own expense:</label>
        <select name="drpwillinginperson" id="drpwillinginperson" class="form-control">
            <option value="YES">YES</option>
            <option value="NO">NO</option>
        </select>
    </div>
</div>
<div class="col-md-4">
    <div class="form-group-sm">
        <label >Upload Resume :</label>
        <span style="color:red">*</span>
        <span id="fileuploaderror" style="color:red"></span>
        <input type="file" name="candidateResume" id="candidateResume" class="form-control" />
    </div>
</div>
</div>
</div>
                    <div class="container_9">
                        <div class="row">
                            
</div>
</div>
<br/>
<span class="label label-primary col-xs-12 well well-sm shadow"  style="text-align:left;Background:#447DC4;color:#FFFFFF; width:100%;font-size:14px" >(B) Consultant's Employer Details</span>
<div class="container_9">
    <div class="row">
        <div class="col-md-4">
            <div class="form-group-sm">
                <label>Employer Name :</label>
                <input name="txtempname" type="text" id="txtempname" value="" class="form-control" />
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group-sm">
                <label>Employer Mail ID:</label>
                <span style="color:red">*</span>
                <input name="txtempmailID" type="text" id="txtempmailID" value="" class="form-control" />
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group-sm">
                <label>Employeer Contact Number :</label>
                <input name="txtempcontactnumber" type="text" id="txtempcontactnumber" value="" class="form-control" />
            </div>
        </div>
		<div class="col-md-4">
            <div class="form-group-sm">
                <label>Employeer Company :</label>
                <input name="txtempCompany" type="text" id="txtempCompany" value="" class="form-control" />
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group-sm">
                <label>Contact Person :</label>
                <input name="txtcontactperson" type="text" id="txtcontactperson" class="form-control" />
            </div>
        </div>
    </div>
</div>
<p class="text-center top-margin">
    <input type="button" name="btnupload" value="Cancel" id="btnupload" class="btn btn-info" onclick="history.back(-1)"/>
	<input type="submit" name="btnupload" value="Submit" id="btnupload" class="btn btn-info" />
</p>
</section>
</div>
</div>
</div>
<div class="ms-clear"></div>
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
<span id="layoutsData" style="display&#58;none;">false,false,1</span>
</div>
</div>
</div>


</span>
</div>
</div>
</div>
<!--footer-->

                    
</div>
</div>
<!--Remove the Ribbon-->
            
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

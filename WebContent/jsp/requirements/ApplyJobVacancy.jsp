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
	  
        
        <%
        
        String encode = (String)request.getAttribute("email");
        String RID = (String)request.getAttribute("RID");
        String applyStatus = null;
        applyStatus = (String)request.getAttribute("applyStatus");
        System.out.println("keshav "+encode);
        HLCContactDetails objContact = new HLCContactDetails();
	    HLCUserMaster objUserMaster = new HLCUserMaster();
	    objUserMaster = (HLCUserMaster)request.getAttribute("objUserMaster");
		objContact = (HLCContactDetails)request.getAttribute("objContact");
	    
        %>
   <title>
	dBConsult - UploadResume
</title>

<style>
#frmapplyjobvacancy label.error{
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
                               <form method="post" name="frmapplyjobvacancy" id="frmapplyjobvacancy" action="GetApplyJobVacancy.html?RID=<%=RID%>&User=<%=encode%>" id="aspnetForm" >
        						<input type="hidden" name="appliedUserId" id="appliedUserId" value="<%=objUserMaster.getUserId()%>" />
                                 
                                 
        
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
<!--script type="text/javascript">
     function hotvalidate() {
         var hotemail = document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txthotlistemailaddress').value;
         if (hotemail == "") {

              
             document.getElementById('hotemailerror').innerHTML = "Please enter the Email";
             document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txthotlistemailaddress').style.borderColor = "Red";

              return false;
          }
          else {
              document.getElementById('hotemailerror').innerHTML = "";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txthotlistemailaddress').style.borderColor = "";

           }
           if (!isemail(hotemail)) {

              

             document.getElementById('hotemailerror').innerHTML = "Please enter value Email";
             document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txthotlistemailaddress').style.borderColor = "Red";
              return false;

          }
          else {
              document.getElementById('emailerror').innerHTML = "";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txthotlistemailaddress').style.borderColor = "";

          }
          function isemail(E) {
              var E1 = /\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
              if (E.match(E1))
                  return true;
          }
         
          
      }
     </script>
<script type="text/javascript">

     function validate() {

         var fileupload = document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_FileUpload1').value;
         var Firstname = document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtcandidatename').value;

         var lastname = document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtlastname').value;

         var contact = document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtcontactnumber').value;
         var email = document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtemailaddress').value;

         var rate = document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtrate').value;

         var location = document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtcurrentlocation').value;
         var visa_approval = document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drpvisaapproval').value;
         var visa_type = document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drpvisatype').value;

         var formI797 = document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drpi797available').value;
         var formI94 = document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drpI97available').value;
         


         if (fileupload == "") {

             
             document.getElementById('fileuploaderror').innerHTML = "Please upload a resume";
             document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_FileUpload1').style.borderColor = "Red";
             return false;
         }
         else {
             document.getElementById('fileuploaderror').innerHTML = "";
             document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_FileUpload1').style.borderColor = "";
         }
         if (!(fileupload.indexOf("pdf") > 0 || fileupload.indexOf("doc") > 0 || fileupload.indexOf("zip") > 0 || fileupload.indexOf("docx") > 0)) {
              
              document.getElementById('fileuploaderror').innerHTML = "Please upload only in pdf,zip,doc,docx";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_FileUpload1').style.borderColor = "Red";
              return false;

          }
          else {
              document.getElementById('fileuploaderror').innerHTML = "";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_FileUpload1').style.borderColor = "";
          }


          if (Firstname == "") {


             

              document.getElementById('nameerror').innerHTML = "Please enter the First Name";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtcandidatename').style.borderColor = "Red";
              return false;
          }
          else {
              document.getElementById('nameerror').innerHTML = "";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtcandidatename').style.borderColor = "";
          }

          if (!isname(Firstname)) {

               

              document.getElementById('nameerror').innerHTML = "Please enter alphabet only";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtcandidatename').style.borderColor = "Red";
              return false;

          }
          else {
              document.getElementById('nameerror').innerHTML = "";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtcandidatename').style.borderColor = "";

         }
         function isname(E) {
             var E1 = /^[A-Za-z]+$/;
             if (E.match(E1))
                 return true;
         }

         if (lastname == "") {


               

              document.getElementById('lastnameerror').innerHTML = "Please enter the Last Name";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtlastname').style.borderColor = "Red";
               return false;
           }
           else {
               document.getElementById('lastnameerror').innerHTML = "";
               document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtlastname').style.borderColor = "";
           }
           if (!islastname(lastname)) {

               

               document.getElementById('lastnameerror').innerHTML = "Please enter alphabet only";
               document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtlastname').style.borderColor = "Red";
               return false;

           }
           else {
               document.getElementById('lastnameerror').innerHTML = "";
               document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtlastname').style.borderColor = "";

           }
           function islastname(E) {
               var E1 = /^[A-Za-z]+$/;
               if (E.match(E1))
                   return true;
           }


           if (email == "") {

              
               document.getElementById('emailerror').innerHTML = "Please enter the Email";
               document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtemailaddress').style.borderColor = "Red";

               return false;
           }
           else {
               document.getElementById('emailerror').innerHTML = "";
               document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtemailaddress').style.borderColor = "";

         }
         if (!isemail(email)) {

              

              document.getElementById('emailerror').innerHTML = "Please enter value Email";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtemailaddress').style.borderColor = "Red";
              return false;

          }
          else {
              document.getElementById('emailerror').innerHTML = "";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtemailaddress').style.borderColor = "";

          }
          function isemail(E) {
              var E1 = /\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
              if (E.match(E1))
                  return true;
          }


          if (contact == "") {

            
              document.getElementById('contacterror').innerHTML = "Please enter the ContactNumber";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtcontactnumber').style.borderColor = "Red";
              return false;
          }
          else {
              document.getElementById('contacterror').innerHTML = "";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtcontactnumber').style.borderColor = "";

          }
         /* if (!isNumeric(contact)) {
              alert("Please enter only number");
              return false;

          }*/



          if (!isNumber(contact)) {
              
              document.getElementById('contacterror').innerHTML = "Please enter only 10 digit Number";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtcontactnumber').style.borderColor = "Red";
              return false;
          }
          else {
              document.getElementById('contacterror').innerHTML = "";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtcontactnumber').style.borderColor = "";

          }
          function isNumber(N) {
              var N1 = /^(\([0-9]{3}\)|[0-9]{3}-)[0-9]{3}-[0-9]{4}$/;
              if (N.match(N1))
                  return true;
          }
         /* function isNumeric(G) {

              var G1 = /^[0-9]/;
              if (G.match(G1))
                  return true;

          }*/

          if (location == "") {

             
              document.getElementById('locationerror').innerHTML = "Please enter the location";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtcurrentlocation').style.borderColor = "Red";

              return false;
          }
          else {
              document.getElementById('locationerror').innerHTML = "";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtcurrentlocation').style.borderColor = "";

          }
          if (!islocation(location)) {

               

              document.getElementById('locationerror').innerHTML = "Please enter alphabet only";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtcurrentlocation').style.borderColor = "Red";
              return false;

          }
          else {
              document.getElementById('locationerror').innerHTML = "";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtcurrentlocation').style.borderColor = "";

          }
          function islocation(E) {
              var E1 = /^[A-Za-z]+$/;
              if (E.match(E1))
                  return true;
          }



          if (rate == "") {

             
             document.getElementById('rateerror').innerHTML = "Please enter the Rate";
             document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtrate').style.borderColor = "Red";
              return false;
          }
          else {
              document.getElementById('rateerror').innerHTML = "";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtrate').style.borderColor = "";

          }

          if (!israte(rate)) {
             
              document.getElementById('rateerror').innerHTML = "Format like(EX:$98 or $19.2)";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtrate').style.borderColor = "Red";
              return false;
          }
          else {
              document.getElementById('rateerror').innerHTML = ""
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtrate').style.borderColor = "";

          }

          function israte(R) {
              var R1 = /^\$?[0-9]+\.?[0-9]*$/;
              //var R1 = /\$\d{1,3}/;
              if (R.match(R1))
                  return true;
          }



          if (visa_approval == "select") {


                
              document.getElementById('visaaprovalerror').innerHTML = "Please select the visa_approval";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drpvisaapproval').style.borderColor = "Red";
              return false;

          }
          else {
              document.getElementById('visaaprovalerror').innerHTML = "";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drpvisaapproval').style.borderColor = "";

          }
          if (visa_approval == "YES") {
              if (visa_type == "select") {
                
                  document.getElementById('visatypeerror').innerHTML = "please select the visa_type";
                  document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drpvisaapproval').style.borderColor = "Red";

                  return false;
              }
              else {
                  document.getElementById('visatypeerror').innerHTML = "";
                  document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drpvisaapproval').style.borderColor = "";
                  

              }

          }



          if (formI797 == "select") {


              
              document.getElementById('formI797error').innerHTML = "Please select the FormI-797";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drpi797available').style.borderColor = "Red";
              return false;

          }
          else {
              document.getElementById('formI797error').innerHTML = "";
              document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drpi797available').style.borderColor = "";

          }
         



         if (formI94 == "select") {


              
             document.getElementById('formI94error').innerHTML = "Please select the FormI-94";
             document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drpI97available').style.borderColor = "Red";
             return false;

         }
         else {
             document.getElementById('formI94error').innerHTML = "";
             document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drpI97available').style.borderColor = "";

          }
        




     }
     function drpvisa()
     {
     if (document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drpvisatype').value == "select")
         {
         document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drpvisatype').style.borderColor = "Red";
     }
     else
         document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drpvisatype').style.borderColor = "";
     }

     function test() {
         if (document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drpvisaapproval').value == "YES") {
             document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drpvisatype').style.display = "block";
             document.getElementById('visatype').style.display = 'block';
             document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drpvisatype').style.borderColor = "Red";
           

         }
         else {
             document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drpvisatype').style.borderColor = "";
             document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drpvisatype').style.display = "none";
             document.getElementById('visatype').style.display = 'none';
             document.getElementById('visaspan').style.display = 'none';
             document.getElementById('visatypeerror').style.display = 'none';
           
         }

     }

     function Relocate() {
         if (document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_drprelocation').value == "OPEN") {
             document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtpreferredlocation').style.display = "block";
             document.getElementById('preferredlocation').style.display = 'block';
         }
         else {
             document.getElementById('ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_txtpreferredlocation').style.display = "none";
             document.getElementById('preferredlocation').style.display = 'none';
         }
     }
    


     function addDashes(f) {
         f.value = f.value.replace(/\D/g, '');
         f.value = f.value.replace('-', '');

         f.value = f.value.slice(0, 3) + "-" + f.value.slice(3, 6) + "-" + f.value.slice(6, 15);
     }




</script>
<!--link rel="stylesheet" href="../../../../_layouts/15/menschforcedatetimepicker/css/jquery-ui.css" type="text/css"/>
<link rel="stylesheet" href="../../../../_layouts/15/menschforcedatetimepicker/css/ui.theme.css" type="text/css"  />
<link rel="stylesheet" href="../../../../_layouts/15/menschforcedatetimepicker/css/bootstrap.min.css" type="text/css"  />
<script src="../../../../_layouts/15/menschforcedatetimepicker/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../../../../_layouts/15/menschforcedatetimepicker/js/jquery.min.js" type="text/javascript"></script>
<script src="../../../../_layouts/15/menschforcedatetimepicker/js/jquery-ui.min.js" type="text/javascript"></script>-->
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
<div id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_tblhotlist">
    <!--h5 class="dis" style="text-transform: capitalize;">Select Candidate from the Hot List Sheet</h5-->
    <!--div class="btn-group">
        <input type="submit" name="ctl00$ctl38$g_8c61ba77_3b64_4668_b517_fbf9cca590da$ctl00$ctl00" value="::" onclick="javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions(&quot;ctl00$ctl38$g_8c61ba77_3b64_4668_b517_fbf9cca590da$ctl00$ctl00&quot;, &quot;&quot;, true, &quot;&quot;, &quot;&quot;, false, false))" class="btn btn-warning" />
    </div-->
																				<h2>Basic Details</h2>
</div>

<div id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_tbluploadresume">
    <section class="news-box">
        <span class="label label-primary col-xs-12 well well-sm shadow"  style="text-align:left;Background:#447DC4;color:#FFFFFF;font-size:14px;" >(A) Consultant Details</span>
        <div class="container_9">
		
            <div class="row">
<!--div class="col-md-4">
    <div class="form-group-sm">
        <label >Upload Resume :</label>
        <span style="color:red">*</span>
        <span id="fileuploaderror" style="color:red"></span>
        <input type="file" name="ctl00$ctl38$g_8c61ba77_3b64_4668_b517_fbf9cca590da$ctl00$FileUpload1" id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_FileUpload1" class="form-control" />
    </div>
</div-->
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
       
     <input type="hidden" name="email" id="encode" value="<%=encode%>" />  
      
        
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
        <label>Contact Number :</label>
        <span style="color:red">*</span>
        <span id="contacterror" style="color:red"></span>
        <input name="txtcontactnumber" value="" type="text" id="txtcontactnumber" class="form-control"  onBlur="addDashes(this)" />
   
   
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
        <label>Current Address1 :</label>
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
		<input type="hidden" name="drpi797available" id="drpi797availableHidden" value=""/>
        <!--
              
                 <input type="file" name="ctl00$ctl38$g_8c61ba77_3b64_4668_b517_fbf9cca590da$ctl00$FileUpload2" id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_FileUpload2" /><span id="formI797fileuploaderror" style="color:red"></span>
     -->
         
           
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
		<input type="hidden" name="drpI97available" id="drpI97availableHidden" value=""/>
        <!--
             
             
                <input type="file" name="ctl00$ctl38$g_8c61ba77_3b64_4668_b517_fbf9cca590da$ctl00$FileUpload3" id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_FileUpload3" /><span id="formI94fileuploaderror"></span> 
     -->
            
             
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
        <label id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_labpostedby">Posted By :</label>
        <select name="drpbywhom" id="drpbywhom" class="form-control">
            <option value="select">Select One</option>
            <option value="Supplier">Supplier</option>
            <option value="Consultant">Consultant</option>
        </select>
        <!--
             
                 Created by: 
             
            
                <span id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker" editorOldValue="" RemoveText="Remove" value="" NoMatchesText="&lt;No Matching Names>" allowEmpty="1" MoreItemsText="More Names..." preferContentEditableDiv="true" showDataValidationErrorBorder="false" allowTypeIn="true" inValidate="false" EEAfterCallbackClientScript="" ShowEntityDisplayTextInTextBox="0" style="display:inline-block;width:250px;"><input name="ctl00$ctl38$g_8c61ba77_3b64_4668_b517_fbf9cca590da$ctl00$spPeoplePicker$hiddenSpanData" type="hidden" id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker_hiddenSpanData" /><input name="ctl00$ctl38$g_8c61ba77_3b64_4668_b517_fbf9cca590da$ctl00$spPeoplePicker$OriginalEntities" type="hidden" id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker_OriginalEntities" value="&lt;Entities />" /><input name="ctl00$ctl38$g_8c61ba77_3b64_4668_b517_fbf9cca590da$ctl00$spPeoplePicker$HiddenEntityKey" type="hidden" id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker_HiddenEntityKey" /><input name="ctl00$ctl38$g_8c61ba77_3b64_4668_b517_fbf9cca590da$ctl00$spPeoplePicker$HiddenEntityDisplayText" type="hidden" id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker_HiddenEntityDisplayText" /><table id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker_OuterTable" class="ms-usereditor" cellspacing="0" cellpadding="0" style="width:250px;border-collapse:collapse;"><tr><td valign="top"><table cellpadding="0" cellspacing="0" style="width:100%;table-layout:fixed;"><tr><td id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker_containerCell"><div id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker_upLevelDiv" tabindex="0" onfocus="StoreOldValue(&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;); saveOldEntities(&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;); Sys.UI.DomElement.addCssClass(this, &#39;ms-inputBoxActive&#39;);" aria-multiline="true" onblur="if(typeof(ExternalCustomControlCallback)==&#39;function&#39;){ if(ShouldCallCustomCallBack(&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;,event)){if(!ValidatePickerControl(&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;)){ShowValidationError();return false;}else {ExternalCustomControlCallback(&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;);}}} Sys.UI.DomElement.removeCssClass(this, &#39;ms-inputBoxActive&#39;);" class="ms-inputuserfield ms-inputBox" onclick="onClickRw(true, true,event,&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;);" onchange="updateControlValue(&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;);" onPaste="dopaste(&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;,event);" preferContentEditableDiv="true" AutoPostBack="0" rows="3" onDragStart="canEvt(event);" onkeyup="return onKeyUpRw(&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;);" onCopy="docopy(&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;,event);" title="People Picker" onkeydown="return onKeyDownRw(&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;, 3, true, event);" spellcheck="false" contentEditable="true" aria-haspopup="true" style="word-wrap: break-word;overflow-x: hidden;overflow-y: auto;" name="upLevelDiv" role="textbox"></div><textarea name="ctl00$ctl38$g_8c61ba77_3b64_4668_b517_fbf9cca590da$ctl00$spPeoplePicker$downlevelTextBox" rows="3" cols="20" id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker_downlevelTextBox" class="ms-inputuserfield ms-inputBox" onfocus="StoreOldValue(&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;); saveOldEntities(&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;); Sys.UI.DomElement.addCssClass(this, &#39;ms-inputBoxActive&#39;);" onkeyup="return onKeyUpRw(&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;);" title="People Picker" onchange="updateControlValue(&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;);" onblur="if(typeof(ExternalCustomControlCallback)==&#39;function&#39;){ if(ShouldCallCustomCallBack(&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;,event)){if(!ValidatePickerControl(&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;)){ShowValidationError();return false;}else {ExternalCustomControlCallback(&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;);}}} Sys.UI.DomElement.removeCssClass(this, &#39;ms-inputBoxActive&#39;);" onkeydown="return onKeyDownRw(&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;, 3, true, event);" AutoPostBack="0" renderAsContentEditableDiv="true" style="width:100%;display: none;position: absolute; ">
</textarea></td>
					</tr>
				</table></td>
			</tr><tr><td><span id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker_errorLabel" class="ms-error"></span></td>
			</tr><tr style="padding-top:2;"><td><table cellspacing="0" cellpadding="0" style="width:100%;border-collapse:collapse;"><tr><td valign="top" style="width:88%;"><span style="font-size:8pt;"></span></td><td valign="top" nowrap="true" style="padding-left:5px;float:right;"><a id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker_checkNames" title="Check Names" onclick=" if(!ValidatePickerControl(&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;)){ ShowValidationError(); return false;} var arg=getUplevel(&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;); var ctx=&#39;ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker&#39;;EntityEditorSetWaitCursor(ctx);WebForm_DoCallback(&#39;ctl00$ctl38$g_8c61ba77_3b64_4668_b517_fbf9cca590da$ctl00$spPeoplePicker&#39;,arg,EntityEditorHandleCheckNameResult,ctx,EntityEditorHandleCheckNameError,true);return false;" href="javascript:"><img title="Check Names" src="/_layouts/15/images/checknames.png" alt="Check Names" /></a>&#160;<a id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker_browse" title="Browse" onclick="__Dialog__ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_spPeoplePicker(); return false;" href="javascript:"><img title="Browse" src="/_layouts/15/images/addressbook.gif" alt="Browse" /></a></td>
					</tr>
				</table></td>
			</tr>
		</table></span>
            
        -->
        
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class = "form-group-sm">
                                    <label for = "name">Skills :</label>
									<span style="color:red">*</span>
                                    <textarea name="txtskills" rows="1" cols="20" id="txtskills" class="form-control">
</textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="container_9">
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group-sm">
                                    <div>
                                        <label>Best Date and Time for Telephonic Interview :</label>
                                    </div>
                                    <div class="col-md-8" style="padding:0;">
                                        <!-- <table border="0" cellpadding="0" cellspacing="0"><tr><td class="ms-dtinput" ><label for="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_DateTimeControl1_DateTimeControl1Date" style="display:none"> Date</label><input name="ctl00$ctl38$g_8c61ba77_3b64_4668_b517_fbf9cca590da$ctl00$DateTimeControl1$DateTimeControl1Date" type="text" maxlength="45" id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_DateTimeControl1_DateTimeControl1Date" class="ms-input" AutoPostBack="0" /></td><td class="ms-dtinput" ><a href="#" onclick='clickDatePicker("ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_DateTimeControl1_DateTimeControl1Date", "\u002f_layouts\u002f15\u002fiframe.aspx?&amp;cal=1&amp;lcid=1033&amp;langid=1033&amp;ww=0111110&amp;fdow=0&amp;fwoy=0&amp;hj=0&amp;swn=False&amp;minjday=109207&amp;maxjday=2666269&amp;date=", "", event);return false;' ><img id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_DateTimeControl1_DateTimeControl1DateDatePickerImage" src="/_layouts/15/images/calendar_25.gif" border="0" alt="Select a date from the calendar."></img></a></td><td><iframe id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_DateTimeControl1_DateTimeControl1DateDatePickerFrame" src="/_layouts/15/images/blank.gif" frameborder="0" scrolling="no" style="DISPLAY:none;POSITION:absolute; width:200px; Z-INDEX:101;" title="Select a date from the calendar."></iframe></td><td class="ms-dttimeinput" nowrap="nowrap"><label for="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_DateTimeControl1_DateTimeControl1DateHours" style="display:none"> Hours</label><select name="ctl00$ctl38$g_8c61ba77_3b64_4668_b517_fbf9cca590da$ctl00$DateTimeControl1$DateTimeControl1DateHours" id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_DateTimeControl1_DateTimeControl1DateHours" dir="ltr"><option selected="selected" value="12 AM">12 AM</option><option value="1 AM">1 AM</option><option value="2 AM">2 AM</option><option value="3 AM">3 AM</option><option value="4 AM">4 AM</option><option value="5 AM">5 AM</option><option value="6 AM">6 AM</option><option value="7 AM">7 AM</option><option value="8 AM">8 AM</option><option value="9 AM">9 AM</option><option value="10 AM">10 AM</option><option value="11 AM">11 AM</option><option value="12 PM">12 PM</option><option value="1 PM">1 PM</option><option value="2 PM">2 PM</option><option value="3 PM">3 PM</option><option value="4 PM">4 PM</option><option value="5 PM">5 PM</option><option value="6 PM">6 PM</option><option value="7 PM">7 PM</option><option value="8 PM">8 PM</option><option value="9 PM">9 PM</option><option value="10 PM">10 PM</option><option value="11 PM">11 PM</option></select>&nbsp;
		<label for="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_DateTimeControl1_DateTimeControl1DateMinutes" style="display:none"> Minutes</label><select name="ctl00$ctl38$g_8c61ba77_3b64_4668_b517_fbf9cca590da$ctl00$DateTimeControl1$DateTimeControl1DateMinutes" id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_DateTimeControl1_DateTimeControl1DateMinutes" dir="ltr"><option selected="selected" value="00">00</option><option value="05">05</option><option value="10">10</option><option value="15">15</option><option value="20">20</option><option value="25">25</option><option value="30">30</option><option value="35">35</option><option value="40">40</option><option value="45">45</option><option value="50">50</option><option value="55">55</option></select></td>
		</tr></table>
		-->
        <input name="txtbesttimefortelephonicinterview" type="text" id="txtbesttimefortelephonicinterview" title="MM/DD/YYYY" class="form-control" PlaceHolder="MM/DD/YYYY" style="width:140px" readonly />
		<img src="img/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;cursor:pointer;float:left;margin-top:-25px;margin-left:112px" border="0" onclick="javascript:NewCssCal('txtbesttimefortelephonicinterview','MMddyyyy','dropdown',false,'24',false)"/>
    </div>
    <div class="col-md-4" style="padding:0 0 0 0px;">
        <select name="drltime" id="drltime" class="form-control" style="text-align:left">
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
</div>
</div>
<br/>
<span class="label label-primary col-xs-12 well well-sm shadow"  style="text-align:left;Background:#447DC4;color:#FFFFFF; width:100%;font-size:14px" >(B) Consultant's Employer Details</span>
<div class="container_9">
    <div class="row">
        <div class="col-md-4">
            <div class="form-group-sm">
                <label>Employer Name :</label>
                <input name="txtempname" type="text" id="txtempname" value="<%=objUserMaster.getFirstName()%> <%=objUserMaster.getLastName()%>" class="form-control" />
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group-sm">
                <label>Employer Mail ID:</label>
                <span style="color:red">*</span>
                <input name="txtempmailID" type="text" id="txtempmailID" value="<%=objUserMaster.getEmailId()%>" class="form-control" />
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group-sm">
                <label>Employeer Contact Number :</label>
                <input name="txtempcontactnumber" type="text" id="txtempcontactnumber" value="<%=objContact.getMobileNo()%>" class="form-control" />
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group-sm">
                <label>Employeer Company :</label>
                <input name="txtempcompany" type="text" id="txtempcompany" value="" class="form-control" />
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
    <input type="submit" name="btnupload" value="Submit" id="ctl00_ctl38_g_8c61ba77_3b64_4668_b517_fbf9cca590da_ctl00_btnupload" class="btn btn-info" />
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

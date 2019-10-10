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
  function isNumber(evt) {    
  var charCode = (evt.which) ? evt.which : evt.keyCode;     
  if (charCode != 46 && charCode > 31   && charCode != 43  && charCode != 45    
  && (charCode < 48 || charCode > 57))         
  return false;      
  return true; }
	
	
	

$(document).ready(function(){
	/* allow numbers and + sign to get experience eg 2+ */
	
	$.validator.addMethod("CheckExperience",  function(value, element) {   
  // put your own logic here, this is just a (crappy) example 
  return value.match(/^\d*[0-9,+,-](|.\d*[0-9,+,-]|,\d*[0-9,+,-])?$/);     }, 
  "Enter valid experience eg. 2+,2"   );
	
	
	/* space validation for clientcontact.jsp */
	$("#client_name").blur(function(){  
//alert("ok check inside name");      
   var a = $("#client_name").val();
   var b = a.trim();
   $("#client_name").val(b);
    });
	 
	
	
$("#client_company").blur(function(){    
//alert("ok check inside company");    
   var c = $("#client_company").val();
   var d = c.trim();
   $("#client_company").val(d);
    });	
	
	
	/* space validation for vendorcotact.jsp */
	$("#vendor_fname").blur(function(){  
//alert("ok check inside name");      
   var a = $("#vendor_fname").val();
   var b = a.trim();
   $("#vendor_fname").val(b);
    });
	 
	
	
$("#vendor_lname").blur(function(){    
//alert("ok check inside company");    
   var c = $("#vendor_lname").val();
   var d = c.trim();
   $("#vendor_lname").val(d);
    });	
	
	
	
$("#vendor_email").blur(function(){    
//alert("ok check inside company");    
   var e = $("#vendor_email").val();
   var f = e.trim();
   $("#vendor_email").val(f);
    });	
	
	
	$("#vendor_company").blur(function(){    
//alert("ok check inside company");    
   var g = $("#vendor_company").val();
   var h = g.trim();
   $("#vendor_company").val(h);
    });	
	
	
	$("#JobTitle").blur(function(){    
//alert("ok check inside company");    
   var i = $("#JobTitle").val();
   var j = i.trim();
   $("#JobTitle").val(j);
    });	
	
	$("#Position").blur(function(){    
//alert("ok check inside company");    
   var k = $("#Position").val();
   var l = k.trim();
   $("#Position").val(l);
    });	
	
	
	$("#LocationField").blur(function(){    
//alert("ok check inside company");    
   var m = $("#LocationField").val();
   var n = m.trim();
   $("#LocationField").val(n);
    });	
	
	$("#RecruiterEmail").blur(function(){    
//alert("ok check inside company");    
   var o = $("#RecruiterEmail").val();
   var p = o.trim();
   $("#RecruiterEmail").val(p);
    });	
	
		
	/* email validation */
		$.validator.addMethod("emailvalidate", 
			function(value, element) {
				return /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
			}, 
			"Please Enter the valid Email"
		);
		
	/* File Upload size validation */
		$.validator.addMethod('filesize', function (value, element, param) {
				return this.optional(element) || (element.files[0].size <= param)
			}, 'File size must be less than {0}'
		);
	
	/* special character validation */
		 $.validator.addMethod(
			"checkspecialcharacter",
			function(value, element) {
				// put your own logic here, this is just a (crappy) example
				return value.match(/^[a-zA-Z\s]+$/);
			},
			"Special characters are not accepted"
			);
	
		$.validator.addMethod("Numbersonly",
   
		   function(value, element) {
			// put your own logic here, this is just a (crappy) example
			return value.match(/^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/);
		   },
		   "Numbers only accepted"
		 );
	
	/* create post requirement page == createPostReq.jsp */
	
		$('form[name="postReq"]').validate({
		 
			rules: {
				
				JobTitle:"required",
				Position:"required",
				LocationField:"required",
				RequirementStatus:"required",
				minExperience:
				{
				   required:true, 
				   maxlength:3,   
				   CheckExperience:true 
				},
				maxExperience:{
				   required:true, 
				   maxlength:3,   
				   CheckExperience:true   
				 },
				RecruiterEmail:{
					emailvalidate:true,
				}
			},
			messages:{
				
				JobTitle:"Please Enter the Job title",
				Position:"Please enter the position",
				LocationField:"Please enter the location field",
				RequirementStatus:"Please select the requirement status",
				minExperience:{
					required:"Please Enter the minimum required Experience",
				},
				maxExperience:{
					required:"Please Enter the maximum required Experience",
				},
				
			},
			errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}
			
	});
	
	/* Demo request validation == demoRequest.jsp */
		 $('form[name="demoRequest"]').validate({
				rules: {				
					inf_field_FirstName:{
						required:true,
						checkspecialcharacter:true
					},
					inf_field_Email:{
						required:true,
						emailvalidate:true
					},	
					
				},
				messages: {				
					inf_field_FirstName:{ 
					required:"FirstName is required",
					checkspecialcharacter:"SpecialCharacters are not allowed "
					},
					
					inf_field_Email:{
						required:"Email is required",
						emailvalidate:"Enter valid E-Mail"
					},
						
				},
				
				errorPlacement: function(error, element) {
					if (element.is("none"))
						error.appendTo(element.parent().next().next());
					else
						error.appendTo(element.parent());
				}
				
				
			});
			
	/* create vendor page == CreateVendorContacts.jsp */
	
	 $('form[name="frmcreateVendor"]').validate({
			rules: {				
				vendor_fname:{
					required:true,checkspecialcharacter:true
				},
				vendor_lname:{
					required:true,checkspecialcharacter:true
				},
				vendor_email:{
					required:true,emailvalidate:true
				},
				vendor_businessPhone:{
					//Numbersonly:true
				},
				vendor_homePhone:{
					//Numbersonly:true
				},
				vendor_company:"required"
				
			},
			messages: {				
				vendor_fname:{ 
				required:"FirstName is required",
				checkspecialcharacter:"SpecialCharacters are not allowed "
				},
				vendor_lname:{ 
				required:"LastName is required",
				checkspecialcharacter:"SpecialCharacters are not allowed "
				},
				vendor_email:{
					required:"Email is required",
					emailvalidate:"Enter valid E-Mail"
				},
				
				vendor_company:"CompanyName is required"
				
			},
			
			errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}
			
			
		});
		
	/* create client page == CreateVendorContacts.jsp */
	
	 $('form[name="frmcreateClient"]').validate({
			rules: {				
				client_name: {
					required:true,checkspecialcharacter:true
				},
				client_businessPhone:{
					//Numbersonly:true
				},
				client_company:"required",
				client_email:{
					required:true,emailvalidate:true
				}
				
			},
			messages: {				
				client_name:{ 
				required:"clientname is required",
				checkspecialcharacter:"SpecialCharacters are not allowed "
				},
				
				client_company:"clientcompany is required",
				client_email:{
					required:"clientemail is required",
					emailvalidate:"Enter valid E-Mail"
				}
				
			},
			// the errorPlacement has to take the table layout into account
			errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}
			
			
		});
		
	/* create negotiate rate page == frmMFRateNegotiate.jsp*/
	
		 $('form[name="createRateNegotiation"]').validate({
			rules: {				
				negotiateRate:"required",
				negotiateReason:"required"
			},
			messages: {	
				negotiateRate:"Please enter the negotiate rate",
				negotiateReason:"Please enter the valid reason for negotiation"
				
			},
			// the errorPlacement has to take the table layout into account
			errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}
			
			
		});


		
	/* Apply for job vacancy page = ApplyJobVacancy.jsp*/
	
		 $('form[name="frmapplyjobvacancy"]').validate({
			rules: {				
				canMaritalStatus:"required",
				txttotalexperience:"required",
				txtcontactnumber:"required",
				txtcurrentAddress1:"required",
				drpvisaapproval:"required",
				drpvisatype:"required",
				drpi797available:"required",
				drpI97available:"required",
				txtskills:"required",
				txtempmailID:"required"
			},
			messages: {	
				canMaritalStatus:"Please select the marital status",
				txttotalexperience:"Enter total experience",
				txtcontactnumber:"Please enter contact number",
				txtcurrentAddress1:"Please enter the address",
				drpvisaapproval:"Please select the VISA Approval Status ",
				drpvisatype:"Select the VISA Type",
				drpi797available:"This field is required",
				drpI97available:"This field is required",
				txtskills:"Please enter the skills",
				txtempmailID:"Please enter the employer mail ID"
					
			},
			// the errorPlacement has to take the table layout into account
			errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}
			
			
		});
		
		
		
	/* Upload Resume to apply for job == PostApplyJobToSP.jsp*/
		
			
			$('form[name="frmuploadresume"]').validate({ 
				rules:{
					ctl00$ctl38$g_8c61ba77_3b64_4668_b517_fbf9cca590da$ctl00$FileUpload1:{
					required:true,
					filesize:1000000					
					}
			},
			messages:{
				ctl00$ctl38$g_8c61ba77_3b64_4668_b517_fbf9cca590da$ctl00$FileUpload1:{
					required:"Please Upload the Resume", 
					filesize:"File Size should be <1MB"
				}
			}
		});

	/* Import Client upload form validation == ImportXLSDataToClient.jsp*/
	
			$('form[name="frmimportClient"]').validate({ 
			onfocusout: function(e) {  // this option is not needed
				this.element(e);       // this is the default behavior
			},
			rules:{
				file:{
					required:true,
					extension: "xlsx|xls",
					filesize:2000000
				}
			},
			messages: {  // <-- you must declare messages inside of "messages" option
				file:{
					required:"Please upload the file",                  
					extension:"select xlsx|xls file format",
					filesize:"File Size should be <2MB"
				}
			}
		});
		
	/* Import vendor upload form == ImportXLSDataToVendor.jsp*/
	
		 $('form[name="frmimportVendor"]').validate({ 
				onfocusout: function(e) {  // this option is not needed
					this.element(e);       // this is the default behavior
				},
				rules:{
					file:{
						required:true,
						extension: "xlsx|xls",
						filesize:2000000
					}
				},
				messages: {  // <-- you must declare messages inside of "messages" option
					file:{
						required:"Please upload the file",                  
						extension:"select xlsx|xls file format",
						filesize:"File Size should be <2MB"
					}
				}
				
						
			});


/*---------------------------Hot List Candiate Validation Start Here---------------------------------*/


	
$("#txtfirstname").blur(function(){    
   
   var c = $("#txtfirstname").val();
   var d = c.trim();
   $("#txtfirstname").val(d);
 });
 $("#txtlastname").blur(function(){    
   
   var c = $("#txtlastname").val();
   var d = c.trim();
   $("#txtlastname").val(d);
 });
 $("#txtdateofbirth").blur(function(){    
   
   var c = $("#txtdateofbirth").val();
   var d = c.trim();
   $("#txtdateofbirth").val(d);
 });
 $("#txtemailaddress").blur(function(){    
   
   var c = $("#txtemailaddress").val();
   var d = c.trim();
   $("#txtemailaddress").val(d);
 });
 $("#txttotalexperience").blur(function(){    
   
   var c = $("#txttotalexperience").val();
   var d = c.trim();
   $("#txttotalexperience").val(d);
 });
 $("#txtcontactnumber").blur(function(){    
   
   var c = $("#txtcontactnumber").val();
   var d = c.trim();
   $("#txtcontactnumber").val(d);
 });
 $("#txtcurrentAddress1").blur(function(){    
   
   var c = $("#txtcurrentAddress1").val();
   var d = c.trim();
   $("#txtcurrentAddress1").val(d);
 });
 $("#drpvisaapproval").blur(function(){    
   
   var c = $("#drpvisaapproval").val();
   var d = c.trim();
   $("#drpvisaapproval").val(d);
 });
 $("#drpvisatype").blur(function(){    
   
   var c = $("#drpvisatype").val();
   var d = c.trim();
   $("#drpvisatype").val(d);
 });
 $("#drpi797available").blur(function(){    
   
   var c = $("#drpi797available").val();
   var d = c.trim();
   $("#drpi797available").val(d);
 });
 $("#drpI97available").blur(function(){    
   
   var c = $("#drpI97available").val();
   var d = c.trim();
   $("#drpI97available").val(d);
 });
 $("#txtskills").blur(function(){    
   
   var c = $("#txtskills").val();
   var d = c.trim();
   $("#txtskills").val(d);
 });
 $("#hotlistAvl").blur(function(){    
   
   var c = $("#hotlistAvl").val();
   var d = c.trim();
   $("#hotlistAvl").val(d);
 });
 $("#txtempmailID").blur(function(){    
   
   var c = $("#txtempmailID").val();
   var d = c.trim();
   $("#txtempmailID").val(d);
 });
 
 
 jQuery.validator.addMethod("noSpace", function(value, element) { 
  return value.indexOf(" ") < 0 && value != ""; 
}, "No space please and don't leave it empty");

$.validator.addMethod('numericOnly', function (value) {
       return /^[0-9].+$/.test(value);
}, 'Please only enter numeric values (0-9)');


 $("#txtcontactnumber").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
             // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
             // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
	
	$("#txttotalexperience").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
             // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
             // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
	
	$("#txtrate").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
             // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
             // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
	
	$("#txtempcontactnumber").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
             // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
             // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
 
	
	/* Create hotlist candidate page = createHotListCandidate.jsp*/
	
		 $('form[name="frmCreateHotlistCandidate"]').validate({
			rules: {				
				txtfirstname:
					{
						required:true,
						checkspecialcharacter:"SpecialCharacters are not allowed ",
					},
				txtlastname:
					{
						required:true,
						checkspecialcharacter:"SpecialCharacters are not allowed "
					},
				txtdateofbirth:"required",
				txtemailaddress:
					{
						required:true,
						emailvalidate:true
					},
				txttotalexperience:
					{
						required:true,
						numericOnly:true,
					},
				txtcontactnumber:
					{
						required:true,
						numericOnly:true,
					},
				txtcurrentAddress1:"required",
				drpvisaapproval:"required",
				drpvisatype:"required",
				drpi797available:"required",
				drpI97available:"required",
				txtskills:"required",
				hotlistAvl:"required",
				txtempmailID:
					{
						required:true,
						emailvalidate:true
					},
				candidateResume:{
					required:true,
					filesize:1000000					
					}
			},
			messages: {	
				txtfirstname:
					{ 
					required:"Enter the first name",
					checkspecialcharacter:"SpecialCharacters are not allowed ",
					},
				txtlastname:
					{ 
					required:"Enter the last name",
					checkspecialcharacter:"SpecialCharacters are not allowed "
					},
				txtdateofbirth:"Enter the DOB",
				txtemailaddress:
					{ 
					required:"Enter the E-Mail Id",
					emailvalidate:"Enter the valid E-Mail Id"
					},
				txttotalexperience:
					{
					required:"Enter total experience",
					numericOnly:"Please only enter numeric values (0-9)",
					},
				txtcontactnumber:
					{
					required:"Please enter contact number",
					numericOnly:"Please only enter numeric values (0-9)",
					},
				txtcurrentAddress1:"Please enter the address",
				drpvisaapproval:"Please select the VISA Approval Status ",
				drpvisatype:"Select the VISA Type",
				drpi797available:"This field is required",
				drpI97available:"This field is required",
				txtskills:"Please enter the skills",
				hotlistAvl:"Please enter the Hotlist AVL",
				txtempmailID:
					{ 
					required:"Please enter the employer mail ID",
					emailvalidate:"Enter the valid employer E-Mail Id"
					},
				candidateResume:
					{
					required:"Please Upload the Resume", 
					filesize:"File Size should be <1MB"
					}
					
			},
			// the errorPlacement has to take the table layout into account
			errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}
			
			
		});
		
	/* special validation for createHotListCandidate.jsp */
	$("#drpvisatype").change(function(){  
		//alert("ok check inside name");      
		var a = $("#drpvisatype").val();
		   if(a == "US Citizen" ){
			   $("#drpi797available").val("NO");
			   $("#drpI97available").val("NO");
			  
			   $("#drpi797available").find('[value="YES"]').remove();
			   $("#drpI97available").find('[value="YES"]').remove();
		   }else{
			   
			   $("#drpi797available").val("");
			   $("#drpI97available").val("");
			   
			    $('#drpi797available').append($('<option>', {
					value: "YES",
					text: "YES"
				}));
				$('#drpI97available').append($('<option>', {
					value: "YES",
					text: "YES"
				}));
				
			   var usedNames = {};
				$("select[name='drpi797available'] > option").each(function () {
					if(usedNames[this.text]) {
						$(this).remove();
					} else {
						usedNames[this.text] = this.value;
					}
				});
				var usedNames = {};
				$("select[name='drpI97available'] > option").each(function () {
					if(usedNames[this.text]) {
						$(this).remove();
					} else {
						usedNames[this.text] = this.value;
					}
				});
		   }
    });
	
	
	/* edit hotlist candidate page = frmEditHotListCandidate.jsp*/
	
		 $('form[name="frmEditHotlistCandidate"]').validate({
			rules: {				
				firstName:
					{
						required:true,
						checkspecialcharacter:"SpecialCharacters are not allowed "
					},
				lastName:
					{
						required:true,
						checkspecialcharacter:"SpecialCharacters are not allowed "
					},
				DateOfBirth:"required",
				CandidateEMail:
					{
						required:true,
						emailvalidate:true
					},
				TotalExperience:"required",
				CandidateContactNumber:"required",
				CurrentLocation:"required",
				VisaApprovalValue:"required",
				VisaTypeValue:"required",
				FormI797AvailableValue:"required",
				FormI94AvailableValue:"required",
				Skills:"required",
				hotlistAvl:"required",
				EmployerMailID:
					{
						required:true,
						emailvalidate:true
					},
				candidateResume:{
					required:true,
					filesize:1000000					
					}
			},
			messages: {	
				firstName:
					{ 
					required:"Enter the first name",
					checkspecialcharacter:"SpecialCharacters are not allowed "
					},
				lastName:
					{ 
					required:"Enter the last name",
					checkspecialcharacter:"SpecialCharacters are not allowed "
					},
				DateOfBirth:"Enter the DOB",
				CandidateEMail:
					{ 
					required:"Enter the E-Mail Id",
					emailvalidate:"Enter the valid E-Mail Id"
					},
				TotalExperience:"Enter total experience",
				CandidateContactNumber:"Please enter contact number",
				CurrentLocation:"Please enter the address",
				VisaApprovalValue:"Please select the VISA Approval Status ",
				VisaTypeValue:"Select the VISA Type",
				FormI797AvailableValue:"This field is required",
				FormI94AvailableValue:"This field is required",
				Skills:"Please enter the skills",
				hotlistAvl:"Please enter the Hotlist AVL",
				EmployerMailID:
					{ 
					required:"Please enter the employer mail ID",
					emailvalidate:"Enter the valid employer E-Mail Id"
					},
				candidateResume:{
					required:"Please Upload the Resume", 
					filesize:"File Size should be <1MB"
				}
					
			},
			// the errorPlacement has to take the table layout into account
			errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}
			
			
		});
		
	/* special validation for createHotListCandidate.jsp */
	$("#VisaTypeValue").change(function(){  
		//alert("ok check inside name");      
		var a = $("#VisaTypeValue").val();
		
		
		   if(a == "US Citizen" ){
			   $("#FormI797AvailableValue").val("NO");
			   $("#FormI94AvailableValue").val("NO");
			   $("#FormI797AvailableValue").find('[value="YES"]').remove();
			   $("#FormI94AvailableValue").find('[value="YES"]').remove();
			   

			   
		   }else{
			   
			   $("#FormI797AvailableValue").val("");
			   $("#FormI94AvailableValue").val("");
			   
			   $('#FormI797AvailableValue').append($('<option>', {
					value: "YES",
					text: "YES"
				}));
				$('#FormI94AvailableValue').append($('<option>', {
					value: "YES",
					text: "YES"
				}));
				
			   var usedNames = {};
				$("select[name='FormI797AvailableValue'] > option").each(function () {
					if(usedNames[this.text]) {
						$(this).remove();
					} else {
						usedNames[this.text] = this.value;
					}
				});
				var usedNames = {};
				$("select[name='FormI94AvailableValue'] > option").each(function () {
					if(usedNames[this.text]) {
						$(this).remove();
					} else {
						usedNames[this.text] = this.value;
					}
				});
		   }
    });
		
	
});

$( window ).on( "load", function() {
     var a = $("#VisaTypeValue").val();
	
		
		   if(a == "US Citizen" ){
			   $("#FormI797AvailableValue").val("NO");
			   $("#FormI94AvailableValue").val("NO");
			   $("#FormI797AvailableValueHidden").val("NO");
			   $("#FormI94AvailableValueHidden").val("NO");
			   $("#FormI797AvailableValue").find('[value="YES"]').remove();
			   $("#FormI94AvailableValue").find('[value="YES"]').remove();
			   
		   }else{
			   $('#FormI797AvailableValue').append($('<option>', {
					value: "YES",
					text: "YES"
				}));
				$('#FormI94AvailableValue').append($('<option>', {
					value: "YES",
					text: "YES"
				}));
				
			   var usedNames = {};
				$("select[name='FormI797AvailableValue'] > option").each(function () {
					if(usedNames[this.text]) {
						$(this).remove();
					} else {
						usedNames[this.text] = this.value;
					}
				});
				var usedNames = {};
				$("select[name='FormI94AvailableValue'] > option").each(function () {
					if(usedNames[this.text]) {
						$(this).remove();
					} else {
						usedNames[this.text] = this.value;
					}
				});
		   }
    });
	
/*---------------------------Hot List Candiate Validation End Here---------------------------------*/

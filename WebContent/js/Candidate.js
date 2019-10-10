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
$(document).ready(function(){
	
	/* File Upload size validation */
	$.validator.addMethod('filesize', function (value, element, param) {
			return this.optional(element) || (element.files[0].size <= param)
		}, 'File size must be less than {0}'
	);
	
	/* email validation */
		$.validator.addMethod("emailvalidate", 
			function(value, element) {
				return /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
			}, 
			"Please Enter the valid Email"
		);
	
	$('input[name="submit"]').click(function(){
		
		  if($("#freshCanResume").val() == "" || $("#freshCanResume").val() == "null"||$("#freshCanResume").val() == null){
			$("#UploadNewResumev").prop('checked',false);
			$("#freshCanResume").val("");
		  }
		 });

	
	$("#freshcandidate_name").blur(function(){    
	   var c = $("#freshcandidate_name").val();
	   var d = c.trim();
	   $("#freshcandidate_name").val(d);
	 });
	 $("#mobile_num").blur(function(){    
	   var c = $("#mobile_num").val();
	   var d = c.trim();
	   $("#mobile_num").val(d);
	 });
	 $("#freshcandidate_email").blur(function(){    
	   var c = $("#freshcandidate_email").val();
	   var d = c.trim();
	   $("#freshcandidate_email").val(d);
	 });
	 $("#status").blur(function(){    
	   var c = $("#status").val();
	   var d = c.trim();
	   $("#status").val(d);
	 });
	 $("#recruiter").blur(function(){    
	   var c = $("#recruiter").val();
	   var d = c.trim();
	   $("#recruiter").val(d);
	 });
	 
	 $("#mobile_num").keydown(function (e) {
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
 
	$("#uploadnew").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#UploadNewResume").show();
               
                $("#UploadResumeCheck").val("CandidateResume");
            }
            else if($(this).prop("checked") == false){
                $("#UploadNewResume").hide();
                $("#UploadResumeCheck").val("");
            }
		
	});
	
	$('form[name="frmfreshcandidate"]').validate({ 
		rules:{
			freshCanResume:{
				required:true,
				filesize:1000000,
									
			},
			freshcandidate_name:"required",
			visa_type:"required",
			freshcandidate_email:
					{
						required:true,
						emailvalidate:true
					},
			
	},
	messages:{
		freshCanResume:{
			required:"Please Upload the form", 
			filesize:"File Size should be <1MB",
			

		},
		freshcandidate_name:"Please enter the name",
		visa_type:"Select the VISA Type",
		freshcandidate_email:
			{ 
				required:"Enter the E-Mail Id",
				emailvalidate:"Enter the valid E-Mail Id"
			},
		
	}
});
});

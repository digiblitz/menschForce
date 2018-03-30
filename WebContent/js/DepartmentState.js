//
//Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/
//
//License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/
//
//Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
//
//Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
//
//"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
//
$(document).ready(function(){
	
	$('input[name="submit"]').click(function(){
		
		  if($("#additionalfileEdit").val() == "" || $("#additionalfileEdit").val() == "null"||$("#additionalfileEdit").val() == null){
			$("#customuploadnew").prop('checked',false);
			$("#additionalfileCheck").val("");
		  }
		 });
	
	$(document).on("click", "#close", function() {
	  if (confirm('Are you sure want to close ?')) {
			//$(this).prev('span.text').remove();
	  location.href="ListEmployeeDetails.html";
	  //alert("check");
		}
	});
	
	$("#uploadnew").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadnewform").show();
               
                $("#visaformCheck").val("visaformCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadnewform").hide();
                $("#visaformCheck").val("");
            }
		
	});
	$("#customuploadnew").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#customuploadnewform").show();
                $("#additionalfileCheck").val("additionalfileCheck");
                
            }
            else if($(this).prop("checked") == false){
                $("#customuploadnewform").hide();
                $("#additionalfileCheck").val("");
            }
		
	});
	
		
	$(window).load(function(){
		$("#uploadnewform").hide();
		$("#customuploadnewform").hide();
		/* To show/enable only submitted form -  button*/
		
		var stageval = $("#stagesize").val();
		//alert(stageval);
		if(stageval != 0){
			//alert("inside if loop");
			for( i=1;i<= stageval;i++){
				
			var val = $("#stage"+i).val();
			if(val == "true" || val == "1"){
				//alert(i);
					//alert("inside for if loop");
					 $("#stage"+i).removeClass("disabledbox");
					  $("#stage"+i).removeAttr("disabled");
					//alert("class removed");
				}
			else {
				//alert("else for condition");
				//alert(val);
				$("#stage"+i).addClass("disabledbox");
				 $("#stage"+i).attr('disabled', 'disabled');
				}
			}
		}
		
		/*get the system date and time while loading and submiting the form*/
		
		 var checkSystemDate = $("#SystemDate").val();
		  //alert(checkSystemDate);
		
		    var oDate = new Date();
			
		   var nHrs = oDate.getHours();
		   var nMin = oDate.getMinutes();
		   var nSecs = oDate.getSeconds();
		   var nDate = oDate.getDate();
		   var nMnth = oDate.getMonth()+1;
		  
		   var nYear = oDate.getFullYear();
		   
		   $("#SystemDate").val(nMnth+"-"+nDate+"-"+nYear+" "+nHrs+":"+nMin+":"+nSecs);
		 
		});

	/* email validation */
		$.validator.addMethod("emailvalidate", 
			function(value, element) {
				return /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
			}, 
			"Please Enter the valid Email"
		);
		
			jQuery.validator.addMethod("greaterThan", 
			function(value, element, params) {

				if (!/Invalid|NaN/.test(new Date(value))) {
					return new Date(value) > new Date($(params).val());
				}

				return isNaN(value) && isNaN($(params).val()) 
					|| (Number(value) > Number($(params).val())); 
			},'Must be greater than {0}.');
			
			
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
	
	/* create post requirement page == frmDepartmentStateStage1.jsp */
	
	$('form[name="frmDSstage1"]').validate({ 
				rules:{
					visaform:{
						required:true,
						filesize:1000000,
						extension:"jpeg|txt|docx|doc|pdf|gif|png"						
					},
					formI_94:{
						required:true,
						filesize:1000000,
						extension:"jpeg|txt|docx|doc|pdf|gif|png"						
					},
					additionalfile:{
						filesize:1000000,
						extension:"jpeg|txt|docx|doc|pdf|gif|png"						
					},
					additional_address_docs:{
						filesize:1000000,
						extension:"jpeg|txt|docx|doc|pdf|gif|png"						
					},
					AdditionalDocsStage6:{
						filesize:1000000,
						extension:"jpeg|txt|docx|doc|pdf|gif|png"						
					},
					userid:"required",
					CreateDate:"required",
					AcknowledgementDate:"required",
					ApprovalStatus:"required",
					StartDate:"required",
					EndDate:{
						required:true,
						greaterThan:"#StartDate"
					},
					newI_9form:{
						required:true,
						filesize:1000000,
						extension:"jpeg|txt|docx|doc|pdf|gif|png"						
					},
					FileAR_1:{
						required:true,
						filesize:1000000,
						extension:"jpeg|txt|docx|doc|pdf|gif|png"						
					},
			},
			messages:{
				visaform:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB",
					extension:" Accept file format .jpeg, .gif, .doc, .docx ,.png or .pdf"

				},
				formI_94:{
						required:"Please Upload the form", 
						filesize:"File Size should be <1MB"	,
						extension:" Accept file format .jpg, .gif, .doc, .docx,.png or .pdf"
					},
				additionalfile:{
					filesize:"File Size should be <1MB",
					extension:" Accept file format .jpg, .gif, .doc, .docx ,.png or .pdf"
				},
				additional_address_docs:{
					filesize:"File Size should be <1MB",
					extension:" Accept file format .jpg, .gif, .doc, .docx ,.png or .pdf"
				},
				AdditionalDocsStage6:{
					filesize:"File Size should be <1MB",
					extension:" Accept file format .jpg, .gif, .doc, .docx ,.png or .pdf"
				},
				userid:"Please enter the userid",
				CreateDate:"Select the create date",
				AcknowledgementDate:"Select the Acknowledgement Date",
				ApprovalStatus:"Please select the approval status",
				EndDate:{
					required:"Please select the start date",
					greaterThan:"End state should be greater the Start Date"
				},
				StartDate:"Please select the end date",
				newI_9form:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB",
					extension:" Accept file format .jpg, .gif, .doc,.png , .docx or .pdf"
				},
				FileAR_1:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB",
					extension:" Accept file format .jpg, .gif, .doc, .docx ,.png or .pdf"
				},
			}
		});
		
});

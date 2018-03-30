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
	
	/* create post requirement page == frmDepartmentStateStage1.jsp */
	
	$('form[name="frmUSCISstage1"]').validate({ 
				rules:{
					UserID:"required",
					CreateDate:"required",
					AcknowledgementDate:"required",
					ApprovalStatus:"required",
					ContractForm:{
						required:true,
						filesize:1000000					
					},
					EmploymentProof:{
						required:true,
						filesize:1000000					
					},
					ClientLetter:{
						required:true,
						filesize:1000000					
					},
					WorkOrder:{
						required:true,
						filesize:1000000					
					},
					LabourClearance:{
						required:true,
						filesize:1000000					
					},
					FormI_129:{
						required:true,
						filesize:1000000					
					},
					//H1_BTransfer:"required",
					onBoarding:{
						required:true,
						filesize:1000000					
					},
					PostingI_129:{
						required:true,
						filesize:1000000					
					},
					Form1I_94:{
						required:true,
						filesize:1000000					
					},
					Form2I_94:{
						required:true,
						filesize:1000000					
					},
					FormI_9:{
						required:true,
						filesize:1000000
					},
					ContractDoc:{
						required:true,
						filesize:1000000
					},
					TimesheetDoc:{
						required:true,
						filesize:1000000
					},
					ClientDoc:{
						required:true,
						filesize:1000000
					},
					WeeklyReviewDoc:{
						required:true,
						filesize:1000000
					},
			},
			messages:{
				UserID:"Please enter the UserId",
				CreateDate:"Select the create date",
				AcknowledgementDate:"Select the Acknowledgement Date",
				ApprovalStatus:"Please select the approval status",
				ContractForm:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				EmploymentProof:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				ClientLetter:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				WorkOrder:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				LabourClearance:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				FormI_129:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				//H1_BTransfer:"Check whether H1-B Transfer",
				onBoarding:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				PostingI_129:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				Form1I_94:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				Form2I_94:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				FormI_9:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				ContractDoc:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				TimesheetDoc:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				ClientDoc:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				WeeklyReviewDoc:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
		}
	});
});

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
	
	/* validation in stage 9 */
	$('input[name="PrevailingWage"]').on('change', function() {
		$('input[name="PrevailingWage"]').not(this).prop('checked', false); 
			
			  if( $("#PrevailingWageyes").prop("checked") == true){
					 $("#wagehigh").show();
					$("#wagelow").hide();
			  }
			  if($("#PrevailingWageno").prop("checked") == true){
					 $("#wagehigh").hide();
					$("#wagelow").show();
			  }
	});
	
	/* Pay Document validation in stage 10*/
	
	$('input[name="PayDocument"]').on('change', function() {
		$('input[name="PayDocument"]').not(this).prop('checked', false); 
			
			  if( $("#PayDocumentyes").prop("checked") == true){
					 $("#Documentform").show();
					$("#Documentnote").hide();
			  }
			  if($("#PayDocumentno").prop("checked") == true){
					 $("#Documentform").hide();
					$("#Documentnote").show();
			  }
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
	
	
	
	/* department of labour jsp pages all stage*/
	
	$('form[name="frmDLstage1"]').validate({ 
				rules:{
					Access_file:{
						required:true,
						filesize:1000000					
					},
					JobFormat:{
						required:true,
						filesize:1000000					
					},
					CreateDate:"required",
					AcknowledgementDate:"required",
					ApprovalStatus:"required",
					salaryLevel:"required",
					yesno:"required",
					
					StateDeptLabour:{
						required:true,
						filesize:1000000					
					},
					NewsPaper:{
						required:true,
						filesize:1000000					
					},
					OnlineJobPortal:{
						required:true,
						filesize:1000000					
					},
					NoOfDays:"required",
					FromDate:"required",
					ToDate:"required",
					CommunicationDet:{
						required:true,
						filesize:1000000					
					},
					EducationQualify:{
						required:true,
						filesize:1000000					
					},
					ExperienceSkill:{
						required:true,
						filesize:1000000					
					},
					LocalPeople:"required",
					AccessFileNotes:"required",
					AccessFileForm:{
						required:true,
						filesize:1000000					
					},
					PrevailingWageReport:{
						required:true,
						filesize:1000000					
					},
					PrevailingWageForm:{
						required:true,
						filesize:1000000					
					},
					LessPrevailingWage:"required",
					PayDocumentForm:{
						required:true,
						filesize:1000000					
					},
					NoPayDocumentNotes:"required",
					ResponseDoc:{
						required:true,
						filesize:1000000					
					},
					H1BCandidate:{
						required:true,
						filesize:1000000					
					},
					LCAForm:{
						required:true,
						filesize:1000000					
					},
					PostingWorkForm:{
						required:true,
						filesize:1000000					
					},
					PostedDate:"required",
					TakenPhoto:{
						required:true,
						filesize:1000000					
					},
					SignClarification:{
						required:true,
						filesize:1000000					
					},
					DayNotification:{
						required:true,
						filesize:1000000					
					},
					TakenPhotoStage17:{
						required:true,
						filesize:1000000					
					},
					SystemTaken:{
						required:true,
						filesize:1000000					
					},
					PhotoTime:"required",
					LCAapprovedDoc:{
						required:true,
						filesize:1000000					
					},
					EmailProcess:{
						required:true,
						filesize:1000000					
					},
					SystemRecord:{
						required:true,
						filesize:1000000					
					},
					MailPosting:{
						required:true,
						filesize:1000000					
					},
					EmployeeAgreement:{
						required:true,
						filesize:1000000
					},
			},
			messages:{
				Access_file:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				JobFormat:{
						required:"Please Upload the form", 
						filesize:"File Size should be <1MB"					
					},
				CreateDate:"Select the create date",
				AcknowledgementDate:"Select the Acknowledgement Date",
				ApprovalStatus:"Please select the approval status",
				salaryLevel:"Please select the salary Level",
				yesno:"Please check any of the checkbox",
				StateDeptLabour:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				NewsPaper:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				OnlineJobPortal:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				CommunicationDet:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				NoOfDays:"Please enter the no. of Days",
				FromDate:"Please select the start date",
				ToDate:"Please select the End date",
				EducationQualify:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				ExperienceSkill:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				LocalPeople:"This Field is required",
				AccessFileNotes:"Please write about Access file",
				
				AccessFileForm:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				PrevailingWageReport:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				PrevailingWageForm:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				LessPrevailingWage:"please enter the reason",
				PayDocumentForm:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				NoPayDocumentNotes:"please enter the reason",
				ResponseDoc:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				H1BCandidate:{
					required:"Please Upload the candidate form", 
					filesize:"File Size should be <1MB"
				},
				LCAForm:{
					required:"Please Upload the LCA form", 
					filesize:"File Size should be <1MB"
				},
				PostingWorkForm:{
					required:"Please Upload the Posting work cycle form", 
					filesize:"File Size should be <1MB"
				},
				PostedDate:"please select the posted date",
				TakenPhoto:{
					required:"Please Upload the Photo", 
					filesize:"File Size should be <1MB"
				},
				SignClarification:{
					required:"Please Upload the signature", 
					filesize:"File Size should be <1MB"
				},
				DayNotification:{
					required:"Please Upload the Notification form", 
					filesize:"File Size should be <1MB"
				},
				TakenPhotoStage17:{
					required:"Please Upload the form", 
					filesize:"File Size should be <1MB"
				},
				SystemTaken:{
					required:"Please Upload the  form", 
					filesize:"File Size should be <1MB"
				},
				PhotoTime:"Please select the time",
				LCAapprovedDoc:{
					required:"Please Upload the LCA Approved form", 
					filesize:"File Size should be <1MB"
				},
				EmailProcess:{
					required:"Please Upload Email Process form", 
					filesize:"File Size should be <1MB"
				},
				SystemRecord:{
					required:"Please Upload the  form", 
					filesize:"File Size should be <1MB"
				},
				MailPosting:{
					required:"Please Upload mail posting", 
					filesize:"File Size should be <1MB"
				},
				EmployeeAgreement:{
					required:"Please Upload Employee Agreement",

					filesize:"File Size should be <1MB"
				},
			},
			errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}
		});
		
});

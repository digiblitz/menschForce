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
	
	$(document).on("click", "#close", function() {
	  if (confirm('Are you sure want to close ?')) {
			//$(this).prev('span.text').remove();
	  location.href="ListEmployeeDetails.html";
	  //alert("check");
		}
	});
	
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
	

	//On load start here------------------------------
	$(window).load(function(){
		$("#uploadAccess_fileForm").hide();
		$("#uploadCustomDocs1Form").hide();
		$("#uploadJobFormatForm").hide();
		$("#uploadStateDeptLabourForm").hide();
		$("#uploadNewsPaperForm").hide();
		$("#uploadOnlineJobPortalForm").hide();
		$("#uploadCommunicationDetForm").hide();
		$("#uploadCustomDocsStage5Form").hide();
		$("#uploadEducationQualifyForm").hide();
		$("#uploadExperienceSkillForm").hide();
		$("#uploadCustomDocsStage6Form").hide();
		$("#uploadAccessFileFormForm").hide();
		$("#uploadCustomDocsStage7Form").hide();
		$("#uploadPrevailingWageReportForm").hide();
		$("#uploadCustomDocsStage8Form").hide();
		$("#uploadPrevailingWageFormForm").hide();
		$("#uploadPayDocumentFormForm").hide();
		$("#uploadCustomDocsStage10Form").hide();
		
		$("#uploadResponseDocForm").hide();
		$("#uploadCustomDocsStage11Form").hide();
		$("#uploadH1BCandidateForm").hide();
		$("#uploadCustomDocsStage12Form").hide();
		$("#uploadLCAFormForm").hide();
		$("#uploadCustomDocsStage13Form").hide();
		$("#uploadPostingWorkFormForm").hide();
		$("#uploadCustomDocsStage14Form").hide();
		$("#uploadTakenPhotoForm").hide();
		$("#uploadSignClarificationForm").hide();
		$("#uploadCustomDocsStage15Form").hide();
		$("#uploadDayNotificationForm").hide();
		$("#uploadCustomDocsStage16Form").hide();
		$("#uploadTakenPhotoStage17Form").hide();
		$("#uploadSystemTakenForm").hide();
		$("#uploadLCAapprovedDocForm").hide();
		$("#uploadCustomDocsStage18Form").hide();
		$("#uploadEmailProcessForm").hide();
		$("#uploadSystemRecordForm").hide();
		$("#uploadMailPostingForm").hide();
		$("#uploadEmployeeAgreementForm").hide();
		$("#uploadCustomDocsStage20Form").hide();
		
		
		$("#uploadAccess_file").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadAccess_fileForm").show();
               
                $("#uploadAccess_fileCheck").val("uploadAccess_fileCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadAccess_fileForm").hide();
                $("#uploadAccess_fileCheck").val("");
            }
		
		});
		
		$("#uploadCustomDocs1").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadCustomDocs1Form").show();
               
                $("#uploadCustomDocs1Check").val("uploadCustomDocs1Check");
            }
            else if($(this).prop("checked") == false){
                $("#uploadCustomDocs1Form").hide();
                $("#uploadCustomDocs1Check").val("");
            }
		
		});
		
		$("#uploadJobFormat").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadJobFormatForm").show();
               
                $("#uploadJobFormatCheck").val("uploadJobFormatCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadJobFormatForm").hide();
                $("#uploadJobFormatCheck").val("");
            }
		
		});
		
		$("#uploadStateDeptLabour").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadStateDeptLabourForm").show();
               
                $("#uploadStateDeptLabourCheck").val("uploadStateDeptLabourCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadStateDeptLabourForm").hide();
                $("#uploadStateDeptLabourCheck").val("");
            }
		
		});
		
		$("#uploadNewsPaper").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadNewsPaperForm").show();
               
                $("#uploadNewsPaperCheck").val("uploadNewsPaperCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadNewsPaperForm").hide();
                $("#uploadNewsPaperCheck").val("");
            }
		
		});
		
		$("#uploadOnlineJobPortal").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadOnlineJobPortalForm").show();
               
                $("#uploadOnlineJobPortalCheck").val("uploadOnlineJobPortalCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadOnlineJobPortalForm").hide();
                $("#uploadOnlineJobPortalCheck").val("");
            }
		
		});
		
		$("#uploadCommunicationDet").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadCommunicationDetForm").show();
               
                $("#uploadCommunicationDetCheck").val("uploadCommunicationDetCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadCommunicationDetForm").hide();
                $("#uploadCommunicationDetCheck").val("");
            }
		
		});
		
		$("#uploadCustomDocsStage5").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadCustomDocsStage5Form").show();
               
                $("#uploadCustomDocsStage5Check").val("uploadCustomDocsStage5Check");
            }
            else if($(this).prop("checked") == false){
                $("#uploadCustomDocsStage5Form").hide();
                $("#uploadCustomDocsStage5Check").val("");
            }
		
		});
		
		$("#uploadEducationQualify").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadEducationQualifyForm").show();
               
                $("#uploadEducationQualifyCheck").val("uploadEducationQualifyCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadEducationQualifyForm").hide();
                $("#uploadEducationQualifyCheck").val("");
            }
		
		});
		
		$("#uploadExperienceSkill").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadExperienceSkillForm").show();
               
                $("#uploadExperienceSkillCheck").val("uploadExperienceSkillCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadExperienceSkillForm").hide();
                $("#uploadExperienceSkillCheck").val("");
            }
		
		});
		
		$("#uploadCustomDocsStage6").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadCustomDocsStage6Form").show();
               
                $("#uploadCustomDocsStage6Check").val("uploadCustomDocsStage6Check");
            }
            else if($(this).prop("checked") == false){
                $("#uploadCustomDocsStage6Form").hide();
                $("#uploadCustomDocsStage6Check").val("");
            }
		
		});
		
		$("#uploadAccessFileForm").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadAccessFileFormForm").show();
               
                $("#uploadAccessFileFormCheck").val("uploadAccessFileFormCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadAccessFileFormForm").hide();
                $("#uploadAccessFileFormCheck").val("");
            }
		
		});
		
		$("#uploadCustomDocsStage7").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadCustomDocsStage7Form").show();
               
                $("#uploadCustomDocsStage7Check").val("uploadCustomDocsStage7Check");
            }
            else if($(this).prop("checked") == false){
                $("#uploadCustomDocsStage7Form").hide();
                $("#uploadCustomDocsStage7Check").val("");
            }
		
		});
		
		$("#uploadPrevailingWageReport").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadPrevailingWageReportForm").show();
               
                $("#uploadPrevailingWageReportCheck").val("uploadPrevailingWageReportCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadPrevailingWageReportForm").hide();
                $("#uploadPrevailingWageReportCheck").val("");
            }
		
		});
		
		$("#uploadCustomDocsStage8").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadCustomDocsStage8Form").show();
               
                $("#uploadCustomDocsStage8Check").val("uploadCustomDocsStage8Check");
            }
            else if($(this).prop("checked") == false){
                $("#uploadCustomDocsStage8Form").hide();
                $("#uploadCustomDocsStage8Check").val("");
            }
		
		});
		
		$("#uploadPrevailingWageForm").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadPrevailingWageFormForm").show();
               
                $("#uploadPrevailingWageFormCheck").val("uploadPrevailingWageFormCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadPrevailingWageFormForm").hide();
                $("#uploadPrevailingWageFormCheck").val("");
            }
		
		});
		
		$("#uploadPayDocumentForm").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadPayDocumentFormForm").show();
               
                $("#uploadPayDocumentFormCheck").val("uploadPayDocumentFormCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadPayDocumentFormForm").hide();
                $("#uploadPayDocumentFormCheck").val("");
            }
		
		});
		
		$("#uploadCustomDocsStage10").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadCustomDocsStage10Form").show();
               
                $("#uploadCustomDocsStage10Check").val("uploadCustomDocsStage10Check");
            }
            else if($(this).prop("checked") == false){
                $("#uploadCustomDocsStage10Form").hide();
                $("#uploadCustomDocsStage10Check").val("");
            }
		
		});
		
		$("#uploadResponseDoc").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadResponseDocForm").show();
               
                $("#uploadResponseDocCheck").val("uploadResponseDocCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadResponseDocForm").hide();
                $("#uploadResponseDocCheck").val("");
            }
		
		});
		
		$("#uploadCustomDocsStage11").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadCustomDocsStage11Form").show();
               
                $("#uploadCustomDocsStage11Check").val("uploadCustomDocsStage11Check");
            }
            else if($(this).prop("checked") == false){
                $("#uploadCustomDocsStage11Form").hide();
                $("#uploadCustomDocsStage11Check").val("");
            }
		
		});
		
		$("#uploadH1BCandidate").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadH1BCandidateForm").show();
               
                $("#uploadH1BCandidateCheck").val("uploadH1BCandidateCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadH1BCandidateForm").hide();
                $("#uploadH1BCandidateCheck").val("");
            }
		
		});
		
		$("#uploadCustomDocsStage12").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadCustomDocsStage12Form").show();
               
                $("#uploadCustomDocsStage12Check").val("uploadCustomDocsStage12Check");
            }
            else if($(this).prop("checked") == false){
                $("#uploadCustomDocsStage12Form").hide();
                $("#uploadCustomDocsStage12Check").val("");
            }
		
		});
		
		$("#uploadLCAForm").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadLCAFormForm").show();
               
                $("#uploadLCAFormCheck").val("uploadLCAFormCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadLCAFormForm").hide();
                $("#uploadLCAFormCheck").val("");
            }
		
		});
		
		$("#uploadCustomDocsStage13").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadCustomDocsStage13Form").show();
               
                $("#uploadCustomDocsStage13Check").val("uploadCustomDocsStage13Check");
            }
            else if($(this).prop("checked") == false){
                $("#uploadCustomDocsStage13Form").hide();
                $("#uploadCustomDocsStage13Check").val("");
            }
		
		});
		
		$("#uploadPostingWorkForm").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadPostingWorkFormForm").show();
               
                $("#uploadPostingWorkFormCheck").val("uploadPostingWorkFormCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadPostingWorkFormForm").hide();
                $("#uploadPostingWorkFormCheck").val("");
            }
		
		});
		
		$("#uploadCustomDocsStage14").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadCustomDocsStage14Form").show();
               
                $("#uploadCustomDocsStage14Check").val("uploadCustomDocsStage14Check");
            }
            else if($(this).prop("checked") == false){
                $("#uploadCustomDocsStage14Form").hide();
                $("#uploadCustomDocsStage14Check").val("");
            }
		
		});
		
		$("#uploadTakenPhoto").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadTakenPhotoForm").show();
               
                $("#uploadTakenPhotoCheck").val("uploadTakenPhotoCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadTakenPhotoForm").hide();
                $("#uploadTakenPhotoCheck").val("");
            }
		
		});
		
		$("#uploadSignClarification").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadSignClarificationForm").show();
               
                $("#uploadSignClarificationCheck").val("uploadSignClarificationCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadSignClarificationForm").hide();
                $("#uploadSignClarificationCheck").val("");
            }
		
		});
		
		$("#uploadCustomDocsStage15").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadCustomDocsStage15Form").show();
               
                $("#uploadCustomDocsStage15Check").val("uploadCustomDocsStage15Check");
            }
            else if($(this).prop("checked") == false){
                $("#uploadCustomDocsStage15Form").hide();
                $("#uploadCustomDocsStage15Check").val("");
            }
		
		});
		
		$("#uploadDayNotification").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadDayNotificationForm").show();
               
                $("#uploadDayNotificationCheck").val("uploadDayNotificationCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadDayNotificationForm").hide();
                $("#uploadDayNotificationCheck").val("");
            }
		
		});
		
		$("#uploadCustomDocsStage16").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadCustomDocsStage16Form").show();
               
                $("#uploadCustomDocsStage16Check").val("uploadCustomDocsStage16Check");
            }
            else if($(this).prop("checked") == false){
                $("#uploadCustomDocsStage16Form").hide();
                $("#uploadCustomDocsStage16Check").val("");
            }
		
		});
		
		$("#uploadTakenPhotoStage17").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadTakenPhotoStage17Form").show();
               
                $("#uploadTakenPhotoStage17Check").val("uploadTakenPhotoStage17Check");
            }
            else if($(this).prop("checked") == false){
                $("#uploadTakenPhotoStage17Form").hide();
                $("#uploadTakenPhotoStage17Check").val("");
            }
		
		});
		
		$("#uploadSystemTaken").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadSystemTakenForm").show();
               
                $("#uploadSystemTakenCheck").val("uploadSystemTakenCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadSystemTakenForm").hide();
                $("#uploadSystemTakenCheck").val("");
            }
		
		});
		
		$("#uploadLCAapprovedDoc").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadLCAapprovedDocForm").show();
               
                $("#uploadLCAapprovedDocCheck").val("uploadLCAapprovedDocCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadLCAapprovedDocForm").hide();
                $("#uploadLCAapprovedDocCheck").val("");
            }
		
		});
		
		$("#uploadCustomDocsStage18").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadCustomDocsStage18Form").show();
               
                $("#uploadCustomDocsStage18Check").val("uploadCustomDocsStage18Check");
            }
            else if($(this).prop("checked") == false){
                $("#uploadCustomDocsStage18Form").hide();
                $("#uploadCustomDocsStage18Check").val("");
            }
		
		});
		
		$("#uploadEmailProcess").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadEmailProcessForm").show();
               
                $("#uploadEmailProcessCheck").val("uploadEmailProcessCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadEmailProcessForm").hide();
                $("#uploadEmailProcessCheck").val("");
            }
		
		});
		
		$("#uploadSystemRecord").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadSystemRecordForm").show();
               
                $("#uploadSystemRecordCheck").val("uploadSystemRecordCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadSystemRecordForm").hide();
                $("#uploadSystemRecordCheck").val("");
            }
		
		});
		
		$("#uploadMailPosting").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadMailPostingForm").show();
               
                $("#uploadMailPostingCheck").val("uploadMailPostingCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadMailPostingForm").hide();
                $("#uploadMailPostingCheck").val("");
            }
		
		});
		
		$("#uploadEmployeeAgreement").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadEmployeeAgreementForm").show();
               
                $("#uploadEmployeeAgreementCheck").val("uploadEmployeeAgreementCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadEmployeeAgreementForm").hide();
                $("#uploadEmployeeAgreementCheck").val("");
            }
		
		});
		
		$("#uploadCustomDocsStage20").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadCustomDocsStage20Form").show();
               
                $("#uploadCustomDocsStage20Check").val("uploadCustomDocsStage20Check");
            }
            else if($(this).prop("checked") == false){
                $("#uploadCustomDocsStage20Form").hide();
                $("#uploadCustomDocsStage20Check").val("");
            }
		
		});
		
		
		
		
		
		
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
		
//On load end here------------------------------		

		
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

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
	
	$(window).load(function(){
		$("#uploadNewContractForm").hide();
		$("#uploadNewEmploymentProofForm").hide();
		$("#uploadNewClientLetterForm").hide();
		$("#uploadNewWorkOrderForm").hide();
		$("#uploadNewLabourClearanceForm").hide();
		$("#uploadNewCustomDocsform").hide();
		$("#uploadFormI_129Form").hide();
		$("#uploadCustomDocsStage2Form").hide();
		$("#uploadPostingI_129Form").hide();
		$("#uploadonBoardingForm").hide();
		$("#uploadCustomDocsStage5Form").hide();
		$("#uploadForm1I_94Form").hide();
		$("#uploadFormI_9Form").hide();
		$("#uploadContractDocForm").hide();
		$("#uploadTimesheetDocForm").hide();
		$("#uploadClientDocForm").hide();
		$("#uploadWeeklyReviewDocForm").hide();
		
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
		
		
	$("#uploadContractDoc").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadContractDocForm").show();
               
                $("#uploadContractDocCheck").val("uploadContractDocCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadContractDocForm").hide();
                $("#uploadContractDocCheck").val("");
            }
		
	});
	$("#uploadTimesheetDoc").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadTimesheetDocForm").show();
               
                $("#uploadTimesheetDocCheck").val("uploadTimesheetDocCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadTimesheetDocForm").hide();
                $("#uploadTimesheetDocCheck").val("");
            }
		
	});
	$("#uploadClientDoc").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadClientDocForm").show();
               
                $("#uploadClientDocCheck").val("uploadClientDocCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadClientDocForm").hide();
                $("#uploadClientDocCheck").val("");
            }
		
	});
	$("#uploadWeeklyReviewDoc").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadWeeklyReviewDocForm").show();
               
                $("#uploadWeeklyReviewDocCheck").val("uploadWeeklyReviewDocCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadWeeklyReviewDocForm").hide();
                $("#uploadWeeklyReviewDocCheck").val("");
            }
		
	});
	$("#uploadForm1I_94").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadForm1I_94Form").show();
               
                $("#uploadForm1I_94Check").val("uploadForm1I_94Check");
            }
            else if($(this).prop("checked") == false){
                $("#uploadForm1I_94Form").hide();
                $("#uploadForm1I_94Check").val("");
            }
		
	});
	$("#uploadFormI_9").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadFormI_9Form").show();
               
                $("#uploadFormI_9Check").val("uploadFormI_9Check");
            }
            else if($(this).prop("checked") == false){
                $("#uploadFormI_9Form").hide();
                $("#uploadFormI_9Check").val("");
            }
		
	});
	$("#uploadonBoarding").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadonBoardingForm").show();
               
                $("#uploadonBoardingCheck").val("uploadonBoardingCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadonBoardingForm").hide();
                $("#uploadonBoardingCheck").val("");
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
	$("#uploadPostingI_129").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadPostingI_129Form").show();
               
                $("#uploadPostingI_129Check").val("uploadPostingI_129Check");
            }
            else if($(this).prop("checked") == false){
                $("#uploadPostingI_129Form").hide();
                $("#uploadPostingI_129Check").val("");
            }
		
	});
	$("#uploadNewContract").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadNewContractForm").show();
               
                $("#ContractFormCheck").val("ContractFormCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadNewContractForm").hide();
                $("#ContractFormCheck").val("");
            }
		
	});
	
	$("#uploadNewEmploymentProof").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadNewEmploymentProofForm").show();
               
                $("#EmploymentProofCheck").val("EmploymentProofCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadNewEmploymentProofForm").hide();
                $("#EmploymentProofCheck").val("");
            }
		
	});
	
	$("#uploadNewClientLetter").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadNewClientLetterForm").show();
               
                $("#ClientLetterCheck").val("ClientLetterCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadNewClientLetterForm").hide();
                $("#ClientLetterCheck").val("");
            }
		
	});
	
	$("#uploadNewWorkOrder").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadNewWorkOrderForm").show();
               
                $("#WorkOrderCheck").val("WorkOrderCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadNewWorkOrderForm").hide();
                $("#WorkOrderCheck").val("");
            }
		
	});
	
	$("#uploadNewLabourClearance").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadNewLabourClearanceForm").show();
               
                $("#LabourClearanceCheck").val("LabourClearanceCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadNewLabourClearanceForm").hide();
                $("#LabourClearanceCheck").val("");
            }
		
	});
	
	$("#uploadNewCustomDocs").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadNewCustomDocsform").show();
               
                $("#CustomDocsCheck").val("CustomDocsCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadNewCustomDocsform").hide();
                $("#CustomDocsCheck").val("");
            }
		
	});
	
	$("#uploadFormI_129").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadFormI_129Form").show();
               
                $("#FormI_129Check").val("FormI_129Check");
            }
            else if($(this).prop("checked") == false){
                $("#uploadFormI_129Form").hide();
                $("#FormI_129Check").val("");
            }
		
	});
	
	$("#uploadCustomDocsStage2").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadCustomDocsStage2Form").show();
               
                $("#CustomDocsStage2Check").val("CustomDocsStage2Check");
            }
            else if($(this).prop("checked") == false){
                $("#uploadCustomDocsStage2Form").hide();
                $("#CustomDocsStage2Check").val("");
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

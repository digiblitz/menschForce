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
          if (charCode != 46 && charCode > 31 
            && (charCode < 48 || charCode > 57))
             return false;

          return true;
	}

$(document).ready(function(){
	
	/* special character validation */
		 $.validator.addMethod(
			"checkspecialcharacter",
			function(value, element) {
				// put your own logic here, this is just a (crappy) example
				return value.match(/^[a-zA-Z\s]+$/);
			},
			"Special characters are not accepted"
			);
			
	/* email validation */
		$.validator.addMethod("emailvalidate", 
			function(value, element) {
				return /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
			}, 
			"Please Enter the valid Email"
		);
		
		
/*password validation*/
		$.validator.addMethod("Pwd", function(value) {
	
			return /^[A-Za-z0-9\d=!\-@._*]*$/.test(value) // consists of only these
			&& /[a-z]/.test(value)
			&& /[A-Z]/.test(value)
			&&/[!,%,&,@,#,$,^,*,?,_,~,]/.test(value)	  
			&& /\d/.test(value)
			&& /\S{8,}/.test(value);
		});
			
		
	/*Change password === frmChangePassword.jsp*/
$('form[name="changePassword"]').validate({ 

	rules:{
		
		newPwd:{
			required:true,
			Pwd:true
		},
		
		currPwd:"required",
		
		reNewPwd:{
			required:true,
			equalTo: "#newPwd"
		},
	},
	
	messages:{
		newPwd:{
			required:"Please enter the new password",
		    Pwd:"Your password must contain at least 1 uppercase, 1 lowercase, 1 specialcharacter and 1 number"
			},
		currPwd:"Please enter valid current password",
		reNewPwd:"Password mismatched",
	},
	
});

/*Edit page=== frmEditMemberUserSignUp.jsp*/

$('form[name="frmMembRegi"]').validate({ 

	rules:{
		email:{
			required:true,
			emailvalidate:true
		},
		QSelect:"required",
		ans:"required",
		padd_txt:"required",
		pcity_txt:"required",
		pstate_sel:"required",
		pzip_txt:"required",
		pcountry_sel:"required",
		pphone_txt:"required",
		sadd_txt:"required",
		scity_txt:"required",
		sState_sel:"required",
		szip_txt:"required",
		sCountry_sel:"required",
		sphone_txt:"required",
		
	},
	messages:{
		email:{
			required:"Please enter the new password",
			emailvalidate:"Please enter the valid email"
		},
		QSelect:"Please enter the current password",
		ans:"Please retype the new password",
		padd_txt:"Please enter the address",
		pcity_txt:"Please enter the city",
		pstate_sel:"Please select the state",
		pzip_txt:"Please enter the zip code",
		pcountry_sel:"Please select the country",
		pphone_txt:"Please enter the phone number",
		sadd_txt:"Please enter the address",
		scity_txt:"Please enter the city",
		sState_sel:"Please select the state",
		szip_txt:"Please enter the zip code",
		sCountry_sel:"Please select the country",
		sphone_txt:"Please enter the phone number",
		
	},
	
	errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}
	
		});
	/*create permission===  frmRolMCreatePermission.jsp*/

$('form[name="frmRolMCreatePermission"]').validate({ 

	rules:{
		permissionName:"required"
	},
	messages:{
		permissionName:"Please enter the permission name"
	}


});

/*create entity=== frmRolMCreateEntity.jsp*/

$('form[name="frmRoleMgtEntityCreate"]').validate({ 

	rules:{
		entityName:"required"
	},
	messages:{
		entityName:"Please enter the entity name"
	}


});

/*create role=== frmRolMCreateRole.jsp*/

$('form[name="frmRoleMgtRolePrev"]').validate({ 

	rules:{
		rolename:"required",
		roledesc:"required",
		status:"required"
	},
	messages:{
		rolename:"Please enter the role name",
		roledesc:"please enter the description",
		status:"Please select the status"
	},
	errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}

});

/*search customer=== customerSearchPage.jsp*/

$('form[name="customerSearch"]').validate({ 

	rules:{
		customerId:"required",
		
	},
	messages:{
		customerId:"Please enter the customer ID",
		
	},
	

});

/* create lifecycle process === SystinetArtifactadd.jsp*/

$("#txtLifeProcees").blur(function(){    
 
   var c = $("#txtLifeProcees").val();
   var d = c.trim();
   $("#txtLifeProcees").val(d);
    });	
	
$("#txtstagename").blur(function(){    
   
   var c = $("#txtstagename").val();
   var d = c.trim();
   $("#txtstagename").val(d);
    });	

	$("#txtInput").blur(function(){    
   
   var c = $("#txtInput").val();
   var d = c.trim();
   $("#txtInput").val(d);
    });	
	
$("#txtprocess").blur(function(){    

   var c = $("#txtprocess").val();
   var d = c.trim();
   $("#txtprocess").val(d);
    });

$("#txtOutput").blur(function(){    

   var c = $("#txtOutput").val();
   var d = c.trim();
   $("#txtOutput").val(d);
    });	
$('form[name="frmlifecycleproc"]').validate({ 

	rules:{
		frmDate:"required",
		SelArtifacttype:"required",
		txtLifeProcees:{
			required:true,
			checkspecialcharacter:true,
			maxlength:30
		},
		selNolifeStage:"required",
		selStages:"required",
		txtstagename:{
			required:true,
			checkspecialcharacter:true,
			maxlength:30
		},
		txtInput:{
			required:true,
			checkspecialcharacter:true,
			maxlength:30
		},
		txtprocess:{
			required:true,
			checkspecialcharacter:true,
			maxlength:30
		},
		txtDesc:"required",
		selControls:"required",
		selVotes:"required",
		txtOutput:{
			required:true,
			checkspecialcharacter:true,
			maxlength:30
		}
		
	},
	messages:{
		frmDate:"Please select the date",
		SelArtifacttype:"Please select the type",
		txtLifeProcees:{
			required:"Please enter the life process",
			checkspecialcharacter:"Only Alphabets Allowed",
			maxlength:"Length should be <30"
		},
		selNolifeStage:"Please select the lifecycle stage",
		selStages:"please select the stage",
		txtstagename:{
			required:"Please enter the stage name",
			checkspecialcharacter:"Only Alphabets Allowed",
			maxlength:"Length should be <30"
		},
		txtInput:{
			required:"Please enter the input",
			checkspecialcharacter:"Only Alphabets Allowed",
			maxlength:"Length should be <30"
		},
			
		txtprocess:{
			required:"Please enter the process",
			checkspecialcharacter:"Only Alphabets Allowed",
			maxlength:"Length should be <30"
		},
		txtDesc:"Please enter the description",
		selControls:"Please select the controls",
		selVotes:"Please select the votes",
		txtOutput:{
			required:"Please enter the output",
			checkspecialcharacter:"Only Alphabets Allowed",
			maxlength:"Length should be <30"
		},
		
	},
	

});

/*Add group in artifact=== frmAddGroups.jsp*/

$('form[name="frmAddListgroups"]').validate({ 

	rules:{
		masterId:"required",
		groupDet:"required",
		
	},
	messages:{
		masterId:"Please enter the detail",
		groupDet:"Please select the group"
		
	},
	

});
});

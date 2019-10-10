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
/*function FillCountry(cboCountry, cboState, sDefaultCountry){
var sDefaultCountry, sDefault, sCountry,t=0;

cboCountry.options.length=0;
for(c=0;c<sCountryString.split("|").length;c++){
sCountry = sCountryString.split("|")[c];

if (sDefaultCountry == sCountry) {
	sDefault=true;
	t = c;
}
else {
	sDefault=false;
}

if (sDefault) {   
    cboCountry.options[c]=new Option(sCountry,sCountry,true,true); 
}
else { 
    cboCountry.options[c]=new Option(sCountry,sCountry,false,false);
}
} 
cboCountry.options[t].selected = true;
}

function FillState(cboCountry, cboState, sDefaultState){
var sDefaultState, sState, sDefault,t=0;
cboState.options.length=0
for(s=0;s<sStateArray.split("|").length;s++){
sState = sStateArray.split("|")[s];

if((sDefaultState == sState)) {
sDefault=true;
t = s;
}
else {sDefault=false;}

if(sDefault) {
    cboState.options[s]=new Option(sState,sState,true,true); 
}    
else {
    cboState.options[s]=new Option(sState,sState,false,false);
}
}
cboState.options[t].selected = true;

}

var sCountryString = "---Select---|USA"

var sStateArray="---Select---|Alabama|Alaska|Arizona|Arkansas|Armed Services (AA)|Armed Services (AE)|Armed Services (AP)|California|Colorado|Connecticut|Delaware|District of Columbia|Florida|Georgia|Hawaii|Idaho|Illinois|Indiana|Iowa|Kansas|Kentucky|Louisiana|Maine|Maryland|Massachusetts|Michigan|Minnesota|Mississippi|Missouri|Montana|Nebraska|Nevada|New Hampshire|New Jersey|New Mexico|New York|North Carolina|North Dakota|Ohio|Oklahoma|Oregon|Pennsylvania|Puerto Rico|Rhode Island|South Carolina|South Dakota|Tennessee|Texas|Utah|Vermont|Virginia|Virgin Islands|Washington|West Virginia|Wisconsin|Wyoming"
*/
$(document).ready(function(){
	
	$(document).on("click", "#close", function() {
	    if (confirm('Are you sure want to close ?')) {
	    //$(this).prev('span.text').remove();
	    location.href="ListEmployeeDetails.html";
	    //alert("check");
	   }
	  });
	
	$('input[name="submit"]').click(function(){
		
		  if($("#fileupload").val() == "" || $("#fileupload").val() == "null"||$("#fileupload").val() == null){
			$("#customuploadnew").prop('checked',false);
			
		  }
		 });
	
	
	$("#uploadfederalnew").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadnewfederalform").show();
                
                $("#uploadfederalnewCheck").val("uploadfederalnewCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadnewfederalform").hide();
                $("#uploadfederalnewCheck").val("");
            }
		
	});
	$("#uploadstatenew").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#uploadnewstateform").show();
                
                $("#uploadstatenewCheck").val("uploadstatenewCheck");
            }
            else if($(this).prop("checked") == false){
                $("#uploadnewstateform").hide();
                $("#uploadstatenewCheck").val("");
            }
		
	});
	$("#customuploadnew").click(function(){
		//alert("check");
		 if($(this).prop("checked") == true){
                $("#customuploadnewform").show();
                
                $("#customuploadnewCheck").val("customuploadnewCheck");
            }
            else if($(this).prop("checked") == false){
                $("#customuploadnewform").hide();
                $("#customuploadnewCheck").val("");
            }
		
	});
	
	$(window).load(function(){
		//alert("check");
		$("#uploadnewfederalform").hide();
		$("#uploadnewstateform").hide();
		$("#customuploadnewform").hide();
	});
	
$.validator.addMethod(
			"uploadFile1",
			function(value, element) {
				
			 var size = element.files[0].size;
				
				if (size < 1000000){
				return true;
				}
			},
			"File size must be < 1MB"
			);

	$("#form3").validate({
	
		rules: {
		
		fileupload:{
					required: true,
					extension:"jpeg|txt|docx|doc|pdf|gif|png",						 
					uploadFile1:true
			},
		
		fileupload1:{
			
					required: true,
					 extension:"jpeg|txt|docx|doc|pdf|gif|png",					
					uploadFile1:true
				}
		},
	
		messages:{
			fileupload:{
				required:"please upload a file",
				uploadFile1:"File size must be < 1MB",
				extension:" Accept file format .jpeg, .gif, .doc, .docx ,.png or .pdf"
			},

			fileupload1:{
				required:"please upload a  file",
				uploadFile1:"File size must be < 1MB",
				extension:" Accept file format .jpeg, .gif, .doc, .docx ,.png or .pdf"
				
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


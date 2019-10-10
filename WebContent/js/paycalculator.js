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
	
	var federal = "";
	function federalCalifornia(id){
		//alert("Check");
		var federal = id.value;
		//alert(federal);
		if(federal == "no" || federal == "NO"  ){
			document.getElementById("CA_federal_no").style.display = "block";
			document.getElementById("CA_federal_yes").style.display = "none";
		}else{
			document.getElementById("CA_federal_no").style.display = "none";
			document.getElementById("CA_federal_yes").style.display = "block";
		}
	}
	
	function federalGeorgia(id){
		var federalGA = id.value;
		
		if(federalGA == "no" || federalGA == "No"  ){
			document.getElementById("GA_federal_no").style.display = "block";
			document.getElementById("GA_federal_yes").style.display = "none";
		}else{
			document.getElementById("GA_federal_no").style.display = "none";
			document.getElementById("GA_federal_yes").style.display = "block";
		}
	}
	
	function addIncomeAmount(id){
		var maritalstatus = id.value;
		//alert(maritalstatus);
		if( maritalstatus == "spouse working"){
			//alert("insode");
			document.getElementById("dual-income").style.display = "block";
		}else{
			document.getElementById("dual-income").style.display = "none";
		}
	}
	
	
	
	function overtimewagemore(rate_ot,type_ot,hour_ot,cp_ot){
		
		var rate_ot = rate_ot.value;
		var payrate_ot = rate_ot.replace('$','');
		var totalhour_ot = hour_ot.value;
		var type_ot1 = type_ot.value;
		//if(type_ot1 == "Hourly" ||){
		//	hour_ot.style.display = "block";
		//}else{hour_ot.style.display = "none";}
		
		if(payrate_ot != "" & totalhour_ot != "" & type_ot1 == "Hourly" ){
			
		var totalwage_ot = parseFloat(payrate_ot)* parseFloat(totalhour_ot);
		//alert("cheking 2 id==="+payrate_ot+"===="+totalhour_ot);
		cp_ot.value = totalwage_ot.toFixed(2);
		
		}
		if(payrate_ot != "" & totalhour_ot != "" & type_ot1 == "Annually" ){
			var totalwage_ot =(parseFloat(payrate_ot)/2080)*parseFloat(totalhour_ot);
			cp_ot.value = totalwage_ot.toFixed(2);
		}
		if(payrate_ot != "" & type_ot1 == "Monthly" & totalhour_ot != ""){
			
			var totalwage_ot = (parseFloat(payrate_ot)/173.33)*parseFloat(totalhour_ot);
			cp_ot.value = totalwage_ot.toFixed(2);
		}
		if(payrate_ot != "" & type_ot1 == "Weekly" & totalhour_ot != ""){
			
			var totalwage_ot = (parseFloat(payrate_ot)/40)*parseFloat(totalhour_ot);
			cp_ot.value = totalwage_ot.toFixed(2);
		}
	}
	
	
	
	
	function FillState( cboState, sDefaultState){
		
		var sStateArray="---Select---|Alabama|Alaska|Arizona|Arkansas|California|Colorado|Connecticut|Delaware|District of Columbia|Florida|Georgia|Hawaii|Idaho|Illinois|Indiana|Iowa|Kansas|Kentucky|Louisiana|Maine|Maryland|Massachusetts|Michigan|Minnesota|Mississippi|Missouri|Montana|Nebraska|Nevada|New Hampshire|New Jersey|New Mexico|New York|North Carolina|North Dakota|Ohio|Oklahoma|Oregon|Pennsylvania|Rhode Island|South Carolina|South Dakota|Tennessee|Texas|Utah|Vermont|Virginia|Washington|West Virginia|Wisconsin|Wyoming"
			var sDefaultState, sState, sDefault ,t=0;
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
			
	
	var counter = 0;
	 function checkcount(val ){
	  
	  var currrow = val.value;
	  //alert(currrow);
	  //var size = $("#overtimecount").size();
	  //alert("check"+size);
	  
	  var totalcount = document.getElementById("overtimecount").value;
	  
	  for( i=currrow ; i<=totalcount ; i++){
	   //alert("inside loop");
	   var x = parseInt(i);
	   var w = parseInt(totalcount);
	   var s = parseInt(i)+1;
	   //alert("totalcount===" + totalcount +"===currentcount===="+i+"===="+s);
	   if( x == w){
	    //alert("inside if");
		     $('#overtimerow').children().children().last().remove();
		     
				 counter = parseInt(totalcount)-1;
			 
		     document.getElementById("overtimecount").value=counter;
		    
			
			 
		    }else{
		     //alert("check again");
		    
		    var overtimerate = document.getElementById("overtimerate"+s).value;
		    var overtimepaytype = document.getElementById("overtimepaytype"+s).value;
		    var ytd = document.getElementById("ytd_ot"+s).value ;
		    var cp = document.getElementById("cp_ot"+s).value;
		    var hour =  document.getElementById("Hour_ot"+s).value;
		     
		    document.getElementById("overtimerate"+i).value = overtimerate;
		    document.getElementById("overtimepaytype"+i).value = overtimepaytype;
		    document.getElementById("Hour_ot"+i).value = hour;
		    document.getElementById("ytd_ot"+i).value = ytd;
		    document.getElementById("cp_ot"+i).value = cp;
		    		   
		   } 

		  }
		 
		 }
			
	
	$(document).ready(function(){
		
		$("#Hourly_payrate").change(function(){
			
			var rate_ot = $("#Hourly_payrate").val();
			var payrate_ot = rate_ot.replace('$','');
			var totalcount = $("#overtimecount").val();
			
			 for( i=0 ; i<=totalcount ; i++){
			   
			   var x = parseInt(i);
			   
				var type_ot1 = document.getElementById("overtimepaytype"+i).value;
				var cp_ot = document.getElementById("cp_ot"+i).value;
				var totalhour_ot =  document.getElementById("Hour_ot"+i).value;
				
				if(payrate_ot != "" & totalhour_ot != "" & type_ot1 == "Hourly" ){
			
				var totalwage_ot = parseFloat(payrate_ot)* parseFloat(totalhour_ot);
				document.getElementById("cp_ot"+i).value = totalwage_ot.toFixed(2);
				
				}
				if(payrate_ot != "" & totalhour_ot != "" & type_ot1 == "Annually" ){
					var totalwage_ot =(parseFloat(payrate_ot)/2080)*parseFloat(totalhour_ot);
					document.getElementById("cp_ot"+i).value = totalwage_ot.toFixed(2);
				}
				if(payrate_ot != "" & type_ot1 == "Monthly" & totalhour_ot != ""){
					
					var totalwage_ot = (parseFloat(payrate_ot)/173.33)*parseFloat(totalhour_ot);
					document.getElementById("cp_ot"+i).value = totalwage_ot.toFixed(2);
				}
				if(payrate_ot != "" & type_ot1 == "Weekly" & totalhour_ot != ""){
					
					var totalwage_ot = (parseFloat(payrate_ot)/40)*parseFloat(totalhour_ot);
					document.getElementById("cp_ot"+i).value = totalwage_ot.toFixed(2);
				}
				
			 }
		});
		
		$(document).on("click", "#close", function() {
			if (confirm('Are you sure want to close ?')) {
			//$(this).prev('span.text').remove();
			location.href="ListEmployeeDetails.html";
			//alert("check");
		   }
		  });
		  
		  $(document).on("click", "#back", function() {
			if (confirm('Are you sure want to go back ?')) {
			//$(this).prev('span.text').remove();
				//location.href="ListEmployeeDetails.html";
				window.history.back();
			//alert("check");
		   }
		  });
		  
		  $(document).on("click", "#delete", function() {
			if (confirm('Are you sure want to delete the payroll item?')) {
			//$(this).prev('span.text').remove();
			location.href="ListEmployeeDetails.html";
			//alert("check");
		   }
		  });
		
		/* Check whether the annual / semi-annual / Quarterly period selected in Wisconsin state*/
	      $(window).click(function(){
	    
	    var StateCheck = $("#state").val();
	    
	    var PayPeriodval = $("#PayPeriod").val();
	    
	    if(StateCheck != null && StateCheck == "Wisconsin"){
	     
	     if(PayPeriodval != null && (PayPeriodval == "Quarterly"|| PayPeriodval == "Semiannually" || PayPeriodval == "Annually")){
	      
	     $("#errorperiod").text("Wisconsin does not allow Quarterly, Semi-Annually, Annually withholding payperiods. Please select another option and re-calculate.");
	     
	     $('#PayPeriod').attr('value', '');
	     }else{
	      $("#errorperiod").text('');
	      }
	     
	    }
	    else if(StateCheck != null && StateCheck == "Vermont"){
	      if(PayPeriodval != null &&  PayPeriodval == "Semiannually" ){
	      
	      $("#errorperiod").text("Vermont does not allow Semi-Annual withholding payperiod. Please select another option and re-calculate.");
	      
	      $('#PayPeriod').attr('value', '');
	      }else{
	       $("#errorperiod").text('');
	       }
	     
	    }else if(StateCheck != null && (StateCheck == "Montana" || StateCheck == "West Virginia" || StateCheck == "Delaware" || StateCheck == "Oregon" || StateCheck == "Ohio" || StateCheck == "New york" || StateCheck == "Colorado"  ||  StateCheck == "Massachusetts")){
						
	        if(PayPeriodval != null && (PayPeriodval == "Quarterly" || PayPeriodval == "Semiannually" )){
	            
	            $("#errorperiod").text("State does not allow Quarterly and Semi-Annual payperiod. Please select another option and re-calculate.");
	            
	            $('#PayPeriod').attr('value', '');
	            }else{
	             $("#errorperiod").text('');
	             }
	        
	       }
		 
	    
	      });
		
		var stateload = null;
		var VI_NofExemptions = null;
		
		$(window).load(function(){
			
			//SSNnumber = document.getElementById("SSNnumber").innerHTML; 
			//alert(SSNnumber);
			//str = SSNnumber.replace(/\d(?=\d{4})/g, "*");
			//document.getElementById("SSNnumber").innerHTML = str ; 
			
			 stateload = $("#state").val(); 
			 VI_NofExemptions = $("#VI_NofExemptions").val();
			 VI_age_exemptions = $("#VI_age_exemptions").val();
			 VI_additional_withholding = $("#VI_additional_withholding").val();
			 
			 personal_allowances = $("#personal_allowances").val();
			 dependent_allowances = $("#dependent_allowances").val();
			 filing_status = $("#filing_status").val();
			 total_allowances = $("#total_allowances").val();
			 allowances = $("#allowances").val();
			 additional_state_withholding = $("#additional_state_withholding").val();
			 additional_allowances = $("#additional_allowances").val();
			 texarna_resident = $("#texarna_resident").val();
			 withholding_code = $("#withholding_code").val();
			 basic_allowances = $("#basic_allowances").val();
			 personal_deduction = $("#personal_deduction").val();
			 dependent_deduction = $("#dependent_deduction").val();
			 WHExemptions = $("#WHExemptions").val();
			 rate_code = $("#rate_code").val();
			 SpouseBlind = $("#spouse_blind").val();
			 PersonalBlind = $("#personal_blind").val();
			 HouseHold = $("#house_hold").val();
			 Student = $("#student").val();
			 
			 withholding_rate = $("#withholding_rate").val();
			 exemption_amount = $("#exemption_amount").val();
			 extended_allowances = $("#extended_allowances").val();
			 regular_allowances = $("#regular_allowances").val();
			 district_rate = $("#district_rate").val();
			 district_no = $("#district_no").val();

			 
			if (stateload != "" & stateload != null){
				
				for(s=0;s<sStateArray1.split("|").length;s++){	
					sState = sStateArray1.split("|")[s];
						
						if(stateload == sStateArray1.split("|")[s]){
							var stateform = statelist[s];
							jQuery("#state_form").empty().append(stateform);
							var setResultVal = showStateWithholdvalue();
							
						}	
							
				}
			}
			
		});
		
		showStateWithholdvalue = function(){ 
		 
			//alert("new function");
			$('input[name="VI_NofExemptions"]').val(VI_NofExemptions);
			$('input[name="VI_age_exemptions"]').val(VI_age_exemptions);
			$('input[name="VI_additional_withholding"]').val(VI_additional_withholding);
			
			$('input[name="personal_allowances"]').val(personal_allowances);
			$('input[name="dependent_allowances"]').val(dependent_allowances);
			$('select[name="filing_status"]').val(filing_status);
			$('input[name="total_allowances"]').val(total_allowances);
			$('input[name="allowances"]').val(allowances);
			$('input[name="additional_state_withholding"]').val(additional_state_withholding);
			$('input[name="additional_allowances"]').val(additional_allowances);
			$('select[name="texarna_resident"]').val(texarna_resident);
			$('select[name="withholding_code"]').val(withholding_code);
			$('input[name="basic_allowances"]').val(basic_allowances);
			$('input[name="personal_deduction"]').val(personal_deduction);
			$('input[name="dependent_deduction"]').val(dependent_deduction);
			$('input[name="WHExemptions"]').val(WHExemptions);
			$('select[name="rate_code"]').val(rate_code);
			
			$('select[name="withholding_rate"]').val(withholding_rate);
			$('input[name="exemption_amount"]').val(exemption_amount);
			$('input[name="regular_allowances"]').val(regular_allowances);
			$('input[name="extended_allowances"]').val(extended_allowances);
			$('select[name="district_rate"]').val(district_rate);
			$('input[name="district_no"]').val(district_no);
			
			if(SpouseBlind != null && SpouseBlind != "null"){
				
				$('input[name="spouse_blind"]').prop("checked",true);
			}
			if(PersonalBlind != null && PersonalBlind != "null"){
				
				$('input[name="personal_blind"]').prop("checked",true);
			}
			if(HouseHold != null && HouseHold != "null"){
				
				$('input[name="house_hold"]').prop("checked",true);
			}
			if(Student != null && Student != "null"){
				
				$('input[name="student"]').prop("checked",true);
			}
		 }
		
		
		var counter = 0;
	$("#addrow").click(function(){
		//alert(counter);
		//event.preventDefault();
		counter = $("#overtimecount").val();
    counter++;
	var newrow =jQuery('<div class="row"><div class="col-lg-12 col-md-12 col-sm-12"><div class="col-lg-1 col-md-1 col-sm-1">&nbsp;</div>'
	            +'<div class="col-lg-2 col-md-2 col-sm-2"><input type="text"  id="overtimerate'+counter+'" Placeholder="Salary Description" value="Overtime'+counter+'" name="overtimerate'+counter+'" " class="form-control" ></div>'
				+'<div class="col-lg-2 col-md-2 col-sm-2">&nbsp;</div><div class="col-lg-2 col-md-2 col-sm-2" align="right" >'
				+'<select name="overtimepaytype'+counter+'" id="overtimepaytype'+counter+'" class="form-control" onchange="overtimewagemore(Hourly_payrate,overtimepaytype'+counter+',Hour_ot'+counter+',cp_ot'+counter+');"><option value="">Select</option><option value="Hourly">Per Hour</option><option value="Weekly">Per Week</option>'
				+'<option value="Monthly">Per Month</option><option value="Annually">Per Year</option></select></div><div class="col-lg-1 col-md-1 col-sm-1" align="right" > '
				+'<input type="text" id="Hour_ot'+counter+'" name="Hour_ot'+counter+'" class="form-control" onkeypress="return isNumber(event)" onchange="overtimewagemore(Hourly_payrate,overtimepaytype'+counter+',Hour_ot'+counter+',cp_ot'+counter+');"></div><div class="col-lg-1 col-md-1 col-sm-1" align="right" >'
				+'<input type="text" value="0.0" id="ytd_ot'+counter+'" name="ytd_ot'+counter+'" class="form-control" onkeypress="return isNumber(event)" /></div><div class="col-lg-1 col-md-1 col-sm-1" align="right" >'
				+' <input type="text" value="0.0" id="cp_ot'+counter+'" name="cp_ot'+counter+'" readonly class="form-control" ></div><input type="hidden" name="count'+counter+'" id="count'+counter+'" value="'+counter+'"><div class="col-lg-1 col-md-1 col-sm-1 align="left" style="padding:10px;""><button id="removerow" onclick="checkcount(count'+counter+');"><i class="fa fa-minus-circle" aria-hidden="true"></i></button></div></div></div></div>');     
				
				//var newrow= jQuery('<p>hello adding row test</p>');
				
				
							
		jQuery('#overtimerow').append(newrow);
		$("#overtimecount").val(counter);
		//alert(counter);
		document.paycalc.overtimecount.value=counter;
		return false;
	});
		
    $("#overtimerow").on('click','#removerow',function(e){
		e.preventDefault();
       //$(this).parent().parent().remove();
		//counter = counter-1;
	//alert("remove count==="+counter);
  });
		
		$("#Payratecycle").change(function(){
			$("#Hourly_payrate").val('');
			$("#total_hours").val('');
			$("#total_gross_pay_hourly").val('');
			
			//alert("ratecheck");
			var paytype = $("#Payratecycle").val();
			//alert(paytype);
			$("#Paytype").val(paytype);	
		});
		
		  $('#overtime').click(function(){
            if($(this).prop("checked") == true){
                $("#overtimerow").show();
            }
            else if($(this).prop("checked") == false){
                $("#overtimerow").hide();
            }
        });
		
		
		
	/* Special character validate*/	
			
		 $.validator.addMethod(
			"checkspecialcharacter",
			function(value, element) {
				// put your own logic here, this is just a (crappy) example
				return value.match(/^[a-zA-Z\s]+$/);
			},
			"Special characters are not accepted"
			);
	
	
		 $("#paycalc").validate({
		 
			rules: {
				
				taxyear:"required",
				state:"required",
				fWHITMarital:"required",
				fWHITNoFAllowances:"required",
				Hour_ot0:"required",
				ytd_ot0:"required",
				Hourly_payrate:"required",
				PayRateType:"required",
				paycycle:"required",
				federal_rate:"required",
				federal_ytd:"required",
				social_rate:"required",
				social_ytd:"required",
				Medicare_rate:"required",
				Medicare_ytd:"required",
				State_rate:"required",
				State_ytd:"required",
				Income_YTD:"required",
				Income:"required",
				deduction:"required",
				deduction_YTD:"required",
				Monthly_payrate:"required",
				total_hours:"required",
				YTD_Monthly:"required",
				YTD_Hourly:"required",
				chequeDate:"required",
					
			},
			messages:{
				
				taxyear:"Please select the tax year",
				state:"Please select the state"	,
				fWHITMarital:"Please select the marital status",
				fWHITNoFAllowances:"This field is required",
				Hour_ot0:"This field is required",
				ytd_ot0:"This field is required",
				Hourly_payrate:"This field is required",
				paycycle:"Please select the pay cycle",
				PayRateType:"Please select the Pay rate type",
				federal_rate:"This field is required",
				federal_ytd:"This field is required",
				social_rate:"This field is required",
				social_ytd:"This field is required",
				Medicare_rate:"This field is required",
				Medicare_ytd:"This field is required",
				State_rate:"This field is required",
				State_ytd:"This field is required",
				Income_YTD:"This field is required",
				Income:"This field is required",
				deduction:"This field is required",
				deduction_YTD:"This field is required",
				Monthly_payrate:"Please enter the monthly payrate",
				total_hours:"Please the total hours worked",
				YTD_Monthly:"This field is required",
				YTD_Hourly:"This field is required",
				chequeDate:"This field is required",
				
			},
			errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}
			
	});
	
	

	/* state withholding form */
	
	var sStateArray1="---Select---|Alabama|Alaska|Arizona|Arkansas|California|Colorado|Connecticut|Delaware|District of Columbia|Florida|Georgia|Hawaii|Idaho|Illinois|Indiana|Iowa|Kansas|Kentucky|Louisiana|Maine|Maryland|Massachusetts|Michigan|Minnesota|Mississippi|Missouri|Montana|Nebraska|Nevada|New Hampshire|New Jersey|New Mexico|New York|North Carolina|North Dakota|Ohio|Oklahoma|Oregon|Pennsylvania|Rhode Island|South Carolina|South Dakota|Tennessee|Texas|Utah|Vermont|Virginia|Washington|West Virginia|Wisconsin|Wyoming"
	var maryland_tax_rate = "---Select---|Allegany|Anne Arundel|Baltimore City|Baltimore County|Calvert|Cecil|Caroline|Carroll|Charles|Dorchester|Frederick|Garrett|Harford|Howard|Kent|Montgomery|Prince George's|Queen Anne's|St. Mary's|Somerset|Talbot|Washington|Wicomico|Worcester|Nonresidents|Del"
	var indiana_country_list = "---Select---|Adams|Allen|Bartholomew|Benton|Blackford|Boone|Brown|Carroll|Cass|Clark|Clay|Clinton|Crawford|Daviess|Dearborn|Decatur|DeKalb|Delaware|Dubois|Elkhart|Fayette|Floyd|Fountain|Franklin|Fulton|Gibson|Grant|Greene|Hamilton|Hancock|Harrison|Hendricks|Henry|Howard|Huntington|Jackson|Jasper|Jay|Jefferson|Jennings|Johnson|Knox|Kosciusko|LaGrange|Lake|LaPorte|Lawrence|Madison|Marion|Marshall|Martin|Miami|Monroe|Montgomery|Morgan|Newton|Noble|Ohio|Orange|Owen|Parke|Perry|Pike|Porter|Posey|Pulaski|Putnam|Randolph|Ripley|Rush|St. Joseph|Scott|Shelby|Spencer|Starke|Steuben|Sullivan|Switzerland|Tippecanoe|Tipton|Union|Vanderburgh|Vermillion|Vigo|Wabash|Warren|Warrick|Washington|Wayne|Wells|White|Whitley"
	
								
	var stateval = "";
	$("#state").change(function(){
		
		var stateval = $("#state").val();
		//alert("checking state"+stateval);
		//alert(sStateArray1.split("|").length);
		
	for(s=0;s<sStateArray1.split("|").length;s++){
		sState = sStateArray1.split("|")[s];
				
		if(stateval!="" & stateval!=null & stateval == sStateArray1.split("|")[s]){
				//alert(s);
		
			var stateform = statelist[s];
			
					
				jQuery("#state_form").empty().append(stateform);
		
			
					if (stateval == "Maryland" ){
					
						for(i=0;i<maryland_tax_rate.split("|").length;i++){
							$('#maryland_tax_rate').append('<option value='+maryland_tax_rate.split("|")[i]+'>' +maryland_tax_rate.split("|")[i]+ '</option>');
							
						}	
						//alert(maryland_tax_rate.split("|").length);
					
					}
					
					if(stateval == "Indiana"){
						for(i=0;i<indiana_country_list.split("|").length;i++){
							$('#indiana_country').append('<option value='+indiana_country_list.split("|")[i]+'>' +indiana_country_list.split("|")[i]+ '</option>');
							
						}
					}
				
				}
			}
	});
	
	
	var statelist = new Array()
	
	/* Alabama */			statelist[1] =jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-2 col-md-2 col-sm-2">&nbsp;</div>'
							+'<p><span style="color:red">*</span> Will withhold taxes using Federal W-4 if IT-104 is not completed</p><div class="col-lg-2 col-md-2 col-sm-2">&nbsp;</div>'
							+'<div class="col-lg-3 col-md-3 col-sm-3"><label class="name form-div-6">Filing Status</label></div><div class="col-lg-4 col-md-4 col-sm-4"><select  name="filing_status"  class="form-control"><option value="">Select</option><option value="Married Filing Joint">Married Filing Joint</option><option value="Married Filing Separate">Married Filing Separate</option><option value="Head of Household">Head of Household</option><option value="Single">Single</option></select></div></div>'
							+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-2 col-md-2 col-sm-2">&nbsp;</div><div class="col-lg-3 col-md-3 col-sm-3"><label class="name form-div-6">No. of Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
							+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-2 col-md-2 col-sm-2">&nbsp;</div><div class="col-lg-3 col-md-3 col-sm-3"><label class="name form-div-6">Dependent Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="dependent_allowances" value="0"class="form-control"></div></div>'
							+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-2 col-md-2 col-sm-2">&nbsp;</div>'
							+'<div class="col-lg-3 col-md-3 col-sm-3"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div></div>');
	
/* Alaska*/					statelist[2] = jQuery(' <div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-2 col-md-2 col-sm-2">&nbsp;</div><p><strong>AK has NO withholding tax.</strong></p></div>');
							
/* Arizona */				statelist[3] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-2 col-md-2 col-sm-2">&nbsp;</div><label class="name form-div-6">Exemption from Arizona withholding</label>&nbsp;&nbsp; <input type="checkbox" name="checkbox"></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div>'
							+'<div class="col-lg-6 col-md-6 col-sm-6">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-2 col-md-2 col-sm-2">&nbsp;</div>Arizona withholding at the rate of &nbsp;&nbsp;<select name="withholding_rate"><option value="">Select</option><option value="0.8">0.8</option>'
							+'<option value="1.3">1.3</option><option value="1.7">1.7</option><option value="2.7">2.7</option><option value="3.6">3.6</option><option value="4.2">4.2</option><option value="5.1">5.1</option></select>&nbsp;&nbsp;of gross taxable wages</div>');
					
/* Arkansas  */				statelist[4] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-2 col-md-2 col-sm-2">&nbsp;</div><div class="col-lg-3 col-md-3 col-sm-3"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="total_allowances" value="0" class="form-control">'
		+'</div></div><div class="col-lg-6 col-md-6 col-sm-6">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-2 col-md-2 col-sm-2">&nbsp;</div><div class="col-lg-3 col-md-3 col-sm-3"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0" class="form-control"></div> </div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-2 col-md-2 col-sm-2">&nbsp;</div><div class="col-lg-3 col-md-3 col-sm-3"><label class="name form-div-6">Texarkana Resident</label></div><div class="col-lg-4 col-md-4 col-sm-4"><select name="texarna_resident"  class="form-control"><option value="No" selected>No</option><option value="Yes">Yes</option></select></div> </div>');

						
	/* California */		statelist[5] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
			+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married">Married</option><option value="Head of Household">Head of Household</option></select></div></div>'
			+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Extended Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="extended_allowances" value="0" class="form-control"></div></div>'
			+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Regular Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="regular_allowances" value="0" class="form-control"></div></div>'
			+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
								
								
	/*Colorado */			statelist[6] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
			+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married">Married</option></select></div></div>'
			+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
			+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
		
	/*Connecticut*/			statelist[7] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-2 col-md-2 col-sm-2">&nbsp;</div><div class="col-lg-4 col-md-4 col-sm-4"><label class="name form-div-6">Withholding Code</label></div><div class="col-lg-4 col-md-4 col-sm-4"><select  name="withholding_code" class="form-control"><option value="">Select</option><option value="A">A</option><option value="B">B</option><option value="C">C</option><option value="D">D</option><option value="E">E</option><option value="F">F</option></select></div></div>'
	+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-2 col-md-2 col-sm-2">&nbsp;</div><div class="col-lg-4 col-md-4 col-sm-4"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>'
	+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div>');
							
					
	/*Delaware*/			statelist[8] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
			+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married Filing Jointly">Married Filing Jointly</option><option value="Married Filing Seperately">Married Filing Seperately</option></select></div></div>'
			+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
			+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');

/* Dist.Of columbia	*/		statelist[9] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-sm-offset-2">* Will withhold tax at single rate w/zero exemption if K-4 is not completed</div><br><br><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances </label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text"  name="allowances" value="0" class="form-control">'
										+'</div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6"> Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
						
									
	/*Folrida */			statelist[10] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-2 col-md-2 col-sm-2">&nbsp;</div ><label>FL has NO withholding tax.</label></div>');
	
								
	/* Georgia */ 			statelist[11] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-2 col-md-2 col-sm-2">&nbsp;</div ><div class="col-lg-6 col-md-6 col-sm-6"><label class="name form-div-6">Use of Federal Allowances</label>&nbsp;&nbsp;&nbsp;<input type="radio" name="GA_federal" id="GA_federal" value="yes" checked onclick="federalGeorgia(GA_federal);">yes&nbsp;<input type="radio" name="GA_federal" id="GA_federal1" value="No" onclick="federalGeorgia(GA_federal1);">No</div></div><div id="GA_federal_yes"><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6">&nbsp;</div>'
							+'<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-2 col-md-2 col-sm-2">&nbsp;</div><div class="col-lg-4 col-md-4 col-sm-4"><label class="name form-div-6">Dependent Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="dependent_allowances" value="0" class="form-control"></div> </div></div><div id="GA_federal_no" style="display:none;"><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-2"><label class="name form-div-6">Exemption from Georgia withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="checkbox" name="checkbox" value="Exemption from Georgia withholding">'
							+'</div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-2"><label class="name form-div-6">Marital Status</label></div><div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married Filing Joint Return(One Spouse working)">Married Filing Joint Return(One Spouse working)</option><option value="Married Filing Joint Return(Both Spouse working)">Married Filing Joint Return(Both Spouse working)</option><option value="Married Filing Separate Return">Married Filing Separate Return</option><option value="Head of Household">Head of Household</option></select></div>'
							+'</div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-2"><label class="name form-div-6">Personal Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="personal_allowances" value="0" class="form-control"></div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-2"><label class="name form-div-6">Dependent Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="dependent_allowances" value="0" class="form-control"></div></div>'
							+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0" class="form-control"></div></div>'
							+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-2"><label class="name form-div-6">Additional Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_allowances" value="0" class="form-control"></div></div>'
							+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-4 col-md-4 col-sm-4 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="total_allowances" value="0" class="form-control"></div></div></div>');
							
	
	/* Hawaii */			statelist[12] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
			+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married">Married</option><option value="Married use Single rate">Married use Single rate</option><option value="Certified Disabled Person">Certified Disabled Person</option></select></div></div>'
			+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
			+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
										
	
	/*Idaho */				statelist[13] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-2 col-md-2 col-sm-2">'
			+'&nbsp;</div><div class="col-lg-3 col-md-3 col-sm-3"><label class="name form-div-6">Filing Status</label></div><div class="col-lg-4 col-md-4 col-sm-4"><select  name="filing_status"  class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married">Married</option></select></div></div>'
			+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-2 col-md-2 col-sm-2">&nbsp;</div><div class="col-lg-3 col-md-3 col-sm-3"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="total_allowances" value="0" class="form-control">'
			+'</div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-2 col-md-2 col-sm-2">&nbsp;</div><div class="col-lg-3 col-md-3 col-sm-3"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0" class="form-control"></div> </div>');
							
							
						
	/* Illinois */			statelist[14] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Basic Allowances </label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" class="form-control" name="basic_allowances" value="0"></div></div>'
							+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_allowances" value="0" class="form-control"></div></div>'
							+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
										
	
	/* Indiana */			statelist[15] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-sm-offset-2">* Will withhold tax as claiming no deductions if WH-4 is not completed</div><br><br><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Personal Deduction</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" class="form-control" name="personal_deduction" value="0"></div></div>'
										+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Dependent Deduction</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="dependent_deduction" value="0" class="form-control"></div></div>'
										+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div> </div>');
										
	
	/* Iowa */				statelist[16] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
			+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married">Married</option></select></div></div>'
			+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
			+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
										
	
	/* Kansas */			statelist[17] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
			+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married">Married</option><option value="Head of HouseHold">Head of HouseHold</option></select></div></div>'
			+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
			+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
							
						
						
				
						
	/* Kentucky */			statelist[18] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-sm-offset-2">* Will withhold tax at single rate w/zero exemption if K-4 is not completed</div><br><br><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances </label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text"  name="allowances" value="0" class="form-control">'
										+'</div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6"> Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
										
										
						
	/*Louisiana */			statelist[19] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
			+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married">Married</option></select></div></div>'
			+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Dependent Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="dependent_allowances" value="0" class="form-control"></div></div>'
			+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Personal Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="personal_allowances" value="0" class="form-control"></div></div>'
			+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
										
										
	/*Maine */				statelist[20] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-sm-offset-2">* Will file as single with no allowances if W-4ME is not completed</div><br><br><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
										+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married">Married</option><option value="Married use Single rate">Married use Single rate</option><option value="NonResidentAlien">NonResidentAlien</option></select></div></div>'
										+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
										+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
										
										
	/* Maryland */			statelist[21] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-sm-offset-2">* Will withhold taxes as claiming 1 allowance if Form MW507 is not completed</div><br><br><div class="col-lg-5 col-md-5 col-sm-5 col-sm-offset-2"><label class="name form-div-6"> Check if you are Non-Resident </label></div><div class="col-lg-4 col-md-4 col-sm-4">'
										+'<input type="checkbox" name="non_resident" value="Non-Resident" ></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Marital Status </label></div><div class="col-lg-4 col-md-4 col-sm-4"><select name="marital_status" class="form-control"><option value="">Select</option>'
										+'<option value="single">Single</option><option value="Married Joint">Married Joint</option><option value="Married Seperate">Married Seperate</option><option value="head of household">Head of Household</option></select></div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6">'
										+'<div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6"> Exemptions</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="exemptions"class="form-control"></div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6">'
										+'<div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6"> Additional Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_withholding" id="additional_withholding" class="form-control"></div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6">'
										+'<div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Use Tax Rate</label></div><div class="col-lg-4 col-md-4 col-sm-4"><select name="maryland_tax_rate" id="maryland_tax_rate" class="form-control"></select></div></div>');
							
						
/*Massachusetts*/			statelist[22] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-5 col-md-5 col-sm-5 col-sm-offset-2">'
		+'<label class="name form-div-6"> A. Head Of Household</label></div><input type="checkbox" name="house_hold" value="Head of Household"><br><br><div class="col-lg-5 col-md-5 col-sm-5 col-sm-offset-2"><label class="name form-div-6"> B. Personal Blind</label></div><input type="checkbox" name="personal_blind" value="Personal Blind" >'
		+'<br><br><div class="col-lg-5 col-md-5 col-sm-5 col-sm-offset-2"><label class="name form-div-6"> C. Spouse is Blind</label></div><input type="checkbox" name="spouse_blind" class="spouse_blind" value="Spouse Blind" ><br><br><div class="col-lg-5 col-md-5 col-sm-5 col-sm-offset-2"><label class="name form-div-6"> D. Student or Part Time</label></div><input type="checkbox" name="student" value="Student"></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
									
					
						
/* Michigan */				statelist[23] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
						
							
					
/*Minnesota */				statelist[24] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
										+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married">Married</option><option value="Married use Single rate">Married use Single rate</option></select></div></div>'
										+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
										+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');


/* Mississippi */			statelist[25] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
		+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Head of HouseHold">Head of HouseHold</option><option value="Married Spouse not Working">Married Spouse not Working</option><option value="Married Spouse Working">Married Spouse Working</option></select></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Exemption Amount</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="exemption_amount" value="0" class="form-control"></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
								
							
/* Missouri */				statelist[26] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
		+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Head of Household">Head of Household</option><option value="Married Spouse Not Works">Married Spouse Not Works</option><option value="Married Spouse Works">Married Spouse Works</option></select></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
										
										
										
/*Montana*/					statelist[27] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');


/*Nebraska*/				statelist[28] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
		+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married">Married</option></select></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');

/* Nevada */				statelist[29] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6 "><label class="col-sm-offset-2">NV has NO withholding tax.</label></div>');

/* New Hampshire */			statelist[30] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6 "><label class="col-sm-offset-2">NH has NO withholding tax.</label></div>');

/* New Jersey */			statelist[31] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
										+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married Filing Jointly">Married Filing Jointly</option><option value="Married Filing Separately">Married Filing Separately</option><option value="Head of Household">Head of Household</option><option value="Qualified Widow">Qualified Widow</option></select></div></div>'
										+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Rate Code </label></div><div class="col-lg-4 col-md-4 col-sm-4"><select name="rate_code" class="form-control"><option value="">Select</option><option value="A">A</option><option value="B">B</option><option value="C">C</option><option value="D">D</option><option value="E">E</option></select></div></div>'
										+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
										+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
											

											
/* New Mexico */			statelist[32] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
		+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married">Married</option></select></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');

/*New york */				statelist[33] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">City Tax</label></div><div class="col-lg-5 col-md-5 col-sm-5"><select name="city_tax"class="form-control"><option value="None">None</option><option value="New york city Resident">New york city Resident</option>'
										+'<option value="yonkers Resident">yonkers Resident</option><option value="yonkers Non-Resident">yonkers Non-Resident</option></select></div></div>');
										
										
/*North Carolina*/			statelist[34] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
		+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married">Married</option><option value="Head of Household">Head of Household</option></select></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
											
											
/* North Dakota */			statelist[35] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
		+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married">Married</option></select></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');

/* Ohio */					statelist[36] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-5 col-md-5 col-sm-5 col-sm-offset-2"><label >Exemption from Ohio withholding</label></div><div class="col-lg-2 col-md-2 col-sm-2"><input type="checkbox" name="checkbox" value="Exemption from Ohio withholding">'
											+'</div></div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">School District No.</label></div><div class="col-lg-5 col-md-5 col-sm-5"><input type="text" name="district_no" value="0" class="form-control"></div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2">'
											+'<label class="name form-div-6"> School Dist. Rate</label></div><div class="col-lg-5 col-md-5 col-sm-5"><select class="form-control" name="district_rate"><option value="0">0</option><option value="0.50">0.50</option><option value="0.75">0.75</option><option value="1.00">1.00</option><option value="1.25">1.25</option><option value="1.50">1.50</option><option value="1.75">1.75</option><option value="2.00">2.00</option></select></div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div>'
											+'<div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-5 col-md-5 col-sm-5"><input type="text" name="allowances" value="0" class="form-control"></div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Wwithholding</label>'
											+'</div><div class="col-lg-5 col-md-5 col-sm-5"><input type="text" name="additional_state_withholding" class="form-control" value="0.0"></div></div>');
						

/* Oklahoma */				statelist[37] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
		+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married">Married</option><option value="Married use Single rate">Married use Single rate</option></select></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
									
/* Oregon*/      			statelist[38] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
		+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married">Married</option></select></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
								
							
/* Pennsylvania */			statelist[39] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6 "><label class="col-sm-offset-2">PA state is using flat rate for payroll tax withholding.</label></div>');



/*Rhode Island*/			statelist[40] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
										+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
								
							
/*South Carolina*/			statelist[41] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
								
/*South Dokato */			statelist[42] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6 "><label class="col-sm-offset-2">SD has NO withholding tax.</label></div>');

/* Tennessee */				statelist[43] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6 "><label class="col-sm-offset-2">TN has NO withholding tax.</label></div>');

/*Texas */					statelist[44] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6 "><label class="col-sm-offset-2">TX has NO withholding tax.</label></div>');

/*Utah */					statelist[45] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
		+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married">Married</option></select></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');

/* Vermont*/				statelist[46] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
										+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married">Married</option><option value="">Select</option><option value="Civil Union">Civil Union</option><option value="Civil Union use Single Rate">Civil Union use Single Rate</option><option value="Married Use Single Rate">Married Use Single Rate</option></select></div></div>'
										+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
										+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');

											
/* Virginia */				statelist[47] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-5 col-md-5 col-sm-5 col-sm-offset-2"><label >Not subject to Virginia withholding</label></div><div class="col-lg-2 col-md-2 col-sm-2"><input type="checkbox" name="checkbox" value="virginia_withholding"></div></div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2">'
										+'<label class="name form-div-6">Number of Exemptions</label></div><div class="col-lg-5 col-md-5 col-sm-5"><input type="text" name="VI_NofExemptions" value="0" class="form-control"></div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6"> Age/Blindness Exemptions</label>'
										+'</div><div class="col-lg-5 col-md-5 col-sm-5"><input type="text" name="VI_age_exemptions" value="0" class="form-control"></div></div><div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional Withholding</label></div><div class="col-lg-5 col-md-5 col-sm-5"><input type="text" name="VI_additional_withholding" value="0.0" class="form-control"></div></div>');
										


/* Washington */  			statelist[48] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6 "><label class="col-sm-offset-2">WA has NO withholding tax.</label></div>');


/* West Virginia */			statelist[49] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
		+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married filing Jointly">Married filing Jointly</option><option value="Married use Single rate">Married use Single rate</option><option value="Head of HouseHold">Head of HouseHold</option></select></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Total Allowances</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="allowances" value="0" class="form-control"></div></div>'
		+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
							
				
/* Wisconsin */				statelist[50] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Filing Status </label></div>'
										+'<div class="col-lg-4 col-md-4 col-sm-4"><select name="filing_status" class="form-control"><option value="">Select</option><option value="Single">Single</option><option value="Married">Married</option><option value="Married use Single Rate">Married use Single Rate</option></select></div></div>'
										+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Withholding Exemptions</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="WHExemptions" value="0" class="form-control"></div></div>'
										+'<div class="col-lg-12 col-md-12 col-sm-12">&nbsp;</div><div class="col-lg-6 col-md-6 col-sm-6 col-sm-offset-6"><div class="col-lg-3 col-md-3 col-sm-3 col-sm-offset-2"><label class="name form-div-6">Additional State Withholding</label></div><div class="col-lg-4 col-md-4 col-sm-4"><input type="text" name="additional_state_withholding" value="0.0" class="form-control"></div></div>');
													
					
							
							
/* wyoming */				statelist[51] = jQuery('<div class="col-lg-6 col-md-6 col-sm-6 "><label class="col-sm-offset-2">WY has NO withholding tax.</label></div>');
					
								
									
							
	});
	

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
function isNumber(evt) {
    var charCode = (evt.which) ? evt.which : evt.keyCode;
          if (charCode != 46 && charCode > 31 
            && (charCode < 48 || charCode > 57))
             return false;

          return true;
}


	$(document).ready(function(){
			/*empty check validation*/
		$("#employeename").blur(function(){        
			var a = $("#employeename").val();
			var b = a.trim();
			$("#employeename").val(b);
				});
				
	/* Special character validate*/	
			
		 $.validator.addMethod(
			"checkspecialcharacter",
			function(value, element) {
				// put your own logic here, this is just a (crappy) example
				return value.match(/^\s*[~!@#$%^&*\s]+\s*$/);
			},
			"Special characters are not accepted"
			);
	
	/*special character end */
	
		 $("#form").validate({
		 
			rules: {
				employeeid:{
					required: true
				 },
				employeename: {
					required: true,
					checkspecialcharacter: true
				},
				insurancededuction: {
					required: true
				},
				otherregulardeduction:{
					required: true
				},
				hourlywage:{
					required: true
				},
				taxstatus:{
					required: true
				},
				federalallowance:{
					required: true
				},
				statetax:{
					required: true
				},
				federalincometax:{
					required: true
				},
				socialsecuritytax:{
					required: true
				},
				medicaretax:{
					required: true
				},

				
			},
			messages:{
				employeeid:"Please enter the employee id",
				employeename:{
					required: "Please enter the employee Name",
					checkspecialcharacter: "Special characters are not accepted"
				},
				insurancededuction:"This field is required",
				otherregulardeduction:"This field is required",
				hourlywage:"This field is required",
				taxstatus:"This field is required",
				federalallowance:"This field is required",
				statetax:"This field is required",
				federalincometax:"This field is required",
				socialsecuritytax:"This field is required",
				medicaretax:"This field is required",
				
			},
			errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}
			
	});
	
	
		 $("#form1").validate({
		 
			rules: {
				employeeid1:{
					required: true
				},
			
				afterTaxIncome:{
					required: true
				},
				otherdeduction:{
					required: true
				},
				regularhoursworked:{
					required: true
				},
				vacationhours:{
					required: true
				},
				sickhours:{
					required: true
				},
				overtimehours:{
					required: true
				},
				overtimerate:{
					required: true
				},
			},
			messages:{
				employeeid1:"This field is required",
				
				afterTaxIncome:"This field is required",
				otherdeduction:"This field is required",
				regularhoursworked:"This field is required",
				vacationhours:"This field is required",
				sickhours:"This field is required",
				overtimehours:"This field is required",
				overtimerate:"This field is required",
			},
			errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}
			
	});
	
	/* empty field validation end */
				
	$("#toPayrollCalculator").click(function(){
		
		$("#payrollDetails").hide();
		$("#payrollCalculator").show();
		
	});
	
	$("#toPayrollDetails").click(function(){
		
		$("#payrollDetails").show();
		$("#payrollCalculator").hide();
		
	});
	
	$("#toPayrollDetails1").click(function(){
		window.location.assign('payroll.html?cmd=payrollXLX');		
	});
	
	$("#hourlywage").blur(function(){
		
		 var oldstr=$("#hourlywage").val();
		 var str=oldstr.replace('$',''); 
		 
		 if(str == ""){
			str = "0";
			symbol = "";
		}
		
            $("#hourlywage").val('$'+str);   
		
	});
	
	$("#statetax").blur(function(){
		
		 var oldstr=$("#statetax").val();
		 var str=oldstr.replace('%',''); 
         var symbol = "%";

			//statetax federalincometax socialsecuritytax medicaretax totaltaxwithheld
		var federalincometax=$("#federalincometax").val();
		var federalincometaxstr=federalincometax.replace('%',''); 
		
		var socialsecuritytax=$("#socialsecuritytax").val();
		var socialsecuritytaxstr=socialsecuritytax.replace('%',''); 
		
		var medicaretax=$("#medicaretax").val();
		var medicaretaxstr=medicaretax.replace('%',''); 
		
		if(str == ""){
			str = "0";
			symbol = "";
		}
		if(federalincometaxstr == ""){
			federalincometaxstr = "0";
		}
		if(socialsecuritytaxstr == ""){
			socialsecuritytaxstr = "0";
		}
		if(medicaretaxstr == ""){
			medicaretaxstr = "0";
		}
		
		var totaltaxwithheld = parseFloat(str)+parseFloat(federalincometaxstr)+parseFloat(socialsecuritytaxstr)+parseFloat(medicaretaxstr);
		var finaltotaltaxwithheld = totaltaxwithheld.toFixed(2);
		 $("#statetax").val(str+symbol); 
		$("#totaltaxwithheld").val(str+'%'); 
		$("#totaltaxwithheld").val(finaltotaltaxwithheld+'%'); 
		
	});


$("#federalincometax").blur(function(){
		
		 var oldstr=$("#federalincometax").val();
		 var str=oldstr.replace('%',''); 
         var symbol = "%";

			//statetax federalincometax socialsecuritytax medicaretax totaltaxwithheld
		var statetax=$("#statetax").val();
		var statetaxstr=statetax.replace('%',''); 
		
		var socialsecuritytax=$("#socialsecuritytax").val();
		var socialsecuritytaxstr=socialsecuritytax.replace('%',''); 
		
		var medicaretax=$("#medicaretax").val();
		var medicaretaxstr=medicaretax.replace('%',''); 
		
		if(str == ""){
			str = "0";
			symbol = "";
		}
		if(statetaxstr == ""){
			statetaxstr = "0";
		}
		if(socialsecuritytaxstr == ""){
			socialsecuritytaxstr = "0";
		}
		if(medicaretaxstr == ""){
			medicaretaxstr = "0";
		}
		
		var totaltaxwithheld = parseFloat(str)+parseFloat(statetaxstr)+parseFloat(socialsecuritytaxstr)+parseFloat(medicaretaxstr);
		var finaltotaltaxwithheld = totaltaxwithheld.toFixed(2);
		 $("#federalincometax").val(str+symbol); 
		$("#totaltaxwithheld").val(str+'%'); 
		$("#totaltaxwithheld").val(finaltotaltaxwithheld+'%'); 
		
	});
	

	$("#socialsecuritytax").blur(function(){
		
		 var oldstr=$("#socialsecuritytax").val();
		 var str=oldstr.replace('%',''); 
		 var symbol = "%";
		 
			//statetax federalincometax socialsecuritytax medicaretax totaltaxwithheld
		var statetax=$("#statetax").val();
		var statetaxstr=statetax.replace('%',''); 
		
		var federalincometax=$("#federalincometax").val();
		var federalincometaxstr=federalincometax.replace('%',''); 
		
		var medicaretax=$("#medicaretax").val();
		var medicaretaxstr=medicaretax.replace('%',''); 
		
		if(str == ""){
			str = "0";
			symbol = "";
		}
		if(statetaxstr == ""){
			statetaxstr = "0";
		}
		if(federalincometaxstr == ""){
			federalincometaxstr = "0";
		}
		if(medicaretaxstr == ""){
			medicaretaxstr = "0";
		}
		
		var totaltaxwithheld = parseFloat(str)+parseFloat(statetaxstr)+parseFloat(federalincometaxstr)+parseFloat(medicaretaxstr);
		
		var finaltotaltaxwithheld = totaltaxwithheld.toFixed(2);
		$("#socialsecuritytax").val(str+symbol); 
		$("#totaltaxwithheld").val(str+'%'); 
		$("#totaltaxwithheld").val(finaltotaltaxwithheld+'%'); 
		
	});
	
		$("#medicaretax").blur(function(){
		
		 var oldstr=$("#medicaretax").val();
		 var str=oldstr.replace('%',''); 
		 var symbol = "%";
		 
			//statetax federalincometax socialsecuritytax medicaretax totaltaxwithheld
		var statetax=$("#statetax").val();
		var statetaxstr=statetax.replace('%',''); 
		
		var federalincometax=$("#federalincometax").val();
		var federalincometaxstr=federalincometax.replace('%',''); 
		
		var socialsecuritytax=$("#socialsecuritytax").val();
		var socialsecuritytaxstr=socialsecuritytax.replace('%',''); 
		
		if(str == ""){
			str = "0";
			symbol = "";
		}
		if(statetaxstr == ""){
			statetaxstr = "0";
		}
		if(federalincometaxstr == ""){
			federalincometaxstr = "0";
		}
		if(socialsecuritytaxstr == ""){
			socialsecuritytaxstr = "0";
		}
		
		var totaltaxwithheld = parseFloat(str)+parseFloat(statetaxstr)+parseFloat(federalincometaxstr)+parseFloat(socialsecuritytaxstr);
		var finaltotaltaxwithheld = totaltaxwithheld.toFixed(2);
		$("#medicaretax").val(str+symbol); 
		$("#totaltaxwithheld").val(str+'%'); 
		$("#totaltaxwithheld").val(finaltotaltaxwithheld+'%'); 
		
	});
	
	$("#insurancededuction").blur(function(){
		
		 var oldstr=$("#insurancededuction").val();
		 var str=oldstr.replace('$',''); 
		 var symbol = "$";
		 
			//insurancededuction otherregulardeduction totalregulardeduction
		var otherregulardeduction=$("#otherregulardeduction").val();
		var strr=otherregulardeduction.replace('$',''); 
		
		if(str == ""){
			str = "0";
			symbol = "";
		}
		if(strr == ""){
			strr = "0";
		}
		
		
		var totalregulardeduction = parseFloat(str)+parseFloat(strr);
		
		var finaltotalregulardeduction = totalregulardeduction.toFixed(2);
		
		$("#insurancededuction").val(symbol+str); 
		//$("#totalregulardeduction").val('$'+str); 
		$("#paytotalregulardeduction").val('$'+finaltotalregulardeduction); 
		
	});
	
	$("#otherregulardeduction").blur(function(){
		
		 var oldstr=$("#otherregulardeduction").val();
		 var str=oldstr.replace('$',''); 
		 var symbol = "$";
		 
			//insurancededuction otherregulardeduction totalregulardeduction
		var insurancededuction=$("#insurancededuction").val();
		var strr=insurancededuction.replace('$',''); 
		
		if(str == ""){
			str = "0";
			symbol = "";
		}
		if(strr == ""){
			strr = "0";
		}
		
		var totalregulardeduction = parseFloat(str)+parseFloat(strr);
		var finaltotalregulardeduction = totalregulardeduction.toFixed(2);
		
		$("#otherregulardeduction").val(symbol+str); 
		//$("#totalregulardeduction").val('$'+str); 
		$("#paytotalregulardeduction").val('$'+finaltotalregulardeduction); 
		
	});
	
	// payroll calculation starts
	$("#employeeid1").blur(function(){
		
		var empid = $("#employeeid1").val();
				
				if(empid == ""){
				//	alert('in ');
				}else{
				
		$.get('payroll.html?cmd=getProg', {
        	prog : empid
        }, function(response) {
			//alert(response);
        	if(response != null){        		
        	         var temp = response.split("/");					
					var employeename = temp[0];
					$("#employeename1").val(employeename);
					var hourlyWages = temp[1]; 
					var hourlywage = hourlyWages.replace('$','');
					
					var totaltaxeswithheld = temp[2];
					var totalregulardeduction = temp[3];

					var temptotaltaxesWH = totaltaxeswithheld.replace('%','');
					var temptotalregulardeduction = totalregulardeduction.replace('$','');
					
					var regularhoursworked = $("#regularhoursworked").val();
					var vacationhours = $("#vacationhours").val();
					var sickhours = $("#sickhours").val();
					var overtimehours = $("#overtimehours").val();
					var overtimerate = $("#overtimerate").val();
					
					if(regularhoursworked == ""){
						regularhoursworked = "0";
					}
					if(vacationhours == ""){
						vacationhours = "0";
					}
					if(sickhours == ""){
						sickhours = "0";
					}
					if(overtimehours == ""){
						overtimehours = "0";
					}
					if(overtimerate == ""){
						overtimerate = "0";
					}else{
						overtimerate = overtimerate.replace('$','');
					}
					
					var temp1 = parseFloat(regularhoursworked)+parseFloat(vacationhours)+parseFloat(sickhours);
					var temp2 = parseFloat(hourlywage)*temp1;
					var temp3 = parseFloat(overtimehours)*parseFloat(overtimerate);;
					var total = temp2+temp3;
					
					$("#hourlyWages").val(hourlywage);
					$("#totaltaxesWH").val(temptotaltaxesWH);
					$("#totalregulardeduction").val(temptotalregulardeduction);
					var grosspay = total.toFixed(2);
					$("#grosspay").val('$'+grosspay);
					//taxesanddeductions
				
					var tempforTaxandDeduction = (parseFloat(temptotaltaxesWH)*parseFloat(grosspay))/100;
					//alert('tempforTaxandDeduction --- '+tempforTaxandDeduction);					
					var sumfortaxAndDeduction = parseFloat(tempforTaxandDeduction)+parseFloat(temptotalregulardeduction);					
					$("#taxesanddeductions").val('$'+sumfortaxAndDeduction.toFixed(2));
					
					//netpay taxesanddeductions otherdeduction
					var otherdeduction = $("#otherdeduction").val();
					if(otherdeduction == ""){
						otherdeduction = "0";
					}else{
						otherdeduction = otherdeduction.replace('$','');
					}
					var netpay = parseFloat(grosspay)-parseFloat(sumfortaxAndDeduction)-parseFloat(otherdeduction);
					
					var str = netpay.toString();
					if(/-/.test(str) == true) {					
							var netpayresults = str.replace('-','');		
							netpayresults = parseFloat(netpayresults).toFixed(2);
					$("#netpay").val('$'+'('+netpayresults+')');
					}else{
						str = parseFloat(str).toFixed(2);
					$("#netpay").val('$'+str);
					}
					
        	}       
        });
	}
	
	});
	
		$("#regularhoursworked").change(function(){
			
					var regularhoursworked = $("#regularhoursworked").val();
					var vacationhours = $("#vacationhours").val();
					var sickhours = $("#sickhours").val();
					var overtimehours = $("#overtimehours").val();
					var overtimerate = $("#overtimerate").val();
			
			if(regularhoursworked == ""){
						regularhoursworked = "0";
					}
					if(vacationhours == ""){
						vacationhours = "0";
					}
					if(sickhours == ""){
						sickhours = "0";
					}
					if(overtimehours == ""){
						overtimehours = "0";
					}
					if(overtimerate == ""){
						overtimerate = "0";
					}else{
						overtimerate = overtimerate.replace('$','');
					}
					
					var hourlywage = $("#hourlyWages").val();
					
					var temp1 = parseFloat(regularhoursworked)+parseFloat(vacationhours)+parseFloat(sickhours);
					var temp2 = parseFloat(hourlywage)*temp1;
					var temp3 = parseFloat(overtimehours)*parseFloat(overtimerate);;
					var total = temp2+temp3;
					
					var grosspay = total.toFixed(2);
					var finalregularhoursworked = parseFloat(regularhoursworked).toFixed(2);
					$("#regularhoursworked").val(finalregularhoursworked);
					$("#grosspay").val('$'+grosspay);
					
					var temptotaltaxesWH = $("#totaltaxesWH").val();
					var temptotalregulardeduction = $("#totalregulardeduction").val();
					
					var tempforTaxandDeduction = (parseFloat(temptotaltaxesWH)*parseFloat(grosspay))/100;
					var sumfortaxAndDeduction = parseFloat(tempforTaxandDeduction)+parseFloat(temptotalregulardeduction);
					$("#taxesanddeductions").val('$'+sumfortaxAndDeduction.toFixed(2));
					
			//employeename1 regularhoursworked vacationhours sickhours overtimehours overtimerate grosspay		
			
			var otherdeduction = $("#otherdeduction").val();
					if(otherdeduction == ""){
						otherdeduction = "0";
					}else{
						otherdeduction = otherdeduction.replace('$','');
					}
					var netpay = parseFloat(grosspay)-parseFloat(sumfortaxAndDeduction)-parseFloat(otherdeduction);
					
					var str = netpay.toString();
					
					if(/-/.test(str) == true) {					
							var netpayresults = str.replace('-','');	
								netpayresults = parseFloat(netpayresults).toFixed(2);							
					$("#netpay").val('$'+'('+netpayresults+')');
					}else{
						str = parseFloat(str).toFixed(2);
					$("#netpay").val('$'+str);
					}
					
		});
		
				$("#vacationhours").change(function(){
			
					var regularhoursworked = $("#regularhoursworked").val();
					var vacationhours = $("#vacationhours").val();
					var sickhours = $("#sickhours").val();
					var overtimehours = $("#overtimehours").val();
					var overtimerate = $("#overtimerate").val();
			
			if(regularhoursworked == ""){
						regularhoursworked = "0";
					}
					if(vacationhours == ""){
						vacationhours = "0";
					}
					if(sickhours == ""){
						sickhours = "0";
					}
					if(overtimehours == ""){
						overtimehours = "0";
					}
					if(overtimerate == ""){
						overtimerate = "0";
					}else{
						overtimerate = overtimerate.replace('$','');
					}
					
					var hourlywage = $("#hourlyWages").val();
					
					var temp1 = parseFloat(regularhoursworked)+parseFloat(vacationhours)+parseFloat(sickhours);
					var temp2 = parseFloat(hourlywage)*temp1;
					var temp3 = parseFloat(overtimehours)*parseFloat(overtimerate);;
					var total = temp2+temp3;
						
					var grosspay = total.toFixed(2);
					var finalvacationhours = parseFloat(vacationhours).toFixed(2);
					$("#vacationhours").val(finalvacationhours);
					$("#grosspay").val('$'+grosspay);
					
					var temptotaltaxesWH = $("#totaltaxesWH").val();
					var temptotalregulardeduction = $("#totalregulardeduction").val();
					
					var tempforTaxandDeduction = (parseFloat(temptotaltaxesWH)*parseFloat(grosspay))/100;
					var sumfortaxAndDeduction = parseFloat(tempforTaxandDeduction)+parseFloat(temptotalregulardeduction);
					$("#taxesanddeductions").val('$'+sumfortaxAndDeduction.toFixed(2));
					
			//employeename1 regularhoursworked vacationhours sickhours overtimehours overtimerate grosspay	

					var otherdeduction = $("#otherdeduction").val();
					if(otherdeduction == ""){
						otherdeduction = "0";
					}else{
						otherdeduction = otherdeduction.replace('$','');
					}
					var netpay = parseFloat(grosspay)-parseFloat(sumfortaxAndDeduction)-parseFloat(otherdeduction);
					
					var str = netpay.toString();
					
					if(/-/.test(str) == true) {							
							var netpayresults = str.replace('-','');	
					netpayresults = parseFloat(netpayresults).toFixed(2);							
					$("#netpay").val('$'+'('+netpayresults+')');
					}else{
					str = parseFloat(str).toFixed(2);
					$("#netpay").val('$'+str);
					}			
		});
		
		$("#sickhours").change(function(){
			
					var regularhoursworked = $("#regularhoursworked").val();
					var vacationhours = $("#vacationhours").val();
					var sickhours = $("#sickhours").val();
					var overtimehours = $("#overtimehours").val();
					var overtimerate = $("#overtimerate").val();
			
			if(regularhoursworked == ""){
						regularhoursworked = "0";
					}
					if(vacationhours == ""){
						vacationhours = "0";
					}
					if(sickhours == ""){
						sickhours = "0";
					}
					if(overtimehours == ""){
						overtimehours = "0";
					}
					if(overtimerate == ""){
						overtimerate = "0";
					}else{
						overtimerate = overtimerate.replace('$','');
					}
					
					var hourlywage = $("#hourlyWages").val();
					
					var temp1 = parseFloat(regularhoursworked)+parseFloat(vacationhours)+parseFloat(sickhours);
					var temp2 = parseFloat(hourlywage)*temp1;
					var temp3 = parseFloat(overtimehours)*parseFloat(overtimerate);;
					var total = temp2+temp3;
						
					var grosspay = total.toFixed(2);
					var finalsickhours = parseFloat(sickhours).toFixed(2);
					$("#sickhours").val(finalsickhours);
					$("#grosspay").val('$'+grosspay);
					
					var temptotaltaxesWH = $("#totaltaxesWH").val();
					var temptotalregulardeduction = $("#totalregulardeduction").val();
					
					var tempforTaxandDeduction = (parseFloat(temptotaltaxesWH)*parseFloat(grosspay))/100;
					var sumfortaxAndDeduction = parseFloat(tempforTaxandDeduction)+parseFloat(temptotalregulardeduction);
					$("#taxesanddeductions").val('$'+sumfortaxAndDeduction.toFixed(2));
					
			//employeename1 regularhoursworked vacationhours sickhours overtimehours overtimerate grosspay	

					var otherdeduction = $("#otherdeduction").val();
					if(otherdeduction == ""){
						otherdeduction = "0";
					}else{
						otherdeduction = otherdeduction.replace('$','');
					}
					var netpay = parseFloat(grosspay)-parseFloat(sumfortaxAndDeduction)-parseFloat(otherdeduction);
					
					var str = netpay.toString();
					
					if(/-/.test(str) == true) {					
							var netpayresults = str.replace('-','');	
							netpayresults = parseFloat(netpayresults).toFixed(2);
					$("#netpay").val('$'+'('+netpayresults+')');
					}else{	
						str = parseFloat(str).toFixed(2);					
					$("#netpay").val('$'+str);
					}
					
		});
		
		$("#overtimehours").change(function(){
			
					var regularhoursworked = $("#regularhoursworked").val();
					var vacationhours = $("#vacationhours").val();
					var sickhours = $("#sickhours").val();
					var overtimehours = $("#overtimehours").val();
					var overtimerate = $("#overtimerate").val();
			
			//alert('1-- '+regularhoursworked+' -- '+vacationhours+' -- '+sickhours+' -- '+overtimehours+' -- '+overtimerate );
			
			if(regularhoursworked == ""){
						regularhoursworked = "0";
					}
					if(vacationhours == ""){
						vacationhours = "0";
					}
					if(sickhours == ""){
						sickhours = "0";
					}
					if(overtimehours == ""){
						overtimehours = "0";
					}
					if(overtimerate == ""){
						overtimerate = "0";
					}else{
						overtimerate = overtimerate.replace('$','');
					}
					
					var hourlywage = $("#hourlyWages").val();
					
					var temp1 = parseFloat(regularhoursworked)+parseFloat(vacationhours)+parseFloat(sickhours);
					var temp2 = parseFloat(hourlywage)*temp1;
					var temp3 = parseFloat(overtimehours)*parseFloat(overtimerate);;
					var total = temp2+temp3;
					
					var grosspay = total.toFixed(2);
				
					var finalovertimehours = parseFloat(overtimehours).toFixed(2);
					$("#overtimehours").val(finalovertimehours);
					$("#grosspay").val('$'+grosspay);
					
					var temptotaltaxesWH = $("#totaltaxesWH").val();
					var temptotalregulardeduction = $("#totalregulardeduction").val();
					
					var tempforTaxandDeduction = (parseFloat(temptotaltaxesWH)*parseFloat(grosspay))/100;
					var sumfortaxAndDeduction = parseFloat(tempforTaxandDeduction)+parseFloat(temptotalregulardeduction);
					$("#taxesanddeductions").val('$'+sumfortaxAndDeduction.toFixed(2));
					
			//employeename1 regularhoursworked vacationhours sickhours overtimehours overtimerate grosspay		
			
			var otherdeduction = $("#otherdeduction").val();
					if(otherdeduction == ""){
						otherdeduction = "0";
					}else{
						otherdeduction = otherdeduction.replace('$','');
					}
					var netpay = parseFloat(grosspay)-parseFloat(sumfortaxAndDeduction)-parseFloat(otherdeduction);					
					var str = netpay.toString();
					if(/-/.test(str) == true) {							
							var netpayresults = str.replace('-','');	
									netpayresults = parseFloat(netpayresults).toFixed(2);
					$("#netpay").val('$'+'('+netpayresults+')');
					}else{						
					 str = parseFloat(str).toFixed(2);
					$("#netpay").val('$'+str);
					}
					
		});
		
		$("#overtimerate").change(function(){
			
					var regularhoursworked = $("#regularhoursworked").val();
					var vacationhours = $("#vacationhours").val();
					var sickhours = $("#sickhours").val();
					var overtimehours = $("#overtimehours").val();
					var overtimerate = $("#overtimerate").val();
			
			if(regularhoursworked == ""){
						regularhoursworked = "0";
					}
					if(vacationhours == ""){
						vacationhours = "0";
					}
					if(sickhours == ""){
						sickhours = "0";
					}
					if(overtimehours == ""){
						overtimehours = "0";
					}
					
					if(overtimerate == ""){
						overtimerate = "0";						
					}else{
						overtimerate = overtimerate.replace('$','');
					}
					
					var hourlywage = $("#hourlyWages").val();
					
					var temp1 = parseFloat(regularhoursworked)+parseFloat(vacationhours)+parseFloat(sickhours);
					var temp2 = parseFloat(hourlywage)*temp1;
					var temp3 = parseFloat(overtimehours)*parseFloat(overtimerate);;
					var total = temp2+temp3;
						
					var grosspay = total.toFixed(2);
					var finalovertimerate = parseFloat(overtimerate).toFixed(2);
					$("#overtimerate").val('$'+finalovertimerate);
					$("#grosspay").val('$'+grosspay);
					
					var temptotaltaxesWH = $("#totaltaxesWH").val();
					var temptotalregulardeduction = $("#totalregulardeduction").val();
					
					var tempforTaxandDeduction = (parseFloat(temptotaltaxesWH)*parseFloat(grosspay))/100;
					var sumfortaxAndDeduction = parseFloat(tempforTaxandDeduction)+parseFloat(temptotalregulardeduction);
					$("#taxesanddeductions").val('$'+sumfortaxAndDeduction.toFixed(2));
					
			//employeename1 regularhoursworked vacationhours sickhours overtimehours overtimerate grosspay		
			
			var otherdeduction = $("#otherdeduction").val();
					if(otherdeduction == ""){
						otherdeduction = "0";
					}else{
						otherdeduction = otherdeduction.replace('$','');
					}
					var netpay = parseFloat(grosspay)-parseFloat(sumfortaxAndDeduction)-parseFloat(otherdeduction);
					
					var str = netpay.toString();
					
					if(/-/.test(str) == true) {
							var netpayresults = str.replace('-','');	
								netpayresults = parseFloat(netpayresults).toFixed(2); 							
					$("#netpay").val('$'+'('+netpayresults+')');
					}else{		
					 str = parseFloat(str).toFixed(2);
					$("#netpay").val('$'+str);
					}
		});
		
		$("#otherdeduction").change(function(){
			
			var otherdeduction = $("#otherdeduction").val();
			var taxesanddeductions = $("#taxesanddeductions").val();
			var grosspay = $("#grosspay").val();
			
					if(otherdeduction == ""){
						otherdeduction = "0";
					}else{
						otherdeduction = otherdeduction.replace('$','');
					}
					if(taxesanddeductions == ""){
						taxesanddeductions = "0";
					}
					if(grosspay == ""){
						grosspay = "0";
					}
			
			var temptaxesanddeductions = taxesanddeductions.replace('$',''); 
			var tempgrosspay = grosspay.replace('$','');
			
			var netpay = parseFloat(tempgrosspay)-parseFloat(temptaxesanddeductions)-parseFloat(otherdeduction);
					
					var str = netpay.toString();					
					if(/-/.test(str) == true) {					
							var netpayresults = str.replace('-','');	
						netpayresults = parseFloat(netpayresults).toFixed(2);							
					$("#netpay").val('$'+'('+netpayresults+')');
					}else{
						str = parseFloat(str).toFixed(2);
					$("#netpay").val('$'+str);
					}
					var tempotherdeduction = parseFloat(otherdeduction).toFixed(2);
					$("#otherdeduction").val('$'+tempotherdeduction);
		});
		
	
	});
	function viewPaystub(empid){
		
		window.location.assign('payroll.html?cmd=viewPayStub&empid='+empid);
	}
	

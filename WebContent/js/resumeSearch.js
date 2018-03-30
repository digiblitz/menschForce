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
		function getDocumentName(tempDocument){
			var tempDocument1 = tempDocument.split("\\");
			var size = tempDocument1.length;			
			return tempDocument1[size-1];
		}
		function formatDate(date){
			//var date_string = moment(uploadeddate, "YYYY-MM-DD").format("YYYY-MM-DD");
			var strDate = date.toString('yyyy-MM-dd');
			var date_string = strDate.split("T");
			//alert('format date function '+date_string[0]);
			return date_string[0];
		}
		
		

$(document).ready(function(){

//alert('ok');	


// validate signup form on keyup and submit
		var validator = $("#form").validate({
			rules: {				
				description: "required",				
				experience: "required",
				skillSet: "required",
				university: "required",
				college: "required",
				degree: "required",
				specialization: "required",
				certification: "required"
			},
			messages: {				
				description: "Job description is reuired",
				experience: "Experience is required",
				skillSet: "Skillset is required",
				university: "University name is required",
				college: "College name is required",
				degree: "Degree is required",
				specialization: "Specialization is required",
				certification: "Certification is required"
			},
			// the errorPlacement has to take the table layout into account
			errorPlacement: function(error, element) {
				if (element.is("none"))
					error.appendTo(element.parent().next().next());
				else
					error.appendTo(element.parent());
			}
			
			
		});
		
		

		$(".submit").click(function(){				
		
		//alert('inside click');
		$('#wait').show();	
		 $('#resume').empty();
		 
		var search = $("#search").val();
		//alert('search  '+search);
		var jobdesc = $("#jobdesc").val();
		var exp = $("#exp").val();
		var skillset = $("#skillset").val();
		var university = $("#university").val();
		var college = $("#college").val();
		var degree = $("#degree").val();
		var specialization = $("#specialization").val();
		var certification = $("#certification").val();
		
		if(search == 'all'){
			//alert('search '+search);
	if(jobdesc != "" && exp != "" && skillset != "" && university != "" && college != "" && degree != "" && specialization != "" && certification != ""){				
			myFuction();			
			}else{
				$('#wait').hide();
				$("#form").valid();
			}
		}else{			
			myFuction();
		}      
	});
	
	function myFuction(){
		
		var search = $("#search").val();
		var requestData = null;		
		if(search == 'all'){		
requestData = $("#jobdesc").val()+'%20AND%20'+$("#exp").val()+'%20AND%20'+$("#skillset").val()+'%20AND%20'+$("#university").val()+'%20AND%20'+$("#college").val()+'%20AND%20'+$("#degree").val()+'%20AND%20'+$("#specialization").val()+'%20AND%20'+$("#certification").val();		
		}else if(search == 'any'){		
		equestData = $("#jobdesc").val()+','+$("#exp").val()+','+$("#skillset").val()+','+$("#university").val()+','+$("#college").val()+','+$("#degree").val()+','+$("#specialization").val()+','+$("#certification").val();		
		}
		//alert(requestData);	
		
		$.get('resumeSearch.html?cmd=getResumes', {
        	requestData: requestData
        }, function(response) {			
			//alert(response);
			if(response != null){
        		$('#wait').hide();	

				 var json = JSON.stringify(eval("(" + response + ")"));
				var obj = JSON.parse(json);
				var temp = obj.response.docs;
				var numFound = obj.response.numFound;
				
				if(numFound != 0){
				$.each(temp, function(i, item) {					
					 
			var tempDate = item.date;						
			var uploadedDate = formatDate(tempDate);					
			var tempModifiedDate = item.last_modified;			
			var modifiedDate = formatDate(tempModifiedDate);			
			var tempDocument = item.id;
			var documents = getDocumentName(tempDocument);
						
			$('.pp').show();
            $('#resume').append($('<div class="col-md-8"><div class="form-group"><label>'+documents +'</label> </br> <label>Uploaded Date:</label>'+uploadedDate+'<br/><label>Last Modified Date:</label>'+modifiedDate+'<br/><a class="aUrl" href="resumeSearch.html?cmd=initResumeDownload&resumePath='+tempDocument+'" > Save Resume' + '</a> </div></div></br></br>'));
				});
				}else{
				 $('#wait').hide();	
				$('.pp').show();
            $('#resume').append($('<div class="col-md-8"><div class="form-group"><label> Sorry, No documents</label> </div></div></br></br>'));	
				}
        	}else{
				$('#wait').hide();	
				$('.pp').show();
            $('#resume').append($('<div class="col-md-8"><div class="form-group"><label> Sorry, No documents</label> </div></div></br></br>'));	
			}       
        });
	}	
	
$("#all").click(function(){
	validator.resetForm();
	var search = $("#all").val();	
	$("#search").val(search);		
	$("#form").show();		
	$("#submit1").hide();
	$("#submit").show();
	$(".form-validation").show();
	//alert($("#search").val());
});

$("#any").click(function(){
	validator.resetForm();
	var search = $("#any").val();
	$("#search").val(search);			
	$("#form").show();	
	$("#submit1").show();
	$("#submit").hide();
	$(".form-validation").hide();
	//alert($("#search").val());
});


});

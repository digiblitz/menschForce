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
// JavaScript Document
function isnotAlpha(str){
	stringCheck="!@#$%^&*()_+|:;{}[]<>?/=-~.,'`0123456789;:"+"\\"+"\""+"\'";
	f1=1;
	for(j=0;j<str.length;j++){
		if(stringCheck.indexOf(str.charAt(j))!=-1){
			f1=0;
		}
	}
	if(f1==0){
		return true;
	}
	else{
		return false;
	}
}

function myvalidate(){
if(document.frmRolMgtEditPriv.txtPrivName.value==""){
		alert("Privilege Name cannot be empty");
		document.frmRolMgtEditPriv.txtPrivName.focus();
		return false;
	}

	if(document.frmRolMgtEditPriv.txtPrivName.value.length>80){
		alert("Privilege Name exceeds the maximum of 80 characters");
		document.frmRolMgtEditPriv.txtPrivName.focus();
		return false;
	}

	if(isnotAlpha(document.frmRolMgtEditPriv.txtPrivName.value)){
		alert("Enter valid Privilege Name");
		document.frmRolMgtEditPriv.txtPrivName.focus();
	    	return false;
	}
	
	if(document.frmRolMgtEditPriv.txtPrivName.value.indexOf(' ')==0){
		alert("Enter Valid Privilege Name");
		document.frmRolMgtEditPriv.txtPrivName.focus();
		return false;
	}
	
	if(document.frmRolMgtEditPriv.txtPrivName.value.indexOf('  ')!==-1){
		alert("Enter Valid Privilege Name");
		document.frmRolMgtEditPriv.txtPrivName.focus();
		return false;
	}

	if(Number(document.frmRolMgtEditPriv.txtPrivName.value)){
		alert("Enter valid Privilege Name");
		document.frmRolMgtEditPriv.txtPrivName.focus();
		return false;
	}


return true;
}

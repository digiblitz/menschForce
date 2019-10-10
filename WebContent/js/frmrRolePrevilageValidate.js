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

//============================for Special char=============================

function isnotAlpha(str){
	
	stringCheck="!@#$%^&*()_+|:;{}[]<>?/=-~.,'`;:1234567890"+"\\"+"\'";
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
//-------------------------------------First name------------------------------------------------------------

	if(document.frmRoleMgtRolePrev.rolename.value==""){
		alert(" Role Name cannot be empty ");
		document.frmRoleMgtRolePrev.rolename.focus();
		return false;
	}
	
	if((document.frmRoleMgtRolePrev.rolename.value.length >80 )){
		alert("Role Name exceeds the maximum of 80 characters"); 
		document.frmRoleMgtRolePrev.rolename.focus();
		return false;
	}	


if((document.frmRoleMgtRolePrev.rolename.value.indexOf('  ')!=-1)||(document.frmRoleMgtRolePrev.rolename.value.indexOf(' ')==0)){
		alert("Enter Valid Role Name");
		document.frmRoleMgtRolePrev.rolename.focus();
		return false;
	}
	if(isnotAlpha(document.frmRoleMgtRolePrev.rolename.value)){
		alert("Enter valid Role Name");
		document.frmRoleMgtRolePrev.rolename.focus();
		return false;
	}
	
	

return true;
}

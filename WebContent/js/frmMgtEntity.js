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
//========================== For Entity Name ==================================
function mgtEntityAdd(){
	if(document.frmRoleMgtEntityCreate.txtEntityName.value==""){
		alert("Enter Entity Name.");
		document.frmRoleMgtEntityCreate.txtEntityName.focus();
		return false;
	}
	if(document.frmRoleMgtEntityCreate.txtEntityName.value.length>80){
		alert("Entered Entity Name is out of Range.");
		document.frmRoleMgtEntityCreate.txtEntityName.focus();
		return false;
	}
	if(document.frmRoleMgtEntityCreate.txtEntityName.value.indexOf(' ')==0){
		alert("Enter valid Entity Name.");
		document.frmRoleMgtEntityCreate.txtEntityName.focus();
		return false;
	}
	if(document.frmRoleMgtEntityCreate.txtEntityName.value.indexOf('  ')!==-1){
		alert("Enter valid Entity Name.");
		document.frmRoleMgtEntityCreate.txtEntityName.focus();
		return false;
	}
	return true;
}

//======================= For Edit =========================================
function mgtEntityEdit(){
	if(document.frmRoleMgtEntityEdit.txtEntityName.value==""){
		alert("Enter Entity Name.");
		document.frmRoleMgtEntityEdit.txtEntityName.focus();
		return false;
	}
	if(document.frmRoleMgtEntityEdit.txtEntityName.value.length>80){
		alert("Entered Entity Name is out of Range.");
		document.frmRoleMgtEntityEdit.txtEntityName.focus();
		return false;
	}
	if(document.frmRoleMgtEntityEdit.txtEntityName.value.indexOf(' ')==0){
		alert("Enter valid Entity Name.");
		document.frmRoleMgtEntityEdit.txtEntityName.focus();
		return false;
	}
	if(document.frmRoleMgtEntityEdit.txtEntityName.value.indexOf('  ')!==-1){
		alert("Enter valid Entity Name.");
		document.frmRoleMgtEntityEdit.txtEntityName.focus();
		return false;
	}
	return true;
}

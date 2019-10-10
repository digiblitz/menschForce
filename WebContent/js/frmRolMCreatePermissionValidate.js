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

function myvalidate(){
	
	if(document.frmRolMCreatePermission.permissionName.value==""){
		alert("Enter the PermissionName");
		document.frmRolMCreatePermission.permissionName.focus();
		return false;
		
	}
if(document.frmRolMCreatePermission.permissionName.value!=""){
	if((document.frmRolMCreatePermission.permissionName.value.indexOf('  ')!=-1)||(document.frmRolMCreatePermission.permissionName.value.indexOf(' ')==0)){
		alert("Enter the valid PermissionName");
		document.frmRolMCreatePermission.permissionName.focus();
		return false;
		
	}	
	if(document.frmRolMCreatePermission.permissionName.value.length > 80){
		alert("Enter the valid PermissionName");
		document.frmRolMCreatePermission.permissionName.focus();
		return false;
		
	}
	
}
	

	return true;
}

//-----------------------------------for frmRolMEditprocesss---------------------------------------------
function myvalidate1(){
	
	if(document.frmRolMEditPermission.permissionName.value==""){
		alert("Enter the PermissionName");
		document.frmRolMEditPermission.permissionName.focus();
		return false;
		
	}
if(document.frmRolMEditPermission.permissionName.value!=""){
	if((document.frmRolMEditPermission.permissionName.value.indexOf('  ')!=-1)||(document.frmRolMEditPermission.permissionName.value.indexOf(' ')==0)){
		alert("Enter the valid PermissionName");
		document.frmRolMEditPermission.permissionName.focus();
		return false;
		
	}	
	if(document.frmRolMEditPermission.permissionName.value.length > 80){
		alert("Enter the valid PermissionName");
		document.frmRolMEditPermission.permissionName.focus();
		return false;
		
	}
	
}
	

	return true;
}

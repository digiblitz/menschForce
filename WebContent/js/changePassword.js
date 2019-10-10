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
//--------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------
//-------------function for doublespace validation -----------------------------
var str="";
function Dospace(str){
if(str.indexOf("  ")!=-1)
{return true;}
else {return false;}
}
//------------------------------------------------------------------------------
function isnotAlphaNumeric(str){
	stringAlphaNumCheck="!@#$%^&*()_+|<>?/=-~.,;:][{}"+"\\"+"\'";
f3=1;
for(j=0;j<str.length;j++)
{if(stringAlphaNumCheck.indexOf(str.charAt(j))!=-1)
{f3=0;}}
if(f3==0)
{return true;}else{return false;}
}
//-----------------------------------------------------------------------------------------------
function myvalidate()
{
    //------------------------------------------------------------------------------------------------
//----------------------------------------Password
if(document.changePassword.currPwd.value=="")
{alert("Enter Current Password ");
 document.changePassword.currPwd.focus();
 return false;}
if(document.changePassword.currPwd.value.length<6)

{alert("Enter Valid Current Password ");
document.changePassword.currPwd.focus();
 return false;}
if(document.changePassword.currPwd.value.length>12)
{alert("Enter Valid Current Password ");
document.changePassword.currPwd.focus();
 return false;}
 	if(Dospace(document.changePassword.currPwd.value))
     { alert("Enter Current Password ");
       document.changePassword.currPwd.focus();
   return false; }
  
 /*if(isnotAlphaNumeric(document.changePassword.currPwd.value))
   { alert("Enter Valid Current Password");
     document.changePassword.currPwd.focus();
   return false; }*/

   /*strnme=document.changePassword.currPwd.value;
      	fnme=1;
      	for(j=0;j<document.changePassword.currPwd.value.length;j++)
      	{ if(stringCheck3.indexOf(strnme.charAt(j))!=-1)
      	   { fnme=0;} }
      	if(fnme==0)
      	{alert("Enter Valid Current Password ");
      	 document.changePassword.currPwd.focus();
		return false;}*/
//---------------------------------------------------------------------------------------------------
//------------------------------------NEW Password---------------------------------------
//alert(document.changePassword.newPwd.value);
if(document.changePassword.newPwd.value=="")
{alert(" Enter a New Password ");
 document.changePassword.newPwd.focus();
 return false;}
//------------------------------------RETYPE Password---------------------------------------


if(document.changePassword.reNewPwd.value=="")
{alert(" Enter Re-Type Password ");
 document.changePassword.reNewPwd.focus();
 return false;}
var pwd1=document.changePassword.newPwd.value;
var rpwd=document.changePassword.reNewPwd.value;
if(pwd1!=rpwd)
{
	alert("Password Mismatch");
	document.changePassword.reNewPwd.focus();
	return false;
}
 return true;
 
 }

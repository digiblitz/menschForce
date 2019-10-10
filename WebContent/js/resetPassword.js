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


function myvalidate()
{

//----------------------------------------Password
/*if(document.frmUserSignup.Password.value=="")
{alert("Enter Password ");
 document.frmUserSignup.Password.focus();
 return false;}

 
        if(document.frmUserSignup.Password.value.indexOf(" ")==0)
        {alert("Enter Valid Password ");
        document.frmUserSignup.Password.focus();
         return false;}

if(document.frmUserSignup.Password.value.length<6)

{alert("Enter Valid Password ");
document.frmUserSignup.Password.focus();
 return false;}
if(document.frmUserSignup.Password.value.length>12)
{alert("Enter Valid Password ");
document.frmUserSignup.Password.focus();
 return false;}
 if(Dospace(document.frmUserSignup.Password.value))
     { alert("Enter Password ");
       document.frmUserSignup.Password.focus();
   return false; }
   if(isnotAlphaNumeric(document.frmUserSignup.Password.value))
   { alert("Enter Valid Password");
     document.frmUserSignup.Password.focus();
   return false; }
   strnme=document.frmUserSignup.Password.value;
      	fnme=1;
      	for(j=0;j<document.frmUserSignup.Password.value.length;j++)
      	{ if(stringCheck3.indexOf(strnme.charAt(j))!=-1)
      	   { fnme=0;} }
      	if(fnme==0)
      	{alert("Enter Valid Password ");
      	 document.frmUserSignup.Password.focus();
	return false;}*/
       
	if(document.frmUserSignup.Password.value=="")
	{alert("Enter Password ");
	 document.frmUserSignup.Password.focus();
	 return false;}

	if(document.frmUserSignup.Password.value.indexOf(" ")==0)
        {alert("Enter Valid Password ");
        document.frmUserSignup.Password.focus();
         return false;}

		if(document.frmUserSignup.Password.value.length<6)
		
		{alert("Enter Valid Password ");
		document.frmUserSignup.Password.focus();
		 return false;}

	if(document.frmUserSignup.Password.value.length>12)
	{alert("Enter Valid Password ");
	document.frmUserSignup.Password.focus();
	 return false;}

/*	 if(isnotAlphaNumeric(document.frmUserSignup.Password.value))
	   { alert("Enter Valid Password");
		 document.frmUserSignup.Password.focus();
	   return false; }
*/

	if(document.frmUserSignup.rpwd.value=="")
	{alert(" Enter Re-Type Password ");
	 document.frmUserSignup.rpwd.focus();
	 return false;}
	var pwd1=document.frmUserSignup.Password.value;
	var rpwd=document.frmUserSignup.rpwd.value;
	if(pwd1!=rpwd)
	{
		alert("Password Mismatch");
		document.frmUserSignup.rpwd.focus();
		return false;
	}

return true;
}

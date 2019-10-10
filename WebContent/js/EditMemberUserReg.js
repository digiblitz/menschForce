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
//------------------------ALPHA NUMERIC------------------------------------------------------------------


function isnotAlphaNumeric(str){
stringAlphaNumCheck="!@#$%^&*()_+|<>?/=~.,;:][{}"+"\\"+"\'"+"\"";
f3=1;
for(j=0;j<str.length;j++)
{if(stringAlphaNumCheck.indexOf(str.charAt(j))!=-1)
{f3=0;}}
if(f3==0)
{return true;}else{return false;}
}
//------------------------DOSPACE--------------------------------------------------------------------------
function Dospace(str){
if(str.indexOf("  ")!=-1)
{return true;}
else {return false;}
}
//------------------------ISNOT ALPHA-------------------------------------------------------------------------
function isnotAlpha(str){
stringCheck="!@#$%^&*()_+|<>?/=-~.,`0123456789;:][{}"+"\\"+"\'"+"\"";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}
//-----------------------------------is not alpha1----------------------------------------------------------------
function isnotAlpha1(str){
stringCheck="!@#$%^&*|<>?/=~`;:"+"\\"+"\'"+"\"";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}

//------------------------ISNOT ALPHA city-------------------------------------------------------------------------
function isnotAlphaCity(str){
stringCheck="!@#$%^&*()_+|<>?/=~,`0123456789;:][{}"+"\\"+"\"";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}

//-------------------------function for Special Character
function isnotSpecial(str){
stringSpecialCheck="!#$%^&*()+|<>?/=~,;:][{}"+"\\"+"\'";
f4=1;
for(j=0;j<str.length;j++)
{if(stringSpecialCheck.indexOf(str.charAt(j))!=-1)
{f4=0;}}
if(f4==0)
{return true;}else{return false;}
}
//--------------------------------------------------------------------------------------------------------
//-----------------------------function for zipcode--------
function isnotZipcode(str){
	stringZip="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-";
	fzip=1;
	for(j=0;j<str.length;j++){
		if(stringZip.indexOf(str.charAt(j))!=-1){
			fzip=0;
		}
	}
	if(fzip==0){
		return false;
	}
	else{
		return true;
	}
}

//---------------------------form validation js---------------------------------------

function myvalidate(){


stringCheck="!@#$%^&*()_+`|<>?/=-~.,0123456789"+"\\";
stringCheck2="!@#$%^&*()_+`|<>?/=-~.,"+"\\";
stringCheck3=" ";

//-----------------------------------SALUTATION-----------------------------------------------------

chosen1="";
len1=document.frmMembRegi.uselect.length;
for(i=0;i<len1;i++)
{if(document.frmMembRegi.uselect[i].selected){
chosen1=document.frmMembRegi.uselect[i].value;}}
//alert(chosen1);
/*if(chosen1=="Select One" || chosen1=="")
{alert(" Select the Salutation ");
 document.frmMembRegi.uselect.focus();
 return false;}
*/
//---------------------------------------FIRST NAME----------------------------------

if(document.frmMembRegi.fname.value=="")
   {alert(" First Name cannot be empty ");
     document.frmMembRegi.fname.focus();
    return false; }
if(document.frmMembRegi.fname.value.indexOf(' ')==0)
{alert("Enter First Name ");
 document.frmMembRegi.fname.focus();
 return false; }

if(isnotAlphaCity(document.frmMembRegi.fname.value))
{ alert("Enter Valid First Name ");
  document.frmMembRegi.fname.focus();
   return false; }
if(Dospace(document.frmMembRegi.fname.value))
   { alert("Enter Valid First Name ");
     document.frmMembRegi.fname.focus();
   return false; }
if( document.frmMembRegi.fname.value.length>80 )
   { alert("Enter within 80 characters for First Name" );
      document.frmMembRegi.fname.focus();
      return false; }
	  
	  
//--------------------------MIDDLE NAME-----------------------------------------


if(isnotAlphaCity(document.frmMembRegi.mname.value))
{ alert("Enter Valid Middle Name ");
  document.frmMembRegi.mname.focus();
   return false; }
if(Dospace(document.frmMembRegi.mname.value)||((document.frmMembRegi.mname.value.indexOf(' '))==0))
   { alert("Enter Valid Middle Name ");
     document.frmMembRegi.mname.focus();
   return false; }
if( document.frmMembRegi.mname.value.length>80 )
   { alert("Enter within 80 characters for Middle Name" );
      document.frmMembRegi.mname.focus();
      return false; }

//----------------------------------LAST NAME--------------------------------------
if(document.frmMembRegi.lname.value=="")
   {alert(" Last Name cannot be empty ");
     document.frmMembRegi.lname.focus();
    return false; }
if(document.frmMembRegi.lname.value.indexOf(' ')==0)
{alert("Enter Last Name  ");
 document.frmMembRegi.lname.focus();
 return false; }

if(isnotAlphaCity(document.frmMembRegi.lname.value))
{ alert("Enter Valid Last Name ");
  document.frmMembRegi.lname.focus();
   return false; }
if(Dospace(document.frmMembRegi.lname.value))
   { alert("Enter Valid Last Name ");
     document.frmMembRegi.lname.focus();
   return false; }
if( document.frmMembRegi.lname.value.length>80 )
   { alert("Enter within 80 characters of Last Name" );
      document.frmMembRegi.lname.focus();
      return false; }

//--------------------------------SUFFIX---------------------------------------


if(document.frmMembRegi.sname.value.indexOf(' ')==0)
{alert("Enter Suffix ");
 document.frmMembRegi.sname.focus();
 return false; }

/*
if(isnotAlphaNumeric(document.frmMembRegi.sname.value))
{ alert("Enter Valid Suffix ");
  document.frmMembRegi.sname.focus();
   return false; }
*/
if(Dospace(document.frmMembRegi.sname.value))
   { alert("Enter Valid Suffix ");
     document.frmMembRegi.sname.focus();
   return false; }
if( document.frmMembRegi.sname.value.length>20 )
   { alert("Enter within 20 characters for Suffix" );
      document.frmMembRegi.sname.focus();
      return false; }


//___________________________________________DATE________________________________________________________
if(document.frmMembRegi.birthmonth.value=="")
{alert(" Select Month in Date of Birth ");
 document.frmMembRegi.birthmonth.focus();
 return false;}
 if(!(document.frmMembRegi.birthmonth.value==""))
 { leno=document.frmMembRegi.birthmonth.length;
  for(i=0;i<leno;i++)
  { if(document.frmMembRegi.birthmonth[i].selected)
 { choseno=document.frmMembRegi.birthmonth[i].value ;}}}
 
 //------month checking----
 var lyear=document.frmMembRegi.birthyear.value;
 var lcheck=(lyear%4);

if((document.frmMembRegi.birthmonth.value== 2)&&(document.frmMembRegi.birthday.value >29)&&(lcheck=="0"))
 {
	alert ("Date is not valid") ;
	document.frmMembRegi.birthday.focus();
	return false;
 }
var lyear1=document.frmMembRegi.birthyear.value;
 var lcheck1=(lyear1%4);
 
 if((document.frmMembRegi.birthmonth.value== 2)&&(document.frmMembRegi.birthday.value >28)&&(lcheck1>0))
 {
	alert ("Date is not valid") ;
	document.frmMembRegi.birthday.focus();
	return false;
 }
 if((document.frmMembRegi.birthmonth.value== 4)&&(document.frmMembRegi.birthday.value=="31"))
 {
	alert ("Date is not valid") ;
	document.frmMembRegi.birthday.focus();
	return false;
 }
 if((document.frmMembRegi.birthmonth.value== 6)&&(document.frmMembRegi.birthday.value=="31"))
 {
	alert ("Date is not valid") ;
	document.frmMembRegi.birthday.focus();
	return false;
 }
 if((document.frmMembRegi.birthmonth.value== 9)&&(document.frmMembRegi.birthday.value=="31"))
 {
	alert ("Date is not valid") ;
	document.frmMembRegi.birthday.focus();
	return false;
 }
 if((document.frmMembRegi.birthmonth.value== 11)&&(document.frmMembRegi.birthday.value=="31"))
 {
	alert ("Date is not valid") ;
	document.frmMembRegi.birthday.focus();
	return false;
 }


 if(document.frmMembRegi.birthday.value=="")
 {alert(" Select Day in Date of Birth");
  document.frmMembRegi.birthday.focus();
  return false;}
  if(!(document.frmMembRegi.birthday.value==""))
  { leno=document.frmMembRegi.birthday.length;
   for(i=0;i<leno;i++)
   { if(document.frmMembRegi.birthday[i].selected)
  { choseno=document.frmMembRegi.birthday[i].value ;}}}


 if(document.frmMembRegi.birthyear.value=="")
 {alert(" Select Year in Date of Birth");
  document.frmMembRegi.birthyear.focus();
  return false;}
  if(!(document.frmMembRegi.birthyear.value==""))
  { leno=document.frmMembRegi.birthyear.length;
   for(i=0;i<leno;i++)
   { if(document.frmMembRegi.birthyear[i].selected)
  { choseno=document.frmMembRegi.birthyear[i].value ;}}}

 if(document.getElementById("gender"))
 {
chosen="";
len = document.frmMembRegi.gender.length ;
for(i=0;i<len;i++)
{if(document.frmMembRegi.gender[i].checked)
  { chosen= document.frmMembRegi.gender[i].value; }
}
if(chosen=="")
{alert("Select the Gender");
 
 return false;
}
 
 }
 
if(document.frmMembRegi.email.value=="")
 {alert("Enter your Email ID");
  document.frmMembRegi.email.focus();
 return false;}
 
 if(!(document.frmMembRegi.email.value== ""))
 { strmail=document.frmMembRegi.email.value;
 firstat = strmail.indexOf("@");
 lastat = strmail.lastIndexOf("@");
 strmain=strmail.substring(0,firstat);
 strsub=strmail.substring(firstat,document.frmMembRegi.email.value.length);
 if(strmail.length>120)
 {alert("Email is out of range");
  document.frmMembRegi.email.focus();
 return false;}
 if(strmain.indexOf('  ')!=-1 || firstat==0 || strsub.indexOf(' ')!=-1 )
 {alert("Enter vaemail Email ");
  document.frmMembRegi.email.focus();
 return false;}
 if(isnotSpecial(strmain) || isnotSpecial(strsub))
 {alert("Enter valid Email ");
  document.frmMembRegi.email.focus();
 return false;}
 k=0;
 strlen=strsub.length;
 for(i=0;i<strlen-1;i++)
 { if(strsub.charAt(i)=='.')
 {k=k+1;}}
 if(k>3)
 {alert("Enter valid Email ");
  document.frmMembRegi.email.focus();
 return false;}
 if(firstat==-1 || lastat==-1)
 {alert("Enter valid Email" );
  document.frmMembRegi.email.focus();
 return false;}
 if(Number(strmain))
 {alert("Enter valid Email ");
  document.frmMembRegi.email.focus();
  return false;}
 if(firstat != lastat )
 {alert("Enter valid Email ");
  document.frmMembRegi.email.focus();
 return false;}
 firstdot=strmail.indexOf(".",firstat);
 lastdot=strmail.lastIndexOf(".");
 if(firstdot == -1 || lastdot == -1 || lastdot==strmail.length-1)
 {alert("Enter valid Email ");
  document.frmMembRegi.email.focus();
  return false;}
}

//-------------------- login name --------------------------
//alert(document.frmMembRegi.sessionLogName.value);

if(document.frmMembRegi.usrname.value=="" || document.frmMembRegi.usrname.value.indexOf(" ") == 0) 
 {
	alert("Enter your Login Name");
  document.frmMembRegi.usrname.focus();
 return false;}

if(isnotAlphaNumeric(document.frmMembRegi.usrname.value))
 {alert("Enter a valid User Name");
  document.frmMembRegi.usrname.focus();
 return false;}

if(document.frmMembRegi.usrname.value.length>25)
 {alert("Enter a valid User Name");
  document.frmMembRegi.usrname.focus();
 return false;}
 
 if(document.frmMembRegi.usrname.value.length<4)
 {alert("Enter a valid User Name");
  document.frmMembRegi.usrname.focus();
 return false;}
 
// ----------------------- sec Qusetion -------------------
   
   if(document.frmMembRegi.QSelect.value=="")
	{
		alert(" Please select any Secret Qusetion");
	 	document.frmMembRegi.QSelect.focus();
 		return false;
	}
	
	// ----------------------- sec Answer -------------------
   
   if(document.frmMembRegi.ans.value=="")
	{
		alert("Please fill a Secret Answer");
	 	document.frmMembRegi.ans.focus();
 		return false;
	}
	
	 if(document.frmMembRegi.ans.value.indexOf(" ")==0)
	{
		alert("Please fill a Secret Answer");
	 	document.frmMembRegi.ans.focus();
 		return false;
	}


//--------------------- new Pwd  Status-----------------------------
//if(document.frmMembRegi.pwdStat.value=="empty"){
	if(document.frmMembRegi.newPwd.value==""){
		alert("Password cannot be empty");
		document.frmMembRegi.newPwd.focus();
		return false;
	}
//}
	
//--------------------- new Pwd -----------------------------
if(document.frmMembRegi.newPwd.value!="") 
 {

if(document.frmMembRegi.newPwd.value.indexOf(" ") == 0)
{alert("Enter Valid New Password ");
document.frmMembRegi.newPwd.focus();
 return false;}

if(document.frmMembRegi.newPwd.value.length<6)
{alert("New Password Should be atleast 6 Characters");
document.frmMembRegi.newPwd.focus();
 return false;}
 
if(document.frmMembRegi.newPwd.value.length>12)
{alert("Enter Valid New Password ");
document.frmMembRegi.newPwd.focus();
 return false;}
 	
/*   if(isnotAlphaNumeric(document.frmMembRegi.newPwd.value))
   { alert("Enter Valid New Password");
     document.frmMembRegi.newPwd.focus();
   return false; }*/
   
   //----------------- conf pwd -----------------------
   
   if(document.frmMembRegi.confPwd.value=="")
{alert(" Enter Re-Type Password ");
 document.frmMembRegi.confPwd.focus();
 return false;}
var pwd1=document.frmMembRegi.newPwd.value;
var rpwd=document.frmMembRegi.confPwd.value;
if(pwd1!=rpwd)
{
	alert("Password Mismatch");
	document.frmMembRegi.confPwd.value="";
	document.frmMembRegi.confPwd.focus();
	return false;
}
   
 }
 
//--------------------------------------------------primary----------------------------------------------------
//____________________________________________________Address1_____________________________________________________


if(document.frmMembRegi.padd_txt.value=="")
{alert(" Enter Primary Address ");
 document.frmMembRegi.padd_txt.focus();
 return false;}
if(document.frmMembRegi.padd_txt.value.length>255)
{alert("Enter Primary Address with in 255 characters");
document.frmMembRegi.padd_txt.focus();
return false;}
if(Dospace(document.frmMembRegi.padd_txt.value))
 	   { alert("Enter Valid Primary Address ");
 	     document.frmMembRegi.padd_txt.focus();
	     return false; }
		 
	if(document.frmMembRegi.padd_txt.value.indexOf(' ')==0){
		alert("Enter Valid Primary Address ");
		document.frmMembRegi.padd_txt.focus();
		return false;
		}
//____________________________________________________Address2_____________________________________________________

if(document.frmMembRegi.padd_txt2.value.length>255)
{alert("Enter Primary Address with in 255 characters");
document.frmMembRegi.padd_txt2.focus();
return false;
}
	if(Dospace(document.frmMembRegi.padd_txt2.value)){
		alert("Enter Valid Primary Address ");
		document.frmMembRegi.padd_txt2.focus();
		return false; 
	}   
	if(document.frmMembRegi.padd_txt2.value.indexOf(" ")==0){
		alert("Enter Valid Primary Address ");
		document.frmMembRegi.padd_txt2.focus();
		return false;
	}
//__________________________________________city___________________________________________________________________


	
	if(document.frmMembRegi.pcity_txt.value=="")
	   {alert(" Enter City Name in Primary Address ");
	     document.frmMembRegi.pcity_txt.focus();
	    return false; }
	if(document.frmMembRegi.pcity_txt.value.indexOf(' ')==0)
	{alert("Enter City ");
	 document.frmMembRegi.pcity_txt.focus();
	 return false; }
	
	if(isnotAlphaCity(document.frmMembRegi.pcity_txt.value))
	{ alert("Enter Valid City in Primary Address ");
	  document.frmMembRegi.pcity_txt.focus();
	   return false; }
	if(Dospace(document.frmMembRegi.pcity_txt.value))
	   { alert("Enter Valid City in Primary Address");
	     document.frmMembRegi.pcity_txt.focus();
	   return false; }
	if( document.frmMembRegi.pcity_txt.value.length>80 )
	   { alert("Enter within 80 characters for City in Primary Address" );
	      document.frmMembRegi.pcity_txt.focus();
      return false; }
		

	 //___________________________________________________ZipCode_________________________________________________________
/*if(document.frmMembRegi.pzip_txt.value==""){
		alert("Enter Zipcode in Primary Address");
		document.frmMembRegi.pzip_txt.focus();
		return false;
	}
      if(!Number(document.frmMembRegi.pzip_txt.value))
        {alert("Enter valid Zipcode in Primary Address ");
        document.frmMembRegi.pzip_txt.focus();
        return false;}

        if((document.frmMembRegi.pzip_txt.value.length <5 ))
        {alert("Enter Valid Zipcode in Primary Address");
         document.frmMembRegi.pzip_txt.focus();
        return false;}
 
         if((document.frmMembRegi.pzip_txt.value.length >6 ))
            {alert("Enter Valid Zipcode in Primary Address");
            document.frmMembRegi.pzip_txt.focus();
            return false;}
        if(Dospace(document.frmMembRegi.pzip_txt.value))
 	   { alert("Enter Valid Zipcode in Primary Address ");
 	     document.frmMembRegi.pzip_txt.focus();
	   return false; }*/
	   
	   //--- latest 
	   	   
if(document.frmMembRegi.pzip_txt.value==""){
		alert("Enter Zipcode in Primary Address");
		document.frmMembRegi.pzip_txt.focus();
		return false;
	    }
if(isnotZipcode(document.frmMembRegi.pzip_txt.value))
        {alert("Enter valid Zipcode in Primary Address ");
        document.frmMembRegi.pzip_txt.focus();
        return false;}

     /* if(!Number(document.frmMembRegi.pzip_txt.value))
        {alert("Enter valid Zipcode in Primary Address ");
        document.frmMembRegi.pzip_txt.focus();
        return false;}
	if(isnotAlphaNumeric(document.frmMembRegi.pzip_txt.value)){
	    alert("Enter Valid Zipcode in Primary Address");
        document.frmMembRegi.pzip_txt.focus();
        return false; 
		}*/
		
    if((document.frmMembRegi.pzip_txt.value.length <3&& document.frmMembRegi.pcountry_sel.value!="USA")){
		alert("Enter Valid Zipcode in Primary Address");
        document.frmMembRegi.pzip_txt.focus();
        return false;
		}
 
    if((document.frmMembRegi.pzip_txt.value.length >20 && document.frmMembRegi.pcountry_sel.value!="USA")){
		alert("Enter Valid Zipcode in Primary Address");
        document.frmMembRegi.pzip_txt.focus();
        return false;
		}
		
	if((document.frmMembRegi.pzip_txt.value.length !=5&& document.frmMembRegi.pcountry_sel.value=="USA")){
		alert("Enter Valid Zipcode in Primary Address");
		document.frmMembRegi.pzip_txt.focus();
		return false;
	}
       /*if(document.frmMembRegi.pzip_txt.value.indexOf('.')!=-1){
			alert("Enter Valid Zipcode in Primary Address.");
			document.frmMembRegi.pzip_txt.focus();
			return false;
		}*/
	if(document.frmMembRegi.pzip_txt.value.indexOf(" ")==0){
		alert("Enter Valid Zipcode in Primary Address");
		document.frmMembRegi.pzip_txt.focus();
		return false; 
	}
//================================Country=========================================================
	if ( document.frmMembRegi.pcountry_sel.selectedIndex == 0 ){
			 alert ( "Please select Country Name in Primary Address" );
			document.frmMembRegi.pcountry_sel.focus();
			 return false;
		 }
	 
//__________________________________________________________________________________________________________________
//___________________________________________________State______________________________________________________
if ( document.frmMembRegi.pstate_sel.selectedIndex == 0 ){
         alert ( "Please select  State Name in Primary Address" );
 		document.frmMembRegi.pstate_sel.focus();
         return false;
     }
/*


 	var cdln = "";
 	if(typeof(window.document.frmMembRegi.pcountry_sel) == 'object'){
 		if (window.document.frmMembRegi.pcountry_sel.value != ""){
 			var cemail;
 			cemail = window.document.frmMembRegi.pcountry_sel[window.document.frmMembRegi.pcountry_sel.selectedIndex].value;
 			cdln = cemail;
 		}
 		if(cdln == "Select One"){
 			alert("Please select Country in Primary Address");
 			window.document.frmMembRegi.pcountry_sel.focus();
 			return false;
 		}
 	}

 	if ( document.frmMembRegi.pcountry_sel.selectedIndex == 0 ){
         alert ( "Please select Country Name in Primary Address" );
 		document.frmMembRegi.pcountry_sel.focus();
         return false;
     }

 	var edln = "";
 	if(typeof(window.document.frmMembRegi.pstate_sel) == 'object'){
 		if (window.document.frmMembRegi.pstate_sel.value != ""){
 			var email;
 			email = window.document.frmMembRegi.pstate_sel[window.document.frmMembRegi.pstate_sel.selectedIndex].value;
 			edln = email;
 		}
// 		if(edln.length == 1 )
                if(edln=""){
 			alert("Please select State in Primary Address");
 			window.document.frmMembRegi.pstate_sel.focus();
 			return false;
 		}
 	}
*/


//-------------------------------------------------------------------------------------------------------
//----------------------IF ENTER EITHER PHONE OR MOBILE NUMBER
if(document.frmMembRegi.pphone_txt.value=="" )
{ alert("Enter Primary Phone Number");
  document.frmMembRegi.pphone_txt.focus();
  return false;}
  if(Dospace(document.frmMembRegi.pphone_txt.value)){
			alert("Enter Valid Phone Number in Primary Address");
	     	document.frmMembRegi.pphone_txt.focus();
	  		return false;
			}
	if(document.frmMembRegi.pphone_txt.value.indexOf(" ")==0){
		alert("Enter Valid Phone Number in Primary Address");
		document.frmMembRegi.pphone_txt.focus();
		return false; 
	}

//--------------------------------Primary Phone Number---------------------------------------


if(document.frmMembRegi.pphone_txt.value!=""){
 len7=document.frmMembRegi.pphone_txt.value.length;
 strnum = document.frmMembRegi.pphone_txt.value;
  var GoodChars = "0123456789()- ";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid Primary Phone Number");
 document.frmMembRegi.pphone_txt.focus();
 return false;}
 if(document.frmMembRegi.pphone_txt.value.length>40)
  {alert("Enter valid Primary Phone Number");
  document.frmMembRegi.pphone_txt.focus();
  return false;}
}

//----------------------------------MOBILE---------------------------------------


if(document.frmMembRegi.pmob_txt.value!="")
{		
        
len7=document.frmMembRegi.pmob_txt.value.length;
 strnum = document.frmMembRegi.pmob_txt.value;
  var GoodChars = "0123456789()- ";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid Primary Mobile Number");
 document.frmMembRegi.pmob_txt.focus();
 return false;}
 if(document.frmMembRegi.pmob_txt.value.length>40)
  {alert("Enter valid Primary Mobile Number");
  document.frmMembRegi.pmob_txt.focus();
  return false;}
 
}


 //-----------------------------------------FAX--------------------------------
 


if(document.frmMembRegi.pfax_txt.value!="")
{
  len7=document.frmMembRegi.pfax_txt.value.length;
 strnum = document.frmMembRegi.pfax_txt.value;
  var GoodChars = "0123456789()- ";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid Primary Fax Number");
 document.frmMembRegi.pfax_txt.focus();
 return false;}
 if(document.frmMembRegi.pfax_txt.value.length>40)
  {alert("Enter valid Primary Fax Number");
  document.frmMembRegi.pfax_txt.focus();
  return false;}
  
 }


//------------------------------------------------------SECONDARY-----------------------------------------------------
//____________________________________________________Address1_____________________________________________________

if(document.frmMembRegi.secAdd_cbx.checked){

if(document.frmMembRegi.sadd_txt.value=="")
{alert(" Enter Secondary Address ");
 document.frmMembRegi.sadd_txt.focus();
 return false;}
	if(document.frmMembRegi.sadd_txt.value.length>255){
		alert("Enter Secondary Address with in 255 characters");
		document.frmMembRegi.sadd_txt.focus();
		return false;
	}
	if(Dospace(document.frmMembRegi.sadd_txt.value)){
		alert("Enter Valid Secondary Address ");
		document.frmMembRegi.sadd_txt.focus();
		return false; 
	}
	if(document.frmMembRegi.sadd_txt.value.indexOf(" ")==0){
		alert("Enter Valid Secondary Address");
		document.frmMembRegi.sadd_txt.focus();
		return false; 
	}
//____________________________________________________Address2_____________________________________________________

if(document.frmMembRegi.sadd_txt1.value.length>255)
{alert("Enter Secondary Address with in 255 characters");
document.frmMembRegi.sadd_txt1.focus();
return false;}
	if(Dospace(document.frmMembRegi.sadd_txt1.value)){
		alert("Enter Valid Secondary Address ");
		document.frmMembRegi.sadd_txt1.focus();
		return false; 
	}
	if(document.frmMembRegi.sadd_txt1.value.indexOf(" ")==0){
		alert("Enter Valid Secondary Address");
		document.frmMembRegi.sadd_txt1.focus();
		return false; 
	}
	//__________________________________________city___________________________________________________________________


	
	if(document.frmMembRegi.scity_txt.value=="")
	   {alert(" City cannot be empty in Secondary Address");
	     document.frmMembRegi.scity_txt.focus();
	    return false; }
	if(document.frmMembRegi.scity_txt.value.indexOf(' ')==0)
	{alert("Enter City in Secondary Address");
	 document.frmMembRegi.scity_txt.focus();
	 return false; }
	
	if(isnotAlphaCity(document.frmMembRegi.scity_txt.value))
	{ alert("Enter Valid City in Secondary Address");
	  document.frmMembRegi.scity_txt.focus();
	   return false; }
	if(Dospace(document.frmMembRegi.scity_txt.value))
	   { alert("Enter  Valid City in Secondary Address ");
	     document.frmMembRegi.scity_txt.focus();
	   return false; }
	if( document.frmMembRegi.scity_txt.value.length>80 )
	   { alert("Enter within 80 characters for City in Secondary Address" );
	      document.frmMembRegi.scity_txt.focus();
      return false; }

//___________________________________________________ZipCode_________________________________________________________

		if(document.frmMembRegi.szip_txt.value==""){
			alert("Enter Zipcode in Secondary Address");
			document.frmMembRegi.szip_txt.focus();
			return false;
	    }
     	if(isnotZipcode(document.frmMembRegi.szip_txt.value)){
			alert("Enter valid Zipcode in Secondary Address");
			document.frmMembRegi.szip_txt.focus();
			return false;
	    }	
    	if((document.frmMembRegi.szip_txt.value.length <3&& document.frmMembRegi.sCountry_sel.value!="USA")){
			alert("Enter Valid Zipcode in Secondary Address");
			document.frmMembRegi.szip_txt.focus();
			return false;
		}
 
    	if((document.frmMembRegi.szip_txt.value.length >20 && document.frmMembRegi.sCountry_sel.value!="USA")){
			alert("Enter Valid Zipcode in Secondary Address");
			document.frmMembRegi.szip_txt.focus();
			return false;
		}
		 if((document.frmMembRegi.szip_txt.value.length !=5&& document.frmMembRegi.sCountry_sel.value=="USA")){
			alert("Enter Valid Zipcode in Secondary Address");
			document.frmMembRegi.szip_txt.focus();
			return false;
		}
 		       
		if(document.frmMembRegi.szip_txt.value.indexOf(" ")==0){
			alert("Enter Valid Zipcode in Secondary Address");
			document.frmMembRegi.szip_txt.focus();
			return false; 
		}
//___________________________________________________Country______________________________________________________


if(document.frmMembRegi.scountry_txt.selectedIndex == 0)
	   { alert(" Country cannot be empty in Secondary Address");
	     document.frmMembRegi.scountry_txt.focus();
	     return false; }
/*	if(document.frmMembRegi.scountry_txt.value.indexOf(' ')==0)
	{alert("Enter Country  in Secondary Address ");
	 document.frmMembRegi.scountry_txt.focus();
	 return false; }
	
	if(isnotAlpha(document.frmMembRegi.scountry_txt.value))
	{ alert("Enter Valid Country  in Secondary Address ");
	  document.frmMembRegi.scountry_txt.focus();
	   return false; }
	if(Dospace(document.frmMembRegi.scountry_txt.value))
	   { alert("Enter Valid Country  in Secondary Address ");
	     document.frmMembRegi.scountry_txt.focus();
	   return false; }
	if( document.frmMembRegi.scountry_txt.value.length>80 )
	   { alert("Enter within 80 characters for Country  in Secondary Address" );
	      document.frmMembRegi.scountry_txt.focus();
      return false; }*/

//------------------------------------------------State-------------------------------------------------------------	 
	if(document.frmMembRegi.sstate_txt.selectedIndex == 0)
	   { alert(" State cannot be empty in Secondary Address");
	     document.frmMembRegi.sstate_txt.focus();
	     return false; }
/*	if(document.frmMembRegi.sstate_txt.value.indexOf(' ')==0)
	{alert("Enter State in Secondary Address");
	 document.frmMembRegi.sstate_txt.focus();
	 return false; }
	
	if(isnotAlpha(document.frmMembRegi.sstate_txt.value))
	{ alert("Enter  Valid State in Secondary Address");
	  document.frmMembRegi.sstate_txt.focus();
	   return false; }
	if(Dospace(document.frmMembRegi.sstate_txt.value))
	   { alert("Enter  Valid State in Secondary Address ");
	     document.frmMembRegi.sstate_txt.focus();
	   return false; }
	if( document.frmMembRegi.sstate_txt.value.length>80 )
	   { alert("Enter within 80 characters for State in Secondary Address" );
	      document.frmMembRegi.sstate_txt.focus();
      return false; }	 
*/
		
//-------------------------------------------------------------------------------------------------------
//----------------------IF ENTER EITHER PHONE OR MOBILE NUMBER in secondary Address
if(document.frmMembRegi.sphone_txt.value=="" )
{ alert("Enter Secondary Phone Number");
  document.frmMembRegi.sphone_txt.focus();
  return false;}
  if(Dospace(document.frmMembRegi.sphone_txt.value)){
			alert("Enter Valid Phone Number in Secondary Address");
	     	document.frmMembRegi.sphone_txt.focus();
	  		return false;
			}
	if(document.frmMembRegi.sphone_txt.value.indexOf(" ")==0){
		alert("Enter Valid Phone Number in Secondary Address");
		document.frmMembRegi.sphone_txt.focus();
		return false; 
	}

//--------------------------------Secondary Phone Number---------------------------------------


if(document.frmMembRegi.sphone_txt.value!=""){
	len7=document.frmMembRegi.sphone_txt.value.length;
	strnum = document.frmMembRegi.sphone_txt.value;
	var GoodChars = "0123456789()- ";
	valid = 1;
	for(j=0;j<len7;j++){
		if(GoodChars.indexOf(strnum.charAt(j))==-1){
			valid=0;
		}
	}
	if(valid!=1){
		alert("Enter valid Secondary Phone Number");
		document.frmMembRegi.sphone_txt.focus();
		return false;
	}
	if(document.frmMembRegi.sphone_txt.value.length>40){
		alert("Enter valid Secondary Phone Number");
		document.frmMembRegi.sphone_txt.focus();
		return false;
	}
}


	

//----------------------------------MOBILE in secondary Address---------------------------------------


if(document.frmMembRegi.smob_txt.value!="")
{		
        
 len7=document.frmMembRegi.smob_txt.value.length;
	strnum = document.frmMembRegi.smob_txt.value;
	var GoodChars = "0123456789()- ";
	valid = 1;
	for(j=0;j<len7;j++){
		if(GoodChars.indexOf(strnum.charAt(j))==-1){
			valid=0;
		}
	}
	if(valid!=1){
		alert("Enter valid Secondary Mobile Number");
		document.frmMembRegi.smob_txt.focus();
		return false;
	}
	if(document.frmMembRegi.smob_txt.value.length>40){
		alert("Enter valid Secondary Mobile Number");
		document.frmMembRegi.smob_txt.focus();
		return false;
	}
 
}


 //-----------------------------------------FAX in secondary Address--------------------------------
 


if(document.frmMembRegi.sfax_txt.value!="")
{		
  
 len7=document.frmMembRegi.sfax_txt.value.length;
 strnum = document.frmMembRegi.sfax_txt.value;
  var GoodChars = "0123456789()- ";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid Secondary Fax Number");
 document.frmMembRegi.sfax_txt.focus();
 return false;}
 if(document.frmMembRegi.sfax_txt.value.length>40)
  {alert("Enter valid Secondary Fax Number");
  document.frmMembRegi.sfax_txt.focus();
  return false;}
 
}

}

}

//================== add on 1 ==================================
 
  function nonUserDetails(param){
	  //alert('hi');
//					alert(param.value);
						if(param.value.length==0 || param.value.indexOf(" ")==0)
						return;
							var  url = null;
							url = "annualAjax.do?method=memDetails&nonmemberId="+escape(param.value);
						//alert(url);

							if (window.ActiveXObject){ 
								req = new ActiveXObject("Microsoft.XMLHTTP"); 
							} 
							else if (window.XMLHttpRequest){ 
								req = new XMLHttpRequest(); 
							} 
						
							req.onreadystatechange = processRequest;         
							req.open("GET", url, true);
							req.send(null);  
				} 
				   
                     function processRequest(){ 
						if (req.readyState == 4){ 
						   //alert(req.readyState);
							if(req.status == 200){ 
							//alert(req.status);
								//clearFields();
								//get the XML send by the servlet 
								    var xmlDoc = req.responseXML.documentElement;
									var fName =xmlDoc.getElementsByTagName('userfirstName')[0].childNodes[0].nodeValue;
									var lName =xmlDoc.getElementsByTagName('userlastName')[0].childNodes[0].nodeValue; 
									var ph =xmlDoc.getElementsByTagName('userphone')[0].childNodes[0].nodeValue; 
									//var usId =xmlDoc.getElementsByTagName('useruserId')[0].childNodes[0].nodeValue; 
									//var usrdob =xmlDoc.getElementsByTagName('userdob')[0].childNodes[0].nodeValue;
									//var usrage =xmlDoc.getElementsByTagName('userage')[0].childNodes[0].nodeValue; 

									
							} 
							if(req.status==500) {
							 
							  return;
							}
							else{ 
							//alert("document.frmMembRegi.usrSessionNam.value :"+document.frmMembRegi.usrSessionNam.value);
							//alert("document.frmMembRegi.usrname.value :"+document.frmMembRegi.usrname.value);
							                                                            
							  alert("UserName Exists Choose Another");
							  clear();
							  document.frmMembRegi.usrname.focus();
							  return false;
                                                          
							} 
							  
						}	
					} 
		          function clear() {
						document.getElementById("usrname").value = "";
						
						
					}


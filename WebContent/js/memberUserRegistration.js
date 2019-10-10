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
//------------------------------ User Registration Ajax Script -------------------------

var arHttpRequest;

function usrdetails()
{

if(document.frmMembRegi.usrname.value!="" && document.frmMembRegi.usrname.value.indexOf(" ")!=0)
{

    var email=document.frmMembRegi.usrname.value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
            
        var url = "./RiderInfoAjaxAction.do?userName="+email; 

        if (window.ActiveXObject) 
        { 
            arHttpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
           
        } 
        else if (window.XMLHttpRequest) 
        { 
            arHttpRequest = new XMLHttpRequest(); 
        } 
     
        arHttpRequest.open("POST", url, true); 
        
        arHttpRequest.onreadystatechange = function() {emailRequest(); } ; 
        arHttpRequest.send(null); 
   } 
}
   
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function emailRequest() 
    { 
   
        if (arHttpRequest.readyState == 4) 
        { 
            if(arHttpRequest.status == 200) 
            { 
                //get the XML send by the servlet 
                 var arnameXML = arHttpRequest.responseXML.getElementsByTagName("email")[0]; 
                 var arnameText = arnameXML.childNodes[0].nodeValue; 
                 
              //  alert(arnameText);
                if(arnameText!=null)
                {    
                alert("Login Name Exists.Choose Another !"); 
            	document.frmMembRegi.usrname.focus();
                return false;
                }    
            } 
            else 
            { 
                alert("Error loading page\n"+ arHttpRequest.status +":"+ arHttpRequest.statusText); 
            } 
        } 
    } 

//-------------------------
//-------------------function for character validation in Names------------------
function isnotName(str){
stringCheck="!@#$%^&*()_+|<>?/=~,`0123456789;:][{}"+"\\";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}


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
//--------------------------------------isnotAlpha1---------------------------------------------------------------
function isnotAlpha1(str){
stringCheck="!@#$%^&*()+|<>?/=-~,`0123456789;:][{}"+"\\"+"\"";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}
//------------------------isnotAlpha2-------------------------
function isnotAlpha2(str){
stringCheck="!@#$%^&*()+|<>?/=_~,`0123456789;:][{}"+"\\"+"\"";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}
//------------------------ISNOT ALPHA-------------------------------------------------------------------------

function isnotAlpha(str){
stringCheck="!@#$%^&*()_+|<>?/=~,`0123456789;:][{}"+"\\"+"\"";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}

//-----------------------------------is not alpha1----------------------------------------------------------------
function isnotAlpha1(str){
stringCheck="!@#$%^&*|<>?/=~.,`;:][{}"+"\\"+"\'"+"\"";
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
//----------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------



function validate(){


	stringCheck="!@#$%^&*()_+`|<>?/=-~.,0123456789"+"\\";
	stringCheck2="!@#$%^&*()_+`|<>?/=-~.,"+"\\";
	stringCheck3=" ";




	//-----------------------------------SALUTATION-----------------------------------------------------
	/*if(document.frmMembRegi.USelect.value=="")
	{alert(" Select the Salutation ");
	 document.frmMembRegi.USelect.focus();
	 return false;}
	*/

	/*if(!(document.frmMembRegi.USelect.value==""))
	{ leno=document.frmMembRegi.USelect.length;
	 for(i=0;i<leno;i++)
	 { if(document.frmMembRegi.USelect[i].selected)
	 { choseno=document.frmMembRegi.USelect[i].value ;}}


	}*/

	//---------------------------------------FIRST NAME----------------------------------

	if(document.frmMembRegi.fname.value.length ==0)
	   {//alert(" First Name cannot be empty ");*/
	     document.frmMembRegi.fname.focus();
	     document.getElementById("fname").innerHTML = "First Name cannot be empty";
	    return false; } 
		else{
	  document.frmMembRegi.fname.focus();
	  document.getElementById("fname").innerHTML = "";
	   } 
	if(document.frmMembRegi.fname.value.indexOf(' ')==0)
	{/*alert("Enter First Name ");*/
	 document.frmMembRegi.fname.focus();
	 document.getElementById("fname").innerHTML = "Enter First Name";
	 return false; }
	 else{
	 document.frmMembRegi.fname.focus();
	 document.getElementById("fname").innerHTML = ""; 
	 }
	if(isnotAlpha(document.frmMembRegi.fname.value))
	{ /*alert("Enter Valid First Name ");*/
	  document.frmMembRegi.fname.focus();
	  document.getElementById("fname").innerHTML = "Enter Valid First Name";
	   return false; }
	   else{
	   document.frmMembRegi.fname.focus();
	  document.getElementById("fname").innerHTML = "";
	    }
	if(Dospace(document.frmMembRegi.fname.value))
	   { /*alert("Enter Valid First Name ");*/
	     document.frmMembRegi.fname.focus();
		 document.getElementById("fname").innerHTML = "Enter Valid First Name ";
	   return false; }
	   else
	   {
	  document.frmMembRegi.fname.focus();
	  document.getElementById("fname").innerHTML = "";
	   }
	if( document.frmMembRegi.fname.value.length>20 )
	   { /*alert("Enter within 80 characters for First Name" );*/
	      document.frmMembRegi.fname.focus();
		  document.getElementById("fname").innerHTML = "Enter within 20 characters for First Name";
	      return false; }
		  else{
		  document.frmMembRegi.fname.focus();
		  document.getElementById("fname").innerHTML = "";
		  }


	//--------------------------MIDDLE NAME-----------------------------------------


	/*if(isnotAlpha(document.frmMembRegi.mname.value))
	{ alert("Enter Valid Middle Name ");
	  document.frmMembRegi.mname.focus();
	   return false; }*/
	/*   
	if(Dospace(document.frmMembRegi.mname.value)||((document.frmMembRegi.mname.value.indexOf(' '))==0))
	   { alert("Enter Valid Middle Name ");
	     document.frmMembRegi.mname.focus();
	   return false; }
	   
	   if(isnotName(document.frmMembRegi.mname.value))
	 {alert("Enter a valid Middle Name");
	  document.frmMembRegi.mname.focus();
	 return false;}
	 
	if( document.frmMembRegi.mname.value.length>80 )
	   { alert("Enter within 80 characters for Middle Name" );
	      document.frmMembRegi.mname.focus();
	      return false; }
	*/
	//----------------------------------LAST NAME--------------------------------------
	if(document.frmMembRegi.lname.value=="")
	   {/*alert(" Last Name cannot be empty ");*/
	     document.frmMembRegi.lname.focus();
		  document.getElementById("lname").innerHTML = "Last Name cannot be empty";
	    return false; }
		else{
		document.frmMembRegi.lname.focus();
		 document.getElementById("lname").innerHTML = "";
		}
	if(document.frmMembRegi.lname.value.indexOf(' ')==0)
	{/*alert("Enter Last Name  ");*/
	 document.frmMembRegi.lname.focus();
	  document.getElementById("lname").innerHTML = "Enter Valid Last Name";
	 return false; }
	 else{
	   document.frmMembRegi.lname.focus();
	  document.getElementById("lname").innerHTML = "";	 
	 }

	if(isnotAlpha(document.frmMembRegi.lname.value))
	{ /*alert("Enter Valid Last Name ");*/
	  document.frmMembRegi.lname.focus();
	   document.getElementById("lname").innerHTML = "Enter Valid Last Name";
	   return false; }
	   else{
	  document.frmMembRegi.lname.focus();
	  document.getElementById("lname").innerHTML = "";	 
	 }
	if(Dospace(document.frmMembRegi.lname.value))
	   { /*alert("Enter Valid Last Name ");*/
	     document.frmMembRegi.lname.focus();
		  document.getElementById("lname").innerHTML = "Enter Valid Last Name";
	   return false; }
	   else{
	  document.frmMembRegi.lname.focus();
	  document.getElementById("lname").innerHTML = "";	 
	 }
	if( document.frmMembRegi.lname.value.length>15 )
	   { /*alert("Enter within 80 characters of Last Name" );*/
	      document.frmMembRegi.lname.focus();
		   document.getElementById("lname").innerHTML = "Enter within 15 characters of Last Name";
	      return false; }
		  else{
	  document.frmMembRegi.lname.focus();
	  document.getElementById("lname").innerHTML = "";	 
	 }


	//--------------------------------SUFFIX---------------------------------------


	if(document.frmMembRegi.sname.value.indexOf(' ')==0)
	{alert("Enter Suffix ");
	 document.frmMembRegi.sname.focus();
	 return false; }


	if(isnotAlphaNumeric(document.frmMembRegi.sname.value))
	{ alert("Enter Valid Suffix ");
	  document.frmMembRegi.sname.focus();
	   return false; }
	if(Dospace(document.frmMembRegi.sname.value))
	   { alert("Enter Valid Suffix ");
	     document.frmMembRegi.sname.focus();
	   return false; }
	if( document.frmMembRegi.sname.value.length>20 )
	   { alert("Enter within 20 characters for Suffix" );
	      document.frmMembRegi.sname.focus();
	      return false; }


	//___________________________________________DATE________________________________________________________
		  //___________________________________________DATE________________________________________________________
	/*===============================date validation changes start here=====================================*/

	if(document.frmMembRegi.dat.value=="")
	{/*alert("Please Enter Date Of Birth");*/
	 document.frmMembRegi.dat.focus();
	  document.getElementById("dob").innerHTML = "Please Enter Date Of Birth";
	 return false;}
	 else{
	  document.frmMembRegi.dat.focus();
	  document.getElementById("dob").innerHTML = ""; 
	 }

	//-----------------------------------------------------------------------------------------------------------
	//-----------------------------------------GENDER
	/*===============================date validation changes End here=====================================*/
	//-----------------------------------------GENDER
	chosen="";
	len = document.frmMembRegi.gender.length ;
	for(i=0;i<len;i++)
	{if(document.frmMembRegi.gender[i].checked)
	  { chosen= document.frmMembRegi.gender[i].value; }
	}
	if(chosen=="")
	{/*alert("Select the Gender");*/
	 
	 document.getElementById("gender").innerHTML = "Select the Gender";
	 return false;
	}
	else {
	  document.getElementById("gender").innerHTML = "";
	}
	//----------- login name ---------------------

	//----------- login name ---------------------

	if(document.frmMembRegi.usrname.value=="")
	{// alert("Enter a User Name");
	 document.frmMembRegi.usrname.focus();
	 document.getElementById("usrname").innerHTML = "Enter a User Name";
	 return false;}
	 else{
	  document.frmMembRegi.usrname.focus();
	 document.getElementById("usrname").innerHTML = "";
	 }

	if(document.frmMembRegi.usrname.value.indexOf(" ")==0)
	{ //alert("Enter a Valid User Name");
	 document.frmMembRegi.usrname.focus();
	 document.getElementById("usrname").innerHTML = "Enter a Valid User Name";
	 return false;}
	 else{
	 document.frmMembRegi.usrname.focus();
	 document.getElementById("usrname").innerHTML = ""; 
	 }

	if(isnotAlphaNumeric(document.frmMembRegi.usrname.value))
	 {//alert("Enter a valid User Name");
	  document.frmMembRegi.usrname.focus();
	  document.getElementById("usrname").innerHTML = "Enter a valid User Name";
	 return false;}
	   else{
	  document.frmMembRegi.usrname.focus();
	  document.getElementById("usrname").innerHTML = ""; 
	 }
	if(document.frmMembRegi.usrname.value.length>25)
	 {//alert("Enter a valid User Name");
	  document.frmMembRegi.usrname.focus();
	  document.getElementById("usrname").innerHTML = "Enter a valid User Name";
	 return false;}
	 else{
	  document.frmMembRegi.usrname.focus();
	  document.getElementById("usrname").innerHTML = ""; 
	 }
	 if(document.frmMembRegi.usrname.value.length<4)
	 {/*alert("Enter a valid User Name");*/
	  document.frmMembRegi.usrname.focus();
	  document.getElementById("usrname").innerHTML = "Enter a valid User Name";
	 return false;}
	else{
	  document.frmMembRegi.usrname.focus();
	  document.getElementById("usrname").innerHTML = ""; 
	 } 
	//----------------------------------------------------------------------------------------------------------------
	//--------------------------------------EMAIL-----------------
	  if(document.frmMembRegi.email.value=="")
	 {//alert("Enter your Email ID");
	  document.frmMembRegi.email.focus();
	  document.getElementById("email").innerHTML = "Enter your Email ID";
	 return false;}
	 else{
	  document.frmMembRegi.email.focus();
	  document.getElementById("email").innerHTML = ""; 
	 }
	 
	if(document.frmMembRegi.email.value.indexOf(" ")==0)
	 {//alert("Enter a Valid Email ID");
	  document.frmMembRegi.email.focus();
	  document.getElementById("email").innerHTML = "Enter a Valid Email ID";
	 return false;}
	  else{
	  document.frmMembRegi.email.focus();
	  document.getElementById("email").innerHTML = ""; 
	 }

	 if(!(document.frmMembRegi.email.value== ""))
	 { strmail=document.frmMembRegi.email.value;
	 firstat = strmail.indexOf("@");
	 lastat = strmail.lastIndexOf("@");
	 strmain=strmail.substring(0,firstat);
	 strsub=strmail.substring(firstat,document.frmMembRegi.email.value.length);
	 if(strmail.length>120)
	 {//alert("Email is out of range");
	  document.frmMembRegi.email.focus();
	   document.getElementById("email").innerHTML = "Email is out of range";
	 return false;}
	  else{
	  document.frmMembRegi.email.focus();
	  document.getElementById("email").innerHTML = ""; 
	 }
	 if(strmain.indexOf('  ')!=-1 || firstat==0 || strsub.indexOf(' ')!=-1 )
	 {//alert("Enter valid Email ");
	  document.frmMembRegi.email.focus();
	   document.getElementById("email").innerHTML = "Enter valid Email";
	 return false;}
	  else{
	  document.frmMembRegi.email.focus();
	  document.getElementById("email").innerHTML = ""; 
	 }
	 if(isnotSpecial(strmain) || isnotSpecial(strsub))
	 {//alert("Enter valid Email ");
	  document.frmMembRegi.email.focus();
	   document.getElementById("email").innerHTML = "Enter valid Email";
	 return false;}
	  else{
	  document.frmMembRegi.email.focus();
	  document.getElementById("email").innerHTML = ""; 
	 }
	 k=0;
	 strlen=strsub.length;
	 for(i=0;i<strlen-1;i++)
	 { if(strsub.charAt(i)=='.')
	 {k=k+1;}}
	 if(k>3)
	 {//alert("Enter valid Email ");
	  document.frmMembRegi.email.focus();
	   document.getElementById("email").innerHTML = "Enter valid Email";
	 return false;}
	  else{
	  document.frmMembRegi.email.focus();
	  document.getElementById("email").innerHTML = ""; 
	 }
	 if(firstat==-1 || lastat==-1)
	 {//alert("Enter valid Email" );
	  document.frmMembRegi.email.focus();
	   document.getElementById("email").innerHTML = "Enter valid Email";
	 return false;}
	  else{
	  document.frmMembRegi.email.focus();
	  document.getElementById("email").innerHTML = ""; 
	 }
	 if(Number(strmain))
	 {//alert("Enter valid Email ");
	  document.frmMembRegi.email.focus();
	   document.getElementById("email").innerHTML = "Enter valid Email";
	  return false;}
	   else{
	  document.frmMembRegi.email.focus();
	  document.getElementById("email").innerHTML = ""; 
	 }
	 if(firstat != lastat )
	 {//alert("Enter valid Email ");
	  document.frmMembRegi.email.focus();
	   document.getElementById("email").innerHTML = "Enter valid Email";
	 return false;}
	  else{
	  document.frmMembRegi.email.focus();
	  document.getElementById("email").innerHTML = ""; 
	 }
	 firstdot=strmail.indexOf(".",firstat);
	 lastdot=strmail.lastIndexOf(".");
	 if(firstdot == -1 || lastdot == -1 || lastdot==strmail.length-1)
	 {//alert("Enter valid Email ");
	  document.frmMembRegi.email.focus();
	   document.getElementById("email").innerHTML = "Enter valid Email";
	  return false;}
	   else{
	  document.frmMembRegi.email.focus();
	  document.getElementById("email").innerHTML = ""; 
	 }
	}
	//------------------------------------------------------------------------------------------------
	//----------------------------------------Password
	if(document.frmMembRegi.pwd.value=="")
	{//alert("Enter Password ");
	 document.frmMembRegi.pwd.focus();
	 document.getElementById("pwd").innerHTML = "Enter Password";
	 return false;}
	 else{
	 document.frmMembRegi.pwd.focus();
	 document.getElementById("pwd").innerHTML = "";	
	 }
	if(document.frmMembRegi.pwd.value.length<8)
	{//alert("Enter Valid Password ");
	document.frmMembRegi.pwd.focus();
	document.getElementById("pwd").innerHTML = "Pwd must be above 8 character";
	 return false;}
	  else{
	 document.frmMembRegi.pwd.focus();
	 document.getElementById("pwd").innerHTML = "";	
	 }
	 re = /[0-9]/;
      if(!re.test(document.frmMembRegi.pwd.value)) {
        document.frmMembRegi.pwd.focus();
	document.getElementById("pwd").innerHTML = "pwd must be 1 numbers";
	 return false;
	 }
	  else{
	 document.frmMembRegi.pwd.focus();
	 document.getElementById("pwd").innerHTML = "";	
	 }
	 re = /[a-z]/;
     if(!re.test(document.frmMembRegi.pwd.value)) {
        document.frmMembRegi.pwd.focus();
	document.getElementById("pwd").innerHTML = "pwd must be 1 lower case";
	 return false;
	 }
	  else{
	 document.frmMembRegi.pwd.focus();
	 document.getElementById("pwd").innerHTML = "";	
	 }
      re = /[A-Z]/;
     if(!re.test(document.frmMembRegi.pwd.value)) {
        document.frmMembRegi.pwd.focus();
	document.getElementById("pwd").innerHTML = "pwd must be 1 Uppercase case";
	 return false;
	 }
	  else{
	 document.frmMembRegi.pwd.focus();
	 document.getElementById("pwd").innerHTML = "";	
	 }
	 re=/[\@\#\$\%\^\&\*\(\)\_\+\!]/
	 if(!re.test(document.frmMembRegi.pwd.value)) {
        document.frmMembRegi.pwd.focus();
	document.getElementById("pwd").innerHTML = "pwd must be 1 Special char";
	 return false;
	 }
	  else{
	 document.frmMembRegi.pwd.focus();
	 document.getElementById("pwd").innerHTML = "";	
	 }
	if(document.frmMembRegi.pwd.value.length>20)
	{//alert("Enter Valid Password ");
	document.frmMembRegi.pwd.focus();
	document.getElementById("pwd").innerHTML = "Pwd be below 20 character";
	 return false;}
	 else{
	 document.frmMembRegi.pwd.focus();
	 document.getElementById("pwd").innerHTML = "";	
	 }
	 if(Dospace(document.frmMembRegi.pwd.value))
	     { //alert("Enter Password ");
	       document.frmMembRegi.pwd.focus();
		   document.getElementById("pwd").innerHTML = "Enter Valid Password";
	   return false; }
	    else{
	 document.frmMembRegi.pwd.focus();
	 document.getElementById("pwd").innerHTML = "";	
	 }

	/*   if(isnotAlphaNumeric(document.frmMembRegi.pwd.value))
	   { alert("Enter Valid Password");
	     document.frmMembRegi.pwd.focus();
	   return false; }*/


	//---------------------------------------------------------------------------------------------------
	//------------------------------------RETYPE Password---------------------------------------


	if(document.frmMembRegi.cpwd.value=="")
	{//alert(" Enter Re-Type jghjhgj Password ");
	 document.frmMembRegi.cpwd.focus();
	 document.getElementById("cpwd").innerHTML = "Enter Re-Type Password";	
	 return false;}
	 else{
	 document.frmMembRegi.cpwd.focus();
	 document.getElementById("cpwd").innerHTML = "";
	 }
	var pwd1=document.frmMembRegi.pwd.value;
	var rpwd=document.frmMembRegi.cpwd.value;
	if(pwd1!=rpwd)
	{
		//alert("Password Mismatch");
		document.frmMembRegi.cpwd.focus();
		document.getElementById("cpwd").innerHTML = "Password Mismatch";	
		return false;
	}
	else{
		document.frmMembRegi.cpwd.focus();
		document.getElementById("cpwd").innerHTML = "";
	}

	//--------------------------------------------------------------------------------------------------------


	//--------------------------------------------------------------------------------------------------------

	//______________________________________________Comapny Details_________________________________________________

	if(document.frmMembRegi.company.value=="")
	{//alert(" Select your Company Name ");
	 document.frmMembRegi.company.focus();
	 document.getElementById("ncompany").innerHTML = "select Company";
	 return false;}

	else{
		document.frmMembRegi.cpwd.focus();
		document.getElementById("ncompany").innerHTML = "";
	}

	//--------------------------------------------------------------------------------------------------------

	//______________________________________________Category Details_________________________________________________

	if(document.frmMembRegi.category.value=="")
	{//alert(" Select your category Type ");
	 document.frmMembRegi.category.focus();
	 document.getElementById("category").innerHTML = "select Category";
	 return false;}
	else{
		document.frmMembRegi.cpwd.focus();
		document.getElementById("category").innerHTML = "";
	}



	//--------------------------------------------------------------------------------------------------------

	//______________________________________________Question_________________________________________________
	if(document.frmMembRegi.QSelect.value=="")
	{//alert(" Select your Secret Question ");
	 document.frmMembRegi.QSelect.focus();
	 document.getElementById("QSelect").innerHTML = "Select your Secret Question";	
	 return false;}
	else{
	 document.frmMembRegi.QSelect.focus();
	 document.getElementById("QSelect").innerHTML = "";	
	}

	if(!(document.frmMembRegi.QSelect.value==""))
	{ leno=document.frmMembRegi.QSelect.length;
	 for(i=0;i<leno;i++)
	 { if(document.frmMembRegi.QSelect[i].selected)
	 { choseno=document.frmMembRegi.QSelect[i].value ;}}
	}
	//_________________________________________________Answer_____________________________________________________

	if(document.frmMembRegi.ans.value=="")
	{//alert(" Enter your Answer to the secret Question ");
	 document.frmMembRegi.ans.focus();
	 document.getElementById("ans").innerHTML = "Enter your Answer";	
	 return false;}
	 else{
	 document.frmMembRegi.ans.focus();
	 document.getElementById("ans").innerHTML = "";	
	 }
	if(document.frmMembRegi.ans.value.length>255)
	 {//alert("Enter Answer with in 255 characters ");
	 document.frmMembRegi.ans.focus();
	 document.getElementById("ans").innerHTML = "Enter Answer with in 255 characters";	
	return false;}
	else{
	 document.frmMembRegi.ans.focus();
	 document.getElementById("ans").innerHTML = "";	
	}
	if(Dospace(document.frmMembRegi.ans.value))
	  { //alert("Enter Valid Answer ");
	 	document.frmMembRegi.ans.focus();
		document.getElementById("ans").innerHTML = "Enter Valid Answer";	
		   return false; }
	else{
		document.frmMembRegi.ans.focus();
		document.getElementById("ans").innerHTML = "";
	}
	if(document.frmMembRegi.ans.value.indexOf(' ')==0){
			//alert("Enter Valid Answer ");
	    document.frmMembRegi.ans.focus();
		document.getElementById("ans").innerHTML = "Enter Valid Answer";	
	    return false; }
		else{
		document.frmMembRegi.ans.focus();
		document.getElementById("ans").innerHTML = "";
		}
		//---------------------------------------------------------Upload pic------------------//
		
if(document.frmMembRegi.uploadpic.value=="")
{//alert(" Enter your Answer to the secret Question ");
 document.frmMembRegi.uploadpic.focus();
document.getElementById("uploadpic").innerHTML = "Upload the Image ";	
	 return false;}
	 else{
	 document.frmMembRegi.uploadpic.focus();
	 document.getElementById("uploadpic").innerHTML = "";	
	 }

		//--------------------------------------------------Prmary Address -------------------------------------------//

	//====================Plot No, Door No, Building No==========================================//

//***********************************************Debuged by Nihal ******************************************************//
if(document.frmMembRegi.plotNo.value=="")
	{
		
	  //alert(" Enter your Answer to the secret Question ");
	 document.frmMembRegi.plotNo.focus();
	 document.getElementById("plotno").innerHTML = "Enter Plot No.";	
	 return false;}
	 else{
	 //document.frmMembRegi.nplotno.focus();
	 document.getElementById("plotno").innerHTML = "";	
	 }
	 
if(document.frmMembRegi.buildNo.value=="")
	{
		
	  //alert(" Enter your Answer to the secret Question ");
	 document.frmMembRegi.buildNo.focus();
	 document.getElementById("buildno").innerHTML = "Enter Building Name/No.";	
	 return false;}
	 else{
	 //document.frmMembRegi.padd_txt.focus();
	 document.getElementById("buildno").innerHTML = "";	
	 }
if(document.frmMembRegi.floorNo.value=="")
	{
		
	  //alert(" Enter your Answer to the secret Question ");
	 document.frmMembRegi.floorNo.focus();
	 document.getElementById("floorno").innerHTML = "Enter Floor Name";	
	 return false;}
	 else{
	// document.frmMembRegi.padd_txt.focus();
	 document.getElementById("floorno").innerHTML = "";	
	 }
	 
	 
	 
	 //*****************************************END *********************************************************************//
	
	if(document.frmMembRegi.street.value=="")
	{
		
	  //alert(" Enter your Answer to the secret Question ");
	 document.frmMembRegi.street.focus();
	 document.getElementById("Street").innerHTML = "Enter Street Address ";	
	 return false;}
	 else{
	// document.frmMembRegi.padd_txt.focus();
	 document.getElementById("Street").innerHTML = "";	
	 }
	
	
	
	
	
	
	
/*	if(document.frmMembRegi.padd_txt2.value.length>20)
	 {//alert("Enter Answer with in 255 characters ");
	 document.frmMembRegi.padd_txt2.focus();
	 document.getElementById("nstreet").innerHTML = "Enter Street Address with in 20 characters";	
	return false;}
	else{
	 document.frmMembRegi.padd_txt2.focus();
	 document.getElementById("nstreet").innerHTML = "";	
	}
	if(Dospace(document.frmMembRegi.padd_txt2.value))
	  { //alert("Enter Valid Answer ");
	 	document.frmMembRegi.padd_txt2.focus();
		document.getElementById("nstreet").innerHTML = "Enter Valid Address";	
		   return false; }
	else{
		document.frmMembRegi.padd_txt2.focus();
		document.getElementById("nstreet").innerHTML = "";
	}
	if(document.frmMembRegi.padd_txt2.value.indexOf(' ')==0){
			//alert("Enter Valid Answer ");
	    document.frmMembRegi.padd_txt2.focus();
		document.getElementById("nstreet").innerHTML = "Enter Valid Address";	
	    return false; }
		else{
		document.frmMembRegi.gstreet.focus();
		document.getElementById("nstreet").innerHTML = "";
		}
		*/
	//=========================================Street Address============================================//
/*	if(document.frmMembRegi.padd_txt2.value.length>15)
	 {//alert("Enter Answer with in 255 characters ");
	 document.frmMembRegi.padd_txt2.focus();
	 document.getElementById("nstreet").innerHTML = "Street Name with in 15 characters";	
	return false;}
	else{
	 document.frmMembRegi.padd_txt2.focus();
	 document.getElementById("nstreet").innerHTML = "";	
	}
	
	*/
	//===========================s==============Lane========================================================//


	if(document.frmMembRegi.lane.value=="")
	{
		
	  //alert(" Enter your Answer to the secret Question ");
	 document.frmMembRegi.lane.focus();
	 document.getElementById("Lane").innerHTML = "Enter Lane ";	
	 return false;}
	 else{
	// document.frmMembRegi.padd_txt.focus();
	 document.getElementById("Lane").innerHTML = "";	
	 }



/*if(document.frmMembRegi.padd_txt3.value.length>15)
	 {//alert("Enter Answer with in 255 characters ");
	 document.frmMembRegi.padd_txt3.focus();
	 document.getElementById("pAdd3_txt").innerHTML = "Lane Name with in 15 characters";	
	return false;}
	else{
	 document.frmMembRegi.padd_txt3.focus();
	 document.getElementById("pAdd3_txt").innerHTML = "";	
	}*/
	//======================================Area        Debuged By Nihal ============================================================//
	/*if(document.frmMembRegi.padd_txt4.value.length>15)
	 {//alert("Enter Answer with in 255 characters ");
	 document.frmMembRegi.padd_txt4.focus();
	 document.getElementById("pAdd4_txt").innerHTML = "Area Name with in 15 characters";	
	return false;}
	else{
	 document.frmMembRegi.padd_txt4.focus();
	 document.getElementById("pAdd4_txt").innerHTML = "";	
	}
	*/
	

	if(document.frmMembRegi.area.value=="")
	{
		
	  //alert(" Enter your Answer to the secret Question ");
	 document.frmMembRegi.area.focus();
	 document.getElementById("Area").innerHTML = "Enter Area Name ";	
	 return false;}
	 else{
	// document.frmMembRegi.padd_txt.focus();
	 document.getElementById("Area").innerHTML = "";	
	 }
	
	//===================================City=================================================================//

	if(document.frmMembRegi.pcity_txt.value=="")
	{
	 document.frmMembRegi.pcity_txt.focus();
	 document.getElementById("pcity_txt").innerHTML = "City Name ";	
	 return false;}
	 else{
	 document.frmMembRegi.pcity_txt.focus();
	 document.getElementById("pcity_txt").innerHTML = "";	
	 }
	if(document.frmMembRegi.pcity_txt.value.length>20)
	 {//alert("Enter Answer with in 255 characters ");
	 document.frmMembRegi.pcity_txt.focus();
	 document.getElementById("pcity_txt").innerHTML = "City Name with in 20 characters";	
	return false;}
	else{
	 document.frmMembRegi.pcity_txt.focus();
	 document.getElementById("pcity_txt").innerHTML = "";	
	}
	if(Dospace(document.frmMembRegi.pcity_txt.value))
	  { //alert("Enter Valid Answer ");
	 	document.frmMembRegi.pcity_txt.focus();
		document.getElementById("pcity_txt").innerHTML = "Enter Valid City Name";	
		   return false; }
	else{
		document.frmMembRegi.pcity_txt.focus();
		document.getElementById("pcity_txt").innerHTML = "";
	}
	if(document.frmMembRegi.pcity_txt.value.indexOf(' ')==0){
			//alert("Enter Valid Answer ");
	    document.frmMembRegi.pcity_txt.focus();
		document.getElementById("pcity_txt").innerHTML = "Enter Valid City Name";	
	    return false; }
		else{
		document.frmMembRegi.pcity_txt.focus();
		document.getElementById("pcity_txt").innerHTML = "";
		}
		
	//_________________________________________________Country_____________________________________________________

	var cdln = "";
	 	if(typeof(window.document.frmMembRegi.pcountry_sel) == 'object'){
	 		if (window.document.frmMembRegi.pcountry_sel.value != ""){
	 			var cemail;
	 			cemail = window.document.frmMembRegi.pcountry_sel[window.document.frmMembRegi.pcountry_sel.selectedIndex].value;
	 			cdln = cemail;
	 		}
	 		if(cdln == "Select One"){
	 			//alert("Please select Country in Primary Address");
	 			window.document.frmMembRegi.pcountry_sel.focus();
				window.document.getElementById("pCountry_sel").innerHTML="Please select Country ";
	 			return false;
	 		}
			else{
			 window.document.frmMembRegi.pcountry_sel.focus();
			 window.document.getElementById("pCountry_sel").innerHTML="";
			}
	 	}

	 	if ( document.frmMembRegi.pcountry_sel.selectedIndex == 0 ){
	         //alert ( "Please select Country Name in Primary Address" );
	 		document.frmMembRegi.pcountry_sel.focus();
			document.getElementById("pCountry_sel").innerHTML="Please select Country Name";
	         return false;
	     }
		 else{
			 document.frmMembRegi.pcountry_sel.focus();
			 document.getElementById("pCountry_sel").innerHTML="";
		 }
		
		 //__________________________________________________ State ______________________________________________________
	var cdln = "";
	 	if(typeof(window.document.frmMembRegi.pstate_sel) == 'object'){
	 		if (window.document.frmMembRegi.pstate_sel.value != ""){
	 			var cemail;
	 			cemail = window.document.frmMembRegi.pstate_sel[window.document.frmMembRegi.pstate_sel.selectedIndex].value;
	 			cdln = cemail;
	 		}
	 		if(cdln == "Select One"){
	 			//alert("Please select Country in Primary Address");
	 			window.document.frmMembRegi.pstate_sel.focus();
				window.document.getElementById("pstate_sel").innerHTML="Please select State";
	 			return false;
	 		}
			else{
			 window.document.frmMembRegi.pstate_sel.focus();
			 window.document.getElementById("pstate_sel").innerHTML="";
			}
	 	}

	 	if ( document.frmMembRegi.pstate_sel.selectedIndex == 0 ){
	         //alert ( "Please select Country Name in Primary Address" );
	 		document.frmMembRegi.pstate_sel.focus();
			document.getElementById("pstate_sel").innerHTML="Please select State Name";
	         return false;
	     }
		 else{
			 document.frmMembRegi.pstate_sel.focus();
			 document.getElementById("pstate_sel").innerHTML="";
		 }
	//___________________________________________________ZipCode_________________________________________________________
	//___________________________________________________ZipCode_________________________________________________________
	if(document.frmMembRegi.pzip_txt.value==""){
			//alert("Enter Zipcode in Primary Address");
			document.frmMembRegi.pzip_txt.focus();
			document.getElementById("pzip_txt").innerHTML="Enter Zipcode in Primary Addres";
			return false;
		    }
			else{
		    document.frmMembRegi.pzip_txt.focus();
			document.getElementById("pzip_txt").innerHTML="";
			}
	     
	    if((document.frmMembRegi.pzip_txt.value.length <3&& document.frmMembRegi.pcountry_sel.value!="USA")){
			//alert("Enter Valid Zipcode in Primary Address");
	        document.frmMembRegi.pzip_txt.focus();
			document.getElementById("pzip_txt").innerHTML="Enter Valid Zipcode in Primary Address";
	        return false;
			}
	        else{
		    document.frmMembRegi.pzip_txt.focus();
			document.getElementById("pzip_txt").innerHTML="";
			}
	     
	    if((document.frmMembRegi.pzip_txt.value.length >6 && document.frmMembRegi.pcountry_sel.value!="USA")){
			//alert("Enter Valid Zipcode in Primary Address");
	        document.frmMembRegi.pzip_txt.focus();
			document.getElementById("pzip_txt").innerHTML="Enter Valid Zipcode in Primary Address";
	        return false;
			}
			else{
			document.frmMembRegi.pzip_txt.focus();
			document.getElementById("pzip_txt").innerHTML="";
			}
		if((document.frmMembRegi.pzip_txt.value.length !=5&& document.frmMembRegi.pcountry_sel.value=="USA")){
			//alert("Enter Valid Zipcode in Primary Address");
	        document.frmMembRegi.pzip_txt.focus();
			document.getElementById("pzip_txt").innerHTML="";
	        return false;
			}
	         
		if(document.frmMembRegi.pzip_txt.value.indexOf(" ")==0){
			//alert("Enter Valid Zipcode in Primary Address");
			document.frmMembRegi.pzip_txt.focus();
			return false; 
		}
		
	//=================================Primary Phone Number +==============================================//
/*
	if(document.frmMembRegi.iphone_txt.value==""){
			//alert("Enter Zipcode in Primary Address");
			document.frmMembRegi.iphone_txt.focus();
			document.getElementById("Iphone_txt").innerHTML="Enter Country Code";
			return false;
		    }
			else{
		    document.frmMembRegi.iphone_txt.focus();
			document.getElementById("Iphone_txt").innerHTML="";
			}
	*/		
				if(document.frmMembRegi.aphone_txt.value==""){
			//alert("Enter Zipcode in Primary Address");
			document.frmMembRegi.aphone_txt.focus();
			document.getElementById("Aphone_txt").innerHTML="Enter Area Code";
			return false;
		    }
			else{
		    document.frmMembRegi.aphone_txt.focus();
			document.getElementById("Aphone_txt").innerHTML="";
			}


	if(document.frmMembRegi.pphone_txt.value!=""){		  
		len7=document.frmMembRegi.pphone_txt.value.length;
		strnum = document.frmMembRegi.pphone_txt.value;
		var GoodChars = "0123456789()- ";
		valid = 1;
		for(j=0;j<len7;j++){
			if(GoodChars.indexOf(strnum.charAt(j))==-1){
				valid=0;
			}
		}
		if(valid!=1){
//			alert("Enter valid Secondary Phone Number");
			document.frmMembRegi.pphone_txt.focus();
					document.getElementById("phone_txt").innerHTML="Enter phone number in Primary Address";
			return false;
		}
		if(document.frmMembRegi.pphone_txt.value.length<10){
		//	alert("Enter valid Secondary Phone Number");
			document.frmMembRegi.pphone_txt.focus();
					document.getElementById("phone_txt").innerHTML="Enter phone number in Primary Address";
			return false;
		}
		
			else{
		    document.frmMembRegi.pphone_txt.focus();
			document.getElementById("phone_txt").innerHTML="";
			}
		if(document.frmMembRegi.pphone_txt.value.length>12){
			//	alert("Enter valid Secondary Phone Number");
				document.frmMembRegi.pphone_txt.focus();
						document.getElementById("phone_txt").innerHTML="Enter phone number in Primary Address";
				return false;
			}
			
				else{
			    document.frmMembRegi.pphone_txt.focus();
				document.getElementById("phone_txt").innerHTML="";
				}

	}


	//-------------------------------------------------------------------------------------------------------
	//----------------------IF ENTER EITHER Primary Phone Number OR Primary mobile Number
	if(document.frmMembRegi.pphone_txt.value=="")
	{ //alert("Enter Secondary Phone Number");
	  document.frmMembRegi.pphone_txt.focus();
	  document.getElementById("phone_txt").innerHTML="Enter Numeric only";
	  return false;}
	else{
		document.frmMembRegi.pphone_txt.focus();
		document.getElementById("phone_txt").innerHTML="";
		}
	if(Dospace(document.frmMembRegi.pphone_txt.value)){
				//alert("Enter Valid Phone Number in Secondary Address");
		     	document.frmMembRegi.pphone_txt.focus();
		     	document.getElementById("phone_txt").innerHTML="Enter Numeric only";
		  		return false;
				}
	else{
		document.frmMembRegi.pphone_txt.focus();
		document.getElementById("phone_txt").innerHTML="";
		}
				if(document.frmMembRegi.pphone_txt.value.indexOf(" ")==0){
			//alert("Enter Valid Phone Number in Secondary Address");
			document.frmMembRegi.pphone_txt.focus();
			document.getElementById("phone_txt").innerHTML="Enter Numeric only";
			return false; 
		}
				else{
					document.frmMembRegi.pphone_txt.focus();
					document.getElementById("phone_txt").innerHTML="";
					}
	//--------------------------------Secondary Phone Number---------------------------------------

/*
	if(document.frmMembRegi.pphone_txt.value!=""){		  
		len7=document.frmMembRegi.pphone_txt.value.length;
		strnum = document.frmMembRegi.pphone_txt.value;
		var GoodChars = "0123456789()- ";
		valid = 1;
		for(j=0;j<len7;j++){
			if(GoodChars.indexOf(strnum.charAt(j))==-1){
				valid=0;
			}
		}
		if(valid!=1){
			//alert("Enter valid Secondary Phone Number");
			document.frmMembRegi.pphone_txt.focus();
			document.getElementById("pphone_txt").innerHTML="Enter Numeric only";
			return false;
		}else{
			document.frmMembRegi.pphone_txt.focus();
			document.getElementById("pphone_txt").innerHTML="";
			}
		if(document.frmMembRegi.pphone_txt.value.length>40){
			//alert("Enter valid Secondary Phone Number");
			document.frmMembRegi.pphone_txt.focus();
			document.getElementById("pphone_txt").innerHTML="Enter Numeric only";
			return false;
		}
		else{
			document.frmMembRegi.pphone_txt.focus();
			document.getElementById(pphone_txt).innerHTML="";
			}
	}

  */
	/*
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

*/		

	//----------------------------------Primary MOBILE---------------------------------------




	if(document.frmMembRegi.pimob_txt.value==""){
			//alert("Enter Zipcode in Primary Address");
			document.frmMembRegi.pimob_txt.focus();
			document.getElementById("imob_txt").innerHTML="Enter Country Code";
			return false;
		    }
			else{
		    document.frmMembRegi.pimob_txt.focus();
			document.getElementById("imob_txt").innerHTML="";
			}
			
		
		if(document.frmMembRegi.pmob_txt.value!=""){		  
		len7=document.frmMembRegi.pmob_txt.value.length;
		strnum = document.frmMembRegi.pmob_txt.value;
		var GoodChars = "0123456789()- ";
		valid = 1;
		for(j=0;j<len7;j++){
			if(GoodChars.indexOf(strnum.charAt(j))==-1){
				valid=0;
			}
		}
		if(valid!=1){
//			alert("Enter valid Secondary Phone Number");
			document.frmMembRegi.pmob_txt.focus();
					document.getElementById("mob_txt").innerHTML="Enter phone number in Primary Address";
			return false;
		}
		if(document.frmMembRegi.pmob_txt.value.length<10){
		//	alert("Enter valid Secondary Phone Number");
			document.frmMembRegi.pmob_txt.focus();
					document.getElementById("mob_txt").innerHTML="Enter phone number in Primary Address";
			return false;
		}
		
			else{
		    document.frmMembRegi.pmob_txt.focus();
			document.getElementById("mob_txt").innerHTML="";
			}
		if(document.frmMembRegi.pmob_txt.value.length>12){
			//	alert("Enter valid Secondary Phone Number");
				document.frmMembRegi.pmob_txt.focus();
						document.getElementById("mob_txt").innerHTML="Enter phone number in Primary Address";
				return false;
			}
			
				else{
			    document.frmMembRegi.pmob_txt.focus();
				document.getElementById("mob_txt").innerHTML="";
				}

	}


	//-------------------------------------------------------------------------------------------------------
	//----------------------IF ENTER EITHER Primary Phone Number OR Primary mobile Number
	if(document.frmMembRegi.pmob_txt.value=="")
	{ //alert("Enter Secondary Phone Number");
	  document.frmMembRegi.pmob_txt.focus();
	  document.getElementById("mob_txt").innerHTML="Enter Numeric only";
	  return false;}
	else{
		document.frmMembRegi.pmob_txt.focus();
		document.getElementById("mob_txt").innerHTML="";
		}
	if(Dospace(document.frmMembRegi.pmob_txt.value)){
				//alert("Enter Valid Phone Number in Secondary Address");
		     	document.frmMembRegi.pmob_txt.focus();
		     	document.getElementById("mob_txt").innerHTML="Enter Numeric only";
		  		return false;
				}
	else{
		document.frmMembRegi.pmob_txt.focus();
		document.getElementById("mob_txt").innerHTML="";
		}
				if(document.frmMembRegi.pmob_txt.value.indexOf(" ")==0){
			//alert("Enter Valid Phone Number in Secondary Address");
			document.frmMembRegi.pmob_txt.focus();
			document.getElementById("mob_txt").innerHTML="Enter Numeric only";
			return false; 
		}
				else{
					document.frmMembRegi.pmob_txt.focus();
					document.getElementById("mob_txt").innerHTML="";
					}
//================================================================//
/*	if(document.frmMembRegi.pmob_txt.value!="")
	{		
	 len7=document.frmMembRegi.pmob_txt.value.length;
	 strnum = document.frmMembRegi.pmob_txt.value;
	  var GoodChars = "0123456789()-+ ";
	 valid = 1;
	 for(j=0;j<len7;j++)
	{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
	   { valid=0;} }
	if(valid!=1)
	{//alert("Enter valid Primary mobile Number");
	 document.frmMembRegi.pmob_txt.focus();
	 document.getElementById("mob_txt").innerHTML="Enter mobile number in Primary Address";
	 return false;}
	 
	 if(isnotAlpha1(document.frmMembRegi.pmob_txt.value))
	{// alert("Enter Valid Primary mobile Number ");
	  document.frmMembRegi.pmob_txt.focus();
	  document.getElementById("mob_txt").innerHTML="Enter mobile number in Primary Address";
	   return false; }
		if(document.frmMembRegi.pmob_txt.value.length<9){
			//	alert("Enter valid Secondary Phone Number");
				document.frmMembRegi.pmob_txt.focus();
						document.getElementById("mob_txt").innerHTML="Enter mobile number in Primary Address";
				return false;
			}
		if(document.frmMembRegi.pmob_txt.value.length>11){
			//	alert("Enter valid Secondary Phone Number");
				document.frmMembRegi.pmob_txt.focus();
						document.getElementById("mob_txt").innerHTML="Enter mobile number in Primary Address";
				return false;
			}
			
				


	}  */
	 //-----------------------------------------Primary fax--------------------------------

if(document.frmMembRegi.pifax_txt.value==""){
			//alert("Enter Zipcode in Primary Address");
			document.frmMembRegi.pifax_txt.focus();
			document.getElementById("ifax_txt").innerHTML="Enter Country Code";
			return false;
		    }
			else{
		    document.frmMembRegi.pifax_txt.focus();
			document.getElementById("ifax_txt").innerHTML="";
			}
			
				if(document.frmMembRegi.pafax_txt.value==""){
			//alert("Enter Zipcode in Primary Address");
			document.frmMembRegi.pafax_txt.focus();
			document.getElementById("afax_txt").innerHTML="Enter Area Code";
			return false;
		    }
			else{
		    document.frmMembRegi.pafax_txt.focus();
			document.getElementById("afax_txt").innerHTML="";
			}

   
		if(document.frmMembRegi.pfax_txt.value!=""){		  
		len7=document.frmMembRegi.pfax_txt.value.length;
		strnum = document.frmMembRegi.pfax_txt.value;
		var GoodChars = "0123456789()- ";
		valid = 1;
		for(j=0;j<len7;j++){
			if(GoodChars.indexOf(strnum.charAt(j))==-1){
				valid=0;
			}
		}
		if(valid!=1){
//			alert("Enter valid Secondary Phone Number");
			document.frmMembRegi.pfax_txt.focus();
					document.getElementById("fax_txt").innerHTML="Enter phone number in Primary Address";
			return false;
		}
		if(document.frmMembRegi.pfax_txt.value.length<10){
		//	alert("Enter valid Secondary Phone Number");
			document.frmMembRegi.pfax_txt.focus();
					document.getElementById("fax_txt").innerHTML="Enter phone number in Primary Address";
			return false;
		}
		
			else{
		    document.frmMembRegi.pfax_txt.focus();
			document.getElementById("fax_txt").innerHTML="";
			}
		if(document.frmMembRegi.pfax_txt.value.length>12){
			//	alert("Enter valid Secondary Phone Number");
				document.frmMembRegi.pfax_txt.focus();
						document.getElementById("fax_txt").innerHTML="Enter phone number in Primary Address";
				return false;
			}
			
				else{
			    document.frmMembRegi.pfax_txt.focus();
				document.getElementById("fax_txt").innerHTML="";
				}

	}


	//-------------------------------------------------------------------------------------------------------
	//----------------------IF ENTER EITHER Primary Phone Number OR Primary mobile Number
	if(document.frmMembRegi.pfax_txt.value=="")
	{ //alert("Enter Secondary Phone Number");
	  document.frmMembRegi.pfax_txt.focus();
	  document.getElementById("fax_txt").innerHTML="Enter Numeric only";
	  return false;}
	else{
		document.frmMembRegi.pfax_txt.focus();
		document.getElementById("fax_txt").innerHTML="";
		}
	if(Dospace(document.frmMembRegi.pfax_txt.value)){
				//alert("Enter Valid Phone Number in Secondary Address");
		     	document.frmMembRegi.pfax_txt.focus();
		     	document.getElementById("fax_txt").innerHTML="Enter Numeric only";
		  		return false;
				}
	else{
		document.frmMembRegi.pfax_txt.focus();
		document.getElementById("fax_txt").innerHTML="";
		}
				if(document.frmMembRegi.pfax_txt.value.indexOf(" ")==0){
			//alert("Enter Valid Phone Number in Secondary Address");
			document.frmMembRegi.pfax_txt.focus();
			document.getElementById("fax_txt").innerHTML="Enter Numeric only";
			return false; 
		}
				else{
					document.frmMembRegi.pfax_txt.focus();
					document.getElementById("fax_txt").innerHTML="";
					}

/*
	if(document.frmMembRegi.pfax_txt.value!="")
	{		
	 len7=document.frmMembRegi.pfax_txt.value.length;
	 strnum = document.frmMembRegi.pfax_txt.value;
	  var GoodChars = "0123456789()-+ ";
	 valid = 1;
	 for(j=0;j<len7;j++)
	{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
	   { valid=0;} }
	if(valid!=1)
	{//alert("Enter valid Primary mobile Number");
	 document.frmMembRegi.pfax_txt.focus();
	 document.getElementById("fax_txt").innerHTML="Enter fax number";
	 return false;}
	 
	 if(isnotAlpha1(document.frmMembRegi.pfax_txt.value))
	{// alert("Enter Valid Primary mobile Number ");
	  document.frmMembRegi.pfax_txt.focus();
	  document.getElementById("fax_txt").innerHTML="Enter fax number";
	   return false; }
		if(document.frmMembRegi.pfax_txt.value.length<12){
			//	alert("Enter valid Secondary Phone Number");
				document.frmMembRegi.pfax_txt.focus();
						document.getElementById("fax_txt").innerHTML="Enter fax number";
				return false;
			}
		if(document.frmMembRegi.pfax_txt.value.length>14){
			//	alert("Enter valid Secondary Phone Number");
				document.frmMembRegi.pfax_txt.focus();
						document.getElementById("fax_txt").innerHTML="Enter fax number";
				return false;
			}
	}
*/
	
	//------------------------------------------------------checking in sec.add------------------------------------------
	/*if(document.frmMembRegi.addr.value=="Primary"){
		if(Number(document.frmMembRegi.scountry_txt.value))
		{ alert("Enter Valid Country  in Secondary Address ");
		  document.frmMembRegi.scountry_txt.focus();
		   return false; }
		   	   if(Number(document.frmMembRegi.sstate_txt.value))
		{ alert("Enter Valid State in Secondary Address ");
		  document.frmMembRegi.sstate_txt.focus();
		   return false; }
		   if(Number(document.frmMembRegi.scity_txt.value))
		{ alert("Enter Valid City in Secondary Address ");
		  document.frmMembRegi.scity_txt.focus();
		   return false; }
		   if(!Number(document.frmMembRegi.szip_txt.value))
	{alert("Enter valid Zipcode in Secondary Address");
	 document.frmMembRegi.szip_txt.focus();
	 return false;}
		   
		   }*/
		
	//------------------------------------------------------SECONDARY-----------------------------------------------------
	//____________________________________________________Address1_____________________________________________________

	var chosen2="";
	var len2 = document.frmMembRegi.addr.length ;
	for(i=0;i<len2;i++)
	{if(document.frmMembRegi.addr[i].checked)
	  { chosen2= document.frmMembRegi.addr[i].value; }
	}

	if(chosen2=="Primary"){
		
if(document.frmMembRegi.sadd_txt.value=="")
	{
		
	  //alert(" Enter your Answer to the secret Question ");
	 document.frmMembRegi.sadd_txt.focus();
	 document.getElementById("sadd_txt").innerHTML = "Enter Plot No.";	
	 return false;}
	 else{
	 //document.frmMembRegi.nplotno.focus();
	 document.getElementById("sadd_txt").innerHTML = "";	
	 }
	 //=========================================Street Address============================================//
	if(document.frmMembRegi.sadd_txt1.value.length>15)
	 {//alert("Enter Answer with in 255 characters ");
	 document.frmMembRegi.sadd_txt1.focus();
	 document.getElementById("sAdd2_txt").innerHTML = "Street Name with in 15 characters";	
	return false;}
	else{
	 document.frmMembRegi.sadd_txt1.focus();
	 document.getElementById("sAdd2_txt").innerHTML = "";	
	}

	//=========================================Lane========================================================//

	if(document.frmMembRegi.sadd_txt2.value.length>15)
	 {//alert("Enter Answer with in 255 characters ");
	 document.frmMembRegi.sadd_txt2.focus();
	 document.getElementById("sAdd3_txt").innerHTML = "Lane Name with in 15 characters";	
	return false;}
	else{
	 document.frmMembRegi.sadd_txt2.focus();
	 document.getElementById("sAdd3_txt").innerHTML = "";	
	}
	//======================================Area============================================================//
	if(document.frmMembRegi.sadd_txt3.value.length>15)
	 {//alert("Enter Answer with in 255 characters ");
	 document.frmMembRegi.sadd_txt3.focus();
	 document.getElementById("sAdd4_txt").innerHTML = "Area Name with in 15 characters";	
	return false;}
	else{
	 document.frmMembRegi.sadd_txt3.focus();
	 document.getElementById("sAdd4_txt").innerHTML = "";	
	}
	
	//__________________________________________city___________________________________________________________________

	//===================================City=================================================================//

	if(document.frmMembRegi.scity_txt.value=="")
	{
	 document.frmMembRegi.scity_txt.focus();
	 document.getElementById("Scity_txt").innerHTML = "City Name ";	
	 return false;}
	 else{
	 document.frmMembRegi.scity_txt.focus();
	 document.getElementById("Scity_txt").innerHTML = "";	
	 }
	if(document.frmMembRegi.scity_txt.value.length>20)
	 {//alert("Enter Answer with in 255 characters ");
	 document.frmMembRegi.scity_txt.focus();
	 document.getElementById("Scity_txt").innerHTML = "City Name with in 20 characters";	
	return false;}
	else{
	 document.frmMembRegi.scity_txt.focus();
	 document.getElementById("Scity_txt").innerHTML = "";	
	}
	if(Dospace(document.frmMembRegi.scity_txt.value))
	  { //alert("Enter Valid Answer ");
	 	document.frmMembRegi.scity_txt.focus();
		document.getElementById("Scity_txt").innerHTML = "Enter Valid City Name";	
		   return false; }
	else{
		document.frmMembRegi.scity_txt.focus();
		document.getElementById("Scity_txt").innerHTML = "";
	}
	if(document.frmMembRegi.scity_txt.value.indexOf(' ')==0){
			//alert("Enter Valid Answer ");
	    document.frmMembRegi.scity_txt.focus();
		document.getElementById("Scity_txt").innerHTML = "Enter Valid City Name";	
	    return false; }
		else{
		document.frmMembRegi.scity_txt.focus();
		document.getElementById("Scity_txt").innerHTML = "";
		}
	//_________________________________________________Country_____________________________________________________

	var cdln = "";
	 	if(typeof(window.document.frmMembRegi.scountry_txt) == 'object'){
	 		if (window.document.frmMembRegi.scountry_txt.value != ""){
	 			var cemail;
	 			cemail = window.document.frmMembRegi.scountry_txt[window.document.frmMembRegi.scountry_txt.selectedIndex].value;
	 			cdln = cemail;
	 		}
	 		if(cdln == "Select One"){
	 			//alert("Please select Country in Primary Address");
	 			window.document.frmMembRegi.scountry_txt.focus();
				window.document.getElementById("sCountry_sel").innerHTML="Select Country";
	 			return false;
	 		}
			else{
			 window.document.frmMembRegi.scountry_txt.focus();
			 window.document.getElementById("sCountry_sel").innerHTML="";
			}
	 	}

	 	if ( document.frmMembRegi.scountry_txt.selectedIndex == 0 ){
	         //alert ( "Please select Country Name in Primary Address" );
	 		document.frmMembRegi.scountry_txt.focus();
			document.getElementById("sCountry_sel").innerHTML="Select Country";
	         return false;
	     }
		 else{
			 document.frmMembRegi.scountry_txt.focus();
			 document.getElementById("sCountry_sel").innerHTML="";
		 }
	 //__________________________________________________ State ______________________________________________________
	var cdln = "";
	 	if(typeof(window.document.frmMembRegi.sstate_txt) == 'object'){
	 		if (window.document.frmMembRegi.sstate_txt.value != ""){
	 			var cemail;
	 			cemail = window.document.frmMembRegi.sstate_txt[window.document.frmMembRegi.sstate_txt.selectedIndex].value;
	 			cdln = cemail;
	 		}
	 		if(cdln == "Select One"){
	 			//alert("Please select Country in Primary Address");
	 			window.document.frmMembRegi.sstate_txt.focus();
				window.document.getElementById("sstate_sel").innerHTML="Select State";
	 			return false;
	 		}
			else{
			 window.document.frmMembRegi.sstate_txt.focus();
			 window.document.getElementById("sstate_sel").innerHTML="";
			}
	 	}

	 	if ( document.frmMembRegi.sstate_txt.selectedIndex == 0 ){
	         //alert ( "Please select Country Name in Primary Address" );
	 		document.frmMembRegi.sstate_txt.focus();
			document.getElementById("sstate_sel").innerHTML="Select State";
	         return false;
	     }
		 else{
			 document.frmMembRegi.sstate_txt.focus();
			 document.getElementById("sstate_sel").innerHTML="";
		 }
	//___________________________________________________ZipCode_________________________________________________________
	//___________________________________________________ZipCode_________________________________________________________
	 
	 	if(isNaN(document.frmMembRegi.szip_txt.value=="")){
			//alert("Enter Zipcode in Primary Address");
			document.frmMembRegi.szip_txt.focus();
			document.getElementById("szip_txt").innerHTML="Enter Numeric only";
			return false;
		    }
			else{
		    document.frmMembRegi.szip_txt.focus();
			document.getElementById("szip_txt").innerHTML="";
			}
	if(document.frmMembRegi.szip_txt.value==""){
			//alert("Enter Zipcode in Primary Address");
			document.frmMembRegi.szip_txt.focus();
			document.getElementById("szip_txt").innerHTML="Enter Zipcode";
			return false;
		    }
			else{
		    document.frmMembRegi.szip_txt.focus();
			document.getElementById("szip_txt").innerHTML="";
			}
	     
	    if((document.frmMembRegi.szip_txt.value.length <3&& document.frmMembRegi.scountry_txt.value!="USA")){
			//alert("Enter Valid Zipcode in Primary Address");
	        document.frmMembRegi.szip_txt.focus();
			document.getElementById("szip_txt").innerHTML="Enter Valid Zipcode";
	        return false;
			}
	        else{
		    document.frmMembRegi.szip_txt.focus();
			document.getElementById("szip_txt").innerHTML="";
			}
	     
	    if((document.frmMembRegi.szip_txt.value.length >6 && document.frmMembRegi.scountry_txt.value!="USA")){
			//alert("Enter Valid Zipcode in Primary Address");
	        document.frmMembRegi.szip_txt.focus();
			document.getElementById("szip_txt").innerHTML="Enter Valid Zipcode";
	        return false;
			}
			else{
			document.frmMembRegi.szip_txt.focus();
			document.getElementById("szip_txt").innerHTML="";
			}
		if((document.frmMembRegi.szip_txt.value.length !=5&& document.frmMembRegi.scountry_txt.value=="USA")){
			//alert("Enter Valid Zipcode in Primary Address");
	        document.frmMembRegi.szip_txt.focus();
			document.getElementById("szip_txt").innerHTML="";
	        return false;
			}
	         
		if(document.frmMembRegi.szip_txt.value.indexOf(" ")==0){
			//alert("Enter Valid Zipcode in Primary Address");
			document.frmMembRegi.szip_txt.focus();
			return false; 
		}
		
	

	//----------------------IF ENTER EITHER Secondary Phone Number OR Secondary mobile Number

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
//				alert("Enter valid Secondary Phone Number");
				document.frmMembRegi.sphone_txt.focus();
						document.getElementById("sphone_txt").innerHTML="Enter phone number ";
				return false;
			}
			if(document.frmMembRegi.sphone_txt.value.length<10){
			//	alert("Enter valid Secondary Phone Number");
				document.frmMembRegi.sphone_txt.focus();
						document.getElementById("sphone_txt").innerHTML="Enter phone number ";
				return false;
			}
			
				else{
			    document.frmMembRegi.sphone_txt.focus();
				document.getElementById("sphone_txt").innerHTML="";
				}
			if(document.frmMembRegi.sphone_txt.value.length>12){
				//	alert("Enter valid Secondary Phone Number");
					document.frmMembRegi.sphone_txt.focus();
							document.getElementById("sphone_txt").innerHTML="Enter phone number";
					return false;
				}
				
					else{
				    document.frmMembRegi.sphone_txt.focus();
					document.getElementById("sphone_txt").innerHTML="";
					}

		}


		//-------------------------------------------------------------------------------------------------------
		//----------------------IF ENTER EITHER Primary Phone Number OR Primary mobile Number
		if(document.frmMembRegi.sphone_txt.value=="")
		{ //alert("Enter Secondary Phone Number");
		  document.frmMembRegi.sphone_txt.focus();
		  document.getElementById("sphone_txt").innerHTML="Enter Numeric only";
		  return false;}
		else{
			document.frmMembRegi.sphone_txt.focus();
			document.getElementById("sphone_txt").innerHTML="";
			}
		if(Dospace(document.frmMembRegi.sphone_txt.value)){
					//alert("Enter Valid Phone Number in Secondary Address");
			     	document.frmMembRegi.sphone_txt.focus();
			     	document.getElementById("sphone_txt").innerHTML="Enter Numeric only";
			  		return false;
					}
		else{
			document.frmMembRegi.sphone_txt.focus();
			document.getElementById("sphone_txt").innerHTML="";
			}
					if(document.frmMembRegi.sphone_txt.value.indexOf(" ")==0){
				//alert("Enter Valid Phone Number in Secondary Address");
				document.frmMembRegi.sphone_txt.focus();
				document.getElementById("sphone_txt").innerHTML="Enter Numeric only";
				return false; 
			}
					else{
						document.frmMembRegi.sphone_txt.focus();
						document.getElementById("sphone_txt").innerHTML="";
						}

		//----------------------------------Secondary MOBILE---------------------------------------


		if(document.frmMembRegi.smob_txt.value!="")
		{		
		 len7=document.frmMembRegi.smob_txt.value.length;
		 strnum = document.frmMembRegi.smob_txt.value;
		  var GoodChars = "0123456789()-+ ";
		 valid = 1;
		 for(j=0;j<len7;j++)
		{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
		   { valid=0;} }
		if(valid!=1)
		{
		 document.frmMembRegi.smob_txt.focus();
		 document.getElementById("smob_txt").innerHTML="Enter mobile number";
		 return false;}
		 
		 if(isnotAlpha1(document.frmMembRegi.smob_txt.value))
		{
		  document.frmMembRegi.smob_txt.focus();
		  document.getElementById("smob_txt").innerHTML="Enter mobile number";
		   return false; }
			if(document.frmMembRegi.smob_txt.value.length<10){
		
					document.frmMembRegi.smob_txt.focus();
							document.getElementById("smob_txt").innerHTML="Enter mobile number ";
					return false;
				}
			if(document.frmMembRegi.smob_txt.value.length>12){
		
					document.frmMembRegi.smob_txt.focus();
							document.getElementById("smob_txt").innerHTML="Enter mobile number ";
					return false;
				}
				
					


		}
		 //-----------------------------------------Secondary fax--------------------------------


		if(document.frmMembRegi.sfax_txt.value!="")
		{		
		 len7=document.frmMembRegi.sfax_txt.value.length;
		 strnum = document.frmMembRegi.sfax_txt.value;
		  var GoodChars = "0123456789()-+ ";
		 valid = 1;
		 for(j=0;j<len7;j++)
		{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
		   { valid=0;} }
		if(valid!=1)
		{
		 document.frmMembRegi.sfax_txt.focus();
		 document.getElementById("sfax_txt").innerHTML="Enter fax number";
		 return false;}
		 
		 if(isnotAlpha1(document.frmMembRegi.sfax_txt.value))
		{
		  document.frmMembRegi.sfax_txt.focus();
		  document.getElementById("sfax_txt").innerHTML="Enter fax number";
		   return false; }
			if(document.frmMembRegi.sfax_txt.value.length<12){
				
					document.frmMembRegi.sfax_txt.focus();
							document.getElementById("sfax_txt").innerHTML="Enter fax number";
					return false;
				}
			if(document.frmMembRegi.sfax_txt.value.length>14){
					document.frmMembRegi.sfax_txt.focus();
							document.getElementById("sfax_txt").innerHTML="Enter fax number";
					return false;
				}
		}}

	return true;

	}

//-------------------------------- User status validation Ajax Script ------------------------------------------------

var httpRequest;

function usrStat()
{

if(document.frmMembRegi.usrname.value!="" && document.frmMembRegi.usrname.value.indexOf(' ')!=0)
	{

   var chsUserName=document.frmMembRegi.usrname.value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
        url= "UsrSignupAjax.html?cmd=checkusrnam&chsUserName="+chsUserName; 

        if (window.ActiveXObject) 
        { 
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
        } 
        else if (window.XMLHttpRequest) 
        { 
            httpRequest = new XMLHttpRequest(); 
        } 
     
        httpRequest.open("GET", url, true); 
        
        httpRequest.onreadystatechange =processUser; 
        httpRequest.send(null); 
   } 
   }
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function processUser() 
    { 
   
        if (httpRequest.readyState == 4) 
        { 
            if(httpRequest.status == 200) 
            { 
                //get the XML send by the servlet 
                 var salutationXML = httpRequest.responseXML.getElementsByTagName("userstatus")[0]; 
                 var salutationText = salutationXML.childNodes[0].nodeValue; 
     
                //Update the HTML 
                updateHTML(salutationXML); 
            } 
            else 
            { 
                alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
            } 
        } 
    } 
        
   /** 
    * This function parses the XML and updates the  
    * HTML DOM by creating a new text node is not present 
    * or replacing the existing text node. 
    */ 
    function updateHTML(salutationXML) 
    { 
        //The node valuse will give actual data 
        var salutationText = salutationXML.childNodes[0].nodeValue; 

		if(salutationText != "false")
		{
			alert("User Name already Exists Choose Another !");
			document.frmMembRegi.usrname.value="";
			document.frmMembRegi.usrname.focus();
		}
		      
    } 

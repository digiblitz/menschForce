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
//______________________________________________Question_________________________________________________

function myvalidate()
{

//_________________________________________________Answer_____________________________________________________

if(document.frmForgetPwd1.sAnswer.value=="")
{alert(" Enter your Answer to the secret Answer ");
 document.frmForgetPwd1.sAnswer.focus();
 return false;}

if(document.frmForgetPwd1.sAnswer.value.indexOf(" ")==0)
{alert(" Enter a valid secret Answer ");
 document.frmForgetPwd1.sAnswer.focus();
 return false;}

if(document.frmForgetPwd1.sAnswer.value.length>255)
 {alert("Enter Answer with in 255 characters ");
 document.frmForgetPwd1.sAnswer.focus();
return false;}
if(Dospace(document.frmForgetPwd1.sAnswer.value))
 	   { alert("Enter Valid Answer ");
 	     document.frmForgetPwd1.sAnswer.focus();
	   return false;
 }

return true;
}
           
//--------------------------------------EMAIL-----------------
//----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------
//--------------------------------function for Valid Email
function isnotVlaidEmail(str){
			 strmail=str;
			 firstat = strmail.indexOf("@");
			 lastat = strmail.lastIndexOf("@");
			 strmain=strmail.substring(0,firstat);
			 strsub=strmail.substring(firstat,str.length);
			 flag=false;
			 if(strmail.length>120)
			 { flag=true;alert("1");}
			 if(strmain.indexOf('  ')!=-1 || firstat==0 || firstat!=lastat || strsub.indexOf(' ')!=-1 )
			 {flag=true;}

			stringMailCheck1="!#$%^&*()+|<>?/=~,;:][{}"+"\\"+"\'";
			f3=1;
			for(j=0;j<strsub.length;j++)
			{if(stringMailCheck1.indexOf(strsub.charAt(j))!=-1)
			{f3=0;}}
			if(f3==0)
			{flag=true;}
							   	
			stringMailCheck2="!#$%^&*()+|<>?/=~,;:][{}"+"\\"+"\'";
			f4=1;
			for(j=0;j<strmain.length;j++)
			{if(stringMailCheck2.indexOf(strmain.charAt(j))!=-1)
			{f4=0;}}
			if(f4==0)
			{flag=true;}
			
			 k=0;
			 strlen=strsub.length;
			 for(i=0;i<strlen-1;i++)
			 { if(strsub.charAt(i)=='.')
			 {k=k+1;}}
			 if(k>3)
			 { flag=true;}
			 if(firstat==-1 || lastat==-1)
			 {flag=true;}
			 if(Number(strmain))
			 {flag=true;}
			 if(firstat != lastat )
			 {flag=true;}
			 firstdot=strmail.indexOf(".",firstat);
			 lastdot=strmail.lastIndexOf(".");
			 if(firstdot == -1 || lastdot == -1 || lastdot==strmail.length-1)
			 {flag=true;}
			
			return flag;
}
//-----------------------------------------------------------------------------------------------
 //---------------------------------------------------------------------------------------------------------------

function myvalidate2()
{

 if(document.frmForgetPwd2.e_mail.value=="")
 {alert("Enter your Email ID");
  document.frmForgetPwd2.e_mail.focus();
 return false;}
 
 if(isnotVlaidEmail(document.frmForgetPwd2.e_mail.value))
 {alert("Enter a valid Email");
  document.frmForgetPwd2.e_mail.focus();
 return false;}

return true;

}


//----------------------- sec q/a validate ajax action ---------------------------------

var shttpRequest;

function forPwdSqa()
{



//alert(document.frmForgetPwd1.usrname.value);
if(document.frmForgetPwd1.usrname.value!="" && document.frmForgetPwd1.usrname.value.indexOf(' ')!=0)
	{

	var userName=document.frmForgetPwd1.usrname.value;

	 url= "UsrSignupAjax.html?cmd=sQaonLogName&usrName="+userName; 

        if (window.ActiveXObject) 
        { 
            shttpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
        } 
        else if (window.XMLHttpRequest) 
        { 
            shttpRequest = new XMLHttpRequest(); 
        } 
     
        shttpRequest.open("GET", url, true); 
        
        shttpRequest.onreadystatechange =processRequest1; 
        shttpRequest.send(null); 
   } 

   }

	
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function processRequest1() 
    { 
   
        if (shttpRequest.readyState == 4) 
        { 
            if(shttpRequest.status == 200) 
            { 
                //get the XML send by the servlet 
                 var salutationXML = shttpRequest.responseXML.getElementsByTagName("secqus")[0]; 
                 var salutationText = salutationXML.childNodes[0].nodeValue; 

				  var salutationText = salutationXML.childNodes[0].nodeValue; 
			
					/*if(salutationText != "")
					{*/
						
						document.frmForgetPwd1.sQustion.value="";
						document.frmForgetPwd1.sAnswer.value="";							
						//document.frmForgetPwd1.sQustion.value=salutationText;	
                        dispDiv(salutationText);		
					//}
              
            } 
            else 
            { 
                alert("Error loading page\n"+ shttpRequest.status +":"+ shttpRequest.statusText); 
            } 
        } 
    } 


function dispDiv(scrQue){
	if(scrQue != "" && scrQue != "null"){
		document.getElementById('secretQuest').style.display = "block";
		document.getElementById('noSecretQuest').style.display = "none";
		document.frmForgetPwd1.sQustion.value=scrQue;
	}
	else{
		document.getElementById('noSecretQuest').style.display = "block";
		document.getElementById('secretQuest').style.display = "none";

			document.frmForgetPwd1.sQustion.value="";
			document.frmForgetPwd1.sAnswer.value="";
	}

if(scrQue == "close")
{
	document.getElementById('noSecretQuest').style.display = "none";
	document.getElementById('secretQuest').style.display = "none";
}

}


var httpRequest;

function usrStat()
{
 //alert("test");

if(document.frmForgetPwd1.usrname.value!="" && document.frmForgetPwd1.usrname.value.indexOf(' ')!=0)
	{

   var chsUserName=document.frmForgetPwd1.usrname.value;

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
        
        httpRequest.onreadystatechange =processRequest; 
        httpRequest.send(null); 
   } 

else
	{

		var val="close";
		//alert(val);
		dispDiv(val);
		document.frmForgetPwd1.usrname.value="";
		document.frmForgetPwd1.sQustion.value="";
		document.frmForgetPwd1.sAnswer.value="";
	}

   }
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function processRequest() 
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
			forPwdSqa();
			
		}
		else
		{
			
			alert("User Name Doesnt Exist !!");
			document.frmForgetPwd1.usrname.value="";
			document.frmForgetPwd1.sQustion.value="";
			document.frmForgetPwd1.sAnswer.value="";

			document.frmForgetPwd1.usrname.focus();
		}
      
    } 
 

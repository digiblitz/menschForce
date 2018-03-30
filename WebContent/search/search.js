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
$(function(){
	var s=location.search.replace(/^\?.*s=([^&]+)/,'$1')
		,form=$('#search-form')
		,input=$('input[type=text]',form)
		,results=$('#search-results').height(0)
		,src='search/results.php'
		,ifr=$('<iframe width="100%" height="100%" frameborder="0" marginheight="0" marginwidth="0" allowTransparency="true"></iframe>')
	
	if(results.length)		
		ifr		
			.attr({
				src:src+'?s='+s
			})
			.appendTo(results)
		,input
			.val(decodeURI(s))
	
	window._resize=function(h){		
		results
			.height(h)
	}
})

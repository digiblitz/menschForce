<!--
Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/

License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/

Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)

Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.

"Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 --> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title>Account Manager Dashboard</title>
</head>

<body>
<!--========================================================
HEADER
=========================================================-->
  <div id="header">
    <!-- HEADER STARTS HERE -->
  <%@ include file = "../../include/menschForceHeader.jsp" %>
      <!-- HEADER ENDS HERE -->
    </div>
	
<!--=======content================================-->

<div class="content">
    <div class="thumb-box2">
        <div class="container">
            <h2 class="wow fadeIn">Position Progress</h2>
            <div class="row">
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 wow fadeInUp">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                            <div class="badge"><img src="img/page1_badge1.png" alt=""></div>
                            <div class="caption">
                                <p class="title">Open</p>
                                <p>If a requirement is posted by a recruited and the position of the job is not assigned to any one then the status of the job is open. This will be presented under opened.</p>
                                <a href="ListPostRequirementsByOpenStatus.html" class="btn-default btn4">Click here</a>                            </div>  
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 wow fadeInUp" data-wow-delay="0.1s">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                            <div class="badge"><img src="img/page1_badge2.png" alt=""></div>
                            <div class="caption">
                                <p class="title">Onhold</p>
                                <p>If a candidate applies for a job and his profile need to be evaluated, it takes time, and he is still there in consideration he will be considered as on hold candidate.</p>
                                <a href="ListPostRequirementsByOnHoldStatus.html" class="btn-default btn4">Click here</a>                            </div>  
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 wow fadeInUp" data-wow-delay="0.2s">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                            <div class="badge"><img src="img/page1_badge3.png" alt=""></div>
                            <div class="caption">
                                <p class="title">Closed</p>
                                <p>If a recruiter has selected candidates for all the vacancies, then the job status will be closed and henceforth no one can apply for the job. This will be presented under closed.</p>
                                <a href="ListPostRequirementsByClosedStatus.html" class="btn-default btn4">Click here</a>                            </div>  
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 wow fadeInUp" data-wow-delay="0.3s">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                            <div class="badge"><img src="img/page1_badge4.png" alt=""></div>
                            <div class="caption">
                                <p class="title">Declined</p>
                                <p>If a candidate applies for a job and if his profile does not meet the requirement his application will be rejected. This will be presented under declined tab.</p>
                                <a href="ListPostRequirementsByDeclinedStatus.html" class="btn-default btn4">Click here</a>                            </div>  
                        </div>
                    </div>
                </div>
            </div>
        </div>
     </div>

	 <div class="thumb-box2">
        <div class="container">
            <h2 class="wow fadeIn">Requirements</h2>
            <div class="row">
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 wow fadeInUp">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                            <div class="badge"><img src="img/page1_badge1.png" alt=""></div>
                            <div class="caption">
                                <p class="title">REQUIREMENT LIST</p>
                                <p>Mails from clients with requirements of them will be present under the category according to which recruiter should take candidates.</p>
                                <a href="RequirementMails.html" class="btn-default btn4">Click here</a>                            </div>  
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 wow fadeInUp" data-wow-delay="0.1s">
                    <div class="thumb-pad1">
					<a href="ListPostRequirements.html">
                        <div class="thumbnail">
                            <div class="badge"><img src="img/page1_badge2.png" alt=""></div>
                            <div class="caption">
                                <p class="title">POST REQUIREMENT</p>
                                <p>According to the requirements, the recruiter can post job vacancy in this category</p>
                                <a href="ListPostRequirements.html" class="btn-default btn4">Click here</a>                      </div>  
                        </div>
					</a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 wow fadeInUp" data-wow-delay="0.1s">
                    <div class="thumb-pad1">
					<a href="ListPostRequirements.html">
                        <div class="thumbnail">
                            <div class="badge"><img src="img/page1_badge2.png" alt=""></div>
                            <div class="caption">
                                <p class="title">Rate Negotiated Request List</p>
                                <p>Rate negotitated by the logged in user</p>
                                <a href="rateNegotiationRequestList.html" class="btn-default btn4">Click here</a>                      </div>  
                        </div>
					</a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 wow fadeInUp" data-wow-delay="0.2s">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                            <div class="badge"><img src="img/page1_badge3.png" alt=""></div>
                            <div class="caption">
                                <p class="title">RECEIVED RESUMES</p>
                                <p>Candidates posting for the job will upload their resumes. These resumes can be viewed in this category.</p>
                                <a href="ListAppliedCanDetails.html" class="btn-default btn4">Click here</a>                            </div>  
                        </div>
                    </div>
                </div>
                
            </div>
        </div>
     </div>

	  <div class="thumb-box2">
        <div class="container">
            <h2 class="wow fadeIn">Candidate Status</h2>
            <div class="row">
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 wow fadeInUp">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                            <div class="badge"><img src="img/page1_badge1.png" alt=""></div>
                            <div class="caption">
                                <p class="title">HOT LIST CANDIDATES</p>
                                <p>Candidates those resume already available to the recruiter’s database will be viewed under this category.</p>
                                <a href="GetHotListCandidate.html" class="btn-default btn4">Click here</a>                            </div>  
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 wow fadeInUp" data-wow-delay="0.1s">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                            <div class="badge"><img src="img/page1_badge2.png" alt=""></div>
                            <div class="caption">
                                <p class="title">STATUS</p>
                                <p>Job status can be viewed under this category.</p>
                                <a href="ListUpdateCandidateStatus.html" class="btn-default btn4">Click here</a>                            </div>  
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 wow fadeInUp" data-wow-delay="0.2s">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                            <div class="badge"><img src="img/page1_badge3.png" alt=""></div>
                            <div class="caption">
                                <p class="title">CANDIDATE LIST</p>
                                <p>Job status can be viewed under this category.</p>
                                <a href="resumeSearch.html?cmd=initCandidateStatus" class="btn-default btn4">Click here</a>                            </div>  
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 wow fadeInUp" data-wow-delay="0.3s">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                            <div class="badge"><img src="img/page1_badge4.png" alt=""></div>
                            <div class="caption">
                                <p class="title">FRESH CANDIDATE</p>
                                <p>New Candidate can be viewed under this category.</p>
                                <a href="GetFreshCandidateList.html" class="btn-default btn4">Click here</a>                            </div>  
                        </div>
                    </div>
                </div>
            </div>
        </div>
     </div>

	  <div class="thumb-box2">
        <div class="container">
            <h2 class="wow fadeIn">Vendor Contacts</h2>
            <div class="row">
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 wow fadeInUp">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                            <div class="badge"><img src="img/page1_badge1.png" alt=""></div>
                            <div class="caption">
                                <p class="title">VENDOR LIST</p>
                                <p>List of all the vendors and their contacts will be presented in this category</p>
                                <a href="ListVendorContacts.html" class="btn-default btn4">Click here</a>                            </div>  
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 wow fadeInUp" data-wow-delay="0.1s">
                    <div class="thumb-pad1">
                        <div class="thumbnail">
                            <div class="badge"><img src="img/page1_badge2.png" alt=""></div>
                            <div class="caption">
                                <p class="title">CLIENT CONTACTS</p>
                                <p>List of all the vendors and their contacts will be presented in this category</p>
                                <a href="ListClientContacts.html" class="btn-default btn4">Click here</a>                            </div>  
                        </div>
                    </div>
                </div>
               
            </div>
        </div>
     </div>
   
	</div>
<!--========================================================
FOOTER
=========================================================-->
  <div>
   
    
    <!-- FOOTER STARTS HERE -->
    
  		<%@ include file = "../../include/menschForceFooter.jsp" %>
      <!-- FOOTER ENDS HERE -->
      
  </div>  
</body>
</html>

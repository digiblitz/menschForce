/*******************************************************************************
 * /*******************************************************************************
 * * Copyright: 2019 digiBlitz Foundation
 * * 
 * * License: digiBlitz Public License 1.0 (DPL) 
 * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 * * 
 * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 * * 
 * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 * * 
 * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
package com.intuit.developer.sampleapp.crud.helper;

import java.text.ParseException;

import com.intuit.ipp.data.JobInfo;
import com.intuit.ipp.data.JobStatusEnum;
import com.intuit.ipp.util.DateUtils;

/**
 * @author dderose
 *
 */
public final class Job {
	
	private Job() {
		
	}

	public static JobInfo getJobInfo() throws ParseException {
		JobInfo jobInfo = new JobInfo();
		jobInfo.setDescription("In Progress");
		jobInfo.setStatus(JobStatusEnum.IN_PROGRESS);
		jobInfo.setStartDate(DateUtils.getDateWithPrevDays(2));
		jobInfo.setEndDate(DateUtils.getDateWithNextDays(5));
		jobInfo.setProjectedEndDate(DateUtils.getDateWithNextDays(5));
		return jobInfo;
	}

}

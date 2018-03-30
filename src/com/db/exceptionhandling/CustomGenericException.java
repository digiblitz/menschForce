/*******************************************************************************
 * Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/
 * 
 * License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 * 
 * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 * 
 * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 * 
 * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
package com.db.exceptionhandling;

public class CustomGenericException extends RuntimeException{

	/**
	 * author : Prabhu
	 */
	private static final long serialVersionUID = 1L;
	private int errCode;
	private String errMsg;

	//getter and setter methods
	
	public int geterrCode() {
		return errCode;
		}
	public void seterrCode(int errCode) {
		this.errCode = errCode;
		}
	public String geterrMsg() {
		return errMsg;
		}
	public void seterrMsg(String errMsg) {
		this.errMsg = errMsg;
		}

	public CustomGenericException(int errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	
	
}

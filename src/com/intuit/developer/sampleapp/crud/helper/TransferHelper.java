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

import java.math.BigDecimal;
import java.text.ParseException;

import com.intuit.ipp.data.Account;
import com.intuit.ipp.data.Transfer;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.DateUtils;

/**
 * @author dderose
 *
 */
public final class TransferHelper {
	
	private TransferHelper() {
		
	}

	public static Transfer getTransferFields(DataService service) throws FMSException {
		Transfer transfer = new Transfer();
        try {
            transfer.setTxnDate(DateUtils.getCurrentDateTime());
        } catch (ParseException e) {
            throw new FMSException("ParseException while getting current date.");
        }
        
        Account depositAccount = AccountHelper.getAssetAccount(service);
        transfer.setFromAccountRef(AccountHelper.getAccountRef(depositAccount));
        
        Account creditAccount = AccountHelper.getCreditCardBankAccount(service);
        transfer.setToAccountRef(AccountHelper.getAccountRef(creditAccount));
        
        transfer.setAmount(new BigDecimal("100.00"));
        transfer.setDomain("QBO");
        transfer.setPrivateNote("Transfer " + transfer.getAmount() + " from " + depositAccount.getFullyQualifiedName() + " to " + creditAccount.getFullyQualifiedName());
        
        return transfer;
	}

}

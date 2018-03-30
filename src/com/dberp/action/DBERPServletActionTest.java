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
package com.dberp.action;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dberp.payroll.session.DbPayrollERPSessionBean;

@Controller
public class DBERPServletActionTest {
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/dBERPTest.html")
	public ModelAndView syncEmployee(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException, SQLException{
		
		DbPayrollERPSessionBean objPayroll = new DbPayrollERPSessionBean();
		
		ArrayList AllOneWithholdingAllowanceList = objPayroll.selectAllOneWithholdingAllowance();
		System.out.println("print AllOneWithholdingAllowanceList-------Start------>");
		printArrayListData(AllOneWithholdingAllowanceList);
		System.out.println("print AllOneWithholdingAllowanceList-------END------>");
		
		double OneWithholdingAllowance = objPayroll.selectOneWithholdingAllowanceByPayrollPeriod("SEMIMONTHLY");
		System.out.println("print OneWithholdingAllowance-------Start------>");
		System.out.println("value of OneWithholdingAllowance is----------->"+OneWithholdingAllowance);
		System.out.println("print OneWithholdingAllowance-------END------>");
		
		ArrayList IncomeTaxWithholdList = objPayroll.selectIncomeTaxWithholdDetailsByPayrollPeriod("BIWEEKLY");
		System.out.println("print IncomeTaxWithholdList-------Start------>");
		printArrayListData(IncomeTaxWithholdList);
		System.out.println("print IncomeTaxWithholdList-------END------>");
		
		ArrayList ITWithholdListByPayPeriodAndMarital = objPayroll.selectITWithholdDetailsByPayPeriodAndMaritalStatus("WEEKLY", "MARRIED");
		System.out.println("print ITWithholdListByPayPeriodAndMarital-------Start------>");
		printArrayListData(ITWithholdListByPayPeriodAndMarital);
		System.out.println("print ITWithholdListByPayPeriodAndMarital-------END------>");
		
		ArrayList ITWithholdListByPayPeriodMaritalWages = objPayroll.selectITWithholdDetailsByPayPeriodMaritalAndWages("SEMIMONTHLY", "SINGLE", 18648);
		System.out.println("print ITWithholdListByPayPeriodMaritalWages-------Start------>");
		printArrayListData(ITWithholdListByPayPeriodMaritalWages);
		System.out.println("print ITWithholdListByPayPeriodMaritalWages-------END------>");
		
		double calcFederalITWithholdAmount = objPayroll.calculateFederalITWithholding(18000, 4673.41, 39.6, 16181.00);
		System.out.println("print calcFederalITWithholdAmount-------Start------>");
		System.out.println("value of calcFederalITWithholdAmount is----------->"+calcFederalITWithholdAmount);
		System.out.println("print calcFederalITWithholdAmount-------END------>");
		
		double federalITWithholdAmount = objPayroll.calculateFederalITWithholdAmount("SINGLE", 0, 18400, "SEMIMONTHLY");
		System.out.println("print federalITWithholdAmount-------Start------>");
		System.out.println("value of federalITWithholdAmount is----------->"+federalITWithholdAmount);
		System.out.println("print federalITWithholdAmount-------END------>");
		
			return null;
			
	}
	
	
	@SuppressWarnings("rawtypes")
	private static void printArrayListData(ArrayList listData) {

		System.out.println("size----------"+listData.size());
		if(listData!=null && listData.size()!=0){
			Iterator itEntList = listData.iterator();
			//int i=1;
			while(itEntList.hasNext()){
			
				String strItTaxlist[]= (String[])itEntList.next();
				
//				String unique_id = strItTaxlist[0];
//                String tax_year = strItTaxlist[1];
//                String payroll_period = strItTaxlist[2];
//                String one_withholding_allowance = strItTaxlist[3];
//                String marital_status = strItTaxlist[4];
//                String min_wage_amount = strItTaxlist[5];
//                String max_wage_amount = strItTaxlist[6];
//                String income_tax_amount = strItTaxlist[7];
//                String income_tax_amount_percent = strItTaxlist[8];
//                String excess_wage_amount = strItTaxlist[9];
                
                //System.out.println("array list data ---- "+i+"---------"+strItTaxlist[i]);
                for (int j = 0; j < strItTaxlist.length;j++){
        			//System.out.println("array list data ---- "+i+"---------"+listData.get(i));
                	System.out.println("array list data ---- "+j+"---------"+strItTaxlist[j]);
        		}
			}
		}
		
	}

}

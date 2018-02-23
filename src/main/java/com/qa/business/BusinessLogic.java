package com.qa.business;

import javax.inject.Inject;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

public class BusinessLogic {
	
	@Inject
	private JSONUtil myJsonConvertor;
	
	public Boolean checkAccountNumber(String account)
	{
		Account newAccount=myJsonConvertor.getObjectForJSON(account, Account.class);
		return newAccount.getAccountNumber().equals("9999");
	}

}

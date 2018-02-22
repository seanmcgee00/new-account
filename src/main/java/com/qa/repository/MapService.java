package com.qa.repository;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

public class MapService  implements ServiceInterface{

	private Map<Integer, Account> accountMap;
	private int count = 0;
	@Inject
	private JSONUtil myJsonConvertor;

	public MapService() {
		accountMap = new HashMap<Integer, Account>();
	}

	public void addAccountFromMap(Account userAccount) {
		accountMap.put(count, userAccount);
		count++;
	}

	public void removeAccountFromMap(Integer accountToRemove) {
		boolean countExists = accountMap.containsKey(accountToRemove);
		if (countExists) {
			accountMap.remove(accountToRemove);
		}
	}

	public Map<Integer, Account> getAccountMap() {
		return accountMap;
	}

	public int getNumberOfAccountWithFirstName(String firstNameOfAccount) {
		return (int) accountMap.values().stream()
				.filter(eachAccount -> eachAccount.getFirstName().equals(firstNameOfAccount)).count();
	}
	
	
	@Override
	public String getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findAnAccount(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findAccount(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteAccount(Long id) {
		int myId= id.intValue();
		boolean countExists = accountMap.containsKey(myId);
		if (countExists) {
			accountMap.remove(myId);
		}
		return null;
	}

	@Override
	public String createAccount(String account) {
		Account userAccount =myJsonConvertor.getObjectForJSON(account, Account.class);
		accountMap.put(count, userAccount);
		count++;
		return "{\"message\": \"account has been sucessfully added\"}";
	}

	@Override
	public String updateAccount(Long id, String account) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}

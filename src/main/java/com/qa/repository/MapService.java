package com.qa.repository;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

import javax.inject.Inject;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;
@ApplicationScoped
@Alternative 
public class MapService  implements ServiceInterface{

	private Map<Integer, Account> accountMap;
	private int count = 0;
	@Inject
	private JSONUtil myJsonConvertor;

	public MapService() {
		accountMap = new HashMap<Integer, Account>();
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
		String result =myJsonConvertor.getJSONForObject(accountMap);	
		return result;
	}

	@Override
	public String findAnAccount(Long id) {
		int myId= id.intValue();
		Account thisAccount= accountMap.get(myId);
		String result =myJsonConvertor.getJSONForObject(thisAccount);	
		return result;
	}

	@Override
	public Account findAccount(Long id) {
		int myId= id.intValue();
		Account thisAccount= accountMap.get(myId);
		return thisAccount;
	}

	@Override
	public String deleteAccount(Long id) {
		int myId= id.intValue();
		boolean countExists = accountMap.containsKey(myId);
		if (countExists) {
			accountMap.remove(myId);
		}
		return "{\"message\": \"account ID:"+myId+" has been sucessfully Deleted from map\"}";
	}

	@Override
	public String createAccount(String account) {
		Account newAccount =myJsonConvertor.getObjectForJSON(account, Account.class);
		accountMap.put(count, newAccount);
		count++;
		return "{\"message\": \"account has been sucessfully added to map\"}";
	}

	@Override
	public String updateAccount(Long id, String account) {
		int myId= id.intValue();
		
		Account newAccount =myJsonConvertor.getObjectForJSON(account, Account.class);
		Account oldAccount= accountMap.get(myId);
		if (oldAccount != null) {
			oldAccount.setAccountNumber(newAccount.getAccountNumber());
			oldAccount.setFirstName(newAccount.getFirstName());
			oldAccount.setSecondName(newAccount.getSecondName());
			return "{\"New message\": \"account sucessfully updated on map\"}";
	
		}
		else {
			return "{\"message\": \"No Account Found in map\"}";
		}
		
	}
	
	
	

}

package com.qa.repository;

import com.qa.domain.Account;

public interface ServiceInterface {
String getAllAccounts();
String findAnAccount(Long id);	
Account findAccount(Long id);
String deleteAccount(Long id);	
String createAccount(String account);
String updateAccount(Long id, String account);

}

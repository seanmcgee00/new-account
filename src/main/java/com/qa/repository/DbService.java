package com.qa.repository;



import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;
@RequestScoped
@Default
@Transactional(SUPPORTS)
public class DbService implements ServiceInterface{
	


	@PersistenceContext(name="primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil myJsonConvertor;
	
	
	public String getAllAccounts() {
		Query query = manager.createQuery("Select a FROM Account a");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
	
		String result=myJsonConvertor.getJSONForObject(accounts);
		return result ;
	}

	
	
	public String findAnAccount(Long id) {
		
		Account thisAccount= manager.find(Account.class, id);
		String result=myJsonConvertor.getJSONForObject(thisAccount);
		return result ;
		
	}
public Account findAccount(Long id) {
		
		return manager.find(Account.class, id);	
	}
	
	
	@Transactional(REQUIRED)
	public String deleteAccount(Long id) {
		Account thisAccount=myJsonConvertor.getObjectForJSON(findAnAccount(id), Account.class);
		if (thisAccount != null) {
			
			manager.remove(manager.contains(thisAccount) ? thisAccount : manager.merge(thisAccount));
		}
		return "{\"message\": \"account sucessfully deleted\"}";
	}
	

	@Transactional(REQUIRED)
	public String createAccount(String account) {
		Account thisAccount = myJsonConvertor.getObjectForJSON(account, Account.class);
		manager.persist(thisAccount);
		
		return "{\"message\": \"account has been sucessfully added\"}";
	}
	
	

	@Transactional(REQUIRED)
	public String updateAccount(Long id, String account) {
		Account thisAccount = myJsonConvertor.getObjectForJSON(account, Account.class);
		Account accountFromDB =findAccount(id);
		
		
		
		if (accountFromDB != null) {
			accountFromDB.setAccountNumber(thisAccount.getAccountNumber());
			accountFromDB.setFirstName(thisAccount.getFirstName());
			accountFromDB.setSecondName(thisAccount.getSecondName());
			
			
		//	return "{\"New message\": \"account sucessfully updated\"}";
			return myJsonConvertor.getJSONForObject(findAnAccount(id));
		}
		else {
			return "{\"message\": \"No Account Found\"}";
		}
		
	}
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setMyJsonConvertor(JSONUtil myJsonConvertor) {
		this.myJsonConvertor = myJsonConvertor;
	}

}

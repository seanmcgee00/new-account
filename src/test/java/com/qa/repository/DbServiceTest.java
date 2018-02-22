package com.qa.repository;

import static org.junit.Assert.*;

import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.domain.Account;
import com.qa.service.AccountService;
import com.qa.util.JSONUtil;




@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {
	

	@InjectMocks
	private DbService repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private JSONUtil util;

	private static final String MOCK_DATA_ARRAY = "[{\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"}]";

	private static final String MOCK_OBJECT = "{\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"}";

	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo. setMyJsonConvertor(util);
	}
	
	
	@Test
	public void testCreateAccount() {
		String reply = repo.createAccount(MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"message\": \"account has been sucessfully added\"}");
	}
	
	
//	@Test
//	public void getAllAccountsTest() {
//		
//		List<Account> accountList =myDbConnection.getAllAccounts();
//		Mockito.when(myDbConnection.getAllAccounts()).thenReturn(new Account("Sean","McGee","1234"));
//		Assert.assertEquals("Sean",accountList.get(0).getFirstName() );
//
//	}
//	
//	
//	@Test
//	public void findAnAccountTest() {
//		
//		Account testAccount= myDbConnection.findAnAccount(1);
//		Mockito.when(myDbConnection.findAnAccount(1)).thenReturn(new Account("Sean","McGee","1234"));
//		Assert.assertEquals("Sean",testAccount.getFirstName() );
//	}
//	
//	@Test
//	public void createAnAccountTest() {
//		
//		Account testAccount= myDbConnection.createAnAccount(new Account("Sean","McGee","1234"));
//		Mockito.when(myDbConnection.createAnAccount(new Account("Sean","McGee","1234"))).thenReturn(new Account("Sean","McGee","1234"));
//		Assert.assertEquals("Sean",testAccount.getFirstName() );
//	}
//	
//	@Test
//	public void updateAnAccountTest() {
//		
//		Account testAccount= myDbConnection.updateAnAccount(new Account("Sean","McGee","1234"));
//		Mockito.when(myDbConnection.updateAnAccount(new Account("Sean","McGee","1234"))).thenReturn(new Account("Sean","McGee","1234"));
//		Assert.assertEquals("Sean",testAccount.getFirstName() );
//	}
//	
//	@Test
//	public void deleteAccountTest() {
//		boolean success=  myDbConnection.deleteAccount(1);
//		Mockito.when(myDbConnection.deleteAccount(1)).thenReturn(true);
//		Assert.assertEquals(true,success);
//		
//	}


}

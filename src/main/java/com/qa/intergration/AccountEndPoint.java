package com.qa.intergration;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.persistence.*;

import com.qa.repository.DbService;
import com.qa.repository.ServiceInterface;

@Path("/account")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})

public class AccountEndPoint {

	@Inject
	private ServiceInterface accountInteraction;
	
	@GET
	@Path("/json")
	public String getAllAccounts(){
		return accountInteraction.getAllAccounts();
		
	}
	
	
	@GET
	@Path("/json/{id}")
	public String findAnAccount(@PathParam("id") String id){
		Long myId= Long.parseLong(id);
		return accountInteraction.findAnAccount(myId);
		
	}
	
	
	@POST
	@Path("/json")
	public String createAccount(String account){
		return accountInteraction.createAccount(account);
		
	}
	
	
	@PUT
	@Path("/json/{id}")
	public String updateAccount(@PathParam("id")String id,String account){
		Long myId= Long.parseLong(id);
		return accountInteraction.updateAccount(myId, account);
		
	}
	
	@DELETE
	@Path("/json/{id}")
	public String deleteAccount(@PathParam("id")String id){
		Long myId= Long.parseLong(id);
		return accountInteraction.deleteAccount(myId);
		
	}

	
}

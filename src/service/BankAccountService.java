package service;

import java.sql.Connection;

import domain.Bank;
import domain.BankAccountException;
import persistence.BankAccountRepository;

public class BankAccountService {
	
	private Connection con;
	private BankAccountRepository bankAccountRep;
	
	public BankAccountService( Connection con ) {
		this.con = con;
		this.bankAccountRep = new BankAccountRepository();
	}
	
	public void getBankAccounts( Bank bank ) throws BankAccountException {
		bank.clear();
		bank.addAll( bankAccountRep.selectAll( con ));
	}
}

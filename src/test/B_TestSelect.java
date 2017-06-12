package test;

import connection.OracleConnection;
import domain.Bank;
import domain.BankAccountException;
import service.BankAccountService;

public class B_TestSelect {

	public static void main(String[] args) {
		try {
			// create domain
			Bank bank = new Bank();
			
			// create oracle connection
			OracleConnection oracon = new OracleConnection();
			
			// create service layer
			BankAccountService bankAccountServ = new BankAccountService( oracon.getConnection() );
			
			// call service methods ...
			bankAccountServ.getBankAccounts( bank );
			
			// verify
			System.out.println( bank );
			
			oracon.close();
		} catch ( BankAccountException e ) {
			System.out.println( e.getMessage() );
		}
	}
}

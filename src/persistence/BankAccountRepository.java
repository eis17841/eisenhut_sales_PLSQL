package persistence;

import domain.BankAccount;
import domain.BankAccountException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BankAccountRepository {
	
	// attention: end of line use a space
	private final static String SQL_SELECT_ALL =
			"SELECT ACCOUNTNUMBER, " +
		    "       FIRSTNAME, " +
		    "       OPENINGDATE, " +
		    "       AMOUNT, " +
		    "       CREDITLIMIT " +
		    "FROM   BANKACCOUNT";
	
	private PreparedStatement selectAllStatement;

	// SELECT ALL -------------------------------------------------------------
	public List<BankAccount> selectAll( Connection con ) throws BankAccountException {
		try {
			// prepare statement if not prepared before
			if ( selectAllStatement == null ) {
				selectAllStatement = con.prepareStatement( SQL_SELECT_ALL );
			}
			//selectAllStatement.executeUpdate();    UPDATES data returns number of rows
			//selectAllStatement.executeQuery();     SELECT returns data
			ResultSet resultSet = selectAllStatement.executeQuery();
			if ( resultSet != null )
			{	
				List<BankAccount> accounts = new ArrayList<>();
				while( resultSet.next() )
				{	
					Long accountNumber = resultSet.getLong( 1 );
					String firstname = resultSet.getString( 2 );
					LocalDateTime openingDate = resultSet.getTimestamp( 3 ).toLocalDateTime();
					Double amount = resultSet.getDouble( 4 );
					Double creditlimit = resultSet.getDouble( 5 );
					accounts.add( new BankAccount( accountNumber, openingDate, firstname, amount, creditlimit ));					
				}
				return accounts;
			}
			else {
				throw new BankAccountException( "Could not read data from database" );
			}
			
		} catch ( SQLException e ) {
			throw new BankAccountException( "Could not execute SQL statement" );
		}
	}

}
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import domain.BankAccountException;

public class OracleConnection {
	
	private Connection con;
	
	public OracleConnection() throws BankAccountException {
		
		try {
			// load database driver
			// Class.forName VS import -> http://stackoverflow.com/questions/19674752/difference-between-import-and-class-forname-in-java
			// DOWNLOAD ORACLE DRIVER -> http://www.oracle.com/technetwork/database/enterprise-edition/jdbc-112010-090769.html
			// CHOOSE FILE -> ojdbc6.jar
			// RIGHT CLICK PROJECT -> JAVA BUILD PATH -> ADD EXTERNAL JAR -> ojdbc6.jar
			Class.forName( "oracle.jdbc.OracleDriver" );
			
			// by default open the connection
			open();
			
		} catch ( ClassNotFoundException e ) {	
			// throws unchecked exception
			throw new BankAccountException( "Couldnt find database driver" );
		}
	}


	public void open() throws BankAccountException {
		
		try {
			//get the connection
			con = DriverManager.getConnection ( "jdbc:oracle:thin:@192.168.56.101:1521:orcl", "thomas", "oracle" );
			con.setAutoCommit( false );
		} catch ( SQLException e ) {
			throw new BankAccountException( "Couldnt open database connection" );
		}
	}
	
	
	public void close() throws BankAccountException {
		
		try {
			con.close();
		} catch ( SQLException e ) {
			throw new BankAccountException( "Couldnt close database connection" );
		}
	}
	
	
	public Connection getConnection() {
		return con;
	}

}

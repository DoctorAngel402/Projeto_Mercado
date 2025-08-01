package connectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectionDatabase {
   // Endereco do Driver SQL Server
	private static final String Driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
   // Endereco do Banco de Dados
	private static final String URL = "jdbc:sqlserver://localhost:1433;encrypt=false;databaseName=Mercado";
   // Usuario do Banco de Dados
	private static final String user = "sa";
   // Senha do Banco de Dados
	private static final String password = "Senailab03";
	
	
	public static Connection getConnection() {
		
		try {
			Class.forName(Driver);
			System.out.println("Conexão Realizada!");
			return DriverManager.getConnection(URL, user, password);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro de Conexão!", e);
		}
				
		
	}
     public static void closeConnection(Connection con) {
		
    	 try {
    		 if(con != null) {
    			 con.close();
    			 System.out.println("Conexão Fechada");
    		 }
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Erro ao fechar conexão!", e);
		}
		
	}
     
    public static void closeConnection(Connection con, PreparedStatement stmt) {
    	closeConnection(con);
    	try {
			if(stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Erro ao fechar conexão!",e);
		}
    	
    }
	public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
		closeConnection(con, stmt);
		
		try {
			if(rs != null)
				rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Erro ao fechar conexão!", e);
		}
		
	}
	
	
	
	
}

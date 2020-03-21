package br.com.contactBook.connectionjdbc;
//feito por Robson Antas
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnect {
	
	public DBconnect() {
		
	}

	public Connection getConnection() throws ClassNotFoundException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/contatos?useTimezone=true&serverTimezone=America/Sao_Paulo","root","");
			
		}catch(SQLException e) {
			throw new RuntimeException("N�o foi poss�vel conectar-se ao Banco de Dados",e);
		}
	}
}

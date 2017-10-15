package br.inatel.dm107.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {

	private static final String SELECT_USER_BY_CREDENTIALS =
			"SELECT COUNT(id) FROM usuario "
			+ "WHERE usuario = ? AND senha = ?;";
	
	public boolean userExists(String usuario, String senha) {
		boolean userExists = false;
		
		try (Connection connection = DaoConnection.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_USER_BY_CREDENTIALS)) {
			pstmt.setString(1, usuario);
			pstmt.setString(2, senha);

			try (ResultSet rs = pstmt.executeQuery()) {
				int numeroLinhas = 0;
				
				while (rs.next()){
                    numeroLinhas = rs.getInt(1);
                }
				
				if (numeroLinhas == 1) {
					userExists = true;
				}
			} 
		} catch (SQLException e) {
			userExists = false;
		}

		return userExists;
	}
	
}

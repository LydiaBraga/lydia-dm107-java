package br.inatel.dm107.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

import br.inatel.dm107.model.Entrega;

public class EntregaDao {
	
	private static final String INSERT_ENTREGA =
			"INSERT INTO entrega(id, numero_pedido, id_cliente)"
			+ "VALUES(?, ?, ?);";

	private static final String SELECT_ENTREGA_BY_NUMERO_PEDIDO =
			"SELECT * FROM entrega"
			+ "WHERE numero_pedido = ?;";
	
	public void inserirEntrega(Entrega entrega) throws SQLException {
		try (Connection connection = DaoConnection.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_ENTREGA)) {
			pstmt.setInt(1, entrega.getId());
			pstmt.setInt(2, entrega.getNumeroPedido());
			pstmt.setInt(3, entrega.getIdCliente());
			pstmt.executeUpdate();	
		}
	}
	
	public Entrega selectEntregaByNumeroPedido(int numeroPedido) throws SQLException {
		Entrega entrega = null;
		
		try (Connection connection = DaoConnection.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_ENTREGA_BY_NUMERO_PEDIDO)) {
			pstmt.setInt(1, numeroPedido);

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					int id = rs.getInt(1);
					int noPedido = rs.getInt(2);
					int idCiente = rs.getInt(3);
					String nomeRecebedor = rs.getString(4);
					String cpfRecebedor = rs.getString(5);
					Instant dataEntrega = rs.getTimestamp(6).toInstant();
					
					entrega = new Entrega(id, noPedido, idCiente, nomeRecebedor, cpfRecebedor, dataEntrega);
				}
			}
		}

		return entrega;
	}
}

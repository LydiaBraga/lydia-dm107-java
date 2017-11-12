package br.inatel.dm107.model;

import java.sql.Timestamp;

public class Entrega {
	
	private Integer id;
	private Integer numeroPedido;
	private Integer idCliente;
	private String nomeRecebedor;
	private String cpfRecebedor;
	private Timestamp dataEntrega;
	
	public Entrega() {
		// For jersey dependencies
	}
	
	public Entrega(Integer id, Integer numeroPedido, Integer idCliente, String nomeRecebedor, String cpfRecebedor,
			Timestamp dataEntrega) {
		this.id = id;
		this.numeroPedido = numeroPedido;
		this.idCliente = idCliente;
		this.nomeRecebedor = nomeRecebedor;
		this.cpfRecebedor = cpfRecebedor;
		this.dataEntrega = dataEntrega;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Integer numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeRecebedor() {
		return nomeRecebedor;
	}

	public void setNomeRecebedor(String nomeRecebedor) {
		this.nomeRecebedor = nomeRecebedor;
	}

	public String getCpfRecebedor() {
		return cpfRecebedor;
	}

	public void setCpfRecebedor(String cpfRecebedor) {
		this.cpfRecebedor = cpfRecebedor;
	}

	public Timestamp getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Timestamp dataEntrega) {
		this.dataEntrega = dataEntrega;
	}	

}

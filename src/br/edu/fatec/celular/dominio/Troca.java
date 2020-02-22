package br.edu.fatec.celular.dominio;

import java.util.List;

public class Troca extends EntidadeDominio {
	private List<ItensTroca> itensTroca;
	private Double precoTotal;
	private Cliente cliente;
	private Pedido pedido;
	private StatusTroca statusTroca;

	public List<ItensTroca> getItensTroca() {
		return itensTroca;
	}

	public void setItensTroca(List<ItensTroca> itensTroca) {
		this.itensTroca = itensTroca;
	}

	public Double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(Double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public StatusTroca getStatusTroca() {
		return statusTroca;
	}

	public void setStatusTroca(StatusTroca statusTroca) {
		this.statusTroca = statusTroca;
	}

}

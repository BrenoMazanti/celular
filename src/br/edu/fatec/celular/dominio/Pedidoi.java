package br.edu.fatec.celular.dominio;

public class Pedidoi extends EntidadeDominio{
	private Celular celular = new Celular();
	private Double precoUni;
	private Integer qtde;
	private Double totalItem;
	private Pedido pedido = new Pedido();
	
	public Celular getCelular() {
		return celular;
	}
	public void setCelular(Celular celular) {
		this.celular = celular;
	}
	public Double getPrecoUni() {
		return precoUni;
	}
	public void setPrecoUni(Double preco) {
		this.precoUni = preco;
	}
	public Integer getQtde() {
		return qtde;
	}
	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Double getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(Double totalItem) {
		this.totalItem = totalItem;
	}

	
	
}

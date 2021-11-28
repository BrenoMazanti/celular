package br.edu.fatec.celular.dominio;

public class Pedidoi extends EntidadeDominio{
	private Celular celular;
	private Double precoUni;
	private Integer qtde;
	private Pedido pedido;
	
	public Celular getCelular() {
		return celular;
	}
	public void setCelular(Celular celular) {
		this.celular = celular;
	}
	public Double getPrecoUni() {
		return precoUni;
	}
	public void setPreco(Double preco) {
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

	
}

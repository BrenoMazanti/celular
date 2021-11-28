package br.edu.fatec.celular.dominio;

public class Carrinhoi extends EntidadeDominio{
	private Celular celular = new Celular();
	private Double precoUni;
	private Integer qtde;
	private Carrinho carrinho;
	
	public Carrinho getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
	public Double getPrecoUni() {
		return precoUni;
	}
	public void setPrecoUni(Double precoUni) {
		this.precoUni = precoUni;
	}
	public Integer getQtde() {
		return qtde;
	}
	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}
	public Celular getCelular() {
		return celular;
	}
	public void setCelular(Celular celular) {
		this.celular = celular;
	}
	
	
}

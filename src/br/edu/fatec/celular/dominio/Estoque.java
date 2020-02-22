package br.edu.fatec.celular.dominio;

public class Estoque extends EntidadeDominio{
	private Celular celular;
	private Integer qtdeEstoque;
	private Integer qtdeBloqueada;
	// preço de custo aqui?????
	
	public Celular getCelular() {
		return celular;
	}
	public void setCelular(Celular celular) {
		this.celular = celular;
	}
	public Integer getQtdeEstoque() {
		return qtdeEstoque;
	}
	public void setQtdeEstoque(Integer qtdeEstoque) {
		this.qtdeEstoque = qtdeEstoque;
	}
	public Integer getQtdeBloqueada() {
		return qtdeBloqueada;
	}
	public void setQtdeBloqueada(Integer qtdeBloqueada) {
		this.qtdeBloqueada = qtdeBloqueada;
	}
	
	
}

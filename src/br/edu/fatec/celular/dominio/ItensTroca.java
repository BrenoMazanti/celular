package br.edu.fatec.celular.dominio;

public class ItensTroca extends EntidadeDominio{
	private Celular celular;
	private Double precoUni;
	private Integer qtde; // vai ser menor ou igual a qtde comprada de um determinado celular
	private Troca troca; // 
	
	public Celular getCelular() {
		return celular;
	}
	public void setCelular(Celular celular) {
		this.celular = celular;
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
	public Troca getTroca() {
		return troca;
	}
	public void setTroca(Troca troca) {
		this.troca = troca;
	}
	
	
}

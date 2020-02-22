package br.edu.fatec.celular.dominio;

import java.util.List;

public class Pagamento extends EntidadeDominio{
	private List<DadosFormaPagamento> dados;
	private Double precoTotal; //preço dos produtos + frete?
	
	public List<DadosFormaPagamento> getDados() {
		return dados;
	}
	
	public void setDados(List<DadosFormaPagamento> dados) {
		this.dados = dados;
	}

	public Double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(Double precoTotal) {
		this.precoTotal = precoTotal;
	}
	
}

package br.edu.fatec.celular.dominio;

import java.util.List;

public class Carrinho extends EntidadeDominio{
	private List<ItensCarrinho> itens; //como tratar a quantidade, bloquear estoque
	private Double precoTotal; // soma dos pre�os de todos os celulares no carrinho
	
	public List<ItensCarrinho> getItens() {
		return itens;
	}
	public void setItens(List<ItensCarrinho> itens) {
			this.itens = itens; ////////////atualizar qtde caso tente adicionar o mesmo produto novamente e n�o criar novo
	}
	public Double getPreco() {
		return precoTotal;
	}
	public void setPreco(Double preco) {
		this.precoTotal = preco;
	}
	
	
	
}

package br.edu.fatec.celular.dominio;

import java.util.ArrayList;
import java.util.List;

public class Carrinho extends EntidadeDominio{
	private List<Carrinhoi> itens; //como tratar a quantidade, bloquear estoque
	private Double precoTotal; // soma dos preços de todos os celulares no carrinho
	private Cliente cliente = new Cliente();
	
	public List<Carrinhoi> getItens() {
		return itens;
	}
	public void setItens(List<Carrinhoi> itens) {
			this.itens = itens; ////////////atualizar qtde caso tente adicionar o mesmo produto novamente e não criar novo
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
	
	
	
}

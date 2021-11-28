package br.edu.fatec.celular.dominio;

import java.util.List;

public class Pedido extends EntidadeDominio{
	private List<Pedidoi> itens;
	private Double precoTotal; // preço dos produtos, para mostrar deve somar o frete
	private Endereco endereco;
	private ValorFrete valorFrete;
	private Cliente cliente;
	private Pagamento pagamento;
	private Integer statusPedido;
	private Double total; //total frete + produtos
	private Boolean confirmado; //se o pedido for finalizado pelo será true
	
	public Double getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(Double precoTotal) {
		this.precoTotal = precoTotal;
	}
	public ValorFrete getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(ValorFrete valorFrete) {
		this.valorFrete = valorFrete;
	}
	public void setStatusPedido(Integer statusPedido) {
		this.statusPedido = statusPedido;
	}
	
	public List<Pedidoi> getItens() {
		return itens;
	}
	public void setItens(List<Pedidoi> itens) {
		this.itens = itens;
	}
	
	public Double getPreco() {
		return precoTotal;
	}
	public void setPreco(Double precoTotal) {
		this.precoTotal = precoTotal;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Pagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	public Integer getStatusPedido() {
		return statusPedido;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Boolean getConfirmado() {
		return confirmado;
	}
	public void setConfirmado(Boolean confirmado) {
		this.confirmado = confirmado;
	}
	
		
}

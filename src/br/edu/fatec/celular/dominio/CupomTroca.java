package br.edu.fatec.celular.dominio;

public class CupomTroca extends FormaPagamento{
	private Double valor;
	private Troca troca; //troca que gerou este cupom
	private Pedido pedido; // pedido em que o cupom foi gasto (usado para pagar este pedido)
	
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Troca getTroca() {
		return troca;
	}
	public void setTroca(Troca troca) {
		this.troca = troca;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
		
}

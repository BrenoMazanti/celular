package br.edu.fatec.celular.dominio;

import java.util.List;

public class Pagamento extends EntidadeDominio{
	private Pedido pedido = new Pedido();
	private Cartao cartao = new Cartao();
	private CupomTroca cupom = new CupomTroca();
	private Integer qtdeParcelas;
	private Double vlParcela;
	private Double vlTotal;

	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Cartao getCartao() {
		return cartao;
	}
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	public CupomTroca getCupom() {
		return cupom;
	}
	public void setCupom(CupomTroca cupom) {
		this.cupom = cupom;
	}
	public Integer getQtdeParcelas() {
		return qtdeParcelas;
	}
	public void setQtdeParcelas(Integer qtdeParcelas) {
		this.qtdeParcelas = qtdeParcelas;
	}
	public Double getVlParcela() {
		return vlParcela;
	}
	public void setVlParcela(Double vlParcela) {
		this.vlParcela = vlParcela;
	}
	public Double getVlTotal() {
		return vlTotal;
	}
	public void setVlTotal(Double vlTotal) {
		this.vlTotal = vlTotal;
	}
	
	
}

package br.edu.fatec.celular.dominio;

public class DadosFormaPagamento extends EntidadeDominio{
	private FormaPagamento formaPagamento;
	private Double valor; //valor a ser pago com este cartão/cupom...
	private Integer parcelas;
	//parcelas?? valor de cada parcela?? em caso de utilizar apenas cupom seria uma única 
	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
		
}

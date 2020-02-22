package br.edu.fatec.celular.dominio;

public class Credito extends Cartao{
	
	private Integer parcelas;
	private Double taxa;
	
	public Integer getParcelas() {
		return parcelas;
	}
	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}
	public Double getTaxa() {
		return taxa;
	}
	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}
}

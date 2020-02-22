package br.edu.fatec.celular.dominio;

public class ValorFrete extends EntidadeDominio{
	private Estado estado;
	private Double preco;
	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
}

package br.edu.fatec.celular.dominio;

public class CelularVariedade extends Celular{
	private Cor cor;
	private Armazenamento armazenamento;
	private Celular celular;
	
	public Cor getCor() {
		return cor;
	}
	public void setCor(Cor cor) {
		this.cor = cor;
	}
	public Armazenamento getArmazenamento() {
		return armazenamento;
	}
	public void setArmazenamento(Armazenamento armazenamento) {
		this.armazenamento = armazenamento;
	}
	public Celular getCelular() {
		return celular;
	}
	public void setCelular(Celular celular) {
		this.celular = celular;
	}
	
	
}

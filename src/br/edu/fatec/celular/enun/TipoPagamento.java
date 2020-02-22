package br.edu.fatec.celular.enun;

public enum TipoPagamento {
	
	CARTAOCREDITO("Credito", 1),
	CUPOMTROCA("CupomTroca", 2),
	CARTAODEBITO("Debito", 3),
	BOLETO("Boleto", 4);
	
	private int codigo;
	private String descricao;
	
	TipoPagamento(String descricao, int codigo){
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
}

package br.edu.fatec.celular.enun;

public enum TipoResidencia {
	
	CASA("Casa", 1),
	APARTAMENTO("Apartamento", 2),
	CONDOMINIO("Condominio", 3);
	
	private int codigo;
	private String descricao;
	
	TipoResidencia(String descricao, int codigo){
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

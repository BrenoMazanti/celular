package br.edu.fatec.celular.dominio;

public class Precificacao extends EntidadeDominio{
	private String descricao;
	private String porcentagem;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getPorcentagem() {
		return porcentagem;
	}
	public void setPorcentagem(String porcentagem) {
		this.porcentagem = porcentagem;
	}
	
	/*CREATE TABLE tb_precificacao(
	id SERIAL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE,
	ativo BOOLEAN DEFAULT TRUE,
	descricao VARCHAR(20) NOT NULL,
	porcentagem DOUBLE NOT NULL
);
*/
}

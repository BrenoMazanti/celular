package br.edu.fatec.celular.dominio;

public class Cartao extends FormaPagamento{
	
	private String descricao;
	private String numero;
	private String mes;
	private String ano;
	private String codigo;
	private String nomeTitular;
	private String cpfTitular;
	private BandeiraCartao bandeira;
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNomeTitular() {
		return nomeTitular;
	}
	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}
	public String getCpfTitular() {
		return cpfTitular;
	}
	public void setCpfTitular(String cpfTitular) {
		this.cpfTitular = cpfTitular;
	}
	public BandeiraCartao getBandeira() {
		return bandeira;
	}
	public void setBandeira(BandeiraCartao bandeira) {
		this.bandeira = bandeira;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}

package br.edu.fatec.celular.dominio;

import br.edu.fatec.celular.enun.TipoResidencia;

public class Endereco extends EntidadeDominio {

	private String descricao;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private Boolean principal; // define se é o endereço principal para entrega
	private Boolean cobranca; // define se é o endereço de cobrança
	private Cliente cliente = new Cliente();
	private String cidade;
	private String uf;
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public Boolean getPrincipal() {
		return principal;
	}
	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}
	
	public Boolean getCobranca() {
		return cobranca;
	}
	public void setCobranca(Boolean cobranca) {
		this.cobranca = cobranca;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}

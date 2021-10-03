package br.edu.fatec.celular.dominio;

import java.util.List;

public class Celular extends EntidadeDominio {
	private String descricao;
	private String tipoChip;
	private String cameraTraseira;
	private String cameraFrontal;
	private String tamanhoTela;
	private String resolucao;
	private String altura;
	private String largura;
	private String comprimento;
	private String peso;
	private String ram;
	private String processador;
	private String componentes;
	private String foto;
		
	//private List<Cor> cores; ////
	//private List<Armazenamento> armazenamentos; ////
	private List<CelularVariedade> variedades;
	private Marca marca;
	private SistemaOperacional so;
	private Double preco;
//	private Precificacao precificacao;
	private Estoque estoque;
	
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipoChip() {
		return tipoChip;
	}
	public void setTipoChip(String tipoChip) {
		this.tipoChip = tipoChip;
	}
	public String getCameraTraseira() {
		return cameraTraseira;
	}
	public void setCameraTraseira(String cameraTraseira) {
		this.cameraTraseira = cameraTraseira;
	}
	public String getCameraFrontal() {
		return cameraFrontal;
	}
	public void setCameraFrontal(String cameraFrontal) {
		this.cameraFrontal = cameraFrontal;
	}
	public String getTamanhoTela() {
		return tamanhoTela;
	}
	public void setTamanhoTela(String tamanhoTela) {
		this.tamanhoTela = tamanhoTela;
	}
	public String getResolucao() {
		return resolucao;
	}
	public void setResolucao(String resolucao) {
		this.resolucao = resolucao;
	}
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	public String getLargura() {
		return largura;
	}
	public void setLargura(String largura) {
		this.largura = largura;
	}
	public String getComprimento() {
		return comprimento;
	}
	public void setComprimento(String comprimento) {
		this.comprimento = comprimento;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getProcessador() {
		return processador;
	}
	public void setProcessador(String processador) {
		this.processador = processador;
	}
	public List<CelularVariedade> getVariedades() {
		return variedades;
	}
	public void setVariedades(List<CelularVariedade> variedades) {
		this.variedades = variedades;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public SistemaOperacional getSo() {
		return so;
	}
	public void setSo(SistemaOperacional so) {
		this.so = so;
	}
	public String getComponentes() {
		return componentes;
	}
	public void setComponentes(String componentes) {
		this.componentes = componentes;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}

}

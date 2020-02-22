package br.edu.fatec.celular.dominio;

import br.edu.fatec.celular.enun.TipoPagamento;

public abstract class FormaPagamento extends EntidadeDominio {
	private Cliente cliente;
	private TipoPagamento tipo;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoPagamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoPagamento tipo) {
		this.tipo = tipo;
	}

}

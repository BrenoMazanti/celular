package br.edu.fatec.celular.util;

import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.celular.dominio.EntidadeDominio;

public class Resultado {
	private String msg;
	private List<EntidadeDominio> entidades;

	public Resultado() {
		this.entidades = new ArrayList<>();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<EntidadeDominio> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<EntidadeDominio> entidades) {
		this.entidades = entidades;
	}
}

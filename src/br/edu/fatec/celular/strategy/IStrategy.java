package br.edu.fatec.celular.strategy;

import br.edu.fatec.celular.dominio.EntidadeDominio;

public interface IStrategy {
	public String processar(EntidadeDominio entidade);
}

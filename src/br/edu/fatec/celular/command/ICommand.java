package br.edu.fatec.celular.command;

import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.util.Resultado;

public interface ICommand {
	public Resultado execute(EntidadeDominio dominio);
}

package br.edu.fatec.celular.command;

import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.util.Resultado;

public class ExcluirCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio dominio) {

		return fachada.excluir(dominio);
	}
}
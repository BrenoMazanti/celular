package br.edu.fatec.celular.command;

import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.util.Resultado;

public class LoginCommand extends AbstractCommand{
	@Override
	public Resultado execute(EntidadeDominio dominio) {
		return fachada.consultar(dominio);
	}
}

package br.edu.fatec.celular.command;

import br.edu.fatec.celular.fachada.Fachada;
import br.edu.fatec.celular.fachada.IFachada;

public abstract class AbstractCommand implements ICommand {
	protected IFachada fachada = new Fachada();
}

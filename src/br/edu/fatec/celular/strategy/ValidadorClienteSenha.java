package br.edu.fatec.celular.strategy;

import java.util.List;

import br.edu.fatec.celular.dominio.Cliente;
import br.edu.fatec.celular.dominio.EntidadeDominio;

public class ValidadorClienteSenha implements IStrategy{
	public String processar(EntidadeDominio entidade) {

		if (entidade instanceof Cliente) {
			
			Cliente cli = new Cliente();
			cli = (Cliente) entidade;
			
			String senha = cli.getSenha();
			String confirmarSenha = cli.getConfirmarSenha();
			
			if (senha != confirmarSenha) {
				return null;
			}
			else {
				return null;
			}
		}

		else {
			return "Deve ser registrado um Cliente!";
		}

	}

}

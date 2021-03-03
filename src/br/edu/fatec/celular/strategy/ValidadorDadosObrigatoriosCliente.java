package br.edu.fatec.celular.strategy;

import java.util.List;

import br.edu.fatec.celular.dominio.Cliente;
import br.edu.fatec.celular.dominio.EntidadeDominio;

public class ValidadorDadosObrigatoriosCliente implements IStrategy{
	public String processar(EntidadeDominio entidade) {

		if (entidade instanceof Cliente) {
			
			String msg = "O(s) campo(s):\n";
			int cont = 0;
			Cliente cli = new Cliente();
			cli = (Cliente) entidade;
			
			String email = cli.getEmail();
			String senha = cli.getSenha();
			String confirmarSenha = cli.getConfirmarSenha();
			String cpf = cli.getCpf();
			String nome = cli.getNome();
			String dataNascimento = cli.getDataNascimento();
			String sexo = cli.getSexo();
			String telefone = cli.getTelefone();

			if (email == null || email == "") {
				msg += "- Email\n";
				cont ++;
			}

			if (senha == null || senha == "") {
				msg += "- Senha\n";
				cont ++;
			}
			
			if (confirmarSenha == null || confirmarSenha == "") {
				msg += "- Confirmar Senha\n";
				cont ++;
			}
			
			if (cpf == null || cpf == "") {
				msg += "- CPF\n";
				cont ++;
			}
			
			if (nome == null || nome == "") {
				msg += "- Nome\n";
				cont ++;
			}
			
			if (dataNascimento == null || dataNascimento == "") {
				msg += "- Data de Nascimento\n";
				cont ++;
			}
			
			if (sexo == null || sexo == "") {
				msg += "- Sexo\n";
				cont ++;
			}
			
			if (telefone == null || telefone == "") {
				msg += "- Telefone\n";
				cont ++;
			}
						
			if(cont == 1)
				msg += "é obrigatório.";
			else if(cont > 1)
				msg += "são obrigatórios.";
			else
				return null;
			return msg;
		}

		else {
			return "Deve ser registrado um Cliente!";
		}

	}
}

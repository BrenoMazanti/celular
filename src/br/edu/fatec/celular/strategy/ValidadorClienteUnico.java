package br.edu.fatec.celular.strategy;

import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.celular.dominio.Cliente;
import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.dao.ClienteDAO;
import br.edu.fatec.celular.dao.IDAO;

public class ValidadorClienteUnico implements IStrategy {
		public String processar(EntidadeDominio entidade) {
			
			List<EntidadeDominio> cadastrados = new ArrayList<EntidadeDominio>();
			
			if (entidade instanceof Cliente) {
				try {
					IDAO dao = new ClienteDAO ();
					cadastrados = dao.consultar(entidade);
				} catch (Exception e) { // era SQLException
					e.printStackTrace();
				}
				
				if(cadastrados.isEmpty())
					return null;
				
				String msg = "O(s) campo(s):\n";
				
				int cont = 0;
				Cliente cli = new Cliente();
				cli = (Cliente) entidade;
				
				Cliente cad = new Cliente();
				cad = (Cliente) cadastrados.get(0);
				

				if (cli.getEmail().equals(cad.getEmail())) {
					msg += "- Email\n";
					cont ++;
				}

				if (cli.getCpf().equals(cad.getCpf())) {
					msg += "- CPF\n";
					cont ++;
				}
				
							
				if(cont == 1)
					msg += "já foi cadastrado.";
				else if(cont > 1)
					msg += "já foram cadastrados.";
				else
					return null;
				return msg;
			}

			else {
				return "Deve ser registrado um Cliente!";
			}

		}
	}


package br.edu.fatec.celular.vh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.fatec.celular.dao.EnderecoDAO;
import br.edu.fatec.celular.dominio.Cidade;
import br.edu.fatec.celular.dominio.Cliente;
import br.edu.fatec.celular.dominio.Endereco;
import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.enun.TipoResidencia;
import br.edu.fatec.celular.util.Resultado;

public class EnderecoVh implements IViewHelper{
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest req) {
		// TODO Auto-generated method stub
		String operacao = req.getParameter("operacao");
		Endereco endereco = new Endereco();
		Date dtCadastro;
		Calendar calendar;
		String descricao;
		//TipoResidencia tipoResidencia;
		String logradouro;
		String numero;
		String complemento;
		String bairro;
		String cep;
		Boolean principal = false; // define se é o endereço principal para entrega
		Boolean cobranca = false; // define se é o endereço de cobrança
		String cidade;
		String uf;
		Cliente cliente = new Cliente();
		
		if (operacao != null && operacao.equals("SALVAR")) {
			
			calendar = Calendar.getInstance();
			dtCadastro = calendar.getTime();
			
			if (req.getParameter("txtDescricao") != null && !req.getParameter("txtDescricao").equals(""))
				descricao = req.getParameter("txtDescricao");
			else
				descricao = "";
			
			//if (req.getParameter("txtTipoResidencia") != null && !req.getParameter("txtTipoResidencia").equals(""))
			//	tipoResidencia.setCodigo(Integer.parseInt(req.getParameter("txtTipoResidencia")));
			//else
			//	tipoResidencia = 0;
			
			if (req.getParameter("txtLogradouro") != null && !req.getParameter("txtLogradouro").equals(""))
				logradouro = req.getParameter("txtLogradouro");
			else
				logradouro = "";

			if (req.getParameter("txtNumero") != null && !req.getParameter("txtNumero").equals(""))
				numero = req.getParameter("txtNumero");
			else
				numero = "";

			if (req.getParameter("txtComplemento") != null && !req.getParameter("txtComplemento").equals(""))
				complemento = req.getParameter("txtComplemento");
			else
				complemento = "";

			if (req.getParameter("txtCep") != null && !req.getParameter("txtCep").equals(""))
				cep = req.getParameter("txtCep");
			else
				cep = "";

			if (req.getParameter("txtBairro") != null && !req.getParameter("txtBairro").equals(""))
				bairro = req.getParameter("txtBairro");
			else
				bairro = "";
			
			if (req.getParameter("txtEstado") != null && !req.getParameter("txtEstado").equals(""))
				uf = req.getParameter("txtEstado");
			else
				uf = null;
			
			if (req.getParameter("txtCidade") != null && !req.getParameter("txtCidade").equals(""))
				cidade = req.getParameter("txtCidade");
			else
				cidade = null;
			
			cliente = (Cliente) req.getSession().getAttribute("cliente");
			
			endereco.setDtCadastro(dtCadastro);
			endereco.setDescricao(descricao.toUpperCase());
			endereco.setLogradouro(logradouro.toUpperCase());
			endereco.setNumero(numero.toUpperCase());
			endereco.setComplemento(complemento.toUpperCase());
			endereco.setBairro(bairro.toUpperCase());
			endereco.setCep(cep);
			endereco.setCidade(cidade.toUpperCase());
			endereco.setUf(uf);
			endereco.setCliente(cliente);
			endereco.setPrincipal(principal);
			endereco.setCobranca(cobranca);
	
		}
		
		else if(operacao != null && operacao.equals("ALTERAR")) {
			
			calendar = Calendar.getInstance();
			endereco.setDtAlteracao(calendar.getTime());
			
			if (req.getParameter("txtDescricao") != null && !req.getParameter("txtDescricao").equals(""))
				endereco.setDescricao(req.getParameter("txtDescricao").toUpperCase());
			
			//if (req.getParameter("txtTipoResidencia") != null && !req.getParameter("txtTipoResidencia").equals(""))
			//	tipoResidencia.setCodigo(Integer.parseInt(req.getParameter("txtTipoResidencia")));
			//else
			//	tipoResidencia = 0;
			
			if (req.getParameter("txtLogradouro") != null && !req.getParameter("txtLogradouro").equals(""))
				endereco.setLogradouro(req.getParameter("txtLogradouro").toUpperCase());

			if (req.getParameter("txtNumero") != null && !req.getParameter("txtNumero").equals(""))
				endereco.setLogradouro(req.getParameter("txtNumero").toUpperCase());

			if (req.getParameter("txtComplemento") != null && !req.getParameter("txtComplemento").equals(""))
				endereco.setComplemento(req.getParameter("txtComplemento").toUpperCase());

			if (req.getParameter("txtCep") != null && !req.getParameter("txtCep").equals(""))
				endereco.setCep(req.getParameter("txtCep"));

			if (req.getParameter("txtBairro") != null && !req.getParameter("txtBairro").equals(""))
				endereco.setBairro(req.getParameter("txtBairro").toUpperCase());
			
			if (req.getParameter("txtEstado") != null && !req.getParameter("txtEstado").equals(""))
				endereco.setUf(req.getParameter("txtEstado"));
			
			if (req.getParameter("txtCidade") != null && !req.getParameter("txtCidade").equals(""))
				endereco.setCidade(req.getParameter("txtCidade").toUpperCase());
			
			
			cliente = (Cliente) req.getSession().getAttribute("cliente");
			
			endereco.setCliente(cliente);
			
		}
		
		else if(operacao != null && operacao.equals("CONSULTAR")) {
			cliente = (Cliente) req.getSession().getAttribute("cliente");
			
			endereco.setCliente(cliente);
		}
		
		else
		{
			HttpSession session = req.getSession();
			Resultado resultado = (Resultado) session.getAttribute("resultado");
			String txtId = req.getParameter("txtId");
			int id = 0;
			
			if(txtId != null && !txtId.trim().equals(""))
			{
				id = Integer.parseInt(txtId);
			}
			
			for(EntidadeDominio e: resultado.getEntidades()){
				if(e.getId() == id)
				{
					endereco = (Endereco)e;
				}
			}
		}
		return endereco;
	}

	@Override
	public void setEntidade(HttpServletRequest req, HttpServletResponse resp, Resultado resultado)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		RequestDispatcher d = null;

		String operacao = req.getParameter("operacao");

		if (resultado.getMsg() == null) {
			if (operacao.equals("SALVAR")) {
				resultado.setMsg("Endereço cadastrado com sucesso!");
				req.setAttribute("enderecos", resultado.getEntidades());
				req.setAttribute("resultado", resultado.getMsg());
				
				d = req.getRequestDispatcher((String) req.getSession().getAttribute("pagina"));
			}
			if (operacao.equals("CONSULTAR")) {
				//System.out.println(resultado.getEntidades());
				if(!resultado.getEntidades().isEmpty() && resultado.getEntidades().get(0) != null) {
					req.setAttribute("enderecos", resultado.getEntidades());
					d = req.getRequestDispatcher(req.getParameter("pagina"));
				}
				else {
					resultado.setMsg("Endereço não encontrado!");
					req.setAttribute("resultado", resultado.getMsg());
					d = req.getRequestDispatcher("telainicial.jsp");
				}
					
					
				
			}
			
			if (operacao.equals("ALTERAR")) {
				//System.out.println(resultado.getEntidades());
				if(!resultado.getEntidades().isEmpty() && resultado.getEntidades().get(0) != null) {
					
					Cliente cli = (Cliente) req.getSession().getAttribute("cliente");
					Endereco endereco = (Endereco) resultado.getEntidades().get(0);
					
					for(int i = 0; i < cli.getEnderecos().size(); i++) {
						
						if (endereco.getId() == cli.getEnderecos().get(i).getId()) {
							cli.getEnderecos().set(i, endereco);
							req.getSession().setAttribute("cliente", cli);
							break;
						}
						
					}
					
					d = req.getRequestDispatcher((String) req.getSession().getAttribute("pagina"));
				}
				else {
					resultado.setMsg("Endereço não encontrado!");
					req.setAttribute("resultado", resultado.getMsg());
					d = req.getRequestDispatcher("telalogin.jsp");
				}
				
			}
			
		}
		else {
			if(operacao.equals("SALVAR")) {
				req.setAttribute("resultado", resultado.getMsg());
				d = req.getRequestDispatcher("FormEndereco.jsp");
			}
			if(operacao.equals("ALTERAR")) {
				req.setAttribute("resultado", resultado.getMsg());
				d = req.getRequestDispatcher("FormEndereco.jsp");
			}
		}
		
		
		
		//d = req.getRequestDispatcher("consultarcliente.jsp");
		
		/*req.getSession().setAttribute("resultado", resultado);
		d = req.getRequestDispatcher("FormCliente.jsp");*/
		
		/*if (resultado.getMsg() == null && operacao.equals("CONSULTAR")) {

			req.setAttribute("cli", resultado.getEntidades().get(0));
			d = req.getRequestDispatcher("consultarcliente.jsp");
		}

		if (resultado.getMsg() == null && operacao.equals("EXCLUIR")) {

			req.getSession().setAttribute("resultado", null);
			d = req.getRequestDispatcher("consultarcliente.jsp");
		}

		if (resultado.getMsg() != null) {
			if (operacao.equals("SALVAR") || operacao.equals("ALTERAR")) {
				
			}
		}*/

		try {
			d.forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package br.edu.fatec.celular.vh;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		TipoResidencia tipoResidencia;
		String logradouro;
		String numero;
		String complemento;
		String bairro;
		String cep;
		Boolean principal; // define se é o endereço principal para entrega
		Boolean cobranca; // define se é o endereço de cobrança
		Cidade cidade;
		Cliente cliente;
		
		if (operacao != null && operacao.equals("SALVAR")) {
			
			calendar = Calendar.getInstance();
			dtCadastro = calendar.getTime();
			
			if (req.getParameter("txtDescricao") != null && !req.getParameter("txtDescricao").equals(""))
				descricao = req.getParameter("txtDescricao");
			else
				descricao = "";
			
			if (req.getParameter("txtTipoResidencia") != null && !req.getParameter("txtTipoResidencia").equals(""))
				tipoResidencia.setCodigo(Integer.parseInt(req.getParameter("txtTipoResidencia")));
			else
				tipoResidencia = 0;
			
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
			
			/*if (req.getParameter("txtEstado") != null && !req.getParameter("txtEstado").equals(""))
				estado = req.getParameter("txtEstado"));
			else
				estado = null;*/
			
			if (req.getParameter("txtCidade") != null && !req.getParameter("txtCidade").equals(""))
				cidade.setId(Integer.parseInt(req.getParameter("txtCidade")));
			else
				cidade = null;
			
			cliente = (Cliente) req.getSession().getAttribute("cliente");
			
			endereco.setDtCadastro(dtCadastro);
			endereco.setDescricao(descricao);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setComplemento(complemento);
			endereco.setBairro(bairro);
			endereco.setCep(cep);
			endereco.setCidade(cidade);
			endereco.setTipoResidencia(tipoResidencia);
			endereco.setCliente(cliente);
			endereco.setPrincipal(principal);
			endereco.setCobranca(cobranca);
	
		}
		
		else if(operacao != null && operacao.equals("CONSULTAR")) {
			if (req.getParameter("txtEmail") != null && !req.getParameter("txtEmail").equals(""))
				email = req.getParameter("txtEmail");
			else
				email = "";

			if (req.getParameter("txtSenha") != null && !req.getParameter("txtSenha").equals(""))
				senha = req.getParameter("txtSenha");
			else
				senha = "";
			
			cli.setEmail(email);
			cli.setSenha(senha);
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
				resultado.setMsg("Cliente cadastrado com sucesso!");
				req.setAttribute("resultado", resultado.getMsg());
				d = req.getRequestDispatcher("FormCliente.jsp");
			}
			if (operacao.equals("CONSULTAR")) {
				System.out.println(resultado.getEntidades());
				if(!resultado.getEntidades().isEmpty() && resultado.getEntidades().get(0) != null) {
					req.getSession().setAttribute("cliente", resultado.getEntidades().get(0));
					d = req.getRequestDispatcher("selecionarendereco.jsp");
				}
				else {
					resultado.setMsg("Cliente não encontrado!");
					req.setAttribute("resultado", resultado.getMsg());
					d = req.getRequestDispatcher("telalogin.jsp");
				}
					
					
				
			}
			
		}
		else {
			if(operacao.equals("SALVAR")) {
				req.setAttribute("resultado", resultado.getMsg());
				d = req.getRequestDispatcher("FormCliente.jsp");
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

package br.edu.fatec.celular.vh;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.celular.dominio.Cliente;
import br.edu.fatec.celular.dominio.Endereco;
import br.edu.fatec.celular.dominio.Cartao;
import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.util.Resultado;

public class CartaoVh implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest req) {
		String operacao = req.getParameter("operacao");
		Cartao cartao = new Cartao();
		Cliente cliente = new Cliente();
		
		
		if(operacao != null && operacao.equals("CONSULTAR")) {
			cliente = (Cliente) req.getSession().getAttribute("cliente");
			
			if(cliente != null) {
				cartao.setCliente(cliente);
			}
			
			if(req.getParameter("codigo") != null) {
				Integer id = Integer.valueOf(req.getParameter("codigo"));
				System.out.println(id);
				cartao.setId(id);
			}
		}
		
		if(operacao != null && operacao.equals("SALVAR")) {
			Calendar calendar = Calendar.getInstance();
			Date dtCadastro = calendar.getTime();

			String descricao = req.getParameter("txtDescricao");
			String numero = req.getParameter("txtNumeroCartao");
			String mes = req.getParameter("txtMes");
			String ano = req.getParameter("txtAno");
			String codigo =  req.getParameter("txtCodigo");
			String nomeTitular = req.getParameter("txtNomeTitular");
			String cpfTitular = req.getParameter("txtCpfTitular");

			cliente = (Cliente) req.getSession().getAttribute("cliente");

			cartao.setDtCadastro(dtCadastro);

			if (descricao != null && !descricao.isEmpty()) {
			    cartao.setDescricao(descricao.toUpperCase());
			} else {
			    cartao.setDescricao("");
			}

			// Check and set the numero
			if (numero != null && !numero.isEmpty()) {
			    cartao.setNumero(numero.toUpperCase());
			} else {
			    cartao.setNumero("");
			}

			// Check and set the mes
			if (mes != null && !mes.isEmpty()) {
			    cartao.setMes(mes);
			} else {
			    cartao.setMes("");
			}

			// Check and set the ano
			if (ano != null && !ano.isEmpty()) {
			    cartao.setAno(ano);
			} else {
			    cartao.setAno("");
			}

			// Check and set the nomeTitular
			if (nomeTitular != null && !nomeTitular.isEmpty()) {
			    cartao.setNomeTitular(nomeTitular);
			} else {
			    cartao.setNomeTitular("");
			}

			// Check and set the cpfTitular
			if (cpfTitular != null && !cpfTitular.isEmpty()) {
			    cartao.setCpfTitular(cpfTitular);
			} else {
			    cartao.setCpfTitular("");
			}
			
			if (codigo != null && !codigo.isEmpty()) {
			    cartao.setCodigo(codigo);
			} else {
			    cartao.setCodigo("");
			}

			cartao.setCliente(cliente);
		}
		
		else if(operacao != null && operacao.equals("EXCLUIR")) {
			if(req.getParameter("codigo") != null && !req.getParameter("codigo").equals("")) {
				Integer id = Integer.valueOf(req.getParameter("codigo"));
				cartao.setId(id);
			}
			
		}
		
		return cartao;
	}

	@Override
	public void setEntidade(HttpServletRequest req, HttpServletResponse resp, Resultado resultado)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		RequestDispatcher d = null;

		String operacao = req.getParameter("operacao");

		if (resultado.getMsg() == null) {
			if (operacao.equals("SALVAR")) {
				resultado.setMsg("Cartão cadastrado com sucesso!");
				req.setAttribute("resultado", resultado.getMsg());
				
				Cliente cli = (Cliente) req.getSession().getAttribute("cliente");
				cli.getCartoes().add((Cartao) resultado.getEntidades().get(0));
				
				req.getSession().setAttribute("cliente", cli);
				
				d = req.getRequestDispatcher((String) req.getSession().getAttribute("pagina"));
			}
			if (operacao.equals("CONSULTAR")) {
				//System.out.println(resultado.getEntidades());
				if(!resultado.getEntidades().isEmpty() && resultado.getEntidades().get(0) != null) {
					req.setAttribute("cartoes", resultado.getEntidades());
					d = req.getRequestDispatcher(req.getParameter("pagina"));
				}
				else {
					resultado.setMsg("Cartão não encontrado!");
					req.setAttribute("resultado", resultado.getMsg());
					d = req.getRequestDispatcher(req.getParameter("pagina"));
				}
					
					
				
			}
			
			if(operacao.equals("EXCLUIR")) {
				resultado.setMsg("Cartão excluído com sucesso!");
			    d = req.getRequestDispatcher("redirecionarsessao.jsp");
			    req.setAttribute("resultado", resultado.getMsg());
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
					resultado.setMsg("Cartão não encontrado!");
					req.setAttribute("resultado", resultado.getMsg());
					d = req.getRequestDispatcher("telalogin.jsp");
				}
				
			}
			
		}
		else {
			if(operacao.equals("SALVAR")) {
				req.setAttribute("resultado", resultado.getMsg());
				req.setAttribute("cartoes", resultado.getEntidades());
				d = req.getRequestDispatcher("FormCartao.jsp");
			}
			if(operacao.equals("ALTERAR")) {
				req.setAttribute("resultado", resultado.getMsg());
				req.setAttribute("cartoes", resultado.getEntidades());
				d = req.getRequestDispatcher("FormCartao.jsp");
			}
			if(operacao.equals("EXCLUIR")) {
				req.setAttribute("resultado", resultado.getMsg());
				req.setAttribute("cartoes", resultado.getEntidades());
				d = req.getRequestDispatcher((String) req.getSession().getAttribute("pagina"));
			}
		}
		
		try {
			d.forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

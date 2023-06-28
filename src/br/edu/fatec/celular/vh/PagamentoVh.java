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
import br.edu.fatec.celular.dominio.Pagamento;
import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.enun.TipoResidencia;
import br.edu.fatec.celular.util.Resultado;

public class PagamentoVh implements IViewHelper{
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest req) {
		// TODO Auto-generated method stub
		Calendar calendar;
		calendar = Calendar.getInstance();
		
		Pagamento pagamento = new Pagamento();
		pagamento.setDtCadastro(calendar.getTime());
		pagamento.setDtAlteracao(calendar.getTime());
		pagamento.getPedido().setId(Integer.valueOf((Integer) req.getSession().getAttribute("pedidoid")));
        
        // Obtém os valores dos parâmetros da requisição
		pagamento.getCartao().setId(req.getParameter("txtId") != null && !req.getParameter("txtId").equals("") ? Integer.parseInt(req.getParameter("txtId")) : null);
		pagamento.setQtdeParcelas(req.getParameter("txtParcelas") != null && !req.getParameter("txtParcelas").equals("") ? Integer.parseInt(req.getParameter("txtParcelas")) : null);
        pagamento.setVlTotal(req.getParameter("txtValorTotal") != null && !req.getParameter("txtValorTotal").equals("") ? Double.parseDouble(req.getParameter("txtValorTotal")) : null);
		pagamento.setVlParcela( pagamento.getVlTotal() / pagamento.getQtdeParcelas());
        return pagamento;
	}

	@Override
	public void setEntidade(HttpServletRequest req, HttpServletResponse resp, Resultado resultado)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		RequestDispatcher d = null;

		String operacao = req.getParameter("operacao");

		if (resultado.getMsg() == null) {
			if (operacao.equals("SALVAR")) {
				resultado.setMsg("Pagamento cadastrado com sucesso!");
				req.setAttribute("pagamentos", resultado.getEntidades());
				req.setAttribute("resultado", resultado.getMsg());
				
				d = req.getRequestDispatcher("redirecionarpedido.jsp");
			}
			if (operacao.equals("CONSULTAR")) {
				//System.out.println(resultado.getEntidades());
				if(!resultado.getEntidades().isEmpty() && resultado.getEntidades().get(0) != null) {
					req.setAttribute("pagamentos", resultado.getEntidades());
					d = req.getRequestDispatcher(req.getParameter("pagina"));
				}
				else {
					resultado.setMsg("Pagamento não encontrado!");
					req.setAttribute("resultado", resultado.getMsg());
					d = req.getRequestDispatcher(req.getParameter("pagina"));
				}
					
					
				
			}
			
			if (operacao.equals("ALTERAR")) {
				//System.out.println(resultado.getEntidades());
				if(!resultado.getEntidades().isEmpty() && resultado.getEntidades().get(0) != null) {
					
					d = req.getRequestDispatcher((String) req.getSession().getAttribute("pagina"));
				}
				else {
					resultado.setMsg("Pagamento não encontrado!");
					req.setAttribute("resultado", resultado.getMsg());
					d = req.getRequestDispatcher("telalogin.jsp");
				}
				
			}
			
			if(operacao.equals("EXCLUIR")) {
				resultado.setMsg("Pagamento excluído com sucesso!");
			    d = req.getRequestDispatcher("redirecionarpedido.jsp");
			    req.setAttribute("resultado", resultado.getMsg());
			}
		}
		else {
			if(operacao.equals("SALVAR")) {
				req.setAttribute("resultado", resultado.getMsg());
				req.setAttribute("pagamentos", resultado.getEntidades());
				d = req.getRequestDispatcher("cartaoparcela.jsp");
			}
			if(operacao.equals("ALTERAR")) {
				req.setAttribute("resultado", resultado.getMsg());
				req.setAttribute("pagamentos", resultado.getEntidades());
				d = req.getRequestDispatcher("cartaoparcela.jsp");
			}
			if(operacao.equals("EXCLUIR")) {
				req.setAttribute("resultado", resultado.getMsg());
				req.setAttribute("pagamentos", resultado.getEntidades());
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

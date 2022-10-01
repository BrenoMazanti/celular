package br.edu.fatec.celular.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.celular.command.AlterarCommand;
import br.edu.fatec.celular.command.ConsultarCommand;
import br.edu.fatec.celular.command.ExcluirCommand;
import br.edu.fatec.celular.command.ICommand;
import br.edu.fatec.celular.command.ListarCommand;
import br.edu.fatec.celular.command.LoginCommand;
import br.edu.fatec.celular.command.SalvarCommand;
import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.util.Resultado;
import br.edu.fatec.celular.vh.IViewHelper;
import br.edu.fatec.celular.vh.AdministradorVh;
import br.edu.fatec.celular.vh.CartaoVh;
import br.edu.fatec.celular.vh.ClienteVh;
import br.edu.fatec.celular.vh.EnderecoVh;
import br.edu.fatec.celular.vh.CelularVh;
import br.edu.fatec.celular.vh.CarrinhoVh;
import br.edu.fatec.celular.vh.CarrinhoiVh;
import br.edu.fatec.celular.vh.PedidoVh;
import br.edu.fatec.celular.vh.PedidoiVh;

/**
 * Servlet implementation class Servlet
 */
@WebServlet(urlPatterns = {"/Cliente"
                         , "/Endereco"
                         , "/Cartao"
                         , "/Administrador"
                         , "/Celular"
                         , "/Carrinho"
                         , "/Carrinhoi"
                         , "/Pedido"
                         , "/Pedidoi"})
public class Servlet extends HttpServlet {	
	private static final long serialVersionUID = 1L;
	private Map <String, IViewHelper> mapavh;
	private Map <String, ICommand> commands;

    /**
     * Default constructor. 
     */
    public Servlet() {
        // TODO Auto-generated constructor stub
    	commands = new HashMap<String, ICommand>();
    	commands.put("SALVAR", new SalvarCommand());
    	commands.put("CONSULTAR", new ConsultarCommand());
    	commands.put("ALTERAR", new AlterarCommand());
    	commands.put("EXCLUIR", new ExcluirCommand());
    	commands.put("LISTAR", new ListarCommand());
    	commands.put("LOGIN", new LoginCommand());
    	
    	mapavh = new HashMap <String, IViewHelper>();
    	mapavh.put("/celular/Cliente", new ClienteVh());
    	mapavh.put("/celular/Endereco", new EnderecoVh());
    	mapavh.put("/celular/Cartao", new CartaoVh());
    	mapavh.put("/celular/Administrador", new AdministradorVh());
    	mapavh.put("/celular/Celular", new CelularVh());
    	mapavh.put("/celular/Carrinho", new CarrinhoVh());
    	mapavh.put("/celular/Carrinhoi", new CarrinhoiVh());
    	mapavh.put("/celular/Pedido", new PedidoVh());
    	mapavh.put("/celular/Pedidoi", new PedidoiVh());
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		executar(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		executar(req, resp);
	}
	
	public void executar(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String teste = req.getRequestURI();
		System.out.println(teste);
		
		IViewHelper ivh = mapavh.get(req.getRequestURI());
		EntidadeDominio dominio = ivh.getEntidade(req);
		String operacao = req.getParameter("operacao");
		ICommand cmd = commands.get(operacao);
		Resultado resultado = cmd.execute(dominio);
		
		ivh.setEntidade(req, resp, resultado);
	}

}

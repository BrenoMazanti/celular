package br.edu.fatec.celular.vh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.util.Resultado;

public interface IViewHelper {
	public EntidadeDominio getEntidade(HttpServletRequest req);
	public void setEntidade(HttpServletRequest req, HttpServletResponse resp, Resultado resultado) throws IOException, ServletException;;
}

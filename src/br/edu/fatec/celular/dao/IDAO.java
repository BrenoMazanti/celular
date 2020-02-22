package br.edu.fatec.celular.dao;

import java.sql.SQLException;
import java.util.List;

import br.edu.fatec.celular.dominio.EntidadeDominio;

public interface IDAO {
	public void salvar(EntidadeDominio entidade);
	public void alterar(EntidadeDominio entidade);
	public void excluir(EntidadeDominio entidade);
	public List<EntidadeDominio> consultar(EntidadeDominio entidade)throws SQLException;
	public List<EntidadeDominio> listar(EntidadeDominio entidade)throws SQLException;
	//alterar excluir consultar
}

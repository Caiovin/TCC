package br.com.sge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sge.bean.UsuarioBean;
import br.com.sge.exception.DaoException;
import br.com.sge.util.DbUtil;

public class UsuarioDao {
    
    private static final String EXCLUIR_USUARIO = 
			"delete from TB_USUARIO where NM_USUARIO = ?";

	private static final String INSERIR_USUARIO =
			"insert into TB_USUARIO (NOME_COMPLETO, NM_USUARIO, SENHA_USUARIO, EMAIL_USUARIO) "+
            "values (?,?,?,?)";

	private static final String ATUALIZAR_USUARIO =
			"update TB_USUARIO set " +
            "NOME_COMPLETO = ?, " +
            "NM_USUARIO = ?," +
            "SENHA_USUARIO = ?," +
			"EMAIL_USUARIO = ?, ";
	
	private static final  String CONSULTA_USUARIO =
			"select * from TB_USUARIO order by NM_USUARIO asc";

	private static final  String CONSULTA_USUARIO_NOME =
			"select * from TB_USUARIO where NOME_COMPLETO like ? order by NOME_COMPLETO";

	private static final  String CONSULTA_USUARIO_NMUS = 
			"select * from TB_USUARIO where NM_USUARIO = ?";

	public List<UsuarioBean> consultarUsuario(String nomeCompleto) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<UsuarioBean> listaUsuario = new ArrayList<UsuarioBean>();
		try {
			if(nomeCompleto.equals("")){
				statement = conn.prepareStatement(CONSULTA_USUARIO);
			}else{
				statement = conn.prepareStatement(CONSULTA_USUARIO_NOME);
				statement.setString(1, "%"+nomeCompleto+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				UsuarioBean objUsua = new UsuarioBean();
				objUsua.setNomeCompleto(result.getString(1));
				objUsua.setNmUsuario(result.getString(2));
                objUsua.setSenhaUsuario(result.getString(3));
				objUsua.setEmailUsuario(result.getString(4));
                listaUsuario.add(objUsua);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaUsuario;
	}

	public UsuarioBean consultarUsuarioNMUS(String nmUsuario) throws DaoException{		
		UsuarioBean objUsua = new UsuarioBean();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_USUARIO_NMUS);
			statement.setString(1, nmUsuario);
			result = statement.executeQuery();
			while (result.next()) {
				objUsua.setNomeCompleto(result.getString(1));
				objUsua.setNmUsuario(result.getString(2));
                objUsua.setSenhaUsuario(result.getString(3));
				objUsua.setEmailUsuario(result.getString(4));
                
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objUsua;
	}

	public boolean inserirUsuario(UsuarioBean obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_USUARIO);
			statement.setString(1, obj.getNomeCompleto());
			statement.setString(2, obj.getNmUsuario());
			statement.setString(3, obj.getSenhaUsuario());
			statement.setString(4, obj.getEmailUsuario());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;
	}

	public boolean atualizarUsuario(UsuarioBean objUsua) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_USUARIO);
			statement.setString(1, objUsua.getNomeCompleto());
			statement.setString(2, objUsua.getNmUsuario());
			statement.setString(3, objUsua.getSenhaUsuario());
			statement.setString(4, objUsua.getEmailUsuario());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirUsuario(String nmUsuario) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_USUARIO);
			statement.setString(1, nmUsuario);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
}
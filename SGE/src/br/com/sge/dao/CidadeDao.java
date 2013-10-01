package br.com.sge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sge.bean.CidadeBean;
import br.com.sge.exception.DaoException;
import br.com.sge.util.DbUtil;

public class CidadeDao {
    
    private static final String EXCLUIR_CIDADE = 
			"delete from TB_CIDADE where ID_CIDADE = ?";

	private static final String INSERIR_CIDADE =
			"insert into TB_CIDADE (CIDADE, UF)"+
			"values (?,?)";

	private static final String ATUALIZAR_CIDADE =
			"update TB_CIDADE set " +
            "CIDADE = ?, " +
            "UF = ? ";
	
	private static final  String CONSULTA_CIDADE =
			"select * from TB_CIDADE order by CIDADE asc";

	private static final  String CONSULTA_CIDADE_NOME =
			"select * from TB_CIDADE where CIDADE like ? order by CIDADE asc";

	private static final  String CONSULTA_CIDADE_ID = 
			"select * from TB_CIDADE where ID_CIDADE = ?";


	public List<CidadeBean> consultarCidade(String cidade) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<CidadeBean> listaCidade = new ArrayList<CidadeBean>();
		try {
			if(cidade.equals("")){
				statement = conn.prepareStatement(CONSULTA_CIDADE);
			}else{
				statement = conn.prepareStatement(CONSULTA_CIDADE_NOME);
				statement.setString(1, "%"+cidade+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				CidadeBean objAlun = new CidadeBean();
				objAlun.setIdCidade(result.getInt(1));
				objAlun.setCidade(result.getString(2));
                objAlun.setUf(result.getString(3));
				listaCidade.add(objAlun);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaCidade;
	}

	public CidadeBean consultarCidadeId(int idCidade) throws DaoException{		
		CidadeBean objCidd = new CidadeBean();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_CIDADE_ID);
			statement.setInt(1, idCidade);
			result = statement.executeQuery();
			while (result.next()) {
				objCidd.setIdCidade(result.getInt(1));
				objCidd.setCidade(result.getString(2));
				objCidd.setUf(result.getString(3));
				
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objCidd;		
	}

	public boolean inserirCidade(CidadeBean obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_CIDADE);
			statement.setString(1, obj.getCidade());
			statement.setString(2, obj.getUf());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;
	}

	public boolean atualizarCidade(CidadeBean objCidd) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_CIDADE);
			statement.setString(1, objCidd.getCidade());
			statement.setString(2, objCidd.getUf());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirCidade(int idCidade) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_CIDADE);
			statement.setInt(1, idCidade);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
}
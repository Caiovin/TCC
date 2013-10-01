package br.com.sge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sge.bean.BairroBean;
import br.com.sge.exception.DaoException;
import br.com.sge.util.DbUtil;

public class BairroDao {
    
    private static final String EXCLUIR_BAIRRO = 
			"delete from TB_BAIRRO where ID_BAIRRO = ?";

	private static final String INSERIR_BAIRRO =
			"insert into TB_BAIRRO (ID_CIDADE, BAIRRO)"+
			"values (?,?)";

	private static final String ATUALIZAR_BAIRRO =
			"update TB_ALUNO set " +
            "ID_CIDADE = ?, " +
            "BAIRRO = ?,";
	
	private static final  String CONSULTA_BAIRRO =
			"select * from TB_BAIRRO order by BAIRRO asc";

	private static final  String CONSULTA_BAIRRO_NOME =
			"select * from TB_BAIRRO where BAIRRO like ? order by BAIRRO asc";

	private static final  String CONSULTA_BAIRRO_ID = 
			"select * from TB_BAIRRO where ID_BAIRRO = ?";


	public List<BairroBean> consultarBairro(String bairro) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<BairroBean> listaBairro = new ArrayList<BairroBean>();
		try {
			if(bairro.equals("")){
				statement = conn.prepareStatement(CONSULTA_BAIRRO);
			}else{
				statement = conn.prepareStatement(CONSULTA_BAIRRO_NOME);
				statement.setString(1, "%"+bairro+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				BairroBean objBair = new BairroBean();
				objBair.setIdBairro(result.getInt(1));
				objBair.setIdCidade(result.getInt(2));
                objBair.setBairro(result.getString(3));
				listaBairro.add(objBair);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaBairro;
	}

	public BairroBean consultarBairroId(int idBairro) throws DaoException{		
		BairroBean objBair = new BairroBean();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_BAIRRO_ID);
			statement.setInt(1, idBairro);
			result = statement.executeQuery();
			while (result.next()) {
				objBair.setIdBairro(result.getInt(1));
				objBair.setIdCidade(result.getInt(2));
                objBair.setBairro(result.getString(3));
				
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objBair;
	}

	public boolean inserirBairro(BairroBean obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_BAIRRO);
			statement.setInt(1, obj.getIdCidade());
			statement.setString(2, obj.getBairro());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean atualizarBairro(BairroBean objBair) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_BAIRRO);
			statement.setInt(1, objBair.getIdCidade());
			statement.setString(2, objBair.getBairro());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirBairro(int idBairro) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_BAIRRO);
			statement.setInt(1, idBairro);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
}
package br.com.sge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sge.bean.EnderecoBean;
import br.com.sge.exception.DaoException;
import br.com.sge.util.DbUtil;

public class EnderecoDao {
    
    private static final String EXCLUIR_ENDERECO = 
			"delete from TB_ENDERECO where CEP = ?";

	private static final String INSERIR_ENDERECO =
			"insert into TB_ENDERECO (CEP, ENDERECO, ID_CIDADE, ID_BAIRRO)"+
            "values (?,?,?,?)";

	private static final String ATUALIZAR_ENDERECO =
			"update TB_ENDERECO set " +
            "CEP = ?, " +
            "ENDERECO = ?," +
            "ID_CIDADE = ?," +
			"ID_BAIRRO = ?, ";
	
	private static final  String CONSULTA_ENDERECO =
			"select * from TB_ENDERECO order by ENDERECO asc";

	private static final  String CONSULTA_ENDERECO_NOME =
			"select * from TB_ENDERECO where ENDERECO like ? order by ENDERECO asc";

	private static final  String CONSULTA_ENDERECO_CEP = 
			"select * from TB_ENDERECO where CEP = ?";

	public List<EnderecoBean> consultarEndereco(String endereco) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<EnderecoBean> listaEndereco = new ArrayList<EnderecoBean>();
		try {
			if(endereco.equals("")){
				statement = conn.prepareStatement(CONSULTA_ENDERECO);
			}else{
				statement = conn.prepareStatement(CONSULTA_ENDERECO_NOME);
				statement.setString(1, "%"+endereco+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				EnderecoBean objEnd = new EnderecoBean();
				objEnd.setCep(result.getString(1));
				objEnd.setEndereco(result.getString(2));
                objEnd.setIdCidade(result.getInt(3));
				objEnd.setIdBairro(result.getInt(4));
                listaEndereco.add(objEnd);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaEndereco;
	}

	public EnderecoBean consultarEnderecoCep(String cep) throws DaoException{		
		EnderecoBean objEnd = new EnderecoBean();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_ENDERECO_CEP);
			statement.setString(1, cep);
			result = statement.executeQuery();
			while (result.next()) {
				objEnd.setCep(result.getString(1));
				objEnd.setEndereco(result.getString(2));
                objEnd.setIdCidade(result.getInt(3));
				objEnd.setIdBairro(result.getInt(4));
                
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objEnd;
	}

	public boolean inserirEndereco(EnderecoBean obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_ENDERECO);
			statement.setString(1, obj.getCep());
			statement.setString(2, obj.getEndereco());
			statement.setInt(3, obj.getIdCidade());
			statement.setInt(4, obj.getIdBairro());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean atualizarEndereco(EnderecoBean objEnd) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_ENDERECO);
			statement.setString(1, objEnd.getCep());
			statement.setString(2, objEnd.getEndereco());
			statement.setInt(3, objEnd.getIdCidade());
			statement.setInt(4, objEnd.getIdBairro());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirEndereco(String cep) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_ENDERECO);
			statement.setString(1, cep);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
}
package br.com.sge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sge.bean.Ocorrencia;
import br.com.sge.exception.DaoException;
import br.com.sge.util.DbUtil;

public class OcorrenciaDao {
    
    private static final String EXCLUIR_OCORRENCIA = 
			"delete from TB_OCORRENCIA where COD_OCORRENCIA = ?";

	private static final String INSERIR_OCORRENCIA =
			"insert into TB_OCORRENCIA (COD_OCORRENCIA, RM_ALUNO, DESC_OCORRENCIA, DT_OCORRENCIA) "+
            "values (?,?,?,?)";

	private static final String ATUALIZAR_OCORRENCIA =
			"update TB_OCORRENCIA set " +
            "COD_OCORRENCIA = ?, " +
            "RM_ALUNO = ?," +
            "NM_ALUNO = ?," +
			"DESC_OCORRENCIA = ?, " +
			"DT_OCORRENCIA = ?, ";

	
	private static final  String CONSULTA_OCORRENCIA =
			"select * from TB_OCORRENCIA order by NM_ALUNO asc";

	private static final  String CONSULTA_OCORRENCIA_NOME =
			"select * from TB_OCORRENCIA where NM_ALUNO like ? order by NM_ALUNO";

	private static final  String CONSULTA_OCORRENCIA_COD = 
			"select * from TB_OCORRENCIA where COD_OCORRENCIA = ?";
	
	private static final String QUERY_SEQUENCIA_ID = 
			"select isnull(max(COD_OCORRENCIA), 0) + 1 AS NOVO_ID from TB_OCORRENCIA";
	
	
	private Ocorrencia getBean(ResultSet result) throws DaoException, SQLException{
		Ocorrencia ocorrencia = new Ocorrencia();
		
		ocorrencia.setCodOcorrencia(result.getInt("COD_OCORRENCIA"));
		ocorrencia.setDescOcorrencia(result.getString("DESC_OCORRENCIA"));
		ocorrencia.setDtOcorrencia(DbUtil.getJavaDate(result, "DT_OCORRENCIA"));
		ocorrencia.setRmAluno(result.getInt("RM_ALUNO"));
		
		return ocorrencia;
	}
	
	private int getSequenciaId() throws DaoException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		int retorno = 0;
		try{
			statement = conn.prepareStatement(QUERY_SEQUENCIA_ID);
			result = statement.executeQuery();
			if(result.next()){
				retorno = result.getInt("NOVO_ID");
			}
		}catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return retorno;
	}

	public List<Ocorrencia> consultarOcorrencia(String nmAluno) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Ocorrencia> listaOcorrencia = new ArrayList<Ocorrencia>();
		try {
			if(nmAluno.equals("")){
				statement = conn.prepareStatement(CONSULTA_OCORRENCIA);
			}else{
				statement = conn.prepareStatement(CONSULTA_OCORRENCIA_NOME);
				statement.setString(1, "%"+nmAluno+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				Ocorrencia objOcor = getBean(result);
				listaOcorrencia.add(objOcor);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaOcorrencia;
	}

	public Ocorrencia consultarOcorrenciaCod(int codOcorrencia) throws DaoException{		
		Ocorrencia objOcor = new Ocorrencia();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_OCORRENCIA_COD);
			statement.setInt(1, codOcorrencia);
			result = statement.executeQuery();
			while (result.next()) {
				objOcor = getBean(result);
				
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objOcor;
	}

	public boolean inserirOcorrencia(Ocorrencia obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_OCORRENCIA);
			statement.setInt(1, getSequenciaId());
			statement.setInt(2, obj.getRmAluno());
			statement.setString(3, obj.getDescOcorrencia());
			statement.setDate(4, DbUtil.getSqlDate(obj.getDtOcorrencia()));
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean atualizarOcorrencia(Ocorrencia objOcor) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_OCORRENCIA);
			statement.setInt(1, objOcor.getRmAluno());
			statement.setString(3, objOcor.getDescOcorrencia());
			statement.setDate(4, DbUtil.getSqlDate(objOcor.getDtOcorrencia()));
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirOcorrencia(int codOcorrencia) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_OCORRENCIA);
			statement.setInt(1, codOcorrencia);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
}
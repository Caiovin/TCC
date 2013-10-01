package br.com.sge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sge.bean.OcorrenciaBean;
import br.com.sge.exception.DaoException;
import br.com.sge.util.DbUtil;

public class OcorrenciaDao {
    
    private static final String EXCLUIR_OCORRENCIA = 
			"delete from TB_OCORRENCIA where COD_OCORRENCIA = ?";

	private static final String INSERIR_OCORRENCIA =
			"insert into TB_OCORRENCIA (COD_OCORRENCIA, RM_ALUNO, NM_ALUNO, DESC_OCORRENCIA, DT_OCORRENCIA) "+
            "values (?,?,?,?,?)";

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

	public List<OcorrenciaBean> consultarOcorrencia(String nmAluno) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<OcorrenciaBean> listaOcorrencia = new ArrayList<OcorrenciaBean>();
		try {
			if(nmAluno.equals("")){
				statement = conn.prepareStatement(CONSULTA_OCORRENCIA);
			}else{
				statement = conn.prepareStatement(CONSULTA_OCORRENCIA_NOME);
				statement.setString(1, "%"+nmAluno+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				OcorrenciaBean objOcor = new OcorrenciaBean();
				objOcor.setCodOcorrencia(result.getInt(1));
				objOcor.setRmAluno(result.getInt(2));
                objOcor.setNmAluno(result.getString(3));
				objOcor.setDescOcorrencia(result.getString(4));
                objOcor.setDtOcorrencia(result.getString(5));
				listaOcorrencia.add(objOcor);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaOcorrencia;
	}

	public OcorrenciaBean consultarOcorrenciaCod(int codOcorrencia) throws DaoException{		
		OcorrenciaBean objOcor = new OcorrenciaBean();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_OCORRENCIA_COD);
			statement.setInt(1, codOcorrencia);
			result = statement.executeQuery();
			while (result.next()) {
				objOcor.setCodOcorrencia(result.getInt(1));
				objOcor.setRmAluno(result.getInt(2));
                objOcor.setNmAluno(result.getString(3));
				objOcor.setDescOcorrencia(result.getString(4));
                objOcor.setDtOcorrencia(result.getString(5));
				
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objOcor;
	}

	public boolean inserirOcorrencia(OcorrenciaBean obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_OCORRENCIA);
			statement.setInt(1, obj.getRmAluno());
			statement.setString(2, obj.getNmAluno());
			statement.setString(3, obj.getDescOcorrencia());
			statement.setString(4, obj.getDtOcorrencia());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean atualizarOcorrencia(OcorrenciaBean objOcor) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_OCORRENCIA);
			statement.setInt(1, objOcor.getRmAluno());
			statement.setString(2, objOcor.getNmAluno());
			statement.setString(3, objOcor.getDescOcorrencia());
			statement.setString(4, objOcor.getDtOcorrencia());
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
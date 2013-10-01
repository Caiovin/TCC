package br.com.sge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sge.bean.FrequenciaBean;
import br.com.sge.exception.DaoException;
import br.com.sge.util.DbUtil;

public class FrequenciaDao {
    
    private static final String EXCLUIR_FREQUENCIA = 
			"delete from TB_FREQUENCIA where COD_AULA = ?";

	private static final String INSERIR_FREQUENCIA =
			"insert into TB_ALUNO(ALUNO_PRESENTE, OBS) "+
			"values (?,?)";

	private static final String ATUALIZAR_FREQUENCIA =
			"update TB_FREQUENCIA set " +
            "ALUNO_PRESENTE = ?, " +
			"OBS = ?, ";

	
	private static final  String CONSULTA_FREQUENCIA =
			"select * from TB_FREQUENCIA order by COD_AULA asc";

	private static final  String CONSULTA_FREQUENCIA_ALUNO_PRESENTE =
			"select * from TB_FREQUENCIA where ALUNO_PRESENTE like ? order by COD_AULA";

	private static final  String CONSULTA_FREQUENCIA_COD_AULA = 
			"select * from TB_FREQUENCIA where COD_AULA = ?";


	public List<FrequenciaBean> consultarFrequencia(String AlunoPresente) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<FrequenciaBean> listaFreq = new ArrayList<FrequenciaBean>();
		try {
			if(AlunoPresente.equals("")){
				statement = conn.prepareStatement(CONSULTA_FREQUENCIA);
			}else{
				statement = conn.prepareStatement(CONSULTA_FREQUENCIA_ALUNO_PRESENTE);
				statement.setString(1, "%"+AlunoPresente+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				FrequenciaBean objFreq = new FrequenciaBean();
				objFreq.setCodAula(result.getInt(1));
				objFreq.setAlunoPresente(result.getString(2));
                objFreq.setObs(result.getString(3));
				listaFreq.add(objFreq);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaFreq;		
	}

	public FrequenciaBean consultarFrequenciaCodAula(int codAula) throws DaoException{		
		FrequenciaBean objFreq = new FrequenciaBean();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_FREQUENCIA_COD_AULA);
			statement.setInt(1, codAula);
			result = statement.executeQuery();
			while (result.next()) {
				objFreq.setCodAula(result.getInt(1));
				objFreq.setAlunoPresente(result.getString(2));
                objFreq.setObs(result.getString(3));
				}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objFreq;		
	}

	public boolean inserirFrequencia(FrequenciaBean obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_FREQUENCIA);
			statement.setString(1, obj.getAlunoPresente());
            statement.setString(2, obj.getObs());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean atualizarFrequencia(FrequenciaBean objFreq) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_FREQUENCIA);
			statement.setString(1, objFreq.getAlunoPresente());
            statement.setString(2, objFreq.getObs());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirFrequencia(int CodAula) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_FREQUENCIA);
			statement.setInt(1, CodAula);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
}
package br.com.sge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sge.bean.AtividadeBean;
import br.com.sge.exception.DaoException;
import br.com.sge.util.DbUtil;

public class AtividadeDao {
    
    private static final String EXCLUIR_ATIVIDADE = 
			"delete from TB_ATIVIDADE where COD_ATIVIDADE = ?";

	private static final String INSERIR_ATIVIDADE =
			"insert into TB_ATIVIDADE(COD_TURMA, COD_PROFESSOR, DESC_ATIVIDADE, TIPO_ATIVIDADE, DT_ATIVIDADE, DT_ENTREGA, VALOR_ATIVIDADE "+
			"values (?,?,?,?,?,?,?)";

	private static final String ATUALIZAR_ATIVIDADE =
			"update TB_ATIVIDADE set " +
            "COD_TURMA = ?, " +
            "COD_PROFESSOR = ?," +
            "DESC_ATIVIDADE = ?," +
			"TIPO_ATIVIDADE= ?, " +
			"DT_ATIVIDADE = ?, " +
			"DT_ENTREGA = ?, " +
            "VALOR_ATIVIDADE = ?, ";

	
	private static final  String CONSULTA_ATIVIDADE =
			"select * from TB_ATIVIDADE order by COD_TURMA asc";

	private static final  String CONSULTA_ATIVIDADE_TIPO =
			"select * from TB_ATIVIDADE where COD_PROFESSOR like ? order by COD_PROFESSOR";

	private static final  String CONSULTA_ATIVIDADE_COD = 
			"select * from TB_ATIVIDADE where COD_ATIVIDADE = ?";
	
		public List<AtividadeBean> consultarAtividade(String tipoAtividade) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<AtividadeBean> listaAtiv = new ArrayList<AtividadeBean>();
		try {
			if(tipoAtividade.equals("")){
				statement = conn.prepareStatement(CONSULTA_ATIVIDADE);
			}else{
				statement = conn.prepareStatement(CONSULTA_ATIVIDADE_TIPO);
				statement.setString(1, "%"+tipoAtividade+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				AtividadeBean objAtiv = new AtividadeBean();
				objAtiv.setCodAtividade(result.getInt(1));
				objAtiv.setCodTurma(result.getInt(2));
				objAtiv.setCodProfessor(result.getInt(3));
				objAtiv.setDescAtividade(result.getString(4));
				objAtiv.setDtAtividade(result.getString(5));
				objAtiv.setDtEntrega(result.getString(6));
				objAtiv.setTipoAtividade(result.getString(7));
				objAtiv.setValorAtividade(result.getFloat(8));
				listaAtiv.add(objAtiv);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaAtiv;		
	}

	public AtividadeBean consultaAtividadeCod(int codAtividade) throws DaoException{		
		AtividadeBean objAtiv = new AtividadeBean();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_ATIVIDADE_COD);
			statement.setInt(1, codAtividade);
			result = statement.executeQuery();
			while (result.next()) {
				objAtiv.setCodAtividade(result.getInt(1));
				objAtiv.setCodTurma(result.getInt(2));
                objAtiv.setCodProfessor(result.getInt(3));
				objAtiv.setDescAtividade(result.getString(4));
                objAtiv.setTipoAtividade(result.getString(5));
				objAtiv.setDtAtividade(result.getString(6));
				objAtiv.setDtEntrega(result.getString(7));
				objAtiv.setValorAtividade(result.getFloat(8));
				
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objAtiv;
	}

	public boolean inserirAtividade(AtividadeBean obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_ATIVIDADE);
			statement.setInt(1, obj.getCodTurma());
			statement.setInt(2, obj.getCodProfessor());
			statement.setString(3, obj.getDescAtividade());
			statement.setString(4, obj.getTipoAtividade());
			statement.setString(5, obj.getDtAtividade());
			statement.setString(6, obj.getDtEntrega());
			statement.setFloat(7, obj.getValorAtividade());
            statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean atualizarAluno(AtividadeBean objAlun) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_ATIVIDADE);
			statement.setInt(1, objAlun.getCodTurma());
			statement.setInt(2, objAlun.getCodProfessor());
			statement.setString(3, objAlun.getDescAtividade());
			statement.setString(4, objAlun.getTipoAtividade());
			statement.setString(5, objAlun.getDtAtividade());
			statement.setString(6, objAlun.getDtEntrega());
			statement.setFloat(7, objAlun.getValorAtividade());
            statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirAtividade(int codAtividade) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_ATIVIDADE);
			statement.setInt(1, codAtividade);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
}
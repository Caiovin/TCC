package br.com.sge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sge.bean.TurmaBean;
import br.com.sge.exception.DaoException;
import br.com.sge.util.DbUtil;

public class TurmaDao {
    
    private static final String EXCLUIR_TURMA = 
			"delete from TB_TURMA where COD_TURMA = ?";

	private static final String INSERIR_TURMA =
			"insert into TB_TURMA (NM_TURMA, PRD_TURMA)"+
			"values (?,?)";

	private static final String ATUALIZAR_TURMA =
			"update TB_TURMA set " +
			"NM_TURMA = ?, " +
            "PRD_TURMA = ?,";
	
	private static final  String CONSULTA_TURMA =
			"select * from TB_TURMA order by NM_TURMA";

	private static final  String CONSULTA_TURMA_NOME =
			"select * from TB_TURMA where NM_TURMA like ? order by NM_TURMA asc";

	private static final  String CONSULTA_TURMA_COD = 
			"select * from TB_TURMA where COD_TURMA = ?";


	public List<TurmaBean> consultarTurma(String nmTurma) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<TurmaBean> listaTurma = new ArrayList<TurmaBean>();
		try {
			if(nmTurma.equals("")){
				statement = conn.prepareStatement(CONSULTA_TURMA);
			}else{
				statement = conn.prepareStatement(CONSULTA_TURMA_NOME);
				statement.setString(1, "%"+nmTurma+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				TurmaBean objTurm = new TurmaBean();
				objTurm.setCodTurma(result.getInt(1));
				objTurm.setNmTurma(result.getString(2));
				objTurm.setPrdTurma(result.getString(3));
                listaTurma.add(objTurm);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaTurma;
	}

	public TurmaBean consultarTurmaCod(int codTurma) throws DaoException{		
		TurmaBean objTurm = new TurmaBean();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_TURMA_COD);
			statement.setInt(1, codTurma);
			result = statement.executeQuery();
			while (result.next()) {
				objTurm.setCodTurma(result.getInt(1));
				objTurm.setNmTurma(result.getString(2));
                objTurm.setPrdTurma(result.getString(3));         
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objTurm;
	}

	public boolean inserirTurma(TurmaBean obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_TURMA);
			statement.setInt(1, obj.getCodTurma());
            statement.setString(2,obj.getNmTurma());
            statement.setString(3, obj.getPrdTurma());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;
	}

	public boolean atualizarTurma(TurmaBean objTurm) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_TURMA);
			statement.setInt(1, objTurm.getCodTurma());
            statement.setString(2,objTurm.getNmTurma());
            statement.setString(3, objTurm.getPrdTurma());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;
	}

	public boolean excluirTurma(int codTurma) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_TURMA);
			statement.setInt(1, codTurma);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
}
package br.com.sge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sge.bean.ProfessorBean;
import br.com.sge.exception.DaoException;
import br.com.sge.util.DbUtil;

public class ProfessorDao {
    
    private static final String EXCLUIR_PROFESSOR = 
			"delete from TB_PROFESSOR where COD_PROFESSOR = ?";

	private static final String INSERIR_PROFESSOR =
			"insert into TB_PROFESSOR(NM_PROFESSOR, SEXO_PROFESSOR, RG_PROFESSOR, CPF_PROFESSOR "+
            "DT_NASC_PROFESSOR, TEL_PROFESSOR, CEL_PROFESSOR, END_PROFESSOR, EMAIL_PROFESSOR, PRD_PROFESSOR "+
            "values (?,?,?,?,?,?,?,?,?,?)";

	private static final String ATUALIZAR_PROFESSOR =
			"update TB_PROFESSOR set " +
			"NM_PROFESSOR = ?," +
            "SEXO_PROFESSOR = ?," +
			"RG_PROFESSOR = ?, " +
            "CPF_PROFESSOR = ?, " +
			"DT_NASC_PROFESSOR = ?, " +
			"TEL_PROFESSOR = ?, " +
            "CEL_PROFESSOR = ?, " +
            "END_PROFESSOR = ?, " +
            "EMAIL_PROFESSOR = ?, " +
			"PRD_PROFESSOR = ?, ";
            
	private static final  String CONSULTA_PROFESSOR =
			"select * from TB_PROFESSOR order by NM_PROFESSOR";

	private static final  String CONSULTA_PROFESSOR_NOME =
			"select * from TB_PROFESSOR where NM_PROFESSOR like ? order by NM_PROFESSOR";

	private static final  String CONSULTA_PROFESSOR_COD = 
			"select * from TB_PROFESSOR where COD_PROFESSOR = ?";


	public List<ProfessorBean> consultarProfessor(String NM_PROFESSOR) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<ProfessorBean> listaProf = new ArrayList<ProfessorBean>();
		try {
			if(NM_PROFESSOR.equals("")){
				statement = conn.prepareStatement(CONSULTA_PROFESSOR);
			}else{
				statement = conn.prepareStatement(CONSULTA_PROFESSOR_NOME);
				statement.setString(1, "%"+NM_PROFESSOR+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				ProfessorBean objFunc = new ProfessorBean();
				objFunc.setCodProfessor(result.getInt(1));
				objFunc.setNmProfessor(result.getString(2));
                objFunc.setSexoProfessor(result.getString(3));
				objFunc.setRgProfessor(result.getString(4));
                objFunc.setCpfProfessor(result.getString(5));
				objFunc.setDtNasProfessor(result.getString(6));
				objFunc.setTelProfessor(result.getString(7));
				objFunc.setCelProfessor(result.getString(8));
                objFunc.setEndProfessor(result.getString(9));
                objFunc.setEmaiProfessor(result.getString(10));
                objFunc.setPrdProfessor(result.getString(11));
                listaProf.add(objFunc);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaProf;		
	}

	public ProfessorBean consultarProfessorCod(int COD_PROFESSOR) throws DaoException{		
		ProfessorBean objProf = new ProfessorBean();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_PROFESSOR_COD);
			statement.setInt(1, COD_PROFESSOR);
			result = statement.executeQuery();
			while (result.next()) {
				objProf.setCodProfessor(result.getInt(1));
				objProf.setNmProfessor(result.getString(2));
				objProf.setSexoProfessor(result.getString(3));
				objProf.setRgProfessor(result.getString(4));
				objProf.setCpfProfessor(result.getString(5));
				objProf.setDtNasProfessor(result.getString(6));
				objProf.setTelProfessor(result.getString(7));
				objProf.setCelProfessor(result.getString(8));
				objProf.setEndProfessor(result.getString(9));
				objProf.setEmaiProfessor(result.getString(10));
				objProf.setPrdProfessor(result.getString(11));
				}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objProf;		
	}

	public boolean inserirProfessor(ProfessorBean obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_PROFESSOR);
			statement.setString(1, obj.getNmProfessor());
			statement.setString(2, obj.getSexoProfessor());
			statement.setString(3, obj.getRgProfessor());
			statement.setString(4, obj.getCpfProfessor());
			statement.setString(5, obj.getDtNasProfessor());
			statement.setString(6, obj.getTelProfessor());
			statement.setString(7, obj.getCelProfessor());
            statement.setString(8, obj.getEndProfessor());
            statement.setString(9, obj.getEmaiProfessor());
            statement.setString(10, obj.getPrdProfessor());
            statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean atualizarProfessor(ProfessorBean objFunc) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_PROFESSOR);
			statement.setString(1, objFunc.getNmProfessor());
			statement.setString(2, objFunc.getSexoProfessor());
			statement.setString(3, objFunc.getRgProfessor());
			statement.setString(4, objFunc.getCpfProfessor());
			statement.setString(5, objFunc.getDtNasProfessor());
			statement.setString(6, objFunc.getTelProfessor());
			statement.setString(7, objFunc.getCelProfessor());
            statement.setString(8, objFunc.getEndProfessor());
            statement.setString(9, objFunc.getEmaiProfessor());
            statement.setString(10, objFunc.getPrdProfessor());
            statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirProfessor(int COD_PROFESSOR) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_PROFESSOR);
			statement.setInt(1, COD_PROFESSOR);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
}
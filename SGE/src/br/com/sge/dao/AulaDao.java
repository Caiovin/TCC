package br.com.sge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sge.bean.AulaBean;
import br.com.sge.exception.DaoException;
import br.com.sge.util.DbUtil;

public class AulaDao {
    
    private static final String EXCLUIR_AULA = 
			"delete from TB_AULA where COD_AULA = ?";

	private static final String INSERIR_AULA =
			"insert into TB_AULA(COD_PROFESSOR, COD_TURMA, COD_MATERIA, NM_PROFESSOR, NM_MATERIA, NM_TURMA, HORARIO_AULA)"+
			"values (?,?,?,?,?,?,?)";

	private static final String ATUALIZAR_AULA =
			"update TB_AULA set " +
            "COD_PROFESSOR = ?, " +
            "COD_TURMA = ?," +
            "COD_MATERIA = ?," +
			"NM_PROFESSOR = ?, " +
			"NM_MATERIA = ?, " +
			"NM_TURMA = ?, " +
            "HORARIO_AULA = ?, ";
	
	private static final  String CONSULTA_AULA =
			"select * from TB_AULA order by HORARIO_AULA asc";

	private static final  String CONSULTA_AULA_PROFESSOR =
			"select * from TB_AULA where NM_PROFESSOR like ? order by NM_PROFESSOR";

	private static final  String CONSULTA_AULA_COD = 
			"select * from TB_AULA where COD_AULA = ?";


	public List<AulaBean> consultarAula(String nmProfessor) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<AulaBean> listaAlun = new ArrayList<AulaBean>();
		try {
			if(nmProfessor.equals("")){
				statement = conn.prepareStatement(CONSULTA_AULA);
			}else{
				statement = conn.prepareStatement(CONSULTA_AULA_PROFESSOR);
				statement.setString(1, "%"+nmProfessor+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				AulaBean objAula = new AulaBean();
				objAula.setCodAula(result.getInt(1));
				objAula.setCodProfessor(result.getInt(2));
                objAula.setCodTurma(result.getInt(3));
				objAula.setCodMateria(result.getInt(4));
                objAula.setNmProfessor(result.getString(5));
				objAula.setNmTurma(result.getString(6));
				objAula.setNmMateria(result.getString(7));
				objAula.setHorarioAula(result.getString(8));
				listaAlun.add(objAula);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaAlun;		
	}

	public AulaBean consultarAulaCod(int codAula) throws DaoException{		
		AulaBean objAlun = new AulaBean();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_AULA_COD);
			statement.setInt(1, codAula);
			result = statement.executeQuery();
			while (result.next()) {
				objAlun.setCodAula(result.getInt(1));
				objAlun.setCodProfessor(result.getInt(2));
                objAlun.setCodTurma(result.getInt(3));
				objAlun.setCodMateria(result.getInt(4));
                objAlun.setNmProfessor(result.getString(5));
				objAlun.setNmTurma(result.getString(6));
				objAlun.setNmMateria(result.getString(7));
				objAlun.setHorarioAula(result.getString(8));
				
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objAlun;
	}

	public boolean inserirAula(AulaBean obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_AULA);
			statement.setInt(1, obj.getCodProfessor());
			statement.setInt(2, obj.getCodTurma());
			statement.setInt(3, obj.getCodMateria());
			statement.setString(4, obj.getNmProfessor());
			statement.setString(5, obj.getNmTurma());
			statement.setString(6, obj.getNmMateria());
			statement.setString(7, obj.getHorarioAula());
            statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;
	}

	public boolean atualizarAula(AulaBean objAula) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_AULA);
			statement.setInt(1, objAula.getCodProfessor());
			statement.setInt(2, objAula.getCodTurma());
			statement.setInt(3, objAula.getCodMateria());
			statement.setString(4, objAula.getNmProfessor());
			statement.setString(5, objAula.getNmTurma());
			statement.setString(6, objAula.getNmMateria());
			statement.setString(7, objAula.getHorarioAula());
            statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirAula(int codAula) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_AULA);
			statement.setInt(1, codAula);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
}
package br.com.sge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sge.bean.AlunoBean;
import br.com.sge.exception.DaoException;
import br.com.sge.util.DbUtil;

/**ALUNO
 *
 * @author QBEX
 */
public class AlunoDao {
    
    private static final String EXCLUIR_ALUNO = 
			"delete from TB_ALUNO where RM_ALUNO = ?";

	private static final String INSERIR_ALUNO =
			"insert into TB_ALUNO(RM_ALUNO, NM_ALUNO, SEXO_ALUNO, RG_ALUNO, CPF_ALUNO, DT_NASC_ALUNO, "+
			"TEL_ALUNO, CEL_ALUNO, END_ALUNO, EMAIL_ALUNO,COD_RESPONSAVEL, NM_RESPONSAVEL, NM_TURMA, COD_TURMA, PRD_ALUNO" +
            "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String ATUALIZAR_ALUNO =
			"update TB_ALUNO set " +
            "RM_ALUNO = ?, " +
			"NM_ALUNO = ?, " +
            "SEXO_IDADE = ?," +
            "RG_ALUNO = ?," +
			"CPF_ALUNO = ?, " +
			"DT_NASC_ALUNO = ?, " +
			"TEL_ALUNO = ?, " +
            "CEL_ALUNO = ?, " +
            "END__ALUNO = ?, " +
            "EMAIL_ALUNO = ?, " +
            "COD_RESPONSAVEL = ?, "+
			"NM_RESPONSAVEL = ?, " +
            "NM_TURMA = ?, " +
            "COD_TURMA = ?, " +
            "PRD_ALUNO = ?, ";

	
	private static final  String CONSULTA_ALUNO =
			"select * from TB_ALUNO order by NM_ALUNO asc";

	private static final  String CONSULTA_ALUNO_NOME =
			"select * from TB_ALUNO where NM_ALUNO like ? order by NM_ALUNO";

	private static final  String CONSULTA_ALUNO_RM = 
			"select * from TB_ALUNO where RM_ALUNO = ?";


	public List<AlunoBean> consultarAluno(String NmAluno) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<AlunoBean> listaAlun = new ArrayList<AlunoBean>();
		try {
			if(NmAluno.equals("")){
				statement = conn.prepareStatement(CONSULTA_ALUNO);
			}else{
				statement = conn.prepareStatement(CONSULTA_ALUNO_NOME);
				statement.setString(1, "%"+NmAluno+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				AlunoBean objAlun = new AlunoBean();
				objAlun.setRmAluno(result.getInt(1));
				objAlun.setNmAluno(result.getString(2));
                objAlun.setSexoAluno(result.getString(3));
				objAlun.setRgAluno(result.getString(4));
                objAlun.setCpfAluno(result.getString(5));
				objAlun.setDtNascAluno(result.getString(6));
				objAlun.setTelAluno(result.getString(7));
				objAlun.setCelAluno(result.getString(8));
				objAlun.setEndAluno(result.getString(9));
				objAlun.setEmailAluno(result.getString(10));
                objAlun.setNmResponsavel(result.getString(11));
                objAlun.setNmTurma(result.getString(12));
                objAlun.setCodTurma(result.getInt(13));
                objAlun.setCodResponsavel(result.getInt(14));
                objAlun.setPrdAluno(result.getString(15));
                listaAlun.add(objAlun);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaAlun;		
	}

	public AlunoBean consultarAlunoRM(int rmAluno) throws DaoException{		
		AlunoBean objAlun = new AlunoBean();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_ALUNO_RM);
			statement.setInt(1, rmAluno);
			result = statement.executeQuery();
			while (result.next()) {
				objAlun.setRmAluno(result.getInt(1));
				objAlun.setNmAluno(result.getString(2));
                objAlun.setSexoAluno(result.getString(3));
				objAlun.setRgAluno(result.getString(4));
                objAlun.setCpfAluno(result.getString(5));
				objAlun.setDtNascAluno(result.getString(6));
				objAlun.setTelAluno(result.getString(7));
				objAlun.setCelAluno(result.getString(8));
				objAlun.setEndAluno(result.getString(9));
				objAlun.setEmailAluno(result.getString(10));
                objAlun.setNmResponsavel(result.getString(11));
                objAlun.setNmTurma(result.getString(12));
                objAlun.setCodTurma(result.getInt(13));
                objAlun.setCodResponsavel(result.getInt(14));
                objAlun.setPrdAluno(result.getNString(15));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objAlun;		
	}

	public boolean inserirAluno(AlunoBean obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_ALUNO);
			statement.setInt(1, obj.getRmAluno());
            statement.setString(2,obj.getNmAluno());
			statement.setString(3, obj.getSexoAluno());
			statement.setString(4, obj.getRgAluno());
			statement.setString(5, obj.getCpfAluno());
			statement.setString(6, obj.getDtNascAluno());
			statement.setString(7, obj.getTelAluno());
			statement.setString(8, obj.getCelAluno());
            statement.setString(9, obj.getEndAluno());
            statement.setString(10, obj.getEmailAluno());
            statement.setString(11, obj.getNmResponsavel());
            statement.setString(12, obj.getNmTurma());
            statement.setInt(13, obj.getCodTurma());
            statement.setInt(14, obj.getCodResponsavel());
            statement.setString(15, obj.getPrdAluno());
            statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean atualizarAluno(AlunoBean objAlun) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_ALUNO);
			statement.setInt(1, objAlun.getRmAluno());
            statement.setString(2, objAlun.getNmAluno());
			statement.setString(3, objAlun.getSexoAluno());
			statement.setString(4, objAlun.getRgAluno());
			statement.setString(5, objAlun.getCpfAluno());
			statement.setString(6, objAlun.getDtNascAluno());
			statement.setString(7, objAlun.getTelAluno());
			statement.setString(8, objAlun.getCelAluno());
            statement.setString(9, objAlun.getEndAluno());
            statement.setString(10, objAlun.getEmailAluno());
            statement.setString(11, objAlun.getNmResponsavel());
            statement.setString(12, objAlun.getNmTurma());
            statement.setInt(13, objAlun.getCodTurma());
            statement.setInt(14, objAlun.getCodResponsavel());
            statement.setString(15, objAlun.getPrdAluno());
            statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirAluno(int rmAluno) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_ALUNO);
			statement.setInt(1, rmAluno);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
}
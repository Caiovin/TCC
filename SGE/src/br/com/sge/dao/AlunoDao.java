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

public class AlunoDao {
    
    private static final String EXCLUIR_ALUNO = 
			"delete from TB_ALUNO where RM_ALUNO = ?";

	private static final String INSERIR_ALUNO =
			"insert into TB_ALUNO(NM_ALUNO, SEXO_ALUNO, RG_ALUNO, CPF_ALUNO, DT_NASC_ALUNO, "+
			"TEL_ALUNO, CEL_ALUNO, END_ALUNO, EMAIL_ALUNO,COD_RESPONSAVEL, NM_RESPONSAVEL, NM_TURMA, COD_TURMA, PRD_ALUNO" +
            "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String ATUALIZAR_ALUNO =
			"update TB_ALUNO set " +
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
	
	private AlunoBean getBean(ResultSet result) throws DaoException, SQLException{
		AlunoBean bean = new AlunoBean();
		
		AlunoBean objAlun = new AlunoBean();
		objAlun.setRmAluno(result.getInt("RM_ALUNO"));
		objAlun.setNmAluno(result.getString("NM_ALUNO"));
        objAlun.setSexoAluno(result.getString("SEXO_ALUNO"));
		objAlun.setRgAluno(result.getString("RG_ALUNO"));
        objAlun.setCpfAluno(result.getString("CPF_ALUNO"));
		objAlun.setDtNascAluno(result.getString("DT_NASC_ALUNO"));
		objAlun.setTelAluno(result.getString("TEL_ALUNO"));
		objAlun.setCelAluno(result.getString("CEL_ALUNO"));
		objAlun.setEmailAluno(result.getString("EMAIL_ALUNO"));
        objAlun.setCodTurma(result.getInt("COD_TURMA"));
        objAlun.setCodResponsavel(result.getInt("COD_RESPONSAVEL"));
        
        return objAlun;
	}


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
				AlunoBean objAlun = getBean(result);
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
			if (result.next()) {
				objAlun = getBean(result);
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
			statement.setString(1, obj.getNmAluno());
			statement.setString(2, obj.getSexoAluno());
			statement.setString(3, obj.getRgAluno());
			statement.setString(4, obj.getCpfAluno());
			statement.setString(5, obj.getDtNascAluno());
			statement.setString(6, obj.getTelAluno());
			statement.setString(7, obj.getCelAluno());
            statement.setString(8, obj.getEndAluno());
            statement.setString(9, obj.getEmailAluno());
            statement.setString(10, obj.getNmResponsavel());
            statement.setString(11, obj.getNmTurma());
            statement.setInt(12, obj.getCodTurma());
            statement.setInt(13, obj.getCodResponsavel());
            statement.setString(14, obj.getPrdAluno());
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
			statement.setString(1, objAlun.getNmAluno());
			statement.setString(2, objAlun.getSexoAluno());
			statement.setString(3, objAlun.getRgAluno());
			statement.setString(4, objAlun.getCpfAluno());
			statement.setString(5, objAlun.getDtNascAluno());
			statement.setString(6, objAlun.getTelAluno());
			statement.setString(7, objAlun.getCelAluno());
            statement.setString(8, objAlun.getEndAluno());
            statement.setString(9, objAlun.getEmailAluno());
            statement.setString(10, objAlun.getNmResponsavel());
            statement.setString(11, objAlun.getNmTurma());
            statement.setInt(12, objAlun.getCodTurma());
            statement.setInt(13, objAlun.getCodResponsavel());
            statement.setString(14, objAlun.getPrdAluno());
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
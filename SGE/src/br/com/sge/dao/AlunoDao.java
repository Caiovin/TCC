package br.com.sge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sge.bean.Aluno;
import br.com.sge.exception.DaoException;
import br.com.sge.util.DbUtil;

/**ALUNO
 *
 * @author QBEX
 */
public class AlunoDao {
    
    private static final String EXCLUIR_ALUNO = 
			"delete from tbAluno where MatriculaAluno = ?";

	private static final String INSERIR_ALUNO =
			"insert into tbAluno(matricula_Aluno, nome_aluno, idade_aluno, nasc_aluno, sexo_aluno, cpf_aluno, rg_aluno, "+
			"ensino_aluno, serie_aluno, endereco_aluno, bairro_aluno, cidade_aluno, cep_aluno, " +
                        "tel_aluno, cel_aluno, estado_aluno )" +
			"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String ATUALIZAR_ALUNO =
			"update tbAluno set " +
                        "matricula_aluno = ?, " +
			"nome_aluno = ?, " +
                        "idade_aluno = ?," +
                        "nasc_aluno = ?," +
			"sexo_aluno = ?, " +
			"cpf_aluno = ?, " +
			"rg_aluno = ?, " +
                        "ensino_aluno = ?, " +
                        "serie_aluno = ?, " +
                        "endereco_aluno = ?, " +
			"bairro_aluno = ?, " +
                        "cidade_aluno = ?, " +
                        "cep_aluno = ?, " +
                        "tel_aluno = ?, " +
                        "cel_aluno = ?, " +
                        "estado_aluno = ? ";

	
	private static final  String CONSULTA_ALUNOS =
			"select * from tbAluno order by nome_aluno";

	private static final  String CONSULTA_ALUNOS_NOME =
			"select * from tbAluno where nome_aluno like ? order by nome_aluno";

	private static final  String CONSULTA_ALUNO_RM = 
			"select * from tbAluno where MatriculaAluno = ?";


	public List<Aluno> consultarAlunos(String nome) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Aluno> listaAlun = new ArrayList<Aluno>();
		try {
			if(nome.equals("")){
				statement = conn.prepareStatement(CONSULTA_ALUNOS);
			}else{
				statement = conn.prepareStatement(CONSULTA_ALUNOS_NOME);
				statement.setString(1, "%"+nome+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				Aluno objAlun = new Aluno();
				objAlun.setMatriculaAluno(result.getInt(1));
				objAlun.setNome(result.getString(2));
                                objAlun.setIdade(result.getInt(3));
				objAlun.setSexo(result.getString(4));
                                objAlun.setNasc(result.getString(5));
				objAlun.setCPF(result.getString(6));
				objAlun.setRG(result.getString(7));
				objAlun.setEnsino(result.getString(8));
				objAlun.setSerie(result.getString(9));
				objAlun.setEndereco(result.getString(10));
                                objAlun.setBairro(result.getString(11));
                                objAlun.setCidade(result.getString(12));
                                objAlun.setCEP(result.getString(13));
                                objAlun.setTelefone(result.getInt(14));
                                objAlun.setCelular(result.getInt(15));
                                objAlun.setEstado(result.getString(16));
				listaAlun.add(objAlun);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaAlun;		
	}

	public Aluno consultarAlunoRM(int idAlun) throws DaoException{		
		Aluno objAlun = new Aluno();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_ALUNO_RM);
			statement.setInt(1, idAlun);
			result = statement.executeQuery();
			while (result.next()) {
				objAlun.setMatriculaAluno(result.getInt(1));
				objAlun.setNome(result.getString(2));
                                objAlun.setIdade(result.getInt(3));
				objAlun.setSexo(result.getString(4));
                                objAlun.setNasc(result.getString(5));
				objAlun.setCPF(result.getString(6));
				objAlun.setRG(result.getString(7));
				objAlun.setEnsino(result.getString(8));
				objAlun.setSerie(result.getString(9));
				objAlun.setEndereco(result.getString(10));
                                objAlun.setBairro(result.getString(11));
                                objAlun.setCidade(result.getString(12));
                                objAlun.setCEP(result.getString(13));
                                objAlun.setTelefone(result.getInt(14));
                                objAlun.setCelular(result.getInt(15));
                                objAlun.setEstado(result.getString(16));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objAlun;		
	}

	public boolean inserirAlunos(Aluno obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_ALUNO);
			statement.setInt(1, obj.getMatriculaAluno());
                        statement.setString(2,obj.getNome());
			statement.setInt(3, obj.getIdade());
			statement.setString(4, obj.getSexo());
			statement.setString(5, obj.getNasc());
			statement.setString(6, obj.getCPF());
			statement.setString(7, obj.getRG());
			statement.setString(8, obj.getEnsino());
                        statement.setString(9, obj.getSerie());
                        statement.setString(10, obj.getEndereco());
                        statement.setString(11, obj.getBairro());
                        statement.setString(12, obj.getCidade());
                        statement.setString(13, obj.getCEP());
                        statement.setInt(14, obj.getTelefone());
                        statement.setInt(15, obj.getCelular());
                        statement.setString(16, obj.getEstado());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean atualizarAluno(Aluno objAlun) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_ALUNO);
			statement.setInt(1, objAlun.getMatriculaAluno());
                        statement.setString(2, objAlun.getNome());
			statement.setInt(3, objAlun.getIdade());
			statement.setString(4, objAlun.getSexo());
			statement.setString(5, objAlun.getNasc());
			statement.setString(6, objAlun.getCPF());
			statement.setString(7, objAlun.getRG());
			statement.setString(8, objAlun.getEnsino());
                        statement.setString(9, objAlun.getSerie());
                        statement.setString(10, objAlun.getEndereco());
                        statement.setString(11, objAlun.getBairro());
                        statement.setString(12, objAlun.getCidade());
                        statement.setString(13, objAlun.getCEP());
                        statement.setInt(14, objAlun.getTelefone());
                        statement.setInt(15, objAlun.getCelular());
                        statement.setString(16, objAlun.getEstado());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirAlunos(int MatriculaAluno) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_ALUNO);
			statement.setInt(1, MatriculaAluno);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
}
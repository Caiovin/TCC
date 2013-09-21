package br.com.sge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sge.bean.Funcionario;
import br.com.sge.exception.DaoException;
import br.com.sge.util.DbUtil;

/**ALUNO
 *
 * @author QBEX
 */
public class FuncionarioDao {
    
    private static final String EXCLUIR_FUNCIONARIO = 
			"delete from tbFuncionario where cosFuncionario = ?";

	private static final String INSERIR_FUNCIONARIO =
			"insert into tbFuncionario(cod_funcionario, nome_funcionario, end_funcionario, cidade_funcionario, rg_funcionario, "+
                        "cpf_funcionario, fonec1_funcionario, fonec2_funcionario, foner_funcioanrio, cel_funcionario, "+
                        "leciona_funcionario, graduacao_funcionario, email_funcionario, sexo_funcionario, "+
			"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String ATUALIZAR_FUNCIONARIO =
			"update tbFuncionario set " +
			"cod_funcionario = ?, " +
                        "nome_funcionario = ?," +
                        "end_funcionario = ?," +
			"cidade_funcionario = ?, " +
                        "rg_funcionario = ?, " +
			"cpf_funcionario = ?, " +
			"fonec1_funcionario = ?, " +
                        "fonec2_funcionario = ?, " +
                        "foner_funcioanrio = ?, " +
                        "cel_funcionario = ?, " +
			"leciona_funcionario = ?, " +
                        "graduacao_funcionario = ?, " +
                        "senha_funcioanrio = ?, " +
                        "email_funcionario = ?,";

	
	private static final  String CONSULTA_FUNCIONARIOS =
			"select * from tbFuncionario order by nome_funcionario";

	private static final  String CONSULTA_FUNCIONARIOS_NOME =
			"select * from tbFuncionario where nome_funcioanrio like ? order by nome_funcionario";

	private static final  String CONSULTA_FUNCIONARIOS_COD = 
			"select * from tbFuncionario where codFuncionario = ?";


	public List<Funcionario> consultarFuncionarios(String nome) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Funcionario> listaFunc = new ArrayList<Funcionario>();
		try {
			if(nome.equals("")){
				statement = conn.prepareStatement(CONSULTA_FUNCIONARIOS);
			}else{
				statement = conn.prepareStatement(CONSULTA_FUNCIONARIOS_NOME);
				statement.setString(1, "%"+nome+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				Funcionario objFunc = new Funcionario();
				objFunc.setCodFuncionario(result.getInt(1));
				objFunc.setNome(result.getString(2));
                                objFunc.setEndereco(result.getString(3));
				objFunc.setCidade(result.getString(4));
                                objFunc.setRG(result.getString(5));
				objFunc.setCPF(result.getString(6));
				objFunc.setFoneCome1(result.getString(7));
				objFunc.setFoneCome2(result.getString(8));
                                objFunc.setFoneResi(result.getString(9));
                                objFunc.setCelular(result.getString(10));
                                objFunc.setLeciona(result.getString(11));
                                objFunc.setGraduacao(result.getString(12));
                                objFunc.setEmail(result.getString(13));
                                objFunc.setSexo(result.getString(14));
                                listaFunc.add(objFunc);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaFunc;		
	}

	public Funcionario consultarFuncioanrioCod(int codFuncionario) throws DaoException{		
		Funcionario objFunc = new Funcionario();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_FUNCIONARIOS_COD);
			statement.setInt(1, codFuncionario);
			result = statement.executeQuery();
			while (result.next()) {
				objFunc.setCodFuncionario(result.getInt(1));
				objFunc.setNome(result.getString(2));
                                objFunc.setEndereco(result.getString(3));
				objFunc.setCidade(result.getString(4));
                                objFunc.setRG(result.getString(5));
				objFunc.setCPF(result.getString(6));
				objFunc.setFoneCome1(result.getString(7));
				objFunc.setFoneCome2(result.getString(8));
				objFunc.setFoneResi(result.getString(9));
				objFunc.setCelular(result.getString(10));
                                objFunc.setLeciona(result.getString(11));
                                objFunc.setGraduacao(result.getString(12));
                                objFunc.setEmail(result.getString(13));
                                objFunc.setSexo(result.getString(14));
                                
                        }
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objFunc;		
	}

	public boolean inserirFuncionarios(Funcionario obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_FUNCIONARIO);
			statement.setInt(1, obj.getCodFuncionario());
                        statement.setString(2,obj.getNome());
			statement.setString(3, obj.getEndereco());
			statement.setString(4, obj.getCidade());
			statement.setString(5, obj.getRG());
			statement.setString(6, obj.getCPF());
			statement.setString(7, obj.getFoneCome1());
			statement.setString(8, obj.getFoneCome2());
                        statement.setString(9, obj.getFoneResi());
                        statement.setString(10, obj.getCelular());
                        statement.setString(11, obj.getLeciona());
                        statement.setString(12, obj.getGraduacao());
                        statement.setString(13, obj.getEmail());
                        statement.setString(14, obj.getSexo());
                        statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean atualizarFuncionario(Funcionario objFunc) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_FUNCIONARIO);
			statement.setInt(1, objFunc.getCodFuncionario());
                        statement.setString(2,objFunc.getNome());
			statement.setString(3, objFunc.getEndereco());
			statement.setString(4, objFunc.getCidade());
			statement.setString(5, objFunc.getRG());
			statement.setString(6, objFunc.getCPF());
			statement.setString(7, objFunc.getFoneCome1());
			statement.setString(8, objFunc.getFoneCome2());
                        statement.setString(9, objFunc.getFoneResi());
                        statement.setString(10, objFunc.getCelular());
                        statement.setString(11, objFunc.getLeciona());
                        statement.setString(12, objFunc.getGraduacao());
                        statement.setString(13, objFunc.getEmail());
                        statement.setString(14, objFunc.getSexo());
                        statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirFuncionarios(int codFuncionario) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_FUNCIONARIO);
			statement.setInt(1, codFuncionario);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

    public boolean getAutenticacao(String nome, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Funcionario consultarFuncionarios(Integer mat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
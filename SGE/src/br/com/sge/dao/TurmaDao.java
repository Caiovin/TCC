package br.com.sge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sge.bean.Turma;
import br.com.sge.exception.DaoException;
import br.com.sge.util.DbUtil;

public class TurmaDao {
    
    private static final String EXCLUIR_TURMA = 
			"delete from tbTurma where cosFuncionario = ?";

	private static final String INSERIR_TURMA =
			"insert into tbTurma(cod_turma, nome_turma)"+
			"values (?,?)";

	private static final String ATUALIZAR_TURMA =
			"update tbTurma set " +
			"cod_turma = ?, " +
                        "nome_turma= ?,";

	
	private static final  String CONSULTA_TURMA =
			"select * from tbTurmaorder by nome_turma";

	private static final  String CONSULTA_TURMA_NOME =
			"select * from tbTurma where nome_turma like ? order by nome_turma";

	private static final  String CONSULTA_TURMA_COD = 
			"select * from tbTurma where codTurma = ?";


	public List<Turma> consultarFuncionarios(String nome) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Turma> listaFunc = new ArrayList<Turma>();
		try {
			if(nome.equals("")){
				statement = conn.prepareStatement(CONSULTA_TURMA);
			}else{
				statement = conn.prepareStatement(CONSULTA_TURMA_NOME);
				statement.setString(1, "%"+nome+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				Turma objTurma = new Turma();
				objTurma.setCodTurma(result.getInt(1));
				objTurma.setNome(result.getString(2));
                                listaFunc.add(objTurma);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaFunc;		
	}

	public Turma consultarTurmaCod(int codTurma) throws DaoException{		
		Turma objTurma = new Turma();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_TURMA_COD);
			statement.setInt(1, codTurma);
			result = statement.executeQuery();
			while (result.next()) {
				objTurma.setCodTurma(result.getInt(1));
				objTurma.setNome(result.getString(2));
                                
                        }
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objTurma;		
	}

	public boolean inserirTurma(Turma obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_TURMA);
			statement.setInt(1, obj.getCodTurma());
                        statement.setString(2,obj.getNome());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean atualizarTurma(Turma objTurma) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_TURMA);
			statement.setInt(1, objTurma.getCodTurma());
                        statement.setString(2,objTurma.getNome());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirFuncionarios(int codTurma) throws DaoException{		
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

    public boolean getAutenticacao(String nome, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Turma consultarFuncionarios(Integer mat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
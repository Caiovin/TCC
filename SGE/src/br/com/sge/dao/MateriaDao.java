package br.com.sge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sge.bean.Materia;
import br.com.sge.exception.DaoException;
import br.com.sge.util.DbUtil;

/**
 *
 * @author Familia
 */
public class MateriaDao {
    
    private static final String EXCLUIR_MATERIA = 
			"delete from tbMateria where codMateria = ?";

	private static final String INSERIR_MATERIA =
			"insert into tbMateria(cod_Materia, prof_materia, nome_materia, carga_materia"+
			"values (?,?,?,?)";

	private static final String ATUALIZAR_MATERIA =
			"update tbMateria set " +
			"cod_Materia = ?, " +
                        "prof_materia = ?," +
                        "nome_materia = ?," +
			"carga_materia = ?, ";

	
	private static final  String CONSULTA_MATERIAS =
			"select * from tbMateria order by nome_materia";

	private static final  String CONSULTA_MATERIAS_NOME =
			"select * from tbMateria where nome_materia like ? order by nome_funcionario";

	private static final  String CONSULTA_MATERIAS_COD = 
			"select * from tbMateria where codFuncionario = ?";


	public List<Materia> consultarMaterias(String nome) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Materia> listaMat = new ArrayList<Materia>();
		try {
			if(nome.equals("")){
				statement = conn.prepareStatement(CONSULTA_MATERIAS);
			}else{
				statement = conn.prepareStatement(CONSULTA_MATERIAS_NOME);
				statement.setString(1, "%"+nome+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				Materia objMat = new Materia();
				objMat.setCodMateria(result.getInt(1));
				objMat.setProfessor(result.getString(2));
                                objMat.setNome(result.getString(3));
				objMat.setCarga(result.getString(4));
                                listaMat.add(objMat);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaMat;		
	}

	public Materia consultarMateriaCod(int codMateria) throws DaoException{		
		Materia objMat = new Materia();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_MATERIAS_COD);
			statement.setInt(1, codMateria);
			result = statement.executeQuery();
			while (result.next()) {
				objMat.setCodMateria(result.getInt(1));
				objMat.setProfessor(result.getString(2));
                                objMat.setNome(result.getString(3));
				objMat.setCarga(result.getString(4));
                                
                        }
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objMat;		
	}

	public boolean inserirMaterias(Materia obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_MATERIA);
			statement.setInt(1, obj.getCodMateria());
                        statement.setString(2,obj.getProfessor());
			statement.setString(3, obj.getNome());
			statement.setString(4, obj.getCarga());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean atualizarMateria(Materia objMat) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_MATERIA);
			statement.setInt(1, objMat.getCodMateria());
                        statement.setString(2,objMat.getProfessor());
			statement.setString(3, objMat.getNome());
			statement.setString(4, objMat.getCarga());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirMaterias(int codMateria) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_MATERIA);
			statement.setInt(1, codMateria);
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

    public Materia consultarMaterias(Integer mat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
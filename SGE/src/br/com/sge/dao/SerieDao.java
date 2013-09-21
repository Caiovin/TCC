package br.com.sge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sge.bean.Serie;
import br.com.sge.exception.DaoException;
import br.com.sge.util.DbUtil;

/**
 *
 * @author Familia
 */
public class SerieDao {
    
    private static final String EXCLUIR_SERIE = 
			"delete from tbSerie where codSerie = ?";

	private static final String INSERIR_SERIE =
			"insert into tbSerie(cod_serie, nome_serie, cod_turma "+
			"values (?,?,?)";

	private static final String ATUALIZAR_SERIE =
			"update tbSerie set " +
			"cod_serie = ?, " +
                        "nome_serie = ?," +
                        "cod_turma = ?,";

	
	private static final  String CONSULTA_SERIE =
			"select * from tbSerie order by nome_serie";

	private static final  String CONSULTA_SERIE_NOME =
			"select * from tbSerie where nome_serie like ? order by nome_serie";

	private static final  String CONSULTA_SERIE_COD = 
			"select * from tbSerie where codSerie = ?";


	public List<Serie> consultarSeries(String nome) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Serie> listaSerie = new ArrayList<Serie>();
		try {
			if(nome.equals("")){
				statement = conn.prepareStatement(CONSULTA_SERIE);
			}else{
				statement = conn.prepareStatement(CONSULTA_SERIE_NOME);
				statement.setString(1, "%"+nome+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				Serie objSerie = new Serie();
				objSerie.setCodSerie(result.getInt(1));
				objSerie.setNome(result.getString(2));
                                objSerie.setCodTurma(result.getInt(3));
				listaSerie.add(objSerie);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaSerie;		
	}

	public Serie consultarSerieCod(int codSerie) throws DaoException{		
		Serie objSerie = new Serie();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_SERIE_COD);
			statement.setInt(1, codSerie);
			result = statement.executeQuery();
			while (result.next()) {
				objSerie.setCodSerie(result.getInt(1));
				objSerie.setNome(result.getString(2));
                                objSerie.setCodTurma(result.getInt(3));
                                
                        }
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objSerie;		
	}

	public boolean inserirSeries(Serie obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_SERIE);
			statement.setInt(1, obj.getCodSerie());
                        statement.setString(2,obj.getNome());
			statement.setInt(3, obj.getCodTurma());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean atualizarSerie(Serie objSerie) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_SERIE);
			statement.setInt(1, objSerie.getCodSerie());
                        statement.setString(2,objSerie.getNome());
			statement.setInt(3, objSerie.getCodTurma());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirSeries(int codSerie) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_SERIE);
			statement.setInt(1, codSerie);
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

    public Serie consultarSeries(Integer mat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
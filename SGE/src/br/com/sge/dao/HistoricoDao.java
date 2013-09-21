package br.com.sge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.sge.bean.Historico;
import br.com.sge.exception.DaoException;
import br.com.sge.util.DbUtil;

/**
 *
 * @author Familia
 */
public class HistoricoDao {
    
    	private static final  String CONSULTA_HISTORICO_COD = 
			"select * from Historico where codHistorico = ?";


	public Historico consultarHistoricoCod(int codHistorico) throws DaoException{		
		Historico objHist = new Historico();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_HISTORICO_COD);
			statement.setInt(1, codHistorico);
			result = statement.executeQuery();
			while (result.next()) {
				objHist.setCodHistorico(result.getInt(1));
				objHist.setCodMateria(result.getInt(2));
                                objHist.setCodSerie(result.getInt(3));
				objHist.setFrequencia(result.getString(4));
                                objHist.setNota(result.getInt(5));
				
                        }
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objHist;		
	}

}
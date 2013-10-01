package br.com.sge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sge.bean.ResponsavelBean;
import br.com.sge.exception.DaoException;
import br.com.sge.util.DbUtil;

public class ResponsavelDao {
    
    private static final String EXCLUIR_RESPONSAVEL = 
			"delete from TB_RESPONSAVEL where COD_RESPONSAVEL = ?";

	private static final String INSERIR_RESPONSAVEL =
			"insert into TB_RESPONSAVEL(NM_RESPONSAVEL, TEL_RESPONSAVEL, CEL_RESPONSAVEL, "+
            "CEP, NUM_ENDERECO, EMAIL_RESPONSAVEL, COMPLEMENTO "+
            "values (?,?,?,?,?,?,?,?)";

	private static final String ATUALIZAR_RESPONSAVEL =
			"update TB_RESPONSAVELset " +
			"NM_RESPONSAVEL = ?," +
            "TEL_RESPONSAVEL = ?, " +
            "CEL_RESPONSAVEL = ?, " +
            "NUM_ENDERECO = ?, " +
            "EMAIL_RESPONSAVEL = ?, " +
			"CEP = ?, " +
            "COMPLEMENTO = ? ";
	
	private static final  String CONSULTA_RESPONSAVEL =
			"select * from TB_RESPONSAVEL order by NM_RESPONSAVEL";

	private static final  String CONSULTA_RESPONSAVEL_NOME =
			"select * from TB_RESPONSAVEL where NM_RESPONSAVEL like ? order by NM_RESPONSAVEL";

	private static final  String CONSULTA_RESPONSAVEL_COD = 
			"select * from TB_RESPONSAVEL where COD_RESPONSAVEL = ?";


	public List<ResponsavelBean> consultarResponsavel(String NmResponsavel) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<ResponsavelBean> listaResp = new ArrayList<ResponsavelBean>();
		try {
			if(NmResponsavel.equals("")){
				statement = conn.prepareStatement(CONSULTA_RESPONSAVEL);
			}else{
				statement = conn.prepareStatement(CONSULTA_RESPONSAVEL_NOME);
				statement.setString(1, "%"+NmResponsavel+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				ResponsavelBean objResp = new ResponsavelBean();
				objResp.setCodResponsavel(result.getInt(1));
				objResp.setNmResponsavel(result.getString(2));
				objResp.setTelResponsavel(result.getString(3));
				objResp.setCelResponsavel(result.getString(4));
				objResp.setCep(result.getString(5));
				objResp.setEmailResponsavel(result.getString(6));
				objResp.setComplemento(result.getString(7));
				objResp.setNumEndereco(result.getInt(8));
                listaResp.add(objResp);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaResp;		
	}

	public ResponsavelBean consultarResponsavelCod(int COD_RESPONSAVEL) throws DaoException{		
		ResponsavelBean objResp = new ResponsavelBean();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_RESPONSAVEL_COD);
			statement.setInt(1, COD_RESPONSAVEL);
			result = statement.executeQuery();
			while (result.next()) {
				objResp.setCodResponsavel(result.getInt(1));
				objResp.setNmResponsavel(result.getString(2));
				objResp.setTelResponsavel(result.getString(3));
				objResp.setCelResponsavel(result.getString(4));
				objResp.setCep(result.getString(5));
				objResp.setEmailResponsavel(result.getString(6));
				objResp.setComplemento(result.getString(7));
				objResp.setNumEndereco(result.getInt(8));
				}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objResp;		
	}

	public boolean inserirResponsavel(ResponsavelBean obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_RESPONSAVEL);
			statement.setString(1,obj.getNmResponsavel());
			statement.setString(2, obj.getTelResponsavel());
			statement.setString(3, obj.getCelResponsavel());
            statement.setString(4, obj.getCep());
            statement.setString(5, obj.getEmailResponsavel());
            statement.setString(6, obj.getComplemento());
            statement.setInt(7, obj.getNumEndereco());
            statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean atualizarResponsavel(ResponsavelBean objResp) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_RESPONSAVEL);
			statement.setString(1, objResp.getNmResponsavel());
			statement.setString(2, objResp.getTelResponsavel());
			statement.setString(3, objResp.getCelResponsavel());
            statement.setString(4, objResp.getCep());
            statement.setString(5, objResp.getEmailResponsavel());
            statement.setString(6, objResp.getComplemento());
            statement.setInt(7, objResp.getNumEndereco());
            statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirResponsavel(int COD_RESPONSAVEL) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_RESPONSAVEL);
			statement.setInt(1, COD_RESPONSAVEL);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
}

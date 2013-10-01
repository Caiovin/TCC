package br.com.sge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sge.bean.MateriaBean;
import br.com.sge.exception.DaoException;
import br.com.sge.util.DbUtil;

public class MateriaDao {
    
    private static final String EXCLUIR_MATERIA = 
			"delete from TB_MATERIA where COD_MATERIA = ?";

	private static final String INSERIR_MATERIA =
			"insert into TB_MATERIA(NM_MATERIA "+
			"values (?)";

	private static final String ATUALIZAR_ALUNO =
			"update TB_ALUNO set " +
            "NM_MATERIA = ?, ";

	
	private static final  String CONSULTA_MATERIA =
			"select * from TB_MATERIA order by NM_MATERIA asc";

	private static final  String CONSULTA_MATERIA_NOME =
			"select * from TB_MATERIA where NM_MATERIA like ? order by NM_MATERIA";

	private static final  String CONSULTA_MATERIA_COD = 
			"select * from TB_MATERIA where COD_MATERIA = ?";


	public List<MateriaBean> consultarMateria(String nmMateria) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<MateriaBean> listaMat = new ArrayList<MateriaBean>();
		try {
			if(nmMateria.equals("")){
				statement = conn.prepareStatement(CONSULTA_MATERIA);
			}else{
				statement = conn.prepareStatement(CONSULTA_MATERIA_NOME);
				statement.setString(1, "%"+nmMateria+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				MateriaBean objMat = new MateriaBean();
				objMat.setCodMateria(result.getInt(1));
				objMat.setNmMateria(result.getString(2));
                listaMat.add(objMat);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaMat;
	}

	public MateriaBean consultarmateriaCod(int codMateria) throws DaoException{		
		MateriaBean objMat = new MateriaBean();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_MATERIA_COD);
			statement.setInt(1, codMateria);
			result = statement.executeQuery();
			while (result.next()) {
				objMat.setCodMateria(result.getInt(1));
				objMat.setNmMateria(result.getString(2));
                
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objMat;		
	}

	public boolean inserirMateria(MateriaBean obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_MATERIA);
			statement.setString(1, obj.getNmMateria());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;
	}

	public boolean atualizarMateria(MateriaBean objAlun) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_ALUNO);
			statement.setString(1, objAlun.getNmMateria());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirMateria(int codMateria) throws DaoException{		
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
}
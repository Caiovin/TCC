package br.com.sge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.sge.bean.Plano;
import br.com.sge.exception.DaoException;
import br.com.sge.util.DbUtil;

/**
 *
 * @author Familia
 */
public class PlanoDao {
    
    private static final String EXCLUIR_PLANO = 
			"delete from tbPlano where codPlano = ?";

	private static final String INSERIR_PLANO =
			"insert into tbPlano(cod_Plano, cod_funcionario, Etapa_plano, disciplina_plano, ano_plano, objetivos_plano, "+
                        "conteudo_plano, procedimentos_plano, recursos_plano, avaliacao_plano, bibliografia_plnao, "+
                        "values (?,?,?,?,?,?,?,?,?,?,?)";

	private static final String ATUALIZAR_PLANO =
			"update tbPlano set " +
                        "cod_Plano = ?, "+
			"cod_funcionario = ?, " +
                        "Etapa_plano = ?," +
                        "disciplina_plano = ?," +
			"ano_plano = ?, " +
                        "objetivos_plano = ?, " +
			"conteudo_plano = ?, " +
			"procedimentos_plano = ?, " +
                        "recursos_plano = ?, " +
                        "avaliacao_plano = ?, " +
                        "biblioteca_plnao = ?, ";

	
	private static final  String CONSULTA_PLANO_COD = 
			"select * from tbPlano where codFuncionario = ?";


	public Plano consultarPlanoCod(int codPlano) throws DaoException{		
		Plano objPlan = new Plano();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_PLANO_COD);
			statement.setInt(1, codPlano);
			result = statement.executeQuery();
			while (result.next()) {
				objPlan.setCodPlano(result.getInt(1));
				objPlan.setCodFuncionario(result.getInt(2));
                                objPlan.setEtapa(result.getString(3));
				objPlan.setDisciplina(result.getString(4));
                                objPlan.setAno(result.getInt(5));
				objPlan.setObjetivos(result.getString(6));
				objPlan.setConteudo(result.getString(7));
				objPlan.setProcedimentos(result.getString(8));
				objPlan.setRecursos(result.getString(9));
				objPlan.setAvaliacao(result.getString(10));
                                objPlan.setBibliografia(result.getString(11));
                                
                        }
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objPlan;		
	}

	public boolean inserirPlano(Plano obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_PLANO);
			statement.setInt(1, obj.getCodPlano());
                        statement.setInt(2,obj.getCodFuncionario());
			statement.setString(3, obj.getEtapa());
			statement.setString(4, obj.getDisciplina());
			statement.setInt(5, obj.getAno());
			statement.setString(6, obj.getObjetivos());
			statement.setString(7, obj.getConteudo());
			statement.setString(8, obj.getProcedimentos());
                        statement.setString(9, obj.getRecursos());
                        statement.setString(10, obj.getAvaliacao());
                        statement.setString(11, obj.getBibliografia());
                        statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean atualizarPlano(Plano objPlan) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_PLANO);
			statement.setInt(1, objPlan.getCodPlano());
                        statement.setInt(2,objPlan.getCodFuncionario());
			statement.setString(3, objPlan.getEtapa());
			statement.setString(4, objPlan.getDisciplina());
			statement.setInt(5, objPlan.getAno());
			statement.setString(6, objPlan.getObjetivos());
			statement.setString(7, objPlan.getConteudo());
			statement.setString(8, objPlan.getProcedimentos());
                        statement.setString(9, objPlan.getRecursos());
                        statement.setString(10, objPlan.getAvaliacao());
                        statement.setString(11, objPlan.getBibliografia());
                        statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirPlanos(int codPlano) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_PLANO);
			statement.setInt(1, codPlano);
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

    public Plano consultarPlanos(Integer mat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
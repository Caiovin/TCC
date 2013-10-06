package br.com.sge.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import br.com.sge.exception.DaoException;

public class DbUtil {
	
	private static final Logger LOG = Logger.getLogger(DbUtil.class);
	
	private static final String URL_DATABASE = "jdbc:sqlserver://localhost:1433;databaseName=SGE";
	private static final String DRIVER_JDBC = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String USUARIO_DB = "sa";
	private static final String SENHA_USUARIO_DB = "123456";
	
	static {
		try {
			LOG.info("URL DATABASE : " + URL_DATABASE);
			LOG.info("DRIVER JDBC : " + DRIVER_JDBC);
			LOG.info("Carregando driver ");
			Class.forName(DRIVER_JDBC);
			LOG.info("Driver carregado");
		} catch (ClassNotFoundException e) {
			LOG.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws DaoException{
		try{
			Connection connection = DriverManager.getConnection(URL_DATABASE, USUARIO_DB, SENHA_USUARIO_DB);
			connection.setAutoCommit(true);
			LOG.debug("Conexao " + connection + " obtida");
			return connection;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	
	public static java.util.Date getJavaDate(ResultSet result, String nomeCampo) throws SQLException {
		Date dataFalecimento = result.getDate(nomeCampo);
		java.util.Date javaDate = null;
		if (dataFalecimento != null) {
			javaDate = new Date(dataFalecimento.getTime());
		}
		return javaDate;
	}
	
	public static java.util.Date getStampDate(ResultSet result, String nomeCampo) throws SQLException {
		Date data = new Date(result.getTimestamp(nomeCampo).getTime());
		java.util.Date javaDate = null;
		if (data != null) {
			javaDate = new Date(data.getTime());
		}
		return javaDate;
	}
	
	public static java.sql.Date getSqlDate(java.util.Date date) {
		java.sql.Date sqlDate = null;
		if (date != null) {
			sqlDate = new java.sql.Date(date.getTime());
		}
		return sqlDate;
	}
	
	public static java.sql.Timestamp getSqlTimestamp(java.util.Date date){
		java.sql.Timestamp sqlDate = null;
		if (date != null) {
			sqlDate = new java.sql.Timestamp(date.getTime());
		}
		return sqlDate;
	}
        
        public static byte[] getInputStream(ResultSet result, String campo) throws IOException, SQLException{
            InputStream input = result.getBinaryStream(campo);
            byte[] bytes = new byte[input.available()];
            
            return bytes;
        }
	
	public static void close(Connection conn, PreparedStatement statement, ResultSet result){
		try{
			if (conn != null) {
				LOG.debug("Fechando conexao " + conn);
				conn.close();
				LOG.debug("Conexao " + conn + " fechada");
			}
			if (statement != null) {
				LOG.debug("Fechando statement " + statement);
				statement.close();
				LOG.debug("Statement " + statement + " fechado");
			}
			if (result != null) {
				LOG.debug("Fechando ResultSet " + result);
				result.close();
				LOG.debug("ResultSet " + result + " fechado");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}

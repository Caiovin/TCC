package br.com.sge.bean;

public class UsuarioBean {
	private String nomeCompleto;
	private String nmUsuario;
	private String senhaUsuario;
	private String emailUsuario;
	
	
	/**
	 * @return the nomeCompleto
	 */
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	/**
	 * @param nomeCompleto the nomeCompleto to set
	 */
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	/**
	 * @return the nmUsuario
	 */
	public String getNmUsuario() {
		return nmUsuario;
	}
	/**
	 * @param nmUsuario the nmUsuario to set
	 */
	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario = nmUsuario;
	}
	/**
	 * @return the senhaUsuario
	 */
	public String getSenhaUsuario() {
		return senhaUsuario;
	}
	/**
	 * @param senhaUsuario the senhaUsuario to set
	 */
	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}
	/**
	 * @return the emailUsuario
	 */
	public String getEmailUsuario() {
		return emailUsuario;
	}
	/**
	 * @param emailUsuario the emailUsuario to set
	 */
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

}
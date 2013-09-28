/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sge.bean;

public class FrequenciaBean {
    private int codAula;
    private String alunoPresente;
    private String obs;
    
    
	/**
	 * @return the codAula
	 */
	public int getCodAula() {
		return codAula;
	}
	/**
	 * @param codAula the codAula to set
	 */
	public void setCodAula(int codAula) {
		this.codAula = codAula;
	}
	/**
	 * @return the alunoPresente
	 */
	public String getAlunoPresente() {
		return alunoPresente;
	}
	/**
	 * @param alunoPresente the alunoPresente to set
	 */
	public void setAlunoPresente(String alunoPresente) {
		this.alunoPresente = alunoPresente;
	}
	/**
	 * @return the obs
	 */
	public String getObs() {
		return obs;
	}
	/**
	 * @param obs the obs to set
	 */
	public void setObs(String obs) {
		this.obs = obs;
	}

    
}
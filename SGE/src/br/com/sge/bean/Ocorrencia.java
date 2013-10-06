package br.com.sge.bean;

import java.util.Date;

public class Ocorrencia {
	
	
    private int codOcorrencia;
    private int rmAluno;
    private String descOcorrencia;
    private Date dtOcorrencia;
    
    
    
	public int getCodOcorrencia() {
		return codOcorrencia;
	}
	public void setCodOcorrencia(int codOcorrencia) {
		this.codOcorrencia = codOcorrencia;
	}
	public int getRmAluno() {
		return rmAluno;
	}
	public void setRmAluno(int rmAluno) {
		this.rmAluno = rmAluno;
	}
	public String getDescOcorrencia() {
		return descOcorrencia;
	}
	public void setDescOcorrencia(String descOcorrencia) {
		this.descOcorrencia = descOcorrencia;
	}
	public Date getDtOcorrencia() {
		return dtOcorrencia;
	}
	public void setDtOcorrencia(Date dtOcorrencia) {
		this.dtOcorrencia = dtOcorrencia;
	}
    
    
	   
}
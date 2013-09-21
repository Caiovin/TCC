package br.com.sge.bean;

/**
 *
 * @author QBEX
 */
public class Plano {
    private int codPlano;
    private int codFuncionario;
    private String Etapa;
    private String Disciplina;
    private int Ano;
    private String Objetivos;
    private String Conteudo;
    private String Procedimentos;
    private String Recursos;
    private String Avaliacao;
    private String Bibliografia;

    public int getCodPlano() {
        return codPlano;
    }

    public void setCodPlano(int codPlano) {
        this.codPlano = codPlano;
    }    

    public int getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(int codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public String getEtapa() {
        return Etapa;
    }

    public void setEtapa(String Etapa) {
        this.Etapa = Etapa;
    }

    public String getDisciplina() {
        return Disciplina;
    }

    public void setDisciplina(String Disciplina) {
        this.Disciplina = Disciplina;
    }

    public int getAno() {
        return Ano;
    }

    public void setAno(int Ano) {
        this.Ano = Ano;
    }

    public String getObjetivos() {
        return Objetivos;
    }

    public void setObjetivos(String Objetivos) {
        this.Objetivos = Objetivos;
    }

    public String getConteudo() {
        return Conteudo;
    }

    public void setConteudo(String Conteudo) {
        this.Conteudo = Conteudo;
    }

    public String getProcedimentos() {
        return Procedimentos;
    }

    public void setProcedimentos(String Procedimentos) {
        this.Procedimentos = Procedimentos;
    }

    public String getRecursos() {
        return Recursos;
    }

    public void setRecursos(String Recursos) {
        this.Recursos = Recursos;
    }

    public String getAvaliacao() {
        return Avaliacao;
    }

    public void setAvaliacao(String Avaliacao) {
        this.Avaliacao = Avaliacao;
    }

    public String getBibliografia() {
        return Bibliografia;
    }

    public void setBibliografia(String Bibliografia) {
        this.Bibliografia = Bibliografia;
    }
    
}

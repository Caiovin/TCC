package br.com.sge.bean;

public class Funcionario {
    private int codFuncionario;
    private String Nome;
    private String sexo;
    private String Endereco;
    private String Cidade;
    private String RG;
    private String CPF;
    private String FoneCome1;
    private String FoneCome2;
    private String FoneResi;
    private String Celular;
    private String Leciona;
    private String Graduacao;
    private String Email;

    public int getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(int codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getFoneCome1() {
        return FoneCome1;
    }

    public void setFoneCome1(String FoneCome1) {
        this.FoneCome1 = FoneCome1;
    }

    public String getFoneCome2() {
        return FoneCome2;
    }

    public void setFoneCome2(String FoneCome2) {
        this.FoneCome2 = FoneCome2;
    }

    public String getFoneResi() {
        return FoneResi;
    }

    public void setFoneResi(String FoneResi) {
        this.FoneResi = FoneResi;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public String getLeciona() {
        return Leciona;
    }

    public void setLeciona(String Leciona) {
        this.Leciona = Leciona;
    }

    public String getGraduacao() {
        return Graduacao;
    }

    public void setGraduacao(String Graduacao) {
        this.Graduacao = Graduacao;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
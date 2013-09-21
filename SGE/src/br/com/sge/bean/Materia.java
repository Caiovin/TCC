/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sge.bean;

/**
 *
 * @author QBEX
 */
public class Materia {
    private int codMateria;
    private String Professor;
    private String nome;
    private String carga;

    public int getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(int codMateria) {
        this.codMateria = codMateria;
    }

    public String getProfessor() {
        return Professor;
    }

    public void setProfessor(String Professor) {
        this.Professor = Professor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCarga() {
        return carga;
    }

    public void setCarga(String carga) {
        this.carga = carga;
    }
 
}

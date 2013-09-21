/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sge.bean;

/**
 *
 * @author QBEX
 */
public class Serie {
    private int codSerie;
    private String Nome;
    private int codTurma;

    public int getCodSerie() {
        return codSerie;
    }

    public void setCodSerie(int codSerie) {
        this.codSerie = codSerie;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public int getCodTurma() {
        return codTurma;
    }

    public void setCodTurma(int codTurma) {
        this.codTurma = codTurma;
    }

}
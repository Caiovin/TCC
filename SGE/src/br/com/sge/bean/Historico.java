/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sge.bean;

/**
 *
 * @author QBEX
 */
public class Historico {
    private int codHistorico;
    private int codMateria;
    private int codSerie;
    private String frequencia;
    private float nota;
    
    public int getCodHistorico(){
        return codHistorico;
    }
    
    public void setCodHistorico(int codHistorico){
        this.codHistorico = codHistorico;
    }

    public int getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(int codMateria) {
        this.codMateria = codMateria;
    }

    public int getCodSerie() {
        return codSerie;
    }

    public void setCodSerie(int codSerie) {
        this.codSerie = codSerie;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

}
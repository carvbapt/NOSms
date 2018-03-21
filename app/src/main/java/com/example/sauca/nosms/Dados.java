package com.example.sauca.nosms;


/*
 * Created by Sauca on 28-01-2018.
 */

import java.util.Date;

public class Dados {

    //Campos
    private long ID;
    private char Genero;
    private int Ordem;
    private Date Dta;
    private char Estado;
    private char Resultado;
    private char Responsabilidade;
    private String Descricao;

    public Dados(){}

    public Dados(long ID, char genero, int ordem, Date dta, char estado, char resultado, char responsabilidade, String descricao) {
        this.ID = ID;
        this.Genero = genero;
        this.Ordem = ordem;
        this.Dta = dta;
        this.Estado = estado;
        this.Resultado = resultado;
        this.Responsabilidade = responsabilidade;
        this.Descricao = descricao;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getID() {
        return this.ID;
    }

    public void setGenero(char genero) {
        this.Genero = genero;
    }

    public char  getGenero() {
        return this.Genero;
    }

    public void setOrdem(int ordem) {
        this.Ordem = ordem;
    }

    public int getOrdem() {
        return this.Ordem;
    }

    public void setDta(Date dta) {
        this.Dta = dta;
    }

    public Date getDta() {
        return this.Dta;
    }

    public void setEstado(char estado) {
        this.Estado = estado;
    }

    public char getEstado() {
        return this.Estado;
    }

    public void setResultado(char resultado) {
        this.Resultado = resultado;
    }

    public char getResultado() {
        return this.Resultado;
    }

    public void setResponsabilidade(char responsabilidade) { this.Responsabilidade = responsabilidade; }

    public char getResponsabilidade() {
        return this.Responsabilidade;
    }

    public void setDescricao(String descricao) {
        this.Descricao = descricao;
    }

    public String getDescricao() {
        return this.Descricao;
    }
}

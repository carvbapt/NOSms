package com.example.sauca.nosms;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/*
 * Created by Sauca on 28-01-2018.
 */

public class Dados extends AppCompatActivity{

    public static ArrayList<String> SCliente=  new ArrayList<>();
    static {
        SCliente=new ArrayList<> (  );
        SCliente.add ("EQUIPAMENTO DESLIGADO");
        SCliente.add ("EQUIPAMENTO LIGADO");
    }

    public static ArrayList<String > ICliente= new ArrayList<>();
    static {
        ICliente=new ArrayList<> (  );
        ICliente.add ("CLIENTE AUSENTE");
        ICliente.add ("SEM CONDICOES");
        ICliente.add ("FALTA EQUIPAMENTO");
    }

    public static ArrayList<String > SNos= new ArrayList<>();
    static {
        SNos = new ArrayList<> ( );
        SNos.add ("AVARIA COMUM");
        SNos.add ("SUBSTITUICAO EQUIPAMENTO");
        SNos.add ("CONFIGURACOES EQUIPAMENTO");
        SNos.add ("RECONFIGURACOES EQUIPAMENTO");
        SNos.add ("INSTALACAO EQUIPAMENTO");
        SNos.add ("ATUALIZACAO EQUIPAMENTO");
        SNos.add ("TESTES EQUIPAMENTO");
        SNos.add ("TESTES ACESSO");
        SNos.add ("RECOLHA EQUIPAMENTO");
        SNos.add ("RETIFICACAO DE LIGACOES");
    }

    public static ArrayList<String> INos= new ArrayList<>();
    static {
        INos = new ArrayList<> ( );
        INos.add ( "MORADA INCORRECTA" );
        INos.add ( "LOCAL SEM COBERTURA" );
        INos.add ( "MAUS VALORES LINHA" );
    }

    public static ArrayList<String > IParceiros= new ArrayList<>();
    static {
        IParceiros = new ArrayList<> ( );
        IParceiros.add ( "LEVOU EQUIPAMENTO ERRADO" );
        IParceiros.add ( "EQUIPAMENTO ESGOTADO" );
    }

    public static ArrayList<String > Motivo= new ArrayList<>();
    static {
        Motivo = new ArrayList<> ( );
        Motivo.add ( "ACIDENTE" );
        Motivo.add ( "MATERIAL" );
        Motivo.add ( "POLICIA" );
        Motivo.add ( "TRANSITO" );
    }
}

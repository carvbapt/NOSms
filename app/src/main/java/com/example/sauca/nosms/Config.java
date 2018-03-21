package com.example.sauca.nosms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Config extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbarc;
    Intent intent;
    ImageButton ib_Back,ib_Coperador,ib_DClear,ib_DReload,ib_DList;
    EditText et_Coperador;
    List te;

    private DadosDB db_dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_config );

        toolbarc = findViewById ( R.id.toolbarC );
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar ( toolbarc );

        te= new ArrayList (  );
        //dados= new Dados (this);

        ib_Back=findViewById ( R.id.IB_Back );
        ib_Coperador=findViewById ( R.id.IB_COperador );
        ib_DClear=findViewById ( R.id.IB_DClear );
        ib_DReload=findViewById ( R.id.IB_DReload );
        ib_DList=findViewById ( R.id.IB_DList );
        et_Coperador=findViewById ( R.id.ET_Operadora );

        ib_Back.setOnClickListener ( this );
        ib_Coperador.setOnClickListener ( this );
    }

    @Override
    public void onClick(View view) {
        if(view==findViewById ( R.id.IB_Back )){
            intent = new Intent(this, Main.class);
            startActivity(intent);
            finish ();
            //moveTaskToBack ( true );
        }else if(view==findViewById ( R.id.IB_COperador )){
            Toast.makeText ( this,"CARREGAR CONTACTO",Toast.LENGTH_LONG ).show ();
        }else if(view==findViewById ( R.id.IB_DClear )){
//            dados.dClear ( this );
//            Log.d ( "Load",Integer.toString ( dados.vResultado )+" " + dados.vResponsabilidade+" " +dados.vMotivo);
            Toast.makeText ( this,"LIMPAR",Toast.LENGTH_LONG ).show ();
        }else if(view==findViewById ( R.id.IB_DReload )){
//            dados.dSave ( this,4,"C","TESTE" );
//            dados.getValue ( this );
//            Log.d ( "Load",Integer.toString ( dados.vResultado )+" " + dados.vResponsabilidade+" " +dados.vMotivo);
//            dados.dSave ( this,3,"N","TESTE2" );
//            dados.getValue ( this );
//            Log.d ( "Load",Integer.toString ( dados.vResultado )+" " + dados.vResponsabilidade+" " +dados.vMotivo);
//            dados.dSave ( this,3,"P","TESTE3" );
//            dados.getValue ( this );
//            Log.d ( "Load",Integer.toString ( dados.vResultado )+" " + dados.vResponsabilidade+" " +dados.vMotivo);

            Toast.makeText(this,getResources().getString(R.string.saved),Toast.LENGTH_LONG).show();
        }else if(view==findViewById ( R.id.IB_DList )){
            //dados.getValue ( this );
//            te=dados.getAllValues ( this );
//            for(int i=0;i<= dados.dCount ();i++){
//                Log.d ( "Load",Integer.toString ( dados.dCount ()) +" "+  te.get ( i ).toString () );
//            }

            //Toast.makeText ( this,"LISTAR " + dados.getValue ( this),Toast.LENGTH_LONG ).show ();
        }
    }
}

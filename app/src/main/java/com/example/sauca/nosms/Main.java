package com.example.sauca.nosms;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class Main extends AppCompatActivity implements View.OnClickListener  {

    // Variables definitions
    Intent intent;
    Toolbar toolbar;
    ImageButton ib_Conf,ib_Apagar;
    ImageView iv_Fundo;
    InputMethodManager imm;

    RadioGroup rgb_Timer;
    RadioButton rb_Wo, rb_Task,rb_Previsao,rb_Inicio,rb_Fim;
    EditText et_Numero;

    FrameLayout fl_Frag;

    Button bt_Sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        // Find the toolbar view inside the activity layout
        toolbar = findViewById ( R.id.toolbarM );
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar ( toolbar );

        ib_Conf = findViewById ( R.id.IB_Conf );
        iv_Fundo=findViewById ( R.id.IV_Fundo );
        rb_Wo = findViewById ( R.id.RB_Wo );
        rb_Task = findViewById ( R.id.RB_Task );
        imm = (InputMethodManager) this.getSystemService ( Context.INPUT_METHOD_SERVICE );

        et_Numero = findViewById ( R.id.ET_Numero );
        ib_Apagar=findViewById ( R.id.IB_Apagar );
        fl_Frag = findViewById ( R.id.FL_Fragment );
        rgb_Timer=findViewById ( R.id.RBG_Timer );
        rb_Previsao=findViewById ( R.id.RB_Previsao );
        rb_Inicio=findViewById ( R.id.RB_Inicio );
        rb_Fim=findViewById ( R.id.RB_Fim );

        bt_Sair = findViewById ( R.id.BT_Sair );

        iv_Fundo.setVisibility ( View.VISIBLE );
        fl_Frag.setVisibility ( View.INVISIBLE );
        rgb_Timer.setVisibility ( View.INVISIBLE );





        if ( savedInstanceState == null) {
            getSupportFragmentManager ( ).beginTransaction ( ).add ( R.id.FL_Fragment, new Iniciar ( ) ).commit ( );
        }

        ib_Conf.setOnClickListener ( this );
        rb_Wo.setOnClickListener ( this );
        rb_Task.setOnClickListener ( this );

        et_Numero.requestFocus ( );
        imm.showSoftInput(et_Numero, InputMethodManager.SHOW_IMPLICIT);
        imm.toggleSoftInput ( InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY );

        et_Numero.addTextChangedListener ( new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if ( (rb_Wo.isChecked ()  && et_Numero.getText ( ).toString ( ).length ( ) >=5 ) || (rb_Task.isChecked () && et_Numero.getText ( ).toString ( ).length ( ) >=6 ) )
                {
                        imm.hideSoftInputFromWindow ( et_Numero.getWindowToken ( ), 0 );
                        rgb_Timer.setVisibility ( View.VISIBLE );
                        fl_Frag.setVisibility ( View.VISIBLE );
                        iv_Fundo.setVisibility ( View.INVISIBLE );
                        rb_Inicio.performClick ();
                }
            }
        } );

        rb_Previsao.setOnClickListener ( this );
        rb_Inicio.setOnClickListener ( this );
        rb_Fim.setOnClickListener ( this );
        bt_Sair.setOnClickListener ( this );
    }

    @Override
    public void onClick(View view) {

        if (view == findViewById ( R.id.IB_Conf )) {
            imm.hideSoftInputFromWindow ( et_Numero.getWindowToken ( ), 0 );
            intent = new Intent(this, Config.class);
            startActivity(intent);
        }else if(view==findViewById ( R.id.IB_Apagar )){
            et_Numero.setText ( "" );
            et_Numero.requestFocus ();
            imm.showSoftInput ( et_Numero,InputMethodManager.SHOW_IMPLICIT );
            fl_Frag.setVisibility ( View.INVISIBLE );
            rgb_Timer.setVisibility ( View.INVISIBLE );
            iv_Fundo.setVisibility ( View.VISIBLE );
        } else if (view == findViewById ( R.id.RB_Wo ) || view==findViewById ( R.id.RB_Task )) {
            ib_Apagar.performClick ();
            if((rb_Wo.isChecked ()  && et_Numero.getText ( ).toString ( ).length ( ) >=4 ) || (rb_Task.isChecked () && et_Numero.getText ( ).toString ( ).length ( ) >=6 )){
                getSupportFragmentManager ( ).beginTransaction ( ).replace ( R.id.FL_Fragment, new Iniciar ( ) ).commit ( );
            }
        }else if(view ==findViewById ( R.id.RB_Previsao )||view==findViewById ( R.id.RB_Inicio )||view==findViewById ( R.id.RB_Fim )) {
            imm.hideSoftInputFromWindow ( et_Numero.getWindowToken ( ), 0 );
            et_Numero.clearFocus ();
            fl_Frag.setVisibility ( View.VISIBLE );
            if(view==findViewById ( R.id.RB_Previsao ))
                getSupportFragmentManager ( ).beginTransaction ( ).replace ( R.id.FL_Fragment, new Previsao ( ) ).commit ( );
            else if(view==findViewById ( R.id.RB_Inicio ))
                getSupportFragmentManager ( ).beginTransaction ( ).replace ( R.id.FL_Fragment, new Iniciar ( ) ).commit ( );
            else
                getSupportFragmentManager ( ).beginTransaction ( ).replace ( R.id.FL_Fragment, new Fechar ( ) ).commit ( );
        } else if (view == findViewById ( R.id.BT_Sair )) {
            moveTaskToBack ( true );
            finish ( );
            System.exit ( 0 );
        }
    }
}


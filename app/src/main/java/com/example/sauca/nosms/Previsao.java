package com.example.sauca.nosms;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Previsao extends Fragment  {

    EditText et_Hora,et_Minuto;
    Main m_Previsao;
    InputMethodManager imm_P;
    Button bt_EnviaP;
    ImageButton ib_ApagarP;

    String str_P;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        // Inflate the layout for this fragment
        View view = inflater.inflate ( R.layout.fragment_previsao, container, false );

        m_Previsao=(Main)getActivity ();
        imm_P = (InputMethodManager) getActivity ().getSystemService ( Context.INPUT_METHOD_SERVICE );

        bt_EnviaP=view.findViewById ( R.id.BT_EnviaP );
        et_Hora=view.findViewById ( R.id.ET_Hora );
        et_Minuto=view.findViewById ( R.id.ET_Minuto );
        ib_ApagarP=view.findViewById ( R.id.IB_ApagarP );

        str_P="";
        if(m_Previsao.rb_Wo.isChecked () && !m_Previsao.et_Numero.getText ().toString ().equals ("")) {
            str_P= getText ( R.string.nos ) + " " + getText ( R.string.wo ) + " " + m_Previsao.et_Numero.getText ( ) + " 1";

        }else if(m_Previsao.rb_Task.isChecked () && !m_Previsao.et_Numero.getText ().toString ().equals ( "" )) {
            str_P= getText ( R.string.nos ) + " " + getText ( R.string.task ) + " " + m_Previsao.et_Numero.getText ( ) + " 1";
        }

        bt_EnviaP.setVisibility ( View.VISIBLE );

        et_Hora.requestFocus ();
        et_Hora.setText ( "" );

        et_Hora.addTextChangedListener ( new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if ( et_Hora.getText ( ).toString ( ).length ( ) ==2)
                    et_Minuto.requestFocus ( );
                    et_Minuto.setText ( "" );
            }
        } );

        et_Minuto.addTextChangedListener ( new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(et_Minuto.getText ().toString ().length ()==2){
                    imm_P.hideSoftInputFromWindow ( et_Minuto.getWindowToken ( ), 0 );
                    et_Minuto.clearFocus ();
                }
            }
        } );


        ib_ApagarP.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                    et_Minuto.setText ( "" );
                    et_Hora.setText ( "" );
                    et_Hora.requestFocus ();
                    imm_P.showSoftInput ( et_Hora,InputMethodManager.SHOW_IMPLICIT );
            }
        } );

        bt_EnviaP. setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                str_P= str_P + " " + et_Hora.getText () + getString ( R.string.sep ) + et_Minuto.getText ();
                Toast.makeText ( getContext () ,str_P,Toast.LENGTH_LONG ).show ();
            }
        } );

        //et_Hora.setOnClickListener ( this );
        //et_Minuto.setOnClickListener ( this );

        return view;
    }
}

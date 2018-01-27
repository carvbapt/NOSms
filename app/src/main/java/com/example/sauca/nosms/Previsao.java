package com.example.sauca.nosms;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import static android.view.KeyEvent.KEYCODE_ENTER;


/**
 * A simple {@link Fragment} subclass.
 */
public class Previsao extends Fragment implements View.OnClickListener {

    View view;
    EditText et_Hora, et_Minuto, et_Outro;
    Main P_Main;
    InputMethodManager imm_P;
    Button bt_EnviaP;
    ImageButton ib_ApagarP;

    Spinner sp_Motivo;
    ArrayAdapter aa_Motivo;
    String str_P;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate ( R.layout.fragment_previsao, container, false );

        P_Main = (Main) getActivity ( );
        imm_P = (InputMethodManager) getActivity ( ).getSystemService ( Context.INPUT_METHOD_SERVICE );

        bt_EnviaP = view.findViewById ( R.id.BT_EnviaP );
        et_Hora = view.findViewById ( R.id.ET_Hora );
        et_Minuto = view.findViewById ( R.id.ET_Minuto );
        ib_ApagarP = view.findViewById ( R.id.IB_ApagarP );
        sp_Motivo = view.findViewById ( R.id.SP_Motivo );
        et_Outro = view.findViewById ( R.id.ET_Outro );
        bt_EnviaP.setVisibility ( View.INVISIBLE );

        et_Outro.setEnabled ( false );
        et_Outro.setFocusable ( false );

        aa_Motivo = ArrayAdapter.createFromResource ( getContext ( ), R.array.amotivo, android.R.layout.simple_spinner_item );
        sp_Motivo.setAdapter ( aa_Motivo );

        et_Hora.requestFocus ( );
        imm_P.toggleSoftInput ( InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY );

        et_Hora.addTextChangedListener ( new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (et_Hora.getText ( ).toString ( ).length ( ) == 2 && Integer.parseInt ( et_Hora.getText ( ).toString ( ) ) <= 23) {
                    et_Minuto.requestFocus ( );
                    et_Minuto.setText ( "" );
                } else if (et_Hora.getText ( ).toString ( ).length ( ) == 2 && Integer.parseInt ( et_Hora.getText ( ).toString ( ) ) > 23) {
                    ib_ApagarP.performClick ( );
                }
            }
        } );

        et_Minuto.addTextChangedListener ( new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (et_Minuto.getText ( ).toString ( ).length ( ) == 2 && Integer.parseInt ( et_Minuto.getText ( ).toString ( ) ) <= 59) {
                    imm_P.hideSoftInputFromWindow ( et_Minuto.getWindowToken ( ), 0 );
                    et_Minuto.clearFocus ( );
                    sp_Motivo.performClick ( );
                } else if (et_Minuto.getText ( ).toString ( ).length ( ) == 2 && Integer.parseInt ( et_Minuto.getText ( ).toString ( ) ) > 59) {
                    ib_ApagarP.performClick ( );
                }
            }
        } );

        sp_Motivo.setOnItemSelectedListener ( new AdapterView.OnItemSelectedListener ( ) {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(et_Minuto.getText ( ).toString ( ).length ( ) == 2) {
                    if(sp_Motivo.getSelectedItem ().toString ().equals ( getString ( R.string.outro))){
                        et_Outro.setEnabled ( true );
                        et_Outro.setFocusable ( true );
                        et_Outro.setFocusableInTouchMode(true);
                        et_Outro.setText ( "" );
                        et_Outro.requestFocus ();
                        imm_P.toggleSoftInput ( InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY );
                    }else
                        bt_EnviaP.setVisibility ( View.VISIBLE );
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        } );

        et_Outro.setOnKeyListener ( new View.OnKeyListener ( ) {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                //if((keyEvent != null && (keyEvent.getKeyCode() == KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)){
                if(keyEvent.getKeyCode() == KEYCODE_ENTER){
                    imm_P.hideSoftInputFromWindow ( et_Outro.getWindowToken ( ), 0 );
                    bt_EnviaP.setVisibility ( View.VISIBLE );
                    return true;
                }else {
                    return false;
                }
            }
        } );

        ib_ApagarP.setOnClickListener ( this );
        bt_EnviaP.setOnClickListener ( this );

        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId ( )) {
            case (R.id.IB_ApagarP):
                bt_EnviaP.setVisibility ( View.INVISIBLE );
                et_Outro.setEnabled ( false );
                et_Outro.setFocusable ( false );
                et_Outro.setText ( "" );
                sp_Motivo.setSelection ( 0 );
                et_Minuto.setText ( "" );
                et_Hora.setText ( "" );
                et_Hora.requestFocus ( );
                imm_P.toggleSoftInput ( InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY );
                break;
            case (R.id.BT_EnviaP):
                str_P = "";
                if (P_Main.rb_Wo.isChecked ( ) && !P_Main.et_Numero.getText ( ).toString ( ).equals ( "" )) {
                    str_P = getText ( R.string.codigo ) + " " + getText ( R.string.wo ) + " " + P_Main.et_Numero.getText ( ) + " 1 ";

                } else if (P_Main.rb_Task.isChecked ( ) && !P_Main.et_Numero.getText ( ).toString ( ).equals ( "" )) {
                    str_P = getText ( R.string.codigo ) + " " + getText ( R.string.task ) + " " + P_Main.et_Numero.getText ( ) + " 1 ";
                }

                if (sp_Motivo.getSelectedItem ( ).toString ( ).equals ( "" ) && et_Outro.getText ( ).toString ( ).equals ( "" )) {
                    Toast.makeText ( getContext ( ), getString ( R.string.selec ), Toast.LENGTH_LONG ).show ( );
                } else if (sp_Motivo.getSelectedItem ( ).toString ( ).equals (getString ( R.string.outro ) )) {
                    str_P = str_P + et_Hora.getText ( ) + getString ( R.string.sep ) + et_Minuto.getText ( ) + " " + et_Outro.getText ( ).toString ( ).toUpperCase ( );
                } else {
                    str_P = str_P + et_Hora.getText ( ) + getString ( R.string.sep ) + et_Minuto.getText ( ) + " " + sp_Motivo.getSelectedItem ( ).toString ( );
                }

                et_Outro.setEnabled ( true );
                Toast.makeText ( getContext ( ), str_P, Toast.LENGTH_LONG ).show ( );
                break;
        }
    }
}


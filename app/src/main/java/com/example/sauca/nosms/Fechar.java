package com.example.sauca.nosms;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fechar extends Fragment implements View.OnClickListener {

    View view;
    Main F_Main;
    Button bt_EnviaF;
    ImageButton ib_ApagarF;
    RadioGroup rbg_Responsabilidade;
    RadioButton rb_Sucesso,rb_Insucesso,rb_Cliente,rb_Nos,rb_Parceiro;
    EditText et_Resumo;
    InputMethodManager imm_F;

    String str_F;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate ( R.layout.fragment_fechar, container, false );

        F_Main = (Main)getActivity ();
        imm_F = (InputMethodManager) getActivity ( ).getSystemService ( Context.INPUT_METHOD_SERVICE );

        ib_ApagarF = view.findViewById ( R.id.IB_ApagarF );
        rb_Sucesso=view.findViewById ( R.id.RB_Sucesso );
        rb_Insucesso=view.findViewById ( R.id.RB_Insucesso );
        rbg_Responsabilidade=view.findViewById ( R.id.RBG_Responsabilidade );
        rb_Cliente=view.findViewById ( R.id.RB_Cliente );
        rb_Nos=view.findViewById ( R.id.RB_Nos );
        rb_Parceiro=view.findViewById ( R.id.RB_Parceiro );
        et_Resumo=view.findViewById ( R.id.ET_Resumo );
        bt_EnviaF=view.findViewById ( R.id.BT_EnviaF );

        rb_Parceiro.setVisibility ( View.INVISIBLE );
        bt_EnviaF.setVisibility ( View.INVISIBLE );
        et_Resumo.requestFocus ();
        imm_F.toggleSoftInput ( InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY );

        rb_Sucesso.setOnClickListener ( this );
        rb_Insucesso.setOnClickListener ( this );
        rb_Cliente.setOnClickListener ( this );
        rb_Nos.setOnClickListener ( this );
        rb_Parceiro.setOnClickListener ( this );
        et_Resumo.setOnEditorActionListener ( new TextView.OnEditorActionListener ( ) {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    bt_EnviaF.setVisibility ( View.VISIBLE );
                    imm_F.hideSoftInputFromWindow ( et_Resumo.getWindowToken ( ), 0 );
                    return true;
                }
                return false;
            }
        } );
        ib_ApagarF.setOnClickListener ( this );
        bt_EnviaF.setOnClickListener ( this );

        return view;
    }

    @Override
    public void onClick(View v) {

        if (v == view.findViewById ( R.id.IB_ApagarF)){
            rb_Sucesso.setChecked ( true );
            rb_Nos.setChecked ( true );
            et_Resumo.setText ( "" );
            et_Resumo.requestFocus ();
            imm_F.toggleSoftInput ( InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY );
        }else if (v == view.findViewById ( R.id.RB_Sucesso )){
            rb_Parceiro.setVisibility ( View.INVISIBLE );
        }else if(v == view.findViewById ( R.id.RB_Insucesso )) {
            rb_Parceiro.setVisibility ( View.VISIBLE );
        } else if(v==view.findViewById ( R.id.BT_EnviaF )){
            str_F="";
            if(F_Main.rb_Wo.isChecked () && !F_Main.et_Numero.getText ().toString ().equals ("")) {
                str_F= getText ( R.string.codigo ) + " " + getText ( R.string.wo ) + " " + F_Main.et_Numero.getText ( );
            }else if(F_Main.rb_Task.isChecked () && !F_Main.et_Numero.getText ().toString ().equals ( "" )) {
                str_F= getText ( R.string.codigo ) + " " + getText ( R.string.task ) + " " + F_Main.et_Numero.getText ( );
            }

            if(rb_Sucesso.isChecked ()){
                str_F=str_F+" 3 ";
            }else if(rb_Insucesso.isChecked ()){
                str_F=str_F+" 4 ";
            }

            if(rb_Cliente.isChecked ()){
                str_F=str_F + getString ( R.string.cli );
            }else if(rb_Nos.isChecked ()){
                str_F=str_F + getString ( R.string.n );
            }else if(rb_Parceiro.isChecked ()){
                str_F=str_F + getString ( R.string.p );
            }

            str_F=str_F+" "+et_Resumo.getText ().toString ().toUpperCase ();
            Toast.makeText ( getContext (), str_F, Toast.LENGTH_SHORT ).show ( );
        }
    }
}

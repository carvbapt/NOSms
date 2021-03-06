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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;

import static com.example.sauca.nosms.Dados_Ini.ICliente;
import static com.example.sauca.nosms.Dados_Ini.INos;
import static com.example.sauca.nosms.Dados_Ini.IParceiros;
import static com.example.sauca.nosms.Dados_Ini.SCliente;
import static com.example.sauca.nosms.Dados_Ini.SNos;

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
    Spinner sp_Resumo;
    ArrayAdapter<String> aa_Resumo;
    ArrayList<String> aa_Load ;
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
        sp_Resumo=view.findViewById ( R.id.SP_Resumo );

        rb_Parceiro.setVisibility ( View.INVISIBLE );
        bt_EnviaF.setVisibility ( View.INVISIBLE );
        et_Resumo.setEnabled ( false );
        et_Resumo.setFocusable ( false );
        //et_Resumo.requestFocus ();
        //imm_F.toggleSoftInput ( InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY );

        aa_Load=new ArrayList<> ( );
        aa_Load.add ( "1" );
        SL_Resumo ( 3 );
        sp_Resumo.performClick ( );


        sp_Resumo.setOnItemSelectedListener ( new AdapterView.OnItemSelectedListener ( ) {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                  if(sp_Resumo.getSelectedItem ().toString ().equals ( getString ( R.string.outro))){
                        et_Resumo.setEnabled ( true );
                        et_Resumo.setFocusable ( true );
                        et_Resumo.setFocusableInTouchMode(true);
                        et_Resumo.setText ( "" );
                        et_Resumo.requestFocus ();
                        imm_F.toggleSoftInput ( InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY );
                    }else
                        bt_EnviaF.setVisibility ( View.VISIBLE );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                imm_F.toggleSoftInput ( InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY );
                sp_Resumo.setSelection ( 0 );
            }
        } );

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

        rb_Sucesso.setOnClickListener ( this );
        rb_Insucesso.setOnClickListener ( this );
        rb_Cliente.setOnClickListener ( this );
        rb_Nos.setOnClickListener ( this );
        rb_Parceiro.setOnClickListener ( this );
        ib_ApagarF.setOnClickListener ( this );
        bt_EnviaF.setOnClickListener ( this );

        return view;
    }

    @Override
    public void onClick(View v) {

        if (v == view.findViewById ( R.id.IB_ApagarF)){
            rb_Sucesso.setChecked ( true );
            rb_Nos.setChecked ( true );
            et_Resumo.setEnabled ( false );
            et_Resumo.setFocusable ( false );
            et_Resumo.setText ( "" );
            sp_Resumo.setSelection ( 0 );
            sp_Resumo.performClick ();
        }else if (v == view.findViewById ( R.id.RB_Sucesso )){
            ib_ApagarF.performClick ();
            rb_Parceiro.setVisibility ( View.INVISIBLE );
            SL_Resumo ( 3 );
        }else if(v == view.findViewById ( R.id.RB_Insucesso )) {
            ib_ApagarF.performClick ();
            rb_Parceiro.setVisibility ( View.VISIBLE );
            SL_Resumo ( 4 );
        }else if((v!=view.findViewById ( R.id.BT_EnviaF )) && rb_Sucesso.isChecked () && (rb_Cliente.isChecked ()||rb_Nos.isChecked ()||rb_Parceiro.isChecked () )){
            SL_Resumo ( 3 );
            sp_Resumo.performClick ();
        }else if((v!=view.findViewById ( R.id.BT_EnviaF )) && rb_Insucesso.isChecked () && (rb_Cliente.isChecked ()||rb_Nos.isChecked ()||rb_Parceiro.isChecked () )){
            SL_Resumo ( 4 );
            sp_Resumo.performClick ();
        } else if(v==view.findViewById ( R.id.BT_EnviaF )) {
            if (sp_Resumo.getSelectedItem ( ).equals ( getString ( R.string.outro ) ))
                str_F = str_F + " " + et_Resumo.getText ( ).toString ( ).toUpperCase ( );
            else
                str_F = str_F + " " + sp_Resumo.getSelectedItem ( ).toString ( );
            Toast.makeText ( getContext ( ), str_F, Toast.LENGTH_SHORT ).show ( );
            aa_Load.clear ();
        }
    }

    public void SL_Resumo(int s){

        str_F = "";
        if (F_Main.rb_Wo.isChecked ( ) && !F_Main.et_Numero.getText ( ).toString ( ).equals ( "" )) {
            str_F = getText ( R.string.codigo ) + " " + getText ( R.string.wo ) + " " + F_Main.et_Numero.getText ( );
        } else if (F_Main.rb_Task.isChecked ( ) && !F_Main.et_Numero.getText ( ).toString ( ).equals ( "" )) {
            str_F = getText ( R.string.codigo ) + " " + getText ( R.string.task ) + " " + F_Main.et_Numero.getText ( );
        }

        if(aa_Load!= null && !aa_Load.isEmpty ()) {
            aa_Load.clear ( );
        }

        switch (s){
            case (3):
                str_F = str_F + " "+s+" ";
                if (rb_Cliente.isChecked ()) {
                    str_F = str_F + getString ( R.string.cli );
                    aa_Load= SCliente;
                } else if (rb_Nos.isChecked ()) {
                    str_F=str_F + getString ( R.string.n );
                    aa_Load= SNos;
                }
                break;
            case (4):
                str_F=str_F+ " "+s+" ";
                if(rb_Cliente.isChecked ()){
                    str_F=str_F + getString ( R.string.cli );
                    aa_Load= ICliente;
                }else if(rb_Nos.isChecked ()){
                    str_F=str_F + getString ( R.string.n );
                    aa_Load= INos;
                }else if(rb_Parceiro.isChecked ()){
                    str_F=str_F + getString ( R.string.p );
                    aa_Load= IParceiros;
                }
                break;
        }

        if (aa_Load == null) throw new AssertionError ( );
        Collections.sort (aa_Load);
        aa_Load.add ( getString ( R.string.outro ) );
        aa_Resumo = new ArrayAdapter<> ( getActivity ( ), android.R.layout.simple_list_item_1, aa_Load);
        sp_Resumo.setAdapter ( aa_Resumo );
    }
}

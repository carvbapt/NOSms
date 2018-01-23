package com.example.sauca.nosms;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Iniciar extends Fragment {

    TextView t;
    Button bt_EnviaI;
    Main m_Iniciar;

    String str_I;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate ( R.layout.fragment_iniciar, container, false );

        m_Iniciar = (Main)getActivity ();

        bt_EnviaI=view.findViewById ( R.id.BT_EnviaI );
        t= view.findViewById ( R.id.TV );
        t.setVisibility ( View.INVISIBLE );

        str_I="";
        if(m_Iniciar.rb_Wo.isChecked () && !m_Iniciar.et_Numero.getText ().toString ().equals ("")) {
            str_I= getText ( R.string.nos ) + " " + getText ( R.string.wo ) + " " + m_Iniciar.et_Numero.getText ( ) + " 1";
            bt_EnviaI.setVisibility ( View.VISIBLE );
        }else if(m_Iniciar.rb_Task.isChecked () && !m_Iniciar.et_Numero.getText ().toString ().equals ( "" )) {
            str_I= getText ( R.string.nos ) + " " + getText ( R.string.task ) + " " + m_Iniciar.et_Numero.getText ( ) + " 1";
            bt_EnviaI.setVisibility ( View.VISIBLE );
        }

        bt_EnviaI.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                if(v==view.findViewById ( R.id.BT_EnviaI )){
                    t.setText ( str_I );
                    t.setVisibility ( View.VISIBLE );
                    Toast.makeText ( getContext ( ),str_I , Toast.LENGTH_LONG ).show ( );
                }
            }
        } );

        return view;
    }



}

package com.example.sauca.nosms;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Iniciar extends Fragment {

    Button bt_EnviaI;
    Main I_Main;

    String str_I;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate ( R.layout.fragment_iniciar, container, false );

        I_Main = (Main)getActivity ();

        bt_EnviaI=view.findViewById ( R.id.BT_EnviaI );

        str_I="";
        if(I_Main.rb_Wo.isChecked () && !I_Main.et_Numero.getText ().toString ().equals ("")) {
            str_I= getText ( R.string.codigo ) + " " + getText ( R.string.wo ) + " " + I_Main.et_Numero.getText ( ) + " 2";
            bt_EnviaI.setVisibility ( View.VISIBLE );
        }else if(I_Main.rb_Task.isChecked () && !I_Main.et_Numero.getText ().toString ().equals ( "" )) {
            str_I= getText ( R.string.codigo ) + " " + getText ( R.string.task ) + " " + I_Main.et_Numero.getText ( ) + " 2";
            bt_EnviaI.setVisibility ( View.VISIBLE );
        }

        bt_EnviaI.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                if(v==view.findViewById ( R.id.BT_EnviaI ))
                    Toast.makeText ( getContext ( ),str_I , Toast.LENGTH_LONG ).show ( );
            }
        } );

        return view;
    }



}

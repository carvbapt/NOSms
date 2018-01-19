package com.example.sauca.nosms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Main extends AppCompatActivity implements View.OnClickListener  {

    // Variables definitions
    Toolbar toolbar;

    RadioGroup rbg_Gen;
    RadioButton rb_Wo,rb_Task;
    ImageButton ib_Conf;
    Button bt_Sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        // Find the toolbar view inside the activity layout
        toolbar = findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        ib_Conf=findViewById ( R.id.IB_Conf );
        rb_Wo=findViewById ( R.id.RB_Wo );
        rb_Task=findViewById ( R.id.RB_Task );
        rbg_Gen=findViewById ( R.id.RBG_Genero );
        bt_Sair= findViewById ( R.id.BT_Sair );

        if(rbg_Gen.getCheckedRadioButtonId ()==R.id.RB_Wo)
            Toast.makeText ( this,getText (R.string.nos) + " " + getText (R.string.wo ),Toast.LENGTH_LONG ).show ();
        else
            Toast.makeText ( this,getText (R.string.nos) + " " + getText ( R.string.task ),Toast.LENGTH_LONG ).show ();

        ib_Conf.setOnClickListener ( this );
        rb_Wo.setOnClickListener ( this );
        rb_Task.setOnClickListener ( this );
        rbg_Gen.setOnClickListener ( this );
        bt_Sair.setOnClickListener ( this );
    }

    @Override
    public void onClick(View view) {

        if(view==findViewById ( R.id.RB_Wo ))
                Toast.makeText ( this,getText (R.string.nos) + " " + getText (R.string.wo) ,Toast.LENGTH_LONG ).show ();
        else if(view==findViewById ( R.id.RB_Task ))
                Toast.makeText ( this,getText (R.string.nos) + " " + getText ( R.string.task ),Toast.LENGTH_LONG ).show ();
        else if(view==findViewById ( R.id.IB_Conf )){
            Toast.makeText ( this,getText ( R.string.conf ),Toast.LENGTH_LONG ).show ();
        }else if(view == findViewById(R.id.BT_Sair)){
            moveTaskToBack(true);
            finish();
            System.exit(0);
        }
    }
}

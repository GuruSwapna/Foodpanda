package com.example.enchanterswapna.foodpanda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class Checkoutact extends AppCompatActivity {

    Button btndet;
    TextView txtvcst;
    RadioButton rb1,rb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkoutact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intp=getIntent();
         final String fnlcst=intp.getStringExtra("tlcost");

        btndet=(Button)findViewById(R.id.btndetails);
        txtvcst=(TextView)findViewById(R.id.tcst);
        rb1=(RadioButton)findViewById(R.id.rfpanda);
        rb2=(RadioButton)findViewById(R.id.rfpick);

        txtvcst.setText(fnlcst);
        btndet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rb1.isChecked()) {
                    Intent intent4 = new Intent(Checkoutact.this, Checkoutfinal.class);
                    intent4.putExtra("flcost", fnlcst);
                    startActivity(intent4);
                }
                else if(rb2.isChecked()){
                    Intent intent4 = new Intent(Checkoutact.this, paymentact.class);
                    intent4.putExtra("flcost", fnlcst);
                    startActivity(intent4);
                }
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);

    }

}

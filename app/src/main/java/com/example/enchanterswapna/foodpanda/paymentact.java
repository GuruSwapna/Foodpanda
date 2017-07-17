package com.example.enchanterswapna.foodpanda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class paymentact extends AppCompatActivity {

    TextView tolcost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tolcost=(TextView)findViewById(R.id.tcst);
        Intent int12=getIntent();
        String totalcost=int12.getStringExtra("ttlcost");
        tolcost.setText(totalcost);

    }
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);

    }

}

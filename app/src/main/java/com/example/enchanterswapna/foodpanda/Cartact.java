package com.example.enchanterswapna.foodpanda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Cartact extends AppCompatActivity {

    TextView txcname,txccost,txcnum,txcval;
    String fdnameitem,fdnumberitem,fdcostitem,fdgname;
    ImageButton fabbuttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fabbuttn=(ImageButton) findViewById(R.id.fabubtn);
        txcname=(TextView)findViewById(R.id.tmnname);
        txccost=(TextView)findViewById(R.id.tmnnum);
        txcnum=(TextView)findViewById(R.id.tmncost);
        txcval=(TextView)findViewById(R.id.tmnvalue);

        Intent intent = getIntent();
        fdnameitem = intent.getStringExtra("fdname");
       fdnumberitem = intent.getStringExtra("fdnumber");
       fdcostitem =intent.getStringExtra("fdcost");
        fdgname=intent.getStringExtra("gname");
        setTitle(fdgname);
        txcname.setText(fdnameitem);
        txccost.setText(fdcostitem);
        txcnum.setText(fdnumberitem);
        txcval.setText(fdnumberitem);
        fabbuttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent10=new Intent(Cartact.this,Basketact.class);
                intent10.putExtra("finame",fdnameitem);
                intent10.putExtra("ficost",fdcostitem);
                intent10.putExtra("finumb",fdnumberitem);
                startActivity(intent10);
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);

    }

}

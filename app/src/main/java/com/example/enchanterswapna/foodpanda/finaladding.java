package com.example.enchanterswapna.foodpanda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class finaladding extends AppCompatActivity {

    TextView txnum,txcst,txname,txcst1,tbasic;
    Button bsub,badd,bbas;
    int sub;
    String dgname,dgcost,dgmname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finaladding);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        bsub=(Button)findViewById(R.id.btnsub);
        badd=(Button)findViewById(R.id.btnadd);
        bbas=(Button)findViewById(R.id.addbas);
        txnum=(TextView)findViewById(R.id.txnumber);
        txcst=(TextView)findViewById(R.id.tvcost);
        txcst1=(TextView)findViewById(R.id.tvcost1);
        txname=(TextView)findViewById(R.id.typename);
        tbasic=(TextView)findViewById(R.id.txtv10);

        dgname= getIntent().getStringExtra("ptypename");
        dgcost=getIntent().getStringExtra("pprice");
        dgmname=getIntent().getStringExtra("pname");
        setTitle(dgmname);

        txname.setText(dgname);
        txcst.setText(dgcost);
        tbasic.setText(dgcost);

        bsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sub=Integer.parseInt(txnum.getText().toString());
                sub--;
                if(sub>0) {
                    txnum.setText(String.valueOf(sub));
                }
                String fct=txnum.getText().toString();
                String fcost=txcst.getText().toString();
                int a=Integer.parseInt(fct);
                int b=Integer.parseInt(fcost);
                txcst.setVisibility(View.INVISIBLE);
                int c=a*b;
                String res=String.valueOf(c);
                txcst1.setText(res);
            }
        });

        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sub=Integer.parseInt(txnum.getText().toString());
                sub++;
                txnum.setText(String.valueOf(sub));
                String fct=txnum.getText().toString();
                String fcost=txcst.getText().toString();
                int a=Integer.parseInt(fct);
                int b=Integer.parseInt(fcost);
                txcst.setVisibility(View.INVISIBLE);
                int c=a*b;
                String res=String.valueOf(c);
                txcst1.setText(res);
            }
        });
        bbas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent int1=new Intent(finaladding.this,Cartact.class);
                String abcname=txname.getText().toString();
                String abcnum=txnum.getText().toString();
                String abccost=txcst.getText().toString();
                int1.putExtra("gname",dgmname);
                int1.putExtra("fdname",abcname);
                int1.putExtra("fdnumber",abcnum);
                int1.putExtra("fdcost",abccost);
                startActivity(int1);

            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);

    }


}

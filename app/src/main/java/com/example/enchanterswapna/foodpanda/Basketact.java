package com.example.enchanterswapna.foodpanda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Basketact extends AppCompatActivity {

    String strname,strcost,strnum;
    TextView tvnumb,tvcost,tvnme,tvcst2,tvc1,tvc2,tvc3,tvc4,tvfinal,tedit,tadditm;
    Button bngo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basketact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent=getIntent();
        strname=intent.getStringExtra("finame");
        strcost=intent.getStringExtra("ficost");
        strnum=intent.getStringExtra("finumb");
        bngo=(Button)findViewById(R.id.btngodp);
        tedit=(TextView)findViewById(R.id.txedito);
        tadditm=(TextView)findViewById(R.id.txadditem);
        tvnumb=(TextView)findViewById(R.id.textnumb);
        tvcost=(TextView)findViewById(R.id.txocos);
        tvnme=(TextView)findViewById(R.id.txonam);
        tvcst2=(TextView)findViewById(R.id.twcost);
        tvc1=(TextView)findViewById(R.id.textView5);
        tvc2=(TextView)findViewById(R.id.ftcost3);
        tvc3=(TextView)findViewById(R.id.ftcost4);
        tvc4=(TextView)findViewById(R.id.ftcost5);
        tvfinal=(TextView)findViewById(R.id.totlcst);

//        String sc=tvcost.getText().toString();
//        String sn=tvnumb.getText().toString();
        int in1=Integer.parseInt(strnum);
        int ic1=Integer.parseInt(strcost);
        int rst=in1*ic1;
        String rst1=String.valueOf(rst);
        tvnumb.setText(strnum);
        tvcost.setText(rst1);
        tvnme.setText(strname);
        tvcst2.setText(rst1);
        String ab1=tvc1.getText().toString();
        String ab2=tvc2.getText().toString();
        String ab3=tvc3.getText().toString();
        String ab4=tvc4.getText().toString();
        String ab5=tvcst2.getText().toString();
        int res1=Integer.parseInt(ab1);
        int res2=Integer.parseInt(ab2);
        int res3=Integer.parseInt(ab3);
        int res4=Integer.parseInt(ab4);
        int res5=Integer.parseInt(ab5);
        int fres=res1+res2+res3+res4+res5;
        String finalstr=String.valueOf(fres);
        tvfinal.setText(finalstr);
        tedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvnumb.setCursorVisible(true);
                tvnumb.setEnabled(true);
                tvnumb.setFocusableInTouchMode(true);
                tvnumb.setInputType(InputType.TYPE_CLASS_TEXT);
                tvnumb.requestFocus(); //to trigger the soft input

                tvnumb.setFocusable(true);

                tvnumb.setClickable(true);

            }
        });

        bngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent21=new Intent(Basketact.this,Checkoutact.class);
                intent21.putExtra("tlcost",tvfinal.getText());
                startActivity(intent21);
            }
        });

//        tadditm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent int6=new Intent(Basketact.this,Producttypes.class);
//                int6.putExtra("inumber",tvnumb.getText());
//                startActivity(int6);
//            }
//        });


    }

    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);

    }

}

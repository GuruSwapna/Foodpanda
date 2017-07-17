package com.example.enchanterswapna.foodpanda;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class Checkoutfinal extends AppCompatActivity {

    Button btnpay;
    TextView txtvcst,textbnum;
    EditText editaddrs;
    Geocoder geocoder;
    public TrackGps gps;
    List<Address> addresses1;
    Double lat1,lng1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkoutfinal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intp=getIntent();
        final String fnlcst=intp.getStringExtra("flcost");

        textbnum=(TextView)findViewById(R.id.ttnmb);
        btnpay=(Button)findViewById(R.id.tpaid);
        txtvcst=(TextView)findViewById(R.id.tcst);
        editaddrs=(EditText)findViewById(R.id.edaddress);


//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//
//        String datanum = prefs.getString("string_num","string_num");


//        String sn1=intp.getStringExtra("add1");
//        String sn2=intp.getStringExtra("add2");
//        String sn3=intp.getStringExtra("add3");
//        String sn4=intp.getStringExtra("add4");
//        String sn5=intp.getStringExtra("add5");
//

//        try {
//            lat1 = gps.getLatitude();
//            lng1 = gps.getLongitude();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//        geocoder = new Geocoder(this, Locale.getDefault());
//
//        try {
//            addresses1 = geocoder.getFromLocation(lat1, lng1, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//         String address = addresses1.get(0).getAddressLine(1); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
////        final String address1 = addresses1.get(0).getAddressLine(1);
//         String city = addresses1.get(0).getLocality();
//         String state = addresses1.get(0).getAdminArea();
//         String country = addresses1.get(0).getCountryName();
//         String postalCode = addresses1.get(0).getPostalCode();
//
//        editaddrs.setText(address+","+","+city+","+state+","+country+","+postalCode);
        //textbnum.setText(datanum);

        txtvcst.setText(fnlcst);

        btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3=new Intent(Checkoutfinal.this,paymentact.class);
                startActivity(intent3);
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);

    }


}

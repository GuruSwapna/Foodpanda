package com.example.enchanterswapna.foodpanda;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Pageaddress extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TextView tvaddress1,tvaddress2,tvaddress3,tvaddress4,tvaddress5,textset;
    Geocoder geocoder;
    List<Address> addresses;
    public TrackGps gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pageaddress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvaddress1=(TextView)findViewById(R.id.txvaddr1);
        tvaddress2=(TextView)findViewById(R.id.txvaddr2);
        tvaddress3=(TextView)findViewById(R.id.txvaddr3);
        tvaddress4=(TextView)findViewById(R.id.txvaddr4);
        tvaddress5=(TextView)findViewById(R.id.txvaddr5);
        textset=(TextView)findViewById(R.id.textView9);
        gps = new TrackGps(Pageaddress.this);
        //final Handler handler = new Handler();
        Double lat = gps.getLatitude();
        Double lng = gps.getLongitude();

        final String dd = Double.toString(lat);
        final String d1 = Double.toString(lng);


        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(lat, lng, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String address = addresses.get(0).getAddressLine(1); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        final String city = addresses.get(0).getLocality();
        final String state = addresses.get(0).getAdminArea();
        final String country = addresses.get(0).getCountryName();
        final String postalCode = addresses.get(0).getPostalCode();
        // String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

        tvaddress1.setText(address);
        tvaddress2.setText(city);
        tvaddress3.setText(state);
        tvaddress4.setText(country);
        tvaddress5.setText(postalCode);


//        Intent intne1=new Intent(Pageaddress.this,Checkoutfinal.class);
//        intne1.putExtra("add1",tvaddress1.getText());
//        intne1.putExtra("add2",tvaddress2.getText());
//        intne1.putExtra("add3",tvaddress3.getText());
//        intne1.putExtra("add4",tvaddress4.getText());
//        intne1.putExtra("add5",tvaddress5.getText());

    textset.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            tvaddress1.setVisibility(View.VISIBLE);
            tvaddress2.setVisibility(View.VISIBLE);
            tvaddress3.setVisibility(View.VISIBLE);
            tvaddress4.setVisibility(View.VISIBLE);
            tvaddress5.setVisibility(View.VISIBLE);

        }
    });


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("string_add1", tvaddress1.getText().toString()); //InputString: from the EditText
        editor.putString("string_add2", tvaddress2.getText().toString());
        editor.putString("string_add3", tvaddress3.getText().toString());
        editor.putString("string_add4", tvaddress4.getText().toString());
        editor.putString("string_add5", tvaddress5.getText().toString());
        editor.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pageaddress, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_address) {

            Intent in1=new Intent(Pageaddress.this,Pageaddress.class);
            startActivity(in1);

        } else if (id == R.id.nav_deals) {

            Intent in1=new Intent(Pageaddress.this,Dealspage.class);
            startActivity(in1);

        } else if (id == R.id.nav_order) {
            Intent in1=new Intent(Pageaddress.this,Loginreg.class);
            startActivity(in1);

        } else if (id == R.id.nav_account) {
            Intent in1=new Intent(Pageaddress.this,Loginreg.class);
            startActivity(in1);

        } else if (id == R.id.nav_settings) {

            Intent in1=new Intent(Pageaddress.this,settingspage.class);
            startActivity(in1);

        } else if (id == R.id.nav_livechat) {

        } else if (id == R.id.nav_info) {
            Intent in1=new Intent(Pageaddress.this,Infonav.class);
            startActivity(in1);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

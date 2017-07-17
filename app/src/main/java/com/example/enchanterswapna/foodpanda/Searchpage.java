package com.example.enchanterswapna.foodpanda;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Searchpage extends AppCompatActivity {

    TextView twsearch,tnorth,tchina,tbiryani,tsouth,tfast,tcakes,tsnacks,tdesserts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        twsearch=(TextView)findViewById(R.id.txsearch);
        handleIntent(getIntent());

        tnorth=(TextView)findViewById(R.id.txtnorth);
        tchina=(TextView)findViewById(R.id.txtchina);
        tbiryani=(TextView)findViewById(R.id.txtbiryani);
        tsouth=(TextView)findViewById(R.id.txtsouth);
        tfast=(TextView)findViewById(R.id.txtfast);
        tcakes=(TextView)findViewById(R.id.txtcakes);
        tsnacks=(TextView)findViewById(R.id.txtsnacks);
        tdesserts=(TextView)findViewById(R.id.txtdesserts);
//        Bundle appData = getIntent().getBundleExtra(SearchManager.APP_DATA);
//        if (appData != null) {
//            boolean jargon = appData.getBoolean(SearchableActivity.JARGON);
//        }

        tnorth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twsearch.setText(tnorth.getText().toString());
            }
        });

        tchina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twsearch.setText(tchina.getText().toString());
            }
        });
        tbiryani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twsearch.setText(tbiryani.getText().toString());
            }
        });
        tsouth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twsearch.setText(tsouth.getText().toString());
            }
        });
        tfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twsearch.setText(tfast.getText().toString());
            }
        });
        tcakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twsearch.setText(tcakes.getText().toString());
            }
        });
        tsnacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twsearch.setText(tsnacks.getText().toString());
            }
        });
        tdesserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twsearch.setText(tdesserts.getText().toString());
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }
//    @Override
//    public boolean onSearchRequested() {
//        Bundle appData = new Bundle();
//        appData.putBoolean(Searchpage.JARGON, true);
//        startSearch(null, false, appData, false);
//        return true;
//    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
           // twsearch.setText(query);
            showResults(query);
            //use the query to search
        }
    }
    private void showResults(String query) {
        // Query your data set and show results
        // ...
    }
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);

    }


}

package com.example.enchanterswapna.foodpanda;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class userdetails extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText editbfname,editblname,editbmail,editbmob;
    String strfname,strlname,stremail,strmob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editbfname=(EditText)findViewById(R.id.input_fname1);
        editblname=(EditText)findViewById(R.id.inputlname12);
        editbmail=(EditText)findViewById(R.id.input_email13);
        editbmob=(EditText)findViewById(R.id.input_num4);

        String n1=getIntent().getStringExtra("ghtw");

        String RYLO=Global_Url.URI_CATEGORY2+n1;
        new Asyncm().execute(RYLO);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public class MovieAdap extends ArrayAdapter {
        private List<userdetailslist> movieModelList;
        private int resource;
        Context context;
        private LayoutInflater inflater;
        MovieAdap(Context context, int resource, List<userdetailslist> objects) {
            super(context, resource, objects);
            movieModelList = objects;
            this.context =context;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getViewTypeCount() {
            return 1;
        }
        @Override
        public int getItemViewType(int position) {
            return position;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if(convertView == null){
                convertView = inflater.inflate(resource,null);
                holder = new ViewHolder();
                holder.textfnamed=(EditText)  convertView.findViewById(R.id.input_fname1);
                holder.textlnamed=(EditText)  convertView.findViewById(R.id.inputlname12);
                holder.textemaild=(EditText)  convertView.findViewById(R.id.input_email13);
                holder.textnumd=(EditText)  convertView.findViewById(R.id.input_num4);
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }
            userdetailslist ccitac=movieModelList.get(position);
            holder.textfnamed.setText(ccitac.getFfname());
            holder.textlnamed.setText(ccitac.getFlname());
            holder.textemaild.setText(ccitac.getFemail());
            holder.textnumd.setText(ccitac.getFnumber());
            return convertView;
        }
        class ViewHolder{
            public TextView textfnamed,textlnamed,textemaild,textnumd;
        }
    }
    public class Asyncm extends AsyncTask<String,String, List<userdetailslist>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<userdetailslist> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder buffer = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("result");
                List<userdetailslist> milokilo = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    userdetailslist catego = gson.fromJson(finalObject.toString(), userdetailslist.class);
                    milokilo.add(catego);
                }
                return milokilo;
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(final List<userdetailslist> movieMode) {
            super.onPostExecute(movieMode);
            if (movieMode.size() > 0) {
                MovieAdap adapter = new MovieAdap(getApplicationContext(), R.layout.content_userdetails, movieMode);
                editbfname.setText((CharSequence) adapter);
                editblname.setText((CharSequence) adapter);
                editbmail.setText((CharSequence) adapter);
                editbmob.setText((CharSequence) adapter);
//                listprod.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        producttypelist item = movieMode.get(position);
//                        Intent intent = new Intent(Producttypes.this, finaladding.class);
//                        intent.putExtra("ptypename", item.getProductcat());
//                        intent.putExtra("pprice", item.getProductcost());
//                        intent.putExtra("pname", item.getProductname());
//                        startActivity(intent);
//                    }
//                });
                adapter.notifyDataSetChanged();
            } else {

                Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        }
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
        getMenuInflater().inflate(R.menu.userdetails, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_address) {

            Intent in1 = new Intent(userdetails.this, Pageaddress.class);
            startActivity(in1);

        } else if (id == R.id.nav_deals) {
            Intent in1 = new Intent(userdetails.this, Dealspage.class);
            startActivity(in1);

        } else if (id == R.id.nav_order) {
            Intent in1 = new Intent(userdetails.this, Loginreg.class);
            startActivity(in1);

        } else if (id == R.id.nav_account) {
            Intent in1 = new Intent(userdetails.this, Loginreg.class);
            startActivity(in1);

        } else if (id == R.id.nav_settings) {

            Intent in1 = new Intent(userdetails.this, settingspage.class);
            startActivity(in1);

        } else if (id == R.id.nav_livechat) {

        } else if (id == R.id.nav_info) {
            Intent in1 = new Intent(userdetails.this, Infonav.class);
            startActivity(in1);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

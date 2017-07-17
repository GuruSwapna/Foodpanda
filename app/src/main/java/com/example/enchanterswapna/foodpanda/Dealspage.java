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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

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

public class Dealspage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView dealslistone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealspage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        dealslistone=(ListView)findViewById(R.id.listdeal1);
        new kilomilo().execute(Global_Url.DEAL_URL);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public class MovieAdap extends ArrayAdapter {
        private List<dealslistss> movieModelList;
        private int resource;
        Context context;
        private LayoutInflater inflater;

        MovieAdap(Context context, int resource, List<dealslistss> objects) {
            super(context, resource, objects);
            movieModelList = objects;
            this.context = context;
            this.resource = resource;
            inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
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
        public View getView(final int position, View converti, ViewGroup parent) {
            final ViewHolder holder;
            if (converti == null) {
                converti = inflater.inflate(resource, null);
                holder = new ViewHolder();
                holder.imageviewdeal = (ImageView) converti.findViewById(R.id.dealimg);
                holder.dealoffer1 = (TextView) converti.findViewById(R.id.dealoffr);
                holder.dealoffer2 = (TextView) converti.findViewById(R.id.dealoffr1);
//                holder.button2=(Button) converti.findViewById(R.id.but2);
                converti.setTag(holder);
            } else {
                holder = (ViewHolder) converti.getTag();
            }
            dealslistss ccitac = movieModelList.get(position);
            Picasso.with(context).load(ccitac.getFimage()).error(R.drawable.ic_error_outline_black_24dp).fit().into(holder.imageviewdeal);
            holder.dealoffer1.setText(ccitac.getFoffer());
            holder.dealoffer2.setText(ccitac.getFoffer());

            return converti;
        }

        class ViewHolder {
            public TextView dealoffer1, dealoffer2;
            public ImageView imageviewdeal;

            //Button button29, button2;
        }
    }

    public class kilomilo extends AsyncTask<String, String, List<dealslistss>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<dealslistss> doInBackground(String... params) {
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
                List<dealslistss> milokilo = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    dealslistss catego = gson.fromJson(finalObject.toString(), dealslistss.class);
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
        protected void onPostExecute(final List<dealslistss> movieMode) {
            super.onPostExecute(movieMode);
            if (movieMode.size() != 0) {
                MovieAdap adapter = new MovieAdap(getApplicationContext(), R.layout.dealslist, movieMode);
                dealslistone.setAdapter(adapter);
                dealslistone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        dealslistss item = movieMode.get(position);
                        ViewGroup vb = (ViewGroup) view;


                        Intent i1 = new Intent(Dealspage.this, Idnextact.class);


                        i1.putExtra("image", item.getFimage());
                        i1.putExtra("offer", item.getFoffer());



                        startActivity(i1);

                    }
                });

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
        getMenuInflater().inflate(R.menu.dealspage, menu);
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

            Intent in1=new Intent(Dealspage.this,Pageaddress.class);
            startActivity(in1);

        } else if (id == R.id.nav_deals) {



        } else if (id == R.id.nav_order) {
            Intent in1=new Intent(Dealspage.this,Loginreg.class);
            startActivity(in1);

        } else if (id == R.id.nav_account) {
            Intent in1=new Intent(Dealspage.this,Loginreg.class);
            startActivity(in1);

        } else if (id == R.id.nav_settings) {

            Intent in1=new Intent(Dealspage.this,settingspage.class);
            startActivity(in1);

        } else if (id == R.id.nav_livechat) {

        } else if (id == R.id.nav_info) {
            Intent in1=new Intent(Dealspage.this,Infonav.class);
            startActivity(in1);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

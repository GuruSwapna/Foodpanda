package com.example.enchanterswapna.foodpanda;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class HomeScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listitems;
    TextView textdisplay;
    ImageView itag,icart,icrn;
    ImageView imgfilter;
    LinearLayout linearlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textdisplay=(TextView)findViewById(R.id.txtgreen);
        listitems = (ListView) findViewById(R.id.listvw1);
        itag=(ImageView) findViewById(R.id.imgtag);
        icart=(ImageView)findViewById(R.id.imgcart);
        icrn=(ImageView)findViewById(R.id.imgcrown);
        imgfilter=(ImageView)findViewById(R.id.imgfilt);
        linearlayout=(LinearLayout)findViewById(R.id.linlay1);
        new kilomilo().execute(Global_Url.ITEM_URL);
        itag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textdisplay.setVisibility(View.VISIBLE);
                textdisplay.setText("Has discount");
                imgfilter.setVisibility(View.VISIBLE);
                linearlayout.setVisibility(View.VISIBLE);
            }
        });
        icart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textdisplay.setVisibility(View.VISIBLE);
                textdisplay.setText("Express Delivery");
                imgfilter.setVisibility(View.VISIBLE);
                linearlayout.setVisibility(View.VISIBLE);
            }
        });
        icrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textdisplay.setVisibility(View.VISIBLE);
                textdisplay.setText("People's Choice");
                imgfilter.setVisibility(View.VISIBLE);
                linearlayout.setVisibility(View.VISIBLE);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    public class MovieAdap extends ArrayAdapter {
        private List<itemslist> movieModelList;
        private int resource;
        Context context;
        private LayoutInflater inflater;

        MovieAdap(Context context, int resource, List<itemslist> objects) {
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
                holder.imageview1 = (ImageView) converti.findViewById(R.id.imge1);
                holder.itemsname = (TextView) converti.findViewById(R.id.itemname);
                holder.itemsdesc = (TextView) converti.findViewById(R.id.itemexp);
                holder.price = (TextView) converti.findViewById(R.id.txcost);
                holder.itemstime = (TextView) converti.findViewById(R.id.delivtime);
//                holder.button2=(Button) converti.findViewById(R.id.but2);
                converti.setTag(holder);
            } else {
                holder = (ViewHolder) converti.getTag();
            }
            itemslist ccitac = movieModelList.get(position);
            Picasso.with(context).load(ccitac.getItem_image()).error(R.drawable.ic_error_outline_black_24dp).fit().into(holder.imageview1);
            holder.itemsname.setText(ccitac.getItem_name());
            holder.itemsdesc.setText(ccitac.getItem_name());
            holder.price.setText(ccitac.getItem_cost());
            holder.itemstime.setText(ccitac.getItem_time());


//            holder.button2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(getApplicationContext(),"This salon offers in home service",Toast.LENGTH_SHORT).show();
//                }
//            });
            return converti;
        }

        class ViewHolder {
            public TextView itemsname,itemsdesc, price,itemstime;
            public ImageView imageview1;

            //Button button29, button2;
        }
    }

    public class kilomilo extends AsyncTask<String, String, List<itemslist>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<itemslist> doInBackground(String... params) {
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
                List<itemslist> milokilo = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    itemslist catego = gson.fromJson(finalObject.toString(), itemslist.class);
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
        protected void onPostExecute(final List<itemslist> movieMode) {
            super.onPostExecute(movieMode);
            if (movieMode.size() > 0) {
                MovieAdap adapter = new MovieAdap(getApplicationContext(), R.layout.firstpagelist, movieMode);
                listitems.setAdapter(adapter);
                listitems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        itemslist item = movieMode.get(position);
                        ViewGroup vb = (ViewGroup) view;


                        Intent i1 = new Intent(HomeScreen.this, Idnextact.class);

                        i1.putExtra("name", item.getItem_name());
                        i1.putExtra("Description", item.getItem_text());
                        i1.putExtra("price", item.getItem_cost());
                        i1.putExtra("image", item.getItem_image());
                        i1.putExtra("time", item.getItem_time());



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
            getMenuInflater().inflate(R.menu.home_screen, menu);
            SearchManager searchManager =
                    (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView searchView =
                    (SearchView) menu.findItem(R.id.searchtbtn).getActionView();
            searchView.setSearchableInfo(
                    searchManager.getSearchableInfo(getComponentName()));
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
            } else if (id == R.id.action_settings2) {
                Intent int1 = new Intent(HomeScreen.this, Pageaddress.class);
                startActivity(int1);
                return true;
            } else if (id == R.id.searchtbtn) {
                Intent int1 = new Intent(HomeScreen.this, Searchpage.class);
                startActivity(int1);
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

                Intent in1 = new Intent(HomeScreen.this, Pageaddress.class);
                startActivity(in1);

            } else if (id == R.id.nav_deals) {
                Intent in1 = new Intent(HomeScreen.this, Dealspage.class);
                startActivity(in1);

            } else if (id == R.id.nav_order) {
                Intent in1 = new Intent(HomeScreen.this, Loginreg.class);
                startActivity(in1);

            } else if (id == R.id.nav_account) {
                Intent in1 = new Intent(HomeScreen.this, Loginreg.class);
                startActivity(in1);

            } else if (id == R.id.nav_settings) {

                Intent in1 = new Intent(HomeScreen.this, settingspage.class);
                startActivity(in1);

            } else if (id == R.id.nav_livechat) {

            } else if (id == R.id.nav_info) {
                Intent in1 = new Intent(HomeScreen.this, Infonav.class);
                startActivity(in1);

            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

}

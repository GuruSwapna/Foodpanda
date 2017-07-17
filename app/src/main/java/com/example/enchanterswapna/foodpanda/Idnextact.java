package com.example.enchanterswapna.foodpanda;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class Idnextact extends AppCompatActivity {

    ImageView img1;
    TextView txtvname,txtvdesc;
    Context context;
    ListView prlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idnextact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        img1=(ImageView)findViewById(R.id.imageviewa);
        txtvname=(TextView)findViewById(R.id.imgtname);
        txtvdesc=(TextView)findViewById(R.id.imgdesc);
        prlist=(ListView)findViewById(R.id.listdist);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final String desc = intent.getStringExtra("Description");
        //final String desc=intent.getStringExtra("desc");
        String imagesn =intent.getStringExtra("image");
        final String ratingpb=intent.getStringExtra("ratingsr");
        txtvname.setText(name);
        setTitle(name);
        txtvdesc.setText(desc);
        Picasso.with(context).load(imagesn).fit().into(img1);
        new kilomilo().execute(Global_Url.DEL_URL);

    }
    public class MovieAdap extends ArrayAdapter {
        private List<dealivlist> movieModelList;
        private int resource;
        Context context;
        private LayoutInflater inflater;

        MovieAdap(Context context, int resource, List<dealivlist> objects) {
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

                holder.ptype = (TextView) converti.findViewById(R.id.delivlist);
//                holder.itemsdesc = (TextView) converti.findViewById(R.id.itemexp);
//                holder.price = (TextView) converti.findViewById(R.id.txcost);

//                holder.button2=(Button) converti.findViewById(R.id.but2);
                converti.setTag(holder);
            } else {
                holder = (ViewHolder) converti.getTag();
            }
            dealivlist ccitac = movieModelList.get(position);
//            Picasso.with(context).load(ccitac.getItem_image()).error(R.drawable.ic_error_outline_black_24dp).fit().into(holder.imageview1);
//            holder.itemsname.setText(ccitac.getItem_name());
//            holder.itemsdesc.setText(ccitac.getItem_name());
//            holder.price.setText(ccitac.getItem_cost());
            holder.ptype.setText(ccitac.getPtypes());


//            holder.button2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(getApplicationContext(),"This salon offers in home service",Toast.LENGTH_SHORT).show();
//                }
//            });
            return converti;
        }

        class ViewHolder {
            public TextView ptype,itemsdesc, price,itemstime;


            //Button button29, button2;
        }
    }

    public class kilomilo extends AsyncTask<String, String, List<dealivlist>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<dealivlist> doInBackground(String... params) {
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
                List<dealivlist> milokilo = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    dealivlist catego = gson.fromJson(finalObject.toString(), dealivlist.class);
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
        protected void onPostExecute(final List<dealivlist> movieMode) {
            super.onPostExecute(movieMode);
            if (movieMode.size() != 0) {
                MovieAdap adapter = new MovieAdap(getApplicationContext(), R.layout.delist, movieMode);
                prlist.setAdapter(adapter);
                prlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        dealivlist item = movieMode.get(position);
                        ViewGroup vb = (ViewGroup) view;


                        Intent i1 = new Intent(Idnextact.this,Producttypes.class);

                        i1.putExtra("ptypename", item.getPtypes());
                        i1.putExtra("pptypename", item.getPptypes());
                        i1.putExtra("pprice", item.getPcost());
                        startActivity(i1);

                    }
                });

            } else {
                Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.idnextact, menu);
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
        } else if (id == R.id.searchtbtn) {
            Intent int1 = new Intent(Idnextact.this, Searchpage.class);
            startActivity(int1);
            return true;
        }

        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


}

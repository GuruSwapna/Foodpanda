package com.example.enchanterswapna.foodpanda;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

public class pproducts extends AppCompatActivity {

    ListView costlistdata;
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    String sname, scost;
    TextView txname, txcost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pproducts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        costlistdata = (ListView) findViewById(R.id.listview123);
        new kilomilo().execute(Global_Url.DEL_URL);
//        txname=(TextView)findViewById(R.id.textnm);
//        txcost=(TextView)findViewById(R.id.textcost);
//        costlistdata=(ListView)findViewById(R.id.listview123);
//        sname=getIntent().getStringExtra("pptypename");
//        scost=getIntent().getStringExtra("price");
//        adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.costlist, list);
//        costlistdata.setAdapter(adapter);
//        txname.setText(sname.toString());
//        txcost.setText(scost.toString());
////        list.add(txname.setText(sname.toString()));
////        list.add(txcost.setText(scost.toString()));
//        adapter.notifyDataSetChanged();
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

                holder.pptype = (TextView) converti.findViewById(R.id.textnm);
//                holder.itemsdesc = (TextView) converti.findViewById(R.id.itemexp);
               holder.price = (TextView) converti.findViewById(R.id.textcost);

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
            holder.pptype.setText(ccitac.getPptypes());
            holder.price.setText(ccitac.getPcost());


//            holder.button2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(getApplicationContext(),"This salon offers in home service",Toast.LENGTH_SHORT).show();
//                }
//            });
            return converti;
        }

        class ViewHolder {
            public TextView pptype, itemsdesc, price, itemstime;


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
                MovieAdap adapter = new MovieAdap(getApplicationContext(), R.layout.costlist, movieMode);
                costlistdata.setAdapter(adapter);
                costlistdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        dealivlist item = movieMode.get(position);
                        ViewGroup vb = (ViewGroup) view;


                        Intent i1 = new Intent(pproducts.this, finaladding.class);

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
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);

    }

}

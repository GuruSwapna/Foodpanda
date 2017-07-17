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

public class Producttypes extends AppCompatActivity {

    ListView listprod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producttypes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listprod=(ListView)findViewById(R.id.prolist);
        String n1=getIntent().getStringExtra("ptypename");
        String n2=getIntent().getStringExtra("pprice");
        setTitle(n1);
       // setTitle(n2);
        String RYLO=Global_Url.URI_CATEGORY1+n1;
        new Asyncm().execute(RYLO);

    }

    public class MovieAdap extends ArrayAdapter {
        private List<producttypelist> movieModelList;
        private int resource;
        Context context;
        private LayoutInflater inflater;
        MovieAdap(Context context, int resource, List<producttypelist> objects) {
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
                holder.textnamed=(TextView)  convertView.findViewById(R.id.textnm);
                holder.textdescd=(TextView)  convertView.findViewById(R.id.textcost);
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }
            producttypelist ccitac=movieModelList.get(position);
            holder.textnamed.setText(ccitac.getProductcat());
            holder.textdescd.setText(ccitac.getProductcost());
            return convertView;
        }
        class ViewHolder{
            public TextView textnamed,textdescd;
        }
    }
    public class Asyncm extends AsyncTask<String,String, List<producttypelist>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected List<producttypelist> doInBackground(String... params) {
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
                List<producttypelist> milokilo = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    producttypelist catego = gson.fromJson(finalObject.toString(), producttypelist.class);
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
        protected void onPostExecute(final List<producttypelist> movieMode) {
            super.onPostExecute(movieMode);
            if (movieMode.size()>0) {
                MovieAdap adapter = new MovieAdap(getApplicationContext(), R.layout.costlist, movieMode);
                listprod.setAdapter(adapter);
                listprod.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        producttypelist item = movieMode.get(position);
                        Intent intent = new Intent(Producttypes.this, finaladding.class);
                        intent.putExtra("ptypename", item.getProductcat());
                        intent.putExtra("pprice", item.getProductcost());
                        intent.putExtra("pname",item.getProductname());
                        startActivity(intent);
                    }
                });
                adapter.notifyDataSetChanged();
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

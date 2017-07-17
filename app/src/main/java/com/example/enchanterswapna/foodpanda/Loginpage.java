package com.example.enchanterswapna.foodpanda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Loginpage extends AppCompatActivity {

    EditText edname,edpwd;
    String sdmail,sdpwd;
    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edname=(EditText)findViewById(R.id.input_emailadd);
        edpwd=(EditText)findViewById(R.id.input_pwdfd);
        btnlogin=(Button)findViewById(R.id.button1);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sdmail=edname.getText().toString();
                sdpwd=edpwd.getText().toString();
                logininto(sdmail,sdpwd);
//                Intent int12=new Intent(Loginpage.this,HomeScreen.class);
//                startActivity(int12);
            }
        });
    }

    public void logininto(final String sstmail, final String sstpass) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Global_Url.LOGINPG_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean abc = jObj.getBoolean("exits");
                    if (abc)
                    {
                        JSONObject users = jObj.getJSONObject("user_det");
                        String uname1 = users.getString("femail");
                        String upass1 = users.getString("fpass");
                        Intent intent2=new Intent(Loginpage.this,userdetails.class);
                        intent2.putExtra("ghtw",uname1);
                        intent2.putExtra("sssw",upass1);

                        startActivity(intent2);
                        //   Toast.makeText(getApplicationContext(),mobile_number,Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Invalid credentials",Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> insert = new HashMap<String, String>();
                insert.put("femail", sstmail);
                insert.put("fpass", sstpass);

                return insert;

            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

    }


    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);

    }

}

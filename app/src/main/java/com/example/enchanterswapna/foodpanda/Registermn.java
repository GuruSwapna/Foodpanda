package com.example.enchanterswapna.foodpanda;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

import static android.widget.Toast.makeText;

public class Registermn extends AppCompatActivity {

    EditText tfname,tflname,tfmail,tfphone,tfpass,tfcpass,tfcomp,tfadd1,tfadd2,tfpost,tfother,tfinst;
    CheckBox cb1,cb2;
    Button baccount;
    String sfname,slname,smail,sphone,spass,scpass,scomp,sadd1,sadd2,spost,sother,sinst,sval;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sval="[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
        tfname=(EditText) findViewById(R.id.input_fname1);
        tflname=(EditText) findViewById(R.id.input_lname2);
        tfmail=(EditText) findViewById(R.id.input_email3);
        tfphone=(EditText) findViewById(R.id.input_num4);
        tfpass=(EditText) findViewById(R.id.input_pwdd5);
        tfcpass=(EditText) findViewById(R.id.input_cnpwdd);
        tfcomp=(EditText) findViewById(R.id.input_cmpname);
        tfadd1=(EditText) findViewById(R.id.input_addline1);
        tfadd2=(EditText) findViewById(R.id.input_addline2);
        tfpost=(EditText) findViewById(R.id.input_poscode12);
        tfother=(EditText) findViewById(R.id.input_addother1);
        tfinst=(EditText) findViewById(R.id.input_adddelivery);
        cb1=(CheckBox)findViewById(R.id.checkb1);
        cb2=(CheckBox)findViewById(R.id.checkb2);
        baccount=(Button)findViewById(R.id.btnaccnt);

        baccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sfname=tfname.getText().toString();
                slname=tflname.getText().toString();
                smail=tfmail.getText().toString();
                sphone=tfphone.getText().toString();
                spass=tfpass.getText().toString();
                scpass=tfcpass.getText().toString();
                scomp=tfcomp.getText().toString();
                sadd1=tfadd1.getText().toString();
                sadd2=tfadd2.getText().toString();
                spost=tfpost.getText().toString();
                sother=tfother.getText().toString();
                sinst=tfinst.getText().toString();
                if (sfname.isEmpty() || slname.isEmpty() || smail.isEmpty() || sphone.isEmpty() || spass.isEmpty() || scpass.isEmpty() || sadd1.isEmpty() || sadd2.isEmpty()) {
//                   tfname.setError("Name should not empty");
//                    tflname.setError("Name should not empty");
//                    tfphone.setError("Number should not empty");
//                    tfpass.setError("Password should not empty");
//                    tfcpass.setError("Confirm Password should not empty");
//                    tfadd1.setError("Address1 should not empty");
//                    tfadd2.setError("Address2 should not empty");
//                    tfmail.setError("Email should not empty");

                    Toast.makeText(getApplicationContext(), "Fill details completely", Toast.LENGTH_SHORT).show();
                } else if (spass.length() < 6 || spass.length() > 8) {
                    //makeText(getApplicationContext(), "password must between 6 to 8", Toast.LENGTH_SHORT).show();
                    tfpass.setError("password must between 6-8");
                } else if (!smail.matches(sval)) {
                    tfmail.setError("Invalid Emailid");
                }else if (!(spass.equals(scpass))) {
                    tfcpass.setError("Confirm password should match password");
                }else if (!(cb2.isChecked())) {
                    makeText(getApplicationContext(),"You must agree terms and conditions",Toast.LENGTH_SHORT).show();
                }
                else {
                    insert_service(sfname,slname,smail,sphone,spass,scpass,scomp,sadd1,sadd2,spost,sother,sinst);
                }

            }
        });

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("string_num", sphone); //InputString: from the EditText

        editor.commit();

    }
    private void insert_service(final String ssname,final String sslname,final String ssmail,final String ssphone,final String sspass,final String sscpass,final String sscomp, final String ssadd1, final String ssadd2,final String sspost,final String ssother,final String ssinst) {

        StringRequest stringreqs = new StringRequest(Request.Method.POST, Global_Url.SIGN_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean abc = jObj.getBoolean("exits");
                    if (abc)
                    {
                        JSONObject users = jObj.getJSONObject("user_det");
                        String fname = users.getString("ffname");
                        String flname = users.getString("flname");
                        String fmail = users.getString("femail");
                        String fphone = users.getString("fphone");
                        String fpass = users.getString("fpass");
                        String fcpass = users.getString("fcpass");
                        String fcomp = users.getString("fcomp");
                        String fadd1 = users.getString("fadd1");
                        String fadd2 = users.getString("fadd2");
                        String fpost = users.getString("fpost");
                        String fother = users.getString("fother");
                        String finst = users.getString("finst");

                        Intent intent1=new Intent(Registermn.this,Loginpage.class);
                        intent1.putExtra("stname", fname);
                        startActivity(intent1);
                    }
                    else
                    {
                        //String msg=jObj.getString("messeade");
                        makeText(getApplicationContext(), "not valid", Toast.LENGTH_SHORT).show();
                    }
                   /*Intent intent = new Intent(SignUp_Screen.this, Home_Screen.class);
                   intent.putExtra("name", name);
                   intent.putExtra("mail", mail);
                   intent.putExtra("uuidq", uuidq);
                   startActivity(intent);
                   Toast.makeText(getApplicationContext(), "Welcome" + name, Toast.LENGTH_SHORT).show();
*/

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                makeText(getApplicationContext(), "INTERNET CONNECTION NOT AVAILABLE", Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> uandme = new HashMap<String, String>();
                uandme.put("ffname", ssname);
                uandme.put("flname", sslname);
                uandme.put("femail", ssmail);
                uandme.put("fphone", ssphone);
                uandme.put("fpass", sspass);
                uandme.put("fcpass", sscpass);
                uandme.put("fcomp", sscomp);
                uandme.put("fadd1", ssadd1);
                uandme.put("fadd2", ssadd2);
                uandme.put("fpost", sspost);
                uandme.put("fother", ssother);
                uandme.put("finst", ssinst);
                //uandme.put("password2", password1);
                return uandme;
            }
        };
        AppController.getInstance().addToRequestQueue(stringreqs);

    }

    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);

    }

}

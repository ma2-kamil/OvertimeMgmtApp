package com.example.overtimemgmtapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class LoginPage extends AppCompatActivity {
    EditText UsernameEt, PasswordEt;
    public static UserDetails user; // Stores all the user info for use in other pages

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        if(!isConnected(LoginPage.this)) buildDialog(LoginPage.this).show();
        else {
            Toast.makeText(LoginPage.this,"Welcome", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_login_page);
        }
        UsernameEt = findViewById(R.id.et_user);
        PasswordEt = findViewById(R.id.et_pass);

    }



    public void OnLogin(View view){

        final String username = UsernameEt.getText().toString();
        final String password = PasswordEt.getText().toString();
        String type = "login";

      BackgroundWorker backgroundWorker = new BackgroundWorker(this);
      backgroundWorker.execute(type, username, password);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    String success = jsonResponse.getString("success");

                    if (success.equals("true")){

                        //Context context = getApplicationContext();
                       // Toast.makeText(context,"LOGGED IN SUCCESFULLY",
                               // Toast.LENGTH_LONG).show();

                        // GET MORE DETAILS FROM DB LATER
                        final String fullname = jsonResponse.getString("fullname");
                        final String username = jsonResponse.getString("username");
                        final String password = jsonResponse.getString("password");
                        final String email = jsonResponse.getString("email");
                        final String shiftmanager = jsonResponse.getString("shiftmanager");
                        final String uniquecode = jsonResponse.getString("uniquecode");

                        user = new UserDetails(fullname,username,password,email,shiftmanager,uniquecode);


                       /*
                        Intent intent = new Intent(LoginPage.this, AccountSettingsActivity.class);
                        intent.putExtra("fullname", fullname);
                        intent.putExtra("username", username);
                        intent.putExtra("password", password);
                        intent.putExtra("email", email);
                        intent.putExtra("shiftmanager", shiftmanager);
                        intent.putExtra("uniquecode", uniquecode);
                        */

                        //After login is successful we take user to the main page
                        // we have 2 different main pages for shift manager and normal user.

                        Intent intents = new Intent(LoginPage.this, AdminMainActivity.class);
                        Intent intents_u = new Intent(LoginPage.this, UserMain.class);


                        if (shiftmanager.equals("1")){
                            Context context = getApplicationContext();
                            LoginPage.this.startActivity(intents);
                        }
                        else if (shiftmanager.equals("0")){
                            Context context = getApplicationContext();
                            LoginPage.this.startActivity(intents_u);
                        }


                    }else{
                        Context context = getApplicationContext();
                        Toast.makeText(context,"LOGIN FAILED",
                                Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };


        LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
        RequestQueue queue = Volley.newRequestQueue(LoginPage.this);
        queue.add(loginRequest);

    }




    public void OpenReg(View view){
        startActivity(new Intent(this, RegisterPage.class)); //start the register activity
    }



    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            return (mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting());
        } else
            return false;
    }
    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection Found");
        builder.setMessage("You need to have Mobile Data or wifi to login. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }

}

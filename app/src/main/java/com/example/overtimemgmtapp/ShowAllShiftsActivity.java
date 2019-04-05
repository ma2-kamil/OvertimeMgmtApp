package com.example.overtimemgmtapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.overtimemgmtapp.adapters.RecyclerViewAd;
import com.example.overtimemgmtapp.classes.Jobs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowAllShiftsActivity extends AppCompatActivity {
    // code mostly same as UserMain
    private final String job_url = "https://overtimemgmt.000webhostapp.com/getjobs.php"; // change to getjobs php file
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Jobs> mJobs;
    private List<Jobs> myJobs;
    private RecyclerView recyclerView;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_shifts);

        mJobs = new ArrayList<>();
        myJobs = new ArrayList<>();
        fab = findViewById(R.id.fabutton);
        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();


        // Bottom Nav
        BottomNavigationView navigation = findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        if (LoginPage.user.shiftmanager.equals("1")) {

                            Intent a = new Intent(ShowAllShiftsActivity.this, AdminMainActivity.class);
                            finish();
                            startActivity(a);
                            ShowAllShiftsActivity.this.overridePendingTransition(0, 0);
                        } else if (LoginPage.user.shiftmanager.equals("0")) {
                            Intent a = new Intent(ShowAllShiftsActivity.this, UserMain.class);
                            finish();
                            startActivity(a);
                            ShowAllShiftsActivity.this.overridePendingTransition(0, 0);
                        }
                        break;
                    case R.id.navigation_showallshifts:
                        break;
                    case R.id.navigation_usersettings:
                        Intent b = new Intent(ShowAllShiftsActivity.this, AccountSettingsActivity.class);
                        finish();
                        startActivity(b);
                        ShowAllShiftsActivity.this.overridePendingTransition(0, 0);
                        break;
                }
                return false;
            }
        });
    }

    private void jsonrequest() {

        request = new JsonArrayRequest(job_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonjob = null;

                for (int i = 0; i < response.length(); i++){

                    try {
                        jsonjob = response.getJSONObject(i);
                        final Jobs jobs = new Jobs();
                        jobs.setId(jsonjob.getString("id"));
                        jobs.setShifttitle(jsonjob.getString("shifttitle"));
                        jobs.setDescription(jsonjob.getString("description"));
                        jobs.setHourlyrate(jsonjob.getString("hourlyrate"));
                        jobs.setDate(jsonjob.getString("date"));
                        jobs.setStarttime(jsonjob.getString("ftime"));
                        jobs.setEndtime(jsonjob.getString("ttime"));
                        jobs.setUniquecode(jsonjob.getString("uniquecode"));
                        jobs.setEmployee(jsonjob.getString("employee"));

                        // We got all the jobs by doing this but we want only jobs where the unique code
                        // is the same, so we only add the jobs to the main array if it has the same uniquecode as the user


                        // if the logged in user is not a shift manager it will only show shifts which are not taken
                        // if the user is a shift manager it will show all shifts and when filled button is pressed,
                        // it will show you shift manager all jobs which are taken.

                        if (LoginPage.user.shiftmanager.equals("0")){

                            if (jobs.getUniquecode().matches(LoginPage.user.uniquecode) && jobs.getEmployee().isEmpty()){
                                mJobs.add(jobs);
                            }

                                if(jobs.getEmployee().matches(LoginPage.user.fullname)){
                                    myJobs.add(jobs);

                            }

                        }
                        else if (LoginPage.user.shiftmanager.equals("1")){

                            if (jobs.getUniquecode().matches(LoginPage.user.uniquecode)) {
                                mJobs.add(jobs);

                                if (!jobs.getEmployee().equals("")) {
                                    myJobs.add(jobs);
                                }

                            }


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                setuprecyclerview(mJobs);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(ShowAllShiftsActivity.this);
        requestQueue.add(request);

    }

    private void setuprecyclerview(List<Jobs> mJobs) {


        RecyclerViewAd myadapter = new RecyclerViewAd(this, mJobs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);
    }

    @Override
    public void onBackPressed(){

    }

    public void viewMyJobs(View view){
        // shows jobs which the user is booked too
        setuprecyclerview(myJobs);

    }

    public void viewAllJobs(View view){
        // shows all jobs which use the same uniquecode.
        setuprecyclerview(mJobs);

    }

}
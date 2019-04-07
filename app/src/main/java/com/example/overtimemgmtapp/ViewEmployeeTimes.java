package com.example.overtimemgmtapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.overtimemgmtapp.adapters.RecyclerViewAdAvailability;
import com.example.overtimemgmtapp.classes.Schedule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewEmployeeTimes extends AppCompatActivity {
    private final String job_url = "https://overtimemgmt.000webhostapp.com/gettimes.php";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Schedule> mySchedule;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee_tines);
        // array list to store data from db in
        mySchedule = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewidc);
        jsonrequest();
    }

    private void jsonrequest() {

        request = new JsonArrayRequest(job_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonschedule = null;

                for (int i = 0; i < response.length(); i++){

                    try {
                        jsonschedule = response.getJSONObject(i);
                        final Schedule schedules = new Schedule();
                        schedules.setId(jsonschedule.getString("id"));
                        schedules.setDate(jsonschedule.getString("date"));
                        schedules.setFromtime(jsonschedule.getString("fromtime"));
                        schedules.setTotime(jsonschedule.getString("totime"));
                        schedules.setUniquecode(jsonschedule.getString("uniquecode"));
                        schedules.setEmployee(jsonschedule.getString("employee"));

                        // we got all listings now we only show listings which belond to user.

                        if(LoginPage.user.uniquecode.matches(schedules.getUniquecode())) {
                            mySchedule.add(schedules);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setuprecyclerview(mySchedule);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(ViewEmployeeTimes.this);
        requestQueue.add(request);

    }

    private void setuprecyclerview(List<Schedule> mySchedule) {
        RecyclerViewAdAvailability myadapters = new RecyclerViewAdAvailability(this, mySchedule);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapters);
    }


}

package com.example.overtimemgmtapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.overtimemgmtapp.adapters.RecyclerViewAd;
import com.example.overtimemgmtapp.adapters.RecyclerViewAdAvailability;
import com.example.overtimemgmtapp.classes.Jobs;
import com.example.overtimemgmtapp.classes.Schedule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserMain extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    // Variables, change job url to php file which gets times.
    private final String job_url = "https://overtimemgmt.000webhostapp.com/gettimes.php";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Schedule> mySchedule;
    private RecyclerView recyclerView;

    // delcaring variables for views/buttons.
    TextView date, starttime, endtime, error;
    String sdate, sstime, setime;
    Button sub;
    int a = 0;
    int b = 0;
    int c = 0;

    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        // array list to store data from db in
        mySchedule = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewidu);
        jsonrequest();
        // casting from xml file to our variables.
        date = findViewById(R.id.tv_dateu);
        starttime = findViewById(R.id.tv_stimeu);
        endtime = findViewById(R.id.tv_etimeu);
        sub = findViewById(R.id.btn_submite);
        error = findViewById(R.id.textView_error);

        // using calender method and getting current time for default when dialog is opened.
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        findViewById(R.id.ib_udate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();

            }
        });

        findViewById(R.id.ib_ustime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Use time picker dialog and then convert the strings for storage in db and for view
                // in app.

                TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint({"SetTextI18n", "DefaultLocale"})
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minuteOfHour) {
                        sstime = (String.format("%02d:%02d", hourOfDay, minuteOfHour) + ":00");
                        starttime.setText((String.format("%02d:%02d", hourOfDay, minuteOfHour)));
                        a = 1;
                    }
                }, hour, minute, android.text.format.DateFormat.is24HourFormat(context));
                timePickerDialog.show();

            }
        });

        findViewById(R.id.ib_uetime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint({"SetTextI18n", "DefaultLocale"})
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minuteOfHour) {
                        setime = (String.format("%02d:%02d", hourOfDay, minuteOfHour) + ":00");
                        endtime.setText((String.format("%02d:%02d", hourOfDay, minuteOfHour)));
                        b = 2;
                    }
                }, hour, minute, android.text.format.DateFormat.is24HourFormat(context));
                timePickerDialog.show();

            }
        });

        // BottomNavigation switch case we finish an activity and start a new one when one is clicked.
        BottomNavigationView navigation = findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        break;
                    case R.id.navigation_showallshifts:
                        Intent a = new Intent(UserMain.this, ShowAllShiftsActivity.class);
                        finish();
                        startActivity(a);
                        UserMain.this.overridePendingTransition(0, 0);
                        break;
                    case R.id.navigation_usersettings:
                        Intent b = new Intent(UserMain.this, AccountSettingsActivity.class);
                        finish();
                        startActivity(b);
                        UserMain.this.overridePendingTransition(0, 0);
                        break;
                }
                return false;
            }
        });


    }


    private void showDatePickerDialog() {
        // getting the year, month, day
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        // Setting min date so we cant choose dates before current date.
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        month = month + 1;
        String date1 = dayOfMonth + "/" + month + "/" + year;
        date.setText(date1); // Display date in app
        // Storing Date in right format
        String date2 = year + "-" + month + "-" + dayOfMonth;

        // formatting the date for show in db and app.

        Date initDate = null;
        try {
            initDate = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        sdate = formatter.format(initDate);

        date.setText(date1);
        c = 3;



    }


    @SuppressLint("SetTextI18n")
    public void OnAddTime(View view) {
        // when every input is selected we get value of 6, else we get less than 6 which means
        // one or more options havent been selected yet.
        int i = a + b + c;

        if (i == 6) {

            String str_date = sdate;
            String str_starttime = sstime;
            String str_endtime = setime;
            String str_uniquecode = LoginPage.user.uniquecode; //get the unique code of the user
            String str_employee = LoginPage.user.fullname;

            // Still need to add a error checker so if rows are empty it doesn't let you register
            // for now it wont add missing data to db but will crash the app in the process.
            // Fixed by doing int a b c. Now fully error free.

            String type = "addtime";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, str_date, str_starttime, str_endtime, str_uniquecode, str_employee);

            error.setTextColor(0xFF32CD32);
            error.setText("Availability has been added to the database");


        }else
        {
            error.setTextColor(0xFFFF3333);
            error.setText("Error, 1 or more options haven't been selected");
        }


    }

    public void clear(View view) {
        startActivity(new Intent(this, UserMain.class)); //restart the activity
    }

    @Override
    public void onBackPressed() {
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
                        if(schedules.getEmployee().matches(LoginPage.user.fullname)){
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


        requestQueue = Volley.newRequestQueue(UserMain.this);
        requestQueue.add(request);

    }

    private void setuprecyclerview(List<Schedule> mySchedule) {
        RecyclerViewAdAvailability myadapters = new RecyclerViewAdAvailability(this, mySchedule);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapters);
    }


}

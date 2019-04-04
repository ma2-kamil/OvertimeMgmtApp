package com.example.overtimemgmtapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.overtimemgmtapp.R;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JobsActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);


        // Get Data from intent

        String id = getIntent().getExtras().getString("id");
        String title = getIntent().getExtras().getString("Shift_Title");
        String description = getIntent().getExtras().getString("Description");
        String hourlyRate = getIntent().getExtras().getString("Hourly_Rate");
        String date = getIntent().getExtras().getString("Date");
        String startTime = getIntent().getExtras().getString("Start_Time");
        String endTime = getIntent().getExtras().getString("End_Time");
        String employee = getIntent().getExtras().getString("Employee_Name");

        // views

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.ctoolbar);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_title = findViewById(R.id.a_shift_heading);
        TextView tv_date = findViewById(R.id.a_date_text);
        TextView tv_start = findViewById(R.id.a_start_time);
        TextView tv_end = findViewById(R.id.a_end_time);
        TextView tv_rate = findViewById(R.id.a_rate);
        TextView tv_employee = findViewById(R.id.a_employee);
        TextView tv_description = findViewById(R.id.a_description);
        Button bt_book = findViewById(R.id.btn_bookshift);
        Button bt_delete = findViewById(R.id.btn_deleteshift);

        // Formatting the data
        String hourlyRate_f = "£" + hourlyRate + " per hour";

        // DATE
        String date_f;
        Date initDate = null;
        try {
            initDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("EEE d MMM yyyy");
        date_f = formatter.format(initDate);

        // Start Time
        String start_f;
        Date initTime = null;
        try {
            initTime = new SimpleDateFormat("HH:mm:ss").parse(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatters = new SimpleDateFormat("HH:mm a");
        start_f = formatters.format(initTime);

        start_f = "Start: " + start_f;

        // Finish Time
        String finish_f;
        Date initTimes = null;
        try {
            initTimes = new SimpleDateFormat("HH:mm:ss").parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatterss = new SimpleDateFormat("HH:mm a");
        finish_f = formatterss.format(initTimes);
        finish_f = "Finish: " + finish_f;




        // Setting values to the view
        tv_title.setText(title);
        tv_date.setText(date_f);
        tv_start.setText(start_f);
        tv_end.setText(finish_f);
        tv_rate.setText(hourlyRate_f);
        tv_description.setText(description);


        // Hide Booking Button if shiftmanager == 1
        if(LoginPage.user.shiftmanager.equals("1")){
            bt_book.setVisibility(View.INVISIBLE);
            int color = 0xFF32CD32;
            tv_employee.setTextColor(color);

            if (!employee.isEmpty()) {
                tv_employee.setText(employee);
            }
            // Now Shift managers can't book shifts for themselves, but can still check if shifts
            // taken by an employee or still available.
        }
        else if (employee.isEmpty()) {
            int color = 0xFF32CD32;
            tv_employee.setTextColor(color);

            // Show Booking Button
            bt_book.setVisibility(View.VISIBLE);
        }
        else {
            tv_employee.setText(employee);
            // Hide Booking Button
            bt_book.setVisibility(View.INVISIBLE);
        }


        //set title to the shift title
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.titlebar);
        collapsingToolbarLayout.setTitle(title);

        // HIDE DELETE SHIFT IF ShiftManager == 0
        if (LoginPage.user.shiftmanager.equals("0")){
            bt_delete.setVisibility(View.INVISIBLE);
        }

        notificationManager = NotificationManagerCompat.from(this);
    }

    public void BookShift(View view){

        String id = getIntent().getExtras().getString("id");
        String employee = LoginPage.user.fullname;

        String type = "BookShift";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, employee, id);


        Intent a = new Intent(JobsActivity.this, ShowAllShiftsActivity.class);
        try { Thread.sleep(600); } //sleep so it refresh page in time.
        catch (InterruptedException ex) { android.util.Log.d("OvertimeMgmtApp", ex.toString()); }
        finish();
        startActivity(a);
        JobsActivity.this.overridePendingTransition(0, 0);

        // Notification system only works on device which makes request, need to connect to a
        // cloud service for functionality to all users.

        Notification notification = new NotificationCompat.Builder(this, notify.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notify)
                .setContentTitle("Shift Has Been Filled")
                .setContentText("NAME X has taken shift X")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

       // notificationManager.notify(1, notification);


    }



    public void DeleteShift(View view){

        String id = getIntent().getExtras().getString("id");
        String type = "DeleteShift";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, id);

        Intent a = new Intent(JobsActivity.this, ShowAllShiftsActivity.class);
        try { Thread.sleep(600); } //sleep so it refresh page in time.
        catch (InterruptedException ex) { android.util.Log.d("OvertimeMgmtApp", ex.toString()); }
        finish();
        startActivity(a);
        JobsActivity.this.overridePendingTransition(0, 0);

    }


}

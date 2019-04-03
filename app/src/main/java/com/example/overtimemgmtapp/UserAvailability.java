package com.example.overtimemgmtapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserAvailability extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_availability);

        // Get Data from intent

        String id = getIntent().getExtras().getString("id");
        String date = getIntent().getExtras().getString("Date");
        String startTime = getIntent().getExtras().getString("Start_Time");
        String endTime = getIntent().getExtras().getString("End_Time");
        String employee = getIntent().getExtras().getString("Employee_Name");

        // Views


        TextView tv_date = findViewById(R.id.a_date_textu);
        TextView tv_start = findViewById(R.id.a_start_timeu);
        TextView tv_end = findViewById(R.id.a_end_timeu);
        Button bt_delete = findViewById(R.id.btn_deletetime);

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
        tv_date.setText(date_f);
        tv_start.setText(start_f);
        tv_end.setText(finish_f);


    }

    public void DeleteTime(View view){

        String id = getIntent().getExtras().getString("id");

        String type = "DeleteTime";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, id);

        Intent a = new Intent(UserAvailability.this, UserMain.class);
        try { Thread.sleep(600); } //sleep so it refresh page in time.
        catch (InterruptedException ex) { android.util.Log.d("OvertimeMgmtApp", ex.toString()); }
        finish();
        startActivity(a);
        UserAvailability.this.overridePendingTransition(0, 0);

    }


}

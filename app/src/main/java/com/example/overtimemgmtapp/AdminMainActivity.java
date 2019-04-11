package com.example.overtimemgmtapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AdminMainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    // Mostly same code as UserMain. Just using dialogs to get date and times. and formatting to correct format
    // for db use and for display on the app.
    EditText shifttitle, description, hourlyrate;
    TextView date, starttime, endtime;

    String sdate, sstime, setime;

    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);


        shifttitle = findViewById(R.id.et_shifttitle);
        description = findViewById(R.id.et_description);
        hourlyrate = findViewById(R.id.et_hourlyrate);

        date = findViewById(R.id.tv_date);
        starttime = findViewById(R.id.tv_stime);
        endtime = findViewById(R.id.tv_etime);

        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        findViewById(R.id.ib_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();

            }
        });


        findViewById(R.id.ib_stime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint({"SetTextI18n", "DefaultLocale"})
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minuteOfHour) {
                        sstime = (String.format("%02d:%02d", hourOfDay, minuteOfHour) + ":00");
                        starttime.setText((String.format("%02d:%02d", hourOfDay, minuteOfHour)));
                    }
                }, hour, minute, android.text.format.DateFormat.is24HourFormat(context));
                   timePickerDialog.show();

            }
        });


        findViewById(R.id.ib_etime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint({"SetTextI18n", "DefaultLocale"})
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minuteOfHour) {
                        setime= (String.format("%02d:%02d", hourOfDay, minuteOfHour) + ":00");
                        endtime.setText((String.format("%02d:%02d", hourOfDay, minuteOfHour)));
                    }
                }, hour, minute, android.text.format.DateFormat.is24HourFormat(context));
                timePickerDialog.show();

            }
        });


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
                        Intent a = new Intent(AdminMainActivity.this, ShowAllShiftsActivity.class);
                        finish();
                        startActivity(a);
                        AdminMainActivity.this.overridePendingTransition(0, 0);
                        break;
                    case R.id.navigation_usersettings:
                        Intent b = new Intent(AdminMainActivity.this, AccountSettingsActivity.class);
                        finish();
                        startActivity(b);
                        AdminMainActivity.this.overridePendingTransition(0, 0);
                        break;
                }
                return false;
            }
        });


    }



    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }


    @Override
    public void onBackPressed(){

    }




    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month = month + 1;
        String date1 = dayOfMonth + "/" + month + "/" + year;
        date.setText(date1); // Display date in app
        // Storing Date in right format
        String date2 = year + "-" + month + "-" + dayOfMonth;


        Date initDate = null;
        try {
            initDate = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        sdate = formatter.format(initDate);

        date.setText(date1);




    }


    public void OnAddShift(View view){


        String str_shifttitle = shifttitle.getText().toString();
        String str_description = description.getText().toString();
        String str_hourlyrate = hourlyrate.getText().toString();
        String str_date = sdate;
        String str_starttime = sstime;
        String str_endtime = setime;
        String str_uniquecode = LoginPage.user.uniquecode; //get the unique code of the user
        String str_employee = "";

        // Add in compare date to current date
        // Same with times
        // For now error checks for title, description, hourly rate
        int color = 0xffff0000;

        if (str_shifttitle.equals("")){
            shifttitle.setHintTextColor(color);
            shifttitle.setHint("Please enter a shift title");
        }else if(str_description.equals("")){
            description.setHintTextColor(color);
            description.setHint("Please enter a description of the job");
        }else if(str_hourlyrate.equals("")){
            hourlyrate.setHintTextColor(color);
            hourlyrate.setHint("Please enter valid hourly rate e.g. 7.50 ");
        }else {
            String type = "addshift";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, str_shifttitle, str_description, str_hourlyrate, str_date, str_starttime, str_endtime, str_uniquecode, str_employee);
        }

    }


    public void clear(View view){
        startActivity(new Intent(this, AdminMainActivity.class)); //restart the activity
    }


}

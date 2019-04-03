package com.example.overtimemgmtapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {
    EditText fullname, username, password, email, unqiuecode;
    String shiftmanager = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullname = findViewById(R.id.et_fullname);
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        email = findViewById(R.id.et_email);
        unqiuecode = findViewById(R.id.et_uniquecode);
        // Only allow all caps and capitalised uniquecodes.
        unqiuecode.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5), new InputFilter.AllCaps()});




    }

    public void IsManager(View view){
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()){
            case R.id.rb_shiftmanager:
                if (checked)
                    shiftmanager = "1";
                else
                    shiftmanager = "0";
                    break;

        }


    }

    public void OnRegister(View view){

        String str_fullname = fullname.getText().toString();
        String str_username = username.getText().toString();
        String str_password = password.getText().toString();
        String str_email = email.getText().toString();
        String str_shiftmanager = shiftmanager;
        String str_uniquecode = unqiuecode.getText().toString();

        // ERROR CHECKING
        if (str_fullname.isEmpty() || str_username.isEmpty() || str_password.isEmpty()|| str_email.isEmpty() || str_uniquecode.isEmpty()) {

            Context context = getApplicationContext();
            Toast.makeText(context,"Error, One or more fields are empty",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            String type = "register";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, str_fullname, str_username, str_password, str_email, str_shiftmanager, str_uniquecode);
        }



    }


    public void OnInfo(View view){
        Context context = getApplicationContext();
        Toast.makeText(context,"If you are a shift manager please create a new unique code." +
        " If you are registering as an employee please use the unique code provided by your manager.",
        Toast.LENGTH_LONG).show();
    }



}

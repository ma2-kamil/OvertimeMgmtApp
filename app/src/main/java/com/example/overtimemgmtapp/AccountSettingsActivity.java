package com.example.overtimemgmtapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AccountSettingsActivity extends AppCompatActivity {
    // This whole page still needs to be work on, but for now it will used to display the details of the user.
    // Later on will add information such as help, contact. etc.
    TextView tvuser, tvfullname, tvemail, tvuniquecode;
    EditText etpass;
    CheckBox cbpass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


        tvfullname = findViewById(R.id.tv_afullname);
        tvuser = findViewById(R.id.tv_ausername);
        tvemail = findViewById(R.id.tv_aemail);
        tvuniquecode = findViewById(R.id.tv_auniquecode);
        etpass = findViewById(R.id.et_apassword);
        cbpass = findViewById(R.id.cb_showpass);


        final String fullname = LoginPage.user.fullname;
        final String username = LoginPage.user.username;
        final String password = LoginPage.user.password;
        final String email = LoginPage.user.email;
        final String shiftmanager = LoginPage.user.shiftmanager;
        final String uniquecode = LoginPage.user.uniquecode;


        tvfullname.setText(fullname);
        tvuser.setText(username);
        tvemail.setText(email);
        tvuniquecode.setText(uniquecode);
        etpass.setText(password);

        cbpass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    etpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else{
                    etpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });



/// END


        BottomNavigationView navigation = findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        if (LoginPage.user.shiftmanager.equals("1")) {
                            Intent a = new Intent(AccountSettingsActivity.this, AdminMainActivity.class);
                            finish();
                            startActivity(a);
                            AccountSettingsActivity.this.overridePendingTransition(0, 0);
                        } else if (LoginPage.user.shiftmanager.equals("0")) {
                            Intent a = new Intent(AccountSettingsActivity.this, UserMain.class);
                            finish();
                            startActivity(a);
                            AccountSettingsActivity.this.overridePendingTransition(0, 0);
                        }
                        break;
                    case R.id.navigation_showallshifts:
                        Intent b = new Intent(AccountSettingsActivity.this, ShowAllShiftsActivity.class);
                        finish();
                        startActivity(b);
                        AccountSettingsActivity.this.overridePendingTransition(0, 0);
                        break;
                    case R.id.navigation_usersettings:
                        break;
                }
                return false;
            }
        });








    }





   @Override
   public void onBackPressed(){
        // Do Nothing
   }

    public void OnLogout(View view){
        startActivity(new Intent(this, LoginPage.class)); //start the Login activity Again

    }

}
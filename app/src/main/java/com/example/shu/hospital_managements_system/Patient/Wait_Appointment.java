package com.example.shu.hospital_managements_system.Patient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shu.hospital_managements_system.R;

public class Wait_Appointment extends AppCompatActivity {


    String username,password,user_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait__appointment);



        Bundle bb = getIntent().getExtras();
        username = bb.getString("username");
        password = bb.getString("password");
        user_type = bb.getString("user_type");
    }
}

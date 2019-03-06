package com.example.shu.hospital_managements_system.Doctor;

import android.database.Cursor;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.shu.hospital_managements_system.DatabaseHelper;
import com.example.shu.hospital_managements_system.R;

import java.util.ArrayList;

public class Specialization extends AppCompatActivity {

    ListView lvs;
    String s, username, password, user_type;
    EditText et;
    ArrayList<String> sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialization);



        Bundle bb = getIntent().getExtras();
        username = bb.getString("username");
        password = bb.getString("password");
        user_type = bb.getString("user_type");

        lvs = (ListView) findViewById(R.id.lv_specialization);
        et = (EditText) findViewById(R.id.et_spl);

        sp = new ArrayList<>();

        DatabaseHelper dbh = new DatabaseHelper(this);
        Cursor y = dbh.checkduplicates_in_user_credentials(username, password, getResources().getString(R.string.doctor_slot));

        if (y.moveToFirst()) {
            String br = y.getString(2);

            for (int i = 0; i < br.length(); i++) {
                if (br.charAt(i) == '_') {
                    if (i + 1 < br.length()) {
                        String ad = "";
                        for (int j = i + 1; j < br.length(); j++) {

                            if (br.charAt(j) == '_') {
                                i = j - 1;
                                break;
                            }
                            ad += br.charAt(j);
                        }
                        if (ad.length() != 0)
                            sp.add(ad);
                    }
                }
            }
            if (sp.size() > 0) {
                ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sp);
                lvs.setAdapter(adapter);
            }else {
                com.example.shu.hospital_managements_system.Message.message(Specialization.this, "Sorry, You have No Specialization");
            }
        } else {
            com.example.shu.hospital_managements_system.Message.message(Specialization.this, "Sorry, You have No Specialization");
        }
    }

    public void onClick(View view) {
        s = et.getText().toString();

        if (s.length() > 0) {
            DatabaseHelper db = new DatabaseHelper(this);
            Cursor y = db.checkduplicates_in_user_credentials(username, password, getResources().getString(R.string.doctor_slot));

            if (y.moveToFirst()) {
                boolean b = db.update_slot(username, password, y.getString(2) + "_" + s, y.getString(3), y.getString(4), y.getString(5));
                if (b) {
                    com.example.shu.hospital_managements_system.Message.message(Specialization.this, "Your Specialization Has been Updated");
                } else {
                    com.example.shu.hospital_managements_system.Message.message(Specialization.this, "Some Error Occured, Try Again");
                }
            } else {
                boolean b = db.insert_slot(username, password, "_" + s, "-", "-", "Y");
                if (b) {
                   com.example.shu.hospital_managements_system.Message.message(Specialization.this, "Your Specialization Has been Inserted");
                } else {
                    com.example.shu.hospital_managements_system.Message.message(Specialization.this, "Some Error Occured, Try Again");
                }
            }

            sp.add(s);
            ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sp);
            lvs.setAdapter(adapter);
            db.close();
        } else {
            com.example.shu.hospital_managements_system.Message.message(Specialization.this, "Please Write Your Specialization");
        }
    }
    }


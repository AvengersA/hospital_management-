package com.example.shu.hospital_managements_system;

import android.content.Context;
import android.widget.Toast;

public class Message {


    public static void message(Context context, String message) {



        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }

}
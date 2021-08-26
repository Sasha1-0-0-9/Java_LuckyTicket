package com.example.luckyticket_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    private static final int MAX_NUMBER_OF_DIGITS = 6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCheckNumber = findViewById(R.id.btnCheckNumber);
        btnCheckNumber.setOnClickListener(view -> {
            checkTicket();
        });
    }

    public void checkTicket(){
            EditText text = findViewById(R.id.etTicketNumber);
            String ticket_number = text.getText().toString();
            ImageView bulb = findViewById(R.id.imgBulb);

            Drawable green = ContextCompat.getDrawable(this, getResources()
                    .getIdentifier("@drawable/ic_green_bulb", null, getPackageName()));

            Drawable red = ContextCompat.getDrawable(this, getResources()
                    .getIdentifier("@drawable/ic_red_bulb", null, getPackageName()));


            if (ticket_number.length() == MAX_NUMBER_OF_DIGITS) {
                int left_sum = ticket_number.substring(0,3).chars().sum();
                int right_sum = ticket_number.substring(3,6).chars().sum();

                if (left_sum == right_sum){
                    bulb.setImageDrawable(green);
                    Toast.makeText(this, "Lucky", Toast.LENGTH_LONG).show();
                }
                else{
                    bulb.setImageDrawable(red);
                    Toast.makeText(this, "Unlucky", Toast.LENGTH_LONG).show();
                }

                backToDefault(bulb);

            } else {
                Toast.makeText(this, "Too short. Enter 6 digits", Toast.LENGTH_LONG).show();
            }

    }

    private void backToDefault(ImageView bulb){
        Drawable grey = ContextCompat.getDrawable(this, getResources()
                .getIdentifier("@drawable/ic_grey_bulb", null, getPackageName()));
        Timer t = new java.util.Timer();
        t.schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        bulb.setImageDrawable(grey);
                        t.cancel();
                    }
                },
                3000
        );
    }
}
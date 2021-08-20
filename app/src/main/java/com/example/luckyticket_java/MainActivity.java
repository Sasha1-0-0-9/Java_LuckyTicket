package com.example.luckyticket_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void check_onClick(View view){
        EditText text = findViewById(R.id.digits);
        String ticket_number = text.getText().toString();
        ImageView bulb = findViewById(R.id.bulb);

        Drawable green = ContextCompat.getDrawable(this, getResources()
                .getIdentifier("@drawable/ic_green_bulb", null, getPackageName()));

        Drawable red = ContextCompat.getDrawable(this, getResources()
                .getIdentifier("@drawable/ic_red_bulb", null, getPackageName()));

        int left_sum = 0;
        int  right_sum = 0;

        if (ticket_number.length() == 6) {

            for(int i = 0; i < ticket_number.length(); i++){
                if (i < 3){
                    left_sum += ticket_number.charAt(i);
                }
                else{
                    right_sum += ticket_number.charAt(i);
                }
            }
            if (left_sum == right_sum){
                bulb.setImageDrawable(green);
                Toast.makeText(this, "Lucky", Toast.LENGTH_LONG).show();
            }

            else{
                bulb.setImageDrawable(red);
                Toast.makeText(this, "Unlucky", Toast.LENGTH_LONG).show();
            }
            // Возвращает лампочку в исходное состояние через 3 секунды
            backToDefault(bulb);

        } else {
            Toast.makeText(this, "Something went wrong. Try again!", Toast.LENGTH_LONG).show();
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
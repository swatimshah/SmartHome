package com.example.smarthome;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button circle1, circle2, circle3, circle4;
    Boolean bulb1Flag = false;
    Boolean bulb2Flag = false;
    Boolean bulb3Flag = false;
    Boolean bulb4Flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("SmartHome", "Enabling AWS SDK logging");
        java.util.logging.Logger.getLogger("com.amazonaws").setLevel(java.util.logging.Level.ALL);
        java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.ALL);

        circle1 = (Button) findViewById(R.id.bulb1);
        circle2 = (Button) findViewById(R.id.bulb2);
        circle3 = (Button) findViewById(R.id.bulb3);
        circle4 = (Button) findViewById(R.id.bulb4);

//        circle1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (bulb1Flag.booleanValue() == true) {
//
//                    // Create Request to server and get response
//
//                    MyNetworkTaskForOff offEsp = new MyNetworkTaskForOff();
//                    offEsp.createMyAsyncTaskForOffLed1();
//
//                    circle1.setBackgroundResource(R.drawable.red_circle);
//                    Toast.makeText(getApplicationContext(), "Switch-1 Off!", Toast.LENGTH_SHORT).show();
//                    bulb1Flag = false;
//                } else {
//
//                    // Create Request to server and get response
//
//                    MyNetworkTaskForOn onEsp = new MyNetworkTaskForOn();
//                    onEsp.createMyAsyncTaskForOnLed1();
//
//                    circle1.setBackgroundResource(R.drawable.circle);
//                    Toast.makeText(getApplicationContext(), "Switch-1 On!", Toast.LENGTH_SHORT).show();
//                    bulb1Flag = true;
//                }
//            }
//        });
        circle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bulb2Flag.booleanValue() == true) {

                    // Create Request to server and get response

                    MyNetworkTaskForOff offEsp = new MyNetworkTaskForOff(MainActivity.this);
                    offEsp.createMyAsyncTaskForOffLed2();

                    circle2.setBackgroundResource(R.drawable.red_circle);
                    Toast.makeText(getApplicationContext(), "Switch-2 Off!", Toast.LENGTH_SHORT).show();
                    bulb2Flag = false;
                } else {

                    // Create Request to server and get response

                    MyNetworkTaskForOn onEsp = new MyNetworkTaskForOn(MainActivity.this);
                    onEsp.createMyAsyncTaskForOnLed2();

                    circle2.setBackgroundResource(R.drawable.circle);
                    Toast.makeText(getApplicationContext(), "Switch-2 On!", Toast.LENGTH_SHORT).show();
                    bulb2Flag = true;
                }
            }
        });

//        circle3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (bulb3Flag.booleanValue() == true) {
//
//                    // Create Request to server and get response
//
//                    MyNetworkTaskForOff offEsp = new MyNetworkTaskForOff();
//                    offEsp.createMyAsyncTaskForOffLed3();
//
//                    circle3.setBackgroundResource(R.drawable.red_circle);
//                    Toast.makeText(getApplicationContext(), "Switch-3 Off!", Toast.LENGTH_SHORT).show();
//                    bulb3Flag = false;
//                } else {
//
//                    // Create Request to server and get response
//
//                    MyNetworkTaskForOn onEsp = new MyNetworkTaskForOn();
//                    onEsp.createMyAsyncTaskForOnLed3();
//
//                    circle3.setBackgroundResource(R.drawable.circle);
//                    Toast.makeText(getApplicationContext(), "Switch-3 On!", Toast.LENGTH_SHORT).show();
//                    bulb3Flag = true;
//                }
//            }
//        });

        circle4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bulb4Flag.booleanValue() == true) {

                    // Create Request to server and get response

                    MyNetworkTaskForOff offEsp = new MyNetworkTaskForOff(MainActivity.this);
                    offEsp.createMyAsyncTaskForOffLed4();

                    circle4.setBackgroundResource(R.drawable.red_circle);
                    Toast.makeText(getApplicationContext(), "Switch-4 Off!", Toast.LENGTH_SHORT).show();
                    bulb4Flag = false;
                } else {

                    // Create Request to server and get response

                    MyNetworkTaskForOn onEsp = new MyNetworkTaskForOn(MainActivity.this);
                    onEsp.createMyAsyncTaskForOnLed4();

                    circle4.setBackgroundResource(R.drawable.circle);
                    Toast.makeText(getApplicationContext(), "Switch-4 On!", Toast.LENGTH_SHORT).show();
                    bulb4Flag = true;
                }
            }
        });


    }
}

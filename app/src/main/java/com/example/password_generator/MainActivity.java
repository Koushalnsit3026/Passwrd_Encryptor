package com.example.password_generator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);                 //Set Content View
    }

    public void aes(View view) {                //Creating "aes" function
        Intent intent = new Intent(MainActivity.this, AES_.class);          //Starting new Activity "AES_"
        startActivity(intent);
    }

}



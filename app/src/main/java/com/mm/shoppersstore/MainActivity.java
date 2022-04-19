package com.mm.shoppersstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    private Button nextScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //btn and intent creation for navigation
        nextScreen=(Button) findViewById(R.id.startBtn);
        nextScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent agn = new Intent(MainActivity.this, Login.class);
                startActivity(agn);

            }
        });

    }

    public void onClick(View v) {
// TODO Auto-generated method stub

    }
}

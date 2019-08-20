package com.example.lakshay.mydictionary;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button click;

    public static TextView data;

    public static EditText myWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        click=findViewById(R.id.mySearchButton);
        data=findViewById(R.id.myMeaning);
        myWord=findViewById(R.id.myWord);

    }



    public void onClick(View view)
    {

        fetchData process=new fetchData();
        process.execute();


    }

}

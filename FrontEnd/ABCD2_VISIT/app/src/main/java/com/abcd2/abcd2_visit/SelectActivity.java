package com.abcd2.abcd2_visit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), UserActivity.class);
                        intent.putExtra("school","KUMC-Ansan");
                        startActivity(intent);
                    }
                }
        );
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), UserActivity.class);
                        intent.putExtra("school","KUMC-Anam");
                        startActivity(intent);
                    }
                }
        );
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), UserActivity.class);
                        intent.putExtra("school","KUMC-Guro");
                        startActivity(intent);
                    }
                }
        );
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), UserActivity.class);
                        intent.putExtra("school","KHMC");
                        startActivity(intent);
                    }
                }
        );
    }
}

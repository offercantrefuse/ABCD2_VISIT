package com.abcd2.abcd2_visit;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EnterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            //Do Something
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent i = new Intent(EnterActivity.this, SelectActivity.class); // xxx가 현재 activity,
                //yyy가 이동할 activity
                startActivity(i);
                finish();
            }
        }, 1000); // 1000ms
    }
}

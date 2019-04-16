package com.abcd2.abcd2_visit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserActivity extends AppCompatActivity {

    EditText Examiner;
    EditText PatientID;
    EditText Password;
    String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent intent = getIntent();
        final String school = intent.getStringExtra("school");

        Examiner = (EditText) findViewById(R.id.editText1);
        PatientID = (EditText) findViewById(R.id.editText2);

        RadioGroup Rg = (RadioGroup) findViewById(R.id.radioGroup);
        Rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton1) {
                    sex = "M";
                }
                else{
                    sex = "F";
                }
            }
        });
        Password = (EditText) findViewById(R.id.editText4);

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        if (Integer.parseInt(Password.getText().toString()) == 1212) {
                            Intent intent = new Intent(v.getContext(), ABCD2Activity.class);
                            intent.putExtra("school", school);
                            intent.putExtra("examiner", Examiner.getText().toString());
                            intent.putExtra("patientID", PatientID.getText().toString());
                            intent.putExtra("sex", sex);
                            SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String time = sdfNow.format(new Date(System.currentTimeMillis()));
                            intent.putExtra("timeStamp", time);
                            startActivity(intent);
                        } else {
                            Toast.makeText(v.getContext(), "WRONG PASSWORD", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}

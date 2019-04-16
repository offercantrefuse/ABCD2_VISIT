package com.abcd2.abcd2_visit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class VISITActivity extends AppCompatActivity {
    int tmp1 = -1, tmp2=-1, tmp3=-1, tmp5=-1, tmp6=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);

        Intent intent = getIntent();
        final String school = intent.getStringExtra("school");
        final String examiner = intent.getStringExtra("examiner");
        final String patientID = intent.getStringExtra("patientID");
        final String sex = intent.getStringExtra("sex");
        final String timeStamp = intent.getStringExtra("timeStamp");
        final int age60 = intent.getIntExtra("age60",0);
        final int bp = intent.getIntExtra("bp",0);
        final int tia = intent.getIntExtra("tia",0);
        final int duration = intent.getIntExtra("duration",0);
        final int diabetes = intent.getIntExtra("diabetes",0);

        RadioGroup Rg1 = (RadioGroup) findViewById(R.id.radioGroup1);
        Rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId== R.id.radioButton1) {
                    tmp1 = 0;
                } else if(checkedId == R.id.radioButton2) {
                    tmp1 = 1;
                }
            }
        });

        RadioGroup Rg2 = (RadioGroup) findViewById(R.id.radioGroup2);
        Rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId== R.id.radioButton3) {
                    tmp2 = 0;
                } else if(checkedId == R.id.radioButton4) {
                    tmp2 = 1;
                }
            }
        });

        RadioGroup Rg3 = (RadioGroup) findViewById(R.id.radioGroup3);
        Rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId== R.id.radioButton5) {
                    tmp3 = 0;
                } else if(checkedId == R.id.radioButton6) {
                    tmp3 = 1;
                }
            }
        });

        RadioGroup Rg5 = (RadioGroup) findViewById(R.id.radioGroup5);
        Rg5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId== R.id.radioButton10) {
                    tmp5 = 0;
                } else if(checkedId == R.id.radioButton11) {
                    tmp5 = 2;
                }
            }
        });

        RadioGroup Rg6 = (RadioGroup) findViewById(R.id.radioGroup6);
        Rg6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId== R.id.radioButton13) {
                    tmp6 = 0;
                } else if(checkedId == R.id.radioButton14) {
                    tmp6 = 1;
                }
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        if(tmp1==-1 || tmp2==-1 || tmp3==-1 || tmp5==-1 || tmp6==-1) {
                            Toast.makeText(getApplicationContext(),"항목을 체크해주세요", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent intent = new Intent(v.getContext(), DurationActivity.class);
                            intent.putExtra("school", school);
                            intent.putExtra("examiner", examiner);
                            intent.putExtra("patientID", patientID);
                            intent.putExtra("sex", sex);
                            intent.putExtra("timeStamp", timeStamp);
                            intent.putExtra("age60", age60);
                            intent.putExtra("bp", bp);
                            intent.putExtra("tia", tia);
                            intent.putExtra("duration", duration);
                            intent.putExtra("diabetes", diabetes);
                            intent.putExtra("visualSx", tmp1);
                            intent.putExtra("imbalance", tmp2);
                            intent.putExtra("sensory", tmp3);
                            intent.putExtra("image", tmp5);
                            intent.putExtra("ischemic", tmp6);
                            startActivity(intent);
                        }
                    }
                }
        );

    }
}

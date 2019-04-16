package com.abcd2.abcd2_visit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class CheckActivity extends AppCompatActivity {
    private NetworkService networkService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        networkService = RetrofitClientInstance.getRetrofitInstance().create(NetworkService.class);

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
        final int visualSx = intent.getIntExtra("visualSx",0);
        final int imbalance = intent.getIntExtra("imbalance",0);
        final int sensory = intent.getIntExtra("sensory",0);
        final int image = intent.getIntExtra("image",0);
        final int ischemic = intent.getIntExtra("ischemic",0);
        final String duration2 = intent.getStringExtra("duration2");
        final String frequency = intent.getStringExtra("frequency");
        final String duration2Text = intent.getStringExtra("duration2Text");
        final String frequencyText = intent.getStringExtra("frequencyText");

        final int abcd2 = age60 + bp + tia + duration + diabetes;
        final int visit = visualSx + imbalance + sensory + image + ischemic;
        final int abcd2visit = abcd2 + visit;

        TextView Abcd2 = (TextView)findViewById(R.id.textView2);
        TextView Visit = (TextView)findViewById(R.id.textView4);
        TextView Abcd2_visit = (TextView)findViewById(R.id.textView6);

        Abcd2.setText(String.valueOf(abcd2));
        Visit.setText(String.valueOf(visit));
        Abcd2_visit.setText(String.valueOf(abcd2visit));

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), EnterActivity.class);
                        Information information = new Information();
                        information.setSchool(school);
                        information.setExaminer(examiner);
                        information.setPatientID(patientID);
                        information.setSex(sex);
                        information.setTimeStamp(timeStamp);
                        information.setAge60(age60);
                        information.setBp(bp);
                        information.setTia(tia);
                        information.setDuration(duration);
                        information.setDiabetes(diabetes);
                        information.setVisualSx(visualSx);
                        information.setImbalance(imbalance);
                        information.setSensory(sensory);
                        information.setImage(image);
                        information.setIschemic(ischemic);
                        information.setDuration2(duration2);
                        information.setFrequency(frequency);
                        information.setDuration2Text(duration2Text);
                        information.setFrequencyText(frequencyText);
                        information.setAbcd2(abcd2);
                        information.setVisit(visit);
                        information.setAbcd2visit(abcd2visit);
                        // DB 저장
                        Call<Information> call = networkService.createUser(information);
                        call.enqueue(new Callback<Information>() {
                            @Override
                            public void onResponse(Call<Information> call, Response<Information> response) {
                                Toast.makeText(getApplicationContext(),"결과가 저장되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onFailure(Call<Information> call, Throwable t) {
                                Toast.makeText(getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
        );

    }
}

package com.abcd2.abcd2_visit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DurationActivity extends AppCompatActivity {

    EditText Duration2;
    EditText Frequency;
    EditText Duration2Text;
    EditText FrequencyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duration);

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

        Duration2 = (EditText) findViewById(R.id.editText1);
        Frequency = (EditText) findViewById(R.id.editText2);
        Duration2Text = (EditText) findViewById(R.id.editText);
        FrequencyText = (EditText) findViewById(R.id.editText3) ;

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        if(Duration2.getText().toString().equals("") || Frequency.getText().toString().equals("")) {
                            Toast.makeText(getApplicationContext(),"모두 입력해주세요", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent intent = new Intent(v.getContext(), CheckActivity.class);
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
                            intent.putExtra("visualSx", visualSx);
                            intent.putExtra("imbalance", imbalance);
                            intent.putExtra("sensory", sensory);
                            intent.putExtra("image", image);
                            intent.putExtra("ischemic", ischemic);

                            intent.putExtra("duration2", Duration2.getText().toString());
                            intent.putExtra("frequency", Frequency.getText().toString());
                            intent.putExtra("duration2Text", Duration2Text.getText().toString());
                            intent.putExtra("frequencyText", FrequencyText.getText().toString());

                            startActivity(intent);
                        }
                    }
                }
        );

    }
}

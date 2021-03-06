package com.kh.project4_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Switch switchStart;
    TextView txtLike;
    RadioGroup rGroup;
    RadioButton rdoP, rdoQ, rdoR;
    ImageView imgAndroid;
    Button btnFinish, btnRestart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchStart = findViewById(R.id.switchStart);
        txtLike = findViewById(R.id.txtLike);
        rGroup = findViewById(R.id.rGroup);
        rdoP = findViewById(R.id.rdoP);
        rdoQ = findViewById(R.id.rdoQ);
        rdoR = findViewById(R.id.rdoR);
        imgAndroid = findViewById(R.id.imgAndroid);
        btnFinish = findViewById(R.id.btnFinish);
        btnRestart = findViewById(R.id.btnRestart);
        // 시작 스위치 동작
        switchStart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    txtLike.setVisibility(View.VISIBLE);
                    rGroup.setVisibility(View.VISIBLE);
                    rdoP.setVisibility(View.VISIBLE);
                    rdoQ.setVisibility(View.VISIBLE);
                    rdoR.setVisibility(View.VISIBLE);
                    imgAndroid.setVisibility(View.VISIBLE);
                    btnFinish.setVisibility(View.VISIBLE);
                    btnRestart.setVisibility(View.VISIBLE);
                } else {
                    txtLike.setVisibility(View.INVISIBLE);
                    rGroup.setVisibility(View.INVISIBLE);
                    rdoP.setVisibility(View.INVISIBLE);
                    rdoQ.setVisibility(View.INVISIBLE);
                    rdoR.setVisibility(View.INVISIBLE);
                    imgAndroid.setVisibility(View.INVISIBLE);
                    btnFinish.setVisibility(View.INVISIBLE);
                    btnRestart.setVisibility(View.INVISIBLE);
                }
            }
        });
        // 파이 라디오버튼 동작
        rdoP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    imgAndroid.setImageResource(R.drawable.pie);
                }
            }
        });
        // 큐 라디오버튼 동작
        rdoQ.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    imgAndroid.setImageResource(R.drawable.q10);
                }
            }
        });
        // 알 라디오버튼 동작
        rdoR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    imgAndroid.setImageResource(R.drawable.r11);
                }
            }
        });
        // 종료 버튼 동작
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 처음으로 버튼 동작
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });
    }

    private void init() {
        imgAndroid.setImageResource(R.drawable.pie);
        rdoP.setChecked(true);
        switchStart.setChecked(false);
        txtLike.setVisibility(View.INVISIBLE);
        rGroup.setVisibility(View.INVISIBLE);
        rdoP.setVisibility(View.INVISIBLE);
        rdoQ.setVisibility(View.INVISIBLE);
        rdoR.setVisibility(View.INVISIBLE);
        imgAndroid.setVisibility(View.INVISIBLE);
        btnFinish.setVisibility(View.INVISIBLE);
        btnRestart.setVisibility(View.INVISIBLE);
    }
}
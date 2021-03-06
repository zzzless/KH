package com.kh.prac7_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.img);

    }
    // 옵션 메뉴 생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 메뉴 전개자(inflater : xml의 태그를 java 객체로 변환 시킴)
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // 메뉴 옵션 선택
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.mnuDog:
                img.setImageResource(R.drawable.dog);
                break;
            case R.id.mnuCat:
                img.setImageResource(R.drawable.cat);
                break;
            case R.id.mnuRabbit:
                img.setImageResource(R.drawable.rabbit);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
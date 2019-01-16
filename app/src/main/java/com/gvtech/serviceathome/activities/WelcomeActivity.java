package com.gvtech.serviceathome.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.LinearLayout;

import com.gvtech.serviceathome.activities.user.HomeActivity;
import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.utils.SharedStore;

public class WelcomeActivity extends AppCompatActivity {

    String color ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (SharedStore.getCurrentTheme(getApplicationContext()) == 0){
            setTheme(R.style.LightTheme);
            color = "#000000";
        }else {
            setTheme(R.style.BlackTheme);
            color = "#FFFFFF";
        }
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='"+color+"'>"+getString(R.string.select_theme_title)+"</font>"));

        LinearLayout llThemeWhite = findViewById(R.id.ll_theme_white);
        LinearLayout llThemeBlack = findViewById(R.id.ll_theme_black);
        Button btnStart = findViewById(R.id.btn_start);

        llThemeWhite.setOnClickListener(v -> {
            Intent activity = this.getIntent();
            SharedStore.setCurrentTheme(getApplicationContext(),0);
            this.finish();
            startActivity(activity);
            //onCreate(savedInstanceState);
        });

        llThemeBlack.setOnClickListener(v -> {
            Intent activity = this.getIntent();
            SharedStore.setCurrentTheme(getApplicationContext(),1);
            this.finish();
            startActivity(activity);
            //onCreate(savedInstanceState);
        });

        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            this.finish();
        });
    }
}

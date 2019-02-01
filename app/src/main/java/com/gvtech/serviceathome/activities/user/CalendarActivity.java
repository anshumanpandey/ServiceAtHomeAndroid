package com.gvtech.serviceathome.activities.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

import com.gvtech.serviceathome.R;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.simpleCalendarView);

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {

        });
    }
}

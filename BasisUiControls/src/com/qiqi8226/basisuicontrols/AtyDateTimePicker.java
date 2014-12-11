package com.qiqi8226.basisuicontrols;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class AtyDateTimePicker extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty_date_time_picker);
        
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvTime = (TextView) findViewById(R.id.tvTime);
        
        findViewById(R.id.btnSetDate).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AtyDateTimePicker.this, new DatePickerDialog.OnDateSetListener() {
                    
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                            int dayOfMonth) {
                        tvDate.setText(String.format("%d-%d-%d", year, monthOfYear, dayOfMonth));
                    }
                }, 2014, 11, 31).show();
            }
        });
        
        findViewById(R.id.btnSetTime).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                new TimePickerDialog(AtyDateTimePicker.this, new TimePickerDialog.OnTimeSetListener() {
                    
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tvTime.setText(String.format("%d:%d", hourOfDay, minute));
                    }
                }, 0, 0, true).show();
            }
        });
    }
    
    private TextView tvDate;
    private TextView tvTime;
}

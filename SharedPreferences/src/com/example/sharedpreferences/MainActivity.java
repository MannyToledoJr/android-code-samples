package com.example.sharedpreferences;

import java.util.Map;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;
    private TextView tv;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb1 = (CheckBox) findViewById(R.id.cb1);
        cb2 = (CheckBox) findViewById(R.id.cb2);
        cb3 = (CheckBox) findViewById(R.id.cb3);
        cb4 = (CheckBox) findViewById(R.id.cb4);
        tv = (TextView) findViewById(R.id.tv);

        sp = getSharedPreferences("mysp", MODE_PRIVATE);
        findViewById(R.id.btn).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Editor edt = sp.edit();
                edt.putBoolean("cb1", cb1.isChecked());
                edt.putBoolean("cb2", cb2.isChecked());
                edt.putBoolean("cb3", cb3.isChecked());
                edt.putBoolean("cb4", cb4.isChecked());
                edt.commit();
                Toast.makeText(MainActivity.this, "Save Successful", Toast.LENGTH_SHORT).show();
            }
        });

        Map<String, ?> map = sp.getAll();
        tv.setTextSize(25);
        tv.setText("The content of \"mysp\" is:");
        if (map.size() == 0) {
            tv.append("\n\nNo Element Found.");
        } else {
            for (Map.Entry<String, ?> i : map.entrySet()) {
                tv.append("\n\n"+i.getKey()+": "+i.getValue());
            }
        }
    }
}

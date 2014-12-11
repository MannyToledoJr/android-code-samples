package com.qiqi8226.basisuicontrols;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AtyRadioButton extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty_radio_button);
        
        rd1 = (RadioButton) findViewById(R.id.radio1);
        rd2 = (RadioButton) findViewById(R.id.radio2);
        rd3 = (RadioButton) findViewById(R.id.radio3);
        rd4 = (RadioButton) findViewById(R.id.radio4);
        
        findViewById(R.id.btnRadio).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                String result = "";
                if (rd1.isChecked())
                    result += "1";
                if (rd2.isChecked())
                    result += "2";
                if (rd3.isChecked())
                    result += "3";
                if (rd4.isChecked())
                    result += "4";
                if (result == "")
                    result += "empty";
                Toast.makeText(AtyRadioButton.this, "You have Checked: "+result, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private RadioButton rd1;
    private RadioButton rd2;
    private RadioButton rd3;
    private RadioButton rd4;
}

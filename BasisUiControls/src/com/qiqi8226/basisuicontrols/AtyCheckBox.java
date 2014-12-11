package com.qiqi8226.basisuicontrols;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

public class AtyCheckBox extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty_check_box);
        
        cb1 = (CheckBox) findViewById(R.id.cb1);
        cb2 = (CheckBox) findViewById(R.id.cb2);
        cb3 = (CheckBox) findViewById(R.id.cb3);
        cb4 = (CheckBox) findViewById(R.id.cb4);
        
        findViewById(R.id.btnCb).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                String message = "";
                
                if (cb1.isChecked())
                    message += "CheckBox1\n";
                if (cb2.isChecked())
                    message += "CheckBox2\n";
                if (cb3.isChecked())
                    message += "CheckBox3\n";
                if (cb4.isChecked())
                    message += "CheckBox4\n";
                
                new AlertDialog.Builder(AtyCheckBox.this).setTitle("result is:").setMessage(message)
                    .setPositiveButton("close", null).show();
                
            }
        });
    }
    
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;
}

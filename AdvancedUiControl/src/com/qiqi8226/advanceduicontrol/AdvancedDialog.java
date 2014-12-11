package com.qiqi8226.advanceduicontrol;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class AdvancedDialog extends Activity {
    private TextView tv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_dialog);
        tv = (TextView) findViewById(R.id.tv);
                
        findViewById(R.id.btnStartDialog).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AdvancedDialog.this);
                LayoutInflater inflater = AdvancedDialog.this.getLayoutInflater();
                final View view = inflater.inflate(R.layout.my_dialog, null);
                builder.setView(view);
                builder.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText etName = (EditText) view.findViewById(R.id.etName);
                        EditText etAge = (EditText) view.findViewById(R.id.etAge);
                        tv.setText(String.format("Name: %s\n\nAge: %s", etName.getText(), etAge.getText()));
                        tv.setTextSize(30);
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setTitle("Please Input Information:").create().show();
            }
            
        });
    }
}

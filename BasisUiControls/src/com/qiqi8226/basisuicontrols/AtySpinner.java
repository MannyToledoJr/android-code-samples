package com.qiqi8226.basisuicontrols;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class AtySpinner extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty_spinner);
        
        sp = (Spinner) findViewById(R.id.sp);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AtySpinner.this, android.R.layout.simple_spinner_item);
        sp.setAdapter(adapter);
        
        adapter.add("item0");
        adapter.add("item1");
        adapter.add("item2");
        
        findViewById(R.id.btnSp).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Toast.makeText(AtySpinner.this, "Current: "+sp.getSelectedItem().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    private Spinner sp;
}

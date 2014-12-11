package com.qiqi8226.basisuicontrols;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class AtyEditText extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty_edit_text);
        
        et1 = (EditText) findViewById(R.id.et1);
        
        findViewById(R.id.btnEdit).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et1.getText()))
                    Toast.makeText(AtyEditText.this, "empty", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AtyEditText.this, et1.getText(), Toast.LENGTH_SHORT).show();;
            }
        });
    }
    
    private EditText et1;
}

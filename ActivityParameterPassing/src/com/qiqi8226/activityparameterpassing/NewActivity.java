/**
 * Copyright 2014 qiqi8226
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qiqi8226.activityparameterpassing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class NewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        
        tv = (TextView) findViewById(R.id.tv);
        Bundle bundle = getIntent().getExtras();
        String string = bundle.getString(MainActivity.MESSAGE);
        tv.setText(string);
        
        tv.append("\n\nThis is the information from NewActivity");
        tv.setTextSize(30);
        
        findViewById(R.id.btnDone).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(RESULT, "This is the information from NewActivity");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
    
    private TextView tv;
    public static final String RESULT = "com.qiqi8226.NewActivity";
}

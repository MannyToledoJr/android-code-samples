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

public class MainActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnStart1).setOnClickListener(this);
        findViewById(R.id.btnStart2).setOnClickListener(this);
        tv = (TextView) findViewById(R.id.tvM);
        tv.setText("First Open This Activity");
        tv.setTextSize(30);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString(MESSAGE, "This is the information from MainActivity");
        Intent intent = new Intent(this, NewActivity.class);
        intent.putExtras(bundle);
        switch (v.getId()) {
        case R.id.btnStart1:
            startActivityForResult(intent, 1);
            break;
        case R.id.btnStart2:
            startActivityForResult(intent, 2);
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        tv.setText("Get the result from NewActivity:");
        tv.append("\n\nrequestCode is: " + requestCode);
        tv.append("\n\nresultCode is: " + resultCode);
        tv.setTextSize(30);
    }

    private TextView tv;
    public static final String MESSAGE = "com.qiqi8226.mainactivity.message";
}
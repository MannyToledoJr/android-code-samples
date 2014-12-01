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
package com.qiqi8226.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements ServiceConnection {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        findViewById(R.id.btnStartService).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, TimerService.class));
            }
        });
        
        findViewById(R.id.btnDestroyService).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, TimerService.class));
            }
        });
        
        findViewById(R.id.btnBind).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                bindService(new Intent(MainActivity.this, TimerService.class), MainActivity.this, BIND_AUTO_CREATE);
            }
        });
        
        findViewById(R.id.btnUnBind).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                unbindService(MainActivity.this);
            }
        });
        
        findViewById(R.id.btnShow).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                if (timerService == null) 
                    Toast.makeText(MainActivity.this, "Do not Have Bind any TimerService", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Current Time: "+timerService.getTime(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private TimerService timerService = null;
    
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        timerService = ((TimerService.TimerBinder)service).getService();
    }
    
    @Override
    public void onServiceDisconnected(ComponentName name) {
    }
}

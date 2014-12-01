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

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class TimerService extends Service {
    
    public int getTime() {
        return i;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "TimerService onBind", Toast.LENGTH_SHORT).show();
        return timerBinder;
    }

    @Override
    public void onCreate() {
        if (timer == null) {
            timer = new Timer();
            task = new TimerTask() {
                
                @Override
                public void run() {
                    i++;
                }
            };
            timer.schedule(task, 1000, 1000);
        }
        Toast.makeText(this, "TimerService onCreate", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Toast.makeText(this, "TimerService onStart", Toast.LENGTH_SHORT).show();
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        task.cancel();
        timer.cancel();
        task = null;
        timer = null;
        Toast.makeText(this, "TimerService onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "TimerService onUnbind", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    public class TimerBinder extends Binder {
        public TimerService getService() {
            return TimerService.this;
        }
    }
    
    private int i = 0;
    private Timer timer = null;
    private TimerTask task = null;
    private final TimerBinder timerBinder = new TimerBinder();
}

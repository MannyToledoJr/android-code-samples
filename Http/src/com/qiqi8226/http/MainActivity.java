/**
 * Copyright 2014 qiqi8226
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.qiqi8226.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
    private TextView tv;
    private HttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new DefaultHttpClient();

        findViewById(R.id.btnClientGet).setOnClickListener(this);
        findViewById(R.id.btnClientPost).setOnClickListener(this);
        findViewById(R.id.btnGet).setOnClickListener(this);
        findViewById(R.id.btnPost).setOnClickListener(this);
        tv = (TextView) findViewById(R.id.tv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btnClientGet:
            onBtnClientGet();
            break;
        case R.id.btnClientPost:
            onBtnClientPost();
            break;
        case R.id.btnGet:
            onBtnGet();
            break;
        case R.id.btnPost:
            onBtnPost();
            break;
        }
    }

    private void onBtnGet() {

        new AsyncTask<String, String, Void>() {

            @Override
            protected Void doInBackground(String... params) {
                try {
                    String tmp = URLEncoder.encode("这里是有道翻译API", "utf-8");
                    URL url = new URL(params[0]+tmp);
                    URLConnection conn = url.openConnection();
                    InputStream is = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line;
                    while ( (line = br.readLine()) != null) {
                        publishProgress(line);
                    }
                    br.close();
                    is.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                tv.append(values[0]+"\n");
            }
        }.execute("http://fanyi.youdao.com/openapi.do?keyfrom=qiqi8226&key=1401459950&type=data&doctype=xml&version=1.1&q=");
    }

    private void onBtnPost() {
        new AsyncTask<String, String, Void>() {

            @Override
            protected Void doInBackground(String... params) {
                URL url;
                try {
                    url = new URL(params[0]);
                    URLConnection conn = url.openConnection();

                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setUseCaches(false);

                    BufferedWriter bw = 
                            new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
                    bw.write("");
                    bw.flush();

                    BufferedReader br = 
                            new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line;
                    while((line = br.readLine()) != null){
                        publishProgress(line);                    }
                    br.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                tv.append(values[0]+"\n");
            }
        }.execute("http://www.baidu.com/");
    }

    private void onBtnClientGet() {
        new AsyncTask<String, String, Void>(){

            @Override
            protected Void doInBackground(String... params) {
                String tmp;
                try {
                    tmp = URLEncoder.encode("这里是有道翻译API","utf-8");
                    HttpGet get = new HttpGet(params[0]+tmp);
                    HttpResponse response = client.execute(get);
                    tmp = EntityUtils.toString(response.getEntity());
                    publishProgress(tmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                tv.setText(values[0]);
            }
        }.execute("http://fanyi.youdao.com/openapi.do?keyfrom=qiqi8226&key=1401459950&type=data&doctype=xml&version=1.1&q=");
    }

    private void onBtnClientPost() {
        new AsyncTask<String, String, Void>() {

            @Override
            protected Void doInBackground(String... params) {
                try {
                    HttpPost post = new HttpPost(params[0]);

                    List<BasicNameValuePair>list = new ArrayList<BasicNameValuePair>();
                    //                    list.add(new BasicNameValuePair("test", "test"));
                    post.setEntity(new UrlEncodedFormEntity(list));

                    HttpResponse response = client.execute(post);
                    String tmp = EntityUtils.toString(response.getEntity());
                    publishProgress(tmp);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                tv.setText(values[0]);
            }
        }.execute("http://www.baidu.com/");
    }
}

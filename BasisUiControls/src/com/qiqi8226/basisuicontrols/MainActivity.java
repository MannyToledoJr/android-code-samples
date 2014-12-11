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
package com.qiqi8226.basisuicontrols;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    private ArrayAdapter<ListCell> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new ArrayAdapter<ListCell>(this, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        adapter.add(new ListCell(this, new Intent(this, AtyCheckBox.class), "CheckBox"));
        adapter.add(new ListCell(this, new Intent(this, AtyEditText.class), "EditText"));
        adapter.add(new ListCell(this, new Intent(this, AtyRadioButton.class), "RadioButton"));
        adapter.add(new ListCell(this, new Intent(this, AtySpinner.class), "Spinner"));
        adapter.add(new ListCell(this, new Intent(this, AtyDateTimePicker.class), "DateTimePicker"));
        adapter.add(new ListCell(this, new Intent(this, AtyScrollView.class), "ScrollView"));
        adapter.add(new ListCell(this, new Intent(this, AtyImageView.class), "ImageView"));
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        ListCell date = adapter.getItem(position);
        date.startActivity();
        
        super.onListItemClick(l, v, position, id);
    }
}
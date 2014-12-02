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
package com.qiqi8226.sqlite;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity 
implements OnClickListener, AddDialogFragment.AddDialogListener, OnItemLongClickListener {
    private SimpleCursorAdapter adapter;
    private Db db;
    private int itemId;
    private SQLiteDatabase dbwriter,dbreader;
    private EditText et;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Db(this);
        dbreader = db.getReadableDatabase();
        dbwriter = db.getWritableDatabase();

        adapter = new SimpleCursorAdapter(this, R.layout.list_cell, null,
                new String[]{"name","age"}, new int[]{R.id.tvName, R.id.tvAge});
        setListAdapter(adapter);
        updateList();
        et = (EditText) findViewById(R.id.etSearch);
        findViewById(R.id.btnAdd).setOnClickListener(this);
        findViewById(R.id.btnSearch).setOnClickListener(this);
        getListView().setOnItemLongClickListener(this);
    }

    private void updateList() {
        Cursor c = dbreader.query("user", null, null, null, null, null, null);
        adapter.changeCursor(c);
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btnAdd:
            new AddDialogFragment("ADD").show(getFragmentManager(), null);
            break;
        case R.id.btnSearch:

            //Search for item
            Cursor c = dbreader.query("user", new String[]{"name", "age"}, "name=?",
                    new String[]{et.getText().toString()}, null, null, null);
            
            if (c.getCount() == 0)
                new AlertDialog.Builder(this).setMessage("No result Found").create().show();
            else  {
                String message = "";
                while (c.moveToNext())
                    message += ("\nname: "+c.getString(c.getColumnIndex("name"))
                            +"\nage: "+c.getString(c.getColumnIndex("age"))+"\n");
                new AlertDialog.Builder(this).setMessage(message).create().show();
            }
            break;
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onPositiveClick(View view, String request) {
        String name = ((EditText) view.findViewById(R.id.etName)).getText().toString();
        String age = ((EditText) view.findViewById(R.id.etAge)).getText().toString();

        // Add or Modify an item in SQLite
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("age", age);
        if (request == "ADD")
            dbwriter.insert("user", null, cv);
        else if (request == "MODIFY")
            dbwriter.update("user", cv, "_id=?", new String[]{itemId+""});
        updateList();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view,
            int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selections:")
        .setItems(new String[]{"delete", "modify"}, new DialogInterface.OnClickListener() {
            @SuppressLint("NewApi")
            public void onClick(DialogInterface dialog, int which) {
                Cursor c = adapter.getCursor();
                itemId = c.getInt(c.getColumnIndex("_id"));
                switch (which) {
                case 0:
                    // Delete an item in SQLite
                    dbwriter.delete("user", "_id=?", new String[]{itemId+""});
                    updateList();
                    break;
                case 1:
                    new AddDialogFragment("MODIFY").show(getFragmentManager(), null);
                    break;
                }
            }
        }).create().show();
        return true;
    }
}

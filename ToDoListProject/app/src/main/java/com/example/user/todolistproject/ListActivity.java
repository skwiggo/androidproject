package com.example.user.todolistproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 06/09/2016.
 */
public class ListActivity extends AppCompatActivity {

    EditText mTextToSave;
    Button mSaveButton;
    TextView mSavedText;
    TaskDBHelper mHelper;
    ArrayAdapter mAdapter;
    ListView mTaskListView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_to_do_list_item);

        mTextToSave = (EditText) findViewById(R.id.text_to_save);
        mSaveButton = (Button) findViewById(R.id.save_button);
        mSavedText = (TextView) findViewById(R.id.saved_text);
        mTaskListView = (ListView) findViewById(R.id.list_text);
        mHelper = new TaskDBHelper(this);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = String.valueOf(mTextToSave.getText());
                SQLiteDatabase db = mHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(TaskContract.FullTaskEntry.COL_FULL_TASK_TITLE, data);
                db.insertWithOnConflict(TaskContract.FullTaskEntry.TABLE,
                        null,
                        values,
                        SQLiteDatabase.CONFLICT_REPLACE);
                db.close();
                updateUI();
            }
        });
    }

    private void updateUI() {
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(TaskContract.FullTaskEntry.TABLE,
                new String[]{TaskContract.TaskEntry._ID, TaskContract.FullTaskEntry.COL_FULL_TASK_TITLE},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(TaskContract.FullTaskEntry.COL_FULL_TASK_TITLE);
            taskList.add(cursor.getString(idx));
        }

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this,
                    R.layout.full_to_do_list_item,
                    R.id.saved_text,
                    taskList);
            mTaskListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
        cursor.close();
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.audio_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}


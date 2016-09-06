package com.example.user.todolistproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by user on 06/09/2016.
 */
public class ListActivity extends AppCompatActivity {

    EditText mTextToSave;
    Button mSaveButton;
    TextView mSavedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_to_do_list_item);

    }
}

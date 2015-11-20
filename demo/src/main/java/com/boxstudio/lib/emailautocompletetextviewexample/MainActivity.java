package com.boxstudio.lib.emailautocompletetextviewexample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;

import com.boxstudio.editviewlibrary.adapter.EmailAutoCompleteAdapter;
import com.boxstudio.editviewlibrary.view.EmailAutoCompleteTextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EmailAutoCompleteTextView emailAutoCompleteTextView = (EmailAutoCompleteTextView)findViewById(R.id.email_auto_complete_textview);
        emailAutoCompleteTextView.setmType(EmailAutoCompleteTextView.TypeAfterAt);
        emailAutoCompleteTextView.setmEmailArray(new String[]{"@163.com"});
    }

}

package com.boxstudio.lib.emailautocompletetextviewexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.boxstudio.editviewlibrary.view.EmailAutoCompleteTextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EmailAutoCompleteTextView emailAutoCompleteTextView = (EmailAutoCompleteTextView)findViewById(R.id.email_auto_complete_textview);
//        emailAutoCompleteTextView.setmType(EmailAutoCompleteTextView.TypeInline);
//        emailAutoCompleteTextView.setHintTextColor(Color.RED);
//        emailAutoCompleteTextView.setmSuggestionArray(new String[]{"@123.com", "@333.com", "@222.com", "@dff.com","@ddd.cd", "@aaa.com" });

        CheckBox typeCheckBox = (CheckBox)findViewById(R.id.type_checkbox);
        typeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    emailAutoCompleteTextView.setmType(EmailAutoCompleteTextView.TypeList);
                }else{
                    emailAutoCompleteTextView.setmType(EmailAutoCompleteTextView.TypeInline);
                }
            }
        });
    }

}

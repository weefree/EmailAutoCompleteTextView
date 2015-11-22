# EmailAutoCompleteTextView

![](https://raw.githubusercontent.com/weefree/EmailAutoCompleteTextView/tree/master/demo/doc/demo.gif)

## Setup

    dependencies {
        compile 'com.boxstudio.lib:email-auto-complete-textview:0.3'
    }

  <com.boxstudio.editviewlibrary.view.EmailAutoCompleteTextView
        android:id="@+id/email_auto_complete_textview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    EmailAutoCompleteTextView emailAutoCompleteTextView = (EmailAutoCompleteTextView)findViewById(R.id.email_auto_complete_textview);
    emailAutoCompleteTextView.setmType(EmailAutoCompleteTextView.TypeInline);
    emailAutoCompleteTextView.setHintTextColor(Color.RED);
    emailAutoCompleteTextView.setmSuggestionArray(new String[]{"@123.com", "@333.com", "@222.com", "@dff.com","@ddd.cd", "@aaa.com" });


## Developed By

* weeln - [weelnme@gmail.com](mailto:weelnme@gmail.com)
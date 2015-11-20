package com.boxstudio.editviewlibrary.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

import com.boxstudio.editviewlibrary.adapter.EmailAutoCompleteAdapter;

/**
 * Created by Administrator on 2015/11/14 0014.
 */
public class EmailAutoCompleteTextView extends AutoCompleteTextView {

    public static final int TypeNormal = 0;
    public static final int TypeAfterAt = 1;
    private  String[] mEmailArray = { "@163.com", "@126.com", "@qq.com", "@gmail.com", "@hotmail.com","@sina.com", "@yahoo.com" };


    private int mType = TypeNormal;

    public EmailAutoCompleteTextView(Context context) {
        super(context);

    }

    public EmailAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initView();
    }

    public void setmType(int mType){
        this.mType = mType;
    }

    public String[] getmEmailArray() {
        return mEmailArray;
    }

    public void setmEmailArray(String[] mEmailArray) {
        this.mEmailArray = mEmailArray;
    }

    private void initView(){
        final EmailAutoCompleteAdapter adapter = new EmailAutoCompleteAdapter(getContext());
        this.setAdapter(adapter);
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String inputStr = s.toString();
                String emailSuffix = "";
                if (inputStr.contains("@")) {
                    emailSuffix = inputStr.substring(inputStr.indexOf("@"), inputStr.length());
                    inputStr = inputStr.substring(0, inputStr.indexOf("@"));
                }else{
                    if(mType == TypeAfterAt){
                        return;
                    }
                }
                adapter.mList.clear();
                if (inputStr.length() > 0) {
                    for (int i = 0; i < mEmailArray.length; ++i) {
                        if (emailSuffix.length() == 0) {
                            adapter.mList.add(inputStr + mEmailArray[i]);
                        } else {
                            String addSuffix = mEmailArray[i];
                            if (addSuffix.startsWith(emailSuffix.toLowerCase())) {
                                adapter.mList.add(inputStr + addSuffix);
                            }
                        }


                    }
                }
                adapter.notifyDataSetChanged();
                EmailAutoCompleteTextView.this.showDropDown();

            }
        });
    }
}

package com.boxstudio.editviewlibrary.view;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
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

    private String mLastBuffer = "";

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
                    if(emailSuffix.length()>0&&mLastBuffer.length()>0&&emailSuffix.endsWith(mLastBuffer)){
                        emailSuffix =  emailSuffix.substring(0,emailSuffix.length()-mLastBuffer.length());
                    }
                    inputStr = inputStr.substring(0, inputStr.indexOf("@"));
                }else{
//                    if(mType == TypeAfterAt){
//                        return;
//                    }
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
                EmailAutoCompleteTextView.this.removeTextChangedListener(this);
                if(adapter.mList.size()>0&&(s.toString().indexOf('@')!=-1)){
                    String addSuffix = adapter.mList.get(0);
                    mLastBuffer = addSuffix.substring(inputStr.length()+emailSuffix.length());
                    s.clear();
                    s.append(addSuffix);

                    s.setSpan(new ForegroundColorSpan(Color.GRAY), s.length() - mLastBuffer.length(), s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    EmailAutoCompleteTextView.this.setSelection(s.length() - mLastBuffer.length());


                }else{
                    if(mLastBuffer.length()>0){

                        String temStr = s.subSequence(0, s.length() - mLastBuffer.length()).toString();
                        s.clear();
                        s.append(temStr);
                        mLastBuffer = "";
                    }
                }
                EmailAutoCompleteTextView.this.addTextChangedListener(this);
//                adapter.notifyDataSetChanged();
//                EmailAutoCompleteTextView.this.showDropDown();

            }
        });
    }
}

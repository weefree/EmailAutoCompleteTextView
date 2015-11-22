package com.boxstudio.editviewlibrary.view;

import android.content.Context;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.boxstudio.editviewlibrary.adapter.EmailAutoCompleteAdapter;

/**
 * Created by Administrator on 2015/11/14 0014.
 */
public class EmailAutoCompleteTextView extends AutoCompleteTextView {

    public static final int TypeInline = 0;
    public static final int TypeList = 1;
    private  String[] mSuggestionArray = { "@163.com", "@126.com", "@qq.com", "@gmail.com", "@hotmail.com","@sina.com", "@yahoo.com" };

    private int mAssistSuffixLength = 0;
    private int mType = TypeInline;
    private Editable mEditText = null;
    private EmailAutoCompleteAdapter adapter;

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
        if(mType==TypeList&&adapter!=null)this.setAdapter(adapter);
    }

    public String[] getmSuggestionArray() {
        return mSuggestionArray;
    }

    public void setmSuggestionArray(String[] mSuggestionArray) {
        this.mSuggestionArray = mSuggestionArray;
    }

    private void initView(){
        adapter = new EmailAutoCompleteAdapter(getContext());

        final TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(mSuggestionArray==null||mSuggestionArray.length==0){
                    return;
                }
                String inputStr = (mAssistSuffixLength > 0 && s.length() > mAssistSuffixLength) ? s.subSequence(0, s.length() - mAssistSuffixLength).toString() : s.toString();
                String inputStrBeforeAt = inputStr;
                String inputStrAfterAt = "";
                String firstSuggestionString = mSuggestionArray[0].charAt(0)+"";

                adapter.mList.clear();
                if (inputStr.contains(firstSuggestionString)) {
                    int atIndex = inputStr.indexOf(firstSuggestionString);
                    if (atIndex != inputStr.lastIndexOf(firstSuggestionString)) {
                        return;
                    }
                    inputStrBeforeAt = inputStr.substring(0, atIndex);
                    if (atIndex < (inputStr.length() - 1)) {
                        inputStrAfterAt = inputStr.substring(atIndex + 1, inputStr.length());
                    }
                }else{
                    return;
                }

                if (inputStr.length() > 0) {
                    for (int i = 0; i < mSuggestionArray.length; ++i) {
                        if (mSuggestionArray[i].startsWith(firstSuggestionString+inputStrAfterAt)) {
                            adapter.mList.add(inputStrBeforeAt +  mSuggestionArray[i]);
                        }
                    }
                }

                switch (mType){
                    case TypeInline:
                        EmailAutoCompleteTextView.this.removeTextChangedListener(this);
                        if (inputStrAfterAt.length() > 0 && adapter.mList.size() > 0) {
                            String firstAssist = adapter.mList.get(0);
                            int preSelectionStart = EmailAutoCompleteTextView.this.getSelectionStart();
                            s.clear();
                            s.append(firstAssist);
                            mAssistSuffixLength = s.length() - inputStr.length();
                            mEditText = s;
                            s.setSpan(new ForegroundColorSpan(EmailAutoCompleteTextView.this.getCurrentHintTextColor()), inputStr.length(), s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            EmailAutoCompleteTextView.this.setSelection(preSelectionStart);
                        } else {
                            mAssistSuffixLength = 0;
                            s.clear();
                            s.append(inputStr);
                            mEditText = null;
                        }
                        EmailAutoCompleteTextView.this.addTextChangedListener(this);
                        break;
                    case TypeList:
                        adapter.notifyDataSetChanged();
                        EmailAutoCompleteTextView.this.showDropDown();
                        break;
                }




            }
        };

        this.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && mEditText != null) {
                    EmailAutoCompleteTextView.this.removeTextChangedListener(textWatcher);
                    String temStr = mEditText.toString();
                    mEditText.clear();
                    mEditText.append(temStr);
                    mAssistSuffixLength = 0;
                    EmailAutoCompleteTextView.this.addTextChangedListener(textWatcher);
                }
            }
        });
        this.addTextChangedListener(textWatcher);
    }
}

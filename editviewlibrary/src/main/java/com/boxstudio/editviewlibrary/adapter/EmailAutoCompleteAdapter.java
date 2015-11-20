package com.boxstudio.editviewlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.boxstudio.lib.editviewlibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/13 0013.
 */
public class EmailAutoCompleteAdapter extends BaseAdapter implements Filterable {

    public List<String> mList;
    public Context mContext;
    public EmailFilter mFilter;

    public EmailAutoCompleteAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<String>();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList == null ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public static class ViewHolder {
        public TextView itemEmailTV;
    }

    @Override
    public View getView(int position, View v, ViewGroup arg2) {

        ViewHolder holder = null;
        if (v == null) {
            v = LayoutInflater.from(mContext).inflate(R.layout.item_email_auto_complete, null);
            holder = new ViewHolder();
            holder.itemEmailTV = (TextView) v.findViewById(R.id.item_email_tv);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.itemEmailTV.setText(mList.get(position));
        return v;
    }


    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new EmailFilter();
        }
        return mFilter;
    }

    public class EmailFilter extends Filter {

        @Override
        protected Filter.FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (mList == null) {
                mList = new ArrayList<String>();
            }
            results.values = mList;
            results.count = mList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

    }

}
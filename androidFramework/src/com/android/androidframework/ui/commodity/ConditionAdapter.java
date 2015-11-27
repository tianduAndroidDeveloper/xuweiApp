package com.android.androidframework.ui.commodity;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.androidframework.R;

public class ConditionAdapter extends BaseAdapter {
	Context mContext;
	String[] mConditions;
	Callback mCallback;

	public ConditionAdapter(Context context, String[] conditions, Callback callback) {
		this.mContext = context;
		this.mConditions = conditions;
		this.mCallback = callback;
	}

	@Override
	public int getCount() {
		return mConditions.length;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		if (convertView == null)
			convertView = View.inflate(mContext, R.layout.item_condition2, null);
		String condition = mConditions[position];
		TextView tv = (TextView) convertView.findViewById(R.id.tv_con);
		tv.setText(condition);
		tv.setTag(condition);
		tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mCallback.callBack(String.valueOf(arg0.getTag()));

			}
		});
		return convertView;
	}

	public interface Callback {
		void callBack(String condition);
	}

}

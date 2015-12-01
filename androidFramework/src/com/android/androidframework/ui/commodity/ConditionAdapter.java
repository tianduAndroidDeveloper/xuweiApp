package com.android.androidframework.ui.commodity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xuwei.app.R;

public class ConditionAdapter extends BaseAdapter {
	Context mContext;
	JSONObject mConditions;
	Callback mCallback;
	JSONArray dataList;
	

	public ConditionAdapter(Context context, String conditions,
			Callback callback) {
		this.mContext = context;
		
		this.mCallback = callback;
		try {
			mConditions = new JSONObject(conditions);
			dataList = mConditions.optJSONArray("Filter");
		} catch (JSONException e) {
			
			try {
				dataList = new JSONArray(conditions);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public int getCount() {
		return dataList == null?0:dataList.length();
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
			convertView = View
					.inflate(mContext, R.layout.item_condition2, null);
		String condition = dataList.optJSONObject(position).optString("title");
		convertView.setTag(dataList.optJSONObject(position));
		
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

	public void refresh(String string) {
		try {
			mConditions = new JSONObject(string);
			dataList = mConditions.optJSONArray("Filter");
		} catch (JSONException e) {
			
			try {
				dataList = new JSONArray(string);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		this.notifyDataSetChanged();
		
	}

}

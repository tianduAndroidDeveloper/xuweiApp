package com.android.androidframework.ui.commodity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.androidframework.actionbar.MainActionBarActivity;
import com.android.androidframework.net.ProgressMessage;
import com.android.androidframework.net.RequestAdapter;
import com.android.androidframework.net.RequestAdapter.RequestMethod;
import com.android.androidframework.net.ResponseData;
import com.xuwei.app.R;

@EActivity(R.layout.activity_category)
public class CategoryActivity extends MainActionBarActivity {
	private static final String TAG = "CategoryActivity";
	GridView gv;

	@AfterViews
	void initUI() {
		titleButton.setText("商品分类");
		gv = (GridView) findViewById(R.id.gv);
		gv.setAdapter(new MyGridViewAdapter());
		requestLeft();
	}

	void requestLeft() {
		new RequestAdapter() {
			
			@Override
			public void onReponse(ResponseData data) {
				Log.i(TAG, data.toString());
			}
			
			@Override
			public void onProgress(ProgressMessage msg) {
				// TODO Auto-generated method stub
				
			}
		}.setUrl(getString(R.string.url_loadProductCategorys)).setRequestMethod(RequestMethod.eGet).notifyRequest();
	}

	class MyGridViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 20;
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
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null)
				convertView = View.inflate(CategoryActivity.this, R.layout.item_category, null);
			ViewHolder holder = initHolder(convertView);
			return convertView;
		}

	}

	ViewHolder initHolder(View convertView) {
		ViewHolder holder = (ViewHolder) convertView.getTag();
		if (holder == null) {
			holder = new ViewHolder();
			holder.iv = (ImageView) findViewById(R.id.iv);
			holder.tv = (TextView) findViewById(R.id.tv);
			convertView.setTag(holder);
		}
		return holder;
	}

	class ViewHolder {
		ImageView iv;
		TextView tv;
	}

	@Override
	public void backButtonClick(View v) {
		finish();
	}

	@Override
	public void titleButtonClick(View v) {

	}

	@Override
	public void rightButtonClick(View v) {

	}

	@Override
	public Boolean showHeadView() {
		return true;
	}

}

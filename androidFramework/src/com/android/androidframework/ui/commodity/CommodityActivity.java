package com.android.androidframework.ui.commodity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.android.androidframework.actionbar.MainActionBarActivity;
import com.android.androidframework.net.ProgressMessage;
import com.android.androidframework.net.RequestAdapter;
import com.android.androidframework.net.ResponseData;
import com.android.androidframework.net.RequestAdapter.RequestContentType;
import com.android.androidframework.net.RequestAdapter.RequestMethod;
import com.android.androidframework.net.ResponseData.ResultState;
import com.android.androidframework.ui.commodity.ConditionAdapter.Callback;
import com.android.androidframework.ui.view.LabelGroup;
import com.android.androidframework.ui.view.MyGridView;
import com.android.androidframework.utils.Utils;
import com.xuwei.app.R;

@EActivity(R.layout.activity_commodity)
public class CommodityActivity extends MainActionBarActivity {
	@Extra
	String category_id;
	@ViewById(R.id.gv)
	MyGridView gv;
	@ViewById(R.id.rg)
	RadioGroup rg;
	private JSONObject conditionsJson;
	//String[] conditions = { "全部", "4G全网通", "移动4G(TD-LTD)", "联通4G(TD-LTD)",
			//"4G全网通", "4G全网通" };
	int dp5;

	@Click(R.id.rb_sell)
	void sellSort(){
		
	}
	@Click(R.id.rb_price)
	void priceSort(){
		
	}
	@Click(R.id.rb_time)
	void timeSort(){
		
	}
	@Click(R.id.rb_condition)
	void conditionSort(){
		if(conditionsJson == null){
			Toast t = Toast.makeText(CommodityActivity.this, "筛选条件为空，请重试", Toast.LENGTH_LONG);
			t.setGravity(Gravity.CENTER, 0, 0);
			t.show();
			return;
		}
		ConditionDialog_.intent(CommodityActivity.this).json(conditionsJson.toString()).start();
	}
	
	@AfterInject
	void initData() {
		
		requestConditions();
	}

	@AfterViews
	void initUI() {
		titleButton.setText("商品列表");
		gv.setAdapter(new MyGridViewAdapter());

		RadioButton rb = (RadioButton) rg.getChildAt(0);
		rb.setChecked(true);

		dp5 = Utils.dp2px(this, 5);
	}

	private void requestConditions() {
		new RequestAdapter() {

			@Override
			public void onReponse(ResponseData data) {

				if(data.getResultState() == ResultState.eSuccess){
					conditionsJson = data.getMRootData();
				}
			}

			@Override
			public void onProgress(ProgressMessage msg) {
				// TODO Auto-generated method stub

			}
		}.setUrl(
				getString(R.string.url_loadProductCategoryFilters))
				.setRequestContentType(RequestContentType.eJSON)
				.setRequestMethod(RequestMethod.ePost)
				//.addParam("category_id", 2+"")
				.setJSON("{category_id:2}")
				.notifyRequest();

	}

	
	



	class ViewHolder {
		ImageView iv;
		TextView tv_name;
		TextView tv_descripition;
		TextView tv_price;
		TextView tv_previous;
	}

	class MyGridViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 5;
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
				convertView = View.inflate(CommodityActivity.this,
						R.layout.item_commodity, null);
			ViewHolder holder = initHolder(convertView);
			return convertView;
		}

	}

	ViewHolder initHolder(View convertView) {
		ViewHolder holder = (ViewHolder) convertView.getTag();
		if (holder == null) {
			holder = new ViewHolder();
			holder.iv = (ImageView) convertView.findViewById(R.id.iv);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_descripition = (TextView) convertView
					.findViewById(R.id.tv_decrip);
			holder.tv_price = (TextView) convertView
					.findViewById(R.id.tv_price);
			holder.tv_previous = (TextView) convertView
					.findViewById(R.id.tv_previous);
			holder.tv_previous.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			convertView.setTag(holder);
		}
		return holder;
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

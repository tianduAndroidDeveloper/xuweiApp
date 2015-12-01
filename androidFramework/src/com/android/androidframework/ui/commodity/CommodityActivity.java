package com.android.androidframework.ui.commodity;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.Paint;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.androidframework.MyApplication;
import com.android.androidframework.actionbar.MainActionBarActivity;
import com.android.androidframework.net.ProgressMessage;
import com.android.androidframework.net.RequestAdapter;
import com.android.androidframework.net.RequestAdapter.RequestContentType;
import com.android.androidframework.net.RequestAdapter.RequestMethod;
import com.android.androidframework.net.ResponseData;
import com.android.androidframework.net.ResponseData.ResultState;
import com.android.androidframework.ui.commodity.bean.CommodityEntity;
import com.android.androidframework.ui.view.MyGridView;
import com.android.androidframework.utils.ParserJSON;
import com.android.androidframework.utils.ParserJSON.ParseListener;
import com.android.androidframework.utils.Utils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xuwei.app.R;

@EActivity(R.layout.activity_commodity)
public class CommodityActivity extends MainActionBarActivity {
	private static final String TAG = "CommodityActivity";
	@Extra
	String category_id;
	@ViewById(R.id.gv)
	MyGridView gv;
	@ViewById(R.id.rg)
	RadioGroup rg;
	@ViewById(R.id.progress)
	View progress;
	private JSONObject conditionsJson;
	List<CommodityEntity> commodities = new ArrayList<CommodityEntity>();
	int dp5;
	MyGridViewAdapter adapter;

	@Click(R.id.rb_sell)
	void sellSort() {

	}

	@Click(R.id.rb_price)
	void priceSort() {

	}

	@Click(R.id.rb_time)
	void timeSort() {

	}

	@Click(R.id.rb_condition)
	void conditionSort() {
		if (conditionsJson == null) {
			Toast t = Toast.makeText(CommodityActivity.this, "筛选条件为空，请重试", Toast.LENGTH_LONG);
			t.setGravity(Gravity.CENTER, 0, 0);
			t.show();
			return;
		}
		Log.i(TAG, conditionsJson.toString());
		ConditionDialog_.intent(CommodityActivity.this).json(conditionsJson.toString()).start();
	}

	@AfterInject
	void initData() {
		requestConditions();
	}

	void requestCommodity() {
		progress.setVisibility(View.VISIBLE);
		new RequestAdapter() {

			@Override
			public void onReponse(ResponseData data) {
				String msg = data.getMsg();
				if (data.getResultState() == ResultState.eSuccess) {
					JSONObject object = data.getMRootData();
					if (object != null) {
						JSONArray array = object.optJSONArray("productData");
						parseToList(array);
					}
				} else {
					Toast.makeText(CommodityActivity.this, msg, Toast.LENGTH_SHORT).show();
				}

			}

			@Override
			public void onProgress(ProgressMessage msg) {

			}
		}.setUrl(getString(R.string.url_loadProductsPageData)).setRequestMethod(RequestMethod.ePost).setRequestContentType(RequestContentType.eJSON).setJSON("{\"category_id\":12,\"page\":1}")
				.notifyRequest();
	}

	void parseToList(final JSONArray array) {
		new ParserJSON(new ParseListener() {

			@Override
			public Object onParse() {
				ObjectMapper om = new ObjectMapper();
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.optJSONObject(i);
					try {
						CommodityEntity commodity = om.readValue(object.toString(), CommodityEntity.class);
						commodities.add(commodity);
					} catch (JsonParseException e) {
						e.printStackTrace();
					} catch (JsonMappingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return commodities;
			}

			@Override
			public void onComplete(Object parseResult) {
				if (parseResult != null) {
					fillData();
				}
			}
		}).execute();
	}

	void fillData() {
		if (adapter == null) {
			adapter = new MyGridViewAdapter();
			gv.setAdapter(adapter);
		} else {
			adapter.notifyDataSetChanged();
		}
		progress.setVisibility(View.GONE);
	}

	@AfterViews
	void initUI() {
		titleButton.setText("商品列表");
		RadioButton rb = (RadioButton) rg.getChildAt(0);
		rb.setChecked(true);

		dp5 = Utils.dp2px(this, 5);
		requestCommodity();
	}

	private void requestConditions() {
		new RequestAdapter() {

			@Override
			public void onReponse(ResponseData data) {

				if (data.getResultState() == ResultState.eSuccess) {
					conditionsJson = data.getMRootData();
				}
			}

			@Override
			public void onProgress(ProgressMessage msg) {

			}
		}.setUrl(getString(R.string.url_loadProductCategoryFilters)).setRequestContentType(RequestContentType.eJSON).setRequestMethod(RequestMethod.ePost).setJSON("{category_id:2}").notifyRequest();

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
			return commodities.size();
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
				convertView = View.inflate(CommodityActivity.this, R.layout.item_commodity, null);
			ViewHolder holder = initHolder(convertView);
			CommodityEntity commodity = commodities.get(position);
			holder.tv_name.setText(commodity.title);
			holder.tv_descripition.setText(commodity.miaoshu);
			holder.tv_previous.setText("￥" + commodity.market_price);
			holder.tv_price.setText("￥" + commodity.seal_price);
			String imgUrl = getString(R.string.url_get_image) + commodity.pic;
			holder.iv.setTag(imgUrl);
			ImageLoader.getInstance().displayImage(imgUrl, holder.iv, MyApplication.getOptions(), MyApplication.getLoadingListener());
			return convertView;
		}

	}

	ViewHolder initHolder(View convertView) {
		ViewHolder holder = (ViewHolder) convertView.getTag();
		if (holder == null) {
			holder = new ViewHolder();
			holder.iv = (ImageView) convertView.findViewById(R.id.iv);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_descripition = (TextView) convertView.findViewById(R.id.tv_decrip);
			holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
			holder.tv_previous = (TextView) convertView.findViewById(R.id.tv_previous);
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

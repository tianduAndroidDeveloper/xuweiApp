package com.android.androidframework.ui.commodity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.android.androidframework.R;
import com.android.androidframework.actionbar.MainActionBarActivity;
import com.android.androidframework.ui.commodity.ConditionAdapter.Callback;
import com.android.androidframework.ui.view.LabelGroup;
import com.android.androidframework.ui.view.MyGridView;
import com.android.androidframework.utils.Utils;

@EActivity(R.layout.activity_commodity)
public class CommodityActivity extends MainActionBarActivity implements OnClickListener {
	@ViewById(R.id.gv)
	MyGridView gv;
	@ViewById(R.id.rg)
	RadioGroup rg;
	String[] conditions = { "全部", "4G全网通", "移动4G(TD-LTD)", "联通4G(TD-LTD)", "4G全网通", "4G全网通" };
	int dp5;

	@AfterViews
	void initUI() {
		titleButton.setText("商品列表");
		gv.setAdapter(new MyGridViewAdapter());

		rg.setOnCheckedChangeListener(new MyCheckChangeListener());
		RadioButton rb = (RadioButton) rg.getChildAt(0);
		rb.setChecked(true);

		dp5 = Utils.dp2px(this, 5);
	}

	class MyCheckChangeListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup arg0, int arg1) {
			int changedId = arg0.getCheckedRadioButtonId();
			switch (changedId) {
			case R.id.rb_condition:
				showConditionDialog();
				break;

			default:
				break;
			}
		}

	}

	Dialog dialog;

	void showConditionDialog() {
		Builder builder = new Builder(this);
		View view = View.inflate(this, R.layout.dialog_condition, null);
		builder.setView(view);
		builder.setCancelable(true);
		dialog = builder.show();

		RadioGroup rg_condition = (RadioGroup) view.findViewById(R.id.rg_condition);
		RadioButton rb = (RadioButton) rg_condition.getChildAt(0);
		rb.setChecked(true);
		rg_condition.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub

			}
		});

		ListView lv_condition = (ListView) view.findViewById(R.id.lv_condition);
		final LabelGroup lg = (LabelGroup) view.findViewById(R.id.lg_condition);
		final LayoutParams params = new LayoutParams(-2, -1);
		params.setMargins(dp5, dp5, dp5, dp5);
		lv_condition.setAdapter(new ConditionAdapter(this, conditions, new Callback() {

			@Override
			public void callBack(String condition) {
				TextView tv = new TextView(CommodityActivity.this);
				tv.setBackgroundResource(R.drawable.shape_border);
				tv.setText(condition);
				tv.setTextColor(Color.WHITE);
				tv.setPadding(dp5, dp5, dp5, dp5);
				tv.setLayoutParams(params);
				lg.addView(tv);
			}
		}));

		TextView tv_ok = (TextView) view.findViewById(R.id.tv_ok);
		TextView tv_clean = (TextView) view.findViewById(R.id.tv_clean);
		tv_ok.setOnClickListener(this);
		tv_clean.setOnClickListener(this);
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
				convertView = View.inflate(CommodityActivity.this, R.layout.item_commodity, null);
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

	void dismissDialog() {
		if (dialog != null) {
			dialog.dismiss();
			dialog = null;
		}
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.tv_ok:
			dismissDialog();

			break;
		case R.id.tv_clean:
			dismissDialog();

			break;

		default:
			break;
		}
	}

}

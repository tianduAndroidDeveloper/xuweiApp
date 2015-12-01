package com.android.androidframework.ui.commodity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.androidframework.ui.commodity.ConditionAdapter.Callback;
import com.android.androidframework.ui.view.LabelGroup;
import com.android.androidframework.utils.Utils;
import com.xuwei.app.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioGroup.OnCheckedChangeListener;
@EActivity(R.layout.dialog_condition)
public class ConditionDialog extends Activity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	@ViewById(R.id.lv_condition)
	ListView lv_condition;
	@ViewById(R.id.rg_condition)
	RadioGroup rg_condition;
	@Extra
	String json;
	int dp5;
	ConditionAdapter adapter;
	LayoutParams params;
	LabelGroup lg;
	JSONObject conditionsJson;
	
		
	@AfterInject
	void initData(){
		try {
			conditionsJson = new JSONObject(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		params = new LayoutParams(-2, -1);
		params.setMargins(dp5, dp5, dp5, dp5);
		
	}
	
	@AfterViews
	void initUI(){
		WindowManager m = getWindowManager();
		Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高
		android.view.WindowManager.LayoutParams p = getWindow().getAttributes(); 
		p.height = (int) (d.getHeight() ); // 高度设置为屏幕的0.8
		p.width = (int) (d.getWidth() ); // 宽度设置为屏幕的0.7
		getWindow().setAttributes(p);
		dp5 = Utils.dp2px(this, 5);
		addOneLevel();
		RadioButton rb = (RadioButton) rg_condition.getChildAt(0);
		rb.setChecked(true);
		rg_condition.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				View v = group.findViewById(checkedId);
				adapter.refresh(v.getTag().toString());
				
			}
		});
		View footerView = LayoutInflater.from(this).inflate(R.layout.condition_footer, null);
		lv_condition.addFooterView(footerView);
		lg = (LabelGroup) footerView.findViewById(R.id.lg_condition);
		
		adapter = new ConditionAdapter(this, rg_condition.getChildAt(0).getTag().toString(),
				new Callback() {

			@Override
			public void callBack(String condition) {
				if(containtId(condition)){
					return;
				}
				TextView tv = new TextView(ConditionDialog.this);
				tv.setBackgroundResource(R.drawable.shape_border);
				tv.setText(condition);
				tv.setTextColor(Color.WHITE);
				tv.setPadding(dp5, dp5, dp5, dp5);
				tv.setLayoutParams(params);
				tv.setTag(condition);
				tv.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						lg.removeView(v);
						
					}
				});
				lg.addView(tv);
			}
		});

		
		lv_condition.setAdapter(adapter);
		TextView tv_ok = (TextView) footerView.findViewById(R.id.tv_ok);
		TextView tv_clean = (TextView) footerView.findViewById(R.id.tv_clean);
		tv_ok.setOnClickListener(this);
		tv_clean.setOnClickListener(this);
	}
	
	protected boolean containtId(String condition) {
		for(int i=0;i<lg.getChildCount();i++){
			if(lg.getChildAt(i).getTag().toString().equals(condition)){
				return true;
			}
		}
		return false;
	}

	private void addOneLevel() {
		JSONArray dataList = conditionsJson.optJSONArray("filterData");
		int count = dataList == null ? 0 : dataList.length();
		
		if (conditionsJson.optInt("has_price", 0) == 1) {
			RadioButton rb = initRadioButton();
			rb.setText("价格");
			rb.setTag(conditionsJson.optJSONArray("priceData"));
			rg_condition.addView(rb);
		}
		for(int i=0;i<count;i++){
			RadioButton rb = initRadioButton();
			rb.setText(dataList.optJSONObject(i).optString("Title"));
			rb.setTag(dataList.optJSONObject(i));
			rg_condition.addView(rb);
		}
		
	}
	
	private RadioButton initRadioButton(){
		RadioButton rb = new RadioButton(this);
		
		rb.setBackgroundResource(R.drawable.selector_rb_condition);
		rb.setButtonDrawable(android.R.color.transparent);
		rb.setPadding(10, 10, 10, 10);
		rb.setSingleLine();
		rb.setTextColor(R.drawable.selector_textcolor_condition);
		rb.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		
		return rb;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.tv_ok:
			finish();
			break;
		case R.id.tv_clean:
			finish();
			break;
			default:
				break;
		}
		
	}
}

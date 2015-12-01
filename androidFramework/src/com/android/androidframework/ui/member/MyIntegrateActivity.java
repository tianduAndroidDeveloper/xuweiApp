package com.android.androidframework.ui.member;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import android.view.View;

import com.android.androidframework.actionbar.MainActionBarActivity;
import com.xuwei.app.R;

@EActivity(R.layout.member_integrate_layout)
public class MyIntegrateActivity extends MainActionBarActivity {
 
	@AfterViews
	void initUI(){
		titleButton.setText("我的积分");
	}
	@Override
	public void backButtonClick(View v) {
		// TODO Auto-generated method stub
		finish();
	}

	@Override
	public void titleButtonClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rightButtonClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean showHeadView() {
		// TODO Auto-generated method stub
		return true;
	}

}

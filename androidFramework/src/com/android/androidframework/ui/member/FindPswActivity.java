package com.android.androidframework.ui.member;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import android.view.View;

import com.android.androidframework.actionbar.MainActionBarActivity;
import com.xuwei.app.R;

@EActivity(R.layout.activity_find1)
public class FindPswActivity extends MainActionBarActivity {
	@AfterViews
	void initUI() {
		titleButton.setText("找回密码");
		rightButton.setVisibility(View.VISIBLE);
		rightButton.setText("下一步");
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

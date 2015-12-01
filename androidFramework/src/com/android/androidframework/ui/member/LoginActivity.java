package com.android.androidframework.ui.member;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import android.view.View;

import com.android.androidframework.actionbar.MainActionBarActivity;
import com.xuwei.app.R;

@EActivity(R.layout.activity_login)
public class LoginActivity extends MainActionBarActivity {
	@Click(R.id.tv_register)
	void register() {
		RegisterActivity_.intent(this).start();
	}

	@Click(R.id.tv_find)
	void find() {
		FindPswActivity_.intent(this).start();
	}

	@AfterViews
	void initUI() {
		titleButton.setText("µÇÂ¼");
		rightButton.setVisibility(View.VISIBLE);
		rightButton.setText("Íê³É");
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

package com.android.androidframework;

import org.androidannotations.annotations.EActivity;

import com.android.androidframework.actionbar.MainActionBarActivity;
import com.android.androidframework.net.ProgressMessage;
import com.android.androidframework.net.RequestAdapter;
import com.android.androidframework.net.ResponseData;

import android.view.View;
@EActivity(R.layout.activity_main)
public class MainActivity extends MainActionBarActivity {

	@Override
	public void backButtonClick(View v) {
		
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

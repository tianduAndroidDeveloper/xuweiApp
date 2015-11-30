package com.android.androidframework;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;

import com.android.androidframework.actionbar.MainActionBarActivity;
import com.android.androidframework.net.ProgressMessage;
import com.android.androidframework.net.RequestAdapter;
import com.android.androidframework.net.RequestAdapter.RequestContentType;
import com.android.androidframework.net.RequestAdapter.RequestMethod;
import com.android.androidframework.net.ResponseData;
import com.android.androidframework.net.ResponseData.ResultState;

import android.view.View;
import com.xuwei.app.R;
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
	
	@Background
	public void bag(){
		
	}
	
	@UiThread
	public void uit(){
		
	}
	
}

package com.android.androidframework.ui.home;

import java.util.ArrayList;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.android.androidframework.R;
import com.android.androidframework.actionbar.MainActionBarActivity;

@EActivity(R.layout.home_main_activity)
public class FramMainActivity extends MainActionBarActivity {
	static String tag = "FramMainActivity";
	@ViewById(android.R.id.tabhost)
	TabHost tabHost;
	@ViewById(R.id.home_main_pager)
	ViewPager mainPager;

	HomeMainAdapter pageAdapter;
	ArrayList<FragmentInfo> fInfo;

	@AfterInject
	void initData() {
		
		fInfo = new ArrayList<FragmentInfo>();
	}

	@AfterViews
	void initUI() {
		backButton.setVisibility(View.INVISIBLE);
		titleButton.setText("主页展示");
		initTabHost();
		initFragments();
		begin();
	}

	void initFragments() {
		Bundle arg1 = new Bundle();
		fInfo.add(new FragmentInfo(FramOneFragment_.class, arg1));
		Bundle arg2 = new Bundle();
		fInfo.add(new FragmentInfo(FramTwoFragment_.class, arg2));

		Bundle arg3 = new Bundle();
		fInfo.add(new FragmentInfo(FramThreeFragment_.class, arg3));

		Bundle arg4 = new Bundle();
		fInfo.add(new FragmentInfo(FramFourFragment_.class, arg4));
	}

	@SuppressLint("NewApi")
	void initTabHost() {
		tabHost.setup();
		tabHost.getTabWidget().setDividerDrawable(null);
		tabHost.addTab(getTabSpec("tab1", R.drawable.me_tab_selector, "tab1"));
		tabHost.addTab(getTabSpec("tab2", R.drawable.me_tab_selector, "tab2"));

		tabHost.addTab(getTabSpec("tab3", R.drawable.me_tab_selector, "tab3"));
		tabHost.addTab(getTabSpec("tab4", R.drawable.me_tab_selector, "tab4"));
		tabHost.setOnTabChangedListener(mainTabChange);
		
	}

	private TabSpec getTabSpec(String content, int resId, String title) {
		MainTabView tab = MainTabView_.build(this);
		tab.setIndicator(resId);
		tab.setTitle(title);
		TabSpec tabSpec = tabHost.newTabSpec(content).setIndicator(tab)
				.setContent(new MainTabFactory(FramMainActivity.this));
		return tabSpec;
	}

	OnPageChangeListener pageListener = new OnPageChangeListener() {
		@Override
		public void onPageSelected(int position) {
			tabHost.setCurrentTab(position);
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	};
	OnTabChangeListener mainTabChange = new OnTabChangeListener() {
		@Override
		public void onTabChanged(String tabId) {
			int position = tabHost.getCurrentTab();
			mainPager.setCurrentItem(position);
		}
	};
	private long exitTime = 0;

	private void begin() {
		pageAdapter = new HomeMainAdapter(getSupportFragmentManager(), fInfo,
				FramMainActivity.this);
		mainPager.setOffscreenPageLimit(3);
		mainPager.setAdapter(pageAdapter);
		mainPager.setOnPageChangeListener(pageListener);
		mainPager.setCurrentItem(0);
	}

	

	public void onBackPressed() {
		if ((System.currentTimeMillis() - exitTime) > 2000) {
			Toast.makeText(getApplicationContext(), "再按一次退出程序",
					Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {
			android.os.Process.killProcess(android.os.Process.myPid());
		}
	}

	class MainTabFactory implements TabHost.TabContentFactory {
		private final Context mContext;

		public MainTabFactory(Context context) {
			mContext = context;
		}

		@Override
		public View createTabContent(String tag) {
			View v = new View(mContext);
			v.setMinimumWidth(0);
			v.setMinimumHeight(0);
			return v;
		}
	}

	@Override
	public void backButtonClick(View v) {
		// TODO Auto-generated method stub
		
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

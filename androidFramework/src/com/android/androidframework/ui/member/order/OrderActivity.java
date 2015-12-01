package com.android.androidframework.ui.member.order;

import java.util.ArrayList;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import com.android.androidframework.actionbar.MainActionBarActivity;
import com.xuwei.app.R;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.TextView;

@EActivity(R.layout.member_order_layout)
public class OrderActivity extends MainActionBarActivity {

	private ViewPager mPager;
	private ArrayList<Fragment> fragmentsList;
	// private ImageView ivBottomLine;
	private TextView tvTab1, tvTab2, tvTab3, tvTab4;

	private int currIndex = 0;
	/*private int position_one;
	private int position_two;
	private int position_three;*/
	private Resources resources;
	@Extra
	int type = 4;

	@AfterInject
	void initData() {
		resources = getResources();
	}

	@AfterViews
	void initUi() {
		titleButton.setText("我的订单");
		rightButton.setVisibility(View.VISIBLE);
		rightButton.setText("会员中心");
		rightButton.setTextColor(Color.WHITE);
		initTextView();
		initViewPager();
		initType();
	}

	/*
	 * type:1 待付款 2：待收货 3：待评价 4：全部订单
	 */
	private void initType() {
		switch (type) {
		case 1:
		case 2:
		case 3:
			mPager.setCurrentItem(type);
			break;
		case 4:
			break;

		default:
			break;
		}

	}

	private void initTextView() {
		// ivBottomLine = (ImageView) findViewById(R.id.iv_bottom_line);
		tvTab1 = (TextView) findViewById(R.id.tv_tab_1);
		tvTab2 = (TextView) findViewById(R.id.tv_tab_2);
		tvTab3 = (TextView) findViewById(R.id.tv_tab_3);
		tvTab4 = (TextView) findViewById(R.id.tv_tab_4);

		tvTab1.setOnClickListener(new MyOnClickListener(0));
		tvTab2.setOnClickListener(new MyOnClickListener(1));
		tvTab3.setOnClickListener(new MyOnClickListener(2));
		tvTab4.setOnClickListener(new MyOnClickListener(3));
	}

	private void initViewPager() {
		mPager = (ViewPager) findViewById(R.id.vPager);
		fragmentsList = new ArrayList<Fragment>();

		Fragment talListfragment = ChildFragment.newInstance();

		Fragment payListfragment = ChildFragment.newInstance();

		Fragment sureListfragment = ChildFragment.newInstance();

		Fragment reviewListFragment = ChildFragment.newInstance();

		fragmentsList.add(talListfragment);
		fragmentsList.add(payListfragment);
		fragmentsList.add(sureListfragment);
		fragmentsList.add(reviewListFragment);

		mPager.setAdapter(new CustomFragmentPagerAdapter(
				getSupportFragmentManager(), fragmentsList));
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());

	}

	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);
		}
	};

	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			// Animation animation = null;
			/*
			 * int width = ivBottomLine.getWidth(); position_one = width;
			 * position_two = 2 * width; position_three = 3 * width;
			 */
			switch (arg0) {
			case 0:
				if (currIndex == 1) {
					// animation = new TranslateAnimation(position_one, 0, 0,
					// 0);
					tvTab2.setTextColor(resources
							.getColor(android.R.color.black));
				} else if (currIndex == 2) {
					// animation = new TranslateAnimation(position_two, 0, 0,
					// 0);
					tvTab3.setTextColor(resources
							.getColor(android.R.color.black));
				} else if (currIndex == 3) {
					// animation = new TranslateAnimation(position_three, 0, 0,
					// 0);
					tvTab4.setTextColor(resources
							.getColor(android.R.color.black));
				}
				tvTab1.setTextColor(resources.getColor(R.color.red_light));
				break;
			case 1:
				if (currIndex == 0) {
					// animation = new TranslateAnimation(0, position_one, 0,
					// 0);
					tvTab1.setTextColor(resources
							.getColor(android.R.color.black));
				} else if (currIndex == 2) {
					// animation = new TranslateAnimation(position_two,
					// position_one, 0, 0);
					tvTab3.setTextColor(resources
							.getColor(android.R.color.black));
				} else if (currIndex == 3) {
					// animation = new TranslateAnimation(position_three,
					// position_one, 0, 0);
					tvTab4.setTextColor(resources
							.getColor(android.R.color.black));
				}
				tvTab2.setTextColor(resources.getColor(R.color.red_light));
				break;
			case 2:
				if (currIndex == 0) {
					// animation = new TranslateAnimation(0, position_two, 0,
					// 0);
					tvTab1.setTextColor(resources
							.getColor(android.R.color.black));
				} else if (currIndex == 1) {
					// animation = new TranslateAnimation(position_one,
					// position_two, 0, 0);
					tvTab2.setTextColor(resources
							.getColor(android.R.color.black));
				} else if (currIndex == 3) {
					// animation = new TranslateAnimation(position_three,
					// position_two, 0, 0);
					tvTab4.setTextColor(resources
							.getColor(android.R.color.black));
				}
				tvTab3.setTextColor(resources.getColor(R.color.red_light));
				break;
			case 3:
				if (currIndex == 0) {
					// animation = new TranslateAnimation(0, position_three, 0,
					// 0);
					tvTab1.setTextColor(resources
							.getColor(android.R.color.black));
				} else if (currIndex == 1) {
					// animation = new TranslateAnimation(position_one,
					// position_three, 0, 0);
					tvTab2.setTextColor(resources
							.getColor(android.R.color.black));
				} else if (currIndex == 2) {
					// animation = new TranslateAnimation(position_two,
					// position_three, 0, 0);
					tvTab3.setTextColor(resources
							.getColor(android.R.color.black));
				}
				tvTab4.setTextColor(resources.getColor(R.color.red_light));
				break;
			}
			currIndex = arg0;
			// animation.setFillAfter(true);
			// animation.setDuration(300);
			// ivBottomLine.startAnimation(animation);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
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
		finish();
	}

	@Override
	public Boolean showHeadView() {
		// TODO Auto-generated method stub
		return true;
	}

}

package com.android.androidframework.ui.commodity;

import java.util.Timer;
import java.util.TimerTask;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.android.androidframework.MyApplication;
import com.android.androidframework.actionbar.MainActionBarActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xuwei.app.R;

@EActivity(R.layout.commodity_detail)
public class CommodityDetailActivity extends MainActionBarActivity {
	private static final int LOOPIMAGE = 0;
	@ViewById(R.id.vp)
	ViewPager vp;
	@ViewById(R.id.ll_points)
	LinearLayout ll_points;
	ImageView[] mImageViews = new ImageView[5];
	ImageView[] tips;
	boolean loopped = false;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case LOOPIMAGE:
				vp.setCurrentItem(vp.getCurrentItem() + 1);
				break;
			}
		};
	};

	@AfterViews
	void initUI() {
		titleButton.setText("商品详情");
		initImages2();
	}

	private class MyPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int arg0) {
			for (int i = 0; i < tips.length; i++)
				tips[i].setBackgroundResource(R.drawable.white_circle);
			tips[arg0 % mImageViews.length].setBackgroundResource(R.drawable.red_circle);
		}
	}

	void initImages(String[] urls) {
		addTips(urls.length);
		mImageViews = new ImageView[urls.length];
		for (int i = 0; i < urls.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			imageView.setScaleType(ScaleType.CENTER);
			imageView.setTag(urls[i]);
			if ((!urls[i].equals("null")) && (!urls[i].equals("")) && urls[i] != null) {
				ImageLoader.getInstance().displayImage(urls[i], imageView, MyApplication.getOptions(), MyApplication.getLoadingListener());
			}
			mImageViews[i] = imageView;
		}
		vp.setAdapter(new MyViewPagerAdapter());
		vp.setCurrentItem((mImageViews.length) * 100);
		vp.setOnPageChangeListener(new MyPageChangeListener());
		if (!loopped) {
			loopped = true;
			loopImage();
		}
	}
	
	void initImages2() {
		addTips(mImageViews.length);
		for (int i = 0; i < mImageViews.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			imageView.setScaleType(ScaleType.CENTER);
			imageView.setImageResource(R.drawable.ic_launcher);
			imageView.setBackgroundColor(Color.WHITE);
			mImageViews[i] = imageView;
		}
		vp.setAdapter(new MyViewPagerAdapter());
		vp.setCurrentItem((mImageViews.length) * 100);
		vp.setOnPageChangeListener(new MyPageChangeListener());
		if (!loopped) {
			loopped = true;
			loopImage();
		}
	}

	private void loopImage() {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				Message msg = new Message();
				msg.what = LOOPIMAGE;
				handler.sendMessage(msg);
			}
		}, 4000, 4000);
	}

	void addTips(int length) {
		tips = new ImageView[length];
		for (int i = 0; i < tips.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(10, 10));
			tips[i] = imageView;
			if (i == 0) {
				tips[i].setBackgroundResource(R.drawable.red_circle);
			} else {
				tips[i].setBackgroundResource(R.drawable.white_circle);
			}
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			layoutParams.leftMargin = 5;
			layoutParams.rightMargin = 5;
			ll_points.addView(imageView, layoutParams);
		}
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

	// ViewPager的Adapter
	class MyViewPagerAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(mImageViews[position % mImageViews.length]);
		}

		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(mImageViews[position % mImageViews.length], 0);
			return mImageViews[position % mImageViews.length];
		}
	}

}

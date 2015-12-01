package com.android.androidframework.ui.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.androidannotations.annotations.EFragment;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

import com.android.androidframework.bean.PhoneBean;
import com.android.androidframework.utils.ImagePagerAdapter;
import com.xuwei.app.R;

@EFragment()
public class FramOneFragment extends Fragment {
	View rootView;
	Context mContext;
	GridView main_gridview, main_phone_gridview,main_phone2_gridview;
	AutoScrollViewPager viewpager;
	private AutoScrollViewPager viewPager, viewPager2;
	private List<Integer> imageIdList, imageIdList2;

	// FrameLayout fragment_cycle_viewpager_content;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (null != rootView) {
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if (null != parent) {
				parent.removeView(rootView);
			}
		} else {
			rootView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main, null);
			mContext = getActivity();

		}
		setMenu();
		setIMageLunbo();
		setGridView();

		return rootView;
	}

	/**
	 * gridview填充
	 */
	private void setGridView() {
		main_phone_gridview = (GridView) rootView.findViewById(R.id.main_phone_gridview);
		main_phone_gridview.setAdapter(new GridviewAdapter(null));
		
		
		main_phone2_gridview=(GridView) rootView.findViewById(R.id.main_phone2_gridview);
		main_phone2_gridview.setAdapter(new GridviewAdapter2(null));
	}

	class GridviewAdapter extends BaseAdapter {
		List<PhoneBean> phoneList = null;
		LayoutInflater infalter;
		public GridviewAdapter(List<PhoneBean> phoneList) {
			this.phoneList = phoneList;
			infalter = LayoutInflater.from(mContext);
		}

		@Override
		public int getCount() {
			return 4;
		}

		@Override
		public Object getItem(int position) {
			return getItem(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = infalter.inflate(R.layout.main_phone__grid_item, null);
				holder = new ViewHolder();
				holder.name = (TextView) convertView.findViewById(R.id.product_name);
				holder.price = (TextView) convertView.findViewById(R.id.product_price);
				holder.image = (ImageView) convertView.findViewById(R.id.phone_image);
				holder.tag = (ImageView) convertView.findViewById(R.id.image_tag);
				holder.market_price = (TextView) convertView.findViewById(R.id.product_market_price);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.market_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			// holder.name.setText(phoneList.get(position).name);
			// holder.price.setText(phoneList.get(position).price);
			// ImageLoader.getInstance().displayImage(uri, holder.image);
			return convertView;
		}

		class ViewHolder {
			TextView name;
			TextView price;
			ImageView image;
			ImageView tag;
			TextView market_price;
		}

	}
	
	//-----------------------------------------------------------
	
	class GridviewAdapter2 extends BaseAdapter {
		List<PhoneBean> phoneList = null;
		LayoutInflater infalter;
		public GridviewAdapter2(List<PhoneBean> phoneList) {
			this.phoneList = phoneList;
			infalter = LayoutInflater.from(mContext);
		}

		@Override
		public int getCount() {
			return 4;
		}

		@Override
		public Object getItem(int position) {
			return getItem(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = infalter.inflate(R.layout.main_phone2_gritview_item, null);
				holder = new ViewHolder();
				holder.name = (TextView) convertView.findViewById(R.id.product_name);
				holder.price = (TextView) convertView.findViewById(R.id.product_price);
				holder.image = (ImageView) convertView.findViewById(R.id.phone_image);
				holder.tag = (ImageView) convertView.findViewById(R.id.image_tag);
				holder.market_price = (TextView) convertView.findViewById(R.id.product_market_price);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.market_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			// holder.name.setText(phoneList.get(position).name);
			// holder.price.setText(phoneList.get(position).price);
			// ImageLoader.getInstance().displayImage(uri, holder.image);
			return convertView;
		}

		class ViewHolder {
			TextView name;
			TextView price;
			ImageView image;
			ImageView tag;
			TextView market_price;
		}

	}

	/**
	 * 主菜单
	 */
	private void setMenu() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String[] names = { "商品分类", "智能手机", "手机配件", "数码商品", "移动积分", "本站资讯", "优客服", "预留栏目" };
		int[] images = { R.drawable.column_03, R.drawable.column_05, R.drawable.column_07, R.drawable.column_09, R.drawable.column_15,
				R.drawable.column_16, R.drawable.column_17, R.drawable.column_18 };
		for (int i = 0; i < names.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", names[i]);
			map.put("image", images[i]);
			list.add(map);
		}
		main_gridview = (GridView) rootView.findViewById(R.id.main_gridview);
		main_gridview.setAdapter(new SimpleAdapter(getActivity(), list, R.layout.grid_item, new String[] { "image", "name" }, new int[] { R.id.image,
				R.id.name }));
	}

	/**
	 * 图片轮播
	 */
	private void setIMageLunbo() {
		// 第一个图片轮播
		viewPager = (AutoScrollViewPager) rootView.findViewById(R.id.view_pager);
		viewPager.startAutoScroll();

		viewPager.setInterval(1000);// 设置自动滚动的间隔时间，单位为毫秒
		// viewpager.setDirection(int) //设置自动滚动的方向，默认向右
		// setCycle(boolean) 是否自动循环轮播，默认为true

		// 第二个图片轮播
		viewPager2 = (AutoScrollViewPager) rootView.findViewById(R.id.view_pager2);
		viewPager2.startAutoScroll();
		viewPager2.setInterval(1000);
		// setScrollDurationFactor(double)
		// //设置ViewPager滑动动画间隔时间的倍率，达到减慢动画或改变动画速度的效果
		// setStopScrollWhenTouch(boolean) 当手指碰到ViewPager时是否停止自动滚动，默认为true
		// setSlideBorderMode(int)
		// 滑动到第一个或最后一个Item的处理方式，支持没有任何操作、轮播以及传递到父View三种模式

		// setBorderAnimation(boolean) 设置循环滚动时滑动到从边缘滚动到下一个是否需要动画，默认为true

		imageIdList = new ArrayList<Integer>();
		imageIdList.add(R.drawable.owl_0);
		imageIdList.add(R.drawable.owl_0);
		imageIdList.add(R.drawable.owl_0);
		imageIdList.add(R.drawable.owl_0);
		viewPager.setAdapter(new ImagePagerAdapter(mContext, imageIdList).setInfiniteLoop(true));
		// viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

		viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageIdList.size());

		imageIdList2 = new ArrayList<Integer>();
		imageIdList2.add(R.drawable.adver_02);
		imageIdList2.add(R.drawable.adver_02);
		imageIdList2.add(R.drawable.adver_02);
		viewPager2.setAdapter(new ImagePagerAdapter(mContext, imageIdList2).setInfiniteLoop(true));
		viewPager2.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageIdList2.size());

	}

}

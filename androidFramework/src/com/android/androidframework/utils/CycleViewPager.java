package com.android.androidframework.utils;

import java.util.ArrayList;
import java.util.List;

import com.android.androidframework.R;



import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


/**
 * 閻庡湱鍋熼獮鍥矗椤栨碍鍎曢柣婊庡灲缁辨繈宕ｉ婵堟瀭闁圭虎鍘惧▓鎲奿ewpager
 */
public class CycleViewPager extends Fragment implements OnPageChangeListener {
	
	private List<ImageView> imageViews = new ArrayList<ImageView>();
	private ImageView[] indicators;
	private FrameLayout viewPagerFragmentLayout;
	private LinearLayout indicatorLayout; // 闁圭娲ㄩ妵姘跺闯閿燂拷
	private BaseViewPager viewPager;
	private BaseViewPager parentViewPager;
	private ViewPagerAdapter adapter;
	private CycleViewPagerHandler handler;
	private int time = 5000; // 濮掓稒顭堥缁樻姜椤旇姤灏￠柡鍐ㄧ埣濡拷
	private int currentPosition = 0; // 閺夌儐鍠楅幐杈亹閹惧啿顤呭ù锝呯Ф閻わ拷
	private boolean isScrolling = false; // 婵犲﹥鑹炬慨鈺侇浖閸℃ɑ笑闁告熬闄勭划鎾礉閵娧勭祷
	private boolean isCycle = false; // 闁哄嫷鍨伴幆浣割嚗椤忓棗绠�
	private boolean isWheel = false; // 闁哄嫷鍨伴幆浣规姜椤旇姤灏�
	private long releaseTime = 0; // 闁归潧顑嗙�垫岸寮堕幆褏纾婚柕鍡曠窔閵嗗妫冮～顓犵憹婵犲﹥鑹炬慨鈺呭籍閸洘锛熼柨娑樼焸濡茶顫㈤姀鈥愁杹闁哄牏鍎ゅ妤�顕ｉ敓浠嬪触鎼达絿鍙氶柡鍐ㄧ埣濡寧娼诲☉婊庢斀闁告帒娲﹀畷锟�
	private int WHEEL = 100; // 閺夌儐鍓欐慨锟�
	private int WHEEL_WAIT = 101; // 缂佹稑顦欢锟�
	private ImageCycleViewListener mImageCycleViewListener;
	private List<ADInfo> infos;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.view_cycle_viewpager_contet, null);

		viewPager = (BaseViewPager) view.findViewById(R.id.viewPager);
		indicatorLayout = (LinearLayout) view
				.findViewById(R.id.layout_viewpager_indicator);

		viewPagerFragmentLayout = (FrameLayout) view
				.findViewById(R.id.layout_viewager_content);

		handler = new CycleViewPagerHandler(getActivity()) {

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (msg.what == WHEEL && imageViews.size() != 0) {
					if (!isScrolling) {
						int max = imageViews.size() + 1;
						int position = (currentPosition + 1) % imageViews.size();
						viewPager.setCurrentItem(position, true);
						if (position == max) { // 闁哄牞鎷烽柛姘凹缁斿瓨銇勯崹顐ｎ槯闁搞儳鍋涢崺宀�绮璺伇濡炪倧鎷�
							viewPager.setCurrentItem(1, false);
						}
					}

					releaseTime = System.currentTimeMillis();
					handler.removeCallbacks(runnable);
					handler.postDelayed(runnable, time);
					return;
				}
				if (msg.what == WHEEL_WAIT && imageViews.size() != 0) {
					handler.removeCallbacks(runnable);
					handler.postDelayed(runnable, time);
				}
			}
		};

		return view;
	}

	public void setData(List<ImageView> views, List<ADInfo> list, ImageCycleViewListener listener) {
		setData(views, list, listener, 0);
	}

	/**
	 * 闁告帗绻傞～鎰板礌閺夘槒ewpager
	 * 
	 * @param views
	 *            閻熸洑鐒﹀Ο澶岀矆閾忚鐣眝iews
	 * @param showPosition
	 *            濮掓稒顭堥濠氬及閸撗佷粵濞达絽绉堕悿锟�
	 */
	public void setData(List<ImageView> views, List<ADInfo> list, ImageCycleViewListener listener, int showPosition) {
		mImageCycleViewListener = listener;
		infos = list;
		this.imageViews.clear();

		if (views.size() == 0) {
			viewPagerFragmentLayout.setVisibility(View.GONE);
			return;
		}

		for (ImageView item : views) {
			this.imageViews.add(item);
		}

		int ivSize = views.size();

		// 閻犱礁澧介悿鍡涘箰閸モ斂浠涢柛锝忔嫹
		indicators = new ImageView[ivSize];
		if (isCycle)
			indicators = new ImageView[ivSize - 2];
		indicatorLayout.removeAllViews();
		for (int i = 0; i < indicators.length; i++) {
			View view = LayoutInflater.from(getActivity()).inflate(
					R.layout.view_cycle_viewpager_indicator, null);
			indicators[i] = (ImageView) view.findViewById(R.id.image_indicator);
			indicatorLayout.addView(view);
		}

		adapter = new ViewPagerAdapter();

		// 濮掓稒顭堥濠氬箰閸パ勫�荤紒妤婂厸缁斿瓨銇勯惂鍝ョ濞戞挸顑嗛弻鐒﹊ewPager.setCurrentItem閻忓繐妫滆闁告瑦鍨块崳鎼佸棘閹峰矈鍚�缂佺姵顨嗙�垫氨绮堥崫鍕彜闁圭娲ら幃锟�
		setIndicator(0);

		viewPager.setOffscreenPageLimit(3);
		viewPager.setOnPageChangeListener(this);
		viewPager.setAdapter(adapter);
		if (showPosition < 0 || showPosition >= views.size())
			showPosition = 0;
		if (isCycle) {
			showPosition = showPosition + 1;
		}
		viewPager.setCurrentItem(showPosition);

	}

	/**
	 * 閻犱礁澧介悿鍡涘箰閸モ斂浠涢柛锝冨妼閻櫕绋夐銊х濮掓稒顭堥濠氬箰閸モ斂浠涢柛锝冨妼濠�顏堝矗閾忣偅鐓�
	 */
	public void setIndicatorCenter() {
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		indicatorLayout.setLayoutParams(params);
	}

	/**
	 * 闁哄嫷鍨伴幆浣割嚗椤忓棗绠氶柨娑樼焸缁垳鎷嬮妶鍕憹鐎殿噯鎷烽柛姘煎灲缁辨繂顕ｉ敓浠嬪触椤栨艾顤呴柨娑樼焷椤曨剛浜搁崬妾宔ws闁汇劌瀚〒鍫曞礈瀹ュ妗ㄥ☉鎾冲濞撳爼宕ユ惔銊︽〃闁告艾瀚慨鐐哄礂閵夈倗顏卞☉鎿冧海椤宕堕幘鍛闁烩偓鍔嬬花顒�顕ラ鍡楃畾
	 * 
	 * @param isCycle
	 *            闁哄嫷鍨伴幆浣割嚗椤忓棗绠�
	 */
	public void setCycle(boolean isCycle) {
		this.isCycle = isCycle;
	}

	/**
	 * 闁哄嫷鍨伴幆浣瑰緞閸曨亞鑹剧�甸偊浜為獮鍡涙偐閼哥鎷烽敓锟�
	 * 
	 * @return
	 */
	public boolean isCycle() {
		return isCycle;
	}

	/**
	 * 閻犱礁澧介悿鍡涘及椤栨碍鍎婇弶鐑嗗枟閹搁亶鏁嶅畝鍕笡閻犱降鍊撶粭澶嬫姜椤旇姤灏�,閺夌儐鍠楅幐杈ㄧ▔閿熺晫锟借纰嶅Σ绋款嚗椤忓棗绠氶柣顭掓嫹
	 * 
	 * @param isWheel
	 */
	public void setWheel(boolean isWheel) {
		this.isWheel = isWheel;
		isCycle = true;
		if (isWheel) {
			handler.postDelayed(runnable, time);
		}
	}

	/**
	 * 闁哄嫷鍨伴幆浣瑰緞閸曨亞鑹鹃弶鐑嗗枟閹搁亶鎮╅懜纰夋嫹閿燂拷
	 * 
	 * @return
	 */
	public boolean isWheel() {
		return isWheel;
	}

	final Runnable runnable = new Runnable() {

		@Override
		public void run() {
			if (getActivity() != null && !getActivity().isFinishing()
					&& isWheel) {
				long now = System.currentTimeMillis();
				// 婵☆偓鎷锋繛鏉戭儎缁楀倹绋夐敓钘夆枎閳╁啰鎷ㄩ柛鏂诲妽濡炲倿姊荤紙鐘电憿闁哄牜鍓氶鍏肩▕鐎ｎ喗锛熼柡鍕靛灠閹線寮垫径骞洟宕欓敓锟�(闁归潧顑嗙划锕傚礉閿燂拷)闁瑰灝绉崇紞鏃堟晬鐏炵偓绠掗柣銊ュ閻︾晫绮垫径濠勭濞戞挸顑嗛鍏兼姜椤旇姤灏�
				if (now - releaseTime > time - 500) {
					handler.sendEmptyMessage(WHEEL);
				} else {
					handler.sendEmptyMessage(WHEEL_WAIT);
				}
			}
		}
	};

	/**
	 * 闂佹彃锕ラ弬渚�骞愰崶鈹夸粵闁革絻鍔戦悵顔芥償閿旇偐绀夐柛娆樺灥閸忔﹢鎮介崣妯艰壘濞戞柨顑呮晶鐘诲箰閸モ斂浠涢柛锝冨姀椤箓姊介幇顒�鐓戝ù婊冩閻濐喗鎯旈敂鑲╃婵縿鍊曢ˇ鈺呮煂婵犲啯鏉�
	 */
	public void releaseHeight() {
		getView().getLayoutParams().height = RelativeLayout.LayoutParams.MATCH_PARENT;
		refreshData();
	}

	/**
	 * 閻犱礁澧介悿鍡樻姜椤旇姤灏￠柡鍡楀�告禒鐘诲籍閸洘锛熼柨娑樿嫰瀹撳棗鈻介垾鎰佹▼閻忓繑鍨归～妤呭礆閸ャ劌搴婇柛鎺楊暒缁楀懏绋夐敓钘夘嚕閻樹警娼掗柛銉嫹.濮掓稒顭堥锟�5000ms
	 * 
	 * @param time
	 *            婵綆鍋嗛～妤佺▔閸濆嫬绀嬪ù锝忔嫹
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * 闁告帡鏀遍弻濠囧极閻楀牆绁﹂柨娑樿嫰缂嶅寰勯弽顓炲姤閻熸瑥妫楀ù姗�寮寸�涙ɑ鐓�闁告艾鍑界槐婵嬫焻濮樿京鍙�闁告帡鏀遍弻濠囧极閻楀牆绁�
	 */
	public void refreshData() {
		if (adapter != null)
			adapter.notifyDataSetChanged();
	}

	/**
	 * 闂傚懏鍔樺Λ瀛媦cleViewPager
	 */
	public void hide() {
		viewPagerFragmentLayout.setVisibility(View.GONE);
	}

	/**
	 * 閺夆晜鏌ㄥú鏍礃閸涱垳鏋傞柣銊ュiewpager
	 * 
	 * @return viewPager
	 */
	public BaseViewPager getViewPager() {
		return viewPager;
	}

	/**
	 * 濡炪倗鏁诲浼存焻閸岀偛甯抽柛锝忔嫹 閺夆晜鏌ㄥú鏍拷鐢垫嚀缁ㄦ煡鎯冮崜濉眅w
	 * 
	 * @author Yuedong Li
	 * 
	 */
	private class ViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imageViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public View instantiateItem(ViewGroup container, final int position) {
			ImageView v = imageViews.get(position);
			if (mImageCycleViewListener != null) {
				v.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						mImageCycleViewListener.onImageClick(infos.get(currentPosition - 1), currentPosition, v);
					}
				});
			}
			container.addView(v);
			return v;
		}

		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		if (arg0 == 1) { // viewPager闁革负鍔嶇划鎾礉閿燂拷
			isScrolling = true;
			return;
		} else if (arg0 == 0) { // viewPager婵犲﹥鑹炬慨鈺冪磼閹惧瓨灏�
			if (parentViewPager != null)
				parentViewPager.setScrollable(true);

			releaseTime = System.currentTimeMillis();

			viewPager.setCurrentItem(currentPosition, false);
			
		}
		isScrolling = false;
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
		int max = imageViews.size() - 1;
		int position = arg0;
		currentPosition = arg0;
		if (isCycle) {
			if (arg0 == 0) {
				currentPosition = max - 1;
			} else if (arg0 == max) {
				currentPosition = 1;
			}
			position = currentPosition - 1;
		}
		setIndicator(position);
	}

	/**
	 * 閻犱礁澧介悿鍞檌ewpager闁哄嫷鍨伴幆渚�宕ｉ娆庣鞍婵犲﹥鑹炬慨锟�
	 * 
	 * @param enable
	 */
	public void setScrollable(boolean enable) {
		viewPager.setScrollable(enable);
	}

	/**
	 * 閺夆晜鏌ㄥú鏍亹閹惧啿顤呭ù锝呯Ф閻わ拷,鐎甸偊浜為獮鍡涘籍閸洘浠橀悷鏇氱劍閺佺偤骞囪箛姘辩闁搞儳鍋熷▓鎲刼sition闁告牕鎳庨幆鍫熺▕鐎ｎ亜顤呴柛锔规殹iews闁哄牞鎷烽柛鎾崇У閺岀喐绋夋惔銏′粯闁告艾瀛╅弻鐔煎礉閻樻彃寮抽柣銊ュ椤宕堕幘鍛闁告鍟跨紞瀣礈瀹ュ锟藉妫冮姀锝囨Ц闁搞儲鍎冲﹢鐛�iews闂傚棗妫楅幃搴ㄦ儍閸曨亞绉寸紓鍐挎嫹
	 * 
	 * @return
	 */
	public int getCurrentPostion() {
		return currentPosition;
	}

	/**
	 * 閻犱礁澧介悿鍡涘箰閸モ斂浠涢柛锝忔嫹
	 * 
	 * @param selectedPosition
	 *            濮掓稒顭堥濠氬箰閸モ斂浠涢柛锝冨妺缂嶅懐绱旈敓锟�
	 */
	private void setIndicator(int selectedPosition) {
		for (int i = 0; i < indicators.length; i++) {
			indicators[i]
					.setBackgroundResource(R.drawable.icon_point);
		}
		if (indicators.length > selectedPosition)
			indicators[selectedPosition]
					.setBackgroundResource(R.drawable.icon_point_pre);
	}

	/**
	 * 濠碘�冲�归悘澶庛亹閹惧啿顤呭銈囨暬濞兼澘鐣电仦绛嬫闁革负鍔岃ぐ鐔哥▔閿熻姤绋夐悰锟絠ewPager濞戞搩鍙忕槐婵囩▔鏉炴壆鍟婇柛锔哄姀缁绘鎮扮仦鍓ф硦闁告柣鍔嶅鍌炴⒓缂佹ɑ鐒介柣鏍沬ewPager婵犲﹥鑹炬慨鈺呮晬鐏炶棄璁插ù鐙呮嫹 闂傚啰绮娑㈡偉缁傜帵ewPager婵犲﹥鍨垫慨鈺傜鐎ｂ晜顐�
	 * 闁绘牜螞iewPager闂傚浄鎷烽悷鏇氱閻ゅ嫰鎮抽惂妾價entViewPager濞戞搩鍘惧▓鎲噀tScrollable闁哄倽顫夌涵锟�
	 */
	public void disableParentViewPagerTouchEvent(BaseViewPager parentViewPager) {
		if (parentViewPager != null)
			parentViewPager.setScrollable(false);
	}

	
	/**
	 * 閺夌儐鍠楅幐閬嶅箳瑜屽▎銏ゆ儍閸曨厽纾ч柛姘煎厸缁ㄣ劍绂掗敓锟�
	 * 
	 * @author minking
	 */
	public static interface ImageCycleViewListener {

		/**
		 * 闁告娲栭崵顕�宕堕崜褍顣诲ù婊冾儎濞嗭拷
		 * 
		 * @param position
		 * @param imageView
		 */
		public void onImageClick(ADInfo info, int postion, View imageView);
	}
}
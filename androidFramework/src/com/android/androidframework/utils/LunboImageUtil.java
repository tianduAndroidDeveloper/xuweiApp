package com.android.androidframework.utils;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import com.android.androidframework.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class LunboImageUtil {
	private List<ImageView> views = new ArrayList<ImageView>();
	private List<ADInfo> infos = new ArrayList<ADInfo>();
	
	
	
	@SuppressLint("NewApi")
	public void initialize(Context context,String[] imageUrls,CycleViewPager cycleViewPager) {
		
		configImageLoader(context);
		
		/*ycleViewPager = (CycleViewPager) getFragmentManager()
				.findFragmentById(R.id.fragment_cycle_viewpager_content);*/
		/*
		 String[] imageUrls = {"http://kingtopgroup.com/mobile/images/banner01.jpg",
					"http://kingtopgroup.com/mobile/images/banner02.jpg",
					"http://kingtopgroup.com/mobile/images/banner03.jpg"};*/
		
		for(int i = 0; i < imageUrls.length; i ++){
			ADInfo info = new ADInfo();
			info.setUrl(imageUrls[i]);
			info.setContent("閸ュ墽澧�-->" + i );
			infos.add(info);
		}
		
		// 鐏忓棙娓堕崥搴濈娑撶嫪mageView濞ｈ濮炴潻娑欐降
		views.add(ViewFactory.getImageView(context, infos.get(infos.size() - 1).getUrl()));
		for (int i = 0; i < infos.size(); i++) {
			views.add(ViewFactory.getImageView(context, infos.get(i).getUrl()));
		}
		// 鐏忓棛顑囨稉锟芥稉鐙猰ageView濞ｈ濮炴潻娑欐降
		views.add(ViewFactory.getImageView(context, infos.get(0).getUrl()));
		
		// 鐠佸墽鐤嗗顏嗗箚閿涘苯婀拫鍐暏setData閺傝纭堕崜宥堢殶閻拷
		cycleViewPager.setCycle(true);

		// 閸︺劌濮炴潪鑺ユ殶閹诡喖澧犵拋鍓х枂閺勵垰鎯佸顏嗗箚
		cycleViewPager.setData(views, infos, null);
		//鐠佸墽鐤嗘潪顔芥尡
		cycleViewPager.setWheel(true);

	    // 鐠佸墽鐤嗘潪顔芥尡閺冨爼妫块敍宀勭帛鐠侊拷5000ms
		cycleViewPager.setTime(2000);
		//鐠佸墽鐤嗛崷鍡欏仯閹稿洨銇氶崶鐐垼缂佸嫬鐪虫稉顓熸▔缁�鐚寸礉姒涙顓婚棃鐘插礁
		cycleViewPager.setIndicatorCenter();
		
		
		
		
	}

	/*private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

		@Override
		public void onImageClick(ADInfo info, int position, View imageView) {
			if (cycleViewPager.isCycle()) {
				position = position - 1;
				Toast.makeText(manipulationActivty.this,
						"position-->" + info.getContent(), Toast.LENGTH_SHORT)
						.show();
			}
			
		}

	};*/

	/**
	 * 闁板秶鐤咺mageLoder
	 */
	public void configImageLoader(Context context) {
		// 閸掓繂顫愰崠鏈搈ageLoader
		DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.icon_stub) // 鐠佸墽鐤嗛崶鍓у娑撳娴囬張鐔兼？閺勫墽銇氶惃鍕禈閻楋拷
				.showImageForEmptyUri(R.drawable.icon_empty) // 鐠佸墽鐤嗛崶鍓уUri娑撹櫣鈹栭幋鏍ㄦЦ闁挎瑨顕ら惃鍕閸婃瑦妯夌粈铏规畱閸ュ墽澧�
				.showImageOnFail(R.drawable.icon_error) // 鐠佸墽鐤嗛崶鍓у閸旂姾娴囬幋鏍掗惍浣界箖缁嬪鑵戦崣鎴犳晸闁挎瑨顕ら弰鍓с仛閻ㄥ嫬娴橀悧锟�
				.cacheInMemory(true) // 鐠佸墽鐤嗘稉瀣祰閻ㄥ嫬娴橀悧鍥ㄦЦ閸氾妇绱︾�涙ê婀崘鍛摠娑擄拷
				.cacheOnDisc(true) // 鐠佸墽鐤嗘稉瀣祰閻ㄥ嫬娴橀悧鍥ㄦЦ閸氾妇绱︾�涙ê婀猄D閸椻�茶厬
				// .displayer(new RoundedBitmapDisplayer(20)) // 鐠佸墽鐤嗛幋鎰妇鐟欐帒娴橀悧锟�
				.build(); // 閸掓稑缂撻柊宥囩枂鏉╁洤绶盌isplayImageOption鐎电钖�
 
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context.getApplicationContext()).defaultDisplayImageOptions(options)
				.threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(config);		
	}





}

package com.example.viewpager.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.example.viewpager.R;
import com.example.viewpager.util.App;
import com.example.viewpager.util.ImageBean;

public final class TestFragment extends Fragment {
    private static final String KEY_CONTENT = "TestFragment:Content";
    /**
     *  资源图片（可改为网络资源） 
     */
//    private int mContent = R.drawable.p1;
    private ImageBean bean;
    public static TestFragment newInstance(ImageBean bean) {
        TestFragment fragment = new TestFragment();
        fragment.bean = bean;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
        	bean.setUrl(savedInstanceState.getString(KEY_CONTENT));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	NetworkImageView iv = new NetworkImageView(getActivity());
    	iv.setDefaultImageResId(R.drawable.p1);
    	iv.setErrorImageResId(R.drawable.p1);
        iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        //如果图片是网络图片的话改成异步加载
//        iv.setBackgroundResource(bean.getIds());
        iv.setPadding(20, 20, 20, 20);
//        AsynImageLoader asynImageLoader = new AsynImageLoader();
//        asynImageLoader.showImageAsyn(iv, bean.getUrl(), R.drawable.p1);
        iv.setImageUrl(bean.getUrl(),App.imageLoader);
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        layout.setGravity(Gravity.CENTER);
        layout.addView(iv);
        //view设置点击事件
        layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "当前点击--->"+ bean.getUrl(), 1000).show();
			}
		});
        return layout;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, bean.getUrl());
    }
}

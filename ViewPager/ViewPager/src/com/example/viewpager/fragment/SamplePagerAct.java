package com.example.viewpager.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.viewpager.R;
import com.example.viewpager.util.SamplePagerView;

public class SamplePagerAct extends FragmentActivity{
	private SamplePagerView pagerView;
	private LinearLayout llRoot;
	private Switch scOpen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        llRoot = (LinearLayout)this.findViewById(R.id.ll_root);
        scOpen = (Switch)this.findViewById(R.id.sc_open);
        pagerView = new SamplePagerView(this);
        pagerView.init();
        llRoot.addView(pagerView, 0);
        scOpen.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					pagerView.setOpen(true);
				}else {
					pagerView.setOpen(false);
				}
			}
		});
    }
}
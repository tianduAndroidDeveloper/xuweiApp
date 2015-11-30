package com.android.androidframework.ui.home;

import org.androidannotations.annotations.EFragment;

import com.android.androidframework.ui.member.MyIntegrateActivity_;
import com.xuwei.app.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

@EFragment()
public class FramFourFragment extends Fragment implements OnClickListener {
	View rootView;
	Context mContext;

	@Override
	public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
		if (null != rootView) {
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if (null != parent) {
				parent.removeView(rootView);
			}
		} else {
			rootView = LayoutInflater.from(getActivity()).inflate(R.layout.memeber_center_layout, null);
			rootView.findViewById(R.id.myIntegrate).setOnClickListener(this);
			mContext = getActivity();
			
		}
		return rootView;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.myIntegrate:
			MyIntegrateActivity_.intent(this).start();
			break;
		}
		
	}

}

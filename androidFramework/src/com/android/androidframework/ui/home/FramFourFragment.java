package com.android.androidframework.ui.home;

import org.androidannotations.annotations.EFragment;
import com.android.androidframework.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@EFragment()
public class FramFourFragment extends Fragment {
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
			rootView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main, null);
			
			mContext = getActivity();
			
		}
		return rootView;
	}

}

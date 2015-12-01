package com.android.androidframework.ui.home;

import org.androidannotations.annotations.EFragment;

import com.xuwei.app.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.androidframework.ui.commodity.CategoryActivity_;
import com.android.androidframework.ui.commodity.CommodityActivity_;

@EFragment()
public class FramTwoFragment extends Fragment implements OnClickListener {
	View rootView;
	Context mContext;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (null != rootView) {
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if (null != parent) {
				parent.removeView(rootView);
			}
		} else {
			rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_two, null);
			mContext = getActivity();
		}
		init();
		return rootView;
	}

	void init() {
		Button btn = (Button) rootView.findViewById(R.id.btn);
		Button btn2 = (Button) rootView.findViewById(R.id.btn2);
		btn.setOnClickListener(this);
		btn2.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.btn:
			CommodityActivity_.intent(mContext).start();
			break;
		case R.id.btn2:
			CategoryActivity_.intent(mContext).start();
			break;
		default:
			break;
		}
	}

}

package com.android.androidframework.ui.member.order;

import org.androidannotations.annotations.EFragment;
import com.xuwei.app.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@EFragment()
public class ChildFragment extends Fragment {
   

    public static ChildFragment newInstance() {
        ChildFragment newFragment = new ChildFragment();
       
        return newFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
      
        View view = inflater.inflate(R.layout.activity_main, container, false);
      
        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        
    }
    
   

}

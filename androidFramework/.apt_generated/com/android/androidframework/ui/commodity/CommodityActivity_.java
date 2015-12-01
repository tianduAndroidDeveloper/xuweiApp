//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package com.android.androidframework.ui.commodity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RadioGroup;
import com.android.androidframework.ui.view.MyGridView;
import com.xuwei.app.R.id;
import com.xuwei.app.R.layout;
import org.androidannotations.api.SdkVersionHelper;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class CommodityActivity_
    extends CommodityActivity
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    public final static String CATEGORY_ID_EXTRA = "category_id";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
        setContentView(layout.activity_commodity);
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        injectExtras_();
        initData();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static CommodityActivity_.IntentBuilder_ intent(Context context) {
        return new CommodityActivity_.IntentBuilder_(context);
    }

    public static CommodityActivity_.IntentBuilder_ intent(Fragment supportFragment) {
        return new CommodityActivity_.IntentBuilder_(supportFragment);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (((SdkVersionHelper.getSdkInt()< 5)&&(keyCode == KeyEvent.KEYCODE_BACK))&&(event.getRepeatCount() == 0)) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        gv = ((MyGridView) hasViews.findViewById(id.gv));
        rg = ((RadioGroup) hasViews.findViewById(id.rg));
        {
            View view = hasViews.findViewById(id.rb_price);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        CommodityActivity_.this.priceSort();
                    }

                }
                );
            }
        }
        {
            View view = hasViews.findViewById(id.rb_time);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        CommodityActivity_.this.timeSort();
                    }

                }
                );
            }
        }
        {
            View view = hasViews.findViewById(id.rb_condition);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        CommodityActivity_.this.conditionSort();
                    }

                }
                );
            }
        }
        {
            View view = hasViews.findViewById(id.rb_sell);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        CommodityActivity_.this.sellSort();
                    }

                }
                );
            }
        }
        initUI();
    }

    private void injectExtras_() {
        Bundle extras_ = getIntent().getExtras();
        if (extras_!= null) {
            if (extras_.containsKey(CATEGORY_ID_EXTRA)) {
                category_id = extras_.getString(CATEGORY_ID_EXTRA);
            }
        }
    }

    @Override
    public void setIntent(Intent newIntent) {
        super.setIntent(newIntent);
        injectExtras_();
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;
        private Fragment fragmentSupport_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, CommodityActivity_.class);
        }

        public IntentBuilder_(Fragment fragment) {
            fragmentSupport_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, CommodityActivity_.class);
        }

        public Intent get() {
            return intent_;
        }

        public CommodityActivity_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

        public void startForResult(int requestCode) {
            if (fragmentSupport_!= null) {
                fragmentSupport_.startActivityForResult(intent_, requestCode);
            } else {
                if (context_ instanceof Activity) {
                    ((Activity) context_).startActivityForResult(intent_, requestCode);
                } else {
                    context_.startActivity(intent_);
                }
            }
        }

        public CommodityActivity_.IntentBuilder_ category_id(String category_id) {
            intent_.putExtra(CATEGORY_ID_EXTRA, category_id);
            return this;
        }

    }

}
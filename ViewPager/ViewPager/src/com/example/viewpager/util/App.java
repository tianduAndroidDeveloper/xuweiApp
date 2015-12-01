package com.example.viewpager.util;

import android.app.Application;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class App extends Application {
	public static ImageLoader imageLoader;
	public static BitmapCache bitmapCache;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		bitmapCache = new BitmapCache();
		imageLoader = new ImageLoader(Volley.newRequestQueue(getApplicationContext()), bitmapCache);
	}
}

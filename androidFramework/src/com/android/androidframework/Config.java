package com.android.androidframework;

import java.io.File;

import android.os.Environment;

public class Config {
	private static String serverHost;
	private static String mLocalDir = Environment.getExternalStorageDirectory().getPath();

	public static String getServerHost() {
		return serverHost;
	}

	public static void setServerHost(String host) {
		serverHost = host;
	}
	
	 public static String getLocalDir(String subDir) {
	        String dirPath = mLocalDir + subDir;
	        File dir = new File(dirPath);
	        if (!dir.exists()) {
	            dir.mkdirs();
	        }
	        return dirPath;
	    }
	 
	 public static void setLocalDir(String dir){
		 mLocalDir += "/" + dir + "/";
	 }
}

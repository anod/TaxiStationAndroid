package com.station.taxi;

import android.util.Log;

public class LoggerWrapper {

	public static void log() {
//s		Log.d(tag, msg)
	}

	public static void logException(String name, Exception ex) {
		Log.e(name, "Exception: ", ex);
	}
	
	
}

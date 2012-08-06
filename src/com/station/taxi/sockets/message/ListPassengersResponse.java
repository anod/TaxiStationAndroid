package com.station.taxi.sockets.message;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Message that contain list of waiting passengers
 * @author alex
 */
public class ListPassengersResponse extends AbstractResponse {
	private static final String KEY_PASSENGERS = "passengers";

	private Map<String,String> mPassengers = new HashMap<String, String>();

	/**
	 * @return the mPassengers
	 */
	public Map<String,String> getPassengers() {
		return mPassengers;
	}

	public void addPassenger(String name, String destination) {
		mPassengers.put(name, destination);
	}

	@Override
	protected void parseType(JSONObject json) throws JSONException {
		JSONObject jsonPassengers = (JSONObject) json.get(KEY_PASSENGERS);
		Iterator keys = jsonPassengers.keys();
		while(keys.hasNext()) {
			String kStr = (String)keys.next();
			String kValue = (String)jsonPassengers.get(kStr);
			mPassengers.put(kStr, kValue);
		}
	}
	
}

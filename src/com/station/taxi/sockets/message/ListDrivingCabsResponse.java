/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.station.taxi.sockets.message;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author alex
 */
public class ListDrivingCabsResponse extends AbstractResponse{
	private static final String KEY_CABS = "cabs";
	private static final String KEY_STATUS = "statuses";

	public static final String STATUS_BREAK = "onBreak";
	public static final String STATUS_WAITING = "waiting";
	private static final String KEY_DESTINATION = "destination";
	private static final String KEY_PASSENGERS = "passengers";
	
	private Map<Integer,Map<String,Object>> mCabs = new HashMap<Integer, Map<String, Object>>();

	public Set<Integer> getCabNumbers() {
		return mCabs.keySet();
	}
	
	@Override
	protected void parseType(JSONObject json) throws JSONException {
		Map<Integer, Map<String, Object>> cabs = (Map<Integer,Map<String,Object>>) json.get(KEY_CABS);
		Map<String, Object> data;
		for(Object key: cabs.keySet()) {
			int number = (Integer) key;
			data = cabs.get(key);
			mCabs.put(number, data);
		}
	}

}

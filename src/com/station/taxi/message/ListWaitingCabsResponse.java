/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.station.taxi.message;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author alex
 */
public class ListWaitingCabsResponse extends AbstractResponse {
	private static final String KEY_WAITACTIONS = "waitActions";
	private static final String KEY_STATUS = "statuses";

	public static final String STATUS_BREAK = "onBreak";
	public static final String STATUS_WAITING = "waiting";
	
	private Map<Integer,String> mCabsWaitActions = new HashMap<Integer, String>();
	private Map<Integer,String> mCabsStatus = new HashMap<Integer, String>();

	/**
	 * @return the mCabsWaitActions
	 */
	public Map<Integer,String> getCabsWaitActions() {
		return mCabsWaitActions;
	}

	/**
	 * @return the mCabsStatus
	 */
	public Map<Integer,String> mCabsStatus() {
		return mCabsStatus;
	}

	@Override
	protected void parseType(JSONObject json) throws JSONException {
		mCabsWaitActions = (Map<Integer, String>) json.get(KEY_WAITACTIONS);
		mCabsStatus = (Map<Integer, String>) json.get(KEY_STATUS);
	}
	
}

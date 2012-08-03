package com.station.taxi.message;

import org.json.JSONException;
import org.json.JSONObject;

import com.station.taxi.LoggerWrapper;

/**
 *
 * @author alex
 */
public class MessageFactory {
	
	public static final String KEY_ACTION = "action";

	public static final String ACTION_ADDCAB = "addcab";
	public static final String ACTION_LIST_WAITING_CABS = "list_waiting_cabs";
	public static final String ACTION_LIST_WAITING_PASSENGERS = "list_waiting_passengers";
	public static final String ACTION_LIST_DRIVING = "list_driving";
	public static final String ACTION_EXIT = "exit";

	/**
	 * Create StationSocket response message
	 * @param action
	 * @return 
	 */
	public static AbstractResponse createResponse(final String action) {
		if (action.equals(ACTION_LIST_WAITING_PASSENGERS)) {
			return new ListPassengersResponse();
		}
		if (action.equals(ACTION_ADDCAB) || 
			action.equals(ACTION_EXIT) ){
			return new SimpleResponse();
		}
		throw new IllegalArgumentException("Unknown action:" + action);
	}
	
	/**
	 * Parse StationSocket response message
	 * @param json
	 * @return 
	 */
	public static AbstractResponse parseResponse(JSONObject json) {
		String action;
		try {
			action = (String) json.get(KEY_ACTION);
		} catch (JSONException e) {
			LoggerWrapper.logException(MessageFactory.class.getSimpleName(), e);
			return null;
		}
		AbstractResponse response = createResponse(action);
		response.parse(json);
		return response;
	}
	
	/**
	 * Create StationClient request
	 * @param action
	 * @return 
	 */
	public static Request parseRequest(JSONObject json) {
		Request request = new Request();
		request.parse(json);
		return request;
	}
}

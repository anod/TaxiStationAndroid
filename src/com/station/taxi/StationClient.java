package com.station.taxi;

import org.json.JSONObject;

import com.station.taxi.message.AbstractResponse;
import com.station.taxi.message.MessageFactory;
import com.station.taxi.message.Request;


/**
 *
 * @author alex
 */
public class StationClient {
	private static final int PORT = 13000;
	private static final String HOST = "10.0.2.2";

	public static final String REQUEST_LIST_WAITING_CABS= "list_waiting_cabs";
	public static final String REQUEST_LIST_WAITING_PASSENGER = "list_waiting_passenger";
	public static final String REQUEST_LIST_DRIVING = "list_driving";

	private final Client mClient;

	public StationClient() {
		mClient = new JSONClient(HOST, PORT);
	}

	public AbstractResponse request(String input) {
		if (!mClient.connect()) {
			return null;
		}
		Request msg = null;
		AbstractResponse response = null;
		if (input.equals(REQUEST_LIST_WAITING_CABS)) {
			msg = new Request(MessageFactory.ACTION_LIST_WAITING_CABS);
		} else if (input.equals(REQUEST_LIST_WAITING_PASSENGER)) {
			msg = new Request(MessageFactory.ACTION_LIST_WAITING_PASSENGERS);			
		} else if (input.equals(REQUEST_LIST_DRIVING)) {
			msg = new Request(MessageFactory.ACTION_LIST_DRIVING);
		}

		if (msg!=null) {
			mClient.sendRequest(msg.toJSON());
			JSONObject json = (JSONObject)mClient.receiveResponse();
			response = MessageFactory.parseResponse(json);
		}
		mClient.close();
		return response;
	}
	

}

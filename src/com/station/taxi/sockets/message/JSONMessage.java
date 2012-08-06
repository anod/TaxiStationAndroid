package com.station.taxi.sockets.message;

import org.json.JSONObject;

/**
 *
 * @author alex
 */
public interface JSONMessage extends Message {
	
	/**
	 * Parse json message
	 */
	public void parse(JSONObject json);
}

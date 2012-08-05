package com.station.taxi;

import com.station.taxi.LoggerWrapper;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JSON Implementation of client
 * @author alex
 */
public class JSONClient implements Client {
	private JSONSocket mJSONSocket;

	private final String mHost;
	private final Integer mPort;
	
	public JSONClient(String host, Integer port) {
		mHost = host;
		mPort = port;
	}
	
	public boolean connect() {
		try {
			mJSONSocket = new JSONSocket(new Socket(mHost, mPort));
			mJSONSocket.init();
		} catch (UnknownHostException ex) {
			LoggerWrapper.logException(StationClient.class.getName(), ex);
			return false;		
		} catch (IOException ex) {
			LoggerWrapper.logException(StationClient.class.getName(), ex);
			return false;
		}
		return true;
	}

	public void close() {
		if (mJSONSocket == null) {
			return;
		}
		try {
			mJSONSocket.close();
		} catch (IOException ex) {
			LoggerWrapper.logException(StationClient.class.getName(), ex);
		}
		mJSONSocket = null;
	}

	public void sendRequest(Object request) {
		mJSONSocket.sendMessage(request);
	}

	public Object receiveResponse() {
		try {
			return mJSONSocket.receiveMessage();
		} catch (IOException ex) {
			LoggerWrapper.logException(JSONClient.class.getName(), ex);
			return null;
		}
	}
	
}

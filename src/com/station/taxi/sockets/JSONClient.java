package com.station.taxi.sockets;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.station.taxi.utils.LoggerWrapper;

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
			Socket socket = new Socket(mHost, mPort);
			LoggerWrapper.log(JSONClient.class.getSimpleName(), 
					"Connected to "+socket.getInetAddress()+":"+socket.getPort()
					+" from "+socket.getLocalSocketAddress()
			);
			mJSONSocket = new JSONSocket(socket);
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
		return mJSONSocket.receiveMessage();
	}
	
}

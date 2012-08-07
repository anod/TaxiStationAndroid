package com.station.taxi.sockets;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import com.station.taxi.utils.LoggerWrapper;

/**
 * JSON Implementation of client
 * @author alex
 */
public class JSONClient implements Client {
	private static final int TIMEOUT = 2000;

	private JSONSocket mJSONSocket;

	private final String mHost;
	private final Integer mPort;
	
	public JSONClient(String host, Integer port) {
		mHost = host;
		mPort = port;
	}
	
	public boolean connect() {
		try {
			SocketAddress sockaddr = new InetSocketAddress(mHost, mPort);
			Socket socket = new Socket();
			socket.connect(sockaddr, TIMEOUT);
			LoggerWrapper.log(JSONClient.class.getSimpleName(), 
					"Connected to "+socket.getInetAddress()+":"+socket.getPort()
					+" from "+socket.getLocalSocketAddress()
			);
			mJSONSocket = new JSONSocket(socket);
			mJSONSocket.init();
		} catch (Exception ex) {
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

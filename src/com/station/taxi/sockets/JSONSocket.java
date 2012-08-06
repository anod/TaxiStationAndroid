
package com.station.taxi.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import org.json.JSONObject;
import org.json.JSONTokener;

import com.station.taxi.utils.LoggerWrapper;

/**
 * Socket wrapper
 * @author alex
 */
public class JSONSocket {
	
	private BufferedReader mFromNetInputStream;
	private PrintStream mToNetOutputStream;	
	private final Socket mSocket;

	public JSONSocket(Socket socket) {
		mSocket = socket;
	}

	public void init() throws IOException {
		mFromNetInputStream = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
		mToNetOutputStream = new PrintStream(mSocket.getOutputStream());
	}
	
	public void sendMessage(Object message) {
		String msg = ((JSONObject)message).toString();
		LoggerWrapper.log(JSONSocket.class.getSimpleName(), "Sending: " + msg);
		mToNetOutputStream.println(msg);
	}
	
	public Object receiveMessage() {
		JSONObject message = null;
		try {
			String string = mFromNetInputStream.readLine();
			LoggerWrapper.log(JSONSocket.class.getSimpleName(), "Received: " + string);
			message = (JSONObject) new JSONTokener(string).nextValue();
		} catch (Exception e) {
			LoggerWrapper.logException(JSONSocket.class.getSimpleName(), e);
		}
		return message;
	}

	public void close() throws IOException {
		mSocket.close();
	}

	public Socket getSocket() {
		return mSocket;
	}
}

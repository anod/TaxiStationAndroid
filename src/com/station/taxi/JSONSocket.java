
package com.station.taxi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

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
		mToNetOutputStream.println(((JSONObject)message).toString());		
	}
	
	public Object receiveMessage() throws IOException {
		String string = mFromNetInputStream.readLine();
		JSONObject message = null;
		try {
			message = (JSONObject) new JSONTokener(string).nextValue();
		} catch (JSONException e) {
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

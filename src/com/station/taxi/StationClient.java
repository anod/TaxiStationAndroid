package com.station.taxi;


/**
 *
 * @author alex
 */
public class StationClient {
	private static final int PORT = 13000;

	private static final String HOST = "localhost";

	private static final String USER_ACTION_EXIT = "exit";
	private static final String USER_ACTION_ADDCAB = "addcab";
	private static final String USER_ACTION_ADDPASSENGER = "addpassenger";
	private static final String USER_ACTION_LIST_WAITING_CABS= "list_waiting_cabs";
	private static final String USER_ACTION_LIST_WAITING_PASSENGER = "list_waiting_passenger";
	private static final String USER_ACTION_LIST_DRIVING = "list_driving";

	private static final String[] sUserActions = {
		USER_ACTION_ADDCAB,
		USER_ACTION_ADDPASSENGER,
		USER_ACTION_LIST_WAITING_CABS,
		USER_ACTION_LIST_WAITING_PASSENGER,
		USER_ACTION_LIST_DRIVING,
		USER_ACTION_EXIT
	}; 

	private final Client mClient;

	public StationClient() {
		mClient = new JSONClient(HOST, PORT);
	}

	public void request(String input) {
		if (!mClient.connect()) {
			return;
		}

		if (input.equals(USER_ACTION_LIST_WAITING_CABS)) {
			
		} else if (input.equals(USER_ACTION_LIST_WAITING_PASSENGER)) {
			
		}

//		mClient.sendRequest(msg.toJSON());
		
		mClient.close();
	}
	

}

package com.station.taxi.sockets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.station.taxi.sockets.message.ListWaitingCabsResponse;

public class SocketClientLoader extends AsyncTaskLoader<List<String>> {

	private String mServerIp;

	public SocketClientLoader(String serverIp, Context context) {
		super(context);
		mServerIp = serverIp;
	}

	@Override
	public List<String> loadInBackground() {
		StationClient client = new StationClient(mServerIp);
		List<String> list = new ArrayList<String>();
		ListWaitingCabsResponse response = (ListWaitingCabsResponse) client.request(StationClient.REQUEST_LIST_WAITING_CABS);
		if (response != null) {
			Map<Integer,String> nums = response.getCabsStatus();
			for(Integer num: nums.keySet()) {
				list.add(String.valueOf(num));
			}
		}
		return list;
	}
	
}


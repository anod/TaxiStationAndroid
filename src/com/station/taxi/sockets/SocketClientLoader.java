package com.station.taxi.sockets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.station.taxi.sockets.message.ListDrivingCabsResponse;

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
		ListDrivingCabsResponse response = (ListDrivingCabsResponse) client.request(StationClient.REQUEST_LIST_WAITING_CABS);
		if (response != null) {
			Set<Integer> nums = response.getCabNumbers();
			for(Integer num: nums) {
				list.add(String.valueOf(num));
			}
		}
		return list;
	}
	
}


package com.station.taxi.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.station.taxi.R;


/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
 * sections of the app.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

	private String mCurrentIp;
    private StationActivity mActivity;

	public SectionsPagerAdapter(StationActivity activity, FragmentManager fm) {
        super(fm);
        mActivity = activity;
    }
	
	public void setIp(String ip) {
		mCurrentIp = ip;
	}

    @Override
    public Fragment getItem(int i) {
    	if (mCurrentIp == null) {
    		throw new IllegalArgumentException("Current ip was not set");
    	}
    	return ItemsListFragment.newInstance(mCurrentIp,i);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return mActivity.getString(R.string.title_section1).toUpperCase();
            case 1: return mActivity.getString(R.string.title_section2).toUpperCase();
            case 2: return mActivity.getString(R.string.title_section3).toUpperCase();
        }
        return null;
    }
}

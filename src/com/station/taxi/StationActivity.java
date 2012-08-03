package com.station.taxi;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.ArrayAdapter;

import com.station.taxi.message.ListDrivingCabsResponse;

public class StationActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * sections. We use a {@link android.support.v4.app.FragmentPagerAdapter} derivative, which will
     * keep every loaded fragment in memory. If this becomes too memory intensive, it may be best
     * to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);
        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_station, menu);
        return true;
    }

    


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            ListFragment fragment = new ItemsListFragment();
            Bundle args = new Bundle();
            args.putInt(ItemsListFragment.ARG_SECTION_NUMBER, i + 1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return getString(R.string.title_section1).toUpperCase();
                case 1: return getString(R.string.title_section2).toUpperCase();
                case 2: return getString(R.string.title_section3).toUpperCase();
            }
            return null;
        }
    }

    /**
     * A dummy fragment representing a section of the app, but that simply displays dummy text.
     */
    public static class ItemsListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<String>> {
        // This is the Adapter being used to display the list's data.
    	ArrayAdapter<String> mAdapter;
		private Context mContext;

		public ItemsListFragment() {
        }

        public static final String ARG_SECTION_NUMBER = "section_number";

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);

        	mContext = getActivity();
        	// Give some text to display if there is no data.  In a real
            // application this would come from a resource.
            setEmptyText("No items");

            // We have a menu item to show in action bar.
            setHasOptionsMenu(true);

            // Create an empty adapter we will use to display the loaded data.
        	mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);

            setListAdapter(mAdapter);

            // Start out with a progress indicator.
            setListShown(false);

            // Prepare the loader.  Either re-connect with an existing one,
            // or start a new one.
            getLoaderManager().initLoader(0, null, this).forceLoad();
		}


		public void onLoadFinished(Loader<List<String>> loader, List<String> items) {
	        // Swap the new cursor in.  (The framework will take care of closing the
	        // old cursor once we return.)
	        mAdapter.addAll(items);

	        // The list should now be shown.
	        if (isResumed()) {
	            setListShown(true);
	        } else {
	            setListShownNoAnimation(true);
	        }			
		}

		public void onLoaderReset(Loader<List<String>> loader) {
	        mAdapter.clear();
		}

		public Loader<List<String>> onCreateLoader(int id, Bundle args) {
			return new SocketClientLoader(mContext);
		}
 
    }
    
    public static class SocketClientLoader extends AsyncTaskLoader<List<String>> {

		public SocketClientLoader(Context context) {
			super(context);
		}

		@Override
		public List<String> loadInBackground() {
			StationClient client = new StationClient();
			ListDrivingCabsResponse response = (ListDrivingCabsResponse) client.request(StationClient.REQUEST_LIST_WAITING_CABS);
			List<String> list = new ArrayList<String>();
			if (response != null) {
				Set<Integer> nums = response.getCabNumbers();
				for(Integer num: nums) {
					list.add(String.valueOf(num));
				}
			}
			return list;
		}
    	
    }
    
}

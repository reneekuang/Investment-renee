package com.renee.investment.myInvestment;

import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renee.investment.R;
import com.renee.investment.database.DBAdapter;

public class MyInvestmentFragment extends ListFragment {
	private DBAdapter dbAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_myinvestment,
				container, false);

		return rootView;
}

	@Override
	public void onResume() {
		super.onResume();
	}
	
}

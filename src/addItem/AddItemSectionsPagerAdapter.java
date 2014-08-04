package addItem;

import java.util.Locale;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;

import com.renee.investment.R;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class AddItemSectionsPagerAdapter extends FragmentPagerAdapter {
	protected Context mContext;

	public AddItemSectionsPagerAdapter(Context context, FragmentManager fm) {
		super(fm);
		mContext = context;
	}

	@Override
	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		// Return a PlaceholderFragment (defined as a static inner class
		// below).
		
		switch(position) {
		case 0:
			return new ProductOneFragment();
		case 1:
			return new ProductTwoFragment();
		case 2:
			return new ProductThreeFragment();
		case 3:
			return new ProductFourFragment();
		}
		return null;

	}

	@Override
	public int getCount() {
		return 4;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Locale l = Locale.getDefault();
		switch (position) {
		case 0:
			return mContext.getString(R.string.productOne_label).toUpperCase(l);
		case 1:
			return mContext.getString(R.string.productTwo_label).toUpperCase(l);
		case 2:
			return mContext.getString(R.string.productThree_label).toUpperCase(l);
		case 3:
			return mContext.getString(R.string.productFour_label).toUpperCase(l);
		}
		return null;
	}
}
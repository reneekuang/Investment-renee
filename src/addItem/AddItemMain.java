package addItem;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.renee.investment.R;

public class AddItemMain extends Activity  {
	AddItemSectionsPagerAdapter mAddItemSectionsPagerAdapter;
	ViewPager mViewPager;
        

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);
		
		final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        
        //定义一个下拉列表数据适配器
        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this,
                        R.array.product_label,
                        R.layout.spinner_additem);                        
        
        actionBar.setListNavigationCallbacks(mSpinnerAdapter, new ActionBar.OnNavigationListener() {
			
			@Override
			public boolean onNavigationItemSelected(int itemPosition, long itemId) {

				return true;
			}


		});
        }

  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
          getMenuInflater().inflate(R.menu.add_item, menu);
          return true;
  }
}

         



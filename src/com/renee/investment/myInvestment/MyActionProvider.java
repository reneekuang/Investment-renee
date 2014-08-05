package com.renee.investment.myInvestment;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;

import com.renee.investment.R;

public class MyActionProvider extends ActionProvider {
    public MyActionProvider(Context context) {
		super(context);
        mContextWrapper = (ContextWrapper)context;

	}

	/** Context wrapper. */
    private ContextWrapper mContextWrapper;

    PopupMenu mPopupMenu;


    @Override
    public View onCreateActionView() {
        // Inflate the action view to be shown on the action bar.
        LayoutInflater layoutInflater = LayoutInflater.from(mContextWrapper);
        View view = layoutInflater.inflate(R.layout.my_action_provider, null);
        ImageView popupView = (ImageView)view.findViewById(R.id.popup_view);
        popupView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });
        return view;
    }

    /**
     * show the popup menu.
     *
     * @param v
     */
    private void showPopup(View v) {
        mPopupMenu = new PopupMenu(mContextWrapper, v);
        mPopupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // do someting
                return true;
            }

        });
        MenuInflater inflater = mPopupMenu.getMenuInflater();
        inflater.inflate(R.menu.popup_additem, mPopupMenu.getMenu());
        mPopupMenu.show();
    }
}

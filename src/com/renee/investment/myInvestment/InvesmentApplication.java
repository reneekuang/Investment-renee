package com.renee.investment.myInvestment;

import android.app.Application;

import com.parse.Parse;

public class InvesmentApplication extends Application{


	@Override
	public void onCreate() {
		  Parse.initialize(this, "NGurJy6JwMAcW9sMwOpVRCyw4XOiNhtcReZDVxZ4", "ZBZNCHQl0kQYtVQj51fs6rLqceVrenTrUiyuIWWO");
		}

}

package parseRelate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.renee.investment.R;
import com.renee.investment.myInvestment.MyMainActivity;

public class SignUpActivity extends Activity {
	protected EditText mUsername;
	protected EditText mPassword;
	protected Button mSignUpButton;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
		mUsername = (EditText) findViewById(R.id.signup_username);
		mPassword = (EditText) findViewById(R.id.signup_password);
		mSignUpButton = (Button) findViewById(R.id.signup_button);
		
		mSignUpButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String Username = mUsername.getText().toString();
				String Password = mPassword.getText().toString();
				//check whether have spaces in user name and password.
				Username.trim();
				Password.trim();
				//check whether the user name or password is empty.
				if (Username.isEmpty() || Password.isEmpty()) {
					AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
					builder.setMessage(R.string.signup_emptydialog_message)
					.setTitle(R.string.signup_errordialog_title)
					.setPositiveButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.show();      
				}
				if (Username.length()<6) {
					AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
					builder.setMessage(R.string.signup_lessdiaglog_message)
					.setTitle(R.string.signup_errordialog_title)
					.setPositiveButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.show();     
				}
				if (Password.length()<6) {
					AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
					builder.setMessage(R.string.signup_lesspassworddiaglog_message)
					.setTitle(R.string.signup_errordialog_title)
					.setPositiveButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.show();     
				}
				else {
					//create a new user.
					ParseUser newUser = new ParseUser();
					newUser.setUsername(Username);
					newUser.setPassword(Password);
					newUser.signUpInBackground(new SignUpCallback() {
						
						@Override
						public void done(ParseException e) {
							if (e == null ){
								//success
								Intent intent = new Intent(SignUpActivity.this, MyMainActivity.class);
								intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
								startActivity(intent);
							}
							else {
								AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
								builder.setMessage(e.getMessage())
								.setTitle(R.string.signup_errordialog_title)
								.setPositiveButton(android.R.string.ok, null);
								AlertDialog dialog = builder.create();
								dialog.show();

							}
							
						}
					});
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

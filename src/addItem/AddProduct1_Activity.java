package addItem;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.renee.investment.R;
import com.renee.investment.database.DBAdapter;

public class AddProduct1_Activity extends Activity {

	public static final String TAG = AddProduct1_Activity.class.getSimpleName();
	 private DBAdapter dbAdapter;
	EditText annualYield, moneyAmount, bankName;
	Button calculate;
	float yearValue;
	TextView result;
	float interest = 0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_product1);
		
        dbAdapter = new DBAdapter(this);
        dbAdapter.open();

		bankName = (EditText) findViewById(R.id.product1_name);
		annualYield = (EditText) findViewById(R.id.product1_yield);
		moneyAmount = (EditText) findViewById(R.id.product1_amount);
		calculate = (Button) findViewById(R.id.product1_calculate);
		result = (TextView) findViewById(R.id.product1_result);
		
		calculate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//calculate
				float yield = Float.parseFloat(annualYield.getText().toString());
				float amount = Float.parseFloat(moneyAmount.getText().toString());
				
				
				interest = (yield/100)*amount*yearValue;
				
				result.setText(interest + "RMB");
				
			}
		});
		
		Spinner spinner = (Spinner) findViewById(R.id.product1_duration_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.product1_duration, 
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {  
			  
            @Override  
            public void onItemSelected(AdapterView<?> parent, View view,  
                    int position, long id) {  
                if (position == 0) {
                	//current deposit
                	yearValue = (float) 1/360;
                }
                if (position == 1) {
                	//3 month
                	yearValue = (float) 0.25;
                }
                if (position == 2) {
                	//half year
                	yearValue = (float) 0.5;               
                }
                if (position == 3) {
                	//1 year
                	yearValue = (float) 1;
                }
                if (position == 4) {
                	//2 year
                	yearValue = (float) 2;
                }
                if (position == 5) {
                	//3 year
                	yearValue = (float) 3;
                }
                if (position == 6) {
                	// 5yr
                	yearValue = (float) 5;
                }
            }  
            
            
            @Override  
            public void onNothingSelected(AdapterView<?> parent) {  
            }  
        });    
        
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_product1, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.addproduct1_save) {
			SaveProduct();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void SaveProduct() {
		Product1Object object = new Product1Object();
		object.bankName = bankName.getText().toString();
		object.moneyAmount = Float.parseFloat(moneyAmount.getText().toString());
		object.annualYield = Float.parseFloat(annualYield.getText().toString());
		object.interest = Float.parseFloat(interest+"");
		long colunm = dbAdapter.insert(object);
		if(colunm == -1)
		{
			Toast.makeText(AddProduct1_Activity.this, "error", Toast.LENGTH_LONG).show();
		
		}
		else
		{
			Toast.makeText(AddProduct1_Activity.this, "成功添加数据 ", 
					Toast.LENGTH_LONG).show();
		
		}
		
	}

	
}

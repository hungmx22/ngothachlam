package vn.itplus.dungpt;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		final ArrayList<PersonModel> list = new ArrayList<PersonModel>();
		PersonModel person = new PersonModel("Amirakhan", "0123456789", 
				getResources().getDrawable(R.drawable.aamirkhan));
		list.add(person);
		person = new PersonModel("Barack Obama", "01987654321", 
				getResources().getDrawable(R.drawable.barackobama));
		list.add(person);
		person = new PersonModel("Yoona", "01888888888", 
				getResources().getDrawable(R.drawable.yoona));
		list.add(person);
		PersonAdapter adapter = new PersonAdapter(this, list);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				Intent i = new Intent(getBaseContext(), PersonDetailActivity.class);
				i.putExtra("Name", list.get(position).getName());
				i.putExtra("Phone", list.get(position).getPhone());
				startActivity(i);
			}
		});
		
		listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				final int pos = position;
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
				// set title
				alertDialogBuilder.setTitle("Your Title");		 
				// set dialog message
				alertDialogBuilder
					.setMessage("Co muon chuyen vao danh muc yeu thich ko ?")
					.setPositiveButton("Dong y",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// Luu vao DB
							DBAdapter db = new DBAdapter(getBaseContext());
                            db.open();
                            db.insertAccount(list.get(pos).getName(), list.get(pos).getPhone());
                            db.close(); 
                            Toast.makeText(getBaseContext(), "Da dc them vao Yeu Thich", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
						}
					})
					.setNegativeButton("Bo qua",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							dialog.cancel();
						}
					});
	 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();	 
				// show it
				alertDialog.show();
				return true;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.add) {
			return true;
		} else if (id == R.id.info) {
			return true;
		} else if (id == R.id.yeuthich) {
			
			return true;
		} else if (id == R.id.thoat) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

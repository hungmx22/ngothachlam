package com.example.lab11_p01_menuactionbardemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        if (id == R.id.action_settings) {
        	Toast.makeText(this, "Click Setting", Toast.LENGTH_SHORT).show(); 
            return true;
        } else if(id == R.id.menu1) {
        	Toast.makeText(this, "Click Menu 1", Toast.LENGTH_SHORT).show(); 
            return true;
        } else if(id == R.id.menu2) {
        	Toast.makeText(this, "Click Menu 2", Toast.LENGTH_SHORT).show(); 
            return true;
        } else if(id == R.id.menu3) {
        	Toast.makeText(this, "Click Menu 3", Toast.LENGTH_SHORT).show(); 
            return true;
        } else if(id == R.id.menu4) {
        	Toast.makeText(this, "Click Menu 4", Toast.LENGTH_SHORT).show(); 
            return true;
        } else if(id == R.id.menu5) {
        	Toast.makeText(this, "Click Menu 5", Toast.LENGTH_SHORT).show(); 
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

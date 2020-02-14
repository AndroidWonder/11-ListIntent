package com.course.example.listintent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.net.Uri;
import android.content.Intent;

public class ListIntentActivity extends Activity implements AdapterView.OnItemClickListener{

	private final String[] options = {"Explicit", "Maps", "Open Dialer", "Exit"};
	private ArrayAdapter<String> adapt = null;
	private ListView listview;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		listview = (ListView)findViewById(R.id.list);
		listview.setOnItemClickListener(this);    //connect listener

		//notice custom format for list items
		adapt = new ArrayAdapter<String> (this, R.layout.item, options);
		listview.setAdapter(adapt);
	}

	//start activity based on list item selected
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

		switch (position) {

			case 0 : {
				Intent intent = new Intent(this, LifeCycleTest.class);
				startActivity(intent);
				break;
			}

			case 1 : {
				Uri uri = Uri.parse("geo:0,0?q=175+forest+street+waltham+ma");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				//check to be sure Google Maps application is on device
				if (intent.resolveActivity(getPackageManager()) != null) {
					startActivity(intent);
				}
				break;
			}

			case 2 : {
				//notice no permission necessary because call not placed
				Uri uri = Uri.parse("tel:8005551212");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				break;
			}

			case 3 : finish();

		}
	}

}
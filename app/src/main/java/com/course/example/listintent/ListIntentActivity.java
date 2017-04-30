package com.course.example.listintent;

import android.app.Activity;
import android.app.ListActivity;
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
		listview.setOnItemClickListener(this);

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

				/*For API18 and 19 Google Maps is not on Launch Pad
             	 so should first check if Package is present to avoid app crashing.
             	 The API 23 emulator using the Intel instruction set would also crash here
             	 without first checking.
            	*/
				if (intent.resolveActivity(getPackageManager()) != null) {
					startActivity(intent);
				}
				break;
			}

			case 2 : {

				Uri uri = Uri.parse("tel:8005551212");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				break;
			}

			case 3 : finish();

		}
	}

}
package com.adelement.aecart;
import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity{

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		final Controller aController = (Controller) getApplicationContext();

		ModelProducts productObject = null; 
		int np=7;
		for(int i=1;i<=np;i++){
			int price = 100+i;
			productObject = new ModelProducts("Product "+i,"Description "+i,price);
			aController.setProducts(productObject);
		}
		ArrayList<String> productNames =aController.getProductNameList();
		//String[] values = new String[] { "Product 1", "Product 2", "Product 3", "Product 4"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, productNames);

		setListAdapter(adapter);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		//String item = (String) getListAdapter().getItem(position);
		//Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
		Intent it = new Intent(getBaseContext(), ProductScreen.class);
		Bundle b = new Bundle();
		b.putInt("key", position); //Your id
		it.putExtras(b); //Put your id to your next Intent
		startActivity(it);
	}
	@Override
	public void onBackPressed() {

	}
}

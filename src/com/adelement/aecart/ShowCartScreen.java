package com.adelement.aecart;

import java.util.HashMap;
import java.util.Map;

import com.adelement.aesdk.MyHttpAsyncTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShowCartScreen extends Activity{	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showcartscreen); 

		TextView showCartContent    = (TextView) findViewById(R.id.cart);
		final Button checkOutBtn 		= (Button) findViewById(R.id.checkout);

		final Controller aController = (Controller) getApplicationContext();

		final int cartSize = aController.getCart().getCartSize();

		String showString = "";
		Map map = new HashMap();
		if(cartSize >0){	
			int sum = 0;

			for(int i=0;i<cartSize;i++){

				String pName 	= aController.getCart().getProducts(i).getProductName();
				int pPrice   	= aController.getCart().getProducts(i).getProductPrice();
				String pDisc   	= aController.getCart().getProducts(i).getProductDesc();
				sum+=pPrice;


				showString += "\nProduct Name : "+pName+"\n"+
						"Price : Rs. "+pPrice+"\n"+
						"Discription : "+pDisc+""+
						"\n -----------------------------------";
				Map pmap = new HashMap();
				pmap.put("name",pName);
				pmap.put("price", pPrice);
				pmap.put("disc", pDisc);
				map.put(i, pmap);
			}
			showString += "\nTotal product :"+cartSize+" Total price : Rs. "+sum;

		}else
			showString = "\n\nShopping cart is empty.\n\n";

		showCartContent.setText(showString);

		checkOutBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getBaseContext(), PaymentScreen.class);
				startActivity(i);
			}
		});	
		TextView info =  (TextView) findViewById(R.id.info);
		info.setText(map.toString());
		
		MyHttpAsyncTask ht = new MyHttpAsyncTask(map);
		ht.execute();

	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}

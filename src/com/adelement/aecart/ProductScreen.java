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

public class ProductScreen extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.productscreen);

		Bundle b = getIntent().getExtras();
		final int key = b.getInt("key");

		final Controller aController = (Controller) getApplicationContext();
		ModelProducts productObject = aController.getProducts(key);
		String pName 	= productObject.getProductName();
		int pPrice   	= productObject.getProductPrice();
		String pDisc   	= productObject.getProductDesc();

		TextView showCartContent    = (TextView) findViewById(R.id.prodDesc);

		String showString = "";

		showString += "\nProduct Name : "+pName+"\n"+
				"Price : Rs. "+pPrice+"\n"+
				"Discription : "+pDisc+""+
				"\n -----------------------------------";
		showCartContent.setText(showString);

		final Button addtoCartBtn 		= (Button) findViewById(R.id.addtocart);
		final Button showCartBtn 		= (Button) findViewById(R.id.showcart);
		if(aController.getCart().checkProductInCart(productObject)){
			addtoCartBtn.setText("Added");
		}else{
			addtoCartBtn.setText("Add To Cart");
		}
		if(aController.getCart().getCartSize()>0){
			showCartBtn.setVisibility(View.VISIBLE);
		}else{
			showCartBtn.setVisibility(View.INVISIBLE);
		}
		addtoCartBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ModelProducts productObject = aController.getProducts(key);
				if(!aController.getCart().checkProductInCart(productObject)){
					addtoCartBtn.setText("Added");
					aController.getCart().setProducts(productObject);
					showCartBtn.setVisibility(View.VISIBLE);
					Toast.makeText(getApplicationContext(), "Now Cart size: "+aController.getCart().getCartSize(),Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(getApplicationContext(), "Product "+(key+1)+" already added in cart.",Toast.LENGTH_LONG).show();
				}	
			}
		});		
		showCartBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getBaseContext(), ShowCartScreen.class);
				startActivity(i);	
			}
		});	
		Map pmap = new HashMap();
		pmap.put("name",pName);
		pmap.put("price", pPrice);
		pmap.put("disc", pDisc);
		
		Map map = new HashMap();
		map.put(key, pmap);
		
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

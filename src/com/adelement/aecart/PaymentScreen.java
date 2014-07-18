package com.adelement.aecart;
import java.util.HashMap;
import java.util.Map;

import com.adelement.aesdk.MyHttpAsyncTask;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PaymentScreen extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paymentscreen); 

		TextView showCartContent    = (TextView) findViewById(R.id.payment);

		final Controller aController = (Controller) getApplicationContext();

		int cartSize = aController.getCart().getCartSize();
		Map map = new HashMap();
		String showString = "";
		int sum=0;
		for(int i=0;i<cartSize;i++){

			String pName     = aController.getCart().getProducts(i).getProductName();
			int pPrice       = aController.getCart().getProducts(i).getProductPrice();
			String pDisc       = aController.getCart().getProducts(i).getProductDesc();
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

		showString += "\n\nTotal price : Rs. "+sum+"\n";

		showCartContent.setText(showString);
		
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

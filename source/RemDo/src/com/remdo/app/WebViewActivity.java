package com.remdo.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EncodingUtils;
import org.apache.http.util.EntityUtils;

import com.remdo.app.R;
import com.remdo.app.R.id;
import com.remdo.app.R.layout;
import com.remdo.app.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {

	private WebView webView;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view);
		
	    // Get the uri from the intent
	    Intent intent = getIntent();
	    String uriToDisplay = intent.getStringExtra("DEVICE_URL");
	    String usr = intent.getStringExtra("DEVICE_USER");
	    String pwd = intent.getStringExtra("DEVICE_PASSWORD");
		
	    if (!uriToDisplay.contains("cgi-bin/od.cgi"))
	    {
	    	uriToDisplay = uriToDisplay.concat("/cgi-bin/od.cgi");
	    }
	    
	    //initialize WebView
		webView = (WebView) findViewById(R.id.webView1);
		//we should define a webview client to get user navigation inside our webview otherwise 
		//defaul behaviour will open a webbrowser on first click
		webView.setWebViewClient(new WebViewClient());
		
	    try {
	        
	        byte[] post = EncodingUtils.getBytes("USERNAME=" + usr + "&PASSWORD="+ pwd , "BASE64");
	        webView.postUrl(uriToDisplay, post);
	        
	    }catch (Exception e) {
	    	String message = e.getMessage();
	    }
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.web_view, menu);
		return true;
	}

}

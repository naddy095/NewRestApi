package com.example.newrestapi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends Activity {
	EditText password,userName;
	Button login,resister;
	ProgressBar progressBar;
	
	
	   
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		password=(EditText) findViewById(R.id.editText2);
		userName=(EditText) findViewById(R.id.editText1);
		login=(Button) findViewById(R.id.button1);
		resister=(Button) findViewById(R.id.button2);
		
		//progess_msz.setVisibility(View.GONE);
		progressBar=(ProgressBar) findViewById(R.id.progressBar1);
		progressBar.setVisibility(View.GONE);
		
		
		resister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent  intent=new Intent(MainActivity.this,ResisterUser.class);
				startActivity(intent);
			}
		});
		login.setOnClickListener(new OnClickListener() {
		
			public void onClick(View v) {
				progressBar.setVisibility(View.VISIBLE);
				
				String s1=userName.getText().toString();
				String s2=password.getText().toString();
				new ExecuteTask().execute(s1,s2);
				
			}
		});
		

	}
	
	 class ExecuteTask extends AsyncTask<String, Integer, String>
	    {

			@Override
			protected String doInBackground(String... params) {
				
				PostData(params);
				
				return "";
			}
			
			@Override
			protected void onPostExecute(String result) {
			progressBar.setVisibility(View.GONE);
			//progess_msz.setVisibility(View.GONE);
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
			}
	    	
	    }

    public void PostData(String[] valuse) {

        Gson gson = new Gson();
        User user = new User();
        user.setUsername(valuse[0]);
        user.setPassword(valuse[1]);
        gson.toJson(user);
        String s = "";
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.comparetokar.com/api/login");
            StringEntity input = new StringEntity(gson.toJson(user).toString());
            input.setContentType("application/json");
            httpPost.setEntity(input);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            s = readResponse(httpResponse);
            System.out.println(s);

        } catch (Exception exception) {
        }
    }

    public static String readResponse(HttpResponse res) {
        InputStream is = null;
        String return_text = "";
        try {
            is = res.getEntity().getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return_text = sb.toString();
        } catch (Exception e) {

        }
        return return_text;

    }
	
}
package com.iut_velizy.dao;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.iut_velizy.projettuteure.Profil;
import com.iut_velizy.projettuteure.R;

import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

/**
 * Appel à la base de données pour la vue <i>Profil</i>
 *
 */
public class LoginDAO extends AsyncTask<String, Void, String>
{
	private HttpClient client;
	private String jsonData;
	
	public LoginDAO()
	{
		this.client = new DefaultHttpClient();
	}
	
	@Override
    protected void onPreExecute()
	{
		//avant l'exécution
    }
	
	@Override
	protected String doInBackground(String... params)
	{
        String jsonData = new String();
        
        try {
            HttpResponse response = this.client.execute(new HttpGet(params[0].toString()));
            
            HttpEntity entity = response.getEntity();
            jsonData = EntityUtils.toString(entity);
            
        } catch (Exception e) { Log.e("RPC","Exception levée:", e); }
        
        return jsonData;
	}
	
	@Override
    protected void onPostExecute(String result)
	{
		//après l'exécution
		
    }
	
	public String getJsonData()
	{
		return jsonData;
	}
}

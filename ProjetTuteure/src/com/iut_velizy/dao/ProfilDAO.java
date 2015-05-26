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
 * Appel � la base de donn�es pour la vue <i>Profil</i>
 *
 */
public class ProfilDAO extends AsyncTask<Void, Void, String>
{
	private HttpClient client;
	private Profil profil;
	private String jsonData;
	
	public ProfilDAO(Profil profil)
	{
		this.profil = profil;
		this.client = new DefaultHttpClient();
	}
	
	@Override
    protected void onPreExecute()
	{
		//avant l'ex�cution
    }
	
	@Override
	protected String doInBackground(Void... params)
	{
        String jsonData = new String();
        
        try {
            HttpResponse response = this.client.execute(new HttpGet("http://192.168.1.12/getTest.php"));
            
            HttpEntity entity = response.getEntity();
            jsonData = EntityUtils.toString(entity);
            
        } catch (Exception e) { Log.e("RPC","Exception lev�e:", e); }
        
        return jsonData;
	}
	
	@Override
    protected void onPostExecute(String result)
	{
		//apr�s l'ex�cution
		//mise � jour des TextView en fonction des donn�es
        TextView tvLogin = (TextView)profil.getView().findViewById(R.id.editTextLogin);
    	tvLogin.setText(tvLogin.getText() + " : " + result);
    }
	
	public String getJsonData()
	{
		return jsonData;
	}
}

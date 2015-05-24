package com.iut_velizy.dao;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.iut_velizy.projettuteure.MainActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Classe appel�e � chaque fois que le main activity prend le focus.<br/>
 * L'appli n'a qu'une activit�e donc l'appel est fait au d�marrage.
 *
 */
public class Initialisation extends AsyncTask<Void, Void, String>
{
	private volatile MainActivity screen;
	private HttpClient client;
    private ProgressDialog progress;
	
	public Initialisation(MainActivity s)
	{
		this.screen = s;
		this.client = new DefaultHttpClient();
		this.progress = new ProgressDialog(this.screen);
	}
	
	@Override
    protected void onPreExecute() {                           
        progress.setTitle("Veuillez patienter");
        progress.setMessage("R�cup�ration des donn�es en cours...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);      
        progress.show();
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
    protected void onPostExecute(String result) {
            
        if(progress.isShowing()) progress.dismiss();    
        this.screen.populate(result);
    }
	
}

package com.iut_velizy.dao;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.iut_velizy.localStorage.LocalSettings;
import com.iut_velizy.projettuteure.LoginActivity;
import com.iut_velizy.projettuteure.Profil;
import com.iut_velizy.projettuteure.R;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Appel à la base de données pour la vue <i>LoginActivity</i>
 *
 */
public class LoginDAO extends AsyncTask<String, Void, String>
{
	private HttpClient client;
	private String jsonData;
	private FragmentManager fm;
	
	public LoginDAO(FragmentManager fm)
	{
		this.fm = fm;
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
        	//execution de la requête
            HttpResponse response = this.client.execute(new HttpGet(params[0].toString()));
            
            //récupération du code retour
            HttpEntity entity = response.getEntity();
            jsonData = EntityUtils.toString(entity);
            
            //construction d'un JSONObject
            JSONObject obj = new JSONObject(jsonData);
            
            //test si le login est correct
            if (obj.getInt("etat")==1)
            {
            	//login et mot de passe OK
            	LocalSettings.dejaLoger=true;
            }
            else
            {
            	//erreur
            	LocalSettings.dejaLoger=false;
            }
            
        } catch (Exception e) { Log.e("RPC","Exception levée:", e); }
        
        return jsonData;
	}
	
	@Override
    protected void onPostExecute(String result)
	{
		//après l'exécution
		if (LocalSettings.dejaLoger)
		{
			//le login est passé, on dismiss la page de login
			Fragment prev = fm.findFragmentByTag("login dialog");
			if (prev != null) {
                DialogFragment df = (DialogFragment) prev;
                df.dismiss();
            }
		}
		else
		{
			//les identifiants sont incorrectes, affichage d'un message d'erreur
			Fragment prev = fm.findFragmentByTag("login dialog");
			if (prev != null) {
                DialogFragment df = (DialogFragment) prev;
                Toast.makeText(df.getView().getContext(), "Impossible de se connécter", Toast.LENGTH_SHORT).show();
            }
		}
    }
	
	public String getJsonData()
	{
		return jsonData;
	}
}

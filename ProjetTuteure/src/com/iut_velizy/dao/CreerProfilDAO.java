package com.iut_velizy.dao;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.iut_velizy.localStorage.LocalSettings;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class CreerProfilDAO extends AsyncTask<String, Void, String>
{
	private HttpClient client;
	private FragmentManager fm;
	private boolean profilCreer = false;

	public CreerProfilDAO(FragmentManager fragmentManager)
	{
		this.fm = fragmentManager;
		this.client = new DefaultHttpClient();
	}

	@Override
	protected String doInBackground(String... params)
	{
        String data = "";
        try {
        	//execution de la requ�te
            HttpResponse response = this.client.execute(new HttpGet(params[0].toString()));
            
            //r�cup�ration du code retour
            HttpEntity entity = response.getEntity();
            data = EntityUtils.toString(entity);
            
            int resultExec = Integer.parseInt(data);
            
            //test si le profil est bien cr�er
            if (resultExec==0)
            {
            	profilCreer = true;
            	LocalSettings.profilCreer = true;
            }
            else
            {
            	//erreur
            	profilCreer = false;
            }
            
        } catch (Exception e) { Log.e("RPC","Exception lev�e:", e); }
        
        return data;
	}
	
	@Override
    protected void onPostExecute(String result)
	{
		//apr�s l'ex�cution
		if (profilCreer)
		{
			//le profil est cr�er, on dismiss la page de creation de profil
			//et la dialog de login
			LocalSettings.dejaLoger = true;
			
			Fragment prev = fm.findFragmentByTag("creerProfil dialog");
			if (prev != null) {
                DialogFragment df = (DialogFragment) prev;
                df.dismiss();
            }
			
			Fragment prev2 = fm.findFragmentByTag("login dialog");
			if (prev2 != null) {
                DialogFragment df = (DialogFragment) prev2;
                df.dismiss();
            }
		}
		else
		{
			//les valeurs sont incorrectes, affichage d'un message d'erreur
			Fragment prev = fm.findFragmentByTag("creerProfil dialog");
			if (prev != null) {
                DialogFragment df = (DialogFragment) prev;
                Toast.makeText(df.getView().getContext(), "erreur creation de profil", Toast.LENGTH_SHORT).show();
            }
		}
    }
}

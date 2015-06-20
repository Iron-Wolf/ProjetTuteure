package com.iut_velizy.dao;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.iut_velizy.localStorage.LocalSettings;
import com.iut_velizy.projettuteure.Profil;
import com.iut_velizy.projettuteure.R;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilUpdateDAO extends AsyncTask<String, Void, String>
{
	private HttpClient client;
	private String data;
	private FragmentManager fm;
	
	public ProfilUpdateDAO(FragmentManager fm)
	{
		this.fm = fm;
		this.client = new DefaultHttpClient();
	}
	
	@Override
	protected String doInBackground(String... params)
	{
        
        
        try {
        	//execution de la requête
            HttpResponse response = this.client.execute(new HttpGet(params[0].toString()));
            
            //récupération du code retour
            HttpEntity entity = response.getEntity();
            data = EntityUtils.toString(entity);
            
            //construction d'un JSONObject
            //JSONObject obj = new JSONObject(jsonData);
            
            //test si l'insertion est OK
            if (data.equals("0"))
            {
            	//update en base
            	LocalSettings.profilUpdate = true;
            	ProfilDAO.login=null;
            	ProfilDAO.password=null;
            	ProfilDAO.prenom=null;
            	ProfilDAO.nom=null;
            	ProfilDAO.mail=null;
            	ProfilDAO.qst_secrete=null;
            	ProfilDAO.rsp_secrete=null;
            	ProfilDAO.voie=null;
            	ProfilDAO.ville=null;
            	ProfilDAO.pays=null;
            	
            }
            else
            {
            	//erreur
            	LocalSettings.profilUpdate = false;
            }
            
        } catch (Exception e) {}
        
        return data;
	}
	
	@Override
    protected void onPostExecute(String result)
	{
		//après l'exécution
		if (LocalSettings.profilUpdate)
		{
			//l'update est passé, on dismiss la page de modification du profil
			Fragment prev = fm.findFragmentByTag("changeProfil dialog");
			if (prev != null) {
                DialogFragment df = (DialogFragment) prev;
                df.dismiss();
            }
		}
		else
		{
			//les identifiants sont incorrectes, affichage d'un message d'erreur
			Fragment prev = fm.findFragmentByTag("changeProfil dialog");
			if (prev != null) {
                DialogFragment df = (DialogFragment) prev;
                Toast.makeText(df.getView().getContext(), "Impossible de mettre à jour vos données", Toast.LENGTH_SHORT).show();
            }
		}
    }
}

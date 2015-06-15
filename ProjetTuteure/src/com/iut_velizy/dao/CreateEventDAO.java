package com.iut_velizy.dao;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.iut_velizy.localStorage.CreateEventStatic;
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
public class CreateEventDAO extends AsyncTask<Void, Void, String>
{
	private HttpClient client;
	private String jsonData;
	
	public CreateEventDAO()
	{
		this.client = new DefaultHttpClient();
	}
	
	@Override
    protected void onPreExecute()
	{
		//avant l'exécution
    }
	
	@Override
	protected String doInBackground(Void... params)
	{
        String jsonData = new String();
        
        try {
        	String url = "http://bountiful.minecraftnoob.com/createEvent.php";
        	url+="?e_organisateur_id=" + CreateEventStatic.imei;
        	url+="&e_nom=" + CreateEventStatic.nomEvent;
        	url+="&e_type=" + CreateEventStatic.centreInteret;
        	url+="&e_date=" + formatDate(CreateEventStatic.dateEvent,CreateEventStatic.heureEvent);
        	url+="&e_fin_creation=" + formatDate(CreateEventStatic.dateCalcul,CreateEventStatic.heureCalcul);
        	url+="&e_description=" + CreateEventStatic.description;
        	url+="&a1_voie=" + CreateEventStatic.adresse1_voie + "&a1_ville=" + CreateEventStatic.adresse1_ville + "&a1_pays=" + CreateEventStatic.adresse1_pays;
        	url+="&a2_voie=" + CreateEventStatic.adresse2_voie + "&a2_ville=" + CreateEventStatic.adresse2_ville + "&a2_pays=" + CreateEventStatic.adresse2_pays;
        	url+="&a3_voie=" + CreateEventStatic.adresse3_voie + "&a3_ville=" + CreateEventStatic.adresse3_ville + "&a3_pays=" + CreateEventStatic.adresse3_pays;
        	url+="&a4_voie=" + CreateEventStatic.adresse4_voie + "&a4_ville=" + CreateEventStatic.adresse4_ville + "&a4_pays=" + CreateEventStatic.adresse4_pays;
        	url+="&a4_voie=" + CreateEventStatic.adresse5_voie + "&a5_ville=" + CreateEventStatic.adresse5_ville + "&a5_pays=" + CreateEventStatic.adresse5_pays;
        	url+="&a6_voie=" + CreateEventStatic.adresse6_voie + "&a6_ville=" + CreateEventStatic.adresse6_ville + "&a6_pays=" + CreateEventStatic.adresse6_pays;
        	
            HttpResponse response = this.client.execute(new HttpGet(url));
            
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
	
	private String formatDate(String date, String heure)
	{
		String retour="";
		
		String[] split = date.split("/");
		retour = split[2] + "-" + split[1] + "-" + split[0];
		retour += " " + heure + ":00";
		
		return retour;
	}
	
	public String getJsonData()
	{
		return jsonData;
	}
}
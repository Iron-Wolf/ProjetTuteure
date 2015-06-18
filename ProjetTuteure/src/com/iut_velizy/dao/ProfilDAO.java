package com.iut_velizy.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.iut_velizy.localStorage.ComingEventStatic;
import com.iut_velizy.localStorage.CreateEventStatic;
import com.iut_velizy.localStorage.LocalSettings;
import com.iut_velizy.projettuteure.Profil;
import com.iut_velizy.projettuteure.R;

import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
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
	
	//champs affich�es dans l'application
	private static String login;
	private static String prenom;
	private static String nom;
	private static String mail;
	private static String voie;
	private static String ville;
	private static String pays;
	
	
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
        
        //on garde les donn�es en cache, donc pas besoin de faire de multiples appels � la base
        if (login==null)
        {
	        try {
	        	//num�ro IMEI
	        	TelephonyManager telephonyManager = (TelephonyManager)profil.getView().getContext().getSystemService(profil.getView().getContext().TELEPHONY_SERVICE);
	        	String imei = telephonyManager.getDeviceId();
	        	
	        	//execution de la requ�te
	            HttpResponse response = this.client.execute(new HttpGet("http://"+LocalSettings.url+"/getProfil.php?id=" + imei));
	            
	            //r�cup�ration des donn�es
	            HttpEntity entity = response.getEntity();
	            jsonData = EntityUtils.toString(entity);
	            
	            //construction d'une array JSON
	            JSONArray array = new JSONArray(jsonData);
	            
	            //�l�ments du profil
	            JSONObject element = array.getJSONObject(0);
	            login = element.getString("profil_login");
	            prenom = element.getString("profil_prenom");
	            nom = element.getString("profil_nom");
	            mail = element.getString("profil_email");
	
	            //�l�ments de l'adresse
	            element = array.getJSONObject(1);
	            voie = element.getString("adresse_voie");
	            ville = element.getString("adresse_ville");
	            pays = element.getString("adresse_pays");
	            
	        } catch (Exception e) { Log.e("RPC","Exception lev�e:", e); }
        }
        return jsonData;
	}
	
	@Override
    protected void onPostExecute(String result)
	{
		//apr�s l'ex�cution
		
		//on affiche les champs lors du premier appel � la base
		if (login!=null)
		{
			//mise � jour des TextView en fonction des donn�es
	        TextView tv = (TextView)profil.getView().findViewById(R.id.editTextLogin);
	    	tv.setText(tv.getText() + " : " + login);
	    	
	    	TextView tv2 = (TextView)profil.getView().findViewById(R.id.editTextFirstName);
	    	tv2.setText(tv2.getText() + " : " + prenom);
	    	
	    	TextView tv3 = (TextView)profil.getView().findViewById(R.id.editTextSecondName);
	    	tv3.setText(tv3.getText() + " : " + nom);
	    	
	    	TextView tv4 = (TextView)profil.getView().findViewById(R.id.editTextMail);
	    	tv4.setText(tv4.getText() + " : " + mail);
	    	
	    	TextView tv5 = (TextView)profil.getView().findViewById(R.id.editTextStreet);
	    	tv5.setText(tv5.getText() + " : " + voie);
	    	
	    	TextView tv6 = (TextView)profil.getView().findViewById(R.id.editTextVille);
	    	tv6.setText(tv6.getText() + " : " + ville);
	    	
	    	TextView tv7 = (TextView)profil.getView().findViewById(R.id.editTextPays);
	    	tv7.setText(tv7.getText() + " : " + pays);
		}
    }
	
	public String getJsonData()
	{
		return jsonData;
	}
}

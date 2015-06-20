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
	public static String login;
	public static String password;
	public static String prenom;
	public static String nom;
	public static String mail;
	public static String qst_secrete;
	public static String rsp_secrete;
	public static String voie;
	public static String ville;
	public static String pays;
	
	
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
	            password = element.getString("profil_password");
	            prenom = element.getString("profil_prenom");
	            nom = element.getString("profil_nom");
	            mail = element.getString("profil_email");
	            qst_secrete = element.getString("profil_question_secrete");
	            rsp_secrete = element.getString("profil_reponse_secrete");
	
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
	        tv.setText("Login : "); tv.append(login);
	    	
	    	TextView tv2 = (TextView)profil.getView().findViewById(R.id.editTextFirstName);
	    	tv2.setText("Pr�nom : "); tv2.append(prenom);
	    	
	    	TextView tv3 = (TextView)profil.getView().findViewById(R.id.editTextSecondName);
	    	tv3.setText("Nom : "); tv3.append(nom);
	    	
	    	TextView tv4 = (TextView)profil.getView().findViewById(R.id.editTextMail);
	    	tv4.setText("Mail : "); tv4.append(mail);
	    	
	    	TextView tv5 = (TextView)profil.getView().findViewById(R.id.editTextStreet);
	    	tv5.setText("Voie : "); tv5.append(mail);
	    	
	    	TextView tv6 = (TextView)profil.getView().findViewById(R.id.editTextVille);
	    	tv6.setText("Ville : "); tv6.append(ville);
	    	
	    	TextView tv7 = (TextView)profil.getView().findViewById(R.id.editTextPays);
	    	tv7.setText("Pays : "); tv7.append(pays);
		}
    }
	
	public String getJsonData()
	{
		return jsonData;
	}
}

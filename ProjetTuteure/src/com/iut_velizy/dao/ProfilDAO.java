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
 * Appel à la base de données pour la vue <i>Profil</i>
 *
 */
public class ProfilDAO extends AsyncTask<Void, Void, String>
{
	private HttpClient client;
	private Profil profil;
	private String jsonData;
	
	//champs affichées dans l'application
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
		//avant l'exécution
    }
	
	@Override
	protected String doInBackground(Void... params)
	{
        String jsonData = new String();
        
        //on garde les données en cache, donc pas besoin de faire de multiples appels à la base
        if (login==null)
        {
	        try {
	        	//numéro IMEI
	        	TelephonyManager telephonyManager = (TelephonyManager)profil.getView().getContext().getSystemService(profil.getView().getContext().TELEPHONY_SERVICE);
	        	String imei = telephonyManager.getDeviceId();
	        	
	        	//execution de la requête
	            HttpResponse response = this.client.execute(new HttpGet("http://"+LocalSettings.url+"/getProfil.php?id=" + imei));
	            
	            //récupération des données
	            HttpEntity entity = response.getEntity();
	            jsonData = EntityUtils.toString(entity);
	            
	            //construction d'une array JSON
	            JSONArray array = new JSONArray(jsonData);
	            
	            //éléments du profil
	            JSONObject element = array.getJSONObject(0);
	            login = element.getString("profil_login");
	            password = element.getString("profil_password");
	            prenom = element.getString("profil_prenom");
	            nom = element.getString("profil_nom");
	            mail = element.getString("profil_email");
	            qst_secrete = element.getString("profil_question_secrete");
	            rsp_secrete = element.getString("profil_reponse_secrete");
	
	            //éléments de l'adresse
	            element = array.getJSONObject(1);
	            voie = element.getString("adresse_voie");
	            ville = element.getString("adresse_ville");
	            pays = element.getString("adresse_pays");
	            
	        } catch (Exception e) { Log.e("RPC","Exception levée:", e); }
        }
        return jsonData;
	}
	
	@Override
    protected void onPostExecute(String result)
	{
		//après l'exécution
		
		//on affiche les champs lors du premier appel à la base
		if (login!=null)
		{
			//mise à jour des TextView en fonction des données
	        TextView tv = (TextView)profil.getView().findViewById(R.id.editTextLogin);
	        tv.setText("Login : "); tv.append(login);
	    	
	    	TextView tv2 = (TextView)profil.getView().findViewById(R.id.editTextFirstName);
	    	tv2.setText("Prénom : "); tv2.append(prenom);
	    	
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

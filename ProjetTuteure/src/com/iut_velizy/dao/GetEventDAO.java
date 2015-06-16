package com.iut_velizy.dao;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import com.iut_velizy.localStorage.ComingEventStatic;
import com.iut_velizy.projettuteure.ComingEvent;

/**
 * R�cup�re les identifiants d'�v�nements correspondant
 * au num�ro IMEI du t�l�phone
 *
 */
public class GetEventDAO extends AsyncTask<Void, Void, String>
{
	private HttpClient client;
	
	public GetEventDAO()
	{
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
        	String url = "http://192.168.1.12/getEvent.php?id="+ComingEventStatic.imei;
        	
        	//execution de la requ�te
            HttpResponse response = this.client.execute(new HttpGet(url));
            
            //r�cup�ration dans un String
            HttpEntity entity = response.getEntity();
            jsonData = EntityUtils.toString(entity);
            
            //construction d'une JSONArray et enregistrement des evenement_id
            JSONArray array = new JSONArray(jsonData);
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject element = array.getJSONObject(i);
                ComingEventStatic.listEventID.add(element.getInt("evenement_id"));
                ComingEventStatic.listNomEvent.add(element.getString("evenement_nom"));
            }
            
        } catch (Exception e) { Log.e("RPC","Exception lev�e:", e); }
        
        return jsonData;
	}
	
	@Override
    protected void onPostExecute(String result)
	{
		//apr�s l'ex�cution
    }
	
}

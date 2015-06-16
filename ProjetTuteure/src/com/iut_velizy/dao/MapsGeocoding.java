package com.iut_velizy.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.iut_velizy.projettuteure.Maps;

/**
 * R�alise l'appel � l'API de Geocoding de google, qui permet
 * de r�cup�rer les coordonn�es d'une adresse
 *
 */
public class MapsGeocoding extends AsyncTask<String, Void, ArrayList<String>>
{
	private Maps refMap;
	
	public MapsGeocoding(Maps map)
	{
		this.refMap = map;
	}
	
	
    @Override
    protected ArrayList<String> doInBackground(String... args)
    {

        ArrayList<String> dataArray = new ArrayList<String>();

        try {
            URL geocodingData = new URL(
                    "https://maps.googleapis.com/maps/api/geocode/json?address="
                            + URLEncoder.encode(args[0].toString(), "UTF-8")
                            + "&key=AIzaSyAFnxO_VllQgDBF6ZCF24jcSkF4U5TE4RY"); 
            URLConnection tc = geocodingData.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    tc.getInputStream()));

            String line;
            StringBuffer sb = new StringBuffer();
            // on transforme le JSON de Google en un simple String
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            
            
            // construction de l'objet JSON
            JSONObject obj = new JSONObject(sb.toString());
            if (! obj.getString("status").equals("OK"))
                return null;
         
            // on r�cup�re le premier noeud, puis on descend jusqu'aux coordonn�es
            JSONObject res = obj.getJSONArray("results").getJSONObject(0);
            
            JSONObject loc =
                res.getJSONObject("geometry").getJSONObject("location");
            
            dataArray.add(loc.getDouble("lat")+"");
            dataArray.add(loc.getDouble("lng")+"");
        }
        catch (IOException e) {}
        catch (JSONException e) {}
        
        return dataArray;

    }

    @Override
    protected void onPostExecute(ArrayList<String> result)
    {
        for (String string : result)
        {
        	//ajout de la longitute et la latitude � l'arrayList
        	refMap.arrayLoc.add(string);
        }
        
        refMap.update(); //on pr�vient la vue
    }
}
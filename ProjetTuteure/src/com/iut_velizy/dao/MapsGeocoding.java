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
 * Réalise l'appel à l'API de Geocoding de google, qui permet
 * de récupérer les coordonnées d'une adresse
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
            // take Google's legible JSON and turn it into one big
            // string.
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            
            
            // build a JSON object
            JSONObject obj = new JSONObject(sb.toString());
            if (! obj.getString("status").equals("OK"))
                return null;
         
            // get the first result
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
        	//ajout de la longitute et la latitude à l'arrayList
        	refMap.arrayLoc.add(string);
        }
        
        refMap.update(); //on prévient la vue
    }
}
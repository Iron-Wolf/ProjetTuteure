package com.iut_velizy.dao;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.iut_velizy.localStorage.ComingEventStatic;
import com.iut_velizy.localStorage.LocalSettings;
import com.iut_velizy.projettuteure.History;
import com.iut_velizy.projettuteure.R;

import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class HistoryDAO extends AsyncTask<Void, Void, String>
{
	private HttpClient client;
	private History history;
	
	public HistoryDAO (History h)
	{
		this.history = h;
		this.client = new DefaultHttpClient();
	}

	@Override
	protected String doInBackground(Void... params)
	{
		String jsonData = new String();
		
		try {
			//numéro IMEI
	    	TelephonyManager telephonyManager = (TelephonyManager)history.getView().getContext().getSystemService(history.getView().getContext().TELEPHONY_SERVICE);
	    	String imei = telephonyManager.getDeviceId();
	    	
        	//connection
        	String url = "http://"+LocalSettings.url+"/getPastEvent.php?id="+imei;
            HttpResponse response = this.client.execute(new HttpGet(url));
            
            //récupération du JSON
            HttpEntity entity = response.getEntity();
            jsonData = EntityUtils.toString(entity);
            
            //construction d'une JSONArray et enregistrement des adresses
            JSONArray array = new JSONArray(jsonData);
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject element = array.getJSONObject(i);
                
                ArrayList<String> listAdr = new ArrayList<String>();
                listAdr.add(element.getString("evenement_nom"));
                listAdr.add(element.getString("evenement_type"));
                listAdr.add(element.getString("evenement_date").substring(0, 10));
                
                ComingEventStatic.listHistoryEvent.add(listAdr);
            }
            
            
            
        } catch (Exception e) { Log.e("RPC","Exception levée:", e); }
		
		return jsonData;
	}
	
	
	@Override
    protected void onPostExecute(String result)
	{
		//mise à jour de données
		
		TableLayout tl = (TableLayout) history.getView().findViewById(R.id.tableLayout1);
		
		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,	LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(2,10, 2, 2);
		
		for(int i = 0; i < ComingEventStatic.listHistoryEvent.size(); i++)
		{
			//récupération du tableau de tableau
			ArrayList<String> array = ComingEventStatic.listHistoryEvent.get(i);
			
			TableRow tr = new TableRow(history.getView().getContext());
			tr.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			
			//nom et type de l'evenement
			LayoutParams event = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			event.setMargins(20, 45, 2, 2);
			
			TextView tv = new TextView(history.getView().getContext());
    		tv.setText("    " + array.get(0) + " le " + formatDate(array.get(2)) + " (" + array.get(1) + ")");
    		tr.addView(tv, event);
			
    		//date de l'evenement
			/*LayoutParams add = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			add.setMargins(20, 45, 2, 2);
			
			TextView tv2 = new TextView(history.getView().getContext());
    		tv2.setText(formatDate(array.get(2)));
    		tr.addView(tv2, add);
    		*/
			    		
    		tl.addView(tr, layoutParams);
			
		}
	}
	
	
	/**
	 * change le format de la date pour l'affichage<br/>
	 * exemple :  2015-12-01  -->  01/12/2015
	 */
	private String formatDate(String date)
	{
		String retour="";
		
		String[] split = date.split("-");
		retour = split[2] + "/" + split[1] + "/" + split[0];
		
		return retour;
	}
	
}

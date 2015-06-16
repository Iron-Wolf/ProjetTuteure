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

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

import com.iut_velizy.localStorage.ComingEventStatic;
import com.iut_velizy.projettuteure.ComingEvent;
import com.iut_velizy.projettuteure.History;
import com.iut_velizy.projettuteure.Maps;
import com.iut_velizy.projettuteure.R;

/**
 * Obligatoirement appelé après GetEventDAO<br/>
 * Permet d'afficher les evenements à venir
 *
 */
public class FindEventDAO extends AsyncTask<Void, Void, String>
{
	private HttpClient client;
	private ComingEvent ce;
	
	public FindEventDAO(ComingEvent ce)
	{
		this.ce = ce;
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
        
        //pour chaque identifiants d'evenements, on récupère les adresses
        for (Integer id : ComingEventStatic.listEventID)
        {
	        try {
	        	//connection
	        	String url = "http://bountiful.minecraftnoob.com/findEvent.php?evenement_id="+id;
	            HttpResponse response = this.client.execute(new HttpGet(url));
	            
	            //récupération du JSON
	            HttpEntity entity = response.getEntity();
	            jsonData = EntityUtils.toString(entity);
	            
	            //construction d'une JSONArray et enregistrement des adresses
	            JSONArray array = new JSONArray(jsonData);
	            ArrayList<String> listAdr = new ArrayList<String>();
	            for (int i = 0; i < array.length(); i++)
	            {
	                JSONObject element = array.getJSONObject(i);
	                String adresse = element.getString("adresse_voie")
	                				+element.getString("adresse_ville")
	                				+element.getString("adresse_pays");
	                listAdr.add(adresse);
	            }
	            
	            ComingEventStatic.listAdresses.add(listAdr);
	            
	        } catch (Exception e) { Log.e("RPC","Exception levée:", e); }
        }
        
        return jsonData;
	}
	
	@Override
    protected void onPostExecute(String result)
	{
		//après l'exécution
		//mise à jour de la vue, en fonction des données
		
		TableLayout tl = (TableLayout) ce.getView().findViewById(R.id.tableLayout1);
		
		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,	LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(2,10, 2, 2);
		
        for (int i=0; i<ComingEventStatic.listEventID.size(); i++)
        {
        	TableRow tr = new TableRow(ce.getView().getContext());
			tr.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
						
			LayoutParams maps = new LayoutParams(LayoutParams.WRAP_CONTENT,	LayoutParams.WRAP_CONTENT);
    	 	ImageButton ib = new ImageButton(ce.getView().getContext());
    	 	ib.setId(i);//identifiant permettant de référencer la position dans l'arraylist des adresses
    		ib.setImageResource(R.drawable.google_maps_icon);
    		ib.setBackground(null);
    		tr.addView(ib,maps);
    		
    		//attribution d'un Listener permetant d'afficher la map
        	ib.setOnClickListener(new View.OnClickListener()
        	{
        		@Override
        		public void onClick(View v) {
        			//récupération des adresses
        			ArrayList adresse = ComingEventStatic.listAdresses.get(v.getId());
        			
        			//on remplit l'arraylist jusqu'à 6 String
        			int compteur = 6-adresse.size();
        			for (int i=0; i<compteur; i++)
        				adresse.add("");
        			
        			//affichage de la map
                	Maps newF2 = new Maps(adresse.get(0).toString(),
                				adresse.get(1).toString(),
                				adresse.get(2).toString(),
                				adresse.get(3).toString(),
                				adresse.get(4).toString(),
                				adresse.get(5).toString());
        			newF2.show(ce.getFragmentManager(), "map");
                }
            });
			
			LayoutParams event = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			event.setMargins(20, 45, 2, 2);
			
			TextView tv = new TextView(ce.getView().getContext());
    		tv.setText("Evenement : " + ComingEventStatic.listNomEvent.get(i)+"                                 ");
    		tr.addView(tv, event);
    		
			    		
    		tl.addView(tr, layoutParams);
        }
    }
	
}

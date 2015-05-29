package com.iut_velizy.projettuteure;

import com.iut_velizy.dao.Initialisation;
import com.iut_velizy.localStorage.LocalSettings;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ActionBar.Tab;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity 
{
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	if (savedInstanceState != null)
		{
			savedInstanceState.remove ("android:support:fragments");
		}
		
		super.onCreate(savedInstanceState);
        
        ActionBar actionBar = getActionBar();
        getActionBar().setDisplayShowHomeEnabled(false);  // hides action bar icon
        getActionBar().setDisplayShowTitleEnabled(false); // hides action bar title
 
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
 
        // Onglet 1 : Créer un évènement //
        String labelCreateEvent = getResources().getString(R.string.labelCreateEvent);
        Tab tab = actionBar.newTab();
        tab.setText(labelCreateEvent);
        TabListener<CreateEvent> tl = new TabListener<CreateEvent>(this, labelCreateEvent, CreateEvent.class);
        tab.setTabListener(tl);
        actionBar.addTab(tab);
        
        // Onglet 2 : Evènements à venir //
        String labelComingEvent = getResources().getString(R.string.labelComingEvent);
        tab = actionBar.newTab();
        tab.setText(labelComingEvent);
        TabListener<ComingEvent> tl2 = new TabListener<ComingEvent>(this, labelComingEvent, ComingEvent.class);
        tab.setTabListener(tl2);
        actionBar.addTab(tab);
                
        // Onglet 3 : Amis proches //
        String labelNearFriends = getResources().getString(R.string.labelNearFriends);
        tab = actionBar.newTab();
        tab.setText(labelNearFriends);
        TabListener<NearFriends> tl4 = new TabListener<NearFriends>(this, labelNearFriends, NearFriends.class);
        tab.setTabListener(tl4);
        actionBar.addTab(tab);
        
        // Onglet 4 : Amis //
        String labelFriends = getResources().getString(R.string.labelFriends);
        tab = actionBar.newTab();
        tab.setText(labelFriends);
        TabListener<Friends> tl5 = new TabListener<Friends>(this, labelFriends, Friends.class);
        tab.setTabListener(tl5);
        actionBar.addTab(tab);
        
        // Onglet 5 : Profil //
        String labelProfil = getResources().getString(R.string.labelProfil);
        tab = actionBar.newTab();
        tab.setText(labelProfil);
        TabListener<Profil> tl6 = new TabListener<Profil>(this, labelProfil, Profil.class);
        tab.setTabListener(tl6);
        actionBar.addTab(tab);
        
        // Onglet 5 : A propos //
        String labelAPropos = getResources().getString(R.string.labelAPropos);
        tab = actionBar.newTab();
        tab.setText(labelAPropos);
        TabListener<APropos> tl7 = new TabListener<APropos>(this, labelAPropos, APropos.class);
        tab.setTabListener(tl6);
        actionBar.addTab(tab);
        
    }
    
    @Override
    public void onStart()
    {
    	super.onStart();
    	
    	//TEST : sauvegarde / récupération de données dans fichier local
    	LocalSettings ls = new LocalSettings();
    	//ls.WriteSettings(this,"setting0, setting1, setting2");
    	//String data[] = ls.ReadSettings(this).split(",");
    	
    	
    	
    	//test si le smartphone est connecté à internet
    	ConnectivityManager connMgr = (ConnectivityManager) 
    	        getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
    	if (networkInfo != null && networkInfo.isConnected())
    	{
    		// récupere les données
    		new Initialisation(this).execute();
    	} 
    	else
    	{
    		// affiche une erreur
    		AlertDialog alertDialog = new AlertDialog.Builder(
                    MainActivity.this).create();
    		
    		alertDialog.setTitle("Problème réseau");
    		alertDialog.setMessage("L'application a besoin d'une connection Internet pour fonctionner");
    		//alertDialog.setIcon(R.drawable.tick);
    		
    		Message msg = null;
    		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"OK", msg);
    		
            alertDialog.show();
    	}
    	
    }
    
    public void populate(String data)
    {
    	// mettre à jour les vues
    }
    
    
 

    
    
    
}
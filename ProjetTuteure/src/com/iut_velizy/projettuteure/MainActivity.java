package com.iut_velizy.projettuteure;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.iut_velizy.dao.Initialisation;
import com.iut_velizy.localStorage.LocalSettings;

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
    }
    
    @Override
    public void onStart()
    {
    	super.onStart();
    	
    	LocalSettings ls = new LocalSettings();
    	
    	
    	//on parcour tous les fichiers
    	boolean fichierExist = false;
    	String[] files = fileList();
	    for (String file : files)
	    {
	        if (file.equals(ls.nomFichier))
	        {
	            //le fichier existe
	        	fichierExist = true;
	        }
	    }
	    
	    //création du fichier s'il n'existe pas
	    if (!fichierExist)
	    {
	    	ls.WriteSettings(this, "");
	    }
    	
    	
    	//test si le smartphone est connecté à internet
    	ConnectivityManager connMgr = (ConnectivityManager) 
    	        getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
    	if (networkInfo != null && networkInfo.isConnected())
    	{
    		//on test si l'utilisateur a déjà rentrer ses identifiants
    		//s'il n'est pas loger et si la fenêtre n'est pas déjà affichée, on l'affiche
    		//permet de gérer la rotation de l'écrant
    		Fragment prev = getFragmentManager().findFragmentByTag("login dialog");
    		if (!LocalSettings.dejaLoger && prev==null)
    		{
    			LoginActivity login = new LoginActivity(this);
    			login.show(getFragmentManager(),"login dialog");
    		}
    	} 
    	else
    	{
    		LoginActivity login = new LoginActivity(this);
			login.show(getFragmentManager(),"login dialog");
			Toast.makeText(this, "L'application a besoin d'une connexion Internet", Toast.LENGTH_SHORT).show();
    	}
    	
    }
    
    
    /**
     * méthode appelée à chaque dismiss de la dialog de Login
     */
    public void updateLogin()
    {
    	//on réaffiche la page de login si l'utilisateur n'est pas logé
    	if (!LocalSettings.dejaLoger)
    	{
    		LoginActivity login = new LoginActivity(this);
    		login.show(getFragmentManager(),"login dialog");
    	}
    	
    	//lors de la création du profil, on passe une variable static à true
    	//si elle est true, on affiche un Toast
    	if (LocalSettings.profilCreer)
    		Toast.makeText(this, "Création du compte réussie !", Toast.LENGTH_SHORT).show();
    }
    
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
        switch (item.getItemId()) 
        {
        case R.id.menu_apropos:
            // Comportement du bouton "A Propos"
        	APropos newF = new APropos();
			newF.show(getFragmentManager(), "dialog");
            return true;
        case R.id.menu_maptest:
            // Comportement du bouton "maps"
        	ConfMapsRapide newF2 = new ConfMapsRapide();
			newF2.show(getFragmentManager(), "dialog");
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
}
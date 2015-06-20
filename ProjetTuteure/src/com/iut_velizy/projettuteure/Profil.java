package com.iut_velizy.projettuteure;

import com.iut_velizy.dao.ProfilDAO;
import com.iut_velizy.localStorage.LocalSettings;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Profil extends Fragment
{
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profil, container, false);
    	
    	//on récupère le bouton et on lui attribut un Listener
    	Button buttonModifier = (Button) view.findViewById(R.id.buttonChange);
    	buttonModifier.setOnClickListener(new View.OnClickListener()
    	{
    		@Override
    		public void onClick(View v) {
    			//affiche la fenêtre de modification du profil
    			ChangeProfil newF = new ChangeProfil(Profil.this);
    			newF.show(getFragmentManager(), "changeProfil dialog");
            }
        });
    	
    	//initialisation de la vue en fonction de la base de donnés
    	ProfilDAO db = new ProfilDAO(this);
    	db.execute(); //récupération des données et mise à jour de la vue
    	
        return (LinearLayout) view;
    }
    
    
    public void updateProfil()
    {
    	ProfilDAO db = new ProfilDAO(this);
    	db.execute();
    	LocalSettings.profilCreer=false;
    }
}

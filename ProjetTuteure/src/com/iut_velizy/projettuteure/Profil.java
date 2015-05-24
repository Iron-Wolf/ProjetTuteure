package com.iut_velizy.projettuteure;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

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
    			ChangeProfil newF = new ChangeProfil();
    			newF.show(getFragmentManager(), "dialog");
            }
        });
    	
        return (LinearLayout) view;
    }
}

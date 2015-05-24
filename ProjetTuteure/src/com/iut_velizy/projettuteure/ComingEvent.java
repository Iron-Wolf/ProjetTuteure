package com.iut_velizy.projettuteure;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class ComingEvent extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.comingevent, container, false);
    	
    	//on récupère le bouton et on lui attribut un Listener
    	Button buttonHistory = (Button) view.findViewById(R.id.buttonHistory);
    	buttonHistory.setOnClickListener(new View.OnClickListener()
    	{
    		@Override
    		public void onClick(View v) {
    			//affiche la fenêtre d'historique
    			History newF = new History();
    			newF.show(getFragmentManager(), "dialog");
            }
        });
    	
        return (LinearLayout) view;
    }
}
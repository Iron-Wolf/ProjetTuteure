package com.iut_velizy.projettuteure;

import java.math.BigInteger;
import java.util.ArrayList;

import com.iut_velizy.dao.FindEventDAO;
import com.iut_velizy.dao.GetEventDAO;
import com.iut_velizy.localStorage.ComingEventStatic;
import com.iut_velizy.localStorage.CreateEventStatic;

import android.app.Fragment;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

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
		
		//numéro IMEI
    	TelephonyManager telephonyManager = (TelephonyManager)view.getContext().getSystemService(view.getContext().TELEPHONY_SERVICE);
    	ComingEventStatic.imei = telephonyManager.getDeviceId();
    	
    	//suppression des ArrayList avant le traitement
    	ComingEventStatic.listAdresses = new ArrayList<ArrayList>();
    	ComingEventStatic.listEventID = new ArrayList<Integer>();
    	ComingEventStatic.listNomEvent = new ArrayList<String>();
		
    	//récupération de tout les id des événement créer par l'utilisateur
		GetEventDAO geDAO = new GetEventDAO();
		geDAO.execute();
		
		//pour chaque identifiants, récupération des adresses
		FindEventDAO feDAO = new FindEventDAO(this);
		feDAO.execute();
		
		
        return (LinearLayout) view;
    }

}
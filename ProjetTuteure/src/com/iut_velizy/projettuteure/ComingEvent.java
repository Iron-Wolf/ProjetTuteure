package com.iut_velizy.projettuteure;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
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
    	
    	/*
    	RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.relativeCommingEvent);
    	
    	
    	//for (int i=0; i<3; i++){

    		RelativeLayout rel = new RelativeLayout(view.getContext());
    		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    		params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
    		TextView tv = new TextView(view.getContext());
    		tv.setText("evenement a venir");
    		tv.setId(1);
    		tv.setLayoutParams(params);
    		rel.addView(tv);
    		
    		
    		params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    		params.addRule(RelativeLayout.BELOW, 1);
    		TextView tv2 = new TextView(view.getContext());
    		tv2.setText("adresse");
    		tv2.setId(2);
    		tv2.setLayoutParams(params);
    		rel.addView(tv2);

    		
    		params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
    		ImageButton ib = new ImageButton(view.getContext());
    		ib.setImageResource(R.drawable.google_maps_icon);
    		ib.setBackground(null);
    		ib.setLayoutParams(params);
    		rel.addView(ib);
    		
    		
    		rl.addView(rel);
    	//}
    	*/
    	
        return (LinearLayout) view;
    }
}
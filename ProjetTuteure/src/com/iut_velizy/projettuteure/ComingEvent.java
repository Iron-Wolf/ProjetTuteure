package com.iut_velizy.projettuteure;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    		
    		
    		params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
    		View line = new View(view.getContext());
    		line.setBackgroundColor(color.black);
    		line.setMinimumHeight(10);
    		rel.addView(line);
    		
    		
    		rl.addView(rel);
    	//}*/
    	
    	TableLayout tl = (TableLayout) view.findViewById(R.id.tableLayout1);
			
		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,	LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(2, 2, 2, 2);
		
		for(int i = 0; i < 3; i++)
		{
			TableRow tr = new TableRow(view.getContext());
			tr.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
						
			LayoutParams event = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			event.setMargins(2, 40, 2, 2);
			TextView tv = new TextView(view.getContext());
    		tv.setText("adresse");
    		tv.setId(1);
    		tr.addView(tv, event);
    		
    		System.out.println();
    		
    		LayoutParams address = new LayoutParams(LayoutParams.WRAP_CONTENT,	LayoutParams.WRAP_CONTENT);
    		address.setMargins(2, 2, 2, 2);
			TextView tv2 = new TextView(view.getContext());
    		tv2.setText("evenement a venir");
    		tv2.setId(2);
    		tr.addView(tv2, address);
			
    		LayoutParams maps = new LayoutParams(LayoutParams.WRAP_CONTENT,	LayoutParams.WRAP_CONTENT);
    		maps.setMargins(200, 2, 2, 2);
    	 	ImageButton ib = new ImageButton(view.getContext());
    		ib.setImageResource(R.drawable.google_maps_icon);
    		ib.setBackground(null);
    		tr.addView(ib, maps);
			
    		
    		tl.addView(tr, layoutParams);
			
		}
		

    	
        return (LinearLayout) view;
        
        
    }
    /*
    public TextView generateTextView(String texte, LayoutParams ly) {
		TextView result = new TextView(this);
		result.setBackgroundColor(Color.LTGRAY);
		result.setTextColor(Color.DKGRAY);
		result.setGravity(Gravity.CENTER);
		result.setPadding(2, 2, 2, 2);
		result.setText(texte);
		result.setLayoutParams(ly);
		return result;*/
    
    
	
}
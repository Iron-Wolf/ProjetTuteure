package com.iut_velizy.projettuteure;

import android.app.Fragment;
import android.os.Bundle;
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

    	
    	TableLayout tl = (TableLayout) view.findViewById(R.id.tableLayout1);
			
		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,	LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(2,10, 2, 2);
		//layoutParams.setMarginStart(0);
		//layoutParams.setMarginEnd(400);
		
		for(int i = 0; i < 10; i++)
		{
			TableRow tr = new TableRow(view.getContext());
			tr.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			//tr.setBackground(@drawable/table);
						
			LayoutParams maps = new LayoutParams(LayoutParams.WRAP_CONTENT,	LayoutParams.WRAP_CONTENT);
    		//maps.setMargins(200, 2, 2, 30);
    		//maps.setMarginStart(300);
    	 	ImageButton ib = new ImageButton(view.getContext());
    		ib.setImageResource(R.drawable.google_maps_icon);
    		ib.setBackground(null);
    		tr.addView(ib,maps);
			
			LayoutParams event = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			event.setMargins(20, 45, 2, 2);
			//event.setMarginEnd(400);
			
			TextView tv = new TextView(view.getContext());
    		tv.setText("Evenement à venir dans un temps relativement proche");
    		//tv.setId(R.id.textViewEventName);
    		tr.addView(tv, event);
    		
			    		
    		tl.addView(tr, layoutParams);
			
		}
		
        return (LinearLayout) view;
        
        
    }

}
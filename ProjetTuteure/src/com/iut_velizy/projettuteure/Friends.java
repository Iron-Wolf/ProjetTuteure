package com.iut_velizy.projettuteure;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Friends extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
    	View view = inflater.inflate(R.layout.friends, container, false);
    	
    	//on récupère le bouton et on lui attribut un Listener
    	Button buttonAddFriends = (Button) view.findViewById(R.id.buttonAddFriends);
    	buttonAddFriends.setOnClickListener(new View.OnClickListener()
    	{
    		@Override
    		public void onClick(View v) {
    			//affiche la fenêtre d'ajout d'un amis
    			AddFriends newF = new AddFriends();
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
						

			
			//event.setMarginEnd(400);
			
			TextView tv = new TextView(view.getContext());
    		tv.setText("Evenement à venir dans un temps relativement proche");
    		//tv.setId(R.id.textViewEventName);
    		tr.addView(tv);
    		
			    		
    		tl.addView(tr, layoutParams);
			
		}
		

    	
        return (LinearLayout) view;
        
        
    }
}

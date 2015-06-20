package com.iut_velizy.projettuteure;

import java.util.ArrayList;

import com.iut_velizy.dao.GetEventDAO;
import com.iut_velizy.dao.HistoryDAO;
import com.iut_velizy.localStorage.ComingEventStatic;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

/**
 * Historique des �v�nements
 *
 */
public class History extends DialogFragment
{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MY_DIALOG);
	}
	
	@Override 
	public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d!=null){
        	int width = ViewGroup.LayoutParams.MATCH_PARENT;
        	int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
        }
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
        View view = inflater.inflate(R.layout.comingevent_history, container, false);
        
        //bouton retour
        ImageButton cancelButton = (ImageButton) view.findViewById(R.id.imageButtonRetour);
        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        
    	
    	//suppression de l'ArrayList avant le traitement
    	ComingEventStatic.listHistoryEvent = new ArrayList<ArrayList>();
    	
    	//r�cup�ration de tout les id des �v�nement cr�er par l'utilisateur
    	HistoryDAO hDAO = new HistoryDAO(this);
    	hDAO.execute();
        /*
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
						
			LayoutParams event = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			event.setMargins(20, 45, 2, 2);
			//event.setMarginEnd(400);
			
			TextView tv = new TextView(view.getContext());
    		tv.setText("Nom de l'event");
    		//tv.setId(R.id.textViewEventName);
    		tr.addView(tv, event);
			
			LayoutParams add = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			add.setMargins(20, 45, 2, 2);
			//event.setMarginEnd(400);
			
			TextView tv2 = new TextView(view.getContext());
    		tv2.setText("Adresse de l'event");
    		//tv.setId(R.id.textViewEventName);
    		tr.addView(tv2, add);
    		
			    		
    		tl.addView(tr, layoutParams);
			
		}
		*/

    	
        return (LinearLayout) view;
        
    }
}

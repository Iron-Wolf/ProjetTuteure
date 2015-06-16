package com.iut_velizy.projettuteure;

import com.iut_velizy.localStorage.CreateEventStatic;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Formulaire de création d'un évènement
 *
 */
public class Form extends DialogFragment
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
        View view = inflater.inflate(R.layout.createevent_form, container, false);
        
        final EditText etNomEvent = (EditText) view.findViewById(R.id.editTextNameEvent);
        final Spinner sCentreInteret = (Spinner) view.findViewById(R.id.spinnerInterest);
        final EditText etDateEvent = (EditText) view.findViewById(R.id.editTextEventDate);
        final EditText etHeureEvent = (EditText) view.findViewById(R.id.editTextEventTime);
        final EditText etDateCalcul = (EditText) view.findViewById(R.id.editTextEndDate);
        final EditText etheureCalcul = (EditText) view.findViewById(R.id.editTextEndTime);
        final EditText etDescription = (EditText) view.findViewById(R.id.editTextMessage);
        
        //action sur le bouton d'invitation
        Button validButton = (Button) view.findViewById(R.id.button1);
        validButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            	//affiche la fenêtre d'invitation des amis
    			/*InviteFriends newF = new InviteFriends();
    			newF.show(getFragmentManager(), "dialog");*/
            	
            	//récupération des champs de l'événement
            	CreateEventStatic.nomEvent = etNomEvent.getText().toString();
            	CreateEventStatic.centreInteret = sCentreInteret.getSelectedItem().toString();
            	CreateEventStatic.dateEvent = etDateEvent.getText().toString();
            	CreateEventStatic.heureEvent = etHeureEvent.getText().toString();
            	CreateEventStatic.dateCalcul = etDateCalcul.getText().toString();
            	CreateEventStatic.heureCalcul = etheureCalcul.getText().toString();
            	CreateEventStatic.description = etDescription.getText().toString();
            	
            	if (CreateEventStatic.nomEvent.equals("") ||
            		CreateEventStatic.centreInteret.equals("") ||
            		CreateEventStatic.dateEvent.equals("") ||
            		CreateEventStatic.heureEvent.equals("") ||
            		CreateEventStatic.dateCalcul.equals("") ||
            		CreateEventStatic.heureCalcul.equals(""))
            	{
            		Toast.makeText(view.getContext(), "Champ(s) manquant", Toast.LENGTH_SHORT).show();
            	}
            	else
            	{
            		AdresseEvent cf = new AdresseEvent();
            		cf.show(getFragmentManager(), "createevent_adresses");
            	}
            }
        });
        
        //bouton retour
        ImageButton cancelButton = (ImageButton) view.findViewById(R.id.imageButtonRetour);
        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }
	
}

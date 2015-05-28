package com.iut_velizy.projettuteure;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class Organize extends DialogFragment
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
        View view = inflater.inflate(R.layout.createevent_organize, container, false);
        
        Button validButton = (Button) view.findViewById(R.id.buttonSave);
        validButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            	//affichage simple d'une fenêtre
            	AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
        		alertDialog.setTitle("bouton enregistrer");
        		alertDialog.setMessage("classe : ChangeProfil");
        		Message msg = null;
        		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"OK", msg);
                alertDialog.show();
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

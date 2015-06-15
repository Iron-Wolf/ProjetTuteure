package com.iut_velizy.projettuteure;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * gestion des adresses à calculer dans la Map
 * disponible dans le menu principal
 *
 */
public class ConfMapsRapide extends DialogFragment
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
        View view = inflater.inflate(R.layout.confmaps, container, false);
        
        //liste des champs
        final EditText et = (EditText) view.findViewById(R.id.editText);
        final EditText et2 = (EditText) view.findViewById(R.id.editText2);
        final EditText et3 = (EditText) view.findViewById(R.id.editText3);
        final EditText et4 = (EditText) view.findViewById(R.id.editText4);
        final EditText et5 = (EditText) view.findViewById(R.id.editText5);
        final EditText et6 = (EditText) view.findViewById(R.id.editText6);
        
        //bouton valider
        Button validButton = (Button) view.findViewById(R.id.buttonValidation);
        validButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            	//récupération des données
            	String adresse1=et.getText().toString();
            	String adresse2=et2.getText().toString();
            	String adresse3=et3.getText().toString();
            	String adresse4=et4.getText().toString();
            	String adresse5=et5.getText().toString();
            	String adresse6=et6.getText().toString();
            	
            	//affichage de la carte
            	Maps newF2 = new Maps(adresse1,adresse2,adresse3,adresse4,adresse5,adresse6);
    			newF2.show(getFragmentManager(), "dialog");
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

package com.iut_velizy.projettuteure;

import com.iut_velizy.dao.CreateEventDAO;
import com.iut_velizy.localStorage.CreateEventStatic;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * gestion des adresses lors de la création d'un evenement
 *
 */
public class AdresseEvent extends DialogFragment
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
        View view = inflater.inflate(R.layout.createevent_adresses, container, false);
        
        //liste des champs
        final EditText etv1 = (EditText) view.findViewById(R.id.editTextVoie1);
        final EditText etvi1 = (EditText) view.findViewById(R.id.editTextVille1);
        final EditText etp1 = (EditText) view.findViewById(R.id.editTextPays1);
        final EditText etv2 = (EditText) view.findViewById(R.id.editTextVoie2);
        final EditText etvi2 = (EditText) view.findViewById(R.id.editTextVille2);
        final EditText etp2 = (EditText) view.findViewById(R.id.editTextPays2);
        final EditText etv3 = (EditText) view.findViewById(R.id.editTextVoie3);
        final EditText etvi3 = (EditText) view.findViewById(R.id.editTextVille3);
        final EditText etp3 = (EditText) view.findViewById(R.id.editTextPays3);
        final EditText etv4 = (EditText) view.findViewById(R.id.editTextVoie4);
        final EditText etvi4 = (EditText) view.findViewById(R.id.editTextVille4);
        final EditText etp4 = (EditText) view.findViewById(R.id.editTextPays4);
        final EditText etv5 = (EditText) view.findViewById(R.id.editTextVoie5);
        final EditText etvi5 = (EditText) view.findViewById(R.id.editTextVille5);
        final EditText etp5 = (EditText) view.findViewById(R.id.editTextPays5);
        final EditText etv6 = (EditText) view.findViewById(R.id.editTextVoie6);
        final EditText etvi6 = (EditText) view.findViewById(R.id.editTextVille6);
        final EditText etp6 = (EditText) view.findViewById(R.id.editTextPays6);
        
        //bouton valider
        Button validButton = (Button) view.findViewById(R.id.buttonValidation);
        validButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            	//récupération des données
            	CreateEventStatic.adresse1_voie = etv1.getText().toString();
            	CreateEventStatic.adresse1_ville = etvi1.getText().toString();
            	CreateEventStatic.adresse1_pays = etp1.getText().toString();
            	CreateEventStatic.adresse2_voie = etv2.getText().toString();
            	CreateEventStatic.adresse2_ville = etvi2.getText().toString();
            	CreateEventStatic.adresse2_pays = etp2.getText().toString();
            	CreateEventStatic.adresse3_voie = etv3.getText().toString();
            	CreateEventStatic.adresse3_ville = etvi3.getText().toString();
            	CreateEventStatic.adresse3_pays = etp3.getText().toString();
            	CreateEventStatic.adresse4_voie = etv4.getText().toString();
            	CreateEventStatic.adresse4_ville = etvi4.getText().toString();
            	CreateEventStatic.adresse4_pays = etp4.getText().toString();
            	CreateEventStatic.adresse5_voie = etv5.getText().toString();
            	CreateEventStatic.adresse5_ville = etvi5.getText().toString();
            	CreateEventStatic.adresse5_pays = etp5.getText().toString();
            	CreateEventStatic.adresse6_voie = etv6.getText().toString();
            	CreateEventStatic.adresse6_ville = etvi6.getText().toString();
            	CreateEventStatic.adresse6_pays = etp6.getText().toString();
            	
            	//numéro IMEI
            	TelephonyManager telephonyManager = (TelephonyManager)view.getContext().getSystemService(view.getContext().TELEPHONY_SERVICE);
            	CreateEventStatic.imei = telephonyManager.getDeviceId();
            	
            	
            	if (CreateEventStatic.adresse1_voie.equals("") ||
                	CreateEventStatic.adresse1_ville.equals("") ||
                	CreateEventStatic.adresse1_pays.equals(""))
                {
                	Toast.makeText(view.getContext(), "nécessite une adresse minimum", Toast.LENGTH_SHORT).show();
                }
                else
                {
	            	//enregistrement en base
	            	CreateEventDAO ceDao = new CreateEventDAO(view.getContext());
	            	ceDao.execute();
	            	
	            	
	            	//on dismiss les deux fragments et redirige l'utilisateur au premier écrant
	            	Fragment prev = getFragmentManager().findFragmentByTag("createevent_form");
	                if (prev != null) {
	                    DialogFragment df = (DialogFragment) prev;
	                    df.dismiss();
	                }
	                
	            	dismiss();
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

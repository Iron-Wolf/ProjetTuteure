package com.iut_velizy.projettuteure;

import com.iut_velizy.dao.CreerProfilDAO;
import com.iut_velizy.dao.LoginDAO;
import com.iut_velizy.localStorage.CreateEventStatic;
import com.iut_velizy.localStorage.LocalSettings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Permet de modifier creer un profil
 *
 */
public class CreerProfil extends DialogFragment
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
        View view = inflater.inflate(R.layout.profil_createprofil, container, false);
        
        //caractéristique des champs
        final EditText inputLogin = (EditText) view.findViewById(R.id.editTextLogin);
        final EditText inputPass = (EditText) view.findViewById(R.id.editTextPassword);
        final EditText inputPrenom = (EditText) view.findViewById(R.id.editTextFirstName);
        final EditText inputNom = (EditText) view.findViewById(R.id.editTextSecondName);
        final EditText inputMail = (EditText) view.findViewById(R.id.editTextMail);
        
        final EditText inputQst_secrete = (EditText) view.findViewById(R.id.editTextSecretQuestion);
        final EditText inputRps_secrete = (EditText) view.findViewById(R.id.editTextReponseQuestion);
        
        final EditText inputPays = (EditText) view.findViewById(R.id.editTextPays);
        final EditText inputVille = (EditText) view.findViewById(R.id.editTextVille);
        final EditText inputVoie = (EditText) view.findViewById(R.id.editTextStreet);
        
        
        
        Button validButton = (Button) view.findViewById(R.id.buttonSave);
        validButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
            	//récupération des champs
            	String login = inputLogin.getText().toString();
            	String pass = inputPass.getText().toString();
            	String prenom = inputPrenom.getText().toString();
            	String nom = inputNom.getText().toString();
            	String mail = inputMail.getText().toString();
            	
            	String qst_secrete = inputQst_secrete.getText().toString();
            	String rps_secrete = inputRps_secrete.getText().toString();
            	
            	String pays = inputPays.getText().toString();
            	String ville = inputVille.getText().toString();
            	String voie = inputVoie.getText().toString();
            	
            	
            	try
				{
            		//numéro IMEI
                	TelephonyManager telephonyManager = (TelephonyManager)view.getContext().getSystemService(view.getContext().TELEPHONY_SERVICE);
                	String imei = telephonyManager.getDeviceId();
            		
					String url = new String("http://"+LocalSettings.url+"/createProfil.php");
					url += "?id=" + imei;
					url += "&login=" + login;
					url += "&password=" + pass;
					url += "&prenom=" + prenom;
					url += "&nom=" + nom;
					url += "&email=" + mail;

					url += "&quest_secre=" + qst_secrete;
					url += "&rep_secre=" + rps_secrete;
					
					url += "&pays=" + pays;
					url += "&ville=" + ville;
					url += "&voie=" + voie;
					
					url = url.replace(" ", "%20");
					
					if (login.equals("") ||
						pass.equals("") ||
						prenom.equals("") ||
						nom.equals("") ||
						mail.equals("") ||
						qst_secrete.equals("") ||
						rps_secrete.equals("") ||
						pays.equals("") ||
						ville.equals("") ||
						voie.equals(""))
		            {
		            	Toast.makeText(view.getContext(), "Champ(s) manquant", Toast.LENGTH_SHORT).show();
		            }
		            else
		            {
		            	CreerProfilDAO ldao = new CreerProfilDAO(getFragmentManager());
						ldao.execute(url);
		            }
				}
				catch (Exception e)
				{
					
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

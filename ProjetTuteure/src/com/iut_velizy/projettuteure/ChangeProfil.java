package com.iut_velizy.projettuteure;

import com.iut_velizy.dao.LoginDAO;
import com.iut_velizy.dao.ProfilDAO;
import com.iut_velizy.dao.ProfilUpdateDAO;
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

/**
 * Permet de modifier les paramètres du profil
 *
 */
public class ChangeProfil extends DialogFragment
{
	private EditText login;
	private EditText password;
	private EditText prenom;
	private EditText nom;
	private EditText mail;
	private EditText qst_secret;
	private EditText rsp_secret;
	private EditText voie;
	private EditText ville;
	private EditText pays;
	private Profil profil;
	
	public ChangeProfil(Profil profil)
	{
		this.profil = profil;
	}
	
	
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
        View view = inflater.inflate(R.layout.profil_changeprofil, container, false);
        
        /* Importation des caractéristiques des champs et boutons */
        login = (EditText) view.findViewById(R.id.editTextLogin);
    	password = (EditText) view.findViewById(R.id.editTextPassword);
    	prenom = (EditText) view.findViewById(R.id.editTextFirstName);
    	nom = (EditText) view.findViewById(R.id.editTextSecondName);
    	mail = (EditText) view.findViewById(R.id.editTextMail);
    	qst_secret = (EditText) view.findViewById(R.id.editTextSecretQuestion);
    	rsp_secret = (EditText) view.findViewById(R.id.editTextReponseQuestion);
    	voie =  (EditText) view.findViewById(R.id.editTextStreet);
    	ville = (EditText) view.findViewById(R.id.editTextVille);
    	pays = (EditText) view.findViewById(R.id.editTextPays);
    	
    	/* on remplit les champs en fonction des valeurs de la page de profil */
    	login.setText(ProfilDAO.login);
    	password.setText(ProfilDAO.password);
    	prenom.setText(ProfilDAO.prenom);
    	nom.setText(ProfilDAO.nom);
    	mail.setText(ProfilDAO.mail);
    	qst_secret.setText(ProfilDAO.qst_secrete);
    	rsp_secret.setText(ProfilDAO.rsp_secrete);
    	voie.setText(ProfilDAO.voie);
    	ville.setText(ProfilDAO.ville);
    	pays.setText(ProfilDAO.pays);
    	
        Button validButton = (Button) view.findViewById(R.id.buttonSave);
        validButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            	//numéro IMEI
	        	TelephonyManager telephonyManager = (TelephonyManager)getView().getContext().getSystemService(getView().getContext().TELEPHONY_SERVICE);
	        	String imei = telephonyManager.getDeviceId();
            	
            	String url = new String("http://"+LocalSettings.url+"/updateProfil.php");
            	url += "?id=" + imei;
            	url += "&login=" + login.getText().toString();
            	url += "&password=" + password.getText().toString();
            	url += "&prenom=" + prenom.getText().toString();
            	url += "&nom=" + nom.getText().toString();
            	url += "&email=" +  mail.getText().toString();
            	url += "&quest_secre=" + qst_secret.getText().toString();
            	url += "&rep_secre=" + rsp_secret.getText().toString();
            	url += "&voie=" + voie.getText().toString();
            	url += "&ville=" + ville.getText().toString();
            	url += "&pays=" + pays.getText().toString();
            	
            	url = url.replace(" ", "%20"); 
        		
				ProfilUpdateDAO ldao = new ProfilUpdateDAO(getFragmentManager());
				ldao.execute(url);
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
	
	
	@Override
	public void onDismiss(DialogInterface dialog)
	{
		profil.updateProfil();
	}
}

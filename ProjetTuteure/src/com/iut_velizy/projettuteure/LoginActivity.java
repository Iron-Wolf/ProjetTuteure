package com.iut_velizy.projettuteure;

import com.iut_velizy.dao.LoginDAO;
import com.iut_velizy.localStorage.LocalSettings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends DialogFragment
{
	EditText inputEmail;
	EditText inputPassword;
	CheckBox checkBox;
	Button btnLogin;
	Button btnLinkToRegister;
	private MainActivity screen;
	private String donneesFichier;
	private LocalSettings ls;
	
	
	
	/* Réponse JSON */
	private static String KEY_SUCCESS = "success";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";

	
	/* Enregistrement des préférences */
	public static final String PREFS_NAME = ".Preferences";
	private static final String PREF_EMAIL = "email";
	private static final String PREF_PASSWORD = "password";
	private static final String PREF_CHECKED = "checked";
	
	
	public LoginActivity(MainActivity screen)
	{
		this.screen = screen;
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
		View view = inflater.inflate(R.layout.login, container, false);
		
		/* Importation des caractéristiques des champs et boutons */
		inputEmail = (EditText) view.findViewById(R.id.loginEmail);
		inputPassword = (EditText) view.findViewById(R.id.loginPassword);
		checkBox = (CheckBox) view.findViewById(R.id.cbRememberMe);
		btnLogin = (Button) view.findViewById(R.id.btnLogin);
		btnLinkToRegister = (Button) view.findViewById(R.id.btnLinkToRegisterScreen);
		
		
		/* lecture des données du fichier */
		ls = new LocalSettings();
		donneesFichier = ls.ReadSettings(view.getContext());
		
		if (donneesFichier.startsWith("true"))
		{
			checkBox.setChecked(true);
			
			//affichage des données dans les champs correspondants
			String[] donnees = donneesFichier.split(":");
			inputEmail.setText(donnees[1].trim());
    		inputPassword.setText(donnees[2].trim());
		}
		
		
		/* clic sur la checkbox */
		checkBox.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
				if (checkBox.isChecked())
				{
					//la checkbox est cochée, on met à jour le fichier
					ls.WriteSettings(view.getContext(), "true");
					
				}
				else
				{
					ls.WriteSettings(view.getContext(), "false");
				}
			}
		});
		
		
		/* Clic sur le bouton Login */
		btnLogin.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
				String login = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();
				
				//si la check box est cochée, on enregistre les valeurs
				if (checkBox.isChecked())
					ls.WriteSettings(view.getContext(), "true:"+login+":"+password);
				
				// APPEL ICI !!!!!!
				try
				{
					String jsonData = new String();
					String url = new String("http://"+LocalSettings.url+"/connexion.php?login=" + login + "&password=" + password);
		
					LoginDAO ldao = new LoginDAO(getFragmentManager());
					ldao.execute(url);
				}
				catch (Exception e)
				{
					
				}
			}
		});
		
		
		/* Clic sur le bouton de création de compte */
		btnLinkToRegister.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
				//affiche la fenêtre de modification du profil
    			CreerProfil newF = new CreerProfil();
    			newF.show(getFragmentManager(), "creerProfil dialog");
			}
		});
		
		return view;
	}
	
	/**
	 * si le bouton "retour" d'android est utilisé,
	 * on réaffiche la page de login
	 */
	@Override
	public void onDismiss(DialogInterface dialog)
	{
		screen.updateLogin();
	}
	
}
package com.iut_velizy.projettuteure;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.iut_velizy.dao.LoginDAO;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

	/****************/
	/* Réponse JSON */
	/****************/

	private static String KEY_SUCCESS = "success";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";

	/**********************************/
	/* Enregistrement des préférences */
	/**********************************/

	public static final String PREFS_NAME = ".Preferences";
	private static final String PREF_EMAIL = "email";
	private static final String PREF_PASSWORD = "password";
	private static final String PREF_CHECKED = "checked";

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
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.login, container, false);
		/********************************/
		/* Définit le nom de l'Activity */
		/********************************/

		//setTitle("Login");

		/**********************************************************/
		/* Importation des caractéristiques des champs et boutons */
		/**********************************************************/

		inputEmail = (EditText) view.findViewById(R.id.loginEmail);
		inputPassword = (EditText) view.findViewById(R.id.loginPassword);
		checkBox = (CheckBox) view.findViewById(R.id.cbRememberMe);
		btnLogin = (Button) view.findViewById(R.id.btnLogin);
		btnLinkToRegister = (Button) view.findViewById(R.id.btnLinkToRegisterScreen);

		/****************************/
		/* Clic sur le bouton Login */
		/****************************/

		btnLogin.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
				String login = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();

				// APPEL ICI !!!!!!
				try
				{
					String jsonData = new String();
					String url = new String("http://bountiful.minecraftnoob.com/connexion.php?login=" + login + "&password=" + password);
		
					LoginDAO ldao = new LoginDAO();
					ldao.execute(url);
					/*DefaultHttpClient client = new DefaultHttpClient();
			        HttpResponse response = client.execute(new HttpGet(url));
			            
			        HttpEntity entity = response.getEntity();
			        jsonData = EntityUtils.toString(entity);
			        
			        JSONObject obj = new JSONObject(jsonData);
			        String result = obj.getString("etat");*/
				}
				catch (Exception e)
				{
					
				}
			}
		});
		return view;
	}
}
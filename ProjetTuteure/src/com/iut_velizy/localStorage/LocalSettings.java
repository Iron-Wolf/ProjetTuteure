package com.iut_velizy.localStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * gère les données de sauvegarde dans un fichier local, et les données en caches<br/>
 * Path du fichier : /data/data/PACKAGE_NAME/files/settings.dat
 *
 */
public class LocalSettings
{
	//flag qui controle l'affichage de la page de login
	public static boolean dejaLoger = false;
	public static boolean profilUpdate = false;
	
	//flag qui indique si le profil a bien été créé
	public static boolean profilCreer = false;
	
	//nom du fichier
	public final String nomFichier = "settings.dat";
	
	
	public static final String url = "192.168.1.12"; //permet de tester en local
	//public static final String url = "bountiful.minecraftnoob.com";
	
	
	// sauvegarder données
	public void WriteSettings(Context context, String data)
	{
		FileOutputStream fOut = null;
		OutputStreamWriter osw = null;
		try{
			fOut = context.openFileOutput(nomFichier,0);      
			osw = new OutputStreamWriter(fOut);
			osw.write(data);
			osw.flush();
			}
		catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(context, "Erreur de sauvegarde",Toast.LENGTH_SHORT).show();
			}
		finally 
		{
			try 
			{
				osw.close();
				fOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// récupère les données du fichier
	public String ReadSettings(Context context)
	{
		FileInputStream fIn = null;
		InputStreamReader isr = null;
		char[] inputBuffer = new char[255];
		String data = null;
		
		try
		{
			fIn = context.openFileInput(nomFichier);
			isr = new InputStreamReader(fIn);
			isr.read(inputBuffer);
			data = new String(inputBuffer);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			Toast.makeText(context, "Les données sont illisibles",Toast.LENGTH_SHORT).show();
		}
		finally
		{
			try
			{
				isr.close();
				fIn.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
         }
		
		return data;
    }
	
	
}

package com.iut_velizy.localStorage;

import java.util.ArrayList;

/**
 * 
 * Contient toute les donn�es permettant l'affichage des evenement � venir
 * 
 */
public class ComingEventStatic
{
	public static String imei = "";
	public static ArrayList<Integer> listEventID = new ArrayList<Integer>();
	public static ArrayList<String> listNomEvent = new ArrayList<String>();
	
	public static ArrayList<ArrayList> listHistoryEvent = new ArrayList<ArrayList>();
	
	//tableau � deux dimensions, contenant la liste des adresses pour chaques evenements
	public static ArrayList<ArrayList> listAdresses = new ArrayList<ArrayList>();
	
}

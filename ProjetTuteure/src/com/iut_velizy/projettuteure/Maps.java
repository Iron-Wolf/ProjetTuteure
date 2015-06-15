package com.iut_velizy.projettuteure;

import java.util.ArrayList;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.iut_velizy.dao.MapsGeocoding;

/**
 * Affiche la carte de google en fonction des adresses rentrées
 *
 */
public class Maps extends DialogFragment {

    private MapView mapView;
    private GoogleMap map;
    private int count=0; //compte le nombre d'éléments (longitude et latitude)
    public ArrayList<String> arrayLoc = new ArrayList<String>();
    
    public Maps(String... adresses) {
		for (String adresse : adresses)
		{
			if (!adresse.equals(""))
			{
				//on traite les champs non vides
				String newS = adresse.trim().replace(" ", "+");
				new MapsGeocoding(this).execute(newS);
				count+=2; //une adresse correspond a deux éléments (latitude et longitude)
			}
		}
	}
    
    public void update()
    {
    	if (arrayLoc.size()==count)
    	{
    		//si le nombre d'éléments dans l'arraylist correspond au nombre d'éléments
    		//rentrés par l'utilisateur, on calcul le barycentre
    		double moyLat = 0;
    		double moyLng = 0;
    		int j=0;
    		
    		for (int i=0; i<arrayLoc.size(); i+=2)
    		{
    			moyLat += Double.parseDouble(arrayLoc.get(i));
    			moyLng += Double.parseDouble(arrayLoc.get(i+1));
    			LatLng currentLoc =  new LatLng(Double.parseDouble(arrayLoc.get(i)),Double.parseDouble(arrayLoc.get(i+1)));
    			map.addMarker(new MarkerOptions()
                .title("Position : "+(j++))
                .position(currentLoc));
    		}
    		
    		//calcul du barycentre des adresses
    		int diviseur = arrayLoc.size()/2;
    		LatLng newLocation = new LatLng(moyLat/diviseur,moyLng/diviseur);
    		
    		//ajout du marker sur la carte
    		map.addMarker(new MarkerOptions()
            .title("Position")
            .snippet("Centre des positions")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
            .position(newLocation));
    		
    		//mise a jour de la carte
    		CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(newLocation, 12);
    		map.moveCamera(cameraUpdate);
    		//map.animateCamera(cameraUpdate);
    	}
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.maps, container, false);

        // récupération du MapView depuis le XML, puis on le crée
        mapView = (MapView) v.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        // récupération de la carte depuis le MapView et configuration initiale
        map = mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);

        // besoin d'appeler MapsInitializer avant n'importe quel appel de CameraUpdateFactory
        MapsInitializer.initialize(this.getActivity());
        
        return v;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}
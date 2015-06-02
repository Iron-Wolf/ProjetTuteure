package com.iut_velizy.projettuteure;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MapTest extends DialogFragment {

    MapView mapView;
    GoogleMap map;
    
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
        View v = inflater.inflate(R.layout.maptest, container, false);

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) v.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        map = mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(this.getActivity());


        // Updates the location and zoom of the MapView
        
        /* données recupérées par géocodage en fonction des adresses des amis */
        LatLng paris = new LatLng(48.85, 2.35);
        LatLng velizy = new LatLng(48.7840659, 2.1955384);
        LatLng issy = new LatLng(48.8239654,2.26274);
        LatLng montrouge = new LatLng(48.815938,2.3162615);
        
        /* calcul du barycentre des adresses */
        double moyLat = (velizy.latitude + issy.latitude + montrouge.latitude) / 3;
        double moyLong = (velizy.longitude + issy.longitude + montrouge.longitude) / 3;
        LatLng newLocation = new LatLng(moyLat,moyLong);
        
        map.addMarker(new MarkerOptions()
        .title("Le Titre")
        .snippet("le snippet")
        .position(newLocation));
        
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(newLocation, 12);
        map.moveCamera(cameraUpdate);
        //map.animateCamera(cameraUpdate);

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
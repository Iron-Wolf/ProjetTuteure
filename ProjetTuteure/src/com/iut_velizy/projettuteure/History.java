package com.iut_velizy.projettuteure;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Historique des évènements
 *
 */
public class History extends DialogFragment
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
        View view = inflater.inflate(R.layout.comingevent_history, container, false);
        /*
        Button validButton = (Button) view.findViewById(R.id.buttonValidation);
        validButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
            	AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
        		alertDialog.setTitle("bouton valider");
        		alertDialog.setMessage("classe : AddFriends");
        		Message msg = null;
        		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"OK", msg);
                alertDialog.show();
            }
        });*/
        
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

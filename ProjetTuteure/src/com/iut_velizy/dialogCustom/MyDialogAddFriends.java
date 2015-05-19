package com.iut_velizy.dialogCustom;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.iut_velizy.projettuteure.R;

public class MyDialogAddFriends extends DialogFragment
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
        View view = inflater.inflate(R.layout.addfriends, container, false);
/*
        Button saveButton = (Button) view.findViewById(R.id.buttonValidation);
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });
*/
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

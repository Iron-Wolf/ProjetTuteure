package com.iut_velizy.projettuteure;

import android.app.Fragment;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Friends extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
    	View view = inflater.inflate(R.layout.friends, container, false);
    	
    	//on récupère le bouton et on lui attribut un Listener
    	Button buttonAddFriends = (Button) view.findViewById(R.id.buttonAddFriends);
    	buttonAddFriends.setOnClickListener(new View.OnClickListener()
    	{
    		@Override
    		public void onClick(View v) {
    			//affiche la fenêtre d'ajout d'un amis
    			AddFriends newF = new AddFriends();
    			newF.show(getFragmentManager(), "dialog");
            }
        });
    	
    	RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.relativePrincipal);
    	
    	TableLayout tl = (TableLayout) view.findViewById(R.id.tableLayout1);
		
		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,	LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(2, 2, 2, 2);
    	
    	for (int i=0; i<3; i++)
    	{
    		TableRow tr = new TableRow(view.getContext());
			tr.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));    		
    		
    		RelativeLayout rel = new RelativeLayout(view.getContext());
        	LayoutParams params = new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        	params.setMargins(5, 20, 5, 0);
        	rel.setLayoutParams(params);


			ImageView imageViewImage = new ImageView(view.getContext());
			imageViewImage.setId(1);
			imageViewImage.setImageResource(R.drawable.ic_launcher);
			LayoutParams layout_image = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			layout_image.setMargins(5, 0, 0, 0);
			imageViewImage.setLayoutParams(layout_image);
			tr.addView(imageViewImage, layout_image);
			
			TextView textViewFirstName = new TextView(view.getContext());
			textViewFirstName.setId(2);
			textViewFirstName.setText("Prénom");
			LayoutParams layout_prenom = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			layout_prenom.setMargins(100, 0, 0, 0);
			textViewFirstName.setLayoutParams(layout_prenom);
			tr.addView(textViewFirstName, layout_prenom);
			
			LayoutParams layout_nom = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			layout_nom.setMargins(300, 0, 0, 0);
			TextView textView2 = new TextView(view.getContext());
			textView2.setId(3);
			textView2.setText("Nom");			
			textView2.setLayoutParams(layout_nom);
			tr.addView(textView2, layout_nom);
			
			TextView textViewDistance = new TextView(view.getContext());
			textViewDistance.setId(4);
			textViewDistance.setText("Distance (en m)");
			LayoutParams layout_textdistance = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			layout_textdistance.setMargins(100,50, 0, 0);
			textViewDistance.setLayoutParams(layout_textdistance);
			tr.addView(textViewDistance, layout_textdistance);
			
			/*EditText editTextDistance = new EditText(view.getContext());
			editTextDistance.setId(5);
			editTextDistance.setEms(5);
			editTextDistance.setRawInputType(InputType.TYPE_CLASS_NUMBER);
			LayoutParams layout_7 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			//editTextDistance.setLayoutParams(layout_7);
			tr.addView(editTextDistance, layout_7);*/
			
			
			tl.addView(tr, params);
    	}

    	
    	
        return (LinearLayout) view;
    }
}

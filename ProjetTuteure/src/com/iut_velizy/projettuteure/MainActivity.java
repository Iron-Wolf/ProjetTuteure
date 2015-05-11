package com.iut_velizy.projettuteure;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity 
{
 
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
 
        ActionBar actionBar = getActionBar();
 
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
 
        // Onglet 1 : Créer un évènement //
        String labelCreateEvent = getResources().getString(R.string.labelCreateEvent);
        Tab tab = actionBar.newTab();
        tab.setText(labelCreateEvent);
        TabListener<CreateEvent> tl = new TabListener<CreateEvent>(this, labelCreateEvent, CreateEvent.class);
        tab.setTabListener(tl);
        actionBar.addTab(tab);
        
        // Onglet 2 : Evènements à venir //
        String labelComingEvent = getResources().getString(R.string.labelComingEvent);
        tab = actionBar.newTab();
        tab.setText(labelComingEvent);
        TabListener<ComingEvent> tl2 = new TabListener<ComingEvent>(this, labelComingEvent, ComingEvent.class);
        tab.setTabListener(tl2);
        actionBar.addTab(tab);
        
        // Onglet 3 : Historique //
        String labelHistory = getResources().getString(R.string.labelHistory);
        tab = actionBar.newTab();
        tab.setText(labelHistory);
        TabListener<History> tl3 = new TabListener<History>(this, labelHistory, History.class);
        tab.setTabListener(tl3);
        actionBar.addTab(tab);
        
        // Onglet 4 : Amis proches //
        String labelNearFriends = getResources().getString(R.string.labelNearFriends);
        tab = actionBar.newTab();
        tab.setText(labelNearFriends);
        TabListener<NearFriends> tl4 = new TabListener<NearFriends>(this, labelNearFriends, NearFriends.class);
        tab.setTabListener(tl4);
        actionBar.addTab(tab);
        
        // Onglet 5 : Amis //
        String labelFriends = getResources().getString(R.string.labelFriends);
        tab = actionBar.newTab();
        tab.setText(labelFriends);
        TabListener<Friends> tl5 = new TabListener<Friends>(this, labelFriends, Friends.class);
        tab.setTabListener(tl5);
        actionBar.addTab(tab);
        
        // Onglet 6 : Profil //
        String labelProfil = getResources().getString(R.string.labelProfil);
        tab = actionBar.newTab();
        tab.setText(labelProfil);
        TabListener<Profil> tl6 = new TabListener<Profil>(this, labelProfil, Profil.class);
        tab.setTabListener(tl6);
        actionBar.addTab(tab);
 
    }
 
    private class TabListener<T extends Fragment> implements ActionBar.TabListener 
    {
        private Fragment mFragment;
        private final Activity mActivity;
        private final String mTag;
        private final Class<T> mClass;
 
        /**
         * Constructor used each time a new tab is created.
         * 
         * @param activity
         *            The host Activity, used to instantiate the fragment
         * @param tag
         *            The identifier tag for the fragment
         * @param clz
         *            The fragment's Class, used to instantiate the fragment
         */
        public TabListener(Activity activity, String tag, Class<T> clz) 
        {
            mActivity = activity;
            mTag = tag;
            mClass = clz;
        }
 
        public void onTabSelected(Tab tab, FragmentTransaction ft) 
        {
            // Check if the fragment is already initialized
            if (mFragment == null) 
            {
                // If not, instantiate and add it to the activity
                mFragment = Fragment.instantiate(mActivity, mClass.getName());
                ft.add(android.R.id.content, mFragment, mTag);
            } 
            else 
            {
                // If it exists, simply attach it in order to show it
                ft.attach(mFragment);
            }
        }
 
        public void onTabUnselected(Tab tab, FragmentTransaction ft) 
        {
            if (mFragment != null) 
            {
                // Detach the fragment, because another one is being attached
                ft.detach(mFragment);
            }
        }
 
        public void onTabReselected(Tab tab, FragmentTransaction ft) 
        {
            // User selected the already selected tab. Usually do nothing.
        }
    }
    

    			
}
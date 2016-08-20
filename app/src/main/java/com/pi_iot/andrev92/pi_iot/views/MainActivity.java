package com.pi_iot.andrev92.pi_iot.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.pi_iot.andrev92.pi_iot.IOTApp;
import com.pi_iot.andrev92.pi_iot.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  {

    @BindView(R.id.toolbar_actions)
    Toolbar toolbar;


    @BindView(R.id.list_gpios)
    RecyclerView list_gpios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupGPIOS();
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;
    }


    private void setupGPIOS(){
        list_gpios.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        list_gpios.setAdapter(new GPIOListAdapter());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                SettingsFragment fragment = SettingsFragment.newInstance(IOTApp.getSpManager());
                fragment.show(getSupportFragmentManager(),"Settings");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}



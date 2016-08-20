package com.pi_iot.andrev92.pi_iot.views;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.common.net.InetAddresses;
import com.pi_iot.andrev92.pi_iot.R;
import com.pi_iot.andrev92.pi_iot.utils.SPManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by avlad92 on 8/20/2016.
 */
public class SettingsFragment extends DialogFragment {

    @BindView(R.id.text_ip) EditText text_IP;
    @BindView(R.id.text_port) EditText text_port;
    @BindView(R.id.text_info)
    LinearLayout text_info;
    private AlertDialog.Builder dialogBuilder;
    private SPManager spManager;
    private Dialog dialog;


    public static SettingsFragment newInstance(SPManager spManager){
        SettingsFragment fragment = new SettingsFragment();
        fragment.spManager = spManager;
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initDialog();
        dialog = dialogBuilder.create();
        setFragmentActions();
        return dialog;
    }

    private void initDialog(){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.settings_fragment,null);
        ButterKnife.bind(this,dialogView);
        dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setTitle("Settings");
        dialogBuilder.setIcon(ContextCompat.getDrawable(getContext(),R.drawable.ic_settings_blue_grey_24dp));
        dialogBuilder.setPositiveButton("Save",null);
        dialogBuilder.setNegativeButton("Cancel",null);
        dialogBuilder.setNeutralButton("Info",null);


        dialogBuilder.setView(dialogView);
        text_IP.setText(spManager.getIPAddress());
        text_port.setText(spManager.getPort());
    }

    private void setFragmentActions(){
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button positive_btn = ((AlertDialog) dialogInterface).getButton(AlertDialog.BUTTON_POSITIVE);
                Button negative_btn = ((AlertDialog) dialogInterface).getButton(AlertDialog.BUTTON_NEGATIVE);
                final Button neutral_btn = ((AlertDialog) dialogInterface).getButton(AlertDialog.BUTTON_NEUTRAL);

                positive_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean validIP = false;
                        boolean validPort = false;
                        String IP = text_IP.getText().toString();
                        String port = text_port.getText().toString();
                        validIP = InetAddresses.isInetAddress(IP);
                        try {
                            int port_n = Integer.parseInt(port);
                            if(port_n>=0 && port_n <=49151) {
                                validPort = true;
                            }
                        }catch (Exception e){

                        }

                        if(validPort && validIP) {
                            spManager.saveNewServerAddress(text_IP.getText().toString(), text_port.getText().toString());
                            dismiss();
                        }else{
                            if(!validIP) {
                                Toast.makeText(getContext(), "IP address is not valid.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if(!validPort) {
                                Toast.makeText(getContext(), "Port number is not valid.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            Toast.makeText(getContext(), "IP address and port number are not valid.", Toast.LENGTH_SHORT).show();



                        }

                    }
                });

                negative_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();

                    }
                });

                neutral_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(text_info.getVisibility() == View.GONE){
                            text_info.setVisibility(View.VISIBLE);
                            neutral_btn.setText("BACK");
                        }else{
                            text_info.setVisibility(View.GONE);
                            neutral_btn.setText("INFO");
                        }

                    }
                });
            }
        });
    }
}

package com.techygeek.f3utilities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
//import android.content.Context;
import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.content.pm.PackageManager.NameNotFoundException;
//import android.content.res.AssetManager;
//import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.*;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;



import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends Activity {
    /** The view to show the ad. */
    private AdView adView;

    /* Your ad unit id. Replace with your actual ad unit id. */
    private static final String AD_UNIT_ID = "ca-app-pub-6428811458148980/5367725117";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        // Create an ad.
        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(AD_UNIT_ID);

        // Add the AdView to the view hierarchy. The view will have no size
        // until the ad is loaded.
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout2);
        layout.addView(adView);

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device.
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("0DE03E6D3F16381D069654B06F38077D")
                .build();

        // Start loading the ad in the background.
        adView.loadAd(adRequest);*/

        // Is this application running on a LG Optimus F3 (fx3)?
        String line = "";
        String CorrectDevice = "fx3_mpcs_tmo_us";
        String CorrectDevice2 = "fx3mt";

        // Get property and save to String line, then compare with StringDevice
        // If the output from getprop doesnt't match CorrectDevice, display a warning.
        try {
            Process ifc = Runtime.getRuntime().exec("getprop ro.product.name");
            BufferedReader bis = new BufferedReader(new InputStreamReader(ifc.getInputStream()));
            line = bis.readLine();
        } catch (java.io.IOException e) {
        }

        if (!line.equals(CorrectDevice)) {
            if (!line.equals(CorrectDevice2)) {
                AlertDialog.Builder IncorrectDevice = new AlertDialog.Builder(this);
                IncorrectDevice.setIcon(R.drawable.warn);
                IncorrectDevice.setTitle("Wrong Device!");
                IncorrectDevice.setMessage("You are probably not running this application on the LG Optimus F3. Please note that this application is intended to run on the LG Optimus F3. If you are using a LG Optimus F3, please contact your ROM developer and tell him, that the \nro.product.name should be fx3_mpcs_tmo_us.");
                IncorrectDevice.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                IncorrectDevice.show();
            }
        }
    }

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

    public void startroot(View view){
    	Intent intent = new Intent(this, RooterActivity.class);
        startActivity(intent);
    }

	public void recoveryInstaller(View view){
		//goto the recovery installer intent
		Intent intent = new Intent(this, RecoveryInstallerActivity.class);
		startActivity(intent);
	}
	
	public void recoveryUninstaller(View view){
		//goto the recovery uninstaller intent
		Intent intent = new Intent(this, RecoveryUninstallerActivity.class);
		startActivity(intent);
	}

    public void rebooter(View view){
        //i dont know if this will need an intent
        //goto the recovery installer intent
        //Intent intent = new Intent(this, rebooter.class);

        root_tools.execute("reboot recovery");
    }
	
	public void sdhacks(View view){

		Intent intent = new Intent(this, SDhacksActivity.class);

			startActivity(intent);
			}


	public void info(View view) {
        AlertDialog.Builder about = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        about.setTitle(R.string.title_activity_info);
        about.setIcon(R.drawable.apple);
        about.setView(inflater.inflate(R.layout.activity_info, null));
        about.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        about.show();
    }
 }

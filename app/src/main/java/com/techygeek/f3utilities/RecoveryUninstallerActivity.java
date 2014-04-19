package com.techygeek.f3utilities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;


public class RecoveryUninstallerActivity extends Activity {

    String tagname = "Recovery Restore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_uninstaller);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.recovery_uninstaller, menu);
        return true;
    }

    void install(){
        String dir = Environment.getExternalStorageDirectory() + "/F3Utilities/recovery";
        String cmd_install = "busybox dd if="+ dir + "/stock-recovery.img" + " of=/dev/block/platform/msm_sdcc.1/by-name/recovery";
        root_tools.execute(cmd_install);
        Log.i(tagname, "Stock Recovery restored.");
    }

    //the method to start the restore.
    public void start(View view) {
        final ProgressDialog RingProgressDialog = ProgressDialog.show(RecoveryUninstallerActivity.this, "Please Wait", "Restoring Recovery", true);
        RingProgressDialog.setCancelable(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //this is the runnable stuff for the progress bar
                    install();
                } catch (Exception e) {
                    Log.e(tagname, "something went wrong");
                }
                RingProgressDialog.dismiss();
            }
        }).start();
    }
}
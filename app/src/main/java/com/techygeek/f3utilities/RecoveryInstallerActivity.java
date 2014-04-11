package com.techygeek.f3utilities;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class RecoveryInstallerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recovery_installer);
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recovery_installer, menu);
		return true;
	}*/
	
	public void install(View view){
		//we have to make sure the right aboot is used first...
		
		String cmd_aboot = "busybox dd if=/data/data/com.techygeek.f3utilities/recovery/aboot.img of=/dev/block/platform/msm_sdcc.1/by-name/aboot";
		
		//where the install will happen...
		String cmd_install = "busybox dd if=/data/data/com.techygeek.f3utilities/recovery/fx3mt-cwm.lok of=/dev/block/platform/msm_sdcc.1/by-name/recovery";
		
		root_tools.execute(cmd_aboot);
		root_tools.execute(cmd_install);
		
		Context context = getApplicationContext();
		CharSequence text = "CWM has been installed!";
		int duration = Toast.LENGTH_SHORT;
		Toast success = Toast.makeText(context, text, duration);
		success.show();
		
	}

}

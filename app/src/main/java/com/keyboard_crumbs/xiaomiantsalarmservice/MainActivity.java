package com.keyboard_crumbs.xiaomiantsalarmservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences settings;
    SharedPreferences.Editor editor;
    private Switch enableSwitch;
    private Switch removeNotiSwitch;
    private Switch vibrateSwitch;
    private Button btnTest;
    private Intent dialogIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btnTest = (Button)findViewById(R.id.btnTestAlarm);
        enableSwitch = (Switch)findViewById(R.id.switch1);
        removeNotiSwitch = (Switch)findViewById(R.id.switch2);
        vibrateSwitch = (Switch)findViewById(R.id.switch3);

        settings = getApplicationContext().getSharedPreferences("settings", 0);
        editor = settings.edit();

        dialogIntent = new Intent(this, AlarmActivity.class);

        btnTest.setOnClickListener(onClickListener);

        enableSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("enable", isChecked);
                editor.apply();
            }
        });

        removeNotiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("removeNotification", isChecked);
                editor.apply();
            }
        });

        vibrateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("vibrate", isChecked);
                editor.apply();
            }
        });
    }

   @Override
    protected void onStart() {
        super.onStart();
        enableSwitch.setChecked(settings.getBoolean("enable", false));
        removeNotiSwitch.setChecked(settings.getBoolean("removeNotification", false));
        vibrateSwitch.setChecked(settings.getBoolean("vibrate", false));
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            switch(v.getId()){
                case R.id.btnTestAlarm:
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Bundle b = new Bundle();
                    b.putString("text", "Test Notification");
                    dialogIntent.putExtras(b);
                    startActivity(dialogIntent);
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}

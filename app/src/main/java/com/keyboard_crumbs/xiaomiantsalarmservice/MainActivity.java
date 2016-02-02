package com.keyboard_crumbs.xiaomiantsalarmservice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences settings;
    SharedPreferences.Editor editor;
    private Switch swEnableService;
    private Switch swRemoveNotification;
    private Switch swVibrate;
    private Button btnTest;
    private Intent dialogIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btnTest = (Button)findViewById(R.id.btnTestAlarm);
        swEnableService = (Switch)findViewById(R.id.switch1);
        swRemoveNotification = (Switch)findViewById(R.id.switch2);
        swVibrate = (Switch)findViewById(R.id.switch3);

        settings = getApplicationContext().getSharedPreferences("settings", 0);
        editor = settings.edit();

        dialogIntent = new Intent(this, AlarmActivity.class);

        btnTest.setOnClickListener(onClickListener);

        swEnableService.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("enable", isChecked);
                editor.apply();
            }
        });

        swRemoveNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("removeNotification", isChecked);
                editor.apply();
            }
        });

        swVibrate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("vibrate", isChecked);
                editor.apply();
            }
        });
    }

   @Override
    protected void onStart() {
        super.onStart();
        swEnableService.setChecked(settings.getBoolean("enable", false));
        swRemoveNotification.setChecked(settings.getBoolean("removeNotification", false));
        swVibrate.setChecked(settings.getBoolean("vibrate", false));
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

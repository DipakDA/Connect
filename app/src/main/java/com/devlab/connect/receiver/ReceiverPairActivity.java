package com.devlab.connect.receiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.devlab.connect.R;

public class ReceiverPairActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_pair);

        TextView textView = findViewById(R.id.pairTextView);

        String wifiHotspotName = getIntent().getStringExtra("WIFI_HOTSPOT_NAME");
        textView.setText(wifiHotspotName);

        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.i(null, "xxxxxxx");
            return;
        }
        for (WifiConfiguration wifiConfiguration : wifi.getConfiguredNetworks()) {
            Log.i(null, wifiConfiguration.SSID);
            if(wifiConfiguration.SSID.equals("\"" + wifiHotspotName + "\"")) {
                Log.i(null, "Z1");
                wifi.disconnect();
                wifi.enableNetwork(wifiConfiguration.networkId, true);
                wifi.reconnect();
            }
        }

    }
}
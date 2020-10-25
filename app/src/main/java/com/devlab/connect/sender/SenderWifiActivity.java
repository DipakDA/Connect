package com.devlab.connect.sender;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.devlab.connect.R;

public class SenderWifiActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender_wifi);

        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        TextView textView2 = findViewById(R.id.textView2);
        if (!wifi.isWifiEnabled()) {
            textView2.setText("Wifi is not enabled. Please enable and retry");

            //This method has been deprecated and hence user needs to turn on wifi on their own.
//            wifi.setWifiEnabled(true);
        } else {
            textView2.setText("get set go");

            WifiManager.LocalOnlyHotspotCallback localOnlyHotspotCallback = new LocalHotspotOverriddenClass();
            Handler handler = new Handler(Looper.myLooper());

            int localHotspotFailureReason = 0;

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here toss request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            wifi.startLocalOnlyHotspot(localOnlyHotspotCallback, handler);

        }


    }
}
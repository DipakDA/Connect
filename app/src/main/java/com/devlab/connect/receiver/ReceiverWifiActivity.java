package com.devlab.connect.receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devlab.connect.R;
import com.devlab.connect.sender.SenderWifiActivity;

import java.util.List;

public class ReceiverWifiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_wifi);

        final LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);

        final WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        new CountDownTimer(30000, 3000) {
            @Override
            public void onTick(long millisUntilFinished) {
                wifi.startScan();
                List<ScanResult> scanResultList = wifi.getScanResults();

                Log.i(null, "try");

                for(final ScanResult scanResult : scanResultList) {
                    if(scanResult.SSID.startsWith("Android")) {
                        TextView newTextView = new TextView(layout.getContext());
                        newTextView.setText(scanResult.SSID);
                        newTextView.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(v.getContext(), ReceiverPairActivity.class);
                                intent.putExtra("WIFI_HOTSPOT_NAME", scanResult.SSID);
//                                intent.putExtra("WIFI_KEY", scanResult.)/
                                startActivity(intent);
                            }
                        });
                        layout.addView(newTextView);
                        cancel();
                        break;
                    }
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }
}
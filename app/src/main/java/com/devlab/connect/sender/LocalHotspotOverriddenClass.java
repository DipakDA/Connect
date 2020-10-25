package com.devlab.connect.sender;

import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.O)
public class LocalHotspotOverriddenClass extends WifiManager.LocalOnlyHotspotCallback {

    @Override
    public void onFailed(int reason) {
        super.onFailed(reason);
        Log.i(null, String.valueOf(reason));
    }

    @Override
    public void onStarted(final WifiManager.LocalOnlyHotspotReservation reservation) {
        Log.i(null, "Started");
        super.onStarted(reservation);
        Log.i(null, "dipak");
        Log.i(null, String.valueOf(reservation));
        Log.i(null, reservation.getWifiConfiguration().toString());

        Handler handler = new Handler(Looper.myLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i(null, "disconnecting");
                reservation.close();
            }
        }, 40000);
    }
}

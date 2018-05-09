package se.abilia.gcmreceiver;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GcmBroadcastReceiver  extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
        String type = gcm.getMessageType(intent);

        Toast.makeText(context, "Received push message of type: " + type, Toast.LENGTH_LONG).show();
        Log.d("gcm", "Received push message of type: " + type);
    }
}

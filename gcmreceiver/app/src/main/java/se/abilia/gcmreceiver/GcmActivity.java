package se.abilia.gcmreceiver;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

public class GcmActivity extends Activity implements View.OnClickListener {

    private final static String SENDER_ID = "<Sender id here>";

    private TextView regIdText;

    private GoogleCloudMessaging gcm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());

        regIdText = findViewById(R.id.regId);
        findViewById(R.id.getRegId).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                try {
                    String regId = gcm.register(SENDER_ID);
                    Log.i("GCM",  "Device registered. RegId: " + regId);

                    return "RegID: " + regId;
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                    return "Error: " + ex;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                regIdText.setText(result);
            }
        }.execute(null, null, null);
    }
}

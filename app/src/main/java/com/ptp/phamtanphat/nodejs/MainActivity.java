package com.ptp.phamtanphat.nodejs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    //Mo ket noi toi server
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://10.0.2.36:3000/");
        } catch (URISyntaxException e) {}
    }
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.textview);
        mSocket.connect();
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSocket.on("serverguitinnhan",onRetrivedata);
            }
        });
    }
    public Emitter.Listener onRetrivedata = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject jsonObject = (JSONObject) args[0];
                    Log.d("BBB",jsonObject.toString());

                    try {
                        String dulieu = jsonObject.getString("noidung");
                        Log.d("BBB",dulieu);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    };
}

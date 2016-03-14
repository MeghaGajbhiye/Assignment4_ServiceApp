package com.megha.assignment4_serviceapp;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by megha on 3/10/2016.
 */
public class ServiceActivity extends AppCompatActivity {
    IntentFilter intentFilter;
    private MyService serviceBinder;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void start_download(View view)
    {
        //---intent to filter for file downloaded intent---
        intentFilter = new IntentFilter();
        intentFilter.addAction("FILE_DOWNLOADED_ACTION");

        //---register the receiver---
        registerReceiver(intentReceiver, intentFilter);


        EditText editText1 = (EditText) findViewById(R.id.et1);
        EditText editText2 = (EditText) findViewById(R.id.et2);
        EditText editText3 = (EditText) findViewById(R.id.et3);
        EditText editText4 = (EditText) findViewById(R.id.et4);
        EditText editText5 = (EditText) findViewById(R.id.et5);

        Intent intent = new Intent(getBaseContext(), MyService.class);
        try {
             System.out.println(editText1.getText().toString());
            URL[] urls = new URL[] {
                   // new URL("https://www.cisco.com/web/about/ac79/docs/innov/IoE.pdf"),
                    new URL(editText1.getText().toString()),
                    new URL(editText2.getText().toString()),
                    new URL(editText3.getText().toString()),
                    new URL(editText4.getText().toString()),
                    new URL(editText5.getText().toString())};

//                    URL[] urls = new URL[] {
//                            new URL("http://www.amazon.com/somefiles.pdf"),
//                            new URL("http://www.wrox.com/somefiles.pdf"),
//                            new URL("http://www.google.com/somefiles.pdf"),
//                            new URL("http://www.learn2develop.net/somefiles.pdf")};

            intent.putExtra("URLs", urls);


        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        startService(intent);


    }

/*
    private ServiceConnection connection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            //---called when the connection is made---
            serviceBinder = ((MyService.MyBinder)service).getService();

            try {
                URL[] urls = new URL[] {
                        new URL("http://www.amazon.com/somefiles.pdf"),
                        new URL("http://www.wrox.com/somefiles.pdf"),
                        new URL("http://www.google.com/somefiles.pdf"),
                        new URL("http://www.learn2develop.net/somefiles.pdf")};
                serviceBinder.urls = urls;

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            startService(i);
        }
        public void onServiceDisconnected(ComponentName className) {
            //---called when the service disconnects---
            serviceBinder = null;
        }
    };

*/
    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getBaseContext(), "File downloaded!",
                    Toast.LENGTH_LONG).show();
        }
    };
}

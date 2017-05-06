package com.example.angelo.iotproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;


public class MainActivity extends AppCompatActivity {

    EditText client,server,porta;
    String clientName, serverName;
    int portaName;
    Button conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client=(EditText)findViewById(R.id.clientId);
        server=(EditText)findViewById(R.id.serverURI);
        porta=(EditText)findViewById(R.id.port);
        conn=(Button)findViewById(R.id.newConnection);



        conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(parserValori(client.getText().toString(),server.getText().toString(),porta.getText().toString())){
                    clientName=client.getText().toString();
                    serverName=server.getText().toString();
                    portaName=Integer.parseInt(porta.getText().toString());
                    serverName="tcp://"+serverName+":"+portaName;


                    MqttAndroidClient clientAndroid=SingletonConnection.getInstance(getApplicationContext()).createClient(getApplicationContext(),serverName,clientName);
                    try {
                        clientAndroid.connect();

                        Log.d("ESEGUI","ok");
                        if(clientAndroid.isConnected()){
                            Log.d("CONNESSIONE","sei connesso");

                        }
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }

                }else Toast.makeText(getApplicationContext(), "inserire campi mancanti",Toast.LENGTH_LONG).show();
            }
        });




    }
    private boolean parserValori(String client, String server, String porta) {
        boolean ok=false;
        if(client.equals("") || server.equals("")  || porta.equals("")){

        }else ok=true;
        return ok;
    }

}

package com.example.angelo.iotproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText client,server,porta;
    String clientName, serverName, portName;
    Button conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client=(EditText)findViewById(R.id.clientId);
        server=(EditText)findViewById(R.id.serverURI);
        porta=(EditText)findViewById(R.id.port);
        conn=(Button)findViewById(R.id.newConnection);

        clientName=client.getText().toString();
        serverName=server.getText().toString();
        portName=porta.getText().toString();

        conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(parserValori(clientName,serverName,portName)){
                    Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_LONG).show();
                }
            }
        });




    }
    private boolean parserValori(String client, String server, String porta) {
        boolean ok=false;
        if(client=="" || server==""  || porta==""){

        }else ok=true;
        return ok;
    }

}

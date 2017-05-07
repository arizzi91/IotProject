package com.example.angelo.iotproject;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;



public class MainActivity extends AppCompatActivity {

    EditText client,server,porta,topic;
    String clientName, serverName,topicName;
    int portaName;
    Button conn;
    MqttAndroidClient clientAndroid;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client=(EditText)findViewById(R.id.clientId);
        server=(EditText)findViewById(R.id.serverURI);
        porta=(EditText)findViewById(R.id.port);
        topic=(EditText)findViewById(R.id.sub);
        conn=(Button)findViewById(R.id.newConnection);
        imageView=(ImageView)findViewById(R.id.image);



        conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(parserValori(client.getText().toString(),server.getText().toString(),porta.getText().toString(),topic.getText().toString())){
                    clientName=client.getText().toString();
                    serverName=server.getText().toString();
                    portaName=Integer.parseInt(porta.getText().toString());
                    topicName=topic.getText().toString();
                    serverName="tcp://"+serverName+":"+portaName;



                    clientAndroid=SingletonConnection.getInstance(getApplicationContext()).createClient(getApplicationContext(),serverName,clientName);
                    clientAndroid.setCallback(new MqttCallback() {
                        @Override
                        public void connectionLost(Throwable cause) {

                        }

                        @Override
                        public void messageArrived(String topic, MqttMessage message) throws Exception {
                            Log.d("messaggio", new String(message.getPayload()));
                            //Bitmap bitmap=DecodeImage.decode(message);
                            //mageView.setImageBitmap(bitmap);
                        }

                        @Override
                        public void deliveryComplete(IMqttDeliveryToken token) {

                        }
                    });
                    MqttConnectOptions mqttConnectOptions= new MqttConnectOptions();
                    mqttConnectOptions.setCleanSession(false);


                    try {

                        clientAndroid.connect(mqttConnectOptions, null, new IMqttActionListener() {
                            @Override
                            public void onSuccess(IMqttToken asyncActionToken) {
                                sottoscriviTopic(topicName);
                            }

                            @Override
                            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                            }
                        });



                    } catch (MqttException e) {
                        e.printStackTrace();
                    }

                }else Toast.makeText(getApplicationContext(), "inserire campi mancanti",Toast.LENGTH_LONG).show();
            }
        });




    }

    private void sottoscriviTopic(String topic) {
        try {
            clientAndroid.subscribe(topic, 2, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d("SOTTOSCRITTO", "ok");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.d("SOTTOSCRITTO", "no");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

    private boolean parserValori(String client, String server, String porta, String topic) {
        boolean ok=false;
        if(client.equals("") || server.equals("")  || porta.equals("") || topic.equals("")){

        }else ok=true;
        return ok;
    }

}

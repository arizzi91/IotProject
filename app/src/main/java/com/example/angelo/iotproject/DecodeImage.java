package com.example.angelo.iotproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by angelo on 05/05/17.
 */

public class DecodeImage {


    public static Bitmap decode(MqttMessage message){
        Bitmap image;
        InputStream stream = new ByteArrayInputStream(message.getPayload());
        image = BitmapFactory.decodeStream(stream);
        return image;
    }


}

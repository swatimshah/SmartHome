package com.example.smarthome;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.amazonaws.mobileconnectors.iot.AWSIotKeystoreHelper;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttManager;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttQos;

import org.json.JSONObject;

import java.security.KeyStore;

public class MyNetworkTaskForOff {

    Context myContext;

    public MyNetworkTaskForOff(Context context) {

        myContext = context;
        java.util.logging.Logger.getLogger("com.amazonaws").setLevel(java.util.logging.Level.ALL);
    }

    // IoT endpoint
    // AWS Iot CLI describe-endpoint call returns: XXXXXXXXXX.iot.<region>.amazonaws.com
    private static final String CUSTOMER_SPECIFIC_ENDPOINT = "a27kjby2cs8yuj-ats.iot.us-west-2.amazonaws.com";
    // Filename of KeyStore file on the filesystem
    private static final String KEYSTORE_NAME = "debug.bks"; //"iot_keystore";
    // Password for the private key in the KeyStore
    private static final String KEYSTORE_PASSWORD = "android";
    // Certificate and key aliases in the KeyStore
    //private static final String CERTIFICATE_ID = "android_root_der";
    private static final String CERTIFICATE_ID = "phone_key_and_cert";
    // Name of the AWS IoT policy to attach to a newly created certificate
    private static final String AWS_IOT_POLICY_NAME = "android-phone-policy";

    AWSIotMqttManager mqttManager;
    String clientId = "android_phone";
    String keystorePath;
    String keystoreName;
    String keystorePassword;
    String certificateId;
    KeyStore clientKeyStore = null;

    public void createMyAsyncTaskForOffLed2() {

        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    java.util.logging.Logger.getLogger("com.amazonaws").setLevel(java.util.logging.Level.ALL);
                    java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.ALL);
                    initIoTClient();
                    mqttManager.connect(clientKeyStore, new AWSIotMqttClientStatusCallback() {
                        @Override
                        public void onStatusChanged(AWSIotMqttClientStatus status, Throwable throwable) {
                            Log.d("SmartHome", "Status = " + String.valueOf(status));
                            if (throwable != null) {
                                Log.e("SmartHome", "Connection error.", throwable.getCause());
                            }
                            if(String.valueOf(status).equals("Connected")) {
                                Log.i("SmartHome", "Publishing message");

                                try {

                                    JSONObject json = new JSONObject();
                                    json.put("state", "led2_off");

                                    final String topic = "NodeMCU/inTopic";
                                    final String msg = json.toString();

                                    mqttManager.publishString(msg, topic, AWSIotMqttQos.QOS1);

                                    Log.i("SmartHome", "Message published successfully on: NodeMCU/inTopic ");

                                } catch (Exception e) {
                                    Log.e("SmartHome", "Publish error.", e);
                                }

                            }

                        }
                    });

                    mqttManager.disconnect();

                } catch (Exception e) {
                    Log.i("SmartHome", "Invalid JSON" + e.getMessage());
                }

            }
        }

        );

        thread.start();
    }


    public void createMyAsyncTaskForOffLed4() {

        Thread thread = new Thread(new Runnable() {
            public void run() {
                    try {
                        java.util.logging.Logger.getLogger("com.amazonaws").setLevel(java.util.logging.Level.ALL);
                        java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.ALL);
                        initIoTClient();
                        mqttManager.connect(clientKeyStore, new AWSIotMqttClientStatusCallback() {
                            @Override
                            public void onStatusChanged(AWSIotMqttClientStatus status, Throwable throwable) {
                                Log.d("SmartHome", "Status = " + String.valueOf(status));
                                if(throwable != null) {
                                    Log.e("SmartHome", "Connection error.", throwable.getCause());
                                }
                                if(String.valueOf(status).equals("Connected")) {

                                    Log.i("SmartHome", "Publishing message");

                                    try {

                                        JSONObject json = new JSONObject();
                                        json.put("state", "led4_off");

                                        final String topic = "NodeMCU/inTopic";
                                        final String msg = json.toString();

                                        mqttManager.publishString(msg, topic, AWSIotMqttQos.QOS1);

                                        Log.i("SmartHome", "Message published successfully on: NodeMCU/inTopic ");

                                    } catch (Exception e) {
                                        Log.e("SmartHome", "Publish error.", e);
                                    }

                                }
                            }
                        });

                        mqttManager.disconnect();


                    } catch(Exception e) {
                        Log.i("SmartHome", "Invalid JSON" + e.getMessage());
                    }




            }
        }

        );

        thread.start();

    }


    void initIoTClient() {
        // MQTT Client
        mqttManager = new AWSIotMqttManager(clientId, CUSTOMER_SPECIFIC_ENDPOINT);

        // Set keepalive to 10 seconds.  Will recognize disconnects more quickly but will also send
        // MQTT pings every 10 seconds.
        mqttManager.setKeepAlive(60);

        keystorePath =  Environment.getExternalStorageDirectory().toString() + "/Download/";
        keystoreName = KEYSTORE_NAME;
        keystorePassword = KEYSTORE_PASSWORD;
        certificateId = CERTIFICATE_ID;

        // To load cert/key from keystore on filesystem
        try {
            if (AWSIotKeystoreHelper
                    .isKeystorePresent(keystorePath, keystoreName)) {
                if (AWSIotKeystoreHelper.keystoreContainsAlias(certificateId, keystorePath,
                        keystoreName, keystorePassword)) {
                    Log.i("SmartHome", "Certificate " + certificateId
                            + " found in keystore - using for MQTT.");

                    clientKeyStore = AWSIotKeystoreHelper.getIotKeystore(certificateId,
                            keystorePath, keystoreName, keystorePassword);
                } else {
                    Log.i("SmartHome", "Key/cert " + certificateId + " not found in keystore.");
                }
            } else {
                Log.i("SmartHome", "Keystore " + keystorePath + "/" + keystoreName + " not found.");
            }
        } catch (Exception e) {
            Log.e("SmartHome", "An error occurred retrieving cert/key from keystore.", e);
        }

    }


}

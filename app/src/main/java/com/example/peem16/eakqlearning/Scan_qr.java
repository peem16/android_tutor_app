package com.example.peem16.eakqlearning;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Scan_qr  extends AppCompatActivity {
    SurfaceView camerePreview;
    TextView txtResult;
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;
    final int RequestCameraPermisstionID = 1001;
    private  String keysv = ServerConnect.KEY_SERVER;
    String AES ="AES";
    final Handler handler = new Handler();

    private String sss ;
    private String jResult;
    private ProcessRollcall Rollcall;

    public boolean  check = true;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case RequestCameraPermisstionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    try {
                        cameraSource.start(camerePreview.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_qr);








        camerePreview = (SurfaceView) findViewById(R.id.camaraPreview);
        txtResult = (TextView) findViewById(R.id.txtResult);
        Initial.SharePref = new PreferenceClass(this);


        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .build();

        camerePreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(Scan_qr.this,
                            new String[]{android.Manifest.permission.CAMERA},RequestCameraPermisstionID);

                    return;
                }
                try {
                    cameraSource.start(camerePreview.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }





        });

        barcodeDetector.setProcessor (new Detector.Processor<Barcode>(){
            @Override
            public void release() {

                try {
                    barcodeDetector.wait(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }


            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrcodes = detections.getDetectedItems();
                if(qrcodes.size() !=0) {


                if(check==true){
                    check = false;



                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Do something after 5s = 5000ms
                                txtResult.setText(qrcodes.valueAt(0).displayValue);


                                sss = qrcodes.valueAt(0).displayValue;


                                Rollcall = new ProcessRollcall();
                                if (Build.VERSION.SDK_INT >= 11) {
                                    //--post GB use serial executor by default --
                                    Rollcall.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, decrypt(sss, "eakqtutor2018"), Initial.SharePref.getCoures());
                                } else {
                                    //--GB uses ThreadPoolExecutor by default--
                                    Rollcall.execute();
                                }

                            }
                        }, 1000);

                    }
                }



            }
        });
    }


    private String decrypt(String outputString,String password){
        try{
        SecretKeySpec  key = generateKey(password);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.DECRYPT_MODE,key);
        byte[] decodeValue = Base64.decode(outputString,Base64.DEFAULT);
        byte[] decValue = c.doFinal(decodeValue);
        String decryptedValue = new String(decValue);
            return decryptedValue;

        }catch (Exception e){
            return "";

        }



    }

    private class ProcessRollcall extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("IDUserAccount",params[0]);
            ph.add("tiemtable",params[1]);


            Initial.httpConn = new ServerConnect(Scan_qr.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.Rollcall_qr, ph);



            ShowMe.log("RESULT="+jResult);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {


                Initial.jObject = new JSONObject(jResult);

                if (Initial.jObject.getString("status").trim().equals("true")) {

                    Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(1000);

                }else{


                }
                check = true;

            }catch (Exception e){
                e.printStackTrace();

            }





        }




    }

    private SecretKeySpec generateKey(String password) throws Exception{
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes,0,bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key,"AES");
        return secretKeySpec;



    }

}

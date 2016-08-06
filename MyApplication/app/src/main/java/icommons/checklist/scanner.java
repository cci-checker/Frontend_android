package icommons.checklist;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.Camera;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class scanner extends Activity {
    private TextView info;
    private SurfaceView cameraView;
    private Button add_manually;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_scanner);

        cameraView = (SurfaceView)findViewById(R.id.camera_view);
        // for making the surface view full screen
        Display display = getWindowManager().getDefaultDisplay();
        int childWidthMeasureSpec = getScreenWidth();
        int childHeightMeasureSpec = getScreenHeight();
        cameraView.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        // end of the full screen settings

        info = (TextView)findViewById(R.id.code_info);
        add_manually = (Button)findViewById(R.id.button);

        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();

        int numberOfCameras = Camera.getNumberOfCameras();
        if (numberOfCameras==1) {

            cameraSource = new CameraSource.Builder(getApplicationContext(), barcodeDetector)
                    .setFacing(CameraSource.CAMERA_FACING_FRONT)
                    .build();
        }
        else {
            cameraSource = new CameraSource.Builder(getApplicationContext(), barcodeDetector)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1600, 1024)
                    .setRequestedFps(15.0f).setAutoFocusEnabled(true)
                    .build();

        }



        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.CAMERA
                }, 10);
            }
            return;
        } else {

            cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {

                    try {

                        cameraSource.start(cameraView.getHolder());


                    } catch (IOException ie) {
                        Log.e("CAMERA SOURCE", ie.getMessage());
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

            barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
                @Override
                public void release() {

                }

                @Override
                public void receiveDetections(Detector.Detections<Barcode> detections) {
                    final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                    if (barcodes.size() != 0) {
                        info.post(new Runnable() {
                            @Override
                            public void run() {
                                info.setText(barcodes.valueAt(0).displayValue);
                                Toast.makeText(getBaseContext(), info.getText(), Toast.LENGTH_SHORT).show();
                                if (info.getText().toString().equalsIgnoreCase("RUSH009")){
                                    startActivity(new Intent(scanner.this, checklist_page.class));
                                }
                            }
                        });
                    }

                }
            });
        }
        add_manually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(scanner.this, PIN.class));
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraSource.release();
        barcodeDetector.release();
    }


}

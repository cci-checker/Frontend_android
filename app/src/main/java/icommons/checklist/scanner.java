package icommons.checklist;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import icommons.checklist.Helper_functions.Camera_functions;
import icommons.checklist.Interface_api.MyApiEndpointInterface;
import icommons.checklist.Pojo_classes.Rooms;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class scanner extends Activity {
    private static final String TAG = "Scanner Class";
    private TextView info;
    private Button flash;
    private SurfaceView cameraView;
    private Button add_manually;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private Camera camera = null;
    private ImageView topimage;
    Boolean api_workied, match_found;

    List<Rooms> rooms_List;
    Integer St_id;

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
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.success);
        //Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_scanner);
        topimage = (ImageView) findViewById(R.id.image_top);

        if (getIntent().getExtras()!=null){
            St_id=getIntent().getIntExtra("St_uid", -1);

        }

        cameraView = (SurfaceView)findViewById(R.id.camera_view);
        flash = (Button) findViewById(R.id.flash);
        // for making the surface view full screen
        //Display display = getWindowManager().getDefaultDisplay();
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
                    .setFacing(CameraSource.CAMERA_FACING_FRONT).setAutoFocusEnabled(true)
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





            MyApiEndpointInterface.factory.getInstance().getRooms().enqueue(new Callback<List<Rooms>>() {
                @Override
                public void onResponse(Call<List<Rooms>> call, Response<List<Rooms>> response) {
                    if (response.isSuccessful()) {
                        rooms_List = response.body();
                        api_workied=true;
                    }
                    else{
                        api_workied=false;
                    }
                }

                @Override
                public void onFailure(Call<List<Rooms>> call, Throwable t) {
                    Log.d(TAG, "Failure: "+t.getMessage());
                    api_workied=false;

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
                                String qrcode_val = barcodes.valueAt(0).displayValue;
                                info.setText(qrcode_val);
                                Log.d(TAG, "Rooms: "+ rooms_List);
                                String room_qr=null;

                                // go to the checklist page with the given list (Opening or closing)

                                for (int b=0; b<rooms_List.size(); b++){
                                    room_qr=rooms_List.get(b).getBuilding_name()+ Integer.toString(rooms_List.get(b).getFloor_number()) + Integer.toString(rooms_List.get(b).getRoom_number());
                                    Log.d(TAG, "Room qr from django :"+ room_qr);
                                    if (room_qr.equalsIgnoreCase(qrcode_val)){
                                        match_found=true;
                                        topimage.setImageResource(R.mipmap.green);
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                topimage.setImageResource(R.mipmap.del);
                                                // Actions to do after 2 seconds
                                            }
                                        }, 1000);
                                        mp.start();
                                        try{
                                            cameraSource.release();
                                        }
                                        catch (NullPointerException e){
                                            System.out.print("Exception is : "+ e);
                                        }
                                        // depending on time pass the right opening/closing list for that room and go to the next activity
                                        Intent n_intent= new Intent(scanner.this, checklist_page.class);

                                        //list can't be passed as intent.putExtra.
                                        n_intent.putIntegerArrayListExtra("id_listitems", (ArrayList<Integer>) rooms_List.get(b).getOpening_list());
                                        n_intent.putExtra("class_name", room_qr);
                                        n_intent.putExtra("Room_id", rooms_List.get(b).getId());
                                        n_intent.putExtra("St_uid", St_id);
                                        startActivity(n_intent);


                                        break;
                                    }
                                    else{
                                        match_found=false;
                                    }
                                }
                                if (match_found==false){
                                    topimage.setImageResource(R.mipmap.red);

                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            topimage.setImageResource(R.mipmap.del);
                                            // Actions to do after 2 seconds
                                        }
                                    }, 1000);
                                }
                                //Toast.makeText(getBaseContext(), info.getText(), Toast.LENGTH_SHORT).show();
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
        flash.setOnClickListener(new View.OnClickListener() {
           // String cameraId="";
            //@TargetApi(Build.VERSION_CODES.M)

            @Override
            public void onClick(View v) {
                Camera_functions cameraFunctions = new Camera_functions(getApplicationContext());
                cameraFunctions.flashOnButton(camera, cameraSource);

            }
        });




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraSource.release();
        barcodeDetector.release();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(scanner.this, LoginID_page.class));
        //super.onBackPressed();
    }






}

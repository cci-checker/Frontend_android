package icommons.checklist.Helper_functions;

import android.content.Context;
import android.hardware.Camera;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;

import java.lang.reflect.Field;

/**
 * Created by Tushar on 3/23/17.
 */
public class Camera_functions {

    private Context cont;
    boolean flashmode=false;

    public Camera_functions(Context context){
        this.cont =  context;
    }

    // had a static on it.
    public Camera getCamera(CameraSource cameraSource) {
        Field[] declaredFields = CameraSource.class.getDeclaredFields();

        for (Field field : declaredFields) {
            if (field.getType() == Camera.class) {
                field.setAccessible(true);
                try {
                    Camera camera = (Camera) field.get(cameraSource);
                    if (camera != null) {
                        return camera;
                    }
                    return null;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return null;
    }

    public void flashOnButton(Camera cam, CameraSource camsource) {
        //System.out.print("In flashonbutton");
        //Camera_functions cameraFunctions = new Camera_functions(cont);

        cam = getCamera(camsource);
        if (cam != null) {
            try {
                Camera.Parameters param = cam.getParameters();
                param.setFlashMode(!flashmode?Camera.Parameters.FLASH_MODE_TORCH :Camera.Parameters.FLASH_MODE_OFF);
                cam.setParameters(param);
                flashmode = !flashmode;
                if(flashmode){
                    Toast.makeText(cont, "Flash Switched ON", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(cont, "Flash Switched Off", Toast.LENGTH_SHORT).show();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

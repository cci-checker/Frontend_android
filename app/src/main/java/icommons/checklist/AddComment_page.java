package icommons.checklist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Tushar on 8/14/16.
 */

// this activity always gets the user with it!
public class AddComment_page extends Activity {

    Button reportbtn;
    ImageView preview;
    private static final int CAM_REQUEST = 1313;
    Button picbtn;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAM_REQUEST){
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            preview.setImageBitmap(thumbnail);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_comment);

        reportbtn= (Button) findViewById(R.id.report_btn);
        preview= (ImageView)findViewById(R.id.preview);
        picbtn=(Button) findViewById(R.id.pic_btn);

        picbtn.setOnClickListener(new btnphotoclick());

        reportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
    class btnphotoclick implements Button.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent CameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(CameraIntent, CAM_REQUEST);

        }
    }

}

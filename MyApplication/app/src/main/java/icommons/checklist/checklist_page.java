package icommons.checklist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Vector;

/**
 * Created by Tushar on 7/18/16.
 */
public class checklist_page extends Activity {
    private static final String TAG = "Add Manually";
    TextView head;
    Button addcommentbtn;
    Button submitbtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.classroom1_checklist);
        Classroom_item hello = new Classroom_item();
        hello.set_RoomID(5);
        Vector<Integer> sample_temp = new Vector<Integer>(5);
        sample_temp.addElement(1);
        sample_temp.addElement(2);
        sample_temp.addElement(4);
        sample_temp.addElement(56);
        sample_temp.addElement(15);
        System.out.print(sample_temp.size());
        head = (TextView)findViewById(R.id.headingclass);
        submitbtn = (Button) findViewById(R.id.submitbtn);
        addcommentbtn = (Button) findViewById(R.id.comment);

        head.setText("Class Name");

        Log.d(TAG, "Size of the array" + sample_temp.size());

        LinearLayout that = (LinearLayout) findViewById(R.id.hello);
        Vector<CheckBox> todel = new Vector<CheckBox>();


        for (int a=0; a<sample_temp.size(); a++) {

            CheckBox temp1 = new CheckBox(this);
            temp1.setText(sample_temp.get(a).toString());
            todel.add(temp1);
            that.addView(temp1);
        }











    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(checklist_page.this, scanner.class));

    }
}

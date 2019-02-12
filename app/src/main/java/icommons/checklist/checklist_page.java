package icommons.checklist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import icommons.checklist.Interface_api.MyApiEndpointInterface;
import icommons.checklist.Pojo_classes.Checklist_items;
import icommons.checklist.Pojo_classes.checkoff_list;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Tushar on 7/18/16.
 */
public class checklist_page extends Activity {
    private static final String TAG = "Add Manually";
    TextView head;
    Button addcommentbtn;
    Button submitbtn;
    List<Integer> item_ids;

    String Classname;
    Integer Room_id=null;
    Integer Student_id;


    Boolean api_worked;


    public List<Checklist_items> all_checklist_items=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.classroom1_checklist);

        head = (TextView)findViewById(R.id.headingclass);
        submitbtn = (Button) findViewById(R.id.submitbtn);
        addcommentbtn = (Button) findViewById(R.id.comment);




        if (getIntent().getExtras()!=null){
            item_ids=getIntent().getIntegerArrayListExtra("id_listitems");
            Classname=getIntent().getStringExtra("class_name");
            Room_id=getIntent().getIntExtra("Room_id", -1);
            Student_id=getIntent().getIntExtra("St_uid", -1);

        }
        Log.d(TAG, "The list is :"+item_ids);

        head.setText(Classname);


        MyApiEndpointInterface.factory.getInstance().getChecklist_items().enqueue(new Callback<List<Checklist_items>>() {
            @Override
            public void onResponse(Call<List<Checklist_items>> call, Response<List<Checklist_items>> response) {
                if (response.isSuccessful()){
                    Log.d(TAG, "Got a response back");
                    setvar(response.body());
                    api_worked=true;
                }
                else{
                    Log.d(TAG, "Response Failed!");
                }
            }




            @Override
            public void onFailure(Call<List<Checklist_items>> call, Throwable t) {
                // make a dialog box for ui here.
                Log.d(TAG, "Response call failed");

            }
        });



    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent inte=new Intent(checklist_page.this, scanner.class);
        inte.putExtra("St_uid", Student_id);
        startActivity(inte);

    }

    public void setvar(List<Checklist_items> data_s){

        HashMap<Integer, String> data = new HashMap<>();
        final HashMap<String, Integer> data_rev = new HashMap<>();
        Log.d(TAG,"response is77t: "+data_s);
        final List<Integer> checkedList=new ArrayList<>();


        // Log.d(TAG, "passed size: "+item_ids.size()+ "\n django size: "+ all_checklist_items.size());
        for (int a=0; a<item_ids.size(); a++){
            for (int m=0; m<data_s.size(); m++){
                Log.d(TAG, "passed val"+ item_ids.get(a)+"\n django value:" + data_s.get(m).getId());
                if (item_ids.get(a).equals((Integer) data_s.get(m).getId())){
                    data.put( item_ids.get(a), data_s.get(m).getItem_name());
                    data_rev.put(data_s.get(m).getItem_name(), item_ids.get(a));
                    Log.d(TAG, "Match found");
                }
            }
        }





       // Log.d(TAG, "Data size2: "+ data.size());
        Vector<String> sample_temp = new Vector<String>(data.values());




        //System.out.print(sample_temp.size());


        Log.d(TAG, "Size of the array" + sample_temp.size());

        LinearLayout that = (LinearLayout) findViewById(R.id.hello);
        final Vector<CheckBox> todel = new Vector<CheckBox>();


        for (int a=0; a<sample_temp.size(); a++) {

            CheckBox temp1 = new CheckBox(this);
            temp1.setText(sample_temp.get(a).toString());
            //temp1.inflate(R.layout.right_checkbox,null);
            todel.add(temp1);
            that.addView(temp1);
        }



        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String list="";
                for (int m=0; m<todel.size(); m++){
                    if (todel.get(m).isChecked()){
                        list = list+todel.get(m).getText();
                        checkedList.add(data_rev.get(todel.get(m).getText()));

                        if (m!=todel.size()-1){
                            list=list+" ,";
                        }
                    }
                }
                list= list+" are the items checked by you.";
                Log.d(TAG, "The string is : " + list);
                //Toast.makeText(getBaseContext(), info.getText(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getBaseContext(), list, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Checklist numbers are: "+checkedList);
                checkoff_list instance=new checkoff_list();
                instance.setDate_time_stamp("2017-01-12T05:23:28Z");
                instance.setinstance_name("From Phone api");
                instance.setStudentUID(Student_id);
                instance.setRoomID(Room_id);
                instance.setIssueReport(1);
                instance.setTask_list(item_ids);
                instance.setTask_completedlist(checkedList);

                Log.d(TAG, "Sending data : "+ instance.toString());


                MyApiEndpointInterface.factory.getInstance().submit_instance("application/json", instance).enqueue(new Callback<checkoff_list>() {
                    @Override
                    public void onResponse(Call<checkoff_list> call, Response<checkoff_list> response) {
                        if (response.isSuccessful()){
                            Log.d(TAG, "The post call is working.");
                        }
                        else{
                            Log.d(TAG, "Not working1: "+ response.errorBody()+"\n And : "+ response.message());
                        }
                        activity_start();
                    }

                    @Override
                    public void onFailure(Call<checkoff_list> call, Throwable t) {
                        Log.d(TAG, "Not working1 message :"+ t.getMessage());

                    }
                });


            }
        });

        //Log.d(TAG, "Checklist numbers are: "+checkedList);

        addcommentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(getApplicationContext(), "This feature will be implemented shortly", Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void activity_start() {
        AlertDialog check= new AlertDialog.Builder(this)
                .setTitle("Status")
                .setMessage("Data sent successfully! Finish another room?")
                .setCancelable(false)
                .setNegativeButton("No, I'm done!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(checklist_page.this, LoginID_page.class));
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent in=new Intent(checklist_page.this, scanner.class);
                        in.putExtra("St_uid", Student_id);
                        startActivity(in);
                    }
                }).create();
        check.setCanceledOnTouchOutside(false);
        check.show();
    }
}
